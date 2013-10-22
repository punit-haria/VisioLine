00000000 (Not Committed Yet 2013-10-18 00:25:12 -0700  1) /* ownCloud Android client application
00000000 (Not Committed Yet 2013-10-18 00:25:12 -0700  2)  *   Copyright (C) 2012-2013 ownCloud Inc.
00000000 (Not Committed Yet 2013-10-18 00:25:12 -0700  3)  *
00000000 (Not Committed Yet 2013-10-18 00:25:12 -0700  4)  *   This program is free software: you can redistribute it and/or modify
00000000 (Not Committed Yet 2013-10-18 00:25:12 -0700  5)  *   it under the terms of the GNU General Public License version 2,
00000000 (Not Committed Yet 2013-10-18 00:25:12 -0700  6)  *   as published by the Free Software Foundation.
00000000 (Not Committed Yet 2013-10-18 00:25:12 -0700  7)  *
00000000 (Not Committed Yet 2013-10-18 00:25:12 -0700  8)  *   This program is distributed in the hope that it will be useful,
00000000 (Not Committed Yet 2013-10-18 00:25:12 -0700  9)  *   but WITHOUT ANY WARRANTY; without even the implied warranty of
00000000 (Not Committed Yet 2013-10-18 00:25:12 -0700 10)  *   MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
00000000 (Not Committed Yet 2013-10-18 00:25:12 -0700 11)  *   GNU General Public License for more details.
00000000 (Not Committed Yet 2013-10-18 00:25:12 -0700 12)  *
00000000 (Not Committed Yet 2013-10-18 00:25:12 -0700 13)  *   You should have received a copy of the GNU General Public License
00000000 (Not Committed Yet 2013-10-18 00:25:12 -0700 14)  *   along with this program.  If not, see <http://www.gnu.org/licenses/>.
00000000 (Not Committed Yet 2013-10-18 00:25:12 -0700 15)  *
00000000 (Not Committed Yet 2013-10-18 00:25:12 -0700 16)  */
00000000 (Not Committed Yet 2013-10-18 00:25:12 -0700 17) 
00000000 (Not Committed Yet 2013-10-18 00:25:12 -0700 18) package com.owncloud.android.ui.activity;
00000000 (Not Committed Yet 2013-10-18 00:25:12 -0700 19) 
00000000 (Not Committed Yet 2013-10-18 00:25:12 -0700 20) import android.app.Activity;
00000000 (Not Committed Yet 2013-10-18 00:25:12 -0700 21) import android.os.Bundle;
00000000 (Not Committed Yet 2013-10-18 00:25:12 -0700 22) import android.view.View;
00000000 (Not Committed Yet 2013-10-18 00:25:12 -0700 23) import android.view.View.OnClickListener;
00000000 (Not Committed Yet 2013-10-18 00:25:12 -0700 24) import android.widget.Button;
00000000 (Not Committed Yet 2013-10-18 00:25:12 -0700 25) import android.widget.TextView;
00000000 (Not Committed Yet 2013-10-18 00:25:12 -0700 26) 
00000000 (Not Committed Yet 2013-10-18 00:25:12 -0700 27) import com.owncloud.android.R;
00000000 (Not Committed Yet 2013-10-18 00:25:12 -0700 28) 
00000000 (Not Committed Yet 2013-10-18 00:25:12 -0700 29) /**
00000000 (Not Committed Yet 2013-10-18 00:25:12 -0700 30)  * This Activity is used to display a detail message for failed uploads
00000000 (Not Committed Yet 2013-10-18 00:25:12 -0700 31)  * 
00000000 (Not Committed Yet 2013-10-18 00:25:12 -0700 32)  * The entry-point for this activity is the 'Failed upload Notification"
00000000 (Not Committed Yet 2013-10-18 00:25:12 -0700 33)  * 
00000000 (Not Committed Yet 2013-10-18 00:25:12 -0700 34)  * @author andomaex / Matthias Baumann
00000000 (Not Committed Yet 2013-10-18 00:25:12 -0700 35)  */
00000000 (Not Committed Yet 2013-10-18 00:25:12 -0700 36) public class FailedUploadActivity extends Activity {
00000000 (Not Committed Yet 2013-10-18 00:25:12 -0700 37) 
00000000 (Not Committed Yet 2013-10-18 00:25:12 -0700 38)     public static final String MESSAGE = "message";
00000000 (Not Committed Yet 2013-10-18 00:25:12 -0700 39) 
00000000 (Not Committed Yet 2013-10-18 00:25:12 -0700 40)     @Override
00000000 (Not Committed Yet 2013-10-18 00:25:12 -0700 41)     public void onCreate(Bundle savedInstanceState) {
00000000 (Not Committed Yet 2013-10-18 00:25:12 -0700 42)         super.onCreate(savedInstanceState);
00000000 (Not Committed Yet 2013-10-18 00:25:12 -0700 43)         setContentView(R.layout.failed_upload_message_view);
00000000 (Not Committed Yet 2013-10-18 00:25:12 -0700 44)         String message = getIntent().getStringExtra(MESSAGE);
00000000 (Not Committed Yet 2013-10-18 00:25:12 -0700 45)         TextView textView = (TextView) findViewById(R.id.faild_upload_message);
00000000 (Not Committed Yet 2013-10-18 00:25:12 -0700 46)         textView.setText(message);
00000000 (Not Committed Yet 2013-10-18 00:25:12 -0700 47)         Button close_button = (Button) findViewById(R.id.failed_uploadactivity_close_button);
00000000 (Not Committed Yet 2013-10-18 00:25:12 -0700 48)         close_button.setOnClickListener(new OnClickListener() {
00000000 (Not Committed Yet 2013-10-18 00:25:12 -0700 49)             @Override
00000000 (Not Committed Yet 2013-10-18 00:25:12 -0700 50)             public void onClick(View v) {
00000000 (Not Committed Yet 2013-10-18 00:25:12 -0700 51)                 finish();
00000000 (Not Committed Yet 2013-10-18 00:25:12 -0700 52)             }
00000000 (Not Committed Yet 2013-10-18 00:25:12 -0700 53)         });
00000000 (Not Committed Yet 2013-10-18 00:25:12 -0700 54)     }
00000000 (Not Committed Yet 2013-10-18 00:25:12 -0700 55) }
