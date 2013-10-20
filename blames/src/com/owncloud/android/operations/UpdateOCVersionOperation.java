d25af7ee (David A. Velasco 2012-10-24 19:39:55 +0200   1) /* ownCloud Android client application
bb257ec7 (David A. Velasco 2013-02-07 18:45:10 +0100   2)  *   Copyright (C) 2012-2013 ownCloud Inc.
d25af7ee (David A. Velasco 2012-10-24 19:39:55 +0200   3)  *
d25af7ee (David A. Velasco 2012-10-24 19:39:55 +0200   4)  *   This program is free software: you can redistribute it and/or modify
bdc0332c (David A. Velasco 2013-04-17 12:26:13 +0200   5)  *   it under the terms of the GNU General Public License version 2,
bdc0332c (David A. Velasco 2013-04-17 12:26:13 +0200   6)  *   as published by the Free Software Foundation.
d25af7ee (David A. Velasco 2012-10-24 19:39:55 +0200   7)  *
d25af7ee (David A. Velasco 2012-10-24 19:39:55 +0200   8)  *   This program is distributed in the hope that it will be useful,
d25af7ee (David A. Velasco 2012-10-24 19:39:55 +0200   9)  *   but WITHOUT ANY WARRANTY; without even the implied warranty of
d25af7ee (David A. Velasco 2012-10-24 19:39:55 +0200  10)  *   MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
d25af7ee (David A. Velasco 2012-10-24 19:39:55 +0200  11)  *   GNU General Public License for more details.
d25af7ee (David A. Velasco 2012-10-24 19:39:55 +0200  12)  *
d25af7ee (David A. Velasco 2012-10-24 19:39:55 +0200  13)  *   You should have received a copy of the GNU General Public License
d25af7ee (David A. Velasco 2012-10-24 19:39:55 +0200  14)  *   along with this program.  If not, see <http://www.gnu.org/licenses/>.
d25af7ee (David A. Velasco 2012-10-24 19:39:55 +0200  15)  *
d25af7ee (David A. Velasco 2012-10-24 19:39:55 +0200  16)  */
d25af7ee (David A. Velasco 2012-10-24 19:39:55 +0200  17) 
d25af7ee (David A. Velasco 2012-10-24 19:39:55 +0200  18) package com.owncloud.android.operations;
d25af7ee (David A. Velasco 2012-10-24 19:39:55 +0200  19) 
d25af7ee (David A. Velasco 2012-10-24 19:39:55 +0200  20) import org.apache.commons.httpclient.HttpStatus;
d25af7ee (David A. Velasco 2012-10-24 19:39:55 +0200  21) import org.apache.commons.httpclient.methods.GetMethod;
d25af7ee (David A. Velasco 2012-10-24 19:39:55 +0200  22) import org.json.JSONException;
d25af7ee (David A. Velasco 2012-10-24 19:39:55 +0200  23) import org.json.JSONObject;
d25af7ee (David A. Velasco 2012-10-24 19:39:55 +0200  24) 
d25af7ee (David A. Velasco 2012-10-24 19:39:55 +0200  25) import android.accounts.Account;
d25af7ee (David A. Velasco 2012-10-24 19:39:55 +0200  26) import android.accounts.AccountManager;
d25af7ee (David A. Velasco 2012-10-24 19:39:55 +0200  27) import android.content.Context;
d25af7ee (David A. Velasco 2012-10-24 19:39:55 +0200  28) 
69d6d821 (David A. Velasco 2013-03-21 12:21:48 +0100  29) import com.owncloud.android.authentication.AccountAuthenticator;
c4ac05de (David A. Velasco 2013-06-18 11:34:08 +0200  30) import com.owncloud.android.authentication.AccountUtils;
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100  31) import com.owncloud.android.Log_OC;
d25af7ee (David A. Velasco 2012-10-24 19:39:55 +0200  32) import com.owncloud.android.operations.RemoteOperationResult.ResultCode;
d25af7ee (David A. Velasco 2012-10-24 19:39:55 +0200  33) import com.owncloud.android.utils.OwnCloudVersion;
d25af7ee (David A. Velasco 2012-10-24 19:39:55 +0200  34) 
d25af7ee (David A. Velasco 2012-10-24 19:39:55 +0200  35) import eu.alefzero.webdav.WebdavClient;
d25af7ee (David A. Velasco 2012-10-24 19:39:55 +0200  36) 
d25af7ee (David A. Velasco 2012-10-24 19:39:55 +0200  37) /**
d25af7ee (David A. Velasco 2012-10-24 19:39:55 +0200  38)  * Remote operation that checks the version of an ownCloud server and stores it locally
d25af7ee (David A. Velasco 2012-10-24 19:39:55 +0200  39)  * 
d25af7ee (David A. Velasco 2012-10-24 19:39:55 +0200  40)  * @author David A. Velasco
d25af7ee (David A. Velasco 2012-10-24 19:39:55 +0200  41)  */
d25af7ee (David A. Velasco 2012-10-24 19:39:55 +0200  42) public class UpdateOCVersionOperation extends RemoteOperation {
d25af7ee (David A. Velasco 2012-10-24 19:39:55 +0200  43) 
c301865c (David A. Velasco 2012-11-16 16:23:45 +0100  44)     private static final String TAG = UpdateOCVersionOperation.class.getSimpleName();
d25af7ee (David A. Velasco 2012-10-24 19:39:55 +0200  45) 
d25af7ee (David A. Velasco 2012-10-24 19:39:55 +0200  46)     private Account mAccount;
d25af7ee (David A. Velasco 2012-10-24 19:39:55 +0200  47)     private Context mContext;
d25af7ee (David A. Velasco 2012-10-24 19:39:55 +0200  48)     
d25af7ee (David A. Velasco 2012-10-24 19:39:55 +0200  49)     
d25af7ee (David A. Velasco 2012-10-24 19:39:55 +0200  50)     public UpdateOCVersionOperation(Account account, Context context) {
d25af7ee (David A. Velasco 2012-10-24 19:39:55 +0200  51)         mAccount = account;
d25af7ee (David A. Velasco 2012-10-24 19:39:55 +0200  52)         mContext = context;
d25af7ee (David A. Velasco 2012-10-24 19:39:55 +0200  53)     }
d25af7ee (David A. Velasco 2012-10-24 19:39:55 +0200  54)     
d25af7ee (David A. Velasco 2012-10-24 19:39:55 +0200  55)     
d25af7ee (David A. Velasco 2012-10-24 19:39:55 +0200  56)     @Override
d25af7ee (David A. Velasco 2012-10-24 19:39:55 +0200  57)     protected RemoteOperationResult run(WebdavClient client) {
d25af7ee (David A. Velasco 2012-10-24 19:39:55 +0200  58)         AccountManager accountMngr = AccountManager.get(mContext); 
d25af7ee (David A. Velasco 2012-10-24 19:39:55 +0200  59)         String statUrl = accountMngr.getUserData(mAccount, AccountAuthenticator.KEY_OC_BASE_URL);
d25af7ee (David A. Velasco 2012-10-24 19:39:55 +0200  60)         statUrl += AccountUtils.STATUS_PATH;
d25af7ee (David A. Velasco 2012-10-24 19:39:55 +0200  61)         RemoteOperationResult result = null;
d25af7ee (David A. Velasco 2012-10-24 19:39:55 +0200  62)         GetMethod get = null;
d25af7ee (David A. Velasco 2012-10-24 19:39:55 +0200  63)         try {
d25af7ee (David A. Velasco 2012-10-24 19:39:55 +0200  64)             get = new GetMethod(statUrl);
d25af7ee (David A. Velasco 2012-10-24 19:39:55 +0200  65)             int status = client.executeMethod(get);
d25af7ee (David A. Velasco 2012-10-24 19:39:55 +0200  66)             if (status != HttpStatus.SC_OK) {
d25af7ee (David A. Velasco 2012-10-24 19:39:55 +0200  67)                 client.exhaustResponse(get.getResponseBodyAsStream());
78bcf721 (David A. Velasco 2013-08-07 19:13:00 +0200  68)                 result = new RemoteOperationResult(false, status, get.getResponseHeaders());
d25af7ee (David A. Velasco 2012-10-24 19:39:55 +0200  69)                 
d25af7ee (David A. Velasco 2012-10-24 19:39:55 +0200  70)             } else {
d25af7ee (David A. Velasco 2012-10-24 19:39:55 +0200  71)                 String response = get.getResponseBodyAsString();
d25af7ee (David A. Velasco 2012-10-24 19:39:55 +0200  72)                 if (response != null) {
d25af7ee (David A. Velasco 2012-10-24 19:39:55 +0200  73)                     JSONObject json = new JSONObject(response);
d25af7ee (David A. Velasco 2012-10-24 19:39:55 +0200  74)                     if (json != null && json.getString("version") != null) {
d25af7ee (David A. Velasco 2012-10-24 19:39:55 +0200  75)                         OwnCloudVersion ocver = new OwnCloudVersion(json.getString("version"));
d25af7ee (David A. Velasco 2012-10-24 19:39:55 +0200  76)                         if (ocver.isVersionValid()) {
d25af7ee (David A. Velasco 2012-10-24 19:39:55 +0200  77)                             accountMngr.setUserData(mAccount, AccountAuthenticator.KEY_OC_VERSION, ocver.toString());
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100  78)                             Log_OC.d(TAG, "Got new OC version " + ocver.toString());
d25af7ee (David A. Velasco 2012-10-24 19:39:55 +0200  79)                             result = new RemoteOperationResult(ResultCode.OK);
d25af7ee (David A. Velasco 2012-10-24 19:39:55 +0200  80)                             
d25af7ee (David A. Velasco 2012-10-24 19:39:55 +0200  81)                         } else {
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100  82)                             Log_OC.w(TAG, "Invalid version number received from server: " + json.getString("version"));
d25af7ee (David A. Velasco 2012-10-24 19:39:55 +0200  83)                             result = new RemoteOperationResult(RemoteOperationResult.ResultCode.BAD_OC_VERSION);
d25af7ee (David A. Velasco 2012-10-24 19:39:55 +0200  84)                         }
d25af7ee (David A. Velasco 2012-10-24 19:39:55 +0200  85)                     }
d25af7ee (David A. Velasco 2012-10-24 19:39:55 +0200  86)                 }
d25af7ee (David A. Velasco 2012-10-24 19:39:55 +0200  87)                 if (result == null) {
d25af7ee (David A. Velasco 2012-10-24 19:39:55 +0200  88)                     result = new RemoteOperationResult(RemoteOperationResult.ResultCode.INSTANCE_NOT_CONFIGURED);
d25af7ee (David A. Velasco 2012-10-24 19:39:55 +0200  89)                 }
d25af7ee (David A. Velasco 2012-10-24 19:39:55 +0200  90)             }
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100  91)             Log_OC.i(TAG, "Check for update of ownCloud server version at " + client.getBaseUri() + ": " + result.getLogMessage());
d25af7ee (David A. Velasco 2012-10-24 19:39:55 +0200  92)             
d25af7ee (David A. Velasco 2012-10-24 19:39:55 +0200  93)         } catch (JSONException e) {
d25af7ee (David A. Velasco 2012-10-24 19:39:55 +0200  94)             result = new RemoteOperationResult(RemoteOperationResult.ResultCode.INSTANCE_NOT_CONFIGURED);
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100  95)             Log_OC.e(TAG, "Check for update of ownCloud server version at " + client.getBaseUri() + ": " + result.getLogMessage(), e);
d25af7ee (David A. Velasco 2012-10-24 19:39:55 +0200  96)                 
d25af7ee (David A. Velasco 2012-10-24 19:39:55 +0200  97)         } catch (Exception e) {
d25af7ee (David A. Velasco 2012-10-24 19:39:55 +0200  98)             result = new RemoteOperationResult(e);
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100  99)             Log_OC.e(TAG, "Check for update of ownCloud server version at " + client.getBaseUri() + ": " + result.getLogMessage(), e);
d25af7ee (David A. Velasco 2012-10-24 19:39:55 +0200 100)             
d25af7ee (David A. Velasco 2012-10-24 19:39:55 +0200 101)         } finally {
d25af7ee (David A. Velasco 2012-10-24 19:39:55 +0200 102)             if (get != null) 
d25af7ee (David A. Velasco 2012-10-24 19:39:55 +0200 103)                 get.releaseConnection();
d25af7ee (David A. Velasco 2012-10-24 19:39:55 +0200 104)         }
d25af7ee (David A. Velasco 2012-10-24 19:39:55 +0200 105)         return result;
d25af7ee (David A. Velasco 2012-10-24 19:39:55 +0200 106)     }
d25af7ee (David A. Velasco 2012-10-24 19:39:55 +0200 107) 
d25af7ee (David A. Velasco 2012-10-24 19:39:55 +0200 108) }
