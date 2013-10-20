8e36e7cc (zerginator        2013-03-12 07:56:27 +0100   1) /* ownCloud Android client application
8e36e7cc (zerginator        2013-03-12 07:56:27 +0100   2)  *   Copyright (C) 2011  Bartek Przybylski
8e36e7cc (zerginator        2013-03-12 07:56:27 +0100   3)  *   Copyright (C) 2012-2013 ownCloud Inc.
8e36e7cc (zerginator        2013-03-12 07:56:27 +0100   4)  *
8e36e7cc (zerginator        2013-03-12 07:56:27 +0100   5)  *   This program is free software: you can redistribute it and/or modify
bdc0332c (David A. Velasco  2013-04-17 12:26:13 +0200   6)  *   it under the terms of the GNU General Public License version 2,
bdc0332c (David A. Velasco  2013-04-17 12:26:13 +0200   7)  *   as published by the Free Software Foundation.
8e36e7cc (zerginator        2013-03-12 07:56:27 +0100   8)  *
8e36e7cc (zerginator        2013-03-12 07:56:27 +0100   9)  *   This program is distributed in the hope that it will be useful,
8e36e7cc (zerginator        2013-03-12 07:56:27 +0100  10)  *   but WITHOUT ANY WARRANTY; without even the implied warranty of
8e36e7cc (zerginator        2013-03-12 07:56:27 +0100  11)  *   MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
8e36e7cc (zerginator        2013-03-12 07:56:27 +0100  12)  *   GNU General Public License for more details.
8e36e7cc (zerginator        2013-03-12 07:56:27 +0100  13)  *
8e36e7cc (zerginator        2013-03-12 07:56:27 +0100  14)  *   You should have received a copy of the GNU General Public License
8e36e7cc (zerginator        2013-03-12 07:56:27 +0100  15)  *   along with this program.  If not, see <http://www.gnu.org/licenses/>.
8e36e7cc (zerginator        2013-03-12 07:56:27 +0100  16)  *
8e36e7cc (zerginator        2013-03-12 07:56:27 +0100  17)  */
2946d8dd (David A. Velasco  2013-04-25 19:39:22 +0200  18) 
8e36e7cc (zerginator        2013-03-12 07:56:27 +0100  19) package eu.alefzero.webdav;
8e36e7cc (zerginator        2013-03-12 07:56:27 +0100  20) 
8e36e7cc (zerginator        2013-03-12 07:56:27 +0100  21) import java.io.IOException;
8e36e7cc (zerginator        2013-03-12 07:56:27 +0100  22) import java.io.InputStream;
2946d8dd (David A. Velasco  2013-04-25 19:39:22 +0200  23) import java.util.ArrayList;
2946d8dd (David A. Velasco  2013-04-25 19:39:22 +0200  24) import java.util.List;
8e36e7cc (zerginator        2013-03-12 07:56:27 +0100  25) 
8e36e7cc (zerginator        2013-03-12 07:56:27 +0100  26) import org.apache.commons.httpclient.Credentials;
8e36e7cc (zerginator        2013-03-12 07:56:27 +0100  27) import org.apache.commons.httpclient.HttpClient;
8e36e7cc (zerginator        2013-03-12 07:56:27 +0100  28) import org.apache.commons.httpclient.HttpConnectionManager;
8e36e7cc (zerginator        2013-03-12 07:56:27 +0100  29) import org.apache.commons.httpclient.HttpException;
52bc433b (David A. Velasco  2013-08-01 17:47:09 +0200  30) import org.apache.commons.httpclient.HttpMethod;
8e36e7cc (zerginator        2013-03-12 07:56:27 +0100  31) import org.apache.commons.httpclient.HttpMethodBase;
8e36e7cc (zerginator        2013-03-12 07:56:27 +0100  32) import org.apache.commons.httpclient.HttpVersion;
8e36e7cc (zerginator        2013-03-12 07:56:27 +0100  33) import org.apache.commons.httpclient.UsernamePasswordCredentials;
2946d8dd (David A. Velasco  2013-04-25 19:39:22 +0200  34) import org.apache.commons.httpclient.auth.AuthPolicy;
8e36e7cc (zerginator        2013-03-12 07:56:27 +0100  35) import org.apache.commons.httpclient.auth.AuthScope;
52bc433b (David A. Velasco  2013-08-01 17:47:09 +0200  36) import org.apache.commons.httpclient.cookie.CookiePolicy;
8e36e7cc (zerginator        2013-03-12 07:56:27 +0100  37) import org.apache.commons.httpclient.methods.HeadMethod;
8e36e7cc (zerginator        2013-03-12 07:56:27 +0100  38) import org.apache.commons.httpclient.params.HttpMethodParams;
8e36e7cc (zerginator        2013-03-12 07:56:27 +0100  39) import org.apache.http.HttpStatus;
8e36e7cc (zerginator        2013-03-12 07:56:27 +0100  40) import org.apache.http.params.CoreProtocolPNames;
8e36e7cc (zerginator        2013-03-12 07:56:27 +0100  41) 
8e36e7cc (zerginator        2013-03-12 07:56:27 +0100  42) import com.owncloud.android.Log_OC;
8e36e7cc (zerginator        2013-03-12 07:56:27 +0100  43) 
68df3cbf (David A. Velasco  2013-08-12 11:34:09 +0200  44) import com.owncloud.android.authentication.AccountAuthenticator;
2946d8dd (David A. Velasco  2013-04-25 19:39:22 +0200  45) import com.owncloud.android.network.BearerAuthScheme;
2946d8dd (David A. Velasco  2013-04-25 19:39:22 +0200  46) import com.owncloud.android.network.BearerCredentials;
2946d8dd (David A. Velasco  2013-04-25 19:39:22 +0200  47) 
8e36e7cc (zerginator        2013-03-12 07:56:27 +0100  48) import android.net.Uri;
8e36e7cc (zerginator        2013-03-12 07:56:27 +0100  49) 
8e36e7cc (zerginator        2013-03-12 07:56:27 +0100  50) public class WebdavClient extends HttpClient {
8e36e7cc (zerginator        2013-03-12 07:56:27 +0100  51)     private Uri mUri;
8e36e7cc (zerginator        2013-03-12 07:56:27 +0100  52)     private Credentials mCredentials;
d2ee9062 (David A. Velasco  2013-07-30 13:02:13 +0200  53)     private boolean mFollowRedirects;
52bc433b (David A. Velasco  2013-08-01 17:47:09 +0200  54)     private String mSsoSessionCookie;
68df3cbf (David A. Velasco  2013-08-12 11:34:09 +0200  55)     private String mAuthTokenType;
8e36e7cc (zerginator        2013-03-12 07:56:27 +0100  56)     final private static String TAG = "WebdavClient";
d2ee9062 (David A. Velasco  2013-07-30 13:02:13 +0200  57)     public static final String USER_AGENT = "Android-ownCloud";
8e36e7cc (zerginator        2013-03-12 07:56:27 +0100  58)     
8e36e7cc (zerginator        2013-03-12 07:56:27 +0100  59)     static private byte[] sExhaustBuffer = new byte[1024];
8e36e7cc (zerginator        2013-03-12 07:56:27 +0100  60)     
8e36e7cc (zerginator        2013-03-12 07:56:27 +0100  61)     /**
8e36e7cc (zerginator        2013-03-12 07:56:27 +0100  62)      * Constructor
8e36e7cc (zerginator        2013-03-12 07:56:27 +0100  63)      */
8e36e7cc (zerginator        2013-03-12 07:56:27 +0100  64)     public WebdavClient(HttpConnectionManager connectionMgr) {
8e36e7cc (zerginator        2013-03-12 07:56:27 +0100  65)         super(connectionMgr);
8e36e7cc (zerginator        2013-03-12 07:56:27 +0100  66)         Log_OC.d(TAG, "Creating WebdavClient");
8e36e7cc (zerginator        2013-03-12 07:56:27 +0100  67)         getParams().setParameter(HttpMethodParams.USER_AGENT, USER_AGENT);
8e36e7cc (zerginator        2013-03-12 07:56:27 +0100  68)         getParams().setParameter(CoreProtocolPNames.PROTOCOL_VERSION, HttpVersion.HTTP_1_1);
d2ee9062 (David A. Velasco  2013-07-30 13:02:13 +0200  69)         mFollowRedirects = true;
52bc433b (David A. Velasco  2013-08-01 17:47:09 +0200  70)         mSsoSessionCookie = null;
68df3cbf (David A. Velasco  2013-08-12 11:34:09 +0200  71)         mAuthTokenType = AccountAuthenticator.AUTH_TOKEN_TYPE_PASSWORD;
8e36e7cc (zerginator        2013-03-12 07:56:27 +0100  72)     }
8e36e7cc (zerginator        2013-03-12 07:56:27 +0100  73) 
2946d8dd (David A. Velasco  2013-04-25 19:39:22 +0200  74)     public void setBearerCredentials(String accessToken) {
2946d8dd (David A. Velasco  2013-04-25 19:39:22 +0200  75)         AuthPolicy.registerAuthScheme(BearerAuthScheme.AUTH_POLICY, BearerAuthScheme.class);
2946d8dd (David A. Velasco  2013-04-25 19:39:22 +0200  76)         
2946d8dd (David A. Velasco  2013-04-25 19:39:22 +0200  77)         List<String> authPrefs = new ArrayList<String>(1);
2946d8dd (David A. Velasco  2013-04-25 19:39:22 +0200  78)         authPrefs.add(BearerAuthScheme.AUTH_POLICY);
2946d8dd (David A. Velasco  2013-04-25 19:39:22 +0200  79)         getParams().setParameter(AuthPolicy.AUTH_SCHEME_PRIORITY, authPrefs);        
2946d8dd (David A. Velasco  2013-04-25 19:39:22 +0200  80)         
2946d8dd (David A. Velasco  2013-04-25 19:39:22 +0200  81)         mCredentials = new BearerCredentials(accessToken);
2946d8dd (David A. Velasco  2013-04-25 19:39:22 +0200  82)         getState().setCredentials(AuthScope.ANY, mCredentials);
52bc433b (David A. Velasco  2013-08-01 17:47:09 +0200  83)         mSsoSessionCookie = null;
68df3cbf (David A. Velasco  2013-08-12 11:34:09 +0200  84)         mAuthTokenType = AccountAuthenticator.AUTH_TOKEN_TYPE_ACCESS_TOKEN;
8e36e7cc (zerginator        2013-03-12 07:56:27 +0100  85)     }
8e36e7cc (zerginator        2013-03-12 07:56:27 +0100  86) 
2946d8dd (David A. Velasco  2013-04-25 19:39:22 +0200  87)     public void setBasicCredentials(String username, String password) {
2946d8dd (David A. Velasco  2013-04-25 19:39:22 +0200  88)         List<String> authPrefs = new ArrayList<String>(1);
2946d8dd (David A. Velasco  2013-04-25 19:39:22 +0200  89)         authPrefs.add(AuthPolicy.BASIC);
2946d8dd (David A. Velasco  2013-04-25 19:39:22 +0200  90)         getParams().setParameter(AuthPolicy.AUTH_SCHEME_PRIORITY, authPrefs);        
2946d8dd (David A. Velasco  2013-04-25 19:39:22 +0200  91)         
2946d8dd (David A. Velasco  2013-04-25 19:39:22 +0200  92)         getParams().setAuthenticationPreemptive(true);
2946d8dd (David A. Velasco  2013-04-25 19:39:22 +0200  93)         mCredentials = new UsernamePasswordCredentials(username, password);
2946d8dd (David A. Velasco  2013-04-25 19:39:22 +0200  94)         getState().setCredentials(AuthScope.ANY, mCredentials);
52bc433b (David A. Velasco  2013-08-01 17:47:09 +0200  95)         mSsoSessionCookie = null;
68df3cbf (David A. Velasco  2013-08-12 11:34:09 +0200  96)         mAuthTokenType = AccountAuthenticator.AUTH_TOKEN_TYPE_PASSWORD;
8e36e7cc (zerginator        2013-03-12 07:56:27 +0100  97)     }
8e36e7cc (zerginator        2013-03-12 07:56:27 +0100  98)     
52bc433b (David A. Velasco  2013-08-01 17:47:09 +0200  99)     public void setSsoSessionCookie(String accessToken) {
52bc433b (David A. Velasco  2013-08-01 17:47:09 +0200 100)         getParams().setAuthenticationPreemptive(false);
e04f2573 (David A. Velasco  2013-08-22 19:24:51 +0200 101)         getParams().setCookiePolicy(CookiePolicy.IGNORE_COOKIES);
52bc433b (David A. Velasco  2013-08-01 17:47:09 +0200 102)         mSsoSessionCookie = accessToken;
52bc433b (David A. Velasco  2013-08-01 17:47:09 +0200 103)         mCredentials = null;
68df3cbf (David A. Velasco  2013-08-12 11:34:09 +0200 104)         mAuthTokenType = AccountAuthenticator.AUTH_TOKEN_TYPE_SAML_WEB_SSO_SESSION_COOKIE;
52bc433b (David A. Velasco  2013-08-01 17:47:09 +0200 105)     }
52bc433b (David A. Velasco  2013-08-01 17:47:09 +0200 106)     
52bc433b (David A. Velasco  2013-08-01 17:47:09 +0200 107)     
8e36e7cc (zerginator        2013-03-12 07:56:27 +0100 108)     /**
8e36e7cc (zerginator        2013-03-12 07:56:27 +0100 109)      * Check if a file exists in the OC server
8e36e7cc (zerginator        2013-03-12 07:56:27 +0100 110)      * 
2b3f7c54 (David A. Velasco  2013-04-25 19:44:21 +0200 111)      * TODO replace with ExistenceOperation
2b3f7c54 (David A. Velasco  2013-04-25 19:44:21 +0200 112)      * 
8e36e7cc (zerginator        2013-03-12 07:56:27 +0100 113)      * @return              'true' if the file exists; 'false' it doesn't exist
8e36e7cc (zerginator        2013-03-12 07:56:27 +0100 114)      * @throws  Exception   When the existence could not be determined
8e36e7cc (zerginator        2013-03-12 07:56:27 +0100 115)      */
8e36e7cc (zerginator        2013-03-12 07:56:27 +0100 116)     public boolean existsFile(String path) throws IOException, HttpException {
8e36e7cc (zerginator        2013-03-12 07:56:27 +0100 117)         HeadMethod head = new HeadMethod(mUri.toString() + WebdavUtils.encodePath(path));
8e36e7cc (zerginator        2013-03-12 07:56:27 +0100 118)         try {
8e36e7cc (zerginator        2013-03-12 07:56:27 +0100 119)             int status = executeMethod(head);
8e36e7cc (zerginator        2013-03-12 07:56:27 +0100 120)             Log_OC.d(TAG, "HEAD to " + path + " finished with HTTP status " + status + ((status != HttpStatus.SC_OK)?"(FAIL)":""));
8e36e7cc (zerginator        2013-03-12 07:56:27 +0100 121)             exhaustResponse(head.getResponseBodyAsStream());
8e36e7cc (zerginator        2013-03-12 07:56:27 +0100 122)             return (status == HttpStatus.SC_OK);
8e36e7cc (zerginator        2013-03-12 07:56:27 +0100 123)             
8e36e7cc (zerginator        2013-03-12 07:56:27 +0100 124)         } finally {
8e36e7cc (zerginator        2013-03-12 07:56:27 +0100 125)             head.releaseConnection();    // let the connection available for other methods
8e36e7cc (zerginator        2013-03-12 07:56:27 +0100 126)         }
8e36e7cc (zerginator        2013-03-12 07:56:27 +0100 127)     }
2946d8dd (David A. Velasco  2013-04-25 19:39:22 +0200 128)     
8e36e7cc (zerginator        2013-03-12 07:56:27 +0100 129)     /**
8e36e7cc (zerginator        2013-03-12 07:56:27 +0100 130)      * Requests the received method with the received timeout (milliseconds).
8e36e7cc (zerginator        2013-03-12 07:56:27 +0100 131)      * 
8e36e7cc (zerginator        2013-03-12 07:56:27 +0100 132)      * Executes the method through the inherited HttpClient.executedMethod(method).
8e36e7cc (zerginator        2013-03-12 07:56:27 +0100 133)      * 
8e36e7cc (zerginator        2013-03-12 07:56:27 +0100 134)      * Sets the socket and connection timeouts only for the method received.
8e36e7cc (zerginator        2013-03-12 07:56:27 +0100 135)      * 
8e36e7cc (zerginator        2013-03-12 07:56:27 +0100 136)      * The timeouts are both in milliseconds; 0 means 'infinite'; < 0 means 'do not change the default'
8e36e7cc (zerginator        2013-03-12 07:56:27 +0100 137)      * 
8e36e7cc (zerginator        2013-03-12 07:56:27 +0100 138)      * @param method            HTTP method request.
8e36e7cc (zerginator        2013-03-12 07:56:27 +0100 139)      * @param readTimeout       Timeout to set for data reception
8e36e7cc (zerginator        2013-03-12 07:56:27 +0100 140)      * @param conntionTimout    Timeout to set for connection establishment
8e36e7cc (zerginator        2013-03-12 07:56:27 +0100 141)      */
8e36e7cc (zerginator        2013-03-12 07:56:27 +0100 142)     public int executeMethod(HttpMethodBase method, int readTimeout, int connectionTimeout) throws HttpException, IOException {
8e36e7cc (zerginator        2013-03-12 07:56:27 +0100 143)         int oldSoTimeout = getParams().getSoTimeout();
8e36e7cc (zerginator        2013-03-12 07:56:27 +0100 144)         int oldConnectionTimeout = getHttpConnectionManager().getParams().getConnectionTimeout();
8e36e7cc (zerginator        2013-03-12 07:56:27 +0100 145)         try {
8e36e7cc (zerginator        2013-03-12 07:56:27 +0100 146)             if (readTimeout >= 0) { 
8e36e7cc (zerginator        2013-03-12 07:56:27 +0100 147)                 method.getParams().setSoTimeout(readTimeout);   // this should be enough...
8e36e7cc (zerginator        2013-03-12 07:56:27 +0100 148)                 getParams().setSoTimeout(readTimeout);          // ... but this looks like necessary for HTTPS
8e36e7cc (zerginator        2013-03-12 07:56:27 +0100 149)             }
8e36e7cc (zerginator        2013-03-12 07:56:27 +0100 150)             if (connectionTimeout >= 0) {
8e36e7cc (zerginator        2013-03-12 07:56:27 +0100 151)                 getHttpConnectionManager().getParams().setConnectionTimeout(connectionTimeout);
8e36e7cc (zerginator        2013-03-12 07:56:27 +0100 152)             }
8e36e7cc (zerginator        2013-03-12 07:56:27 +0100 153)             return executeMethod(method);
8e36e7cc (zerginator        2013-03-12 07:56:27 +0100 154)         } finally {
8e36e7cc (zerginator        2013-03-12 07:56:27 +0100 155)             getParams().setSoTimeout(oldSoTimeout);
8e36e7cc (zerginator        2013-03-12 07:56:27 +0100 156)             getHttpConnectionManager().getParams().setConnectionTimeout(oldConnectionTimeout);
8e36e7cc (zerginator        2013-03-12 07:56:27 +0100 157)         }
8e36e7cc (zerginator        2013-03-12 07:56:27 +0100 158)     }
52bc433b (David A. Velasco  2013-08-01 17:47:09 +0200 159)     
52bc433b (David A. Velasco  2013-08-01 17:47:09 +0200 160)     
52bc433b (David A. Velasco  2013-08-01 17:47:09 +0200 161)     @Override
52bc433b (David A. Velasco  2013-08-01 17:47:09 +0200 162)     public int executeMethod(HttpMethod method) throws IOException, HttpException {
52bc433b (David A. Velasco  2013-08-01 17:47:09 +0200 163)         try {
52bc433b (David A. Velasco  2013-08-01 17:47:09 +0200 164)             method.setFollowRedirects(mFollowRedirects);
52bc433b (David A. Velasco  2013-08-01 17:47:09 +0200 165)         } catch (Exception e) {
52bc433b (David A. Velasco  2013-08-01 17:47:09 +0200 166)             
52bc433b (David A. Velasco  2013-08-01 17:47:09 +0200 167)         }
52bc433b (David A. Velasco  2013-08-01 17:47:09 +0200 168)         if (mSsoSessionCookie != null && mSsoSessionCookie.length() > 0) {
52bc433b (David A. Velasco  2013-08-01 17:47:09 +0200 169)             method.setRequestHeader("Cookie", mSsoSessionCookie);
52bc433b (David A. Velasco  2013-08-01 17:47:09 +0200 170)         }
52bc433b (David A. Velasco  2013-08-01 17:47:09 +0200 171)         return super.executeMethod(method);
52bc433b (David A. Velasco  2013-08-01 17:47:09 +0200 172)     }
52bc433b (David A. Velasco  2013-08-01 17:47:09 +0200 173) 
8e36e7cc (zerginator        2013-03-12 07:56:27 +0100 174) 
8e36e7cc (zerginator        2013-03-12 07:56:27 +0100 175)     /**
8e36e7cc (zerginator        2013-03-12 07:56:27 +0100 176)      * Exhausts a not interesting HTTP response. Encouraged by HttpClient documentation.
8e36e7cc (zerginator        2013-03-12 07:56:27 +0100 177)      * 
8e36e7cc (zerginator        2013-03-12 07:56:27 +0100 178)      * @param responseBodyAsStream      InputStream with the HTTP response to exhaust.
8e36e7cc (zerginator        2013-03-12 07:56:27 +0100 179)      */
8e36e7cc (zerginator        2013-03-12 07:56:27 +0100 180)     public void exhaustResponse(InputStream responseBodyAsStream) {
8e36e7cc (zerginator        2013-03-12 07:56:27 +0100 181)         if (responseBodyAsStream != null) {
8e36e7cc (zerginator        2013-03-12 07:56:27 +0100 182)             try {
8e36e7cc (zerginator        2013-03-12 07:56:27 +0100 183)                 while (responseBodyAsStream.read(sExhaustBuffer) >= 0);
8e36e7cc (zerginator        2013-03-12 07:56:27 +0100 184)                 responseBodyAsStream.close();
8e36e7cc (zerginator        2013-03-12 07:56:27 +0100 185)             
8e36e7cc (zerginator        2013-03-12 07:56:27 +0100 186)             } catch (IOException io) {
8e36e7cc (zerginator        2013-03-12 07:56:27 +0100 187)                 Log_OC.e(TAG, "Unexpected exception while exhausting not interesting HTTP response; will be IGNORED", io);
8e36e7cc (zerginator        2013-03-12 07:56:27 +0100 188)             }
8e36e7cc (zerginator        2013-03-12 07:56:27 +0100 189)         }
8e36e7cc (zerginator        2013-03-12 07:56:27 +0100 190)     }
8e36e7cc (zerginator        2013-03-12 07:56:27 +0100 191) 
8e36e7cc (zerginator        2013-03-12 07:56:27 +0100 192)     /**
8e36e7cc (zerginator        2013-03-12 07:56:27 +0100 193)      * Sets the connection and wait-for-data timeouts to be applied by default to the methods performed by this client.
8e36e7cc (zerginator        2013-03-12 07:56:27 +0100 194)      */
8e36e7cc (zerginator        2013-03-12 07:56:27 +0100 195)     public void setDefaultTimeouts(int defaultDataTimeout, int defaultConnectionTimeout) {
8e36e7cc (zerginator        2013-03-12 07:56:27 +0100 196)             getParams().setSoTimeout(defaultDataTimeout);
8e36e7cc (zerginator        2013-03-12 07:56:27 +0100 197)             getHttpConnectionManager().getParams().setConnectionTimeout(defaultConnectionTimeout);
8e36e7cc (zerginator        2013-03-12 07:56:27 +0100 198)     }
8e36e7cc (zerginator        2013-03-12 07:56:27 +0100 199) 
8e36e7cc (zerginator        2013-03-12 07:56:27 +0100 200)     /**
8e36e7cc (zerginator        2013-03-12 07:56:27 +0100 201)      * Sets the base URI for the helper methods that receive paths as parameters, instead of full URLs
8e36e7cc (zerginator        2013-03-12 07:56:27 +0100 202)      * @param uri
8e36e7cc (zerginator        2013-03-12 07:56:27 +0100 203)      */
8e36e7cc (zerginator        2013-03-12 07:56:27 +0100 204)     public void setBaseUri(Uri uri) {
8e36e7cc (zerginator        2013-03-12 07:56:27 +0100 205)         mUri = uri;
8e36e7cc (zerginator        2013-03-12 07:56:27 +0100 206)     }
8e36e7cc (zerginator        2013-03-12 07:56:27 +0100 207) 
8e36e7cc (zerginator        2013-03-12 07:56:27 +0100 208)     public Uri getBaseUri() {
8e36e7cc (zerginator        2013-03-12 07:56:27 +0100 209)         return mUri;
8e36e7cc (zerginator        2013-03-12 07:56:27 +0100 210)     }
8e36e7cc (zerginator        2013-03-12 07:56:27 +0100 211) 
00000000 (Not Committed Yet 2013-10-18 00:25:14 -0700 212)     public final Credentials getCredentials() {
00000000 (Not Committed Yet 2013-10-18 00:25:14 -0700 213)         return mCredentials;
d2ee9062 (David A. Velasco  2013-07-30 13:02:13 +0200 214)     }
78bcf721 (David A. Velasco  2013-08-07 19:13:00 +0200 215)     
78bcf721 (David A. Velasco  2013-08-07 19:13:00 +0200 216)     public final String getSsoSessionCookie() {
78bcf721 (David A. Velasco  2013-08-07 19:13:00 +0200 217)         return mSsoSessionCookie;
78bcf721 (David A. Velasco  2013-08-07 19:13:00 +0200 218)     }
d2ee9062 (David A. Velasco  2013-07-30 13:02:13 +0200 219) 
d2ee9062 (David A. Velasco  2013-07-30 13:02:13 +0200 220)     public void setFollowRedirects(boolean followRedirects) {
d2ee9062 (David A. Velasco  2013-07-30 13:02:13 +0200 221)         mFollowRedirects = followRedirects;
52bc433b (David A. Velasco  2013-08-01 17:47:09 +0200 222)     }
52bc433b (David A. Velasco  2013-08-01 17:47:09 +0200 223) 
68df3cbf (David A. Velasco  2013-08-12 11:34:09 +0200 224)     public String getAuthTokenType() {
68df3cbf (David A. Velasco  2013-08-12 11:34:09 +0200 225)         return mAuthTokenType;
68df3cbf (David A. Velasco  2013-08-12 11:34:09 +0200 226)     }
68df3cbf (David A. Velasco  2013-08-12 11:34:09 +0200 227) 
8e36e7cc (zerginator        2013-03-12 07:56:27 +0100 228) }
