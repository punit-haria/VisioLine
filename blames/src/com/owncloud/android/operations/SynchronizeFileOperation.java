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
6c323657 (Bartek Przybylski 2012-10-27 12:20:52 +0200  19) package com.owncloud.android.operations;
6c323657 (Bartek Przybylski 2012-10-27 12:20:52 +0200  20) 
6c323657 (Bartek Przybylski 2012-10-27 12:20:52 +0200  21) import org.apache.http.HttpStatus;
6c323657 (Bartek Przybylski 2012-10-27 12:20:52 +0200  22) import org.apache.jackrabbit.webdav.MultiStatus;
6c323657 (Bartek Przybylski 2012-10-27 12:20:52 +0200  23) import org.apache.jackrabbit.webdav.client.methods.PropFindMethod;
6c323657 (Bartek Przybylski 2012-10-27 12:20:52 +0200  24) 
6c323657 (Bartek Przybylski 2012-10-27 12:20:52 +0200  25) import android.accounts.Account;
ff2271a8 (David A. Velasco  2012-11-15 17:18:04 +0100  26) import android.content.Context;
ff2271a8 (David A. Velasco  2012-11-15 17:18:04 +0100  27) import android.content.Intent;
6c323657 (Bartek Przybylski 2012-10-27 12:20:52 +0200  28) 
8e36e7cc (zerginator        2013-03-12 07:56:27 +0100  29) import com.owncloud.android.Log_OC;
6c323657 (Bartek Przybylski 2012-10-27 12:20:52 +0200  30) import com.owncloud.android.datamodel.DataStorageManager;
6c323657 (Bartek Przybylski 2012-10-27 12:20:52 +0200  31) import com.owncloud.android.datamodel.OCFile;
ff2271a8 (David A. Velasco  2012-11-15 17:18:04 +0100  32) import com.owncloud.android.files.services.FileDownloader;
ff2271a8 (David A. Velasco  2012-11-15 17:18:04 +0100  33) import com.owncloud.android.files.services.FileUploader;
ff2271a8 (David A. Velasco  2012-11-15 17:18:04 +0100  34) import com.owncloud.android.operations.RemoteOperationResult.ResultCode;
6c323657 (Bartek Przybylski 2012-10-27 12:20:52 +0200  35) 
6c323657 (Bartek Przybylski 2012-10-27 12:20:52 +0200  36) import eu.alefzero.webdav.WebdavClient;
6c323657 (Bartek Przybylski 2012-10-27 12:20:52 +0200  37) import eu.alefzero.webdav.WebdavEntry;
6c323657 (Bartek Przybylski 2012-10-27 12:20:52 +0200  38) import eu.alefzero.webdav.WebdavUtils;
6c323657 (Bartek Przybylski 2012-10-27 12:20:52 +0200  39) 
6c323657 (Bartek Przybylski 2012-10-27 12:20:52 +0200  40) public class SynchronizeFileOperation extends RemoteOperation {
6c323657 (Bartek Przybylski 2012-10-27 12:20:52 +0200  41) 
6c323657 (Bartek Przybylski 2012-10-27 12:20:52 +0200  42)     private String TAG = SynchronizeFileOperation.class.getSimpleName();
9c38fbae (David A. Velasco  2012-11-20 11:00:56 +0100  43)     private static final int SYNC_READ_TIMEOUT = 10000;
9c38fbae (David A. Velasco  2012-11-20 11:00:56 +0100  44)     private static final int SYNC_CONNECTION_TIMEOUT = 5000;
9c38fbae (David A. Velasco  2012-11-20 11:00:56 +0100  45)     
97dd5906 (David A. Velasco  2012-11-15 19:29:30 +0100  46)     private OCFile mLocalFile;
c301865c (David A. Velasco  2012-11-16 16:23:45 +0100  47)     private OCFile mServerFile;
6c323657 (Bartek Przybylski 2012-10-27 12:20:52 +0200  48)     private DataStorageManager mStorageManager;
6c323657 (Bartek Przybylski 2012-10-27 12:20:52 +0200  49)     private Account mAccount;
ff2271a8 (David A. Velasco  2012-11-15 17:18:04 +0100  50)     private boolean mSyncFileContents;
ff2271a8 (David A. Velasco  2012-11-15 17:18:04 +0100  51)     private boolean mLocalChangeAlreadyKnown;
ff2271a8 (David A. Velasco  2012-11-15 17:18:04 +0100  52)     private Context mContext;
ff2271a8 (David A. Velasco  2012-11-15 17:18:04 +0100  53)     
ff2271a8 (David A. Velasco  2012-11-15 17:18:04 +0100  54)     private boolean mTransferWasRequested = false;
6c323657 (Bartek Przybylski 2012-10-27 12:20:52 +0200  55)     
6c323657 (Bartek Przybylski 2012-10-27 12:20:52 +0200  56)     public SynchronizeFileOperation(
c301865c (David A. Velasco  2012-11-16 16:23:45 +0100  57)             OCFile localFile,
c301865c (David A. Velasco  2012-11-16 16:23:45 +0100  58)             OCFile serverFile,          // make this null to let the operation checks the server; added to reuse info from SynchronizeFolderOperation 
c301865c (David A. Velasco  2012-11-16 16:23:45 +0100  59)             DataStorageManager storageManager, 
ff2271a8 (David A. Velasco  2012-11-15 17:18:04 +0100  60)             Account account, 
ff2271a8 (David A. Velasco  2012-11-15 17:18:04 +0100  61)             boolean syncFileContents,
c301865c (David A. Velasco  2012-11-16 16:23:45 +0100  62)             boolean localChangeAlreadyKnown, 
ff2271a8 (David A. Velasco  2012-11-15 17:18:04 +0100  63)             Context context) {
ff2271a8 (David A. Velasco  2012-11-15 17:18:04 +0100  64)         
97dd5906 (David A. Velasco  2012-11-15 19:29:30 +0100  65)         mLocalFile = localFile;
c301865c (David A. Velasco  2012-11-16 16:23:45 +0100  66)         mServerFile = serverFile;
c301865c (David A. Velasco  2012-11-16 16:23:45 +0100  67)         mStorageManager = storageManager;
6c323657 (Bartek Przybylski 2012-10-27 12:20:52 +0200  68)         mAccount = account;
ff2271a8 (David A. Velasco  2012-11-15 17:18:04 +0100  69)         mSyncFileContents = syncFileContents;
ff2271a8 (David A. Velasco  2012-11-15 17:18:04 +0100  70)         mLocalChangeAlreadyKnown = localChangeAlreadyKnown;
ff2271a8 (David A. Velasco  2012-11-15 17:18:04 +0100  71)         mContext = context;
6c323657 (Bartek Przybylski 2012-10-27 12:20:52 +0200  72)     }
6c323657 (Bartek Przybylski 2012-10-27 12:20:52 +0200  73) 
c301865c (David A. Velasco  2012-11-16 16:23:45 +0100  74) 
6c323657 (Bartek Przybylski 2012-10-27 12:20:52 +0200  75)     @Override
6c323657 (Bartek Przybylski 2012-10-27 12:20:52 +0200  76)     protected RemoteOperationResult run(WebdavClient client) {
ff2271a8 (David A. Velasco  2012-11-15 17:18:04 +0100  77)         
6c323657 (Bartek Przybylski 2012-10-27 12:20:52 +0200  78)         PropFindMethod propfind = null;
6c323657 (Bartek Przybylski 2012-10-27 12:20:52 +0200  79)         RemoteOperationResult result = null;
ff2271a8 (David A. Velasco  2012-11-15 17:18:04 +0100  80)         mTransferWasRequested = false;
6c323657 (Bartek Przybylski 2012-10-27 12:20:52 +0200  81)         try {
97dd5906 (David A. Velasco  2012-11-15 19:29:30 +0100  82)             if (!mLocalFile.isDown()) {
ff2271a8 (David A. Velasco  2012-11-15 17:18:04 +0100  83)                 /// easy decision
97dd5906 (David A. Velasco  2012-11-15 19:29:30 +0100  84)                 requestForDownload(mLocalFile);
ff2271a8 (David A. Velasco  2012-11-15 17:18:04 +0100  85)                 result = new RemoteOperationResult(ResultCode.OK);
ff2271a8 (David A. Velasco  2012-11-15 17:18:04 +0100  86)                 
ff2271a8 (David A. Velasco  2012-11-15 17:18:04 +0100  87)             } else {
97dd5906 (David A. Velasco  2012-11-15 19:29:30 +0100  88)                 /// local copy in the device -> need to think a bit more before do anything
ff2271a8 (David A. Velasco  2012-11-15 17:18:04 +0100  89)                 
c301865c (David A. Velasco  2012-11-16 16:23:45 +0100  90)                 if (mServerFile == null) {
c301865c (David A. Velasco  2012-11-16 16:23:45 +0100  91)                     /// take the duty of check the server for the current state of the file there
c301865c (David A. Velasco  2012-11-16 16:23:45 +0100  92)                     propfind = new PropFindMethod(client.getBaseUri() + WebdavUtils.encodePath(mLocalFile.getRemotePath()));
9c38fbae (David A. Velasco  2012-11-20 11:00:56 +0100  93)                     int status = client.executeMethod(propfind, SYNC_READ_TIMEOUT, SYNC_CONNECTION_TIMEOUT);
c301865c (David A. Velasco  2012-11-16 16:23:45 +0100  94)                     boolean isMultiStatus = status == HttpStatus.SC_MULTI_STATUS;
c301865c (David A. Velasco  2012-11-16 16:23:45 +0100  95)                     if (isMultiStatus) {
c301865c (David A. Velasco  2012-11-16 16:23:45 +0100  96)                         MultiStatus resp = propfind.getResponseBodyAsMultiStatus();
c301865c (David A. Velasco  2012-11-16 16:23:45 +0100  97)                         WebdavEntry we = new WebdavEntry(resp.getResponses()[0],
6c323657 (Bartek Przybylski 2012-10-27 12:20:52 +0200  98)                                                client.getBaseUri().getPath());
c301865c (David A. Velasco  2012-11-16 16:23:45 +0100  99)                         mServerFile = fillOCFile(we);
22a789e8 (David A. Velasco  2012-11-19 15:08:48 +0100 100)                         mServerFile.setLastSyncDateForProperties(System.currentTimeMillis());
c301865c (David A. Velasco  2012-11-16 16:23:45 +0100 101)                         
c301865c (David A. Velasco  2012-11-16 16:23:45 +0100 102)                     } else {
c301865c (David A. Velasco  2012-11-16 16:23:45 +0100 103)                         client.exhaustResponse(propfind.getResponseBodyAsStream());
78bcf721 (David A. Velasco  2013-08-07 19:13:00 +0200 104)                         result = new RemoteOperationResult(false, status, propfind.getResponseHeaders());
c301865c (David A. Velasco  2012-11-16 16:23:45 +0100 105)                     }
c301865c (David A. Velasco  2012-11-16 16:23:45 +0100 106)                 }
c301865c (David A. Velasco  2012-11-16 16:23:45 +0100 107)                 
bdc0332c (David A. Velasco  2013-04-17 12:26:13 +0200 108)                 if (result == null) {   // true if the server was not checked. nothing was wrong with the remote request
ff2271a8 (David A. Velasco  2012-11-15 17:18:04 +0100 109)               
ff2271a8 (David A. Velasco  2012-11-15 17:18:04 +0100 110)                     /// check changes in server and local file
ff2271a8 (David A. Velasco  2012-11-15 17:18:04 +0100 111)                     boolean serverChanged = false;
c301865c (David A. Velasco  2012-11-16 16:23:45 +0100 112)                     if (mServerFile.getEtag() != null) {
c301865c (David A. Velasco  2012-11-16 16:23:45 +0100 113)                         serverChanged = (!mServerFile.getEtag().equals(mLocalFile.getEtag()));   // TODO could this be dangerous when the user upgrades the server from non-tagged to tagged?
ff2271a8 (David A. Velasco  2012-11-15 17:18:04 +0100 114)                     } else {
ff2271a8 (David A. Velasco  2012-11-15 17:18:04 +0100 115)                         // server without etags
abd5f515 (David A. Velasco  2012-12-05 16:14:01 +0100 116)                         serverChanged = (mServerFile.getModificationTimestamp() > mLocalFile.getModificationTimestampAtLastSyncForData());
ff2271a8 (David A. Velasco  2012-11-15 17:18:04 +0100 117)                     }
97dd5906 (David A. Velasco  2012-11-15 19:29:30 +0100 118)                     boolean localChanged = (mLocalChangeAlreadyKnown || mLocalFile.getLocalModificationTimestamp() > mLocalFile.getLastSyncDateForData());
2623e9c1 (David A. Velasco  2013-02-08 09:22:46 +0100 119)                         // TODO this will be always true after the app is upgraded to database version 2; will result in unnecessary uploads
ff2271a8 (David A. Velasco  2012-11-15 17:18:04 +0100 120)               
ff2271a8 (David A. Velasco  2012-11-15 17:18:04 +0100 121)                     /// decide action to perform depending upon changes
ff2271a8 (David A. Velasco  2012-11-15 17:18:04 +0100 122)                     if (localChanged && serverChanged) {
ff2271a8 (David A. Velasco  2012-11-15 17:18:04 +0100 123)                         result = new RemoteOperationResult(ResultCode.SYNC_CONFLICT);
ff2271a8 (David A. Velasco  2012-11-15 17:18:04 +0100 124)                   
ff2271a8 (David A. Velasco  2012-11-15 17:18:04 +0100 125)                     } else if (localChanged) {
ff2271a8 (David A. Velasco  2012-11-15 17:18:04 +0100 126)                         if (mSyncFileContents) {
97dd5906 (David A. Velasco  2012-11-15 19:29:30 +0100 127)                             requestForUpload(mLocalFile);
ff2271a8 (David A. Velasco  2012-11-15 17:18:04 +0100 128)                             // the local update of file properties will be done by the FileUploader service when the upload finishes
ff2271a8 (David A. Velasco  2012-11-15 17:18:04 +0100 129)                         } else {
ff2271a8 (David A. Velasco  2012-11-15 17:18:04 +0100 130)                             // NOTHING TO DO HERE: updating the properties of the file in the server without uploading the contents would be stupid; 
ff2271a8 (David A. Velasco  2012-11-15 17:18:04 +0100 131)                             // So, an instance of SynchronizeFileOperation created with syncFileContents == false is completely useless when we suspect
ff2271a8 (David A. Velasco  2012-11-15 17:18:04 +0100 132)                             // that an upload is necessary (for instance, in FileObserverService).
ff2271a8 (David A. Velasco  2012-11-15 17:18:04 +0100 133)                         }
ff2271a8 (David A. Velasco  2012-11-15 17:18:04 +0100 134)                         result = new RemoteOperationResult(ResultCode.OK);
ff2271a8 (David A. Velasco  2012-11-15 17:18:04 +0100 135)                   
ff2271a8 (David A. Velasco  2012-11-15 17:18:04 +0100 136)                     } else if (serverChanged) {
ff2271a8 (David A. Velasco  2012-11-15 17:18:04 +0100 137)                         if (mSyncFileContents) {
97dd5906 (David A. Velasco  2012-11-15 19:29:30 +0100 138)                             requestForDownload(mLocalFile); // local, not server; we won't to keep the value of keepInSync!
ff2271a8 (David A. Velasco  2012-11-15 17:18:04 +0100 139)                             // the update of local data will be done later by the FileUploader service when the upload finishes
ff2271a8 (David A. Velasco  2012-11-15 17:18:04 +0100 140)                         } else {
ff2271a8 (David A. Velasco  2012-11-15 17:18:04 +0100 141)                             // TODO CHECK: is this really useful in some point in the code?
c301865c (David A. Velasco  2012-11-16 16:23:45 +0100 142)                             mServerFile.setKeepInSync(mLocalFile.keepInSync());
22a789e8 (David A. Velasco  2012-11-19 15:08:48 +0100 143)                             mServerFile.setLastSyncDateForData(mLocalFile.getLastSyncDateForData());
22a789e8 (David A. Velasco  2012-11-19 15:08:48 +0100 144)                             mServerFile.setStoragePath(mLocalFile.getStoragePath());
c301865c (David A. Velasco  2012-11-16 16:23:45 +0100 145)                             mServerFile.setParentId(mLocalFile.getParentId());
c301865c (David A. Velasco  2012-11-16 16:23:45 +0100 146)                             mStorageManager.saveFile(mServerFile);
ff2271a8 (David A. Velasco  2012-11-15 17:18:04 +0100 147)                             
ff2271a8 (David A. Velasco  2012-11-15 17:18:04 +0100 148)                         }
ff2271a8 (David A. Velasco  2012-11-15 17:18:04 +0100 149)                         result = new RemoteOperationResult(ResultCode.OK);
6c323657 (Bartek Przybylski 2012-10-27 12:20:52 +0200 150)               
ff2271a8 (David A. Velasco  2012-11-15 17:18:04 +0100 151)                     } else {
ff2271a8 (David A. Velasco  2012-11-15 17:18:04 +0100 152)                         // nothing changed, nothing to do
ff2271a8 (David A. Velasco  2012-11-15 17:18:04 +0100 153)                         result = new RemoteOperationResult(ResultCode.OK);
ff2271a8 (David A. Velasco  2012-11-15 17:18:04 +0100 154)                     }
ff2271a8 (David A. Velasco  2012-11-15 17:18:04 +0100 155)               
c301865c (David A. Velasco  2012-11-16 16:23:45 +0100 156)                 } 
ff2271a8 (David A. Velasco  2012-11-15 17:18:04 +0100 157)           
ff2271a8 (David A. Velasco  2012-11-15 17:18:04 +0100 158)             }
ff2271a8 (David A. Velasco  2012-11-15 17:18:04 +0100 159)             
8e36e7cc (zerginator        2013-03-12 07:56:27 +0100 160)             Log_OC.i(TAG, "Synchronizing " + mAccount.name + ", file " + mLocalFile.getRemotePath() + ": " + result.getLogMessage());
6c323657 (Bartek Przybylski 2012-10-27 12:20:52 +0200 161)           
6c323657 (Bartek Przybylski 2012-10-27 12:20:52 +0200 162)         } catch (Exception e) {
6c323657 (Bartek Przybylski 2012-10-27 12:20:52 +0200 163)             result = new RemoteOperationResult(e);
c3ca5b5a (David A. Velasco  2013-04-15 13:12:04 +0200 164)             Log_OC.e(TAG, "Synchronizing " + mAccount.name + ", file " + (mLocalFile != null ? mLocalFile.getRemotePath() : "NULL") + ": " + result.getLogMessage(), result.getException());
6c323657 (Bartek Przybylski 2012-10-27 12:20:52 +0200 165) 
6c323657 (Bartek Przybylski 2012-10-27 12:20:52 +0200 166)         } finally {
6c323657 (Bartek Przybylski 2012-10-27 12:20:52 +0200 167)             if (propfind != null)
6c323657 (Bartek Przybylski 2012-10-27 12:20:52 +0200 168)                 propfind.releaseConnection();
6c323657 (Bartek Przybylski 2012-10-27 12:20:52 +0200 169)         }
6c323657 (Bartek Przybylski 2012-10-27 12:20:52 +0200 170)         return result;
6c323657 (Bartek Przybylski 2012-10-27 12:20:52 +0200 171)     }
ff2271a8 (David A. Velasco  2012-11-15 17:18:04 +0100 172) 
6c323657 (Bartek Przybylski 2012-10-27 12:20:52 +0200 173)     
6c323657 (Bartek Przybylski 2012-10-27 12:20:52 +0200 174)     /**
ff2271a8 (David A. Velasco  2012-11-15 17:18:04 +0100 175)      * Requests for an upload to the FileUploader service
ff2271a8 (David A. Velasco  2012-11-15 17:18:04 +0100 176)      * 
97dd5906 (David A. Velasco  2012-11-15 19:29:30 +0100 177)      * @param file     OCFile object representing the file to upload
ff2271a8 (David A. Velasco  2012-11-15 17:18:04 +0100 178)      */
97dd5906 (David A. Velasco  2012-11-15 19:29:30 +0100 179)     private void requestForUpload(OCFile file) {
ff2271a8 (David A. Velasco  2012-11-15 17:18:04 +0100 180)         Intent i = new Intent(mContext, FileUploader.class);
ff2271a8 (David A. Velasco  2012-11-15 17:18:04 +0100 181)         i.putExtra(FileUploader.KEY_ACCOUNT, mAccount);
97dd5906 (David A. Velasco  2012-11-15 19:29:30 +0100 182)         i.putExtra(FileUploader.KEY_FILE, file);
97dd5906 (David A. Velasco  2012-11-15 19:29:30 +0100 183)         /*i.putExtra(FileUploader.KEY_REMOTE_FILE, mRemotePath);    // doing this we would lose the value of keepInSync in the road, and maybe it's not updated in the database when the FileUploader service gets it!  
97dd5906 (David A. Velasco  2012-11-15 19:29:30 +0100 184)         i.putExtra(FileUploader.KEY_LOCAL_FILE, localFile.getStoragePath());*/
ff2271a8 (David A. Velasco  2012-11-15 17:18:04 +0100 185)         i.putExtra(FileUploader.KEY_UPLOAD_TYPE, FileUploader.UPLOAD_SINGLE_FILE);
ff2271a8 (David A. Velasco  2012-11-15 17:18:04 +0100 186)         i.putExtra(FileUploader.KEY_FORCE_OVERWRITE, true);
ff2271a8 (David A. Velasco  2012-11-15 17:18:04 +0100 187)         mContext.startService(i);
ff2271a8 (David A. Velasco  2012-11-15 17:18:04 +0100 188)         mTransferWasRequested = true;
ff2271a8 (David A. Velasco  2012-11-15 17:18:04 +0100 189)     }
ff2271a8 (David A. Velasco  2012-11-15 17:18:04 +0100 190) 
ff2271a8 (David A. Velasco  2012-11-15 17:18:04 +0100 191) 
ff2271a8 (David A. Velasco  2012-11-15 17:18:04 +0100 192)     /**
ff2271a8 (David A. Velasco  2012-11-15 17:18:04 +0100 193)      * Requests for a download to the FileDownloader service
ff2271a8 (David A. Velasco  2012-11-15 17:18:04 +0100 194)      * 
ff2271a8 (David A. Velasco  2012-11-15 17:18:04 +0100 195)      * @param file     OCFile object representing the file to download
ff2271a8 (David A. Velasco  2012-11-15 17:18:04 +0100 196)      */
ff2271a8 (David A. Velasco  2012-11-15 17:18:04 +0100 197)     private void requestForDownload(OCFile file) {
ff2271a8 (David A. Velasco  2012-11-15 17:18:04 +0100 198)         Intent i = new Intent(mContext, FileDownloader.class);
ff2271a8 (David A. Velasco  2012-11-15 17:18:04 +0100 199)         i.putExtra(FileDownloader.EXTRA_ACCOUNT, mAccount);
ff2271a8 (David A. Velasco  2012-11-15 17:18:04 +0100 200)         i.putExtra(FileDownloader.EXTRA_FILE, file);
ff2271a8 (David A. Velasco  2012-11-15 17:18:04 +0100 201)         mContext.startService(i);
ff2271a8 (David A. Velasco  2012-11-15 17:18:04 +0100 202)         mTransferWasRequested = true;
ff2271a8 (David A. Velasco  2012-11-15 17:18:04 +0100 203)     }
ff2271a8 (David A. Velasco  2012-11-15 17:18:04 +0100 204) 
ff2271a8 (David A. Velasco  2012-11-15 17:18:04 +0100 205) 
ff2271a8 (David A. Velasco  2012-11-15 17:18:04 +0100 206)     /**
6c323657 (Bartek Przybylski 2012-10-27 12:20:52 +0200 207)      * Creates and populates a new {@link OCFile} object with the data read from the server.
6c323657 (Bartek Przybylski 2012-10-27 12:20:52 +0200 208)      * 
6c323657 (Bartek Przybylski 2012-10-27 12:20:52 +0200 209)      * @param we        WebDAV entry read from the server for a WebDAV resource (remote file or folder).
6c323657 (Bartek Przybylski 2012-10-27 12:20:52 +0200 210)      * @return          New OCFile instance representing the remote resource described by we.
6c323657 (Bartek Przybylski 2012-10-27 12:20:52 +0200 211)      */
6c323657 (Bartek Przybylski 2012-10-27 12:20:52 +0200 212)     private OCFile fillOCFile(WebdavEntry we) {
6c323657 (Bartek Przybylski 2012-10-27 12:20:52 +0200 213)         OCFile file = new OCFile(we.decodedPath());
6c323657 (Bartek Przybylski 2012-10-27 12:20:52 +0200 214)         file.setCreationTimestamp(we.createTimestamp());
6c323657 (Bartek Przybylski 2012-10-27 12:20:52 +0200 215)         file.setFileLength(we.contentLength());
6c323657 (Bartek Przybylski 2012-10-27 12:20:52 +0200 216)         file.setMimetype(we.contentType());
abd5f515 (David A. Velasco  2012-12-05 16:14:01 +0100 217)         file.setModificationTimestamp(we.modifiedTimestamp());
6c323657 (Bartek Przybylski 2012-10-27 12:20:52 +0200 218)         return file;
6c323657 (Bartek Przybylski 2012-10-27 12:20:52 +0200 219)     }
6c323657 (Bartek Przybylski 2012-10-27 12:20:52 +0200 220) 
ff2271a8 (David A. Velasco  2012-11-15 17:18:04 +0100 221) 
ff2271a8 (David A. Velasco  2012-11-15 17:18:04 +0100 222)     public boolean transferWasRequested() {
ff2271a8 (David A. Velasco  2012-11-15 17:18:04 +0100 223)         return mTransferWasRequested;
ff2271a8 (David A. Velasco  2012-11-15 17:18:04 +0100 224)     }
ff2271a8 (David A. Velasco  2012-11-15 17:18:04 +0100 225) 
ba52dfdf (David A. Velasco  2012-11-23 14:30:36 +0100 226) 
ba52dfdf (David A. Velasco  2012-11-23 14:30:36 +0100 227)     public OCFile getLocalFile() {
ba52dfdf (David A. Velasco  2012-11-23 14:30:36 +0100 228)         return mLocalFile;
ba52dfdf (David A. Velasco  2012-11-23 14:30:36 +0100 229)     }
ba52dfdf (David A. Velasco  2012-11-23 14:30:36 +0100 230) 
6c323657 (Bartek Przybylski 2012-10-27 12:20:52 +0200 231) }
