9dd2cf29 src/eu/alefzero/owncloud/files/PhotoTakenBroadcastReceiver.java    (Bartek Przybylski 2012-05-27 15:57:18 +0200   1) /* ownCloud Android client application
9dd2cf29 src/eu/alefzero/owncloud/files/PhotoTakenBroadcastReceiver.java    (Bartek Przybylski 2012-05-27 15:57:18 +0200   2)  *   Copyright (C) 2012  Bartek Przybylski
bb257ec7 src/com/owncloud/android/files/InstantUploadBroadcastReceiver.java (David A. Velasco  2013-02-07 18:45:10 +0100   3)  *   Copyright (C) 2012-2013 ownCloud Inc.
9dd2cf29 src/eu/alefzero/owncloud/files/PhotoTakenBroadcastReceiver.java    (Bartek Przybylski 2012-05-27 15:57:18 +0200   4)  *
9dd2cf29 src/eu/alefzero/owncloud/files/PhotoTakenBroadcastReceiver.java    (Bartek Przybylski 2012-05-27 15:57:18 +0200   5)  *   This program is free software: you can redistribute it and/or modify
bdc0332c src/com/owncloud/android/files/InstantUploadBroadcastReceiver.java (David A. Velasco  2013-04-17 12:26:13 +0200   6)  *   it under the terms of the GNU General Public License version 2,
bdc0332c src/com/owncloud/android/files/InstantUploadBroadcastReceiver.java (David A. Velasco  2013-04-17 12:26:13 +0200   7)  *   as published by the Free Software Foundation.
9dd2cf29 src/eu/alefzero/owncloud/files/PhotoTakenBroadcastReceiver.java    (Bartek Przybylski 2012-05-27 15:57:18 +0200   8)  *
9dd2cf29 src/eu/alefzero/owncloud/files/PhotoTakenBroadcastReceiver.java    (Bartek Przybylski 2012-05-27 15:57:18 +0200   9)  *   This program is distributed in the hope that it will be useful,
9dd2cf29 src/eu/alefzero/owncloud/files/PhotoTakenBroadcastReceiver.java    (Bartek Przybylski 2012-05-27 15:57:18 +0200  10)  *   but WITHOUT ANY WARRANTY; without even the implied warranty of
9dd2cf29 src/eu/alefzero/owncloud/files/PhotoTakenBroadcastReceiver.java    (Bartek Przybylski 2012-05-27 15:57:18 +0200  11)  *   MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
9dd2cf29 src/eu/alefzero/owncloud/files/PhotoTakenBroadcastReceiver.java    (Bartek Przybylski 2012-05-27 15:57:18 +0200  12)  *   GNU General Public License for more details.
9dd2cf29 src/eu/alefzero/owncloud/files/PhotoTakenBroadcastReceiver.java    (Bartek Przybylski 2012-05-27 15:57:18 +0200  13)  *
9dd2cf29 src/eu/alefzero/owncloud/files/PhotoTakenBroadcastReceiver.java    (Bartek Przybylski 2012-05-27 15:57:18 +0200  14)  *   You should have received a copy of the GNU General Public License
9dd2cf29 src/eu/alefzero/owncloud/files/PhotoTakenBroadcastReceiver.java    (Bartek Przybylski 2012-05-27 15:57:18 +0200  15)  *   along with this program.  If not, see <http://www.gnu.org/licenses/>.
9dd2cf29 src/eu/alefzero/owncloud/files/PhotoTakenBroadcastReceiver.java    (Bartek Przybylski 2012-05-27 15:57:18 +0200  16)  *
9dd2cf29 src/eu/alefzero/owncloud/files/PhotoTakenBroadcastReceiver.java    (Bartek Przybylski 2012-05-27 15:57:18 +0200  17)  */
9dd2cf29 src/eu/alefzero/owncloud/files/PhotoTakenBroadcastReceiver.java    (Bartek Przybylski 2012-05-27 15:57:18 +0200  18) 
a4ba6170 src/com/owncloud/android/files/PhotoTakenBroadcastReceiver.java    (Bartek Przybylski 2012-07-31 17:43:37 +0200  19) package com.owncloud.android.files;
9dd2cf29 src/eu/alefzero/owncloud/files/PhotoTakenBroadcastReceiver.java    (Bartek Przybylski 2012-05-27 15:57:18 +0200  20) 
2b00ff1c src/eu/alefzero/owncloud/files/PhotoTakenBroadcastReceiver.java    (Bartek Przybylski 2012-06-03 19:59:14 +0200  21) import java.io.File;
2b00ff1c src/eu/alefzero/owncloud/files/PhotoTakenBroadcastReceiver.java    (Bartek Przybylski 2012-06-03 19:59:14 +0200  22) 
69d6d821 src/com/owncloud/android/files/InstantUploadBroadcastReceiver.java (David A. Velasco  2013-03-21 12:21:48 +0100  23) import com.owncloud.android.authentication.AccountAuthenticator;
c4ac05de src/com/owncloud/android/files/InstantUploadBroadcastReceiver.java (David A. Velasco  2013-06-18 11:34:08 +0200  24) import com.owncloud.android.authentication.AccountUtils;
a4ba6170 src/com/owncloud/android/files/PhotoTakenBroadcastReceiver.java    (Bartek Przybylski 2012-07-31 17:43:37 +0200  25) import com.owncloud.android.db.DbHandler;
ceb3dfdd src/com/owncloud/android/files/PhotoTakenBroadcastReceiver.java    (David A. Velasco  2012-08-22 10:48:16 +0200  26) import com.owncloud.android.files.services.FileUploader;
a4ba6170 src/com/owncloud/android/files/PhotoTakenBroadcastReceiver.java    (Bartek Przybylski 2012-07-31 17:43:37 +0200  27) 
9dd2cf29 src/eu/alefzero/owncloud/files/PhotoTakenBroadcastReceiver.java    (Bartek Przybylski 2012-05-27 15:57:18 +0200  28) import android.accounts.Account;
9dd2cf29 src/eu/alefzero/owncloud/files/PhotoTakenBroadcastReceiver.java    (Bartek Przybylski 2012-05-27 15:57:18 +0200  29) import android.content.BroadcastReceiver;
9dd2cf29 src/eu/alefzero/owncloud/files/PhotoTakenBroadcastReceiver.java    (Bartek Przybylski 2012-05-27 15:57:18 +0200  30) import android.content.Context;
9dd2cf29 src/eu/alefzero/owncloud/files/PhotoTakenBroadcastReceiver.java    (Bartek Przybylski 2012-05-27 15:57:18 +0200  31) import android.content.Intent;
5bb706e8 src/com/owncloud/android/files/PhotoTakenBroadcastReceiver.java    (Bartek Przybylski 2012-10-09 22:53:41 +0200  32) import android.content.IntentFilter;
9dd2cf29 src/eu/alefzero/owncloud/files/PhotoTakenBroadcastReceiver.java    (Bartek Przybylski 2012-05-27 15:57:18 +0200  33) import android.database.Cursor;
2b00ff1c src/eu/alefzero/owncloud/files/PhotoTakenBroadcastReceiver.java    (Bartek Przybylski 2012-06-03 19:59:14 +0200  34) import android.net.ConnectivityManager;
671cb91c src/com/owncloud/android/files/PhotoTakenBroadcastReceiver.java    (Bartek Przybylski 2012-10-08 21:26:18 +0200  35) import android.net.NetworkInfo.State;
9dd2cf29 src/eu/alefzero/owncloud/files/PhotoTakenBroadcastReceiver.java    (Bartek Przybylski 2012-05-27 15:57:18 +0200  36) import android.preference.PreferenceManager;
9dd2cf29 src/eu/alefzero/owncloud/files/PhotoTakenBroadcastReceiver.java    (Bartek Przybylski 2012-05-27 15:57:18 +0200  37) import android.provider.MediaStore.Images.Media;
2b00ff1c src/eu/alefzero/owncloud/files/PhotoTakenBroadcastReceiver.java    (Bartek Przybylski 2012-06-03 19:59:14 +0200  38) import android.webkit.MimeTypeMap;
9dd2cf29 src/eu/alefzero/owncloud/files/PhotoTakenBroadcastReceiver.java    (Bartek Przybylski 2012-05-27 15:57:18 +0200  39) 
8e36e7cc src/com/owncloud/android/files/InstantUploadBroadcastReceiver.java (zerginator        2013-03-12 07:56:27 +0100  40) import com.owncloud.android.Log_OC;
7392cfe5 src/com/owncloud/android/files/InstantUploadBroadcastReceiver.java (Matthias Baumann  2013-03-16 18:56:27 +0100  41) import com.owncloud.android.utils.FileStorageUtils;
f7649804 src/com/owncloud/android/files/InstantUploadBroadcastReceiver.java (Matthias Baumann  2013-02-25 08:21:33 +0100  42) 
b6eb63a4 src/com/owncloud/android/files/InstantUploadBroadcastReceiver.java (Bartek Przybylski 2012-10-09 23:26:53 +0200  43) public class InstantUploadBroadcastReceiver extends BroadcastReceiver {
9dd2cf29 src/eu/alefzero/owncloud/files/PhotoTakenBroadcastReceiver.java    (Bartek Przybylski 2012-05-27 15:57:18 +0200  44) 
733206c5 src/com/owncloud/android/files/InstantUploadBroadcastReceiver.java (masensio          2013-08-07 12:10:00 +0200  45)     private static String TAG = "InstantUploadBroadcastReceiver";
f7649804 src/com/owncloud/android/files/InstantUploadBroadcastReceiver.java (Matthias Baumann  2013-02-25 08:21:33 +0100  46)     private static final String[] CONTENT_PROJECTION = { Media.DATA, Media.DISPLAY_NAME, Media.MIME_TYPE, Media.SIZE };
01cc2380 src/com/owncloud/android/files/InstantUploadBroadcastReceiver.java (Luke Owncloud     2013-08-05 16:34:21 +0200  47)     //Unofficial action, works for most devices but not HTC. See: https://github.com/owncloud/android/issues/6
733206c5 src/com/owncloud/android/files/InstantUploadBroadcastReceiver.java (masensio          2013-08-07 12:10:00 +0200  48)     private static String NEW_PHOTO_ACTION_UNOFFICIAL = "com.android.camera.NEW_PICTURE";
01cc2380 src/com/owncloud/android/files/InstantUploadBroadcastReceiver.java (Luke Owncloud     2013-08-05 16:34:21 +0200  49)     //Officially supported action since SDK 14: http://developer.android.com/reference/android/hardware/Camera.html#ACTION_NEW_PICTURE
01cc2380 src/com/owncloud/android/files/InstantUploadBroadcastReceiver.java (Luke Owncloud     2013-08-05 16:34:21 +0200  50)     private static String NEW_PHOTO_ACTION = "android.hardware.action.NEW_PICTURE";
f7649804 src/com/owncloud/android/files/InstantUploadBroadcastReceiver.java (Matthias Baumann  2013-02-25 08:21:33 +0100  51) 
9dd2cf29 src/eu/alefzero/owncloud/files/PhotoTakenBroadcastReceiver.java    (Bartek Przybylski 2012-05-27 15:57:18 +0200  52)     @Override
9dd2cf29 src/eu/alefzero/owncloud/files/PhotoTakenBroadcastReceiver.java    (Bartek Przybylski 2012-05-27 15:57:18 +0200  53)     public void onReceive(Context context, Intent intent) {
8e36e7cc src/com/owncloud/android/files/InstantUploadBroadcastReceiver.java (zerginator        2013-03-12 07:56:27 +0100  54)         Log_OC.d(TAG, "Received: " + intent.getAction());
2b00ff1c src/eu/alefzero/owncloud/files/PhotoTakenBroadcastReceiver.java    (Bartek Przybylski 2012-06-03 19:59:14 +0200  55)         if (intent.getAction().equals(android.net.ConnectivityManager.CONNECTIVITY_ACTION)) {
2b00ff1c src/eu/alefzero/owncloud/files/PhotoTakenBroadcastReceiver.java    (Bartek Przybylski 2012-06-03 19:59:14 +0200  56)             handleConnectivityAction(context, intent);
733206c5 src/com/owncloud/android/files/InstantUploadBroadcastReceiver.java (masensio          2013-08-07 12:10:00 +0200  57)         } else if (android.os.Build.VERSION.SDK_INT < android.os.Build.VERSION_CODES.ICE_CREAM_SANDWICH && 
733206c5 src/com/owncloud/android/files/InstantUploadBroadcastReceiver.java (masensio          2013-08-07 12:10:00 +0200  58)                 intent.getAction().equals(NEW_PHOTO_ACTION_UNOFFICIAL)) {
01cc2380 src/com/owncloud/android/files/InstantUploadBroadcastReceiver.java (Luke Owncloud     2013-08-05 16:34:21 +0200  59)             handleNewPhotoAction(context, intent);
733206c5 src/com/owncloud/android/files/InstantUploadBroadcastReceiver.java (masensio          2013-08-07 12:10:00 +0200  60)             Log_OC.d(TAG, "UNOFFICIAL processed: com.android.camera.NEW_PICTURE");
2b00ff1c src/eu/alefzero/owncloud/files/PhotoTakenBroadcastReceiver.java    (Bartek Przybylski 2012-06-03 19:59:14 +0200  61)         } else if (intent.getAction().equals(NEW_PHOTO_ACTION)) {
ceb3dfdd src/com/owncloud/android/files/PhotoTakenBroadcastReceiver.java    (David A. Velasco  2012-08-22 10:48:16 +0200  62)             handleNewPhotoAction(context, intent);
733206c5 src/com/owncloud/android/files/InstantUploadBroadcastReceiver.java (masensio          2013-08-07 12:10:00 +0200  63)             Log_OC.d(TAG, "OFFICIAL processed: android.hardware.action.NEW_PICTURE");
5bb706e8 src/com/owncloud/android/files/PhotoTakenBroadcastReceiver.java    (Bartek Przybylski 2012-10-09 22:53:41 +0200  64)         } else if (intent.getAction().equals(FileUploader.UPLOAD_FINISH_MESSAGE)) {
5bb706e8 src/com/owncloud/android/files/PhotoTakenBroadcastReceiver.java    (Bartek Przybylski 2012-10-09 22:53:41 +0200  65)             handleUploadFinished(context, intent);
2b00ff1c src/eu/alefzero/owncloud/files/PhotoTakenBroadcastReceiver.java    (Bartek Przybylski 2012-06-03 19:59:14 +0200  66)         } else {
8e36e7cc src/com/owncloud/android/files/InstantUploadBroadcastReceiver.java (zerginator        2013-03-12 07:56:27 +0100  67)             Log_OC.e(TAG, "Incorrect intent sent: " + intent.getAction());
9dd2cf29 src/eu/alefzero/owncloud/files/PhotoTakenBroadcastReceiver.java    (Bartek Przybylski 2012-05-27 15:57:18 +0200  68)         }
2b00ff1c src/eu/alefzero/owncloud/files/PhotoTakenBroadcastReceiver.java    (Bartek Przybylski 2012-06-03 19:59:14 +0200  69)     }
2b00ff1c src/eu/alefzero/owncloud/files/PhotoTakenBroadcastReceiver.java    (Bartek Przybylski 2012-06-03 19:59:14 +0200  70) 
5bb706e8 src/com/owncloud/android/files/PhotoTakenBroadcastReceiver.java    (Bartek Przybylski 2012-10-09 22:53:41 +0200  71)     private void handleUploadFinished(Context context, Intent intent) {
5bb706e8 src/com/owncloud/android/files/PhotoTakenBroadcastReceiver.java    (Bartek Przybylski 2012-10-09 22:53:41 +0200  72)         // remove successfull uploading, ignore rest for reupload on reconnect
5bb706e8 src/com/owncloud/android/files/PhotoTakenBroadcastReceiver.java    (Bartek Przybylski 2012-10-09 22:53:41 +0200  73)         if (intent.getBooleanExtra(FileUploader.EXTRA_UPLOAD_RESULT, false)) {
5bb706e8 src/com/owncloud/android/files/PhotoTakenBroadcastReceiver.java    (Bartek Przybylski 2012-10-09 22:53:41 +0200  74)             DbHandler db = new DbHandler(context);
80448dc1 src/com/owncloud/android/files/InstantUploadBroadcastReceiver.java (David A. Velasco  2012-12-05 12:22:26 +0100  75)             String localPath = intent.getStringExtra(FileUploader.EXTRA_OLD_FILE_PATH);
f7649804 src/com/owncloud/android/files/InstantUploadBroadcastReceiver.java (Matthias Baumann  2013-02-25 08:21:33 +0100  76)             if (!db.removeIUPendingFile(localPath)) {
8e36e7cc src/com/owncloud/android/files/InstantUploadBroadcastReceiver.java (zerginator        2013-03-12 07:56:27 +0100  77)                 Log_OC.w(TAG, "Tried to remove non existing instant upload file " + localPath);
5bb706e8 src/com/owncloud/android/files/PhotoTakenBroadcastReceiver.java    (Bartek Przybylski 2012-10-09 22:53:41 +0200  78)             }
5bb706e8 src/com/owncloud/android/files/PhotoTakenBroadcastReceiver.java    (Bartek Przybylski 2012-10-09 22:53:41 +0200  79)             db.close();
5bb706e8 src/com/owncloud/android/files/PhotoTakenBroadcastReceiver.java    (Bartek Przybylski 2012-10-09 22:53:41 +0200  80)         }
5bb706e8 src/com/owncloud/android/files/PhotoTakenBroadcastReceiver.java    (Bartek Przybylski 2012-10-09 22:53:41 +0200  81)     }
5bb706e8 src/com/owncloud/android/files/PhotoTakenBroadcastReceiver.java    (Bartek Przybylski 2012-10-09 22:53:41 +0200  82) 
ceb3dfdd src/com/owncloud/android/files/PhotoTakenBroadcastReceiver.java    (David A. Velasco  2012-08-22 10:48:16 +0200  83)     private void handleNewPhotoAction(Context context, Intent intent) {
5bb706e8 src/com/owncloud/android/files/PhotoTakenBroadcastReceiver.java    (Bartek Przybylski 2012-10-09 22:53:41 +0200  84)         if (!instantUploadEnabled(context)) {
01cc2380 src/com/owncloud/android/files/InstantUploadBroadcastReceiver.java (Luke Owncloud     2013-08-05 16:34:21 +0200  85)             Log_OC.d(TAG, "Instant upload disabled, aborting uploading");
5bb706e8 src/com/owncloud/android/files/PhotoTakenBroadcastReceiver.java    (Bartek Przybylski 2012-10-09 22:53:41 +0200  86)             return;
5bb706e8 src/com/owncloud/android/files/PhotoTakenBroadcastReceiver.java    (Bartek Przybylski 2012-10-09 22:53:41 +0200  87)         }
5bb706e8 src/com/owncloud/android/files/PhotoTakenBroadcastReceiver.java    (Bartek Przybylski 2012-10-09 22:53:41 +0200  88) 
9dd2cf29 src/eu/alefzero/owncloud/files/PhotoTakenBroadcastReceiver.java    (Bartek Przybylski 2012-05-27 15:57:18 +0200  89)         Account account = AccountUtils.getCurrentOwnCloudAccount(context);
9dd2cf29 src/eu/alefzero/owncloud/files/PhotoTakenBroadcastReceiver.java    (Bartek Przybylski 2012-05-27 15:57:18 +0200  90)         if (account == null) {
8e36e7cc src/com/owncloud/android/files/InstantUploadBroadcastReceiver.java (zerginator        2013-03-12 07:56:27 +0100  91)             Log_OC.w(TAG, "No owncloud account found for instant upload, aborting");
9dd2cf29 src/eu/alefzero/owncloud/files/PhotoTakenBroadcastReceiver.java    (Bartek Przybylski 2012-05-27 15:57:18 +0200  92)             return;
9dd2cf29 src/eu/alefzero/owncloud/files/PhotoTakenBroadcastReceiver.java    (Bartek Przybylski 2012-05-27 15:57:18 +0200  93)         }
9dd2cf29 src/eu/alefzero/owncloud/files/PhotoTakenBroadcastReceiver.java    (Bartek Przybylski 2012-05-27 15:57:18 +0200  94) 
f7649804 src/com/owncloud/android/files/InstantUploadBroadcastReceiver.java (Matthias Baumann  2013-02-25 08:21:33 +0100  95)         Cursor c = context.getContentResolver().query(intent.getData(), CONTENT_PROJECTION, null, null, null);
f7649804 src/com/owncloud/android/files/InstantUploadBroadcastReceiver.java (Matthias Baumann  2013-02-25 08:21:33 +0100  96) 
9dd2cf29 src/eu/alefzero/owncloud/files/PhotoTakenBroadcastReceiver.java    (Bartek Przybylski 2012-05-27 15:57:18 +0200  97)         if (!c.moveToFirst()) {
8e36e7cc src/com/owncloud/android/files/InstantUploadBroadcastReceiver.java (zerginator        2013-03-12 07:56:27 +0100  98)             Log_OC.e(TAG, "Couldn't resolve given uri: " + intent.getDataString());
9dd2cf29 src/eu/alefzero/owncloud/files/PhotoTakenBroadcastReceiver.java    (Bartek Przybylski 2012-05-27 15:57:18 +0200  99)             return;
9dd2cf29 src/eu/alefzero/owncloud/files/PhotoTakenBroadcastReceiver.java    (Bartek Przybylski 2012-05-27 15:57:18 +0200 100)         }
671cb91c src/com/owncloud/android/files/PhotoTakenBroadcastReceiver.java    (Bartek Przybylski 2012-10-08 21:26:18 +0200 101) 
9dd2cf29 src/eu/alefzero/owncloud/files/PhotoTakenBroadcastReceiver.java    (Bartek Przybylski 2012-05-27 15:57:18 +0200 102)         String file_path = c.getString(c.getColumnIndex(Media.DATA));
9dd2cf29 src/eu/alefzero/owncloud/files/PhotoTakenBroadcastReceiver.java    (Bartek Przybylski 2012-05-27 15:57:18 +0200 103)         String file_name = c.getString(c.getColumnIndex(Media.DISPLAY_NAME));
9dd2cf29 src/eu/alefzero/owncloud/files/PhotoTakenBroadcastReceiver.java    (Bartek Przybylski 2012-05-27 15:57:18 +0200 104)         String mime_type = c.getString(c.getColumnIndex(Media.MIME_TYPE));
9dd2cf29 src/eu/alefzero/owncloud/files/PhotoTakenBroadcastReceiver.java    (Bartek Przybylski 2012-05-27 15:57:18 +0200 105) 
9dd2cf29 src/eu/alefzero/owncloud/files/PhotoTakenBroadcastReceiver.java    (Bartek Przybylski 2012-05-27 15:57:18 +0200 106)         c.close();
8e36e7cc src/com/owncloud/android/files/InstantUploadBroadcastReceiver.java (zerginator        2013-03-12 07:56:27 +0100 107)         Log_OC.e(TAG, file_path + "");
f7649804 src/com/owncloud/android/files/InstantUploadBroadcastReceiver.java (Matthias Baumann  2013-02-25 08:21:33 +0100 108) 
f7649804 src/com/owncloud/android/files/InstantUploadBroadcastReceiver.java (Matthias Baumann  2013-02-25 08:21:33 +0100 109)         // same always temporally the picture to upload
f7649804 src/com/owncloud/android/files/InstantUploadBroadcastReceiver.java (Matthias Baumann  2013-02-25 08:21:33 +0100 110)         DbHandler db = new DbHandler(context);
7392cfe5 src/com/owncloud/android/files/InstantUploadBroadcastReceiver.java (Matthias Baumann  2013-03-16 18:56:27 +0100 111)         db.putFileForLater(file_path, account.name, null);
f7649804 src/com/owncloud/android/files/InstantUploadBroadcastReceiver.java (Matthias Baumann  2013-02-25 08:21:33 +0100 112)         db.close();
f7649804 src/com/owncloud/android/files/InstantUploadBroadcastReceiver.java (Matthias Baumann  2013-02-25 08:21:33 +0100 113) 
f7649804 src/com/owncloud/android/files/InstantUploadBroadcastReceiver.java (Matthias Baumann  2013-02-25 08:21:33 +0100 114)         if (!isOnline(context) || (instantUploadViaWiFiOnly(context) && !isConnectedViaWiFi(context))) {
2b00ff1c src/eu/alefzero/owncloud/files/PhotoTakenBroadcastReceiver.java    (Bartek Przybylski 2012-06-03 19:59:14 +0200 115)             return;
2b00ff1c src/eu/alefzero/owncloud/files/PhotoTakenBroadcastReceiver.java    (Bartek Przybylski 2012-06-03 19:59:14 +0200 116)         }
f7649804 src/com/owncloud/android/files/InstantUploadBroadcastReceiver.java (Matthias Baumann  2013-02-25 08:21:33 +0100 117) 
5bb706e8 src/com/owncloud/android/files/PhotoTakenBroadcastReceiver.java    (Bartek Przybylski 2012-10-09 22:53:41 +0200 118)         // register for upload finishe message
f7649804 src/com/owncloud/android/files/InstantUploadBroadcastReceiver.java (Matthias Baumann  2013-02-25 08:21:33 +0100 119)         // there is a litte problem with android API, we can register for
f7649804 src/com/owncloud/android/files/InstantUploadBroadcastReceiver.java (Matthias Baumann  2013-02-25 08:21:33 +0100 120)         // particular
f7649804 src/com/owncloud/android/files/InstantUploadBroadcastReceiver.java (Matthias Baumann  2013-02-25 08:21:33 +0100 121)         // intent in registerReceiver but we cannot unregister from precise
f7649804 src/com/owncloud/android/files/InstantUploadBroadcastReceiver.java (Matthias Baumann  2013-02-25 08:21:33 +0100 122)         // intent
5bb706e8 src/com/owncloud/android/files/PhotoTakenBroadcastReceiver.java    (Bartek Przybylski 2012-10-09 22:53:41 +0200 123)         // we can unregister from entire listenings but thats suck a bit.
5bb706e8 src/com/owncloud/android/files/PhotoTakenBroadcastReceiver.java    (Bartek Przybylski 2012-10-09 22:53:41 +0200 124)         // On the other hand this might be only for dynamicly registered
5bb706e8 src/com/owncloud/android/files/PhotoTakenBroadcastReceiver.java    (Bartek Przybylski 2012-10-09 22:53:41 +0200 125)         // broadcast receivers, needs investigation.
5bb706e8 src/com/owncloud/android/files/PhotoTakenBroadcastReceiver.java    (Bartek Przybylski 2012-10-09 22:53:41 +0200 126)         IntentFilter filter = new IntentFilter(FileUploader.UPLOAD_FINISH_MESSAGE);
4e1aafda src/com/owncloud/android/files/InstantUploadBroadcastReceiver.java (Bartek Przybylski 2012-10-10 22:08:13 +0200 127)         context.getApplicationContext().registerReceiver(this, filter);
f7649804 src/com/owncloud/android/files/InstantUploadBroadcastReceiver.java (Matthias Baumann  2013-02-25 08:21:33 +0100 128) 
ceb3dfdd src/com/owncloud/android/files/PhotoTakenBroadcastReceiver.java    (David A. Velasco  2012-08-22 10:48:16 +0200 129)         Intent i = new Intent(context, FileUploader.class);
ceb3dfdd src/com/owncloud/android/files/PhotoTakenBroadcastReceiver.java    (David A. Velasco  2012-08-22 10:48:16 +0200 130)         i.putExtra(FileUploader.KEY_ACCOUNT, account);
ceb3dfdd src/com/owncloud/android/files/PhotoTakenBroadcastReceiver.java    (David A. Velasco  2012-08-22 10:48:16 +0200 131)         i.putExtra(FileUploader.KEY_LOCAL_FILE, file_path);
9ad30bdd src/com/owncloud/android/files/InstantUploadBroadcastReceiver.java (David A. Velasco  2013-04-17 18:00:31 +0200 132)         i.putExtra(FileUploader.KEY_REMOTE_FILE, FileStorageUtils.getInstantUploadFilePath(context, file_name));
ceb3dfdd src/com/owncloud/android/files/PhotoTakenBroadcastReceiver.java    (David A. Velasco  2012-08-22 10:48:16 +0200 133)         i.putExtra(FileUploader.KEY_UPLOAD_TYPE, FileUploader.UPLOAD_SINGLE_FILE);
ceb3dfdd src/com/owncloud/android/files/PhotoTakenBroadcastReceiver.java    (David A. Velasco  2012-08-22 10:48:16 +0200 134)         i.putExtra(FileUploader.KEY_MIME_TYPE, mime_type);
ceb3dfdd src/com/owncloud/android/files/PhotoTakenBroadcastReceiver.java    (David A. Velasco  2012-08-22 10:48:16 +0200 135)         i.putExtra(FileUploader.KEY_INSTANT_UPLOAD, true);
ceb3dfdd src/com/owncloud/android/files/PhotoTakenBroadcastReceiver.java    (David A. Velasco  2012-08-22 10:48:16 +0200 136)         context.startService(i);
5bb706e8 src/com/owncloud/android/files/PhotoTakenBroadcastReceiver.java    (Bartek Przybylski 2012-10-09 22:53:41 +0200 137) 
9dd2cf29 src/eu/alefzero/owncloud/files/PhotoTakenBroadcastReceiver.java    (Bartek Przybylski 2012-05-27 15:57:18 +0200 138)     }
9dd2cf29 src/eu/alefzero/owncloud/files/PhotoTakenBroadcastReceiver.java    (Bartek Przybylski 2012-05-27 15:57:18 +0200 139) 
2b00ff1c src/eu/alefzero/owncloud/files/PhotoTakenBroadcastReceiver.java    (Bartek Przybylski 2012-06-03 19:59:14 +0200 140)     private void handleConnectivityAction(Context context, Intent intent) {
5bb706e8 src/com/owncloud/android/files/PhotoTakenBroadcastReceiver.java    (Bartek Przybylski 2012-10-09 22:53:41 +0200 141)         if (!instantUploadEnabled(context)) {
8e36e7cc src/com/owncloud/android/files/InstantUploadBroadcastReceiver.java (zerginator        2013-03-12 07:56:27 +0100 142)             Log_OC.d(TAG, "Instant upload disabled, abording uploading");
5bb706e8 src/com/owncloud/android/files/PhotoTakenBroadcastReceiver.java    (Bartek Przybylski 2012-10-09 22:53:41 +0200 143)             return;
5bb706e8 src/com/owncloud/android/files/PhotoTakenBroadcastReceiver.java    (Bartek Przybylski 2012-10-09 22:53:41 +0200 144)         }
f7649804 src/com/owncloud/android/files/InstantUploadBroadcastReceiver.java (Matthias Baumann  2013-02-25 08:21:33 +0100 145) 
f7649804 src/com/owncloud/android/files/InstantUploadBroadcastReceiver.java (Matthias Baumann  2013-02-25 08:21:33 +0100 146)         if (!intent.hasExtra(ConnectivityManager.EXTRA_NO_CONNECTIVITY)
f7649804 src/com/owncloud/android/files/InstantUploadBroadcastReceiver.java (Matthias Baumann  2013-02-25 08:21:33 +0100 147)                 && isOnline(context)
f7649804 src/com/owncloud/android/files/InstantUploadBroadcastReceiver.java (Matthias Baumann  2013-02-25 08:21:33 +0100 148)                 && (!instantUploadViaWiFiOnly(context) || (instantUploadViaWiFiOnly(context) == isConnectedViaWiFi(context) == true))) {
2b00ff1c src/eu/alefzero/owncloud/files/PhotoTakenBroadcastReceiver.java    (Bartek Przybylski 2012-06-03 19:59:14 +0200 149)             DbHandler db = new DbHandler(context);
2b00ff1c src/eu/alefzero/owncloud/files/PhotoTakenBroadcastReceiver.java    (Bartek Przybylski 2012-06-03 19:59:14 +0200 150)             Cursor c = db.getAwaitingFiles();
2b00ff1c src/eu/alefzero/owncloud/files/PhotoTakenBroadcastReceiver.java    (Bartek Przybylski 2012-06-03 19:59:14 +0200 151)             if (c.moveToFirst()) {
4e1aafda src/com/owncloud/android/files/InstantUploadBroadcastReceiver.java (Bartek Przybylski 2012-10-10 22:08:13 +0200 152)                 IntentFilter filter = new IntentFilter(FileUploader.UPLOAD_FINISH_MESSAGE);
4e1aafda src/com/owncloud/android/files/InstantUploadBroadcastReceiver.java (Bartek Przybylski 2012-10-10 22:08:13 +0200 153)                 context.getApplicationContext().registerReceiver(this, filter);
2b00ff1c src/eu/alefzero/owncloud/files/PhotoTakenBroadcastReceiver.java    (Bartek Przybylski 2012-06-03 19:59:14 +0200 154)                 do {
2b00ff1c src/eu/alefzero/owncloud/files/PhotoTakenBroadcastReceiver.java    (Bartek Przybylski 2012-06-03 19:59:14 +0200 155)                     String account_name = c.getString(c.getColumnIndex("account"));
2b00ff1c src/eu/alefzero/owncloud/files/PhotoTakenBroadcastReceiver.java    (Bartek Przybylski 2012-06-03 19:59:14 +0200 156)                     String file_path = c.getString(c.getColumnIndex("path"));
2b00ff1c src/eu/alefzero/owncloud/files/PhotoTakenBroadcastReceiver.java    (Bartek Przybylski 2012-06-03 19:59:14 +0200 157)                     File f = new File(file_path);
2b00ff1c src/eu/alefzero/owncloud/files/PhotoTakenBroadcastReceiver.java    (Bartek Przybylski 2012-06-03 19:59:14 +0200 158)                     if (f.exists()) {
2b00ff1c src/eu/alefzero/owncloud/files/PhotoTakenBroadcastReceiver.java    (Bartek Przybylski 2012-06-03 19:59:14 +0200 159)                         Account account = new Account(account_name, AccountAuthenticator.ACCOUNT_TYPE);
5bb706e8 src/com/owncloud/android/files/PhotoTakenBroadcastReceiver.java    (Bartek Przybylski 2012-10-09 22:53:41 +0200 160) 
494ebd60 src/eu/alefzero/owncloud/files/PhotoTakenBroadcastReceiver.java    (David A. Velasco  2012-07-05 16:38:59 +0200 161)                         String mimeType = null;
121a462b src/eu/alefzero/owncloud/files/PhotoTakenBroadcastReceiver.java    (David A. Velasco  2012-06-28 10:59:27 +0200 162)                         try {
f7649804 src/com/owncloud/android/files/InstantUploadBroadcastReceiver.java (Matthias Baumann  2013-02-25 08:21:33 +0100 163)                             mimeType = MimeTypeMap.getSingleton().getMimeTypeFromExtension(
f7649804 src/com/owncloud/android/files/InstantUploadBroadcastReceiver.java (Matthias Baumann  2013-02-25 08:21:33 +0100 164)                                     f.getName().substring(f.getName().lastIndexOf('.') + 1));
f7649804 src/com/owncloud/android/files/InstantUploadBroadcastReceiver.java (Matthias Baumann  2013-02-25 08:21:33 +0100 165) 
5bb706e8 src/com/owncloud/android/files/PhotoTakenBroadcastReceiver.java    (Bartek Przybylski 2012-10-09 22:53:41 +0200 166)                         } catch (Throwable e) {
8e36e7cc src/com/owncloud/android/files/InstantUploadBroadcastReceiver.java (zerginator        2013-03-12 07:56:27 +0100 167)                             Log_OC.e(TAG, "Trying to find out MIME type of a file without extension: " + f.getName());
121a462b src/eu/alefzero/owncloud/files/PhotoTakenBroadcastReceiver.java    (David A. Velasco  2012-06-28 10:59:27 +0200 168)                         }
494ebd60 src/eu/alefzero/owncloud/files/PhotoTakenBroadcastReceiver.java    (David A. Velasco  2012-07-05 16:38:59 +0200 169)                         if (mimeType == null)
494ebd60 src/eu/alefzero/owncloud/files/PhotoTakenBroadcastReceiver.java    (David A. Velasco  2012-07-05 16:38:59 +0200 170)                             mimeType = "application/octet-stream";
f7649804 src/com/owncloud/android/files/InstantUploadBroadcastReceiver.java (Matthias Baumann  2013-02-25 08:21:33 +0100 171) 
ceb3dfdd src/com/owncloud/android/files/PhotoTakenBroadcastReceiver.java    (David A. Velasco  2012-08-22 10:48:16 +0200 172)                         Intent i = new Intent(context, FileUploader.class);
ceb3dfdd src/com/owncloud/android/files/PhotoTakenBroadcastReceiver.java    (David A. Velasco  2012-08-22 10:48:16 +0200 173)                         i.putExtra(FileUploader.KEY_ACCOUNT, account);
ceb3dfdd src/com/owncloud/android/files/PhotoTakenBroadcastReceiver.java    (David A. Velasco  2012-08-22 10:48:16 +0200 174)                         i.putExtra(FileUploader.KEY_LOCAL_FILE, file_path);
9ad30bdd src/com/owncloud/android/files/InstantUploadBroadcastReceiver.java (David A. Velasco  2013-04-17 18:00:31 +0200 175)                         i.putExtra(FileUploader.KEY_REMOTE_FILE, FileStorageUtils.getInstantUploadFilePath(context, f.getName()));
ceb3dfdd src/com/owncloud/android/files/PhotoTakenBroadcastReceiver.java    (David A. Velasco  2012-08-22 10:48:16 +0200 176)                         i.putExtra(FileUploader.KEY_UPLOAD_TYPE, FileUploader.UPLOAD_SINGLE_FILE);
ceb3dfdd src/com/owncloud/android/files/PhotoTakenBroadcastReceiver.java    (David A. Velasco  2012-08-22 10:48:16 +0200 177)                         i.putExtra(FileUploader.KEY_INSTANT_UPLOAD, true);
ceb3dfdd src/com/owncloud/android/files/PhotoTakenBroadcastReceiver.java    (David A. Velasco  2012-08-22 10:48:16 +0200 178)                         context.startService(i);
f7649804 src/com/owncloud/android/files/InstantUploadBroadcastReceiver.java (Matthias Baumann  2013-02-25 08:21:33 +0100 179) 
2b00ff1c src/eu/alefzero/owncloud/files/PhotoTakenBroadcastReceiver.java    (Bartek Przybylski 2012-06-03 19:59:14 +0200 180)                     } else {
8e36e7cc src/com/owncloud/android/files/InstantUploadBroadcastReceiver.java (zerginator        2013-03-12 07:56:27 +0100 181)                         Log_OC.w(TAG, "Instant upload file " + f.getAbsolutePath() + " dont exist anymore");
2b00ff1c src/eu/alefzero/owncloud/files/PhotoTakenBroadcastReceiver.java    (Bartek Przybylski 2012-06-03 19:59:14 +0200 182)                     }
f7649804 src/com/owncloud/android/files/InstantUploadBroadcastReceiver.java (Matthias Baumann  2013-02-25 08:21:33 +0100 183)                 } while (c.moveToNext());
2b00ff1c src/eu/alefzero/owncloud/files/PhotoTakenBroadcastReceiver.java    (Bartek Przybylski 2012-06-03 19:59:14 +0200 184)             }
5bb706e8 src/com/owncloud/android/files/PhotoTakenBroadcastReceiver.java    (Bartek Przybylski 2012-10-09 22:53:41 +0200 185)             c.close();
2b00ff1c src/eu/alefzero/owncloud/files/PhotoTakenBroadcastReceiver.java    (Bartek Przybylski 2012-06-03 19:59:14 +0200 186)             db.close();
2b00ff1c src/eu/alefzero/owncloud/files/PhotoTakenBroadcastReceiver.java    (Bartek Przybylski 2012-06-03 19:59:14 +0200 187)         }
f7649804 src/com/owncloud/android/files/InstantUploadBroadcastReceiver.java (Matthias Baumann  2013-02-25 08:21:33 +0100 188) 
2b00ff1c src/eu/alefzero/owncloud/files/PhotoTakenBroadcastReceiver.java    (Bartek Przybylski 2012-06-03 19:59:14 +0200 189)     }
2b00ff1c src/eu/alefzero/owncloud/files/PhotoTakenBroadcastReceiver.java    (Bartek Przybylski 2012-06-03 19:59:14 +0200 190) 
f7649804 src/com/owncloud/android/files/InstantUploadBroadcastReceiver.java (Matthias Baumann  2013-02-25 08:21:33 +0100 191)     public static boolean isOnline(Context context) {
2b00ff1c src/eu/alefzero/owncloud/files/PhotoTakenBroadcastReceiver.java    (Bartek Przybylski 2012-06-03 19:59:14 +0200 192)         ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
2b00ff1c src/eu/alefzero/owncloud/files/PhotoTakenBroadcastReceiver.java    (Bartek Przybylski 2012-06-03 19:59:14 +0200 193)         return cm.getActiveNetworkInfo() != null && cm.getActiveNetworkInfo().isConnected();
2b00ff1c src/eu/alefzero/owncloud/files/PhotoTakenBroadcastReceiver.java    (Bartek Przybylski 2012-06-03 19:59:14 +0200 194)     }
f7649804 src/com/owncloud/android/files/InstantUploadBroadcastReceiver.java (Matthias Baumann  2013-02-25 08:21:33 +0100 195) 
f7649804 src/com/owncloud/android/files/InstantUploadBroadcastReceiver.java (Matthias Baumann  2013-02-25 08:21:33 +0100 196)     public static boolean isConnectedViaWiFi(Context context) {
5bb706e8 src/com/owncloud/android/files/PhotoTakenBroadcastReceiver.java    (Bartek Przybylski 2012-10-09 22:53:41 +0200 197)         ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
f7649804 src/com/owncloud/android/files/InstantUploadBroadcastReceiver.java (Matthias Baumann  2013-02-25 08:21:33 +0100 198)         return cm != null && cm.getActiveNetworkInfo() != null
f7649804 src/com/owncloud/android/files/InstantUploadBroadcastReceiver.java (Matthias Baumann  2013-02-25 08:21:33 +0100 199)                 && cm.getActiveNetworkInfo().getType() == ConnectivityManager.TYPE_WIFI
f7649804 src/com/owncloud/android/files/InstantUploadBroadcastReceiver.java (Matthias Baumann  2013-02-25 08:21:33 +0100 200)                 && cm.getActiveNetworkInfo().getState() == State.CONNECTED;
5bb706e8 src/com/owncloud/android/files/PhotoTakenBroadcastReceiver.java    (Bartek Przybylski 2012-10-09 22:53:41 +0200 201)     }
f7649804 src/com/owncloud/android/files/InstantUploadBroadcastReceiver.java (Matthias Baumann  2013-02-25 08:21:33 +0100 202) 
f7649804 src/com/owncloud/android/files/InstantUploadBroadcastReceiver.java (Matthias Baumann  2013-02-25 08:21:33 +0100 203)     public static boolean instantUploadEnabled(Context context) {
5bb706e8 src/com/owncloud/android/files/PhotoTakenBroadcastReceiver.java    (Bartek Przybylski 2012-10-09 22:53:41 +0200 204)         return PreferenceManager.getDefaultSharedPreferences(context).getBoolean("instant_uploading", false);
5bb706e8 src/com/owncloud/android/files/PhotoTakenBroadcastReceiver.java    (Bartek Przybylski 2012-10-09 22:53:41 +0200 205)     }
f7649804 src/com/owncloud/android/files/InstantUploadBroadcastReceiver.java (Matthias Baumann  2013-02-25 08:21:33 +0100 206) 
f7649804 src/com/owncloud/android/files/InstantUploadBroadcastReceiver.java (Matthias Baumann  2013-02-25 08:21:33 +0100 207)     public static boolean instantUploadViaWiFiOnly(Context context) {
5bb706e8 src/com/owncloud/android/files/PhotoTakenBroadcastReceiver.java    (Bartek Przybylski 2012-10-09 22:53:41 +0200 208)         return PreferenceManager.getDefaultSharedPreferences(context).getBoolean("instant_upload_on_wifi", false);
5bb706e8 src/com/owncloud/android/files/PhotoTakenBroadcastReceiver.java    (Bartek Przybylski 2012-10-09 22:53:41 +0200 209)     }
9dd2cf29 src/eu/alefzero/owncloud/files/PhotoTakenBroadcastReceiver.java    (Bartek Przybylski 2012-05-27 15:57:18 +0200 210) }
