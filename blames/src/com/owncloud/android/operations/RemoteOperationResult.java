53b67429 (David A. Velasco  2012-09-12 16:16:56 +0200   1) /* ownCloud Android client application
53b67429 (David A. Velasco  2012-09-12 16:16:56 +0200   2)  *   Copyright (C) 2012 Bartek Przybylski
bb257ec7 (David A. Velasco  2013-02-07 18:45:10 +0100   3)  *   Copyright (C) 2012-2013 ownCloud Inc.
53b67429 (David A. Velasco  2012-09-12 16:16:56 +0200   4)  *
53b67429 (David A. Velasco  2012-09-12 16:16:56 +0200   5)  *   This program is free software: you can redistribute it and/or modify
bdc0332c (David A. Velasco  2013-04-17 12:26:13 +0200   6)  *   it under the terms of the GNU General Public License version 2,
bdc0332c (David A. Velasco  2013-04-17 12:26:13 +0200   7)  *   as published by the Free Software Foundation.
53b67429 (David A. Velasco  2012-09-12 16:16:56 +0200   8)  *
53b67429 (David A. Velasco  2012-09-12 16:16:56 +0200   9)  *   This program is distributed in the hope that it will be useful,
53b67429 (David A. Velasco  2012-09-12 16:16:56 +0200  10)  *   but WITHOUT ANY WARRANTY; without even the implied warranty of
53b67429 (David A. Velasco  2012-09-12 16:16:56 +0200  11)  *   MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
53b67429 (David A. Velasco  2012-09-12 16:16:56 +0200  12)  *   GNU General Public License for more details.
53b67429 (David A. Velasco  2012-09-12 16:16:56 +0200  13)  *
53b67429 (David A. Velasco  2012-09-12 16:16:56 +0200  14)  *   You should have received a copy of the GNU General Public License
53b67429 (David A. Velasco  2012-09-12 16:16:56 +0200  15)  *   along with this program.  If not, see <http://www.gnu.org/licenses/>.
53b67429 (David A. Velasco  2012-09-12 16:16:56 +0200  16)  *
53b67429 (David A. Velasco  2012-09-12 16:16:56 +0200  17)  */
53b67429 (David A. Velasco  2012-09-12 16:16:56 +0200  18) 
48f13c8a (David A. Velasco  2012-09-05 13:46:30 +0200  19) package com.owncloud.android.operations;
48f13c8a (David A. Velasco  2012-09-05 13:46:30 +0200  20) 
53b67429 (David A. Velasco  2012-09-12 16:16:56 +0200  21) import java.io.IOException;
67eb9210 (David A. Velasco  2012-10-22 15:11:59 +0200  22) import java.io.Serializable;
48f13c8a (David A. Velasco  2012-09-05 13:46:30 +0200  23) import java.net.MalformedURLException;
48f13c8a (David A. Velasco  2012-09-05 13:46:30 +0200  24) import java.net.SocketException;
48f13c8a (David A. Velasco  2012-09-05 13:46:30 +0200  25) import java.net.SocketTimeoutException;
48f13c8a (David A. Velasco  2012-09-05 13:46:30 +0200  26) import java.net.UnknownHostException;
48f13c8a (David A. Velasco  2012-09-05 13:46:30 +0200  27) 
48f13c8a (David A. Velasco  2012-09-05 13:46:30 +0200  28) import javax.net.ssl.SSLException;
48f13c8a (David A. Velasco  2012-09-05 13:46:30 +0200  29) 
48f13c8a (David A. Velasco  2012-09-05 13:46:30 +0200  30) import org.apache.commons.httpclient.ConnectTimeoutException;
d2ee9062 (David A. Velasco  2013-07-30 13:02:13 +0200  31) import org.apache.commons.httpclient.Header;
53b67429 (David A. Velasco  2012-09-12 16:16:56 +0200  32) import org.apache.commons.httpclient.HttpException;
48f13c8a (David A. Velasco  2012-09-05 13:46:30 +0200  33) import org.apache.commons.httpclient.HttpStatus;
6e469559 (David A. Velasco  2012-10-23 12:47:29 +0200  34) import org.apache.jackrabbit.webdav.DavException;
48f13c8a (David A. Velasco  2012-09-05 13:46:30 +0200  35) 
6ca1e170 (David A. Velasco  2013-06-18 14:04:51 +0200  36) import android.accounts.Account;
6ca1e170 (David A. Velasco  2013-06-18 14:04:51 +0200  37) import android.accounts.AccountsException;
6ca1e170 (David A. Velasco  2013-06-18 14:04:51 +0200  38) 
274dfd29 (David A. Velasco  2013-04-15 13:17:47 +0200  39) import com.owncloud.android.Log_OC;
6ca1e170 (David A. Velasco  2013-06-18 14:04:51 +0200  40) import com.owncloud.android.authentication.AccountUtils.AccountNotFoundException;
261aaf50 (David A. Velasco  2012-09-07 14:11:08 +0200  41) import com.owncloud.android.network.CertificateCombinedException;
261aaf50 (David A. Velasco  2012-09-07 14:11:08 +0200  42) 
53b67429 (David A. Velasco  2012-09-12 16:16:56 +0200  43) /**
53b67429 (David A. Velasco  2012-09-12 16:16:56 +0200  44)  * The result of a remote operation required to an ownCloud server.
53b67429 (David A. Velasco  2012-09-12 16:16:56 +0200  45)  * 
7392cfe5 (Matthias Baumann  2013-03-16 18:56:27 +0100  46)  * Provides a common classification of remote operation results for all the
7392cfe5 (Matthias Baumann  2013-03-16 18:56:27 +0100  47)  * application.
53b67429 (David A. Velasco  2012-09-12 16:16:56 +0200  48)  * 
53b67429 (David A. Velasco  2012-09-12 16:16:56 +0200  49)  * @author David A. Velasco
53b67429 (David A. Velasco  2012-09-12 16:16:56 +0200  50)  */
67eb9210 (David A. Velasco  2012-10-22 15:11:59 +0200  51) public class RemoteOperationResult implements Serializable {
7392cfe5 (Matthias Baumann  2013-03-16 18:56:27 +0100  52) 
c38a3b2e (David A. Velasco  2012-12-18 11:20:03 +0100  53)     /** Generated - should be refreshed every time the class changes!! */
b2f18e0f (David A. Velasco  2013-08-22 19:03:21 +0200  54)     private static final long serialVersionUID = -4415103901492836870L;
6ca1e170 (David A. Velasco  2013-06-18 14:04:51 +0200  55) 
5be4bf17 (David A. Velasco  2012-12-18 11:07:05 +0100  56)     
7392cfe5 (Matthias Baumann  2013-03-16 18:56:27 +0100  57)     private static final String TAG = "RemoteOperationResult";
2946d8dd (David A. Velasco  2013-04-25 19:39:22 +0200  58)     
53b67429 (David A. Velasco  2012-09-12 16:16:56 +0200  59)     public enum ResultCode { 
48f13c8a (David A. Velasco  2012-09-05 13:46:30 +0200  60)         OK,
48f13c8a (David A. Velasco  2012-09-05 13:46:30 +0200  61)         OK_SSL,
48f13c8a (David A. Velasco  2012-09-05 13:46:30 +0200  62)         OK_NO_SSL,
48f13c8a (David A. Velasco  2012-09-05 13:46:30 +0200  63)         UNHANDLED_HTTP_CODE,
67eb9210 (David A. Velasco  2012-10-22 15:11:59 +0200  64)         UNAUTHORIZED,        
48f13c8a (David A. Velasco  2012-09-05 13:46:30 +0200  65)         FILE_NOT_FOUND, 
48f13c8a (David A. Velasco  2012-09-05 13:46:30 +0200  66)         INSTANCE_NOT_CONFIGURED, 
48f13c8a (David A. Velasco  2012-09-05 13:46:30 +0200  67)         UNKNOWN_ERROR, 
48f13c8a (David A. Velasco  2012-09-05 13:46:30 +0200  68)         WRONG_CONNECTION,  
48f13c8a (David A. Velasco  2012-09-05 13:46:30 +0200  69)         TIMEOUT, 
48f13c8a (David A. Velasco  2012-09-05 13:46:30 +0200  70)         INCORRECT_ADDRESS, 
48f13c8a (David A. Velasco  2012-09-05 13:46:30 +0200  71)         HOST_NOT_AVAILABLE, 
48f13c8a (David A. Velasco  2012-09-05 13:46:30 +0200  72)         NO_NETWORK_CONNECTION, 
48f13c8a (David A. Velasco  2012-09-05 13:46:30 +0200  73)         SSL_ERROR,
bf3cf8cd (David A. Velasco  2012-09-13 17:07:17 +0200  74)         SSL_RECOVERABLE_PEER_UNVERIFIED,
68ce2e7a (David A. Velasco  2012-10-09 14:53:25 +0200  75)         BAD_OC_VERSION,
5af0fb44 (David A. Velasco  2012-10-26 11:34:20 +0200  76)         CANCELLED, 
5af0fb44 (David A. Velasco  2012-10-26 11:34:20 +0200  77)         INVALID_LOCAL_FILE_NAME, 
6c323657 (Bartek Przybylski 2012-10-27 12:20:52 +0200  78)         INVALID_OVERWRITE,
5be4bf17 (David A. Velasco  2012-12-18 11:07:05 +0100  79)         CONFLICT, 
c38a3b2e (David A. Velasco  2012-12-18 11:20:03 +0100  80)         OAUTH2_ERROR,
c38a3b2e (David A. Velasco  2012-12-18 11:20:03 +0100  81)         SYNC_CONFLICT,
c38a3b2e (David A. Velasco  2012-12-18 11:20:03 +0100  82)         LOCAL_STORAGE_FULL, 
c38a3b2e (David A. Velasco  2012-12-18 11:20:03 +0100  83)         LOCAL_STORAGE_NOT_MOVED, 
fb8a8a21 (David A. Velasco  2013-01-28 13:15:28 +0100  84)         LOCAL_STORAGE_NOT_COPIED, 
2946d8dd (David A. Velasco  2013-04-25 19:39:22 +0200  85)         OAUTH2_ERROR_ACCESS_DENIED,
6ca1e170 (David A. Velasco  2013-06-18 14:04:51 +0200  86)         QUOTA_EXCEEDED, 
6ca1e170 (David A. Velasco  2013-06-18 14:04:51 +0200  87)         ACCOUNT_NOT_FOUND, 
d7c6472b (David A. Velasco  2013-08-22 17:38:26 +0200  88)         ACCOUNT_EXCEPTION, 
b2f18e0f (David A. Velasco  2013-08-22 19:03:21 +0200  89)         ACCOUNT_NOT_NEW, 
b2f18e0f (David A. Velasco  2013-08-22 19:03:21 +0200  90)         ACCOUNT_NOT_THE_SAME
48f13c8a (David A. Velasco  2012-09-05 13:46:30 +0200  91)     }
261aaf50 (David A. Velasco  2012-09-07 14:11:08 +0200  92) 
48f13c8a (David A. Velasco  2012-09-05 13:46:30 +0200  93)     private boolean mSuccess = false;
48f13c8a (David A. Velasco  2012-09-05 13:46:30 +0200  94)     private int mHttpCode = -1;
48f13c8a (David A. Velasco  2012-09-05 13:46:30 +0200  95)     private Exception mException = null;
48f13c8a (David A. Velasco  2012-09-05 13:46:30 +0200  96)     private ResultCode mCode = ResultCode.UNKNOWN_ERROR;
d2ee9062 (David A. Velasco  2013-07-30 13:02:13 +0200  97)     private String mRedirectedLocation;
7392cfe5 (Matthias Baumann  2013-03-16 18:56:27 +0100  98) 
48f13c8a (David A. Velasco  2012-09-05 13:46:30 +0200  99)     public RemoteOperationResult(ResultCode code) {
48f13c8a (David A. Velasco  2012-09-05 13:46:30 +0200 100)         mCode = code;
48f13c8a (David A. Velasco  2012-09-05 13:46:30 +0200 101)         mSuccess = (code == ResultCode.OK || code == ResultCode.OK_SSL || code == ResultCode.OK_NO_SSL);
48f13c8a (David A. Velasco  2012-09-05 13:46:30 +0200 102)     }
7392cfe5 (Matthias Baumann  2013-03-16 18:56:27 +0100 103) 
78bcf721 (David A. Velasco  2013-08-07 19:13:00 +0200 104)     private RemoteOperationResult(boolean success, int httpCode) {
7392cfe5 (Matthias Baumann  2013-03-16 18:56:27 +0100 105)         mSuccess = success;
48f13c8a (David A. Velasco  2012-09-05 13:46:30 +0200 106)         mHttpCode = httpCode;
48f13c8a (David A. Velasco  2012-09-05 13:46:30 +0200 107) 
48f13c8a (David A. Velasco  2012-09-05 13:46:30 +0200 108)         if (success) {
48f13c8a (David A. Velasco  2012-09-05 13:46:30 +0200 109)             mCode = ResultCode.OK;
7392cfe5 (Matthias Baumann  2013-03-16 18:56:27 +0100 110) 
48f13c8a (David A. Velasco  2012-09-05 13:46:30 +0200 111)         } else if (httpCode > 0) {
48f13c8a (David A. Velasco  2012-09-05 13:46:30 +0200 112)             switch (httpCode) {
7392cfe5 (Matthias Baumann  2013-03-16 18:56:27 +0100 113)             case HttpStatus.SC_UNAUTHORIZED:
7392cfe5 (Matthias Baumann  2013-03-16 18:56:27 +0100 114)                 mCode = ResultCode.UNAUTHORIZED;
7392cfe5 (Matthias Baumann  2013-03-16 18:56:27 +0100 115)                 break;
7392cfe5 (Matthias Baumann  2013-03-16 18:56:27 +0100 116)             case HttpStatus.SC_NOT_FOUND:
7392cfe5 (Matthias Baumann  2013-03-16 18:56:27 +0100 117)                 mCode = ResultCode.FILE_NOT_FOUND;
7392cfe5 (Matthias Baumann  2013-03-16 18:56:27 +0100 118)                 break;
7392cfe5 (Matthias Baumann  2013-03-16 18:56:27 +0100 119)             case HttpStatus.SC_INTERNAL_SERVER_ERROR:
7392cfe5 (Matthias Baumann  2013-03-16 18:56:27 +0100 120)                 mCode = ResultCode.INSTANCE_NOT_CONFIGURED;
7392cfe5 (Matthias Baumann  2013-03-16 18:56:27 +0100 121)                 break;
7392cfe5 (Matthias Baumann  2013-03-16 18:56:27 +0100 122)             case HttpStatus.SC_CONFLICT:
7392cfe5 (Matthias Baumann  2013-03-16 18:56:27 +0100 123)                 mCode = ResultCode.CONFLICT;
7392cfe5 (Matthias Baumann  2013-03-16 18:56:27 +0100 124)                 break;
7392cfe5 (Matthias Baumann  2013-03-16 18:56:27 +0100 125)             case HttpStatus.SC_INSUFFICIENT_STORAGE:
7392cfe5 (Matthias Baumann  2013-03-16 18:56:27 +0100 126)                 mCode = ResultCode.QUOTA_EXCEEDED;
7392cfe5 (Matthias Baumann  2013-03-16 18:56:27 +0100 127)                 break;
7392cfe5 (Matthias Baumann  2013-03-16 18:56:27 +0100 128)             default:
7392cfe5 (Matthias Baumann  2013-03-16 18:56:27 +0100 129)                 mCode = ResultCode.UNHANDLED_HTTP_CODE;
52bc433b (David A. Velasco  2013-08-01 17:47:09 +0200 130)                 Log_OC.d(TAG, "RemoteOperationResult has processed UNHANDLED_HTTP_CODE: " + httpCode);
48f13c8a (David A. Velasco  2012-09-05 13:46:30 +0200 131)             }
48f13c8a (David A. Velasco  2012-09-05 13:46:30 +0200 132)         }
48f13c8a (David A. Velasco  2012-09-05 13:46:30 +0200 133)     }
d2ee9062 (David A. Velasco  2013-07-30 13:02:13 +0200 134)     
d2ee9062 (David A. Velasco  2013-07-30 13:02:13 +0200 135)     public RemoteOperationResult(boolean success, int httpCode, Header[] headers) {
d2ee9062 (David A. Velasco  2013-07-30 13:02:13 +0200 136)         this(success, httpCode);
d2ee9062 (David A. Velasco  2013-07-30 13:02:13 +0200 137)         if (headers != null) {
d2ee9062 (David A. Velasco  2013-07-30 13:02:13 +0200 138)             Header current;
d2ee9062 (David A. Velasco  2013-07-30 13:02:13 +0200 139)             for (int i=0; i<headers.length; i++) {
d2ee9062 (David A. Velasco  2013-07-30 13:02:13 +0200 140)                 current = headers[i];
d2ee9062 (David A. Velasco  2013-07-30 13:02:13 +0200 141)                 if ("Location".equals(current.getName())) {
d2ee9062 (David A. Velasco  2013-07-30 13:02:13 +0200 142)                     mRedirectedLocation = current.getValue();
d2ee9062 (David A. Velasco  2013-07-30 13:02:13 +0200 143)                     break;
d2ee9062 (David A. Velasco  2013-07-30 13:02:13 +0200 144)                 }
d2ee9062 (David A. Velasco  2013-07-30 13:02:13 +0200 145)             }
d2ee9062 (David A. Velasco  2013-07-30 13:02:13 +0200 146)         }
d2ee9062 (David A. Velasco  2013-07-30 13:02:13 +0200 147)     }    
7392cfe5 (Matthias Baumann  2013-03-16 18:56:27 +0100 148) 
48f13c8a (David A. Velasco  2012-09-05 13:46:30 +0200 149)     public RemoteOperationResult(Exception e) {
7392cfe5 (Matthias Baumann  2013-03-16 18:56:27 +0100 150)         mException = e;
7392cfe5 (Matthias Baumann  2013-03-16 18:56:27 +0100 151) 
68ce2e7a (David A. Velasco  2012-10-09 14:53:25 +0200 152)         if (e instanceof OperationCancelledException) {
68ce2e7a (David A. Velasco  2012-10-09 14:53:25 +0200 153)             mCode = ResultCode.CANCELLED;
7392cfe5 (Matthias Baumann  2013-03-16 18:56:27 +0100 154) 
7392cfe5 (Matthias Baumann  2013-03-16 18:56:27 +0100 155)         } else if (e instanceof SocketException) {
48f13c8a (David A. Velasco  2012-09-05 13:46:30 +0200 156)             mCode = ResultCode.WRONG_CONNECTION;
7392cfe5 (Matthias Baumann  2013-03-16 18:56:27 +0100 157) 
48f13c8a (David A. Velasco  2012-09-05 13:46:30 +0200 158)         } else if (e instanceof SocketTimeoutException) {
48f13c8a (David A. Velasco  2012-09-05 13:46:30 +0200 159)             mCode = ResultCode.TIMEOUT;
7392cfe5 (Matthias Baumann  2013-03-16 18:56:27 +0100 160) 
48f13c8a (David A. Velasco  2012-09-05 13:46:30 +0200 161)         } else if (e instanceof ConnectTimeoutException) {
48f13c8a (David A. Velasco  2012-09-05 13:46:30 +0200 162)             mCode = ResultCode.TIMEOUT;
7392cfe5 (Matthias Baumann  2013-03-16 18:56:27 +0100 163) 
48f13c8a (David A. Velasco  2012-09-05 13:46:30 +0200 164)         } else if (e instanceof MalformedURLException) {
48f13c8a (David A. Velasco  2012-09-05 13:46:30 +0200 165)             mCode = ResultCode.INCORRECT_ADDRESS;
7392cfe5 (Matthias Baumann  2013-03-16 18:56:27 +0100 166) 
48f13c8a (David A. Velasco  2012-09-05 13:46:30 +0200 167)         } else if (e instanceof UnknownHostException) {
48f13c8a (David A. Velasco  2012-09-05 13:46:30 +0200 168)             mCode = ResultCode.HOST_NOT_AVAILABLE;
7392cfe5 (Matthias Baumann  2013-03-16 18:56:27 +0100 169) 
6ca1e170 (David A. Velasco  2013-06-18 14:04:51 +0200 170)         } else if (e instanceof AccountNotFoundException) {
6ca1e170 (David A. Velasco  2013-06-18 14:04:51 +0200 171)             mCode = ResultCode.ACCOUNT_NOT_FOUND;
6ca1e170 (David A. Velasco  2013-06-18 14:04:51 +0200 172)             
6ca1e170 (David A. Velasco  2013-06-18 14:04:51 +0200 173)         } else if (e instanceof AccountsException) {
6ca1e170 (David A. Velasco  2013-06-18 14:04:51 +0200 174)             mCode = ResultCode.ACCOUNT_EXCEPTION;
6ca1e170 (David A. Velasco  2013-06-18 14:04:51 +0200 175)             
bf3cf8cd (David A. Velasco  2012-09-13 17:07:17 +0200 176)         } else if (e instanceof SSLException || e instanceof RuntimeException) {
bf3cf8cd (David A. Velasco  2012-09-13 17:07:17 +0200 177)             CertificateCombinedException se = getCertificateCombinedException(e);
bf3cf8cd (David A. Velasco  2012-09-13 17:07:17 +0200 178)             if (se != null) {
bf3cf8cd (David A. Velasco  2012-09-13 17:07:17 +0200 179)                 mException = se;
7392cfe5 (Matthias Baumann  2013-03-16 18:56:27 +0100 180)                 if (se.isRecoverable()) {
bf3cf8cd (David A. Velasco  2012-09-13 17:07:17 +0200 181)                     mCode = ResultCode.SSL_RECOVERABLE_PEER_UNVERIFIED;
bf3cf8cd (David A. Velasco  2012-09-13 17:07:17 +0200 182)                 }
7392cfe5 (Matthias Baumann  2013-03-16 18:56:27 +0100 183)             } else if (e instanceof RuntimeException) {
7392cfe5 (Matthias Baumann  2013-03-16 18:56:27 +0100 184)                 mCode = ResultCode.HOST_NOT_AVAILABLE;
7392cfe5 (Matthias Baumann  2013-03-16 18:56:27 +0100 185) 
7392cfe5 (Matthias Baumann  2013-03-16 18:56:27 +0100 186)             } else {
bf3cf8cd (David A. Velasco  2012-09-13 17:07:17 +0200 187)                 mCode = ResultCode.SSL_ERROR;
bf3cf8cd (David A. Velasco  2012-09-13 17:07:17 +0200 188)             }
7392cfe5 (Matthias Baumann  2013-03-16 18:56:27 +0100 189) 
48f13c8a (David A. Velasco  2012-09-05 13:46:30 +0200 190)         } else {
48f13c8a (David A. Velasco  2012-09-05 13:46:30 +0200 191)             mCode = ResultCode.UNKNOWN_ERROR;
48f13c8a (David A. Velasco  2012-09-05 13:46:30 +0200 192)         }
7392cfe5 (Matthias Baumann  2013-03-16 18:56:27 +0100 193) 
48f13c8a (David A. Velasco  2012-09-05 13:46:30 +0200 194)     }
7392cfe5 (Matthias Baumann  2013-03-16 18:56:27 +0100 195) 
48f13c8a (David A. Velasco  2012-09-05 13:46:30 +0200 196)     public boolean isSuccess() {
48f13c8a (David A. Velasco  2012-09-05 13:46:30 +0200 197)         return mSuccess;
48f13c8a (David A. Velasco  2012-09-05 13:46:30 +0200 198)     }
7392cfe5 (Matthias Baumann  2013-03-16 18:56:27 +0100 199) 
68ce2e7a (David A. Velasco  2012-10-09 14:53:25 +0200 200)     public boolean isCancelled() {
68ce2e7a (David A. Velasco  2012-10-09 14:53:25 +0200 201)         return mCode == ResultCode.CANCELLED;
68ce2e7a (David A. Velasco  2012-10-09 14:53:25 +0200 202)     }
7392cfe5 (Matthias Baumann  2013-03-16 18:56:27 +0100 203) 
48f13c8a (David A. Velasco  2012-09-05 13:46:30 +0200 204)     public int getHttpCode() {
48f13c8a (David A. Velasco  2012-09-05 13:46:30 +0200 205)         return mHttpCode;
48f13c8a (David A. Velasco  2012-09-05 13:46:30 +0200 206)     }
7392cfe5 (Matthias Baumann  2013-03-16 18:56:27 +0100 207) 
48f13c8a (David A. Velasco  2012-09-05 13:46:30 +0200 208)     public ResultCode getCode() {
48f13c8a (David A. Velasco  2012-09-05 13:46:30 +0200 209)         return mCode;
48f13c8a (David A. Velasco  2012-09-05 13:46:30 +0200 210)     }
7392cfe5 (Matthias Baumann  2013-03-16 18:56:27 +0100 211) 
48f13c8a (David A. Velasco  2012-09-05 13:46:30 +0200 212)     public Exception getException() {
48f13c8a (David A. Velasco  2012-09-05 13:46:30 +0200 213)         return mException;
48f13c8a (David A. Velasco  2012-09-05 13:46:30 +0200 214)     }
48f13c8a (David A. Velasco  2012-09-05 13:46:30 +0200 215) 
261aaf50 (David A. Velasco  2012-09-07 14:11:08 +0200 216)     public boolean isSslRecoverableException() {
bf3cf8cd (David A. Velasco  2012-09-13 17:07:17 +0200 217)         return mCode == ResultCode.SSL_RECOVERABLE_PEER_UNVERIFIED;
261aaf50 (David A. Velasco  2012-09-07 14:11:08 +0200 218)     }
7392cfe5 (Matthias Baumann  2013-03-16 18:56:27 +0100 219) 
bf3cf8cd (David A. Velasco  2012-09-13 17:07:17 +0200 220)     private CertificateCombinedException getCertificateCombinedException(Exception e) {
261aaf50 (David A. Velasco  2012-09-07 14:11:08 +0200 221)         CertificateCombinedException result = null;
bf3cf8cd (David A. Velasco  2012-09-13 17:07:17 +0200 222)         if (e instanceof CertificateCombinedException) {
7392cfe5 (Matthias Baumann  2013-03-16 18:56:27 +0100 223)             return (CertificateCombinedException) e;
bf3cf8cd (David A. Velasco  2012-09-13 17:07:17 +0200 224)         }
bf3cf8cd (David A. Velasco  2012-09-13 17:07:17 +0200 225)         Throwable cause = mException.getCause();
bf3cf8cd (David A. Velasco  2012-09-13 17:07:17 +0200 226)         Throwable previousCause = null;
bf3cf8cd (David A. Velasco  2012-09-13 17:07:17 +0200 227)         while (cause != null && cause != previousCause && !(cause instanceof CertificateCombinedException)) {
bf3cf8cd (David A. Velasco  2012-09-13 17:07:17 +0200 228)             previousCause = cause;
bf3cf8cd (David A. Velasco  2012-09-13 17:07:17 +0200 229)             cause = cause.getCause();
261aaf50 (David A. Velasco  2012-09-07 14:11:08 +0200 230)         }
bf3cf8cd (David A. Velasco  2012-09-13 17:07:17 +0200 231)         if (cause != null && cause instanceof CertificateCombinedException) {
7392cfe5 (Matthias Baumann  2013-03-16 18:56:27 +0100 232)             result = (CertificateCombinedException) cause;
bf3cf8cd (David A. Velasco  2012-09-13 17:07:17 +0200 233)         }
bf3cf8cd (David A. Velasco  2012-09-13 17:07:17 +0200 234)         return result;
261aaf50 (David A. Velasco  2012-09-07 14:11:08 +0200 235)     }
7392cfe5 (Matthias Baumann  2013-03-16 18:56:27 +0100 236) 
53b67429 (David A. Velasco  2012-09-12 16:16:56 +0200 237)     public String getLogMessage() {
7392cfe5 (Matthias Baumann  2013-03-16 18:56:27 +0100 238) 
53b67429 (David A. Velasco  2012-09-12 16:16:56 +0200 239)         if (mException != null) {
68ce2e7a (David A. Velasco  2012-10-09 14:53:25 +0200 240)             if (mException instanceof OperationCancelledException) {
68ce2e7a (David A. Velasco  2012-10-09 14:53:25 +0200 241)                 return "Operation cancelled by the caller";
7392cfe5 (Matthias Baumann  2013-03-16 18:56:27 +0100 242) 
7392cfe5 (Matthias Baumann  2013-03-16 18:56:27 +0100 243)             } else if (mException instanceof SocketException) {
53b67429 (David A. Velasco  2012-09-12 16:16:56 +0200 244)                 return "Socket exception";
7392cfe5 (Matthias Baumann  2013-03-16 18:56:27 +0100 245) 
53b67429 (David A. Velasco  2012-09-12 16:16:56 +0200 246)             } else if (mException instanceof SocketTimeoutException) {
53b67429 (David A. Velasco  2012-09-12 16:16:56 +0200 247)                 return "Socket timeout exception";
7392cfe5 (Matthias Baumann  2013-03-16 18:56:27 +0100 248) 
53b67429 (David A. Velasco  2012-09-12 16:16:56 +0200 249)             } else if (mException instanceof ConnectTimeoutException) {
53b67429 (David A. Velasco  2012-09-12 16:16:56 +0200 250)                 return "Connect timeout exception";
7392cfe5 (Matthias Baumann  2013-03-16 18:56:27 +0100 251) 
53b67429 (David A. Velasco  2012-09-12 16:16:56 +0200 252)             } else if (mException instanceof MalformedURLException) {
53b67429 (David A. Velasco  2012-09-12 16:16:56 +0200 253)                 return "Malformed URL exception";
7392cfe5 (Matthias Baumann  2013-03-16 18:56:27 +0100 254) 
53b67429 (David A. Velasco  2012-09-12 16:16:56 +0200 255)             } else if (mException instanceof UnknownHostException) {
53b67429 (David A. Velasco  2012-09-12 16:16:56 +0200 256)                 return "Unknown host exception";
7392cfe5 (Matthias Baumann  2013-03-16 18:56:27 +0100 257) 
d25af7ee (David A. Velasco  2012-10-24 19:39:55 +0200 258)             } else if (mException instanceof CertificateCombinedException) {
d25af7ee (David A. Velasco  2012-10-24 19:39:55 +0200 259)                 if (((CertificateCombinedException) mException).isRecoverable())
bf3cf8cd (David A. Velasco  2012-09-13 17:07:17 +0200 260)                     return "SSL recoverable exception";
bf3cf8cd (David A. Velasco  2012-09-13 17:07:17 +0200 261)                 else
bf3cf8cd (David A. Velasco  2012-09-13 17:07:17 +0200 262)                     return "SSL exception";
7392cfe5 (Matthias Baumann  2013-03-16 18:56:27 +0100 263) 
d25af7ee (David A. Velasco  2012-10-24 19:39:55 +0200 264)             } else if (mException instanceof SSLException) {
d25af7ee (David A. Velasco  2012-10-24 19:39:55 +0200 265)                 return "SSL exception";
53b67429 (David A. Velasco  2012-09-12 16:16:56 +0200 266) 
6e469559 (David A. Velasco  2012-10-23 12:47:29 +0200 267)             } else if (mException instanceof DavException) {
6e469559 (David A. Velasco  2012-10-23 12:47:29 +0200 268)                 return "Unexpected WebDAV exception";
6e469559 (David A. Velasco  2012-10-23 12:47:29 +0200 269) 
53b67429 (David A. Velasco  2012-09-12 16:16:56 +0200 270)             } else if (mException instanceof HttpException) {
53b67429 (David A. Velasco  2012-09-12 16:16:56 +0200 271)                 return "HTTP violation";
53b67429 (David A. Velasco  2012-09-12 16:16:56 +0200 272) 
53b67429 (David A. Velasco  2012-09-12 16:16:56 +0200 273)             } else if (mException instanceof IOException) {
53b67429 (David A. Velasco  2012-09-12 16:16:56 +0200 274)                 return "Unrecovered transport exception";
53b67429 (David A. Velasco  2012-09-12 16:16:56 +0200 275) 
6ca1e170 (David A. Velasco  2013-06-18 14:04:51 +0200 276)             } else if (mException instanceof AccountNotFoundException) {
6ca1e170 (David A. Velasco  2013-06-18 14:04:51 +0200 277)                 Account failedAccount = ((AccountNotFoundException)mException).getFailedAccount();
6ca1e170 (David A. Velasco  2013-06-18 14:04:51 +0200 278)                 return mException.getMessage() + " (" + (failedAccount != null ? failedAccount.name : "NULL") + ")";
6ca1e170 (David A. Velasco  2013-06-18 14:04:51 +0200 279)                 
6ca1e170 (David A. Velasco  2013-06-18 14:04:51 +0200 280)             } else if (mException instanceof AccountsException) {
6ca1e170 (David A. Velasco  2013-06-18 14:04:51 +0200 281)                 return "Exception while using account";
6ca1e170 (David A. Velasco  2013-06-18 14:04:51 +0200 282)                 
53b67429 (David A. Velasco  2012-09-12 16:16:56 +0200 283)             } else {
53b67429 (David A. Velasco  2012-09-12 16:16:56 +0200 284)                 return "Unexpected exception";
53b67429 (David A. Velasco  2012-09-12 16:16:56 +0200 285)             }
53b67429 (David A. Velasco  2012-09-12 16:16:56 +0200 286)         }
7392cfe5 (Matthias Baumann  2013-03-16 18:56:27 +0100 287) 
bf3cf8cd (David A. Velasco  2012-09-13 17:07:17 +0200 288)         if (mCode == ResultCode.INSTANCE_NOT_CONFIGURED) {
bf3cf8cd (David A. Velasco  2012-09-13 17:07:17 +0200 289)             return "The ownCloud server is not configured!";
7392cfe5 (Matthias Baumann  2013-03-16 18:56:27 +0100 290) 
bf3cf8cd (David A. Velasco  2012-09-13 17:07:17 +0200 291)         } else if (mCode == ResultCode.NO_NETWORK_CONNECTION) {
bf3cf8cd (David A. Velasco  2012-09-13 17:07:17 +0200 292)             return "No network connection";
7392cfe5 (Matthias Baumann  2013-03-16 18:56:27 +0100 293) 
bf3cf8cd (David A. Velasco  2012-09-13 17:07:17 +0200 294)         } else if (mCode == ResultCode.BAD_OC_VERSION) {
bf3cf8cd (David A. Velasco  2012-09-13 17:07:17 +0200 295)             return "No valid ownCloud version was found at the server";
7392cfe5 (Matthias Baumann  2013-03-16 18:56:27 +0100 296) 
c38a3b2e (David A. Velasco  2012-12-18 11:20:03 +0100 297)         } else if (mCode == ResultCode.LOCAL_STORAGE_FULL) {
c38a3b2e (David A. Velasco  2012-12-18 11:20:03 +0100 298)             return "Local storage full";
7392cfe5 (Matthias Baumann  2013-03-16 18:56:27 +0100 299) 
c38a3b2e (David A. Velasco  2012-12-18 11:20:03 +0100 300)         } else if (mCode == ResultCode.LOCAL_STORAGE_NOT_MOVED) {
c38a3b2e (David A. Velasco  2012-12-18 11:20:03 +0100 301)             return "Error while moving file to final directory";
d7c6472b (David A. Velasco  2013-08-22 17:38:26 +0200 302) 
d7c6472b (David A. Velasco  2013-08-22 17:38:26 +0200 303)         } else if (mCode == ResultCode.ACCOUNT_NOT_NEW) {
d7c6472b (David A. Velasco  2013-08-22 17:38:26 +0200 304)             return "Account already existing when creating a new one";
b2f18e0f (David A. Velasco  2013-08-22 19:03:21 +0200 305) 
b2f18e0f (David A. Velasco  2013-08-22 19:03:21 +0200 306)         } else if (mCode == ResultCode.ACCOUNT_NOT_THE_SAME) {
b2f18e0f (David A. Velasco  2013-08-22 19:03:21 +0200 307)             return "Authenticated with a different account than the one updating";
bf3cf8cd (David A. Velasco  2012-09-13 17:07:17 +0200 308)         }
7392cfe5 (Matthias Baumann  2013-03-16 18:56:27 +0100 309) 
7392cfe5 (Matthias Baumann  2013-03-16 18:56:27 +0100 310)         return "Operation finished with HTTP status code " + mHttpCode + " (" + (isSuccess() ? "success" : "fail") + ")";
7392cfe5 (Matthias Baumann  2013-03-16 18:56:27 +0100 311) 
53b67429 (David A. Velasco  2012-09-12 16:16:56 +0200 312)     }
261aaf50 (David A. Velasco  2012-09-07 14:11:08 +0200 313) 
30ab3774 (David A. Velasco  2013-06-10 12:51:48 +0200 314)     public boolean isServerFail() {
30ab3774 (David A. Velasco  2013-06-10 12:51:48 +0200 315)         return (mHttpCode >= HttpStatus.SC_INTERNAL_SERVER_ERROR);
30ab3774 (David A. Velasco  2013-06-10 12:51:48 +0200 316)     }
30ab3774 (David A. Velasco  2013-06-10 12:51:48 +0200 317) 
30ab3774 (David A. Velasco  2013-06-10 12:51:48 +0200 318)     public boolean isException() {
30ab3774 (David A. Velasco  2013-06-10 12:51:48 +0200 319)         return (mException != null);
30ab3774 (David A. Velasco  2013-06-10 12:51:48 +0200 320)     }
30ab3774 (David A. Velasco  2013-06-10 12:51:48 +0200 321) 
d2ee9062 (David A. Velasco  2013-07-30 13:02:13 +0200 322)     public boolean isTemporalRedirection() {
7ed9e800 (masensio          2013-08-21 18:31:57 +0200 323)         return (mHttpCode == 302 || mHttpCode == 307);
d2ee9062 (David A. Velasco  2013-07-30 13:02:13 +0200 324)     }
d2ee9062 (David A. Velasco  2013-07-30 13:02:13 +0200 325) 
d2ee9062 (David A. Velasco  2013-07-30 13:02:13 +0200 326)     public String getRedirectedLocation() {
d2ee9062 (David A. Velasco  2013-07-30 13:02:13 +0200 327)         return mRedirectedLocation;
d2ee9062 (David A. Velasco  2013-07-30 13:02:13 +0200 328)     }
9724afda (masensio          2013-08-21 18:12:28 +0200 329)     
7ed9e800 (masensio          2013-08-21 18:31:57 +0200 330)     public boolean isIdPRedirection() {
b2f18e0f (David A. Velasco  2013-08-22 19:03:21 +0200 331)         return (mRedirectedLocation != null &&
b2f18e0f (David A. Velasco  2013-08-22 19:03:21 +0200 332)                 (mRedirectedLocation.toUpperCase().contains("SAML") || 
b2f18e0f (David A. Velasco  2013-08-22 19:03:21 +0200 333)                 mRedirectedLocation.toLowerCase().contains("wayf")));
7ed9e800 (masensio          2013-08-21 18:31:57 +0200 334)     }
7ed9e800 (masensio          2013-08-21 18:31:57 +0200 335) 
48f13c8a (David A. Velasco  2012-09-05 13:46:30 +0200 336) }
