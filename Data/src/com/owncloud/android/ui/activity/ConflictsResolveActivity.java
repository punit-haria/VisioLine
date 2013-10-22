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
6c323657 (Bartek Przybylski 2012-10-27 12:20:52 +0200  19) package com.owncloud.android.ui.activity;
6c323657 (Bartek Przybylski 2012-10-27 12:20:52 +0200  20) 
8e36e7cc (zerginator        2013-03-12 07:56:27 +0100  21) import com.owncloud.android.Log_OC;
7f34fba4 (David A. Velasco  2013-06-17 14:43:26 +0200  22) import com.owncloud.android.datamodel.DataStorageManager;
7f34fba4 (David A. Velasco  2013-06-17 14:43:26 +0200  23) import com.owncloud.android.datamodel.FileDataStorageManager;
195b0092 (David A. Velasco  2012-11-20 14:12:32 +0100  24) import com.owncloud.android.datamodel.OCFile;
6c323657 (Bartek Przybylski 2012-10-27 12:20:52 +0200  25) import com.owncloud.android.files.services.FileUploader;
6c323657 (Bartek Przybylski 2012-10-27 12:20:52 +0200  26) import com.owncloud.android.ui.dialog.ConflictsResolveDialog;
6c323657 (Bartek Przybylski 2012-10-27 12:20:52 +0200  27) import com.owncloud.android.ui.dialog.ConflictsResolveDialog.Decision;
6c323657 (Bartek Przybylski 2012-10-27 12:20:52 +0200  28) import com.owncloud.android.ui.dialog.ConflictsResolveDialog.OnConflictDecisionMadeListener;
6c323657 (Bartek Przybylski 2012-10-27 12:20:52 +0200  29) 
6c323657 (Bartek Przybylski 2012-10-27 12:20:52 +0200  30) import android.content.Intent;
6c323657 (Bartek Przybylski 2012-10-27 12:20:52 +0200  31) import android.os.Bundle;
6c323657 (Bartek Przybylski 2012-10-27 12:20:52 +0200  32) 
92080afe (Bartek Przybylski 2012-10-28 00:12:15 +0200  33) /**
92080afe (Bartek Przybylski 2012-10-28 00:12:15 +0200  34)  * Wrapper activity which will be launched if keep-in-sync file will be modified by external
92080afe (Bartek Przybylski 2012-10-28 00:12:15 +0200  35)  * application. 
92080afe (Bartek Przybylski 2012-10-28 00:12:15 +0200  36)  * 
92080afe (Bartek Przybylski 2012-10-28 00:12:15 +0200  37)  * @author Bartek Przybylski
7f34fba4 (David A. Velasco  2013-06-17 14:43:26 +0200  38)  * @author David A. Velasco
92080afe (Bartek Przybylski 2012-10-28 00:12:15 +0200  39)  */
7f34fba4 (David A. Velasco  2013-06-17 14:43:26 +0200  40) public class ConflictsResolveActivity extends FileActivity implements OnConflictDecisionMadeListener {
195b0092 (David A. Velasco  2012-11-20 14:12:32 +0100  41) 
6c323657 (Bartek Przybylski 2012-10-27 12:20:52 +0200  42)     private String TAG = ConflictsResolveActivity.class.getSimpleName();
6c323657 (Bartek Przybylski 2012-10-27 12:20:52 +0200  43)     
6c323657 (Bartek Przybylski 2012-10-27 12:20:52 +0200  44)     @Override
6c323657 (Bartek Przybylski 2012-10-27 12:20:52 +0200  45)     protected void onCreate(Bundle savedInstanceState) {
6c323657 (Bartek Przybylski 2012-10-27 12:20:52 +0200  46)         super.onCreate(savedInstanceState);
6c323657 (Bartek Przybylski 2012-10-27 12:20:52 +0200  47)     }
6c323657 (Bartek Przybylski 2012-10-27 12:20:52 +0200  48) 
6c323657 (Bartek Przybylski 2012-10-27 12:20:52 +0200  49)     @Override
6c323657 (Bartek Przybylski 2012-10-27 12:20:52 +0200  50)     public void ConflictDecisionMade(Decision decision) {
6c323657 (Bartek Przybylski 2012-10-27 12:20:52 +0200  51)         Intent i = new Intent(getApplicationContext(), FileUploader.class);
6c323657 (Bartek Przybylski 2012-10-27 12:20:52 +0200  52)         
6c323657 (Bartek Przybylski 2012-10-27 12:20:52 +0200  53)         switch (decision) {
6c323657 (Bartek Przybylski 2012-10-27 12:20:52 +0200  54)             case CANCEL:
69ac5b6b (David A. Velasco  2012-11-20 11:40:58 +0100  55)                 finish();
6c323657 (Bartek Przybylski 2012-10-27 12:20:52 +0200  56)                 return;
6c323657 (Bartek Przybylski 2012-10-27 12:20:52 +0200  57)             case OVERWRITE:
6c323657 (Bartek Przybylski 2012-10-27 12:20:52 +0200  58)                 i.putExtra(FileUploader.KEY_FORCE_OVERWRITE, true);
a1c538db (David A. Velasco  2012-11-27 15:15:17 +0100  59)                 break;
a1c538db (David A. Velasco  2012-11-27 15:15:17 +0100  60)             case KEEP_BOTH:
a1c538db (David A. Velasco  2012-11-27 15:15:17 +0100  61)                 i.putExtra(FileUploader.KEY_LOCAL_BEHAVIOUR, FileUploader.LOCAL_BEHAVIOUR_MOVE);
6c323657 (Bartek Przybylski 2012-10-27 12:20:52 +0200  62)                 break;
6c323657 (Bartek Przybylski 2012-10-27 12:20:52 +0200  63)             default:
8e36e7cc (zerginator        2013-03-12 07:56:27 +0100  64)                 Log_OC.wtf(TAG, "Unhandled conflict decision " + decision);
6c323657 (Bartek Przybylski 2012-10-27 12:20:52 +0200  65)                 return;
6c323657 (Bartek Przybylski 2012-10-27 12:20:52 +0200  66)         }
7f34fba4 (David A. Velasco  2013-06-17 14:43:26 +0200  67)         i.putExtra(FileUploader.KEY_ACCOUNT, getAccount());
7f34fba4 (David A. Velasco  2013-06-17 14:43:26 +0200  68)         i.putExtra(FileUploader.KEY_FILE, getFile());
6c323657 (Bartek Przybylski 2012-10-27 12:20:52 +0200  69)         i.putExtra(FileUploader.KEY_UPLOAD_TYPE, FileUploader.UPLOAD_SINGLE_FILE);
6c323657 (Bartek Przybylski 2012-10-27 12:20:52 +0200  70)         
6c323657 (Bartek Przybylski 2012-10-27 12:20:52 +0200  71)         startService(i);
6c323657 (Bartek Przybylski 2012-10-27 12:20:52 +0200  72)         finish();
6c323657 (Bartek Przybylski 2012-10-27 12:20:52 +0200  73)     }
7f34fba4 (David A. Velasco  2013-06-17 14:43:26 +0200  74) 
7f34fba4 (David A. Velasco  2013-06-17 14:43:26 +0200  75)     @Override
7f34fba4 (David A. Velasco  2013-06-17 14:43:26 +0200  76)     protected void onAccountSet(boolean stateWasRecovered) {
7f34fba4 (David A. Velasco  2013-06-17 14:43:26 +0200  77)         if (getAccount() != null) {
7f34fba4 (David A. Velasco  2013-06-17 14:43:26 +0200  78)             OCFile file = getFile();
7f34fba4 (David A. Velasco  2013-06-17 14:43:26 +0200  79)             if (getFile() == null) {
7f34fba4 (David A. Velasco  2013-06-17 14:43:26 +0200  80)                 Log_OC.e(TAG, "No conflictive file received");
7f34fba4 (David A. Velasco  2013-06-17 14:43:26 +0200  81)                 finish();
7f34fba4 (David A. Velasco  2013-06-17 14:43:26 +0200  82)             } else {
7f34fba4 (David A. Velasco  2013-06-17 14:43:26 +0200  83)                 /// Check whether the 'main' OCFile handled by the Activity is contained in the current Account
7f34fba4 (David A. Velasco  2013-06-17 14:43:26 +0200  84)                 DataStorageManager storageManager = new FileDataStorageManager(getAccount(), getContentResolver());
7f34fba4 (David A. Velasco  2013-06-17 14:43:26 +0200  85)                 file = storageManager.getFileByPath(file.getRemotePath());   // file = null if not in the current Account
7f34fba4 (David A. Velasco  2013-06-17 14:43:26 +0200  86)                 if (file != null) {
7f34fba4 (David A. Velasco  2013-06-17 14:43:26 +0200  87)                     setFile(file);
7f34fba4 (David A. Velasco  2013-06-17 14:43:26 +0200  88)                     ConflictsResolveDialog d = ConflictsResolveDialog.newInstance(file.getRemotePath(), this);
7f34fba4 (David A. Velasco  2013-06-17 14:43:26 +0200  89)                     d.showDialog(this);
7f34fba4 (David A. Velasco  2013-06-17 14:43:26 +0200  90)                     
7f34fba4 (David A. Velasco  2013-06-17 14:43:26 +0200  91)                 } else {
7f34fba4 (David A. Velasco  2013-06-17 14:43:26 +0200  92)                     // account was changed to a different one - just finish
7f34fba4 (David A. Velasco  2013-06-17 14:43:26 +0200  93)                     finish();
7f34fba4 (David A. Velasco  2013-06-17 14:43:26 +0200  94)                 }
7f34fba4 (David A. Velasco  2013-06-17 14:43:26 +0200  95)             }
7f34fba4 (David A. Velasco  2013-06-17 14:43:26 +0200  96)             
7f34fba4 (David A. Velasco  2013-06-17 14:43:26 +0200  97)         } else {
7f34fba4 (David A. Velasco  2013-06-17 14:43:26 +0200  98)             Log_OC.wtf(TAG, "onAccountChanged was called with NULL account associated!");
7f34fba4 (David A. Velasco  2013-06-17 14:43:26 +0200  99)             finish();
7f34fba4 (David A. Velasco  2013-06-17 14:43:26 +0200 100)         }
7f34fba4 (David A. Velasco  2013-06-17 14:43:26 +0200 101)         
7f34fba4 (David A. Velasco  2013-06-17 14:43:26 +0200 102)     }
6c323657 (Bartek Przybylski 2012-10-27 12:20:52 +0200 103) }
