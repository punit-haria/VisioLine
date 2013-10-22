92080afe (Bartek Przybylski 2012-10-28 00:12:15 +0200  1) /* ownCloud Android client application
92080afe (Bartek Przybylski 2012-10-28 00:12:15 +0200  2)  *   Copyright (C) 2012 Bartek Przybylski
bb257ec7 (David A. Velasco  2013-02-07 18:45:10 +0100  3)  *   Copyright (C) 2012-2013 ownCloud Inc.
92080afe (Bartek Przybylski 2012-10-28 00:12:15 +0200  4)  *
92080afe (Bartek Przybylski 2012-10-28 00:12:15 +0200  5)  *   This program is free software: you can redistribute it and/or modify
bdc0332c (David A. Velasco  2013-04-17 12:26:13 +0200  6)  *   it under the terms of the GNU General Public License version 2,
bdc0332c (David A. Velasco  2013-04-17 12:26:13 +0200  7)  *   as published by the Free Software Foundation.
92080afe (Bartek Przybylski 2012-10-28 00:12:15 +0200  8)  *
92080afe (Bartek Przybylski 2012-10-28 00:12:15 +0200  9)  *   This program is distributed in the hope that it will be useful,
92080afe (Bartek Przybylski 2012-10-28 00:12:15 +0200 10)  *   but WITHOUT ANY WARRANTY; without even the implied warranty of
92080afe (Bartek Przybylski 2012-10-28 00:12:15 +0200 11)  *   MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
92080afe (Bartek Przybylski 2012-10-28 00:12:15 +0200 12)  *   GNU General Public License for more details.
92080afe (Bartek Przybylski 2012-10-28 00:12:15 +0200 13)  *
92080afe (Bartek Przybylski 2012-10-28 00:12:15 +0200 14)  *   You should have received a copy of the GNU General Public License
92080afe (Bartek Przybylski 2012-10-28 00:12:15 +0200 15)  *   along with this program.  If not, see <http://www.gnu.org/licenses/>.
92080afe (Bartek Przybylski 2012-10-28 00:12:15 +0200 16)  *
92080afe (Bartek Przybylski 2012-10-28 00:12:15 +0200 17)  */
92080afe (Bartek Przybylski 2012-10-28 00:12:15 +0200 18) 
68ce2e7a (David A. Velasco  2012-10-09 14:53:25 +0200 19) package com.owncloud.android.ui.activity;
68ce2e7a (David A. Velasco  2012-10-09 14:53:25 +0200 20) 
68ce2e7a (David A. Velasco  2012-10-09 14:53:25 +0200 21) import com.owncloud.android.files.services.FileDownloader.FileDownloaderBinder;
5fc7cd13 (David A. Velasco  2012-10-11 11:24:17 +0200 22) import com.owncloud.android.files.services.FileUploader.FileUploaderBinder;
68ce2e7a (David A. Velasco  2012-10-09 14:53:25 +0200 23) 
68ce2e7a (David A. Velasco  2012-10-09 14:53:25 +0200 24) public interface TransferServiceGetter {
68ce2e7a (David A. Velasco  2012-10-09 14:53:25 +0200 25) 
68ce2e7a (David A. Velasco  2012-10-09 14:53:25 +0200 26)     /**
68ce2e7a (David A. Velasco  2012-10-09 14:53:25 +0200 27)      * Callback method invoked when the parent activity is fully created to get a reference to the FileDownloader service API.
68ce2e7a (David A. Velasco  2012-10-09 14:53:25 +0200 28)      * 
68ce2e7a (David A. Velasco  2012-10-09 14:53:25 +0200 29)      * @return  Directory to list firstly. Can be NULL.
68ce2e7a (David A. Velasco  2012-10-09 14:53:25 +0200 30)      */
68ce2e7a (David A. Velasco  2012-10-09 14:53:25 +0200 31)     public FileDownloaderBinder getFileDownloaderBinder();
68ce2e7a (David A. Velasco  2012-10-09 14:53:25 +0200 32) 
5fc7cd13 (David A. Velasco  2012-10-11 11:24:17 +0200 33)     
5fc7cd13 (David A. Velasco  2012-10-11 11:24:17 +0200 34)     /**
5fc7cd13 (David A. Velasco  2012-10-11 11:24:17 +0200 35)      * Callback method invoked when the parent activity is fully created to get a reference to the FileUploader service API.
5fc7cd13 (David A. Velasco  2012-10-11 11:24:17 +0200 36)      * 
5fc7cd13 (David A. Velasco  2012-10-11 11:24:17 +0200 37)      * @return  Directory to list firstly. Can be NULL.
5fc7cd13 (David A. Velasco  2012-10-11 11:24:17 +0200 38)      */
5fc7cd13 (David A. Velasco  2012-10-11 11:24:17 +0200 39)     public FileUploaderBinder getFileUploaderBinder();
5fc7cd13 (David A. Velasco  2012-10-11 11:24:17 +0200 40) 
68ce2e7a (David A. Velasco  2012-10-09 14:53:25 +0200 41) 
68ce2e7a (David A. Velasco  2012-10-09 14:53:25 +0200 42) }
