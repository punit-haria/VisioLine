900563e2 (David A. Velasco 2012-10-25 14:21:01 +0200   1) /* ownCloud Android client application
bb257ec7 (David A. Velasco 2013-02-07 18:45:10 +0100   2)  *   Copyright (C) 2012-2013 ownCloud Inc.
900563e2 (David A. Velasco 2012-10-25 14:21:01 +0200   3)  *
900563e2 (David A. Velasco 2012-10-25 14:21:01 +0200   4)  *   This program is free software: you can redistribute it and/or modify
bdc0332c (David A. Velasco 2013-04-17 12:26:13 +0200   5)  *   it under the terms of the GNU General Public License version 2,
bdc0332c (David A. Velasco 2013-04-17 12:26:13 +0200   6)  *   as published by the Free Software Foundation.
900563e2 (David A. Velasco 2012-10-25 14:21:01 +0200   7)  *
900563e2 (David A. Velasco 2012-10-25 14:21:01 +0200   8)  *   This program is distributed in the hope that it will be useful,
900563e2 (David A. Velasco 2012-10-25 14:21:01 +0200   9)  *   but WITHOUT ANY WARRANTY; without even the implied warranty of
900563e2 (David A. Velasco 2012-10-25 14:21:01 +0200  10)  *   MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
900563e2 (David A. Velasco 2012-10-25 14:21:01 +0200  11)  *   GNU General Public License for more details.
900563e2 (David A. Velasco 2012-10-25 14:21:01 +0200  12)  *
900563e2 (David A. Velasco 2012-10-25 14:21:01 +0200  13)  *   You should have received a copy of the GNU General Public License
900563e2 (David A. Velasco 2012-10-25 14:21:01 +0200  14)  *   along with this program.  If not, see <http://www.gnu.org/licenses/>.
900563e2 (David A. Velasco 2012-10-25 14:21:01 +0200  15)  *
900563e2 (David A. Velasco 2012-10-25 14:21:01 +0200  16)  */
900563e2 (David A. Velasco 2012-10-25 14:21:01 +0200  17) 
900563e2 (David A. Velasco 2012-10-25 14:21:01 +0200  18) package com.owncloud.android.operations;
900563e2 (David A. Velasco 2012-10-25 14:21:01 +0200  19) 
60d0a121 (David A. Velasco 2012-11-22 13:35:07 +0100  20) import org.apache.commons.httpclient.HttpStatus;
900563e2 (David A. Velasco 2012-10-25 14:21:01 +0200  21) import org.apache.jackrabbit.webdav.client.methods.DeleteMethod;
900563e2 (David A. Velasco 2012-10-25 14:21:01 +0200  22) 
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100  23) import com.owncloud.android.Log_OC;
900563e2 (David A. Velasco 2012-10-25 14:21:01 +0200  24) import com.owncloud.android.datamodel.DataStorageManager;
900563e2 (David A. Velasco 2012-10-25 14:21:01 +0200  25) import com.owncloud.android.datamodel.OCFile;
900563e2 (David A. Velasco 2012-10-25 14:21:01 +0200  26) 
900563e2 (David A. Velasco 2012-10-25 14:21:01 +0200  27) import eu.alefzero.webdav.WebdavClient;
900563e2 (David A. Velasco 2012-10-25 14:21:01 +0200  28) import eu.alefzero.webdav.WebdavUtils;
900563e2 (David A. Velasco 2012-10-25 14:21:01 +0200  29) 
900563e2 (David A. Velasco 2012-10-25 14:21:01 +0200  30) /**
900563e2 (David A. Velasco 2012-10-25 14:21:01 +0200  31)  * Remote operation performing the removal of a remote file or folder in the ownCloud server.
900563e2 (David A. Velasco 2012-10-25 14:21:01 +0200  32)  * 
900563e2 (David A. Velasco 2012-10-25 14:21:01 +0200  33)  * @author David A. Velasco
900563e2 (David A. Velasco 2012-10-25 14:21:01 +0200  34)  */
900563e2 (David A. Velasco 2012-10-25 14:21:01 +0200  35) public class RemoveFileOperation extends RemoteOperation {
900563e2 (David A. Velasco 2012-10-25 14:21:01 +0200  36)     
c6a3abf0 (David A. Velasco 2012-10-26 13:45:59 +0200  37)     private static final String TAG = RemoveFileOperation.class.getSimpleName();
900563e2 (David A. Velasco 2012-10-25 14:21:01 +0200  38) 
900563e2 (David A. Velasco 2012-10-25 14:21:01 +0200  39)     private static final int REMOVE_READ_TIMEOUT = 10000;
900563e2 (David A. Velasco 2012-10-25 14:21:01 +0200  40)     private static final int REMOVE_CONNECTION_TIMEOUT = 5000;
900563e2 (David A. Velasco 2012-10-25 14:21:01 +0200  41)     
900563e2 (David A. Velasco 2012-10-25 14:21:01 +0200  42)     OCFile mFileToRemove;
900563e2 (David A. Velasco 2012-10-25 14:21:01 +0200  43)     boolean mDeleteLocalCopy;
900563e2 (David A. Velasco 2012-10-25 14:21:01 +0200  44)     DataStorageManager mDataStorageManager;
900563e2 (David A. Velasco 2012-10-25 14:21:01 +0200  45)     
900563e2 (David A. Velasco 2012-10-25 14:21:01 +0200  46)     
900563e2 (David A. Velasco 2012-10-25 14:21:01 +0200  47)     /**
900563e2 (David A. Velasco 2012-10-25 14:21:01 +0200  48)      * Constructor
900563e2 (David A. Velasco 2012-10-25 14:21:01 +0200  49)      * 
900563e2 (David A. Velasco 2012-10-25 14:21:01 +0200  50)      * @param fileToRemove          OCFile instance describing the remote file or folder to remove from the server
900563e2 (David A. Velasco 2012-10-25 14:21:01 +0200  51)      * @param deleteLocalCopy       When 'true', and a local copy of the file exists, it is also removed.
900563e2 (David A. Velasco 2012-10-25 14:21:01 +0200  52)      * @param storageManager        Reference to the local database corresponding to the account where the file is contained. 
900563e2 (David A. Velasco 2012-10-25 14:21:01 +0200  53)      */
900563e2 (David A. Velasco 2012-10-25 14:21:01 +0200  54)     public RemoveFileOperation(OCFile fileToRemove, boolean deleteLocalCopy, DataStorageManager storageManager) {
900563e2 (David A. Velasco 2012-10-25 14:21:01 +0200  55)         mFileToRemove = fileToRemove;
900563e2 (David A. Velasco 2012-10-25 14:21:01 +0200  56)         mDeleteLocalCopy = deleteLocalCopy;
900563e2 (David A. Velasco 2012-10-25 14:21:01 +0200  57)         mDataStorageManager = storageManager;
900563e2 (David A. Velasco 2012-10-25 14:21:01 +0200  58)     }
900563e2 (David A. Velasco 2012-10-25 14:21:01 +0200  59)     
900563e2 (David A. Velasco 2012-10-25 14:21:01 +0200  60)     
900563e2 (David A. Velasco 2012-10-25 14:21:01 +0200  61)     /**
85e9a40d (David A. Velasco 2012-11-08 17:21:45 +0100  62)      * Getter for the file to remove (or removed, if the operation was successfully performed).
85e9a40d (David A. Velasco 2012-11-08 17:21:45 +0100  63)      * 
85e9a40d (David A. Velasco 2012-11-08 17:21:45 +0100  64)      * @return      File to remove or already removed.
85e9a40d (David A. Velasco 2012-11-08 17:21:45 +0100  65)      */
85e9a40d (David A. Velasco 2012-11-08 17:21:45 +0100  66)     public OCFile getFile() {
85e9a40d (David A. Velasco 2012-11-08 17:21:45 +0100  67)         return mFileToRemove;
85e9a40d (David A. Velasco 2012-11-08 17:21:45 +0100  68)     }
85e9a40d (David A. Velasco 2012-11-08 17:21:45 +0100  69)     
85e9a40d (David A. Velasco 2012-11-08 17:21:45 +0100  70)     
85e9a40d (David A. Velasco 2012-11-08 17:21:45 +0100  71)     /**
5af0fb44 (David A. Velasco 2012-10-26 11:34:20 +0200  72)      * Performs the remove operation
5af0fb44 (David A. Velasco 2012-10-26 11:34:20 +0200  73)      * 
5af0fb44 (David A. Velasco 2012-10-26 11:34:20 +0200  74)      * @param   client      Client object to communicate with the remote ownCloud server.
900563e2 (David A. Velasco 2012-10-25 14:21:01 +0200  75)      */
900563e2 (David A. Velasco 2012-10-25 14:21:01 +0200  76)     @Override
900563e2 (David A. Velasco 2012-10-25 14:21:01 +0200  77)     protected RemoteOperationResult run(WebdavClient client) {
900563e2 (David A. Velasco 2012-10-25 14:21:01 +0200  78)         RemoteOperationResult result = null;
900563e2 (David A. Velasco 2012-10-25 14:21:01 +0200  79)         DeleteMethod delete = null;
900563e2 (David A. Velasco 2012-10-25 14:21:01 +0200  80)         try {
900563e2 (David A. Velasco 2012-10-25 14:21:01 +0200  81)             delete = new DeleteMethod(client.getBaseUri() + WebdavUtils.encodePath(mFileToRemove.getRemotePath()));
900563e2 (David A. Velasco 2012-10-25 14:21:01 +0200  82)             int status = client.executeMethod(delete, REMOVE_READ_TIMEOUT, REMOVE_CONNECTION_TIMEOUT);
60d0a121 (David A. Velasco 2012-11-22 13:35:07 +0100  83)             if (delete.succeeded() || status == HttpStatus.SC_NOT_FOUND) {
b27ebf03 (David A. Velasco 2012-11-05 15:44:09 +0100  84)                 if (mFileToRemove.isDirectory()) {
b27ebf03 (David A. Velasco 2012-11-05 15:44:09 +0100  85)                     mDataStorageManager.removeDirectory(mFileToRemove, true, mDeleteLocalCopy);
b27ebf03 (David A. Velasco 2012-11-05 15:44:09 +0100  86)                 } else {
b27ebf03 (David A. Velasco 2012-11-05 15:44:09 +0100  87)                     mDataStorageManager.removeFile(mFileToRemove, mDeleteLocalCopy);
b27ebf03 (David A. Velasco 2012-11-05 15:44:09 +0100  88)                 }
900563e2 (David A. Velasco 2012-10-25 14:21:01 +0200  89)             }
900563e2 (David A. Velasco 2012-10-25 14:21:01 +0200  90)             delete.getResponseBodyAsString();   // exhaust the response, although not interesting
78bcf721 (David A. Velasco 2013-08-07 19:13:00 +0200  91)             result = new RemoteOperationResult((delete.succeeded() || status == HttpStatus.SC_NOT_FOUND), status, delete.getResponseHeaders());
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100  92)             Log_OC.i(TAG, "Remove " + mFileToRemove.getRemotePath() + ": " + result.getLogMessage());
900563e2 (David A. Velasco 2012-10-25 14:21:01 +0200  93)             
900563e2 (David A. Velasco 2012-10-25 14:21:01 +0200  94)         } catch (Exception e) {
900563e2 (David A. Velasco 2012-10-25 14:21:01 +0200  95)             result = new RemoteOperationResult(e);
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100  96)             Log_OC.e(TAG, "Remove " + mFileToRemove.getRemotePath() + ": " + result.getLogMessage(), e);
900563e2 (David A. Velasco 2012-10-25 14:21:01 +0200  97)             
900563e2 (David A. Velasco 2012-10-25 14:21:01 +0200  98)         } finally {
900563e2 (David A. Velasco 2012-10-25 14:21:01 +0200  99)             if (delete != null)
900563e2 (David A. Velasco 2012-10-25 14:21:01 +0200 100)                 delete.releaseConnection();
900563e2 (David A. Velasco 2012-10-25 14:21:01 +0200 101)         }
900563e2 (David A. Velasco 2012-10-25 14:21:01 +0200 102)         return result;
900563e2 (David A. Velasco 2012-10-25 14:21:01 +0200 103)     }
900563e2 (David A. Velasco 2012-10-25 14:21:01 +0200 104)     
900563e2 (David A. Velasco 2012-10-25 14:21:01 +0200 105) }
