3d272eb7 src/com/owncloud/android/ui/fragment/PreviewImageFragment.java (David A. Velasco 2013-02-25 12:24:14 +0100   1) /* ownCloud Android client application
3d272eb7 src/com/owncloud/android/ui/fragment/PreviewImageFragment.java (David A. Velasco 2013-02-25 12:24:14 +0100   2)  *   Copyright (C) 2012-2013 ownCloud Inc. 
3d272eb7 src/com/owncloud/android/ui/fragment/PreviewImageFragment.java (David A. Velasco 2013-02-25 12:24:14 +0100   3)  *
3d272eb7 src/com/owncloud/android/ui/fragment/PreviewImageFragment.java (David A. Velasco 2013-02-25 12:24:14 +0100   4)  *   This program is free software: you can redistribute it and/or modify
bdc0332c src/com/owncloud/android/ui/preview/PreviewImageFragment.java  (David A. Velasco 2013-04-17 12:26:13 +0200   5)  *   it under the terms of the GNU General Public License version 2,
bdc0332c src/com/owncloud/android/ui/preview/PreviewImageFragment.java  (David A. Velasco 2013-04-17 12:26:13 +0200   6)  *   as published by the Free Software Foundation.
3d272eb7 src/com/owncloud/android/ui/fragment/PreviewImageFragment.java (David A. Velasco 2013-02-25 12:24:14 +0100   7)  *
3d272eb7 src/com/owncloud/android/ui/fragment/PreviewImageFragment.java (David A. Velasco 2013-02-25 12:24:14 +0100   8)  *   This program is distributed in the hope that it will be useful,
3d272eb7 src/com/owncloud/android/ui/fragment/PreviewImageFragment.java (David A. Velasco 2013-02-25 12:24:14 +0100   9)  *   but WITHOUT ANY WARRANTY; without even the implied warranty of
3d272eb7 src/com/owncloud/android/ui/fragment/PreviewImageFragment.java (David A. Velasco 2013-02-25 12:24:14 +0100  10)  *   MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
3d272eb7 src/com/owncloud/android/ui/fragment/PreviewImageFragment.java (David A. Velasco 2013-02-25 12:24:14 +0100  11)  *   GNU General Public License for more details.
3d272eb7 src/com/owncloud/android/ui/fragment/PreviewImageFragment.java (David A. Velasco 2013-02-25 12:24:14 +0100  12)  *
3d272eb7 src/com/owncloud/android/ui/fragment/PreviewImageFragment.java (David A. Velasco 2013-02-25 12:24:14 +0100  13)  *   You should have received a copy of the GNU General Public License
3d272eb7 src/com/owncloud/android/ui/fragment/PreviewImageFragment.java (David A. Velasco 2013-02-25 12:24:14 +0100  14)  *   along with this program.  If not, see <http://www.gnu.org/licenses/>.
3d272eb7 src/com/owncloud/android/ui/fragment/PreviewImageFragment.java (David A. Velasco 2013-02-25 12:24:14 +0100  15)  *
3d272eb7 src/com/owncloud/android/ui/fragment/PreviewImageFragment.java (David A. Velasco 2013-02-25 12:24:14 +0100  16)  */
fc2af5b0 src/com/owncloud/android/ui/preview/PreviewImageFragment.java  (David A. Velasco 2013-02-26 12:33:28 +0100  17) package com.owncloud.android.ui.preview;
3d272eb7 src/com/owncloud/android/ui/fragment/PreviewImageFragment.java (David A. Velasco 2013-02-25 12:24:14 +0100  18) 
3d272eb7 src/com/owncloud/android/ui/fragment/PreviewImageFragment.java (David A. Velasco 2013-02-25 12:24:14 +0100  19) import java.io.File;
3d272eb7 src/com/owncloud/android/ui/fragment/PreviewImageFragment.java (David A. Velasco 2013-02-25 12:24:14 +0100  20) import java.lang.ref.WeakReference;
3d272eb7 src/com/owncloud/android/ui/fragment/PreviewImageFragment.java (David A. Velasco 2013-02-25 12:24:14 +0100  21) import java.util.ArrayList;
3d272eb7 src/com/owncloud/android/ui/fragment/PreviewImageFragment.java (David A. Velasco 2013-02-25 12:24:14 +0100  22) import java.util.List;
3d272eb7 src/com/owncloud/android/ui/fragment/PreviewImageFragment.java (David A. Velasco 2013-02-25 12:24:14 +0100  23) 
3d272eb7 src/com/owncloud/android/ui/fragment/PreviewImageFragment.java (David A. Velasco 2013-02-25 12:24:14 +0100  24) 
3d272eb7 src/com/owncloud/android/ui/fragment/PreviewImageFragment.java (David A. Velasco 2013-02-25 12:24:14 +0100  25) import android.accounts.Account;
3d272eb7 src/com/owncloud/android/ui/fragment/PreviewImageFragment.java (David A. Velasco 2013-02-25 12:24:14 +0100  26) import android.annotation.SuppressLint;
3d272eb7 src/com/owncloud/android/ui/fragment/PreviewImageFragment.java (David A. Velasco 2013-02-25 12:24:14 +0100  27) import android.app.Activity;
3d272eb7 src/com/owncloud/android/ui/fragment/PreviewImageFragment.java (David A. Velasco 2013-02-25 12:24:14 +0100  28) import android.content.ActivityNotFoundException;
3d272eb7 src/com/owncloud/android/ui/fragment/PreviewImageFragment.java (David A. Velasco 2013-02-25 12:24:14 +0100  29) import android.content.Intent;
3d272eb7 src/com/owncloud/android/ui/fragment/PreviewImageFragment.java (David A. Velasco 2013-02-25 12:24:14 +0100  30) import android.graphics.Bitmap;
3d272eb7 src/com/owncloud/android/ui/fragment/PreviewImageFragment.java (David A. Velasco 2013-02-25 12:24:14 +0100  31) import android.graphics.BitmapFactory;
3d272eb7 src/com/owncloud/android/ui/fragment/PreviewImageFragment.java (David A. Velasco 2013-02-25 12:24:14 +0100  32) import android.graphics.BitmapFactory.Options;
3d272eb7 src/com/owncloud/android/ui/fragment/PreviewImageFragment.java (David A. Velasco 2013-02-25 12:24:14 +0100  33) import android.graphics.Point;
3d272eb7 src/com/owncloud/android/ui/fragment/PreviewImageFragment.java (David A. Velasco 2013-02-25 12:24:14 +0100  34) import android.net.Uri;
3d272eb7 src/com/owncloud/android/ui/fragment/PreviewImageFragment.java (David A. Velasco 2013-02-25 12:24:14 +0100  35) import android.os.AsyncTask;
3d272eb7 src/com/owncloud/android/ui/fragment/PreviewImageFragment.java (David A. Velasco 2013-02-25 12:24:14 +0100  36) import android.os.Bundle;
3d272eb7 src/com/owncloud/android/ui/fragment/PreviewImageFragment.java (David A. Velasco 2013-02-25 12:24:14 +0100  37) import android.os.Handler;
86cec34b src/com/owncloud/android/ui/preview/PreviewImageFragment.java  (David A. Velasco 2013-02-27 14:08:58 +0100  38) import android.support.v4.app.FragmentStatePagerAdapter;
3d272eb7 src/com/owncloud/android/ui/fragment/PreviewImageFragment.java (David A. Velasco 2013-02-25 12:24:14 +0100  39) import android.view.Display;
3d272eb7 src/com/owncloud/android/ui/fragment/PreviewImageFragment.java (David A. Velasco 2013-02-25 12:24:14 +0100  40) import android.view.LayoutInflater;
3d272eb7 src/com/owncloud/android/ui/fragment/PreviewImageFragment.java (David A. Velasco 2013-02-25 12:24:14 +0100  41) import android.view.View;
638b7767 src/com/owncloud/android/ui/preview/PreviewImageFragment.java  (David A. Velasco 2013-02-28 17:09:00 +0100  42) import android.view.View.OnTouchListener;
3d272eb7 src/com/owncloud/android/ui/fragment/PreviewImageFragment.java (David A. Velasco 2013-02-25 12:24:14 +0100  43) import android.view.ViewGroup;
3d272eb7 src/com/owncloud/android/ui/fragment/PreviewImageFragment.java (David A. Velasco 2013-02-25 12:24:14 +0100  44) import android.webkit.MimeTypeMap;
3d272eb7 src/com/owncloud/android/ui/fragment/PreviewImageFragment.java (David A. Velasco 2013-02-25 12:24:14 +0100  45) import android.widget.ImageView;
427a3e63 src/com/owncloud/android/ui/preview/PreviewImageFragment.java  (David A. Velasco 2013-03-01 10:49:35 +0100  46) import android.widget.ProgressBar;
6cfa24f5 src/com/owncloud/android/ui/preview/PreviewImageFragment.java  (David A. Velasco 2013-03-01 09:54:49 +0100  47) import android.widget.TextView;
3d272eb7 src/com/owncloud/android/ui/fragment/PreviewImageFragment.java (David A. Velasco 2013-02-25 12:24:14 +0100  48) import android.widget.Toast;
3d272eb7 src/com/owncloud/android/ui/fragment/PreviewImageFragment.java (David A. Velasco 2013-02-25 12:24:14 +0100  49) 
3d272eb7 src/com/owncloud/android/ui/fragment/PreviewImageFragment.java (David A. Velasco 2013-02-25 12:24:14 +0100  50) import com.actionbarsherlock.view.Menu;
3d272eb7 src/com/owncloud/android/ui/fragment/PreviewImageFragment.java (David A. Velasco 2013-02-25 12:24:14 +0100  51) import com.actionbarsherlock.view.MenuInflater;
3d272eb7 src/com/owncloud/android/ui/fragment/PreviewImageFragment.java (David A. Velasco 2013-02-25 12:24:14 +0100  52) import com.actionbarsherlock.view.MenuItem;
3d272eb7 src/com/owncloud/android/ui/fragment/PreviewImageFragment.java (David A. Velasco 2013-02-25 12:24:14 +0100  53) import com.owncloud.android.datamodel.FileDataStorageManager;
3d272eb7 src/com/owncloud/android/ui/fragment/PreviewImageFragment.java (David A. Velasco 2013-02-25 12:24:14 +0100  54) import com.owncloud.android.datamodel.OCFile;
3d272eb7 src/com/owncloud/android/ui/fragment/PreviewImageFragment.java (David A. Velasco 2013-02-25 12:24:14 +0100  55) import com.owncloud.android.operations.OnRemoteOperationListener;
3d272eb7 src/com/owncloud/android/ui/fragment/PreviewImageFragment.java (David A. Velasco 2013-02-25 12:24:14 +0100  56) import com.owncloud.android.operations.RemoteOperation;
3d272eb7 src/com/owncloud/android/ui/fragment/PreviewImageFragment.java (David A. Velasco 2013-02-25 12:24:14 +0100  57) import com.owncloud.android.operations.RemoteOperationResult;
3d272eb7 src/com/owncloud/android/ui/fragment/PreviewImageFragment.java (David A. Velasco 2013-02-25 12:24:14 +0100  58) import com.owncloud.android.operations.RemoveFileOperation;
fc2af5b0 src/com/owncloud/android/ui/preview/PreviewImageFragment.java  (David A. Velasco 2013-02-26 12:33:28 +0100  59) import com.owncloud.android.ui.fragment.ConfirmationDialogFragment;
fc2af5b0 src/com/owncloud/android/ui/preview/PreviewImageFragment.java  (David A. Velasco 2013-02-26 12:33:28 +0100  60) import com.owncloud.android.ui.fragment.FileFragment;
3d272eb7 src/com/owncloud/android/ui/fragment/PreviewImageFragment.java (David A. Velasco 2013-02-25 12:24:14 +0100  61) 
2946d8dd src/com/owncloud/android/ui/preview/PreviewImageFragment.java  (David A. Velasco 2013-04-25 19:39:22 +0200  62) import com.owncloud.android.Log_OC;
3d272eb7 src/com/owncloud/android/ui/fragment/PreviewImageFragment.java (David A. Velasco 2013-02-25 12:24:14 +0100  63) import com.owncloud.android.R;
3d272eb7 src/com/owncloud/android/ui/fragment/PreviewImageFragment.java (David A. Velasco 2013-02-25 12:24:14 +0100  64) import eu.alefzero.webdav.WebdavUtils;
3d272eb7 src/com/owncloud/android/ui/fragment/PreviewImageFragment.java (David A. Velasco 2013-02-25 12:24:14 +0100  65) 
3d272eb7 src/com/owncloud/android/ui/fragment/PreviewImageFragment.java (David A. Velasco 2013-02-25 12:24:14 +0100  66) 
3d272eb7 src/com/owncloud/android/ui/fragment/PreviewImageFragment.java (David A. Velasco 2013-02-25 12:24:14 +0100  67) /**
3d272eb7 src/com/owncloud/android/ui/fragment/PreviewImageFragment.java (David A. Velasco 2013-02-25 12:24:14 +0100  68)  * This fragment shows a preview of a downloaded image.
3d272eb7 src/com/owncloud/android/ui/fragment/PreviewImageFragment.java (David A. Velasco 2013-02-25 12:24:14 +0100  69)  * 
3d272eb7 src/com/owncloud/android/ui/fragment/PreviewImageFragment.java (David A. Velasco 2013-02-25 12:24:14 +0100  70)  * Trying to get an instance with NULL {@link OCFile} or ownCloud {@link Account} values will produce an {@link IllegalStateException}.
3d272eb7 src/com/owncloud/android/ui/fragment/PreviewImageFragment.java (David A. Velasco 2013-02-25 12:24:14 +0100  71)  * 
3d272eb7 src/com/owncloud/android/ui/fragment/PreviewImageFragment.java (David A. Velasco 2013-02-25 12:24:14 +0100  72)  * If the {@link OCFile} passed is not downloaded, an {@link IllegalStateException} is generated on instantiation too.
3d272eb7 src/com/owncloud/android/ui/fragment/PreviewImageFragment.java (David A. Velasco 2013-02-25 12:24:14 +0100  73)  * 
3d272eb7 src/com/owncloud/android/ui/fragment/PreviewImageFragment.java (David A. Velasco 2013-02-25 12:24:14 +0100  74)  * @author David A. Velasco
3d272eb7 src/com/owncloud/android/ui/fragment/PreviewImageFragment.java (David A. Velasco 2013-02-25 12:24:14 +0100  75)  */
fd396289 src/com/owncloud/android/ui/preview/PreviewImageFragment.java  (David A. Velasco 2013-05-30 17:53:21 +0200  76) public class PreviewImageFragment extends FileFragment implements   OnRemoteOperationListener, 
6cfa24f5 src/com/owncloud/android/ui/preview/PreviewImageFragment.java  (David A. Velasco 2013-03-01 09:54:49 +0100  77)                                                                         ConfirmationDialogFragment.ConfirmationDialogFragmentListener {
3d272eb7 src/com/owncloud/android/ui/fragment/PreviewImageFragment.java (David A. Velasco 2013-02-25 12:24:14 +0100  78)     public static final String EXTRA_FILE = "FILE";
3d272eb7 src/com/owncloud/android/ui/fragment/PreviewImageFragment.java (David A. Velasco 2013-02-25 12:24:14 +0100  79)     public static final String EXTRA_ACCOUNT = "ACCOUNT";
3d272eb7 src/com/owncloud/android/ui/fragment/PreviewImageFragment.java (David A. Velasco 2013-02-25 12:24:14 +0100  80) 
3d272eb7 src/com/owncloud/android/ui/fragment/PreviewImageFragment.java (David A. Velasco 2013-02-25 12:24:14 +0100  81)     private View mView;
3d272eb7 src/com/owncloud/android/ui/fragment/PreviewImageFragment.java (David A. Velasco 2013-02-25 12:24:14 +0100  82)     private Account mAccount;
3d272eb7 src/com/owncloud/android/ui/fragment/PreviewImageFragment.java (David A. Velasco 2013-02-25 12:24:14 +0100  83)     private FileDataStorageManager mStorageManager;
3d272eb7 src/com/owncloud/android/ui/fragment/PreviewImageFragment.java (David A. Velasco 2013-02-25 12:24:14 +0100  84)     private ImageView mImageView;
6cfa24f5 src/com/owncloud/android/ui/preview/PreviewImageFragment.java  (David A. Velasco 2013-03-01 09:54:49 +0100  85)     private TextView mMessageView;
427a3e63 src/com/owncloud/android/ui/preview/PreviewImageFragment.java  (David A. Velasco 2013-03-01 10:49:35 +0100  86)     private ProgressBar mProgressWheel;
6cfa24f5 src/com/owncloud/android/ui/preview/PreviewImageFragment.java  (David A. Velasco 2013-03-01 09:54:49 +0100  87) 
3d272eb7 src/com/owncloud/android/ui/fragment/PreviewImageFragment.java (David A. Velasco 2013-02-25 12:24:14 +0100  88)     public Bitmap mBitmap = null;
3d272eb7 src/com/owncloud/android/ui/fragment/PreviewImageFragment.java (David A. Velasco 2013-02-25 12:24:14 +0100  89)     
3d272eb7 src/com/owncloud/android/ui/fragment/PreviewImageFragment.java (David A. Velasco 2013-02-25 12:24:14 +0100  90)     private Handler mHandler;
3d272eb7 src/com/owncloud/android/ui/fragment/PreviewImageFragment.java (David A. Velasco 2013-02-25 12:24:14 +0100  91)     private RemoteOperation mLastRemoteOperation;
3d272eb7 src/com/owncloud/android/ui/fragment/PreviewImageFragment.java (David A. Velasco 2013-02-25 12:24:14 +0100  92)     
3d272eb7 src/com/owncloud/android/ui/fragment/PreviewImageFragment.java (David A. Velasco 2013-02-25 12:24:14 +0100  93)     private static final String TAG = PreviewImageFragment.class.getSimpleName();
3d272eb7 src/com/owncloud/android/ui/fragment/PreviewImageFragment.java (David A. Velasco 2013-02-25 12:24:14 +0100  94) 
86cec34b src/com/owncloud/android/ui/preview/PreviewImageFragment.java  (David A. Velasco 2013-02-27 14:08:58 +0100  95)     private boolean mIgnoreFirstSavedState;
6cfa24f5 src/com/owncloud/android/ui/preview/PreviewImageFragment.java  (David A. Velasco 2013-03-01 09:54:49 +0100  96) 
3d272eb7 src/com/owncloud/android/ui/fragment/PreviewImageFragment.java (David A. Velasco 2013-02-25 12:24:14 +0100  97)     
3d272eb7 src/com/owncloud/android/ui/fragment/PreviewImageFragment.java (David A. Velasco 2013-02-25 12:24:14 +0100  98)     /**
3d272eb7 src/com/owncloud/android/ui/fragment/PreviewImageFragment.java (David A. Velasco 2013-02-25 12:24:14 +0100  99)      * Creates a fragment to preview an image.
3d272eb7 src/com/owncloud/android/ui/fragment/PreviewImageFragment.java (David A. Velasco 2013-02-25 12:24:14 +0100 100)      * 
3d272eb7 src/com/owncloud/android/ui/fragment/PreviewImageFragment.java (David A. Velasco 2013-02-25 12:24:14 +0100 101)      * When 'imageFile' or 'ocAccount' are null
3d272eb7 src/com/owncloud/android/ui/fragment/PreviewImageFragment.java (David A. Velasco 2013-02-25 12:24:14 +0100 102)      * 
86cec34b src/com/owncloud/android/ui/preview/PreviewImageFragment.java  (David A. Velasco 2013-02-27 14:08:58 +0100 103)      * @param imageFile                 An {@link OCFile} to preview as an image in the fragment
86cec34b src/com/owncloud/android/ui/preview/PreviewImageFragment.java  (David A. Velasco 2013-02-27 14:08:58 +0100 104)      * @param ocAccount                 An ownCloud account; needed to start downloads
86cec34b src/com/owncloud/android/ui/preview/PreviewImageFragment.java  (David A. Velasco 2013-02-27 14:08:58 +0100 105)      * @param ignoreFirstSavedState     Flag to work around an unexpected behaviour of {@link FragmentStatePagerAdapter}; TODO better solution 
3d272eb7 src/com/owncloud/android/ui/fragment/PreviewImageFragment.java (David A. Velasco 2013-02-25 12:24:14 +0100 106)      */
86cec34b src/com/owncloud/android/ui/preview/PreviewImageFragment.java  (David A. Velasco 2013-02-27 14:08:58 +0100 107)     public PreviewImageFragment(OCFile fileToDetail, Account ocAccount, boolean ignoreFirstSavedState) {
fd396289 src/com/owncloud/android/ui/preview/PreviewImageFragment.java  (David A. Velasco 2013-05-30 17:53:21 +0200 108)         super(fileToDetail);
3d272eb7 src/com/owncloud/android/ui/fragment/PreviewImageFragment.java (David A. Velasco 2013-02-25 12:24:14 +0100 109)         mAccount = ocAccount;
86cec34b src/com/owncloud/android/ui/preview/PreviewImageFragment.java  (David A. Velasco 2013-02-27 14:08:58 +0100 110)         mStorageManager = null; // we need a context to init this; the container activity is not available yet at this moment
86cec34b src/com/owncloud/android/ui/preview/PreviewImageFragment.java  (David A. Velasco 2013-02-27 14:08:58 +0100 111)         mIgnoreFirstSavedState = ignoreFirstSavedState;
3d272eb7 src/com/owncloud/android/ui/fragment/PreviewImageFragment.java (David A. Velasco 2013-02-25 12:24:14 +0100 112)     }
3d272eb7 src/com/owncloud/android/ui/fragment/PreviewImageFragment.java (David A. Velasco 2013-02-25 12:24:14 +0100 113)     
3d272eb7 src/com/owncloud/android/ui/fragment/PreviewImageFragment.java (David A. Velasco 2013-02-25 12:24:14 +0100 114)     
3d272eb7 src/com/owncloud/android/ui/fragment/PreviewImageFragment.java (David A. Velasco 2013-02-25 12:24:14 +0100 115)     /**
3d272eb7 src/com/owncloud/android/ui/fragment/PreviewImageFragment.java (David A. Velasco 2013-02-25 12:24:14 +0100 116)      *  Creates an empty fragment for image previews.
3d272eb7 src/com/owncloud/android/ui/fragment/PreviewImageFragment.java (David A. Velasco 2013-02-25 12:24:14 +0100 117)      * 
3d272eb7 src/com/owncloud/android/ui/fragment/PreviewImageFragment.java (David A. Velasco 2013-02-25 12:24:14 +0100 118)      *  MUST BE KEPT: the system uses it when tries to reinstantiate a fragment automatically (for instance, when the device is turned a aside).
3d272eb7 src/com/owncloud/android/ui/fragment/PreviewImageFragment.java (David A. Velasco 2013-02-25 12:24:14 +0100 119)      * 
3d272eb7 src/com/owncloud/android/ui/fragment/PreviewImageFragment.java (David A. Velasco 2013-02-25 12:24:14 +0100 120)      *  DO NOT CALL IT: an {@link OCFile} and {@link Account} must be provided for a successful construction 
3d272eb7 src/com/owncloud/android/ui/fragment/PreviewImageFragment.java (David A. Velasco 2013-02-25 12:24:14 +0100 121)      */
3d272eb7 src/com/owncloud/android/ui/fragment/PreviewImageFragment.java (David A. Velasco 2013-02-25 12:24:14 +0100 122)     public PreviewImageFragment() {
fd396289 src/com/owncloud/android/ui/preview/PreviewImageFragment.java  (David A. Velasco 2013-05-30 17:53:21 +0200 123)         super();
3d272eb7 src/com/owncloud/android/ui/fragment/PreviewImageFragment.java (David A. Velasco 2013-02-25 12:24:14 +0100 124)         mAccount = null;
3d272eb7 src/com/owncloud/android/ui/fragment/PreviewImageFragment.java (David A. Velasco 2013-02-25 12:24:14 +0100 125)         mStorageManager = null;
86cec34b src/com/owncloud/android/ui/preview/PreviewImageFragment.java  (David A. Velasco 2013-02-27 14:08:58 +0100 126)         mIgnoreFirstSavedState = false;
3d272eb7 src/com/owncloud/android/ui/fragment/PreviewImageFragment.java (David A. Velasco 2013-02-25 12:24:14 +0100 127)     }
3d272eb7 src/com/owncloud/android/ui/fragment/PreviewImageFragment.java (David A. Velasco 2013-02-25 12:24:14 +0100 128)     
3d272eb7 src/com/owncloud/android/ui/fragment/PreviewImageFragment.java (David A. Velasco 2013-02-25 12:24:14 +0100 129)     
3d272eb7 src/com/owncloud/android/ui/fragment/PreviewImageFragment.java (David A. Velasco 2013-02-25 12:24:14 +0100 130)     /**
3d272eb7 src/com/owncloud/android/ui/fragment/PreviewImageFragment.java (David A. Velasco 2013-02-25 12:24:14 +0100 131)      * {@inheritDoc}
3d272eb7 src/com/owncloud/android/ui/fragment/PreviewImageFragment.java (David A. Velasco 2013-02-25 12:24:14 +0100 132)      */
3d272eb7 src/com/owncloud/android/ui/fragment/PreviewImageFragment.java (David A. Velasco 2013-02-25 12:24:14 +0100 133)     @Override
3d272eb7 src/com/owncloud/android/ui/fragment/PreviewImageFragment.java (David A. Velasco 2013-02-25 12:24:14 +0100 134)     public void onCreate(Bundle savedInstanceState) {
3d272eb7 src/com/owncloud/android/ui/fragment/PreviewImageFragment.java (David A. Velasco 2013-02-25 12:24:14 +0100 135)         super.onCreate(savedInstanceState);
3d272eb7 src/com/owncloud/android/ui/fragment/PreviewImageFragment.java (David A. Velasco 2013-02-25 12:24:14 +0100 136)         mHandler = new Handler();
3d272eb7 src/com/owncloud/android/ui/fragment/PreviewImageFragment.java (David A. Velasco 2013-02-25 12:24:14 +0100 137)         setHasOptionsMenu(true);
3d272eb7 src/com/owncloud/android/ui/fragment/PreviewImageFragment.java (David A. Velasco 2013-02-25 12:24:14 +0100 138)     }
3d272eb7 src/com/owncloud/android/ui/fragment/PreviewImageFragment.java (David A. Velasco 2013-02-25 12:24:14 +0100 139)     
3d272eb7 src/com/owncloud/android/ui/fragment/PreviewImageFragment.java (David A. Velasco 2013-02-25 12:24:14 +0100 140) 
3d272eb7 src/com/owncloud/android/ui/fragment/PreviewImageFragment.java (David A. Velasco 2013-02-25 12:24:14 +0100 141)     /**
3d272eb7 src/com/owncloud/android/ui/fragment/PreviewImageFragment.java (David A. Velasco 2013-02-25 12:24:14 +0100 142)      * {@inheritDoc}
3d272eb7 src/com/owncloud/android/ui/fragment/PreviewImageFragment.java (David A. Velasco 2013-02-25 12:24:14 +0100 143)      */
3d272eb7 src/com/owncloud/android/ui/fragment/PreviewImageFragment.java (David A. Velasco 2013-02-25 12:24:14 +0100 144)     @Override
3d272eb7 src/com/owncloud/android/ui/fragment/PreviewImageFragment.java (David A. Velasco 2013-02-25 12:24:14 +0100 145)     public View onCreateView(LayoutInflater inflater, ViewGroup container,
3d272eb7 src/com/owncloud/android/ui/fragment/PreviewImageFragment.java (David A. Velasco 2013-02-25 12:24:14 +0100 146)             Bundle savedInstanceState) {
3d272eb7 src/com/owncloud/android/ui/fragment/PreviewImageFragment.java (David A. Velasco 2013-02-25 12:24:14 +0100 147)         super.onCreateView(inflater, container, savedInstanceState);
3d272eb7 src/com/owncloud/android/ui/fragment/PreviewImageFragment.java (David A. Velasco 2013-02-25 12:24:14 +0100 148)         mView = inflater.inflate(R.layout.preview_image_fragment, container, false);
3d272eb7 src/com/owncloud/android/ui/fragment/PreviewImageFragment.java (David A. Velasco 2013-02-25 12:24:14 +0100 149)         mImageView = (ImageView)mView.findViewById(R.id.image);
427a3e63 src/com/owncloud/android/ui/preview/PreviewImageFragment.java  (David A. Velasco 2013-03-01 10:49:35 +0100 150)         mImageView.setVisibility(View.GONE);
6cfa24f5 src/com/owncloud/android/ui/preview/PreviewImageFragment.java  (David A. Velasco 2013-03-01 09:54:49 +0100 151)         mView.setOnTouchListener((OnTouchListener)getActivity());   // WATCH OUT THAT CAST
6cfa24f5 src/com/owncloud/android/ui/preview/PreviewImageFragment.java  (David A. Velasco 2013-03-01 09:54:49 +0100 152)         mMessageView = (TextView)mView.findViewById(R.id.message);
6cfa24f5 src/com/owncloud/android/ui/preview/PreviewImageFragment.java  (David A. Velasco 2013-03-01 09:54:49 +0100 153)         mMessageView.setVisibility(View.GONE);
427a3e63 src/com/owncloud/android/ui/preview/PreviewImageFragment.java  (David A. Velasco 2013-03-01 10:49:35 +0100 154)         mProgressWheel = (ProgressBar)mView.findViewById(R.id.progressWheel);
427a3e63 src/com/owncloud/android/ui/preview/PreviewImageFragment.java  (David A. Velasco 2013-03-01 10:49:35 +0100 155)         mProgressWheel.setVisibility(View.VISIBLE);
3d272eb7 src/com/owncloud/android/ui/fragment/PreviewImageFragment.java (David A. Velasco 2013-02-25 12:24:14 +0100 156)         return mView;
3d272eb7 src/com/owncloud/android/ui/fragment/PreviewImageFragment.java (David A. Velasco 2013-02-25 12:24:14 +0100 157)     }
3d272eb7 src/com/owncloud/android/ui/fragment/PreviewImageFragment.java (David A. Velasco 2013-02-25 12:24:14 +0100 158)     
3d272eb7 src/com/owncloud/android/ui/fragment/PreviewImageFragment.java (David A. Velasco 2013-02-25 12:24:14 +0100 159) 
3d272eb7 src/com/owncloud/android/ui/fragment/PreviewImageFragment.java (David A. Velasco 2013-02-25 12:24:14 +0100 160)     /**
3d272eb7 src/com/owncloud/android/ui/fragment/PreviewImageFragment.java (David A. Velasco 2013-02-25 12:24:14 +0100 161)      * {@inheritDoc}
3d272eb7 src/com/owncloud/android/ui/fragment/PreviewImageFragment.java (David A. Velasco 2013-02-25 12:24:14 +0100 162)      */
3d272eb7 src/com/owncloud/android/ui/fragment/PreviewImageFragment.java (David A. Velasco 2013-02-25 12:24:14 +0100 163)     @Override
3d272eb7 src/com/owncloud/android/ui/fragment/PreviewImageFragment.java (David A. Velasco 2013-02-25 12:24:14 +0100 164)     public void onAttach(Activity activity) {
3d272eb7 src/com/owncloud/android/ui/fragment/PreviewImageFragment.java (David A. Velasco 2013-02-25 12:24:14 +0100 165)         super.onAttach(activity);
3d272eb7 src/com/owncloud/android/ui/fragment/PreviewImageFragment.java (David A. Velasco 2013-02-25 12:24:14 +0100 166)         if (!(activity instanceof FileFragment.ContainerActivity))
3d272eb7 src/com/owncloud/android/ui/fragment/PreviewImageFragment.java (David A. Velasco 2013-02-25 12:24:14 +0100 167)             throw new ClassCastException(activity.toString() + " must implement " + FileFragment.ContainerActivity.class.getSimpleName());
3d272eb7 src/com/owncloud/android/ui/fragment/PreviewImageFragment.java (David A. Velasco 2013-02-25 12:24:14 +0100 168)     }
3d272eb7 src/com/owncloud/android/ui/fragment/PreviewImageFragment.java (David A. Velasco 2013-02-25 12:24:14 +0100 169)     
3d272eb7 src/com/owncloud/android/ui/fragment/PreviewImageFragment.java (David A. Velasco 2013-02-25 12:24:14 +0100 170)     
3d272eb7 src/com/owncloud/android/ui/fragment/PreviewImageFragment.java (David A. Velasco 2013-02-25 12:24:14 +0100 171)     /**
3d272eb7 src/com/owncloud/android/ui/fragment/PreviewImageFragment.java (David A. Velasco 2013-02-25 12:24:14 +0100 172)      * {@inheritDoc}
3d272eb7 src/com/owncloud/android/ui/fragment/PreviewImageFragment.java (David A. Velasco 2013-02-25 12:24:14 +0100 173)      */
3d272eb7 src/com/owncloud/android/ui/fragment/PreviewImageFragment.java (David A. Velasco 2013-02-25 12:24:14 +0100 174)     @Override
3d272eb7 src/com/owncloud/android/ui/fragment/PreviewImageFragment.java (David A. Velasco 2013-02-25 12:24:14 +0100 175)     public void onActivityCreated(Bundle savedInstanceState) {
3d272eb7 src/com/owncloud/android/ui/fragment/PreviewImageFragment.java (David A. Velasco 2013-02-25 12:24:14 +0100 176)         super.onActivityCreated(savedInstanceState);
3d272eb7 src/com/owncloud/android/ui/fragment/PreviewImageFragment.java (David A. Velasco 2013-02-25 12:24:14 +0100 177)         mStorageManager = new FileDataStorageManager(mAccount, getActivity().getApplicationContext().getContentResolver());
3d272eb7 src/com/owncloud/android/ui/fragment/PreviewImageFragment.java (David A. Velasco 2013-02-25 12:24:14 +0100 178)         if (savedInstanceState != null) {
86cec34b src/com/owncloud/android/ui/preview/PreviewImageFragment.java  (David A. Velasco 2013-02-27 14:08:58 +0100 179)             if (!mIgnoreFirstSavedState) {
fd396289 src/com/owncloud/android/ui/preview/PreviewImageFragment.java  (David A. Velasco 2013-05-30 17:53:21 +0200 180)                 setFile((OCFile)savedInstanceState.getParcelable(PreviewImageFragment.EXTRA_FILE));
86cec34b src/com/owncloud/android/ui/preview/PreviewImageFragment.java  (David A. Velasco 2013-02-27 14:08:58 +0100 181)                 mAccount = savedInstanceState.getParcelable(PreviewImageFragment.EXTRA_ACCOUNT);
86cec34b src/com/owncloud/android/ui/preview/PreviewImageFragment.java  (David A. Velasco 2013-02-27 14:08:58 +0100 182)             } else {
86cec34b src/com/owncloud/android/ui/preview/PreviewImageFragment.java  (David A. Velasco 2013-02-27 14:08:58 +0100 183)                 mIgnoreFirstSavedState = false;
86cec34b src/com/owncloud/android/ui/preview/PreviewImageFragment.java  (David A. Velasco 2013-02-27 14:08:58 +0100 184)             }
3d272eb7 src/com/owncloud/android/ui/fragment/PreviewImageFragment.java (David A. Velasco 2013-02-25 12:24:14 +0100 185)         }
fd396289 src/com/owncloud/android/ui/preview/PreviewImageFragment.java  (David A. Velasco 2013-05-30 17:53:21 +0200 186)         if (getFile() == null) {
3d272eb7 src/com/owncloud/android/ui/fragment/PreviewImageFragment.java (David A. Velasco 2013-02-25 12:24:14 +0100 187)             throw new IllegalStateException("Instanced with a NULL OCFile");
3d272eb7 src/com/owncloud/android/ui/fragment/PreviewImageFragment.java (David A. Velasco 2013-02-25 12:24:14 +0100 188)         }
3d272eb7 src/com/owncloud/android/ui/fragment/PreviewImageFragment.java (David A. Velasco 2013-02-25 12:24:14 +0100 189)         if (mAccount == null) {
3d272eb7 src/com/owncloud/android/ui/fragment/PreviewImageFragment.java (David A. Velasco 2013-02-25 12:24:14 +0100 190)             throw new IllegalStateException("Instanced with a NULL ownCloud Account");
3d272eb7 src/com/owncloud/android/ui/fragment/PreviewImageFragment.java (David A. Velasco 2013-02-25 12:24:14 +0100 191)         }
fd396289 src/com/owncloud/android/ui/preview/PreviewImageFragment.java  (David A. Velasco 2013-05-30 17:53:21 +0200 192)         if (!getFile().isDown()) {
3d272eb7 src/com/owncloud/android/ui/fragment/PreviewImageFragment.java (David A. Velasco 2013-02-25 12:24:14 +0100 193)             throw new IllegalStateException("There is no local file to preview");
3d272eb7 src/com/owncloud/android/ui/fragment/PreviewImageFragment.java (David A. Velasco 2013-02-25 12:24:14 +0100 194)         }
3d272eb7 src/com/owncloud/android/ui/fragment/PreviewImageFragment.java (David A. Velasco 2013-02-25 12:24:14 +0100 195)     }
3d272eb7 src/com/owncloud/android/ui/fragment/PreviewImageFragment.java (David A. Velasco 2013-02-25 12:24:14 +0100 196)         
3d272eb7 src/com/owncloud/android/ui/fragment/PreviewImageFragment.java (David A. Velasco 2013-02-25 12:24:14 +0100 197) 
3d272eb7 src/com/owncloud/android/ui/fragment/PreviewImageFragment.java (David A. Velasco 2013-02-25 12:24:14 +0100 198)     /**
3d272eb7 src/com/owncloud/android/ui/fragment/PreviewImageFragment.java (David A. Velasco 2013-02-25 12:24:14 +0100 199)      * {@inheritDoc}
3d272eb7 src/com/owncloud/android/ui/fragment/PreviewImageFragment.java (David A. Velasco 2013-02-25 12:24:14 +0100 200)      */
3d272eb7 src/com/owncloud/android/ui/fragment/PreviewImageFragment.java (David A. Velasco 2013-02-25 12:24:14 +0100 201)     @Override
3d272eb7 src/com/owncloud/android/ui/fragment/PreviewImageFragment.java (David A. Velasco 2013-02-25 12:24:14 +0100 202)     public void onSaveInstanceState(Bundle outState) {
3d272eb7 src/com/owncloud/android/ui/fragment/PreviewImageFragment.java (David A. Velasco 2013-02-25 12:24:14 +0100 203)         super.onSaveInstanceState(outState);
fd396289 src/com/owncloud/android/ui/preview/PreviewImageFragment.java  (David A. Velasco 2013-05-30 17:53:21 +0200 204)         outState.putParcelable(PreviewImageFragment.EXTRA_FILE, getFile());
3d272eb7 src/com/owncloud/android/ui/fragment/PreviewImageFragment.java (David A. Velasco 2013-02-25 12:24:14 +0100 205)         outState.putParcelable(PreviewImageFragment.EXTRA_ACCOUNT, mAccount);
3d272eb7 src/com/owncloud/android/ui/fragment/PreviewImageFragment.java (David A. Velasco 2013-02-25 12:24:14 +0100 206)     }
3d272eb7 src/com/owncloud/android/ui/fragment/PreviewImageFragment.java (David A. Velasco 2013-02-25 12:24:14 +0100 207)     
3d272eb7 src/com/owncloud/android/ui/fragment/PreviewImageFragment.java (David A. Velasco 2013-02-25 12:24:14 +0100 208) 
3d272eb7 src/com/owncloud/android/ui/fragment/PreviewImageFragment.java (David A. Velasco 2013-02-25 12:24:14 +0100 209)     @Override
3d272eb7 src/com/owncloud/android/ui/fragment/PreviewImageFragment.java (David A. Velasco 2013-02-25 12:24:14 +0100 210)     public void onStart() {
3d272eb7 src/com/owncloud/android/ui/fragment/PreviewImageFragment.java (David A. Velasco 2013-02-25 12:24:14 +0100 211)         super.onStart();
fd396289 src/com/owncloud/android/ui/preview/PreviewImageFragment.java  (David A. Velasco 2013-05-30 17:53:21 +0200 212)         if (getFile() != null) {
427a3e63 src/com/owncloud/android/ui/preview/PreviewImageFragment.java  (David A. Velasco 2013-03-01 10:49:35 +0100 213)            BitmapLoader bl = new BitmapLoader(mImageView, mMessageView, mProgressWheel);
fd396289 src/com/owncloud/android/ui/preview/PreviewImageFragment.java  (David A. Velasco 2013-05-30 17:53:21 +0200 214)            bl.execute(new String[]{getFile().getStoragePath()});
3d272eb7 src/com/owncloud/android/ui/fragment/PreviewImageFragment.java (David A. Velasco 2013-02-25 12:24:14 +0100 215)         }
3d272eb7 src/com/owncloud/android/ui/fragment/PreviewImageFragment.java (David A. Velasco 2013-02-25 12:24:14 +0100 216)     }
3d272eb7 src/com/owncloud/android/ui/fragment/PreviewImageFragment.java (David A. Velasco 2013-02-25 12:24:14 +0100 217)     
3d272eb7 src/com/owncloud/android/ui/fragment/PreviewImageFragment.java (David A. Velasco 2013-02-25 12:24:14 +0100 218)     
3d272eb7 src/com/owncloud/android/ui/fragment/PreviewImageFragment.java (David A. Velasco 2013-02-25 12:24:14 +0100 219)     /**
3d272eb7 src/com/owncloud/android/ui/fragment/PreviewImageFragment.java (David A. Velasco 2013-02-25 12:24:14 +0100 220)      * {@inheritDoc}
3d272eb7 src/com/owncloud/android/ui/fragment/PreviewImageFragment.java (David A. Velasco 2013-02-25 12:24:14 +0100 221)      */
3d272eb7 src/com/owncloud/android/ui/fragment/PreviewImageFragment.java (David A. Velasco 2013-02-25 12:24:14 +0100 222)     @Override
3d272eb7 src/com/owncloud/android/ui/fragment/PreviewImageFragment.java (David A. Velasco 2013-02-25 12:24:14 +0100 223)     public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
3d272eb7 src/com/owncloud/android/ui/fragment/PreviewImageFragment.java (David A. Velasco 2013-02-25 12:24:14 +0100 224)         super.onCreateOptionsMenu(menu, inflater);
3d272eb7 src/com/owncloud/android/ui/fragment/PreviewImageFragment.java (David A. Velasco 2013-02-25 12:24:14 +0100 225) 
3d272eb7 src/com/owncloud/android/ui/fragment/PreviewImageFragment.java (David A. Velasco 2013-02-25 12:24:14 +0100 226)         inflater.inflate(R.menu.file_actions_menu, menu);
3d272eb7 src/com/owncloud/android/ui/fragment/PreviewImageFragment.java (David A. Velasco 2013-02-25 12:24:14 +0100 227)         List<Integer> toHide = new ArrayList<Integer>();    
3d272eb7 src/com/owncloud/android/ui/fragment/PreviewImageFragment.java (David A. Velasco 2013-02-25 12:24:14 +0100 228)         
3d272eb7 src/com/owncloud/android/ui/fragment/PreviewImageFragment.java (David A. Velasco 2013-02-25 12:24:14 +0100 229)         MenuItem item = null;
3d272eb7 src/com/owncloud/android/ui/fragment/PreviewImageFragment.java (David A. Velasco 2013-02-25 12:24:14 +0100 230)         toHide.add(R.id.action_cancel_download);
3d272eb7 src/com/owncloud/android/ui/fragment/PreviewImageFragment.java (David A. Velasco 2013-02-25 12:24:14 +0100 231)         toHide.add(R.id.action_cancel_upload);
3d272eb7 src/com/owncloud/android/ui/fragment/PreviewImageFragment.java (David A. Velasco 2013-02-25 12:24:14 +0100 232)         toHide.add(R.id.action_download_file);
3d272eb7 src/com/owncloud/android/ui/fragment/PreviewImageFragment.java (David A. Velasco 2013-02-25 12:24:14 +0100 233)         toHide.add(R.id.action_rename_file);    // by now
3d272eb7 src/com/owncloud/android/ui/fragment/PreviewImageFragment.java (David A. Velasco 2013-02-25 12:24:14 +0100 234) 
3d272eb7 src/com/owncloud/android/ui/fragment/PreviewImageFragment.java (David A. Velasco 2013-02-25 12:24:14 +0100 235)         for (int i : toHide) {
3d272eb7 src/com/owncloud/android/ui/fragment/PreviewImageFragment.java (David A. Velasco 2013-02-25 12:24:14 +0100 236)             item = menu.findItem(i);
3d272eb7 src/com/owncloud/android/ui/fragment/PreviewImageFragment.java (David A. Velasco 2013-02-25 12:24:14 +0100 237)             if (item != null) {
3d272eb7 src/com/owncloud/android/ui/fragment/PreviewImageFragment.java (David A. Velasco 2013-02-25 12:24:14 +0100 238)                 item.setVisible(false);
3d272eb7 src/com/owncloud/android/ui/fragment/PreviewImageFragment.java (David A. Velasco 2013-02-25 12:24:14 +0100 239)                 item.setEnabled(false);
3d272eb7 src/com/owncloud/android/ui/fragment/PreviewImageFragment.java (David A. Velasco 2013-02-25 12:24:14 +0100 240)             }
3d272eb7 src/com/owncloud/android/ui/fragment/PreviewImageFragment.java (David A. Velasco 2013-02-25 12:24:14 +0100 241)         }
3d272eb7 src/com/owncloud/android/ui/fragment/PreviewImageFragment.java (David A. Velasco 2013-02-25 12:24:14 +0100 242)         
3d272eb7 src/com/owncloud/android/ui/fragment/PreviewImageFragment.java (David A. Velasco 2013-02-25 12:24:14 +0100 243)     }
3d272eb7 src/com/owncloud/android/ui/fragment/PreviewImageFragment.java (David A. Velasco 2013-02-25 12:24:14 +0100 244) 
3d272eb7 src/com/owncloud/android/ui/fragment/PreviewImageFragment.java (David A. Velasco 2013-02-25 12:24:14 +0100 245)     
3d272eb7 src/com/owncloud/android/ui/fragment/PreviewImageFragment.java (David A. Velasco 2013-02-25 12:24:14 +0100 246)     /**
3d272eb7 src/com/owncloud/android/ui/fragment/PreviewImageFragment.java (David A. Velasco 2013-02-25 12:24:14 +0100 247)      * {@inheritDoc}
3d272eb7 src/com/owncloud/android/ui/fragment/PreviewImageFragment.java (David A. Velasco 2013-02-25 12:24:14 +0100 248)      */
3d272eb7 src/com/owncloud/android/ui/fragment/PreviewImageFragment.java (David A. Velasco 2013-02-25 12:24:14 +0100 249)     @Override
3d272eb7 src/com/owncloud/android/ui/fragment/PreviewImageFragment.java (David A. Velasco 2013-02-25 12:24:14 +0100 250)     public boolean onOptionsItemSelected(MenuItem item) {
3d272eb7 src/com/owncloud/android/ui/fragment/PreviewImageFragment.java (David A. Velasco 2013-02-25 12:24:14 +0100 251)         switch (item.getItemId()) {
3d272eb7 src/com/owncloud/android/ui/fragment/PreviewImageFragment.java (David A. Velasco 2013-02-25 12:24:14 +0100 252)             case R.id.action_open_file_with: {
3d272eb7 src/com/owncloud/android/ui/fragment/PreviewImageFragment.java (David A. Velasco 2013-02-25 12:24:14 +0100 253)                 openFile();
3d272eb7 src/com/owncloud/android/ui/fragment/PreviewImageFragment.java (David A. Velasco 2013-02-25 12:24:14 +0100 254)                 return true;
3d272eb7 src/com/owncloud/android/ui/fragment/PreviewImageFragment.java (David A. Velasco 2013-02-25 12:24:14 +0100 255)             }
3d272eb7 src/com/owncloud/android/ui/fragment/PreviewImageFragment.java (David A. Velasco 2013-02-25 12:24:14 +0100 256)             case R.id.action_remove_file: {
3d272eb7 src/com/owncloud/android/ui/fragment/PreviewImageFragment.java (David A. Velasco 2013-02-25 12:24:14 +0100 257)                 removeFile();
3d272eb7 src/com/owncloud/android/ui/fragment/PreviewImageFragment.java (David A. Velasco 2013-02-25 12:24:14 +0100 258)                 return true;
3d272eb7 src/com/owncloud/android/ui/fragment/PreviewImageFragment.java (David A. Velasco 2013-02-25 12:24:14 +0100 259)             }
3d272eb7 src/com/owncloud/android/ui/fragment/PreviewImageFragment.java (David A. Velasco 2013-02-25 12:24:14 +0100 260)             case R.id.action_see_details: {
3d272eb7 src/com/owncloud/android/ui/fragment/PreviewImageFragment.java (David A. Velasco 2013-02-25 12:24:14 +0100 261)                 seeDetails();
3d272eb7 src/com/owncloud/android/ui/fragment/PreviewImageFragment.java (David A. Velasco 2013-02-25 12:24:14 +0100 262)                 return true;
3d272eb7 src/com/owncloud/android/ui/fragment/PreviewImageFragment.java (David A. Velasco 2013-02-25 12:24:14 +0100 263)             }
3d272eb7 src/com/owncloud/android/ui/fragment/PreviewImageFragment.java (David A. Velasco 2013-02-25 12:24:14 +0100 264)             
3d272eb7 src/com/owncloud/android/ui/fragment/PreviewImageFragment.java (David A. Velasco 2013-02-25 12:24:14 +0100 265)             default:
3d272eb7 src/com/owncloud/android/ui/fragment/PreviewImageFragment.java (David A. Velasco 2013-02-25 12:24:14 +0100 266)                 return false;
3d272eb7 src/com/owncloud/android/ui/fragment/PreviewImageFragment.java (David A. Velasco 2013-02-25 12:24:14 +0100 267)         }
3d272eb7 src/com/owncloud/android/ui/fragment/PreviewImageFragment.java (David A. Velasco 2013-02-25 12:24:14 +0100 268)     }
3d272eb7 src/com/owncloud/android/ui/fragment/PreviewImageFragment.java (David A. Velasco 2013-02-25 12:24:14 +0100 269) 
3d272eb7 src/com/owncloud/android/ui/fragment/PreviewImageFragment.java (David A. Velasco 2013-02-25 12:24:14 +0100 270)     
3d272eb7 src/com/owncloud/android/ui/fragment/PreviewImageFragment.java (David A. Velasco 2013-02-25 12:24:14 +0100 271)     private void seeDetails() {
fd396289 src/com/owncloud/android/ui/preview/PreviewImageFragment.java  (David A. Velasco 2013-05-30 17:53:21 +0200 272)         ((FileFragment.ContainerActivity)getActivity()).showDetails(getFile());        
3d272eb7 src/com/owncloud/android/ui/fragment/PreviewImageFragment.java (David A. Velasco 2013-02-25 12:24:14 +0100 273)     }
3d272eb7 src/com/owncloud/android/ui/fragment/PreviewImageFragment.java (David A. Velasco 2013-02-25 12:24:14 +0100 274) 
3d272eb7 src/com/owncloud/android/ui/fragment/PreviewImageFragment.java (David A. Velasco 2013-02-25 12:24:14 +0100 275) 
3d272eb7 src/com/owncloud/android/ui/fragment/PreviewImageFragment.java (David A. Velasco 2013-02-25 12:24:14 +0100 276)     @Override
3d272eb7 src/com/owncloud/android/ui/fragment/PreviewImageFragment.java (David A. Velasco 2013-02-25 12:24:14 +0100 277)     public void onResume() {
3d272eb7 src/com/owncloud/android/ui/fragment/PreviewImageFragment.java (David A. Velasco 2013-02-25 12:24:14 +0100 278)         super.onResume();
3d272eb7 src/com/owncloud/android/ui/fragment/PreviewImageFragment.java (David A. Velasco 2013-02-25 12:24:14 +0100 279)     }
3d272eb7 src/com/owncloud/android/ui/fragment/PreviewImageFragment.java (David A. Velasco 2013-02-25 12:24:14 +0100 280) 
3d272eb7 src/com/owncloud/android/ui/fragment/PreviewImageFragment.java (David A. Velasco 2013-02-25 12:24:14 +0100 281) 
3d272eb7 src/com/owncloud/android/ui/fragment/PreviewImageFragment.java (David A. Velasco 2013-02-25 12:24:14 +0100 282)     @Override
3d272eb7 src/com/owncloud/android/ui/fragment/PreviewImageFragment.java (David A. Velasco 2013-02-25 12:24:14 +0100 283)     public void onPause() {
3d272eb7 src/com/owncloud/android/ui/fragment/PreviewImageFragment.java (David A. Velasco 2013-02-25 12:24:14 +0100 284)         super.onPause();
3d272eb7 src/com/owncloud/android/ui/fragment/PreviewImageFragment.java (David A. Velasco 2013-02-25 12:24:14 +0100 285)     }
3d272eb7 src/com/owncloud/android/ui/fragment/PreviewImageFragment.java (David A. Velasco 2013-02-25 12:24:14 +0100 286) 
3d272eb7 src/com/owncloud/android/ui/fragment/PreviewImageFragment.java (David A. Velasco 2013-02-25 12:24:14 +0100 287) 
3d272eb7 src/com/owncloud/android/ui/fragment/PreviewImageFragment.java (David A. Velasco 2013-02-25 12:24:14 +0100 288)     @Override
3d272eb7 src/com/owncloud/android/ui/fragment/PreviewImageFragment.java (David A. Velasco 2013-02-25 12:24:14 +0100 289)     public void onDestroy() {
3d272eb7 src/com/owncloud/android/ui/fragment/PreviewImageFragment.java (David A. Velasco 2013-02-25 12:24:14 +0100 290)         super.onDestroy();
3d272eb7 src/com/owncloud/android/ui/fragment/PreviewImageFragment.java (David A. Velasco 2013-02-25 12:24:14 +0100 291)         if (mBitmap != null) {
3d272eb7 src/com/owncloud/android/ui/fragment/PreviewImageFragment.java (David A. Velasco 2013-02-25 12:24:14 +0100 292)             mBitmap.recycle();
3d272eb7 src/com/owncloud/android/ui/fragment/PreviewImageFragment.java (David A. Velasco 2013-02-25 12:24:14 +0100 293)         }
3d272eb7 src/com/owncloud/android/ui/fragment/PreviewImageFragment.java (David A. Velasco 2013-02-25 12:24:14 +0100 294)     }
3d272eb7 src/com/owncloud/android/ui/fragment/PreviewImageFragment.java (David A. Velasco 2013-02-25 12:24:14 +0100 295) 
3d272eb7 src/com/owncloud/android/ui/fragment/PreviewImageFragment.java (David A. Velasco 2013-02-25 12:24:14 +0100 296)     
3d272eb7 src/com/owncloud/android/ui/fragment/PreviewImageFragment.java (David A. Velasco 2013-02-25 12:24:14 +0100 297)     /**
3d272eb7 src/com/owncloud/android/ui/fragment/PreviewImageFragment.java (David A. Velasco 2013-02-25 12:24:14 +0100 298)      * Opens the previewed image with an external application.
3d272eb7 src/com/owncloud/android/ui/fragment/PreviewImageFragment.java (David A. Velasco 2013-02-25 12:24:14 +0100 299)      * 
3d272eb7 src/com/owncloud/android/ui/fragment/PreviewImageFragment.java (David A. Velasco 2013-02-25 12:24:14 +0100 300)      * TODO - improve this; instead of prioritize the actions available for the MIME type in the server, 
3d272eb7 src/com/owncloud/android/ui/fragment/PreviewImageFragment.java (David A. Velasco 2013-02-25 12:24:14 +0100 301)      * we should get a list of available apps for MIME tpye in the server and join it with the list of 
3d272eb7 src/com/owncloud/android/ui/fragment/PreviewImageFragment.java (David A. Velasco 2013-02-25 12:24:14 +0100 302)      * available apps for the MIME type known from the file extension, to let the user choose
3d272eb7 src/com/owncloud/android/ui/fragment/PreviewImageFragment.java (David A. Velasco 2013-02-25 12:24:14 +0100 303)      */
3d272eb7 src/com/owncloud/android/ui/fragment/PreviewImageFragment.java (David A. Velasco 2013-02-25 12:24:14 +0100 304)     private void openFile() {
fd396289 src/com/owncloud/android/ui/preview/PreviewImageFragment.java  (David A. Velasco 2013-05-30 17:53:21 +0200 305)         OCFile file = getFile();
fd396289 src/com/owncloud/android/ui/preview/PreviewImageFragment.java  (David A. Velasco 2013-05-30 17:53:21 +0200 306)         String storagePath = file.getStoragePath();
3d272eb7 src/com/owncloud/android/ui/fragment/PreviewImageFragment.java (David A. Velasco 2013-02-25 12:24:14 +0100 307)         String encodedStoragePath = WebdavUtils.encodePath(storagePath);
3d272eb7 src/com/owncloud/android/ui/fragment/PreviewImageFragment.java (David A. Velasco 2013-02-25 12:24:14 +0100 308)         try {
3d272eb7 src/com/owncloud/android/ui/fragment/PreviewImageFragment.java (David A. Velasco 2013-02-25 12:24:14 +0100 309)             Intent i = new Intent(Intent.ACTION_VIEW);
fd396289 src/com/owncloud/android/ui/preview/PreviewImageFragment.java  (David A. Velasco 2013-05-30 17:53:21 +0200 310)             i.setDataAndType(Uri.parse("file://"+ encodedStoragePath), file.getMimetype());
3d272eb7 src/com/owncloud/android/ui/fragment/PreviewImageFragment.java (David A. Velasco 2013-02-25 12:24:14 +0100 311)             i.setFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION | Intent.FLAG_GRANT_WRITE_URI_PERMISSION);
3d272eb7 src/com/owncloud/android/ui/fragment/PreviewImageFragment.java (David A. Velasco 2013-02-25 12:24:14 +0100 312)             startActivity(i);
3d272eb7 src/com/owncloud/android/ui/fragment/PreviewImageFragment.java (David A. Velasco 2013-02-25 12:24:14 +0100 313)             
3d272eb7 src/com/owncloud/android/ui/fragment/PreviewImageFragment.java (David A. Velasco 2013-02-25 12:24:14 +0100 314)         } catch (Throwable t) {
fd396289 src/com/owncloud/android/ui/preview/PreviewImageFragment.java  (David A. Velasco 2013-05-30 17:53:21 +0200 315)             Log_OC.e(TAG, "Fail when trying to open with the mimeType provided from the ownCloud server: " + file.getMimetype());
3d272eb7 src/com/owncloud/android/ui/fragment/PreviewImageFragment.java (David A. Velasco 2013-02-25 12:24:14 +0100 316)             boolean toastIt = true; 
3d272eb7 src/com/owncloud/android/ui/fragment/PreviewImageFragment.java (David A. Velasco 2013-02-25 12:24:14 +0100 317)             String mimeType = "";
3d272eb7 src/com/owncloud/android/ui/fragment/PreviewImageFragment.java (David A. Velasco 2013-02-25 12:24:14 +0100 318)             try {
3d272eb7 src/com/owncloud/android/ui/fragment/PreviewImageFragment.java (David A. Velasco 2013-02-25 12:24:14 +0100 319)                 Intent i = new Intent(Intent.ACTION_VIEW);
3d272eb7 src/com/owncloud/android/ui/fragment/PreviewImageFragment.java (David A. Velasco 2013-02-25 12:24:14 +0100 320)                 mimeType = MimeTypeMap.getSingleton().getMimeTypeFromExtension(storagePath.substring(storagePath.lastIndexOf('.') + 1));
fd396289 src/com/owncloud/android/ui/preview/PreviewImageFragment.java  (David A. Velasco 2013-05-30 17:53:21 +0200 321)                 if (mimeType == null || !mimeType.equals(file.getMimetype())) {
3d272eb7 src/com/owncloud/android/ui/fragment/PreviewImageFragment.java (David A. Velasco 2013-02-25 12:24:14 +0100 322)                     if (mimeType != null) {
3d272eb7 src/com/owncloud/android/ui/fragment/PreviewImageFragment.java (David A. Velasco 2013-02-25 12:24:14 +0100 323)                         i.setDataAndType(Uri.parse("file://"+ encodedStoragePath), mimeType);
3d272eb7 src/com/owncloud/android/ui/fragment/PreviewImageFragment.java (David A. Velasco 2013-02-25 12:24:14 +0100 324)                     } else {
3d272eb7 src/com/owncloud/android/ui/fragment/PreviewImageFragment.java (David A. Velasco 2013-02-25 12:24:14 +0100 325)                         // desperate try
3d272eb7 src/com/owncloud/android/ui/fragment/PreviewImageFragment.java (David A. Velasco 2013-02-25 12:24:14 +0100 326)                         i.setDataAndType(Uri.parse("file://"+ encodedStoragePath), "*-/*");
3d272eb7 src/com/owncloud/android/ui/fragment/PreviewImageFragment.java (David A. Velasco 2013-02-25 12:24:14 +0100 327)                     }
3d272eb7 src/com/owncloud/android/ui/fragment/PreviewImageFragment.java (David A. Velasco 2013-02-25 12:24:14 +0100 328)                     i.setFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION | Intent.FLAG_GRANT_WRITE_URI_PERMISSION);
3d272eb7 src/com/owncloud/android/ui/fragment/PreviewImageFragment.java (David A. Velasco 2013-02-25 12:24:14 +0100 329)                     startActivity(i);
3d272eb7 src/com/owncloud/android/ui/fragment/PreviewImageFragment.java (David A. Velasco 2013-02-25 12:24:14 +0100 330)                     toastIt = false;
3d272eb7 src/com/owncloud/android/ui/fragment/PreviewImageFragment.java (David A. Velasco 2013-02-25 12:24:14 +0100 331)                 }
3d272eb7 src/com/owncloud/android/ui/fragment/PreviewImageFragment.java (David A. Velasco 2013-02-25 12:24:14 +0100 332)                 
3d272eb7 src/com/owncloud/android/ui/fragment/PreviewImageFragment.java (David A. Velasco 2013-02-25 12:24:14 +0100 333)             } catch (IndexOutOfBoundsException e) {
2946d8dd src/com/owncloud/android/ui/preview/PreviewImageFragment.java  (David A. Velasco 2013-04-25 19:39:22 +0200 334)                 Log_OC.e(TAG, "Trying to find out MIME type of a file without extension: " + storagePath);
3d272eb7 src/com/owncloud/android/ui/fragment/PreviewImageFragment.java (David A. Velasco 2013-02-25 12:24:14 +0100 335)                 
3d272eb7 src/com/owncloud/android/ui/fragment/PreviewImageFragment.java (David A. Velasco 2013-02-25 12:24:14 +0100 336)             } catch (ActivityNotFoundException e) {
2946d8dd src/com/owncloud/android/ui/preview/PreviewImageFragment.java  (David A. Velasco 2013-04-25 19:39:22 +0200 337)                 Log_OC.e(TAG, "No activity found to handle: " + storagePath + " with MIME type " + mimeType + " obtained from extension");
3d272eb7 src/com/owncloud/android/ui/fragment/PreviewImageFragment.java (David A. Velasco 2013-02-25 12:24:14 +0100 338)                 
3d272eb7 src/com/owncloud/android/ui/fragment/PreviewImageFragment.java (David A. Velasco 2013-02-25 12:24:14 +0100 339)             } catch (Throwable th) {
2946d8dd src/com/owncloud/android/ui/preview/PreviewImageFragment.java  (David A. Velasco 2013-04-25 19:39:22 +0200 340)                 Log_OC.e(TAG, "Unexpected problem when opening: " + storagePath, th);
3d272eb7 src/com/owncloud/android/ui/fragment/PreviewImageFragment.java (David A. Velasco 2013-02-25 12:24:14 +0100 341)                 
3d272eb7 src/com/owncloud/android/ui/fragment/PreviewImageFragment.java (David A. Velasco 2013-02-25 12:24:14 +0100 342)             } finally {
3d272eb7 src/com/owncloud/android/ui/fragment/PreviewImageFragment.java (David A. Velasco 2013-02-25 12:24:14 +0100 343)                 if (toastIt) {
fd396289 src/com/owncloud/android/ui/preview/PreviewImageFragment.java  (David A. Velasco 2013-05-30 17:53:21 +0200 344)                     Toast.makeText(getActivity(), "There is no application to handle file " + file.getFileName(), Toast.LENGTH_SHORT).show();
3d272eb7 src/com/owncloud/android/ui/fragment/PreviewImageFragment.java (David A. Velasco 2013-02-25 12:24:14 +0100 345)                 }
3d272eb7 src/com/owncloud/android/ui/fragment/PreviewImageFragment.java (David A. Velasco 2013-02-25 12:24:14 +0100 346)             }
3d272eb7 src/com/owncloud/android/ui/fragment/PreviewImageFragment.java (David A. Velasco 2013-02-25 12:24:14 +0100 347)             
3d272eb7 src/com/owncloud/android/ui/fragment/PreviewImageFragment.java (David A. Velasco 2013-02-25 12:24:14 +0100 348)         }
3d272eb7 src/com/owncloud/android/ui/fragment/PreviewImageFragment.java (David A. Velasco 2013-02-25 12:24:14 +0100 349)         finish();
3d272eb7 src/com/owncloud/android/ui/fragment/PreviewImageFragment.java (David A. Velasco 2013-02-25 12:24:14 +0100 350)     }
3d272eb7 src/com/owncloud/android/ui/fragment/PreviewImageFragment.java (David A. Velasco 2013-02-25 12:24:14 +0100 351)     
3d272eb7 src/com/owncloud/android/ui/fragment/PreviewImageFragment.java (David A. Velasco 2013-02-25 12:24:14 +0100 352)     
3d272eb7 src/com/owncloud/android/ui/fragment/PreviewImageFragment.java (David A. Velasco 2013-02-25 12:24:14 +0100 353)     /**
3d272eb7 src/com/owncloud/android/ui/fragment/PreviewImageFragment.java (David A. Velasco 2013-02-25 12:24:14 +0100 354)      * Starts a the removal of the previewed file.
3d272eb7 src/com/owncloud/android/ui/fragment/PreviewImageFragment.java (David A. Velasco 2013-02-25 12:24:14 +0100 355)      * 
3d272eb7 src/com/owncloud/android/ui/fragment/PreviewImageFragment.java (David A. Velasco 2013-02-25 12:24:14 +0100 356)      * Shows a confirmation dialog. The action continues in {@link #onConfirmation(String)} , {@link #onNeutral(String)} or {@link #onCancel(String)},
3d272eb7 src/com/owncloud/android/ui/fragment/PreviewImageFragment.java (David A. Velasco 2013-02-25 12:24:14 +0100 357)      * depending upon the user selection in the dialog. 
3d272eb7 src/com/owncloud/android/ui/fragment/PreviewImageFragment.java (David A. Velasco 2013-02-25 12:24:14 +0100 358)      */
3d272eb7 src/com/owncloud/android/ui/fragment/PreviewImageFragment.java (David A. Velasco 2013-02-25 12:24:14 +0100 359)     private void removeFile() {
3d272eb7 src/com/owncloud/android/ui/fragment/PreviewImageFragment.java (David A. Velasco 2013-02-25 12:24:14 +0100 360)         ConfirmationDialogFragment confDialog = ConfirmationDialogFragment.newInstance(
3d272eb7 src/com/owncloud/android/ui/fragment/PreviewImageFragment.java (David A. Velasco 2013-02-25 12:24:14 +0100 361)                 R.string.confirmation_remove_alert,
fd396289 src/com/owncloud/android/ui/preview/PreviewImageFragment.java  (David A. Velasco 2013-05-30 17:53:21 +0200 362)                 new String[]{getFile().getFileName()},
3d272eb7 src/com/owncloud/android/ui/fragment/PreviewImageFragment.java (David A. Velasco 2013-02-25 12:24:14 +0100 363)                 R.string.confirmation_remove_remote_and_local,
3d272eb7 src/com/owncloud/android/ui/fragment/PreviewImageFragment.java (David A. Velasco 2013-02-25 12:24:14 +0100 364)                 R.string.confirmation_remove_local,
3d272eb7 src/com/owncloud/android/ui/fragment/PreviewImageFragment.java (David A. Velasco 2013-02-25 12:24:14 +0100 365)                 R.string.common_cancel);
3d272eb7 src/com/owncloud/android/ui/fragment/PreviewImageFragment.java (David A. Velasco 2013-02-25 12:24:14 +0100 366)         confDialog.setOnConfirmationListener(this);
3d272eb7 src/com/owncloud/android/ui/fragment/PreviewImageFragment.java (David A. Velasco 2013-02-25 12:24:14 +0100 367)         confDialog.show(getFragmentManager(), ConfirmationDialogFragment.FTAG_CONFIRMATION);
3d272eb7 src/com/owncloud/android/ui/fragment/PreviewImageFragment.java (David A. Velasco 2013-02-25 12:24:14 +0100 368)     }
3d272eb7 src/com/owncloud/android/ui/fragment/PreviewImageFragment.java (David A. Velasco 2013-02-25 12:24:14 +0100 369) 
3d272eb7 src/com/owncloud/android/ui/fragment/PreviewImageFragment.java (David A. Velasco 2013-02-25 12:24:14 +0100 370)     
3d272eb7 src/com/owncloud/android/ui/fragment/PreviewImageFragment.java (David A. Velasco 2013-02-25 12:24:14 +0100 371)     /**
3d272eb7 src/com/owncloud/android/ui/fragment/PreviewImageFragment.java (David A. Velasco 2013-02-25 12:24:14 +0100 372)      * Performs the removal of the previewed file, both locally and in the server.
3d272eb7 src/com/owncloud/android/ui/fragment/PreviewImageFragment.java (David A. Velasco 2013-02-25 12:24:14 +0100 373)      */
3d272eb7 src/com/owncloud/android/ui/fragment/PreviewImageFragment.java (David A. Velasco 2013-02-25 12:24:14 +0100 374)     @Override
3d272eb7 src/com/owncloud/android/ui/fragment/PreviewImageFragment.java (David A. Velasco 2013-02-25 12:24:14 +0100 375)     public void onConfirmation(String callerTag) {
fd396289 src/com/owncloud/android/ui/preview/PreviewImageFragment.java  (David A. Velasco 2013-05-30 17:53:21 +0200 376)         if (mStorageManager.getFileById(getFile().getFileId()) != null) {   // check that the file is still there;
fd396289 src/com/owncloud/android/ui/preview/PreviewImageFragment.java  (David A. Velasco 2013-05-30 17:53:21 +0200 377)             mLastRemoteOperation = new RemoveFileOperation( getFile(),      // TODO we need to review the interface with RemoteOperations, and use OCFile IDs instead of OCFile objects as parameters
3d272eb7 src/com/owncloud/android/ui/fragment/PreviewImageFragment.java (David A. Velasco 2013-02-25 12:24:14 +0100 378)                                                             true, 
3d272eb7 src/com/owncloud/android/ui/fragment/PreviewImageFragment.java (David A. Velasco 2013-02-25 12:24:14 +0100 379)                                                             mStorageManager);
2946d8dd src/com/owncloud/android/ui/preview/PreviewImageFragment.java  (David A. Velasco 2013-04-25 19:39:22 +0200 380)             mLastRemoteOperation.execute(mAccount, getSherlockActivity(), this, mHandler, getSherlockActivity());
3d272eb7 src/com/owncloud/android/ui/fragment/PreviewImageFragment.java (David A. Velasco 2013-02-25 12:24:14 +0100 381)             
3d272eb7 src/com/owncloud/android/ui/fragment/PreviewImageFragment.java (David A. Velasco 2013-02-25 12:24:14 +0100 382)             getActivity().showDialog(PreviewImageActivity.DIALOG_SHORT_WAIT);
3d272eb7 src/com/owncloud/android/ui/fragment/PreviewImageFragment.java (David A. Velasco 2013-02-25 12:24:14 +0100 383)         }
3d272eb7 src/com/owncloud/android/ui/fragment/PreviewImageFragment.java (David A. Velasco 2013-02-25 12:24:14 +0100 384)     }
3d272eb7 src/com/owncloud/android/ui/fragment/PreviewImageFragment.java (David A. Velasco 2013-02-25 12:24:14 +0100 385)     
3d272eb7 src/com/owncloud/android/ui/fragment/PreviewImageFragment.java (David A. Velasco 2013-02-25 12:24:14 +0100 386)     
3d272eb7 src/com/owncloud/android/ui/fragment/PreviewImageFragment.java (David A. Velasco 2013-02-25 12:24:14 +0100 387)     /**
3d272eb7 src/com/owncloud/android/ui/fragment/PreviewImageFragment.java (David A. Velasco 2013-02-25 12:24:14 +0100 388)      * Removes the file from local storage
3d272eb7 src/com/owncloud/android/ui/fragment/PreviewImageFragment.java (David A. Velasco 2013-02-25 12:24:14 +0100 389)      */
3d272eb7 src/com/owncloud/android/ui/fragment/PreviewImageFragment.java (David A. Velasco 2013-02-25 12:24:14 +0100 390)     @Override
3d272eb7 src/com/owncloud/android/ui/fragment/PreviewImageFragment.java (David A. Velasco 2013-02-25 12:24:14 +0100 391)     public void onNeutral(String callerTag) {
3d272eb7 src/com/owncloud/android/ui/fragment/PreviewImageFragment.java (David A. Velasco 2013-02-25 12:24:14 +0100 392)         // TODO this code should be made in a secondary thread,
fd396289 src/com/owncloud/android/ui/preview/PreviewImageFragment.java  (David A. Velasco 2013-05-30 17:53:21 +0200 393)         OCFile file = getFile();
fd396289 src/com/owncloud/android/ui/preview/PreviewImageFragment.java  (David A. Velasco 2013-05-30 17:53:21 +0200 394)         if (file.isDown()) {   // checks it is still there
fd396289 src/com/owncloud/android/ui/preview/PreviewImageFragment.java  (David A. Velasco 2013-05-30 17:53:21 +0200 395)             File f = new File(file.getStoragePath());
3d272eb7 src/com/owncloud/android/ui/fragment/PreviewImageFragment.java (David A. Velasco 2013-02-25 12:24:14 +0100 396)             f.delete();
fd396289 src/com/owncloud/android/ui/preview/PreviewImageFragment.java  (David A. Velasco 2013-05-30 17:53:21 +0200 397)             file.setStoragePath(null);
fd396289 src/com/owncloud/android/ui/preview/PreviewImageFragment.java  (David A. Velasco 2013-05-30 17:53:21 +0200 398)             mStorageManager.saveFile(file);
3d272eb7 src/com/owncloud/android/ui/fragment/PreviewImageFragment.java (David A. Velasco 2013-02-25 12:24:14 +0100 399)             finish();
3d272eb7 src/com/owncloud/android/ui/fragment/PreviewImageFragment.java (David A. Velasco 2013-02-25 12:24:14 +0100 400)         }
3d272eb7 src/com/owncloud/android/ui/fragment/PreviewImageFragment.java (David A. Velasco 2013-02-25 12:24:14 +0100 401)     }
3d272eb7 src/com/owncloud/android/ui/fragment/PreviewImageFragment.java (David A. Velasco 2013-02-25 12:24:14 +0100 402)     
3d272eb7 src/com/owncloud/android/ui/fragment/PreviewImageFragment.java (David A. Velasco 2013-02-25 12:24:14 +0100 403)     /**
3d272eb7 src/com/owncloud/android/ui/fragment/PreviewImageFragment.java (David A. Velasco 2013-02-25 12:24:14 +0100 404)      * User cancelled the removal action.
3d272eb7 src/com/owncloud/android/ui/fragment/PreviewImageFragment.java (David A. Velasco 2013-02-25 12:24:14 +0100 405)      */
3d272eb7 src/com/owncloud/android/ui/fragment/PreviewImageFragment.java (David A. Velasco 2013-02-25 12:24:14 +0100 406)     @Override
3d272eb7 src/com/owncloud/android/ui/fragment/PreviewImageFragment.java (David A. Velasco 2013-02-25 12:24:14 +0100 407)     public void onCancel(String callerTag) {
3d272eb7 src/com/owncloud/android/ui/fragment/PreviewImageFragment.java (David A. Velasco 2013-02-25 12:24:14 +0100 408)         // nothing to do here
3d272eb7 src/com/owncloud/android/ui/fragment/PreviewImageFragment.java (David A. Velasco 2013-02-25 12:24:14 +0100 409)     }
3d272eb7 src/com/owncloud/android/ui/fragment/PreviewImageFragment.java (David A. Velasco 2013-02-25 12:24:14 +0100 410)     
3d272eb7 src/com/owncloud/android/ui/fragment/PreviewImageFragment.java (David A. Velasco 2013-02-25 12:24:14 +0100 411) 
3d272eb7 src/com/owncloud/android/ui/fragment/PreviewImageFragment.java (David A. Velasco 2013-02-25 12:24:14 +0100 412)     private class BitmapLoader extends AsyncTask<String, Void, Bitmap> {
3d272eb7 src/com/owncloud/android/ui/fragment/PreviewImageFragment.java (David A. Velasco 2013-02-25 12:24:14 +0100 413) 
3d272eb7 src/com/owncloud/android/ui/fragment/PreviewImageFragment.java (David A. Velasco 2013-02-25 12:24:14 +0100 414)         /**
3d272eb7 src/com/owncloud/android/ui/fragment/PreviewImageFragment.java (David A. Velasco 2013-02-25 12:24:14 +0100 415)          * Weak reference to the target {@link ImageView} where the bitmap will be loaded into.
3d272eb7 src/com/owncloud/android/ui/fragment/PreviewImageFragment.java (David A. Velasco 2013-02-25 12:24:14 +0100 416)          * 
3d272eb7 src/com/owncloud/android/ui/fragment/PreviewImageFragment.java (David A. Velasco 2013-02-25 12:24:14 +0100 417)          * Using a weak reference will avoid memory leaks if the target ImageView is retired from memory before the load finishes.
3d272eb7 src/com/owncloud/android/ui/fragment/PreviewImageFragment.java (David A. Velasco 2013-02-25 12:24:14 +0100 418)          */
3d272eb7 src/com/owncloud/android/ui/fragment/PreviewImageFragment.java (David A. Velasco 2013-02-25 12:24:14 +0100 419)         private final WeakReference<ImageView> mImageViewRef;
6cfa24f5 src/com/owncloud/android/ui/preview/PreviewImageFragment.java  (David A. Velasco 2013-03-01 09:54:49 +0100 420) 
6cfa24f5 src/com/owncloud/android/ui/preview/PreviewImageFragment.java  (David A. Velasco 2013-03-01 09:54:49 +0100 421)         /**
6cfa24f5 src/com/owncloud/android/ui/preview/PreviewImageFragment.java  (David A. Velasco 2013-03-01 09:54:49 +0100 422)          * Weak reference to the target {@link TextView} where error messages will be written.
6cfa24f5 src/com/owncloud/android/ui/preview/PreviewImageFragment.java  (David A. Velasco 2013-03-01 09:54:49 +0100 423)          * 
6cfa24f5 src/com/owncloud/android/ui/preview/PreviewImageFragment.java  (David A. Velasco 2013-03-01 09:54:49 +0100 424)          * Using a weak reference will avoid memory leaks if the target ImageView is retired from memory before the load finishes.
6cfa24f5 src/com/owncloud/android/ui/preview/PreviewImageFragment.java  (David A. Velasco 2013-03-01 09:54:49 +0100 425)          */
6cfa24f5 src/com/owncloud/android/ui/preview/PreviewImageFragment.java  (David A. Velasco 2013-03-01 09:54:49 +0100 426)         private final WeakReference<TextView> mMessageViewRef;
6cfa24f5 src/com/owncloud/android/ui/preview/PreviewImageFragment.java  (David A. Velasco 2013-03-01 09:54:49 +0100 427) 
6cfa24f5 src/com/owncloud/android/ui/preview/PreviewImageFragment.java  (David A. Velasco 2013-03-01 09:54:49 +0100 428)         
427a3e63 src/com/owncloud/android/ui/preview/PreviewImageFragment.java  (David A. Velasco 2013-03-01 10:49:35 +0100 429)         /**
427a3e63 src/com/owncloud/android/ui/preview/PreviewImageFragment.java  (David A. Velasco 2013-03-01 10:49:35 +0100 430)          * Weak reference to the target {@link Progressbar} shown while the load is in progress.
427a3e63 src/com/owncloud/android/ui/preview/PreviewImageFragment.java  (David A. Velasco 2013-03-01 10:49:35 +0100 431)          * 
427a3e63 src/com/owncloud/android/ui/preview/PreviewImageFragment.java  (David A. Velasco 2013-03-01 10:49:35 +0100 432)          * Using a weak reference will avoid memory leaks if the target ImageView is retired from memory before the load finishes.
427a3e63 src/com/owncloud/android/ui/preview/PreviewImageFragment.java  (David A. Velasco 2013-03-01 10:49:35 +0100 433)          */
427a3e63 src/com/owncloud/android/ui/preview/PreviewImageFragment.java  (David A. Velasco 2013-03-01 10:49:35 +0100 434)         private final WeakReference<ProgressBar> mProgressWheelRef;
427a3e63 src/com/owncloud/android/ui/preview/PreviewImageFragment.java  (David A. Velasco 2013-03-01 10:49:35 +0100 435) 
427a3e63 src/com/owncloud/android/ui/preview/PreviewImageFragment.java  (David A. Velasco 2013-03-01 10:49:35 +0100 436)         
427a3e63 src/com/owncloud/android/ui/preview/PreviewImageFragment.java  (David A. Velasco 2013-03-01 10:49:35 +0100 437)         /**
427a3e63 src/com/owncloud/android/ui/preview/PreviewImageFragment.java  (David A. Velasco 2013-03-01 10:49:35 +0100 438)          * Error message to show when a load fails 
427a3e63 src/com/owncloud/android/ui/preview/PreviewImageFragment.java  (David A. Velasco 2013-03-01 10:49:35 +0100 439)          */
427a3e63 src/com/owncloud/android/ui/preview/PreviewImageFragment.java  (David A. Velasco 2013-03-01 10:49:35 +0100 440)         private int mErrorMessageId;
3d272eb7 src/com/owncloud/android/ui/fragment/PreviewImageFragment.java (David A. Velasco 2013-02-25 12:24:14 +0100 441)         
3d272eb7 src/com/owncloud/android/ui/fragment/PreviewImageFragment.java (David A. Velasco 2013-02-25 12:24:14 +0100 442)         
3d272eb7 src/com/owncloud/android/ui/fragment/PreviewImageFragment.java (David A. Velasco 2013-02-25 12:24:14 +0100 443)         /**
3d272eb7 src/com/owncloud/android/ui/fragment/PreviewImageFragment.java (David A. Velasco 2013-02-25 12:24:14 +0100 444)          * Constructor.
3d272eb7 src/com/owncloud/android/ui/fragment/PreviewImageFragment.java (David A. Velasco 2013-02-25 12:24:14 +0100 445)          * 
3d272eb7 src/com/owncloud/android/ui/fragment/PreviewImageFragment.java (David A. Velasco 2013-02-25 12:24:14 +0100 446)          * @param imageView     Target {@link ImageView} where the bitmap will be loaded into.
3d272eb7 src/com/owncloud/android/ui/fragment/PreviewImageFragment.java (David A. Velasco 2013-02-25 12:24:14 +0100 447)          */
427a3e63 src/com/owncloud/android/ui/preview/PreviewImageFragment.java  (David A. Velasco 2013-03-01 10:49:35 +0100 448)         public BitmapLoader(ImageView imageView, TextView messageView, ProgressBar progressWheel) {
3d272eb7 src/com/owncloud/android/ui/fragment/PreviewImageFragment.java (David A. Velasco 2013-02-25 12:24:14 +0100 449)             mImageViewRef = new WeakReference<ImageView>(imageView);
6cfa24f5 src/com/owncloud/android/ui/preview/PreviewImageFragment.java  (David A. Velasco 2013-03-01 09:54:49 +0100 450)             mMessageViewRef = new WeakReference<TextView>(messageView);
427a3e63 src/com/owncloud/android/ui/preview/PreviewImageFragment.java  (David A. Velasco 2013-03-01 10:49:35 +0100 451)             mProgressWheelRef = new WeakReference<ProgressBar>(progressWheel);
3d272eb7 src/com/owncloud/android/ui/fragment/PreviewImageFragment.java (David A. Velasco 2013-02-25 12:24:14 +0100 452)         }
3d272eb7 src/com/owncloud/android/ui/fragment/PreviewImageFragment.java (David A. Velasco 2013-02-25 12:24:14 +0100 453)         
3d272eb7 src/com/owncloud/android/ui/fragment/PreviewImageFragment.java (David A. Velasco 2013-02-25 12:24:14 +0100 454)         
3d272eb7 src/com/owncloud/android/ui/fragment/PreviewImageFragment.java (David A. Velasco 2013-02-25 12:24:14 +0100 455)         @SuppressWarnings("deprecation")
3d272eb7 src/com/owncloud/android/ui/fragment/PreviewImageFragment.java (David A. Velasco 2013-02-25 12:24:14 +0100 456)         @SuppressLint({ "NewApi", "NewApi", "NewApi" }) // to avoid Lint errors since Android SDK r20
3d272eb7 src/com/owncloud/android/ui/fragment/PreviewImageFragment.java (David A. Velasco 2013-02-25 12:24:14 +0100 457) 		@Override
3d272eb7 src/com/owncloud/android/ui/fragment/PreviewImageFragment.java (David A. Velasco 2013-02-25 12:24:14 +0100 458)         protected Bitmap doInBackground(String... params) {
3d272eb7 src/com/owncloud/android/ui/fragment/PreviewImageFragment.java (David A. Velasco 2013-02-25 12:24:14 +0100 459)             Bitmap result = null;
3d272eb7 src/com/owncloud/android/ui/fragment/PreviewImageFragment.java (David A. Velasco 2013-02-25 12:24:14 +0100 460)             if (params.length != 1) return result;
3d272eb7 src/com/owncloud/android/ui/fragment/PreviewImageFragment.java (David A. Velasco 2013-02-25 12:24:14 +0100 461)             String storagePath = params[0];
3d272eb7 src/com/owncloud/android/ui/fragment/PreviewImageFragment.java (David A. Velasco 2013-02-25 12:24:14 +0100 462)             try {
3d272eb7 src/com/owncloud/android/ui/fragment/PreviewImageFragment.java (David A. Velasco 2013-02-25 12:24:14 +0100 463)                 // set desired options that will affect the size of the bitmap
3d272eb7 src/com/owncloud/android/ui/fragment/PreviewImageFragment.java (David A. Velasco 2013-02-25 12:24:14 +0100 464)                 BitmapFactory.Options options = new Options();
3d272eb7 src/com/owncloud/android/ui/fragment/PreviewImageFragment.java (David A. Velasco 2013-02-25 12:24:14 +0100 465)                 options.inScaled = true;
3d272eb7 src/com/owncloud/android/ui/fragment/PreviewImageFragment.java (David A. Velasco 2013-02-25 12:24:14 +0100 466)                 options.inPurgeable = true;
3d272eb7 src/com/owncloud/android/ui/fragment/PreviewImageFragment.java (David A. Velasco 2013-02-25 12:24:14 +0100 467)                 if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.GINGERBREAD_MR1) {
3d272eb7 src/com/owncloud/android/ui/fragment/PreviewImageFragment.java (David A. Velasco 2013-02-25 12:24:14 +0100 468)                     options.inPreferQualityOverSpeed = false;
3d272eb7 src/com/owncloud/android/ui/fragment/PreviewImageFragment.java (David A. Velasco 2013-02-25 12:24:14 +0100 469)                 }
3d272eb7 src/com/owncloud/android/ui/fragment/PreviewImageFragment.java (David A. Velasco 2013-02-25 12:24:14 +0100 470)                 if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.HONEYCOMB) {
3d272eb7 src/com/owncloud/android/ui/fragment/PreviewImageFragment.java (David A. Velasco 2013-02-25 12:24:14 +0100 471)                     options.inMutable = false;
3d272eb7 src/com/owncloud/android/ui/fragment/PreviewImageFragment.java (David A. Velasco 2013-02-25 12:24:14 +0100 472)                 }
3d272eb7 src/com/owncloud/android/ui/fragment/PreviewImageFragment.java (David A. Velasco 2013-02-25 12:24:14 +0100 473)                 // make a false load of the bitmap - just to be able to read outWidth, outHeight and outMimeType
3d272eb7 src/com/owncloud/android/ui/fragment/PreviewImageFragment.java (David A. Velasco 2013-02-25 12:24:14 +0100 474)                 options.inJustDecodeBounds = true;
3d272eb7 src/com/owncloud/android/ui/fragment/PreviewImageFragment.java (David A. Velasco 2013-02-25 12:24:14 +0100 475)                 BitmapFactory.decodeFile(storagePath, options);   
3d272eb7 src/com/owncloud/android/ui/fragment/PreviewImageFragment.java (David A. Velasco 2013-02-25 12:24:14 +0100 476)                 
3d272eb7 src/com/owncloud/android/ui/fragment/PreviewImageFragment.java (David A. Velasco 2013-02-25 12:24:14 +0100 477)                 int width = options.outWidth;
3d272eb7 src/com/owncloud/android/ui/fragment/PreviewImageFragment.java (David A. Velasco 2013-02-25 12:24:14 +0100 478)                 int height = options.outHeight;
3d272eb7 src/com/owncloud/android/ui/fragment/PreviewImageFragment.java (David A. Velasco 2013-02-25 12:24:14 +0100 479)                 int scale = 1;
6cfa24f5 src/com/owncloud/android/ui/preview/PreviewImageFragment.java  (David A. Velasco 2013-03-01 09:54:49 +0100 480)                 
3d272eb7 src/com/owncloud/android/ui/fragment/PreviewImageFragment.java (David A. Velasco 2013-02-25 12:24:14 +0100 481)                 Display display = getActivity().getWindowManager().getDefaultDisplay();
3d272eb7 src/com/owncloud/android/ui/fragment/PreviewImageFragment.java (David A. Velasco 2013-02-25 12:24:14 +0100 482)                 Point size = new Point();
6cfa24f5 src/com/owncloud/android/ui/preview/PreviewImageFragment.java  (David A. Velasco 2013-03-01 09:54:49 +0100 483)                 int screenWidth;
6cfa24f5 src/com/owncloud/android/ui/preview/PreviewImageFragment.java  (David A. Velasco 2013-03-01 09:54:49 +0100 484)                 int screenHeight;
3d272eb7 src/com/owncloud/android/ui/fragment/PreviewImageFragment.java (David A. Velasco 2013-02-25 12:24:14 +0100 485)                 if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.HONEYCOMB_MR2) {
3d272eb7 src/com/owncloud/android/ui/fragment/PreviewImageFragment.java (David A. Velasco 2013-02-25 12:24:14 +0100 486)                     display.getSize(size);
6cfa24f5 src/com/owncloud/android/ui/preview/PreviewImageFragment.java  (David A. Velasco 2013-03-01 09:54:49 +0100 487)                     screenWidth = size.x;
6cfa24f5 src/com/owncloud/android/ui/preview/PreviewImageFragment.java  (David A. Velasco 2013-03-01 09:54:49 +0100 488)                     screenHeight = size.y;
3d272eb7 src/com/owncloud/android/ui/fragment/PreviewImageFragment.java (David A. Velasco 2013-02-25 12:24:14 +0100 489)                 } else {
6cfa24f5 src/com/owncloud/android/ui/preview/PreviewImageFragment.java  (David A. Velasco 2013-03-01 09:54:49 +0100 490)                     screenWidth = display.getWidth();
6cfa24f5 src/com/owncloud/android/ui/preview/PreviewImageFragment.java  (David A. Velasco 2013-03-01 09:54:49 +0100 491)                     screenHeight = display.getHeight();
3d272eb7 src/com/owncloud/android/ui/fragment/PreviewImageFragment.java (David A. Velasco 2013-02-25 12:24:14 +0100 492)                 }
3d272eb7 src/com/owncloud/android/ui/fragment/PreviewImageFragment.java (David A. Velasco 2013-02-25 12:24:14 +0100 493) 
6cfa24f5 src/com/owncloud/android/ui/preview/PreviewImageFragment.java  (David A. Velasco 2013-03-01 09:54:49 +0100 494)                 if (width > screenWidth) {
07a89382 src/com/owncloud/android/ui/preview/PreviewImageFragment.java  (David A. Velasco 2013-03-01 11:17:12 +0100 495)                     // second try to scale down the image , this time depending upon the screen size 
07a89382 src/com/owncloud/android/ui/preview/PreviewImageFragment.java  (David A. Velasco 2013-03-01 11:17:12 +0100 496)                     scale = (int) Math.floor((float)width / screenWidth);
3d272eb7 src/com/owncloud/android/ui/fragment/PreviewImageFragment.java (David A. Velasco 2013-02-25 12:24:14 +0100 497)                 }
6cfa24f5 src/com/owncloud/android/ui/preview/PreviewImageFragment.java  (David A. Velasco 2013-03-01 09:54:49 +0100 498)                 if (height > screenHeight) {
07a89382 src/com/owncloud/android/ui/preview/PreviewImageFragment.java  (David A. Velasco 2013-03-01 11:17:12 +0100 499)                     scale = Math.max(scale, (int) Math.floor((float)height / screenHeight));
6cfa24f5 src/com/owncloud/android/ui/preview/PreviewImageFragment.java  (David A. Velasco 2013-03-01 09:54:49 +0100 500)                 }
07a89382 src/com/owncloud/android/ui/preview/PreviewImageFragment.java  (David A. Velasco 2013-03-01 11:17:12 +0100 501)                 options.inSampleSize = scale;
3d272eb7 src/com/owncloud/android/ui/fragment/PreviewImageFragment.java (David A. Velasco 2013-02-25 12:24:14 +0100 502) 
3d272eb7 src/com/owncloud/android/ui/fragment/PreviewImageFragment.java (David A. Velasco 2013-02-25 12:24:14 +0100 503)                 // really load the bitmap
3d272eb7 src/com/owncloud/android/ui/fragment/PreviewImageFragment.java (David A. Velasco 2013-02-25 12:24:14 +0100 504)                 options.inJustDecodeBounds = false; // the next decodeFile call will be real
3d272eb7 src/com/owncloud/android/ui/fragment/PreviewImageFragment.java (David A. Velasco 2013-02-25 12:24:14 +0100 505)                 result = BitmapFactory.decodeFile(storagePath, options);
2946d8dd src/com/owncloud/android/ui/preview/PreviewImageFragment.java  (David A. Velasco 2013-04-25 19:39:22 +0200 506)                 //Log_OC.d(TAG, "Image loaded - width: " + options.outWidth + ", loaded height: " + options.outHeight);
3d272eb7 src/com/owncloud/android/ui/fragment/PreviewImageFragment.java (David A. Velasco 2013-02-25 12:24:14 +0100 507) 
427a3e63 src/com/owncloud/android/ui/preview/PreviewImageFragment.java  (David A. Velasco 2013-03-01 10:49:35 +0100 508)                 if (result == null) {
427a3e63 src/com/owncloud/android/ui/preview/PreviewImageFragment.java  (David A. Velasco 2013-03-01 10:49:35 +0100 509)                     mErrorMessageId = R.string.preview_image_error_unknown_format;
2946d8dd src/com/owncloud/android/ui/preview/PreviewImageFragment.java  (David A. Velasco 2013-04-25 19:39:22 +0200 510)                     Log_OC.e(TAG, "File could not be loaded as a bitmap: " + storagePath);
427a3e63 src/com/owncloud/android/ui/preview/PreviewImageFragment.java  (David A. Velasco 2013-03-01 10:49:35 +0100 511)                 }
427a3e63 src/com/owncloud/android/ui/preview/PreviewImageFragment.java  (David A. Velasco 2013-03-01 10:49:35 +0100 512)                 
427a3e63 src/com/owncloud/android/ui/preview/PreviewImageFragment.java  (David A. Velasco 2013-03-01 10:49:35 +0100 513)             } catch (OutOfMemoryError e) {
427a3e63 src/com/owncloud/android/ui/preview/PreviewImageFragment.java  (David A. Velasco 2013-03-01 10:49:35 +0100 514)                 mErrorMessageId = R.string.preview_image_error_unknown_format;
2946d8dd src/com/owncloud/android/ui/preview/PreviewImageFragment.java  (David A. Velasco 2013-04-25 19:39:22 +0200 515)                 Log_OC.e(TAG, "Out of memory occured for file " + storagePath, e);
427a3e63 src/com/owncloud/android/ui/preview/PreviewImageFragment.java  (David A. Velasco 2013-03-01 10:49:35 +0100 516)                     
427a3e63 src/com/owncloud/android/ui/preview/PreviewImageFragment.java  (David A. Velasco 2013-03-01 10:49:35 +0100 517)             } catch (NoSuchFieldError e) {
427a3e63 src/com/owncloud/android/ui/preview/PreviewImageFragment.java  (David A. Velasco 2013-03-01 10:49:35 +0100 518)                 mErrorMessageId = R.string.common_error_unknown;
2946d8dd src/com/owncloud/android/ui/preview/PreviewImageFragment.java  (David A. Velasco 2013-04-25 19:39:22 +0200 519)                 Log_OC.e(TAG, "Error from access to unexisting field despite protection; file " + storagePath, e);
427a3e63 src/com/owncloud/android/ui/preview/PreviewImageFragment.java  (David A. Velasco 2013-03-01 10:49:35 +0100 520)                     
3d272eb7 src/com/owncloud/android/ui/fragment/PreviewImageFragment.java (David A. Velasco 2013-02-25 12:24:14 +0100 521)             } catch (Throwable t) {
427a3e63 src/com/owncloud/android/ui/preview/PreviewImageFragment.java  (David A. Velasco 2013-03-01 10:49:35 +0100 522)                 mErrorMessageId = R.string.common_error_unknown;
fd396289 src/com/owncloud/android/ui/preview/PreviewImageFragment.java  (David A. Velasco 2013-05-30 17:53:21 +0200 523)                 Log_OC.e(TAG, "Unexpected error loading " + getFile().getStoragePath(), t);
427a3e63 src/com/owncloud/android/ui/preview/PreviewImageFragment.java  (David A. Velasco 2013-03-01 10:49:35 +0100 524)                 
3d272eb7 src/com/owncloud/android/ui/fragment/PreviewImageFragment.java (David A. Velasco 2013-02-25 12:24:14 +0100 525)             }
3d272eb7 src/com/owncloud/android/ui/fragment/PreviewImageFragment.java (David A. Velasco 2013-02-25 12:24:14 +0100 526)             return result;
3d272eb7 src/com/owncloud/android/ui/fragment/PreviewImageFragment.java (David A. Velasco 2013-02-25 12:24:14 +0100 527)         }
3d272eb7 src/com/owncloud/android/ui/fragment/PreviewImageFragment.java (David A. Velasco 2013-02-25 12:24:14 +0100 528)         
3d272eb7 src/com/owncloud/android/ui/fragment/PreviewImageFragment.java (David A. Velasco 2013-02-25 12:24:14 +0100 529)         @Override
3d272eb7 src/com/owncloud/android/ui/fragment/PreviewImageFragment.java (David A. Velasco 2013-02-25 12:24:14 +0100 530)         protected void onPostExecute(Bitmap result) {
427a3e63 src/com/owncloud/android/ui/preview/PreviewImageFragment.java  (David A. Velasco 2013-03-01 10:49:35 +0100 531)             hideProgressWheel();
427a3e63 src/com/owncloud/android/ui/preview/PreviewImageFragment.java  (David A. Velasco 2013-03-01 10:49:35 +0100 532)             if (result != null) {
427a3e63 src/com/owncloud/android/ui/preview/PreviewImageFragment.java  (David A. Velasco 2013-03-01 10:49:35 +0100 533)                 showLoadedImage(result);
427a3e63 src/com/owncloud/android/ui/preview/PreviewImageFragment.java  (David A. Velasco 2013-03-01 10:49:35 +0100 534)             } else {
427a3e63 src/com/owncloud/android/ui/preview/PreviewImageFragment.java  (David A. Velasco 2013-03-01 10:49:35 +0100 535)                 showErrorMessage();
427a3e63 src/com/owncloud/android/ui/preview/PreviewImageFragment.java  (David A. Velasco 2013-03-01 10:49:35 +0100 536)             }
427a3e63 src/com/owncloud/android/ui/preview/PreviewImageFragment.java  (David A. Velasco 2013-03-01 10:49:35 +0100 537)         }
427a3e63 src/com/owncloud/android/ui/preview/PreviewImageFragment.java  (David A. Velasco 2013-03-01 10:49:35 +0100 538)         
427a3e63 src/com/owncloud/android/ui/preview/PreviewImageFragment.java  (David A. Velasco 2013-03-01 10:49:35 +0100 539)         private void showLoadedImage(Bitmap result) {
427a3e63 src/com/owncloud/android/ui/preview/PreviewImageFragment.java  (David A. Velasco 2013-03-01 10:49:35 +0100 540)             if (mImageViewRef != null) {
3d272eb7 src/com/owncloud/android/ui/fragment/PreviewImageFragment.java (David A. Velasco 2013-02-25 12:24:14 +0100 541)                 final ImageView imageView = mImageViewRef.get();
6cfa24f5 src/com/owncloud/android/ui/preview/PreviewImageFragment.java  (David A. Velasco 2013-03-01 09:54:49 +0100 542)                 if (imageView != null) {
6cfa24f5 src/com/owncloud/android/ui/preview/PreviewImageFragment.java  (David A. Velasco 2013-03-01 09:54:49 +0100 543)                     imageView.setImageBitmap(result);
427a3e63 src/com/owncloud/android/ui/preview/PreviewImageFragment.java  (David A. Velasco 2013-03-01 10:49:35 +0100 544)                     imageView.setVisibility(View.VISIBLE);
6cfa24f5 src/com/owncloud/android/ui/preview/PreviewImageFragment.java  (David A. Velasco 2013-03-01 09:54:49 +0100 545)                     mBitmap  = result;
6cfa24f5 src/com/owncloud/android/ui/preview/PreviewImageFragment.java  (David A. Velasco 2013-03-01 09:54:49 +0100 546)                 } // else , silently finish, the fragment was destroyed
427a3e63 src/com/owncloud/android/ui/preview/PreviewImageFragment.java  (David A. Velasco 2013-03-01 10:49:35 +0100 547)             }
427a3e63 src/com/owncloud/android/ui/preview/PreviewImageFragment.java  (David A. Velasco 2013-03-01 10:49:35 +0100 548)             if (mMessageViewRef != null) {
427a3e63 src/com/owncloud/android/ui/preview/PreviewImageFragment.java  (David A. Velasco 2013-03-01 10:49:35 +0100 549)                 final TextView messageView = mMessageViewRef.get();
427a3e63 src/com/owncloud/android/ui/preview/PreviewImageFragment.java  (David A. Velasco 2013-03-01 10:49:35 +0100 550)                 if (messageView != null) {
427a3e63 src/com/owncloud/android/ui/preview/PreviewImageFragment.java  (David A. Velasco 2013-03-01 10:49:35 +0100 551)                     messageView.setVisibility(View.GONE);
427a3e63 src/com/owncloud/android/ui/preview/PreviewImageFragment.java  (David A. Velasco 2013-03-01 10:49:35 +0100 552)                 } // else , silently finish, the fragment was destroyed
427a3e63 src/com/owncloud/android/ui/preview/PreviewImageFragment.java  (David A. Velasco 2013-03-01 10:49:35 +0100 553)             }
427a3e63 src/com/owncloud/android/ui/preview/PreviewImageFragment.java  (David A. Velasco 2013-03-01 10:49:35 +0100 554)         }
427a3e63 src/com/owncloud/android/ui/preview/PreviewImageFragment.java  (David A. Velasco 2013-03-01 10:49:35 +0100 555)         
427a3e63 src/com/owncloud/android/ui/preview/PreviewImageFragment.java  (David A. Velasco 2013-03-01 10:49:35 +0100 556)         private void showErrorMessage() {
427a3e63 src/com/owncloud/android/ui/preview/PreviewImageFragment.java  (David A. Velasco 2013-03-01 10:49:35 +0100 557)             if (mImageViewRef != null) {
427a3e63 src/com/owncloud/android/ui/preview/PreviewImageFragment.java  (David A. Velasco 2013-03-01 10:49:35 +0100 558)                 final ImageView imageView = mImageViewRef.get();
427a3e63 src/com/owncloud/android/ui/preview/PreviewImageFragment.java  (David A. Velasco 2013-03-01 10:49:35 +0100 559)                 if (imageView != null) {
427a3e63 src/com/owncloud/android/ui/preview/PreviewImageFragment.java  (David A. Velasco 2013-03-01 10:49:35 +0100 560)                     // shows the default error icon
427a3e63 src/com/owncloud/android/ui/preview/PreviewImageFragment.java  (David A. Velasco 2013-03-01 10:49:35 +0100 561)                     imageView.setVisibility(View.VISIBLE);
427a3e63 src/com/owncloud/android/ui/preview/PreviewImageFragment.java  (David A. Velasco 2013-03-01 10:49:35 +0100 562)                 } // else , silently finish, the fragment was destroyed
427a3e63 src/com/owncloud/android/ui/preview/PreviewImageFragment.java  (David A. Velasco 2013-03-01 10:49:35 +0100 563)             }
427a3e63 src/com/owncloud/android/ui/preview/PreviewImageFragment.java  (David A. Velasco 2013-03-01 10:49:35 +0100 564)             if (mMessageViewRef != null) {
6cfa24f5 src/com/owncloud/android/ui/preview/PreviewImageFragment.java  (David A. Velasco 2013-03-01 09:54:49 +0100 565)                 final TextView messageView = mMessageViewRef.get();
6cfa24f5 src/com/owncloud/android/ui/preview/PreviewImageFragment.java  (David A. Velasco 2013-03-01 09:54:49 +0100 566)                 if (messageView != null) {
427a3e63 src/com/owncloud/android/ui/preview/PreviewImageFragment.java  (David A. Velasco 2013-03-01 10:49:35 +0100 567)                     messageView.setText(mErrorMessageId);
6cfa24f5 src/com/owncloud/android/ui/preview/PreviewImageFragment.java  (David A. Velasco 2013-03-01 09:54:49 +0100 568)                     messageView.setVisibility(View.VISIBLE);
6cfa24f5 src/com/owncloud/android/ui/preview/PreviewImageFragment.java  (David A. Velasco 2013-03-01 09:54:49 +0100 569)                 } // else , silently finish, the fragment was destroyed
3d272eb7 src/com/owncloud/android/ui/fragment/PreviewImageFragment.java (David A. Velasco 2013-02-25 12:24:14 +0100 570)             }
427a3e63 src/com/owncloud/android/ui/preview/PreviewImageFragment.java  (David A. Velasco 2013-03-01 10:49:35 +0100 571)         }
427a3e63 src/com/owncloud/android/ui/preview/PreviewImageFragment.java  (David A. Velasco 2013-03-01 10:49:35 +0100 572)         
427a3e63 src/com/owncloud/android/ui/preview/PreviewImageFragment.java  (David A. Velasco 2013-03-01 10:49:35 +0100 573)         private void hideProgressWheel() {
427a3e63 src/com/owncloud/android/ui/preview/PreviewImageFragment.java  (David A. Velasco 2013-03-01 10:49:35 +0100 574)             if (mProgressWheelRef != null) {
427a3e63 src/com/owncloud/android/ui/preview/PreviewImageFragment.java  (David A. Velasco 2013-03-01 10:49:35 +0100 575)                 final ProgressBar progressWheel = mProgressWheelRef.get();
427a3e63 src/com/owncloud/android/ui/preview/PreviewImageFragment.java  (David A. Velasco 2013-03-01 10:49:35 +0100 576)                 if (progressWheel != null) {
427a3e63 src/com/owncloud/android/ui/preview/PreviewImageFragment.java  (David A. Velasco 2013-03-01 10:49:35 +0100 577)                     progressWheel.setVisibility(View.GONE);
427a3e63 src/com/owncloud/android/ui/preview/PreviewImageFragment.java  (David A. Velasco 2013-03-01 10:49:35 +0100 578)                 }
427a3e63 src/com/owncloud/android/ui/preview/PreviewImageFragment.java  (David A. Velasco 2013-03-01 10:49:35 +0100 579)             }
3d272eb7 src/com/owncloud/android/ui/fragment/PreviewImageFragment.java (David A. Velasco 2013-02-25 12:24:14 +0100 580)         }
3d272eb7 src/com/owncloud/android/ui/fragment/PreviewImageFragment.java (David A. Velasco 2013-02-25 12:24:14 +0100 581)         
3d272eb7 src/com/owncloud/android/ui/fragment/PreviewImageFragment.java (David A. Velasco 2013-02-25 12:24:14 +0100 582)     }
3d272eb7 src/com/owncloud/android/ui/fragment/PreviewImageFragment.java (David A. Velasco 2013-02-25 12:24:14 +0100 583) 
3d272eb7 src/com/owncloud/android/ui/fragment/PreviewImageFragment.java (David A. Velasco 2013-02-25 12:24:14 +0100 584)     /**
3d272eb7 src/com/owncloud/android/ui/fragment/PreviewImageFragment.java (David A. Velasco 2013-02-25 12:24:14 +0100 585)      * Helper method to test if an {@link OCFile} can be passed to a {@link PreviewImageFragment} to be previewed.
3d272eb7 src/com/owncloud/android/ui/fragment/PreviewImageFragment.java (David A. Velasco 2013-02-25 12:24:14 +0100 586)      * 
3d272eb7 src/com/owncloud/android/ui/fragment/PreviewImageFragment.java (David A. Velasco 2013-02-25 12:24:14 +0100 587)      * @param file      File to test if can be previewed.
3d272eb7 src/com/owncloud/android/ui/fragment/PreviewImageFragment.java (David A. Velasco 2013-02-25 12:24:14 +0100 588)      * @return          'True' if the file can be handled by the fragment.
3d272eb7 src/com/owncloud/android/ui/fragment/PreviewImageFragment.java (David A. Velasco 2013-02-25 12:24:14 +0100 589)      */
3d272eb7 src/com/owncloud/android/ui/fragment/PreviewImageFragment.java (David A. Velasco 2013-02-25 12:24:14 +0100 590)     public static boolean canBePreviewed(OCFile file) {
3d272eb7 src/com/owncloud/android/ui/fragment/PreviewImageFragment.java (David A. Velasco 2013-02-25 12:24:14 +0100 591)         return (file != null && file.isImage());
3d272eb7 src/com/owncloud/android/ui/fragment/PreviewImageFragment.java (David A. Velasco 2013-02-25 12:24:14 +0100 592)     }
3d272eb7 src/com/owncloud/android/ui/fragment/PreviewImageFragment.java (David A. Velasco 2013-02-25 12:24:14 +0100 593) 
6cfa24f5 src/com/owncloud/android/ui/preview/PreviewImageFragment.java  (David A. Velasco 2013-03-01 09:54:49 +0100 594)     
3d272eb7 src/com/owncloud/android/ui/fragment/PreviewImageFragment.java (David A. Velasco 2013-02-25 12:24:14 +0100 595)     /**
3d272eb7 src/com/owncloud/android/ui/fragment/PreviewImageFragment.java (David A. Velasco 2013-02-25 12:24:14 +0100 596)      * {@inheritDoc}
3d272eb7 src/com/owncloud/android/ui/fragment/PreviewImageFragment.java (David A. Velasco 2013-02-25 12:24:14 +0100 597)      */
3d272eb7 src/com/owncloud/android/ui/fragment/PreviewImageFragment.java (David A. Velasco 2013-02-25 12:24:14 +0100 598)     @Override
3d272eb7 src/com/owncloud/android/ui/fragment/PreviewImageFragment.java (David A. Velasco 2013-02-25 12:24:14 +0100 599)     public void onRemoteOperationFinish(RemoteOperation operation, RemoteOperationResult result) {
3d272eb7 src/com/owncloud/android/ui/fragment/PreviewImageFragment.java (David A. Velasco 2013-02-25 12:24:14 +0100 600)         if (operation.equals(mLastRemoteOperation) && operation instanceof RemoveFileOperation) {
3d272eb7 src/com/owncloud/android/ui/fragment/PreviewImageFragment.java (David A. Velasco 2013-02-25 12:24:14 +0100 601)             onRemoveFileOperationFinish((RemoveFileOperation)operation, result);
3d272eb7 src/com/owncloud/android/ui/fragment/PreviewImageFragment.java (David A. Velasco 2013-02-25 12:24:14 +0100 602)         }
3d272eb7 src/com/owncloud/android/ui/fragment/PreviewImageFragment.java (David A. Velasco 2013-02-25 12:24:14 +0100 603)     }
3d272eb7 src/com/owncloud/android/ui/fragment/PreviewImageFragment.java (David A. Velasco 2013-02-25 12:24:14 +0100 604)     
3d272eb7 src/com/owncloud/android/ui/fragment/PreviewImageFragment.java (David A. Velasco 2013-02-25 12:24:14 +0100 605)     private void onRemoveFileOperationFinish(RemoveFileOperation operation, RemoteOperationResult result) {
3d272eb7 src/com/owncloud/android/ui/fragment/PreviewImageFragment.java (David A. Velasco 2013-02-25 12:24:14 +0100 606)         getActivity().dismissDialog(PreviewImageActivity.DIALOG_SHORT_WAIT);
3d272eb7 src/com/owncloud/android/ui/fragment/PreviewImageFragment.java (David A. Velasco 2013-02-25 12:24:14 +0100 607)         
3d272eb7 src/com/owncloud/android/ui/fragment/PreviewImageFragment.java (David A. Velasco 2013-02-25 12:24:14 +0100 608)         if (result.isSuccess()) {
3d272eb7 src/com/owncloud/android/ui/fragment/PreviewImageFragment.java (David A. Velasco 2013-02-25 12:24:14 +0100 609)             Toast msg = Toast.makeText(getActivity().getApplicationContext(), R.string.remove_success_msg, Toast.LENGTH_LONG);
3d272eb7 src/com/owncloud/android/ui/fragment/PreviewImageFragment.java (David A. Velasco 2013-02-25 12:24:14 +0100 610)             msg.show();
3d272eb7 src/com/owncloud/android/ui/fragment/PreviewImageFragment.java (David A. Velasco 2013-02-25 12:24:14 +0100 611)             finish();
3d272eb7 src/com/owncloud/android/ui/fragment/PreviewImageFragment.java (David A. Velasco 2013-02-25 12:24:14 +0100 612)                 
3d272eb7 src/com/owncloud/android/ui/fragment/PreviewImageFragment.java (David A. Velasco 2013-02-25 12:24:14 +0100 613)         } else {
3d272eb7 src/com/owncloud/android/ui/fragment/PreviewImageFragment.java (David A. Velasco 2013-02-25 12:24:14 +0100 614)             Toast msg = Toast.makeText(getActivity(), R.string.remove_fail_msg, Toast.LENGTH_LONG); 
3d272eb7 src/com/owncloud/android/ui/fragment/PreviewImageFragment.java (David A. Velasco 2013-02-25 12:24:14 +0100 615)             msg.show();
3d272eb7 src/com/owncloud/android/ui/fragment/PreviewImageFragment.java (David A. Velasco 2013-02-25 12:24:14 +0100 616)             if (result.isSslRecoverableException()) {
3d272eb7 src/com/owncloud/android/ui/fragment/PreviewImageFragment.java (David A. Velasco 2013-02-25 12:24:14 +0100 617)                 // TODO show the SSL warning dialog
3d272eb7 src/com/owncloud/android/ui/fragment/PreviewImageFragment.java (David A. Velasco 2013-02-25 12:24:14 +0100 618)             }
3d272eb7 src/com/owncloud/android/ui/fragment/PreviewImageFragment.java (David A. Velasco 2013-02-25 12:24:14 +0100 619)         }
3d272eb7 src/com/owncloud/android/ui/fragment/PreviewImageFragment.java (David A. Velasco 2013-02-25 12:24:14 +0100 620)     }
3d272eb7 src/com/owncloud/android/ui/fragment/PreviewImageFragment.java (David A. Velasco 2013-02-25 12:24:14 +0100 621) 
3d272eb7 src/com/owncloud/android/ui/fragment/PreviewImageFragment.java (David A. Velasco 2013-02-25 12:24:14 +0100 622)     /**
3d272eb7 src/com/owncloud/android/ui/fragment/PreviewImageFragment.java (David A. Velasco 2013-02-25 12:24:14 +0100 623)      * Finishes the preview
3d272eb7 src/com/owncloud/android/ui/fragment/PreviewImageFragment.java (David A. Velasco 2013-02-25 12:24:14 +0100 624)      */
3d272eb7 src/com/owncloud/android/ui/fragment/PreviewImageFragment.java (David A. Velasco 2013-02-25 12:24:14 +0100 625)     private void finish() {
3d272eb7 src/com/owncloud/android/ui/fragment/PreviewImageFragment.java (David A. Velasco 2013-02-25 12:24:14 +0100 626)         Activity container = getActivity();
3d272eb7 src/com/owncloud/android/ui/fragment/PreviewImageFragment.java (David A. Velasco 2013-02-25 12:24:14 +0100 627)         container.finish();
3d272eb7 src/com/owncloud/android/ui/fragment/PreviewImageFragment.java (David A. Velasco 2013-02-25 12:24:14 +0100 628)     }
3d272eb7 src/com/owncloud/android/ui/fragment/PreviewImageFragment.java (David A. Velasco 2013-02-25 12:24:14 +0100 629)     
6cfa24f5 src/com/owncloud/android/ui/preview/PreviewImageFragment.java  (David A. Velasco 2013-03-01 09:54:49 +0100 630)     
3d272eb7 src/com/owncloud/android/ui/fragment/PreviewImageFragment.java (David A. Velasco 2013-02-25 12:24:14 +0100 631) }
