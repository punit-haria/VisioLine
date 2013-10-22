92080afe (Bartek Przybylski 2012-10-28 00:12:15 +0200   1) /* ownCloud Android client application
92080afe (Bartek Przybylski 2012-10-28 00:12:15 +0200   2)  *   Copyright (C) 2012 Bartek Przybylski
bb257ec7 (David A. Velasco  2013-02-07 18:45:10 +0100   3)  *   Copyright (C) 2012-2013 ownCloud Inc.
92080afe (Bartek Przybylski 2012-10-28 00:12:15 +0200   4)  *
92080afe (Bartek Przybylski 2012-10-28 00:12:15 +0200   5)  *   This program is free software: you can redistribute it and/or modify
bdc0332c (David A. Velasco  2013-04-17 12:26:13 +0200   6)  *   it under the terms of the GNU General Public License version 2,
bdc0332c (David A. Velasco  2013-04-17 12:26:13 +0200   7)  *   as published by the Free Software Foundation.
92080afe (Bartek Przybylski 2012-10-28 00:12:15 +0200   8)  *
92080afe (Bartek Przybylski 2012-10-28 00:12:15 +0200   9)  *   This program is distributed in the hope that it will be useful,
92080afe (Bartek Przybylski 2012-10-28 00:12:15 +0200  10)  *   but WITHOUT ANY WARRANTY; without even the implied warranty of
92080afe (Bartek Przybylski 2012-10-28 00:12:15 +0200  11)  *   MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
92080afe (Bartek Przybylski 2012-10-28 00:12:15 +0200  12)  *   GNU General Public License for more details.
92080afe (Bartek Przybylski 2012-10-28 00:12:15 +0200  13)  *
92080afe (Bartek Przybylski 2012-10-28 00:12:15 +0200  14)  *   You should have received a copy of the GNU General Public License
92080afe (Bartek Przybylski 2012-10-28 00:12:15 +0200  15)  *   along with this program.  If not, see <http://www.gnu.org/licenses/>.
92080afe (Bartek Przybylski 2012-10-28 00:12:15 +0200  16)  *
92080afe (Bartek Przybylski 2012-10-28 00:12:15 +0200  17)  */
92080afe (Bartek Przybylski 2012-10-28 00:12:15 +0200  18) 
ba148a82 (Bartek Przybylski 2012-08-22 19:32:42 +0200  19) package com.owncloud.android.files.services;
ba148a82 (Bartek Przybylski 2012-08-22 19:32:42 +0200  20) 
4460e431 (David A. Velasco  2012-11-13 10:39:37 +0100  21) import java.io.File;
c301865c (David A. Velasco  2012-11-16 16:23:45 +0100  22) import java.util.HashMap;
c301865c (David A. Velasco  2012-11-16 16:23:45 +0100  23) import java.util.Map;
ba148a82 (Bartek Przybylski 2012-08-22 19:32:42 +0200  24) 
8e36e7cc (zerginator        2013-03-12 07:56:27 +0100  25) import com.owncloud.android.Log_OC;
ba148a82 (Bartek Przybylski 2012-08-22 19:32:42 +0200  26) import com.owncloud.android.datamodel.FileDataStorageManager;
bd38627e (David A. Velasco  2012-11-14 15:00:53 +0100  27) import com.owncloud.android.datamodel.OCFile;
ba148a82 (Bartek Przybylski 2012-08-22 19:32:42 +0200  28) import com.owncloud.android.db.ProviderMeta.ProviderTableMeta;
ba148a82 (Bartek Przybylski 2012-08-22 19:32:42 +0200  29) import com.owncloud.android.files.OwnCloudFileObserver;
c301865c (David A. Velasco  2012-11-16 16:23:45 +0100  30) import com.owncloud.android.operations.SynchronizeFileOperation;
bd38627e (David A. Velasco  2012-11-14 15:00:53 +0100  31) import com.owncloud.android.utils.FileStorageUtils;
ba148a82 (Bartek Przybylski 2012-08-22 19:32:42 +0200  32) 
ba148a82 (Bartek Przybylski 2012-08-22 19:32:42 +0200  33) import android.accounts.Account;
ba148a82 (Bartek Przybylski 2012-08-22 19:32:42 +0200  34) import android.accounts.AccountManager;
ba148a82 (Bartek Przybylski 2012-08-22 19:32:42 +0200  35) import android.app.Service;
ba148a82 (Bartek Przybylski 2012-08-22 19:32:42 +0200  36) import android.content.BroadcastReceiver;
ba148a82 (Bartek Przybylski 2012-08-22 19:32:42 +0200  37) import android.content.Context;
ba148a82 (Bartek Przybylski 2012-08-22 19:32:42 +0200  38) import android.content.Intent;
ba148a82 (Bartek Przybylski 2012-08-22 19:32:42 +0200  39) import android.content.IntentFilter;
ba148a82 (Bartek Przybylski 2012-08-22 19:32:42 +0200  40) import android.database.Cursor;
ba148a82 (Bartek Przybylski 2012-08-22 19:32:42 +0200  41) import android.os.Binder;
ba148a82 (Bartek Przybylski 2012-08-22 19:32:42 +0200  42) import android.os.IBinder;
ba148a82 (Bartek Przybylski 2012-08-22 19:32:42 +0200  43) 
22a789e8 (David A. Velasco  2012-11-19 15:08:48 +0100  44) public class FileObserverService extends Service {
ba148a82 (Bartek Przybylski 2012-08-22 19:32:42 +0200  45) 
ba148a82 (Bartek Przybylski 2012-08-22 19:32:42 +0200  46)     public final static int CMD_INIT_OBSERVED_LIST = 1;
ba148a82 (Bartek Przybylski 2012-08-22 19:32:42 +0200  47)     public final static int CMD_ADD_OBSERVED_FILE = 2;
ba148a82 (Bartek Przybylski 2012-08-22 19:32:42 +0200  48)     public final static int CMD_DEL_OBSERVED_FILE = 3;
c301865c (David A. Velasco  2012-11-16 16:23:45 +0100  49) 
c301865c (David A. Velasco  2012-11-16 16:23:45 +0100  50)     public final static String KEY_FILE_CMD = "KEY_FILE_CMD";
c301865c (David A. Velasco  2012-11-16 16:23:45 +0100  51)     public final static String KEY_CMD_ARG_FILE = "KEY_CMD_ARG_FILE";
c301865c (David A. Velasco  2012-11-16 16:23:45 +0100  52)     public final static String KEY_CMD_ARG_ACCOUNT = "KEY_CMD_ARG_ACCOUNT";
ba148a82 (Bartek Przybylski 2012-08-22 19:32:42 +0200  53) 
9aab2d26 (David A. Velasco  2012-11-12 15:20:00 +0100  54)     private static String TAG = FileObserverService.class.getSimpleName();
c301865c (David A. Velasco  2012-11-16 16:23:45 +0100  55) 
c301865c (David A. Velasco  2012-11-16 16:23:45 +0100  56)     private static Map<String, OwnCloudFileObserver> mObserversMap;
c301865c (David A. Velasco  2012-11-16 16:23:45 +0100  57)     private static DownloadCompletedReceiverBis mDownloadReceiver;
ba148a82 (Bartek Przybylski 2012-08-22 19:32:42 +0200  58)     private IBinder mBinder = new LocalBinder();
ba148a82 (Bartek Przybylski 2012-08-22 19:32:42 +0200  59) 
ba148a82 (Bartek Przybylski 2012-08-22 19:32:42 +0200  60)     public class LocalBinder extends Binder {
ba148a82 (Bartek Przybylski 2012-08-22 19:32:42 +0200  61)         FileObserverService getService() {
ba148a82 (Bartek Przybylski 2012-08-22 19:32:42 +0200  62)             return FileObserverService.this;
ba148a82 (Bartek Przybylski 2012-08-22 19:32:42 +0200  63)         }
ba148a82 (Bartek Przybylski 2012-08-22 19:32:42 +0200  64)     }
ba148a82 (Bartek Przybylski 2012-08-22 19:32:42 +0200  65)     
ba148a82 (Bartek Przybylski 2012-08-22 19:32:42 +0200  66)     @Override
c301865c (David A. Velasco  2012-11-16 16:23:45 +0100  67)     public void onCreate() {
c301865c (David A. Velasco  2012-11-16 16:23:45 +0100  68)         super.onCreate();
c301865c (David A. Velasco  2012-11-16 16:23:45 +0100  69)         mDownloadReceiver = new DownloadCompletedReceiverBis();
c301865c (David A. Velasco  2012-11-16 16:23:45 +0100  70)         IntentFilter filter = new IntentFilter();
c301865c (David A. Velasco  2012-11-16 16:23:45 +0100  71)         filter.addAction(FileDownloader.DOWNLOAD_ADDED_MESSAGE);
c301865c (David A. Velasco  2012-11-16 16:23:45 +0100  72)         filter.addAction(FileDownloader.DOWNLOAD_FINISH_MESSAGE);        
c301865c (David A. Velasco  2012-11-16 16:23:45 +0100  73)         registerReceiver(mDownloadReceiver, filter);
c301865c (David A. Velasco  2012-11-16 16:23:45 +0100  74)         
c301865c (David A. Velasco  2012-11-16 16:23:45 +0100  75)         mObserversMap = new HashMap<String, OwnCloudFileObserver>();
22a789e8 (David A. Velasco  2012-11-19 15:08:48 +0100  76)         //initializeObservedList();
c301865c (David A. Velasco  2012-11-16 16:23:45 +0100  77)     }
c301865c (David A. Velasco  2012-11-16 16:23:45 +0100  78)     
c301865c (David A. Velasco  2012-11-16 16:23:45 +0100  79)     
c301865c (David A. Velasco  2012-11-16 16:23:45 +0100  80)     @Override
c301865c (David A. Velasco  2012-11-16 16:23:45 +0100  81)     public void onDestroy() {
c301865c (David A. Velasco  2012-11-16 16:23:45 +0100  82)         super.onDestroy();
c301865c (David A. Velasco  2012-11-16 16:23:45 +0100  83)         unregisterReceiver(mDownloadReceiver);
c301865c (David A. Velasco  2012-11-16 16:23:45 +0100  84)         mObserversMap = null;   // TODO study carefully the life cycle of Services to grant the best possible observance
8e36e7cc (zerginator        2013-03-12 07:56:27 +0100  85)         Log_OC.d(TAG, "Bye, bye");
c301865c (David A. Velasco  2012-11-16 16:23:45 +0100  86)     }
c301865c (David A. Velasco  2012-11-16 16:23:45 +0100  87)     
c301865c (David A. Velasco  2012-11-16 16:23:45 +0100  88)     
c301865c (David A. Velasco  2012-11-16 16:23:45 +0100  89)     @Override
ba148a82 (Bartek Przybylski 2012-08-22 19:32:42 +0200  90)     public IBinder onBind(Intent intent) {
ba148a82 (Bartek Przybylski 2012-08-22 19:32:42 +0200  91)         return mBinder;
ba148a82 (Bartek Przybylski 2012-08-22 19:32:42 +0200  92)     }
ba148a82 (Bartek Przybylski 2012-08-22 19:32:42 +0200  93) 
ba148a82 (Bartek Przybylski 2012-08-22 19:32:42 +0200  94)     @Override
ba148a82 (Bartek Przybylski 2012-08-22 19:32:42 +0200  95)     public int onStartCommand(Intent intent, int flags, int startId) {
b2a26028 (Bartek Przybylski 2012-08-22 21:20:38 +0200  96)         // this occurs when system tries to restart
b2a26028 (Bartek Przybylski 2012-08-22 21:20:38 +0200  97)         // service, so we need to reinitialize observers
b2a26028 (Bartek Przybylski 2012-08-22 21:20:38 +0200  98)         if (intent == null) {
b2a26028 (Bartek Przybylski 2012-08-22 21:20:38 +0200  99)             initializeObservedList();
b2a26028 (Bartek Przybylski 2012-08-22 21:20:38 +0200 100)             return Service.START_STICKY;
b2a26028 (Bartek Przybylski 2012-08-22 21:20:38 +0200 101)         }
b2a26028 (Bartek Przybylski 2012-08-22 21:20:38 +0200 102)             
ba148a82 (Bartek Przybylski 2012-08-22 19:32:42 +0200 103)         if (!intent.hasExtra(KEY_FILE_CMD)) {
8e36e7cc (zerginator        2013-03-12 07:56:27 +0100 104)             Log_OC.e(TAG, "No KEY_FILE_CMD argument given");
ba148a82 (Bartek Przybylski 2012-08-22 19:32:42 +0200 105)             return Service.START_STICKY;
ba148a82 (Bartek Przybylski 2012-08-22 19:32:42 +0200 106)         }
ba148a82 (Bartek Przybylski 2012-08-22 19:32:42 +0200 107) 
ba148a82 (Bartek Przybylski 2012-08-22 19:32:42 +0200 108)         switch (intent.getIntExtra(KEY_FILE_CMD, -1)) {
ba148a82 (Bartek Przybylski 2012-08-22 19:32:42 +0200 109)             case CMD_INIT_OBSERVED_LIST:
ba148a82 (Bartek Przybylski 2012-08-22 19:32:42 +0200 110)                 initializeObservedList();
ba148a82 (Bartek Przybylski 2012-08-22 19:32:42 +0200 111)                 break;
ba148a82 (Bartek Przybylski 2012-08-22 19:32:42 +0200 112)             case CMD_ADD_OBSERVED_FILE:
bd38627e (David A. Velasco  2012-11-14 15:00:53 +0100 113)                 addObservedFile( (OCFile)intent.getParcelableExtra(KEY_CMD_ARG_FILE), 
bd38627e (David A. Velasco  2012-11-14 15:00:53 +0100 114)                                  (Account)intent.getParcelableExtra(KEY_CMD_ARG_ACCOUNT));
ba148a82 (Bartek Przybylski 2012-08-22 19:32:42 +0200 115)                 break;
ba148a82 (Bartek Przybylski 2012-08-22 19:32:42 +0200 116)             case CMD_DEL_OBSERVED_FILE:
bd38627e (David A. Velasco  2012-11-14 15:00:53 +0100 117)                 removeObservedFile( (OCFile)intent.getParcelableExtra(KEY_CMD_ARG_FILE), 
bd38627e (David A. Velasco  2012-11-14 15:00:53 +0100 118)                                     (Account)intent.getParcelableExtra(KEY_CMD_ARG_ACCOUNT));
ba148a82 (Bartek Przybylski 2012-08-22 19:32:42 +0200 119)                 break;
ba148a82 (Bartek Przybylski 2012-08-22 19:32:42 +0200 120)             default:
8e36e7cc (zerginator        2013-03-12 07:56:27 +0100 121)                 Log_OC.wtf(TAG, "Incorrect key given");
ba148a82 (Bartek Przybylski 2012-08-22 19:32:42 +0200 122)         }
ba148a82 (Bartek Przybylski 2012-08-22 19:32:42 +0200 123) 
ba148a82 (Bartek Przybylski 2012-08-22 19:32:42 +0200 124)         return Service.START_STICKY;
ba148a82 (Bartek Przybylski 2012-08-22 19:32:42 +0200 125)     }
de94751c (Bartek Przybylski 2012-10-22 21:15:26 +0200 126) 
c301865c (David A. Velasco  2012-11-16 16:23:45 +0100 127)     
c301865c (David A. Velasco  2012-11-16 16:23:45 +0100 128)     /**
c301865c (David A. Velasco  2012-11-16 16:23:45 +0100 129)      * Read from the local database the list of files that must to be kept synchronized and 
c301865c (David A. Velasco  2012-11-16 16:23:45 +0100 130)      * starts file observers to monitor local changes on them
c301865c (David A. Velasco  2012-11-16 16:23:45 +0100 131)      */
ba148a82 (Bartek Przybylski 2012-08-22 19:32:42 +0200 132)     private void initializeObservedList() {
c301865c (David A. Velasco  2012-11-16 16:23:45 +0100 133)         mObserversMap.clear();
ba148a82 (Bartek Przybylski 2012-08-22 19:32:42 +0200 134)         Cursor c = getContentResolver().query(
ba148a82 (Bartek Przybylski 2012-08-22 19:32:42 +0200 135)                 ProviderTableMeta.CONTENT_URI,
ba148a82 (Bartek Przybylski 2012-08-22 19:32:42 +0200 136)                 null,
ba148a82 (Bartek Przybylski 2012-08-22 19:32:42 +0200 137)                 ProviderTableMeta.FILE_KEEP_IN_SYNC + " = ?",
ba148a82 (Bartek Przybylski 2012-08-22 19:32:42 +0200 138)                 new String[] {String.valueOf(1)},
ba148a82 (Bartek Przybylski 2012-08-22 19:32:42 +0200 139)                 null);
f91d3ce7 (David A. Velasco  2013-01-14 12:38:46 +0100 140)         if (c == null || !c.moveToFirst()) return;
ba148a82 (Bartek Przybylski 2012-08-22 19:32:42 +0200 141)         AccountManager acm = AccountManager.get(this);
ba148a82 (Bartek Przybylski 2012-08-22 19:32:42 +0200 142)         Account[] accounts = acm.getAccounts();
ba148a82 (Bartek Przybylski 2012-08-22 19:32:42 +0200 143)         do {
ba148a82 (Bartek Przybylski 2012-08-22 19:32:42 +0200 144)             Account account = null;
ba148a82 (Bartek Przybylski 2012-08-22 19:32:42 +0200 145)             for (Account a : accounts)
ba148a82 (Bartek Przybylski 2012-08-22 19:32:42 +0200 146)                 if (a.name.equals(c.getString(c.getColumnIndex(ProviderTableMeta.FILE_ACCOUNT_OWNER)))) {
ba148a82 (Bartek Przybylski 2012-08-22 19:32:42 +0200 147)                     account = a;
ba148a82 (Bartek Przybylski 2012-08-22 19:32:42 +0200 148)                     break;
ba148a82 (Bartek Przybylski 2012-08-22 19:32:42 +0200 149)                 }
ba148a82 (Bartek Przybylski 2012-08-22 19:32:42 +0200 150) 
ba148a82 (Bartek Przybylski 2012-08-22 19:32:42 +0200 151)             if (account == null) continue;
ba148a82 (Bartek Przybylski 2012-08-22 19:32:42 +0200 152)             FileDataStorageManager storage =
ba148a82 (Bartek Przybylski 2012-08-22 19:32:42 +0200 153)                     new FileDataStorageManager(account, getContentResolver());
ba148a82 (Bartek Przybylski 2012-08-22 19:32:42 +0200 154)             if (!storage.fileExists(c.getString(c.getColumnIndex(ProviderTableMeta.FILE_PATH))))
ba148a82 (Bartek Przybylski 2012-08-22 19:32:42 +0200 155)                 continue;
ba148a82 (Bartek Przybylski 2012-08-22 19:32:42 +0200 156) 
ba148a82 (Bartek Przybylski 2012-08-22 19:32:42 +0200 157)             String path = c.getString(c.getColumnIndex(ProviderTableMeta.FILE_STORAGE_PATH));
9c3dc6d8 (David A. Velasco  2012-11-20 14:48:44 +0100 158)             if (path == null || path.length() <= 0)
9c3dc6d8 (David A. Velasco  2012-11-20 14:48:44 +0100 159)                 continue;
ba148a82 (Bartek Przybylski 2012-08-22 19:32:42 +0200 160)             OwnCloudFileObserver observer =
22a789e8 (David A. Velasco  2012-11-19 15:08:48 +0100 161)                     new OwnCloudFileObserver(   path, 
22a789e8 (David A. Velasco  2012-11-19 15:08:48 +0100 162)                                                 account, 
22a789e8 (David A. Velasco  2012-11-19 15:08:48 +0100 163)                                                 getApplicationContext(), 
22a789e8 (David A. Velasco  2012-11-19 15:08:48 +0100 164)                                                 OwnCloudFileObserver.CHANGES_ONLY);
c301865c (David A. Velasco  2012-11-16 16:23:45 +0100 165)             mObserversMap.put(path, observer);
c301865c (David A. Velasco  2012-11-16 16:23:45 +0100 166)             if (new File(path).exists()) {
c301865c (David A. Velasco  2012-11-16 16:23:45 +0100 167)                 observer.startWatching();
8e36e7cc (zerginator        2013-03-12 07:56:27 +0100 168)                 Log_OC.d(TAG, "Started watching file " + path);
c301865c (David A. Velasco  2012-11-16 16:23:45 +0100 169)             }
ba148a82 (Bartek Przybylski 2012-08-22 19:32:42 +0200 170)             
ba148a82 (Bartek Przybylski 2012-08-22 19:32:42 +0200 171)         } while (c.moveToNext());
ba148a82 (Bartek Przybylski 2012-08-22 19:32:42 +0200 172)         c.close();
ba148a82 (Bartek Przybylski 2012-08-22 19:32:42 +0200 173)     }
ba148a82 (Bartek Przybylski 2012-08-22 19:32:42 +0200 174)     
22a789e8 (David A. Velasco  2012-11-19 15:08:48 +0100 175)     
bd38627e (David A. Velasco  2012-11-14 15:00:53 +0100 176)     /**
bd38627e (David A. Velasco  2012-11-14 15:00:53 +0100 177)      * Registers the local copy of a remote file to be observed for local changes,
bd38627e (David A. Velasco  2012-11-14 15:00:53 +0100 178)      * an automatically updated in the ownCloud server.
c301865c (David A. Velasco  2012-11-16 16:23:45 +0100 179)      * 
c301865c (David A. Velasco  2012-11-16 16:23:45 +0100 180)      * This method does NOT perform a {@link SynchronizeFileOperation} over the file. 
bd38627e (David A. Velasco  2012-11-14 15:00:53 +0100 181)      *
c301865c (David A. Velasco  2012-11-16 16:23:45 +0100 182)      * TODO We are ignoring that, currently, a local file can be linked to different files
c301865c (David A. Velasco  2012-11-16 16:23:45 +0100 183)      * in ownCloud if it's uploaded several times. That's something pending to update: we 
c301865c (David A. Velasco  2012-11-16 16:23:45 +0100 184)      * will avoid that the same local file is linked to different remote files.
c301865c (David A. Velasco  2012-11-16 16:23:45 +0100 185)      * 
bd38627e (David A. Velasco  2012-11-14 15:00:53 +0100 186)      * @param file      Object representing a remote file which local copy must be observed.
bd38627e (David A. Velasco  2012-11-14 15:00:53 +0100 187)      * @param account   OwnCloud account containing file.
bd38627e (David A. Velasco  2012-11-14 15:00:53 +0100 188)      */
bd38627e (David A. Velasco  2012-11-14 15:00:53 +0100 189)     private void addObservedFile(OCFile file, Account account) {
bd38627e (David A. Velasco  2012-11-14 15:00:53 +0100 190)         if (file == null) {
8e36e7cc (zerginator        2013-03-12 07:56:27 +0100 191)             Log_OC.e(TAG, "Trying to add a NULL file to observer");
bd38627e (David A. Velasco  2012-11-14 15:00:53 +0100 192)             return;
bd38627e (David A. Velasco  2012-11-14 15:00:53 +0100 193)         }
bd38627e (David A. Velasco  2012-11-14 15:00:53 +0100 194)         String localPath = file.getStoragePath();
c301865c (David A. Velasco  2012-11-16 16:23:45 +0100 195)         if (localPath == null || localPath.length() <= 0) { // file downloading / to be download for the first time
bd38627e (David A. Velasco  2012-11-14 15:00:53 +0100 196)             localPath = FileStorageUtils.getDefaultSavePathFor(account.name, file);
bd38627e (David A. Velasco  2012-11-14 15:00:53 +0100 197)         }
c301865c (David A. Velasco  2012-11-16 16:23:45 +0100 198)         OwnCloudFileObserver observer = mObserversMap.get(localPath);
bd38627e (David A. Velasco  2012-11-14 15:00:53 +0100 199)         if (observer == null) {
bd38627e (David A. Velasco  2012-11-14 15:00:53 +0100 200)             /// the local file was never registered to observe before
3fd56ecf (David A. Velasco  2012-11-20 11:28:27 +0100 201)             observer = new OwnCloudFileObserver(    localPath, 
22a789e8 (David A. Velasco  2012-11-19 15:08:48 +0100 202)                                                     account, 
22a789e8 (David A. Velasco  2012-11-19 15:08:48 +0100 203)                                                     getApplicationContext(), 
22a789e8 (David A. Velasco  2012-11-19 15:08:48 +0100 204)                                                     OwnCloudFileObserver.CHANGES_ONLY);
c301865c (David A. Velasco  2012-11-16 16:23:45 +0100 205)             mObserversMap.put(localPath, observer);
8e36e7cc (zerginator        2013-03-12 07:56:27 +0100 206)             Log_OC.d(TAG, "Observer added for path " + localPath);
bd38627e (David A. Velasco  2012-11-14 15:00:53 +0100 207)         
c301865c (David A. Velasco  2012-11-16 16:23:45 +0100 208)             if (file.isDown()) {
c301865c (David A. Velasco  2012-11-16 16:23:45 +0100 209)                 observer.startWatching();
8e36e7cc (zerginator        2013-03-12 07:56:27 +0100 210)                 Log_OC.d(TAG, "Started watching " + localPath);
c301865c (David A. Velasco  2012-11-16 16:23:45 +0100 211)             }   // else - the observance can't be started on a file not already down; mDownloadReceiver will get noticed when the download of the file finishes
bd38627e (David A. Velasco  2012-11-14 15:00:53 +0100 212)         }
bd38627e (David A. Velasco  2012-11-14 15:00:53 +0100 213)         
ba148a82 (Bartek Przybylski 2012-08-22 19:32:42 +0200 214)     }
bd38627e (David A. Velasco  2012-11-14 15:00:53 +0100 215) 
ba148a82 (Bartek Przybylski 2012-08-22 19:32:42 +0200 216)     
bd38627e (David A. Velasco  2012-11-14 15:00:53 +0100 217)     /**
bd38627e (David A. Velasco  2012-11-14 15:00:53 +0100 218)      * Unregisters the local copy of a remote file to be observed for local changes.
bd38627e (David A. Velasco  2012-11-14 15:00:53 +0100 219)      *
c301865c (David A. Velasco  2012-11-16 16:23:45 +0100 220)      * Starts to watch it, if the file has a local copy to watch.
c301865c (David A. Velasco  2012-11-16 16:23:45 +0100 221)      * 
c301865c (David A. Velasco  2012-11-16 16:23:45 +0100 222)      * TODO We are ignoring that, currently, a local file can be linked to different files
c301865c (David A. Velasco  2012-11-16 16:23:45 +0100 223)      * in ownCloud if it's uploaded several times. That's something pending to update: we 
c301865c (David A. Velasco  2012-11-16 16:23:45 +0100 224)      * will avoid that the same local file is linked to different remote files.
c301865c (David A. Velasco  2012-11-16 16:23:45 +0100 225)      *
bd38627e (David A. Velasco  2012-11-14 15:00:53 +0100 226)      * @param file      Object representing a remote file which local copy must be not observed longer.
bd38627e (David A. Velasco  2012-11-14 15:00:53 +0100 227)      * @param account   OwnCloud account containing file.
bd38627e (David A. Velasco  2012-11-14 15:00:53 +0100 228)      */
bd38627e (David A. Velasco  2012-11-14 15:00:53 +0100 229)     private void removeObservedFile(OCFile file, Account account) {
bd38627e (David A. Velasco  2012-11-14 15:00:53 +0100 230)         if (file == null) {
8e36e7cc (zerginator        2013-03-12 07:56:27 +0100 231)             Log_OC.e(TAG, "Trying to remove a NULL file");
bd38627e (David A. Velasco  2012-11-14 15:00:53 +0100 232)             return;
bd38627e (David A. Velasco  2012-11-14 15:00:53 +0100 233)         }
bd38627e (David A. Velasco  2012-11-14 15:00:53 +0100 234)         String localPath = file.getStoragePath();
c301865c (David A. Velasco  2012-11-16 16:23:45 +0100 235)         if (localPath == null || localPath.length() <= 0) {
bd38627e (David A. Velasco  2012-11-14 15:00:53 +0100 236)             localPath = FileStorageUtils.getDefaultSavePathFor(account.name, file);
bd38627e (David A. Velasco  2012-11-14 15:00:53 +0100 237)         }
bd38627e (David A. Velasco  2012-11-14 15:00:53 +0100 238)         
c301865c (David A. Velasco  2012-11-16 16:23:45 +0100 239)         OwnCloudFileObserver observer = mObserversMap.get(localPath);
c301865c (David A. Velasco  2012-11-16 16:23:45 +0100 240)         if (observer != null) {
c301865c (David A. Velasco  2012-11-16 16:23:45 +0100 241)             observer.stopWatching();
c301865c (David A. Velasco  2012-11-16 16:23:45 +0100 242)             mObserversMap.remove(observer);
8e36e7cc (zerginator        2013-03-12 07:56:27 +0100 243)             Log_OC.d(TAG, "Stopped watching " + localPath);
ba148a82 (Bartek Przybylski 2012-08-22 19:32:42 +0200 244)         }
de94751c (Bartek Przybylski 2012-10-22 21:15:26 +0200 245)         
bd38627e (David A. Velasco  2012-11-14 15:00:53 +0100 246)     }
bd38627e (David A. Velasco  2012-11-14 15:00:53 +0100 247) 
6c323657 (Bartek Przybylski 2012-10-27 12:20:52 +0200 248) 
c301865c (David A. Velasco  2012-11-16 16:23:45 +0100 249)     /**
c301865c (David A. Velasco  2012-11-16 16:23:45 +0100 250)      *  Private receiver listening to events broadcast by the FileDownloader service.
c301865c (David A. Velasco  2012-11-16 16:23:45 +0100 251)      * 
c301865c (David A. Velasco  2012-11-16 16:23:45 +0100 252)      *  Starts and stops the observance on registered files when they are being download,
c301865c (David A. Velasco  2012-11-16 16:23:45 +0100 253)      *  in order to avoid to start unnecessary synchronizations. 
c301865c (David A. Velasco  2012-11-16 16:23:45 +0100 254)      */
c301865c (David A. Velasco  2012-11-16 16:23:45 +0100 255)     private class DownloadCompletedReceiverBis extends BroadcastReceiver {
ba148a82 (Bartek Przybylski 2012-08-22 19:32:42 +0200 256)         
ba148a82 (Bartek Przybylski 2012-08-22 19:32:42 +0200 257)         @Override
ba148a82 (Bartek Przybylski 2012-08-22 19:32:42 +0200 258)         public void onReceive(Context context, Intent intent) {
c301865c (David A. Velasco  2012-11-16 16:23:45 +0100 259)             String downloadPath = intent.getStringExtra(FileDownloader.EXTRA_FILE_PATH);
c301865c (David A. Velasco  2012-11-16 16:23:45 +0100 260)             OwnCloudFileObserver observer = mObserversMap.get(downloadPath);
c301865c (David A. Velasco  2012-11-16 16:23:45 +0100 261)             if (observer != null) {
c301865c (David A. Velasco  2012-11-16 16:23:45 +0100 262)                 if (intent.getAction().equals(FileDownloader.DOWNLOAD_FINISH_MESSAGE) &&
bdc0332c (David A. Velasco  2013-04-17 12:26:13 +0200 263)                         new File(downloadPath).exists()) {  // the download could be successful. not; in both cases, the file could be down, due to a former download or upload   
c301865c (David A. Velasco  2012-11-16 16:23:45 +0100 264)                     observer.startWatching();
8e36e7cc (zerginator        2013-03-12 07:56:27 +0100 265)                     Log_OC.d(TAG, "Watching again " + downloadPath);
c301865c (David A. Velasco  2012-11-16 16:23:45 +0100 266)                 
c301865c (David A. Velasco  2012-11-16 16:23:45 +0100 267)                 } else if (intent.getAction().equals(FileDownloader.DOWNLOAD_ADDED_MESSAGE)) {
c301865c (David A. Velasco  2012-11-16 16:23:45 +0100 268)                     observer.stopWatching();
8e36e7cc (zerginator        2013-03-12 07:56:27 +0100 269)                     Log_OC.d(TAG, "Disabling observance of " + downloadPath);
c301865c (David A. Velasco  2012-11-16 16:23:45 +0100 270)                 } 
ba148a82 (Bartek Przybylski 2012-08-22 19:32:42 +0200 271)             }
ba148a82 (Bartek Przybylski 2012-08-22 19:32:42 +0200 272)         }
ba148a82 (Bartek Przybylski 2012-08-22 19:32:42 +0200 273)         
ba148a82 (Bartek Przybylski 2012-08-22 19:32:42 +0200 274)     }
c301865c (David A. Velasco  2012-11-16 16:23:45 +0100 275)     
ba148a82 (Bartek Przybylski 2012-08-22 19:32:42 +0200 276) }
