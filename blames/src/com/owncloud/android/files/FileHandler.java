edb380e0 (David A. Velasco 2013-05-27 15:01:54 +0200  1) /* ownCloud Android client application
edb380e0 (David A. Velasco 2013-05-27 15:01:54 +0200  2)  *   Copyright (C) 2012-2013 ownCloud Inc.
edb380e0 (David A. Velasco 2013-05-27 15:01:54 +0200  3)  *
edb380e0 (David A. Velasco 2013-05-27 15:01:54 +0200  4)  *   This program is free software: you can redistribute it and/or modify
edb380e0 (David A. Velasco 2013-05-27 15:01:54 +0200  5)  *   it under the terms of the GNU General Public License version 2,
edb380e0 (David A. Velasco 2013-05-27 15:01:54 +0200  6)  *   as published by the Free Software Foundation.
edb380e0 (David A. Velasco 2013-05-27 15:01:54 +0200  7)  *
edb380e0 (David A. Velasco 2013-05-27 15:01:54 +0200  8)  *   This program is distributed in the hope that it will be useful,
edb380e0 (David A. Velasco 2013-05-27 15:01:54 +0200  9)  *   but WITHOUT ANY WARRANTY; without even the implied warranty of
edb380e0 (David A. Velasco 2013-05-27 15:01:54 +0200 10)  *   MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
edb380e0 (David A. Velasco 2013-05-27 15:01:54 +0200 11)  *   GNU General Public License for more details.
edb380e0 (David A. Velasco 2013-05-27 15:01:54 +0200 12)  *
edb380e0 (David A. Velasco 2013-05-27 15:01:54 +0200 13)  *   You should have received a copy of the GNU General Public License
edb380e0 (David A. Velasco 2013-05-27 15:01:54 +0200 14)  *   along with this program.  If not, see <http://www.gnu.org/licenses/>.
edb380e0 (David A. Velasco 2013-05-27 15:01:54 +0200 15)  *
edb380e0 (David A. Velasco 2013-05-27 15:01:54 +0200 16)  */
edb380e0 (David A. Velasco 2013-05-27 15:01:54 +0200 17) 
edb380e0 (David A. Velasco 2013-05-27 15:01:54 +0200 18) package com.owncloud.android.files;
edb380e0 (David A. Velasco 2013-05-27 15:01:54 +0200 19) 
edb380e0 (David A. Velasco 2013-05-27 15:01:54 +0200 20) import com.owncloud.android.datamodel.OCFile;
edb380e0 (David A. Velasco 2013-05-27 15:01:54 +0200 21) 
edb380e0 (David A. Velasco 2013-05-27 15:01:54 +0200 22) public interface FileHandler {
edb380e0 (David A. Velasco 2013-05-27 15:01:54 +0200 23) 
edb380e0 (David A. Velasco 2013-05-27 15:01:54 +0200 24)     /**
edb380e0 (David A. Velasco 2013-05-27 15:01:54 +0200 25)      * TODO
edb380e0 (David A. Velasco 2013-05-27 15:01:54 +0200 26)      */
edb380e0 (David A. Velasco 2013-05-27 15:01:54 +0200 27)     public void openFile(OCFile file);
edb380e0 (David A. Velasco 2013-05-27 15:01:54 +0200 28) 
edb380e0 (David A. Velasco 2013-05-27 15:01:54 +0200 29)     
edb380e0 (David A. Velasco 2013-05-27 15:01:54 +0200 30) }
