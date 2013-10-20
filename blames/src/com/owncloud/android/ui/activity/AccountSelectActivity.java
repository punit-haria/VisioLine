92080afe src/com/owncloud/android/ui/activity/AccountSelectActivity.java (Bartek Przybylski 2012-10-28 00:12:15 +0200   1) /* ownCloud Android client application
92080afe src/com/owncloud/android/ui/activity/AccountSelectActivity.java (Bartek Przybylski 2012-10-28 00:12:15 +0200   2)  *   Copyright (C) 2012 Bartek Przybylski
bb257ec7 src/com/owncloud/android/ui/activity/AccountSelectActivity.java (David A. Velasco  2013-02-07 18:45:10 +0100   3)  *   Copyright (C) 2012-2013 ownCloud Inc.
92080afe src/com/owncloud/android/ui/activity/AccountSelectActivity.java (Bartek Przybylski 2012-10-28 00:12:15 +0200   4)  *
92080afe src/com/owncloud/android/ui/activity/AccountSelectActivity.java (Bartek Przybylski 2012-10-28 00:12:15 +0200   5)  *   This program is free software: you can redistribute it and/or modify
bdc0332c src/com/owncloud/android/ui/activity/AccountSelectActivity.java (David A. Velasco  2013-04-17 12:26:13 +0200   6)  *   it under the terms of the GNU General Public License version 2,
bdc0332c src/com/owncloud/android/ui/activity/AccountSelectActivity.java (David A. Velasco  2013-04-17 12:26:13 +0200   7)  *   as published by the Free Software Foundation.
92080afe src/com/owncloud/android/ui/activity/AccountSelectActivity.java (Bartek Przybylski 2012-10-28 00:12:15 +0200   8)  *
92080afe src/com/owncloud/android/ui/activity/AccountSelectActivity.java (Bartek Przybylski 2012-10-28 00:12:15 +0200   9)  *   This program is distributed in the hope that it will be useful,
92080afe src/com/owncloud/android/ui/activity/AccountSelectActivity.java (Bartek Przybylski 2012-10-28 00:12:15 +0200  10)  *   but WITHOUT ANY WARRANTY; without even the implied warranty of
92080afe src/com/owncloud/android/ui/activity/AccountSelectActivity.java (Bartek Przybylski 2012-10-28 00:12:15 +0200  11)  *   MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
92080afe src/com/owncloud/android/ui/activity/AccountSelectActivity.java (Bartek Przybylski 2012-10-28 00:12:15 +0200  12)  *   GNU General Public License for more details.
92080afe src/com/owncloud/android/ui/activity/AccountSelectActivity.java (Bartek Przybylski 2012-10-28 00:12:15 +0200  13)  *
92080afe src/com/owncloud/android/ui/activity/AccountSelectActivity.java (Bartek Przybylski 2012-10-28 00:12:15 +0200  14)  *   You should have received a copy of the GNU General Public License
92080afe src/com/owncloud/android/ui/activity/AccountSelectActivity.java (Bartek Przybylski 2012-10-28 00:12:15 +0200  15)  *   along with this program.  If not, see <http://www.gnu.org/licenses/>.
92080afe src/com/owncloud/android/ui/activity/AccountSelectActivity.java (Bartek Przybylski 2012-10-28 00:12:15 +0200  16)  *
92080afe src/com/owncloud/android/ui/activity/AccountSelectActivity.java (Bartek Przybylski 2012-10-28 00:12:15 +0200  17)  */
92080afe src/com/owncloud/android/ui/activity/AccountSelectActivity.java (Bartek Przybylski 2012-10-28 00:12:15 +0200  18) 
a4ba6170 src/com/owncloud/android/ui/activity/AccountSelectActivity.java (Bartek Przybylski 2012-07-31 17:43:37 +0200  19) package com.owncloud.android.ui.activity;
0aeb4258 src/eu/alefzero/owncloud/ui/activity/AccountSelectActivity.java (Bartek Przybylski 2012-05-13 16:13:13 +0200  20) 
0aeb4258 src/eu/alefzero/owncloud/ui/activity/AccountSelectActivity.java (Bartek Przybylski 2012-05-13 16:13:13 +0200  21) import java.util.HashMap;
0aeb4258 src/eu/alefzero/owncloud/ui/activity/AccountSelectActivity.java (Bartek Przybylski 2012-05-13 16:13:13 +0200  22) import java.util.LinkedList;
0aeb4258 src/eu/alefzero/owncloud/ui/activity/AccountSelectActivity.java (Bartek Przybylski 2012-05-13 16:13:13 +0200  23) import java.util.List;
0aeb4258 src/eu/alefzero/owncloud/ui/activity/AccountSelectActivity.java (Bartek Przybylski 2012-05-13 16:13:13 +0200  24) import java.util.Map;
0aeb4258 src/eu/alefzero/owncloud/ui/activity/AccountSelectActivity.java (Bartek Przybylski 2012-05-13 16:13:13 +0200  25) 
0aeb4258 src/eu/alefzero/owncloud/ui/activity/AccountSelectActivity.java (Bartek Przybylski 2012-05-13 16:13:13 +0200  26) import android.accounts.Account;
0aeb4258 src/eu/alefzero/owncloud/ui/activity/AccountSelectActivity.java (Bartek Przybylski 2012-05-13 16:13:13 +0200  27) import android.accounts.AccountManager;
2c9b84a8 src/eu/alefzero/owncloud/ui/activity/AccountSelectActivity.java (Bartek Przybylski 2012-05-13 22:28:44 +0200  28) import android.accounts.AccountManagerCallback;
2c9b84a8 src/eu/alefzero/owncloud/ui/activity/AccountSelectActivity.java (Bartek Przybylski 2012-05-13 22:28:44 +0200  29) import android.accounts.AccountManagerFuture;
c0645e68 src/eu/alefzero/owncloud/ui/activity/AccountSelectActivity.java (David A. Velasco  2012-07-16 11:34:37 +0200  30) import android.content.ContentResolver;
0aeb4258 src/eu/alefzero/owncloud/ui/activity/AccountSelectActivity.java (Bartek Przybylski 2012-05-13 16:13:13 +0200  31) import android.content.Context;
0aeb4258 src/eu/alefzero/owncloud/ui/activity/AccountSelectActivity.java (Bartek Przybylski 2012-05-13 16:13:13 +0200  32) import android.content.Intent;
0aeb4258 src/eu/alefzero/owncloud/ui/activity/AccountSelectActivity.java (Bartek Przybylski 2012-05-13 16:13:13 +0200  33) import android.os.Bundle;
2c9b84a8 src/eu/alefzero/owncloud/ui/activity/AccountSelectActivity.java (Bartek Przybylski 2012-05-13 22:28:44 +0200  34) import android.os.Handler;
2c9b84a8 src/eu/alefzero/owncloud/ui/activity/AccountSelectActivity.java (Bartek Przybylski 2012-05-13 22:28:44 +0200  35) import android.view.ContextMenu;
0aeb4258 src/eu/alefzero/owncloud/ui/activity/AccountSelectActivity.java (Bartek Przybylski 2012-05-13 16:13:13 +0200  36) import android.view.View;
0aeb4258 src/eu/alefzero/owncloud/ui/activity/AccountSelectActivity.java (Bartek Przybylski 2012-05-13 16:13:13 +0200  37) import android.view.ViewGroup;
2c9b84a8 src/eu/alefzero/owncloud/ui/activity/AccountSelectActivity.java (Bartek Przybylski 2012-05-13 22:28:44 +0200  38) import android.view.ContextMenu.ContextMenuInfo;
2c9b84a8 src/eu/alefzero/owncloud/ui/activity/AccountSelectActivity.java (Bartek Przybylski 2012-05-13 22:28:44 +0200  39) import android.widget.AdapterView.AdapterContextMenuInfo;
0aeb4258 src/eu/alefzero/owncloud/ui/activity/AccountSelectActivity.java (Bartek Przybylski 2012-05-13 16:13:13 +0200  40) import android.widget.CheckedTextView;
0aeb4258 src/eu/alefzero/owncloud/ui/activity/AccountSelectActivity.java (Bartek Przybylski 2012-05-13 16:13:13 +0200  41) import android.widget.ListView;
0aeb4258 src/eu/alefzero/owncloud/ui/activity/AccountSelectActivity.java (Bartek Przybylski 2012-05-13 16:13:13 +0200  42) import android.widget.SimpleAdapter;
0aeb4258 src/eu/alefzero/owncloud/ui/activity/AccountSelectActivity.java (Bartek Przybylski 2012-05-13 16:13:13 +0200  43) import android.widget.TextView;
0aeb4258 src/eu/alefzero/owncloud/ui/activity/AccountSelectActivity.java (Bartek Przybylski 2012-05-13 16:13:13 +0200  44) 
0aeb4258 src/eu/alefzero/owncloud/ui/activity/AccountSelectActivity.java (Bartek Przybylski 2012-05-13 16:13:13 +0200  45) import com.actionbarsherlock.app.ActionBar;
0aeb4258 src/eu/alefzero/owncloud/ui/activity/AccountSelectActivity.java (Bartek Przybylski 2012-05-13 16:13:13 +0200  46) import com.actionbarsherlock.app.SherlockListActivity;
0aeb4258 src/eu/alefzero/owncloud/ui/activity/AccountSelectActivity.java (Bartek Przybylski 2012-05-13 16:13:13 +0200  47) import com.actionbarsherlock.view.Menu;
0aeb4258 src/eu/alefzero/owncloud/ui/activity/AccountSelectActivity.java (Bartek Przybylski 2012-05-13 16:13:13 +0200  48) import com.actionbarsherlock.view.MenuInflater;
0aeb4258 src/eu/alefzero/owncloud/ui/activity/AccountSelectActivity.java (Bartek Przybylski 2012-05-13 16:13:13 +0200  49) import com.actionbarsherlock.view.MenuItem;
69d6d821 src/com/owncloud/android/ui/activity/AccountSelectActivity.java (David A. Velasco  2013-03-21 12:21:48 +0100  50) import com.owncloud.android.authentication.AccountAuthenticator;
a102f269 src/com/owncloud/android/ui/activity/AccountSelectActivity.java (David A. Velasco  2013-06-21 14:04:48 +0200  51) import com.owncloud.android.authentication.AuthenticatorActivity;
c4ac05de src/com/owncloud/android/ui/activity/AccountSelectActivity.java (David A. Velasco  2013-06-18 11:34:08 +0200  52) import com.owncloud.android.authentication.AccountUtils;
5a65ff53 src/com/owncloud/android/ui/activity/AccountSelectActivity.java (David A. Velasco  2013-07-26 13:05:01 +0200  53) import com.owncloud.android.ui.activity.FileActivity.AccountCreationCallback;
8e36e7cc src/com/owncloud/android/ui/activity/AccountSelectActivity.java (zerginator        2013-03-12 07:56:27 +0100  54) import com.owncloud.android.Log_OC;
0aeb4258 src/eu/alefzero/owncloud/ui/activity/AccountSelectActivity.java (Bartek Przybylski 2012-05-13 16:13:13 +0200  55) 
a4ba6170 src/com/owncloud/android/ui/activity/AccountSelectActivity.java (Bartek Przybylski 2012-07-31 17:43:37 +0200  56) import com.owncloud.android.R;
0aeb4258 src/eu/alefzero/owncloud/ui/activity/AccountSelectActivity.java (Bartek Przybylski 2012-05-13 16:13:13 +0200  57) 
435b31ba src/eu/alefzero/owncloud/ui/activity/AccountSelectActivity.java (Lennart Rosam     2012-05-16 09:48:34 +0200  58) public class AccountSelectActivity extends SherlockListActivity implements
435b31ba src/eu/alefzero/owncloud/ui/activity/AccountSelectActivity.java (Lennart Rosam     2012-05-16 09:48:34 +0200  59)         AccountManagerCallback<Boolean> {
435b31ba src/eu/alefzero/owncloud/ui/activity/AccountSelectActivity.java (Lennart Rosam     2012-05-16 09:48:34 +0200  60) 
aa14479e src/com/owncloud/android/ui/activity/AccountSelectActivity.java (Bartek Przybylski 2012-09-29 23:17:39 +0200  61)     private static final String  TAG = "AccountSelectActivity";
aa14479e src/com/owncloud/android/ui/activity/AccountSelectActivity.java (Bartek Przybylski 2012-09-29 23:17:39 +0200  62)     
11117ace src/com/owncloud/android/ui/activity/AccountSelectActivity.java (David A. Velasco  2012-08-23 11:28:40 +0200  63)     private static final String PREVIOUS_ACCOUNT_KEY = "ACCOUNT";
11117ace src/com/owncloud/android/ui/activity/AccountSelectActivity.java (David A. Velasco  2012-08-23 11:28:40 +0200  64)     
435b31ba src/eu/alefzero/owncloud/ui/activity/AccountSelectActivity.java (Lennart Rosam     2012-05-16 09:48:34 +0200  65)     private final Handler mHandler = new Handler();
11117ace src/com/owncloud/android/ui/activity/AccountSelectActivity.java (David A. Velasco  2012-08-23 11:28:40 +0200  66)     private Account mPreviousAccount = null;
435b31ba src/eu/alefzero/owncloud/ui/activity/AccountSelectActivity.java (Lennart Rosam     2012-05-16 09:48:34 +0200  67) 
435b31ba src/eu/alefzero/owncloud/ui/activity/AccountSelectActivity.java (Lennart Rosam     2012-05-16 09:48:34 +0200  68)     @Override
435b31ba src/eu/alefzero/owncloud/ui/activity/AccountSelectActivity.java (Lennart Rosam     2012-05-16 09:48:34 +0200  69)     protected void onCreate(Bundle savedInstanceState) {
435b31ba src/eu/alefzero/owncloud/ui/activity/AccountSelectActivity.java (Lennart Rosam     2012-05-16 09:48:34 +0200  70)         super.onCreate(savedInstanceState);
435b31ba src/eu/alefzero/owncloud/ui/activity/AccountSelectActivity.java (Lennart Rosam     2012-05-16 09:48:34 +0200  71) 
11117ace src/com/owncloud/android/ui/activity/AccountSelectActivity.java (David A. Velasco  2012-08-23 11:28:40 +0200  72)         if (savedInstanceState != null) {
11117ace src/com/owncloud/android/ui/activity/AccountSelectActivity.java (David A. Velasco  2012-08-23 11:28:40 +0200  73)             mPreviousAccount = savedInstanceState.getParcelable(PREVIOUS_ACCOUNT_KEY);
11117ace src/com/owncloud/android/ui/activity/AccountSelectActivity.java (David A. Velasco  2012-08-23 11:28:40 +0200  74)         } else {
11117ace src/com/owncloud/android/ui/activity/AccountSelectActivity.java (David A. Velasco  2012-08-23 11:28:40 +0200  75)             mPreviousAccount = AccountUtils.getCurrentOwnCloudAccount(this);
11117ace src/com/owncloud/android/ui/activity/AccountSelectActivity.java (David A. Velasco  2012-08-23 11:28:40 +0200  76)         }
11117ace src/com/owncloud/android/ui/activity/AccountSelectActivity.java (David A. Velasco  2012-08-23 11:28:40 +0200  77)         
435b31ba src/eu/alefzero/owncloud/ui/activity/AccountSelectActivity.java (Lennart Rosam     2012-05-16 09:48:34 +0200  78)         ActionBar action_bar = getSupportActionBar();
435b31ba src/eu/alefzero/owncloud/ui/activity/AccountSelectActivity.java (Lennart Rosam     2012-05-16 09:48:34 +0200  79)         action_bar.setDisplayShowTitleEnabled(true);
435b31ba src/eu/alefzero/owncloud/ui/activity/AccountSelectActivity.java (Lennart Rosam     2012-05-16 09:48:34 +0200  80)         action_bar.setDisplayHomeAsUpEnabled(false);
0aeb4258 src/eu/alefzero/owncloud/ui/activity/AccountSelectActivity.java (Bartek Przybylski 2012-05-13 16:13:13 +0200  81)     }
435b31ba src/eu/alefzero/owncloud/ui/activity/AccountSelectActivity.java (Lennart Rosam     2012-05-16 09:48:34 +0200  82) 
435b31ba src/eu/alefzero/owncloud/ui/activity/AccountSelectActivity.java (Lennart Rosam     2012-05-16 09:48:34 +0200  83)     @Override
435b31ba src/eu/alefzero/owncloud/ui/activity/AccountSelectActivity.java (Lennart Rosam     2012-05-16 09:48:34 +0200  84)     protected void onResume() {
435b31ba src/eu/alefzero/owncloud/ui/activity/AccountSelectActivity.java (Lennart Rosam     2012-05-16 09:48:34 +0200  85)         super.onResume();
435b31ba src/eu/alefzero/owncloud/ui/activity/AccountSelectActivity.java (Lennart Rosam     2012-05-16 09:48:34 +0200  86)         populateAccountList();
2c9b84a8 src/eu/alefzero/owncloud/ui/activity/AccountSelectActivity.java (Bartek Przybylski 2012-05-13 22:28:44 +0200  87)     }
11117ace src/com/owncloud/android/ui/activity/AccountSelectActivity.java (David A. Velasco  2012-08-23 11:28:40 +0200  88)     
11117ace src/com/owncloud/android/ui/activity/AccountSelectActivity.java (David A. Velasco  2012-08-23 11:28:40 +0200  89)     @Override
11117ace src/com/owncloud/android/ui/activity/AccountSelectActivity.java (David A. Velasco  2012-08-23 11:28:40 +0200  90)     protected void onPause() {
11117ace src/com/owncloud/android/ui/activity/AccountSelectActivity.java (David A. Velasco  2012-08-23 11:28:40 +0200  91)         super.onPause();
11117ace src/com/owncloud/android/ui/activity/AccountSelectActivity.java (David A. Velasco  2012-08-23 11:28:40 +0200  92)         if (this.isFinishing()) {
11117ace src/com/owncloud/android/ui/activity/AccountSelectActivity.java (David A. Velasco  2012-08-23 11:28:40 +0200  93)             Account current = AccountUtils.getCurrentOwnCloudAccount(this);
11117ace src/com/owncloud/android/ui/activity/AccountSelectActivity.java (David A. Velasco  2012-08-23 11:28:40 +0200  94)             if ((mPreviousAccount == null && current != null) || 
11117ace src/com/owncloud/android/ui/activity/AccountSelectActivity.java (David A. Velasco  2012-08-23 11:28:40 +0200  95)                 (mPreviousAccount != null && !mPreviousAccount.equals(current))) {
11117ace src/com/owncloud/android/ui/activity/AccountSelectActivity.java (David A. Velasco  2012-08-23 11:28:40 +0200  96)                 /// the account set as default changed since this activity was created 
11117ace src/com/owncloud/android/ui/activity/AccountSelectActivity.java (David A. Velasco  2012-08-23 11:28:40 +0200  97)             
11117ace src/com/owncloud/android/ui/activity/AccountSelectActivity.java (David A. Velasco  2012-08-23 11:28:40 +0200  98)                 // trigger synchronization
088b1e83 src/com/owncloud/android/ui/activity/AccountSelectActivity.java (David A. Velasco  2013-01-24 10:29:11 +0100  99)                 ContentResolver.cancelSync(null, AccountAuthenticator.AUTHORITY);
11117ace src/com/owncloud/android/ui/activity/AccountSelectActivity.java (David A. Velasco  2012-08-23 11:28:40 +0200 100)                 Bundle bundle = new Bundle();
11117ace src/com/owncloud/android/ui/activity/AccountSelectActivity.java (David A. Velasco  2012-08-23 11:28:40 +0200 101)                 bundle.putBoolean(ContentResolver.SYNC_EXTRAS_MANUAL, true);
088b1e83 src/com/owncloud/android/ui/activity/AccountSelectActivity.java (David A. Velasco  2013-01-24 10:29:11 +0100 102)                 ContentResolver.requestSync(AccountUtils.getCurrentOwnCloudAccount(this), AccountAuthenticator.AUTHORITY, bundle);
11117ace src/com/owncloud/android/ui/activity/AccountSelectActivity.java (David A. Velasco  2012-08-23 11:28:40 +0200 103)                 
11117ace src/com/owncloud/android/ui/activity/AccountSelectActivity.java (David A. Velasco  2012-08-23 11:28:40 +0200 104)                 // restart the main activity
11117ace src/com/owncloud/android/ui/activity/AccountSelectActivity.java (David A. Velasco  2012-08-23 11:28:40 +0200 105)                 Intent i = new Intent(this, FileDisplayActivity.class);
11117ace src/com/owncloud/android/ui/activity/AccountSelectActivity.java (David A. Velasco  2012-08-23 11:28:40 +0200 106)                 i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
11117ace src/com/owncloud/android/ui/activity/AccountSelectActivity.java (David A. Velasco  2012-08-23 11:28:40 +0200 107)                 startActivity(i);
11117ace src/com/owncloud/android/ui/activity/AccountSelectActivity.java (David A. Velasco  2012-08-23 11:28:40 +0200 108)             }
11117ace src/com/owncloud/android/ui/activity/AccountSelectActivity.java (David A. Velasco  2012-08-23 11:28:40 +0200 109)         }
11117ace src/com/owncloud/android/ui/activity/AccountSelectActivity.java (David A. Velasco  2012-08-23 11:28:40 +0200 110)     }
435b31ba src/eu/alefzero/owncloud/ui/activity/AccountSelectActivity.java (Lennart Rosam     2012-05-16 09:48:34 +0200 111) 
435b31ba src/eu/alefzero/owncloud/ui/activity/AccountSelectActivity.java (Lennart Rosam     2012-05-16 09:48:34 +0200 112)     @Override
435b31ba src/eu/alefzero/owncloud/ui/activity/AccountSelectActivity.java (Lennart Rosam     2012-05-16 09:48:34 +0200 113)     public boolean onCreateOptionsMenu(Menu menu) {
435b31ba src/eu/alefzero/owncloud/ui/activity/AccountSelectActivity.java (Lennart Rosam     2012-05-16 09:48:34 +0200 114)         MenuInflater inflater = getSherlock().getMenuInflater();
a4ba6170 src/com/owncloud/android/ui/activity/AccountSelectActivity.java (Bartek Przybylski 2012-07-31 17:43:37 +0200 115)         inflater.inflate(R.menu.account_picker, menu);
435b31ba src/eu/alefzero/owncloud/ui/activity/AccountSelectActivity.java (Lennart Rosam     2012-05-16 09:48:34 +0200 116)         return true;
2c9b84a8 src/eu/alefzero/owncloud/ui/activity/AccountSelectActivity.java (Bartek Przybylski 2012-05-13 22:28:44 +0200 117)     }
435b31ba src/eu/alefzero/owncloud/ui/activity/AccountSelectActivity.java (Lennart Rosam     2012-05-16 09:48:34 +0200 118) 
435b31ba src/eu/alefzero/owncloud/ui/activity/AccountSelectActivity.java (Lennart Rosam     2012-05-16 09:48:34 +0200 119)     @Override
435b31ba src/eu/alefzero/owncloud/ui/activity/AccountSelectActivity.java (Lennart Rosam     2012-05-16 09:48:34 +0200 120)     public void onCreateContextMenu(ContextMenu menu, View v,
435b31ba src/eu/alefzero/owncloud/ui/activity/AccountSelectActivity.java (Lennart Rosam     2012-05-16 09:48:34 +0200 121)             ContextMenuInfo menuInfo) {
435b31ba src/eu/alefzero/owncloud/ui/activity/AccountSelectActivity.java (Lennart Rosam     2012-05-16 09:48:34 +0200 122)         getMenuInflater().inflate(R.menu.account_picker_long_click, menu);
435b31ba src/eu/alefzero/owncloud/ui/activity/AccountSelectActivity.java (Lennart Rosam     2012-05-16 09:48:34 +0200 123)         super.onCreateContextMenu(menu, v, menuInfo);
435b31ba src/eu/alefzero/owncloud/ui/activity/AccountSelectActivity.java (Lennart Rosam     2012-05-16 09:48:34 +0200 124)     }
435b31ba src/eu/alefzero/owncloud/ui/activity/AccountSelectActivity.java (Lennart Rosam     2012-05-16 09:48:34 +0200 125) 
435b31ba src/eu/alefzero/owncloud/ui/activity/AccountSelectActivity.java (Lennart Rosam     2012-05-16 09:48:34 +0200 126)     @Override
435b31ba src/eu/alefzero/owncloud/ui/activity/AccountSelectActivity.java (Lennart Rosam     2012-05-16 09:48:34 +0200 127)     protected void onListItemClick(ListView l, View v, int position, long id) {
435b31ba src/eu/alefzero/owncloud/ui/activity/AccountSelectActivity.java (Lennart Rosam     2012-05-16 09:48:34 +0200 128)         String accountName = ((TextView) v.findViewById(android.R.id.text1))
435b31ba src/eu/alefzero/owncloud/ui/activity/AccountSelectActivity.java (Lennart Rosam     2012-05-16 09:48:34 +0200 129)                 .getText().toString();
32b09ab7 src/eu/alefzero/owncloud/ui/activity/AccountSelectActivity.java (Bartek Przybylski 2012-05-13 22:41:51 +0200 130)         AccountUtils.setCurrentOwnCloudAccount(this, accountName);
11117ace src/com/owncloud/android/ui/activity/AccountSelectActivity.java (David A. Velasco  2012-08-23 11:28:40 +0200 131)         finish();   // immediate exit
435b31ba src/eu/alefzero/owncloud/ui/activity/AccountSelectActivity.java (Lennart Rosam     2012-05-16 09:48:34 +0200 132)     }
435b31ba src/eu/alefzero/owncloud/ui/activity/AccountSelectActivity.java (Lennart Rosam     2012-05-16 09:48:34 +0200 133) 
435b31ba src/eu/alefzero/owncloud/ui/activity/AccountSelectActivity.java (Lennart Rosam     2012-05-16 09:48:34 +0200 134)     @Override
435b31ba src/eu/alefzero/owncloud/ui/activity/AccountSelectActivity.java (Lennart Rosam     2012-05-16 09:48:34 +0200 135)     public boolean onMenuItemSelected(int featureId, MenuItem item) {
435b31ba src/eu/alefzero/owncloud/ui/activity/AccountSelectActivity.java (Lennart Rosam     2012-05-16 09:48:34 +0200 136)         if (item.getItemId() == R.id.createAccount) {
5a65ff53 src/com/owncloud/android/ui/activity/AccountSelectActivity.java (David A. Velasco  2013-07-26 13:05:01 +0200 137)             /*Intent intent = new Intent(
435b31ba src/eu/alefzero/owncloud/ui/activity/AccountSelectActivity.java (Lennart Rosam     2012-05-16 09:48:34 +0200 138)                     android.provider.Settings.ACTION_ADD_ACCOUNT);
435b31ba src/eu/alefzero/owncloud/ui/activity/AccountSelectActivity.java (Lennart Rosam     2012-05-16 09:48:34 +0200 139)             intent.putExtra("authorities",
088b1e83 src/com/owncloud/android/ui/activity/AccountSelectActivity.java (David A. Velasco  2013-01-24 10:29:11 +0100 140)                     new String[] { AccountAuthenticator.AUTHORITY });
5a65ff53 src/com/owncloud/android/ui/activity/AccountSelectActivity.java (David A. Velasco  2013-07-26 13:05:01 +0200 141)             startActivity(intent);*/
5a65ff53 src/com/owncloud/android/ui/activity/AccountSelectActivity.java (David A. Velasco  2013-07-26 13:05:01 +0200 142)             AccountManager am = AccountManager.get(getApplicationContext());
5a65ff53 src/com/owncloud/android/ui/activity/AccountSelectActivity.java (David A. Velasco  2013-07-26 13:05:01 +0200 143)             am.addAccount(AccountAuthenticator.ACCOUNT_TYPE, 
5a65ff53 src/com/owncloud/android/ui/activity/AccountSelectActivity.java (David A. Velasco  2013-07-26 13:05:01 +0200 144)                             null,
5a65ff53 src/com/owncloud/android/ui/activity/AccountSelectActivity.java (David A. Velasco  2013-07-26 13:05:01 +0200 145)                             null, 
5a65ff53 src/com/owncloud/android/ui/activity/AccountSelectActivity.java (David A. Velasco  2013-07-26 13:05:01 +0200 146)                             null, 
5a65ff53 src/com/owncloud/android/ui/activity/AccountSelectActivity.java (David A. Velasco  2013-07-26 13:05:01 +0200 147)                             this, 
5a65ff53 src/com/owncloud/android/ui/activity/AccountSelectActivity.java (David A. Velasco  2013-07-26 13:05:01 +0200 148)                             null,                        
5a65ff53 src/com/owncloud/android/ui/activity/AccountSelectActivity.java (David A. Velasco  2013-07-26 13:05:01 +0200 149)                             null);
5a65ff53 src/com/owncloud/android/ui/activity/AccountSelectActivity.java (David A. Velasco  2013-07-26 13:05:01 +0200 150)             
435b31ba src/eu/alefzero/owncloud/ui/activity/AccountSelectActivity.java (Lennart Rosam     2012-05-16 09:48:34 +0200 151)             return true;
435b31ba src/eu/alefzero/owncloud/ui/activity/AccountSelectActivity.java (Lennart Rosam     2012-05-16 09:48:34 +0200 152)         }
435b31ba src/eu/alefzero/owncloud/ui/activity/AccountSelectActivity.java (Lennart Rosam     2012-05-16 09:48:34 +0200 153)         return false;
435b31ba src/eu/alefzero/owncloud/ui/activity/AccountSelectActivity.java (Lennart Rosam     2012-05-16 09:48:34 +0200 154)     }
435b31ba src/eu/alefzero/owncloud/ui/activity/AccountSelectActivity.java (Lennart Rosam     2012-05-16 09:48:34 +0200 155) 
a102f269 src/com/owncloud/android/ui/activity/AccountSelectActivity.java (David A. Velasco  2013-06-21 14:04:48 +0200 156)     /**
a102f269 src/com/owncloud/android/ui/activity/AccountSelectActivity.java (David A. Velasco  2013-06-21 14:04:48 +0200 157)      * Called when the user clicked on an item into the context menu created at 
a102f269 src/com/owncloud/android/ui/activity/AccountSelectActivity.java (David A. Velasco  2013-06-21 14:04:48 +0200 158)      * {@link #onCreateContextMenu(ContextMenu, View, ContextMenuInfo)} for every
a102f269 src/com/owncloud/android/ui/activity/AccountSelectActivity.java (David A. Velasco  2013-06-21 14:04:48 +0200 159)      * ownCloud {@link Account} , containing 'secondary actions' for them.
a102f269 src/com/owncloud/android/ui/activity/AccountSelectActivity.java (David A. Velasco  2013-06-21 14:04:48 +0200 160)      * 
a102f269 src/com/owncloud/android/ui/activity/AccountSelectActivity.java (David A. Velasco  2013-06-21 14:04:48 +0200 161)      * {@inheritDoc}}
a102f269 src/com/owncloud/android/ui/activity/AccountSelectActivity.java (David A. Velasco  2013-06-21 14:04:48 +0200 162)      */
aa14479e src/com/owncloud/android/ui/activity/AccountSelectActivity.java (Bartek Przybylski 2012-09-29 23:17:39 +0200 163)     @SuppressWarnings("unchecked")
435b31ba src/eu/alefzero/owncloud/ui/activity/AccountSelectActivity.java (Lennart Rosam     2012-05-16 09:48:34 +0200 164)     @Override
435b31ba src/eu/alefzero/owncloud/ui/activity/AccountSelectActivity.java (Lennart Rosam     2012-05-16 09:48:34 +0200 165)     public boolean onContextItemSelected(android.view.MenuItem item) {
a102f269 src/com/owncloud/android/ui/activity/AccountSelectActivity.java (David A. Velasco  2013-06-21 14:04:48 +0200 166)         AdapterContextMenuInfo info = (AdapterContextMenuInfo) item.getMenuInfo();
435b31ba src/eu/alefzero/owncloud/ui/activity/AccountSelectActivity.java (Lennart Rosam     2012-05-16 09:48:34 +0200 167)         int index = info.position;
aa14479e src/com/owncloud/android/ui/activity/AccountSelectActivity.java (Bartek Przybylski 2012-09-29 23:17:39 +0200 168)         HashMap<String, String> map = null;
aa14479e src/com/owncloud/android/ui/activity/AccountSelectActivity.java (Bartek Przybylski 2012-09-29 23:17:39 +0200 169)         try {
aa14479e src/com/owncloud/android/ui/activity/AccountSelectActivity.java (Bartek Przybylski 2012-09-29 23:17:39 +0200 170)             map = (HashMap<String, String>) getListAdapter().getItem(index);
aa14479e src/com/owncloud/android/ui/activity/AccountSelectActivity.java (Bartek Przybylski 2012-09-29 23:17:39 +0200 171)         } catch (ClassCastException e) {
8e36e7cc src/com/owncloud/android/ui/activity/AccountSelectActivity.java (zerginator        2013-03-12 07:56:27 +0100 172)             Log_OC.wtf(TAG, "getitem(index) from list adapter did not return hashmap, bailing out");
aa14479e src/com/owncloud/android/ui/activity/AccountSelectActivity.java (Bartek Przybylski 2012-09-29 23:17:39 +0200 173)             return false;
aa14479e src/com/owncloud/android/ui/activity/AccountSelectActivity.java (Bartek Przybylski 2012-09-29 23:17:39 +0200 174)         }
aa14479e src/com/owncloud/android/ui/activity/AccountSelectActivity.java (Bartek Przybylski 2012-09-29 23:17:39 +0200 175)         
435b31ba src/eu/alefzero/owncloud/ui/activity/AccountSelectActivity.java (Lennart Rosam     2012-05-16 09:48:34 +0200 176)         String accountName = map.get("NAME");
435b31ba src/eu/alefzero/owncloud/ui/activity/AccountSelectActivity.java (Lennart Rosam     2012-05-16 09:48:34 +0200 177)         AccountManager am = (AccountManager) getSystemService(ACCOUNT_SERVICE);
a102f269 src/com/owncloud/android/ui/activity/AccountSelectActivity.java (David A. Velasco  2013-06-21 14:04:48 +0200 178)         Account accounts[] = am.getAccountsByType(AccountAuthenticator.ACCOUNT_TYPE);
435b31ba src/eu/alefzero/owncloud/ui/activity/AccountSelectActivity.java (Lennart Rosam     2012-05-16 09:48:34 +0200 179)         for (Account a : accounts) {
435b31ba src/eu/alefzero/owncloud/ui/activity/AccountSelectActivity.java (Lennart Rosam     2012-05-16 09:48:34 +0200 180)             if (a.name.equals(accountName)) {
a102f269 src/com/owncloud/android/ui/activity/AccountSelectActivity.java (David A. Velasco  2013-06-21 14:04:48 +0200 181)                 if (item.getItemId() == R.id.change_password) {
a102f269 src/com/owncloud/android/ui/activity/AccountSelectActivity.java (David A. Velasco  2013-06-21 14:04:48 +0200 182)                     Intent updateAccountCredentials = new Intent(this, AuthenticatorActivity.class);
a102f269 src/com/owncloud/android/ui/activity/AccountSelectActivity.java (David A. Velasco  2013-06-21 14:04:48 +0200 183)                     updateAccountCredentials.putExtra(AuthenticatorActivity.EXTRA_ACCOUNT, a);
a102f269 src/com/owncloud/android/ui/activity/AccountSelectActivity.java (David A. Velasco  2013-06-21 14:04:48 +0200 184)                     updateAccountCredentials.putExtra(AuthenticatorActivity.EXTRA_ACTION, AuthenticatorActivity.ACTION_UPDATE_TOKEN);
a102f269 src/com/owncloud/android/ui/activity/AccountSelectActivity.java (David A. Velasco  2013-06-21 14:04:48 +0200 185)                     startActivity(updateAccountCredentials);
a102f269 src/com/owncloud/android/ui/activity/AccountSelectActivity.java (David A. Velasco  2013-06-21 14:04:48 +0200 186)                     
a102f269 src/com/owncloud/android/ui/activity/AccountSelectActivity.java (David A. Velasco  2013-06-21 14:04:48 +0200 187)                 } else if (item.getItemId() == R.id.delete_account) {
a102f269 src/com/owncloud/android/ui/activity/AccountSelectActivity.java (David A. Velasco  2013-06-21 14:04:48 +0200 188)                     am.removeAccount(a, this, mHandler);
a102f269 src/com/owncloud/android/ui/activity/AccountSelectActivity.java (David A. Velasco  2013-06-21 14:04:48 +0200 189)                 }
435b31ba src/eu/alefzero/owncloud/ui/activity/AccountSelectActivity.java (Lennart Rosam     2012-05-16 09:48:34 +0200 190)             }
435b31ba src/eu/alefzero/owncloud/ui/activity/AccountSelectActivity.java (Lennart Rosam     2012-05-16 09:48:34 +0200 191)         }
435b31ba src/eu/alefzero/owncloud/ui/activity/AccountSelectActivity.java (Lennart Rosam     2012-05-16 09:48:34 +0200 192) 
c38a3b2e src/com/owncloud/android/ui/activity/AccountSelectActivity.java (David A. Velasco  2012-12-18 11:20:03 +0100 193)         return true;
2c9b84a8 src/eu/alefzero/owncloud/ui/activity/AccountSelectActivity.java (Bartek Przybylski 2012-05-13 22:28:44 +0200 194)     }
435b31ba src/eu/alefzero/owncloud/ui/activity/AccountSelectActivity.java (Lennart Rosam     2012-05-16 09:48:34 +0200 195) 
435b31ba src/eu/alefzero/owncloud/ui/activity/AccountSelectActivity.java (Lennart Rosam     2012-05-16 09:48:34 +0200 196)     private void populateAccountList() {
435b31ba src/eu/alefzero/owncloud/ui/activity/AccountSelectActivity.java (Lennart Rosam     2012-05-16 09:48:34 +0200 197)         AccountManager am = (AccountManager) getSystemService(ACCOUNT_SERVICE);
435b31ba src/eu/alefzero/owncloud/ui/activity/AccountSelectActivity.java (Lennart Rosam     2012-05-16 09:48:34 +0200 198)         Account accounts[] = am
435b31ba src/eu/alefzero/owncloud/ui/activity/AccountSelectActivity.java (Lennart Rosam     2012-05-16 09:48:34 +0200 199)                 .getAccountsByType(AccountAuthenticator.ACCOUNT_TYPE);
435b31ba src/eu/alefzero/owncloud/ui/activity/AccountSelectActivity.java (Lennart Rosam     2012-05-16 09:48:34 +0200 200)         LinkedList<HashMap<String, String>> ll = new LinkedList<HashMap<String, String>>();
435b31ba src/eu/alefzero/owncloud/ui/activity/AccountSelectActivity.java (Lennart Rosam     2012-05-16 09:48:34 +0200 201)         for (Account a : accounts) {
435b31ba src/eu/alefzero/owncloud/ui/activity/AccountSelectActivity.java (Lennart Rosam     2012-05-16 09:48:34 +0200 202)             HashMap<String, String> h = new HashMap<String, String>();
435b31ba src/eu/alefzero/owncloud/ui/activity/AccountSelectActivity.java (Lennart Rosam     2012-05-16 09:48:34 +0200 203)             h.put("NAME", a.name);
435b31ba src/eu/alefzero/owncloud/ui/activity/AccountSelectActivity.java (Lennart Rosam     2012-05-16 09:48:34 +0200 204)             h.put("VER",
435b31ba src/eu/alefzero/owncloud/ui/activity/AccountSelectActivity.java (Lennart Rosam     2012-05-16 09:48:34 +0200 205)                     "ownCloud version: "
435b31ba src/eu/alefzero/owncloud/ui/activity/AccountSelectActivity.java (Lennart Rosam     2012-05-16 09:48:34 +0200 206)                             + am.getUserData(a,
435b31ba src/eu/alefzero/owncloud/ui/activity/AccountSelectActivity.java (Lennart Rosam     2012-05-16 09:48:34 +0200 207)                                     AccountAuthenticator.KEY_OC_VERSION));
435b31ba src/eu/alefzero/owncloud/ui/activity/AccountSelectActivity.java (Lennart Rosam     2012-05-16 09:48:34 +0200 208)             ll.add(h);
435b31ba src/eu/alefzero/owncloud/ui/activity/AccountSelectActivity.java (Lennart Rosam     2012-05-16 09:48:34 +0200 209)         }
435b31ba src/eu/alefzero/owncloud/ui/activity/AccountSelectActivity.java (Lennart Rosam     2012-05-16 09:48:34 +0200 210) 
435b31ba src/eu/alefzero/owncloud/ui/activity/AccountSelectActivity.java (Lennart Rosam     2012-05-16 09:48:34 +0200 211)         setListAdapter(new AccountCheckedSimpleAdepter(this, ll,
435b31ba src/eu/alefzero/owncloud/ui/activity/AccountSelectActivity.java (Lennart Rosam     2012-05-16 09:48:34 +0200 212)                 android.R.layout.simple_list_item_single_choice,
435b31ba src/eu/alefzero/owncloud/ui/activity/AccountSelectActivity.java (Lennart Rosam     2012-05-16 09:48:34 +0200 213)                 new String[] { "NAME" }, new int[] { android.R.id.text1 }));
435b31ba src/eu/alefzero/owncloud/ui/activity/AccountSelectActivity.java (Lennart Rosam     2012-05-16 09:48:34 +0200 214)         registerForContextMenu(getListView());
0aeb4258 src/eu/alefzero/owncloud/ui/activity/AccountSelectActivity.java (Bartek Przybylski 2012-05-13 16:13:13 +0200 215)     }
435b31ba src/eu/alefzero/owncloud/ui/activity/AccountSelectActivity.java (Lennart Rosam     2012-05-16 09:48:34 +0200 216) 
435b31ba src/eu/alefzero/owncloud/ui/activity/AccountSelectActivity.java (Lennart Rosam     2012-05-16 09:48:34 +0200 217)     @Override
435b31ba src/eu/alefzero/owncloud/ui/activity/AccountSelectActivity.java (Lennart Rosam     2012-05-16 09:48:34 +0200 218)     public void run(AccountManagerFuture<Boolean> future) {
435b31ba src/eu/alefzero/owncloud/ui/activity/AccountSelectActivity.java (Lennart Rosam     2012-05-16 09:48:34 +0200 219)         if (future.isDone()) {
435b31ba src/eu/alefzero/owncloud/ui/activity/AccountSelectActivity.java (Lennart Rosam     2012-05-16 09:48:34 +0200 220)             Account a = AccountUtils.getCurrentOwnCloudAccount(this);
435b31ba src/eu/alefzero/owncloud/ui/activity/AccountSelectActivity.java (Lennart Rosam     2012-05-16 09:48:34 +0200 221)             String accountName = "";
435b31ba src/eu/alefzero/owncloud/ui/activity/AccountSelectActivity.java (Lennart Rosam     2012-05-16 09:48:34 +0200 222)             if (a == null) {
435b31ba src/eu/alefzero/owncloud/ui/activity/AccountSelectActivity.java (Lennart Rosam     2012-05-16 09:48:34 +0200 223)                 Account[] accounts = AccountManager.get(this)
435b31ba src/eu/alefzero/owncloud/ui/activity/AccountSelectActivity.java (Lennart Rosam     2012-05-16 09:48:34 +0200 224)                         .getAccountsByType(AccountAuthenticator.ACCOUNT_TYPE);
435b31ba src/eu/alefzero/owncloud/ui/activity/AccountSelectActivity.java (Lennart Rosam     2012-05-16 09:48:34 +0200 225)                 if (accounts.length != 0)
435b31ba src/eu/alefzero/owncloud/ui/activity/AccountSelectActivity.java (Lennart Rosam     2012-05-16 09:48:34 +0200 226)                     accountName = accounts[0].name;
435b31ba src/eu/alefzero/owncloud/ui/activity/AccountSelectActivity.java (Lennart Rosam     2012-05-16 09:48:34 +0200 227)                 AccountUtils.setCurrentOwnCloudAccount(this, accountName);
435b31ba src/eu/alefzero/owncloud/ui/activity/AccountSelectActivity.java (Lennart Rosam     2012-05-16 09:48:34 +0200 228)             }
435b31ba src/eu/alefzero/owncloud/ui/activity/AccountSelectActivity.java (Lennart Rosam     2012-05-16 09:48:34 +0200 229)             populateAccountList();
435b31ba src/eu/alefzero/owncloud/ui/activity/AccountSelectActivity.java (Lennart Rosam     2012-05-16 09:48:34 +0200 230)         }
0aeb4258 src/eu/alefzero/owncloud/ui/activity/AccountSelectActivity.java (Bartek Przybylski 2012-05-13 16:13:13 +0200 231)     }
2c9b84a8 src/eu/alefzero/owncloud/ui/activity/AccountSelectActivity.java (Bartek Przybylski 2012-05-13 22:28:44 +0200 232) 
435b31ba src/eu/alefzero/owncloud/ui/activity/AccountSelectActivity.java (Lennart Rosam     2012-05-16 09:48:34 +0200 233)     private class AccountCheckedSimpleAdepter extends SimpleAdapter {
435b31ba src/eu/alefzero/owncloud/ui/activity/AccountSelectActivity.java (Lennart Rosam     2012-05-16 09:48:34 +0200 234)         private Account mCurrentAccount;
435b31ba src/eu/alefzero/owncloud/ui/activity/AccountSelectActivity.java (Lennart Rosam     2012-05-16 09:48:34 +0200 235)         private List<? extends Map<String, ?>> mPrivateData;
0aeb4258 src/eu/alefzero/owncloud/ui/activity/AccountSelectActivity.java (Bartek Przybylski 2012-05-13 16:13:13 +0200 236) 
435b31ba src/eu/alefzero/owncloud/ui/activity/AccountSelectActivity.java (Lennart Rosam     2012-05-16 09:48:34 +0200 237)         public AccountCheckedSimpleAdepter(Context context,
435b31ba src/eu/alefzero/owncloud/ui/activity/AccountSelectActivity.java (Lennart Rosam     2012-05-16 09:48:34 +0200 238)                 List<? extends Map<String, ?>> data, int resource,
435b31ba src/eu/alefzero/owncloud/ui/activity/AccountSelectActivity.java (Lennart Rosam     2012-05-16 09:48:34 +0200 239)                 String[] from, int[] to) {
435b31ba src/eu/alefzero/owncloud/ui/activity/AccountSelectActivity.java (Lennart Rosam     2012-05-16 09:48:34 +0200 240)             super(context, data, resource, from, to);
435b31ba src/eu/alefzero/owncloud/ui/activity/AccountSelectActivity.java (Lennart Rosam     2012-05-16 09:48:34 +0200 241)             mCurrentAccount = AccountUtils
435b31ba src/eu/alefzero/owncloud/ui/activity/AccountSelectActivity.java (Lennart Rosam     2012-05-16 09:48:34 +0200 242)                     .getCurrentOwnCloudAccount(AccountSelectActivity.this);
435b31ba src/eu/alefzero/owncloud/ui/activity/AccountSelectActivity.java (Lennart Rosam     2012-05-16 09:48:34 +0200 243)             mPrivateData = data;
435b31ba src/eu/alefzero/owncloud/ui/activity/AccountSelectActivity.java (Lennart Rosam     2012-05-16 09:48:34 +0200 244)         }
435b31ba src/eu/alefzero/owncloud/ui/activity/AccountSelectActivity.java (Lennart Rosam     2012-05-16 09:48:34 +0200 245) 
435b31ba src/eu/alefzero/owncloud/ui/activity/AccountSelectActivity.java (Lennart Rosam     2012-05-16 09:48:34 +0200 246)         @Override
435b31ba src/eu/alefzero/owncloud/ui/activity/AccountSelectActivity.java (Lennart Rosam     2012-05-16 09:48:34 +0200 247)         public View getView(int position, View convertView, ViewGroup parent) {
435b31ba src/eu/alefzero/owncloud/ui/activity/AccountSelectActivity.java (Lennart Rosam     2012-05-16 09:48:34 +0200 248)             View v = super.getView(position, convertView, parent);
435b31ba src/eu/alefzero/owncloud/ui/activity/AccountSelectActivity.java (Lennart Rosam     2012-05-16 09:48:34 +0200 249)             CheckedTextView ctv = (CheckedTextView) v
435b31ba src/eu/alefzero/owncloud/ui/activity/AccountSelectActivity.java (Lennart Rosam     2012-05-16 09:48:34 +0200 250)                     .findViewById(android.R.id.text1);
435b31ba src/eu/alefzero/owncloud/ui/activity/AccountSelectActivity.java (Lennart Rosam     2012-05-16 09:48:34 +0200 251)             if (mPrivateData.get(position).get("NAME")
435b31ba src/eu/alefzero/owncloud/ui/activity/AccountSelectActivity.java (Lennart Rosam     2012-05-16 09:48:34 +0200 252)                     .equals(mCurrentAccount.name)) {
435b31ba src/eu/alefzero/owncloud/ui/activity/AccountSelectActivity.java (Lennart Rosam     2012-05-16 09:48:34 +0200 253)                 ctv.setChecked(true);
435b31ba src/eu/alefzero/owncloud/ui/activity/AccountSelectActivity.java (Lennart Rosam     2012-05-16 09:48:34 +0200 254)             }
435b31ba src/eu/alefzero/owncloud/ui/activity/AccountSelectActivity.java (Lennart Rosam     2012-05-16 09:48:34 +0200 255)             return v;
435b31ba src/eu/alefzero/owncloud/ui/activity/AccountSelectActivity.java (Lennart Rosam     2012-05-16 09:48:34 +0200 256)         }
435b31ba src/eu/alefzero/owncloud/ui/activity/AccountSelectActivity.java (Lennart Rosam     2012-05-16 09:48:34 +0200 257) 
435b31ba src/eu/alefzero/owncloud/ui/activity/AccountSelectActivity.java (Lennart Rosam     2012-05-16 09:48:34 +0200 258)     }
435b31ba src/eu/alefzero/owncloud/ui/activity/AccountSelectActivity.java (Lennart Rosam     2012-05-16 09:48:34 +0200 259) 
435b31ba src/eu/alefzero/owncloud/ui/activity/AccountSelectActivity.java (Lennart Rosam     2012-05-16 09:48:34 +0200 260) }
