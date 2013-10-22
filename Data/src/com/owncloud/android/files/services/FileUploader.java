92080afe src/com/owncloud/android/files/services/FileUploader.java (Bartek Przybylski 2012-10-28 00:12:15 +0200   1) /* ownCloud Android client application
92080afe src/com/owncloud/android/files/services/FileUploader.java (Bartek Przybylski 2012-10-28 00:12:15 +0200   2)  *   Copyright (C) 2012 Bartek Przybylski
bb257ec7 src/com/owncloud/android/files/services/FileUploader.java (David A. Velasco  2013-02-07 18:45:10 +0100   3)  *   Copyright (C) 2012-2013 ownCloud Inc.
92080afe src/com/owncloud/android/files/services/FileUploader.java (Bartek Przybylski 2012-10-28 00:12:15 +0200   4)  *
92080afe src/com/owncloud/android/files/services/FileUploader.java (Bartek Przybylski 2012-10-28 00:12:15 +0200   5)  *   This program is free software: you can redistribute it and/or modify
bdc0332c src/com/owncloud/android/files/services/FileUploader.java (David A. Velasco  2013-04-17 12:26:13 +0200   6)  *   it under the terms of the GNU General Public License version 2,
bdc0332c src/com/owncloud/android/files/services/FileUploader.java (David A. Velasco  2013-04-17 12:26:13 +0200   7)  *   as published by the Free Software Foundation.
92080afe src/com/owncloud/android/files/services/FileUploader.java (Bartek Przybylski 2012-10-28 00:12:15 +0200   8)  *
92080afe src/com/owncloud/android/files/services/FileUploader.java (Bartek Przybylski 2012-10-28 00:12:15 +0200   9)  *   This program is distributed in the hope that it will be useful,
92080afe src/com/owncloud/android/files/services/FileUploader.java (Bartek Przybylski 2012-10-28 00:12:15 +0200  10)  *   but WITHOUT ANY WARRANTY; without even the implied warranty of
92080afe src/com/owncloud/android/files/services/FileUploader.java (Bartek Przybylski 2012-10-28 00:12:15 +0200  11)  *   MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
92080afe src/com/owncloud/android/files/services/FileUploader.java (Bartek Przybylski 2012-10-28 00:12:15 +0200  12)  *   GNU General Public License for more details.
92080afe src/com/owncloud/android/files/services/FileUploader.java (Bartek Przybylski 2012-10-28 00:12:15 +0200  13)  *
92080afe src/com/owncloud/android/files/services/FileUploader.java (Bartek Przybylski 2012-10-28 00:12:15 +0200  14)  *   You should have received a copy of the GNU General Public License
92080afe src/com/owncloud/android/files/services/FileUploader.java (Bartek Przybylski 2012-10-28 00:12:15 +0200  15)  *   along with this program.  If not, see <http://www.gnu.org/licenses/>.
92080afe src/com/owncloud/android/files/services/FileUploader.java (Bartek Przybylski 2012-10-28 00:12:15 +0200  16)  *
92080afe src/com/owncloud/android/files/services/FileUploader.java (Bartek Przybylski 2012-10-28 00:12:15 +0200  17)  */
92080afe src/com/owncloud/android/files/services/FileUploader.java (Bartek Przybylski 2012-10-28 00:12:15 +0200  18) 
a4ba6170 src/com/owncloud/android/files/services/FileUploader.java (Bartek Przybylski 2012-07-31 17:43:37 +0200  19) package com.owncloud.android.files.services;
b999f542 src/eu/alefzero/owncloud/files/services/FileUploader.java (Bartek Przybylski 2012-05-15 21:52:59 +0200  20) 
35a384d0 src/eu/alefzero/owncloud/files/services/FileUploader.java (Bartek Przybylski 2012-05-19 23:30:23 +0200  21) import java.io.File;
11b88e0f src/com/owncloud/android/files/services/FileUploader.java (David A. Velasco  2012-12-19 14:12:53 +0100  22) import java.io.IOException;
53b67429 src/com/owncloud/android/files/services/FileUploader.java (David A. Velasco  2012-09-12 16:16:56 +0200  23) import java.util.AbstractList;
cfb68a69 src/com/owncloud/android/files/services/FileUploader.java (David A. Velasco  2013-02-20 12:34:11 +0100  24) import java.util.HashMap;
53b67429 src/com/owncloud/android/files/services/FileUploader.java (David A. Velasco  2012-09-12 16:16:56 +0200  25) import java.util.Iterator;
cfb68a69 src/com/owncloud/android/files/services/FileUploader.java (David A. Velasco  2013-02-20 12:34:11 +0100  26) import java.util.Map;
53b67429 src/com/owncloud/android/files/services/FileUploader.java (David A. Velasco  2012-09-12 16:16:56 +0200  27) import java.util.Vector;
5fc7cd13 src/com/owncloud/android/files/services/FileUploader.java (David A. Velasco  2012-10-11 11:24:17 +0200  28) import java.util.concurrent.ConcurrentHashMap;
5fc7cd13 src/com/owncloud/android/files/services/FileUploader.java (David A. Velasco  2012-10-11 11:24:17 +0200  29) import java.util.concurrent.ConcurrentMap;
3a34911f src/eu/alefzero/owncloud/files/services/FileUploader.java (David A. Velasco  2012-07-23 10:13:50 +0200  30) 
c38a3b2e src/com/owncloud/android/files/services/FileUploader.java (David A. Velasco  2012-12-18 11:20:03 +0100  31) import org.apache.http.HttpStatus;
c38a3b2e src/com/owncloud/android/files/services/FileUploader.java (David A. Velasco  2012-12-18 11:20:03 +0100  32) import org.apache.jackrabbit.webdav.MultiStatus;
c38a3b2e src/com/owncloud/android/files/services/FileUploader.java (David A. Velasco  2012-12-18 11:20:03 +0100  33) import org.apache.jackrabbit.webdav.client.methods.PropFindMethod;
c38a3b2e src/com/owncloud/android/files/services/FileUploader.java (David A. Velasco  2012-12-18 11:20:03 +0100  34) 
69d6d821 src/com/owncloud/android/files/services/FileUploader.java (David A. Velasco  2013-03-21 12:21:48 +0100  35) import com.owncloud.android.authentication.AccountAuthenticator;
69d6d821 src/com/owncloud/android/files/services/FileUploader.java (David A. Velasco  2013-03-21 12:21:48 +0100  36) import com.owncloud.android.authentication.AuthenticatorActivity;
a4ba6170 src/com/owncloud/android/files/services/FileUploader.java (Bartek Przybylski 2012-07-31 17:43:37 +0200  37) import com.owncloud.android.datamodel.FileDataStorageManager;
a4ba6170 src/com/owncloud/android/files/services/FileUploader.java (Bartek Przybylski 2012-07-31 17:43:37 +0200  38) import com.owncloud.android.datamodel.OCFile;
53b67429 src/com/owncloud/android/files/services/FileUploader.java (David A. Velasco  2012-09-12 16:16:56 +0200  39) import com.owncloud.android.operations.ChunkedUploadFileOperation;
11b88e0f src/com/owncloud/android/files/services/FileUploader.java (David A. Velasco  2012-12-19 14:12:53 +0100  40) import com.owncloud.android.operations.CreateFolderOperation;
11b88e0f src/com/owncloud/android/files/services/FileUploader.java (David A. Velasco  2012-12-19 14:12:53 +0100  41) import com.owncloud.android.operations.RemoteOperation;
53b67429 src/com/owncloud/android/files/services/FileUploader.java (David A. Velasco  2012-09-12 16:16:56 +0200  42) import com.owncloud.android.operations.RemoteOperationResult;
53b67429 src/com/owncloud/android/files/services/FileUploader.java (David A. Velasco  2012-09-12 16:16:56 +0200  43) import com.owncloud.android.operations.UploadFileOperation;
c38a3b2e src/com/owncloud/android/files/services/FileUploader.java (David A. Velasco  2012-12-18 11:20:03 +0100  44) import com.owncloud.android.operations.RemoteOperationResult.ResultCode;
53b67429 src/com/owncloud/android/files/services/FileUploader.java (David A. Velasco  2012-09-12 16:16:56 +0200  45) import com.owncloud.android.utils.OwnCloudVersion;
ceb3dfdd src/com/owncloud/android/files/services/FileUploader.java (David A. Velasco  2012-08-22 10:48:16 +0200  46) 
99d52af2 src/com/owncloud/android/files/services/FileUploader.java (David A. Velasco  2012-08-16 16:06:12 +0200  47) import eu.alefzero.webdav.OnDatatransferProgressListener;
c38a3b2e src/com/owncloud/android/files/services/FileUploader.java (David A. Velasco  2012-12-18 11:20:03 +0100  48) import eu.alefzero.webdav.WebdavEntry;
c38a3b2e src/com/owncloud/android/files/services/FileUploader.java (David A. Velasco  2012-12-18 11:20:03 +0100  49) import eu.alefzero.webdav.WebdavUtils;
48f13c8a src/com/owncloud/android/files/services/FileUploader.java (David A. Velasco  2012-09-05 13:46:30 +0200  50) 
48f13c8a src/com/owncloud/android/files/services/FileUploader.java (David A. Velasco  2012-09-05 13:46:30 +0200  51) import com.owncloud.android.network.OwnCloudClientUtils;
a4ba6170 src/com/owncloud/android/files/services/FileUploader.java (Bartek Przybylski 2012-07-31 17:43:37 +0200  52) 
b999f542 src/eu/alefzero/owncloud/files/services/FileUploader.java (Bartek Przybylski 2012-05-15 21:52:59 +0200  53) import android.accounts.Account;
53b67429 src/com/owncloud/android/files/services/FileUploader.java (David A. Velasco  2012-09-12 16:16:56 +0200  54) import android.accounts.AccountManager;
11b88e0f src/com/owncloud/android/files/services/FileUploader.java (David A. Velasco  2012-12-19 14:12:53 +0100  55) import android.accounts.AccountsException;
b999f542 src/eu/alefzero/owncloud/files/services/FileUploader.java (Bartek Przybylski 2012-05-15 21:52:59 +0200  56) import android.app.Notification;
b999f542 src/eu/alefzero/owncloud/files/services/FileUploader.java (Bartek Przybylski 2012-05-15 21:52:59 +0200  57) import android.app.NotificationManager;
194490b3 src/eu/alefzero/owncloud/files/services/FileUploader.java (David A. Velasco  2012-06-19 11:05:00 +0200  58) import android.app.PendingIntent;
b999f542 src/eu/alefzero/owncloud/files/services/FileUploader.java (Bartek Przybylski 2012-05-15 21:52:59 +0200  59) import android.app.Service;
b999f542 src/eu/alefzero/owncloud/files/services/FileUploader.java (Bartek Przybylski 2012-05-15 21:52:59 +0200  60) import android.content.Intent;
5fc7cd13 src/com/owncloud/android/files/services/FileUploader.java (David A. Velasco  2012-10-11 11:24:17 +0200  61) import android.os.Binder;
b999f542 src/eu/alefzero/owncloud/files/services/FileUploader.java (Bartek Przybylski 2012-05-15 21:52:59 +0200  62) import android.os.Handler;
b999f542 src/eu/alefzero/owncloud/files/services/FileUploader.java (Bartek Przybylski 2012-05-15 21:52:59 +0200  63) import android.os.HandlerThread;
b999f542 src/eu/alefzero/owncloud/files/services/FileUploader.java (Bartek Przybylski 2012-05-15 21:52:59 +0200  64) import android.os.IBinder;
b999f542 src/eu/alefzero/owncloud/files/services/FileUploader.java (Bartek Przybylski 2012-05-15 21:52:59 +0200  65) import android.os.Looper;
b999f542 src/eu/alefzero/owncloud/files/services/FileUploader.java (Bartek Przybylski 2012-05-15 21:52:59 +0200  66) import android.os.Message;
b999f542 src/eu/alefzero/owncloud/files/services/FileUploader.java (Bartek Przybylski 2012-05-15 21:52:59 +0200  67) import android.os.Process;
27112bd2 src/com/owncloud/android/files/services/FileUploader.java (David A. Velasco  2012-10-11 16:40:56 +0200  68) import android.webkit.MimeTypeMap;
b999f542 src/eu/alefzero/owncloud/files/services/FileUploader.java (Bartek Przybylski 2012-05-15 21:52:59 +0200  69) import android.widget.RemoteViews;
53b67429 src/com/owncloud/android/files/services/FileUploader.java (David A. Velasco  2012-09-12 16:16:56 +0200  70) 
8e36e7cc src/com/owncloud/android/files/services/FileUploader.java (zerginator        2013-03-12 07:56:27 +0100  71) import com.owncloud.android.Log_OC;
a4ba6170 src/com/owncloud/android/files/services/FileUploader.java (Bartek Przybylski 2012-07-31 17:43:37 +0200  72) import com.owncloud.android.R;
f7649804 src/com/owncloud/android/files/services/FileUploader.java (Matthias Baumann  2013-02-25 08:21:33 +0100  73) import com.owncloud.android.db.DbHandler;
6c5f5613 src/com/owncloud/android/files/services/FileUploader.java (Matthias Baumann  2013-03-17 10:38:42 +0100  74) import com.owncloud.android.ui.activity.FailedUploadActivity;
bc1fcf84 src/com/owncloud/android/files/services/FileUploader.java (David A. Velasco  2013-05-07 13:49:54 +0200  75) import com.owncloud.android.ui.activity.FileActivity;
fd396289 src/com/owncloud/android/files/services/FileUploader.java (David A. Velasco  2013-05-30 17:53:21 +0200  76) import com.owncloud.android.ui.activity.FileDisplayActivity;
f7649804 src/com/owncloud/android/files/services/FileUploader.java (Matthias Baumann  2013-02-25 08:21:33 +0100  77) import com.owncloud.android.ui.activity.InstantUploadActivity;
77ddd1cc src/com/owncloud/android/files/services/FileUploader.java (David A. Velasco  2013-04-04 16:44:30 +0200  78) import com.owncloud.android.ui.preview.PreviewImageActivity;
77ddd1cc src/com/owncloud/android/files/services/FileUploader.java (David A. Velasco  2013-04-04 16:44:30 +0200  79) import com.owncloud.android.ui.preview.PreviewImageFragment;
9ad30bdd src/com/owncloud/android/files/services/FileUploader.java (David A. Velasco  2013-04-17 18:00:31 +0200  80) import com.owncloud.android.utils.FileStorageUtils;
f7649804 src/com/owncloud/android/files/services/FileUploader.java (Matthias Baumann  2013-02-25 08:21:33 +0100  81) 
eb1c63d3 src/eu/alefzero/owncloud/files/services/FileUploader.java (Lennart Rosam     2012-07-04 01:06:27 +0200  82) import eu.alefzero.webdav.WebdavClient;
b999f542 src/eu/alefzero/owncloud/files/services/FileUploader.java (Bartek Przybylski 2012-05-15 21:52:59 +0200  83) 
edd38b80 src/eu/alefzero/owncloud/files/services/FileUploader.java (Bartek Przybylski 2012-05-26 14:30:14 +0200  84) public class FileUploader extends Service implements OnDatatransferProgressListener {
b999f542 src/eu/alefzero/owncloud/files/services/FileUploader.java (Bartek Przybylski 2012-05-15 21:52:59 +0200  85) 
114e0c25 src/eu/alefzero/owncloud/files/services/FileUploader.java (David A. Velasco  2012-06-28 14:12:52 +0200  86)     public static final String UPLOAD_FINISH_MESSAGE = "UPLOAD_FINISH";
275eb1f8 src/eu/alefzero/owncloud/files/services/FileUploader.java (David A. Velasco  2012-07-27 14:29:46 +0200  87)     public static final String EXTRA_UPLOAD_RESULT = "RESULT";
275eb1f8 src/eu/alefzero/owncloud/files/services/FileUploader.java (David A. Velasco  2012-07-27 14:29:46 +0200  88)     public static final String EXTRA_REMOTE_PATH = "REMOTE_PATH";
c38a3b2e src/com/owncloud/android/files/services/FileUploader.java (David A. Velasco  2012-12-18 11:20:03 +0100  89)     public static final String EXTRA_OLD_REMOTE_PATH = "OLD_REMOTE_PATH";
c38a3b2e src/com/owncloud/android/files/services/FileUploader.java (David A. Velasco  2012-12-18 11:20:03 +0100  90)     public static final String EXTRA_OLD_FILE_PATH = "OLD_FILE_PATH";
f7649804 src/com/owncloud/android/files/services/FileUploader.java (Matthias Baumann  2013-02-25 08:21:33 +0100  91)     public static final String ACCOUNT_NAME = "ACCOUNT_NAME";
f7649804 src/com/owncloud/android/files/services/FileUploader.java (Matthias Baumann  2013-02-25 08:21:33 +0100  92) 
c38a3b2e src/com/owncloud/android/files/services/FileUploader.java (David A. Velasco  2012-12-18 11:20:03 +0100  93)     public static final String KEY_FILE = "FILE";
435b31ba src/eu/alefzero/owncloud/files/services/FileUploader.java (Lennart Rosam     2012-05-16 09:48:34 +0200  94)     public static final String KEY_LOCAL_FILE = "LOCAL_FILE";
435b31ba src/eu/alefzero/owncloud/files/services/FileUploader.java (Lennart Rosam     2012-05-16 09:48:34 +0200  95)     public static final String KEY_REMOTE_FILE = "REMOTE_FILE";
c38a3b2e src/com/owncloud/android/files/services/FileUploader.java (David A. Velasco  2012-12-18 11:20:03 +0100  96)     public static final String KEY_MIME_TYPE = "MIME_TYPE";
c38a3b2e src/com/owncloud/android/files/services/FileUploader.java (David A. Velasco  2012-12-18 11:20:03 +0100  97) 
435b31ba src/eu/alefzero/owncloud/files/services/FileUploader.java (Lennart Rosam     2012-05-16 09:48:34 +0200  98)     public static final String KEY_ACCOUNT = "ACCOUNT";
f7649804 src/com/owncloud/android/files/services/FileUploader.java (Matthias Baumann  2013-02-25 08:21:33 +0100  99) 
435b31ba src/eu/alefzero/owncloud/files/services/FileUploader.java (Lennart Rosam     2012-05-16 09:48:34 +0200 100)     public static final String KEY_UPLOAD_TYPE = "UPLOAD_TYPE";
ba148a82 src/com/owncloud/android/files/services/FileUploader.java (Bartek Przybylski 2012-08-22 19:32:42 +0200 101)     public static final String KEY_FORCE_OVERWRITE = "KEY_FORCE_OVERWRITE";
ceb3dfdd src/com/owncloud/android/files/services/FileUploader.java (David A. Velasco  2012-08-22 10:48:16 +0200 102)     public static final String KEY_INSTANT_UPLOAD = "INSTANT_UPLOAD";
c38a3b2e src/com/owncloud/android/files/services/FileUploader.java (David A. Velasco  2012-12-18 11:20:03 +0100 103)     public static final String KEY_LOCAL_BEHAVIOUR = "BEHAVIOUR";
f7649804 src/com/owncloud/android/files/services/FileUploader.java (Matthias Baumann  2013-02-25 08:21:33 +0100 104) 
c38a3b2e src/com/owncloud/android/files/services/FileUploader.java (David A. Velasco  2012-12-18 11:20:03 +0100 105)     public static final int LOCAL_BEHAVIOUR_COPY = 0;
c38a3b2e src/com/owncloud/android/files/services/FileUploader.java (David A. Velasco  2012-12-18 11:20:03 +0100 106)     public static final int LOCAL_BEHAVIOUR_MOVE = 1;
c38a3b2e src/com/owncloud/android/files/services/FileUploader.java (David A. Velasco  2012-12-18 11:20:03 +0100 107)     public static final int LOCAL_BEHAVIOUR_FORGET = 2;
435b31ba src/eu/alefzero/owncloud/files/services/FileUploader.java (Lennart Rosam     2012-05-16 09:48:34 +0200 108) 
435b31ba src/eu/alefzero/owncloud/files/services/FileUploader.java (Lennart Rosam     2012-05-16 09:48:34 +0200 109)     public static final int UPLOAD_SINGLE_FILE = 0;
435b31ba src/eu/alefzero/owncloud/files/services/FileUploader.java (Lennart Rosam     2012-05-16 09:48:34 +0200 110)     public static final int UPLOAD_MULTIPLE_FILES = 1;
435b31ba src/eu/alefzero/owncloud/files/services/FileUploader.java (Lennart Rosam     2012-05-16 09:48:34 +0200 111) 
53b67429 src/com/owncloud/android/files/services/FileUploader.java (David A. Velasco  2012-09-12 16:16:56 +0200 112)     private static final String TAG = FileUploader.class.getSimpleName();
f7649804 src/com/owncloud/android/files/services/FileUploader.java (Matthias Baumann  2013-02-25 08:21:33 +0100 113) 
435b31ba src/eu/alefzero/owncloud/files/services/FileUploader.java (Lennart Rosam     2012-05-16 09:48:34 +0200 114)     private Looper mServiceLooper;
435b31ba src/eu/alefzero/owncloud/files/services/FileUploader.java (Lennart Rosam     2012-05-16 09:48:34 +0200 115)     private ServiceHandler mServiceHandler;
5fc7cd13 src/com/owncloud/android/files/services/FileUploader.java (David A. Velasco  2012-10-11 11:24:17 +0200 116)     private IBinder mBinder;
5fc7cd13 src/com/owncloud/android/files/services/FileUploader.java (David A. Velasco  2012-10-11 11:24:17 +0200 117)     private WebdavClient mUploadClient = null;
cf27417e src/com/owncloud/android/files/services/FileUploader.java (David A. Velasco  2012-10-15 15:22:07 +0200 118)     private Account mLastAccount = null;
5fc7cd13 src/com/owncloud/android/files/services/FileUploader.java (David A. Velasco  2012-10-11 11:24:17 +0200 119)     private FileDataStorageManager mStorageManager;
68ce2e7a src/com/owncloud/android/files/services/FileUploader.java (David A. Velasco  2012-10-09 14:53:25 +0200 120) 
5fc7cd13 src/com/owncloud/android/files/services/FileUploader.java (David A. Velasco  2012-10-11 11:24:17 +0200 121)     private ConcurrentMap<String, UploadFileOperation> mPendingUploads = new ConcurrentHashMap<String, UploadFileOperation>();
5fc7cd13 src/com/owncloud/android/files/services/FileUploader.java (David A. Velasco  2012-10-11 11:24:17 +0200 122)     private UploadFileOperation mCurrentUpload = null;
f7649804 src/com/owncloud/android/files/services/FileUploader.java (Matthias Baumann  2013-02-25 08:21:33 +0100 123) 
68ce2e7a src/com/owncloud/android/files/services/FileUploader.java (David A. Velasco  2012-10-09 14:53:25 +0200 124)     private NotificationManager mNotificationManager;
35a384d0 src/eu/alefzero/owncloud/files/services/FileUploader.java (Bartek Przybylski 2012-05-19 23:30:23 +0200 125)     private Notification mNotification;
5fc7cd13 src/com/owncloud/android/files/services/FileUploader.java (David A. Velasco  2012-10-11 11:24:17 +0200 126)     private int mLastPercent;
68ce2e7a src/com/owncloud/android/files/services/FileUploader.java (David A. Velasco  2012-10-09 14:53:25 +0200 127)     private RemoteViews mDefaultNotificationContentView;
f7649804 src/com/owncloud/android/files/services/FileUploader.java (Matthias Baumann  2013-02-25 08:21:33 +0100 128) 
f3ba1075 src/eu/alefzero/owncloud/files/services/FileUploader.java (David A. Velasco  2012-07-26 18:35:09 +0200 129)     /**
5fc7cd13 src/com/owncloud/android/files/services/FileUploader.java (David A. Velasco  2012-10-11 11:24:17 +0200 130)      * Builds a key for mPendingUploads from the account and file to upload
5fc7cd13 src/com/owncloud/android/files/services/FileUploader.java (David A. Velasco  2012-10-11 11:24:17 +0200 131)      * 
bcc7bd27 src/com/owncloud/android/files/services/FileUploader.java (David A. Velasco  2013-02-21 09:57:23 +0100 132)      * @param account   Account where the file to upload is stored
bcc7bd27 src/com/owncloud/android/files/services/FileUploader.java (David A. Velasco  2013-02-21 09:57:23 +0100 133)      * @param file      File to upload
f3ba1075 src/eu/alefzero/owncloud/files/services/FileUploader.java (David A. Velasco  2012-07-26 18:35:09 +0200 134)      */
5fc7cd13 src/com/owncloud/android/files/services/FileUploader.java (David A. Velasco  2012-10-11 11:24:17 +0200 135)     private String buildRemoteName(Account account, OCFile file) {
5fc7cd13 src/com/owncloud/android/files/services/FileUploader.java (David A. Velasco  2012-10-11 11:24:17 +0200 136)         return account.name + file.getRemotePath();
f3ba1075 src/eu/alefzero/owncloud/files/services/FileUploader.java (David A. Velasco  2012-07-26 18:35:09 +0200 137)     }
5fc7cd13 src/com/owncloud/android/files/services/FileUploader.java (David A. Velasco  2012-10-11 11:24:17 +0200 138) 
5fc7cd13 src/com/owncloud/android/files/services/FileUploader.java (David A. Velasco  2012-10-11 11:24:17 +0200 139)     private String buildRemoteName(Account account, String remotePath) {
5fc7cd13 src/com/owncloud/android/files/services/FileUploader.java (David A. Velasco  2012-10-11 11:24:17 +0200 140)         return account.name + remotePath;
f3ba1075 src/eu/alefzero/owncloud/files/services/FileUploader.java (David A. Velasco  2012-07-26 18:35:09 +0200 141)     }
f3ba1075 src/eu/alefzero/owncloud/files/services/FileUploader.java (David A. Velasco  2012-07-26 18:35:09 +0200 142) 
53b67429 src/com/owncloud/android/files/services/FileUploader.java (David A. Velasco  2012-09-12 16:16:56 +0200 143)     /**
53b67429 src/com/owncloud/android/files/services/FileUploader.java (David A. Velasco  2012-09-12 16:16:56 +0200 144)      * Checks if an ownCloud server version should support chunked uploads.
53b67429 src/com/owncloud/android/files/services/FileUploader.java (David A. Velasco  2012-09-12 16:16:56 +0200 145)      * 
f7649804 src/com/owncloud/android/files/services/FileUploader.java (Matthias Baumann  2013-02-25 08:21:33 +0100 146)      * @param version OwnCloud version instance corresponding to an ownCloud
f7649804 src/com/owncloud/android/files/services/FileUploader.java (Matthias Baumann  2013-02-25 08:21:33 +0100 147)      *            server.
f7649804 src/com/owncloud/android/files/services/FileUploader.java (Matthias Baumann  2013-02-25 08:21:33 +0100 148)      * @return 'True' if the ownCloud server with version supports chunked
f7649804 src/com/owncloud/android/files/services/FileUploader.java (Matthias Baumann  2013-02-25 08:21:33 +0100 149)      *         uploads.
53b67429 src/com/owncloud/android/files/services/FileUploader.java (David A. Velasco  2012-09-12 16:16:56 +0200 150)      */
53b67429 src/com/owncloud/android/files/services/FileUploader.java (David A. Velasco  2012-09-12 16:16:56 +0200 151)     private static boolean chunkedUploadIsSupported(OwnCloudVersion version) {
5fc7cd13 src/com/owncloud/android/files/services/FileUploader.java (David A. Velasco  2012-10-11 11:24:17 +0200 152)         return (version != null && version.compareTo(OwnCloudVersion.owncloud_v4_5) >= 0);
53b67429 src/com/owncloud/android/files/services/FileUploader.java (David A. Velasco  2012-09-12 16:16:56 +0200 153)     }
53b67429 src/com/owncloud/android/files/services/FileUploader.java (David A. Velasco  2012-09-12 16:16:56 +0200 154) 
68ce2e7a src/com/owncloud/android/files/services/FileUploader.java (David A. Velasco  2012-10-09 14:53:25 +0200 155)     /**
68ce2e7a src/com/owncloud/android/files/services/FileUploader.java (David A. Velasco  2012-10-09 14:53:25 +0200 156)      * Service initialization
68ce2e7a src/com/owncloud/android/files/services/FileUploader.java (David A. Velasco  2012-10-09 14:53:25 +0200 157)      */
435b31ba src/eu/alefzero/owncloud/files/services/FileUploader.java (Lennart Rosam     2012-05-16 09:48:34 +0200 158)     @Override
435b31ba src/eu/alefzero/owncloud/files/services/FileUploader.java (Lennart Rosam     2012-05-16 09:48:34 +0200 159)     public void onCreate() {
435b31ba src/eu/alefzero/owncloud/files/services/FileUploader.java (Lennart Rosam     2012-05-16 09:48:34 +0200 160)         super.onCreate();
8e36e7cc src/com/owncloud/android/files/services/FileUploader.java (zerginator        2013-03-12 07:56:27 +0100 161)         Log_OC.i(TAG, "mPendingUploads size:" + mPendingUploads.size());
435b31ba src/eu/alefzero/owncloud/files/services/FileUploader.java (Lennart Rosam     2012-05-16 09:48:34 +0200 162)         mNotificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
f7649804 src/com/owncloud/android/files/services/FileUploader.java (Matthias Baumann  2013-02-25 08:21:33 +0100 163)         HandlerThread thread = new HandlerThread("FileUploaderThread", Process.THREAD_PRIORITY_BACKGROUND);
435b31ba src/eu/alefzero/owncloud/files/services/FileUploader.java (Lennart Rosam     2012-05-16 09:48:34 +0200 164)         thread.start();
435b31ba src/eu/alefzero/owncloud/files/services/FileUploader.java (Lennart Rosam     2012-05-16 09:48:34 +0200 165)         mServiceLooper = thread.getLooper();
5fc7cd13 src/com/owncloud/android/files/services/FileUploader.java (David A. Velasco  2012-10-11 11:24:17 +0200 166)         mServiceHandler = new ServiceHandler(mServiceLooper, this);
5fc7cd13 src/com/owncloud/android/files/services/FileUploader.java (David A. Velasco  2012-10-11 11:24:17 +0200 167)         mBinder = new FileUploaderBinder();
b999f542 src/eu/alefzero/owncloud/files/services/FileUploader.java (Bartek Przybylski 2012-05-15 21:52:59 +0200 168)     }
435b31ba src/eu/alefzero/owncloud/files/services/FileUploader.java (Lennart Rosam     2012-05-16 09:48:34 +0200 169) 
68ce2e7a src/com/owncloud/android/files/services/FileUploader.java (David A. Velasco  2012-10-09 14:53:25 +0200 170)     /**
68ce2e7a src/com/owncloud/android/files/services/FileUploader.java (David A. Velasco  2012-10-09 14:53:25 +0200 171)      * Entry point to add one or several files to the queue of uploads.
68ce2e7a src/com/owncloud/android/files/services/FileUploader.java (David A. Velasco  2012-10-09 14:53:25 +0200 172)      * 
f7649804 src/com/owncloud/android/files/services/FileUploader.java (Matthias Baumann  2013-02-25 08:21:33 +0100 173)      * New uploads are added calling to startService(), resulting in a call to
f7649804 src/com/owncloud/android/files/services/FileUploader.java (Matthias Baumann  2013-02-25 08:21:33 +0100 174)      * this method. This ensures the service will keep on working although the
f7649804 src/com/owncloud/android/files/services/FileUploader.java (Matthias Baumann  2013-02-25 08:21:33 +0100 175)      * caller activity goes away.
68ce2e7a src/com/owncloud/android/files/services/FileUploader.java (David A. Velasco  2012-10-09 14:53:25 +0200 176)      */
435b31ba src/eu/alefzero/owncloud/files/services/FileUploader.java (Lennart Rosam     2012-05-16 09:48:34 +0200 177)     @Override
435b31ba src/eu/alefzero/owncloud/files/services/FileUploader.java (Lennart Rosam     2012-05-16 09:48:34 +0200 178)     public int onStartCommand(Intent intent, int flags, int startId) {
f7649804 src/com/owncloud/android/files/services/FileUploader.java (Matthias Baumann  2013-02-25 08:21:33 +0100 179)         if (!intent.hasExtra(KEY_ACCOUNT) || !intent.hasExtra(KEY_UPLOAD_TYPE)
f7649804 src/com/owncloud/android/files/services/FileUploader.java (Matthias Baumann  2013-02-25 08:21:33 +0100 180)                 || !(intent.hasExtra(KEY_LOCAL_FILE) || intent.hasExtra(KEY_FILE))) {
8e36e7cc src/com/owncloud/android/files/services/FileUploader.java (zerginator        2013-03-12 07:56:27 +0100 181)             Log_OC.e(TAG, "Not enough information provided in intent");
435b31ba src/eu/alefzero/owncloud/files/services/FileUploader.java (Lennart Rosam     2012-05-16 09:48:34 +0200 182)             return Service.START_NOT_STICKY;
435b31ba src/eu/alefzero/owncloud/files/services/FileUploader.java (Lennart Rosam     2012-05-16 09:48:34 +0200 183)         }
53b67429 src/com/owncloud/android/files/services/FileUploader.java (David A. Velasco  2012-09-12 16:16:56 +0200 184)         int uploadType = intent.getIntExtra(KEY_UPLOAD_TYPE, -1);
53b67429 src/com/owncloud/android/files/services/FileUploader.java (David A. Velasco  2012-09-12 16:16:56 +0200 185)         if (uploadType == -1) {
8e36e7cc src/com/owncloud/android/files/services/FileUploader.java (zerginator        2013-03-12 07:56:27 +0100 186)             Log_OC.e(TAG, "Incorrect upload type provided");
435b31ba src/eu/alefzero/owncloud/files/services/FileUploader.java (Lennart Rosam     2012-05-16 09:48:34 +0200 187)             return Service.START_NOT_STICKY;
435b31ba src/eu/alefzero/owncloud/files/services/FileUploader.java (Lennart Rosam     2012-05-16 09:48:34 +0200 188)         }
5fc7cd13 src/com/owncloud/android/files/services/FileUploader.java (David A. Velasco  2012-10-11 11:24:17 +0200 189)         Account account = intent.getParcelableExtra(KEY_ACCOUNT);
f7649804 src/com/owncloud/android/files/services/FileUploader.java (Matthias Baumann  2013-02-25 08:21:33 +0100 190) 
c38a3b2e src/com/owncloud/android/files/services/FileUploader.java (David A. Velasco  2012-12-18 11:20:03 +0100 191)         String[] localPaths = null, remotePaths = null, mimeTypes = null;
c38a3b2e src/com/owncloud/android/files/services/FileUploader.java (David A. Velasco  2012-12-18 11:20:03 +0100 192)         OCFile[] files = null;
53b67429 src/com/owncloud/android/files/services/FileUploader.java (David A. Velasco  2012-09-12 16:16:56 +0200 193)         if (uploadType == UPLOAD_SINGLE_FILE) {
f7649804 src/com/owncloud/android/files/services/FileUploader.java (Matthias Baumann  2013-02-25 08:21:33 +0100 194) 
c38a3b2e src/com/owncloud/android/files/services/FileUploader.java (David A. Velasco  2012-12-18 11:20:03 +0100 195)             if (intent.hasExtra(KEY_FILE)) {
f7649804 src/com/owncloud/android/files/services/FileUploader.java (Matthias Baumann  2013-02-25 08:21:33 +0100 196)                 files = new OCFile[] { intent.getParcelableExtra(KEY_FILE) };
f7649804 src/com/owncloud/android/files/services/FileUploader.java (Matthias Baumann  2013-02-25 08:21:33 +0100 197) 
c38a3b2e src/com/owncloud/android/files/services/FileUploader.java (David A. Velasco  2012-12-18 11:20:03 +0100 198)             } else {
c38a3b2e src/com/owncloud/android/files/services/FileUploader.java (David A. Velasco  2012-12-18 11:20:03 +0100 199)                 localPaths = new String[] { intent.getStringExtra(KEY_LOCAL_FILE) };
c38a3b2e src/com/owncloud/android/files/services/FileUploader.java (David A. Velasco  2012-12-18 11:20:03 +0100 200)                 remotePaths = new String[] { intent.getStringExtra(KEY_REMOTE_FILE) };
c38a3b2e src/com/owncloud/android/files/services/FileUploader.java (David A. Velasco  2012-12-18 11:20:03 +0100 201)                 mimeTypes = new String[] { intent.getStringExtra(KEY_MIME_TYPE) };
c38a3b2e src/com/owncloud/android/files/services/FileUploader.java (David A. Velasco  2012-12-18 11:20:03 +0100 202)             }
f7649804 src/com/owncloud/android/files/services/FileUploader.java (Matthias Baumann  2013-02-25 08:21:33 +0100 203) 
435b31ba src/eu/alefzero/owncloud/files/services/FileUploader.java (Lennart Rosam     2012-05-16 09:48:34 +0200 204)         } else { // mUploadType == UPLOAD_MULTIPLE_FILES
f7649804 src/com/owncloud/android/files/services/FileUploader.java (Matthias Baumann  2013-02-25 08:21:33 +0100 205) 
c38a3b2e src/com/owncloud/android/files/services/FileUploader.java (David A. Velasco  2012-12-18 11:20:03 +0100 206)             if (intent.hasExtra(KEY_FILE)) {
f7649804 src/com/owncloud/android/files/services/FileUploader.java (Matthias Baumann  2013-02-25 08:21:33 +0100 207)                 files = (OCFile[]) intent.getParcelableArrayExtra(KEY_FILE); // TODO
f7649804 src/com/owncloud/android/files/services/FileUploader.java (Matthias Baumann  2013-02-25 08:21:33 +0100 208)                                                                              // will
f7649804 src/com/owncloud/android/files/services/FileUploader.java (Matthias Baumann  2013-02-25 08:21:33 +0100 209)                                                                              // this
f7649804 src/com/owncloud/android/files/services/FileUploader.java (Matthias Baumann  2013-02-25 08:21:33 +0100 210)                                                                              // casting
f7649804 src/com/owncloud/android/files/services/FileUploader.java (Matthias Baumann  2013-02-25 08:21:33 +0100 211)                                                                              // work
f7649804 src/com/owncloud/android/files/services/FileUploader.java (Matthias Baumann  2013-02-25 08:21:33 +0100 212)                                                                              // fine?
f7649804 src/com/owncloud/android/files/services/FileUploader.java (Matthias Baumann  2013-02-25 08:21:33 +0100 213) 
c38a3b2e src/com/owncloud/android/files/services/FileUploader.java (David A. Velasco  2012-12-18 11:20:03 +0100 214)             } else {
c38a3b2e src/com/owncloud/android/files/services/FileUploader.java (David A. Velasco  2012-12-18 11:20:03 +0100 215)                 localPaths = intent.getStringArrayExtra(KEY_LOCAL_FILE);
c38a3b2e src/com/owncloud/android/files/services/FileUploader.java (David A. Velasco  2012-12-18 11:20:03 +0100 216)                 remotePaths = intent.getStringArrayExtra(KEY_REMOTE_FILE);
c38a3b2e src/com/owncloud/android/files/services/FileUploader.java (David A. Velasco  2012-12-18 11:20:03 +0100 217)                 mimeTypes = intent.getStringArrayExtra(KEY_MIME_TYPE);
c38a3b2e src/com/owncloud/android/files/services/FileUploader.java (David A. Velasco  2012-12-18 11:20:03 +0100 218)             }
435b31ba src/eu/alefzero/owncloud/files/services/FileUploader.java (Lennart Rosam     2012-05-16 09:48:34 +0200 219)         }
5fc7cd13 src/com/owncloud/android/files/services/FileUploader.java (David A. Velasco  2012-10-11 11:24:17 +0200 220) 
c38a3b2e src/com/owncloud/android/files/services/FileUploader.java (David A. Velasco  2012-12-18 11:20:03 +0100 221)         FileDataStorageManager storageManager = new FileDataStorageManager(account, getContentResolver());
f7649804 src/com/owncloud/android/files/services/FileUploader.java (Matthias Baumann  2013-02-25 08:21:33 +0100 222) 
c38a3b2e src/com/owncloud/android/files/services/FileUploader.java (David A. Velasco  2012-12-18 11:20:03 +0100 223)         boolean forceOverwrite = intent.getBooleanExtra(KEY_FORCE_OVERWRITE, false);
c38a3b2e src/com/owncloud/android/files/services/FileUploader.java (David A. Velasco  2012-12-18 11:20:03 +0100 224)         boolean isInstant = intent.getBooleanExtra(KEY_INSTANT_UPLOAD, false);
c38a3b2e src/com/owncloud/android/files/services/FileUploader.java (David A. Velasco  2012-12-18 11:20:03 +0100 225)         int localAction = intent.getIntExtra(KEY_LOCAL_BEHAVIOUR, LOCAL_BEHAVIOUR_COPY);
c38a3b2e src/com/owncloud/android/files/services/FileUploader.java (David A. Velasco  2012-12-18 11:20:03 +0100 226)         boolean fixed = false;
c38a3b2e src/com/owncloud/android/files/services/FileUploader.java (David A. Velasco  2012-12-18 11:20:03 +0100 227)         if (isInstant) {
f7649804 src/com/owncloud/android/files/services/FileUploader.java (Matthias Baumann  2013-02-25 08:21:33 +0100 228)             fixed = checkAndFixInstantUploadDirectory(storageManager); // MUST
f7649804 src/com/owncloud/android/files/services/FileUploader.java (Matthias Baumann  2013-02-25 08:21:33 +0100 229)                                                                        // be
f7649804 src/com/owncloud/android/files/services/FileUploader.java (Matthias Baumann  2013-02-25 08:21:33 +0100 230)                                                                        // done
f7649804 src/com/owncloud/android/files/services/FileUploader.java (Matthias Baumann  2013-02-25 08:21:33 +0100 231)                                                                        // BEFORE
f7649804 src/com/owncloud/android/files/services/FileUploader.java (Matthias Baumann  2013-02-25 08:21:33 +0100 232)                                                                        // calling
f7649804 src/com/owncloud/android/files/services/FileUploader.java (Matthias Baumann  2013-02-25 08:21:33 +0100 233)                                                                        // obtainNewOCFileToUpload
5fc7cd13 src/com/owncloud/android/files/services/FileUploader.java (David A. Velasco  2012-10-11 11:24:17 +0200 234)         }
f7649804 src/com/owncloud/android/files/services/FileUploader.java (Matthias Baumann  2013-02-25 08:21:33 +0100 235) 
c38a3b2e src/com/owncloud/android/files/services/FileUploader.java (David A. Velasco  2012-12-18 11:20:03 +0100 236)         if (intent.hasExtra(KEY_FILE) && files == null) {
8e36e7cc src/com/owncloud/android/files/services/FileUploader.java (zerginator        2013-03-12 07:56:27 +0100 237)             Log_OC.e(TAG, "Incorrect array for OCFiles provided in upload intent");
5fc7cd13 src/com/owncloud/android/files/services/FileUploader.java (David A. Velasco  2012-10-11 11:24:17 +0200 238)             return Service.START_NOT_STICKY;
f7649804 src/com/owncloud/android/files/services/FileUploader.java (Matthias Baumann  2013-02-25 08:21:33 +0100 239) 
c38a3b2e src/com/owncloud/android/files/services/FileUploader.java (David A. Velasco  2012-12-18 11:20:03 +0100 240)         } else if (!intent.hasExtra(KEY_FILE)) {
c38a3b2e src/com/owncloud/android/files/services/FileUploader.java (David A. Velasco  2012-12-18 11:20:03 +0100 241)             if (localPaths == null) {
8e36e7cc src/com/owncloud/android/files/services/FileUploader.java (zerginator        2013-03-12 07:56:27 +0100 242)                 Log_OC.e(TAG, "Incorrect array for local paths provided in upload intent");
c38a3b2e src/com/owncloud/android/files/services/FileUploader.java (David A. Velasco  2012-12-18 11:20:03 +0100 243)                 return Service.START_NOT_STICKY;
c38a3b2e src/com/owncloud/android/files/services/FileUploader.java (David A. Velasco  2012-12-18 11:20:03 +0100 244)             }
c38a3b2e src/com/owncloud/android/files/services/FileUploader.java (David A. Velasco  2012-12-18 11:20:03 +0100 245)             if (remotePaths == null) {
8e36e7cc src/com/owncloud/android/files/services/FileUploader.java (zerginator        2013-03-12 07:56:27 +0100 246)                 Log_OC.e(TAG, "Incorrect array for remote paths provided in upload intent");
c38a3b2e src/com/owncloud/android/files/services/FileUploader.java (David A. Velasco  2012-12-18 11:20:03 +0100 247)                 return Service.START_NOT_STICKY;
c38a3b2e src/com/owncloud/android/files/services/FileUploader.java (David A. Velasco  2012-12-18 11:20:03 +0100 248)             }
c38a3b2e src/com/owncloud/android/files/services/FileUploader.java (David A. Velasco  2012-12-18 11:20:03 +0100 249)             if (localPaths.length != remotePaths.length) {
8e36e7cc src/com/owncloud/android/files/services/FileUploader.java (zerginator        2013-03-12 07:56:27 +0100 250)                 Log_OC.e(TAG, "Different number of remote paths and local paths!");
c38a3b2e src/com/owncloud/android/files/services/FileUploader.java (David A. Velasco  2012-12-18 11:20:03 +0100 251)                 return Service.START_NOT_STICKY;
c38a3b2e src/com/owncloud/android/files/services/FileUploader.java (David A. Velasco  2012-12-18 11:20:03 +0100 252)             }
f7649804 src/com/owncloud/android/files/services/FileUploader.java (Matthias Baumann  2013-02-25 08:21:33 +0100 253) 
c38a3b2e src/com/owncloud/android/files/services/FileUploader.java (David A. Velasco  2012-12-18 11:20:03 +0100 254)             files = new OCFile[localPaths.length];
f7649804 src/com/owncloud/android/files/services/FileUploader.java (Matthias Baumann  2013-02-25 08:21:33 +0100 255)             for (int i = 0; i < localPaths.length; i++) {
f7649804 src/com/owncloud/android/files/services/FileUploader.java (Matthias Baumann  2013-02-25 08:21:33 +0100 256)                 files[i] = obtainNewOCFileToUpload(remotePaths[i], localPaths[i], ((mimeTypes != null) ? mimeTypes[i]
f7649804 src/com/owncloud/android/files/services/FileUploader.java (Matthias Baumann  2013-02-25 08:21:33 +0100 257)                         : (String) null), storageManager);
f7649804 src/com/owncloud/android/files/services/FileUploader.java (Matthias Baumann  2013-02-25 08:21:33 +0100 258)                 if (files[i] == null) {
bdc0332c src/com/owncloud/android/files/services/FileUploader.java (David A. Velasco  2013-04-17 12:26:13 +0200 259)                     // TODO @andomaex add failure Notiification
f7649804 src/com/owncloud/android/files/services/FileUploader.java (Matthias Baumann  2013-02-25 08:21:33 +0100 260)                     return Service.START_NOT_STICKY;
f7649804 src/com/owncloud/android/files/services/FileUploader.java (Matthias Baumann  2013-02-25 08:21:33 +0100 261)                 }
c38a3b2e src/com/owncloud/android/files/services/FileUploader.java (David A. Velasco  2012-12-18 11:20:03 +0100 262)             }
435b31ba src/eu/alefzero/owncloud/files/services/FileUploader.java (Lennart Rosam     2012-05-16 09:48:34 +0200 263)         }
f7649804 src/com/owncloud/android/files/services/FileUploader.java (Matthias Baumann  2013-02-25 08:21:33 +0100 264) 
f7649804 src/com/owncloud/android/files/services/FileUploader.java (Matthias Baumann  2013-02-25 08:21:33 +0100 265)         OwnCloudVersion ocv = new OwnCloudVersion(AccountManager.get(this).getUserData(account,
f7649804 src/com/owncloud/android/files/services/FileUploader.java (Matthias Baumann  2013-02-25 08:21:33 +0100 266)                 AccountAuthenticator.KEY_OC_VERSION));
5fc7cd13 src/com/owncloud/android/files/services/FileUploader.java (David A. Velasco  2012-10-11 11:24:17 +0200 267)         boolean chunked = FileUploader.chunkedUploadIsSupported(ocv);
5fc7cd13 src/com/owncloud/android/files/services/FileUploader.java (David A. Velasco  2012-10-11 11:24:17 +0200 268)         AbstractList<String> requestedUploads = new Vector<String>();
5fc7cd13 src/com/owncloud/android/files/services/FileUploader.java (David A. Velasco  2012-10-11 11:24:17 +0200 269)         String uploadKey = null;
5fc7cd13 src/com/owncloud/android/files/services/FileUploader.java (David A. Velasco  2012-10-11 11:24:17 +0200 270)         UploadFileOperation newUpload = null;
5fc7cd13 src/com/owncloud/android/files/services/FileUploader.java (David A. Velasco  2012-10-11 11:24:17 +0200 271)         try {
f7649804 src/com/owncloud/android/files/services/FileUploader.java (Matthias Baumann  2013-02-25 08:21:33 +0100 272)             for (int i = 0; i < files.length; i++) {
c38a3b2e src/com/owncloud/android/files/services/FileUploader.java (David A. Velasco  2012-12-18 11:20:03 +0100 273)                 uploadKey = buildRemoteName(account, files[i].getRemotePath());
5fc7cd13 src/com/owncloud/android/files/services/FileUploader.java (David A. Velasco  2012-10-11 11:24:17 +0200 274)                 if (chunked) {
f7649804 src/com/owncloud/android/files/services/FileUploader.java (Matthias Baumann  2013-02-25 08:21:33 +0100 275)                     newUpload = new ChunkedUploadFileOperation(account, files[i], isInstant, forceOverwrite,
f7649804 src/com/owncloud/android/files/services/FileUploader.java (Matthias Baumann  2013-02-25 08:21:33 +0100 276)                             localAction);
5fc7cd13 src/com/owncloud/android/files/services/FileUploader.java (David A. Velasco  2012-10-11 11:24:17 +0200 277)                 } else {
c38a3b2e src/com/owncloud/android/files/services/FileUploader.java (David A. Velasco  2012-12-18 11:20:03 +0100 278)                     newUpload = new UploadFileOperation(account, files[i], isInstant, forceOverwrite, localAction);
5fc7cd13 src/com/owncloud/android/files/services/FileUploader.java (David A. Velasco  2012-10-11 11:24:17 +0200 279)                 }
f7649804 src/com/owncloud/android/files/services/FileUploader.java (Matthias Baumann  2013-02-25 08:21:33 +0100 280)                 if (fixed && i == 0) {
cf27417e src/com/owncloud/android/files/services/FileUploader.java (David A. Velasco  2012-10-15 15:22:07 +0200 281)                     newUpload.setRemoteFolderToBeCreated();
cf27417e src/com/owncloud/android/files/services/FileUploader.java (David A. Velasco  2012-10-15 15:22:07 +0200 282)                 }
5fc7cd13 src/com/owncloud/android/files/services/FileUploader.java (David A. Velasco  2012-10-11 11:24:17 +0200 283)                 mPendingUploads.putIfAbsent(uploadKey, newUpload);
5fc7cd13 src/com/owncloud/android/files/services/FileUploader.java (David A. Velasco  2012-10-11 11:24:17 +0200 284)                 newUpload.addDatatransferProgressListener(this);
cfb68a69 src/com/owncloud/android/files/services/FileUploader.java (David A. Velasco  2013-02-20 12:34:11 +0100 285)                 newUpload.addDatatransferProgressListener((FileUploaderBinder)mBinder);
5fc7cd13 src/com/owncloud/android/files/services/FileUploader.java (David A. Velasco  2012-10-11 11:24:17 +0200 286)                 requestedUploads.add(uploadKey);
53b67429 src/com/owncloud/android/files/services/FileUploader.java (David A. Velasco  2012-09-12 16:16:56 +0200 287)             }
f7649804 src/com/owncloud/android/files/services/FileUploader.java (Matthias Baumann  2013-02-25 08:21:33 +0100 288) 
5fc7cd13 src/com/owncloud/android/files/services/FileUploader.java (David A. Velasco  2012-10-11 11:24:17 +0200 289)         } catch (IllegalArgumentException e) {
8e36e7cc src/com/owncloud/android/files/services/FileUploader.java (zerginator        2013-03-12 07:56:27 +0100 290)             Log_OC.e(TAG, "Not enough information provided in intent: " + e.getMessage());
5fc7cd13 src/com/owncloud/android/files/services/FileUploader.java (David A. Velasco  2012-10-11 11:24:17 +0200 291)             return START_NOT_STICKY;
f7649804 src/com/owncloud/android/files/services/FileUploader.java (Matthias Baumann  2013-02-25 08:21:33 +0100 292) 
cf27417e src/com/owncloud/android/files/services/FileUploader.java (David A. Velasco  2012-10-15 15:22:07 +0200 293)         } catch (IllegalStateException e) {
8e36e7cc src/com/owncloud/android/files/services/FileUploader.java (zerginator        2013-03-12 07:56:27 +0100 294)             Log_OC.e(TAG, "Bad information provided in intent: " + e.getMessage());
cf27417e src/com/owncloud/android/files/services/FileUploader.java (David A. Velasco  2012-10-15 15:22:07 +0200 295)             return START_NOT_STICKY;
f7649804 src/com/owncloud/android/files/services/FileUploader.java (Matthias Baumann  2013-02-25 08:21:33 +0100 296) 
cf27417e src/com/owncloud/android/files/services/FileUploader.java (David A. Velasco  2012-10-15 15:22:07 +0200 297)         } catch (Exception e) {
8e36e7cc src/com/owncloud/android/files/services/FileUploader.java (zerginator        2013-03-12 07:56:27 +0100 298)             Log_OC.e(TAG, "Unexpected exception while processing upload intent", e);
cf27417e src/com/owncloud/android/files/services/FileUploader.java (David A. Velasco  2012-10-15 15:22:07 +0200 299)             return START_NOT_STICKY;
f7649804 src/com/owncloud/android/files/services/FileUploader.java (Matthias Baumann  2013-02-25 08:21:33 +0100 300) 
5fc7cd13 src/com/owncloud/android/files/services/FileUploader.java (David A. Velasco  2012-10-11 11:24:17 +0200 301)         }
f7649804 src/com/owncloud/android/files/services/FileUploader.java (Matthias Baumann  2013-02-25 08:21:33 +0100 302) 
5fc7cd13 src/com/owncloud/android/files/services/FileUploader.java (David A. Velasco  2012-10-11 11:24:17 +0200 303)         if (requestedUploads.size() > 0) {
5fc7cd13 src/com/owncloud/android/files/services/FileUploader.java (David A. Velasco  2012-10-11 11:24:17 +0200 304)             Message msg = mServiceHandler.obtainMessage();
5fc7cd13 src/com/owncloud/android/files/services/FileUploader.java (David A. Velasco  2012-10-11 11:24:17 +0200 305)             msg.arg1 = startId;
5fc7cd13 src/com/owncloud/android/files/services/FileUploader.java (David A. Velasco  2012-10-11 11:24:17 +0200 306)             msg.obj = requestedUploads;
5fc7cd13 src/com/owncloud/android/files/services/FileUploader.java (David A. Velasco  2012-10-11 11:24:17 +0200 307)             mServiceHandler.sendMessage(msg);
53b67429 src/com/owncloud/android/files/services/FileUploader.java (David A. Velasco  2012-09-12 16:16:56 +0200 308)         }
8e36e7cc src/com/owncloud/android/files/services/FileUploader.java (zerginator        2013-03-12 07:56:27 +0100 309)         Log_OC.i(TAG, "mPendingUploads size:" + mPendingUploads.size());
435b31ba src/eu/alefzero/owncloud/files/services/FileUploader.java (Lennart Rosam     2012-05-16 09:48:34 +0200 310)         return Service.START_NOT_STICKY;
b999f542 src/eu/alefzero/owncloud/files/services/FileUploader.java (Bartek Przybylski 2012-05-15 21:52:59 +0200 311)     }
435b31ba src/eu/alefzero/owncloud/files/services/FileUploader.java (Lennart Rosam     2012-05-16 09:48:34 +0200 312) 
f3ba1075 src/eu/alefzero/owncloud/files/services/FileUploader.java (David A. Velasco  2012-07-26 18:35:09 +0200 313)     /**
f7649804 src/com/owncloud/android/files/services/FileUploader.java (Matthias Baumann  2013-02-25 08:21:33 +0100 314)      * Provides a binder object that clients can use to perform operations on
f7649804 src/com/owncloud/android/files/services/FileUploader.java (Matthias Baumann  2013-02-25 08:21:33 +0100 315)      * the queue of uploads, excepting the addition of new files.
68ce2e7a src/com/owncloud/android/files/services/FileUploader.java (David A. Velasco  2012-10-09 14:53:25 +0200 316)      * 
f7649804 src/com/owncloud/android/files/services/FileUploader.java (Matthias Baumann  2013-02-25 08:21:33 +0100 317)      * Implemented to perform cancellation, pause and resume of existing
f7649804 src/com/owncloud/android/files/services/FileUploader.java (Matthias Baumann  2013-02-25 08:21:33 +0100 318)      * uploads.
68ce2e7a src/com/owncloud/android/files/services/FileUploader.java (David A. Velasco  2012-10-09 14:53:25 +0200 319)      */
68ce2e7a src/com/owncloud/android/files/services/FileUploader.java (David A. Velasco  2012-10-09 14:53:25 +0200 320)     @Override
68ce2e7a src/com/owncloud/android/files/services/FileUploader.java (David A. Velasco  2012-10-09 14:53:25 +0200 321)     public IBinder onBind(Intent arg0) {
5fc7cd13 src/com/owncloud/android/files/services/FileUploader.java (David A. Velasco  2012-10-11 11:24:17 +0200 322)         return mBinder;
68ce2e7a src/com/owncloud/android/files/services/FileUploader.java (David A. Velasco  2012-10-09 14:53:25 +0200 323)     }
cfb68a69 src/com/owncloud/android/files/services/FileUploader.java (David A. Velasco  2013-02-20 12:34:11 +0100 324)     
cfb68a69 src/com/owncloud/android/files/services/FileUploader.java (David A. Velasco  2013-02-20 12:34:11 +0100 325)     /**
cfb68a69 src/com/owncloud/android/files/services/FileUploader.java (David A. Velasco  2013-02-20 12:34:11 +0100 326)      * Called when ALL the bound clients were onbound.
cfb68a69 src/com/owncloud/android/files/services/FileUploader.java (David A. Velasco  2013-02-20 12:34:11 +0100 327)      */
cfb68a69 src/com/owncloud/android/files/services/FileUploader.java (David A. Velasco  2013-02-20 12:34:11 +0100 328)     @Override
cfb68a69 src/com/owncloud/android/files/services/FileUploader.java (David A. Velasco  2013-02-20 12:34:11 +0100 329)     public boolean onUnbind(Intent intent) {
bcc7bd27 src/com/owncloud/android/files/services/FileUploader.java (David A. Velasco  2013-02-21 09:57:23 +0100 330)         ((FileUploaderBinder)mBinder).clearListeners();
cfb68a69 src/com/owncloud/android/files/services/FileUploader.java (David A. Velasco  2013-02-20 12:34:11 +0100 331)         return false;   // not accepting rebinding (default behaviour)
cfb68a69 src/com/owncloud/android/files/services/FileUploader.java (David A. Velasco  2013-02-20 12:34:11 +0100 332)     }
cfb68a69 src/com/owncloud/android/files/services/FileUploader.java (David A. Velasco  2013-02-20 12:34:11 +0100 333)     
68ce2e7a src/com/owncloud/android/files/services/FileUploader.java (David A. Velasco  2012-10-09 14:53:25 +0200 334) 
5fc7cd13 src/com/owncloud/android/files/services/FileUploader.java (David A. Velasco  2012-10-11 11:24:17 +0200 335)     /**
f7649804 src/com/owncloud/android/files/services/FileUploader.java (Matthias Baumann  2013-02-25 08:21:33 +0100 336)      * Binder to let client components to perform operations on the queue of
f7649804 src/com/owncloud/android/files/services/FileUploader.java (Matthias Baumann  2013-02-25 08:21:33 +0100 337)      * uploads.
5fc7cd13 src/com/owncloud/android/files/services/FileUploader.java (David A. Velasco  2012-10-11 11:24:17 +0200 338)      * 
f7649804 src/com/owncloud/android/files/services/FileUploader.java (Matthias Baumann  2013-02-25 08:21:33 +0100 339)      * It provides by itself the available operations.
5fc7cd13 src/com/owncloud/android/files/services/FileUploader.java (David A. Velasco  2012-10-11 11:24:17 +0200 340)      */
cfb68a69 src/com/owncloud/android/files/services/FileUploader.java (David A. Velasco  2013-02-20 12:34:11 +0100 341)     public class FileUploaderBinder extends Binder implements OnDatatransferProgressListener {
cfb68a69 src/com/owncloud/android/files/services/FileUploader.java (David A. Velasco  2013-02-20 12:34:11 +0100 342)         
cfb68a69 src/com/owncloud/android/files/services/FileUploader.java (David A. Velasco  2013-02-20 12:34:11 +0100 343)         /** 
bcc7bd27 src/com/owncloud/android/files/services/FileUploader.java (David A. Velasco  2013-02-21 09:57:23 +0100 344)          * Map of listeners that will be reported about progress of uploads from a {@link FileUploaderBinder} instance 
cfb68a69 src/com/owncloud/android/files/services/FileUploader.java (David A. Velasco  2013-02-20 12:34:11 +0100 345)          */
cfb68a69 src/com/owncloud/android/files/services/FileUploader.java (David A. Velasco  2013-02-20 12:34:11 +0100 346)         private Map<String, OnDatatransferProgressListener> mBoundListeners = new HashMap<String, OnDatatransferProgressListener>();
5fc7cd13 src/com/owncloud/android/files/services/FileUploader.java (David A. Velasco  2012-10-11 11:24:17 +0200 347)         
5fc7cd13 src/com/owncloud/android/files/services/FileUploader.java (David A. Velasco  2012-10-11 11:24:17 +0200 348)         /**
5fc7cd13 src/com/owncloud/android/files/services/FileUploader.java (David A. Velasco  2012-10-11 11:24:17 +0200 349)          * Cancels a pending or current upload of a remote file.
5fc7cd13 src/com/owncloud/android/files/services/FileUploader.java (David A. Velasco  2012-10-11 11:24:17 +0200 350)          * 
f7649804 src/com/owncloud/android/files/services/FileUploader.java (Matthias Baumann  2013-02-25 08:21:33 +0100 351)          * @param account Owncloud account where the remote file will be stored.
f7649804 src/com/owncloud/android/files/services/FileUploader.java (Matthias Baumann  2013-02-25 08:21:33 +0100 352)          * @param file A file in the queue of pending uploads
5fc7cd13 src/com/owncloud/android/files/services/FileUploader.java (David A. Velasco  2012-10-11 11:24:17 +0200 353)          */
5fc7cd13 src/com/owncloud/android/files/services/FileUploader.java (David A. Velasco  2012-10-11 11:24:17 +0200 354)         public void cancel(Account account, OCFile file) {
5fc7cd13 src/com/owncloud/android/files/services/FileUploader.java (David A. Velasco  2012-10-11 11:24:17 +0200 355)             UploadFileOperation upload = null;
5fc7cd13 src/com/owncloud/android/files/services/FileUploader.java (David A. Velasco  2012-10-11 11:24:17 +0200 356)             synchronized (mPendingUploads) {
5fc7cd13 src/com/owncloud/android/files/services/FileUploader.java (David A. Velasco  2012-10-11 11:24:17 +0200 357)                 upload = mPendingUploads.remove(buildRemoteName(account, file));
5fc7cd13 src/com/owncloud/android/files/services/FileUploader.java (David A. Velasco  2012-10-11 11:24:17 +0200 358)             }
5fc7cd13 src/com/owncloud/android/files/services/FileUploader.java (David A. Velasco  2012-10-11 11:24:17 +0200 359)             if (upload != null) {
27112bd2 src/com/owncloud/android/files/services/FileUploader.java (David A. Velasco  2012-10-11 16:40:56 +0200 360)                 upload.cancel();
5fc7cd13 src/com/owncloud/android/files/services/FileUploader.java (David A. Velasco  2012-10-11 11:24:17 +0200 361)             }
5fc7cd13 src/com/owncloud/android/files/services/FileUploader.java (David A. Velasco  2012-10-11 11:24:17 +0200 362)         }
5fc7cd13 src/com/owncloud/android/files/services/FileUploader.java (David A. Velasco  2012-10-11 11:24:17 +0200 363)         
5fc7cd13 src/com/owncloud/android/files/services/FileUploader.java (David A. Velasco  2012-10-11 11:24:17 +0200 364)         
cfb68a69 src/com/owncloud/android/files/services/FileUploader.java (David A. Velasco  2013-02-20 12:34:11 +0100 365)         
cfb68a69 src/com/owncloud/android/files/services/FileUploader.java (David A. Velasco  2013-02-20 12:34:11 +0100 366)         public void clearListeners() {
cfb68a69 src/com/owncloud/android/files/services/FileUploader.java (David A. Velasco  2013-02-20 12:34:11 +0100 367)             mBoundListeners.clear();
cfb68a69 src/com/owncloud/android/files/services/FileUploader.java (David A. Velasco  2013-02-20 12:34:11 +0100 368)         }
f7649804 src/com/owncloud/android/files/services/FileUploader.java (Matthias Baumann  2013-02-25 08:21:33 +0100 369) 
cfb68a69 src/com/owncloud/android/files/services/FileUploader.java (David A. Velasco  2013-02-20 12:34:11 +0100 370) 
cfb68a69 src/com/owncloud/android/files/services/FileUploader.java (David A. Velasco  2013-02-20 12:34:11 +0100 371)         
cfb68a69 src/com/owncloud/android/files/services/FileUploader.java (David A. Velasco  2013-02-20 12:34:11 +0100 372)         
5fc7cd13 src/com/owncloud/android/files/services/FileUploader.java (David A. Velasco  2012-10-11 11:24:17 +0200 373)         /**
f7649804 src/com/owncloud/android/files/services/FileUploader.java (Matthias Baumann  2013-02-25 08:21:33 +0100 374)          * Returns True when the file described by 'file' is being uploaded to
f7649804 src/com/owncloud/android/files/services/FileUploader.java (Matthias Baumann  2013-02-25 08:21:33 +0100 375)          * the ownCloud account 'account' or waiting for it
5fc7cd13 src/com/owncloud/android/files/services/FileUploader.java (David A. Velasco  2012-10-11 11:24:17 +0200 376)          * 
bcc7bd27 src/com/owncloud/android/files/services/FileUploader.java (David A. Velasco  2013-02-21 09:57:23 +0100 377)          * If 'file' is a directory, returns 'true' if some of its descendant files is uploading or waiting to upload. 
c38a3b2e src/com/owncloud/android/files/services/FileUploader.java (David A. Velasco  2012-12-18 11:20:03 +0100 378)          * 
f7649804 src/com/owncloud/android/files/services/FileUploader.java (Matthias Baumann  2013-02-25 08:21:33 +0100 379)          * @param account Owncloud account where the remote file will be stored.
f7649804 src/com/owncloud/android/files/services/FileUploader.java (Matthias Baumann  2013-02-25 08:21:33 +0100 380)          * @param file A file that could be in the queue of pending uploads
5fc7cd13 src/com/owncloud/android/files/services/FileUploader.java (David A. Velasco  2012-10-11 11:24:17 +0200 381)          */
5fc7cd13 src/com/owncloud/android/files/services/FileUploader.java (David A. Velasco  2012-10-11 11:24:17 +0200 382)         public boolean isUploading(Account account, OCFile file) {
f7649804 src/com/owncloud/android/files/services/FileUploader.java (Matthias Baumann  2013-02-25 08:21:33 +0100 383)             if (account == null || file == null)
f7649804 src/com/owncloud/android/files/services/FileUploader.java (Matthias Baumann  2013-02-25 08:21:33 +0100 384)                 return false;
c38a3b2e src/com/owncloud/android/files/services/FileUploader.java (David A. Velasco  2012-12-18 11:20:03 +0100 385)             String targetKey = buildRemoteName(account, file);
5fc7cd13 src/com/owncloud/android/files/services/FileUploader.java (David A. Velasco  2012-10-11 11:24:17 +0200 386)             synchronized (mPendingUploads) {
c38a3b2e src/com/owncloud/android/files/services/FileUploader.java (David A. Velasco  2012-12-18 11:20:03 +0100 387)                 if (file.isDirectory()) {
bcc7bd27 src/com/owncloud/android/files/services/FileUploader.java (David A. Velasco  2013-02-21 09:57:23 +0100 388)                     // this can be slow if there are many uploads :(
c38a3b2e src/com/owncloud/android/files/services/FileUploader.java (David A. Velasco  2012-12-18 11:20:03 +0100 389)                     Iterator<String> it = mPendingUploads.keySet().iterator();
c38a3b2e src/com/owncloud/android/files/services/FileUploader.java (David A. Velasco  2012-12-18 11:20:03 +0100 390)                     boolean found = false;
c38a3b2e src/com/owncloud/android/files/services/FileUploader.java (David A. Velasco  2012-12-18 11:20:03 +0100 391)                     while (it.hasNext() && !found) {
c38a3b2e src/com/owncloud/android/files/services/FileUploader.java (David A. Velasco  2012-12-18 11:20:03 +0100 392)                         found = it.next().startsWith(targetKey);
c38a3b2e src/com/owncloud/android/files/services/FileUploader.java (David A. Velasco  2012-12-18 11:20:03 +0100 393)                     }
c38a3b2e src/com/owncloud/android/files/services/FileUploader.java (David A. Velasco  2012-12-18 11:20:03 +0100 394)                     return found;
c38a3b2e src/com/owncloud/android/files/services/FileUploader.java (David A. Velasco  2012-12-18 11:20:03 +0100 395)                 } else {
c38a3b2e src/com/owncloud/android/files/services/FileUploader.java (David A. Velasco  2012-12-18 11:20:03 +0100 396)                     return (mPendingUploads.containsKey(targetKey));
c38a3b2e src/com/owncloud/android/files/services/FileUploader.java (David A. Velasco  2012-12-18 11:20:03 +0100 397)                 }
5fc7cd13 src/com/owncloud/android/files/services/FileUploader.java (David A. Velasco  2012-10-11 11:24:17 +0200 398)             }
5fc7cd13 src/com/owncloud/android/files/services/FileUploader.java (David A. Velasco  2012-10-11 11:24:17 +0200 399)         }
24dd5136 src/com/owncloud/android/files/services/FileUploader.java (David A. Velasco  2013-02-19 13:28:01 +0100 400) 
24dd5136 src/com/owncloud/android/files/services/FileUploader.java (David A. Velasco  2013-02-19 13:28:01 +0100 401) 
24dd5136 src/com/owncloud/android/files/services/FileUploader.java (David A. Velasco  2013-02-19 13:28:01 +0100 402)         /**
bcc7bd27 src/com/owncloud/android/files/services/FileUploader.java (David A. Velasco  2013-02-21 09:57:23 +0100 403)          * Adds a listener interested in the progress of the upload for a concrete file.
24dd5136 src/com/owncloud/android/files/services/FileUploader.java (David A. Velasco  2013-02-19 13:28:01 +0100 404)          * 
24dd5136 src/com/owncloud/android/files/services/FileUploader.java (David A. Velasco  2013-02-19 13:28:01 +0100 405)          * @param listener      Object to notify about progress of transfer.    
24dd5136 src/com/owncloud/android/files/services/FileUploader.java (David A. Velasco  2013-02-19 13:28:01 +0100 406)          * @param account       ownCloud account holding the file of interest.
24dd5136 src/com/owncloud/android/files/services/FileUploader.java (David A. Velasco  2013-02-19 13:28:01 +0100 407)          * @param file          {@link OCfile} of interest for listener. 
24dd5136 src/com/owncloud/android/files/services/FileUploader.java (David A. Velasco  2013-02-19 13:28:01 +0100 408)          */
24dd5136 src/com/owncloud/android/files/services/FileUploader.java (David A. Velasco  2013-02-19 13:28:01 +0100 409)         public void addDatatransferProgressListener (OnDatatransferProgressListener listener, Account account, OCFile file) {
cfb68a69 src/com/owncloud/android/files/services/FileUploader.java (David A. Velasco  2013-02-20 12:34:11 +0100 410)             if (account == null || file == null || listener == null) return;
24dd5136 src/com/owncloud/android/files/services/FileUploader.java (David A. Velasco  2013-02-19 13:28:01 +0100 411)             String targetKey = buildRemoteName(account, file);
cfb68a69 src/com/owncloud/android/files/services/FileUploader.java (David A. Velasco  2013-02-20 12:34:11 +0100 412)             mBoundListeners.put(targetKey, listener);
cfb68a69 src/com/owncloud/android/files/services/FileUploader.java (David A. Velasco  2013-02-20 12:34:11 +0100 413)         }
cfb68a69 src/com/owncloud/android/files/services/FileUploader.java (David A. Velasco  2013-02-20 12:34:11 +0100 414)         
cfb68a69 src/com/owncloud/android/files/services/FileUploader.java (David A. Velasco  2013-02-20 12:34:11 +0100 415)         
cfb68a69 src/com/owncloud/android/files/services/FileUploader.java (David A. Velasco  2013-02-20 12:34:11 +0100 416)         
cfb68a69 src/com/owncloud/android/files/services/FileUploader.java (David A. Velasco  2013-02-20 12:34:11 +0100 417)         /**
bcc7bd27 src/com/owncloud/android/files/services/FileUploader.java (David A. Velasco  2013-02-21 09:57:23 +0100 418)          * Removes a listener interested in the progress of the upload for a concrete file.
cfb68a69 src/com/owncloud/android/files/services/FileUploader.java (David A. Velasco  2013-02-20 12:34:11 +0100 419)          * 
cfb68a69 src/com/owncloud/android/files/services/FileUploader.java (David A. Velasco  2013-02-20 12:34:11 +0100 420)          * @param listener      Object to notify about progress of transfer.    
cfb68a69 src/com/owncloud/android/files/services/FileUploader.java (David A. Velasco  2013-02-20 12:34:11 +0100 421)          * @param account       ownCloud account holding the file of interest.
cfb68a69 src/com/owncloud/android/files/services/FileUploader.java (David A. Velasco  2013-02-20 12:34:11 +0100 422)          * @param file          {@link OCfile} of interest for listener. 
cfb68a69 src/com/owncloud/android/files/services/FileUploader.java (David A. Velasco  2013-02-20 12:34:11 +0100 423)          */
cfb68a69 src/com/owncloud/android/files/services/FileUploader.java (David A. Velasco  2013-02-20 12:34:11 +0100 424)         public void removeDatatransferProgressListener (OnDatatransferProgressListener listener, Account account, OCFile file) {
cfb68a69 src/com/owncloud/android/files/services/FileUploader.java (David A. Velasco  2013-02-20 12:34:11 +0100 425)             if (account == null || file == null || listener == null) return;
cfb68a69 src/com/owncloud/android/files/services/FileUploader.java (David A. Velasco  2013-02-20 12:34:11 +0100 426)             String targetKey = buildRemoteName(account, file);
cfb68a69 src/com/owncloud/android/files/services/FileUploader.java (David A. Velasco  2013-02-20 12:34:11 +0100 427)             if (mBoundListeners.get(targetKey) == listener) {
cfb68a69 src/com/owncloud/android/files/services/FileUploader.java (David A. Velasco  2013-02-20 12:34:11 +0100 428)                 mBoundListeners.remove(targetKey);
24dd5136 src/com/owncloud/android/files/services/FileUploader.java (David A. Velasco  2013-02-19 13:28:01 +0100 429)             }
cfb68a69 src/com/owncloud/android/files/services/FileUploader.java (David A. Velasco  2013-02-20 12:34:11 +0100 430)         }
cfb68a69 src/com/owncloud/android/files/services/FileUploader.java (David A. Velasco  2013-02-20 12:34:11 +0100 431) 
cfb68a69 src/com/owncloud/android/files/services/FileUploader.java (David A. Velasco  2013-02-20 12:34:11 +0100 432) 
cfb68a69 src/com/owncloud/android/files/services/FileUploader.java (David A. Velasco  2013-02-20 12:34:11 +0100 433)         @Override
cfb68a69 src/com/owncloud/android/files/services/FileUploader.java (David A. Velasco  2013-02-20 12:34:11 +0100 434)         public void onTransferProgress(long progressRate) {
cfb68a69 src/com/owncloud/android/files/services/FileUploader.java (David A. Velasco  2013-02-20 12:34:11 +0100 435)             // old way, should not be in use any more
cfb68a69 src/com/owncloud/android/files/services/FileUploader.java (David A. Velasco  2013-02-20 12:34:11 +0100 436)         }
cfb68a69 src/com/owncloud/android/files/services/FileUploader.java (David A. Velasco  2013-02-20 12:34:11 +0100 437) 
cfb68a69 src/com/owncloud/android/files/services/FileUploader.java (David A. Velasco  2013-02-20 12:34:11 +0100 438) 
cfb68a69 src/com/owncloud/android/files/services/FileUploader.java (David A. Velasco  2013-02-20 12:34:11 +0100 439)         @Override
cfb68a69 src/com/owncloud/android/files/services/FileUploader.java (David A. Velasco  2013-02-20 12:34:11 +0100 440)         public void onTransferProgress(long progressRate, long totalTransferredSoFar, long totalToTransfer,
cfb68a69 src/com/owncloud/android/files/services/FileUploader.java (David A. Velasco  2013-02-20 12:34:11 +0100 441)                 String fileName) {
cfb68a69 src/com/owncloud/android/files/services/FileUploader.java (David A. Velasco  2013-02-20 12:34:11 +0100 442)             String key = buildRemoteName(mCurrentUpload.getAccount(), mCurrentUpload.getFile());
cfb68a69 src/com/owncloud/android/files/services/FileUploader.java (David A. Velasco  2013-02-20 12:34:11 +0100 443)             OnDatatransferProgressListener boundListener = mBoundListeners.get(key);
cfb68a69 src/com/owncloud/android/files/services/FileUploader.java (David A. Velasco  2013-02-20 12:34:11 +0100 444)             if (boundListener != null) {
cfb68a69 src/com/owncloud/android/files/services/FileUploader.java (David A. Velasco  2013-02-20 12:34:11 +0100 445)                 boundListener.onTransferProgress(progressRate, totalTransferredSoFar, totalToTransfer, fileName);
24dd5136 src/com/owncloud/android/files/services/FileUploader.java (David A. Velasco  2013-02-19 13:28:01 +0100 446)             }
24dd5136 src/com/owncloud/android/files/services/FileUploader.java (David A. Velasco  2013-02-19 13:28:01 +0100 447)         }
24dd5136 src/com/owncloud/android/files/services/FileUploader.java (David A. Velasco  2013-02-19 13:28:01 +0100 448)         
5fc7cd13 src/com/owncloud/android/files/services/FileUploader.java (David A. Velasco  2012-10-11 11:24:17 +0200 449)     }
f7649804 src/com/owncloud/android/files/services/FileUploader.java (Matthias Baumann  2013-02-25 08:21:33 +0100 450) 
f7649804 src/com/owncloud/android/files/services/FileUploader.java (Matthias Baumann  2013-02-25 08:21:33 +0100 451)     /**
f7649804 src/com/owncloud/android/files/services/FileUploader.java (Matthias Baumann  2013-02-25 08:21:33 +0100 452)      * Upload worker. Performs the pending uploads in the order they were
f7649804 src/com/owncloud/android/files/services/FileUploader.java (Matthias Baumann  2013-02-25 08:21:33 +0100 453)      * requested.
68ce2e7a src/com/owncloud/android/files/services/FileUploader.java (David A. Velasco  2012-10-09 14:53:25 +0200 454)      * 
f7649804 src/com/owncloud/android/files/services/FileUploader.java (Matthias Baumann  2013-02-25 08:21:33 +0100 455)      * Created with the Looper of a new thread, started in
f7649804 src/com/owncloud/android/files/services/FileUploader.java (Matthias Baumann  2013-02-25 08:21:33 +0100 456)      * {@link FileUploader#onCreate()}.
68ce2e7a src/com/owncloud/android/files/services/FileUploader.java (David A. Velasco  2012-10-09 14:53:25 +0200 457)      */
5fc7cd13 src/com/owncloud/android/files/services/FileUploader.java (David A. Velasco  2012-10-11 11:24:17 +0200 458)     private static class ServiceHandler extends Handler {
f7649804 src/com/owncloud/android/files/services/FileUploader.java (Matthias Baumann  2013-02-25 08:21:33 +0100 459)         // don't make it a final class, and don't remove the static ; lint will
f7649804 src/com/owncloud/android/files/services/FileUploader.java (Matthias Baumann  2013-02-25 08:21:33 +0100 460)         // warn about a possible memory leak
5fc7cd13 src/com/owncloud/android/files/services/FileUploader.java (David A. Velasco  2012-10-11 11:24:17 +0200 461)         FileUploader mService;
f7649804 src/com/owncloud/android/files/services/FileUploader.java (Matthias Baumann  2013-02-25 08:21:33 +0100 462) 
5fc7cd13 src/com/owncloud/android/files/services/FileUploader.java (David A. Velasco  2012-10-11 11:24:17 +0200 463)         public ServiceHandler(Looper looper, FileUploader service) {
68ce2e7a src/com/owncloud/android/files/services/FileUploader.java (David A. Velasco  2012-10-09 14:53:25 +0200 464)             super(looper);
5fc7cd13 src/com/owncloud/android/files/services/FileUploader.java (David A. Velasco  2012-10-11 11:24:17 +0200 465)             if (service == null)
5fc7cd13 src/com/owncloud/android/files/services/FileUploader.java (David A. Velasco  2012-10-11 11:24:17 +0200 466)                 throw new IllegalArgumentException("Received invalid NULL in parameter 'service'");
5fc7cd13 src/com/owncloud/android/files/services/FileUploader.java (David A. Velasco  2012-10-11 11:24:17 +0200 467)             mService = service;
68ce2e7a src/com/owncloud/android/files/services/FileUploader.java (David A. Velasco  2012-10-09 14:53:25 +0200 468)         }
68ce2e7a src/com/owncloud/android/files/services/FileUploader.java (David A. Velasco  2012-10-09 14:53:25 +0200 469) 
68ce2e7a src/com/owncloud/android/files/services/FileUploader.java (David A. Velasco  2012-10-09 14:53:25 +0200 470)         @Override
68ce2e7a src/com/owncloud/android/files/services/FileUploader.java (David A. Velasco  2012-10-09 14:53:25 +0200 471)         public void handleMessage(Message msg) {
5fc7cd13 src/com/owncloud/android/files/services/FileUploader.java (David A. Velasco  2012-10-11 11:24:17 +0200 472)             @SuppressWarnings("unchecked")
5fc7cd13 src/com/owncloud/android/files/services/FileUploader.java (David A. Velasco  2012-10-11 11:24:17 +0200 473)             AbstractList<String> requestedUploads = (AbstractList<String>) msg.obj;
5fc7cd13 src/com/owncloud/android/files/services/FileUploader.java (David A. Velasco  2012-10-11 11:24:17 +0200 474)             if (msg.obj != null) {
5fc7cd13 src/com/owncloud/android/files/services/FileUploader.java (David A. Velasco  2012-10-11 11:24:17 +0200 475)                 Iterator<String> it = requestedUploads.iterator();
5fc7cd13 src/com/owncloud/android/files/services/FileUploader.java (David A. Velasco  2012-10-11 11:24:17 +0200 476)                 while (it.hasNext()) {
5fc7cd13 src/com/owncloud/android/files/services/FileUploader.java (David A. Velasco  2012-10-11 11:24:17 +0200 477)                     mService.uploadFile(it.next());
5fc7cd13 src/com/owncloud/android/files/services/FileUploader.java (David A. Velasco  2012-10-11 11:24:17 +0200 478)                 }
5fc7cd13 src/com/owncloud/android/files/services/FileUploader.java (David A. Velasco  2012-10-11 11:24:17 +0200 479)             }
5fc7cd13 src/com/owncloud/android/files/services/FileUploader.java (David A. Velasco  2012-10-11 11:24:17 +0200 480)             mService.stopSelf(msg.arg1);
68ce2e7a src/com/owncloud/android/files/services/FileUploader.java (David A. Velasco  2012-10-09 14:53:25 +0200 481)         }
68ce2e7a src/com/owncloud/android/files/services/FileUploader.java (David A. Velasco  2012-10-09 14:53:25 +0200 482)     }
68ce2e7a src/com/owncloud/android/files/services/FileUploader.java (David A. Velasco  2012-10-09 14:53:25 +0200 483) 
68ce2e7a src/com/owncloud/android/files/services/FileUploader.java (David A. Velasco  2012-10-09 14:53:25 +0200 484)     /**
f3ba1075 src/eu/alefzero/owncloud/files/services/FileUploader.java (David A. Velasco  2012-07-26 18:35:09 +0200 485)      * Core upload method: sends the file(s) to upload
5fc7cd13 src/com/owncloud/android/files/services/FileUploader.java (David A. Velasco  2012-10-11 11:24:17 +0200 486)      * 
f7649804 src/com/owncloud/android/files/services/FileUploader.java (Matthias Baumann  2013-02-25 08:21:33 +0100 487)      * @param uploadKey Key to access the upload to perform, contained in
f7649804 src/com/owncloud/android/files/services/FileUploader.java (Matthias Baumann  2013-02-25 08:21:33 +0100 488)      *            mPendingUploads
f3ba1075 src/eu/alefzero/owncloud/files/services/FileUploader.java (David A. Velasco  2012-07-26 18:35:09 +0200 489)      */
5fc7cd13 src/com/owncloud/android/files/services/FileUploader.java (David A. Velasco  2012-10-11 11:24:17 +0200 490)     public void uploadFile(String uploadKey) {
f3ba1075 src/eu/alefzero/owncloud/files/services/FileUploader.java (David A. Velasco  2012-07-26 18:35:09 +0200 491) 
f7649804 src/com/owncloud/android/files/services/FileUploader.java (Matthias Baumann  2013-02-25 08:21:33 +0100 492)         synchronized (mPendingUploads) {
5fc7cd13 src/com/owncloud/android/files/services/FileUploader.java (David A. Velasco  2012-10-11 11:24:17 +0200 493)             mCurrentUpload = mPendingUploads.get(uploadKey);
53b67429 src/com/owncloud/android/files/services/FileUploader.java (David A. Velasco  2012-09-12 16:16:56 +0200 494)         }
f7649804 src/com/owncloud/android/files/services/FileUploader.java (Matthias Baumann  2013-02-25 08:21:33 +0100 495) 
5fc7cd13 src/com/owncloud/android/files/services/FileUploader.java (David A. Velasco  2012-10-11 11:24:17 +0200 496)         if (mCurrentUpload != null) {
f7649804 src/com/owncloud/android/files/services/FileUploader.java (Matthias Baumann  2013-02-25 08:21:33 +0100 497) 
5fc7cd13 src/com/owncloud/android/files/services/FileUploader.java (David A. Velasco  2012-10-11 11:24:17 +0200 498)             notifyUploadStart(mCurrentUpload);
5fc7cd13 src/com/owncloud/android/files/services/FileUploader.java (David A. Velasco  2012-10-11 11:24:17 +0200 499) 
11b88e0f src/com/owncloud/android/files/services/FileUploader.java (David A. Velasco  2012-12-19 14:12:53 +0100 500)             RemoteOperationResult uploadResult = null;
53b67429 src/com/owncloud/android/files/services/FileUploader.java (David A. Velasco  2012-09-12 16:16:56 +0200 501)             
11b88e0f src/com/owncloud/android/files/services/FileUploader.java (David A. Velasco  2012-12-19 14:12:53 +0100 502)             try {
11b88e0f src/com/owncloud/android/files/services/FileUploader.java (David A. Velasco  2012-12-19 14:12:53 +0100 503)                 /// prepare client object to send requests to the ownCloud server
11b88e0f src/com/owncloud/android/files/services/FileUploader.java (David A. Velasco  2012-12-19 14:12:53 +0100 504)                 if (mUploadClient == null || !mLastAccount.equals(mCurrentUpload.getAccount())) {
11b88e0f src/com/owncloud/android/files/services/FileUploader.java (David A. Velasco  2012-12-19 14:12:53 +0100 505)                     mLastAccount = mCurrentUpload.getAccount();
11b88e0f src/com/owncloud/android/files/services/FileUploader.java (David A. Velasco  2012-12-19 14:12:53 +0100 506)                     mStorageManager = new FileDataStorageManager(mLastAccount, getContentResolver());
11b88e0f src/com/owncloud/android/files/services/FileUploader.java (David A. Velasco  2012-12-19 14:12:53 +0100 507)                     mUploadClient = OwnCloudClientUtils.createOwnCloudClient(mLastAccount, getApplicationContext());
11b88e0f src/com/owncloud/android/files/services/FileUploader.java (David A. Velasco  2012-12-19 14:12:53 +0100 508)                 }
53b67429 src/com/owncloud/android/files/services/FileUploader.java (David A. Velasco  2012-09-12 16:16:56 +0200 509)             
11b88e0f src/com/owncloud/android/files/services/FileUploader.java (David A. Velasco  2012-12-19 14:12:53 +0100 510)                 /// create remote folder for instant uploads
11b88e0f src/com/owncloud/android/files/services/FileUploader.java (David A. Velasco  2012-12-19 14:12:53 +0100 511)                 if (mCurrentUpload.isRemoteFolderToBeCreated()) {
2946d8dd src/com/owncloud/android/files/services/FileUploader.java (David A. Velasco  2013-04-25 19:39:22 +0200 512)                     RemoteOperation operation = new CreateFolderOperation(  FileStorageUtils.getInstantUploadFilePath(this, ""), 
11b88e0f src/com/owncloud/android/files/services/FileUploader.java (David A. Velasco  2012-12-19 14:12:53 +0100 513)                                                                             mStorageManager.getFileByPath(OCFile.PATH_SEPARATOR).getFileId(), // TODO generalize this : INSTANT_UPLOAD_DIR could not be a child of root
11b88e0f src/com/owncloud/android/files/services/FileUploader.java (David A. Velasco  2012-12-19 14:12:53 +0100 514)                                                                             mStorageManager);
11b88e0f src/com/owncloud/android/files/services/FileUploader.java (David A. Velasco  2012-12-19 14:12:53 +0100 515)                     operation.execute(mUploadClient);      // ignoring result; fail could just mean that it already exists, but local database is not synchronized; the upload will be tried anyway
11b88e0f src/com/owncloud/android/files/services/FileUploader.java (David A. Velasco  2012-12-19 14:12:53 +0100 516)                 }
cf27417e src/com/owncloud/android/files/services/FileUploader.java (David A. Velasco  2012-10-15 15:22:07 +0200 517) 
cf27417e src/com/owncloud/android/files/services/FileUploader.java (David A. Velasco  2012-10-15 15:22:07 +0200 518)             
11b88e0f src/com/owncloud/android/files/services/FileUploader.java (David A. Velasco  2012-12-19 14:12:53 +0100 519)                 /// perform the upload
5fc7cd13 src/com/owncloud/android/files/services/FileUploader.java (David A. Velasco  2012-10-11 11:24:17 +0200 520)                 uploadResult = mCurrentUpload.execute(mUploadClient);
53b67429 src/com/owncloud/android/files/services/FileUploader.java (David A. Velasco  2012-09-12 16:16:56 +0200 521)                 if (uploadResult.isSuccess()) {
c38a3b2e src/com/owncloud/android/files/services/FileUploader.java (David A. Velasco  2012-12-18 11:20:03 +0100 522)                     saveUploadedFile();
53b67429 src/com/owncloud/android/files/services/FileUploader.java (David A. Velasco  2012-09-12 16:16:56 +0200 523)                 }
53b67429 src/com/owncloud/android/files/services/FileUploader.java (David A. Velasco  2012-09-12 16:16:56 +0200 524)                 
11b88e0f src/com/owncloud/android/files/services/FileUploader.java (David A. Velasco  2012-12-19 14:12:53 +0100 525)             } catch (AccountsException e) {
2946d8dd src/com/owncloud/android/files/services/FileUploader.java (David A. Velasco  2013-04-25 19:39:22 +0200 526)                 Log_OC.e(TAG, "Error while trying to get autorization for " + mLastAccount.name, e);
11b88e0f src/com/owncloud/android/files/services/FileUploader.java (David A. Velasco  2012-12-19 14:12:53 +0100 527)                 uploadResult = new RemoteOperationResult(e);
11b88e0f src/com/owncloud/android/files/services/FileUploader.java (David A. Velasco  2012-12-19 14:12:53 +0100 528)                 
11b88e0f src/com/owncloud/android/files/services/FileUploader.java (David A. Velasco  2012-12-19 14:12:53 +0100 529)             } catch (IOException e) {
2946d8dd src/com/owncloud/android/files/services/FileUploader.java (David A. Velasco  2013-04-25 19:39:22 +0200 530)                 Log_OC.e(TAG, "Error while trying to get autorization for " + mLastAccount.name, e);
11b88e0f src/com/owncloud/android/files/services/FileUploader.java (David A. Velasco  2012-12-19 14:12:53 +0100 531)                 uploadResult = new RemoteOperationResult(e);
11b88e0f src/com/owncloud/android/files/services/FileUploader.java (David A. Velasco  2012-12-19 14:12:53 +0100 532)                 
53b67429 src/com/owncloud/android/files/services/FileUploader.java (David A. Velasco  2012-09-12 16:16:56 +0200 533)             } finally {
f7649804 src/com/owncloud/android/files/services/FileUploader.java (Matthias Baumann  2013-02-25 08:21:33 +0100 534)                 synchronized (mPendingUploads) {
5fc7cd13 src/com/owncloud/android/files/services/FileUploader.java (David A. Velasco  2012-10-11 11:24:17 +0200 535)                     mPendingUploads.remove(uploadKey);
8e36e7cc src/com/owncloud/android/files/services/FileUploader.java (zerginator        2013-03-12 07:56:27 +0100 536)                     Log_OC.i(TAG, "Remove CurrentUploadItem from pending upload Item Map.");
5fc7cd13 src/com/owncloud/android/files/services/FileUploader.java (David A. Velasco  2012-10-11 11:24:17 +0200 537)                 }
53b67429 src/com/owncloud/android/files/services/FileUploader.java (David A. Velasco  2012-09-12 16:16:56 +0200 538)             }
11b88e0f src/com/owncloud/android/files/services/FileUploader.java (David A. Velasco  2012-12-19 14:12:53 +0100 539)             
5fc7cd13 src/com/owncloud/android/files/services/FileUploader.java (David A. Velasco  2012-10-11 11:24:17 +0200 540)             /// notify result
5fc7cd13 src/com/owncloud/android/files/services/FileUploader.java (David A. Velasco  2012-10-11 11:24:17 +0200 541)             
5fc7cd13 src/com/owncloud/android/files/services/FileUploader.java (David A. Velasco  2012-10-11 11:24:17 +0200 542)             notifyUploadResult(uploadResult, mCurrentUpload);
27112bd2 src/com/owncloud/android/files/services/FileUploader.java (David A. Velasco  2012-10-11 16:40:56 +0200 543)             sendFinalBroadcast(mCurrentUpload, uploadResult);
f7649804 src/com/owncloud/android/files/services/FileUploader.java (Matthias Baumann  2013-02-25 08:21:33 +0100 544) 
5fc7cd13 src/com/owncloud/android/files/services/FileUploader.java (David A. Velasco  2012-10-11 11:24:17 +0200 545)         }
f7649804 src/com/owncloud/android/files/services/FileUploader.java (Matthias Baumann  2013-02-25 08:21:33 +0100 546) 
53b67429 src/com/owncloud/android/files/services/FileUploader.java (David A. Velasco  2012-09-12 16:16:56 +0200 547)     }
53b67429 src/com/owncloud/android/files/services/FileUploader.java (David A. Velasco  2012-09-12 16:16:56 +0200 548) 
53b67429 src/com/owncloud/android/files/services/FileUploader.java (David A. Velasco  2012-09-12 16:16:56 +0200 549)     /**
c38a3b2e src/com/owncloud/android/files/services/FileUploader.java (David A. Velasco  2012-12-18 11:20:03 +0100 550)      * Saves a OC File after a successful upload.
c38a3b2e src/com/owncloud/android/files/services/FileUploader.java (David A. Velasco  2012-12-18 11:20:03 +0100 551)      * 
f7649804 src/com/owncloud/android/files/services/FileUploader.java (Matthias Baumann  2013-02-25 08:21:33 +0100 552)      * A PROPFIND is necessary to keep the props in the local database
f7649804 src/com/owncloud/android/files/services/FileUploader.java (Matthias Baumann  2013-02-25 08:21:33 +0100 553)      * synchronized with the server, specially the modification time and Etag
f7649804 src/com/owncloud/android/files/services/FileUploader.java (Matthias Baumann  2013-02-25 08:21:33 +0100 554)      * (where available)
53b67429 src/com/owncloud/android/files/services/FileUploader.java (David A. Velasco  2012-09-12 16:16:56 +0200 555)      * 
c38a3b2e src/com/owncloud/android/files/services/FileUploader.java (David A. Velasco  2012-12-18 11:20:03 +0100 556)      * TODO refactor this ugly thing
53b67429 src/com/owncloud/android/files/services/FileUploader.java (David A. Velasco  2012-09-12 16:16:56 +0200 557)      */
c38a3b2e src/com/owncloud/android/files/services/FileUploader.java (David A. Velasco  2012-12-18 11:20:03 +0100 558)     private void saveUploadedFile() {
c38a3b2e src/com/owncloud/android/files/services/FileUploader.java (David A. Velasco  2012-12-18 11:20:03 +0100 559)         OCFile file = mCurrentUpload.getFile();
c38a3b2e src/com/owncloud/android/files/services/FileUploader.java (David A. Velasco  2012-12-18 11:20:03 +0100 560)         long syncDate = System.currentTimeMillis();
c38a3b2e src/com/owncloud/android/files/services/FileUploader.java (David A. Velasco  2012-12-18 11:20:03 +0100 561)         file.setLastSyncDateForData(syncDate);
f7649804 src/com/owncloud/android/files/services/FileUploader.java (Matthias Baumann  2013-02-25 08:21:33 +0100 562) 
f7649804 src/com/owncloud/android/files/services/FileUploader.java (Matthias Baumann  2013-02-25 08:21:33 +0100 563)         // / new PROPFIND to keep data consistent with server in theory, should
f7649804 src/com/owncloud/android/files/services/FileUploader.java (Matthias Baumann  2013-02-25 08:21:33 +0100 564)         // return the same we already have
c38a3b2e src/com/owncloud/android/files/services/FileUploader.java (David A. Velasco  2012-12-18 11:20:03 +0100 565)         PropFindMethod propfind = null;
c38a3b2e src/com/owncloud/android/files/services/FileUploader.java (David A. Velasco  2012-12-18 11:20:03 +0100 566)         RemoteOperationResult result = null;
c38a3b2e src/com/owncloud/android/files/services/FileUploader.java (David A. Velasco  2012-12-18 11:20:03 +0100 567)         try {
f7649804 src/com/owncloud/android/files/services/FileUploader.java (Matthias Baumann  2013-02-25 08:21:33 +0100 568)             propfind = new PropFindMethod(mUploadClient.getBaseUri()
f7649804 src/com/owncloud/android/files/services/FileUploader.java (Matthias Baumann  2013-02-25 08:21:33 +0100 569)                     + WebdavUtils.encodePath(mCurrentUpload.getRemotePath()));
f7649804 src/com/owncloud/android/files/services/FileUploader.java (Matthias Baumann  2013-02-25 08:21:33 +0100 570)             int status = mUploadClient.executeMethod(propfind);
f7649804 src/com/owncloud/android/files/services/FileUploader.java (Matthias Baumann  2013-02-25 08:21:33 +0100 571)             boolean isMultiStatus = (status == HttpStatus.SC_MULTI_STATUS);
f7649804 src/com/owncloud/android/files/services/FileUploader.java (Matthias Baumann  2013-02-25 08:21:33 +0100 572)             if (isMultiStatus) {
f7649804 src/com/owncloud/android/files/services/FileUploader.java (Matthias Baumann  2013-02-25 08:21:33 +0100 573)                 MultiStatus resp = propfind.getResponseBodyAsMultiStatus();
f7649804 src/com/owncloud/android/files/services/FileUploader.java (Matthias Baumann  2013-02-25 08:21:33 +0100 574)                 WebdavEntry we = new WebdavEntry(resp.getResponses()[0], mUploadClient.getBaseUri().getPath());
f7649804 src/com/owncloud/android/files/services/FileUploader.java (Matthias Baumann  2013-02-25 08:21:33 +0100 575)                 updateOCFile(file, we);
f7649804 src/com/owncloud/android/files/services/FileUploader.java (Matthias Baumann  2013-02-25 08:21:33 +0100 576)                 file.setLastSyncDateForProperties(syncDate);
f7649804 src/com/owncloud/android/files/services/FileUploader.java (Matthias Baumann  2013-02-25 08:21:33 +0100 577) 
f7649804 src/com/owncloud/android/files/services/FileUploader.java (Matthias Baumann  2013-02-25 08:21:33 +0100 578)             } else {
f7649804 src/com/owncloud/android/files/services/FileUploader.java (Matthias Baumann  2013-02-25 08:21:33 +0100 579)                 mUploadClient.exhaustResponse(propfind.getResponseBodyAsStream());
f7649804 src/com/owncloud/android/files/services/FileUploader.java (Matthias Baumann  2013-02-25 08:21:33 +0100 580)             }
f7649804 src/com/owncloud/android/files/services/FileUploader.java (Matthias Baumann  2013-02-25 08:21:33 +0100 581) 
78bcf721 src/com/owncloud/android/files/services/FileUploader.java (David A. Velasco  2013-08-07 19:13:00 +0200 582)             result = new RemoteOperationResult(isMultiStatus, status, propfind.getResponseHeaders());
8e36e7cc src/com/owncloud/android/files/services/FileUploader.java (zerginator        2013-03-12 07:56:27 +0100 583)             Log_OC.i(TAG, "Update: synchronizing properties for uploaded " + mCurrentUpload.getRemotePath() + ": "
f7649804 src/com/owncloud/android/files/services/FileUploader.java (Matthias Baumann  2013-02-25 08:21:33 +0100 584)                     + result.getLogMessage());
f7649804 src/com/owncloud/android/files/services/FileUploader.java (Matthias Baumann  2013-02-25 08:21:33 +0100 585) 
c38a3b2e src/com/owncloud/android/files/services/FileUploader.java (David A. Velasco  2012-12-18 11:20:03 +0100 586)         } catch (Exception e) {
c38a3b2e src/com/owncloud/android/files/services/FileUploader.java (David A. Velasco  2012-12-18 11:20:03 +0100 587)             result = new RemoteOperationResult(e);
8e36e7cc src/com/owncloud/android/files/services/FileUploader.java (zerginator        2013-03-12 07:56:27 +0100 588)             Log_OC.e(TAG, "Update: synchronizing properties for uploaded " + mCurrentUpload.getRemotePath() + ": "
f7649804 src/com/owncloud/android/files/services/FileUploader.java (Matthias Baumann  2013-02-25 08:21:33 +0100 589)                     + result.getLogMessage(), e);
c38a3b2e src/com/owncloud/android/files/services/FileUploader.java (David A. Velasco  2012-12-18 11:20:03 +0100 590) 
c38a3b2e src/com/owncloud/android/files/services/FileUploader.java (David A. Velasco  2012-12-18 11:20:03 +0100 591)         } finally {
c38a3b2e src/com/owncloud/android/files/services/FileUploader.java (David A. Velasco  2012-12-18 11:20:03 +0100 592)             if (propfind != null)
c38a3b2e src/com/owncloud/android/files/services/FileUploader.java (David A. Velasco  2012-12-18 11:20:03 +0100 593)                 propfind.releaseConnection();
c38a3b2e src/com/owncloud/android/files/services/FileUploader.java (David A. Velasco  2012-12-18 11:20:03 +0100 594)         }
c38a3b2e src/com/owncloud/android/files/services/FileUploader.java (David A. Velasco  2012-12-18 11:20:03 +0100 595) 
f7649804 src/com/owncloud/android/files/services/FileUploader.java (Matthias Baumann  2013-02-25 08:21:33 +0100 596)         // / maybe this would be better as part of UploadFileOperation... or
f7649804 src/com/owncloud/android/files/services/FileUploader.java (Matthias Baumann  2013-02-25 08:21:33 +0100 597)         // maybe all this method
c38a3b2e src/com/owncloud/android/files/services/FileUploader.java (David A. Velasco  2012-12-18 11:20:03 +0100 598)         if (mCurrentUpload.wasRenamed()) {
c38a3b2e src/com/owncloud/android/files/services/FileUploader.java (David A. Velasco  2012-12-18 11:20:03 +0100 599)             OCFile oldFile = mCurrentUpload.getOldFile();
c38a3b2e src/com/owncloud/android/files/services/FileUploader.java (David A. Velasco  2012-12-18 11:20:03 +0100 600)             if (oldFile.fileExists()) {
c38a3b2e src/com/owncloud/android/files/services/FileUploader.java (David A. Velasco  2012-12-18 11:20:03 +0100 601)                 oldFile.setStoragePath(null);
c38a3b2e src/com/owncloud/android/files/services/FileUploader.java (David A. Velasco  2012-12-18 11:20:03 +0100 602)                 mStorageManager.saveFile(oldFile);
f7649804 src/com/owncloud/android/files/services/FileUploader.java (Matthias Baumann  2013-02-25 08:21:33 +0100 603) 
f7649804 src/com/owncloud/android/files/services/FileUploader.java (Matthias Baumann  2013-02-25 08:21:33 +0100 604)             } // else: it was just an automatic renaming due to a name
f7649804 src/com/owncloud/android/files/services/FileUploader.java (Matthias Baumann  2013-02-25 08:21:33 +0100 605)               // coincidence; nothing else is needed, the storagePath is right
f7649804 src/com/owncloud/android/files/services/FileUploader.java (Matthias Baumann  2013-02-25 08:21:33 +0100 606)               // in the instance returned by mCurrentUpload.getFile()
c38a3b2e src/com/owncloud/android/files/services/FileUploader.java (David A. Velasco  2012-12-18 11:20:03 +0100 607)         }
f7649804 src/com/owncloud/android/files/services/FileUploader.java (Matthias Baumann  2013-02-25 08:21:33 +0100 608) 
c38a3b2e src/com/owncloud/android/files/services/FileUploader.java (David A. Velasco  2012-12-18 11:20:03 +0100 609)         mStorageManager.saveFile(file);
c38a3b2e src/com/owncloud/android/files/services/FileUploader.java (David A. Velasco  2012-12-18 11:20:03 +0100 610)     }
c38a3b2e src/com/owncloud/android/files/services/FileUploader.java (David A. Velasco  2012-12-18 11:20:03 +0100 611) 
c38a3b2e src/com/owncloud/android/files/services/FileUploader.java (David A. Velasco  2012-12-18 11:20:03 +0100 612)     private void updateOCFile(OCFile file, WebdavEntry we) {
c38a3b2e src/com/owncloud/android/files/services/FileUploader.java (David A. Velasco  2012-12-18 11:20:03 +0100 613)         file.setCreationTimestamp(we.createTimestamp());
c38a3b2e src/com/owncloud/android/files/services/FileUploader.java (David A. Velasco  2012-12-18 11:20:03 +0100 614)         file.setFileLength(we.contentLength());
c38a3b2e src/com/owncloud/android/files/services/FileUploader.java (David A. Velasco  2012-12-18 11:20:03 +0100 615)         file.setMimetype(we.contentType());
c38a3b2e src/com/owncloud/android/files/services/FileUploader.java (David A. Velasco  2012-12-18 11:20:03 +0100 616)         file.setModificationTimestamp(we.modifiedTimestamp());
c38a3b2e src/com/owncloud/android/files/services/FileUploader.java (David A. Velasco  2012-12-18 11:20:03 +0100 617)         file.setModificationTimestampAtLastSyncForData(we.modifiedTimestamp());
bcc7bd27 src/com/owncloud/android/files/services/FileUploader.java (David A. Velasco  2013-02-21 09:57:23 +0100 618)         // file.setEtag(mCurrentUpload.getEtag());    // TODO Etag, where available
27112bd2 src/com/owncloud/android/files/services/FileUploader.java (David A. Velasco  2012-10-11 16:40:56 +0200 619)     }
f7649804 src/com/owncloud/android/files/services/FileUploader.java (Matthias Baumann  2013-02-25 08:21:33 +0100 620) 
cf27417e src/com/owncloud/android/files/services/FileUploader.java (David A. Velasco  2012-10-15 15:22:07 +0200 621)     private boolean checkAndFixInstantUploadDirectory(FileDataStorageManager storageManager) {
9ad30bdd src/com/owncloud/android/files/services/FileUploader.java (David A. Velasco  2013-04-17 18:00:31 +0200 622)         String instantUploadDirPath = FileStorageUtils.getInstantUploadFilePath(this, "");
9ad30bdd src/com/owncloud/android/files/services/FileUploader.java (David A. Velasco  2013-04-17 18:00:31 +0200 623)         OCFile instantUploadDir = storageManager.getFileByPath(instantUploadDirPath);
cf27417e src/com/owncloud/android/files/services/FileUploader.java (David A. Velasco  2012-10-15 15:22:07 +0200 624)         if (instantUploadDir == null) {
bdc0332c src/com/owncloud/android/files/services/FileUploader.java (David A. Velasco  2013-04-17 12:26:13 +0200 625)             // first instant upload in the account. never account not
f7649804 src/com/owncloud/android/files/services/FileUploader.java (Matthias Baumann  2013-02-25 08:21:33 +0100 626)             // synchronized after the remote InstantUpload folder was created
9ad30bdd src/com/owncloud/android/files/services/FileUploader.java (David A. Velasco  2013-04-17 18:00:31 +0200 627)             OCFile newDir = new OCFile(instantUploadDirPath);
cf27417e src/com/owncloud/android/files/services/FileUploader.java (David A. Velasco  2012-10-15 15:22:07 +0200 628)             newDir.setMimetype("DIR");
f7649804 src/com/owncloud/android/files/services/FileUploader.java (Matthias Baumann  2013-02-25 08:21:33 +0100 629)             OCFile path = storageManager.getFileByPath(OCFile.PATH_SEPARATOR);
f7649804 src/com/owncloud/android/files/services/FileUploader.java (Matthias Baumann  2013-02-25 08:21:33 +0100 630) 
f7649804 src/com/owncloud/android/files/services/FileUploader.java (Matthias Baumann  2013-02-25 08:21:33 +0100 631)             if (path != null) {
f7649804 src/com/owncloud/android/files/services/FileUploader.java (Matthias Baumann  2013-02-25 08:21:33 +0100 632)                 newDir.setParentId(path.getFileId());
f7649804 src/com/owncloud/android/files/services/FileUploader.java (Matthias Baumann  2013-02-25 08:21:33 +0100 633)                 storageManager.saveFile(newDir);
f7649804 src/com/owncloud/android/files/services/FileUploader.java (Matthias Baumann  2013-02-25 08:21:33 +0100 634)                 return true;
9ad30bdd src/com/owncloud/android/files/services/FileUploader.java (David A. Velasco  2013-04-17 18:00:31 +0200 635)             } else {    // this should not happen anymore
f7649804 src/com/owncloud/android/files/services/FileUploader.java (Matthias Baumann  2013-02-25 08:21:33 +0100 636)                 return false;
f7649804 src/com/owncloud/android/files/services/FileUploader.java (Matthias Baumann  2013-02-25 08:21:33 +0100 637)             }
f7649804 src/com/owncloud/android/files/services/FileUploader.java (Matthias Baumann  2013-02-25 08:21:33 +0100 638) 
cf27417e src/com/owncloud/android/files/services/FileUploader.java (David A. Velasco  2012-10-15 15:22:07 +0200 639)         }
cf27417e src/com/owncloud/android/files/services/FileUploader.java (David A. Velasco  2012-10-15 15:22:07 +0200 640)         return false;
cf27417e src/com/owncloud/android/files/services/FileUploader.java (David A. Velasco  2012-10-15 15:22:07 +0200 641)     }
cf27417e src/com/owncloud/android/files/services/FileUploader.java (David A. Velasco  2012-10-15 15:22:07 +0200 642) 
f7649804 src/com/owncloud/android/files/services/FileUploader.java (Matthias Baumann  2013-02-25 08:21:33 +0100 643)     private OCFile obtainNewOCFileToUpload(String remotePath, String localPath, String mimeType,
f7649804 src/com/owncloud/android/files/services/FileUploader.java (Matthias Baumann  2013-02-25 08:21:33 +0100 644)             FileDataStorageManager storageManager) {
27112bd2 src/com/owncloud/android/files/services/FileUploader.java (David A. Velasco  2012-10-11 16:40:56 +0200 645)         OCFile newFile = new OCFile(remotePath);
27112bd2 src/com/owncloud/android/files/services/FileUploader.java (David A. Velasco  2012-10-11 16:40:56 +0200 646)         newFile.setStoragePath(localPath);
c38a3b2e src/com/owncloud/android/files/services/FileUploader.java (David A. Velasco  2012-12-18 11:20:03 +0100 647)         newFile.setLastSyncDateForProperties(0);
c38a3b2e src/com/owncloud/android/files/services/FileUploader.java (David A. Velasco  2012-12-18 11:20:03 +0100 648)         newFile.setLastSyncDateForData(0);
f7649804 src/com/owncloud/android/files/services/FileUploader.java (Matthias Baumann  2013-02-25 08:21:33 +0100 649) 
27112bd2 src/com/owncloud/android/files/services/FileUploader.java (David A. Velasco  2012-10-11 16:40:56 +0200 650)         // size
27112bd2 src/com/owncloud/android/files/services/FileUploader.java (David A. Velasco  2012-10-11 16:40:56 +0200 651)         if (localPath != null && localPath.length() > 0) {
27112bd2 src/com/owncloud/android/files/services/FileUploader.java (David A. Velasco  2012-10-11 16:40:56 +0200 652)             File localFile = new File(localPath);
27112bd2 src/com/owncloud/android/files/services/FileUploader.java (David A. Velasco  2012-10-11 16:40:56 +0200 653)             newFile.setFileLength(localFile.length());
c38a3b2e src/com/owncloud/android/files/services/FileUploader.java (David A. Velasco  2012-12-18 11:20:03 +0100 654)             newFile.setLastSyncDateForData(localFile.lastModified());
f7649804 src/com/owncloud/android/files/services/FileUploader.java (Matthias Baumann  2013-02-25 08:21:33 +0100 655)         } // don't worry about not assigning size, the problems with localPath
f7649804 src/com/owncloud/android/files/services/FileUploader.java (Matthias Baumann  2013-02-25 08:21:33 +0100 656)           // are checked when the UploadFileOperation instance is created
f7649804 src/com/owncloud/android/files/services/FileUploader.java (Matthias Baumann  2013-02-25 08:21:33 +0100 657) 
27112bd2 src/com/owncloud/android/files/services/FileUploader.java (David A. Velasco  2012-10-11 16:40:56 +0200 658)         // MIME type
27112bd2 src/com/owncloud/android/files/services/FileUploader.java (David A. Velasco  2012-10-11 16:40:56 +0200 659)         if (mimeType == null || mimeType.length() <= 0) {
27112bd2 src/com/owncloud/android/files/services/FileUploader.java (David A. Velasco  2012-10-11 16:40:56 +0200 660)             try {
f7649804 src/com/owncloud/android/files/services/FileUploader.java (Matthias Baumann  2013-02-25 08:21:33 +0100 661)                 mimeType = MimeTypeMap.getSingleton().getMimeTypeFromExtension(
f7649804 src/com/owncloud/android/files/services/FileUploader.java (Matthias Baumann  2013-02-25 08:21:33 +0100 662)                         remotePath.substring(remotePath.lastIndexOf('.') + 1));
27112bd2 src/com/owncloud/android/files/services/FileUploader.java (David A. Velasco  2012-10-11 16:40:56 +0200 663)             } catch (IndexOutOfBoundsException e) {
8e36e7cc src/com/owncloud/android/files/services/FileUploader.java (zerginator        2013-03-12 07:56:27 +0100 664)                 Log_OC.e(TAG, "Trying to find out MIME type of a file without extension: " + remotePath);
27112bd2 src/com/owncloud/android/files/services/FileUploader.java (David A. Velasco  2012-10-11 16:40:56 +0200 665)             }
27112bd2 src/com/owncloud/android/files/services/FileUploader.java (David A. Velasco  2012-10-11 16:40:56 +0200 666)         }
27112bd2 src/com/owncloud/android/files/services/FileUploader.java (David A. Velasco  2012-10-11 16:40:56 +0200 667)         if (mimeType == null) {
27112bd2 src/com/owncloud/android/files/services/FileUploader.java (David A. Velasco  2012-10-11 16:40:56 +0200 668)             mimeType = "application/octet-stream";
27112bd2 src/com/owncloud/android/files/services/FileUploader.java (David A. Velasco  2012-10-11 16:40:56 +0200 669)         }
27112bd2 src/com/owncloud/android/files/services/FileUploader.java (David A. Velasco  2012-10-11 16:40:56 +0200 670)         newFile.setMimetype(mimeType);
f7649804 src/com/owncloud/android/files/services/FileUploader.java (Matthias Baumann  2013-02-25 08:21:33 +0100 671) 
27112bd2 src/com/owncloud/android/files/services/FileUploader.java (David A. Velasco  2012-10-11 16:40:56 +0200 672)         // parent dir
27112bd2 src/com/owncloud/android/files/services/FileUploader.java (David A. Velasco  2012-10-11 16:40:56 +0200 673)         String parentPath = new File(remotePath).getParent();
f7649804 src/com/owncloud/android/files/services/FileUploader.java (Matthias Baumann  2013-02-25 08:21:33 +0100 674)         parentPath = parentPath.endsWith(OCFile.PATH_SEPARATOR) ? parentPath : parentPath + OCFile.PATH_SEPARATOR;
cf27417e src/com/owncloud/android/files/services/FileUploader.java (David A. Velasco  2012-10-15 15:22:07 +0200 675)         OCFile parentDir = storageManager.getFileByPath(parentPath);
cf27417e src/com/owncloud/android/files/services/FileUploader.java (David A. Velasco  2012-10-15 15:22:07 +0200 676)         long parentDirId = parentDir.getFileId();
53b67429 src/com/owncloud/android/files/services/FileUploader.java (David A. Velasco  2012-09-12 16:16:56 +0200 677)         newFile.setParentId(parentDirId);
27112bd2 src/com/owncloud/android/files/services/FileUploader.java (David A. Velasco  2012-10-11 16:40:56 +0200 678)         return newFile;
53b67429 src/com/owncloud/android/files/services/FileUploader.java (David A. Velasco  2012-09-12 16:16:56 +0200 679)     }
f3ba1075 src/eu/alefzero/owncloud/files/services/FileUploader.java (David A. Velasco  2012-07-26 18:35:09 +0200 680) 
53b67429 src/com/owncloud/android/files/services/FileUploader.java (David A. Velasco  2012-09-12 16:16:56 +0200 681)     /**
53b67429 src/com/owncloud/android/files/services/FileUploader.java (David A. Velasco  2012-09-12 16:16:56 +0200 682)      * Creates a status notification to show the upload progress
5fc7cd13 src/com/owncloud/android/files/services/FileUploader.java (David A. Velasco  2012-10-11 11:24:17 +0200 683)      * 
f7649804 src/com/owncloud/android/files/services/FileUploader.java (Matthias Baumann  2013-02-25 08:21:33 +0100 684)      * @param upload Upload operation starting.
53b67429 src/com/owncloud/android/files/services/FileUploader.java (David A. Velasco  2012-09-12 16:16:56 +0200 685)      */
7018ae7e src/com/owncloud/android/files/services/FileUploader.java (David A. Velasco  2013-02-05 12:20:47 +0100 686)     @SuppressWarnings("deprecation")
5fc7cd13 src/com/owncloud/android/files/services/FileUploader.java (David A. Velasco  2012-10-11 11:24:17 +0200 687)     private void notifyUploadStart(UploadFileOperation upload) {
f7649804 src/com/owncloud/android/files/services/FileUploader.java (Matthias Baumann  2013-02-25 08:21:33 +0100 688)         // / create status notification with a progress bar
5fc7cd13 src/com/owncloud/android/files/services/FileUploader.java (David A. Velasco  2012-10-11 11:24:17 +0200 689)         mLastPercent = 0;
f7649804 src/com/owncloud/android/files/services/FileUploader.java (Matthias Baumann  2013-02-25 08:21:33 +0100 690)         mNotification = new Notification(R.drawable.icon, getString(R.string.uploader_upload_in_progress_ticker),
f7649804 src/com/owncloud/android/files/services/FileUploader.java (Matthias Baumann  2013-02-25 08:21:33 +0100 691)                 System.currentTimeMillis());
35a384d0 src/eu/alefzero/owncloud/files/services/FileUploader.java (Bartek Przybylski 2012-05-19 23:30:23 +0200 692)         mNotification.flags |= Notification.FLAG_ONGOING_EVENT;
53b67429 src/com/owncloud/android/files/services/FileUploader.java (David A. Velasco  2012-09-12 16:16:56 +0200 693)         mDefaultNotificationContentView = mNotification.contentView;
f7649804 src/com/owncloud/android/files/services/FileUploader.java (Matthias Baumann  2013-02-25 08:21:33 +0100 694)         mNotification.contentView = new RemoteViews(getApplicationContext().getPackageName(),
f7649804 src/com/owncloud/android/files/services/FileUploader.java (Matthias Baumann  2013-02-25 08:21:33 +0100 695)                 R.layout.progressbar_layout);
35a384d0 src/eu/alefzero/owncloud/files/services/FileUploader.java (Bartek Przybylski 2012-05-19 23:30:23 +0200 696)         mNotification.contentView.setProgressBar(R.id.status_progress, 100, 0, false);
f7649804 src/com/owncloud/android/files/services/FileUploader.java (Matthias Baumann  2013-02-25 08:21:33 +0100 697)         mNotification.contentView.setTextViewText(R.id.status_text,
f7649804 src/com/owncloud/android/files/services/FileUploader.java (Matthias Baumann  2013-02-25 08:21:33 +0100 698)                 String.format(getString(R.string.uploader_upload_in_progress_content), 0, upload.getFileName()));
35a384d0 src/eu/alefzero/owncloud/files/services/FileUploader.java (Bartek Przybylski 2012-05-19 23:30:23 +0200 699)         mNotification.contentView.setImageViewResource(R.id.status_icon, R.drawable.icon);
5fc7cd13 src/com/owncloud/android/files/services/FileUploader.java (David A. Velasco  2012-10-11 11:24:17 +0200 700)         
5fc7cd13 src/com/owncloud/android/files/services/FileUploader.java (David A. Velasco  2012-10-11 11:24:17 +0200 701)         /// includes a pending intent in the notification showing the details view of the file
e0cb989b src/com/owncloud/android/files/services/FileUploader.java (David A. Velasco  2013-02-27 16:27:25 +0100 702)         Intent showDetailsIntent = null;
e0cb989b src/com/owncloud/android/files/services/FileUploader.java (David A. Velasco  2013-02-27 16:27:25 +0100 703)         if (PreviewImageFragment.canBePreviewed(upload.getFile())) {
e0cb989b src/com/owncloud/android/files/services/FileUploader.java (David A. Velasco  2013-02-27 16:27:25 +0100 704)             showDetailsIntent = new Intent(this, PreviewImageActivity.class);
e0cb989b src/com/owncloud/android/files/services/FileUploader.java (David A. Velasco  2013-02-27 16:27:25 +0100 705)         } else {
fd396289 src/com/owncloud/android/files/services/FileUploader.java (David A. Velasco  2013-05-30 17:53:21 +0200 706)             showDetailsIntent = new Intent(this, FileDisplayActivity.class);
e0cb989b src/com/owncloud/android/files/services/FileUploader.java (David A. Velasco  2013-02-27 16:27:25 +0100 707)         }
bc1fcf84 src/com/owncloud/android/files/services/FileUploader.java (David A. Velasco  2013-05-07 13:49:54 +0200 708)         showDetailsIntent.putExtra(FileActivity.EXTRA_FILE, upload.getFile());
bc1fcf84 src/com/owncloud/android/files/services/FileUploader.java (David A. Velasco  2013-05-07 13:49:54 +0200 709)         showDetailsIntent.putExtra(FileActivity.EXTRA_ACCOUNT, upload.getAccount());
5fc7cd13 src/com/owncloud/android/files/services/FileUploader.java (David A. Velasco  2012-10-11 11:24:17 +0200 710)         showDetailsIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
f7649804 src/com/owncloud/android/files/services/FileUploader.java (Matthias Baumann  2013-02-25 08:21:33 +0100 711)         mNotification.contentIntent = PendingIntent.getActivity(getApplicationContext(),
f7649804 src/com/owncloud/android/files/services/FileUploader.java (Matthias Baumann  2013-02-25 08:21:33 +0100 712)                 (int) System.currentTimeMillis(), showDetailsIntent, 0);
f7649804 src/com/owncloud/android/files/services/FileUploader.java (Matthias Baumann  2013-02-25 08:21:33 +0100 713) 
f3ba1075 src/eu/alefzero/owncloud/files/services/FileUploader.java (David A. Velasco  2012-07-26 18:35:09 +0200 714)         mNotificationManager.notify(R.string.uploader_upload_in_progress_ticker, mNotification);
53b67429 src/com/owncloud/android/files/services/FileUploader.java (David A. Velasco  2012-09-12 16:16:56 +0200 715)     }
435b31ba src/eu/alefzero/owncloud/files/services/FileUploader.java (Lennart Rosam     2012-05-16 09:48:34 +0200 716) 
53b67429 src/com/owncloud/android/files/services/FileUploader.java (David A. Velasco  2012-09-12 16:16:56 +0200 717)     /**
5fc7cd13 src/com/owncloud/android/files/services/FileUploader.java (David A. Velasco  2012-10-11 11:24:17 +0200 718)      * Callback method to update the progress bar in the status notification
53b67429 src/com/owncloud/android/files/services/FileUploader.java (David A. Velasco  2012-09-12 16:16:56 +0200 719)      */
5fc7cd13 src/com/owncloud/android/files/services/FileUploader.java (David A. Velasco  2012-10-11 11:24:17 +0200 720)     @Override
5fc7cd13 src/com/owncloud/android/files/services/FileUploader.java (David A. Velasco  2012-10-11 11:24:17 +0200 721)     public void onTransferProgress(long progressRate, long totalTransferredSoFar, long totalToTransfer, String fileName) {
f7649804 src/com/owncloud/android/files/services/FileUploader.java (Matthias Baumann  2013-02-25 08:21:33 +0100 722)         int percent = (int) (100.0 * ((double) totalTransferredSoFar) / ((double) totalToTransfer));
5fc7cd13 src/com/owncloud/android/files/services/FileUploader.java (David A. Velasco  2012-10-11 11:24:17 +0200 723)         if (percent != mLastPercent) {
5fc7cd13 src/com/owncloud/android/files/services/FileUploader.java (David A. Velasco  2012-10-11 11:24:17 +0200 724)             mNotification.contentView.setProgressBar(R.id.status_progress, 100, percent, false);
5fc7cd13 src/com/owncloud/android/files/services/FileUploader.java (David A. Velasco  2012-10-11 11:24:17 +0200 725)             String text = String.format(getString(R.string.uploader_upload_in_progress_content), percent, fileName);
5fc7cd13 src/com/owncloud/android/files/services/FileUploader.java (David A. Velasco  2012-10-11 11:24:17 +0200 726)             mNotification.contentView.setTextViewText(R.id.status_text, text);
5fc7cd13 src/com/owncloud/android/files/services/FileUploader.java (David A. Velasco  2012-10-11 11:24:17 +0200 727)             mNotificationManager.notify(R.string.uploader_upload_in_progress_ticker, mNotification);
5fc7cd13 src/com/owncloud/android/files/services/FileUploader.java (David A. Velasco  2012-10-11 11:24:17 +0200 728)         }
5fc7cd13 src/com/owncloud/android/files/services/FileUploader.java (David A. Velasco  2012-10-11 11:24:17 +0200 729)         mLastPercent = percent;
5fc7cd13 src/com/owncloud/android/files/services/FileUploader.java (David A. Velasco  2012-10-11 11:24:17 +0200 730)     }
f7649804 src/com/owncloud/android/files/services/FileUploader.java (Matthias Baumann  2013-02-25 08:21:33 +0100 731) 
5fc7cd13 src/com/owncloud/android/files/services/FileUploader.java (David A. Velasco  2012-10-11 11:24:17 +0200 732)     /**
f7649804 src/com/owncloud/android/files/services/FileUploader.java (Matthias Baumann  2013-02-25 08:21:33 +0100 733)      * Callback method to update the progress bar in the status notification
f7649804 src/com/owncloud/android/files/services/FileUploader.java (Matthias Baumann  2013-02-25 08:21:33 +0100 734)      * (old version)
5fc7cd13 src/com/owncloud/android/files/services/FileUploader.java (David A. Velasco  2012-10-11 11:24:17 +0200 735)      */
5fc7cd13 src/com/owncloud/android/files/services/FileUploader.java (David A. Velasco  2012-10-11 11:24:17 +0200 736)     @Override
5fc7cd13 src/com/owncloud/android/files/services/FileUploader.java (David A. Velasco  2012-10-11 11:24:17 +0200 737)     public void onTransferProgress(long progressRate) {
5fc7cd13 src/com/owncloud/android/files/services/FileUploader.java (David A. Velasco  2012-10-11 11:24:17 +0200 738)         // NOTHING TO DO HERE ANYMORE
53b67429 src/com/owncloud/android/files/services/FileUploader.java (David A. Velasco  2012-09-12 16:16:56 +0200 739)     }
53b67429 src/com/owncloud/android/files/services/FileUploader.java (David A. Velasco  2012-09-12 16:16:56 +0200 740) 
53b67429 src/com/owncloud/android/files/services/FileUploader.java (David A. Velasco  2012-09-12 16:16:56 +0200 741)     /**
5fc7cd13 src/com/owncloud/android/files/services/FileUploader.java (David A. Velasco  2012-10-11 11:24:17 +0200 742)      * Updates the status notification with the result of an upload operation.
5fc7cd13 src/com/owncloud/android/files/services/FileUploader.java (David A. Velasco  2012-10-11 11:24:17 +0200 743)      * 
f7649804 src/com/owncloud/android/files/services/FileUploader.java (Matthias Baumann  2013-02-25 08:21:33 +0100 744)      * @param uploadResult Result of the upload operation.
f7649804 src/com/owncloud/android/files/services/FileUploader.java (Matthias Baumann  2013-02-25 08:21:33 +0100 745)      * @param upload Finished upload operation
53b67429 src/com/owncloud/android/files/services/FileUploader.java (David A. Velasco  2012-09-12 16:16:56 +0200 746)      */
5fc7cd13 src/com/owncloud/android/files/services/FileUploader.java (David A. Velasco  2012-10-11 11:24:17 +0200 747)     private void notifyUploadResult(RemoteOperationResult uploadResult, UploadFileOperation upload) {
8e36e7cc src/com/owncloud/android/files/services/FileUploader.java (zerginator        2013-03-12 07:56:27 +0100 748)         Log_OC.d(TAG, "NotifyUploadResult with resultCode: " + uploadResult.getCode());
5fc7cd13 src/com/owncloud/android/files/services/FileUploader.java (David A. Velasco  2012-10-11 11:24:17 +0200 749)         if (uploadResult.isCancelled()) {
f7649804 src/com/owncloud/android/files/services/FileUploader.java (Matthias Baumann  2013-02-25 08:21:33 +0100 750)             // / cancelled operation -> silent removal of progress notification
5fc7cd13 src/com/owncloud/android/files/services/FileUploader.java (David A. Velasco  2012-10-11 11:24:17 +0200 751)             mNotificationManager.cancel(R.string.uploader_upload_in_progress_ticker);
f7649804 src/com/owncloud/android/files/services/FileUploader.java (Matthias Baumann  2013-02-25 08:21:33 +0100 752) 
5fc7cd13 src/com/owncloud/android/files/services/FileUploader.java (David A. Velasco  2012-10-11 11:24:17 +0200 753)         } else if (uploadResult.isSuccess()) {
f7649804 src/com/owncloud/android/files/services/FileUploader.java (Matthias Baumann  2013-02-25 08:21:33 +0100 754)             // / success -> silent update of progress notification to success
f7649804 src/com/owncloud/android/files/services/FileUploader.java (Matthias Baumann  2013-02-25 08:21:33 +0100 755)             // message
f7649804 src/com/owncloud/android/files/services/FileUploader.java (Matthias Baumann  2013-02-25 08:21:33 +0100 756)             mNotification.flags ^= Notification.FLAG_ONGOING_EVENT; // remove
f7649804 src/com/owncloud/android/files/services/FileUploader.java (Matthias Baumann  2013-02-25 08:21:33 +0100 757)                                                                     // the
f7649804 src/com/owncloud/android/files/services/FileUploader.java (Matthias Baumann  2013-02-25 08:21:33 +0100 758)                                                                     // ongoing
f7649804 src/com/owncloud/android/files/services/FileUploader.java (Matthias Baumann  2013-02-25 08:21:33 +0100 759)                                                                     // flag
f3ba1075 src/eu/alefzero/owncloud/files/services/FileUploader.java (David A. Velasco  2012-07-26 18:35:09 +0200 760)             mNotification.flags |= Notification.FLAG_AUTO_CANCEL;
53b67429 src/com/owncloud/android/files/services/FileUploader.java (David A. Velasco  2012-09-12 16:16:56 +0200 761)             mNotification.contentView = mDefaultNotificationContentView;
5fc7cd13 src/com/owncloud/android/files/services/FileUploader.java (David A. Velasco  2012-10-11 11:24:17 +0200 762)             
5fc7cd13 src/com/owncloud/android/files/services/FileUploader.java (David A. Velasco  2012-10-11 11:24:17 +0200 763)             /// includes a pending intent in the notification showing the details view of the file
e0cb989b src/com/owncloud/android/files/services/FileUploader.java (David A. Velasco  2013-02-27 16:27:25 +0100 764)             Intent showDetailsIntent = null;
e0cb989b src/com/owncloud/android/files/services/FileUploader.java (David A. Velasco  2013-02-27 16:27:25 +0100 765)             if (PreviewImageFragment.canBePreviewed(upload.getFile())) {
e0cb989b src/com/owncloud/android/files/services/FileUploader.java (David A. Velasco  2013-02-27 16:27:25 +0100 766)                 showDetailsIntent = new Intent(this, PreviewImageActivity.class); 
e0cb989b src/com/owncloud/android/files/services/FileUploader.java (David A. Velasco  2013-02-27 16:27:25 +0100 767)             } else {
fd396289 src/com/owncloud/android/files/services/FileUploader.java (David A. Velasco  2013-05-30 17:53:21 +0200 768)                 showDetailsIntent = new Intent(this, FileDisplayActivity.class); 
e0cb989b src/com/owncloud/android/files/services/FileUploader.java (David A. Velasco  2013-02-27 16:27:25 +0100 769)             }
bc1fcf84 src/com/owncloud/android/files/services/FileUploader.java (David A. Velasco  2013-05-07 13:49:54 +0200 770)             showDetailsIntent.putExtra(FileActivity.EXTRA_FILE, upload.getFile());
bc1fcf84 src/com/owncloud/android/files/services/FileUploader.java (David A. Velasco  2013-05-07 13:49:54 +0200 771)             showDetailsIntent.putExtra(FileActivity.EXTRA_ACCOUNT, upload.getAccount());
5fc7cd13 src/com/owncloud/android/files/services/FileUploader.java (David A. Velasco  2012-10-11 11:24:17 +0200 772)             showDetailsIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
f7649804 src/com/owncloud/android/files/services/FileUploader.java (Matthias Baumann  2013-02-25 08:21:33 +0100 773)             mNotification.contentIntent = PendingIntent.getActivity(getApplicationContext(),
f7649804 src/com/owncloud/android/files/services/FileUploader.java (Matthias Baumann  2013-02-25 08:21:33 +0100 774)                     (int) System.currentTimeMillis(), showDetailsIntent, 0);
f7649804 src/com/owncloud/android/files/services/FileUploader.java (Matthias Baumann  2013-02-25 08:21:33 +0100 775) 
f7649804 src/com/owncloud/android/files/services/FileUploader.java (Matthias Baumann  2013-02-25 08:21:33 +0100 776)             mNotification.setLatestEventInfo(getApplicationContext(),
f7649804 src/com/owncloud/android/files/services/FileUploader.java (Matthias Baumann  2013-02-25 08:21:33 +0100 777)                     getString(R.string.uploader_upload_succeeded_ticker),
f7649804 src/com/owncloud/android/files/services/FileUploader.java (Matthias Baumann  2013-02-25 08:21:33 +0100 778)                     String.format(getString(R.string.uploader_upload_succeeded_content_single), upload.getFileName()),
f7649804 src/com/owncloud/android/files/services/FileUploader.java (Matthias Baumann  2013-02-25 08:21:33 +0100 779)                     mNotification.contentIntent);
f7649804 src/com/owncloud/android/files/services/FileUploader.java (Matthias Baumann  2013-02-25 08:21:33 +0100 780) 
f7649804 src/com/owncloud/android/files/services/FileUploader.java (Matthias Baumann  2013-02-25 08:21:33 +0100 781)             mNotificationManager.notify(R.string.uploader_upload_in_progress_ticker, mNotification); // NOT
f7649804 src/com/owncloud/android/files/services/FileUploader.java (Matthias Baumann  2013-02-25 08:21:33 +0100 782)                                                                                                      // AN
f7649804 src/com/owncloud/android/files/services/FileUploader.java (Matthias Baumann  2013-02-25 08:21:33 +0100 783)             DbHandler db = new DbHandler(this.getBaseContext());
f7649804 src/com/owncloud/android/files/services/FileUploader.java (Matthias Baumann  2013-02-25 08:21:33 +0100 784)             db.removeIUPendingFile(mCurrentUpload.getFile().getStoragePath());
f7649804 src/com/owncloud/android/files/services/FileUploader.java (Matthias Baumann  2013-02-25 08:21:33 +0100 785)             db.close();
f7649804 src/com/owncloud/android/files/services/FileUploader.java (Matthias Baumann  2013-02-25 08:21:33 +0100 786) 
f3ba1075 src/eu/alefzero/owncloud/files/services/FileUploader.java (David A. Velasco  2012-07-26 18:35:09 +0200 787)         } else {
f7649804 src/com/owncloud/android/files/services/FileUploader.java (Matthias Baumann  2013-02-25 08:21:33 +0100 788) 
f7649804 src/com/owncloud/android/files/services/FileUploader.java (Matthias Baumann  2013-02-25 08:21:33 +0100 789)             // / fail -> explicit failure notification
f3ba1075 src/eu/alefzero/owncloud/files/services/FileUploader.java (David A. Velasco  2012-07-26 18:35:09 +0200 790)             mNotificationManager.cancel(R.string.uploader_upload_in_progress_ticker);
f7649804 src/com/owncloud/android/files/services/FileUploader.java (Matthias Baumann  2013-02-25 08:21:33 +0100 791)             Notification finalNotification = new Notification(R.drawable.icon,
f7649804 src/com/owncloud/android/files/services/FileUploader.java (Matthias Baumann  2013-02-25 08:21:33 +0100 792)                     getString(R.string.uploader_upload_failed_ticker), System.currentTimeMillis());
f3ba1075 src/eu/alefzero/owncloud/files/services/FileUploader.java (David A. Velasco  2012-07-26 18:35:09 +0200 793)             finalNotification.flags |= Notification.FLAG_AUTO_CANCEL;
75eab3de src/com/owncloud/android/files/services/FileUploader.java (masensio          2013-08-21 12:45:07 +0200 794)             String content = null;
75eab3de src/com/owncloud/android/files/services/FileUploader.java (masensio          2013-08-21 12:45:07 +0200 795)             
75eab3de src/com/owncloud/android/files/services/FileUploader.java (masensio          2013-08-21 12:45:07 +0200 796)             boolean needsToUpdateCredentials = (uploadResult.getCode() == ResultCode.UNAUTHORIZED ||
4047c625 src/com/owncloud/android/files/services/FileUploader.java (masensio          2013-08-27 12:32:52 +0200 797)                     //(uploadResult.isTemporalRedirection() && uploadResult.isIdPRedirection() && 
4047c625 src/com/owncloud/android/files/services/FileUploader.java (masensio          2013-08-27 12:32:52 +0200 798)                     (uploadResult.isIdPRedirection() &&
2dc5cc57 src/com/owncloud/android/files/services/FileUploader.java (David A. Velasco  2013-08-22 19:25:15 +0200 799)                             AccountAuthenticator.AUTH_TOKEN_TYPE_SAML_WEB_SSO_SESSION_COOKIE.equals(mUploadClient.getAuthTokenType())));
75eab3de src/com/owncloud/android/files/services/FileUploader.java (masensio          2013-08-21 12:45:07 +0200 800)             if (needsToUpdateCredentials) {
48616361 src/com/owncloud/android/files/services/FileUploader.java (David A. Velasco  2013-03-18 15:05:35 +0100 801)                 // let the user update credentials with one click
48616361 src/com/owncloud/android/files/services/FileUploader.java (David A. Velasco  2013-03-18 15:05:35 +0100 802)                 Intent updateAccountCredentials = new Intent(this, AuthenticatorActivity.class);
48616361 src/com/owncloud/android/files/services/FileUploader.java (David A. Velasco  2013-03-18 15:05:35 +0100 803)                 updateAccountCredentials.putExtra(AuthenticatorActivity.EXTRA_ACCOUNT, upload.getAccount());
8f1566a2 src/com/owncloud/android/files/services/FileUploader.java (David A. Velasco  2013-06-21 14:16:45 +0200 804)                 updateAccountCredentials.putExtra(AuthenticatorActivity.EXTRA_ENFORCED_UPDATE, true);
48616361 src/com/owncloud/android/files/services/FileUploader.java (David A. Velasco  2013-03-18 15:05:35 +0100 805)                 updateAccountCredentials.putExtra(AuthenticatorActivity.EXTRA_ACTION, AuthenticatorActivity.ACTION_UPDATE_TOKEN);
48616361 src/com/owncloud/android/files/services/FileUploader.java (David A. Velasco  2013-03-18 15:05:35 +0100 806)                 updateAccountCredentials.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
48616361 src/com/owncloud/android/files/services/FileUploader.java (David A. Velasco  2013-03-18 15:05:35 +0100 807)                 updateAccountCredentials.addFlags(Intent.FLAG_ACTIVITY_EXCLUDE_FROM_RECENTS);
48616361 src/com/owncloud/android/files/services/FileUploader.java (David A. Velasco  2013-03-18 15:05:35 +0100 808)                 updateAccountCredentials.addFlags(Intent.FLAG_FROM_BACKGROUND);
48616361 src/com/owncloud/android/files/services/FileUploader.java (David A. Velasco  2013-03-18 15:05:35 +0100 809)                 finalNotification.contentIntent = PendingIntent.getActivity(this, (int)System.currentTimeMillis(), updateAccountCredentials, PendingIntent.FLAG_ONE_SHOT);
75eab3de src/com/owncloud/android/files/services/FileUploader.java (masensio          2013-08-21 12:45:07 +0200 810)                 content =  String.format(getString(R.string.uploader_upload_failed_content_single), upload.getFileName());
75eab3de src/com/owncloud/android/files/services/FileUploader.java (masensio          2013-08-21 12:45:07 +0200 811)                 finalNotification.setLatestEventInfo(getApplicationContext(),
75eab3de src/com/owncloud/android/files/services/FileUploader.java (masensio          2013-08-21 12:45:07 +0200 812)                         getString(R.string.uploader_upload_failed_ticker), content, finalNotification.contentIntent);
48616361 src/com/owncloud/android/files/services/FileUploader.java (David A. Velasco  2013-03-18 15:05:35 +0100 813)                 mUploadClient = null;   // grant that future retries on the same account will get the fresh credentials
48616361 src/com/owncloud/android/files/services/FileUploader.java (David A. Velasco  2013-03-18 15:05:35 +0100 814)             } else {
48616361 src/com/owncloud/android/files/services/FileUploader.java (David A. Velasco  2013-03-18 15:05:35 +0100 815)                 // TODO put something smart in the contentIntent below
75eab3de src/com/owncloud/android/files/services/FileUploader.java (masensio          2013-08-21 12:45:07 +0200 816)             //    finalNotification.contentIntent = PendingIntent.getActivity(getApplicationContext(), (int)System.currentTimeMillis(), new Intent(), 0);
75eab3de src/com/owncloud/android/files/services/FileUploader.java (masensio          2013-08-21 12:45:07 +0200 817)             //}
c38a3b2e src/com/owncloud/android/files/services/FileUploader.java (David A. Velasco  2012-12-18 11:20:03 +0100 818)             
75eab3de src/com/owncloud/android/files/services/FileUploader.java (masensio          2013-08-21 12:45:07 +0200 819)                 if (uploadResult.getCode() == ResultCode.LOCAL_STORAGE_FULL
75eab3de src/com/owncloud/android/files/services/FileUploader.java (masensio          2013-08-21 12:45:07 +0200 820)                         || uploadResult.getCode() == ResultCode.LOCAL_STORAGE_NOT_COPIED) {
75eab3de src/com/owncloud/android/files/services/FileUploader.java (masensio          2013-08-21 12:45:07 +0200 821)                     // TODO we need a class to provide error messages for the users
75eab3de src/com/owncloud/android/files/services/FileUploader.java (masensio          2013-08-21 12:45:07 +0200 822)                     // from a RemoteOperationResult and a RemoteOperation
75eab3de src/com/owncloud/android/files/services/FileUploader.java (masensio          2013-08-21 12:45:07 +0200 823)                     content = String.format(getString(R.string.error__upload__local_file_not_copied), upload.getFileName(),
75eab3de src/com/owncloud/android/files/services/FileUploader.java (masensio          2013-08-21 12:45:07 +0200 824)                             getString(R.string.app_name));
75eab3de src/com/owncloud/android/files/services/FileUploader.java (masensio          2013-08-21 12:45:07 +0200 825)                 } else if (uploadResult.getCode() == ResultCode.QUOTA_EXCEEDED) {
75eab3de src/com/owncloud/android/files/services/FileUploader.java (masensio          2013-08-21 12:45:07 +0200 826)                     content = getString(R.string.failed_upload_quota_exceeded_text);
75eab3de src/com/owncloud/android/files/services/FileUploader.java (masensio          2013-08-21 12:45:07 +0200 827)                 } else {
75eab3de src/com/owncloud/android/files/services/FileUploader.java (masensio          2013-08-21 12:45:07 +0200 828)                     content = String
75eab3de src/com/owncloud/android/files/services/FileUploader.java (masensio          2013-08-21 12:45:07 +0200 829)                             .format(getString(R.string.uploader_upload_failed_content_single), upload.getFileName());
75eab3de src/com/owncloud/android/files/services/FileUploader.java (masensio          2013-08-21 12:45:07 +0200 830)                 }
f7649804 src/com/owncloud/android/files/services/FileUploader.java (Matthias Baumann  2013-02-25 08:21:33 +0100 831) 
75eab3de src/com/owncloud/android/files/services/FileUploader.java (masensio          2013-08-21 12:45:07 +0200 832)                 // we add only for instant-uploads the InstantUploadActivity and the
75eab3de src/com/owncloud/android/files/services/FileUploader.java (masensio          2013-08-21 12:45:07 +0200 833)                 // db entry
75eab3de src/com/owncloud/android/files/services/FileUploader.java (masensio          2013-08-21 12:45:07 +0200 834)                 Intent detailUploadIntent = null;
75eab3de src/com/owncloud/android/files/services/FileUploader.java (masensio          2013-08-21 12:45:07 +0200 835)                 if (upload.isInstant() && InstantUploadActivity.IS_ENABLED) {
75eab3de src/com/owncloud/android/files/services/FileUploader.java (masensio          2013-08-21 12:45:07 +0200 836)                     detailUploadIntent = new Intent(this, InstantUploadActivity.class);
75eab3de src/com/owncloud/android/files/services/FileUploader.java (masensio          2013-08-21 12:45:07 +0200 837)                     detailUploadIntent.putExtra(FileUploader.KEY_ACCOUNT, upload.getAccount());
75eab3de src/com/owncloud/android/files/services/FileUploader.java (masensio          2013-08-21 12:45:07 +0200 838)                 } else {
75eab3de src/com/owncloud/android/files/services/FileUploader.java (masensio          2013-08-21 12:45:07 +0200 839)                     detailUploadIntent = new Intent(this, FailedUploadActivity.class);
75eab3de src/com/owncloud/android/files/services/FileUploader.java (masensio          2013-08-21 12:45:07 +0200 840)                     detailUploadIntent.putExtra(FailedUploadActivity.MESSAGE, content);
75eab3de src/com/owncloud/android/files/services/FileUploader.java (masensio          2013-08-21 12:45:07 +0200 841)                 }
75eab3de src/com/owncloud/android/files/services/FileUploader.java (masensio          2013-08-21 12:45:07 +0200 842)                 finalNotification.contentIntent = PendingIntent.getActivity(getApplicationContext(),
75eab3de src/com/owncloud/android/files/services/FileUploader.java (masensio          2013-08-21 12:45:07 +0200 843)                         (int) System.currentTimeMillis(), detailUploadIntent, PendingIntent.FLAG_UPDATE_CURRENT
75eab3de src/com/owncloud/android/files/services/FileUploader.java (masensio          2013-08-21 12:45:07 +0200 844)                         | PendingIntent.FLAG_ONE_SHOT);
75eab3de src/com/owncloud/android/files/services/FileUploader.java (masensio          2013-08-21 12:45:07 +0200 845) 
75eab3de src/com/owncloud/android/files/services/FileUploader.java (masensio          2013-08-21 12:45:07 +0200 846)                 if (upload.isInstant()) {
75eab3de src/com/owncloud/android/files/services/FileUploader.java (masensio          2013-08-21 12:45:07 +0200 847)                     DbHandler db = null;
75eab3de src/com/owncloud/android/files/services/FileUploader.java (masensio          2013-08-21 12:45:07 +0200 848)                     try {
75eab3de src/com/owncloud/android/files/services/FileUploader.java (masensio          2013-08-21 12:45:07 +0200 849)                         db = new DbHandler(this.getBaseContext());
75eab3de src/com/owncloud/android/files/services/FileUploader.java (masensio          2013-08-21 12:45:07 +0200 850)                         String message = uploadResult.getLogMessage() + " errorCode: " + uploadResult.getCode();
75eab3de src/com/owncloud/android/files/services/FileUploader.java (masensio          2013-08-21 12:45:07 +0200 851)                         Log_OC.e(TAG, message + " Http-Code: " + uploadResult.getHttpCode());
75eab3de src/com/owncloud/android/files/services/FileUploader.java (masensio          2013-08-21 12:45:07 +0200 852)                         if (uploadResult.getCode() == ResultCode.QUOTA_EXCEEDED) {
75eab3de src/com/owncloud/android/files/services/FileUploader.java (masensio          2013-08-21 12:45:07 +0200 853)                             message = getString(R.string.failed_upload_quota_exceeded_text);
75eab3de src/com/owncloud/android/files/services/FileUploader.java (masensio          2013-08-21 12:45:07 +0200 854)                         }
75eab3de src/com/owncloud/android/files/services/FileUploader.java (masensio          2013-08-21 12:45:07 +0200 855)                         if (db.updateFileState(upload.getOriginalStoragePath(), DbHandler.UPLOAD_STATUS_UPLOAD_FAILED,
75eab3de src/com/owncloud/android/files/services/FileUploader.java (masensio          2013-08-21 12:45:07 +0200 856)                                 message) == 0) {
75eab3de src/com/owncloud/android/files/services/FileUploader.java (masensio          2013-08-21 12:45:07 +0200 857)                             db.putFileForLater(upload.getOriginalStoragePath(), upload.getAccount().name, message);
75eab3de src/com/owncloud/android/files/services/FileUploader.java (masensio          2013-08-21 12:45:07 +0200 858)                         }
75eab3de src/com/owncloud/android/files/services/FileUploader.java (masensio          2013-08-21 12:45:07 +0200 859)                     } finally {
75eab3de src/com/owncloud/android/files/services/FileUploader.java (masensio          2013-08-21 12:45:07 +0200 860)                         if (db != null) {
75eab3de src/com/owncloud/android/files/services/FileUploader.java (masensio          2013-08-21 12:45:07 +0200 861)                             db.close();
75eab3de src/com/owncloud/android/files/services/FileUploader.java (masensio          2013-08-21 12:45:07 +0200 862)                         }
6c5f5613 src/com/owncloud/android/files/services/FileUploader.java (Matthias Baumann  2013-03-17 10:38:42 +0100 863)                     }
6c5f5613 src/com/owncloud/android/files/services/FileUploader.java (Matthias Baumann  2013-03-17 10:38:42 +0100 864)                 }
f7649804 src/com/owncloud/android/files/services/FileUploader.java (Matthias Baumann  2013-02-25 08:21:33 +0100 865)             }
6c5f5613 src/com/owncloud/android/files/services/FileUploader.java (Matthias Baumann  2013-03-17 10:38:42 +0100 866)             finalNotification.setLatestEventInfo(getApplicationContext(),
6c5f5613 src/com/owncloud/android/files/services/FileUploader.java (Matthias Baumann  2013-03-17 10:38:42 +0100 867)                     getString(R.string.uploader_upload_failed_ticker), content, finalNotification.contentIntent);
75eab3de src/com/owncloud/android/files/services/FileUploader.java (masensio          2013-08-21 12:45:07 +0200 868)             
5fc7cd13 src/com/owncloud/android/files/services/FileUploader.java (David A. Velasco  2012-10-11 11:24:17 +0200 869)             mNotificationManager.notify(R.string.uploader_upload_failed_ticker, finalNotification);
435b31ba src/eu/alefzero/owncloud/files/services/FileUploader.java (Lennart Rosam     2012-05-16 09:48:34 +0200 870)         }
f7649804 src/com/owncloud/android/files/services/FileUploader.java (Matthias Baumann  2013-02-25 08:21:33 +0100 871) 
b999f542 src/eu/alefzero/owncloud/files/services/FileUploader.java (Bartek Przybylski 2012-05-15 21:52:59 +0200 872)     }
f7649804 src/com/owncloud/android/files/services/FileUploader.java (Matthias Baumann  2013-02-25 08:21:33 +0100 873) 
f3ba1075 src/eu/alefzero/owncloud/files/services/FileUploader.java (David A. Velasco  2012-07-26 18:35:09 +0200 874)     /**
f7649804 src/com/owncloud/android/files/services/FileUploader.java (Matthias Baumann  2013-02-25 08:21:33 +0100 875)      * Sends a broadcast in order to the interested activities can update their
f7649804 src/com/owncloud/android/files/services/FileUploader.java (Matthias Baumann  2013-02-25 08:21:33 +0100 876)      * view
5fc7cd13 src/com/owncloud/android/files/services/FileUploader.java (David A. Velasco  2012-10-11 11:24:17 +0200 877)      * 
f7649804 src/com/owncloud/android/files/services/FileUploader.java (Matthias Baumann  2013-02-25 08:21:33 +0100 878)      * @param upload Finished upload operation
f7649804 src/com/owncloud/android/files/services/FileUploader.java (Matthias Baumann  2013-02-25 08:21:33 +0100 879)      * @param uploadResult Result of the upload operation
f3ba1075 src/eu/alefzero/owncloud/files/services/FileUploader.java (David A. Velasco  2012-07-26 18:35:09 +0200 880)      */
27112bd2 src/com/owncloud/android/files/services/FileUploader.java (David A. Velasco  2012-10-11 16:40:56 +0200 881)     private void sendFinalBroadcast(UploadFileOperation upload, RemoteOperationResult uploadResult) {
5fc7cd13 src/com/owncloud/android/files/services/FileUploader.java (David A. Velasco  2012-10-11 11:24:17 +0200 882)         Intent end = new Intent(UPLOAD_FINISH_MESSAGE);
f7649804 src/com/owncloud/android/files/services/FileUploader.java (Matthias Baumann  2013-02-25 08:21:33 +0100 883)         end.putExtra(EXTRA_REMOTE_PATH, upload.getRemotePath()); // real remote
f7649804 src/com/owncloud/android/files/services/FileUploader.java (Matthias Baumann  2013-02-25 08:21:33 +0100 884)                                                                  // path, after
f7649804 src/com/owncloud/android/files/services/FileUploader.java (Matthias Baumann  2013-02-25 08:21:33 +0100 885)                                                                  // possible
f7649804 src/com/owncloud/android/files/services/FileUploader.java (Matthias Baumann  2013-02-25 08:21:33 +0100 886)                                                                  // automatic
f7649804 src/com/owncloud/android/files/services/FileUploader.java (Matthias Baumann  2013-02-25 08:21:33 +0100 887)                                                                  // renaming
c38a3b2e src/com/owncloud/android/files/services/FileUploader.java (David A. Velasco  2012-12-18 11:20:03 +0100 888)         if (upload.wasRenamed()) {
c38a3b2e src/com/owncloud/android/files/services/FileUploader.java (David A. Velasco  2012-12-18 11:20:03 +0100 889)             end.putExtra(EXTRA_OLD_REMOTE_PATH, upload.getOldFile().getRemotePath());
c38a3b2e src/com/owncloud/android/files/services/FileUploader.java (David A. Velasco  2012-12-18 11:20:03 +0100 890)         }
c38a3b2e src/com/owncloud/android/files/services/FileUploader.java (David A. Velasco  2012-12-18 11:20:03 +0100 891)         end.putExtra(EXTRA_OLD_FILE_PATH, upload.getOriginalStoragePath());
5fc7cd13 src/com/owncloud/android/files/services/FileUploader.java (David A. Velasco  2012-10-11 11:24:17 +0200 892)         end.putExtra(ACCOUNT_NAME, upload.getAccount().name);
5fc7cd13 src/com/owncloud/android/files/services/FileUploader.java (David A. Velasco  2012-10-11 11:24:17 +0200 893)         end.putExtra(EXTRA_UPLOAD_RESULT, uploadResult.isSuccess());
c38a3b2e src/com/owncloud/android/files/services/FileUploader.java (David A. Velasco  2012-12-18 11:20:03 +0100 894)         sendStickyBroadcast(end);
35a384d0 src/eu/alefzero/owncloud/files/services/FileUploader.java (Bartek Przybylski 2012-05-19 23:30:23 +0200 895)     }
68ce2e7a src/com/owncloud/android/files/services/FileUploader.java (David A. Velasco  2012-10-09 14:53:25 +0200 896) 
b999f542 src/eu/alefzero/owncloud/files/services/FileUploader.java (Bartek Przybylski 2012-05-15 21:52:59 +0200 897) }
