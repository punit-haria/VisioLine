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
0aeb4258 (Bartek Przybylski 2012-05-13 16:13:13 +0200  19) package eu.alefzero.webdav;
0aeb4258 (Bartek Przybylski 2012-05-13 16:13:13 +0200  20) 
0aeb4258 (Bartek Przybylski 2012-05-13 16:13:13 +0200  21) import java.io.File;
0aeb4258 (Bartek Przybylski 2012-05-13 16:13:13 +0200  22) import java.io.IOException;
0aeb4258 (Bartek Przybylski 2012-05-13 16:13:13 +0200  23) import java.io.OutputStream;
be22e58b (David A. Velasco  2012-08-24 14:50:37 +0200  24) import java.io.RandomAccessFile;
be22e58b (David A. Velasco  2012-08-24 14:50:37 +0200  25) import java.nio.ByteBuffer;
be22e58b (David A. Velasco  2012-08-24 14:50:37 +0200  26) import java.nio.channels.FileChannel;
68ce2e7a (David A. Velasco  2012-10-09 14:53:25 +0200  27) import java.util.Collection;
68ce2e7a (David A. Velasco  2012-10-09 14:53:25 +0200  28) import java.util.HashSet;
68ce2e7a (David A. Velasco  2012-10-09 14:53:25 +0200  29) import java.util.Iterator;
68ce2e7a (David A. Velasco  2012-10-09 14:53:25 +0200  30) import java.util.Set;
0aeb4258 (Bartek Przybylski 2012-05-13 16:13:13 +0200  31) 
0aeb4258 (Bartek Przybylski 2012-05-13 16:13:13 +0200  32) import org.apache.commons.httpclient.methods.RequestEntity;
0aeb4258 (Bartek Przybylski 2012-05-13 16:13:13 +0200  33) 
8e36e7cc (zerginator        2013-03-12 07:56:27 +0100  34) import com.owncloud.android.Log_OC;
24dd5136 (David A. Velasco  2013-02-19 13:28:01 +0100  35) import com.owncloud.android.network.ProgressiveDataTransferer;
8e36e7cc (zerginator        2013-03-12 07:56:27 +0100  36) 
99d52af2 (David A. Velasco  2012-08-16 16:06:12 +0200  37) import eu.alefzero.webdav.OnDatatransferProgressListener;
a4ba6170 (Bartek Przybylski 2012-07-31 17:43:37 +0200  38) 
edd38b80 (Bartek Przybylski 2012-05-26 14:30:14 +0200  39) 
0aeb4258 (Bartek Przybylski 2012-05-13 16:13:13 +0200  40) /**
0aeb4258 (Bartek Przybylski 2012-05-13 16:13:13 +0200  41)  * A RequestEntity that represents a File.
435b31ba (Lennart Rosam     2012-05-16 09:48:34 +0200  42)  * 
0aeb4258 (Bartek Przybylski 2012-05-13 16:13:13 +0200  43)  */
24dd5136 (David A. Velasco  2013-02-19 13:28:01 +0100  44) public class FileRequestEntity implements RequestEntity, ProgressiveDataTransferer {
0aeb4258 (Bartek Przybylski 2012-05-13 16:13:13 +0200  45) 
981bf054 (Bartek Przybylski 2012-09-29 23:55:19 +0200  46)     final File mFile;
981bf054 (Bartek Przybylski 2012-09-29 23:55:19 +0200  47)     final String mContentType;
27112bd2 (David A. Velasco  2012-10-11 16:40:56 +0200  48)     Set<OnDatatransferProgressListener> mDataTransferListeners = new HashSet<OnDatatransferProgressListener>();
0aeb4258 (Bartek Przybylski 2012-05-13 16:13:13 +0200  49) 
435b31ba (Lennart Rosam     2012-05-16 09:48:34 +0200  50)     public FileRequestEntity(final File file, final String contentType) {
435b31ba (Lennart Rosam     2012-05-16 09:48:34 +0200  51)         super();
981bf054 (Bartek Przybylski 2012-09-29 23:55:19 +0200  52)         this.mFile = file;
981bf054 (Bartek Przybylski 2012-09-29 23:55:19 +0200  53)         this.mContentType = contentType;
435b31ba (Lennart Rosam     2012-05-16 09:48:34 +0200  54)         if (file == null) {
435b31ba (Lennart Rosam     2012-05-16 09:48:34 +0200  55)             throw new IllegalArgumentException("File may not be null");
435b31ba (Lennart Rosam     2012-05-16 09:48:34 +0200  56)         }
0aeb4258 (Bartek Przybylski 2012-05-13 16:13:13 +0200  57)     }
35a384d0 (Bartek Przybylski 2012-05-19 23:30:23 +0200  58)     
981bf054 (Bartek Przybylski 2012-09-29 23:55:19 +0200  59)     @Override
435b31ba (Lennart Rosam     2012-05-16 09:48:34 +0200  60)     public long getContentLength() {
981bf054 (Bartek Przybylski 2012-09-29 23:55:19 +0200  61)         return mFile.length();
435b31ba (Lennart Rosam     2012-05-16 09:48:34 +0200  62)     }
435b31ba (Lennart Rosam     2012-05-16 09:48:34 +0200  63) 
981bf054 (Bartek Przybylski 2012-09-29 23:55:19 +0200  64)     @Override
435b31ba (Lennart Rosam     2012-05-16 09:48:34 +0200  65)     public String getContentType() {
981bf054 (Bartek Przybylski 2012-09-29 23:55:19 +0200  66)         return mContentType;
435b31ba (Lennart Rosam     2012-05-16 09:48:34 +0200  67)     }
435b31ba (Lennart Rosam     2012-05-16 09:48:34 +0200  68) 
981bf054 (Bartek Przybylski 2012-09-29 23:55:19 +0200  69)     @Override
435b31ba (Lennart Rosam     2012-05-16 09:48:34 +0200  70)     public boolean isRepeatable() {
435b31ba (Lennart Rosam     2012-05-16 09:48:34 +0200  71)         return true;
435b31ba (Lennart Rosam     2012-05-16 09:48:34 +0200  72)     }
24dd5136 (David A. Velasco  2013-02-19 13:28:01 +0100  73) 
24dd5136 (David A. Velasco  2013-02-19 13:28:01 +0100  74)     @Override
24dd5136 (David A. Velasco  2013-02-19 13:28:01 +0100  75)     public void addDatatransferProgressListener(OnDatatransferProgressListener listener) {
24dd5136 (David A. Velasco  2013-02-19 13:28:01 +0100  76)         synchronized (mDataTransferListeners) {
24dd5136 (David A. Velasco  2013-02-19 13:28:01 +0100  77)             mDataTransferListeners.add(listener);
24dd5136 (David A. Velasco  2013-02-19 13:28:01 +0100  78)         }
35a384d0 (Bartek Przybylski 2012-05-19 23:30:23 +0200  79)     }
53b67429 (David A. Velasco  2012-09-12 16:16:56 +0200  80)     
24dd5136 (David A. Velasco  2013-02-19 13:28:01 +0100  81)     @Override
24dd5136 (David A. Velasco  2013-02-19 13:28:01 +0100  82)     public void addDatatransferProgressListeners(Collection<OnDatatransferProgressListener> listeners) {
24dd5136 (David A. Velasco  2013-02-19 13:28:01 +0100  83)         synchronized (mDataTransferListeners) {
24dd5136 (David A. Velasco  2013-02-19 13:28:01 +0100  84)             mDataTransferListeners.addAll(listeners);
24dd5136 (David A. Velasco  2013-02-19 13:28:01 +0100  85)         }
68ce2e7a (David A. Velasco  2012-10-09 14:53:25 +0200  86)     }
68ce2e7a (David A. Velasco  2012-10-09 14:53:25 +0200  87)     
24dd5136 (David A. Velasco  2013-02-19 13:28:01 +0100  88)     @Override
24dd5136 (David A. Velasco  2013-02-19 13:28:01 +0100  89)     public void removeDatatransferProgressListener(OnDatatransferProgressListener listener) {
24dd5136 (David A. Velasco  2013-02-19 13:28:01 +0100  90)         synchronized (mDataTransferListeners) {
24dd5136 (David A. Velasco  2013-02-19 13:28:01 +0100  91)             mDataTransferListeners.remove(listener);
24dd5136 (David A. Velasco  2013-02-19 13:28:01 +0100  92)         }
68ce2e7a (David A. Velasco  2012-10-09 14:53:25 +0200  93)     }
68ce2e7a (David A. Velasco  2012-10-09 14:53:25 +0200  94)     
68ce2e7a (David A. Velasco  2012-10-09 14:53:25 +0200  95)     
981bf054 (Bartek Przybylski 2012-09-29 23:55:19 +0200  96)     @Override
435b31ba (Lennart Rosam     2012-05-16 09:48:34 +0200  97)     public void writeRequest(final OutputStream out) throws IOException {
be22e58b (David A. Velasco  2012-08-24 14:50:37 +0200  98)         //byte[] tmp = new byte[4096];
be22e58b (David A. Velasco  2012-08-24 14:50:37 +0200  99)         ByteBuffer tmp = ByteBuffer.allocate(4096);
27112bd2 (David A. Velasco  2012-10-11 16:40:56 +0200 100)         int readResult = 0;
be22e58b (David A. Velasco  2012-08-24 14:50:37 +0200 101)         
981bf054 (Bartek Przybylski 2012-09-29 23:55:19 +0200 102)         // TODO(bprzybylski): each mem allocation can throw OutOfMemoryError we need to handle it
981bf054 (Bartek Przybylski 2012-09-29 23:55:19 +0200 103)         //                    globally in some fashionable manner
6c323657 (Bartek Przybylski 2012-10-27 12:20:52 +0200 104)         RandomAccessFile raf = new RandomAccessFile(mFile, "r");
981bf054 (Bartek Przybylski 2012-09-29 23:55:19 +0200 105)         FileChannel channel = raf.getChannel();
68ce2e7a (David A. Velasco  2012-10-09 14:53:25 +0200 106)         Iterator<OnDatatransferProgressListener> it = null;
27112bd2 (David A. Velasco  2012-10-11 16:40:56 +0200 107)         long transferred = 0;
27112bd2 (David A. Velasco  2012-10-11 16:40:56 +0200 108)         long size = mFile.length();
27112bd2 (David A. Velasco  2012-10-11 16:40:56 +0200 109)         if (size == 0) size = -1;
435b31ba (Lennart Rosam     2012-05-16 09:48:34 +0200 110)         try {
27112bd2 (David A. Velasco  2012-10-11 16:40:56 +0200 111)             while ((readResult = channel.read(tmp)) >= 0) {
27112bd2 (David A. Velasco  2012-10-11 16:40:56 +0200 112)                 out.write(tmp.array(), 0, readResult);
be22e58b (David A. Velasco  2012-08-24 14:50:37 +0200 113)                 tmp.clear();
27112bd2 (David A. Velasco  2012-10-11 16:40:56 +0200 114)                 transferred += readResult;
24dd5136 (David A. Velasco  2013-02-19 13:28:01 +0100 115)                 synchronized (mDataTransferListeners) {
24dd5136 (David A. Velasco  2013-02-19 13:28:01 +0100 116)                     it = mDataTransferListeners.iterator();
24dd5136 (David A. Velasco  2013-02-19 13:28:01 +0100 117)                     while (it.hasNext()) {
24dd5136 (David A. Velasco  2013-02-19 13:28:01 +0100 118)                         it.next().onTransferProgress(readResult, transferred, size, mFile.getName());
24dd5136 (David A. Velasco  2013-02-19 13:28:01 +0100 119)                     }
68ce2e7a (David A. Velasco  2012-10-09 14:53:25 +0200 120)                 }
435b31ba (Lennart Rosam     2012-05-16 09:48:34 +0200 121)             }
68ce2e7a (David A. Velasco  2012-10-09 14:53:25 +0200 122)             
f3ba1075 (David A. Velasco  2012-07-26 18:35:09 +0200 123)         } catch (IOException io) {
8e36e7cc (zerginator        2013-03-12 07:56:27 +0100 124)             Log_OC.e("FileRequestException", io.getMessage());
f3ba1075 (David A. Velasco  2012-07-26 18:35:09 +0200 125)             throw new RuntimeException("Ugly solution to workaround the default policy of retries when the server falls while uploading ; temporal fix; really", io);   
f3ba1075 (David A. Velasco  2012-07-26 18:35:09 +0200 126)             
435b31ba (Lennart Rosam     2012-05-16 09:48:34 +0200 127)         } finally {
be22e58b (David A. Velasco  2012-08-24 14:50:37 +0200 128)             channel.close();
981bf054 (Bartek Przybylski 2012-09-29 23:55:19 +0200 129)             raf.close();
435b31ba (Lennart Rosam     2012-05-16 09:48:34 +0200 130)         }
0aeb4258 (Bartek Przybylski 2012-05-13 16:13:13 +0200 131)     }
0aeb4258 (Bartek Przybylski 2012-05-13 16:13:13 +0200 132) 
92080afe (Bartek Przybylski 2012-10-28 00:12:15 +0200 133) }
