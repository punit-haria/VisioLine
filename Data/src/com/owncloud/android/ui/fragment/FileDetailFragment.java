8e36e7cc (zerginator       2013-03-12 07:56:27 +0100   1) /* ownCloud Android client application
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100   2)  *   Copyright (C) 2011  Bartek Przybylski
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100   3)  *   Copyright (C) 2012-2013 ownCloud Inc.
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100   4)  *
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100   5)  *   This program is free software: you can redistribute it and/or modify
bdc0332c (David A. Velasco 2013-04-17 12:26:13 +0200   6)  *   it under the terms of the GNU General Public License version 2,
bdc0332c (David A. Velasco 2013-04-17 12:26:13 +0200   7)  *   as published by the Free Software Foundation.
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100   8)  *
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100   9)  *   This program is distributed in the hope that it will be useful,
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100  10)  *   but WITHOUT ANY WARRANTY; without even the implied warranty of
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100  11)  *   MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100  12)  *   GNU General Public License for more details.
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100  13)  *
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100  14)  *   You should have received a copy of the GNU General Public License
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100  15)  *   along with this program.  If not, see <http://www.gnu.org/licenses/>.
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100  16)  *
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100  17)  */
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100  18) package com.owncloud.android.ui.fragment;
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100  19) 
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100  20) import java.io.File;
c3ca5b5a (David A. Velasco 2013-04-15 13:12:04 +0200  21) import java.lang.ref.WeakReference;
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100  22) import java.util.ArrayList;
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100  23) import java.util.List;
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100  24) 
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100  25) import android.accounts.Account;
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100  26) import android.app.Activity;
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100  27) import android.content.BroadcastReceiver;
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100  28) import android.content.Context;
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100  29) import android.content.Intent;
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100  30) import android.content.IntentFilter;
c3ca5b5a (David A. Velasco 2013-04-15 13:12:04 +0200  31) import android.os.Bundle;
c3ca5b5a (David A. Velasco 2013-04-15 13:12:04 +0200  32) import android.os.Handler;
c3ca5b5a (David A. Velasco 2013-04-15 13:12:04 +0200  33) import android.support.v4.app.FragmentTransaction;
c3ca5b5a (David A. Velasco 2013-04-15 13:12:04 +0200  34) import android.view.LayoutInflater;
c3ca5b5a (David A. Velasco 2013-04-15 13:12:04 +0200  35) import android.view.View;
c3ca5b5a (David A. Velasco 2013-04-15 13:12:04 +0200  36) import android.view.View.OnClickListener;
c3ca5b5a (David A. Velasco 2013-04-15 13:12:04 +0200  37) import android.view.ViewGroup;
c3ca5b5a (David A. Velasco 2013-04-15 13:12:04 +0200  38) import android.widget.CheckBox;
c3ca5b5a (David A. Velasco 2013-04-15 13:12:04 +0200  39) import android.widget.ImageView;
c3ca5b5a (David A. Velasco 2013-04-15 13:12:04 +0200  40) import android.widget.ProgressBar;
c3ca5b5a (David A. Velasco 2013-04-15 13:12:04 +0200  41) import android.widget.TextView;
c3ca5b5a (David A. Velasco 2013-04-15 13:12:04 +0200  42) import android.widget.Toast;
c3ca5b5a (David A. Velasco 2013-04-15 13:12:04 +0200  43) 
803568ab (David A. Velasco 2013-04-22 14:34:42 +0200  44) import com.actionbarsherlock.view.Menu;
803568ab (David A. Velasco 2013-04-22 14:34:42 +0200  45) import com.actionbarsherlock.view.MenuInflater;
803568ab (David A. Velasco 2013-04-22 14:34:42 +0200  46) import com.actionbarsherlock.view.MenuItem;
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100  47) import com.owncloud.android.DisplayUtils;
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100  48) import com.owncloud.android.Log_OC;
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100  49) import com.owncloud.android.datamodel.FileDataStorageManager;
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100  50) import com.owncloud.android.datamodel.OCFile;
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100  51) import com.owncloud.android.files.services.FileObserverService;
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100  52) import com.owncloud.android.files.services.FileUploader;
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100  53) import com.owncloud.android.files.services.FileDownloader.FileDownloaderBinder;
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100  54) import com.owncloud.android.files.services.FileUploader.FileUploaderBinder;
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100  55) import com.owncloud.android.operations.OnRemoteOperationListener;
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100  56) import com.owncloud.android.operations.RemoteOperation;
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100  57) import com.owncloud.android.operations.RemoteOperationResult;
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100  58) import com.owncloud.android.operations.RemoteOperationResult.ResultCode;
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100  59) import com.owncloud.android.operations.RemoveFileOperation;
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100  60) import com.owncloud.android.operations.RenameFileOperation;
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100  61) import com.owncloud.android.operations.SynchronizeFileOperation;
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100  62) import com.owncloud.android.ui.activity.ConflictsResolveActivity;
bc1fcf84 (David A. Velasco 2013-05-07 13:49:54 +0200  63) import com.owncloud.android.ui.activity.FileActivity;
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100  64) import com.owncloud.android.ui.activity.FileDisplayActivity;
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100  65) import com.owncloud.android.ui.dialog.EditNameDialog;
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100  66) import com.owncloud.android.ui.dialog.EditNameDialog.EditNameDialogListener;
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100  67) 
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100  68) import com.owncloud.android.R;
c3ca5b5a (David A. Velasco 2013-04-15 13:12:04 +0200  69) 
c3ca5b5a (David A. Velasco 2013-04-15 13:12:04 +0200  70) import eu.alefzero.webdav.OnDatatransferProgressListener;
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100  71) 
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100  72) /**
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100  73)  * This Fragment is used to display the details about a file.
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100  74)  * 
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100  75)  * @author Bartek Przybylski
c3ca5b5a (David A. Velasco 2013-04-15 13:12:04 +0200  76)  * @author David A. Velasco
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100  77)  */
fd396289 (David A. Velasco 2013-05-30 17:53:21 +0200  78) public class FileDetailFragment extends FileFragment implements
c3ca5b5a (David A. Velasco 2013-04-15 13:12:04 +0200  79)         OnClickListener, 
fd396289 (David A. Velasco 2013-05-30 17:53:21 +0200  80)         ConfirmationDialogFragment.ConfirmationDialogFragmentListener, OnRemoteOperationListener, EditNameDialogListener {
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100  81) 
c3ca5b5a (David A. Velasco 2013-04-15 13:12:04 +0200  82)     private FileFragment.ContainerActivity mContainerActivity;
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100  83)     
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100  84)     private int mLayout;
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100  85)     private View mView;
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100  86)     private Account mAccount;
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100  87)     private FileDataStorageManager mStorageManager;
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100  88)     
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100  89)     private UploadFinishReceiver mUploadFinishReceiver;
c3ca5b5a (David A. Velasco 2013-04-15 13:12:04 +0200  90)     public ProgressListener mProgressListener;
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100  91)     
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100  92)     private Handler mHandler;
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100  93)     private RemoteOperation mLastRemoteOperation;
c3ca5b5a (David A. Velasco 2013-04-15 13:12:04 +0200  94)     
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100  95)     private static final String TAG = FileDetailFragment.class.getSimpleName();
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100  96)     public static final String FTAG_CONFIRMATION = "REMOVE_CONFIRMATION_FRAGMENT";
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100  97)     
c3ca5b5a (David A. Velasco 2013-04-15 13:12:04 +0200  98) 
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100  99)     /**
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100 100)      * Creates an empty details fragment.
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100 101)      * 
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100 102)      * It's necessary to keep a public constructor without parameters; the system uses it when tries to reinstantiate a fragment automatically. 
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100 103)      */
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100 104)     public FileDetailFragment() {
fd396289 (David A. Velasco 2013-05-30 17:53:21 +0200 105)         super();
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100 106)         mAccount = null;
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100 107)         mStorageManager = null;
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100 108)         mLayout = R.layout.file_details_empty;
c3ca5b5a (David A. Velasco 2013-04-15 13:12:04 +0200 109)         mProgressListener = null;
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100 110)     }
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100 111)     
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100 112)     /**
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100 113)      * Creates a details fragment.
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100 114)      * 
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100 115)      * When 'fileToDetail' or 'ocAccount' are null, creates a dummy layout (to use when a file wasn't tapped before).
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100 116)      * 
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100 117)      * @param fileToDetail      An {@link OCFile} to show in the fragment
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100 118)      * @param ocAccount         An ownCloud account; needed to start downloads
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100 119)      */
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100 120)     public FileDetailFragment(OCFile fileToDetail, Account ocAccount) {
fd396289 (David A. Velasco 2013-05-30 17:53:21 +0200 121)         super(fileToDetail);
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100 122)         mAccount = ocAccount;
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100 123)         mStorageManager = null; // we need a context to init this; the container activity is not available yet at this moment 
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100 124)         mLayout = R.layout.file_details_empty;
c3ca5b5a (David A. Velasco 2013-04-15 13:12:04 +0200 125)         mProgressListener = null;
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100 126)     }
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100 127)     
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100 128)     
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100 129)     @Override
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100 130)     public void onCreate(Bundle savedInstanceState) {
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100 131)         super.onCreate(savedInstanceState);
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100 132)         mHandler = new Handler();
803568ab (David A. Velasco 2013-04-22 14:34:42 +0200 133)         setHasOptionsMenu(true);
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100 134)     }
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100 135)     
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100 136) 
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100 137)     @Override
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100 138)     public View onCreateView(LayoutInflater inflater, ViewGroup container,
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100 139)             Bundle savedInstanceState) {
c70f3333 (David A. Velasco 2013-06-04 14:30:29 +0200 140)         //super.onCreateView(inflater, container, savedInstanceState);
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100 141)         
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100 142)         if (savedInstanceState != null) {
fd396289 (David A. Velasco 2013-05-30 17:53:21 +0200 143)             setFile((OCFile)savedInstanceState.getParcelable(FileActivity.EXTRA_FILE));
bc1fcf84 (David A. Velasco 2013-05-07 13:49:54 +0200 144)             mAccount = savedInstanceState.getParcelable(FileActivity.EXTRA_ACCOUNT);
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100 145)         }
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100 146)         
fd396289 (David A. Velasco 2013-05-30 17:53:21 +0200 147)         if(getFile() != null && mAccount != null) {
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100 148)             mLayout = R.layout.file_details_fragment;
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100 149)         }
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100 150)         
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100 151)         View view = null;
c70f3333 (David A. Velasco 2013-06-04 14:30:29 +0200 152)         //view = inflater.inflate(mLayout, container, false);
c70f3333 (David A. Velasco 2013-06-04 14:30:29 +0200 153)         view = inflater.inflate(mLayout, null);
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100 154)         mView = view;
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100 155)         
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100 156)         if (mLayout == R.layout.file_details_fragment) {
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100 157)             mView.findViewById(R.id.fdKeepInSync).setOnClickListener(this);
c3ca5b5a (David A. Velasco 2013-04-15 13:12:04 +0200 158)             ProgressBar progressBar = (ProgressBar)mView.findViewById(R.id.fdProgressBar);
c3ca5b5a (David A. Velasco 2013-04-15 13:12:04 +0200 159)             mProgressListener = new ProgressListener(progressBar);
803568ab (David A. Velasco 2013-04-22 14:34:42 +0200 160)             mView.findViewById(R.id.fdCancelBtn).setOnClickListener(this);
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100 161)         }
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100 162)         
c3ca5b5a (David A. Velasco 2013-04-15 13:12:04 +0200 163)         updateFileDetails(false, false);
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100 164)         return view;
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100 165)     }
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100 166)     
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100 167)     /**
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100 168)      * {@inheritDoc}
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100 169)      */
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100 170)     @Override
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100 171)     public void onAttach(Activity activity) {
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100 172)         super.onAttach(activity);
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100 173)         try {
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100 174)             mContainerActivity = (ContainerActivity) activity;
c3ca5b5a (David A. Velasco 2013-04-15 13:12:04 +0200 175)             
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100 176)         } catch (ClassCastException e) {
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100 177)             throw new ClassCastException(activity.toString() + " must implement " + FileDetailFragment.ContainerActivity.class.getSimpleName());
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100 178)         }
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100 179)     }
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100 180)     
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100 181)     
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100 182)     /**
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100 183)      * {@inheritDoc}
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100 184)      */
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100 185)     @Override
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100 186)     public void onActivityCreated(Bundle savedInstanceState) {
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100 187)         super.onActivityCreated(savedInstanceState);
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100 188)         if (mAccount != null) {
c3ca5b5a (David A. Velasco 2013-04-15 13:12:04 +0200 189)             mStorageManager = new FileDataStorageManager(mAccount, getActivity().getApplicationContext().getContentResolver());
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100 190)         }
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100 191)     }
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100 192)         
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100 193) 
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100 194)     @Override
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100 195)     public void onSaveInstanceState(Bundle outState) {
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100 196)         super.onSaveInstanceState(outState);
fd396289 (David A. Velasco 2013-05-30 17:53:21 +0200 197)         outState.putParcelable(FileActivity.EXTRA_FILE, getFile());
bc1fcf84 (David A. Velasco 2013-05-07 13:49:54 +0200 198)         outState.putParcelable(FileActivity.EXTRA_ACCOUNT, mAccount);
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100 199)     }
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100 200) 
c3ca5b5a (David A. Velasco 2013-04-15 13:12:04 +0200 201)     @Override
c3ca5b5a (David A. Velasco 2013-04-15 13:12:04 +0200 202)     public void onStart() {
c3ca5b5a (David A. Velasco 2013-04-15 13:12:04 +0200 203)         super.onStart();
c3ca5b5a (David A. Velasco 2013-04-15 13:12:04 +0200 204)         listenForTransferProgress();
c3ca5b5a (David A. Velasco 2013-04-15 13:12:04 +0200 205)     }
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100 206)     
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100 207)     @Override
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100 208)     public void onResume() {
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100 209)         super.onResume();
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100 210)         mUploadFinishReceiver = new UploadFinishReceiver();
c3ca5b5a (David A. Velasco 2013-04-15 13:12:04 +0200 211)         IntentFilter filter = new IntentFilter(FileUploader.UPLOAD_FINISH_MESSAGE);
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100 212)         getActivity().registerReceiver(mUploadFinishReceiver, filter);
c3ca5b5a (David A. Velasco 2013-04-15 13:12:04 +0200 213) 
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100 214)     }
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100 215) 
c3ca5b5a (David A. Velasco 2013-04-15 13:12:04 +0200 216) 
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100 217)     @Override
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100 218)     public void onPause() {
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100 219)         super.onPause();
c3ca5b5a (David A. Velasco 2013-04-15 13:12:04 +0200 220)         if (mUploadFinishReceiver != null) {
c3ca5b5a (David A. Velasco 2013-04-15 13:12:04 +0200 221)             getActivity().unregisterReceiver(mUploadFinishReceiver);
c3ca5b5a (David A. Velasco 2013-04-15 13:12:04 +0200 222)             mUploadFinishReceiver = null;
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100 223)         }
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100 224)     }
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100 225) 
c3ca5b5a (David A. Velasco 2013-04-15 13:12:04 +0200 226)     
c3ca5b5a (David A. Velasco 2013-04-15 13:12:04 +0200 227)     @Override
c3ca5b5a (David A. Velasco 2013-04-15 13:12:04 +0200 228)     public void onStop() {
c3ca5b5a (David A. Velasco 2013-04-15 13:12:04 +0200 229)         super.onStop();
c3ca5b5a (David A. Velasco 2013-04-15 13:12:04 +0200 230)         leaveTransferProgress();
c3ca5b5a (David A. Velasco 2013-04-15 13:12:04 +0200 231)     }
c3ca5b5a (David A. Velasco 2013-04-15 13:12:04 +0200 232) 
c3ca5b5a (David A. Velasco 2013-04-15 13:12:04 +0200 233)     
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100 234)     @Override
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100 235)     public View getView() {
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100 236)         return super.getView() == null ? mView : super.getView();
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100 237)     }
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100 238) 
803568ab (David A. Velasco 2013-04-22 14:34:42 +0200 239)     
803568ab (David A. Velasco 2013-04-22 14:34:42 +0200 240)     /**
803568ab (David A. Velasco 2013-04-22 14:34:42 +0200 241)      * {@inheritDoc}
803568ab (David A. Velasco 2013-04-22 14:34:42 +0200 242)      */
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100 243)     @Override
803568ab (David A. Velasco 2013-04-22 14:34:42 +0200 244)     public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
803568ab (David A. Velasco 2013-04-22 14:34:42 +0200 245)         super.onCreateOptionsMenu(menu, inflater);
803568ab (David A. Velasco 2013-04-22 14:34:42 +0200 246)         inflater.inflate(R.menu.file_actions_menu, menu);
803568ab (David A. Velasco 2013-04-22 14:34:42 +0200 247)         MenuItem item = menu.findItem(R.id.action_see_details);
803568ab (David A. Velasco 2013-04-22 14:34:42 +0200 248)         if (item != null) {
803568ab (David A. Velasco 2013-04-22 14:34:42 +0200 249)             item.setVisible(false);
803568ab (David A. Velasco 2013-04-22 14:34:42 +0200 250)             item.setEnabled(false);
803568ab (David A. Velasco 2013-04-22 14:34:42 +0200 251)         }
803568ab (David A. Velasco 2013-04-22 14:34:42 +0200 252)     }
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100 253) 
803568ab (David A. Velasco 2013-04-22 14:34:42 +0200 254)     
803568ab (David A. Velasco 2013-04-22 14:34:42 +0200 255)     /**
803568ab (David A. Velasco 2013-04-22 14:34:42 +0200 256)      * {@inheritDoc}
803568ab (David A. Velasco 2013-04-22 14:34:42 +0200 257)      */
803568ab (David A. Velasco 2013-04-22 14:34:42 +0200 258)     @Override
803568ab (David A. Velasco 2013-04-22 14:34:42 +0200 259)     public void onPrepareOptionsMenu (Menu menu) {
803568ab (David A. Velasco 2013-04-22 14:34:42 +0200 260)         super.onPrepareOptionsMenu(menu);
803568ab (David A. Velasco 2013-04-22 14:34:42 +0200 261)         
803568ab (David A. Velasco 2013-04-22 14:34:42 +0200 262)         List<Integer> toHide = new ArrayList<Integer>();
803568ab (David A. Velasco 2013-04-22 14:34:42 +0200 263)         List<Integer> toShow = new ArrayList<Integer>();
fd396289 (David A. Velasco 2013-05-30 17:53:21 +0200 264)         OCFile file = getFile();
803568ab (David A. Velasco 2013-04-22 14:34:42 +0200 265)         
803568ab (David A. Velasco 2013-04-22 14:34:42 +0200 266)         FileDownloaderBinder downloaderBinder = mContainerActivity.getFileDownloaderBinder();
fd396289 (David A. Velasco 2013-05-30 17:53:21 +0200 267)         boolean downloading = downloaderBinder != null && downloaderBinder.isDownloading(mAccount, file);
803568ab (David A. Velasco 2013-04-22 14:34:42 +0200 268)         FileUploaderBinder uploaderBinder = mContainerActivity.getFileUploaderBinder();
fd396289 (David A. Velasco 2013-05-30 17:53:21 +0200 269)         boolean uploading = uploaderBinder != null && uploaderBinder.isUploading(mAccount, getFile());
803568ab (David A. Velasco 2013-04-22 14:34:42 +0200 270)         
803568ab (David A. Velasco 2013-04-22 14:34:42 +0200 271)         if (downloading || uploading) {
803568ab (David A. Velasco 2013-04-22 14:34:42 +0200 272)             toHide.add(R.id.action_download_file);
803568ab (David A. Velasco 2013-04-22 14:34:42 +0200 273)             toHide.add(R.id.action_rename_file);
803568ab (David A. Velasco 2013-04-22 14:34:42 +0200 274)             toHide.add(R.id.action_remove_file);
803568ab (David A. Velasco 2013-04-22 14:34:42 +0200 275)             toHide.add(R.id.action_open_file_with);
803568ab (David A. Velasco 2013-04-22 14:34:42 +0200 276)             if (!downloading) {
803568ab (David A. Velasco 2013-04-22 14:34:42 +0200 277)                 toHide.add(R.id.action_cancel_download);
803568ab (David A. Velasco 2013-04-22 14:34:42 +0200 278)                 toShow.add(R.id.action_cancel_upload);
803568ab (David A. Velasco 2013-04-22 14:34:42 +0200 279)             } else {
803568ab (David A. Velasco 2013-04-22 14:34:42 +0200 280)                 toHide.add(R.id.action_cancel_upload);
803568ab (David A. Velasco 2013-04-22 14:34:42 +0200 281)                 toShow.add(R.id.action_cancel_download);
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100 282)             }
803568ab (David A. Velasco 2013-04-22 14:34:42 +0200 283) 
fd396289 (David A. Velasco 2013-05-30 17:53:21 +0200 284)         } else if (file != null && file.isDown()) {
803568ab (David A. Velasco 2013-04-22 14:34:42 +0200 285)             toHide.add(R.id.action_download_file);
803568ab (David A. Velasco 2013-04-22 14:34:42 +0200 286)             toHide.add(R.id.action_cancel_download);
803568ab (David A. Velasco 2013-04-22 14:34:42 +0200 287)             toHide.add(R.id.action_cancel_upload);
803568ab (David A. Velasco 2013-04-22 14:34:42 +0200 288)             
803568ab (David A. Velasco 2013-04-22 14:34:42 +0200 289)             toShow.add(R.id.action_rename_file);
803568ab (David A. Velasco 2013-04-22 14:34:42 +0200 290)             toShow.add(R.id.action_remove_file);
803568ab (David A. Velasco 2013-04-22 14:34:42 +0200 291)             toShow.add(R.id.action_open_file_with);
803568ab (David A. Velasco 2013-04-22 14:34:42 +0200 292)             toShow.add(R.id.action_sync_file);
803568ab (David A. Velasco 2013-04-22 14:34:42 +0200 293)             
fd396289 (David A. Velasco 2013-05-30 17:53:21 +0200 294)         } else if (file != null) {
803568ab (David A. Velasco 2013-04-22 14:34:42 +0200 295)             toHide.add(R.id.action_open_file_with);
803568ab (David A. Velasco 2013-04-22 14:34:42 +0200 296)             toHide.add(R.id.action_cancel_download);
803568ab (David A. Velasco 2013-04-22 14:34:42 +0200 297)             toHide.add(R.id.action_cancel_upload);
803568ab (David A. Velasco 2013-04-22 14:34:42 +0200 298)             toHide.add(R.id.action_sync_file);
803568ab (David A. Velasco 2013-04-22 14:34:42 +0200 299)             
803568ab (David A. Velasco 2013-04-22 14:34:42 +0200 300)             toShow.add(R.id.action_rename_file);
803568ab (David A. Velasco 2013-04-22 14:34:42 +0200 301)             toShow.add(R.id.action_remove_file);
803568ab (David A. Velasco 2013-04-22 14:34:42 +0200 302)             toShow.add(R.id.action_download_file);
850d7a11 (David A. Velasco 2013-04-22 15:11:09 +0200 303)             
850d7a11 (David A. Velasco 2013-04-22 15:11:09 +0200 304)         } else {
850d7a11 (David A. Velasco 2013-04-22 15:11:09 +0200 305)             toHide.add(R.id.action_open_file_with);
850d7a11 (David A. Velasco 2013-04-22 15:11:09 +0200 306)             toHide.add(R.id.action_cancel_download);
850d7a11 (David A. Velasco 2013-04-22 15:11:09 +0200 307)             toHide.add(R.id.action_cancel_upload);
850d7a11 (David A. Velasco 2013-04-22 15:11:09 +0200 308)             toHide.add(R.id.action_sync_file);
850d7a11 (David A. Velasco 2013-04-22 15:11:09 +0200 309)             toHide.add(R.id.action_download_file);
850d7a11 (David A. Velasco 2013-04-22 15:11:09 +0200 310)             toHide.add(R.id.action_rename_file);
850d7a11 (David A. Velasco 2013-04-22 15:11:09 +0200 311)             toHide.add(R.id.action_remove_file);
850d7a11 (David A. Velasco 2013-04-22 15:11:09 +0200 312)             
803568ab (David A. Velasco 2013-04-22 14:34:42 +0200 313)         }
803568ab (David A. Velasco 2013-04-22 14:34:42 +0200 314) 
803568ab (David A. Velasco 2013-04-22 14:34:42 +0200 315)         MenuItem item = null;
803568ab (David A. Velasco 2013-04-22 14:34:42 +0200 316)         for (int i : toHide) {
803568ab (David A. Velasco 2013-04-22 14:34:42 +0200 317)             item = menu.findItem(i);
803568ab (David A. Velasco 2013-04-22 14:34:42 +0200 318)             if (item != null) {
803568ab (David A. Velasco 2013-04-22 14:34:42 +0200 319)                 item.setVisible(false);
803568ab (David A. Velasco 2013-04-22 14:34:42 +0200 320)                 item.setEnabled(false);
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100 321)             }
803568ab (David A. Velasco 2013-04-22 14:34:42 +0200 322)         }
803568ab (David A. Velasco 2013-04-22 14:34:42 +0200 323)         for (int i : toShow) {
803568ab (David A. Velasco 2013-04-22 14:34:42 +0200 324)             item = menu.findItem(i);
803568ab (David A. Velasco 2013-04-22 14:34:42 +0200 325)             if (item != null) {
803568ab (David A. Velasco 2013-04-22 14:34:42 +0200 326)                 item.setVisible(true);
803568ab (David A. Velasco 2013-04-22 14:34:42 +0200 327)                 item.setEnabled(true);
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100 328)             }
803568ab (David A. Velasco 2013-04-22 14:34:42 +0200 329)         }
803568ab (David A. Velasco 2013-04-22 14:34:42 +0200 330)     }
803568ab (David A. Velasco 2013-04-22 14:34:42 +0200 331) 
803568ab (David A. Velasco 2013-04-22 14:34:42 +0200 332)     
803568ab (David A. Velasco 2013-04-22 14:34:42 +0200 333)     /**
803568ab (David A. Velasco 2013-04-22 14:34:42 +0200 334)      * {@inheritDoc}
803568ab (David A. Velasco 2013-04-22 14:34:42 +0200 335)      */
803568ab (David A. Velasco 2013-04-22 14:34:42 +0200 336)     @Override
803568ab (David A. Velasco 2013-04-22 14:34:42 +0200 337)     public boolean onOptionsItemSelected(MenuItem item) {
803568ab (David A. Velasco 2013-04-22 14:34:42 +0200 338)         switch (item.getItemId()) {
803568ab (David A. Velasco 2013-04-22 14:34:42 +0200 339)             case R.id.action_open_file_with: {
fd396289 (David A. Velasco 2013-05-30 17:53:21 +0200 340)                 mContainerActivity.openFile(getFile());
803568ab (David A. Velasco 2013-04-22 14:34:42 +0200 341)                 return true;
803568ab (David A. Velasco 2013-04-22 14:34:42 +0200 342)             }
803568ab (David A. Velasco 2013-04-22 14:34:42 +0200 343)             case R.id.action_remove_file: {
803568ab (David A. Velasco 2013-04-22 14:34:42 +0200 344)                 removeFile();
803568ab (David A. Velasco 2013-04-22 14:34:42 +0200 345)                 return true;
803568ab (David A. Velasco 2013-04-22 14:34:42 +0200 346)             }
803568ab (David A. Velasco 2013-04-22 14:34:42 +0200 347)             case R.id.action_rename_file: {
803568ab (David A. Velasco 2013-04-22 14:34:42 +0200 348)                 renameFile();
803568ab (David A. Velasco 2013-04-22 14:34:42 +0200 349)                 return true;
803568ab (David A. Velasco 2013-04-22 14:34:42 +0200 350)             }
803568ab (David A. Velasco 2013-04-22 14:34:42 +0200 351)             case R.id.action_download_file: 
803568ab (David A. Velasco 2013-04-22 14:34:42 +0200 352)             case R.id.action_cancel_download:
803568ab (David A. Velasco 2013-04-22 14:34:42 +0200 353)             case R.id.action_cancel_upload:
803568ab (David A. Velasco 2013-04-22 14:34:42 +0200 354)             case R.id.action_sync_file: {
803568ab (David A. Velasco 2013-04-22 14:34:42 +0200 355)                 synchronizeFile();
803568ab (David A. Velasco 2013-04-22 14:34:42 +0200 356)                 return true;
803568ab (David A. Velasco 2013-04-22 14:34:42 +0200 357)             }
803568ab (David A. Velasco 2013-04-22 14:34:42 +0200 358)             default:
803568ab (David A. Velasco 2013-04-22 14:34:42 +0200 359)                 return false;
803568ab (David A. Velasco 2013-04-22 14:34:42 +0200 360)         }
803568ab (David A. Velasco 2013-04-22 14:34:42 +0200 361)     }
803568ab (David A. Velasco 2013-04-22 14:34:42 +0200 362)     
803568ab (David A. Velasco 2013-04-22 14:34:42 +0200 363)     @Override
803568ab (David A. Velasco 2013-04-22 14:34:42 +0200 364)     public void onClick(View v) {
803568ab (David A. Velasco 2013-04-22 14:34:42 +0200 365)         switch (v.getId()) {
803568ab (David A. Velasco 2013-04-22 14:34:42 +0200 366)             case R.id.fdKeepInSync: {
803568ab (David A. Velasco 2013-04-22 14:34:42 +0200 367)                 toggleKeepInSync();
803568ab (David A. Velasco 2013-04-22 14:34:42 +0200 368)                 break;
803568ab (David A. Velasco 2013-04-22 14:34:42 +0200 369)             }
803568ab (David A. Velasco 2013-04-22 14:34:42 +0200 370)             case R.id.fdCancelBtn: {
803568ab (David A. Velasco 2013-04-22 14:34:42 +0200 371)                 synchronizeFile();
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100 372)                 break;
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100 373)             }
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100 374)             default:
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100 375)                 Log_OC.e(TAG, "Incorrect view clicked!");
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100 376)         }
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100 377)     }
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100 378)     
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100 379)     
803568ab (David A. Velasco 2013-04-22 14:34:42 +0200 380)     private void toggleKeepInSync() {
803568ab (David A. Velasco 2013-04-22 14:34:42 +0200 381)         CheckBox cb = (CheckBox) getView().findViewById(R.id.fdKeepInSync);
fd396289 (David A. Velasco 2013-05-30 17:53:21 +0200 382)         OCFile file = getFile();
fd396289 (David A. Velasco 2013-05-30 17:53:21 +0200 383)         file.setKeepInSync(cb.isChecked());
fd396289 (David A. Velasco 2013-05-30 17:53:21 +0200 384)         mStorageManager.saveFile(file);
803568ab (David A. Velasco 2013-04-22 14:34:42 +0200 385)         
803568ab (David A. Velasco 2013-04-22 14:34:42 +0200 386)         /// register the OCFile instance in the observer service to monitor local updates;
803568ab (David A. Velasco 2013-04-22 14:34:42 +0200 387)         /// if necessary, the file is download 
803568ab (David A. Velasco 2013-04-22 14:34:42 +0200 388)         Intent intent = new Intent(getActivity().getApplicationContext(),
803568ab (David A. Velasco 2013-04-22 14:34:42 +0200 389)                                    FileObserverService.class);
803568ab (David A. Velasco 2013-04-22 14:34:42 +0200 390)         intent.putExtra(FileObserverService.KEY_FILE_CMD,
803568ab (David A. Velasco 2013-04-22 14:34:42 +0200 391)                    (cb.isChecked()?
803568ab (David A. Velasco 2013-04-22 14:34:42 +0200 392)                            FileObserverService.CMD_ADD_OBSERVED_FILE:
803568ab (David A. Velasco 2013-04-22 14:34:42 +0200 393)                            FileObserverService.CMD_DEL_OBSERVED_FILE));
fd396289 (David A. Velasco 2013-05-30 17:53:21 +0200 394)         intent.putExtra(FileObserverService.KEY_CMD_ARG_FILE, file);
803568ab (David A. Velasco 2013-04-22 14:34:42 +0200 395)         intent.putExtra(FileObserverService.KEY_CMD_ARG_ACCOUNT, mAccount);
803568ab (David A. Velasco 2013-04-22 14:34:42 +0200 396)         getActivity().startService(intent);
803568ab (David A. Velasco 2013-04-22 14:34:42 +0200 397)         
fd396289 (David A. Velasco 2013-05-30 17:53:21 +0200 398)         if (file.keepInSync()) {
803568ab (David A. Velasco 2013-04-22 14:34:42 +0200 399)             synchronizeFile();   // force an immediate synchronization
803568ab (David A. Velasco 2013-04-22 14:34:42 +0200 400)         }
803568ab (David A. Velasco 2013-04-22 14:34:42 +0200 401)     }
803568ab (David A. Velasco 2013-04-22 14:34:42 +0200 402) 
803568ab (David A. Velasco 2013-04-22 14:34:42 +0200 403) 
803568ab (David A. Velasco 2013-04-22 14:34:42 +0200 404)     private void removeFile() {
fd396289 (David A. Velasco 2013-05-30 17:53:21 +0200 405)         OCFile file = getFile();
803568ab (David A. Velasco 2013-04-22 14:34:42 +0200 406)         ConfirmationDialogFragment confDialog = ConfirmationDialogFragment.newInstance(
803568ab (David A. Velasco 2013-04-22 14:34:42 +0200 407)                 R.string.confirmation_remove_alert,
fd396289 (David A. Velasco 2013-05-30 17:53:21 +0200 408)                 new String[]{file.getFileName()},
fd396289 (David A. Velasco 2013-05-30 17:53:21 +0200 409)                 file.isDown() ? R.string.confirmation_remove_remote_and_local : R.string.confirmation_remove_remote,
fd396289 (David A. Velasco 2013-05-30 17:53:21 +0200 410)                 file.isDown() ? R.string.confirmation_remove_local : -1,
803568ab (David A. Velasco 2013-04-22 14:34:42 +0200 411)                 R.string.common_cancel);
803568ab (David A. Velasco 2013-04-22 14:34:42 +0200 412)         confDialog.setOnConfirmationListener(this);
803568ab (David A. Velasco 2013-04-22 14:34:42 +0200 413)         confDialog.show(getFragmentManager(), FTAG_CONFIRMATION);
803568ab (David A. Velasco 2013-04-22 14:34:42 +0200 414)     }
803568ab (David A. Velasco 2013-04-22 14:34:42 +0200 415) 
803568ab (David A. Velasco 2013-04-22 14:34:42 +0200 416) 
803568ab (David A. Velasco 2013-04-22 14:34:42 +0200 417)     private void renameFile() {
fd396289 (David A. Velasco 2013-05-30 17:53:21 +0200 418)         OCFile file = getFile();
fd396289 (David A. Velasco 2013-05-30 17:53:21 +0200 419)         String fileName = file.getFileName();
fd396289 (David A. Velasco 2013-05-30 17:53:21 +0200 420)         int extensionStart = file.isDirectory() ? -1 : fileName.lastIndexOf(".");
803568ab (David A. Velasco 2013-04-22 14:34:42 +0200 421)         int selectionEnd = (extensionStart >= 0) ? extensionStart : fileName.length();
803568ab (David A. Velasco 2013-04-22 14:34:42 +0200 422)         EditNameDialog dialog = EditNameDialog.newInstance(getString(R.string.rename_dialog_title), fileName, 0, selectionEnd, this);
803568ab (David A. Velasco 2013-04-22 14:34:42 +0200 423)         dialog.show(getFragmentManager(), "nameeditdialog");
803568ab (David A. Velasco 2013-04-22 14:34:42 +0200 424)     }
803568ab (David A. Velasco 2013-04-22 14:34:42 +0200 425) 
803568ab (David A. Velasco 2013-04-22 14:34:42 +0200 426)     private void synchronizeFile() {
fd396289 (David A. Velasco 2013-05-30 17:53:21 +0200 427)         OCFile file = getFile();
803568ab (David A. Velasco 2013-04-22 14:34:42 +0200 428)         FileDownloaderBinder downloaderBinder = mContainerActivity.getFileDownloaderBinder();
803568ab (David A. Velasco 2013-04-22 14:34:42 +0200 429)         FileUploaderBinder uploaderBinder = mContainerActivity.getFileUploaderBinder();
fd396289 (David A. Velasco 2013-05-30 17:53:21 +0200 430)         if (downloaderBinder != null && downloaderBinder.isDownloading(mAccount, file)) {
fd396289 (David A. Velasco 2013-05-30 17:53:21 +0200 431)             downloaderBinder.cancel(mAccount, file);
fd396289 (David A. Velasco 2013-05-30 17:53:21 +0200 432)             if (file.isDown()) {
803568ab (David A. Velasco 2013-04-22 14:34:42 +0200 433)                 setButtonsForDown();
803568ab (David A. Velasco 2013-04-22 14:34:42 +0200 434)             } else {
803568ab (David A. Velasco 2013-04-22 14:34:42 +0200 435)                 setButtonsForRemote();
803568ab (David A. Velasco 2013-04-22 14:34:42 +0200 436)             }
803568ab (David A. Velasco 2013-04-22 14:34:42 +0200 437) 
fd396289 (David A. Velasco 2013-05-30 17:53:21 +0200 438)         } else if (uploaderBinder != null && uploaderBinder.isUploading(mAccount, file)) {
fd396289 (David A. Velasco 2013-05-30 17:53:21 +0200 439)             uploaderBinder.cancel(mAccount, file);
fd396289 (David A. Velasco 2013-05-30 17:53:21 +0200 440)             if (!file.fileExists()) {
803568ab (David A. Velasco 2013-04-22 14:34:42 +0200 441)                 // TODO make something better
c70f3333 (David A. Velasco 2013-06-04 14:30:29 +0200 442)                 ((FileDisplayActivity)getActivity()).cleanSecondFragment();
803568ab (David A. Velasco 2013-04-22 14:34:42 +0200 443)                 
fd396289 (David A. Velasco 2013-05-30 17:53:21 +0200 444)             } else if (file.isDown()) {
803568ab (David A. Velasco 2013-04-22 14:34:42 +0200 445)                 setButtonsForDown();
803568ab (David A. Velasco 2013-04-22 14:34:42 +0200 446)             } else {
803568ab (David A. Velasco 2013-04-22 14:34:42 +0200 447)                 setButtonsForRemote();
803568ab (David A. Velasco 2013-04-22 14:34:42 +0200 448)             }
803568ab (David A. Velasco 2013-04-22 14:34:42 +0200 449)             
803568ab (David A. Velasco 2013-04-22 14:34:42 +0200 450)         } else {
fd396289 (David A. Velasco 2013-05-30 17:53:21 +0200 451)             mLastRemoteOperation = new SynchronizeFileOperation(file, null, mStorageManager, mAccount, true, false, getActivity());
b36914df (David A. Velasco 2013-05-06 10:48:43 +0200 452)             mLastRemoteOperation.execute(mAccount, getSherlockActivity(), this, mHandler, getSherlockActivity());
b36914df (David A. Velasco 2013-05-06 10:48:43 +0200 453)             
803568ab (David A. Velasco 2013-04-22 14:34:42 +0200 454)             // update ui 
803568ab (David A. Velasco 2013-04-22 14:34:42 +0200 455)             boolean inDisplayActivity = getActivity() instanceof FileDisplayActivity;
fd396289 (David A. Velasco 2013-05-30 17:53:21 +0200 456)             getActivity().showDialog(FileDisplayActivity.DIALOG_SHORT_WAIT);
803568ab (David A. Velasco 2013-04-22 14:34:42 +0200 457)             
803568ab (David A. Velasco 2013-04-22 14:34:42 +0200 458)         }
803568ab (David A. Velasco 2013-04-22 14:34:42 +0200 459)     }
803568ab (David A. Velasco 2013-04-22 14:34:42 +0200 460) 
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100 461)     @Override
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100 462)     public void onConfirmation(String callerTag) {
fd396289 (David A. Velasco 2013-05-30 17:53:21 +0200 463)         OCFile file = getFile();
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100 464)         if (callerTag.equals(FTAG_CONFIRMATION)) {
fd396289 (David A. Velasco 2013-05-30 17:53:21 +0200 465)             if (mStorageManager.getFileById(file.getFileId()) != null) {
fd396289 (David A. Velasco 2013-05-30 17:53:21 +0200 466)                 mLastRemoteOperation = new RemoveFileOperation( file, 
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100 467)                                                                 true, 
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100 468)                                                                 mStorageManager);
2946d8dd (David A. Velasco 2013-04-25 19:39:22 +0200 469)                 mLastRemoteOperation.execute(mAccount, getSherlockActivity(), this, mHandler, getSherlockActivity());
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100 470)                 
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100 471)                 boolean inDisplayActivity = getActivity() instanceof FileDisplayActivity;
fd396289 (David A. Velasco 2013-05-30 17:53:21 +0200 472)                 getActivity().showDialog(FileDisplayActivity.DIALOG_SHORT_WAIT);
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100 473)             }
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100 474)         }
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100 475)     }
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100 476)     
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100 477)     @Override
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100 478)     public void onNeutral(String callerTag) {
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100 479)         File f = null;
fd396289 (David A. Velasco 2013-05-30 17:53:21 +0200 480)         OCFile file = getFile();
fd396289 (David A. Velasco 2013-05-30 17:53:21 +0200 481)         if (file.isDown() && (f = new File(file.getStoragePath())).exists()) {
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100 482)             f.delete();
fd396289 (David A. Velasco 2013-05-30 17:53:21 +0200 483)             file.setStoragePath(null);
fd396289 (David A. Velasco 2013-05-30 17:53:21 +0200 484)             mStorageManager.saveFile(file);
fd396289 (David A. Velasco 2013-05-30 17:53:21 +0200 485)             updateFileDetails(file, mAccount);
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100 486)         }
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100 487)     }
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100 488)     
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100 489)     @Override
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100 490)     public void onCancel(String callerTag) {
2946d8dd (David A. Velasco 2013-04-25 19:39:22 +0200 491)         Log_OC.d(TAG, "REMOVAL CANCELED");
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100 492)     }
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100 493)     
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100 494)     
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100 495)     /**
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100 496)      * Check if the fragment was created with an empty layout. An empty fragment can't show file details, must be replaced.
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100 497)      * 
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100 498)      * @return  True when the fragment was created with the empty layout.
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100 499)      */
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100 500)     public boolean isEmpty() {
fd396289 (David A. Velasco 2013-05-30 17:53:21 +0200 501)         return (mLayout == R.layout.file_details_empty || getFile() == null || mAccount == null);
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100 502)     }
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100 503) 
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100 504)     
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100 505)     /**
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100 506)      * Use this method to signal this Activity that it shall update its view.
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100 507)      * 
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100 508)      * @param file : An {@link OCFile}
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100 509)      */
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100 510)     public void updateFileDetails(OCFile file, Account ocAccount) {
fd396289 (David A. Velasco 2013-05-30 17:53:21 +0200 511)         setFile(file);
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100 512)         if (ocAccount != null && ( 
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100 513)                 mStorageManager == null || 
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100 514)                 (mAccount != null && !mAccount.equals(ocAccount))
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100 515)            )) {
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100 516)             mStorageManager = new FileDataStorageManager(ocAccount, getActivity().getApplicationContext().getContentResolver());
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100 517)         }
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100 518)         mAccount = ocAccount;
c3ca5b5a (David A. Velasco 2013-04-15 13:12:04 +0200 519)         updateFileDetails(false, false);
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100 520)     }
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100 521) 
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100 522)     /**
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100 523)      * Updates the view with all relevant details about that file.
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100 524)      *
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100 525)      * TODO Remove parameter when the transferring state of files is kept in database. 
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100 526)      * 
c3ca5b5a (David A. Velasco 2013-04-15 13:12:04 +0200 527)      * TODO REFACTORING! this method called 5 times before every time the fragment is shown! 
c3ca5b5a (David A. Velasco 2013-04-15 13:12:04 +0200 528)      * 
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100 529)      * @param transferring      Flag signaling if the file should be considered as downloading or uploading, 
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100 530)      *                          although {@link FileDownloaderBinder#isDownloading(Account, OCFile)}  and 
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100 531)      *                          {@link FileUploaderBinder#isUploading(Account, OCFile)} return false.
c3ca5b5a (David A. Velasco 2013-04-15 13:12:04 +0200 532)      *                          
c3ca5b5a (David A. Velasco 2013-04-15 13:12:04 +0200 533)      * @param refresh           If 'true', try to refresh the hold file from the database
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100 534)      */
c3ca5b5a (David A. Velasco 2013-04-15 13:12:04 +0200 535)     public void updateFileDetails(boolean transferring, boolean refresh) {
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100 536) 
c3ca5b5a (David A. Velasco 2013-04-15 13:12:04 +0200 537)         if (readyToShow()) {
c3ca5b5a (David A. Velasco 2013-04-15 13:12:04 +0200 538)             
c3ca5b5a (David A. Velasco 2013-04-15 13:12:04 +0200 539)             if (refresh && mStorageManager != null) {
fd396289 (David A. Velasco 2013-05-30 17:53:21 +0200 540)                 setFile(mStorageManager.getFileByPath(getFile().getRemotePath()));
c3ca5b5a (David A. Velasco 2013-04-15 13:12:04 +0200 541)             }
fd396289 (David A. Velasco 2013-05-30 17:53:21 +0200 542)             OCFile file = getFile();
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100 543)             
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100 544)             // set file details
fd396289 (David A. Velasco 2013-05-30 17:53:21 +0200 545)             setFilename(file.getFileName());
fd396289 (David A. Velasco 2013-05-30 17:53:21 +0200 546)             setFiletype(file.getMimetype());
fd396289 (David A. Velasco 2013-05-30 17:53:21 +0200 547)             setFilesize(file.getFileLength());
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100 548)             if(ocVersionSupportsTimeCreated()){
fd396289 (David A. Velasco 2013-05-30 17:53:21 +0200 549)                 setTimeCreated(file.getCreationTimestamp());
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100 550)             }
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100 551)            
fd396289 (David A. Velasco 2013-05-30 17:53:21 +0200 552)             setTimeModified(file.getModificationTimestamp());
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100 553)             
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100 554)             CheckBox cb = (CheckBox)getView().findViewById(R.id.fdKeepInSync);
fd396289 (David A. Velasco 2013-05-30 17:53:21 +0200 555)             cb.setChecked(file.keepInSync());
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100 556) 
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100 557)             // configure UI for depending upon local state of the file
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100 558)             //if (FileDownloader.isDownloading(mAccount, mFile.getRemotePath()) || FileUploader.isUploading(mAccount, mFile.getRemotePath())) {
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100 559)             FileDownloaderBinder downloaderBinder = mContainerActivity.getFileDownloaderBinder();
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100 560)             FileUploaderBinder uploaderBinder = mContainerActivity.getFileUploaderBinder();
fd396289 (David A. Velasco 2013-05-30 17:53:21 +0200 561)             if (transferring || (downloaderBinder != null && downloaderBinder.isDownloading(mAccount, file)) || (uploaderBinder != null && uploaderBinder.isUploading(mAccount, file))) {
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100 562)                 setButtonsForTransferring();
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100 563)                 
fd396289 (David A. Velasco 2013-05-30 17:53:21 +0200 564)             } else if (file.isDown()) {
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100 565)                 
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100 566)                 setButtonsForDown();
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100 567)                 
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100 568)             } else {
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100 569)                 // TODO load default preview image; when the local file is removed, the preview remains there
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100 570)                 setButtonsForRemote();
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100 571)             }
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100 572)         }
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100 573)         getView().invalidate();
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100 574)     }
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100 575)     
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100 576)     /**
c3ca5b5a (David A. Velasco 2013-04-15 13:12:04 +0200 577)      * Checks if the fragment is ready to show details of a OCFile
c3ca5b5a (David A. Velasco 2013-04-15 13:12:04 +0200 578)      *  
c3ca5b5a (David A. Velasco 2013-04-15 13:12:04 +0200 579)      * @return  'True' when the fragment is ready to show details of a file
c3ca5b5a (David A. Velasco 2013-04-15 13:12:04 +0200 580)      */
c3ca5b5a (David A. Velasco 2013-04-15 13:12:04 +0200 581)     private boolean readyToShow() {
fd396289 (David A. Velasco 2013-05-30 17:53:21 +0200 582)         return (getFile() != null && mAccount != null && mLayout == R.layout.file_details_fragment);        
c3ca5b5a (David A. Velasco 2013-04-15 13:12:04 +0200 583)     }
c3ca5b5a (David A. Velasco 2013-04-15 13:12:04 +0200 584) 
c3ca5b5a (David A. Velasco 2013-04-15 13:12:04 +0200 585) 
c3ca5b5a (David A. Velasco 2013-04-15 13:12:04 +0200 586)     /**
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100 587)      * Updates the filename in view
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100 588)      * @param filename to set
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100 589)      */
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100 590)     private void setFilename(String filename) {
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100 591)         TextView tv = (TextView) getView().findViewById(R.id.fdFilename);
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100 592)         if (tv != null)
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100 593)             tv.setText(filename);
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100 594)     }
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100 595) 
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100 596)     /**
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100 597)      * Updates the MIME type in view
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100 598)      * @param mimetype to set
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100 599)      */
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100 600)     private void setFiletype(String mimetype) {
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100 601)         TextView tv = (TextView) getView().findViewById(R.id.fdType);
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100 602)         if (tv != null) {
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100 603)             String printableMimetype = DisplayUtils.convertMIMEtoPrettyPrint(mimetype);;        
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100 604)             tv.setText(printableMimetype);
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100 605)         }
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100 606)         ImageView iv = (ImageView) getView().findViewById(R.id.fdIcon);
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100 607)         if (iv != null) {
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100 608)             iv.setImageResource(DisplayUtils.getResourceId(mimetype));
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100 609)         }
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100 610)     }
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100 611) 
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100 612)     /**
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100 613)      * Updates the file size in view
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100 614)      * @param filesize in bytes to set
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100 615)      */
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100 616)     private void setFilesize(long filesize) {
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100 617)         TextView tv = (TextView) getView().findViewById(R.id.fdSize);
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100 618)         if (tv != null)
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100 619)             tv.setText(DisplayUtils.bytesToHumanReadable(filesize));
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100 620)     }
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100 621)     
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100 622)     /**
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100 623)      * Updates the time that the file was created in view
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100 624)      * @param milliseconds Unix time to set
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100 625)      */
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100 626)     private void setTimeCreated(long milliseconds){
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100 627)         TextView tv = (TextView) getView().findViewById(R.id.fdCreated);
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100 628)         TextView tvLabel = (TextView) getView().findViewById(R.id.fdCreatedLabel);
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100 629)         if(tv != null){
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100 630)             tv.setText(DisplayUtils.unixTimeToHumanReadable(milliseconds));
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100 631)             tv.setVisibility(View.VISIBLE);
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100 632)             tvLabel.setVisibility(View.VISIBLE);
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100 633)         }
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100 634)     }
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100 635)     
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100 636)     /**
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100 637)      * Updates the time that the file was last modified
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100 638)      * @param milliseconds Unix time to set
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100 639)      */
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100 640)     private void setTimeModified(long milliseconds){
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100 641)         TextView tv = (TextView) getView().findViewById(R.id.fdModified);
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100 642)         if(tv != null){
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100 643)             tv.setText(DisplayUtils.unixTimeToHumanReadable(milliseconds));
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100 644)         }
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100 645)     }
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100 646)     
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100 647)     /**
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100 648)      * Enables or disables buttons for a file being downloaded
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100 649)      */
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100 650)     private void setButtonsForTransferring() {
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100 651)         if (!isEmpty()) {
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100 652)             // let's protect the user from himself ;)
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100 653)             getView().findViewById(R.id.fdKeepInSync).setEnabled(false);
c3ca5b5a (David A. Velasco 2013-04-15 13:12:04 +0200 654)             
c3ca5b5a (David A. Velasco 2013-04-15 13:12:04 +0200 655)             // show the progress bar for the transfer
803568ab (David A. Velasco 2013-04-22 14:34:42 +0200 656)             getView().findViewById(R.id.fdProgressBlock).setVisibility(View.VISIBLE);
c3ca5b5a (David A. Velasco 2013-04-15 13:12:04 +0200 657)             TextView progressText = (TextView)getView().findViewById(R.id.fdProgressText);
c3ca5b5a (David A. Velasco 2013-04-15 13:12:04 +0200 658)             progressText.setVisibility(View.VISIBLE);
c3ca5b5a (David A. Velasco 2013-04-15 13:12:04 +0200 659)             FileDownloaderBinder downloaderBinder = mContainerActivity.getFileDownloaderBinder();
c3ca5b5a (David A. Velasco 2013-04-15 13:12:04 +0200 660)             FileUploaderBinder uploaderBinder = mContainerActivity.getFileUploaderBinder();
fd396289 (David A. Velasco 2013-05-30 17:53:21 +0200 661)             if (downloaderBinder != null && downloaderBinder.isDownloading(mAccount, getFile())) {
c3ca5b5a (David A. Velasco 2013-04-15 13:12:04 +0200 662)                 progressText.setText(R.string.downloader_download_in_progress_ticker);
fd396289 (David A. Velasco 2013-05-30 17:53:21 +0200 663)             } else if (uploaderBinder != null && uploaderBinder.isUploading(mAccount, getFile())) {
c3ca5b5a (David A. Velasco 2013-04-15 13:12:04 +0200 664)                 progressText.setText(R.string.uploader_upload_in_progress_ticker);
c3ca5b5a (David A. Velasco 2013-04-15 13:12:04 +0200 665)             }
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100 666)         }
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100 667)     }
c3ca5b5a (David A. Velasco 2013-04-15 13:12:04 +0200 668) 
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100 669)     /**
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100 670)      * Enables or disables buttons for a file locally available 
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100 671)      */
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100 672)     private void setButtonsForDown() {
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100 673)         if (!isEmpty()) {
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100 674)             getView().findViewById(R.id.fdKeepInSync).setEnabled(true);
c3ca5b5a (David A. Velasco 2013-04-15 13:12:04 +0200 675)             
c3ca5b5a (David A. Velasco 2013-04-15 13:12:04 +0200 676)             // hides the progress bar
803568ab (David A. Velasco 2013-04-22 14:34:42 +0200 677)             getView().findViewById(R.id.fdProgressBlock).setVisibility(View.GONE);
c3ca5b5a (David A. Velasco 2013-04-15 13:12:04 +0200 678)             TextView progressText = (TextView)getView().findViewById(R.id.fdProgressText);
c3ca5b5a (David A. Velasco 2013-04-15 13:12:04 +0200 679)             progressText.setVisibility(View.GONE);
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100 680)         }
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100 681)     }
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100 682) 
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100 683)     /**
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100 684)      * Enables or disables buttons for a file not locally available 
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100 685)      */
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100 686)     private void setButtonsForRemote() {
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100 687)         if (!isEmpty()) {
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100 688)             getView().findViewById(R.id.fdKeepInSync).setEnabled(true);
c3ca5b5a (David A. Velasco 2013-04-15 13:12:04 +0200 689)             
c3ca5b5a (David A. Velasco 2013-04-15 13:12:04 +0200 690)             // hides the progress bar
803568ab (David A. Velasco 2013-04-22 14:34:42 +0200 691)             getView().findViewById(R.id.fdProgressBlock).setVisibility(View.GONE);
c3ca5b5a (David A. Velasco 2013-04-15 13:12:04 +0200 692)             TextView progressText = (TextView)getView().findViewById(R.id.fdProgressText);
c3ca5b5a (David A. Velasco 2013-04-15 13:12:04 +0200 693)             progressText.setVisibility(View.GONE);
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100 694)         }
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100 695)     }
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100 696)     
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100 697) 
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100 698)     /**
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100 699)      * In ownCloud 3.X.X and 4.X.X there is a bug that SabreDAV does not return
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100 700)      * the time that the file was created. There is a chance that this will
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100 701)      * be fixed in future versions. Use this method to check if this version of
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100 702)      * ownCloud has this fix.
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100 703)      * @return True, if ownCloud the ownCloud version is supporting creation time
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100 704)      */
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100 705)     private boolean ocVersionSupportsTimeCreated(){
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100 706)         /*if(mAccount != null){
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100 707)             AccountManager accManager = (AccountManager) getActivity().getSystemService(Context.ACCOUNT_SERVICE);
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100 708)             OwnCloudVersion ocVersion = new OwnCloudVersion(accManager
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100 709)                     .getUserData(mAccount, AccountAuthenticator.KEY_OC_VERSION));
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100 710)             if(ocVersion.compareTo(new OwnCloudVersion(0x030000)) < 0) {
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100 711)                 return true;
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100 712)             }
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100 713)         }*/
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100 714)         return false;
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100 715)     }
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100 716)     
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100 717) 
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100 718)     /**
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100 719)      * Once the file upload has finished -> update view
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100 720)      * 
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100 721)      * Being notified about the finish of an upload is necessary for the next sequence:
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100 722)      *   1. Upload a big file.
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100 723)      *   2. Force a synchronization; if it finished before the upload, the file in transfer will be included in the local database and in the file list
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100 724)      *      of its containing folder; the the server includes it in the PROPFIND requests although it's not fully upload. 
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100 725)      *   3. Click the file in the list to see its details.
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100 726)      *   4. Wait for the upload finishes; at this moment, the details view must be refreshed to enable the action buttons.
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100 727)      */
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100 728)     private class UploadFinishReceiver extends BroadcastReceiver {
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100 729)         @Override
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100 730)         public void onReceive(Context context, Intent intent) {
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100 731)             String accountName = intent.getStringExtra(FileUploader.ACCOUNT_NAME);
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100 732) 
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100 733)             if (!isEmpty() && accountName.equals(mAccount.name)) {
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100 734)                 boolean uploadWasFine = intent.getBooleanExtra(FileUploader.EXTRA_UPLOAD_RESULT, false);
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100 735)                 String uploadRemotePath = intent.getStringExtra(FileUploader.EXTRA_REMOTE_PATH);
fd396289 (David A. Velasco 2013-05-30 17:53:21 +0200 736)                 boolean renamedInUpload = getFile().getRemotePath().equals(intent.getStringExtra(FileUploader.EXTRA_OLD_REMOTE_PATH));
fd396289 (David A. Velasco 2013-05-30 17:53:21 +0200 737)                 if (getFile().getRemotePath().equals(uploadRemotePath) ||
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100 738)                     renamedInUpload) {
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100 739)                     if (uploadWasFine) {
fd396289 (David A. Velasco 2013-05-30 17:53:21 +0200 740)                         setFile(mStorageManager.getFileByPath(uploadRemotePath));
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100 741)                     }
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100 742)                     if (renamedInUpload) {
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100 743)                         String newName = (new File(uploadRemotePath)).getName();
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100 744)                         Toast msg = Toast.makeText(getActivity().getApplicationContext(), String.format(getString(R.string.filedetails_renamed_in_upload_msg), newName), Toast.LENGTH_LONG);
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100 745)                         msg.show();
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100 746)                     }
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100 747)                     getSherlockActivity().removeStickyBroadcast(intent);    // not the best place to do this; a small refactorization of BroadcastReceivers should be done
c3ca5b5a (David A. Velasco 2013-04-15 13:12:04 +0200 748)                     updateFileDetails(false, false);    // it updates the buttons; must be called although !uploadWasFine; interrupted uploads still leave an incomplete file in the server
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100 749)                 }
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100 750)             }
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100 751)         }
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100 752)     }
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100 753)     
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100 754) 
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100 755)     public void onDismiss(EditNameDialog dialog) {
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100 756)         if (dialog.getResult()) {
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100 757)             String newFilename = dialog.getNewFilename();
2946d8dd (David A. Velasco 2013-04-25 19:39:22 +0200 758)             Log_OC.d(TAG, "name edit dialog dismissed with new name " + newFilename);
fd396289 (David A. Velasco 2013-05-30 17:53:21 +0200 759)             mLastRemoteOperation = new RenameFileOperation( getFile(), 
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100 760)                                                             mAccount, 
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100 761)                                                             newFilename, 
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100 762)                                                             new FileDataStorageManager(mAccount, getActivity().getContentResolver()));
2946d8dd (David A. Velasco 2013-04-25 19:39:22 +0200 763)             mLastRemoteOperation.execute(mAccount, getSherlockActivity(), this, mHandler, getSherlockActivity());
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100 764)             boolean inDisplayActivity = getActivity() instanceof FileDisplayActivity;
fd396289 (David A. Velasco 2013-05-30 17:53:21 +0200 765)             getActivity().showDialog(FileDisplayActivity.DIALOG_SHORT_WAIT);
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100 766)         }
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100 767)     }
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100 768)     
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100 769)     
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100 770)     /**
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100 771)      * {@inheritDoc}
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100 772)      */
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100 773)     @Override
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100 774)     public void onRemoteOperationFinish(RemoteOperation operation, RemoteOperationResult result) {
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100 775)         if (operation.equals(mLastRemoteOperation)) {
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100 776)             if (operation instanceof RemoveFileOperation) {
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100 777)                 onRemoveFileOperationFinish((RemoveFileOperation)operation, result);
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100 778)                 
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100 779)             } else if (operation instanceof RenameFileOperation) {
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100 780)                 onRenameFileOperationFinish((RenameFileOperation)operation, result);
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100 781)                 
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100 782)             } else if (operation instanceof SynchronizeFileOperation) {
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100 783)                 onSynchronizeFileOperationFinish((SynchronizeFileOperation)operation, result);
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100 784)             }
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100 785)         }
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100 786)     }
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100 787)     
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100 788)     
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100 789)     private void onRemoveFileOperationFinish(RemoveFileOperation operation, RemoteOperationResult result) {
fd396289 (David A. Velasco 2013-05-30 17:53:21 +0200 790)         getActivity().dismissDialog(FileDisplayActivity.DIALOG_SHORT_WAIT);
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100 791)         
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100 792)         if (result.isSuccess()) {
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100 793)             Toast msg = Toast.makeText(getActivity().getApplicationContext(), R.string.remove_success_msg, Toast.LENGTH_LONG);
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100 794)             msg.show();
c70f3333 (David A. Velasco 2013-06-04 14:30:29 +0200 795)             ((FileDisplayActivity)getActivity()).cleanSecondFragment();
c70f3333 (David A. Velasco 2013-06-04 14:30:29 +0200 796) 
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100 797)         } else {
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100 798)             Toast msg = Toast.makeText(getActivity(), R.string.remove_fail_msg, Toast.LENGTH_LONG); 
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100 799)             msg.show();
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100 800)             if (result.isSslRecoverableException()) {
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100 801)                 // TODO show the SSL warning dialog
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100 802)             }
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100 803)         }
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100 804)     }
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100 805)     
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100 806)     private void onRenameFileOperationFinish(RenameFileOperation operation, RemoteOperationResult result) {
fd396289 (David A. Velasco 2013-05-30 17:53:21 +0200 807)         getActivity().dismissDialog(FileDisplayActivity.DIALOG_SHORT_WAIT);
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100 808)         
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100 809)         if (result.isSuccess()) {
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100 810)             updateFileDetails(((RenameFileOperation)operation).getFile(), mAccount);
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100 811)             mContainerActivity.onFileStateChanged();
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100 812)             
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100 813)         } else {
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100 814)             if (result.getCode().equals(ResultCode.INVALID_LOCAL_FILE_NAME)) {
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100 815)                 Toast msg = Toast.makeText(getActivity(), R.string.rename_local_fail_msg, Toast.LENGTH_LONG); 
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100 816)                 msg.show();
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100 817)                 // TODO throw again the new rename dialog
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100 818)             } else {
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100 819)                 Toast msg = Toast.makeText(getActivity(), R.string.rename_server_fail_msg, Toast.LENGTH_LONG); 
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100 820)                 msg.show();
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100 821)                 if (result.isSslRecoverableException()) {
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100 822)                     // TODO show the SSL warning dialog
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100 823)                 }
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100 824)             }
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100 825)         }
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100 826)     }
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100 827)     
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100 828)     private void onSynchronizeFileOperationFinish(SynchronizeFileOperation operation, RemoteOperationResult result) {
fd396289 (David A. Velasco 2013-05-30 17:53:21 +0200 829)         getActivity().dismissDialog(FileDisplayActivity.DIALOG_SHORT_WAIT);
fd396289 (David A. Velasco 2013-05-30 17:53:21 +0200 830)         OCFile file = getFile();
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100 831)         if (!result.isSuccess()) {
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100 832)             if (result.getCode() == ResultCode.SYNC_CONFLICT) {
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100 833)                 Intent i = new Intent(getActivity(), ConflictsResolveActivity.class);
fd396289 (David A. Velasco 2013-05-30 17:53:21 +0200 834)                 i.putExtra(ConflictsResolveActivity.EXTRA_FILE, file);
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100 835)                 i.putExtra(ConflictsResolveActivity.EXTRA_ACCOUNT, mAccount);
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100 836)                 startActivity(i);
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100 837)                 
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100 838)             } else {
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100 839)                 Toast msg = Toast.makeText(getActivity(), R.string.sync_file_fail_msg, Toast.LENGTH_LONG); 
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100 840)                 msg.show();
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100 841)             }
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100 842)             
fd396289 (David A. Velasco 2013-05-30 17:53:21 +0200 843)             if (file.isDown()) {
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100 844)                 setButtonsForDown();
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100 845)                 
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100 846)             } else {
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100 847)                 setButtonsForRemote();
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100 848)             }
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100 849)             
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100 850)         } else {
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100 851)             if (operation.transferWasRequested()) {
c3ca5b5a (David A. Velasco 2013-04-15 13:12:04 +0200 852)                 setButtonsForTransferring();
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100 853)                 mContainerActivity.onFileStateChanged();    // this is not working; FileDownloader won't do NOTHING at all until this method finishes, so 
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100 854)                                                             // checking the service to see if the file is downloading results in FALSE
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100 855)             } else {
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100 856)                 Toast msg = Toast.makeText(getActivity(), R.string.sync_file_nothing_to_do_msg, Toast.LENGTH_LONG); 
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100 857)                 msg.show();
fd396289 (David A. Velasco 2013-05-30 17:53:21 +0200 858)                 if (file.isDown()) {
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100 859)                     setButtonsForDown();
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100 860)                     
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100 861)                 } else {
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100 862)                     setButtonsForRemote();
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100 863)                 }
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100 864)             }
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100 865)         }
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100 866)     }
c3ca5b5a (David A. Velasco 2013-04-15 13:12:04 +0200 867)     
2946d8dd (David A. Velasco 2013-04-25 19:39:22 +0200 868) 
c3ca5b5a (David A. Velasco 2013-04-15 13:12:04 +0200 869)     public void listenForTransferProgress() {
c3ca5b5a (David A. Velasco 2013-04-15 13:12:04 +0200 870)         if (mProgressListener != null) {
c3ca5b5a (David A. Velasco 2013-04-15 13:12:04 +0200 871)             if (mContainerActivity.getFileDownloaderBinder() != null) {
fd396289 (David A. Velasco 2013-05-30 17:53:21 +0200 872)                 mContainerActivity.getFileDownloaderBinder().addDatatransferProgressListener(mProgressListener, mAccount, getFile());
c3ca5b5a (David A. Velasco 2013-04-15 13:12:04 +0200 873)             }
c3ca5b5a (David A. Velasco 2013-04-15 13:12:04 +0200 874)             if (mContainerActivity.getFileUploaderBinder() != null) {
fd396289 (David A. Velasco 2013-05-30 17:53:21 +0200 875)                 mContainerActivity.getFileUploaderBinder().addDatatransferProgressListener(mProgressListener, mAccount, getFile());
c3ca5b5a (David A. Velasco 2013-04-15 13:12:04 +0200 876)             }
c3ca5b5a (David A. Velasco 2013-04-15 13:12:04 +0200 877)         }
c3ca5b5a (David A. Velasco 2013-04-15 13:12:04 +0200 878)     }
c3ca5b5a (David A. Velasco 2013-04-15 13:12:04 +0200 879)     
c3ca5b5a (David A. Velasco 2013-04-15 13:12:04 +0200 880)     
c3ca5b5a (David A. Velasco 2013-04-15 13:12:04 +0200 881)     public void leaveTransferProgress() {
c3ca5b5a (David A. Velasco 2013-04-15 13:12:04 +0200 882)         if (mProgressListener != null) {
c3ca5b5a (David A. Velasco 2013-04-15 13:12:04 +0200 883)             if (mContainerActivity.getFileDownloaderBinder() != null) {
fd396289 (David A. Velasco 2013-05-30 17:53:21 +0200 884)                 mContainerActivity.getFileDownloaderBinder().removeDatatransferProgressListener(mProgressListener, mAccount, getFile());
c3ca5b5a (David A. Velasco 2013-04-15 13:12:04 +0200 885)             }
c3ca5b5a (David A. Velasco 2013-04-15 13:12:04 +0200 886)             if (mContainerActivity.getFileUploaderBinder() != null) {
fd396289 (David A. Velasco 2013-05-30 17:53:21 +0200 887)                 mContainerActivity.getFileUploaderBinder().removeDatatransferProgressListener(mProgressListener, mAccount, getFile());
c3ca5b5a (David A. Velasco 2013-04-15 13:12:04 +0200 888)             }
c3ca5b5a (David A. Velasco 2013-04-15 13:12:04 +0200 889)         }
c3ca5b5a (David A. Velasco 2013-04-15 13:12:04 +0200 890)     }
c3ca5b5a (David A. Velasco 2013-04-15 13:12:04 +0200 891) 
c3ca5b5a (David A. Velasco 2013-04-15 13:12:04 +0200 892) 
c3ca5b5a (David A. Velasco 2013-04-15 13:12:04 +0200 893)     
c3ca5b5a (David A. Velasco 2013-04-15 13:12:04 +0200 894)     /**
c3ca5b5a (David A. Velasco 2013-04-15 13:12:04 +0200 895)      * Helper class responsible for updating the progress bar shown for file uploading or downloading  
c3ca5b5a (David A. Velasco 2013-04-15 13:12:04 +0200 896)      * 
c3ca5b5a (David A. Velasco 2013-04-15 13:12:04 +0200 897)      * @author David A. Velasco
c3ca5b5a (David A. Velasco 2013-04-15 13:12:04 +0200 898)      */
c3ca5b5a (David A. Velasco 2013-04-15 13:12:04 +0200 899)     private class ProgressListener implements OnDatatransferProgressListener {
c3ca5b5a (David A. Velasco 2013-04-15 13:12:04 +0200 900)         int mLastPercent = 0;
c3ca5b5a (David A. Velasco 2013-04-15 13:12:04 +0200 901)         WeakReference<ProgressBar> mProgressBar = null;
c3ca5b5a (David A. Velasco 2013-04-15 13:12:04 +0200 902)         
c3ca5b5a (David A. Velasco 2013-04-15 13:12:04 +0200 903)         ProgressListener(ProgressBar progressBar) {
c3ca5b5a (David A. Velasco 2013-04-15 13:12:04 +0200 904)             mProgressBar = new WeakReference<ProgressBar>(progressBar);
c3ca5b5a (David A. Velasco 2013-04-15 13:12:04 +0200 905)         }
c3ca5b5a (David A. Velasco 2013-04-15 13:12:04 +0200 906)         
c3ca5b5a (David A. Velasco 2013-04-15 13:12:04 +0200 907)         @Override
c3ca5b5a (David A. Velasco 2013-04-15 13:12:04 +0200 908)         public void onTransferProgress(long progressRate) {
c3ca5b5a (David A. Velasco 2013-04-15 13:12:04 +0200 909)             // old method, nothing here
c3ca5b5a (David A. Velasco 2013-04-15 13:12:04 +0200 910)         };
c3ca5b5a (David A. Velasco 2013-04-15 13:12:04 +0200 911) 
c3ca5b5a (David A. Velasco 2013-04-15 13:12:04 +0200 912)         @Override
c3ca5b5a (David A. Velasco 2013-04-15 13:12:04 +0200 913)         public void onTransferProgress(long progressRate, long totalTransferredSoFar, long totalToTransfer, String filename) {
c3ca5b5a (David A. Velasco 2013-04-15 13:12:04 +0200 914)             int percent = (int)(100.0*((double)totalTransferredSoFar)/((double)totalToTransfer));
c3ca5b5a (David A. Velasco 2013-04-15 13:12:04 +0200 915)             if (percent != mLastPercent) {
c3ca5b5a (David A. Velasco 2013-04-15 13:12:04 +0200 916)                 ProgressBar pb = mProgressBar.get();
c3ca5b5a (David A. Velasco 2013-04-15 13:12:04 +0200 917)                 if (pb != null) {
c3ca5b5a (David A. Velasco 2013-04-15 13:12:04 +0200 918)                     pb.setProgress(percent);
c3ca5b5a (David A. Velasco 2013-04-15 13:12:04 +0200 919)                     pb.postInvalidate();
c3ca5b5a (David A. Velasco 2013-04-15 13:12:04 +0200 920)                 }
c3ca5b5a (David A. Velasco 2013-04-15 13:12:04 +0200 921)             }
c3ca5b5a (David A. Velasco 2013-04-15 13:12:04 +0200 922)             mLastPercent = percent;
c3ca5b5a (David A. Velasco 2013-04-15 13:12:04 +0200 923)         }
c3ca5b5a (David A. Velasco 2013-04-15 13:12:04 +0200 924) 
c3ca5b5a (David A. Velasco 2013-04-15 13:12:04 +0200 925)     };
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100 926) 
2946d8dd (David A. Velasco 2013-04-25 19:39:22 +0200 927) }
