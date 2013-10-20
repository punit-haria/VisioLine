3d272eb7 src/com/owncloud/android/ui/activity/PreviewImageActivity.java (David A. Velasco 2013-02-25 12:24:14 +0100   1) /* ownCloud Android client application
3d272eb7 src/com/owncloud/android/ui/activity/PreviewImageActivity.java (David A. Velasco 2013-02-25 12:24:14 +0100   2)  *   Copyright (C) 2012-2013  ownCloud Inc.
3d272eb7 src/com/owncloud/android/ui/activity/PreviewImageActivity.java (David A. Velasco 2013-02-25 12:24:14 +0100   3)  *
3d272eb7 src/com/owncloud/android/ui/activity/PreviewImageActivity.java (David A. Velasco 2013-02-25 12:24:14 +0100   4)  *   This program is free software: you can redistribute it and/or modify
bdc0332c src/com/owncloud/android/ui/preview/PreviewImageActivity.java  (David A. Velasco 2013-04-17 12:26:13 +0200   5)  *   it under the terms of the GNU General Public License version 2,
bdc0332c src/com/owncloud/android/ui/preview/PreviewImageActivity.java  (David A. Velasco 2013-04-17 12:26:13 +0200   6)  *   as published by the Free Software Foundation.
3d272eb7 src/com/owncloud/android/ui/activity/PreviewImageActivity.java (David A. Velasco 2013-02-25 12:24:14 +0100   7)  *
3d272eb7 src/com/owncloud/android/ui/activity/PreviewImageActivity.java (David A. Velasco 2013-02-25 12:24:14 +0100   8)  *   This program is distributed in the hope that it will be useful,
3d272eb7 src/com/owncloud/android/ui/activity/PreviewImageActivity.java (David A. Velasco 2013-02-25 12:24:14 +0100   9)  *   but WITHOUT ANY WARRANTY; without even the implied warranty of
3d272eb7 src/com/owncloud/android/ui/activity/PreviewImageActivity.java (David A. Velasco 2013-02-25 12:24:14 +0100  10)  *   MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
3d272eb7 src/com/owncloud/android/ui/activity/PreviewImageActivity.java (David A. Velasco 2013-02-25 12:24:14 +0100  11)  *   GNU General Public License for more details.
3d272eb7 src/com/owncloud/android/ui/activity/PreviewImageActivity.java (David A. Velasco 2013-02-25 12:24:14 +0100  12)  *
3d272eb7 src/com/owncloud/android/ui/activity/PreviewImageActivity.java (David A. Velasco 2013-02-25 12:24:14 +0100  13)  *   You should have received a copy of the GNU General Public License
3d272eb7 src/com/owncloud/android/ui/activity/PreviewImageActivity.java (David A. Velasco 2013-02-25 12:24:14 +0100  14)  *   along with this program.  If not, see <http://www.gnu.org/licenses/>.
3d272eb7 src/com/owncloud/android/ui/activity/PreviewImageActivity.java (David A. Velasco 2013-02-25 12:24:14 +0100  15)  *
3d272eb7 src/com/owncloud/android/ui/activity/PreviewImageActivity.java (David A. Velasco 2013-02-25 12:24:14 +0100  16)  */
fc2af5b0 src/com/owncloud/android/ui/preview/PreviewImageActivity.java  (David A. Velasco 2013-02-26 12:33:28 +0100  17) package com.owncloud.android.ui.preview;
d7e05192 src/com/owncloud/android/ui/activity/PreviewImageActivity.java (David A. Velasco 2013-02-25 14:33:16 +0100  18) 
3d272eb7 src/com/owncloud/android/ui/activity/PreviewImageActivity.java (David A. Velasco 2013-02-25 12:24:14 +0100  19) import android.app.Dialog;
3d272eb7 src/com/owncloud/android/ui/activity/PreviewImageActivity.java (David A. Velasco 2013-02-25 12:24:14 +0100  20) import android.app.ProgressDialog;
86cec34b src/com/owncloud/android/ui/preview/PreviewImageActivity.java  (David A. Velasco 2013-02-27 14:08:58 +0100  21) import android.content.BroadcastReceiver;
3d272eb7 src/com/owncloud/android/ui/activity/PreviewImageActivity.java (David A. Velasco 2013-02-25 12:24:14 +0100  22) import android.content.ComponentName;
3d272eb7 src/com/owncloud/android/ui/activity/PreviewImageActivity.java (David A. Velasco 2013-02-25 12:24:14 +0100  23) import android.content.Context;
3d272eb7 src/com/owncloud/android/ui/activity/PreviewImageActivity.java (David A. Velasco 2013-02-25 12:24:14 +0100  24) import android.content.Intent;
86cec34b src/com/owncloud/android/ui/preview/PreviewImageActivity.java  (David A. Velasco 2013-02-27 14:08:58 +0100  25) import android.content.IntentFilter;
3d272eb7 src/com/owncloud/android/ui/activity/PreviewImageActivity.java (David A. Velasco 2013-02-25 12:24:14 +0100  26) import android.content.ServiceConnection;
3d272eb7 src/com/owncloud/android/ui/activity/PreviewImageActivity.java (David A. Velasco 2013-02-25 12:24:14 +0100  27) import android.os.Bundle;
3d272eb7 src/com/owncloud/android/ui/activity/PreviewImageActivity.java (David A. Velasco 2013-02-25 12:24:14 +0100  28) import android.os.IBinder;
d7e05192 src/com/owncloud/android/ui/activity/PreviewImageActivity.java (David A. Velasco 2013-02-25 14:33:16 +0100  29) import android.support.v4.view.ViewPager;
638b7767 src/com/owncloud/android/ui/preview/PreviewImageActivity.java  (David A. Velasco 2013-02-28 17:09:00 +0100  30) import android.view.MotionEvent;
638b7767 src/com/owncloud/android/ui/preview/PreviewImageActivity.java  (David A. Velasco 2013-02-28 17:09:00 +0100  31) import android.view.View;
638b7767 src/com/owncloud/android/ui/preview/PreviewImageActivity.java  (David A. Velasco 2013-02-28 17:09:00 +0100  32) import android.view.View.OnTouchListener;
3d272eb7 src/com/owncloud/android/ui/activity/PreviewImageActivity.java (David A. Velasco 2013-02-25 12:24:14 +0100  33) 
3d272eb7 src/com/owncloud/android/ui/activity/PreviewImageActivity.java (David A. Velasco 2013-02-25 12:24:14 +0100  34) import com.actionbarsherlock.app.ActionBar;
3d272eb7 src/com/owncloud/android/ui/activity/PreviewImageActivity.java (David A. Velasco 2013-02-25 12:24:14 +0100  35) import com.actionbarsherlock.view.MenuItem;
638b7767 src/com/owncloud/android/ui/preview/PreviewImageActivity.java  (David A. Velasco 2013-02-28 17:09:00 +0100  36) import com.actionbarsherlock.view.Window;
c4ac05de src/com/owncloud/android/ui/preview/PreviewImageActivity.java  (David A. Velasco 2013-06-18 11:34:08 +0200  37) import com.owncloud.android.authentication.AccountUtils;
d7e05192 src/com/owncloud/android/ui/activity/PreviewImageActivity.java (David A. Velasco 2013-02-25 14:33:16 +0100  38) import com.owncloud.android.datamodel.DataStorageManager;
d7e05192 src/com/owncloud/android/ui/activity/PreviewImageActivity.java (David A. Velasco 2013-02-25 14:33:16 +0100  39) import com.owncloud.android.datamodel.FileDataStorageManager;
3d272eb7 src/com/owncloud/android/ui/activity/PreviewImageActivity.java (David A. Velasco 2013-02-25 12:24:14 +0100  40) import com.owncloud.android.datamodel.OCFile;
3d272eb7 src/com/owncloud/android/ui/activity/PreviewImageActivity.java (David A. Velasco 2013-02-25 12:24:14 +0100  41) import com.owncloud.android.files.services.FileDownloader;
3d272eb7 src/com/owncloud/android/ui/activity/PreviewImageActivity.java (David A. Velasco 2013-02-25 12:24:14 +0100  42) import com.owncloud.android.files.services.FileDownloader.FileDownloaderBinder;
3d272eb7 src/com/owncloud/android/ui/activity/PreviewImageActivity.java (David A. Velasco 2013-02-25 12:24:14 +0100  43) import com.owncloud.android.files.services.FileUploader;
3d272eb7 src/com/owncloud/android/ui/activity/PreviewImageActivity.java (David A. Velasco 2013-02-25 12:24:14 +0100  44) import com.owncloud.android.files.services.FileUploader.FileUploaderBinder;
bc1fcf84 src/com/owncloud/android/ui/preview/PreviewImageActivity.java  (David A. Velasco 2013-05-07 13:49:54 +0200  45) import com.owncloud.android.ui.activity.FileActivity;
fd396289 src/com/owncloud/android/ui/preview/PreviewImageActivity.java  (David A. Velasco 2013-05-30 17:53:21 +0200  46) import com.owncloud.android.ui.activity.FileDisplayActivity;
3d272eb7 src/com/owncloud/android/ui/activity/PreviewImageActivity.java (David A. Velasco 2013-02-25 12:24:14 +0100  47) import com.owncloud.android.ui.fragment.FileFragment;
3d272eb7 src/com/owncloud/android/ui/activity/PreviewImageActivity.java (David A. Velasco 2013-02-25 12:24:14 +0100  48) 
2946d8dd src/com/owncloud/android/ui/preview/PreviewImageActivity.java  (David A. Velasco 2013-04-25 19:39:22 +0200  49) import com.owncloud.android.Log_OC;
3d272eb7 src/com/owncloud/android/ui/activity/PreviewImageActivity.java (David A. Velasco 2013-02-25 12:24:14 +0100  50) import com.owncloud.android.R;
3d272eb7 src/com/owncloud/android/ui/activity/PreviewImageActivity.java (David A. Velasco 2013-02-25 12:24:14 +0100  51) 
3d272eb7 src/com/owncloud/android/ui/activity/PreviewImageActivity.java (David A. Velasco 2013-02-25 12:24:14 +0100  52) /**
f9babe7b src/com/owncloud/android/ui/preview/PreviewImageActivity.java  (David A. Velasco 2013-06-14 12:53:45 +0200  53)  *  Holds a swiping galley where image files contained in an ownCloud directory are shown
3d272eb7 src/com/owncloud/android/ui/activity/PreviewImageActivity.java (David A. Velasco 2013-02-25 12:24:14 +0100  54)  *  
3d272eb7 src/com/owncloud/android/ui/activity/PreviewImageActivity.java (David A. Velasco 2013-02-25 12:24:14 +0100  55)  *  @author David A. Velasco
3d272eb7 src/com/owncloud/android/ui/activity/PreviewImageActivity.java (David A. Velasco 2013-02-25 12:24:14 +0100  56)  */
edb380e0 src/com/owncloud/android/ui/preview/PreviewImageActivity.java  (David A. Velasco 2013-05-27 15:01:54 +0200  57) public class PreviewImageActivity extends FileActivity implements FileFragment.ContainerActivity, ViewPager.OnPageChangeListener, OnTouchListener {
3d272eb7 src/com/owncloud/android/ui/activity/PreviewImageActivity.java (David A. Velasco 2013-02-25 12:24:14 +0100  58)     
3d272eb7 src/com/owncloud/android/ui/activity/PreviewImageActivity.java (David A. Velasco 2013-02-25 12:24:14 +0100  59)     public static final int DIALOG_SHORT_WAIT = 0;
3d272eb7 src/com/owncloud/android/ui/activity/PreviewImageActivity.java (David A. Velasco 2013-02-25 12:24:14 +0100  60) 
3d272eb7 src/com/owncloud/android/ui/activity/PreviewImageActivity.java (David A. Velasco 2013-02-25 12:24:14 +0100  61)     public static final String TAG = PreviewImageActivity.class.getSimpleName();
3d272eb7 src/com/owncloud/android/ui/activity/PreviewImageActivity.java (David A. Velasco 2013-02-25 12:24:14 +0100  62)     
3d272eb7 src/com/owncloud/android/ui/activity/PreviewImageActivity.java (David A. Velasco 2013-02-25 12:24:14 +0100  63)     public static final String KEY_WAITING_TO_PREVIEW = "WAITING_TO_PREVIEW";
a3aca946 src/com/owncloud/android/ui/preview/PreviewImageActivity.java  (David A. Velasco 2013-02-26 13:17:44 +0100  64)     private static final String KEY_WAITING_FOR_BINDER = "WAITING_FOR_BINDER";
3d272eb7 src/com/owncloud/android/ui/activity/PreviewImageActivity.java (David A. Velasco 2013-02-25 12:24:14 +0100  65)     
d7e05192 src/com/owncloud/android/ui/activity/PreviewImageActivity.java (David A. Velasco 2013-02-25 14:33:16 +0100  66)     private DataStorageManager mStorageManager;
d7e05192 src/com/owncloud/android/ui/activity/PreviewImageActivity.java (David A. Velasco 2013-02-25 14:33:16 +0100  67)     
d7e05192 src/com/owncloud/android/ui/activity/PreviewImageActivity.java (David A. Velasco 2013-02-25 14:33:16 +0100  68)     private ViewPager mViewPager; 
d7e05192 src/com/owncloud/android/ui/activity/PreviewImageActivity.java (David A. Velasco 2013-02-25 14:33:16 +0100  69)     private PreviewImagePagerAdapter mPreviewImagePagerAdapter;    
d7e05192 src/com/owncloud/android/ui/activity/PreviewImageActivity.java (David A. Velasco 2013-02-25 14:33:16 +0100  70)     
3d272eb7 src/com/owncloud/android/ui/activity/PreviewImageActivity.java (David A. Velasco 2013-02-25 12:24:14 +0100  71)     private FileDownloaderBinder mDownloaderBinder = null;
3d272eb7 src/com/owncloud/android/ui/activity/PreviewImageActivity.java (David A. Velasco 2013-02-25 12:24:14 +0100  72)     private ServiceConnection mDownloadConnection, mUploadConnection = null;
3d272eb7 src/com/owncloud/android/ui/activity/PreviewImageActivity.java (David A. Velasco 2013-02-25 12:24:14 +0100  73)     private FileUploaderBinder mUploaderBinder = null;
a3aca946 src/com/owncloud/android/ui/preview/PreviewImageActivity.java  (David A. Velasco 2013-02-26 13:17:44 +0100  74) 
a3aca946 src/com/owncloud/android/ui/preview/PreviewImageActivity.java  (David A. Velasco 2013-02-26 13:17:44 +0100  75)     private boolean mRequestWaitingForBinder;
3d272eb7 src/com/owncloud/android/ui/activity/PreviewImageActivity.java (David A. Velasco 2013-02-25 12:24:14 +0100  76)     
86cec34b src/com/owncloud/android/ui/preview/PreviewImageActivity.java  (David A. Velasco 2013-02-27 14:08:58 +0100  77)     private DownloadFinishReceiver mDownloadFinishReceiver;
638b7767 src/com/owncloud/android/ui/preview/PreviewImageActivity.java  (David A. Velasco 2013-02-28 17:09:00 +0100  78) 
638b7767 src/com/owncloud/android/ui/preview/PreviewImageActivity.java  (David A. Velasco 2013-02-28 17:09:00 +0100  79)     private boolean mFullScreen;
86cec34b src/com/owncloud/android/ui/preview/PreviewImageActivity.java  (David A. Velasco 2013-02-27 14:08:58 +0100  80)     
3d272eb7 src/com/owncloud/android/ui/activity/PreviewImageActivity.java (David A. Velasco 2013-02-25 12:24:14 +0100  81) 
3d272eb7 src/com/owncloud/android/ui/activity/PreviewImageActivity.java (David A. Velasco 2013-02-25 12:24:14 +0100  82)     @Override
3d272eb7 src/com/owncloud/android/ui/activity/PreviewImageActivity.java (David A. Velasco 2013-02-25 12:24:14 +0100  83)     protected void onCreate(Bundle savedInstanceState) {
3d272eb7 src/com/owncloud/android/ui/activity/PreviewImageActivity.java (David A. Velasco 2013-02-25 12:24:14 +0100  84)         super.onCreate(savedInstanceState);
3d272eb7 src/com/owncloud/android/ui/activity/PreviewImageActivity.java (David A. Velasco 2013-02-25 12:24:14 +0100  85) 
638b7767 src/com/owncloud/android/ui/preview/PreviewImageActivity.java  (David A. Velasco 2013-02-28 17:09:00 +0100  86)         requestWindowFeature(Window.FEATURE_ACTION_BAR_OVERLAY);
3d272eb7 src/com/owncloud/android/ui/activity/PreviewImageActivity.java (David A. Velasco 2013-02-25 12:24:14 +0100  87)         setContentView(R.layout.preview_image_activity);
f9babe7b src/com/owncloud/android/ui/preview/PreviewImageActivity.java  (David A. Velasco 2013-06-14 12:53:45 +0200  88)         
3d272eb7 src/com/owncloud/android/ui/activity/PreviewImageActivity.java (David A. Velasco 2013-02-25 12:24:14 +0100  89)         ActionBar actionBar = getSupportActionBar();
3d272eb7 src/com/owncloud/android/ui/activity/PreviewImageActivity.java (David A. Velasco 2013-02-25 12:24:14 +0100  90)         actionBar.setDisplayHomeAsUpEnabled(true);
638b7767 src/com/owncloud/android/ui/preview/PreviewImageActivity.java  (David A. Velasco 2013-02-28 17:09:00 +0100  91)         actionBar.hide();
638b7767 src/com/owncloud/android/ui/preview/PreviewImageActivity.java  (David A. Velasco 2013-02-28 17:09:00 +0100  92)         
638b7767 src/com/owncloud/android/ui/preview/PreviewImageActivity.java  (David A. Velasco 2013-02-28 17:09:00 +0100  93)         mFullScreen = true;
def870aa src/com/owncloud/android/ui/preview/PreviewImageActivity.java  (David A. Velasco 2013-02-26 12:49:15 +0100  94)         if (savedInstanceState != null) {
a3aca946 src/com/owncloud/android/ui/preview/PreviewImageActivity.java  (David A. Velasco 2013-02-26 13:17:44 +0100  95)             mRequestWaitingForBinder = savedInstanceState.getBoolean(KEY_WAITING_FOR_BINDER);
a3aca946 src/com/owncloud/android/ui/preview/PreviewImageActivity.java  (David A. Velasco 2013-02-26 13:17:44 +0100  96)         } else {
a3aca946 src/com/owncloud/android/ui/preview/PreviewImageActivity.java  (David A. Velasco 2013-02-26 13:17:44 +0100  97)             mRequestWaitingForBinder = false;
3d272eb7 src/com/owncloud/android/ui/activity/PreviewImageActivity.java (David A. Velasco 2013-02-25 12:24:14 +0100  98)         }
3d272eb7 src/com/owncloud/android/ui/activity/PreviewImageActivity.java (David A. Velasco 2013-02-25 12:24:14 +0100  99)     }
3d272eb7 src/com/owncloud/android/ui/activity/PreviewImageActivity.java (David A. Velasco 2013-02-25 12:24:14 +0100 100) 
f9babe7b src/com/owncloud/android/ui/preview/PreviewImageActivity.java  (David A. Velasco 2013-06-14 12:53:45 +0200 101)     private void initViewPager() {
f9babe7b src/com/owncloud/android/ui/preview/PreviewImageActivity.java  (David A. Velasco 2013-06-14 12:53:45 +0200 102)         OCFile parentFolder = mStorageManager.getFileById(getFile().getParentId());
f9babe7b src/com/owncloud/android/ui/preview/PreviewImageActivity.java  (David A. Velasco 2013-06-14 12:53:45 +0200 103)         if (parentFolder == null) {
f9babe7b src/com/owncloud/android/ui/preview/PreviewImageActivity.java  (David A. Velasco 2013-06-14 12:53:45 +0200 104)             // should not be necessary
f9babe7b src/com/owncloud/android/ui/preview/PreviewImageActivity.java  (David A. Velasco 2013-06-14 12:53:45 +0200 105)             parentFolder = mStorageManager.getFileByPath(OCFile.PATH_SEPARATOR);
f9babe7b src/com/owncloud/android/ui/preview/PreviewImageActivity.java  (David A. Velasco 2013-06-14 12:53:45 +0200 106)         }
f9babe7b src/com/owncloud/android/ui/preview/PreviewImageActivity.java  (David A. Velasco 2013-06-14 12:53:45 +0200 107)         mPreviewImagePagerAdapter = new PreviewImagePagerAdapter(getSupportFragmentManager(), parentFolder, getAccount(), mStorageManager);
d7e05192 src/com/owncloud/android/ui/activity/PreviewImageActivity.java (David A. Velasco 2013-02-25 14:33:16 +0100 108)         mViewPager = (ViewPager) findViewById(R.id.fragmentPager);
f9babe7b src/com/owncloud/android/ui/preview/PreviewImageActivity.java  (David A. Velasco 2013-06-14 12:53:45 +0200 109)         int position = mPreviewImagePagerAdapter.getFilePosition(getFile());
4ba98835 src/com/owncloud/android/ui/preview/PreviewImageActivity.java  (David A. Velasco 2013-02-27 10:49:13 +0100 110)         position = (position >= 0) ? position : 0;
86cec34b src/com/owncloud/android/ui/preview/PreviewImageActivity.java  (David A. Velasco 2013-02-27 14:08:58 +0100 111)         mViewPager.setAdapter(mPreviewImagePagerAdapter); 
d7e05192 src/com/owncloud/android/ui/activity/PreviewImageActivity.java (David A. Velasco 2013-02-25 14:33:16 +0100 112)         mViewPager.setOnPageChangeListener(this);
4ba98835 src/com/owncloud/android/ui/preview/PreviewImageActivity.java  (David A. Velasco 2013-02-27 10:49:13 +0100 113)         mViewPager.setCurrentItem(position);
f9babe7b src/com/owncloud/android/ui/preview/PreviewImageActivity.java  (David A. Velasco 2013-06-14 12:53:45 +0200 114)         if (position == 0 && !getFile().isDown()) {
4ba98835 src/com/owncloud/android/ui/preview/PreviewImageActivity.java  (David A. Velasco 2013-02-27 10:49:13 +0100 115)             // this is necessary because mViewPager.setCurrentItem(0) just after setting the adapter does not result in a call to #onPageSelected(0) 
4ba98835 src/com/owncloud/android/ui/preview/PreviewImageActivity.java  (David A. Velasco 2013-02-27 10:49:13 +0100 116)             mRequestWaitingForBinder = true;
4ba98835 src/com/owncloud/android/ui/preview/PreviewImageActivity.java  (David A. Velasco 2013-02-27 10:49:13 +0100 117)         }
d7e05192 src/com/owncloud/android/ui/activity/PreviewImageActivity.java (David A. Velasco 2013-02-25 14:33:16 +0100 118)     }
d7e05192 src/com/owncloud/android/ui/activity/PreviewImageActivity.java (David A. Velasco 2013-02-25 14:33:16 +0100 119)     
86cec34b src/com/owncloud/android/ui/preview/PreviewImageActivity.java  (David A. Velasco 2013-02-27 14:08:58 +0100 120)     
86cec34b src/com/owncloud/android/ui/preview/PreviewImageActivity.java  (David A. Velasco 2013-02-27 14:08:58 +0100 121)     @Override
86cec34b src/com/owncloud/android/ui/preview/PreviewImageActivity.java  (David A. Velasco 2013-02-27 14:08:58 +0100 122)     public void onStart() {
86cec34b src/com/owncloud/android/ui/preview/PreviewImageActivity.java  (David A. Velasco 2013-02-27 14:08:58 +0100 123)         super.onStart();
3d989ad0 src/com/owncloud/android/ui/preview/PreviewImageActivity.java  (David A. Velasco 2013-02-27 15:50:52 +0100 124)         mDownloadConnection = new PreviewImageServiceConnection();
3d989ad0 src/com/owncloud/android/ui/preview/PreviewImageActivity.java  (David A. Velasco 2013-02-27 15:50:52 +0100 125)         bindService(new Intent(this, FileDownloader.class), mDownloadConnection, Context.BIND_AUTO_CREATE);
3d989ad0 src/com/owncloud/android/ui/preview/PreviewImageActivity.java  (David A. Velasco 2013-02-27 15:50:52 +0100 126)         mUploadConnection = new PreviewImageServiceConnection();
3d989ad0 src/com/owncloud/android/ui/preview/PreviewImageActivity.java  (David A. Velasco 2013-02-27 15:50:52 +0100 127)         bindService(new Intent(this, FileUploader.class), mUploadConnection, Context.BIND_AUTO_CREATE);
86cec34b src/com/owncloud/android/ui/preview/PreviewImageActivity.java  (David A. Velasco 2013-02-27 14:08:58 +0100 128)     }
86cec34b src/com/owncloud/android/ui/preview/PreviewImageActivity.java  (David A. Velasco 2013-02-27 14:08:58 +0100 129)     
3d272eb7 src/com/owncloud/android/ui/activity/PreviewImageActivity.java (David A. Velasco 2013-02-25 12:24:14 +0100 130)     @Override
3d272eb7 src/com/owncloud/android/ui/activity/PreviewImageActivity.java (David A. Velasco 2013-02-25 12:24:14 +0100 131)     protected void onSaveInstanceState(Bundle outState) {
3d272eb7 src/com/owncloud/android/ui/activity/PreviewImageActivity.java (David A. Velasco 2013-02-25 12:24:14 +0100 132)         super.onSaveInstanceState(outState);
a3aca946 src/com/owncloud/android/ui/preview/PreviewImageActivity.java  (David A. Velasco 2013-02-26 13:17:44 +0100 133)         outState.putBoolean(KEY_WAITING_FOR_BINDER, mRequestWaitingForBinder);    
3d272eb7 src/com/owncloud/android/ui/activity/PreviewImageActivity.java (David A. Velasco 2013-02-25 12:24:14 +0100 134)     }
3d272eb7 src/com/owncloud/android/ui/activity/PreviewImageActivity.java (David A. Velasco 2013-02-25 12:24:14 +0100 135) 
3d272eb7 src/com/owncloud/android/ui/activity/PreviewImageActivity.java (David A. Velasco 2013-02-25 12:24:14 +0100 136) 
3d272eb7 src/com/owncloud/android/ui/activity/PreviewImageActivity.java (David A. Velasco 2013-02-25 12:24:14 +0100 137)     /** Defines callbacks for service binding, passed to bindService() */
fc2af5b0 src/com/owncloud/android/ui/preview/PreviewImageActivity.java  (David A. Velasco 2013-02-26 12:33:28 +0100 138)     private class PreviewImageServiceConnection implements ServiceConnection {
3d272eb7 src/com/owncloud/android/ui/activity/PreviewImageActivity.java (David A. Velasco 2013-02-25 12:24:14 +0100 139) 
3d272eb7 src/com/owncloud/android/ui/activity/PreviewImageActivity.java (David A. Velasco 2013-02-25 12:24:14 +0100 140)         @Override
3d272eb7 src/com/owncloud/android/ui/activity/PreviewImageActivity.java (David A. Velasco 2013-02-25 12:24:14 +0100 141)         public void onServiceConnected(ComponentName component, IBinder service) {
3d272eb7 src/com/owncloud/android/ui/activity/PreviewImageActivity.java (David A. Velasco 2013-02-25 12:24:14 +0100 142)                 
3d272eb7 src/com/owncloud/android/ui/activity/PreviewImageActivity.java (David A. Velasco 2013-02-25 12:24:14 +0100 143)             if (component.equals(new ComponentName(PreviewImageActivity.this, FileDownloader.class))) {
3d272eb7 src/com/owncloud/android/ui/activity/PreviewImageActivity.java (David A. Velasco 2013-02-25 12:24:14 +0100 144)                 mDownloaderBinder = (FileDownloaderBinder) service;
a3aca946 src/com/owncloud/android/ui/preview/PreviewImageActivity.java  (David A. Velasco 2013-02-26 13:17:44 +0100 145)                 if (mRequestWaitingForBinder) {
86cec34b src/com/owncloud/android/ui/preview/PreviewImageActivity.java  (David A. Velasco 2013-02-27 14:08:58 +0100 146)                     mRequestWaitingForBinder = false;
2946d8dd src/com/owncloud/android/ui/preview/PreviewImageActivity.java  (David A. Velasco 2013-04-25 19:39:22 +0200 147)                     Log_OC.d(TAG, "Simulating reselection of current page after connection of download binder");
86cec34b src/com/owncloud/android/ui/preview/PreviewImageActivity.java  (David A. Velasco 2013-02-27 14:08:58 +0100 148)                     onPageSelected(mViewPager.getCurrentItem());
a3aca946 src/com/owncloud/android/ui/preview/PreviewImageActivity.java  (David A. Velasco 2013-02-26 13:17:44 +0100 149)                 }
3d272eb7 src/com/owncloud/android/ui/activity/PreviewImageActivity.java (David A. Velasco 2013-02-25 12:24:14 +0100 150)                     
3d272eb7 src/com/owncloud/android/ui/activity/PreviewImageActivity.java (David A. Velasco 2013-02-25 12:24:14 +0100 151)             } else if (component.equals(new ComponentName(PreviewImageActivity.this, FileUploader.class))) {
2946d8dd src/com/owncloud/android/ui/preview/PreviewImageActivity.java  (David A. Velasco 2013-04-25 19:39:22 +0200 152)                 Log_OC.d(TAG, "Upload service connected");
3d272eb7 src/com/owncloud/android/ui/activity/PreviewImageActivity.java (David A. Velasco 2013-02-25 12:24:14 +0100 153)                 mUploaderBinder = (FileUploaderBinder) service;
3d272eb7 src/com/owncloud/android/ui/activity/PreviewImageActivity.java (David A. Velasco 2013-02-25 12:24:14 +0100 154)             } else {
3d272eb7 src/com/owncloud/android/ui/activity/PreviewImageActivity.java (David A. Velasco 2013-02-25 12:24:14 +0100 155)                 return;
3d272eb7 src/com/owncloud/android/ui/activity/PreviewImageActivity.java (David A. Velasco 2013-02-25 12:24:14 +0100 156)             }
3d272eb7 src/com/owncloud/android/ui/activity/PreviewImageActivity.java (David A. Velasco 2013-02-25 12:24:14 +0100 157)             
3d272eb7 src/com/owncloud/android/ui/activity/PreviewImageActivity.java (David A. Velasco 2013-02-25 12:24:14 +0100 158)         }
3d272eb7 src/com/owncloud/android/ui/activity/PreviewImageActivity.java (David A. Velasco 2013-02-25 12:24:14 +0100 159) 
3d272eb7 src/com/owncloud/android/ui/activity/PreviewImageActivity.java (David A. Velasco 2013-02-25 12:24:14 +0100 160)         @Override
3d272eb7 src/com/owncloud/android/ui/activity/PreviewImageActivity.java (David A. Velasco 2013-02-25 12:24:14 +0100 161)         public void onServiceDisconnected(ComponentName component) {
3d272eb7 src/com/owncloud/android/ui/activity/PreviewImageActivity.java (David A. Velasco 2013-02-25 12:24:14 +0100 162)             if (component.equals(new ComponentName(PreviewImageActivity.this, FileDownloader.class))) {
2946d8dd src/com/owncloud/android/ui/preview/PreviewImageActivity.java  (David A. Velasco 2013-04-25 19:39:22 +0200 163)                 Log_OC.d(TAG, "Download service suddenly disconnected");
3d272eb7 src/com/owncloud/android/ui/activity/PreviewImageActivity.java (David A. Velasco 2013-02-25 12:24:14 +0100 164)                 mDownloaderBinder = null;
3d272eb7 src/com/owncloud/android/ui/activity/PreviewImageActivity.java (David A. Velasco 2013-02-25 12:24:14 +0100 165)             } else if (component.equals(new ComponentName(PreviewImageActivity.this, FileUploader.class))) {
2946d8dd src/com/owncloud/android/ui/preview/PreviewImageActivity.java  (David A. Velasco 2013-04-25 19:39:22 +0200 166)                 Log_OC.d(TAG, "Upload service suddenly disconnected");
3d272eb7 src/com/owncloud/android/ui/activity/PreviewImageActivity.java (David A. Velasco 2013-02-25 12:24:14 +0100 167)                 mUploaderBinder = null;
3d272eb7 src/com/owncloud/android/ui/activity/PreviewImageActivity.java (David A. Velasco 2013-02-25 12:24:14 +0100 168)             }
3d272eb7 src/com/owncloud/android/ui/activity/PreviewImageActivity.java (David A. Velasco 2013-02-25 12:24:14 +0100 169)         }
3d272eb7 src/com/owncloud/android/ui/activity/PreviewImageActivity.java (David A. Velasco 2013-02-25 12:24:14 +0100 170)     };    
3d272eb7 src/com/owncloud/android/ui/activity/PreviewImageActivity.java (David A. Velasco 2013-02-25 12:24:14 +0100 171)     
3d272eb7 src/com/owncloud/android/ui/activity/PreviewImageActivity.java (David A. Velasco 2013-02-25 12:24:14 +0100 172)     
3d272eb7 src/com/owncloud/android/ui/activity/PreviewImageActivity.java (David A. Velasco 2013-02-25 12:24:14 +0100 173)     @Override
3d989ad0 src/com/owncloud/android/ui/preview/PreviewImageActivity.java  (David A. Velasco 2013-02-27 15:50:52 +0100 174)     public void onStop() {
3d989ad0 src/com/owncloud/android/ui/preview/PreviewImageActivity.java  (David A. Velasco 2013-02-27 15:50:52 +0100 175)         super.onStop();
3d272eb7 src/com/owncloud/android/ui/activity/PreviewImageActivity.java (David A. Velasco 2013-02-25 12:24:14 +0100 176)         if (mDownloadConnection != null) {
3d272eb7 src/com/owncloud/android/ui/activity/PreviewImageActivity.java (David A. Velasco 2013-02-25 12:24:14 +0100 177)             unbindService(mDownloadConnection);
3d272eb7 src/com/owncloud/android/ui/activity/PreviewImageActivity.java (David A. Velasco 2013-02-25 12:24:14 +0100 178)             mDownloadConnection = null;
3d272eb7 src/com/owncloud/android/ui/activity/PreviewImageActivity.java (David A. Velasco 2013-02-25 12:24:14 +0100 179)         }
3d272eb7 src/com/owncloud/android/ui/activity/PreviewImageActivity.java (David A. Velasco 2013-02-25 12:24:14 +0100 180)         if (mUploadConnection != null) {
3d272eb7 src/com/owncloud/android/ui/activity/PreviewImageActivity.java (David A. Velasco 2013-02-25 12:24:14 +0100 181)             unbindService(mUploadConnection);
3d272eb7 src/com/owncloud/android/ui/activity/PreviewImageActivity.java (David A. Velasco 2013-02-25 12:24:14 +0100 182)             mUploadConnection = null;
3d272eb7 src/com/owncloud/android/ui/activity/PreviewImageActivity.java (David A. Velasco 2013-02-25 12:24:14 +0100 183)         }
3d272eb7 src/com/owncloud/android/ui/activity/PreviewImageActivity.java (David A. Velasco 2013-02-25 12:24:14 +0100 184)     }
3d272eb7 src/com/owncloud/android/ui/activity/PreviewImageActivity.java (David A. Velasco 2013-02-25 12:24:14 +0100 185)     
3d272eb7 src/com/owncloud/android/ui/activity/PreviewImageActivity.java (David A. Velasco 2013-02-25 12:24:14 +0100 186)     
3d272eb7 src/com/owncloud/android/ui/activity/PreviewImageActivity.java (David A. Velasco 2013-02-25 12:24:14 +0100 187)     @Override
3d989ad0 src/com/owncloud/android/ui/preview/PreviewImageActivity.java  (David A. Velasco 2013-02-27 15:50:52 +0100 188)     public void onDestroy() {
3d989ad0 src/com/owncloud/android/ui/preview/PreviewImageActivity.java  (David A. Velasco 2013-02-27 15:50:52 +0100 189)         super.onDestroy();
3d989ad0 src/com/owncloud/android/ui/preview/PreviewImageActivity.java  (David A. Velasco 2013-02-27 15:50:52 +0100 190)     }
3d989ad0 src/com/owncloud/android/ui/preview/PreviewImageActivity.java  (David A. Velasco 2013-02-27 15:50:52 +0100 191)     
3d989ad0 src/com/owncloud/android/ui/preview/PreviewImageActivity.java  (David A. Velasco 2013-02-27 15:50:52 +0100 192)     
3d989ad0 src/com/owncloud/android/ui/preview/PreviewImageActivity.java  (David A. Velasco 2013-02-27 15:50:52 +0100 193)     @Override
3d272eb7 src/com/owncloud/android/ui/activity/PreviewImageActivity.java (David A. Velasco 2013-02-25 12:24:14 +0100 194)     public boolean onOptionsItemSelected(MenuItem item) {
3d272eb7 src/com/owncloud/android/ui/activity/PreviewImageActivity.java (David A. Velasco 2013-02-25 12:24:14 +0100 195)         boolean returnValue = false;
3d272eb7 src/com/owncloud/android/ui/activity/PreviewImageActivity.java (David A. Velasco 2013-02-25 12:24:14 +0100 196)         
3d272eb7 src/com/owncloud/android/ui/activity/PreviewImageActivity.java (David A. Velasco 2013-02-25 12:24:14 +0100 197)         switch(item.getItemId()){
3d272eb7 src/com/owncloud/android/ui/activity/PreviewImageActivity.java (David A. Velasco 2013-02-25 12:24:14 +0100 198)         case android.R.id.home:
3d272eb7 src/com/owncloud/android/ui/activity/PreviewImageActivity.java (David A. Velasco 2013-02-25 12:24:14 +0100 199)             backToDisplayActivity();
3d272eb7 src/com/owncloud/android/ui/activity/PreviewImageActivity.java (David A. Velasco 2013-02-25 12:24:14 +0100 200)             returnValue = true;
3d272eb7 src/com/owncloud/android/ui/activity/PreviewImageActivity.java (David A. Velasco 2013-02-25 12:24:14 +0100 201)             break;
3d272eb7 src/com/owncloud/android/ui/activity/PreviewImageActivity.java (David A. Velasco 2013-02-25 12:24:14 +0100 202)         default:
3d272eb7 src/com/owncloud/android/ui/activity/PreviewImageActivity.java (David A. Velasco 2013-02-25 12:24:14 +0100 203)         	returnValue = super.onOptionsItemSelected(item);
3d272eb7 src/com/owncloud/android/ui/activity/PreviewImageActivity.java (David A. Velasco 2013-02-25 12:24:14 +0100 204)         }
3d272eb7 src/com/owncloud/android/ui/activity/PreviewImageActivity.java (David A. Velasco 2013-02-25 12:24:14 +0100 205)         
3d272eb7 src/com/owncloud/android/ui/activity/PreviewImageActivity.java (David A. Velasco 2013-02-25 12:24:14 +0100 206)         return returnValue;
3d272eb7 src/com/owncloud/android/ui/activity/PreviewImageActivity.java (David A. Velasco 2013-02-25 12:24:14 +0100 207)     }
3d272eb7 src/com/owncloud/android/ui/activity/PreviewImageActivity.java (David A. Velasco 2013-02-25 12:24:14 +0100 208) 
3d272eb7 src/com/owncloud/android/ui/activity/PreviewImageActivity.java (David A. Velasco 2013-02-25 12:24:14 +0100 209) 
3d272eb7 src/com/owncloud/android/ui/activity/PreviewImageActivity.java (David A. Velasco 2013-02-25 12:24:14 +0100 210)     @Override
3d272eb7 src/com/owncloud/android/ui/activity/PreviewImageActivity.java (David A. Velasco 2013-02-25 12:24:14 +0100 211)     protected void onResume() {
3d272eb7 src/com/owncloud/android/ui/activity/PreviewImageActivity.java (David A. Velasco 2013-02-25 12:24:14 +0100 212)         super.onResume();
07a89382 src/com/owncloud/android/ui/preview/PreviewImageActivity.java  (David A. Velasco 2013-03-01 11:17:12 +0100 213)         //Log.e(TAG, "ACTIVITY, ONRESUME");
86cec34b src/com/owncloud/android/ui/preview/PreviewImageActivity.java  (David A. Velasco 2013-02-27 14:08:58 +0100 214)         mDownloadFinishReceiver = new DownloadFinishReceiver();
86cec34b src/com/owncloud/android/ui/preview/PreviewImageActivity.java  (David A. Velasco 2013-02-27 14:08:58 +0100 215)         IntentFilter filter = new IntentFilter(FileDownloader.DOWNLOAD_FINISH_MESSAGE);
48e7e403 src/com/owncloud/android/ui/preview/PreviewImageActivity.java  (David A. Velasco 2013-03-01 15:30:46 +0100 216)         filter.addAction(FileDownloader.DOWNLOAD_ADDED_MESSAGE);
86cec34b src/com/owncloud/android/ui/preview/PreviewImageActivity.java  (David A. Velasco 2013-02-27 14:08:58 +0100 217)         registerReceiver(mDownloadFinishReceiver, filter);
86cec34b src/com/owncloud/android/ui/preview/PreviewImageActivity.java  (David A. Velasco 2013-02-27 14:08:58 +0100 218)     }
86cec34b src/com/owncloud/android/ui/preview/PreviewImageActivity.java  (David A. Velasco 2013-02-27 14:08:58 +0100 219) 
86cec34b src/com/owncloud/android/ui/preview/PreviewImageActivity.java  (David A. Velasco 2013-02-27 14:08:58 +0100 220)     @Override
86cec34b src/com/owncloud/android/ui/preview/PreviewImageActivity.java  (David A. Velasco 2013-02-27 14:08:58 +0100 221)     protected void onPostResume() {
07a89382 src/com/owncloud/android/ui/preview/PreviewImageActivity.java  (David A. Velasco 2013-03-01 11:17:12 +0100 222)         //Log.e(TAG, "ACTIVITY, ONPOSTRESUME");
86cec34b src/com/owncloud/android/ui/preview/PreviewImageActivity.java  (David A. Velasco 2013-02-27 14:08:58 +0100 223)         super.onPostResume();
86cec34b src/com/owncloud/android/ui/preview/PreviewImageActivity.java  (David A. Velasco 2013-02-27 14:08:58 +0100 224)     }
86cec34b src/com/owncloud/android/ui/preview/PreviewImageActivity.java  (David A. Velasco 2013-02-27 14:08:58 +0100 225)     
86cec34b src/com/owncloud/android/ui/preview/PreviewImageActivity.java  (David A. Velasco 2013-02-27 14:08:58 +0100 226)     @Override
86cec34b src/com/owncloud/android/ui/preview/PreviewImageActivity.java  (David A. Velasco 2013-02-27 14:08:58 +0100 227)     public void onPause() {
86cec34b src/com/owncloud/android/ui/preview/PreviewImageActivity.java  (David A. Velasco 2013-02-27 14:08:58 +0100 228)         super.onPause();
86cec34b src/com/owncloud/android/ui/preview/PreviewImageActivity.java  (David A. Velasco 2013-02-27 14:08:58 +0100 229)         unregisterReceiver(mDownloadFinishReceiver);
86cec34b src/com/owncloud/android/ui/preview/PreviewImageActivity.java  (David A. Velasco 2013-02-27 14:08:58 +0100 230)         mDownloadFinishReceiver = null;
3d272eb7 src/com/owncloud/android/ui/activity/PreviewImageActivity.java (David A. Velasco 2013-02-25 12:24:14 +0100 231)     }
3d272eb7 src/com/owncloud/android/ui/activity/PreviewImageActivity.java (David A. Velasco 2013-02-25 12:24:14 +0100 232)     
3d272eb7 src/com/owncloud/android/ui/activity/PreviewImageActivity.java (David A. Velasco 2013-02-25 12:24:14 +0100 233) 
3d272eb7 src/com/owncloud/android/ui/activity/PreviewImageActivity.java (David A. Velasco 2013-02-25 12:24:14 +0100 234)     private void backToDisplayActivity() {
3d272eb7 src/com/owncloud/android/ui/activity/PreviewImageActivity.java (David A. Velasco 2013-02-25 12:24:14 +0100 235)         finish();
3d272eb7 src/com/owncloud/android/ui/activity/PreviewImageActivity.java (David A. Velasco 2013-02-25 12:24:14 +0100 236)     }
3d272eb7 src/com/owncloud/android/ui/activity/PreviewImageActivity.java (David A. Velasco 2013-02-25 12:24:14 +0100 237)     
3d272eb7 src/com/owncloud/android/ui/activity/PreviewImageActivity.java (David A. Velasco 2013-02-25 12:24:14 +0100 238)     
3d272eb7 src/com/owncloud/android/ui/activity/PreviewImageActivity.java (David A. Velasco 2013-02-25 12:24:14 +0100 239)     @Override
3d272eb7 src/com/owncloud/android/ui/activity/PreviewImageActivity.java (David A. Velasco 2013-02-25 12:24:14 +0100 240)     protected Dialog onCreateDialog(int id) {
3d272eb7 src/com/owncloud/android/ui/activity/PreviewImageActivity.java (David A. Velasco 2013-02-25 12:24:14 +0100 241)         Dialog dialog = null;
3d272eb7 src/com/owncloud/android/ui/activity/PreviewImageActivity.java (David A. Velasco 2013-02-25 12:24:14 +0100 242)         switch (id) {
3d272eb7 src/com/owncloud/android/ui/activity/PreviewImageActivity.java (David A. Velasco 2013-02-25 12:24:14 +0100 243)         case DIALOG_SHORT_WAIT: {
3d272eb7 src/com/owncloud/android/ui/activity/PreviewImageActivity.java (David A. Velasco 2013-02-25 12:24:14 +0100 244)             ProgressDialog working_dialog = new ProgressDialog(this);
3d272eb7 src/com/owncloud/android/ui/activity/PreviewImageActivity.java (David A. Velasco 2013-02-25 12:24:14 +0100 245)             working_dialog.setMessage(getResources().getString(
3d272eb7 src/com/owncloud/android/ui/activity/PreviewImageActivity.java (David A. Velasco 2013-02-25 12:24:14 +0100 246)                     R.string.wait_a_moment));
3d272eb7 src/com/owncloud/android/ui/activity/PreviewImageActivity.java (David A. Velasco 2013-02-25 12:24:14 +0100 247)             working_dialog.setIndeterminate(true);
3d272eb7 src/com/owncloud/android/ui/activity/PreviewImageActivity.java (David A. Velasco 2013-02-25 12:24:14 +0100 248)             working_dialog.setCancelable(false);
3d272eb7 src/com/owncloud/android/ui/activity/PreviewImageActivity.java (David A. Velasco 2013-02-25 12:24:14 +0100 249)             dialog = working_dialog;
3d272eb7 src/com/owncloud/android/ui/activity/PreviewImageActivity.java (David A. Velasco 2013-02-25 12:24:14 +0100 250)             break;
3d272eb7 src/com/owncloud/android/ui/activity/PreviewImageActivity.java (David A. Velasco 2013-02-25 12:24:14 +0100 251)         }
3d272eb7 src/com/owncloud/android/ui/activity/PreviewImageActivity.java (David A. Velasco 2013-02-25 12:24:14 +0100 252)         default:
3d272eb7 src/com/owncloud/android/ui/activity/PreviewImageActivity.java (David A. Velasco 2013-02-25 12:24:14 +0100 253)             dialog = null;
3d272eb7 src/com/owncloud/android/ui/activity/PreviewImageActivity.java (David A. Velasco 2013-02-25 12:24:14 +0100 254)         }
3d272eb7 src/com/owncloud/android/ui/activity/PreviewImageActivity.java (David A. Velasco 2013-02-25 12:24:14 +0100 255)         return dialog;
3d272eb7 src/com/owncloud/android/ui/activity/PreviewImageActivity.java (David A. Velasco 2013-02-25 12:24:14 +0100 256)     }
3d272eb7 src/com/owncloud/android/ui/activity/PreviewImageActivity.java (David A. Velasco 2013-02-25 12:24:14 +0100 257)     
3d272eb7 src/com/owncloud/android/ui/activity/PreviewImageActivity.java (David A. Velasco 2013-02-25 12:24:14 +0100 258)     
3d272eb7 src/com/owncloud/android/ui/activity/PreviewImageActivity.java (David A. Velasco 2013-02-25 12:24:14 +0100 259)     /**
3d272eb7 src/com/owncloud/android/ui/activity/PreviewImageActivity.java (David A. Velasco 2013-02-25 12:24:14 +0100 260)      * {@inheritDoc}
3d272eb7 src/com/owncloud/android/ui/activity/PreviewImageActivity.java (David A. Velasco 2013-02-25 12:24:14 +0100 261)      */
3d272eb7 src/com/owncloud/android/ui/activity/PreviewImageActivity.java (David A. Velasco 2013-02-25 12:24:14 +0100 262)     @Override
3d272eb7 src/com/owncloud/android/ui/activity/PreviewImageActivity.java (David A. Velasco 2013-02-25 12:24:14 +0100 263)     public void onFileStateChanged() {
3d272eb7 src/com/owncloud/android/ui/activity/PreviewImageActivity.java (David A. Velasco 2013-02-25 12:24:14 +0100 264)         // nothing to do here!
3d272eb7 src/com/owncloud/android/ui/activity/PreviewImageActivity.java (David A. Velasco 2013-02-25 12:24:14 +0100 265)     }
3d272eb7 src/com/owncloud/android/ui/activity/PreviewImageActivity.java (David A. Velasco 2013-02-25 12:24:14 +0100 266) 
3d272eb7 src/com/owncloud/android/ui/activity/PreviewImageActivity.java (David A. Velasco 2013-02-25 12:24:14 +0100 267)     
3d272eb7 src/com/owncloud/android/ui/activity/PreviewImageActivity.java (David A. Velasco 2013-02-25 12:24:14 +0100 268)     /**
3d272eb7 src/com/owncloud/android/ui/activity/PreviewImageActivity.java (David A. Velasco 2013-02-25 12:24:14 +0100 269)      * {@inheritDoc}
3d272eb7 src/com/owncloud/android/ui/activity/PreviewImageActivity.java (David A. Velasco 2013-02-25 12:24:14 +0100 270)      */
3d272eb7 src/com/owncloud/android/ui/activity/PreviewImageActivity.java (David A. Velasco 2013-02-25 12:24:14 +0100 271)     @Override
3d272eb7 src/com/owncloud/android/ui/activity/PreviewImageActivity.java (David A. Velasco 2013-02-25 12:24:14 +0100 272)     public FileDownloaderBinder getFileDownloaderBinder() {
3d272eb7 src/com/owncloud/android/ui/activity/PreviewImageActivity.java (David A. Velasco 2013-02-25 12:24:14 +0100 273)         return mDownloaderBinder;
3d272eb7 src/com/owncloud/android/ui/activity/PreviewImageActivity.java (David A. Velasco 2013-02-25 12:24:14 +0100 274)     }
3d272eb7 src/com/owncloud/android/ui/activity/PreviewImageActivity.java (David A. Velasco 2013-02-25 12:24:14 +0100 275) 
3d272eb7 src/com/owncloud/android/ui/activity/PreviewImageActivity.java (David A. Velasco 2013-02-25 12:24:14 +0100 276) 
3d272eb7 src/com/owncloud/android/ui/activity/PreviewImageActivity.java (David A. Velasco 2013-02-25 12:24:14 +0100 277)     @Override
3d272eb7 src/com/owncloud/android/ui/activity/PreviewImageActivity.java (David A. Velasco 2013-02-25 12:24:14 +0100 278)     public FileUploaderBinder getFileUploaderBinder() {
3d272eb7 src/com/owncloud/android/ui/activity/PreviewImageActivity.java (David A. Velasco 2013-02-25 12:24:14 +0100 279)         return mUploaderBinder;
3d272eb7 src/com/owncloud/android/ui/activity/PreviewImageActivity.java (David A. Velasco 2013-02-25 12:24:14 +0100 280)     }
3d272eb7 src/com/owncloud/android/ui/activity/PreviewImageActivity.java (David A. Velasco 2013-02-25 12:24:14 +0100 281) 
3d272eb7 src/com/owncloud/android/ui/activity/PreviewImageActivity.java (David A. Velasco 2013-02-25 12:24:14 +0100 282) 
3d272eb7 src/com/owncloud/android/ui/activity/PreviewImageActivity.java (David A. Velasco 2013-02-25 12:24:14 +0100 283)     @Override
fd396289 src/com/owncloud/android/ui/preview/PreviewImageActivity.java  (David A. Velasco 2013-05-30 17:53:21 +0200 284)     public void showDetails(OCFile file) {
fd396289 src/com/owncloud/android/ui/preview/PreviewImageActivity.java  (David A. Velasco 2013-05-30 17:53:21 +0200 285)         Intent showDetailsIntent = new Intent(this, FileDisplayActivity.class);
fd396289 src/com/owncloud/android/ui/preview/PreviewImageActivity.java  (David A. Velasco 2013-05-30 17:53:21 +0200 286)         showDetailsIntent.setAction(FileDisplayActivity.ACTION_DETAILS);
bc1fcf84 src/com/owncloud/android/ui/preview/PreviewImageActivity.java  (David A. Velasco 2013-05-07 13:49:54 +0200 287)         showDetailsIntent.putExtra(FileActivity.EXTRA_FILE, file);
bc1fcf84 src/com/owncloud/android/ui/preview/PreviewImageActivity.java  (David A. Velasco 2013-05-07 13:49:54 +0200 288)         showDetailsIntent.putExtra(FileActivity.EXTRA_ACCOUNT, AccountUtils.getCurrentOwnCloudAccount(this));
3d272eb7 src/com/owncloud/android/ui/activity/PreviewImageActivity.java (David A. Velasco 2013-02-25 12:24:14 +0100 289)         startActivity(showDetailsIntent);
48e7e403 src/com/owncloud/android/ui/preview/PreviewImageActivity.java  (David A. Velasco 2013-03-01 15:30:46 +0100 290)         int pos = mPreviewImagePagerAdapter.getFilePosition(file);
48e7e403 src/com/owncloud/android/ui/preview/PreviewImageActivity.java  (David A. Velasco 2013-03-01 15:30:46 +0100 291)         file = mPreviewImagePagerAdapter.getFileAt(pos);
48e7e403 src/com/owncloud/android/ui/preview/PreviewImageActivity.java  (David A. Velasco 2013-03-01 15:30:46 +0100 292)         
3d272eb7 src/com/owncloud/android/ui/activity/PreviewImageActivity.java (David A. Velasco 2013-02-25 12:24:14 +0100 293)     }
3d272eb7 src/com/owncloud/android/ui/activity/PreviewImageActivity.java (David A. Velasco 2013-02-25 12:24:14 +0100 294) 
3d272eb7 src/com/owncloud/android/ui/activity/PreviewImageActivity.java (David A. Velasco 2013-02-25 12:24:14 +0100 295)     
86cec34b src/com/owncloud/android/ui/preview/PreviewImageActivity.java  (David A. Velasco 2013-02-27 14:08:58 +0100 296)     private void requestForDownload(OCFile file) {
a3aca946 src/com/owncloud/android/ui/preview/PreviewImageActivity.java  (David A. Velasco 2013-02-26 13:17:44 +0100 297)         if (mDownloaderBinder == null) {
2946d8dd src/com/owncloud/android/ui/preview/PreviewImageActivity.java  (David A. Velasco 2013-04-25 19:39:22 +0200 298)             Log_OC.d(TAG, "requestForDownload called without binder to download service");
a3aca946 src/com/owncloud/android/ui/preview/PreviewImageActivity.java  (David A. Velasco 2013-02-26 13:17:44 +0100 299)             
f9babe7b src/com/owncloud/android/ui/preview/PreviewImageActivity.java  (David A. Velasco 2013-06-14 12:53:45 +0200 300)         } else if (!mDownloaderBinder.isDownloading(getAccount(), file)) {
3d272eb7 src/com/owncloud/android/ui/activity/PreviewImageActivity.java (David A. Velasco 2013-02-25 12:24:14 +0100 301)             Intent i = new Intent(this, FileDownloader.class);
f9babe7b src/com/owncloud/android/ui/preview/PreviewImageActivity.java  (David A. Velasco 2013-06-14 12:53:45 +0200 302)             i.putExtra(FileDownloader.EXTRA_ACCOUNT, getAccount());
86cec34b src/com/owncloud/android/ui/preview/PreviewImageActivity.java  (David A. Velasco 2013-02-27 14:08:58 +0100 303)             i.putExtra(FileDownloader.EXTRA_FILE, file);
3d272eb7 src/com/owncloud/android/ui/activity/PreviewImageActivity.java (David A. Velasco 2013-02-25 12:24:14 +0100 304)             startService(i);
3d272eb7 src/com/owncloud/android/ui/activity/PreviewImageActivity.java (David A. Velasco 2013-02-25 12:24:14 +0100 305)         }
3d272eb7 src/com/owncloud/android/ui/activity/PreviewImageActivity.java (David A. Velasco 2013-02-25 12:24:14 +0100 306)     }
3d272eb7 src/com/owncloud/android/ui/activity/PreviewImageActivity.java (David A. Velasco 2013-02-25 12:24:14 +0100 307) 
d7e05192 src/com/owncloud/android/ui/activity/PreviewImageActivity.java (David A. Velasco 2013-02-25 14:33:16 +0100 308)     /**
d7e05192 src/com/owncloud/android/ui/activity/PreviewImageActivity.java (David A. Velasco 2013-02-25 14:33:16 +0100 309)      * This method will be invoked when a new page becomes selected. Animation is not necessarily complete.
d7e05192 src/com/owncloud/android/ui/activity/PreviewImageActivity.java (David A. Velasco 2013-02-25 14:33:16 +0100 310)      * 
d7e05192 src/com/owncloud/android/ui/activity/PreviewImageActivity.java (David A. Velasco 2013-02-25 14:33:16 +0100 311)      *  @param  Position        Position index of the new selected page
d7e05192 src/com/owncloud/android/ui/activity/PreviewImageActivity.java (David A. Velasco 2013-02-25 14:33:16 +0100 312)      */
d7e05192 src/com/owncloud/android/ui/activity/PreviewImageActivity.java (David A. Velasco 2013-02-25 14:33:16 +0100 313)     @Override
d7e05192 src/com/owncloud/android/ui/activity/PreviewImageActivity.java (David A. Velasco 2013-02-25 14:33:16 +0100 314)     public void onPageSelected(int position) {
86cec34b src/com/owncloud/android/ui/preview/PreviewImageActivity.java  (David A. Velasco 2013-02-27 14:08:58 +0100 315)         if (mDownloaderBinder == null) {
86cec34b src/com/owncloud/android/ui/preview/PreviewImageActivity.java  (David A. Velasco 2013-02-27 14:08:58 +0100 316)             mRequestWaitingForBinder = true;
86cec34b src/com/owncloud/android/ui/preview/PreviewImageActivity.java  (David A. Velasco 2013-02-27 14:08:58 +0100 317)             
fc2af5b0 src/com/owncloud/android/ui/preview/PreviewImageActivity.java  (David A. Velasco 2013-02-26 12:33:28 +0100 318)         } else {
86cec34b src/com/owncloud/android/ui/preview/PreviewImageActivity.java  (David A. Velasco 2013-02-27 14:08:58 +0100 319)             OCFile currentFile = mPreviewImagePagerAdapter.getFileAt(position); 
86cec34b src/com/owncloud/android/ui/preview/PreviewImageActivity.java  (David A. Velasco 2013-02-27 14:08:58 +0100 320)             getSupportActionBar().setTitle(currentFile.getFileName());
86cec34b src/com/owncloud/android/ui/preview/PreviewImageActivity.java  (David A. Velasco 2013-02-27 14:08:58 +0100 321)             if (!currentFile.isDown()) {
db7eea71 src/com/owncloud/android/ui/preview/PreviewImageActivity.java  (David A. Velasco 2013-03-01 14:20:25 +0100 322)                 if (!mPreviewImagePagerAdapter.pendingErrorAt(position)) {
db7eea71 src/com/owncloud/android/ui/preview/PreviewImageActivity.java  (David A. Velasco 2013-03-01 14:20:25 +0100 323)                     requestForDownload(currentFile);
db7eea71 src/com/owncloud/android/ui/preview/PreviewImageActivity.java  (David A. Velasco 2013-03-01 14:20:25 +0100 324)                 }
86cec34b src/com/owncloud/android/ui/preview/PreviewImageActivity.java  (David A. Velasco 2013-02-27 14:08:58 +0100 325)             }
fc2af5b0 src/com/owncloud/android/ui/preview/PreviewImageActivity.java  (David A. Velasco 2013-02-26 12:33:28 +0100 326)         }
d7e05192 src/com/owncloud/android/ui/activity/PreviewImageActivity.java (David A. Velasco 2013-02-25 14:33:16 +0100 327)     }
d7e05192 src/com/owncloud/android/ui/activity/PreviewImageActivity.java (David A. Velasco 2013-02-25 14:33:16 +0100 328)     
d7e05192 src/com/owncloud/android/ui/activity/PreviewImageActivity.java (David A. Velasco 2013-02-25 14:33:16 +0100 329)     /**
d7e05192 src/com/owncloud/android/ui/activity/PreviewImageActivity.java (David A. Velasco 2013-02-25 14:33:16 +0100 330)      * Called when the scroll state changes. Useful for discovering when the user begins dragging, 
bdc0332c src/com/owncloud/android/ui/preview/PreviewImageActivity.java  (David A. Velasco 2013-04-17 12:26:13 +0200 331)      * when the pager is automatically settling to the current page. when it is fully stopped/idle.
d7e05192 src/com/owncloud/android/ui/activity/PreviewImageActivity.java (David A. Velasco 2013-02-25 14:33:16 +0100 332)      * 
d7e05192 src/com/owncloud/android/ui/activity/PreviewImageActivity.java (David A. Velasco 2013-02-25 14:33:16 +0100 333)      * @param   State       The new scroll state (SCROLL_STATE_IDLE, _DRAGGING, _SETTLING
d7e05192 src/com/owncloud/android/ui/activity/PreviewImageActivity.java (David A. Velasco 2013-02-25 14:33:16 +0100 334)      */
d7e05192 src/com/owncloud/android/ui/activity/PreviewImageActivity.java (David A. Velasco 2013-02-25 14:33:16 +0100 335)     @Override
d7e05192 src/com/owncloud/android/ui/activity/PreviewImageActivity.java (David A. Velasco 2013-02-25 14:33:16 +0100 336)     public void onPageScrollStateChanged(int state) {
d7e05192 src/com/owncloud/android/ui/activity/PreviewImageActivity.java (David A. Velasco 2013-02-25 14:33:16 +0100 337)     }
d7e05192 src/com/owncloud/android/ui/activity/PreviewImageActivity.java (David A. Velasco 2013-02-25 14:33:16 +0100 338) 
d7e05192 src/com/owncloud/android/ui/activity/PreviewImageActivity.java (David A. Velasco 2013-02-25 14:33:16 +0100 339)     /**
d7e05192 src/com/owncloud/android/ui/activity/PreviewImageActivity.java (David A. Velasco 2013-02-25 14:33:16 +0100 340)      * This method will be invoked when the current page is scrolled, either as part of a programmatically 
d7e05192 src/com/owncloud/android/ui/activity/PreviewImageActivity.java (David A. Velasco 2013-02-25 14:33:16 +0100 341)      * initiated smooth scroll or a user initiated touch scroll.
d7e05192 src/com/owncloud/android/ui/activity/PreviewImageActivity.java (David A. Velasco 2013-02-25 14:33:16 +0100 342)      * 
d7e05192 src/com/owncloud/android/ui/activity/PreviewImageActivity.java (David A. Velasco 2013-02-25 14:33:16 +0100 343)      * @param   position                Position index of the first page currently being displayed. 
d7e05192 src/com/owncloud/android/ui/activity/PreviewImageActivity.java (David A. Velasco 2013-02-25 14:33:16 +0100 344)      *                                  Page position+1 will be visible if positionOffset is nonzero.
d7e05192 src/com/owncloud/android/ui/activity/PreviewImageActivity.java (David A. Velasco 2013-02-25 14:33:16 +0100 345)      *                                  
d7e05192 src/com/owncloud/android/ui/activity/PreviewImageActivity.java (David A. Velasco 2013-02-25 14:33:16 +0100 346)      * @param   positionOffset          Value from [0, 1) indicating the offset from the page at position.
d7e05192 src/com/owncloud/android/ui/activity/PreviewImageActivity.java (David A. Velasco 2013-02-25 14:33:16 +0100 347)      * @param   positionOffsetPixels    Value in pixels indicating the offset from position. 
d7e05192 src/com/owncloud/android/ui/activity/PreviewImageActivity.java (David A. Velasco 2013-02-25 14:33:16 +0100 348)      */
d7e05192 src/com/owncloud/android/ui/activity/PreviewImageActivity.java (David A. Velasco 2013-02-25 14:33:16 +0100 349)     @Override
d7e05192 src/com/owncloud/android/ui/activity/PreviewImageActivity.java (David A. Velasco 2013-02-25 14:33:16 +0100 350)     public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
d7e05192 src/com/owncloud/android/ui/activity/PreviewImageActivity.java (David A. Velasco 2013-02-25 14:33:16 +0100 351)     }
3d272eb7 src/com/owncloud/android/ui/activity/PreviewImageActivity.java (David A. Velasco 2013-02-25 12:24:14 +0100 352)     
86cec34b src/com/owncloud/android/ui/preview/PreviewImageActivity.java  (David A. Velasco 2013-02-27 14:08:58 +0100 353) 
86cec34b src/com/owncloud/android/ui/preview/PreviewImageActivity.java  (David A. Velasco 2013-02-27 14:08:58 +0100 354)     /**
86cec34b src/com/owncloud/android/ui/preview/PreviewImageActivity.java  (David A. Velasco 2013-02-27 14:08:58 +0100 355)      * Class waiting for broadcast events from the {@link FielDownloader} service.
86cec34b src/com/owncloud/android/ui/preview/PreviewImageActivity.java  (David A. Velasco 2013-02-27 14:08:58 +0100 356)      * 
86cec34b src/com/owncloud/android/ui/preview/PreviewImageActivity.java  (David A. Velasco 2013-02-27 14:08:58 +0100 357)      * Updates the UI when a download is started or finished, provided that it is relevant for the
86cec34b src/com/owncloud/android/ui/preview/PreviewImageActivity.java  (David A. Velasco 2013-02-27 14:08:58 +0100 358)      * folder displayed in the gallery.
86cec34b src/com/owncloud/android/ui/preview/PreviewImageActivity.java  (David A. Velasco 2013-02-27 14:08:58 +0100 359)      */
86cec34b src/com/owncloud/android/ui/preview/PreviewImageActivity.java  (David A. Velasco 2013-02-27 14:08:58 +0100 360)     private class DownloadFinishReceiver extends BroadcastReceiver {
86cec34b src/com/owncloud/android/ui/preview/PreviewImageActivity.java  (David A. Velasco 2013-02-27 14:08:58 +0100 361)         @Override
86cec34b src/com/owncloud/android/ui/preview/PreviewImageActivity.java  (David A. Velasco 2013-02-27 14:08:58 +0100 362)         public void onReceive(Context context, Intent intent) {
86cec34b src/com/owncloud/android/ui/preview/PreviewImageActivity.java  (David A. Velasco 2013-02-27 14:08:58 +0100 363)             String accountName = intent.getStringExtra(FileDownloader.ACCOUNT_NAME);
86cec34b src/com/owncloud/android/ui/preview/PreviewImageActivity.java  (David A. Velasco 2013-02-27 14:08:58 +0100 364)             String downloadedRemotePath = intent.getStringExtra(FileDownloader.EXTRA_REMOTE_PATH);
f9babe7b src/com/owncloud/android/ui/preview/PreviewImageActivity.java  (David A. Velasco 2013-06-14 12:53:45 +0200 365)             if (getAccount().name.equals(accountName) && 
86cec34b src/com/owncloud/android/ui/preview/PreviewImageActivity.java  (David A. Velasco 2013-02-27 14:08:58 +0100 366)                     downloadedRemotePath != null) {
86cec34b src/com/owncloud/android/ui/preview/PreviewImageActivity.java  (David A. Velasco 2013-02-27 14:08:58 +0100 367) 
86cec34b src/com/owncloud/android/ui/preview/PreviewImageActivity.java  (David A. Velasco 2013-02-27 14:08:58 +0100 368)                 OCFile file = mStorageManager.getFileByPath(downloadedRemotePath);
86cec34b src/com/owncloud/android/ui/preview/PreviewImageActivity.java  (David A. Velasco 2013-02-27 14:08:58 +0100 369)                 int position = mPreviewImagePagerAdapter.getFilePosition(file);
86cec34b src/com/owncloud/android/ui/preview/PreviewImageActivity.java  (David A. Velasco 2013-02-27 14:08:58 +0100 370)                 boolean downloadWasFine = intent.getBooleanExtra(FileDownloader.EXTRA_DOWNLOAD_RESULT, false);
db7eea71 src/com/owncloud/android/ui/preview/PreviewImageActivity.java  (David A. Velasco 2013-03-01 14:20:25 +0100 371)                 //boolean isOffscreen =  Math.abs((mViewPager.getCurrentItem() - position)) <= mViewPager.getOffscreenPageLimit();
86cec34b src/com/owncloud/android/ui/preview/PreviewImageActivity.java  (David A. Velasco 2013-02-27 14:08:58 +0100 372)                 
48e7e403 src/com/owncloud/android/ui/preview/PreviewImageActivity.java  (David A. Velasco 2013-03-01 15:30:46 +0100 373)                 if (position >= 0 && intent.getAction().equals(FileDownloader.DOWNLOAD_FINISH_MESSAGE)) {
86cec34b src/com/owncloud/android/ui/preview/PreviewImageActivity.java  (David A. Velasco 2013-02-27 14:08:58 +0100 374)                     if (downloadWasFine) {
db7eea71 src/com/owncloud/android/ui/preview/PreviewImageActivity.java  (David A. Velasco 2013-03-01 14:20:25 +0100 375)                         mPreviewImagePagerAdapter.updateFile(position, file);   
86cec34b src/com/owncloud/android/ui/preview/PreviewImageActivity.java  (David A. Velasco 2013-02-27 14:08:58 +0100 376)                         
db7eea71 src/com/owncloud/android/ui/preview/PreviewImageActivity.java  (David A. Velasco 2013-03-01 14:20:25 +0100 377)                     } else {
db7eea71 src/com/owncloud/android/ui/preview/PreviewImageActivity.java  (David A. Velasco 2013-03-01 14:20:25 +0100 378)                         mPreviewImagePagerAdapter.updateWithDownloadError(position);
86cec34b src/com/owncloud/android/ui/preview/PreviewImageActivity.java  (David A. Velasco 2013-02-27 14:08:58 +0100 379)                     }
db7eea71 src/com/owncloud/android/ui/preview/PreviewImageActivity.java  (David A. Velasco 2013-03-01 14:20:25 +0100 380)                     mPreviewImagePagerAdapter.notifyDataSetChanged();   // will trigger the creation of new fragments
86cec34b src/com/owncloud/android/ui/preview/PreviewImageActivity.java  (David A. Velasco 2013-02-27 14:08:58 +0100 381)                     
86cec34b src/com/owncloud/android/ui/preview/PreviewImageActivity.java  (David A. Velasco 2013-02-27 14:08:58 +0100 382)                 } else {
2946d8dd src/com/owncloud/android/ui/preview/PreviewImageActivity.java  (David A. Velasco 2013-04-25 19:39:22 +0200 383)                     Log_OC.d(TAG, "Download finished, but the fragment is offscreen");
86cec34b src/com/owncloud/android/ui/preview/PreviewImageActivity.java  (David A. Velasco 2013-02-27 14:08:58 +0100 384)                 }
86cec34b src/com/owncloud/android/ui/preview/PreviewImageActivity.java  (David A. Velasco 2013-02-27 14:08:58 +0100 385)                 
86cec34b src/com/owncloud/android/ui/preview/PreviewImageActivity.java  (David A. Velasco 2013-02-27 14:08:58 +0100 386)             }
86cec34b src/com/owncloud/android/ui/preview/PreviewImageActivity.java  (David A. Velasco 2013-02-27 14:08:58 +0100 387)             removeStickyBroadcast(intent);
86cec34b src/com/owncloud/android/ui/preview/PreviewImageActivity.java  (David A. Velasco 2013-02-27 14:08:58 +0100 388)         }
86cec34b src/com/owncloud/android/ui/preview/PreviewImageActivity.java  (David A. Velasco 2013-02-27 14:08:58 +0100 389) 
86cec34b src/com/owncloud/android/ui/preview/PreviewImageActivity.java  (David A. Velasco 2013-02-27 14:08:58 +0100 390)     }
638b7767 src/com/owncloud/android/ui/preview/PreviewImageActivity.java  (David A. Velasco 2013-02-28 17:09:00 +0100 391) 
638b7767 src/com/owncloud/android/ui/preview/PreviewImageActivity.java  (David A. Velasco 2013-02-28 17:09:00 +0100 392) 
638b7767 src/com/owncloud/android/ui/preview/PreviewImageActivity.java  (David A. Velasco 2013-02-28 17:09:00 +0100 393)     @Override
638b7767 src/com/owncloud/android/ui/preview/PreviewImageActivity.java  (David A. Velasco 2013-02-28 17:09:00 +0100 394)     public boolean onTouch(View v, MotionEvent event) {
31827bce src/com/owncloud/android/ui/preview/PreviewImageActivity.java  (David A. Velasco 2013-02-28 17:52:15 +0100 395)         if (event.getAction() == MotionEvent.ACTION_UP) {
31827bce src/com/owncloud/android/ui/preview/PreviewImageActivity.java  (David A. Velasco 2013-02-28 17:52:15 +0100 396)            toggleFullScreen();
638b7767 src/com/owncloud/android/ui/preview/PreviewImageActivity.java  (David A. Velasco 2013-02-28 17:09:00 +0100 397)         }
638b7767 src/com/owncloud/android/ui/preview/PreviewImageActivity.java  (David A. Velasco 2013-02-28 17:09:00 +0100 398)         return true;
638b7767 src/com/owncloud/android/ui/preview/PreviewImageActivity.java  (David A. Velasco 2013-02-28 17:09:00 +0100 399)     }
638b7767 src/com/owncloud/android/ui/preview/PreviewImageActivity.java  (David A. Velasco 2013-02-28 17:09:00 +0100 400) 
638b7767 src/com/owncloud/android/ui/preview/PreviewImageActivity.java  (David A. Velasco 2013-02-28 17:09:00 +0100 401)     
638b7767 src/com/owncloud/android/ui/preview/PreviewImageActivity.java  (David A. Velasco 2013-02-28 17:09:00 +0100 402)     private void toggleFullScreen() {
638b7767 src/com/owncloud/android/ui/preview/PreviewImageActivity.java  (David A. Velasco 2013-02-28 17:09:00 +0100 403)         ActionBar actionBar = getSupportActionBar();
638b7767 src/com/owncloud/android/ui/preview/PreviewImageActivity.java  (David A. Velasco 2013-02-28 17:09:00 +0100 404)         if (mFullScreen) {
638b7767 src/com/owncloud/android/ui/preview/PreviewImageActivity.java  (David A. Velasco 2013-02-28 17:09:00 +0100 405)             actionBar.show();
638b7767 src/com/owncloud/android/ui/preview/PreviewImageActivity.java  (David A. Velasco 2013-02-28 17:09:00 +0100 406)             
638b7767 src/com/owncloud/android/ui/preview/PreviewImageActivity.java  (David A. Velasco 2013-02-28 17:09:00 +0100 407)         } else {
638b7767 src/com/owncloud/android/ui/preview/PreviewImageActivity.java  (David A. Velasco 2013-02-28 17:09:00 +0100 408)             actionBar.hide();
638b7767 src/com/owncloud/android/ui/preview/PreviewImageActivity.java  (David A. Velasco 2013-02-28 17:09:00 +0100 409)             
638b7767 src/com/owncloud/android/ui/preview/PreviewImageActivity.java  (David A. Velasco 2013-02-28 17:09:00 +0100 410)         }
638b7767 src/com/owncloud/android/ui/preview/PreviewImageActivity.java  (David A. Velasco 2013-02-28 17:09:00 +0100 411)         mFullScreen = !mFullScreen;
638b7767 src/com/owncloud/android/ui/preview/PreviewImageActivity.java  (David A. Velasco 2013-02-28 17:09:00 +0100 412)     }
edb380e0 src/com/owncloud/android/ui/preview/PreviewImageActivity.java  (David A. Velasco 2013-05-27 15:01:54 +0200 413) 
edb380e0 src/com/owncloud/android/ui/preview/PreviewImageActivity.java  (David A. Velasco 2013-05-27 15:01:54 +0200 414)     @Override
c70f3333 src/com/owncloud/android/ui/preview/PreviewImageActivity.java  (David A. Velasco 2013-06-04 14:30:29 +0200 415)     protected void onAccountSet(boolean stateWasRecovered) {
f9babe7b src/com/owncloud/android/ui/preview/PreviewImageActivity.java  (David A. Velasco 2013-06-14 12:53:45 +0200 416)         if (getAccount() != null) {
f9babe7b src/com/owncloud/android/ui/preview/PreviewImageActivity.java  (David A. Velasco 2013-06-14 12:53:45 +0200 417)             OCFile file = getFile();
f9babe7b src/com/owncloud/android/ui/preview/PreviewImageActivity.java  (David A. Velasco 2013-06-14 12:53:45 +0200 418)             /// Validate handled file  (first image to preview)
f9babe7b src/com/owncloud/android/ui/preview/PreviewImageActivity.java  (David A. Velasco 2013-06-14 12:53:45 +0200 419)             if (file == null) {
f9babe7b src/com/owncloud/android/ui/preview/PreviewImageActivity.java  (David A. Velasco 2013-06-14 12:53:45 +0200 420)                 throw new IllegalStateException("Instanced with a NULL OCFile");
f9babe7b src/com/owncloud/android/ui/preview/PreviewImageActivity.java  (David A. Velasco 2013-06-14 12:53:45 +0200 421)             }
f9babe7b src/com/owncloud/android/ui/preview/PreviewImageActivity.java  (David A. Velasco 2013-06-14 12:53:45 +0200 422)             if (!file.isImage()) {
f9babe7b src/com/owncloud/android/ui/preview/PreviewImageActivity.java  (David A. Velasco 2013-06-14 12:53:45 +0200 423)                 throw new IllegalArgumentException("Non-image file passed as argument");
f9babe7b src/com/owncloud/android/ui/preview/PreviewImageActivity.java  (David A. Velasco 2013-06-14 12:53:45 +0200 424)             }
f9babe7b src/com/owncloud/android/ui/preview/PreviewImageActivity.java  (David A. Velasco 2013-06-14 12:53:45 +0200 425)             mStorageManager = new FileDataStorageManager(getAccount(), getContentResolver());
f9babe7b src/com/owncloud/android/ui/preview/PreviewImageActivity.java  (David A. Velasco 2013-06-14 12:53:45 +0200 426)             file = mStorageManager.getFileById(file.getFileId()); 
f9babe7b src/com/owncloud/android/ui/preview/PreviewImageActivity.java  (David A. Velasco 2013-06-14 12:53:45 +0200 427)             if (file != null) {
f9babe7b src/com/owncloud/android/ui/preview/PreviewImageActivity.java  (David A. Velasco 2013-06-14 12:53:45 +0200 428)                 /// Refresh the activity according to the Account and OCFile set
f9babe7b src/com/owncloud/android/ui/preview/PreviewImageActivity.java  (David A. Velasco 2013-06-14 12:53:45 +0200 429)                 setFile(file);  // reset after getting it fresh from mStorageManager
f9babe7b src/com/owncloud/android/ui/preview/PreviewImageActivity.java  (David A. Velasco 2013-06-14 12:53:45 +0200 430)                 getSupportActionBar().setTitle(getFile().getFileName());
f9babe7b src/com/owncloud/android/ui/preview/PreviewImageActivity.java  (David A. Velasco 2013-06-14 12:53:45 +0200 431)                 //if (!stateWasRecovered) {
f9babe7b src/com/owncloud/android/ui/preview/PreviewImageActivity.java  (David A. Velasco 2013-06-14 12:53:45 +0200 432)                     initViewPager();
f9babe7b src/com/owncloud/android/ui/preview/PreviewImageActivity.java  (David A. Velasco 2013-06-14 12:53:45 +0200 433)                 //}
f9babe7b src/com/owncloud/android/ui/preview/PreviewImageActivity.java  (David A. Velasco 2013-06-14 12:53:45 +0200 434) 
f9babe7b src/com/owncloud/android/ui/preview/PreviewImageActivity.java  (David A. Velasco 2013-06-14 12:53:45 +0200 435)             } else {
f9babe7b src/com/owncloud/android/ui/preview/PreviewImageActivity.java  (David A. Velasco 2013-06-14 12:53:45 +0200 436)                 // handled file not in the current Account
f9babe7b src/com/owncloud/android/ui/preview/PreviewImageActivity.java  (David A. Velasco 2013-06-14 12:53:45 +0200 437)                 finish();
f9babe7b src/com/owncloud/android/ui/preview/PreviewImageActivity.java  (David A. Velasco 2013-06-14 12:53:45 +0200 438)             }
f9babe7b src/com/owncloud/android/ui/preview/PreviewImageActivity.java  (David A. Velasco 2013-06-14 12:53:45 +0200 439)             
f9babe7b src/com/owncloud/android/ui/preview/PreviewImageActivity.java  (David A. Velasco 2013-06-14 12:53:45 +0200 440)         } else {
f9babe7b src/com/owncloud/android/ui/preview/PreviewImageActivity.java  (David A. Velasco 2013-06-14 12:53:45 +0200 441)             Log_OC.wtf(TAG, "onAccountChanged was called with NULL account associated!");
f9babe7b src/com/owncloud/android/ui/preview/PreviewImageActivity.java  (David A. Velasco 2013-06-14 12:53:45 +0200 442)         }
edb380e0 src/com/owncloud/android/ui/preview/PreviewImageActivity.java  (David A. Velasco 2013-05-27 15:01:54 +0200 443)     }
86cec34b src/com/owncloud/android/ui/preview/PreviewImageActivity.java  (David A. Velasco 2013-02-27 14:08:58 +0100 444)     
86cec34b src/com/owncloud/android/ui/preview/PreviewImageActivity.java  (David A. Velasco 2013-02-27 14:08:58 +0100 445)     
3d272eb7 src/com/owncloud/android/ui/activity/PreviewImageActivity.java (David A. Velasco 2013-02-25 12:24:14 +0100 446) }
