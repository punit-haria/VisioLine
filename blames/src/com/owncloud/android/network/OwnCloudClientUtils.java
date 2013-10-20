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
48f13c8a (David A. Velasco 2012-09-05 13:46:30 +0200  17) package com.owncloud.android.network;
48f13c8a (David A. Velasco 2012-09-05 13:46:30 +0200  18) 
48f13c8a (David A. Velasco 2012-09-05 13:46:30 +0200  19) import java.io.File;
48f13c8a (David A. Velasco 2012-09-05 13:46:30 +0200  20) import java.io.FileInputStream;
48f13c8a (David A. Velasco 2012-09-05 13:46:30 +0200  21) import java.io.FileOutputStream;
48f13c8a (David A. Velasco 2012-09-05 13:46:30 +0200  22) import java.io.IOException;
48f13c8a (David A. Velasco 2012-09-05 13:46:30 +0200  23) import java.io.InputStream;
48f13c8a (David A. Velasco 2012-09-05 13:46:30 +0200  24) import java.security.GeneralSecurityException;
48f13c8a (David A. Velasco 2012-09-05 13:46:30 +0200  25) import java.security.KeyStore;
48f13c8a (David A. Velasco 2012-09-05 13:46:30 +0200  26) import java.security.KeyStoreException;
48f13c8a (David A. Velasco 2012-09-05 13:46:30 +0200  27) import java.security.NoSuchAlgorithmException;
48f13c8a (David A. Velasco 2012-09-05 13:46:30 +0200  28) import java.security.cert.Certificate;
48f13c8a (David A. Velasco 2012-09-05 13:46:30 +0200  29) import java.security.cert.CertificateException;
48f13c8a (David A. Velasco 2012-09-05 13:46:30 +0200  30) 
48f13c8a (David A. Velasco 2012-09-05 13:46:30 +0200  31) import javax.net.ssl.SSLContext;
48f13c8a (David A. Velasco 2012-09-05 13:46:30 +0200  32) import javax.net.ssl.TrustManager;
48f13c8a (David A. Velasco 2012-09-05 13:46:30 +0200  33) 
48f13c8a (David A. Velasco 2012-09-05 13:46:30 +0200  34) import org.apache.commons.httpclient.MultiThreadedHttpConnectionManager;
78bcf721 (David A. Velasco 2013-08-07 19:13:00 +0200  35) import org.apache.commons.httpclient.methods.GetMethod;
48f13c8a (David A. Velasco 2012-09-05 13:46:30 +0200  36) import org.apache.commons.httpclient.protocol.Protocol;
261aaf50 (David A. Velasco 2012-09-07 14:11:08 +0200  37) import org.apache.http.conn.ssl.BrowserCompatHostnameVerifier;
261aaf50 (David A. Velasco 2012-09-07 14:11:08 +0200  38) import org.apache.http.conn.ssl.X509HostnameVerifier;
48f13c8a (David A. Velasco 2012-09-05 13:46:30 +0200  39) 
69d6d821 (David A. Velasco 2013-03-21 12:21:48 +0100  40) import com.owncloud.android.authentication.AccountAuthenticator;
c4ac05de (David A. Velasco 2013-06-18 11:34:08 +0200  41) import com.owncloud.android.authentication.AccountUtils;
6ca1e170 (David A. Velasco 2013-06-18 14:04:51 +0200  42) import com.owncloud.android.authentication.AccountUtils.AccountNotFoundException;
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100  43) import com.owncloud.android.Log_OC;
48f13c8a (David A. Velasco 2012-09-05 13:46:30 +0200  44) 
48f13c8a (David A. Velasco 2012-09-05 13:46:30 +0200  45) import eu.alefzero.webdav.WebdavClient;
48f13c8a (David A. Velasco 2012-09-05 13:46:30 +0200  46) 
48f13c8a (David A. Velasco 2012-09-05 13:46:30 +0200  47) import android.accounts.Account;
48f13c8a (David A. Velasco 2012-09-05 13:46:30 +0200  48) import android.accounts.AccountManager;
73f8797b (David A. Velasco 2013-01-17 13:01:51 +0100  49) import android.accounts.AccountManagerFuture;
11b88e0f (David A. Velasco 2012-12-19 14:12:53 +0100  50) import android.accounts.AuthenticatorException;
11b88e0f (David A. Velasco 2012-12-19 14:12:53 +0100  51) import android.accounts.OperationCanceledException;
73f8797b (David A. Velasco 2013-01-17 13:01:51 +0100  52) import android.app.Activity;
48f13c8a (David A. Velasco 2012-09-05 13:46:30 +0200  53) import android.content.Context;
48f13c8a (David A. Velasco 2012-09-05 13:46:30 +0200  54) import android.net.Uri;
73f8797b (David A. Velasco 2013-01-17 13:01:51 +0100  55) import android.os.Bundle;
48f13c8a (David A. Velasco 2012-09-05 13:46:30 +0200  56) 
48f13c8a (David A. Velasco 2012-09-05 13:46:30 +0200  57) public class OwnCloudClientUtils {
48f13c8a (David A. Velasco 2012-09-05 13:46:30 +0200  58)     
11b88e0f (David A. Velasco 2012-12-19 14:12:53 +0100  59)     final private static String TAG = OwnCloudClientUtils.class.getSimpleName();
48f13c8a (David A. Velasco 2012-09-05 13:46:30 +0200  60)     
48f13c8a (David A. Velasco 2012-09-05 13:46:30 +0200  61)     /** Default timeout for waiting data from the server */
48f13c8a (David A. Velasco 2012-09-05 13:46:30 +0200  62)     public static final int DEFAULT_DATA_TIMEOUT = 60000;
48f13c8a (David A. Velasco 2012-09-05 13:46:30 +0200  63)     
48f13c8a (David A. Velasco 2012-09-05 13:46:30 +0200  64)     /** Default timeout for establishing a connection */
48f13c8a (David A. Velasco 2012-09-05 13:46:30 +0200  65)     public static final int DEFAULT_CONNECTION_TIMEOUT = 60000;
48f13c8a (David A. Velasco 2012-09-05 13:46:30 +0200  66) 
48f13c8a (David A. Velasco 2012-09-05 13:46:30 +0200  67)     /** Connection manager for all the WebdavClients */
48f13c8a (David A. Velasco 2012-09-05 13:46:30 +0200  68)     private static MultiThreadedHttpConnectionManager mConnManager = null;
48f13c8a (David A. Velasco 2012-09-05 13:46:30 +0200  69)     
48f13c8a (David A. Velasco 2012-09-05 13:46:30 +0200  70)     private static Protocol mDefaultHttpsProtocol = null;
48f13c8a (David A. Velasco 2012-09-05 13:46:30 +0200  71) 
48f13c8a (David A. Velasco 2012-09-05 13:46:30 +0200  72)     private static AdvancedSslSocketFactory mAdvancedSslSocketFactory = null;
261aaf50 (David A. Velasco 2012-09-07 14:11:08 +0200  73) 
261aaf50 (David A. Velasco 2012-09-07 14:11:08 +0200  74)     private static X509HostnameVerifier mHostnameVerifier = null;
48f13c8a (David A. Velasco 2012-09-05 13:46:30 +0200  75)     
48f13c8a (David A. Velasco 2012-09-05 13:46:30 +0200  76)     
48f13c8a (David A. Velasco 2012-09-05 13:46:30 +0200  77)     /**
48f13c8a (David A. Velasco 2012-09-05 13:46:30 +0200  78)      * Creates a WebdavClient setup for an ownCloud account
48f13c8a (David A. Velasco 2012-09-05 13:46:30 +0200  79)      * 
11b88e0f (David A. Velasco 2012-12-19 14:12:53 +0100  80)      * Do not call this method from the main thread.
11b88e0f (David A. Velasco 2012-12-19 14:12:53 +0100  81)      * 
11b88e0f (David A. Velasco 2012-12-19 14:12:53 +0100  82)      * @param account                       The ownCloud account
11b88e0f (David A. Velasco 2012-12-19 14:12:53 +0100  83)      * @param appContext                    Android application context
11b88e0f (David A. Velasco 2012-12-19 14:12:53 +0100  84)      * @return                              A WebdavClient object ready to be used
11b88e0f (David A. Velasco 2012-12-19 14:12:53 +0100  85)      * @throws AuthenticatorException       If the authenticator failed to get the authorization token for the account.
11b88e0f (David A. Velasco 2012-12-19 14:12:53 +0100  86)      * @throws OperationCanceledException   If the authenticator operation was cancelled while getting the authorization token for the account. 
11b88e0f (David A. Velasco 2012-12-19 14:12:53 +0100  87)      * @throws IOException                  If there was some I/O error while getting the authorization token for the account.
6ca1e170 (David A. Velasco 2013-06-18 14:04:51 +0200  88)      * @throws AccountNotFoundException     If 'account' is unknown for the AccountManager
48f13c8a (David A. Velasco 2012-09-05 13:46:30 +0200  89)      */
6ca1e170 (David A. Velasco 2013-06-18 14:04:51 +0200  90)     public static WebdavClient createOwnCloudClient (Account account, Context appContext) throws OperationCanceledException, AuthenticatorException, IOException, AccountNotFoundException {
2946d8dd (David A. Velasco 2013-04-25 19:39:22 +0200  91)         //Log_OC.d(TAG, "Creating WebdavClient associated to " + account.name);
48f13c8a (David A. Velasco 2012-09-05 13:46:30 +0200  92)        
11b88e0f (David A. Velasco 2012-12-19 14:12:53 +0100  93)         Uri uri = Uri.parse(AccountUtils.constructFullURLForAccount(appContext, account));
11b88e0f (David A. Velasco 2012-12-19 14:12:53 +0100  94)         AccountManager am = AccountManager.get(appContext);
78bcf721 (David A. Velasco 2013-08-07 19:13:00 +0200  95)         boolean isOauth2 = am.getUserData(account, AccountAuthenticator.KEY_SUPPORTS_OAUTH2) != null;   // TODO avoid calling to getUserData here
78bcf721 (David A. Velasco 2013-08-07 19:13:00 +0200  96)         boolean isSamlSso = am.getUserData(account, AccountAuthenticator.KEY_SUPPORTS_SAML_WEB_SSO) != null;
78bcf721 (David A. Velasco 2013-08-07 19:13:00 +0200  97)         WebdavClient client = createOwnCloudClient(uri, appContext, !isSamlSso);
78bcf721 (David A. Velasco 2013-08-07 19:13:00 +0200  98)         if (isOauth2) {    
11b88e0f (David A. Velasco 2012-12-19 14:12:53 +0100  99)             String accessToken = am.blockingGetAuthToken(account, AccountAuthenticator.AUTH_TOKEN_TYPE_ACCESS_TOKEN, false);
11b88e0f (David A. Velasco 2012-12-19 14:12:53 +0100 100)             client.setBearerCredentials(accessToken);   // TODO not assume that the access token is a bearer token
48f13c8a (David A. Velasco 2012-09-05 13:46:30 +0200 101)         
78bcf721 (David A. Velasco 2013-08-07 19:13:00 +0200 102)         } else if (isSamlSso) {    // TODO avoid a call to getUserData here
52bc433b (David A. Velasco 2013-08-01 17:47:09 +0200 103)             String accessToken = am.blockingGetAuthToken(account, AccountAuthenticator.AUTH_TOKEN_TYPE_SAML_WEB_SSO_SESSION_COOKIE, false);
52bc433b (David A. Velasco 2013-08-01 17:47:09 +0200 104)             client.setSsoSessionCookie(accessToken);
52bc433b (David A. Velasco 2013-08-01 17:47:09 +0200 105)             
11b88e0f (David A. Velasco 2012-12-19 14:12:53 +0100 106)         } else {
11b88e0f (David A. Velasco 2012-12-19 14:12:53 +0100 107)             String username = account.name.substring(0, account.name.lastIndexOf('@'));
11b88e0f (David A. Velasco 2012-12-19 14:12:53 +0100 108)             //String password = am.getPassword(account);
11b88e0f (David A. Velasco 2012-12-19 14:12:53 +0100 109)             String password = am.blockingGetAuthToken(account, AccountAuthenticator.AUTH_TOKEN_TYPE_PASSWORD, false);
11b88e0f (David A. Velasco 2012-12-19 14:12:53 +0100 110)             client.setBasicCredentials(username, password);
11b88e0f (David A. Velasco 2012-12-19 14:12:53 +0100 111)         }
48f13c8a (David A. Velasco 2012-09-05 13:46:30 +0200 112)         
48f13c8a (David A. Velasco 2012-09-05 13:46:30 +0200 113)         return client;
48f13c8a (David A. Velasco 2012-09-05 13:46:30 +0200 114)     }
48f13c8a (David A. Velasco 2012-09-05 13:46:30 +0200 115)     
48f13c8a (David A. Velasco 2012-09-05 13:46:30 +0200 116)     
6ca1e170 (David A. Velasco 2013-06-18 14:04:51 +0200 117)     public static WebdavClient createOwnCloudClient (Account account, Context appContext, Activity currentActivity) throws OperationCanceledException, AuthenticatorException, IOException, AccountNotFoundException {
73f8797b (David A. Velasco 2013-01-17 13:01:51 +0100 118)         Uri uri = Uri.parse(AccountUtils.constructFullURLForAccount(appContext, account));
73f8797b (David A. Velasco 2013-01-17 13:01:51 +0100 119)         AccountManager am = AccountManager.get(appContext);
78bcf721 (David A. Velasco 2013-08-07 19:13:00 +0200 120)         boolean isOauth2 = am.getUserData(account, AccountAuthenticator.KEY_SUPPORTS_OAUTH2) != null;   // TODO avoid calling to getUserData here
78bcf721 (David A. Velasco 2013-08-07 19:13:00 +0200 121)         boolean isSamlSso = am.getUserData(account, AccountAuthenticator.KEY_SUPPORTS_SAML_WEB_SSO) != null;
78bcf721 (David A. Velasco 2013-08-07 19:13:00 +0200 122)         WebdavClient client = createOwnCloudClient(uri, appContext, !isSamlSso);
78bcf721 (David A. Velasco 2013-08-07 19:13:00 +0200 123)         
78bcf721 (David A. Velasco 2013-08-07 19:13:00 +0200 124)         if (isOauth2) {    // TODO avoid a call to getUserData here
73f8797b (David A. Velasco 2013-01-17 13:01:51 +0100 125)             AccountManagerFuture<Bundle> future =  am.getAuthToken(account, AccountAuthenticator.AUTH_TOKEN_TYPE_ACCESS_TOKEN, null, currentActivity, null, null);
73f8797b (David A. Velasco 2013-01-17 13:01:51 +0100 126)             Bundle result = future.getResult();
73f8797b (David A. Velasco 2013-01-17 13:01:51 +0100 127)             String accessToken = result.getString(AccountManager.KEY_AUTHTOKEN);
73f8797b (David A. Velasco 2013-01-17 13:01:51 +0100 128)             if (accessToken == null) throw new AuthenticatorException("WTF!");
73f8797b (David A. Velasco 2013-01-17 13:01:51 +0100 129)             client.setBearerCredentials(accessToken);   // TODO not assume that the access token is a bearer token
52bc433b (David A. Velasco 2013-08-01 17:47:09 +0200 130) 
78bcf721 (David A. Velasco 2013-08-07 19:13:00 +0200 131)         } else if (isSamlSso) {    // TODO avoid a call to getUserData here
52bc433b (David A. Velasco 2013-08-01 17:47:09 +0200 132)             AccountManagerFuture<Bundle> future =  am.getAuthToken(account, AccountAuthenticator.AUTH_TOKEN_TYPE_SAML_WEB_SSO_SESSION_COOKIE, null, currentActivity, null, null);
52bc433b (David A. Velasco 2013-08-01 17:47:09 +0200 133)             Bundle result = future.getResult();
52bc433b (David A. Velasco 2013-08-01 17:47:09 +0200 134)             String accessToken = result.getString(AccountManager.KEY_AUTHTOKEN);
52bc433b (David A. Velasco 2013-08-01 17:47:09 +0200 135)             if (accessToken == null) throw new AuthenticatorException("WTF!");
52bc433b (David A. Velasco 2013-08-01 17:47:09 +0200 136)             client.setSsoSessionCookie(accessToken);
52bc433b (David A. Velasco 2013-08-01 17:47:09 +0200 137) 
73f8797b (David A. Velasco 2013-01-17 13:01:51 +0100 138)         } else {
73f8797b (David A. Velasco 2013-01-17 13:01:51 +0100 139)             String username = account.name.substring(0, account.name.lastIndexOf('@'));
73f8797b (David A. Velasco 2013-01-17 13:01:51 +0100 140)             //String password = am.getPassword(account);
73f8797b (David A. Velasco 2013-01-17 13:01:51 +0100 141)             //String password = am.blockingGetAuthToken(account, AccountAuthenticator.AUTH_TOKEN_TYPE_PASSWORD, false);
73f8797b (David A. Velasco 2013-01-17 13:01:51 +0100 142)             AccountManagerFuture<Bundle> future =  am.getAuthToken(account, AccountAuthenticator.AUTH_TOKEN_TYPE_PASSWORD, null, currentActivity, null, null);
73f8797b (David A. Velasco 2013-01-17 13:01:51 +0100 143)             Bundle result = future.getResult();
73f8797b (David A. Velasco 2013-01-17 13:01:51 +0100 144)             String password = result.getString(AccountManager.KEY_AUTHTOKEN);
73f8797b (David A. Velasco 2013-01-17 13:01:51 +0100 145)             client.setBasicCredentials(username, password);
73f8797b (David A. Velasco 2013-01-17 13:01:51 +0100 146)         }
73f8797b (David A. Velasco 2013-01-17 13:01:51 +0100 147)         
73f8797b (David A. Velasco 2013-01-17 13:01:51 +0100 148)         return client;
73f8797b (David A. Velasco 2013-01-17 13:01:51 +0100 149)     }
48f13c8a (David A. Velasco 2012-09-05 13:46:30 +0200 150)     
48f13c8a (David A. Velasco 2012-09-05 13:46:30 +0200 151)     /**
48f13c8a (David A. Velasco 2012-09-05 13:46:30 +0200 152)      * Creates a WebdavClient to access a URL and sets the desired parameters for ownCloud client connections.
48f13c8a (David A. Velasco 2012-09-05 13:46:30 +0200 153)      * 
48f13c8a (David A. Velasco 2012-09-05 13:46:30 +0200 154)      * @param uri       URL to the ownCloud server
48f13c8a (David A. Velasco 2012-09-05 13:46:30 +0200 155)      * @param context   Android context where the WebdavClient is being created.
48f13c8a (David A. Velasco 2012-09-05 13:46:30 +0200 156)      * @return          A WebdavClient object ready to be used
48f13c8a (David A. Velasco 2012-09-05 13:46:30 +0200 157)      */
d2ee9062 (David A. Velasco 2013-07-30 13:02:13 +0200 158)     public static WebdavClient createOwnCloudClient(Uri uri, Context context, boolean followRedirects) {
48f13c8a (David A. Velasco 2012-09-05 13:46:30 +0200 159)         try {
48f13c8a (David A. Velasco 2012-09-05 13:46:30 +0200 160)             registerAdvancedSslContext(true, context);
48f13c8a (David A. Velasco 2012-09-05 13:46:30 +0200 161)         }  catch (GeneralSecurityException e) {
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100 162)             Log_OC.e(TAG, "Advanced SSL Context could not be loaded. Default SSL management in the system will be used for HTTPS connections", e);
48f13c8a (David A. Velasco 2012-09-05 13:46:30 +0200 163)             
48f13c8a (David A. Velasco 2012-09-05 13:46:30 +0200 164)         } catch (IOException e) {
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100 165)             Log_OC.e(TAG, "The local server truststore could not be read. Default SSL management in the system will be used for HTTPS connections", e);
48f13c8a (David A. Velasco 2012-09-05 13:46:30 +0200 166)         }
48f13c8a (David A. Velasco 2012-09-05 13:46:30 +0200 167)         
48f13c8a (David A. Velasco 2012-09-05 13:46:30 +0200 168)         WebdavClient client = new WebdavClient(getMultiThreadedConnManager());
48f13c8a (David A. Velasco 2012-09-05 13:46:30 +0200 169)         
48f13c8a (David A. Velasco 2012-09-05 13:46:30 +0200 170)         client.setDefaultTimeouts(DEFAULT_DATA_TIMEOUT, DEFAULT_CONNECTION_TIMEOUT);
48f13c8a (David A. Velasco 2012-09-05 13:46:30 +0200 171)         client.setBaseUri(uri);
d2ee9062 (David A. Velasco 2013-07-30 13:02:13 +0200 172)         client.setFollowRedirects(followRedirects);
48f13c8a (David A. Velasco 2012-09-05 13:46:30 +0200 173)         
48f13c8a (David A. Velasco 2012-09-05 13:46:30 +0200 174)         return client;
48f13c8a (David A. Velasco 2012-09-05 13:46:30 +0200 175)     }
48f13c8a (David A. Velasco 2012-09-05 13:46:30 +0200 176)     
48f13c8a (David A. Velasco 2012-09-05 13:46:30 +0200 177)     
48f13c8a (David A. Velasco 2012-09-05 13:46:30 +0200 178)     /**
48f13c8a (David A. Velasco 2012-09-05 13:46:30 +0200 179)      * Registers or unregisters the proper components for advanced SSL handling.
48f13c8a (David A. Velasco 2012-09-05 13:46:30 +0200 180)      * @throws IOException 
48f13c8a (David A. Velasco 2012-09-05 13:46:30 +0200 181)      */
48f13c8a (David A. Velasco 2012-09-05 13:46:30 +0200 182)     private static void registerAdvancedSslContext(boolean register, Context context) throws GeneralSecurityException, IOException {
48f13c8a (David A. Velasco 2012-09-05 13:46:30 +0200 183)         Protocol pr = null;
48f13c8a (David A. Velasco 2012-09-05 13:46:30 +0200 184)         try {
48f13c8a (David A. Velasco 2012-09-05 13:46:30 +0200 185)             pr = Protocol.getProtocol("https");
48f13c8a (David A. Velasco 2012-09-05 13:46:30 +0200 186)             if (pr != null && mDefaultHttpsProtocol == null) {
48f13c8a (David A. Velasco 2012-09-05 13:46:30 +0200 187)                 mDefaultHttpsProtocol = pr;
48f13c8a (David A. Velasco 2012-09-05 13:46:30 +0200 188)             }
48f13c8a (David A. Velasco 2012-09-05 13:46:30 +0200 189)         } catch (IllegalStateException e) {
48f13c8a (David A. Velasco 2012-09-05 13:46:30 +0200 190)             // nothing to do here; really
48f13c8a (David A. Velasco 2012-09-05 13:46:30 +0200 191)         }
48f13c8a (David A. Velasco 2012-09-05 13:46:30 +0200 192)         boolean isRegistered = (pr != null && pr.getSocketFactory() instanceof AdvancedSslSocketFactory);
48f13c8a (David A. Velasco 2012-09-05 13:46:30 +0200 193)         if (register && !isRegistered) {
48f13c8a (David A. Velasco 2012-09-05 13:46:30 +0200 194)             Protocol.registerProtocol("https", new Protocol("https", getAdvancedSslSocketFactory(context), 443));
48f13c8a (David A. Velasco 2012-09-05 13:46:30 +0200 195)             
48f13c8a (David A. Velasco 2012-09-05 13:46:30 +0200 196)         } else if (!register && isRegistered) {
48f13c8a (David A. Velasco 2012-09-05 13:46:30 +0200 197)             if (mDefaultHttpsProtocol != null) {
48f13c8a (David A. Velasco 2012-09-05 13:46:30 +0200 198)                 Protocol.registerProtocol("https", mDefaultHttpsProtocol);
48f13c8a (David A. Velasco 2012-09-05 13:46:30 +0200 199)             }
48f13c8a (David A. Velasco 2012-09-05 13:46:30 +0200 200)         }
48f13c8a (David A. Velasco 2012-09-05 13:46:30 +0200 201)     }
48f13c8a (David A. Velasco 2012-09-05 13:46:30 +0200 202)     
261aaf50 (David A. Velasco 2012-09-07 14:11:08 +0200 203)     public static AdvancedSslSocketFactory getAdvancedSslSocketFactory(Context context) throws GeneralSecurityException, IOException {
48f13c8a (David A. Velasco 2012-09-05 13:46:30 +0200 204)         if (mAdvancedSslSocketFactory  == null) {
48f13c8a (David A. Velasco 2012-09-05 13:46:30 +0200 205)             KeyStore trustStore = getKnownServersStore(context);
48f13c8a (David A. Velasco 2012-09-05 13:46:30 +0200 206)             AdvancedX509TrustManager trustMgr = new AdvancedX509TrustManager(trustStore);
48f13c8a (David A. Velasco 2012-09-05 13:46:30 +0200 207)             TrustManager[] tms = new TrustManager[] { trustMgr };
48f13c8a (David A. Velasco 2012-09-05 13:46:30 +0200 208)                 
48f13c8a (David A. Velasco 2012-09-05 13:46:30 +0200 209)             SSLContext sslContext = SSLContext.getInstance("TLS");
48f13c8a (David A. Velasco 2012-09-05 13:46:30 +0200 210)             sslContext.init(null, tms, null);
48f13c8a (David A. Velasco 2012-09-05 13:46:30 +0200 211)                     
261aaf50 (David A. Velasco 2012-09-07 14:11:08 +0200 212)             mHostnameVerifier = new BrowserCompatHostnameVerifier();
261aaf50 (David A. Velasco 2012-09-07 14:11:08 +0200 213)             mAdvancedSslSocketFactory = new AdvancedSslSocketFactory(sslContext, trustMgr, mHostnameVerifier);
48f13c8a (David A. Velasco 2012-09-05 13:46:30 +0200 214)         }
48f13c8a (David A. Velasco 2012-09-05 13:46:30 +0200 215)         return mAdvancedSslSocketFactory;
48f13c8a (David A. Velasco 2012-09-05 13:46:30 +0200 216)     }
48f13c8a (David A. Velasco 2012-09-05 13:46:30 +0200 217) 
48f13c8a (David A. Velasco 2012-09-05 13:46:30 +0200 218) 
48f13c8a (David A. Velasco 2012-09-05 13:46:30 +0200 219)     private static String LOCAL_TRUSTSTORE_FILENAME = "knownServers.bks";
48f13c8a (David A. Velasco 2012-09-05 13:46:30 +0200 220)     
48f13c8a (David A. Velasco 2012-09-05 13:46:30 +0200 221)     private static String LOCAL_TRUSTSTORE_PASSWORD = "password";
48f13c8a (David A. Velasco 2012-09-05 13:46:30 +0200 222) 
48f13c8a (David A. Velasco 2012-09-05 13:46:30 +0200 223)     private static KeyStore mKnownServersStore = null;
48f13c8a (David A. Velasco 2012-09-05 13:46:30 +0200 224)     
48f13c8a (David A. Velasco 2012-09-05 13:46:30 +0200 225)     /**
48f13c8a (David A. Velasco 2012-09-05 13:46:30 +0200 226)      * Returns the local store of reliable server certificates, explicitly accepted by the user.
48f13c8a (David A. Velasco 2012-09-05 13:46:30 +0200 227)      * 
48f13c8a (David A. Velasco 2012-09-05 13:46:30 +0200 228)      * Returns a KeyStore instance with empty content if the local store was never created.
48f13c8a (David A. Velasco 2012-09-05 13:46:30 +0200 229)      * 
48f13c8a (David A. Velasco 2012-09-05 13:46:30 +0200 230)      * Loads the store from the storage environment if needed.
48f13c8a (David A. Velasco 2012-09-05 13:46:30 +0200 231)      * 
48f13c8a (David A. Velasco 2012-09-05 13:46:30 +0200 232)      * @param context                       Android context where the operation is being performed.
48f13c8a (David A. Velasco 2012-09-05 13:46:30 +0200 233)      * @return                              KeyStore instance with explicitly-accepted server certificates. 
48f13c8a (David A. Velasco 2012-09-05 13:46:30 +0200 234)      * @throws KeyStoreException            When the KeyStore instance could not be created.
48f13c8a (David A. Velasco 2012-09-05 13:46:30 +0200 235)      * @throws IOException                  When an existing local trust store could not be loaded.
48f13c8a (David A. Velasco 2012-09-05 13:46:30 +0200 236)      * @throws NoSuchAlgorithmException     When the existing local trust store was saved with an unsupported algorithm.
48f13c8a (David A. Velasco 2012-09-05 13:46:30 +0200 237)      * @throws CertificateException         When an exception occurred while loading the certificates from the local trust store.
48f13c8a (David A. Velasco 2012-09-05 13:46:30 +0200 238)      */
48f13c8a (David A. Velasco 2012-09-05 13:46:30 +0200 239)     private static KeyStore getKnownServersStore(Context context) throws KeyStoreException, IOException, NoSuchAlgorithmException, CertificateException {
48f13c8a (David A. Velasco 2012-09-05 13:46:30 +0200 240)         if (mKnownServersStore == null) {
48f13c8a (David A. Velasco 2012-09-05 13:46:30 +0200 241)             //mKnownServersStore = KeyStore.getInstance("BKS");
48f13c8a (David A. Velasco 2012-09-05 13:46:30 +0200 242)             mKnownServersStore = KeyStore.getInstance(KeyStore.getDefaultType());
48f13c8a (David A. Velasco 2012-09-05 13:46:30 +0200 243)             File localTrustStoreFile = new File(context.getFilesDir(), LOCAL_TRUSTSTORE_FILENAME);
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100 244)             Log_OC.d(TAG, "Searching known-servers store at " + localTrustStoreFile.getAbsolutePath());
48f13c8a (David A. Velasco 2012-09-05 13:46:30 +0200 245)             if (localTrustStoreFile.exists()) {
48f13c8a (David A. Velasco 2012-09-05 13:46:30 +0200 246)                 InputStream in = new FileInputStream(localTrustStoreFile);
48f13c8a (David A. Velasco 2012-09-05 13:46:30 +0200 247)                 try {
48f13c8a (David A. Velasco 2012-09-05 13:46:30 +0200 248)                     mKnownServersStore.load(in, LOCAL_TRUSTSTORE_PASSWORD.toCharArray());
48f13c8a (David A. Velasco 2012-09-05 13:46:30 +0200 249)                 } finally {
48f13c8a (David A. Velasco 2012-09-05 13:46:30 +0200 250)                     in.close();
48f13c8a (David A. Velasco 2012-09-05 13:46:30 +0200 251)                 }
48f13c8a (David A. Velasco 2012-09-05 13:46:30 +0200 252)             } else {
48f13c8a (David A. Velasco 2012-09-05 13:46:30 +0200 253)                 mKnownServersStore.load(null, LOCAL_TRUSTSTORE_PASSWORD.toCharArray()); // necessary to initialize an empty KeyStore instance
48f13c8a (David A. Velasco 2012-09-05 13:46:30 +0200 254)             }
48f13c8a (David A. Velasco 2012-09-05 13:46:30 +0200 255)         }
48f13c8a (David A. Velasco 2012-09-05 13:46:30 +0200 256)         return mKnownServersStore;
48f13c8a (David A. Velasco 2012-09-05 13:46:30 +0200 257)     }
48f13c8a (David A. Velasco 2012-09-05 13:46:30 +0200 258)     
48f13c8a (David A. Velasco 2012-09-05 13:46:30 +0200 259)     
48f13c8a (David A. Velasco 2012-09-05 13:46:30 +0200 260)     public static void addCertToKnownServersStore(Certificate cert, Context context) throws  KeyStoreException, NoSuchAlgorithmException, 
48f13c8a (David A. Velasco 2012-09-05 13:46:30 +0200 261)                                                                                             CertificateException, IOException {
48f13c8a (David A. Velasco 2012-09-05 13:46:30 +0200 262)         KeyStore knownServers = getKnownServersStore(context);
48f13c8a (David A. Velasco 2012-09-05 13:46:30 +0200 263)         knownServers.setCertificateEntry(Integer.toString(cert.hashCode()), cert);
48f13c8a (David A. Velasco 2012-09-05 13:46:30 +0200 264)         FileOutputStream fos = null;
48f13c8a (David A. Velasco 2012-09-05 13:46:30 +0200 265)         try {
48f13c8a (David A. Velasco 2012-09-05 13:46:30 +0200 266)             fos = context.openFileOutput(LOCAL_TRUSTSTORE_FILENAME, Context.MODE_PRIVATE);
48f13c8a (David A. Velasco 2012-09-05 13:46:30 +0200 267)             knownServers.store(fos, LOCAL_TRUSTSTORE_PASSWORD.toCharArray());
48f13c8a (David A. Velasco 2012-09-05 13:46:30 +0200 268)         } finally {
48f13c8a (David A. Velasco 2012-09-05 13:46:30 +0200 269)             fos.close();
48f13c8a (David A. Velasco 2012-09-05 13:46:30 +0200 270)         }
48f13c8a (David A. Velasco 2012-09-05 13:46:30 +0200 271)     }
48f13c8a (David A. Velasco 2012-09-05 13:46:30 +0200 272)     
48f13c8a (David A. Velasco 2012-09-05 13:46:30 +0200 273)     
48f13c8a (David A. Velasco 2012-09-05 13:46:30 +0200 274)     static private MultiThreadedHttpConnectionManager getMultiThreadedConnManager() {
48f13c8a (David A. Velasco 2012-09-05 13:46:30 +0200 275)         if (mConnManager == null) {
48f13c8a (David A. Velasco 2012-09-05 13:46:30 +0200 276)             mConnManager = new MultiThreadedHttpConnectionManager();
48f13c8a (David A. Velasco 2012-09-05 13:46:30 +0200 277)             mConnManager.getParams().setDefaultMaxConnectionsPerHost(5);
48f13c8a (David A. Velasco 2012-09-05 13:46:30 +0200 278)             mConnManager.getParams().setMaxTotalConnections(5);
48f13c8a (David A. Velasco 2012-09-05 13:46:30 +0200 279)         }
48f13c8a (David A. Velasco 2012-09-05 13:46:30 +0200 280)         return mConnManager;
48f13c8a (David A. Velasco 2012-09-05 13:46:30 +0200 281)     }
48f13c8a (David A. Velasco 2012-09-05 13:46:30 +0200 282) 
5be4bf17 (David A. Velasco 2012-12-18 11:07:05 +0100 283) 
48f13c8a (David A. Velasco 2012-09-05 13:46:30 +0200 284) }
