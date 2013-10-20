6c323657 (Bartek Przybylski 2012-10-27 12:20:52 +0200   1) /* ownCloud Android client application
6c323657 (Bartek Przybylski 2012-10-27 12:20:52 +0200   2)  *   Copyright (C) 2012 Bartek Przybylski
bb257ec7 (David A. Velasco  2013-02-07 18:45:10 +0100   3)  *   Copyright (C) 2012-2013 ownCloud Inc.
6c323657 (Bartek Przybylski 2012-10-27 12:20:52 +0200   4)  *
6c323657 (Bartek Przybylski 2012-10-27 12:20:52 +0200   5)  *   This program is free software: you can redistribute it and/or modify
bdc0332c (David A. Velasco  2013-04-17 12:26:13 +0200   6)  *   it under the terms of the GNU General Public License version 2,
bdc0332c (David A. Velasco  2013-04-17 12:26:13 +0200   7)  *   as published by the Free Software Foundation.
6c323657 (Bartek Przybylski 2012-10-27 12:20:52 +0200   8)  *
6c323657 (Bartek Przybylski 2012-10-27 12:20:52 +0200   9)  *   This program is distributed in the hope that it will be useful,
6c323657 (Bartek Przybylski 2012-10-27 12:20:52 +0200  10)  *   but WITHOUT ANY WARRANTY; without even the implied warranty of
6c323657 (Bartek Przybylski 2012-10-27 12:20:52 +0200  11)  *   MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
6c323657 (Bartek Przybylski 2012-10-27 12:20:52 +0200  12)  *   GNU General Public License for more details.
6c323657 (Bartek Przybylski 2012-10-27 12:20:52 +0200  13)  *
6c323657 (Bartek Przybylski 2012-10-27 12:20:52 +0200  14)  *   You should have received a copy of the GNU General Public License
6c323657 (Bartek Przybylski 2012-10-27 12:20:52 +0200  15)  *   along with this program.  If not, see <http://www.gnu.org/licenses/>.
6c323657 (Bartek Przybylski 2012-10-27 12:20:52 +0200  16)  *
6c323657 (Bartek Przybylski 2012-10-27 12:20:52 +0200  17)  */
6c323657 (Bartek Przybylski 2012-10-27 12:20:52 +0200  18) 
ba148a82 (Bartek Przybylski 2012-08-22 19:32:42 +0200  19) package com.owncloud.android.files;
ba148a82 (Bartek Przybylski 2012-08-22 19:32:42 +0200  20) 
c38a3b2e (David A. Velasco  2012-12-18 11:20:03 +0100  21) import java.io.File;
6c323657 (Bartek Przybylski 2012-10-27 12:20:52 +0200  22) 
8e36e7cc (zerginator        2013-03-12 07:56:27 +0100  23) import com.owncloud.android.Log_OC;
c38a3b2e (David A. Velasco  2012-12-18 11:20:03 +0100  24) import com.owncloud.android.datamodel.FileDataStorageManager;
ba148a82 (Bartek Przybylski 2012-08-22 19:32:42 +0200  25) import com.owncloud.android.datamodel.OCFile;
6c323657 (Bartek Przybylski 2012-10-27 12:20:52 +0200  26) import com.owncloud.android.operations.RemoteOperationResult;
6c323657 (Bartek Przybylski 2012-10-27 12:20:52 +0200  27) import com.owncloud.android.operations.SynchronizeFileOperation;
c38a3b2e (David A. Velasco  2012-12-18 11:20:03 +0100  28) import com.owncloud.android.operations.RemoteOperationResult.ResultCode;
c38a3b2e (David A. Velasco  2012-12-18 11:20:03 +0100  29) import com.owncloud.android.ui.activity.ConflictsResolveActivity;
6c323657 (Bartek Przybylski 2012-10-27 12:20:52 +0200  30) 
ba148a82 (Bartek Przybylski 2012-08-22 19:32:42 +0200  31) 
ba148a82 (Bartek Przybylski 2012-08-22 19:32:42 +0200  32) import android.accounts.Account;
ba148a82 (Bartek Przybylski 2012-08-22 19:32:42 +0200  33) import android.content.Context;
ba148a82 (Bartek Przybylski 2012-08-22 19:32:42 +0200  34) import android.content.Intent;
ba148a82 (Bartek Przybylski 2012-08-22 19:32:42 +0200  35) import android.os.FileObserver;
ba148a82 (Bartek Przybylski 2012-08-22 19:32:42 +0200  36) 
ba148a82 (Bartek Przybylski 2012-08-22 19:32:42 +0200  37) public class OwnCloudFileObserver extends FileObserver {
ba148a82 (Bartek Przybylski 2012-08-22 19:32:42 +0200  38) 
0f1065e5 (Bartek Przybylski 2012-10-27 21:55:24 +0200  39)     public static int CHANGES_ONLY = CLOSE_WRITE;
ba148a82 (Bartek Przybylski 2012-08-22 19:32:42 +0200  40)     
c38a3b2e (David A. Velasco  2012-12-18 11:20:03 +0100  41)     private static String TAG = OwnCloudFileObserver.class.getSimpleName();
c38a3b2e (David A. Velasco  2012-12-18 11:20:03 +0100  42)     
ba148a82 (Bartek Przybylski 2012-08-22 19:32:42 +0200  43)     private String mPath;
ba148a82 (Bartek Przybylski 2012-08-22 19:32:42 +0200  44)     private int mMask;
c38a3b2e (David A. Velasco  2012-12-18 11:20:03 +0100  45)     private Account mOCAccount;
c38a3b2e (David A. Velasco  2012-12-18 11:20:03 +0100  46)     //private OCFile mFile;
c38a3b2e (David A. Velasco  2012-12-18 11:20:03 +0100  47)     private Context mContext;
c38a3b2e (David A. Velasco  2012-12-18 11:20:03 +0100  48) 
ba148a82 (Bartek Przybylski 2012-08-22 19:32:42 +0200  49)     
c38a3b2e (David A. Velasco  2012-12-18 11:20:03 +0100  50)     public OwnCloudFileObserver(String path, Account account, Context context, int mask) {
ba148a82 (Bartek Przybylski 2012-08-22 19:32:42 +0200  51)         super(path, mask);
c38a3b2e (David A. Velasco  2012-12-18 11:20:03 +0100  52)         if (path == null)
c38a3b2e (David A. Velasco  2012-12-18 11:20:03 +0100  53)             throw new IllegalArgumentException("NULL path argument received"); 
c38a3b2e (David A. Velasco  2012-12-18 11:20:03 +0100  54)         /*if (file == null)
c38a3b2e (David A. Velasco  2012-12-18 11:20:03 +0100  55)             throw new IllegalArgumentException("NULL file argument received");*/ 
c38a3b2e (David A. Velasco  2012-12-18 11:20:03 +0100  56)         if (account == null)
c38a3b2e (David A. Velasco  2012-12-18 11:20:03 +0100  57)             throw new IllegalArgumentException("NULL account argument received"); 
c38a3b2e (David A. Velasco  2012-12-18 11:20:03 +0100  58)         if (context == null)
c38a3b2e (David A. Velasco  2012-12-18 11:20:03 +0100  59)             throw new IllegalArgumentException("NULL context argument received");
c38a3b2e (David A. Velasco  2012-12-18 11:20:03 +0100  60)         /*if (!path.equals(file.getStoragePath()) && !path.equals(FileStorageUtils.getDefaultSavePathFor(account.name, file)))
c38a3b2e (David A. Velasco  2012-12-18 11:20:03 +0100  61)             throw new IllegalArgumentException("File argument is not linked to the local file set in path argument"); */
ba148a82 (Bartek Przybylski 2012-08-22 19:32:42 +0200  62)         mPath = path;
c38a3b2e (David A. Velasco  2012-12-18 11:20:03 +0100  63)         //mFile = file;
ba148a82 (Bartek Przybylski 2012-08-22 19:32:42 +0200  64)         mOCAccount = account;
c38a3b2e (David A. Velasco  2012-12-18 11:20:03 +0100  65)         mContext = context; 
c38a3b2e (David A. Velasco  2012-12-18 11:20:03 +0100  66)         mMask = mask;
6c323657 (Bartek Przybylski 2012-10-27 12:20:52 +0200  67)     }
6c323657 (Bartek Przybylski 2012-10-27 12:20:52 +0200  68)     
ba148a82 (Bartek Przybylski 2012-08-22 19:32:42 +0200  69)     @Override
ba148a82 (Bartek Przybylski 2012-08-22 19:32:42 +0200  70)     public void onEvent(int event, String path) {
8e36e7cc (zerginator        2013-03-12 07:56:27 +0100  71)         Log_OC.d(TAG, "Got file modified with event " + event + " and path " + mPath + ((path != null) ? File.separator + path : ""));
de94751c (Bartek Przybylski 2012-10-22 21:15:26 +0200  72)         if ((event & mMask) == 0) {
8e36e7cc (zerginator        2013-03-12 07:56:27 +0100  73)             Log_OC.wtf(TAG, "Incorrect event " + event + " sent for file " + mPath + ((path != null) ? File.separator + path : "") +
ba148a82 (Bartek Przybylski 2012-08-22 19:32:42 +0200  74)                          " with registered for " + mMask + " and original path " +
ba148a82 (Bartek Przybylski 2012-08-22 19:32:42 +0200  75)                          mPath);
ba148a82 (Bartek Przybylski 2012-08-22 19:32:42 +0200  76)             return;
ba148a82 (Bartek Przybylski 2012-08-22 19:32:42 +0200  77)         }
c38a3b2e (David A. Velasco  2012-12-18 11:20:03 +0100  78)         FileDataStorageManager storageManager = new FileDataStorageManager(mOCAccount, mContext.getContentResolver());
c38a3b2e (David A. Velasco  2012-12-18 11:20:03 +0100  79)         OCFile file = storageManager.getFileByLocalPath(mPath);     // a fresh object is needed; many things could have occurred to the file since it was registered to observe
c38a3b2e (David A. Velasco  2012-12-18 11:20:03 +0100  80)                                                                     // again, assuming that local files are linked to a remote file AT MOST, SOMETHING TO BE DONE; 
c38a3b2e (David A. Velasco  2012-12-18 11:20:03 +0100  81)         SynchronizeFileOperation sfo = new SynchronizeFileOperation(file, 
c38a3b2e (David A. Velasco  2012-12-18 11:20:03 +0100  82)                                                                     null, 
c38a3b2e (David A. Velasco  2012-12-18 11:20:03 +0100  83)                                                                     storageManager, 
c38a3b2e (David A. Velasco  2012-12-18 11:20:03 +0100  84)                                                                     mOCAccount, 
c38a3b2e (David A. Velasco  2012-12-18 11:20:03 +0100  85)                                                                     true, 
c38a3b2e (David A. Velasco  2012-12-18 11:20:03 +0100  86)                                                                     true, 
c38a3b2e (David A. Velasco  2012-12-18 11:20:03 +0100  87)                                                                     mContext);
11b88e0f (David A. Velasco  2012-12-19 14:12:53 +0100  88)         RemoteOperationResult result = sfo.execute(mOCAccount, mContext);
c38a3b2e (David A. Velasco  2012-12-18 11:20:03 +0100  89)         if (result.getCode() == ResultCode.SYNC_CONFLICT) {
c38a3b2e (David A. Velasco  2012-12-18 11:20:03 +0100  90)             // ISSUE 5: if the user is not running the app (this is a service!), this can be very intrusive; a notification should be preferred
c38a3b2e (David A. Velasco  2012-12-18 11:20:03 +0100  91)             Intent i = new Intent(mContext, ConflictsResolveActivity.class);
c38a3b2e (David A. Velasco  2012-12-18 11:20:03 +0100  92)             i.setFlags(i.getFlags() | Intent.FLAG_ACTIVITY_NEW_TASK);
c38a3b2e (David A. Velasco  2012-12-18 11:20:03 +0100  93)             i.putExtra(ConflictsResolveActivity.EXTRA_FILE, file);
c38a3b2e (David A. Velasco  2012-12-18 11:20:03 +0100  94)             i.putExtra(ConflictsResolveActivity.EXTRA_ACCOUNT, mOCAccount);
c38a3b2e (David A. Velasco  2012-12-18 11:20:03 +0100  95)             mContext.startActivity(i);
6c323657 (Bartek Przybylski 2012-10-27 12:20:52 +0200  96)         }
c38a3b2e (David A. Velasco  2012-12-18 11:20:03 +0100  97)         // TODO save other errors in some point where the user can inspect them later;
c38a3b2e (David A. Velasco  2012-12-18 11:20:03 +0100  98)         //      or maybe just toast them;
c38a3b2e (David A. Velasco  2012-12-18 11:20:03 +0100  99)         //      or nothing, very strange fails
6c323657 (Bartek Przybylski 2012-10-27 12:20:52 +0200 100)     }
6c323657 (Bartek Przybylski 2012-10-27 12:20:52 +0200 101)     
ba148a82 (Bartek Przybylski 2012-08-22 19:32:42 +0200 102) }
