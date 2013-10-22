002e1bf9 (zerginator       2013-04-28 11:33:20 +0200   1) /* ownCloud Android client application
002e1bf9 (zerginator       2013-04-28 11:33:20 +0200   2)  *   Copyright (C) 2012  Bartek Przybylski
002e1bf9 (zerginator       2013-04-28 11:33:20 +0200   3)  *   Copyright (C) 2012-2013 ownCloud Inc.
002e1bf9 (zerginator       2013-04-28 11:33:20 +0200   4)  *
002e1bf9 (zerginator       2013-04-28 11:33:20 +0200   5)  *   This program is free software: you can redistribute it and/or modify
bdc0332c (David A. Velasco 2013-04-17 12:26:13 +0200   6)  *   it under the terms of the GNU General Public License version 2,
bdc0332c (David A. Velasco 2013-04-17 12:26:13 +0200   7)  *   as published by the Free Software Foundation.
002e1bf9 (zerginator       2013-04-28 11:33:20 +0200   8)  *
002e1bf9 (zerginator       2013-04-28 11:33:20 +0200   9)  *   This program is distributed in the hope that it will be useful,
002e1bf9 (zerginator       2013-04-28 11:33:20 +0200  10)  *   but WITHOUT ANY WARRANTY; without even the implied warranty of
002e1bf9 (zerginator       2013-04-28 11:33:20 +0200  11)  *   MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
002e1bf9 (zerginator       2013-04-28 11:33:20 +0200  12)  *   GNU General Public License for more details.
002e1bf9 (zerginator       2013-04-28 11:33:20 +0200  13)  *
002e1bf9 (zerginator       2013-04-28 11:33:20 +0200  14)  *   You should have received a copy of the GNU General Public License
002e1bf9 (zerginator       2013-04-28 11:33:20 +0200  15)  *   along with this program.  If not, see <http://www.gnu.org/licenses/>.
002e1bf9 (zerginator       2013-04-28 11:33:20 +0200  16)  *
002e1bf9 (zerginator       2013-04-28 11:33:20 +0200  17)  */
2946d8dd (David A. Velasco 2013-04-25 19:39:22 +0200  18) 
002e1bf9 (zerginator       2013-04-28 11:33:20 +0200  19) package com.owncloud.android;
002e1bf9 (zerginator       2013-04-28 11:33:20 +0200  20) 
002e1bf9 (zerginator       2013-04-28 11:33:20 +0200  21) import java.io.File;
002e1bf9 (zerginator       2013-04-28 11:33:20 +0200  22) import java.util.ArrayList;
002e1bf9 (zerginator       2013-04-28 11:33:20 +0200  23) import java.util.HashMap;
002e1bf9 (zerginator       2013-04-28 11:33:20 +0200  24) import java.util.LinkedList;
002e1bf9 (zerginator       2013-04-28 11:33:20 +0200  25) import java.util.List;
002e1bf9 (zerginator       2013-04-28 11:33:20 +0200  26) import java.util.Stack;
002e1bf9 (zerginator       2013-04-28 11:33:20 +0200  27) import java.util.Vector;
002e1bf9 (zerginator       2013-04-28 11:33:20 +0200  28) 
002e1bf9 (zerginator       2013-04-28 11:33:20 +0200  29) import android.accounts.Account;
002e1bf9 (zerginator       2013-04-28 11:33:20 +0200  30) import android.accounts.AccountManager;
002e1bf9 (zerginator       2013-04-28 11:33:20 +0200  31) import android.app.AlertDialog;
002e1bf9 (zerginator       2013-04-28 11:33:20 +0200  32) import android.app.AlertDialog.Builder;
002e1bf9 (zerginator       2013-04-28 11:33:20 +0200  33) import android.app.Dialog;
002e1bf9 (zerginator       2013-04-28 11:33:20 +0200  34) import android.app.ListActivity;
002e1bf9 (zerginator       2013-04-28 11:33:20 +0200  35) import android.app.ProgressDialog;
002e1bf9 (zerginator       2013-04-28 11:33:20 +0200  36) import android.content.Context;
002e1bf9 (zerginator       2013-04-28 11:33:20 +0200  37) import android.content.DialogInterface;
002e1bf9 (zerginator       2013-04-28 11:33:20 +0200  38) import android.content.DialogInterface.OnCancelListener;
002e1bf9 (zerginator       2013-04-28 11:33:20 +0200  39) import android.content.DialogInterface.OnClickListener;
002e1bf9 (zerginator       2013-04-28 11:33:20 +0200  40) import android.content.Intent;
002e1bf9 (zerginator       2013-04-28 11:33:20 +0200  41) import android.database.Cursor;
002e1bf9 (zerginator       2013-04-28 11:33:20 +0200  42) import android.net.Uri;
002e1bf9 (zerginator       2013-04-28 11:33:20 +0200  43) import android.os.Bundle;
002e1bf9 (zerginator       2013-04-28 11:33:20 +0200  44) import android.os.Parcelable;
002e1bf9 (zerginator       2013-04-28 11:33:20 +0200  45) import android.provider.MediaStore.Audio;
002e1bf9 (zerginator       2013-04-28 11:33:20 +0200  46) import android.provider.MediaStore.Images;
002e1bf9 (zerginator       2013-04-28 11:33:20 +0200  47) import android.provider.MediaStore.Video;
002e1bf9 (zerginator       2013-04-28 11:33:20 +0200  48) import android.view.View;
002e1bf9 (zerginator       2013-04-28 11:33:20 +0200  49) import android.view.Window;
002e1bf9 (zerginator       2013-04-28 11:33:20 +0200  50) import android.widget.AdapterView;
002e1bf9 (zerginator       2013-04-28 11:33:20 +0200  51) import android.widget.AdapterView.OnItemClickListener;
002e1bf9 (zerginator       2013-04-28 11:33:20 +0200  52) import android.widget.Button;
002e1bf9 (zerginator       2013-04-28 11:33:20 +0200  53) import android.widget.EditText;
002e1bf9 (zerginator       2013-04-28 11:33:20 +0200  54) import android.widget.SimpleAdapter;
002e1bf9 (zerginator       2013-04-28 11:33:20 +0200  55) import android.widget.Toast;
002e1bf9 (zerginator       2013-04-28 11:33:20 +0200  56) 
65f403bf (David A. Velasco 2013-05-03 14:25:02 +0200  57) import com.owncloud.android.authentication.AccountAuthenticator;
002e1bf9 (zerginator       2013-04-28 11:33:20 +0200  58) import com.owncloud.android.datamodel.DataStorageManager;
002e1bf9 (zerginator       2013-04-28 11:33:20 +0200  59) import com.owncloud.android.datamodel.FileDataStorageManager;
002e1bf9 (zerginator       2013-04-28 11:33:20 +0200  60) import com.owncloud.android.datamodel.OCFile;
002e1bf9 (zerginator       2013-04-28 11:33:20 +0200  61) import com.owncloud.android.files.services.FileUploader;
002e1bf9 (zerginator       2013-04-28 11:33:20 +0200  62) 
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100  63) import com.owncloud.android.R;
002e1bf9 (zerginator       2013-04-28 11:33:20 +0200  64) 
002e1bf9 (zerginator       2013-04-28 11:33:20 +0200  65) /**
002e1bf9 (zerginator       2013-04-28 11:33:20 +0200  66)  * This can be used to upload things to an ownCloud instance.
002e1bf9 (zerginator       2013-04-28 11:33:20 +0200  67)  * 
002e1bf9 (zerginator       2013-04-28 11:33:20 +0200  68)  * @author Bartek Przybylski
002e1bf9 (zerginator       2013-04-28 11:33:20 +0200  69)  * 
002e1bf9 (zerginator       2013-04-28 11:33:20 +0200  70)  */
002e1bf9 (zerginator       2013-04-28 11:33:20 +0200  71) public class Uploader extends ListActivity implements OnItemClickListener, android.view.View.OnClickListener {
002e1bf9 (zerginator       2013-04-28 11:33:20 +0200  72)     private static final String TAG = "ownCloudUploader";
002e1bf9 (zerginator       2013-04-28 11:33:20 +0200  73) 
002e1bf9 (zerginator       2013-04-28 11:33:20 +0200  74)     private Account mAccount;
002e1bf9 (zerginator       2013-04-28 11:33:20 +0200  75)     private AccountManager mAccountManager;
002e1bf9 (zerginator       2013-04-28 11:33:20 +0200  76)     private Stack<String> mParents;
002e1bf9 (zerginator       2013-04-28 11:33:20 +0200  77)     private ArrayList<Parcelable> mStreamsToUpload;
002e1bf9 (zerginator       2013-04-28 11:33:20 +0200  78)     private boolean mCreateDir;
002e1bf9 (zerginator       2013-04-28 11:33:20 +0200  79)     private String mUploadPath;
002e1bf9 (zerginator       2013-04-28 11:33:20 +0200  80)     private DataStorageManager mStorageManager;
002e1bf9 (zerginator       2013-04-28 11:33:20 +0200  81)     private OCFile mFile;
002e1bf9 (zerginator       2013-04-28 11:33:20 +0200  82) 
002e1bf9 (zerginator       2013-04-28 11:33:20 +0200  83)     private final static int DIALOG_NO_ACCOUNT = 0;
002e1bf9 (zerginator       2013-04-28 11:33:20 +0200  84)     private final static int DIALOG_WAITING = 1;
002e1bf9 (zerginator       2013-04-28 11:33:20 +0200  85)     private final static int DIALOG_NO_STREAM = 2;
002e1bf9 (zerginator       2013-04-28 11:33:20 +0200  86)     private final static int DIALOG_MULTIPLE_ACCOUNT = 3;
002e1bf9 (zerginator       2013-04-28 11:33:20 +0200  87) 
002e1bf9 (zerginator       2013-04-28 11:33:20 +0200  88)     private final static int REQUEST_CODE_SETUP_ACCOUNT = 0;
002e1bf9 (zerginator       2013-04-28 11:33:20 +0200  89) 
002e1bf9 (zerginator       2013-04-28 11:33:20 +0200  90)     @Override
002e1bf9 (zerginator       2013-04-28 11:33:20 +0200  91)     protected void onCreate(Bundle savedInstanceState) {
002e1bf9 (zerginator       2013-04-28 11:33:20 +0200  92)         super.onCreate(savedInstanceState);
002e1bf9 (zerginator       2013-04-28 11:33:20 +0200  93)         getWindow().requestFeature(Window.FEATURE_NO_TITLE);
002e1bf9 (zerginator       2013-04-28 11:33:20 +0200  94)         mParents = new Stack<String>();
002e1bf9 (zerginator       2013-04-28 11:33:20 +0200  95)         mParents.add("");
002e1bf9 (zerginator       2013-04-28 11:33:20 +0200  96)         if (prepareStreamsToUpload()) {
002e1bf9 (zerginator       2013-04-28 11:33:20 +0200  97)             mAccountManager = (AccountManager) getSystemService(Context.ACCOUNT_SERVICE);
002e1bf9 (zerginator       2013-04-28 11:33:20 +0200  98)             Account[] accounts = mAccountManager.getAccountsByType(AccountAuthenticator.ACCOUNT_TYPE);
002e1bf9 (zerginator       2013-04-28 11:33:20 +0200  99)             if (accounts.length == 0) {
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100 100)                 Log_OC.i(TAG, "No ownCloud account is available");
002e1bf9 (zerginator       2013-04-28 11:33:20 +0200 101)                 showDialog(DIALOG_NO_ACCOUNT);
002e1bf9 (zerginator       2013-04-28 11:33:20 +0200 102)             } else if (accounts.length > 1) {
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100 103)                 Log_OC.i(TAG, "More then one ownCloud is available");
002e1bf9 (zerginator       2013-04-28 11:33:20 +0200 104)                 showDialog(DIALOG_MULTIPLE_ACCOUNT);
002e1bf9 (zerginator       2013-04-28 11:33:20 +0200 105)             } else {
002e1bf9 (zerginator       2013-04-28 11:33:20 +0200 106)                 mAccount = accounts[0];
002e1bf9 (zerginator       2013-04-28 11:33:20 +0200 107)                 mStorageManager = new FileDataStorageManager(mAccount, getContentResolver());
002e1bf9 (zerginator       2013-04-28 11:33:20 +0200 108)                 populateDirectoryList();
002e1bf9 (zerginator       2013-04-28 11:33:20 +0200 109)             }
002e1bf9 (zerginator       2013-04-28 11:33:20 +0200 110)         } else {
002e1bf9 (zerginator       2013-04-28 11:33:20 +0200 111)             showDialog(DIALOG_NO_STREAM);
002e1bf9 (zerginator       2013-04-28 11:33:20 +0200 112)         }
002e1bf9 (zerginator       2013-04-28 11:33:20 +0200 113)     }
002e1bf9 (zerginator       2013-04-28 11:33:20 +0200 114)     
002e1bf9 (zerginator       2013-04-28 11:33:20 +0200 115)     @Override
002e1bf9 (zerginator       2013-04-28 11:33:20 +0200 116)     protected Dialog onCreateDialog(final int id) {
002e1bf9 (zerginator       2013-04-28 11:33:20 +0200 117)         final AlertDialog.Builder builder = new Builder(this);
002e1bf9 (zerginator       2013-04-28 11:33:20 +0200 118)         switch (id) {
002e1bf9 (zerginator       2013-04-28 11:33:20 +0200 119)         case DIALOG_WAITING:
002e1bf9 (zerginator       2013-04-28 11:33:20 +0200 120)             ProgressDialog pDialog = new ProgressDialog(this);
002e1bf9 (zerginator       2013-04-28 11:33:20 +0200 121)             pDialog.setIndeterminate(false);
002e1bf9 (zerginator       2013-04-28 11:33:20 +0200 122)             pDialog.setCancelable(false);
002e1bf9 (zerginator       2013-04-28 11:33:20 +0200 123)             pDialog.setMessage(getResources().getString(R.string.uploader_info_uploading));
002e1bf9 (zerginator       2013-04-28 11:33:20 +0200 124)             return pDialog;
002e1bf9 (zerginator       2013-04-28 11:33:20 +0200 125)         case DIALOG_NO_ACCOUNT:
002e1bf9 (zerginator       2013-04-28 11:33:20 +0200 126)             builder.setIcon(android.R.drawable.ic_dialog_alert);
002e1bf9 (zerginator       2013-04-28 11:33:20 +0200 127)             builder.setTitle(R.string.uploader_wrn_no_account_title);
002e1bf9 (zerginator       2013-04-28 11:33:20 +0200 128)             builder.setMessage(String.format(getString(R.string.uploader_wrn_no_account_text), getString(R.string.app_name)));
002e1bf9 (zerginator       2013-04-28 11:33:20 +0200 129)             builder.setCancelable(false);
002e1bf9 (zerginator       2013-04-28 11:33:20 +0200 130)             builder.setPositiveButton(R.string.uploader_wrn_no_account_setup_btn_text, new OnClickListener() {
002e1bf9 (zerginator       2013-04-28 11:33:20 +0200 131)                 @Override
002e1bf9 (zerginator       2013-04-28 11:33:20 +0200 132)                 public void onClick(DialogInterface dialog, int which) {
002e1bf9 (zerginator       2013-04-28 11:33:20 +0200 133)                     if (android.os.Build.VERSION.SDK_INT > android.os.Build.VERSION_CODES.ECLAIR_MR1) {
002e1bf9 (zerginator       2013-04-28 11:33:20 +0200 134)                         // using string value since in API7 this
002e1bf9 (zerginator       2013-04-28 11:33:20 +0200 135)                         // constatn is not defined
002e1bf9 (zerginator       2013-04-28 11:33:20 +0200 136)                         // in API7 < this constatant is defined in
002e1bf9 (zerginator       2013-04-28 11:33:20 +0200 137)                         // Settings.ADD_ACCOUNT_SETTINGS
002e1bf9 (zerginator       2013-04-28 11:33:20 +0200 138)                         // and Settings.EXTRA_AUTHORITIES
2946d8dd (David A. Velasco 2013-04-25 19:39:22 +0200 139)                         Intent intent = new Intent(android.provider.Settings.ACTION_ADD_ACCOUNT);
2946d8dd (David A. Velasco 2013-04-25 19:39:22 +0200 140)                         intent.putExtra("authorities", new String[] { AccountAuthenticator.AUTHORITY });
002e1bf9 (zerginator       2013-04-28 11:33:20 +0200 141)                         startActivityForResult(intent, REQUEST_CODE_SETUP_ACCOUNT);
002e1bf9 (zerginator       2013-04-28 11:33:20 +0200 142)                     } else {
002e1bf9 (zerginator       2013-04-28 11:33:20 +0200 143)                         // since in API7 there is no direct call for
002e1bf9 (zerginator       2013-04-28 11:33:20 +0200 144)                         // account setup, so we need to
002e1bf9 (zerginator       2013-04-28 11:33:20 +0200 145)                         // show our own AccountSetupAcricity, get
002e1bf9 (zerginator       2013-04-28 11:33:20 +0200 146)                         // desired results and setup
002e1bf9 (zerginator       2013-04-28 11:33:20 +0200 147)                         // everything for ourself
002e1bf9 (zerginator       2013-04-28 11:33:20 +0200 148)                         Intent intent = new Intent(getBaseContext(), AccountAuthenticator.class);
002e1bf9 (zerginator       2013-04-28 11:33:20 +0200 149)                         startActivityForResult(intent, REQUEST_CODE_SETUP_ACCOUNT);
002e1bf9 (zerginator       2013-04-28 11:33:20 +0200 150)                     }
002e1bf9 (zerginator       2013-04-28 11:33:20 +0200 151)                 }
002e1bf9 (zerginator       2013-04-28 11:33:20 +0200 152)             });
002e1bf9 (zerginator       2013-04-28 11:33:20 +0200 153)             builder.setNegativeButton(R.string.uploader_wrn_no_account_quit_btn_text, new OnClickListener() {
002e1bf9 (zerginator       2013-04-28 11:33:20 +0200 154)                 @Override
002e1bf9 (zerginator       2013-04-28 11:33:20 +0200 155)                 public void onClick(DialogInterface dialog, int which) {
002e1bf9 (zerginator       2013-04-28 11:33:20 +0200 156)                     finish();
002e1bf9 (zerginator       2013-04-28 11:33:20 +0200 157)                 }
002e1bf9 (zerginator       2013-04-28 11:33:20 +0200 158)             });
002e1bf9 (zerginator       2013-04-28 11:33:20 +0200 159)             return builder.create();
002e1bf9 (zerginator       2013-04-28 11:33:20 +0200 160)         case DIALOG_MULTIPLE_ACCOUNT:
002e1bf9 (zerginator       2013-04-28 11:33:20 +0200 161)             CharSequence ac[] = new CharSequence[mAccountManager.getAccountsByType(AccountAuthenticator.ACCOUNT_TYPE).length];
002e1bf9 (zerginator       2013-04-28 11:33:20 +0200 162)             for (int i = 0; i < ac.length; ++i) {
002e1bf9 (zerginator       2013-04-28 11:33:20 +0200 163)                 ac[i] = mAccountManager.getAccountsByType(AccountAuthenticator.ACCOUNT_TYPE)[i].name;
002e1bf9 (zerginator       2013-04-28 11:33:20 +0200 164)             }
002e1bf9 (zerginator       2013-04-28 11:33:20 +0200 165)             builder.setTitle(R.string.common_choose_account);
002e1bf9 (zerginator       2013-04-28 11:33:20 +0200 166)             builder.setItems(ac, new OnClickListener() {
002e1bf9 (zerginator       2013-04-28 11:33:20 +0200 167)                 @Override
002e1bf9 (zerginator       2013-04-28 11:33:20 +0200 168)                 public void onClick(DialogInterface dialog, int which) {
002e1bf9 (zerginator       2013-04-28 11:33:20 +0200 169)                     mAccount = mAccountManager.getAccountsByType(AccountAuthenticator.ACCOUNT_TYPE)[which];
002e1bf9 (zerginator       2013-04-28 11:33:20 +0200 170)                     mStorageManager = new FileDataStorageManager(mAccount, getContentResolver());
002e1bf9 (zerginator       2013-04-28 11:33:20 +0200 171)                     populateDirectoryList();
002e1bf9 (zerginator       2013-04-28 11:33:20 +0200 172)                 }
002e1bf9 (zerginator       2013-04-28 11:33:20 +0200 173)             });
002e1bf9 (zerginator       2013-04-28 11:33:20 +0200 174)             builder.setCancelable(true);
002e1bf9 (zerginator       2013-04-28 11:33:20 +0200 175)             builder.setOnCancelListener(new OnCancelListener() {
002e1bf9 (zerginator       2013-04-28 11:33:20 +0200 176)                 @Override
002e1bf9 (zerginator       2013-04-28 11:33:20 +0200 177)                 public void onCancel(DialogInterface dialog) {
002e1bf9 (zerginator       2013-04-28 11:33:20 +0200 178)                     dialog.cancel();
002e1bf9 (zerginator       2013-04-28 11:33:20 +0200 179)                     finish();
002e1bf9 (zerginator       2013-04-28 11:33:20 +0200 180)                 }
002e1bf9 (zerginator       2013-04-28 11:33:20 +0200 181)             });
002e1bf9 (zerginator       2013-04-28 11:33:20 +0200 182)             return builder.create();
002e1bf9 (zerginator       2013-04-28 11:33:20 +0200 183)         case DIALOG_NO_STREAM:
002e1bf9 (zerginator       2013-04-28 11:33:20 +0200 184)             builder.setIcon(android.R.drawable.ic_dialog_alert);
002e1bf9 (zerginator       2013-04-28 11:33:20 +0200 185)             builder.setTitle(R.string.uploader_wrn_no_content_title);
002e1bf9 (zerginator       2013-04-28 11:33:20 +0200 186)             builder.setMessage(R.string.uploader_wrn_no_content_text);
002e1bf9 (zerginator       2013-04-28 11:33:20 +0200 187)             builder.setCancelable(false);
002e1bf9 (zerginator       2013-04-28 11:33:20 +0200 188)             builder.setNegativeButton(R.string.common_cancel, new OnClickListener() {
002e1bf9 (zerginator       2013-04-28 11:33:20 +0200 189)                 @Override
002e1bf9 (zerginator       2013-04-28 11:33:20 +0200 190)                 public void onClick(DialogInterface dialog, int which) {
002e1bf9 (zerginator       2013-04-28 11:33:20 +0200 191)                     finish();
002e1bf9 (zerginator       2013-04-28 11:33:20 +0200 192)                 }
002e1bf9 (zerginator       2013-04-28 11:33:20 +0200 193)             });
002e1bf9 (zerginator       2013-04-28 11:33:20 +0200 194)             return builder.create();
002e1bf9 (zerginator       2013-04-28 11:33:20 +0200 195)         default:
002e1bf9 (zerginator       2013-04-28 11:33:20 +0200 196)             throw new IllegalArgumentException("Unknown dialog id: " + id);
002e1bf9 (zerginator       2013-04-28 11:33:20 +0200 197)         }
002e1bf9 (zerginator       2013-04-28 11:33:20 +0200 198)     }
002e1bf9 (zerginator       2013-04-28 11:33:20 +0200 199) 
002e1bf9 (zerginator       2013-04-28 11:33:20 +0200 200)     class a implements OnClickListener {
002e1bf9 (zerginator       2013-04-28 11:33:20 +0200 201)         String mPath;
002e1bf9 (zerginator       2013-04-28 11:33:20 +0200 202)         EditText mDirname;
002e1bf9 (zerginator       2013-04-28 11:33:20 +0200 203) 
002e1bf9 (zerginator       2013-04-28 11:33:20 +0200 204)         public a(String path, EditText dirname) {
002e1bf9 (zerginator       2013-04-28 11:33:20 +0200 205)             mPath = path; 
002e1bf9 (zerginator       2013-04-28 11:33:20 +0200 206)             mDirname = dirname;
002e1bf9 (zerginator       2013-04-28 11:33:20 +0200 207)         }
002e1bf9 (zerginator       2013-04-28 11:33:20 +0200 208) 
002e1bf9 (zerginator       2013-04-28 11:33:20 +0200 209)         @Override
002e1bf9 (zerginator       2013-04-28 11:33:20 +0200 210)         public void onClick(DialogInterface dialog, int which) {
002e1bf9 (zerginator       2013-04-28 11:33:20 +0200 211)             Uploader.this.mUploadPath = mPath + mDirname.getText().toString();
002e1bf9 (zerginator       2013-04-28 11:33:20 +0200 212)             Uploader.this.mCreateDir = true;
002e1bf9 (zerginator       2013-04-28 11:33:20 +0200 213)             uploadFiles();
002e1bf9 (zerginator       2013-04-28 11:33:20 +0200 214)         }
002e1bf9 (zerginator       2013-04-28 11:33:20 +0200 215)     }
002e1bf9 (zerginator       2013-04-28 11:33:20 +0200 216) 
002e1bf9 (zerginator       2013-04-28 11:33:20 +0200 217)     @Override
002e1bf9 (zerginator       2013-04-28 11:33:20 +0200 218)     public void onBackPressed() {
002e1bf9 (zerginator       2013-04-28 11:33:20 +0200 219) 
002e1bf9 (zerginator       2013-04-28 11:33:20 +0200 220)         if (mParents.size() <= 1) {
002e1bf9 (zerginator       2013-04-28 11:33:20 +0200 221)             super.onBackPressed();
002e1bf9 (zerginator       2013-04-28 11:33:20 +0200 222)             return;
002e1bf9 (zerginator       2013-04-28 11:33:20 +0200 223)         } else {
002e1bf9 (zerginator       2013-04-28 11:33:20 +0200 224)             mParents.pop();
002e1bf9 (zerginator       2013-04-28 11:33:20 +0200 225)             populateDirectoryList();
002e1bf9 (zerginator       2013-04-28 11:33:20 +0200 226)         }
002e1bf9 (zerginator       2013-04-28 11:33:20 +0200 227)     }
002e1bf9 (zerginator       2013-04-28 11:33:20 +0200 228) 
002e1bf9 (zerginator       2013-04-28 11:33:20 +0200 229)     @Override
002e1bf9 (zerginator       2013-04-28 11:33:20 +0200 230)     public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
002e1bf9 (zerginator       2013-04-28 11:33:20 +0200 231)         // click on folder in the list
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100 232)         Log_OC.d(TAG, "on item click");
002e1bf9 (zerginator       2013-04-28 11:33:20 +0200 233)         Vector<OCFile> tmpfiles = mStorageManager.getDirectoryContent(mFile);
002e1bf9 (zerginator       2013-04-28 11:33:20 +0200 234)         if (tmpfiles.size() <= 0) return;
002e1bf9 (zerginator       2013-04-28 11:33:20 +0200 235)         // filter on dirtype
002e1bf9 (zerginator       2013-04-28 11:33:20 +0200 236)         Vector<OCFile> files = new Vector<OCFile>();
002e1bf9 (zerginator       2013-04-28 11:33:20 +0200 237)         for (OCFile f : tmpfiles)
002e1bf9 (zerginator       2013-04-28 11:33:20 +0200 238)             if (f.isDirectory())
002e1bf9 (zerginator       2013-04-28 11:33:20 +0200 239)                 files.add(f);
002e1bf9 (zerginator       2013-04-28 11:33:20 +0200 240)         if (files.size() < position) {
002e1bf9 (zerginator       2013-04-28 11:33:20 +0200 241)             throw new IndexOutOfBoundsException("Incorrect item selected");
002e1bf9 (zerginator       2013-04-28 11:33:20 +0200 242)         }
002e1bf9 (zerginator       2013-04-28 11:33:20 +0200 243)         mParents.push(files.get(position).getFileName());
002e1bf9 (zerginator       2013-04-28 11:33:20 +0200 244)         populateDirectoryList();
002e1bf9 (zerginator       2013-04-28 11:33:20 +0200 245)     }
002e1bf9 (zerginator       2013-04-28 11:33:20 +0200 246) 
002e1bf9 (zerginator       2013-04-28 11:33:20 +0200 247)     @Override
002e1bf9 (zerginator       2013-04-28 11:33:20 +0200 248)     public void onClick(View v) {
002e1bf9 (zerginator       2013-04-28 11:33:20 +0200 249)         // click on button
002e1bf9 (zerginator       2013-04-28 11:33:20 +0200 250)         switch (v.getId()) {
002e1bf9 (zerginator       2013-04-28 11:33:20 +0200 251)         case R.id.uploader_choose_folder:
002e1bf9 (zerginator       2013-04-28 11:33:20 +0200 252)             mUploadPath = "";   // first element in mParents is root dir, represented by ""; init mUploadPath with "/" results in a "//" prefix
002e1bf9 (zerginator       2013-04-28 11:33:20 +0200 253)             for (String p : mParents)
002e1bf9 (zerginator       2013-04-28 11:33:20 +0200 254)                 mUploadPath += p + OCFile.PATH_SEPARATOR;
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100 255)             Log_OC.d(TAG, "Uploading file to dir " + mUploadPath);
002e1bf9 (zerginator       2013-04-28 11:33:20 +0200 256) 
002e1bf9 (zerginator       2013-04-28 11:33:20 +0200 257)             uploadFiles();
002e1bf9 (zerginator       2013-04-28 11:33:20 +0200 258) 
002e1bf9 (zerginator       2013-04-28 11:33:20 +0200 259)             break;
002e1bf9 (zerginator       2013-04-28 11:33:20 +0200 260)         default:
002e1bf9 (zerginator       2013-04-28 11:33:20 +0200 261)             throw new IllegalArgumentException("Wrong element clicked");
002e1bf9 (zerginator       2013-04-28 11:33:20 +0200 262)         }
002e1bf9 (zerginator       2013-04-28 11:33:20 +0200 263)     }
002e1bf9 (zerginator       2013-04-28 11:33:20 +0200 264) 
002e1bf9 (zerginator       2013-04-28 11:33:20 +0200 265)     @Override
002e1bf9 (zerginator       2013-04-28 11:33:20 +0200 266)     protected void onActivityResult(int requestCode, int resultCode, Intent data) {
002e1bf9 (zerginator       2013-04-28 11:33:20 +0200 267)         super.onActivityResult(requestCode, resultCode, data);
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100 268)         Log_OC.i(TAG, "result received. req: " + requestCode + " res: " + resultCode);
002e1bf9 (zerginator       2013-04-28 11:33:20 +0200 269)         if (requestCode == REQUEST_CODE_SETUP_ACCOUNT) {
002e1bf9 (zerginator       2013-04-28 11:33:20 +0200 270)             dismissDialog(DIALOG_NO_ACCOUNT);
002e1bf9 (zerginator       2013-04-28 11:33:20 +0200 271)             if (resultCode == RESULT_CANCELED) {
002e1bf9 (zerginator       2013-04-28 11:33:20 +0200 272)                 finish();
002e1bf9 (zerginator       2013-04-28 11:33:20 +0200 273)             }
002e1bf9 (zerginator       2013-04-28 11:33:20 +0200 274)             Account[] accounts = mAccountManager.getAccountsByType(AccountAuthenticator.AUTH_TOKEN_TYPE);
002e1bf9 (zerginator       2013-04-28 11:33:20 +0200 275)             if (accounts.length == 0) {
002e1bf9 (zerginator       2013-04-28 11:33:20 +0200 276)                 showDialog(DIALOG_NO_ACCOUNT);
002e1bf9 (zerginator       2013-04-28 11:33:20 +0200 277)             } else {
002e1bf9 (zerginator       2013-04-28 11:33:20 +0200 278)                 // there is no need for checking for is there more then one
002e1bf9 (zerginator       2013-04-28 11:33:20 +0200 279)                 // account at this point
002e1bf9 (zerginator       2013-04-28 11:33:20 +0200 280)                 // since account setup can set only one account at time
002e1bf9 (zerginator       2013-04-28 11:33:20 +0200 281)                 mAccount = accounts[0];
002e1bf9 (zerginator       2013-04-28 11:33:20 +0200 282)                 populateDirectoryList();
002e1bf9 (zerginator       2013-04-28 11:33:20 +0200 283)             }
002e1bf9 (zerginator       2013-04-28 11:33:20 +0200 284)         }
002e1bf9 (zerginator       2013-04-28 11:33:20 +0200 285)     }
002e1bf9 (zerginator       2013-04-28 11:33:20 +0200 286) 
002e1bf9 (zerginator       2013-04-28 11:33:20 +0200 287)     private void populateDirectoryList() {
002e1bf9 (zerginator       2013-04-28 11:33:20 +0200 288)         setContentView(R.layout.uploader_layout);
002e1bf9 (zerginator       2013-04-28 11:33:20 +0200 289) 
002e1bf9 (zerginator       2013-04-28 11:33:20 +0200 290)         String full_path = "";
002e1bf9 (zerginator       2013-04-28 11:33:20 +0200 291)         for (String a : mParents)
002e1bf9 (zerginator       2013-04-28 11:33:20 +0200 292)             full_path += a + "/";
002e1bf9 (zerginator       2013-04-28 11:33:20 +0200 293)         
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100 294)         Log_OC.d(TAG, "Populating view with content of : " + full_path);
002e1bf9 (zerginator       2013-04-28 11:33:20 +0200 295)         
002e1bf9 (zerginator       2013-04-28 11:33:20 +0200 296)         mFile = mStorageManager.getFileByPath(full_path);
002e1bf9 (zerginator       2013-04-28 11:33:20 +0200 297)         if (mFile != null) {
002e1bf9 (zerginator       2013-04-28 11:33:20 +0200 298)             Vector<OCFile> files = mStorageManager.getDirectoryContent(mFile);
002e1bf9 (zerginator       2013-04-28 11:33:20 +0200 299)             List<HashMap<String, Object>> data = new LinkedList<HashMap<String,Object>>();
002e1bf9 (zerginator       2013-04-28 11:33:20 +0200 300)             for (OCFile f : files) {
002e1bf9 (zerginator       2013-04-28 11:33:20 +0200 301)                 HashMap<String, Object> h = new HashMap<String, Object>();
002e1bf9 (zerginator       2013-04-28 11:33:20 +0200 302)                 if (f.isDirectory()) {
002e1bf9 (zerginator       2013-04-28 11:33:20 +0200 303)                     h.put("dirname", f.getFileName());
002e1bf9 (zerginator       2013-04-28 11:33:20 +0200 304)                     data.add(h);
002e1bf9 (zerginator       2013-04-28 11:33:20 +0200 305)                 }
002e1bf9 (zerginator       2013-04-28 11:33:20 +0200 306)             }
002e1bf9 (zerginator       2013-04-28 11:33:20 +0200 307)             SimpleAdapter sa = new SimpleAdapter(this,
002e1bf9 (zerginator       2013-04-28 11:33:20 +0200 308)                                                 data,
002e1bf9 (zerginator       2013-04-28 11:33:20 +0200 309)                                                 R.layout.uploader_list_item_layout,
002e1bf9 (zerginator       2013-04-28 11:33:20 +0200 310)                                                 new String[] {"dirname"},
002e1bf9 (zerginator       2013-04-28 11:33:20 +0200 311)                                                 new int[] {R.id.textView1});
002e1bf9 (zerginator       2013-04-28 11:33:20 +0200 312)             setListAdapter(sa);
002e1bf9 (zerginator       2013-04-28 11:33:20 +0200 313)             Button btn = (Button) findViewById(R.id.uploader_choose_folder);
002e1bf9 (zerginator       2013-04-28 11:33:20 +0200 314)             btn.setOnClickListener(this);
002e1bf9 (zerginator       2013-04-28 11:33:20 +0200 315)             getListView().setOnItemClickListener(this);
002e1bf9 (zerginator       2013-04-28 11:33:20 +0200 316)         }
002e1bf9 (zerginator       2013-04-28 11:33:20 +0200 317)     }
002e1bf9 (zerginator       2013-04-28 11:33:20 +0200 318) 
002e1bf9 (zerginator       2013-04-28 11:33:20 +0200 319)     private boolean prepareStreamsToUpload() {
002e1bf9 (zerginator       2013-04-28 11:33:20 +0200 320)         if (getIntent().getAction().equals(Intent.ACTION_SEND)) {
002e1bf9 (zerginator       2013-04-28 11:33:20 +0200 321)             mStreamsToUpload = new ArrayList<Parcelable>();
002e1bf9 (zerginator       2013-04-28 11:33:20 +0200 322)             mStreamsToUpload.add(getIntent().getParcelableExtra(Intent.EXTRA_STREAM));
002e1bf9 (zerginator       2013-04-28 11:33:20 +0200 323)         } else if (getIntent().getAction().equals(Intent.ACTION_SEND_MULTIPLE)) {
002e1bf9 (zerginator       2013-04-28 11:33:20 +0200 324)             mStreamsToUpload = getIntent().getParcelableArrayListExtra(Intent.EXTRA_STREAM);
002e1bf9 (zerginator       2013-04-28 11:33:20 +0200 325)         }
002e1bf9 (zerginator       2013-04-28 11:33:20 +0200 326)         return (mStreamsToUpload != null && mStreamsToUpload.get(0) != null);
002e1bf9 (zerginator       2013-04-28 11:33:20 +0200 327)     }
002e1bf9 (zerginator       2013-04-28 11:33:20 +0200 328) 
002e1bf9 (zerginator       2013-04-28 11:33:20 +0200 329)     public void uploadFiles() {
002e1bf9 (zerginator       2013-04-28 11:33:20 +0200 330)         try {
65f403bf (David A. Velasco 2013-05-03 14:25:02 +0200 331)             //WebdavClient webdav = OwnCloudClientUtils.createOwnCloudClient(mAccount, getApplicationContext());
002e1bf9 (zerginator       2013-04-28 11:33:20 +0200 332) 
002e1bf9 (zerginator       2013-04-28 11:33:20 +0200 333)             ArrayList<String> local = new ArrayList<String>();
002e1bf9 (zerginator       2013-04-28 11:33:20 +0200 334)             ArrayList<String> remote = new ArrayList<String>();
002e1bf9 (zerginator       2013-04-28 11:33:20 +0200 335)             
2946d8dd (David A. Velasco 2013-04-25 19:39:22 +0200 336)             /* TODO - mCreateDir can never be true at this moment; we will replace wdc.createDirectory by CreateFolderOperation when that is fixed 
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100 337)             WebdavClient wdc = OwnCloudClientUtils.createOwnCloudClient(mAccount, getApplicationContext());
002e1bf9 (zerginator       2013-04-28 11:33:20 +0200 338)             // create last directory in path if necessary
002e1bf9 (zerginator       2013-04-28 11:33:20 +0200 339)             if (mCreateDir) {
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100 340)                 wdc.createDirectory(mUploadPath);
002e1bf9 (zerginator       2013-04-28 11:33:20 +0200 341)             }
2946d8dd (David A. Velasco 2013-04-25 19:39:22 +0200 342)             */
65f403bf (David A. Velasco 2013-05-03 14:25:02 +0200 343)             
002e1bf9 (zerginator       2013-04-28 11:33:20 +0200 344)             // this checks the mimeType 
002e1bf9 (zerginator       2013-04-28 11:33:20 +0200 345)             for (Parcelable mStream : mStreamsToUpload) {
002e1bf9 (zerginator       2013-04-28 11:33:20 +0200 346)                 
002e1bf9 (zerginator       2013-04-28 11:33:20 +0200 347)                 Uri uri = (Uri) mStream;
002e1bf9 (zerginator       2013-04-28 11:33:20 +0200 348)                 if (uri !=null) {
002e1bf9 (zerginator       2013-04-28 11:33:20 +0200 349)                     if (uri.getScheme().equals("content")) {
002e1bf9 (zerginator       2013-04-28 11:33:20 +0200 350)                         
002e1bf9 (zerginator       2013-04-28 11:33:20 +0200 351)                        String mimeType = getContentResolver().getType(uri);
002e1bf9 (zerginator       2013-04-28 11:33:20 +0200 352)                        
002e1bf9 (zerginator       2013-04-28 11:33:20 +0200 353)                        if (mimeType.contains("image")) {
002e1bf9 (zerginator       2013-04-28 11:33:20 +0200 354)                            String[] CONTENT_PROJECTION = { Images.Media.DATA, Images.Media.DISPLAY_NAME, Images.Media.MIME_TYPE, Images.Media.SIZE};
002e1bf9 (zerginator       2013-04-28 11:33:20 +0200 355)                            Cursor c = getContentResolver().query(uri, CONTENT_PROJECTION, null, null, null);
002e1bf9 (zerginator       2013-04-28 11:33:20 +0200 356)                            c.moveToFirst();
002e1bf9 (zerginator       2013-04-28 11:33:20 +0200 357)                            int index = c.getColumnIndex(Images.Media.DATA);
002e1bf9 (zerginator       2013-04-28 11:33:20 +0200 358)                            String data = c.getString(index);
002e1bf9 (zerginator       2013-04-28 11:33:20 +0200 359)                            local.add(data);
002e1bf9 (zerginator       2013-04-28 11:33:20 +0200 360)                            remote.add(mUploadPath + c.getString(c.getColumnIndex(Images.Media.DISPLAY_NAME)));
002e1bf9 (zerginator       2013-04-28 11:33:20 +0200 361)                        
002e1bf9 (zerginator       2013-04-28 11:33:20 +0200 362)                        }
002e1bf9 (zerginator       2013-04-28 11:33:20 +0200 363)                        else if (mimeType.contains("video")) {
002e1bf9 (zerginator       2013-04-28 11:33:20 +0200 364)                            String[] CONTENT_PROJECTION = { Video.Media.DATA, Video.Media.DISPLAY_NAME, Video.Media.MIME_TYPE, Video.Media.SIZE, Video.Media.DATE_MODIFIED };
002e1bf9 (zerginator       2013-04-28 11:33:20 +0200 365)                            Cursor c = getContentResolver().query(uri, CONTENT_PROJECTION, null, null, null);
002e1bf9 (zerginator       2013-04-28 11:33:20 +0200 366)                            c.moveToFirst();
002e1bf9 (zerginator       2013-04-28 11:33:20 +0200 367)                            int index = c.getColumnIndex(Video.Media.DATA);
002e1bf9 (zerginator       2013-04-28 11:33:20 +0200 368)                            String data = c.getString(index);
002e1bf9 (zerginator       2013-04-28 11:33:20 +0200 369)                            local.add(data);
002e1bf9 (zerginator       2013-04-28 11:33:20 +0200 370)                            remote.add(mUploadPath + c.getString(c.getColumnIndex(Video.Media.DISPLAY_NAME)));
002e1bf9 (zerginator       2013-04-28 11:33:20 +0200 371)                           
002e1bf9 (zerginator       2013-04-28 11:33:20 +0200 372)                        }
002e1bf9 (zerginator       2013-04-28 11:33:20 +0200 373)                        else if (mimeType.contains("audio")) {
002e1bf9 (zerginator       2013-04-28 11:33:20 +0200 374)                            String[] CONTENT_PROJECTION = { Audio.Media.DATA, Audio.Media.DISPLAY_NAME, Audio.Media.MIME_TYPE, Audio.Media.SIZE };
002e1bf9 (zerginator       2013-04-28 11:33:20 +0200 375)                            Cursor c = getContentResolver().query(uri, CONTENT_PROJECTION, null, null, null);
002e1bf9 (zerginator       2013-04-28 11:33:20 +0200 376)                            c.moveToFirst();
002e1bf9 (zerginator       2013-04-28 11:33:20 +0200 377)                            int index = c.getColumnIndex(Audio.Media.DATA);
002e1bf9 (zerginator       2013-04-28 11:33:20 +0200 378)                            String data = c.getString(index);
002e1bf9 (zerginator       2013-04-28 11:33:20 +0200 379)                            local.add(data);
002e1bf9 (zerginator       2013-04-28 11:33:20 +0200 380)                            remote.add(mUploadPath + c.getString(c.getColumnIndex(Audio.Media.DISPLAY_NAME)));
002e1bf9 (zerginator       2013-04-28 11:33:20 +0200 381)                         
002e1bf9 (zerginator       2013-04-28 11:33:20 +0200 382)                        }
002e1bf9 (zerginator       2013-04-28 11:33:20 +0200 383)                        else {
002e1bf9 (zerginator       2013-04-28 11:33:20 +0200 384)                            String filePath = Uri.decode(uri.toString()).replace(uri.getScheme() + "://", "");
002e1bf9 (zerginator       2013-04-28 11:33:20 +0200 385)                            // cut everything whats before mnt. It occured to me that sometimes apps send their name into the URI
002e1bf9 (zerginator       2013-04-28 11:33:20 +0200 386)                            if (filePath.contains("mnt")) {
002e1bf9 (zerginator       2013-04-28 11:33:20 +0200 387)                               String splitedFilePath[] = filePath.split("/mnt");
002e1bf9 (zerginator       2013-04-28 11:33:20 +0200 388)                               filePath = splitedFilePath[1];
002e1bf9 (zerginator       2013-04-28 11:33:20 +0200 389)                            }
002e1bf9 (zerginator       2013-04-28 11:33:20 +0200 390)                            final File file = new File(filePath);
002e1bf9 (zerginator       2013-04-28 11:33:20 +0200 391)                            local.add(file.getAbsolutePath());
002e1bf9 (zerginator       2013-04-28 11:33:20 +0200 392)                            remote.add(mUploadPath + file.getName());
002e1bf9 (zerginator       2013-04-28 11:33:20 +0200 393)                        }
002e1bf9 (zerginator       2013-04-28 11:33:20 +0200 394)                         
002e1bf9 (zerginator       2013-04-28 11:33:20 +0200 395)                     } else if (uri.getScheme().equals("file")) {
002e1bf9 (zerginator       2013-04-28 11:33:20 +0200 396)                         String filePath = Uri.decode(uri.toString()).replace(uri.getScheme() + "://", "");
002e1bf9 (zerginator       2013-04-28 11:33:20 +0200 397)                         if (filePath.contains("mnt")) {
002e1bf9 (zerginator       2013-04-28 11:33:20 +0200 398)                            String splitedFilePath[] = filePath.split("/mnt");
002e1bf9 (zerginator       2013-04-28 11:33:20 +0200 399)                            filePath = splitedFilePath[1];
002e1bf9 (zerginator       2013-04-28 11:33:20 +0200 400)                         }
002e1bf9 (zerginator       2013-04-28 11:33:20 +0200 401)                         final File file = new File(filePath);
002e1bf9 (zerginator       2013-04-28 11:33:20 +0200 402)                         local.add(file.getAbsolutePath());
002e1bf9 (zerginator       2013-04-28 11:33:20 +0200 403)                         remote.add(mUploadPath + file.getName());
002e1bf9 (zerginator       2013-04-28 11:33:20 +0200 404)                     }
002e1bf9 (zerginator       2013-04-28 11:33:20 +0200 405)                     else {
002e1bf9 (zerginator       2013-04-28 11:33:20 +0200 406)                         throw new SecurityException();
002e1bf9 (zerginator       2013-04-28 11:33:20 +0200 407)                     }
002e1bf9 (zerginator       2013-04-28 11:33:20 +0200 408)                 }
002e1bf9 (zerginator       2013-04-28 11:33:20 +0200 409)                 else {
002e1bf9 (zerginator       2013-04-28 11:33:20 +0200 410)                     throw new SecurityException();
002e1bf9 (zerginator       2013-04-28 11:33:20 +0200 411)                 }
002e1bf9 (zerginator       2013-04-28 11:33:20 +0200 412)            
002e1bf9 (zerginator       2013-04-28 11:33:20 +0200 413)             Intent intent = new Intent(getApplicationContext(), FileUploader.class);
002e1bf9 (zerginator       2013-04-28 11:33:20 +0200 414)             intent.putExtra(FileUploader.KEY_UPLOAD_TYPE, FileUploader.UPLOAD_MULTIPLE_FILES);
002e1bf9 (zerginator       2013-04-28 11:33:20 +0200 415)             intent.putExtra(FileUploader.KEY_LOCAL_FILE, local.toArray(new String[local.size()]));
002e1bf9 (zerginator       2013-04-28 11:33:20 +0200 416)             intent.putExtra(FileUploader.KEY_REMOTE_FILE, remote.toArray(new String[remote.size()]));
002e1bf9 (zerginator       2013-04-28 11:33:20 +0200 417)             intent.putExtra(FileUploader.KEY_ACCOUNT, mAccount);
002e1bf9 (zerginator       2013-04-28 11:33:20 +0200 418)             startService(intent);
002e1bf9 (zerginator       2013-04-28 11:33:20 +0200 419)             finish();
002e1bf9 (zerginator       2013-04-28 11:33:20 +0200 420)             }
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100 421)             
002e1bf9 (zerginator       2013-04-28 11:33:20 +0200 422)         } catch (SecurityException e) {
002e1bf9 (zerginator       2013-04-28 11:33:20 +0200 423)             String message = String.format(getString(R.string.uploader_error_forbidden_content), getString(R.string.app_name));
002e1bf9 (zerginator       2013-04-28 11:33:20 +0200 424)             Toast.makeText(this, message, Toast.LENGTH_LONG).show();            
002e1bf9 (zerginator       2013-04-28 11:33:20 +0200 425)         }
002e1bf9 (zerginator       2013-04-28 11:33:20 +0200 426)     }
002e1bf9 (zerginator       2013-04-28 11:33:20 +0200 427) 
002e1bf9 (zerginator       2013-04-28 11:33:20 +0200 428) }
