8e36e7cc (zerginator       2013-03-12 07:56:27 +0100   1) /* ownCloud Android client application
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100   2)  *   Copyright (C) 2011  Bartek Przybylski
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100   3)  *   Copyright (C) 2012-2013 ownCloud Inc.
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100   4)  *
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100   5)  *   This program is free software: you can redistribute it and/or modify
bdc0332c (David A. Velasco 2013-04-17 12:26:13 +0200   6)  *   it under the terms of the GNU General Public License version 2,
bdc0332c (David A. Velasco 2013-04-17 12:26:13 +0200   7)  *   as published by the Free Software Foundation.
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100   8)  *
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100   9)  *   This program is distributed in the hope that it will be useful,
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100  10)  *   but WITHOUT ANY WARRANTY; without even the implied warranty of
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100  11)  *   MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100  12)  *   GNU General Public License for more details.
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100  13)  *
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100  14)  *   You should have received a copy of the GNU General Public License
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100  15)  *   along with this program.  If not, see <http://www.gnu.org/licenses/>.
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100  16)  *
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100  17)  */
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100  18) 
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100  19) package com.owncloud.android.syncadapter;
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100  20) 
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100  21) import java.io.IOException;
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100  22) import java.util.ArrayList;
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100  23) import java.util.HashMap;
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100  24) import java.util.List;
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100  25) import java.util.Map;
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100  26) 
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100  27) import org.apache.jackrabbit.webdav.DavException;
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100  28) 
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100  29) import com.owncloud.android.Log_OC;
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100  30) import com.owncloud.android.R;
68df3cbf (David A. Velasco 2013-08-12 11:34:09 +0200  31) import com.owncloud.android.authentication.AccountAuthenticator;
2946d8dd (David A. Velasco 2013-04-25 19:39:22 +0200  32) import com.owncloud.android.authentication.AuthenticatorActivity;
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100  33) import com.owncloud.android.datamodel.DataStorageManager;
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100  34) import com.owncloud.android.datamodel.FileDataStorageManager;
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100  35) import com.owncloud.android.datamodel.OCFile;
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100  36) import com.owncloud.android.operations.RemoteOperationResult;
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100  37) import com.owncloud.android.operations.SynchronizeFolderOperation;
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100  38) import com.owncloud.android.operations.UpdateOCVersionOperation;
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100  39) import com.owncloud.android.operations.RemoteOperationResult.ResultCode;
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100  40) import com.owncloud.android.ui.activity.ErrorsWhileCopyingHandlerActivity;
eee74aa5 (masensio         2013-07-04 18:47:38 +0200  41) import com.owncloud.android.utils.FileStorageUtils;
eee74aa5 (masensio         2013-07-04 18:47:38 +0200  42) 
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100  43) import android.accounts.Account;
2946d8dd (David A. Velasco 2013-04-25 19:39:22 +0200  44) import android.accounts.AccountsException;
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100  45) import android.app.Notification;
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100  46) import android.app.NotificationManager;
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100  47) import android.app.PendingIntent;
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100  48) import android.content.ContentProviderClient;
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100  49) import android.content.ContentResolver;
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100  50) import android.content.Context;
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100  51) import android.content.Intent;
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100  52) import android.content.SyncResult;
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100  53) import android.os.Bundle;
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100  54) 
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100  55) /**
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100  56)  * SyncAdapter implementation for syncing sample SyncAdapter contacts to the
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100  57)  * platform ContactOperations provider.
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100  58)  * 
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100  59)  * @author Bartek Przybylski
2946d8dd (David A. Velasco 2013-04-25 19:39:22 +0200  60)  * @author David A. Velasco
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100  61)  */
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100  62) public class FileSyncAdapter extends AbstractOwnCloudSyncAdapter {
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100  63) 
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100  64)     private final static String TAG = "FileSyncAdapter";
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100  65) 
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100  66)     /** 
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100  67)      * Maximum number of failed folder synchronizations that are supported before finishing the synchronization operation
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100  68)      */
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100  69)     private static final int MAX_FAILED_RESULTS = 3; 
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100  70)     
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100  71)     private long mCurrentSyncTime;
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100  72)     private boolean mCancellation;
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100  73)     private boolean mIsManualSync;
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100  74)     private int mFailedResultsCounter;    
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100  75)     private RemoteOperationResult mLastFailedResult;
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100  76)     private SyncResult mSyncResult;
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100  77)     private int mConflictsFound;
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100  78)     private int mFailsInFavouritesFound;
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100  79)     private Map<String, String> mForgottenLocalFiles;
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100  80) 
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100  81)     
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100  82)     public FileSyncAdapter(Context context, boolean autoInitialize) {
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100  83)         super(context, autoInitialize);
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100  84)     }
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100  85) 
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100  86)     /**
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100  87)      * {@inheritDoc}
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100  88)      */
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100  89)     @Override
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100  90)     public synchronized void onPerformSync(Account account, Bundle extras,
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100  91)             String authority, ContentProviderClient provider,
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100  92)             SyncResult syncResult) {
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100  93) 
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100  94)         mCancellation = false;
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100  95)         mIsManualSync = extras.getBoolean(ContentResolver.SYNC_EXTRAS_MANUAL, false);
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100  96)         mFailedResultsCounter = 0;
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100  97)         mLastFailedResult = null;
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100  98)         mConflictsFound = 0;
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100  99)         mFailsInFavouritesFound = 0;
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100 100)         mForgottenLocalFiles = new HashMap<String, String>();
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100 101)         mSyncResult = syncResult;
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100 102)         mSyncResult.fullSyncRequested = false;
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100 103)         mSyncResult.delayUntil = 60*60*24; // sync after 24h
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100 104) 
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100 105)         this.setAccount(account);
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100 106)         this.setContentProvider(provider);
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100 107)         this.setStorageManager(new FileDataStorageManager(account, getContentProvider()));
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100 108)         try {
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100 109)             this.initClientForCurrentAccount();
2946d8dd (David A. Velasco 2013-04-25 19:39:22 +0200 110)         } catch (IOException e) {
2946d8dd (David A. Velasco 2013-04-25 19:39:22 +0200 111)             /// the account is unknown for the Synchronization Manager, or unreachable for this context; don't try this again
2946d8dd (David A. Velasco 2013-04-25 19:39:22 +0200 112)             mSyncResult.tooManyRetries = true;
2946d8dd (David A. Velasco 2013-04-25 19:39:22 +0200 113)             notifyFailedSynchronization();
2946d8dd (David A. Velasco 2013-04-25 19:39:22 +0200 114)             return;
2946d8dd (David A. Velasco 2013-04-25 19:39:22 +0200 115)         } catch (AccountsException e) {
2946d8dd (David A. Velasco 2013-04-25 19:39:22 +0200 116)             /// the account is unknown for the Synchronization Manager, or unreachable for this context; don't try this again
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100 117)             mSyncResult.tooManyRetries = true;
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100 118)             notifyFailedSynchronization();
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100 119)             return;
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100 120)         }
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100 121)         
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100 122)         Log_OC.d(TAG, "Synchronization of ownCloud account " + account.name + " starting");
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100 123)         sendStickyBroadcast(true, null, null);  // message to signal the start of the synchronization to the UI
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100 124)         
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100 125)         try {
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100 126)             updateOCVersion();
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100 127)             mCurrentSyncTime = System.currentTimeMillis();
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100 128)             if (!mCancellation) {
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100 129)                 fetchData(OCFile.PATH_SEPARATOR, DataStorageManager.ROOT_PARENT_ID);
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100 130)                 
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100 131)             } else {
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100 132)                 Log_OC.d(TAG, "Leaving synchronization before any remote request due to cancellation was requested");
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100 133)             }
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100 134)             
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100 135)             
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100 136)         } finally {
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100 137)             // it's important making this although very unexpected errors occur; that's the reason for the finally
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100 138)             
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100 139)             if (mFailedResultsCounter > 0 && mIsManualSync) {
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100 140)                 /// don't let the system synchronization manager retries MANUAL synchronizations
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100 141)                 //      (be careful: "MANUAL" currently includes the synchronization requested when a new account is created and when the user changes the current account)
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100 142)                 mSyncResult.tooManyRetries = true;
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100 143)                 
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100 144)                 /// notify the user about the failure of MANUAL synchronization
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100 145)                 notifyFailedSynchronization();
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100 146)                 
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100 147)             }
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100 148)             if (mConflictsFound > 0 || mFailsInFavouritesFound > 0) {
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100 149)                 notifyFailsInFavourites();
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100 150)             }
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100 151)             if (mForgottenLocalFiles.size() > 0) {
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100 152)                 notifyForgottenLocalFiles();
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100 153)                 
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100 154)             }
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100 155)             sendStickyBroadcast(false, null, mLastFailedResult);        // message to signal the end to the UI
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100 156)         }
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100 157)         
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100 158)     }
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100 159)     
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100 160)     /**
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100 161)      * Called by system SyncManager when a synchronization is required to be cancelled.
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100 162)      * 
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100 163)      * Sets the mCancellation flag to 'true'. THe synchronization will be stopped when before a new folder is fetched. Data of the last folder
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100 164)      * fetched will be still saved in the database. See onPerformSync implementation.
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100 165)      */
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100 166)     @Override
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100 167)     public void onSyncCanceled() {
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100 168)         Log_OC.d(TAG, "Synchronization of " + getAccount().name + " has been requested to cancel");
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100 169)         mCancellation = true;
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100 170)         super.onSyncCanceled();
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100 171)     }
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100 172)     
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100 173)     
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100 174)     /**
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100 175)      * Updates the locally stored version value of the ownCloud server
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100 176)      */
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100 177)     private void updateOCVersion() {
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100 178)         UpdateOCVersionOperation update = new UpdateOCVersionOperation(getAccount(), getContext());
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100 179)         RemoteOperationResult result = update.execute(getClient());
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100 180)         if (!result.isSuccess()) {
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100 181)             mLastFailedResult = result; 
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100 182)         }
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100 183)     }
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100 184)     
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100 185)     
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100 186)     /**
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100 187)      * Synchronize the properties of files and folders contained in a remote folder given by remotePath.
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100 188)      * 
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100 189)      * @param remotePath        Remote path to the folder to synchronize.
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100 190)      * @param parentId          Database Id of the folder to synchronize.
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100 191)      */
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100 192)     private void fetchData(String remotePath, long parentId) {
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100 193)         
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100 194)         if (mFailedResultsCounter > MAX_FAILED_RESULTS || isFinisher(mLastFailedResult))
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100 195)             return;
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100 196)         
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100 197)         // perform folder synchronization
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100 198)         SynchronizeFolderOperation synchFolderOp = new SynchronizeFolderOperation(  remotePath, 
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100 199)                                                                                     mCurrentSyncTime, 
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100 200)                                                                                     parentId, 
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100 201)                                                                                     getStorageManager(), 
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100 202)                                                                                     getAccount(), 
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100 203)                                                                                     getContext()
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100 204)                                                                                   );
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100 205)         RemoteOperationResult result = synchFolderOp.execute(getClient());
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100 206)         
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100 207)         
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100 208)         // synchronized folder -> notice to UI - ALWAYS, although !result.isSuccess
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100 209)         sendStickyBroadcast(true, remotePath, null);
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100 210)         
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100 211)         if (result.isSuccess() || result.getCode() == ResultCode.SYNC_CONFLICT) {
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100 212)             
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100 213)             if (result.getCode() == ResultCode.SYNC_CONFLICT) {
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100 214)                 mConflictsFound += synchFolderOp.getConflictsFound();
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100 215)                 mFailsInFavouritesFound += synchFolderOp.getFailsInFavouritesFound();
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100 216)             }
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100 217)             if (synchFolderOp.getForgottenLocalFiles().size() > 0) {
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100 218)                 mForgottenLocalFiles.putAll(synchFolderOp.getForgottenLocalFiles());
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100 219)             }
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100 220)             // synchronize children folders 
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100 221)             List<OCFile> children = synchFolderOp.getChildren();
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100 222)             fetchChildren(children);    // beware of the 'hidden' recursion here!
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100 223)             
5f0dfa61 (masensio         2013-07-11 18:57:35 +0200 224)             sendStickyBroadcast(true, remotePath, null);
5f0dfa61 (masensio         2013-07-11 18:57:35 +0200 225)             
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100 226)         } else {
68df3cbf (David A. Velasco 2013-08-12 11:34:09 +0200 227)             if (result.getCode() == RemoteOperationResult.ResultCode.UNAUTHORIZED ||
4047c625 (masensio         2013-08-27 12:32:52 +0200 228)                    // (result.isTemporalRedirection() && result.isIdPRedirection() &&
4047c625 (masensio         2013-08-27 12:32:52 +0200 229)                     ( result.isIdPRedirection() && 
2dc5cc57 (David A. Velasco 2013-08-22 19:25:15 +0200 230)                             AccountAuthenticator.AUTH_TOKEN_TYPE_SAML_WEB_SSO_SESSION_COOKIE.equals(getClient().getAuthTokenType()))) {
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100 231)                 mSyncResult.stats.numAuthExceptions++;
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100 232)                 
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100 233)             } else if (result.getException() instanceof DavException) {
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100 234)                 mSyncResult.stats.numParseExceptions++;
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100 235)                 
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100 236)             } else if (result.getException() instanceof IOException) { 
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100 237)                 mSyncResult.stats.numIoExceptions++;
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100 238)             }
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100 239)             mFailedResultsCounter++;
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100 240)             mLastFailedResult = result;
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100 241)         }
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100 242)             
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100 243)     }
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100 244) 
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100 245)     /**
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100 246)      * Checks if a failed result should terminate the synchronization process immediately, according to
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100 247)      * OUR OWN POLICY
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100 248)      * 
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100 249)      * @param   failedResult        Remote operation result to check.
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100 250)      * @return                      'True' if the result should immediately finish the synchronization
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100 251)      */
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100 252)     private boolean isFinisher(RemoteOperationResult failedResult) {
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100 253)         if  (failedResult != null) {
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100 254)             RemoteOperationResult.ResultCode code = failedResult.getCode();
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100 255)             return (code.equals(RemoteOperationResult.ResultCode.SSL_ERROR) ||
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100 256)                     code.equals(RemoteOperationResult.ResultCode.SSL_RECOVERABLE_PEER_UNVERIFIED) ||
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100 257)                     code.equals(RemoteOperationResult.ResultCode.BAD_OC_VERSION) ||
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100 258)                     code.equals(RemoteOperationResult.ResultCode.INSTANCE_NOT_CONFIGURED));
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100 259)         }
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100 260)         return false;
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100 261)     }
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100 262) 
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100 263)     /**
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100 264)      * Synchronize data of folders in the list of received files
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100 265)      * 
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100 266)      * @param files         Files to recursively fetch 
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100 267)      */
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100 268)     private void fetchChildren(List<OCFile> files) {
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100 269)         int i;
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100 270)         for (i=0; i < files.size() && !mCancellation; i++) {
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100 271)             OCFile newFile = files.get(i);
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100 272)             if (newFile.isDirectory()) {
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100 273)                 fetchData(newFile.getRemotePath(), newFile.getFileId());
eee74aa5 (masensio         2013-07-04 18:47:38 +0200 274)                 
eee74aa5 (masensio         2013-07-04 18:47:38 +0200 275)                 // Update folder size on DB
5f0dfa61 (masensio         2013-07-11 18:57:35 +0200 276)                 getStorageManager().calculateFolderSize(newFile.getFileId());                   
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100 277)             }
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100 278)         }
eee74aa5 (masensio         2013-07-04 18:47:38 +0200 279)        
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100 280)         if (mCancellation && i <files.size()) Log_OC.d(TAG, "Leaving synchronization before synchronizing " + files.get(i).getRemotePath() + " because cancelation request");
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100 281)     }
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100 282) 
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100 283)     
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100 284)     /**
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100 285)      * Sends a message to any application component interested in the progress of the synchronization.
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100 286)      * 
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100 287)      * @param inProgress        'True' when the synchronization progress is not finished.
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100 288)      * @param dirRemotePath     Remote path of a folder that was just synchronized (with or without success)
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100 289)      */
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100 290)     private void sendStickyBroadcast(boolean inProgress, String dirRemotePath, RemoteOperationResult result) {
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100 291)         Intent i = new Intent(FileSyncService.SYNC_MESSAGE);
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100 292)         i.putExtra(FileSyncService.IN_PROGRESS, inProgress);
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100 293)         i.putExtra(FileSyncService.ACCOUNT_NAME, getAccount().name);
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100 294)         if (dirRemotePath != null) {
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100 295)             i.putExtra(FileSyncService.SYNC_FOLDER_REMOTE_PATH, dirRemotePath);
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100 296)         }
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100 297)         if (result != null) {
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100 298)             i.putExtra(FileSyncService.SYNC_RESULT, result);
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100 299)         }
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100 300)         getContext().sendStickyBroadcast(i);
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100 301)     }
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100 302) 
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100 303)     
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100 304)     
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100 305)     /**
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100 306)      * Notifies the user about a failed synchronization through the status notification bar 
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100 307)      */
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100 308)     private void notifyFailedSynchronization() {
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100 309)         Notification notification = new Notification(R.drawable.icon, getContext().getString(R.string.sync_fail_ticker), System.currentTimeMillis());
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100 310)         notification.flags |= Notification.FLAG_AUTO_CANCEL;
68df3cbf (David A. Velasco 2013-08-12 11:34:09 +0200 311)         boolean needsToUpdateCredentials = (mLastFailedResult != null && 
68df3cbf (David A. Velasco 2013-08-12 11:34:09 +0200 312)                                              (  mLastFailedResult.getCode() == ResultCode.UNAUTHORIZED ||
4047c625 (masensio         2013-08-27 12:32:52 +0200 313)                                                 // (mLastFailedResult.isTemporalRedirection() && mLastFailedResult.isIdPRedirection() && 
4047c625 (masensio         2013-08-27 12:32:52 +0200 314)                                                 ( mLastFailedResult.isIdPRedirection() && 
2dc5cc57 (David A. Velasco 2013-08-22 19:25:15 +0200 315)                                                  AccountAuthenticator.AUTH_TOKEN_TYPE_SAML_WEB_SSO_SESSION_COOKIE.equals(getClient().getAuthTokenType()))
68df3cbf (David A. Velasco 2013-08-12 11:34:09 +0200 316)                                              )
68df3cbf (David A. Velasco 2013-08-12 11:34:09 +0200 317)                                            );
2946d8dd (David A. Velasco 2013-04-25 19:39:22 +0200 318)         // TODO put something smart in the contentIntent below for all the possible errors
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100 319)         notification.contentIntent = PendingIntent.getActivity(getContext().getApplicationContext(), (int)System.currentTimeMillis(), new Intent(), 0);
2946d8dd (David A. Velasco 2013-04-25 19:39:22 +0200 320)         if (needsToUpdateCredentials) {
2946d8dd (David A. Velasco 2013-04-25 19:39:22 +0200 321)             // let the user update credentials with one click
2946d8dd (David A. Velasco 2013-04-25 19:39:22 +0200 322)             Intent updateAccountCredentials = new Intent(getContext(), AuthenticatorActivity.class);
2946d8dd (David A. Velasco 2013-04-25 19:39:22 +0200 323)             updateAccountCredentials.putExtra(AuthenticatorActivity.EXTRA_ACCOUNT, getAccount());
8f1566a2 (David A. Velasco 2013-06-21 14:16:45 +0200 324)             updateAccountCredentials.putExtra(AuthenticatorActivity.EXTRA_ENFORCED_UPDATE, true);
2946d8dd (David A. Velasco 2013-04-25 19:39:22 +0200 325)             updateAccountCredentials.putExtra(AuthenticatorActivity.EXTRA_ACTION, AuthenticatorActivity.ACTION_UPDATE_TOKEN);
2946d8dd (David A. Velasco 2013-04-25 19:39:22 +0200 326)             updateAccountCredentials.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
2946d8dd (David A. Velasco 2013-04-25 19:39:22 +0200 327)             updateAccountCredentials.addFlags(Intent.FLAG_ACTIVITY_EXCLUDE_FROM_RECENTS);
2946d8dd (David A. Velasco 2013-04-25 19:39:22 +0200 328)             updateAccountCredentials.addFlags(Intent.FLAG_FROM_BACKGROUND);
2946d8dd (David A. Velasco 2013-04-25 19:39:22 +0200 329)             notification.contentIntent = PendingIntent.getActivity(getContext(), (int)System.currentTimeMillis(), updateAccountCredentials, PendingIntent.FLAG_ONE_SHOT);
2946d8dd (David A. Velasco 2013-04-25 19:39:22 +0200 330)             notification.setLatestEventInfo(getContext().getApplicationContext(), 
2946d8dd (David A. Velasco 2013-04-25 19:39:22 +0200 331)                     getContext().getString(R.string.sync_fail_ticker), 
2946d8dd (David A. Velasco 2013-04-25 19:39:22 +0200 332)                     String.format(getContext().getString(R.string.sync_fail_content_unauthorized), getAccount().name), 
2946d8dd (David A. Velasco 2013-04-25 19:39:22 +0200 333)                     notification.contentIntent);
2946d8dd (David A. Velasco 2013-04-25 19:39:22 +0200 334)         } else {
2946d8dd (David A. Velasco 2013-04-25 19:39:22 +0200 335)             notification.setLatestEventInfo(getContext().getApplicationContext(), 
2946d8dd (David A. Velasco 2013-04-25 19:39:22 +0200 336)                                             getContext().getString(R.string.sync_fail_ticker), 
2946d8dd (David A. Velasco 2013-04-25 19:39:22 +0200 337)                                             String.format(getContext().getString(R.string.sync_fail_content), getAccount().name), 
2946d8dd (David A. Velasco 2013-04-25 19:39:22 +0200 338)                                             notification.contentIntent);
2946d8dd (David A. Velasco 2013-04-25 19:39:22 +0200 339)         }
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100 340)         ((NotificationManager) getContext().getSystemService(Context.NOTIFICATION_SERVICE)).notify(R.string.sync_fail_ticker, notification);
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100 341)     }
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100 342) 
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100 343) 
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100 344)     /**
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100 345)      * Notifies the user about conflicts and strange fails when trying to synchronize the contents of kept-in-sync files.
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100 346)      * 
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100 347)      * By now, we won't consider a failed synchronization.
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100 348)      */
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100 349)     private void notifyFailsInFavourites() {
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100 350)         if (mFailedResultsCounter > 0) {
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100 351)             Notification notification = new Notification(R.drawable.icon, getContext().getString(R.string.sync_fail_in_favourites_ticker), System.currentTimeMillis());
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100 352)             notification.flags |= Notification.FLAG_AUTO_CANCEL;
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100 353)             // TODO put something smart in the contentIntent below
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100 354)             notification.contentIntent = PendingIntent.getActivity(getContext().getApplicationContext(), (int)System.currentTimeMillis(), new Intent(), 0);
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100 355)             notification.setLatestEventInfo(getContext().getApplicationContext(), 
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100 356)                                             getContext().getString(R.string.sync_fail_in_favourites_ticker), 
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100 357)                                             String.format(getContext().getString(R.string.sync_fail_in_favourites_content), mFailedResultsCounter + mConflictsFound, mConflictsFound), 
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100 358)                                             notification.contentIntent);
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100 359)             ((NotificationManager) getContext().getSystemService(Context.NOTIFICATION_SERVICE)).notify(R.string.sync_fail_in_favourites_ticker, notification);
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100 360)             
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100 361)         } else {
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100 362)             Notification notification = new Notification(R.drawable.icon, getContext().getString(R.string.sync_conflicts_in_favourites_ticker), System.currentTimeMillis());
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100 363)             notification.flags |= Notification.FLAG_AUTO_CANCEL;
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100 364)             // TODO put something smart in the contentIntent below
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100 365)             notification.contentIntent = PendingIntent.getActivity(getContext().getApplicationContext(), (int)System.currentTimeMillis(), new Intent(), 0);
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100 366)             notification.setLatestEventInfo(getContext().getApplicationContext(), 
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100 367)                                             getContext().getString(R.string.sync_conflicts_in_favourites_ticker), 
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100 368)                                             String.format(getContext().getString(R.string.sync_conflicts_in_favourites_content), mConflictsFound), 
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100 369)                                             notification.contentIntent);
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100 370)             ((NotificationManager) getContext().getSystemService(Context.NOTIFICATION_SERVICE)).notify(R.string.sync_conflicts_in_favourites_ticker, notification);
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100 371)         } 
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100 372)     }
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100 373)     
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100 374)     /**
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100 375)      * Notifies the user about local copies of files out of the ownCloud local directory that were 'forgotten' because 
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100 376)      * copying them inside the ownCloud local directory was not possible.
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100 377)      * 
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100 378)      * We don't want links to files out of the ownCloud local directory (foreign files) anymore. It's easy to have 
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100 379)      * synchronization problems if a local file is linked to more than one remote file.
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100 380)      * 
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100 381)      * We won't consider a synchronization as failed when foreign files can not be copied to the ownCloud local directory.
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100 382)      */
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100 383)     private void notifyForgottenLocalFiles() {
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100 384)         Notification notification = new Notification(R.drawable.icon, getContext().getString(R.string.sync_foreign_files_forgotten_ticker), System.currentTimeMillis());
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100 385)         notification.flags |= Notification.FLAG_AUTO_CANCEL;
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100 386) 
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100 387)         /// includes a pending intent in the notification showing a more detailed explanation
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100 388)         Intent explanationIntent = new Intent(getContext(), ErrorsWhileCopyingHandlerActivity.class);
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100 389)         explanationIntent.putExtra(ErrorsWhileCopyingHandlerActivity.EXTRA_ACCOUNT, getAccount());
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100 390)         ArrayList<String> remotePaths = new ArrayList<String>();
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100 391)         ArrayList<String> localPaths = new ArrayList<String>();
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100 392)         remotePaths.addAll(mForgottenLocalFiles.keySet());
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100 393)         localPaths.addAll(mForgottenLocalFiles.values());
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100 394)         explanationIntent.putExtra(ErrorsWhileCopyingHandlerActivity.EXTRA_LOCAL_PATHS, localPaths);
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100 395)         explanationIntent.putExtra(ErrorsWhileCopyingHandlerActivity.EXTRA_REMOTE_PATHS, remotePaths);  
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100 396)         explanationIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100 397)         
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100 398)         notification.contentIntent = PendingIntent.getActivity(getContext().getApplicationContext(), (int)System.currentTimeMillis(), explanationIntent, 0);
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100 399)         notification.setLatestEventInfo(getContext().getApplicationContext(), 
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100 400)                                         getContext().getString(R.string.sync_foreign_files_forgotten_ticker), 
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100 401)                                         String.format(getContext().getString(R.string.sync_foreign_files_forgotten_content), mForgottenLocalFiles.size(), getContext().getString(R.string.app_name)), 
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100 402)                                         notification.contentIntent);
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100 403)         ((NotificationManager) getContext().getSystemService(Context.NOTIFICATION_SERVICE)).notify(R.string.sync_foreign_files_forgotten_ticker, notification);
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100 404)         
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100 405)     }
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100 406)     
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100 407)     
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100 408) }
