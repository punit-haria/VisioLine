7101a042 src/com/owncloud/android/ui/fragment/FilePreviewFragment.java (David A. Velasco 2013-02-14 19:21:09 +0100   1) /* ownCloud Android client application
7101a042 src/com/owncloud/android/ui/fragment/FilePreviewFragment.java (David A. Velasco 2013-02-14 19:21:09 +0100   2)  *   Copyright (C) 2012-2013 ownCloud Inc. 
7101a042 src/com/owncloud/android/ui/fragment/FilePreviewFragment.java (David A. Velasco 2013-02-14 19:21:09 +0100   3)  *
7101a042 src/com/owncloud/android/ui/fragment/FilePreviewFragment.java (David A. Velasco 2013-02-14 19:21:09 +0100   4)  *   This program is free software: you can redistribute it and/or modify
bdc0332c src/com/owncloud/android/ui/preview/PreviewMediaFragment.java (David A. Velasco 2013-04-17 12:26:13 +0200   5)  *   it under the terms of the GNU General Public License version 2,
bdc0332c src/com/owncloud/android/ui/preview/PreviewMediaFragment.java (David A. Velasco 2013-04-17 12:26:13 +0200   6)  *   as published by the Free Software Foundation.
7101a042 src/com/owncloud/android/ui/fragment/FilePreviewFragment.java (David A. Velasco 2013-02-14 19:21:09 +0100   7)  *
7101a042 src/com/owncloud/android/ui/fragment/FilePreviewFragment.java (David A. Velasco 2013-02-14 19:21:09 +0100   8)  *   This program is distributed in the hope that it will be useful,
7101a042 src/com/owncloud/android/ui/fragment/FilePreviewFragment.java (David A. Velasco 2013-02-14 19:21:09 +0100   9)  *   but WITHOUT ANY WARRANTY; without even the implied warranty of
7101a042 src/com/owncloud/android/ui/fragment/FilePreviewFragment.java (David A. Velasco 2013-02-14 19:21:09 +0100  10)  *   MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
7101a042 src/com/owncloud/android/ui/fragment/FilePreviewFragment.java (David A. Velasco 2013-02-14 19:21:09 +0100  11)  *   GNU General Public License for more details.
7101a042 src/com/owncloud/android/ui/fragment/FilePreviewFragment.java (David A. Velasco 2013-02-14 19:21:09 +0100  12)  *
7101a042 src/com/owncloud/android/ui/fragment/FilePreviewFragment.java (David A. Velasco 2013-02-14 19:21:09 +0100  13)  *   You should have received a copy of the GNU General Public License
7101a042 src/com/owncloud/android/ui/fragment/FilePreviewFragment.java (David A. Velasco 2013-02-14 19:21:09 +0100  14)  *   along with this program.  If not, see <http://www.gnu.org/licenses/>.
7101a042 src/com/owncloud/android/ui/fragment/FilePreviewFragment.java (David A. Velasco 2013-02-14 19:21:09 +0100  15)  *
7101a042 src/com/owncloud/android/ui/fragment/FilePreviewFragment.java (David A. Velasco 2013-02-14 19:21:09 +0100  16)  */
2f1aaa8b src/com/owncloud/android/ui/preview/PreviewMediaFragment.java (David A. Velasco 2013-02-26 19:04:42 +0100  17) package com.owncloud.android.ui.preview;
7101a042 src/com/owncloud/android/ui/fragment/FilePreviewFragment.java (David A. Velasco 2013-02-14 19:21:09 +0100  18) 
7101a042 src/com/owncloud/android/ui/fragment/FilePreviewFragment.java (David A. Velasco 2013-02-14 19:21:09 +0100  19) import java.io.File;
7101a042 src/com/owncloud/android/ui/fragment/FilePreviewFragment.java (David A. Velasco 2013-02-14 19:21:09 +0100  20) import java.util.ArrayList;
7101a042 src/com/owncloud/android/ui/fragment/FilePreviewFragment.java (David A. Velasco 2013-02-14 19:21:09 +0100  21) import java.util.List;
7101a042 src/com/owncloud/android/ui/fragment/FilePreviewFragment.java (David A. Velasco 2013-02-14 19:21:09 +0100  22) 
7101a042 src/com/owncloud/android/ui/fragment/FilePreviewFragment.java (David A. Velasco 2013-02-14 19:21:09 +0100  23) import android.accounts.Account;
7101a042 src/com/owncloud/android/ui/fragment/FilePreviewFragment.java (David A. Velasco 2013-02-14 19:21:09 +0100  24) import android.app.Activity;
7101a042 src/com/owncloud/android/ui/fragment/FilePreviewFragment.java (David A. Velasco 2013-02-14 19:21:09 +0100  25) import android.app.AlertDialog;
7101a042 src/com/owncloud/android/ui/fragment/FilePreviewFragment.java (David A. Velasco 2013-02-14 19:21:09 +0100  26) import android.content.ActivityNotFoundException;
7101a042 src/com/owncloud/android/ui/fragment/FilePreviewFragment.java (David A. Velasco 2013-02-14 19:21:09 +0100  27) import android.content.ComponentName;
7101a042 src/com/owncloud/android/ui/fragment/FilePreviewFragment.java (David A. Velasco 2013-02-14 19:21:09 +0100  28) import android.content.Context;
7101a042 src/com/owncloud/android/ui/fragment/FilePreviewFragment.java (David A. Velasco 2013-02-14 19:21:09 +0100  29) import android.content.DialogInterface;
7101a042 src/com/owncloud/android/ui/fragment/FilePreviewFragment.java (David A. Velasco 2013-02-14 19:21:09 +0100  30) import android.content.Intent;
7101a042 src/com/owncloud/android/ui/fragment/FilePreviewFragment.java (David A. Velasco 2013-02-14 19:21:09 +0100  31) import android.content.ServiceConnection;
00328cb8 src/com/owncloud/android/ui/preview/PreviewMediaFragment.java (David A. Velasco 2013-04-30 13:16:08 +0200  32) import android.content.res.Configuration;
7101a042 src/com/owncloud/android/ui/fragment/FilePreviewFragment.java (David A. Velasco 2013-02-14 19:21:09 +0100  33) import android.media.MediaPlayer;
7101a042 src/com/owncloud/android/ui/fragment/FilePreviewFragment.java (David A. Velasco 2013-02-14 19:21:09 +0100  34) import android.media.MediaPlayer.OnCompletionListener;
7101a042 src/com/owncloud/android/ui/fragment/FilePreviewFragment.java (David A. Velasco 2013-02-14 19:21:09 +0100  35) import android.media.MediaPlayer.OnErrorListener;
7101a042 src/com/owncloud/android/ui/fragment/FilePreviewFragment.java (David A. Velasco 2013-02-14 19:21:09 +0100  36) import android.media.MediaPlayer.OnPreparedListener;
7101a042 src/com/owncloud/android/ui/fragment/FilePreviewFragment.java (David A. Velasco 2013-02-14 19:21:09 +0100  37) import android.net.Uri;
a11e7fd9 src/com/owncloud/android/ui/preview/PreviewMediaFragment.java (David A. Velasco 2013-04-10 13:07:10 +0200  38) import android.os.Build;
7101a042 src/com/owncloud/android/ui/fragment/FilePreviewFragment.java (David A. Velasco 2013-02-14 19:21:09 +0100  39) import android.os.Bundle;
7101a042 src/com/owncloud/android/ui/fragment/FilePreviewFragment.java (David A. Velasco 2013-02-14 19:21:09 +0100  40) import android.os.Handler;
7101a042 src/com/owncloud/android/ui/fragment/FilePreviewFragment.java (David A. Velasco 2013-02-14 19:21:09 +0100  41) import android.os.IBinder;
7101a042 src/com/owncloud/android/ui/fragment/FilePreviewFragment.java (David A. Velasco 2013-02-14 19:21:09 +0100  42) import android.view.LayoutInflater;
7101a042 src/com/owncloud/android/ui/fragment/FilePreviewFragment.java (David A. Velasco 2013-02-14 19:21:09 +0100  43) import android.view.MotionEvent;
7101a042 src/com/owncloud/android/ui/fragment/FilePreviewFragment.java (David A. Velasco 2013-02-14 19:21:09 +0100  44) import android.view.View;
7101a042 src/com/owncloud/android/ui/fragment/FilePreviewFragment.java (David A. Velasco 2013-02-14 19:21:09 +0100  45) import android.view.View.OnTouchListener;
7101a042 src/com/owncloud/android/ui/fragment/FilePreviewFragment.java (David A. Velasco 2013-02-14 19:21:09 +0100  46) import android.view.ViewGroup;
7101a042 src/com/owncloud/android/ui/fragment/FilePreviewFragment.java (David A. Velasco 2013-02-14 19:21:09 +0100  47) import android.webkit.MimeTypeMap;
7101a042 src/com/owncloud/android/ui/fragment/FilePreviewFragment.java (David A. Velasco 2013-02-14 19:21:09 +0100  48) import android.widget.ImageView;
7101a042 src/com/owncloud/android/ui/fragment/FilePreviewFragment.java (David A. Velasco 2013-02-14 19:21:09 +0100  49) import android.widget.Toast;
7101a042 src/com/owncloud/android/ui/fragment/FilePreviewFragment.java (David A. Velasco 2013-02-14 19:21:09 +0100  50) import android.widget.VideoView;
7101a042 src/com/owncloud/android/ui/fragment/FilePreviewFragment.java (David A. Velasco 2013-02-14 19:21:09 +0100  51) 
f2474ae2 src/com/owncloud/android/ui/fragment/FilePreviewFragment.java (David A. Velasco 2013-02-18 11:23:18 +0100  52) import com.actionbarsherlock.view.Menu;
f2474ae2 src/com/owncloud/android/ui/fragment/FilePreviewFragment.java (David A. Velasco 2013-02-18 11:23:18 +0100  53) import com.actionbarsherlock.view.MenuInflater;
f2474ae2 src/com/owncloud/android/ui/fragment/FilePreviewFragment.java (David A. Velasco 2013-02-18 11:23:18 +0100  54) import com.actionbarsherlock.view.MenuItem;
7101a042 src/com/owncloud/android/ui/fragment/FilePreviewFragment.java (David A. Velasco 2013-02-14 19:21:09 +0100  55) import com.owncloud.android.datamodel.FileDataStorageManager;
7101a042 src/com/owncloud/android/ui/fragment/FilePreviewFragment.java (David A. Velasco 2013-02-14 19:21:09 +0100  56) import com.owncloud.android.datamodel.OCFile;
d68b3246 src/com/owncloud/android/ui/preview/PreviewMediaFragment.java (David A. Velasco 2013-03-08 13:32:10 +0100  57) import com.owncloud.android.media.MediaControlView;
7101a042 src/com/owncloud/android/ui/fragment/FilePreviewFragment.java (David A. Velasco 2013-02-14 19:21:09 +0100  58) import com.owncloud.android.media.MediaService;
7101a042 src/com/owncloud/android/ui/fragment/FilePreviewFragment.java (David A. Velasco 2013-02-14 19:21:09 +0100  59) import com.owncloud.android.media.MediaServiceBinder;
7101a042 src/com/owncloud/android/ui/fragment/FilePreviewFragment.java (David A. Velasco 2013-02-14 19:21:09 +0100  60) import com.owncloud.android.operations.OnRemoteOperationListener;
7101a042 src/com/owncloud/android/ui/fragment/FilePreviewFragment.java (David A. Velasco 2013-02-14 19:21:09 +0100  61) import com.owncloud.android.operations.RemoteOperation;
7101a042 src/com/owncloud/android/ui/fragment/FilePreviewFragment.java (David A. Velasco 2013-02-14 19:21:09 +0100  62) import com.owncloud.android.operations.RemoteOperationResult;
7101a042 src/com/owncloud/android/ui/fragment/FilePreviewFragment.java (David A. Velasco 2013-02-14 19:21:09 +0100  63) import com.owncloud.android.operations.RemoveFileOperation;
3ecabe3e src/com/owncloud/android/ui/preview/PreviewMediaFragment.java (David A. Velasco 2013-06-14 16:59:38 +0200  64) import com.owncloud.android.ui.activity.FileActivity;
7101a042 src/com/owncloud/android/ui/fragment/FilePreviewFragment.java (David A. Velasco 2013-02-14 19:21:09 +0100  65) import com.owncloud.android.ui.activity.FileDisplayActivity;
2f1aaa8b src/com/owncloud/android/ui/preview/PreviewMediaFragment.java (David A. Velasco 2013-02-26 19:04:42 +0100  66) import com.owncloud.android.ui.fragment.ConfirmationDialogFragment;
2f1aaa8b src/com/owncloud/android/ui/preview/PreviewMediaFragment.java (David A. Velasco 2013-02-26 19:04:42 +0100  67) import com.owncloud.android.ui.fragment.FileFragment;
7101a042 src/com/owncloud/android/ui/fragment/FilePreviewFragment.java (David A. Velasco 2013-02-14 19:21:09 +0100  68) 
00328cb8 src/com/owncloud/android/ui/preview/PreviewMediaFragment.java (David A. Velasco 2013-04-30 13:16:08 +0200  69) import com.owncloud.android.Log_OC;
7101a042 src/com/owncloud/android/ui/fragment/FilePreviewFragment.java (David A. Velasco 2013-02-14 19:21:09 +0100  70) import com.owncloud.android.R;
7101a042 src/com/owncloud/android/ui/fragment/FilePreviewFragment.java (David A. Velasco 2013-02-14 19:21:09 +0100  71) import eu.alefzero.webdav.WebdavUtils;
7101a042 src/com/owncloud/android/ui/fragment/FilePreviewFragment.java (David A. Velasco 2013-02-14 19:21:09 +0100  72) 
7101a042 src/com/owncloud/android/ui/fragment/FilePreviewFragment.java (David A. Velasco 2013-02-14 19:21:09 +0100  73) /**
2f1aaa8b src/com/owncloud/android/ui/preview/PreviewMediaFragment.java (David A. Velasco 2013-02-26 19:04:42 +0100  74)  * This fragment shows a preview of a downloaded media file (audio or video).
7101a042 src/com/owncloud/android/ui/fragment/FilePreviewFragment.java (David A. Velasco 2013-02-14 19:21:09 +0100  75)  * 
7101a042 src/com/owncloud/android/ui/fragment/FilePreviewFragment.java (David A. Velasco 2013-02-14 19:21:09 +0100  76)  * Trying to get an instance with NULL {@link OCFile} or ownCloud {@link Account} values will produce an {@link IllegalStateException}.
7101a042 src/com/owncloud/android/ui/fragment/FilePreviewFragment.java (David A. Velasco 2013-02-14 19:21:09 +0100  77)  * 
7101a042 src/com/owncloud/android/ui/fragment/FilePreviewFragment.java (David A. Velasco 2013-02-14 19:21:09 +0100  78)  * By now, if the {@link OCFile} passed is not downloaded, an {@link IllegalStateException} is generated on instantiation too.
7101a042 src/com/owncloud/android/ui/fragment/FilePreviewFragment.java (David A. Velasco 2013-02-14 19:21:09 +0100  79)  * 
7101a042 src/com/owncloud/android/ui/fragment/FilePreviewFragment.java (David A. Velasco 2013-02-14 19:21:09 +0100  80)  * @author David A. Velasco
7101a042 src/com/owncloud/android/ui/fragment/FilePreviewFragment.java (David A. Velasco 2013-02-14 19:21:09 +0100  81)  */
fd396289 src/com/owncloud/android/ui/preview/PreviewMediaFragment.java (David A. Velasco 2013-05-30 17:53:21 +0200  82) public class PreviewMediaFragment extends FileFragment implements
fd396289 src/com/owncloud/android/ui/preview/PreviewMediaFragment.java (David A. Velasco 2013-05-30 17:53:21 +0200  83)         OnTouchListener,  
2f1aaa8b src/com/owncloud/android/ui/preview/PreviewMediaFragment.java (David A. Velasco 2013-02-26 19:04:42 +0100  84)         ConfirmationDialogFragment.ConfirmationDialogFragmentListener, OnRemoteOperationListener  {
7101a042 src/com/owncloud/android/ui/fragment/FilePreviewFragment.java (David A. Velasco 2013-02-14 19:21:09 +0100  85) 
7101a042 src/com/owncloud/android/ui/fragment/FilePreviewFragment.java (David A. Velasco 2013-02-14 19:21:09 +0100  86)     public static final String EXTRA_FILE = "FILE";
7101a042 src/com/owncloud/android/ui/fragment/FilePreviewFragment.java (David A. Velasco 2013-02-14 19:21:09 +0100  87)     public static final String EXTRA_ACCOUNT = "ACCOUNT";
7101a042 src/com/owncloud/android/ui/fragment/FilePreviewFragment.java (David A. Velasco 2013-02-14 19:21:09 +0100  88)     private static final String EXTRA_PLAY_POSITION = "PLAY_POSITION";
2c2efa28 src/com/owncloud/android/ui/preview/PreviewMediaFragment.java (David A. Velasco 2013-03-13 12:21:33 +0100  89)     private static final String EXTRA_PLAYING = "PLAYING";
7101a042 src/com/owncloud/android/ui/fragment/FilePreviewFragment.java (David A. Velasco 2013-02-14 19:21:09 +0100  90) 
7101a042 src/com/owncloud/android/ui/fragment/FilePreviewFragment.java (David A. Velasco 2013-02-14 19:21:09 +0100  91)     private View mView;
7101a042 src/com/owncloud/android/ui/fragment/FilePreviewFragment.java (David A. Velasco 2013-02-14 19:21:09 +0100  92)     private Account mAccount;
f2474ae2 src/com/owncloud/android/ui/fragment/FilePreviewFragment.java (David A. Velasco 2013-02-18 11:23:18 +0100  93)     private FileDataStorageManager mStorageManager;
7101a042 src/com/owncloud/android/ui/fragment/FilePreviewFragment.java (David A. Velasco 2013-02-14 19:21:09 +0100  94)     private ImageView mImagePreview;
7101a042 src/com/owncloud/android/ui/fragment/FilePreviewFragment.java (David A. Velasco 2013-02-14 19:21:09 +0100  95)     private VideoView mVideoPreview;
7101a042 src/com/owncloud/android/ui/fragment/FilePreviewFragment.java (David A. Velasco 2013-02-14 19:21:09 +0100  96)     private int mSavedPlaybackPosition;
7101a042 src/com/owncloud/android/ui/fragment/FilePreviewFragment.java (David A. Velasco 2013-02-14 19:21:09 +0100  97)     
f2474ae2 src/com/owncloud/android/ui/fragment/FilePreviewFragment.java (David A. Velasco 2013-02-18 11:23:18 +0100  98)     private Handler mHandler;
f2474ae2 src/com/owncloud/android/ui/fragment/FilePreviewFragment.java (David A. Velasco 2013-02-18 11:23:18 +0100  99)     private RemoteOperation mLastRemoteOperation;
7101a042 src/com/owncloud/android/ui/fragment/FilePreviewFragment.java (David A. Velasco 2013-02-14 19:21:09 +0100 100)     
7101a042 src/com/owncloud/android/ui/fragment/FilePreviewFragment.java (David A. Velasco 2013-02-14 19:21:09 +0100 101)     private MediaServiceBinder mMediaServiceBinder = null;
d68b3246 src/com/owncloud/android/ui/preview/PreviewMediaFragment.java (David A. Velasco 2013-03-08 13:32:10 +0100 102)     private MediaControlView mMediaController = null;
7101a042 src/com/owncloud/android/ui/fragment/FilePreviewFragment.java (David A. Velasco 2013-02-14 19:21:09 +0100 103)     private MediaServiceConnection mMediaServiceConnection = null;
7101a042 src/com/owncloud/android/ui/fragment/FilePreviewFragment.java (David A. Velasco 2013-02-14 19:21:09 +0100 104)     private VideoHelper mVideoHelper;
2c2efa28 src/com/owncloud/android/ui/preview/PreviewMediaFragment.java (David A. Velasco 2013-03-13 12:21:33 +0100 105)     private boolean mAutoplay;
00328cb8 src/com/owncloud/android/ui/preview/PreviewMediaFragment.java (David A. Velasco 2013-04-30 13:16:08 +0200 106)     public boolean mPrepared;
7101a042 src/com/owncloud/android/ui/fragment/FilePreviewFragment.java (David A. Velasco 2013-02-14 19:21:09 +0100 107)     
2f1aaa8b src/com/owncloud/android/ui/preview/PreviewMediaFragment.java (David A. Velasco 2013-02-26 19:04:42 +0100 108)     private static final String TAG = PreviewMediaFragment.class.getSimpleName();
7101a042 src/com/owncloud/android/ui/fragment/FilePreviewFragment.java (David A. Velasco 2013-02-14 19:21:09 +0100 109) 
7101a042 src/com/owncloud/android/ui/fragment/FilePreviewFragment.java (David A. Velasco 2013-02-14 19:21:09 +0100 110)     
7101a042 src/com/owncloud/android/ui/fragment/FilePreviewFragment.java (David A. Velasco 2013-02-14 19:21:09 +0100 111)     /**
7101a042 src/com/owncloud/android/ui/fragment/FilePreviewFragment.java (David A. Velasco 2013-02-14 19:21:09 +0100 112)      * Creates a fragment to preview a file.
7101a042 src/com/owncloud/android/ui/fragment/FilePreviewFragment.java (David A. Velasco 2013-02-14 19:21:09 +0100 113)      * 
7101a042 src/com/owncloud/android/ui/fragment/FilePreviewFragment.java (David A. Velasco 2013-02-14 19:21:09 +0100 114)      * When 'fileToDetail' or 'ocAccount' are null
7101a042 src/com/owncloud/android/ui/fragment/FilePreviewFragment.java (David A. Velasco 2013-02-14 19:21:09 +0100 115)      * 
7101a042 src/com/owncloud/android/ui/fragment/FilePreviewFragment.java (David A. Velasco 2013-02-14 19:21:09 +0100 116)      * @param fileToDetail      An {@link OCFile} to preview in the fragment
7101a042 src/com/owncloud/android/ui/fragment/FilePreviewFragment.java (David A. Velasco 2013-02-14 19:21:09 +0100 117)      * @param ocAccount         An ownCloud account; needed to start downloads
7101a042 src/com/owncloud/android/ui/fragment/FilePreviewFragment.java (David A. Velasco 2013-02-14 19:21:09 +0100 118)      */
6496b920 src/com/owncloud/android/ui/preview/PreviewMediaFragment.java (David A. Velasco 2013-04-26 14:02:17 +0200 119)     public PreviewMediaFragment(OCFile fileToDetail, Account ocAccount, int startPlaybackPosition, boolean autoplay) {
fd396289 src/com/owncloud/android/ui/preview/PreviewMediaFragment.java (David A. Velasco 2013-05-30 17:53:21 +0200 120)         super(fileToDetail);
7101a042 src/com/owncloud/android/ui/fragment/FilePreviewFragment.java (David A. Velasco 2013-02-14 19:21:09 +0100 121)         mAccount = ocAccount;
6496b920 src/com/owncloud/android/ui/preview/PreviewMediaFragment.java (David A. Velasco 2013-04-26 14:02:17 +0200 122)         mSavedPlaybackPosition = startPlaybackPosition;
f2474ae2 src/com/owncloud/android/ui/fragment/FilePreviewFragment.java (David A. Velasco 2013-02-18 11:23:18 +0100 123)         mStorageManager = null; // we need a context to init this; the container activity is not available yet at this moment 
6496b920 src/com/owncloud/android/ui/preview/PreviewMediaFragment.java (David A. Velasco 2013-04-26 14:02:17 +0200 124)         mAutoplay = autoplay;
7101a042 src/com/owncloud/android/ui/fragment/FilePreviewFragment.java (David A. Velasco 2013-02-14 19:21:09 +0100 125)     }
7101a042 src/com/owncloud/android/ui/fragment/FilePreviewFragment.java (David A. Velasco 2013-02-14 19:21:09 +0100 126)     
7101a042 src/com/owncloud/android/ui/fragment/FilePreviewFragment.java (David A. Velasco 2013-02-14 19:21:09 +0100 127)     
7101a042 src/com/owncloud/android/ui/fragment/FilePreviewFragment.java (David A. Velasco 2013-02-14 19:21:09 +0100 128)     /**
7101a042 src/com/owncloud/android/ui/fragment/FilePreviewFragment.java (David A. Velasco 2013-02-14 19:21:09 +0100 129)      *  Creates an empty fragment for previews.
7101a042 src/com/owncloud/android/ui/fragment/FilePreviewFragment.java (David A. Velasco 2013-02-14 19:21:09 +0100 130)      * 
7101a042 src/com/owncloud/android/ui/fragment/FilePreviewFragment.java (David A. Velasco 2013-02-14 19:21:09 +0100 131)      *  MUST BE KEPT: the system uses it when tries to reinstantiate a fragment automatically (for instance, when the device is turned a aside).
7101a042 src/com/owncloud/android/ui/fragment/FilePreviewFragment.java (David A. Velasco 2013-02-14 19:21:09 +0100 132)      * 
7101a042 src/com/owncloud/android/ui/fragment/FilePreviewFragment.java (David A. Velasco 2013-02-14 19:21:09 +0100 133)      *  DO NOT CALL IT: an {@link OCFile} and {@link Account} must be provided for a successful construction 
7101a042 src/com/owncloud/android/ui/fragment/FilePreviewFragment.java (David A. Velasco 2013-02-14 19:21:09 +0100 134)      */
2f1aaa8b src/com/owncloud/android/ui/preview/PreviewMediaFragment.java (David A. Velasco 2013-02-26 19:04:42 +0100 135)     public PreviewMediaFragment() {
fd396289 src/com/owncloud/android/ui/preview/PreviewMediaFragment.java (David A. Velasco 2013-05-30 17:53:21 +0200 136)         super();
7101a042 src/com/owncloud/android/ui/fragment/FilePreviewFragment.java (David A. Velasco 2013-02-14 19:21:09 +0100 137)         mAccount = null;
7101a042 src/com/owncloud/android/ui/fragment/FilePreviewFragment.java (David A. Velasco 2013-02-14 19:21:09 +0100 138)         mSavedPlaybackPosition = 0;
f2474ae2 src/com/owncloud/android/ui/fragment/FilePreviewFragment.java (David A. Velasco 2013-02-18 11:23:18 +0100 139)         mStorageManager = null;
2c2efa28 src/com/owncloud/android/ui/preview/PreviewMediaFragment.java (David A. Velasco 2013-03-13 12:21:33 +0100 140)         mAutoplay = true;
7101a042 src/com/owncloud/android/ui/fragment/FilePreviewFragment.java (David A. Velasco 2013-02-14 19:21:09 +0100 141)     }
7101a042 src/com/owncloud/android/ui/fragment/FilePreviewFragment.java (David A. Velasco 2013-02-14 19:21:09 +0100 142)     
7101a042 src/com/owncloud/android/ui/fragment/FilePreviewFragment.java (David A. Velasco 2013-02-14 19:21:09 +0100 143)     
7101a042 src/com/owncloud/android/ui/fragment/FilePreviewFragment.java (David A. Velasco 2013-02-14 19:21:09 +0100 144)     /**
7101a042 src/com/owncloud/android/ui/fragment/FilePreviewFragment.java (David A. Velasco 2013-02-14 19:21:09 +0100 145)      * {@inheritDoc}
7101a042 src/com/owncloud/android/ui/fragment/FilePreviewFragment.java (David A. Velasco 2013-02-14 19:21:09 +0100 146)      */
7101a042 src/com/owncloud/android/ui/fragment/FilePreviewFragment.java (David A. Velasco 2013-02-14 19:21:09 +0100 147)     @Override
7101a042 src/com/owncloud/android/ui/fragment/FilePreviewFragment.java (David A. Velasco 2013-02-14 19:21:09 +0100 148)     public void onCreate(Bundle savedInstanceState) {
7101a042 src/com/owncloud/android/ui/fragment/FilePreviewFragment.java (David A. Velasco 2013-02-14 19:21:09 +0100 149)         super.onCreate(savedInstanceState);
f2474ae2 src/com/owncloud/android/ui/fragment/FilePreviewFragment.java (David A. Velasco 2013-02-18 11:23:18 +0100 150)         mHandler = new Handler();
f2474ae2 src/com/owncloud/android/ui/fragment/FilePreviewFragment.java (David A. Velasco 2013-02-18 11:23:18 +0100 151)         setHasOptionsMenu(true);
7101a042 src/com/owncloud/android/ui/fragment/FilePreviewFragment.java (David A. Velasco 2013-02-14 19:21:09 +0100 152)     }
7101a042 src/com/owncloud/android/ui/fragment/FilePreviewFragment.java (David A. Velasco 2013-02-14 19:21:09 +0100 153)     
7101a042 src/com/owncloud/android/ui/fragment/FilePreviewFragment.java (David A. Velasco 2013-02-14 19:21:09 +0100 154) 
7101a042 src/com/owncloud/android/ui/fragment/FilePreviewFragment.java (David A. Velasco 2013-02-14 19:21:09 +0100 155)     /**
7101a042 src/com/owncloud/android/ui/fragment/FilePreviewFragment.java (David A. Velasco 2013-02-14 19:21:09 +0100 156)      * {@inheritDoc}
7101a042 src/com/owncloud/android/ui/fragment/FilePreviewFragment.java (David A. Velasco 2013-02-14 19:21:09 +0100 157)      */
7101a042 src/com/owncloud/android/ui/fragment/FilePreviewFragment.java (David A. Velasco 2013-02-14 19:21:09 +0100 158)     @Override
7101a042 src/com/owncloud/android/ui/fragment/FilePreviewFragment.java (David A. Velasco 2013-02-14 19:21:09 +0100 159)     public View onCreateView(LayoutInflater inflater, ViewGroup container,
7101a042 src/com/owncloud/android/ui/fragment/FilePreviewFragment.java (David A. Velasco 2013-02-14 19:21:09 +0100 160)             Bundle savedInstanceState) {
7101a042 src/com/owncloud/android/ui/fragment/FilePreviewFragment.java (David A. Velasco 2013-02-14 19:21:09 +0100 161)         super.onCreateView(inflater, container, savedInstanceState);
00328cb8 src/com/owncloud/android/ui/preview/PreviewMediaFragment.java (David A. Velasco 2013-04-30 13:16:08 +0200 162)         Log_OC.e(TAG, "onCreateView");
00328cb8 src/com/owncloud/android/ui/preview/PreviewMediaFragment.java (David A. Velasco 2013-04-30 13:16:08 +0200 163) 
7101a042 src/com/owncloud/android/ui/fragment/FilePreviewFragment.java (David A. Velasco 2013-02-14 19:21:09 +0100 164)         
7101a042 src/com/owncloud/android/ui/fragment/FilePreviewFragment.java (David A. Velasco 2013-02-14 19:21:09 +0100 165)         mView = inflater.inflate(R.layout.file_preview, container, false);
7101a042 src/com/owncloud/android/ui/fragment/FilePreviewFragment.java (David A. Velasco 2013-02-14 19:21:09 +0100 166)         
7101a042 src/com/owncloud/android/ui/fragment/FilePreviewFragment.java (David A. Velasco 2013-02-14 19:21:09 +0100 167)         mImagePreview = (ImageView)mView.findViewById(R.id.image_preview);
7101a042 src/com/owncloud/android/ui/fragment/FilePreviewFragment.java (David A. Velasco 2013-02-14 19:21:09 +0100 168)         mVideoPreview = (VideoView)mView.findViewById(R.id.video_preview);
bc5c3f5f src/com/owncloud/android/ui/preview/PreviewMediaFragment.java (David A. Velasco 2013-03-14 16:55:57 +0100 169)         mVideoPreview.setOnTouchListener(this);
7101a042 src/com/owncloud/android/ui/fragment/FilePreviewFragment.java (David A. Velasco 2013-02-14 19:21:09 +0100 170)         
d68b3246 src/com/owncloud/android/ui/preview/PreviewMediaFragment.java (David A. Velasco 2013-03-08 13:32:10 +0100 171)         mMediaController = (MediaControlView)mView.findViewById(R.id.media_controller);
d68b3246 src/com/owncloud/android/ui/preview/PreviewMediaFragment.java (David A. Velasco 2013-03-08 13:32:10 +0100 172)         
7101a042 src/com/owncloud/android/ui/fragment/FilePreviewFragment.java (David A. Velasco 2013-02-14 19:21:09 +0100 173)         return mView;
7101a042 src/com/owncloud/android/ui/fragment/FilePreviewFragment.java (David A. Velasco 2013-02-14 19:21:09 +0100 174)     }
7101a042 src/com/owncloud/android/ui/fragment/FilePreviewFragment.java (David A. Velasco 2013-02-14 19:21:09 +0100 175)     
7101a042 src/com/owncloud/android/ui/fragment/FilePreviewFragment.java (David A. Velasco 2013-02-14 19:21:09 +0100 176) 
7101a042 src/com/owncloud/android/ui/fragment/FilePreviewFragment.java (David A. Velasco 2013-02-14 19:21:09 +0100 177)     /**
7101a042 src/com/owncloud/android/ui/fragment/FilePreviewFragment.java (David A. Velasco 2013-02-14 19:21:09 +0100 178)      * {@inheritDoc}
7101a042 src/com/owncloud/android/ui/fragment/FilePreviewFragment.java (David A. Velasco 2013-02-14 19:21:09 +0100 179)      */
7101a042 src/com/owncloud/android/ui/fragment/FilePreviewFragment.java (David A. Velasco 2013-02-14 19:21:09 +0100 180)     @Override
7101a042 src/com/owncloud/android/ui/fragment/FilePreviewFragment.java (David A. Velasco 2013-02-14 19:21:09 +0100 181)     public void onAttach(Activity activity) {
7101a042 src/com/owncloud/android/ui/fragment/FilePreviewFragment.java (David A. Velasco 2013-02-14 19:21:09 +0100 182)         super.onAttach(activity);
00328cb8 src/com/owncloud/android/ui/preview/PreviewMediaFragment.java (David A. Velasco 2013-04-30 13:16:08 +0200 183)         Log_OC.e(TAG, "onAttach");
00328cb8 src/com/owncloud/android/ui/preview/PreviewMediaFragment.java (David A. Velasco 2013-04-30 13:16:08 +0200 184)         
f2474ae2 src/com/owncloud/android/ui/fragment/FilePreviewFragment.java (David A. Velasco 2013-02-18 11:23:18 +0100 185)         if (!(activity instanceof FileFragment.ContainerActivity))
f2474ae2 src/com/owncloud/android/ui/fragment/FilePreviewFragment.java (David A. Velasco 2013-02-18 11:23:18 +0100 186)             throw new ClassCastException(activity.toString() + " must implement " + FileFragment.ContainerActivity.class.getSimpleName());
7101a042 src/com/owncloud/android/ui/fragment/FilePreviewFragment.java (David A. Velasco 2013-02-14 19:21:09 +0100 187)     }
7101a042 src/com/owncloud/android/ui/fragment/FilePreviewFragment.java (David A. Velasco 2013-02-14 19:21:09 +0100 188)     
7101a042 src/com/owncloud/android/ui/fragment/FilePreviewFragment.java (David A. Velasco 2013-02-14 19:21:09 +0100 189)     
7101a042 src/com/owncloud/android/ui/fragment/FilePreviewFragment.java (David A. Velasco 2013-02-14 19:21:09 +0100 190)     /**
7101a042 src/com/owncloud/android/ui/fragment/FilePreviewFragment.java (David A. Velasco 2013-02-14 19:21:09 +0100 191)      * {@inheritDoc}
7101a042 src/com/owncloud/android/ui/fragment/FilePreviewFragment.java (David A. Velasco 2013-02-14 19:21:09 +0100 192)      */
7101a042 src/com/owncloud/android/ui/fragment/FilePreviewFragment.java (David A. Velasco 2013-02-14 19:21:09 +0100 193)     @Override
7101a042 src/com/owncloud/android/ui/fragment/FilePreviewFragment.java (David A. Velasco 2013-02-14 19:21:09 +0100 194)     public void onActivityCreated(Bundle savedInstanceState) {
7101a042 src/com/owncloud/android/ui/fragment/FilePreviewFragment.java (David A. Velasco 2013-02-14 19:21:09 +0100 195)         super.onActivityCreated(savedInstanceState);
00328cb8 src/com/owncloud/android/ui/preview/PreviewMediaFragment.java (David A. Velasco 2013-04-30 13:16:08 +0200 196)         Log_OC.e(TAG, "onActivityCreated");
7101a042 src/com/owncloud/android/ui/fragment/FilePreviewFragment.java (David A. Velasco 2013-02-14 19:21:09 +0100 197) 
f2474ae2 src/com/owncloud/android/ui/fragment/FilePreviewFragment.java (David A. Velasco 2013-02-18 11:23:18 +0100 198)         mStorageManager = new FileDataStorageManager(mAccount, getActivity().getApplicationContext().getContentResolver());
7101a042 src/com/owncloud/android/ui/fragment/FilePreviewFragment.java (David A. Velasco 2013-02-14 19:21:09 +0100 199)         if (savedInstanceState != null) {
fd396289 src/com/owncloud/android/ui/preview/PreviewMediaFragment.java (David A. Velasco 2013-05-30 17:53:21 +0200 200)             setFile((OCFile)savedInstanceState.getParcelable(PreviewMediaFragment.EXTRA_FILE));
2f1aaa8b src/com/owncloud/android/ui/preview/PreviewMediaFragment.java (David A. Velasco 2013-02-26 19:04:42 +0100 201)             mAccount = savedInstanceState.getParcelable(PreviewMediaFragment.EXTRA_ACCOUNT);
2f1aaa8b src/com/owncloud/android/ui/preview/PreviewMediaFragment.java (David A. Velasco 2013-02-26 19:04:42 +0100 202)             mSavedPlaybackPosition = savedInstanceState.getInt(PreviewMediaFragment.EXTRA_PLAY_POSITION);
2c2efa28 src/com/owncloud/android/ui/preview/PreviewMediaFragment.java (David A. Velasco 2013-03-13 12:21:33 +0100 203)             mAutoplay = savedInstanceState.getBoolean(PreviewMediaFragment.EXTRA_PLAYING);
7101a042 src/com/owncloud/android/ui/fragment/FilePreviewFragment.java (David A. Velasco 2013-02-14 19:21:09 +0100 204)             
7101a042 src/com/owncloud/android/ui/fragment/FilePreviewFragment.java (David A. Velasco 2013-02-14 19:21:09 +0100 205)         }
fd396289 src/com/owncloud/android/ui/preview/PreviewMediaFragment.java (David A. Velasco 2013-05-30 17:53:21 +0200 206)         OCFile file = getFile();
fd396289 src/com/owncloud/android/ui/preview/PreviewMediaFragment.java (David A. Velasco 2013-05-30 17:53:21 +0200 207)         if (file == null) {
7101a042 src/com/owncloud/android/ui/fragment/FilePreviewFragment.java (David A. Velasco 2013-02-14 19:21:09 +0100 208)             throw new IllegalStateException("Instanced with a NULL OCFile");
7101a042 src/com/owncloud/android/ui/fragment/FilePreviewFragment.java (David A. Velasco 2013-02-14 19:21:09 +0100 209)         }
7101a042 src/com/owncloud/android/ui/fragment/FilePreviewFragment.java (David A. Velasco 2013-02-14 19:21:09 +0100 210)         if (mAccount == null) {
7101a042 src/com/owncloud/android/ui/fragment/FilePreviewFragment.java (David A. Velasco 2013-02-14 19:21:09 +0100 211)             throw new IllegalStateException("Instanced with a NULL ownCloud Account");
7101a042 src/com/owncloud/android/ui/fragment/FilePreviewFragment.java (David A. Velasco 2013-02-14 19:21:09 +0100 212)         }
fd396289 src/com/owncloud/android/ui/preview/PreviewMediaFragment.java (David A. Velasco 2013-05-30 17:53:21 +0200 213)         if (!file.isDown()) {
7101a042 src/com/owncloud/android/ui/fragment/FilePreviewFragment.java (David A. Velasco 2013-02-14 19:21:09 +0100 214)             throw new IllegalStateException("There is no local file to preview");
7101a042 src/com/owncloud/android/ui/fragment/FilePreviewFragment.java (David A. Velasco 2013-02-14 19:21:09 +0100 215)         }
fd396289 src/com/owncloud/android/ui/preview/PreviewMediaFragment.java (David A. Velasco 2013-05-30 17:53:21 +0200 216)         if (file.isVideo()) {
7101a042 src/com/owncloud/android/ui/fragment/FilePreviewFragment.java (David A. Velasco 2013-02-14 19:21:09 +0100 217)             mVideoPreview.setVisibility(View.VISIBLE);
7101a042 src/com/owncloud/android/ui/fragment/FilePreviewFragment.java (David A. Velasco 2013-02-14 19:21:09 +0100 218)             mImagePreview.setVisibility(View.GONE);
7101a042 src/com/owncloud/android/ui/fragment/FilePreviewFragment.java (David A. Velasco 2013-02-14 19:21:09 +0100 219)             prepareVideo();
7101a042 src/com/owncloud/android/ui/fragment/FilePreviewFragment.java (David A. Velasco 2013-02-14 19:21:09 +0100 220)             
7101a042 src/com/owncloud/android/ui/fragment/FilePreviewFragment.java (David A. Velasco 2013-02-14 19:21:09 +0100 221)         } else {
7101a042 src/com/owncloud/android/ui/fragment/FilePreviewFragment.java (David A. Velasco 2013-02-14 19:21:09 +0100 222)             mVideoPreview.setVisibility(View.GONE);
7101a042 src/com/owncloud/android/ui/fragment/FilePreviewFragment.java (David A. Velasco 2013-02-14 19:21:09 +0100 223)             mImagePreview.setVisibility(View.VISIBLE);
7101a042 src/com/owncloud/android/ui/fragment/FilePreviewFragment.java (David A. Velasco 2013-02-14 19:21:09 +0100 224)         }
7101a042 src/com/owncloud/android/ui/fragment/FilePreviewFragment.java (David A. Velasco 2013-02-14 19:21:09 +0100 225)         
7101a042 src/com/owncloud/android/ui/fragment/FilePreviewFragment.java (David A. Velasco 2013-02-14 19:21:09 +0100 226)     }
7101a042 src/com/owncloud/android/ui/fragment/FilePreviewFragment.java (David A. Velasco 2013-02-14 19:21:09 +0100 227)         
7101a042 src/com/owncloud/android/ui/fragment/FilePreviewFragment.java (David A. Velasco 2013-02-14 19:21:09 +0100 228) 
7101a042 src/com/owncloud/android/ui/fragment/FilePreviewFragment.java (David A. Velasco 2013-02-14 19:21:09 +0100 229)     /**
7101a042 src/com/owncloud/android/ui/fragment/FilePreviewFragment.java (David A. Velasco 2013-02-14 19:21:09 +0100 230)      * {@inheritDoc}
7101a042 src/com/owncloud/android/ui/fragment/FilePreviewFragment.java (David A. Velasco 2013-02-14 19:21:09 +0100 231)      */
7101a042 src/com/owncloud/android/ui/fragment/FilePreviewFragment.java (David A. Velasco 2013-02-14 19:21:09 +0100 232)     @Override
7101a042 src/com/owncloud/android/ui/fragment/FilePreviewFragment.java (David A. Velasco 2013-02-14 19:21:09 +0100 233)     public void onSaveInstanceState(Bundle outState) {
7101a042 src/com/owncloud/android/ui/fragment/FilePreviewFragment.java (David A. Velasco 2013-02-14 19:21:09 +0100 234)         super.onSaveInstanceState(outState);
00328cb8 src/com/owncloud/android/ui/preview/PreviewMediaFragment.java (David A. Velasco 2013-04-30 13:16:08 +0200 235)         Log_OC.e(TAG, "onSaveInstanceState");
00328cb8 src/com/owncloud/android/ui/preview/PreviewMediaFragment.java (David A. Velasco 2013-04-30 13:16:08 +0200 236)         
fd396289 src/com/owncloud/android/ui/preview/PreviewMediaFragment.java (David A. Velasco 2013-05-30 17:53:21 +0200 237)         outState.putParcelable(PreviewMediaFragment.EXTRA_FILE, getFile());
2f1aaa8b src/com/owncloud/android/ui/preview/PreviewMediaFragment.java (David A. Velasco 2013-02-26 19:04:42 +0100 238)         outState.putParcelable(PreviewMediaFragment.EXTRA_ACCOUNT, mAccount);
7101a042 src/com/owncloud/android/ui/fragment/FilePreviewFragment.java (David A. Velasco 2013-02-14 19:21:09 +0100 239)         
fd396289 src/com/owncloud/android/ui/preview/PreviewMediaFragment.java (David A. Velasco 2013-05-30 17:53:21 +0200 240)         if (getFile().isVideo()) {
2a426514 src/com/owncloud/android/ui/preview/PreviewMediaFragment.java (David A. Velasco 2013-04-11 16:56:51 +0200 241)             mSavedPlaybackPosition = mVideoPreview.getCurrentPosition();
2a426514 src/com/owncloud/android/ui/preview/PreviewMediaFragment.java (David A. Velasco 2013-04-11 16:56:51 +0200 242)             mAutoplay = mVideoPreview.isPlaying();
2a426514 src/com/owncloud/android/ui/preview/PreviewMediaFragment.java (David A. Velasco 2013-04-11 16:56:51 +0200 243)             outState.putInt(PreviewMediaFragment.EXTRA_PLAY_POSITION , mSavedPlaybackPosition);
2a426514 src/com/owncloud/android/ui/preview/PreviewMediaFragment.java (David A. Velasco 2013-04-11 16:56:51 +0200 244)             outState.putBoolean(PreviewMediaFragment.EXTRA_PLAYING , mAutoplay);
fa801791 src/com/owncloud/android/ui/preview/PreviewMediaFragment.java (David A. Velasco 2013-03-22 13:04:57 +0100 245)         } else {
fa801791 src/com/owncloud/android/ui/preview/PreviewMediaFragment.java (David A. Velasco 2013-03-22 13:04:57 +0100 246)             outState.putInt(PreviewMediaFragment.EXTRA_PLAY_POSITION , mMediaServiceBinder.getCurrentPosition());
fa801791 src/com/owncloud/android/ui/preview/PreviewMediaFragment.java (David A. Velasco 2013-03-22 13:04:57 +0100 247)             outState.putBoolean(PreviewMediaFragment.EXTRA_PLAYING , mMediaServiceBinder.isPlaying());
7101a042 src/com/owncloud/android/ui/fragment/FilePreviewFragment.java (David A. Velasco 2013-02-14 19:21:09 +0100 248)         }
7101a042 src/com/owncloud/android/ui/fragment/FilePreviewFragment.java (David A. Velasco 2013-02-14 19:21:09 +0100 249)     }
7101a042 src/com/owncloud/android/ui/fragment/FilePreviewFragment.java (David A. Velasco 2013-02-14 19:21:09 +0100 250)     
7101a042 src/com/owncloud/android/ui/fragment/FilePreviewFragment.java (David A. Velasco 2013-02-14 19:21:09 +0100 251) 
7101a042 src/com/owncloud/android/ui/fragment/FilePreviewFragment.java (David A. Velasco 2013-02-14 19:21:09 +0100 252)     @Override
7101a042 src/com/owncloud/android/ui/fragment/FilePreviewFragment.java (David A. Velasco 2013-02-14 19:21:09 +0100 253)     public void onStart() {
7101a042 src/com/owncloud/android/ui/fragment/FilePreviewFragment.java (David A. Velasco 2013-02-14 19:21:09 +0100 254)         super.onStart();
00328cb8 src/com/owncloud/android/ui/preview/PreviewMediaFragment.java (David A. Velasco 2013-04-30 13:16:08 +0200 255)         Log_OC.e(TAG, "onStart");
7101a042 src/com/owncloud/android/ui/fragment/FilePreviewFragment.java (David A. Velasco 2013-02-14 19:21:09 +0100 256) 
fd396289 src/com/owncloud/android/ui/preview/PreviewMediaFragment.java (David A. Velasco 2013-05-30 17:53:21 +0200 257)         OCFile file = getFile();
fd396289 src/com/owncloud/android/ui/preview/PreviewMediaFragment.java (David A. Velasco 2013-05-30 17:53:21 +0200 258)         if (file != null) {
fd396289 src/com/owncloud/android/ui/preview/PreviewMediaFragment.java (David A. Velasco 2013-05-30 17:53:21 +0200 259)            if (file.isAudio()) {
7101a042 src/com/owncloud/android/ui/fragment/FilePreviewFragment.java (David A. Velasco 2013-02-14 19:21:09 +0100 260)                bindMediaService();
7101a042 src/com/owncloud/android/ui/fragment/FilePreviewFragment.java (David A. Velasco 2013-02-14 19:21:09 +0100 261)                
fd396289 src/com/owncloud/android/ui/preview/PreviewMediaFragment.java (David A. Velasco 2013-05-30 17:53:21 +0200 262)            } else if (file.isVideo()) {
3aacc7c0 src/com/owncloud/android/ui/preview/PreviewMediaFragment.java (David A. Velasco 2013-03-07 17:07:58 +0100 263)                stopAudio();
7101a042 src/com/owncloud/android/ui/fragment/FilePreviewFragment.java (David A. Velasco 2013-02-14 19:21:09 +0100 264)                playVideo(); 
7101a042 src/com/owncloud/android/ui/fragment/FilePreviewFragment.java (David A. Velasco 2013-02-14 19:21:09 +0100 265)            }
7101a042 src/com/owncloud/android/ui/fragment/FilePreviewFragment.java (David A. Velasco 2013-02-14 19:21:09 +0100 266)         }
7101a042 src/com/owncloud/android/ui/fragment/FilePreviewFragment.java (David A. Velasco 2013-02-14 19:21:09 +0100 267)     }
7101a042 src/com/owncloud/android/ui/fragment/FilePreviewFragment.java (David A. Velasco 2013-02-14 19:21:09 +0100 268)     
7101a042 src/com/owncloud/android/ui/fragment/FilePreviewFragment.java (David A. Velasco 2013-02-14 19:21:09 +0100 269)     
3aacc7c0 src/com/owncloud/android/ui/preview/PreviewMediaFragment.java (David A. Velasco 2013-03-07 17:07:58 +0100 270)     private void stopAudio() {
3aacc7c0 src/com/owncloud/android/ui/preview/PreviewMediaFragment.java (David A. Velasco 2013-03-07 17:07:58 +0100 271)         Intent i = new Intent(getSherlockActivity(), MediaService.class);
3aacc7c0 src/com/owncloud/android/ui/preview/PreviewMediaFragment.java (David A. Velasco 2013-03-07 17:07:58 +0100 272)         i.setAction(MediaService.ACTION_STOP_ALL);
3aacc7c0 src/com/owncloud/android/ui/preview/PreviewMediaFragment.java (David A. Velasco 2013-03-07 17:07:58 +0100 273)         getSherlockActivity().startService(i);
3aacc7c0 src/com/owncloud/android/ui/preview/PreviewMediaFragment.java (David A. Velasco 2013-03-07 17:07:58 +0100 274)     }
3aacc7c0 src/com/owncloud/android/ui/preview/PreviewMediaFragment.java (David A. Velasco 2013-03-07 17:07:58 +0100 275) 
3aacc7c0 src/com/owncloud/android/ui/preview/PreviewMediaFragment.java (David A. Velasco 2013-03-07 17:07:58 +0100 276) 
f2474ae2 src/com/owncloud/android/ui/fragment/FilePreviewFragment.java (David A. Velasco 2013-02-18 11:23:18 +0100 277)     /**
f2474ae2 src/com/owncloud/android/ui/fragment/FilePreviewFragment.java (David A. Velasco 2013-02-18 11:23:18 +0100 278)      * {@inheritDoc}
f2474ae2 src/com/owncloud/android/ui/fragment/FilePreviewFragment.java (David A. Velasco 2013-02-18 11:23:18 +0100 279)      */
f2474ae2 src/com/owncloud/android/ui/fragment/FilePreviewFragment.java (David A. Velasco 2013-02-18 11:23:18 +0100 280)     @Override
f2474ae2 src/com/owncloud/android/ui/fragment/FilePreviewFragment.java (David A. Velasco 2013-02-18 11:23:18 +0100 281)     public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
f2474ae2 src/com/owncloud/android/ui/fragment/FilePreviewFragment.java (David A. Velasco 2013-02-18 11:23:18 +0100 282)         super.onCreateOptionsMenu(menu, inflater);
f2474ae2 src/com/owncloud/android/ui/fragment/FilePreviewFragment.java (David A. Velasco 2013-02-18 11:23:18 +0100 283) 
f2474ae2 src/com/owncloud/android/ui/fragment/FilePreviewFragment.java (David A. Velasco 2013-02-18 11:23:18 +0100 284)         inflater.inflate(R.menu.file_actions_menu, menu);
f2474ae2 src/com/owncloud/android/ui/fragment/FilePreviewFragment.java (David A. Velasco 2013-02-18 11:23:18 +0100 285)         List<Integer> toHide = new ArrayList<Integer>();    
f2474ae2 src/com/owncloud/android/ui/fragment/FilePreviewFragment.java (David A. Velasco 2013-02-18 11:23:18 +0100 286)         
f2474ae2 src/com/owncloud/android/ui/fragment/FilePreviewFragment.java (David A. Velasco 2013-02-18 11:23:18 +0100 287)         MenuItem item = null;
f2474ae2 src/com/owncloud/android/ui/fragment/FilePreviewFragment.java (David A. Velasco 2013-02-18 11:23:18 +0100 288)         toHide.add(R.id.action_cancel_download);
f2474ae2 src/com/owncloud/android/ui/fragment/FilePreviewFragment.java (David A. Velasco 2013-02-18 11:23:18 +0100 289)         toHide.add(R.id.action_cancel_upload);
f2474ae2 src/com/owncloud/android/ui/fragment/FilePreviewFragment.java (David A. Velasco 2013-02-18 11:23:18 +0100 290)         toHide.add(R.id.action_download_file);
803568ab src/com/owncloud/android/ui/preview/PreviewMediaFragment.java (David A. Velasco 2013-04-22 14:34:42 +0200 291)         toHide.add(R.id.action_sync_file);
f2474ae2 src/com/owncloud/android/ui/fragment/FilePreviewFragment.java (David A. Velasco 2013-02-18 11:23:18 +0100 292)         toHide.add(R.id.action_rename_file);    // by now
f2474ae2 src/com/owncloud/android/ui/fragment/FilePreviewFragment.java (David A. Velasco 2013-02-18 11:23:18 +0100 293) 
f2474ae2 src/com/owncloud/android/ui/fragment/FilePreviewFragment.java (David A. Velasco 2013-02-18 11:23:18 +0100 294)         for (int i : toHide) {
f2474ae2 src/com/owncloud/android/ui/fragment/FilePreviewFragment.java (David A. Velasco 2013-02-18 11:23:18 +0100 295)             item = menu.findItem(i);
f2474ae2 src/com/owncloud/android/ui/fragment/FilePreviewFragment.java (David A. Velasco 2013-02-18 11:23:18 +0100 296)             if (item != null) {
f2474ae2 src/com/owncloud/android/ui/fragment/FilePreviewFragment.java (David A. Velasco 2013-02-18 11:23:18 +0100 297)                 item.setVisible(false);
f2474ae2 src/com/owncloud/android/ui/fragment/FilePreviewFragment.java (David A. Velasco 2013-02-18 11:23:18 +0100 298)                 item.setEnabled(false);
f2474ae2 src/com/owncloud/android/ui/fragment/FilePreviewFragment.java (David A. Velasco 2013-02-18 11:23:18 +0100 299)             }
f2474ae2 src/com/owncloud/android/ui/fragment/FilePreviewFragment.java (David A. Velasco 2013-02-18 11:23:18 +0100 300)         }
f2474ae2 src/com/owncloud/android/ui/fragment/FilePreviewFragment.java (David A. Velasco 2013-02-18 11:23:18 +0100 301)         
f2474ae2 src/com/owncloud/android/ui/fragment/FilePreviewFragment.java (David A. Velasco 2013-02-18 11:23:18 +0100 302)     }
f2474ae2 src/com/owncloud/android/ui/fragment/FilePreviewFragment.java (David A. Velasco 2013-02-18 11:23:18 +0100 303) 
f2474ae2 src/com/owncloud/android/ui/fragment/FilePreviewFragment.java (David A. Velasco 2013-02-18 11:23:18 +0100 304)     
f2474ae2 src/com/owncloud/android/ui/fragment/FilePreviewFragment.java (David A. Velasco 2013-02-18 11:23:18 +0100 305)     /**
f2474ae2 src/com/owncloud/android/ui/fragment/FilePreviewFragment.java (David A. Velasco 2013-02-18 11:23:18 +0100 306)      * {@inheritDoc}
f2474ae2 src/com/owncloud/android/ui/fragment/FilePreviewFragment.java (David A. Velasco 2013-02-18 11:23:18 +0100 307)      */
f2474ae2 src/com/owncloud/android/ui/fragment/FilePreviewFragment.java (David A. Velasco 2013-02-18 11:23:18 +0100 308)     @Override
f2474ae2 src/com/owncloud/android/ui/fragment/FilePreviewFragment.java (David A. Velasco 2013-02-18 11:23:18 +0100 309)     public boolean onOptionsItemSelected(MenuItem item) {
f2474ae2 src/com/owncloud/android/ui/fragment/FilePreviewFragment.java (David A. Velasco 2013-02-18 11:23:18 +0100 310)         switch (item.getItemId()) {
f2474ae2 src/com/owncloud/android/ui/fragment/FilePreviewFragment.java (David A. Velasco 2013-02-18 11:23:18 +0100 311)             case R.id.action_open_file_with: {
f2474ae2 src/com/owncloud/android/ui/fragment/FilePreviewFragment.java (David A. Velasco 2013-02-18 11:23:18 +0100 312)                 openFile();
f2474ae2 src/com/owncloud/android/ui/fragment/FilePreviewFragment.java (David A. Velasco 2013-02-18 11:23:18 +0100 313)                 return true;
f2474ae2 src/com/owncloud/android/ui/fragment/FilePreviewFragment.java (David A. Velasco 2013-02-18 11:23:18 +0100 314)             }
f2474ae2 src/com/owncloud/android/ui/fragment/FilePreviewFragment.java (David A. Velasco 2013-02-18 11:23:18 +0100 315)             case R.id.action_remove_file: {
f2474ae2 src/com/owncloud/android/ui/fragment/FilePreviewFragment.java (David A. Velasco 2013-02-18 11:23:18 +0100 316)                 removeFile();
f2474ae2 src/com/owncloud/android/ui/fragment/FilePreviewFragment.java (David A. Velasco 2013-02-18 11:23:18 +0100 317)                 return true;
f2474ae2 src/com/owncloud/android/ui/fragment/FilePreviewFragment.java (David A. Velasco 2013-02-18 11:23:18 +0100 318)             }
f2474ae2 src/com/owncloud/android/ui/fragment/FilePreviewFragment.java (David A. Velasco 2013-02-18 11:23:18 +0100 319)             case R.id.action_see_details: {
f2474ae2 src/com/owncloud/android/ui/fragment/FilePreviewFragment.java (David A. Velasco 2013-02-18 11:23:18 +0100 320)                 seeDetails();
f2474ae2 src/com/owncloud/android/ui/fragment/FilePreviewFragment.java (David A. Velasco 2013-02-18 11:23:18 +0100 321)                 return true;
f2474ae2 src/com/owncloud/android/ui/fragment/FilePreviewFragment.java (David A. Velasco 2013-02-18 11:23:18 +0100 322)             }
f2474ae2 src/com/owncloud/android/ui/fragment/FilePreviewFragment.java (David A. Velasco 2013-02-18 11:23:18 +0100 323)             
f2474ae2 src/com/owncloud/android/ui/fragment/FilePreviewFragment.java (David A. Velasco 2013-02-18 11:23:18 +0100 324)             default:
f2474ae2 src/com/owncloud/android/ui/fragment/FilePreviewFragment.java (David A. Velasco 2013-02-18 11:23:18 +0100 325)                 return false;
f2474ae2 src/com/owncloud/android/ui/fragment/FilePreviewFragment.java (David A. Velasco 2013-02-18 11:23:18 +0100 326)         }
f2474ae2 src/com/owncloud/android/ui/fragment/FilePreviewFragment.java (David A. Velasco 2013-02-18 11:23:18 +0100 327)     }
f2474ae2 src/com/owncloud/android/ui/fragment/FilePreviewFragment.java (David A. Velasco 2013-02-18 11:23:18 +0100 328) 
f2474ae2 src/com/owncloud/android/ui/fragment/FilePreviewFragment.java (David A. Velasco 2013-02-18 11:23:18 +0100 329)     
f2474ae2 src/com/owncloud/android/ui/fragment/FilePreviewFragment.java (David A. Velasco 2013-02-18 11:23:18 +0100 330)     private void seeDetails() {
f2474ae2 src/com/owncloud/android/ui/fragment/FilePreviewFragment.java (David A. Velasco 2013-02-18 11:23:18 +0100 331)         stopPreview(false);
fd396289 src/com/owncloud/android/ui/preview/PreviewMediaFragment.java (David A. Velasco 2013-05-30 17:53:21 +0200 332)         ((FileFragment.ContainerActivity)getActivity()).showDetails(getFile());        
f2474ae2 src/com/owncloud/android/ui/fragment/FilePreviewFragment.java (David A. Velasco 2013-02-18 11:23:18 +0100 333)     }
f2474ae2 src/com/owncloud/android/ui/fragment/FilePreviewFragment.java (David A. Velasco 2013-02-18 11:23:18 +0100 334) 
f2474ae2 src/com/owncloud/android/ui/fragment/FilePreviewFragment.java (David A. Velasco 2013-02-18 11:23:18 +0100 335) 
7101a042 src/com/owncloud/android/ui/fragment/FilePreviewFragment.java (David A. Velasco 2013-02-14 19:21:09 +0100 336)     private void prepareVideo() {
7101a042 src/com/owncloud/android/ui/fragment/FilePreviewFragment.java (David A. Velasco 2013-02-14 19:21:09 +0100 337)         // create helper to get more control on the playback
7ffe1196 src/com/owncloud/android/ui/fragment/FilePreviewFragment.java (David A. Velasco 2013-02-15 09:58:53 +0100 338)         mVideoHelper = new VideoHelper();
7101a042 src/com/owncloud/android/ui/fragment/FilePreviewFragment.java (David A. Velasco 2013-02-14 19:21:09 +0100 339)         mVideoPreview.setOnPreparedListener(mVideoHelper);
7101a042 src/com/owncloud/android/ui/fragment/FilePreviewFragment.java (David A. Velasco 2013-02-14 19:21:09 +0100 340)         mVideoPreview.setOnCompletionListener(mVideoHelper);
7101a042 src/com/owncloud/android/ui/fragment/FilePreviewFragment.java (David A. Velasco 2013-02-14 19:21:09 +0100 341)         mVideoPreview.setOnErrorListener(mVideoHelper);
7101a042 src/com/owncloud/android/ui/fragment/FilePreviewFragment.java (David A. Velasco 2013-02-14 19:21:09 +0100 342)     }
7101a042 src/com/owncloud/android/ui/fragment/FilePreviewFragment.java (David A. Velasco 2013-02-14 19:21:09 +0100 343)     
7101a042 src/com/owncloud/android/ui/fragment/FilePreviewFragment.java (David A. Velasco 2013-02-14 19:21:09 +0100 344)     private void playVideo() {
ca8a1985 src/com/owncloud/android/ui/preview/PreviewMediaFragment.java (David A. Velasco 2013-03-11 10:20:04 +0100 345)         // create and prepare control panel for the user
ca8a1985 src/com/owncloud/android/ui/preview/PreviewMediaFragment.java (David A. Velasco 2013-03-11 10:20:04 +0100 346)         mMediaController.setMediaPlayer(mVideoPreview);
ca8a1985 src/com/owncloud/android/ui/preview/PreviewMediaFragment.java (David A. Velasco 2013-03-11 10:20:04 +0100 347)         
7101a042 src/com/owncloud/android/ui/fragment/FilePreviewFragment.java (David A. Velasco 2013-02-14 19:21:09 +0100 348)         // load the video file in the video player ; when done, VideoHelper#onPrepared() will be called
fd396289 src/com/owncloud/android/ui/preview/PreviewMediaFragment.java (David A. Velasco 2013-05-30 17:53:21 +0200 349)         mVideoPreview.setVideoPath(getFile().getStoragePath()); 
7101a042 src/com/owncloud/android/ui/fragment/FilePreviewFragment.java (David A. Velasco 2013-02-14 19:21:09 +0100 350)     }
7101a042 src/com/owncloud/android/ui/fragment/FilePreviewFragment.java (David A. Velasco 2013-02-14 19:21:09 +0100 351)     
7101a042 src/com/owncloud/android/ui/fragment/FilePreviewFragment.java (David A. Velasco 2013-02-14 19:21:09 +0100 352) 
7101a042 src/com/owncloud/android/ui/fragment/FilePreviewFragment.java (David A. Velasco 2013-02-14 19:21:09 +0100 353)     private class VideoHelper implements OnCompletionListener, OnPreparedListener, OnErrorListener {
7101a042 src/com/owncloud/android/ui/fragment/FilePreviewFragment.java (David A. Velasco 2013-02-14 19:21:09 +0100 354)         
7101a042 src/com/owncloud/android/ui/fragment/FilePreviewFragment.java (David A. Velasco 2013-02-14 19:21:09 +0100 355)         /** 
7101a042 src/com/owncloud/android/ui/fragment/FilePreviewFragment.java (David A. Velasco 2013-02-14 19:21:09 +0100 356)          * Called when the file is ready to be played.
7101a042 src/com/owncloud/android/ui/fragment/FilePreviewFragment.java (David A. Velasco 2013-02-14 19:21:09 +0100 357)          * 
7101a042 src/com/owncloud/android/ui/fragment/FilePreviewFragment.java (David A. Velasco 2013-02-14 19:21:09 +0100 358)          * Just starts the playback.
7101a042 src/com/owncloud/android/ui/fragment/FilePreviewFragment.java (David A. Velasco 2013-02-14 19:21:09 +0100 359)          * 
7101a042 src/com/owncloud/android/ui/fragment/FilePreviewFragment.java (David A. Velasco 2013-02-14 19:21:09 +0100 360)          * @param   mp    {@link MediaPlayer} instance performing the playback.
7101a042 src/com/owncloud/android/ui/fragment/FilePreviewFragment.java (David A. Velasco 2013-02-14 19:21:09 +0100 361)          */
7101a042 src/com/owncloud/android/ui/fragment/FilePreviewFragment.java (David A. Velasco 2013-02-14 19:21:09 +0100 362)         @Override
7101a042 src/com/owncloud/android/ui/fragment/FilePreviewFragment.java (David A. Velasco 2013-02-14 19:21:09 +0100 363)         public void onPrepared(MediaPlayer vp) {
2946d8dd src/com/owncloud/android/ui/preview/PreviewMediaFragment.java (David A. Velasco 2013-04-25 19:39:22 +0200 364)             Log_OC.e(TAG, "onPrepared");
7101a042 src/com/owncloud/android/ui/fragment/FilePreviewFragment.java (David A. Velasco 2013-02-14 19:21:09 +0100 365)             mVideoPreview.seekTo(mSavedPlaybackPosition);
2c2efa28 src/com/owncloud/android/ui/preview/PreviewMediaFragment.java (David A. Velasco 2013-03-13 12:21:33 +0100 366)             if (mAutoplay) { 
2c2efa28 src/com/owncloud/android/ui/preview/PreviewMediaFragment.java (David A. Velasco 2013-03-13 12:21:33 +0100 367)                 mVideoPreview.start();
2c2efa28 src/com/owncloud/android/ui/preview/PreviewMediaFragment.java (David A. Velasco 2013-03-13 12:21:33 +0100 368)             }
ca8a1985 src/com/owncloud/android/ui/preview/PreviewMediaFragment.java (David A. Velasco 2013-03-11 10:20:04 +0100 369)             mMediaController.setEnabled(true);
ca8a1985 src/com/owncloud/android/ui/preview/PreviewMediaFragment.java (David A. Velasco 2013-03-11 10:20:04 +0100 370)             mMediaController.updatePausePlay();
00328cb8 src/com/owncloud/android/ui/preview/PreviewMediaFragment.java (David A. Velasco 2013-04-30 13:16:08 +0200 371)             mPrepared = true;
7101a042 src/com/owncloud/android/ui/fragment/FilePreviewFragment.java (David A. Velasco 2013-02-14 19:21:09 +0100 372)         }
7101a042 src/com/owncloud/android/ui/fragment/FilePreviewFragment.java (David A. Velasco 2013-02-14 19:21:09 +0100 373)         
7101a042 src/com/owncloud/android/ui/fragment/FilePreviewFragment.java (David A. Velasco 2013-02-14 19:21:09 +0100 374)         
7101a042 src/com/owncloud/android/ui/fragment/FilePreviewFragment.java (David A. Velasco 2013-02-14 19:21:09 +0100 375)         /**
7101a042 src/com/owncloud/android/ui/fragment/FilePreviewFragment.java (David A. Velasco 2013-02-14 19:21:09 +0100 376)          * Called when the file is finished playing.
7101a042 src/com/owncloud/android/ui/fragment/FilePreviewFragment.java (David A. Velasco 2013-02-14 19:21:09 +0100 377)          *  
7101a042 src/com/owncloud/android/ui/fragment/FilePreviewFragment.java (David A. Velasco 2013-02-14 19:21:09 +0100 378)          * Finishes the activity.
7101a042 src/com/owncloud/android/ui/fragment/FilePreviewFragment.java (David A. Velasco 2013-02-14 19:21:09 +0100 379)          * 
7101a042 src/com/owncloud/android/ui/fragment/FilePreviewFragment.java (David A. Velasco 2013-02-14 19:21:09 +0100 380)          * @param   mp    {@link MediaPlayer} instance performing the playback.
7101a042 src/com/owncloud/android/ui/fragment/FilePreviewFragment.java (David A. Velasco 2013-02-14 19:21:09 +0100 381)          */
7101a042 src/com/owncloud/android/ui/fragment/FilePreviewFragment.java (David A. Velasco 2013-02-14 19:21:09 +0100 382)         @Override
7101a042 src/com/owncloud/android/ui/fragment/FilePreviewFragment.java (David A. Velasco 2013-02-14 19:21:09 +0100 383)         public void onCompletion(MediaPlayer  mp) {
2946d8dd src/com/owncloud/android/ui/preview/PreviewMediaFragment.java (David A. Velasco 2013-04-25 19:39:22 +0200 384)             Log_OC.e(TAG, "completed");
a11e7fd9 src/com/owncloud/android/ui/preview/PreviewMediaFragment.java (David A. Velasco 2013-04-10 13:07:10 +0200 385)             if (mp != null) {
a11e7fd9 src/com/owncloud/android/ui/preview/PreviewMediaFragment.java (David A. Velasco 2013-04-10 13:07:10 +0200 386)                 mVideoPreview.seekTo(0);
a11e7fd9 src/com/owncloud/android/ui/preview/PreviewMediaFragment.java (David A. Velasco 2013-04-10 13:07:10 +0200 387)                 // next lines are necessary to work around undesired video loops
a11e7fd9 src/com/owncloud/android/ui/preview/PreviewMediaFragment.java (David A. Velasco 2013-04-10 13:07:10 +0200 388)                 if (Build.VERSION.SDK_INT == Build.VERSION_CODES.GINGERBREAD) {
a11e7fd9 src/com/owncloud/android/ui/preview/PreviewMediaFragment.java (David A. Velasco 2013-04-10 13:07:10 +0200 389)                     mVideoPreview.pause();   
a11e7fd9 src/com/owncloud/android/ui/preview/PreviewMediaFragment.java (David A. Velasco 2013-04-10 13:07:10 +0200 390)                     
a11e7fd9 src/com/owncloud/android/ui/preview/PreviewMediaFragment.java (David A. Velasco 2013-04-10 13:07:10 +0200 391)                 } else if (Build.VERSION.SDK_INT == Build.VERSION_CODES.GINGERBREAD_MR1) {
a11e7fd9 src/com/owncloud/android/ui/preview/PreviewMediaFragment.java (David A. Velasco 2013-04-10 13:07:10 +0200 392)                     // mVideePreview.pause() is not enough
a11e7fd9 src/com/owncloud/android/ui/preview/PreviewMediaFragment.java (David A. Velasco 2013-04-10 13:07:10 +0200 393)                     
a11e7fd9 src/com/owncloud/android/ui/preview/PreviewMediaFragment.java (David A. Velasco 2013-04-10 13:07:10 +0200 394)                     mMediaController.setEnabled(false);
a11e7fd9 src/com/owncloud/android/ui/preview/PreviewMediaFragment.java (David A. Velasco 2013-04-10 13:07:10 +0200 395)                     mVideoPreview.stopPlayback();
a11e7fd9 src/com/owncloud/android/ui/preview/PreviewMediaFragment.java (David A. Velasco 2013-04-10 13:07:10 +0200 396)                     mAutoplay = false;
a11e7fd9 src/com/owncloud/android/ui/preview/PreviewMediaFragment.java (David A. Velasco 2013-04-10 13:07:10 +0200 397)                     mSavedPlaybackPosition = 0;
fd396289 src/com/owncloud/android/ui/preview/PreviewMediaFragment.java (David A. Velasco 2013-05-30 17:53:21 +0200 398)                     mVideoPreview.setVideoPath(getFile().getStoragePath());
a11e7fd9 src/com/owncloud/android/ui/preview/PreviewMediaFragment.java (David A. Velasco 2013-04-10 13:07:10 +0200 399)                 }
a11e7fd9 src/com/owncloud/android/ui/preview/PreviewMediaFragment.java (David A. Velasco 2013-04-10 13:07:10 +0200 400)             } // else : called from onError()
bc5c3f5f src/com/owncloud/android/ui/preview/PreviewMediaFragment.java (David A. Velasco 2013-03-14 16:55:57 +0100 401)             mMediaController.updatePausePlay();
7101a042 src/com/owncloud/android/ui/fragment/FilePreviewFragment.java (David A. Velasco 2013-02-14 19:21:09 +0100 402)         }
7101a042 src/com/owncloud/android/ui/fragment/FilePreviewFragment.java (David A. Velasco 2013-02-14 19:21:09 +0100 403)         
7101a042 src/com/owncloud/android/ui/fragment/FilePreviewFragment.java (David A. Velasco 2013-02-14 19:21:09 +0100 404)         
7101a042 src/com/owncloud/android/ui/fragment/FilePreviewFragment.java (David A. Velasco 2013-02-14 19:21:09 +0100 405)         /**
7101a042 src/com/owncloud/android/ui/fragment/FilePreviewFragment.java (David A. Velasco 2013-02-14 19:21:09 +0100 406)          * Called when an error in playback occurs.
7101a042 src/com/owncloud/android/ui/fragment/FilePreviewFragment.java (David A. Velasco 2013-02-14 19:21:09 +0100 407)          * 
7101a042 src/com/owncloud/android/ui/fragment/FilePreviewFragment.java (David A. Velasco 2013-02-14 19:21:09 +0100 408)          * @param   mp      {@link MediaPlayer} instance performing the playback.
7101a042 src/com/owncloud/android/ui/fragment/FilePreviewFragment.java (David A. Velasco 2013-02-14 19:21:09 +0100 409)          * @param   what    Type of error
7101a042 src/com/owncloud/android/ui/fragment/FilePreviewFragment.java (David A. Velasco 2013-02-14 19:21:09 +0100 410)          * @param   extra   Extra code specific to the error
7101a042 src/com/owncloud/android/ui/fragment/FilePreviewFragment.java (David A. Velasco 2013-02-14 19:21:09 +0100 411)          */
7101a042 src/com/owncloud/android/ui/fragment/FilePreviewFragment.java (David A. Velasco 2013-02-14 19:21:09 +0100 412)         @Override
7101a042 src/com/owncloud/android/ui/fragment/FilePreviewFragment.java (David A. Velasco 2013-02-14 19:21:09 +0100 413)         public boolean onError(MediaPlayer mp, int what, int extra) {
7101a042 src/com/owncloud/android/ui/fragment/FilePreviewFragment.java (David A. Velasco 2013-02-14 19:21:09 +0100 414)             if (mVideoPreview.getWindowToken() != null) {
7101a042 src/com/owncloud/android/ui/fragment/FilePreviewFragment.java (David A. Velasco 2013-02-14 19:21:09 +0100 415)                 String message = MediaService.getMessageForMediaError(getActivity(), what, extra);
7101a042 src/com/owncloud/android/ui/fragment/FilePreviewFragment.java (David A. Velasco 2013-02-14 19:21:09 +0100 416)                 new AlertDialog.Builder(getActivity())
7101a042 src/com/owncloud/android/ui/fragment/FilePreviewFragment.java (David A. Velasco 2013-02-14 19:21:09 +0100 417)                         .setMessage(message)
7101a042 src/com/owncloud/android/ui/fragment/FilePreviewFragment.java (David A. Velasco 2013-02-14 19:21:09 +0100 418)                         .setPositiveButton(android.R.string.VideoView_error_button,
7101a042 src/com/owncloud/android/ui/fragment/FilePreviewFragment.java (David A. Velasco 2013-02-14 19:21:09 +0100 419)                                 new DialogInterface.OnClickListener() {
7101a042 src/com/owncloud/android/ui/fragment/FilePreviewFragment.java (David A. Velasco 2013-02-14 19:21:09 +0100 420)                                     public void onClick(DialogInterface dialog, int whichButton) {
7101a042 src/com/owncloud/android/ui/fragment/FilePreviewFragment.java (David A. Velasco 2013-02-14 19:21:09 +0100 421)                                         dialog.dismiss();
7101a042 src/com/owncloud/android/ui/fragment/FilePreviewFragment.java (David A. Velasco 2013-02-14 19:21:09 +0100 422)                                         VideoHelper.this.onCompletion(null);
7101a042 src/com/owncloud/android/ui/fragment/FilePreviewFragment.java (David A. Velasco 2013-02-14 19:21:09 +0100 423)                                     }
7101a042 src/com/owncloud/android/ui/fragment/FilePreviewFragment.java (David A. Velasco 2013-02-14 19:21:09 +0100 424)                                 })
7101a042 src/com/owncloud/android/ui/fragment/FilePreviewFragment.java (David A. Velasco 2013-02-14 19:21:09 +0100 425)                         .setCancelable(false)
7101a042 src/com/owncloud/android/ui/fragment/FilePreviewFragment.java (David A. Velasco 2013-02-14 19:21:09 +0100 426)                         .show();
7101a042 src/com/owncloud/android/ui/fragment/FilePreviewFragment.java (David A. Velasco 2013-02-14 19:21:09 +0100 427)             }
7101a042 src/com/owncloud/android/ui/fragment/FilePreviewFragment.java (David A. Velasco 2013-02-14 19:21:09 +0100 428)             return true;
7101a042 src/com/owncloud/android/ui/fragment/FilePreviewFragment.java (David A. Velasco 2013-02-14 19:21:09 +0100 429)         }
7101a042 src/com/owncloud/android/ui/fragment/FilePreviewFragment.java (David A. Velasco 2013-02-14 19:21:09 +0100 430)         
7101a042 src/com/owncloud/android/ui/fragment/FilePreviewFragment.java (David A. Velasco 2013-02-14 19:21:09 +0100 431)     }
7101a042 src/com/owncloud/android/ui/fragment/FilePreviewFragment.java (David A. Velasco 2013-02-14 19:21:09 +0100 432) 
7101a042 src/com/owncloud/android/ui/fragment/FilePreviewFragment.java (David A. Velasco 2013-02-14 19:21:09 +0100 433)     
7101a042 src/com/owncloud/android/ui/fragment/FilePreviewFragment.java (David A. Velasco 2013-02-14 19:21:09 +0100 434)     @Override
00328cb8 src/com/owncloud/android/ui/preview/PreviewMediaFragment.java (David A. Velasco 2013-04-30 13:16:08 +0200 435)     public void onPause() {
00328cb8 src/com/owncloud/android/ui/preview/PreviewMediaFragment.java (David A. Velasco 2013-04-30 13:16:08 +0200 436)         super.onPause();
00328cb8 src/com/owncloud/android/ui/preview/PreviewMediaFragment.java (David A. Velasco 2013-04-30 13:16:08 +0200 437)         Log_OC.e(TAG, "onPause");
00328cb8 src/com/owncloud/android/ui/preview/PreviewMediaFragment.java (David A. Velasco 2013-04-30 13:16:08 +0200 438)     }
00328cb8 src/com/owncloud/android/ui/preview/PreviewMediaFragment.java (David A. Velasco 2013-04-30 13:16:08 +0200 439)     
00328cb8 src/com/owncloud/android/ui/preview/PreviewMediaFragment.java (David A. Velasco 2013-04-30 13:16:08 +0200 440)     @Override
00328cb8 src/com/owncloud/android/ui/preview/PreviewMediaFragment.java (David A. Velasco 2013-04-30 13:16:08 +0200 441)     public void onResume() {
00328cb8 src/com/owncloud/android/ui/preview/PreviewMediaFragment.java (David A. Velasco 2013-04-30 13:16:08 +0200 442)         super.onResume();
00328cb8 src/com/owncloud/android/ui/preview/PreviewMediaFragment.java (David A. Velasco 2013-04-30 13:16:08 +0200 443)         Log_OC.e(TAG, "onResume");
00328cb8 src/com/owncloud/android/ui/preview/PreviewMediaFragment.java (David A. Velasco 2013-04-30 13:16:08 +0200 444)     }
00328cb8 src/com/owncloud/android/ui/preview/PreviewMediaFragment.java (David A. Velasco 2013-04-30 13:16:08 +0200 445)     
00328cb8 src/com/owncloud/android/ui/preview/PreviewMediaFragment.java (David A. Velasco 2013-04-30 13:16:08 +0200 446)     @Override
00328cb8 src/com/owncloud/android/ui/preview/PreviewMediaFragment.java (David A. Velasco 2013-04-30 13:16:08 +0200 447)     public void onDestroy() {
00328cb8 src/com/owncloud/android/ui/preview/PreviewMediaFragment.java (David A. Velasco 2013-04-30 13:16:08 +0200 448)         super.onDestroy();
00328cb8 src/com/owncloud/android/ui/preview/PreviewMediaFragment.java (David A. Velasco 2013-04-30 13:16:08 +0200 449)         Log_OC.e(TAG, "onDestroy");
00328cb8 src/com/owncloud/android/ui/preview/PreviewMediaFragment.java (David A. Velasco 2013-04-30 13:16:08 +0200 450)     }
00328cb8 src/com/owncloud/android/ui/preview/PreviewMediaFragment.java (David A. Velasco 2013-04-30 13:16:08 +0200 451)     
00328cb8 src/com/owncloud/android/ui/preview/PreviewMediaFragment.java (David A. Velasco 2013-04-30 13:16:08 +0200 452)     @Override
7101a042 src/com/owncloud/android/ui/fragment/FilePreviewFragment.java (David A. Velasco 2013-02-14 19:21:09 +0100 453)     public void onStop() {
00328cb8 src/com/owncloud/android/ui/preview/PreviewMediaFragment.java (David A. Velasco 2013-04-30 13:16:08 +0200 454)         Log_OC.e(TAG, "onStop");
7101a042 src/com/owncloud/android/ui/fragment/FilePreviewFragment.java (David A. Velasco 2013-02-14 19:21:09 +0100 455)         super.onStop();
00328cb8 src/com/owncloud/android/ui/preview/PreviewMediaFragment.java (David A. Velasco 2013-04-30 13:16:08 +0200 456) 
00328cb8 src/com/owncloud/android/ui/preview/PreviewMediaFragment.java (David A. Velasco 2013-04-30 13:16:08 +0200 457)         mPrepared = false;
7101a042 src/com/owncloud/android/ui/fragment/FilePreviewFragment.java (David A. Velasco 2013-02-14 19:21:09 +0100 458)         if (mMediaServiceConnection != null) {
2946d8dd src/com/owncloud/android/ui/preview/PreviewMediaFragment.java (David A. Velasco 2013-04-25 19:39:22 +0200 459)             Log_OC.d(TAG, "Unbinding from MediaService ...");
7101a042 src/com/owncloud/android/ui/fragment/FilePreviewFragment.java (David A. Velasco 2013-02-14 19:21:09 +0100 460)             if (mMediaServiceBinder != null && mMediaController != null) {
ce7f7fa4 src/com/owncloud/android/ui/preview/PreviewMediaFragment.java (David A. Velasco 2013-03-12 14:36:31 +0100 461)                 mMediaServiceBinder.unregisterMediaController(mMediaController);
7101a042 src/com/owncloud/android/ui/fragment/FilePreviewFragment.java (David A. Velasco 2013-02-14 19:21:09 +0100 462)             }
7101a042 src/com/owncloud/android/ui/fragment/FilePreviewFragment.java (David A. Velasco 2013-02-14 19:21:09 +0100 463)             getActivity().unbindService(mMediaServiceConnection);
7101a042 src/com/owncloud/android/ui/fragment/FilePreviewFragment.java (David A. Velasco 2013-02-14 19:21:09 +0100 464)             mMediaServiceConnection = null;
7101a042 src/com/owncloud/android/ui/fragment/FilePreviewFragment.java (David A. Velasco 2013-02-14 19:21:09 +0100 465)             mMediaServiceBinder = null;
7101a042 src/com/owncloud/android/ui/fragment/FilePreviewFragment.java (David A. Velasco 2013-02-14 19:21:09 +0100 466)         }
f2474ae2 src/com/owncloud/android/ui/fragment/FilePreviewFragment.java (David A. Velasco 2013-02-18 11:23:18 +0100 467)     }
f2474ae2 src/com/owncloud/android/ui/fragment/FilePreviewFragment.java (David A. Velasco 2013-02-18 11:23:18 +0100 468)     
f2474ae2 src/com/owncloud/android/ui/fragment/FilePreviewFragment.java (David A. Velasco 2013-02-18 11:23:18 +0100 469)     @Override
7101a042 src/com/owncloud/android/ui/fragment/FilePreviewFragment.java (David A. Velasco 2013-02-14 19:21:09 +0100 470)     public boolean onTouch(View v, MotionEvent event) {
bc5c3f5f src/com/owncloud/android/ui/preview/PreviewMediaFragment.java (David A. Velasco 2013-03-14 16:55:57 +0100 471)         if (event.getAction() == MotionEvent.ACTION_DOWN && v == mVideoPreview) {
bc5c3f5f src/com/owncloud/android/ui/preview/PreviewMediaFragment.java (David A. Velasco 2013-03-14 16:55:57 +0100 472)             startFullScreenVideo();
bc5c3f5f src/com/owncloud/android/ui/preview/PreviewMediaFragment.java (David A. Velasco 2013-03-14 16:55:57 +0100 473)             return true;        
7101a042 src/com/owncloud/android/ui/fragment/FilePreviewFragment.java (David A. Velasco 2013-02-14 19:21:09 +0100 474)         }
7101a042 src/com/owncloud/android/ui/fragment/FilePreviewFragment.java (David A. Velasco 2013-02-14 19:21:09 +0100 475)         return false;
7101a042 src/com/owncloud/android/ui/fragment/FilePreviewFragment.java (David A. Velasco 2013-02-14 19:21:09 +0100 476)     }
7101a042 src/com/owncloud/android/ui/fragment/FilePreviewFragment.java (David A. Velasco 2013-02-14 19:21:09 +0100 477) 
7101a042 src/com/owncloud/android/ui/fragment/FilePreviewFragment.java (David A. Velasco 2013-02-14 19:21:09 +0100 478)     
bc5c3f5f src/com/owncloud/android/ui/preview/PreviewMediaFragment.java (David A. Velasco 2013-03-14 16:55:57 +0100 479)     private void startFullScreenVideo() {
bc5c3f5f src/com/owncloud/android/ui/preview/PreviewMediaFragment.java (David A. Velasco 2013-03-14 16:55:57 +0100 480)         Intent i = new Intent(getActivity(), PreviewVideoActivity.class);
3ecabe3e src/com/owncloud/android/ui/preview/PreviewMediaFragment.java (David A. Velasco 2013-06-14 16:59:38 +0200 481)         i.putExtra(FileActivity.EXTRA_ACCOUNT, mAccount);
3ecabe3e src/com/owncloud/android/ui/preview/PreviewMediaFragment.java (David A. Velasco 2013-06-14 16:59:38 +0200 482)         i.putExtra(FileActivity.EXTRA_FILE, getFile());
bc5c3f5f src/com/owncloud/android/ui/preview/PreviewMediaFragment.java (David A. Velasco 2013-03-14 16:55:57 +0100 483)         i.putExtra(PreviewVideoActivity.EXTRA_AUTOPLAY, mVideoPreview.isPlaying());
bc5c3f5f src/com/owncloud/android/ui/preview/PreviewMediaFragment.java (David A. Velasco 2013-03-14 16:55:57 +0100 484)         mVideoPreview.pause();
bc5c3f5f src/com/owncloud/android/ui/preview/PreviewMediaFragment.java (David A. Velasco 2013-03-14 16:55:57 +0100 485)         i.putExtra(PreviewVideoActivity.EXTRA_START_POSITION, mVideoPreview.getCurrentPosition());
bc5c3f5f src/com/owncloud/android/ui/preview/PreviewMediaFragment.java (David A. Velasco 2013-03-14 16:55:57 +0100 486)         startActivityForResult(i, 0);
7101a042 src/com/owncloud/android/ui/fragment/FilePreviewFragment.java (David A. Velasco 2013-02-14 19:21:09 +0100 487)     }
7101a042 src/com/owncloud/android/ui/fragment/FilePreviewFragment.java (David A. Velasco 2013-02-14 19:21:09 +0100 488) 
00328cb8 src/com/owncloud/android/ui/preview/PreviewMediaFragment.java (David A. Velasco 2013-04-30 13:16:08 +0200 489)     @Override
00328cb8 src/com/owncloud/android/ui/preview/PreviewMediaFragment.java (David A. Velasco 2013-04-30 13:16:08 +0200 490)     public void onConfigurationChanged (Configuration newConfig) {
00328cb8 src/com/owncloud/android/ui/preview/PreviewMediaFragment.java (David A. Velasco 2013-04-30 13:16:08 +0200 491)         Log_OC.e(TAG, "onConfigurationChanged " + this);
00328cb8 src/com/owncloud/android/ui/preview/PreviewMediaFragment.java (David A. Velasco 2013-04-30 13:16:08 +0200 492)     }
bc5c3f5f src/com/owncloud/android/ui/preview/PreviewMediaFragment.java (David A. Velasco 2013-03-14 16:55:57 +0100 493)     
bc5c3f5f src/com/owncloud/android/ui/preview/PreviewMediaFragment.java (David A. Velasco 2013-03-14 16:55:57 +0100 494)     @Override
bc5c3f5f src/com/owncloud/android/ui/preview/PreviewMediaFragment.java (David A. Velasco 2013-03-14 16:55:57 +0100 495)     public void onActivityResult (int requestCode, int resultCode, Intent data) {
00328cb8 src/com/owncloud/android/ui/preview/PreviewMediaFragment.java (David A. Velasco 2013-04-30 13:16:08 +0200 496)         Log_OC.e(TAG, "onActivityResult " + this);
bc5c3f5f src/com/owncloud/android/ui/preview/PreviewMediaFragment.java (David A. Velasco 2013-03-14 16:55:57 +0100 497)         super.onActivityResult(requestCode, resultCode, data);
bc5c3f5f src/com/owncloud/android/ui/preview/PreviewMediaFragment.java (David A. Velasco 2013-03-14 16:55:57 +0100 498)         if (resultCode == Activity.RESULT_OK) {
bc5c3f5f src/com/owncloud/android/ui/preview/PreviewMediaFragment.java (David A. Velasco 2013-03-14 16:55:57 +0100 499)             mSavedPlaybackPosition = data.getExtras().getInt(PreviewVideoActivity.EXTRA_START_POSITION);
bc5c3f5f src/com/owncloud/android/ui/preview/PreviewMediaFragment.java (David A. Velasco 2013-03-14 16:55:57 +0100 500)             mAutoplay = data.getExtras().getBoolean(PreviewVideoActivity.EXTRA_AUTOPLAY); 
bc5c3f5f src/com/owncloud/android/ui/preview/PreviewMediaFragment.java (David A. Velasco 2013-03-14 16:55:57 +0100 501)         }
bc5c3f5f src/com/owncloud/android/ui/preview/PreviewMediaFragment.java (David A. Velasco 2013-03-14 16:55:57 +0100 502)     }
bc5c3f5f src/com/owncloud/android/ui/preview/PreviewMediaFragment.java (David A. Velasco 2013-03-14 16:55:57 +0100 503)     
7101a042 src/com/owncloud/android/ui/fragment/FilePreviewFragment.java (David A. Velasco 2013-02-14 19:21:09 +0100 504) 
7101a042 src/com/owncloud/android/ui/fragment/FilePreviewFragment.java (David A. Velasco 2013-02-14 19:21:09 +0100 505)     private void playAudio() {
fd396289 src/com/owncloud/android/ui/preview/PreviewMediaFragment.java (David A. Velasco 2013-05-30 17:53:21 +0200 506)         OCFile file = getFile();
fd396289 src/com/owncloud/android/ui/preview/PreviewMediaFragment.java (David A. Velasco 2013-05-30 17:53:21 +0200 507)         if (!mMediaServiceBinder.isPlaying(file)) {
fd396289 src/com/owncloud/android/ui/preview/PreviewMediaFragment.java (David A. Velasco 2013-05-30 17:53:21 +0200 508)             Log_OC.d(TAG, "starting playback of " + file.getStoragePath());
fd396289 src/com/owncloud/android/ui/preview/PreviewMediaFragment.java (David A. Velasco 2013-05-30 17:53:21 +0200 509)             mMediaServiceBinder.start(mAccount, file, mAutoplay, mSavedPlaybackPosition);
7101a042 src/com/owncloud/android/ui/fragment/FilePreviewFragment.java (David A. Velasco 2013-02-14 19:21:09 +0100 510)             
7101a042 src/com/owncloud/android/ui/fragment/FilePreviewFragment.java (David A. Velasco 2013-02-14 19:21:09 +0100 511)         } else {
fa801791 src/com/owncloud/android/ui/preview/PreviewMediaFragment.java (David A. Velasco 2013-03-22 13:04:57 +0100 512)             if (!mMediaServiceBinder.isPlaying() && mAutoplay) {
7101a042 src/com/owncloud/android/ui/fragment/FilePreviewFragment.java (David A. Velasco 2013-02-14 19:21:09 +0100 513)                 mMediaServiceBinder.start();
ce7f7fa4 src/com/owncloud/android/ui/preview/PreviewMediaFragment.java (David A. Velasco 2013-03-12 14:36:31 +0100 514)                 mMediaController.updatePausePlay();
7101a042 src/com/owncloud/android/ui/fragment/FilePreviewFragment.java (David A. Velasco 2013-02-14 19:21:09 +0100 515)             }
7101a042 src/com/owncloud/android/ui/fragment/FilePreviewFragment.java (David A. Velasco 2013-02-14 19:21:09 +0100 516)         }
7101a042 src/com/owncloud/android/ui/fragment/FilePreviewFragment.java (David A. Velasco 2013-02-14 19:21:09 +0100 517)     }
7101a042 src/com/owncloud/android/ui/fragment/FilePreviewFragment.java (David A. Velasco 2013-02-14 19:21:09 +0100 518) 
7101a042 src/com/owncloud/android/ui/fragment/FilePreviewFragment.java (David A. Velasco 2013-02-14 19:21:09 +0100 519) 
7101a042 src/com/owncloud/android/ui/fragment/FilePreviewFragment.java (David A. Velasco 2013-02-14 19:21:09 +0100 520)     private void bindMediaService() {
2946d8dd src/com/owncloud/android/ui/preview/PreviewMediaFragment.java (David A. Velasco 2013-04-25 19:39:22 +0200 521)         Log_OC.d(TAG, "Binding to MediaService...");
7101a042 src/com/owncloud/android/ui/fragment/FilePreviewFragment.java (David A. Velasco 2013-02-14 19:21:09 +0100 522)         if (mMediaServiceConnection == null) {
7101a042 src/com/owncloud/android/ui/fragment/FilePreviewFragment.java (David A. Velasco 2013-02-14 19:21:09 +0100 523)             mMediaServiceConnection = new MediaServiceConnection();
7101a042 src/com/owncloud/android/ui/fragment/FilePreviewFragment.java (David A. Velasco 2013-02-14 19:21:09 +0100 524)         }
7101a042 src/com/owncloud/android/ui/fragment/FilePreviewFragment.java (David A. Velasco 2013-02-14 19:21:09 +0100 525)         getActivity().bindService(  new Intent(getActivity(), 
7101a042 src/com/owncloud/android/ui/fragment/FilePreviewFragment.java (David A. Velasco 2013-02-14 19:21:09 +0100 526)                                     MediaService.class),
7101a042 src/com/owncloud/android/ui/fragment/FilePreviewFragment.java (David A. Velasco 2013-02-14 19:21:09 +0100 527)                                     mMediaServiceConnection, 
7101a042 src/com/owncloud/android/ui/fragment/FilePreviewFragment.java (David A. Velasco 2013-02-14 19:21:09 +0100 528)                                     Context.BIND_AUTO_CREATE);
7101a042 src/com/owncloud/android/ui/fragment/FilePreviewFragment.java (David A. Velasco 2013-02-14 19:21:09 +0100 529)             // follow the flow in MediaServiceConnection#onServiceConnected(...)
7101a042 src/com/owncloud/android/ui/fragment/FilePreviewFragment.java (David A. Velasco 2013-02-14 19:21:09 +0100 530)     }
7101a042 src/com/owncloud/android/ui/fragment/FilePreviewFragment.java (David A. Velasco 2013-02-14 19:21:09 +0100 531)     
7101a042 src/com/owncloud/android/ui/fragment/FilePreviewFragment.java (David A. Velasco 2013-02-14 19:21:09 +0100 532)     /** Defines callbacks for service binding, passed to bindService() */
7101a042 src/com/owncloud/android/ui/fragment/FilePreviewFragment.java (David A. Velasco 2013-02-14 19:21:09 +0100 533)     private class MediaServiceConnection implements ServiceConnection {
7101a042 src/com/owncloud/android/ui/fragment/FilePreviewFragment.java (David A. Velasco 2013-02-14 19:21:09 +0100 534) 
7101a042 src/com/owncloud/android/ui/fragment/FilePreviewFragment.java (David A. Velasco 2013-02-14 19:21:09 +0100 535)         @Override
7101a042 src/com/owncloud/android/ui/fragment/FilePreviewFragment.java (David A. Velasco 2013-02-14 19:21:09 +0100 536)         public void onServiceConnected(ComponentName component, IBinder service) {
7101a042 src/com/owncloud/android/ui/fragment/FilePreviewFragment.java (David A. Velasco 2013-02-14 19:21:09 +0100 537)             if (component.equals(new ComponentName(getActivity(), MediaService.class))) {
2946d8dd src/com/owncloud/android/ui/preview/PreviewMediaFragment.java (David A. Velasco 2013-04-25 19:39:22 +0200 538)                 Log_OC.d(TAG, "Media service connected");
7101a042 src/com/owncloud/android/ui/fragment/FilePreviewFragment.java (David A. Velasco 2013-02-14 19:21:09 +0100 539)                 mMediaServiceBinder = (MediaServiceBinder) service;
7101a042 src/com/owncloud/android/ui/fragment/FilePreviewFragment.java (David A. Velasco 2013-02-14 19:21:09 +0100 540)                 if (mMediaServiceBinder != null) {
7101a042 src/com/owncloud/android/ui/fragment/FilePreviewFragment.java (David A. Velasco 2013-02-14 19:21:09 +0100 541)                     prepareMediaController();
7101a042 src/com/owncloud/android/ui/fragment/FilePreviewFragment.java (David A. Velasco 2013-02-14 19:21:09 +0100 542)                     playAudio();    // do not wait for the touch of nobody to play audio
7101a042 src/com/owncloud/android/ui/fragment/FilePreviewFragment.java (David A. Velasco 2013-02-14 19:21:09 +0100 543)                     
2946d8dd src/com/owncloud/android/ui/preview/PreviewMediaFragment.java (David A. Velasco 2013-04-25 19:39:22 +0200 544)                     Log_OC.d(TAG, "Successfully bound to MediaService, MediaController ready");
7101a042 src/com/owncloud/android/ui/fragment/FilePreviewFragment.java (David A. Velasco 2013-02-14 19:21:09 +0100 545)                     
7101a042 src/com/owncloud/android/ui/fragment/FilePreviewFragment.java (David A. Velasco 2013-02-14 19:21:09 +0100 546)                 } else {
2946d8dd src/com/owncloud/android/ui/preview/PreviewMediaFragment.java (David A. Velasco 2013-04-25 19:39:22 +0200 547)                     Log_OC.e(TAG, "Unexpected response from MediaService while binding");
7101a042 src/com/owncloud/android/ui/fragment/FilePreviewFragment.java (David A. Velasco 2013-02-14 19:21:09 +0100 548)                 }
7101a042 src/com/owncloud/android/ui/fragment/FilePreviewFragment.java (David A. Velasco 2013-02-14 19:21:09 +0100 549)             }
7101a042 src/com/owncloud/android/ui/fragment/FilePreviewFragment.java (David A. Velasco 2013-02-14 19:21:09 +0100 550)         }
ce7f7fa4 src/com/owncloud/android/ui/preview/PreviewMediaFragment.java (David A. Velasco 2013-03-12 14:36:31 +0100 551) 
7101a042 src/com/owncloud/android/ui/fragment/FilePreviewFragment.java (David A. Velasco 2013-02-14 19:21:09 +0100 552)         private void prepareMediaController() {
ce7f7fa4 src/com/owncloud/android/ui/preview/PreviewMediaFragment.java (David A. Velasco 2013-03-12 14:36:31 +0100 553)             mMediaServiceBinder.registerMediaController(mMediaController);
d68b3246 src/com/owncloud/android/ui/preview/PreviewMediaFragment.java (David A. Velasco 2013-03-08 13:32:10 +0100 554)             if (mMediaController != null) {
d68b3246 src/com/owncloud/android/ui/preview/PreviewMediaFragment.java (David A. Velasco 2013-03-08 13:32:10 +0100 555)                 mMediaController.setMediaPlayer(mMediaServiceBinder);
ca8a1985 src/com/owncloud/android/ui/preview/PreviewMediaFragment.java (David A. Velasco 2013-03-11 10:20:04 +0100 556)                 mMediaController.setEnabled(true);
ca8a1985 src/com/owncloud/android/ui/preview/PreviewMediaFragment.java (David A. Velasco 2013-03-11 10:20:04 +0100 557)                 mMediaController.updatePausePlay();
d68b3246 src/com/owncloud/android/ui/preview/PreviewMediaFragment.java (David A. Velasco 2013-03-08 13:32:10 +0100 558)             }
7101a042 src/com/owncloud/android/ui/fragment/FilePreviewFragment.java (David A. Velasco 2013-02-14 19:21:09 +0100 559)         }
7101a042 src/com/owncloud/android/ui/fragment/FilePreviewFragment.java (David A. Velasco 2013-02-14 19:21:09 +0100 560) 
7101a042 src/com/owncloud/android/ui/fragment/FilePreviewFragment.java (David A. Velasco 2013-02-14 19:21:09 +0100 561)         @Override
7101a042 src/com/owncloud/android/ui/fragment/FilePreviewFragment.java (David A. Velasco 2013-02-14 19:21:09 +0100 562)         public void onServiceDisconnected(ComponentName component) {
7101a042 src/com/owncloud/android/ui/fragment/FilePreviewFragment.java (David A. Velasco 2013-02-14 19:21:09 +0100 563)             if (component.equals(new ComponentName(getActivity(), MediaService.class))) {
2946d8dd src/com/owncloud/android/ui/preview/PreviewMediaFragment.java (David A. Velasco 2013-04-25 19:39:22 +0200 564)                 Log_OC.e(TAG, "Media service suddenly disconnected");
7101a042 src/com/owncloud/android/ui/fragment/FilePreviewFragment.java (David A. Velasco 2013-02-14 19:21:09 +0100 565)                 if (mMediaController != null) {
7101a042 src/com/owncloud/android/ui/fragment/FilePreviewFragment.java (David A. Velasco 2013-02-14 19:21:09 +0100 566)                     mMediaController.setMediaPlayer(null);
d68b3246 src/com/owncloud/android/ui/preview/PreviewMediaFragment.java (David A. Velasco 2013-03-08 13:32:10 +0100 567)                 } else {
d68b3246 src/com/owncloud/android/ui/preview/PreviewMediaFragment.java (David A. Velasco 2013-03-08 13:32:10 +0100 568)                     Toast.makeText(getActivity(), "No media controller to release when disconnected from media service", Toast.LENGTH_SHORT).show();
7101a042 src/com/owncloud/android/ui/fragment/FilePreviewFragment.java (David A. Velasco 2013-02-14 19:21:09 +0100 569)                 }
7101a042 src/com/owncloud/android/ui/fragment/FilePreviewFragment.java (David A. Velasco 2013-02-14 19:21:09 +0100 570)                 mMediaServiceBinder = null;
7101a042 src/com/owncloud/android/ui/fragment/FilePreviewFragment.java (David A. Velasco 2013-02-14 19:21:09 +0100 571)                 mMediaServiceConnection = null;
7101a042 src/com/owncloud/android/ui/fragment/FilePreviewFragment.java (David A. Velasco 2013-02-14 19:21:09 +0100 572)             }
7101a042 src/com/owncloud/android/ui/fragment/FilePreviewFragment.java (David A. Velasco 2013-02-14 19:21:09 +0100 573)         }
7101a042 src/com/owncloud/android/ui/fragment/FilePreviewFragment.java (David A. Velasco 2013-02-14 19:21:09 +0100 574)     }    
7101a042 src/com/owncloud/android/ui/fragment/FilePreviewFragment.java (David A. Velasco 2013-02-14 19:21:09 +0100 575) 
7101a042 src/com/owncloud/android/ui/fragment/FilePreviewFragment.java (David A. Velasco 2013-02-14 19:21:09 +0100 576)     
7101a042 src/com/owncloud/android/ui/fragment/FilePreviewFragment.java (David A. Velasco 2013-02-14 19:21:09 +0100 577) 
f2474ae2 src/com/owncloud/android/ui/fragment/FilePreviewFragment.java (David A. Velasco 2013-02-18 11:23:18 +0100 578)     /**
f2474ae2 src/com/owncloud/android/ui/fragment/FilePreviewFragment.java (David A. Velasco 2013-02-18 11:23:18 +0100 579)      * Opens the previewed file with an external application.
f2474ae2 src/com/owncloud/android/ui/fragment/FilePreviewFragment.java (David A. Velasco 2013-02-18 11:23:18 +0100 580)      * 
f2474ae2 src/com/owncloud/android/ui/fragment/FilePreviewFragment.java (David A. Velasco 2013-02-18 11:23:18 +0100 581)      * TODO - improve this; instead of prioritize the actions available for the MIME type in the server, 
f2474ae2 src/com/owncloud/android/ui/fragment/FilePreviewFragment.java (David A. Velasco 2013-02-18 11:23:18 +0100 582)      * we should get a list of available apps for MIME tpye in the server and join it with the list of 
f2474ae2 src/com/owncloud/android/ui/fragment/FilePreviewFragment.java (David A. Velasco 2013-02-18 11:23:18 +0100 583)      * available apps for the MIME type known from the file extension, to let the user choose
f2474ae2 src/com/owncloud/android/ui/fragment/FilePreviewFragment.java (David A. Velasco 2013-02-18 11:23:18 +0100 584)      */
7101a042 src/com/owncloud/android/ui/fragment/FilePreviewFragment.java (David A. Velasco 2013-02-14 19:21:09 +0100 585)     private void openFile() {
fd396289 src/com/owncloud/android/ui/preview/PreviewMediaFragment.java (David A. Velasco 2013-05-30 17:53:21 +0200 586)         OCFile file = getFile();
f2474ae2 src/com/owncloud/android/ui/fragment/FilePreviewFragment.java (David A. Velasco 2013-02-18 11:23:18 +0100 587)         stopPreview(true);
fd396289 src/com/owncloud/android/ui/preview/PreviewMediaFragment.java (David A. Velasco 2013-05-30 17:53:21 +0200 588)         String storagePath = file.getStoragePath();
7101a042 src/com/owncloud/android/ui/fragment/FilePreviewFragment.java (David A. Velasco 2013-02-14 19:21:09 +0100 589)         String encodedStoragePath = WebdavUtils.encodePath(storagePath);
7101a042 src/com/owncloud/android/ui/fragment/FilePreviewFragment.java (David A. Velasco 2013-02-14 19:21:09 +0100 590)         try {
7101a042 src/com/owncloud/android/ui/fragment/FilePreviewFragment.java (David A. Velasco 2013-02-14 19:21:09 +0100 591)             Intent i = new Intent(Intent.ACTION_VIEW);
fd396289 src/com/owncloud/android/ui/preview/PreviewMediaFragment.java (David A. Velasco 2013-05-30 17:53:21 +0200 592)             i.setDataAndType(Uri.parse("file://"+ encodedStoragePath), file.getMimetype());
7101a042 src/com/owncloud/android/ui/fragment/FilePreviewFragment.java (David A. Velasco 2013-02-14 19:21:09 +0100 593)             i.setFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION | Intent.FLAG_GRANT_WRITE_URI_PERMISSION);
7101a042 src/com/owncloud/android/ui/fragment/FilePreviewFragment.java (David A. Velasco 2013-02-14 19:21:09 +0100 594)             startActivity(i);
7101a042 src/com/owncloud/android/ui/fragment/FilePreviewFragment.java (David A. Velasco 2013-02-14 19:21:09 +0100 595)             
7101a042 src/com/owncloud/android/ui/fragment/FilePreviewFragment.java (David A. Velasco 2013-02-14 19:21:09 +0100 596)         } catch (Throwable t) {
fd396289 src/com/owncloud/android/ui/preview/PreviewMediaFragment.java (David A. Velasco 2013-05-30 17:53:21 +0200 597)             Log_OC.e(TAG, "Fail when trying to open with the mimeType provided from the ownCloud server: " + file.getMimetype());
7101a042 src/com/owncloud/android/ui/fragment/FilePreviewFragment.java (David A. Velasco 2013-02-14 19:21:09 +0100 598)             boolean toastIt = true; 
7101a042 src/com/owncloud/android/ui/fragment/FilePreviewFragment.java (David A. Velasco 2013-02-14 19:21:09 +0100 599)             String mimeType = "";
7101a042 src/com/owncloud/android/ui/fragment/FilePreviewFragment.java (David A. Velasco 2013-02-14 19:21:09 +0100 600)             try {
7101a042 src/com/owncloud/android/ui/fragment/FilePreviewFragment.java (David A. Velasco 2013-02-14 19:21:09 +0100 601)                 Intent i = new Intent(Intent.ACTION_VIEW);
7101a042 src/com/owncloud/android/ui/fragment/FilePreviewFragment.java (David A. Velasco 2013-02-14 19:21:09 +0100 602)                 mimeType = MimeTypeMap.getSingleton().getMimeTypeFromExtension(storagePath.substring(storagePath.lastIndexOf('.') + 1));
fd396289 src/com/owncloud/android/ui/preview/PreviewMediaFragment.java (David A. Velasco 2013-05-30 17:53:21 +0200 603)                 if (mimeType == null || !mimeType.equals(file.getMimetype())) {
7101a042 src/com/owncloud/android/ui/fragment/FilePreviewFragment.java (David A. Velasco 2013-02-14 19:21:09 +0100 604)                     if (mimeType != null) {
7101a042 src/com/owncloud/android/ui/fragment/FilePreviewFragment.java (David A. Velasco 2013-02-14 19:21:09 +0100 605)                         i.setDataAndType(Uri.parse("file://"+ encodedStoragePath), mimeType);
7101a042 src/com/owncloud/android/ui/fragment/FilePreviewFragment.java (David A. Velasco 2013-02-14 19:21:09 +0100 606)                     } else {
7101a042 src/com/owncloud/android/ui/fragment/FilePreviewFragment.java (David A. Velasco 2013-02-14 19:21:09 +0100 607)                         // desperate try
7101a042 src/com/owncloud/android/ui/fragment/FilePreviewFragment.java (David A. Velasco 2013-02-14 19:21:09 +0100 608)                         i.setDataAndType(Uri.parse("file://"+ encodedStoragePath), "*-/*");
7101a042 src/com/owncloud/android/ui/fragment/FilePreviewFragment.java (David A. Velasco 2013-02-14 19:21:09 +0100 609)                     }
7101a042 src/com/owncloud/android/ui/fragment/FilePreviewFragment.java (David A. Velasco 2013-02-14 19:21:09 +0100 610)                     i.setFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION | Intent.FLAG_GRANT_WRITE_URI_PERMISSION);
7101a042 src/com/owncloud/android/ui/fragment/FilePreviewFragment.java (David A. Velasco 2013-02-14 19:21:09 +0100 611)                     startActivity(i);
7101a042 src/com/owncloud/android/ui/fragment/FilePreviewFragment.java (David A. Velasco 2013-02-14 19:21:09 +0100 612)                     toastIt = false;
7101a042 src/com/owncloud/android/ui/fragment/FilePreviewFragment.java (David A. Velasco 2013-02-14 19:21:09 +0100 613)                 }
7101a042 src/com/owncloud/android/ui/fragment/FilePreviewFragment.java (David A. Velasco 2013-02-14 19:21:09 +0100 614)                 
7101a042 src/com/owncloud/android/ui/fragment/FilePreviewFragment.java (David A. Velasco 2013-02-14 19:21:09 +0100 615)             } catch (IndexOutOfBoundsException e) {
2946d8dd src/com/owncloud/android/ui/preview/PreviewMediaFragment.java (David A. Velasco 2013-04-25 19:39:22 +0200 616)                 Log_OC.e(TAG, "Trying to find out MIME type of a file without extension: " + storagePath);
7101a042 src/com/owncloud/android/ui/fragment/FilePreviewFragment.java (David A. Velasco 2013-02-14 19:21:09 +0100 617)                 
7101a042 src/com/owncloud/android/ui/fragment/FilePreviewFragment.java (David A. Velasco 2013-02-14 19:21:09 +0100 618)             } catch (ActivityNotFoundException e) {
2946d8dd src/com/owncloud/android/ui/preview/PreviewMediaFragment.java (David A. Velasco 2013-04-25 19:39:22 +0200 619)                 Log_OC.e(TAG, "No activity found to handle: " + storagePath + " with MIME type " + mimeType + " obtained from extension");
7101a042 src/com/owncloud/android/ui/fragment/FilePreviewFragment.java (David A. Velasco 2013-02-14 19:21:09 +0100 620)                 
7101a042 src/com/owncloud/android/ui/fragment/FilePreviewFragment.java (David A. Velasco 2013-02-14 19:21:09 +0100 621)             } catch (Throwable th) {
2946d8dd src/com/owncloud/android/ui/preview/PreviewMediaFragment.java (David A. Velasco 2013-04-25 19:39:22 +0200 622)                 Log_OC.e(TAG, "Unexpected problem when opening: " + storagePath, th);
7101a042 src/com/owncloud/android/ui/fragment/FilePreviewFragment.java (David A. Velasco 2013-02-14 19:21:09 +0100 623)                 
7101a042 src/com/owncloud/android/ui/fragment/FilePreviewFragment.java (David A. Velasco 2013-02-14 19:21:09 +0100 624)             } finally {
7101a042 src/com/owncloud/android/ui/fragment/FilePreviewFragment.java (David A. Velasco 2013-02-14 19:21:09 +0100 625)                 if (toastIt) {
fd396289 src/com/owncloud/android/ui/preview/PreviewMediaFragment.java (David A. Velasco 2013-05-30 17:53:21 +0200 626)                     Toast.makeText(getActivity(), "There is no application to handle file " + file.getFileName(), Toast.LENGTH_SHORT).show();
7101a042 src/com/owncloud/android/ui/fragment/FilePreviewFragment.java (David A. Velasco 2013-02-14 19:21:09 +0100 627)                 }
7101a042 src/com/owncloud/android/ui/fragment/FilePreviewFragment.java (David A. Velasco 2013-02-14 19:21:09 +0100 628)             }
7101a042 src/com/owncloud/android/ui/fragment/FilePreviewFragment.java (David A. Velasco 2013-02-14 19:21:09 +0100 629)             
7101a042 src/com/owncloud/android/ui/fragment/FilePreviewFragment.java (David A. Velasco 2013-02-14 19:21:09 +0100 630)         }
f2474ae2 src/com/owncloud/android/ui/fragment/FilePreviewFragment.java (David A. Velasco 2013-02-18 11:23:18 +0100 631)         finish();
f2474ae2 src/com/owncloud/android/ui/fragment/FilePreviewFragment.java (David A. Velasco 2013-02-18 11:23:18 +0100 632)     }
f2474ae2 src/com/owncloud/android/ui/fragment/FilePreviewFragment.java (David A. Velasco 2013-02-18 11:23:18 +0100 633)     
f2474ae2 src/com/owncloud/android/ui/fragment/FilePreviewFragment.java (David A. Velasco 2013-02-18 11:23:18 +0100 634)     /**
f2474ae2 src/com/owncloud/android/ui/fragment/FilePreviewFragment.java (David A. Velasco 2013-02-18 11:23:18 +0100 635)      * Starts a the removal of the previewed file.
f2474ae2 src/com/owncloud/android/ui/fragment/FilePreviewFragment.java (David A. Velasco 2013-02-18 11:23:18 +0100 636)      * 
f2474ae2 src/com/owncloud/android/ui/fragment/FilePreviewFragment.java (David A. Velasco 2013-02-18 11:23:18 +0100 637)      * Shows a confirmation dialog. The action continues in {@link #onConfirmation(String)} , {@link #onNeutral(String)} or {@link #onCancel(String)},
f2474ae2 src/com/owncloud/android/ui/fragment/FilePreviewFragment.java (David A. Velasco 2013-02-18 11:23:18 +0100 638)      * depending upon the user selection in the dialog. 
f2474ae2 src/com/owncloud/android/ui/fragment/FilePreviewFragment.java (David A. Velasco 2013-02-18 11:23:18 +0100 639)      */
f2474ae2 src/com/owncloud/android/ui/fragment/FilePreviewFragment.java (David A. Velasco 2013-02-18 11:23:18 +0100 640)     private void removeFile() {
f2474ae2 src/com/owncloud/android/ui/fragment/FilePreviewFragment.java (David A. Velasco 2013-02-18 11:23:18 +0100 641)         ConfirmationDialogFragment confDialog = ConfirmationDialogFragment.newInstance(
f2474ae2 src/com/owncloud/android/ui/fragment/FilePreviewFragment.java (David A. Velasco 2013-02-18 11:23:18 +0100 642)                 R.string.confirmation_remove_alert,
fd396289 src/com/owncloud/android/ui/preview/PreviewMediaFragment.java (David A. Velasco 2013-05-30 17:53:21 +0200 643)                 new String[]{getFile().getFileName()},
f2474ae2 src/com/owncloud/android/ui/fragment/FilePreviewFragment.java (David A. Velasco 2013-02-18 11:23:18 +0100 644)                 R.string.confirmation_remove_remote_and_local,
f2474ae2 src/com/owncloud/android/ui/fragment/FilePreviewFragment.java (David A. Velasco 2013-02-18 11:23:18 +0100 645)                 R.string.confirmation_remove_local,
f2474ae2 src/com/owncloud/android/ui/fragment/FilePreviewFragment.java (David A. Velasco 2013-02-18 11:23:18 +0100 646)                 R.string.common_cancel);
f2474ae2 src/com/owncloud/android/ui/fragment/FilePreviewFragment.java (David A. Velasco 2013-02-18 11:23:18 +0100 647)         confDialog.setOnConfirmationListener(this);
f2474ae2 src/com/owncloud/android/ui/fragment/FilePreviewFragment.java (David A. Velasco 2013-02-18 11:23:18 +0100 648)         confDialog.show(getFragmentManager(), ConfirmationDialogFragment.FTAG_CONFIRMATION);
7101a042 src/com/owncloud/android/ui/fragment/FilePreviewFragment.java (David A. Velasco 2013-02-14 19:21:09 +0100 649)     }
7101a042 src/com/owncloud/android/ui/fragment/FilePreviewFragment.java (David A. Velasco 2013-02-14 19:21:09 +0100 650) 
f2474ae2 src/com/owncloud/android/ui/fragment/FilePreviewFragment.java (David A. Velasco 2013-02-18 11:23:18 +0100 651)     
f2474ae2 src/com/owncloud/android/ui/fragment/FilePreviewFragment.java (David A. Velasco 2013-02-18 11:23:18 +0100 652)     /**
f2474ae2 src/com/owncloud/android/ui/fragment/FilePreviewFragment.java (David A. Velasco 2013-02-18 11:23:18 +0100 653)      * Performs the removal of the previewed file, both locally and in the server.
f2474ae2 src/com/owncloud/android/ui/fragment/FilePreviewFragment.java (David A. Velasco 2013-02-18 11:23:18 +0100 654)      */
7101a042 src/com/owncloud/android/ui/fragment/FilePreviewFragment.java (David A. Velasco 2013-02-14 19:21:09 +0100 655)     @Override
7101a042 src/com/owncloud/android/ui/fragment/FilePreviewFragment.java (David A. Velasco 2013-02-14 19:21:09 +0100 656)     public void onConfirmation(String callerTag) {
fd396289 src/com/owncloud/android/ui/preview/PreviewMediaFragment.java (David A. Velasco 2013-05-30 17:53:21 +0200 657)         OCFile file = getFile();
fd396289 src/com/owncloud/android/ui/preview/PreviewMediaFragment.java (David A. Velasco 2013-05-30 17:53:21 +0200 658)         if (mStorageManager.getFileById(file.getFileId()) != null) {   // check that the file is still there;
f2474ae2 src/com/owncloud/android/ui/fragment/FilePreviewFragment.java (David A. Velasco 2013-02-18 11:23:18 +0100 659)             stopPreview(true);
fd396289 src/com/owncloud/android/ui/preview/PreviewMediaFragment.java (David A. Velasco 2013-05-30 17:53:21 +0200 660)             mLastRemoteOperation = new RemoveFileOperation( file,      // TODO we need to review the interface with RemoteOperations, and use OCFile IDs instead of OCFile objects as parameters
f2474ae2 src/com/owncloud/android/ui/fragment/FilePreviewFragment.java (David A. Velasco 2013-02-18 11:23:18 +0100 661)                                                             true, 
f2474ae2 src/com/owncloud/android/ui/fragment/FilePreviewFragment.java (David A. Velasco 2013-02-18 11:23:18 +0100 662)                                                             mStorageManager);
2946d8dd src/com/owncloud/android/ui/preview/PreviewMediaFragment.java (David A. Velasco 2013-04-25 19:39:22 +0200 663)             mLastRemoteOperation.execute(mAccount, getSherlockActivity(), this, mHandler, getSherlockActivity());
f2474ae2 src/com/owncloud/android/ui/fragment/FilePreviewFragment.java (David A. Velasco 2013-02-18 11:23:18 +0100 664)             
fd396289 src/com/owncloud/android/ui/preview/PreviewMediaFragment.java (David A. Velasco 2013-05-30 17:53:21 +0200 665)             getActivity().showDialog(FileDisplayActivity.DIALOG_SHORT_WAIT);
7101a042 src/com/owncloud/android/ui/fragment/FilePreviewFragment.java (David A. Velasco 2013-02-14 19:21:09 +0100 666)         }
7101a042 src/com/owncloud/android/ui/fragment/FilePreviewFragment.java (David A. Velasco 2013-02-14 19:21:09 +0100 667)     }
7101a042 src/com/owncloud/android/ui/fragment/FilePreviewFragment.java (David A. Velasco 2013-02-14 19:21:09 +0100 668)     
f2474ae2 src/com/owncloud/android/ui/fragment/FilePreviewFragment.java (David A. Velasco 2013-02-18 11:23:18 +0100 669)     
f2474ae2 src/com/owncloud/android/ui/fragment/FilePreviewFragment.java (David A. Velasco 2013-02-18 11:23:18 +0100 670)     /**
f2474ae2 src/com/owncloud/android/ui/fragment/FilePreviewFragment.java (David A. Velasco 2013-02-18 11:23:18 +0100 671)      * Removes the file from local storage
f2474ae2 src/com/owncloud/android/ui/fragment/FilePreviewFragment.java (David A. Velasco 2013-02-18 11:23:18 +0100 672)      */
7101a042 src/com/owncloud/android/ui/fragment/FilePreviewFragment.java (David A. Velasco 2013-02-14 19:21:09 +0100 673)     @Override
7101a042 src/com/owncloud/android/ui/fragment/FilePreviewFragment.java (David A. Velasco 2013-02-14 19:21:09 +0100 674)     public void onNeutral(String callerTag) {
f2474ae2 src/com/owncloud/android/ui/fragment/FilePreviewFragment.java (David A. Velasco 2013-02-18 11:23:18 +0100 675)         // TODO this code should be made in a secondary thread,
fd396289 src/com/owncloud/android/ui/preview/PreviewMediaFragment.java (David A. Velasco 2013-05-30 17:53:21 +0200 676)         OCFile file = getFile();
fd396289 src/com/owncloud/android/ui/preview/PreviewMediaFragment.java (David A. Velasco 2013-05-30 17:53:21 +0200 677)         if (file.isDown()) {   // checks it is still there
f2474ae2 src/com/owncloud/android/ui/fragment/FilePreviewFragment.java (David A. Velasco 2013-02-18 11:23:18 +0100 678)             stopPreview(true);
fd396289 src/com/owncloud/android/ui/preview/PreviewMediaFragment.java (David A. Velasco 2013-05-30 17:53:21 +0200 679)             File f = new File(file.getStoragePath());
7101a042 src/com/owncloud/android/ui/fragment/FilePreviewFragment.java (David A. Velasco 2013-02-14 19:21:09 +0100 680)             f.delete();
fd396289 src/com/owncloud/android/ui/preview/PreviewMediaFragment.java (David A. Velasco 2013-05-30 17:53:21 +0200 681)             file.setStoragePath(null);
fd396289 src/com/owncloud/android/ui/preview/PreviewMediaFragment.java (David A. Velasco 2013-05-30 17:53:21 +0200 682)             mStorageManager.saveFile(file);
f2474ae2 src/com/owncloud/android/ui/fragment/FilePreviewFragment.java (David A. Velasco 2013-02-18 11:23:18 +0100 683)             finish();
7101a042 src/com/owncloud/android/ui/fragment/FilePreviewFragment.java (David A. Velasco 2013-02-14 19:21:09 +0100 684)         }
7101a042 src/com/owncloud/android/ui/fragment/FilePreviewFragment.java (David A. Velasco 2013-02-14 19:21:09 +0100 685)     }
7101a042 src/com/owncloud/android/ui/fragment/FilePreviewFragment.java (David A. Velasco 2013-02-14 19:21:09 +0100 686)     
f2474ae2 src/com/owncloud/android/ui/fragment/FilePreviewFragment.java (David A. Velasco 2013-02-18 11:23:18 +0100 687)     /**
f2474ae2 src/com/owncloud/android/ui/fragment/FilePreviewFragment.java (David A. Velasco 2013-02-18 11:23:18 +0100 688)      * User cancelled the removal action.
f2474ae2 src/com/owncloud/android/ui/fragment/FilePreviewFragment.java (David A. Velasco 2013-02-18 11:23:18 +0100 689)      */
7101a042 src/com/owncloud/android/ui/fragment/FilePreviewFragment.java (David A. Velasco 2013-02-14 19:21:09 +0100 690)     @Override
7101a042 src/com/owncloud/android/ui/fragment/FilePreviewFragment.java (David A. Velasco 2013-02-14 19:21:09 +0100 691)     public void onCancel(String callerTag) {
f2474ae2 src/com/owncloud/android/ui/fragment/FilePreviewFragment.java (David A. Velasco 2013-02-18 11:23:18 +0100 692)         // nothing to do here
7101a042 src/com/owncloud/android/ui/fragment/FilePreviewFragment.java (David A. Velasco 2013-02-14 19:21:09 +0100 693)     }
f2474ae2 src/com/owncloud/android/ui/fragment/FilePreviewFragment.java (David A. Velasco 2013-02-18 11:23:18 +0100 694)     
7101a042 src/com/owncloud/android/ui/fragment/FilePreviewFragment.java (David A. Velasco 2013-02-14 19:21:09 +0100 695) 
7101a042 src/com/owncloud/android/ui/fragment/FilePreviewFragment.java (David A. Velasco 2013-02-14 19:21:09 +0100 696)     /**
2f1aaa8b src/com/owncloud/android/ui/preview/PreviewMediaFragment.java (David A. Velasco 2013-02-26 19:04:42 +0100 697)      * Helper method to test if an {@link OCFile} can be passed to a {@link PreviewMediaFragment} to be previewed.
7101a042 src/com/owncloud/android/ui/fragment/FilePreviewFragment.java (David A. Velasco 2013-02-14 19:21:09 +0100 698)      * 
7101a042 src/com/owncloud/android/ui/fragment/FilePreviewFragment.java (David A. Velasco 2013-02-14 19:21:09 +0100 699)      * @param file      File to test if can be previewed.
7101a042 src/com/owncloud/android/ui/fragment/FilePreviewFragment.java (David A. Velasco 2013-02-14 19:21:09 +0100 700)      * @return          'True' if the file can be handled by the fragment.
7101a042 src/com/owncloud/android/ui/fragment/FilePreviewFragment.java (David A. Velasco 2013-02-14 19:21:09 +0100 701)      */
7101a042 src/com/owncloud/android/ui/fragment/FilePreviewFragment.java (David A. Velasco 2013-02-14 19:21:09 +0100 702)     public static boolean canBePreviewed(OCFile file) {
2f1aaa8b src/com/owncloud/android/ui/preview/PreviewMediaFragment.java (David A. Velasco 2013-02-26 19:04:42 +0100 703)         return (file != null && (file.isAudio() || file.isVideo()));
7101a042 src/com/owncloud/android/ui/fragment/FilePreviewFragment.java (David A. Velasco 2013-02-14 19:21:09 +0100 704)     }
7101a042 src/com/owncloud/android/ui/fragment/FilePreviewFragment.java (David A. Velasco 2013-02-14 19:21:09 +0100 705) 
f2474ae2 src/com/owncloud/android/ui/fragment/FilePreviewFragment.java (David A. Velasco 2013-02-18 11:23:18 +0100 706)     /**
7101a042 src/com/owncloud/android/ui/fragment/FilePreviewFragment.java (David A. Velasco 2013-02-14 19:21:09 +0100 707)      * {@inheritDoc}
f2474ae2 src/com/owncloud/android/ui/fragment/FilePreviewFragment.java (David A. Velasco 2013-02-18 11:23:18 +0100 708)      */
7101a042 src/com/owncloud/android/ui/fragment/FilePreviewFragment.java (David A. Velasco 2013-02-14 19:21:09 +0100 709)     @Override
7101a042 src/com/owncloud/android/ui/fragment/FilePreviewFragment.java (David A. Velasco 2013-02-14 19:21:09 +0100 710)     public void onRemoteOperationFinish(RemoteOperation operation, RemoteOperationResult result) {
7101a042 src/com/owncloud/android/ui/fragment/FilePreviewFragment.java (David A. Velasco 2013-02-14 19:21:09 +0100 711)         if (operation.equals(mLastRemoteOperation)) {
7101a042 src/com/owncloud/android/ui/fragment/FilePreviewFragment.java (David A. Velasco 2013-02-14 19:21:09 +0100 712)             if (operation instanceof RemoveFileOperation) {
7101a042 src/com/owncloud/android/ui/fragment/FilePreviewFragment.java (David A. Velasco 2013-02-14 19:21:09 +0100 713)                 onRemoveFileOperationFinish((RemoveFileOperation)operation, result);
7101a042 src/com/owncloud/android/ui/fragment/FilePreviewFragment.java (David A. Velasco 2013-02-14 19:21:09 +0100 714)             }
7101a042 src/com/owncloud/android/ui/fragment/FilePreviewFragment.java (David A. Velasco 2013-02-14 19:21:09 +0100 715)         }
7101a042 src/com/owncloud/android/ui/fragment/FilePreviewFragment.java (David A. Velasco 2013-02-14 19:21:09 +0100 716)     }
7101a042 src/com/owncloud/android/ui/fragment/FilePreviewFragment.java (David A. Velasco 2013-02-14 19:21:09 +0100 717)     
7101a042 src/com/owncloud/android/ui/fragment/FilePreviewFragment.java (David A. Velasco 2013-02-14 19:21:09 +0100 718)     private void onRemoveFileOperationFinish(RemoveFileOperation operation, RemoteOperationResult result) {
fd396289 src/com/owncloud/android/ui/preview/PreviewMediaFragment.java (David A. Velasco 2013-05-30 17:53:21 +0200 719)         getActivity().dismissDialog(FileDisplayActivity.DIALOG_SHORT_WAIT);
7101a042 src/com/owncloud/android/ui/fragment/FilePreviewFragment.java (David A. Velasco 2013-02-14 19:21:09 +0100 720)         
7101a042 src/com/owncloud/android/ui/fragment/FilePreviewFragment.java (David A. Velasco 2013-02-14 19:21:09 +0100 721)         if (result.isSuccess()) {
7101a042 src/com/owncloud/android/ui/fragment/FilePreviewFragment.java (David A. Velasco 2013-02-14 19:21:09 +0100 722)             Toast msg = Toast.makeText(getActivity().getApplicationContext(), R.string.remove_success_msg, Toast.LENGTH_LONG);
7101a042 src/com/owncloud/android/ui/fragment/FilePreviewFragment.java (David A. Velasco 2013-02-14 19:21:09 +0100 723)             msg.show();
f2474ae2 src/com/owncloud/android/ui/fragment/FilePreviewFragment.java (David A. Velasco 2013-02-18 11:23:18 +0100 724)             finish();
7101a042 src/com/owncloud/android/ui/fragment/FilePreviewFragment.java (David A. Velasco 2013-02-14 19:21:09 +0100 725)                 
7101a042 src/com/owncloud/android/ui/fragment/FilePreviewFragment.java (David A. Velasco 2013-02-14 19:21:09 +0100 726)         } else {
7101a042 src/com/owncloud/android/ui/fragment/FilePreviewFragment.java (David A. Velasco 2013-02-14 19:21:09 +0100 727)             Toast msg = Toast.makeText(getActivity(), R.string.remove_fail_msg, Toast.LENGTH_LONG); 
7101a042 src/com/owncloud/android/ui/fragment/FilePreviewFragment.java (David A. Velasco 2013-02-14 19:21:09 +0100 728)             msg.show();
7101a042 src/com/owncloud/android/ui/fragment/FilePreviewFragment.java (David A. Velasco 2013-02-14 19:21:09 +0100 729)             if (result.isSslRecoverableException()) {
7101a042 src/com/owncloud/android/ui/fragment/FilePreviewFragment.java (David A. Velasco 2013-02-14 19:21:09 +0100 730)                 // TODO show the SSL warning dialog
7101a042 src/com/owncloud/android/ui/fragment/FilePreviewFragment.java (David A. Velasco 2013-02-14 19:21:09 +0100 731)             }
7101a042 src/com/owncloud/android/ui/fragment/FilePreviewFragment.java (David A. Velasco 2013-02-14 19:21:09 +0100 732)         }
7101a042 src/com/owncloud/android/ui/fragment/FilePreviewFragment.java (David A. Velasco 2013-02-14 19:21:09 +0100 733)     }
f2474ae2 src/com/owncloud/android/ui/fragment/FilePreviewFragment.java (David A. Velasco 2013-02-18 11:23:18 +0100 734) 
f2474ae2 src/com/owncloud/android/ui/fragment/FilePreviewFragment.java (David A. Velasco 2013-02-18 11:23:18 +0100 735)     private void stopPreview(boolean stopAudio) {
fd396289 src/com/owncloud/android/ui/preview/PreviewMediaFragment.java (David A. Velasco 2013-05-30 17:53:21 +0200 736)         OCFile file = getFile();
fd396289 src/com/owncloud/android/ui/preview/PreviewMediaFragment.java (David A. Velasco 2013-05-30 17:53:21 +0200 737)         if (file.isAudio() && stopAudio) {
f2474ae2 src/com/owncloud/android/ui/fragment/FilePreviewFragment.java (David A. Velasco 2013-02-18 11:23:18 +0100 738)             mMediaServiceBinder.pause();
f2474ae2 src/com/owncloud/android/ui/fragment/FilePreviewFragment.java (David A. Velasco 2013-02-18 11:23:18 +0100 739)             
fd396289 src/com/owncloud/android/ui/preview/PreviewMediaFragment.java (David A. Velasco 2013-05-30 17:53:21 +0200 740)         } else if (file.isVideo()) {
f2474ae2 src/com/owncloud/android/ui/fragment/FilePreviewFragment.java (David A. Velasco 2013-02-18 11:23:18 +0100 741)             mVideoPreview.stopPlayback();
f2474ae2 src/com/owncloud/android/ui/fragment/FilePreviewFragment.java (David A. Velasco 2013-02-18 11:23:18 +0100 742)         }
f2474ae2 src/com/owncloud/android/ui/fragment/FilePreviewFragment.java (David A. Velasco 2013-02-18 11:23:18 +0100 743)     }
f2474ae2 src/com/owncloud/android/ui/fragment/FilePreviewFragment.java (David A. Velasco 2013-02-18 11:23:18 +0100 744) 
f2474ae2 src/com/owncloud/android/ui/fragment/FilePreviewFragment.java (David A. Velasco 2013-02-18 11:23:18 +0100 745) 
f2474ae2 src/com/owncloud/android/ui/fragment/FilePreviewFragment.java (David A. Velasco 2013-02-18 11:23:18 +0100 746) 
f2474ae2 src/com/owncloud/android/ui/fragment/FilePreviewFragment.java (David A. Velasco 2013-02-18 11:23:18 +0100 747)     /**
f2474ae2 src/com/owncloud/android/ui/fragment/FilePreviewFragment.java (David A. Velasco 2013-02-18 11:23:18 +0100 748)      * Finishes the preview
f2474ae2 src/com/owncloud/android/ui/fragment/FilePreviewFragment.java (David A. Velasco 2013-02-18 11:23:18 +0100 749)      */
f2474ae2 src/com/owncloud/android/ui/fragment/FilePreviewFragment.java (David A. Velasco 2013-02-18 11:23:18 +0100 750)     private void finish() {
fd396289 src/com/owncloud/android/ui/preview/PreviewMediaFragment.java (David A. Velasco 2013-05-30 17:53:21 +0200 751)         getActivity().onBackPressed();
f2474ae2 src/com/owncloud/android/ui/fragment/FilePreviewFragment.java (David A. Velasco 2013-02-18 11:23:18 +0100 752)     }
6496b920 src/com/owncloud/android/ui/preview/PreviewMediaFragment.java (David A. Velasco 2013-04-26 14:02:17 +0200 753) 
6496b920 src/com/owncloud/android/ui/preview/PreviewMediaFragment.java (David A. Velasco 2013-04-26 14:02:17 +0200 754) 
6496b920 src/com/owncloud/android/ui/preview/PreviewMediaFragment.java (David A. Velasco 2013-04-26 14:02:17 +0200 755)     public int getPosition() {
00328cb8 src/com/owncloud/android/ui/preview/PreviewMediaFragment.java (David A. Velasco 2013-04-30 13:16:08 +0200 756)         if (mPrepared) {
00328cb8 src/com/owncloud/android/ui/preview/PreviewMediaFragment.java (David A. Velasco 2013-04-30 13:16:08 +0200 757)             mSavedPlaybackPosition = mVideoPreview.getCurrentPosition();
00328cb8 src/com/owncloud/android/ui/preview/PreviewMediaFragment.java (David A. Velasco 2013-04-30 13:16:08 +0200 758)         }
00328cb8 src/com/owncloud/android/ui/preview/PreviewMediaFragment.java (David A. Velasco 2013-04-30 13:16:08 +0200 759)         Log_OC.e(TAG, "getting position: " + mSavedPlaybackPosition);
6496b920 src/com/owncloud/android/ui/preview/PreviewMediaFragment.java (David A. Velasco 2013-04-26 14:02:17 +0200 760)         return mSavedPlaybackPosition;
6496b920 src/com/owncloud/android/ui/preview/PreviewMediaFragment.java (David A. Velasco 2013-04-26 14:02:17 +0200 761)     }
6496b920 src/com/owncloud/android/ui/preview/PreviewMediaFragment.java (David A. Velasco 2013-04-26 14:02:17 +0200 762)     
6496b920 src/com/owncloud/android/ui/preview/PreviewMediaFragment.java (David A. Velasco 2013-04-26 14:02:17 +0200 763)     public boolean isPlaying() {
00328cb8 src/com/owncloud/android/ui/preview/PreviewMediaFragment.java (David A. Velasco 2013-04-30 13:16:08 +0200 764)         if (mPrepared) {
00328cb8 src/com/owncloud/android/ui/preview/PreviewMediaFragment.java (David A. Velasco 2013-04-30 13:16:08 +0200 765)             mAutoplay = mVideoPreview.isPlaying();
00328cb8 src/com/owncloud/android/ui/preview/PreviewMediaFragment.java (David A. Velasco 2013-04-30 13:16:08 +0200 766)         }
6496b920 src/com/owncloud/android/ui/preview/PreviewMediaFragment.java (David A. Velasco 2013-04-26 14:02:17 +0200 767)         return mAutoplay;
6496b920 src/com/owncloud/android/ui/preview/PreviewMediaFragment.java (David A. Velasco 2013-04-26 14:02:17 +0200 768)     }
7101a042 src/com/owncloud/android/ui/fragment/FilePreviewFragment.java (David A. Velasco 2013-02-14 19:21:09 +0100 769)     
7101a042 src/com/owncloud/android/ui/fragment/FilePreviewFragment.java (David A. Velasco 2013-02-14 19:21:09 +0100 770) }
