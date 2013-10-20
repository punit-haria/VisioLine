11b88e0f (David A. Velasco 2012-12-19 14:12:53 +0100  1) /* ownCloud Android client application
11b88e0f (David A. Velasco 2012-12-19 14:12:53 +0100  2)  *   Copyright (C) 2012 ownCloud Inc.
11b88e0f (David A. Velasco 2012-12-19 14:12:53 +0100  3)  *
11b88e0f (David A. Velasco 2012-12-19 14:12:53 +0100  4)  *   This program is free software: you can redistribute it and/or modify
2946d8dd (David A. Velasco 2013-04-25 19:39:22 +0200  5)  *   it under the terms of the GNU General Public License version 2,
2946d8dd (David A. Velasco 2013-04-25 19:39:22 +0200  6)  *   as published by the Free Software Foundation.
11b88e0f (David A. Velasco 2012-12-19 14:12:53 +0100  7)  *
11b88e0f (David A. Velasco 2012-12-19 14:12:53 +0100  8)  *   This program is distributed in the hope that it will be useful,
11b88e0f (David A. Velasco 2012-12-19 14:12:53 +0100  9)  *   but WITHOUT ANY WARRANTY; without even the implied warranty of
11b88e0f (David A. Velasco 2012-12-19 14:12:53 +0100 10)  *   MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
11b88e0f (David A. Velasco 2012-12-19 14:12:53 +0100 11)  *   GNU General Public License for more details.
11b88e0f (David A. Velasco 2012-12-19 14:12:53 +0100 12)  *
11b88e0f (David A. Velasco 2012-12-19 14:12:53 +0100 13)  *   You should have received a copy of the GNU General Public License
11b88e0f (David A. Velasco 2012-12-19 14:12:53 +0100 14)  *   along with this program.  If not, see <http://www.gnu.org/licenses/>.
11b88e0f (David A. Velasco 2012-12-19 14:12:53 +0100 15)  *
11b88e0f (David A. Velasco 2012-12-19 14:12:53 +0100 16)  */
11b88e0f (David A. Velasco 2012-12-19 14:12:53 +0100 17) 
11b88e0f (David A. Velasco 2012-12-19 14:12:53 +0100 18) package com.owncloud.android.operations;
11b88e0f (David A. Velasco 2012-12-19 14:12:53 +0100 19) 
11b88e0f (David A. Velasco 2012-12-19 14:12:53 +0100 20) import org.apache.jackrabbit.webdav.client.methods.MkColMethod;
11b88e0f (David A. Velasco 2012-12-19 14:12:53 +0100 21) 
2946d8dd (David A. Velasco 2013-04-25 19:39:22 +0200 22) import com.owncloud.android.Log_OC;
11b88e0f (David A. Velasco 2012-12-19 14:12:53 +0100 23) import com.owncloud.android.datamodel.DataStorageManager;
11b88e0f (David A. Velasco 2012-12-19 14:12:53 +0100 24) import com.owncloud.android.datamodel.OCFile;
11b88e0f (David A. Velasco 2012-12-19 14:12:53 +0100 25) 
11b88e0f (David A. Velasco 2012-12-19 14:12:53 +0100 26) import eu.alefzero.webdav.WebdavClient;
11b88e0f (David A. Velasco 2012-12-19 14:12:53 +0100 27) import eu.alefzero.webdav.WebdavUtils;
11b88e0f (David A. Velasco 2012-12-19 14:12:53 +0100 28) 
11b88e0f (David A. Velasco 2012-12-19 14:12:53 +0100 29) /**
11b88e0f (David A. Velasco 2012-12-19 14:12:53 +0100 30)  * Remote operation performing the creation of a new folder in the ownCloud server.
11b88e0f (David A. Velasco 2012-12-19 14:12:53 +0100 31)  * 
11b88e0f (David A. Velasco 2012-12-19 14:12:53 +0100 32)  * @author David A. Velasco 
11b88e0f (David A. Velasco 2012-12-19 14:12:53 +0100 33)  */
11b88e0f (David A. Velasco 2012-12-19 14:12:53 +0100 34) public class CreateFolderOperation extends RemoteOperation {
11b88e0f (David A. Velasco 2012-12-19 14:12:53 +0100 35)     
11b88e0f (David A. Velasco 2012-12-19 14:12:53 +0100 36)     private static final String TAG = CreateFolderOperation.class.getSimpleName();
11b88e0f (David A. Velasco 2012-12-19 14:12:53 +0100 37) 
11b88e0f (David A. Velasco 2012-12-19 14:12:53 +0100 38)     private static final int READ_TIMEOUT = 10000;
11b88e0f (David A. Velasco 2012-12-19 14:12:53 +0100 39)     private static final int CONNECTION_TIMEOUT = 5000;
11b88e0f (David A. Velasco 2012-12-19 14:12:53 +0100 40)     
11b88e0f (David A. Velasco 2012-12-19 14:12:53 +0100 41)     protected String mRemotePath;
11b88e0f (David A. Velasco 2012-12-19 14:12:53 +0100 42)     protected long mParentDirId;
11b88e0f (David A. Velasco 2012-12-19 14:12:53 +0100 43)     protected DataStorageManager mStorageManager;
11b88e0f (David A. Velasco 2012-12-19 14:12:53 +0100 44)     
11b88e0f (David A. Velasco 2012-12-19 14:12:53 +0100 45)     /**
11b88e0f (David A. Velasco 2012-12-19 14:12:53 +0100 46)      * Constructor
11b88e0f (David A. Velasco 2012-12-19 14:12:53 +0100 47)      * 
11b88e0f (David A. Velasco 2012-12-19 14:12:53 +0100 48)      * @param remoetPath            Full path to the new directory to create in the remote server.
11b88e0f (David A. Velasco 2012-12-19 14:12:53 +0100 49)      * @param parentDirId           Local database id for the parent folder.
11b88e0f (David A. Velasco 2012-12-19 14:12:53 +0100 50)      * @param storageManager        Reference to the local database corresponding to the account where the file is contained. 
11b88e0f (David A. Velasco 2012-12-19 14:12:53 +0100 51)      */
11b88e0f (David A. Velasco 2012-12-19 14:12:53 +0100 52)     public CreateFolderOperation(String remotePath, long parentDirId, DataStorageManager storageManager) {
11b88e0f (David A. Velasco 2012-12-19 14:12:53 +0100 53)         mRemotePath = remotePath;
11b88e0f (David A. Velasco 2012-12-19 14:12:53 +0100 54)         mParentDirId = parentDirId;
11b88e0f (David A. Velasco 2012-12-19 14:12:53 +0100 55)         mStorageManager = storageManager;
11b88e0f (David A. Velasco 2012-12-19 14:12:53 +0100 56)     }
11b88e0f (David A. Velasco 2012-12-19 14:12:53 +0100 57)     
11b88e0f (David A. Velasco 2012-12-19 14:12:53 +0100 58)     
11b88e0f (David A. Velasco 2012-12-19 14:12:53 +0100 59)     /**
11b88e0f (David A. Velasco 2012-12-19 14:12:53 +0100 60)      * Performs the remove operation
11b88e0f (David A. Velasco 2012-12-19 14:12:53 +0100 61)      * 
11b88e0f (David A. Velasco 2012-12-19 14:12:53 +0100 62)      * @param   client      Client object to communicate with the remote ownCloud server.
11b88e0f (David A. Velasco 2012-12-19 14:12:53 +0100 63)      */
11b88e0f (David A. Velasco 2012-12-19 14:12:53 +0100 64)     @Override
11b88e0f (David A. Velasco 2012-12-19 14:12:53 +0100 65)     protected RemoteOperationResult run(WebdavClient client) {
11b88e0f (David A. Velasco 2012-12-19 14:12:53 +0100 66)         RemoteOperationResult result = null;
11b88e0f (David A. Velasco 2012-12-19 14:12:53 +0100 67)         MkColMethod mkcol = null;
11b88e0f (David A. Velasco 2012-12-19 14:12:53 +0100 68)         try {
11b88e0f (David A. Velasco 2012-12-19 14:12:53 +0100 69)             mkcol = new MkColMethod(client.getBaseUri() + WebdavUtils.encodePath(mRemotePath));
11b88e0f (David A. Velasco 2012-12-19 14:12:53 +0100 70)             int status =  client.executeMethod(mkcol, READ_TIMEOUT, CONNECTION_TIMEOUT);
11b88e0f (David A. Velasco 2012-12-19 14:12:53 +0100 71)             if (mkcol.succeeded()) {
11b88e0f (David A. Velasco 2012-12-19 14:12:53 +0100 72)                 // Save new directory in local database
11b88e0f (David A. Velasco 2012-12-19 14:12:53 +0100 73)                 OCFile newDir = new OCFile(mRemotePath);
11b88e0f (David A. Velasco 2012-12-19 14:12:53 +0100 74)                 newDir.setMimetype("DIR");
11b88e0f (David A. Velasco 2012-12-19 14:12:53 +0100 75)                 newDir.setParentId(mParentDirId);
a72d7bfe (masensio         2013-07-05 14:54:02 +0200 76)                 newDir.setModificationTimestamp(System.currentTimeMillis());
11b88e0f (David A. Velasco 2012-12-19 14:12:53 +0100 77)                 mStorageManager.saveFile(newDir);
11b88e0f (David A. Velasco 2012-12-19 14:12:53 +0100 78)             }
11b88e0f (David A. Velasco 2012-12-19 14:12:53 +0100 79) 
78bcf721 (David A. Velasco 2013-08-07 19:13:00 +0200 80)             result = new RemoteOperationResult(mkcol.succeeded(), status, mkcol.getResponseHeaders());
2946d8dd (David A. Velasco 2013-04-25 19:39:22 +0200 81)             Log_OC.d(TAG, "Create directory " + mRemotePath + ": " + result.getLogMessage());
11b88e0f (David A. Velasco 2012-12-19 14:12:53 +0100 82)             client.exhaustResponse(mkcol.getResponseBodyAsStream());
11b88e0f (David A. Velasco 2012-12-19 14:12:53 +0100 83)                 
11b88e0f (David A. Velasco 2012-12-19 14:12:53 +0100 84)         } catch (Exception e) {
11b88e0f (David A. Velasco 2012-12-19 14:12:53 +0100 85)             result = new RemoteOperationResult(e);
2946d8dd (David A. Velasco 2013-04-25 19:39:22 +0200 86)             Log_OC.e(TAG, "Create directory " + mRemotePath + ": " + result.getLogMessage(), e);
11b88e0f (David A. Velasco 2012-12-19 14:12:53 +0100 87)             
11b88e0f (David A. Velasco 2012-12-19 14:12:53 +0100 88)         } finally {
11b88e0f (David A. Velasco 2012-12-19 14:12:53 +0100 89)             if (mkcol != null)
11b88e0f (David A. Velasco 2012-12-19 14:12:53 +0100 90)                 mkcol.releaseConnection();
11b88e0f (David A. Velasco 2012-12-19 14:12:53 +0100 91)         }
11b88e0f (David A. Velasco 2012-12-19 14:12:53 +0100 92)         return result;
11b88e0f (David A. Velasco 2012-12-19 14:12:53 +0100 93)     }
11b88e0f (David A. Velasco 2012-12-19 14:12:53 +0100 94)     
11b88e0f (David A. Velasco 2012-12-19 14:12:53 +0100 95) }
