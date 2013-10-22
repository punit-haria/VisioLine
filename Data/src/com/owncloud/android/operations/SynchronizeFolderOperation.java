67eb9210 (David A. Velasco 2012-10-22 15:11:59 +0200   1) /* ownCloud Android client application
bb257ec7 (David A. Velasco 2013-02-07 18:45:10 +0100   2)  *   Copyright (C) 2012-2013 ownCloud Inc.
67eb9210 (David A. Velasco 2012-10-22 15:11:59 +0200   3)  *
67eb9210 (David A. Velasco 2012-10-22 15:11:59 +0200   4)  *   This program is free software: you can redistribute it and/or modify
bdc0332c (David A. Velasco 2013-04-17 12:26:13 +0200   5)  *   it under the terms of the GNU General Public License version 2,
bdc0332c (David A. Velasco 2013-04-17 12:26:13 +0200   6)  *   as published by the Free Software Foundation.
67eb9210 (David A. Velasco 2012-10-22 15:11:59 +0200   7)  *
67eb9210 (David A. Velasco 2012-10-22 15:11:59 +0200   8)  *   This program is distributed in the hope that it will be useful,
67eb9210 (David A. Velasco 2012-10-22 15:11:59 +0200   9)  *   but WITHOUT ANY WARRANTY; without even the implied warranty of
67eb9210 (David A. Velasco 2012-10-22 15:11:59 +0200  10)  *   MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
67eb9210 (David A. Velasco 2012-10-22 15:11:59 +0200  11)  *   GNU General Public License for more details.
67eb9210 (David A. Velasco 2012-10-22 15:11:59 +0200  12)  *
67eb9210 (David A. Velasco 2012-10-22 15:11:59 +0200  13)  *   You should have received a copy of the GNU General Public License
67eb9210 (David A. Velasco 2012-10-22 15:11:59 +0200  14)  *   along with this program.  If not, see <http://www.gnu.org/licenses/>.
67eb9210 (David A. Velasco 2012-10-22 15:11:59 +0200  15)  *
67eb9210 (David A. Velasco 2012-10-22 15:11:59 +0200  16)  */
67eb9210 (David A. Velasco 2012-10-22 15:11:59 +0200  17) 
67eb9210 (David A. Velasco 2012-10-22 15:11:59 +0200  18) package com.owncloud.android.operations;
67eb9210 (David A. Velasco 2012-10-22 15:11:59 +0200  19) 
22a789e8 (David A. Velasco 2012-11-19 15:08:48 +0100  20) import java.io.File;
2a913bfb (David A. Velasco 2012-11-30 11:29:18 +0100  21) import java.io.FileInputStream;
2a913bfb (David A. Velasco 2012-11-30 11:29:18 +0100  22) import java.io.FileOutputStream;
4476c047 (David A. Velasco 2012-12-05 16:01:19 +0100  23) import java.io.IOException;
2a913bfb (David A. Velasco 2012-11-30 11:29:18 +0100  24) import java.io.InputStream;
2a913bfb (David A. Velasco 2012-11-30 11:29:18 +0100  25) import java.io.OutputStream;
2a913bfb (David A. Velasco 2012-11-30 11:29:18 +0100  26) import java.util.HashMap;
67eb9210 (David A. Velasco 2012-10-22 15:11:59 +0200  27) import java.util.List;
2a913bfb (David A. Velasco 2012-11-30 11:29:18 +0100  28) import java.util.Map;
67eb9210 (David A. Velasco 2012-10-22 15:11:59 +0200  29) import java.util.Vector;
67eb9210 (David A. Velasco 2012-10-22 15:11:59 +0200  30) 
b6213a0d (David A. Velasco 2013-08-22 11:48:57 +0200  31) import org.apache.commons.httpclient.Header;
67eb9210 (David A. Velasco 2012-10-22 15:11:59 +0200  32) import org.apache.http.HttpStatus;
67eb9210 (David A. Velasco 2012-10-22 15:11:59 +0200  33) import org.apache.jackrabbit.webdav.MultiStatus;
67eb9210 (David A. Velasco 2012-10-22 15:11:59 +0200  34) import org.apache.jackrabbit.webdav.client.methods.PropFindMethod;
67eb9210 (David A. Velasco 2012-10-22 15:11:59 +0200  35) 
67eb9210 (David A. Velasco 2012-10-22 15:11:59 +0200  36) import android.accounts.Account;
6e469559 (David A. Velasco 2012-10-23 12:47:29 +0200  37) import android.content.Context;
67eb9210 (David A. Velasco 2012-10-22 15:11:59 +0200  38) 
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100  39) import com.owncloud.android.Log_OC;
6e469559 (David A. Velasco 2012-10-23 12:47:29 +0200  40) import com.owncloud.android.datamodel.DataStorageManager;
67eb9210 (David A. Velasco 2012-10-22 15:11:59 +0200  41) import com.owncloud.android.datamodel.OCFile;
c301865c (David A. Velasco 2012-11-16 16:23:45 +0100  42) import com.owncloud.android.operations.RemoteOperationResult.ResultCode;
9aab2d26 (David A. Velasco 2012-11-12 15:20:00 +0100  43) import com.owncloud.android.utils.FileStorageUtils;
67eb9210 (David A. Velasco 2012-10-22 15:11:59 +0200  44) 
67eb9210 (David A. Velasco 2012-10-22 15:11:59 +0200  45) import eu.alefzero.webdav.WebdavClient;
67eb9210 (David A. Velasco 2012-10-22 15:11:59 +0200  46) import eu.alefzero.webdav.WebdavEntry;
67eb9210 (David A. Velasco 2012-10-22 15:11:59 +0200  47) import eu.alefzero.webdav.WebdavUtils;
67eb9210 (David A. Velasco 2012-10-22 15:11:59 +0200  48) 
67eb9210 (David A. Velasco 2012-10-22 15:11:59 +0200  49) 
67eb9210 (David A. Velasco 2012-10-22 15:11:59 +0200  50) /**
67eb9210 (David A. Velasco 2012-10-22 15:11:59 +0200  51)  * Remote operation performing the synchronization a the contents of a remote folder with the local database
67eb9210 (David A. Velasco 2012-10-22 15:11:59 +0200  52)  * 
67eb9210 (David A. Velasco 2012-10-22 15:11:59 +0200  53)  * @author David A. Velasco
67eb9210 (David A. Velasco 2012-10-22 15:11:59 +0200  54)  */
67eb9210 (David A. Velasco 2012-10-22 15:11:59 +0200  55) public class SynchronizeFolderOperation extends RemoteOperation {
67eb9210 (David A. Velasco 2012-10-22 15:11:59 +0200  56) 
c6a3abf0 (David A. Velasco 2012-10-26 13:45:59 +0200  57)     private static final String TAG = SynchronizeFolderOperation.class.getSimpleName();
67eb9210 (David A. Velasco 2012-10-22 15:11:59 +0200  58) 
67eb9210 (David A. Velasco 2012-10-22 15:11:59 +0200  59)     /** Remote folder to synchronize */
67eb9210 (David A. Velasco 2012-10-22 15:11:59 +0200  60)     private String mRemotePath;
67eb9210 (David A. Velasco 2012-10-22 15:11:59 +0200  61)     
67eb9210 (David A. Velasco 2012-10-22 15:11:59 +0200  62)     /** Timestamp for the synchronization in progress */
67eb9210 (David A. Velasco 2012-10-22 15:11:59 +0200  63)     private long mCurrentSyncTime;
67eb9210 (David A. Velasco 2012-10-22 15:11:59 +0200  64)     
67eb9210 (David A. Velasco 2012-10-22 15:11:59 +0200  65)     /** Id of the folder to synchronize in the local database */
67eb9210 (David A. Velasco 2012-10-22 15:11:59 +0200  66)     private long mParentId;
67eb9210 (David A. Velasco 2012-10-22 15:11:59 +0200  67)     
67eb9210 (David A. Velasco 2012-10-22 15:11:59 +0200  68)     /** Access to the local database */
6e469559 (David A. Velasco 2012-10-23 12:47:29 +0200  69)     private DataStorageManager mStorageManager;
67eb9210 (David A. Velasco 2012-10-22 15:11:59 +0200  70)     
67eb9210 (David A. Velasco 2012-10-22 15:11:59 +0200  71)     /** Account where the file to synchronize belongs */
67eb9210 (David A. Velasco 2012-10-22 15:11:59 +0200  72)     private Account mAccount;
67eb9210 (David A. Velasco 2012-10-22 15:11:59 +0200  73)     
6e469559 (David A. Velasco 2012-10-23 12:47:29 +0200  74)     /** Android context; necessary to send requests to the download service; maybe something to refactor */
6e469559 (David A. Velasco 2012-10-23 12:47:29 +0200  75)     private Context mContext;
67eb9210 (David A. Velasco 2012-10-22 15:11:59 +0200  76)     
6e469559 (David A. Velasco 2012-10-23 12:47:29 +0200  77)     /** Files and folders contained in the synchronized folder */
6e469559 (David A. Velasco 2012-10-23 12:47:29 +0200  78)     private List<OCFile> mChildren;
c301865c (David A. Velasco 2012-11-16 16:23:45 +0100  79) 
c301865c (David A. Velasco 2012-11-16 16:23:45 +0100  80)     private int mConflictsFound;
c301865c (David A. Velasco 2012-11-16 16:23:45 +0100  81) 
c301865c (David A. Velasco 2012-11-16 16:23:45 +0100  82)     private int mFailsInFavouritesFound;
2a913bfb (David A. Velasco 2012-11-30 11:29:18 +0100  83) 
2a913bfb (David A. Velasco 2012-11-30 11:29:18 +0100  84)     private Map<String, String> mForgottenLocalFiles;
6e469559 (David A. Velasco 2012-10-23 12:47:29 +0200  85)     
6e469559 (David A. Velasco 2012-10-23 12:47:29 +0200  86)     
6e469559 (David A. Velasco 2012-10-23 12:47:29 +0200  87)     public SynchronizeFolderOperation(  String remotePath, 
6e469559 (David A. Velasco 2012-10-23 12:47:29 +0200  88)                                         long currentSyncTime, 
6e469559 (David A. Velasco 2012-10-23 12:47:29 +0200  89)                                         long parentId, 
6e469559 (David A. Velasco 2012-10-23 12:47:29 +0200  90)                                         DataStorageManager dataStorageManager, 
6e469559 (David A. Velasco 2012-10-23 12:47:29 +0200  91)                                         Account account, 
6e469559 (David A. Velasco 2012-10-23 12:47:29 +0200  92)                                         Context context ) {
67eb9210 (David A. Velasco 2012-10-22 15:11:59 +0200  93)         mRemotePath = remotePath;
67eb9210 (David A. Velasco 2012-10-22 15:11:59 +0200  94)         mCurrentSyncTime = currentSyncTime;
67eb9210 (David A. Velasco 2012-10-22 15:11:59 +0200  95)         mParentId = parentId;
6e469559 (David A. Velasco 2012-10-23 12:47:29 +0200  96)         mStorageManager = dataStorageManager;
67eb9210 (David A. Velasco 2012-10-22 15:11:59 +0200  97)         mAccount = account;
6e469559 (David A. Velasco 2012-10-23 12:47:29 +0200  98)         mContext = context;
2a913bfb (David A. Velasco 2012-11-30 11:29:18 +0100  99)         mForgottenLocalFiles = new HashMap<String, String>();
6e469559 (David A. Velasco 2012-10-23 12:47:29 +0200 100)     }
6e469559 (David A. Velasco 2012-10-23 12:47:29 +0200 101)     
6e469559 (David A. Velasco 2012-10-23 12:47:29 +0200 102)     
c301865c (David A. Velasco 2012-11-16 16:23:45 +0100 103)     public int getConflictsFound() {
c301865c (David A. Velasco 2012-11-16 16:23:45 +0100 104)         return mConflictsFound;
c301865c (David A. Velasco 2012-11-16 16:23:45 +0100 105)     }
c301865c (David A. Velasco 2012-11-16 16:23:45 +0100 106)     
c301865c (David A. Velasco 2012-11-16 16:23:45 +0100 107)     public int getFailsInFavouritesFound() {
c301865c (David A. Velasco 2012-11-16 16:23:45 +0100 108)         return mFailsInFavouritesFound;
c301865c (David A. Velasco 2012-11-16 16:23:45 +0100 109)     }
c301865c (David A. Velasco 2012-11-16 16:23:45 +0100 110)     
2a913bfb (David A. Velasco 2012-11-30 11:29:18 +0100 111)     public Map<String, String> getForgottenLocalFiles() {
2a913bfb (David A. Velasco 2012-11-30 11:29:18 +0100 112)         return mForgottenLocalFiles;
2a913bfb (David A. Velasco 2012-11-30 11:29:18 +0100 113)     }
2a913bfb (David A. Velasco 2012-11-30 11:29:18 +0100 114)     
6e469559 (David A. Velasco 2012-10-23 12:47:29 +0200 115)     /**
6e469559 (David A. Velasco 2012-10-23 12:47:29 +0200 116)      * Returns the list of files and folders contained in the synchronized folder, if called after synchronization is complete.
6e469559 (David A. Velasco 2012-10-23 12:47:29 +0200 117)      * 
6e469559 (David A. Velasco 2012-10-23 12:47:29 +0200 118)      * @return      List of files and folders contained in the synchronized folder.
6e469559 (David A. Velasco 2012-10-23 12:47:29 +0200 119)      */
6e469559 (David A. Velasco 2012-10-23 12:47:29 +0200 120)     public List<OCFile> getChildren() {
6e469559 (David A. Velasco 2012-10-23 12:47:29 +0200 121)         return mChildren;
67eb9210 (David A. Velasco 2012-10-22 15:11:59 +0200 122)     }
67eb9210 (David A. Velasco 2012-10-22 15:11:59 +0200 123)     
67eb9210 (David A. Velasco 2012-10-22 15:11:59 +0200 124)     
67eb9210 (David A. Velasco 2012-10-22 15:11:59 +0200 125)     @Override
67eb9210 (David A. Velasco 2012-10-22 15:11:59 +0200 126)     protected RemoteOperationResult run(WebdavClient client) {
67eb9210 (David A. Velasco 2012-10-22 15:11:59 +0200 127)         RemoteOperationResult result = null;
c301865c (David A. Velasco 2012-11-16 16:23:45 +0100 128)         mFailsInFavouritesFound = 0;
c301865c (David A. Velasco 2012-11-16 16:23:45 +0100 129)         mConflictsFound = 0;
2a913bfb (David A. Velasco 2012-11-30 11:29:18 +0100 130)         mForgottenLocalFiles.clear();
67eb9210 (David A. Velasco 2012-10-22 15:11:59 +0200 131)         
67eb9210 (David A. Velasco 2012-10-22 15:11:59 +0200 132)         // code before in FileSyncAdapter.fetchData
67eb9210 (David A. Velasco 2012-10-22 15:11:59 +0200 133)         PropFindMethod query = null;
67eb9210 (David A. Velasco 2012-10-22 15:11:59 +0200 134)         try {
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100 135)             Log_OC.d(TAG, "Synchronizing " + mAccount.name + ", fetching files in " + mRemotePath);
67eb9210 (David A. Velasco 2012-10-22 15:11:59 +0200 136)             
67eb9210 (David A. Velasco 2012-10-22 15:11:59 +0200 137)             // remote request 
67eb9210 (David A. Velasco 2012-10-22 15:11:59 +0200 138)             query = new PropFindMethod(client.getBaseUri() + WebdavUtils.encodePath(mRemotePath));
67eb9210 (David A. Velasco 2012-10-22 15:11:59 +0200 139)             int status = client.executeMethod(query);
67eb9210 (David A. Velasco 2012-10-22 15:11:59 +0200 140)             
6e469559 (David A. Velasco 2012-10-23 12:47:29 +0200 141)             // check and process response   - /// TODO take into account all the possible status per child-resource
1192e036 (David A. Velasco 2012-10-22 15:38:37 +0200 142)             if (isMultiStatus(status)) { 
67eb9210 (David A. Velasco 2012-10-22 15:11:59 +0200 143)                 MultiStatus resp = query.getResponseBodyAsMultiStatus();
67eb9210 (David A. Velasco 2012-10-22 15:11:59 +0200 144)             
6e469559 (David A. Velasco 2012-10-23 12:47:29 +0200 145)                 // synchronize properties of the parent folder, if necessary
6e469559 (David A. Velasco 2012-10-23 12:47:29 +0200 146)                 if (mParentId == DataStorageManager.ROOT_PARENT_ID) {
6e469559 (David A. Velasco 2012-10-23 12:47:29 +0200 147)                     WebdavEntry we = new WebdavEntry(resp.getResponses()[0], client.getBaseUri().getPath());
6e469559 (David A. Velasco 2012-10-23 12:47:29 +0200 148)                     OCFile parent = fillOCFile(we);
6e469559 (David A. Velasco 2012-10-23 12:47:29 +0200 149)                     mStorageManager.saveFile(parent);
6e469559 (David A. Velasco 2012-10-23 12:47:29 +0200 150)                     mParentId = parent.getFileId();
6e469559 (David A. Velasco 2012-10-23 12:47:29 +0200 151)                 }
6e469559 (David A. Velasco 2012-10-23 12:47:29 +0200 152)                 
1192e036 (David A. Velasco 2012-10-22 15:38:37 +0200 153)                 // read contents in folder
67eb9210 (David A. Velasco 2012-10-22 15:11:59 +0200 154)                 List<OCFile> updatedFiles = new Vector<OCFile>(resp.getResponses().length - 1);
c301865c (David A. Velasco 2012-11-16 16:23:45 +0100 155)                 List<SynchronizeFileOperation> filesToSyncContents = new Vector<SynchronizeFileOperation>();
67eb9210 (David A. Velasco 2012-10-22 15:11:59 +0200 156)                 for (int i = 1; i < resp.getResponses().length; ++i) {
22a789e8 (David A. Velasco 2012-11-19 15:08:48 +0100 157)                     /// new OCFile instance with the data from the server
67eb9210 (David A. Velasco 2012-10-22 15:11:59 +0200 158)                     WebdavEntry we = new WebdavEntry(resp.getResponses()[i], client.getBaseUri().getPath());
67eb9210 (David A. Velasco 2012-10-22 15:11:59 +0200 159)                     OCFile file = fillOCFile(we);
22a789e8 (David A. Velasco 2012-11-19 15:08:48 +0100 160)                     
22a789e8 (David A. Velasco 2012-11-19 15:08:48 +0100 161)                     /// set data about local state, keeping unchanged former data if existing
22a789e8 (David A. Velasco 2012-11-19 15:08:48 +0100 162)                     file.setLastSyncDateForProperties(mCurrentSyncTime);
67eb9210 (David A. Velasco 2012-10-22 15:11:59 +0200 163)                     OCFile oldFile = mStorageManager.getFileByPath(file.getRemotePath());
67eb9210 (David A. Velasco 2012-10-22 15:11:59 +0200 164)                     if (oldFile != null) {
67eb9210 (David A. Velasco 2012-10-22 15:11:59 +0200 165)                         file.setKeepInSync(oldFile.keepInSync());
e83a73cb (David A. Velasco 2012-12-10 14:01:45 +0100 166)                         file.setLastSyncDateForData(oldFile.getLastSyncDateForData());
e83a73cb (David A. Velasco 2012-12-10 14:01:45 +0100 167)                         file.setModificationTimestampAtLastSyncForData(oldFile.getModificationTimestampAtLastSyncForData());    // must be kept unchanged when the file contents are not updated
2a913bfb (David A. Velasco 2012-11-30 11:29:18 +0100 168)                         checkAndFixForeignStoragePath(oldFile);
22a789e8 (David A. Velasco 2012-11-19 15:08:48 +0100 169)                         file.setStoragePath(oldFile.getStoragePath());
22a789e8 (David A. Velasco 2012-11-19 15:08:48 +0100 170)                     }
22a789e8 (David A. Velasco 2012-11-19 15:08:48 +0100 171) 
22a789e8 (David A. Velasco 2012-11-19 15:08:48 +0100 172)                     /// scan default location if local copy of file is not linked in OCFile instance
22a789e8 (David A. Velasco 2012-11-19 15:08:48 +0100 173)                     if (file.getStoragePath() == null && !file.isDirectory()) {
22a789e8 (David A. Velasco 2012-11-19 15:08:48 +0100 174)                         File f = new File(FileStorageUtils.getDefaultSavePathFor(mAccount.name, file));
22a789e8 (David A. Velasco 2012-11-19 15:08:48 +0100 175)                         if (f.exists()) {
22a789e8 (David A. Velasco 2012-11-19 15:08:48 +0100 176)                             file.setStoragePath(f.getAbsolutePath());
22a789e8 (David A. Velasco 2012-11-19 15:08:48 +0100 177)                             file.setLastSyncDateForData(f.lastModified());
c301865c (David A. Velasco 2012-11-16 16:23:45 +0100 178)                         }
67eb9210 (David A. Velasco 2012-10-22 15:11:59 +0200 179)                     }
22a789e8 (David A. Velasco 2012-11-19 15:08:48 +0100 180)                     
22a789e8 (David A. Velasco 2012-11-19 15:08:48 +0100 181)                     /// prepare content synchronization for kept-in-sync files
22a789e8 (David A. Velasco 2012-11-19 15:08:48 +0100 182)                     if (file.keepInSync()) {
22a789e8 (David A. Velasco 2012-11-19 15:08:48 +0100 183)                         SynchronizeFileOperation operation = new SynchronizeFileOperation(  oldFile,        
22a789e8 (David A. Velasco 2012-11-19 15:08:48 +0100 184)                                                                                             file, 
22a789e8 (David A. Velasco 2012-11-19 15:08:48 +0100 185)                                                                                             mStorageManager,
22a789e8 (David A. Velasco 2012-11-19 15:08:48 +0100 186)                                                                                             mAccount,       
22a789e8 (David A. Velasco 2012-11-19 15:08:48 +0100 187)                                                                                             true, 
22a789e8 (David A. Velasco 2012-11-19 15:08:48 +0100 188)                                                                                             false,          
22a789e8 (David A. Velasco 2012-11-19 15:08:48 +0100 189)                                                                                             mContext
22a789e8 (David A. Velasco 2012-11-19 15:08:48 +0100 190)                                                                                             );
22a789e8 (David A. Velasco 2012-11-19 15:08:48 +0100 191)                         filesToSyncContents.add(operation);
22a789e8 (David A. Velasco 2012-11-19 15:08:48 +0100 192)                     }
67eb9210 (David A. Velasco 2012-10-22 15:11:59 +0200 193)                 
67eb9210 (David A. Velasco 2012-10-22 15:11:59 +0200 194)                     updatedFiles.add(file);
67eb9210 (David A. Velasco 2012-10-22 15:11:59 +0200 195)                 }
d5a327c3 (David A. Velasco 2012-10-25 10:41:24 +0200 196)                                 
1192e036 (David A. Velasco 2012-10-22 15:38:37 +0200 197)                 // save updated contents in local database; all at once, trying to get a best performance in database update (not a big deal, indeed)
67eb9210 (David A. Velasco 2012-10-22 15:11:59 +0200 198)                 mStorageManager.saveFiles(updatedFiles);
67eb9210 (David A. Velasco 2012-10-22 15:11:59 +0200 199)                 
c301865c (David A. Velasco 2012-11-16 16:23:45 +0100 200)                 // request for the synchronization of files AFTER saving last properties
c301865c (David A. Velasco 2012-11-16 16:23:45 +0100 201)                 SynchronizeFileOperation op = null;
c301865c (David A. Velasco 2012-11-16 16:23:45 +0100 202)                 RemoteOperationResult contentsResult = null;
c301865c (David A. Velasco 2012-11-16 16:23:45 +0100 203)                 for (int i=0; i < filesToSyncContents.size(); i++) {
c301865c (David A. Velasco 2012-11-16 16:23:45 +0100 204)                     op = filesToSyncContents.get(i);
c301865c (David A. Velasco 2012-11-16 16:23:45 +0100 205)                     contentsResult = op.execute(client);   // returns without waiting for upload or download finishes
c301865c (David A. Velasco 2012-11-16 16:23:45 +0100 206)                     if (!contentsResult.isSuccess()) {
c301865c (David A. Velasco 2012-11-16 16:23:45 +0100 207)                         if (contentsResult.getCode() == ResultCode.SYNC_CONFLICT) {
c301865c (David A. Velasco 2012-11-16 16:23:45 +0100 208)                             mConflictsFound++;
c301865c (David A. Velasco 2012-11-16 16:23:45 +0100 209)                         } else {
c301865c (David A. Velasco 2012-11-16 16:23:45 +0100 210)                             mFailsInFavouritesFound++;
c301865c (David A. Velasco 2012-11-16 16:23:45 +0100 211)                             if (contentsResult.getException() != null) {
52bc433b (David A. Velasco 2013-08-01 17:47:09 +0200 212)                                 Log_OC.e(TAG, "Error while synchronizing favourites : " +  contentsResult.getLogMessage(), contentsResult.getException());
c301865c (David A. Velasco 2012-11-16 16:23:45 +0100 213)                             } else {
52bc433b (David A. Velasco 2013-08-01 17:47:09 +0200 214)                                 Log_OC.e(TAG, "Error while synchronizing favourites : " + contentsResult.getLogMessage());
c301865c (David A. Velasco 2012-11-16 16:23:45 +0100 215)                             }
c301865c (David A. Velasco 2012-11-16 16:23:45 +0100 216)                         }
c301865c (David A. Velasco 2012-11-16 16:23:45 +0100 217)                     }   // won't let these fails break the synchronization process
c301865c (David A. Velasco 2012-11-16 16:23:45 +0100 218)                 }
c301865c (David A. Velasco 2012-11-16 16:23:45 +0100 219) 
c301865c (David A. Velasco 2012-11-16 16:23:45 +0100 220)                     
67eb9210 (David A. Velasco 2012-10-22 15:11:59 +0200 221)                 // removal of obsolete files
6e469559 (David A. Velasco 2012-10-23 12:47:29 +0200 222)                 mChildren = mStorageManager.getDirectoryContent(mStorageManager.getFileById(mParentId));
67eb9210 (David A. Velasco 2012-10-22 15:11:59 +0200 223)                 OCFile file;
9aab2d26 (David A. Velasco 2012-11-12 15:20:00 +0100 224)                 String currentSavePath = FileStorageUtils.getSavePath(mAccount.name);
6e469559 (David A. Velasco 2012-10-23 12:47:29 +0200 225)                 for (int i=0; i < mChildren.size(); ) {
6e469559 (David A. Velasco 2012-10-23 12:47:29 +0200 226)                     file = mChildren.get(i);
ff2271a8 (David A. Velasco 2012-11-15 17:18:04 +0100 227)                     if (file.getLastSyncDateForProperties() != mCurrentSyncTime) {
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100 228)                         Log_OC.d(TAG, "removing file: " + file);
67eb9210 (David A. Velasco 2012-10-22 15:11:59 +0200 229)                         mStorageManager.removeFile(file, (file.isDown() && file.getStoragePath().startsWith(currentSavePath)));
6e469559 (David A. Velasco 2012-10-23 12:47:29 +0200 230)                         mChildren.remove(i);
67eb9210 (David A. Velasco 2012-10-22 15:11:59 +0200 231)                     } else {
67eb9210 (David A. Velasco 2012-10-22 15:11:59 +0200 232)                         i++;
67eb9210 (David A. Velasco 2012-10-22 15:11:59 +0200 233)                     }
67eb9210 (David A. Velasco 2012-10-22 15:11:59 +0200 234)                 }
67eb9210 (David A. Velasco 2012-10-22 15:11:59 +0200 235)                 
67eb9210 (David A. Velasco 2012-10-22 15:11:59 +0200 236)             } else {
1192e036 (David A. Velasco 2012-10-22 15:38:37 +0200 237)                 client.exhaustResponse(query.getResponseBodyAsStream());
67eb9210 (David A. Velasco 2012-10-22 15:11:59 +0200 238)             }
67eb9210 (David A. Velasco 2012-10-22 15:11:59 +0200 239)             
1192e036 (David A. Velasco 2012-10-22 15:38:37 +0200 240)             // prepare result object
c301865c (David A. Velasco 2012-11-16 16:23:45 +0100 241)             if (isMultiStatus(status)) {
c301865c (David A. Velasco 2012-11-16 16:23:45 +0100 242)                 if (mConflictsFound > 0  || mFailsInFavouritesFound > 0) { 
c301865c (David A. Velasco 2012-11-16 16:23:45 +0100 243)                     result = new RemoteOperationResult(ResultCode.SYNC_CONFLICT);   // should be different result, but will do the job
c301865c (David A. Velasco 2012-11-16 16:23:45 +0100 244)                             
c301865c (David A. Velasco 2012-11-16 16:23:45 +0100 245)                 } else {
78bcf721 (David A. Velasco 2013-08-07 19:13:00 +0200 246)                     result = new RemoteOperationResult(true, status, query.getResponseHeaders());
c301865c (David A. Velasco 2012-11-16 16:23:45 +0100 247)                 }
c301865c (David A. Velasco 2012-11-16 16:23:45 +0100 248)             } else {
78bcf721 (David A. Velasco 2013-08-07 19:13:00 +0200 249)                 result = new RemoteOperationResult(false, status, query.getResponseHeaders());
c301865c (David A. Velasco 2012-11-16 16:23:45 +0100 250)             }
52bc433b (David A. Velasco 2013-08-01 17:47:09 +0200 251)             
67eb9210 (David A. Velasco 2012-10-22 15:11:59 +0200 252)             
67eb9210 (David A. Velasco 2012-10-22 15:11:59 +0200 253)             
67eb9210 (David A. Velasco 2012-10-22 15:11:59 +0200 254)         } catch (Exception e) {
1192e036 (David A. Velasco 2012-10-22 15:38:37 +0200 255)             result = new RemoteOperationResult(e);
52bc433b (David A. Velasco 2013-08-01 17:47:09 +0200 256)             
67eb9210 (David A. Velasco 2012-10-22 15:11:59 +0200 257) 
67eb9210 (David A. Velasco 2012-10-22 15:11:59 +0200 258)         } finally {
67eb9210 (David A. Velasco 2012-10-22 15:11:59 +0200 259)             if (query != null)
67eb9210 (David A. Velasco 2012-10-22 15:11:59 +0200 260)                 query.releaseConnection();  // let the connection available for other methods
52bc433b (David A. Velasco 2013-08-01 17:47:09 +0200 261)             if (result.isSuccess()) {
52bc433b (David A. Velasco 2013-08-01 17:47:09 +0200 262)                 Log_OC.i(TAG, "Synchronizing " + mAccount.name + ", folder " + mRemotePath + ": " + result.getLogMessage());
52bc433b (David A. Velasco 2013-08-01 17:47:09 +0200 263)             } else {
52bc433b (David A. Velasco 2013-08-01 17:47:09 +0200 264)                 if (result.isException()) {
52bc433b (David A. Velasco 2013-08-01 17:47:09 +0200 265)                     Log_OC.e(TAG, "Synchronizing " + mAccount.name + ", folder " + mRemotePath + ": " + result.getLogMessage(), result.getException());
52bc433b (David A. Velasco 2013-08-01 17:47:09 +0200 266)                 } else {
52bc433b (David A. Velasco 2013-08-01 17:47:09 +0200 267)                     Log_OC.e(TAG, "Synchronizing " + mAccount.name + ", folder " + mRemotePath + ": " + result.getLogMessage());
52bc433b (David A. Velasco 2013-08-01 17:47:09 +0200 268)                 }
52bc433b (David A. Velasco 2013-08-01 17:47:09 +0200 269)             }
67eb9210 (David A. Velasco 2012-10-22 15:11:59 +0200 270)         }
67eb9210 (David A. Velasco 2012-10-22 15:11:59 +0200 271)         
67eb9210 (David A. Velasco 2012-10-22 15:11:59 +0200 272)         return result;
67eb9210 (David A. Velasco 2012-10-22 15:11:59 +0200 273)     }
67eb9210 (David A. Velasco 2012-10-22 15:11:59 +0200 274)     
d5a327c3 (David A. Velasco 2012-10-25 10:41:24 +0200 275) 
1192e036 (David A. Velasco 2012-10-22 15:38:37 +0200 276)     public boolean isMultiStatus(int status) {
1192e036 (David A. Velasco 2012-10-22 15:38:37 +0200 277)         return (status == HttpStatus.SC_MULTI_STATUS); 
67eb9210 (David A. Velasco 2012-10-22 15:11:59 +0200 278)     }
67eb9210 (David A. Velasco 2012-10-22 15:11:59 +0200 279) 
67eb9210 (David A. Velasco 2012-10-22 15:11:59 +0200 280) 
1192e036 (David A. Velasco 2012-10-22 15:38:37 +0200 281)     /**
1192e036 (David A. Velasco 2012-10-22 15:38:37 +0200 282)      * Creates and populates a new {@link OCFile} object with the data read from the server.
1192e036 (David A. Velasco 2012-10-22 15:38:37 +0200 283)      * 
1192e036 (David A. Velasco 2012-10-22 15:38:37 +0200 284)      * @param we        WebDAV entry read from the server for a WebDAV resource (remote file or folder).
1192e036 (David A. Velasco 2012-10-22 15:38:37 +0200 285)      * @return          New OCFile instance representing the remote resource described by we.
1192e036 (David A. Velasco 2012-10-22 15:38:37 +0200 286)      */
67eb9210 (David A. Velasco 2012-10-22 15:11:59 +0200 287)     private OCFile fillOCFile(WebdavEntry we) {
67eb9210 (David A. Velasco 2012-10-22 15:11:59 +0200 288)         OCFile file = new OCFile(we.decodedPath());
67eb9210 (David A. Velasco 2012-10-22 15:11:59 +0200 289)         file.setCreationTimestamp(we.createTimestamp());
67eb9210 (David A. Velasco 2012-10-22 15:11:59 +0200 290)         file.setFileLength(we.contentLength());
67eb9210 (David A. Velasco 2012-10-22 15:11:59 +0200 291)         file.setMimetype(we.contentType());
abd5f515 (David A. Velasco 2012-12-05 16:14:01 +0100 292)         file.setModificationTimestamp(we.modifiedTimestamp());
c301865c (David A. Velasco 2012-11-16 16:23:45 +0100 293)         file.setParentId(mParentId);
67eb9210 (David A. Velasco 2012-10-22 15:11:59 +0200 294)         return file;
67eb9210 (David A. Velasco 2012-10-22 15:11:59 +0200 295)     }
67eb9210 (David A. Velasco 2012-10-22 15:11:59 +0200 296)     
1192e036 (David A. Velasco 2012-10-22 15:38:37 +0200 297) 
2a913bfb (David A. Velasco 2012-11-30 11:29:18 +0100 298)     /**
2a913bfb (David A. Velasco 2012-11-30 11:29:18 +0100 299)      * Checks the storage path of the OCFile received as parameter. If it's out of the local ownCloud folder,
2a913bfb (David A. Velasco 2012-11-30 11:29:18 +0100 300)      * tries to copy the file inside it. 
2a913bfb (David A. Velasco 2012-11-30 11:29:18 +0100 301)      * 
2a913bfb (David A. Velasco 2012-11-30 11:29:18 +0100 302)      * If the copy fails, the link to the local file is nullified. The account of forgotten files is kept in 
2a913bfb (David A. Velasco 2012-11-30 11:29:18 +0100 303)      * {@link #mForgottenLocalFiles}
2a913bfb (David A. Velasco 2012-11-30 11:29:18 +0100 304)      * 
2a913bfb (David A. Velasco 2012-11-30 11:29:18 +0100 305)      * @param file      File to check and fix.
2a913bfb (David A. Velasco 2012-11-30 11:29:18 +0100 306)      */
2a913bfb (David A. Velasco 2012-11-30 11:29:18 +0100 307)     private void checkAndFixForeignStoragePath(OCFile file) {
2a913bfb (David A. Velasco 2012-11-30 11:29:18 +0100 308)         String storagePath = file.getStoragePath();
2a913bfb (David A. Velasco 2012-11-30 11:29:18 +0100 309)         String expectedPath = FileStorageUtils.getDefaultSavePathFor(mAccount.name, file);
2a913bfb (David A. Velasco 2012-11-30 11:29:18 +0100 310)         if (storagePath != null && !storagePath.equals(expectedPath)) {
2a913bfb (David A. Velasco 2012-11-30 11:29:18 +0100 311)             /// fix storagePaths out of the local ownCloud folder
2a913bfb (David A. Velasco 2012-11-30 11:29:18 +0100 312)             File originalFile = new File(storagePath);
83615408 (David A. Velasco 2012-12-04 14:36:49 +0100 313)             if (FileStorageUtils.getUsableSpace(mAccount.name) < originalFile.length()) {
2a913bfb (David A. Velasco 2012-11-30 11:29:18 +0100 314)                 mForgottenLocalFiles.put(file.getRemotePath(), storagePath);
2a913bfb (David A. Velasco 2012-11-30 11:29:18 +0100 315)                 file.setStoragePath(null);
2a913bfb (David A. Velasco 2012-11-30 11:29:18 +0100 316)                     
2a913bfb (David A. Velasco 2012-11-30 11:29:18 +0100 317)             } else {
2a913bfb (David A. Velasco 2012-11-30 11:29:18 +0100 318)                 InputStream in = null;
2a913bfb (David A. Velasco 2012-11-30 11:29:18 +0100 319)                 OutputStream out = null;
2a913bfb (David A. Velasco 2012-11-30 11:29:18 +0100 320)                 try {
2a913bfb (David A. Velasco 2012-11-30 11:29:18 +0100 321)                     File expectedFile = new File(expectedPath);
4476c047 (David A. Velasco 2012-12-05 16:01:19 +0100 322)                     File expectedParent = expectedFile.getParentFile();
4476c047 (David A. Velasco 2012-12-05 16:01:19 +0100 323)                     expectedParent.mkdirs();
4476c047 (David A. Velasco 2012-12-05 16:01:19 +0100 324)                     if (!expectedParent.isDirectory()) {
4476c047 (David A. Velasco 2012-12-05 16:01:19 +0100 325)                         throw new IOException("Unexpected error: parent directory could not be created");
4476c047 (David A. Velasco 2012-12-05 16:01:19 +0100 326)                     }
4476c047 (David A. Velasco 2012-12-05 16:01:19 +0100 327)                     expectedFile.createNewFile();
4476c047 (David A. Velasco 2012-12-05 16:01:19 +0100 328)                     if (!expectedFile.isFile()) {
4476c047 (David A. Velasco 2012-12-05 16:01:19 +0100 329)                         throw new IOException("Unexpected error: target file could not be created");
4476c047 (David A. Velasco 2012-12-05 16:01:19 +0100 330)                     }                    
2a913bfb (David A. Velasco 2012-11-30 11:29:18 +0100 331)                     in = new FileInputStream(originalFile);
2a913bfb (David A. Velasco 2012-11-30 11:29:18 +0100 332)                     out = new FileOutputStream(expectedFile);
2a913bfb (David A. Velasco 2012-11-30 11:29:18 +0100 333)                     byte[] buf = new byte[1024];
2a913bfb (David A. Velasco 2012-11-30 11:29:18 +0100 334)                     int len;
2a913bfb (David A. Velasco 2012-11-30 11:29:18 +0100 335)                     while ((len = in.read(buf)) > 0){
2a913bfb (David A. Velasco 2012-11-30 11:29:18 +0100 336)                         out.write(buf, 0, len);
2a913bfb (David A. Velasco 2012-11-30 11:29:18 +0100 337)                     }
2a913bfb (David A. Velasco 2012-11-30 11:29:18 +0100 338)                     file.setStoragePath(expectedPath);
2a913bfb (David A. Velasco 2012-11-30 11:29:18 +0100 339)                     
2a913bfb (David A. Velasco 2012-11-30 11:29:18 +0100 340)                 } catch (Exception e) {
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100 341)                     Log_OC.e(TAG, "Exception while copying foreign file " + expectedPath, e);
2a913bfb (David A. Velasco 2012-11-30 11:29:18 +0100 342)                     mForgottenLocalFiles.put(file.getRemotePath(), storagePath);
2a913bfb (David A. Velasco 2012-11-30 11:29:18 +0100 343)                     file.setStoragePath(null);
2a913bfb (David A. Velasco 2012-11-30 11:29:18 +0100 344)                     
2a913bfb (David A. Velasco 2012-11-30 11:29:18 +0100 345)                 } finally {
2a913bfb (David A. Velasco 2012-11-30 11:29:18 +0100 346)                     try {
2a913bfb (David A. Velasco 2012-11-30 11:29:18 +0100 347)                         if (in != null) in.close();
2a913bfb (David A. Velasco 2012-11-30 11:29:18 +0100 348)                     } catch (Exception e) {
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100 349)                         Log_OC.d(TAG, "Weird exception while closing input stream for " + storagePath + " (ignoring)", e);
2a913bfb (David A. Velasco 2012-11-30 11:29:18 +0100 350)                     }
2a913bfb (David A. Velasco 2012-11-30 11:29:18 +0100 351)                     try {
2a913bfb (David A. Velasco 2012-11-30 11:29:18 +0100 352)                         if (out != null) out.close();
2a913bfb (David A. Velasco 2012-11-30 11:29:18 +0100 353)                     } catch (Exception e) {
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100 354)                         Log_OC.d(TAG, "Weird exception while closing output stream for " + expectedPath + " (ignoring)", e);
2a913bfb (David A. Velasco 2012-11-30 11:29:18 +0100 355)                     }
2a913bfb (David A. Velasco 2012-11-30 11:29:18 +0100 356)                 }
2a913bfb (David A. Velasco 2012-11-30 11:29:18 +0100 357)             }
2a913bfb (David A. Velasco 2012-11-30 11:29:18 +0100 358)         }
2a913bfb (David A. Velasco 2012-11-30 11:29:18 +0100 359)     }
2a913bfb (David A. Velasco 2012-11-30 11:29:18 +0100 360) 
2a913bfb (David A. Velasco 2012-11-30 11:29:18 +0100 361) 
67eb9210 (David A. Velasco 2012-10-22 15:11:59 +0200 362) }
