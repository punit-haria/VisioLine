00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700   1) /* ownCloud Android client application
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700   2)  *   Copyright (C) 2012  Bartek Przybylski
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700   3)  *   Copyright (C) 2012-2013 ownCloud Inc.
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700   4)  *
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700   5)  *   This program is free software: you can redistribute it and/or modify
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700   6)  *   it under the terms of the GNU General Public License version 2,
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700   7)  *   as published by the Free Software Foundation.
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700   8)  *
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700   9)  *   This program is distributed in the hope that it will be useful,
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700  10)  *   but WITHOUT ANY WARRANTY; without even the implied warranty of
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700  11)  *   MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700  12)  *   GNU General Public License for more details.
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700  13)  *
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700  14)  *   You should have received a copy of the GNU General Public License
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700  15)  *   along with this program.  If not, see <http://www.gnu.org/licenses/>.
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700  16)  *
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700  17)  */
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700  18) 
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700  19) package com.owncloud.android.authentication;
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700  20) 
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700  21) import com.owncloud.android.utils.OwnCloudVersion;
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700  22) 
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700  23) import android.accounts.Account;
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700  24) import android.accounts.AccountManager;
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700  25) import android.accounts.AccountsException;
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700  26) import android.content.Context;
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700  27) import android.content.SharedPreferences;
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700  28) import android.preference.PreferenceManager;
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700  29) 
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700  30) public class AccountUtils {
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700  31)     public static final String WEBDAV_PATH_1_2 = "/webdav/owncloud.php";
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700  32)     public static final String WEBDAV_PATH_2_0 = "/files/webdav.php";
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700  33)     public static final String WEBDAV_PATH_4_0 = "/remote.php/webdav";
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700  34)     private static final String ODAV_PATH = "/remote.php/odav";
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700  35)     private static final String SAML_SSO_PATH = "/remote.php/webdav";
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700  36)     public static final String CARDDAV_PATH_2_0 = "/apps/contacts/carddav.php";
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700  37)     public static final String CARDDAV_PATH_4_0 = "/remote/carddav.php";
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700  38)     public static final String STATUS_PATH = "/status.php";
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700  39) 
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700  40)     /**
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700  41)      * Can be used to get the currently selected ownCloud {@link Account} in the
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700  42)      * application preferences.
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700  43)      * 
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700  44)      * @param   context     The current application {@link Context}
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700  45)      * @return              The ownCloud {@link Account} currently saved in preferences, or the first 
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700  46)      *                      {@link Account} available, if valid (still registered in the system as ownCloud 
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700  47)      *                      account). If none is available and valid, returns null.
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700  48)      */
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700  49)     public static Account getCurrentOwnCloudAccount(Context context) {
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700  50)         Account[] ocAccounts = AccountManager.get(context).getAccountsByType(
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700  51)                 AccountAuthenticator.ACCOUNT_TYPE);
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700  52)         Account defaultAccount = null;
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700  53) 
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700  54)         SharedPreferences appPreferences = PreferenceManager
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700  55)                 .getDefaultSharedPreferences(context);
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700  56)         String accountName = appPreferences
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700  57)                 .getString("select_oc_account", null);
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700  58) 
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700  59)         // account validation: the saved account MUST be in the list of ownCloud Accounts known by the AccountManager
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700  60)         if (accountName != null) {
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700  61)             for (Account account : ocAccounts) {
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700  62)                 if (account.name.equals(accountName)) {
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700  63)                     defaultAccount = account;
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700  64)                     break;
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700  65)                 }
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700  66)             }
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700  67)         }
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700  68)         
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700  69)         if (defaultAccount == null && ocAccounts.length != 0) {
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700  70)             // take first account as fallback
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700  71)             defaultAccount = ocAccounts[0];
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700  72)         }
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700  73) 
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700  74)         return defaultAccount;
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700  75)     }
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700  76) 
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700  77)     
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700  78)     public static boolean exists(Account account, Context context) {
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700  79)         Account[] ocAccounts = AccountManager.get(context).getAccountsByType(
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700  80)                 AccountAuthenticator.ACCOUNT_TYPE);
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700  81) 
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700  82)         if (account != null && account.name != null) {
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700  83)             for (Account ac : ocAccounts) {
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700  84)                 if (ac.name.equals(account.name)) {
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700  85)                     return true;
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700  86)                 }
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700  87)             }
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700  88)         }
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700  89)         return false;
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700  90)     }
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700  91)     
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700  92) 
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700  93)     /**
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700  94)      * Checks, whether or not there are any ownCloud accounts setup.
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700  95)      * 
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700  96)      * @return true, if there is at least one account.
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700  97)      */
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700  98)     public static boolean accountsAreSetup(Context context) {
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700  99)         AccountManager accMan = AccountManager.get(context);
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700 100)         Account[] accounts = accMan
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700 101)                 .getAccountsByType(AccountAuthenticator.ACCOUNT_TYPE);
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700 102)         return accounts.length > 0;
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700 103)     }
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700 104)     
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700 105)     
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700 106)     public static boolean setCurrentOwnCloudAccount(Context context, String accountName) {
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700 107)         boolean result = false;
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700 108)         if (accountName != null) {
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700 109)             Account[] ocAccounts = AccountManager.get(context).getAccountsByType(
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700 110)                     AccountAuthenticator.ACCOUNT_TYPE);
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700 111)             boolean found = false;
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700 112)             for (Account account : ocAccounts) {
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700 113)                 found = (account.name.equals(accountName));
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700 114)                 if (found) {
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700 115)                     SharedPreferences.Editor appPrefs = PreferenceManager
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700 116)                             .getDefaultSharedPreferences(context).edit();
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700 117)                     appPrefs.putString("select_oc_account", accountName);
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700 118)     
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700 119)                     appPrefs.commit();
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700 120)                     result = true;
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700 121)                     break;
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700 122)                 }
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700 123)             }
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700 124)         }
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700 125)         return result;
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700 126)     }
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700 127) 
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700 128)     /**
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700 129)      * 
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700 130)      * @param version version of owncloud
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700 131)      * @return webdav path for given OC version, null if OC version unknown
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700 132)      */
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700 133)     public static String getWebdavPath(OwnCloudVersion version, boolean supportsOAuth, boolean supportsSamlSso) {
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700 134)         if (version != null) {
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700 135)             if (supportsOAuth) {
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700 136)                 return ODAV_PATH;
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700 137)             }
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700 138)             if (supportsSamlSso) {
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700 139)                 return SAML_SSO_PATH;
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700 140)             }
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700 141)             if (version.compareTo(OwnCloudVersion.owncloud_v4) >= 0)
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700 142)                 return WEBDAV_PATH_4_0;
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700 143)             if (version.compareTo(OwnCloudVersion.owncloud_v3) >= 0
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700 144)                     || version.compareTo(OwnCloudVersion.owncloud_v2) >= 0)
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700 145)                 return WEBDAV_PATH_2_0;
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700 146)             if (version.compareTo(OwnCloudVersion.owncloud_v1) >= 0)
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700 147)                 return WEBDAV_PATH_1_2;
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700 148)         }
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700 149)         return null;
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700 150)     }
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700 151)     
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700 152)     /**
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700 153)      * Returns the proper URL path to access the WebDAV interface of an ownCloud server,
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700 154)      * according to its version and the authorization method used.
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700 155)      * 
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700 156)      * @param   version         Version of ownCloud server.
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700 157)      * @param   authTokenType   Authorization token type, matching some of the AUTH_TOKEN_TYPE_* constants in {@link AccountAuthenticator}. 
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700 158)      * @return                  WebDAV path for given OC version and authorization method, null if OC version is unknown.
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700 159)      */
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700 160)     public static String getWebdavPath(OwnCloudVersion version, String authTokenType) {
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700 161)         if (version != null) {
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700 162)             if (AccountAuthenticator.AUTH_TOKEN_TYPE_ACCESS_TOKEN.equals(authTokenType)) {
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700 163)                 return ODAV_PATH;
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700 164)             }
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700 165)             if (AccountAuthenticator.AUTH_TOKEN_TYPE_SAML_WEB_SSO_SESSION_COOKIE.equals(authTokenType)) {
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700 166)                 return SAML_SSO_PATH;
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700 167)             }
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700 168)             if (version.compareTo(OwnCloudVersion.owncloud_v4) >= 0)
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700 169)                 return WEBDAV_PATH_4_0;
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700 170)             if (version.compareTo(OwnCloudVersion.owncloud_v3) >= 0
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700 171)                     || version.compareTo(OwnCloudVersion.owncloud_v2) >= 0)
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700 172)                 return WEBDAV_PATH_2_0;
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700 173)             if (version.compareTo(OwnCloudVersion.owncloud_v1) >= 0)
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700 174)                 return WEBDAV_PATH_1_2;
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700 175)         }
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700 176)         return null;
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700 177)     }
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700 178)     
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700 179)     /**
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700 180)      * Constructs full url to host and webdav resource basing on host version
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700 181)      * @param context
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700 182)      * @param account
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700 183)      * @return url or null on failure
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700 184)      * @throws AccountNotFoundException     When 'account' is unknown for the AccountManager
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700 185)      */
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700 186)     public static String constructFullURLForAccount(Context context, Account account) throws AccountNotFoundException {
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700 187)         AccountManager ama = AccountManager.get(context);
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700 188)         String baseurl = ama.getUserData(account, AccountAuthenticator.KEY_OC_BASE_URL);
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700 189)         String strver  = ama.getUserData(account, AccountAuthenticator.KEY_OC_VERSION);
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700 190)         boolean supportsOAuth = (ama.getUserData(account, AccountAuthenticator.KEY_SUPPORTS_OAUTH2) != null);
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700 191)         boolean supportsSamlSso = (ama.getUserData(account, AccountAuthenticator.KEY_SUPPORTS_SAML_WEB_SSO) != null);
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700 192)         OwnCloudVersion ver = new OwnCloudVersion(strver);
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700 193)         String webdavpath = getWebdavPath(ver, supportsOAuth, supportsSamlSso);
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700 194) 
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700 195)         if (baseurl == null || webdavpath == null) 
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700 196)             throw new AccountNotFoundException(account, "Account not found", null);
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700 197)         
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700 198)         return baseurl + webdavpath;
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700 199)     }
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700 200)     
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700 201)     
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700 202)     public static class AccountNotFoundException extends AccountsException {
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700 203)         
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700 204)         /** Generated - should be refreshed every time the class changes!! */
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700 205)         private static final long serialVersionUID = -9013287181793186830L;
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700 206)         
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700 207)         private Account mFailedAccount; 
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700 208)                 
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700 209)         public AccountNotFoundException(Account failedAccount, String message, Throwable cause) {
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700 210)             super(message, cause);
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700 211)             mFailedAccount = failedAccount;
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700 212)         }
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700 213)         
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700 214)         public Account getFailedAccount() {
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700 215)             return mFailedAccount;
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700 216)         }
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700 217)     }
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700 218) 
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700 219) }
