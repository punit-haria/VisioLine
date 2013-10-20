5be4bf17 (David A. Velasco 2012-12-18 11:07:05 +0100  1) /* ownCloud Android client application
5be4bf17 (David A. Velasco 2012-12-18 11:07:05 +0100  2)  *   Copyright (C) 2012 ownCloud Inc.
5be4bf17 (David A. Velasco 2012-12-18 11:07:05 +0100  3)  *
5be4bf17 (David A. Velasco 2012-12-18 11:07:05 +0100  4)  *   This program is free software: you can redistribute it and/or modify
2946d8dd (David A. Velasco 2013-04-25 19:39:22 +0200  5)  *   it under the terms of the GNU General Public License version 2,
2946d8dd (David A. Velasco 2013-04-25 19:39:22 +0200  6)  *   as published by the Free Software Foundation.
5be4bf17 (David A. Velasco 2012-12-18 11:07:05 +0100  7)  *
5be4bf17 (David A. Velasco 2012-12-18 11:07:05 +0100  8)  *   This program is distributed in the hope that it will be useful,
5be4bf17 (David A. Velasco 2012-12-18 11:07:05 +0100  9)  *   but WITHOUT ANY WARRANTY; without even the implied warranty of
5be4bf17 (David A. Velasco 2012-12-18 11:07:05 +0100 10)  *   MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
5be4bf17 (David A. Velasco 2012-12-18 11:07:05 +0100 11)  *   GNU General Public License for more details.
5be4bf17 (David A. Velasco 2012-12-18 11:07:05 +0100 12)  *
5be4bf17 (David A. Velasco 2012-12-18 11:07:05 +0100 13)  *   You should have received a copy of the GNU General Public License
5be4bf17 (David A. Velasco 2012-12-18 11:07:05 +0100 14)  *   along with this program.  If not, see <http://www.gnu.org/licenses/>.
5be4bf17 (David A. Velasco 2012-12-18 11:07:05 +0100 15)  *
5be4bf17 (David A. Velasco 2012-12-18 11:07:05 +0100 16)  */
5be4bf17 (David A. Velasco 2012-12-18 11:07:05 +0100 17) 
5be4bf17 (David A. Velasco 2012-12-18 11:07:05 +0100 18) package com.owncloud.android.operations;
5be4bf17 (David A. Velasco 2012-12-18 11:07:05 +0100 19) 
5be4bf17 (David A. Velasco 2012-12-18 11:07:05 +0100 20) import org.apache.commons.httpclient.HttpStatus;
5be4bf17 (David A. Velasco 2012-12-18 11:07:05 +0100 21) import org.apache.commons.httpclient.methods.HeadMethod;
5be4bf17 (David A. Velasco 2012-12-18 11:07:05 +0100 22) 
2946d8dd (David A. Velasco 2013-04-25 19:39:22 +0200 23) import com.owncloud.android.Log_OC;
2946d8dd (David A. Velasco 2013-04-25 19:39:22 +0200 24) 
5be4bf17 (David A. Velasco 2012-12-18 11:07:05 +0100 25) import eu.alefzero.webdav.WebdavClient;
5be4bf17 (David A. Velasco 2012-12-18 11:07:05 +0100 26) import android.content.Context;
5be4bf17 (David A. Velasco 2012-12-18 11:07:05 +0100 27) import android.net.ConnectivityManager;
5be4bf17 (David A. Velasco 2012-12-18 11:07:05 +0100 28) 
5be4bf17 (David A. Velasco 2012-12-18 11:07:05 +0100 29) /**
5be4bf17 (David A. Velasco 2012-12-18 11:07:05 +0100 30)  * Operation to check the existence or absence of a path in a remote server.
5be4bf17 (David A. Velasco 2012-12-18 11:07:05 +0100 31)  * 
5be4bf17 (David A. Velasco 2012-12-18 11:07:05 +0100 32)  * @author David A. Velasco
5be4bf17 (David A. Velasco 2012-12-18 11:07:05 +0100 33)  */
5be4bf17 (David A. Velasco 2012-12-18 11:07:05 +0100 34) public class ExistenceCheckOperation extends RemoteOperation {
5be4bf17 (David A. Velasco 2012-12-18 11:07:05 +0100 35)     
5be4bf17 (David A. Velasco 2012-12-18 11:07:05 +0100 36)     /** Maximum time to wait for a response from the server in MILLISECONDs.  */
5be4bf17 (David A. Velasco 2012-12-18 11:07:05 +0100 37)     public static final int TIMEOUT = 10000;
5be4bf17 (David A. Velasco 2012-12-18 11:07:05 +0100 38)     
5be4bf17 (David A. Velasco 2012-12-18 11:07:05 +0100 39)     private static final String TAG = ExistenceCheckOperation.class.getSimpleName();
5be4bf17 (David A. Velasco 2012-12-18 11:07:05 +0100 40)     
5be4bf17 (David A. Velasco 2012-12-18 11:07:05 +0100 41)     private String mPath;
5be4bf17 (David A. Velasco 2012-12-18 11:07:05 +0100 42)     private Context mContext;
5be4bf17 (David A. Velasco 2012-12-18 11:07:05 +0100 43)     private boolean mSuccessIfAbsent;
5be4bf17 (David A. Velasco 2012-12-18 11:07:05 +0100 44) 
5be4bf17 (David A. Velasco 2012-12-18 11:07:05 +0100 45)     
5be4bf17 (David A. Velasco 2012-12-18 11:07:05 +0100 46)     /**
5be4bf17 (David A. Velasco 2012-12-18 11:07:05 +0100 47)      * Full constructor. Success of the operation will depend upon the value of successIfAbsent.
5be4bf17 (David A. Velasco 2012-12-18 11:07:05 +0100 48)      * 
5be4bf17 (David A. Velasco 2012-12-18 11:07:05 +0100 49)      * @param path              Path to append to the URL owned by the client instance.
5be4bf17 (David A. Velasco 2012-12-18 11:07:05 +0100 50)      * @param context           Android application context.
5be4bf17 (David A. Velasco 2012-12-18 11:07:05 +0100 51)      * @param successIfAbsent   When 'true', the operation finishes in success if the path does NOT exist in the remote server (HTTP 404).
5be4bf17 (David A. Velasco 2012-12-18 11:07:05 +0100 52)      */
5be4bf17 (David A. Velasco 2012-12-18 11:07:05 +0100 53)     public ExistenceCheckOperation(String path, Context context, boolean successIfAbsent) {
5be4bf17 (David A. Velasco 2012-12-18 11:07:05 +0100 54)         mPath = (path != null) ? path : "";
5be4bf17 (David A. Velasco 2012-12-18 11:07:05 +0100 55)         mContext = context;
5be4bf17 (David A. Velasco 2012-12-18 11:07:05 +0100 56)         mSuccessIfAbsent = successIfAbsent;
5be4bf17 (David A. Velasco 2012-12-18 11:07:05 +0100 57)     }
5be4bf17 (David A. Velasco 2012-12-18 11:07:05 +0100 58)     
5be4bf17 (David A. Velasco 2012-12-18 11:07:05 +0100 59) 
5be4bf17 (David A. Velasco 2012-12-18 11:07:05 +0100 60) 	@Override
5be4bf17 (David A. Velasco 2012-12-18 11:07:05 +0100 61) 	protected RemoteOperationResult run(WebdavClient client) {
5be4bf17 (David A. Velasco 2012-12-18 11:07:05 +0100 62)         if (!isOnline()) {
5be4bf17 (David A. Velasco 2012-12-18 11:07:05 +0100 63)             return new RemoteOperationResult(RemoteOperationResult.ResultCode.NO_NETWORK_CONNECTION);
5be4bf17 (David A. Velasco 2012-12-18 11:07:05 +0100 64)         }
5be4bf17 (David A. Velasco 2012-12-18 11:07:05 +0100 65)         RemoteOperationResult result = null;
5be4bf17 (David A. Velasco 2012-12-18 11:07:05 +0100 66)         HeadMethod head = null;
5be4bf17 (David A. Velasco 2012-12-18 11:07:05 +0100 67)         try {
5be4bf17 (David A. Velasco 2012-12-18 11:07:05 +0100 68)             head = new HeadMethod(client.getBaseUri() + mPath);
5be4bf17 (David A. Velasco 2012-12-18 11:07:05 +0100 69)             int status = client.executeMethod(head, TIMEOUT, TIMEOUT);
5be4bf17 (David A. Velasco 2012-12-18 11:07:05 +0100 70)             client.exhaustResponse(head.getResponseBodyAsStream());
5be4bf17 (David A. Velasco 2012-12-18 11:07:05 +0100 71)             boolean success = (status == HttpStatus.SC_OK && !mSuccessIfAbsent) || (status == HttpStatus.SC_NOT_FOUND && mSuccessIfAbsent);
d2ee9062 (David A. Velasco 2013-07-30 13:02:13 +0200 72)             result = new RemoteOperationResult(success, status, head.getResponseHeaders());
2946d8dd (David A. Velasco 2013-04-25 19:39:22 +0200 73)             Log_OC.d(TAG, "Existence check for " + client.getBaseUri() + mPath + " targeting for " + (mSuccessIfAbsent ? " absence " : " existence ") + "finished with HTTP status " + status + (!success?"(FAIL)":""));
5be4bf17 (David A. Velasco 2012-12-18 11:07:05 +0100 74)             
5be4bf17 (David A. Velasco 2012-12-18 11:07:05 +0100 75)         } catch (Exception e) {
5be4bf17 (David A. Velasco 2012-12-18 11:07:05 +0100 76)             result = new RemoteOperationResult(e);
2946d8dd (David A. Velasco 2013-04-25 19:39:22 +0200 77)             Log_OC.e(TAG, "Existence check for " + client.getBaseUri() + mPath + " targeting for " + (mSuccessIfAbsent ? " absence " : " existence ") + ": " + result.getLogMessage(), result.getException());
5be4bf17 (David A. Velasco 2012-12-18 11:07:05 +0100 78)             
5be4bf17 (David A. Velasco 2012-12-18 11:07:05 +0100 79)         } finally {
5be4bf17 (David A. Velasco 2012-12-18 11:07:05 +0100 80)             if (head != null)
5be4bf17 (David A. Velasco 2012-12-18 11:07:05 +0100 81)                 head.releaseConnection();
5be4bf17 (David A. Velasco 2012-12-18 11:07:05 +0100 82)         }
5be4bf17 (David A. Velasco 2012-12-18 11:07:05 +0100 83)         return result;
5be4bf17 (David A. Velasco 2012-12-18 11:07:05 +0100 84) 	}
5be4bf17 (David A. Velasco 2012-12-18 11:07:05 +0100 85) 
5be4bf17 (David A. Velasco 2012-12-18 11:07:05 +0100 86)     private boolean isOnline() {
5be4bf17 (David A. Velasco 2012-12-18 11:07:05 +0100 87)         ConnectivityManager cm = (ConnectivityManager) mContext
5be4bf17 (David A. Velasco 2012-12-18 11:07:05 +0100 88)                 .getSystemService(Context.CONNECTIVITY_SERVICE);
5be4bf17 (David A. Velasco 2012-12-18 11:07:05 +0100 89)         return cm != null && cm.getActiveNetworkInfo() != null
5be4bf17 (David A. Velasco 2012-12-18 11:07:05 +0100 90)                 && cm.getActiveNetworkInfo().isConnectedOrConnecting();
5be4bf17 (David A. Velasco 2012-12-18 11:07:05 +0100 91)     }
5be4bf17 (David A. Velasco 2012-12-18 11:07:05 +0100 92) 
11b88e0f (David A. Velasco 2012-12-19 14:12:53 +0100 93) 
5be4bf17 (David A. Velasco 2012-12-18 11:07:05 +0100 94) }
