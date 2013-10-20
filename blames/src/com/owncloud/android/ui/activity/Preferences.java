18a71507 (zerginator       2013-03-10 20:38:46 +0100   1) /* ownCloud Android client application
18a71507 (zerginator       2013-03-10 20:38:46 +0100   2)  *   Copyright (C) 2011  Bartek Przybylski
18a71507 (zerginator       2013-03-10 20:38:46 +0100   3)  *   Copyright (C) 2012-2013 ownCloud Inc.
18a71507 (zerginator       2013-03-10 20:38:46 +0100   4)  *
18a71507 (zerginator       2013-03-10 20:38:46 +0100   5)  *   This program is free software: you can redistribute it and/or modify
bdc0332c (David A. Velasco 2013-04-17 12:26:13 +0200   6)  *   it under the terms of the GNU General Public License version 2,
bdc0332c (David A. Velasco 2013-04-17 12:26:13 +0200   7)  *   as published by the Free Software Foundation.
18a71507 (zerginator       2013-03-10 20:38:46 +0100   8)  *
18a71507 (zerginator       2013-03-10 20:38:46 +0100   9)  *   This program is distributed in the hope that it will be useful,
18a71507 (zerginator       2013-03-10 20:38:46 +0100  10)  *   but WITHOUT ANY WARRANTY; without even the implied warranty of
18a71507 (zerginator       2013-03-10 20:38:46 +0100  11)  *   MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
18a71507 (zerginator       2013-03-10 20:38:46 +0100  12)  *   GNU General Public License for more details.
18a71507 (zerginator       2013-03-10 20:38:46 +0100  13)  *
18a71507 (zerginator       2013-03-10 20:38:46 +0100  14)  *   You should have received a copy of the GNU General Public License
18a71507 (zerginator       2013-03-10 20:38:46 +0100  15)  *   along with this program.  If not, see <http://www.gnu.org/licenses/>.
18a71507 (zerginator       2013-03-10 20:38:46 +0100  16)  *
18a71507 (zerginator       2013-03-10 20:38:46 +0100  17)  */
18a71507 (zerginator       2013-03-10 20:38:46 +0100  18) package com.owncloud.android.ui.activity;
18a71507 (zerginator       2013-03-10 20:38:46 +0100  19) 
5a22c7e7 (zerginator       2013-03-31 22:43:46 +0200  20) import java.io.File;
18a71507 (zerginator       2013-03-10 20:38:46 +0100  21) import java.util.Vector;
18a71507 (zerginator       2013-03-10 20:38:46 +0100  22) 
18a71507 (zerginator       2013-03-10 20:38:46 +0100  23) import android.content.Intent;
18a71507 (zerginator       2013-03-10 20:38:46 +0100  24) import android.content.SharedPreferences;
18a71507 (zerginator       2013-03-10 20:38:46 +0100  25) import android.content.pm.PackageInfo;
18a71507 (zerginator       2013-03-10 20:38:46 +0100  26) import android.content.pm.PackageManager.NameNotFoundException;
18a71507 (zerginator       2013-03-10 20:38:46 +0100  27) import android.os.Bundle;
5a22c7e7 (zerginator       2013-03-31 22:43:46 +0200  28) import android.os.Environment;
18a71507 (zerginator       2013-03-10 20:38:46 +0100  29) import android.preference.CheckBoxPreference;
18a71507 (zerginator       2013-03-10 20:38:46 +0100  30) import android.preference.ListPreference;
18a71507 (zerginator       2013-03-10 20:38:46 +0100  31) import android.preference.Preference;
18a71507 (zerginator       2013-03-10 20:38:46 +0100  32) import android.preference.Preference.OnPreferenceChangeListener;
18a71507 (zerginator       2013-03-10 20:38:46 +0100  33) import android.preference.Preference.OnPreferenceClickListener;
18a71507 (zerginator       2013-03-10 20:38:46 +0100  34) import android.preference.PreferenceManager;
18a71507 (zerginator       2013-03-10 20:38:46 +0100  35) 
18a71507 (zerginator       2013-03-10 20:38:46 +0100  36) import com.actionbarsherlock.app.ActionBar;
18a71507 (zerginator       2013-03-10 20:38:46 +0100  37) import com.actionbarsherlock.app.SherlockPreferenceActivity;
18a71507 (zerginator       2013-03-10 20:38:46 +0100  38) import com.actionbarsherlock.view.Menu;
18a71507 (zerginator       2013-03-10 20:38:46 +0100  39) import com.actionbarsherlock.view.MenuItem;
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100  40) import com.owncloud.android.Log_OC;
18a71507 (zerginator       2013-03-10 20:38:46 +0100  41) import com.owncloud.android.OwnCloudSession;
18a71507 (zerginator       2013-03-10 20:38:46 +0100  42) import com.owncloud.android.R;
18a71507 (zerginator       2013-03-10 20:38:46 +0100  43) import com.owncloud.android.db.DbHandler;
18a71507 (zerginator       2013-03-10 20:38:46 +0100  44) 
18a71507 (zerginator       2013-03-10 20:38:46 +0100  45) /**
18a71507 (zerginator       2013-03-10 20:38:46 +0100  46)  * An Activity that allows the user to change the application's settings.
18a71507 (zerginator       2013-03-10 20:38:46 +0100  47)  * 
18a71507 (zerginator       2013-03-10 20:38:46 +0100  48)  * @author Bartek Przybylski
18a71507 (zerginator       2013-03-10 20:38:46 +0100  49)  * 
18a71507 (zerginator       2013-03-10 20:38:46 +0100  50)  */
18a71507 (zerginator       2013-03-10 20:38:46 +0100  51) public class Preferences extends SherlockPreferenceActivity implements OnPreferenceChangeListener {
18a71507 (zerginator       2013-03-10 20:38:46 +0100  52)     
18a71507 (zerginator       2013-03-10 20:38:46 +0100  53)     private static final String TAG = "OwnCloudPreferences";
18a71507 (zerginator       2013-03-10 20:38:46 +0100  54)     private final int mNewSession = 47;
18a71507 (zerginator       2013-03-10 20:38:46 +0100  55)     private final int mEditSession = 48;
18a71507 (zerginator       2013-03-10 20:38:46 +0100  56)     private DbHandler mDbHandler;
18a71507 (zerginator       2013-03-10 20:38:46 +0100  57)     private Vector<OwnCloudSession> mSessions;
18a71507 (zerginator       2013-03-10 20:38:46 +0100  58)     private ListPreference mTrackingUpdateInterval;
18a71507 (zerginator       2013-03-10 20:38:46 +0100  59)     private CheckBoxPreference mDeviceTracking;
18a71507 (zerginator       2013-03-10 20:38:46 +0100  60)     private CheckBoxPreference pCode;
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100  61)     private CheckBoxPreference pLogging;
5a22c7e7 (zerginator       2013-03-31 22:43:46 +0200  62)     private Preference pLoggingHistory;
18a71507 (zerginator       2013-03-10 20:38:46 +0100  63)     private Preference pAboutApp;
18a71507 (zerginator       2013-03-10 20:38:46 +0100  64)     private int mSelectedMenuItem;
18a71507 (zerginator       2013-03-10 20:38:46 +0100  65) 
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100  66) 
18a71507 (zerginator       2013-03-10 20:38:46 +0100  67)     @SuppressWarnings("deprecation")
18a71507 (zerginator       2013-03-10 20:38:46 +0100  68)     @Override
18a71507 (zerginator       2013-03-10 20:38:46 +0100  69)     public void onCreate(Bundle savedInstanceState) {
18a71507 (zerginator       2013-03-10 20:38:46 +0100  70)         super.onCreate(savedInstanceState);
18a71507 (zerginator       2013-03-10 20:38:46 +0100  71)         mDbHandler = new DbHandler(getBaseContext());
18a71507 (zerginator       2013-03-10 20:38:46 +0100  72)         mSessions = new Vector<OwnCloudSession>();
18a71507 (zerginator       2013-03-10 20:38:46 +0100  73)         addPreferencesFromResource(R.xml.preferences);
18a71507 (zerginator       2013-03-10 20:38:46 +0100  74)         //populateAccountList();
18a71507 (zerginator       2013-03-10 20:38:46 +0100  75)         ActionBar actionBar = getSherlock().getActionBar();
18a71507 (zerginator       2013-03-10 20:38:46 +0100  76)         actionBar.setDisplayHomeAsUpEnabled(true);
18a71507 (zerginator       2013-03-10 20:38:46 +0100  77)         Preference p = findPreference("manage_account");
18a71507 (zerginator       2013-03-10 20:38:46 +0100  78)         if (p != null)
18a71507 (zerginator       2013-03-10 20:38:46 +0100  79)         p.setOnPreferenceClickListener(new OnPreferenceClickListener() {
18a71507 (zerginator       2013-03-10 20:38:46 +0100  80)             @Override
18a71507 (zerginator       2013-03-10 20:38:46 +0100  81)             public boolean onPreferenceClick(Preference preference) {
18a71507 (zerginator       2013-03-10 20:38:46 +0100  82)                 Intent i = new Intent(getApplicationContext(), AccountSelectActivity.class);
18a71507 (zerginator       2013-03-10 20:38:46 +0100  83)                 startActivity(i);
18a71507 (zerginator       2013-03-10 20:38:46 +0100  84)                 return true;
18a71507 (zerginator       2013-03-10 20:38:46 +0100  85)             }
18a71507 (zerginator       2013-03-10 20:38:46 +0100  86)         });
18a71507 (zerginator       2013-03-10 20:38:46 +0100  87)         
18a71507 (zerginator       2013-03-10 20:38:46 +0100  88)         pCode = (CheckBoxPreference) findPreference("set_pincode");
18a71507 (zerginator       2013-03-10 20:38:46 +0100  89)         if (pCode != null){
18a71507 (zerginator       2013-03-10 20:38:46 +0100  90)             pCode.setOnPreferenceChangeListener(new OnPreferenceChangeListener() {
18a71507 (zerginator       2013-03-10 20:38:46 +0100  91)                 @Override
18a71507 (zerginator       2013-03-10 20:38:46 +0100  92)                 public boolean onPreferenceChange(Preference preference, Object newValue) {
18a71507 (zerginator       2013-03-10 20:38:46 +0100  93)                     Intent i = new Intent(getApplicationContext(), PinCodeActivity.class);
18a71507 (zerginator       2013-03-10 20:38:46 +0100  94)                     i.putExtra(PinCodeActivity.EXTRA_ACTIVITY, "preferences");
18a71507 (zerginator       2013-03-10 20:38:46 +0100  95)                     i.putExtra(PinCodeActivity.EXTRA_NEW_STATE, newValue.toString());
18a71507 (zerginator       2013-03-10 20:38:46 +0100  96)                     startActivity(i);
18a71507 (zerginator       2013-03-10 20:38:46 +0100  97)                     return true;
18a71507 (zerginator       2013-03-10 20:38:46 +0100  98)                 }
18a71507 (zerginator       2013-03-10 20:38:46 +0100  99)             });
18a71507 (zerginator       2013-03-10 20:38:46 +0100 100)             
18a71507 (zerginator       2013-03-10 20:38:46 +0100 101)            /* About App */
18a71507 (zerginator       2013-03-10 20:38:46 +0100 102)        pAboutApp = (Preference) findPreference("about_app");
18a71507 (zerginator       2013-03-10 20:38:46 +0100 103)        if (pAboutApp != null) { 
7052e3b5 (David A. Velasco 2013-04-15 13:45:20 +0200 104)                pAboutApp.setTitle(String.format(getString(R.string.about_android), getString(R.string.app_name)));
18a71507 (zerginator       2013-03-10 20:38:46 +0100 105)                PackageInfo pkg;
18a71507 (zerginator       2013-03-10 20:38:46 +0100 106)                try {
18a71507 (zerginator       2013-03-10 20:38:46 +0100 107)                    pkg = getPackageManager().getPackageInfo(getPackageName(), 0);
7052e3b5 (David A. Velasco 2013-04-15 13:45:20 +0200 108)                    pAboutApp.setSummary(String.format(getString(R.string.about_version), pkg.versionName));
18a71507 (zerginator       2013-03-10 20:38:46 +0100 109)                } catch (NameNotFoundException e) {
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100 110)                    Log_OC.e(TAG, "Error while showing about dialog", e);
18a71507 (zerginator       2013-03-10 20:38:46 +0100 111)                }
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100 112)        }
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100 113)        
0f2a7475 (David A. Velasco 2013-06-26 09:13:28 +0200 114)        /* DISABLED FOR RELEASE UNTIL FIXED 
5a22c7e7 (zerginator       2013-03-31 22:43:46 +0200 115)        pLogging = (CheckBoxPreference) findPreference("log_to_file");
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100 116)        if (pLogging != null) {
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100 117)            pLogging.setOnPreferenceChangeListener(new OnPreferenceChangeListener() {
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100 118)                @Override
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100 119)                public boolean onPreferenceChange(Preference preference, Object newValue) {
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100 120)                    
5a22c7e7 (zerginator       2013-03-31 22:43:46 +0200 121)                    String logpath = Environment.getExternalStorageDirectory()+File.separator+"owncloud"+File.separator+"log";
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100 122)                 
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100 123)                    if(!pLogging.isChecked()) {
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100 124)                        Log_OC.d("Debug", "start logging");
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100 125)                        Log_OC.v("PATH", logpath);
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100 126)                        Log_OC.startLogging(logpath);
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100 127)                    }
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100 128)                    else {
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100 129)                        Log_OC.d("Debug", "stop logging");
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100 130)                        Log_OC.stopLogging();
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100 131)                    }
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100 132)                    return true;
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100 133)                }
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100 134)            });
5a22c7e7 (zerginator       2013-03-31 22:43:46 +0200 135)        }
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100 136)        
5a22c7e7 (zerginator       2013-03-31 22:43:46 +0200 137)        pLoggingHistory = (Preference) findPreference("log_history");
5a22c7e7 (zerginator       2013-03-31 22:43:46 +0200 138)        if (pLoggingHistory != null) {
5a22c7e7 (zerginator       2013-03-31 22:43:46 +0200 139)            pLoggingHistory.setOnPreferenceClickListener(new OnPreferenceClickListener() {
5a22c7e7 (zerginator       2013-03-31 22:43:46 +0200 140)             
5a22c7e7 (zerginator       2013-03-31 22:43:46 +0200 141)             @Override
5a22c7e7 (zerginator       2013-03-31 22:43:46 +0200 142)             public boolean onPreferenceClick(Preference preference) {
5a22c7e7 (zerginator       2013-03-31 22:43:46 +0200 143)                 Intent intent = new Intent(getApplicationContext(),LogHistoryActivity.class);
5a22c7e7 (zerginator       2013-03-31 22:43:46 +0200 144)                 startActivity(intent);
5a22c7e7 (zerginator       2013-03-31 22:43:46 +0200 145)                 return true;
5a22c7e7 (zerginator       2013-03-31 22:43:46 +0200 146)             }
5a22c7e7 (zerginator       2013-03-31 22:43:46 +0200 147)         });
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100 148)        }
0f2a7475 (David A. Velasco 2013-06-26 09:13:28 +0200 149)        */
0f2a7475 (David A. Velasco 2013-06-26 09:13:28 +0200 150)        
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100 151)       }
18a71507 (zerginator       2013-03-10 20:38:46 +0100 152)     }
18a71507 (zerginator       2013-03-10 20:38:46 +0100 153) 
18a71507 (zerginator       2013-03-10 20:38:46 +0100 154)     @Override
18a71507 (zerginator       2013-03-10 20:38:46 +0100 155)     protected void onResume() {
18a71507 (zerginator       2013-03-10 20:38:46 +0100 156)         SharedPreferences appPrefs = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
18a71507 (zerginator       2013-03-10 20:38:46 +0100 157)         boolean state = appPrefs.getBoolean("set_pincode", false);
18a71507 (zerginator       2013-03-10 20:38:46 +0100 158)         pCode.setChecked(state);
18a71507 (zerginator       2013-03-10 20:38:46 +0100 159)         super.onResume();
18a71507 (zerginator       2013-03-10 20:38:46 +0100 160)     }
18a71507 (zerginator       2013-03-10 20:38:46 +0100 161) 
18a71507 (zerginator       2013-03-10 20:38:46 +0100 162)     @Override
18a71507 (zerginator       2013-03-10 20:38:46 +0100 163)     public boolean onCreateOptionsMenu(Menu menu) {
18a71507 (zerginator       2013-03-10 20:38:46 +0100 164)         super.onCreateOptionsMenu(menu);
18a71507 (zerginator       2013-03-10 20:38:46 +0100 165)         return true;
18a71507 (zerginator       2013-03-10 20:38:46 +0100 166)     }
18a71507 (zerginator       2013-03-10 20:38:46 +0100 167) 
18a71507 (zerginator       2013-03-10 20:38:46 +0100 168)     @Override
18a71507 (zerginator       2013-03-10 20:38:46 +0100 169)     public boolean onMenuItemSelected(int featureId, MenuItem item) {
18a71507 (zerginator       2013-03-10 20:38:46 +0100 170)         super.onMenuItemSelected(featureId, item);
18a71507 (zerginator       2013-03-10 20:38:46 +0100 171)         Intent intent;
18a71507 (zerginator       2013-03-10 20:38:46 +0100 172) 
18a71507 (zerginator       2013-03-10 20:38:46 +0100 173)         switch (item.getItemId()) {
18a71507 (zerginator       2013-03-10 20:38:46 +0100 174)         //case R.id.addSessionItem:
18a71507 (zerginator       2013-03-10 20:38:46 +0100 175)         case 1:
18a71507 (zerginator       2013-03-10 20:38:46 +0100 176)             intent = new Intent(this, PreferencesNewSession.class);
18a71507 (zerginator       2013-03-10 20:38:46 +0100 177)             startActivityForResult(intent, mNewSession);
18a71507 (zerginator       2013-03-10 20:38:46 +0100 178)             break;
18a71507 (zerginator       2013-03-10 20:38:46 +0100 179)         case R.id.SessionContextEdit:
18a71507 (zerginator       2013-03-10 20:38:46 +0100 180)             intent = new Intent(this, PreferencesNewSession.class);
18a71507 (zerginator       2013-03-10 20:38:46 +0100 181)             intent.putExtra("sessionId", mSessions.get(mSelectedMenuItem)
18a71507 (zerginator       2013-03-10 20:38:46 +0100 182)                     .getEntryId());
18a71507 (zerginator       2013-03-10 20:38:46 +0100 183)             intent.putExtra("sessionName", mSessions.get(mSelectedMenuItem)
18a71507 (zerginator       2013-03-10 20:38:46 +0100 184)                     .getName());
18a71507 (zerginator       2013-03-10 20:38:46 +0100 185)             intent.putExtra("sessionURL", mSessions.get(mSelectedMenuItem)
18a71507 (zerginator       2013-03-10 20:38:46 +0100 186)                     .getUrl());
18a71507 (zerginator       2013-03-10 20:38:46 +0100 187)             startActivityForResult(intent, mEditSession);
18a71507 (zerginator       2013-03-10 20:38:46 +0100 188)             break;
18a71507 (zerginator       2013-03-10 20:38:46 +0100 189)         case android.R.id.home:
18a71507 (zerginator       2013-03-10 20:38:46 +0100 190)             intent = new Intent(getBaseContext(), FileDisplayActivity.class);
18a71507 (zerginator       2013-03-10 20:38:46 +0100 191)             intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
18a71507 (zerginator       2013-03-10 20:38:46 +0100 192)             startActivity(intent);
18a71507 (zerginator       2013-03-10 20:38:46 +0100 193)             break;
18a71507 (zerginator       2013-03-10 20:38:46 +0100 194)         default:
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100 195)             Log_OC.w(TAG, "Unknown menu item triggered");
18a71507 (zerginator       2013-03-10 20:38:46 +0100 196)             return false;
18a71507 (zerginator       2013-03-10 20:38:46 +0100 197)         }
18a71507 (zerginator       2013-03-10 20:38:46 +0100 198)         return true;
18a71507 (zerginator       2013-03-10 20:38:46 +0100 199)     }
18a71507 (zerginator       2013-03-10 20:38:46 +0100 200) 
18a71507 (zerginator       2013-03-10 20:38:46 +0100 201)     @Override
18a71507 (zerginator       2013-03-10 20:38:46 +0100 202)     protected void onActivityResult(int requestCode, int resultCode, Intent data) {
18a71507 (zerginator       2013-03-10 20:38:46 +0100 203)         super.onActivityResult(requestCode, resultCode, data);
18a71507 (zerginator       2013-03-10 20:38:46 +0100 204)     }
18a71507 (zerginator       2013-03-10 20:38:46 +0100 205) 
18a71507 (zerginator       2013-03-10 20:38:46 +0100 206)     @Override
18a71507 (zerginator       2013-03-10 20:38:46 +0100 207)     protected void onDestroy() {
18a71507 (zerginator       2013-03-10 20:38:46 +0100 208)         mDbHandler.close();
18a71507 (zerginator       2013-03-10 20:38:46 +0100 209)         super.onDestroy();
18a71507 (zerginator       2013-03-10 20:38:46 +0100 210)     }
18a71507 (zerginator       2013-03-10 20:38:46 +0100 211)     
18a71507 (zerginator       2013-03-10 20:38:46 +0100 212)     @Override
18a71507 (zerginator       2013-03-10 20:38:46 +0100 213)     /**
18a71507 (zerginator       2013-03-10 20:38:46 +0100 214)      * Updates various summaries after updates. Also starts and stops 
18a71507 (zerginator       2013-03-10 20:38:46 +0100 215)      * the
18a71507 (zerginator       2013-03-10 20:38:46 +0100 216)      */
18a71507 (zerginator       2013-03-10 20:38:46 +0100 217)     public boolean onPreferenceChange(Preference preference, Object newValue) {
18a71507 (zerginator       2013-03-10 20:38:46 +0100 218)         // Update current account summary
18a71507 (zerginator       2013-03-10 20:38:46 +0100 219)         /*if (preference.equals(mAccountList)) {
18a71507 (zerginator       2013-03-10 20:38:46 +0100 220)             mAccountList.setSummary(newValue.toString());
18a71507 (zerginator       2013-03-10 20:38:46 +0100 221)         }
18a71507 (zerginator       2013-03-10 20:38:46 +0100 222) 
18a71507 (zerginator       2013-03-10 20:38:46 +0100 223)         // Update tracking interval summary
18a71507 (zerginator       2013-03-10 20:38:46 +0100 224)         else*/ if (preference.equals(mTrackingUpdateInterval)) {
18a71507 (zerginator       2013-03-10 20:38:46 +0100 225)             String trackingSummary = getResources().getString(
18a71507 (zerginator       2013-03-10 20:38:46 +0100 226)                     R.string.prefs_trackmydevice_interval_summary);
18a71507 (zerginator       2013-03-10 20:38:46 +0100 227)             trackingSummary = String.format(trackingSummary,
18a71507 (zerginator       2013-03-10 20:38:46 +0100 228)                     newValue.toString());
18a71507 (zerginator       2013-03-10 20:38:46 +0100 229)             mTrackingUpdateInterval.setSummary(trackingSummary);
18a71507 (zerginator       2013-03-10 20:38:46 +0100 230)         }
18a71507 (zerginator       2013-03-10 20:38:46 +0100 231) 
18a71507 (zerginator       2013-03-10 20:38:46 +0100 232)         // Start or stop tracking service
18a71507 (zerginator       2013-03-10 20:38:46 +0100 233)         else if (preference.equals(mDeviceTracking)) {
18a71507 (zerginator       2013-03-10 20:38:46 +0100 234)             Intent locationServiceIntent = new Intent();
18a71507 (zerginator       2013-03-10 20:38:46 +0100 235)             locationServiceIntent
18a71507 (zerginator       2013-03-10 20:38:46 +0100 236)                     .setAction("com.owncloud.android.location.LocationLauncher");
18a71507 (zerginator       2013-03-10 20:38:46 +0100 237)             locationServiceIntent.putExtra("TRACKING_SETTING",
18a71507 (zerginator       2013-03-10 20:38:46 +0100 238)                     (Boolean) newValue);
18a71507 (zerginator       2013-03-10 20:38:46 +0100 239)             sendBroadcast(locationServiceIntent);
18a71507 (zerginator       2013-03-10 20:38:46 +0100 240)         } 
18a71507 (zerginator       2013-03-10 20:38:46 +0100 241)         return true;
18a71507 (zerginator       2013-03-10 20:38:46 +0100 242)     }
18a71507 (zerginator       2013-03-10 20:38:46 +0100 243) }
