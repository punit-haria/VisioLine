00000000 src/com/owncloud/android/syncadapter/AbstractOwnCloudSyncAdapter.java (Not Committed Yet 2013-10-18 00:25:12 -0700   1) /* ownCloud Android client application
00000000 src/com/owncloud/android/syncadapter/AbstractOwnCloudSyncAdapter.java (Not Committed Yet 2013-10-18 00:25:12 -0700   2)  *   Copyright (C) 2011  Bartek Przybylski
00000000 src/com/owncloud/android/syncadapter/AbstractOwnCloudSyncAdapter.java (Not Committed Yet 2013-10-18 00:25:12 -0700   3)  *   Copyright (C) 2012-2013 ownCloud Inc.
00000000 src/com/owncloud/android/syncadapter/AbstractOwnCloudSyncAdapter.java (Not Committed Yet 2013-10-18 00:25:12 -0700   4)  *
00000000 src/com/owncloud/android/syncadapter/AbstractOwnCloudSyncAdapter.java (Not Committed Yet 2013-10-18 00:25:12 -0700   5)  *   This program is free software: you can redistribute it and/or modify
00000000 src/com/owncloud/android/syncadapter/AbstractOwnCloudSyncAdapter.java (Not Committed Yet 2013-10-18 00:25:12 -0700   6)  *   it under the terms of the GNU General Public License version 2,
00000000 src/com/owncloud/android/syncadapter/AbstractOwnCloudSyncAdapter.java (Not Committed Yet 2013-10-18 00:25:12 -0700   7)  *   as published by the Free Software Foundation.
00000000 src/com/owncloud/android/syncadapter/AbstractOwnCloudSyncAdapter.java (Not Committed Yet 2013-10-18 00:25:12 -0700   8)  *
00000000 src/com/owncloud/android/syncadapter/AbstractOwnCloudSyncAdapter.java (Not Committed Yet 2013-10-18 00:25:12 -0700   9)  *   This program is distributed in the hope that it will be useful,
00000000 src/com/owncloud/android/syncadapter/AbstractOwnCloudSyncAdapter.java (Not Committed Yet 2013-10-18 00:25:12 -0700  10)  *   but WITHOUT ANY WARRANTY; without even the implied warranty of
00000000 src/com/owncloud/android/syncadapter/AbstractOwnCloudSyncAdapter.java (Not Committed Yet 2013-10-18 00:25:12 -0700  11)  *   MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
00000000 src/com/owncloud/android/syncadapter/AbstractOwnCloudSyncAdapter.java (Not Committed Yet 2013-10-18 00:25:12 -0700  12)  *   GNU General Public License for more details.
00000000 src/com/owncloud/android/syncadapter/AbstractOwnCloudSyncAdapter.java (Not Committed Yet 2013-10-18 00:25:12 -0700  13)  *
00000000 src/com/owncloud/android/syncadapter/AbstractOwnCloudSyncAdapter.java (Not Committed Yet 2013-10-18 00:25:12 -0700  14)  *   You should have received a copy of the GNU General Public License
00000000 src/com/owncloud/android/syncadapter/AbstractOwnCloudSyncAdapter.java (Not Committed Yet 2013-10-18 00:25:12 -0700  15)  *   along with this program.  If not, see <http://www.gnu.org/licenses/>.
00000000 src/com/owncloud/android/syncadapter/AbstractOwnCloudSyncAdapter.java (Not Committed Yet 2013-10-18 00:25:12 -0700  16)  *
00000000 src/com/owncloud/android/syncadapter/AbstractOwnCloudSyncAdapter.java (Not Committed Yet 2013-10-18 00:25:12 -0700  17)  */
00000000 src/com/owncloud/android/syncadapter/AbstractOwnCloudSyncAdapter.java (Not Committed Yet 2013-10-18 00:25:12 -0700  18) 
00000000 src/com/owncloud/android/syncadapter/AbstractOwnCloudSyncAdapter.java (Not Committed Yet 2013-10-18 00:25:12 -0700  19) package com.owncloud.android.syncadapter;
00000000 src/com/owncloud/android/syncadapter/AbstractOwnCloudSyncAdapter.java (Not Committed Yet 2013-10-18 00:25:12 -0700  20) 
00000000 src/com/owncloud/android/syncadapter/AbstractOwnCloudSyncAdapter.java (Not Committed Yet 2013-10-18 00:25:12 -0700  21) import java.io.IOException;
00000000 src/com/owncloud/android/syncadapter/AbstractOwnCloudSyncAdapter.java (Not Committed Yet 2013-10-18 00:25:12 -0700  22) import java.net.UnknownHostException;
00000000 src/com/owncloud/android/syncadapter/AbstractOwnCloudSyncAdapter.java (Not Committed Yet 2013-10-18 00:25:12 -0700  23) import java.util.Date;
00000000 src/com/owncloud/android/syncadapter/AbstractOwnCloudSyncAdapter.java (Not Committed Yet 2013-10-18 00:25:12 -0700  24) 
00000000 src/com/owncloud/android/syncadapter/AbstractOwnCloudSyncAdapter.java (Not Committed Yet 2013-10-18 00:25:12 -0700  25) import org.apache.http.HttpRequest;
00000000 src/com/owncloud/android/syncadapter/AbstractOwnCloudSyncAdapter.java (Not Committed Yet 2013-10-18 00:25:12 -0700  26) import org.apache.http.HttpResponse;
00000000 src/com/owncloud/android/syncadapter/AbstractOwnCloudSyncAdapter.java (Not Committed Yet 2013-10-18 00:25:12 -0700  27) import org.apache.http.client.ClientProtocolException;
00000000 src/com/owncloud/android/syncadapter/AbstractOwnCloudSyncAdapter.java (Not Committed Yet 2013-10-18 00:25:12 -0700  28) import org.apache.http.conn.ConnectionKeepAliveStrategy;
00000000 src/com/owncloud/android/syncadapter/AbstractOwnCloudSyncAdapter.java (Not Committed Yet 2013-10-18 00:25:12 -0700  29) import org.apache.http.protocol.HttpContext;
00000000 src/com/owncloud/android/syncadapter/AbstractOwnCloudSyncAdapter.java (Not Committed Yet 2013-10-18 00:25:12 -0700  30) 
00000000 src/com/owncloud/android/syncadapter/AbstractOwnCloudSyncAdapter.java (Not Committed Yet 2013-10-18 00:25:12 -0700  31) import com.owncloud.android.authentication.AccountUtils;
00000000 src/com/owncloud/android/syncadapter/AbstractOwnCloudSyncAdapter.java (Not Committed Yet 2013-10-18 00:25:12 -0700  32) import com.owncloud.android.authentication.AccountUtils.AccountNotFoundException;
00000000 src/com/owncloud/android/syncadapter/AbstractOwnCloudSyncAdapter.java (Not Committed Yet 2013-10-18 00:25:12 -0700  33) import com.owncloud.android.datamodel.DataStorageManager;
00000000 src/com/owncloud/android/syncadapter/AbstractOwnCloudSyncAdapter.java (Not Committed Yet 2013-10-18 00:25:12 -0700  34) import com.owncloud.android.network.OwnCloudClientUtils;
00000000 src/com/owncloud/android/syncadapter/AbstractOwnCloudSyncAdapter.java (Not Committed Yet 2013-10-18 00:25:12 -0700  35) 
00000000 src/com/owncloud/android/syncadapter/AbstractOwnCloudSyncAdapter.java (Not Committed Yet 2013-10-18 00:25:12 -0700  36) import android.accounts.Account;
00000000 src/com/owncloud/android/syncadapter/AbstractOwnCloudSyncAdapter.java (Not Committed Yet 2013-10-18 00:25:12 -0700  37) import android.accounts.AccountManager;
00000000 src/com/owncloud/android/syncadapter/AbstractOwnCloudSyncAdapter.java (Not Committed Yet 2013-10-18 00:25:12 -0700  38) import android.accounts.AuthenticatorException;
00000000 src/com/owncloud/android/syncadapter/AbstractOwnCloudSyncAdapter.java (Not Committed Yet 2013-10-18 00:25:12 -0700  39) import android.accounts.OperationCanceledException;
00000000 src/com/owncloud/android/syncadapter/AbstractOwnCloudSyncAdapter.java (Not Committed Yet 2013-10-18 00:25:12 -0700  40) import android.content.AbstractThreadedSyncAdapter;
00000000 src/com/owncloud/android/syncadapter/AbstractOwnCloudSyncAdapter.java (Not Committed Yet 2013-10-18 00:25:12 -0700  41) import android.content.ContentProviderClient;
00000000 src/com/owncloud/android/syncadapter/AbstractOwnCloudSyncAdapter.java (Not Committed Yet 2013-10-18 00:25:12 -0700  42) import android.content.Context;
00000000 src/com/owncloud/android/syncadapter/AbstractOwnCloudSyncAdapter.java (Not Committed Yet 2013-10-18 00:25:12 -0700  43) import eu.alefzero.webdav.WebdavClient;
00000000 src/com/owncloud/android/syncadapter/AbstractOwnCloudSyncAdapter.java (Not Committed Yet 2013-10-18 00:25:12 -0700  44) 
00000000 src/com/owncloud/android/syncadapter/AbstractOwnCloudSyncAdapter.java (Not Committed Yet 2013-10-18 00:25:12 -0700  45) /**
00000000 src/com/owncloud/android/syncadapter/AbstractOwnCloudSyncAdapter.java (Not Committed Yet 2013-10-18 00:25:12 -0700  46)  * Base SyncAdapter for OwnCloud Designed to be subclassed for the concrete
00000000 src/com/owncloud/android/syncadapter/AbstractOwnCloudSyncAdapter.java (Not Committed Yet 2013-10-18 00:25:12 -0700  47)  * SyncAdapter, like ConcatsSync, CalendarSync, FileSync etc..
00000000 src/com/owncloud/android/syncadapter/AbstractOwnCloudSyncAdapter.java (Not Committed Yet 2013-10-18 00:25:12 -0700  48)  * 
00000000 src/com/owncloud/android/syncadapter/AbstractOwnCloudSyncAdapter.java (Not Committed Yet 2013-10-18 00:25:12 -0700  49)  * @author sassman
00000000 src/com/owncloud/android/syncadapter/AbstractOwnCloudSyncAdapter.java (Not Committed Yet 2013-10-18 00:25:12 -0700  50)  * 
00000000 src/com/owncloud/android/syncadapter/AbstractOwnCloudSyncAdapter.java (Not Committed Yet 2013-10-18 00:25:12 -0700  51)  */
00000000 src/com/owncloud/android/syncadapter/AbstractOwnCloudSyncAdapter.java (Not Committed Yet 2013-10-18 00:25:12 -0700  52) public abstract class AbstractOwnCloudSyncAdapter extends
00000000 src/com/owncloud/android/syncadapter/AbstractOwnCloudSyncAdapter.java (Not Committed Yet 2013-10-18 00:25:12 -0700  53)         AbstractThreadedSyncAdapter {
00000000 src/com/owncloud/android/syncadapter/AbstractOwnCloudSyncAdapter.java (Not Committed Yet 2013-10-18 00:25:12 -0700  54) 
00000000 src/com/owncloud/android/syncadapter/AbstractOwnCloudSyncAdapter.java (Not Committed Yet 2013-10-18 00:25:12 -0700  55)     private AccountManager accountManager;
00000000 src/com/owncloud/android/syncadapter/AbstractOwnCloudSyncAdapter.java (Not Committed Yet 2013-10-18 00:25:12 -0700  56)     private Account account;
00000000 src/com/owncloud/android/syncadapter/AbstractOwnCloudSyncAdapter.java (Not Committed Yet 2013-10-18 00:25:12 -0700  57)     private ContentProviderClient contentProvider;
00000000 src/com/owncloud/android/syncadapter/AbstractOwnCloudSyncAdapter.java (Not Committed Yet 2013-10-18 00:25:12 -0700  58)     private Date lastUpdated;
00000000 src/com/owncloud/android/syncadapter/AbstractOwnCloudSyncAdapter.java (Not Committed Yet 2013-10-18 00:25:12 -0700  59)     private DataStorageManager mStoreManager;
00000000 src/com/owncloud/android/syncadapter/AbstractOwnCloudSyncAdapter.java (Not Committed Yet 2013-10-18 00:25:12 -0700  60) 
00000000 src/com/owncloud/android/syncadapter/AbstractOwnCloudSyncAdapter.java (Not Committed Yet 2013-10-18 00:25:12 -0700  61)     private WebdavClient mClient = null;
00000000 src/com/owncloud/android/syncadapter/AbstractOwnCloudSyncAdapter.java (Not Committed Yet 2013-10-18 00:25:12 -0700  62) 
00000000 src/com/owncloud/android/syncadapter/AbstractOwnCloudSyncAdapter.java (Not Committed Yet 2013-10-18 00:25:12 -0700  63)     public AbstractOwnCloudSyncAdapter(Context context, boolean autoInitialize) {
00000000 src/com/owncloud/android/syncadapter/AbstractOwnCloudSyncAdapter.java (Not Committed Yet 2013-10-18 00:25:12 -0700  64)         super(context, autoInitialize);
00000000 src/com/owncloud/android/syncadapter/AbstractOwnCloudSyncAdapter.java (Not Committed Yet 2013-10-18 00:25:12 -0700  65)         this.setAccountManager(AccountManager.get(context));
00000000 src/com/owncloud/android/syncadapter/AbstractOwnCloudSyncAdapter.java (Not Committed Yet 2013-10-18 00:25:12 -0700  66)     }
00000000 src/com/owncloud/android/syncadapter/AbstractOwnCloudSyncAdapter.java (Not Committed Yet 2013-10-18 00:25:12 -0700  67) 
00000000 src/com/owncloud/android/syncadapter/AbstractOwnCloudSyncAdapter.java (Not Committed Yet 2013-10-18 00:25:12 -0700  68)     public AccountManager getAccountManager() {
00000000 src/com/owncloud/android/syncadapter/AbstractOwnCloudSyncAdapter.java (Not Committed Yet 2013-10-18 00:25:12 -0700  69)         return accountManager;
00000000 src/com/owncloud/android/syncadapter/AbstractOwnCloudSyncAdapter.java (Not Committed Yet 2013-10-18 00:25:12 -0700  70)     }
00000000 src/com/owncloud/android/syncadapter/AbstractOwnCloudSyncAdapter.java (Not Committed Yet 2013-10-18 00:25:12 -0700  71) 
00000000 src/com/owncloud/android/syncadapter/AbstractOwnCloudSyncAdapter.java (Not Committed Yet 2013-10-18 00:25:12 -0700  72)     public void setAccountManager(AccountManager accountManager) {
00000000 src/com/owncloud/android/syncadapter/AbstractOwnCloudSyncAdapter.java (Not Committed Yet 2013-10-18 00:25:12 -0700  73)         this.accountManager = accountManager;
00000000 src/com/owncloud/android/syncadapter/AbstractOwnCloudSyncAdapter.java (Not Committed Yet 2013-10-18 00:25:12 -0700  74)     }
00000000 src/com/owncloud/android/syncadapter/AbstractOwnCloudSyncAdapter.java (Not Committed Yet 2013-10-18 00:25:12 -0700  75) 
00000000 src/com/owncloud/android/syncadapter/AbstractOwnCloudSyncAdapter.java (Not Committed Yet 2013-10-18 00:25:12 -0700  76)     public Account getAccount() {
00000000 src/com/owncloud/android/syncadapter/AbstractOwnCloudSyncAdapter.java (Not Committed Yet 2013-10-18 00:25:12 -0700  77)         return account;
00000000 src/com/owncloud/android/syncadapter/AbstractOwnCloudSyncAdapter.java (Not Committed Yet 2013-10-18 00:25:12 -0700  78)     }
00000000 src/com/owncloud/android/syncadapter/AbstractOwnCloudSyncAdapter.java (Not Committed Yet 2013-10-18 00:25:12 -0700  79) 
00000000 src/com/owncloud/android/syncadapter/AbstractOwnCloudSyncAdapter.java (Not Committed Yet 2013-10-18 00:25:12 -0700  80)     public void setAccount(Account account) {
00000000 src/com/owncloud/android/syncadapter/AbstractOwnCloudSyncAdapter.java (Not Committed Yet 2013-10-18 00:25:12 -0700  81)         this.account = account;
00000000 src/com/owncloud/android/syncadapter/AbstractOwnCloudSyncAdapter.java (Not Committed Yet 2013-10-18 00:25:12 -0700  82)     }
00000000 src/com/owncloud/android/syncadapter/AbstractOwnCloudSyncAdapter.java (Not Committed Yet 2013-10-18 00:25:12 -0700  83) 
00000000 src/com/owncloud/android/syncadapter/AbstractOwnCloudSyncAdapter.java (Not Committed Yet 2013-10-18 00:25:12 -0700  84)     public ContentProviderClient getContentProvider() {
00000000 src/com/owncloud/android/syncadapter/AbstractOwnCloudSyncAdapter.java (Not Committed Yet 2013-10-18 00:25:12 -0700  85)         return contentProvider;
00000000 src/com/owncloud/android/syncadapter/AbstractOwnCloudSyncAdapter.java (Not Committed Yet 2013-10-18 00:25:12 -0700  86)     }
00000000 src/com/owncloud/android/syncadapter/AbstractOwnCloudSyncAdapter.java (Not Committed Yet 2013-10-18 00:25:12 -0700  87) 
00000000 src/com/owncloud/android/syncadapter/AbstractOwnCloudSyncAdapter.java (Not Committed Yet 2013-10-18 00:25:12 -0700  88)     public void setContentProvider(ContentProviderClient contentProvider) {
00000000 src/com/owncloud/android/syncadapter/AbstractOwnCloudSyncAdapter.java (Not Committed Yet 2013-10-18 00:25:12 -0700  89)         this.contentProvider = contentProvider;
00000000 src/com/owncloud/android/syncadapter/AbstractOwnCloudSyncAdapter.java (Not Committed Yet 2013-10-18 00:25:12 -0700  90)     }
00000000 src/com/owncloud/android/syncadapter/AbstractOwnCloudSyncAdapter.java (Not Committed Yet 2013-10-18 00:25:12 -0700  91) 
00000000 src/com/owncloud/android/syncadapter/AbstractOwnCloudSyncAdapter.java (Not Committed Yet 2013-10-18 00:25:12 -0700  92)     public Date getLastUpdated() {
00000000 src/com/owncloud/android/syncadapter/AbstractOwnCloudSyncAdapter.java (Not Committed Yet 2013-10-18 00:25:12 -0700  93)         return lastUpdated;
00000000 src/com/owncloud/android/syncadapter/AbstractOwnCloudSyncAdapter.java (Not Committed Yet 2013-10-18 00:25:12 -0700  94)     }
00000000 src/com/owncloud/android/syncadapter/AbstractOwnCloudSyncAdapter.java (Not Committed Yet 2013-10-18 00:25:12 -0700  95) 
00000000 src/com/owncloud/android/syncadapter/AbstractOwnCloudSyncAdapter.java (Not Committed Yet 2013-10-18 00:25:12 -0700  96)     public void setLastUpdated(Date lastUpdated) {
00000000 src/com/owncloud/android/syncadapter/AbstractOwnCloudSyncAdapter.java (Not Committed Yet 2013-10-18 00:25:12 -0700  97)         this.lastUpdated = lastUpdated;
00000000 src/com/owncloud/android/syncadapter/AbstractOwnCloudSyncAdapter.java (Not Committed Yet 2013-10-18 00:25:12 -0700  98)     }
00000000 src/com/owncloud/android/syncadapter/AbstractOwnCloudSyncAdapter.java (Not Committed Yet 2013-10-18 00:25:12 -0700  99) 
00000000 src/com/owncloud/android/syncadapter/AbstractOwnCloudSyncAdapter.java (Not Committed Yet 2013-10-18 00:25:12 -0700 100)     public void setStorageManager(DataStorageManager storage_manager) {
00000000 src/com/owncloud/android/syncadapter/AbstractOwnCloudSyncAdapter.java (Not Committed Yet 2013-10-18 00:25:12 -0700 101)         mStoreManager = storage_manager;
00000000 src/com/owncloud/android/syncadapter/AbstractOwnCloudSyncAdapter.java (Not Committed Yet 2013-10-18 00:25:12 -0700 102)     }
00000000 src/com/owncloud/android/syncadapter/AbstractOwnCloudSyncAdapter.java (Not Committed Yet 2013-10-18 00:25:12 -0700 103) 
00000000 src/com/owncloud/android/syncadapter/AbstractOwnCloudSyncAdapter.java (Not Committed Yet 2013-10-18 00:25:12 -0700 104)     public DataStorageManager getStorageManager() {
00000000 src/com/owncloud/android/syncadapter/AbstractOwnCloudSyncAdapter.java (Not Committed Yet 2013-10-18 00:25:12 -0700 105)         return mStoreManager;
00000000 src/com/owncloud/android/syncadapter/AbstractOwnCloudSyncAdapter.java (Not Committed Yet 2013-10-18 00:25:12 -0700 106)     }
00000000 src/com/owncloud/android/syncadapter/AbstractOwnCloudSyncAdapter.java (Not Committed Yet 2013-10-18 00:25:12 -0700 107) 
00000000 src/com/owncloud/android/syncadapter/AbstractOwnCloudSyncAdapter.java (Not Committed Yet 2013-10-18 00:25:12 -0700 108)     protected ConnectionKeepAliveStrategy getKeepAliveStrategy() {
00000000 src/com/owncloud/android/syncadapter/AbstractOwnCloudSyncAdapter.java (Not Committed Yet 2013-10-18 00:25:12 -0700 109)         return new ConnectionKeepAliveStrategy() {
00000000 src/com/owncloud/android/syncadapter/AbstractOwnCloudSyncAdapter.java (Not Committed Yet 2013-10-18 00:25:12 -0700 110)             public long getKeepAliveDuration(HttpResponse response,
00000000 src/com/owncloud/android/syncadapter/AbstractOwnCloudSyncAdapter.java (Not Committed Yet 2013-10-18 00:25:12 -0700 111)                     HttpContext context) {
00000000 src/com/owncloud/android/syncadapter/AbstractOwnCloudSyncAdapter.java (Not Committed Yet 2013-10-18 00:25:12 -0700 112)                 // Change keep alive straategy basing on response: ie
00000000 src/com/owncloud/android/syncadapter/AbstractOwnCloudSyncAdapter.java (Not Committed Yet 2013-10-18 00:25:12 -0700 113)                 // forbidden/not found/etc
00000000 src/com/owncloud/android/syncadapter/AbstractOwnCloudSyncAdapter.java (Not Committed Yet 2013-10-18 00:25:12 -0700 114)                 // should have keep alive 0
00000000 src/com/owncloud/android/syncadapter/AbstractOwnCloudSyncAdapter.java (Not Committed Yet 2013-10-18 00:25:12 -0700 115)                 // default return: 5s
00000000 src/com/owncloud/android/syncadapter/AbstractOwnCloudSyncAdapter.java (Not Committed Yet 2013-10-18 00:25:12 -0700 116)                 int statusCode = response.getStatusLine().getStatusCode();
00000000 src/com/owncloud/android/syncadapter/AbstractOwnCloudSyncAdapter.java (Not Committed Yet 2013-10-18 00:25:12 -0700 117) 
00000000 src/com/owncloud/android/syncadapter/AbstractOwnCloudSyncAdapter.java (Not Committed Yet 2013-10-18 00:25:12 -0700 118)                 // HTTP 400, 500 Errors as well as HTTP 118 - Connection timed
00000000 src/com/owncloud/android/syncadapter/AbstractOwnCloudSyncAdapter.java (Not Committed Yet 2013-10-18 00:25:12 -0700 119)                 // out
00000000 src/com/owncloud/android/syncadapter/AbstractOwnCloudSyncAdapter.java (Not Committed Yet 2013-10-18 00:25:12 -0700 120)                 if ((statusCode >= 400 && statusCode <= 418)
00000000 src/com/owncloud/android/syncadapter/AbstractOwnCloudSyncAdapter.java (Not Committed Yet 2013-10-18 00:25:12 -0700 121)                         || (statusCode >= 421 && statusCode <= 426)
00000000 src/com/owncloud/android/syncadapter/AbstractOwnCloudSyncAdapter.java (Not Committed Yet 2013-10-18 00:25:12 -0700 122)                         || (statusCode >= 500 && statusCode <= 510)
00000000 src/com/owncloud/android/syncadapter/AbstractOwnCloudSyncAdapter.java (Not Committed Yet 2013-10-18 00:25:12 -0700 123)                         || statusCode == 118) {
00000000 src/com/owncloud/android/syncadapter/AbstractOwnCloudSyncAdapter.java (Not Committed Yet 2013-10-18 00:25:12 -0700 124)                     return 0;
00000000 src/com/owncloud/android/syncadapter/AbstractOwnCloudSyncAdapter.java (Not Committed Yet 2013-10-18 00:25:12 -0700 125)                 }
00000000 src/com/owncloud/android/syncadapter/AbstractOwnCloudSyncAdapter.java (Not Committed Yet 2013-10-18 00:25:12 -0700 126) 
00000000 src/com/owncloud/android/syncadapter/AbstractOwnCloudSyncAdapter.java (Not Committed Yet 2013-10-18 00:25:12 -0700 127)                 return 5 * 1000;
00000000 src/com/owncloud/android/syncadapter/AbstractOwnCloudSyncAdapter.java (Not Committed Yet 2013-10-18 00:25:12 -0700 128)             }
00000000 src/com/owncloud/android/syncadapter/AbstractOwnCloudSyncAdapter.java (Not Committed Yet 2013-10-18 00:25:12 -0700 129)         };
00000000 src/com/owncloud/android/syncadapter/AbstractOwnCloudSyncAdapter.java (Not Committed Yet 2013-10-18 00:25:12 -0700 130)     }
00000000 src/com/owncloud/android/syncadapter/AbstractOwnCloudSyncAdapter.java (Not Committed Yet 2013-10-18 00:25:12 -0700 131) 
00000000 src/com/owncloud/android/syncadapter/AbstractOwnCloudSyncAdapter.java (Not Committed Yet 2013-10-18 00:25:12 -0700 132)     protected HttpResponse fireRawRequest(HttpRequest query)
00000000 src/com/owncloud/android/syncadapter/AbstractOwnCloudSyncAdapter.java (Not Committed Yet 2013-10-18 00:25:12 -0700 133)             throws ClientProtocolException, OperationCanceledException,
00000000 src/com/owncloud/android/syncadapter/AbstractOwnCloudSyncAdapter.java (Not Committed Yet 2013-10-18 00:25:12 -0700 134)             AuthenticatorException, IOException {
00000000 src/com/owncloud/android/syncadapter/AbstractOwnCloudSyncAdapter.java (Not Committed Yet 2013-10-18 00:25:12 -0700 135)         /*
00000000 src/com/owncloud/android/syncadapter/AbstractOwnCloudSyncAdapter.java (Not Committed Yet 2013-10-18 00:25:12 -0700 136)          * BasicHttpContext httpContext = new BasicHttpContext(); BasicScheme
00000000 src/com/owncloud/android/syncadapter/AbstractOwnCloudSyncAdapter.java (Not Committed Yet 2013-10-18 00:25:12 -0700 137)          * basicAuth = new BasicScheme();
00000000 src/com/owncloud/android/syncadapter/AbstractOwnCloudSyncAdapter.java (Not Committed Yet 2013-10-18 00:25:12 -0700 138)          * httpContext.setAttribute("preemptive-auth", basicAuth);
00000000 src/com/owncloud/android/syncadapter/AbstractOwnCloudSyncAdapter.java (Not Committed Yet 2013-10-18 00:25:12 -0700 139)          * 
00000000 src/com/owncloud/android/syncadapter/AbstractOwnCloudSyncAdapter.java (Not Committed Yet 2013-10-18 00:25:12 -0700 140)          * HttpResponse response = getClient().execute(mHost, query,
00000000 src/com/owncloud/android/syncadapter/AbstractOwnCloudSyncAdapter.java (Not Committed Yet 2013-10-18 00:25:12 -0700 141)          * httpContext);
00000000 src/com/owncloud/android/syncadapter/AbstractOwnCloudSyncAdapter.java (Not Committed Yet 2013-10-18 00:25:12 -0700 142)          */
00000000 src/com/owncloud/android/syncadapter/AbstractOwnCloudSyncAdapter.java (Not Committed Yet 2013-10-18 00:25:12 -0700 143)         return null;
00000000 src/com/owncloud/android/syncadapter/AbstractOwnCloudSyncAdapter.java (Not Committed Yet 2013-10-18 00:25:12 -0700 144)     }
00000000 src/com/owncloud/android/syncadapter/AbstractOwnCloudSyncAdapter.java (Not Committed Yet 2013-10-18 00:25:12 -0700 145) 
00000000 src/com/owncloud/android/syncadapter/AbstractOwnCloudSyncAdapter.java (Not Committed Yet 2013-10-18 00:25:12 -0700 146)     protected void initClientForCurrentAccount() throws OperationCanceledException, AuthenticatorException, IOException, AccountNotFoundException {
00000000 src/com/owncloud/android/syncadapter/AbstractOwnCloudSyncAdapter.java (Not Committed Yet 2013-10-18 00:25:12 -0700 147)         AccountUtils.constructFullURLForAccount(getContext(), account);
00000000 src/com/owncloud/android/syncadapter/AbstractOwnCloudSyncAdapter.java (Not Committed Yet 2013-10-18 00:25:12 -0700 148)         mClient = OwnCloudClientUtils.createOwnCloudClient(account, getContext());
00000000 src/com/owncloud/android/syncadapter/AbstractOwnCloudSyncAdapter.java (Not Committed Yet 2013-10-18 00:25:12 -0700 149)     }
00000000 src/com/owncloud/android/syncadapter/AbstractOwnCloudSyncAdapter.java (Not Committed Yet 2013-10-18 00:25:12 -0700 150)     
00000000 src/com/owncloud/android/syncadapter/AbstractOwnCloudSyncAdapter.java (Not Committed Yet 2013-10-18 00:25:12 -0700 151)     protected WebdavClient getClient() {
00000000 src/com/owncloud/android/syncadapter/AbstractOwnCloudSyncAdapter.java (Not Committed Yet 2013-10-18 00:25:12 -0700 152)         return mClient;
00000000 src/com/owncloud/android/syncadapter/AbstractOwnCloudSyncAdapter.java (Not Committed Yet 2013-10-18 00:25:12 -0700 153)     }
5ef7a4e5 src/eu/alefzero/owncloud/syncadapter/AbstractOwnCloudSyncAdapter.java (Sven Aßmann       2011-09-15 21:41:46 +0200 154) }
