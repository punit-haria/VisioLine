aa19d764 (David A. Velasco 2013-01-24 10:27:53 +0100   1) package com.owncloud.android.operations;
aa19d764 (David A. Velasco 2013-01-24 10:27:53 +0100   2) 
aa19d764 (David A. Velasco 2013-01-24 10:27:53 +0100   3) import java.util.HashMap;
aa19d764 (David A. Velasco 2013-01-24 10:27:53 +0100   4) import java.util.Map;
aa19d764 (David A. Velasco 2013-01-24 10:27:53 +0100   5) 
aa19d764 (David A. Velasco 2013-01-24 10:27:53 +0100   6) import org.apache.commons.httpclient.methods.PostMethod;
aa19d764 (David A. Velasco 2013-01-24 10:27:53 +0100   7) import org.apache.commons.httpclient.NameValuePair;
aa19d764 (David A. Velasco 2013-01-24 10:27:53 +0100   8) import org.json.JSONException;
aa19d764 (David A. Velasco 2013-01-24 10:27:53 +0100   9) import org.json.JSONObject;
aa19d764 (David A. Velasco 2013-01-24 10:27:53 +0100  10) 
2946d8dd (David A. Velasco 2013-04-25 19:39:22 +0200  11) import com.owncloud.android.Log_OC;
69d6d821 (David A. Velasco 2013-03-21 12:21:48 +0100  12) import com.owncloud.android.authentication.OAuth2Constants;
aa19d764 (David A. Velasco 2013-01-24 10:27:53 +0100  13) import com.owncloud.android.operations.RemoteOperationResult.ResultCode;
aa19d764 (David A. Velasco 2013-01-24 10:27:53 +0100  14) 
aa19d764 (David A. Velasco 2013-01-24 10:27:53 +0100  15) import eu.alefzero.webdav.WebdavClient;
aa19d764 (David A. Velasco 2013-01-24 10:27:53 +0100  16) 
aa19d764 (David A. Velasco 2013-01-24 10:27:53 +0100  17) public class OAuth2GetAccessToken extends RemoteOperation {
aa19d764 (David A. Velasco 2013-01-24 10:27:53 +0100  18)     
aa19d764 (David A. Velasco 2013-01-24 10:27:53 +0100  19)     private static final String TAG = OAuth2GetAccessToken.class.getSimpleName();
aa19d764 (David A. Velasco 2013-01-24 10:27:53 +0100  20)     
69d6d821 (David A. Velasco 2013-03-21 12:21:48 +0100  21)     private String mClientId;
69d6d821 (David A. Velasco 2013-03-21 12:21:48 +0100  22)     private String mRedirectUri;
69d6d821 (David A. Velasco 2013-03-21 12:21:48 +0100  23)     private String mGrantType;
69d6d821 (David A. Velasco 2013-03-21 12:21:48 +0100  24)     
fb8a8a21 (David A. Velasco 2013-01-28 13:15:28 +0100  25)     private String mOAuth2AuthorizationResponse;
fb8a8a21 (David A. Velasco 2013-01-28 13:15:28 +0100  26)     private Map<String, String> mOAuth2ParsedAuthorizationResponse;
aa19d764 (David A. Velasco 2013-01-24 10:27:53 +0100  27)     private Map<String, String> mResultTokenMap;
aa19d764 (David A. Velasco 2013-01-24 10:27:53 +0100  28) 
aa19d764 (David A. Velasco 2013-01-24 10:27:53 +0100  29)     
69d6d821 (David A. Velasco 2013-03-21 12:21:48 +0100  30)     public OAuth2GetAccessToken(String clientId, String redirectUri, String grantType, String oAuth2AuthorizationResponse) {
69d6d821 (David A. Velasco 2013-03-21 12:21:48 +0100  31)         mClientId = clientId;
69d6d821 (David A. Velasco 2013-03-21 12:21:48 +0100  32)         mRedirectUri = redirectUri;
69d6d821 (David A. Velasco 2013-03-21 12:21:48 +0100  33)         mGrantType = grantType;
aa19d764 (David A. Velasco 2013-01-24 10:27:53 +0100  34)         mOAuth2AuthorizationResponse = oAuth2AuthorizationResponse;
fb8a8a21 (David A. Velasco 2013-01-28 13:15:28 +0100  35)         mOAuth2ParsedAuthorizationResponse = new HashMap<String, String>();
aa19d764 (David A. Velasco 2013-01-24 10:27:53 +0100  36)         mResultTokenMap = null;
aa19d764 (David A. Velasco 2013-01-24 10:27:53 +0100  37)     }
aa19d764 (David A. Velasco 2013-01-24 10:27:53 +0100  38)     
aa19d764 (David A. Velasco 2013-01-24 10:27:53 +0100  39)     
aa19d764 (David A. Velasco 2013-01-24 10:27:53 +0100  40)     public Map<String, String> getOauth2AutorizationResponse() {
fb8a8a21 (David A. Velasco 2013-01-28 13:15:28 +0100  41)         return mOAuth2ParsedAuthorizationResponse;
aa19d764 (David A. Velasco 2013-01-24 10:27:53 +0100  42)     }
aa19d764 (David A. Velasco 2013-01-24 10:27:53 +0100  43) 
aa19d764 (David A. Velasco 2013-01-24 10:27:53 +0100  44)     public Map<String, String> getResultTokenMap() {
aa19d764 (David A. Velasco 2013-01-24 10:27:53 +0100  45)         return mResultTokenMap;
aa19d764 (David A. Velasco 2013-01-24 10:27:53 +0100  46)     }
aa19d764 (David A. Velasco 2013-01-24 10:27:53 +0100  47)     
aa19d764 (David A. Velasco 2013-01-24 10:27:53 +0100  48)     @Override
aa19d764 (David A. Velasco 2013-01-24 10:27:53 +0100  49)     protected RemoteOperationResult run(WebdavClient client) {
aa19d764 (David A. Velasco 2013-01-24 10:27:53 +0100  50)         RemoteOperationResult result = null;
aa19d764 (David A. Velasco 2013-01-24 10:27:53 +0100  51)         PostMethod postMethod = null;
aa19d764 (David A. Velasco 2013-01-24 10:27:53 +0100  52)         
aa19d764 (David A. Velasco 2013-01-24 10:27:53 +0100  53)         try {
fb8a8a21 (David A. Velasco 2013-01-28 13:15:28 +0100  54)             parseAuthorizationResponse();
69d6d821 (David A. Velasco 2013-03-21 12:21:48 +0100  55)             if (mOAuth2ParsedAuthorizationResponse.keySet().contains(OAuth2Constants.KEY_ERROR)) {
69d6d821 (David A. Velasco 2013-03-21 12:21:48 +0100  56)                 if (OAuth2Constants.VALUE_ERROR_ACCESS_DENIED.equals(mOAuth2ParsedAuthorizationResponse.get(OAuth2Constants.KEY_ERROR))) {
fb8a8a21 (David A. Velasco 2013-01-28 13:15:28 +0100  57)                     result = new RemoteOperationResult(ResultCode.OAUTH2_ERROR_ACCESS_DENIED);
fb8a8a21 (David A. Velasco 2013-01-28 13:15:28 +0100  58)                 } else {
fb8a8a21 (David A. Velasco 2013-01-28 13:15:28 +0100  59)                     result = new RemoteOperationResult(ResultCode.OAUTH2_ERROR);
fb8a8a21 (David A. Velasco 2013-01-28 13:15:28 +0100  60)                 }
fb8a8a21 (David A. Velasco 2013-01-28 13:15:28 +0100  61)             }
aa19d764 (David A. Velasco 2013-01-24 10:27:53 +0100  62)             
fb8a8a21 (David A. Velasco 2013-01-28 13:15:28 +0100  63)             if (result == null) { 
18d4ff20 (David A. Velasco 2013-03-21 13:22:53 +0100  64)                 NameValuePair[] nameValuePairs = new NameValuePair[4];
69d6d821 (David A. Velasco 2013-03-21 12:21:48 +0100  65)                 nameValuePairs[0] = new NameValuePair(OAuth2Constants.KEY_GRANT_TYPE, mGrantType);
69d6d821 (David A. Velasco 2013-03-21 12:21:48 +0100  66)                 nameValuePairs[1] = new NameValuePair(OAuth2Constants.KEY_CODE, mOAuth2ParsedAuthorizationResponse.get(OAuth2Constants.KEY_CODE));            
69d6d821 (David A. Velasco 2013-03-21 12:21:48 +0100  67)                 nameValuePairs[2] = new NameValuePair(OAuth2Constants.KEY_REDIRECT_URI, mRedirectUri);       
69d6d821 (David A. Velasco 2013-03-21 12:21:48 +0100  68)                 nameValuePairs[3] = new NameValuePair(OAuth2Constants.KEY_CLIENT_ID, mClientId);
69d6d821 (David A. Velasco 2013-03-21 12:21:48 +0100  69)                 //nameValuePairs[4] = new NameValuePair(OAuth2Constants.KEY_SCOPE, mOAuth2ParsedAuthorizationResponse.get(OAuth2Constants.KEY_SCOPE));         
aa19d764 (David A. Velasco 2013-01-24 10:27:53 +0100  70)                 
fb8a8a21 (David A. Velasco 2013-01-28 13:15:28 +0100  71)                 postMethod = new PostMethod(client.getBaseUri().toString());
fb8a8a21 (David A. Velasco 2013-01-28 13:15:28 +0100  72)                 postMethod.setRequestBody(nameValuePairs);
fb8a8a21 (David A. Velasco 2013-01-28 13:15:28 +0100  73)                 int status = client.executeMethod(postMethod);
fb8a8a21 (David A. Velasco 2013-01-28 13:15:28 +0100  74)                 
fb8a8a21 (David A. Velasco 2013-01-28 13:15:28 +0100  75)                 String response = postMethod.getResponseBodyAsString();
fb8a8a21 (David A. Velasco 2013-01-28 13:15:28 +0100  76)                 if (response != null && response.length() > 0) {
fb8a8a21 (David A. Velasco 2013-01-28 13:15:28 +0100  77)                     JSONObject tokenJson = new JSONObject(response);
fb8a8a21 (David A. Velasco 2013-01-28 13:15:28 +0100  78)                     parseAccessTokenResult(tokenJson);
69d6d821 (David A. Velasco 2013-03-21 12:21:48 +0100  79)                     if (mResultTokenMap.get(OAuth2Constants.KEY_ERROR) != null || mResultTokenMap.get(OAuth2Constants.KEY_ACCESS_TOKEN) == null) {
fb8a8a21 (David A. Velasco 2013-01-28 13:15:28 +0100  80)                         result = new RemoteOperationResult(ResultCode.OAUTH2_ERROR);
fb8a8a21 (David A. Velasco 2013-01-28 13:15:28 +0100  81)                     
fb8a8a21 (David A. Velasco 2013-01-28 13:15:28 +0100  82)                     } else {
78bcf721 (David A. Velasco 2013-08-07 19:13:00 +0200  83)                         result = new RemoteOperationResult(true, status, postMethod.getResponseHeaders());
fb8a8a21 (David A. Velasco 2013-01-28 13:15:28 +0100  84)                     }
aa19d764 (David A. Velasco 2013-01-24 10:27:53 +0100  85)                     
aa19d764 (David A. Velasco 2013-01-24 10:27:53 +0100  86)                 } else {
fb8a8a21 (David A. Velasco 2013-01-28 13:15:28 +0100  87)                     client.exhaustResponse(postMethod.getResponseBodyAsStream());
78bcf721 (David A. Velasco 2013-08-07 19:13:00 +0200  88)                     result = new RemoteOperationResult(false, status, postMethod.getResponseHeaders());
aa19d764 (David A. Velasco 2013-01-24 10:27:53 +0100  89)                 }
aa19d764 (David A. Velasco 2013-01-24 10:27:53 +0100  90)             }
fb8a8a21 (David A. Velasco 2013-01-28 13:15:28 +0100  91)             
aa19d764 (David A. Velasco 2013-01-24 10:27:53 +0100  92)         } catch (Exception e) {
aa19d764 (David A. Velasco 2013-01-24 10:27:53 +0100  93)             result = new RemoteOperationResult(e);
aa19d764 (David A. Velasco 2013-01-24 10:27:53 +0100  94)             
aa19d764 (David A. Velasco 2013-01-24 10:27:53 +0100  95)         } finally {
aa19d764 (David A. Velasco 2013-01-24 10:27:53 +0100  96)             if (postMethod != null)
aa19d764 (David A. Velasco 2013-01-24 10:27:53 +0100  97)                 postMethod.releaseConnection();    // let the connection available for other methods
aa19d764 (David A. Velasco 2013-01-24 10:27:53 +0100  98)             
aa19d764 (David A. Velasco 2013-01-24 10:27:53 +0100  99)             if (result.isSuccess()) {
2946d8dd (David A. Velasco 2013-04-25 19:39:22 +0200 100)                 Log_OC.i(TAG, "OAuth2 TOKEN REQUEST with auth code " + mOAuth2ParsedAuthorizationResponse.get("code") + " to " + client.getBaseUri() + ": " + result.getLogMessage());
aa19d764 (David A. Velasco 2013-01-24 10:27:53 +0100 101)             
aa19d764 (David A. Velasco 2013-01-24 10:27:53 +0100 102)             } else if (result.getException() != null) {
2946d8dd (David A. Velasco 2013-04-25 19:39:22 +0200 103)                 Log_OC.e(TAG, "OAuth2 TOKEN REQUEST with auth code " + mOAuth2ParsedAuthorizationResponse.get("code") + " to " + client.getBaseUri() + ": " + result.getLogMessage(), result.getException());
aa19d764 (David A. Velasco 2013-01-24 10:27:53 +0100 104)                 
aa19d764 (David A. Velasco 2013-01-24 10:27:53 +0100 105)             } else if (result.getCode() == ResultCode.OAUTH2_ERROR) {
2946d8dd (David A. Velasco 2013-04-25 19:39:22 +0200 106)                 Log_OC.e(TAG, "OAuth2 TOKEN REQUEST with auth code " + mOAuth2ParsedAuthorizationResponse.get("code") + " to " + client.getBaseUri() + ": " + ((mResultTokenMap != null) ? mResultTokenMap.get(OAuth2Constants.KEY_ERROR) : "NULL"));
aa19d764 (David A. Velasco 2013-01-24 10:27:53 +0100 107)                     
aa19d764 (David A. Velasco 2013-01-24 10:27:53 +0100 108)             } else {
2946d8dd (David A. Velasco 2013-04-25 19:39:22 +0200 109)                 Log_OC.e(TAG, "OAuth2 TOKEN REQUEST with auth code " + mOAuth2ParsedAuthorizationResponse.get("code") + " to " + client.getBaseUri() + ": " + result.getLogMessage());
aa19d764 (David A. Velasco 2013-01-24 10:27:53 +0100 110)             }
aa19d764 (David A. Velasco 2013-01-24 10:27:53 +0100 111)         }
aa19d764 (David A. Velasco 2013-01-24 10:27:53 +0100 112)         
aa19d764 (David A. Velasco 2013-01-24 10:27:53 +0100 113)         return result;
aa19d764 (David A. Velasco 2013-01-24 10:27:53 +0100 114)     }
aa19d764 (David A. Velasco 2013-01-24 10:27:53 +0100 115)     
aa19d764 (David A. Velasco 2013-01-24 10:27:53 +0100 116)     
fb8a8a21 (David A. Velasco 2013-01-28 13:15:28 +0100 117)     private void parseAuthorizationResponse() {
fb8a8a21 (David A. Velasco 2013-01-28 13:15:28 +0100 118)         String[] pairs = mOAuth2AuthorizationResponse.split("&");
fb8a8a21 (David A. Velasco 2013-01-28 13:15:28 +0100 119)         int i = 0;
fb8a8a21 (David A. Velasco 2013-01-28 13:15:28 +0100 120)         String key = "";
fb8a8a21 (David A. Velasco 2013-01-28 13:15:28 +0100 121)         String value = "";
fb8a8a21 (David A. Velasco 2013-01-28 13:15:28 +0100 122)         StringBuilder sb = new StringBuilder();
fb8a8a21 (David A. Velasco 2013-01-28 13:15:28 +0100 123)         while (pairs.length > i) {
fb8a8a21 (David A. Velasco 2013-01-28 13:15:28 +0100 124)             int j = 0;
fb8a8a21 (David A. Velasco 2013-01-28 13:15:28 +0100 125)             String[] part = pairs[i].split("=");
fb8a8a21 (David A. Velasco 2013-01-28 13:15:28 +0100 126)             while (part.length > j) {
fb8a8a21 (David A. Velasco 2013-01-28 13:15:28 +0100 127)                 String p = part[j];
fb8a8a21 (David A. Velasco 2013-01-28 13:15:28 +0100 128)                 if (j == 0) {
fb8a8a21 (David A. Velasco 2013-01-28 13:15:28 +0100 129)                     key = p;
fb8a8a21 (David A. Velasco 2013-01-28 13:15:28 +0100 130)                     sb.append(key + " = ");
fb8a8a21 (David A. Velasco 2013-01-28 13:15:28 +0100 131)                 } else if (j == 1) {
fb8a8a21 (David A. Velasco 2013-01-28 13:15:28 +0100 132)                     value = p;
fb8a8a21 (David A. Velasco 2013-01-28 13:15:28 +0100 133)                     mOAuth2ParsedAuthorizationResponse.put(key, value);
fb8a8a21 (David A. Velasco 2013-01-28 13:15:28 +0100 134)                     sb.append(value + "\n");
fb8a8a21 (David A. Velasco 2013-01-28 13:15:28 +0100 135)                 }
fb8a8a21 (David A. Velasco 2013-01-28 13:15:28 +0100 136) 
2946d8dd (David A. Velasco 2013-04-25 19:39:22 +0200 137)                 Log_OC.v(TAG, "[" + i + "," + j + "] = " + p);
fb8a8a21 (David A. Velasco 2013-01-28 13:15:28 +0100 138)                 j++;
fb8a8a21 (David A. Velasco 2013-01-28 13:15:28 +0100 139)             }
fb8a8a21 (David A. Velasco 2013-01-28 13:15:28 +0100 140)             i++;
fb8a8a21 (David A. Velasco 2013-01-28 13:15:28 +0100 141)         }
fb8a8a21 (David A. Velasco 2013-01-28 13:15:28 +0100 142)     }
fb8a8a21 (David A. Velasco 2013-01-28 13:15:28 +0100 143) 
fb8a8a21 (David A. Velasco 2013-01-28 13:15:28 +0100 144) 
fb8a8a21 (David A. Velasco 2013-01-28 13:15:28 +0100 145)     private void parseAccessTokenResult (JSONObject tokenJson) throws JSONException {
aa19d764 (David A. Velasco 2013-01-24 10:27:53 +0100 146)         mResultTokenMap = new HashMap<String, String>();
aa19d764 (David A. Velasco 2013-01-24 10:27:53 +0100 147)         
69d6d821 (David A. Velasco 2013-03-21 12:21:48 +0100 148)         if (tokenJson.has(OAuth2Constants.KEY_ACCESS_TOKEN)) {
69d6d821 (David A. Velasco 2013-03-21 12:21:48 +0100 149)             mResultTokenMap.put(OAuth2Constants.KEY_ACCESS_TOKEN, tokenJson.getString(OAuth2Constants.KEY_ACCESS_TOKEN));
aa19d764 (David A. Velasco 2013-01-24 10:27:53 +0100 150)         }
69d6d821 (David A. Velasco 2013-03-21 12:21:48 +0100 151)         if (tokenJson.has(OAuth2Constants.KEY_TOKEN_TYPE)) {
69d6d821 (David A. Velasco 2013-03-21 12:21:48 +0100 152)             mResultTokenMap.put(OAuth2Constants.KEY_TOKEN_TYPE, tokenJson.getString(OAuth2Constants.KEY_TOKEN_TYPE));
aa19d764 (David A. Velasco 2013-01-24 10:27:53 +0100 153)         }
69d6d821 (David A. Velasco 2013-03-21 12:21:48 +0100 154)         if (tokenJson.has(OAuth2Constants.KEY_EXPIRES_IN)) {
69d6d821 (David A. Velasco 2013-03-21 12:21:48 +0100 155)             mResultTokenMap.put(OAuth2Constants.KEY_EXPIRES_IN, tokenJson.getString(OAuth2Constants.KEY_EXPIRES_IN));
aa19d764 (David A. Velasco 2013-01-24 10:27:53 +0100 156)         }
69d6d821 (David A. Velasco 2013-03-21 12:21:48 +0100 157)         if (tokenJson.has(OAuth2Constants.KEY_REFRESH_TOKEN)) {
69d6d821 (David A. Velasco 2013-03-21 12:21:48 +0100 158)             mResultTokenMap.put(OAuth2Constants.KEY_REFRESH_TOKEN, tokenJson.getString(OAuth2Constants.KEY_REFRESH_TOKEN));
aa19d764 (David A. Velasco 2013-01-24 10:27:53 +0100 159)         }
69d6d821 (David A. Velasco 2013-03-21 12:21:48 +0100 160)         if (tokenJson.has(OAuth2Constants.KEY_SCOPE)) {
69d6d821 (David A. Velasco 2013-03-21 12:21:48 +0100 161)             mResultTokenMap.put(OAuth2Constants.KEY_SCOPE, tokenJson.getString(OAuth2Constants.KEY_SCOPE));
aa19d764 (David A. Velasco 2013-01-24 10:27:53 +0100 162)         }
69d6d821 (David A. Velasco 2013-03-21 12:21:48 +0100 163)         if (tokenJson.has(OAuth2Constants.KEY_ERROR)) {
69d6d821 (David A. Velasco 2013-03-21 12:21:48 +0100 164)             mResultTokenMap.put(OAuth2Constants.KEY_ERROR, tokenJson.getString(OAuth2Constants.KEY_ERROR));
aa19d764 (David A. Velasco 2013-01-24 10:27:53 +0100 165)         }
69d6d821 (David A. Velasco 2013-03-21 12:21:48 +0100 166)         if (tokenJson.has(OAuth2Constants.KEY_ERROR_DESCRIPTION)) {
69d6d821 (David A. Velasco 2013-03-21 12:21:48 +0100 167)             mResultTokenMap.put(OAuth2Constants.KEY_ERROR_DESCRIPTION, tokenJson.getString(OAuth2Constants.KEY_ERROR_DESCRIPTION));
aa19d764 (David A. Velasco 2013-01-24 10:27:53 +0100 168)         }
69d6d821 (David A. Velasco 2013-03-21 12:21:48 +0100 169)         if (tokenJson.has(OAuth2Constants.KEY_ERROR_URI)) {
69d6d821 (David A. Velasco 2013-03-21 12:21:48 +0100 170)             mResultTokenMap.put(OAuth2Constants.KEY_ERROR_URI, tokenJson.getString(OAuth2Constants.KEY_ERROR_URI));
aa19d764 (David A. Velasco 2013-01-24 10:27:53 +0100 171)         }
aa19d764 (David A. Velasco 2013-01-24 10:27:53 +0100 172)     }
aa19d764 (David A. Velasco 2013-01-24 10:27:53 +0100 173) 
aa19d764 (David A. Velasco 2013-01-24 10:27:53 +0100 174) }
