48f13c8a (David A. Velasco 2012-09-05 13:46:30 +0200   1) /* ownCloud Android client application
bb257ec7 (David A. Velasco 2013-02-07 18:45:10 +0100   2)  *   Copyright (C) 2012-2013 ownCloud Inc.
48f13c8a (David A. Velasco 2012-09-05 13:46:30 +0200   3)  *
48f13c8a (David A. Velasco 2012-09-05 13:46:30 +0200   4)  *   This program is free software: you can redistribute it and/or modify
bdc0332c (David A. Velasco 2013-04-17 12:26:13 +0200   5)  *   it under the terms of the GNU General Public License version 2,
bdc0332c (David A. Velasco 2013-04-17 12:26:13 +0200   6)  *   as published by the Free Software Foundation.
48f13c8a (David A. Velasco 2012-09-05 13:46:30 +0200   7)  *
48f13c8a (David A. Velasco 2012-09-05 13:46:30 +0200   8)  *   This program is distributed in the hope that it will be useful,
48f13c8a (David A. Velasco 2012-09-05 13:46:30 +0200   9)  *   but WITHOUT ANY WARRANTY; without even the implied warranty of
48f13c8a (David A. Velasco 2012-09-05 13:46:30 +0200  10)  *   MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
48f13c8a (David A. Velasco 2012-09-05 13:46:30 +0200  11)  *   GNU General Public License for more details.
48f13c8a (David A. Velasco 2012-09-05 13:46:30 +0200  12)  *
48f13c8a (David A. Velasco 2012-09-05 13:46:30 +0200  13)  *   You should have received a copy of the GNU General Public License
48f13c8a (David A. Velasco 2012-09-05 13:46:30 +0200  14)  *   along with this program.  If not, see <http://www.gnu.org/licenses/>.
48f13c8a (David A. Velasco 2012-09-05 13:46:30 +0200  15)  *
48f13c8a (David A. Velasco 2012-09-05 13:46:30 +0200  16)  */
48f13c8a (David A. Velasco 2012-09-05 13:46:30 +0200  17) package com.owncloud.android.operations;
48f13c8a (David A. Velasco 2012-09-05 13:46:30 +0200  18) 
11b88e0f (David A. Velasco 2012-12-19 14:12:53 +0100  19) import java.io.IOException;
11b88e0f (David A. Velasco 2012-12-19 14:12:53 +0100  20) 
e9f13d77 (David A. Velasco 2013-01-17 16:03:59 +0100  21) import org.apache.commons.httpclient.Credentials;
e9f13d77 (David A. Velasco 2013-01-17 16:03:59 +0100  22) 
2946d8dd (David A. Velasco 2013-04-25 19:39:22 +0200  23) import com.owncloud.android.Log_OC;
69d6d821 (David A. Velasco 2013-03-21 12:21:48 +0100  24) import com.owncloud.android.authentication.AccountAuthenticator;
e9f13d77 (David A. Velasco 2013-01-17 16:03:59 +0100  25) import com.owncloud.android.network.BearerCredentials;
11b88e0f (David A. Velasco 2012-12-19 14:12:53 +0100  26) import com.owncloud.android.network.OwnCloudClientUtils;
e9f13d77 (David A. Velasco 2013-01-17 16:03:59 +0100  27) import com.owncloud.android.operations.RemoteOperationResult.ResultCode;
11b88e0f (David A. Velasco 2012-12-19 14:12:53 +0100  28) 
11b88e0f (David A. Velasco 2012-12-19 14:12:53 +0100  29) import android.accounts.Account;
e9f13d77 (David A. Velasco 2013-01-17 16:03:59 +0100  30) import android.accounts.AccountManager;
11b88e0f (David A. Velasco 2012-12-19 14:12:53 +0100  31) import android.accounts.AccountsException;
73f8797b (David A. Velasco 2013-01-17 13:01:51 +0100  32) import android.app.Activity;
11b88e0f (David A. Velasco 2012-12-19 14:12:53 +0100  33) import android.content.Context;
48f13c8a (David A. Velasco 2012-09-05 13:46:30 +0200  34) import android.os.Handler;
48f13c8a (David A. Velasco 2012-09-05 13:46:30 +0200  35) 
48f13c8a (David A. Velasco 2012-09-05 13:46:30 +0200  36) import eu.alefzero.webdav.WebdavClient;
48f13c8a (David A. Velasco 2012-09-05 13:46:30 +0200  37) 
48f13c8a (David A. Velasco 2012-09-05 13:46:30 +0200  38) /**
48f13c8a (David A. Velasco 2012-09-05 13:46:30 +0200  39)  * Operation which execution involves one or several interactions with an ownCloud server.
48f13c8a (David A. Velasco 2012-09-05 13:46:30 +0200  40)  * 
48f13c8a (David A. Velasco 2012-09-05 13:46:30 +0200  41)  * Provides methods to execute the operation both synchronously or asynchronously.
48f13c8a (David A. Velasco 2012-09-05 13:46:30 +0200  42)  * 
48f13c8a (David A. Velasco 2012-09-05 13:46:30 +0200  43)  * @author David A. Velasco 
48f13c8a (David A. Velasco 2012-09-05 13:46:30 +0200  44)  */
48f13c8a (David A. Velasco 2012-09-05 13:46:30 +0200  45) public abstract class RemoteOperation implements Runnable {
48f13c8a (David A. Velasco 2012-09-05 13:46:30 +0200  46) 	
11b88e0f (David A. Velasco 2012-12-19 14:12:53 +0100  47)     private static final String TAG = RemoteOperation.class.getSimpleName();
11b88e0f (David A. Velasco 2012-12-19 14:12:53 +0100  48) 
11b88e0f (David A. Velasco 2012-12-19 14:12:53 +0100  49)     /** ownCloud account in the remote ownCloud server to operate */
11b88e0f (David A. Velasco 2012-12-19 14:12:53 +0100  50)     private Account mAccount = null;
11b88e0f (David A. Velasco 2012-12-19 14:12:53 +0100  51)     
11b88e0f (David A. Velasco 2012-12-19 14:12:53 +0100  52)     /** Android Application context */
11b88e0f (David A. Velasco 2012-12-19 14:12:53 +0100  53)     private Context mContext = null;
11b88e0f (David A. Velasco 2012-12-19 14:12:53 +0100  54)     
11b88e0f (David A. Velasco 2012-12-19 14:12:53 +0100  55) 	/** Object to interact with the remote server */
48f13c8a (David A. Velasco 2012-09-05 13:46:30 +0200  56) 	private WebdavClient mClient = null;
48f13c8a (David A. Velasco 2012-09-05 13:46:30 +0200  57) 	
48f13c8a (David A. Velasco 2012-09-05 13:46:30 +0200  58) 	/** Callback object to notify about the execution of the remote operation */
48f13c8a (David A. Velasco 2012-09-05 13:46:30 +0200  59) 	private OnRemoteOperationListener mListener = null;
48f13c8a (David A. Velasco 2012-09-05 13:46:30 +0200  60) 	
48f13c8a (David A. Velasco 2012-09-05 13:46:30 +0200  61) 	/** Handler to the thread where mListener methods will be called */
48f13c8a (David A. Velasco 2012-09-05 13:46:30 +0200  62) 	private Handler mListenerHandler = null;
48f13c8a (David A. Velasco 2012-09-05 13:46:30 +0200  63) 
73f8797b (David A. Velasco 2013-01-17 13:01:51 +0100  64) 	/** Activity */
73f8797b (David A. Velasco 2013-01-17 13:01:51 +0100  65)     private Activity mCallerActivity;
73f8797b (David A. Velasco 2013-01-17 13:01:51 +0100  66) 
48f13c8a (David A. Velasco 2012-09-05 13:46:30 +0200  67) 	
48f13c8a (David A. Velasco 2012-09-05 13:46:30 +0200  68) 	/**
48f13c8a (David A. Velasco 2012-09-05 13:46:30 +0200  69) 	 *  Abstract method to implement the operation in derived classes.
48f13c8a (David A. Velasco 2012-09-05 13:46:30 +0200  70) 	 */
48f13c8a (David A. Velasco 2012-09-05 13:46:30 +0200  71) 	protected abstract RemoteOperationResult run(WebdavClient client); 
48f13c8a (David A. Velasco 2012-09-05 13:46:30 +0200  72) 	
11b88e0f (David A. Velasco 2012-12-19 14:12:53 +0100  73) 
11b88e0f (David A. Velasco 2012-12-19 14:12:53 +0100  74)     /**
11b88e0f (David A. Velasco 2012-12-19 14:12:53 +0100  75)      * Synchronously executes the remote operation on the received ownCloud account.
11b88e0f (David A. Velasco 2012-12-19 14:12:53 +0100  76)      * 
11b88e0f (David A. Velasco 2012-12-19 14:12:53 +0100  77)      * Do not call this method from the main thread.
11b88e0f (David A. Velasco 2012-12-19 14:12:53 +0100  78)      * 
11b88e0f (David A. Velasco 2012-12-19 14:12:53 +0100  79)      * This method should be used whenever an ownCloud account is available, instead of {@link #execute(WebdavClient)}. 
11b88e0f (David A. Velasco 2012-12-19 14:12:53 +0100  80)      * 
11b88e0f (David A. Velasco 2012-12-19 14:12:53 +0100  81)      * @param account   ownCloud account in remote ownCloud server to reach during the execution of the operation.
11b88e0f (David A. Velasco 2012-12-19 14:12:53 +0100  82)      * @param context   Android context for the component calling the method.
11b88e0f (David A. Velasco 2012-12-19 14:12:53 +0100  83)      * @return          Result of the operation.
11b88e0f (David A. Velasco 2012-12-19 14:12:53 +0100  84)      */
11b88e0f (David A. Velasco 2012-12-19 14:12:53 +0100  85)     public final RemoteOperationResult execute(Account account, Context context) {
11b88e0f (David A. Velasco 2012-12-19 14:12:53 +0100  86)         if (account == null)
11b88e0f (David A. Velasco 2012-12-19 14:12:53 +0100  87)             throw new IllegalArgumentException("Trying to execute a remote operation with a NULL Account");
11b88e0f (David A. Velasco 2012-12-19 14:12:53 +0100  88)         if (context == null)
11b88e0f (David A. Velasco 2012-12-19 14:12:53 +0100  89)             throw new IllegalArgumentException("Trying to execute a remote operation with a NULL Context");
11b88e0f (David A. Velasco 2012-12-19 14:12:53 +0100  90)         mAccount = account;
11b88e0f (David A. Velasco 2012-12-19 14:12:53 +0100  91)         mContext = context.getApplicationContext();
11b88e0f (David A. Velasco 2012-12-19 14:12:53 +0100  92)         try {
11b88e0f (David A. Velasco 2012-12-19 14:12:53 +0100  93)             mClient = OwnCloudClientUtils.createOwnCloudClient(mAccount, mContext);
11b88e0f (David A. Velasco 2012-12-19 14:12:53 +0100  94)         } catch (Exception e) {
2946d8dd (David A. Velasco 2013-04-25 19:39:22 +0200  95)             Log_OC.e(TAG, "Error while trying to access to " + mAccount.name, e);
11b88e0f (David A. Velasco 2012-12-19 14:12:53 +0100  96)             return new RemoteOperationResult(e);
11b88e0f (David A. Velasco 2012-12-19 14:12:53 +0100  97)         }
11b88e0f (David A. Velasco 2012-12-19 14:12:53 +0100  98)         return run(mClient);
11b88e0f (David A. Velasco 2012-12-19 14:12:53 +0100  99)     }
11b88e0f (David A. Velasco 2012-12-19 14:12:53 +0100 100)     
48f13c8a (David A. Velasco 2012-09-05 13:46:30 +0200 101) 	
48f13c8a (David A. Velasco 2012-09-05 13:46:30 +0200 102) 	/**
48f13c8a (David A. Velasco 2012-09-05 13:46:30 +0200 103) 	 * Synchronously executes the remote operation
48f13c8a (David A. Velasco 2012-09-05 13:46:30 +0200 104) 	 * 
11b88e0f (David A. Velasco 2012-12-19 14:12:53 +0100 105)      * Do not call this method from the main thread.
11b88e0f (David A. Velasco 2012-12-19 14:12:53 +0100 106)      * 
48f13c8a (David A. Velasco 2012-09-05 13:46:30 +0200 107) 	 * @param client	Client object to reach an ownCloud server during the execution of the operation.
48f13c8a (David A. Velasco 2012-09-05 13:46:30 +0200 108) 	 * @return			Result of the operation.
48f13c8a (David A. Velasco 2012-09-05 13:46:30 +0200 109) 	 */
48f13c8a (David A. Velasco 2012-09-05 13:46:30 +0200 110) 	public final RemoteOperationResult execute(WebdavClient client) {
48f13c8a (David A. Velasco 2012-09-05 13:46:30 +0200 111) 		if (client == null)
48f13c8a (David A. Velasco 2012-09-05 13:46:30 +0200 112) 			throw new IllegalArgumentException("Trying to execute a remote operation with a NULL WebdavClient");
48f13c8a (David A. Velasco 2012-09-05 13:46:30 +0200 113) 		mClient = client;
48f13c8a (David A. Velasco 2012-09-05 13:46:30 +0200 114) 		return run(client);
48f13c8a (David A. Velasco 2012-09-05 13:46:30 +0200 115) 	}
48f13c8a (David A. Velasco 2012-09-05 13:46:30 +0200 116) 
48f13c8a (David A. Velasco 2012-09-05 13:46:30 +0200 117) 	
11b88e0f (David A. Velasco 2012-12-19 14:12:53 +0100 118)     /**
11b88e0f (David A. Velasco 2012-12-19 14:12:53 +0100 119)      * Asynchronously executes the remote operation
11b88e0f (David A. Velasco 2012-12-19 14:12:53 +0100 120)      * 
11b88e0f (David A. Velasco 2012-12-19 14:12:53 +0100 121)      * This method should be used whenever an ownCloud account is available, instead of {@link #execute(WebdavClient)}. 
11b88e0f (David A. Velasco 2012-12-19 14:12:53 +0100 122)      * 
11b88e0f (David A. Velasco 2012-12-19 14:12:53 +0100 123)      * @param account           ownCloud account in remote ownCloud server to reach during the execution of the operation.
11b88e0f (David A. Velasco 2012-12-19 14:12:53 +0100 124)      * @param context           Android context for the component calling the method.
11b88e0f (David A. Velasco 2012-12-19 14:12:53 +0100 125)      * @param listener          Listener to be notified about the execution of the operation.
11b88e0f (David A. Velasco 2012-12-19 14:12:53 +0100 126)      * @param listenerHandler   Handler associated to the thread where the methods of the listener objects must be called.
11b88e0f (David A. Velasco 2012-12-19 14:12:53 +0100 127)      * @return                  Thread were the remote operation is executed.
11b88e0f (David A. Velasco 2012-12-19 14:12:53 +0100 128)      */
73f8797b (David A. Velasco 2013-01-17 13:01:51 +0100 129)     public final Thread execute(Account account, Context context, OnRemoteOperationListener listener, Handler listenerHandler, Activity callerActivity) {
11b88e0f (David A. Velasco 2012-12-19 14:12:53 +0100 130)         if (account == null)
11b88e0f (David A. Velasco 2012-12-19 14:12:53 +0100 131)             throw new IllegalArgumentException("Trying to execute a remote operation with a NULL Account");
11b88e0f (David A. Velasco 2012-12-19 14:12:53 +0100 132)         if (context == null)
11b88e0f (David A. Velasco 2012-12-19 14:12:53 +0100 133)             throw new IllegalArgumentException("Trying to execute a remote operation with a NULL Context");
11b88e0f (David A. Velasco 2012-12-19 14:12:53 +0100 134)         mAccount = account;
11b88e0f (David A. Velasco 2012-12-19 14:12:53 +0100 135)         mContext = context.getApplicationContext();
73f8797b (David A. Velasco 2013-01-17 13:01:51 +0100 136)         mCallerActivity = callerActivity;
11b88e0f (David A. Velasco 2012-12-19 14:12:53 +0100 137)         mClient = null;     // the client instance will be created from mAccount and mContext in the runnerThread to create below
11b88e0f (David A. Velasco 2012-12-19 14:12:53 +0100 138)         
11b88e0f (David A. Velasco 2012-12-19 14:12:53 +0100 139)         if (listener == null) {
11b88e0f (David A. Velasco 2012-12-19 14:12:53 +0100 140)             throw new IllegalArgumentException("Trying to execute a remote operation asynchronously without a listener to notiy the result");
11b88e0f (David A. Velasco 2012-12-19 14:12:53 +0100 141)         }
11b88e0f (David A. Velasco 2012-12-19 14:12:53 +0100 142)         mListener = listener;
11b88e0f (David A. Velasco 2012-12-19 14:12:53 +0100 143)         
11b88e0f (David A. Velasco 2012-12-19 14:12:53 +0100 144)         if (listenerHandler == null) {
11b88e0f (David A. Velasco 2012-12-19 14:12:53 +0100 145)             throw new IllegalArgumentException("Trying to execute a remote operation asynchronously without a handler to the listener's thread");
11b88e0f (David A. Velasco 2012-12-19 14:12:53 +0100 146)         }
11b88e0f (David A. Velasco 2012-12-19 14:12:53 +0100 147)         mListenerHandler = listenerHandler;
11b88e0f (David A. Velasco 2012-12-19 14:12:53 +0100 148)         
11b88e0f (David A. Velasco 2012-12-19 14:12:53 +0100 149)         Thread runnerThread = new Thread(this);
11b88e0f (David A. Velasco 2012-12-19 14:12:53 +0100 150)         runnerThread.start();
11b88e0f (David A. Velasco 2012-12-19 14:12:53 +0100 151)         return runnerThread;
11b88e0f (David A. Velasco 2012-12-19 14:12:53 +0100 152)     }
11b88e0f (David A. Velasco 2012-12-19 14:12:53 +0100 153) 
11b88e0f (David A. Velasco 2012-12-19 14:12:53 +0100 154)     
48f13c8a (David A. Velasco 2012-09-05 13:46:30 +0200 155) 	/**
48f13c8a (David A. Velasco 2012-09-05 13:46:30 +0200 156) 	 * Asynchronously executes the remote operation
48f13c8a (David A. Velasco 2012-09-05 13:46:30 +0200 157) 	 * 
48f13c8a (David A. Velasco 2012-09-05 13:46:30 +0200 158) 	 * @param client			Client object to reach an ownCloud server during the execution of the operation.
48f13c8a (David A. Velasco 2012-09-05 13:46:30 +0200 159) 	 * @param listener			Listener to be notified about the execution of the operation.
48f13c8a (David A. Velasco 2012-09-05 13:46:30 +0200 160) 	 * @param listenerHandler	Handler associated to the thread where the methods of the listener objects must be called.
48f13c8a (David A. Velasco 2012-09-05 13:46:30 +0200 161) 	 * @return					Thread were the remote operation is executed.
48f13c8a (David A. Velasco 2012-09-05 13:46:30 +0200 162) 	 */
48f13c8a (David A. Velasco 2012-09-05 13:46:30 +0200 163) 	public final Thread execute(WebdavClient client, OnRemoteOperationListener listener, Handler listenerHandler) {
48f13c8a (David A. Velasco 2012-09-05 13:46:30 +0200 164) 		if (client == null) {
48f13c8a (David A. Velasco 2012-09-05 13:46:30 +0200 165) 			throw new IllegalArgumentException("Trying to execute a remote operation with a NULL WebdavClient");
48f13c8a (David A. Velasco 2012-09-05 13:46:30 +0200 166) 		}
48f13c8a (David A. Velasco 2012-09-05 13:46:30 +0200 167) 		mClient = client;
48f13c8a (David A. Velasco 2012-09-05 13:46:30 +0200 168) 		
48f13c8a (David A. Velasco 2012-09-05 13:46:30 +0200 169) 		if (listener == null) {
48f13c8a (David A. Velasco 2012-09-05 13:46:30 +0200 170) 			throw new IllegalArgumentException("Trying to execute a remote operation asynchronously without a listener to notiy the result");
48f13c8a (David A. Velasco 2012-09-05 13:46:30 +0200 171) 		}
48f13c8a (David A. Velasco 2012-09-05 13:46:30 +0200 172) 		mListener = listener;
48f13c8a (David A. Velasco 2012-09-05 13:46:30 +0200 173) 		
48f13c8a (David A. Velasco 2012-09-05 13:46:30 +0200 174) 		if (listenerHandler == null) {
48f13c8a (David A. Velasco 2012-09-05 13:46:30 +0200 175) 			throw new IllegalArgumentException("Trying to execute a remote operation asynchronously without a handler to the listener's thread");
48f13c8a (David A. Velasco 2012-09-05 13:46:30 +0200 176) 		}
48f13c8a (David A. Velasco 2012-09-05 13:46:30 +0200 177) 		mListenerHandler = listenerHandler;
48f13c8a (David A. Velasco 2012-09-05 13:46:30 +0200 178) 		
48f13c8a (David A. Velasco 2012-09-05 13:46:30 +0200 179) 		Thread runnerThread = new Thread(this);
48f13c8a (David A. Velasco 2012-09-05 13:46:30 +0200 180) 		runnerThread.start();
48f13c8a (David A. Velasco 2012-09-05 13:46:30 +0200 181) 		return runnerThread;
48f13c8a (David A. Velasco 2012-09-05 13:46:30 +0200 182) 	}
48f13c8a (David A. Velasco 2012-09-05 13:46:30 +0200 183) 	
48f13c8a (David A. Velasco 2012-09-05 13:46:30 +0200 184)     /**
48f13c8a (David A. Velasco 2012-09-05 13:46:30 +0200 185)      * Synchronously retries the remote operation using the same WebdavClient in the last call to {@link RemoteOperation#execute(WebdavClient)}
48f13c8a (David A. Velasco 2012-09-05 13:46:30 +0200 186)      * 
48f13c8a (David A. Velasco 2012-09-05 13:46:30 +0200 187)      * @param listener          Listener to be notified about the execution of the operation.
48f13c8a (David A. Velasco 2012-09-05 13:46:30 +0200 188)      * @param listenerHandler   Handler associated to the thread where the methods of the listener objects must be called.
48f13c8a (David A. Velasco 2012-09-05 13:46:30 +0200 189)      * @return                  Thread were the remote operation is executed.
48f13c8a (David A. Velasco 2012-09-05 13:46:30 +0200 190)      */
48f13c8a (David A. Velasco 2012-09-05 13:46:30 +0200 191)     public final RemoteOperationResult retry() {
48f13c8a (David A. Velasco 2012-09-05 13:46:30 +0200 192)         return execute(mClient);
48f13c8a (David A. Velasco 2012-09-05 13:46:30 +0200 193)     }
48f13c8a (David A. Velasco 2012-09-05 13:46:30 +0200 194)     
48f13c8a (David A. Velasco 2012-09-05 13:46:30 +0200 195)     /**
48f13c8a (David A. Velasco 2012-09-05 13:46:30 +0200 196)      * Asynchronously retries the remote operation using the same WebdavClient in the last call to {@link RemoteOperation#execute(WebdavClient, OnRemoteOperationListener, Handler)}
48f13c8a (David A. Velasco 2012-09-05 13:46:30 +0200 197)      * 
48f13c8a (David A. Velasco 2012-09-05 13:46:30 +0200 198)      * @param listener          Listener to be notified about the execution of the operation.
48f13c8a (David A. Velasco 2012-09-05 13:46:30 +0200 199)      * @param listenerHandler   Handler associated to the thread where the methods of the listener objects must be called.
48f13c8a (David A. Velasco 2012-09-05 13:46:30 +0200 200)      * @return                  Thread were the remote operation is executed.
48f13c8a (David A. Velasco 2012-09-05 13:46:30 +0200 201)      */
48f13c8a (David A. Velasco 2012-09-05 13:46:30 +0200 202)     public final Thread retry(OnRemoteOperationListener listener, Handler listenerHandler) {
48f13c8a (David A. Velasco 2012-09-05 13:46:30 +0200 203)         return execute(mClient, listener, listenerHandler);
48f13c8a (David A. Velasco 2012-09-05 13:46:30 +0200 204)     }
48f13c8a (David A. Velasco 2012-09-05 13:46:30 +0200 205) 	
48f13c8a (David A. Velasco 2012-09-05 13:46:30 +0200 206) 	
48f13c8a (David A. Velasco 2012-09-05 13:46:30 +0200 207) 	/**
48f13c8a (David A. Velasco 2012-09-05 13:46:30 +0200 208) 	 * Asynchronous execution of the operation 
48f13c8a (David A. Velasco 2012-09-05 13:46:30 +0200 209) 	 * started by {@link RemoteOperation#execute(WebdavClient, OnRemoteOperationListener, Handler)}, 
48f13c8a (David A. Velasco 2012-09-05 13:46:30 +0200 210) 	 * and result posting.
e9f13d77 (David A. Velasco 2013-01-17 16:03:59 +0100 211) 	 * 
e9f13d77 (David A. Velasco 2013-01-17 16:03:59 +0100 212) 	 * TODO refactor && clean the code; now it's a mess
48f13c8a (David A. Velasco 2012-09-05 13:46:30 +0200 213) 	 */
48f13c8a (David A. Velasco 2012-09-05 13:46:30 +0200 214)     @Override
48f13c8a (David A. Velasco 2012-09-05 13:46:30 +0200 215)     public final void run() {
11b88e0f (David A. Velasco 2012-12-19 14:12:53 +0100 216)         RemoteOperationResult result = null;
e9f13d77 (David A. Velasco 2013-01-17 16:03:59 +0100 217)         boolean repeat = false;
e9f13d77 (David A. Velasco 2013-01-17 16:03:59 +0100 218)         do {
e9f13d77 (David A. Velasco 2013-01-17 16:03:59 +0100 219)             try{
e9f13d77 (David A. Velasco 2013-01-17 16:03:59 +0100 220)                 if (mClient == null) {
e9f13d77 (David A. Velasco 2013-01-17 16:03:59 +0100 221)                     if (mAccount != null && mContext != null) {
e9f13d77 (David A. Velasco 2013-01-17 16:03:59 +0100 222)                         if (mCallerActivity != null) {
e9f13d77 (David A. Velasco 2013-01-17 16:03:59 +0100 223)                             mClient = OwnCloudClientUtils.createOwnCloudClient(mAccount, mContext, mCallerActivity);
e9f13d77 (David A. Velasco 2013-01-17 16:03:59 +0100 224)                         } else {
e9f13d77 (David A. Velasco 2013-01-17 16:03:59 +0100 225)                             mClient = OwnCloudClientUtils.createOwnCloudClient(mAccount, mContext);
e9f13d77 (David A. Velasco 2013-01-17 16:03:59 +0100 226)                         }
73f8797b (David A. Velasco 2013-01-17 13:01:51 +0100 227)                     } else {
e9f13d77 (David A. Velasco 2013-01-17 16:03:59 +0100 228)                         throw new IllegalStateException("Trying to run a remote operation asynchronously with no client instance or account");
73f8797b (David A. Velasco 2013-01-17 13:01:51 +0100 229)                     }
11b88e0f (David A. Velasco 2012-12-19 14:12:53 +0100 230)                 }
11b88e0f (David A. Velasco 2012-12-19 14:12:53 +0100 231)             
e9f13d77 (David A. Velasco 2013-01-17 16:03:59 +0100 232)             } catch (IOException e) {
2946d8dd (David A. Velasco 2013-04-25 19:39:22 +0200 233)                 Log_OC.e(TAG, "Error while trying to access to " + mAccount.name, new AccountsException("I/O exception while trying to authorize the account", e));
e9f13d77 (David A. Velasco 2013-01-17 16:03:59 +0100 234)                 result = new RemoteOperationResult(e);
11b88e0f (David A. Velasco 2012-12-19 14:12:53 +0100 235)             
e9f13d77 (David A. Velasco 2013-01-17 16:03:59 +0100 236)             } catch (AccountsException e) {
2946d8dd (David A. Velasco 2013-04-25 19:39:22 +0200 237)                 Log_OC.e(TAG, "Error while trying to access to " + mAccount.name, e);
e9f13d77 (David A. Velasco 2013-01-17 16:03:59 +0100 238)                 result = new RemoteOperationResult(e);
e9f13d77 (David A. Velasco 2013-01-17 16:03:59 +0100 239)             }
48f13c8a (David A. Velasco 2012-09-05 13:46:30 +0200 240)     	
e9f13d77 (David A. Velasco 2013-01-17 16:03:59 +0100 241)             if (result == null)
e9f13d77 (David A. Velasco 2013-01-17 16:03:59 +0100 242)                 result = run(mClient);
e9f13d77 (David A. Velasco 2013-01-17 16:03:59 +0100 243)         
e9f13d77 (David A. Velasco 2013-01-17 16:03:59 +0100 244)             repeat = false;
78bcf721 (David A. Velasco 2013-08-07 19:13:00 +0200 245)             if (mCallerActivity != null && mAccount != null && mContext != null && !result.isSuccess() &&
4047c625 (masensio         2013-08-27 12:32:52 +0200 246) //                    (result.getCode() == ResultCode.UNAUTHORIZED || (result.isTemporalRedirection() && result.isIdPRedirection()))) {
4047c625 (masensio         2013-08-27 12:32:52 +0200 247)                     (result.getCode() == ResultCode.UNAUTHORIZED || result.isIdPRedirection())) {
78bcf721 (David A. Velasco 2013-08-07 19:13:00 +0200 248)                 /// possible fail due to lack of authorization in an operation performed in foreground
e9f13d77 (David A. Velasco 2013-01-17 16:03:59 +0100 249)                 Credentials cred = mClient.getCredentials();
78bcf721 (David A. Velasco 2013-08-07 19:13:00 +0200 250)                 String ssoSessionCookie = mClient.getSsoSessionCookie();
78bcf721 (David A. Velasco 2013-08-07 19:13:00 +0200 251)                 if (cred != null || ssoSessionCookie != null) {
78bcf721 (David A. Velasco 2013-08-07 19:13:00 +0200 252)                     /// confirmed : unauthorized operation
78bcf721 (David A. Velasco 2013-08-07 19:13:00 +0200 253)                     AccountManager am = AccountManager.get(mContext);
78bcf721 (David A. Velasco 2013-08-07 19:13:00 +0200 254)                     boolean bearerAuthorization = (cred != null && cred instanceof BearerCredentials);
78bcf721 (David A. Velasco 2013-08-07 19:13:00 +0200 255)                     boolean samlBasedSsoAuthorization = (cred == null && ssoSessionCookie != null);
78bcf721 (David A. Velasco 2013-08-07 19:13:00 +0200 256)                     if (bearerAuthorization) {
78bcf721 (David A. Velasco 2013-08-07 19:13:00 +0200 257)                         am.invalidateAuthToken(AccountAuthenticator.ACCOUNT_TYPE, ((BearerCredentials)cred).getAccessToken());
78bcf721 (David A. Velasco 2013-08-07 19:13:00 +0200 258)                     } else if (samlBasedSsoAuthorization ) {
78bcf721 (David A. Velasco 2013-08-07 19:13:00 +0200 259)                         am.invalidateAuthToken(AccountAuthenticator.ACCOUNT_TYPE, ssoSessionCookie);
78bcf721 (David A. Velasco 2013-08-07 19:13:00 +0200 260)                     } else {
78bcf721 (David A. Velasco 2013-08-07 19:13:00 +0200 261)                         am.clearPassword(mAccount);
78bcf721 (David A. Velasco 2013-08-07 19:13:00 +0200 262)                     }
78bcf721 (David A. Velasco 2013-08-07 19:13:00 +0200 263)                     mClient = null;
78bcf721 (David A. Velasco 2013-08-07 19:13:00 +0200 264)                     repeat = true;  // when repeated, the creation of a new OwnCloudClient after erasing the saved credentials will trigger the login activity
78bcf721 (David A. Velasco 2013-08-07 19:13:00 +0200 265)                     result = null;
e9f13d77 (David A. Velasco 2013-01-17 16:03:59 +0100 266)                 }
e9f13d77 (David A. Velasco 2013-01-17 16:03:59 +0100 267)             }
e9f13d77 (David A. Velasco 2013-01-17 16:03:59 +0100 268)         } while (repeat);
73f8797b (David A. Velasco 2013-01-17 13:01:51 +0100 269)         
11b88e0f (David A. Velasco 2012-12-19 14:12:53 +0100 270)         final RemoteOperationResult resultToSend = result;
48f13c8a (David A. Velasco 2012-09-05 13:46:30 +0200 271)         if (mListenerHandler != null && mListener != null) {
48f13c8a (David A. Velasco 2012-09-05 13:46:30 +0200 272)         	mListenerHandler.post(new Runnable() {
48f13c8a (David A. Velasco 2012-09-05 13:46:30 +0200 273)                 @Override
48f13c8a (David A. Velasco 2012-09-05 13:46:30 +0200 274)                 public void run() {
11b88e0f (David A. Velasco 2012-12-19 14:12:53 +0100 275)                     mListener.onRemoteOperationFinish(RemoteOperation.this, resultToSend);
48f13c8a (David A. Velasco 2012-09-05 13:46:30 +0200 276)                 }
48f13c8a (David A. Velasco 2012-09-05 13:46:30 +0200 277)             });
48f13c8a (David A. Velasco 2012-09-05 13:46:30 +0200 278)         }
48f13c8a (David A. Velasco 2012-09-05 13:46:30 +0200 279)     }
73f8797b (David A. Velasco 2013-01-17 13:01:51 +0100 280) 
73f8797b (David A. Velasco 2013-01-17 13:01:51 +0100 281) 
73f8797b (David A. Velasco 2013-01-17 13:01:51 +0100 282)     /**
73f8797b (David A. Velasco 2013-01-17 13:01:51 +0100 283)      * Returns the current client instance to access the remote server.
73f8797b (David A. Velasco 2013-01-17 13:01:51 +0100 284)      * 
73f8797b (David A. Velasco 2013-01-17 13:01:51 +0100 285)      * @return      Current client instance to access the remote server.
73f8797b (David A. Velasco 2013-01-17 13:01:51 +0100 286)      */
73f8797b (David A. Velasco 2013-01-17 13:01:51 +0100 287)     public final WebdavClient getClient() {
73f8797b (David A. Velasco 2013-01-17 13:01:51 +0100 288)         return mClient;
73f8797b (David A. Velasco 2013-01-17 13:01:51 +0100 289)     }
73f8797b (David A. Velasco 2013-01-17 13:01:51 +0100 290) 
d2ee9062 (David A. Velasco 2013-07-30 13:02:13 +0200 291) 
48f13c8a (David A. Velasco 2012-09-05 13:46:30 +0200 292) }
