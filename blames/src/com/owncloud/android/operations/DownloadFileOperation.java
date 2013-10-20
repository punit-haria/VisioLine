68ce2e7a (David A. Velasco 2012-10-09 14:53:25 +0200   1) /* ownCloud Android client application
bb257ec7 (David A. Velasco 2013-02-07 18:45:10 +0100   2)  *   Copyright (C) 2012-2013 ownCloud Inc.
68ce2e7a (David A. Velasco 2012-10-09 14:53:25 +0200   3)  *
68ce2e7a (David A. Velasco 2012-10-09 14:53:25 +0200   4)  *   This program is free software: you can redistribute it and/or modify
bdc0332c (David A. Velasco 2013-04-17 12:26:13 +0200   5)  *   it under the terms of the GNU General Public License version 2,
bdc0332c (David A. Velasco 2013-04-17 12:26:13 +0200   6)  *   as published by the Free Software Foundation.
68ce2e7a (David A. Velasco 2012-10-09 14:53:25 +0200   7)  *
68ce2e7a (David A. Velasco 2012-10-09 14:53:25 +0200   8)  *   This program is distributed in the hope that it will be useful,
68ce2e7a (David A. Velasco 2012-10-09 14:53:25 +0200   9)  *   but WITHOUT ANY WARRANTY; without even the implied warranty of
68ce2e7a (David A. Velasco 2012-10-09 14:53:25 +0200  10)  *   MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
68ce2e7a (David A. Velasco 2012-10-09 14:53:25 +0200  11)  *   GNU General Public License for more details.
68ce2e7a (David A. Velasco 2012-10-09 14:53:25 +0200  12)  *
68ce2e7a (David A. Velasco 2012-10-09 14:53:25 +0200  13)  *   You should have received a copy of the GNU General Public License
68ce2e7a (David A. Velasco 2012-10-09 14:53:25 +0200  14)  *   along with this program.  If not, see <http://www.gnu.org/licenses/>.
68ce2e7a (David A. Velasco 2012-10-09 14:53:25 +0200  15)  *
68ce2e7a (David A. Velasco 2012-10-09 14:53:25 +0200  16)  */
68ce2e7a (David A. Velasco 2012-10-09 14:53:25 +0200  17) 
68ce2e7a (David A. Velasco 2012-10-09 14:53:25 +0200  18) package com.owncloud.android.operations;
68ce2e7a (David A. Velasco 2012-10-09 14:53:25 +0200  19) 
68ce2e7a (David A. Velasco 2012-10-09 14:53:25 +0200  20) import java.io.BufferedInputStream;
68ce2e7a (David A. Velasco 2012-10-09 14:53:25 +0200  21) import java.io.File;
68ce2e7a (David A. Velasco 2012-10-09 14:53:25 +0200  22) import java.io.FileOutputStream;
68ce2e7a (David A. Velasco 2012-10-09 14:53:25 +0200  23) import java.io.IOException;
925a0554 (David A. Velasco 2012-11-13 15:59:05 +0100  24) import java.util.Date;
68ce2e7a (David A. Velasco 2012-10-09 14:53:25 +0200  25) import java.util.HashSet;
68ce2e7a (David A. Velasco 2012-10-09 14:53:25 +0200  26) import java.util.Iterator;
68ce2e7a (David A. Velasco 2012-10-09 14:53:25 +0200  27) import java.util.Set;
b246cf70 (David A. Velasco 2012-10-10 11:30:47 +0200  28) import java.util.concurrent.atomic.AtomicBoolean;
68ce2e7a (David A. Velasco 2012-10-09 14:53:25 +0200  29) 
925a0554 (David A. Velasco 2012-11-13 15:59:05 +0100  30) import org.apache.commons.httpclient.Header;
68ce2e7a (David A. Velasco 2012-10-09 14:53:25 +0200  31) import org.apache.commons.httpclient.HttpException;
68ce2e7a (David A. Velasco 2012-10-09 14:53:25 +0200  32) import org.apache.commons.httpclient.methods.GetMethod;
68ce2e7a (David A. Velasco 2012-10-09 14:53:25 +0200  33) import org.apache.http.HttpStatus;
68ce2e7a (David A. Velasco 2012-10-09 14:53:25 +0200  34) 
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100  35) import com.owncloud.android.Log_OC;
25f307c9 (David A. Velasco 2012-10-10 13:13:22 +0200  36) import com.owncloud.android.datamodel.OCFile;
68ce2e7a (David A. Velasco 2012-10-09 14:53:25 +0200  37) import com.owncloud.android.operations.RemoteOperation;
68ce2e7a (David A. Velasco 2012-10-09 14:53:25 +0200  38) import com.owncloud.android.operations.RemoteOperationResult;
9aab2d26 (David A. Velasco 2012-11-12 15:20:00 +0100  39) import com.owncloud.android.utils.FileStorageUtils;
68ce2e7a (David A. Velasco 2012-10-09 14:53:25 +0200  40) 
68ce2e7a (David A. Velasco 2012-10-09 14:53:25 +0200  41) import eu.alefzero.webdav.OnDatatransferProgressListener;
68ce2e7a (David A. Velasco 2012-10-09 14:53:25 +0200  42) import eu.alefzero.webdav.WebdavClient;
68ce2e7a (David A. Velasco 2012-10-09 14:53:25 +0200  43) import eu.alefzero.webdav.WebdavUtils;
68ce2e7a (David A. Velasco 2012-10-09 14:53:25 +0200  44) import android.accounts.Account;
68ce2e7a (David A. Velasco 2012-10-09 14:53:25 +0200  45) import android.webkit.MimeTypeMap;
68ce2e7a (David A. Velasco 2012-10-09 14:53:25 +0200  46) 
68ce2e7a (David A. Velasco 2012-10-09 14:53:25 +0200  47) /**
68ce2e7a (David A. Velasco 2012-10-09 14:53:25 +0200  48)  * Remote operation performing the download of a file to an ownCloud server
68ce2e7a (David A. Velasco 2012-10-09 14:53:25 +0200  49)  * 
68ce2e7a (David A. Velasco 2012-10-09 14:53:25 +0200  50)  * @author David A. Velasco
68ce2e7a (David A. Velasco 2012-10-09 14:53:25 +0200  51)  */
68ce2e7a (David A. Velasco 2012-10-09 14:53:25 +0200  52) public class DownloadFileOperation extends RemoteOperation {
68ce2e7a (David A. Velasco 2012-10-09 14:53:25 +0200  53)     
c6a3abf0 (David A. Velasco 2012-10-26 13:45:59 +0200  54)     private static final String TAG = DownloadFileOperation.class.getSimpleName();
68ce2e7a (David A. Velasco 2012-10-09 14:53:25 +0200  55) 
27112bd2 (David A. Velasco 2012-10-11 16:40:56 +0200  56)     private Account mAccount;
25f307c9 (David A. Velasco 2012-10-10 13:13:22 +0200  57)     private OCFile mFile;
68ce2e7a (David A. Velasco 2012-10-09 14:53:25 +0200  58)     private Set<OnDatatransferProgressListener> mDataTransferListeners = new HashSet<OnDatatransferProgressListener>();
5fc7cd13 (David A. Velasco 2012-10-11 11:24:17 +0200  59)     private final AtomicBoolean mCancellationRequested = new AtomicBoolean(false);
925a0554 (David A. Velasco 2012-11-13 15:59:05 +0100  60)     private long mModificationTimestamp = 0;
78bcf721 (David A. Velasco 2013-08-07 19:13:00 +0200  61)     private GetMethod mGet;
68ce2e7a (David A. Velasco 2012-10-09 14:53:25 +0200  62) 
68ce2e7a (David A. Velasco 2012-10-09 14:53:25 +0200  63)     
25f307c9 (David A. Velasco 2012-10-10 13:13:22 +0200  64)     public DownloadFileOperation(Account account, OCFile file) {
25f307c9 (David A. Velasco 2012-10-10 13:13:22 +0200  65)         if (account == null)
25f307c9 (David A. Velasco 2012-10-10 13:13:22 +0200  66)             throw new IllegalArgumentException("Illegal null account in DownloadFileOperation creation");
25f307c9 (David A. Velasco 2012-10-10 13:13:22 +0200  67)         if (file == null)
25f307c9 (David A. Velasco 2012-10-10 13:13:22 +0200  68)             throw new IllegalArgumentException("Illegal null file in DownloadFileOperation creation");
25f307c9 (David A. Velasco 2012-10-10 13:13:22 +0200  69)         
25f307c9 (David A. Velasco 2012-10-10 13:13:22 +0200  70)         mAccount = account;
25f307c9 (David A. Velasco 2012-10-10 13:13:22 +0200  71)         mFile = file;
68ce2e7a (David A. Velasco 2012-10-09 14:53:25 +0200  72)     }
68ce2e7a (David A. Velasco 2012-10-09 14:53:25 +0200  73) 
25f307c9 (David A. Velasco 2012-10-10 13:13:22 +0200  74) 
25f307c9 (David A. Velasco 2012-10-10 13:13:22 +0200  75)     public Account getAccount() {
25f307c9 (David A. Velasco 2012-10-10 13:13:22 +0200  76)         return mAccount;
68ce2e7a (David A. Velasco 2012-10-09 14:53:25 +0200  77)     }
68ce2e7a (David A. Velasco 2012-10-09 14:53:25 +0200  78)     
25f307c9 (David A. Velasco 2012-10-10 13:13:22 +0200  79)     public OCFile getFile() {
25f307c9 (David A. Velasco 2012-10-10 13:13:22 +0200  80)         return mFile;
68ce2e7a (David A. Velasco 2012-10-09 14:53:25 +0200  81)     }
68ce2e7a (David A. Velasco 2012-10-09 14:53:25 +0200  82) 
25f307c9 (David A. Velasco 2012-10-10 13:13:22 +0200  83)     public String getSavePath() {
9aab2d26 (David A. Velasco 2012-11-12 15:20:00 +0100  84)         String path = mFile.getStoragePath();   // re-downloads should be done over the original file 
9aab2d26 (David A. Velasco 2012-11-12 15:20:00 +0100  85)         if (path != null && path.length() > 0) {
9aab2d26 (David A. Velasco 2012-11-12 15:20:00 +0100  86)             return path;
9aab2d26 (David A. Velasco 2012-11-12 15:20:00 +0100  87)         }
9aab2d26 (David A. Velasco 2012-11-12 15:20:00 +0100  88)         return FileStorageUtils.getDefaultSavePathFor(mAccount.name, mFile);
68ce2e7a (David A. Velasco 2012-10-09 14:53:25 +0200  89)     }
68ce2e7a (David A. Velasco 2012-10-09 14:53:25 +0200  90)     
25f307c9 (David A. Velasco 2012-10-10 13:13:22 +0200  91)     public String getTmpPath() {
9aab2d26 (David A. Velasco 2012-11-12 15:20:00 +0100  92)         return FileStorageUtils.getTemporalPath(mAccount.name) + mFile.getRemotePath();
68ce2e7a (David A. Velasco 2012-10-09 14:53:25 +0200  93)     }
68ce2e7a (David A. Velasco 2012-10-09 14:53:25 +0200  94)     
25f307c9 (David A. Velasco 2012-10-10 13:13:22 +0200  95)     public String getRemotePath() {
25f307c9 (David A. Velasco 2012-10-10 13:13:22 +0200  96)         return mFile.getRemotePath();
25f307c9 (David A. Velasco 2012-10-10 13:13:22 +0200  97)     }
25f307c9 (David A. Velasco 2012-10-10 13:13:22 +0200  98) 
25f307c9 (David A. Velasco 2012-10-10 13:13:22 +0200  99)     public String getMimeType() {
925a0554 (David A. Velasco 2012-11-13 15:59:05 +0100 100)         String mimeType = mFile.getMimetype();
25f307c9 (David A. Velasco 2012-10-10 13:13:22 +0200 101)         if (mimeType == null || mimeType.length() <= 0) {
68ce2e7a (David A. Velasco 2012-10-09 14:53:25 +0200 102)             try {
25f307c9 (David A. Velasco 2012-10-10 13:13:22 +0200 103)                 mimeType = MimeTypeMap.getSingleton()
68ce2e7a (David A. Velasco 2012-10-09 14:53:25 +0200 104)                     .getMimeTypeFromExtension(
25f307c9 (David A. Velasco 2012-10-10 13:13:22 +0200 105)                             mFile.getRemotePath().substring(mFile.getRemotePath().lastIndexOf('.') + 1));
68ce2e7a (David A. Velasco 2012-10-09 14:53:25 +0200 106)             } catch (IndexOutOfBoundsException e) {
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100 107)                 Log_OC.e(TAG, "Trying to find out MIME type of a file without extension: " + mFile.getRemotePath());
68ce2e7a (David A. Velasco 2012-10-09 14:53:25 +0200 108)             }
68ce2e7a (David A. Velasco 2012-10-09 14:53:25 +0200 109)         }
25f307c9 (David A. Velasco 2012-10-10 13:13:22 +0200 110)         if (mimeType == null) {
25f307c9 (David A. Velasco 2012-10-10 13:13:22 +0200 111)             mimeType = "application/octet-stream";
68ce2e7a (David A. Velasco 2012-10-09 14:53:25 +0200 112)         }
25f307c9 (David A. Velasco 2012-10-10 13:13:22 +0200 113)         return mimeType;
68ce2e7a (David A. Velasco 2012-10-09 14:53:25 +0200 114)     }
68ce2e7a (David A. Velasco 2012-10-09 14:53:25 +0200 115)     
25f307c9 (David A. Velasco 2012-10-10 13:13:22 +0200 116)     public long getSize() {
25f307c9 (David A. Velasco 2012-10-10 13:13:22 +0200 117)         return mFile.getFileLength();
68ce2e7a (David A. Velasco 2012-10-09 14:53:25 +0200 118)     }
68ce2e7a (David A. Velasco 2012-10-09 14:53:25 +0200 119)     
925a0554 (David A. Velasco 2012-11-13 15:59:05 +0100 120)     public long getModificationTimestamp() {
925a0554 (David A. Velasco 2012-11-13 15:59:05 +0100 121)         return (mModificationTimestamp > 0) ? mModificationTimestamp : mFile.getModificationTimestamp();
925a0554 (David A. Velasco 2012-11-13 15:59:05 +0100 122)     }
925a0554 (David A. Velasco 2012-11-13 15:59:05 +0100 123)     
68ce2e7a (David A. Velasco 2012-10-09 14:53:25 +0200 124)     
25f307c9 (David A. Velasco 2012-10-10 13:13:22 +0200 125)     public void addDatatransferProgressListener (OnDatatransferProgressListener listener) {
24dd5136 (David A. Velasco 2013-02-19 13:28:01 +0100 126)         synchronized (mDataTransferListeners) {
24dd5136 (David A. Velasco 2013-02-19 13:28:01 +0100 127)             mDataTransferListeners.add(listener);
24dd5136 (David A. Velasco 2013-02-19 13:28:01 +0100 128)         }
25f307c9 (David A. Velasco 2012-10-10 13:13:22 +0200 129)     }
68ce2e7a (David A. Velasco 2012-10-09 14:53:25 +0200 130)     
24dd5136 (David A. Velasco 2013-02-19 13:28:01 +0100 131)     public void removeDatatransferProgressListener(OnDatatransferProgressListener listener) {
24dd5136 (David A. Velasco 2013-02-19 13:28:01 +0100 132)         synchronized (mDataTransferListeners) {
24dd5136 (David A. Velasco 2013-02-19 13:28:01 +0100 133)             mDataTransferListeners.remove(listener);
24dd5136 (David A. Velasco 2013-02-19 13:28:01 +0100 134)         }
24dd5136 (David A. Velasco 2013-02-19 13:28:01 +0100 135)     }
24dd5136 (David A. Velasco 2013-02-19 13:28:01 +0100 136) 
68ce2e7a (David A. Velasco 2012-10-09 14:53:25 +0200 137)     @Override
68ce2e7a (David A. Velasco 2012-10-09 14:53:25 +0200 138)     protected RemoteOperationResult run(WebdavClient client) {
68ce2e7a (David A. Velasco 2012-10-09 14:53:25 +0200 139)         RemoteOperationResult result = null;
68ce2e7a (David A. Velasco 2012-10-09 14:53:25 +0200 140)         File newFile = null;
d25af7ee (David A. Velasco 2012-10-24 19:39:55 +0200 141)         boolean moved = true;
68ce2e7a (David A. Velasco 2012-10-09 14:53:25 +0200 142)         
25f307c9 (David A. Velasco 2012-10-10 13:13:22 +0200 143)         /// download will be performed to a temporal file, then moved to the final location
25f307c9 (David A. Velasco 2012-10-10 13:13:22 +0200 144)         File tmpFile = new File(getTmpPath());
68ce2e7a (David A. Velasco 2012-10-09 14:53:25 +0200 145)         
68ce2e7a (David A. Velasco 2012-10-09 14:53:25 +0200 146)         /// perform the download
68ce2e7a (David A. Velasco 2012-10-09 14:53:25 +0200 147)         try {
68ce2e7a (David A. Velasco 2012-10-09 14:53:25 +0200 148)             tmpFile.getParentFile().mkdirs();
68ce2e7a (David A. Velasco 2012-10-09 14:53:25 +0200 149)             int status = downloadFile(client, tmpFile);
68ce2e7a (David A. Velasco 2012-10-09 14:53:25 +0200 150)             if (isSuccess(status)) {
25f307c9 (David A. Velasco 2012-10-10 13:13:22 +0200 151)                 newFile = new File(getSavePath());
68ce2e7a (David A. Velasco 2012-10-09 14:53:25 +0200 152)                 newFile.getParentFile().mkdirs();
68ce2e7a (David A. Velasco 2012-10-09 14:53:25 +0200 153)                 moved = tmpFile.renameTo(newFile);
68ce2e7a (David A. Velasco 2012-10-09 14:53:25 +0200 154)             }
68ce2e7a (David A. Velasco 2012-10-09 14:53:25 +0200 155)             if (!moved)
fd9086a8 (David A. Velasco 2012-11-27 14:28:19 +0100 156)                 result = new RemoteOperationResult(RemoteOperationResult.ResultCode.LOCAL_STORAGE_NOT_MOVED);
68ce2e7a (David A. Velasco 2012-10-09 14:53:25 +0200 157)             else
78bcf721 (David A. Velasco 2013-08-07 19:13:00 +0200 158)                 result = new RemoteOperationResult(isSuccess(status), status, (mGet != null ? mGet.getResponseHeaders() : null));
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100 159)             Log_OC.i(TAG, "Download of " + mFile.getRemotePath() + " to " + getSavePath() + ": " + result.getLogMessage());
68ce2e7a (David A. Velasco 2012-10-09 14:53:25 +0200 160)             
68ce2e7a (David A. Velasco 2012-10-09 14:53:25 +0200 161)         } catch (Exception e) {
68ce2e7a (David A. Velasco 2012-10-09 14:53:25 +0200 162)             result = new RemoteOperationResult(e);
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100 163)             Log_OC.e(TAG, "Download of " + mFile.getRemotePath() + " to " + getSavePath() + ": " + result.getLogMessage(), e);
68ce2e7a (David A. Velasco 2012-10-09 14:53:25 +0200 164)         }
68ce2e7a (David A. Velasco 2012-10-09 14:53:25 +0200 165)         
68ce2e7a (David A. Velasco 2012-10-09 14:53:25 +0200 166)         return result;
68ce2e7a (David A. Velasco 2012-10-09 14:53:25 +0200 167)     }
68ce2e7a (David A. Velasco 2012-10-09 14:53:25 +0200 168) 
68ce2e7a (David A. Velasco 2012-10-09 14:53:25 +0200 169)     
68ce2e7a (David A. Velasco 2012-10-09 14:53:25 +0200 170)     public boolean isSuccess(int status) {
68ce2e7a (David A. Velasco 2012-10-09 14:53:25 +0200 171)         return (status == HttpStatus.SC_OK);
68ce2e7a (David A. Velasco 2012-10-09 14:53:25 +0200 172)     }
68ce2e7a (David A. Velasco 2012-10-09 14:53:25 +0200 173)     
68ce2e7a (David A. Velasco 2012-10-09 14:53:25 +0200 174)     
68ce2e7a (David A. Velasco 2012-10-09 14:53:25 +0200 175)     protected int downloadFile(WebdavClient client, File targetFile) throws HttpException, IOException, OperationCancelledException {
68ce2e7a (David A. Velasco 2012-10-09 14:53:25 +0200 176)         int status = -1;
68ce2e7a (David A. Velasco 2012-10-09 14:53:25 +0200 177)         boolean savedFile = false;
78bcf721 (David A. Velasco 2013-08-07 19:13:00 +0200 178)         mGet = new GetMethod(client.getBaseUri() + WebdavUtils.encodePath(mFile.getRemotePath()));
68ce2e7a (David A. Velasco 2012-10-09 14:53:25 +0200 179)         Iterator<OnDatatransferProgressListener> it = null;
68ce2e7a (David A. Velasco 2012-10-09 14:53:25 +0200 180)         
b246cf70 (David A. Velasco 2012-10-10 11:30:47 +0200 181)         FileOutputStream fos = null;
68ce2e7a (David A. Velasco 2012-10-09 14:53:25 +0200 182)         try {
78bcf721 (David A. Velasco 2013-08-07 19:13:00 +0200 183)             status = client.executeMethod(mGet);
68ce2e7a (David A. Velasco 2012-10-09 14:53:25 +0200 184)             if (isSuccess(status)) {
68ce2e7a (David A. Velasco 2012-10-09 14:53:25 +0200 185)                 targetFile.createNewFile();
78bcf721 (David A. Velasco 2013-08-07 19:13:00 +0200 186)                 BufferedInputStream bis = new BufferedInputStream(mGet.getResponseBodyAsStream());
b246cf70 (David A. Velasco 2012-10-10 11:30:47 +0200 187)                 fos = new FileOutputStream(targetFile);
68ce2e7a (David A. Velasco 2012-10-09 14:53:25 +0200 188)                 long transferred = 0;
68ce2e7a (David A. Velasco 2012-10-09 14:53:25 +0200 189) 
68ce2e7a (David A. Velasco 2012-10-09 14:53:25 +0200 190)                 byte[] bytes = new byte[4096];
68ce2e7a (David A. Velasco 2012-10-09 14:53:25 +0200 191)                 int readResult = 0;
68ce2e7a (David A. Velasco 2012-10-09 14:53:25 +0200 192)                 while ((readResult = bis.read(bytes)) != -1) {
68ce2e7a (David A. Velasco 2012-10-09 14:53:25 +0200 193)                     synchronized(mCancellationRequested) {
b246cf70 (David A. Velasco 2012-10-10 11:30:47 +0200 194)                         if (mCancellationRequested.get()) {
78bcf721 (David A. Velasco 2013-08-07 19:13:00 +0200 195)                             mGet.abort();
68ce2e7a (David A. Velasco 2012-10-09 14:53:25 +0200 196)                             throw new OperationCancelledException();
68ce2e7a (David A. Velasco 2012-10-09 14:53:25 +0200 197)                         }
68ce2e7a (David A. Velasco 2012-10-09 14:53:25 +0200 198)                     }
68ce2e7a (David A. Velasco 2012-10-09 14:53:25 +0200 199)                     fos.write(bytes, 0, readResult);
68ce2e7a (David A. Velasco 2012-10-09 14:53:25 +0200 200)                     transferred += readResult;
24dd5136 (David A. Velasco 2013-02-19 13:28:01 +0100 201)                     synchronized (mDataTransferListeners) {
24dd5136 (David A. Velasco 2013-02-19 13:28:01 +0100 202)                         it = mDataTransferListeners.iterator();
24dd5136 (David A. Velasco 2013-02-19 13:28:01 +0100 203)                         while (it.hasNext()) {
24dd5136 (David A. Velasco 2013-02-19 13:28:01 +0100 204)                             it.next().onTransferProgress(readResult, transferred, mFile.getFileLength(), targetFile.getName());
24dd5136 (David A. Velasco 2013-02-19 13:28:01 +0100 205)                         }
68ce2e7a (David A. Velasco 2012-10-09 14:53:25 +0200 206)                     }
68ce2e7a (David A. Velasco 2012-10-09 14:53:25 +0200 207)                 }
68ce2e7a (David A. Velasco 2012-10-09 14:53:25 +0200 208)                 savedFile = true;
78bcf721 (David A. Velasco 2013-08-07 19:13:00 +0200 209)                 Header modificationTime = mGet.getResponseHeader("Last-Modified");
925a0554 (David A. Velasco 2012-11-13 15:59:05 +0100 210)                 if (modificationTime != null) {
925a0554 (David A. Velasco 2012-11-13 15:59:05 +0100 211)                     Date d = WebdavUtils.parseResponseDate((String) modificationTime.getValue());
925a0554 (David A. Velasco 2012-11-13 15:59:05 +0100 212)                     mModificationTimestamp = (d != null) ? d.getTime() : 0;
925a0554 (David A. Velasco 2012-11-13 15:59:05 +0100 213)                 }
68ce2e7a (David A. Velasco 2012-10-09 14:53:25 +0200 214)                 
68ce2e7a (David A. Velasco 2012-10-09 14:53:25 +0200 215)             } else {
78bcf721 (David A. Velasco 2013-08-07 19:13:00 +0200 216)                 client.exhaustResponse(mGet.getResponseBodyAsStream());
68ce2e7a (David A. Velasco 2012-10-09 14:53:25 +0200 217)             }
68ce2e7a (David A. Velasco 2012-10-09 14:53:25 +0200 218)                 
68ce2e7a (David A. Velasco 2012-10-09 14:53:25 +0200 219)         } finally {
b246cf70 (David A. Velasco 2012-10-10 11:30:47 +0200 220)             if (fos != null) fos.close();
68ce2e7a (David A. Velasco 2012-10-09 14:53:25 +0200 221)             if (!savedFile && targetFile.exists()) {
68ce2e7a (David A. Velasco 2012-10-09 14:53:25 +0200 222)                 targetFile.delete();
68ce2e7a (David A. Velasco 2012-10-09 14:53:25 +0200 223)             }
78bcf721 (David A. Velasco 2013-08-07 19:13:00 +0200 224)             mGet.releaseConnection();    // let the connection available for other methods
68ce2e7a (David A. Velasco 2012-10-09 14:53:25 +0200 225)         }
68ce2e7a (David A. Velasco 2012-10-09 14:53:25 +0200 226)         return status;
68ce2e7a (David A. Velasco 2012-10-09 14:53:25 +0200 227)     }
68ce2e7a (David A. Velasco 2012-10-09 14:53:25 +0200 228) 
68ce2e7a (David A. Velasco 2012-10-09 14:53:25 +0200 229)     
68ce2e7a (David A. Velasco 2012-10-09 14:53:25 +0200 230)     public void cancel() {
b246cf70 (David A. Velasco 2012-10-10 11:30:47 +0200 231)         mCancellationRequested.set(true);   // atomic set; there is no need of synchronizing it
68ce2e7a (David A. Velasco 2012-10-09 14:53:25 +0200 232)     }
25f307c9 (David A. Velasco 2012-10-10 13:13:22 +0200 233) 
24dd5136 (David A. Velasco 2013-02-19 13:28:01 +0100 234) 
68ce2e7a (David A. Velasco 2012-10-09 14:53:25 +0200 235) }
