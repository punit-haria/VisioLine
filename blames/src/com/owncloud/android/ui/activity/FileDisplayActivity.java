f7649804 (Matthias Baumann 2013-02-25 08:21:33 +0100    1) /* ownCloud Android client application
f7649804 (Matthias Baumann 2013-02-25 08:21:33 +0100    2)  *   Copyright (C) 2011  Bartek Przybylski
f7649804 (Matthias Baumann 2013-02-25 08:21:33 +0100    3)  *   Copyright (C) 2012-2013 ownCloud Inc.
f7649804 (Matthias Baumann 2013-02-25 08:21:33 +0100    4)  *
f7649804 (Matthias Baumann 2013-02-25 08:21:33 +0100    5)  *   This program is free software: you can redistribute it and/or modify
bdc0332c (David A. Velasco 2013-04-17 12:26:13 +0200    6)  *   it under the terms of the GNU General Public License version 2,
bdc0332c (David A. Velasco 2013-04-17 12:26:13 +0200    7)  *   as published by the Free Software Foundation.
f7649804 (Matthias Baumann 2013-02-25 08:21:33 +0100    8)  *
f7649804 (Matthias Baumann 2013-02-25 08:21:33 +0100    9)  *   This program is distributed in the hope that it will be useful,
f7649804 (Matthias Baumann 2013-02-25 08:21:33 +0100   10)  *   but WITHOUT ANY WARRANTY; without even the implied warranty of
f7649804 (Matthias Baumann 2013-02-25 08:21:33 +0100   11)  *   MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
f7649804 (Matthias Baumann 2013-02-25 08:21:33 +0100   12)  *   GNU General Public License for more details.
f7649804 (Matthias Baumann 2013-02-25 08:21:33 +0100   13)  *
f7649804 (Matthias Baumann 2013-02-25 08:21:33 +0100   14)  *   You should have received a copy of the GNU General Public License
f7649804 (Matthias Baumann 2013-02-25 08:21:33 +0100   15)  *   along with this program.  If not, see <http://www.gnu.org/licenses/>.
f7649804 (Matthias Baumann 2013-02-25 08:21:33 +0100   16)  *
f7649804 (Matthias Baumann 2013-02-25 08:21:33 +0100   17)  */
f7649804 (Matthias Baumann 2013-02-25 08:21:33 +0100   18) 
f7649804 (Matthias Baumann 2013-02-25 08:21:33 +0100   19) package com.owncloud.android.ui.activity;
f7649804 (Matthias Baumann 2013-02-25 08:21:33 +0100   20) 
f7649804 (Matthias Baumann 2013-02-25 08:21:33 +0100   21) import java.io.File;
f7649804 (Matthias Baumann 2013-02-25 08:21:33 +0100   22) 
f7649804 (Matthias Baumann 2013-02-25 08:21:33 +0100   23) import android.accounts.Account;
f7649804 (Matthias Baumann 2013-02-25 08:21:33 +0100   24) import android.app.AlertDialog;
8aeb5243 (David A. Velasco 2013-04-12 12:34:17 +0200   25) import android.app.ProgressDialog;
f7649804 (Matthias Baumann 2013-02-25 08:21:33 +0100   26) import android.app.Dialog;
f7649804 (Matthias Baumann 2013-02-25 08:21:33 +0100   27) import android.content.BroadcastReceiver;
f7649804 (Matthias Baumann 2013-02-25 08:21:33 +0100   28) import android.content.ComponentName;
f7649804 (Matthias Baumann 2013-02-25 08:21:33 +0100   29) import android.content.ContentResolver;
f7649804 (Matthias Baumann 2013-02-25 08:21:33 +0100   30) import android.content.Context;
f7649804 (Matthias Baumann 2013-02-25 08:21:33 +0100   31) import android.content.DialogInterface;
f7649804 (Matthias Baumann 2013-02-25 08:21:33 +0100   32) import android.content.Intent;
f7649804 (Matthias Baumann 2013-02-25 08:21:33 +0100   33) import android.content.IntentFilter;
f7649804 (Matthias Baumann 2013-02-25 08:21:33 +0100   34) import android.content.ServiceConnection;
f7649804 (Matthias Baumann 2013-02-25 08:21:33 +0100   35) import android.content.SharedPreferences;
f7649804 (Matthias Baumann 2013-02-25 08:21:33 +0100   36) import android.content.res.Resources.NotFoundException;
f7649804 (Matthias Baumann 2013-02-25 08:21:33 +0100   37) import android.database.Cursor;
f7649804 (Matthias Baumann 2013-02-25 08:21:33 +0100   38) import android.net.Uri;
f7649804 (Matthias Baumann 2013-02-25 08:21:33 +0100   39) import android.os.Bundle;
f7649804 (Matthias Baumann 2013-02-25 08:21:33 +0100   40) import android.os.Handler;
f7649804 (Matthias Baumann 2013-02-25 08:21:33 +0100   41) import android.os.IBinder;
f7649804 (Matthias Baumann 2013-02-25 08:21:33 +0100   42) import android.preference.PreferenceManager;
f7649804 (Matthias Baumann 2013-02-25 08:21:33 +0100   43) import android.provider.MediaStore;
56f1df02 (David A. Velasco 2013-04-12 12:46:18 +0200   44) import android.support.v4.app.Fragment;
f7649804 (Matthias Baumann 2013-02-25 08:21:33 +0100   45) import android.support.v4.app.FragmentTransaction;
c70f3333 (David A. Velasco 2013-06-04 14:30:29 +0200   46) import android.util.Log;
f7649804 (Matthias Baumann 2013-02-25 08:21:33 +0100   47) import android.view.View;
f7649804 (Matthias Baumann 2013-02-25 08:21:33 +0100   48) import android.view.ViewGroup;
f7649804 (Matthias Baumann 2013-02-25 08:21:33 +0100   49) import android.widget.ArrayAdapter;
f7649804 (Matthias Baumann 2013-02-25 08:21:33 +0100   50) import android.widget.TextView;
f7649804 (Matthias Baumann 2013-02-25 08:21:33 +0100   51) import android.widget.Toast;
f7649804 (Matthias Baumann 2013-02-25 08:21:33 +0100   52) 
f7649804 (Matthias Baumann 2013-02-25 08:21:33 +0100   53) import com.actionbarsherlock.app.ActionBar;
f7649804 (Matthias Baumann 2013-02-25 08:21:33 +0100   54) import com.actionbarsherlock.app.ActionBar.OnNavigationListener;
f7649804 (Matthias Baumann 2013-02-25 08:21:33 +0100   55) import com.actionbarsherlock.view.Menu;
f7649804 (Matthias Baumann 2013-02-25 08:21:33 +0100   56) import com.actionbarsherlock.view.MenuInflater;
f7649804 (Matthias Baumann 2013-02-25 08:21:33 +0100   57) import com.actionbarsherlock.view.MenuItem;
f7649804 (Matthias Baumann 2013-02-25 08:21:33 +0100   58) import com.actionbarsherlock.view.Window;
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100   59) import com.owncloud.android.Log_OC;
f7649804 (Matthias Baumann 2013-02-25 08:21:33 +0100   60) import com.owncloud.android.R;
2946d8dd (David A. Velasco 2013-04-25 19:39:22 +0200   61) import com.owncloud.android.authentication.AccountAuthenticator;
f7649804 (Matthias Baumann 2013-02-25 08:21:33 +0100   62) import com.owncloud.android.datamodel.DataStorageManager;
f7649804 (Matthias Baumann 2013-02-25 08:21:33 +0100   63) import com.owncloud.android.datamodel.FileDataStorageManager;
f7649804 (Matthias Baumann 2013-02-25 08:21:33 +0100   64) import com.owncloud.android.datamodel.OCFile;
f7649804 (Matthias Baumann 2013-02-25 08:21:33 +0100   65) import com.owncloud.android.files.services.FileDownloader;
f7649804 (Matthias Baumann 2013-02-25 08:21:33 +0100   66) import com.owncloud.android.files.services.FileDownloader.FileDownloaderBinder;
f7649804 (Matthias Baumann 2013-02-25 08:21:33 +0100   67) import com.owncloud.android.files.services.FileObserverService;
f7649804 (Matthias Baumann 2013-02-25 08:21:33 +0100   68) import com.owncloud.android.files.services.FileUploader;
f7649804 (Matthias Baumann 2013-02-25 08:21:33 +0100   69) import com.owncloud.android.files.services.FileUploader.FileUploaderBinder;
2946d8dd (David A. Velasco 2013-04-25 19:39:22 +0200   70) import com.owncloud.android.operations.CreateFolderOperation;
f7649804 (Matthias Baumann 2013-02-25 08:21:33 +0100   71) import com.owncloud.android.operations.OnRemoteOperationListener;
f7649804 (Matthias Baumann 2013-02-25 08:21:33 +0100   72) import com.owncloud.android.operations.RemoteOperation;
f7649804 (Matthias Baumann 2013-02-25 08:21:33 +0100   73) import com.owncloud.android.operations.RemoteOperationResult;
f7649804 (Matthias Baumann 2013-02-25 08:21:33 +0100   74) import com.owncloud.android.operations.RemoveFileOperation;
f7649804 (Matthias Baumann 2013-02-25 08:21:33 +0100   75) import com.owncloud.android.operations.RenameFileOperation;
f7649804 (Matthias Baumann 2013-02-25 08:21:33 +0100   76) import com.owncloud.android.operations.SynchronizeFileOperation;
8aeb5243 (David A. Velasco 2013-04-12 12:34:17 +0200   77) import com.owncloud.android.operations.RemoteOperationResult.ResultCode;
f7649804 (Matthias Baumann 2013-02-25 08:21:33 +0100   78) import com.owncloud.android.syncadapter.FileSyncService;
8aeb5243 (David A. Velasco 2013-04-12 12:34:17 +0200   79) import com.owncloud.android.ui.dialog.EditNameDialog;
f7649804 (Matthias Baumann 2013-02-25 08:21:33 +0100   80) import com.owncloud.android.ui.dialog.SslValidatorDialog;
8aeb5243 (David A. Velasco 2013-04-12 12:34:17 +0200   81) import com.owncloud.android.ui.dialog.EditNameDialog.EditNameDialogListener;
f7649804 (Matthias Baumann 2013-02-25 08:21:33 +0100   82) import com.owncloud.android.ui.dialog.SslValidatorDialog.OnSslValidatorListener;
f7649804 (Matthias Baumann 2013-02-25 08:21:33 +0100   83) import com.owncloud.android.ui.fragment.FileDetailFragment;
56f1df02 (David A. Velasco 2013-04-12 12:46:18 +0200   84) import com.owncloud.android.ui.fragment.FileFragment;
f7649804 (Matthias Baumann 2013-02-25 08:21:33 +0100   85) import com.owncloud.android.ui.fragment.OCFileListFragment;
56f1df02 (David A. Velasco 2013-04-12 12:46:18 +0200   86) import com.owncloud.android.ui.preview.PreviewImageActivity;
56f1df02 (David A. Velasco 2013-04-12 12:46:18 +0200   87) import com.owncloud.android.ui.preview.PreviewMediaFragment;
6496b920 (David A. Velasco 2013-04-26 14:02:17 +0200   88) import com.owncloud.android.ui.preview.PreviewVideoActivity;
f7649804 (Matthias Baumann 2013-02-25 08:21:33 +0100   89) 
f7649804 (Matthias Baumann 2013-02-25 08:21:33 +0100   90) /**
f7649804 (Matthias Baumann 2013-02-25 08:21:33 +0100   91)  * Displays, what files the user has available in his ownCloud.
f7649804 (Matthias Baumann 2013-02-25 08:21:33 +0100   92)  * 
f7649804 (Matthias Baumann 2013-02-25 08:21:33 +0100   93)  * @author Bartek Przybylski
56f1df02 (David A. Velasco 2013-04-12 12:46:18 +0200   94)  * @author David A. Velasco
f7649804 (Matthias Baumann 2013-02-25 08:21:33 +0100   95)  */
f7649804 (Matthias Baumann 2013-02-25 08:21:33 +0100   96) 
f6780fdc (David A. Velasco 2013-05-07 12:54:31 +0200   97) public class FileDisplayActivity extends FileActivity implements
eee74aa5 (masensio         2013-07-04 18:47:38 +0200   98) OCFileListFragment.ContainerActivity, FileDetailFragment.ContainerActivity, OnNavigationListener, OnSslValidatorListener, OnRemoteOperationListener, EditNameDialogListener {
eee74aa5 (masensio         2013-07-04 18:47:38 +0200   99) 
f7649804 (Matthias Baumann 2013-02-25 08:21:33 +0100  100)     private ArrayAdapter<String> mDirectories;
f7649804 (Matthias Baumann 2013-02-25 08:21:33 +0100  101) 
f6780fdc (David A. Velasco 2013-05-07 12:54:31 +0200  102)     /** Access point to the cached database for the current ownCloud {@link Account} */
f6780fdc (David A. Velasco 2013-05-07 12:54:31 +0200  103)     private DataStorageManager mStorageManager = null;
eee74aa5 (masensio         2013-07-04 18:47:38 +0200  104) 
f7649804 (Matthias Baumann 2013-02-25 08:21:33 +0100  105)     private SyncBroadcastReceiver mSyncBroadcastReceiver;
f7649804 (Matthias Baumann 2013-02-25 08:21:33 +0100  106)     private UploadFinishReceiver mUploadFinishReceiver;
f7649804 (Matthias Baumann 2013-02-25 08:21:33 +0100  107)     private DownloadFinishReceiver mDownloadFinishReceiver;
f7649804 (Matthias Baumann 2013-02-25 08:21:33 +0100  108)     private FileDownloaderBinder mDownloaderBinder = null;
f7649804 (Matthias Baumann 2013-02-25 08:21:33 +0100  109)     private FileUploaderBinder mUploaderBinder = null;
f7649804 (Matthias Baumann 2013-02-25 08:21:33 +0100  110)     private ServiceConnection mDownloadConnection = null, mUploadConnection = null;
f7649804 (Matthias Baumann 2013-02-25 08:21:33 +0100  111)     private RemoteOperationResult mLastSslUntrustedServerResult = null;
eee74aa5 (masensio         2013-07-04 18:47:38 +0200  112) 
f7649804 (Matthias Baumann 2013-02-25 08:21:33 +0100  113)     private boolean mDualPane;
c70f3333 (David A. Velasco 2013-06-04 14:30:29 +0200  114)     private View mLeftFragmentContainer;
c70f3333 (David A. Velasco 2013-06-04 14:30:29 +0200  115)     private View mRightFragmentContainer;
eee74aa5 (masensio         2013-07-04 18:47:38 +0200  116) 
fd396289 (David A. Velasco 2013-05-30 17:53:21 +0200  117)     private static final String KEY_WAITING_TO_PREVIEW = "WAITING_TO_PREVIEW";
eee74aa5 (masensio         2013-07-04 18:47:38 +0200  118) 
f6780fdc (David A. Velasco 2013-05-07 12:54:31 +0200  119)     public static final int DIALOG_SHORT_WAIT = 0;
f6780fdc (David A. Velasco 2013-05-07 12:54:31 +0200  120)     private static final int DIALOG_CHOOSE_UPLOAD_SOURCE = 1;
f6780fdc (David A. Velasco 2013-05-07 12:54:31 +0200  121)     private static final int DIALOG_SSL_VALIDATOR = 2;
f6780fdc (David A. Velasco 2013-05-07 12:54:31 +0200  122)     private static final int DIALOG_CERT_NOT_SAVED = 3;
eee74aa5 (masensio         2013-07-04 18:47:38 +0200  123) 
fd396289 (David A. Velasco 2013-05-30 17:53:21 +0200  124)     public static final String ACTION_DETAILS = "com.owncloud.android.ui.activity.action.DETAILS";
eee74aa5 (masensio         2013-07-04 18:47:38 +0200  125) 
f7649804 (Matthias Baumann 2013-02-25 08:21:33 +0100  126)     private static final int ACTION_SELECT_CONTENT_FROM_APPS = 1;
f7649804 (Matthias Baumann 2013-02-25 08:21:33 +0100  127)     private static final int ACTION_SELECT_MULTIPLE_FILES = 2;
eee74aa5 (masensio         2013-07-04 18:47:38 +0200  128) 
f6780fdc (David A. Velasco 2013-05-07 12:54:31 +0200  129)     private static final String TAG = FileDisplayActivity.class.getSimpleName();
f7649804 (Matthias Baumann 2013-02-25 08:21:33 +0100  130) 
c70f3333 (David A. Velasco 2013-06-04 14:30:29 +0200  131)     private static final String TAG_LIST_OF_FILES = "LIST_OF_FILES";
c70f3333 (David A. Velasco 2013-06-04 14:30:29 +0200  132)     private static final String TAG_SECOND_FRAGMENT = "SECOND_FRAGMENT";
c70f3333 (David A. Velasco 2013-06-04 14:30:29 +0200  133) 
56f1df02 (David A. Velasco 2013-04-12 12:46:18 +0200  134)     private OCFile mWaitingToPreview;
56f1df02 (David A. Velasco 2013-04-12 12:46:18 +0200  135)     private Handler mHandler;
c70f3333 (David A. Velasco 2013-06-04 14:30:29 +0200  136) 
f7649804 (Matthias Baumann 2013-02-25 08:21:33 +0100  137)     @Override
979bdc83 (David A. Velasco 2013-05-07 13:05:40 +0200  138)     protected void onCreate(Bundle savedInstanceState) {
f6780fdc (David A. Velasco 2013-05-07 12:54:31 +0200  139)         Log_OC.d(TAG, "onCreate() start");
f9babe7b (David A. Velasco 2013-06-14 12:53:45 +0200  140)         requestWindowFeature(Window.FEATURE_INDETERMINATE_PROGRESS);
994e81d7 (masensio         2013-07-04 18:47:38 +0200  141) 
c70f3333 (David A. Velasco 2013-06-04 14:30:29 +0200  142)         super.onCreate(savedInstanceState); // this calls onAccountChanged() when ownCloud Account is valid
994e81d7 (masensio         2013-07-04 18:47:38 +0200  143) 
2946d8dd (David A. Velasco 2013-04-25 19:39:22 +0200  144)         mHandler = new Handler();
2946d8dd (David A. Velasco 2013-04-25 19:39:22 +0200  145) 
f6780fdc (David A. Velasco 2013-05-07 12:54:31 +0200  146)         /// bindings to transference services
f7649804 (Matthias Baumann 2013-02-25 08:21:33 +0100  147)         mUploadConnection = new ListServiceConnection(); 
f7649804 (Matthias Baumann 2013-02-25 08:21:33 +0100  148)         mDownloadConnection = new ListServiceConnection();
f7649804 (Matthias Baumann 2013-02-25 08:21:33 +0100  149)         bindService(new Intent(this, FileUploader.class), mUploadConnection, Context.BIND_AUTO_CREATE);
f7649804 (Matthias Baumann 2013-02-25 08:21:33 +0100  150)         bindService(new Intent(this, FileDownloader.class), mDownloadConnection, Context.BIND_AUTO_CREATE);
f7649804 (Matthias Baumann 2013-02-25 08:21:33 +0100  151) 
f7649804 (Matthias Baumann 2013-02-25 08:21:33 +0100  152)         // PIN CODE request ;  best location is to decide, let's try this first
f7649804 (Matthias Baumann 2013-02-25 08:21:33 +0100  153)         if (getIntent().getAction() != null && getIntent().getAction().equals(Intent.ACTION_MAIN) && savedInstanceState == null) {
f7649804 (Matthias Baumann 2013-02-25 08:21:33 +0100  154)             requestPinCode();
f7649804 (Matthias Baumann 2013-02-25 08:21:33 +0100  155)         }
f7649804 (Matthias Baumann 2013-02-25 08:21:33 +0100  156) 
c70f3333 (David A. Velasco 2013-06-04 14:30:29 +0200  157)         /// file observer
f7649804 (Matthias Baumann 2013-02-25 08:21:33 +0100  158)         Intent observer_intent = new Intent(this, FileObserverService.class);
f7649804 (Matthias Baumann 2013-02-25 08:21:33 +0100  159)         observer_intent.putExtra(FileObserverService.KEY_FILE_CMD, FileObserverService.CMD_INIT_OBSERVED_LIST);
f7649804 (Matthias Baumann 2013-02-25 08:21:33 +0100  160)         startService(observer_intent);
eee74aa5 (masensio         2013-07-04 18:47:38 +0200  161) 
fd396289 (David A. Velasco 2013-05-30 17:53:21 +0200  162)         /// Load of saved instance state
fd396289 (David A. Velasco 2013-05-30 17:53:21 +0200  163)         if(savedInstanceState != null) {
fd396289 (David A. Velasco 2013-05-30 17:53:21 +0200  164)             mWaitingToPreview = (OCFile) savedInstanceState.getParcelable(FileDisplayActivity.KEY_WAITING_TO_PREVIEW);
fd396289 (David A. Velasco 2013-05-30 17:53:21 +0200  165) 
fd396289 (David A. Velasco 2013-05-30 17:53:21 +0200  166)         } else {
fd396289 (David A. Velasco 2013-05-30 17:53:21 +0200  167)             mWaitingToPreview = null;
fd396289 (David A. Velasco 2013-05-30 17:53:21 +0200  168)         }
eee74aa5 (masensio         2013-07-04 18:47:38 +0200  169) 
f7649804 (Matthias Baumann 2013-02-25 08:21:33 +0100  170)         /// USER INTERFACE
eee74aa5 (masensio         2013-07-04 18:47:38 +0200  171) 
f7649804 (Matthias Baumann 2013-02-25 08:21:33 +0100  172)         // Inflate and set the layout view
f7649804 (Matthias Baumann 2013-02-25 08:21:33 +0100  173)         setContentView(R.layout.files);    
c70f3333 (David A. Velasco 2013-06-04 14:30:29 +0200  174)         mDualPane = getResources().getBoolean(R.bool.large_land_layout);
c70f3333 (David A. Velasco 2013-06-04 14:30:29 +0200  175)         mLeftFragmentContainer = findViewById(R.id.left_fragment_container);
c70f3333 (David A. Velasco 2013-06-04 14:30:29 +0200  176)         mRightFragmentContainer = findViewById(R.id.right_fragment_container);
fd396289 (David A. Velasco 2013-05-30 17:53:21 +0200  177)         if (savedInstanceState == null) {
c70f3333 (David A. Velasco 2013-06-04 14:30:29 +0200  178)             createMinFragments();
f7649804 (Matthias Baumann 2013-02-25 08:21:33 +0100  179)         }
eee74aa5 (masensio         2013-07-04 18:47:38 +0200  180) 
f7649804 (Matthias Baumann 2013-02-25 08:21:33 +0100  181)         // Action bar setup
fd396289 (David A. Velasco 2013-05-30 17:53:21 +0200  182)         mDirectories = new CustomArrayAdapter<String>(this, R.layout.sherlock_spinner_dropdown_item);
f9babe7b (David A. Velasco 2013-06-14 12:53:45 +0200  183)         getSupportActionBar().setHomeButtonEnabled(true);       // mandatory since Android ICS, according to the official documentation
f9babe7b (David A. Velasco 2013-06-14 12:53:45 +0200  184)         setSupportProgressBarIndeterminateVisibility(false);    // always AFTER setContentView(...) ; to work around bug in its implementation
f7649804 (Matthias Baumann 2013-02-25 08:21:33 +0100  185)         
f6780fdc (David A. Velasco 2013-05-07 12:54:31 +0200  186)         Log_OC.d(TAG, "onCreate() end");
f7649804 (Matthias Baumann 2013-02-25 08:21:33 +0100  187)     }
f7649804 (Matthias Baumann 2013-02-25 08:21:33 +0100  188) 
eee74aa5 (masensio         2013-07-04 18:47:38 +0200  189) 
c70f3333 (David A. Velasco 2013-06-04 14:30:29 +0200  190)     @Override
c70f3333 (David A. Velasco 2013-06-04 14:30:29 +0200  191)     protected void onDestroy() {
c70f3333 (David A. Velasco 2013-06-04 14:30:29 +0200  192)         super.onDestroy();
c70f3333 (David A. Velasco 2013-06-04 14:30:29 +0200  193)         if (mDownloadConnection != null)
c70f3333 (David A. Velasco 2013-06-04 14:30:29 +0200  194)             unbindService(mDownloadConnection);
c70f3333 (David A. Velasco 2013-06-04 14:30:29 +0200  195)         if (mUploadConnection != null)
c70f3333 (David A. Velasco 2013-06-04 14:30:29 +0200  196)             unbindService(mUploadConnection);
c70f3333 (David A. Velasco 2013-06-04 14:30:29 +0200  197)     }
eee74aa5 (masensio         2013-07-04 18:47:38 +0200  198) 
eee74aa5 (masensio         2013-07-04 18:47:38 +0200  199) 
c70f3333 (David A. Velasco 2013-06-04 14:30:29 +0200  200)     /**
c70f3333 (David A. Velasco 2013-06-04 14:30:29 +0200  201)      *  Called when the ownCloud {@link Account} associated to the Activity was just updated.
c70f3333 (David A. Velasco 2013-06-04 14:30:29 +0200  202)      */ 
c70f3333 (David A. Velasco 2013-06-04 14:30:29 +0200  203)     @Override
c70f3333 (David A. Velasco 2013-06-04 14:30:29 +0200  204)     protected void onAccountSet(boolean stateWasRecovered) {
c70f3333 (David A. Velasco 2013-06-04 14:30:29 +0200  205)         if (getAccount() != null) {
c70f3333 (David A. Velasco 2013-06-04 14:30:29 +0200  206)             mStorageManager = new FileDataStorageManager(getAccount(), getContentResolver());
eee74aa5 (masensio         2013-07-04 18:47:38 +0200  207) 
c70f3333 (David A. Velasco 2013-06-04 14:30:29 +0200  208)             /// Check whether the 'main' OCFile handled by the Activity is contained in the current Account
c70f3333 (David A. Velasco 2013-06-04 14:30:29 +0200  209)             OCFile file = getFile();
c70f3333 (David A. Velasco 2013-06-04 14:30:29 +0200  210)             if (file != null) {
19bcc995 (David A. Velasco 2013-06-18 14:59:36 +0200  211)                 if (file.isDown() && file.getLastSyncDateForProperties() == 0) {
19bcc995 (David A. Velasco 2013-06-18 14:59:36 +0200  212)                     // upload in progress - right now, files are not inserted in the local cache until the upload is successful
19bcc995 (David A. Velasco 2013-06-18 14:59:36 +0200  213)                     if (mStorageManager.getFileById(file.getParentId()) == null) {
19bcc995 (David A. Velasco 2013-06-18 14:59:36 +0200  214)                         file = null;    // not able to know the directory where the file is uploading
19bcc995 (David A. Velasco 2013-06-18 14:59:36 +0200  215)                     }
19bcc995 (David A. Velasco 2013-06-18 14:59:36 +0200  216)                 } else {
19bcc995 (David A. Velasco 2013-06-18 14:59:36 +0200  217)                     file = mStorageManager.getFileByPath(file.getRemotePath());   // currentDir = null if not in the current Account
19bcc995 (David A. Velasco 2013-06-18 14:59:36 +0200  218)                 }
c70f3333 (David A. Velasco 2013-06-04 14:30:29 +0200  219)             }
c70f3333 (David A. Velasco 2013-06-04 14:30:29 +0200  220)             if (file == null) {
c70f3333 (David A. Velasco 2013-06-04 14:30:29 +0200  221)                 // fall back to root folder
f9babe7b (David A. Velasco 2013-06-14 12:53:45 +0200  222)                 file = mStorageManager.getFileByPath(OCFile.PATH_SEPARATOR);  // never returns null
c70f3333 (David A. Velasco 2013-06-04 14:30:29 +0200  223)             }
c70f3333 (David A. Velasco 2013-06-04 14:30:29 +0200  224)             setFile(file);
3ecabe3e (David A. Velasco 2013-06-14 16:59:38 +0200  225)             mDirectories.clear();
10b014a1 (David A. Velasco 2013-06-17 11:16:39 +0200  226)             OCFile fileIt = file;
10b014a1 (David A. Velasco 2013-06-17 11:16:39 +0200  227)             while(fileIt != null && fileIt.getFileName() != OCFile.PATH_SEPARATOR) {
10b014a1 (David A. Velasco 2013-06-17 11:16:39 +0200  228)                 if (fileIt.isDirectory()) {
10b014a1 (David A. Velasco 2013-06-17 11:16:39 +0200  229)                     mDirectories.add(fileIt.getFileName());
f9babe7b (David A. Velasco 2013-06-14 12:53:45 +0200  230)                 }
10b014a1 (David A. Velasco 2013-06-17 11:16:39 +0200  231)                 fileIt = mStorageManager.getFileById(fileIt.getParentId());
f9babe7b (David A. Velasco 2013-06-14 12:53:45 +0200  232)             }
f9babe7b (David A. Velasco 2013-06-14 12:53:45 +0200  233)             mDirectories.add(OCFile.PATH_SEPARATOR);
f9babe7b (David A. Velasco 2013-06-14 12:53:45 +0200  234)             if (!stateWasRecovered) {
c70f3333 (David A. Velasco 2013-06-04 14:30:29 +0200  235)                 Log_OC.e(TAG, "Initializing Fragments in onAccountChanged..");
c70f3333 (David A. Velasco 2013-06-04 14:30:29 +0200  236)                 initFragmentsWithFile();
10b014a1 (David A. Velasco 2013-06-17 11:16:39 +0200  237)                 
10b014a1 (David A. Velasco 2013-06-17 11:16:39 +0200  238)             } else {
10b014a1 (David A. Velasco 2013-06-17 11:16:39 +0200  239)                 updateFragmentsVisibility(!file.isDirectory());
10b014a1 (David A. Velasco 2013-06-17 11:16:39 +0200  240)                 updateNavigationElementsInActionBar(file.isDirectory() ? null : file);
fd396289 (David A. Velasco 2013-05-30 17:53:21 +0200  241)             }
c70f3333 (David A. Velasco 2013-06-04 14:30:29 +0200  242)             
10b014a1 (David A. Velasco 2013-06-17 11:16:39 +0200  243)             
c70f3333 (David A. Velasco 2013-06-04 14:30:29 +0200  244)         } else {
c70f3333 (David A. Velasco 2013-06-04 14:30:29 +0200  245)             Log_OC.wtf(TAG, "onAccountChanged was called with NULL account associated!");
f7649804 (Matthias Baumann 2013-02-25 08:21:33 +0100  246)         }
c70f3333 (David A. Velasco 2013-06-04 14:30:29 +0200  247)     }
eee74aa5 (masensio         2013-07-04 18:47:38 +0200  248) 
eee74aa5 (masensio         2013-07-04 18:47:38 +0200  249) 
c70f3333 (David A. Velasco 2013-06-04 14:30:29 +0200  250)     private void createMinFragments() {
c70f3333 (David A. Velasco 2013-06-04 14:30:29 +0200  251)         OCFileListFragment listOfFiles = new OCFileListFragment();
c70f3333 (David A. Velasco 2013-06-04 14:30:29 +0200  252)         FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
c70f3333 (David A. Velasco 2013-06-04 14:30:29 +0200  253)         transaction.add(R.id.left_fragment_container, listOfFiles, TAG_LIST_OF_FILES);
fd396289 (David A. Velasco 2013-05-30 17:53:21 +0200  254)         transaction.commit();
f7649804 (Matthias Baumann 2013-02-25 08:21:33 +0100  255)     }
fd396289 (David A. Velasco 2013-05-30 17:53:21 +0200  256) 
c70f3333 (David A. Velasco 2013-06-04 14:30:29 +0200  257)     private void initFragmentsWithFile() {
c70f3333 (David A. Velasco 2013-06-04 14:30:29 +0200  258)         if (getAccount() != null && getFile() != null) {
10b014a1 (David A. Velasco 2013-06-17 11:16:39 +0200  259)             /// First fragment
10b014a1 (David A. Velasco 2013-06-17 11:16:39 +0200  260)             OCFileListFragment listOfFiles = getListOfFilesFragment(); 
10b014a1 (David A. Velasco 2013-06-17 11:16:39 +0200  261)             if (listOfFiles != null) {
10b014a1 (David A. Velasco 2013-06-17 11:16:39 +0200  262)                 listOfFiles.listDirectory(getCurrentDir());   
10b014a1 (David A. Velasco 2013-06-17 11:16:39 +0200  263)             } else {
10b014a1 (David A. Velasco 2013-06-17 11:16:39 +0200  264)                 Log.e(TAG, "Still have a chance to lose the initializacion of list fragment >(");
10b014a1 (David A. Velasco 2013-06-17 11:16:39 +0200  265)             }
10b014a1 (David A. Velasco 2013-06-17 11:16:39 +0200  266)             
c70f3333 (David A. Velasco 2013-06-04 14:30:29 +0200  267)             /// Second fragment
c70f3333 (David A. Velasco 2013-06-04 14:30:29 +0200  268)             OCFile file = getFile(); 
c70f3333 (David A. Velasco 2013-06-04 14:30:29 +0200  269)             Fragment secondFragment = chooseInitialSecondFragment(file);
c70f3333 (David A. Velasco 2013-06-04 14:30:29 +0200  270)             if (secondFragment != null) {
c70f3333 (David A. Velasco 2013-06-04 14:30:29 +0200  271)                 setSecondFragment(secondFragment);
3ecabe3e (David A. Velasco 2013-06-14 16:59:38 +0200  272)                 updateFragmentsVisibility(true);
3ecabe3e (David A. Velasco 2013-06-14 16:59:38 +0200  273)                 updateNavigationElementsInActionBar(file);
3ecabe3e (David A. Velasco 2013-06-14 16:59:38 +0200  274)                 
3ecabe3e (David A. Velasco 2013-06-14 16:59:38 +0200  275)             } else {
3ecabe3e (David A. Velasco 2013-06-14 16:59:38 +0200  276)                 cleanSecondFragment();
c70f3333 (David A. Velasco 2013-06-04 14:30:29 +0200  277)             }
eee74aa5 (masensio         2013-07-04 18:47:38 +0200  278) 
c70f3333 (David A. Velasco 2013-06-04 14:30:29 +0200  279)         } else {
c70f3333 (David A. Velasco 2013-06-04 14:30:29 +0200  280)             Log.wtf(TAG, "initFragments() called with invalid NULLs!");
c70f3333 (David A. Velasco 2013-06-04 14:30:29 +0200  281)             if (getAccount() == null) {
c70f3333 (David A. Velasco 2013-06-04 14:30:29 +0200  282)                 Log.wtf(TAG, "\t account is NULL");
c70f3333 (David A. Velasco 2013-06-04 14:30:29 +0200  283)             }
c70f3333 (David A. Velasco 2013-06-04 14:30:29 +0200  284)             if (getFile() == null) {
c70f3333 (David A. Velasco 2013-06-04 14:30:29 +0200  285)                 Log.wtf(TAG, "\t file is NULL");
c70f3333 (David A. Velasco 2013-06-04 14:30:29 +0200  286)             }
c70f3333 (David A. Velasco 2013-06-04 14:30:29 +0200  287)         }
c70f3333 (David A. Velasco 2013-06-04 14:30:29 +0200  288)     }
fd396289 (David A. Velasco 2013-05-30 17:53:21 +0200  289) 
c70f3333 (David A. Velasco 2013-06-04 14:30:29 +0200  290)     private Fragment chooseInitialSecondFragment(OCFile file) {
fd396289 (David A. Velasco 2013-05-30 17:53:21 +0200  291)         Fragment secondFragment = null;
c70f3333 (David A. Velasco 2013-06-04 14:30:29 +0200  292)         if (file != null && !file.isDirectory()) {
19bcc995 (David A. Velasco 2013-06-18 14:59:36 +0200  293)             if (file.isDown() && PreviewMediaFragment.canBePreviewed(file) 
19bcc995 (David A. Velasco 2013-06-18 14:59:36 +0200  294)                     && file.getLastSyncDateForProperties() > 0  // temporal fix
19bcc995 (David A. Velasco 2013-06-18 14:59:36 +0200  295)                     ) {
fd396289 (David A. Velasco 2013-05-30 17:53:21 +0200  296)                 int startPlaybackPosition = getIntent().getIntExtra(PreviewVideoActivity.EXTRA_START_POSITION, 0);
fd396289 (David A. Velasco 2013-05-30 17:53:21 +0200  297)                 boolean autoplay = getIntent().getBooleanExtra(PreviewVideoActivity.EXTRA_AUTOPLAY, true);
fd396289 (David A. Velasco 2013-05-30 17:53:21 +0200  298)                 secondFragment = new PreviewMediaFragment(file, getAccount(), startPlaybackPosition, autoplay);
eee74aa5 (masensio         2013-07-04 18:47:38 +0200  299) 
f7649804 (Matthias Baumann 2013-02-25 08:21:33 +0100  300)             } else {
fd396289 (David A. Velasco 2013-05-30 17:53:21 +0200  301)                 secondFragment = new FileDetailFragment(file, getAccount());
f7649804 (Matthias Baumann 2013-02-25 08:21:33 +0100  302)             }
00328cb8 (David A. Velasco 2013-04-30 13:16:08 +0200  303)         }
fd396289 (David A. Velasco 2013-05-30 17:53:21 +0200  304)         return secondFragment;
00328cb8 (David A. Velasco 2013-04-30 13:16:08 +0200  305)     }
00328cb8 (David A. Velasco 2013-04-30 13:16:08 +0200  306) 
eee74aa5 (masensio         2013-07-04 18:47:38 +0200  307) 
c70f3333 (David A. Velasco 2013-06-04 14:30:29 +0200  308)     /**
c70f3333 (David A. Velasco 2013-06-04 14:30:29 +0200  309)      * Replaces the second fragment managed by the activity with the received as
c70f3333 (David A. Velasco 2013-06-04 14:30:29 +0200  310)      * a parameter.
c70f3333 (David A. Velasco 2013-06-04 14:30:29 +0200  311)      * 
c70f3333 (David A. Velasco 2013-06-04 14:30:29 +0200  312)      * Assumes never will be more than two fragments managed at the same time. 
c70f3333 (David A. Velasco 2013-06-04 14:30:29 +0200  313)      * 
c70f3333 (David A. Velasco 2013-06-04 14:30:29 +0200  314)      * @param fragment      New second Fragment to set.
c70f3333 (David A. Velasco 2013-06-04 14:30:29 +0200  315)      */
c70f3333 (David A. Velasco 2013-06-04 14:30:29 +0200  316)     private void setSecondFragment(Fragment fragment) {
c70f3333 (David A. Velasco 2013-06-04 14:30:29 +0200  317)         FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
16f121a7 (David A. Velasco 2013-06-05 09:59:48 +0200  318)         transaction.replace(R.id.right_fragment_container, fragment, TAG_SECOND_FRAGMENT);
c70f3333 (David A. Velasco 2013-06-04 14:30:29 +0200  319)         transaction.commit();
c70f3333 (David A. Velasco 2013-06-04 14:30:29 +0200  320)     }
eee74aa5 (masensio         2013-07-04 18:47:38 +0200  321) 
eee74aa5 (masensio         2013-07-04 18:47:38 +0200  322) 
c70f3333 (David A. Velasco 2013-06-04 14:30:29 +0200  323)     private void updateFragmentsVisibility(boolean existsSecondFragment) {
c70f3333 (David A. Velasco 2013-06-04 14:30:29 +0200  324)         if (mDualPane) {
c70f3333 (David A. Velasco 2013-06-04 14:30:29 +0200  325)             if (mLeftFragmentContainer.getVisibility() != View.VISIBLE) {
c70f3333 (David A. Velasco 2013-06-04 14:30:29 +0200  326)                 mLeftFragmentContainer.setVisibility(View.VISIBLE);
c70f3333 (David A. Velasco 2013-06-04 14:30:29 +0200  327)             }
c70f3333 (David A. Velasco 2013-06-04 14:30:29 +0200  328)             if (mRightFragmentContainer.getVisibility() != View.VISIBLE) {
c70f3333 (David A. Velasco 2013-06-04 14:30:29 +0200  329)                 mRightFragmentContainer.setVisibility(View.VISIBLE);
c70f3333 (David A. Velasco 2013-06-04 14:30:29 +0200  330)             }
eee74aa5 (masensio         2013-07-04 18:47:38 +0200  331) 
c70f3333 (David A. Velasco 2013-06-04 14:30:29 +0200  332)         } else if (existsSecondFragment) {
c70f3333 (David A. Velasco 2013-06-04 14:30:29 +0200  333)             if (mLeftFragmentContainer.getVisibility() != View.GONE) {
c70f3333 (David A. Velasco 2013-06-04 14:30:29 +0200  334)                 mLeftFragmentContainer.setVisibility(View.GONE);
c70f3333 (David A. Velasco 2013-06-04 14:30:29 +0200  335)             }
c70f3333 (David A. Velasco 2013-06-04 14:30:29 +0200  336)             if (mRightFragmentContainer.getVisibility() != View.VISIBLE) {
c70f3333 (David A. Velasco 2013-06-04 14:30:29 +0200  337)                 mRightFragmentContainer.setVisibility(View.VISIBLE);
c70f3333 (David A. Velasco 2013-06-04 14:30:29 +0200  338)             }
eee74aa5 (masensio         2013-07-04 18:47:38 +0200  339) 
c70f3333 (David A. Velasco 2013-06-04 14:30:29 +0200  340)         } else {
c70f3333 (David A. Velasco 2013-06-04 14:30:29 +0200  341)             if (mLeftFragmentContainer.getVisibility() != View.VISIBLE) {
c70f3333 (David A. Velasco 2013-06-04 14:30:29 +0200  342)                 mLeftFragmentContainer.setVisibility(View.VISIBLE);
c70f3333 (David A. Velasco 2013-06-04 14:30:29 +0200  343)             }
c70f3333 (David A. Velasco 2013-06-04 14:30:29 +0200  344)             if (mRightFragmentContainer.getVisibility() != View.GONE) {
c70f3333 (David A. Velasco 2013-06-04 14:30:29 +0200  345)                 mRightFragmentContainer.setVisibility(View.GONE);
c70f3333 (David A. Velasco 2013-06-04 14:30:29 +0200  346)             }
c70f3333 (David A. Velasco 2013-06-04 14:30:29 +0200  347)         }
c70f3333 (David A. Velasco 2013-06-04 14:30:29 +0200  348)     }
00328cb8 (David A. Velasco 2013-04-30 13:16:08 +0200  349) 
eee74aa5 (masensio         2013-07-04 18:47:38 +0200  350) 
c70f3333 (David A. Velasco 2013-06-04 14:30:29 +0200  351)     private OCFileListFragment getListOfFilesFragment() {
c70f3333 (David A. Velasco 2013-06-04 14:30:29 +0200  352)         Fragment listOfFiles = getSupportFragmentManager().findFragmentByTag(FileDisplayActivity.TAG_LIST_OF_FILES);
c70f3333 (David A. Velasco 2013-06-04 14:30:29 +0200  353)         if (listOfFiles != null) {
c70f3333 (David A. Velasco 2013-06-04 14:30:29 +0200  354)             return (OCFileListFragment)listOfFiles;
c70f3333 (David A. Velasco 2013-06-04 14:30:29 +0200  355)         }
c70f3333 (David A. Velasco 2013-06-04 14:30:29 +0200  356)         Log_OC.wtf(TAG, "Access to unexisting list of files fragment!!");
c70f3333 (David A. Velasco 2013-06-04 14:30:29 +0200  357)         return null;
c70f3333 (David A. Velasco 2013-06-04 14:30:29 +0200  358)     }
eee74aa5 (masensio         2013-07-04 18:47:38 +0200  359) 
c70f3333 (David A. Velasco 2013-06-04 14:30:29 +0200  360)     protected FileFragment getSecondFragment() {
c70f3333 (David A. Velasco 2013-06-04 14:30:29 +0200  361)         Fragment second = getSupportFragmentManager().findFragmentByTag(FileDisplayActivity.TAG_SECOND_FRAGMENT);
c70f3333 (David A. Velasco 2013-06-04 14:30:29 +0200  362)         if (second != null) {
c70f3333 (David A. Velasco 2013-06-04 14:30:29 +0200  363)             return (FileFragment)second;
c70f3333 (David A. Velasco 2013-06-04 14:30:29 +0200  364)         }
c70f3333 (David A. Velasco 2013-06-04 14:30:29 +0200  365)         return null;
f7649804 (Matthias Baumann 2013-02-25 08:21:33 +0100  366)     }
f7649804 (Matthias Baumann 2013-02-25 08:21:33 +0100  367) 
c70f3333 (David A. Velasco 2013-06-04 14:30:29 +0200  368)     public void cleanSecondFragment() {
c70f3333 (David A. Velasco 2013-06-04 14:30:29 +0200  369)         Fragment second = getSecondFragment();
c70f3333 (David A. Velasco 2013-06-04 14:30:29 +0200  370)         if (second != null) {
c70f3333 (David A. Velasco 2013-06-04 14:30:29 +0200  371)             FragmentTransaction tr = getSupportFragmentManager().beginTransaction();
c70f3333 (David A. Velasco 2013-06-04 14:30:29 +0200  372)             tr.remove(second);
c70f3333 (David A. Velasco 2013-06-04 14:30:29 +0200  373)             tr.commit();
c70f3333 (David A. Velasco 2013-06-04 14:30:29 +0200  374)         }
3ecabe3e (David A. Velasco 2013-06-14 16:59:38 +0200  375)         updateFragmentsVisibility(false);
3ecabe3e (David A. Velasco 2013-06-14 16:59:38 +0200  376)         updateNavigationElementsInActionBar(null);
c70f3333 (David A. Velasco 2013-06-04 14:30:29 +0200  377)     }
eee74aa5 (masensio         2013-07-04 18:47:38 +0200  378) 
c70f3333 (David A. Velasco 2013-06-04 14:30:29 +0200  379)     protected void refeshListOfFilesFragment() {
c70f3333 (David A. Velasco 2013-06-04 14:30:29 +0200  380)         OCFileListFragment fileListFragment = getListOfFilesFragment();
c70f3333 (David A. Velasco 2013-06-04 14:30:29 +0200  381)         if (fileListFragment != null) { 
c70f3333 (David A. Velasco 2013-06-04 14:30:29 +0200  382)             fileListFragment.listDirectory();
c70f3333 (David A. Velasco 2013-06-04 14:30:29 +0200  383)         }
c70f3333 (David A. Velasco 2013-06-04 14:30:29 +0200  384)     }
c70f3333 (David A. Velasco 2013-06-04 14:30:29 +0200  385) 
c70f3333 (David A. Velasco 2013-06-04 14:30:29 +0200  386)     protected void refreshSecondFragment(String downloadEvent, String downloadedRemotePath, boolean success) {
c70f3333 (David A. Velasco 2013-06-04 14:30:29 +0200  387)         FileFragment secondFragment = getSecondFragment();
c70f3333 (David A. Velasco 2013-06-04 14:30:29 +0200  388)         boolean waitedPreview = (mWaitingToPreview != null && mWaitingToPreview.getRemotePath().equals(downloadedRemotePath));
c70f3333 (David A. Velasco 2013-06-04 14:30:29 +0200  389)         if (secondFragment != null && secondFragment instanceof FileDetailFragment) {
c70f3333 (David A. Velasco 2013-06-04 14:30:29 +0200  390)             FileDetailFragment detailsFragment = (FileDetailFragment) secondFragment;
c70f3333 (David A. Velasco 2013-06-04 14:30:29 +0200  391)             OCFile fileInFragment = detailsFragment.getFile();
c70f3333 (David A. Velasco 2013-06-04 14:30:29 +0200  392)             if (fileInFragment != null && !downloadedRemotePath.equals(fileInFragment.getRemotePath())) {
c70f3333 (David A. Velasco 2013-06-04 14:30:29 +0200  393)                 // the user browsed to other file ; forget the automatic preview 
c70f3333 (David A. Velasco 2013-06-04 14:30:29 +0200  394)                 mWaitingToPreview = null;
eee74aa5 (masensio         2013-07-04 18:47:38 +0200  395) 
c70f3333 (David A. Velasco 2013-06-04 14:30:29 +0200  396)             } else if (downloadEvent.equals(FileDownloader.DOWNLOAD_ADDED_MESSAGE)) {
c70f3333 (David A. Velasco 2013-06-04 14:30:29 +0200  397)                 // grant that the right panel updates the progress bar
c70f3333 (David A. Velasco 2013-06-04 14:30:29 +0200  398)                 detailsFragment.listenForTransferProgress();
c70f3333 (David A. Velasco 2013-06-04 14:30:29 +0200  399)                 detailsFragment.updateFileDetails(true, false);
eee74aa5 (masensio         2013-07-04 18:47:38 +0200  400) 
c70f3333 (David A. Velasco 2013-06-04 14:30:29 +0200  401)             } else if (downloadEvent.equals(FileDownloader.DOWNLOAD_FINISH_MESSAGE)) {
64bbf07d (David A. Velasco 2013-06-10 13:52:24 +0200  402)                 //  update the right panel
64bbf07d (David A. Velasco 2013-06-10 13:52:24 +0200  403)                 boolean detailsFragmentChanged = false;
64bbf07d (David A. Velasco 2013-06-10 13:52:24 +0200  404)                 if (waitedPreview) {
64bbf07d (David A. Velasco 2013-06-10 13:52:24 +0200  405)                     if (success) {
64bbf07d (David A. Velasco 2013-06-10 13:52:24 +0200  406)                         mWaitingToPreview = mStorageManager.getFileById(mWaitingToPreview.getFileId());   // update the file from database, for the local storage path
64bbf07d (David A. Velasco 2013-06-10 13:52:24 +0200  407)                         if (PreviewMediaFragment.canBePreviewed(mWaitingToPreview)) {
64bbf07d (David A. Velasco 2013-06-10 13:52:24 +0200  408)                             startMediaPreview(mWaitingToPreview, 0, true);
64bbf07d (David A. Velasco 2013-06-10 13:52:24 +0200  409)                             detailsFragmentChanged = true;
64bbf07d (David A. Velasco 2013-06-10 13:52:24 +0200  410)                         } else {
64bbf07d (David A. Velasco 2013-06-10 13:52:24 +0200  411)                             openFile(mWaitingToPreview);
64bbf07d (David A. Velasco 2013-06-10 13:52:24 +0200  412)                         }
c70f3333 (David A. Velasco 2013-06-04 14:30:29 +0200  413)                     }
c70f3333 (David A. Velasco 2013-06-04 14:30:29 +0200  414)                     mWaitingToPreview = null;
64bbf07d (David A. Velasco 2013-06-10 13:52:24 +0200  415)                 }
64bbf07d (David A. Velasco 2013-06-10 13:52:24 +0200  416)                 if (!detailsFragmentChanged) {
c70f3333 (David A. Velasco 2013-06-04 14:30:29 +0200  417)                     detailsFragment.updateFileDetails(false, (success));
c70f3333 (David A. Velasco 2013-06-04 14:30:29 +0200  418)                 }
c70f3333 (David A. Velasco 2013-06-04 14:30:29 +0200  419)             }
c70f3333 (David A. Velasco 2013-06-04 14:30:29 +0200  420)         }
c70f3333 (David A. Velasco 2013-06-04 14:30:29 +0200  421)     }
c70f3333 (David A. Velasco 2013-06-04 14:30:29 +0200  422) 
c70f3333 (David A. Velasco 2013-06-04 14:30:29 +0200  423) 
f7649804 (Matthias Baumann 2013-02-25 08:21:33 +0100  424)     @Override
f7649804 (Matthias Baumann 2013-02-25 08:21:33 +0100  425)     public boolean onCreateOptionsMenu(Menu menu) {
f7649804 (Matthias Baumann 2013-02-25 08:21:33 +0100  426)         MenuInflater inflater = getSherlock().getMenuInflater();
c70f3333 (David A. Velasco 2013-06-04 14:30:29 +0200  427)         inflater.inflate(R.menu.main_menu, menu);
c70f3333 (David A. Velasco 2013-06-04 14:30:29 +0200  428)         return true;
f7649804 (Matthias Baumann 2013-02-25 08:21:33 +0100  429)     }
f7649804 (Matthias Baumann 2013-02-25 08:21:33 +0100  430) 
f7649804 (Matthias Baumann 2013-02-25 08:21:33 +0100  431)     @Override
f7649804 (Matthias Baumann 2013-02-25 08:21:33 +0100  432)     public boolean onOptionsItemSelected(MenuItem item) {
f7649804 (Matthias Baumann 2013-02-25 08:21:33 +0100  433)         boolean retval = true;
f7649804 (Matthias Baumann 2013-02-25 08:21:33 +0100  434)         switch (item.getItemId()) {
eee74aa5 (masensio         2013-07-04 18:47:38 +0200  435)         case R.id.action_create_dir: {
eee74aa5 (masensio         2013-07-04 18:47:38 +0200  436)             EditNameDialog dialog = EditNameDialog.newInstance(getString(R.string.uploader_info_dirname), "", -1, -1, this);
eee74aa5 (masensio         2013-07-04 18:47:38 +0200  437)             dialog.show(getSupportFragmentManager(), "createdirdialog");
eee74aa5 (masensio         2013-07-04 18:47:38 +0200  438)             break;
eee74aa5 (masensio         2013-07-04 18:47:38 +0200  439)         }
eee74aa5 (masensio         2013-07-04 18:47:38 +0200  440)         case R.id.action_sync_account: {
eee74aa5 (masensio         2013-07-04 18:47:38 +0200  441)             startSynchronization();
eee74aa5 (masensio         2013-07-04 18:47:38 +0200  442)             break;
eee74aa5 (masensio         2013-07-04 18:47:38 +0200  443)         }
eee74aa5 (masensio         2013-07-04 18:47:38 +0200  444)         case R.id.action_upload: {
eee74aa5 (masensio         2013-07-04 18:47:38 +0200  445)             showDialog(DIALOG_CHOOSE_UPLOAD_SOURCE);
eee74aa5 (masensio         2013-07-04 18:47:38 +0200  446)             break;
eee74aa5 (masensio         2013-07-04 18:47:38 +0200  447)         }
eee74aa5 (masensio         2013-07-04 18:47:38 +0200  448)         case R.id.action_settings: {
eee74aa5 (masensio         2013-07-04 18:47:38 +0200  449)             Intent settingsIntent = new Intent(this, Preferences.class);
eee74aa5 (masensio         2013-07-04 18:47:38 +0200  450)             startActivity(settingsIntent);
eee74aa5 (masensio         2013-07-04 18:47:38 +0200  451)             break;
eee74aa5 (masensio         2013-07-04 18:47:38 +0200  452)         }
eee74aa5 (masensio         2013-07-04 18:47:38 +0200  453)         case android.R.id.home: {
eee74aa5 (masensio         2013-07-04 18:47:38 +0200  454)             FileFragment second = getSecondFragment();
eee74aa5 (masensio         2013-07-04 18:47:38 +0200  455)             OCFile currentDir = getCurrentDir();
eee74aa5 (masensio         2013-07-04 18:47:38 +0200  456)             if((currentDir != null && currentDir.getParentId() != 0) || 
eee74aa5 (masensio         2013-07-04 18:47:38 +0200  457)                     (second != null && second.getFile() != null)) {
eee74aa5 (masensio         2013-07-04 18:47:38 +0200  458)                 onBackPressed(); 
8aeb5243 (David A. Velasco 2013-04-12 12:34:17 +0200  459)             }
eee74aa5 (masensio         2013-07-04 18:47:38 +0200  460)             break;
eee74aa5 (masensio         2013-07-04 18:47:38 +0200  461)         }
eee74aa5 (masensio         2013-07-04 18:47:38 +0200  462)         default:
eee74aa5 (masensio         2013-07-04 18:47:38 +0200  463)             retval = super.onOptionsItemSelected(item);
f7649804 (Matthias Baumann 2013-02-25 08:21:33 +0100  464)         }
f7649804 (Matthias Baumann 2013-02-25 08:21:33 +0100  465)         return retval;
f7649804 (Matthias Baumann 2013-02-25 08:21:33 +0100  466)     }
f7649804 (Matthias Baumann 2013-02-25 08:21:33 +0100  467) 
f7649804 (Matthias Baumann 2013-02-25 08:21:33 +0100  468)     private void startSynchronization() {
2946d8dd (David A. Velasco 2013-04-25 19:39:22 +0200  469)         ContentResolver.cancelSync(null, AccountAuthenticator.AUTHORITY);   // cancel the current synchronizations of any ownCloud account
f7649804 (Matthias Baumann 2013-02-25 08:21:33 +0100  470)         Bundle bundle = new Bundle();
f7649804 (Matthias Baumann 2013-02-25 08:21:33 +0100  471)         bundle.putBoolean(ContentResolver.SYNC_EXTRAS_MANUAL, true);
f7649804 (Matthias Baumann 2013-02-25 08:21:33 +0100  472)         ContentResolver.requestSync(
f6780fdc (David A. Velasco 2013-05-07 12:54:31 +0200  473)                 getAccount(),
2946d8dd (David A. Velasco 2013-04-25 19:39:22 +0200  474)                 AccountAuthenticator.AUTHORITY, bundle);
f7649804 (Matthias Baumann 2013-02-25 08:21:33 +0100  475)     }
f7649804 (Matthias Baumann 2013-02-25 08:21:33 +0100  476) 
f7649804 (Matthias Baumann 2013-02-25 08:21:33 +0100  477) 
f7649804 (Matthias Baumann 2013-02-25 08:21:33 +0100  478)     @Override
f7649804 (Matthias Baumann 2013-02-25 08:21:33 +0100  479)     public boolean onNavigationItemSelected(int itemPosition, long itemId) {
f7649804 (Matthias Baumann 2013-02-25 08:21:33 +0100  480)         int i = itemPosition;
f7649804 (Matthias Baumann 2013-02-25 08:21:33 +0100  481)         while (i-- != 0) {
f7649804 (Matthias Baumann 2013-02-25 08:21:33 +0100  482)             onBackPressed();
f7649804 (Matthias Baumann 2013-02-25 08:21:33 +0100  483)         }
f7649804 (Matthias Baumann 2013-02-25 08:21:33 +0100  484)         // the next operation triggers a new call to this method, but it's necessary to 
f7649804 (Matthias Baumann 2013-02-25 08:21:33 +0100  485)         // ensure that the name exposed in the action bar is the current directory when the 
f7649804 (Matthias Baumann 2013-02-25 08:21:33 +0100  486)         // user selected it in the navigation list
f7649804 (Matthias Baumann 2013-02-25 08:21:33 +0100  487)         if (itemPosition != 0)
f7649804 (Matthias Baumann 2013-02-25 08:21:33 +0100  488)             getSupportActionBar().setSelectedNavigationItem(0);
f7649804 (Matthias Baumann 2013-02-25 08:21:33 +0100  489)         return true;
f7649804 (Matthias Baumann 2013-02-25 08:21:33 +0100  490)     }
f7649804 (Matthias Baumann 2013-02-25 08:21:33 +0100  491) 
f7649804 (Matthias Baumann 2013-02-25 08:21:33 +0100  492)     /**
f7649804 (Matthias Baumann 2013-02-25 08:21:33 +0100  493)      * Called, when the user selected something for uploading
f7649804 (Matthias Baumann 2013-02-25 08:21:33 +0100  494)      */
979bdc83 (David A. Velasco 2013-05-07 13:05:40 +0200  495)     protected void onActivityResult(int requestCode, int resultCode, Intent data) {
00328cb8 (David A. Velasco 2013-04-30 13:16:08 +0200  496)         super.onActivityResult(requestCode, resultCode, data);
eee74aa5 (masensio         2013-07-04 18:47:38 +0200  497) 
8aeb5243 (David A. Velasco 2013-04-12 12:34:17 +0200  498)         if (requestCode == ACTION_SELECT_CONTENT_FROM_APPS && (resultCode == RESULT_OK || resultCode == UploadFilesActivity.RESULT_OK_AND_MOVE)) {
f7649804 (Matthias Baumann 2013-02-25 08:21:33 +0100  499)             requestSimpleUpload(data, resultCode);
eee74aa5 (masensio         2013-07-04 18:47:38 +0200  500) 
8aeb5243 (David A. Velasco 2013-04-12 12:34:17 +0200  501)         } else if (requestCode == ACTION_SELECT_MULTIPLE_FILES && (resultCode == RESULT_OK || resultCode == UploadFilesActivity.RESULT_OK_AND_MOVE)) {
f7649804 (Matthias Baumann 2013-02-25 08:21:33 +0100  502)             requestMultipleUpload(data, resultCode);
eee74aa5 (masensio         2013-07-04 18:47:38 +0200  503) 
f7649804 (Matthias Baumann 2013-02-25 08:21:33 +0100  504)         }
f7649804 (Matthias Baumann 2013-02-25 08:21:33 +0100  505)     }
f7649804 (Matthias Baumann 2013-02-25 08:21:33 +0100  506) 
f7649804 (Matthias Baumann 2013-02-25 08:21:33 +0100  507)     private void requestMultipleUpload(Intent data, int resultCode) {
f7649804 (Matthias Baumann 2013-02-25 08:21:33 +0100  508)         String[] filePaths = data.getStringArrayExtra(UploadFilesActivity.EXTRA_CHOSEN_FILES);
f7649804 (Matthias Baumann 2013-02-25 08:21:33 +0100  509)         if (filePaths != null) {
f7649804 (Matthias Baumann 2013-02-25 08:21:33 +0100  510)             String[] remotePaths = new String[filePaths.length];
f7649804 (Matthias Baumann 2013-02-25 08:21:33 +0100  511)             String remotePathBase = "";
f7649804 (Matthias Baumann 2013-02-25 08:21:33 +0100  512)             for (int j = mDirectories.getCount() - 2; j >= 0; --j) {
f7649804 (Matthias Baumann 2013-02-25 08:21:33 +0100  513)                 remotePathBase += OCFile.PATH_SEPARATOR + mDirectories.getItem(j);
f7649804 (Matthias Baumann 2013-02-25 08:21:33 +0100  514)             }
f7649804 (Matthias Baumann 2013-02-25 08:21:33 +0100  515)             if (!remotePathBase.endsWith(OCFile.PATH_SEPARATOR))
f7649804 (Matthias Baumann 2013-02-25 08:21:33 +0100  516)                 remotePathBase += OCFile.PATH_SEPARATOR;
8aeb5243 (David A. Velasco 2013-04-12 12:34:17 +0200  517)             for (int j = 0; j< remotePaths.length; j++) {
f7649804 (Matthias Baumann 2013-02-25 08:21:33 +0100  518)                 remotePaths[j] = remotePathBase + (new File(filePaths[j])).getName();
f7649804 (Matthias Baumann 2013-02-25 08:21:33 +0100  519)             }
f7649804 (Matthias Baumann 2013-02-25 08:21:33 +0100  520) 
f7649804 (Matthias Baumann 2013-02-25 08:21:33 +0100  521)             Intent i = new Intent(this, FileUploader.class);
f6780fdc (David A. Velasco 2013-05-07 12:54:31 +0200  522)             i.putExtra(FileUploader.KEY_ACCOUNT, getAccount());
f7649804 (Matthias Baumann 2013-02-25 08:21:33 +0100  523)             i.putExtra(FileUploader.KEY_LOCAL_FILE, filePaths);
f7649804 (Matthias Baumann 2013-02-25 08:21:33 +0100  524)             i.putExtra(FileUploader.KEY_REMOTE_FILE, remotePaths);
f7649804 (Matthias Baumann 2013-02-25 08:21:33 +0100  525)             i.putExtra(FileUploader.KEY_UPLOAD_TYPE, FileUploader.UPLOAD_MULTIPLE_FILES);
f7649804 (Matthias Baumann 2013-02-25 08:21:33 +0100  526)             if (resultCode == UploadFilesActivity.RESULT_OK_AND_MOVE)
f7649804 (Matthias Baumann 2013-02-25 08:21:33 +0100  527)                 i.putExtra(FileUploader.KEY_LOCAL_BEHAVIOUR, FileUploader.LOCAL_BEHAVIOUR_MOVE);
f7649804 (Matthias Baumann 2013-02-25 08:21:33 +0100  528)             startService(i);
eee74aa5 (masensio         2013-07-04 18:47:38 +0200  529) 
f7649804 (Matthias Baumann 2013-02-25 08:21:33 +0100  530)         } else {
fd396289 (David A. Velasco 2013-05-30 17:53:21 +0200  531)             Log_OC.d(TAG, "User clicked on 'Update' with no selection");
f7649804 (Matthias Baumann 2013-02-25 08:21:33 +0100  532)             Toast t = Toast.makeText(this, getString(R.string.filedisplay_no_file_selected), Toast.LENGTH_LONG);
f7649804 (Matthias Baumann 2013-02-25 08:21:33 +0100  533)             t.show();
f7649804 (Matthias Baumann 2013-02-25 08:21:33 +0100  534)             return;
f7649804 (Matthias Baumann 2013-02-25 08:21:33 +0100  535)         }
f7649804 (Matthias Baumann 2013-02-25 08:21:33 +0100  536)     }
f7649804 (Matthias Baumann 2013-02-25 08:21:33 +0100  537) 
f7649804 (Matthias Baumann 2013-02-25 08:21:33 +0100  538) 
f7649804 (Matthias Baumann 2013-02-25 08:21:33 +0100  539)     private void requestSimpleUpload(Intent data, int resultCode) {
f7649804 (Matthias Baumann 2013-02-25 08:21:33 +0100  540)         String filepath = null;
f7649804 (Matthias Baumann 2013-02-25 08:21:33 +0100  541)         try {
f7649804 (Matthias Baumann 2013-02-25 08:21:33 +0100  542)             Uri selectedImageUri = data.getData();
f7649804 (Matthias Baumann 2013-02-25 08:21:33 +0100  543) 
f7649804 (Matthias Baumann 2013-02-25 08:21:33 +0100  544)             String filemanagerstring = selectedImageUri.getPath();
f7649804 (Matthias Baumann 2013-02-25 08:21:33 +0100  545)             String selectedImagePath = getPath(selectedImageUri);
f7649804 (Matthias Baumann 2013-02-25 08:21:33 +0100  546) 
f7649804 (Matthias Baumann 2013-02-25 08:21:33 +0100  547)             if (selectedImagePath != null)
f7649804 (Matthias Baumann 2013-02-25 08:21:33 +0100  548)                 filepath = selectedImagePath;
f7649804 (Matthias Baumann 2013-02-25 08:21:33 +0100  549)             else
f7649804 (Matthias Baumann 2013-02-25 08:21:33 +0100  550)                 filepath = filemanagerstring;
eee74aa5 (masensio         2013-07-04 18:47:38 +0200  551) 
f7649804 (Matthias Baumann 2013-02-25 08:21:33 +0100  552)         } catch (Exception e) {
fd396289 (David A. Velasco 2013-05-30 17:53:21 +0200  553)             Log_OC.e(TAG, "Unexpected exception when trying to read the result of Intent.ACTION_GET_CONTENT", e);
f7649804 (Matthias Baumann 2013-02-25 08:21:33 +0100  554)             e.printStackTrace();
eee74aa5 (masensio         2013-07-04 18:47:38 +0200  555) 
f7649804 (Matthias Baumann 2013-02-25 08:21:33 +0100  556)         } finally {
f7649804 (Matthias Baumann 2013-02-25 08:21:33 +0100  557)             if (filepath == null) {
fd396289 (David A. Velasco 2013-05-30 17:53:21 +0200  558)                 Log_OC.e(TAG, "Couldnt resolve path to file");
f7649804 (Matthias Baumann 2013-02-25 08:21:33 +0100  559)                 Toast t = Toast.makeText(this, getString(R.string.filedisplay_unexpected_bad_get_content), Toast.LENGTH_LONG);
f7649804 (Matthias Baumann 2013-02-25 08:21:33 +0100  560)                 t.show();
f7649804 (Matthias Baumann 2013-02-25 08:21:33 +0100  561)                 return;
f7649804 (Matthias Baumann 2013-02-25 08:21:33 +0100  562)             }
f7649804 (Matthias Baumann 2013-02-25 08:21:33 +0100  563)         }
f7649804 (Matthias Baumann 2013-02-25 08:21:33 +0100  564) 
f7649804 (Matthias Baumann 2013-02-25 08:21:33 +0100  565)         Intent i = new Intent(this, FileUploader.class);
8aeb5243 (David A. Velasco 2013-04-12 12:34:17 +0200  566)         i.putExtra(FileUploader.KEY_ACCOUNT,
f6780fdc (David A. Velasco 2013-05-07 12:54:31 +0200  567)                 getAccount());
f7649804 (Matthias Baumann 2013-02-25 08:21:33 +0100  568)         String remotepath = new String();
f7649804 (Matthias Baumann 2013-02-25 08:21:33 +0100  569)         for (int j = mDirectories.getCount() - 2; j >= 0; --j) {
f7649804 (Matthias Baumann 2013-02-25 08:21:33 +0100  570)             remotepath += OCFile.PATH_SEPARATOR + mDirectories.getItem(j);
f7649804 (Matthias Baumann 2013-02-25 08:21:33 +0100  571)         }
f7649804 (Matthias Baumann 2013-02-25 08:21:33 +0100  572)         if (!remotepath.endsWith(OCFile.PATH_SEPARATOR))
f7649804 (Matthias Baumann 2013-02-25 08:21:33 +0100  573)             remotepath += OCFile.PATH_SEPARATOR;
f7649804 (Matthias Baumann 2013-02-25 08:21:33 +0100  574)         remotepath += new File(filepath).getName();
f7649804 (Matthias Baumann 2013-02-25 08:21:33 +0100  575) 
f7649804 (Matthias Baumann 2013-02-25 08:21:33 +0100  576)         i.putExtra(FileUploader.KEY_LOCAL_FILE, filepath);
f7649804 (Matthias Baumann 2013-02-25 08:21:33 +0100  577)         i.putExtra(FileUploader.KEY_REMOTE_FILE, remotepath);
f7649804 (Matthias Baumann 2013-02-25 08:21:33 +0100  578)         i.putExtra(FileUploader.KEY_UPLOAD_TYPE, FileUploader.UPLOAD_SINGLE_FILE);
f7649804 (Matthias Baumann 2013-02-25 08:21:33 +0100  579)         if (resultCode == UploadFilesActivity.RESULT_OK_AND_MOVE)
f7649804 (Matthias Baumann 2013-02-25 08:21:33 +0100  580)             i.putExtra(FileUploader.KEY_LOCAL_BEHAVIOUR, FileUploader.LOCAL_BEHAVIOUR_MOVE);
f7649804 (Matthias Baumann 2013-02-25 08:21:33 +0100  581)         startService(i);
f7649804 (Matthias Baumann 2013-02-25 08:21:33 +0100  582)     }
f7649804 (Matthias Baumann 2013-02-25 08:21:33 +0100  583) 
f7649804 (Matthias Baumann 2013-02-25 08:21:33 +0100  584)     @Override
f7649804 (Matthias Baumann 2013-02-25 08:21:33 +0100  585)     public void onBackPressed() {
16f121a7 (David A. Velasco 2013-06-05 09:59:48 +0200  586)         OCFileListFragment listOfFiles = getListOfFilesFragment(); 
c70f3333 (David A. Velasco 2013-06-04 14:30:29 +0200  587)         if (mDualPane || getSecondFragment() == null) {
c70f3333 (David A. Velasco 2013-06-04 14:30:29 +0200  588)             if (listOfFiles != null) {  // should never be null, indeed
c70f3333 (David A. Velasco 2013-06-04 14:30:29 +0200  589)                 if (mDirectories.getCount() <= 1) {
c70f3333 (David A. Velasco 2013-06-04 14:30:29 +0200  590)                     finish();
c70f3333 (David A. Velasco 2013-06-04 14:30:29 +0200  591)                     return;
c70f3333 (David A. Velasco 2013-06-04 14:30:29 +0200  592)                 }
c70f3333 (David A. Velasco 2013-06-04 14:30:29 +0200  593)                 popDirname();
c70f3333 (David A. Velasco 2013-06-04 14:30:29 +0200  594)                 listOfFiles.onBrowseUp();
fd396289 (David A. Velasco 2013-05-30 17:53:21 +0200  595)             }
f7649804 (Matthias Baumann 2013-02-25 08:21:33 +0100  596)         }
16f121a7 (David A. Velasco 2013-06-05 09:59:48 +0200  597)         if (listOfFiles != null) {  // should never be null, indeed
16f121a7 (David A. Velasco 2013-06-05 09:59:48 +0200  598)             setFile(listOfFiles.getCurrentFile());
16f121a7 (David A. Velasco 2013-06-05 09:59:48 +0200  599)         }
c70f3333 (David A. Velasco 2013-06-04 14:30:29 +0200  600)         cleanSecondFragment();
fd396289 (David A. Velasco 2013-05-30 17:53:21 +0200  601)     }
fd396289 (David A. Velasco 2013-05-30 17:53:21 +0200  602) 
f7649804 (Matthias Baumann 2013-02-25 08:21:33 +0100  603)     @Override
f7649804 (Matthias Baumann 2013-02-25 08:21:33 +0100  604)     protected void onSaveInstanceState(Bundle outState) {
f7649804 (Matthias Baumann 2013-02-25 08:21:33 +0100  605)         // responsibility of restore is preferred in onCreate() before than in onRestoreInstanceState when there are Fragments involved
fd396289 (David A. Velasco 2013-05-30 17:53:21 +0200  606)         Log_OC.e(TAG, "onSaveInstanceState() start");
f7649804 (Matthias Baumann 2013-02-25 08:21:33 +0100  607)         super.onSaveInstanceState(outState);
fd396289 (David A. Velasco 2013-05-30 17:53:21 +0200  608)         outState.putParcelable(FileDisplayActivity.KEY_WAITING_TO_PREVIEW, mWaitingToPreview);
f6780fdc (David A. Velasco 2013-05-07 12:54:31 +0200  609)         Log_OC.d(TAG, "onSaveInstanceState() end");
f7649804 (Matthias Baumann 2013-02-25 08:21:33 +0100  610)     }
f9babe7b (David A. Velasco 2013-06-14 12:53:45 +0200  611) 
fd396289 (David A. Velasco 2013-05-30 17:53:21 +0200  612)     @Override
f7649804 (Matthias Baumann 2013-02-25 08:21:33 +0100  613)     protected void onResume() {
f7649804 (Matthias Baumann 2013-02-25 08:21:33 +0100  614)         super.onResume();
fd396289 (David A. Velasco 2013-05-30 17:53:21 +0200  615)         Log_OC.e(TAG, "onResume() start");
f7649804 (Matthias Baumann 2013-02-25 08:21:33 +0100  616) 
f6780fdc (David A. Velasco 2013-05-07 12:54:31 +0200  617)         // Listen for sync messages
f6780fdc (David A. Velasco 2013-05-07 12:54:31 +0200  618)         IntentFilter syncIntentFilter = new IntentFilter(FileSyncService.SYNC_MESSAGE);
f6780fdc (David A. Velasco 2013-05-07 12:54:31 +0200  619)         mSyncBroadcastReceiver = new SyncBroadcastReceiver();
f6780fdc (David A. Velasco 2013-05-07 12:54:31 +0200  620)         registerReceiver(mSyncBroadcastReceiver, syncIntentFilter);
eee74aa5 (masensio         2013-07-04 18:47:38 +0200  621) 
f6780fdc (David A. Velasco 2013-05-07 12:54:31 +0200  622)         // Listen for upload messages
f6780fdc (David A. Velasco 2013-05-07 12:54:31 +0200  623)         IntentFilter uploadIntentFilter = new IntentFilter(FileUploader.UPLOAD_FINISH_MESSAGE);
f6780fdc (David A. Velasco 2013-05-07 12:54:31 +0200  624)         mUploadFinishReceiver = new UploadFinishReceiver();
f6780fdc (David A. Velasco 2013-05-07 12:54:31 +0200  625)         registerReceiver(mUploadFinishReceiver, uploadIntentFilter);
eee74aa5 (masensio         2013-07-04 18:47:38 +0200  626) 
f6780fdc (David A. Velasco 2013-05-07 12:54:31 +0200  627)         // Listen for download messages
f6780fdc (David A. Velasco 2013-05-07 12:54:31 +0200  628)         IntentFilter downloadIntentFilter = new IntentFilter(FileDownloader.DOWNLOAD_ADDED_MESSAGE);
f6780fdc (David A. Velasco 2013-05-07 12:54:31 +0200  629)         downloadIntentFilter.addAction(FileDownloader.DOWNLOAD_FINISH_MESSAGE);
f6780fdc (David A. Velasco 2013-05-07 12:54:31 +0200  630)         mDownloadFinishReceiver = new DownloadFinishReceiver();
f6780fdc (David A. Velasco 2013-05-07 12:54:31 +0200  631)         registerReceiver(mDownloadFinishReceiver, downloadIntentFilter);
f6780fdc (David A. Velasco 2013-05-07 12:54:31 +0200  632)     
f6780fdc (David A. Velasco 2013-05-07 12:54:31 +0200  633)         Log_OC.d(TAG, "onResume() end");
f7649804 (Matthias Baumann 2013-02-25 08:21:33 +0100  634)     }
f7649804 (Matthias Baumann 2013-02-25 08:21:33 +0100  635) 
eee74aa5 (masensio         2013-07-04 18:47:38 +0200  636) 
f7649804 (Matthias Baumann 2013-02-25 08:21:33 +0100  637)     @Override
f7649804 (Matthias Baumann 2013-02-25 08:21:33 +0100  638)     protected void onPause() {
f7649804 (Matthias Baumann 2013-02-25 08:21:33 +0100  639)         super.onPause();
fd396289 (David A. Velasco 2013-05-30 17:53:21 +0200  640)         Log_OC.e(TAG, "onPause() start");
f7649804 (Matthias Baumann 2013-02-25 08:21:33 +0100  641)         if (mSyncBroadcastReceiver != null) {
f7649804 (Matthias Baumann 2013-02-25 08:21:33 +0100  642)             unregisterReceiver(mSyncBroadcastReceiver);
f7649804 (Matthias Baumann 2013-02-25 08:21:33 +0100  643)             mSyncBroadcastReceiver = null;
f7649804 (Matthias Baumann 2013-02-25 08:21:33 +0100  644)         }
f7649804 (Matthias Baumann 2013-02-25 08:21:33 +0100  645)         if (mUploadFinishReceiver != null) {
f7649804 (Matthias Baumann 2013-02-25 08:21:33 +0100  646)             unregisterReceiver(mUploadFinishReceiver);
f7649804 (Matthias Baumann 2013-02-25 08:21:33 +0100  647)             mUploadFinishReceiver = null;
f7649804 (Matthias Baumann 2013-02-25 08:21:33 +0100  648)         }
f7649804 (Matthias Baumann 2013-02-25 08:21:33 +0100  649)         if (mDownloadFinishReceiver != null) {
f7649804 (Matthias Baumann 2013-02-25 08:21:33 +0100  650)             unregisterReceiver(mDownloadFinishReceiver);
f7649804 (Matthias Baumann 2013-02-25 08:21:33 +0100  651)             mDownloadFinishReceiver = null;
f7649804 (Matthias Baumann 2013-02-25 08:21:33 +0100  652)         }
eee74aa5 (masensio         2013-07-04 18:47:38 +0200  653) 
f6780fdc (David A. Velasco 2013-05-07 12:54:31 +0200  654)         Log_OC.d(TAG, "onPause() end");
f7649804 (Matthias Baumann 2013-02-25 08:21:33 +0100  655)     }
f7649804 (Matthias Baumann 2013-02-25 08:21:33 +0100  656) 
eee74aa5 (masensio         2013-07-04 18:47:38 +0200  657) 
f7649804 (Matthias Baumann 2013-02-25 08:21:33 +0100  658)     @Override
f7649804 (Matthias Baumann 2013-02-25 08:21:33 +0100  659)     protected void onPrepareDialog(int id, Dialog dialog, Bundle args) {
f7649804 (Matthias Baumann 2013-02-25 08:21:33 +0100  660)         if (id == DIALOG_SSL_VALIDATOR && mLastSslUntrustedServerResult != null) {
f7649804 (Matthias Baumann 2013-02-25 08:21:33 +0100  661)             ((SslValidatorDialog)dialog).updateResult(mLastSslUntrustedServerResult);
f7649804 (Matthias Baumann 2013-02-25 08:21:33 +0100  662)         }
f7649804 (Matthias Baumann 2013-02-25 08:21:33 +0100  663)     }
f7649804 (Matthias Baumann 2013-02-25 08:21:33 +0100  664) 
eee74aa5 (masensio         2013-07-04 18:47:38 +0200  665) 
f7649804 (Matthias Baumann 2013-02-25 08:21:33 +0100  666)     @Override
f7649804 (Matthias Baumann 2013-02-25 08:21:33 +0100  667)     protected Dialog onCreateDialog(int id) {
f7649804 (Matthias Baumann 2013-02-25 08:21:33 +0100  668)         Dialog dialog = null;
f7649804 (Matthias Baumann 2013-02-25 08:21:33 +0100  669)         AlertDialog.Builder builder;
f7649804 (Matthias Baumann 2013-02-25 08:21:33 +0100  670)         switch (id) {
f7649804 (Matthias Baumann 2013-02-25 08:21:33 +0100  671)         case DIALOG_SHORT_WAIT: {
f7649804 (Matthias Baumann 2013-02-25 08:21:33 +0100  672)             ProgressDialog working_dialog = new ProgressDialog(this);
8aeb5243 (David A. Velasco 2013-04-12 12:34:17 +0200  673)             working_dialog.setMessage(getResources().getString(
8aeb5243 (David A. Velasco 2013-04-12 12:34:17 +0200  674)                     R.string.wait_a_moment));
f7649804 (Matthias Baumann 2013-02-25 08:21:33 +0100  675)             working_dialog.setIndeterminate(true);
f7649804 (Matthias Baumann 2013-02-25 08:21:33 +0100  676)             working_dialog.setCancelable(false);
f7649804 (Matthias Baumann 2013-02-25 08:21:33 +0100  677)             dialog = working_dialog;
f7649804 (Matthias Baumann 2013-02-25 08:21:33 +0100  678)             break;
f7649804 (Matthias Baumann 2013-02-25 08:21:33 +0100  679)         }
f7649804 (Matthias Baumann 2013-02-25 08:21:33 +0100  680)         case DIALOG_CHOOSE_UPLOAD_SOURCE: {
eee74aa5 (masensio         2013-07-04 18:47:38 +0200  681) 
56f1df02 (David A. Velasco 2013-04-12 12:46:18 +0200  682)             String[] items = null;
eee74aa5 (masensio         2013-07-04 18:47:38 +0200  683) 
56f1df02 (David A. Velasco 2013-04-12 12:46:18 +0200  684)             String[] allTheItems = { getString(R.string.actionbar_upload_files),
eee74aa5 (masensio         2013-07-04 18:47:38 +0200  685)                     getString(R.string.actionbar_upload_from_apps),
eee74aa5 (masensio         2013-07-04 18:47:38 +0200  686)                     getString(R.string.actionbar_failed_instant_upload) };
eee74aa5 (masensio         2013-07-04 18:47:38 +0200  687) 
56f1df02 (David A. Velasco 2013-04-12 12:46:18 +0200  688)             String[] commonItems = { getString(R.string.actionbar_upload_files),
eee74aa5 (masensio         2013-07-04 18:47:38 +0200  689)                     getString(R.string.actionbar_upload_from_apps) };
eee74aa5 (masensio         2013-07-04 18:47:38 +0200  690) 
56f1df02 (David A. Velasco 2013-04-12 12:46:18 +0200  691)             if (InstantUploadActivity.IS_ENABLED)
56f1df02 (David A. Velasco 2013-04-12 12:46:18 +0200  692)                 items = allTheItems;
56f1df02 (David A. Velasco 2013-04-12 12:46:18 +0200  693)             else 
56f1df02 (David A. Velasco 2013-04-12 12:46:18 +0200  694)                 items = commonItems;
eee74aa5 (masensio         2013-07-04 18:47:38 +0200  695) 
f7649804 (Matthias Baumann 2013-02-25 08:21:33 +0100  696)             builder = new AlertDialog.Builder(this);
f7649804 (Matthias Baumann 2013-02-25 08:21:33 +0100  697)             builder.setTitle(R.string.actionbar_upload);
f7649804 (Matthias Baumann 2013-02-25 08:21:33 +0100  698)             builder.setItems(items, new DialogInterface.OnClickListener() {
f7649804 (Matthias Baumann 2013-02-25 08:21:33 +0100  699)                 public void onClick(DialogInterface dialog, int item) {
f7649804 (Matthias Baumann 2013-02-25 08:21:33 +0100  700)                     if (item == 0) {
f7649804 (Matthias Baumann 2013-02-25 08:21:33 +0100  701)                         // if (!mDualPane) {
eee74aa5 (masensio         2013-07-04 18:47:38 +0200  702)                             Intent action = new Intent(FileDisplayActivity.this, UploadFilesActivity.class);
eee74aa5 (masensio         2013-07-04 18:47:38 +0200  703)                             action.putExtra(UploadFilesActivity.EXTRA_ACCOUNT, FileDisplayActivity.this.getAccount());
eee74aa5 (masensio         2013-07-04 18:47:38 +0200  704)                             startActivityForResult(action, ACTION_SELECT_MULTIPLE_FILES);
eee74aa5 (masensio         2013-07-04 18:47:38 +0200  705)                             // } else {
eee74aa5 (masensio         2013-07-04 18:47:38 +0200  706)                             // TODO create and handle new fragment
eee74aa5 (masensio         2013-07-04 18:47:38 +0200  707)                             // LocalFileListFragment
eee74aa5 (masensio         2013-07-04 18:47:38 +0200  708)                             // }
f7649804 (Matthias Baumann 2013-02-25 08:21:33 +0100  709)                     } else if (item == 1) {
f7649804 (Matthias Baumann 2013-02-25 08:21:33 +0100  710)                         Intent action = new Intent(Intent.ACTION_GET_CONTENT);
f7649804 (Matthias Baumann 2013-02-25 08:21:33 +0100  711)                         action = action.setType("*/*").addCategory(Intent.CATEGORY_OPENABLE);
f7649804 (Matthias Baumann 2013-02-25 08:21:33 +0100  712)                         startActivityForResult(Intent.createChooser(action, getString(R.string.upload_chooser_title)),
f7649804 (Matthias Baumann 2013-02-25 08:21:33 +0100  713)                                 ACTION_SELECT_CONTENT_FROM_APPS);
56f1df02 (David A. Velasco 2013-04-12 12:46:18 +0200  714)                     } else if (item == 2 && InstantUploadActivity.IS_ENABLED) {
f7649804 (Matthias Baumann 2013-02-25 08:21:33 +0100  715)                         Intent action = new Intent(FileDisplayActivity.this, InstantUploadActivity.class);
f6780fdc (David A. Velasco 2013-05-07 12:54:31 +0200  716)                         action.putExtra(FileUploader.KEY_ACCOUNT, FileDisplayActivity.this.getAccount());
f7649804 (Matthias Baumann 2013-02-25 08:21:33 +0100  717)                         startActivity(action);
f7649804 (Matthias Baumann 2013-02-25 08:21:33 +0100  718)                     }
f7649804 (Matthias Baumann 2013-02-25 08:21:33 +0100  719)                 }
f7649804 (Matthias Baumann 2013-02-25 08:21:33 +0100  720)             });
f7649804 (Matthias Baumann 2013-02-25 08:21:33 +0100  721)             dialog = builder.create();
f7649804 (Matthias Baumann 2013-02-25 08:21:33 +0100  722)             break;
f7649804 (Matthias Baumann 2013-02-25 08:21:33 +0100  723)         }
f7649804 (Matthias Baumann 2013-02-25 08:21:33 +0100  724)         case DIALOG_SSL_VALIDATOR: {
f7649804 (Matthias Baumann 2013-02-25 08:21:33 +0100  725)             dialog = SslValidatorDialog.newInstance(this, mLastSslUntrustedServerResult, this);
f7649804 (Matthias Baumann 2013-02-25 08:21:33 +0100  726)             break;
f7649804 (Matthias Baumann 2013-02-25 08:21:33 +0100  727)         }
f7649804 (Matthias Baumann 2013-02-25 08:21:33 +0100  728)         case DIALOG_CERT_NOT_SAVED: {
f7649804 (Matthias Baumann 2013-02-25 08:21:33 +0100  729)             builder = new AlertDialog.Builder(this);
f7649804 (Matthias Baumann 2013-02-25 08:21:33 +0100  730)             builder.setMessage(getResources().getString(R.string.ssl_validator_not_saved));
f7649804 (Matthias Baumann 2013-02-25 08:21:33 +0100  731)             builder.setCancelable(false);
f7649804 (Matthias Baumann 2013-02-25 08:21:33 +0100  732)             builder.setPositiveButton(R.string.common_ok, new DialogInterface.OnClickListener() {
eee74aa5 (masensio         2013-07-04 18:47:38 +0200  733)                 @Override
eee74aa5 (masensio         2013-07-04 18:47:38 +0200  734)                 public void onClick(DialogInterface dialog, int which) {
eee74aa5 (masensio         2013-07-04 18:47:38 +0200  735)                     dialog.dismiss();
eee74aa5 (masensio         2013-07-04 18:47:38 +0200  736)                 };
eee74aa5 (masensio         2013-07-04 18:47:38 +0200  737)             });
f7649804 (Matthias Baumann 2013-02-25 08:21:33 +0100  738)             dialog = builder.create();
f7649804 (Matthias Baumann 2013-02-25 08:21:33 +0100  739)             break;
f7649804 (Matthias Baumann 2013-02-25 08:21:33 +0100  740)         }
f7649804 (Matthias Baumann 2013-02-25 08:21:33 +0100  741)         default:
f7649804 (Matthias Baumann 2013-02-25 08:21:33 +0100  742)             dialog = null;
f7649804 (Matthias Baumann 2013-02-25 08:21:33 +0100  743)         }
eee74aa5 (masensio         2013-07-04 18:47:38 +0200  744) 
f7649804 (Matthias Baumann 2013-02-25 08:21:33 +0100  745)         return dialog;
f7649804 (Matthias Baumann 2013-02-25 08:21:33 +0100  746)     }
f7649804 (Matthias Baumann 2013-02-25 08:21:33 +0100  747) 
eee74aa5 (masensio         2013-07-04 18:47:38 +0200  748) 
f7649804 (Matthias Baumann 2013-02-25 08:21:33 +0100  749)     /**
f7649804 (Matthias Baumann 2013-02-25 08:21:33 +0100  750)      * Translates a content URI of an image to a physical path
f7649804 (Matthias Baumann 2013-02-25 08:21:33 +0100  751)      * on the disk
f7649804 (Matthias Baumann 2013-02-25 08:21:33 +0100  752)      * @param uri The URI to resolve
f7649804 (Matthias Baumann 2013-02-25 08:21:33 +0100  753)      * @return The path to the image or null if it could not be found
f7649804 (Matthias Baumann 2013-02-25 08:21:33 +0100  754)      */
f7649804 (Matthias Baumann 2013-02-25 08:21:33 +0100  755)     public String getPath(Uri uri) {
f7649804 (Matthias Baumann 2013-02-25 08:21:33 +0100  756)         String[] projection = { MediaStore.Images.Media.DATA };
f7649804 (Matthias Baumann 2013-02-25 08:21:33 +0100  757)         Cursor cursor = managedQuery(uri, projection, null, null, null);
f7649804 (Matthias Baumann 2013-02-25 08:21:33 +0100  758)         if (cursor != null) {
8aeb5243 (David A. Velasco 2013-04-12 12:34:17 +0200  759)             int column_index = cursor
8aeb5243 (David A. Velasco 2013-04-12 12:34:17 +0200  760)                     .getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
f7649804 (Matthias Baumann 2013-02-25 08:21:33 +0100  761)             cursor.moveToFirst();
f7649804 (Matthias Baumann 2013-02-25 08:21:33 +0100  762)             return cursor.getString(column_index);
8aeb5243 (David A. Velasco 2013-04-12 12:34:17 +0200  763)         } 
f7649804 (Matthias Baumann 2013-02-25 08:21:33 +0100  764)         return null;
f7649804 (Matthias Baumann 2013-02-25 08:21:33 +0100  765)     }
eee74aa5 (masensio         2013-07-04 18:47:38 +0200  766) 
f7649804 (Matthias Baumann 2013-02-25 08:21:33 +0100  767)     /**
f7649804 (Matthias Baumann 2013-02-25 08:21:33 +0100  768)      * Pushes a directory to the drop down list
f7649804 (Matthias Baumann 2013-02-25 08:21:33 +0100  769)      * @param directory to push
f7649804 (Matthias Baumann 2013-02-25 08:21:33 +0100  770)      * @throws IllegalArgumentException If the {@link OCFile#isDirectory()} returns false.
f7649804 (Matthias Baumann 2013-02-25 08:21:33 +0100  771)      */
f7649804 (Matthias Baumann 2013-02-25 08:21:33 +0100  772)     public void pushDirname(OCFile directory) {
f7649804 (Matthias Baumann 2013-02-25 08:21:33 +0100  773)         if(!directory.isDirectory()){
f7649804 (Matthias Baumann 2013-02-25 08:21:33 +0100  774)             throw new IllegalArgumentException("Only directories may be pushed!");
f7649804 (Matthias Baumann 2013-02-25 08:21:33 +0100  775)         }
f7649804 (Matthias Baumann 2013-02-25 08:21:33 +0100  776)         mDirectories.insert(directory.getFileName(), 0);
f6780fdc (David A. Velasco 2013-05-07 12:54:31 +0200  777)         setFile(directory);
f7649804 (Matthias Baumann 2013-02-25 08:21:33 +0100  778)     }
f7649804 (Matthias Baumann 2013-02-25 08:21:33 +0100  779) 
f7649804 (Matthias Baumann 2013-02-25 08:21:33 +0100  780)     /**
f7649804 (Matthias Baumann 2013-02-25 08:21:33 +0100  781)      * Pops a directory name from the drop down list
f7649804 (Matthias Baumann 2013-02-25 08:21:33 +0100  782)      * @return True, unless the stack is empty
f7649804 (Matthias Baumann 2013-02-25 08:21:33 +0100  783)      */
f7649804 (Matthias Baumann 2013-02-25 08:21:33 +0100  784)     public boolean popDirname() {
f7649804 (Matthias Baumann 2013-02-25 08:21:33 +0100  785)         mDirectories.remove(mDirectories.getItem(0));
f7649804 (Matthias Baumann 2013-02-25 08:21:33 +0100  786)         return !mDirectories.isEmpty();
f7649804 (Matthias Baumann 2013-02-25 08:21:33 +0100  787)     }
f7649804 (Matthias Baumann 2013-02-25 08:21:33 +0100  788) 
f7649804 (Matthias Baumann 2013-02-25 08:21:33 +0100  789)     // Custom array adapter to override text colors
f7649804 (Matthias Baumann 2013-02-25 08:21:33 +0100  790)     private class CustomArrayAdapter<T> extends ArrayAdapter<T> {
eee74aa5 (masensio         2013-07-04 18:47:38 +0200  791) 
f7649804 (Matthias Baumann 2013-02-25 08:21:33 +0100  792)         public CustomArrayAdapter(FileDisplayActivity ctx, int view) {
f7649804 (Matthias Baumann 2013-02-25 08:21:33 +0100  793)             super(ctx, view);
f7649804 (Matthias Baumann 2013-02-25 08:21:33 +0100  794)         }
eee74aa5 (masensio         2013-07-04 18:47:38 +0200  795) 
f7649804 (Matthias Baumann 2013-02-25 08:21:33 +0100  796)         public View getView(int position, View convertView, ViewGroup parent) {
f7649804 (Matthias Baumann 2013-02-25 08:21:33 +0100  797)             View v = super.getView(position, convertView, parent);
eee74aa5 (masensio         2013-07-04 18:47:38 +0200  798) 
8aeb5243 (David A. Velasco 2013-04-12 12:34:17 +0200  799)             ((TextView) v).setTextColor(getResources().getColorStateList(
8aeb5243 (David A. Velasco 2013-04-12 12:34:17 +0200  800)                     android.R.color.white));
f7649804 (Matthias Baumann 2013-02-25 08:21:33 +0100  801)             return v;
f7649804 (Matthias Baumann 2013-02-25 08:21:33 +0100  802)         }
eee74aa5 (masensio         2013-07-04 18:47:38 +0200  803) 
8aeb5243 (David A. Velasco 2013-04-12 12:34:17 +0200  804)         public View getDropDownView(int position, View convertView,
8aeb5243 (David A. Velasco 2013-04-12 12:34:17 +0200  805)                 ViewGroup parent) {
f7649804 (Matthias Baumann 2013-02-25 08:21:33 +0100  806)             View v = super.getDropDownView(position, convertView, parent);
eee74aa5 (masensio         2013-07-04 18:47:38 +0200  807) 
8aeb5243 (David A. Velasco 2013-04-12 12:34:17 +0200  808)             ((TextView) v).setTextColor(getResources().getColorStateList(
8aeb5243 (David A. Velasco 2013-04-12 12:34:17 +0200  809)                     android.R.color.white));
eee74aa5 (masensio         2013-07-04 18:47:38 +0200  810) 
f7649804 (Matthias Baumann 2013-02-25 08:21:33 +0100  811)             return v;
f7649804 (Matthias Baumann 2013-02-25 08:21:33 +0100  812)         }
eee74aa5 (masensio         2013-07-04 18:47:38 +0200  813) 
f7649804 (Matthias Baumann 2013-02-25 08:21:33 +0100  814)     }
f7649804 (Matthias Baumann 2013-02-25 08:21:33 +0100  815) 
f7649804 (Matthias Baumann 2013-02-25 08:21:33 +0100  816)     private class SyncBroadcastReceiver extends BroadcastReceiver {
f7649804 (Matthias Baumann 2013-02-25 08:21:33 +0100  817) 
f7649804 (Matthias Baumann 2013-02-25 08:21:33 +0100  818)         /**
f7649804 (Matthias Baumann 2013-02-25 08:21:33 +0100  819)          * {@link BroadcastReceiver} to enable syncing feedback in UI
f7649804 (Matthias Baumann 2013-02-25 08:21:33 +0100  820)          */
f7649804 (Matthias Baumann 2013-02-25 08:21:33 +0100  821)         @Override
f7649804 (Matthias Baumann 2013-02-25 08:21:33 +0100  822)         public void onReceive(Context context, Intent intent) {
f7649804 (Matthias Baumann 2013-02-25 08:21:33 +0100  823)             boolean inProgress = intent.getBooleanExtra(FileSyncService.IN_PROGRESS, false);
f7649804 (Matthias Baumann 2013-02-25 08:21:33 +0100  824)             String accountName = intent.getStringExtra(FileSyncService.ACCOUNT_NAME);
f7649804 (Matthias Baumann 2013-02-25 08:21:33 +0100  825) 
fd396289 (David A. Velasco 2013-05-30 17:53:21 +0200  826)             Log_OC.d(TAG, "sync of account " + accountName + " is in_progress: " + inProgress);
f7649804 (Matthias Baumann 2013-02-25 08:21:33 +0100  827) 
f6780fdc (David A. Velasco 2013-05-07 12:54:31 +0200  828)             if (getAccount() != null && accountName.equals(getAccount().name)) {  
eee74aa5 (masensio         2013-07-04 18:47:38 +0200  829) 
8aeb5243 (David A. Velasco 2013-04-12 12:34:17 +0200  830)                 String synchFolderRemotePath = intent.getStringExtra(FileSyncService.SYNC_FOLDER_REMOTE_PATH); 
eee74aa5 (masensio         2013-07-04 18:47:38 +0200  831) 
f7649804 (Matthias Baumann 2013-02-25 08:21:33 +0100  832)                 boolean fillBlankRoot = false;
16f121a7 (David A. Velasco 2013-06-05 09:59:48 +0200  833)                 OCFile currentDir = getCurrentDir();
f6780fdc (David A. Velasco 2013-05-07 12:54:31 +0200  834)                 if (currentDir == null) {
f6780fdc (David A. Velasco 2013-05-07 12:54:31 +0200  835)                     currentDir = mStorageManager.getFileByPath(OCFile.PATH_SEPARATOR);
eee74aa5 (masensio         2013-07-04 18:47:38 +0200  836)                     fillBlankRoot = (currentDir != null);                   
f7649804 (Matthias Baumann 2013-02-25 08:21:33 +0100  837)                 }
f7649804 (Matthias Baumann 2013-02-25 08:21:33 +0100  838) 
f6780fdc (David A. Velasco 2013-05-07 12:54:31 +0200  839)                 if ((synchFolderRemotePath != null && currentDir != null && (currentDir.getRemotePath().equals(synchFolderRemotePath)))
8aeb5243 (David A. Velasco 2013-04-12 12:34:17 +0200  840)                         || fillBlankRoot ) {
8aeb5243 (David A. Velasco 2013-04-12 12:34:17 +0200  841)                     if (!fillBlankRoot) 
f6780fdc (David A. Velasco 2013-05-07 12:54:31 +0200  842)                         currentDir = getStorageManager().getFileByPath(synchFolderRemotePath);
fd396289 (David A. Velasco 2013-05-30 17:53:21 +0200  843)                     OCFileListFragment fileListFragment = getListOfFilesFragment();
f7649804 (Matthias Baumann 2013-02-25 08:21:33 +0100  844)                     if (fileListFragment != null) {
f6780fdc (David A. Velasco 2013-05-07 12:54:31 +0200  845)                         fileListFragment.listDirectory(currentDir);
f7649804 (Matthias Baumann 2013-02-25 08:21:33 +0100  846)                     }
82375a91 (David A. Velasco 2013-07-02 12:41:10 +0200  847)                     if (getSecondFragment() == null)
82375a91 (David A. Velasco 2013-07-02 12:41:10 +0200  848)                         setFile(currentDir);
f7649804 (Matthias Baumann 2013-02-25 08:21:33 +0100  849)                 }
f7649804 (Matthias Baumann 2013-02-25 08:21:33 +0100  850)                 
f7649804 (Matthias Baumann 2013-02-25 08:21:33 +0100  851)                 setSupportProgressBarIndeterminateVisibility(inProgress);
f7649804 (Matthias Baumann 2013-02-25 08:21:33 +0100  852)                 removeStickyBroadcast(intent);
eee74aa5 (masensio         2013-07-04 18:47:38 +0200  853) 
f7649804 (Matthias Baumann 2013-02-25 08:21:33 +0100  854)             }
eee74aa5 (masensio         2013-07-04 18:47:38 +0200  855) 
8aeb5243 (David A. Velasco 2013-04-12 12:34:17 +0200  856)             RemoteOperationResult synchResult = (RemoteOperationResult)intent.getSerializableExtra(FileSyncService.SYNC_RESULT);
f7649804 (Matthias Baumann 2013-02-25 08:21:33 +0100  857)             if (synchResult != null) {
f7649804 (Matthias Baumann 2013-02-25 08:21:33 +0100  858)                 if (synchResult.getCode().equals(RemoteOperationResult.ResultCode.SSL_RECOVERABLE_PEER_UNVERIFIED)) {
f7649804 (Matthias Baumann 2013-02-25 08:21:33 +0100  859)                     mLastSslUntrustedServerResult = synchResult;
8aeb5243 (David A. Velasco 2013-04-12 12:34:17 +0200  860)                     showDialog(DIALOG_SSL_VALIDATOR); 
f7649804 (Matthias Baumann 2013-02-25 08:21:33 +0100  861)                 }
f7649804 (Matthias Baumann 2013-02-25 08:21:33 +0100  862)             }
f7649804 (Matthias Baumann 2013-02-25 08:21:33 +0100  863)         }
f7649804 (Matthias Baumann 2013-02-25 08:21:33 +0100  864)     }
eee74aa5 (masensio         2013-07-04 18:47:38 +0200  865) 
f7649804 (Matthias Baumann 2013-02-25 08:21:33 +0100  866) 
f7649804 (Matthias Baumann 2013-02-25 08:21:33 +0100  867)     private class UploadFinishReceiver extends BroadcastReceiver {
f7649804 (Matthias Baumann 2013-02-25 08:21:33 +0100  868)         /**
f7649804 (Matthias Baumann 2013-02-25 08:21:33 +0100  869)          * Once the file upload has finished -> update view
f7649804 (Matthias Baumann 2013-02-25 08:21:33 +0100  870)          *  @author David A. Velasco
f7649804 (Matthias Baumann 2013-02-25 08:21:33 +0100  871)          * {@link BroadcastReceiver} to enable upload feedback in UI
f7649804 (Matthias Baumann 2013-02-25 08:21:33 +0100  872)          */
f7649804 (Matthias Baumann 2013-02-25 08:21:33 +0100  873)         @Override
f7649804 (Matthias Baumann 2013-02-25 08:21:33 +0100  874)         public void onReceive(Context context, Intent intent) {
f7649804 (Matthias Baumann 2013-02-25 08:21:33 +0100  875)             String uploadedRemotePath = intent.getStringExtra(FileDownloader.EXTRA_REMOTE_PATH);
f7649804 (Matthias Baumann 2013-02-25 08:21:33 +0100  876)             String accountName = intent.getStringExtra(FileUploader.ACCOUNT_NAME);
f6780fdc (David A. Velasco 2013-05-07 12:54:31 +0200  877)             boolean sameAccount = getAccount() != null && accountName.equals(getAccount().name);
16f121a7 (David A. Velasco 2013-06-05 09:59:48 +0200  878)             OCFile currentDir = getCurrentDir();
16f121a7 (David A. Velasco 2013-06-05 09:59:48 +0200  879)             boolean isDescendant = (currentDir != null) && (uploadedRemotePath != null) && (uploadedRemotePath.startsWith(currentDir.getRemotePath()));
f7649804 (Matthias Baumann 2013-02-25 08:21:33 +0100  880)             if (sameAccount && isDescendant) {
fd396289 (David A. Velasco 2013-05-30 17:53:21 +0200  881)                 refeshListOfFilesFragment();
f7649804 (Matthias Baumann 2013-02-25 08:21:33 +0100  882)             }
f7649804 (Matthias Baumann 2013-02-25 08:21:33 +0100  883)         }
eee74aa5 (masensio         2013-07-04 18:47:38 +0200  884) 
f7649804 (Matthias Baumann 2013-02-25 08:21:33 +0100  885)     }
eee74aa5 (masensio         2013-07-04 18:47:38 +0200  886) 
eee74aa5 (masensio         2013-07-04 18:47:38 +0200  887) 
f7649804 (Matthias Baumann 2013-02-25 08:21:33 +0100  888)     /**
56f1df02 (David A. Velasco 2013-04-12 12:46:18 +0200  889)      * Class waiting for broadcast events from the {@link FielDownloader} service.
56f1df02 (David A. Velasco 2013-04-12 12:46:18 +0200  890)      * 
56f1df02 (David A. Velasco 2013-04-12 12:46:18 +0200  891)      * Updates the UI when a download is started or finished, provided that it is relevant for the
56f1df02 (David A. Velasco 2013-04-12 12:46:18 +0200  892)      * current folder.
f7649804 (Matthias Baumann 2013-02-25 08:21:33 +0100  893)      */
f7649804 (Matthias Baumann 2013-02-25 08:21:33 +0100  894)     private class DownloadFinishReceiver extends BroadcastReceiver {
f7649804 (Matthias Baumann 2013-02-25 08:21:33 +0100  895)         @Override
f7649804 (Matthias Baumann 2013-02-25 08:21:33 +0100  896)         public void onReceive(Context context, Intent intent) {
56f1df02 (David A. Velasco 2013-04-12 12:46:18 +0200  897)             boolean sameAccount = isSameAccount(context, intent);
f7649804 (Matthias Baumann 2013-02-25 08:21:33 +0100  898)             String downloadedRemotePath = intent.getStringExtra(FileDownloader.EXTRA_REMOTE_PATH);
56f1df02 (David A. Velasco 2013-04-12 12:46:18 +0200  899)             boolean isDescendant = isDescendant(downloadedRemotePath);
eee74aa5 (masensio         2013-07-04 18:47:38 +0200  900) 
f7649804 (Matthias Baumann 2013-02-25 08:21:33 +0100  901)             if (sameAccount && isDescendant) {
fd396289 (David A. Velasco 2013-05-30 17:53:21 +0200  902)                 refeshListOfFilesFragment();
c70f3333 (David A. Velasco 2013-06-04 14:30:29 +0200  903)                 refreshSecondFragment(intent.getAction(), downloadedRemotePath, intent.getBooleanExtra(FileDownloader.EXTRA_DOWNLOAD_RESULT, false));
f7649804 (Matthias Baumann 2013-02-25 08:21:33 +0100  904)             }
eee74aa5 (masensio         2013-07-04 18:47:38 +0200  905) 
56f1df02 (David A. Velasco 2013-04-12 12:46:18 +0200  906)             removeStickyBroadcast(intent);
56f1df02 (David A. Velasco 2013-04-12 12:46:18 +0200  907)         }
56f1df02 (David A. Velasco 2013-04-12 12:46:18 +0200  908) 
56f1df02 (David A. Velasco 2013-04-12 12:46:18 +0200  909)         private boolean isDescendant(String downloadedRemotePath) {
16f121a7 (David A. Velasco 2013-06-05 09:59:48 +0200  910)             OCFile currentDir = getCurrentDir();
16f121a7 (David A. Velasco 2013-06-05 09:59:48 +0200  911)             return (currentDir != null && downloadedRemotePath != null && downloadedRemotePath.startsWith(currentDir.getRemotePath()));
56f1df02 (David A. Velasco 2013-04-12 12:46:18 +0200  912)         }
56f1df02 (David A. Velasco 2013-04-12 12:46:18 +0200  913) 
56f1df02 (David A. Velasco 2013-04-12 12:46:18 +0200  914)         private boolean isSameAccount(Context context, Intent intent) {
56f1df02 (David A. Velasco 2013-04-12 12:46:18 +0200  915)             String accountName = intent.getStringExtra(FileDownloader.ACCOUNT_NAME);
f6780fdc (David A. Velasco 2013-05-07 12:54:31 +0200  916)             return (accountName != null && getAccount() != null && accountName.equals(getAccount().name));
f7649804 (Matthias Baumann 2013-02-25 08:21:33 +0100  917)         }
f7649804 (Matthias Baumann 2013-02-25 08:21:33 +0100  918)     }
eee74aa5 (masensio         2013-07-04 18:47:38 +0200  919) 
eee74aa5 (masensio         2013-07-04 18:47:38 +0200  920) 
f7649804 (Matthias Baumann 2013-02-25 08:21:33 +0100  921)     /**
f7649804 (Matthias Baumann 2013-02-25 08:21:33 +0100  922)      * {@inheritDoc}
f7649804 (Matthias Baumann 2013-02-25 08:21:33 +0100  923)      */
f7649804 (Matthias Baumann 2013-02-25 08:21:33 +0100  924)     @Override
f7649804 (Matthias Baumann 2013-02-25 08:21:33 +0100  925)     public DataStorageManager getStorageManager() {
f7649804 (Matthias Baumann 2013-02-25 08:21:33 +0100  926)         return mStorageManager;
f7649804 (Matthias Baumann 2013-02-25 08:21:33 +0100  927)     }
eee74aa5 (masensio         2013-07-04 18:47:38 +0200  928) 
56f1df02 (David A. Velasco 2013-04-12 12:46:18 +0200  929) 
f7649804 (Matthias Baumann 2013-02-25 08:21:33 +0100  930)     /**
f7649804 (Matthias Baumann 2013-02-25 08:21:33 +0100  931)      * {@inheritDoc}
fd396289 (David A. Velasco 2013-05-30 17:53:21 +0200  932)      * 
fd396289 (David A. Velasco 2013-05-30 17:53:21 +0200  933)      * Updates action bar and second fragment, if in dual pane mode.
f7649804 (Matthias Baumann 2013-02-25 08:21:33 +0100  934)      */
f7649804 (Matthias Baumann 2013-02-25 08:21:33 +0100  935)     @Override
fd396289 (David A. Velasco 2013-05-30 17:53:21 +0200  936)     public void onBrowsedDownTo(OCFile directory) {
f7649804 (Matthias Baumann 2013-02-25 08:21:33 +0100  937)         pushDirname(directory);
fd396289 (David A. Velasco 2013-05-30 17:53:21 +0200  938)         cleanSecondFragment();
f7649804 (Matthias Baumann 2013-02-25 08:21:33 +0100  939)     }
eee74aa5 (masensio         2013-07-04 18:47:38 +0200  940) 
f7649804 (Matthias Baumann 2013-02-25 08:21:33 +0100  941)     /**
fd396289 (David A. Velasco 2013-05-30 17:53:21 +0200  942)      * Opens the image gallery showing the image {@link OCFile} received as parameter.
fd396289 (David A. Velasco 2013-05-30 17:53:21 +0200  943)      * 
fd396289 (David A. Velasco 2013-05-30 17:53:21 +0200  944)      * @param file                      Image {@link OCFile} to show.
f7649804 (Matthias Baumann 2013-02-25 08:21:33 +0100  945)      */
f7649804 (Matthias Baumann 2013-02-25 08:21:33 +0100  946)     @Override
fd396289 (David A. Velasco 2013-05-30 17:53:21 +0200  947)     public void startImagePreview(OCFile file) {
56f1df02 (David A. Velasco 2013-04-12 12:46:18 +0200  948)         Intent showDetailsIntent = new Intent(this, PreviewImageActivity.class);
bc1fcf84 (David A. Velasco 2013-05-07 13:49:54 +0200  949)         showDetailsIntent.putExtra(EXTRA_FILE, file);
bc1fcf84 (David A. Velasco 2013-05-07 13:49:54 +0200  950)         showDetailsIntent.putExtra(EXTRA_ACCOUNT, getAccount());
56f1df02 (David A. Velasco 2013-04-12 12:46:18 +0200  951)         startActivity(showDetailsIntent);
56f1df02 (David A. Velasco 2013-04-12 12:46:18 +0200  952)     }
eee74aa5 (masensio         2013-07-04 18:47:38 +0200  953) 
fd396289 (David A. Velasco 2013-05-30 17:53:21 +0200  954)     /**
fd396289 (David A. Velasco 2013-05-30 17:53:21 +0200  955)      * Stars the preview of an already down media {@link OCFile}.
fd396289 (David A. Velasco 2013-05-30 17:53:21 +0200  956)      * 
fd396289 (David A. Velasco 2013-05-30 17:53:21 +0200  957)      * @param file                      Media {@link OCFile} to preview.
fd396289 (David A. Velasco 2013-05-30 17:53:21 +0200  958)      * @param startPlaybackPosition     Media position where the playback will be started, in milliseconds.
fd396289 (David A. Velasco 2013-05-30 17:53:21 +0200  959)      * @param autoplay                  When 'true', the playback will start without user interactions.
fd396289 (David A. Velasco 2013-05-30 17:53:21 +0200  960)      */
fd396289 (David A. Velasco 2013-05-30 17:53:21 +0200  961)     @Override
fd396289 (David A. Velasco 2013-05-30 17:53:21 +0200  962)     public void startMediaPreview(OCFile file, int startPlaybackPosition, boolean autoplay) {
fd396289 (David A. Velasco 2013-05-30 17:53:21 +0200  963)         Fragment mediaFragment = new PreviewMediaFragment(file, getAccount(), startPlaybackPosition, autoplay);
fd396289 (David A. Velasco 2013-05-30 17:53:21 +0200  964)         setSecondFragment(mediaFragment);
c70f3333 (David A. Velasco 2013-06-04 14:30:29 +0200  965)         updateFragmentsVisibility(true);
16f121a7 (David A. Velasco 2013-06-05 09:59:48 +0200  966)         updateNavigationElementsInActionBar(file);
16f121a7 (David A. Velasco 2013-06-05 09:59:48 +0200  967)         setFile(file);
f7649804 (Matthias Baumann 2013-02-25 08:21:33 +0100  968)     }
eee74aa5 (masensio         2013-07-04 18:47:38 +0200  969) 
fd396289 (David A. Velasco 2013-05-30 17:53:21 +0200  970)     /**
fd396289 (David A. Velasco 2013-05-30 17:53:21 +0200  971)      * Requests the download of the received {@link OCFile} , updates the UI
fd396289 (David A. Velasco 2013-05-30 17:53:21 +0200  972)      * to monitor the download progress and prepares the activity to preview
fd396289 (David A. Velasco 2013-05-30 17:53:21 +0200  973)      * or open the file when the download finishes.
fd396289 (David A. Velasco 2013-05-30 17:53:21 +0200  974)      * 
fd396289 (David A. Velasco 2013-05-30 17:53:21 +0200  975)      * @param file          {@link OCFile} to download and preview.
fd396289 (David A. Velasco 2013-05-30 17:53:21 +0200  976)      */
fd396289 (David A. Velasco 2013-05-30 17:53:21 +0200  977)     @Override
fd396289 (David A. Velasco 2013-05-30 17:53:21 +0200  978)     public void startDownloadForPreview(OCFile file) {
fd396289 (David A. Velasco 2013-05-30 17:53:21 +0200  979)         Fragment detailFragment = new FileDetailFragment(file, getAccount());
fd396289 (David A. Velasco 2013-05-30 17:53:21 +0200  980)         setSecondFragment(detailFragment);
fd396289 (David A. Velasco 2013-05-30 17:53:21 +0200  981)         mWaitingToPreview = file;
fd396289 (David A. Velasco 2013-05-30 17:53:21 +0200  982)         requestForDownload();
c70f3333 (David A. Velasco 2013-06-04 14:30:29 +0200  983)         updateFragmentsVisibility(true);
16f121a7 (David A. Velasco 2013-06-05 09:59:48 +0200  984)         updateNavigationElementsInActionBar(file);
16f121a7 (David A. Velasco 2013-06-05 09:59:48 +0200  985)         setFile(file);
fd396289 (David A. Velasco 2013-05-30 17:53:21 +0200  986)     }
fd396289 (David A. Velasco 2013-05-30 17:53:21 +0200  987) 
eee74aa5 (masensio         2013-07-04 18:47:38 +0200  988) 
fd396289 (David A. Velasco 2013-05-30 17:53:21 +0200  989)     /**
fd396289 (David A. Velasco 2013-05-30 17:53:21 +0200  990)      * Shows the information of the {@link OCFile} received as a 
fd396289 (David A. Velasco 2013-05-30 17:53:21 +0200  991)      * parameter in the second fragment.
fd396289 (David A. Velasco 2013-05-30 17:53:21 +0200  992)      * 
fd396289 (David A. Velasco 2013-05-30 17:53:21 +0200  993)      * @param file          {@link OCFile} whose details will be shown
fd396289 (David A. Velasco 2013-05-30 17:53:21 +0200  994)      */
fd396289 (David A. Velasco 2013-05-30 17:53:21 +0200  995)     @Override
fd396289 (David A. Velasco 2013-05-30 17:53:21 +0200  996)     public void showDetails(OCFile file) {
fd396289 (David A. Velasco 2013-05-30 17:53:21 +0200  997)         Fragment detailFragment = new FileDetailFragment(file, getAccount());
fd396289 (David A. Velasco 2013-05-30 17:53:21 +0200  998)         setSecondFragment(detailFragment);
c70f3333 (David A. Velasco 2013-06-04 14:30:29 +0200  999)         updateFragmentsVisibility(true);
16f121a7 (David A. Velasco 2013-06-05 09:59:48 +0200 1000)         updateNavigationElementsInActionBar(file);
16f121a7 (David A. Velasco 2013-06-05 09:59:48 +0200 1001)         setFile(file);
fd396289 (David A. Velasco 2013-05-30 17:53:21 +0200 1002)     }
eee74aa5 (masensio         2013-07-04 18:47:38 +0200 1003) 
eee74aa5 (masensio         2013-07-04 18:47:38 +0200 1004) 
fd396289 (David A. Velasco 2013-05-30 17:53:21 +0200 1005)     /**
fd396289 (David A. Velasco 2013-05-30 17:53:21 +0200 1006)      * TODO
fd396289 (David A. Velasco 2013-05-30 17:53:21 +0200 1007)      */
10b014a1 (David A. Velasco 2013-06-17 11:16:39 +0200 1008)     private void updateNavigationElementsInActionBar(OCFile chosenFile) {
fd396289 (David A. Velasco 2013-05-30 17:53:21 +0200 1009)         ActionBar actionBar = getSupportActionBar(); 
10b014a1 (David A. Velasco 2013-06-17 11:16:39 +0200 1010)         if (chosenFile == null || mDualPane) {
fd396289 (David A. Velasco 2013-05-30 17:53:21 +0200 1011)             // only list of files - set for browsing through folders
16f121a7 (David A. Velasco 2013-06-05 09:59:48 +0200 1012)             OCFile currentDir = getCurrentDir();
fd396289 (David A. Velasco 2013-05-30 17:53:21 +0200 1013)             actionBar.setDisplayHomeAsUpEnabled(currentDir != null && currentDir.getParentId() != 0);
fd396289 (David A. Velasco 2013-05-30 17:53:21 +0200 1014)             actionBar.setDisplayShowTitleEnabled(false);
fd396289 (David A. Velasco 2013-05-30 17:53:21 +0200 1015)             actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_LIST);
c70f3333 (David A. Velasco 2013-06-04 14:30:29 +0200 1016)             actionBar.setListNavigationCallbacks(mDirectories, this);   // assuming mDirectories is updated
eee74aa5 (masensio         2013-07-04 18:47:38 +0200 1017) 
56f1df02 (David A. Velasco 2013-04-12 12:46:18 +0200 1018)         } else {
fd396289 (David A. Velasco 2013-05-30 17:53:21 +0200 1019)             actionBar.setDisplayHomeAsUpEnabled(true);
fd396289 (David A. Velasco 2013-05-30 17:53:21 +0200 1020)             actionBar.setDisplayShowTitleEnabled(true);
10b014a1 (David A. Velasco 2013-06-17 11:16:39 +0200 1021)             actionBar.setTitle(chosenFile.getFileName());
fd396289 (David A. Velasco 2013-05-30 17:53:21 +0200 1022)             actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_STANDARD);
56f1df02 (David A. Velasco 2013-04-12 12:46:18 +0200 1023)         }
56f1df02 (David A. Velasco 2013-04-12 12:46:18 +0200 1024)     }
eee74aa5 (masensio         2013-07-04 18:47:38 +0200 1025) 
eee74aa5 (masensio         2013-07-04 18:47:38 +0200 1026) 
f7649804 (Matthias Baumann 2013-02-25 08:21:33 +0100 1027)     /**
f7649804 (Matthias Baumann 2013-02-25 08:21:33 +0100 1028)      * {@inheritDoc}
f7649804 (Matthias Baumann 2013-02-25 08:21:33 +0100 1029)      */
f7649804 (Matthias Baumann 2013-02-25 08:21:33 +0100 1030)     @Override
f7649804 (Matthias Baumann 2013-02-25 08:21:33 +0100 1031)     public void onFileStateChanged() {
fd396289 (David A. Velasco 2013-05-30 17:53:21 +0200 1032)         refeshListOfFilesFragment();
646abfa0 (David A. Velasco 2013-06-26 11:47:20 +0200 1033)         updateNavigationElementsInActionBar(getSecondFragment().getFile());
f7649804 (Matthias Baumann 2013-02-25 08:21:33 +0100 1034)     }
f7649804 (Matthias Baumann 2013-02-25 08:21:33 +0100 1035) 
eee74aa5 (masensio         2013-07-04 18:47:38 +0200 1036) 
f7649804 (Matthias Baumann 2013-02-25 08:21:33 +0100 1037)     /**
f7649804 (Matthias Baumann 2013-02-25 08:21:33 +0100 1038)      * {@inheritDoc}
f7649804 (Matthias Baumann 2013-02-25 08:21:33 +0100 1039)      */
f7649804 (Matthias Baumann 2013-02-25 08:21:33 +0100 1040)     @Override
f7649804 (Matthias Baumann 2013-02-25 08:21:33 +0100 1041)     public FileDownloaderBinder getFileDownloaderBinder() {
f7649804 (Matthias Baumann 2013-02-25 08:21:33 +0100 1042)         return mDownloaderBinder;
f7649804 (Matthias Baumann 2013-02-25 08:21:33 +0100 1043)     }
f7649804 (Matthias Baumann 2013-02-25 08:21:33 +0100 1044) 
eee74aa5 (masensio         2013-07-04 18:47:38 +0200 1045) 
f7649804 (Matthias Baumann 2013-02-25 08:21:33 +0100 1046)     /**
f7649804 (Matthias Baumann 2013-02-25 08:21:33 +0100 1047)      * {@inheritDoc}
f7649804 (Matthias Baumann 2013-02-25 08:21:33 +0100 1048)      */
f7649804 (Matthias Baumann 2013-02-25 08:21:33 +0100 1049)     @Override
f7649804 (Matthias Baumann 2013-02-25 08:21:33 +0100 1050)     public FileUploaderBinder getFileUploaderBinder() {
f7649804 (Matthias Baumann 2013-02-25 08:21:33 +0100 1051)         return mUploaderBinder;
f7649804 (Matthias Baumann 2013-02-25 08:21:33 +0100 1052)     }
eee74aa5 (masensio         2013-07-04 18:47:38 +0200 1053) 
eee74aa5 (masensio         2013-07-04 18:47:38 +0200 1054) 
f7649804 (Matthias Baumann 2013-02-25 08:21:33 +0100 1055)     /** Defines callbacks for service binding, passed to bindService() */
f7649804 (Matthias Baumann 2013-02-25 08:21:33 +0100 1056)     private class ListServiceConnection implements ServiceConnection {
f7649804 (Matthias Baumann 2013-02-25 08:21:33 +0100 1057) 
f7649804 (Matthias Baumann 2013-02-25 08:21:33 +0100 1058)         @Override
f7649804 (Matthias Baumann 2013-02-25 08:21:33 +0100 1059)         public void onServiceConnected(ComponentName component, IBinder service) {
f7649804 (Matthias Baumann 2013-02-25 08:21:33 +0100 1060)             if (component.equals(new ComponentName(FileDisplayActivity.this, FileDownloader.class))) {
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100 1061)                 Log_OC.d(TAG, "Download service connected");
f7649804 (Matthias Baumann 2013-02-25 08:21:33 +0100 1062)                 mDownloaderBinder = (FileDownloaderBinder) service;
56f1df02 (David A. Velasco 2013-04-12 12:46:18 +0200 1063)                 if (mWaitingToPreview != null) {
56f1df02 (David A. Velasco 2013-04-12 12:46:18 +0200 1064)                     requestForDownload();
56f1df02 (David A. Velasco 2013-04-12 12:46:18 +0200 1065)                 }
eee74aa5 (masensio         2013-07-04 18:47:38 +0200 1066) 
f7649804 (Matthias Baumann 2013-02-25 08:21:33 +0100 1067)             } else if (component.equals(new ComponentName(FileDisplayActivity.this, FileUploader.class))) {
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100 1068)                 Log_OC.d(TAG, "Upload service connected");
f7649804 (Matthias Baumann 2013-02-25 08:21:33 +0100 1069)                 mUploaderBinder = (FileUploaderBinder) service;
f7649804 (Matthias Baumann 2013-02-25 08:21:33 +0100 1070)             } else {
f7649804 (Matthias Baumann 2013-02-25 08:21:33 +0100 1071)                 return;
f7649804 (Matthias Baumann 2013-02-25 08:21:33 +0100 1072)             }
f7649804 (Matthias Baumann 2013-02-25 08:21:33 +0100 1073)             // a new chance to get the mDownloadBinder through getFileDownloadBinder() - THIS IS A MESS
fd396289 (David A. Velasco 2013-05-30 17:53:21 +0200 1074)             OCFileListFragment listOfFiles = getListOfFilesFragment(); 
fd396289 (David A. Velasco 2013-05-30 17:53:21 +0200 1075)             if (listOfFiles != null) {
fd396289 (David A. Velasco 2013-05-30 17:53:21 +0200 1076)                 listOfFiles.listDirectory();
fd396289 (David A. Velasco 2013-05-30 17:53:21 +0200 1077)             }
fd396289 (David A. Velasco 2013-05-30 17:53:21 +0200 1078)             FileFragment secondFragment = getSecondFragment();
fd396289 (David A. Velasco 2013-05-30 17:53:21 +0200 1079)             if (secondFragment != null && secondFragment instanceof FileDetailFragment) {
fd396289 (David A. Velasco 2013-05-30 17:53:21 +0200 1080)                 FileDetailFragment detailFragment = (FileDetailFragment)secondFragment;
fd396289 (David A. Velasco 2013-05-30 17:53:21 +0200 1081)                 detailFragment.listenForTransferProgress();
fd396289 (David A. Velasco 2013-05-30 17:53:21 +0200 1082)                 detailFragment.updateFileDetails(false, false);
f7649804 (Matthias Baumann 2013-02-25 08:21:33 +0100 1083)             }
f7649804 (Matthias Baumann 2013-02-25 08:21:33 +0100 1084)         }
f7649804 (Matthias Baumann 2013-02-25 08:21:33 +0100 1085) 
f7649804 (Matthias Baumann 2013-02-25 08:21:33 +0100 1086)         @Override
f7649804 (Matthias Baumann 2013-02-25 08:21:33 +0100 1087)         public void onServiceDisconnected(ComponentName component) {
f7649804 (Matthias Baumann 2013-02-25 08:21:33 +0100 1088)             if (component.equals(new ComponentName(FileDisplayActivity.this, FileDownloader.class))) {
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100 1089)                 Log_OC.d(TAG, "Download service disconnected");
f7649804 (Matthias Baumann 2013-02-25 08:21:33 +0100 1090)                 mDownloaderBinder = null;
f7649804 (Matthias Baumann 2013-02-25 08:21:33 +0100 1091)             } else if (component.equals(new ComponentName(FileDisplayActivity.this, FileUploader.class))) {
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100 1092)                 Log_OC.d(TAG, "Upload service disconnected");
f7649804 (Matthias Baumann 2013-02-25 08:21:33 +0100 1093)                 mUploaderBinder = null;
f7649804 (Matthias Baumann 2013-02-25 08:21:33 +0100 1094)             }
f7649804 (Matthias Baumann 2013-02-25 08:21:33 +0100 1095)         }
f7649804 (Matthias Baumann 2013-02-25 08:21:33 +0100 1096)     };    
f7649804 (Matthias Baumann 2013-02-25 08:21:33 +0100 1097) 
eee74aa5 (masensio         2013-07-04 18:47:38 +0200 1098) 
eee74aa5 (masensio         2013-07-04 18:47:38 +0200 1099) 
f7649804 (Matthias Baumann 2013-02-25 08:21:33 +0100 1100)     /**
f7649804 (Matthias Baumann 2013-02-25 08:21:33 +0100 1101)      * Launch an intent to request the PIN code to the user before letting him use the app
f7649804 (Matthias Baumann 2013-02-25 08:21:33 +0100 1102)      */
f7649804 (Matthias Baumann 2013-02-25 08:21:33 +0100 1103)     private void requestPinCode() {
f7649804 (Matthias Baumann 2013-02-25 08:21:33 +0100 1104)         boolean pinStart = false;
f7649804 (Matthias Baumann 2013-02-25 08:21:33 +0100 1105)         SharedPreferences appPrefs = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
f7649804 (Matthias Baumann 2013-02-25 08:21:33 +0100 1106)         pinStart = appPrefs.getBoolean("set_pincode", false);
f7649804 (Matthias Baumann 2013-02-25 08:21:33 +0100 1107)         if (pinStart) {
f7649804 (Matthias Baumann 2013-02-25 08:21:33 +0100 1108)             Intent i = new Intent(getApplicationContext(), PinCodeActivity.class);
f7649804 (Matthias Baumann 2013-02-25 08:21:33 +0100 1109)             i.putExtra(PinCodeActivity.EXTRA_ACTIVITY, "FileDisplayActivity");
f7649804 (Matthias Baumann 2013-02-25 08:21:33 +0100 1110)             startActivity(i);
f7649804 (Matthias Baumann 2013-02-25 08:21:33 +0100 1111)         }
f7649804 (Matthias Baumann 2013-02-25 08:21:33 +0100 1112)     }
f7649804 (Matthias Baumann 2013-02-25 08:21:33 +0100 1113) 
f7649804 (Matthias Baumann 2013-02-25 08:21:33 +0100 1114) 
f7649804 (Matthias Baumann 2013-02-25 08:21:33 +0100 1115)     @Override
f7649804 (Matthias Baumann 2013-02-25 08:21:33 +0100 1116)     public void onSavedCertificate() {
f7649804 (Matthias Baumann 2013-02-25 08:21:33 +0100 1117)         startSynchronization();                
f7649804 (Matthias Baumann 2013-02-25 08:21:33 +0100 1118)     }
f7649804 (Matthias Baumann 2013-02-25 08:21:33 +0100 1119) 
8aeb5243 (David A. Velasco 2013-04-12 12:34:17 +0200 1120) 
f7649804 (Matthias Baumann 2013-02-25 08:21:33 +0100 1121)     @Override
f7649804 (Matthias Baumann 2013-02-25 08:21:33 +0100 1122)     public void onFailedSavingCertificate() {
f7649804 (Matthias Baumann 2013-02-25 08:21:33 +0100 1123)         showDialog(DIALOG_CERT_NOT_SAVED);
f7649804 (Matthias Baumann 2013-02-25 08:21:33 +0100 1124)     }
f7649804 (Matthias Baumann 2013-02-25 08:21:33 +0100 1125) 
f7649804 (Matthias Baumann 2013-02-25 08:21:33 +0100 1126) 
f7649804 (Matthias Baumann 2013-02-25 08:21:33 +0100 1127)     /**
f7649804 (Matthias Baumann 2013-02-25 08:21:33 +0100 1128)      * Updates the view associated to the activity after the finish of some operation over files
f7649804 (Matthias Baumann 2013-02-25 08:21:33 +0100 1129)      * in the current account.
f7649804 (Matthias Baumann 2013-02-25 08:21:33 +0100 1130)      * 
f7649804 (Matthias Baumann 2013-02-25 08:21:33 +0100 1131)      * @param operation     Removal operation performed.
f7649804 (Matthias Baumann 2013-02-25 08:21:33 +0100 1132)      * @param result        Result of the removal.
f7649804 (Matthias Baumann 2013-02-25 08:21:33 +0100 1133)      */
f7649804 (Matthias Baumann 2013-02-25 08:21:33 +0100 1134)     @Override
f7649804 (Matthias Baumann 2013-02-25 08:21:33 +0100 1135)     public void onRemoteOperationFinish(RemoteOperation operation, RemoteOperationResult result) {
f7649804 (Matthias Baumann 2013-02-25 08:21:33 +0100 1136)         if (operation instanceof RemoveFileOperation) {
8aeb5243 (David A. Velasco 2013-04-12 12:34:17 +0200 1137)             onRemoveFileOperationFinish((RemoveFileOperation)operation, result);
eee74aa5 (masensio         2013-07-04 18:47:38 +0200 1138) 
f7649804 (Matthias Baumann 2013-02-25 08:21:33 +0100 1139)         } else if (operation instanceof RenameFileOperation) {
8aeb5243 (David A. Velasco 2013-04-12 12:34:17 +0200 1140)             onRenameFileOperationFinish((RenameFileOperation)operation, result);
eee74aa5 (masensio         2013-07-04 18:47:38 +0200 1141) 
f7649804 (Matthias Baumann 2013-02-25 08:21:33 +0100 1142)         } else if (operation instanceof SynchronizeFileOperation) {
8aeb5243 (David A. Velasco 2013-04-12 12:34:17 +0200 1143)             onSynchronizeFileOperationFinish((SynchronizeFileOperation)operation, result);
2946d8dd (David A. Velasco 2013-04-25 19:39:22 +0200 1144) 
2946d8dd (David A. Velasco 2013-04-25 19:39:22 +0200 1145)         } else if (operation instanceof CreateFolderOperation) {
2946d8dd (David A. Velasco 2013-04-25 19:39:22 +0200 1146)             onCreateFolderOperationFinish((CreateFolderOperation)operation, result);
f7649804 (Matthias Baumann 2013-02-25 08:21:33 +0100 1147)         }
f7649804 (Matthias Baumann 2013-02-25 08:21:33 +0100 1148)     }
f7649804 (Matthias Baumann 2013-02-25 08:21:33 +0100 1149) 
f7649804 (Matthias Baumann 2013-02-25 08:21:33 +0100 1150) 
f7649804 (Matthias Baumann 2013-02-25 08:21:33 +0100 1151)     /**
f7649804 (Matthias Baumann 2013-02-25 08:21:33 +0100 1152)      * Updates the view associated to the activity after the finish of an operation trying to remove a 
f7649804 (Matthias Baumann 2013-02-25 08:21:33 +0100 1153)      * file. 
f7649804 (Matthias Baumann 2013-02-25 08:21:33 +0100 1154)      * 
f7649804 (Matthias Baumann 2013-02-25 08:21:33 +0100 1155)      * @param operation     Removal operation performed.
f7649804 (Matthias Baumann 2013-02-25 08:21:33 +0100 1156)      * @param result        Result of the removal.
f7649804 (Matthias Baumann 2013-02-25 08:21:33 +0100 1157)      */
f7649804 (Matthias Baumann 2013-02-25 08:21:33 +0100 1158)     private void onRemoveFileOperationFinish(RemoveFileOperation operation, RemoteOperationResult result) {
f7649804 (Matthias Baumann 2013-02-25 08:21:33 +0100 1159)         dismissDialog(DIALOG_SHORT_WAIT);
f7649804 (Matthias Baumann 2013-02-25 08:21:33 +0100 1160)         if (result.isSuccess()) {
f7649804 (Matthias Baumann 2013-02-25 08:21:33 +0100 1161)             Toast msg = Toast.makeText(this, R.string.remove_success_msg, Toast.LENGTH_LONG);
f7649804 (Matthias Baumann 2013-02-25 08:21:33 +0100 1162)             msg.show();
f7649804 (Matthias Baumann 2013-02-25 08:21:33 +0100 1163)             OCFile removedFile = operation.getFile();
fd396289 (David A. Velasco 2013-05-30 17:53:21 +0200 1164)             getSecondFragment();
c70f3333 (David A. Velasco 2013-06-04 14:30:29 +0200 1165)             FileFragment second = getSecondFragment();
c70f3333 (David A. Velasco 2013-06-04 14:30:29 +0200 1166)             if (second != null && removedFile.equals(second.getFile())) {
c70f3333 (David A. Velasco 2013-06-04 14:30:29 +0200 1167)                 cleanSecondFragment();
f7649804 (Matthias Baumann 2013-02-25 08:21:33 +0100 1168)             }
16f121a7 (David A. Velasco 2013-06-05 09:59:48 +0200 1169)             if (mStorageManager.getFileById(removedFile.getParentId()).equals(getCurrentDir())) {
fd396289 (David A. Velasco 2013-05-30 17:53:21 +0200 1170)                 refeshListOfFilesFragment();
f7649804 (Matthias Baumann 2013-02-25 08:21:33 +0100 1171)             }
eee74aa5 (masensio         2013-07-04 18:47:38 +0200 1172) 
f7649804 (Matthias Baumann 2013-02-25 08:21:33 +0100 1173)         } else {
8aeb5243 (David A. Velasco 2013-04-12 12:34:17 +0200 1174)             Toast msg = Toast.makeText(this, R.string.remove_fail_msg, Toast.LENGTH_LONG); 
f7649804 (Matthias Baumann 2013-02-25 08:21:33 +0100 1175)             msg.show();
f7649804 (Matthias Baumann 2013-02-25 08:21:33 +0100 1176)             if (result.isSslRecoverableException()) {
f7649804 (Matthias Baumann 2013-02-25 08:21:33 +0100 1177)                 mLastSslUntrustedServerResult = result;
8aeb5243 (David A. Velasco 2013-04-12 12:34:17 +0200 1178)                 showDialog(DIALOG_SSL_VALIDATOR); 
f7649804 (Matthias Baumann 2013-02-25 08:21:33 +0100 1179)             }
f7649804 (Matthias Baumann 2013-02-25 08:21:33 +0100 1180)         }
f7649804 (Matthias Baumann 2013-02-25 08:21:33 +0100 1181)     }
f7649804 (Matthias Baumann 2013-02-25 08:21:33 +0100 1182) 
f7649804 (Matthias Baumann 2013-02-25 08:21:33 +0100 1183)     /**
2946d8dd (David A. Velasco 2013-04-25 19:39:22 +0200 1184)      * Updates the view associated to the activity after the finish of an operation trying create a new folder
2946d8dd (David A. Velasco 2013-04-25 19:39:22 +0200 1185)      * 
2946d8dd (David A. Velasco 2013-04-25 19:39:22 +0200 1186)      * @param operation     Creation operation performed.
2946d8dd (David A. Velasco 2013-04-25 19:39:22 +0200 1187)      * @param result        Result of the creation.
2946d8dd (David A. Velasco 2013-04-25 19:39:22 +0200 1188)      */
2946d8dd (David A. Velasco 2013-04-25 19:39:22 +0200 1189)     private void onCreateFolderOperationFinish(CreateFolderOperation operation, RemoteOperationResult result) {
2946d8dd (David A. Velasco 2013-04-25 19:39:22 +0200 1190)         if (result.isSuccess()) {
2946d8dd (David A. Velasco 2013-04-25 19:39:22 +0200 1191)             dismissDialog(DIALOG_SHORT_WAIT);
fd396289 (David A. Velasco 2013-05-30 17:53:21 +0200 1192)             refeshListOfFilesFragment();
eee74aa5 (masensio         2013-07-04 18:47:38 +0200 1193) 
2946d8dd (David A. Velasco 2013-04-25 19:39:22 +0200 1194)         } else {
2946d8dd (David A. Velasco 2013-04-25 19:39:22 +0200 1195)             dismissDialog(DIALOG_SHORT_WAIT);
2946d8dd (David A. Velasco 2013-04-25 19:39:22 +0200 1196)             try {
2946d8dd (David A. Velasco 2013-04-25 19:39:22 +0200 1197)                 Toast msg = Toast.makeText(FileDisplayActivity.this, R.string.create_dir_fail_msg, Toast.LENGTH_LONG); 
2946d8dd (David A. Velasco 2013-04-25 19:39:22 +0200 1198)                 msg.show();
eee74aa5 (masensio         2013-07-04 18:47:38 +0200 1199) 
2946d8dd (David A. Velasco 2013-04-25 19:39:22 +0200 1200)             } catch (NotFoundException e) {
2946d8dd (David A. Velasco 2013-04-25 19:39:22 +0200 1201)                 Log_OC.e(TAG, "Error while trying to show fail message " , e);
2946d8dd (David A. Velasco 2013-04-25 19:39:22 +0200 1202)             }
2946d8dd (David A. Velasco 2013-04-25 19:39:22 +0200 1203)         }
2946d8dd (David A. Velasco 2013-04-25 19:39:22 +0200 1204)     }
eee74aa5 (masensio         2013-07-04 18:47:38 +0200 1205) 
eee74aa5 (masensio         2013-07-04 18:47:38 +0200 1206) 
2946d8dd (David A. Velasco 2013-04-25 19:39:22 +0200 1207)     /**
f7649804 (Matthias Baumann 2013-02-25 08:21:33 +0100 1208)      * Updates the view associated to the activity after the finish of an operation trying to rename a 
f7649804 (Matthias Baumann 2013-02-25 08:21:33 +0100 1209)      * file. 
f7649804 (Matthias Baumann 2013-02-25 08:21:33 +0100 1210)      * 
f7649804 (Matthias Baumann 2013-02-25 08:21:33 +0100 1211)      * @param operation     Renaming operation performed.
f7649804 (Matthias Baumann 2013-02-25 08:21:33 +0100 1212)      * @param result        Result of the renaming.
f7649804 (Matthias Baumann 2013-02-25 08:21:33 +0100 1213)      */
f7649804 (Matthias Baumann 2013-02-25 08:21:33 +0100 1214)     private void onRenameFileOperationFinish(RenameFileOperation operation, RemoteOperationResult result) {
f7649804 (Matthias Baumann 2013-02-25 08:21:33 +0100 1215)         dismissDialog(DIALOG_SHORT_WAIT);
f7649804 (Matthias Baumann 2013-02-25 08:21:33 +0100 1216)         OCFile renamedFile = operation.getFile();
f7649804 (Matthias Baumann 2013-02-25 08:21:33 +0100 1217)         if (result.isSuccess()) {
f7649804 (Matthias Baumann 2013-02-25 08:21:33 +0100 1218)             if (mDualPane) {
c70f3333 (David A. Velasco 2013-06-04 14:30:29 +0200 1219)                 FileFragment details = getSecondFragment();
56f1df02 (David A. Velasco 2013-04-12 12:46:18 +0200 1220)                 if (details != null && details instanceof FileDetailFragment && renamedFile.equals(details.getFile()) ) {
f6780fdc (David A. Velasco 2013-05-07 12:54:31 +0200 1221)                     ((FileDetailFragment) details).updateFileDetails(renamedFile, getAccount());
f7649804 (Matthias Baumann 2013-02-25 08:21:33 +0100 1222)                 }
f7649804 (Matthias Baumann 2013-02-25 08:21:33 +0100 1223)             }
16f121a7 (David A. Velasco 2013-06-05 09:59:48 +0200 1224)             if (mStorageManager.getFileById(renamedFile.getParentId()).equals(getCurrentDir())) {
fd396289 (David A. Velasco 2013-05-30 17:53:21 +0200 1225)                 refeshListOfFilesFragment();
f7649804 (Matthias Baumann 2013-02-25 08:21:33 +0100 1226)             }
eee74aa5 (masensio         2013-07-04 18:47:38 +0200 1227) 
f7649804 (Matthias Baumann 2013-02-25 08:21:33 +0100 1228)         } else {
f7649804 (Matthias Baumann 2013-02-25 08:21:33 +0100 1229)             if (result.getCode().equals(ResultCode.INVALID_LOCAL_FILE_NAME)) {
8aeb5243 (David A. Velasco 2013-04-12 12:34:17 +0200 1230)                 Toast msg = Toast.makeText(this, R.string.rename_local_fail_msg, Toast.LENGTH_LONG); 
f7649804 (Matthias Baumann 2013-02-25 08:21:33 +0100 1231)                 msg.show();
f7649804 (Matthias Baumann 2013-02-25 08:21:33 +0100 1232)                 // TODO throw again the new rename dialog
f7649804 (Matthias Baumann 2013-02-25 08:21:33 +0100 1233)             } else {
8aeb5243 (David A. Velasco 2013-04-12 12:34:17 +0200 1234)                 Toast msg = Toast.makeText(this, R.string.rename_server_fail_msg, Toast.LENGTH_LONG); 
f7649804 (Matthias Baumann 2013-02-25 08:21:33 +0100 1235)                 msg.show();
f7649804 (Matthias Baumann 2013-02-25 08:21:33 +0100 1236)                 if (result.isSslRecoverableException()) {
f7649804 (Matthias Baumann 2013-02-25 08:21:33 +0100 1237)                     mLastSslUntrustedServerResult = result;
8aeb5243 (David A. Velasco 2013-04-12 12:34:17 +0200 1238)                     showDialog(DIALOG_SSL_VALIDATOR); 
f7649804 (Matthias Baumann 2013-02-25 08:21:33 +0100 1239)                 }
f7649804 (Matthias Baumann 2013-02-25 08:21:33 +0100 1240)             }
f7649804 (Matthias Baumann 2013-02-25 08:21:33 +0100 1241)         }
f7649804 (Matthias Baumann 2013-02-25 08:21:33 +0100 1242)     }
f7649804 (Matthias Baumann 2013-02-25 08:21:33 +0100 1243) 
8aeb5243 (David A. Velasco 2013-04-12 12:34:17 +0200 1244) 
f7649804 (Matthias Baumann 2013-02-25 08:21:33 +0100 1245)     private void onSynchronizeFileOperationFinish(SynchronizeFileOperation operation, RemoteOperationResult result) {
f7649804 (Matthias Baumann 2013-02-25 08:21:33 +0100 1246)         dismissDialog(DIALOG_SHORT_WAIT);
f7649804 (Matthias Baumann 2013-02-25 08:21:33 +0100 1247)         OCFile syncedFile = operation.getLocalFile();
f7649804 (Matthias Baumann 2013-02-25 08:21:33 +0100 1248)         if (!result.isSuccess()) {
f7649804 (Matthias Baumann 2013-02-25 08:21:33 +0100 1249)             if (result.getCode() == ResultCode.SYNC_CONFLICT) {
f7649804 (Matthias Baumann 2013-02-25 08:21:33 +0100 1250)                 Intent i = new Intent(this, ConflictsResolveActivity.class);
f7649804 (Matthias Baumann 2013-02-25 08:21:33 +0100 1251)                 i.putExtra(ConflictsResolveActivity.EXTRA_FILE, syncedFile);
f6780fdc (David A. Velasco 2013-05-07 12:54:31 +0200 1252)                 i.putExtra(ConflictsResolveActivity.EXTRA_ACCOUNT, getAccount());
f7649804 (Matthias Baumann 2013-02-25 08:21:33 +0100 1253)                 startActivity(i);
eee74aa5 (masensio         2013-07-04 18:47:38 +0200 1254) 
f7649804 (Matthias Baumann 2013-02-25 08:21:33 +0100 1255)             } else {
8aeb5243 (David A. Velasco 2013-04-12 12:34:17 +0200 1256)                 Toast msg = Toast.makeText(this, R.string.sync_file_fail_msg, Toast.LENGTH_LONG); 
f7649804 (Matthias Baumann 2013-02-25 08:21:33 +0100 1257)                 msg.show();
f7649804 (Matthias Baumann 2013-02-25 08:21:33 +0100 1258)             }
eee74aa5 (masensio         2013-07-04 18:47:38 +0200 1259) 
f7649804 (Matthias Baumann 2013-02-25 08:21:33 +0100 1260)         } else {
f7649804 (Matthias Baumann 2013-02-25 08:21:33 +0100 1261)             if (operation.transferWasRequested()) {
fd396289 (David A. Velasco 2013-05-30 17:53:21 +0200 1262)                 refeshListOfFilesFragment();
f7649804 (Matthias Baumann 2013-02-25 08:21:33 +0100 1263)                 onTransferStateChanged(syncedFile, true, true);
eee74aa5 (masensio         2013-07-04 18:47:38 +0200 1264) 
f7649804 (Matthias Baumann 2013-02-25 08:21:33 +0100 1265)             } else {
8aeb5243 (David A. Velasco 2013-04-12 12:34:17 +0200 1266)                 Toast msg = Toast.makeText(this, R.string.sync_file_nothing_to_do_msg, Toast.LENGTH_LONG); 
f7649804 (Matthias Baumann 2013-02-25 08:21:33 +0100 1267)                 msg.show();
f7649804 (Matthias Baumann 2013-02-25 08:21:33 +0100 1268)             }
f7649804 (Matthias Baumann 2013-02-25 08:21:33 +0100 1269)         }
f7649804 (Matthias Baumann 2013-02-25 08:21:33 +0100 1270)     }
f7649804 (Matthias Baumann 2013-02-25 08:21:33 +0100 1271) 
f7649804 (Matthias Baumann 2013-02-25 08:21:33 +0100 1272) 
f7649804 (Matthias Baumann 2013-02-25 08:21:33 +0100 1273)     /**
f7649804 (Matthias Baumann 2013-02-25 08:21:33 +0100 1274)      * {@inheritDoc}
f7649804 (Matthias Baumann 2013-02-25 08:21:33 +0100 1275)      */
f7649804 (Matthias Baumann 2013-02-25 08:21:33 +0100 1276)     @Override
f7649804 (Matthias Baumann 2013-02-25 08:21:33 +0100 1277)     public void onTransferStateChanged(OCFile file, boolean downloading, boolean uploading) {
f7649804 (Matthias Baumann 2013-02-25 08:21:33 +0100 1278)         if (mDualPane) {
c70f3333 (David A. Velasco 2013-06-04 14:30:29 +0200 1279)             FileFragment details = getSecondFragment();
56f1df02 (David A. Velasco 2013-04-12 12:46:18 +0200 1280)             if (details != null && details instanceof FileDetailFragment && file.equals(details.getFile()) ) {
f7649804 (Matthias Baumann 2013-02-25 08:21:33 +0100 1281)                 if (downloading || uploading) {
f6780fdc (David A. Velasco 2013-05-07 12:54:31 +0200 1282)                     ((FileDetailFragment)details).updateFileDetails(file, getAccount());
f7649804 (Matthias Baumann 2013-02-25 08:21:33 +0100 1283)                 } else {
56f1df02 (David A. Velasco 2013-04-12 12:46:18 +0200 1284)                     ((FileDetailFragment)details).updateFileDetails(false, true);
f7649804 (Matthias Baumann 2013-02-25 08:21:33 +0100 1285)                 }
f7649804 (Matthias Baumann 2013-02-25 08:21:33 +0100 1286)             }
f7649804 (Matthias Baumann 2013-02-25 08:21:33 +0100 1287)         }
f7649804 (Matthias Baumann 2013-02-25 08:21:33 +0100 1288)     }
f7649804 (Matthias Baumann 2013-02-25 08:21:33 +0100 1289) 
f7649804 (Matthias Baumann 2013-02-25 08:21:33 +0100 1290) 
8aeb5243 (David A. Velasco 2013-04-12 12:34:17 +0200 1291)     public void onDismiss(EditNameDialog dialog) {
8aeb5243 (David A. Velasco 2013-04-12 12:34:17 +0200 1292)         if (dialog.getResult()) {
8aeb5243 (David A. Velasco 2013-04-12 12:34:17 +0200 1293)             String newDirectoryName = dialog.getNewFilename().trim();
2946d8dd (David A. Velasco 2013-04-25 19:39:22 +0200 1294)             Log_OC.d(TAG, "'create directory' dialog dismissed with new name " + newDirectoryName);
8aeb5243 (David A. Velasco 2013-04-12 12:34:17 +0200 1295)             if (newDirectoryName.length() > 0) {
16f121a7 (David A. Velasco 2013-06-05 09:59:48 +0200 1296)                 String path = getCurrentDir().getRemotePath();
eee74aa5 (masensio         2013-07-04 18:47:38 +0200 1297) 
8aeb5243 (David A. Velasco 2013-04-12 12:34:17 +0200 1298)                 // Create directory
8aeb5243 (David A. Velasco 2013-04-12 12:34:17 +0200 1299)                 path += newDirectoryName + OCFile.PATH_SEPARATOR;
16f121a7 (David A. Velasco 2013-06-05 09:59:48 +0200 1300)                 RemoteOperation operation = new CreateFolderOperation(path, getCurrentDir().getFileId(), mStorageManager);
f6780fdc (David A. Velasco 2013-05-07 12:54:31 +0200 1301)                 operation.execute(  getAccount(), 
eee74aa5 (masensio         2013-07-04 18:47:38 +0200 1302)                         FileDisplayActivity.this, 
eee74aa5 (masensio         2013-07-04 18:47:38 +0200 1303)                         FileDisplayActivity.this, 
eee74aa5 (masensio         2013-07-04 18:47:38 +0200 1304)                         mHandler,
eee74aa5 (masensio         2013-07-04 18:47:38 +0200 1305)                         FileDisplayActivity.this);
eee74aa5 (masensio         2013-07-04 18:47:38 +0200 1306) 
8aeb5243 (David A. Velasco 2013-04-12 12:34:17 +0200 1307)                 showDialog(DIALOG_SHORT_WAIT);
8aeb5243 (David A. Velasco 2013-04-12 12:34:17 +0200 1308)             }
8aeb5243 (David A. Velasco 2013-04-12 12:34:17 +0200 1309)         }
8aeb5243 (David A. Velasco 2013-04-12 12:34:17 +0200 1310)     }
eee74aa5 (masensio         2013-07-04 18:47:38 +0200 1311) 
eee74aa5 (masensio         2013-07-04 18:47:38 +0200 1312) 
56f1df02 (David A. Velasco 2013-04-12 12:46:18 +0200 1313)     private void requestForDownload() {
f6780fdc (David A. Velasco 2013-05-07 12:54:31 +0200 1314)         Account account = getAccount();
56f1df02 (David A. Velasco 2013-04-12 12:46:18 +0200 1315)         if (!mDownloaderBinder.isDownloading(account, mWaitingToPreview)) {
56f1df02 (David A. Velasco 2013-04-12 12:46:18 +0200 1316)             Intent i = new Intent(this, FileDownloader.class);
56f1df02 (David A. Velasco 2013-04-12 12:46:18 +0200 1317)             i.putExtra(FileDownloader.EXTRA_ACCOUNT, account);
56f1df02 (David A. Velasco 2013-04-12 12:46:18 +0200 1318)             i.putExtra(FileDownloader.EXTRA_FILE, mWaitingToPreview);
56f1df02 (David A. Velasco 2013-04-12 12:46:18 +0200 1319)             startService(i);
56f1df02 (David A. Velasco 2013-04-12 12:46:18 +0200 1320)         }
56f1df02 (David A. Velasco 2013-04-12 12:46:18 +0200 1321)     }
eee74aa5 (masensio         2013-07-04 18:47:38 +0200 1322) 
eee74aa5 (masensio         2013-07-04 18:47:38 +0200 1323) 
16f121a7 (David A. Velasco 2013-06-05 09:59:48 +0200 1324)     private OCFile getCurrentDir() {
16f121a7 (David A. Velasco 2013-06-05 09:59:48 +0200 1325)         OCFile file = getFile();
16f121a7 (David A. Velasco 2013-06-05 09:59:48 +0200 1326)         if (file != null) {
16f121a7 (David A. Velasco 2013-06-05 09:59:48 +0200 1327)             if (file.isDirectory()) {
16f121a7 (David A. Velasco 2013-06-05 09:59:48 +0200 1328)                 return file;
10b014a1 (David A. Velasco 2013-06-17 11:16:39 +0200 1329)             } else if (mStorageManager != null) {
16f121a7 (David A. Velasco 2013-06-05 09:59:48 +0200 1330)                 return mStorageManager.getFileById(file.getParentId());
16f121a7 (David A. Velasco 2013-06-05 09:59:48 +0200 1331)             }
16f121a7 (David A. Velasco 2013-06-05 09:59:48 +0200 1332)         }
10b014a1 (David A. Velasco 2013-06-17 11:16:39 +0200 1333)         return null;
16f121a7 (David A. Velasco 2013-06-05 09:59:48 +0200 1334)     }
f6780fdc (David A. Velasco 2013-05-07 12:54:31 +0200 1335) 
f7649804 (Matthias Baumann 2013-02-25 08:21:33 +0100 1336) }
