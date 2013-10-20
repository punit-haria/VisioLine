d7e05192 src/com/owncloud/android/ui/fragment/FileDownloadFragment.java (David A. Velasco 2013-02-25 14:33:16 +0100   1) /* ownCloud Android client application
cb66b599 src/com/owncloud/android/ui/fragment/FileDownloadFragment.java (David A. Velasco 2013-02-25 15:19:18 +0100   2)  * 
cb66b599 src/com/owncloud/android/ui/fragment/FileDownloadFragment.java (David A. Velasco 2013-02-25 15:19:18 +0100   3)  *   Copyright (C) 2012-2013  ownCloud Inc.
d7e05192 src/com/owncloud/android/ui/fragment/FileDownloadFragment.java (David A. Velasco 2013-02-25 14:33:16 +0100   4)  *
d7e05192 src/com/owncloud/android/ui/fragment/FileDownloadFragment.java (David A. Velasco 2013-02-25 14:33:16 +0100   5)  *   This program is free software: you can redistribute it and/or modify
2946d8dd src/com/owncloud/android/ui/preview/FileDownloadFragment.java  (David A. Velasco 2013-04-25 19:39:22 +0200   6)  *   it under the terms of the GNU General Public License version 2,
2946d8dd src/com/owncloud/android/ui/preview/FileDownloadFragment.java  (David A. Velasco 2013-04-25 19:39:22 +0200   7)  *   as published by the Free Software Foundation.
d7e05192 src/com/owncloud/android/ui/fragment/FileDownloadFragment.java (David A. Velasco 2013-02-25 14:33:16 +0100   8)  *
d7e05192 src/com/owncloud/android/ui/fragment/FileDownloadFragment.java (David A. Velasco 2013-02-25 14:33:16 +0100   9)  *   This program is distributed in the hope that it will be useful,
d7e05192 src/com/owncloud/android/ui/fragment/FileDownloadFragment.java (David A. Velasco 2013-02-25 14:33:16 +0100  10)  *   but WITHOUT ANY WARRANTY; without even the implied warranty of
d7e05192 src/com/owncloud/android/ui/fragment/FileDownloadFragment.java (David A. Velasco 2013-02-25 14:33:16 +0100  11)  *   MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
d7e05192 src/com/owncloud/android/ui/fragment/FileDownloadFragment.java (David A. Velasco 2013-02-25 14:33:16 +0100  12)  *   GNU General Public License for more details.
d7e05192 src/com/owncloud/android/ui/fragment/FileDownloadFragment.java (David A. Velasco 2013-02-25 14:33:16 +0100  13)  *
d7e05192 src/com/owncloud/android/ui/fragment/FileDownloadFragment.java (David A. Velasco 2013-02-25 14:33:16 +0100  14)  *   You should have received a copy of the GNU General Public License
d7e05192 src/com/owncloud/android/ui/fragment/FileDownloadFragment.java (David A. Velasco 2013-02-25 14:33:16 +0100  15)  *   along with this program.  If not, see <http://www.gnu.org/licenses/>.
d7e05192 src/com/owncloud/android/ui/fragment/FileDownloadFragment.java (David A. Velasco 2013-02-25 14:33:16 +0100  16)  *
d7e05192 src/com/owncloud/android/ui/fragment/FileDownloadFragment.java (David A. Velasco 2013-02-25 14:33:16 +0100  17)  */
a3aca946 src/com/owncloud/android/ui/preview/FileDownloadFragment.java  (David A. Velasco 2013-02-26 13:17:44 +0100  18) package com.owncloud.android.ui.preview;
d7e05192 src/com/owncloud/android/ui/fragment/FileDownloadFragment.java (David A. Velasco 2013-02-25 14:33:16 +0100  19) 
d7e05192 src/com/owncloud/android/ui/fragment/FileDownloadFragment.java (David A. Velasco 2013-02-25 14:33:16 +0100  20) import java.lang.ref.WeakReference;
d7e05192 src/com/owncloud/android/ui/fragment/FileDownloadFragment.java (David A. Velasco 2013-02-25 14:33:16 +0100  21) 
d7e05192 src/com/owncloud/android/ui/fragment/FileDownloadFragment.java (David A. Velasco 2013-02-25 14:33:16 +0100  22) import android.accounts.Account;
d7e05192 src/com/owncloud/android/ui/fragment/FileDownloadFragment.java (David A. Velasco 2013-02-25 14:33:16 +0100  23) import android.app.Activity;
d7e05192 src/com/owncloud/android/ui/fragment/FileDownloadFragment.java (David A. Velasco 2013-02-25 14:33:16 +0100  24) import android.os.Bundle;
86cec34b src/com/owncloud/android/ui/preview/FileDownloadFragment.java  (David A. Velasco 2013-02-27 14:08:58 +0100  25) import android.support.v4.app.FragmentStatePagerAdapter;
d7e05192 src/com/owncloud/android/ui/fragment/FileDownloadFragment.java (David A. Velasco 2013-02-25 14:33:16 +0100  26) import android.view.LayoutInflater;
d7e05192 src/com/owncloud/android/ui/fragment/FileDownloadFragment.java (David A. Velasco 2013-02-25 14:33:16 +0100  27) import android.view.View;
d7e05192 src/com/owncloud/android/ui/fragment/FileDownloadFragment.java (David A. Velasco 2013-02-25 14:33:16 +0100  28) import android.view.View.OnClickListener;
d7e05192 src/com/owncloud/android/ui/fragment/FileDownloadFragment.java (David A. Velasco 2013-02-25 14:33:16 +0100  29) import android.view.ViewGroup;
93f79c70 src/com/owncloud/android/ui/preview/FileDownloadFragment.java  (David A. Velasco 2013-06-05 11:08:33 +0200  30) import android.widget.ImageButton;
d7e05192 src/com/owncloud/android/ui/fragment/FileDownloadFragment.java (David A. Velasco 2013-02-25 14:33:16 +0100  31) import android.widget.ProgressBar;
d7e05192 src/com/owncloud/android/ui/fragment/FileDownloadFragment.java (David A. Velasco 2013-02-25 14:33:16 +0100  32) import android.widget.TextView;
d7e05192 src/com/owncloud/android/ui/fragment/FileDownloadFragment.java (David A. Velasco 2013-02-25 14:33:16 +0100  33) 
d7e05192 src/com/owncloud/android/ui/fragment/FileDownloadFragment.java (David A. Velasco 2013-02-25 14:33:16 +0100  34) import com.owncloud.android.datamodel.OCFile;
d7e05192 src/com/owncloud/android/ui/fragment/FileDownloadFragment.java (David A. Velasco 2013-02-25 14:33:16 +0100  35) import com.owncloud.android.files.services.FileDownloader.FileDownloaderBinder;
a3aca946 src/com/owncloud/android/ui/preview/FileDownloadFragment.java  (David A. Velasco 2013-02-26 13:17:44 +0100  36) import com.owncloud.android.ui.fragment.FileFragment;
d7e05192 src/com/owncloud/android/ui/fragment/FileDownloadFragment.java (David A. Velasco 2013-02-25 14:33:16 +0100  37) 
2946d8dd src/com/owncloud/android/ui/preview/FileDownloadFragment.java  (David A. Velasco 2013-04-25 19:39:22 +0200  38) import com.owncloud.android.Log_OC;
d7e05192 src/com/owncloud/android/ui/fragment/FileDownloadFragment.java (David A. Velasco 2013-02-25 14:33:16 +0100  39) import com.owncloud.android.R;
d7e05192 src/com/owncloud/android/ui/fragment/FileDownloadFragment.java (David A. Velasco 2013-02-25 14:33:16 +0100  40) 
d7e05192 src/com/owncloud/android/ui/fragment/FileDownloadFragment.java (David A. Velasco 2013-02-25 14:33:16 +0100  41) import eu.alefzero.webdav.OnDatatransferProgressListener;
d7e05192 src/com/owncloud/android/ui/fragment/FileDownloadFragment.java (David A. Velasco 2013-02-25 14:33:16 +0100  42) 
d7e05192 src/com/owncloud/android/ui/fragment/FileDownloadFragment.java (David A. Velasco 2013-02-25 14:33:16 +0100  43) /**
cb66b599 src/com/owncloud/android/ui/fragment/FileDownloadFragment.java (David A. Velasco 2013-02-25 15:19:18 +0100  44)  * This Fragment is used to monitor the progress of a file downloading.
d7e05192 src/com/owncloud/android/ui/fragment/FileDownloadFragment.java (David A. Velasco 2013-02-25 14:33:16 +0100  45)  * 
d7e05192 src/com/owncloud/android/ui/fragment/FileDownloadFragment.java (David A. Velasco 2013-02-25 14:33:16 +0100  46)  * @author David A. Velasco
d7e05192 src/com/owncloud/android/ui/fragment/FileDownloadFragment.java (David A. Velasco 2013-02-25 14:33:16 +0100  47)  */
fd396289 src/com/owncloud/android/ui/preview/FileDownloadFragment.java  (David A. Velasco 2013-05-30 17:53:21 +0200  48) public class FileDownloadFragment extends FileFragment implements OnClickListener {
d7e05192 src/com/owncloud/android/ui/fragment/FileDownloadFragment.java (David A. Velasco 2013-02-25 14:33:16 +0100  49) 
d7e05192 src/com/owncloud/android/ui/fragment/FileDownloadFragment.java (David A. Velasco 2013-02-25 14:33:16 +0100  50)     public static final String EXTRA_FILE = "FILE";
d7e05192 src/com/owncloud/android/ui/fragment/FileDownloadFragment.java (David A. Velasco 2013-02-25 14:33:16 +0100  51)     public static final String EXTRA_ACCOUNT = "ACCOUNT";
db7eea71 src/com/owncloud/android/ui/preview/FileDownloadFragment.java  (David A. Velasco 2013-03-01 14:20:25 +0100  52)     private static final String EXTRA_ERROR = "ERROR";
d7e05192 src/com/owncloud/android/ui/fragment/FileDownloadFragment.java (David A. Velasco 2013-02-25 14:33:16 +0100  53) 
d7e05192 src/com/owncloud/android/ui/fragment/FileDownloadFragment.java (David A. Velasco 2013-02-25 14:33:16 +0100  54)     private FileFragment.ContainerActivity mContainerActivity;
d7e05192 src/com/owncloud/android/ui/fragment/FileDownloadFragment.java (David A. Velasco 2013-02-25 14:33:16 +0100  55)     
d7e05192 src/com/owncloud/android/ui/fragment/FileDownloadFragment.java (David A. Velasco 2013-02-25 14:33:16 +0100  56)     private View mView;
d7e05192 src/com/owncloud/android/ui/fragment/FileDownloadFragment.java (David A. Velasco 2013-02-25 14:33:16 +0100  57)     private Account mAccount;
d7e05192 src/com/owncloud/android/ui/fragment/FileDownloadFragment.java (David A. Velasco 2013-02-25 14:33:16 +0100  58)     
d7e05192 src/com/owncloud/android/ui/fragment/FileDownloadFragment.java (David A. Velasco 2013-02-25 14:33:16 +0100  59)     public ProgressListener mProgressListener;
fc2af5b0 src/com/owncloud/android/ui/fragment/FileDownloadFragment.java (David A. Velasco 2013-02-26 12:33:28 +0100  60)     private boolean mListening;
d7e05192 src/com/owncloud/android/ui/fragment/FileDownloadFragment.java (David A. Velasco 2013-02-25 14:33:16 +0100  61)     
d7e05192 src/com/owncloud/android/ui/fragment/FileDownloadFragment.java (David A. Velasco 2013-02-25 14:33:16 +0100  62)     private static final String TAG = FileDownloadFragment.class.getSimpleName();
db7eea71 src/com/owncloud/android/ui/preview/FileDownloadFragment.java  (David A. Velasco 2013-03-01 14:20:25 +0100  63)     
86cec34b src/com/owncloud/android/ui/preview/FileDownloadFragment.java  (David A. Velasco 2013-02-27 14:08:58 +0100  64)     private boolean mIgnoreFirstSavedState;
db7eea71 src/com/owncloud/android/ui/preview/FileDownloadFragment.java  (David A. Velasco 2013-03-01 14:20:25 +0100  65)     private boolean mError;
d7e05192 src/com/owncloud/android/ui/fragment/FileDownloadFragment.java (David A. Velasco 2013-02-25 14:33:16 +0100  66)     
86cec34b src/com/owncloud/android/ui/preview/FileDownloadFragment.java  (David A. Velasco 2013-02-27 14:08:58 +0100  67) 
d7e05192 src/com/owncloud/android/ui/fragment/FileDownloadFragment.java (David A. Velasco 2013-02-25 14:33:16 +0100  68)     /**
d7e05192 src/com/owncloud/android/ui/fragment/FileDownloadFragment.java (David A. Velasco 2013-02-25 14:33:16 +0100  69)      * Creates an empty details fragment.
d7e05192 src/com/owncloud/android/ui/fragment/FileDownloadFragment.java (David A. Velasco 2013-02-25 14:33:16 +0100  70)      * 
d7e05192 src/com/owncloud/android/ui/fragment/FileDownloadFragment.java (David A. Velasco 2013-02-25 14:33:16 +0100  71)      * It's necessary to keep a public constructor without parameters; the system uses it when tries to reinstantiate a fragment automatically. 
d7e05192 src/com/owncloud/android/ui/fragment/FileDownloadFragment.java (David A. Velasco 2013-02-25 14:33:16 +0100  72)      */
d7e05192 src/com/owncloud/android/ui/fragment/FileDownloadFragment.java (David A. Velasco 2013-02-25 14:33:16 +0100  73)     public FileDownloadFragment() {
fd396289 src/com/owncloud/android/ui/preview/FileDownloadFragment.java  (David A. Velasco 2013-05-30 17:53:21 +0200  74)         super();
d7e05192 src/com/owncloud/android/ui/fragment/FileDownloadFragment.java (David A. Velasco 2013-02-25 14:33:16 +0100  75)         mAccount = null;
d7e05192 src/com/owncloud/android/ui/fragment/FileDownloadFragment.java (David A. Velasco 2013-02-25 14:33:16 +0100  76)         mProgressListener = null;
fc2af5b0 src/com/owncloud/android/ui/fragment/FileDownloadFragment.java (David A. Velasco 2013-02-26 12:33:28 +0100  77)         mListening = false;
86cec34b src/com/owncloud/android/ui/preview/FileDownloadFragment.java  (David A. Velasco 2013-02-27 14:08:58 +0100  78)         mIgnoreFirstSavedState = false;
db7eea71 src/com/owncloud/android/ui/preview/FileDownloadFragment.java  (David A. Velasco 2013-03-01 14:20:25 +0100  79)         mError = false;
d7e05192 src/com/owncloud/android/ui/fragment/FileDownloadFragment.java (David A. Velasco 2013-02-25 14:33:16 +0100  80)     }
d7e05192 src/com/owncloud/android/ui/fragment/FileDownloadFragment.java (David A. Velasco 2013-02-25 14:33:16 +0100  81)     
d7e05192 src/com/owncloud/android/ui/fragment/FileDownloadFragment.java (David A. Velasco 2013-02-25 14:33:16 +0100  82)     
d7e05192 src/com/owncloud/android/ui/fragment/FileDownloadFragment.java (David A. Velasco 2013-02-25 14:33:16 +0100  83)     /**
d7e05192 src/com/owncloud/android/ui/fragment/FileDownloadFragment.java (David A. Velasco 2013-02-25 14:33:16 +0100  84)      * Creates a details fragment.
d7e05192 src/com/owncloud/android/ui/fragment/FileDownloadFragment.java (David A. Velasco 2013-02-25 14:33:16 +0100  85)      * 
d7e05192 src/com/owncloud/android/ui/fragment/FileDownloadFragment.java (David A. Velasco 2013-02-25 14:33:16 +0100  86)      * When 'fileToDetail' or 'ocAccount' are null, creates a dummy layout (to use when a file wasn't tapped before).
d7e05192 src/com/owncloud/android/ui/fragment/FileDownloadFragment.java (David A. Velasco 2013-02-25 14:33:16 +0100  87)      * 
d7e05192 src/com/owncloud/android/ui/fragment/FileDownloadFragment.java (David A. Velasco 2013-02-25 14:33:16 +0100  88)      * @param fileToDetail      An {@link OCFile} to show in the fragment
d7e05192 src/com/owncloud/android/ui/fragment/FileDownloadFragment.java (David A. Velasco 2013-02-25 14:33:16 +0100  89)      * @param ocAccount         An ownCloud account; needed to start downloads
86cec34b src/com/owncloud/android/ui/preview/FileDownloadFragment.java  (David A. Velasco 2013-02-27 14:08:58 +0100  90)      * @param ignoreFirstSavedState     Flag to work around an unexpected behaviour of {@link FragmentStatePagerAdapter}; TODO better solution 
d7e05192 src/com/owncloud/android/ui/fragment/FileDownloadFragment.java (David A. Velasco 2013-02-25 14:33:16 +0100  91)      */
86cec34b src/com/owncloud/android/ui/preview/FileDownloadFragment.java  (David A. Velasco 2013-02-27 14:08:58 +0100  92)     public FileDownloadFragment(OCFile fileToDetail, Account ocAccount, boolean ignoreFirstSavedState) {
fd396289 src/com/owncloud/android/ui/preview/FileDownloadFragment.java  (David A. Velasco 2013-05-30 17:53:21 +0200  93)         super(fileToDetail);
d7e05192 src/com/owncloud/android/ui/fragment/FileDownloadFragment.java (David A. Velasco 2013-02-25 14:33:16 +0100  94)         mAccount = ocAccount;
d7e05192 src/com/owncloud/android/ui/fragment/FileDownloadFragment.java (David A. Velasco 2013-02-25 14:33:16 +0100  95)         mProgressListener = null;
fc2af5b0 src/com/owncloud/android/ui/fragment/FileDownloadFragment.java (David A. Velasco 2013-02-26 12:33:28 +0100  96)         mListening = false;
86cec34b src/com/owncloud/android/ui/preview/FileDownloadFragment.java  (David A. Velasco 2013-02-27 14:08:58 +0100  97)         mIgnoreFirstSavedState = ignoreFirstSavedState;
db7eea71 src/com/owncloud/android/ui/preview/FileDownloadFragment.java  (David A. Velasco 2013-03-01 14:20:25 +0100  98)         mError = false;
d7e05192 src/com/owncloud/android/ui/fragment/FileDownloadFragment.java (David A. Velasco 2013-02-25 14:33:16 +0100  99)     }
d7e05192 src/com/owncloud/android/ui/fragment/FileDownloadFragment.java (David A. Velasco 2013-02-25 14:33:16 +0100 100)     
d7e05192 src/com/owncloud/android/ui/fragment/FileDownloadFragment.java (David A. Velasco 2013-02-25 14:33:16 +0100 101)     
d7e05192 src/com/owncloud/android/ui/fragment/FileDownloadFragment.java (David A. Velasco 2013-02-25 14:33:16 +0100 102)     @Override
d7e05192 src/com/owncloud/android/ui/fragment/FileDownloadFragment.java (David A. Velasco 2013-02-25 14:33:16 +0100 103)     public void onCreate(Bundle savedInstanceState) {
d7e05192 src/com/owncloud/android/ui/fragment/FileDownloadFragment.java (David A. Velasco 2013-02-25 14:33:16 +0100 104)         super.onCreate(savedInstanceState);
d7e05192 src/com/owncloud/android/ui/fragment/FileDownloadFragment.java (David A. Velasco 2013-02-25 14:33:16 +0100 105)     }
d7e05192 src/com/owncloud/android/ui/fragment/FileDownloadFragment.java (David A. Velasco 2013-02-25 14:33:16 +0100 106)     
d7e05192 src/com/owncloud/android/ui/fragment/FileDownloadFragment.java (David A. Velasco 2013-02-25 14:33:16 +0100 107) 
d7e05192 src/com/owncloud/android/ui/fragment/FileDownloadFragment.java (David A. Velasco 2013-02-25 14:33:16 +0100 108)     @Override
d7e05192 src/com/owncloud/android/ui/fragment/FileDownloadFragment.java (David A. Velasco 2013-02-25 14:33:16 +0100 109)     public View onCreateView(LayoutInflater inflater, ViewGroup container,
d7e05192 src/com/owncloud/android/ui/fragment/FileDownloadFragment.java (David A. Velasco 2013-02-25 14:33:16 +0100 110)             Bundle savedInstanceState) {
d7e05192 src/com/owncloud/android/ui/fragment/FileDownloadFragment.java (David A. Velasco 2013-02-25 14:33:16 +0100 111)         super.onCreateView(inflater, container, savedInstanceState);
d7e05192 src/com/owncloud/android/ui/fragment/FileDownloadFragment.java (David A. Velasco 2013-02-25 14:33:16 +0100 112)         
d7e05192 src/com/owncloud/android/ui/fragment/FileDownloadFragment.java (David A. Velasco 2013-02-25 14:33:16 +0100 113)         if (savedInstanceState != null) {
86cec34b src/com/owncloud/android/ui/preview/FileDownloadFragment.java  (David A. Velasco 2013-02-27 14:08:58 +0100 114)             if (!mIgnoreFirstSavedState) {
fd396289 src/com/owncloud/android/ui/preview/FileDownloadFragment.java  (David A. Velasco 2013-05-30 17:53:21 +0200 115)                 setFile((OCFile)savedInstanceState.getParcelable(FileDownloadFragment.EXTRA_FILE));
86cec34b src/com/owncloud/android/ui/preview/FileDownloadFragment.java  (David A. Velasco 2013-02-27 14:08:58 +0100 116)                 mAccount = savedInstanceState.getParcelable(FileDownloadFragment.EXTRA_ACCOUNT);
db7eea71 src/com/owncloud/android/ui/preview/FileDownloadFragment.java  (David A. Velasco 2013-03-01 14:20:25 +0100 117)                 mError = savedInstanceState.getBoolean(FileDownloadFragment.EXTRA_ERROR);
86cec34b src/com/owncloud/android/ui/preview/FileDownloadFragment.java  (David A. Velasco 2013-02-27 14:08:58 +0100 118)             } else {
86cec34b src/com/owncloud/android/ui/preview/FileDownloadFragment.java  (David A. Velasco 2013-02-27 14:08:58 +0100 119)                 mIgnoreFirstSavedState = false;
86cec34b src/com/owncloud/android/ui/preview/FileDownloadFragment.java  (David A. Velasco 2013-02-27 14:08:58 +0100 120)             }
d7e05192 src/com/owncloud/android/ui/fragment/FileDownloadFragment.java (David A. Velasco 2013-02-25 14:33:16 +0100 121)         }
d7e05192 src/com/owncloud/android/ui/fragment/FileDownloadFragment.java (David A. Velasco 2013-02-25 14:33:16 +0100 122)         
d7e05192 src/com/owncloud/android/ui/fragment/FileDownloadFragment.java (David A. Velasco 2013-02-25 14:33:16 +0100 123)         View view = null;
cb66b599 src/com/owncloud/android/ui/fragment/FileDownloadFragment.java (David A. Velasco 2013-02-25 15:19:18 +0100 124)         view = inflater.inflate(R.layout.file_download_fragment, container, false);
d7e05192 src/com/owncloud/android/ui/fragment/FileDownloadFragment.java (David A. Velasco 2013-02-25 14:33:16 +0100 125)         mView = view;
d7e05192 src/com/owncloud/android/ui/fragment/FileDownloadFragment.java (David A. Velasco 2013-02-25 14:33:16 +0100 126)         
fc2af5b0 src/com/owncloud/android/ui/fragment/FileDownloadFragment.java (David A. Velasco 2013-02-26 12:33:28 +0100 127)         ProgressBar progressBar = (ProgressBar)mView.findViewById(R.id.progressBar);
cb66b599 src/com/owncloud/android/ui/fragment/FileDownloadFragment.java (David A. Velasco 2013-02-25 15:19:18 +0100 128)         mProgressListener = new ProgressListener(progressBar);
d7e05192 src/com/owncloud/android/ui/fragment/FileDownloadFragment.java (David A. Velasco 2013-02-25 14:33:16 +0100 129)         
93f79c70 src/com/owncloud/android/ui/preview/FileDownloadFragment.java  (David A. Velasco 2013-06-05 11:08:33 +0200 130)         ((ImageButton)mView.findViewById(R.id.cancelBtn)).setOnClickListener(this);
25e342e9 src/com/owncloud/android/ui/preview/FileDownloadFragment.java  (David A. Velasco 2013-02-26 19:26:45 +0100 131)         
db7eea71 src/com/owncloud/android/ui/preview/FileDownloadFragment.java  (David A. Velasco 2013-03-01 14:20:25 +0100 132)         if (mError) {
db7eea71 src/com/owncloud/android/ui/preview/FileDownloadFragment.java  (David A. Velasco 2013-03-01 14:20:25 +0100 133)             setButtonsForRemote();
db7eea71 src/com/owncloud/android/ui/preview/FileDownloadFragment.java  (David A. Velasco 2013-03-01 14:20:25 +0100 134)         } else {
db7eea71 src/com/owncloud/android/ui/preview/FileDownloadFragment.java  (David A. Velasco 2013-03-01 14:20:25 +0100 135)             setButtonsForTransferring();
db7eea71 src/com/owncloud/android/ui/preview/FileDownloadFragment.java  (David A. Velasco 2013-03-01 14:20:25 +0100 136)         }
db7eea71 src/com/owncloud/android/ui/preview/FileDownloadFragment.java  (David A. Velasco 2013-03-01 14:20:25 +0100 137)         
d7e05192 src/com/owncloud/android/ui/fragment/FileDownloadFragment.java (David A. Velasco 2013-02-25 14:33:16 +0100 138)         return view;
d7e05192 src/com/owncloud/android/ui/fragment/FileDownloadFragment.java (David A. Velasco 2013-02-25 14:33:16 +0100 139)     }
d7e05192 src/com/owncloud/android/ui/fragment/FileDownloadFragment.java (David A. Velasco 2013-02-25 14:33:16 +0100 140)     
d7e05192 src/com/owncloud/android/ui/fragment/FileDownloadFragment.java (David A. Velasco 2013-02-25 14:33:16 +0100 141) 
d7e05192 src/com/owncloud/android/ui/fragment/FileDownloadFragment.java (David A. Velasco 2013-02-25 14:33:16 +0100 142)     /**
d7e05192 src/com/owncloud/android/ui/fragment/FileDownloadFragment.java (David A. Velasco 2013-02-25 14:33:16 +0100 143)      * {@inheritDoc}
d7e05192 src/com/owncloud/android/ui/fragment/FileDownloadFragment.java (David A. Velasco 2013-02-25 14:33:16 +0100 144)      */
d7e05192 src/com/owncloud/android/ui/fragment/FileDownloadFragment.java (David A. Velasco 2013-02-25 14:33:16 +0100 145)     @Override
d7e05192 src/com/owncloud/android/ui/fragment/FileDownloadFragment.java (David A. Velasco 2013-02-25 14:33:16 +0100 146)     public void onAttach(Activity activity) {
d7e05192 src/com/owncloud/android/ui/fragment/FileDownloadFragment.java (David A. Velasco 2013-02-25 14:33:16 +0100 147)         super.onAttach(activity);
d7e05192 src/com/owncloud/android/ui/fragment/FileDownloadFragment.java (David A. Velasco 2013-02-25 14:33:16 +0100 148)         try {
d7e05192 src/com/owncloud/android/ui/fragment/FileDownloadFragment.java (David A. Velasco 2013-02-25 14:33:16 +0100 149)             mContainerActivity = (ContainerActivity) activity;
d7e05192 src/com/owncloud/android/ui/fragment/FileDownloadFragment.java (David A. Velasco 2013-02-25 14:33:16 +0100 150)             
d7e05192 src/com/owncloud/android/ui/fragment/FileDownloadFragment.java (David A. Velasco 2013-02-25 14:33:16 +0100 151)         } catch (ClassCastException e) {
cb66b599 src/com/owncloud/android/ui/fragment/FileDownloadFragment.java (David A. Velasco 2013-02-25 15:19:18 +0100 152)             throw new ClassCastException(activity.toString() + " must implement " + FileFragment.ContainerActivity.class.getSimpleName());
d7e05192 src/com/owncloud/android/ui/fragment/FileDownloadFragment.java (David A. Velasco 2013-02-25 14:33:16 +0100 153)         }
d7e05192 src/com/owncloud/android/ui/fragment/FileDownloadFragment.java (David A. Velasco 2013-02-25 14:33:16 +0100 154)     }
d7e05192 src/com/owncloud/android/ui/fragment/FileDownloadFragment.java (David A. Velasco 2013-02-25 14:33:16 +0100 155)     
d7e05192 src/com/owncloud/android/ui/fragment/FileDownloadFragment.java (David A. Velasco 2013-02-25 14:33:16 +0100 156)     
d7e05192 src/com/owncloud/android/ui/fragment/FileDownloadFragment.java (David A. Velasco 2013-02-25 14:33:16 +0100 157)     /**
d7e05192 src/com/owncloud/android/ui/fragment/FileDownloadFragment.java (David A. Velasco 2013-02-25 14:33:16 +0100 158)      * {@inheritDoc}
d7e05192 src/com/owncloud/android/ui/fragment/FileDownloadFragment.java (David A. Velasco 2013-02-25 14:33:16 +0100 159)      */
d7e05192 src/com/owncloud/android/ui/fragment/FileDownloadFragment.java (David A. Velasco 2013-02-25 14:33:16 +0100 160)     @Override
d7e05192 src/com/owncloud/android/ui/fragment/FileDownloadFragment.java (David A. Velasco 2013-02-25 14:33:16 +0100 161)     public void onActivityCreated(Bundle savedInstanceState) {
d7e05192 src/com/owncloud/android/ui/fragment/FileDownloadFragment.java (David A. Velasco 2013-02-25 14:33:16 +0100 162)         super.onActivityCreated(savedInstanceState);
d7e05192 src/com/owncloud/android/ui/fragment/FileDownloadFragment.java (David A. Velasco 2013-02-25 14:33:16 +0100 163)         if (mAccount != null) {
fd396289 src/com/owncloud/android/ui/preview/FileDownloadFragment.java  (David A. Velasco 2013-05-30 17:53:21 +0200 164)             //mStorageManager = new FileDataStorageManager(mAccount, getActivity().getApplicationContext().getContentResolver());;
d7e05192 src/com/owncloud/android/ui/fragment/FileDownloadFragment.java (David A. Velasco 2013-02-25 14:33:16 +0100 165)         }
d7e05192 src/com/owncloud/android/ui/fragment/FileDownloadFragment.java (David A. Velasco 2013-02-25 14:33:16 +0100 166)     }
d7e05192 src/com/owncloud/android/ui/fragment/FileDownloadFragment.java (David A. Velasco 2013-02-25 14:33:16 +0100 167)         
d7e05192 src/com/owncloud/android/ui/fragment/FileDownloadFragment.java (David A. Velasco 2013-02-25 14:33:16 +0100 168) 
d7e05192 src/com/owncloud/android/ui/fragment/FileDownloadFragment.java (David A. Velasco 2013-02-25 14:33:16 +0100 169)     @Override
d7e05192 src/com/owncloud/android/ui/fragment/FileDownloadFragment.java (David A. Velasco 2013-02-25 14:33:16 +0100 170)     public void onSaveInstanceState(Bundle outState) {
d7e05192 src/com/owncloud/android/ui/fragment/FileDownloadFragment.java (David A. Velasco 2013-02-25 14:33:16 +0100 171)         super.onSaveInstanceState(outState);
fd396289 src/com/owncloud/android/ui/preview/FileDownloadFragment.java  (David A. Velasco 2013-05-30 17:53:21 +0200 172)         outState.putParcelable(FileDownloadFragment.EXTRA_FILE, getFile());
d7e05192 src/com/owncloud/android/ui/fragment/FileDownloadFragment.java (David A. Velasco 2013-02-25 14:33:16 +0100 173)         outState.putParcelable(FileDownloadFragment.EXTRA_ACCOUNT, mAccount);
db7eea71 src/com/owncloud/android/ui/preview/FileDownloadFragment.java  (David A. Velasco 2013-03-01 14:20:25 +0100 174)         outState.putBoolean(FileDownloadFragment.EXTRA_ERROR, mError);
d7e05192 src/com/owncloud/android/ui/fragment/FileDownloadFragment.java (David A. Velasco 2013-02-25 14:33:16 +0100 175)     }
d7e05192 src/com/owncloud/android/ui/fragment/FileDownloadFragment.java (David A. Velasco 2013-02-25 14:33:16 +0100 176) 
d7e05192 src/com/owncloud/android/ui/fragment/FileDownloadFragment.java (David A. Velasco 2013-02-25 14:33:16 +0100 177)     @Override
d7e05192 src/com/owncloud/android/ui/fragment/FileDownloadFragment.java (David A. Velasco 2013-02-25 14:33:16 +0100 178)     public void onStart() {
d7e05192 src/com/owncloud/android/ui/fragment/FileDownloadFragment.java (David A. Velasco 2013-02-25 14:33:16 +0100 179)         super.onStart();
d7e05192 src/com/owncloud/android/ui/fragment/FileDownloadFragment.java (David A. Velasco 2013-02-25 14:33:16 +0100 180)         listenForTransferProgress();
d7e05192 src/com/owncloud/android/ui/fragment/FileDownloadFragment.java (David A. Velasco 2013-02-25 14:33:16 +0100 181)     }
d7e05192 src/com/owncloud/android/ui/fragment/FileDownloadFragment.java (David A. Velasco 2013-02-25 14:33:16 +0100 182)     
d7e05192 src/com/owncloud/android/ui/fragment/FileDownloadFragment.java (David A. Velasco 2013-02-25 14:33:16 +0100 183)     @Override
d7e05192 src/com/owncloud/android/ui/fragment/FileDownloadFragment.java (David A. Velasco 2013-02-25 14:33:16 +0100 184)     public void onResume() {
d7e05192 src/com/owncloud/android/ui/fragment/FileDownloadFragment.java (David A. Velasco 2013-02-25 14:33:16 +0100 185)         super.onResume();
d7e05192 src/com/owncloud/android/ui/fragment/FileDownloadFragment.java (David A. Velasco 2013-02-25 14:33:16 +0100 186)     }
d7e05192 src/com/owncloud/android/ui/fragment/FileDownloadFragment.java (David A. Velasco 2013-02-25 14:33:16 +0100 187) 
d7e05192 src/com/owncloud/android/ui/fragment/FileDownloadFragment.java (David A. Velasco 2013-02-25 14:33:16 +0100 188) 
d7e05192 src/com/owncloud/android/ui/fragment/FileDownloadFragment.java (David A. Velasco 2013-02-25 14:33:16 +0100 189)     @Override
d7e05192 src/com/owncloud/android/ui/fragment/FileDownloadFragment.java (David A. Velasco 2013-02-25 14:33:16 +0100 190)     public void onPause() {
d7e05192 src/com/owncloud/android/ui/fragment/FileDownloadFragment.java (David A. Velasco 2013-02-25 14:33:16 +0100 191)         super.onPause();
d7e05192 src/com/owncloud/android/ui/fragment/FileDownloadFragment.java (David A. Velasco 2013-02-25 14:33:16 +0100 192)     }
d7e05192 src/com/owncloud/android/ui/fragment/FileDownloadFragment.java (David A. Velasco 2013-02-25 14:33:16 +0100 193) 
d7e05192 src/com/owncloud/android/ui/fragment/FileDownloadFragment.java (David A. Velasco 2013-02-25 14:33:16 +0100 194)     
d7e05192 src/com/owncloud/android/ui/fragment/FileDownloadFragment.java (David A. Velasco 2013-02-25 14:33:16 +0100 195)     @Override
d7e05192 src/com/owncloud/android/ui/fragment/FileDownloadFragment.java (David A. Velasco 2013-02-25 14:33:16 +0100 196)     public void onStop() {
d7e05192 src/com/owncloud/android/ui/fragment/FileDownloadFragment.java (David A. Velasco 2013-02-25 14:33:16 +0100 197)         super.onStop();
d7e05192 src/com/owncloud/android/ui/fragment/FileDownloadFragment.java (David A. Velasco 2013-02-25 14:33:16 +0100 198)         leaveTransferProgress();
d7e05192 src/com/owncloud/android/ui/fragment/FileDownloadFragment.java (David A. Velasco 2013-02-25 14:33:16 +0100 199)     }
d7e05192 src/com/owncloud/android/ui/fragment/FileDownloadFragment.java (David A. Velasco 2013-02-25 14:33:16 +0100 200)     
d7e05192 src/com/owncloud/android/ui/fragment/FileDownloadFragment.java (David A. Velasco 2013-02-25 14:33:16 +0100 201)     @Override
d7e05192 src/com/owncloud/android/ui/fragment/FileDownloadFragment.java (David A. Velasco 2013-02-25 14:33:16 +0100 202)     public void onDestroy() {
d7e05192 src/com/owncloud/android/ui/fragment/FileDownloadFragment.java (David A. Velasco 2013-02-25 14:33:16 +0100 203)         super.onDestroy();
d7e05192 src/com/owncloud/android/ui/fragment/FileDownloadFragment.java (David A. Velasco 2013-02-25 14:33:16 +0100 204)     }
d7e05192 src/com/owncloud/android/ui/fragment/FileDownloadFragment.java (David A. Velasco 2013-02-25 14:33:16 +0100 205)     
d7e05192 src/com/owncloud/android/ui/fragment/FileDownloadFragment.java (David A. Velasco 2013-02-25 14:33:16 +0100 206)     
d7e05192 src/com/owncloud/android/ui/fragment/FileDownloadFragment.java (David A. Velasco 2013-02-25 14:33:16 +0100 207)     @Override
d7e05192 src/com/owncloud/android/ui/fragment/FileDownloadFragment.java (David A. Velasco 2013-02-25 14:33:16 +0100 208)     public View getView() {
fc2af5b0 src/com/owncloud/android/ui/fragment/FileDownloadFragment.java (David A. Velasco 2013-02-26 12:33:28 +0100 209)         if (!mListening) {
fc2af5b0 src/com/owncloud/android/ui/fragment/FileDownloadFragment.java (David A. Velasco 2013-02-26 12:33:28 +0100 210)             listenForTransferProgress();
fc2af5b0 src/com/owncloud/android/ui/fragment/FileDownloadFragment.java (David A. Velasco 2013-02-26 12:33:28 +0100 211)         }
d7e05192 src/com/owncloud/android/ui/fragment/FileDownloadFragment.java (David A. Velasco 2013-02-25 14:33:16 +0100 212)         return super.getView() == null ? mView : super.getView();
d7e05192 src/com/owncloud/android/ui/fragment/FileDownloadFragment.java (David A. Velasco 2013-02-25 14:33:16 +0100 213)     }
d7e05192 src/com/owncloud/android/ui/fragment/FileDownloadFragment.java (David A. Velasco 2013-02-25 14:33:16 +0100 214) 
d7e05192 src/com/owncloud/android/ui/fragment/FileDownloadFragment.java (David A. Velasco 2013-02-25 14:33:16 +0100 215)     
d7e05192 src/com/owncloud/android/ui/fragment/FileDownloadFragment.java (David A. Velasco 2013-02-25 14:33:16 +0100 216)     @Override
d7e05192 src/com/owncloud/android/ui/fragment/FileDownloadFragment.java (David A. Velasco 2013-02-25 14:33:16 +0100 217)     public void onClick(View v) {
d7e05192 src/com/owncloud/android/ui/fragment/FileDownloadFragment.java (David A. Velasco 2013-02-25 14:33:16 +0100 218)         switch (v.getId()) {
cb66b599 src/com/owncloud/android/ui/fragment/FileDownloadFragment.java (David A. Velasco 2013-02-25 15:19:18 +0100 219)             case R.id.cancelBtn: {
d7e05192 src/com/owncloud/android/ui/fragment/FileDownloadFragment.java (David A. Velasco 2013-02-25 14:33:16 +0100 220)                 FileDownloaderBinder downloaderBinder = mContainerActivity.getFileDownloaderBinder();
fd396289 src/com/owncloud/android/ui/preview/FileDownloadFragment.java  (David A. Velasco 2013-05-30 17:53:21 +0200 221)                 if (downloaderBinder != null && downloaderBinder.isDownloading(mAccount, getFile())) {
fd396289 src/com/owncloud/android/ui/preview/FileDownloadFragment.java  (David A. Velasco 2013-05-30 17:53:21 +0200 222)                     downloaderBinder.cancel(mAccount, getFile());
75ae57b6 src/com/owncloud/android/ui/preview/FileDownloadFragment.java  (David A. Velasco 2013-02-27 16:30:29 +0100 223)                     getActivity().finish(); // :)
75ae57b6 src/com/owncloud/android/ui/preview/FileDownloadFragment.java  (David A. Velasco 2013-02-27 16:30:29 +0100 224)                     /*
25e342e9 src/com/owncloud/android/ui/preview/FileDownloadFragment.java  (David A. Velasco 2013-02-26 19:26:45 +0100 225)                     leaveTransferProgress();
d7e05192 src/com/owncloud/android/ui/fragment/FileDownloadFragment.java (David A. Velasco 2013-02-25 14:33:16 +0100 226)                     if (mFile.isDown()) {
d7e05192 src/com/owncloud/android/ui/fragment/FileDownloadFragment.java (David A. Velasco 2013-02-25 14:33:16 +0100 227)                         setButtonsForDown();
d7e05192 src/com/owncloud/android/ui/fragment/FileDownloadFragment.java (David A. Velasco 2013-02-25 14:33:16 +0100 228)                     } else {
d7e05192 src/com/owncloud/android/ui/fragment/FileDownloadFragment.java (David A. Velasco 2013-02-25 14:33:16 +0100 229)                         setButtonsForRemote();
d7e05192 src/com/owncloud/android/ui/fragment/FileDownloadFragment.java (David A. Velasco 2013-02-25 14:33:16 +0100 230)                     }
75ae57b6 src/com/owncloud/android/ui/preview/FileDownloadFragment.java  (David A. Velasco 2013-02-27 16:30:29 +0100 231)                     */
d7e05192 src/com/owncloud/android/ui/fragment/FileDownloadFragment.java (David A. Velasco 2013-02-25 14:33:16 +0100 232)                 }
d7e05192 src/com/owncloud/android/ui/fragment/FileDownloadFragment.java (David A. Velasco 2013-02-25 14:33:16 +0100 233)                 break;
d7e05192 src/com/owncloud/android/ui/fragment/FileDownloadFragment.java (David A. Velasco 2013-02-25 14:33:16 +0100 234)             }
d7e05192 src/com/owncloud/android/ui/fragment/FileDownloadFragment.java (David A. Velasco 2013-02-25 14:33:16 +0100 235)             default:
2946d8dd src/com/owncloud/android/ui/preview/FileDownloadFragment.java  (David A. Velasco 2013-04-25 19:39:22 +0200 236)                 Log_OC.e(TAG, "Incorrect view clicked!");
d7e05192 src/com/owncloud/android/ui/fragment/FileDownloadFragment.java (David A. Velasco 2013-02-25 14:33:16 +0100 237)         }
d7e05192 src/com/owncloud/android/ui/fragment/FileDownloadFragment.java (David A. Velasco 2013-02-25 14:33:16 +0100 238)     }
d7e05192 src/com/owncloud/android/ui/fragment/FileDownloadFragment.java (David A. Velasco 2013-02-25 14:33:16 +0100 239) 
d7e05192 src/com/owncloud/android/ui/fragment/FileDownloadFragment.java (David A. Velasco 2013-02-25 14:33:16 +0100 240)     
d7e05192 src/com/owncloud/android/ui/fragment/FileDownloadFragment.java (David A. Velasco 2013-02-25 14:33:16 +0100 241)     /**
cb66b599 src/com/owncloud/android/ui/fragment/FileDownloadFragment.java (David A. Velasco 2013-02-25 15:19:18 +0100 242)      * Updates the view depending upon the state of the downloading file.
fc2af5b0 src/com/owncloud/android/ui/fragment/FileDownloadFragment.java (David A. Velasco 2013-02-26 12:33:28 +0100 243)      * 
fc2af5b0 src/com/owncloud/android/ui/fragment/FileDownloadFragment.java (David A. Velasco 2013-02-26 12:33:28 +0100 244)      * @param   transferring    When true, the view must be updated assuming that the holded file is 
fc2af5b0 src/com/owncloud/android/ui/fragment/FileDownloadFragment.java (David A. Velasco 2013-02-26 12:33:28 +0100 245)      *                          downloading, no matter what the downloaderBinder says.
d7e05192 src/com/owncloud/android/ui/fragment/FileDownloadFragment.java (David A. Velasco 2013-02-25 14:33:16 +0100 246)      */
fc2af5b0 src/com/owncloud/android/ui/fragment/FileDownloadFragment.java (David A. Velasco 2013-02-26 12:33:28 +0100 247)     public void updateView(boolean transferring) {
cb66b599 src/com/owncloud/android/ui/fragment/FileDownloadFragment.java (David A. Velasco 2013-02-25 15:19:18 +0100 248)         // configure UI for depending upon local state of the file
db7eea71 src/com/owncloud/android/ui/preview/FileDownloadFragment.java  (David A. Velasco 2013-03-01 14:20:25 +0100 249)         FileDownloaderBinder downloaderBinder = (mContainerActivity == null) ? null : mContainerActivity.getFileDownloaderBinder();
fd396289 src/com/owncloud/android/ui/preview/FileDownloadFragment.java  (David A. Velasco 2013-05-30 17:53:21 +0200 250)         if (transferring || (downloaderBinder != null && downloaderBinder.isDownloading(mAccount, getFile()))) {
cb66b599 src/com/owncloud/android/ui/fragment/FileDownloadFragment.java (David A. Velasco 2013-02-25 15:19:18 +0100 251)             setButtonsForTransferring();
d7e05192 src/com/owncloud/android/ui/fragment/FileDownloadFragment.java (David A. Velasco 2013-02-25 14:33:16 +0100 252)             
fd396289 src/com/owncloud/android/ui/preview/FileDownloadFragment.java  (David A. Velasco 2013-05-30 17:53:21 +0200 253)         } else if (getFile().isDown()) {
d7e05192 src/com/owncloud/android/ui/fragment/FileDownloadFragment.java (David A. Velasco 2013-02-25 14:33:16 +0100 254)             
cb66b599 src/com/owncloud/android/ui/fragment/FileDownloadFragment.java (David A. Velasco 2013-02-25 15:19:18 +0100 255)             setButtonsForDown();
cb66b599 src/com/owncloud/android/ui/fragment/FileDownloadFragment.java (David A. Velasco 2013-02-25 15:19:18 +0100 256)             
cb66b599 src/com/owncloud/android/ui/fragment/FileDownloadFragment.java (David A. Velasco 2013-02-25 15:19:18 +0100 257)         } else {
cb66b599 src/com/owncloud/android/ui/fragment/FileDownloadFragment.java (David A. Velasco 2013-02-25 15:19:18 +0100 258)             setButtonsForRemote();
d7e05192 src/com/owncloud/android/ui/fragment/FileDownloadFragment.java (David A. Velasco 2013-02-25 14:33:16 +0100 259)         }
d7e05192 src/com/owncloud/android/ui/fragment/FileDownloadFragment.java (David A. Velasco 2013-02-25 14:33:16 +0100 260)         getView().invalidate();
db7eea71 src/com/owncloud/android/ui/preview/FileDownloadFragment.java  (David A. Velasco 2013-03-01 14:20:25 +0100 261)         
d7e05192 src/com/owncloud/android/ui/fragment/FileDownloadFragment.java (David A. Velasco 2013-02-25 14:33:16 +0100 262)     }
d7e05192 src/com/owncloud/android/ui/fragment/FileDownloadFragment.java (David A. Velasco 2013-02-25 14:33:16 +0100 263) 
d7e05192 src/com/owncloud/android/ui/fragment/FileDownloadFragment.java (David A. Velasco 2013-02-25 14:33:16 +0100 264) 
d7e05192 src/com/owncloud/android/ui/fragment/FileDownloadFragment.java (David A. Velasco 2013-02-25 14:33:16 +0100 265)     /**
d7e05192 src/com/owncloud/android/ui/fragment/FileDownloadFragment.java (David A. Velasco 2013-02-25 14:33:16 +0100 266)      * Enables or disables buttons for a file being downloaded
d7e05192 src/com/owncloud/android/ui/fragment/FileDownloadFragment.java (David A. Velasco 2013-02-25 14:33:16 +0100 267)      */
d7e05192 src/com/owncloud/android/ui/fragment/FileDownloadFragment.java (David A. Velasco 2013-02-25 14:33:16 +0100 268)     private void setButtonsForTransferring() {
db7eea71 src/com/owncloud/android/ui/preview/FileDownloadFragment.java  (David A. Velasco 2013-03-01 14:20:25 +0100 269)         getView().findViewById(R.id.cancelBtn).setVisibility(View.VISIBLE);
cb66b599 src/com/owncloud/android/ui/fragment/FileDownloadFragment.java (David A. Velasco 2013-02-25 15:19:18 +0100 270)     
cb66b599 src/com/owncloud/android/ui/fragment/FileDownloadFragment.java (David A. Velasco 2013-02-25 15:19:18 +0100 271)         // show the progress bar for the transfer
db7eea71 src/com/owncloud/android/ui/preview/FileDownloadFragment.java  (David A. Velasco 2013-03-01 14:20:25 +0100 272)         getView().findViewById(R.id.progressBar).setVisibility(View.VISIBLE);
fc2af5b0 src/com/owncloud/android/ui/fragment/FileDownloadFragment.java (David A. Velasco 2013-02-26 12:33:28 +0100 273)         TextView progressText = (TextView)getView().findViewById(R.id.progressText);
cb66b599 src/com/owncloud/android/ui/fragment/FileDownloadFragment.java (David A. Velasco 2013-02-25 15:19:18 +0100 274)         progressText.setText(R.string.downloader_download_in_progress_ticker);
db7eea71 src/com/owncloud/android/ui/preview/FileDownloadFragment.java  (David A. Velasco 2013-03-01 14:20:25 +0100 275)         progressText.setVisibility(View.VISIBLE);
db7eea71 src/com/owncloud/android/ui/preview/FileDownloadFragment.java  (David A. Velasco 2013-03-01 14:20:25 +0100 276)                 
db7eea71 src/com/owncloud/android/ui/preview/FileDownloadFragment.java  (David A. Velasco 2013-03-01 14:20:25 +0100 277)         // hides the error icon
db7eea71 src/com/owncloud/android/ui/preview/FileDownloadFragment.java  (David A. Velasco 2013-03-01 14:20:25 +0100 278)         getView().findViewById(R.id.errorText).setVisibility(View.GONE);
db7eea71 src/com/owncloud/android/ui/preview/FileDownloadFragment.java  (David A. Velasco 2013-03-01 14:20:25 +0100 279)         getView().findViewById(R.id.error_image).setVisibility(View.GONE);
d7e05192 src/com/owncloud/android/ui/fragment/FileDownloadFragment.java (David A. Velasco 2013-02-25 14:33:16 +0100 280)     }
d7e05192 src/com/owncloud/android/ui/fragment/FileDownloadFragment.java (David A. Velasco 2013-02-25 14:33:16 +0100 281)     
d7e05192 src/com/owncloud/android/ui/fragment/FileDownloadFragment.java (David A. Velasco 2013-02-25 14:33:16 +0100 282) 
d7e05192 src/com/owncloud/android/ui/fragment/FileDownloadFragment.java (David A. Velasco 2013-02-25 14:33:16 +0100 283)     /**
d7e05192 src/com/owncloud/android/ui/fragment/FileDownloadFragment.java (David A. Velasco 2013-02-25 14:33:16 +0100 284)      * Enables or disables buttons for a file locally available 
d7e05192 src/com/owncloud/android/ui/fragment/FileDownloadFragment.java (David A. Velasco 2013-02-25 14:33:16 +0100 285)      */
d7e05192 src/com/owncloud/android/ui/fragment/FileDownloadFragment.java (David A. Velasco 2013-02-25 14:33:16 +0100 286)     private void setButtonsForDown() {
db7eea71 src/com/owncloud/android/ui/preview/FileDownloadFragment.java  (David A. Velasco 2013-03-01 14:20:25 +0100 287)         getView().findViewById(R.id.cancelBtn).setVisibility(View.GONE);
cb66b599 src/com/owncloud/android/ui/fragment/FileDownloadFragment.java (David A. Velasco 2013-02-25 15:19:18 +0100 288)     
cb66b599 src/com/owncloud/android/ui/fragment/FileDownloadFragment.java (David A. Velasco 2013-02-25 15:19:18 +0100 289)         // hides the progress bar
db7eea71 src/com/owncloud/android/ui/preview/FileDownloadFragment.java  (David A. Velasco 2013-03-01 14:20:25 +0100 290)         getView().findViewById(R.id.progressBar).setVisibility(View.GONE);
d7e05192 src/com/owncloud/android/ui/fragment/FileDownloadFragment.java (David A. Velasco 2013-02-25 14:33:16 +0100 291)         
cb66b599 src/com/owncloud/android/ui/fragment/FileDownloadFragment.java (David A. Velasco 2013-02-25 15:19:18 +0100 292)         // updates the text message
fc2af5b0 src/com/owncloud/android/ui/fragment/FileDownloadFragment.java (David A. Velasco 2013-02-26 12:33:28 +0100 293)         TextView progressText = (TextView)getView().findViewById(R.id.progressText);
cb66b599 src/com/owncloud/android/ui/fragment/FileDownloadFragment.java (David A. Velasco 2013-02-25 15:19:18 +0100 294)         progressText.setText(R.string.common_loading);
db7eea71 src/com/owncloud/android/ui/preview/FileDownloadFragment.java  (David A. Velasco 2013-03-01 14:20:25 +0100 295)         progressText.setVisibility(View.VISIBLE);
db7eea71 src/com/owncloud/android/ui/preview/FileDownloadFragment.java  (David A. Velasco 2013-03-01 14:20:25 +0100 296)         
db7eea71 src/com/owncloud/android/ui/preview/FileDownloadFragment.java  (David A. Velasco 2013-03-01 14:20:25 +0100 297)         // hides the error icon
db7eea71 src/com/owncloud/android/ui/preview/FileDownloadFragment.java  (David A. Velasco 2013-03-01 14:20:25 +0100 298)         getView().findViewById(R.id.errorText).setVisibility(View.GONE);
db7eea71 src/com/owncloud/android/ui/preview/FileDownloadFragment.java  (David A. Velasco 2013-03-01 14:20:25 +0100 299)         getView().findViewById(R.id.error_image).setVisibility(View.GONE);
d7e05192 src/com/owncloud/android/ui/fragment/FileDownloadFragment.java (David A. Velasco 2013-02-25 14:33:16 +0100 300)     }
d7e05192 src/com/owncloud/android/ui/fragment/FileDownloadFragment.java (David A. Velasco 2013-02-25 14:33:16 +0100 301) 
cb66b599 src/com/owncloud/android/ui/fragment/FileDownloadFragment.java (David A. Velasco 2013-02-25 15:19:18 +0100 302)     
d7e05192 src/com/owncloud/android/ui/fragment/FileDownloadFragment.java (David A. Velasco 2013-02-25 14:33:16 +0100 303)     /**
d7e05192 src/com/owncloud/android/ui/fragment/FileDownloadFragment.java (David A. Velasco 2013-02-25 14:33:16 +0100 304)      * Enables or disables buttons for a file not locally available 
db7eea71 src/com/owncloud/android/ui/preview/FileDownloadFragment.java  (David A. Velasco 2013-03-01 14:20:25 +0100 305)      * 
db7eea71 src/com/owncloud/android/ui/preview/FileDownloadFragment.java  (David A. Velasco 2013-03-01 14:20:25 +0100 306)      * Currently, this is only used when a download was failed
d7e05192 src/com/owncloud/android/ui/fragment/FileDownloadFragment.java (David A. Velasco 2013-02-25 14:33:16 +0100 307)      */
d7e05192 src/com/owncloud/android/ui/fragment/FileDownloadFragment.java (David A. Velasco 2013-02-25 14:33:16 +0100 308)     private void setButtonsForRemote() {
db7eea71 src/com/owncloud/android/ui/preview/FileDownloadFragment.java  (David A. Velasco 2013-03-01 14:20:25 +0100 309)         getView().findViewById(R.id.cancelBtn).setVisibility(View.GONE);
cb66b599 src/com/owncloud/android/ui/fragment/FileDownloadFragment.java (David A. Velasco 2013-02-25 15:19:18 +0100 310)         
db7eea71 src/com/owncloud/android/ui/preview/FileDownloadFragment.java  (David A. Velasco 2013-03-01 14:20:25 +0100 311)         // hides the progress bar and message
db7eea71 src/com/owncloud/android/ui/preview/FileDownloadFragment.java  (David A. Velasco 2013-03-01 14:20:25 +0100 312)         getView().findViewById(R.id.progressBar).setVisibility(View.GONE);
db7eea71 src/com/owncloud/android/ui/preview/FileDownloadFragment.java  (David A. Velasco 2013-03-01 14:20:25 +0100 313)         getView().findViewById(R.id.progressText).setVisibility(View.GONE);
db7eea71 src/com/owncloud/android/ui/preview/FileDownloadFragment.java  (David A. Velasco 2013-03-01 14:20:25 +0100 314) 
db7eea71 src/com/owncloud/android/ui/preview/FileDownloadFragment.java  (David A. Velasco 2013-03-01 14:20:25 +0100 315)         // shows the error icon and message
db7eea71 src/com/owncloud/android/ui/preview/FileDownloadFragment.java  (David A. Velasco 2013-03-01 14:20:25 +0100 316)         getView().findViewById(R.id.errorText).setVisibility(View.VISIBLE);
db7eea71 src/com/owncloud/android/ui/preview/FileDownloadFragment.java  (David A. Velasco 2013-03-01 14:20:25 +0100 317)         getView().findViewById(R.id.error_image).setVisibility(View.VISIBLE);
d7e05192 src/com/owncloud/android/ui/fragment/FileDownloadFragment.java (David A. Velasco 2013-02-25 14:33:16 +0100 318)     }
d7e05192 src/com/owncloud/android/ui/fragment/FileDownloadFragment.java (David A. Velasco 2013-02-25 14:33:16 +0100 319)     
d7e05192 src/com/owncloud/android/ui/fragment/FileDownloadFragment.java (David A. Velasco 2013-02-25 14:33:16 +0100 320) 
d7e05192 src/com/owncloud/android/ui/fragment/FileDownloadFragment.java (David A. Velasco 2013-02-25 14:33:16 +0100 321)     public void listenForTransferProgress() {
fc2af5b0 src/com/owncloud/android/ui/fragment/FileDownloadFragment.java (David A. Velasco 2013-02-26 12:33:28 +0100 322)         if (mProgressListener != null && !mListening) {
d7e05192 src/com/owncloud/android/ui/fragment/FileDownloadFragment.java (David A. Velasco 2013-02-25 14:33:16 +0100 323)             if (mContainerActivity.getFileDownloaderBinder() != null) {
fd396289 src/com/owncloud/android/ui/preview/FileDownloadFragment.java  (David A. Velasco 2013-05-30 17:53:21 +0200 324)                 mContainerActivity.getFileDownloaderBinder().addDatatransferProgressListener(mProgressListener, mAccount, getFile());
fc2af5b0 src/com/owncloud/android/ui/fragment/FileDownloadFragment.java (David A. Velasco 2013-02-26 12:33:28 +0100 325)                 mListening = true;
fc2af5b0 src/com/owncloud/android/ui/fragment/FileDownloadFragment.java (David A. Velasco 2013-02-26 12:33:28 +0100 326)                 setButtonsForTransferring();
d7e05192 src/com/owncloud/android/ui/fragment/FileDownloadFragment.java (David A. Velasco 2013-02-25 14:33:16 +0100 327)             }
d7e05192 src/com/owncloud/android/ui/fragment/FileDownloadFragment.java (David A. Velasco 2013-02-25 14:33:16 +0100 328)         }
d7e05192 src/com/owncloud/android/ui/fragment/FileDownloadFragment.java (David A. Velasco 2013-02-25 14:33:16 +0100 329)     }
d7e05192 src/com/owncloud/android/ui/fragment/FileDownloadFragment.java (David A. Velasco 2013-02-25 14:33:16 +0100 330)     
d7e05192 src/com/owncloud/android/ui/fragment/FileDownloadFragment.java (David A. Velasco 2013-02-25 14:33:16 +0100 331)     
d7e05192 src/com/owncloud/android/ui/fragment/FileDownloadFragment.java (David A. Velasco 2013-02-25 14:33:16 +0100 332)     public void leaveTransferProgress() {
d7e05192 src/com/owncloud/android/ui/fragment/FileDownloadFragment.java (David A. Velasco 2013-02-25 14:33:16 +0100 333)         if (mProgressListener != null) {
d7e05192 src/com/owncloud/android/ui/fragment/FileDownloadFragment.java (David A. Velasco 2013-02-25 14:33:16 +0100 334)             if (mContainerActivity.getFileDownloaderBinder() != null) {
fd396289 src/com/owncloud/android/ui/preview/FileDownloadFragment.java  (David A. Velasco 2013-05-30 17:53:21 +0200 335)                 mContainerActivity.getFileDownloaderBinder().removeDatatransferProgressListener(mProgressListener, mAccount, getFile());
3d989ad0 src/com/owncloud/android/ui/preview/FileDownloadFragment.java  (David A. Velasco 2013-02-27 15:50:52 +0100 336)                 mListening = false;
d7e05192 src/com/owncloud/android/ui/fragment/FileDownloadFragment.java (David A. Velasco 2013-02-25 14:33:16 +0100 337)             }
d7e05192 src/com/owncloud/android/ui/fragment/FileDownloadFragment.java (David A. Velasco 2013-02-25 14:33:16 +0100 338)         }
d7e05192 src/com/owncloud/android/ui/fragment/FileDownloadFragment.java (David A. Velasco 2013-02-25 14:33:16 +0100 339)     }
d7e05192 src/com/owncloud/android/ui/fragment/FileDownloadFragment.java (David A. Velasco 2013-02-25 14:33:16 +0100 340) 
d7e05192 src/com/owncloud/android/ui/fragment/FileDownloadFragment.java (David A. Velasco 2013-02-25 14:33:16 +0100 341)     
d7e05192 src/com/owncloud/android/ui/fragment/FileDownloadFragment.java (David A. Velasco 2013-02-25 14:33:16 +0100 342)     /**
d7e05192 src/com/owncloud/android/ui/fragment/FileDownloadFragment.java (David A. Velasco 2013-02-25 14:33:16 +0100 343)      * Helper class responsible for updating the progress bar shown for file uploading or downloading  
d7e05192 src/com/owncloud/android/ui/fragment/FileDownloadFragment.java (David A. Velasco 2013-02-25 14:33:16 +0100 344)      * 
d7e05192 src/com/owncloud/android/ui/fragment/FileDownloadFragment.java (David A. Velasco 2013-02-25 14:33:16 +0100 345)      * @author David A. Velasco
d7e05192 src/com/owncloud/android/ui/fragment/FileDownloadFragment.java (David A. Velasco 2013-02-25 14:33:16 +0100 346)      */
d7e05192 src/com/owncloud/android/ui/fragment/FileDownloadFragment.java (David A. Velasco 2013-02-25 14:33:16 +0100 347)     private class ProgressListener implements OnDatatransferProgressListener {
d7e05192 src/com/owncloud/android/ui/fragment/FileDownloadFragment.java (David A. Velasco 2013-02-25 14:33:16 +0100 348)         int mLastPercent = 0;
d7e05192 src/com/owncloud/android/ui/fragment/FileDownloadFragment.java (David A. Velasco 2013-02-25 14:33:16 +0100 349)         WeakReference<ProgressBar> mProgressBar = null;
d7e05192 src/com/owncloud/android/ui/fragment/FileDownloadFragment.java (David A. Velasco 2013-02-25 14:33:16 +0100 350)         
d7e05192 src/com/owncloud/android/ui/fragment/FileDownloadFragment.java (David A. Velasco 2013-02-25 14:33:16 +0100 351)         ProgressListener(ProgressBar progressBar) {
d7e05192 src/com/owncloud/android/ui/fragment/FileDownloadFragment.java (David A. Velasco 2013-02-25 14:33:16 +0100 352)             mProgressBar = new WeakReference<ProgressBar>(progressBar);
d7e05192 src/com/owncloud/android/ui/fragment/FileDownloadFragment.java (David A. Velasco 2013-02-25 14:33:16 +0100 353)         }
d7e05192 src/com/owncloud/android/ui/fragment/FileDownloadFragment.java (David A. Velasco 2013-02-25 14:33:16 +0100 354)         
d7e05192 src/com/owncloud/android/ui/fragment/FileDownloadFragment.java (David A. Velasco 2013-02-25 14:33:16 +0100 355)         @Override
d7e05192 src/com/owncloud/android/ui/fragment/FileDownloadFragment.java (David A. Velasco 2013-02-25 14:33:16 +0100 356)         public void onTransferProgress(long progressRate) {
d7e05192 src/com/owncloud/android/ui/fragment/FileDownloadFragment.java (David A. Velasco 2013-02-25 14:33:16 +0100 357)             // old method, nothing here
d7e05192 src/com/owncloud/android/ui/fragment/FileDownloadFragment.java (David A. Velasco 2013-02-25 14:33:16 +0100 358)         };
d7e05192 src/com/owncloud/android/ui/fragment/FileDownloadFragment.java (David A. Velasco 2013-02-25 14:33:16 +0100 359) 
d7e05192 src/com/owncloud/android/ui/fragment/FileDownloadFragment.java (David A. Velasco 2013-02-25 14:33:16 +0100 360)         @Override
d7e05192 src/com/owncloud/android/ui/fragment/FileDownloadFragment.java (David A. Velasco 2013-02-25 14:33:16 +0100 361)         public void onTransferProgress(long progressRate, long totalTransferredSoFar, long totalToTransfer, String filename) {
d7e05192 src/com/owncloud/android/ui/fragment/FileDownloadFragment.java (David A. Velasco 2013-02-25 14:33:16 +0100 362)             int percent = (int)(100.0*((double)totalTransferredSoFar)/((double)totalToTransfer));
d7e05192 src/com/owncloud/android/ui/fragment/FileDownloadFragment.java (David A. Velasco 2013-02-25 14:33:16 +0100 363)             if (percent != mLastPercent) {
d7e05192 src/com/owncloud/android/ui/fragment/FileDownloadFragment.java (David A. Velasco 2013-02-25 14:33:16 +0100 364)                 ProgressBar pb = mProgressBar.get();
d7e05192 src/com/owncloud/android/ui/fragment/FileDownloadFragment.java (David A. Velasco 2013-02-25 14:33:16 +0100 365)                 if (pb != null) {
d7e05192 src/com/owncloud/android/ui/fragment/FileDownloadFragment.java (David A. Velasco 2013-02-25 14:33:16 +0100 366)                     pb.setProgress(percent);
d7e05192 src/com/owncloud/android/ui/fragment/FileDownloadFragment.java (David A. Velasco 2013-02-25 14:33:16 +0100 367)                     pb.postInvalidate();
d7e05192 src/com/owncloud/android/ui/fragment/FileDownloadFragment.java (David A. Velasco 2013-02-25 14:33:16 +0100 368)                 }
d7e05192 src/com/owncloud/android/ui/fragment/FileDownloadFragment.java (David A. Velasco 2013-02-25 14:33:16 +0100 369)             }
d7e05192 src/com/owncloud/android/ui/fragment/FileDownloadFragment.java (David A. Velasco 2013-02-25 14:33:16 +0100 370)             mLastPercent = percent;
d7e05192 src/com/owncloud/android/ui/fragment/FileDownloadFragment.java (David A. Velasco 2013-02-25 14:33:16 +0100 371)         }
d7e05192 src/com/owncloud/android/ui/fragment/FileDownloadFragment.java (David A. Velasco 2013-02-25 14:33:16 +0100 372) 
db7eea71 src/com/owncloud/android/ui/preview/FileDownloadFragment.java  (David A. Velasco 2013-03-01 14:20:25 +0100 373)     }
db7eea71 src/com/owncloud/android/ui/preview/FileDownloadFragment.java  (David A. Velasco 2013-03-01 14:20:25 +0100 374) 
db7eea71 src/com/owncloud/android/ui/preview/FileDownloadFragment.java  (David A. Velasco 2013-03-01 14:20:25 +0100 375) 
db7eea71 src/com/owncloud/android/ui/preview/FileDownloadFragment.java  (David A. Velasco 2013-03-01 14:20:25 +0100 376)     public void setError(boolean error) {
db7eea71 src/com/owncloud/android/ui/preview/FileDownloadFragment.java  (David A. Velasco 2013-03-01 14:20:25 +0100 377)         mError = error;
d7e05192 src/com/owncloud/android/ui/fragment/FileDownloadFragment.java (David A. Velasco 2013-02-25 14:33:16 +0100 378)     };
d7e05192 src/com/owncloud/android/ui/fragment/FileDownloadFragment.java (David A. Velasco 2013-02-25 14:33:16 +0100 379)     
d7e05192 src/com/owncloud/android/ui/fragment/FileDownloadFragment.java (David A. Velasco 2013-02-25 14:33:16 +0100 380) 
d7e05192 src/com/owncloud/android/ui/fragment/FileDownloadFragment.java (David A. Velasco 2013-02-25 14:33:16 +0100 381) 
d7e05192 src/com/owncloud/android/ui/fragment/FileDownloadFragment.java (David A. Velasco 2013-02-25 14:33:16 +0100 382) }
