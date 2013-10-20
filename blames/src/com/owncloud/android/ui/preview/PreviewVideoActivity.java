6103446a src/com/owncloud/android/ui/activity/VideoActivity.java        (David A. Velasco 2013-02-11 11:33:50 +0100   1) /* ownCloud Android client application
6103446a src/com/owncloud/android/ui/activity/VideoActivity.java        (David A. Velasco 2013-02-11 11:33:50 +0100   2)  *   Copyright (C) 2012-2013 ownCloud Inc.
6103446a src/com/owncloud/android/ui/activity/VideoActivity.java        (David A. Velasco 2013-02-11 11:33:50 +0100   3)  *
6103446a src/com/owncloud/android/ui/activity/VideoActivity.java        (David A. Velasco 2013-02-11 11:33:50 +0100   4)  *   This program is free software: you can redistribute it and/or modify
bdc0332c src/com/owncloud/android/ui/preview/PreviewVideoActivity.java  (David A. Velasco 2013-04-17 12:26:13 +0200   5)  *   it under the terms of the GNU General Public License version 2,
bdc0332c src/com/owncloud/android/ui/preview/PreviewVideoActivity.java  (David A. Velasco 2013-04-17 12:26:13 +0200   6)  *   as published by the Free Software Foundation.
6103446a src/com/owncloud/android/ui/activity/VideoActivity.java        (David A. Velasco 2013-02-11 11:33:50 +0100   7)  *
6103446a src/com/owncloud/android/ui/activity/VideoActivity.java        (David A. Velasco 2013-02-11 11:33:50 +0100   8)  *   This program is distributed in the hope that it will be useful,
6103446a src/com/owncloud/android/ui/activity/VideoActivity.java        (David A. Velasco 2013-02-11 11:33:50 +0100   9)  *   but WITHOUT ANY WARRANTY; without even the implied warranty of
6103446a src/com/owncloud/android/ui/activity/VideoActivity.java        (David A. Velasco 2013-02-11 11:33:50 +0100  10)  *   MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
6103446a src/com/owncloud/android/ui/activity/VideoActivity.java        (David A. Velasco 2013-02-11 11:33:50 +0100  11)  *   GNU General Public License for more details.
6103446a src/com/owncloud/android/ui/activity/VideoActivity.java        (David A. Velasco 2013-02-11 11:33:50 +0100  12)  *
6103446a src/com/owncloud/android/ui/activity/VideoActivity.java        (David A. Velasco 2013-02-11 11:33:50 +0100  13)  *   You should have received a copy of the GNU General Public License
6103446a src/com/owncloud/android/ui/activity/VideoActivity.java        (David A. Velasco 2013-02-11 11:33:50 +0100  14)  *   along with this program.  If not, see <http://www.gnu.org/licenses/>.
6103446a src/com/owncloud/android/ui/activity/VideoActivity.java        (David A. Velasco 2013-02-11 11:33:50 +0100  15)  *
6103446a src/com/owncloud/android/ui/activity/VideoActivity.java        (David A. Velasco 2013-02-11 11:33:50 +0100  16)  */
6103446a src/com/owncloud/android/ui/activity/VideoActivity.java        (David A. Velasco 2013-02-11 11:33:50 +0100  17) 
bc5c3f5f src/com/owncloud/android/ui/preview/PreviewVideoActivity.java  (David A. Velasco 2013-03-14 16:55:57 +0100  18) package com.owncloud.android.ui.preview;
586793a2 src/com/owncloud/android/ui/activity/VideoActivity.java        (David A. Velasco 2013-02-07 11:09:28 +0100  19) 
6103446a src/com/owncloud/android/ui/activity/VideoActivity.java        (David A. Velasco 2013-02-11 11:33:50 +0100  20) import android.accounts.Account;
6103446a src/com/owncloud/android/ui/activity/VideoActivity.java        (David A. Velasco 2013-02-11 11:33:50 +0100  21) import android.app.AlertDialog;
6103446a src/com/owncloud/android/ui/activity/VideoActivity.java        (David A. Velasco 2013-02-11 11:33:50 +0100  22) import android.content.DialogInterface;
6103446a src/com/owncloud/android/ui/activity/VideoActivity.java        (David A. Velasco 2013-02-11 11:33:50 +0100  23) import android.content.Intent;
586793a2 src/com/owncloud/android/ui/activity/VideoActivity.java        (David A. Velasco 2013-02-07 11:09:28 +0100  24) import android.media.MediaPlayer;
586793a2 src/com/owncloud/android/ui/activity/VideoActivity.java        (David A. Velasco 2013-02-07 11:09:28 +0100  25) import android.media.MediaPlayer.OnCompletionListener;
6103446a src/com/owncloud/android/ui/activity/VideoActivity.java        (David A. Velasco 2013-02-11 11:33:50 +0100  26) import android.media.MediaPlayer.OnErrorListener;
586793a2 src/com/owncloud/android/ui/activity/VideoActivity.java        (David A. Velasco 2013-02-07 11:09:28 +0100  27) import android.media.MediaPlayer.OnPreparedListener;
6103446a src/com/owncloud/android/ui/activity/VideoActivity.java        (David A. Velasco 2013-02-11 11:33:50 +0100  28) import android.net.Uri;
586793a2 src/com/owncloud/android/ui/activity/VideoActivity.java        (David A. Velasco 2013-02-07 11:09:28 +0100  29) import android.os.Bundle;
6103446a src/com/owncloud/android/ui/activity/VideoActivity.java        (David A. Velasco 2013-02-11 11:33:50 +0100  30) import android.widget.MediaController;
586793a2 src/com/owncloud/android/ui/activity/VideoActivity.java        (David A. Velasco 2013-02-07 11:09:28 +0100  31) import android.widget.VideoView;
586793a2 src/com/owncloud/android/ui/activity/VideoActivity.java        (David A. Velasco 2013-02-07 11:09:28 +0100  32) 
2946d8dd src/com/owncloud/android/ui/preview/PreviewVideoActivity.java  (David A. Velasco 2013-04-25 19:39:22 +0200  33) import com.owncloud.android.Log_OC;
586793a2 src/com/owncloud/android/ui/activity/VideoActivity.java        (David A. Velasco 2013-02-07 11:09:28 +0100  34) import com.owncloud.android.R;
3ecabe3e src/com/owncloud/android/ui/preview/PreviewVideoActivity.java  (David A. Velasco 2013-06-14 16:59:38 +0200  35) import com.owncloud.android.datamodel.DataStorageManager;
3ecabe3e src/com/owncloud/android/ui/preview/PreviewVideoActivity.java  (David A. Velasco 2013-06-14 16:59:38 +0200  36) import com.owncloud.android.datamodel.FileDataStorageManager;
c4ac05de src/com/owncloud/android/ui/preview/PreviewVideoActivity.java  (David A. Velasco 2013-06-18 11:34:08 +0200  37) import com.owncloud.android.authentication.AccountUtils;
6ca1e170 src/com/owncloud/android/ui/preview/PreviewVideoActivity.java  (David A. Velasco 2013-06-18 14:04:51 +0200  38) import com.owncloud.android.authentication.AccountUtils.AccountNotFoundException;
6103446a src/com/owncloud/android/ui/activity/VideoActivity.java        (David A. Velasco 2013-02-11 11:33:50 +0100  39) import com.owncloud.android.datamodel.OCFile;
11c1ad8f src/com/owncloud/android/ui/activity/VideoActivity.java        (David A. Velasco 2013-02-12 11:26:38 +0100  40) import com.owncloud.android.media.MediaService;
3ecabe3e src/com/owncloud/android/ui/preview/PreviewVideoActivity.java  (David A. Velasco 2013-06-14 16:59:38 +0200  41) import com.owncloud.android.ui.activity.FileActivity;
6103446a src/com/owncloud/android/ui/activity/VideoActivity.java        (David A. Velasco 2013-02-11 11:33:50 +0100  42) 
6103446a src/com/owncloud/android/ui/activity/VideoActivity.java        (David A. Velasco 2013-02-11 11:33:50 +0100  43) /**
6103446a src/com/owncloud/android/ui/activity/VideoActivity.java        (David A. Velasco 2013-02-11 11:33:50 +0100  44)  *  Activity implementing a basic video player.
6103446a src/com/owncloud/android/ui/activity/VideoActivity.java        (David A. Velasco 2013-02-11 11:33:50 +0100  45)  * 
6103446a src/com/owncloud/android/ui/activity/VideoActivity.java        (David A. Velasco 2013-02-11 11:33:50 +0100  46)  *  Used as an utility to preview video files contained in an ownCloud account.
6103446a src/com/owncloud/android/ui/activity/VideoActivity.java        (David A. Velasco 2013-02-11 11:33:50 +0100  47)  *  
6103446a src/com/owncloud/android/ui/activity/VideoActivity.java        (David A. Velasco 2013-02-11 11:33:50 +0100  48)  *  Currently, it always plays in landscape mode, full screen. When the playback ends,
6103446a src/com/owncloud/android/ui/activity/VideoActivity.java        (David A. Velasco 2013-02-11 11:33:50 +0100  49)  *  the activity is finished. 
6103446a src/com/owncloud/android/ui/activity/VideoActivity.java        (David A. Velasco 2013-02-11 11:33:50 +0100  50)  *  
6103446a src/com/owncloud/android/ui/activity/VideoActivity.java        (David A. Velasco 2013-02-11 11:33:50 +0100  51)  *  @author David A. Velasco
6103446a src/com/owncloud/android/ui/activity/VideoActivity.java        (David A. Velasco 2013-02-11 11:33:50 +0100  52)  */
3ecabe3e src/com/owncloud/android/ui/preview/PreviewVideoActivity.java  (David A. Velasco 2013-06-14 16:59:38 +0200  53) public class PreviewVideoActivity extends FileActivity implements OnCompletionListener, OnPreparedListener, OnErrorListener {
6103446a src/com/owncloud/android/ui/activity/VideoActivity.java        (David A. Velasco 2013-02-11 11:33:50 +0100  54) 
bc5c3f5f src/com/owncloud/android/ui/preview/PreviewVideoActivity.java  (David A. Velasco 2013-03-14 16:55:57 +0100  55)     /** Key to receive a flag signaling if the video should be started immediately */
bc5c3f5f src/com/owncloud/android/ui/preview/PreviewVideoActivity.java  (David A. Velasco 2013-03-14 16:55:57 +0100  56)     public static final String EXTRA_AUTOPLAY = "AUTOPLAY";
bc5c3f5f src/com/owncloud/android/ui/preview/PreviewVideoActivity.java  (David A. Velasco 2013-03-14 16:55:57 +0100  57)     
bc5c3f5f src/com/owncloud/android/ui/preview/PreviewVideoActivity.java  (David A. Velasco 2013-03-14 16:55:57 +0100  58)     /** Key to receive the position of the playback where the video should be put at start */
bc5c3f5f src/com/owncloud/android/ui/preview/PreviewVideoActivity.java  (David A. Velasco 2013-03-14 16:55:57 +0100  59)     public static final String EXTRA_START_POSITION = "START_POSITION";
bc5c3f5f src/com/owncloud/android/ui/preview/PreviewVideoActivity.java  (David A. Velasco 2013-03-14 16:55:57 +0100  60)     
bc5c3f5f src/com/owncloud/android/ui/preview/PreviewVideoActivity.java  (David A. Velasco 2013-03-14 16:55:57 +0100  61)     private static final String TAG = PreviewVideoActivity.class.getSimpleName();
6103446a src/com/owncloud/android/ui/activity/VideoActivity.java        (David A. Velasco 2013-02-11 11:33:50 +0100  62) 
3ecabe3e src/com/owncloud/android/ui/preview/PreviewVideoActivity.java  (David A. Velasco 2013-06-14 16:59:38 +0200  63)     private DataStorageManager mStorageManager;
3ecabe3e src/com/owncloud/android/ui/preview/PreviewVideoActivity.java  (David A. Velasco 2013-06-14 16:59:38 +0200  64)     
bc5c3f5f src/com/owncloud/android/ui/preview/PreviewVideoActivity.java  (David A. Velasco 2013-03-14 16:55:57 +0100  65)     private int mSavedPlaybackPosition;         // in the unit time handled by MediaPlayer.getCurrentPosition()
bc5c3f5f src/com/owncloud/android/ui/preview/PreviewVideoActivity.java  (David A. Velasco 2013-03-14 16:55:57 +0100  66)     private boolean mAutoplay;                  // when 'true', the playback starts immediately with the activity
6103446a src/com/owncloud/android/ui/activity/VideoActivity.java        (David A. Velasco 2013-02-11 11:33:50 +0100  67)     private VideoView mVideoPlayer;             // view to play the file; both performs and show the playback
6103446a src/com/owncloud/android/ui/activity/VideoActivity.java        (David A. Velasco 2013-02-11 11:33:50 +0100  68)     private MediaController mMediaController;   // panel control used by the user to control the playback
6103446a src/com/owncloud/android/ui/activity/VideoActivity.java        (David A. Velasco 2013-02-11 11:33:50 +0100  69)           
6103446a src/com/owncloud/android/ui/activity/VideoActivity.java        (David A. Velasco 2013-02-11 11:33:50 +0100  70)     /** 
6103446a src/com/owncloud/android/ui/activity/VideoActivity.java        (David A. Velasco 2013-02-11 11:33:50 +0100  71)      *  Called when the activity is first created.
6103446a src/com/owncloud/android/ui/activity/VideoActivity.java        (David A. Velasco 2013-02-11 11:33:50 +0100  72)      *  
6103446a src/com/owncloud/android/ui/activity/VideoActivity.java        (David A. Velasco 2013-02-11 11:33:50 +0100  73)      *  Searches for an {@link OCFile} and ownCloud {@link Account} holding it in the starting {@link Intent}.
6103446a src/com/owncloud/android/ui/activity/VideoActivity.java        (David A. Velasco 2013-02-11 11:33:50 +0100  74)      *  
6103446a src/com/owncloud/android/ui/activity/VideoActivity.java        (David A. Velasco 2013-02-11 11:33:50 +0100  75)      *  The {@link Account} is unnecessary if the file is downloaded; else, the {@link Account} is used to 
6103446a src/com/owncloud/android/ui/activity/VideoActivity.java        (David A. Velasco 2013-02-11 11:33:50 +0100  76)      *  try to stream the remote file - TODO get the streaming works
6103446a src/com/owncloud/android/ui/activity/VideoActivity.java        (David A. Velasco 2013-02-11 11:33:50 +0100  77)      * 
6103446a src/com/owncloud/android/ui/activity/VideoActivity.java        (David A. Velasco 2013-02-11 11:33:50 +0100  78)      *  {@inheritDoc}
6103446a src/com/owncloud/android/ui/activity/VideoActivity.java        (David A. Velasco 2013-02-11 11:33:50 +0100  79)      */
6103446a src/com/owncloud/android/ui/activity/VideoActivity.java        (David A. Velasco 2013-02-11 11:33:50 +0100  80)     @Override
6103446a src/com/owncloud/android/ui/activity/VideoActivity.java        (David A. Velasco 2013-02-11 11:33:50 +0100  81)     public void onCreate(Bundle savedInstanceState) {
6103446a src/com/owncloud/android/ui/activity/VideoActivity.java        (David A. Velasco 2013-02-11 11:33:50 +0100  82)         super.onCreate(savedInstanceState);
2946d8dd src/com/owncloud/android/ui/preview/PreviewVideoActivity.java  (David A. Velasco 2013-04-25 19:39:22 +0200  83)         Log_OC.e(TAG, "ACTIVITY\t\tonCreate");
6103446a src/com/owncloud/android/ui/activity/VideoActivity.java        (David A. Velasco 2013-02-11 11:33:50 +0100  84)         
6103446a src/com/owncloud/android/ui/activity/VideoActivity.java        (David A. Velasco 2013-02-11 11:33:50 +0100  85)         setContentView(R.layout.video_layout);
6103446a src/com/owncloud/android/ui/activity/VideoActivity.java        (David A. Velasco 2013-02-11 11:33:50 +0100  86)     
1236edf9 src/com/owncloud/android/ui/preview/PreviewVideoActivity.java  (David A. Velasco 2013-03-21 15:49:10 +0100  87)         if (savedInstanceState == null) {
1236edf9 src/com/owncloud/android/ui/preview/PreviewVideoActivity.java  (David A. Velasco 2013-03-21 15:49:10 +0100  88)             Bundle extras = getIntent().getExtras();
1236edf9 src/com/owncloud/android/ui/preview/PreviewVideoActivity.java  (David A. Velasco 2013-03-21 15:49:10 +0100  89)             mSavedPlaybackPosition = extras.getInt(EXTRA_START_POSITION);
1236edf9 src/com/owncloud/android/ui/preview/PreviewVideoActivity.java  (David A. Velasco 2013-03-21 15:49:10 +0100  90)             mAutoplay = extras.getBoolean(EXTRA_AUTOPLAY);
1236edf9 src/com/owncloud/android/ui/preview/PreviewVideoActivity.java  (David A. Velasco 2013-03-21 15:49:10 +0100  91)             
1236edf9 src/com/owncloud/android/ui/preview/PreviewVideoActivity.java  (David A. Velasco 2013-03-21 15:49:10 +0100  92)         } else {
1236edf9 src/com/owncloud/android/ui/preview/PreviewVideoActivity.java  (David A. Velasco 2013-03-21 15:49:10 +0100  93)             mSavedPlaybackPosition = savedInstanceState.getInt(EXTRA_START_POSITION);
1236edf9 src/com/owncloud/android/ui/preview/PreviewVideoActivity.java  (David A. Velasco 2013-03-21 15:49:10 +0100  94)             mAutoplay = savedInstanceState.getBoolean(EXTRA_AUTOPLAY);
1236edf9 src/com/owncloud/android/ui/preview/PreviewVideoActivity.java  (David A. Velasco 2013-03-21 15:49:10 +0100  95)         }
6103446a src/com/owncloud/android/ui/activity/VideoActivity.java        (David A. Velasco 2013-02-11 11:33:50 +0100  96)           
6103446a src/com/owncloud/android/ui/activity/VideoActivity.java        (David A. Velasco 2013-02-11 11:33:50 +0100  97)         mVideoPlayer = (VideoView) findViewById(R.id.videoPlayer);
6103446a src/com/owncloud/android/ui/activity/VideoActivity.java        (David A. Velasco 2013-02-11 11:33:50 +0100  98) 
6103446a src/com/owncloud/android/ui/activity/VideoActivity.java        (David A. Velasco 2013-02-11 11:33:50 +0100  99)         // set listeners to get more contol on the playback
6103446a src/com/owncloud/android/ui/activity/VideoActivity.java        (David A. Velasco 2013-02-11 11:33:50 +0100 100)         mVideoPlayer.setOnPreparedListener(this);
6103446a src/com/owncloud/android/ui/activity/VideoActivity.java        (David A. Velasco 2013-02-11 11:33:50 +0100 101)         mVideoPlayer.setOnCompletionListener(this);
6103446a src/com/owncloud/android/ui/activity/VideoActivity.java        (David A. Velasco 2013-02-11 11:33:50 +0100 102)         mVideoPlayer.setOnErrorListener(this);
6103446a src/com/owncloud/android/ui/activity/VideoActivity.java        (David A. Velasco 2013-02-11 11:33:50 +0100 103)           
6103446a src/com/owncloud/android/ui/activity/VideoActivity.java        (David A. Velasco 2013-02-11 11:33:50 +0100 104)         // keep the screen on while the playback is performed (prevents screen off by battery save)
6103446a src/com/owncloud/android/ui/activity/VideoActivity.java        (David A. Velasco 2013-02-11 11:33:50 +0100 105)         mVideoPlayer.setKeepScreenOn(true);
6103446a src/com/owncloud/android/ui/activity/VideoActivity.java        (David A. Velasco 2013-02-11 11:33:50 +0100 106)     }    
6103446a src/com/owncloud/android/ui/activity/VideoActivity.java        (David A. Velasco 2013-02-11 11:33:50 +0100 107)     
6103446a src/com/owncloud/android/ui/activity/VideoActivity.java        (David A. Velasco 2013-02-11 11:33:50 +0100 108)     
1236edf9 src/com/owncloud/android/ui/preview/PreviewVideoActivity.java  (David A. Velasco 2013-03-21 15:49:10 +0100 109)     /**
1236edf9 src/com/owncloud/android/ui/preview/PreviewVideoActivity.java  (David A. Velasco 2013-03-21 15:49:10 +0100 110)      * {@inheritDoc}
1236edf9 src/com/owncloud/android/ui/preview/PreviewVideoActivity.java  (David A. Velasco 2013-03-21 15:49:10 +0100 111)      */
1236edf9 src/com/owncloud/android/ui/preview/PreviewVideoActivity.java  (David A. Velasco 2013-03-21 15:49:10 +0100 112)     @Override
1236edf9 src/com/owncloud/android/ui/preview/PreviewVideoActivity.java  (David A. Velasco 2013-03-21 15:49:10 +0100 113)     public void onSaveInstanceState(Bundle outState) {
1236edf9 src/com/owncloud/android/ui/preview/PreviewVideoActivity.java  (David A. Velasco 2013-03-21 15:49:10 +0100 114)         super.onSaveInstanceState(outState);
2946d8dd src/com/owncloud/android/ui/preview/PreviewVideoActivity.java  (David A. Velasco 2013-04-25 19:39:22 +0200 115)         Log_OC.e(TAG, "ACTIVITY\t\tonSaveInstanceState");
1236edf9 src/com/owncloud/android/ui/preview/PreviewVideoActivity.java  (David A. Velasco 2013-03-21 15:49:10 +0100 116)         outState.putInt(PreviewVideoActivity.EXTRA_START_POSITION, mVideoPlayer.getCurrentPosition());
1236edf9 src/com/owncloud/android/ui/preview/PreviewVideoActivity.java  (David A. Velasco 2013-03-21 15:49:10 +0100 117)         outState.putBoolean(PreviewVideoActivity.EXTRA_AUTOPLAY , mVideoPlayer.isPlaying());
1236edf9 src/com/owncloud/android/ui/preview/PreviewVideoActivity.java  (David A. Velasco 2013-03-21 15:49:10 +0100 118)     }
1236edf9 src/com/owncloud/android/ui/preview/PreviewVideoActivity.java  (David A. Velasco 2013-03-21 15:49:10 +0100 119) 
1236edf9 src/com/owncloud/android/ui/preview/PreviewVideoActivity.java  (David A. Velasco 2013-03-21 15:49:10 +0100 120)     
bc5c3f5f src/com/owncloud/android/ui/preview/PreviewVideoActivity.java  (David A. Velasco 2013-03-14 16:55:57 +0100 121)     @Override
bc5c3f5f src/com/owncloud/android/ui/preview/PreviewVideoActivity.java  (David A. Velasco 2013-03-14 16:55:57 +0100 122)     public void onBackPressed() {
2946d8dd src/com/owncloud/android/ui/preview/PreviewVideoActivity.java  (David A. Velasco 2013-04-25 19:39:22 +0200 123)         Log_OC.e(TAG, "ACTIVTIY\t\tonBackPressed");
bc5c3f5f src/com/owncloud/android/ui/preview/PreviewVideoActivity.java  (David A. Velasco 2013-03-14 16:55:57 +0100 124)         Intent i = new Intent();
bc5c3f5f src/com/owncloud/android/ui/preview/PreviewVideoActivity.java  (David A. Velasco 2013-03-14 16:55:57 +0100 125)         i.putExtra(EXTRA_AUTOPLAY, mVideoPlayer.isPlaying());
bc5c3f5f src/com/owncloud/android/ui/preview/PreviewVideoActivity.java  (David A. Velasco 2013-03-14 16:55:57 +0100 126)         i.putExtra(EXTRA_START_POSITION, mVideoPlayer.getCurrentPosition());
bc5c3f5f src/com/owncloud/android/ui/preview/PreviewVideoActivity.java  (David A. Velasco 2013-03-14 16:55:57 +0100 127)         setResult(RESULT_OK, i);
bc5c3f5f src/com/owncloud/android/ui/preview/PreviewVideoActivity.java  (David A. Velasco 2013-03-14 16:55:57 +0100 128)         super.onBackPressed();
bc5c3f5f src/com/owncloud/android/ui/preview/PreviewVideoActivity.java  (David A. Velasco 2013-03-14 16:55:57 +0100 129)     }
bc5c3f5f src/com/owncloud/android/ui/preview/PreviewVideoActivity.java  (David A. Velasco 2013-03-14 16:55:57 +0100 130) 
bc5c3f5f src/com/owncloud/android/ui/preview/PreviewVideoActivity.java  (David A. Velasco 2013-03-14 16:55:57 +0100 131)     
6103446a src/com/owncloud/android/ui/activity/VideoActivity.java        (David A. Velasco 2013-02-11 11:33:50 +0100 132)     /** 
6103446a src/com/owncloud/android/ui/activity/VideoActivity.java        (David A. Velasco 2013-02-11 11:33:50 +0100 133)      * Called when the file is ready to be played.
6103446a src/com/owncloud/android/ui/activity/VideoActivity.java        (David A. Velasco 2013-02-11 11:33:50 +0100 134)      * 
6103446a src/com/owncloud/android/ui/activity/VideoActivity.java        (David A. Velasco 2013-02-11 11:33:50 +0100 135)      * Just starts the playback.
6103446a src/com/owncloud/android/ui/activity/VideoActivity.java        (David A. Velasco 2013-02-11 11:33:50 +0100 136)      * 
6103446a src/com/owncloud/android/ui/activity/VideoActivity.java        (David A. Velasco 2013-02-11 11:33:50 +0100 137)      * @param   mp    {@link MediaPlayer} instance performing the playback.
6103446a src/com/owncloud/android/ui/activity/VideoActivity.java        (David A. Velasco 2013-02-11 11:33:50 +0100 138)      */
6103446a src/com/owncloud/android/ui/activity/VideoActivity.java        (David A. Velasco 2013-02-11 11:33:50 +0100 139)     @Override
fa801791 src/com/owncloud/android/ui/preview/PreviewVideoActivity.java  (David A. Velasco 2013-03-22 13:04:57 +0100 140)     public void onPrepared(MediaPlayer mp) {
2946d8dd src/com/owncloud/android/ui/preview/PreviewVideoActivity.java  (David A. Velasco 2013-04-25 19:39:22 +0200 141)         Log_OC.e(TAG, "ACTIVITY\t\tonPrepare");
bc5c3f5f src/com/owncloud/android/ui/preview/PreviewVideoActivity.java  (David A. Velasco 2013-03-14 16:55:57 +0100 142)         mVideoPlayer.seekTo(mSavedPlaybackPosition);
bc5c3f5f src/com/owncloud/android/ui/preview/PreviewVideoActivity.java  (David A. Velasco 2013-03-14 16:55:57 +0100 143)         if (mAutoplay) { 
bc5c3f5f src/com/owncloud/android/ui/preview/PreviewVideoActivity.java  (David A. Velasco 2013-03-14 16:55:57 +0100 144)             mVideoPlayer.start();
bc5c3f5f src/com/owncloud/android/ui/preview/PreviewVideoActivity.java  (David A. Velasco 2013-03-14 16:55:57 +0100 145)         }
11c1ad8f src/com/owncloud/android/ui/activity/VideoActivity.java        (David A. Velasco 2013-02-12 11:26:38 +0100 146)         mMediaController.show(5000);  
6103446a src/com/owncloud/android/ui/activity/VideoActivity.java        (David A. Velasco 2013-02-11 11:33:50 +0100 147)     }
6103446a src/com/owncloud/android/ui/activity/VideoActivity.java        (David A. Velasco 2013-02-11 11:33:50 +0100 148)     
6103446a src/com/owncloud/android/ui/activity/VideoActivity.java        (David A. Velasco 2013-02-11 11:33:50 +0100 149)     
6103446a src/com/owncloud/android/ui/activity/VideoActivity.java        (David A. Velasco 2013-02-11 11:33:50 +0100 150)     /**
6103446a src/com/owncloud/android/ui/activity/VideoActivity.java        (David A. Velasco 2013-02-11 11:33:50 +0100 151)      * Called when the file is finished playing.
6103446a src/com/owncloud/android/ui/activity/VideoActivity.java        (David A. Velasco 2013-02-11 11:33:50 +0100 152)      *  
bc5c3f5f src/com/owncloud/android/ui/preview/PreviewVideoActivity.java  (David A. Velasco 2013-03-14 16:55:57 +0100 153)      * Rewinds the video
6103446a src/com/owncloud/android/ui/activity/VideoActivity.java        (David A. Velasco 2013-02-11 11:33:50 +0100 154)      * 
6103446a src/com/owncloud/android/ui/activity/VideoActivity.java        (David A. Velasco 2013-02-11 11:33:50 +0100 155)      * @param   mp    {@link MediaPlayer} instance performing the playback.
6103446a src/com/owncloud/android/ui/activity/VideoActivity.java        (David A. Velasco 2013-02-11 11:33:50 +0100 156)      */
6103446a src/com/owncloud/android/ui/activity/VideoActivity.java        (David A. Velasco 2013-02-11 11:33:50 +0100 157)     @Override
6103446a src/com/owncloud/android/ui/activity/VideoActivity.java        (David A. Velasco 2013-02-11 11:33:50 +0100 158)     public void onCompletion(MediaPlayer  mp) {
bc5c3f5f src/com/owncloud/android/ui/preview/PreviewVideoActivity.java  (David A. Velasco 2013-03-14 16:55:57 +0100 159)         mVideoPlayer.seekTo(0);
6103446a src/com/owncloud/android/ui/activity/VideoActivity.java        (David A. Velasco 2013-02-11 11:33:50 +0100 160)     }
6103446a src/com/owncloud/android/ui/activity/VideoActivity.java        (David A. Velasco 2013-02-11 11:33:50 +0100 161)     
6103446a src/com/owncloud/android/ui/activity/VideoActivity.java        (David A. Velasco 2013-02-11 11:33:50 +0100 162)     
6103446a src/com/owncloud/android/ui/activity/VideoActivity.java        (David A. Velasco 2013-02-11 11:33:50 +0100 163)     /**
6103446a src/com/owncloud/android/ui/activity/VideoActivity.java        (David A. Velasco 2013-02-11 11:33:50 +0100 164)      * Called when an error in playback occurs.
6103446a src/com/owncloud/android/ui/activity/VideoActivity.java        (David A. Velasco 2013-02-11 11:33:50 +0100 165)      * 
6103446a src/com/owncloud/android/ui/activity/VideoActivity.java        (David A. Velasco 2013-02-11 11:33:50 +0100 166)      * @param   mp      {@link MediaPlayer} instance performing the playback.
6103446a src/com/owncloud/android/ui/activity/VideoActivity.java        (David A. Velasco 2013-02-11 11:33:50 +0100 167)      * @param   what    Type of error
6103446a src/com/owncloud/android/ui/activity/VideoActivity.java        (David A. Velasco 2013-02-11 11:33:50 +0100 168)      * @param   extra   Extra code specific to the error
6103446a src/com/owncloud/android/ui/activity/VideoActivity.java        (David A. Velasco 2013-02-11 11:33:50 +0100 169)      */
6103446a src/com/owncloud/android/ui/activity/VideoActivity.java        (David A. Velasco 2013-02-11 11:33:50 +0100 170)     @Override
6103446a src/com/owncloud/android/ui/activity/VideoActivity.java        (David A. Velasco 2013-02-11 11:33:50 +0100 171)     public boolean onError(MediaPlayer mp, int what, int extra) {
2946d8dd src/com/owncloud/android/ui/preview/PreviewVideoActivity.java  (David A. Velasco 2013-04-25 19:39:22 +0200 172)         Log_OC.e(TAG, "Error in video playback, what = " + what + ", extra = " + extra);
6103446a src/com/owncloud/android/ui/activity/VideoActivity.java        (David A. Velasco 2013-02-11 11:33:50 +0100 173)         
6103446a src/com/owncloud/android/ui/activity/VideoActivity.java        (David A. Velasco 2013-02-11 11:33:50 +0100 174)         if (mMediaController != null) {
6103446a src/com/owncloud/android/ui/activity/VideoActivity.java        (David A. Velasco 2013-02-11 11:33:50 +0100 175)             mMediaController.hide();
6103446a src/com/owncloud/android/ui/activity/VideoActivity.java        (David A. Velasco 2013-02-11 11:33:50 +0100 176)         }
6103446a src/com/owncloud/android/ui/activity/VideoActivity.java        (David A. Velasco 2013-02-11 11:33:50 +0100 177)         
6103446a src/com/owncloud/android/ui/activity/VideoActivity.java        (David A. Velasco 2013-02-11 11:33:50 +0100 178)         if (mVideoPlayer.getWindowToken() != null) {
94206519 src/com/owncloud/android/ui/activity/VideoActivity.java        (David A. Velasco 2013-02-12 12:07:42 +0100 179)             String message = MediaService.getMessageForMediaError(this, what, extra);
6103446a src/com/owncloud/android/ui/activity/VideoActivity.java        (David A. Velasco 2013-02-11 11:33:50 +0100 180)             new AlertDialog.Builder(this)
94206519 src/com/owncloud/android/ui/activity/VideoActivity.java        (David A. Velasco 2013-02-12 12:07:42 +0100 181)                     .setMessage(message)
6103446a src/com/owncloud/android/ui/activity/VideoActivity.java        (David A. Velasco 2013-02-11 11:33:50 +0100 182)                     .setPositiveButton(android.R.string.VideoView_error_button,
6103446a src/com/owncloud/android/ui/activity/VideoActivity.java        (David A. Velasco 2013-02-11 11:33:50 +0100 183)                             new DialogInterface.OnClickListener() {
6103446a src/com/owncloud/android/ui/activity/VideoActivity.java        (David A. Velasco 2013-02-11 11:33:50 +0100 184)                                 public void onClick(DialogInterface dialog, int whichButton) {
3d272eb7 src/com/owncloud/android/ui/activity/PreviewVideoActivity.java (David A. Velasco 2013-02-25 12:24:14 +0100 185)                                     PreviewVideoActivity.this.onCompletion(null);
6103446a src/com/owncloud/android/ui/activity/VideoActivity.java        (David A. Velasco 2013-02-11 11:33:50 +0100 186)                                 }
6103446a src/com/owncloud/android/ui/activity/VideoActivity.java        (David A. Velasco 2013-02-11 11:33:50 +0100 187)                             })
6103446a src/com/owncloud/android/ui/activity/VideoActivity.java        (David A. Velasco 2013-02-11 11:33:50 +0100 188)                     .setCancelable(false)
6103446a src/com/owncloud/android/ui/activity/VideoActivity.java        (David A. Velasco 2013-02-11 11:33:50 +0100 189)                     .show();
6103446a src/com/owncloud/android/ui/activity/VideoActivity.java        (David A. Velasco 2013-02-11 11:33:50 +0100 190)         }
6103446a src/com/owncloud/android/ui/activity/VideoActivity.java        (David A. Velasco 2013-02-11 11:33:50 +0100 191)         return true;
6103446a src/com/owncloud/android/ui/activity/VideoActivity.java        (David A. Velasco 2013-02-11 11:33:50 +0100 192)     }
6103446a src/com/owncloud/android/ui/activity/VideoActivity.java        (David A. Velasco 2013-02-11 11:33:50 +0100 193)     
6103446a src/com/owncloud/android/ui/activity/VideoActivity.java        (David A. Velasco 2013-02-11 11:33:50 +0100 194)     
6103446a src/com/owncloud/android/ui/activity/VideoActivity.java        (David A. Velasco 2013-02-11 11:33:50 +0100 195)     @Override
3ecabe3e src/com/owncloud/android/ui/preview/PreviewVideoActivity.java  (David A. Velasco 2013-06-14 16:59:38 +0200 196)     protected void onAccountSet(boolean stateWasRecovered) {
3ecabe3e src/com/owncloud/android/ui/preview/PreviewVideoActivity.java  (David A. Velasco 2013-06-14 16:59:38 +0200 197)         if (getAccount() != null) {
3ecabe3e src/com/owncloud/android/ui/preview/PreviewVideoActivity.java  (David A. Velasco 2013-06-14 16:59:38 +0200 198)             OCFile file = getFile();
3ecabe3e src/com/owncloud/android/ui/preview/PreviewVideoActivity.java  (David A. Velasco 2013-06-14 16:59:38 +0200 199)             /// Validate handled file  (first image to preview)
3ecabe3e src/com/owncloud/android/ui/preview/PreviewVideoActivity.java  (David A. Velasco 2013-06-14 16:59:38 +0200 200)             if (file == null) {
3ecabe3e src/com/owncloud/android/ui/preview/PreviewVideoActivity.java  (David A. Velasco 2013-06-14 16:59:38 +0200 201)                 throw new IllegalStateException("Instanced with a NULL OCFile");
3ecabe3e src/com/owncloud/android/ui/preview/PreviewVideoActivity.java  (David A. Velasco 2013-06-14 16:59:38 +0200 202)             }
3ecabe3e src/com/owncloud/android/ui/preview/PreviewVideoActivity.java  (David A. Velasco 2013-06-14 16:59:38 +0200 203)             if (!file.isVideo()) {
3ecabe3e src/com/owncloud/android/ui/preview/PreviewVideoActivity.java  (David A. Velasco 2013-06-14 16:59:38 +0200 204)                 throw new IllegalArgumentException("Non-video file passed as argument");
3ecabe3e src/com/owncloud/android/ui/preview/PreviewVideoActivity.java  (David A. Velasco 2013-06-14 16:59:38 +0200 205)             }
3ecabe3e src/com/owncloud/android/ui/preview/PreviewVideoActivity.java  (David A. Velasco 2013-06-14 16:59:38 +0200 206)             mStorageManager = new FileDataStorageManager(getAccount(), getContentResolver());
3ecabe3e src/com/owncloud/android/ui/preview/PreviewVideoActivity.java  (David A. Velasco 2013-06-14 16:59:38 +0200 207)             file = mStorageManager.getFileById(file.getFileId()); 
3ecabe3e src/com/owncloud/android/ui/preview/PreviewVideoActivity.java  (David A. Velasco 2013-06-14 16:59:38 +0200 208)             if (file != null) {
3ecabe3e src/com/owncloud/android/ui/preview/PreviewVideoActivity.java  (David A. Velasco 2013-06-14 16:59:38 +0200 209)                 if (file.isDown()) {
3ecabe3e src/com/owncloud/android/ui/preview/PreviewVideoActivity.java  (David A. Velasco 2013-06-14 16:59:38 +0200 210)                     mVideoPlayer.setVideoPath(file.getStoragePath());
3ecabe3e src/com/owncloud/android/ui/preview/PreviewVideoActivity.java  (David A. Velasco 2013-06-14 16:59:38 +0200 211)                     
3ecabe3e src/com/owncloud/android/ui/preview/PreviewVideoActivity.java  (David A. Velasco 2013-06-14 16:59:38 +0200 212)                 } else {
5b792b04 src/com/owncloud/android/ui/preview/PreviewVideoActivity.java  (David A. Velasco 2013-06-28 11:58:43 +0200 213)                     // not working yet
5b792b04 src/com/owncloud/android/ui/preview/PreviewVideoActivity.java  (David A. Velasco 2013-06-28 11:58:43 +0200 214)                     String url;
5b792b04 src/com/owncloud/android/ui/preview/PreviewVideoActivity.java  (David A. Velasco 2013-06-28 11:58:43 +0200 215)                     try {
5b792b04 src/com/owncloud/android/ui/preview/PreviewVideoActivity.java  (David A. Velasco 2013-06-28 11:58:43 +0200 216)                         url = AccountUtils.constructFullURLForAccount(this, getAccount()) + file.getRemotePath();
5b792b04 src/com/owncloud/android/ui/preview/PreviewVideoActivity.java  (David A. Velasco 2013-06-28 11:58:43 +0200 217)                         mVideoPlayer.setVideoURI(Uri.parse(url));
5b792b04 src/com/owncloud/android/ui/preview/PreviewVideoActivity.java  (David A. Velasco 2013-06-28 11:58:43 +0200 218)                     } catch (AccountNotFoundException e) {
5b792b04 src/com/owncloud/android/ui/preview/PreviewVideoActivity.java  (David A. Velasco 2013-06-28 11:58:43 +0200 219)                         onError(null, MediaService.OC_MEDIA_ERROR, R.string.media_err_no_account);
5b792b04 src/com/owncloud/android/ui/preview/PreviewVideoActivity.java  (David A. Velasco 2013-06-28 11:58:43 +0200 220)                     }
3ecabe3e src/com/owncloud/android/ui/preview/PreviewVideoActivity.java  (David A. Velasco 2013-06-14 16:59:38 +0200 221)                 }
3ecabe3e src/com/owncloud/android/ui/preview/PreviewVideoActivity.java  (David A. Velasco 2013-06-14 16:59:38 +0200 222)                 
3ecabe3e src/com/owncloud/android/ui/preview/PreviewVideoActivity.java  (David A. Velasco 2013-06-14 16:59:38 +0200 223)                 // create and prepare control panel for the user
3ecabe3e src/com/owncloud/android/ui/preview/PreviewVideoActivity.java  (David A. Velasco 2013-06-14 16:59:38 +0200 224)                 mMediaController = new MediaController(this);
3ecabe3e src/com/owncloud/android/ui/preview/PreviewVideoActivity.java  (David A. Velasco 2013-06-14 16:59:38 +0200 225)                 mMediaController.setMediaPlayer(mVideoPlayer);
3ecabe3e src/com/owncloud/android/ui/preview/PreviewVideoActivity.java  (David A. Velasco 2013-06-14 16:59:38 +0200 226)                 mMediaController.setAnchorView(mVideoPlayer);
3ecabe3e src/com/owncloud/android/ui/preview/PreviewVideoActivity.java  (David A. Velasco 2013-06-14 16:59:38 +0200 227)                 mVideoPlayer.setMediaController(mMediaController);
3ecabe3e src/com/owncloud/android/ui/preview/PreviewVideoActivity.java  (David A. Velasco 2013-06-14 16:59:38 +0200 228)                 
1236edf9 src/com/owncloud/android/ui/preview/PreviewVideoActivity.java  (David A. Velasco 2013-03-21 15:49:10 +0100 229)             } else {
3ecabe3e src/com/owncloud/android/ui/preview/PreviewVideoActivity.java  (David A. Velasco 2013-06-14 16:59:38 +0200 230)                 finish();
1236edf9 src/com/owncloud/android/ui/preview/PreviewVideoActivity.java  (David A. Velasco 2013-03-21 15:49:10 +0100 231)             }
6103446a src/com/owncloud/android/ui/activity/VideoActivity.java        (David A. Velasco 2013-02-11 11:33:50 +0100 232)         } else {
3ecabe3e src/com/owncloud/android/ui/preview/PreviewVideoActivity.java  (David A. Velasco 2013-06-14 16:59:38 +0200 233)             Log_OC.wtf(TAG, "onAccountChanged was called with NULL account associated!");
3ecabe3e src/com/owncloud/android/ui/preview/PreviewVideoActivity.java  (David A. Velasco 2013-06-14 16:59:38 +0200 234)             finish();
3ecabe3e src/com/owncloud/android/ui/preview/PreviewVideoActivity.java  (David A. Velasco 2013-06-14 16:59:38 +0200 235)         }
3ecabe3e src/com/owncloud/android/ui/preview/PreviewVideoActivity.java  (David A. Velasco 2013-06-14 16:59:38 +0200 236)    }
6103446a src/com/owncloud/android/ui/activity/VideoActivity.java        (David A. Velasco 2013-02-11 11:33:50 +0100 237) 
586793a2 src/com/owncloud/android/ui/activity/VideoActivity.java        (David A. Velasco 2013-02-07 11:09:28 +0100 238) 
586793a2 src/com/owncloud/android/ui/activity/VideoActivity.java        (David A. Velasco 2013-02-07 11:09:28 +0100 239) }
