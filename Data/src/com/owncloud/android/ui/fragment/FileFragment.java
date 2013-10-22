7101a042 (David A. Velasco 2013-02-14 19:21:09 +0100   1) /* ownCloud Android client application
7101a042 (David A. Velasco 2013-02-14 19:21:09 +0100   2)  *   Copyright (C) 2012-2013  ownCloud Inc.
7101a042 (David A. Velasco 2013-02-14 19:21:09 +0100   3)  *
7101a042 (David A. Velasco 2013-02-14 19:21:09 +0100   4)  *   This program is free software: you can redistribute it and/or modify
bdc0332c (David A. Velasco 2013-04-17 12:26:13 +0200   5)  *   it under the terms of the GNU General Public License version 2,
bdc0332c (David A. Velasco 2013-04-17 12:26:13 +0200   6)  *   as published by the Free Software Foundation.
7101a042 (David A. Velasco 2013-02-14 19:21:09 +0100   7)  *
7101a042 (David A. Velasco 2013-02-14 19:21:09 +0100   8)  *   This program is distributed in the hope that it will be useful,
7101a042 (David A. Velasco 2013-02-14 19:21:09 +0100   9)  *   but WITHOUT ANY WARRANTY; without even the implied warranty of
7101a042 (David A. Velasco 2013-02-14 19:21:09 +0100  10)  *   MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
7101a042 (David A. Velasco 2013-02-14 19:21:09 +0100  11)  *   GNU General Public License for more details.
7101a042 (David A. Velasco 2013-02-14 19:21:09 +0100  12)  *
7101a042 (David A. Velasco 2013-02-14 19:21:09 +0100  13)  *   You should have received a copy of the GNU General Public License
7101a042 (David A. Velasco 2013-02-14 19:21:09 +0100  14)  *   along with this program.  If not, see <http://www.gnu.org/licenses/>.
7101a042 (David A. Velasco 2013-02-14 19:21:09 +0100  15)  *
7101a042 (David A. Velasco 2013-02-14 19:21:09 +0100  16)  */
7101a042 (David A. Velasco 2013-02-14 19:21:09 +0100  17) 
7101a042 (David A. Velasco 2013-02-14 19:21:09 +0100  18) package com.owncloud.android.ui.fragment;
7101a042 (David A. Velasco 2013-02-14 19:21:09 +0100  19) 
7101a042 (David A. Velasco 2013-02-14 19:21:09 +0100  20) import android.support.v4.app.Fragment;
7101a042 (David A. Velasco 2013-02-14 19:21:09 +0100  21) 
fd396289 (David A. Velasco 2013-05-30 17:53:21 +0200  22) import com.actionbarsherlock.app.SherlockFragment;
7101a042 (David A. Velasco 2013-02-14 19:21:09 +0100  23) import com.owncloud.android.datamodel.OCFile;
edb380e0 (David A. Velasco 2013-05-27 15:01:54 +0200  24) import com.owncloud.android.files.FileHandler;
f2474ae2 (David A. Velasco 2013-02-18 11:23:18 +0100  25) import com.owncloud.android.ui.activity.TransferServiceGetter;
7101a042 (David A. Velasco 2013-02-14 19:21:09 +0100  26) 
7101a042 (David A. Velasco 2013-02-14 19:21:09 +0100  27) /**
7101a042 (David A. Velasco 2013-02-14 19:21:09 +0100  28)  * Common methods for {@link Fragment}s containing {@link OCFile}s
7101a042 (David A. Velasco 2013-02-14 19:21:09 +0100  29)  * 
7101a042 (David A. Velasco 2013-02-14 19:21:09 +0100  30)  * @author David A. Velasco
7101a042 (David A. Velasco 2013-02-14 19:21:09 +0100  31)  *
7101a042 (David A. Velasco 2013-02-14 19:21:09 +0100  32)  */
fd396289 (David A. Velasco 2013-05-30 17:53:21 +0200  33) public class FileFragment extends SherlockFragment {
fd396289 (David A. Velasco 2013-05-30 17:53:21 +0200  34)     
fd396289 (David A. Velasco 2013-05-30 17:53:21 +0200  35)     private OCFile mFile;
fd396289 (David A. Velasco 2013-05-30 17:53:21 +0200  36) 
fd396289 (David A. Velasco 2013-05-30 17:53:21 +0200  37) 
fd396289 (David A. Velasco 2013-05-30 17:53:21 +0200  38)     /**
fd396289 (David A. Velasco 2013-05-30 17:53:21 +0200  39)      * Creates an empty fragment.
fd396289 (David A. Velasco 2013-05-30 17:53:21 +0200  40)      * 
fd396289 (David A. Velasco 2013-05-30 17:53:21 +0200  41)      * It's necessary to keep a public constructor without parameters; the system uses it when tries to reinstantiate a fragment automatically. 
fd396289 (David A. Velasco 2013-05-30 17:53:21 +0200  42)      */
fd396289 (David A. Velasco 2013-05-30 17:53:21 +0200  43)     public FileFragment() {
fd396289 (David A. Velasco 2013-05-30 17:53:21 +0200  44)         mFile = null;
fd396289 (David A. Velasco 2013-05-30 17:53:21 +0200  45)     }
7101a042 (David A. Velasco 2013-02-14 19:21:09 +0100  46)     
7101a042 (David A. Velasco 2013-02-14 19:21:09 +0100  47)     /**
fd396289 (David A. Velasco 2013-05-30 17:53:21 +0200  48)      * Creates an instance for a given {@OCFile}.
fd396289 (David A. Velasco 2013-05-30 17:53:21 +0200  49)      * 
fd396289 (David A. Velasco 2013-05-30 17:53:21 +0200  50)      * @param file
fd396289 (David A. Velasco 2013-05-30 17:53:21 +0200  51)      */
fd396289 (David A. Velasco 2013-05-30 17:53:21 +0200  52)     public FileFragment(OCFile file) {
fd396289 (David A. Velasco 2013-05-30 17:53:21 +0200  53)         mFile = file;
fd396289 (David A. Velasco 2013-05-30 17:53:21 +0200  54)     }
fd396289 (David A. Velasco 2013-05-30 17:53:21 +0200  55) 
fd396289 (David A. Velasco 2013-05-30 17:53:21 +0200  56)     /**
7101a042 (David A. Velasco 2013-02-14 19:21:09 +0100  57)      * Getter for the hold {@link OCFile}
7101a042 (David A. Velasco 2013-02-14 19:21:09 +0100  58)      * 
7101a042 (David A. Velasco 2013-02-14 19:21:09 +0100  59)      * @return The {@link OCFile} hold
7101a042 (David A. Velasco 2013-02-14 19:21:09 +0100  60)      */
fd396289 (David A. Velasco 2013-05-30 17:53:21 +0200  61)     public OCFile getFile() {
fd396289 (David A. Velasco 2013-05-30 17:53:21 +0200  62)         return mFile;
fd396289 (David A. Velasco 2013-05-30 17:53:21 +0200  63)     }
f2474ae2 (David A. Velasco 2013-02-18 11:23:18 +0100  64)     
f2474ae2 (David A. Velasco 2013-02-18 11:23:18 +0100  65)     
fd396289 (David A. Velasco 2013-05-30 17:53:21 +0200  66)     protected void setFile(OCFile file) {
fd396289 (David A. Velasco 2013-05-30 17:53:21 +0200  67)         mFile = file;
fd396289 (David A. Velasco 2013-05-30 17:53:21 +0200  68)     }
fd396289 (David A. Velasco 2013-05-30 17:53:21 +0200  69) 
f2474ae2 (David A. Velasco 2013-02-18 11:23:18 +0100  70)     /**
f2474ae2 (David A. Velasco 2013-02-18 11:23:18 +0100  71)      * Interface to implement by any Activity that includes some instance of FileFragment
f2474ae2 (David A. Velasco 2013-02-18 11:23:18 +0100  72)      * 
f2474ae2 (David A. Velasco 2013-02-18 11:23:18 +0100  73)      * @author David A. Velasco
f2474ae2 (David A. Velasco 2013-02-18 11:23:18 +0100  74)      */
edb380e0 (David A. Velasco 2013-05-27 15:01:54 +0200  75)     public interface ContainerActivity extends TransferServiceGetter, FileHandler {
f2474ae2 (David A. Velasco 2013-02-18 11:23:18 +0100  76) 
f2474ae2 (David A. Velasco 2013-02-18 11:23:18 +0100  77)         /**
f2474ae2 (David A. Velasco 2013-02-18 11:23:18 +0100  78)          * Callback method invoked when the detail fragment wants to notice its container 
f2474ae2 (David A. Velasco 2013-02-18 11:23:18 +0100  79)          * activity about a relevant state the file shown by the fragment.
f2474ae2 (David A. Velasco 2013-02-18 11:23:18 +0100  80)          * 
f2474ae2 (David A. Velasco 2013-02-18 11:23:18 +0100  81)          * Added to notify to FileDisplayActivity about the need of refresh the files list. 
f2474ae2 (David A. Velasco 2013-02-18 11:23:18 +0100  82)          * 
f2474ae2 (David A. Velasco 2013-02-18 11:23:18 +0100  83)          * Currently called when:
f2474ae2 (David A. Velasco 2013-02-18 11:23:18 +0100  84)          *  - a download is started;
f2474ae2 (David A. Velasco 2013-02-18 11:23:18 +0100  85)          *  - a rename is completed;
f2474ae2 (David A. Velasco 2013-02-18 11:23:18 +0100  86)          *  - a deletion is completed;
f2474ae2 (David A. Velasco 2013-02-18 11:23:18 +0100  87)          *  - the 'inSync' flag is changed;
f2474ae2 (David A. Velasco 2013-02-18 11:23:18 +0100  88)          */
f2474ae2 (David A. Velasco 2013-02-18 11:23:18 +0100  89)         public void onFileStateChanged();
f2474ae2 (David A. Velasco 2013-02-18 11:23:18 +0100  90) 
f2474ae2 (David A. Velasco 2013-02-18 11:23:18 +0100  91)         /**
f2474ae2 (David A. Velasco 2013-02-18 11:23:18 +0100  92)          * Request the parent activity to show the details of an {@link OCFile}.
f2474ae2 (David A. Velasco 2013-02-18 11:23:18 +0100  93)          * 
f2474ae2 (David A. Velasco 2013-02-18 11:23:18 +0100  94)          * @param file      File to show details
f2474ae2 (David A. Velasco 2013-02-18 11:23:18 +0100  95)          */
fd396289 (David A. Velasco 2013-05-30 17:53:21 +0200  96)         public void showDetails(OCFile file);
f2474ae2 (David A. Velasco 2013-02-18 11:23:18 +0100  97)         
559ab656 (David A. Velasco 2013-02-22 15:27:22 +0100  98)         
f2474ae2 (David A. Velasco 2013-02-18 11:23:18 +0100  99)     }
f2474ae2 (David A. Velasco 2013-02-18 11:23:18 +0100 100)     
7101a042 (David A. Velasco 2013-02-14 19:21:09 +0100 101) }
