00000000 (Not Committed Yet 2013-10-18 00:25:13 -0700   1) /* ownCloud Android client application
00000000 (Not Committed Yet 2013-10-18 00:25:13 -0700   2)  *   Copyright (C) 2012 Bartek Przybylski
00000000 (Not Committed Yet 2013-10-18 00:25:13 -0700   3)  *   Copyright (C) 2012-2013 ownCloud Inc.
00000000 (Not Committed Yet 2013-10-18 00:25:13 -0700   4)  *
00000000 (Not Committed Yet 2013-10-18 00:25:13 -0700   5)  *   This program is free software: you can redistribute it and/or modify
00000000 (Not Committed Yet 2013-10-18 00:25:13 -0700   6)  *   it under the terms of the GNU General Public License version 2,
00000000 (Not Committed Yet 2013-10-18 00:25:13 -0700   7)  *   as published by the Free Software Foundation.
00000000 (Not Committed Yet 2013-10-18 00:25:13 -0700   8)  *
00000000 (Not Committed Yet 2013-10-18 00:25:13 -0700   9)  *   This program is distributed in the hope that it will be useful,
00000000 (Not Committed Yet 2013-10-18 00:25:13 -0700  10)  *   but WITHOUT ANY WARRANTY; without even the implied warranty of
00000000 (Not Committed Yet 2013-10-18 00:25:13 -0700  11)  *   MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
00000000 (Not Committed Yet 2013-10-18 00:25:13 -0700  12)  *   GNU General Public License for more details.
00000000 (Not Committed Yet 2013-10-18 00:25:13 -0700  13)  *
00000000 (Not Committed Yet 2013-10-18 00:25:13 -0700  14)  *   You should have received a copy of the GNU General Public License
00000000 (Not Committed Yet 2013-10-18 00:25:13 -0700  15)  *   along with this program.  If not, see <http://www.gnu.org/licenses/>.
00000000 (Not Committed Yet 2013-10-18 00:25:13 -0700  16)  *
00000000 (Not Committed Yet 2013-10-18 00:25:13 -0700  17)  */
00000000 (Not Committed Yet 2013-10-18 00:25:13 -0700  18) 
00000000 (Not Committed Yet 2013-10-18 00:25:13 -0700  19) package com.owncloud.android.ui.activity;
00000000 (Not Committed Yet 2013-10-18 00:25:13 -0700  20) 
00000000 (Not Committed Yet 2013-10-18 00:25:13 -0700  21) import android.accounts.AccountAuthenticatorActivity;
00000000 (Not Committed Yet 2013-10-18 00:25:13 -0700  22) import android.app.Activity;
00000000 (Not Committed Yet 2013-10-18 00:25:13 -0700  23) import android.os.Bundle;
00000000 (Not Committed Yet 2013-10-18 00:25:13 -0700  24) import android.view.View;
00000000 (Not Committed Yet 2013-10-18 00:25:13 -0700  25) import android.view.View.OnClickListener;
00000000 (Not Committed Yet 2013-10-18 00:25:13 -0700  26) 
00000000 (Not Committed Yet 2013-10-18 00:25:13 -0700  27) public class PreferencesNewSession extends AccountAuthenticatorActivity
00000000 (Not Committed Yet 2013-10-18 00:25:13 -0700  28)         implements OnClickListener {
00000000 (Not Committed Yet 2013-10-18 00:25:13 -0700  29)     @Override
00000000 (Not Committed Yet 2013-10-18 00:25:13 -0700  30)     public void onCreate(Bundle savedInstanceState) {
00000000 (Not Committed Yet 2013-10-18 00:25:13 -0700  31)         super.onCreate(savedInstanceState);
00000000 (Not Committed Yet 2013-10-18 00:25:13 -0700  32)         // setContentView(R.layout.add_new_session);
00000000 (Not Committed Yet 2013-10-18 00:25:13 -0700  33)         /*
00000000 (Not Committed Yet 2013-10-18 00:25:13 -0700  34)          * EditText et;// = (EditText)
00000000 (Not Committed Yet 2013-10-18 00:25:13 -0700  35)          * findViewById(R.id.newSession_sessionName);
00000000 (Not Committed Yet 2013-10-18 00:25:13 -0700  36)          * 
00000000 (Not Committed Yet 2013-10-18 00:25:13 -0700  37)          * et = (EditText) findViewById(R.id.newSession_URL); if
00000000 (Not Committed Yet 2013-10-18 00:25:13 -0700  38)          * (getIntent().hasExtra("sessionURL")) { try { URI uri = new
00000000 (Not Committed Yet 2013-10-18 00:25:13 -0700  39)          * URI(getIntent().getStringExtra("sessionURL")); String url =
00000000 (Not Committed Yet 2013-10-18 00:25:13 -0700  40)          * uri.getHost(); if (uri.getPort() != -1) { url += ":" +
00000000 (Not Committed Yet 2013-10-18 00:25:13 -0700  41)          * String.valueOf(uri.getPort()); } if (uri.getPath() != null) { url +=
00000000 (Not Committed Yet 2013-10-18 00:25:13 -0700  42)          * uri.getPath(); } else { url += "/"; } et.setText(url); et =
00000000 (Not Committed Yet 2013-10-18 00:25:13 -0700  43)          * (EditText) findViewById(R.id.newSession_username); if
00000000 (Not Committed Yet 2013-10-18 00:25:13 -0700  44)          * (uri.getAuthority() != null) { if (uri.getUserInfo().indexOf(':') !=
00000000 (Not Committed Yet 2013-10-18 00:25:13 -0700  45)          * -1) { et.setText(uri.getUserInfo().substring(0,
00000000 (Not Committed Yet 2013-10-18 00:25:13 -0700  46)          * uri.getUserInfo().indexOf(':'))); et = (EditText)
00000000 (Not Committed Yet 2013-10-18 00:25:13 -0700  47)          * findViewById(R.id.newSession_password);
00000000 (Not Committed Yet 2013-10-18 00:25:13 -0700  48)          * et.setText(uri.getUserInfo().substring
00000000 (Not Committed Yet 2013-10-18 00:25:13 -0700  49)          * (uri.getUserInfo().indexOf(':')+1)); } else {
00000000 (Not Committed Yet 2013-10-18 00:25:13 -0700  50)          * et.setText(uri.getUserInfo()); } }
00000000 (Not Committed Yet 2013-10-18 00:25:13 -0700  51)          * 
00000000 (Not Committed Yet 2013-10-18 00:25:13 -0700  52)          * } catch (URISyntaxException e) { Log.e(TAG, "Incorrect URI syntax " +
00000000 (Not Committed Yet 2013-10-18 00:25:13 -0700  53)          * e.getLocalizedMessage()); } }
00000000 (Not Committed Yet 2013-10-18 00:25:13 -0700  54)          * 
00000000 (Not Committed Yet 2013-10-18 00:25:13 -0700  55)          * mReturnData = new Intent(); setResult(Activity.RESULT_OK,
00000000 (Not Committed Yet 2013-10-18 00:25:13 -0700  56)          * mReturnData); ((Button)
00000000 (Not Committed Yet 2013-10-18 00:25:13 -0700  57)          * findViewById(R.id.button1)).setOnClickListener(this); ((Button)
00000000 (Not Committed Yet 2013-10-18 00:25:13 -0700  58)          * findViewById(R.id.button2)).setOnClickListener(this);
00000000 (Not Committed Yet 2013-10-18 00:25:13 -0700  59)          */
00000000 (Not Committed Yet 2013-10-18 00:25:13 -0700  60)     }
00000000 (Not Committed Yet 2013-10-18 00:25:13 -0700  61) 
00000000 (Not Committed Yet 2013-10-18 00:25:13 -0700  62)     @Override
00000000 (Not Committed Yet 2013-10-18 00:25:13 -0700  63)     protected void onResume() {
00000000 (Not Committed Yet 2013-10-18 00:25:13 -0700  64)         super.onResume();
00000000 (Not Committed Yet 2013-10-18 00:25:13 -0700  65)     }
00000000 (Not Committed Yet 2013-10-18 00:25:13 -0700  66) 
00000000 (Not Committed Yet 2013-10-18 00:25:13 -0700  67)     public void onClick(View v) {
00000000 (Not Committed Yet 2013-10-18 00:25:13 -0700  68)         /*
00000000 (Not Committed Yet 2013-10-18 00:25:13 -0700  69)          * switch (v.getId()) { case R.id.button1: Intent intent = new Intent();
00000000 (Not Committed Yet 2013-10-18 00:25:13 -0700  70)          * if (getIntent().hasExtra("sessionId")) { intent.putExtra("sessionId",
00000000 (Not Committed Yet 2013-10-18 00:25:13 -0700  71)          * getIntent().getIntExtra("sessionId", -1)); } //String sessionName =
00000000 (Not Committed Yet 2013-10-18 00:25:13 -0700  72)          * ((EditText)
00000000 (Not Committed Yet 2013-10-18 00:25:13 -0700  73)          * findViewById(R.id.newSession_sessionName)).getText().toString(); //
00000000 (Not Committed Yet 2013-10-18 00:25:13 -0700  74)          * if (sessionName.trim().equals("") || !isNameValid(sessionName)) { //
00000000 (Not Committed Yet 2013-10-18 00:25:13 -0700  75)          * Toast.makeText(this, R.string.new_session_session_name_error,
00000000 (Not Committed Yet 2013-10-18 00:25:13 -0700  76)          * Toast.LENGTH_LONG).show(); // break; // } URI uri = prepareURI(); if
00000000 (Not Committed Yet 2013-10-18 00:25:13 -0700  77)          * (uri != null) { //intent.putExtra("sessionName", sessionName);
00000000 (Not Committed Yet 2013-10-18 00:25:13 -0700  78)          * intent.putExtra("sessionURL", uri.toString());
00000000 (Not Committed Yet 2013-10-18 00:25:13 -0700  79)          * setResult(Activity.RESULT_OK, intent); AccountManager accMgr =
00000000 (Not Committed Yet 2013-10-18 00:25:13 -0700  80)          * AccountManager.get(this); Account a = new Account("OwnCloud",
00000000 (Not Committed Yet 2013-10-18 00:25:13 -0700  81)          * AccountAuthenticatorService.ACCOUNT_TYPE);
00000000 (Not Committed Yet 2013-10-18 00:25:13 -0700  82)          * accMgr.addAccountExplicitly(a, "asd", null); finish(); } break; case
00000000 (Not Committed Yet 2013-10-18 00:25:13 -0700  83)          * R.id.button2: setResult(Activity.RESULT_CANCELED); finish(); break; }
00000000 (Not Committed Yet 2013-10-18 00:25:13 -0700  84)          */
00000000 (Not Committed Yet 2013-10-18 00:25:13 -0700  85)     }
00000000 (Not Committed Yet 2013-10-18 00:25:13 -0700  86) 
00000000 (Not Committed Yet 2013-10-18 00:25:13 -0700  87)     /*
00000000 (Not Committed Yet 2013-10-18 00:25:13 -0700  88)      * private URI prepareURI() { URI uri = null; String url = ""; try { String
00000000 (Not Committed Yet 2013-10-18 00:25:13 -0700  89)      * username = ((EditText)
00000000 (Not Committed Yet 2013-10-18 00:25:13 -0700  90)      * findViewById(R.id.newSession_username)).getText().toString().trim();
00000000 (Not Committed Yet 2013-10-18 00:25:13 -0700  91)      * String password = ((EditText)
00000000 (Not Committed Yet 2013-10-18 00:25:13 -0700  92)      * findViewById(R.id.newSession_password)).getText().toString().trim();
00000000 (Not Committed Yet 2013-10-18 00:25:13 -0700  93)      * String hostname = ((EditText)
00000000 (Not Committed Yet 2013-10-18 00:25:13 -0700  94)      * findViewById(R.id.newSession_URL)).getText().toString().trim(); String
00000000 (Not Committed Yet 2013-10-18 00:25:13 -0700  95)      * scheme; if (hostname.matches("[A-Za-z]://")) { scheme =
00000000 (Not Committed Yet 2013-10-18 00:25:13 -0700  96)      * hostname.substring(0, hostname.indexOf("://")+3); hostname =
00000000 (Not Committed Yet 2013-10-18 00:25:13 -0700  97)      * hostname.substring(hostname.indexOf("://")+3); } else { scheme =
00000000 (Not Committed Yet 2013-10-18 00:25:13 -0700  98)      * "http://"; } if (!username.equals("")) { if (!password.equals("")) {
00000000 (Not Committed Yet 2013-10-18 00:25:13 -0700  99)      * username += ":" + password + "@"; } else { username += "@"; } } url =
00000000 (Not Committed Yet 2013-10-18 00:25:13 -0700 100)      * scheme + username + hostname; Log.i(TAG, url); uri = new URI(url); }
00000000 (Not Committed Yet 2013-10-18 00:25:13 -0700 101)      * catch (URISyntaxException e) { Log.e(TAG, "Incorrect URI syntax " +
00000000 (Not Committed Yet 2013-10-18 00:25:13 -0700 102)      * e.getLocalizedMessage()); Toast.makeText(this,
00000000 (Not Committed Yet 2013-10-18 00:25:13 -0700 103)      * R.string.new_session_uri_error, Toast.LENGTH_LONG).show(); } return uri;
00000000 (Not Committed Yet 2013-10-18 00:25:13 -0700 104)      * }
00000000 (Not Committed Yet 2013-10-18 00:25:13 -0700 105)      * 
00000000 (Not Committed Yet 2013-10-18 00:25:13 -0700 106)      * private boolean isNameValid(String string) { return
00000000 (Not Committed Yet 2013-10-18 00:25:13 -0700 107)      * string.matches("[A-Za-z0-9 _-]*"); }
00000000 (Not Committed Yet 2013-10-18 00:25:13 -0700 108)      */
00000000 (Not Committed Yet 2013-10-18 00:25:13 -0700 109) 
00000000 (Not Committed Yet 2013-10-18 00:25:13 -0700 110)     @Override
00000000 (Not Committed Yet 2013-10-18 00:25:13 -0700 111)     public void onBackPressed() {
00000000 (Not Committed Yet 2013-10-18 00:25:13 -0700 112)         setResult(Activity.RESULT_CANCELED);
00000000 (Not Committed Yet 2013-10-18 00:25:13 -0700 113)         super.onBackPressed();
00000000 (Not Committed Yet 2013-10-18 00:25:13 -0700 114)     }
00000000 (Not Committed Yet 2013-10-18 00:25:13 -0700 115) 
00000000 (Not Committed Yet 2013-10-18 00:25:13 -0700 116) }
