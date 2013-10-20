18bf35a8 (David A. Velasco 2013-02-06 14:14:55 +0100   1) /* ownCloud Android client application
bdc0332c (David A. Velasco 2013-04-17 12:26:13 +0200   2)  *   Copyright (C) 2012-2013 ownCloud Inc.
18bf35a8 (David A. Velasco 2013-02-06 14:14:55 +0100   3)  *
18bf35a8 (David A. Velasco 2013-02-06 14:14:55 +0100   4)  *   This program is free software: you can redistribute it and/or modify
bdc0332c (David A. Velasco 2013-04-17 12:26:13 +0200   5)  *   it under the terms of the GNU General Public License version 2,
bdc0332c (David A. Velasco 2013-04-17 12:26:13 +0200   6)  *   as published by the Free Software Foundation.
18bf35a8 (David A. Velasco 2013-02-06 14:14:55 +0100   7)  *
18bf35a8 (David A. Velasco 2013-02-06 14:14:55 +0100   8)  *   This program is distributed in the hope that it will be useful,
18bf35a8 (David A. Velasco 2013-02-06 14:14:55 +0100   9)  *   but WITHOUT ANY WARRANTY; without even the implied warranty of
18bf35a8 (David A. Velasco 2013-02-06 14:14:55 +0100  10)  *   MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
18bf35a8 (David A. Velasco 2013-02-06 14:14:55 +0100  11)  *   GNU General Public License for more details.
18bf35a8 (David A. Velasco 2013-02-06 14:14:55 +0100  12)  *
18bf35a8 (David A. Velasco 2013-02-06 14:14:55 +0100  13)  *   You should have received a copy of the GNU General Public License
18bf35a8 (David A. Velasco 2013-02-06 14:14:55 +0100  14)  *   along with this program.  If not, see <http://www.gnu.org/licenses/>.
18bf35a8 (David A. Velasco 2013-02-06 14:14:55 +0100  15)  *
18bf35a8 (David A. Velasco 2013-02-06 14:14:55 +0100  16)  */
18bf35a8 (David A. Velasco 2013-02-06 14:14:55 +0100  17) 
18bf35a8 (David A. Velasco 2013-02-06 14:14:55 +0100  18) package com.owncloud.android.media;
18bf35a8 (David A. Velasco 2013-02-06 14:14:55 +0100  19) 
18bf35a8 (David A. Velasco 2013-02-06 14:14:55 +0100  20) 
274dfd29 (David A. Velasco 2013-04-15 13:17:47 +0200  21) import com.owncloud.android.Log_OC;
18bf35a8 (David A. Velasco 2013-02-06 14:14:55 +0100  22) import com.owncloud.android.datamodel.OCFile;
18bf35a8 (David A. Velasco 2013-02-06 14:14:55 +0100  23) import com.owncloud.android.media.MediaService.State;
18bf35a8 (David A. Velasco 2013-02-06 14:14:55 +0100  24) 
18bf35a8 (David A. Velasco 2013-02-06 14:14:55 +0100  25) import android.accounts.Account;
18bf35a8 (David A. Velasco 2013-02-06 14:14:55 +0100  26) import android.content.Intent;
18bf35a8 (David A. Velasco 2013-02-06 14:14:55 +0100  27) import android.media.MediaPlayer;
18bf35a8 (David A. Velasco 2013-02-06 14:14:55 +0100  28) import android.os.Binder;
18bf35a8 (David A. Velasco 2013-02-06 14:14:55 +0100  29) import android.widget.MediaController;
18bf35a8 (David A. Velasco 2013-02-06 14:14:55 +0100  30) 
18bf35a8 (David A. Velasco 2013-02-06 14:14:55 +0100  31) 
18bf35a8 (David A. Velasco 2013-02-06 14:14:55 +0100  32) /**
18bf35a8 (David A. Velasco 2013-02-06 14:14:55 +0100  33)  *  Binder allowing client components to perform operations on on the MediaPlayer managed by a MediaService instance.
18bf35a8 (David A. Velasco 2013-02-06 14:14:55 +0100  34)  * 
18bf35a8 (David A. Velasco 2013-02-06 14:14:55 +0100  35)  *  Provides the operations of {@link MediaController.MediaPlayerControl}, and an extra method to check if
18bf35a8 (David A. Velasco 2013-02-06 14:14:55 +0100  36)  *  an {@link OCFile} instance is handled by the MediaService.
18bf35a8 (David A. Velasco 2013-02-06 14:14:55 +0100  37)  *  
18bf35a8 (David A. Velasco 2013-02-06 14:14:55 +0100  38)  *  @author David A. Velasco
18bf35a8 (David A. Velasco 2013-02-06 14:14:55 +0100  39)  */
18bf35a8 (David A. Velasco 2013-02-06 14:14:55 +0100  40) public class MediaServiceBinder extends Binder implements MediaController.MediaPlayerControl {
18bf35a8 (David A. Velasco 2013-02-06 14:14:55 +0100  41) 
18bf35a8 (David A. Velasco 2013-02-06 14:14:55 +0100  42)     private static final String TAG = MediaServiceBinder.class.getSimpleName();
18bf35a8 (David A. Velasco 2013-02-06 14:14:55 +0100  43)     /**
18bf35a8 (David A. Velasco 2013-02-06 14:14:55 +0100  44)      * {@link MediaService} instance to access with the binder
18bf35a8 (David A. Velasco 2013-02-06 14:14:55 +0100  45)      */
18bf35a8 (David A. Velasco 2013-02-06 14:14:55 +0100  46)     private MediaService mService = null;
18bf35a8 (David A. Velasco 2013-02-06 14:14:55 +0100  47)     
18bf35a8 (David A. Velasco 2013-02-06 14:14:55 +0100  48)     /**
18bf35a8 (David A. Velasco 2013-02-06 14:14:55 +0100  49)      * Public constructor
18bf35a8 (David A. Velasco 2013-02-06 14:14:55 +0100  50)      * 
18bf35a8 (David A. Velasco 2013-02-06 14:14:55 +0100  51)      * @param service       A {@link MediaService} instance to access with the binder 
18bf35a8 (David A. Velasco 2013-02-06 14:14:55 +0100  52)      */
18bf35a8 (David A. Velasco 2013-02-06 14:14:55 +0100  53)     public MediaServiceBinder(MediaService service) {
18bf35a8 (David A. Velasco 2013-02-06 14:14:55 +0100  54)         if (service == null) {
18bf35a8 (David A. Velasco 2013-02-06 14:14:55 +0100  55)             throw new IllegalArgumentException("Argument 'service' can not be null");
18bf35a8 (David A. Velasco 2013-02-06 14:14:55 +0100  56)         }
18bf35a8 (David A. Velasco 2013-02-06 14:14:55 +0100  57)         mService = service;
18bf35a8 (David A. Velasco 2013-02-06 14:14:55 +0100  58)     }
18bf35a8 (David A. Velasco 2013-02-06 14:14:55 +0100  59)     
18bf35a8 (David A. Velasco 2013-02-06 14:14:55 +0100  60)     
18bf35a8 (David A. Velasco 2013-02-06 14:14:55 +0100  61)     public boolean isPlaying(OCFile mFile) {
18bf35a8 (David A. Velasco 2013-02-06 14:14:55 +0100  62)         return (mFile != null && mFile.equals(mService.getCurrentFile())); 
18bf35a8 (David A. Velasco 2013-02-06 14:14:55 +0100  63)     }
18bf35a8 (David A. Velasco 2013-02-06 14:14:55 +0100  64) 
18bf35a8 (David A. Velasco 2013-02-06 14:14:55 +0100  65)     
18bf35a8 (David A. Velasco 2013-02-06 14:14:55 +0100  66)     @Override
18bf35a8 (David A. Velasco 2013-02-06 14:14:55 +0100  67)     public boolean canPause() {
18bf35a8 (David A. Velasco 2013-02-06 14:14:55 +0100  68)         return true;
18bf35a8 (David A. Velasco 2013-02-06 14:14:55 +0100  69)     }
18bf35a8 (David A. Velasco 2013-02-06 14:14:55 +0100  70) 
18bf35a8 (David A. Velasco 2013-02-06 14:14:55 +0100  71)     @Override
18bf35a8 (David A. Velasco 2013-02-06 14:14:55 +0100  72)     public boolean canSeekBackward() {
18bf35a8 (David A. Velasco 2013-02-06 14:14:55 +0100  73)         return true;
18bf35a8 (David A. Velasco 2013-02-06 14:14:55 +0100  74)     }
18bf35a8 (David A. Velasco 2013-02-06 14:14:55 +0100  75) 
18bf35a8 (David A. Velasco 2013-02-06 14:14:55 +0100  76)     @Override
18bf35a8 (David A. Velasco 2013-02-06 14:14:55 +0100  77)     public boolean canSeekForward() {
18bf35a8 (David A. Velasco 2013-02-06 14:14:55 +0100  78)         return true;
18bf35a8 (David A. Velasco 2013-02-06 14:14:55 +0100  79)     }
18bf35a8 (David A. Velasco 2013-02-06 14:14:55 +0100  80) 
18bf35a8 (David A. Velasco 2013-02-06 14:14:55 +0100  81)     @Override
18bf35a8 (David A. Velasco 2013-02-06 14:14:55 +0100  82)     public int getBufferPercentage() {
18bf35a8 (David A. Velasco 2013-02-06 14:14:55 +0100  83)         MediaPlayer currentPlayer = mService.getPlayer();
18bf35a8 (David A. Velasco 2013-02-06 14:14:55 +0100  84)         if (currentPlayer != null) {
18bf35a8 (David A. Velasco 2013-02-06 14:14:55 +0100  85)             return 100;
18bf35a8 (David A. Velasco 2013-02-06 14:14:55 +0100  86)             // TODO update for streamed playback; add OnBufferUpdateListener in MediaService
18bf35a8 (David A. Velasco 2013-02-06 14:14:55 +0100  87)         } else {
18bf35a8 (David A. Velasco 2013-02-06 14:14:55 +0100  88)             return 0;
18bf35a8 (David A. Velasco 2013-02-06 14:14:55 +0100  89)         }
18bf35a8 (David A. Velasco 2013-02-06 14:14:55 +0100  90)     }
18bf35a8 (David A. Velasco 2013-02-06 14:14:55 +0100  91) 
18bf35a8 (David A. Velasco 2013-02-06 14:14:55 +0100  92)     @Override
18bf35a8 (David A. Velasco 2013-02-06 14:14:55 +0100  93)     public int getCurrentPosition() {
18bf35a8 (David A. Velasco 2013-02-06 14:14:55 +0100  94)         MediaPlayer currentPlayer = mService.getPlayer();
18bf35a8 (David A. Velasco 2013-02-06 14:14:55 +0100  95)         if (currentPlayer != null) {
18bf35a8 (David A. Velasco 2013-02-06 14:14:55 +0100  96)             int pos = currentPlayer.getCurrentPosition();
18bf35a8 (David A. Velasco 2013-02-06 14:14:55 +0100  97)             return pos;
18bf35a8 (David A. Velasco 2013-02-06 14:14:55 +0100  98)         } else {
18bf35a8 (David A. Velasco 2013-02-06 14:14:55 +0100  99)             return 0;
18bf35a8 (David A. Velasco 2013-02-06 14:14:55 +0100 100)         }
18bf35a8 (David A. Velasco 2013-02-06 14:14:55 +0100 101)     }
18bf35a8 (David A. Velasco 2013-02-06 14:14:55 +0100 102) 
18bf35a8 (David A. Velasco 2013-02-06 14:14:55 +0100 103)     @Override
18bf35a8 (David A. Velasco 2013-02-06 14:14:55 +0100 104)     public int getDuration() {
18bf35a8 (David A. Velasco 2013-02-06 14:14:55 +0100 105)         MediaPlayer currentPlayer = mService.getPlayer();
18bf35a8 (David A. Velasco 2013-02-06 14:14:55 +0100 106)         if (currentPlayer != null) {
18bf35a8 (David A. Velasco 2013-02-06 14:14:55 +0100 107)             int dur = currentPlayer.getDuration();
18bf35a8 (David A. Velasco 2013-02-06 14:14:55 +0100 108)             return dur;
18bf35a8 (David A. Velasco 2013-02-06 14:14:55 +0100 109)         } else {
18bf35a8 (David A. Velasco 2013-02-06 14:14:55 +0100 110)             return 0;
18bf35a8 (David A. Velasco 2013-02-06 14:14:55 +0100 111)         }
18bf35a8 (David A. Velasco 2013-02-06 14:14:55 +0100 112)     }
18bf35a8 (David A. Velasco 2013-02-06 14:14:55 +0100 113) 
18bf35a8 (David A. Velasco 2013-02-06 14:14:55 +0100 114)     
18bf35a8 (David A. Velasco 2013-02-06 14:14:55 +0100 115)     /**
18bf35a8 (David A. Velasco 2013-02-06 14:14:55 +0100 116)      * Reports if the MediaService is playing a file or not.
18bf35a8 (David A. Velasco 2013-02-06 14:14:55 +0100 117)      * 
18bf35a8 (David A. Velasco 2013-02-06 14:14:55 +0100 118)      * Considers that the file is being played when it is in preparation because the expected
18bf35a8 (David A. Velasco 2013-02-06 14:14:55 +0100 119)      * client of this method is a {@link MediaController} , and we do not want that the 'play'
18bf35a8 (David A. Velasco 2013-02-06 14:14:55 +0100 120)      * button is shown when the file is being prepared by the MediaService.
18bf35a8 (David A. Velasco 2013-02-06 14:14:55 +0100 121)      */
18bf35a8 (David A. Velasco 2013-02-06 14:14:55 +0100 122)     @Override
18bf35a8 (David A. Velasco 2013-02-06 14:14:55 +0100 123)     public boolean isPlaying() {
18bf35a8 (David A. Velasco 2013-02-06 14:14:55 +0100 124)         MediaService.State currentState = mService.getState();
fa801791 (David A. Velasco 2013-03-22 13:04:57 +0100 125)         return (currentState == State.PLAYING || (currentState == State.PREPARING && mService.mPlayOnPrepared));
18bf35a8 (David A. Velasco 2013-02-06 14:14:55 +0100 126)     }
18bf35a8 (David A. Velasco 2013-02-06 14:14:55 +0100 127) 
18bf35a8 (David A. Velasco 2013-02-06 14:14:55 +0100 128)     
18bf35a8 (David A. Velasco 2013-02-06 14:14:55 +0100 129)     @Override
18bf35a8 (David A. Velasco 2013-02-06 14:14:55 +0100 130)     public void pause() {
274dfd29 (David A. Velasco 2013-04-15 13:17:47 +0200 131)         Log_OC.d(TAG, "Pausing through binder...");
18bf35a8 (David A. Velasco 2013-02-06 14:14:55 +0100 132)         mService.processPauseRequest();
18bf35a8 (David A. Velasco 2013-02-06 14:14:55 +0100 133)     }
18bf35a8 (David A. Velasco 2013-02-06 14:14:55 +0100 134) 
18bf35a8 (David A. Velasco 2013-02-06 14:14:55 +0100 135)     @Override
18bf35a8 (David A. Velasco 2013-02-06 14:14:55 +0100 136)     public void seekTo(int pos) {
274dfd29 (David A. Velasco 2013-04-15 13:17:47 +0200 137)         Log_OC.d(TAG, "Seeking " + pos + " through binder...");
18bf35a8 (David A. Velasco 2013-02-06 14:14:55 +0100 138)         MediaPlayer currentPlayer = mService.getPlayer();
18bf35a8 (David A. Velasco 2013-02-06 14:14:55 +0100 139)         MediaService.State currentState = mService.getState();
18bf35a8 (David A. Velasco 2013-02-06 14:14:55 +0100 140)         if (currentPlayer != null && currentState != State.PREPARING && currentState != State.STOPPED) {
18bf35a8 (David A. Velasco 2013-02-06 14:14:55 +0100 141)             currentPlayer.seekTo(pos);
18bf35a8 (David A. Velasco 2013-02-06 14:14:55 +0100 142)         }
18bf35a8 (David A. Velasco 2013-02-06 14:14:55 +0100 143)     }
18bf35a8 (David A. Velasco 2013-02-06 14:14:55 +0100 144) 
18bf35a8 (David A. Velasco 2013-02-06 14:14:55 +0100 145)     @Override
18bf35a8 (David A. Velasco 2013-02-06 14:14:55 +0100 146)     public void start() {
274dfd29 (David A. Velasco 2013-04-15 13:17:47 +0200 147)         Log_OC.d(TAG, "Starting through binder...");
18bf35a8 (David A. Velasco 2013-02-06 14:14:55 +0100 148)         mService.processPlayRequest();  // this will finish the service if there is no file preloaded to play
18bf35a8 (David A. Velasco 2013-02-06 14:14:55 +0100 149)     }
18bf35a8 (David A. Velasco 2013-02-06 14:14:55 +0100 150)     
fa801791 (David A. Velasco 2013-03-22 13:04:57 +0100 151)     public void start(Account account, OCFile file, boolean playImmediately, int position) {
274dfd29 (David A. Velasco 2013-04-15 13:17:47 +0200 152)         Log_OC.d(TAG, "Loading and starting through binder...");
18bf35a8 (David A. Velasco 2013-02-06 14:14:55 +0100 153)         Intent i = new Intent(mService, MediaService.class);
18bf35a8 (David A. Velasco 2013-02-06 14:14:55 +0100 154)         i.putExtra(MediaService.EXTRA_ACCOUNT, account);
18bf35a8 (David A. Velasco 2013-02-06 14:14:55 +0100 155)         i.putExtra(MediaService.EXTRA_FILE, file);
fa801791 (David A. Velasco 2013-03-22 13:04:57 +0100 156)         i.putExtra(MediaService.EXTRA_PLAY_ON_LOAD, playImmediately);
fa801791 (David A. Velasco 2013-03-22 13:04:57 +0100 157)         i.putExtra(MediaService.EXTRA_START_POSITION, position);
18bf35a8 (David A. Velasco 2013-02-06 14:14:55 +0100 158)         i.setAction(MediaService.ACTION_PLAY_FILE);
18bf35a8 (David A. Velasco 2013-02-06 14:14:55 +0100 159)         mService.startService(i);
18bf35a8 (David A. Velasco 2013-02-06 14:14:55 +0100 160)     }
18bf35a8 (David A. Velasco 2013-02-06 14:14:55 +0100 161) 
11c1ad8f (David A. Velasco 2013-02-12 11:26:38 +0100 162) 
ce7f7fa4 (David A. Velasco 2013-03-12 14:36:31 +0100 163)     public void registerMediaController(MediaControlView mediaController) {
11c1ad8f (David A. Velasco 2013-02-12 11:26:38 +0100 164)         mService.setMediaContoller(mediaController);
11c1ad8f (David A. Velasco 2013-02-12 11:26:38 +0100 165)     }
11c1ad8f (David A. Velasco 2013-02-12 11:26:38 +0100 166)     
ce7f7fa4 (David A. Velasco 2013-03-12 14:36:31 +0100 167)     public void unregisterMediaController(MediaControlView mediaController) {
7101a042 (David A. Velasco 2013-02-14 19:21:09 +0100 168)         if (mediaController != null && mediaController == mService.getMediaController()) {
11c1ad8f (David A. Velasco 2013-02-12 11:26:38 +0100 169)             mService.setMediaContoller(null);
11c1ad8f (David A. Velasco 2013-02-12 11:26:38 +0100 170)         }
11c1ad8f (David A. Velasco 2013-02-12 11:26:38 +0100 171)         
11c1ad8f (David A. Velasco 2013-02-12 11:26:38 +0100 172)     }
11c1ad8f (David A. Velasco 2013-02-12 11:26:38 +0100 173) 
11c1ad8f (David A. Velasco 2013-02-12 11:26:38 +0100 174)     public boolean isInPlaybackState() {
11c1ad8f (David A. Velasco 2013-02-12 11:26:38 +0100 175)         MediaService.State currentState = mService.getState();
11c1ad8f (David A. Velasco 2013-02-12 11:26:38 +0100 176)         return (currentState == MediaService.State.PLAYING || currentState == MediaService.State.PAUSED);
11c1ad8f (David A. Velasco 2013-02-12 11:26:38 +0100 177)     }
11c1ad8f (David A. Velasco 2013-02-12 11:26:38 +0100 178) 
18bf35a8 (David A. Velasco 2013-02-06 14:14:55 +0100 179) }
18bf35a8 (David A. Velasco 2013-02-06 14:14:55 +0100 180) 
18bf35a8 (David A. Velasco 2013-02-06 14:14:55 +0100 181) 
