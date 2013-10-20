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
53b67429 (David A. Velasco 2012-09-12 16:16:56 +0200  18) package eu.alefzero.webdav;
53b67429 (David A. Velasco 2012-09-12 16:16:56 +0200  19) 
27112bd2 (David A. Velasco 2012-10-11 16:40:56 +0200  20) import java.io.File;
53b67429 (David A. Velasco 2012-09-12 16:16:56 +0200  21) import java.io.IOException;
53b67429 (David A. Velasco 2012-09-12 16:16:56 +0200  22) import java.io.OutputStream;
53b67429 (David A. Velasco 2012-09-12 16:16:56 +0200  23) import java.nio.ByteBuffer;
53b67429 (David A. Velasco 2012-09-12 16:16:56 +0200  24) import java.nio.channels.FileChannel;
5fc7cd13 (David A. Velasco 2012-10-11 11:24:17 +0200  25) import java.util.Collection;
5fc7cd13 (David A. Velasco 2012-10-11 11:24:17 +0200  26) import java.util.HashSet;
5fc7cd13 (David A. Velasco 2012-10-11 11:24:17 +0200  27) import java.util.Iterator;
5fc7cd13 (David A. Velasco 2012-10-11 11:24:17 +0200  28) import java.util.Set;
53b67429 (David A. Velasco 2012-09-12 16:16:56 +0200  29) 
53b67429 (David A. Velasco 2012-09-12 16:16:56 +0200  30) import org.apache.commons.httpclient.methods.RequestEntity;
53b67429 (David A. Velasco 2012-09-12 16:16:56 +0200  31) 
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100  32) import com.owncloud.android.Log_OC;
24dd5136 (David A. Velasco 2013-02-19 13:28:01 +0100  33) import com.owncloud.android.network.ProgressiveDataTransferer;
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100  34) 
53b67429 (David A. Velasco 2012-09-12 16:16:56 +0200  35) import eu.alefzero.webdav.OnDatatransferProgressListener;
53b67429 (David A. Velasco 2012-09-12 16:16:56 +0200  36) 
53b67429 (David A. Velasco 2012-09-12 16:16:56 +0200  37) 
53b67429 (David A. Velasco 2012-09-12 16:16:56 +0200  38) /**
53b67429 (David A. Velasco 2012-09-12 16:16:56 +0200  39)  * A RequestEntity that represents a PIECE of a file.
53b67429 (David A. Velasco 2012-09-12 16:16:56 +0200  40)  * 
53b67429 (David A. Velasco 2012-09-12 16:16:56 +0200  41)  * @author David A. Velasco
53b67429 (David A. Velasco 2012-09-12 16:16:56 +0200  42)  */
24dd5136 (David A. Velasco 2013-02-19 13:28:01 +0100  43) public class ChunkFromFileChannelRequestEntity implements RequestEntity, ProgressiveDataTransferer {
53b67429 (David A. Velasco 2012-09-12 16:16:56 +0200  44) 
53b67429 (David A. Velasco 2012-09-12 16:16:56 +0200  45)     private static final String TAG = ChunkFromFileChannelRequestEntity.class.getSimpleName();
53b67429 (David A. Velasco 2012-09-12 16:16:56 +0200  46)     
53b67429 (David A. Velasco 2012-09-12 16:16:56 +0200  47)     //private final File mFile;
53b67429 (David A. Velasco 2012-09-12 16:16:56 +0200  48)     private final FileChannel mChannel;
53b67429 (David A. Velasco 2012-09-12 16:16:56 +0200  49)     private final String mContentType;
27112bd2 (David A. Velasco 2012-10-11 16:40:56 +0200  50)     private final long mChunkSize;
27112bd2 (David A. Velasco 2012-10-11 16:40:56 +0200  51)     private final File mFile;
53b67429 (David A. Velasco 2012-09-12 16:16:56 +0200  52)     private long mOffset;
27112bd2 (David A. Velasco 2012-10-11 16:40:56 +0200  53)     private long mTransferred;
27112bd2 (David A. Velasco 2012-10-11 16:40:56 +0200  54)     Set<OnDatatransferProgressListener> mDataTransferListeners = new HashSet<OnDatatransferProgressListener>();
53b67429 (David A. Velasco 2012-09-12 16:16:56 +0200  55)     private ByteBuffer mBuffer = ByteBuffer.allocate(4096);
53b67429 (David A. Velasco 2012-09-12 16:16:56 +0200  56) 
27112bd2 (David A. Velasco 2012-10-11 16:40:56 +0200  57)     public ChunkFromFileChannelRequestEntity(final FileChannel channel, final String contentType, long chunkSize, final File file) {
53b67429 (David A. Velasco 2012-09-12 16:16:56 +0200  58)         super();
53b67429 (David A. Velasco 2012-09-12 16:16:56 +0200  59)         if (channel == null) {
53b67429 (David A. Velasco 2012-09-12 16:16:56 +0200  60)             throw new IllegalArgumentException("File may not be null");
53b67429 (David A. Velasco 2012-09-12 16:16:56 +0200  61)         }
27112bd2 (David A. Velasco 2012-10-11 16:40:56 +0200  62)         if (chunkSize <= 0) {
27112bd2 (David A. Velasco 2012-10-11 16:40:56 +0200  63)             throw new IllegalArgumentException("Chunk size must be greater than zero");
53b67429 (David A. Velasco 2012-09-12 16:16:56 +0200  64)         }
53b67429 (David A. Velasco 2012-09-12 16:16:56 +0200  65)         mChannel = channel;
53b67429 (David A. Velasco 2012-09-12 16:16:56 +0200  66)         mContentType = contentType;
27112bd2 (David A. Velasco 2012-10-11 16:40:56 +0200  67)         mChunkSize = chunkSize;
27112bd2 (David A. Velasco 2012-10-11 16:40:56 +0200  68)         mFile = file;
53b67429 (David A. Velasco 2012-09-12 16:16:56 +0200  69)         mOffset = 0;
27112bd2 (David A. Velasco 2012-10-11 16:40:56 +0200  70)         mTransferred = 0;
53b67429 (David A. Velasco 2012-09-12 16:16:56 +0200  71)     }
53b67429 (David A. Velasco 2012-09-12 16:16:56 +0200  72)     
53b67429 (David A. Velasco 2012-09-12 16:16:56 +0200  73)     public void setOffset(long offset) {
53b67429 (David A. Velasco 2012-09-12 16:16:56 +0200  74)         mOffset = offset;
53b67429 (David A. Velasco 2012-09-12 16:16:56 +0200  75)     }
53b67429 (David A. Velasco 2012-09-12 16:16:56 +0200  76)     
53b67429 (David A. Velasco 2012-09-12 16:16:56 +0200  77)     public long getContentLength() {
53b67429 (David A. Velasco 2012-09-12 16:16:56 +0200  78)         try {
27112bd2 (David A. Velasco 2012-10-11 16:40:56 +0200  79)             return Math.min(mChunkSize, mChannel.size() - mChannel.position());
53b67429 (David A. Velasco 2012-09-12 16:16:56 +0200  80)         } catch (IOException e) {
27112bd2 (David A. Velasco 2012-10-11 16:40:56 +0200  81)             return mChunkSize;
53b67429 (David A. Velasco 2012-09-12 16:16:56 +0200  82)         }
53b67429 (David A. Velasco 2012-09-12 16:16:56 +0200  83)     }
53b67429 (David A. Velasco 2012-09-12 16:16:56 +0200  84) 
53b67429 (David A. Velasco 2012-09-12 16:16:56 +0200  85)     public String getContentType() {
53b67429 (David A. Velasco 2012-09-12 16:16:56 +0200  86)         return mContentType;
53b67429 (David A. Velasco 2012-09-12 16:16:56 +0200  87)     }
53b67429 (David A. Velasco 2012-09-12 16:16:56 +0200  88) 
53b67429 (David A. Velasco 2012-09-12 16:16:56 +0200  89)     public boolean isRepeatable() {
53b67429 (David A. Velasco 2012-09-12 16:16:56 +0200  90)         return true;
53b67429 (David A. Velasco 2012-09-12 16:16:56 +0200  91)     }
53b67429 (David A. Velasco 2012-09-12 16:16:56 +0200  92)     
24dd5136 (David A. Velasco 2013-02-19 13:28:01 +0100  93)     @Override
24dd5136 (David A. Velasco 2013-02-19 13:28:01 +0100  94)     public void addDatatransferProgressListener(OnDatatransferProgressListener listener) {
24dd5136 (David A. Velasco 2013-02-19 13:28:01 +0100  95)         synchronized (mDataTransferListeners) {
24dd5136 (David A. Velasco 2013-02-19 13:28:01 +0100  96)             mDataTransferListeners.add(listener);
24dd5136 (David A. Velasco 2013-02-19 13:28:01 +0100  97)         }
53b67429 (David A. Velasco 2012-09-12 16:16:56 +0200  98)     }
53b67429 (David A. Velasco 2012-09-12 16:16:56 +0200  99)     
24dd5136 (David A. Velasco 2013-02-19 13:28:01 +0100 100)     @Override
24dd5136 (David A. Velasco 2013-02-19 13:28:01 +0100 101)     public void addDatatransferProgressListeners(Collection<OnDatatransferProgressListener> listeners) {
24dd5136 (David A. Velasco 2013-02-19 13:28:01 +0100 102)         synchronized (mDataTransferListeners) {
24dd5136 (David A. Velasco 2013-02-19 13:28:01 +0100 103)             mDataTransferListeners.addAll(listeners);
24dd5136 (David A. Velasco 2013-02-19 13:28:01 +0100 104)         }
5fc7cd13 (David A. Velasco 2012-10-11 11:24:17 +0200 105)     }
5fc7cd13 (David A. Velasco 2012-10-11 11:24:17 +0200 106)     
24dd5136 (David A. Velasco 2013-02-19 13:28:01 +0100 107)     @Override
24dd5136 (David A. Velasco 2013-02-19 13:28:01 +0100 108)     public void removeDatatransferProgressListener(OnDatatransferProgressListener listener) {
24dd5136 (David A. Velasco 2013-02-19 13:28:01 +0100 109)         synchronized (mDataTransferListeners) {
24dd5136 (David A. Velasco 2013-02-19 13:28:01 +0100 110)             mDataTransferListeners.remove(listener);
24dd5136 (David A. Velasco 2013-02-19 13:28:01 +0100 111)         }
5fc7cd13 (David A. Velasco 2012-10-11 11:24:17 +0200 112)     }
5fc7cd13 (David A. Velasco 2012-10-11 11:24:17 +0200 113)     
5fc7cd13 (David A. Velasco 2012-10-11 11:24:17 +0200 114)     
53b67429 (David A. Velasco 2012-09-12 16:16:56 +0200 115)     public void writeRequest(final OutputStream out) throws IOException {
53b67429 (David A. Velasco 2012-09-12 16:16:56 +0200 116)         int readCount = 0;
5fc7cd13 (David A. Velasco 2012-10-11 11:24:17 +0200 117)         Iterator<OnDatatransferProgressListener> it = null;
53b67429 (David A. Velasco 2012-09-12 16:16:56 +0200 118)         
53b67429 (David A. Velasco 2012-09-12 16:16:56 +0200 119)        try {
53b67429 (David A. Velasco 2012-09-12 16:16:56 +0200 120)             mChannel.position(mOffset);
27112bd2 (David A. Velasco 2012-10-11 16:40:56 +0200 121)             long size = mFile.length();
27112bd2 (David A. Velasco 2012-10-11 16:40:56 +0200 122)             if (size == 0) size = -1;
27112bd2 (David A. Velasco 2012-10-11 16:40:56 +0200 123)             while (mChannel.position() < mOffset + mChunkSize && mChannel.position() < mChannel.size()) {
53b67429 (David A. Velasco 2012-09-12 16:16:56 +0200 124)                 readCount = mChannel.read(mBuffer);
53b67429 (David A. Velasco 2012-09-12 16:16:56 +0200 125)                 out.write(mBuffer.array(), 0, readCount);
53b67429 (David A. Velasco 2012-09-12 16:16:56 +0200 126)                 mBuffer.clear();
27112bd2 (David A. Velasco 2012-10-11 16:40:56 +0200 127)                 mTransferred += readCount;
24dd5136 (David A. Velasco 2013-02-19 13:28:01 +0100 128)                 synchronized (mDataTransferListeners) {
24dd5136 (David A. Velasco 2013-02-19 13:28:01 +0100 129)                     it = mDataTransferListeners.iterator();
24dd5136 (David A. Velasco 2013-02-19 13:28:01 +0100 130)                     while (it.hasNext()) {
24dd5136 (David A. Velasco 2013-02-19 13:28:01 +0100 131)                         it.next().onTransferProgress(readCount, mTransferred, size, mFile.getName());
24dd5136 (David A. Velasco 2013-02-19 13:28:01 +0100 132)                     }
5fc7cd13 (David A. Velasco 2012-10-11 11:24:17 +0200 133)                 }
53b67429 (David A. Velasco 2012-09-12 16:16:56 +0200 134)             }
53b67429 (David A. Velasco 2012-09-12 16:16:56 +0200 135)             
53b67429 (David A. Velasco 2012-09-12 16:16:56 +0200 136)         } catch (IOException io) {
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100 137)             Log_OC.e(TAG, io.getMessage());
53b67429 (David A. Velasco 2012-09-12 16:16:56 +0200 138)             throw new RuntimeException("Ugly solution to workaround the default policy of retries when the server falls while uploading ; temporal fix; really", io);   
53b67429 (David A. Velasco 2012-09-12 16:16:56 +0200 139)             
53b67429 (David A. Velasco 2012-09-12 16:16:56 +0200 140)         }
53b67429 (David A. Velasco 2012-09-12 16:16:56 +0200 141)     }
53b67429 (David A. Velasco 2012-09-12 16:16:56 +0200 142) 
53b67429 (David A. Velasco 2012-09-12 16:16:56 +0200 143) }
