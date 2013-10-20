b7ca8521 src/eu/alefzero/owncloud/datamodel/DataStorageManager.java (Bartek Przybylski 2012-04-14 22:28:55 +0200  1) /* ownCloud Android client application
b7ca8521 src/eu/alefzero/owncloud/datamodel/DataStorageManager.java (Bartek Przybylski 2012-04-14 22:28:55 +0200  2)  *   Copyright (C) 2012  Bartek Przybylski
bb257ec7 src/com/owncloud/android/datamodel/DataStorageManager.java (David A. Velasco  2013-02-07 18:45:10 +0100  3)  *   Copyright (C) 2012-2013 ownCloud Inc.
b7ca8521 src/eu/alefzero/owncloud/datamodel/DataStorageManager.java (Bartek Przybylski 2012-04-14 22:28:55 +0200  4)  *
b7ca8521 src/eu/alefzero/owncloud/datamodel/DataStorageManager.java (Bartek Przybylski 2012-04-14 22:28:55 +0200  5)  *   This program is free software: you can redistribute it and/or modify
bdc0332c src/com/owncloud/android/datamodel/DataStorageManager.java (David A. Velasco  2013-04-17 12:26:13 +0200  6)  *   it under the terms of the GNU General Public License version 2,
bdc0332c src/com/owncloud/android/datamodel/DataStorageManager.java (David A. Velasco  2013-04-17 12:26:13 +0200  7)  *   as published by the Free Software Foundation.
b7ca8521 src/eu/alefzero/owncloud/datamodel/DataStorageManager.java (Bartek Przybylski 2012-04-14 22:28:55 +0200  8)  *
b7ca8521 src/eu/alefzero/owncloud/datamodel/DataStorageManager.java (Bartek Przybylski 2012-04-14 22:28:55 +0200  9)  *   This program is distributed in the hope that it will be useful,
b7ca8521 src/eu/alefzero/owncloud/datamodel/DataStorageManager.java (Bartek Przybylski 2012-04-14 22:28:55 +0200 10)  *   but WITHOUT ANY WARRANTY; without even the implied warranty of
b7ca8521 src/eu/alefzero/owncloud/datamodel/DataStorageManager.java (Bartek Przybylski 2012-04-14 22:28:55 +0200 11)  *   MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
b7ca8521 src/eu/alefzero/owncloud/datamodel/DataStorageManager.java (Bartek Przybylski 2012-04-14 22:28:55 +0200 12)  *   GNU General Public License for more details.
b7ca8521 src/eu/alefzero/owncloud/datamodel/DataStorageManager.java (Bartek Przybylski 2012-04-14 22:28:55 +0200 13)  *
b7ca8521 src/eu/alefzero/owncloud/datamodel/DataStorageManager.java (Bartek Przybylski 2012-04-14 22:28:55 +0200 14)  *   You should have received a copy of the GNU General Public License
b7ca8521 src/eu/alefzero/owncloud/datamodel/DataStorageManager.java (Bartek Przybylski 2012-04-14 22:28:55 +0200 15)  *   along with this program.  If not, see <http://www.gnu.org/licenses/>.
b7ca8521 src/eu/alefzero/owncloud/datamodel/DataStorageManager.java (Bartek Przybylski 2012-04-14 22:28:55 +0200 16)  *
b7ca8521 src/eu/alefzero/owncloud/datamodel/DataStorageManager.java (Bartek Przybylski 2012-04-14 22:28:55 +0200 17)  */
b7ca8521 src/eu/alefzero/owncloud/datamodel/DataStorageManager.java (Bartek Przybylski 2012-04-14 22:28:55 +0200 18) 
a4ba6170 src/com/owncloud/android/datamodel/DataStorageManager.java (Bartek Przybylski 2012-07-31 17:43:37 +0200 19) package com.owncloud.android.datamodel;
b7ca8521 src/eu/alefzero/owncloud/datamodel/DataStorageManager.java (Bartek Przybylski 2012-04-14 22:28:55 +0200 20) 
d4f8391f src/eu/alefzero/owncloud/datamodel/DataStorageManager.java (David A. Velasco  2012-07-11 13:51:57 +0200 21) import java.util.List;
b7ca8521 src/eu/alefzero/owncloud/datamodel/DataStorageManager.java (Bartek Przybylski 2012-04-14 22:28:55 +0200 22) import java.util.Vector;
b7ca8521 src/eu/alefzero/owncloud/datamodel/DataStorageManager.java (Bartek Przybylski 2012-04-14 22:28:55 +0200 23) 
b7ca8521 src/eu/alefzero/owncloud/datamodel/DataStorageManager.java (Bartek Przybylski 2012-04-14 22:28:55 +0200 24) public interface DataStorageManager {
b7ca8521 src/eu/alefzero/owncloud/datamodel/DataStorageManager.java (Bartek Przybylski 2012-04-14 22:28:55 +0200 25) 
6e469559 src/com/owncloud/android/datamodel/DataStorageManager.java (David A. Velasco  2012-10-23 12:47:29 +0200 26)     public static final int ROOT_PARENT_ID = 0;
6e469559 src/com/owncloud/android/datamodel/DataStorageManager.java (David A. Velasco  2012-10-23 12:47:29 +0200 27)     
435b31ba src/eu/alefzero/owncloud/datamodel/DataStorageManager.java (Lennart Rosam     2012-05-16 09:48:34 +0200 28)     public OCFile getFileByPath(String path);
435b31ba src/eu/alefzero/owncloud/datamodel/DataStorageManager.java (Lennart Rosam     2012-05-16 09:48:34 +0200 29) 
435b31ba src/eu/alefzero/owncloud/datamodel/DataStorageManager.java (Lennart Rosam     2012-05-16 09:48:34 +0200 30)     public OCFile getFileById(long id);
435b31ba src/eu/alefzero/owncloud/datamodel/DataStorageManager.java (Lennart Rosam     2012-05-16 09:48:34 +0200 31) 
435b31ba src/eu/alefzero/owncloud/datamodel/DataStorageManager.java (Lennart Rosam     2012-05-16 09:48:34 +0200 32)     public boolean fileExists(String path);
435b31ba src/eu/alefzero/owncloud/datamodel/DataStorageManager.java (Lennart Rosam     2012-05-16 09:48:34 +0200 33) 
435b31ba src/eu/alefzero/owncloud/datamodel/DataStorageManager.java (Lennart Rosam     2012-05-16 09:48:34 +0200 34)     public boolean fileExists(long id);
435b31ba src/eu/alefzero/owncloud/datamodel/DataStorageManager.java (Lennart Rosam     2012-05-16 09:48:34 +0200 35) 
435b31ba src/eu/alefzero/owncloud/datamodel/DataStorageManager.java (Lennart Rosam     2012-05-16 09:48:34 +0200 36)     public boolean saveFile(OCFile file);
435b31ba src/eu/alefzero/owncloud/datamodel/DataStorageManager.java (Lennart Rosam     2012-05-16 09:48:34 +0200 37) 
d4f8391f src/eu/alefzero/owncloud/datamodel/DataStorageManager.java (David A. Velasco  2012-07-11 13:51:57 +0200 38)     public void saveFiles(List<OCFile> files);
d4f8391f src/eu/alefzero/owncloud/datamodel/DataStorageManager.java (David A. Velasco  2012-07-11 13:51:57 +0200 39) 
435b31ba src/eu/alefzero/owncloud/datamodel/DataStorageManager.java (Lennart Rosam     2012-05-16 09:48:34 +0200 40)     public Vector<OCFile> getDirectoryContent(OCFile f);
65523409 src/eu/alefzero/owncloud/datamodel/DataStorageManager.java (Bartek Przybylski 2012-05-19 18:30:03 +0200 41)     
938c7846 src/com/owncloud/android/datamodel/DataStorageManager.java (David A. Velasco  2012-08-22 12:50:06 +0200 42)     public void removeFile(OCFile file, boolean removeLocalCopy);
b27ebf03 src/com/owncloud/android/datamodel/DataStorageManager.java (David A. Velasco  2012-11-05 15:44:09 +0100 43)     
b27ebf03 src/com/owncloud/android/datamodel/DataStorageManager.java (David A. Velasco  2012-11-05 15:44:09 +0100 44)     public void removeDirectory(OCFile dir, boolean removeDBData, boolean removeLocalContent);
d0b7df16 src/com/owncloud/android/datamodel/DataStorageManager.java (David A. Velasco  2012-11-21 14:32:40 +0100 45) 
d0b7df16 src/com/owncloud/android/datamodel/DataStorageManager.java (David A. Velasco  2012-11-21 14:32:40 +0100 46)     public void moveDirectory(OCFile dir, String newPath);
d7e05192 src/com/owncloud/android/datamodel/DataStorageManager.java (David A. Velasco  2013-02-25 14:33:16 +0100 47) 
d7e05192 src/com/owncloud/android/datamodel/DataStorageManager.java (David A. Velasco  2013-02-25 14:33:16 +0100 48)     public Vector<OCFile> getDirectoryImages(OCFile mParentFolder);
eee74aa5 src/com/owncloud/android/datamodel/DataStorageManager.java (masensio          2013-07-04 18:47:38 +0200 49)     
7a7fe5db src/com/owncloud/android/datamodel/DataStorageManager.java (David A. Velasco  2013-07-09 12:32:44 +0200 50)     public void calculateFolderSize(long id);
eee74aa5 src/com/owncloud/android/datamodel/DataStorageManager.java (masensio          2013-07-04 18:47:38 +0200 51)     
b7ca8521 src/eu/alefzero/owncloud/datamodel/DataStorageManager.java (Bartek Przybylski 2012-04-14 22:28:55 +0200 52) }
