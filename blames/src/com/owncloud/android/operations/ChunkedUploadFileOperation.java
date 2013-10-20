53b67429 (David A. Velasco  2012-09-12 16:16:56 +0200  1) /* ownCloud Android client application
53b67429 (David A. Velasco  2012-09-12 16:16:56 +0200  2)  *   Copyright (C) 2012 Bartek Przybylski
bb257ec7 (David A. Velasco  2013-02-07 18:45:10 +0100  3)  *   Copyright (C) 2012-2013 ownCloud Inc.
53b67429 (David A. Velasco  2012-09-12 16:16:56 +0200  4)  *
53b67429 (David A. Velasco  2012-09-12 16:16:56 +0200  5)  *   This program is free software: you can redistribute it and/or modify
bdc0332c (David A. Velasco  2013-04-17 12:26:13 +0200  6)  *   it under the terms of the GNU General Public License version 2,
bdc0332c (David A. Velasco  2013-04-17 12:26:13 +0200  7)  *   as published by the Free Software Foundation.
53b67429 (David A. Velasco  2012-09-12 16:16:56 +0200  8)  *
53b67429 (David A. Velasco  2012-09-12 16:16:56 +0200  9)  *   This program is distributed in the hope that it will be useful,
53b67429 (David A. Velasco  2012-09-12 16:16:56 +0200 10)  *   but WITHOUT ANY WARRANTY; without even the implied warranty of
53b67429 (David A. Velasco  2012-09-12 16:16:56 +0200 11)  *   MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
53b67429 (David A. Velasco  2012-09-12 16:16:56 +0200 12)  *   GNU General Public License for more details.
53b67429 (David A. Velasco  2012-09-12 16:16:56 +0200 13)  *
53b67429 (David A. Velasco  2012-09-12 16:16:56 +0200 14)  *   You should have received a copy of the GNU General Public License
53b67429 (David A. Velasco  2012-09-12 16:16:56 +0200 15)  *   along with this program.  If not, see <http://www.gnu.org/licenses/>.
53b67429 (David A. Velasco  2012-09-12 16:16:56 +0200 16)  *
53b67429 (David A. Velasco  2012-09-12 16:16:56 +0200 17)  */
53b67429 (David A. Velasco  2012-09-12 16:16:56 +0200 18) 
53b67429 (David A. Velasco  2012-09-12 16:16:56 +0200 19) package com.owncloud.android.operations;
53b67429 (David A. Velasco  2012-09-12 16:16:56 +0200 20) 
53b67429 (David A. Velasco  2012-09-12 16:16:56 +0200 21) import java.io.File;
53b67429 (David A. Velasco  2012-09-12 16:16:56 +0200 22) import java.io.IOException;
53b67429 (David A. Velasco  2012-09-12 16:16:56 +0200 23) import java.io.RandomAccessFile;
53b67429 (David A. Velasco  2012-09-12 16:16:56 +0200 24) import java.nio.channels.FileChannel;
53b67429 (David A. Velasco  2012-09-12 16:16:56 +0200 25) import java.util.Random;
53b67429 (David A. Velasco  2012-09-12 16:16:56 +0200 26) 
53b67429 (David A. Velasco  2012-09-12 16:16:56 +0200 27) import org.apache.commons.httpclient.HttpException;
53b67429 (David A. Velasco  2012-09-12 16:16:56 +0200 28) import org.apache.commons.httpclient.methods.PutMethod;
53b67429 (David A. Velasco  2012-09-12 16:16:56 +0200 29) 
8e36e7cc (zerginator        2013-03-12 07:56:27 +0100 30) import com.owncloud.android.Log_OC;
27112bd2 (David A. Velasco  2012-10-11 16:40:56 +0200 31) import com.owncloud.android.datamodel.OCFile;
24dd5136 (David A. Velasco  2013-02-19 13:28:01 +0100 32) import com.owncloud.android.network.ProgressiveDataTransferer;
27112bd2 (David A. Velasco  2012-10-11 16:40:56 +0200 33) 
5fc7cd13 (David A. Velasco  2012-10-11 11:24:17 +0200 34) import android.accounts.Account;
b00957b5 (David A. Velasco  2012-09-24 14:10:31 +0200 35) 
53b67429 (David A. Velasco  2012-09-12 16:16:56 +0200 36) import eu.alefzero.webdav.ChunkFromFileChannelRequestEntity;
53b67429 (David A. Velasco  2012-09-12 16:16:56 +0200 37) import eu.alefzero.webdav.WebdavClient;
53b67429 (David A. Velasco  2012-09-12 16:16:56 +0200 38) import eu.alefzero.webdav.WebdavUtils;
53b67429 (David A. Velasco  2012-09-12 16:16:56 +0200 39) 
53b67429 (David A. Velasco  2012-09-12 16:16:56 +0200 40) public class ChunkedUploadFileOperation extends UploadFileOperation {
53b67429 (David A. Velasco  2012-09-12 16:16:56 +0200 41)     
7ebcda4d (David A. Velasco  2012-10-26 15:42:05 +0200 42)     private static final long CHUNK_SIZE = 1024000;
53b67429 (David A. Velasco  2012-09-12 16:16:56 +0200 43)     private static final String OC_CHUNKED_HEADER = "OC-Chunked";
b00957b5 (David A. Velasco  2012-09-24 14:10:31 +0200 44)     private static final String TAG = ChunkedUploadFileOperation.class.getSimpleName();
53b67429 (David A. Velasco  2012-09-12 16:16:56 +0200 45) 
5fc7cd13 (David A. Velasco  2012-10-11 11:24:17 +0200 46)     public ChunkedUploadFileOperation(  Account account,
27112bd2 (David A. Velasco  2012-10-11 16:40:56 +0200 47)                                         OCFile file,
53b67429 (David A. Velasco  2012-09-12 16:16:56 +0200 48)                                         boolean isInstant, 
fd9086a8 (David A. Velasco  2012-11-27 14:28:19 +0100 49)                                         boolean forceOverwrite,
a1c538db (David A. Velasco  2012-11-27 15:15:17 +0100 50)                                         int localBehaviour) {
53b67429 (David A. Velasco  2012-09-12 16:16:56 +0200 51)         
a1c538db (David A. Velasco  2012-11-27 15:15:17 +0100 52)         super(account, file, isInstant, forceOverwrite, localBehaviour);
53b67429 (David A. Velasco  2012-09-12 16:16:56 +0200 53)     }
53b67429 (David A. Velasco  2012-09-12 16:16:56 +0200 54) 
53b67429 (David A. Velasco  2012-09-12 16:16:56 +0200 55)     @Override
53b67429 (David A. Velasco  2012-09-12 16:16:56 +0200 56)     protected int uploadFile(WebdavClient client) throws HttpException, IOException {
53b67429 (David A. Velasco  2012-09-12 16:16:56 +0200 57)         int status = -1;
53b67429 (David A. Velasco  2012-09-12 16:16:56 +0200 58) 
53b67429 (David A. Velasco  2012-09-12 16:16:56 +0200 59)         FileChannel channel = null;
981bf054 (Bartek Przybylski 2012-09-29 23:55:19 +0200 60)         RandomAccessFile raf = null;
53b67429 (David A. Velasco  2012-09-12 16:16:56 +0200 61)         try {
27112bd2 (David A. Velasco  2012-10-11 16:40:56 +0200 62)             File file = new File(getStoragePath());
6c323657 (Bartek Przybylski 2012-10-27 12:20:52 +0200 63)             raf = new RandomAccessFile(file, "r");
981bf054 (Bartek Przybylski 2012-09-29 23:55:19 +0200 64)             channel = raf.getChannel();
24dd5136 (David A. Velasco  2013-02-19 13:28:01 +0100 65)             mEntity = new ChunkFromFileChannelRequestEntity(channel, getMimeType(), CHUNK_SIZE, file);
24dd5136 (David A. Velasco  2013-02-19 13:28:01 +0100 66)             ((ProgressiveDataTransferer)mEntity).addDatatransferProgressListeners(getDataTransferListeners());
53b67429 (David A. Velasco  2012-09-12 16:16:56 +0200 67)             long offset = 0;
b00957b5 (David A. Velasco  2012-09-24 14:10:31 +0200 68)             String uriPrefix = client.getBaseUri() + WebdavUtils.encodePath(getRemotePath()) + "-chunking-" + Math.abs((new Random()).nextInt(9000)+1000) + "-" ;
53b67429 (David A. Velasco  2012-09-12 16:16:56 +0200 69)             long chunkCount = (long) Math.ceil((double)file.length() / CHUNK_SIZE);
53b67429 (David A. Velasco  2012-09-12 16:16:56 +0200 70)             for (int chunkIndex = 0; chunkIndex < chunkCount ; chunkIndex++, offset += CHUNK_SIZE) {
78bcf721 (David A. Velasco  2013-08-07 19:13:00 +0200 71)                 if (mPutMethod != null) {
78bcf721 (David A. Velasco  2013-08-07 19:13:00 +0200 72)                     mPutMethod.releaseConnection();    // let the connection available for other methods
78bcf721 (David A. Velasco  2013-08-07 19:13:00 +0200 73)                 }
27112bd2 (David A. Velasco  2012-10-11 16:40:56 +0200 74)                 mPutMethod = new PutMethod(uriPrefix + chunkCount + "-" + chunkIndex);
27112bd2 (David A. Velasco  2012-10-11 16:40:56 +0200 75)                 mPutMethod.addRequestHeader(OC_CHUNKED_HEADER, OC_CHUNKED_HEADER);
24dd5136 (David A. Velasco  2013-02-19 13:28:01 +0100 76)                 ((ChunkFromFileChannelRequestEntity)mEntity).setOffset(offset);
24dd5136 (David A. Velasco  2013-02-19 13:28:01 +0100 77)                 mPutMethod.setRequestEntity(mEntity);
27112bd2 (David A. Velasco  2012-10-11 16:40:56 +0200 78)                 status = client.executeMethod(mPutMethod);
27112bd2 (David A. Velasco  2012-10-11 16:40:56 +0200 79)                 client.exhaustResponse(mPutMethod.getResponseBodyAsStream());
8e36e7cc (zerginator        2013-03-12 07:56:27 +0100 80)                 Log_OC.d(TAG, "Upload of " + getStoragePath() + " to " + getRemotePath() + ", chunk index " + chunkIndex + ", count " + chunkCount + ", HTTP result status " + status);
53b67429 (David A. Velasco  2012-09-12 16:16:56 +0200 81)                 if (!isSuccess(status))
53b67429 (David A. Velasco  2012-09-12 16:16:56 +0200 82)                     break;
53b67429 (David A. Velasco  2012-09-12 16:16:56 +0200 83)             }
53b67429 (David A. Velasco  2012-09-12 16:16:56 +0200 84)             
53b67429 (David A. Velasco  2012-09-12 16:16:56 +0200 85)         } finally {
53b67429 (David A. Velasco  2012-09-12 16:16:56 +0200 86)             if (channel != null)
53b67429 (David A. Velasco  2012-09-12 16:16:56 +0200 87)                 channel.close();
981bf054 (Bartek Przybylski 2012-09-29 23:55:19 +0200 88)             if (raf != null)
981bf054 (Bartek Przybylski 2012-09-29 23:55:19 +0200 89)                 raf.close();
27112bd2 (David A. Velasco  2012-10-11 16:40:56 +0200 90)             if (mPutMethod != null)
27112bd2 (David A. Velasco  2012-10-11 16:40:56 +0200 91)                 mPutMethod.releaseConnection();    // let the connection available for other methods
53b67429 (David A. Velasco  2012-09-12 16:16:56 +0200 92)         }
53b67429 (David A. Velasco  2012-09-12 16:16:56 +0200 93)         return status;
53b67429 (David A. Velasco  2012-09-12 16:16:56 +0200 94)     }
53b67429 (David A. Velasco  2012-09-12 16:16:56 +0200 95) 
53b67429 (David A. Velasco  2012-09-12 16:16:56 +0200 96) }
