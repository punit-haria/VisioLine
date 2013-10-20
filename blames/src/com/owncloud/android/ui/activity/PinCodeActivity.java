93de9f91 src/eu/alefzero/owncloud/ui/activity/PinCodeActivity.java (David A. Velasco  2012-07-09 11:11:44 +0200   1) /* ownCloud Android client application
93de9f91 src/eu/alefzero/owncloud/ui/activity/PinCodeActivity.java (David A. Velasco  2012-07-09 11:11:44 +0200   2)  *   Copyright (C) 2011 Bartek Przybylski
93de9f91 src/eu/alefzero/owncloud/ui/activity/PinCodeActivity.java (David A. Velasco  2012-07-09 11:11:44 +0200   3)  *
93de9f91 src/eu/alefzero/owncloud/ui/activity/PinCodeActivity.java (David A. Velasco  2012-07-09 11:11:44 +0200   4)  *   This program is free software: you can redistribute it and/or modify
bdc0332c src/com/owncloud/android/ui/activity/PinCodeActivity.java (David A. Velasco  2013-04-17 12:26:13 +0200   5)  *   it under the terms of the GNU General Public License version 2,
bdc0332c src/com/owncloud/android/ui/activity/PinCodeActivity.java (David A. Velasco  2013-04-17 12:26:13 +0200   6)  *   as published by the Free Software Foundation.
93de9f91 src/eu/alefzero/owncloud/ui/activity/PinCodeActivity.java (David A. Velasco  2012-07-09 11:11:44 +0200   7)  *
93de9f91 src/eu/alefzero/owncloud/ui/activity/PinCodeActivity.java (David A. Velasco  2012-07-09 11:11:44 +0200   8)  *   This program is distributed in the hope that it will be useful,
93de9f91 src/eu/alefzero/owncloud/ui/activity/PinCodeActivity.java (David A. Velasco  2012-07-09 11:11:44 +0200   9)  *   but WITHOUT ANY WARRANTY; without even the implied warranty of
93de9f91 src/eu/alefzero/owncloud/ui/activity/PinCodeActivity.java (David A. Velasco  2012-07-09 11:11:44 +0200  10)  *   MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
93de9f91 src/eu/alefzero/owncloud/ui/activity/PinCodeActivity.java (David A. Velasco  2012-07-09 11:11:44 +0200  11)  *   GNU General Public License for more details.
93de9f91 src/eu/alefzero/owncloud/ui/activity/PinCodeActivity.java (David A. Velasco  2012-07-09 11:11:44 +0200  12)  *
93de9f91 src/eu/alefzero/owncloud/ui/activity/PinCodeActivity.java (David A. Velasco  2012-07-09 11:11:44 +0200  13)  *   You should have received a copy of the GNU General Public License
93de9f91 src/eu/alefzero/owncloud/ui/activity/PinCodeActivity.java (David A. Velasco  2012-07-09 11:11:44 +0200  14)  *   along with this program.  If not, see <http://www.gnu.org/licenses/>.
93de9f91 src/eu/alefzero/owncloud/ui/activity/PinCodeActivity.java (David A. Velasco  2012-07-09 11:11:44 +0200  15)  *
93de9f91 src/eu/alefzero/owncloud/ui/activity/PinCodeActivity.java (David A. Velasco  2012-07-09 11:11:44 +0200  16)  */
a4ba6170 src/com/owncloud/android/ui/activity/PinCodeActivity.java (Bartek Przybylski 2012-07-31 17:43:37 +0200  17) package com.owncloud.android.ui.activity;
93de9f91 src/eu/alefzero/owncloud/ui/activity/PinCodeActivity.java (David A. Velasco  2012-07-09 11:11:44 +0200  18) 
e1846469 src/eu/alefzero/owncloud/ui/activity/PinCodeActivity.java (David A. Velasco  2012-07-16 12:59:25 +0200  19) import java.util.Arrays;
e1846469 src/eu/alefzero/owncloud/ui/activity/PinCodeActivity.java (David A. Velasco  2012-07-16 12:59:25 +0200  20) 
93de9f91 src/eu/alefzero/owncloud/ui/activity/PinCodeActivity.java (David A. Velasco  2012-07-09 11:11:44 +0200  21) import com.actionbarsherlock.app.SherlockFragmentActivity;
93de9f91 src/eu/alefzero/owncloud/ui/activity/PinCodeActivity.java (David A. Velasco  2012-07-09 11:11:44 +0200  22) 
a4ba6170 src/com/owncloud/android/ui/activity/PinCodeActivity.java (Bartek Przybylski 2012-07-31 17:43:37 +0200  23) import com.owncloud.android.R;
0583bc8b src/eu/alefzero/owncloud/ui/activity/PinCodeActivity.java (David A. Velasco  2012-07-17 15:16:27 +0200  24) 
93de9f91 src/eu/alefzero/owncloud/ui/activity/PinCodeActivity.java (David A. Velasco  2012-07-09 11:11:44 +0200  25) import android.app.AlertDialog;
93de9f91 src/eu/alefzero/owncloud/ui/activity/PinCodeActivity.java (David A. Velasco  2012-07-09 11:11:44 +0200  26) import android.content.DialogInterface;
93de9f91 src/eu/alefzero/owncloud/ui/activity/PinCodeActivity.java (David A. Velasco  2012-07-09 11:11:44 +0200  27) import android.content.Intent;
93de9f91 src/eu/alefzero/owncloud/ui/activity/PinCodeActivity.java (David A. Velasco  2012-07-09 11:11:44 +0200  28) import android.content.SharedPreferences;
93de9f91 src/eu/alefzero/owncloud/ui/activity/PinCodeActivity.java (David A. Velasco  2012-07-09 11:11:44 +0200  29) import android.os.Bundle;
93de9f91 src/eu/alefzero/owncloud/ui/activity/PinCodeActivity.java (David A. Velasco  2012-07-09 11:11:44 +0200  30) import android.preference.PreferenceManager;
93de9f91 src/eu/alefzero/owncloud/ui/activity/PinCodeActivity.java (David A. Velasco  2012-07-09 11:11:44 +0200  31) import android.text.Editable;
93de9f91 src/eu/alefzero/owncloud/ui/activity/PinCodeActivity.java (David A. Velasco  2012-07-09 11:11:44 +0200  32) import android.text.TextWatcher;
93de9f91 src/eu/alefzero/owncloud/ui/activity/PinCodeActivity.java (David A. Velasco  2012-07-09 11:11:44 +0200  33) import android.view.KeyEvent;
93de9f91 src/eu/alefzero/owncloud/ui/activity/PinCodeActivity.java (David A. Velasco  2012-07-09 11:11:44 +0200  34) import android.view.View;
93de9f91 src/eu/alefzero/owncloud/ui/activity/PinCodeActivity.java (David A. Velasco  2012-07-09 11:11:44 +0200  35) import android.view.View.OnClickListener;
93de9f91 src/eu/alefzero/owncloud/ui/activity/PinCodeActivity.java (David A. Velasco  2012-07-09 11:11:44 +0200  36) import android.view.View.OnFocusChangeListener;
93de9f91 src/eu/alefzero/owncloud/ui/activity/PinCodeActivity.java (David A. Velasco  2012-07-09 11:11:44 +0200  37) import android.view.View.OnKeyListener;
93de9f91 src/eu/alefzero/owncloud/ui/activity/PinCodeActivity.java (David A. Velasco  2012-07-09 11:11:44 +0200  38) import android.widget.Button;
93de9f91 src/eu/alefzero/owncloud/ui/activity/PinCodeActivity.java (David A. Velasco  2012-07-09 11:11:44 +0200  39) import android.widget.EditText;
93de9f91 src/eu/alefzero/owncloud/ui/activity/PinCodeActivity.java (David A. Velasco  2012-07-09 11:11:44 +0200  40) import android.widget.TextView;
93de9f91 src/eu/alefzero/owncloud/ui/activity/PinCodeActivity.java (David A. Velasco  2012-07-09 11:11:44 +0200  41) 
93de9f91 src/eu/alefzero/owncloud/ui/activity/PinCodeActivity.java (David A. Velasco  2012-07-09 11:11:44 +0200  42) public class PinCodeActivity extends SherlockFragmentActivity {
93de9f91 src/eu/alefzero/owncloud/ui/activity/PinCodeActivity.java (David A. Velasco  2012-07-09 11:11:44 +0200  43) 
93de9f91 src/eu/alefzero/owncloud/ui/activity/PinCodeActivity.java (David A. Velasco  2012-07-09 11:11:44 +0200  44)   
6b765c7a src/com/owncloud/android/ui/activity/PinCodeActivity.java (David A. Velasco  2012-08-01 10:00:43 +0200  45)     public final static String EXTRA_ACTIVITY = "com.owncloud.android.ui.activity.PinCodeActivity.ACTIVITY";
6b765c7a src/com/owncloud/android/ui/activity/PinCodeActivity.java (David A. Velasco  2012-08-01 10:00:43 +0200  46)     public final static String EXTRA_NEW_STATE = "com.owncloud.android.ui.activity.PinCodeActivity.NEW_STATE";
93de9f91 src/eu/alefzero/owncloud/ui/activity/PinCodeActivity.java (David A. Velasco  2012-07-09 11:11:44 +0200  47)     
93de9f91 src/eu/alefzero/owncloud/ui/activity/PinCodeActivity.java (David A. Velasco  2012-07-09 11:11:44 +0200  48)     Button bCancel;
93de9f91 src/eu/alefzero/owncloud/ui/activity/PinCodeActivity.java (David A. Velasco  2012-07-09 11:11:44 +0200  49)     TextView mPinHdr;
d193e003 src/com/owncloud/android/ui/activity/PinCodeActivity.java (David A. Velasco  2012-10-17 11:04:27 +0200  50)     TextView mPinHdrExplanation;
93de9f91 src/eu/alefzero/owncloud/ui/activity/PinCodeActivity.java (David A. Velasco  2012-07-09 11:11:44 +0200  51)     EditText mText1;
93de9f91 src/eu/alefzero/owncloud/ui/activity/PinCodeActivity.java (David A. Velasco  2012-07-09 11:11:44 +0200  52)     EditText mText2;
93de9f91 src/eu/alefzero/owncloud/ui/activity/PinCodeActivity.java (David A. Velasco  2012-07-09 11:11:44 +0200  53)     EditText mText3;
93de9f91 src/eu/alefzero/owncloud/ui/activity/PinCodeActivity.java (David A. Velasco  2012-07-09 11:11:44 +0200  54)     EditText mText4;
93de9f91 src/eu/alefzero/owncloud/ui/activity/PinCodeActivity.java (David A. Velasco  2012-07-09 11:11:44 +0200  55)     
93de9f91 src/eu/alefzero/owncloud/ui/activity/PinCodeActivity.java (David A. Velasco  2012-07-09 11:11:44 +0200  56)     String [] tempText ={"","","",""};
93de9f91 src/eu/alefzero/owncloud/ui/activity/PinCodeActivity.java (David A. Velasco  2012-07-09 11:11:44 +0200  57)     
93de9f91 src/eu/alefzero/owncloud/ui/activity/PinCodeActivity.java (David A. Velasco  2012-07-09 11:11:44 +0200  58)     String activity;
93de9f91 src/eu/alefzero/owncloud/ui/activity/PinCodeActivity.java (David A. Velasco  2012-07-09 11:11:44 +0200  59)     
93de9f91 src/eu/alefzero/owncloud/ui/activity/PinCodeActivity.java (David A. Velasco  2012-07-09 11:11:44 +0200  60)     boolean confirmingPinCode = false;
93de9f91 src/eu/alefzero/owncloud/ui/activity/PinCodeActivity.java (David A. Velasco  2012-07-09 11:11:44 +0200  61)     boolean pinCodeChecked = false;
93de9f91 src/eu/alefzero/owncloud/ui/activity/PinCodeActivity.java (David A. Velasco  2012-07-09 11:11:44 +0200  62)     boolean newPasswordEntered = false;
93de9f91 src/eu/alefzero/owncloud/ui/activity/PinCodeActivity.java (David A. Velasco  2012-07-09 11:11:44 +0200  63)     boolean bChange = true; // to control that only one blocks jump
91b17284 src/eu/alefzero/owncloud/ui/activity/PinCodeActivity.java (David A. Velasco  2012-07-13 14:46:40 +0200  64)     int tCounter ; // Count the number of attempts an user could introduce the PIN code
93de9f91 src/eu/alefzero/owncloud/ui/activity/PinCodeActivity.java (David A. Velasco  2012-07-09 11:11:44 +0200  65) 
93de9f91 src/eu/alefzero/owncloud/ui/activity/PinCodeActivity.java (David A. Velasco  2012-07-09 11:11:44 +0200  66)     
93de9f91 src/eu/alefzero/owncloud/ui/activity/PinCodeActivity.java (David A. Velasco  2012-07-09 11:11:44 +0200  67)     protected void onCreate(Bundle savedInstanceState) {
93de9f91 src/eu/alefzero/owncloud/ui/activity/PinCodeActivity.java (David A. Velasco  2012-07-09 11:11:44 +0200  68)         super.onCreate(savedInstanceState);
93de9f91 src/eu/alefzero/owncloud/ui/activity/PinCodeActivity.java (David A. Velasco  2012-07-09 11:11:44 +0200  69)         setContentView(R.layout.pincodelock); 
93de9f91 src/eu/alefzero/owncloud/ui/activity/PinCodeActivity.java (David A. Velasco  2012-07-09 11:11:44 +0200  70)         
93de9f91 src/eu/alefzero/owncloud/ui/activity/PinCodeActivity.java (David A. Velasco  2012-07-09 11:11:44 +0200  71)         Intent intent = getIntent();
3e4f8b4f src/eu/alefzero/owncloud/ui/activity/PinCodeActivity.java (David A. Velasco  2012-07-09 15:10:26 +0200  72)         activity = intent.getStringExtra(EXTRA_ACTIVITY);
93de9f91 src/eu/alefzero/owncloud/ui/activity/PinCodeActivity.java (David A. Velasco  2012-07-09 11:11:44 +0200  73)      
93de9f91 src/eu/alefzero/owncloud/ui/activity/PinCodeActivity.java (David A. Velasco  2012-07-09 11:11:44 +0200  74)         bCancel = (Button) findViewById(R.id.cancel);
93de9f91 src/eu/alefzero/owncloud/ui/activity/PinCodeActivity.java (David A. Velasco  2012-07-09 11:11:44 +0200  75)         mPinHdr = (TextView) findViewById(R.id.pinHdr);
d193e003 src/com/owncloud/android/ui/activity/PinCodeActivity.java (David A. Velasco  2012-10-17 11:04:27 +0200  76)         mPinHdrExplanation = (TextView) findViewById(R.id.pinHdrExpl);
93de9f91 src/eu/alefzero/owncloud/ui/activity/PinCodeActivity.java (David A. Velasco  2012-07-09 11:11:44 +0200  77)         mText1 = (EditText) findViewById(R.id.txt1);
93de9f91 src/eu/alefzero/owncloud/ui/activity/PinCodeActivity.java (David A. Velasco  2012-07-09 11:11:44 +0200  78)         mText1.requestFocus();
0583bc8b src/eu/alefzero/owncloud/ui/activity/PinCodeActivity.java (David A. Velasco  2012-07-17 15:16:27 +0200  79)         getWindow().setSoftInputMode(android.view.WindowManager.LayoutParams.SOFT_INPUT_STATE_VISIBLE);
93de9f91 src/eu/alefzero/owncloud/ui/activity/PinCodeActivity.java (David A. Velasco  2012-07-09 11:11:44 +0200  80)         mText2 = (EditText) findViewById(R.id.txt2);
93de9f91 src/eu/alefzero/owncloud/ui/activity/PinCodeActivity.java (David A. Velasco  2012-07-09 11:11:44 +0200  81)         mText3 = (EditText) findViewById(R.id.txt3);
93de9f91 src/eu/alefzero/owncloud/ui/activity/PinCodeActivity.java (David A. Velasco  2012-07-09 11:11:44 +0200  82)         mText4 = (EditText) findViewById(R.id.txt4);
93de9f91 src/eu/alefzero/owncloud/ui/activity/PinCodeActivity.java (David A. Velasco  2012-07-09 11:11:44 +0200  83)         
93de9f91 src/eu/alefzero/owncloud/ui/activity/PinCodeActivity.java (David A. Velasco  2012-07-09 11:11:44 +0200  84)         
0583bc8b src/eu/alefzero/owncloud/ui/activity/PinCodeActivity.java (David A. Velasco  2012-07-17 15:16:27 +0200  85)         
93de9f91 src/eu/alefzero/owncloud/ui/activity/PinCodeActivity.java (David A. Velasco  2012-07-09 11:11:44 +0200  86)         SharedPreferences appPrefs = PreferenceManager
93de9f91 src/eu/alefzero/owncloud/ui/activity/PinCodeActivity.java (David A. Velasco  2012-07-09 11:11:44 +0200  87)                 .getDefaultSharedPreferences(getApplicationContext());
93de9f91 src/eu/alefzero/owncloud/ui/activity/PinCodeActivity.java (David A. Velasco  2012-07-09 11:11:44 +0200  88)         
f757b74c src/eu/alefzero/owncloud/ui/activity/PinCodeActivity.java (David A. Velasco  2012-07-11 18:59:59 +0200  89)  
f757b74c src/eu/alefzero/owncloud/ui/activity/PinCodeActivity.java (David A. Velasco  2012-07-11 18:59:59 +0200  90)         // Not PIN Code defined yet.
f757b74c src/eu/alefzero/owncloud/ui/activity/PinCodeActivity.java (David A. Velasco  2012-07-11 18:59:59 +0200  91)         // In a previous version settings is allow from start
f757b74c src/eu/alefzero/owncloud/ui/activity/PinCodeActivity.java (David A. Velasco  2012-07-11 18:59:59 +0200  92)         if ( (appPrefs.getString("PrefPinCode1", null) == null ) ){
c82fc04a src/eu/alefzero/owncloud/ui/activity/PinCodeActivity.java (David A. Velasco  2012-07-23 14:53:46 +0200  93)             setChangePincodeView(true);
93de9f91 src/eu/alefzero/owncloud/ui/activity/PinCodeActivity.java (David A. Velasco  2012-07-09 11:11:44 +0200  94)             pinCodeChecked = true; 
93de9f91 src/eu/alefzero/owncloud/ui/activity/PinCodeActivity.java (David A. Velasco  2012-07-09 11:11:44 +0200  95)             newPasswordEntered = true;
93de9f91 src/eu/alefzero/owncloud/ui/activity/PinCodeActivity.java (David A. Velasco  2012-07-09 11:11:44 +0200  96)             
91b17284 src/eu/alefzero/owncloud/ui/activity/PinCodeActivity.java (David A. Velasco  2012-07-13 14:46:40 +0200  97)         }else{ 
f757b74c src/eu/alefzero/owncloud/ui/activity/PinCodeActivity.java (David A. Velasco  2012-07-11 18:59:59 +0200  98)             
91b17284 src/eu/alefzero/owncloud/ui/activity/PinCodeActivity.java (David A. Velasco  2012-07-13 14:46:40 +0200  99)             if (appPrefs.getBoolean("set_pincode", false)){
91b17284 src/eu/alefzero/owncloud/ui/activity/PinCodeActivity.java (David A. Velasco  2012-07-13 14:46:40 +0200 100)                // pincode activated
91b17284 src/eu/alefzero/owncloud/ui/activity/PinCodeActivity.java (David A. Velasco  2012-07-13 14:46:40 +0200 101)                if (activity.equals("preferences")){
91b17284 src/eu/alefzero/owncloud/ui/activity/PinCodeActivity.java (David A. Velasco  2012-07-13 14:46:40 +0200 102)                 // PIN has been activated yet
91b17284 src/eu/alefzero/owncloud/ui/activity/PinCodeActivity.java (David A. Velasco  2012-07-13 14:46:40 +0200 103)                  mPinHdr.setText(R.string.pincode_configure_your_pin);
d193e003 src/com/owncloud/android/ui/activity/PinCodeActivity.java (David A. Velasco  2012-10-17 11:04:27 +0200 104)                  mPinHdrExplanation.setVisibility(View.VISIBLE);
91b17284 src/eu/alefzero/owncloud/ui/activity/PinCodeActivity.java (David A. Velasco  2012-07-13 14:46:40 +0200 105)                  pinCodeChecked = true ; // No need to check it 
91b17284 src/eu/alefzero/owncloud/ui/activity/PinCodeActivity.java (David A. Velasco  2012-07-13 14:46:40 +0200 106)                  setChangePincodeView(true);
91b17284 src/eu/alefzero/owncloud/ui/activity/PinCodeActivity.java (David A. Velasco  2012-07-13 14:46:40 +0200 107)                }else{
91b17284 src/eu/alefzero/owncloud/ui/activity/PinCodeActivity.java (David A. Velasco  2012-07-13 14:46:40 +0200 108)                 // PIN active
91b17284 src/eu/alefzero/owncloud/ui/activity/PinCodeActivity.java (David A. Velasco  2012-07-13 14:46:40 +0200 109)                  bCancel.setVisibility(View.INVISIBLE);
91b17284 src/eu/alefzero/owncloud/ui/activity/PinCodeActivity.java (David A. Velasco  2012-07-13 14:46:40 +0200 110)                  bCancel.setVisibility(View.GONE);
91b17284 src/eu/alefzero/owncloud/ui/activity/PinCodeActivity.java (David A. Velasco  2012-07-13 14:46:40 +0200 111)                  mPinHdr.setText(R.string.pincode_enter_pin_code);
d193e003 src/com/owncloud/android/ui/activity/PinCodeActivity.java (David A. Velasco  2012-10-17 11:04:27 +0200 112)                  mPinHdrExplanation.setVisibility(View.INVISIBLE);
91b17284 src/eu/alefzero/owncloud/ui/activity/PinCodeActivity.java (David A. Velasco  2012-07-13 14:46:40 +0200 113)                  setChangePincodeView(false);
91b17284 src/eu/alefzero/owncloud/ui/activity/PinCodeActivity.java (David A. Velasco  2012-07-13 14:46:40 +0200 114)               }
91b17284 src/eu/alefzero/owncloud/ui/activity/PinCodeActivity.java (David A. Velasco  2012-07-13 14:46:40 +0200 115)             
91b17284 src/eu/alefzero/owncloud/ui/activity/PinCodeActivity.java (David A. Velasco  2012-07-13 14:46:40 +0200 116)            }else {
91b17284 src/eu/alefzero/owncloud/ui/activity/PinCodeActivity.java (David A. Velasco  2012-07-13 14:46:40 +0200 117)             // pincode removal
0583bc8b src/eu/alefzero/owncloud/ui/activity/PinCodeActivity.java (David A. Velasco  2012-07-17 15:16:27 +0200 118)               mPinHdr.setText(R.string.pincode_remove_your_pincode);
d193e003 src/com/owncloud/android/ui/activity/PinCodeActivity.java (David A. Velasco  2012-10-17 11:04:27 +0200 119)               mPinHdrExplanation.setVisibility(View.INVISIBLE);
91b17284 src/eu/alefzero/owncloud/ui/activity/PinCodeActivity.java (David A. Velasco  2012-07-13 14:46:40 +0200 120)               pinCodeChecked = false;
91b17284 src/eu/alefzero/owncloud/ui/activity/PinCodeActivity.java (David A. Velasco  2012-07-13 14:46:40 +0200 121)               setChangePincodeView(true); 
91b17284 src/eu/alefzero/owncloud/ui/activity/PinCodeActivity.java (David A. Velasco  2012-07-13 14:46:40 +0200 122)            }
93de9f91 src/eu/alefzero/owncloud/ui/activity/PinCodeActivity.java (David A. Velasco  2012-07-09 11:11:44 +0200 123)            
91b17284 src/eu/alefzero/owncloud/ui/activity/PinCodeActivity.java (David A. Velasco  2012-07-13 14:46:40 +0200 124)         }
93de9f91 src/eu/alefzero/owncloud/ui/activity/PinCodeActivity.java (David A. Velasco  2012-07-09 11:11:44 +0200 125)         setTextListeners();
93de9f91 src/eu/alefzero/owncloud/ui/activity/PinCodeActivity.java (David A. Velasco  2012-07-09 11:11:44 +0200 126)         
93de9f91 src/eu/alefzero/owncloud/ui/activity/PinCodeActivity.java (David A. Velasco  2012-07-09 11:11:44 +0200 127)         
93de9f91 src/eu/alefzero/owncloud/ui/activity/PinCodeActivity.java (David A. Velasco  2012-07-09 11:11:44 +0200 128)     }
0583bc8b src/eu/alefzero/owncloud/ui/activity/PinCodeActivity.java (David A. Velasco  2012-07-17 15:16:27 +0200 129)     
0583bc8b src/eu/alefzero/owncloud/ui/activity/PinCodeActivity.java (David A. Velasco  2012-07-17 15:16:27 +0200 130) 
91b17284 src/eu/alefzero/owncloud/ui/activity/PinCodeActivity.java (David A. Velasco  2012-07-13 14:46:40 +0200 131)      
91b17284 src/eu/alefzero/owncloud/ui/activity/PinCodeActivity.java (David A. Velasco  2012-07-13 14:46:40 +0200 132)     protected void setInitVars(){
91b17284 src/eu/alefzero/owncloud/ui/activity/PinCodeActivity.java (David A. Velasco  2012-07-13 14:46:40 +0200 133)         confirmingPinCode = false;
91b17284 src/eu/alefzero/owncloud/ui/activity/PinCodeActivity.java (David A. Velasco  2012-07-13 14:46:40 +0200 134)         pinCodeChecked = false;
91b17284 src/eu/alefzero/owncloud/ui/activity/PinCodeActivity.java (David A. Velasco  2012-07-13 14:46:40 +0200 135)         newPasswordEntered = false;
91b17284 src/eu/alefzero/owncloud/ui/activity/PinCodeActivity.java (David A. Velasco  2012-07-13 14:46:40 +0200 136) 
91b17284 src/eu/alefzero/owncloud/ui/activity/PinCodeActivity.java (David A. Velasco  2012-07-13 14:46:40 +0200 137)     }
93de9f91 src/eu/alefzero/owncloud/ui/activity/PinCodeActivity.java (David A. Velasco  2012-07-09 11:11:44 +0200 138)     
93de9f91 src/eu/alefzero/owncloud/ui/activity/PinCodeActivity.java (David A. Velasco  2012-07-09 11:11:44 +0200 139)     protected void setInitView(){
93de9f91 src/eu/alefzero/owncloud/ui/activity/PinCodeActivity.java (David A. Velasco  2012-07-09 11:11:44 +0200 140)         bCancel.setVisibility(View.INVISIBLE);
93de9f91 src/eu/alefzero/owncloud/ui/activity/PinCodeActivity.java (David A. Velasco  2012-07-09 11:11:44 +0200 141)         bCancel.setVisibility(View.GONE);
de5d8187 src/eu/alefzero/owncloud/ui/activity/PinCodeActivity.java (David A. Velasco  2012-07-09 15:19:44 +0200 142)         mPinHdr.setText(R.string.pincode_enter_pin_code);
d193e003 src/com/owncloud/android/ui/activity/PinCodeActivity.java (David A. Velasco  2012-10-17 11:04:27 +0200 143)         mPinHdrExplanation.setVisibility(View.INVISIBLE);
93de9f91 src/eu/alefzero/owncloud/ui/activity/PinCodeActivity.java (David A. Velasco  2012-07-09 11:11:44 +0200 144)     }
93de9f91 src/eu/alefzero/owncloud/ui/activity/PinCodeActivity.java (David A. Velasco  2012-07-09 11:11:44 +0200 145)     
f757b74c src/eu/alefzero/owncloud/ui/activity/PinCodeActivity.java (David A. Velasco  2012-07-11 18:59:59 +0200 146)    
f757b74c src/eu/alefzero/owncloud/ui/activity/PinCodeActivity.java (David A. Velasco  2012-07-11 18:59:59 +0200 147)     protected void setChangePincodeView(boolean state){
f757b74c src/eu/alefzero/owncloud/ui/activity/PinCodeActivity.java (David A. Velasco  2012-07-11 18:59:59 +0200 148)        
f757b74c src/eu/alefzero/owncloud/ui/activity/PinCodeActivity.java (David A. Velasco  2012-07-11 18:59:59 +0200 149)         if(state){
f757b74c src/eu/alefzero/owncloud/ui/activity/PinCodeActivity.java (David A. Velasco  2012-07-11 18:59:59 +0200 150)         bCancel.setVisibility(View.VISIBLE);
93de9f91 src/eu/alefzero/owncloud/ui/activity/PinCodeActivity.java (David A. Velasco  2012-07-09 11:11:44 +0200 151)         bCancel.setOnClickListener(new OnClickListener() {
f757b74c src/eu/alefzero/owncloud/ui/activity/PinCodeActivity.java (David A. Velasco  2012-07-11 18:59:59 +0200 152)         @Override
f757b74c src/eu/alefzero/owncloud/ui/activity/PinCodeActivity.java (David A. Velasco  2012-07-11 18:59:59 +0200 153)         public void onClick(View v) {
f757b74c src/eu/alefzero/owncloud/ui/activity/PinCodeActivity.java (David A. Velasco  2012-07-11 18:59:59 +0200 154)             
f757b74c src/eu/alefzero/owncloud/ui/activity/PinCodeActivity.java (David A. Velasco  2012-07-11 18:59:59 +0200 155)             SharedPreferences.Editor appPrefsE = PreferenceManager
f757b74c src/eu/alefzero/owncloud/ui/activity/PinCodeActivity.java (David A. Velasco  2012-07-11 18:59:59 +0200 156)                     .getDefaultSharedPreferences(getApplicationContext()).edit();
f757b74c src/eu/alefzero/owncloud/ui/activity/PinCodeActivity.java (David A. Velasco  2012-07-11 18:59:59 +0200 157)             
f757b74c src/eu/alefzero/owncloud/ui/activity/PinCodeActivity.java (David A. Velasco  2012-07-11 18:59:59 +0200 158)             SharedPreferences appPrefs = PreferenceManager
f757b74c src/eu/alefzero/owncloud/ui/activity/PinCodeActivity.java (David A. Velasco  2012-07-11 18:59:59 +0200 159)                     .getDefaultSharedPreferences(getApplicationContext());
f757b74c src/eu/alefzero/owncloud/ui/activity/PinCodeActivity.java (David A. Velasco  2012-07-11 18:59:59 +0200 160)             
f757b74c src/eu/alefzero/owncloud/ui/activity/PinCodeActivity.java (David A. Velasco  2012-07-11 18:59:59 +0200 161)             boolean state = appPrefs.getBoolean("set_pincode", false);
f757b74c src/eu/alefzero/owncloud/ui/activity/PinCodeActivity.java (David A. Velasco  2012-07-11 18:59:59 +0200 162)             appPrefsE.putBoolean("set_pincode",!state); 
f757b74c src/eu/alefzero/owncloud/ui/activity/PinCodeActivity.java (David A. Velasco  2012-07-11 18:59:59 +0200 163)             appPrefsE.commit();
91b17284 src/eu/alefzero/owncloud/ui/activity/PinCodeActivity.java (David A. Velasco  2012-07-13 14:46:40 +0200 164)             setInitVars();
f757b74c src/eu/alefzero/owncloud/ui/activity/PinCodeActivity.java (David A. Velasco  2012-07-11 18:59:59 +0200 165)             finish();
93de9f91 src/eu/alefzero/owncloud/ui/activity/PinCodeActivity.java (David A. Velasco  2012-07-09 11:11:44 +0200 166)             }
93de9f91 src/eu/alefzero/owncloud/ui/activity/PinCodeActivity.java (David A. Velasco  2012-07-09 11:11:44 +0200 167)         });
f757b74c src/eu/alefzero/owncloud/ui/activity/PinCodeActivity.java (David A. Velasco  2012-07-11 18:59:59 +0200 168)         }  
93de9f91 src/eu/alefzero/owncloud/ui/activity/PinCodeActivity.java (David A. Velasco  2012-07-09 11:11:44 +0200 169)     
93de9f91 src/eu/alefzero/owncloud/ui/activity/PinCodeActivity.java (David A. Velasco  2012-07-09 11:11:44 +0200 170)     }
93de9f91 src/eu/alefzero/owncloud/ui/activity/PinCodeActivity.java (David A. Velasco  2012-07-09 11:11:44 +0200 171)     
f757b74c src/eu/alefzero/owncloud/ui/activity/PinCodeActivity.java (David A. Velasco  2012-07-11 18:59:59 +0200 172)     
f757b74c src/eu/alefzero/owncloud/ui/activity/PinCodeActivity.java (David A. Velasco  2012-07-11 18:59:59 +0200 173)     
93de9f91 src/eu/alefzero/owncloud/ui/activity/PinCodeActivity.java (David A. Velasco  2012-07-09 11:11:44 +0200 174)     /*
93de9f91 src/eu/alefzero/owncloud/ui/activity/PinCodeActivity.java (David A. Velasco  2012-07-09 11:11:44 +0200 175)      *  
93de9f91 src/eu/alefzero/owncloud/ui/activity/PinCodeActivity.java (David A. Velasco  2012-07-09 11:11:44 +0200 176)      */
93de9f91 src/eu/alefzero/owncloud/ui/activity/PinCodeActivity.java (David A. Velasco  2012-07-09 11:11:44 +0200 177)     protected void setTextListeners(){
93de9f91 src/eu/alefzero/owncloud/ui/activity/PinCodeActivity.java (David A. Velasco  2012-07-09 11:11:44 +0200 178)     
93de9f91 src/eu/alefzero/owncloud/ui/activity/PinCodeActivity.java (David A. Velasco  2012-07-09 11:11:44 +0200 179)         /*------------------------------------------------
93de9f91 src/eu/alefzero/owncloud/ui/activity/PinCodeActivity.java (David A. Velasco  2012-07-09 11:11:44 +0200 180)          *  FIRST BOX
93de9f91 src/eu/alefzero/owncloud/ui/activity/PinCodeActivity.java (David A. Velasco  2012-07-09 11:11:44 +0200 181)          -------------------------------------------------*/
93de9f91 src/eu/alefzero/owncloud/ui/activity/PinCodeActivity.java (David A. Velasco  2012-07-09 11:11:44 +0200 182)         
93de9f91 src/eu/alefzero/owncloud/ui/activity/PinCodeActivity.java (David A. Velasco  2012-07-09 11:11:44 +0200 183)         mText1.addTextChangedListener(new TextWatcher() {
93de9f91 src/eu/alefzero/owncloud/ui/activity/PinCodeActivity.java (David A. Velasco  2012-07-09 11:11:44 +0200 184) 
93de9f91 src/eu/alefzero/owncloud/ui/activity/PinCodeActivity.java (David A. Velasco  2012-07-09 11:11:44 +0200 185)             @Override
93de9f91 src/eu/alefzero/owncloud/ui/activity/PinCodeActivity.java (David A. Velasco  2012-07-09 11:11:44 +0200 186)             public void onTextChanged(CharSequence s, int start, int before,
93de9f91 src/eu/alefzero/owncloud/ui/activity/PinCodeActivity.java (David A. Velasco  2012-07-09 11:11:44 +0200 187)                     int count) {
93de9f91 src/eu/alefzero/owncloud/ui/activity/PinCodeActivity.java (David A. Velasco  2012-07-09 11:11:44 +0200 188)             }
93de9f91 src/eu/alefzero/owncloud/ui/activity/PinCodeActivity.java (David A. Velasco  2012-07-09 11:11:44 +0200 189) 
93de9f91 src/eu/alefzero/owncloud/ui/activity/PinCodeActivity.java (David A. Velasco  2012-07-09 11:11:44 +0200 190)             @Override
93de9f91 src/eu/alefzero/owncloud/ui/activity/PinCodeActivity.java (David A. Velasco  2012-07-09 11:11:44 +0200 191)             public void beforeTextChanged(CharSequence s, int start, int count,
93de9f91 src/eu/alefzero/owncloud/ui/activity/PinCodeActivity.java (David A. Velasco  2012-07-09 11:11:44 +0200 192)                     int after) {
93de9f91 src/eu/alefzero/owncloud/ui/activity/PinCodeActivity.java (David A. Velasco  2012-07-09 11:11:44 +0200 193)             }
93de9f91 src/eu/alefzero/owncloud/ui/activity/PinCodeActivity.java (David A. Velasco  2012-07-09 11:11:44 +0200 194) 
93de9f91 src/eu/alefzero/owncloud/ui/activity/PinCodeActivity.java (David A. Velasco  2012-07-09 11:11:44 +0200 195)             @Override
93de9f91 src/eu/alefzero/owncloud/ui/activity/PinCodeActivity.java (David A. Velasco  2012-07-09 11:11:44 +0200 196)             public void afterTextChanged(Editable s) {
cd0886d3 src/eu/alefzero/owncloud/ui/activity/PinCodeActivity.java (David A. Velasco  2012-07-26 13:08:14 +0200 197)                 if (s.length() > 0) {
cd0886d3 src/eu/alefzero/owncloud/ui/activity/PinCodeActivity.java (David A. Velasco  2012-07-26 13:08:14 +0200 198)                     if (!confirmingPinCode){
cd0886d3 src/eu/alefzero/owncloud/ui/activity/PinCodeActivity.java (David A. Velasco  2012-07-26 13:08:14 +0200 199)                        tempText[0] = mText1.getText().toString();
cd0886d3 src/eu/alefzero/owncloud/ui/activity/PinCodeActivity.java (David A. Velasco  2012-07-26 13:08:14 +0200 200)                        
cd0886d3 src/eu/alefzero/owncloud/ui/activity/PinCodeActivity.java (David A. Velasco  2012-07-26 13:08:14 +0200 201)                     }
cd0886d3 src/eu/alefzero/owncloud/ui/activity/PinCodeActivity.java (David A. Velasco  2012-07-26 13:08:14 +0200 202)                     mText2.requestFocus();
cd0886d3 src/eu/alefzero/owncloud/ui/activity/PinCodeActivity.java (David A. Velasco  2012-07-26 13:08:14 +0200 203)                  }
93de9f91 src/eu/alefzero/owncloud/ui/activity/PinCodeActivity.java (David A. Velasco  2012-07-09 11:11:44 +0200 204)             }
93de9f91 src/eu/alefzero/owncloud/ui/activity/PinCodeActivity.java (David A. Velasco  2012-07-09 11:11:44 +0200 205)         });
93de9f91 src/eu/alefzero/owncloud/ui/activity/PinCodeActivity.java (David A. Velasco  2012-07-09 11:11:44 +0200 206)         
93de9f91 src/eu/alefzero/owncloud/ui/activity/PinCodeActivity.java (David A. Velasco  2012-07-09 11:11:44 +0200 207)         
93de9f91 src/eu/alefzero/owncloud/ui/activity/PinCodeActivity.java (David A. Velasco  2012-07-09 11:11:44 +0200 208) 
93de9f91 src/eu/alefzero/owncloud/ui/activity/PinCodeActivity.java (David A. Velasco  2012-07-09 11:11:44 +0200 209)         /*------------------------------------------------
93de9f91 src/eu/alefzero/owncloud/ui/activity/PinCodeActivity.java (David A. Velasco  2012-07-09 11:11:44 +0200 210)          *  SECOND BOX 
93de9f91 src/eu/alefzero/owncloud/ui/activity/PinCodeActivity.java (David A. Velasco  2012-07-09 11:11:44 +0200 211)          -------------------------------------------------*/
93de9f91 src/eu/alefzero/owncloud/ui/activity/PinCodeActivity.java (David A. Velasco  2012-07-09 11:11:44 +0200 212)         mText2.addTextChangedListener(new TextWatcher() {
93de9f91 src/eu/alefzero/owncloud/ui/activity/PinCodeActivity.java (David A. Velasco  2012-07-09 11:11:44 +0200 213) 
93de9f91 src/eu/alefzero/owncloud/ui/activity/PinCodeActivity.java (David A. Velasco  2012-07-09 11:11:44 +0200 214)             @Override
93de9f91 src/eu/alefzero/owncloud/ui/activity/PinCodeActivity.java (David A. Velasco  2012-07-09 11:11:44 +0200 215)             public void onTextChanged(CharSequence s, int start, int before,
93de9f91 src/eu/alefzero/owncloud/ui/activity/PinCodeActivity.java (David A. Velasco  2012-07-09 11:11:44 +0200 216)                     int count) {
93de9f91 src/eu/alefzero/owncloud/ui/activity/PinCodeActivity.java (David A. Velasco  2012-07-09 11:11:44 +0200 217)             }
93de9f91 src/eu/alefzero/owncloud/ui/activity/PinCodeActivity.java (David A. Velasco  2012-07-09 11:11:44 +0200 218) 
93de9f91 src/eu/alefzero/owncloud/ui/activity/PinCodeActivity.java (David A. Velasco  2012-07-09 11:11:44 +0200 219)             @Override
93de9f91 src/eu/alefzero/owncloud/ui/activity/PinCodeActivity.java (David A. Velasco  2012-07-09 11:11:44 +0200 220)             public void beforeTextChanged(CharSequence s, int start, int count,
93de9f91 src/eu/alefzero/owncloud/ui/activity/PinCodeActivity.java (David A. Velasco  2012-07-09 11:11:44 +0200 221)                     int after) {
93de9f91 src/eu/alefzero/owncloud/ui/activity/PinCodeActivity.java (David A. Velasco  2012-07-09 11:11:44 +0200 222)             }
93de9f91 src/eu/alefzero/owncloud/ui/activity/PinCodeActivity.java (David A. Velasco  2012-07-09 11:11:44 +0200 223) 
93de9f91 src/eu/alefzero/owncloud/ui/activity/PinCodeActivity.java (David A. Velasco  2012-07-09 11:11:44 +0200 224)             @Override
93de9f91 src/eu/alefzero/owncloud/ui/activity/PinCodeActivity.java (David A. Velasco  2012-07-09 11:11:44 +0200 225)             public void afterTextChanged(Editable s) {
cd0886d3 src/eu/alefzero/owncloud/ui/activity/PinCodeActivity.java (David A. Velasco  2012-07-26 13:08:14 +0200 226)                 if (s.length() > 0) {
cd0886d3 src/eu/alefzero/owncloud/ui/activity/PinCodeActivity.java (David A. Velasco  2012-07-26 13:08:14 +0200 227)                     if (!confirmingPinCode){
cd0886d3 src/eu/alefzero/owncloud/ui/activity/PinCodeActivity.java (David A. Velasco  2012-07-26 13:08:14 +0200 228)                         tempText[1] = mText2.getText().toString();
cd0886d3 src/eu/alefzero/owncloud/ui/activity/PinCodeActivity.java (David A. Velasco  2012-07-26 13:08:14 +0200 229)                     }
cd0886d3 src/eu/alefzero/owncloud/ui/activity/PinCodeActivity.java (David A. Velasco  2012-07-26 13:08:14 +0200 230)                     
cd0886d3 src/eu/alefzero/owncloud/ui/activity/PinCodeActivity.java (David A. Velasco  2012-07-26 13:08:14 +0200 231)                     mText3.requestFocus();
cd0886d3 src/eu/alefzero/owncloud/ui/activity/PinCodeActivity.java (David A. Velasco  2012-07-26 13:08:14 +0200 232)                 }
93de9f91 src/eu/alefzero/owncloud/ui/activity/PinCodeActivity.java (David A. Velasco  2012-07-09 11:11:44 +0200 233)             }
93de9f91 src/eu/alefzero/owncloud/ui/activity/PinCodeActivity.java (David A. Velasco  2012-07-09 11:11:44 +0200 234)         });
93de9f91 src/eu/alefzero/owncloud/ui/activity/PinCodeActivity.java (David A. Velasco  2012-07-09 11:11:44 +0200 235)  
93de9f91 src/eu/alefzero/owncloud/ui/activity/PinCodeActivity.java (David A. Velasco  2012-07-09 11:11:44 +0200 236)         mText2.setOnKeyListener(new OnKeyListener() {
93de9f91 src/eu/alefzero/owncloud/ui/activity/PinCodeActivity.java (David A. Velasco  2012-07-09 11:11:44 +0200 237) 
93de9f91 src/eu/alefzero/owncloud/ui/activity/PinCodeActivity.java (David A. Velasco  2012-07-09 11:11:44 +0200 238)             @Override
93de9f91 src/eu/alefzero/owncloud/ui/activity/PinCodeActivity.java (David A. Velasco  2012-07-09 11:11:44 +0200 239)             public boolean onKey(View v, int keyCode, KeyEvent event) {
93de9f91 src/eu/alefzero/owncloud/ui/activity/PinCodeActivity.java (David A. Velasco  2012-07-09 11:11:44 +0200 240)                 if (keyCode == KeyEvent.KEYCODE_DEL && bChange) {
93de9f91 src/eu/alefzero/owncloud/ui/activity/PinCodeActivity.java (David A. Velasco  2012-07-09 11:11:44 +0200 241) 
93de9f91 src/eu/alefzero/owncloud/ui/activity/PinCodeActivity.java (David A. Velasco  2012-07-09 11:11:44 +0200 242)                     mText1.setText("");
93de9f91 src/eu/alefzero/owncloud/ui/activity/PinCodeActivity.java (David A. Velasco  2012-07-09 11:11:44 +0200 243)                     mText1.requestFocus();
e1846469 src/eu/alefzero/owncloud/ui/activity/PinCodeActivity.java (David A. Velasco  2012-07-16 12:59:25 +0200 244)                     if (!confirmingPinCode)
e1846469 src/eu/alefzero/owncloud/ui/activity/PinCodeActivity.java (David A. Velasco  2012-07-16 12:59:25 +0200 245)                        tempText[0] = "";
93de9f91 src/eu/alefzero/owncloud/ui/activity/PinCodeActivity.java (David A. Velasco  2012-07-09 11:11:44 +0200 246)                     bChange= false;
93de9f91 src/eu/alefzero/owncloud/ui/activity/PinCodeActivity.java (David A. Velasco  2012-07-09 11:11:44 +0200 247)                 
93de9f91 src/eu/alefzero/owncloud/ui/activity/PinCodeActivity.java (David A. Velasco  2012-07-09 11:11:44 +0200 248)                 }else if(!bChange){
93de9f91 src/eu/alefzero/owncloud/ui/activity/PinCodeActivity.java (David A. Velasco  2012-07-09 11:11:44 +0200 249)                     bChange=true;
93de9f91 src/eu/alefzero/owncloud/ui/activity/PinCodeActivity.java (David A. Velasco  2012-07-09 11:11:44 +0200 250)                     
93de9f91 src/eu/alefzero/owncloud/ui/activity/PinCodeActivity.java (David A. Velasco  2012-07-09 11:11:44 +0200 251)                 }
93de9f91 src/eu/alefzero/owncloud/ui/activity/PinCodeActivity.java (David A. Velasco  2012-07-09 11:11:44 +0200 252)                 return false;
93de9f91 src/eu/alefzero/owncloud/ui/activity/PinCodeActivity.java (David A. Velasco  2012-07-09 11:11:44 +0200 253)             }
93de9f91 src/eu/alefzero/owncloud/ui/activity/PinCodeActivity.java (David A. Velasco  2012-07-09 11:11:44 +0200 254)         });        
93de9f91 src/eu/alefzero/owncloud/ui/activity/PinCodeActivity.java (David A. Velasco  2012-07-09 11:11:44 +0200 255)  
93de9f91 src/eu/alefzero/owncloud/ui/activity/PinCodeActivity.java (David A. Velasco  2012-07-09 11:11:44 +0200 256)         mText2.setOnFocusChangeListener(new OnFocusChangeListener() {
93de9f91 src/eu/alefzero/owncloud/ui/activity/PinCodeActivity.java (David A. Velasco  2012-07-09 11:11:44 +0200 257)                
93de9f91 src/eu/alefzero/owncloud/ui/activity/PinCodeActivity.java (David A. Velasco  2012-07-09 11:11:44 +0200 258)             @Override
93de9f91 src/eu/alefzero/owncloud/ui/activity/PinCodeActivity.java (David A. Velasco  2012-07-09 11:11:44 +0200 259)             public void onFocusChange(View v, boolean hasFocus) {
3cf90ff1 src/eu/alefzero/owncloud/ui/activity/PinCodeActivity.java (David A. Velasco  2012-07-19 11:51:05 +0200 260)                 mText2.setCursorVisible(true);
93de9f91 src/eu/alefzero/owncloud/ui/activity/PinCodeActivity.java (David A. Velasco  2012-07-09 11:11:44 +0200 261)                 if (mText1.getText().toString().equals("")){
3cf90ff1 src/eu/alefzero/owncloud/ui/activity/PinCodeActivity.java (David A. Velasco  2012-07-19 11:51:05 +0200 262)                     mText2.setSelected(false);
3cf90ff1 src/eu/alefzero/owncloud/ui/activity/PinCodeActivity.java (David A. Velasco  2012-07-19 11:51:05 +0200 263)                     mText2.setCursorVisible(false);
93de9f91 src/eu/alefzero/owncloud/ui/activity/PinCodeActivity.java (David A. Velasco  2012-07-09 11:11:44 +0200 264)                     mText1.requestFocus(); 
3cf90ff1 src/eu/alefzero/owncloud/ui/activity/PinCodeActivity.java (David A. Velasco  2012-07-19 11:51:05 +0200 265)                     mText1.setSelected(true);
3cf90ff1 src/eu/alefzero/owncloud/ui/activity/PinCodeActivity.java (David A. Velasco  2012-07-19 11:51:05 +0200 266)                     mText1.setSelection(0);
93de9f91 src/eu/alefzero/owncloud/ui/activity/PinCodeActivity.java (David A. Velasco  2012-07-09 11:11:44 +0200 267)                 }
93de9f91 src/eu/alefzero/owncloud/ui/activity/PinCodeActivity.java (David A. Velasco  2012-07-09 11:11:44 +0200 268)                 
93de9f91 src/eu/alefzero/owncloud/ui/activity/PinCodeActivity.java (David A. Velasco  2012-07-09 11:11:44 +0200 269)             }
93de9f91 src/eu/alefzero/owncloud/ui/activity/PinCodeActivity.java (David A. Velasco  2012-07-09 11:11:44 +0200 270)         });
93de9f91 src/eu/alefzero/owncloud/ui/activity/PinCodeActivity.java (David A. Velasco  2012-07-09 11:11:44 +0200 271)         
93de9f91 src/eu/alefzero/owncloud/ui/activity/PinCodeActivity.java (David A. Velasco  2012-07-09 11:11:44 +0200 272)         
93de9f91 src/eu/alefzero/owncloud/ui/activity/PinCodeActivity.java (David A. Velasco  2012-07-09 11:11:44 +0200 273)         /*------------------------------------------------
93de9f91 src/eu/alefzero/owncloud/ui/activity/PinCodeActivity.java (David A. Velasco  2012-07-09 11:11:44 +0200 274)          *  THIRD BOX
93de9f91 src/eu/alefzero/owncloud/ui/activity/PinCodeActivity.java (David A. Velasco  2012-07-09 11:11:44 +0200 275)          -------------------------------------------------*/
93de9f91 src/eu/alefzero/owncloud/ui/activity/PinCodeActivity.java (David A. Velasco  2012-07-09 11:11:44 +0200 276)         mText3.addTextChangedListener(new TextWatcher() {
93de9f91 src/eu/alefzero/owncloud/ui/activity/PinCodeActivity.java (David A. Velasco  2012-07-09 11:11:44 +0200 277) 
93de9f91 src/eu/alefzero/owncloud/ui/activity/PinCodeActivity.java (David A. Velasco  2012-07-09 11:11:44 +0200 278)             @Override
93de9f91 src/eu/alefzero/owncloud/ui/activity/PinCodeActivity.java (David A. Velasco  2012-07-09 11:11:44 +0200 279)             public void onTextChanged(CharSequence s, int start, int before,
93de9f91 src/eu/alefzero/owncloud/ui/activity/PinCodeActivity.java (David A. Velasco  2012-07-09 11:11:44 +0200 280)                     int count) {
93de9f91 src/eu/alefzero/owncloud/ui/activity/PinCodeActivity.java (David A. Velasco  2012-07-09 11:11:44 +0200 281)             }
93de9f91 src/eu/alefzero/owncloud/ui/activity/PinCodeActivity.java (David A. Velasco  2012-07-09 11:11:44 +0200 282) 
93de9f91 src/eu/alefzero/owncloud/ui/activity/PinCodeActivity.java (David A. Velasco  2012-07-09 11:11:44 +0200 283)             @Override
93de9f91 src/eu/alefzero/owncloud/ui/activity/PinCodeActivity.java (David A. Velasco  2012-07-09 11:11:44 +0200 284)             public void beforeTextChanged(CharSequence s, int start, int count,
93de9f91 src/eu/alefzero/owncloud/ui/activity/PinCodeActivity.java (David A. Velasco  2012-07-09 11:11:44 +0200 285)                     int after) {
93de9f91 src/eu/alefzero/owncloud/ui/activity/PinCodeActivity.java (David A. Velasco  2012-07-09 11:11:44 +0200 286)             }
93de9f91 src/eu/alefzero/owncloud/ui/activity/PinCodeActivity.java (David A. Velasco  2012-07-09 11:11:44 +0200 287) 
93de9f91 src/eu/alefzero/owncloud/ui/activity/PinCodeActivity.java (David A. Velasco  2012-07-09 11:11:44 +0200 288)             @Override
93de9f91 src/eu/alefzero/owncloud/ui/activity/PinCodeActivity.java (David A. Velasco  2012-07-09 11:11:44 +0200 289)             public void afterTextChanged(Editable s) {
cd0886d3 src/eu/alefzero/owncloud/ui/activity/PinCodeActivity.java (David A. Velasco  2012-07-26 13:08:14 +0200 290)                 if (s.length() > 0) {
cd0886d3 src/eu/alefzero/owncloud/ui/activity/PinCodeActivity.java (David A. Velasco  2012-07-26 13:08:14 +0200 291)                     if (!confirmingPinCode){
cd0886d3 src/eu/alefzero/owncloud/ui/activity/PinCodeActivity.java (David A. Velasco  2012-07-26 13:08:14 +0200 292)                         tempText[2] = mText3.getText().toString();
cd0886d3 src/eu/alefzero/owncloud/ui/activity/PinCodeActivity.java (David A. Velasco  2012-07-26 13:08:14 +0200 293)                     }
cd0886d3 src/eu/alefzero/owncloud/ui/activity/PinCodeActivity.java (David A. Velasco  2012-07-26 13:08:14 +0200 294)                     mText4.requestFocus();
cd0886d3 src/eu/alefzero/owncloud/ui/activity/PinCodeActivity.java (David A. Velasco  2012-07-26 13:08:14 +0200 295)                 }
93de9f91 src/eu/alefzero/owncloud/ui/activity/PinCodeActivity.java (David A. Velasco  2012-07-09 11:11:44 +0200 296)             }
93de9f91 src/eu/alefzero/owncloud/ui/activity/PinCodeActivity.java (David A. Velasco  2012-07-09 11:11:44 +0200 297)         });
93de9f91 src/eu/alefzero/owncloud/ui/activity/PinCodeActivity.java (David A. Velasco  2012-07-09 11:11:44 +0200 298)         
93de9f91 src/eu/alefzero/owncloud/ui/activity/PinCodeActivity.java (David A. Velasco  2012-07-09 11:11:44 +0200 299)         mText3.setOnKeyListener(new OnKeyListener() {
93de9f91 src/eu/alefzero/owncloud/ui/activity/PinCodeActivity.java (David A. Velasco  2012-07-09 11:11:44 +0200 300) 
93de9f91 src/eu/alefzero/owncloud/ui/activity/PinCodeActivity.java (David A. Velasco  2012-07-09 11:11:44 +0200 301)             @Override
93de9f91 src/eu/alefzero/owncloud/ui/activity/PinCodeActivity.java (David A. Velasco  2012-07-09 11:11:44 +0200 302)             public boolean onKey(View v, int keyCode, KeyEvent event) {
93de9f91 src/eu/alefzero/owncloud/ui/activity/PinCodeActivity.java (David A. Velasco  2012-07-09 11:11:44 +0200 303)                 if (keyCode == KeyEvent.KEYCODE_DEL && bChange) {
93de9f91 src/eu/alefzero/owncloud/ui/activity/PinCodeActivity.java (David A. Velasco  2012-07-09 11:11:44 +0200 304)                     mText2.requestFocus();
e1846469 src/eu/alefzero/owncloud/ui/activity/PinCodeActivity.java (David A. Velasco  2012-07-16 12:59:25 +0200 305)                     if (!confirmingPinCode)
e1846469 src/eu/alefzero/owncloud/ui/activity/PinCodeActivity.java (David A. Velasco  2012-07-16 12:59:25 +0200 306)                         tempText[1] = "";
93de9f91 src/eu/alefzero/owncloud/ui/activity/PinCodeActivity.java (David A. Velasco  2012-07-09 11:11:44 +0200 307)                     mText2.setText("");
93de9f91 src/eu/alefzero/owncloud/ui/activity/PinCodeActivity.java (David A. Velasco  2012-07-09 11:11:44 +0200 308)                     bChange= false;
93de9f91 src/eu/alefzero/owncloud/ui/activity/PinCodeActivity.java (David A. Velasco  2012-07-09 11:11:44 +0200 309)                     
93de9f91 src/eu/alefzero/owncloud/ui/activity/PinCodeActivity.java (David A. Velasco  2012-07-09 11:11:44 +0200 310)                 }else if(!bChange){
93de9f91 src/eu/alefzero/owncloud/ui/activity/PinCodeActivity.java (David A. Velasco  2012-07-09 11:11:44 +0200 311)                     bChange=true;                        
93de9f91 src/eu/alefzero/owncloud/ui/activity/PinCodeActivity.java (David A. Velasco  2012-07-09 11:11:44 +0200 312)                     
93de9f91 src/eu/alefzero/owncloud/ui/activity/PinCodeActivity.java (David A. Velasco  2012-07-09 11:11:44 +0200 313)                 }
93de9f91 src/eu/alefzero/owncloud/ui/activity/PinCodeActivity.java (David A. Velasco  2012-07-09 11:11:44 +0200 314)                 return false;
93de9f91 src/eu/alefzero/owncloud/ui/activity/PinCodeActivity.java (David A. Velasco  2012-07-09 11:11:44 +0200 315)             }
93de9f91 src/eu/alefzero/owncloud/ui/activity/PinCodeActivity.java (David A. Velasco  2012-07-09 11:11:44 +0200 316)         });
93de9f91 src/eu/alefzero/owncloud/ui/activity/PinCodeActivity.java (David A. Velasco  2012-07-09 11:11:44 +0200 317)         
93de9f91 src/eu/alefzero/owncloud/ui/activity/PinCodeActivity.java (David A. Velasco  2012-07-09 11:11:44 +0200 318)         mText3.setOnFocusChangeListener(new OnFocusChangeListener() {
93de9f91 src/eu/alefzero/owncloud/ui/activity/PinCodeActivity.java (David A. Velasco  2012-07-09 11:11:44 +0200 319)             
93de9f91 src/eu/alefzero/owncloud/ui/activity/PinCodeActivity.java (David A. Velasco  2012-07-09 11:11:44 +0200 320)             @Override
93de9f91 src/eu/alefzero/owncloud/ui/activity/PinCodeActivity.java (David A. Velasco  2012-07-09 11:11:44 +0200 321)             public void onFocusChange(View v, boolean hasFocus) {
3cf90ff1 src/eu/alefzero/owncloud/ui/activity/PinCodeActivity.java (David A. Velasco  2012-07-19 11:51:05 +0200 322)                 mText3.setCursorVisible(true);
93de9f91 src/eu/alefzero/owncloud/ui/activity/PinCodeActivity.java (David A. Velasco  2012-07-09 11:11:44 +0200 323)                 if (mText1.getText().toString().equals("")){
3cf90ff1 src/eu/alefzero/owncloud/ui/activity/PinCodeActivity.java (David A. Velasco  2012-07-19 11:51:05 +0200 324)                     mText3.setSelected(false);
3cf90ff1 src/eu/alefzero/owncloud/ui/activity/PinCodeActivity.java (David A. Velasco  2012-07-19 11:51:05 +0200 325)                     mText3.setCursorVisible(false);
3cf90ff1 src/eu/alefzero/owncloud/ui/activity/PinCodeActivity.java (David A. Velasco  2012-07-19 11:51:05 +0200 326)                     mText1.requestFocus();
3cf90ff1 src/eu/alefzero/owncloud/ui/activity/PinCodeActivity.java (David A. Velasco  2012-07-19 11:51:05 +0200 327)                     mText1.setSelected(true);
3cf90ff1 src/eu/alefzero/owncloud/ui/activity/PinCodeActivity.java (David A. Velasco  2012-07-19 11:51:05 +0200 328)                     mText1.setSelection(0);
93de9f91 src/eu/alefzero/owncloud/ui/activity/PinCodeActivity.java (David A. Velasco  2012-07-09 11:11:44 +0200 329)                 }else if (mText2.getText().toString().equals("")){
3cf90ff1 src/eu/alefzero/owncloud/ui/activity/PinCodeActivity.java (David A. Velasco  2012-07-19 11:51:05 +0200 330)                     mText3.setSelected(false);
3cf90ff1 src/eu/alefzero/owncloud/ui/activity/PinCodeActivity.java (David A. Velasco  2012-07-19 11:51:05 +0200 331)                     mText3.setCursorVisible(false);
3cf90ff1 src/eu/alefzero/owncloud/ui/activity/PinCodeActivity.java (David A. Velasco  2012-07-19 11:51:05 +0200 332)                     mText2.requestFocus();
3cf90ff1 src/eu/alefzero/owncloud/ui/activity/PinCodeActivity.java (David A. Velasco  2012-07-19 11:51:05 +0200 333)                     mText2.setSelected(true);
3cf90ff1 src/eu/alefzero/owncloud/ui/activity/PinCodeActivity.java (David A. Velasco  2012-07-19 11:51:05 +0200 334)                     mText2.setSelection(0);
93de9f91 src/eu/alefzero/owncloud/ui/activity/PinCodeActivity.java (David A. Velasco  2012-07-09 11:11:44 +0200 335)                 }
93de9f91 src/eu/alefzero/owncloud/ui/activity/PinCodeActivity.java (David A. Velasco  2012-07-09 11:11:44 +0200 336)                 
93de9f91 src/eu/alefzero/owncloud/ui/activity/PinCodeActivity.java (David A. Velasco  2012-07-09 11:11:44 +0200 337)             }
93de9f91 src/eu/alefzero/owncloud/ui/activity/PinCodeActivity.java (David A. Velasco  2012-07-09 11:11:44 +0200 338)         });
93de9f91 src/eu/alefzero/owncloud/ui/activity/PinCodeActivity.java (David A. Velasco  2012-07-09 11:11:44 +0200 339)         
93de9f91 src/eu/alefzero/owncloud/ui/activity/PinCodeActivity.java (David A. Velasco  2012-07-09 11:11:44 +0200 340)         /*------------------------------------------------
93de9f91 src/eu/alefzero/owncloud/ui/activity/PinCodeActivity.java (David A. Velasco  2012-07-09 11:11:44 +0200 341)          *  FOURTH BOX
93de9f91 src/eu/alefzero/owncloud/ui/activity/PinCodeActivity.java (David A. Velasco  2012-07-09 11:11:44 +0200 342)          -------------------------------------------------*/
93de9f91 src/eu/alefzero/owncloud/ui/activity/PinCodeActivity.java (David A. Velasco  2012-07-09 11:11:44 +0200 343)         mText4.addTextChangedListener(new TextWatcher() {
93de9f91 src/eu/alefzero/owncloud/ui/activity/PinCodeActivity.java (David A. Velasco  2012-07-09 11:11:44 +0200 344) 
93de9f91 src/eu/alefzero/owncloud/ui/activity/PinCodeActivity.java (David A. Velasco  2012-07-09 11:11:44 +0200 345)             @Override
93de9f91 src/eu/alefzero/owncloud/ui/activity/PinCodeActivity.java (David A. Velasco  2012-07-09 11:11:44 +0200 346)             public void onTextChanged(CharSequence s, int start, int before,
93de9f91 src/eu/alefzero/owncloud/ui/activity/PinCodeActivity.java (David A. Velasco  2012-07-09 11:11:44 +0200 347)                     int count) {
93de9f91 src/eu/alefzero/owncloud/ui/activity/PinCodeActivity.java (David A. Velasco  2012-07-09 11:11:44 +0200 348)             }
93de9f91 src/eu/alefzero/owncloud/ui/activity/PinCodeActivity.java (David A. Velasco  2012-07-09 11:11:44 +0200 349) 
93de9f91 src/eu/alefzero/owncloud/ui/activity/PinCodeActivity.java (David A. Velasco  2012-07-09 11:11:44 +0200 350)             @Override
93de9f91 src/eu/alefzero/owncloud/ui/activity/PinCodeActivity.java (David A. Velasco  2012-07-09 11:11:44 +0200 351)             public void beforeTextChanged(CharSequence s, int start, int count,
93de9f91 src/eu/alefzero/owncloud/ui/activity/PinCodeActivity.java (David A. Velasco  2012-07-09 11:11:44 +0200 352)                     int after) {
93de9f91 src/eu/alefzero/owncloud/ui/activity/PinCodeActivity.java (David A. Velasco  2012-07-09 11:11:44 +0200 353)             }
93de9f91 src/eu/alefzero/owncloud/ui/activity/PinCodeActivity.java (David A. Velasco  2012-07-09 11:11:44 +0200 354) 
93de9f91 src/eu/alefzero/owncloud/ui/activity/PinCodeActivity.java (David A. Velasco  2012-07-09 11:11:44 +0200 355)             @Override
93de9f91 src/eu/alefzero/owncloud/ui/activity/PinCodeActivity.java (David A. Velasco  2012-07-09 11:11:44 +0200 356)             public void afterTextChanged(Editable s) {
cd0886d3 src/eu/alefzero/owncloud/ui/activity/PinCodeActivity.java (David A. Velasco  2012-07-26 13:08:14 +0200 357)                 if (s.length() > 0) {
cd0886d3 src/eu/alefzero/owncloud/ui/activity/PinCodeActivity.java (David A. Velasco  2012-07-26 13:08:14 +0200 358)                     
cd0886d3 src/eu/alefzero/owncloud/ui/activity/PinCodeActivity.java (David A. Velasco  2012-07-26 13:08:14 +0200 359)                     if (!confirmingPinCode){
cd0886d3 src/eu/alefzero/owncloud/ui/activity/PinCodeActivity.java (David A. Velasco  2012-07-26 13:08:14 +0200 360)                        tempText[3] = mText4.getText().toString();
cd0886d3 src/eu/alefzero/owncloud/ui/activity/PinCodeActivity.java (David A. Velasco  2012-07-26 13:08:14 +0200 361)                     }
cd0886d3 src/eu/alefzero/owncloud/ui/activity/PinCodeActivity.java (David A. Velasco  2012-07-26 13:08:14 +0200 362)                     mText1.requestFocus();
93de9f91 src/eu/alefzero/owncloud/ui/activity/PinCodeActivity.java (David A. Velasco  2012-07-09 11:11:44 +0200 363) 
cd0886d3 src/eu/alefzero/owncloud/ui/activity/PinCodeActivity.java (David A. Velasco  2012-07-26 13:08:14 +0200 364)                     if (!pinCodeChecked){
cd0886d3 src/eu/alefzero/owncloud/ui/activity/PinCodeActivity.java (David A. Velasco  2012-07-26 13:08:14 +0200 365)                         pinCodeChecked = checkPincode();
cd0886d3 src/eu/alefzero/owncloud/ui/activity/PinCodeActivity.java (David A. Velasco  2012-07-26 13:08:14 +0200 366)                     }
cd0886d3 src/eu/alefzero/owncloud/ui/activity/PinCodeActivity.java (David A. Velasco  2012-07-26 13:08:14 +0200 367)                     
cd0886d3 src/eu/alefzero/owncloud/ui/activity/PinCodeActivity.java (David A. Velasco  2012-07-26 13:08:14 +0200 368)                     if (pinCodeChecked && activity.equals("FileDisplayActivity")){
cd0886d3 src/eu/alefzero/owncloud/ui/activity/PinCodeActivity.java (David A. Velasco  2012-07-26 13:08:14 +0200 369)                         finish();
cd0886d3 src/eu/alefzero/owncloud/ui/activity/PinCodeActivity.java (David A. Velasco  2012-07-26 13:08:14 +0200 370)                     } else if (pinCodeChecked){
cd0886d3 src/eu/alefzero/owncloud/ui/activity/PinCodeActivity.java (David A. Velasco  2012-07-26 13:08:14 +0200 371)                         
cd0886d3 src/eu/alefzero/owncloud/ui/activity/PinCodeActivity.java (David A. Velasco  2012-07-26 13:08:14 +0200 372)                         Intent intent = getIntent();
cd0886d3 src/eu/alefzero/owncloud/ui/activity/PinCodeActivity.java (David A. Velasco  2012-07-26 13:08:14 +0200 373)                         String newState = intent.getStringExtra(EXTRA_NEW_STATE);
cd0886d3 src/eu/alefzero/owncloud/ui/activity/PinCodeActivity.java (David A. Velasco  2012-07-26 13:08:14 +0200 374)                         
cd0886d3 src/eu/alefzero/owncloud/ui/activity/PinCodeActivity.java (David A. Velasco  2012-07-26 13:08:14 +0200 375)                         if (newState.equals("false")){
cd0886d3 src/eu/alefzero/owncloud/ui/activity/PinCodeActivity.java (David A. Velasco  2012-07-26 13:08:14 +0200 376)                             SharedPreferences.Editor appPrefs = PreferenceManager
cd0886d3 src/eu/alefzero/owncloud/ui/activity/PinCodeActivity.java (David A. Velasco  2012-07-26 13:08:14 +0200 377)                                     .getDefaultSharedPreferences(getApplicationContext()).edit();
cd0886d3 src/eu/alefzero/owncloud/ui/activity/PinCodeActivity.java (David A. Velasco  2012-07-26 13:08:14 +0200 378)                             appPrefs.putBoolean("set_pincode",false);
cd0886d3 src/eu/alefzero/owncloud/ui/activity/PinCodeActivity.java (David A. Velasco  2012-07-26 13:08:14 +0200 379)                             appPrefs.commit();
cd0886d3 src/eu/alefzero/owncloud/ui/activity/PinCodeActivity.java (David A. Velasco  2012-07-26 13:08:14 +0200 380)                             
cd0886d3 src/eu/alefzero/owncloud/ui/activity/PinCodeActivity.java (David A. Velasco  2012-07-26 13:08:14 +0200 381)                             setInitVars();
cd0886d3 src/eu/alefzero/owncloud/ui/activity/PinCodeActivity.java (David A. Velasco  2012-07-26 13:08:14 +0200 382)                             pinCodeEnd(false);
cd0886d3 src/eu/alefzero/owncloud/ui/activity/PinCodeActivity.java (David A. Velasco  2012-07-26 13:08:14 +0200 383)                             
cd0886d3 src/eu/alefzero/owncloud/ui/activity/PinCodeActivity.java (David A. Velasco  2012-07-26 13:08:14 +0200 384)                         }else{
cd0886d3 src/eu/alefzero/owncloud/ui/activity/PinCodeActivity.java (David A. Velasco  2012-07-26 13:08:14 +0200 385)                         
cd0886d3 src/eu/alefzero/owncloud/ui/activity/PinCodeActivity.java (David A. Velasco  2012-07-26 13:08:14 +0200 386)                             if (!confirmingPinCode){
cd0886d3 src/eu/alefzero/owncloud/ui/activity/PinCodeActivity.java (David A. Velasco  2012-07-26 13:08:14 +0200 387)                                 pinCodeChangeRequest();
cd0886d3 src/eu/alefzero/owncloud/ui/activity/PinCodeActivity.java (David A. Velasco  2012-07-26 13:08:14 +0200 388)                              
cd0886d3 src/eu/alefzero/owncloud/ui/activity/PinCodeActivity.java (David A. Velasco  2012-07-26 13:08:14 +0200 389)                             } else {
cd0886d3 src/eu/alefzero/owncloud/ui/activity/PinCodeActivity.java (David A. Velasco  2012-07-26 13:08:14 +0200 390)                                 confirmPincode();
cd0886d3 src/eu/alefzero/owncloud/ui/activity/PinCodeActivity.java (David A. Velasco  2012-07-26 13:08:14 +0200 391)                             }
cd0886d3 src/eu/alefzero/owncloud/ui/activity/PinCodeActivity.java (David A. Velasco  2012-07-26 13:08:14 +0200 392)                         }
cd0886d3 src/eu/alefzero/owncloud/ui/activity/PinCodeActivity.java (David A. Velasco  2012-07-26 13:08:14 +0200 393)                    
cd0886d3 src/eu/alefzero/owncloud/ui/activity/PinCodeActivity.java (David A. Velasco  2012-07-26 13:08:14 +0200 394)                         
cd0886d3 src/eu/alefzero/owncloud/ui/activity/PinCodeActivity.java (David A. Velasco  2012-07-26 13:08:14 +0200 395)                     }    
cd0886d3 src/eu/alefzero/owncloud/ui/activity/PinCodeActivity.java (David A. Velasco  2012-07-26 13:08:14 +0200 396)                 }
93de9f91 src/eu/alefzero/owncloud/ui/activity/PinCodeActivity.java (David A. Velasco  2012-07-09 11:11:44 +0200 397)             }
93de9f91 src/eu/alefzero/owncloud/ui/activity/PinCodeActivity.java (David A. Velasco  2012-07-09 11:11:44 +0200 398)         });
93de9f91 src/eu/alefzero/owncloud/ui/activity/PinCodeActivity.java (David A. Velasco  2012-07-09 11:11:44 +0200 399) 
93de9f91 src/eu/alefzero/owncloud/ui/activity/PinCodeActivity.java (David A. Velasco  2012-07-09 11:11:44 +0200 400)         
93de9f91 src/eu/alefzero/owncloud/ui/activity/PinCodeActivity.java (David A. Velasco  2012-07-09 11:11:44 +0200 401)         
93de9f91 src/eu/alefzero/owncloud/ui/activity/PinCodeActivity.java (David A. Velasco  2012-07-09 11:11:44 +0200 402)         mText4.setOnKeyListener(new OnKeyListener() {
93de9f91 src/eu/alefzero/owncloud/ui/activity/PinCodeActivity.java (David A. Velasco  2012-07-09 11:11:44 +0200 403) 
93de9f91 src/eu/alefzero/owncloud/ui/activity/PinCodeActivity.java (David A. Velasco  2012-07-09 11:11:44 +0200 404)             @Override
93de9f91 src/eu/alefzero/owncloud/ui/activity/PinCodeActivity.java (David A. Velasco  2012-07-09 11:11:44 +0200 405)             public boolean onKey(View v, int keyCode, KeyEvent event) {
93de9f91 src/eu/alefzero/owncloud/ui/activity/PinCodeActivity.java (David A. Velasco  2012-07-09 11:11:44 +0200 406)                 if (keyCode == KeyEvent.KEYCODE_DEL && bChange) {
93de9f91 src/eu/alefzero/owncloud/ui/activity/PinCodeActivity.java (David A. Velasco  2012-07-09 11:11:44 +0200 407)                     mText3.requestFocus();
e1846469 src/eu/alefzero/owncloud/ui/activity/PinCodeActivity.java (David A. Velasco  2012-07-16 12:59:25 +0200 408)                     if (!confirmingPinCode)
e1846469 src/eu/alefzero/owncloud/ui/activity/PinCodeActivity.java (David A. Velasco  2012-07-16 12:59:25 +0200 409)                         tempText[2]="";
93de9f91 src/eu/alefzero/owncloud/ui/activity/PinCodeActivity.java (David A. Velasco  2012-07-09 11:11:44 +0200 410)                     mText3.setText("");
93de9f91 src/eu/alefzero/owncloud/ui/activity/PinCodeActivity.java (David A. Velasco  2012-07-09 11:11:44 +0200 411)                     bChange= false;
93de9f91 src/eu/alefzero/owncloud/ui/activity/PinCodeActivity.java (David A. Velasco  2012-07-09 11:11:44 +0200 412)                     
93de9f91 src/eu/alefzero/owncloud/ui/activity/PinCodeActivity.java (David A. Velasco  2012-07-09 11:11:44 +0200 413)                 }else if(!bChange){
93de9f91 src/eu/alefzero/owncloud/ui/activity/PinCodeActivity.java (David A. Velasco  2012-07-09 11:11:44 +0200 414)                     bChange=true;    
93de9f91 src/eu/alefzero/owncloud/ui/activity/PinCodeActivity.java (David A. Velasco  2012-07-09 11:11:44 +0200 415)                 }
93de9f91 src/eu/alefzero/owncloud/ui/activity/PinCodeActivity.java (David A. Velasco  2012-07-09 11:11:44 +0200 416)                 return false;
93de9f91 src/eu/alefzero/owncloud/ui/activity/PinCodeActivity.java (David A. Velasco  2012-07-09 11:11:44 +0200 417)             }
93de9f91 src/eu/alefzero/owncloud/ui/activity/PinCodeActivity.java (David A. Velasco  2012-07-09 11:11:44 +0200 418)         });
93de9f91 src/eu/alefzero/owncloud/ui/activity/PinCodeActivity.java (David A. Velasco  2012-07-09 11:11:44 +0200 419)         
93de9f91 src/eu/alefzero/owncloud/ui/activity/PinCodeActivity.java (David A. Velasco  2012-07-09 11:11:44 +0200 420)        mText4.setOnFocusChangeListener(new OnFocusChangeListener() {
93de9f91 src/eu/alefzero/owncloud/ui/activity/PinCodeActivity.java (David A. Velasco  2012-07-09 11:11:44 +0200 421)             
93de9f91 src/eu/alefzero/owncloud/ui/activity/PinCodeActivity.java (David A. Velasco  2012-07-09 11:11:44 +0200 422)             @Override
93de9f91 src/eu/alefzero/owncloud/ui/activity/PinCodeActivity.java (David A. Velasco  2012-07-09 11:11:44 +0200 423)             public void onFocusChange(View v, boolean hasFocus) {
3cf90ff1 src/eu/alefzero/owncloud/ui/activity/PinCodeActivity.java (David A. Velasco  2012-07-19 11:51:05 +0200 424)                 mText4.setCursorVisible(true);
3cf90ff1 src/eu/alefzero/owncloud/ui/activity/PinCodeActivity.java (David A. Velasco  2012-07-19 11:51:05 +0200 425)                 
93de9f91 src/eu/alefzero/owncloud/ui/activity/PinCodeActivity.java (David A. Velasco  2012-07-09 11:11:44 +0200 426)                 if (mText1.getText().toString().equals("")){
3cf90ff1 src/eu/alefzero/owncloud/ui/activity/PinCodeActivity.java (David A. Velasco  2012-07-19 11:51:05 +0200 427)                     mText4.setSelected(false);
3cf90ff1 src/eu/alefzero/owncloud/ui/activity/PinCodeActivity.java (David A. Velasco  2012-07-19 11:51:05 +0200 428)                     mText4.setCursorVisible(false);
3cf90ff1 src/eu/alefzero/owncloud/ui/activity/PinCodeActivity.java (David A. Velasco  2012-07-19 11:51:05 +0200 429)                     mText1.requestFocus();
3cf90ff1 src/eu/alefzero/owncloud/ui/activity/PinCodeActivity.java (David A. Velasco  2012-07-19 11:51:05 +0200 430)                     mText1.setSelected(true);
3cf90ff1 src/eu/alefzero/owncloud/ui/activity/PinCodeActivity.java (David A. Velasco  2012-07-19 11:51:05 +0200 431)                     mText1.setSelection(0);
93de9f91 src/eu/alefzero/owncloud/ui/activity/PinCodeActivity.java (David A. Velasco  2012-07-09 11:11:44 +0200 432)                 }else if (mText2.getText().toString().equals("")){
3cf90ff1 src/eu/alefzero/owncloud/ui/activity/PinCodeActivity.java (David A. Velasco  2012-07-19 11:51:05 +0200 433)                     mText4.setSelected(false);
3cf90ff1 src/eu/alefzero/owncloud/ui/activity/PinCodeActivity.java (David A. Velasco  2012-07-19 11:51:05 +0200 434)                     mText4.setCursorVisible(false);
3cf90ff1 src/eu/alefzero/owncloud/ui/activity/PinCodeActivity.java (David A. Velasco  2012-07-19 11:51:05 +0200 435)                     mText2.requestFocus();
3cf90ff1 src/eu/alefzero/owncloud/ui/activity/PinCodeActivity.java (David A. Velasco  2012-07-19 11:51:05 +0200 436)                     mText2.setSelected(true);
3cf90ff1 src/eu/alefzero/owncloud/ui/activity/PinCodeActivity.java (David A. Velasco  2012-07-19 11:51:05 +0200 437)                     mText2.setSelection(0);
93de9f91 src/eu/alefzero/owncloud/ui/activity/PinCodeActivity.java (David A. Velasco  2012-07-09 11:11:44 +0200 438)                 }else if (mText3.getText().toString().equals("")){
3cf90ff1 src/eu/alefzero/owncloud/ui/activity/PinCodeActivity.java (David A. Velasco  2012-07-19 11:51:05 +0200 439)                     mText4.setSelected(false);
3cf90ff1 src/eu/alefzero/owncloud/ui/activity/PinCodeActivity.java (David A. Velasco  2012-07-19 11:51:05 +0200 440)                     mText4.setCursorVisible(false);
3cf90ff1 src/eu/alefzero/owncloud/ui/activity/PinCodeActivity.java (David A. Velasco  2012-07-19 11:51:05 +0200 441)                     mText3.requestFocus();
3cf90ff1 src/eu/alefzero/owncloud/ui/activity/PinCodeActivity.java (David A. Velasco  2012-07-19 11:51:05 +0200 442)                     mText3.setSelected(true);
3cf90ff1 src/eu/alefzero/owncloud/ui/activity/PinCodeActivity.java (David A. Velasco  2012-07-19 11:51:05 +0200 443)                     mText3.setSelection(0);
93de9f91 src/eu/alefzero/owncloud/ui/activity/PinCodeActivity.java (David A. Velasco  2012-07-09 11:11:44 +0200 444)                 }
93de9f91 src/eu/alefzero/owncloud/ui/activity/PinCodeActivity.java (David A. Velasco  2012-07-09 11:11:44 +0200 445)                 
93de9f91 src/eu/alefzero/owncloud/ui/activity/PinCodeActivity.java (David A. Velasco  2012-07-09 11:11:44 +0200 446)             }
93de9f91 src/eu/alefzero/owncloud/ui/activity/PinCodeActivity.java (David A. Velasco  2012-07-09 11:11:44 +0200 447)         });
93de9f91 src/eu/alefzero/owncloud/ui/activity/PinCodeActivity.java (David A. Velasco  2012-07-09 11:11:44 +0200 448)         
93de9f91 src/eu/alefzero/owncloud/ui/activity/PinCodeActivity.java (David A. Velasco  2012-07-09 11:11:44 +0200 449)         
93de9f91 src/eu/alefzero/owncloud/ui/activity/PinCodeActivity.java (David A. Velasco  2012-07-09 11:11:44 +0200 450)         
93de9f91 src/eu/alefzero/owncloud/ui/activity/PinCodeActivity.java (David A. Velasco  2012-07-09 11:11:44 +0200 451)     } // end setTextListener
93de9f91 src/eu/alefzero/owncloud/ui/activity/PinCodeActivity.java (David A. Velasco  2012-07-09 11:11:44 +0200 452)     
93de9f91 src/eu/alefzero/owncloud/ui/activity/PinCodeActivity.java (David A. Velasco  2012-07-09 11:11:44 +0200 453)     
93de9f91 src/eu/alefzero/owncloud/ui/activity/PinCodeActivity.java (David A. Velasco  2012-07-09 11:11:44 +0200 454)     protected void pinCodeChangeRequest(){
93de9f91 src/eu/alefzero/owncloud/ui/activity/PinCodeActivity.java (David A. Velasco  2012-07-09 11:11:44 +0200 455)     
f757b74c src/eu/alefzero/owncloud/ui/activity/PinCodeActivity.java (David A. Velasco  2012-07-11 18:59:59 +0200 456)         clearBoxes(); 
91b17284 src/eu/alefzero/owncloud/ui/activity/PinCodeActivity.java (David A. Velasco  2012-07-13 14:46:40 +0200 457)         mPinHdr.setText(R.string.pincode_reenter_your_pincode); 
d193e003 src/com/owncloud/android/ui/activity/PinCodeActivity.java (David A. Velasco  2012-10-17 11:04:27 +0200 458)         mPinHdrExplanation.setVisibility(View.INVISIBLE);        
f757b74c src/eu/alefzero/owncloud/ui/activity/PinCodeActivity.java (David A. Velasco  2012-07-11 18:59:59 +0200 459)         confirmingPinCode =true;
93de9f91 src/eu/alefzero/owncloud/ui/activity/PinCodeActivity.java (David A. Velasco  2012-07-09 11:11:44 +0200 460)         
93de9f91 src/eu/alefzero/owncloud/ui/activity/PinCodeActivity.java (David A. Velasco  2012-07-09 11:11:44 +0200 461)     }
93de9f91 src/eu/alefzero/owncloud/ui/activity/PinCodeActivity.java (David A. Velasco  2012-07-09 11:11:44 +0200 462)     
93de9f91 src/eu/alefzero/owncloud/ui/activity/PinCodeActivity.java (David A. Velasco  2012-07-09 11:11:44 +0200 463)     
93de9f91 src/eu/alefzero/owncloud/ui/activity/PinCodeActivity.java (David A. Velasco  2012-07-09 11:11:44 +0200 464)     protected boolean checkPincode(){
93de9f91 src/eu/alefzero/owncloud/ui/activity/PinCodeActivity.java (David A. Velasco  2012-07-09 11:11:44 +0200 465)         
93de9f91 src/eu/alefzero/owncloud/ui/activity/PinCodeActivity.java (David A. Velasco  2012-07-09 11:11:44 +0200 466)         
93de9f91 src/eu/alefzero/owncloud/ui/activity/PinCodeActivity.java (David A. Velasco  2012-07-09 11:11:44 +0200 467)         SharedPreferences appPrefs = PreferenceManager
93de9f91 src/eu/alefzero/owncloud/ui/activity/PinCodeActivity.java (David A. Velasco  2012-07-09 11:11:44 +0200 468)                 .getDefaultSharedPreferences(getApplicationContext());
93de9f91 src/eu/alefzero/owncloud/ui/activity/PinCodeActivity.java (David A. Velasco  2012-07-09 11:11:44 +0200 469)         
93de9f91 src/eu/alefzero/owncloud/ui/activity/PinCodeActivity.java (David A. Velasco  2012-07-09 11:11:44 +0200 470)        String pText1 = appPrefs.getString("PrefPinCode1", null);
93de9f91 src/eu/alefzero/owncloud/ui/activity/PinCodeActivity.java (David A. Velasco  2012-07-09 11:11:44 +0200 471)         String pText2 = appPrefs.getString("PrefPinCode2", null);
93de9f91 src/eu/alefzero/owncloud/ui/activity/PinCodeActivity.java (David A. Velasco  2012-07-09 11:11:44 +0200 472)         String pText3 = appPrefs.getString("PrefPinCode3", null);
93de9f91 src/eu/alefzero/owncloud/ui/activity/PinCodeActivity.java (David A. Velasco  2012-07-09 11:11:44 +0200 473)         String pText4 = appPrefs.getString("PrefPinCode4", null);
93de9f91 src/eu/alefzero/owncloud/ui/activity/PinCodeActivity.java (David A. Velasco  2012-07-09 11:11:44 +0200 474) 
93de9f91 src/eu/alefzero/owncloud/ui/activity/PinCodeActivity.java (David A. Velasco  2012-07-09 11:11:44 +0200 475)         if ( tempText[0].equals(pText1) && 
93de9f91 src/eu/alefzero/owncloud/ui/activity/PinCodeActivity.java (David A. Velasco  2012-07-09 11:11:44 +0200 476)              tempText[1].equals(pText2) &&
93de9f91 src/eu/alefzero/owncloud/ui/activity/PinCodeActivity.java (David A. Velasco  2012-07-09 11:11:44 +0200 477)              tempText[2].equals(pText3) &&
93de9f91 src/eu/alefzero/owncloud/ui/activity/PinCodeActivity.java (David A. Velasco  2012-07-09 11:11:44 +0200 478)              tempText[3].equals(pText4) ) {
93de9f91 src/eu/alefzero/owncloud/ui/activity/PinCodeActivity.java (David A. Velasco  2012-07-09 11:11:44 +0200 479)             
93de9f91 src/eu/alefzero/owncloud/ui/activity/PinCodeActivity.java (David A. Velasco  2012-07-09 11:11:44 +0200 480)             return true;
93de9f91 src/eu/alefzero/owncloud/ui/activity/PinCodeActivity.java (David A. Velasco  2012-07-09 11:11:44 +0200 481)         
93de9f91 src/eu/alefzero/owncloud/ui/activity/PinCodeActivity.java (David A. Velasco  2012-07-09 11:11:44 +0200 482)         
93de9f91 src/eu/alefzero/owncloud/ui/activity/PinCodeActivity.java (David A. Velasco  2012-07-09 11:11:44 +0200 483)         }else {
e1846469 src/eu/alefzero/owncloud/ui/activity/PinCodeActivity.java (David A. Velasco  2012-07-16 12:59:25 +0200 484)             Arrays.fill(tempText, null);
93de9f91 src/eu/alefzero/owncloud/ui/activity/PinCodeActivity.java (David A. Velasco  2012-07-09 11:11:44 +0200 485)             AlertDialog aDialog = new AlertDialog.Builder(this).create();
0583bc8b src/eu/alefzero/owncloud/ui/activity/PinCodeActivity.java (David A. Velasco  2012-07-17 15:16:27 +0200 486)             CharSequence errorSeq = getString(R.string.common_error);
0583bc8b src/eu/alefzero/owncloud/ui/activity/PinCodeActivity.java (David A. Velasco  2012-07-17 15:16:27 +0200 487)             aDialog.setTitle(errorSeq);
91b17284 src/eu/alefzero/owncloud/ui/activity/PinCodeActivity.java (David A. Velasco  2012-07-13 14:46:40 +0200 488)             CharSequence cseq = getString(R.string.pincode_wrong);
91b17284 src/eu/alefzero/owncloud/ui/activity/PinCodeActivity.java (David A. Velasco  2012-07-13 14:46:40 +0200 489)             aDialog.setMessage(cseq);
0583bc8b src/eu/alefzero/owncloud/ui/activity/PinCodeActivity.java (David A. Velasco  2012-07-17 15:16:27 +0200 490)             CharSequence okSeq = getString(R.string.common_ok);
0583bc8b src/eu/alefzero/owncloud/ui/activity/PinCodeActivity.java (David A. Velasco  2012-07-17 15:16:27 +0200 491)             aDialog.setButton(okSeq, new DialogInterface.OnClickListener(){
93de9f91 src/eu/alefzero/owncloud/ui/activity/PinCodeActivity.java (David A. Velasco  2012-07-09 11:11:44 +0200 492) 
93de9f91 src/eu/alefzero/owncloud/ui/activity/PinCodeActivity.java (David A. Velasco  2012-07-09 11:11:44 +0200 493)                 @Override
93de9f91 src/eu/alefzero/owncloud/ui/activity/PinCodeActivity.java (David A. Velasco  2012-07-09 11:11:44 +0200 494)                 public void onClick(DialogInterface dialog, int which) {
93de9f91 src/eu/alefzero/owncloud/ui/activity/PinCodeActivity.java (David A. Velasco  2012-07-09 11:11:44 +0200 495)                    return; 
93de9f91 src/eu/alefzero/owncloud/ui/activity/PinCodeActivity.java (David A. Velasco  2012-07-09 11:11:44 +0200 496)                 }
93de9f91 src/eu/alefzero/owncloud/ui/activity/PinCodeActivity.java (David A. Velasco  2012-07-09 11:11:44 +0200 497)                 
93de9f91 src/eu/alefzero/owncloud/ui/activity/PinCodeActivity.java (David A. Velasco  2012-07-09 11:11:44 +0200 498)             });
93de9f91 src/eu/alefzero/owncloud/ui/activity/PinCodeActivity.java (David A. Velasco  2012-07-09 11:11:44 +0200 499)             aDialog.show();
93de9f91 src/eu/alefzero/owncloud/ui/activity/PinCodeActivity.java (David A. Velasco  2012-07-09 11:11:44 +0200 500)             clearBoxes(); 
91b17284 src/eu/alefzero/owncloud/ui/activity/PinCodeActivity.java (David A. Velasco  2012-07-13 14:46:40 +0200 501)             mPinHdr.setText(R.string.pincode_enter_pin_code);
d193e003 src/com/owncloud/android/ui/activity/PinCodeActivity.java (David A. Velasco  2012-10-17 11:04:27 +0200 502)             mPinHdrExplanation.setVisibility(View.INVISIBLE);
93de9f91 src/eu/alefzero/owncloud/ui/activity/PinCodeActivity.java (David A. Velasco  2012-07-09 11:11:44 +0200 503)             newPasswordEntered = true;
93de9f91 src/eu/alefzero/owncloud/ui/activity/PinCodeActivity.java (David A. Velasco  2012-07-09 11:11:44 +0200 504)             confirmingPinCode = false;
93de9f91 src/eu/alefzero/owncloud/ui/activity/PinCodeActivity.java (David A. Velasco  2012-07-09 11:11:44 +0200 505)             
93de9f91 src/eu/alefzero/owncloud/ui/activity/PinCodeActivity.java (David A. Velasco  2012-07-09 11:11:44 +0200 506)         }
93de9f91 src/eu/alefzero/owncloud/ui/activity/PinCodeActivity.java (David A. Velasco  2012-07-09 11:11:44 +0200 507)      
93de9f91 src/eu/alefzero/owncloud/ui/activity/PinCodeActivity.java (David A. Velasco  2012-07-09 11:11:44 +0200 508)         
93de9f91 src/eu/alefzero/owncloud/ui/activity/PinCodeActivity.java (David A. Velasco  2012-07-09 11:11:44 +0200 509)         return false;
93de9f91 src/eu/alefzero/owncloud/ui/activity/PinCodeActivity.java (David A. Velasco  2012-07-09 11:11:44 +0200 510)     }
93de9f91 src/eu/alefzero/owncloud/ui/activity/PinCodeActivity.java (David A. Velasco  2012-07-09 11:11:44 +0200 511)     
93de9f91 src/eu/alefzero/owncloud/ui/activity/PinCodeActivity.java (David A. Velasco  2012-07-09 11:11:44 +0200 512)     protected void confirmPincode(){
93de9f91 src/eu/alefzero/owncloud/ui/activity/PinCodeActivity.java (David A. Velasco  2012-07-09 11:11:44 +0200 513)         
93de9f91 src/eu/alefzero/owncloud/ui/activity/PinCodeActivity.java (David A. Velasco  2012-07-09 11:11:44 +0200 514)         confirmingPinCode = false;
93de9f91 src/eu/alefzero/owncloud/ui/activity/PinCodeActivity.java (David A. Velasco  2012-07-09 11:11:44 +0200 515)         
93de9f91 src/eu/alefzero/owncloud/ui/activity/PinCodeActivity.java (David A. Velasco  2012-07-09 11:11:44 +0200 516)         String rText1 = mText1.getText().toString();
93de9f91 src/eu/alefzero/owncloud/ui/activity/PinCodeActivity.java (David A. Velasco  2012-07-09 11:11:44 +0200 517)         String rText2 = mText2.getText().toString();
93de9f91 src/eu/alefzero/owncloud/ui/activity/PinCodeActivity.java (David A. Velasco  2012-07-09 11:11:44 +0200 518)         String rText3 = mText3.getText().toString();
93de9f91 src/eu/alefzero/owncloud/ui/activity/PinCodeActivity.java (David A. Velasco  2012-07-09 11:11:44 +0200 519)         String rText4 = mText4.getText().toString();
93de9f91 src/eu/alefzero/owncloud/ui/activity/PinCodeActivity.java (David A. Velasco  2012-07-09 11:11:44 +0200 520)         
93de9f91 src/eu/alefzero/owncloud/ui/activity/PinCodeActivity.java (David A. Velasco  2012-07-09 11:11:44 +0200 521)         if ( tempText[0].equals(rText1) && 
93de9f91 src/eu/alefzero/owncloud/ui/activity/PinCodeActivity.java (David A. Velasco  2012-07-09 11:11:44 +0200 522)              tempText[1].equals(rText2) &&
93de9f91 src/eu/alefzero/owncloud/ui/activity/PinCodeActivity.java (David A. Velasco  2012-07-09 11:11:44 +0200 523)              tempText[2].equals(rText3) &&
93de9f91 src/eu/alefzero/owncloud/ui/activity/PinCodeActivity.java (David A. Velasco  2012-07-09 11:11:44 +0200 524)              tempText[3].equals(rText4) ) {
93de9f91 src/eu/alefzero/owncloud/ui/activity/PinCodeActivity.java (David A. Velasco  2012-07-09 11:11:44 +0200 525)                         
93de9f91 src/eu/alefzero/owncloud/ui/activity/PinCodeActivity.java (David A. Velasco  2012-07-09 11:11:44 +0200 526)             savePincodeAndExit();
93de9f91 src/eu/alefzero/owncloud/ui/activity/PinCodeActivity.java (David A. Velasco  2012-07-09 11:11:44 +0200 527)             
93de9f91 src/eu/alefzero/owncloud/ui/activity/PinCodeActivity.java (David A. Velasco  2012-07-09 11:11:44 +0200 528)         } else {
93de9f91 src/eu/alefzero/owncloud/ui/activity/PinCodeActivity.java (David A. Velasco  2012-07-09 11:11:44 +0200 529)             
e1846469 src/eu/alefzero/owncloud/ui/activity/PinCodeActivity.java (David A. Velasco  2012-07-16 12:59:25 +0200 530)             Arrays.fill(tempText, null);
93de9f91 src/eu/alefzero/owncloud/ui/activity/PinCodeActivity.java (David A. Velasco  2012-07-09 11:11:44 +0200 531)             AlertDialog aDialog = new AlertDialog.Builder(this).create();
0583bc8b src/eu/alefzero/owncloud/ui/activity/PinCodeActivity.java (David A. Velasco  2012-07-17 15:16:27 +0200 532)             CharSequence errorSeq = getString(R.string.common_error);
0583bc8b src/eu/alefzero/owncloud/ui/activity/PinCodeActivity.java (David A. Velasco  2012-07-17 15:16:27 +0200 533)             aDialog.setTitle(errorSeq);
91b17284 src/eu/alefzero/owncloud/ui/activity/PinCodeActivity.java (David A. Velasco  2012-07-13 14:46:40 +0200 534)             CharSequence cseq = getString(R.string.pincode_mismatch);
91b17284 src/eu/alefzero/owncloud/ui/activity/PinCodeActivity.java (David A. Velasco  2012-07-13 14:46:40 +0200 535)             aDialog.setMessage(cseq);
0583bc8b src/eu/alefzero/owncloud/ui/activity/PinCodeActivity.java (David A. Velasco  2012-07-17 15:16:27 +0200 536)             CharSequence okSeq = getString(R.string.common_ok);
0583bc8b src/eu/alefzero/owncloud/ui/activity/PinCodeActivity.java (David A. Velasco  2012-07-17 15:16:27 +0200 537)             aDialog.setButton(okSeq, new DialogInterface.OnClickListener(){
93de9f91 src/eu/alefzero/owncloud/ui/activity/PinCodeActivity.java (David A. Velasco  2012-07-09 11:11:44 +0200 538) 
93de9f91 src/eu/alefzero/owncloud/ui/activity/PinCodeActivity.java (David A. Velasco  2012-07-09 11:11:44 +0200 539)                 @Override
93de9f91 src/eu/alefzero/owncloud/ui/activity/PinCodeActivity.java (David A. Velasco  2012-07-09 11:11:44 +0200 540)                 public void onClick(DialogInterface dialog, int which) {
93de9f91 src/eu/alefzero/owncloud/ui/activity/PinCodeActivity.java (David A. Velasco  2012-07-09 11:11:44 +0200 541)                    return; 
93de9f91 src/eu/alefzero/owncloud/ui/activity/PinCodeActivity.java (David A. Velasco  2012-07-09 11:11:44 +0200 542)                 }
93de9f91 src/eu/alefzero/owncloud/ui/activity/PinCodeActivity.java (David A. Velasco  2012-07-09 11:11:44 +0200 543)                 
93de9f91 src/eu/alefzero/owncloud/ui/activity/PinCodeActivity.java (David A. Velasco  2012-07-09 11:11:44 +0200 544)             });
93de9f91 src/eu/alefzero/owncloud/ui/activity/PinCodeActivity.java (David A. Velasco  2012-07-09 11:11:44 +0200 545)             aDialog.show();
de5d8187 src/eu/alefzero/owncloud/ui/activity/PinCodeActivity.java (David A. Velasco  2012-07-09 15:19:44 +0200 546)             mPinHdr.setText(R.string.pincode_configure_your_pin);
d193e003 src/com/owncloud/android/ui/activity/PinCodeActivity.java (David A. Velasco  2012-10-17 11:04:27 +0200 547)             mPinHdrExplanation.setVisibility(View.VISIBLE);
93de9f91 src/eu/alefzero/owncloud/ui/activity/PinCodeActivity.java (David A. Velasco  2012-07-09 11:11:44 +0200 548)             clearBoxes();
93de9f91 src/eu/alefzero/owncloud/ui/activity/PinCodeActivity.java (David A. Velasco  2012-07-09 11:11:44 +0200 549)         }
93de9f91 src/eu/alefzero/owncloud/ui/activity/PinCodeActivity.java (David A. Velasco  2012-07-09 11:11:44 +0200 550)     
93de9f91 src/eu/alefzero/owncloud/ui/activity/PinCodeActivity.java (David A. Velasco  2012-07-09 11:11:44 +0200 551)     }
93de9f91 src/eu/alefzero/owncloud/ui/activity/PinCodeActivity.java (David A. Velasco  2012-07-09 11:11:44 +0200 552)    
f757b74c src/eu/alefzero/owncloud/ui/activity/PinCodeActivity.java (David A. Velasco  2012-07-11 18:59:59 +0200 553)     
f757b74c src/eu/alefzero/owncloud/ui/activity/PinCodeActivity.java (David A. Velasco  2012-07-11 18:59:59 +0200 554)     protected void pinCodeEnd(boolean state){
f757b74c src/eu/alefzero/owncloud/ui/activity/PinCodeActivity.java (David A. Velasco  2012-07-11 18:59:59 +0200 555)         AlertDialog aDialog = new AlertDialog.Builder(this).create();
f757b74c src/eu/alefzero/owncloud/ui/activity/PinCodeActivity.java (David A. Velasco  2012-07-11 18:59:59 +0200 556)         
f757b74c src/eu/alefzero/owncloud/ui/activity/PinCodeActivity.java (David A. Velasco  2012-07-11 18:59:59 +0200 557)         if (state){
0583bc8b src/eu/alefzero/owncloud/ui/activity/PinCodeActivity.java (David A. Velasco  2012-07-17 15:16:27 +0200 558)             CharSequence saveSeq = getString(R.string.common_save_exit);
0583bc8b src/eu/alefzero/owncloud/ui/activity/PinCodeActivity.java (David A. Velasco  2012-07-17 15:16:27 +0200 559)             aDialog.setTitle(saveSeq);
0583bc8b src/eu/alefzero/owncloud/ui/activity/PinCodeActivity.java (David A. Velasco  2012-07-17 15:16:27 +0200 560)             CharSequence cseq = getString(R.string.pincode_stored);
0583bc8b src/eu/alefzero/owncloud/ui/activity/PinCodeActivity.java (David A. Velasco  2012-07-17 15:16:27 +0200 561)             aDialog.setMessage(cseq);
0583bc8b src/eu/alefzero/owncloud/ui/activity/PinCodeActivity.java (David A. Velasco  2012-07-17 15:16:27 +0200 562)             
f757b74c src/eu/alefzero/owncloud/ui/activity/PinCodeActivity.java (David A. Velasco  2012-07-11 18:59:59 +0200 563)         }else{
0583bc8b src/eu/alefzero/owncloud/ui/activity/PinCodeActivity.java (David A. Velasco  2012-07-17 15:16:27 +0200 564)             CharSequence saveSeq = getString(R.string.common_save_exit);
0583bc8b src/eu/alefzero/owncloud/ui/activity/PinCodeActivity.java (David A. Velasco  2012-07-17 15:16:27 +0200 565)             aDialog.setTitle(saveSeq);
0583bc8b src/eu/alefzero/owncloud/ui/activity/PinCodeActivity.java (David A. Velasco  2012-07-17 15:16:27 +0200 566)             CharSequence cseq = getString(R.string.pincode_removed);
0583bc8b src/eu/alefzero/owncloud/ui/activity/PinCodeActivity.java (David A. Velasco  2012-07-17 15:16:27 +0200 567)             aDialog.setMessage(cseq);
0583bc8b src/eu/alefzero/owncloud/ui/activity/PinCodeActivity.java (David A. Velasco  2012-07-17 15:16:27 +0200 568)             
f757b74c src/eu/alefzero/owncloud/ui/activity/PinCodeActivity.java (David A. Velasco  2012-07-11 18:59:59 +0200 569)         }
0583bc8b src/eu/alefzero/owncloud/ui/activity/PinCodeActivity.java (David A. Velasco  2012-07-17 15:16:27 +0200 570)         CharSequence okSeq = getString(R.string.common_ok);
0583bc8b src/eu/alefzero/owncloud/ui/activity/PinCodeActivity.java (David A. Velasco  2012-07-17 15:16:27 +0200 571)         aDialog.setButton(okSeq, new DialogInterface.OnClickListener(){
f757b74c src/eu/alefzero/owncloud/ui/activity/PinCodeActivity.java (David A. Velasco  2012-07-11 18:59:59 +0200 572) 
f757b74c src/eu/alefzero/owncloud/ui/activity/PinCodeActivity.java (David A. Velasco  2012-07-11 18:59:59 +0200 573)             @Override
f757b74c src/eu/alefzero/owncloud/ui/activity/PinCodeActivity.java (David A. Velasco  2012-07-11 18:59:59 +0200 574)             public void onClick(DialogInterface dialog, int which) {
f757b74c src/eu/alefzero/owncloud/ui/activity/PinCodeActivity.java (David A. Velasco  2012-07-11 18:59:59 +0200 575)                 finish();
f757b74c src/eu/alefzero/owncloud/ui/activity/PinCodeActivity.java (David A. Velasco  2012-07-11 18:59:59 +0200 576)                 return; 
f757b74c src/eu/alefzero/owncloud/ui/activity/PinCodeActivity.java (David A. Velasco  2012-07-11 18:59:59 +0200 577)             }
f757b74c src/eu/alefzero/owncloud/ui/activity/PinCodeActivity.java (David A. Velasco  2012-07-11 18:59:59 +0200 578)             
f757b74c src/eu/alefzero/owncloud/ui/activity/PinCodeActivity.java (David A. Velasco  2012-07-11 18:59:59 +0200 579)         });
f757b74c src/eu/alefzero/owncloud/ui/activity/PinCodeActivity.java (David A. Velasco  2012-07-11 18:59:59 +0200 580)         aDialog.show(); 
f757b74c src/eu/alefzero/owncloud/ui/activity/PinCodeActivity.java (David A. Velasco  2012-07-11 18:59:59 +0200 581)     }
f757b74c src/eu/alefzero/owncloud/ui/activity/PinCodeActivity.java (David A. Velasco  2012-07-11 18:59:59 +0200 582)     
93de9f91 src/eu/alefzero/owncloud/ui/activity/PinCodeActivity.java (David A. Velasco  2012-07-09 11:11:44 +0200 583)     protected void savePincodeAndExit(){
93de9f91 src/eu/alefzero/owncloud/ui/activity/PinCodeActivity.java (David A. Velasco  2012-07-09 11:11:44 +0200 584)         SharedPreferences.Editor appPrefs = PreferenceManager
93de9f91 src/eu/alefzero/owncloud/ui/activity/PinCodeActivity.java (David A. Velasco  2012-07-09 11:11:44 +0200 585)                 .getDefaultSharedPreferences(getApplicationContext()).edit();
93de9f91 src/eu/alefzero/owncloud/ui/activity/PinCodeActivity.java (David A. Velasco  2012-07-09 11:11:44 +0200 586)         
93de9f91 src/eu/alefzero/owncloud/ui/activity/PinCodeActivity.java (David A. Velasco  2012-07-09 11:11:44 +0200 587)         appPrefs.putString("PrefPinCode1", tempText[0]);
93de9f91 src/eu/alefzero/owncloud/ui/activity/PinCodeActivity.java (David A. Velasco  2012-07-09 11:11:44 +0200 588)         appPrefs.putString("PrefPinCode2",tempText[1]);
93de9f91 src/eu/alefzero/owncloud/ui/activity/PinCodeActivity.java (David A. Velasco  2012-07-09 11:11:44 +0200 589)         appPrefs.putString("PrefPinCode3", tempText[2]);
93de9f91 src/eu/alefzero/owncloud/ui/activity/PinCodeActivity.java (David A. Velasco  2012-07-09 11:11:44 +0200 590)         appPrefs.putString("PrefPinCode4", tempText[3]);
93de9f91 src/eu/alefzero/owncloud/ui/activity/PinCodeActivity.java (David A. Velasco  2012-07-09 11:11:44 +0200 591)         appPrefs.putBoolean("set_pincode",true);
93de9f91 src/eu/alefzero/owncloud/ui/activity/PinCodeActivity.java (David A. Velasco  2012-07-09 11:11:44 +0200 592)         appPrefs.commit();
93de9f91 src/eu/alefzero/owncloud/ui/activity/PinCodeActivity.java (David A. Velasco  2012-07-09 11:11:44 +0200 593)         
f757b74c src/eu/alefzero/owncloud/ui/activity/PinCodeActivity.java (David A. Velasco  2012-07-11 18:59:59 +0200 594)         pinCodeEnd(true);
f757b74c src/eu/alefzero/owncloud/ui/activity/PinCodeActivity.java (David A. Velasco  2012-07-11 18:59:59 +0200 595)         
f757b74c src/eu/alefzero/owncloud/ui/activity/PinCodeActivity.java (David A. Velasco  2012-07-11 18:59:59 +0200 596)         
f757b74c src/eu/alefzero/owncloud/ui/activity/PinCodeActivity.java (David A. Velasco  2012-07-11 18:59:59 +0200 597)         
93de9f91 src/eu/alefzero/owncloud/ui/activity/PinCodeActivity.java (David A. Velasco  2012-07-09 11:11:44 +0200 598)     }
93de9f91 src/eu/alefzero/owncloud/ui/activity/PinCodeActivity.java (David A. Velasco  2012-07-09 11:11:44 +0200 599)     
93de9f91 src/eu/alefzero/owncloud/ui/activity/PinCodeActivity.java (David A. Velasco  2012-07-09 11:11:44 +0200 600)     
93de9f91 src/eu/alefzero/owncloud/ui/activity/PinCodeActivity.java (David A. Velasco  2012-07-09 11:11:44 +0200 601)     protected void clearBoxes(){
93de9f91 src/eu/alefzero/owncloud/ui/activity/PinCodeActivity.java (David A. Velasco  2012-07-09 11:11:44 +0200 602)         
93de9f91 src/eu/alefzero/owncloud/ui/activity/PinCodeActivity.java (David A. Velasco  2012-07-09 11:11:44 +0200 603)         mText1.setText("");
93de9f91 src/eu/alefzero/owncloud/ui/activity/PinCodeActivity.java (David A. Velasco  2012-07-09 11:11:44 +0200 604)         mText2.setText("");
93de9f91 src/eu/alefzero/owncloud/ui/activity/PinCodeActivity.java (David A. Velasco  2012-07-09 11:11:44 +0200 605)         mText3.setText("");
93de9f91 src/eu/alefzero/owncloud/ui/activity/PinCodeActivity.java (David A. Velasco  2012-07-09 11:11:44 +0200 606)         mText4.setText("");
93de9f91 src/eu/alefzero/owncloud/ui/activity/PinCodeActivity.java (David A. Velasco  2012-07-09 11:11:44 +0200 607)         mText1.requestFocus(); 
93de9f91 src/eu/alefzero/owncloud/ui/activity/PinCodeActivity.java (David A. Velasco  2012-07-09 11:11:44 +0200 608)     }
f757b74c src/eu/alefzero/owncloud/ui/activity/PinCodeActivity.java (David A. Velasco  2012-07-11 18:59:59 +0200 609)     
f757b74c src/eu/alefzero/owncloud/ui/activity/PinCodeActivity.java (David A. Velasco  2012-07-11 18:59:59 +0200 610)     
f757b74c src/eu/alefzero/owncloud/ui/activity/PinCodeActivity.java (David A. Velasco  2012-07-11 18:59:59 +0200 611)     @Override
f757b74c src/eu/alefzero/owncloud/ui/activity/PinCodeActivity.java (David A. Velasco  2012-07-11 18:59:59 +0200 612)     public boolean onKeyDown(int keyCode, KeyEvent event){
f757b74c src/eu/alefzero/owncloud/ui/activity/PinCodeActivity.java (David A. Velasco  2012-07-11 18:59:59 +0200 613)         if (keyCode == KeyEvent.KEYCODE_BACK && event.getRepeatCount()== 0){
f757b74c src/eu/alefzero/owncloud/ui/activity/PinCodeActivity.java (David A. Velasco  2012-07-11 18:59:59 +0200 614)             
91b17284 src/eu/alefzero/owncloud/ui/activity/PinCodeActivity.java (David A. Velasco  2012-07-13 14:46:40 +0200 615)             if (activity.equals("preferences")){
91b17284 src/eu/alefzero/owncloud/ui/activity/PinCodeActivity.java (David A. Velasco  2012-07-13 14:46:40 +0200 616)                 SharedPreferences.Editor appPrefsE = PreferenceManager
91b17284 src/eu/alefzero/owncloud/ui/activity/PinCodeActivity.java (David A. Velasco  2012-07-13 14:46:40 +0200 617)             
91b17284 src/eu/alefzero/owncloud/ui/activity/PinCodeActivity.java (David A. Velasco  2012-07-13 14:46:40 +0200 618)                     .getDefaultSharedPreferences(getApplicationContext()).edit();
91b17284 src/eu/alefzero/owncloud/ui/activity/PinCodeActivity.java (David A. Velasco  2012-07-13 14:46:40 +0200 619)             
91b17284 src/eu/alefzero/owncloud/ui/activity/PinCodeActivity.java (David A. Velasco  2012-07-13 14:46:40 +0200 620)                 SharedPreferences appPrefs = PreferenceManager
91b17284 src/eu/alefzero/owncloud/ui/activity/PinCodeActivity.java (David A. Velasco  2012-07-13 14:46:40 +0200 621)                     .getDefaultSharedPreferences(getApplicationContext());
f757b74c src/eu/alefzero/owncloud/ui/activity/PinCodeActivity.java (David A. Velasco  2012-07-11 18:59:59 +0200 622)             
91b17284 src/eu/alefzero/owncloud/ui/activity/PinCodeActivity.java (David A. Velasco  2012-07-13 14:46:40 +0200 623)                 boolean state = appPrefs.getBoolean("set_pincode", false);
91b17284 src/eu/alefzero/owncloud/ui/activity/PinCodeActivity.java (David A. Velasco  2012-07-13 14:46:40 +0200 624)                 appPrefsE.putBoolean("set_pincode",!state); 
91b17284 src/eu/alefzero/owncloud/ui/activity/PinCodeActivity.java (David A. Velasco  2012-07-13 14:46:40 +0200 625)                 appPrefsE.commit();
91b17284 src/eu/alefzero/owncloud/ui/activity/PinCodeActivity.java (David A. Velasco  2012-07-13 14:46:40 +0200 626)                 setInitVars();
91b17284 src/eu/alefzero/owncloud/ui/activity/PinCodeActivity.java (David A. Velasco  2012-07-13 14:46:40 +0200 627)                 finish();
91b17284 src/eu/alefzero/owncloud/ui/activity/PinCodeActivity.java (David A. Velasco  2012-07-13 14:46:40 +0200 628)             }
f757b74c src/eu/alefzero/owncloud/ui/activity/PinCodeActivity.java (David A. Velasco  2012-07-11 18:59:59 +0200 629)             return true; 
f757b74c src/eu/alefzero/owncloud/ui/activity/PinCodeActivity.java (David A. Velasco  2012-07-11 18:59:59 +0200 630)             
f757b74c src/eu/alefzero/owncloud/ui/activity/PinCodeActivity.java (David A. Velasco  2012-07-11 18:59:59 +0200 631)         }
f757b74c src/eu/alefzero/owncloud/ui/activity/PinCodeActivity.java (David A. Velasco  2012-07-11 18:59:59 +0200 632)         
f757b74c src/eu/alefzero/owncloud/ui/activity/PinCodeActivity.java (David A. Velasco  2012-07-11 18:59:59 +0200 633)         return super.onKeyDown(keyCode, event);
f757b74c src/eu/alefzero/owncloud/ui/activity/PinCodeActivity.java (David A. Velasco  2012-07-11 18:59:59 +0200 634)     }
f757b74c src/eu/alefzero/owncloud/ui/activity/PinCodeActivity.java (David A. Velasco  2012-07-11 18:59:59 +0200 635)     
0583bc8b src/eu/alefzero/owncloud/ui/activity/PinCodeActivity.java (David A. Velasco  2012-07-17 15:16:27 +0200 636)    
0583bc8b src/eu/alefzero/owncloud/ui/activity/PinCodeActivity.java (David A. Velasco  2012-07-17 15:16:27 +0200 637) 
0583bc8b src/eu/alefzero/owncloud/ui/activity/PinCodeActivity.java (David A. Velasco  2012-07-17 15:16:27 +0200 638)     
93de9f91 src/eu/alefzero/owncloud/ui/activity/PinCodeActivity.java (David A. Velasco  2012-07-09 11:11:44 +0200 639)             
93de9f91 src/eu/alefzero/owncloud/ui/activity/PinCodeActivity.java (David A. Velasco  2012-07-09 11:11:44 +0200 640) }
