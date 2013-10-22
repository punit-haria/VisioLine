b7ca8521 src/eu/alefzero/owncloud/datamodel/FileDataStorageManager.java (Bartek Przybylski 2012-04-14 22:28:55 +0200   1) /* ownCloud Android client application
b7ca8521 src/eu/alefzero/owncloud/datamodel/FileDataStorageManager.java (Bartek Przybylski 2012-04-14 22:28:55 +0200   2)  *   Copyright (C) 2012  Bartek Przybylski
bb257ec7 src/com/owncloud/android/datamodel/FileDataStorageManager.java (David A. Velasco  2013-02-07 18:45:10 +0100   3)  *   Copyright (C) 2012-2013 ownCloud Inc.
b7ca8521 src/eu/alefzero/owncloud/datamodel/FileDataStorageManager.java (Bartek Przybylski 2012-04-14 22:28:55 +0200   4)  *
b7ca8521 src/eu/alefzero/owncloud/datamodel/FileDataStorageManager.java (Bartek Przybylski 2012-04-14 22:28:55 +0200   5)  *   This program is free software: you can redistribute it and/or modify
bdc0332c src/com/owncloud/android/datamodel/FileDataStorageManager.java (David A. Velasco  2013-04-17 12:26:13 +0200   6)  *   it under the terms of the GNU General Public License version 2,
bdc0332c src/com/owncloud/android/datamodel/FileDataStorageManager.java (David A. Velasco  2013-04-17 12:26:13 +0200   7)  *   as published by the Free Software Foundation.
b7ca8521 src/eu/alefzero/owncloud/datamodel/FileDataStorageManager.java (Bartek Przybylski 2012-04-14 22:28:55 +0200   8)  *
b7ca8521 src/eu/alefzero/owncloud/datamodel/FileDataStorageManager.java (Bartek Przybylski 2012-04-14 22:28:55 +0200   9)  *   This program is distributed in the hope that it will be useful,
b7ca8521 src/eu/alefzero/owncloud/datamodel/FileDataStorageManager.java (Bartek Przybylski 2012-04-14 22:28:55 +0200  10)  *   but WITHOUT ANY WARRANTY; without even the implied warranty of
b7ca8521 src/eu/alefzero/owncloud/datamodel/FileDataStorageManager.java (Bartek Przybylski 2012-04-14 22:28:55 +0200  11)  *   MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
b7ca8521 src/eu/alefzero/owncloud/datamodel/FileDataStorageManager.java (Bartek Przybylski 2012-04-14 22:28:55 +0200  12)  *   GNU General Public License for more details.
b7ca8521 src/eu/alefzero/owncloud/datamodel/FileDataStorageManager.java (Bartek Przybylski 2012-04-14 22:28:55 +0200  13)  *
b7ca8521 src/eu/alefzero/owncloud/datamodel/FileDataStorageManager.java (Bartek Przybylski 2012-04-14 22:28:55 +0200  14)  *   You should have received a copy of the GNU General Public License
b7ca8521 src/eu/alefzero/owncloud/datamodel/FileDataStorageManager.java (Bartek Przybylski 2012-04-14 22:28:55 +0200  15)  *   along with this program.  If not, see <http://www.gnu.org/licenses/>.
b7ca8521 src/eu/alefzero/owncloud/datamodel/FileDataStorageManager.java (Bartek Przybylski 2012-04-14 22:28:55 +0200  16)  *
b7ca8521 src/eu/alefzero/owncloud/datamodel/FileDataStorageManager.java (Bartek Przybylski 2012-04-14 22:28:55 +0200  17)  */
b7ca8521 src/eu/alefzero/owncloud/datamodel/FileDataStorageManager.java (Bartek Przybylski 2012-04-14 22:28:55 +0200  18) 
a4ba6170 src/com/owncloud/android/datamodel/FileDataStorageManager.java (Bartek Przybylski 2012-07-31 17:43:37 +0200  19) package com.owncloud.android.datamodel;
b7ca8521 src/eu/alefzero/owncloud/datamodel/FileDataStorageManager.java (Bartek Przybylski 2012-04-14 22:28:55 +0200  20) 
49ad2498 src/eu/alefzero/owncloud/datamodel/FileDataStorageManager.java (Bartek Przybylski 2012-05-23 22:40:31 +0200  21) import java.io.File;
d4f8391f src/eu/alefzero/owncloud/datamodel/FileDataStorageManager.java (David A. Velasco  2012-07-11 13:51:57 +0200  22) import java.util.ArrayList;
49ad2498 src/eu/alefzero/owncloud/datamodel/FileDataStorageManager.java (Bartek Przybylski 2012-05-23 22:40:31 +0200  23) import java.util.Collections;
d4f8391f src/eu/alefzero/owncloud/datamodel/FileDataStorageManager.java (David A. Velasco  2012-07-11 13:51:57 +0200  24) import java.util.Iterator;
d4f8391f src/eu/alefzero/owncloud/datamodel/FileDataStorageManager.java (David A. Velasco  2012-07-11 13:51:57 +0200  25) import java.util.List;
b7ca8521 src/eu/alefzero/owncloud/datamodel/FileDataStorageManager.java (Bartek Przybylski 2012-04-14 22:28:55 +0200  26) import java.util.Vector;
b7ca8521 src/eu/alefzero/owncloud/datamodel/FileDataStorageManager.java (Bartek Przybylski 2012-04-14 22:28:55 +0200  27) 
a72d7bfe src/com/owncloud/android/datamodel/FileDataStorageManager.java (masensio          2013-07-05 14:54:02 +0200  28) import com.owncloud.android.DisplayUtils;
8e36e7cc src/com/owncloud/android/datamodel/FileDataStorageManager.java (zerginator        2013-03-12 07:56:27 +0100  29) import com.owncloud.android.Log_OC;
a4ba6170 src/com/owncloud/android/datamodel/FileDataStorageManager.java (Bartek Przybylski 2012-07-31 17:43:37 +0200  30) import com.owncloud.android.db.ProviderMeta;
a4ba6170 src/com/owncloud/android/datamodel/FileDataStorageManager.java (Bartek Przybylski 2012-07-31 17:43:37 +0200  31) import com.owncloud.android.db.ProviderMeta.ProviderTableMeta;
9aab2d26 src/com/owncloud/android/datamodel/FileDataStorageManager.java (David A. Velasco  2012-11-12 15:20:00 +0100  32) import com.owncloud.android.utils.FileStorageUtils;
a4ba6170 src/com/owncloud/android/datamodel/FileDataStorageManager.java (Bartek Przybylski 2012-07-31 17:43:37 +0200  33) 
b7ca8521 src/eu/alefzero/owncloud/datamodel/FileDataStorageManager.java (Bartek Przybylski 2012-04-14 22:28:55 +0200  34) import android.accounts.Account;
b7ca8521 src/eu/alefzero/owncloud/datamodel/FileDataStorageManager.java (Bartek Przybylski 2012-04-14 22:28:55 +0200  35) import android.content.ContentProviderClient;
d4f8391f src/eu/alefzero/owncloud/datamodel/FileDataStorageManager.java (David A. Velasco  2012-07-11 13:51:57 +0200  36) import android.content.ContentProviderOperation;
d4f8391f src/eu/alefzero/owncloud/datamodel/FileDataStorageManager.java (David A. Velasco  2012-07-11 13:51:57 +0200  37) import android.content.ContentProviderResult;
b7ca8521 src/eu/alefzero/owncloud/datamodel/FileDataStorageManager.java (Bartek Przybylski 2012-04-14 22:28:55 +0200  38) import android.content.ContentResolver;
b7ca8521 src/eu/alefzero/owncloud/datamodel/FileDataStorageManager.java (Bartek Przybylski 2012-04-14 22:28:55 +0200  39) import android.content.ContentValues;
d4f8391f src/eu/alefzero/owncloud/datamodel/FileDataStorageManager.java (David A. Velasco  2012-07-11 13:51:57 +0200  40) import android.content.OperationApplicationException;
b7ca8521 src/eu/alefzero/owncloud/datamodel/FileDataStorageManager.java (Bartek Przybylski 2012-04-14 22:28:55 +0200  41) import android.database.Cursor;
b7ca8521 src/eu/alefzero/owncloud/datamodel/FileDataStorageManager.java (Bartek Przybylski 2012-04-14 22:28:55 +0200  42) import android.net.Uri;
b7ca8521 src/eu/alefzero/owncloud/datamodel/FileDataStorageManager.java (Bartek Przybylski 2012-04-14 22:28:55 +0200  43) import android.os.RemoteException;
b7ca8521 src/eu/alefzero/owncloud/datamodel/FileDataStorageManager.java (Bartek Przybylski 2012-04-14 22:28:55 +0200  44) 
b7ca8521 src/eu/alefzero/owncloud/datamodel/FileDataStorageManager.java (Bartek Przybylski 2012-04-14 22:28:55 +0200  45) public class FileDataStorageManager implements DataStorageManager {
b7ca8521 src/eu/alefzero/owncloud/datamodel/FileDataStorageManager.java (Bartek Przybylski 2012-04-14 22:28:55 +0200  46) 
435b31ba src/eu/alefzero/owncloud/datamodel/FileDataStorageManager.java (Lennart Rosam     2012-05-16 09:48:34 +0200  47)     private ContentResolver mContentResolver;
435b31ba src/eu/alefzero/owncloud/datamodel/FileDataStorageManager.java (Lennart Rosam     2012-05-16 09:48:34 +0200  48)     private ContentProviderClient mContentProvider;
435b31ba src/eu/alefzero/owncloud/datamodel/FileDataStorageManager.java (Lennart Rosam     2012-05-16 09:48:34 +0200  49)     private Account mAccount;
eee74aa5 src/com/owncloud/android/datamodel/FileDataStorageManager.java (masensio          2013-07-04 18:47:38 +0200  50) 
435b31ba src/eu/alefzero/owncloud/datamodel/FileDataStorageManager.java (Lennart Rosam     2012-05-16 09:48:34 +0200  51)     private static String TAG = "FileDataStorageManager";
eee74aa5 src/com/owncloud/android/datamodel/FileDataStorageManager.java (masensio          2013-07-04 18:47:38 +0200  52) 
435b31ba src/eu/alefzero/owncloud/datamodel/FileDataStorageManager.java (Lennart Rosam     2012-05-16 09:48:34 +0200  53)     public FileDataStorageManager(Account account, ContentResolver cr) {
435b31ba src/eu/alefzero/owncloud/datamodel/FileDataStorageManager.java (Lennart Rosam     2012-05-16 09:48:34 +0200  54)         mContentProvider = null;
435b31ba src/eu/alefzero/owncloud/datamodel/FileDataStorageManager.java (Lennart Rosam     2012-05-16 09:48:34 +0200  55)         mContentResolver = cr;
435b31ba src/eu/alefzero/owncloud/datamodel/FileDataStorageManager.java (Lennart Rosam     2012-05-16 09:48:34 +0200  56)         mAccount = account;
2d8300d8 src/eu/alefzero/owncloud/datamodel/FileDataStorageManager.java (Bartek Przybylski 2012-04-29 11:27:26 +0200  57)     }
435b31ba src/eu/alefzero/owncloud/datamodel/FileDataStorageManager.java (Lennart Rosam     2012-05-16 09:48:34 +0200  58) 
435b31ba src/eu/alefzero/owncloud/datamodel/FileDataStorageManager.java (Lennart Rosam     2012-05-16 09:48:34 +0200  59)     public FileDataStorageManager(Account account, ContentProviderClient cp) {
435b31ba src/eu/alefzero/owncloud/datamodel/FileDataStorageManager.java (Lennart Rosam     2012-05-16 09:48:34 +0200  60)         mContentProvider = cp;
435b31ba src/eu/alefzero/owncloud/datamodel/FileDataStorageManager.java (Lennart Rosam     2012-05-16 09:48:34 +0200  61)         mContentResolver = null;
435b31ba src/eu/alefzero/owncloud/datamodel/FileDataStorageManager.java (Lennart Rosam     2012-05-16 09:48:34 +0200  62)         mAccount = account;
2d8300d8 src/eu/alefzero/owncloud/datamodel/FileDataStorageManager.java (Bartek Przybylski 2012-04-29 11:27:26 +0200  63)     }
435b31ba src/eu/alefzero/owncloud/datamodel/FileDataStorageManager.java (Lennart Rosam     2012-05-16 09:48:34 +0200  64) 
435b31ba src/eu/alefzero/owncloud/datamodel/FileDataStorageManager.java (Lennart Rosam     2012-05-16 09:48:34 +0200  65)     @Override
435b31ba src/eu/alefzero/owncloud/datamodel/FileDataStorageManager.java (Lennart Rosam     2012-05-16 09:48:34 +0200  66)     public OCFile getFileByPath(String path) {
435b31ba src/eu/alefzero/owncloud/datamodel/FileDataStorageManager.java (Lennart Rosam     2012-05-16 09:48:34 +0200  67)         Cursor c = getCursorForValue(ProviderTableMeta.FILE_PATH, path);
435b31ba src/eu/alefzero/owncloud/datamodel/FileDataStorageManager.java (Lennart Rosam     2012-05-16 09:48:34 +0200  68)         OCFile file = null;
435b31ba src/eu/alefzero/owncloud/datamodel/FileDataStorageManager.java (Lennart Rosam     2012-05-16 09:48:34 +0200  69)         if (c.moveToFirst()) {
435b31ba src/eu/alefzero/owncloud/datamodel/FileDataStorageManager.java (Lennart Rosam     2012-05-16 09:48:34 +0200  70)             file = createFileInstance(c);
b7ca8521 src/eu/alefzero/owncloud/datamodel/FileDataStorageManager.java (Bartek Przybylski 2012-04-14 22:28:55 +0200  71)         }
eed9eed8 src/eu/alefzero/owncloud/datamodel/FileDataStorageManager.java (Bartek Przybylski 2012-06-18 19:40:47 +0200  72)         c.close();
471bff2a src/com/owncloud/android/datamodel/FileDataStorageManager.java (David A. Velasco  2013-01-10 11:39:22 +0100  73)         if (file == null && OCFile.PATH_SEPARATOR.equals(path)) {
471bff2a src/com/owncloud/android/datamodel/FileDataStorageManager.java (David A. Velasco  2013-01-10 11:39:22 +0100  74)             return createRootDir(); // root should always exist
471bff2a src/com/owncloud/android/datamodel/FileDataStorageManager.java (David A. Velasco  2013-01-10 11:39:22 +0100  75)         }
435b31ba src/eu/alefzero/owncloud/datamodel/FileDataStorageManager.java (Lennart Rosam     2012-05-16 09:48:34 +0200  76)         return file;
435b31ba src/eu/alefzero/owncloud/datamodel/FileDataStorageManager.java (Lennart Rosam     2012-05-16 09:48:34 +0200  77)     }
471bff2a src/com/owncloud/android/datamodel/FileDataStorageManager.java (David A. Velasco  2013-01-10 11:39:22 +0100  78) 
eee74aa5 src/com/owncloud/android/datamodel/FileDataStorageManager.java (masensio          2013-07-04 18:47:38 +0200  79) 
471bff2a src/com/owncloud/android/datamodel/FileDataStorageManager.java (David A. Velasco  2013-01-10 11:39:22 +0100  80)     private OCFile createRootDir() {
471bff2a src/com/owncloud/android/datamodel/FileDataStorageManager.java (David A. Velasco  2013-01-10 11:39:22 +0100  81)         OCFile file = new OCFile(OCFile.PATH_SEPARATOR);
471bff2a src/com/owncloud/android/datamodel/FileDataStorageManager.java (David A. Velasco  2013-01-10 11:39:22 +0100  82)         file.setMimetype("DIR");
471bff2a src/com/owncloud/android/datamodel/FileDataStorageManager.java (David A. Velasco  2013-01-10 11:39:22 +0100  83)         file.setParentId(DataStorageManager.ROOT_PARENT_ID);
471bff2a src/com/owncloud/android/datamodel/FileDataStorageManager.java (David A. Velasco  2013-01-10 11:39:22 +0100  84)         saveFile(file);
471bff2a src/com/owncloud/android/datamodel/FileDataStorageManager.java (David A. Velasco  2013-01-10 11:39:22 +0100  85)         return file;
471bff2a src/com/owncloud/android/datamodel/FileDataStorageManager.java (David A. Velasco  2013-01-10 11:39:22 +0100  86)     }
471bff2a src/com/owncloud/android/datamodel/FileDataStorageManager.java (David A. Velasco  2013-01-10 11:39:22 +0100  87) 
435b31ba src/eu/alefzero/owncloud/datamodel/FileDataStorageManager.java (Lennart Rosam     2012-05-16 09:48:34 +0200  88)     @Override
435b31ba src/eu/alefzero/owncloud/datamodel/FileDataStorageManager.java (Lennart Rosam     2012-05-16 09:48:34 +0200  89)     public OCFile getFileById(long id) {
435b31ba src/eu/alefzero/owncloud/datamodel/FileDataStorageManager.java (Lennart Rosam     2012-05-16 09:48:34 +0200  90)         Cursor c = getCursorForValue(ProviderTableMeta._ID, String.valueOf(id));
435b31ba src/eu/alefzero/owncloud/datamodel/FileDataStorageManager.java (Lennart Rosam     2012-05-16 09:48:34 +0200  91)         OCFile file = null;
435b31ba src/eu/alefzero/owncloud/datamodel/FileDataStorageManager.java (Lennart Rosam     2012-05-16 09:48:34 +0200  92)         if (c.moveToFirst()) {
435b31ba src/eu/alefzero/owncloud/datamodel/FileDataStorageManager.java (Lennart Rosam     2012-05-16 09:48:34 +0200  93)             file = createFileInstance(c);
b7ca8521 src/eu/alefzero/owncloud/datamodel/FileDataStorageManager.java (Bartek Przybylski 2012-04-14 22:28:55 +0200  94)         }
eed9eed8 src/eu/alefzero/owncloud/datamodel/FileDataStorageManager.java (Bartek Przybylski 2012-06-18 19:40:47 +0200  95)         c.close();
435b31ba src/eu/alefzero/owncloud/datamodel/FileDataStorageManager.java (Lennart Rosam     2012-05-16 09:48:34 +0200  96)         return file;
435b31ba src/eu/alefzero/owncloud/datamodel/FileDataStorageManager.java (Lennart Rosam     2012-05-16 09:48:34 +0200  97)     }
eee74aa5 src/com/owncloud/android/datamodel/FileDataStorageManager.java (masensio          2013-07-04 18:47:38 +0200  98) 
ba148a82 src/com/owncloud/android/datamodel/FileDataStorageManager.java (Bartek Przybylski 2012-08-22 19:32:42 +0200  99)     public OCFile getFileByLocalPath(String path) {
ba148a82 src/com/owncloud/android/datamodel/FileDataStorageManager.java (Bartek Przybylski 2012-08-22 19:32:42 +0200 100)         Cursor c = getCursorForValue(ProviderTableMeta.FILE_STORAGE_PATH, path);
ba148a82 src/com/owncloud/android/datamodel/FileDataStorageManager.java (Bartek Przybylski 2012-08-22 19:32:42 +0200 101)         OCFile file = null;
ba148a82 src/com/owncloud/android/datamodel/FileDataStorageManager.java (Bartek Przybylski 2012-08-22 19:32:42 +0200 102)         if (c.moveToFirst()) {
ba148a82 src/com/owncloud/android/datamodel/FileDataStorageManager.java (Bartek Przybylski 2012-08-22 19:32:42 +0200 103)             file = createFileInstance(c);
ba148a82 src/com/owncloud/android/datamodel/FileDataStorageManager.java (Bartek Przybylski 2012-08-22 19:32:42 +0200 104)         }
ba148a82 src/com/owncloud/android/datamodel/FileDataStorageManager.java (Bartek Przybylski 2012-08-22 19:32:42 +0200 105)         c.close();
ba148a82 src/com/owncloud/android/datamodel/FileDataStorageManager.java (Bartek Przybylski 2012-08-22 19:32:42 +0200 106)         return file;
ba148a82 src/com/owncloud/android/datamodel/FileDataStorageManager.java (Bartek Przybylski 2012-08-22 19:32:42 +0200 107)     }
435b31ba src/eu/alefzero/owncloud/datamodel/FileDataStorageManager.java (Lennart Rosam     2012-05-16 09:48:34 +0200 108) 
435b31ba src/eu/alefzero/owncloud/datamodel/FileDataStorageManager.java (Lennart Rosam     2012-05-16 09:48:34 +0200 109)     @Override
435b31ba src/eu/alefzero/owncloud/datamodel/FileDataStorageManager.java (Lennart Rosam     2012-05-16 09:48:34 +0200 110)     public boolean fileExists(long id) {
435b31ba src/eu/alefzero/owncloud/datamodel/FileDataStorageManager.java (Lennart Rosam     2012-05-16 09:48:34 +0200 111)         return fileExists(ProviderTableMeta._ID, String.valueOf(id));
435b31ba src/eu/alefzero/owncloud/datamodel/FileDataStorageManager.java (Lennart Rosam     2012-05-16 09:48:34 +0200 112)     }
435b31ba src/eu/alefzero/owncloud/datamodel/FileDataStorageManager.java (Lennart Rosam     2012-05-16 09:48:34 +0200 113) 
435b31ba src/eu/alefzero/owncloud/datamodel/FileDataStorageManager.java (Lennart Rosam     2012-05-16 09:48:34 +0200 114)     @Override
435b31ba src/eu/alefzero/owncloud/datamodel/FileDataStorageManager.java (Lennart Rosam     2012-05-16 09:48:34 +0200 115)     public boolean fileExists(String path) {
435b31ba src/eu/alefzero/owncloud/datamodel/FileDataStorageManager.java (Lennart Rosam     2012-05-16 09:48:34 +0200 116)         return fileExists(ProviderTableMeta.FILE_PATH, path);
b7ca8521 src/eu/alefzero/owncloud/datamodel/FileDataStorageManager.java (Bartek Przybylski 2012-04-14 22:28:55 +0200 117)     }
87814cec src/eu/alefzero/owncloud/datamodel/FileDataStorageManager.java (Bartek Przybylski 2012-04-29 00:14:01 +0200 118) 
435b31ba src/eu/alefzero/owncloud/datamodel/FileDataStorageManager.java (Lennart Rosam     2012-05-16 09:48:34 +0200 119)     @Override
435b31ba src/eu/alefzero/owncloud/datamodel/FileDataStorageManager.java (Lennart Rosam     2012-05-16 09:48:34 +0200 120)     public boolean saveFile(OCFile file) {
435b31ba src/eu/alefzero/owncloud/datamodel/FileDataStorageManager.java (Lennart Rosam     2012-05-16 09:48:34 +0200 121)         boolean overriden = false;
435b31ba src/eu/alefzero/owncloud/datamodel/FileDataStorageManager.java (Lennart Rosam     2012-05-16 09:48:34 +0200 122)         ContentValues cv = new ContentValues();
435b31ba src/eu/alefzero/owncloud/datamodel/FileDataStorageManager.java (Lennart Rosam     2012-05-16 09:48:34 +0200 123)         cv.put(ProviderTableMeta.FILE_MODIFIED, file.getModificationTimestamp());
abd5f515 src/com/owncloud/android/datamodel/FileDataStorageManager.java (David A. Velasco  2012-12-05 16:14:01 +0100 124)         cv.put(ProviderTableMeta.FILE_MODIFIED_AT_LAST_SYNC_FOR_DATA, file.getModificationTimestampAtLastSyncForData());
435b31ba src/eu/alefzero/owncloud/datamodel/FileDataStorageManager.java (Lennart Rosam     2012-05-16 09:48:34 +0200 125)         cv.put(ProviderTableMeta.FILE_CREATION, file.getCreationTimestamp());
435b31ba src/eu/alefzero/owncloud/datamodel/FileDataStorageManager.java (Lennart Rosam     2012-05-16 09:48:34 +0200 126)         cv.put(ProviderTableMeta.FILE_CONTENT_LENGTH, file.getFileLength());
435b31ba src/eu/alefzero/owncloud/datamodel/FileDataStorageManager.java (Lennart Rosam     2012-05-16 09:48:34 +0200 127)         cv.put(ProviderTableMeta.FILE_CONTENT_TYPE, file.getMimetype());
435b31ba src/eu/alefzero/owncloud/datamodel/FileDataStorageManager.java (Lennart Rosam     2012-05-16 09:48:34 +0200 128)         cv.put(ProviderTableMeta.FILE_NAME, file.getFileName());
435b31ba src/eu/alefzero/owncloud/datamodel/FileDataStorageManager.java (Lennart Rosam     2012-05-16 09:48:34 +0200 129)         if (file.getParentId() != 0)
435b31ba src/eu/alefzero/owncloud/datamodel/FileDataStorageManager.java (Lennart Rosam     2012-05-16 09:48:34 +0200 130)             cv.put(ProviderTableMeta.FILE_PARENT, file.getParentId());
5ae7704e src/eu/alefzero/owncloud/datamodel/FileDataStorageManager.java (Lennart Rosam     2012-05-23 22:39:37 +0200 131)         cv.put(ProviderTableMeta.FILE_PATH, file.getRemotePath());
35ace0b9 src/eu/alefzero/owncloud/datamodel/FileDataStorageManager.java (Bartek Przybylski 2012-05-27 00:03:43 +0200 132)         if (!file.isDirectory())
35ace0b9 src/eu/alefzero/owncloud/datamodel/FileDataStorageManager.java (Bartek Przybylski 2012-05-27 00:03:43 +0200 133)             cv.put(ProviderTableMeta.FILE_STORAGE_PATH, file.getStoragePath());
435b31ba src/eu/alefzero/owncloud/datamodel/FileDataStorageManager.java (Lennart Rosam     2012-05-16 09:48:34 +0200 134)         cv.put(ProviderTableMeta.FILE_ACCOUNT_OWNER, mAccount.name);
ff2271a8 src/com/owncloud/android/datamodel/FileDataStorageManager.java (David A. Velasco  2012-11-15 17:18:04 +0100 135)         cv.put(ProviderTableMeta.FILE_LAST_SYNC_DATE, file.getLastSyncDateForProperties());
ff2271a8 src/com/owncloud/android/datamodel/FileDataStorageManager.java (David A. Velasco  2012-11-15 17:18:04 +0100 136)         cv.put(ProviderTableMeta.FILE_LAST_SYNC_DATE_FOR_DATA, file.getLastSyncDateForData());
3113c459 src/eu/alefzero/owncloud/datamodel/FileDataStorageManager.java (Bartek Przybylski 2012-07-08 16:57:48 +0200 137)         cv.put(ProviderTableMeta.FILE_KEEP_IN_SYNC, file.keepInSync() ? 1 : 0);
435b31ba src/eu/alefzero/owncloud/datamodel/FileDataStorageManager.java (Lennart Rosam     2012-05-16 09:48:34 +0200 138) 
4c50eb4d src/com/owncloud/android/datamodel/FileDataStorageManager.java (David A. Velasco  2012-11-23 10:25:06 +0100 139)         boolean sameRemotePath = fileExists(file.getRemotePath());
7a7fe5db src/com/owncloud/android/datamodel/FileDataStorageManager.java (David A. Velasco  2013-07-09 12:32:44 +0200 140)         boolean changesSizeOfAncestors = false;
4c50eb4d src/com/owncloud/android/datamodel/FileDataStorageManager.java (David A. Velasco  2012-11-23 10:25:06 +0100 141)         if (sameRemotePath ||
eee74aa5 src/com/owncloud/android/datamodel/FileDataStorageManager.java (masensio          2013-07-04 18:47:38 +0200 142)                 fileExists(file.getFileId())        ) {           // for renamed files; no more delete and create
eee74aa5 src/com/owncloud/android/datamodel/FileDataStorageManager.java (masensio          2013-07-04 18:47:38 +0200 143) 
7a7fe5db src/com/owncloud/android/datamodel/FileDataStorageManager.java (David A. Velasco  2013-07-09 12:32:44 +0200 144)             OCFile oldFile = null;
4c50eb4d src/com/owncloud/android/datamodel/FileDataStorageManager.java (David A. Velasco  2012-11-23 10:25:06 +0100 145)             if (sameRemotePath) {
7a7fe5db src/com/owncloud/android/datamodel/FileDataStorageManager.java (David A. Velasco  2013-07-09 12:32:44 +0200 146)                 oldFile = getFileByPath(file.getRemotePath());
4c50eb4d src/com/owncloud/android/datamodel/FileDataStorageManager.java (David A. Velasco  2012-11-23 10:25:06 +0100 147)                 file.setFileId(oldFile.getFileId());
7a7fe5db src/com/owncloud/android/datamodel/FileDataStorageManager.java (David A. Velasco  2013-07-09 12:32:44 +0200 148)             } else {
7a7fe5db src/com/owncloud/android/datamodel/FileDataStorageManager.java (David A. Velasco  2013-07-09 12:32:44 +0200 149)                 oldFile = getFileById(file.getFileId());
4c50eb4d src/com/owncloud/android/datamodel/FileDataStorageManager.java (David A. Velasco  2012-11-23 10:25:06 +0100 150)             }
7a7fe5db src/com/owncloud/android/datamodel/FileDataStorageManager.java (David A. Velasco  2013-07-09 12:32:44 +0200 151)             changesSizeOfAncestors = (oldFile.getFileLength() != file.getFileLength());
435b31ba src/eu/alefzero/owncloud/datamodel/FileDataStorageManager.java (Lennart Rosam     2012-05-16 09:48:34 +0200 152) 
435b31ba src/eu/alefzero/owncloud/datamodel/FileDataStorageManager.java (Lennart Rosam     2012-05-16 09:48:34 +0200 153)             overriden = true;
435b31ba src/eu/alefzero/owncloud/datamodel/FileDataStorageManager.java (Lennart Rosam     2012-05-16 09:48:34 +0200 154)             if (getContentResolver() != null) {
435b31ba src/eu/alefzero/owncloud/datamodel/FileDataStorageManager.java (Lennart Rosam     2012-05-16 09:48:34 +0200 155)                 getContentResolver().update(ProviderTableMeta.CONTENT_URI, cv,
435b31ba src/eu/alefzero/owncloud/datamodel/FileDataStorageManager.java (Lennart Rosam     2012-05-16 09:48:34 +0200 156)                         ProviderTableMeta._ID + "=?",
435b31ba src/eu/alefzero/owncloud/datamodel/FileDataStorageManager.java (Lennart Rosam     2012-05-16 09:48:34 +0200 157)                         new String[] { String.valueOf(file.getFileId()) });
435b31ba src/eu/alefzero/owncloud/datamodel/FileDataStorageManager.java (Lennart Rosam     2012-05-16 09:48:34 +0200 158)             } else {
435b31ba src/eu/alefzero/owncloud/datamodel/FileDataStorageManager.java (Lennart Rosam     2012-05-16 09:48:34 +0200 159)                 try {
435b31ba src/eu/alefzero/owncloud/datamodel/FileDataStorageManager.java (Lennart Rosam     2012-05-16 09:48:34 +0200 160)                     getContentProvider().update(ProviderTableMeta.CONTENT_URI,
435b31ba src/eu/alefzero/owncloud/datamodel/FileDataStorageManager.java (Lennart Rosam     2012-05-16 09:48:34 +0200 161)                             cv, ProviderTableMeta._ID + "=?",
435b31ba src/eu/alefzero/owncloud/datamodel/FileDataStorageManager.java (Lennart Rosam     2012-05-16 09:48:34 +0200 162)                             new String[] { String.valueOf(file.getFileId()) });
435b31ba src/eu/alefzero/owncloud/datamodel/FileDataStorageManager.java (Lennart Rosam     2012-05-16 09:48:34 +0200 163)                 } catch (RemoteException e) {
8e36e7cc src/com/owncloud/android/datamodel/FileDataStorageManager.java (zerginator        2013-03-12 07:56:27 +0100 164)                     Log_OC.e(TAG,
435b31ba src/eu/alefzero/owncloud/datamodel/FileDataStorageManager.java (Lennart Rosam     2012-05-16 09:48:34 +0200 165)                             "Fail to insert insert file to database "
435b31ba src/eu/alefzero/owncloud/datamodel/FileDataStorageManager.java (Lennart Rosam     2012-05-16 09:48:34 +0200 166)                                     + e.getMessage());
435b31ba src/eu/alefzero/owncloud/datamodel/FileDataStorageManager.java (Lennart Rosam     2012-05-16 09:48:34 +0200 167)                 }
435b31ba src/eu/alefzero/owncloud/datamodel/FileDataStorageManager.java (Lennart Rosam     2012-05-16 09:48:34 +0200 168)             }
435b31ba src/eu/alefzero/owncloud/datamodel/FileDataStorageManager.java (Lennart Rosam     2012-05-16 09:48:34 +0200 169)         } else {
7a7fe5db src/com/owncloud/android/datamodel/FileDataStorageManager.java (David A. Velasco  2013-07-09 12:32:44 +0200 170)             changesSizeOfAncestors = true;
435b31ba src/eu/alefzero/owncloud/datamodel/FileDataStorageManager.java (Lennart Rosam     2012-05-16 09:48:34 +0200 171)             Uri result_uri = null;
435b31ba src/eu/alefzero/owncloud/datamodel/FileDataStorageManager.java (Lennart Rosam     2012-05-16 09:48:34 +0200 172)             if (getContentResolver() != null) {
435b31ba src/eu/alefzero/owncloud/datamodel/FileDataStorageManager.java (Lennart Rosam     2012-05-16 09:48:34 +0200 173)                 result_uri = getContentResolver().insert(
435b31ba src/eu/alefzero/owncloud/datamodel/FileDataStorageManager.java (Lennart Rosam     2012-05-16 09:48:34 +0200 174)                         ProviderTableMeta.CONTENT_URI_FILE, cv);
435b31ba src/eu/alefzero/owncloud/datamodel/FileDataStorageManager.java (Lennart Rosam     2012-05-16 09:48:34 +0200 175)             } else {
435b31ba src/eu/alefzero/owncloud/datamodel/FileDataStorageManager.java (Lennart Rosam     2012-05-16 09:48:34 +0200 176)                 try {
435b31ba src/eu/alefzero/owncloud/datamodel/FileDataStorageManager.java (Lennart Rosam     2012-05-16 09:48:34 +0200 177)                     result_uri = getContentProvider().insert(
435b31ba src/eu/alefzero/owncloud/datamodel/FileDataStorageManager.java (Lennart Rosam     2012-05-16 09:48:34 +0200 178)                             ProviderTableMeta.CONTENT_URI_FILE, cv);
435b31ba src/eu/alefzero/owncloud/datamodel/FileDataStorageManager.java (Lennart Rosam     2012-05-16 09:48:34 +0200 179)                 } catch (RemoteException e) {
8e36e7cc src/com/owncloud/android/datamodel/FileDataStorageManager.java (zerginator        2013-03-12 07:56:27 +0100 180)                     Log_OC.e(TAG,
435b31ba src/eu/alefzero/owncloud/datamodel/FileDataStorageManager.java (Lennart Rosam     2012-05-16 09:48:34 +0200 181)                             "Fail to insert insert file to database "
435b31ba src/eu/alefzero/owncloud/datamodel/FileDataStorageManager.java (Lennart Rosam     2012-05-16 09:48:34 +0200 182)                                     + e.getMessage());
435b31ba src/eu/alefzero/owncloud/datamodel/FileDataStorageManager.java (Lennart Rosam     2012-05-16 09:48:34 +0200 183)                 }
435b31ba src/eu/alefzero/owncloud/datamodel/FileDataStorageManager.java (Lennart Rosam     2012-05-16 09:48:34 +0200 184)             }
435b31ba src/eu/alefzero/owncloud/datamodel/FileDataStorageManager.java (Lennart Rosam     2012-05-16 09:48:34 +0200 185)             if (result_uri != null) {
435b31ba src/eu/alefzero/owncloud/datamodel/FileDataStorageManager.java (Lennart Rosam     2012-05-16 09:48:34 +0200 186)                 long new_id = Long.parseLong(result_uri.getPathSegments()
435b31ba src/eu/alefzero/owncloud/datamodel/FileDataStorageManager.java (Lennart Rosam     2012-05-16 09:48:34 +0200 187)                         .get(1));
435b31ba src/eu/alefzero/owncloud/datamodel/FileDataStorageManager.java (Lennart Rosam     2012-05-16 09:48:34 +0200 188)                 file.setFileId(new_id);
a72d7bfe src/com/owncloud/android/datamodel/FileDataStorageManager.java (masensio          2013-07-05 14:54:02 +0200 189)             }            
b7ca8521 src/eu/alefzero/owncloud/datamodel/FileDataStorageManager.java (Bartek Przybylski 2012-04-14 22:28:55 +0200 190)         }
435b31ba src/eu/alefzero/owncloud/datamodel/FileDataStorageManager.java (Lennart Rosam     2012-05-16 09:48:34 +0200 191) 
7a7fe5db src/com/owncloud/android/datamodel/FileDataStorageManager.java (David A. Velasco  2013-07-09 12:32:44 +0200 192)         if (file.isDirectory()) {
7a7fe5db src/com/owncloud/android/datamodel/FileDataStorageManager.java (David A. Velasco  2013-07-09 12:32:44 +0200 193)             calculateFolderSize(file.getFileId());
7a7fe5db src/com/owncloud/android/datamodel/FileDataStorageManager.java (David A. Velasco  2013-07-09 12:32:44 +0200 194)             if (file.needsUpdatingWhileSaving()) {
7a7fe5db src/com/owncloud/android/datamodel/FileDataStorageManager.java (David A. Velasco  2013-07-09 12:32:44 +0200 195)                 for (OCFile f : getDirectoryContent(file))
7a7fe5db src/com/owncloud/android/datamodel/FileDataStorageManager.java (David A. Velasco  2013-07-09 12:32:44 +0200 196)                     saveFile(f);
7a7fe5db src/com/owncloud/android/datamodel/FileDataStorageManager.java (David A. Velasco  2013-07-09 12:32:44 +0200 197)             }
7a7fe5db src/com/owncloud/android/datamodel/FileDataStorageManager.java (David A. Velasco  2013-07-09 12:32:44 +0200 198)         }
a72d7bfe src/com/owncloud/android/datamodel/FileDataStorageManager.java (masensio          2013-07-05 14:54:02 +0200 199)         
7a7fe5db src/com/owncloud/android/datamodel/FileDataStorageManager.java (David A. Velasco  2013-07-09 12:32:44 +0200 200)         if (changesSizeOfAncestors || file.isDirectory()) {
7a7fe5db src/com/owncloud/android/datamodel/FileDataStorageManager.java (David A. Velasco  2013-07-09 12:32:44 +0200 201)             updateSizesToTheRoot(file.getParentId());
7a7fe5db src/com/owncloud/android/datamodel/FileDataStorageManager.java (David A. Velasco  2013-07-09 12:32:44 +0200 202)         }
a72d7bfe src/com/owncloud/android/datamodel/FileDataStorageManager.java (masensio          2013-07-05 14:54:02 +0200 203)         
435b31ba src/eu/alefzero/owncloud/datamodel/FileDataStorageManager.java (Lennart Rosam     2012-05-16 09:48:34 +0200 204)         return overriden;
435b31ba src/eu/alefzero/owncloud/datamodel/FileDataStorageManager.java (Lennart Rosam     2012-05-16 09:48:34 +0200 205)     }
435b31ba src/eu/alefzero/owncloud/datamodel/FileDataStorageManager.java (Lennart Rosam     2012-05-16 09:48:34 +0200 206) 
d4f8391f src/eu/alefzero/owncloud/datamodel/FileDataStorageManager.java (David A. Velasco  2012-07-11 13:51:57 +0200 207) 
d4f8391f src/eu/alefzero/owncloud/datamodel/FileDataStorageManager.java (David A. Velasco  2012-07-11 13:51:57 +0200 208)     @Override
d4f8391f src/eu/alefzero/owncloud/datamodel/FileDataStorageManager.java (David A. Velasco  2012-07-11 13:51:57 +0200 209)     public void saveFiles(List<OCFile> files) {
eee74aa5 src/com/owncloud/android/datamodel/FileDataStorageManager.java (masensio          2013-07-04 18:47:38 +0200 210) 
d4f8391f src/eu/alefzero/owncloud/datamodel/FileDataStorageManager.java (David A. Velasco  2012-07-11 13:51:57 +0200 211)         Iterator<OCFile> filesIt = files.iterator();
d4f8391f src/eu/alefzero/owncloud/datamodel/FileDataStorageManager.java (David A. Velasco  2012-07-11 13:51:57 +0200 212)         ArrayList<ContentProviderOperation> operations = new ArrayList<ContentProviderOperation>(files.size());
d4f8391f src/eu/alefzero/owncloud/datamodel/FileDataStorageManager.java (David A. Velasco  2012-07-11 13:51:57 +0200 213)         OCFile file = null;
d4f8391f src/eu/alefzero/owncloud/datamodel/FileDataStorageManager.java (David A. Velasco  2012-07-11 13:51:57 +0200 214) 
d4f8391f src/eu/alefzero/owncloud/datamodel/FileDataStorageManager.java (David A. Velasco  2012-07-11 13:51:57 +0200 215)         // prepare operations to perform
d4f8391f src/eu/alefzero/owncloud/datamodel/FileDataStorageManager.java (David A. Velasco  2012-07-11 13:51:57 +0200 216)         while (filesIt.hasNext()) {
d4f8391f src/eu/alefzero/owncloud/datamodel/FileDataStorageManager.java (David A. Velasco  2012-07-11 13:51:57 +0200 217)             file = filesIt.next();
d4f8391f src/eu/alefzero/owncloud/datamodel/FileDataStorageManager.java (David A. Velasco  2012-07-11 13:51:57 +0200 218)             ContentValues cv = new ContentValues();
d4f8391f src/eu/alefzero/owncloud/datamodel/FileDataStorageManager.java (David A. Velasco  2012-07-11 13:51:57 +0200 219)             cv.put(ProviderTableMeta.FILE_MODIFIED, file.getModificationTimestamp());
abd5f515 src/com/owncloud/android/datamodel/FileDataStorageManager.java (David A. Velasco  2012-12-05 16:14:01 +0100 220)             cv.put(ProviderTableMeta.FILE_MODIFIED_AT_LAST_SYNC_FOR_DATA, file.getModificationTimestampAtLastSyncForData());
d4f8391f src/eu/alefzero/owncloud/datamodel/FileDataStorageManager.java (David A. Velasco  2012-07-11 13:51:57 +0200 221)             cv.put(ProviderTableMeta.FILE_CREATION, file.getCreationTimestamp());
d4f8391f src/eu/alefzero/owncloud/datamodel/FileDataStorageManager.java (David A. Velasco  2012-07-11 13:51:57 +0200 222)             cv.put(ProviderTableMeta.FILE_CONTENT_LENGTH, file.getFileLength());
d4f8391f src/eu/alefzero/owncloud/datamodel/FileDataStorageManager.java (David A. Velasco  2012-07-11 13:51:57 +0200 223)             cv.put(ProviderTableMeta.FILE_CONTENT_TYPE, file.getMimetype());
d4f8391f src/eu/alefzero/owncloud/datamodel/FileDataStorageManager.java (David A. Velasco  2012-07-11 13:51:57 +0200 224)             cv.put(ProviderTableMeta.FILE_NAME, file.getFileName());
d4f8391f src/eu/alefzero/owncloud/datamodel/FileDataStorageManager.java (David A. Velasco  2012-07-11 13:51:57 +0200 225)             if (file.getParentId() != 0)
d4f8391f src/eu/alefzero/owncloud/datamodel/FileDataStorageManager.java (David A. Velasco  2012-07-11 13:51:57 +0200 226)                 cv.put(ProviderTableMeta.FILE_PARENT, file.getParentId());
d4f8391f src/eu/alefzero/owncloud/datamodel/FileDataStorageManager.java (David A. Velasco  2012-07-11 13:51:57 +0200 227)             cv.put(ProviderTableMeta.FILE_PATH, file.getRemotePath());
d4f8391f src/eu/alefzero/owncloud/datamodel/FileDataStorageManager.java (David A. Velasco  2012-07-11 13:51:57 +0200 228)             if (!file.isDirectory())
d4f8391f src/eu/alefzero/owncloud/datamodel/FileDataStorageManager.java (David A. Velasco  2012-07-11 13:51:57 +0200 229)                 cv.put(ProviderTableMeta.FILE_STORAGE_PATH, file.getStoragePath());
d4f8391f src/eu/alefzero/owncloud/datamodel/FileDataStorageManager.java (David A. Velasco  2012-07-11 13:51:57 +0200 230)             cv.put(ProviderTableMeta.FILE_ACCOUNT_OWNER, mAccount.name);
ff2271a8 src/com/owncloud/android/datamodel/FileDataStorageManager.java (David A. Velasco  2012-11-15 17:18:04 +0100 231)             cv.put(ProviderTableMeta.FILE_LAST_SYNC_DATE, file.getLastSyncDateForProperties());
ff2271a8 src/com/owncloud/android/datamodel/FileDataStorageManager.java (David A. Velasco  2012-11-15 17:18:04 +0100 232)             cv.put(ProviderTableMeta.FILE_LAST_SYNC_DATE_FOR_DATA, file.getLastSyncDateForData());
d4f8391f src/eu/alefzero/owncloud/datamodel/FileDataStorageManager.java (David A. Velasco  2012-07-11 13:51:57 +0200 233)             cv.put(ProviderTableMeta.FILE_KEEP_IN_SYNC, file.keepInSync() ? 1 : 0);
d4f8391f src/eu/alefzero/owncloud/datamodel/FileDataStorageManager.java (David A. Velasco  2012-07-11 13:51:57 +0200 234) 
d4f8391f src/eu/alefzero/owncloud/datamodel/FileDataStorageManager.java (David A. Velasco  2012-07-11 13:51:57 +0200 235)             if (fileExists(file.getRemotePath())) {
1baa27a4 src/com/owncloud/android/datamodel/FileDataStorageManager.java (David A. Velasco  2012-11-06 13:35:08 +0100 236)                 OCFile oldFile = getFileByPath(file.getRemotePath());
1baa27a4 src/com/owncloud/android/datamodel/FileDataStorageManager.java (David A. Velasco  2012-11-06 13:35:08 +0100 237)                 file.setFileId(oldFile.getFileId());
5f0dfa61 src/com/owncloud/android/datamodel/FileDataStorageManager.java (masensio          2013-07-11 18:57:35 +0200 238)                
5f0dfa61 src/com/owncloud/android/datamodel/FileDataStorageManager.java (masensio          2013-07-11 18:57:35 +0200 239)                 if (file.isDirectory()) {
5f0dfa61 src/com/owncloud/android/datamodel/FileDataStorageManager.java (masensio          2013-07-11 18:57:35 +0200 240)                     cv.put(ProviderTableMeta.FILE_CONTENT_LENGTH, oldFile.getFileLength());
5f0dfa61 src/com/owncloud/android/datamodel/FileDataStorageManager.java (masensio          2013-07-11 18:57:35 +0200 241)                     file.setFileLength(oldFile.getFileLength());
5f0dfa61 src/com/owncloud/android/datamodel/FileDataStorageManager.java (masensio          2013-07-11 18:57:35 +0200 242)                 }
5f0dfa61 src/com/owncloud/android/datamodel/FileDataStorageManager.java (masensio          2013-07-11 18:57:35 +0200 243)                 
d4f8391f src/eu/alefzero/owncloud/datamodel/FileDataStorageManager.java (David A. Velasco  2012-07-11 13:51:57 +0200 244)                 operations.add(ContentProviderOperation.newUpdate(ProviderTableMeta.CONTENT_URI).
d4f8391f src/eu/alefzero/owncloud/datamodel/FileDataStorageManager.java (David A. Velasco  2012-07-11 13:51:57 +0200 245)                         withValues(cv).
d4f8391f src/eu/alefzero/owncloud/datamodel/FileDataStorageManager.java (David A. Velasco  2012-07-11 13:51:57 +0200 246)                         withSelection(  ProviderTableMeta._ID + "=?", 
eee74aa5 src/com/owncloud/android/datamodel/FileDataStorageManager.java (masensio          2013-07-04 18:47:38 +0200 247)                                 new String[] { String.valueOf(file.getFileId()) })
eee74aa5 src/com/owncloud/android/datamodel/FileDataStorageManager.java (masensio          2013-07-04 18:47:38 +0200 248)                                 .build());
eee74aa5 src/com/owncloud/android/datamodel/FileDataStorageManager.java (masensio          2013-07-04 18:47:38 +0200 249) 
1baa27a4 src/com/owncloud/android/datamodel/FileDataStorageManager.java (David A. Velasco  2012-11-06 13:35:08 +0100 250)             } else if (fileExists(file.getFileId())) {
eee74aa5 src/com/owncloud/android/datamodel/FileDataStorageManager.java (masensio          2013-07-04 18:47:38 +0200 251)                 OCFile oldFile = getFileById(file.getFileId());
eee74aa5 src/com/owncloud/android/datamodel/FileDataStorageManager.java (masensio          2013-07-04 18:47:38 +0200 252)                 if (file.getStoragePath() == null && oldFile.getStoragePath() != null)
eee74aa5 src/com/owncloud/android/datamodel/FileDataStorageManager.java (masensio          2013-07-04 18:47:38 +0200 253)                     file.setStoragePath(oldFile.getStoragePath());
5f0dfa61 src/com/owncloud/android/datamodel/FileDataStorageManager.java (masensio          2013-07-11 18:57:35 +0200 254)                 
5f0dfa61 src/com/owncloud/android/datamodel/FileDataStorageManager.java (masensio          2013-07-11 18:57:35 +0200 255)                 if (!file.isDirectory())
5f0dfa61 src/com/owncloud/android/datamodel/FileDataStorageManager.java (masensio          2013-07-11 18:57:35 +0200 256)                     cv.put(ProviderTableMeta.FILE_STORAGE_PATH, file.getStoragePath());
5f0dfa61 src/com/owncloud/android/datamodel/FileDataStorageManager.java (masensio          2013-07-11 18:57:35 +0200 257)                 else {
5f0dfa61 src/com/owncloud/android/datamodel/FileDataStorageManager.java (masensio          2013-07-11 18:57:35 +0200 258)                     cv.put(ProviderTableMeta.FILE_CONTENT_LENGTH, oldFile.getFileLength());
5f0dfa61 src/com/owncloud/android/datamodel/FileDataStorageManager.java (masensio          2013-07-11 18:57:35 +0200 259)                     file.setFileLength(oldFile.getFileLength());
5f0dfa61 src/com/owncloud/android/datamodel/FileDataStorageManager.java (masensio          2013-07-11 18:57:35 +0200 260)                 }
5f0dfa61 src/com/owncloud/android/datamodel/FileDataStorageManager.java (masensio          2013-07-11 18:57:35 +0200 261)                 
eee74aa5 src/com/owncloud/android/datamodel/FileDataStorageManager.java (masensio          2013-07-04 18:47:38 +0200 262)                 operations.add(ContentProviderOperation.newUpdate(ProviderTableMeta.CONTENT_URI).
eee74aa5 src/com/owncloud/android/datamodel/FileDataStorageManager.java (masensio          2013-07-04 18:47:38 +0200 263)                         withValues(cv).
eee74aa5 src/com/owncloud/android/datamodel/FileDataStorageManager.java (masensio          2013-07-04 18:47:38 +0200 264)                         withSelection(  ProviderTableMeta._ID + "=?", 
eee74aa5 src/com/owncloud/android/datamodel/FileDataStorageManager.java (masensio          2013-07-04 18:47:38 +0200 265)                                 new String[] { String.valueOf(file.getFileId()) })
eee74aa5 src/com/owncloud/android/datamodel/FileDataStorageManager.java (masensio          2013-07-04 18:47:38 +0200 266)                                 .build());
1baa27a4 src/com/owncloud/android/datamodel/FileDataStorageManager.java (David A. Velasco  2012-11-06 13:35:08 +0100 267) 
d4f8391f src/eu/alefzero/owncloud/datamodel/FileDataStorageManager.java (David A. Velasco  2012-07-11 13:51:57 +0200 268)             } else {
d4f8391f src/eu/alefzero/owncloud/datamodel/FileDataStorageManager.java (David A. Velasco  2012-07-11 13:51:57 +0200 269)                 operations.add(ContentProviderOperation.newInsert(ProviderTableMeta.CONTENT_URI).withValues(cv).build());
d4f8391f src/eu/alefzero/owncloud/datamodel/FileDataStorageManager.java (David A. Velasco  2012-07-11 13:51:57 +0200 270)             }
d4f8391f src/eu/alefzero/owncloud/datamodel/FileDataStorageManager.java (David A. Velasco  2012-07-11 13:51:57 +0200 271)         }
eee74aa5 src/com/owncloud/android/datamodel/FileDataStorageManager.java (masensio          2013-07-04 18:47:38 +0200 272) 
d4f8391f src/eu/alefzero/owncloud/datamodel/FileDataStorageManager.java (David A. Velasco  2012-07-11 13:51:57 +0200 273)         // apply operations in batch
d4f8391f src/eu/alefzero/owncloud/datamodel/FileDataStorageManager.java (David A. Velasco  2012-07-11 13:51:57 +0200 274)         ContentProviderResult[] results = null;
d4f8391f src/eu/alefzero/owncloud/datamodel/FileDataStorageManager.java (David A. Velasco  2012-07-11 13:51:57 +0200 275)         try {
d4f8391f src/eu/alefzero/owncloud/datamodel/FileDataStorageManager.java (David A. Velasco  2012-07-11 13:51:57 +0200 276)             if (getContentResolver() != null) {
d4f8391f src/eu/alefzero/owncloud/datamodel/FileDataStorageManager.java (David A. Velasco  2012-07-11 13:51:57 +0200 277)                 results = getContentResolver().applyBatch(ProviderMeta.AUTHORITY_FILES, operations);
eee74aa5 src/com/owncloud/android/datamodel/FileDataStorageManager.java (masensio          2013-07-04 18:47:38 +0200 278) 
d4f8391f src/eu/alefzero/owncloud/datamodel/FileDataStorageManager.java (David A. Velasco  2012-07-11 13:51:57 +0200 279)             } else {
d4f8391f src/eu/alefzero/owncloud/datamodel/FileDataStorageManager.java (David A. Velasco  2012-07-11 13:51:57 +0200 280)                 results = getContentProvider().applyBatch(operations);
d4f8391f src/eu/alefzero/owncloud/datamodel/FileDataStorageManager.java (David A. Velasco  2012-07-11 13:51:57 +0200 281)             }
eee74aa5 src/com/owncloud/android/datamodel/FileDataStorageManager.java (masensio          2013-07-04 18:47:38 +0200 282) 
d4f8391f src/eu/alefzero/owncloud/datamodel/FileDataStorageManager.java (David A. Velasco  2012-07-11 13:51:57 +0200 283)         } catch (OperationApplicationException e) {
8e36e7cc src/com/owncloud/android/datamodel/FileDataStorageManager.java (zerginator        2013-03-12 07:56:27 +0100 284)             Log_OC.e(TAG, "Fail to update/insert list of files to database " + e.getMessage());
eee74aa5 src/com/owncloud/android/datamodel/FileDataStorageManager.java (masensio          2013-07-04 18:47:38 +0200 285) 
d4f8391f src/eu/alefzero/owncloud/datamodel/FileDataStorageManager.java (David A. Velasco  2012-07-11 13:51:57 +0200 286)         } catch (RemoteException e) {
8e36e7cc src/com/owncloud/android/datamodel/FileDataStorageManager.java (zerginator        2013-03-12 07:56:27 +0100 287)             Log_OC.e(TAG, "Fail to update/insert list of files to database " + e.getMessage());
d4f8391f src/eu/alefzero/owncloud/datamodel/FileDataStorageManager.java (David A. Velasco  2012-07-11 13:51:57 +0200 288)         }
eee74aa5 src/com/owncloud/android/datamodel/FileDataStorageManager.java (masensio          2013-07-04 18:47:38 +0200 289) 
d4f8391f src/eu/alefzero/owncloud/datamodel/FileDataStorageManager.java (David A. Velasco  2012-07-11 13:51:57 +0200 290)         // update new id in file objects for insertions
d4f8391f src/eu/alefzero/owncloud/datamodel/FileDataStorageManager.java (David A. Velasco  2012-07-11 13:51:57 +0200 291)         if (results != null) {
d4f8391f src/eu/alefzero/owncloud/datamodel/FileDataStorageManager.java (David A. Velasco  2012-07-11 13:51:57 +0200 292)             long newId;
d4f8391f src/eu/alefzero/owncloud/datamodel/FileDataStorageManager.java (David A. Velasco  2012-07-11 13:51:57 +0200 293)             for (int i=0; i<results.length; i++) {
d4f8391f src/eu/alefzero/owncloud/datamodel/FileDataStorageManager.java (David A. Velasco  2012-07-11 13:51:57 +0200 294)                 if (results[i].uri != null) {
d4f8391f src/eu/alefzero/owncloud/datamodel/FileDataStorageManager.java (David A. Velasco  2012-07-11 13:51:57 +0200 295)                     newId = Long.parseLong(results[i].uri.getPathSegments().get(1));
d4f8391f src/eu/alefzero/owncloud/datamodel/FileDataStorageManager.java (David A. Velasco  2012-07-11 13:51:57 +0200 296)                     files.get(i).setFileId(newId);
8e36e7cc src/com/owncloud/android/datamodel/FileDataStorageManager.java (zerginator        2013-03-12 07:56:27 +0100 297)                     //Log_OC.v(TAG, "Found and added id in insertion for " + files.get(i).getRemotePath());
d4f8391f src/eu/alefzero/owncloud/datamodel/FileDataStorageManager.java (David A. Velasco  2012-07-11 13:51:57 +0200 298)                 }
d4f8391f src/eu/alefzero/owncloud/datamodel/FileDataStorageManager.java (David A. Velasco  2012-07-11 13:51:57 +0200 299)             }
d4f8391f src/eu/alefzero/owncloud/datamodel/FileDataStorageManager.java (David A. Velasco  2012-07-11 13:51:57 +0200 300)         }
d4f8391f src/eu/alefzero/owncloud/datamodel/FileDataStorageManager.java (David A. Velasco  2012-07-11 13:51:57 +0200 301) 
d4f8391f src/eu/alefzero/owncloud/datamodel/FileDataStorageManager.java (David A. Velasco  2012-07-11 13:51:57 +0200 302)         for (OCFile aFile : files) {
d4f8391f src/eu/alefzero/owncloud/datamodel/FileDataStorageManager.java (David A. Velasco  2012-07-11 13:51:57 +0200 303)             if (aFile.isDirectory() && aFile.needsUpdatingWhileSaving())
d4f8391f src/eu/alefzero/owncloud/datamodel/FileDataStorageManager.java (David A. Velasco  2012-07-11 13:51:57 +0200 304)                 saveFiles(getDirectoryContent(aFile));
d4f8391f src/eu/alefzero/owncloud/datamodel/FileDataStorageManager.java (David A. Velasco  2012-07-11 13:51:57 +0200 305)         }
d4f8391f src/eu/alefzero/owncloud/datamodel/FileDataStorageManager.java (David A. Velasco  2012-07-11 13:51:57 +0200 306) 
d4f8391f src/eu/alefzero/owncloud/datamodel/FileDataStorageManager.java (David A. Velasco  2012-07-11 13:51:57 +0200 307)     }
eee74aa5 src/com/owncloud/android/datamodel/FileDataStorageManager.java (masensio          2013-07-04 18:47:38 +0200 308) 
435b31ba src/eu/alefzero/owncloud/datamodel/FileDataStorageManager.java (Lennart Rosam     2012-05-16 09:48:34 +0200 309)     public void setAccount(Account account) {
435b31ba src/eu/alefzero/owncloud/datamodel/FileDataStorageManager.java (Lennart Rosam     2012-05-16 09:48:34 +0200 310)         mAccount = account;
b7ca8521 src/eu/alefzero/owncloud/datamodel/FileDataStorageManager.java (Bartek Przybylski 2012-04-14 22:28:55 +0200 311)     }
435b31ba src/eu/alefzero/owncloud/datamodel/FileDataStorageManager.java (Lennart Rosam     2012-05-16 09:48:34 +0200 312) 
435b31ba src/eu/alefzero/owncloud/datamodel/FileDataStorageManager.java (Lennart Rosam     2012-05-16 09:48:34 +0200 313)     public Account getAccount() {
435b31ba src/eu/alefzero/owncloud/datamodel/FileDataStorageManager.java (Lennart Rosam     2012-05-16 09:48:34 +0200 314)         return mAccount;
b7ca8521 src/eu/alefzero/owncloud/datamodel/FileDataStorageManager.java (Bartek Przybylski 2012-04-14 22:28:55 +0200 315)     }
435b31ba src/eu/alefzero/owncloud/datamodel/FileDataStorageManager.java (Lennart Rosam     2012-05-16 09:48:34 +0200 316) 
435b31ba src/eu/alefzero/owncloud/datamodel/FileDataStorageManager.java (Lennart Rosam     2012-05-16 09:48:34 +0200 317)     public void setContentResolver(ContentResolver cr) {
435b31ba src/eu/alefzero/owncloud/datamodel/FileDataStorageManager.java (Lennart Rosam     2012-05-16 09:48:34 +0200 318)         mContentResolver = cr;
435b31ba src/eu/alefzero/owncloud/datamodel/FileDataStorageManager.java (Lennart Rosam     2012-05-16 09:48:34 +0200 319)     }
435b31ba src/eu/alefzero/owncloud/datamodel/FileDataStorageManager.java (Lennart Rosam     2012-05-16 09:48:34 +0200 320) 
435b31ba src/eu/alefzero/owncloud/datamodel/FileDataStorageManager.java (Lennart Rosam     2012-05-16 09:48:34 +0200 321)     public ContentResolver getContentResolver() {
435b31ba src/eu/alefzero/owncloud/datamodel/FileDataStorageManager.java (Lennart Rosam     2012-05-16 09:48:34 +0200 322)         return mContentResolver;
b7ca8521 src/eu/alefzero/owncloud/datamodel/FileDataStorageManager.java (Bartek Przybylski 2012-04-14 22:28:55 +0200 323)     }
435b31ba src/eu/alefzero/owncloud/datamodel/FileDataStorageManager.java (Lennart Rosam     2012-05-16 09:48:34 +0200 324) 
435b31ba src/eu/alefzero/owncloud/datamodel/FileDataStorageManager.java (Lennart Rosam     2012-05-16 09:48:34 +0200 325)     public void setContentProvider(ContentProviderClient cp) {
435b31ba src/eu/alefzero/owncloud/datamodel/FileDataStorageManager.java (Lennart Rosam     2012-05-16 09:48:34 +0200 326)         mContentProvider = cp;
435b31ba src/eu/alefzero/owncloud/datamodel/FileDataStorageManager.java (Lennart Rosam     2012-05-16 09:48:34 +0200 327)     }
435b31ba src/eu/alefzero/owncloud/datamodel/FileDataStorageManager.java (Lennart Rosam     2012-05-16 09:48:34 +0200 328) 
435b31ba src/eu/alefzero/owncloud/datamodel/FileDataStorageManager.java (Lennart Rosam     2012-05-16 09:48:34 +0200 329)     public ContentProviderClient getContentProvider() {
435b31ba src/eu/alefzero/owncloud/datamodel/FileDataStorageManager.java (Lennart Rosam     2012-05-16 09:48:34 +0200 330)         return mContentProvider;
435b31ba src/eu/alefzero/owncloud/datamodel/FileDataStorageManager.java (Lennart Rosam     2012-05-16 09:48:34 +0200 331)     }
7a7fe5db src/com/owncloud/android/datamodel/FileDataStorageManager.java (David A. Velasco  2013-07-09 12:32:44 +0200 332)     
981bf054 src/com/owncloud/android/datamodel/FileDataStorageManager.java (Bartek Przybylski 2012-09-29 23:55:19 +0200 333)     @Override
435b31ba src/eu/alefzero/owncloud/datamodel/FileDataStorageManager.java (Lennart Rosam     2012-05-16 09:48:34 +0200 334)     public Vector<OCFile> getDirectoryContent(OCFile f) {
435b31ba src/eu/alefzero/owncloud/datamodel/FileDataStorageManager.java (Lennart Rosam     2012-05-16 09:48:34 +0200 335)         if (f != null && f.isDirectory() && f.getFileId() != -1) {
7a7fe5db src/com/owncloud/android/datamodel/FileDataStorageManager.java (David A. Velasco  2013-07-09 12:32:44 +0200 336)             return getDirectoryContent(f.getFileId());
435b31ba src/eu/alefzero/owncloud/datamodel/FileDataStorageManager.java (Lennart Rosam     2012-05-16 09:48:34 +0200 337) 
7a7fe5db src/com/owncloud/android/datamodel/FileDataStorageManager.java (David A. Velasco  2013-07-09 12:32:44 +0200 338)         } else {
7a7fe5db src/com/owncloud/android/datamodel/FileDataStorageManager.java (David A. Velasco  2013-07-09 12:32:44 +0200 339)             return new Vector<OCFile>();
7a7fe5db src/com/owncloud/android/datamodel/FileDataStorageManager.java (David A. Velasco  2013-07-09 12:32:44 +0200 340)         }
7a7fe5db src/com/owncloud/android/datamodel/FileDataStorageManager.java (David A. Velasco  2013-07-09 12:32:44 +0200 341)     }
435b31ba src/eu/alefzero/owncloud/datamodel/FileDataStorageManager.java (Lennart Rosam     2012-05-16 09:48:34 +0200 342) 
7a7fe5db src/com/owncloud/android/datamodel/FileDataStorageManager.java (David A. Velasco  2013-07-09 12:32:44 +0200 343)     private Vector<OCFile> getDirectoryContent(long parentId) {
435b31ba src/eu/alefzero/owncloud/datamodel/FileDataStorageManager.java (Lennart Rosam     2012-05-16 09:48:34 +0200 344) 
7a7fe5db src/com/owncloud/android/datamodel/FileDataStorageManager.java (David A. Velasco  2013-07-09 12:32:44 +0200 345)         Vector<OCFile> ret = new Vector<OCFile>();
435b31ba src/eu/alefzero/owncloud/datamodel/FileDataStorageManager.java (Lennart Rosam     2012-05-16 09:48:34 +0200 346) 
7a7fe5db src/com/owncloud/android/datamodel/FileDataStorageManager.java (David A. Velasco  2013-07-09 12:32:44 +0200 347)         Uri req_uri = Uri.withAppendedPath(
7a7fe5db src/com/owncloud/android/datamodel/FileDataStorageManager.java (David A. Velasco  2013-07-09 12:32:44 +0200 348)                 ProviderTableMeta.CONTENT_URI_DIR,
7a7fe5db src/com/owncloud/android/datamodel/FileDataStorageManager.java (David A. Velasco  2013-07-09 12:32:44 +0200 349)                 String.valueOf(parentId));
7a7fe5db src/com/owncloud/android/datamodel/FileDataStorageManager.java (David A. Velasco  2013-07-09 12:32:44 +0200 350)         Cursor c = null;
eee74aa5 src/com/owncloud/android/datamodel/FileDataStorageManager.java (masensio          2013-07-04 18:47:38 +0200 351) 
7a7fe5db src/com/owncloud/android/datamodel/FileDataStorageManager.java (David A. Velasco  2013-07-09 12:32:44 +0200 352)         if (getContentProvider() != null) {
7a7fe5db src/com/owncloud/android/datamodel/FileDataStorageManager.java (David A. Velasco  2013-07-09 12:32:44 +0200 353)             try {
7a7fe5db src/com/owncloud/android/datamodel/FileDataStorageManager.java (David A. Velasco  2013-07-09 12:32:44 +0200 354)                 c = getContentProvider().query(req_uri, null, 
7a7fe5db src/com/owncloud/android/datamodel/FileDataStorageManager.java (David A. Velasco  2013-07-09 12:32:44 +0200 355)                         ProviderTableMeta.FILE_PARENT + "=?" ,
7a7fe5db src/com/owncloud/android/datamodel/FileDataStorageManager.java (David A. Velasco  2013-07-09 12:32:44 +0200 356)                         new String[] { String.valueOf(parentId)}, null);
7a7fe5db src/com/owncloud/android/datamodel/FileDataStorageManager.java (David A. Velasco  2013-07-09 12:32:44 +0200 357)             } catch (RemoteException e) {
7a7fe5db src/com/owncloud/android/datamodel/FileDataStorageManager.java (David A. Velasco  2013-07-09 12:32:44 +0200 358)                 Log_OC.e(TAG, e.getMessage());
7a7fe5db src/com/owncloud/android/datamodel/FileDataStorageManager.java (David A. Velasco  2013-07-09 12:32:44 +0200 359)                 return ret;
7a7fe5db src/com/owncloud/android/datamodel/FileDataStorageManager.java (David A. Velasco  2013-07-09 12:32:44 +0200 360)             }
7a7fe5db src/com/owncloud/android/datamodel/FileDataStorageManager.java (David A. Velasco  2013-07-09 12:32:44 +0200 361)         } else {
7a7fe5db src/com/owncloud/android/datamodel/FileDataStorageManager.java (David A. Velasco  2013-07-09 12:32:44 +0200 362)             c = getContentResolver().query(req_uri, null, 
7a7fe5db src/com/owncloud/android/datamodel/FileDataStorageManager.java (David A. Velasco  2013-07-09 12:32:44 +0200 363)                     ProviderTableMeta.FILE_PARENT + "=?" ,
7a7fe5db src/com/owncloud/android/datamodel/FileDataStorageManager.java (David A. Velasco  2013-07-09 12:32:44 +0200 364)                     new String[] { String.valueOf(parentId)}, null);
7a7fe5db src/com/owncloud/android/datamodel/FileDataStorageManager.java (David A. Velasco  2013-07-09 12:32:44 +0200 365)         }
eee74aa5 src/com/owncloud/android/datamodel/FileDataStorageManager.java (masensio          2013-07-04 18:47:38 +0200 366) 
7a7fe5db src/com/owncloud/android/datamodel/FileDataStorageManager.java (David A. Velasco  2013-07-09 12:32:44 +0200 367)         if (c.moveToFirst()) {
7a7fe5db src/com/owncloud/android/datamodel/FileDataStorageManager.java (David A. Velasco  2013-07-09 12:32:44 +0200 368)             do {
7a7fe5db src/com/owncloud/android/datamodel/FileDataStorageManager.java (David A. Velasco  2013-07-09 12:32:44 +0200 369)                 OCFile child = createFileInstance(c);
7a7fe5db src/com/owncloud/android/datamodel/FileDataStorageManager.java (David A. Velasco  2013-07-09 12:32:44 +0200 370)                 ret.add(child);
7a7fe5db src/com/owncloud/android/datamodel/FileDataStorageManager.java (David A. Velasco  2013-07-09 12:32:44 +0200 371)             } while (c.moveToNext());
435b31ba src/eu/alefzero/owncloud/datamodel/FileDataStorageManager.java (Lennart Rosam     2012-05-16 09:48:34 +0200 372)         }
7a7fe5db src/com/owncloud/android/datamodel/FileDataStorageManager.java (David A. Velasco  2013-07-09 12:32:44 +0200 373) 
7a7fe5db src/com/owncloud/android/datamodel/FileDataStorageManager.java (David A. Velasco  2013-07-09 12:32:44 +0200 374)         c.close();
7a7fe5db src/com/owncloud/android/datamodel/FileDataStorageManager.java (David A. Velasco  2013-07-09 12:32:44 +0200 375) 
7a7fe5db src/com/owncloud/android/datamodel/FileDataStorageManager.java (David A. Velasco  2013-07-09 12:32:44 +0200 376)         Collections.sort(ret);
7a7fe5db src/com/owncloud/android/datamodel/FileDataStorageManager.java (David A. Velasco  2013-07-09 12:32:44 +0200 377) 
e81a7790 src/com/owncloud/android/datamodel/FileDataStorageManager.java (David A. Velasco  2012-11-30 13:28:00 +0100 378)         return ret;
435b31ba src/eu/alefzero/owncloud/datamodel/FileDataStorageManager.java (Lennart Rosam     2012-05-16 09:48:34 +0200 379)     }
7a7fe5db src/com/owncloud/android/datamodel/FileDataStorageManager.java (David A. Velasco  2013-07-09 12:32:44 +0200 380)     
7a7fe5db src/com/owncloud/android/datamodel/FileDataStorageManager.java (David A. Velasco  2013-07-09 12:32:44 +0200 381)     
435b31ba src/eu/alefzero/owncloud/datamodel/FileDataStorageManager.java (Lennart Rosam     2012-05-16 09:48:34 +0200 382) 
435b31ba src/eu/alefzero/owncloud/datamodel/FileDataStorageManager.java (Lennart Rosam     2012-05-16 09:48:34 +0200 383)     private boolean fileExists(String cmp_key, String value) {
435b31ba src/eu/alefzero/owncloud/datamodel/FileDataStorageManager.java (Lennart Rosam     2012-05-16 09:48:34 +0200 384)         Cursor c;
435b31ba src/eu/alefzero/owncloud/datamodel/FileDataStorageManager.java (Lennart Rosam     2012-05-16 09:48:34 +0200 385)         if (getContentResolver() != null) {
435b31ba src/eu/alefzero/owncloud/datamodel/FileDataStorageManager.java (Lennart Rosam     2012-05-16 09:48:34 +0200 386)             c = getContentResolver()
435b31ba src/eu/alefzero/owncloud/datamodel/FileDataStorageManager.java (Lennart Rosam     2012-05-16 09:48:34 +0200 387)                     .query(ProviderTableMeta.CONTENT_URI,
435b31ba src/eu/alefzero/owncloud/datamodel/FileDataStorageManager.java (Lennart Rosam     2012-05-16 09:48:34 +0200 388)                             null,
435b31ba src/eu/alefzero/owncloud/datamodel/FileDataStorageManager.java (Lennart Rosam     2012-05-16 09:48:34 +0200 389)                             cmp_key + "=? AND "
435b31ba src/eu/alefzero/owncloud/datamodel/FileDataStorageManager.java (Lennart Rosam     2012-05-16 09:48:34 +0200 390)                                     + ProviderTableMeta.FILE_ACCOUNT_OWNER
435b31ba src/eu/alefzero/owncloud/datamodel/FileDataStorageManager.java (Lennart Rosam     2012-05-16 09:48:34 +0200 391)                                     + "=?",
eee74aa5 src/com/owncloud/android/datamodel/FileDataStorageManager.java (masensio          2013-07-04 18:47:38 +0200 392)                                     new String[] { value, mAccount.name }, null);
435b31ba src/eu/alefzero/owncloud/datamodel/FileDataStorageManager.java (Lennart Rosam     2012-05-16 09:48:34 +0200 393)         } else {
435b31ba src/eu/alefzero/owncloud/datamodel/FileDataStorageManager.java (Lennart Rosam     2012-05-16 09:48:34 +0200 394)             try {
435b31ba src/eu/alefzero/owncloud/datamodel/FileDataStorageManager.java (Lennart Rosam     2012-05-16 09:48:34 +0200 395)                 c = getContentProvider().query(
435b31ba src/eu/alefzero/owncloud/datamodel/FileDataStorageManager.java (Lennart Rosam     2012-05-16 09:48:34 +0200 396)                         ProviderTableMeta.CONTENT_URI,
435b31ba src/eu/alefzero/owncloud/datamodel/FileDataStorageManager.java (Lennart Rosam     2012-05-16 09:48:34 +0200 397)                         null,
435b31ba src/eu/alefzero/owncloud/datamodel/FileDataStorageManager.java (Lennart Rosam     2012-05-16 09:48:34 +0200 398)                         cmp_key + "=? AND "
435b31ba src/eu/alefzero/owncloud/datamodel/FileDataStorageManager.java (Lennart Rosam     2012-05-16 09:48:34 +0200 399)                                 + ProviderTableMeta.FILE_ACCOUNT_OWNER + "=?",
eee74aa5 src/com/owncloud/android/datamodel/FileDataStorageManager.java (masensio          2013-07-04 18:47:38 +0200 400)                                 new String[] { value, mAccount.name }, null);
435b31ba src/eu/alefzero/owncloud/datamodel/FileDataStorageManager.java (Lennart Rosam     2012-05-16 09:48:34 +0200 401)             } catch (RemoteException e) {
8e36e7cc src/com/owncloud/android/datamodel/FileDataStorageManager.java (zerginator        2013-03-12 07:56:27 +0100 402)                 Log_OC.e(TAG,
435b31ba src/eu/alefzero/owncloud/datamodel/FileDataStorageManager.java (Lennart Rosam     2012-05-16 09:48:34 +0200 403)                         "Couldn't determine file existance, assuming non existance: "
435b31ba src/eu/alefzero/owncloud/datamodel/FileDataStorageManager.java (Lennart Rosam     2012-05-16 09:48:34 +0200 404)                                 + e.getMessage());
435b31ba src/eu/alefzero/owncloud/datamodel/FileDataStorageManager.java (Lennart Rosam     2012-05-16 09:48:34 +0200 405)                 return false;
435b31ba src/eu/alefzero/owncloud/datamodel/FileDataStorageManager.java (Lennart Rosam     2012-05-16 09:48:34 +0200 406)             }
435b31ba src/eu/alefzero/owncloud/datamodel/FileDataStorageManager.java (Lennart Rosam     2012-05-16 09:48:34 +0200 407)         }
435b31ba src/eu/alefzero/owncloud/datamodel/FileDataStorageManager.java (Lennart Rosam     2012-05-16 09:48:34 +0200 408)         boolean retval = c.moveToFirst();
435b31ba src/eu/alefzero/owncloud/datamodel/FileDataStorageManager.java (Lennart Rosam     2012-05-16 09:48:34 +0200 409)         c.close();
435b31ba src/eu/alefzero/owncloud/datamodel/FileDataStorageManager.java (Lennart Rosam     2012-05-16 09:48:34 +0200 410)         return retval;
435b31ba src/eu/alefzero/owncloud/datamodel/FileDataStorageManager.java (Lennart Rosam     2012-05-16 09:48:34 +0200 411)     }
435b31ba src/eu/alefzero/owncloud/datamodel/FileDataStorageManager.java (Lennart Rosam     2012-05-16 09:48:34 +0200 412) 
435b31ba src/eu/alefzero/owncloud/datamodel/FileDataStorageManager.java (Lennart Rosam     2012-05-16 09:48:34 +0200 413)     private Cursor getCursorForValue(String key, String value) {
435b31ba src/eu/alefzero/owncloud/datamodel/FileDataStorageManager.java (Lennart Rosam     2012-05-16 09:48:34 +0200 414)         Cursor c = null;
435b31ba src/eu/alefzero/owncloud/datamodel/FileDataStorageManager.java (Lennart Rosam     2012-05-16 09:48:34 +0200 415)         if (getContentResolver() != null) {
435b31ba src/eu/alefzero/owncloud/datamodel/FileDataStorageManager.java (Lennart Rosam     2012-05-16 09:48:34 +0200 416)             c = getContentResolver()
435b31ba src/eu/alefzero/owncloud/datamodel/FileDataStorageManager.java (Lennart Rosam     2012-05-16 09:48:34 +0200 417)                     .query(ProviderTableMeta.CONTENT_URI,
435b31ba src/eu/alefzero/owncloud/datamodel/FileDataStorageManager.java (Lennart Rosam     2012-05-16 09:48:34 +0200 418)                             null,
435b31ba src/eu/alefzero/owncloud/datamodel/FileDataStorageManager.java (Lennart Rosam     2012-05-16 09:48:34 +0200 419)                             key + "=? AND "
435b31ba src/eu/alefzero/owncloud/datamodel/FileDataStorageManager.java (Lennart Rosam     2012-05-16 09:48:34 +0200 420)                                     + ProviderTableMeta.FILE_ACCOUNT_OWNER
435b31ba src/eu/alefzero/owncloud/datamodel/FileDataStorageManager.java (Lennart Rosam     2012-05-16 09:48:34 +0200 421)                                     + "=?",
eee74aa5 src/com/owncloud/android/datamodel/FileDataStorageManager.java (masensio          2013-07-04 18:47:38 +0200 422)                                     new String[] { value, mAccount.name }, null);
435b31ba src/eu/alefzero/owncloud/datamodel/FileDataStorageManager.java (Lennart Rosam     2012-05-16 09:48:34 +0200 423)         } else {
435b31ba src/eu/alefzero/owncloud/datamodel/FileDataStorageManager.java (Lennart Rosam     2012-05-16 09:48:34 +0200 424)             try {
435b31ba src/eu/alefzero/owncloud/datamodel/FileDataStorageManager.java (Lennart Rosam     2012-05-16 09:48:34 +0200 425)                 c = getContentProvider().query(
435b31ba src/eu/alefzero/owncloud/datamodel/FileDataStorageManager.java (Lennart Rosam     2012-05-16 09:48:34 +0200 426)                         ProviderTableMeta.CONTENT_URI,
435b31ba src/eu/alefzero/owncloud/datamodel/FileDataStorageManager.java (Lennart Rosam     2012-05-16 09:48:34 +0200 427)                         null,
435b31ba src/eu/alefzero/owncloud/datamodel/FileDataStorageManager.java (Lennart Rosam     2012-05-16 09:48:34 +0200 428)                         key + "=? AND " + ProviderTableMeta.FILE_ACCOUNT_OWNER
eee74aa5 src/com/owncloud/android/datamodel/FileDataStorageManager.java (masensio          2013-07-04 18:47:38 +0200 429)                         + "=?", new String[] { value, mAccount.name },
435b31ba src/eu/alefzero/owncloud/datamodel/FileDataStorageManager.java (Lennart Rosam     2012-05-16 09:48:34 +0200 430)                         null);
435b31ba src/eu/alefzero/owncloud/datamodel/FileDataStorageManager.java (Lennart Rosam     2012-05-16 09:48:34 +0200 431)             } catch (RemoteException e) {
8e36e7cc src/com/owncloud/android/datamodel/FileDataStorageManager.java (zerginator        2013-03-12 07:56:27 +0100 432)                 Log_OC.e(TAG, "Could not get file details: " + e.getMessage());
435b31ba src/eu/alefzero/owncloud/datamodel/FileDataStorageManager.java (Lennart Rosam     2012-05-16 09:48:34 +0200 433)                 c = null;
435b31ba src/eu/alefzero/owncloud/datamodel/FileDataStorageManager.java (Lennart Rosam     2012-05-16 09:48:34 +0200 434)             }
435b31ba src/eu/alefzero/owncloud/datamodel/FileDataStorageManager.java (Lennart Rosam     2012-05-16 09:48:34 +0200 435)         }
435b31ba src/eu/alefzero/owncloud/datamodel/FileDataStorageManager.java (Lennart Rosam     2012-05-16 09:48:34 +0200 436)         return c;
435b31ba src/eu/alefzero/owncloud/datamodel/FileDataStorageManager.java (Lennart Rosam     2012-05-16 09:48:34 +0200 437)     }
435b31ba src/eu/alefzero/owncloud/datamodel/FileDataStorageManager.java (Lennart Rosam     2012-05-16 09:48:34 +0200 438) 
435b31ba src/eu/alefzero/owncloud/datamodel/FileDataStorageManager.java (Lennart Rosam     2012-05-16 09:48:34 +0200 439)     private OCFile createFileInstance(Cursor c) {
435b31ba src/eu/alefzero/owncloud/datamodel/FileDataStorageManager.java (Lennart Rosam     2012-05-16 09:48:34 +0200 440)         OCFile file = null;
435b31ba src/eu/alefzero/owncloud/datamodel/FileDataStorageManager.java (Lennart Rosam     2012-05-16 09:48:34 +0200 441)         if (c != null) {
435b31ba src/eu/alefzero/owncloud/datamodel/FileDataStorageManager.java (Lennart Rosam     2012-05-16 09:48:34 +0200 442)             file = new OCFile(c.getString(c
435b31ba src/eu/alefzero/owncloud/datamodel/FileDataStorageManager.java (Lennart Rosam     2012-05-16 09:48:34 +0200 443)                     .getColumnIndex(ProviderTableMeta.FILE_PATH)));
435b31ba src/eu/alefzero/owncloud/datamodel/FileDataStorageManager.java (Lennart Rosam     2012-05-16 09:48:34 +0200 444)             file.setFileId(c.getLong(c.getColumnIndex(ProviderTableMeta._ID)));
435b31ba src/eu/alefzero/owncloud/datamodel/FileDataStorageManager.java (Lennart Rosam     2012-05-16 09:48:34 +0200 445)             file.setParentId(c.getLong(c
435b31ba src/eu/alefzero/owncloud/datamodel/FileDataStorageManager.java (Lennart Rosam     2012-05-16 09:48:34 +0200 446)                     .getColumnIndex(ProviderTableMeta.FILE_PARENT)));
435b31ba src/eu/alefzero/owncloud/datamodel/FileDataStorageManager.java (Lennart Rosam     2012-05-16 09:48:34 +0200 447)             file.setMimetype(c.getString(c
435b31ba src/eu/alefzero/owncloud/datamodel/FileDataStorageManager.java (Lennart Rosam     2012-05-16 09:48:34 +0200 448)                     .getColumnIndex(ProviderTableMeta.FILE_CONTENT_TYPE)));
35ace0b9 src/eu/alefzero/owncloud/datamodel/FileDataStorageManager.java (Bartek Przybylski 2012-05-27 00:03:43 +0200 449)             if (!file.isDirectory()) {
35ace0b9 src/eu/alefzero/owncloud/datamodel/FileDataStorageManager.java (Bartek Przybylski 2012-05-27 00:03:43 +0200 450)                 file.setStoragePath(c.getString(c
35ace0b9 src/eu/alefzero/owncloud/datamodel/FileDataStorageManager.java (Bartek Przybylski 2012-05-27 00:03:43 +0200 451)                         .getColumnIndex(ProviderTableMeta.FILE_STORAGE_PATH)));
35ace0b9 src/eu/alefzero/owncloud/datamodel/FileDataStorageManager.java (Bartek Przybylski 2012-05-27 00:03:43 +0200 452)                 if (file.getStoragePath() == null) {
22a789e8 src/com/owncloud/android/datamodel/FileDataStorageManager.java (David A. Velasco  2012-11-19 15:08:48 +0100 453)                     // try to find existing file and bind it with current account; - with the current update of SynchronizeFolderOperation, this won't be necessary anymore after a full synchronization of the account
9aab2d26 src/com/owncloud/android/datamodel/FileDataStorageManager.java (David A. Velasco  2012-11-12 15:20:00 +0100 454)                     File f = new File(FileStorageUtils.getDefaultSavePathFor(mAccount.name, file));
22a789e8 src/com/owncloud/android/datamodel/FileDataStorageManager.java (David A. Velasco  2012-11-19 15:08:48 +0100 455)                     if (f.exists()) {
35ace0b9 src/eu/alefzero/owncloud/datamodel/FileDataStorageManager.java (Bartek Przybylski 2012-05-27 00:03:43 +0200 456)                         file.setStoragePath(f.getAbsolutePath());
22a789e8 src/com/owncloud/android/datamodel/FileDataStorageManager.java (David A. Velasco  2012-11-19 15:08:48 +0100 457)                         file.setLastSyncDateForData(f.lastModified());
22a789e8 src/com/owncloud/android/datamodel/FileDataStorageManager.java (David A. Velasco  2012-11-19 15:08:48 +0100 458)                     }
35ace0b9 src/eu/alefzero/owncloud/datamodel/FileDataStorageManager.java (Bartek Przybylski 2012-05-27 00:03:43 +0200 459)                 }
35ace0b9 src/eu/alefzero/owncloud/datamodel/FileDataStorageManager.java (Bartek Przybylski 2012-05-27 00:03:43 +0200 460)             }
435b31ba src/eu/alefzero/owncloud/datamodel/FileDataStorageManager.java (Lennart Rosam     2012-05-16 09:48:34 +0200 461)             file.setFileLength(c.getLong(c
435b31ba src/eu/alefzero/owncloud/datamodel/FileDataStorageManager.java (Lennart Rosam     2012-05-16 09:48:34 +0200 462)                     .getColumnIndex(ProviderTableMeta.FILE_CONTENT_LENGTH)));
435b31ba src/eu/alefzero/owncloud/datamodel/FileDataStorageManager.java (Lennart Rosam     2012-05-16 09:48:34 +0200 463)             file.setCreationTimestamp(c.getLong(c
435b31ba src/eu/alefzero/owncloud/datamodel/FileDataStorageManager.java (Lennart Rosam     2012-05-16 09:48:34 +0200 464)                     .getColumnIndex(ProviderTableMeta.FILE_CREATION)));
435b31ba src/eu/alefzero/owncloud/datamodel/FileDataStorageManager.java (Lennart Rosam     2012-05-16 09:48:34 +0200 465)             file.setModificationTimestamp(c.getLong(c
435b31ba src/eu/alefzero/owncloud/datamodel/FileDataStorageManager.java (Lennart Rosam     2012-05-16 09:48:34 +0200 466)                     .getColumnIndex(ProviderTableMeta.FILE_MODIFIED)));
abd5f515 src/com/owncloud/android/datamodel/FileDataStorageManager.java (David A. Velasco  2012-12-05 16:14:01 +0100 467)             file.setModificationTimestampAtLastSyncForData(c.getLong(c
abd5f515 src/com/owncloud/android/datamodel/FileDataStorageManager.java (David A. Velasco  2012-12-05 16:14:01 +0100 468)                     .getColumnIndex(ProviderTableMeta.FILE_MODIFIED_AT_LAST_SYNC_FOR_DATA)));
ff2271a8 src/com/owncloud/android/datamodel/FileDataStorageManager.java (David A. Velasco  2012-11-15 17:18:04 +0100 469)             file.setLastSyncDateForProperties(c.getLong(c
8d7a0291 src/eu/alefzero/owncloud/datamodel/FileDataStorageManager.java (Bartek Przybylski 2012-05-19 19:07:54 +0200 470)                     .getColumnIndex(ProviderTableMeta.FILE_LAST_SYNC_DATE)));
ff2271a8 src/com/owncloud/android/datamodel/FileDataStorageManager.java (David A. Velasco  2012-11-15 17:18:04 +0100 471)             file.setLastSyncDateForData(c.getLong(c.
ff2271a8 src/com/owncloud/android/datamodel/FileDataStorageManager.java (David A. Velasco  2012-11-15 17:18:04 +0100 472)                     getColumnIndex(ProviderTableMeta.FILE_LAST_SYNC_DATE_FOR_DATA)));
3113c459 src/eu/alefzero/owncloud/datamodel/FileDataStorageManager.java (Bartek Przybylski 2012-07-08 16:57:48 +0200 473)             file.setKeepInSync(c.getInt(
eee74aa5 src/com/owncloud/android/datamodel/FileDataStorageManager.java (masensio          2013-07-04 18:47:38 +0200 474)                     c.getColumnIndex(ProviderTableMeta.FILE_KEEP_IN_SYNC)) == 1 ? true : false);
435b31ba src/eu/alefzero/owncloud/datamodel/FileDataStorageManager.java (Lennart Rosam     2012-05-16 09:48:34 +0200 475)         }
435b31ba src/eu/alefzero/owncloud/datamodel/FileDataStorageManager.java (Lennart Rosam     2012-05-16 09:48:34 +0200 476)         return file;
b7ca8521 src/eu/alefzero/owncloud/datamodel/FileDataStorageManager.java (Bartek Przybylski 2012-04-14 22:28:55 +0200 477)     }
eee74aa5 src/com/owncloud/android/datamodel/FileDataStorageManager.java (masensio          2013-07-04 18:47:38 +0200 478) 
981bf054 src/com/owncloud/android/datamodel/FileDataStorageManager.java (Bartek Przybylski 2012-09-29 23:55:19 +0200 479)     @Override
938c7846 src/com/owncloud/android/datamodel/FileDataStorageManager.java (David A. Velasco  2012-08-22 12:50:06 +0200 480)     public void removeFile(OCFile file, boolean removeLocalCopy) {
8d7a0291 src/eu/alefzero/owncloud/datamodel/FileDataStorageManager.java (Bartek Przybylski 2012-05-19 19:07:54 +0200 481)         Uri file_uri = Uri.withAppendedPath(ProviderTableMeta.CONTENT_URI_FILE, ""+file.getFileId());
65523409 src/eu/alefzero/owncloud/datamodel/FileDataStorageManager.java (Bartek Przybylski 2012-05-19 18:30:03 +0200 482)         if (getContentProvider() != null) {
65523409 src/eu/alefzero/owncloud/datamodel/FileDataStorageManager.java (Bartek Przybylski 2012-05-19 18:30:03 +0200 483)             try {
8d7a0291 src/eu/alefzero/owncloud/datamodel/FileDataStorageManager.java (Bartek Przybylski 2012-05-19 19:07:54 +0200 484)                 getContentProvider().delete(file_uri,
eee74aa5 src/com/owncloud/android/datamodel/FileDataStorageManager.java (masensio          2013-07-04 18:47:38 +0200 485)                         ProviderTableMeta.FILE_ACCOUNT_OWNER+"=?",
eee74aa5 src/com/owncloud/android/datamodel/FileDataStorageManager.java (masensio          2013-07-04 18:47:38 +0200 486)                         new String[]{mAccount.name});
65523409 src/eu/alefzero/owncloud/datamodel/FileDataStorageManager.java (Bartek Przybylski 2012-05-19 18:30:03 +0200 487)             } catch (RemoteException e) {
65523409 src/eu/alefzero/owncloud/datamodel/FileDataStorageManager.java (Bartek Przybylski 2012-05-19 18:30:03 +0200 488)                 e.printStackTrace();
65523409 src/eu/alefzero/owncloud/datamodel/FileDataStorageManager.java (Bartek Przybylski 2012-05-19 18:30:03 +0200 489)             }
65523409 src/eu/alefzero/owncloud/datamodel/FileDataStorageManager.java (Bartek Przybylski 2012-05-19 18:30:03 +0200 490)         } else {
8d7a0291 src/eu/alefzero/owncloud/datamodel/FileDataStorageManager.java (Bartek Przybylski 2012-05-19 19:07:54 +0200 491)             getContentResolver().delete(file_uri,
eee74aa5 src/com/owncloud/android/datamodel/FileDataStorageManager.java (masensio          2013-07-04 18:47:38 +0200 492)                     ProviderTableMeta.FILE_ACCOUNT_OWNER+"=?",
eee74aa5 src/com/owncloud/android/datamodel/FileDataStorageManager.java (masensio          2013-07-04 18:47:38 +0200 493)                     new String[]{mAccount.name});
65523409 src/eu/alefzero/owncloud/datamodel/FileDataStorageManager.java (Bartek Przybylski 2012-05-19 18:30:03 +0200 494)         }
938c7846 src/com/owncloud/android/datamodel/FileDataStorageManager.java (David A. Velasco  2012-08-22 12:50:06 +0200 495)         if (file.isDown() && removeLocalCopy) {
49ad2498 src/eu/alefzero/owncloud/datamodel/FileDataStorageManager.java (Bartek Przybylski 2012-05-23 22:40:31 +0200 496)             new File(file.getStoragePath()).delete();
49ad2498 src/eu/alefzero/owncloud/datamodel/FileDataStorageManager.java (Bartek Przybylski 2012-05-23 22:40:31 +0200 497)         }
b27ebf03 src/com/owncloud/android/datamodel/FileDataStorageManager.java (David A. Velasco  2012-11-05 15:44:09 +0100 498)         if (file.isDirectory() && removeLocalCopy) {
4c50eb4d src/com/owncloud/android/datamodel/FileDataStorageManager.java (David A. Velasco  2012-11-23 10:25:06 +0100 499)             File f = new File(FileStorageUtils.getDefaultSavePathFor(mAccount.name, file));
b27ebf03 src/com/owncloud/android/datamodel/FileDataStorageManager.java (David A. Velasco  2012-11-05 15:44:09 +0100 500)             if (f.exists() && f.isDirectory() && (f.list() == null || f.list().length == 0)) {
b27ebf03 src/com/owncloud/android/datamodel/FileDataStorageManager.java (David A. Velasco  2012-11-05 15:44:09 +0100 501)                 f.delete();
b27ebf03 src/com/owncloud/android/datamodel/FileDataStorageManager.java (David A. Velasco  2012-11-05 15:44:09 +0100 502)             }
b27ebf03 src/com/owncloud/android/datamodel/FileDataStorageManager.java (David A. Velasco  2012-11-05 15:44:09 +0100 503)         }
7ec25767 src/com/owncloud/android/datamodel/FileDataStorageManager.java (masensio          2013-07-05 13:00:57 +0200 504)         
7a7fe5db src/com/owncloud/android/datamodel/FileDataStorageManager.java (David A. Velasco  2013-07-09 12:32:44 +0200 505)         if (file.getFileLength() > 0) {
7a7fe5db src/com/owncloud/android/datamodel/FileDataStorageManager.java (David A. Velasco  2013-07-09 12:32:44 +0200 506)             updateSizesToTheRoot(file.getParentId());
7a7fe5db src/com/owncloud/android/datamodel/FileDataStorageManager.java (David A. Velasco  2013-07-09 12:32:44 +0200 507)         }
b27ebf03 src/com/owncloud/android/datamodel/FileDataStorageManager.java (David A. Velasco  2012-11-05 15:44:09 +0100 508)     }
b27ebf03 src/com/owncloud/android/datamodel/FileDataStorageManager.java (David A. Velasco  2012-11-05 15:44:09 +0100 509) 
b27ebf03 src/com/owncloud/android/datamodel/FileDataStorageManager.java (David A. Velasco  2012-11-05 15:44:09 +0100 510)     @Override
b27ebf03 src/com/owncloud/android/datamodel/FileDataStorageManager.java (David A. Velasco  2012-11-05 15:44:09 +0100 511)     public void removeDirectory(OCFile dir, boolean removeDBData, boolean removeLocalContent) {
b27ebf03 src/com/owncloud/android/datamodel/FileDataStorageManager.java (David A. Velasco  2012-11-05 15:44:09 +0100 512)         // TODO consider possible failures
b27ebf03 src/com/owncloud/android/datamodel/FileDataStorageManager.java (David A. Velasco  2012-11-05 15:44:09 +0100 513)         if (dir != null && dir.isDirectory() && dir.getFileId() != -1) {
b27ebf03 src/com/owncloud/android/datamodel/FileDataStorageManager.java (David A. Velasco  2012-11-05 15:44:09 +0100 514)             Vector<OCFile> children = getDirectoryContent(dir);
b59784af src/com/owncloud/android/datamodel/FileDataStorageManager.java (David A. Velasco  2012-11-30 13:33:23 +0100 515)             if (children.size() > 0) {
b27ebf03 src/com/owncloud/android/datamodel/FileDataStorageManager.java (David A. Velasco  2012-11-05 15:44:09 +0100 516)                 OCFile child = null;
b27ebf03 src/com/owncloud/android/datamodel/FileDataStorageManager.java (David A. Velasco  2012-11-05 15:44:09 +0100 517)                 for (int i=0; i<children.size(); i++) {
b27ebf03 src/com/owncloud/android/datamodel/FileDataStorageManager.java (David A. Velasco  2012-11-05 15:44:09 +0100 518)                     child = children.get(i);
b27ebf03 src/com/owncloud/android/datamodel/FileDataStorageManager.java (David A. Velasco  2012-11-05 15:44:09 +0100 519)                     if (child.isDirectory()) {
b27ebf03 src/com/owncloud/android/datamodel/FileDataStorageManager.java (David A. Velasco  2012-11-05 15:44:09 +0100 520)                         removeDirectory(child, removeDBData, removeLocalContent);
b27ebf03 src/com/owncloud/android/datamodel/FileDataStorageManager.java (David A. Velasco  2012-11-05 15:44:09 +0100 521)                     } else {
b27ebf03 src/com/owncloud/android/datamodel/FileDataStorageManager.java (David A. Velasco  2012-11-05 15:44:09 +0100 522)                         if (removeDBData) {
b27ebf03 src/com/owncloud/android/datamodel/FileDataStorageManager.java (David A. Velasco  2012-11-05 15:44:09 +0100 523)                             removeFile(child, removeLocalContent);
b27ebf03 src/com/owncloud/android/datamodel/FileDataStorageManager.java (David A. Velasco  2012-11-05 15:44:09 +0100 524)                         } else if (removeLocalContent) {
b27ebf03 src/com/owncloud/android/datamodel/FileDataStorageManager.java (David A. Velasco  2012-11-05 15:44:09 +0100 525)                             if (child.isDown()) {
b27ebf03 src/com/owncloud/android/datamodel/FileDataStorageManager.java (David A. Velasco  2012-11-05 15:44:09 +0100 526)                                 new File(child.getStoragePath()).delete();
b27ebf03 src/com/owncloud/android/datamodel/FileDataStorageManager.java (David A. Velasco  2012-11-05 15:44:09 +0100 527)                             }
b27ebf03 src/com/owncloud/android/datamodel/FileDataStorageManager.java (David A. Velasco  2012-11-05 15:44:09 +0100 528)                         }
b27ebf03 src/com/owncloud/android/datamodel/FileDataStorageManager.java (David A. Velasco  2012-11-05 15:44:09 +0100 529)                     }
b27ebf03 src/com/owncloud/android/datamodel/FileDataStorageManager.java (David A. Velasco  2012-11-05 15:44:09 +0100 530)                 }
840c842f src/com/owncloud/android/datamodel/FileDataStorageManager.java (David A. Velasco  2013-01-10 16:27:05 +0100 531)             }
840c842f src/com/owncloud/android/datamodel/FileDataStorageManager.java (David A. Velasco  2013-01-10 16:27:05 +0100 532)             if (removeDBData) {
840c842f src/com/owncloud/android/datamodel/FileDataStorageManager.java (David A. Velasco  2013-01-10 16:27:05 +0100 533)                 removeFile(dir, true);
b27ebf03 src/com/owncloud/android/datamodel/FileDataStorageManager.java (David A. Velasco  2012-11-05 15:44:09 +0100 534)             }
a72d7bfe src/com/owncloud/android/datamodel/FileDataStorageManager.java (masensio          2013-07-05 14:54:02 +0200 535)             
7a7fe5db src/com/owncloud/android/datamodel/FileDataStorageManager.java (David A. Velasco  2013-07-09 12:32:44 +0200 536)             if (dir.getFileLength() > 0) {
7a7fe5db src/com/owncloud/android/datamodel/FileDataStorageManager.java (David A. Velasco  2013-07-09 12:32:44 +0200 537)                 updateSizesToTheRoot(dir.getParentId());
7a7fe5db src/com/owncloud/android/datamodel/FileDataStorageManager.java (David A. Velasco  2013-07-09 12:32:44 +0200 538)             }
b27ebf03 src/com/owncloud/android/datamodel/FileDataStorageManager.java (David A. Velasco  2012-11-05 15:44:09 +0100 539)         }
65523409 src/eu/alefzero/owncloud/datamodel/FileDataStorageManager.java (Bartek Przybylski 2012-05-19 18:30:03 +0200 540)     }
eee74aa5 src/com/owncloud/android/datamodel/FileDataStorageManager.java (masensio          2013-07-04 18:47:38 +0200 541) 
eee74aa5 src/com/owncloud/android/datamodel/FileDataStorageManager.java (masensio          2013-07-04 18:47:38 +0200 542) 
d0b7df16 src/com/owncloud/android/datamodel/FileDataStorageManager.java (David A. Velasco  2012-11-21 14:32:40 +0100 543)     /**
d0b7df16 src/com/owncloud/android/datamodel/FileDataStorageManager.java (David A. Velasco  2012-11-21 14:32:40 +0100 544)      * Updates database for a folder that was moved to a different location.
d0b7df16 src/com/owncloud/android/datamodel/FileDataStorageManager.java (David A. Velasco  2012-11-21 14:32:40 +0100 545)      * 
d0b7df16 src/com/owncloud/android/datamodel/FileDataStorageManager.java (David A. Velasco  2012-11-21 14:32:40 +0100 546)      * TODO explore better (faster) implementations
d0b7df16 src/com/owncloud/android/datamodel/FileDataStorageManager.java (David A. Velasco  2012-11-21 14:32:40 +0100 547)      * TODO throw exceptions up !
d0b7df16 src/com/owncloud/android/datamodel/FileDataStorageManager.java (David A. Velasco  2012-11-21 14:32:40 +0100 548)      */
d0b7df16 src/com/owncloud/android/datamodel/FileDataStorageManager.java (David A. Velasco  2012-11-21 14:32:40 +0100 549)     @Override
d0b7df16 src/com/owncloud/android/datamodel/FileDataStorageManager.java (David A. Velasco  2012-11-21 14:32:40 +0100 550)     public void moveDirectory(OCFile dir, String newPath) {
d0b7df16 src/com/owncloud/android/datamodel/FileDataStorageManager.java (David A. Velasco  2012-11-21 14:32:40 +0100 551)         // TODO check newPath
eee74aa5 src/com/owncloud/android/datamodel/FileDataStorageManager.java (masensio          2013-07-04 18:47:38 +0200 552) 
d0b7df16 src/com/owncloud/android/datamodel/FileDataStorageManager.java (David A. Velasco  2012-11-21 14:32:40 +0100 553)         if (dir != null && dir.isDirectory() && dir.fileExists() && !dir.getFileName().equals(OCFile.PATH_SEPARATOR)) {
d0b7df16 src/com/owncloud/android/datamodel/FileDataStorageManager.java (David A. Velasco  2012-11-21 14:32:40 +0100 554)             /// 1. get all the descendants of 'dir' in a single QUERY (including 'dir')
d0b7df16 src/com/owncloud/android/datamodel/FileDataStorageManager.java (David A. Velasco  2012-11-21 14:32:40 +0100 555)             Cursor c = null;
d0b7df16 src/com/owncloud/android/datamodel/FileDataStorageManager.java (David A. Velasco  2012-11-21 14:32:40 +0100 556)             if (getContentProvider() != null) {
d0b7df16 src/com/owncloud/android/datamodel/FileDataStorageManager.java (David A. Velasco  2012-11-21 14:32:40 +0100 557)                 try {
d0b7df16 src/com/owncloud/android/datamodel/FileDataStorageManager.java (David A. Velasco  2012-11-21 14:32:40 +0100 558)                     c = getContentProvider().query(ProviderTableMeta.CONTENT_URI, 
eee74aa5 src/com/owncloud/android/datamodel/FileDataStorageManager.java (masensio          2013-07-04 18:47:38 +0200 559)                             null,
68d4c74f src/com/owncloud/android/datamodel/FileDataStorageManager.java (masensio          2013-09-16 10:54:20 +0200 560)                             ProviderTableMeta.FILE_ACCOUNT_OWNER + "=? AND " + ProviderTableMeta.FILE_PATH + " LIKE ? ",
68d4c74f src/com/owncloud/android/datamodel/FileDataStorageManager.java (masensio          2013-09-16 10:54:20 +0200 561)                             new String[] { mAccount.name, dir.getRemotePath() + "%"  }, ProviderTableMeta.FILE_PATH + " ASC ");
d0b7df16 src/com/owncloud/android/datamodel/FileDataStorageManager.java (David A. Velasco  2012-11-21 14:32:40 +0100 562)                 } catch (RemoteException e) {
8e36e7cc src/com/owncloud/android/datamodel/FileDataStorageManager.java (zerginator        2013-03-12 07:56:27 +0100 563)                     Log_OC.e(TAG, e.getMessage());
d0b7df16 src/com/owncloud/android/datamodel/FileDataStorageManager.java (David A. Velasco  2012-11-21 14:32:40 +0100 564)                 }
d0b7df16 src/com/owncloud/android/datamodel/FileDataStorageManager.java (David A. Velasco  2012-11-21 14:32:40 +0100 565)             } else {
d0b7df16 src/com/owncloud/android/datamodel/FileDataStorageManager.java (David A. Velasco  2012-11-21 14:32:40 +0100 566)                 c = getContentResolver().query(ProviderTableMeta.CONTENT_URI, 
eee74aa5 src/com/owncloud/android/datamodel/FileDataStorageManager.java (masensio          2013-07-04 18:47:38 +0200 567)                         null,
68d4c74f src/com/owncloud/android/datamodel/FileDataStorageManager.java (masensio          2013-09-16 10:54:20 +0200 568)                         ProviderTableMeta.FILE_ACCOUNT_OWNER + "=? AND " + ProviderTableMeta.FILE_PATH + " LIKE ? ",
68d4c74f src/com/owncloud/android/datamodel/FileDataStorageManager.java (masensio          2013-09-16 10:54:20 +0200 569)                         new String[] { mAccount.name, dir.getRemotePath() + "%"  }, ProviderTableMeta.FILE_PATH + " ASC ");
d0b7df16 src/com/owncloud/android/datamodel/FileDataStorageManager.java (David A. Velasco  2012-11-21 14:32:40 +0100 570)             }
d0b7df16 src/com/owncloud/android/datamodel/FileDataStorageManager.java (David A. Velasco  2012-11-21 14:32:40 +0100 571) 
d0b7df16 src/com/owncloud/android/datamodel/FileDataStorageManager.java (David A. Velasco  2012-11-21 14:32:40 +0100 572)             /// 2. prepare a batch of update operations to change all the descendants
d0b7df16 src/com/owncloud/android/datamodel/FileDataStorageManager.java (David A. Velasco  2012-11-21 14:32:40 +0100 573)             ArrayList<ContentProviderOperation> operations = new ArrayList<ContentProviderOperation>(c.getCount());
d0b7df16 src/com/owncloud/android/datamodel/FileDataStorageManager.java (David A. Velasco  2012-11-21 14:32:40 +0100 574)             int lengthOfOldPath = dir.getRemotePath().length();
4c50eb4d src/com/owncloud/android/datamodel/FileDataStorageManager.java (David A. Velasco  2012-11-23 10:25:06 +0100 575)             String defaultSavePath = FileStorageUtils.getSavePath(mAccount.name);
d0b7df16 src/com/owncloud/android/datamodel/FileDataStorageManager.java (David A. Velasco  2012-11-21 14:32:40 +0100 576)             int lengthOfOldStoragePath = defaultSavePath.length() + lengthOfOldPath;
d0b7df16 src/com/owncloud/android/datamodel/FileDataStorageManager.java (David A. Velasco  2012-11-21 14:32:40 +0100 577)             if (c.moveToFirst()) {
d0b7df16 src/com/owncloud/android/datamodel/FileDataStorageManager.java (David A. Velasco  2012-11-21 14:32:40 +0100 578)                 do {
ccb79617 src/com/owncloud/android/datamodel/FileDataStorageManager.java (David A. Velasco  2012-11-21 16:28:20 +0100 579)                     ContentValues cv = new ContentValues(); // don't take the constructor out of the loop and clear the object
d0b7df16 src/com/owncloud/android/datamodel/FileDataStorageManager.java (David A. Velasco  2012-11-21 14:32:40 +0100 580)                     OCFile child = createFileInstance(c);
d0b7df16 src/com/owncloud/android/datamodel/FileDataStorageManager.java (David A. Velasco  2012-11-21 14:32:40 +0100 581)                     cv.put(ProviderTableMeta.FILE_PATH, newPath + child.getRemotePath().substring(lengthOfOldPath));
d0b7df16 src/com/owncloud/android/datamodel/FileDataStorageManager.java (David A. Velasco  2012-11-21 14:32:40 +0100 582)                     if (child.getStoragePath() != null && child.getStoragePath().startsWith(defaultSavePath)) {
d0b7df16 src/com/owncloud/android/datamodel/FileDataStorageManager.java (David A. Velasco  2012-11-21 14:32:40 +0100 583)                         cv.put(ProviderTableMeta.FILE_STORAGE_PATH, defaultSavePath + newPath + child.getStoragePath().substring(lengthOfOldStoragePath));
d0b7df16 src/com/owncloud/android/datamodel/FileDataStorageManager.java (David A. Velasco  2012-11-21 14:32:40 +0100 584)                     }
d0b7df16 src/com/owncloud/android/datamodel/FileDataStorageManager.java (David A. Velasco  2012-11-21 14:32:40 +0100 585)                     operations.add(ContentProviderOperation.newUpdate(ProviderTableMeta.CONTENT_URI).
eee74aa5 src/com/owncloud/android/datamodel/FileDataStorageManager.java (masensio          2013-07-04 18:47:38 +0200 586)                             withValues(cv).
eee74aa5 src/com/owncloud/android/datamodel/FileDataStorageManager.java (masensio          2013-07-04 18:47:38 +0200 587)                             withSelection(  ProviderTableMeta._ID + "=?", 
eee74aa5 src/com/owncloud/android/datamodel/FileDataStorageManager.java (masensio          2013-07-04 18:47:38 +0200 588)                                     new String[] { String.valueOf(child.getFileId()) })
eee74aa5 src/com/owncloud/android/datamodel/FileDataStorageManager.java (masensio          2013-07-04 18:47:38 +0200 589)                                     .build());
d0b7df16 src/com/owncloud/android/datamodel/FileDataStorageManager.java (David A. Velasco  2012-11-21 14:32:40 +0100 590)                 } while (c.moveToNext());
d0b7df16 src/com/owncloud/android/datamodel/FileDataStorageManager.java (David A. Velasco  2012-11-21 14:32:40 +0100 591)             }
d0b7df16 src/com/owncloud/android/datamodel/FileDataStorageManager.java (David A. Velasco  2012-11-21 14:32:40 +0100 592)             c.close();
eee74aa5 src/com/owncloud/android/datamodel/FileDataStorageManager.java (masensio          2013-07-04 18:47:38 +0200 593) 
d0b7df16 src/com/owncloud/android/datamodel/FileDataStorageManager.java (David A. Velasco  2012-11-21 14:32:40 +0100 594)             /// 3. apply updates in batch
d0b7df16 src/com/owncloud/android/datamodel/FileDataStorageManager.java (David A. Velasco  2012-11-21 14:32:40 +0100 595)             try {
d0b7df16 src/com/owncloud/android/datamodel/FileDataStorageManager.java (David A. Velasco  2012-11-21 14:32:40 +0100 596)                 if (getContentResolver() != null) {
d0b7df16 src/com/owncloud/android/datamodel/FileDataStorageManager.java (David A. Velasco  2012-11-21 14:32:40 +0100 597)                     getContentResolver().applyBatch(ProviderMeta.AUTHORITY_FILES, operations);
eee74aa5 src/com/owncloud/android/datamodel/FileDataStorageManager.java (masensio          2013-07-04 18:47:38 +0200 598) 
d0b7df16 src/com/owncloud/android/datamodel/FileDataStorageManager.java (David A. Velasco  2012-11-21 14:32:40 +0100 599)                 } else {
d0b7df16 src/com/owncloud/android/datamodel/FileDataStorageManager.java (David A. Velasco  2012-11-21 14:32:40 +0100 600)                     getContentProvider().applyBatch(operations);
d0b7df16 src/com/owncloud/android/datamodel/FileDataStorageManager.java (David A. Velasco  2012-11-21 14:32:40 +0100 601)                 }
eee74aa5 src/com/owncloud/android/datamodel/FileDataStorageManager.java (masensio          2013-07-04 18:47:38 +0200 602) 
d0b7df16 src/com/owncloud/android/datamodel/FileDataStorageManager.java (David A. Velasco  2012-11-21 14:32:40 +0100 603)             } catch (OperationApplicationException e) {
8e36e7cc src/com/owncloud/android/datamodel/FileDataStorageManager.java (zerginator        2013-03-12 07:56:27 +0100 604)                 Log_OC.e(TAG, "Fail to update descendants of " + dir.getFileId() + " in database", e);
eee74aa5 src/com/owncloud/android/datamodel/FileDataStorageManager.java (masensio          2013-07-04 18:47:38 +0200 605) 
d0b7df16 src/com/owncloud/android/datamodel/FileDataStorageManager.java (David A. Velasco  2012-11-21 14:32:40 +0100 606)             } catch (RemoteException e) {
8e36e7cc src/com/owncloud/android/datamodel/FileDataStorageManager.java (zerginator        2013-03-12 07:56:27 +0100 607)                 Log_OC.e(TAG, "Fail to update desendants of " + dir.getFileId() + " in database", e);
d0b7df16 src/com/owncloud/android/datamodel/FileDataStorageManager.java (David A. Velasco  2012-11-21 14:32:40 +0100 608)             }
eee74aa5 src/com/owncloud/android/datamodel/FileDataStorageManager.java (masensio          2013-07-04 18:47:38 +0200 609) 
d0b7df16 src/com/owncloud/android/datamodel/FileDataStorageManager.java (David A. Velasco  2012-11-21 14:32:40 +0100 610)         }
d0b7df16 src/com/owncloud/android/datamodel/FileDataStorageManager.java (David A. Velasco  2012-11-21 14:32:40 +0100 611)     }
b7ca8521 src/eu/alefzero/owncloud/datamodel/FileDataStorageManager.java (Bartek Przybylski 2012-04-14 22:28:55 +0200 612) 
d7e05192 src/com/owncloud/android/datamodel/FileDataStorageManager.java (David A. Velasco  2013-02-25 14:33:16 +0100 613)     @Override
d7e05192 src/com/owncloud/android/datamodel/FileDataStorageManager.java (David A. Velasco  2013-02-25 14:33:16 +0100 614)     public Vector<OCFile> getDirectoryImages(OCFile directory) {
d7e05192 src/com/owncloud/android/datamodel/FileDataStorageManager.java (David A. Velasco  2013-02-25 14:33:16 +0100 615)         Vector<OCFile> ret = new Vector<OCFile>(); 
d7e05192 src/com/owncloud/android/datamodel/FileDataStorageManager.java (David A. Velasco  2013-02-25 14:33:16 +0100 616)         if (directory != null) {
d7e05192 src/com/owncloud/android/datamodel/FileDataStorageManager.java (David A. Velasco  2013-02-25 14:33:16 +0100 617)             // TODO better implementation, filtering in the access to database (if possible) instead of here 
d7e05192 src/com/owncloud/android/datamodel/FileDataStorageManager.java (David A. Velasco  2013-02-25 14:33:16 +0100 618)             Vector<OCFile> tmp = getDirectoryContent(directory);
d7e05192 src/com/owncloud/android/datamodel/FileDataStorageManager.java (David A. Velasco  2013-02-25 14:33:16 +0100 619)             OCFile current = null; 
d7e05192 src/com/owncloud/android/datamodel/FileDataStorageManager.java (David A. Velasco  2013-02-25 14:33:16 +0100 620)             for (int i=0; i<tmp.size(); i++) {
d7e05192 src/com/owncloud/android/datamodel/FileDataStorageManager.java (David A. Velasco  2013-02-25 14:33:16 +0100 621)                 current = tmp.get(i);
d7e05192 src/com/owncloud/android/datamodel/FileDataStorageManager.java (David A. Velasco  2013-02-25 14:33:16 +0100 622)                 if (current.isImage()) {
d7e05192 src/com/owncloud/android/datamodel/FileDataStorageManager.java (David A. Velasco  2013-02-25 14:33:16 +0100 623)                     ret.add(current);
d7e05192 src/com/owncloud/android/datamodel/FileDataStorageManager.java (David A. Velasco  2013-02-25 14:33:16 +0100 624)                 }
d7e05192 src/com/owncloud/android/datamodel/FileDataStorageManager.java (David A. Velasco  2013-02-25 14:33:16 +0100 625)             }
d7e05192 src/com/owncloud/android/datamodel/FileDataStorageManager.java (David A. Velasco  2013-02-25 14:33:16 +0100 626)         }
d7e05192 src/com/owncloud/android/datamodel/FileDataStorageManager.java (David A. Velasco  2013-02-25 14:33:16 +0100 627)         return ret;
d7e05192 src/com/owncloud/android/datamodel/FileDataStorageManager.java (David A. Velasco  2013-02-25 14:33:16 +0100 628)     }
d7e05192 src/com/owncloud/android/datamodel/FileDataStorageManager.java (David A. Velasco  2013-02-25 14:33:16 +0100 629) 
7ec25767 src/com/owncloud/android/datamodel/FileDataStorageManager.java (masensio          2013-07-05 13:00:57 +0200 630)     /**
7ec25767 src/com/owncloud/android/datamodel/FileDataStorageManager.java (masensio          2013-07-05 13:00:57 +0200 631)      * Calculate and save the folderSize on DB
7ec25767 src/com/owncloud/android/datamodel/FileDataStorageManager.java (masensio          2013-07-05 13:00:57 +0200 632)      * @param id
7ec25767 src/com/owncloud/android/datamodel/FileDataStorageManager.java (masensio          2013-07-05 13:00:57 +0200 633)      */
7ec25767 src/com/owncloud/android/datamodel/FileDataStorageManager.java (masensio          2013-07-05 13:00:57 +0200 634)     @Override
7a7fe5db src/com/owncloud/android/datamodel/FileDataStorageManager.java (David A. Velasco  2013-07-09 12:32:44 +0200 635)     public void calculateFolderSize(long id) {
7ec25767 src/com/owncloud/android/datamodel/FileDataStorageManager.java (masensio          2013-07-05 13:00:57 +0200 636)         long folderSize = 0;
7ec25767 src/com/owncloud/android/datamodel/FileDataStorageManager.java (masensio          2013-07-05 13:00:57 +0200 637)         
7a7fe5db src/com/owncloud/android/datamodel/FileDataStorageManager.java (David A. Velasco  2013-07-09 12:32:44 +0200 638)         Vector<OCFile> files = getDirectoryContent(id);
7ec25767 src/com/owncloud/android/datamodel/FileDataStorageManager.java (masensio          2013-07-05 13:00:57 +0200 639)         
7ec25767 src/com/owncloud/android/datamodel/FileDataStorageManager.java (masensio          2013-07-05 13:00:57 +0200 640)         for (OCFile f: files)
7ec25767 src/com/owncloud/android/datamodel/FileDataStorageManager.java (masensio          2013-07-05 13:00:57 +0200 641)         {
7ec25767 src/com/owncloud/android/datamodel/FileDataStorageManager.java (masensio          2013-07-05 13:00:57 +0200 642)             folderSize = folderSize + f.getFileLength();
7ec25767 src/com/owncloud/android/datamodel/FileDataStorageManager.java (masensio          2013-07-05 13:00:57 +0200 643)         }
7ec25767 src/com/owncloud/android/datamodel/FileDataStorageManager.java (masensio          2013-07-05 13:00:57 +0200 644)         
7a7fe5db src/com/owncloud/android/datamodel/FileDataStorageManager.java (David A. Velasco  2013-07-09 12:32:44 +0200 645)         updateSize(id, folderSize);
7ec25767 src/com/owncloud/android/datamodel/FileDataStorageManager.java (masensio          2013-07-05 13:00:57 +0200 646)     }
eee74aa5 src/com/owncloud/android/datamodel/FileDataStorageManager.java (masensio          2013-07-04 18:47:38 +0200 647) 
eee74aa5 src/com/owncloud/android/datamodel/FileDataStorageManager.java (masensio          2013-07-04 18:47:38 +0200 648)     /**
7a7fe5db src/com/owncloud/android/datamodel/FileDataStorageManager.java (David A. Velasco  2013-07-09 12:32:44 +0200 649)      * Update the size value of an OCFile in DB
eee74aa5 src/com/owncloud/android/datamodel/FileDataStorageManager.java (masensio          2013-07-04 18:47:38 +0200 650)      */
7a7fe5db src/com/owncloud/android/datamodel/FileDataStorageManager.java (David A. Velasco  2013-07-09 12:32:44 +0200 651)     private int updateSize(long id, long size) {
eee74aa5 src/com/owncloud/android/datamodel/FileDataStorageManager.java (masensio          2013-07-04 18:47:38 +0200 652)         ContentValues cv = new ContentValues();
eee74aa5 src/com/owncloud/android/datamodel/FileDataStorageManager.java (masensio          2013-07-04 18:47:38 +0200 653)         cv.put(ProviderTableMeta.FILE_CONTENT_LENGTH, size);
7a7fe5db src/com/owncloud/android/datamodel/FileDataStorageManager.java (David A. Velasco  2013-07-09 12:32:44 +0200 654)         int result = -1;
7a7fe5db src/com/owncloud/android/datamodel/FileDataStorageManager.java (David A. Velasco  2013-07-09 12:32:44 +0200 655)         if (getContentResolver() != null) {
7a7fe5db src/com/owncloud/android/datamodel/FileDataStorageManager.java (David A. Velasco  2013-07-09 12:32:44 +0200 656)              result = getContentResolver().update(ProviderTableMeta.CONTENT_URI, cv, ProviderTableMeta._ID + "=?", 
7a7fe5db src/com/owncloud/android/datamodel/FileDataStorageManager.java (David A. Velasco  2013-07-09 12:32:44 +0200 657)                      new String[] { String.valueOf(id) });
7a7fe5db src/com/owncloud/android/datamodel/FileDataStorageManager.java (David A. Velasco  2013-07-09 12:32:44 +0200 658)         } else {
7a7fe5db src/com/owncloud/android/datamodel/FileDataStorageManager.java (David A. Velasco  2013-07-09 12:32:44 +0200 659)             try {
7a7fe5db src/com/owncloud/android/datamodel/FileDataStorageManager.java (David A. Velasco  2013-07-09 12:32:44 +0200 660)                 result = getContentProvider().update(ProviderTableMeta.CONTENT_URI, cv, ProviderTableMeta._ID + "=?", 
7a7fe5db src/com/owncloud/android/datamodel/FileDataStorageManager.java (David A. Velasco  2013-07-09 12:32:44 +0200 661)                         new String[] { String.valueOf(id) });
7a7fe5db src/com/owncloud/android/datamodel/FileDataStorageManager.java (David A. Velasco  2013-07-09 12:32:44 +0200 662)             } catch (RemoteException e) {
7a7fe5db src/com/owncloud/android/datamodel/FileDataStorageManager.java (David A. Velasco  2013-07-09 12:32:44 +0200 663)                 Log_OC.e(TAG,"Fail to update size column into database " + e.getMessage());
7a7fe5db src/com/owncloud/android/datamodel/FileDataStorageManager.java (David A. Velasco  2013-07-09 12:32:44 +0200 664)             }
7a7fe5db src/com/owncloud/android/datamodel/FileDataStorageManager.java (David A. Velasco  2013-07-09 12:32:44 +0200 665)         }
eee74aa5 src/com/owncloud/android/datamodel/FileDataStorageManager.java (masensio          2013-07-04 18:47:38 +0200 666)         return result;
eee74aa5 src/com/owncloud/android/datamodel/FileDataStorageManager.java (masensio          2013-07-04 18:47:38 +0200 667)     }
eee74aa5 src/com/owncloud/android/datamodel/FileDataStorageManager.java (masensio          2013-07-04 18:47:38 +0200 668) 
7ec25767 src/com/owncloud/android/datamodel/FileDataStorageManager.java (masensio          2013-07-05 13:00:57 +0200 669)     /** 
7ec25767 src/com/owncloud/android/datamodel/FileDataStorageManager.java (masensio          2013-07-05 13:00:57 +0200 670)      * Update the size of a subtree of folder from a file to the root
7ec25767 src/com/owncloud/android/datamodel/FileDataStorageManager.java (masensio          2013-07-05 13:00:57 +0200 671)      * @param parentId: parent of the file
7ec25767 src/com/owncloud/android/datamodel/FileDataStorageManager.java (masensio          2013-07-05 13:00:57 +0200 672)      */
7a7fe5db src/com/owncloud/android/datamodel/FileDataStorageManager.java (David A. Velasco  2013-07-09 12:32:44 +0200 673)     private void updateSizesToTheRoot(long parentId) {
7ec25767 src/com/owncloud/android/datamodel/FileDataStorageManager.java (masensio          2013-07-05 13:00:57 +0200 674)         
7ec25767 src/com/owncloud/android/datamodel/FileDataStorageManager.java (masensio          2013-07-05 13:00:57 +0200 675)         OCFile file; 
7ec25767 src/com/owncloud/android/datamodel/FileDataStorageManager.java (masensio          2013-07-05 13:00:57 +0200 676) 
7ec25767 src/com/owncloud/android/datamodel/FileDataStorageManager.java (masensio          2013-07-05 13:00:57 +0200 677)         while (parentId != 0) {
7ec25767 src/com/owncloud/android/datamodel/FileDataStorageManager.java (masensio          2013-07-05 13:00:57 +0200 678)             
7ec25767 src/com/owncloud/android/datamodel/FileDataStorageManager.java (masensio          2013-07-05 13:00:57 +0200 679)             Log_OC.d(TAG, "parent = " + parentId);
7ec25767 src/com/owncloud/android/datamodel/FileDataStorageManager.java (masensio          2013-07-05 13:00:57 +0200 680)             // Update the size of the parent
7a7fe5db src/com/owncloud/android/datamodel/FileDataStorageManager.java (David A. Velasco  2013-07-09 12:32:44 +0200 681)             calculateFolderSize(parentId);
7ec25767 src/com/owncloud/android/datamodel/FileDataStorageManager.java (masensio          2013-07-05 13:00:57 +0200 682)             
7ec25767 src/com/owncloud/android/datamodel/FileDataStorageManager.java (masensio          2013-07-05 13:00:57 +0200 683)             // search the next parent
7ec25767 src/com/owncloud/android/datamodel/FileDataStorageManager.java (masensio          2013-07-05 13:00:57 +0200 684)             file = getFileById(parentId);            
7ec25767 src/com/owncloud/android/datamodel/FileDataStorageManager.java (masensio          2013-07-05 13:00:57 +0200 685)             parentId = file.getParentId();
7ec25767 src/com/owncloud/android/datamodel/FileDataStorageManager.java (masensio          2013-07-05 13:00:57 +0200 686)             
7ec25767 src/com/owncloud/android/datamodel/FileDataStorageManager.java (masensio          2013-07-05 13:00:57 +0200 687)         }              
7ec25767 src/com/owncloud/android/datamodel/FileDataStorageManager.java (masensio          2013-07-05 13:00:57 +0200 688)         
7ec25767 src/com/owncloud/android/datamodel/FileDataStorageManager.java (masensio          2013-07-05 13:00:57 +0200 689)     }
eee74aa5 src/com/owncloud/android/datamodel/FileDataStorageManager.java (masensio          2013-07-04 18:47:38 +0200 690)     
b7ca8521 src/eu/alefzero/owncloud/datamodel/FileDataStorageManager.java (Bartek Przybylski 2012-04-14 22:28:55 +0200 691) }
