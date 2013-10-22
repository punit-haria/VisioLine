1ecd2b61 (David A. Velasco 2012-08-14 12:37:08 +0200   1) /* ownCloud Android client application
1ecd2b61 (David A. Velasco 2012-08-14 12:37:08 +0200   2)  *   Copyright (C) 2011  Bartek Przybylski
bb257ec7 (David A. Velasco 2013-02-07 18:45:10 +0100   3)  *   Copyright (C) 2012-2013 ownCloud Inc.
1ecd2b61 (David A. Velasco 2012-08-14 12:37:08 +0200   4)  *
1ecd2b61 (David A. Velasco 2012-08-14 12:37:08 +0200   5)  *   This program is free software: you can redistribute it and/or modify
bdc0332c (David A. Velasco 2013-04-17 12:26:13 +0200   6)  *   it under the terms of the GNU General Public License version 2,
bdc0332c (David A. Velasco 2013-04-17 12:26:13 +0200   7)  *   as published by the Free Software Foundation.
1ecd2b61 (David A. Velasco 2012-08-14 12:37:08 +0200   8)  *
1ecd2b61 (David A. Velasco 2012-08-14 12:37:08 +0200   9)  *   This program is distributed in the hope that it will be useful,
1ecd2b61 (David A. Velasco 2012-08-14 12:37:08 +0200  10)  *   but WITHOUT ANY WARRANTY; without even the implied warranty of
1ecd2b61 (David A. Velasco 2012-08-14 12:37:08 +0200  11)  *   MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
1ecd2b61 (David A. Velasco 2012-08-14 12:37:08 +0200  12)  *   GNU General Public License for more details.
1ecd2b61 (David A. Velasco 2012-08-14 12:37:08 +0200  13)  *
1ecd2b61 (David A. Velasco 2012-08-14 12:37:08 +0200  14)  *   You should have received a copy of the GNU General Public License
1ecd2b61 (David A. Velasco 2012-08-14 12:37:08 +0200  15)  *   along with this program.  If not, see <http://www.gnu.org/licenses/>.
1ecd2b61 (David A. Velasco 2012-08-14 12:37:08 +0200  16)  *
1ecd2b61 (David A. Velasco 2012-08-14 12:37:08 +0200  17)  */
1ecd2b61 (David A. Velasco 2012-08-14 12:37:08 +0200  18) package com.owncloud.android.ui.fragment;
1ecd2b61 (David A. Velasco 2012-08-14 12:37:08 +0200  19) 
454f1513 (David A. Velasco 2012-10-31 17:41:57 +0100  20) import java.io.File;
658267ad (David A. Velasco 2012-11-22 13:06:54 +0100  21) import java.util.ArrayList;
658267ad (David A. Velasco 2012-11-22 13:06:54 +0100  22) import java.util.List;
454f1513 (David A. Velasco 2012-10-31 17:41:57 +0100  23) 
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100  24) import com.owncloud.android.Log_OC;
454f1513 (David A. Velasco 2012-10-31 17:41:57 +0100  25) import com.owncloud.android.R;
c4ac05de (David A. Velasco 2013-06-18 11:34:08 +0200  26) import com.owncloud.android.authentication.AccountUtils;
1ecd2b61 (David A. Velasco 2012-08-14 12:37:08 +0200  27) import com.owncloud.android.datamodel.DataStorageManager;
1ecd2b61 (David A. Velasco 2012-08-14 12:37:08 +0200  28) import com.owncloud.android.datamodel.OCFile;
edb380e0 (David A. Velasco 2013-05-27 15:01:54 +0200  29) import com.owncloud.android.files.FileHandler;
ac6b3d25 (David A. Velasco 2012-11-07 14:17:58 +0100  30) import com.owncloud.android.files.services.FileDownloader.FileDownloaderBinder;
ac6b3d25 (David A. Velasco 2012-11-07 14:17:58 +0100  31) import com.owncloud.android.files.services.FileUploader.FileUploaderBinder;
454f1513 (David A. Velasco 2012-10-31 17:41:57 +0100  32) import com.owncloud.android.operations.OnRemoteOperationListener;
454f1513 (David A. Velasco 2012-10-31 17:41:57 +0100  33) import com.owncloud.android.operations.RemoteOperation;
454f1513 (David A. Velasco 2012-10-31 17:41:57 +0100  34) import com.owncloud.android.operations.RemoveFileOperation;
454f1513 (David A. Velasco 2012-10-31 17:41:57 +0100  35) import com.owncloud.android.operations.RenameFileOperation;
ba52dfdf (David A. Velasco 2012-11-23 14:30:36 +0100  36) import com.owncloud.android.operations.SynchronizeFileOperation;
454f1513 (David A. Velasco 2012-10-31 17:41:57 +0100  37) import com.owncloud.android.ui.activity.FileDisplayActivity;
68ce2e7a (David A. Velasco 2012-10-09 14:53:25 +0200  38) import com.owncloud.android.ui.activity.TransferServiceGetter;
1ecd2b61 (David A. Velasco 2012-08-14 12:37:08 +0200  39) import com.owncloud.android.ui.adapter.FileListListAdapter;
454f1513 (David A. Velasco 2012-10-31 17:41:57 +0100  40) import com.owncloud.android.ui.dialog.EditNameDialog;
85e9a40d (David A. Velasco 2012-11-08 17:21:45 +0100  41) import com.owncloud.android.ui.dialog.EditNameDialog.EditNameDialogListener;
454f1513 (David A. Velasco 2012-10-31 17:41:57 +0100  42) import com.owncloud.android.ui.fragment.ConfirmationDialogFragment.ConfirmationDialogFragmentListener;
fd396289 (David A. Velasco 2013-05-30 17:53:21 +0200  43) import com.owncloud.android.ui.preview.PreviewImageFragment;
fd396289 (David A. Velasco 2013-05-30 17:53:21 +0200  44) import com.owncloud.android.ui.preview.PreviewMediaFragment;
454f1513 (David A. Velasco 2012-10-31 17:41:57 +0100  45) 
ac6b3d25 (David A. Velasco 2012-11-07 14:17:58 +0100  46) import android.accounts.Account;
1ecd2b61 (David A. Velasco 2012-08-14 12:37:08 +0200  47) import android.app.Activity;
1ecd2b61 (David A. Velasco 2012-08-14 12:37:08 +0200  48) import android.os.Bundle;
454f1513 (David A. Velasco 2012-10-31 17:41:57 +0100  49) import android.os.Handler;
454f1513 (David A. Velasco 2012-10-31 17:41:57 +0100  50) import android.view.ContextMenu;
454f1513 (David A. Velasco 2012-10-31 17:41:57 +0100  51) import android.view.MenuInflater;
454f1513 (David A. Velasco 2012-10-31 17:41:57 +0100  52) import android.view.MenuItem;
1ecd2b61 (David A. Velasco 2012-08-14 12:37:08 +0200  53) import android.view.View;
1ecd2b61 (David A. Velasco 2012-08-14 12:37:08 +0200  54) import android.widget.AdapterView;
454f1513 (David A. Velasco 2012-10-31 17:41:57 +0100  55) import android.widget.AdapterView.AdapterContextMenuInfo;
1ecd2b61 (David A. Velasco 2012-08-14 12:37:08 +0200  56) 
1ecd2b61 (David A. Velasco 2012-08-14 12:37:08 +0200  57) /**
1ecd2b61 (David A. Velasco 2012-08-14 12:37:08 +0200  58)  * A Fragment that lists all files and folders in a given path.
1ecd2b61 (David A. Velasco 2012-08-14 12:37:08 +0200  59)  * 
1ecd2b61 (David A. Velasco 2012-08-14 12:37:08 +0200  60)  * @author Bartek Przybylski
1ecd2b61 (David A. Velasco 2012-08-14 12:37:08 +0200  61)  * 
1ecd2b61 (David A. Velasco 2012-08-14 12:37:08 +0200  62)  */
fd396289 (David A. Velasco 2013-05-30 17:53:21 +0200  63) public class OCFileListFragment extends ExtendedListFragment implements EditNameDialogListener, ConfirmationDialogFragmentListener {
c70f3333 (David A. Velasco 2013-06-04 14:30:29 +0200  64)     
fd396289 (David A. Velasco 2013-05-30 17:53:21 +0200  65)     private static final String TAG = OCFileListFragment.class.getSimpleName();
fb500a90 (David A. Velasco 2013-06-28 13:35:03 +0200  66) 
fb500a90 (David A. Velasco 2013-06-28 13:35:03 +0200  67)     private static final String MY_PACKAGE = OCFileListFragment.class.getPackage() != null ? OCFileListFragment.class.getPackage().getName() : "com.owncloud.android.ui.fragment";
fb500a90 (David A. Velasco 2013-06-28 13:35:03 +0200  68)     private static final String EXTRA_FILE = MY_PACKAGE + ".extra.FILE";
1ecd2b61 (David A. Velasco 2012-08-14 12:37:08 +0200  69)     
1ecd2b61 (David A. Velasco 2012-08-14 12:37:08 +0200  70)     private OCFileListFragment.ContainerActivity mContainerActivity;
1ecd2b61 (David A. Velasco 2012-08-14 12:37:08 +0200  71)     
1ecd2b61 (David A. Velasco 2012-08-14 12:37:08 +0200  72)     private OCFile mFile = null;
1ecd2b61 (David A. Velasco 2012-08-14 12:37:08 +0200  73)     private FileListListAdapter mAdapter;
454f1513 (David A. Velasco 2012-10-31 17:41:57 +0100  74)     
454f1513 (David A. Velasco 2012-10-31 17:41:57 +0100  75)     private Handler mHandler;
454f1513 (David A. Velasco 2012-10-31 17:41:57 +0100  76)     private OCFile mTargetFile;
80448dc1 (David A. Velasco 2012-12-05 12:22:26 +0100  77)     
1ecd2b61 (David A. Velasco 2012-08-14 12:37:08 +0200  78)     /**
1ecd2b61 (David A. Velasco 2012-08-14 12:37:08 +0200  79)      * {@inheritDoc}
1ecd2b61 (David A. Velasco 2012-08-14 12:37:08 +0200  80)      */
1ecd2b61 (David A. Velasco 2012-08-14 12:37:08 +0200  81)     @Override
1ecd2b61 (David A. Velasco 2012-08-14 12:37:08 +0200  82)     public void onAttach(Activity activity) {
1ecd2b61 (David A. Velasco 2012-08-14 12:37:08 +0200  83)         super.onAttach(activity);
fd396289 (David A. Velasco 2013-05-30 17:53:21 +0200  84)         Log_OC.e(TAG, "onAttach");
1ecd2b61 (David A. Velasco 2012-08-14 12:37:08 +0200  85)         try {
1ecd2b61 (David A. Velasco 2012-08-14 12:37:08 +0200  86)             mContainerActivity = (ContainerActivity) activity;
1ecd2b61 (David A. Velasco 2012-08-14 12:37:08 +0200  87)         } catch (ClassCastException e) {
c6a3abf0 (David A. Velasco 2012-10-26 13:45:59 +0200  88)             throw new ClassCastException(activity.toString() + " must implement " + OCFileListFragment.ContainerActivity.class.getSimpleName());
1ecd2b61 (David A. Velasco 2012-08-14 12:37:08 +0200  89)         }
1ecd2b61 (David A. Velasco 2012-08-14 12:37:08 +0200  90)     }
1ecd2b61 (David A. Velasco 2012-08-14 12:37:08 +0200  91)     
1ecd2b61 (David A. Velasco 2012-08-14 12:37:08 +0200  92)     
a2d4e98a (David A. Velasco 2012-10-03 16:48:06 +0200  93)     /**
a2d4e98a (David A. Velasco 2012-10-03 16:48:06 +0200  94)      * {@inheritDoc}
a2d4e98a (David A. Velasco 2012-10-03 16:48:06 +0200  95)      */
1ecd2b61 (David A. Velasco 2012-08-14 12:37:08 +0200  96)     @Override
1ecd2b61 (David A. Velasco 2012-08-14 12:37:08 +0200  97)     public void onActivityCreated(Bundle savedInstanceState) {
68ce2e7a (David A. Velasco 2012-10-09 14:53:25 +0200  98)         super.onActivityCreated(savedInstanceState);
fd396289 (David A. Velasco 2013-05-30 17:53:21 +0200  99)         Log_OC.e(TAG, "onActivityCreated() start");
10b014a1 (David A. Velasco 2013-06-17 11:16:39 +0200 100)         mAdapter = new FileListListAdapter(getActivity(), mContainerActivity);
fb500a90 (David A. Velasco 2013-06-28 13:35:03 +0200 101)         if (savedInstanceState != null) {
fb500a90 (David A. Velasco 2013-06-28 13:35:03 +0200 102)             mFile = savedInstanceState.getParcelable(EXTRA_FILE);
fb500a90 (David A. Velasco 2013-06-28 13:35:03 +0200 103)         }
a2d4e98a (David A. Velasco 2012-10-03 16:48:06 +0200 104)         setListAdapter(mAdapter);
a2d4e98a (David A. Velasco 2012-10-03 16:48:06 +0200 105)         
454f1513 (David A. Velasco 2012-10-31 17:41:57 +0100 106)         registerForContextMenu(getListView());
454f1513 (David A. Velasco 2012-10-31 17:41:57 +0100 107)         getListView().setOnCreateContextMenuListener(this);        
454f1513 (David A. Velasco 2012-10-31 17:41:57 +0100 108)         
454f1513 (David A. Velasco 2012-10-31 17:41:57 +0100 109)         mHandler = new Handler();
eee74aa5 (masensio         2013-07-04 18:47:38 +0200 110) 
a2d4e98a (David A. Velasco 2012-10-03 16:48:06 +0200 111)     }
a2d4e98a (David A. Velasco 2012-10-03 16:48:06 +0200 112)     
fb500a90 (David A. Velasco 2013-06-28 13:35:03 +0200 113)     /**
fb500a90 (David A. Velasco 2013-06-28 13:35:03 +0200 114)      * Saves the current listed folder.
fb500a90 (David A. Velasco 2013-06-28 13:35:03 +0200 115)      */
fb500a90 (David A. Velasco 2013-06-28 13:35:03 +0200 116)     @Override
fb500a90 (David A. Velasco 2013-06-28 13:35:03 +0200 117)     public void onSaveInstanceState (Bundle outState) {
fb500a90 (David A. Velasco 2013-06-28 13:35:03 +0200 118)         super.onSaveInstanceState(outState);
fb500a90 (David A. Velasco 2013-06-28 13:35:03 +0200 119)         outState.putParcelable(EXTRA_FILE, mFile);
fb500a90 (David A. Velasco 2013-06-28 13:35:03 +0200 120)     }
994e81d7 (masensio         2013-07-04 18:47:38 +0200 121) 
0bb59e0f (David A. Velasco 2012-10-17 13:44:36 +0200 122) 
fd396289 (David A. Velasco 2013-05-30 17:53:21 +0200 123)     /**
fd396289 (David A. Velasco 2013-05-30 17:53:21 +0200 124)      * Call this, when the user presses the up button
fd396289 (David A. Velasco 2013-05-30 17:53:21 +0200 125)      */
fd396289 (David A. Velasco 2013-05-30 17:53:21 +0200 126)     public void onBrowseUp() {
fd396289 (David A. Velasco 2013-05-30 17:53:21 +0200 127)         OCFile parentDir = null;
a2d4e98a (David A. Velasco 2012-10-03 16:48:06 +0200 128)         
fd396289 (David A. Velasco 2013-05-30 17:53:21 +0200 129)         if(mFile != null){
fd396289 (David A. Velasco 2013-05-30 17:53:21 +0200 130)             DataStorageManager storageManager = mContainerActivity.getStorageManager();
fd396289 (David A. Velasco 2013-05-30 17:53:21 +0200 131)             parentDir = storageManager.getFileById(mFile.getParentId());
fd396289 (David A. Velasco 2013-05-30 17:53:21 +0200 132)             mFile = parentDir;
fd396289 (David A. Velasco 2013-05-30 17:53:21 +0200 133)         }
fd396289 (David A. Velasco 2013-05-30 17:53:21 +0200 134)         listDirectory(parentDir);
1ecd2b61 (David A. Velasco 2012-08-14 12:37:08 +0200 135)     }
1ecd2b61 (David A. Velasco 2012-08-14 12:37:08 +0200 136)     
1ecd2b61 (David A. Velasco 2012-08-14 12:37:08 +0200 137)     @Override
1ecd2b61 (David A. Velasco 2012-08-14 12:37:08 +0200 138)     public void onItemClick(AdapterView<?> l, View v, int position, long id) {
1ecd2b61 (David A. Velasco 2012-08-14 12:37:08 +0200 139)         OCFile file = (OCFile) mAdapter.getItem(position);
1ecd2b61 (David A. Velasco 2012-08-14 12:37:08 +0200 140)         if (file != null) {
fd396289 (David A. Velasco 2013-05-30 17:53:21 +0200 141)             if (file.isDirectory()) { 
fd396289 (David A. Velasco 2013-05-30 17:53:21 +0200 142)                 // update state and view of this fragment
1ecd2b61 (David A. Velasco 2012-08-14 12:37:08 +0200 143)                 listDirectory(file);
fd396289 (David A. Velasco 2013-05-30 17:53:21 +0200 144)                 // then, notify parent activity to let it update its state and view, and other fragments
fd396289 (David A. Velasco 2013-05-30 17:53:21 +0200 145)                 mContainerActivity.onBrowsedDownTo(file);
fd396289 (David A. Velasco 2013-05-30 17:53:21 +0200 146)                 
fd396289 (David A. Velasco 2013-05-30 17:53:21 +0200 147)             } else { /// Click on a file
fd396289 (David A. Velasco 2013-05-30 17:53:21 +0200 148)                 if (PreviewImageFragment.canBePreviewed(file)) {
fd396289 (David A. Velasco 2013-05-30 17:53:21 +0200 149)                     // preview image - it handles the download, if needed
fd396289 (David A. Velasco 2013-05-30 17:53:21 +0200 150)                     mContainerActivity.startImagePreview(file);
fd396289 (David A. Velasco 2013-05-30 17:53:21 +0200 151)                     
fd396289 (David A. Velasco 2013-05-30 17:53:21 +0200 152)                 } else if (file.isDown()) {
fd396289 (David A. Velasco 2013-05-30 17:53:21 +0200 153)                     if (PreviewMediaFragment.canBePreviewed(file)) {
fd396289 (David A. Velasco 2013-05-30 17:53:21 +0200 154)                         // media preview
fd396289 (David A. Velasco 2013-05-30 17:53:21 +0200 155)                         mContainerActivity.startMediaPreview(file, 0, true);
fd396289 (David A. Velasco 2013-05-30 17:53:21 +0200 156)                     } else {
fd396289 (David A. Velasco 2013-05-30 17:53:21 +0200 157)                         // open with
fd396289 (David A. Velasco 2013-05-30 17:53:21 +0200 158)                         mContainerActivity.openFile(file);
fd396289 (David A. Velasco 2013-05-30 17:53:21 +0200 159)                     }
fd396289 (David A. Velasco 2013-05-30 17:53:21 +0200 160)                     
fd396289 (David A. Velasco 2013-05-30 17:53:21 +0200 161)                 } else {
fd396289 (David A. Velasco 2013-05-30 17:53:21 +0200 162)                     // automatic download, preview on finish
fd396289 (David A. Velasco 2013-05-30 17:53:21 +0200 163)                     mContainerActivity.startDownloadForPreview(file);
fd396289 (David A. Velasco 2013-05-30 17:53:21 +0200 164)                 }
fd396289 (David A. Velasco 2013-05-30 17:53:21 +0200 165)                     
1ecd2b61 (David A. Velasco 2012-08-14 12:37:08 +0200 166)             }
1ecd2b61 (David A. Velasco 2012-08-14 12:37:08 +0200 167)             
1ecd2b61 (David A. Velasco 2012-08-14 12:37:08 +0200 168)         } else {
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100 169)             Log_OC.d(TAG, "Null object in ListAdapter!!");
1ecd2b61 (David A. Velasco 2012-08-14 12:37:08 +0200 170)         }
1ecd2b61 (David A. Velasco 2012-08-14 12:37:08 +0200 171)         
1ecd2b61 (David A. Velasco 2012-08-14 12:37:08 +0200 172)     }
454f1513 (David A. Velasco 2012-10-31 17:41:57 +0100 173)     
454f1513 (David A. Velasco 2012-10-31 17:41:57 +0100 174)     /**
454f1513 (David A. Velasco 2012-10-31 17:41:57 +0100 175)      * {@inheritDoc}
454f1513 (David A. Velasco 2012-10-31 17:41:57 +0100 176)      */
454f1513 (David A. Velasco 2012-10-31 17:41:57 +0100 177)     @Override
454f1513 (David A. Velasco 2012-10-31 17:41:57 +0100 178)     public void onCreateContextMenu (ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
454f1513 (David A. Velasco 2012-10-31 17:41:57 +0100 179)         super.onCreateContextMenu(menu, v, menuInfo);
454f1513 (David A. Velasco 2012-10-31 17:41:57 +0100 180)         MenuInflater inflater = getActivity().getMenuInflater();
f2474ae2 (David A. Velasco 2013-02-18 11:23:18 +0100 181)         inflater.inflate(R.menu.file_actions_menu, menu);
500cbf9c (David A. Velasco 2012-11-06 15:13:27 +0100 182)         AdapterContextMenuInfo info = (AdapterContextMenuInfo) menuInfo;
500cbf9c (David A. Velasco 2012-11-06 15:13:27 +0100 183)         OCFile targetFile = (OCFile) mAdapter.getItem(info.position);
658267ad (David A. Velasco 2012-11-22 13:06:54 +0100 184)         List<Integer> toHide = new ArrayList<Integer>();    
658267ad (David A. Velasco 2012-11-22 13:06:54 +0100 185)         List<Integer> toDisable = new ArrayList<Integer>();  
658267ad (David A. Velasco 2012-11-22 13:06:54 +0100 186)         
ba52dfdf (David A. Velasco 2012-11-23 14:30:36 +0100 187)         MenuItem item = null;
500cbf9c (David A. Velasco 2012-11-06 15:13:27 +0100 188)         if (targetFile.isDirectory()) {
658267ad (David A. Velasco 2012-11-22 13:06:54 +0100 189)             // contextual menu for folders
f2474ae2 (David A. Velasco 2013-02-18 11:23:18 +0100 190)             toHide.add(R.id.action_open_file_with);
f2474ae2 (David A. Velasco 2013-02-18 11:23:18 +0100 191)             toHide.add(R.id.action_download_file);
f2474ae2 (David A. Velasco 2013-02-18 11:23:18 +0100 192)             toHide.add(R.id.action_cancel_download);
f2474ae2 (David A. Velasco 2013-02-18 11:23:18 +0100 193)             toHide.add(R.id.action_cancel_upload);
6a6d8331 (David A. Velasco 2013-04-24 11:04:23 +0200 194)             toHide.add(R.id.action_sync_file);
f2474ae2 (David A. Velasco 2013-02-18 11:23:18 +0100 195)             toHide.add(R.id.action_see_details);
658267ad (David A. Velasco 2012-11-22 13:06:54 +0100 196)             if (    mContainerActivity.getFileDownloaderBinder().isDownloading(AccountUtils.getCurrentOwnCloudAccount(getActivity()), targetFile) ||
658267ad (David A. Velasco 2012-11-22 13:06:54 +0100 197)                     mContainerActivity.getFileUploaderBinder().isUploading(AccountUtils.getCurrentOwnCloudAccount(getActivity()), targetFile)           ) {
f2474ae2 (David A. Velasco 2013-02-18 11:23:18 +0100 198)                 toDisable.add(R.id.action_rename_file);
f2474ae2 (David A. Velasco 2013-02-18 11:23:18 +0100 199)                 toDisable.add(R.id.action_remove_file);
658267ad (David A. Velasco 2012-11-22 13:06:54 +0100 200)                 
658267ad (David A. Velasco 2012-11-22 13:06:54 +0100 201)             }
500cbf9c (David A. Velasco 2012-11-06 15:13:27 +0100 202)             
500cbf9c (David A. Velasco 2012-11-06 15:13:27 +0100 203)         } else {
658267ad (David A. Velasco 2012-11-22 13:06:54 +0100 204)             // contextual menu for regular files
782186f5 (David A. Velasco 2013-06-10 14:21:24 +0200 205)             
782186f5 (David A. Velasco 2013-06-10 14:21:24 +0200 206)             // new design: 'download' and 'open with' won't be available anymore in context menu
782186f5 (David A. Velasco 2013-06-10 14:21:24 +0200 207)             toHide.add(R.id.action_download_file);
782186f5 (David A. Velasco 2013-06-10 14:21:24 +0200 208)             toHide.add(R.id.action_open_file_with);
782186f5 (David A. Velasco 2013-06-10 14:21:24 +0200 209)             
658267ad (David A. Velasco 2012-11-22 13:06:54 +0100 210)             if (targetFile.isDown()) {
f2474ae2 (David A. Velasco 2013-02-18 11:23:18 +0100 211)                 toHide.add(R.id.action_cancel_download);
f2474ae2 (David A. Velasco 2013-02-18 11:23:18 +0100 212)                 toHide.add(R.id.action_cancel_upload);
6a6d8331 (David A. Velasco 2013-04-24 11:04:23 +0200 213)                 
658267ad (David A. Velasco 2012-11-22 13:06:54 +0100 214)             } else {
6a6d8331 (David A. Velasco 2013-04-24 11:04:23 +0200 215)                 toHide.add(R.id.action_sync_file);
658267ad (David A. Velasco 2012-11-22 13:06:54 +0100 216)             }
658267ad (David A. Velasco 2012-11-22 13:06:54 +0100 217)             if ( mContainerActivity.getFileDownloaderBinder().isDownloading(AccountUtils.getCurrentOwnCloudAccount(getActivity()), targetFile)) {
f2474ae2 (David A. Velasco 2013-02-18 11:23:18 +0100 218)                 toHide.add(R.id.action_cancel_upload);
f2474ae2 (David A. Velasco 2013-02-18 11:23:18 +0100 219)                 toDisable.add(R.id.action_rename_file);
f2474ae2 (David A. Velasco 2013-02-18 11:23:18 +0100 220)                 toDisable.add(R.id.action_remove_file);
658267ad (David A. Velasco 2012-11-22 13:06:54 +0100 221)                     
658267ad (David A. Velasco 2012-11-22 13:06:54 +0100 222)             } else if ( mContainerActivity.getFileUploaderBinder().isUploading(AccountUtils.getCurrentOwnCloudAccount(getActivity()), targetFile)) {
f2474ae2 (David A. Velasco 2013-02-18 11:23:18 +0100 223)                 toHide.add(R.id.action_cancel_download);
f2474ae2 (David A. Velasco 2013-02-18 11:23:18 +0100 224)                 toDisable.add(R.id.action_rename_file);
f2474ae2 (David A. Velasco 2013-02-18 11:23:18 +0100 225)                 toDisable.add(R.id.action_remove_file);
658267ad (David A. Velasco 2012-11-22 13:06:54 +0100 226)                     
658267ad (David A. Velasco 2012-11-22 13:06:54 +0100 227)             } else {
f2474ae2 (David A. Velasco 2013-02-18 11:23:18 +0100 228)                 toHide.add(R.id.action_cancel_download);
f2474ae2 (David A. Velasco 2013-02-18 11:23:18 +0100 229)                 toHide.add(R.id.action_cancel_upload);
658267ad (David A. Velasco 2012-11-22 13:06:54 +0100 230)             }
500cbf9c (David A. Velasco 2012-11-06 15:13:27 +0100 231)         }
658267ad (David A. Velasco 2012-11-22 13:06:54 +0100 232) 
658267ad (David A. Velasco 2012-11-22 13:06:54 +0100 233)         for (int i : toHide) {
658267ad (David A. Velasco 2012-11-22 13:06:54 +0100 234)             item = menu.findItem(i);
500cbf9c (David A. Velasco 2012-11-06 15:13:27 +0100 235)             if (item != null) {
500cbf9c (David A. Velasco 2012-11-06 15:13:27 +0100 236)                 item.setVisible(false);
500cbf9c (David A. Velasco 2012-11-06 15:13:27 +0100 237)                 item.setEnabled(false);
500cbf9c (David A. Velasco 2012-11-06 15:13:27 +0100 238)             }
500cbf9c (David A. Velasco 2012-11-06 15:13:27 +0100 239)         }
658267ad (David A. Velasco 2012-11-22 13:06:54 +0100 240)         
658267ad (David A. Velasco 2012-11-22 13:06:54 +0100 241)         for (int i : toDisable) {
658267ad (David A. Velasco 2012-11-22 13:06:54 +0100 242)             item = menu.findItem(i);
658267ad (David A. Velasco 2012-11-22 13:06:54 +0100 243)             if (item != null) {
658267ad (David A. Velasco 2012-11-22 13:06:54 +0100 244)                 item.setEnabled(false);
658267ad (David A. Velasco 2012-11-22 13:06:54 +0100 245)             }
658267ad (David A. Velasco 2012-11-22 13:06:54 +0100 246)         }
454f1513 (David A. Velasco 2012-10-31 17:41:57 +0100 247)     }
454f1513 (David A. Velasco 2012-10-31 17:41:57 +0100 248)     
454f1513 (David A. Velasco 2012-10-31 17:41:57 +0100 249)     
454f1513 (David A. Velasco 2012-10-31 17:41:57 +0100 250)     /**
454f1513 (David A. Velasco 2012-10-31 17:41:57 +0100 251)      * {@inhericDoc}
454f1513 (David A. Velasco 2012-10-31 17:41:57 +0100 252)      */
454f1513 (David A. Velasco 2012-10-31 17:41:57 +0100 253)     @Override
454f1513 (David A. Velasco 2012-10-31 17:41:57 +0100 254)     public boolean onContextItemSelected (MenuItem item) {
454f1513 (David A. Velasco 2012-10-31 17:41:57 +0100 255)         AdapterContextMenuInfo info = (AdapterContextMenuInfo) item.getMenuInfo();        
454f1513 (David A. Velasco 2012-10-31 17:41:57 +0100 256)         mTargetFile = (OCFile) mAdapter.getItem(info.position);
454f1513 (David A. Velasco 2012-10-31 17:41:57 +0100 257)         switch (item.getItemId()) {
f2474ae2 (David A. Velasco 2013-02-18 11:23:18 +0100 258)             case R.id.action_rename_file: {
5bcfed8e (David A. Velasco 2013-04-15 14:21:10 +0200 259)                 String fileName = mTargetFile.getFileName();
5bcfed8e (David A. Velasco 2013-04-15 14:21:10 +0200 260)                 int extensionStart = mTargetFile.isDirectory() ? -1 : fileName.lastIndexOf(".");
5bcfed8e (David A. Velasco 2013-04-15 14:21:10 +0200 261)                 int selectionEnd = (extensionStart >= 0) ? extensionStart : fileName.length();
5bcfed8e (David A. Velasco 2013-04-15 14:21:10 +0200 262)                 EditNameDialog dialog = EditNameDialog.newInstance(getString(R.string.rename_dialog_title), fileName, 0, selectionEnd, this);
85e9a40d (David A. Velasco 2012-11-08 17:21:45 +0100 263)                 dialog.show(getFragmentManager(), EditNameDialog.TAG);
454f1513 (David A. Velasco 2012-10-31 17:41:57 +0100 264)                 return true;
ac6b3d25 (David A. Velasco 2012-11-07 14:17:58 +0100 265)             }
f2474ae2 (David A. Velasco 2013-02-18 11:23:18 +0100 266)             case R.id.action_remove_file: {
b27ebf03 (David A. Velasco 2012-11-05 15:44:09 +0100 267)                 int messageStringId = R.string.confirmation_remove_alert;
b27ebf03 (David A. Velasco 2012-11-05 15:44:09 +0100 268)                 int posBtnStringId = R.string.confirmation_remove_remote;
b27ebf03 (David A. Velasco 2012-11-05 15:44:09 +0100 269)                 int neuBtnStringId = -1;
b27ebf03 (David A. Velasco 2012-11-05 15:44:09 +0100 270)                 if (mTargetFile.isDirectory()) {
b27ebf03 (David A. Velasco 2012-11-05 15:44:09 +0100 271)                     messageStringId = R.string.confirmation_remove_folder_alert;
b27ebf03 (David A. Velasco 2012-11-05 15:44:09 +0100 272)                     posBtnStringId = R.string.confirmation_remove_remote_and_local;
b27ebf03 (David A. Velasco 2012-11-05 15:44:09 +0100 273)                     neuBtnStringId = R.string.confirmation_remove_folder_local;
b27ebf03 (David A. Velasco 2012-11-05 15:44:09 +0100 274)                 } else if (mTargetFile.isDown()) {
b27ebf03 (David A. Velasco 2012-11-05 15:44:09 +0100 275)                     posBtnStringId = R.string.confirmation_remove_remote_and_local;
b27ebf03 (David A. Velasco 2012-11-05 15:44:09 +0100 276)                     neuBtnStringId = R.string.confirmation_remove_local;
b27ebf03 (David A. Velasco 2012-11-05 15:44:09 +0100 277)                 }
454f1513 (David A. Velasco 2012-10-31 17:41:57 +0100 278)                 ConfirmationDialogFragment confDialog = ConfirmationDialogFragment.newInstance(
b27ebf03 (David A. Velasco 2012-11-05 15:44:09 +0100 279)                         messageStringId,
454f1513 (David A. Velasco 2012-10-31 17:41:57 +0100 280)                         new String[]{mTargetFile.getFileName()},
b27ebf03 (David A. Velasco 2012-11-05 15:44:09 +0100 281)                         posBtnStringId,
b27ebf03 (David A. Velasco 2012-11-05 15:44:09 +0100 282)                         neuBtnStringId,
454f1513 (David A. Velasco 2012-10-31 17:41:57 +0100 283)                         R.string.common_cancel);
454f1513 (David A. Velasco 2012-10-31 17:41:57 +0100 284)                 confDialog.setOnConfirmationListener(this);
f2474ae2 (David A. Velasco 2013-02-18 11:23:18 +0100 285)                 confDialog.show(getFragmentManager(), FileDetailFragment.FTAG_CONFIRMATION);
454f1513 (David A. Velasco 2012-10-31 17:41:57 +0100 286)                 return true;
ac6b3d25 (David A. Velasco 2012-11-07 14:17:58 +0100 287)             }
6a6d8331 (David A. Velasco 2013-04-24 11:04:23 +0200 288)             case R.id.action_sync_file: {
ba52dfdf (David A. Velasco 2012-11-23 14:30:36 +0100 289)                 Account account = AccountUtils.getCurrentOwnCloudAccount(getSherlockActivity());
ba52dfdf (David A. Velasco 2012-11-23 14:30:36 +0100 290)                 RemoteOperation operation = new SynchronizeFileOperation(mTargetFile, null, mContainerActivity.getStorageManager(), account, true, false, getSherlockActivity());
73f8797b (David A. Velasco 2013-01-17 13:01:51 +0100 291)                 operation.execute(account, getSherlockActivity(), mContainerActivity, mHandler, getSherlockActivity());
ba52dfdf (David A. Velasco 2012-11-23 14:30:36 +0100 292)                 getSherlockActivity().showDialog(FileDisplayActivity.DIALOG_SHORT_WAIT);
ac6b3d25 (David A. Velasco 2012-11-07 14:17:58 +0100 293)                 return true;
ac6b3d25 (David A. Velasco 2012-11-07 14:17:58 +0100 294)             }
f2474ae2 (David A. Velasco 2013-02-18 11:23:18 +0100 295)             case R.id.action_cancel_download: {
ac6b3d25 (David A. Velasco 2012-11-07 14:17:58 +0100 296)                 FileDownloaderBinder downloaderBinder = mContainerActivity.getFileDownloaderBinder();
ac6b3d25 (David A. Velasco 2012-11-07 14:17:58 +0100 297)                 Account account = AccountUtils.getCurrentOwnCloudAccount(getActivity());
ac6b3d25 (David A. Velasco 2012-11-07 14:17:58 +0100 298)                 if (downloaderBinder != null && downloaderBinder.isDownloading(account, mTargetFile)) {
ac6b3d25 (David A. Velasco 2012-11-07 14:17:58 +0100 299)                     downloaderBinder.cancel(account, mTargetFile);
ac6b3d25 (David A. Velasco 2012-11-07 14:17:58 +0100 300)                     listDirectory();
85e9a40d (David A. Velasco 2012-11-08 17:21:45 +0100 301)                     mContainerActivity.onTransferStateChanged(mTargetFile, false, false);
ac6b3d25 (David A. Velasco 2012-11-07 14:17:58 +0100 302)                 }
ac6b3d25 (David A. Velasco 2012-11-07 14:17:58 +0100 303)                 return true;
ac6b3d25 (David A. Velasco 2012-11-07 14:17:58 +0100 304)             }
f2474ae2 (David A. Velasco 2013-02-18 11:23:18 +0100 305)             case R.id.action_cancel_upload: {
ac6b3d25 (David A. Velasco 2012-11-07 14:17:58 +0100 306)                 FileUploaderBinder uploaderBinder = mContainerActivity.getFileUploaderBinder();
ac6b3d25 (David A. Velasco 2012-11-07 14:17:58 +0100 307)                 Account account = AccountUtils.getCurrentOwnCloudAccount(getActivity());
ac6b3d25 (David A. Velasco 2012-11-07 14:17:58 +0100 308)                 if (uploaderBinder != null && uploaderBinder.isUploading(account, mTargetFile)) {
ac6b3d25 (David A. Velasco 2012-11-07 14:17:58 +0100 309)                     uploaderBinder.cancel(account, mTargetFile);
ac6b3d25 (David A. Velasco 2012-11-07 14:17:58 +0100 310)                     listDirectory();
85e9a40d (David A. Velasco 2012-11-08 17:21:45 +0100 311)                     mContainerActivity.onTransferStateChanged(mTargetFile, false, false);
ac6b3d25 (David A. Velasco 2012-11-07 14:17:58 +0100 312)                 }
ac6b3d25 (David A. Velasco 2012-11-07 14:17:58 +0100 313)                 return true;
ac6b3d25 (David A. Velasco 2012-11-07 14:17:58 +0100 314)             }
f2474ae2 (David A. Velasco 2013-02-18 11:23:18 +0100 315)             case R.id.action_see_details: {
fd396289 (David A. Velasco 2013-05-30 17:53:21 +0200 316)                 ((FileFragment.ContainerActivity)getActivity()).showDetails(mTargetFile);
f2474ae2 (David A. Velasco 2013-02-18 11:23:18 +0100 317)                 return true;
f2474ae2 (David A. Velasco 2013-02-18 11:23:18 +0100 318)             }
454f1513 (David A. Velasco 2012-10-31 17:41:57 +0100 319)             default:
454f1513 (David A. Velasco 2012-10-31 17:41:57 +0100 320)                 return super.onContextItemSelected(item); 
454f1513 (David A. Velasco 2012-10-31 17:41:57 +0100 321)         }
454f1513 (David A. Velasco 2012-10-31 17:41:57 +0100 322)     }
454f1513 (David A. Velasco 2012-10-31 17:41:57 +0100 323)     
1ecd2b61 (David A. Velasco 2012-08-14 12:37:08 +0200 324) 
1ecd2b61 (David A. Velasco 2012-08-14 12:37:08 +0200 325)     /**
1ecd2b61 (David A. Velasco 2012-08-14 12:37:08 +0200 326)      * Use this to query the {@link OCFile} that is currently
1ecd2b61 (David A. Velasco 2012-08-14 12:37:08 +0200 327)      * being displayed by this fragment
1ecd2b61 (David A. Velasco 2012-08-14 12:37:08 +0200 328)      * @return The currently viewed OCFile
1ecd2b61 (David A. Velasco 2012-08-14 12:37:08 +0200 329)      */
1ecd2b61 (David A. Velasco 2012-08-14 12:37:08 +0200 330)     public OCFile getCurrentFile(){
1ecd2b61 (David A. Velasco 2012-08-14 12:37:08 +0200 331)         return mFile;
1ecd2b61 (David A. Velasco 2012-08-14 12:37:08 +0200 332)     }
1ecd2b61 (David A. Velasco 2012-08-14 12:37:08 +0200 333)     
1ecd2b61 (David A. Velasco 2012-08-14 12:37:08 +0200 334)     /**
1ecd2b61 (David A. Velasco 2012-08-14 12:37:08 +0200 335)      * Calls {@link OCFileListFragment#listDirectory(OCFile)} with a null parameter
1ecd2b61 (David A. Velasco 2012-08-14 12:37:08 +0200 336)      */
1ecd2b61 (David A. Velasco 2012-08-14 12:37:08 +0200 337)     public void listDirectory(){
1ecd2b61 (David A. Velasco 2012-08-14 12:37:08 +0200 338)         listDirectory(null);
1ecd2b61 (David A. Velasco 2012-08-14 12:37:08 +0200 339)     }
1ecd2b61 (David A. Velasco 2012-08-14 12:37:08 +0200 340)     
1ecd2b61 (David A. Velasco 2012-08-14 12:37:08 +0200 341)     /**
1ecd2b61 (David A. Velasco 2012-08-14 12:37:08 +0200 342)      * Lists the given directory on the view. When the input parameter is null,
bdc0332c (David A. Velasco 2013-04-17 12:26:13 +0200 343)      * it will either refresh the last known directory. list the root
1ecd2b61 (David A. Velasco 2012-08-14 12:37:08 +0200 344)      * if there never was a directory.
1ecd2b61 (David A. Velasco 2012-08-14 12:37:08 +0200 345)      * 
1ecd2b61 (David A. Velasco 2012-08-14 12:37:08 +0200 346)      * @param directory File to be listed
1ecd2b61 (David A. Velasco 2012-08-14 12:37:08 +0200 347)      */
1ecd2b61 (David A. Velasco 2012-08-14 12:37:08 +0200 348)     public void listDirectory(OCFile directory) {
1ecd2b61 (David A. Velasco 2012-08-14 12:37:08 +0200 349)         DataStorageManager storageManager = mContainerActivity.getStorageManager();
7fead3cb (David A. Velasco 2012-10-11 11:33:13 +0200 350)         if (storageManager != null) {
1ecd2b61 (David A. Velasco 2012-08-14 12:37:08 +0200 351) 
7fead3cb (David A. Velasco 2012-10-11 11:33:13 +0200 352)             // Check input parameters for null
7fead3cb (David A. Velasco 2012-10-11 11:33:13 +0200 353)             if(directory == null){
7fead3cb (David A. Velasco 2012-10-11 11:33:13 +0200 354)                 if(mFile != null){
7fead3cb (David A. Velasco 2012-10-11 11:33:13 +0200 355)                     directory = mFile;
7fead3cb (David A. Velasco 2012-10-11 11:33:13 +0200 356)                 } else {
7fead3cb (David A. Velasco 2012-10-11 11:33:13 +0200 357)                     directory = storageManager.getFileByPath("/");
7fead3cb (David A. Velasco 2012-10-11 11:33:13 +0200 358)                     if (directory == null) return; // no files, wait for sync
7fead3cb (David A. Velasco 2012-10-11 11:33:13 +0200 359)                 }
1ecd2b61 (David A. Velasco 2012-08-14 12:37:08 +0200 360)             }
1ecd2b61 (David A. Velasco 2012-08-14 12:37:08 +0200 361)         
1ecd2b61 (David A. Velasco 2012-08-14 12:37:08 +0200 362)         
7fead3cb (David A. Velasco 2012-10-11 11:33:13 +0200 363)             // If that's not a directory -> List its parent
7fead3cb (David A. Velasco 2012-10-11 11:33:13 +0200 364)             if(!directory.isDirectory()){
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100 365)                 Log_OC.w(TAG, "You see, that is not a directory -> " + directory.toString());
7fead3cb (David A. Velasco 2012-10-11 11:33:13 +0200 366)                 directory = storageManager.getFileById(directory.getParentId());
7fead3cb (David A. Velasco 2012-10-11 11:33:13 +0200 367)             }
1ecd2b61 (David A. Velasco 2012-08-14 12:37:08 +0200 368) 
5c0faf23 (David A. Velasco 2012-10-18 19:11:59 +0200 369)             mAdapter.swapDirectory(directory, storageManager);
0bb59e0f (David A. Velasco 2012-10-17 13:44:36 +0200 370)             if (mFile == null || !mFile.equals(directory)) {
0bb59e0f (David A. Velasco 2012-10-17 13:44:36 +0200 371)                 mList.setSelectionFromTop(0, 0);
0bb59e0f (David A. Velasco 2012-10-17 13:44:36 +0200 372)             }
0bb59e0f (David A. Velasco 2012-10-17 13:44:36 +0200 373)             mFile = directory;
eee74aa5 (masensio         2013-07-04 18:47:38 +0200 374) 
7fead3cb (David A. Velasco 2012-10-11 11:33:13 +0200 375)         }
1ecd2b61 (David A. Velasco 2012-08-14 12:37:08 +0200 376)     }
1ecd2b61 (David A. Velasco 2012-08-14 12:37:08 +0200 377)     
1ecd2b61 (David A. Velasco 2012-08-14 12:37:08 +0200 378)     
1ecd2b61 (David A. Velasco 2012-08-14 12:37:08 +0200 379)     
1ecd2b61 (David A. Velasco 2012-08-14 12:37:08 +0200 380)     /**
1ecd2b61 (David A. Velasco 2012-08-14 12:37:08 +0200 381)      * Interface to implement by any Activity that includes some instance of FileListFragment
1ecd2b61 (David A. Velasco 2012-08-14 12:37:08 +0200 382)      * 
1ecd2b61 (David A. Velasco 2012-08-14 12:37:08 +0200 383)      * @author David A. Velasco
1ecd2b61 (David A. Velasco 2012-08-14 12:37:08 +0200 384)      */
edb380e0 (David A. Velasco 2013-05-27 15:01:54 +0200 385)     public interface ContainerActivity extends TransferServiceGetter, OnRemoteOperationListener, FileHandler {
1ecd2b61 (David A. Velasco 2012-08-14 12:37:08 +0200 386) 
1ecd2b61 (David A. Velasco 2012-08-14 12:37:08 +0200 387)         /**
fd396289 (David A. Velasco 2013-05-30 17:53:21 +0200 388)          * Callback method invoked when a the user browsed into a different folder through the list of files
1ecd2b61 (David A. Velasco 2012-08-14 12:37:08 +0200 389)          *  
1ecd2b61 (David A. Velasco 2012-08-14 12:37:08 +0200 390)          * @param file
1ecd2b61 (David A. Velasco 2012-08-14 12:37:08 +0200 391)          */
fd396289 (David A. Velasco 2013-05-30 17:53:21 +0200 392)         public void onBrowsedDownTo(OCFile folder);
1ecd2b61 (David A. Velasco 2012-08-14 12:37:08 +0200 393)         
fd396289 (David A. Velasco 2013-05-30 17:53:21 +0200 394)         public void startDownloadForPreview(OCFile file);
fd396289 (David A. Velasco 2013-05-30 17:53:21 +0200 395) 
fd396289 (David A. Velasco 2013-05-30 17:53:21 +0200 396)         public void startMediaPreview(OCFile file, int i, boolean b);
fd396289 (David A. Velasco 2013-05-30 17:53:21 +0200 397) 
fd396289 (David A. Velasco 2013-05-30 17:53:21 +0200 398)         public void startImagePreview(OCFile file);
1ecd2b61 (David A. Velasco 2012-08-14 12:37:08 +0200 399) 
1ecd2b61 (David A. Velasco 2012-08-14 12:37:08 +0200 400)         /**
1ecd2b61 (David A. Velasco 2012-08-14 12:37:08 +0200 401)          * Getter for the current DataStorageManager in the container activity
1ecd2b61 (David A. Velasco 2012-08-14 12:37:08 +0200 402)          */
1ecd2b61 (David A. Velasco 2012-08-14 12:37:08 +0200 403)         public DataStorageManager getStorageManager();
1ecd2b61 (David A. Velasco 2012-08-14 12:37:08 +0200 404)         
a2d4e98a (David A. Velasco 2012-10-03 16:48:06 +0200 405)         
a2d4e98a (David A. Velasco 2012-10-03 16:48:06 +0200 406)         /**
85e9a40d (David A. Velasco 2012-11-08 17:21:45 +0100 407)          * Callback method invoked when a the 'transfer state' of a file changes.
85e9a40d (David A. Velasco 2012-11-08 17:21:45 +0100 408)          * 
85e9a40d (David A. Velasco 2012-11-08 17:21:45 +0100 409)          * This happens when a download or upload is started or ended for a file.
85e9a40d (David A. Velasco 2012-11-08 17:21:45 +0100 410)          * 
85e9a40d (David A. Velasco 2012-11-08 17:21:45 +0100 411)          * This method is necessary by now to update the user interface of the double-pane layout in tablets
85e9a40d (David A. Velasco 2012-11-08 17:21:45 +0100 412)          * because methods {@link FileDownloaderBinder#isDownloading(Account, OCFile)} and {@link FileUploaderBinder#isUploading(Account, OCFile)}
85e9a40d (David A. Velasco 2012-11-08 17:21:45 +0100 413)          * won't provide the needed response before the method where this is called finishes. 
85e9a40d (David A. Velasco 2012-11-08 17:21:45 +0100 414)          * 
85e9a40d (David A. Velasco 2012-11-08 17:21:45 +0100 415)          * TODO Remove this when the transfer state of a file is kept in the database (other thing TODO)
85e9a40d (David A. Velasco 2012-11-08 17:21:45 +0100 416)          * 
85e9a40d (David A. Velasco 2012-11-08 17:21:45 +0100 417)          * @param file          OCFile which state changed.
85e9a40d (David A. Velasco 2012-11-08 17:21:45 +0100 418)          * @param downloading   Flag signaling if the file is now downloading.
85e9a40d (David A. Velasco 2012-11-08 17:21:45 +0100 419)          * @param uploading     Flag signaling if the file is now uploading.
85e9a40d (David A. Velasco 2012-11-08 17:21:45 +0100 420)          */
85e9a40d (David A. Velasco 2012-11-08 17:21:45 +0100 421)         public void onTransferStateChanged(OCFile file, boolean downloading, boolean uploading);
85e9a40d (David A. Velasco 2012-11-08 17:21:45 +0100 422)         
1ecd2b61 (David A. Velasco 2012-08-14 12:37:08 +0200 423)     }
85e9a40d (David A. Velasco 2012-11-08 17:21:45 +0100 424)     
85e9a40d (David A. Velasco 2012-11-08 17:21:45 +0100 425)     
454f1513 (David A. Velasco 2012-10-31 17:41:57 +0100 426)     @Override
454f1513 (David A. Velasco 2012-10-31 17:41:57 +0100 427)     public void onDismiss(EditNameDialog dialog) {
454f1513 (David A. Velasco 2012-10-31 17:41:57 +0100 428)         if (dialog.getResult()) {
454f1513 (David A. Velasco 2012-10-31 17:41:57 +0100 429)             String newFilename = dialog.getNewFilename();
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100 430)             Log_OC.d(TAG, "name edit dialog dismissed with new name " + newFilename);
454f1513 (David A. Velasco 2012-10-31 17:41:57 +0100 431)             RemoteOperation operation = new RenameFileOperation(mTargetFile, 
d0b7df16 (David A. Velasco 2012-11-21 14:32:40 +0100 432)                                                                 AccountUtils.getCurrentOwnCloudAccount(getActivity()), 
454f1513 (David A. Velasco 2012-10-31 17:41:57 +0100 433)                                                                 newFilename, 
454f1513 (David A. Velasco 2012-10-31 17:41:57 +0100 434)                                                                 mContainerActivity.getStorageManager());
73f8797b (David A. Velasco 2013-01-17 13:01:51 +0100 435)             operation.execute(AccountUtils.getCurrentOwnCloudAccount(getSherlockActivity()), getSherlockActivity(), mContainerActivity, mHandler, getSherlockActivity());
454f1513 (David A. Velasco 2012-10-31 17:41:57 +0100 436)             getActivity().showDialog(FileDisplayActivity.DIALOG_SHORT_WAIT);
454f1513 (David A. Velasco 2012-10-31 17:41:57 +0100 437)         }
454f1513 (David A. Velasco 2012-10-31 17:41:57 +0100 438)     }
454f1513 (David A. Velasco 2012-10-31 17:41:57 +0100 439) 
454f1513 (David A. Velasco 2012-10-31 17:41:57 +0100 440)     
454f1513 (David A. Velasco 2012-10-31 17:41:57 +0100 441)     @Override
454f1513 (David A. Velasco 2012-10-31 17:41:57 +0100 442)     public void onConfirmation(String callerTag) {
454f1513 (David A. Velasco 2012-10-31 17:41:57 +0100 443)         if (callerTag.equals(FileDetailFragment.FTAG_CONFIRMATION)) {
454f1513 (David A. Velasco 2012-10-31 17:41:57 +0100 444)             if (mContainerActivity.getStorageManager().getFileById(mTargetFile.getFileId()) != null) {
454f1513 (David A. Velasco 2012-10-31 17:41:57 +0100 445)                 RemoteOperation operation = new RemoveFileOperation( mTargetFile, 
454f1513 (David A. Velasco 2012-10-31 17:41:57 +0100 446)                                                                     true, 
454f1513 (David A. Velasco 2012-10-31 17:41:57 +0100 447)                                                                     mContainerActivity.getStorageManager());
73f8797b (David A. Velasco 2013-01-17 13:01:51 +0100 448)                 operation.execute(AccountUtils.getCurrentOwnCloudAccount(getSherlockActivity()), getSherlockActivity(), mContainerActivity, mHandler, getSherlockActivity());
454f1513 (David A. Velasco 2012-10-31 17:41:57 +0100 449)                 
454f1513 (David A. Velasco 2012-10-31 17:41:57 +0100 450)                 getActivity().showDialog(FileDisplayActivity.DIALOG_SHORT_WAIT);
454f1513 (David A. Velasco 2012-10-31 17:41:57 +0100 451)             }
454f1513 (David A. Velasco 2012-10-31 17:41:57 +0100 452)         }
454f1513 (David A. Velasco 2012-10-31 17:41:57 +0100 453)     }
454f1513 (David A. Velasco 2012-10-31 17:41:57 +0100 454)     
454f1513 (David A. Velasco 2012-10-31 17:41:57 +0100 455)     @Override
454f1513 (David A. Velasco 2012-10-31 17:41:57 +0100 456)     public void onNeutral(String callerTag) {
454f1513 (David A. Velasco 2012-10-31 17:41:57 +0100 457)         File f = null;
b27ebf03 (David A. Velasco 2012-11-05 15:44:09 +0100 458)         if (mTargetFile.isDirectory()) {
b27ebf03 (David A. Velasco 2012-11-05 15:44:09 +0100 459)             // TODO run in a secondary thread?
b27ebf03 (David A. Velasco 2012-11-05 15:44:09 +0100 460)             mContainerActivity.getStorageManager().removeDirectory(mTargetFile, false, true);
b27ebf03 (David A. Velasco 2012-11-05 15:44:09 +0100 461)             
b27ebf03 (David A. Velasco 2012-11-05 15:44:09 +0100 462)         } else if (mTargetFile.isDown() && (f = new File(mTargetFile.getStoragePath())).exists()) {
454f1513 (David A. Velasco 2012-10-31 17:41:57 +0100 463)             f.delete();
454f1513 (David A. Velasco 2012-10-31 17:41:57 +0100 464)             mTargetFile.setStoragePath(null);
85e9a40d (David A. Velasco 2012-11-08 17:21:45 +0100 465)             mContainerActivity.getStorageManager().saveFile(mTargetFile);
454f1513 (David A. Velasco 2012-10-31 17:41:57 +0100 466)         }
454f1513 (David A. Velasco 2012-10-31 17:41:57 +0100 467)         listDirectory();
85e9a40d (David A. Velasco 2012-11-08 17:21:45 +0100 468)         mContainerActivity.onTransferStateChanged(mTargetFile, false, false);
454f1513 (David A. Velasco 2012-10-31 17:41:57 +0100 469)     }
454f1513 (David A. Velasco 2012-10-31 17:41:57 +0100 470)     
454f1513 (David A. Velasco 2012-10-31 17:41:57 +0100 471)     @Override
454f1513 (David A. Velasco 2012-10-31 17:41:57 +0100 472)     public void onCancel(String callerTag) {
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100 473)         Log_OC.d(TAG, "REMOVAL CANCELED");
454f1513 (David A. Velasco 2012-10-31 17:41:57 +0100 474)     }
85e9a40d (David A. Velasco 2012-11-08 17:21:45 +0100 475) 
85e9a40d (David A. Velasco 2012-11-08 17:21:45 +0100 476) 
1ecd2b61 (David A. Velasco 2012-08-14 12:37:08 +0200 477) }
