8e36e7cc src/com/owncloud/android/authenticator/AccountAuthenticator.java  (zerginator        2013-03-12 07:56:27 +0100   1) /* ownCloud Android client application
8e36e7cc src/com/owncloud/android/authenticator/AccountAuthenticator.java  (zerginator        2013-03-12 07:56:27 +0100   2)  *   Copyright (C) 2012  Bartek Przybylski
8e36e7cc src/com/owncloud/android/authenticator/AccountAuthenticator.java  (zerginator        2013-03-12 07:56:27 +0100   3)  *   Copyright (C) 2012-2013 ownCloud Inc.
8e36e7cc src/com/owncloud/android/authenticator/AccountAuthenticator.java  (zerginator        2013-03-12 07:56:27 +0100   4)  *
8e36e7cc src/com/owncloud/android/authenticator/AccountAuthenticator.java  (zerginator        2013-03-12 07:56:27 +0100   5)  *   This program is free software: you can redistribute it and/or modify
bdc0332c src/com/owncloud/android/authenticator/AccountAuthenticator.java  (David A. Velasco  2013-04-17 12:26:13 +0200   6)  *   it under the terms of the GNU General Public License version 2,
bdc0332c src/com/owncloud/android/authenticator/AccountAuthenticator.java  (David A. Velasco  2013-04-17 12:26:13 +0200   7)  *   as published by the Free Software Foundation.
8e36e7cc src/com/owncloud/android/authenticator/AccountAuthenticator.java  (zerginator        2013-03-12 07:56:27 +0100   8)  *
8e36e7cc src/com/owncloud/android/authenticator/AccountAuthenticator.java  (zerginator        2013-03-12 07:56:27 +0100   9)  *   This program is distributed in the hope that it will be useful,
8e36e7cc src/com/owncloud/android/authenticator/AccountAuthenticator.java  (zerginator        2013-03-12 07:56:27 +0100  10)  *   but WITHOUT ANY WARRANTY; without even the implied warranty of
8e36e7cc src/com/owncloud/android/authenticator/AccountAuthenticator.java  (zerginator        2013-03-12 07:56:27 +0100  11)  *   MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
8e36e7cc src/com/owncloud/android/authenticator/AccountAuthenticator.java  (zerginator        2013-03-12 07:56:27 +0100  12)  *   GNU General Public License for more details.
8e36e7cc src/com/owncloud/android/authenticator/AccountAuthenticator.java  (zerginator        2013-03-12 07:56:27 +0100  13)  *
8e36e7cc src/com/owncloud/android/authenticator/AccountAuthenticator.java  (zerginator        2013-03-12 07:56:27 +0100  14)  *   You should have received a copy of the GNU General Public License
8e36e7cc src/com/owncloud/android/authenticator/AccountAuthenticator.java  (zerginator        2013-03-12 07:56:27 +0100  15)  *   along with this program.  If not, see <http://www.gnu.org/licenses/>.
8e36e7cc src/com/owncloud/android/authenticator/AccountAuthenticator.java  (zerginator        2013-03-12 07:56:27 +0100  16)  *
8e36e7cc src/com/owncloud/android/authenticator/AccountAuthenticator.java  (zerginator        2013-03-12 07:56:27 +0100  17)  */
8e36e7cc src/com/owncloud/android/authenticator/AccountAuthenticator.java  (zerginator        2013-03-12 07:56:27 +0100  18) 
2946d8dd src/com/owncloud/android/authentication/AccountAuthenticator.java (David A. Velasco  2013-04-25 19:39:22 +0200  19) package com.owncloud.android.authentication;
8e36e7cc src/com/owncloud/android/authenticator/AccountAuthenticator.java  (zerginator        2013-03-12 07:56:27 +0100  20) 
8e36e7cc src/com/owncloud/android/authenticator/AccountAuthenticator.java  (zerginator        2013-03-12 07:56:27 +0100  21) import android.accounts.*;
8e36e7cc src/com/owncloud/android/authenticator/AccountAuthenticator.java  (zerginator        2013-03-12 07:56:27 +0100  22) import android.content.Context;
8e36e7cc src/com/owncloud/android/authenticator/AccountAuthenticator.java  (zerginator        2013-03-12 07:56:27 +0100  23) import android.content.Intent;
8e36e7cc src/com/owncloud/android/authenticator/AccountAuthenticator.java  (zerginator        2013-03-12 07:56:27 +0100  24) import android.os.Bundle;
2946d8dd src/com/owncloud/android/authentication/AccountAuthenticator.java (David A. Velasco  2013-04-25 19:39:22 +0200  25) import com.owncloud.android.Log_OC;
8e36e7cc src/com/owncloud/android/authenticator/AccountAuthenticator.java  (zerginator        2013-03-12 07:56:27 +0100  26) 
2946d8dd src/com/owncloud/android/authentication/AccountAuthenticator.java (David A. Velasco  2013-04-25 19:39:22 +0200  27) /**
2946d8dd src/com/owncloud/android/authentication/AccountAuthenticator.java (David A. Velasco  2013-04-25 19:39:22 +0200  28)  *  Authenticator for ownCloud accounts.
2946d8dd src/com/owncloud/android/authentication/AccountAuthenticator.java (David A. Velasco  2013-04-25 19:39:22 +0200  29)  * 
2946d8dd src/com/owncloud/android/authentication/AccountAuthenticator.java (David A. Velasco  2013-04-25 19:39:22 +0200  30)  *  Controller class accessed from the system AccountManager, providing integration of ownCloud accounts with the Android system.
2946d8dd src/com/owncloud/android/authentication/AccountAuthenticator.java (David A. Velasco  2013-04-25 19:39:22 +0200  31)  * 
2946d8dd src/com/owncloud/android/authentication/AccountAuthenticator.java (David A. Velasco  2013-04-25 19:39:22 +0200  32)  *  TODO - better separation in operations for OAuth-capable and regular ownCloud accounts.
2946d8dd src/com/owncloud/android/authentication/AccountAuthenticator.java (David A. Velasco  2013-04-25 19:39:22 +0200  33)  *  TODO - review completeness 
2946d8dd src/com/owncloud/android/authentication/AccountAuthenticator.java (David A. Velasco  2013-04-25 19:39:22 +0200  34)  * 
2946d8dd src/com/owncloud/android/authentication/AccountAuthenticator.java (David A. Velasco  2013-04-25 19:39:22 +0200  35)  * @author David A. Velasco
2946d8dd src/com/owncloud/android/authentication/AccountAuthenticator.java (David A. Velasco  2013-04-25 19:39:22 +0200  36)  */
8e36e7cc src/com/owncloud/android/authenticator/AccountAuthenticator.java  (zerginator        2013-03-12 07:56:27 +0100  37) public class AccountAuthenticator extends AbstractAccountAuthenticator {
2946d8dd src/com/owncloud/android/authentication/AccountAuthenticator.java (David A. Velasco  2013-04-25 19:39:22 +0200  38)     
8e36e7cc src/com/owncloud/android/authenticator/AccountAuthenticator.java  (zerginator        2013-03-12 07:56:27 +0100  39)     /**
8e36e7cc src/com/owncloud/android/authenticator/AccountAuthenticator.java  (zerginator        2013-03-12 07:56:27 +0100  40)      * Is used by android system to assign accounts to authenticators. Should be
8e36e7cc src/com/owncloud/android/authenticator/AccountAuthenticator.java  (zerginator        2013-03-12 07:56:27 +0100  41)      * used by application and all extensions.
8e36e7cc src/com/owncloud/android/authenticator/AccountAuthenticator.java  (zerginator        2013-03-12 07:56:27 +0100  42)      */
8e36e7cc src/com/owncloud/android/authenticator/AccountAuthenticator.java  (zerginator        2013-03-12 07:56:27 +0100  43)     public static final String ACCOUNT_TYPE = "owncloud";
2946d8dd src/com/owncloud/android/authentication/AccountAuthenticator.java (David A. Velasco  2013-04-25 19:39:22 +0200  44)     public static final String AUTHORITY = "org.owncloud";
8e36e7cc src/com/owncloud/android/authenticator/AccountAuthenticator.java  (zerginator        2013-03-12 07:56:27 +0100  45)     public static final String AUTH_TOKEN_TYPE = "org.owncloud";
2946d8dd src/com/owncloud/android/authentication/AccountAuthenticator.java (David A. Velasco  2013-04-25 19:39:22 +0200  46)     public static final String AUTH_TOKEN_TYPE_PASSWORD = "owncloud.password";
2946d8dd src/com/owncloud/android/authentication/AccountAuthenticator.java (David A. Velasco  2013-04-25 19:39:22 +0200  47)     public static final String AUTH_TOKEN_TYPE_ACCESS_TOKEN = "owncloud.oauth2.access_token";
2946d8dd src/com/owncloud/android/authentication/AccountAuthenticator.java (David A. Velasco  2013-04-25 19:39:22 +0200  48)     public static final String AUTH_TOKEN_TYPE_REFRESH_TOKEN = "owncloud.oauth2.refresh_token";
0713ba61 src/com/owncloud/android/authentication/AccountAuthenticator.java (David A. Velasco  2013-07-25 17:29:52 +0200  49)     public static final String AUTH_TOKEN_TYPE_SAML_WEB_SSO_SESSION_COOKIE = "owncloud.saml.web_sso.session_cookie";
8e36e7cc src/com/owncloud/android/authenticator/AccountAuthenticator.java  (zerginator        2013-03-12 07:56:27 +0100  50) 
8e36e7cc src/com/owncloud/android/authenticator/AccountAuthenticator.java  (zerginator        2013-03-12 07:56:27 +0100  51)     public static final String KEY_AUTH_TOKEN_TYPE = "authTokenType";
8e36e7cc src/com/owncloud/android/authenticator/AccountAuthenticator.java  (zerginator        2013-03-12 07:56:27 +0100  52)     public static final String KEY_REQUIRED_FEATURES = "requiredFeatures";
8e36e7cc src/com/owncloud/android/authenticator/AccountAuthenticator.java  (zerginator        2013-03-12 07:56:27 +0100  53)     public static final String KEY_LOGIN_OPTIONS = "loginOptions";
8e36e7cc src/com/owncloud/android/authenticator/AccountAuthenticator.java  (zerginator        2013-03-12 07:56:27 +0100  54)     public static final String KEY_ACCOUNT = "account";
2946d8dd src/com/owncloud/android/authentication/AccountAuthenticator.java (David A. Velasco  2013-04-25 19:39:22 +0200  55)     
8e36e7cc src/com/owncloud/android/authenticator/AccountAuthenticator.java  (zerginator        2013-03-12 07:56:27 +0100  56)     /**
8e36e7cc src/com/owncloud/android/authenticator/AccountAuthenticator.java  (zerginator        2013-03-12 07:56:27 +0100  57)      * Value under this key should handle path to webdav php script. Will be
8e36e7cc src/com/owncloud/android/authenticator/AccountAuthenticator.java  (zerginator        2013-03-12 07:56:27 +0100  58)      * removed and usage should be replaced by combining
2946d8dd src/com/owncloud/android/authentication/AccountAuthenticator.java (David A. Velasco  2013-04-25 19:39:22 +0200  59)      * {@link com.owncloud.android.authentication.AuthenticatorActivity.KEY_OC_BASE_URL} and
8e36e7cc src/com/owncloud/android/authenticator/AccountAuthenticator.java  (zerginator        2013-03-12 07:56:27 +0100  60)      * {@link com.owncloud.android.utils.OwnCloudVersion}
8e36e7cc src/com/owncloud/android/authenticator/AccountAuthenticator.java  (zerginator        2013-03-12 07:56:27 +0100  61)      * 
8e36e7cc src/com/owncloud/android/authenticator/AccountAuthenticator.java  (zerginator        2013-03-12 07:56:27 +0100  62)      * @deprecated
8e36e7cc src/com/owncloud/android/authenticator/AccountAuthenticator.java  (zerginator        2013-03-12 07:56:27 +0100  63)      */
8e36e7cc src/com/owncloud/android/authenticator/AccountAuthenticator.java  (zerginator        2013-03-12 07:56:27 +0100  64)     public static final String KEY_OC_URL = "oc_url";
8e36e7cc src/com/owncloud/android/authenticator/AccountAuthenticator.java  (zerginator        2013-03-12 07:56:27 +0100  65)     /**
8e36e7cc src/com/owncloud/android/authenticator/AccountAuthenticator.java  (zerginator        2013-03-12 07:56:27 +0100  66)      * Version should be 3 numbers separated by dot so it can be parsed by
8e36e7cc src/com/owncloud/android/authenticator/AccountAuthenticator.java  (zerginator        2013-03-12 07:56:27 +0100  67)      * {@link com.owncloud.android.utils.OwnCloudVersion}
8e36e7cc src/com/owncloud/android/authenticator/AccountAuthenticator.java  (zerginator        2013-03-12 07:56:27 +0100  68)      */
8e36e7cc src/com/owncloud/android/authenticator/AccountAuthenticator.java  (zerginator        2013-03-12 07:56:27 +0100  69)     public static final String KEY_OC_VERSION = "oc_version";
8e36e7cc src/com/owncloud/android/authenticator/AccountAuthenticator.java  (zerginator        2013-03-12 07:56:27 +0100  70)     /**
8e36e7cc src/com/owncloud/android/authenticator/AccountAuthenticator.java  (zerginator        2013-03-12 07:56:27 +0100  71)      * Base url should point to owncloud installation without trailing / ie:
8e36e7cc src/com/owncloud/android/authenticator/AccountAuthenticator.java  (zerginator        2013-03-12 07:56:27 +0100  72)      * http://server/path or https://owncloud.server
8e36e7cc src/com/owncloud/android/authenticator/AccountAuthenticator.java  (zerginator        2013-03-12 07:56:27 +0100  73)      */
8e36e7cc src/com/owncloud/android/authenticator/AccountAuthenticator.java  (zerginator        2013-03-12 07:56:27 +0100  74)     public static final String KEY_OC_BASE_URL = "oc_base_url";
2946d8dd src/com/owncloud/android/authentication/AccountAuthenticator.java (David A. Velasco  2013-04-25 19:39:22 +0200  75)     /**
2946d8dd src/com/owncloud/android/authentication/AccountAuthenticator.java (David A. Velasco  2013-04-25 19:39:22 +0200  76)      * Flag signaling if the ownCloud server can be accessed with OAuth2 access tokens.
2946d8dd src/com/owncloud/android/authentication/AccountAuthenticator.java (David A. Velasco  2013-04-25 19:39:22 +0200  77)      */
2946d8dd src/com/owncloud/android/authentication/AccountAuthenticator.java (David A. Velasco  2013-04-25 19:39:22 +0200  78)     public static final String KEY_SUPPORTS_OAUTH2 = "oc_supports_oauth2";
0713ba61 src/com/owncloud/android/authentication/AccountAuthenticator.java (David A. Velasco  2013-07-25 17:29:52 +0200  79)     /**
0713ba61 src/com/owncloud/android/authentication/AccountAuthenticator.java (David A. Velasco  2013-07-25 17:29:52 +0200  80)      * Flag signaling if the ownCloud server can be accessed with session cookies from SAML-based web single-sign-on.
0713ba61 src/com/owncloud/android/authentication/AccountAuthenticator.java (David A. Velasco  2013-07-25 17:29:52 +0200  81)      */
0713ba61 src/com/owncloud/android/authentication/AccountAuthenticator.java (David A. Velasco  2013-07-25 17:29:52 +0200  82)     public static final String KEY_SUPPORTS_SAML_WEB_SSO = "oc_supports_saml_web_sso";
2946d8dd src/com/owncloud/android/authentication/AccountAuthenticator.java (David A. Velasco  2013-04-25 19:39:22 +0200  83)     
2946d8dd src/com/owncloud/android/authentication/AccountAuthenticator.java (David A. Velasco  2013-04-25 19:39:22 +0200  84)     private static final String TAG = AccountAuthenticator.class.getSimpleName();
2946d8dd src/com/owncloud/android/authentication/AccountAuthenticator.java (David A. Velasco  2013-04-25 19:39:22 +0200  85)     
8e36e7cc src/com/owncloud/android/authenticator/AccountAuthenticator.java  (zerginator        2013-03-12 07:56:27 +0100  86)     private Context mContext;
8e36e7cc src/com/owncloud/android/authenticator/AccountAuthenticator.java  (zerginator        2013-03-12 07:56:27 +0100  87) 
8e36e7cc src/com/owncloud/android/authenticator/AccountAuthenticator.java  (zerginator        2013-03-12 07:56:27 +0100  88)     public AccountAuthenticator(Context context) {
8e36e7cc src/com/owncloud/android/authenticator/AccountAuthenticator.java  (zerginator        2013-03-12 07:56:27 +0100  89)         super(context);
8e36e7cc src/com/owncloud/android/authenticator/AccountAuthenticator.java  (zerginator        2013-03-12 07:56:27 +0100  90)         mContext = context;
8e36e7cc src/com/owncloud/android/authenticator/AccountAuthenticator.java  (zerginator        2013-03-12 07:56:27 +0100  91)     }
8e36e7cc src/com/owncloud/android/authenticator/AccountAuthenticator.java  (zerginator        2013-03-12 07:56:27 +0100  92) 
8e36e7cc src/com/owncloud/android/authenticator/AccountAuthenticator.java  (zerginator        2013-03-12 07:56:27 +0100  93)     /**
8e36e7cc src/com/owncloud/android/authenticator/AccountAuthenticator.java  (zerginator        2013-03-12 07:56:27 +0100  94)      * {@inheritDoc}
8e36e7cc src/com/owncloud/android/authenticator/AccountAuthenticator.java  (zerginator        2013-03-12 07:56:27 +0100  95)      */
8e36e7cc src/com/owncloud/android/authenticator/AccountAuthenticator.java  (zerginator        2013-03-12 07:56:27 +0100  96)     @Override
8e36e7cc src/com/owncloud/android/authenticator/AccountAuthenticator.java  (zerginator        2013-03-12 07:56:27 +0100  97)     public Bundle addAccount(AccountAuthenticatorResponse response,
8e36e7cc src/com/owncloud/android/authenticator/AccountAuthenticator.java  (zerginator        2013-03-12 07:56:27 +0100  98)             String accountType, String authTokenType,
8e36e7cc src/com/owncloud/android/authenticator/AccountAuthenticator.java  (zerginator        2013-03-12 07:56:27 +0100  99)             String[] requiredFeatures, Bundle options)
8e36e7cc src/com/owncloud/android/authenticator/AccountAuthenticator.java  (zerginator        2013-03-12 07:56:27 +0100 100)             throws NetworkErrorException {
8e36e7cc src/com/owncloud/android/authenticator/AccountAuthenticator.java  (zerginator        2013-03-12 07:56:27 +0100 101)         Log_OC.i(TAG, "Adding account with type " + accountType
8e36e7cc src/com/owncloud/android/authenticator/AccountAuthenticator.java  (zerginator        2013-03-12 07:56:27 +0100 102)                 + " and auth token " + authTokenType);
8e36e7cc src/com/owncloud/android/authenticator/AccountAuthenticator.java  (zerginator        2013-03-12 07:56:27 +0100 103)         try {
8e36e7cc src/com/owncloud/android/authenticator/AccountAuthenticator.java  (zerginator        2013-03-12 07:56:27 +0100 104)             validateAccountType(accountType);
8e36e7cc src/com/owncloud/android/authenticator/AccountAuthenticator.java  (zerginator        2013-03-12 07:56:27 +0100 105)         } catch (AuthenticatorException e) {
8e36e7cc src/com/owncloud/android/authenticator/AccountAuthenticator.java  (zerginator        2013-03-12 07:56:27 +0100 106)             Log_OC.e(TAG, "Failed to validate account type " + accountType + ": "
8e36e7cc src/com/owncloud/android/authenticator/AccountAuthenticator.java  (zerginator        2013-03-12 07:56:27 +0100 107)                     + e.getMessage());
8e36e7cc src/com/owncloud/android/authenticator/AccountAuthenticator.java  (zerginator        2013-03-12 07:56:27 +0100 108)             e.printStackTrace();
8e36e7cc src/com/owncloud/android/authenticator/AccountAuthenticator.java  (zerginator        2013-03-12 07:56:27 +0100 109)             return e.getFailureBundle();
8e36e7cc src/com/owncloud/android/authenticator/AccountAuthenticator.java  (zerginator        2013-03-12 07:56:27 +0100 110)         }
8e36e7cc src/com/owncloud/android/authenticator/AccountAuthenticator.java  (zerginator        2013-03-12 07:56:27 +0100 111)         final Intent intent = new Intent(mContext, AuthenticatorActivity.class);
2946d8dd src/com/owncloud/android/authentication/AccountAuthenticator.java (David A. Velasco  2013-04-25 19:39:22 +0200 112)         intent.putExtra(AccountManager.KEY_ACCOUNT_AUTHENTICATOR_RESPONSE, response);
8e36e7cc src/com/owncloud/android/authenticator/AccountAuthenticator.java  (zerginator        2013-03-12 07:56:27 +0100 113)         intent.putExtra(KEY_AUTH_TOKEN_TYPE, authTokenType);
8e36e7cc src/com/owncloud/android/authenticator/AccountAuthenticator.java  (zerginator        2013-03-12 07:56:27 +0100 114)         intent.putExtra(KEY_REQUIRED_FEATURES, requiredFeatures);
8e36e7cc src/com/owncloud/android/authenticator/AccountAuthenticator.java  (zerginator        2013-03-12 07:56:27 +0100 115)         intent.putExtra(KEY_LOGIN_OPTIONS, options);
2946d8dd src/com/owncloud/android/authentication/AccountAuthenticator.java (David A. Velasco  2013-04-25 19:39:22 +0200 116)         intent.putExtra(AuthenticatorActivity.EXTRA_ACTION, AuthenticatorActivity.ACTION_CREATE);
8e36e7cc src/com/owncloud/android/authenticator/AccountAuthenticator.java  (zerginator        2013-03-12 07:56:27 +0100 117) 
8e36e7cc src/com/owncloud/android/authenticator/AccountAuthenticator.java  (zerginator        2013-03-12 07:56:27 +0100 118)         setIntentFlags(intent);
2946d8dd src/com/owncloud/android/authentication/AccountAuthenticator.java (David A. Velasco  2013-04-25 19:39:22 +0200 119)         
8e36e7cc src/com/owncloud/android/authenticator/AccountAuthenticator.java  (zerginator        2013-03-12 07:56:27 +0100 120)         final Bundle bundle = new Bundle();
8e36e7cc src/com/owncloud/android/authenticator/AccountAuthenticator.java  (zerginator        2013-03-12 07:56:27 +0100 121)         bundle.putParcelable(AccountManager.KEY_INTENT, intent);
8e36e7cc src/com/owncloud/android/authenticator/AccountAuthenticator.java  (zerginator        2013-03-12 07:56:27 +0100 122)         return bundle;
8e36e7cc src/com/owncloud/android/authenticator/AccountAuthenticator.java  (zerginator        2013-03-12 07:56:27 +0100 123)     }
8e36e7cc src/com/owncloud/android/authenticator/AccountAuthenticator.java  (zerginator        2013-03-12 07:56:27 +0100 124) 
00000000 src/com/owncloud/android/authentication/AccountAuthenticator.java (Not Committed Yet 2013-10-18 00:25:10 -0700 125)     /**
00000000 src/com/owncloud/android/authentication/AccountAuthenticator.java (Not Committed Yet 2013-10-18 00:25:10 -0700 126)      * {@inheritDoc}
00000000 src/com/owncloud/android/authentication/AccountAuthenticator.java (Not Committed Yet 2013-10-18 00:25:10 -0700 127)      */
00000000 src/com/owncloud/android/authentication/AccountAuthenticator.java (Not Committed Yet 2013-10-18 00:25:10 -0700 128)     @Override
00000000 src/com/owncloud/android/authentication/AccountAuthenticator.java (Not Committed Yet 2013-10-18 00:25:10 -0700 129)     public Bundle confirmCredentials(AccountAuthenticatorResponse response,
00000000 src/com/owncloud/android/authentication/AccountAuthenticator.java (Not Committed Yet 2013-10-18 00:25:10 -0700 130)             Account account, Bundle options) throws NetworkErrorException {
00000000 src/com/owncloud/android/authentication/AccountAuthenticator.java (Not Committed Yet 2013-10-18 00:25:10 -0700 131)         try {
00000000 src/com/owncloud/android/authentication/AccountAuthenticator.java (Not Committed Yet 2013-10-18 00:25:10 -0700 132)             validateAccountType(account.type);
00000000 src/com/owncloud/android/authentication/AccountAuthenticator.java (Not Committed Yet 2013-10-18 00:25:10 -0700 133)         } catch (AuthenticatorException e) {
00000000 src/com/owncloud/android/authentication/AccountAuthenticator.java (Not Committed Yet 2013-10-18 00:25:10 -0700 134)             Log_OC.e(TAG, "Failed to validate account type " + account.type + ": "
00000000 src/com/owncloud/android/authentication/AccountAuthenticator.java (Not Committed Yet 2013-10-18 00:25:10 -0700 135)                     + e.getMessage());
00000000 src/com/owncloud/android/authentication/AccountAuthenticator.java (Not Committed Yet 2013-10-18 00:25:10 -0700 136)             e.printStackTrace();
00000000 src/com/owncloud/android/authentication/AccountAuthenticator.java (Not Committed Yet 2013-10-18 00:25:10 -0700 137)             return e.getFailureBundle();
00000000 src/com/owncloud/android/authentication/AccountAuthenticator.java (Not Committed Yet 2013-10-18 00:25:10 -0700 138)         }
00000000 src/com/owncloud/android/authentication/AccountAuthenticator.java (Not Committed Yet 2013-10-18 00:25:10 -0700 139)         Intent intent = new Intent(mContext, AuthenticatorActivity.class);
00000000 src/com/owncloud/android/authentication/AccountAuthenticator.java (Not Committed Yet 2013-10-18 00:25:10 -0700 140)         intent.putExtra(AccountManager.KEY_ACCOUNT_AUTHENTICATOR_RESPONSE,
00000000 src/com/owncloud/android/authentication/AccountAuthenticator.java (Not Committed Yet 2013-10-18 00:25:10 -0700 141)                 response);
00000000 src/com/owncloud/android/authentication/AccountAuthenticator.java (Not Committed Yet 2013-10-18 00:25:10 -0700 142)         intent.putExtra(KEY_ACCOUNT, account);
00000000 src/com/owncloud/android/authentication/AccountAuthenticator.java (Not Committed Yet 2013-10-18 00:25:10 -0700 143)         intent.putExtra(KEY_LOGIN_OPTIONS, options);
00000000 src/com/owncloud/android/authentication/AccountAuthenticator.java (Not Committed Yet 2013-10-18 00:25:10 -0700 144) 
00000000 src/com/owncloud/android/authentication/AccountAuthenticator.java (Not Committed Yet 2013-10-18 00:25:10 -0700 145)         setIntentFlags(intent);
00000000 src/com/owncloud/android/authentication/AccountAuthenticator.java (Not Committed Yet 2013-10-18 00:25:10 -0700 146) 
00000000 src/com/owncloud/android/authentication/AccountAuthenticator.java (Not Committed Yet 2013-10-18 00:25:10 -0700 147)         Bundle resultBundle = new Bundle();
00000000 src/com/owncloud/android/authentication/AccountAuthenticator.java (Not Committed Yet 2013-10-18 00:25:10 -0700 148)         resultBundle.putParcelable(AccountManager.KEY_INTENT, intent);
00000000 src/com/owncloud/android/authentication/AccountAuthenticator.java (Not Committed Yet 2013-10-18 00:25:10 -0700 149)         return resultBundle;
00000000 src/com/owncloud/android/authentication/AccountAuthenticator.java (Not Committed Yet 2013-10-18 00:25:10 -0700 150)     }
00000000 src/com/owncloud/android/authentication/AccountAuthenticator.java (Not Committed Yet 2013-10-18 00:25:10 -0700 151) 
00000000 src/com/owncloud/android/authentication/AccountAuthenticator.java (Not Committed Yet 2013-10-18 00:25:10 -0700 152)     @Override
00000000 src/com/owncloud/android/authentication/AccountAuthenticator.java (Not Committed Yet 2013-10-18 00:25:10 -0700 153)     public Bundle editProperties(AccountAuthenticatorResponse response,
00000000 src/com/owncloud/android/authentication/AccountAuthenticator.java (Not Committed Yet 2013-10-18 00:25:10 -0700 154)             String accountType) {
00000000 src/com/owncloud/android/authentication/AccountAuthenticator.java (Not Committed Yet 2013-10-18 00:25:10 -0700 155)         return null;
00000000 src/com/owncloud/android/authentication/AccountAuthenticator.java (Not Committed Yet 2013-10-18 00:25:10 -0700 156)     }
00000000 src/com/owncloud/android/authentication/AccountAuthenticator.java (Not Committed Yet 2013-10-18 00:25:10 -0700 157) 
8e36e7cc src/com/owncloud/android/authenticator/AccountAuthenticator.java  (zerginator        2013-03-12 07:56:27 +0100 158)     /**
8e36e7cc src/com/owncloud/android/authenticator/AccountAuthenticator.java  (zerginator        2013-03-12 07:56:27 +0100 159)      * {@inheritDoc}
8e36e7cc src/com/owncloud/android/authenticator/AccountAuthenticator.java  (zerginator        2013-03-12 07:56:27 +0100 160)      */
8e36e7cc src/com/owncloud/android/authenticator/AccountAuthenticator.java  (zerginator        2013-03-12 07:56:27 +0100 161)     @Override
8e36e7cc src/com/owncloud/android/authenticator/AccountAuthenticator.java  (zerginator        2013-03-12 07:56:27 +0100 162)     public Bundle getAuthToken(AccountAuthenticatorResponse response,
8e36e7cc src/com/owncloud/android/authenticator/AccountAuthenticator.java  (zerginator        2013-03-12 07:56:27 +0100 163)             Account account, String authTokenType, Bundle options)
8e36e7cc src/com/owncloud/android/authenticator/AccountAuthenticator.java  (zerginator        2013-03-12 07:56:27 +0100 164)             throws NetworkErrorException {
2946d8dd src/com/owncloud/android/authentication/AccountAuthenticator.java (David A. Velasco  2013-04-25 19:39:22 +0200 165)         /// validate parameters
8e36e7cc src/com/owncloud/android/authenticator/AccountAuthenticator.java  (zerginator        2013-03-12 07:56:27 +0100 166)         try {
8e36e7cc src/com/owncloud/android/authenticator/AccountAuthenticator.java  (zerginator        2013-03-12 07:56:27 +0100 167)             validateAccountType(account.type);
8e36e7cc src/com/owncloud/android/authenticator/AccountAuthenticator.java  (zerginator        2013-03-12 07:56:27 +0100 168)             validateAuthTokenType(authTokenType);
8e36e7cc src/com/owncloud/android/authenticator/AccountAuthenticator.java  (zerginator        2013-03-12 07:56:27 +0100 169)         } catch (AuthenticatorException e) {
8e36e7cc src/com/owncloud/android/authenticator/AccountAuthenticator.java  (zerginator        2013-03-12 07:56:27 +0100 170)             Log_OC.e(TAG, "Failed to validate account type " + account.type + ": "
8e36e7cc src/com/owncloud/android/authenticator/AccountAuthenticator.java  (zerginator        2013-03-12 07:56:27 +0100 171)                     + e.getMessage());
8e36e7cc src/com/owncloud/android/authenticator/AccountAuthenticator.java  (zerginator        2013-03-12 07:56:27 +0100 172)             e.printStackTrace();
8e36e7cc src/com/owncloud/android/authenticator/AccountAuthenticator.java  (zerginator        2013-03-12 07:56:27 +0100 173)             return e.getFailureBundle();
8e36e7cc src/com/owncloud/android/authenticator/AccountAuthenticator.java  (zerginator        2013-03-12 07:56:27 +0100 174)         }
2946d8dd src/com/owncloud/android/authentication/AccountAuthenticator.java (David A. Velasco  2013-04-25 19:39:22 +0200 175)         
2946d8dd src/com/owncloud/android/authentication/AccountAuthenticator.java (David A. Velasco  2013-04-25 19:39:22 +0200 176)         /// check if required token is stored
8e36e7cc src/com/owncloud/android/authenticator/AccountAuthenticator.java  (zerginator        2013-03-12 07:56:27 +0100 177)         final AccountManager am = AccountManager.get(mContext);
2946d8dd src/com/owncloud/android/authentication/AccountAuthenticator.java (David A. Velasco  2013-04-25 19:39:22 +0200 178)         String accessToken;
2946d8dd src/com/owncloud/android/authentication/AccountAuthenticator.java (David A. Velasco  2013-04-25 19:39:22 +0200 179)         if (authTokenType.equals(AUTH_TOKEN_TYPE_PASSWORD)) {
2946d8dd src/com/owncloud/android/authentication/AccountAuthenticator.java (David A. Velasco  2013-04-25 19:39:22 +0200 180)             accessToken = am.getPassword(account);
2946d8dd src/com/owncloud/android/authentication/AccountAuthenticator.java (David A. Velasco  2013-04-25 19:39:22 +0200 181)         } else {
2946d8dd src/com/owncloud/android/authentication/AccountAuthenticator.java (David A. Velasco  2013-04-25 19:39:22 +0200 182)             accessToken = am.peekAuthToken(account, authTokenType);
2946d8dd src/com/owncloud/android/authentication/AccountAuthenticator.java (David A. Velasco  2013-04-25 19:39:22 +0200 183)         }
2946d8dd src/com/owncloud/android/authentication/AccountAuthenticator.java (David A. Velasco  2013-04-25 19:39:22 +0200 184)         if (accessToken != null) {
8e36e7cc src/com/owncloud/android/authenticator/AccountAuthenticator.java  (zerginator        2013-03-12 07:56:27 +0100 185)             final Bundle result = new Bundle();
8e36e7cc src/com/owncloud/android/authenticator/AccountAuthenticator.java  (zerginator        2013-03-12 07:56:27 +0100 186)             result.putString(AccountManager.KEY_ACCOUNT_NAME, account.name);
8e36e7cc src/com/owncloud/android/authenticator/AccountAuthenticator.java  (zerginator        2013-03-12 07:56:27 +0100 187)             result.putString(AccountManager.KEY_ACCOUNT_TYPE, ACCOUNT_TYPE);
2946d8dd src/com/owncloud/android/authentication/AccountAuthenticator.java (David A. Velasco  2013-04-25 19:39:22 +0200 188)             result.putString(AccountManager.KEY_AUTHTOKEN, accessToken);
8e36e7cc src/com/owncloud/android/authenticator/AccountAuthenticator.java  (zerginator        2013-03-12 07:56:27 +0100 189)             return result;
8e36e7cc src/com/owncloud/android/authenticator/AccountAuthenticator.java  (zerginator        2013-03-12 07:56:27 +0100 190)         }
2946d8dd src/com/owncloud/android/authentication/AccountAuthenticator.java (David A. Velasco  2013-04-25 19:39:22 +0200 191)         
2946d8dd src/com/owncloud/android/authentication/AccountAuthenticator.java (David A. Velasco  2013-04-25 19:39:22 +0200 192)         /// if not stored, return Intent to access the AuthenticatorActivity and UPDATE the token for the account
8e36e7cc src/com/owncloud/android/authenticator/AccountAuthenticator.java  (zerginator        2013-03-12 07:56:27 +0100 193)         final Intent intent = new Intent(mContext, AuthenticatorActivity.class);
2946d8dd src/com/owncloud/android/authentication/AccountAuthenticator.java (David A. Velasco  2013-04-25 19:39:22 +0200 194)         intent.putExtra(AccountManager.KEY_ACCOUNT_AUTHENTICATOR_RESPONSE, response);
8e36e7cc src/com/owncloud/android/authenticator/AccountAuthenticator.java  (zerginator        2013-03-12 07:56:27 +0100 195)         intent.putExtra(KEY_AUTH_TOKEN_TYPE, authTokenType);
8e36e7cc src/com/owncloud/android/authenticator/AccountAuthenticator.java  (zerginator        2013-03-12 07:56:27 +0100 196)         intent.putExtra(KEY_LOGIN_OPTIONS, options);
2946d8dd src/com/owncloud/android/authentication/AccountAuthenticator.java (David A. Velasco  2013-04-25 19:39:22 +0200 197)         intent.putExtra(AuthenticatorActivity.EXTRA_ACCOUNT, account);
8f1566a2 src/com/owncloud/android/authentication/AccountAuthenticator.java (David A. Velasco  2013-06-21 14:16:45 +0200 198)         intent.putExtra(AuthenticatorActivity.EXTRA_ENFORCED_UPDATE, true);
2946d8dd src/com/owncloud/android/authentication/AccountAuthenticator.java (David A. Velasco  2013-04-25 19:39:22 +0200 199)         intent.putExtra(AuthenticatorActivity.EXTRA_ACTION, AuthenticatorActivity.ACTION_UPDATE_TOKEN);
2946d8dd src/com/owncloud/android/authentication/AccountAuthenticator.java (David A. Velasco  2013-04-25 19:39:22 +0200 200)         
8e36e7cc src/com/owncloud/android/authenticator/AccountAuthenticator.java  (zerginator        2013-03-12 07:56:27 +0100 201) 
8e36e7cc src/com/owncloud/android/authenticator/AccountAuthenticator.java  (zerginator        2013-03-12 07:56:27 +0100 202)         final Bundle bundle = new Bundle();
8e36e7cc src/com/owncloud/android/authenticator/AccountAuthenticator.java  (zerginator        2013-03-12 07:56:27 +0100 203)         bundle.putParcelable(AccountManager.KEY_INTENT, intent);
8e36e7cc src/com/owncloud/android/authenticator/AccountAuthenticator.java  (zerginator        2013-03-12 07:56:27 +0100 204)         return bundle;
8e36e7cc src/com/owncloud/android/authenticator/AccountAuthenticator.java  (zerginator        2013-03-12 07:56:27 +0100 205)     }
8e36e7cc src/com/owncloud/android/authenticator/AccountAuthenticator.java  (zerginator        2013-03-12 07:56:27 +0100 206) 
8e36e7cc src/com/owncloud/android/authenticator/AccountAuthenticator.java  (zerginator        2013-03-12 07:56:27 +0100 207)     @Override
8e36e7cc src/com/owncloud/android/authenticator/AccountAuthenticator.java  (zerginator        2013-03-12 07:56:27 +0100 208)     public String getAuthTokenLabel(String authTokenType) {
8e36e7cc src/com/owncloud/android/authenticator/AccountAuthenticator.java  (zerginator        2013-03-12 07:56:27 +0100 209)         return null;
8e36e7cc src/com/owncloud/android/authenticator/AccountAuthenticator.java  (zerginator        2013-03-12 07:56:27 +0100 210)     }
8e36e7cc src/com/owncloud/android/authenticator/AccountAuthenticator.java  (zerginator        2013-03-12 07:56:27 +0100 211) 
8e36e7cc src/com/owncloud/android/authenticator/AccountAuthenticator.java  (zerginator        2013-03-12 07:56:27 +0100 212)     @Override
8e36e7cc src/com/owncloud/android/authenticator/AccountAuthenticator.java  (zerginator        2013-03-12 07:56:27 +0100 213)     public Bundle hasFeatures(AccountAuthenticatorResponse response,
8e36e7cc src/com/owncloud/android/authenticator/AccountAuthenticator.java  (zerginator        2013-03-12 07:56:27 +0100 214)             Account account, String[] features) throws NetworkErrorException {
8e36e7cc src/com/owncloud/android/authenticator/AccountAuthenticator.java  (zerginator        2013-03-12 07:56:27 +0100 215)         final Bundle result = new Bundle();
8e36e7cc src/com/owncloud/android/authenticator/AccountAuthenticator.java  (zerginator        2013-03-12 07:56:27 +0100 216)         result.putBoolean(AccountManager.KEY_BOOLEAN_RESULT, true);
8e36e7cc src/com/owncloud/android/authenticator/AccountAuthenticator.java  (zerginator        2013-03-12 07:56:27 +0100 217)         return result;
8e36e7cc src/com/owncloud/android/authenticator/AccountAuthenticator.java  (zerginator        2013-03-12 07:56:27 +0100 218)     }
8e36e7cc src/com/owncloud/android/authenticator/AccountAuthenticator.java  (zerginator        2013-03-12 07:56:27 +0100 219) 
8e36e7cc src/com/owncloud/android/authenticator/AccountAuthenticator.java  (zerginator        2013-03-12 07:56:27 +0100 220)     @Override
8e36e7cc src/com/owncloud/android/authenticator/AccountAuthenticator.java  (zerginator        2013-03-12 07:56:27 +0100 221)     public Bundle updateCredentials(AccountAuthenticatorResponse response,
8e36e7cc src/com/owncloud/android/authenticator/AccountAuthenticator.java  (zerginator        2013-03-12 07:56:27 +0100 222)             Account account, String authTokenType, Bundle options)
8e36e7cc src/com/owncloud/android/authenticator/AccountAuthenticator.java  (zerginator        2013-03-12 07:56:27 +0100 223)             throws NetworkErrorException {
8e36e7cc src/com/owncloud/android/authenticator/AccountAuthenticator.java  (zerginator        2013-03-12 07:56:27 +0100 224)         final Intent intent = new Intent(mContext, AuthenticatorActivity.class);
8e36e7cc src/com/owncloud/android/authenticator/AccountAuthenticator.java  (zerginator        2013-03-12 07:56:27 +0100 225)         intent.putExtra(AccountManager.KEY_ACCOUNT_AUTHENTICATOR_RESPONSE,
8e36e7cc src/com/owncloud/android/authenticator/AccountAuthenticator.java  (zerginator        2013-03-12 07:56:27 +0100 226)                 response);
8e36e7cc src/com/owncloud/android/authenticator/AccountAuthenticator.java  (zerginator        2013-03-12 07:56:27 +0100 227)         intent.putExtra(KEY_ACCOUNT, account);
8e36e7cc src/com/owncloud/android/authenticator/AccountAuthenticator.java  (zerginator        2013-03-12 07:56:27 +0100 228)         intent.putExtra(KEY_AUTH_TOKEN_TYPE, authTokenType);
8e36e7cc src/com/owncloud/android/authenticator/AccountAuthenticator.java  (zerginator        2013-03-12 07:56:27 +0100 229)         intent.putExtra(KEY_LOGIN_OPTIONS, options);
8e36e7cc src/com/owncloud/android/authenticator/AccountAuthenticator.java  (zerginator        2013-03-12 07:56:27 +0100 230)         setIntentFlags(intent);
8e36e7cc src/com/owncloud/android/authenticator/AccountAuthenticator.java  (zerginator        2013-03-12 07:56:27 +0100 231) 
8e36e7cc src/com/owncloud/android/authenticator/AccountAuthenticator.java  (zerginator        2013-03-12 07:56:27 +0100 232)         final Bundle bundle = new Bundle();
8e36e7cc src/com/owncloud/android/authenticator/AccountAuthenticator.java  (zerginator        2013-03-12 07:56:27 +0100 233)         bundle.putParcelable(AccountManager.KEY_INTENT, intent);
8e36e7cc src/com/owncloud/android/authenticator/AccountAuthenticator.java  (zerginator        2013-03-12 07:56:27 +0100 234)         return bundle;
8e36e7cc src/com/owncloud/android/authenticator/AccountAuthenticator.java  (zerginator        2013-03-12 07:56:27 +0100 235)     }
8e36e7cc src/com/owncloud/android/authenticator/AccountAuthenticator.java  (zerginator        2013-03-12 07:56:27 +0100 236) 
8e36e7cc src/com/owncloud/android/authenticator/AccountAuthenticator.java  (zerginator        2013-03-12 07:56:27 +0100 237)     @Override
8e36e7cc src/com/owncloud/android/authenticator/AccountAuthenticator.java  (zerginator        2013-03-12 07:56:27 +0100 238)     public Bundle getAccountRemovalAllowed(
8e36e7cc src/com/owncloud/android/authenticator/AccountAuthenticator.java  (zerginator        2013-03-12 07:56:27 +0100 239)             AccountAuthenticatorResponse response, Account account)
8e36e7cc src/com/owncloud/android/authenticator/AccountAuthenticator.java  (zerginator        2013-03-12 07:56:27 +0100 240)             throws NetworkErrorException {
8e36e7cc src/com/owncloud/android/authenticator/AccountAuthenticator.java  (zerginator        2013-03-12 07:56:27 +0100 241)         return super.getAccountRemovalAllowed(response, account);
8e36e7cc src/com/owncloud/android/authenticator/AccountAuthenticator.java  (zerginator        2013-03-12 07:56:27 +0100 242)     }
8e36e7cc src/com/owncloud/android/authenticator/AccountAuthenticator.java  (zerginator        2013-03-12 07:56:27 +0100 243) 
8e36e7cc src/com/owncloud/android/authenticator/AccountAuthenticator.java  (zerginator        2013-03-12 07:56:27 +0100 244)     private void setIntentFlags(Intent intent) {
8e36e7cc src/com/owncloud/android/authenticator/AccountAuthenticator.java  (zerginator        2013-03-12 07:56:27 +0100 245)         intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
8e36e7cc src/com/owncloud/android/authenticator/AccountAuthenticator.java  (zerginator        2013-03-12 07:56:27 +0100 246)         intent.addFlags(Intent.FLAG_ACTIVITY_EXCLUDE_FROM_RECENTS);
8e36e7cc src/com/owncloud/android/authenticator/AccountAuthenticator.java  (zerginator        2013-03-12 07:56:27 +0100 247)         intent.addFlags(Intent.FLAG_FROM_BACKGROUND);
8e36e7cc src/com/owncloud/android/authenticator/AccountAuthenticator.java  (zerginator        2013-03-12 07:56:27 +0100 248)     }
8e36e7cc src/com/owncloud/android/authenticator/AccountAuthenticator.java  (zerginator        2013-03-12 07:56:27 +0100 249) 
8e36e7cc src/com/owncloud/android/authenticator/AccountAuthenticator.java  (zerginator        2013-03-12 07:56:27 +0100 250)     private void validateAccountType(String type)
8e36e7cc src/com/owncloud/android/authenticator/AccountAuthenticator.java  (zerginator        2013-03-12 07:56:27 +0100 251)             throws UnsupportedAccountTypeException {
8e36e7cc src/com/owncloud/android/authenticator/AccountAuthenticator.java  (zerginator        2013-03-12 07:56:27 +0100 252)         if (!type.equals(ACCOUNT_TYPE)) {
8e36e7cc src/com/owncloud/android/authenticator/AccountAuthenticator.java  (zerginator        2013-03-12 07:56:27 +0100 253)             throw new UnsupportedAccountTypeException();
8e36e7cc src/com/owncloud/android/authenticator/AccountAuthenticator.java  (zerginator        2013-03-12 07:56:27 +0100 254)         }
8e36e7cc src/com/owncloud/android/authenticator/AccountAuthenticator.java  (zerginator        2013-03-12 07:56:27 +0100 255)     }
8e36e7cc src/com/owncloud/android/authenticator/AccountAuthenticator.java  (zerginator        2013-03-12 07:56:27 +0100 256) 
00000000 src/com/owncloud/android/authentication/AccountAuthenticator.java (Not Committed Yet 2013-10-18 00:25:10 -0700 257)     private void validateAuthTokenType(String authTokenType)
00000000 src/com/owncloud/android/authentication/AccountAuthenticator.java (Not Committed Yet 2013-10-18 00:25:10 -0700 258)             throws UnsupportedAuthTokenTypeException {
00000000 src/com/owncloud/android/authentication/AccountAuthenticator.java (Not Committed Yet 2013-10-18 00:25:10 -0700 259)         if (!authTokenType.equals(AUTH_TOKEN_TYPE) &&
00000000 src/com/owncloud/android/authentication/AccountAuthenticator.java (Not Committed Yet 2013-10-18 00:25:10 -0700 260)             !authTokenType.equals(AUTH_TOKEN_TYPE_PASSWORD) &&
00000000 src/com/owncloud/android/authentication/AccountAuthenticator.java (Not Committed Yet 2013-10-18 00:25:10 -0700 261)             !authTokenType.equals(AUTH_TOKEN_TYPE_ACCESS_TOKEN) &&
52bc433b src/com/owncloud/android/authentication/AccountAuthenticator.java (David A. Velasco  2013-08-01 17:47:09 +0200 262)             !authTokenType.equals(AUTH_TOKEN_TYPE_REFRESH_TOKEN) &&
00000000 src/com/owncloud/android/authentication/AccountAuthenticator.java (Not Committed Yet 2013-10-18 00:25:10 -0700 263)             !authTokenType.equals(AUTH_TOKEN_TYPE_SAML_WEB_SSO_SESSION_COOKIE)) {
00000000 src/com/owncloud/android/authentication/AccountAuthenticator.java (Not Committed Yet 2013-10-18 00:25:10 -0700 264)             throw new UnsupportedAuthTokenTypeException();
00000000 src/com/owncloud/android/authentication/AccountAuthenticator.java (Not Committed Yet 2013-10-18 00:25:10 -0700 265)         }
00000000 src/com/owncloud/android/authentication/AccountAuthenticator.java (Not Committed Yet 2013-10-18 00:25:10 -0700 266)     }
00000000 src/com/owncloud/android/authentication/AccountAuthenticator.java (Not Committed Yet 2013-10-18 00:25:10 -0700 267) 
00000000 src/com/owncloud/android/authentication/AccountAuthenticator.java (Not Committed Yet 2013-10-18 00:25:10 -0700 268)     public static class AuthenticatorException extends Exception {
00000000 src/com/owncloud/android/authentication/AccountAuthenticator.java (Not Committed Yet 2013-10-18 00:25:10 -0700 269)         private static final long serialVersionUID = 1L;
00000000 src/com/owncloud/android/authentication/AccountAuthenticator.java (Not Committed Yet 2013-10-18 00:25:10 -0700 270)         private Bundle mFailureBundle;
00000000 src/com/owncloud/android/authentication/AccountAuthenticator.java (Not Committed Yet 2013-10-18 00:25:10 -0700 271) 
00000000 src/com/owncloud/android/authentication/AccountAuthenticator.java (Not Committed Yet 2013-10-18 00:25:10 -0700 272)         public AuthenticatorException(int code, String errorMsg) {
00000000 src/com/owncloud/android/authentication/AccountAuthenticator.java (Not Committed Yet 2013-10-18 00:25:10 -0700 273)             mFailureBundle = new Bundle();
00000000 src/com/owncloud/android/authentication/AccountAuthenticator.java (Not Committed Yet 2013-10-18 00:25:10 -0700 274)             mFailureBundle.putInt(AccountManager.KEY_ERROR_CODE, code);
00000000 src/com/owncloud/android/authentication/AccountAuthenticator.java (Not Committed Yet 2013-10-18 00:25:10 -0700 275)             mFailureBundle
00000000 src/com/owncloud/android/authentication/AccountAuthenticator.java (Not Committed Yet 2013-10-18 00:25:10 -0700 276)                     .putString(AccountManager.KEY_ERROR_MESSAGE, errorMsg);
00000000 src/com/owncloud/android/authentication/AccountAuthenticator.java (Not Committed Yet 2013-10-18 00:25:10 -0700 277)         }
00000000 src/com/owncloud/android/authentication/AccountAuthenticator.java (Not Committed Yet 2013-10-18 00:25:10 -0700 278) 
00000000 src/com/owncloud/android/authentication/AccountAuthenticator.java (Not Committed Yet 2013-10-18 00:25:10 -0700 279)         public Bundle getFailureBundle() {
00000000 src/com/owncloud/android/authentication/AccountAuthenticator.java (Not Committed Yet 2013-10-18 00:25:10 -0700 280)             return mFailureBundle;
00000000 src/com/owncloud/android/authentication/AccountAuthenticator.java (Not Committed Yet 2013-10-18 00:25:10 -0700 281)         }
00000000 src/com/owncloud/android/authentication/AccountAuthenticator.java (Not Committed Yet 2013-10-18 00:25:10 -0700 282)     }
00000000 src/com/owncloud/android/authentication/AccountAuthenticator.java (Not Committed Yet 2013-10-18 00:25:10 -0700 283) 
00000000 src/com/owncloud/android/authentication/AccountAuthenticator.java (Not Committed Yet 2013-10-18 00:25:10 -0700 284)     public static class UnsupportedAccountTypeException extends
00000000 src/com/owncloud/android/authentication/AccountAuthenticator.java (Not Committed Yet 2013-10-18 00:25:10 -0700 285)             AuthenticatorException {
00000000 src/com/owncloud/android/authentication/AccountAuthenticator.java (Not Committed Yet 2013-10-18 00:25:10 -0700 286)         private static final long serialVersionUID = 1L;
00000000 src/com/owncloud/android/authentication/AccountAuthenticator.java (Not Committed Yet 2013-10-18 00:25:10 -0700 287) 
00000000 src/com/owncloud/android/authentication/AccountAuthenticator.java (Not Committed Yet 2013-10-18 00:25:10 -0700 288)         public UnsupportedAccountTypeException() {
00000000 src/com/owncloud/android/authentication/AccountAuthenticator.java (Not Committed Yet 2013-10-18 00:25:10 -0700 289)             super(AccountManager.ERROR_CODE_UNSUPPORTED_OPERATION,
00000000 src/com/owncloud/android/authentication/AccountAuthenticator.java (Not Committed Yet 2013-10-18 00:25:10 -0700 290)                     "Unsupported account type");
00000000 src/com/owncloud/android/authentication/AccountAuthenticator.java (Not Committed Yet 2013-10-18 00:25:10 -0700 291)         }
00000000 src/com/owncloud/android/authentication/AccountAuthenticator.java (Not Committed Yet 2013-10-18 00:25:10 -0700 292)     }
00000000 src/com/owncloud/android/authentication/AccountAuthenticator.java (Not Committed Yet 2013-10-18 00:25:10 -0700 293) 
00000000 src/com/owncloud/android/authentication/AccountAuthenticator.java (Not Committed Yet 2013-10-18 00:25:10 -0700 294)     public static class UnsupportedAuthTokenTypeException extends
00000000 src/com/owncloud/android/authentication/AccountAuthenticator.java (Not Committed Yet 2013-10-18 00:25:10 -0700 295)             AuthenticatorException {
00000000 src/com/owncloud/android/authentication/AccountAuthenticator.java (Not Committed Yet 2013-10-18 00:25:10 -0700 296)         private static final long serialVersionUID = 1L;
00000000 src/com/owncloud/android/authentication/AccountAuthenticator.java (Not Committed Yet 2013-10-18 00:25:10 -0700 297) 
00000000 src/com/owncloud/android/authentication/AccountAuthenticator.java (Not Committed Yet 2013-10-18 00:25:10 -0700 298)         public UnsupportedAuthTokenTypeException() {
00000000 src/com/owncloud/android/authentication/AccountAuthenticator.java (Not Committed Yet 2013-10-18 00:25:10 -0700 299)             super(AccountManager.ERROR_CODE_UNSUPPORTED_OPERATION,
00000000 src/com/owncloud/android/authentication/AccountAuthenticator.java (Not Committed Yet 2013-10-18 00:25:10 -0700 300)                     "Unsupported auth token type");
00000000 src/com/owncloud/android/authentication/AccountAuthenticator.java (Not Committed Yet 2013-10-18 00:25:10 -0700 301)         }
00000000 src/com/owncloud/android/authentication/AccountAuthenticator.java (Not Committed Yet 2013-10-18 00:25:10 -0700 302)     }
00000000 src/com/owncloud/android/authentication/AccountAuthenticator.java (Not Committed Yet 2013-10-18 00:25:10 -0700 303) 
00000000 src/com/owncloud/android/authentication/AccountAuthenticator.java (Not Committed Yet 2013-10-18 00:25:10 -0700 304)     public static class UnsupportedFeaturesException extends
00000000 src/com/owncloud/android/authentication/AccountAuthenticator.java (Not Committed Yet 2013-10-18 00:25:10 -0700 305)             AuthenticatorException {
00000000 src/com/owncloud/android/authentication/AccountAuthenticator.java (Not Committed Yet 2013-10-18 00:25:10 -0700 306)         public static final long serialVersionUID = 1L;
00000000 src/com/owncloud/android/authentication/AccountAuthenticator.java (Not Committed Yet 2013-10-18 00:25:10 -0700 307) 
00000000 src/com/owncloud/android/authentication/AccountAuthenticator.java (Not Committed Yet 2013-10-18 00:25:10 -0700 308)         public UnsupportedFeaturesException() {
00000000 src/com/owncloud/android/authentication/AccountAuthenticator.java (Not Committed Yet 2013-10-18 00:25:10 -0700 309)             super(AccountManager.ERROR_CODE_UNSUPPORTED_OPERATION,
00000000 src/com/owncloud/android/authentication/AccountAuthenticator.java (Not Committed Yet 2013-10-18 00:25:10 -0700 310)                     "Unsupported features");
00000000 src/com/owncloud/android/authentication/AccountAuthenticator.java (Not Committed Yet 2013-10-18 00:25:10 -0700 311)         }
00000000 src/com/owncloud/android/authentication/AccountAuthenticator.java (Not Committed Yet 2013-10-18 00:25:10 -0700 312)     }
00000000 src/com/owncloud/android/authentication/AccountAuthenticator.java (Not Committed Yet 2013-10-18 00:25:10 -0700 313) 
00000000 src/com/owncloud/android/authentication/AccountAuthenticator.java (Not Committed Yet 2013-10-18 00:25:10 -0700 314)     public static class AccessDeniedException extends AuthenticatorException {
00000000 src/com/owncloud/android/authentication/AccountAuthenticator.java (Not Committed Yet 2013-10-18 00:25:10 -0700 315)         public AccessDeniedException(int code, String errorMsg) {
00000000 src/com/owncloud/android/authentication/AccountAuthenticator.java (Not Committed Yet 2013-10-18 00:25:10 -0700 316)             super(AccountManager.ERROR_CODE_INVALID_RESPONSE, "Access Denied");
00000000 src/com/owncloud/android/authentication/AccountAuthenticator.java (Not Committed Yet 2013-10-18 00:25:10 -0700 317)         }
00000000 src/com/owncloud/android/authentication/AccountAuthenticator.java (Not Committed Yet 2013-10-18 00:25:10 -0700 318) 
00000000 src/com/owncloud/android/authentication/AccountAuthenticator.java (Not Committed Yet 2013-10-18 00:25:10 -0700 319)         private static final long serialVersionUID = 1L;
00000000 src/com/owncloud/android/authentication/AccountAuthenticator.java (Not Committed Yet 2013-10-18 00:25:10 -0700 320) 
00000000 src/com/owncloud/android/authentication/AccountAuthenticator.java (Not Committed Yet 2013-10-18 00:25:10 -0700 321)     }
00000000 src/com/owncloud/android/authentication/AccountAuthenticator.java (Not Committed Yet 2013-10-18 00:25:10 -0700 322) }
