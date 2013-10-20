9aab2d26 (David A. Velasco 2012-11-12 15:20:00 +0100  1) /* ownCloud Android client application
bb257ec7 (David A. Velasco 2013-02-07 18:45:10 +0100  2)  *   Copyright (C) 2012-2013 ownCloud Inc.
9aab2d26 (David A. Velasco 2012-11-12 15:20:00 +0100  3)  *
9aab2d26 (David A. Velasco 2012-11-12 15:20:00 +0100  4)  *   This program is free software: you can redistribute it and/or modify
bdc0332c (David A. Velasco 2013-04-17 12:26:13 +0200  5)  *   it under the terms of the GNU General Public License version 2,
bdc0332c (David A. Velasco 2013-04-17 12:26:13 +0200  6)  *   as published by the Free Software Foundation.
9aab2d26 (David A. Velasco 2012-11-12 15:20:00 +0100  7)  *
9aab2d26 (David A. Velasco 2012-11-12 15:20:00 +0100  8)  *   This program is distributed in the hope that it will be useful,
9aab2d26 (David A. Velasco 2012-11-12 15:20:00 +0100  9)  *   but WITHOUT ANY WARRANTY; without even the implied warranty of
9aab2d26 (David A. Velasco 2012-11-12 15:20:00 +0100 10)  *   MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
9aab2d26 (David A. Velasco 2012-11-12 15:20:00 +0100 11)  *   GNU General Public License for more details.
9aab2d26 (David A. Velasco 2012-11-12 15:20:00 +0100 12)  *
9aab2d26 (David A. Velasco 2012-11-12 15:20:00 +0100 13)  *   You should have received a copy of the GNU General Public License
9aab2d26 (David A. Velasco 2012-11-12 15:20:00 +0100 14)  *   along with this program.  If not, see <http://www.gnu.org/licenses/>.
9aab2d26 (David A. Velasco 2012-11-12 15:20:00 +0100 15)  *
9aab2d26 (David A. Velasco 2012-11-12 15:20:00 +0100 16)  */
9aab2d26 (David A. Velasco 2012-11-12 15:20:00 +0100 17) 
9aab2d26 (David A. Velasco 2012-11-12 15:20:00 +0100 18) package com.owncloud.android.utils;
9aab2d26 (David A. Velasco 2012-11-12 15:20:00 +0100 19) 
9aab2d26 (David A. Velasco 2012-11-12 15:20:00 +0100 20) import java.io.File;
9aab2d26 (David A. Velasco 2012-11-12 15:20:00 +0100 21) 
7392cfe5 (Matthias Baumann 2013-03-16 18:56:27 +0100 22) import android.annotation.SuppressLint;
9ad30bdd (David A. Velasco 2013-04-17 18:00:31 +0200 23) import android.content.Context;
9aab2d26 (David A. Velasco 2012-11-12 15:20:00 +0100 24) import android.net.Uri;
9aab2d26 (David A. Velasco 2012-11-12 15:20:00 +0100 25) import android.os.Environment;
83615408 (David A. Velasco 2012-12-04 14:36:49 +0100 26) import android.os.StatFs;
83615408 (David A. Velasco 2012-12-04 14:36:49 +0100 27) 
9ad30bdd (David A. Velasco 2013-04-17 18:00:31 +0200 28) import com.owncloud.android.R;
9aab2d26 (David A. Velasco 2012-11-12 15:20:00 +0100 29) import com.owncloud.android.datamodel.OCFile;
9aab2d26 (David A. Velasco 2012-11-12 15:20:00 +0100 30) 
bb257ec7 (David A. Velasco 2013-02-07 18:45:10 +0100 31) /**
bb257ec7 (David A. Velasco 2013-02-07 18:45:10 +0100 32)  * Static methods to help in access to local file system.
bb257ec7 (David A. Velasco 2013-02-07 18:45:10 +0100 33)  * 
bb257ec7 (David A. Velasco 2013-02-07 18:45:10 +0100 34)  * @author David A. Velasco
bb257ec7 (David A. Velasco 2013-02-07 18:45:10 +0100 35)  */
9aab2d26 (David A. Velasco 2012-11-12 15:20:00 +0100 36) public class FileStorageUtils {
7392cfe5 (Matthias Baumann 2013-03-16 18:56:27 +0100 37)     private static final String LOG_TAG = "FileStorageUtils";
7392cfe5 (Matthias Baumann 2013-03-16 18:56:27 +0100 38) 
9aab2d26 (David A. Velasco 2012-11-12 15:20:00 +0100 39)     public static final String getSavePath(String accountName) {
9aab2d26 (David A. Velasco 2012-11-12 15:20:00 +0100 40)         File sdCard = Environment.getExternalStorageDirectory();
7392cfe5 (Matthias Baumann 2013-03-16 18:56:27 +0100 41)         return sdCard.getAbsolutePath() + "/owncloud/" + Uri.encode(accountName, "@");
7392cfe5 (Matthias Baumann 2013-03-16 18:56:27 +0100 42)         // URL encoding is an 'easy fix' to overcome that NTFS and FAT32 don't allow ":" in file names, that can be in the accountName since 0.1.190B
9aab2d26 (David A. Velasco 2012-11-12 15:20:00 +0100 43)     }
7392cfe5 (Matthias Baumann 2013-03-16 18:56:27 +0100 44) 
9aab2d26 (David A. Velasco 2012-11-12 15:20:00 +0100 45)     public static final String getDefaultSavePathFor(String accountName, OCFile file) {
9aab2d26 (David A. Velasco 2012-11-12 15:20:00 +0100 46)         return getSavePath(accountName) + file.getRemotePath();
9aab2d26 (David A. Velasco 2012-11-12 15:20:00 +0100 47)     }
7392cfe5 (Matthias Baumann 2013-03-16 18:56:27 +0100 48) 
9aab2d26 (David A. Velasco 2012-11-12 15:20:00 +0100 49)     public static final String getTemporalPath(String accountName) {
9aab2d26 (David A. Velasco 2012-11-12 15:20:00 +0100 50)         File sdCard = Environment.getExternalStorageDirectory();
9aab2d26 (David A. Velasco 2012-11-12 15:20:00 +0100 51)         return sdCard.getAbsolutePath() + "/owncloud/tmp/" + Uri.encode(accountName, "@");
9aab2d26 (David A. Velasco 2012-11-12 15:20:00 +0100 52)             // URL encoding is an 'easy fix' to overcome that NTFS and FAT32 don't allow ":" in file names, that can be in the accountName since 0.1.190B
9aab2d26 (David A. Velasco 2012-11-12 15:20:00 +0100 53)     }
9aab2d26 (David A. Velasco 2012-11-12 15:20:00 +0100 54) 
7392cfe5 (Matthias Baumann 2013-03-16 18:56:27 +0100 55)     @SuppressLint("NewApi")
83615408 (David A. Velasco 2012-12-04 14:36:49 +0100 56)     public static final long getUsableSpace(String accountName) {
83615408 (David A. Velasco 2012-12-04 14:36:49 +0100 57)         File savePath = Environment.getExternalStorageDirectory();
83615408 (David A. Velasco 2012-12-04 14:36:49 +0100 58)         if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.GINGERBREAD) {
83615408 (David A. Velasco 2012-12-04 14:36:49 +0100 59)             return savePath.getUsableSpace();
7392cfe5 (Matthias Baumann 2013-03-16 18:56:27 +0100 60) 
83615408 (David A. Velasco 2012-12-04 14:36:49 +0100 61)         } else {
83615408 (David A. Velasco 2012-12-04 14:36:49 +0100 62)             StatFs stats = new StatFs(savePath.getAbsolutePath());
83615408 (David A. Velasco 2012-12-04 14:36:49 +0100 63)             return stats.getAvailableBlocks() * stats.getBlockSize();
83615408 (David A. Velasco 2012-12-04 14:36:49 +0100 64)         }
7392cfe5 (Matthias Baumann 2013-03-16 18:56:27 +0100 65) 
83615408 (David A. Velasco 2012-12-04 14:36:49 +0100 66)     }
9aab2d26 (David A. Velasco 2012-11-12 15:20:00 +0100 67)     
1003ecb7 (David A. Velasco 2013-04-15 13:32:49 +0200 68)     public static final String getLogPath()  {
1003ecb7 (David A. Velasco 2013-04-15 13:32:49 +0200 69)         return Environment.getExternalStorageDirectory() + File.separator + "owncloud" + File.separator + "log";
1003ecb7 (David A. Velasco 2013-04-15 13:32:49 +0200 70)     }
7392cfe5 (Matthias Baumann 2013-03-16 18:56:27 +0100 71) 
9ad30bdd (David A. Velasco 2013-04-17 18:00:31 +0200 72)     public static String getInstantUploadFilePath(Context context, String fileName) {
9ad30bdd (David A. Velasco 2013-04-17 18:00:31 +0200 73)         String uploadPath = context.getString(R.string.instant_upload_path);
9ad30bdd (David A. Velasco 2013-04-17 18:00:31 +0200 74)         String value = uploadPath + OCFile.PATH_SEPARATOR +  (fileName == null ? "" : fileName);
9ad30bdd (David A. Velasco 2013-04-17 18:00:31 +0200 75)         return value;
83615408 (David A. Velasco 2012-12-04 14:36:49 +0100 76)     }
7ec25767 (masensio         2013-07-05 13:00:57 +0200 77)   
9aab2d26 (David A. Velasco 2012-11-12 15:20:00 +0100 78) }
