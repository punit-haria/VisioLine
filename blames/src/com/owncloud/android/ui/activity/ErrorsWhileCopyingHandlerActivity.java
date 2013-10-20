bb257ec7 (David A. Velasco 2013-02-07 18:45:10 +0100   1) /* ownCloud Android client application
bb257ec7 (David A. Velasco 2013-02-07 18:45:10 +0100   2)  *   Copyright (C) 2012-2013 ownCloud Inc.
bb257ec7 (David A. Velasco 2013-02-07 18:45:10 +0100   3)  *
bb257ec7 (David A. Velasco 2013-02-07 18:45:10 +0100   4)  *   This program is free software: you can redistribute it and/or modify
bdc0332c (David A. Velasco 2013-04-17 12:26:13 +0200   5)  *   it under the terms of the GNU General Public License version 2,
bdc0332c (David A. Velasco 2013-04-17 12:26:13 +0200   6)  *   as published by the Free Software Foundation.
bb257ec7 (David A. Velasco 2013-02-07 18:45:10 +0100   7)  *
bb257ec7 (David A. Velasco 2013-02-07 18:45:10 +0100   8)  *   This program is distributed in the hope that it will be useful,
bb257ec7 (David A. Velasco 2013-02-07 18:45:10 +0100   9)  *   but WITHOUT ANY WARRANTY; without even the implied warranty of
bb257ec7 (David A. Velasco 2013-02-07 18:45:10 +0100  10)  *   MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
bb257ec7 (David A. Velasco 2013-02-07 18:45:10 +0100  11)  *   GNU General Public License for more details.
bb257ec7 (David A. Velasco 2013-02-07 18:45:10 +0100  12)  *
bb257ec7 (David A. Velasco 2013-02-07 18:45:10 +0100  13)  *   You should have received a copy of the GNU General Public License
bb257ec7 (David A. Velasco 2013-02-07 18:45:10 +0100  14)  *   along with this program.  If not, see <http://www.gnu.org/licenses/>.
bb257ec7 (David A. Velasco 2013-02-07 18:45:10 +0100  15)  *
bb257ec7 (David A. Velasco 2013-02-07 18:45:10 +0100  16)  */
bb257ec7 (David A. Velasco 2013-02-07 18:45:10 +0100  17) 
f68d10ab (David A. Velasco 2012-12-04 12:49:18 +0100  18) package com.owncloud.android.ui.activity;
f68d10ab (David A. Velasco 2012-12-04 12:49:18 +0100  19) 
f68d10ab (David A. Velasco 2012-12-04 12:49:18 +0100  20) import java.io.File;
f68d10ab (David A. Velasco 2012-12-04 12:49:18 +0100  21) import java.util.ArrayList;
f68d10ab (David A. Velasco 2012-12-04 12:49:18 +0100  22) 
f68d10ab (David A. Velasco 2012-12-04 12:49:18 +0100  23) import android.accounts.Account;
f68d10ab (David A. Velasco 2012-12-04 12:49:18 +0100  24) import android.content.Context;
f68d10ab (David A. Velasco 2012-12-04 12:49:18 +0100  25) import android.content.Intent;
f68d10ab (David A. Velasco 2012-12-04 12:49:18 +0100  26) import android.os.AsyncTask;
f68d10ab (David A. Velasco 2012-12-04 12:49:18 +0100  27) import android.os.Bundle;
f68d10ab (David A. Velasco 2012-12-04 12:49:18 +0100  28) import android.os.Handler;
f68d10ab (David A. Velasco 2012-12-04 12:49:18 +0100  29) import android.support.v4.app.DialogFragment;
f68d10ab (David A. Velasco 2012-12-04 12:49:18 +0100  30) import android.text.method.ScrollingMovementMethod;
f68d10ab (David A. Velasco 2012-12-04 12:49:18 +0100  31) import android.view.LayoutInflater;
f68d10ab (David A. Velasco 2012-12-04 12:49:18 +0100  32) import android.view.View;
f68d10ab (David A. Velasco 2012-12-04 12:49:18 +0100  33) import android.view.View.OnClickListener;
f68d10ab (David A. Velasco 2012-12-04 12:49:18 +0100  34) import android.view.ViewGroup;
f68d10ab (David A. Velasco 2012-12-04 12:49:18 +0100  35) import android.widget.ArrayAdapter;
f68d10ab (David A. Velasco 2012-12-04 12:49:18 +0100  36) import android.widget.Button;
f68d10ab (David A. Velasco 2012-12-04 12:49:18 +0100  37) import android.widget.ListView;
f68d10ab (David A. Velasco 2012-12-04 12:49:18 +0100  38) import android.widget.TextView;
f68d10ab (David A. Velasco 2012-12-04 12:49:18 +0100  39) import android.widget.Toast;
f68d10ab (David A. Velasco 2012-12-04 12:49:18 +0100  40) 
f68d10ab (David A. Velasco 2012-12-04 12:49:18 +0100  41) import com.actionbarsherlock.app.SherlockFragmentActivity;
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100  42) import com.owncloud.android.Log_OC;
f68d10ab (David A. Velasco 2012-12-04 12:49:18 +0100  43) import com.owncloud.android.R;
f68d10ab (David A. Velasco 2012-12-04 12:49:18 +0100  44) import com.owncloud.android.datamodel.FileDataStorageManager;
f68d10ab (David A. Velasco 2012-12-04 12:49:18 +0100  45) import com.owncloud.android.datamodel.OCFile;
f68d10ab (David A. Velasco 2012-12-04 12:49:18 +0100  46) import com.owncloud.android.ui.dialog.IndeterminateProgressDialog;
f68d10ab (David A. Velasco 2012-12-04 12:49:18 +0100  47) import com.owncloud.android.utils.FileStorageUtils;
f68d10ab (David A. Velasco 2012-12-04 12:49:18 +0100  48) 
f68d10ab (David A. Velasco 2012-12-04 12:49:18 +0100  49) 
f68d10ab (David A. Velasco 2012-12-04 12:49:18 +0100  50) /**
f68d10ab (David A. Velasco 2012-12-04 12:49:18 +0100  51)  * Activity reporting errors occurred when local files uploaded to an ownCloud account with an app in
f68d10ab (David A. Velasco 2012-12-04 12:49:18 +0100  52)  * version under 1.3.16 where being copied to the ownCloud local folder.
f68d10ab (David A. Velasco 2012-12-04 12:49:18 +0100  53)  * 
bdc0332c (David A. Velasco 2013-04-17 12:26:13 +0200  54)  * Allows the user move the files to the ownCloud local folder. let them unlinked to the remote
f68d10ab (David A. Velasco 2012-12-04 12:49:18 +0100  55)  * files.
f68d10ab (David A. Velasco 2012-12-04 12:49:18 +0100  56)  * 
f68d10ab (David A. Velasco 2012-12-04 12:49:18 +0100  57)  * Shown when the error notification summarizing the list of errors is clicked by the user.
f68d10ab (David A. Velasco 2012-12-04 12:49:18 +0100  58)  * 
f68d10ab (David A. Velasco 2012-12-04 12:49:18 +0100  59)  * @author David A. Velasco
f68d10ab (David A. Velasco 2012-12-04 12:49:18 +0100  60)  */
f68d10ab (David A. Velasco 2012-12-04 12:49:18 +0100  61) public class ErrorsWhileCopyingHandlerActivity  extends SherlockFragmentActivity implements OnClickListener {
f68d10ab (David A. Velasco 2012-12-04 12:49:18 +0100  62) 
f68d10ab (David A. Velasco 2012-12-04 12:49:18 +0100  63)     private static final String TAG = ErrorsWhileCopyingHandlerActivity.class.getSimpleName();
f68d10ab (David A. Velasco 2012-12-04 12:49:18 +0100  64)     
f68d10ab (David A. Velasco 2012-12-04 12:49:18 +0100  65)     public static final String EXTRA_ACCOUNT = ErrorsWhileCopyingHandlerActivity.class.getCanonicalName() + ".EXTRA_ACCOUNT";
f68d10ab (David A. Velasco 2012-12-04 12:49:18 +0100  66)     public static final String EXTRA_LOCAL_PATHS = ErrorsWhileCopyingHandlerActivity.class.getCanonicalName() + ".EXTRA_LOCAL_PATHS";
f68d10ab (David A. Velasco 2012-12-04 12:49:18 +0100  67)     public static final String EXTRA_REMOTE_PATHS = ErrorsWhileCopyingHandlerActivity.class.getCanonicalName() + ".EXTRA_REMOTE_PATHS";
f68d10ab (David A. Velasco 2012-12-04 12:49:18 +0100  68) 
f68d10ab (David A. Velasco 2012-12-04 12:49:18 +0100  69)     private static final String WAIT_DIALOG_TAG = "WAIT_DIALOG";
f68d10ab (David A. Velasco 2012-12-04 12:49:18 +0100  70)     
f68d10ab (David A. Velasco 2012-12-04 12:49:18 +0100  71)     protected Account mAccount;
f68d10ab (David A. Velasco 2012-12-04 12:49:18 +0100  72)     protected FileDataStorageManager mStorageManager;
f68d10ab (David A. Velasco 2012-12-04 12:49:18 +0100  73)     protected ArrayList<String> mLocalPaths;
f68d10ab (David A. Velasco 2012-12-04 12:49:18 +0100  74)     protected ArrayList<String> mRemotePaths;
f68d10ab (David A. Velasco 2012-12-04 12:49:18 +0100  75)     protected ArrayAdapter<String> mAdapter;
f68d10ab (David A. Velasco 2012-12-04 12:49:18 +0100  76)     protected Handler mHandler;
f68d10ab (David A. Velasco 2012-12-04 12:49:18 +0100  77)     private DialogFragment mCurrentDialog;
f68d10ab (David A. Velasco 2012-12-04 12:49:18 +0100  78)     
f68d10ab (David A. Velasco 2012-12-04 12:49:18 +0100  79)     /**
f68d10ab (David A. Velasco 2012-12-04 12:49:18 +0100  80)      * {@link}
f68d10ab (David A. Velasco 2012-12-04 12:49:18 +0100  81)      */
f68d10ab (David A. Velasco 2012-12-04 12:49:18 +0100  82)     @Override
f68d10ab (David A. Velasco 2012-12-04 12:49:18 +0100  83)     protected void onCreate(Bundle savedInstanceState) {
f68d10ab (David A. Velasco 2012-12-04 12:49:18 +0100  84)         super.onCreate(savedInstanceState);
f68d10ab (David A. Velasco 2012-12-04 12:49:18 +0100  85)         
f68d10ab (David A. Velasco 2012-12-04 12:49:18 +0100  86)         /// read extra parameters in intent
f68d10ab (David A. Velasco 2012-12-04 12:49:18 +0100  87)         Intent intent = getIntent();
f68d10ab (David A. Velasco 2012-12-04 12:49:18 +0100  88)         mAccount = intent.getParcelableExtra(EXTRA_ACCOUNT);
f68d10ab (David A. Velasco 2012-12-04 12:49:18 +0100  89)         mRemotePaths = intent.getStringArrayListExtra(EXTRA_REMOTE_PATHS);
f68d10ab (David A. Velasco 2012-12-04 12:49:18 +0100  90)         mLocalPaths = intent.getStringArrayListExtra(EXTRA_LOCAL_PATHS);
f68d10ab (David A. Velasco 2012-12-04 12:49:18 +0100  91)         mStorageManager = new FileDataStorageManager(mAccount, getContentResolver());
f68d10ab (David A. Velasco 2012-12-04 12:49:18 +0100  92)         mHandler = new Handler();
f68d10ab (David A. Velasco 2012-12-04 12:49:18 +0100  93)         if (mCurrentDialog != null) {
f68d10ab (David A. Velasco 2012-12-04 12:49:18 +0100  94)             mCurrentDialog.dismiss();
f68d10ab (David A. Velasco 2012-12-04 12:49:18 +0100  95)             mCurrentDialog = null;
f68d10ab (David A. Velasco 2012-12-04 12:49:18 +0100  96)         }
f68d10ab (David A. Velasco 2012-12-04 12:49:18 +0100  97)         
f68d10ab (David A. Velasco 2012-12-04 12:49:18 +0100  98)         /// load generic layout
f68d10ab (David A. Velasco 2012-12-04 12:49:18 +0100  99)         setContentView(R.layout.generic_explanation);
f68d10ab (David A. Velasco 2012-12-04 12:49:18 +0100 100)         
f68d10ab (David A. Velasco 2012-12-04 12:49:18 +0100 101)         /// customize text message
f68d10ab (David A. Velasco 2012-12-04 12:49:18 +0100 102)         TextView textView = (TextView) findViewById(R.id.message);
f68d10ab (David A. Velasco 2012-12-04 12:49:18 +0100 103)         String appName = getString(R.string.app_name);
f68d10ab (David A. Velasco 2012-12-04 12:49:18 +0100 104)         String message = String.format(getString(R.string.sync_foreign_files_forgotten_explanation), appName, appName, appName, appName, mAccount.name);
f68d10ab (David A. Velasco 2012-12-04 12:49:18 +0100 105)         textView.setText(message);
f68d10ab (David A. Velasco 2012-12-04 12:49:18 +0100 106)         textView.setMovementMethod(new ScrollingMovementMethod());
f68d10ab (David A. Velasco 2012-12-04 12:49:18 +0100 107)         
f68d10ab (David A. Velasco 2012-12-04 12:49:18 +0100 108)         /// load the list of local and remote files that failed
f68d10ab (David A. Velasco 2012-12-04 12:49:18 +0100 109)         ListView listView = (ListView) findViewById(R.id.list);
f68d10ab (David A. Velasco 2012-12-04 12:49:18 +0100 110)         if (mLocalPaths != null && mLocalPaths.size() > 0) {
f68d10ab (David A. Velasco 2012-12-04 12:49:18 +0100 111)             mAdapter = new ErrorsWhileCopyingListAdapter();
f68d10ab (David A. Velasco 2012-12-04 12:49:18 +0100 112)             listView.setAdapter(mAdapter);
f68d10ab (David A. Velasco 2012-12-04 12:49:18 +0100 113)         } else {
f68d10ab (David A. Velasco 2012-12-04 12:49:18 +0100 114)             listView.setVisibility(View.GONE);
f68d10ab (David A. Velasco 2012-12-04 12:49:18 +0100 115)             mAdapter = null;
f68d10ab (David A. Velasco 2012-12-04 12:49:18 +0100 116)         }
f68d10ab (David A. Velasco 2012-12-04 12:49:18 +0100 117)         
f68d10ab (David A. Velasco 2012-12-04 12:49:18 +0100 118)         /// customize buttons
f68d10ab (David A. Velasco 2012-12-04 12:49:18 +0100 119)         Button cancelBtn = (Button) findViewById(R.id.cancel);
f68d10ab (David A. Velasco 2012-12-04 12:49:18 +0100 120)         Button okBtn = (Button) findViewById(R.id.ok);
f68d10ab (David A. Velasco 2012-12-04 12:49:18 +0100 121)         okBtn.setText(R.string.foreign_files_move);
f68d10ab (David A. Velasco 2012-12-04 12:49:18 +0100 122)         cancelBtn.setOnClickListener(this);
f68d10ab (David A. Velasco 2012-12-04 12:49:18 +0100 123)         okBtn.setOnClickListener(this);
f68d10ab (David A. Velasco 2012-12-04 12:49:18 +0100 124)     }
f68d10ab (David A. Velasco 2012-12-04 12:49:18 +0100 125)     
f68d10ab (David A. Velasco 2012-12-04 12:49:18 +0100 126)     
f68d10ab (David A. Velasco 2012-12-04 12:49:18 +0100 127)     /**
f68d10ab (David A. Velasco 2012-12-04 12:49:18 +0100 128)      * Customized adapter, showing the local files as main text in two-lines list item and the remote files
f68d10ab (David A. Velasco 2012-12-04 12:49:18 +0100 129)      * as the secondary text. 
f68d10ab (David A. Velasco 2012-12-04 12:49:18 +0100 130)      * 
f68d10ab (David A. Velasco 2012-12-04 12:49:18 +0100 131)      * @author David A. Velasco
f68d10ab (David A. Velasco 2012-12-04 12:49:18 +0100 132)      */
f68d10ab (David A. Velasco 2012-12-04 12:49:18 +0100 133)     public class ErrorsWhileCopyingListAdapter extends ArrayAdapter<String> {
f68d10ab (David A. Velasco 2012-12-04 12:49:18 +0100 134)         
f68d10ab (David A. Velasco 2012-12-04 12:49:18 +0100 135)         ErrorsWhileCopyingListAdapter() {
f68d10ab (David A. Velasco 2012-12-04 12:49:18 +0100 136)             super(ErrorsWhileCopyingHandlerActivity.this, android.R.layout.two_line_list_item, android.R.id.text1, mLocalPaths);
f68d10ab (David A. Velasco 2012-12-04 12:49:18 +0100 137)         }
f68d10ab (David A. Velasco 2012-12-04 12:49:18 +0100 138) 
f68d10ab (David A. Velasco 2012-12-04 12:49:18 +0100 139)         @Override
f68d10ab (David A. Velasco 2012-12-04 12:49:18 +0100 140)         public boolean isEnabled(int position) {
f68d10ab (David A. Velasco 2012-12-04 12:49:18 +0100 141)             return false;
f68d10ab (David A. Velasco 2012-12-04 12:49:18 +0100 142)         }
f68d10ab (David A. Velasco 2012-12-04 12:49:18 +0100 143)         
f68d10ab (David A. Velasco 2012-12-04 12:49:18 +0100 144)         /**
f68d10ab (David A. Velasco 2012-12-04 12:49:18 +0100 145)          * {@inheritDoc}
f68d10ab (David A. Velasco 2012-12-04 12:49:18 +0100 146)          */
f68d10ab (David A. Velasco 2012-12-04 12:49:18 +0100 147)         @Override
f68d10ab (David A. Velasco 2012-12-04 12:49:18 +0100 148)         public View getView (int position, View convertView, ViewGroup parent) {
f68d10ab (David A. Velasco 2012-12-04 12:49:18 +0100 149)             View view = convertView;
f68d10ab (David A. Velasco 2012-12-04 12:49:18 +0100 150)             if (view == null) {
f68d10ab (David A. Velasco 2012-12-04 12:49:18 +0100 151)                 LayoutInflater vi = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
f68d10ab (David A. Velasco 2012-12-04 12:49:18 +0100 152)                 view = vi.inflate(android.R.layout.two_line_list_item, null);
f68d10ab (David A. Velasco 2012-12-04 12:49:18 +0100 153)             }
f68d10ab (David A. Velasco 2012-12-04 12:49:18 +0100 154)             if (view != null)  {
f68d10ab (David A. Velasco 2012-12-04 12:49:18 +0100 155)                 String localPath = getItem(position);
f68d10ab (David A. Velasco 2012-12-04 12:49:18 +0100 156)                 if (localPath != null) {
f68d10ab (David A. Velasco 2012-12-04 12:49:18 +0100 157)                     TextView text1 = (TextView) view.findViewById(android.R.id.text1);
f68d10ab (David A. Velasco 2012-12-04 12:49:18 +0100 158)                     if (text1 != null) {
f68d10ab (David A. Velasco 2012-12-04 12:49:18 +0100 159)                         text1.setText(String.format(getString(R.string.foreign_files_local_text), localPath));
f68d10ab (David A. Velasco 2012-12-04 12:49:18 +0100 160)                     }
f68d10ab (David A. Velasco 2012-12-04 12:49:18 +0100 161)                 }
f68d10ab (David A. Velasco 2012-12-04 12:49:18 +0100 162)                 if (mRemotePaths != null && mRemotePaths.size() > 0 && position >= 0 && position < mRemotePaths.size()) {
f68d10ab (David A. Velasco 2012-12-04 12:49:18 +0100 163)                     TextView text2 = (TextView) view.findViewById(android.R.id.text2);
f68d10ab (David A. Velasco 2012-12-04 12:49:18 +0100 164)                     String remotePath = mRemotePaths.get(position);
f68d10ab (David A. Velasco 2012-12-04 12:49:18 +0100 165)                     if (text2 != null && remotePath != null) {
f68d10ab (David A. Velasco 2012-12-04 12:49:18 +0100 166)                         text2.setText(String.format(getString(R.string.foreign_files_remote_text), remotePath));
f68d10ab (David A. Velasco 2012-12-04 12:49:18 +0100 167)                     }
f68d10ab (David A. Velasco 2012-12-04 12:49:18 +0100 168)                 }
f68d10ab (David A. Velasco 2012-12-04 12:49:18 +0100 169)             }
f68d10ab (David A. Velasco 2012-12-04 12:49:18 +0100 170)             return view;
f68d10ab (David A. Velasco 2012-12-04 12:49:18 +0100 171)         }
f68d10ab (David A. Velasco 2012-12-04 12:49:18 +0100 172)     }
f68d10ab (David A. Velasco 2012-12-04 12:49:18 +0100 173) 
f68d10ab (David A. Velasco 2012-12-04 12:49:18 +0100 174) 
f68d10ab (David A. Velasco 2012-12-04 12:49:18 +0100 175)     /**
f68d10ab (David A. Velasco 2012-12-04 12:49:18 +0100 176)      * Listener method to perform the MOVE / CANCEL action available in this activity.
f68d10ab (David A. Velasco 2012-12-04 12:49:18 +0100 177)      * 
f68d10ab (David A. Velasco 2012-12-04 12:49:18 +0100 178)      * @param v     Clicked view (button MOVE or CANCEL)
f68d10ab (David A. Velasco 2012-12-04 12:49:18 +0100 179)      */
f68d10ab (David A. Velasco 2012-12-04 12:49:18 +0100 180)     @Override
f68d10ab (David A. Velasco 2012-12-04 12:49:18 +0100 181)     public void onClick(View v) {
f68d10ab (David A. Velasco 2012-12-04 12:49:18 +0100 182)         if (v.getId() == R.id.ok) {
f68d10ab (David A. Velasco 2012-12-04 12:49:18 +0100 183)             /// perform movement operation in background thread
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100 184)             Log_OC.d(TAG, "Clicked MOVE, start movement");
f68d10ab (David A. Velasco 2012-12-04 12:49:18 +0100 185)             new MoveFilesTask().execute();            
f68d10ab (David A. Velasco 2012-12-04 12:49:18 +0100 186)             
f68d10ab (David A. Velasco 2012-12-04 12:49:18 +0100 187)         } else if (v.getId() == R.id.cancel) {
f68d10ab (David A. Velasco 2012-12-04 12:49:18 +0100 188)             /// just finish
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100 189)             Log_OC.d(TAG, "Clicked CANCEL, bye");
f68d10ab (David A. Velasco 2012-12-04 12:49:18 +0100 190)             finish();
f68d10ab (David A. Velasco 2012-12-04 12:49:18 +0100 191)             
f68d10ab (David A. Velasco 2012-12-04 12:49:18 +0100 192)         } else {
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100 193)             Log_OC.e(TAG, "Clicked phantom button, id: " + v.getId());
f68d10ab (David A. Velasco 2012-12-04 12:49:18 +0100 194)         }
f68d10ab (David A. Velasco 2012-12-04 12:49:18 +0100 195)     }
f68d10ab (David A. Velasco 2012-12-04 12:49:18 +0100 196) 
f68d10ab (David A. Velasco 2012-12-04 12:49:18 +0100 197)     
f68d10ab (David A. Velasco 2012-12-04 12:49:18 +0100 198)     /**
f68d10ab (David A. Velasco 2012-12-04 12:49:18 +0100 199)      * Asynchronous task performing the move of all the local files to the ownCloud folder.
f68d10ab (David A. Velasco 2012-12-04 12:49:18 +0100 200)      * 
f68d10ab (David A. Velasco 2012-12-04 12:49:18 +0100 201)      * @author David A. Velasco
f68d10ab (David A. Velasco 2012-12-04 12:49:18 +0100 202)      */
f68d10ab (David A. Velasco 2012-12-04 12:49:18 +0100 203)     private class MoveFilesTask extends AsyncTask<Void, Void, Boolean> {
f68d10ab (David A. Velasco 2012-12-04 12:49:18 +0100 204) 
f68d10ab (David A. Velasco 2012-12-04 12:49:18 +0100 205)         /**
f68d10ab (David A. Velasco 2012-12-04 12:49:18 +0100 206)          * Updates the UI before trying the movement
f68d10ab (David A. Velasco 2012-12-04 12:49:18 +0100 207)          */
f68d10ab (David A. Velasco 2012-12-04 12:49:18 +0100 208)         @Override
f68d10ab (David A. Velasco 2012-12-04 12:49:18 +0100 209)         protected void onPreExecute () {
f68d10ab (David A. Velasco 2012-12-04 12:49:18 +0100 210)             /// progress dialog and disable 'Move' button
f68d10ab (David A. Velasco 2012-12-04 12:49:18 +0100 211)             mCurrentDialog = IndeterminateProgressDialog.newInstance(R.string.wait_a_moment, false);
f68d10ab (David A. Velasco 2012-12-04 12:49:18 +0100 212)             mCurrentDialog.show(getSupportFragmentManager(), WAIT_DIALOG_TAG);
f68d10ab (David A. Velasco 2012-12-04 12:49:18 +0100 213)             findViewById(R.id.ok).setEnabled(false);
f68d10ab (David A. Velasco 2012-12-04 12:49:18 +0100 214)         }
f68d10ab (David A. Velasco 2012-12-04 12:49:18 +0100 215)         
f68d10ab (David A. Velasco 2012-12-04 12:49:18 +0100 216)         
f68d10ab (David A. Velasco 2012-12-04 12:49:18 +0100 217)         /**
f68d10ab (David A. Velasco 2012-12-04 12:49:18 +0100 218)          * Performs the movement
f68d10ab (David A. Velasco 2012-12-04 12:49:18 +0100 219)          * 
f68d10ab (David A. Velasco 2012-12-04 12:49:18 +0100 220)          * @return     'False' when the movement of any file fails.
f68d10ab (David A. Velasco 2012-12-04 12:49:18 +0100 221)          */
f68d10ab (David A. Velasco 2012-12-04 12:49:18 +0100 222)         @Override
f68d10ab (David A. Velasco 2012-12-04 12:49:18 +0100 223)         protected Boolean doInBackground(Void... params) {
f68d10ab (David A. Velasco 2012-12-04 12:49:18 +0100 224)             while (!mLocalPaths.isEmpty()) {
f68d10ab (David A. Velasco 2012-12-04 12:49:18 +0100 225)                 String currentPath = mLocalPaths.get(0);
f68d10ab (David A. Velasco 2012-12-04 12:49:18 +0100 226)                 File currentFile = new File(currentPath);
f68d10ab (David A. Velasco 2012-12-04 12:49:18 +0100 227)                 String expectedPath = FileStorageUtils.getSavePath(mAccount.name) + mRemotePaths.get(0);
f68d10ab (David A. Velasco 2012-12-04 12:49:18 +0100 228)                 File expectedFile = new File(expectedPath);
f68d10ab (David A. Velasco 2012-12-04 12:49:18 +0100 229) 
f68d10ab (David A. Velasco 2012-12-04 12:49:18 +0100 230)                 if (expectedFile.equals(currentFile) || currentFile.renameTo(expectedFile)) {
f68d10ab (David A. Velasco 2012-12-04 12:49:18 +0100 231)                     // SUCCESS
f68d10ab (David A. Velasco 2012-12-04 12:49:18 +0100 232)                     OCFile file = mStorageManager.getFileByPath(mRemotePaths.get(0));
f68d10ab (David A. Velasco 2012-12-04 12:49:18 +0100 233)                     file.setStoragePath(expectedPath);
f68d10ab (David A. Velasco 2012-12-04 12:49:18 +0100 234)                     mStorageManager.saveFile(file);
f68d10ab (David A. Velasco 2012-12-04 12:49:18 +0100 235)                     mRemotePaths.remove(0);
f68d10ab (David A. Velasco 2012-12-04 12:49:18 +0100 236)                     mLocalPaths.remove(0);
f68d10ab (David A. Velasco 2012-12-04 12:49:18 +0100 237)                         
f68d10ab (David A. Velasco 2012-12-04 12:49:18 +0100 238)                 } else {
f68d10ab (David A. Velasco 2012-12-04 12:49:18 +0100 239)                     // FAIL
f68d10ab (David A. Velasco 2012-12-04 12:49:18 +0100 240)                     return false;   
f68d10ab (David A. Velasco 2012-12-04 12:49:18 +0100 241)                 }
f68d10ab (David A. Velasco 2012-12-04 12:49:18 +0100 242)             }
f68d10ab (David A. Velasco 2012-12-04 12:49:18 +0100 243)             return true;
f68d10ab (David A. Velasco 2012-12-04 12:49:18 +0100 244)         }
f68d10ab (David A. Velasco 2012-12-04 12:49:18 +0100 245) 
f68d10ab (David A. Velasco 2012-12-04 12:49:18 +0100 246)         /**
f68d10ab (David A. Velasco 2012-12-04 12:49:18 +0100 247)          * Updates the activity UI after the movement of local files is tried.
f68d10ab (David A. Velasco 2012-12-04 12:49:18 +0100 248)          * 
f68d10ab (David A. Velasco 2012-12-04 12:49:18 +0100 249)          * If the movement was successful for all the files, finishes the activity immediately.
f68d10ab (David A. Velasco 2012-12-04 12:49:18 +0100 250)          * 
f68d10ab (David A. Velasco 2012-12-04 12:49:18 +0100 251)          * In other case, the list of remaining files is still available to retry the movement.
f68d10ab (David A. Velasco 2012-12-04 12:49:18 +0100 252)          * 
45588662 (David A. Velasco 2012-12-04 14:20:02 +0100 253)          * @param result      'True' when the movement was successful.
f68d10ab (David A. Velasco 2012-12-04 12:49:18 +0100 254)          */
f68d10ab (David A. Velasco 2012-12-04 12:49:18 +0100 255)         @Override
f68d10ab (David A. Velasco 2012-12-04 12:49:18 +0100 256)         protected void onPostExecute(Boolean result) {
f68d10ab (David A. Velasco 2012-12-04 12:49:18 +0100 257)             mAdapter.notifyDataSetChanged();
f68d10ab (David A. Velasco 2012-12-04 12:49:18 +0100 258)             mCurrentDialog.dismiss();
f68d10ab (David A. Velasco 2012-12-04 12:49:18 +0100 259)             mCurrentDialog = null;
f68d10ab (David A. Velasco 2012-12-04 12:49:18 +0100 260)             findViewById(R.id.ok).setEnabled(true);
f68d10ab (David A. Velasco 2012-12-04 12:49:18 +0100 261)             
f68d10ab (David A. Velasco 2012-12-04 12:49:18 +0100 262)             if (result) {
f68d10ab (David A. Velasco 2012-12-04 12:49:18 +0100 263)                 // nothing else to do in this activity
f68d10ab (David A. Velasco 2012-12-04 12:49:18 +0100 264)                 Toast t = Toast.makeText(ErrorsWhileCopyingHandlerActivity.this, getString(R.string.foreign_files_success), Toast.LENGTH_LONG);
f68d10ab (David A. Velasco 2012-12-04 12:49:18 +0100 265)                 t.show();
f68d10ab (David A. Velasco 2012-12-04 12:49:18 +0100 266)                 finish();
f68d10ab (David A. Velasco 2012-12-04 12:49:18 +0100 267)                 
f68d10ab (David A. Velasco 2012-12-04 12:49:18 +0100 268)             } else {
f68d10ab (David A. Velasco 2012-12-04 12:49:18 +0100 269)                 Toast t = Toast.makeText(ErrorsWhileCopyingHandlerActivity.this, getString(R.string.foreign_files_fail), Toast.LENGTH_LONG);
f68d10ab (David A. Velasco 2012-12-04 12:49:18 +0100 270)                 t.show();
f68d10ab (David A. Velasco 2012-12-04 12:49:18 +0100 271)             }
f68d10ab (David A. Velasco 2012-12-04 12:49:18 +0100 272)         }
f68d10ab (David A. Velasco 2012-12-04 12:49:18 +0100 273)     }    
f68d10ab (David A. Velasco 2012-12-04 12:49:18 +0100 274) 
f68d10ab (David A. Velasco 2012-12-04 12:49:18 +0100 275) }
