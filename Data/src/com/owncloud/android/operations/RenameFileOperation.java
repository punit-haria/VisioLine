5af0fb44 (David A. Velasco 2012-10-26 11:34:20 +0200   1) /* ownCloud Android client application
bb257ec7 (David A. Velasco 2013-02-07 18:45:10 +0100   2)  *   Copyright (C) 2012-2013 ownCloud Inc.
5af0fb44 (David A. Velasco 2012-10-26 11:34:20 +0200   3)  *
5af0fb44 (David A. Velasco 2012-10-26 11:34:20 +0200   4)  *   This program is free software: you can redistribute it and/or modify
bdc0332c (David A. Velasco 2013-04-17 12:26:13 +0200   5)  *   it under the terms of the GNU General Public License version 2,
bdc0332c (David A. Velasco 2013-04-17 12:26:13 +0200   6)  *   as published by the Free Software Foundation.
5af0fb44 (David A. Velasco 2012-10-26 11:34:20 +0200   7)  *
5af0fb44 (David A. Velasco 2012-10-26 11:34:20 +0200   8)  *   This program is distributed in the hope that it will be useful,
5af0fb44 (David A. Velasco 2012-10-26 11:34:20 +0200   9)  *   but WITHOUT ANY WARRANTY; without even the implied warranty of
5af0fb44 (David A. Velasco 2012-10-26 11:34:20 +0200  10)  *   MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
5af0fb44 (David A. Velasco 2012-10-26 11:34:20 +0200  11)  *   GNU General Public License for more details.
5af0fb44 (David A. Velasco 2012-10-26 11:34:20 +0200  12)  *
5af0fb44 (David A. Velasco 2012-10-26 11:34:20 +0200  13)  *   You should have received a copy of the GNU General Public License
5af0fb44 (David A. Velasco 2012-10-26 11:34:20 +0200  14)  *   along with this program.  If not, see <http://www.gnu.org/licenses/>.
5af0fb44 (David A. Velasco 2012-10-26 11:34:20 +0200  15)  *
5af0fb44 (David A. Velasco 2012-10-26 11:34:20 +0200  16)  */
5af0fb44 (David A. Velasco 2012-10-26 11:34:20 +0200  17) 
5af0fb44 (David A. Velasco 2012-10-26 11:34:20 +0200  18) package com.owncloud.android.operations;
5af0fb44 (David A. Velasco 2012-10-26 11:34:20 +0200  19) 
5af0fb44 (David A. Velasco 2012-10-26 11:34:20 +0200  20) import java.io.File;
5af0fb44 (David A. Velasco 2012-10-26 11:34:20 +0200  21) import java.io.IOException;
5af0fb44 (David A. Velasco 2012-10-26 11:34:20 +0200  22) 
5af0fb44 (David A. Velasco 2012-10-26 11:34:20 +0200  23) import org.apache.jackrabbit.webdav.client.methods.DavMethodBase;
5af0fb44 (David A. Velasco 2012-10-26 11:34:20 +0200  24) //import org.apache.jackrabbit.webdav.client.methods.MoveMethod;
5af0fb44 (David A. Velasco 2012-10-26 11:34:20 +0200  25) 
c38a3b2e (David A. Velasco 2012-12-18 11:20:03 +0100  26) import android.accounts.Account;
5af0fb44 (David A. Velasco 2012-10-26 11:34:20 +0200  27) 
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100  28) import com.owncloud.android.Log_OC;
5af0fb44 (David A. Velasco 2012-10-26 11:34:20 +0200  29) import com.owncloud.android.datamodel.DataStorageManager;
5af0fb44 (David A. Velasco 2012-10-26 11:34:20 +0200  30) import com.owncloud.android.datamodel.OCFile;
5af0fb44 (David A. Velasco 2012-10-26 11:34:20 +0200  31) import com.owncloud.android.operations.RemoteOperationResult.ResultCode;
c38a3b2e (David A. Velasco 2012-12-18 11:20:03 +0100  32) import com.owncloud.android.utils.FileStorageUtils;
5af0fb44 (David A. Velasco 2012-10-26 11:34:20 +0200  33) 
5af0fb44 (David A. Velasco 2012-10-26 11:34:20 +0200  34) import eu.alefzero.webdav.WebdavClient;
5af0fb44 (David A. Velasco 2012-10-26 11:34:20 +0200  35) import eu.alefzero.webdav.WebdavUtils;
5af0fb44 (David A. Velasco 2012-10-26 11:34:20 +0200  36) 
5af0fb44 (David A. Velasco 2012-10-26 11:34:20 +0200  37) /**
5af0fb44 (David A. Velasco 2012-10-26 11:34:20 +0200  38)  * Remote operation performing the rename of a remote file (or folder?) in the ownCloud server.
5af0fb44 (David A. Velasco 2012-10-26 11:34:20 +0200  39)  * 
5af0fb44 (David A. Velasco 2012-10-26 11:34:20 +0200  40)  * @author David A. Velasco
5af0fb44 (David A. Velasco 2012-10-26 11:34:20 +0200  41)  */
5af0fb44 (David A. Velasco 2012-10-26 11:34:20 +0200  42) public class RenameFileOperation extends RemoteOperation {
5af0fb44 (David A. Velasco 2012-10-26 11:34:20 +0200  43)     
11b88e0f (David A. Velasco 2012-12-19 14:12:53 +0100  44)     private static final String TAG = RenameFileOperation.class.getSimpleName();
5af0fb44 (David A. Velasco 2012-10-26 11:34:20 +0200  45) 
5af0fb44 (David A. Velasco 2012-10-26 11:34:20 +0200  46)     private static final int RENAME_READ_TIMEOUT = 10000;
5af0fb44 (David A. Velasco 2012-10-26 11:34:20 +0200  47)     private static final int RENAME_CONNECTION_TIMEOUT = 5000;
5af0fb44 (David A. Velasco 2012-10-26 11:34:20 +0200  48)     
5af0fb44 (David A. Velasco 2012-10-26 11:34:20 +0200  49) 
5af0fb44 (David A. Velasco 2012-10-26 11:34:20 +0200  50)     private OCFile mFile;
c38a3b2e (David A. Velasco 2012-12-18 11:20:03 +0100  51)     private Account mAccount;
5af0fb44 (David A. Velasco 2012-10-26 11:34:20 +0200  52)     private String mNewName;
c38a3b2e (David A. Velasco 2012-12-18 11:20:03 +0100  53)     private String mNewRemotePath;
5af0fb44 (David A. Velasco 2012-10-26 11:34:20 +0200  54)     private DataStorageManager mStorageManager;
5af0fb44 (David A. Velasco 2012-10-26 11:34:20 +0200  55)     
5af0fb44 (David A. Velasco 2012-10-26 11:34:20 +0200  56)     
5af0fb44 (David A. Velasco 2012-10-26 11:34:20 +0200  57)     /**
5af0fb44 (David A. Velasco 2012-10-26 11:34:20 +0200  58)      * Constructor
5af0fb44 (David A. Velasco 2012-10-26 11:34:20 +0200  59)      * 
5af0fb44 (David A. Velasco 2012-10-26 11:34:20 +0200  60)      * @param file                  OCFile instance describing the remote file or folder to rename
c38a3b2e (David A. Velasco 2012-12-18 11:20:03 +0100  61)      * @param account               OwnCloud account containing the remote file 
5af0fb44 (David A. Velasco 2012-10-26 11:34:20 +0200  62)      * @param newName               New name to set as the name of file.
5af0fb44 (David A. Velasco 2012-10-26 11:34:20 +0200  63)      * @param storageManager        Reference to the local database corresponding to the account where the file is contained. 
5af0fb44 (David A. Velasco 2012-10-26 11:34:20 +0200  64)      */
c38a3b2e (David A. Velasco 2012-12-18 11:20:03 +0100  65)     public RenameFileOperation(OCFile file, Account account, String newName, DataStorageManager storageManager) {
5af0fb44 (David A. Velasco 2012-10-26 11:34:20 +0200  66)         mFile = file;
c38a3b2e (David A. Velasco 2012-12-18 11:20:03 +0100  67)         mAccount = account;
5af0fb44 (David A. Velasco 2012-10-26 11:34:20 +0200  68)         mNewName = newName;
c38a3b2e (David A. Velasco 2012-12-18 11:20:03 +0100  69)         mNewRemotePath = null;
5af0fb44 (David A. Velasco 2012-10-26 11:34:20 +0200  70)         mStorageManager = storageManager;
5af0fb44 (David A. Velasco 2012-10-26 11:34:20 +0200  71)     }
5af0fb44 (David A. Velasco 2012-10-26 11:34:20 +0200  72)   
5af0fb44 (David A. Velasco 2012-10-26 11:34:20 +0200  73)     public OCFile getFile() {
5af0fb44 (David A. Velasco 2012-10-26 11:34:20 +0200  74)         return mFile;
5af0fb44 (David A. Velasco 2012-10-26 11:34:20 +0200  75)     }
5af0fb44 (David A. Velasco 2012-10-26 11:34:20 +0200  76)     
5af0fb44 (David A. Velasco 2012-10-26 11:34:20 +0200  77)     
5af0fb44 (David A. Velasco 2012-10-26 11:34:20 +0200  78)     /**
5af0fb44 (David A. Velasco 2012-10-26 11:34:20 +0200  79)      * Performs the rename operation.
5af0fb44 (David A. Velasco 2012-10-26 11:34:20 +0200  80)      * 
5af0fb44 (David A. Velasco 2012-10-26 11:34:20 +0200  81)      * @param   client      Client object to communicate with the remote ownCloud server.
5af0fb44 (David A. Velasco 2012-10-26 11:34:20 +0200  82)      */
5af0fb44 (David A. Velasco 2012-10-26 11:34:20 +0200  83)     @Override
5af0fb44 (David A. Velasco 2012-10-26 11:34:20 +0200  84)     protected RemoteOperationResult run(WebdavClient client) {
5af0fb44 (David A. Velasco 2012-10-26 11:34:20 +0200  85)         RemoteOperationResult result = null;
5af0fb44 (David A. Velasco 2012-10-26 11:34:20 +0200  86)         
5af0fb44 (David A. Velasco 2012-10-26 11:34:20 +0200  87)         LocalMoveMethod move = null;
c38a3b2e (David A. Velasco 2012-12-18 11:20:03 +0100  88)         mNewRemotePath = null;
5af0fb44 (David A. Velasco 2012-10-26 11:34:20 +0200  89)         try {
5af0fb44 (David A. Velasco 2012-10-26 11:34:20 +0200  90)             if (mNewName.equals(mFile.getFileName())) {
5af0fb44 (David A. Velasco 2012-10-26 11:34:20 +0200  91)                 return new RemoteOperationResult(ResultCode.OK);
5af0fb44 (David A. Velasco 2012-10-26 11:34:20 +0200  92)             }
5af0fb44 (David A. Velasco 2012-10-26 11:34:20 +0200  93)         
c38a3b2e (David A. Velasco 2012-12-18 11:20:03 +0100  94)             String parent = (new File(mFile.getRemotePath())).getParent();
c38a3b2e (David A. Velasco 2012-12-18 11:20:03 +0100  95)             parent = (parent.endsWith(OCFile.PATH_SEPARATOR)) ? parent : parent + OCFile.PATH_SEPARATOR; 
c38a3b2e (David A. Velasco 2012-12-18 11:20:03 +0100  96)             mNewRemotePath =  parent + mNewName;
c38a3b2e (David A. Velasco 2012-12-18 11:20:03 +0100  97)             if (mFile.isDirectory()) {
c38a3b2e (David A. Velasco 2012-12-18 11:20:03 +0100  98)                 mNewRemotePath += OCFile.PATH_SEPARATOR;
c38a3b2e (David A. Velasco 2012-12-18 11:20:03 +0100  99)             }
5af0fb44 (David A. Velasco 2012-10-26 11:34:20 +0200 100)             
5af0fb44 (David A. Velasco 2012-10-26 11:34:20 +0200 101)             // check if the new name is valid in the local file system
5af0fb44 (David A. Velasco 2012-10-26 11:34:20 +0200 102)             if (!isValidNewName()) {
5af0fb44 (David A. Velasco 2012-10-26 11:34:20 +0200 103)                 return new RemoteOperationResult(ResultCode.INVALID_LOCAL_FILE_NAME);
5af0fb44 (David A. Velasco 2012-10-26 11:34:20 +0200 104)             }
5af0fb44 (David A. Velasco 2012-10-26 11:34:20 +0200 105)         
c38a3b2e (David A. Velasco 2012-12-18 11:20:03 +0100 106)             // check if a file with the new name already exists
bdc0332c (David A. Velasco 2013-04-17 12:26:13 +0200 107)             if (client.existsFile(mNewRemotePath) ||                             // remote check could fail by network failure. by indeterminate behavior of HEAD for folders ... 
c38a3b2e (David A. Velasco 2012-12-18 11:20:03 +0100 108)                     mStorageManager.getFileByPath(mNewRemotePath) != null) {     // ... so local check is convenient
5af0fb44 (David A. Velasco 2012-10-26 11:34:20 +0200 109)                 return new RemoteOperationResult(ResultCode.INVALID_OVERWRITE);
5af0fb44 (David A. Velasco 2012-10-26 11:34:20 +0200 110)             }
5af0fb44 (David A. Velasco 2012-10-26 11:34:20 +0200 111)             move = new LocalMoveMethod( client.getBaseUri() + WebdavUtils.encodePath(mFile.getRemotePath()),
c38a3b2e (David A. Velasco 2012-12-18 11:20:03 +0100 112)                                         client.getBaseUri() + WebdavUtils.encodePath(mNewRemotePath));
5af0fb44 (David A. Velasco 2012-10-26 11:34:20 +0200 113)             int status = client.executeMethod(move, RENAME_READ_TIMEOUT, RENAME_CONNECTION_TIMEOUT);
5af0fb44 (David A. Velasco 2012-10-26 11:34:20 +0200 114)             if (move.succeeded()) {
5af0fb44 (David A. Velasco 2012-10-26 11:34:20 +0200 115) 
c38a3b2e (David A. Velasco 2012-12-18 11:20:03 +0100 116)                 if (mFile.isDirectory()) {
c38a3b2e (David A. Velasco 2012-12-18 11:20:03 +0100 117)                     saveLocalDirectory();
c38a3b2e (David A. Velasco 2012-12-18 11:20:03 +0100 118)                     
c38a3b2e (David A. Velasco 2012-12-18 11:20:03 +0100 119)                 } else {
c38a3b2e (David A. Velasco 2012-12-18 11:20:03 +0100 120)                     saveLocalFile();
c38a3b2e (David A. Velasco 2012-12-18 11:20:03 +0100 121)                     
5af0fb44 (David A. Velasco 2012-10-26 11:34:20 +0200 122)                 }
c38a3b2e (David A. Velasco 2012-12-18 11:20:03 +0100 123)              
c38a3b2e (David A. Velasco 2012-12-18 11:20:03 +0100 124)             /* 
c38a3b2e (David A. Velasco 2012-12-18 11:20:03 +0100 125)              *} else if (mFile.isDirectory() && (status == 207 || status >= 500)) {
c38a3b2e (David A. Velasco 2012-12-18 11:20:03 +0100 126)              *   // TODO 
c38a3b2e (David A. Velasco 2012-12-18 11:20:03 +0100 127)              *   // if server fails in the rename of a folder, some children files could have been moved to a folder with the new name while some others
c38a3b2e (David A. Velasco 2012-12-18 11:20:03 +0100 128)              *   // stayed in the old folder;
c38a3b2e (David A. Velasco 2012-12-18 11:20:03 +0100 129)              *   //
c38a3b2e (David A. Velasco 2012-12-18 11:20:03 +0100 130)              *   // easiest and heaviest solution is synchronizing the parent folder (or the full account);
c38a3b2e (David A. Velasco 2012-12-18 11:20:03 +0100 131)              *   //
c38a3b2e (David A. Velasco 2012-12-18 11:20:03 +0100 132)              *   // a better solution is synchronizing the folders with the old and new names;
c38a3b2e (David A. Velasco 2012-12-18 11:20:03 +0100 133)              *}
c38a3b2e (David A. Velasco 2012-12-18 11:20:03 +0100 134)              */
5af0fb44 (David A. Velasco 2012-10-26 11:34:20 +0200 135)                 
5af0fb44 (David A. Velasco 2012-10-26 11:34:20 +0200 136)             }
5af0fb44 (David A. Velasco 2012-10-26 11:34:20 +0200 137)             
5af0fb44 (David A. Velasco 2012-10-26 11:34:20 +0200 138)             move.getResponseBodyAsString(); // exhaust response, although not interesting
78bcf721 (David A. Velasco 2013-08-07 19:13:00 +0200 139)             result = new RemoteOperationResult(move.succeeded(), status, move.getResponseHeaders());
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100 140)             Log_OC.i(TAG, "Rename " + mFile.getRemotePath() + " to " + mNewRemotePath + ": " + result.getLogMessage());
5af0fb44 (David A. Velasco 2012-10-26 11:34:20 +0200 141)             
5af0fb44 (David A. Velasco 2012-10-26 11:34:20 +0200 142)         } catch (Exception e) {
5af0fb44 (David A. Velasco 2012-10-26 11:34:20 +0200 143)             result = new RemoteOperationResult(e);
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100 144)             Log_OC.e(TAG, "Rename " + mFile.getRemotePath() + " to " + ((mNewRemotePath==null) ? mNewName : mNewRemotePath) + ": " + result.getLogMessage(), e);
5af0fb44 (David A. Velasco 2012-10-26 11:34:20 +0200 145)             
5af0fb44 (David A. Velasco 2012-10-26 11:34:20 +0200 146)         } finally {
5af0fb44 (David A. Velasco 2012-10-26 11:34:20 +0200 147)             if (move != null)
5af0fb44 (David A. Velasco 2012-10-26 11:34:20 +0200 148)                 move.releaseConnection();
5af0fb44 (David A. Velasco 2012-10-26 11:34:20 +0200 149)         }
5af0fb44 (David A. Velasco 2012-10-26 11:34:20 +0200 150)         return result;
5af0fb44 (David A. Velasco 2012-10-26 11:34:20 +0200 151)     }
5af0fb44 (David A. Velasco 2012-10-26 11:34:20 +0200 152) 
5af0fb44 (David A. Velasco 2012-10-26 11:34:20 +0200 153)     
c38a3b2e (David A. Velasco 2012-12-18 11:20:03 +0100 154)     private void saveLocalDirectory() {
c38a3b2e (David A. Velasco 2012-12-18 11:20:03 +0100 155)         mStorageManager.moveDirectory(mFile, mNewRemotePath);
c38a3b2e (David A. Velasco 2012-12-18 11:20:03 +0100 156)         String localPath = FileStorageUtils.getDefaultSavePathFor(mAccount.name, mFile);
c38a3b2e (David A. Velasco 2012-12-18 11:20:03 +0100 157)         File localDir = new File(localPath);
c38a3b2e (David A. Velasco 2012-12-18 11:20:03 +0100 158)         if (localDir.exists()) {
c38a3b2e (David A. Velasco 2012-12-18 11:20:03 +0100 159)             localDir.renameTo(new File(FileStorageUtils.getSavePath(mAccount.name) + mNewRemotePath));
c38a3b2e (David A. Velasco 2012-12-18 11:20:03 +0100 160)             // TODO - if renameTo fails, children files that are already down will result unlinked
c38a3b2e (David A. Velasco 2012-12-18 11:20:03 +0100 161)         }
c38a3b2e (David A. Velasco 2012-12-18 11:20:03 +0100 162)     }
c38a3b2e (David A. Velasco 2012-12-18 11:20:03 +0100 163) 
c38a3b2e (David A. Velasco 2012-12-18 11:20:03 +0100 164)     private void saveLocalFile() {
c38a3b2e (David A. Velasco 2012-12-18 11:20:03 +0100 165)         mFile.setFileName(mNewName);
c38a3b2e (David A. Velasco 2012-12-18 11:20:03 +0100 166)         
c38a3b2e (David A. Velasco 2012-12-18 11:20:03 +0100 167)         // try to rename the local copy of the file
c38a3b2e (David A. Velasco 2012-12-18 11:20:03 +0100 168)         if (mFile.isDown()) {
c38a3b2e (David A. Velasco 2012-12-18 11:20:03 +0100 169)             File f = new File(mFile.getStoragePath());
c38a3b2e (David A. Velasco 2012-12-18 11:20:03 +0100 170)             String parentStoragePath = f.getParent();
c38a3b2e (David A. Velasco 2012-12-18 11:20:03 +0100 171)             if (!parentStoragePath.endsWith(File.separator))
c38a3b2e (David A. Velasco 2012-12-18 11:20:03 +0100 172)                 parentStoragePath += File.separator;
c38a3b2e (David A. Velasco 2012-12-18 11:20:03 +0100 173)             if (f.renameTo(new File(parentStoragePath + mNewName))) {
c38a3b2e (David A. Velasco 2012-12-18 11:20:03 +0100 174)                 mFile.setStoragePath(parentStoragePath + mNewName);
c38a3b2e (David A. Velasco 2012-12-18 11:20:03 +0100 175)             }
c38a3b2e (David A. Velasco 2012-12-18 11:20:03 +0100 176)             // else - NOTHING: the link to the local file is kept although the local name can't be updated
c38a3b2e (David A. Velasco 2012-12-18 11:20:03 +0100 177)             // TODO - study conditions when this could be a problem
c38a3b2e (David A. Velasco 2012-12-18 11:20:03 +0100 178)         }
c38a3b2e (David A. Velasco 2012-12-18 11:20:03 +0100 179)         
c38a3b2e (David A. Velasco 2012-12-18 11:20:03 +0100 180)         mStorageManager.saveFile(mFile);
c38a3b2e (David A. Velasco 2012-12-18 11:20:03 +0100 181)     }
c38a3b2e (David A. Velasco 2012-12-18 11:20:03 +0100 182) 
5af0fb44 (David A. Velasco 2012-10-26 11:34:20 +0200 183)     /**
5af0fb44 (David A. Velasco 2012-10-26 11:34:20 +0200 184)      * Checks if the new name to set is valid in the file system 
5af0fb44 (David A. Velasco 2012-10-26 11:34:20 +0200 185)      * 
5af0fb44 (David A. Velasco 2012-10-26 11:34:20 +0200 186)      * The only way to be sure is trying to create a file with that name. It's made in the temporal directory
5af0fb44 (David A. Velasco 2012-10-26 11:34:20 +0200 187)      * for downloads, out of any account, and then removed. 
5af0fb44 (David A. Velasco 2012-10-26 11:34:20 +0200 188)      * 
5af0fb44 (David A. Velasco 2012-10-26 11:34:20 +0200 189)      * IMPORTANT: The test must be made in the same file system where files are download. The internal storage
5af0fb44 (David A. Velasco 2012-10-26 11:34:20 +0200 190)      * could be formatted with a different file system.
5af0fb44 (David A. Velasco 2012-10-26 11:34:20 +0200 191)      * 
30c934fe (David A. Velasco 2012-10-26 13:49:03 +0200 192)      * TODO move this method, and maybe FileDownload.get***Path(), to a class with utilities specific for the interactions with the file system
30c934fe (David A. Velasco 2012-10-26 13:49:03 +0200 193)      * 
6ef46f26 (David A. Velasco 2013-04-19 10:56:00 +0200 194)      * @return              'True' if a temporal file named with the name to set could be created in the file system where 
6ef46f26 (David A. Velasco 2013-04-19 10:56:00 +0200 195)      *                      local files are stored.
6ef46f26 (David A. Velasco 2013-04-19 10:56:00 +0200 196)      * @throws IOException  When the temporal folder can not be created.
5af0fb44 (David A. Velasco 2012-10-26 11:34:20 +0200 197)      */
6ef46f26 (David A. Velasco 2013-04-19 10:56:00 +0200 198)     private boolean isValidNewName() throws IOException {
5af0fb44 (David A. Velasco 2012-10-26 11:34:20 +0200 199)         // check tricky names
30c934fe (David A. Velasco 2012-10-26 13:49:03 +0200 200)         if (mNewName == null || mNewName.length() <= 0 || mNewName.contains(File.separator) || mNewName.contains("%")) { 
5af0fb44 (David A. Velasco 2012-10-26 11:34:20 +0200 201)             return false;
5af0fb44 (David A. Velasco 2012-10-26 11:34:20 +0200 202)         }
5af0fb44 (David A. Velasco 2012-10-26 11:34:20 +0200 203)         // create a test file
6ef46f26 (David A. Velasco 2013-04-19 10:56:00 +0200 204)         String tmpFolderName = FileStorageUtils.getTemporalPath("");
6ef46f26 (David A. Velasco 2013-04-19 10:56:00 +0200 205)         File testFile = new File(tmpFolderName + mNewName);
6ef46f26 (David A. Velasco 2013-04-19 10:56:00 +0200 206)         File tmpFolder = testFile.getParentFile();
6ef46f26 (David A. Velasco 2013-04-19 10:56:00 +0200 207)         tmpFolder.mkdirs();
6ef46f26 (David A. Velasco 2013-04-19 10:56:00 +0200 208)         if (!tmpFolder.isDirectory()) {
6ef46f26 (David A. Velasco 2013-04-19 10:56:00 +0200 209)             throw new IOException("Unexpected error: temporal directory could not be created");
6ef46f26 (David A. Velasco 2013-04-19 10:56:00 +0200 210)         }
5af0fb44 (David A. Velasco 2012-10-26 11:34:20 +0200 211)         try {
5af0fb44 (David A. Velasco 2012-10-26 11:34:20 +0200 212)             testFile.createNewFile();   // return value is ignored; it could be 'false' because the file already existed, that doesn't invalidate the name
5af0fb44 (David A. Velasco 2012-10-26 11:34:20 +0200 213)         } catch (IOException e) {
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100 214)             Log_OC.i(TAG, "Test for validity of name " + mNewName + " in the file system failed");
5af0fb44 (David A. Velasco 2012-10-26 11:34:20 +0200 215)             return false;
5af0fb44 (David A. Velasco 2012-10-26 11:34:20 +0200 216)         }
5af0fb44 (David A. Velasco 2012-10-26 11:34:20 +0200 217)         boolean result = (testFile.exists() && testFile.isFile());
5af0fb44 (David A. Velasco 2012-10-26 11:34:20 +0200 218)         
5af0fb44 (David A. Velasco 2012-10-26 11:34:20 +0200 219)         // cleaning ; result is ignored, since there is not much we could do in case of failure, but repeat and repeat...
5af0fb44 (David A. Velasco 2012-10-26 11:34:20 +0200 220)         testFile.delete();
5af0fb44 (David A. Velasco 2012-10-26 11:34:20 +0200 221)         
5af0fb44 (David A. Velasco 2012-10-26 11:34:20 +0200 222)         return result;
5af0fb44 (David A. Velasco 2012-10-26 11:34:20 +0200 223)     }
5af0fb44 (David A. Velasco 2012-10-26 11:34:20 +0200 224) 
5af0fb44 (David A. Velasco 2012-10-26 11:34:20 +0200 225) 
c38a3b2e (David A. Velasco 2012-12-18 11:20:03 +0100 226)     // move operation
5af0fb44 (David A. Velasco 2012-10-26 11:34:20 +0200 227)     private class LocalMoveMethod extends DavMethodBase {
5af0fb44 (David A. Velasco 2012-10-26 11:34:20 +0200 228) 
5af0fb44 (David A. Velasco 2012-10-26 11:34:20 +0200 229)         public LocalMoveMethod(String uri, String dest) {
5af0fb44 (David A. Velasco 2012-10-26 11:34:20 +0200 230)             super(uri);
5af0fb44 (David A. Velasco 2012-10-26 11:34:20 +0200 231)             addRequestHeader(new org.apache.commons.httpclient.Header("Destination", dest));
5af0fb44 (David A. Velasco 2012-10-26 11:34:20 +0200 232)         }
5af0fb44 (David A. Velasco 2012-10-26 11:34:20 +0200 233) 
5af0fb44 (David A. Velasco 2012-10-26 11:34:20 +0200 234)         @Override
5af0fb44 (David A. Velasco 2012-10-26 11:34:20 +0200 235)         public String getName() {
5af0fb44 (David A. Velasco 2012-10-26 11:34:20 +0200 236)             return "MOVE";
5af0fb44 (David A. Velasco 2012-10-26 11:34:20 +0200 237)         }
5af0fb44 (David A. Velasco 2012-10-26 11:34:20 +0200 238) 
5af0fb44 (David A. Velasco 2012-10-26 11:34:20 +0200 239)         @Override
5af0fb44 (David A. Velasco 2012-10-26 11:34:20 +0200 240)         protected boolean isSuccess(int status) {
5af0fb44 (David A. Velasco 2012-10-26 11:34:20 +0200 241)             return status == 201 || status == 204;
5af0fb44 (David A. Velasco 2012-10-26 11:34:20 +0200 242)         }
5af0fb44 (David A. Velasco 2012-10-26 11:34:20 +0200 243)             
5af0fb44 (David A. Velasco 2012-10-26 11:34:20 +0200 244)     }
5af0fb44 (David A. Velasco 2012-10-26 11:34:20 +0200 245)     
5af0fb44 (David A. Velasco 2012-10-26 11:34:20 +0200 246) 
5af0fb44 (David A. Velasco 2012-10-26 11:34:20 +0200 247) }
