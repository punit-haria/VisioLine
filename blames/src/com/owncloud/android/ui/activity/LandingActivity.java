00000000 (Not Committed Yet 2013-10-18 00:25:12 -0700   1) /* ownCloud Android client application
00000000 (Not Committed Yet 2013-10-18 00:25:12 -0700   2)  *   Copyright (C) 2011 Bartek Przybylski
00000000 (Not Committed Yet 2013-10-18 00:25:12 -0700   3)  *
00000000 (Not Committed Yet 2013-10-18 00:25:12 -0700   4)  *   This program is free software: you can redistribute it and/or modify
00000000 (Not Committed Yet 2013-10-18 00:25:12 -0700   5)  *   it under the terms of the GNU General Public License version 2,
00000000 (Not Committed Yet 2013-10-18 00:25:12 -0700   6)  *   as published by the Free Software Foundation.
00000000 (Not Committed Yet 2013-10-18 00:25:12 -0700   7)  *
00000000 (Not Committed Yet 2013-10-18 00:25:12 -0700   8)  *   This program is distributed in the hope that it will be useful,
00000000 (Not Committed Yet 2013-10-18 00:25:12 -0700   9)  *   but WITHOUT ANY WARRANTY; without even the implied warranty of
00000000 (Not Committed Yet 2013-10-18 00:25:12 -0700  10)  *   MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
00000000 (Not Committed Yet 2013-10-18 00:25:12 -0700  11)  *   GNU General Public License for more details.
00000000 (Not Committed Yet 2013-10-18 00:25:12 -0700  12)  *
00000000 (Not Committed Yet 2013-10-18 00:25:12 -0700  13)  *   You should have received a copy of the GNU General Public License
00000000 (Not Committed Yet 2013-10-18 00:25:12 -0700  14)  *   along with this program.  If not, see <http://www.gnu.org/licenses/>.
00000000 (Not Committed Yet 2013-10-18 00:25:12 -0700  15)  *
00000000 (Not Committed Yet 2013-10-18 00:25:12 -0700  16)  */
00000000 (Not Committed Yet 2013-10-18 00:25:12 -0700  17) package com.owncloud.android.ui.activity;
00000000 (Not Committed Yet 2013-10-18 00:25:12 -0700  18) 
00000000 (Not Committed Yet 2013-10-18 00:25:12 -0700  19) import com.actionbarsherlock.app.SherlockFragmentActivity;
00000000 (Not Committed Yet 2013-10-18 00:25:12 -0700  20) import com.owncloud.android.authentication.AccountAuthenticator;
00000000 (Not Committed Yet 2013-10-18 00:25:12 -0700  21) import com.owncloud.android.ui.adapter.LandingScreenAdapter;
00000000 (Not Committed Yet 2013-10-18 00:25:12 -0700  22) 
00000000 (Not Committed Yet 2013-10-18 00:25:12 -0700  23) import android.accounts.Account;
00000000 (Not Committed Yet 2013-10-18 00:25:12 -0700  24) import android.accounts.AccountManager;
00000000 (Not Committed Yet 2013-10-18 00:25:12 -0700  25) import android.app.AlertDialog;
00000000 (Not Committed Yet 2013-10-18 00:25:12 -0700  26) import android.app.Dialog;
00000000 (Not Committed Yet 2013-10-18 00:25:12 -0700  27) import android.content.DialogInterface;
00000000 (Not Committed Yet 2013-10-18 00:25:12 -0700  28) import android.content.DialogInterface.OnClickListener;
00000000 (Not Committed Yet 2013-10-18 00:25:12 -0700  29) import android.content.Intent;
00000000 (Not Committed Yet 2013-10-18 00:25:12 -0700  30) import android.os.Bundle;
00000000 (Not Committed Yet 2013-10-18 00:25:12 -0700  31) import android.view.View;
00000000 (Not Committed Yet 2013-10-18 00:25:12 -0700  32) import android.widget.AdapterView;
00000000 (Not Committed Yet 2013-10-18 00:25:12 -0700  33) import android.widget.AdapterView.OnItemClickListener;
00000000 (Not Committed Yet 2013-10-18 00:25:12 -0700  34) import android.widget.GridView;
00000000 (Not Committed Yet 2013-10-18 00:25:12 -0700  35) import android.widget.Toast;
00000000 (Not Committed Yet 2013-10-18 00:25:12 -0700  36) import com.owncloud.android.R;
00000000 (Not Committed Yet 2013-10-18 00:25:12 -0700  37) 
00000000 (Not Committed Yet 2013-10-18 00:25:12 -0700  38) /**
00000000 (Not Committed Yet 2013-10-18 00:25:12 -0700  39)  * This activity is used as a landing page when the user first opens this app.
00000000 (Not Committed Yet 2013-10-18 00:25:12 -0700  40)  * 
00000000 (Not Committed Yet 2013-10-18 00:25:12 -0700  41)  * @author Lennart Rosam
00000000 (Not Committed Yet 2013-10-18 00:25:12 -0700  42)  * 
00000000 (Not Committed Yet 2013-10-18 00:25:12 -0700  43)  */
00000000 (Not Committed Yet 2013-10-18 00:25:12 -0700  44) public class LandingActivity extends SherlockFragmentActivity implements
00000000 (Not Committed Yet 2013-10-18 00:25:12 -0700  45)         OnClickListener, OnItemClickListener {
00000000 (Not Committed Yet 2013-10-18 00:25:12 -0700  46) 
00000000 (Not Committed Yet 2013-10-18 00:25:12 -0700  47)     public static final int DIALOG_SETUP_ACCOUNT = 1;
00000000 (Not Committed Yet 2013-10-18 00:25:12 -0700  48) 
00000000 (Not Committed Yet 2013-10-18 00:25:12 -0700  49)     @Override
00000000 (Not Committed Yet 2013-10-18 00:25:12 -0700  50)     protected void onCreate(Bundle savedInstanceState) {
00000000 (Not Committed Yet 2013-10-18 00:25:12 -0700  51)         super.onCreate(savedInstanceState);
00000000 (Not Committed Yet 2013-10-18 00:25:12 -0700  52)         setContentView(R.layout.main);
00000000 (Not Committed Yet 2013-10-18 00:25:12 -0700  53) 
00000000 (Not Committed Yet 2013-10-18 00:25:12 -0700  54)         // Fill the grid view of the landing screen with icons
00000000 (Not Committed Yet 2013-10-18 00:25:12 -0700  55)         GridView landingScreenItems = (GridView) findViewById(R.id.homeScreenGrid);
00000000 (Not Committed Yet 2013-10-18 00:25:12 -0700  56)         landingScreenItems.setAdapter(new LandingScreenAdapter(this));
00000000 (Not Committed Yet 2013-10-18 00:25:12 -0700  57)         landingScreenItems.setOnItemClickListener(this);
00000000 (Not Committed Yet 2013-10-18 00:25:12 -0700  58) 
00000000 (Not Committed Yet 2013-10-18 00:25:12 -0700  59)         // Check, if there are ownCloud accounts
00000000 (Not Committed Yet 2013-10-18 00:25:12 -0700  60)         if (!accountsAreSetup()) {
00000000 (Not Committed Yet 2013-10-18 00:25:12 -0700  61)             showDialog(DIALOG_SETUP_ACCOUNT);
00000000 (Not Committed Yet 2013-10-18 00:25:12 -0700  62)         } else {
00000000 (Not Committed Yet 2013-10-18 00:25:12 -0700  63)             // Start device tracking service
00000000 (Not Committed Yet 2013-10-18 00:25:12 -0700  64)             Intent locationServiceIntent = new Intent();
00000000 (Not Committed Yet 2013-10-18 00:25:12 -0700  65)             locationServiceIntent
00000000 (Not Committed Yet 2013-10-18 00:25:12 -0700  66)                     .setAction("com.owncloud.android.location.LocationLauncher");
00000000 (Not Committed Yet 2013-10-18 00:25:12 -0700  67)             sendBroadcast(locationServiceIntent);
00000000 (Not Committed Yet 2013-10-18 00:25:12 -0700  68)         }
00000000 (Not Committed Yet 2013-10-18 00:25:12 -0700  69) 
00000000 (Not Committed Yet 2013-10-18 00:25:12 -0700  70)     }
00000000 (Not Committed Yet 2013-10-18 00:25:12 -0700  71) 
00000000 (Not Committed Yet 2013-10-18 00:25:12 -0700  72)     @Override
00000000 (Not Committed Yet 2013-10-18 00:25:12 -0700  73)     protected void onRestart() {
00000000 (Not Committed Yet 2013-10-18 00:25:12 -0700  74)         super.onRestart();
00000000 (Not Committed Yet 2013-10-18 00:25:12 -0700  75)         // Check, if there are ownCloud accounts
00000000 (Not Committed Yet 2013-10-18 00:25:12 -0700  76)         if (!accountsAreSetup()) {
00000000 (Not Committed Yet 2013-10-18 00:25:12 -0700  77)             showDialog(DIALOG_SETUP_ACCOUNT);
00000000 (Not Committed Yet 2013-10-18 00:25:12 -0700  78)         }
00000000 (Not Committed Yet 2013-10-18 00:25:12 -0700  79)     }
00000000 (Not Committed Yet 2013-10-18 00:25:12 -0700  80) 
00000000 (Not Committed Yet 2013-10-18 00:25:12 -0700  81)     @Override
00000000 (Not Committed Yet 2013-10-18 00:25:12 -0700  82)     protected void onRestoreInstanceState(Bundle savedInstanceState) {
00000000 (Not Committed Yet 2013-10-18 00:25:12 -0700  83)         super.onRestoreInstanceState(savedInstanceState);
00000000 (Not Committed Yet 2013-10-18 00:25:12 -0700  84)         // Check, if there are ownCloud accounts
00000000 (Not Committed Yet 2013-10-18 00:25:12 -0700  85)         if (!accountsAreSetup()) {
00000000 (Not Committed Yet 2013-10-18 00:25:12 -0700  86)             showDialog(DIALOG_SETUP_ACCOUNT);
00000000 (Not Committed Yet 2013-10-18 00:25:12 -0700  87)         }
00000000 (Not Committed Yet 2013-10-18 00:25:12 -0700  88)     }
00000000 (Not Committed Yet 2013-10-18 00:25:12 -0700  89) 
00000000 (Not Committed Yet 2013-10-18 00:25:12 -0700  90)     @Override
00000000 (Not Committed Yet 2013-10-18 00:25:12 -0700  91)     protected Dialog onCreateDialog(int id) {
00000000 (Not Committed Yet 2013-10-18 00:25:12 -0700  92)         Dialog dialog;
00000000 (Not Committed Yet 2013-10-18 00:25:12 -0700  93)         switch (id) {
00000000 (Not Committed Yet 2013-10-18 00:25:12 -0700  94)         case DIALOG_SETUP_ACCOUNT:
00000000 (Not Committed Yet 2013-10-18 00:25:12 -0700  95)             AlertDialog.Builder builder = new AlertDialog.Builder(this);
00000000 (Not Committed Yet 2013-10-18 00:25:12 -0700  96)             builder.setTitle(R.string.main_tit_accsetup);
00000000 (Not Committed Yet 2013-10-18 00:25:12 -0700  97)             builder.setMessage(R.string.main_wrn_accsetup);
00000000 (Not Committed Yet 2013-10-18 00:25:12 -0700  98)             builder.setCancelable(false);
00000000 (Not Committed Yet 2013-10-18 00:25:12 -0700  99)             builder.setPositiveButton(R.string.common_ok, this);
00000000 (Not Committed Yet 2013-10-18 00:25:12 -0700 100)             builder.setNegativeButton(R.string.common_cancel, this);
00000000 (Not Committed Yet 2013-10-18 00:25:12 -0700 101)             dialog = builder.create();
00000000 (Not Committed Yet 2013-10-18 00:25:12 -0700 102)             break;
00000000 (Not Committed Yet 2013-10-18 00:25:12 -0700 103)         default:
00000000 (Not Committed Yet 2013-10-18 00:25:12 -0700 104)             dialog = null;
00000000 (Not Committed Yet 2013-10-18 00:25:12 -0700 105)         }
00000000 (Not Committed Yet 2013-10-18 00:25:12 -0700 106) 
00000000 (Not Committed Yet 2013-10-18 00:25:12 -0700 107)         return dialog;
00000000 (Not Committed Yet 2013-10-18 00:25:12 -0700 108)     }
00000000 (Not Committed Yet 2013-10-18 00:25:12 -0700 109) 
00000000 (Not Committed Yet 2013-10-18 00:25:12 -0700 110)     public void onClick(DialogInterface dialog, int which) {
00000000 (Not Committed Yet 2013-10-18 00:25:12 -0700 111)         // In any case - we won't need it anymore
00000000 (Not Committed Yet 2013-10-18 00:25:12 -0700 112)         dialog.dismiss();
00000000 (Not Committed Yet 2013-10-18 00:25:12 -0700 113)         switch (which) {
00000000 (Not Committed Yet 2013-10-18 00:25:12 -0700 114)         case DialogInterface.BUTTON_POSITIVE:
00000000 (Not Committed Yet 2013-10-18 00:25:12 -0700 115)             Intent intent = new Intent(android.provider.Settings.ACTION_ADD_ACCOUNT);
00000000 (Not Committed Yet 2013-10-18 00:25:12 -0700 116)             intent.putExtra("authorities",
00000000 (Not Committed Yet 2013-10-18 00:25:12 -0700 117)                     new String[] { AccountAuthenticator.AUTHORITY });
00000000 (Not Committed Yet 2013-10-18 00:25:12 -0700 118)             startActivity(intent);
00000000 (Not Committed Yet 2013-10-18 00:25:12 -0700 119)             break;
00000000 (Not Committed Yet 2013-10-18 00:25:12 -0700 120)         case DialogInterface.BUTTON_NEGATIVE:
00000000 (Not Committed Yet 2013-10-18 00:25:12 -0700 121)             finish();
00000000 (Not Committed Yet 2013-10-18 00:25:12 -0700 122)         }
00000000 (Not Committed Yet 2013-10-18 00:25:12 -0700 123) 
00000000 (Not Committed Yet 2013-10-18 00:25:12 -0700 124)     }
00000000 (Not Committed Yet 2013-10-18 00:25:12 -0700 125) 
00000000 (Not Committed Yet 2013-10-18 00:25:12 -0700 126)     @Override
00000000 (Not Committed Yet 2013-10-18 00:25:12 -0700 127)     /**
00000000 (Not Committed Yet 2013-10-18 00:25:12 -0700 128)      * Start an activity based on the selection
00000000 (Not Committed Yet 2013-10-18 00:25:12 -0700 129)      * the user made
00000000 (Not Committed Yet 2013-10-18 00:25:12 -0700 130)      */
00000000 (Not Committed Yet 2013-10-18 00:25:12 -0700 131)     public void onItemClick(AdapterView<?> parent, View view, int position,
00000000 (Not Committed Yet 2013-10-18 00:25:12 -0700 132)             long id) {
00000000 (Not Committed Yet 2013-10-18 00:25:12 -0700 133)         Intent intent;
00000000 (Not Committed Yet 2013-10-18 00:25:12 -0700 134)         intent = (Intent) parent.getAdapter().getItem(position);
00000000 (Not Committed Yet 2013-10-18 00:25:12 -0700 135)         if (intent != null) {
00000000 (Not Committed Yet 2013-10-18 00:25:12 -0700 136)             startActivity(intent);
00000000 (Not Committed Yet 2013-10-18 00:25:12 -0700 137)         } else {
00000000 (Not Committed Yet 2013-10-18 00:25:12 -0700 138)             // TODO: Implement all of this and make this text go away ;-)
00000000 (Not Committed Yet 2013-10-18 00:25:12 -0700 139)             Toast toast = Toast.makeText(this, "Not yet implemented!",
00000000 (Not Committed Yet 2013-10-18 00:25:12 -0700 140)                     Toast.LENGTH_SHORT);
00000000 (Not Committed Yet 2013-10-18 00:25:12 -0700 141)             toast.show();
00000000 (Not Committed Yet 2013-10-18 00:25:12 -0700 142)         }
00000000 (Not Committed Yet 2013-10-18 00:25:12 -0700 143)     }
00000000 (Not Committed Yet 2013-10-18 00:25:12 -0700 144) 
00000000 (Not Committed Yet 2013-10-18 00:25:12 -0700 145)     /**
00000000 (Not Committed Yet 2013-10-18 00:25:12 -0700 146)      * Checks, whether or not there are any ownCloud accounts setup.
00000000 (Not Committed Yet 2013-10-18 00:25:12 -0700 147)      * 
00000000 (Not Committed Yet 2013-10-18 00:25:12 -0700 148)      * @return true, if there is at least one account.
00000000 (Not Committed Yet 2013-10-18 00:25:12 -0700 149)      */
00000000 (Not Committed Yet 2013-10-18 00:25:12 -0700 150)     private boolean accountsAreSetup() {
00000000 (Not Committed Yet 2013-10-18 00:25:12 -0700 151)         AccountManager accMan = AccountManager.get(this);
00000000 (Not Committed Yet 2013-10-18 00:25:12 -0700 152)         Account[] accounts = accMan
00000000 (Not Committed Yet 2013-10-18 00:25:12 -0700 153)                 .getAccountsByType(AccountAuthenticator.ACCOUNT_TYPE);
00000000 (Not Committed Yet 2013-10-18 00:25:12 -0700 154)         return accounts.length > 0;
00000000 (Not Committed Yet 2013-10-18 00:25:12 -0700 155)     }
00000000 (Not Committed Yet 2013-10-18 00:25:12 -0700 156) 
00000000 (Not Committed Yet 2013-10-18 00:25:12 -0700 157) }
