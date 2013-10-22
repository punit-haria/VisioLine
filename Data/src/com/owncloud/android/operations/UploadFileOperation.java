53b67429 (David A. Velasco 2012-09-12 16:16:56 +0200   1) /* ownCloud Android client application
bb257ec7 (David A. Velasco 2013-02-07 18:45:10 +0100   2)  *   Copyright (C) 2012-2013 ownCloud Inc.
53b67429 (David A. Velasco 2012-09-12 16:16:56 +0200   3)  *
53b67429 (David A. Velasco 2012-09-12 16:16:56 +0200   4)  *   This program is free software: you can redistribute it and/or modify
bdc0332c (David A. Velasco 2013-04-17 12:26:13 +0200   5)  *   it under the terms of the GNU General Public License version 2,
bdc0332c (David A. Velasco 2013-04-17 12:26:13 +0200   6)  *   as published by the Free Software Foundation.
53b67429 (David A. Velasco 2012-09-12 16:16:56 +0200   7)  *
53b67429 (David A. Velasco 2012-09-12 16:16:56 +0200   8)  *   This program is distributed in the hope that it will be useful,
53b67429 (David A. Velasco 2012-09-12 16:16:56 +0200   9)  *   but WITHOUT ANY WARRANTY; without even the implied warranty of
53b67429 (David A. Velasco 2012-09-12 16:16:56 +0200  10)  *   MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
53b67429 (David A. Velasco 2012-09-12 16:16:56 +0200  11)  *   GNU General Public License for more details.
53b67429 (David A. Velasco 2012-09-12 16:16:56 +0200  12)  *
53b67429 (David A. Velasco 2012-09-12 16:16:56 +0200  13)  *   You should have received a copy of the GNU General Public License
53b67429 (David A. Velasco 2012-09-12 16:16:56 +0200  14)  *   along with this program.  If not, see <http://www.gnu.org/licenses/>.
53b67429 (David A. Velasco 2012-09-12 16:16:56 +0200  15)  *
53b67429 (David A. Velasco 2012-09-12 16:16:56 +0200  16)  */
53b67429 (David A. Velasco 2012-09-12 16:16:56 +0200  17) 
53b67429 (David A. Velasco 2012-09-12 16:16:56 +0200  18) package com.owncloud.android.operations;
53b67429 (David A. Velasco 2012-09-12 16:16:56 +0200  19) 
53b67429 (David A. Velasco 2012-09-12 16:16:56 +0200  20) import java.io.File;
fd9086a8 (David A. Velasco 2012-11-27 14:28:19 +0100  21) import java.io.FileInputStream;
fd9086a8 (David A. Velasco 2012-11-27 14:28:19 +0100  22) import java.io.FileOutputStream;
53b67429 (David A. Velasco 2012-09-12 16:16:56 +0200  23) import java.io.IOException;
fd9086a8 (David A. Velasco 2012-11-27 14:28:19 +0100  24) import java.io.InputStream;
fd9086a8 (David A. Velasco 2012-11-27 14:28:19 +0100  25) import java.io.OutputStream;
5fc7cd13 (David A. Velasco 2012-10-11 11:24:17 +0200  26) import java.util.HashSet;
5fc7cd13 (David A. Velasco 2012-10-11 11:24:17 +0200  27) import java.util.Set;
27112bd2 (David A. Velasco 2012-10-11 16:40:56 +0200  28) import java.util.concurrent.atomic.AtomicBoolean;
53b67429 (David A. Velasco 2012-09-12 16:16:56 +0200  29) 
53b67429 (David A. Velasco 2012-09-12 16:16:56 +0200  30) import org.apache.commons.httpclient.HttpException;
53b67429 (David A. Velasco 2012-09-12 16:16:56 +0200  31) import org.apache.commons.httpclient.methods.PutMethod;
24dd5136 (David A. Velasco 2013-02-19 13:28:01 +0100  32) import org.apache.commons.httpclient.methods.RequestEntity;
53b67429 (David A. Velasco 2012-09-12 16:16:56 +0200  33) import org.apache.http.HttpStatus;
53b67429 (David A. Velasco 2012-09-12 16:16:56 +0200  34) 
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100  35) import com.owncloud.android.Log_OC;
6c5f5613 (Matthias Baumann 2013-03-17 10:38:42 +0100  36) import android.accounts.Account;
6c5f5613 (Matthias Baumann 2013-03-17 10:38:42 +0100  37) 
27112bd2 (David A. Velasco 2012-10-11 16:40:56 +0200  38) import com.owncloud.android.datamodel.OCFile;
a1c538db (David A. Velasco 2012-11-27 15:15:17 +0100  39) import com.owncloud.android.files.services.FileUploader;
24dd5136 (David A. Velasco 2013-02-19 13:28:01 +0100  40) import com.owncloud.android.network.ProgressiveDataTransferer;
53b67429 (David A. Velasco 2012-09-12 16:16:56 +0200  41) import com.owncloud.android.operations.RemoteOperation;
53b67429 (David A. Velasco 2012-09-12 16:16:56 +0200  42) import com.owncloud.android.operations.RemoteOperationResult;
fd9086a8 (David A. Velasco 2012-11-27 14:28:19 +0100  43) import com.owncloud.android.operations.RemoteOperationResult.ResultCode;
fd9086a8 (David A. Velasco 2012-11-27 14:28:19 +0100  44) import com.owncloud.android.utils.FileStorageUtils;
53b67429 (David A. Velasco 2012-09-12 16:16:56 +0200  45) 
53b67429 (David A. Velasco 2012-09-12 16:16:56 +0200  46) import eu.alefzero.webdav.FileRequestEntity;
53b67429 (David A. Velasco 2012-09-12 16:16:56 +0200  47) import eu.alefzero.webdav.OnDatatransferProgressListener;
53b67429 (David A. Velasco 2012-09-12 16:16:56 +0200  48) import eu.alefzero.webdav.WebdavClient;
53b67429 (David A. Velasco 2012-09-12 16:16:56 +0200  49) import eu.alefzero.webdav.WebdavUtils;
53b67429 (David A. Velasco 2012-09-12 16:16:56 +0200  50) 
53b67429 (David A. Velasco 2012-09-12 16:16:56 +0200  51) /**
53b67429 (David A. Velasco 2012-09-12 16:16:56 +0200  52)  * Remote operation performing the upload of a file to an ownCloud server
53b67429 (David A. Velasco 2012-09-12 16:16:56 +0200  53)  * 
53b67429 (David A. Velasco 2012-09-12 16:16:56 +0200  54)  * @author David A. Velasco
53b67429 (David A. Velasco 2012-09-12 16:16:56 +0200  55)  */
53b67429 (David A. Velasco 2012-09-12 16:16:56 +0200  56) public class UploadFileOperation extends RemoteOperation {
6c5f5613 (Matthias Baumann 2013-03-17 10:38:42 +0100  57) 
c6a3abf0 (David A. Velasco 2012-10-26 13:45:59 +0200  58)     private static final String TAG = UploadFileOperation.class.getSimpleName();
53b67429 (David A. Velasco 2012-09-12 16:16:56 +0200  59) 
27112bd2 (David A. Velasco 2012-10-11 16:40:56 +0200  60)     private Account mAccount;
27112bd2 (David A. Velasco 2012-10-11 16:40:56 +0200  61)     private OCFile mFile;
195b0092 (David A. Velasco 2012-11-20 14:12:32 +0100  62)     private OCFile mOldFile;
53b67429 (David A. Velasco 2012-09-12 16:16:56 +0200  63)     private String mRemotePath = null;
53b67429 (David A. Velasco 2012-09-12 16:16:56 +0200  64)     private boolean mIsInstant = false;
cf27417e (David A. Velasco 2012-10-15 15:22:07 +0200  65)     private boolean mRemoteFolderToBeCreated = false;
53b67429 (David A. Velasco 2012-09-12 16:16:56 +0200  66)     private boolean mForceOverwrite = false;
a1c538db (David A. Velasco 2012-11-27 15:15:17 +0100  67)     private int mLocalBehaviour = FileUploader.LOCAL_BEHAVIOUR_COPY;
195b0092 (David A. Velasco 2012-11-20 14:12:32 +0100  68)     private boolean mWasRenamed = false;
80448dc1 (David A. Velasco 2012-12-05 12:22:26 +0100  69)     private String mOriginalFileName = null;
80448dc1 (David A. Velasco 2012-12-05 12:22:26 +0100  70)     private String mOriginalStoragePath = null;
27112bd2 (David A. Velasco 2012-10-11 16:40:56 +0200  71)     PutMethod mPutMethod = null;
5fc7cd13 (David A. Velasco 2012-10-11 11:24:17 +0200  72)     private Set<OnDatatransferProgressListener> mDataTransferListeners = new HashSet<OnDatatransferProgressListener>();
27112bd2 (David A. Velasco 2012-10-11 16:40:56 +0200  73)     private final AtomicBoolean mCancellationRequested = new AtomicBoolean(false);
195b0092 (David A. Velasco 2012-11-20 14:12:32 +0100  74) 
24dd5136 (David A. Velasco 2013-02-19 13:28:01 +0100  75)     protected RequestEntity mEntity = null;
24dd5136 (David A. Velasco 2013-02-19 13:28:01 +0100  76) 
53b67429 (David A. Velasco 2012-09-12 16:16:56 +0200  77)     
5fc7cd13 (David A. Velasco 2012-10-11 11:24:17 +0200  78)     public UploadFileOperation( Account account,
27112bd2 (David A. Velasco 2012-10-11 16:40:56 +0200  79)                                 OCFile file,
5fc7cd13 (David A. Velasco 2012-10-11 11:24:17 +0200  80)                                 boolean isInstant, 
fd9086a8 (David A. Velasco 2012-11-27 14:28:19 +0100  81)                                 boolean forceOverwrite,
a1c538db (David A. Velasco 2012-11-27 15:15:17 +0100  82)                                 int localBehaviour) {
5fc7cd13 (David A. Velasco 2012-10-11 11:24:17 +0200  83)         if (account == null)
27112bd2 (David A. Velasco 2012-10-11 16:40:56 +0200  84)             throw new IllegalArgumentException("Illegal NULL account in UploadFileOperation creation");
27112bd2 (David A. Velasco 2012-10-11 16:40:56 +0200  85)         if (file == null)
27112bd2 (David A. Velasco 2012-10-11 16:40:56 +0200  86)             throw new IllegalArgumentException("Illegal NULL file in UploadFileOperation creation");
6c5f5613 (Matthias Baumann 2013-03-17 10:38:42 +0100  87)         if (file.getStoragePath() == null || file.getStoragePath().length() <= 0
6c5f5613 (Matthias Baumann 2013-03-17 10:38:42 +0100  88)                 || !(new File(file.getStoragePath()).exists())) {
6c5f5613 (Matthias Baumann 2013-03-17 10:38:42 +0100  89)             throw new IllegalArgumentException(
6c5f5613 (Matthias Baumann 2013-03-17 10:38:42 +0100  90)                     "Illegal file in UploadFileOperation; storage path invalid or file not found: "
6c5f5613 (Matthias Baumann 2013-03-17 10:38:42 +0100  91)                             + file.getStoragePath());
5fc7cd13 (David A. Velasco 2012-10-11 11:24:17 +0200  92)         }
6c5f5613 (Matthias Baumann 2013-03-17 10:38:42 +0100  93) 
27112bd2 (David A. Velasco 2012-10-11 16:40:56 +0200  94)         mAccount = account;
27112bd2 (David A. Velasco 2012-10-11 16:40:56 +0200  95)         mFile = file;
27112bd2 (David A. Velasco 2012-10-11 16:40:56 +0200  96)         mRemotePath = file.getRemotePath();
5fc7cd13 (David A. Velasco 2012-10-11 11:24:17 +0200  97)         mIsInstant = isInstant;
5fc7cd13 (David A. Velasco 2012-10-11 11:24:17 +0200  98)         mForceOverwrite = forceOverwrite;
a1c538db (David A. Velasco 2012-11-27 15:15:17 +0100  99)         mLocalBehaviour = localBehaviour;
80448dc1 (David A. Velasco 2012-12-05 12:22:26 +0100 100)         mOriginalStoragePath = mFile.getStoragePath();
80448dc1 (David A. Velasco 2012-12-05 12:22:26 +0100 101)         mOriginalFileName = mFile.getFileName();
5fc7cd13 (David A. Velasco 2012-10-11 11:24:17 +0200 102)     }
5fc7cd13 (David A. Velasco 2012-10-11 11:24:17 +0200 103) 
5fc7cd13 (David A. Velasco 2012-10-11 11:24:17 +0200 104)     public Account getAccount() {
5fc7cd13 (David A. Velasco 2012-10-11 11:24:17 +0200 105)         return mAccount;
5fc7cd13 (David A. Velasco 2012-10-11 11:24:17 +0200 106)     }
6c5f5613 (Matthias Baumann 2013-03-17 10:38:42 +0100 107) 
80448dc1 (David A. Velasco 2012-12-05 12:22:26 +0100 108)     public String getFileName() {
80448dc1 (David A. Velasco 2012-12-05 12:22:26 +0100 109)         return mOriginalFileName;
80448dc1 (David A. Velasco 2012-12-05 12:22:26 +0100 110)     }
6c5f5613 (Matthias Baumann 2013-03-17 10:38:42 +0100 111) 
27112bd2 (David A. Velasco 2012-10-11 16:40:56 +0200 112)     public OCFile getFile() {
27112bd2 (David A. Velasco 2012-10-11 16:40:56 +0200 113)         return mFile;
27112bd2 (David A. Velasco 2012-10-11 16:40:56 +0200 114)     }
6c5f5613 (Matthias Baumann 2013-03-17 10:38:42 +0100 115) 
195b0092 (David A. Velasco 2012-11-20 14:12:32 +0100 116)     public OCFile getOldFile() {
6c5f5613 (Matthias Baumann 2013-03-17 10:38:42 +0100 117)         return mOldFile;
195b0092 (David A. Velasco 2012-11-20 14:12:32 +0100 118)     }
6c5f5613 (Matthias Baumann 2013-03-17 10:38:42 +0100 119) 
80448dc1 (David A. Velasco 2012-12-05 12:22:26 +0100 120)     public String getOriginalStoragePath() {
80448dc1 (David A. Velasco 2012-12-05 12:22:26 +0100 121)         return mOriginalStoragePath;
80448dc1 (David A. Velasco 2012-12-05 12:22:26 +0100 122)     }
6c5f5613 (Matthias Baumann 2013-03-17 10:38:42 +0100 123) 
27112bd2 (David A. Velasco 2012-10-11 16:40:56 +0200 124)     public String getStoragePath() {
27112bd2 (David A. Velasco 2012-10-11 16:40:56 +0200 125)         return mFile.getStoragePath();
53b67429 (David A. Velasco 2012-09-12 16:16:56 +0200 126)     }
53b67429 (David A. Velasco 2012-09-12 16:16:56 +0200 127) 
53b67429 (David A. Velasco 2012-09-12 16:16:56 +0200 128)     public String getRemotePath() {
6c5f5613 (Matthias Baumann 2013-03-17 10:38:42 +0100 129)         return mFile.getRemotePath();
53b67429 (David A. Velasco 2012-09-12 16:16:56 +0200 130)     }
53b67429 (David A. Velasco 2012-09-12 16:16:56 +0200 131) 
53b67429 (David A. Velasco 2012-09-12 16:16:56 +0200 132)     public String getMimeType() {
27112bd2 (David A. Velasco 2012-10-11 16:40:56 +0200 133)         return mFile.getMimetype();
53b67429 (David A. Velasco 2012-09-12 16:16:56 +0200 134)     }
6c5f5613 (Matthias Baumann 2013-03-17 10:38:42 +0100 135) 
53b67429 (David A. Velasco 2012-09-12 16:16:56 +0200 136)     public boolean isInstant() {
53b67429 (David A. Velasco 2012-09-12 16:16:56 +0200 137)         return mIsInstant;
53b67429 (David A. Velasco 2012-09-12 16:16:56 +0200 138)     }
cf27417e (David A. Velasco 2012-10-15 15:22:07 +0200 139) 
cf27417e (David A. Velasco 2012-10-15 15:22:07 +0200 140)     public boolean isRemoteFolderToBeCreated() {
cf27417e (David A. Velasco 2012-10-15 15:22:07 +0200 141)         return mRemoteFolderToBeCreated;
cf27417e (David A. Velasco 2012-10-15 15:22:07 +0200 142)     }
6c5f5613 (Matthias Baumann 2013-03-17 10:38:42 +0100 143) 
cf27417e (David A. Velasco 2012-10-15 15:22:07 +0200 144)     public void setRemoteFolderToBeCreated() {
cf27417e (David A. Velasco 2012-10-15 15:22:07 +0200 145)         mRemoteFolderToBeCreated = true;
cf27417e (David A. Velasco 2012-10-15 15:22:07 +0200 146)     }
cf27417e (David A. Velasco 2012-10-15 15:22:07 +0200 147) 
53b67429 (David A. Velasco 2012-09-12 16:16:56 +0200 148)     public boolean getForceOverwrite() {
53b67429 (David A. Velasco 2012-09-12 16:16:56 +0200 149)         return mForceOverwrite;
53b67429 (David A. Velasco 2012-09-12 16:16:56 +0200 150)     }
6c5f5613 (Matthias Baumann 2013-03-17 10:38:42 +0100 151) 
195b0092 (David A. Velasco 2012-11-20 14:12:32 +0100 152)     public boolean wasRenamed() {
195b0092 (David A. Velasco 2012-11-20 14:12:32 +0100 153)         return mWasRenamed;
195b0092 (David A. Velasco 2012-11-20 14:12:32 +0100 154)     }
6c5f5613 (Matthias Baumann 2013-03-17 10:38:42 +0100 155) 
5fc7cd13 (David A. Velasco 2012-10-11 11:24:17 +0200 156)     public Set<OnDatatransferProgressListener> getDataTransferListeners() {
5fc7cd13 (David A. Velasco 2012-10-11 11:24:17 +0200 157)         return mDataTransferListeners;
53b67429 (David A. Velasco 2012-09-12 16:16:56 +0200 158)     }
53b67429 (David A. Velasco 2012-09-12 16:16:56 +0200 159)     
5fc7cd13 (David A. Velasco 2012-10-11 11:24:17 +0200 160)     public void addDatatransferProgressListener (OnDatatransferProgressListener listener) {
24dd5136 (David A. Velasco 2013-02-19 13:28:01 +0100 161)         synchronized (mDataTransferListeners) {
24dd5136 (David A. Velasco 2013-02-19 13:28:01 +0100 162)             mDataTransferListeners.add(listener);
24dd5136 (David A. Velasco 2013-02-19 13:28:01 +0100 163)         }
24dd5136 (David A. Velasco 2013-02-19 13:28:01 +0100 164)         if (mEntity != null) {
24dd5136 (David A. Velasco 2013-02-19 13:28:01 +0100 165)             ((ProgressiveDataTransferer)mEntity).addDatatransferProgressListener(listener);
24dd5136 (David A. Velasco 2013-02-19 13:28:01 +0100 166)         }
53b67429 (David A. Velasco 2012-09-12 16:16:56 +0200 167)     }
53b67429 (David A. Velasco 2012-09-12 16:16:56 +0200 168)     
24dd5136 (David A. Velasco 2013-02-19 13:28:01 +0100 169)     public void removeDatatransferProgressListener(OnDatatransferProgressListener listener) {
24dd5136 (David A. Velasco 2013-02-19 13:28:01 +0100 170)         synchronized (mDataTransferListeners) {
24dd5136 (David A. Velasco 2013-02-19 13:28:01 +0100 171)             mDataTransferListeners.remove(listener);
24dd5136 (David A. Velasco 2013-02-19 13:28:01 +0100 172)         }
24dd5136 (David A. Velasco 2013-02-19 13:28:01 +0100 173)         if (mEntity != null) {
24dd5136 (David A. Velasco 2013-02-19 13:28:01 +0100 174)             ((ProgressiveDataTransferer)mEntity).removeDatatransferProgressListener(listener);
24dd5136 (David A. Velasco 2013-02-19 13:28:01 +0100 175)         }
53b67429 (David A. Velasco 2012-09-12 16:16:56 +0200 176)     }
6c5f5613 (Matthias Baumann 2013-03-17 10:38:42 +0100 177) 
53b67429 (David A. Velasco 2012-09-12 16:16:56 +0200 178)     @Override
53b67429 (David A. Velasco 2012-09-12 16:16:56 +0200 179)     protected RemoteOperationResult run(WebdavClient client) {
53b67429 (David A. Velasco 2012-09-12 16:16:56 +0200 180)         RemoteOperationResult result = null;
fd9086a8 (David A. Velasco 2012-11-27 14:28:19 +0100 181)         boolean localCopyPassed = false, nameCheckPassed = false;
80448dc1 (David A. Velasco 2012-12-05 12:22:26 +0100 182)         File temporalFile = null, originalFile = new File(mOriginalStoragePath), expectedFile = null;
53b67429 (David A. Velasco 2012-09-12 16:16:56 +0200 183)         try {
6c5f5613 (Matthias Baumann 2013-03-17 10:38:42 +0100 184)             // / rename the file to upload, if necessary
53b67429 (David A. Velasco 2012-09-12 16:16:56 +0200 185)             if (!mForceOverwrite) {
195b0092 (David A. Velasco 2012-11-20 14:12:32 +0100 186)                 String remotePath = getAvailableRemotePath(client, mRemotePath);
195b0092 (David A. Velasco 2012-11-20 14:12:32 +0100 187)                 mWasRenamed = !remotePath.equals(mRemotePath);
195b0092 (David A. Velasco 2012-11-20 14:12:32 +0100 188)                 if (mWasRenamed) {
6c5f5613 (Matthias Baumann 2013-03-17 10:38:42 +0100 189)                     createNewOCFile(remotePath);
195b0092 (David A. Velasco 2012-11-20 14:12:32 +0100 190)                 }
53b67429 (David A. Velasco 2012-09-12 16:16:56 +0200 191)             }
fd9086a8 (David A. Velasco 2012-11-27 14:28:19 +0100 192)             nameCheckPassed = true;
6c5f5613 (Matthias Baumann 2013-03-17 10:38:42 +0100 193) 
6c5f5613 (Matthias Baumann 2013-03-17 10:38:42 +0100 194)             String expectedPath = FileStorageUtils.getDefaultSavePathFor(mAccount.name, mFile); // /
6c5f5613 (Matthias Baumann 2013-03-17 10:38:42 +0100 195)                                                                                                 // not
6c5f5613 (Matthias Baumann 2013-03-17 10:38:42 +0100 196)                                                                                                 // before
6c5f5613 (Matthias Baumann 2013-03-17 10:38:42 +0100 197)                                                                                                 // getAvailableRemotePath()
6c5f5613 (Matthias Baumann 2013-03-17 10:38:42 +0100 198)                                                                                                 // !!!
a1c538db (David A. Velasco 2012-11-27 15:15:17 +0100 199)             expectedFile = new File(expectedPath);
6c5f5613 (Matthias Baumann 2013-03-17 10:38:42 +0100 200) 
6c5f5613 (Matthias Baumann 2013-03-17 10:38:42 +0100 201)             // / check location of local file; if not the expected, copy to a
6c5f5613 (Matthias Baumann 2013-03-17 10:38:42 +0100 202)             // temporal file before upload (if COPY is the expected behaviour)
80448dc1 (David A. Velasco 2012-12-05 12:22:26 +0100 203)             if (!mOriginalStoragePath.equals(expectedPath) && mLocalBehaviour == FileUploader.LOCAL_BEHAVIOUR_COPY) {
a1c538db (David A. Velasco 2012-11-27 15:15:17 +0100 204) 
83615408 (David A. Velasco 2012-12-04 14:36:49 +0100 205)                 if (FileStorageUtils.getUsableSpace(mAccount.name) < originalFile.length()) {
a1c538db (David A. Velasco 2012-11-27 15:15:17 +0100 206)                     result = new RemoteOperationResult(ResultCode.LOCAL_STORAGE_FULL);
6c5f5613 (Matthias Baumann 2013-03-17 10:38:42 +0100 207)                     return result; // error condition when the file should be
6c5f5613 (Matthias Baumann 2013-03-17 10:38:42 +0100 208)                                    // copied
6c5f5613 (Matthias Baumann 2013-03-17 10:38:42 +0100 209) 
a1c538db (David A. Velasco 2012-11-27 15:15:17 +0100 210)                 } else {
a1c538db (David A. Velasco 2012-11-27 15:15:17 +0100 211)                     String temporalPath = FileStorageUtils.getTemporalPath(mAccount.name) + mFile.getRemotePath();
a1c538db (David A. Velasco 2012-11-27 15:15:17 +0100 212)                     mFile.setStoragePath(temporalPath);
a1c538db (David A. Velasco 2012-11-27 15:15:17 +0100 213)                     temporalFile = new File(temporalPath);
6c5f5613 (Matthias Baumann 2013-03-17 10:38:42 +0100 214)                     if (!mOriginalStoragePath.equals(temporalPath)) { // preventing
6c5f5613 (Matthias Baumann 2013-03-17 10:38:42 +0100 215)                                                                       // weird
6c5f5613 (Matthias Baumann 2013-03-17 10:38:42 +0100 216)                                                                       // but
6c5f5613 (Matthias Baumann 2013-03-17 10:38:42 +0100 217)                                                                       // possible
6c5f5613 (Matthias Baumann 2013-03-17 10:38:42 +0100 218)                                                                       // situation
2a913bfb (David A. Velasco 2012-11-30 11:29:18 +0100 219)                         InputStream in = null;
2a913bfb (David A. Velasco 2012-11-30 11:29:18 +0100 220)                         OutputStream out = null;
2a913bfb (David A. Velasco 2012-11-30 11:29:18 +0100 221)                         try {
4476c047 (David A. Velasco 2012-12-05 16:01:19 +0100 222)                             File temporalParent = temporalFile.getParentFile();
4476c047 (David A. Velasco 2012-12-05 16:01:19 +0100 223)                             temporalParent.mkdirs();
4476c047 (David A. Velasco 2012-12-05 16:01:19 +0100 224)                             if (!temporalParent.isDirectory()) {
4476c047 (David A. Velasco 2012-12-05 16:01:19 +0100 225)                                 throw new IOException("Unexpected error: parent directory could not be created");
4476c047 (David A. Velasco 2012-12-05 16:01:19 +0100 226)                             }
4476c047 (David A. Velasco 2012-12-05 16:01:19 +0100 227)                             temporalFile.createNewFile();
4476c047 (David A. Velasco 2012-12-05 16:01:19 +0100 228)                             if (!temporalFile.isFile()) {
4476c047 (David A. Velasco 2012-12-05 16:01:19 +0100 229)                                 throw new IOException("Unexpected error: target file could not be created");
6c5f5613 (Matthias Baumann 2013-03-17 10:38:42 +0100 230)                             }
2a913bfb (David A. Velasco 2012-11-30 11:29:18 +0100 231)                             in = new FileInputStream(originalFile);
2a913bfb (David A. Velasco 2012-11-30 11:29:18 +0100 232)                             out = new FileOutputStream(temporalFile);
2a913bfb (David A. Velasco 2012-11-30 11:29:18 +0100 233)                             byte[] buf = new byte[1024];
2a913bfb (David A. Velasco 2012-11-30 11:29:18 +0100 234)                             int len;
6c5f5613 (Matthias Baumann 2013-03-17 10:38:42 +0100 235)                             while ((len = in.read(buf)) > 0) {
2a913bfb (David A. Velasco 2012-11-30 11:29:18 +0100 236)                                 out.write(buf, 0, len);
2a913bfb (David A. Velasco 2012-11-30 11:29:18 +0100 237)                             }
6c5f5613 (Matthias Baumann 2013-03-17 10:38:42 +0100 238) 
a7535406 (David A. Velasco 2012-11-30 14:39:31 +0100 239)                         } catch (Exception e) {
a7535406 (David A. Velasco 2012-11-30 14:39:31 +0100 240)                             result = new RemoteOperationResult(ResultCode.LOCAL_STORAGE_NOT_COPIED);
a7535406 (David A. Velasco 2012-11-30 14:39:31 +0100 241)                             return result;
6c5f5613 (Matthias Baumann 2013-03-17 10:38:42 +0100 242) 
2a913bfb (David A. Velasco 2012-11-30 11:29:18 +0100 243)                         } finally {
2a913bfb (David A. Velasco 2012-11-30 11:29:18 +0100 244)                             try {
6c5f5613 (Matthias Baumann 2013-03-17 10:38:42 +0100 245)                                 if (in != null)
6c5f5613 (Matthias Baumann 2013-03-17 10:38:42 +0100 246)                                     in.close();
2a913bfb (David A. Velasco 2012-11-30 11:29:18 +0100 247)                             } catch (Exception e) {
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100 248)                                 Log_OC.d(TAG, "Weird exception while closing input stream for " + mOriginalStoragePath + " (ignoring)", e);
2a913bfb (David A. Velasco 2012-11-30 11:29:18 +0100 249)                             }
2a913bfb (David A. Velasco 2012-11-30 11:29:18 +0100 250)                             try {
6c5f5613 (Matthias Baumann 2013-03-17 10:38:42 +0100 251)                                 if (out != null)
6c5f5613 (Matthias Baumann 2013-03-17 10:38:42 +0100 252)                                     out.close();
2a913bfb (David A. Velasco 2012-11-30 11:29:18 +0100 253)                             } catch (Exception e) {
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100 254)                                 Log_OC.d(TAG, "Weird exception while closing output stream for " + expectedPath + " (ignoring)", e);
2a913bfb (David A. Velasco 2012-11-30 11:29:18 +0100 255)                             }
fd9086a8 (David A. Velasco 2012-11-27 14:28:19 +0100 256)                         }
fd9086a8 (David A. Velasco 2012-11-27 14:28:19 +0100 257)                     }
a1c538db (David A. Velasco 2012-11-27 15:15:17 +0100 258)                 }
fd9086a8 (David A. Velasco 2012-11-27 14:28:19 +0100 259)             }
fd9086a8 (David A. Velasco 2012-11-27 14:28:19 +0100 260)             localCopyPassed = true;
6c5f5613 (Matthias Baumann 2013-03-17 10:38:42 +0100 261) 
6c5f5613 (Matthias Baumann 2013-03-17 10:38:42 +0100 262)             // / perform the upload
6c5f5613 (Matthias Baumann 2013-03-17 10:38:42 +0100 263)             synchronized (mCancellationRequested) {
27112bd2 (David A. Velasco 2012-10-11 16:40:56 +0200 264)                 if (mCancellationRequested.get()) {
27112bd2 (David A. Velasco 2012-10-11 16:40:56 +0200 265)                     throw new OperationCancelledException();
27112bd2 (David A. Velasco 2012-10-11 16:40:56 +0200 266)                 } else {
fd9086a8 (David A. Velasco 2012-11-27 14:28:19 +0100 267)                     mPutMethod = new PutMethod(client.getBaseUri() + WebdavUtils.encodePath(mFile.getRemotePath()));
27112bd2 (David A. Velasco 2012-10-11 16:40:56 +0200 268)                 }
27112bd2 (David A. Velasco 2012-10-11 16:40:56 +0200 269)             }
53b67429 (David A. Velasco 2012-09-12 16:16:56 +0200 270)             int status = uploadFile(client);
6c5f5613 (Matthias Baumann 2013-03-17 10:38:42 +0100 271) 
6c5f5613 (Matthias Baumann 2013-03-17 10:38:42 +0100 272)             // / move local temporal file or original file to its corresponding
6c5f5613 (Matthias Baumann 2013-03-17 10:38:42 +0100 273)             // location in the ownCloud local folder
fd9086a8 (David A. Velasco 2012-11-27 14:28:19 +0100 274)             if (isSuccess(status)) {
a1c538db (David A. Velasco 2012-11-27 15:15:17 +0100 275)                 if (mLocalBehaviour == FileUploader.LOCAL_BEHAVIOUR_FORGET) {
a1c538db (David A. Velasco 2012-11-27 15:15:17 +0100 276)                     mFile.setStoragePath(null);
6c5f5613 (Matthias Baumann 2013-03-17 10:38:42 +0100 277) 
a1c538db (David A. Velasco 2012-11-27 15:15:17 +0100 278)                 } else {
a1c538db (David A. Velasco 2012-11-27 15:15:17 +0100 279)                     mFile.setStoragePath(expectedPath);
a1c538db (David A. Velasco 2012-11-27 15:15:17 +0100 280)                     File fileToMove = null;
6c5f5613 (Matthias Baumann 2013-03-17 10:38:42 +0100 281)                     if (temporalFile != null) { // FileUploader.LOCAL_BEHAVIOUR_COPY
6c5f5613 (Matthias Baumann 2013-03-17 10:38:42 +0100 282)                                                 // ; see where temporalFile was
6c5f5613 (Matthias Baumann 2013-03-17 10:38:42 +0100 283)                                                 // set
a1c538db (David A. Velasco 2012-11-27 15:15:17 +0100 284)                         fileToMove = temporalFile;
6c5f5613 (Matthias Baumann 2013-03-17 10:38:42 +0100 285)                     } else { // FileUploader.LOCAL_BEHAVIOUR_MOVE
a1c538db (David A. Velasco 2012-11-27 15:15:17 +0100 286)                         fileToMove = originalFile;
a1c538db (David A. Velasco 2012-11-27 15:15:17 +0100 287)                     }
082c11a1 (David A. Velasco 2012-12-05 12:37:19 +0100 288)                     if (!expectedFile.equals(fileToMove)) {
082c11a1 (David A. Velasco 2012-12-05 12:37:19 +0100 289)                         File expectedFolder = expectedFile.getParentFile();
082c11a1 (David A. Velasco 2012-12-05 12:37:19 +0100 290)                         expectedFolder.mkdirs();
082c11a1 (David A. Velasco 2012-12-05 12:37:19 +0100 291)                         if (!expectedFolder.isDirectory() || !fileToMove.renameTo(expectedFile)) {
082c11a1 (David A. Velasco 2012-12-05 12:37:19 +0100 292)                             mFile.setStoragePath(null); // forget the local file
6c5f5613 (Matthias Baumann 2013-03-17 10:38:42 +0100 293)                             // by now, treat this as a success; the file was
6c5f5613 (Matthias Baumann 2013-03-17 10:38:42 +0100 294)                             // uploaded; the user won't like that the local file
6c5f5613 (Matthias Baumann 2013-03-17 10:38:42 +0100 295)                             // is not linked, but this should be a very rare
6c5f5613 (Matthias Baumann 2013-03-17 10:38:42 +0100 296)                             // fail;
6c5f5613 (Matthias Baumann 2013-03-17 10:38:42 +0100 297)                             // the best option could be show a warning message
6c5f5613 (Matthias Baumann 2013-03-17 10:38:42 +0100 298)                             // (but not a fail)
6c5f5613 (Matthias Baumann 2013-03-17 10:38:42 +0100 299)                             // result = new
6c5f5613 (Matthias Baumann 2013-03-17 10:38:42 +0100 300)                             // RemoteOperationResult(ResultCode.LOCAL_STORAGE_NOT_MOVED);
6c5f5613 (Matthias Baumann 2013-03-17 10:38:42 +0100 301)                             // return result;
082c11a1 (David A. Velasco 2012-12-05 12:37:19 +0100 302)                         }
fd9086a8 (David A. Velasco 2012-11-27 14:28:19 +0100 303)                     }
6c5f5613 (Matthias Baumann 2013-03-17 10:38:42 +0100 304)                 }
fd9086a8 (David A. Velasco 2012-11-27 14:28:19 +0100 305)             }
6c5f5613 (Matthias Baumann 2013-03-17 10:38:42 +0100 306) 
78bcf721 (David A. Velasco 2013-08-07 19:13:00 +0200 307)             result = new RemoteOperationResult(isSuccess(status), status, (mPutMethod != null ? mPutMethod.getResponseHeaders() : null));
6c5f5613 (Matthias Baumann 2013-03-17 10:38:42 +0100 308) 
53b67429 (David A. Velasco 2012-09-12 16:16:56 +0200 309)         } catch (Exception e) {
a1c538db (David A. Velasco 2012-11-27 15:15:17 +0100 310)             // TODO something cleaner with cancellations
27112bd2 (David A. Velasco 2012-10-11 16:40:56 +0200 311)             if (mCancellationRequested.get()) {
27112bd2 (David A. Velasco 2012-10-11 16:40:56 +0200 312)                 result = new RemoteOperationResult(new OperationCancelledException());
27112bd2 (David A. Velasco 2012-10-11 16:40:56 +0200 313)             } else {
27112bd2 (David A. Velasco 2012-10-11 16:40:56 +0200 314)                 result = new RemoteOperationResult(e);
27112bd2 (David A. Velasco 2012-10-11 16:40:56 +0200 315)             }
6c5f5613 (Matthias Baumann 2013-03-17 10:38:42 +0100 316) 
fd9086a8 (David A. Velasco 2012-11-27 14:28:19 +0100 317)         } finally {
fd9086a8 (David A. Velasco 2012-11-27 14:28:19 +0100 318)             if (temporalFile != null && !originalFile.equals(temporalFile)) {
fd9086a8 (David A. Velasco 2012-11-27 14:28:19 +0100 319)                 temporalFile.delete();
fd9086a8 (David A. Velasco 2012-11-27 14:28:19 +0100 320)             }
fd9086a8 (David A. Velasco 2012-11-27 14:28:19 +0100 321)             if (result.isSuccess()) {
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100 322)                 Log_OC.i(TAG, "Upload of " + mOriginalStoragePath + " to " + mRemotePath + ": " + result.getLogMessage());
fd9086a8 (David A. Velasco 2012-11-27 14:28:19 +0100 323)             } else {
fd9086a8 (David A. Velasco 2012-11-27 14:28:19 +0100 324)                 if (result.getException() != null) {
fd9086a8 (David A. Velasco 2012-11-27 14:28:19 +0100 325)                     String complement = "";
fd9086a8 (David A. Velasco 2012-11-27 14:28:19 +0100 326)                     if (!nameCheckPassed) {
fd9086a8 (David A. Velasco 2012-11-27 14:28:19 +0100 327)                         complement = " (while checking file existence in server)";
fd9086a8 (David A. Velasco 2012-11-27 14:28:19 +0100 328)                     } else if (!localCopyPassed) {
6c5f5613 (Matthias Baumann 2013-03-17 10:38:42 +0100 329)                         complement = " (while copying local file to " + FileStorageUtils.getSavePath(mAccount.name)
6c5f5613 (Matthias Baumann 2013-03-17 10:38:42 +0100 330)                                 + ")";
fd9086a8 (David A. Velasco 2012-11-27 14:28:19 +0100 331)                     }
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100 332)                     Log_OC.e(TAG, "Upload of " + mOriginalStoragePath + " to " + mRemotePath + ": " + result.getLogMessage() + complement, result.getException());
fd9086a8 (David A. Velasco 2012-11-27 14:28:19 +0100 333)                 } else {
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100 334)                     Log_OC.e(TAG, "Upload of " + mOriginalStoragePath + " to " + mRemotePath + ": " + result.getLogMessage());
fd9086a8 (David A. Velasco 2012-11-27 14:28:19 +0100 335)                 }
fd9086a8 (David A. Velasco 2012-11-27 14:28:19 +0100 336)             }
53b67429 (David A. Velasco 2012-09-12 16:16:56 +0200 337)         }
6c5f5613 (Matthias Baumann 2013-03-17 10:38:42 +0100 338) 
53b67429 (David A. Velasco 2012-09-12 16:16:56 +0200 339)         return result;
53b67429 (David A. Velasco 2012-09-12 16:16:56 +0200 340)     }
53b67429 (David A. Velasco 2012-09-12 16:16:56 +0200 341) 
195b0092 (David A. Velasco 2012-11-20 14:12:32 +0100 342)     private void createNewOCFile(String newRemotePath) {
195b0092 (David A. Velasco 2012-11-20 14:12:32 +0100 343)         // a new OCFile instance must be created for a new remote path
195b0092 (David A. Velasco 2012-11-20 14:12:32 +0100 344)         OCFile newFile = new OCFile(newRemotePath);
195b0092 (David A. Velasco 2012-11-20 14:12:32 +0100 345)         newFile.setCreationTimestamp(mFile.getCreationTimestamp());
195b0092 (David A. Velasco 2012-11-20 14:12:32 +0100 346)         newFile.setFileLength(mFile.getFileLength());
195b0092 (David A. Velasco 2012-11-20 14:12:32 +0100 347)         newFile.setMimetype(mFile.getMimetype());
195b0092 (David A. Velasco 2012-11-20 14:12:32 +0100 348)         newFile.setModificationTimestamp(mFile.getModificationTimestamp());
abd5f515 (David A. Velasco 2012-12-05 16:14:01 +0100 349)         newFile.setModificationTimestampAtLastSyncForData(mFile.getModificationTimestampAtLastSyncForData());
195b0092 (David A. Velasco 2012-11-20 14:12:32 +0100 350)         // newFile.setEtag(mFile.getEtag())
195b0092 (David A. Velasco 2012-11-20 14:12:32 +0100 351)         newFile.setKeepInSync(mFile.keepInSync());
195b0092 (David A. Velasco 2012-11-20 14:12:32 +0100 352)         newFile.setLastSyncDateForProperties(mFile.getLastSyncDateForProperties());
195b0092 (David A. Velasco 2012-11-20 14:12:32 +0100 353)         newFile.setLastSyncDateForData(mFile.getLastSyncDateForData());
195b0092 (David A. Velasco 2012-11-20 14:12:32 +0100 354)         newFile.setStoragePath(mFile.getStoragePath());
195b0092 (David A. Velasco 2012-11-20 14:12:32 +0100 355)         newFile.setParentId(mFile.getParentId());
195b0092 (David A. Velasco 2012-11-20 14:12:32 +0100 356)         mOldFile = mFile;
195b0092 (David A. Velasco 2012-11-20 14:12:32 +0100 357)         mFile = newFile;
195b0092 (David A. Velasco 2012-11-20 14:12:32 +0100 358)     }
195b0092 (David A. Velasco 2012-11-20 14:12:32 +0100 359) 
53b67429 (David A. Velasco 2012-09-12 16:16:56 +0200 360)     public boolean isSuccess(int status) {
53b67429 (David A. Velasco 2012-09-12 16:16:56 +0200 361)         return ((status == HttpStatus.SC_OK || status == HttpStatus.SC_CREATED || status == HttpStatus.SC_NO_CONTENT));
53b67429 (David A. Velasco 2012-09-12 16:16:56 +0200 362)     }
6c5f5613 (Matthias Baumann 2013-03-17 10:38:42 +0100 363) 
27112bd2 (David A. Velasco 2012-10-11 16:40:56 +0200 364)     protected int uploadFile(WebdavClient client) throws HttpException, IOException, OperationCancelledException {
53b67429 (David A. Velasco 2012-09-12 16:16:56 +0200 365)         int status = -1;
53b67429 (David A. Velasco 2012-09-12 16:16:56 +0200 366)         try {
27112bd2 (David A. Velasco 2012-10-11 16:40:56 +0200 367)             File f = new File(mFile.getStoragePath());
24dd5136 (David A. Velasco 2013-02-19 13:28:01 +0100 368)             mEntity  = new FileRequestEntity(f, getMimeType());
24dd5136 (David A. Velasco 2013-02-19 13:28:01 +0100 369)             synchronized (mDataTransferListeners) {
24dd5136 (David A. Velasco 2013-02-19 13:28:01 +0100 370)                 ((ProgressiveDataTransferer)mEntity).addDatatransferProgressListeners(mDataTransferListeners);
24dd5136 (David A. Velasco 2013-02-19 13:28:01 +0100 371)             }
24dd5136 (David A. Velasco 2013-02-19 13:28:01 +0100 372)             mPutMethod.setRequestEntity(mEntity);
27112bd2 (David A. Velasco 2012-10-11 16:40:56 +0200 373)             status = client.executeMethod(mPutMethod);
27112bd2 (David A. Velasco 2012-10-11 16:40:56 +0200 374)             client.exhaustResponse(mPutMethod.getResponseBodyAsStream());
6c5f5613 (Matthias Baumann 2013-03-17 10:38:42 +0100 375) 
53b67429 (David A. Velasco 2012-09-12 16:16:56 +0200 376)         } finally {
6c5f5613 (Matthias Baumann 2013-03-17 10:38:42 +0100 377)             mPutMethod.releaseConnection(); // let the connection available for
6c5f5613 (Matthias Baumann 2013-03-17 10:38:42 +0100 378)                                             // other methods
53b67429 (David A. Velasco 2012-09-12 16:16:56 +0200 379)         }
53b67429 (David A. Velasco 2012-09-12 16:16:56 +0200 380)         return status;
53b67429 (David A. Velasco 2012-09-12 16:16:56 +0200 381)     }
6c5f5613 (Matthias Baumann 2013-03-17 10:38:42 +0100 382) 
53b67429 (David A. Velasco 2012-09-12 16:16:56 +0200 383)     /**
6c5f5613 (Matthias Baumann 2013-03-17 10:38:42 +0100 384)      * Checks if remotePath does not exist in the server and returns it, or adds
6c5f5613 (Matthias Baumann 2013-03-17 10:38:42 +0100 385)      * a suffix to it in order to avoid the server file is overwritten.
53b67429 (David A. Velasco 2012-09-12 16:16:56 +0200 386)      * 
53b67429 (David A. Velasco 2012-09-12 16:16:56 +0200 387)      * @param string
53b67429 (David A. Velasco 2012-09-12 16:16:56 +0200 388)      * @return
53b67429 (David A. Velasco 2012-09-12 16:16:56 +0200 389)      */
53b67429 (David A. Velasco 2012-09-12 16:16:56 +0200 390)     private String getAvailableRemotePath(WebdavClient wc, String remotePath) throws Exception {
53b67429 (David A. Velasco 2012-09-12 16:16:56 +0200 391)         boolean check = wc.existsFile(remotePath);
53b67429 (David A. Velasco 2012-09-12 16:16:56 +0200 392)         if (!check) {
53b67429 (David A. Velasco 2012-09-12 16:16:56 +0200 393)             return remotePath;
53b67429 (David A. Velasco 2012-09-12 16:16:56 +0200 394)         }
6c5f5613 (Matthias Baumann 2013-03-17 10:38:42 +0100 395) 
53b67429 (David A. Velasco 2012-09-12 16:16:56 +0200 396)         int pos = remotePath.lastIndexOf(".");
53b67429 (David A. Velasco 2012-09-12 16:16:56 +0200 397)         String suffix = "";
53b67429 (David A. Velasco 2012-09-12 16:16:56 +0200 398)         String extension = "";
53b67429 (David A. Velasco 2012-09-12 16:16:56 +0200 399)         if (pos >= 0) {
6c5f5613 (Matthias Baumann 2013-03-17 10:38:42 +0100 400)             extension = remotePath.substring(pos + 1);
53b67429 (David A. Velasco 2012-09-12 16:16:56 +0200 401)             remotePath = remotePath.substring(0, pos);
53b67429 (David A. Velasco 2012-09-12 16:16:56 +0200 402)         }
53b67429 (David A. Velasco 2012-09-12 16:16:56 +0200 403)         int count = 2;
53b67429 (David A. Velasco 2012-09-12 16:16:56 +0200 404)         do {
53b67429 (David A. Velasco 2012-09-12 16:16:56 +0200 405)             suffix = " (" + count + ")";
53b67429 (David A. Velasco 2012-09-12 16:16:56 +0200 406)             if (pos >= 0)
53b67429 (David A. Velasco 2012-09-12 16:16:56 +0200 407)                 check = wc.existsFile(remotePath + suffix + "." + extension);
53b67429 (David A. Velasco 2012-09-12 16:16:56 +0200 408)             else
53b67429 (David A. Velasco 2012-09-12 16:16:56 +0200 409)                 check = wc.existsFile(remotePath + suffix);
53b67429 (David A. Velasco 2012-09-12 16:16:56 +0200 410)             count++;
53b67429 (David A. Velasco 2012-09-12 16:16:56 +0200 411)         } while (check);
53b67429 (David A. Velasco 2012-09-12 16:16:56 +0200 412) 
6c5f5613 (Matthias Baumann 2013-03-17 10:38:42 +0100 413)         if (pos >= 0) {
53b67429 (David A. Velasco 2012-09-12 16:16:56 +0200 414)             return remotePath + suffix + "." + extension;
53b67429 (David A. Velasco 2012-09-12 16:16:56 +0200 415)         } else {
53b67429 (David A. Velasco 2012-09-12 16:16:56 +0200 416)             return remotePath + suffix;
53b67429 (David A. Velasco 2012-09-12 16:16:56 +0200 417)         }
53b67429 (David A. Velasco 2012-09-12 16:16:56 +0200 418)     }
53b67429 (David A. Velasco 2012-09-12 16:16:56 +0200 419) 
27112bd2 (David A. Velasco 2012-10-11 16:40:56 +0200 420)     public void cancel() {
6c5f5613 (Matthias Baumann 2013-03-17 10:38:42 +0100 421)         synchronized (mCancellationRequested) {
27112bd2 (David A. Velasco 2012-10-11 16:40:56 +0200 422)             mCancellationRequested.set(true);
27112bd2 (David A. Velasco 2012-10-11 16:40:56 +0200 423)             if (mPutMethod != null)
27112bd2 (David A. Velasco 2012-10-11 16:40:56 +0200 424)                 mPutMethod.abort();
27112bd2 (David A. Velasco 2012-10-11 16:40:56 +0200 425)         }
27112bd2 (David A. Velasco 2012-10-11 16:40:56 +0200 426)     }
27112bd2 (David A. Velasco 2012-10-11 16:40:56 +0200 427) 
53b67429 (David A. Velasco 2012-09-12 16:16:56 +0200 428) }
