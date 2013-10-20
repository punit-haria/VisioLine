1ecd2b61 (David A. Velasco 2012-08-14 12:37:08 +0200   1) /* ownCloud Android client application
bb257ec7 (David A. Velasco 2013-02-07 18:45:10 +0100   2)  *   Copyright (C) 2012-2013 ownCloud Inc.
1ecd2b61 (David A. Velasco 2012-08-14 12:37:08 +0200   3)  *
1ecd2b61 (David A. Velasco 2012-08-14 12:37:08 +0200   4)  *   This program is free software: you can redistribute it and/or modify
bdc0332c (David A. Velasco 2013-04-17 12:26:13 +0200   5)  *   it under the terms of the GNU General Public License version 2,
bdc0332c (David A. Velasco 2013-04-17 12:26:13 +0200   6)  *   as published by the Free Software Foundation.
1ecd2b61 (David A. Velasco 2012-08-14 12:37:08 +0200   7)  *
1ecd2b61 (David A. Velasco 2012-08-14 12:37:08 +0200   8)  *   This program is distributed in the hope that it will be useful,
1ecd2b61 (David A. Velasco 2012-08-14 12:37:08 +0200   9)  *   but WITHOUT ANY WARRANTY; without even the implied warranty of
1ecd2b61 (David A. Velasco 2012-08-14 12:37:08 +0200  10)  *   MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
1ecd2b61 (David A. Velasco 2012-08-14 12:37:08 +0200  11)  *   GNU General Public License for more details.
1ecd2b61 (David A. Velasco 2012-08-14 12:37:08 +0200  12)  *
1ecd2b61 (David A. Velasco 2012-08-14 12:37:08 +0200  13)  *   You should have received a copy of the GNU General Public License
1ecd2b61 (David A. Velasco 2012-08-14 12:37:08 +0200  14)  *   along with this program.  If not, see <http://www.gnu.org/licenses/>.
1ecd2b61 (David A. Velasco 2012-08-14 12:37:08 +0200  15)  *
1ecd2b61 (David A. Velasco 2012-08-14 12:37:08 +0200  16)  */
1ecd2b61 (David A. Velasco 2012-08-14 12:37:08 +0200  17) 
1ecd2b61 (David A. Velasco 2012-08-14 12:37:08 +0200  18) package com.owncloud.android.ui.activity;
1ecd2b61 (David A. Velasco 2012-08-14 12:37:08 +0200  19) 
1ecd2b61 (David A. Velasco 2012-08-14 12:37:08 +0200  20) import java.io.File;
1ecd2b61 (David A. Velasco 2012-08-14 12:37:08 +0200  21) 
45588662 (David A. Velasco 2012-12-04 14:20:02 +0100  22) import android.accounts.Account;
1ecd2b61 (David A. Velasco 2012-08-14 12:37:08 +0200  23) import android.content.Intent;
45588662 (David A. Velasco 2012-12-04 14:20:02 +0100  24) import android.os.AsyncTask;
1ecd2b61 (David A. Velasco 2012-08-14 12:37:08 +0200  25) import android.os.Bundle;
1ecd2b61 (David A. Velasco 2012-08-14 12:37:08 +0200  26) import android.os.Environment;
45588662 (David A. Velasco 2012-12-04 14:20:02 +0100  27) import android.support.v4.app.DialogFragment;
1ecd2b61 (David A. Velasco 2012-08-14 12:37:08 +0200  28) import android.view.View;
1ecd2b61 (David A. Velasco 2012-08-14 12:37:08 +0200  29) import android.view.View.OnClickListener;
1ecd2b61 (David A. Velasco 2012-08-14 12:37:08 +0200  30) import android.view.ViewGroup;
1ecd2b61 (David A. Velasco 2012-08-14 12:37:08 +0200  31) import android.widget.ArrayAdapter;
1ecd2b61 (David A. Velasco 2012-08-14 12:37:08 +0200  32) import android.widget.Button;
1ecd2b61 (David A. Velasco 2012-08-14 12:37:08 +0200  33) import android.widget.TextView;
1ecd2b61 (David A. Velasco 2012-08-14 12:37:08 +0200  34) 
1ecd2b61 (David A. Velasco 2012-08-14 12:37:08 +0200  35) import com.actionbarsherlock.app.ActionBar;
1ecd2b61 (David A. Velasco 2012-08-14 12:37:08 +0200  36) import com.actionbarsherlock.app.ActionBar.OnNavigationListener;
1ecd2b61 (David A. Velasco 2012-08-14 12:37:08 +0200  37) import com.actionbarsherlock.view.MenuItem;
45588662 (David A. Velasco 2012-12-04 14:20:02 +0100  38) import com.owncloud.android.ui.dialog.IndeterminateProgressDialog;
45588662 (David A. Velasco 2012-12-04 14:20:02 +0100  39) import com.owncloud.android.ui.fragment.ConfirmationDialogFragment;
1ecd2b61 (David A. Velasco 2012-08-14 12:37:08 +0200  40) import com.owncloud.android.ui.fragment.LocalFileListFragment;
45588662 (David A. Velasco 2012-12-04 14:20:02 +0100  41) import com.owncloud.android.ui.fragment.ConfirmationDialogFragment.ConfirmationDialogFragmentListener;
45588662 (David A. Velasco 2012-12-04 14:20:02 +0100  42) import com.owncloud.android.utils.FileStorageUtils;
1ecd2b61 (David A. Velasco 2012-08-14 12:37:08 +0200  43) 
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100  44) import com.owncloud.android.Log_OC;
1ecd2b61 (David A. Velasco 2012-08-14 12:37:08 +0200  45) import com.owncloud.android.R;
1ecd2b61 (David A. Velasco 2012-08-14 12:37:08 +0200  46) 
1ecd2b61 (David A. Velasco 2012-08-14 12:37:08 +0200  47) /**
1ecd2b61 (David A. Velasco 2012-08-14 12:37:08 +0200  48)  * Displays local files and let the user choose what of them wants to upload
1ecd2b61 (David A. Velasco 2012-08-14 12:37:08 +0200  49)  * to the current ownCloud account
1ecd2b61 (David A. Velasco 2012-08-14 12:37:08 +0200  50)  * 
1ecd2b61 (David A. Velasco 2012-08-14 12:37:08 +0200  51)  * @author David A. Velasco
1ecd2b61 (David A. Velasco 2012-08-14 12:37:08 +0200  52)  * 
1ecd2b61 (David A. Velasco 2012-08-14 12:37:08 +0200  53)  */
1ecd2b61 (David A. Velasco 2012-08-14 12:37:08 +0200  54) 
c43b15de (David A. Velasco 2013-06-17 14:21:33 +0200  55) public class UploadFilesActivity extends FileActivity implements
45588662 (David A. Velasco 2012-12-04 14:20:02 +0100  56)     LocalFileListFragment.ContainerActivity, OnNavigationListener, OnClickListener, ConfirmationDialogFragmentListener {
1ecd2b61 (David A. Velasco 2012-08-14 12:37:08 +0200  57)     
1ecd2b61 (David A. Velasco 2012-08-14 12:37:08 +0200  58)     private ArrayAdapter<String> mDirectories;
1ecd2b61 (David A. Velasco 2012-08-14 12:37:08 +0200  59)     private File mCurrentDir = null;
1ecd2b61 (David A. Velasco 2012-08-14 12:37:08 +0200  60)     private LocalFileListFragment mFileListFragment;
1ecd2b61 (David A. Velasco 2012-08-14 12:37:08 +0200  61)     private Button mCancelBtn;
1ecd2b61 (David A. Velasco 2012-08-14 12:37:08 +0200  62)     private Button mUploadBtn;
c43b15de (David A. Velasco 2013-06-17 14:21:33 +0200  63)     private Account mAccountOnCreation;
45588662 (David A. Velasco 2012-12-04 14:20:02 +0100  64)     private DialogFragment mCurrentDialog;
1ecd2b61 (David A. Velasco 2012-08-14 12:37:08 +0200  65)     
45588662 (David A. Velasco 2012-12-04 14:20:02 +0100  66)     public static final String EXTRA_CHOSEN_FILES = UploadFilesActivity.class.getCanonicalName() + ".EXTRA_CHOSEN_FILES";
45588662 (David A. Velasco 2012-12-04 14:20:02 +0100  67) 
45588662 (David A. Velasco 2012-12-04 14:20:02 +0100  68)     public static final int RESULT_OK_AND_MOVE = RESULT_FIRST_USER; 
1ecd2b61 (David A. Velasco 2012-08-14 12:37:08 +0200  69)     
45588662 (David A. Velasco 2012-12-04 14:20:02 +0100  70)     private static final String KEY_DIRECTORY_PATH = UploadFilesActivity.class.getCanonicalName() + ".KEY_DIRECTORY_PATH";
1ecd2b61 (David A. Velasco 2012-08-14 12:37:08 +0200  71)     private static final String TAG = "UploadFilesActivity";
45588662 (David A. Velasco 2012-12-04 14:20:02 +0100  72)     private static final String WAIT_DIALOG_TAG = "WAIT";
45588662 (David A. Velasco 2012-12-04 14:20:02 +0100  73)     private static final String QUERY_TO_MOVE_DIALOG_TAG = "QUERY_TO_MOVE";
1ecd2b61 (David A. Velasco 2012-08-14 12:37:08 +0200  74)     
1ecd2b61 (David A. Velasco 2012-08-14 12:37:08 +0200  75)     
1ecd2b61 (David A. Velasco 2012-08-14 12:37:08 +0200  76)     @Override
1ecd2b61 (David A. Velasco 2012-08-14 12:37:08 +0200  77)     public void onCreate(Bundle savedInstanceState) {
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100  78)         Log_OC.d(TAG, "onCreate() start");
1ecd2b61 (David A. Velasco 2012-08-14 12:37:08 +0200  79)         super.onCreate(savedInstanceState);
1ecd2b61 (David A. Velasco 2012-08-14 12:37:08 +0200  80) 
1ecd2b61 (David A. Velasco 2012-08-14 12:37:08 +0200  81)         if(savedInstanceState != null) {
45588662 (David A. Velasco 2012-12-04 14:20:02 +0100  82)             mCurrentDir = new File(savedInstanceState.getString(UploadFilesActivity.KEY_DIRECTORY_PATH));
1ecd2b61 (David A. Velasco 2012-08-14 12:37:08 +0200  83)         } else {
1ecd2b61 (David A. Velasco 2012-08-14 12:37:08 +0200  84)             mCurrentDir = Environment.getExternalStorageDirectory();
1ecd2b61 (David A. Velasco 2012-08-14 12:37:08 +0200  85)         }
1ecd2b61 (David A. Velasco 2012-08-14 12:37:08 +0200  86)         
c43b15de (David A. Velasco 2013-06-17 14:21:33 +0200  87)         mAccountOnCreation = getAccount();
45588662 (David A. Velasco 2012-12-04 14:20:02 +0100  88)                 
1ecd2b61 (David A. Velasco 2012-08-14 12:37:08 +0200  89)         /// USER INTERFACE
1ecd2b61 (David A. Velasco 2012-08-14 12:37:08 +0200  90)             
1ecd2b61 (David A. Velasco 2012-08-14 12:37:08 +0200  91)         // Drop-down navigation 
1ecd2b61 (David A. Velasco 2012-08-14 12:37:08 +0200  92)         mDirectories = new CustomArrayAdapter<String>(this, R.layout.sherlock_spinner_dropdown_item);
1ecd2b61 (David A. Velasco 2012-08-14 12:37:08 +0200  93)         File currDir = mCurrentDir;
1ecd2b61 (David A. Velasco 2012-08-14 12:37:08 +0200  94)         while(currDir != null && currDir.getParentFile() != null) {
1ecd2b61 (David A. Velasco 2012-08-14 12:37:08 +0200  95)             mDirectories.add(currDir.getName());
1ecd2b61 (David A. Velasco 2012-08-14 12:37:08 +0200  96)             currDir = currDir.getParentFile();
1ecd2b61 (David A. Velasco 2012-08-14 12:37:08 +0200  97)         }
1ecd2b61 (David A. Velasco 2012-08-14 12:37:08 +0200  98)         mDirectories.add(File.separator);
1ecd2b61 (David A. Velasco 2012-08-14 12:37:08 +0200  99) 
1ecd2b61 (David A. Velasco 2012-08-14 12:37:08 +0200 100)         // Inflate and set the layout view
1ecd2b61 (David A. Velasco 2012-08-14 12:37:08 +0200 101)         setContentView(R.layout.upload_files_layout);
1ecd2b61 (David A. Velasco 2012-08-14 12:37:08 +0200 102)         mFileListFragment = (LocalFileListFragment) getSupportFragmentManager().findFragmentById(R.id.local_files_list);
1ecd2b61 (David A. Velasco 2012-08-14 12:37:08 +0200 103)         
a2d4e98a (David A. Velasco 2012-10-03 16:48:06 +0200 104)         
1ecd2b61 (David A. Velasco 2012-08-14 12:37:08 +0200 105)         // Set input controllers
1ecd2b61 (David A. Velasco 2012-08-14 12:37:08 +0200 106)         mCancelBtn = (Button) findViewById(R.id.upload_files_btn_cancel);
1ecd2b61 (David A. Velasco 2012-08-14 12:37:08 +0200 107)         mCancelBtn.setOnClickListener(this);
1ecd2b61 (David A. Velasco 2012-08-14 12:37:08 +0200 108)         mUploadBtn = (Button) findViewById(R.id.upload_files_btn_upload);
1ecd2b61 (David A. Velasco 2012-08-14 12:37:08 +0200 109)         mUploadBtn.setOnClickListener(this);
1ecd2b61 (David A. Velasco 2012-08-14 12:37:08 +0200 110)             
1ecd2b61 (David A. Velasco 2012-08-14 12:37:08 +0200 111)         // Action bar setup
1ecd2b61 (David A. Velasco 2012-08-14 12:37:08 +0200 112)         ActionBar actionBar = getSupportActionBar();
1ecd2b61 (David A. Velasco 2012-08-14 12:37:08 +0200 113)         actionBar.setHomeButtonEnabled(true);   // mandatory since Android ICS, according to the official documentation
1ecd2b61 (David A. Velasco 2012-08-14 12:37:08 +0200 114)         actionBar.setDisplayHomeAsUpEnabled(mCurrentDir != null && mCurrentDir.getName() != null);
1ecd2b61 (David A. Velasco 2012-08-14 12:37:08 +0200 115)         actionBar.setDisplayShowTitleEnabled(false);
1ecd2b61 (David A. Velasco 2012-08-14 12:37:08 +0200 116)         actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_LIST);
1ecd2b61 (David A. Velasco 2012-08-14 12:37:08 +0200 117)         actionBar.setListNavigationCallbacks(mDirectories, this);
45588662 (David A. Velasco 2012-12-04 14:20:02 +0100 118)         
45588662 (David A. Velasco 2012-12-04 14:20:02 +0100 119)         // wait dialog
45588662 (David A. Velasco 2012-12-04 14:20:02 +0100 120)         if (mCurrentDialog != null) {
45588662 (David A. Velasco 2012-12-04 14:20:02 +0100 121)             mCurrentDialog.dismiss();
45588662 (David A. Velasco 2012-12-04 14:20:02 +0100 122)             mCurrentDialog = null;
45588662 (David A. Velasco 2012-12-04 14:20:02 +0100 123)         }
1ecd2b61 (David A. Velasco 2012-08-14 12:37:08 +0200 124)             
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100 125)         Log_OC.d(TAG, "onCreate() end");
1ecd2b61 (David A. Velasco 2012-08-14 12:37:08 +0200 126)     }
1ecd2b61 (David A. Velasco 2012-08-14 12:37:08 +0200 127) 
1ecd2b61 (David A. Velasco 2012-08-14 12:37:08 +0200 128) 
1ecd2b61 (David A. Velasco 2012-08-14 12:37:08 +0200 129)     @Override
1ecd2b61 (David A. Velasco 2012-08-14 12:37:08 +0200 130)     public boolean onOptionsItemSelected(MenuItem item) {
1ecd2b61 (David A. Velasco 2012-08-14 12:37:08 +0200 131)         boolean retval = true;
1ecd2b61 (David A. Velasco 2012-08-14 12:37:08 +0200 132)         switch (item.getItemId()) {
1ecd2b61 (David A. Velasco 2012-08-14 12:37:08 +0200 133)             case android.R.id.home: {
1ecd2b61 (David A. Velasco 2012-08-14 12:37:08 +0200 134)                 if(mCurrentDir != null && mCurrentDir.getParentFile() != null){
1ecd2b61 (David A. Velasco 2012-08-14 12:37:08 +0200 135)                     onBackPressed(); 
1ecd2b61 (David A. Velasco 2012-08-14 12:37:08 +0200 136)                 }
1ecd2b61 (David A. Velasco 2012-08-14 12:37:08 +0200 137)                 break;
1ecd2b61 (David A. Velasco 2012-08-14 12:37:08 +0200 138)             }
1ecd2b61 (David A. Velasco 2012-08-14 12:37:08 +0200 139)             default:
4905f9a3 (David A. Velasco 2013-01-10 16:12:59 +0100 140)                 retval = super.onOptionsItemSelected(item);
1ecd2b61 (David A. Velasco 2012-08-14 12:37:08 +0200 141)         }
1ecd2b61 (David A. Velasco 2012-08-14 12:37:08 +0200 142)         return retval;
1ecd2b61 (David A. Velasco 2012-08-14 12:37:08 +0200 143)     }
1ecd2b61 (David A. Velasco 2012-08-14 12:37:08 +0200 144) 
1ecd2b61 (David A. Velasco 2012-08-14 12:37:08 +0200 145)     
1ecd2b61 (David A. Velasco 2012-08-14 12:37:08 +0200 146)     @Override
1ecd2b61 (David A. Velasco 2012-08-14 12:37:08 +0200 147)     public boolean onNavigationItemSelected(int itemPosition, long itemId) {
1ecd2b61 (David A. Velasco 2012-08-14 12:37:08 +0200 148)         int i = itemPosition;
1ecd2b61 (David A. Velasco 2012-08-14 12:37:08 +0200 149)         while (i-- != 0) {
1ecd2b61 (David A. Velasco 2012-08-14 12:37:08 +0200 150)             onBackPressed();
1ecd2b61 (David A. Velasco 2012-08-14 12:37:08 +0200 151)         }
1ecd2b61 (David A. Velasco 2012-08-14 12:37:08 +0200 152)         // the next operation triggers a new call to this method, but it's necessary to 
1ecd2b61 (David A. Velasco 2012-08-14 12:37:08 +0200 153)         // ensure that the name exposed in the action bar is the current directory when the 
1ecd2b61 (David A. Velasco 2012-08-14 12:37:08 +0200 154)         // user selected it in the navigation list
1ecd2b61 (David A. Velasco 2012-08-14 12:37:08 +0200 155)         if (itemPosition != 0)
1ecd2b61 (David A. Velasco 2012-08-14 12:37:08 +0200 156)             getSupportActionBar().setSelectedNavigationItem(0);
1ecd2b61 (David A. Velasco 2012-08-14 12:37:08 +0200 157)         return true;
1ecd2b61 (David A. Velasco 2012-08-14 12:37:08 +0200 158)     }
1ecd2b61 (David A. Velasco 2012-08-14 12:37:08 +0200 159) 
1ecd2b61 (David A. Velasco 2012-08-14 12:37:08 +0200 160)     
1ecd2b61 (David A. Velasco 2012-08-14 12:37:08 +0200 161)     @Override
1ecd2b61 (David A. Velasco 2012-08-14 12:37:08 +0200 162)     public void onBackPressed() {
1ecd2b61 (David A. Velasco 2012-08-14 12:37:08 +0200 163)         if (mDirectories.getCount() <= 1) {
1ecd2b61 (David A. Velasco 2012-08-14 12:37:08 +0200 164)             finish();
1ecd2b61 (David A. Velasco 2012-08-14 12:37:08 +0200 165)             return;
1ecd2b61 (David A. Velasco 2012-08-14 12:37:08 +0200 166)         }
1ecd2b61 (David A. Velasco 2012-08-14 12:37:08 +0200 167)         popDirname();
1ecd2b61 (David A. Velasco 2012-08-14 12:37:08 +0200 168)         mFileListFragment.onNavigateUp();
1ecd2b61 (David A. Velasco 2012-08-14 12:37:08 +0200 169)         mCurrentDir = mFileListFragment.getCurrentDirectory();
1ecd2b61 (David A. Velasco 2012-08-14 12:37:08 +0200 170)         
1ecd2b61 (David A. Velasco 2012-08-14 12:37:08 +0200 171)         if(mCurrentDir.getParentFile() == null){
1ecd2b61 (David A. Velasco 2012-08-14 12:37:08 +0200 172)             ActionBar actionBar = getSupportActionBar(); 
1ecd2b61 (David A. Velasco 2012-08-14 12:37:08 +0200 173)             actionBar.setDisplayHomeAsUpEnabled(false);
1ecd2b61 (David A. Velasco 2012-08-14 12:37:08 +0200 174)         } 
1ecd2b61 (David A. Velasco 2012-08-14 12:37:08 +0200 175)     }
1ecd2b61 (David A. Velasco 2012-08-14 12:37:08 +0200 176) 
1ecd2b61 (David A. Velasco 2012-08-14 12:37:08 +0200 177)     
1ecd2b61 (David A. Velasco 2012-08-14 12:37:08 +0200 178)     @Override
1ecd2b61 (David A. Velasco 2012-08-14 12:37:08 +0200 179)     protected void onSaveInstanceState(Bundle outState) {
1ecd2b61 (David A. Velasco 2012-08-14 12:37:08 +0200 180)         // responsibility of restore is preferred in onCreate() before than in onRestoreInstanceState when there are Fragments involved
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100 181)         Log_OC.d(TAG, "onSaveInstanceState() start");
1ecd2b61 (David A. Velasco 2012-08-14 12:37:08 +0200 182)         super.onSaveInstanceState(outState);
45588662 (David A. Velasco 2012-12-04 14:20:02 +0100 183)         outState.putString(UploadFilesActivity.KEY_DIRECTORY_PATH, mCurrentDir.getAbsolutePath());
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100 184)         Log_OC.d(TAG, "onSaveInstanceState() end");
1ecd2b61 (David A. Velasco 2012-08-14 12:37:08 +0200 185)     }
1ecd2b61 (David A. Velasco 2012-08-14 12:37:08 +0200 186) 
1ecd2b61 (David A. Velasco 2012-08-14 12:37:08 +0200 187)     
1ecd2b61 (David A. Velasco 2012-08-14 12:37:08 +0200 188)     /**
1ecd2b61 (David A. Velasco 2012-08-14 12:37:08 +0200 189)      * Pushes a directory to the drop down list
1ecd2b61 (David A. Velasco 2012-08-14 12:37:08 +0200 190)      * @param directory to push
1ecd2b61 (David A. Velasco 2012-08-14 12:37:08 +0200 191)      * @throws IllegalArgumentException If the {@link File#isDirectory()} returns false.
1ecd2b61 (David A. Velasco 2012-08-14 12:37:08 +0200 192)      */
1ecd2b61 (David A. Velasco 2012-08-14 12:37:08 +0200 193)     public void pushDirname(File directory) {
1ecd2b61 (David A. Velasco 2012-08-14 12:37:08 +0200 194)         if(!directory.isDirectory()){
1ecd2b61 (David A. Velasco 2012-08-14 12:37:08 +0200 195)             throw new IllegalArgumentException("Only directories may be pushed!");
1ecd2b61 (David A. Velasco 2012-08-14 12:37:08 +0200 196)         }
1ecd2b61 (David A. Velasco 2012-08-14 12:37:08 +0200 197)         mDirectories.insert(directory.getName(), 0);
1ecd2b61 (David A. Velasco 2012-08-14 12:37:08 +0200 198)         mCurrentDir = directory;
1ecd2b61 (David A. Velasco 2012-08-14 12:37:08 +0200 199)     }
1ecd2b61 (David A. Velasco 2012-08-14 12:37:08 +0200 200) 
1ecd2b61 (David A. Velasco 2012-08-14 12:37:08 +0200 201)     /**
1ecd2b61 (David A. Velasco 2012-08-14 12:37:08 +0200 202)      * Pops a directory name from the drop down list
1ecd2b61 (David A. Velasco 2012-08-14 12:37:08 +0200 203)      * @return True, unless the stack is empty
1ecd2b61 (David A. Velasco 2012-08-14 12:37:08 +0200 204)      */
1ecd2b61 (David A. Velasco 2012-08-14 12:37:08 +0200 205)     public boolean popDirname() {
1ecd2b61 (David A. Velasco 2012-08-14 12:37:08 +0200 206)         mDirectories.remove(mDirectories.getItem(0));
1ecd2b61 (David A. Velasco 2012-08-14 12:37:08 +0200 207)         return !mDirectories.isEmpty();
1ecd2b61 (David A. Velasco 2012-08-14 12:37:08 +0200 208)     }
1ecd2b61 (David A. Velasco 2012-08-14 12:37:08 +0200 209) 
1ecd2b61 (David A. Velasco 2012-08-14 12:37:08 +0200 210)     
1ecd2b61 (David A. Velasco 2012-08-14 12:37:08 +0200 211)     // Custom array adapter to override text colors
1ecd2b61 (David A. Velasco 2012-08-14 12:37:08 +0200 212)     private class CustomArrayAdapter<T> extends ArrayAdapter<T> {
1ecd2b61 (David A. Velasco 2012-08-14 12:37:08 +0200 213)     
1ecd2b61 (David A. Velasco 2012-08-14 12:37:08 +0200 214)         public CustomArrayAdapter(UploadFilesActivity ctx, int view) {
1ecd2b61 (David A. Velasco 2012-08-14 12:37:08 +0200 215)             super(ctx, view);
1ecd2b61 (David A. Velasco 2012-08-14 12:37:08 +0200 216)         }
1ecd2b61 (David A. Velasco 2012-08-14 12:37:08 +0200 217)     
1ecd2b61 (David A. Velasco 2012-08-14 12:37:08 +0200 218)         public View getView(int position, View convertView, ViewGroup parent) {
1ecd2b61 (David A. Velasco 2012-08-14 12:37:08 +0200 219)             View v = super.getView(position, convertView, parent);
1ecd2b61 (David A. Velasco 2012-08-14 12:37:08 +0200 220)     
1ecd2b61 (David A. Velasco 2012-08-14 12:37:08 +0200 221)             ((TextView) v).setTextColor(getResources().getColorStateList(
1ecd2b61 (David A. Velasco 2012-08-14 12:37:08 +0200 222)                     android.R.color.white));
1ecd2b61 (David A. Velasco 2012-08-14 12:37:08 +0200 223)             return v;
1ecd2b61 (David A. Velasco 2012-08-14 12:37:08 +0200 224)         }
1ecd2b61 (David A. Velasco 2012-08-14 12:37:08 +0200 225)     
1ecd2b61 (David A. Velasco 2012-08-14 12:37:08 +0200 226)         public View getDropDownView(int position, View convertView,
1ecd2b61 (David A. Velasco 2012-08-14 12:37:08 +0200 227)                 ViewGroup parent) {
1ecd2b61 (David A. Velasco 2012-08-14 12:37:08 +0200 228)             View v = super.getDropDownView(position, convertView, parent);
1ecd2b61 (David A. Velasco 2012-08-14 12:37:08 +0200 229)     
1ecd2b61 (David A. Velasco 2012-08-14 12:37:08 +0200 230)             ((TextView) v).setTextColor(getResources().getColorStateList(
1ecd2b61 (David A. Velasco 2012-08-14 12:37:08 +0200 231)                     android.R.color.white));
1ecd2b61 (David A. Velasco 2012-08-14 12:37:08 +0200 232)     
1ecd2b61 (David A. Velasco 2012-08-14 12:37:08 +0200 233)             return v;
1ecd2b61 (David A. Velasco 2012-08-14 12:37:08 +0200 234)         }
1ecd2b61 (David A. Velasco 2012-08-14 12:37:08 +0200 235)     
1ecd2b61 (David A. Velasco 2012-08-14 12:37:08 +0200 236)     }
1ecd2b61 (David A. Velasco 2012-08-14 12:37:08 +0200 237) 
1ecd2b61 (David A. Velasco 2012-08-14 12:37:08 +0200 238)     /**
1ecd2b61 (David A. Velasco 2012-08-14 12:37:08 +0200 239)      * {@inheritDoc}
1ecd2b61 (David A. Velasco 2012-08-14 12:37:08 +0200 240)      */
1ecd2b61 (David A. Velasco 2012-08-14 12:37:08 +0200 241)     @Override
1ecd2b61 (David A. Velasco 2012-08-14 12:37:08 +0200 242)     public void onDirectoryClick(File directory) {
1ecd2b61 (David A. Velasco 2012-08-14 12:37:08 +0200 243)         pushDirname(directory);
1ecd2b61 (David A. Velasco 2012-08-14 12:37:08 +0200 244)         ActionBar actionBar = getSupportActionBar();
1ecd2b61 (David A. Velasco 2012-08-14 12:37:08 +0200 245)         actionBar.setDisplayHomeAsUpEnabled(true);
1ecd2b61 (David A. Velasco 2012-08-14 12:37:08 +0200 246)     }
1ecd2b61 (David A. Velasco 2012-08-14 12:37:08 +0200 247)     
1ecd2b61 (David A. Velasco 2012-08-14 12:37:08 +0200 248)     
1ecd2b61 (David A. Velasco 2012-08-14 12:37:08 +0200 249)     /**
1ecd2b61 (David A. Velasco 2012-08-14 12:37:08 +0200 250)      * {@inheritDoc}
1ecd2b61 (David A. Velasco 2012-08-14 12:37:08 +0200 251)      */
1ecd2b61 (David A. Velasco 2012-08-14 12:37:08 +0200 252)     @Override
1ecd2b61 (David A. Velasco 2012-08-14 12:37:08 +0200 253)     public void onFileClick(File file) {
1ecd2b61 (David A. Velasco 2012-08-14 12:37:08 +0200 254)         // nothing to do
1ecd2b61 (David A. Velasco 2012-08-14 12:37:08 +0200 255)     }
a2d4e98a (David A. Velasco 2012-10-03 16:48:06 +0200 256)     
a2d4e98a (David A. Velasco 2012-10-03 16:48:06 +0200 257)     /**
a2d4e98a (David A. Velasco 2012-10-03 16:48:06 +0200 258)      * {@inheritDoc}
a2d4e98a (David A. Velasco 2012-10-03 16:48:06 +0200 259)      */
a2d4e98a (David A. Velasco 2012-10-03 16:48:06 +0200 260)     @Override
a2d4e98a (David A. Velasco 2012-10-03 16:48:06 +0200 261)     public File getInitialDirectory() {
a2d4e98a (David A. Velasco 2012-10-03 16:48:06 +0200 262)         return mCurrentDir;
a2d4e98a (David A. Velasco 2012-10-03 16:48:06 +0200 263)     }
1ecd2b61 (David A. Velasco 2012-08-14 12:37:08 +0200 264) 
1ecd2b61 (David A. Velasco 2012-08-14 12:37:08 +0200 265) 
1ecd2b61 (David A. Velasco 2012-08-14 12:37:08 +0200 266)     /**
1ecd2b61 (David A. Velasco 2012-08-14 12:37:08 +0200 267)      * Performs corresponding action when user presses 'Cancel' or 'Upload' button
45588662 (David A. Velasco 2012-12-04 14:20:02 +0100 268)      * 
45588662 (David A. Velasco 2012-12-04 14:20:02 +0100 269)      * TODO Make here the real request to the Upload service ; will require to receive the account and 
45588662 (David A. Velasco 2012-12-04 14:20:02 +0100 270)      * target folder where the upload must be done in the received intent.
1ecd2b61 (David A. Velasco 2012-08-14 12:37:08 +0200 271)      */
1ecd2b61 (David A. Velasco 2012-08-14 12:37:08 +0200 272)     @Override
1ecd2b61 (David A. Velasco 2012-08-14 12:37:08 +0200 273)     public void onClick(View v) {
1ecd2b61 (David A. Velasco 2012-08-14 12:37:08 +0200 274)         if (v.getId() == R.id.upload_files_btn_cancel) {
1ecd2b61 (David A. Velasco 2012-08-14 12:37:08 +0200 275)             setResult(RESULT_CANCELED);
1ecd2b61 (David A. Velasco 2012-08-14 12:37:08 +0200 276)             finish();
1ecd2b61 (David A. Velasco 2012-08-14 12:37:08 +0200 277)             
1ecd2b61 (David A. Velasco 2012-08-14 12:37:08 +0200 278)         } else if (v.getId() == R.id.upload_files_btn_upload) {
45588662 (David A. Velasco 2012-12-04 14:20:02 +0100 279)             new CheckAvailableSpaceTask().execute();
45588662 (David A. Velasco 2012-12-04 14:20:02 +0100 280)         }
45588662 (David A. Velasco 2012-12-04 14:20:02 +0100 281)     }
45588662 (David A. Velasco 2012-12-04 14:20:02 +0100 282) 
45588662 (David A. Velasco 2012-12-04 14:20:02 +0100 283) 
45588662 (David A. Velasco 2012-12-04 14:20:02 +0100 284)     /**
45588662 (David A. Velasco 2012-12-04 14:20:02 +0100 285)      * Asynchronous task checking if there is space enough to copy all the files chosen
45588662 (David A. Velasco 2012-12-04 14:20:02 +0100 286)      * to upload into the ownCloud local folder.
45588662 (David A. Velasco 2012-12-04 14:20:02 +0100 287)      * 
45588662 (David A. Velasco 2012-12-04 14:20:02 +0100 288)      * Maybe an AsyncTask is not strictly necessary, but who really knows.
45588662 (David A. Velasco 2012-12-04 14:20:02 +0100 289)      * 
45588662 (David A. Velasco 2012-12-04 14:20:02 +0100 290)      * @author David A. Velasco
45588662 (David A. Velasco 2012-12-04 14:20:02 +0100 291)      */
45588662 (David A. Velasco 2012-12-04 14:20:02 +0100 292)     private class CheckAvailableSpaceTask extends AsyncTask<Void, Void, Boolean> {
45588662 (David A. Velasco 2012-12-04 14:20:02 +0100 293) 
45588662 (David A. Velasco 2012-12-04 14:20:02 +0100 294)         /**
45588662 (David A. Velasco 2012-12-04 14:20:02 +0100 295)          * Updates the UI before trying the movement
45588662 (David A. Velasco 2012-12-04 14:20:02 +0100 296)          */
45588662 (David A. Velasco 2012-12-04 14:20:02 +0100 297)         @Override
45588662 (David A. Velasco 2012-12-04 14:20:02 +0100 298)         protected void onPreExecute () {
45588662 (David A. Velasco 2012-12-04 14:20:02 +0100 299)             /// progress dialog and disable 'Move' button
45588662 (David A. Velasco 2012-12-04 14:20:02 +0100 300)             mCurrentDialog = IndeterminateProgressDialog.newInstance(R.string.wait_a_moment, false);
45588662 (David A. Velasco 2012-12-04 14:20:02 +0100 301)             mCurrentDialog.show(getSupportFragmentManager(), WAIT_DIALOG_TAG);
45588662 (David A. Velasco 2012-12-04 14:20:02 +0100 302)         }
45588662 (David A. Velasco 2012-12-04 14:20:02 +0100 303)         
45588662 (David A. Velasco 2012-12-04 14:20:02 +0100 304)         
45588662 (David A. Velasco 2012-12-04 14:20:02 +0100 305)         /**
45588662 (David A. Velasco 2012-12-04 14:20:02 +0100 306)          * Checks the available space
45588662 (David A. Velasco 2012-12-04 14:20:02 +0100 307)          * 
45588662 (David A. Velasco 2012-12-04 14:20:02 +0100 308)          * @return     'True' if there is space enough.
45588662 (David A. Velasco 2012-12-04 14:20:02 +0100 309)          */
45588662 (David A. Velasco 2012-12-04 14:20:02 +0100 310)         @Override
45588662 (David A. Velasco 2012-12-04 14:20:02 +0100 311)         protected Boolean doInBackground(Void... params) {
45588662 (David A. Velasco 2012-12-04 14:20:02 +0100 312)             String[] checkedFilePaths = mFileListFragment.getCheckedFilePaths();
45588662 (David A. Velasco 2012-12-04 14:20:02 +0100 313)             long total = 0;
a1a31b68 (David A. Velasco 2013-01-08 17:48:58 +0100 314)             for (int i=0; checkedFilePaths != null && i < checkedFilePaths.length ; i++) {
45588662 (David A. Velasco 2012-12-04 14:20:02 +0100 315)                 String localPath = checkedFilePaths[i];
45588662 (David A. Velasco 2012-12-04 14:20:02 +0100 316)                 File localFile = new File(localPath);
45588662 (David A. Velasco 2012-12-04 14:20:02 +0100 317)                 total += localFile.length();
45588662 (David A. Velasco 2012-12-04 14:20:02 +0100 318)             }
c43b15de (David A. Velasco 2013-06-17 14:21:33 +0200 319)             return (FileStorageUtils.getUsableSpace(mAccountOnCreation.name) >= total);
45588662 (David A. Velasco 2012-12-04 14:20:02 +0100 320)         }
45588662 (David A. Velasco 2012-12-04 14:20:02 +0100 321) 
45588662 (David A. Velasco 2012-12-04 14:20:02 +0100 322)         /**
45588662 (David A. Velasco 2012-12-04 14:20:02 +0100 323)          * Updates the activity UI after the check of space is done.
45588662 (David A. Velasco 2012-12-04 14:20:02 +0100 324)          * 
45588662 (David A. Velasco 2012-12-04 14:20:02 +0100 325)          * If there is not space enough. shows a new dialog to query the user if wants to move the files instead
45588662 (David A. Velasco 2012-12-04 14:20:02 +0100 326)          * of copy them.
45588662 (David A. Velasco 2012-12-04 14:20:02 +0100 327)          * 
45588662 (David A. Velasco 2012-12-04 14:20:02 +0100 328)          * @param result        'True' when there is space enough to copy all the selected files.
45588662 (David A. Velasco 2012-12-04 14:20:02 +0100 329)          */
45588662 (David A. Velasco 2012-12-04 14:20:02 +0100 330)         @Override
45588662 (David A. Velasco 2012-12-04 14:20:02 +0100 331)         protected void onPostExecute(Boolean result) {
45588662 (David A. Velasco 2012-12-04 14:20:02 +0100 332)             mCurrentDialog.dismiss();
45588662 (David A. Velasco 2012-12-04 14:20:02 +0100 333)             mCurrentDialog = null;
45588662 (David A. Velasco 2012-12-04 14:20:02 +0100 334)             
45588662 (David A. Velasco 2012-12-04 14:20:02 +0100 335)             if (result) {
45588662 (David A. Velasco 2012-12-04 14:20:02 +0100 336)                 // return the list of selected files (success)
45588662 (David A. Velasco 2012-12-04 14:20:02 +0100 337)                 Intent data = new Intent();
45588662 (David A. Velasco 2012-12-04 14:20:02 +0100 338)                 data.putExtra(EXTRA_CHOSEN_FILES, mFileListFragment.getCheckedFilePaths());
45588662 (David A. Velasco 2012-12-04 14:20:02 +0100 339)                 setResult(RESULT_OK, data);
45588662 (David A. Velasco 2012-12-04 14:20:02 +0100 340)                 finish();
45588662 (David A. Velasco 2012-12-04 14:20:02 +0100 341)                 
45588662 (David A. Velasco 2012-12-04 14:20:02 +0100 342)             } else {
45588662 (David A. Velasco 2012-12-04 14:20:02 +0100 343)                 // show a dialog to query the user if wants to move the selected files to the ownCloud folder instead of copying
45588662 (David A. Velasco 2012-12-04 14:20:02 +0100 344)                 String[] args = {getString(R.string.app_name)};
45588662 (David A. Velasco 2012-12-04 14:20:02 +0100 345)                 ConfirmationDialogFragment dialog = ConfirmationDialogFragment.newInstance(R.string.upload_query_move_foreign_files, args, R.string.common_yes, -1, R.string.common_no);
45588662 (David A. Velasco 2012-12-04 14:20:02 +0100 346)                 dialog.setOnConfirmationListener(UploadFilesActivity.this);
f2474ae2 (David A. Velasco 2013-02-18 11:23:18 +0100 347)                 dialog.show(getSupportFragmentManager(), QUERY_TO_MOVE_DIALOG_TAG);
45588662 (David A. Velasco 2012-12-04 14:20:02 +0100 348)             }
45588662 (David A. Velasco 2012-12-04 14:20:02 +0100 349)         }
45588662 (David A. Velasco 2012-12-04 14:20:02 +0100 350)     }
45588662 (David A. Velasco 2012-12-04 14:20:02 +0100 351) 
45588662 (David A. Velasco 2012-12-04 14:20:02 +0100 352)     @Override
45588662 (David A. Velasco 2012-12-04 14:20:02 +0100 353)     public void onConfirmation(String callerTag) {
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100 354)         Log_OC.d(TAG, "Positive button in dialog was clicked; dialog tag is " + callerTag);
45588662 (David A. Velasco 2012-12-04 14:20:02 +0100 355)         if (callerTag.equals(QUERY_TO_MOVE_DIALOG_TAG)) {
45588662 (David A. Velasco 2012-12-04 14:20:02 +0100 356)             // return the list of selected files to the caller activity (success), signaling that they should be moved to the ownCloud folder, instead of copied
1ecd2b61 (David A. Velasco 2012-08-14 12:37:08 +0200 357)             Intent data = new Intent();
1ecd2b61 (David A. Velasco 2012-08-14 12:37:08 +0200 358)             data.putExtra(EXTRA_CHOSEN_FILES, mFileListFragment.getCheckedFilePaths());
45588662 (David A. Velasco 2012-12-04 14:20:02 +0100 359)             setResult(RESULT_OK_AND_MOVE, data);
1ecd2b61 (David A. Velasco 2012-08-14 12:37:08 +0200 360)             finish();
1ecd2b61 (David A. Velasco 2012-08-14 12:37:08 +0200 361)         }
1ecd2b61 (David A. Velasco 2012-08-14 12:37:08 +0200 362)     }
45588662 (David A. Velasco 2012-12-04 14:20:02 +0100 363) 
45588662 (David A. Velasco 2012-12-04 14:20:02 +0100 364) 
45588662 (David A. Velasco 2012-12-04 14:20:02 +0100 365)     @Override
45588662 (David A. Velasco 2012-12-04 14:20:02 +0100 366)     public void onNeutral(String callerTag) {
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100 367)         Log_OC.d(TAG, "Phantom neutral button in dialog was clicked; dialog tag is " + callerTag);
45588662 (David A. Velasco 2012-12-04 14:20:02 +0100 368)     }
45588662 (David A. Velasco 2012-12-04 14:20:02 +0100 369) 
45588662 (David A. Velasco 2012-12-04 14:20:02 +0100 370) 
45588662 (David A. Velasco 2012-12-04 14:20:02 +0100 371)     @Override
45588662 (David A. Velasco 2012-12-04 14:20:02 +0100 372)     public void onCancel(String callerTag) {
45588662 (David A. Velasco 2012-12-04 14:20:02 +0100 373)         /// nothing to do; don't finish, let the user change the selection
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100 374)         Log_OC.d(TAG, "Negative button in dialog was clicked; dialog tag is " + callerTag);
c43b15de (David A. Velasco 2013-06-17 14:21:33 +0200 375)     }
c43b15de (David A. Velasco 2013-06-17 14:21:33 +0200 376) 
c43b15de (David A. Velasco 2013-06-17 14:21:33 +0200 377) 
c43b15de (David A. Velasco 2013-06-17 14:21:33 +0200 378)     @Override
c43b15de (David A. Velasco 2013-06-17 14:21:33 +0200 379)     protected void onAccountSet(boolean stateWasRecovered) {
c43b15de (David A. Velasco 2013-06-17 14:21:33 +0200 380)         if (getAccount() != null) {
c43b15de (David A. Velasco 2013-06-17 14:21:33 +0200 381)             if (!mAccountOnCreation.equals(getAccount())) {
c43b15de (David A. Velasco 2013-06-17 14:21:33 +0200 382)                 setResult(RESULT_CANCELED);
c43b15de (David A. Velasco 2013-06-17 14:21:33 +0200 383)                 finish();
c43b15de (David A. Velasco 2013-06-17 14:21:33 +0200 384)             }
c43b15de (David A. Velasco 2013-06-17 14:21:33 +0200 385)             
c43b15de (David A. Velasco 2013-06-17 14:21:33 +0200 386)         } else {
c43b15de (David A. Velasco 2013-06-17 14:21:33 +0200 387)             Log_OC.wtf(TAG, "onAccountChanged was called with NULL account associated!");
c43b15de (David A. Velasco 2013-06-17 14:21:33 +0200 388)             setResult(RESULT_CANCELED);
c43b15de (David A. Velasco 2013-06-17 14:21:33 +0200 389)             finish();
c43b15de (David A. Velasco 2013-06-17 14:21:33 +0200 390)         }
45588662 (David A. Velasco 2012-12-04 14:20:02 +0100 391)     }    
45588662 (David A. Velasco 2012-12-04 14:20:02 +0100 392) 
1ecd2b61 (David A. Velasco 2012-08-14 12:37:08 +0200 393)     
1ecd2b61 (David A. Velasco 2012-08-14 12:37:08 +0200 394) }
