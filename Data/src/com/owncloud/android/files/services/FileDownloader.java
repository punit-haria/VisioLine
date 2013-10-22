8e36e7cc (zerginator       2013-03-12 07:56:27 +0100   1) /* ownCloud Android client application
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100   2)  *   Copyright (C) 2012 Bartek Przybylski
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100   3)  *   Copyright (C) 2012-2013 ownCloud Inc.
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100   4)  *
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100   5)  *   This program is free software: you can redistribute it and/or modify
bdc0332c (David A. Velasco 2013-04-17 12:26:13 +0200   6)  *   it under the terms of the GNU General Public License version 2,
bdc0332c (David A. Velasco 2013-04-17 12:26:13 +0200   7)  *   as published by the Free Software Foundation.
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100   8)  *
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100   9)  *   This program is distributed in the hope that it will be useful,
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100  10)  *   but WITHOUT ANY WARRANTY; without even the implied warranty of
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100  11)  *   MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100  12)  *   GNU General Public License for more details.
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100  13)  *
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100  14)  *   You should have received a copy of the GNU General Public License
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100  15)  *   along with this program.  If not, see <http://www.gnu.org/licenses/>.
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100  16)  *
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100  17)  */
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100  18) 
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100  19) package com.owncloud.android.files.services;
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100  20) 
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100  21) import java.io.File;
2946d8dd (David A. Velasco 2013-04-25 19:39:22 +0200  22) import java.io.IOException;
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100  23) import java.util.AbstractList;
c3ca5b5a (David A. Velasco 2013-04-15 13:12:04 +0200  24) import java.util.HashMap;
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100  25) import java.util.Iterator;
c3ca5b5a (David A. Velasco 2013-04-15 13:12:04 +0200  26) import java.util.Map;
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100  27) import java.util.Vector;
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100  28) import java.util.concurrent.ConcurrentHashMap;
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100  29) import java.util.concurrent.ConcurrentMap;
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100  30) 
68df3cbf (David A. Velasco 2013-08-12 11:34:09 +0200  31) import com.owncloud.android.authentication.AccountAuthenticator;
2946d8dd (David A. Velasco 2013-04-25 19:39:22 +0200  32) import com.owncloud.android.authentication.AuthenticatorActivity;
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100  33) import com.owncloud.android.datamodel.FileDataStorageManager;
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100  34) import com.owncloud.android.datamodel.OCFile;
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100  35) import eu.alefzero.webdav.OnDatatransferProgressListener;
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100  36) 
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100  37) import com.owncloud.android.network.OwnCloudClientUtils;
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100  38) import com.owncloud.android.operations.DownloadFileOperation;
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100  39) import com.owncloud.android.operations.RemoteOperationResult;
2946d8dd (David A. Velasco 2013-04-25 19:39:22 +0200  40) import com.owncloud.android.operations.RemoteOperationResult.ResultCode;
bc1fcf84 (David A. Velasco 2013-05-07 13:49:54 +0200  41) import com.owncloud.android.ui.activity.FileActivity;
fd396289 (David A. Velasco 2013-05-30 17:53:21 +0200  42) import com.owncloud.android.ui.activity.FileDisplayActivity;
c3ca5b5a (David A. Velasco 2013-04-15 13:12:04 +0200  43) import com.owncloud.android.ui.preview.PreviewImageActivity;
c3ca5b5a (David A. Velasco 2013-04-15 13:12:04 +0200  44) import com.owncloud.android.ui.preview.PreviewImageFragment;
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100  45) 
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100  46) import android.accounts.Account;
2946d8dd (David A. Velasco 2013-04-25 19:39:22 +0200  47) import android.accounts.AccountsException;
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100  48) import android.app.Notification;
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100  49) import android.app.NotificationManager;
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100  50) import android.app.PendingIntent;
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100  51) import android.app.Service;
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100  52) import android.content.Intent;
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100  53) import android.os.Binder;
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100  54) import android.os.Handler;
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100  55) import android.os.HandlerThread;
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100  56) import android.os.IBinder;
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100  57) import android.os.Looper;
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100  58) import android.os.Message;
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100  59) import android.os.Process;
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100  60) import android.widget.RemoteViews;
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100  61) 
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100  62) import com.owncloud.android.Log_OC;
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100  63) import com.owncloud.android.R;
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100  64) import eu.alefzero.webdav.WebdavClient;
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100  65) 
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100  66) public class FileDownloader extends Service implements OnDatatransferProgressListener {
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100  67)     
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100  68)     public static final String EXTRA_ACCOUNT = "ACCOUNT";
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100  69)     public static final String EXTRA_FILE = "FILE";
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100  70)     
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100  71)     public static final String DOWNLOAD_ADDED_MESSAGE = "DOWNLOAD_ADDED";
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100  72)     public static final String DOWNLOAD_FINISH_MESSAGE = "DOWNLOAD_FINISH";
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100  73)     public static final String EXTRA_DOWNLOAD_RESULT = "RESULT";    
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100  74)     public static final String EXTRA_FILE_PATH = "FILE_PATH";
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100  75)     public static final String EXTRA_REMOTE_PATH = "REMOTE_PATH";
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100  76)     public static final String ACCOUNT_NAME = "ACCOUNT_NAME";
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100  77)     
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100  78)     private static final String TAG = "FileDownloader";
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100  79) 
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100  80)     private Looper mServiceLooper;
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100  81)     private ServiceHandler mServiceHandler;
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100  82)     private IBinder mBinder;
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100  83)     private WebdavClient mDownloadClient = null;
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100  84)     private Account mLastAccount = null;
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100  85)     private FileDataStorageManager mStorageManager;
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100  86)     
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100  87)     private ConcurrentMap<String, DownloadFileOperation> mPendingDownloads = new ConcurrentHashMap<String, DownloadFileOperation>();
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100  88)     private DownloadFileOperation mCurrentDownload = null;
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100  89)     
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100  90)     private NotificationManager mNotificationManager;
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100  91)     private Notification mNotification;
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100  92)     private int mLastPercent;
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100  93)     
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100  94)     
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100  95)     /**
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100  96)      * Builds a key for mPendingDownloads from the account and file to download
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100  97)      * 
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100  98)      * @param account   Account where the file to download is stored
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100  99)      * @param file      File to download
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100 100)      */
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100 101)     private String buildRemoteName(Account account, OCFile file) {
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100 102)         return account.name + file.getRemotePath();
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100 103)     }
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100 104) 
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100 105)     
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100 106)     /**
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100 107)      * Service initialization
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100 108)      */
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100 109)     @Override
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100 110)     public void onCreate() {
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100 111)         super.onCreate();
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100 112)         mNotificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100 113)         HandlerThread thread = new HandlerThread("FileDownloaderThread",
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100 114)                 Process.THREAD_PRIORITY_BACKGROUND);
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100 115)         thread.start();
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100 116)         mServiceLooper = thread.getLooper();
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100 117)         mServiceHandler = new ServiceHandler(mServiceLooper, this);
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100 118)         mBinder = new FileDownloaderBinder();
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100 119)     }
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100 120) 
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100 121)     /**
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100 122)      * Entry point to add one or several files to the queue of downloads.
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100 123)      * 
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100 124)      * New downloads are added calling to startService(), resulting in a call to this method. This ensures the service will keep on working 
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100 125)      * although the caller activity goes away.
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100 126)      */
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100 127)     @Override
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100 128)     public int onStartCommand(Intent intent, int flags, int startId) {
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100 129)         if (    !intent.hasExtra(EXTRA_ACCOUNT) ||
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100 130)                 !intent.hasExtra(EXTRA_FILE)
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100 131)                 /*!intent.hasExtra(EXTRA_FILE_PATH) ||
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100 132)                 !intent.hasExtra(EXTRA_REMOTE_PATH)*/
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100 133)            ) {
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100 134)             Log_OC.e(TAG, "Not enough information provided in intent");
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100 135)             return START_NOT_STICKY;
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100 136)         }
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100 137)         Account account = intent.getParcelableExtra(EXTRA_ACCOUNT);
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100 138)         OCFile file = intent.getParcelableExtra(EXTRA_FILE);
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100 139)         
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100 140)         AbstractList<String> requestedDownloads = new Vector<String>(); // dvelasco: now this always contains just one element, but that can change in a near future (download of multiple selection)
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100 141)         String downloadKey = buildRemoteName(account, file);
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100 142)         try {
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100 143)             DownloadFileOperation newDownload = new DownloadFileOperation(account, file); 
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100 144)             mPendingDownloads.putIfAbsent(downloadKey, newDownload);
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100 145)             newDownload.addDatatransferProgressListener(this);
c3ca5b5a (David A. Velasco 2013-04-15 13:12:04 +0200 146)             newDownload.addDatatransferProgressListener((FileDownloaderBinder)mBinder);
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100 147)             requestedDownloads.add(downloadKey);
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100 148)             sendBroadcastNewDownload(newDownload);
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100 149)             
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100 150)         } catch (IllegalArgumentException e) {
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100 151)             Log_OC.e(TAG, "Not enough information provided in intent: " + e.getMessage());
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100 152)             return START_NOT_STICKY;
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100 153)         }
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100 154)         
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100 155)         if (requestedDownloads.size() > 0) {
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100 156)             Message msg = mServiceHandler.obtainMessage();
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100 157)             msg.arg1 = startId;
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100 158)             msg.obj = requestedDownloads;
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100 159)             mServiceHandler.sendMessage(msg);
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100 160)         }
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100 161) 
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100 162)         return START_NOT_STICKY;
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100 163)     }
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100 164)     
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100 165)     
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100 166)     /**
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100 167)      * Provides a binder object that clients can use to perform operations on the queue of downloads, excepting the addition of new files. 
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100 168)      * 
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100 169)      * Implemented to perform cancellation, pause and resume of existing downloads.
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100 170)      */
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100 171)     @Override
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100 172)     public IBinder onBind(Intent arg0) {
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100 173)         return mBinder;
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100 174)     }
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100 175) 
c3ca5b5a (David A. Velasco 2013-04-15 13:12:04 +0200 176) 
c3ca5b5a (David A. Velasco 2013-04-15 13:12:04 +0200 177)     /**
c3ca5b5a (David A. Velasco 2013-04-15 13:12:04 +0200 178)      * Called when ALL the bound clients were onbound.
c3ca5b5a (David A. Velasco 2013-04-15 13:12:04 +0200 179)      */
c3ca5b5a (David A. Velasco 2013-04-15 13:12:04 +0200 180)     @Override
c3ca5b5a (David A. Velasco 2013-04-15 13:12:04 +0200 181)     public boolean onUnbind(Intent intent) {
c3ca5b5a (David A. Velasco 2013-04-15 13:12:04 +0200 182)         ((FileDownloaderBinder)mBinder).clearListeners();
c3ca5b5a (David A. Velasco 2013-04-15 13:12:04 +0200 183)         return false;   // not accepting rebinding (default behaviour)
c3ca5b5a (David A. Velasco 2013-04-15 13:12:04 +0200 184)     }
c3ca5b5a (David A. Velasco 2013-04-15 13:12:04 +0200 185) 
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100 186)     
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100 187)     /**
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100 188)      *  Binder to let client components to perform operations on the queue of downloads.
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100 189)      * 
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100 190)      *  It provides by itself the available operations.
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100 191)      */
c3ca5b5a (David A. Velasco 2013-04-15 13:12:04 +0200 192)     public class FileDownloaderBinder extends Binder implements OnDatatransferProgressListener {
c3ca5b5a (David A. Velasco 2013-04-15 13:12:04 +0200 193)         
c3ca5b5a (David A. Velasco 2013-04-15 13:12:04 +0200 194)         /** 
c3ca5b5a (David A. Velasco 2013-04-15 13:12:04 +0200 195)          * Map of listeners that will be reported about progress of downloads from a {@link FileDownloaderBinder} instance 
c3ca5b5a (David A. Velasco 2013-04-15 13:12:04 +0200 196)          */
c3ca5b5a (David A. Velasco 2013-04-15 13:12:04 +0200 197)         private Map<String, OnDatatransferProgressListener> mBoundListeners = new HashMap<String, OnDatatransferProgressListener>();
c3ca5b5a (David A. Velasco 2013-04-15 13:12:04 +0200 198)         
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100 199)         
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100 200)         /**
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100 201)          * Cancels a pending or current download of a remote file.
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100 202)          * 
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100 203)          * @param account       Owncloud account where the remote file is stored.
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100 204)          * @param file          A file in the queue of pending downloads
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100 205)          */
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100 206)         public void cancel(Account account, OCFile file) {
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100 207)             DownloadFileOperation download = null;
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100 208)             synchronized (mPendingDownloads) {
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100 209)                 download = mPendingDownloads.remove(buildRemoteName(account, file));
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100 210)             }
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100 211)             if (download != null) {
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100 212)                 download.cancel();
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100 213)             }
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100 214)         }
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100 215)         
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100 216)         
c3ca5b5a (David A. Velasco 2013-04-15 13:12:04 +0200 217)         public void clearListeners() {
c3ca5b5a (David A. Velasco 2013-04-15 13:12:04 +0200 218)             mBoundListeners.clear();
c3ca5b5a (David A. Velasco 2013-04-15 13:12:04 +0200 219)         }
c3ca5b5a (David A. Velasco 2013-04-15 13:12:04 +0200 220) 
c3ca5b5a (David A. Velasco 2013-04-15 13:12:04 +0200 221) 
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100 222)         /**
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100 223)          * Returns True when the file described by 'file' in the ownCloud account 'account' is downloading or waiting to download.
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100 224)          * 
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100 225)          * If 'file' is a directory, returns 'true' if some of its descendant files is downloading or waiting to download. 
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100 226)          * 
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100 227)          * @param account       Owncloud account where the remote file is stored.
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100 228)          * @param file          A file that could be in the queue of downloads.
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100 229)          */
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100 230)         public boolean isDownloading(Account account, OCFile file) {
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100 231)             if (account == null || file == null) return false;
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100 232)             String targetKey = buildRemoteName(account, file);
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100 233)             synchronized (mPendingDownloads) {
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100 234)                 if (file.isDirectory()) {
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100 235)                     // this can be slow if there are many downloads :(
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100 236)                     Iterator<String> it = mPendingDownloads.keySet().iterator();
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100 237)                     boolean found = false;
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100 238)                     while (it.hasNext() && !found) {
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100 239)                         found = it.next().startsWith(targetKey);
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100 240)                     }
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100 241)                     return found;
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100 242)                 } else {
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100 243)                     return (mPendingDownloads.containsKey(targetKey));
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100 244)                 }
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100 245)             }
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100 246)         }
c3ca5b5a (David A. Velasco 2013-04-15 13:12:04 +0200 247) 
c3ca5b5a (David A. Velasco 2013-04-15 13:12:04 +0200 248)         
c3ca5b5a (David A. Velasco 2013-04-15 13:12:04 +0200 249)         /**
c3ca5b5a (David A. Velasco 2013-04-15 13:12:04 +0200 250)          * Adds a listener interested in the progress of the download for a concrete file.
c3ca5b5a (David A. Velasco 2013-04-15 13:12:04 +0200 251)          * 
c3ca5b5a (David A. Velasco 2013-04-15 13:12:04 +0200 252)          * @param listener      Object to notify about progress of transfer.    
c3ca5b5a (David A. Velasco 2013-04-15 13:12:04 +0200 253)          * @param account       ownCloud account holding the file of interest.
c3ca5b5a (David A. Velasco 2013-04-15 13:12:04 +0200 254)          * @param file          {@link OCfile} of interest for listener. 
c3ca5b5a (David A. Velasco 2013-04-15 13:12:04 +0200 255)          */
c3ca5b5a (David A. Velasco 2013-04-15 13:12:04 +0200 256)         public void addDatatransferProgressListener (OnDatatransferProgressListener listener, Account account, OCFile file) {
c3ca5b5a (David A. Velasco 2013-04-15 13:12:04 +0200 257)             if (account == null || file == null || listener == null) return;
c3ca5b5a (David A. Velasco 2013-04-15 13:12:04 +0200 258)             String targetKey = buildRemoteName(account, file);
c3ca5b5a (David A. Velasco 2013-04-15 13:12:04 +0200 259)             mBoundListeners.put(targetKey, listener);
c3ca5b5a (David A. Velasco 2013-04-15 13:12:04 +0200 260)         }
c3ca5b5a (David A. Velasco 2013-04-15 13:12:04 +0200 261)         
c3ca5b5a (David A. Velasco 2013-04-15 13:12:04 +0200 262)         
c3ca5b5a (David A. Velasco 2013-04-15 13:12:04 +0200 263)         /**
c3ca5b5a (David A. Velasco 2013-04-15 13:12:04 +0200 264)          * Removes a listener interested in the progress of the download for a concrete file.
c3ca5b5a (David A. Velasco 2013-04-15 13:12:04 +0200 265)          * 
c3ca5b5a (David A. Velasco 2013-04-15 13:12:04 +0200 266)          * @param listener      Object to notify about progress of transfer.    
c3ca5b5a (David A. Velasco 2013-04-15 13:12:04 +0200 267)          * @param account       ownCloud account holding the file of interest.
c3ca5b5a (David A. Velasco 2013-04-15 13:12:04 +0200 268)          * @param file          {@link OCfile} of interest for listener. 
c3ca5b5a (David A. Velasco 2013-04-15 13:12:04 +0200 269)          */
c3ca5b5a (David A. Velasco 2013-04-15 13:12:04 +0200 270)         public void removeDatatransferProgressListener (OnDatatransferProgressListener listener, Account account, OCFile file) {
c3ca5b5a (David A. Velasco 2013-04-15 13:12:04 +0200 271)             if (account == null || file == null || listener == null) return;
c3ca5b5a (David A. Velasco 2013-04-15 13:12:04 +0200 272)             String targetKey = buildRemoteName(account, file);
c3ca5b5a (David A. Velasco 2013-04-15 13:12:04 +0200 273)             if (mBoundListeners.get(targetKey) == listener) {
c3ca5b5a (David A. Velasco 2013-04-15 13:12:04 +0200 274)                 mBoundListeners.remove(targetKey);
c3ca5b5a (David A. Velasco 2013-04-15 13:12:04 +0200 275)             }
c3ca5b5a (David A. Velasco 2013-04-15 13:12:04 +0200 276)         }
c3ca5b5a (David A. Velasco 2013-04-15 13:12:04 +0200 277) 
c3ca5b5a (David A. Velasco 2013-04-15 13:12:04 +0200 278) 
c3ca5b5a (David A. Velasco 2013-04-15 13:12:04 +0200 279)         @Override
c3ca5b5a (David A. Velasco 2013-04-15 13:12:04 +0200 280)         public void onTransferProgress(long progressRate) {
c3ca5b5a (David A. Velasco 2013-04-15 13:12:04 +0200 281)             // old way, should not be in use any more
c3ca5b5a (David A. Velasco 2013-04-15 13:12:04 +0200 282)         }
c3ca5b5a (David A. Velasco 2013-04-15 13:12:04 +0200 283) 
c3ca5b5a (David A. Velasco 2013-04-15 13:12:04 +0200 284) 
c3ca5b5a (David A. Velasco 2013-04-15 13:12:04 +0200 285)         @Override
c3ca5b5a (David A. Velasco 2013-04-15 13:12:04 +0200 286)         public void onTransferProgress(long progressRate, long totalTransferredSoFar, long totalToTransfer,
c3ca5b5a (David A. Velasco 2013-04-15 13:12:04 +0200 287)                 String fileName) {
c3ca5b5a (David A. Velasco 2013-04-15 13:12:04 +0200 288)             String key = buildRemoteName(mCurrentDownload.getAccount(), mCurrentDownload.getFile());
c3ca5b5a (David A. Velasco 2013-04-15 13:12:04 +0200 289)             OnDatatransferProgressListener boundListener = mBoundListeners.get(key);
c3ca5b5a (David A. Velasco 2013-04-15 13:12:04 +0200 290)             if (boundListener != null) {
c3ca5b5a (David A. Velasco 2013-04-15 13:12:04 +0200 291)                 boundListener.onTransferProgress(progressRate, totalTransferredSoFar, totalToTransfer, fileName);
c3ca5b5a (David A. Velasco 2013-04-15 13:12:04 +0200 292)             }
c3ca5b5a (David A. Velasco 2013-04-15 13:12:04 +0200 293)         }
c3ca5b5a (David A. Velasco 2013-04-15 13:12:04 +0200 294)         
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100 295)     }
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100 296)     
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100 297)     
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100 298)     /** 
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100 299)      * Download worker. Performs the pending downloads in the order they were requested. 
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100 300)      * 
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100 301)      * Created with the Looper of a new thread, started in {@link FileUploader#onCreate()}. 
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100 302)      */
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100 303)     private static class ServiceHandler extends Handler {
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100 304)         // don't make it a final class, and don't remove the static ; lint will warn about a possible memory leak
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100 305)         FileDownloader mService;
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100 306)         public ServiceHandler(Looper looper, FileDownloader service) {
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100 307)             super(looper);
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100 308)             if (service == null)
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100 309)                 throw new IllegalArgumentException("Received invalid NULL in parameter 'service'");
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100 310)             mService = service;
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100 311)         }
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100 312) 
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100 313)         @Override
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100 314)         public void handleMessage(Message msg) {
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100 315)             @SuppressWarnings("unchecked")
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100 316)             AbstractList<String> requestedDownloads = (AbstractList<String>) msg.obj;
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100 317)             if (msg.obj != null) {
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100 318)                 Iterator<String> it = requestedDownloads.iterator();
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100 319)                 while (it.hasNext()) {
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100 320)                     mService.downloadFile(it.next());
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100 321)                 }
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100 322)             }
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100 323)             mService.stopSelf(msg.arg1);
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100 324)         }
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100 325)     }
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100 326)     
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100 327) 
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100 328)     /**
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100 329)      * Core download method: requests a file to download and stores it.
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100 330)      * 
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100 331)      * @param downloadKey   Key to access the download to perform, contained in mPendingDownloads 
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100 332)      */
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100 333)     private void downloadFile(String downloadKey) {
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100 334)         
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100 335)         synchronized(mPendingDownloads) {
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100 336)             mCurrentDownload = mPendingDownloads.get(downloadKey);
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100 337)         }
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100 338)         
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100 339)         if (mCurrentDownload != null) {
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100 340)             
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100 341)             notifyDownloadStart(mCurrentDownload);
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100 342) 
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100 343)             RemoteOperationResult downloadResult = null;
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100 344)             try {
2946d8dd (David A. Velasco 2013-04-25 19:39:22 +0200 345)                 /// prepare client object to send the request to the ownCloud server
2946d8dd (David A. Velasco 2013-04-25 19:39:22 +0200 346)                 if (mDownloadClient == null || !mLastAccount.equals(mCurrentDownload.getAccount())) {
2946d8dd (David A. Velasco 2013-04-25 19:39:22 +0200 347)                     mLastAccount = mCurrentDownload.getAccount();
2946d8dd (David A. Velasco 2013-04-25 19:39:22 +0200 348)                     mStorageManager = new FileDataStorageManager(mLastAccount, getContentResolver());
2946d8dd (David A. Velasco 2013-04-25 19:39:22 +0200 349)                     mDownloadClient = OwnCloudClientUtils.createOwnCloudClient(mLastAccount, getApplicationContext());
2946d8dd (David A. Velasco 2013-04-25 19:39:22 +0200 350)                 }
2946d8dd (David A. Velasco 2013-04-25 19:39:22 +0200 351) 
2946d8dd (David A. Velasco 2013-04-25 19:39:22 +0200 352)                 /// perform the download
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100 353)                 downloadResult = mCurrentDownload.execute(mDownloadClient);
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100 354)                 if (downloadResult.isSuccess()) {
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100 355)                     saveDownloadedFile();
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100 356)                 }
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100 357)             
2946d8dd (David A. Velasco 2013-04-25 19:39:22 +0200 358)             } catch (AccountsException e) {
2946d8dd (David A. Velasco 2013-04-25 19:39:22 +0200 359)                 Log_OC.e(TAG, "Error while trying to get autorization for " + mLastAccount.name, e);
2946d8dd (David A. Velasco 2013-04-25 19:39:22 +0200 360)                 downloadResult = new RemoteOperationResult(e);
2946d8dd (David A. Velasco 2013-04-25 19:39:22 +0200 361)             } catch (IOException e) {
2946d8dd (David A. Velasco 2013-04-25 19:39:22 +0200 362)                 Log_OC.e(TAG, "Error while trying to get autorization for " + mLastAccount.name, e);
2946d8dd (David A. Velasco 2013-04-25 19:39:22 +0200 363)                 downloadResult = new RemoteOperationResult(e);
2946d8dd (David A. Velasco 2013-04-25 19:39:22 +0200 364)                 
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100 365)             } finally {
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100 366)                 synchronized(mPendingDownloads) {
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100 367)                     mPendingDownloads.remove(downloadKey);
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100 368)                 }
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100 369)             }
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100 370) 
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100 371)             
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100 372)             /// notify result
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100 373)             notifyDownloadResult(mCurrentDownload, downloadResult);
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100 374)             
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100 375)             sendBroadcastDownloadFinished(mCurrentDownload, downloadResult);
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100 376)         }
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100 377)     }
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100 378) 
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100 379) 
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100 380)     /**
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100 381)      * Updates the OC File after a successful download.
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100 382)      */
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100 383)     private void saveDownloadedFile() {
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100 384)         OCFile file = mCurrentDownload.getFile();
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100 385)         long syncDate = System.currentTimeMillis();
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100 386)         file.setLastSyncDateForProperties(syncDate);
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100 387)         file.setLastSyncDateForData(syncDate);
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100 388)         file.setModificationTimestamp(mCurrentDownload.getModificationTimestamp());
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100 389)         file.setModificationTimestampAtLastSyncForData(mCurrentDownload.getModificationTimestamp());
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100 390)         // file.setEtag(mCurrentDownload.getEtag());    // TODO Etag, where available
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100 391)         file.setMimetype(mCurrentDownload.getMimeType());
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100 392)         file.setStoragePath(mCurrentDownload.getSavePath());
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100 393)         file.setFileLength((new File(mCurrentDownload.getSavePath()).length()));
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100 394)         mStorageManager.saveFile(file);
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100 395)     }
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100 396) 
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100 397) 
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100 398)     /**
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100 399)      * Creates a status notification to show the download progress
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100 400)      * 
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100 401)      * @param download  Download operation starting.
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100 402)      */
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100 403)     private void notifyDownloadStart(DownloadFileOperation download) {
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100 404)         /// create status notification with a progress bar
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100 405)         mLastPercent = 0;
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100 406)         mNotification = new Notification(R.drawable.icon, getString(R.string.downloader_download_in_progress_ticker), System.currentTimeMillis());
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100 407)         mNotification.flags |= Notification.FLAG_ONGOING_EVENT;
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100 408)         mNotification.contentView = new RemoteViews(getApplicationContext().getPackageName(), R.layout.progressbar_layout);
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100 409)         mNotification.contentView.setProgressBar(R.id.status_progress, 100, 0, download.getSize() < 0);
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100 410)         mNotification.contentView.setTextViewText(R.id.status_text, String.format(getString(R.string.downloader_download_in_progress_content), 0, new File(download.getSavePath()).getName()));
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100 411)         mNotification.contentView.setImageViewResource(R.id.status_icon, R.drawable.icon);
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100 412)         
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100 413)         /// includes a pending intent in the notification showing the details view of the file
c3ca5b5a (David A. Velasco 2013-04-15 13:12:04 +0200 414)         Intent showDetailsIntent = null;
c3ca5b5a (David A. Velasco 2013-04-15 13:12:04 +0200 415)         if (PreviewImageFragment.canBePreviewed(download.getFile())) {
c3ca5b5a (David A. Velasco 2013-04-15 13:12:04 +0200 416)             showDetailsIntent = new Intent(this, PreviewImageActivity.class);
c3ca5b5a (David A. Velasco 2013-04-15 13:12:04 +0200 417)         } else {
fd396289 (David A. Velasco 2013-05-30 17:53:21 +0200 418)             showDetailsIntent = new Intent(this, FileDisplayActivity.class);
c3ca5b5a (David A. Velasco 2013-04-15 13:12:04 +0200 419)         }
bc1fcf84 (David A. Velasco 2013-05-07 13:49:54 +0200 420)         showDetailsIntent.putExtra(FileActivity.EXTRA_FILE, download.getFile());
bc1fcf84 (David A. Velasco 2013-05-07 13:49:54 +0200 421)         showDetailsIntent.putExtra(FileActivity.EXTRA_ACCOUNT, download.getAccount());
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100 422)         showDetailsIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100 423)         mNotification.contentIntent = PendingIntent.getActivity(getApplicationContext(), (int)System.currentTimeMillis(), showDetailsIntent, 0);
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100 424)         
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100 425)         mNotificationManager.notify(R.string.downloader_download_in_progress_ticker, mNotification);
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100 426)     }
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100 427) 
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100 428)     
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100 429)     /**
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100 430)      * Callback method to update the progress bar in the status notification.
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100 431)      */
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100 432)     @Override
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100 433)     public void onTransferProgress(long progressRate, long totalTransferredSoFar, long totalToTransfer, String fileName) {
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100 434)         int percent = (int)(100.0*((double)totalTransferredSoFar)/((double)totalToTransfer));
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100 435)         if (percent != mLastPercent) {
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100 436)           mNotification.contentView.setProgressBar(R.id.status_progress, 100, percent, totalToTransfer < 0);
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100 437)           String text = String.format(getString(R.string.downloader_download_in_progress_content), percent, fileName);
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100 438)           mNotification.contentView.setTextViewText(R.id.status_text, text);
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100 439)           mNotificationManager.notify(R.string.downloader_download_in_progress_ticker, mNotification);
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100 440)         }
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100 441)         mLastPercent = percent;
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100 442)     }
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100 443)     
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100 444)     
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100 445)     /**
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100 446)      * Callback method to update the progress bar in the status notification (old version)
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100 447)      */
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100 448)     @Override
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100 449)     public void onTransferProgress(long progressRate) {
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100 450)         // NOTHING TO DO HERE ANYMORE
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100 451)     }
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100 452)     
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100 453) 
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100 454)     /**
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100 455)      * Updates the status notification with the result of a download operation.
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100 456)      * 
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100 457)      * @param downloadResult    Result of the download operation.
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100 458)      * @param download          Finished download operation
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100 459)      */
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100 460)     private void notifyDownloadResult(DownloadFileOperation download, RemoteOperationResult downloadResult) {
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100 461)         mNotificationManager.cancel(R.string.downloader_download_in_progress_ticker);
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100 462)         if (!downloadResult.isCancelled()) {
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100 463)             int tickerId = (downloadResult.isSuccess()) ? R.string.downloader_download_succeeded_ticker : R.string.downloader_download_failed_ticker;
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100 464)             int contentId = (downloadResult.isSuccess()) ? R.string.downloader_download_succeeded_content : R.string.downloader_download_failed_content;
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100 465)             Notification finalNotification = new Notification(R.drawable.icon, getString(tickerId), System.currentTimeMillis());
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100 466)             finalNotification.flags |= Notification.FLAG_AUTO_CANCEL;
68df3cbf (David A. Velasco 2013-08-12 11:34:09 +0200 467)             boolean needsToUpdateCredentials = (downloadResult.getCode() == ResultCode.UNAUTHORIZED ||
4047c625 (masensio         2013-08-27 12:32:52 +0200 468)                                                 // (downloadResult.isTemporalRedirection() && downloadResult.isIdPRedirection()
4047c625 (masensio         2013-08-27 12:32:52 +0200 469)                                                   (downloadResult.isIdPRedirection()
7ed9e800 (masensio         2013-08-21 18:31:57 +0200 470)                                                         && AccountAuthenticator.AUTH_TOKEN_TYPE_SAML_WEB_SSO_SESSION_COOKIE.equals(mDownloadClient.getAuthTokenType())));
2946d8dd (David A. Velasco 2013-04-25 19:39:22 +0200 471)             if (needsToUpdateCredentials) {
2946d8dd (David A. Velasco 2013-04-25 19:39:22 +0200 472)                 // let the user update credentials with one click
2946d8dd (David A. Velasco 2013-04-25 19:39:22 +0200 473)                 Intent updateAccountCredentials = new Intent(this, AuthenticatorActivity.class);
2946d8dd (David A. Velasco 2013-04-25 19:39:22 +0200 474)                 updateAccountCredentials.putExtra(AuthenticatorActivity.EXTRA_ACCOUNT, download.getAccount());
8f1566a2 (David A. Velasco 2013-06-21 14:16:45 +0200 475)                 updateAccountCredentials.putExtra(AuthenticatorActivity.EXTRA_ENFORCED_UPDATE, true);
2946d8dd (David A. Velasco 2013-04-25 19:39:22 +0200 476)                 updateAccountCredentials.putExtra(AuthenticatorActivity.EXTRA_ACTION, AuthenticatorActivity.ACTION_UPDATE_TOKEN);
2946d8dd (David A. Velasco 2013-04-25 19:39:22 +0200 477)                 updateAccountCredentials.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
2946d8dd (David A. Velasco 2013-04-25 19:39:22 +0200 478)                 updateAccountCredentials.addFlags(Intent.FLAG_ACTIVITY_EXCLUDE_FROM_RECENTS);
2946d8dd (David A. Velasco 2013-04-25 19:39:22 +0200 479)                 updateAccountCredentials.addFlags(Intent.FLAG_FROM_BACKGROUND);
2946d8dd (David A. Velasco 2013-04-25 19:39:22 +0200 480)                 finalNotification.contentIntent = PendingIntent.getActivity(this, (int)System.currentTimeMillis(), updateAccountCredentials, PendingIntent.FLAG_ONE_SHOT);
2946d8dd (David A. Velasco 2013-04-25 19:39:22 +0200 481)                 finalNotification.setLatestEventInfo(   getApplicationContext(), 
2946d8dd (David A. Velasco 2013-04-25 19:39:22 +0200 482)                                                         getString(tickerId), 
2946d8dd (David A. Velasco 2013-04-25 19:39:22 +0200 483)                                                         String.format(getString(contentId), new File(download.getSavePath()).getName()),
2946d8dd (David A. Velasco 2013-04-25 19:39:22 +0200 484)                                                         finalNotification.contentIntent);
2946d8dd (David A. Velasco 2013-04-25 19:39:22 +0200 485)                 mDownloadClient = null;   // grant that future retries on the same account will get the fresh credentials
c3ca5b5a (David A. Velasco 2013-04-15 13:12:04 +0200 486)                 
c3ca5b5a (David A. Velasco 2013-04-15 13:12:04 +0200 487)             } else {
2946d8dd (David A. Velasco 2013-04-25 19:39:22 +0200 488)                 Intent showDetailsIntent = null;
2946d8dd (David A. Velasco 2013-04-25 19:39:22 +0200 489)                 if (downloadResult.isSuccess()) {
2946d8dd (David A. Velasco 2013-04-25 19:39:22 +0200 490)                     if (PreviewImageFragment.canBePreviewed(download.getFile())) {
2946d8dd (David A. Velasco 2013-04-25 19:39:22 +0200 491)                         showDetailsIntent = new Intent(this, PreviewImageActivity.class);
2946d8dd (David A. Velasco 2013-04-25 19:39:22 +0200 492)                     } else {
fd396289 (David A. Velasco 2013-05-30 17:53:21 +0200 493)                         showDetailsIntent = new Intent(this, FileDisplayActivity.class);
2946d8dd (David A. Velasco 2013-04-25 19:39:22 +0200 494)                     }
bc1fcf84 (David A. Velasco 2013-05-07 13:49:54 +0200 495)                     showDetailsIntent.putExtra(FileActivity.EXTRA_FILE, download.getFile());
bc1fcf84 (David A. Velasco 2013-05-07 13:49:54 +0200 496)                     showDetailsIntent.putExtra(FileActivity.EXTRA_ACCOUNT, download.getAccount());
2946d8dd (David A. Velasco 2013-04-25 19:39:22 +0200 497)                     showDetailsIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
2946d8dd (David A. Velasco 2013-04-25 19:39:22 +0200 498)                     
2946d8dd (David A. Velasco 2013-04-25 19:39:22 +0200 499)                 } else {
2946d8dd (David A. Velasco 2013-04-25 19:39:22 +0200 500)                     // TODO put something smart in showDetailsIntent
2946d8dd (David A. Velasco 2013-04-25 19:39:22 +0200 501)                     showDetailsIntent = new Intent();
2946d8dd (David A. Velasco 2013-04-25 19:39:22 +0200 502)                 }
2946d8dd (David A. Velasco 2013-04-25 19:39:22 +0200 503)                 finalNotification.contentIntent = PendingIntent.getActivity(getApplicationContext(), (int)System.currentTimeMillis(), showDetailsIntent, 0);
2946d8dd (David A. Velasco 2013-04-25 19:39:22 +0200 504)                 finalNotification.setLatestEventInfo(getApplicationContext(), getString(tickerId), String.format(getString(contentId), new File(download.getSavePath()).getName()), finalNotification.contentIntent);
c3ca5b5a (David A. Velasco 2013-04-15 13:12:04 +0200 505)             }
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100 506)             mNotificationManager.notify(tickerId, finalNotification);
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100 507)         }
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100 508)     }
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100 509)     
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100 510)     
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100 511)     /**
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100 512)      * Sends a broadcast when a download finishes in order to the interested activities can update their view
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100 513)      * 
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100 514)      * @param download          Finished download operation
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100 515)      * @param downloadResult    Result of the download operation
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100 516)      */
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100 517)     private void sendBroadcastDownloadFinished(DownloadFileOperation download, RemoteOperationResult downloadResult) {
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100 518)         Intent end = new Intent(DOWNLOAD_FINISH_MESSAGE);
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100 519)         end.putExtra(EXTRA_DOWNLOAD_RESULT, downloadResult.isSuccess());
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100 520)         end.putExtra(ACCOUNT_NAME, download.getAccount().name);
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100 521)         end.putExtra(EXTRA_REMOTE_PATH, download.getRemotePath());
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100 522)         end.putExtra(EXTRA_FILE_PATH, download.getSavePath());
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100 523)         sendStickyBroadcast(end);
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100 524)     }
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100 525)     
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100 526)     
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100 527)     /**
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100 528)      * Sends a broadcast when a new download is added to the queue.
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100 529)      * 
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100 530)      * @param download          Added download operation
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100 531)      */
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100 532)     private void sendBroadcastNewDownload(DownloadFileOperation download) {
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100 533)         Intent added = new Intent(DOWNLOAD_ADDED_MESSAGE);
c3ca5b5a (David A. Velasco 2013-04-15 13:12:04 +0200 534)         added.putExtra(ACCOUNT_NAME, download.getAccount().name);
c3ca5b5a (David A. Velasco 2013-04-15 13:12:04 +0200 535)         added.putExtra(EXTRA_REMOTE_PATH, download.getRemotePath());
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100 536)         added.putExtra(EXTRA_FILE_PATH, download.getSavePath());
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100 537)         sendStickyBroadcast(added);
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100 538)     }
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100 539) 
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100 540) }
