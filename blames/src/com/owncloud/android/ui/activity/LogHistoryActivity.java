5a22c7e7 (zerginator       2013-03-31 22:43:46 +0200   1) /* ownCloud Android client application
5a22c7e7 (zerginator       2013-03-31 22:43:46 +0200   2)  *   Copyright (C) 2012-2013 ownCloud Inc.
5a22c7e7 (zerginator       2013-03-31 22:43:46 +0200   3)  *
5a22c7e7 (zerginator       2013-03-31 22:43:46 +0200   4)  *   This program is free software: you can redistribute it and/or modify
bdc0332c (David A. Velasco 2013-04-17 12:26:13 +0200   5)  *   it under the terms of the GNU General Public License version 2,
bdc0332c (David A. Velasco 2013-04-17 12:26:13 +0200   6)  *   as published by the Free Software Foundation.
5a22c7e7 (zerginator       2013-03-31 22:43:46 +0200   7)  *
5a22c7e7 (zerginator       2013-03-31 22:43:46 +0200   8)  *   This program is distributed in the hope that it will be useful,
5a22c7e7 (zerginator       2013-03-31 22:43:46 +0200   9)  *   but WITHOUT ANY WARRANTY; without even the implied warranty of
5a22c7e7 (zerginator       2013-03-31 22:43:46 +0200  10)  *   MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
5a22c7e7 (zerginator       2013-03-31 22:43:46 +0200  11)  *   GNU General Public License for more details.
5a22c7e7 (zerginator       2013-03-31 22:43:46 +0200  12)  *
5a22c7e7 (zerginator       2013-03-31 22:43:46 +0200  13)  *   You should have received a copy of the GNU General Public License
5a22c7e7 (zerginator       2013-03-31 22:43:46 +0200  14)  *   along with this program.  If not, see <http://www.gnu.org/licenses/>.
5a22c7e7 (zerginator       2013-03-31 22:43:46 +0200  15)  *
5a22c7e7 (zerginator       2013-03-31 22:43:46 +0200  16)  */
5a22c7e7 (zerginator       2013-03-31 22:43:46 +0200  17) 
5a22c7e7 (zerginator       2013-03-31 22:43:46 +0200  18) package com.owncloud.android.ui.activity;
5a22c7e7 (zerginator       2013-03-31 22:43:46 +0200  19) 
5a22c7e7 (zerginator       2013-03-31 22:43:46 +0200  20) import java.io.File;
5a22c7e7 (zerginator       2013-03-31 22:43:46 +0200  21) import java.util.ArrayList;
5a22c7e7 (zerginator       2013-03-31 22:43:46 +0200  22) 
5a22c7e7 (zerginator       2013-03-31 22:43:46 +0200  23) import android.content.Intent;
5a22c7e7 (zerginator       2013-03-31 22:43:46 +0200  24) import android.os.Bundle;
5a22c7e7 (zerginator       2013-03-31 22:43:46 +0200  25) import android.preference.Preference;
5a22c7e7 (zerginator       2013-03-31 22:43:46 +0200  26) import android.preference.Preference.OnPreferenceChangeListener;
5a22c7e7 (zerginator       2013-03-31 22:43:46 +0200  27) import android.view.View;
5a22c7e7 (zerginator       2013-03-31 22:43:46 +0200  28) import android.view.View.OnClickListener;
5a22c7e7 (zerginator       2013-03-31 22:43:46 +0200  29) import android.widget.Button;
5a22c7e7 (zerginator       2013-03-31 22:43:46 +0200  30) import android.widget.ListView;
5a22c7e7 (zerginator       2013-03-31 22:43:46 +0200  31) 
5a22c7e7 (zerginator       2013-03-31 22:43:46 +0200  32) import com.actionbarsherlock.app.ActionBar;
5a22c7e7 (zerginator       2013-03-31 22:43:46 +0200  33) import com.actionbarsherlock.app.SherlockPreferenceActivity;
5a22c7e7 (zerginator       2013-03-31 22:43:46 +0200  34) import com.actionbarsherlock.view.MenuItem;
5a22c7e7 (zerginator       2013-03-31 22:43:46 +0200  35) import com.owncloud.android.R;
5a22c7e7 (zerginator       2013-03-31 22:43:46 +0200  36) import com.owncloud.android.ui.adapter.LogListAdapter;
1003ecb7 (David A. Velasco 2013-04-15 13:32:49 +0200  37) import com.owncloud.android.utils.FileStorageUtils;
5a22c7e7 (zerginator       2013-03-31 22:43:46 +0200  38) 
5a22c7e7 (zerginator       2013-03-31 22:43:46 +0200  39) 
5a22c7e7 (zerginator       2013-03-31 22:43:46 +0200  40) 
5a22c7e7 (zerginator       2013-03-31 22:43:46 +0200  41) public class LogHistoryActivity extends SherlockPreferenceActivity implements OnPreferenceChangeListener {
1003ecb7 (David A. Velasco 2013-04-15 13:32:49 +0200  42)     String logpath = FileStorageUtils.getLogPath();
5a22c7e7 (zerginator       2013-03-31 22:43:46 +0200  43)     File logDIR = null;
5a22c7e7 (zerginator       2013-03-31 22:43:46 +0200  44)     
5a22c7e7 (zerginator       2013-03-31 22:43:46 +0200  45)     @Override
5a22c7e7 (zerginator       2013-03-31 22:43:46 +0200  46)     protected void onCreate(Bundle savedInstanceState) {
5a22c7e7 (zerginator       2013-03-31 22:43:46 +0200  47)         super.onCreate(savedInstanceState);
5a22c7e7 (zerginator       2013-03-31 22:43:46 +0200  48)         
5a22c7e7 (zerginator       2013-03-31 22:43:46 +0200  49)         setContentView(R.layout.log_send_file);
5a22c7e7 (zerginator       2013-03-31 22:43:46 +0200  50)         setTitle("Log History");
5a22c7e7 (zerginator       2013-03-31 22:43:46 +0200  51)         ActionBar actionBar = getSherlock().getActionBar();
5a22c7e7 (zerginator       2013-03-31 22:43:46 +0200  52)         actionBar.setDisplayHomeAsUpEnabled(true);
5a22c7e7 (zerginator       2013-03-31 22:43:46 +0200  53)         ListView listView = (ListView) findViewById(android.R.id.list);
5a22c7e7 (zerginator       2013-03-31 22:43:46 +0200  54)         Button deleteHistoryButton = (Button) findViewById(R.id.deleteLogHistoryButton);
5a22c7e7 (zerginator       2013-03-31 22:43:46 +0200  55)         deleteHistoryButton.setOnClickListener(new OnClickListener() {
5a22c7e7 (zerginator       2013-03-31 22:43:46 +0200  56)             
5a22c7e7 (zerginator       2013-03-31 22:43:46 +0200  57)             @Override
5a22c7e7 (zerginator       2013-03-31 22:43:46 +0200  58)             public void onClick(View v) {
5a22c7e7 (zerginator       2013-03-31 22:43:46 +0200  59)                 File dir = new File(logpath);
5a22c7e7 (zerginator       2013-03-31 22:43:46 +0200  60)                 if (dir != null) {
5a22c7e7 (zerginator       2013-03-31 22:43:46 +0200  61)                     File[] files = dir.listFiles();
5a22c7e7 (zerginator       2013-03-31 22:43:46 +0200  62)                     if(files!=null) { 
5a22c7e7 (zerginator       2013-03-31 22:43:46 +0200  63)                         for(File f: files) {
5a22c7e7 (zerginator       2013-03-31 22:43:46 +0200  64)                                 f.delete();
5a22c7e7 (zerginator       2013-03-31 22:43:46 +0200  65)                         }
5a22c7e7 (zerginator       2013-03-31 22:43:46 +0200  66)                     }
5a22c7e7 (zerginator       2013-03-31 22:43:46 +0200  67)                     dir.delete();
5a22c7e7 (zerginator       2013-03-31 22:43:46 +0200  68)                 }
5a22c7e7 (zerginator       2013-03-31 22:43:46 +0200  69)                 Intent intent = new Intent(getBaseContext(), Preferences.class);
5a22c7e7 (zerginator       2013-03-31 22:43:46 +0200  70)                 intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
5a22c7e7 (zerginator       2013-03-31 22:43:46 +0200  71)                 startActivity(intent);
5a22c7e7 (zerginator       2013-03-31 22:43:46 +0200  72)             }
5a22c7e7 (zerginator       2013-03-31 22:43:46 +0200  73)             
5a22c7e7 (zerginator       2013-03-31 22:43:46 +0200  74)         });
5a22c7e7 (zerginator       2013-03-31 22:43:46 +0200  75)         
5a22c7e7 (zerginator       2013-03-31 22:43:46 +0200  76)        
5a22c7e7 (zerginator       2013-03-31 22:43:46 +0200  77)         if(logpath != null){
5a22c7e7 (zerginator       2013-03-31 22:43:46 +0200  78)         logDIR = new File(logpath);
5a22c7e7 (zerginator       2013-03-31 22:43:46 +0200  79)         }
5a22c7e7 (zerginator       2013-03-31 22:43:46 +0200  80)         
5a22c7e7 (zerginator       2013-03-31 22:43:46 +0200  81)         if(logDIR != null && logDIR.isDirectory()) {
5a22c7e7 (zerginator       2013-03-31 22:43:46 +0200  82)             File[] files = logDIR.listFiles();
5a22c7e7 (zerginator       2013-03-31 22:43:46 +0200  83)           
5a22c7e7 (zerginator       2013-03-31 22:43:46 +0200  84)             if (files != null && files.length != 0) {
5a22c7e7 (zerginator       2013-03-31 22:43:46 +0200  85)                 ArrayList<String> logfiles_name = new ArrayList<String>();
5a22c7e7 (zerginator       2013-03-31 22:43:46 +0200  86)                 for (File file : files) {
5a22c7e7 (zerginator       2013-03-31 22:43:46 +0200  87)                     logfiles_name.add(file.getName());
5a22c7e7 (zerginator       2013-03-31 22:43:46 +0200  88)                 }
5a22c7e7 (zerginator       2013-03-31 22:43:46 +0200  89)                     String[] logFiles2Array = logfiles_name.toArray(new String[logfiles_name.size()]);
5a22c7e7 (zerginator       2013-03-31 22:43:46 +0200  90)                     LogListAdapter listadapter = new LogListAdapter(this,logFiles2Array);
5a22c7e7 (zerginator       2013-03-31 22:43:46 +0200  91)                     listView.setAdapter(listadapter);
5a22c7e7 (zerginator       2013-03-31 22:43:46 +0200  92)             }
5a22c7e7 (zerginator       2013-03-31 22:43:46 +0200  93)         }
5a22c7e7 (zerginator       2013-03-31 22:43:46 +0200  94)     }
5a22c7e7 (zerginator       2013-03-31 22:43:46 +0200  95) 
5a22c7e7 (zerginator       2013-03-31 22:43:46 +0200  96)     
5a22c7e7 (zerginator       2013-03-31 22:43:46 +0200  97)     @Override
5a22c7e7 (zerginator       2013-03-31 22:43:46 +0200  98)     public boolean onMenuItemSelected(int featureId, MenuItem item) {
5a22c7e7 (zerginator       2013-03-31 22:43:46 +0200  99)         super.onMenuItemSelected(featureId, item);
5a22c7e7 (zerginator       2013-03-31 22:43:46 +0200 100)         Intent intent;
5a22c7e7 (zerginator       2013-03-31 22:43:46 +0200 101) 
5a22c7e7 (zerginator       2013-03-31 22:43:46 +0200 102)         switch (item.getItemId()) {
5a22c7e7 (zerginator       2013-03-31 22:43:46 +0200 103)        
5a22c7e7 (zerginator       2013-03-31 22:43:46 +0200 104)         case android.R.id.home:
5a22c7e7 (zerginator       2013-03-31 22:43:46 +0200 105)             intent = new Intent(getBaseContext(), Preferences.class);
5a22c7e7 (zerginator       2013-03-31 22:43:46 +0200 106)             intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
5a22c7e7 (zerginator       2013-03-31 22:43:46 +0200 107)             startActivity(intent);
5a22c7e7 (zerginator       2013-03-31 22:43:46 +0200 108)             break;
5a22c7e7 (zerginator       2013-03-31 22:43:46 +0200 109)         default:
5a22c7e7 (zerginator       2013-03-31 22:43:46 +0200 110)             return false;
5a22c7e7 (zerginator       2013-03-31 22:43:46 +0200 111)         }
5a22c7e7 (zerginator       2013-03-31 22:43:46 +0200 112)         return true;
5a22c7e7 (zerginator       2013-03-31 22:43:46 +0200 113)     }
5a22c7e7 (zerginator       2013-03-31 22:43:46 +0200 114)     @Override
5a22c7e7 (zerginator       2013-03-31 22:43:46 +0200 115)     public boolean onPreferenceChange(Preference arg0, Object arg1) {
5a22c7e7 (zerginator       2013-03-31 22:43:46 +0200 116)         return false;
5a22c7e7 (zerginator       2013-03-31 22:43:46 +0200 117)     }
5a22c7e7 (zerginator       2013-03-31 22:43:46 +0200 118) }
