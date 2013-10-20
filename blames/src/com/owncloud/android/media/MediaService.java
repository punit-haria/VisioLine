7018ae7e (David A. Velasco 2013-02-05 12:20:47 +0100   1) /* ownCloud Android client application
bdc0332c (David A. Velasco 2013-04-17 12:26:13 +0200   2)  *   Copyright (C) 2012-2013 ownCloud Inc.
7018ae7e (David A. Velasco 2013-02-05 12:20:47 +0100   3)  *
7018ae7e (David A. Velasco 2013-02-05 12:20:47 +0100   4)  *   This program is free software: you can redistribute it and/or modify
bdc0332c (David A. Velasco 2013-04-17 12:26:13 +0200   5)  *   it under the terms of the GNU General Public License version 2,
bdc0332c (David A. Velasco 2013-04-17 12:26:13 +0200   6)  *   as published by the Free Software Foundation.
7018ae7e (David A. Velasco 2013-02-05 12:20:47 +0100   7)  *
7018ae7e (David A. Velasco 2013-02-05 12:20:47 +0100   8)  *   This program is distributed in the hope that it will be useful,
7018ae7e (David A. Velasco 2013-02-05 12:20:47 +0100   9)  *   but WITHOUT ANY WARRANTY; without even the implied warranty of
7018ae7e (David A. Velasco 2013-02-05 12:20:47 +0100  10)  *   MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
7018ae7e (David A. Velasco 2013-02-05 12:20:47 +0100  11)  *   GNU General Public License for more details.
7018ae7e (David A. Velasco 2013-02-05 12:20:47 +0100  12)  *
7018ae7e (David A. Velasco 2013-02-05 12:20:47 +0100  13)  *   You should have received a copy of the GNU General Public License
7018ae7e (David A. Velasco 2013-02-05 12:20:47 +0100  14)  *   along with this program.  If not, see <http://www.gnu.org/licenses/>.
7018ae7e (David A. Velasco 2013-02-05 12:20:47 +0100  15)  *
7018ae7e (David A. Velasco 2013-02-05 12:20:47 +0100  16)  */
7018ae7e (David A. Velasco 2013-02-05 12:20:47 +0100  17) 
7018ae7e (David A. Velasco 2013-02-05 12:20:47 +0100  18) package com.owncloud.android.media;
7018ae7e (David A. Velasco 2013-02-05 12:20:47 +0100  19) 
7018ae7e (David A. Velasco 2013-02-05 12:20:47 +0100  20) import android.accounts.Account;
7018ae7e (David A. Velasco 2013-02-05 12:20:47 +0100  21) import android.app.Notification;
7018ae7e (David A. Velasco 2013-02-05 12:20:47 +0100  22) import android.app.NotificationManager;
7018ae7e (David A. Velasco 2013-02-05 12:20:47 +0100  23) import android.app.PendingIntent;
7018ae7e (David A. Velasco 2013-02-05 12:20:47 +0100  24) import android.app.Service;
7018ae7e (David A. Velasco 2013-02-05 12:20:47 +0100  25) import android.content.Context;
7018ae7e (David A. Velasco 2013-02-05 12:20:47 +0100  26) import android.content.Intent;
7018ae7e (David A. Velasco 2013-02-05 12:20:47 +0100  27) import android.media.AudioManager;
7018ae7e (David A. Velasco 2013-02-05 12:20:47 +0100  28) import android.media.MediaPlayer;
7018ae7e (David A. Velasco 2013-02-05 12:20:47 +0100  29) import android.media.MediaPlayer.OnCompletionListener;
7018ae7e (David A. Velasco 2013-02-05 12:20:47 +0100  30) import android.media.MediaPlayer.OnErrorListener;
7018ae7e (David A. Velasco 2013-02-05 12:20:47 +0100  31) import android.media.MediaPlayer.OnPreparedListener;
7018ae7e (David A. Velasco 2013-02-05 12:20:47 +0100  32) import android.net.wifi.WifiManager;
7018ae7e (David A. Velasco 2013-02-05 12:20:47 +0100  33) import android.net.wifi.WifiManager.WifiLock;
7018ae7e (David A. Velasco 2013-02-05 12:20:47 +0100  34) import android.os.IBinder;
7018ae7e (David A. Velasco 2013-02-05 12:20:47 +0100  35) import android.os.PowerManager;
7018ae7e (David A. Velasco 2013-02-05 12:20:47 +0100  36) import android.widget.Toast;
7018ae7e (David A. Velasco 2013-02-05 12:20:47 +0100  37) 
7018ae7e (David A. Velasco 2013-02-05 12:20:47 +0100  38) import java.io.IOException;
7018ae7e (David A. Velasco 2013-02-05 12:20:47 +0100  39) 
274dfd29 (David A. Velasco 2013-04-15 13:17:47 +0200  40) import com.owncloud.android.Log_OC;
7018ae7e (David A. Velasco 2013-02-05 12:20:47 +0100  41) import com.owncloud.android.R;
7018ae7e (David A. Velasco 2013-02-05 12:20:47 +0100  42) import com.owncloud.android.datamodel.OCFile;
bc1fcf84 (David A. Velasco 2013-05-07 13:49:54 +0200  43) import com.owncloud.android.ui.activity.FileActivity;
fd396289 (David A. Velasco 2013-05-30 17:53:21 +0200  44) import com.owncloud.android.ui.activity.FileDisplayActivity;
7018ae7e (David A. Velasco 2013-02-05 12:20:47 +0100  45) 
7018ae7e (David A. Velasco 2013-02-05 12:20:47 +0100  46) /**
7018ae7e (David A. Velasco 2013-02-05 12:20:47 +0100  47)  * Service that handles media playback, both audio and video. 
7018ae7e (David A. Velasco 2013-02-05 12:20:47 +0100  48)  * 
7018ae7e (David A. Velasco 2013-02-05 12:20:47 +0100  49)  * Waits for Intents which signal the service to perform specific operations: Play, Pause,
7018ae7e (David A. Velasco 2013-02-05 12:20:47 +0100  50)  * Rewind, etc.
7018ae7e (David A. Velasco 2013-02-05 12:20:47 +0100  51)  * 
7018ae7e (David A. Velasco 2013-02-05 12:20:47 +0100  52)  * @author David A. Velasco
7018ae7e (David A. Velasco 2013-02-05 12:20:47 +0100  53)  */
7018ae7e (David A. Velasco 2013-02-05 12:20:47 +0100  54) public class MediaService extends Service implements OnCompletionListener, OnPreparedListener,
7018ae7e (David A. Velasco 2013-02-05 12:20:47 +0100  55)                 OnErrorListener, AudioManager.OnAudioFocusChangeListener {
7018ae7e (David A. Velasco 2013-02-05 12:20:47 +0100  56) 
7018ae7e (David A. Velasco 2013-02-05 12:20:47 +0100  57)     private static final String TAG = MediaService.class.getSimpleName();
7018ae7e (David A. Velasco 2013-02-05 12:20:47 +0100  58) 
7018ae7e (David A. Velasco 2013-02-05 12:20:47 +0100  59)     private static final String MY_PACKAGE = MediaService.class.getPackage() != null ? MediaService.class.getPackage().getName() : "com.owncloud.android.media";
7018ae7e (David A. Velasco 2013-02-05 12:20:47 +0100  60)     
7018ae7e (David A. Velasco 2013-02-05 12:20:47 +0100  61)     /// Intent actions that we are prepared to handle
3aacc7c0 (David A. Velasco 2013-03-07 17:07:58 +0100  62)     public static final String ACTION_PLAY_FILE = MY_PACKAGE + ".action.PLAY_FILE";
3aacc7c0 (David A. Velasco 2013-03-07 17:07:58 +0100  63)     public static final String ACTION_STOP_ALL = MY_PACKAGE + ".action.STOP_ALL";
7018ae7e (David A. Velasco 2013-02-05 12:20:47 +0100  64) 
7018ae7e (David A. Velasco 2013-02-05 12:20:47 +0100  65)     /// Keys to add extras to the action
7018ae7e (David A. Velasco 2013-02-05 12:20:47 +0100  66)     public static final String EXTRA_FILE = MY_PACKAGE + ".extra.FILE";
7018ae7e (David A. Velasco 2013-02-05 12:20:47 +0100  67)     public static final String EXTRA_ACCOUNT = MY_PACKAGE + ".extra.ACCOUNT";
fa801791 (David A. Velasco 2013-03-22 13:04:57 +0100  68)     public static String EXTRA_START_POSITION = MY_PACKAGE + ".extra.START_POSITION";
fa801791 (David A. Velasco 2013-03-22 13:04:57 +0100  69)     public static final String EXTRA_PLAY_ON_LOAD = MY_PACKAGE + ".extra.PLAY_ON_LOAD";
fa801791 (David A. Velasco 2013-03-22 13:04:57 +0100  70) 
11c1ad8f (David A. Velasco 2013-02-12 11:26:38 +0100  71) 
11c1ad8f (David A. Velasco 2013-02-12 11:26:38 +0100  72)     /** Error code for specific messages - see regular error codes at {@link MediaPlayer} */
11c1ad8f (David A. Velasco 2013-02-12 11:26:38 +0100  73)     public static final int OC_MEDIA_ERROR = 0;
11c1ad8f (David A. Velasco 2013-02-12 11:26:38 +0100  74) 
11c1ad8f (David A. Velasco 2013-02-12 11:26:38 +0100  75)     /** Time To keep the control panel visible when the user does not use it */
1236edf9 (David A. Velasco 2013-03-21 15:49:10 +0100  76)     public static final int MEDIA_CONTROL_SHORT_LIFE = 4000;
7101a042 (David A. Velasco 2013-02-14 19:21:09 +0100  77)     
7101a042 (David A. Velasco 2013-02-14 19:21:09 +0100  78)     /** Time To keep the control panel visible when the user does not use it */
7101a042 (David A. Velasco 2013-02-14 19:21:09 +0100  79)     public static final int MEDIA_CONTROL_PERMANENT = 0;
7018ae7e (David A. Velasco 2013-02-05 12:20:47 +0100  80)     
e4cd3fe5 (David A. Velasco 2013-02-11 12:46:50 +0100  81)     /** Volume to set when audio focus is lost and ducking is allowed */
7018ae7e (David A. Velasco 2013-02-05 12:20:47 +0100  82)     private static final float DUCK_VOLUME = 0.1f;
7018ae7e (David A. Velasco 2013-02-05 12:20:47 +0100  83) 
e4cd3fe5 (David A. Velasco 2013-02-11 12:46:50 +0100  84)     /** Media player instance */
7018ae7e (David A. Velasco 2013-02-05 12:20:47 +0100  85)     private MediaPlayer mPlayer = null;
7018ae7e (David A. Velasco 2013-02-05 12:20:47 +0100  86)     
e4cd3fe5 (David A. Velasco 2013-02-11 12:46:50 +0100  87)     /** Reference to the system AudioManager */
7018ae7e (David A. Velasco 2013-02-05 12:20:47 +0100  88)     private AudioManager mAudioManager = null;
7018ae7e (David A. Velasco 2013-02-05 12:20:47 +0100  89) 
7018ae7e (David A. Velasco 2013-02-05 12:20:47 +0100  90)     
e4cd3fe5 (David A. Velasco 2013-02-11 12:46:50 +0100  91)     /** Values to indicate the state of the service */
7018ae7e (David A. Velasco 2013-02-05 12:20:47 +0100  92)     enum State {
7018ae7e (David A. Velasco 2013-02-05 12:20:47 +0100  93)         STOPPED,
7018ae7e (David A. Velasco 2013-02-05 12:20:47 +0100  94)         PREPARING,      
7018ae7e (David A. Velasco 2013-02-05 12:20:47 +0100  95)         PLAYING,        
7018ae7e (David A. Velasco 2013-02-05 12:20:47 +0100  96)         PAUSED 
7018ae7e (David A. Velasco 2013-02-05 12:20:47 +0100  97)     };
7018ae7e (David A. Velasco 2013-02-05 12:20:47 +0100  98)     
7018ae7e (David A. Velasco 2013-02-05 12:20:47 +0100  99) 
e4cd3fe5 (David A. Velasco 2013-02-11 12:46:50 +0100 100)     /** Current state */
7018ae7e (David A. Velasco 2013-02-05 12:20:47 +0100 101)     private State mState = State.STOPPED;
7018ae7e (David A. Velasco 2013-02-05 12:20:47 +0100 102)     
e4cd3fe5 (David A. Velasco 2013-02-11 12:46:50 +0100 103)     /** Possible focus values */
7018ae7e (David A. Velasco 2013-02-05 12:20:47 +0100 104)     enum AudioFocus {
7018ae7e (David A. Velasco 2013-02-05 12:20:47 +0100 105)         NO_FOCUS,            
7018ae7e (David A. Velasco 2013-02-05 12:20:47 +0100 106)         NO_FOCUS_CAN_DUCK,   
7018ae7e (David A. Velasco 2013-02-05 12:20:47 +0100 107)         FOCUS           
7018ae7e (David A. Velasco 2013-02-05 12:20:47 +0100 108)     }
7018ae7e (David A. Velasco 2013-02-05 12:20:47 +0100 109)     
e4cd3fe5 (David A. Velasco 2013-02-11 12:46:50 +0100 110)     /** Current focus state */
7018ae7e (David A. Velasco 2013-02-05 12:20:47 +0100 111)     private AudioFocus mAudioFocus = AudioFocus.NO_FOCUS;
7018ae7e (David A. Velasco 2013-02-05 12:20:47 +0100 112)     
7018ae7e (David A. Velasco 2013-02-05 12:20:47 +0100 113) 
e4cd3fe5 (David A. Velasco 2013-02-11 12:46:50 +0100 114)     /** 'True' when the current song is streaming from the network */
7018ae7e (David A. Velasco 2013-02-05 12:20:47 +0100 115)     private boolean mIsStreaming = false;
7018ae7e (David A. Velasco 2013-02-05 12:20:47 +0100 116) 
e4cd3fe5 (David A. Velasco 2013-02-11 12:46:50 +0100 117)     /** Wifi lock kept to prevents the device from shutting off the radio when streaming a file. */
7018ae7e (David A. Velasco 2013-02-05 12:20:47 +0100 118)     private WifiLock mWifiLock;
e4cd3fe5 (David A. Velasco 2013-02-11 12:46:50 +0100 119)     
7018ae7e (David A. Velasco 2013-02-05 12:20:47 +0100 120)     private static final String MEDIA_WIFI_LOCK_TAG = MY_PACKAGE + ".WIFI_LOCK";
7018ae7e (David A. Velasco 2013-02-05 12:20:47 +0100 121) 
e4cd3fe5 (David A. Velasco 2013-02-11 12:46:50 +0100 122)     /** Notification to keep in the notification bar while a song is playing */
7018ae7e (David A. Velasco 2013-02-05 12:20:47 +0100 123)     private NotificationManager mNotificationManager;
7018ae7e (David A. Velasco 2013-02-05 12:20:47 +0100 124)     private Notification mNotification = null;
e4cd3fe5 (David A. Velasco 2013-02-11 12:46:50 +0100 125) 
e4cd3fe5 (David A. Velasco 2013-02-11 12:46:50 +0100 126)     /** File being played */
7018ae7e (David A. Velasco 2013-02-05 12:20:47 +0100 127)     private OCFile mFile;
e4cd3fe5 (David A. Velasco 2013-02-11 12:46:50 +0100 128)     
e4cd3fe5 (David A. Velasco 2013-02-11 12:46:50 +0100 129)     /** Account holding the file being played */
7018ae7e (David A. Velasco 2013-02-05 12:20:47 +0100 130)     private Account mAccount;
18bf35a8 (David A. Velasco 2013-02-06 14:14:55 +0100 131) 
fa801791 (David A. Velasco 2013-03-22 13:04:57 +0100 132)     /** Flag signaling if the audio should be played immediately when the file is prepared */ 
fa801791 (David A. Velasco 2013-03-22 13:04:57 +0100 133)     protected boolean mPlayOnPrepared;
fa801791 (David A. Velasco 2013-03-22 13:04:57 +0100 134) 
fa801791 (David A. Velasco 2013-03-22 13:04:57 +0100 135)     /** Position, in miliseconds, where the audio should be started */
fa801791 (David A. Velasco 2013-03-22 13:04:57 +0100 136)     private int mStartPosition;
fa801791 (David A. Velasco 2013-03-22 13:04:57 +0100 137)     
e4cd3fe5 (David A. Velasco 2013-02-11 12:46:50 +0100 138)     /** Interface to access the service through binding */
18bf35a8 (David A. Velasco 2013-02-06 14:14:55 +0100 139)     private IBinder mBinder;
11c1ad8f (David A. Velasco 2013-02-12 11:26:38 +0100 140) 
11c1ad8f (David A. Velasco 2013-02-12 11:26:38 +0100 141)     /** Control panel shown to the user to control the playback, to register through binding */
ce7f7fa4 (David A. Velasco 2013-03-12 14:36:31 +0100 142)     private MediaControlView mMediaController;
7018ae7e (David A. Velasco 2013-02-05 12:20:47 +0100 143)     
7018ae7e (David A. Velasco 2013-02-05 12:20:47 +0100 144) 
94206519 (David A. Velasco 2013-02-12 12:07:42 +0100 145)     
94206519 (David A. Velasco 2013-02-12 12:07:42 +0100 146)     /**
94206519 (David A. Velasco 2013-02-12 12:07:42 +0100 147)      * Helper method to get an error message suitable to show to users for errors occurred in media playback,
94206519 (David A. Velasco 2013-02-12 12:07:42 +0100 148)      * 
94206519 (David A. Velasco 2013-02-12 12:07:42 +0100 149)      * @param context   A context to access string resources.
94206519 (David A. Velasco 2013-02-12 12:07:42 +0100 150)      * @param what      See {@link MediaPlayer.OnErrorListener#onError(MediaPlayer, int, int)
94206519 (David A. Velasco 2013-02-12 12:07:42 +0100 151)      * @param extra     See {@link MediaPlayer.OnErrorListener#onError(MediaPlayer, int, int)
94206519 (David A. Velasco 2013-02-12 12:07:42 +0100 152)      * @return          Message suitable to users.
94206519 (David A. Velasco 2013-02-12 12:07:42 +0100 153)      */
94206519 (David A. Velasco 2013-02-12 12:07:42 +0100 154)     public static String getMessageForMediaError(Context context, int what, int extra) {
94206519 (David A. Velasco 2013-02-12 12:07:42 +0100 155)         int messageId;
94206519 (David A. Velasco 2013-02-12 12:07:42 +0100 156)         
94206519 (David A. Velasco 2013-02-12 12:07:42 +0100 157)         if (what == OC_MEDIA_ERROR) {
94206519 (David A. Velasco 2013-02-12 12:07:42 +0100 158)             messageId = extra;
94206519 (David A. Velasco 2013-02-12 12:07:42 +0100 159)                 
94206519 (David A. Velasco 2013-02-12 12:07:42 +0100 160)         } else if (extra == MediaPlayer.MEDIA_ERROR_UNSUPPORTED) {
94206519 (David A. Velasco 2013-02-12 12:07:42 +0100 161)             /*  Added in API level 17
94206519 (David A. Velasco 2013-02-12 12:07:42 +0100 162)                 Bitstream is conforming to the related coding standard or file spec, but the media framework does not support the feature.
94206519 (David A. Velasco 2013-02-12 12:07:42 +0100 163)                 Constant Value: -1010 (0xfffffc0e)
94206519 (David A. Velasco 2013-02-12 12:07:42 +0100 164)              */
94206519 (David A. Velasco 2013-02-12 12:07:42 +0100 165)             messageId = R.string.media_err_unsupported;
94206519 (David A. Velasco 2013-02-12 12:07:42 +0100 166) 
94206519 (David A. Velasco 2013-02-12 12:07:42 +0100 167)         } else if (extra == MediaPlayer.MEDIA_ERROR_IO) {
94206519 (David A. Velasco 2013-02-12 12:07:42 +0100 168)             /*  Added in API level 17
94206519 (David A. Velasco 2013-02-12 12:07:42 +0100 169)                 File or network related operation errors.
94206519 (David A. Velasco 2013-02-12 12:07:42 +0100 170)                 Constant Value: -1004 (0xfffffc14) 
94206519 (David A. Velasco 2013-02-12 12:07:42 +0100 171)              */
94206519 (David A. Velasco 2013-02-12 12:07:42 +0100 172)             messageId = R.string.media_err_io;
94206519 (David A. Velasco 2013-02-12 12:07:42 +0100 173)             
94206519 (David A. Velasco 2013-02-12 12:07:42 +0100 174)         } else if (extra == MediaPlayer.MEDIA_ERROR_MALFORMED) {
94206519 (David A. Velasco 2013-02-12 12:07:42 +0100 175)             /*  Added in API level 17
94206519 (David A. Velasco 2013-02-12 12:07:42 +0100 176)                 Bitstream is not conforming to the related coding standard or file spec.
94206519 (David A. Velasco 2013-02-12 12:07:42 +0100 177)                 Constant Value: -1007 (0xfffffc11) 
94206519 (David A. Velasco 2013-02-12 12:07:42 +0100 178)              */
94206519 (David A. Velasco 2013-02-12 12:07:42 +0100 179)             messageId = R.string.media_err_malformed;
94206519 (David A. Velasco 2013-02-12 12:07:42 +0100 180)             
94206519 (David A. Velasco 2013-02-12 12:07:42 +0100 181)         } else if (extra == MediaPlayer.MEDIA_ERROR_TIMED_OUT) {
94206519 (David A. Velasco 2013-02-12 12:07:42 +0100 182)             /*  Added in API level 17
94206519 (David A. Velasco 2013-02-12 12:07:42 +0100 183)                 Some operation takes too long to complete, usually more than 3-5 seconds.
94206519 (David A. Velasco 2013-02-12 12:07:42 +0100 184)                 Constant Value: -110 (0xffffff92)
94206519 (David A. Velasco 2013-02-12 12:07:42 +0100 185)             */
94206519 (David A. Velasco 2013-02-12 12:07:42 +0100 186)             messageId = R.string.media_err_timeout;
94206519 (David A. Velasco 2013-02-12 12:07:42 +0100 187) 
94206519 (David A. Velasco 2013-02-12 12:07:42 +0100 188)         } else if (what == MediaPlayer.MEDIA_ERROR_NOT_VALID_FOR_PROGRESSIVE_PLAYBACK) {
94206519 (David A. Velasco 2013-02-12 12:07:42 +0100 189)             /*  Added in API level 3
94206519 (David A. Velasco 2013-02-12 12:07:42 +0100 190)                 The video is streamed and its container is not valid for progressive playback i.e the video's index (e.g moov atom) is not at the start of the file.
94206519 (David A. Velasco 2013-02-12 12:07:42 +0100 191)                 Constant Value: 200 (0x000000c8)
94206519 (David A. Velasco 2013-02-12 12:07:42 +0100 192)             */
94206519 (David A. Velasco 2013-02-12 12:07:42 +0100 193)             messageId = R.string.media_err_invalid_progressive_playback;
94206519 (David A. Velasco 2013-02-12 12:07:42 +0100 194)                 
94206519 (David A. Velasco 2013-02-12 12:07:42 +0100 195)         } else {
94206519 (David A. Velasco 2013-02-12 12:07:42 +0100 196)             /*  MediaPlayer.MEDIA_ERROR_UNKNOWN
94206519 (David A. Velasco 2013-02-12 12:07:42 +0100 197)                 Added in API level 1
94206519 (David A. Velasco 2013-02-12 12:07:42 +0100 198)                 Unspecified media player error.
94206519 (David A. Velasco 2013-02-12 12:07:42 +0100 199)                 Constant Value: 1 (0x00000001)
94206519 (David A. Velasco 2013-02-12 12:07:42 +0100 200)             */
94206519 (David A. Velasco 2013-02-12 12:07:42 +0100 201)             /*  MediaPlayer.MEDIA_ERROR_SERVER_DIED)
94206519 (David A. Velasco 2013-02-12 12:07:42 +0100 202)                 Added in API level 1
94206519 (David A. Velasco 2013-02-12 12:07:42 +0100 203)                 Media server died. In this case, the application must release the MediaPlayer object and instantiate a new one.
94206519 (David A. Velasco 2013-02-12 12:07:42 +0100 204)                 Constant Value: 100 (0x00000064) 
94206519 (David A. Velasco 2013-02-12 12:07:42 +0100 205)              */
94206519 (David A. Velasco 2013-02-12 12:07:42 +0100 206)             messageId = R.string.media_err_unknown;
94206519 (David A. Velasco 2013-02-12 12:07:42 +0100 207)         }
94206519 (David A. Velasco 2013-02-12 12:07:42 +0100 208)         return context.getString(messageId);
94206519 (David A. Velasco 2013-02-12 12:07:42 +0100 209)     }
94206519 (David A. Velasco 2013-02-12 12:07:42 +0100 210) 
94206519 (David A. Velasco 2013-02-12 12:07:42 +0100 211) 
94206519 (David A. Velasco 2013-02-12 12:07:42 +0100 212)     
7018ae7e (David A. Velasco 2013-02-05 12:20:47 +0100 213)     /**
7018ae7e (David A. Velasco 2013-02-05 12:20:47 +0100 214)      * Initialize a service instance
7018ae7e (David A. Velasco 2013-02-05 12:20:47 +0100 215)      * 
7018ae7e (David A. Velasco 2013-02-05 12:20:47 +0100 216)      * {@inheritDoc}
7018ae7e (David A. Velasco 2013-02-05 12:20:47 +0100 217)      */
7018ae7e (David A. Velasco 2013-02-05 12:20:47 +0100 218)     @Override
7018ae7e (David A. Velasco 2013-02-05 12:20:47 +0100 219)     public void onCreate() {
274dfd29 (David A. Velasco 2013-04-15 13:17:47 +0200 220)         Log_OC.d(TAG, "Creating ownCloud media service");
7018ae7e (David A. Velasco 2013-02-05 12:20:47 +0100 221) 
7018ae7e (David A. Velasco 2013-02-05 12:20:47 +0100 222)         mWifiLock = ((WifiManager) getSystemService(Context.WIFI_SERVICE)).
7018ae7e (David A. Velasco 2013-02-05 12:20:47 +0100 223)                 createWifiLock(WifiManager.WIFI_MODE_FULL, MEDIA_WIFI_LOCK_TAG);
7018ae7e (David A. Velasco 2013-02-05 12:20:47 +0100 224) 
7018ae7e (David A. Velasco 2013-02-05 12:20:47 +0100 225)         mNotificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
7018ae7e (David A. Velasco 2013-02-05 12:20:47 +0100 226)         mAudioManager = (AudioManager) getSystemService(AUDIO_SERVICE);
18bf35a8 (David A. Velasco 2013-02-06 14:14:55 +0100 227)         mBinder = new MediaServiceBinder(this);
7018ae7e (David A. Velasco 2013-02-05 12:20:47 +0100 228)     }
7018ae7e (David A. Velasco 2013-02-05 12:20:47 +0100 229) 
7018ae7e (David A. Velasco 2013-02-05 12:20:47 +0100 230)     
7018ae7e (David A. Velasco 2013-02-05 12:20:47 +0100 231)     /**
7018ae7e (David A. Velasco 2013-02-05 12:20:47 +0100 232)      * Entry point for Intents requesting actions, sent here via startService.
7018ae7e (David A. Velasco 2013-02-05 12:20:47 +0100 233)      * 
e4cd3fe5 (David A. Velasco 2013-02-11 12:46:50 +0100 234)      * {@inheritDoc}
7018ae7e (David A. Velasco 2013-02-05 12:20:47 +0100 235)      */
7018ae7e (David A. Velasco 2013-02-05 12:20:47 +0100 236)     @Override
7018ae7e (David A. Velasco 2013-02-05 12:20:47 +0100 237)     public int onStartCommand(Intent intent, int flags, int startId) {
7018ae7e (David A. Velasco 2013-02-05 12:20:47 +0100 238)         String action = intent.getAction();
7018ae7e (David A. Velasco 2013-02-05 12:20:47 +0100 239)         if (action.equals(ACTION_PLAY_FILE)) { 
7018ae7e (David A. Velasco 2013-02-05 12:20:47 +0100 240)             processPlayFileRequest(intent);
3aacc7c0 (David A. Velasco 2013-03-07 17:07:58 +0100 241)             
3aacc7c0 (David A. Velasco 2013-03-07 17:07:58 +0100 242)         } else if (action.equals(ACTION_STOP_ALL)) {
3aacc7c0 (David A. Velasco 2013-03-07 17:07:58 +0100 243)             processStopRequest(true);
7018ae7e (David A. Velasco 2013-02-05 12:20:47 +0100 244)         }
7018ae7e (David A. Velasco 2013-02-05 12:20:47 +0100 245) 
7018ae7e (David A. Velasco 2013-02-05 12:20:47 +0100 246)         return START_NOT_STICKY; // don't want it to restart in case it's killed.
7018ae7e (David A. Velasco 2013-02-05 12:20:47 +0100 247)     }
7018ae7e (David A. Velasco 2013-02-05 12:20:47 +0100 248) 
7018ae7e (David A. Velasco 2013-02-05 12:20:47 +0100 249) 
7018ae7e (David A. Velasco 2013-02-05 12:20:47 +0100 250)     /**
7018ae7e (David A. Velasco 2013-02-05 12:20:47 +0100 251)      * Processes a request to play a media file received as a parameter
7018ae7e (David A. Velasco 2013-02-05 12:20:47 +0100 252)      * 
18bf35a8 (David A. Velasco 2013-02-06 14:14:55 +0100 253)      * TODO If a new request is received when a file is being prepared, it is ignored. Is this what we want? 
18bf35a8 (David A. Velasco 2013-02-06 14:14:55 +0100 254)      * 
7018ae7e (David A. Velasco 2013-02-05 12:20:47 +0100 255)      * @param intent    Intent received in the request with the data to identify the file to play. 
7018ae7e (David A. Velasco 2013-02-05 12:20:47 +0100 256)      */
7018ae7e (David A. Velasco 2013-02-05 12:20:47 +0100 257)     private void processPlayFileRequest(Intent intent) {
11c1ad8f (David A. Velasco 2013-02-12 11:26:38 +0100 258)         if (mState != State.PREPARING) {
7018ae7e (David A. Velasco 2013-02-05 12:20:47 +0100 259)             mFile = intent.getExtras().getParcelable(EXTRA_FILE);
7018ae7e (David A. Velasco 2013-02-05 12:20:47 +0100 260)             mAccount = intent.getExtras().getParcelable(EXTRA_ACCOUNT);
fa801791 (David A. Velasco 2013-03-22 13:04:57 +0100 261)             mPlayOnPrepared = intent.getExtras().getBoolean(EXTRA_PLAY_ON_LOAD, false);
fa801791 (David A. Velasco 2013-03-22 13:04:57 +0100 262)             mStartPosition = intent.getExtras().getInt(EXTRA_START_POSITION, 0);
7018ae7e (David A. Velasco 2013-02-05 12:20:47 +0100 263)             tryToGetAudioFocus();
7018ae7e (David A. Velasco 2013-02-05 12:20:47 +0100 264)             playMedia();
7018ae7e (David A. Velasco 2013-02-05 12:20:47 +0100 265)         }
7018ae7e (David A. Velasco 2013-02-05 12:20:47 +0100 266)     }
7018ae7e (David A. Velasco 2013-02-05 12:20:47 +0100 267) 
7018ae7e (David A. Velasco 2013-02-05 12:20:47 +0100 268)     
7018ae7e (David A. Velasco 2013-02-05 12:20:47 +0100 269)     /**
7018ae7e (David A. Velasco 2013-02-05 12:20:47 +0100 270)      * Processes a request to play a media file.
7018ae7e (David A. Velasco 2013-02-05 12:20:47 +0100 271)      */
18bf35a8 (David A. Velasco 2013-02-06 14:14:55 +0100 272)     protected void processPlayRequest() {
7018ae7e (David A. Velasco 2013-02-05 12:20:47 +0100 273)         // request audio focus
7018ae7e (David A. Velasco 2013-02-05 12:20:47 +0100 274)         tryToGetAudioFocus();
7018ae7e (David A. Velasco 2013-02-05 12:20:47 +0100 275) 
7018ae7e (David A. Velasco 2013-02-05 12:20:47 +0100 276)         // actually play the song
7018ae7e (David A. Velasco 2013-02-05 12:20:47 +0100 277)         if (mState == State.STOPPED) {
7018ae7e (David A. Velasco 2013-02-05 12:20:47 +0100 278)             // (re)start playback
7018ae7e (David A. Velasco 2013-02-05 12:20:47 +0100 279)             playMedia();
7018ae7e (David A. Velasco 2013-02-05 12:20:47 +0100 280)             
7018ae7e (David A. Velasco 2013-02-05 12:20:47 +0100 281)         } else if (mState == State.PAUSED) {
7018ae7e (David A. Velasco 2013-02-05 12:20:47 +0100 282)             // continue playback
7018ae7e (David A. Velasco 2013-02-05 12:20:47 +0100 283)             mState = State.PLAYING;
7018ae7e (David A. Velasco 2013-02-05 12:20:47 +0100 284)             setUpAsForeground(String.format(getString(R.string.media_state_playing), mFile.getFileName()));
7018ae7e (David A. Velasco 2013-02-05 12:20:47 +0100 285)             configAndStartMediaPlayer();
7018ae7e (David A. Velasco 2013-02-05 12:20:47 +0100 286)             
7018ae7e (David A. Velasco 2013-02-05 12:20:47 +0100 287)         }
7018ae7e (David A. Velasco 2013-02-05 12:20:47 +0100 288)     }
7018ae7e (David A. Velasco 2013-02-05 12:20:47 +0100 289) 
7018ae7e (David A. Velasco 2013-02-05 12:20:47 +0100 290)     
7018ae7e (David A. Velasco 2013-02-05 12:20:47 +0100 291)     /**
7018ae7e (David A. Velasco 2013-02-05 12:20:47 +0100 292)      * Makes sure the media player exists and has been reset. This will create the media player
bdc0332c (David A. Velasco 2013-04-17 12:26:13 +0200 293)      * if needed. reset the existing media player if one already exists.
7018ae7e (David A. Velasco 2013-02-05 12:20:47 +0100 294)      */
18bf35a8 (David A. Velasco 2013-02-06 14:14:55 +0100 295)     protected void createMediaPlayerIfNeeded() {
7018ae7e (David A. Velasco 2013-02-05 12:20:47 +0100 296)         if (mPlayer == null) {
7018ae7e (David A. Velasco 2013-02-05 12:20:47 +0100 297)             mPlayer = new MediaPlayer();
7018ae7e (David A. Velasco 2013-02-05 12:20:47 +0100 298) 
7018ae7e (David A. Velasco 2013-02-05 12:20:47 +0100 299)             // make sure the CPU won't go to sleep while media is playing
7018ae7e (David A. Velasco 2013-02-05 12:20:47 +0100 300)             mPlayer.setWakeMode(getApplicationContext(), PowerManager.PARTIAL_WAKE_LOCK);
7018ae7e (David A. Velasco 2013-02-05 12:20:47 +0100 301) 
7018ae7e (David A. Velasco 2013-02-05 12:20:47 +0100 302)             // the media player will notify the service when it's ready preparing, and when it's done playing
7018ae7e (David A. Velasco 2013-02-05 12:20:47 +0100 303)             mPlayer.setOnPreparedListener(this);
7018ae7e (David A. Velasco 2013-02-05 12:20:47 +0100 304)             mPlayer.setOnCompletionListener(this);
7018ae7e (David A. Velasco 2013-02-05 12:20:47 +0100 305)             mPlayer.setOnErrorListener(this);
7018ae7e (David A. Velasco 2013-02-05 12:20:47 +0100 306)             
7018ae7e (David A. Velasco 2013-02-05 12:20:47 +0100 307)         } else {
7018ae7e (David A. Velasco 2013-02-05 12:20:47 +0100 308)             mPlayer.reset();
7018ae7e (David A. Velasco 2013-02-05 12:20:47 +0100 309)         }
7018ae7e (David A. Velasco 2013-02-05 12:20:47 +0100 310)     }
7018ae7e (David A. Velasco 2013-02-05 12:20:47 +0100 311) 
7018ae7e (David A. Velasco 2013-02-05 12:20:47 +0100 312)     /**
7018ae7e (David A. Velasco 2013-02-05 12:20:47 +0100 313)      * Processes a request to pause the current playback 
7018ae7e (David A. Velasco 2013-02-05 12:20:47 +0100 314)      */
18bf35a8 (David A. Velasco 2013-02-06 14:14:55 +0100 315)     protected void processPauseRequest() {
7018ae7e (David A. Velasco 2013-02-05 12:20:47 +0100 316)         if (mState == State.PLAYING) {
7018ae7e (David A. Velasco 2013-02-05 12:20:47 +0100 317)             mState = State.PAUSED;
7018ae7e (David A. Velasco 2013-02-05 12:20:47 +0100 318)             mPlayer.pause();
7018ae7e (David A. Velasco 2013-02-05 12:20:47 +0100 319)             releaseResources(false); // retain media player in pause
7018ae7e (David A. Velasco 2013-02-05 12:20:47 +0100 320)             // TODO polite audio focus, instead of keep it owned; or not?
7018ae7e (David A. Velasco 2013-02-05 12:20:47 +0100 321)         }
7018ae7e (David A. Velasco 2013-02-05 12:20:47 +0100 322)     }
7018ae7e (David A. Velasco 2013-02-05 12:20:47 +0100 323)     
7018ae7e (David A. Velasco 2013-02-05 12:20:47 +0100 324)     
7018ae7e (David A. Velasco 2013-02-05 12:20:47 +0100 325)     /**
7018ae7e (David A. Velasco 2013-02-05 12:20:47 +0100 326)      * Processes a request to stop the playback.
7018ae7e (David A. Velasco 2013-02-05 12:20:47 +0100 327)      * 
7018ae7e (David A. Velasco 2013-02-05 12:20:47 +0100 328)      * @param   force       When 'true', the playback is stopped no matter the value of mState
7018ae7e (David A. Velasco 2013-02-05 12:20:47 +0100 329)      */
e4cd3fe5 (David A. Velasco 2013-02-11 12:46:50 +0100 330)     protected void processStopRequest(boolean force) {
11c1ad8f (David A. Velasco 2013-02-12 11:26:38 +0100 331)         if (mState != State.PREPARING || force) {
7018ae7e (David A. Velasco 2013-02-05 12:20:47 +0100 332)             mState = State.STOPPED;
18bf35a8 (David A. Velasco 2013-02-06 14:14:55 +0100 333)             mFile = null;
18bf35a8 (David A. Velasco 2013-02-06 14:14:55 +0100 334)             mAccount = null;
7018ae7e (David A. Velasco 2013-02-05 12:20:47 +0100 335)             releaseResources(true);
7018ae7e (David A. Velasco 2013-02-05 12:20:47 +0100 336)             giveUpAudioFocus();
7018ae7e (David A. Velasco 2013-02-05 12:20:47 +0100 337)             stopSelf();     // service is no longer necessary
7018ae7e (David A. Velasco 2013-02-05 12:20:47 +0100 338)         }
7018ae7e (David A. Velasco 2013-02-05 12:20:47 +0100 339)     }
7018ae7e (David A. Velasco 2013-02-05 12:20:47 +0100 340)     
7018ae7e (David A. Velasco 2013-02-05 12:20:47 +0100 341) 
7018ae7e (David A. Velasco 2013-02-05 12:20:47 +0100 342)     /**
7018ae7e (David A. Velasco 2013-02-05 12:20:47 +0100 343)      * Releases resources used by the service for playback. This includes the "foreground service"
7018ae7e (David A. Velasco 2013-02-05 12:20:47 +0100 344)      * status and notification, the wake locks and possibly the MediaPlayer.
7018ae7e (David A. Velasco 2013-02-05 12:20:47 +0100 345)      *
7018ae7e (David A. Velasco 2013-02-05 12:20:47 +0100 346)      * @param releaseMediaPlayer    Indicates whether the Media Player should also be released or not
7018ae7e (David A. Velasco 2013-02-05 12:20:47 +0100 347)      */
e4cd3fe5 (David A. Velasco 2013-02-11 12:46:50 +0100 348)     protected void releaseResources(boolean releaseMediaPlayer) {
7018ae7e (David A. Velasco 2013-02-05 12:20:47 +0100 349)         // stop being a foreground service
7018ae7e (David A. Velasco 2013-02-05 12:20:47 +0100 350)         stopForeground(true);
7018ae7e (David A. Velasco 2013-02-05 12:20:47 +0100 351) 
7018ae7e (David A. Velasco 2013-02-05 12:20:47 +0100 352)         // stop and release the Media Player, if it's available
7018ae7e (David A. Velasco 2013-02-05 12:20:47 +0100 353)         if (releaseMediaPlayer && mPlayer != null) {
7018ae7e (David A. Velasco 2013-02-05 12:20:47 +0100 354)             mPlayer.reset();
7018ae7e (David A. Velasco 2013-02-05 12:20:47 +0100 355)             mPlayer.release();
7018ae7e (David A. Velasco 2013-02-05 12:20:47 +0100 356)             mPlayer = null;
7018ae7e (David A. Velasco 2013-02-05 12:20:47 +0100 357)         }
7018ae7e (David A. Velasco 2013-02-05 12:20:47 +0100 358) 
7018ae7e (David A. Velasco 2013-02-05 12:20:47 +0100 359)         // release the Wifi lock, if holding it
7018ae7e (David A. Velasco 2013-02-05 12:20:47 +0100 360)         if (mWifiLock.isHeld()) {
7018ae7e (David A. Velasco 2013-02-05 12:20:47 +0100 361)             mWifiLock.release();
7018ae7e (David A. Velasco 2013-02-05 12:20:47 +0100 362)         }
7018ae7e (David A. Velasco 2013-02-05 12:20:47 +0100 363)     }
7018ae7e (David A. Velasco 2013-02-05 12:20:47 +0100 364) 
7018ae7e (David A. Velasco 2013-02-05 12:20:47 +0100 365)     
7018ae7e (David A. Velasco 2013-02-05 12:20:47 +0100 366)     /**
7018ae7e (David A. Velasco 2013-02-05 12:20:47 +0100 367)      * Fully releases the audio focus.
7018ae7e (David A. Velasco 2013-02-05 12:20:47 +0100 368)      */
7018ae7e (David A. Velasco 2013-02-05 12:20:47 +0100 369)     private void giveUpAudioFocus() {
7018ae7e (David A. Velasco 2013-02-05 12:20:47 +0100 370)         if (mAudioFocus == AudioFocus.FOCUS 
7018ae7e (David A. Velasco 2013-02-05 12:20:47 +0100 371)                 && mAudioManager != null  
7018ae7e (David A. Velasco 2013-02-05 12:20:47 +0100 372)                 && AudioManager.AUDIOFOCUS_REQUEST_GRANTED == mAudioManager.abandonAudioFocus(this))  {
7018ae7e (David A. Velasco 2013-02-05 12:20:47 +0100 373)             
7018ae7e (David A. Velasco 2013-02-05 12:20:47 +0100 374)             mAudioFocus = AudioFocus.NO_FOCUS;
7018ae7e (David A. Velasco 2013-02-05 12:20:47 +0100 375)         }
7018ae7e (David A. Velasco 2013-02-05 12:20:47 +0100 376)     }
7018ae7e (David A. Velasco 2013-02-05 12:20:47 +0100 377) 
7018ae7e (David A. Velasco 2013-02-05 12:20:47 +0100 378)     
7018ae7e (David A. Velasco 2013-02-05 12:20:47 +0100 379)     /**
7018ae7e (David A. Velasco 2013-02-05 12:20:47 +0100 380)      * Reconfigures MediaPlayer according to audio focus settings and starts/restarts it. 
7018ae7e (David A. Velasco 2013-02-05 12:20:47 +0100 381)      */
e4cd3fe5 (David A. Velasco 2013-02-11 12:46:50 +0100 382)     protected void configAndStartMediaPlayer() {
7018ae7e (David A. Velasco 2013-02-05 12:20:47 +0100 383)         if (mPlayer == null) {
7018ae7e (David A. Velasco 2013-02-05 12:20:47 +0100 384)             throw new IllegalStateException("mPlayer is NULL");
7018ae7e (David A. Velasco 2013-02-05 12:20:47 +0100 385)         }
7018ae7e (David A. Velasco 2013-02-05 12:20:47 +0100 386)         
7018ae7e (David A. Velasco 2013-02-05 12:20:47 +0100 387)         if (mAudioFocus == AudioFocus.NO_FOCUS) {
7018ae7e (David A. Velasco 2013-02-05 12:20:47 +0100 388)             if (mPlayer.isPlaying()) {
7018ae7e (David A. Velasco 2013-02-05 12:20:47 +0100 389)                 mPlayer.pause();        // have to be polite; but mState is not changed, to resume when focus is received again
7018ae7e (David A. Velasco 2013-02-05 12:20:47 +0100 390)             }
7018ae7e (David A. Velasco 2013-02-05 12:20:47 +0100 391)             
7018ae7e (David A. Velasco 2013-02-05 12:20:47 +0100 392)         }  else { 
7018ae7e (David A. Velasco 2013-02-05 12:20:47 +0100 393)             if (mAudioFocus == AudioFocus.NO_FOCUS_CAN_DUCK) {
7018ae7e (David A. Velasco 2013-02-05 12:20:47 +0100 394)                 mPlayer.setVolume(DUCK_VOLUME, DUCK_VOLUME);
7018ae7e (David A. Velasco 2013-02-05 12:20:47 +0100 395)                 
7018ae7e (David A. Velasco 2013-02-05 12:20:47 +0100 396)             } else {
7018ae7e (David A. Velasco 2013-02-05 12:20:47 +0100 397)                 mPlayer.setVolume(1.0f, 1.0f); // full volume
7018ae7e (David A. Velasco 2013-02-05 12:20:47 +0100 398)             }
7018ae7e (David A. Velasco 2013-02-05 12:20:47 +0100 399)     
7018ae7e (David A. Velasco 2013-02-05 12:20:47 +0100 400)             if (!mPlayer.isPlaying()) {
7018ae7e (David A. Velasco 2013-02-05 12:20:47 +0100 401)                 mPlayer.start();
7018ae7e (David A. Velasco 2013-02-05 12:20:47 +0100 402)             }
7018ae7e (David A. Velasco 2013-02-05 12:20:47 +0100 403)         }
7018ae7e (David A. Velasco 2013-02-05 12:20:47 +0100 404)     }
7018ae7e (David A. Velasco 2013-02-05 12:20:47 +0100 405) 
7018ae7e (David A. Velasco 2013-02-05 12:20:47 +0100 406)     
7018ae7e (David A. Velasco 2013-02-05 12:20:47 +0100 407)     /**
7018ae7e (David A. Velasco 2013-02-05 12:20:47 +0100 408)      * Requests the audio focus to the Audio Manager 
7018ae7e (David A. Velasco 2013-02-05 12:20:47 +0100 409)      */
7018ae7e (David A. Velasco 2013-02-05 12:20:47 +0100 410)     private void tryToGetAudioFocus() {
7018ae7e (David A. Velasco 2013-02-05 12:20:47 +0100 411)         if (mAudioFocus != AudioFocus.FOCUS 
7018ae7e (David A. Velasco 2013-02-05 12:20:47 +0100 412)                 && mAudioManager != null 
7018ae7e (David A. Velasco 2013-02-05 12:20:47 +0100 413)                 && (AudioManager.AUDIOFOCUS_REQUEST_GRANTED == mAudioManager.requestAudioFocus( this,
7018ae7e (David A. Velasco 2013-02-05 12:20:47 +0100 414)                                                                                                 AudioManager.STREAM_MUSIC, 
7018ae7e (David A. Velasco 2013-02-05 12:20:47 +0100 415)                                                                                                 AudioManager.AUDIOFOCUS_GAIN))
7018ae7e (David A. Velasco 2013-02-05 12:20:47 +0100 416)                 ) {
7018ae7e (David A. Velasco 2013-02-05 12:20:47 +0100 417)             mAudioFocus = AudioFocus.FOCUS;
7018ae7e (David A. Velasco 2013-02-05 12:20:47 +0100 418)         }
7018ae7e (David A. Velasco 2013-02-05 12:20:47 +0100 419)     }
7018ae7e (David A. Velasco 2013-02-05 12:20:47 +0100 420) 
7018ae7e (David A. Velasco 2013-02-05 12:20:47 +0100 421)     
7018ae7e (David A. Velasco 2013-02-05 12:20:47 +0100 422)     /**
7018ae7e (David A. Velasco 2013-02-05 12:20:47 +0100 423)      * Starts playing the current media file. 
7018ae7e (David A. Velasco 2013-02-05 12:20:47 +0100 424)      */
e4cd3fe5 (David A. Velasco 2013-02-11 12:46:50 +0100 425)     protected void playMedia() {
7018ae7e (David A. Velasco 2013-02-05 12:20:47 +0100 426)         mState = State.STOPPED;
7018ae7e (David A. Velasco 2013-02-05 12:20:47 +0100 427)         releaseResources(false); // release everything except MediaPlayer
7018ae7e (David A. Velasco 2013-02-05 12:20:47 +0100 428) 
7018ae7e (David A. Velasco 2013-02-05 12:20:47 +0100 429)         try {
7018ae7e (David A. Velasco 2013-02-05 12:20:47 +0100 430)             if (mFile == null) { 
7018ae7e (David A. Velasco 2013-02-05 12:20:47 +0100 431)                 Toast.makeText(this, R.string.media_err_nothing_to_play, Toast.LENGTH_LONG).show();
7018ae7e (David A. Velasco 2013-02-05 12:20:47 +0100 432)                 processStopRequest(true);
7018ae7e (David A. Velasco 2013-02-05 12:20:47 +0100 433)                 return;
7018ae7e (David A. Velasco 2013-02-05 12:20:47 +0100 434)                 
7018ae7e (David A. Velasco 2013-02-05 12:20:47 +0100 435)             } else if (mAccount == null) {
7018ae7e (David A. Velasco 2013-02-05 12:20:47 +0100 436)                 Toast.makeText(this, R.string.media_err_not_in_owncloud, Toast.LENGTH_LONG).show();
7018ae7e (David A. Velasco 2013-02-05 12:20:47 +0100 437)                 processStopRequest(true);
7018ae7e (David A. Velasco 2013-02-05 12:20:47 +0100 438)                 return;
7018ae7e (David A. Velasco 2013-02-05 12:20:47 +0100 439)             }
7018ae7e (David A. Velasco 2013-02-05 12:20:47 +0100 440) 
7018ae7e (David A. Velasco 2013-02-05 12:20:47 +0100 441)             createMediaPlayerIfNeeded();
7018ae7e (David A. Velasco 2013-02-05 12:20:47 +0100 442)             mPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
7018ae7e (David A. Velasco 2013-02-05 12:20:47 +0100 443)             String url = mFile.getStoragePath();
ba6eafcc (David A. Velasco 2013-02-12 12:19:38 +0100 444)             /* Streaming is not possible right now
7018ae7e (David A. Velasco 2013-02-05 12:20:47 +0100 445)             if (url == null || url.length() <= 0) {
7018ae7e (David A. Velasco 2013-02-05 12:20:47 +0100 446)                 url = AccountUtils.constructFullURLForAccount(this, mAccount) + mFile.getRemotePath();
7018ae7e (David A. Velasco 2013-02-05 12:20:47 +0100 447)             }
7018ae7e (David A. Velasco 2013-02-05 12:20:47 +0100 448)             mIsStreaming = url.startsWith("http:") || url.startsWith("https:");
ba6eafcc (David A. Velasco 2013-02-12 12:19:38 +0100 449)             */
ba6eafcc (David A. Velasco 2013-02-12 12:19:38 +0100 450)             mIsStreaming = false;
7018ae7e (David A. Velasco 2013-02-05 12:20:47 +0100 451)             
7018ae7e (David A. Velasco 2013-02-05 12:20:47 +0100 452)             mPlayer.setDataSource(url);
7018ae7e (David A. Velasco 2013-02-05 12:20:47 +0100 453) 
7018ae7e (David A. Velasco 2013-02-05 12:20:47 +0100 454)             mState = State.PREPARING;
7018ae7e (David A. Velasco 2013-02-05 12:20:47 +0100 455)             setUpAsForeground(String.format(getString(R.string.media_state_loading), mFile.getFileName()));
7018ae7e (David A. Velasco 2013-02-05 12:20:47 +0100 456) 
7018ae7e (David A. Velasco 2013-02-05 12:20:47 +0100 457)             // starts preparing the media player in background
7018ae7e (David A. Velasco 2013-02-05 12:20:47 +0100 458)             mPlayer.prepareAsync();
7018ae7e (David A. Velasco 2013-02-05 12:20:47 +0100 459) 
7018ae7e (David A. Velasco 2013-02-05 12:20:47 +0100 460)             // prevent the Wifi from going to sleep when streaming
7018ae7e (David A. Velasco 2013-02-05 12:20:47 +0100 461)             if (mIsStreaming) { 
7018ae7e (David A. Velasco 2013-02-05 12:20:47 +0100 462)                 mWifiLock.acquire();
7018ae7e (David A. Velasco 2013-02-05 12:20:47 +0100 463)             } else if (mWifiLock.isHeld()) {
7018ae7e (David A. Velasco 2013-02-05 12:20:47 +0100 464)                 mWifiLock.release();
7018ae7e (David A. Velasco 2013-02-05 12:20:47 +0100 465)             }
7018ae7e (David A. Velasco 2013-02-05 12:20:47 +0100 466)             
7018ae7e (David A. Velasco 2013-02-05 12:20:47 +0100 467)         } catch (SecurityException e) {
274dfd29 (David A. Velasco 2013-04-15 13:17:47 +0200 468)             Log_OC.e(TAG, "SecurityException playing " + mAccount.name + mFile.getRemotePath(), e);
11c1ad8f (David A. Velasco 2013-02-12 11:26:38 +0100 469)             Toast.makeText(this, String.format(getString(R.string.media_err_security_ex), mFile.getFileName()), Toast.LENGTH_LONG).show();
11c1ad8f (David A. Velasco 2013-02-12 11:26:38 +0100 470)             processStopRequest(true);
7018ae7e (David A. Velasco 2013-02-05 12:20:47 +0100 471)             
7018ae7e (David A. Velasco 2013-02-05 12:20:47 +0100 472)         } catch (IOException e) {
274dfd29 (David A. Velasco 2013-04-15 13:17:47 +0200 473)             Log_OC.e(TAG, "IOException playing " + mAccount.name + mFile.getRemotePath(), e);
11c1ad8f (David A. Velasco 2013-02-12 11:26:38 +0100 474)             Toast.makeText(this, String.format(getString(R.string.media_err_io_ex), mFile.getFileName()), Toast.LENGTH_LONG).show();
11c1ad8f (David A. Velasco 2013-02-12 11:26:38 +0100 475)             processStopRequest(true);
7018ae7e (David A. Velasco 2013-02-05 12:20:47 +0100 476)             
7018ae7e (David A. Velasco 2013-02-05 12:20:47 +0100 477)         } catch (IllegalStateException e) {
274dfd29 (David A. Velasco 2013-04-15 13:17:47 +0200 478)             Log_OC.e(TAG, "IllegalStateException " + mAccount.name + mFile.getRemotePath(), e);
11c1ad8f (David A. Velasco 2013-02-12 11:26:38 +0100 479)             Toast.makeText(this, String.format(getString(R.string.media_err_unexpected), mFile.getFileName()), Toast.LENGTH_LONG).show();
11c1ad8f (David A. Velasco 2013-02-12 11:26:38 +0100 480)             processStopRequest(true);
7018ae7e (David A. Velasco 2013-02-05 12:20:47 +0100 481)             
7018ae7e (David A. Velasco 2013-02-05 12:20:47 +0100 482)         } catch (IllegalArgumentException e) {
274dfd29 (David A. Velasco 2013-04-15 13:17:47 +0200 483)             Log_OC.e(TAG, "IllegalArgumentException " + mAccount.name + mFile.getRemotePath(), e);
11c1ad8f (David A. Velasco 2013-02-12 11:26:38 +0100 484)             Toast.makeText(this, String.format(getString(R.string.media_err_unexpected), mFile.getFileName()), Toast.LENGTH_LONG).show();
11c1ad8f (David A. Velasco 2013-02-12 11:26:38 +0100 485)             processStopRequest(true);
7018ae7e (David A. Velasco 2013-02-05 12:20:47 +0100 486)         }
7018ae7e (David A. Velasco 2013-02-05 12:20:47 +0100 487)     }
7018ae7e (David A. Velasco 2013-02-05 12:20:47 +0100 488) 
7018ae7e (David A. Velasco 2013-02-05 12:20:47 +0100 489)     
7018ae7e (David A. Velasco 2013-02-05 12:20:47 +0100 490)     /** Called when media player is done playing current song. */
7018ae7e (David A. Velasco 2013-02-05 12:20:47 +0100 491)     public void onCompletion(MediaPlayer player) {
7018ae7e (David A. Velasco 2013-02-05 12:20:47 +0100 492)         Toast.makeText(this, String.format(getString(R.string.media_event_done, mFile.getFileName())), Toast.LENGTH_LONG).show();
fa801791 (David A. Velasco 2013-03-22 13:04:57 +0100 493)         if (mMediaController != null) {
fa801791 (David A. Velasco 2013-03-22 13:04:57 +0100 494)             // somebody is still bound to the service
fa801791 (David A. Velasco 2013-03-22 13:04:57 +0100 495)             player.seekTo(0);
fa801791 (David A. Velasco 2013-03-22 13:04:57 +0100 496)             processPauseRequest();
fa801791 (David A. Velasco 2013-03-22 13:04:57 +0100 497)             mMediaController.updatePausePlay();
fa801791 (David A. Velasco 2013-03-22 13:04:57 +0100 498)         } else {
fa801791 (David A. Velasco 2013-03-22 13:04:57 +0100 499)             // nobody is bound
fa801791 (David A. Velasco 2013-03-22 13:04:57 +0100 500)             processStopRequest(true);
fa801791 (David A. Velasco 2013-03-22 13:04:57 +0100 501)         }
7018ae7e (David A. Velasco 2013-02-05 12:20:47 +0100 502)         return;
7018ae7e (David A. Velasco 2013-02-05 12:20:47 +0100 503)     }
7018ae7e (David A. Velasco 2013-02-05 12:20:47 +0100 504)     
7018ae7e (David A. Velasco 2013-02-05 12:20:47 +0100 505) 
7018ae7e (David A. Velasco 2013-02-05 12:20:47 +0100 506)     /** 
7018ae7e (David A. Velasco 2013-02-05 12:20:47 +0100 507)      * Called when media player is done preparing. 
7018ae7e (David A. Velasco 2013-02-05 12:20:47 +0100 508)      *
7018ae7e (David A. Velasco 2013-02-05 12:20:47 +0100 509)      * Time to start.
7018ae7e (David A. Velasco 2013-02-05 12:20:47 +0100 510)      */
7018ae7e (David A. Velasco 2013-02-05 12:20:47 +0100 511)     public void onPrepared(MediaPlayer player) {
7018ae7e (David A. Velasco 2013-02-05 12:20:47 +0100 512)         mState = State.PLAYING;
7018ae7e (David A. Velasco 2013-02-05 12:20:47 +0100 513)         updateNotification(String.format(getString(R.string.media_state_playing), mFile.getFileName()));
11c1ad8f (David A. Velasco 2013-02-12 11:26:38 +0100 514)         if (mMediaController != null) {
11c1ad8f (David A. Velasco 2013-02-12 11:26:38 +0100 515)             mMediaController.setEnabled(true);
11c1ad8f (David A. Velasco 2013-02-12 11:26:38 +0100 516)         }
fa801791 (David A. Velasco 2013-03-22 13:04:57 +0100 517)         player.seekTo(mStartPosition);
7018ae7e (David A. Velasco 2013-02-05 12:20:47 +0100 518)         configAndStartMediaPlayer();
fa801791 (David A. Velasco 2013-03-22 13:04:57 +0100 519)         if (!mPlayOnPrepared) {
fa801791 (David A. Velasco 2013-03-22 13:04:57 +0100 520)             processPauseRequest();
fa801791 (David A. Velasco 2013-03-22 13:04:57 +0100 521)         }
fa801791 (David A. Velasco 2013-03-22 13:04:57 +0100 522)         
11c1ad8f (David A. Velasco 2013-02-12 11:26:38 +0100 523)         if (mMediaController != null) {
ce7f7fa4 (David A. Velasco 2013-03-12 14:36:31 +0100 524)             mMediaController.updatePausePlay();
11c1ad8f (David A. Velasco 2013-02-12 11:26:38 +0100 525)         }
7018ae7e (David A. Velasco 2013-02-05 12:20:47 +0100 526)     }
7018ae7e (David A. Velasco 2013-02-05 12:20:47 +0100 527)     
7018ae7e (David A. Velasco 2013-02-05 12:20:47 +0100 528) 
7018ae7e (David A. Velasco 2013-02-05 12:20:47 +0100 529)     /** 
7018ae7e (David A. Velasco 2013-02-05 12:20:47 +0100 530)      * Updates the status notification
7018ae7e (David A. Velasco 2013-02-05 12:20:47 +0100 531)      */
7018ae7e (David A. Velasco 2013-02-05 12:20:47 +0100 532)     @SuppressWarnings("deprecation")
7018ae7e (David A. Velasco 2013-02-05 12:20:47 +0100 533)     private void updateNotification(String content) {
7018ae7e (David A. Velasco 2013-02-05 12:20:47 +0100 534)         // TODO check if updating the Intent is really necessary
fd396289 (David A. Velasco 2013-05-30 17:53:21 +0200 535)         Intent showDetailsIntent = new Intent(this, FileDisplayActivity.class);
bc1fcf84 (David A. Velasco 2013-05-07 13:49:54 +0200 536)         showDetailsIntent.putExtra(FileActivity.EXTRA_FILE, mFile);
bc1fcf84 (David A. Velasco 2013-05-07 13:49:54 +0200 537)         showDetailsIntent.putExtra(FileActivity.EXTRA_ACCOUNT, mAccount);
7018ae7e (David A. Velasco 2013-02-05 12:20:47 +0100 538)         showDetailsIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
7018ae7e (David A. Velasco 2013-02-05 12:20:47 +0100 539)         mNotification.contentIntent = PendingIntent.getActivity(getApplicationContext(), 
7018ae7e (David A. Velasco 2013-02-05 12:20:47 +0100 540)                                                                 (int)System.currentTimeMillis(), 
7018ae7e (David A. Velasco 2013-02-05 12:20:47 +0100 541)                                                                 showDetailsIntent, 
7018ae7e (David A. Velasco 2013-02-05 12:20:47 +0100 542)                                                                 PendingIntent.FLAG_UPDATE_CURRENT);
7018ae7e (David A. Velasco 2013-02-05 12:20:47 +0100 543)         mNotification.when = System.currentTimeMillis();
7018ae7e (David A. Velasco 2013-02-05 12:20:47 +0100 544)         //mNotification.contentView.setTextViewText(R.id.status_text, content);
e4cd3fe5 (David A. Velasco 2013-02-11 12:46:50 +0100 545)         String ticker = String.format(getString(R.string.media_notif_ticker), getString(R.string.app_name));
7018ae7e (David A. Velasco 2013-02-05 12:20:47 +0100 546)         mNotification.setLatestEventInfo(getApplicationContext(), ticker, content, mNotification.contentIntent);
e4cd3fe5 (David A. Velasco 2013-02-11 12:46:50 +0100 547)         mNotificationManager.notify(R.string.media_notif_ticker, mNotification);
7018ae7e (David A. Velasco 2013-02-05 12:20:47 +0100 548)     }
7018ae7e (David A. Velasco 2013-02-05 12:20:47 +0100 549) 
7018ae7e (David A. Velasco 2013-02-05 12:20:47 +0100 550)     
7018ae7e (David A. Velasco 2013-02-05 12:20:47 +0100 551)     /**
7018ae7e (David A. Velasco 2013-02-05 12:20:47 +0100 552)      * Configures the service as a foreground service.
7018ae7e (David A. Velasco 2013-02-05 12:20:47 +0100 553)      * 
7018ae7e (David A. Velasco 2013-02-05 12:20:47 +0100 554)      * The system will avoid finishing the service as much as possible when resources as low.
7018ae7e (David A. Velasco 2013-02-05 12:20:47 +0100 555)      * 
7018ae7e (David A. Velasco 2013-02-05 12:20:47 +0100 556)      * A notification must be created to keep the user aware of the existance of the service.
7018ae7e (David A. Velasco 2013-02-05 12:20:47 +0100 557)      */
7018ae7e (David A. Velasco 2013-02-05 12:20:47 +0100 558)     @SuppressWarnings("deprecation")
7018ae7e (David A. Velasco 2013-02-05 12:20:47 +0100 559)     private void setUpAsForeground(String content) {
7018ae7e (David A. Velasco 2013-02-05 12:20:47 +0100 560)         /// creates status notification
7018ae7e (David A. Velasco 2013-02-05 12:20:47 +0100 561)         // TODO put a progress bar to follow the playback progress
7018ae7e (David A. Velasco 2013-02-05 12:20:47 +0100 562)         mNotification = new Notification();
7018ae7e (David A. Velasco 2013-02-05 12:20:47 +0100 563)         mNotification.icon = android.R.drawable.ic_media_play;
7018ae7e (David A. Velasco 2013-02-05 12:20:47 +0100 564)         //mNotification.tickerText = text;
7018ae7e (David A. Velasco 2013-02-05 12:20:47 +0100 565)         mNotification.when = System.currentTimeMillis();
7018ae7e (David A. Velasco 2013-02-05 12:20:47 +0100 566)         mNotification.flags |= Notification.FLAG_ONGOING_EVENT;
7018ae7e (David A. Velasco 2013-02-05 12:20:47 +0100 567)         //mNotification.contentView.setTextViewText(R.id.status_text, "ownCloud Music Player");     // NULL POINTER
7018ae7e (David A. Velasco 2013-02-05 12:20:47 +0100 568)         //mNotification.contentView.setTextViewText(R.id.status_text, getString(R.string.downloader_download_in_progress_content));
7018ae7e (David A. Velasco 2013-02-05 12:20:47 +0100 569) 
7018ae7e (David A. Velasco 2013-02-05 12:20:47 +0100 570)         
7018ae7e (David A. Velasco 2013-02-05 12:20:47 +0100 571)         /// includes a pending intent in the notification showing the details view of the file
fd396289 (David A. Velasco 2013-05-30 17:53:21 +0200 572)         Intent showDetailsIntent = new Intent(this, FileDisplayActivity.class);
bc1fcf84 (David A. Velasco 2013-05-07 13:49:54 +0200 573)         showDetailsIntent.putExtra(FileActivity.EXTRA_FILE, mFile);
bc1fcf84 (David A. Velasco 2013-05-07 13:49:54 +0200 574)         showDetailsIntent.putExtra(FileActivity.EXTRA_ACCOUNT, mAccount);
7018ae7e (David A. Velasco 2013-02-05 12:20:47 +0100 575)         showDetailsIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
7018ae7e (David A. Velasco 2013-02-05 12:20:47 +0100 576)         mNotification.contentIntent = PendingIntent.getActivity(getApplicationContext(), 
7018ae7e (David A. Velasco 2013-02-05 12:20:47 +0100 577)                                                                 (int)System.currentTimeMillis(), 
7018ae7e (David A. Velasco 2013-02-05 12:20:47 +0100 578)                                                                 showDetailsIntent, 
7018ae7e (David A. Velasco 2013-02-05 12:20:47 +0100 579)                                                                 PendingIntent.FLAG_UPDATE_CURRENT);
7018ae7e (David A. Velasco 2013-02-05 12:20:47 +0100 580)         
7018ae7e (David A. Velasco 2013-02-05 12:20:47 +0100 581)         
7018ae7e (David A. Velasco 2013-02-05 12:20:47 +0100 582)         //mNotificationManager.notify(R.string.downloader_download_in_progress_ticker, mNotification);
e4cd3fe5 (David A. Velasco 2013-02-11 12:46:50 +0100 583)         String ticker = String.format(getString(R.string.media_notif_ticker), getString(R.string.app_name));
7018ae7e (David A. Velasco 2013-02-05 12:20:47 +0100 584)         mNotification.setLatestEventInfo(getApplicationContext(), ticker, content, mNotification.contentIntent);
e4cd3fe5 (David A. Velasco 2013-02-11 12:46:50 +0100 585)         startForeground(R.string.media_notif_ticker, mNotification);
7018ae7e (David A. Velasco 2013-02-05 12:20:47 +0100 586)         
7018ae7e (David A. Velasco 2013-02-05 12:20:47 +0100 587)     }
7018ae7e (David A. Velasco 2013-02-05 12:20:47 +0100 588) 
7018ae7e (David A. Velasco 2013-02-05 12:20:47 +0100 589)     /**
7018ae7e (David A. Velasco 2013-02-05 12:20:47 +0100 590)      * Called when there's an error playing media. 
7018ae7e (David A. Velasco 2013-02-05 12:20:47 +0100 591)      * 
7018ae7e (David A. Velasco 2013-02-05 12:20:47 +0100 592)      * Warns the user about the error and resets the media player.
7018ae7e (David A. Velasco 2013-02-05 12:20:47 +0100 593)      */
7018ae7e (David A. Velasco 2013-02-05 12:20:47 +0100 594)     public boolean onError(MediaPlayer mp, int what, int extra) {
274dfd29 (David A. Velasco 2013-04-15 13:17:47 +0200 595)         Log_OC.e(TAG, "Error in audio playback, what = " + what + ", extra = " + extra);
7018ae7e (David A. Velasco 2013-02-05 12:20:47 +0100 596)         
94206519 (David A. Velasco 2013-02-12 12:07:42 +0100 597)         String message = getMessageForMediaError(this, what, extra);
94206519 (David A. Velasco 2013-02-12 12:07:42 +0100 598)         Toast.makeText(getApplicationContext(), message, Toast.LENGTH_SHORT).show();
11c1ad8f (David A. Velasco 2013-02-12 11:26:38 +0100 599)         
11c1ad8f (David A. Velasco 2013-02-12 11:26:38 +0100 600)         processStopRequest(true);
7018ae7e (David A. Velasco 2013-02-05 12:20:47 +0100 601)         return true; 
7018ae7e (David A. Velasco 2013-02-05 12:20:47 +0100 602)     }
7018ae7e (David A. Velasco 2013-02-05 12:20:47 +0100 603) 
7018ae7e (David A. Velasco 2013-02-05 12:20:47 +0100 604)     /**
7018ae7e (David A. Velasco 2013-02-05 12:20:47 +0100 605)      * Called by the system when another app tries to play some sound.
7018ae7e (David A. Velasco 2013-02-05 12:20:47 +0100 606)      * 
7018ae7e (David A. Velasco 2013-02-05 12:20:47 +0100 607)      * {@inheritDoc}
7018ae7e (David A. Velasco 2013-02-05 12:20:47 +0100 608)      */
7018ae7e (David A. Velasco 2013-02-05 12:20:47 +0100 609)     @Override
7018ae7e (David A. Velasco 2013-02-05 12:20:47 +0100 610)     public void onAudioFocusChange(int focusChange) {
7018ae7e (David A. Velasco 2013-02-05 12:20:47 +0100 611)         if (focusChange > 0) {
7018ae7e (David A. Velasco 2013-02-05 12:20:47 +0100 612)             // focus gain; check AudioManager.AUDIOFOCUS_* values
7018ae7e (David A. Velasco 2013-02-05 12:20:47 +0100 613)             mAudioFocus = AudioFocus.FOCUS;
7018ae7e (David A. Velasco 2013-02-05 12:20:47 +0100 614)             // restart media player with new focus settings
7018ae7e (David A. Velasco 2013-02-05 12:20:47 +0100 615)             if (mState == State.PLAYING)
7018ae7e (David A. Velasco 2013-02-05 12:20:47 +0100 616)                 configAndStartMediaPlayer();
7018ae7e (David A. Velasco 2013-02-05 12:20:47 +0100 617)             
7018ae7e (David A. Velasco 2013-02-05 12:20:47 +0100 618)         } else if (focusChange < 0) {
7018ae7e (David A. Velasco 2013-02-05 12:20:47 +0100 619)             // focus loss; check AudioManager.AUDIOFOCUS_* values
7018ae7e (David A. Velasco 2013-02-05 12:20:47 +0100 620)             boolean canDuck = AudioManager.AUDIOFOCUS_LOSS_TRANSIENT_CAN_DUCK == focusChange;
7018ae7e (David A. Velasco 2013-02-05 12:20:47 +0100 621)                 mAudioFocus = canDuck ? AudioFocus.NO_FOCUS_CAN_DUCK : AudioFocus.NO_FOCUS;
7018ae7e (David A. Velasco 2013-02-05 12:20:47 +0100 622)                 // start/restart/pause media player with new focus settings
7018ae7e (David A. Velasco 2013-02-05 12:20:47 +0100 623)                 if (mPlayer != null && mPlayer.isPlaying())
7018ae7e (David A. Velasco 2013-02-05 12:20:47 +0100 624)                     configAndStartMediaPlayer();
7018ae7e (David A. Velasco 2013-02-05 12:20:47 +0100 625)         }
7018ae7e (David A. Velasco 2013-02-05 12:20:47 +0100 626)         
7018ae7e (David A. Velasco 2013-02-05 12:20:47 +0100 627)     }
7018ae7e (David A. Velasco 2013-02-05 12:20:47 +0100 628) 
7018ae7e (David A. Velasco 2013-02-05 12:20:47 +0100 629)     /**
7018ae7e (David A. Velasco 2013-02-05 12:20:47 +0100 630)      * Called when the service is finished for final clean-up.
7018ae7e (David A. Velasco 2013-02-05 12:20:47 +0100 631)      * 
7018ae7e (David A. Velasco 2013-02-05 12:20:47 +0100 632)      * {@inheritDoc}
7018ae7e (David A. Velasco 2013-02-05 12:20:47 +0100 633)      */
7018ae7e (David A. Velasco 2013-02-05 12:20:47 +0100 634)     @Override
7018ae7e (David A. Velasco 2013-02-05 12:20:47 +0100 635)     public void onDestroy() {
7018ae7e (David A. Velasco 2013-02-05 12:20:47 +0100 636)         mState = State.STOPPED;
7018ae7e (David A. Velasco 2013-02-05 12:20:47 +0100 637)         releaseResources(true);
7018ae7e (David A. Velasco 2013-02-05 12:20:47 +0100 638)         giveUpAudioFocus();
7018ae7e (David A. Velasco 2013-02-05 12:20:47 +0100 639)     }
7018ae7e (David A. Velasco 2013-02-05 12:20:47 +0100 640)     
7018ae7e (David A. Velasco 2013-02-05 12:20:47 +0100 641) 
18bf35a8 (David A. Velasco 2013-02-06 14:14:55 +0100 642)     /**
18bf35a8 (David A. Velasco 2013-02-06 14:14:55 +0100 643)      * Provides a binder object that clients can use to perform operations on the MediaPlayer managed by the MediaService. 
18bf35a8 (David A. Velasco 2013-02-06 14:14:55 +0100 644)      */
7018ae7e (David A. Velasco 2013-02-05 12:20:47 +0100 645)     @Override
18bf35a8 (David A. Velasco 2013-02-06 14:14:55 +0100 646)     public IBinder onBind(Intent arg) {
18bf35a8 (David A. Velasco 2013-02-06 14:14:55 +0100 647)         return mBinder;
18bf35a8 (David A. Velasco 2013-02-06 14:14:55 +0100 648)     }
18bf35a8 (David A. Velasco 2013-02-06 14:14:55 +0100 649)     
18bf35a8 (David A. Velasco 2013-02-06 14:14:55 +0100 650)     
18bf35a8 (David A. Velasco 2013-02-06 14:14:55 +0100 651)     /**
18bf35a8 (David A. Velasco 2013-02-06 14:14:55 +0100 652)      * Called when ALL the bound clients were onbound.
18bf35a8 (David A. Velasco 2013-02-06 14:14:55 +0100 653)      * 
18bf35a8 (David A. Velasco 2013-02-06 14:14:55 +0100 654)      * The service is destroyed if playback stopped or paused
18bf35a8 (David A. Velasco 2013-02-06 14:14:55 +0100 655)      */
18bf35a8 (David A. Velasco 2013-02-06 14:14:55 +0100 656)     @Override
18bf35a8 (David A. Velasco 2013-02-06 14:14:55 +0100 657)     public boolean onUnbind(Intent intent) {
18bf35a8 (David A. Velasco 2013-02-06 14:14:55 +0100 658)         if (mState == State.PAUSED || mState == State.STOPPED)  {
18bf35a8 (David A. Velasco 2013-02-06 14:14:55 +0100 659)             processStopRequest(false);
18bf35a8 (David A. Velasco 2013-02-06 14:14:55 +0100 660)         }
18bf35a8 (David A. Velasco 2013-02-06 14:14:55 +0100 661)         return false;   // not accepting rebinding (default behaviour)
18bf35a8 (David A. Velasco 2013-02-06 14:14:55 +0100 662)     }
18bf35a8 (David A. Velasco 2013-02-06 14:14:55 +0100 663) 
18bf35a8 (David A. Velasco 2013-02-06 14:14:55 +0100 664) 
18bf35a8 (David A. Velasco 2013-02-06 14:14:55 +0100 665)     /**
18bf35a8 (David A. Velasco 2013-02-06 14:14:55 +0100 666)      * Accesses the current MediaPlayer instance in the service.
18bf35a8 (David A. Velasco 2013-02-06 14:14:55 +0100 667)      * 
18bf35a8 (David A. Velasco 2013-02-06 14:14:55 +0100 668)      * To be handled carefully. Visibility is protected to be accessed only 
18bf35a8 (David A. Velasco 2013-02-06 14:14:55 +0100 669)      * 
18bf35a8 (David A. Velasco 2013-02-06 14:14:55 +0100 670)      * @return Current MediaPlayer instance handled by MediaService.
18bf35a8 (David A. Velasco 2013-02-06 14:14:55 +0100 671)      */
18bf35a8 (David A. Velasco 2013-02-06 14:14:55 +0100 672)     protected MediaPlayer getPlayer() {
18bf35a8 (David A. Velasco 2013-02-06 14:14:55 +0100 673)         return mPlayer;
18bf35a8 (David A. Velasco 2013-02-06 14:14:55 +0100 674)     }
18bf35a8 (David A. Velasco 2013-02-06 14:14:55 +0100 675) 
18bf35a8 (David A. Velasco 2013-02-06 14:14:55 +0100 676) 
18bf35a8 (David A. Velasco 2013-02-06 14:14:55 +0100 677)     /**
18bf35a8 (David A. Velasco 2013-02-06 14:14:55 +0100 678)      * Accesses the current OCFile loaded in the service.
18bf35a8 (David A. Velasco 2013-02-06 14:14:55 +0100 679)      * 
18bf35a8 (David A. Velasco 2013-02-06 14:14:55 +0100 680)      * @return  The current OCFile loaded in the service.
18bf35a8 (David A. Velasco 2013-02-06 14:14:55 +0100 681)      */
18bf35a8 (David A. Velasco 2013-02-06 14:14:55 +0100 682)     protected OCFile getCurrentFile() {
18bf35a8 (David A. Velasco 2013-02-06 14:14:55 +0100 683)         return mFile;
18bf35a8 (David A. Velasco 2013-02-06 14:14:55 +0100 684)     }
18bf35a8 (David A. Velasco 2013-02-06 14:14:55 +0100 685) 
18bf35a8 (David A. Velasco 2013-02-06 14:14:55 +0100 686)     
18bf35a8 (David A. Velasco 2013-02-06 14:14:55 +0100 687)     /**
18bf35a8 (David A. Velasco 2013-02-06 14:14:55 +0100 688)      * Accesses the current {@link State} of the MediaService.
18bf35a8 (David A. Velasco 2013-02-06 14:14:55 +0100 689)      * 
18bf35a8 (David A. Velasco 2013-02-06 14:14:55 +0100 690)      * @return  The current {@link State} of the MediaService.
18bf35a8 (David A. Velasco 2013-02-06 14:14:55 +0100 691)      */
e4cd3fe5 (David A. Velasco 2013-02-11 12:46:50 +0100 692)     protected State getState() {
18bf35a8 (David A. Velasco 2013-02-06 14:14:55 +0100 693)         return mState;
7018ae7e (David A. Velasco 2013-02-05 12:20:47 +0100 694)     }
7018ae7e (David A. Velasco 2013-02-05 12:20:47 +0100 695) 
11c1ad8f (David A. Velasco 2013-02-12 11:26:38 +0100 696) 
ce7f7fa4 (David A. Velasco 2013-03-12 14:36:31 +0100 697)     protected void setMediaContoller(MediaControlView mediaController) {
11c1ad8f (David A. Velasco 2013-02-12 11:26:38 +0100 698)         mMediaController = mediaController;
11c1ad8f (David A. Velasco 2013-02-12 11:26:38 +0100 699)     }
11c1ad8f (David A. Velasco 2013-02-12 11:26:38 +0100 700) 
ce7f7fa4 (David A. Velasco 2013-03-12 14:36:31 +0100 701)     protected MediaControlView getMediaController() {
11c1ad8f (David A. Velasco 2013-02-12 11:26:38 +0100 702)         return mMediaController;
11c1ad8f (David A. Velasco 2013-02-12 11:26:38 +0100 703)     }
11c1ad8f (David A. Velasco 2013-02-12 11:26:38 +0100 704) 
7018ae7e (David A. Velasco 2013-02-05 12:20:47 +0100 705) }
