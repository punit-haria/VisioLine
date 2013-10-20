00000000 (Not Committed Yet 2013-10-18 00:25:13 -0700   1) /* ownCloud Android client application
00000000 (Not Committed Yet 2013-10-18 00:25:13 -0700   2)  *   Copyright (C) 2011  Bartek Przybylski
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
00000000 (Not Committed Yet 2013-10-18 00:25:13 -0700  18) package com.owncloud.android.ui.adapter;
00000000 (Not Committed Yet 2013-10-18 00:25:13 -0700  19) 
00000000 (Not Committed Yet 2013-10-18 00:25:13 -0700  20) import com.owncloud.android.authentication.AccountUtils;
00000000 (Not Committed Yet 2013-10-18 00:25:13 -0700  21) import com.owncloud.android.ui.activity.FileDisplayActivity;
00000000 (Not Committed Yet 2013-10-18 00:25:13 -0700  22) import com.owncloud.android.ui.activity.Preferences;
00000000 (Not Committed Yet 2013-10-18 00:25:13 -0700  23) 
00000000 (Not Committed Yet 2013-10-18 00:25:13 -0700  24) import android.content.Context;
00000000 (Not Committed Yet 2013-10-18 00:25:13 -0700  25) import android.content.Intent;
00000000 (Not Committed Yet 2013-10-18 00:25:13 -0700  26) import android.view.LayoutInflater;
00000000 (Not Committed Yet 2013-10-18 00:25:13 -0700  27) import android.view.View;
00000000 (Not Committed Yet 2013-10-18 00:25:13 -0700  28) import android.view.ViewGroup;
00000000 (Not Committed Yet 2013-10-18 00:25:13 -0700  29) import android.widget.BaseAdapter;
00000000 (Not Committed Yet 2013-10-18 00:25:13 -0700  30) import android.widget.ImageView;
00000000 (Not Committed Yet 2013-10-18 00:25:13 -0700  31) import android.widget.TextView;
00000000 (Not Committed Yet 2013-10-18 00:25:13 -0700  32) import com.owncloud.android.R;
00000000 (Not Committed Yet 2013-10-18 00:25:13 -0700  33) 
00000000 (Not Committed Yet 2013-10-18 00:25:13 -0700  34) /**
00000000 (Not Committed Yet 2013-10-18 00:25:13 -0700  35)  * Populates the landing screen icons.
00000000 (Not Committed Yet 2013-10-18 00:25:13 -0700  36)  * 
00000000 (Not Committed Yet 2013-10-18 00:25:13 -0700  37)  * @author Lennart Rosam
00000000 (Not Committed Yet 2013-10-18 00:25:13 -0700  38)  * 
00000000 (Not Committed Yet 2013-10-18 00:25:13 -0700  39)  */
00000000 (Not Committed Yet 2013-10-18 00:25:13 -0700  40) public class LandingScreenAdapter extends BaseAdapter {
00000000 (Not Committed Yet 2013-10-18 00:25:13 -0700  41) 
00000000 (Not Committed Yet 2013-10-18 00:25:13 -0700  42)     private Context mContext;
00000000 (Not Committed Yet 2013-10-18 00:25:13 -0700  43) 
00000000 (Not Committed Yet 2013-10-18 00:25:13 -0700  44)     private final Integer[] mLandingScreenIcons = { R.drawable.home,
00000000 (Not Committed Yet 2013-10-18 00:25:13 -0700  45)             R.drawable.music, R.drawable.contacts, R.drawable.calendar,
00000000 (Not Committed Yet 2013-10-18 00:25:13 -0700  46)             android.R.drawable.ic_menu_agenda, R.drawable.settings };
00000000 (Not Committed Yet 2013-10-18 00:25:13 -0700  47) 
00000000 (Not Committed Yet 2013-10-18 00:25:13 -0700  48)     private final Integer[] mLandingScreenTexts = { R.string.main_files,
00000000 (Not Committed Yet 2013-10-18 00:25:13 -0700  49)             R.string.main_music, R.string.main_contacts,
00000000 (Not Committed Yet 2013-10-18 00:25:13 -0700  50)             R.string.main_calendar, R.string.main_bookmarks,
00000000 (Not Committed Yet 2013-10-18 00:25:13 -0700  51)             R.string.main_settings };
00000000 (Not Committed Yet 2013-10-18 00:25:13 -0700  52) 
00000000 (Not Committed Yet 2013-10-18 00:25:13 -0700  53)     public LandingScreenAdapter(Context context) {
00000000 (Not Committed Yet 2013-10-18 00:25:13 -0700  54)         mContext = context;
00000000 (Not Committed Yet 2013-10-18 00:25:13 -0700  55)     }
00000000 (Not Committed Yet 2013-10-18 00:25:13 -0700  56) 
00000000 (Not Committed Yet 2013-10-18 00:25:13 -0700  57)     @Override
00000000 (Not Committed Yet 2013-10-18 00:25:13 -0700  58)     public int getCount() {
00000000 (Not Committed Yet 2013-10-18 00:25:13 -0700  59)         return mLandingScreenIcons.length;
00000000 (Not Committed Yet 2013-10-18 00:25:13 -0700  60)     }
00000000 (Not Committed Yet 2013-10-18 00:25:13 -0700  61) 
00000000 (Not Committed Yet 2013-10-18 00:25:13 -0700  62)     @Override
00000000 (Not Committed Yet 2013-10-18 00:25:13 -0700  63)     /**
00000000 (Not Committed Yet 2013-10-18 00:25:13 -0700  64)      * Returns the Intent associated with this object
00000000 (Not Committed Yet 2013-10-18 00:25:13 -0700  65)      * or null if the functionality is not yet implemented
00000000 (Not Committed Yet 2013-10-18 00:25:13 -0700  66)      */
00000000 (Not Committed Yet 2013-10-18 00:25:13 -0700  67)     public Object getItem(int position) {
00000000 (Not Committed Yet 2013-10-18 00:25:13 -0700  68)         Intent intent = new Intent();
00000000 (Not Committed Yet 2013-10-18 00:25:13 -0700  69) 
00000000 (Not Committed Yet 2013-10-18 00:25:13 -0700  70)         switch (position) {
00000000 (Not Committed Yet 2013-10-18 00:25:13 -0700  71)         case 0:
00000000 (Not Committed Yet 2013-10-18 00:25:13 -0700  72)             /*
00000000 (Not Committed Yet 2013-10-18 00:25:13 -0700  73)              * The FileDisplayActivity requires the ownCloud account as an
00000000 (Not Committed Yet 2013-10-18 00:25:13 -0700  74)              * parcableExtra. We will put in the one that is selected in the
00000000 (Not Committed Yet 2013-10-18 00:25:13 -0700  75)              * preferences
00000000 (Not Committed Yet 2013-10-18 00:25:13 -0700  76)              */
00000000 (Not Committed Yet 2013-10-18 00:25:13 -0700  77)             intent.setClass(mContext, FileDisplayActivity.class);
00000000 (Not Committed Yet 2013-10-18 00:25:13 -0700  78)             intent.putExtra("ACCOUNT",
00000000 (Not Committed Yet 2013-10-18 00:25:13 -0700  79)                     AccountUtils.getCurrentOwnCloudAccount(mContext));
00000000 (Not Committed Yet 2013-10-18 00:25:13 -0700  80)             intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
00000000 (Not Committed Yet 2013-10-18 00:25:13 -0700  81)             break;
00000000 (Not Committed Yet 2013-10-18 00:25:13 -0700  82)         case 5:
00000000 (Not Committed Yet 2013-10-18 00:25:13 -0700  83)             intent.setClass(mContext, Preferences.class);
00000000 (Not Committed Yet 2013-10-18 00:25:13 -0700  84)             break;
00000000 (Not Committed Yet 2013-10-18 00:25:13 -0700  85)         default:
00000000 (Not Committed Yet 2013-10-18 00:25:13 -0700  86)             intent = null;
00000000 (Not Committed Yet 2013-10-18 00:25:13 -0700  87)         }
00000000 (Not Committed Yet 2013-10-18 00:25:13 -0700  88)         return intent;
00000000 (Not Committed Yet 2013-10-18 00:25:13 -0700  89)     }
00000000 (Not Committed Yet 2013-10-18 00:25:13 -0700  90) 
00000000 (Not Committed Yet 2013-10-18 00:25:13 -0700  91)     @Override
00000000 (Not Committed Yet 2013-10-18 00:25:13 -0700  92)     public long getItemId(int position) {
00000000 (Not Committed Yet 2013-10-18 00:25:13 -0700  93)         return position;
00000000 (Not Committed Yet 2013-10-18 00:25:13 -0700  94)     }
00000000 (Not Committed Yet 2013-10-18 00:25:13 -0700  95) 
00000000 (Not Committed Yet 2013-10-18 00:25:13 -0700  96)     @Override
00000000 (Not Committed Yet 2013-10-18 00:25:13 -0700  97)     public View getView(int position, View convertView, ViewGroup parent) {
00000000 (Not Committed Yet 2013-10-18 00:25:13 -0700  98)         if (convertView == null) {
00000000 (Not Committed Yet 2013-10-18 00:25:13 -0700  99)             LayoutInflater inflator = LayoutInflater.from(mContext);
00000000 (Not Committed Yet 2013-10-18 00:25:13 -0700 100)             convertView = inflator.inflate(R.layout.landing_page_item, null);
00000000 (Not Committed Yet 2013-10-18 00:25:13 -0700 101) 
00000000 (Not Committed Yet 2013-10-18 00:25:13 -0700 102)             ImageView icon = (ImageView) convertView
00000000 (Not Committed Yet 2013-10-18 00:25:13 -0700 103)                     .findViewById(R.id.gridImage);
00000000 (Not Committed Yet 2013-10-18 00:25:13 -0700 104)             TextView iconText = (TextView) convertView
00000000 (Not Committed Yet 2013-10-18 00:25:13 -0700 105)                     .findViewById(R.id.gridText);
00000000 (Not Committed Yet 2013-10-18 00:25:13 -0700 106) 
00000000 (Not Committed Yet 2013-10-18 00:25:13 -0700 107)             icon.setImageResource(mLandingScreenIcons[position]);
00000000 (Not Committed Yet 2013-10-18 00:25:13 -0700 108)             iconText.setText(mLandingScreenTexts[position]);
00000000 (Not Committed Yet 2013-10-18 00:25:13 -0700 109)         }
00000000 (Not Committed Yet 2013-10-18 00:25:13 -0700 110)         return convertView;
00000000 (Not Committed Yet 2013-10-18 00:25:13 -0700 111)     }
00000000 (Not Committed Yet 2013-10-18 00:25:13 -0700 112) }
