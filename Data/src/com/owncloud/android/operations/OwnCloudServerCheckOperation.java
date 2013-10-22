aa19d764 src/com/owncloud/android/operations/OwnCloudServerCheckOperation.java (David A. Velasco 2013-01-24 10:27:53 +0100   1) /* ownCloud Android client application
bb257ec7 src/com/owncloud/android/operations/ConnectionCheckOperation.java     (David A. Velasco 2013-02-07 18:45:10 +0100   2)  *   Copyright (C) 2012-2013 ownCloud Inc.
aa19d764 src/com/owncloud/android/operations/OwnCloudServerCheckOperation.java (David A. Velasco 2013-01-24 10:27:53 +0100   3)  *
aa19d764 src/com/owncloud/android/operations/OwnCloudServerCheckOperation.java (David A. Velasco 2013-01-24 10:27:53 +0100   4)  *   This program is free software: you can redistribute it and/or modify
bdc0332c src/com/owncloud/android/operations/ConnectionCheckOperation.java     (David A. Velasco 2013-04-17 12:26:13 +0200   5)  *   it under the terms of the GNU General Public License version 2,
bdc0332c src/com/owncloud/android/operations/ConnectionCheckOperation.java     (David A. Velasco 2013-04-17 12:26:13 +0200   6)  *   as published by the Free Software Foundation.
aa19d764 src/com/owncloud/android/operations/OwnCloudServerCheckOperation.java (David A. Velasco 2013-01-24 10:27:53 +0100   7)  *
aa19d764 src/com/owncloud/android/operations/OwnCloudServerCheckOperation.java (David A. Velasco 2013-01-24 10:27:53 +0100   8)  *   This program is distributed in the hope that it will be useful,
aa19d764 src/com/owncloud/android/operations/OwnCloudServerCheckOperation.java (David A. Velasco 2013-01-24 10:27:53 +0100   9)  *   but WITHOUT ANY WARRANTY; without even the implied warranty of
aa19d764 src/com/owncloud/android/operations/OwnCloudServerCheckOperation.java (David A. Velasco 2013-01-24 10:27:53 +0100  10)  *   MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
aa19d764 src/com/owncloud/android/operations/OwnCloudServerCheckOperation.java (David A. Velasco 2013-01-24 10:27:53 +0100  11)  *   GNU General Public License for more details.
aa19d764 src/com/owncloud/android/operations/OwnCloudServerCheckOperation.java (David A. Velasco 2013-01-24 10:27:53 +0100  12)  *
aa19d764 src/com/owncloud/android/operations/OwnCloudServerCheckOperation.java (David A. Velasco 2013-01-24 10:27:53 +0100  13)  *   You should have received a copy of the GNU General Public License
aa19d764 src/com/owncloud/android/operations/OwnCloudServerCheckOperation.java (David A. Velasco 2013-01-24 10:27:53 +0100  14)  *   along with this program.  If not, see <http://www.gnu.org/licenses/>.
aa19d764 src/com/owncloud/android/operations/OwnCloudServerCheckOperation.java (David A. Velasco 2013-01-24 10:27:53 +0100  15)  *
aa19d764 src/com/owncloud/android/operations/OwnCloudServerCheckOperation.java (David A. Velasco 2013-01-24 10:27:53 +0100  16)  */
aa19d764 src/com/owncloud/android/operations/OwnCloudServerCheckOperation.java (David A. Velasco 2013-01-24 10:27:53 +0100  17) 
aa19d764 src/com/owncloud/android/operations/OwnCloudServerCheckOperation.java (David A. Velasco 2013-01-24 10:27:53 +0100  18) package com.owncloud.android.operations;
aa19d764 src/com/owncloud/android/operations/OwnCloudServerCheckOperation.java (David A. Velasco 2013-01-24 10:27:53 +0100  19) 
aa19d764 src/com/owncloud/android/operations/OwnCloudServerCheckOperation.java (David A. Velasco 2013-01-24 10:27:53 +0100  20) import org.apache.commons.httpclient.HttpStatus;
aa19d764 src/com/owncloud/android/operations/OwnCloudServerCheckOperation.java (David A. Velasco 2013-01-24 10:27:53 +0100  21) import org.apache.commons.httpclient.methods.GetMethod;
aa19d764 src/com/owncloud/android/operations/OwnCloudServerCheckOperation.java (David A. Velasco 2013-01-24 10:27:53 +0100  22) import org.json.JSONException;
aa19d764 src/com/owncloud/android/operations/OwnCloudServerCheckOperation.java (David A. Velasco 2013-01-24 10:27:53 +0100  23) import org.json.JSONObject;
aa19d764 src/com/owncloud/android/operations/OwnCloudServerCheckOperation.java (David A. Velasco 2013-01-24 10:27:53 +0100  24) 
8e36e7cc src/com/owncloud/android/operations/ConnectionCheckOperation.java     (zerginator       2013-03-12 07:56:27 +0100  25) import com.owncloud.android.Log_OC;
c4ac05de src/com/owncloud/android/operations/OwnCloudServerCheckOperation.java (David A. Velasco 2013-06-18 11:34:08 +0200  26) import com.owncloud.android.authentication.AccountUtils;
aa19d764 src/com/owncloud/android/operations/OwnCloudServerCheckOperation.java (David A. Velasco 2013-01-24 10:27:53 +0100  27) import com.owncloud.android.utils.OwnCloudVersion;
aa19d764 src/com/owncloud/android/operations/OwnCloudServerCheckOperation.java (David A. Velasco 2013-01-24 10:27:53 +0100  28) 
aa19d764 src/com/owncloud/android/operations/OwnCloudServerCheckOperation.java (David A. Velasco 2013-01-24 10:27:53 +0100  29) import eu.alefzero.webdav.WebdavClient;
aa19d764 src/com/owncloud/android/operations/OwnCloudServerCheckOperation.java (David A. Velasco 2013-01-24 10:27:53 +0100  30) import android.content.Context;
aa19d764 src/com/owncloud/android/operations/OwnCloudServerCheckOperation.java (David A. Velasco 2013-01-24 10:27:53 +0100  31) import android.net.ConnectivityManager;
aa19d764 src/com/owncloud/android/operations/OwnCloudServerCheckOperation.java (David A. Velasco 2013-01-24 10:27:53 +0100  32) import android.net.Uri;
aa19d764 src/com/owncloud/android/operations/OwnCloudServerCheckOperation.java (David A. Velasco 2013-01-24 10:27:53 +0100  33) 
aa19d764 src/com/owncloud/android/operations/OwnCloudServerCheckOperation.java (David A. Velasco 2013-01-24 10:27:53 +0100  34) public class OwnCloudServerCheckOperation extends RemoteOperation {
aa19d764 src/com/owncloud/android/operations/OwnCloudServerCheckOperation.java (David A. Velasco 2013-01-24 10:27:53 +0100  35)     
aa19d764 src/com/owncloud/android/operations/OwnCloudServerCheckOperation.java (David A. Velasco 2013-01-24 10:27:53 +0100  36)     /** Maximum time to wait for a response from the server when the connection is being tested, in MILLISECONDs.  */
aa19d764 src/com/owncloud/android/operations/OwnCloudServerCheckOperation.java (David A. Velasco 2013-01-24 10:27:53 +0100  37)     public static final int TRY_CONNECTION_TIMEOUT = 5000;
aa19d764 src/com/owncloud/android/operations/OwnCloudServerCheckOperation.java (David A. Velasco 2013-01-24 10:27:53 +0100  38)     
aa19d764 src/com/owncloud/android/operations/OwnCloudServerCheckOperation.java (David A. Velasco 2013-01-24 10:27:53 +0100  39)     private static final String TAG = OwnCloudServerCheckOperation.class.getSimpleName();
aa19d764 src/com/owncloud/android/operations/OwnCloudServerCheckOperation.java (David A. Velasco 2013-01-24 10:27:53 +0100  40)     
aa19d764 src/com/owncloud/android/operations/OwnCloudServerCheckOperation.java (David A. Velasco 2013-01-24 10:27:53 +0100  41)     private String mUrl;
aa19d764 src/com/owncloud/android/operations/OwnCloudServerCheckOperation.java (David A. Velasco 2013-01-24 10:27:53 +0100  42)     private RemoteOperationResult mLatestResult;
aa19d764 src/com/owncloud/android/operations/OwnCloudServerCheckOperation.java (David A. Velasco 2013-01-24 10:27:53 +0100  43)     private Context mContext;
aa19d764 src/com/owncloud/android/operations/OwnCloudServerCheckOperation.java (David A. Velasco 2013-01-24 10:27:53 +0100  44)     private OwnCloudVersion mOCVersion;
aa19d764 src/com/owncloud/android/operations/OwnCloudServerCheckOperation.java (David A. Velasco 2013-01-24 10:27:53 +0100  45) 
aa19d764 src/com/owncloud/android/operations/OwnCloudServerCheckOperation.java (David A. Velasco 2013-01-24 10:27:53 +0100  46)     public OwnCloudServerCheckOperation(String url, Context context) {
aa19d764 src/com/owncloud/android/operations/OwnCloudServerCheckOperation.java (David A. Velasco 2013-01-24 10:27:53 +0100  47)         mUrl = url;
aa19d764 src/com/owncloud/android/operations/OwnCloudServerCheckOperation.java (David A. Velasco 2013-01-24 10:27:53 +0100  48)         mContext = context;
aa19d764 src/com/owncloud/android/operations/OwnCloudServerCheckOperation.java (David A. Velasco 2013-01-24 10:27:53 +0100  49)         mOCVersion = null;
aa19d764 src/com/owncloud/android/operations/OwnCloudServerCheckOperation.java (David A. Velasco 2013-01-24 10:27:53 +0100  50)     }
aa19d764 src/com/owncloud/android/operations/OwnCloudServerCheckOperation.java (David A. Velasco 2013-01-24 10:27:53 +0100  51)     
aa19d764 src/com/owncloud/android/operations/OwnCloudServerCheckOperation.java (David A. Velasco 2013-01-24 10:27:53 +0100  52)     public OwnCloudVersion getDiscoveredVersion() {
aa19d764 src/com/owncloud/android/operations/OwnCloudServerCheckOperation.java (David A. Velasco 2013-01-24 10:27:53 +0100  53)         return mOCVersion;
aa19d764 src/com/owncloud/android/operations/OwnCloudServerCheckOperation.java (David A. Velasco 2013-01-24 10:27:53 +0100  54)     }
aa19d764 src/com/owncloud/android/operations/OwnCloudServerCheckOperation.java (David A. Velasco 2013-01-24 10:27:53 +0100  55) 
aa19d764 src/com/owncloud/android/operations/OwnCloudServerCheckOperation.java (David A. Velasco 2013-01-24 10:27:53 +0100  56)     private boolean tryConnection(WebdavClient wc, String urlSt) {
aa19d764 src/com/owncloud/android/operations/OwnCloudServerCheckOperation.java (David A. Velasco 2013-01-24 10:27:53 +0100  57)         boolean retval = false;
aa19d764 src/com/owncloud/android/operations/OwnCloudServerCheckOperation.java (David A. Velasco 2013-01-24 10:27:53 +0100  58)         GetMethod get = null;
aa19d764 src/com/owncloud/android/operations/OwnCloudServerCheckOperation.java (David A. Velasco 2013-01-24 10:27:53 +0100  59)         try {
aa19d764 src/com/owncloud/android/operations/OwnCloudServerCheckOperation.java (David A. Velasco 2013-01-24 10:27:53 +0100  60)             get = new GetMethod(urlSt);
aa19d764 src/com/owncloud/android/operations/OwnCloudServerCheckOperation.java (David A. Velasco 2013-01-24 10:27:53 +0100  61)             int status = wc.executeMethod(get, TRY_CONNECTION_TIMEOUT, TRY_CONNECTION_TIMEOUT);
aa19d764 src/com/owncloud/android/operations/OwnCloudServerCheckOperation.java (David A. Velasco 2013-01-24 10:27:53 +0100  62)             String response = get.getResponseBodyAsString();
aa19d764 src/com/owncloud/android/operations/OwnCloudServerCheckOperation.java (David A. Velasco 2013-01-24 10:27:53 +0100  63)             if (status == HttpStatus.SC_OK) {
aa19d764 src/com/owncloud/android/operations/OwnCloudServerCheckOperation.java (David A. Velasco 2013-01-24 10:27:53 +0100  64)                 JSONObject json = new JSONObject(response);
aa19d764 src/com/owncloud/android/operations/OwnCloudServerCheckOperation.java (David A. Velasco 2013-01-24 10:27:53 +0100  65)                 if (!json.getBoolean("installed")) {
aa19d764 src/com/owncloud/android/operations/OwnCloudServerCheckOperation.java (David A. Velasco 2013-01-24 10:27:53 +0100  66)                     mLatestResult = new RemoteOperationResult(RemoteOperationResult.ResultCode.INSTANCE_NOT_CONFIGURED);
aa19d764 src/com/owncloud/android/operations/OwnCloudServerCheckOperation.java (David A. Velasco 2013-01-24 10:27:53 +0100  67)                 } else {
aa19d764 src/com/owncloud/android/operations/OwnCloudServerCheckOperation.java (David A. Velasco 2013-01-24 10:27:53 +0100  68)                     mOCVersion = new OwnCloudVersion(json.getString("version"));
aa19d764 src/com/owncloud/android/operations/OwnCloudServerCheckOperation.java (David A. Velasco 2013-01-24 10:27:53 +0100  69)                     if (!mOCVersion.isVersionValid()) {
aa19d764 src/com/owncloud/android/operations/OwnCloudServerCheckOperation.java (David A. Velasco 2013-01-24 10:27:53 +0100  70)                         mLatestResult = new RemoteOperationResult(RemoteOperationResult.ResultCode.BAD_OC_VERSION);
aa19d764 src/com/owncloud/android/operations/OwnCloudServerCheckOperation.java (David A. Velasco 2013-01-24 10:27:53 +0100  71)                         
aa19d764 src/com/owncloud/android/operations/OwnCloudServerCheckOperation.java (David A. Velasco 2013-01-24 10:27:53 +0100  72)                     } else {
aa19d764 src/com/owncloud/android/operations/OwnCloudServerCheckOperation.java (David A. Velasco 2013-01-24 10:27:53 +0100  73)                         mLatestResult = new RemoteOperationResult(urlSt.startsWith("https://") ? 
aa19d764 src/com/owncloud/android/operations/OwnCloudServerCheckOperation.java (David A. Velasco 2013-01-24 10:27:53 +0100  74)                                                                     RemoteOperationResult.ResultCode.OK_SSL : 
aa19d764 src/com/owncloud/android/operations/OwnCloudServerCheckOperation.java (David A. Velasco 2013-01-24 10:27:53 +0100  75)                                                                     RemoteOperationResult.ResultCode.OK_NO_SSL
aa19d764 src/com/owncloud/android/operations/OwnCloudServerCheckOperation.java (David A. Velasco 2013-01-24 10:27:53 +0100  76)                             );
aa19d764 src/com/owncloud/android/operations/OwnCloudServerCheckOperation.java (David A. Velasco 2013-01-24 10:27:53 +0100  77) 
aa19d764 src/com/owncloud/android/operations/OwnCloudServerCheckOperation.java (David A. Velasco 2013-01-24 10:27:53 +0100  78)                         retval = true;
aa19d764 src/com/owncloud/android/operations/OwnCloudServerCheckOperation.java (David A. Velasco 2013-01-24 10:27:53 +0100  79)                     }
aa19d764 src/com/owncloud/android/operations/OwnCloudServerCheckOperation.java (David A. Velasco 2013-01-24 10:27:53 +0100  80)                 }
aa19d764 src/com/owncloud/android/operations/OwnCloudServerCheckOperation.java (David A. Velasco 2013-01-24 10:27:53 +0100  81)                 
aa19d764 src/com/owncloud/android/operations/OwnCloudServerCheckOperation.java (David A. Velasco 2013-01-24 10:27:53 +0100  82)             } else {
78bcf721 src/com/owncloud/android/operations/OwnCloudServerCheckOperation.java (David A. Velasco 2013-08-07 19:13:00 +0200  83)                 mLatestResult = new RemoteOperationResult(false, status, get.getResponseHeaders());
aa19d764 src/com/owncloud/android/operations/OwnCloudServerCheckOperation.java (David A. Velasco 2013-01-24 10:27:53 +0100  84)             }
aa19d764 src/com/owncloud/android/operations/OwnCloudServerCheckOperation.java (David A. Velasco 2013-01-24 10:27:53 +0100  85) 
aa19d764 src/com/owncloud/android/operations/OwnCloudServerCheckOperation.java (David A. Velasco 2013-01-24 10:27:53 +0100  86)         } catch (JSONException e) {
aa19d764 src/com/owncloud/android/operations/OwnCloudServerCheckOperation.java (David A. Velasco 2013-01-24 10:27:53 +0100  87)             mLatestResult = new RemoteOperationResult(RemoteOperationResult.ResultCode.INSTANCE_NOT_CONFIGURED);
aa19d764 src/com/owncloud/android/operations/OwnCloudServerCheckOperation.java (David A. Velasco 2013-01-24 10:27:53 +0100  88)             
aa19d764 src/com/owncloud/android/operations/OwnCloudServerCheckOperation.java (David A. Velasco 2013-01-24 10:27:53 +0100  89)         } catch (Exception e) {
aa19d764 src/com/owncloud/android/operations/OwnCloudServerCheckOperation.java (David A. Velasco 2013-01-24 10:27:53 +0100  90)             mLatestResult = new RemoteOperationResult(e);
aa19d764 src/com/owncloud/android/operations/OwnCloudServerCheckOperation.java (David A. Velasco 2013-01-24 10:27:53 +0100  91)             
aa19d764 src/com/owncloud/android/operations/OwnCloudServerCheckOperation.java (David A. Velasco 2013-01-24 10:27:53 +0100  92)         } finally {
aa19d764 src/com/owncloud/android/operations/OwnCloudServerCheckOperation.java (David A. Velasco 2013-01-24 10:27:53 +0100  93)             if (get != null)
aa19d764 src/com/owncloud/android/operations/OwnCloudServerCheckOperation.java (David A. Velasco 2013-01-24 10:27:53 +0100  94)                 get.releaseConnection();
aa19d764 src/com/owncloud/android/operations/OwnCloudServerCheckOperation.java (David A. Velasco 2013-01-24 10:27:53 +0100  95)         }
aa19d764 src/com/owncloud/android/operations/OwnCloudServerCheckOperation.java (David A. Velasco 2013-01-24 10:27:53 +0100  96)         
aa19d764 src/com/owncloud/android/operations/OwnCloudServerCheckOperation.java (David A. Velasco 2013-01-24 10:27:53 +0100  97)         if (mLatestResult.isSuccess()) {
8e36e7cc src/com/owncloud/android/operations/ConnectionCheckOperation.java     (zerginator       2013-03-12 07:56:27 +0100  98)             Log_OC.i(TAG, "Connection check at " + urlSt + ": " + mLatestResult.getLogMessage());
aa19d764 src/com/owncloud/android/operations/OwnCloudServerCheckOperation.java (David A. Velasco 2013-01-24 10:27:53 +0100  99)             
aa19d764 src/com/owncloud/android/operations/OwnCloudServerCheckOperation.java (David A. Velasco 2013-01-24 10:27:53 +0100 100)         } else if (mLatestResult.getException() != null) {
8e36e7cc src/com/owncloud/android/operations/ConnectionCheckOperation.java     (zerginator       2013-03-12 07:56:27 +0100 101)             Log_OC.e(TAG, "Connection check at " + urlSt + ": " + mLatestResult.getLogMessage(), mLatestResult.getException());
aa19d764 src/com/owncloud/android/operations/OwnCloudServerCheckOperation.java (David A. Velasco 2013-01-24 10:27:53 +0100 102)             
aa19d764 src/com/owncloud/android/operations/OwnCloudServerCheckOperation.java (David A. Velasco 2013-01-24 10:27:53 +0100 103)         } else {
8e36e7cc src/com/owncloud/android/operations/ConnectionCheckOperation.java     (zerginator       2013-03-12 07:56:27 +0100 104)             Log_OC.e(TAG, "Connection check at " + urlSt + ": " + mLatestResult.getLogMessage());
aa19d764 src/com/owncloud/android/operations/OwnCloudServerCheckOperation.java (David A. Velasco 2013-01-24 10:27:53 +0100 105)         }
aa19d764 src/com/owncloud/android/operations/OwnCloudServerCheckOperation.java (David A. Velasco 2013-01-24 10:27:53 +0100 106) 
aa19d764 src/com/owncloud/android/operations/OwnCloudServerCheckOperation.java (David A. Velasco 2013-01-24 10:27:53 +0100 107)         return retval;
aa19d764 src/com/owncloud/android/operations/OwnCloudServerCheckOperation.java (David A. Velasco 2013-01-24 10:27:53 +0100 108)     }
aa19d764 src/com/owncloud/android/operations/OwnCloudServerCheckOperation.java (David A. Velasco 2013-01-24 10:27:53 +0100 109) 
aa19d764 src/com/owncloud/android/operations/OwnCloudServerCheckOperation.java (David A. Velasco 2013-01-24 10:27:53 +0100 110)     private boolean isOnline() {
aa19d764 src/com/owncloud/android/operations/OwnCloudServerCheckOperation.java (David A. Velasco 2013-01-24 10:27:53 +0100 111)         ConnectivityManager cm = (ConnectivityManager) mContext
aa19d764 src/com/owncloud/android/operations/OwnCloudServerCheckOperation.java (David A. Velasco 2013-01-24 10:27:53 +0100 112)                 .getSystemService(Context.CONNECTIVITY_SERVICE);
aa19d764 src/com/owncloud/android/operations/OwnCloudServerCheckOperation.java (David A. Velasco 2013-01-24 10:27:53 +0100 113)         return cm != null && cm.getActiveNetworkInfo() != null
aa19d764 src/com/owncloud/android/operations/OwnCloudServerCheckOperation.java (David A. Velasco 2013-01-24 10:27:53 +0100 114)                 && cm.getActiveNetworkInfo().isConnectedOrConnecting();
aa19d764 src/com/owncloud/android/operations/OwnCloudServerCheckOperation.java (David A. Velasco 2013-01-24 10:27:53 +0100 115)     }
aa19d764 src/com/owncloud/android/operations/OwnCloudServerCheckOperation.java (David A. Velasco 2013-01-24 10:27:53 +0100 116) 
aa19d764 src/com/owncloud/android/operations/OwnCloudServerCheckOperation.java (David A. Velasco 2013-01-24 10:27:53 +0100 117) 	@Override
aa19d764 src/com/owncloud/android/operations/OwnCloudServerCheckOperation.java (David A. Velasco 2013-01-24 10:27:53 +0100 118) 	protected RemoteOperationResult run(WebdavClient client) {
aa19d764 src/com/owncloud/android/operations/OwnCloudServerCheckOperation.java (David A. Velasco 2013-01-24 10:27:53 +0100 119)         if (!isOnline()) {
aa19d764 src/com/owncloud/android/operations/OwnCloudServerCheckOperation.java (David A. Velasco 2013-01-24 10:27:53 +0100 120)         	return new RemoteOperationResult(RemoteOperationResult.ResultCode.NO_NETWORK_CONNECTION);
aa19d764 src/com/owncloud/android/operations/OwnCloudServerCheckOperation.java (David A. Velasco 2013-01-24 10:27:53 +0100 121)         }
aa19d764 src/com/owncloud/android/operations/OwnCloudServerCheckOperation.java (David A. Velasco 2013-01-24 10:27:53 +0100 122)         if (mUrl.startsWith("http://") || mUrl.startsWith("https://")) {
aa19d764 src/com/owncloud/android/operations/OwnCloudServerCheckOperation.java (David A. Velasco 2013-01-24 10:27:53 +0100 123)             tryConnection(client, mUrl + AccountUtils.STATUS_PATH);
aa19d764 src/com/owncloud/android/operations/OwnCloudServerCheckOperation.java (David A. Velasco 2013-01-24 10:27:53 +0100 124)             
aa19d764 src/com/owncloud/android/operations/OwnCloudServerCheckOperation.java (David A. Velasco 2013-01-24 10:27:53 +0100 125)         } else {
aa19d764 src/com/owncloud/android/operations/OwnCloudServerCheckOperation.java (David A. Velasco 2013-01-24 10:27:53 +0100 126)             client.setBaseUri(Uri.parse("https://" + mUrl + AccountUtils.STATUS_PATH));
aa19d764 src/com/owncloud/android/operations/OwnCloudServerCheckOperation.java (David A. Velasco 2013-01-24 10:27:53 +0100 127)             boolean httpsSuccess = tryConnection(client, "https://" + mUrl + AccountUtils.STATUS_PATH); 
aa19d764 src/com/owncloud/android/operations/OwnCloudServerCheckOperation.java (David A. Velasco 2013-01-24 10:27:53 +0100 128)             if (!httpsSuccess && !mLatestResult.isSslRecoverableException()) {
8e36e7cc src/com/owncloud/android/operations/ConnectionCheckOperation.java     (zerginator       2013-03-12 07:56:27 +0100 129)                 Log_OC.d(TAG, "establishing secure connection failed, trying non secure connection");
aa19d764 src/com/owncloud/android/operations/OwnCloudServerCheckOperation.java (David A. Velasco 2013-01-24 10:27:53 +0100 130)                 client.setBaseUri(Uri.parse("http://" + mUrl + AccountUtils.STATUS_PATH));
aa19d764 src/com/owncloud/android/operations/OwnCloudServerCheckOperation.java (David A. Velasco 2013-01-24 10:27:53 +0100 131)                 tryConnection(client, "http://" + mUrl + AccountUtils.STATUS_PATH);
aa19d764 src/com/owncloud/android/operations/OwnCloudServerCheckOperation.java (David A. Velasco 2013-01-24 10:27:53 +0100 132)             }
aa19d764 src/com/owncloud/android/operations/OwnCloudServerCheckOperation.java (David A. Velasco 2013-01-24 10:27:53 +0100 133)         }
aa19d764 src/com/owncloud/android/operations/OwnCloudServerCheckOperation.java (David A. Velasco 2013-01-24 10:27:53 +0100 134)         return mLatestResult;
aa19d764 src/com/owncloud/android/operations/OwnCloudServerCheckOperation.java (David A. Velasco 2013-01-24 10:27:53 +0100 135) 	}
aa19d764 src/com/owncloud/android/operations/OwnCloudServerCheckOperation.java (David A. Velasco 2013-01-24 10:27:53 +0100 136) 	
aa19d764 src/com/owncloud/android/operations/OwnCloudServerCheckOperation.java (David A. Velasco 2013-01-24 10:27:53 +0100 137) }
