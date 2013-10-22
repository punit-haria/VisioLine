f7649804 (Matthias Baumann 2013-02-25 08:21:33 +0100   1) /* ownCloud Android client application
f7649804 (Matthias Baumann 2013-02-25 08:21:33 +0100   2)  *   Copyright (C) 2012-2013 ownCloud Inc.
f7649804 (Matthias Baumann 2013-02-25 08:21:33 +0100   3)  *
f7649804 (Matthias Baumann 2013-02-25 08:21:33 +0100   4)  *   This program is free software: you can redistribute it and/or modify
2946d8dd (David A. Velasco 2013-04-25 19:39:22 +0200   5)  *   it under the terms of the GNU General Public License version 2,
2946d8dd (David A. Velasco 2013-04-25 19:39:22 +0200   6)  *   as published by the Free Software Foundation.
f7649804 (Matthias Baumann 2013-02-25 08:21:33 +0100   7)  *
f7649804 (Matthias Baumann 2013-02-25 08:21:33 +0100   8)  *   This program is distributed in the hope that it will be useful,
f7649804 (Matthias Baumann 2013-02-25 08:21:33 +0100   9)  *   but WITHOUT ANY WARRANTY; without even the implied warranty of
f7649804 (Matthias Baumann 2013-02-25 08:21:33 +0100  10)  *   MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
f7649804 (Matthias Baumann 2013-02-25 08:21:33 +0100  11)  *   GNU General Public License for more details.
f7649804 (Matthias Baumann 2013-02-25 08:21:33 +0100  12)  *
f7649804 (Matthias Baumann 2013-02-25 08:21:33 +0100  13)  *   You should have received a copy of the GNU General Public License
f7649804 (Matthias Baumann 2013-02-25 08:21:33 +0100  14)  *   along with this program.  If not, see <http://www.gnu.org/licenses/>.
f7649804 (Matthias Baumann 2013-02-25 08:21:33 +0100  15)  *
f7649804 (Matthias Baumann 2013-02-25 08:21:33 +0100  16)  */
f7649804 (Matthias Baumann 2013-02-25 08:21:33 +0100  17) package com.owncloud.android.ui.activity;
f7649804 (Matthias Baumann 2013-02-25 08:21:33 +0100  18) 
f7649804 (Matthias Baumann 2013-02-25 08:21:33 +0100  19) import java.util.ArrayList;
f7649804 (Matthias Baumann 2013-02-25 08:21:33 +0100  20) import java.util.List;
f7649804 (Matthias Baumann 2013-02-25 08:21:33 +0100  21) 
f7649804 (Matthias Baumann 2013-02-25 08:21:33 +0100  22) import android.accounts.Account;
f7649804 (Matthias Baumann 2013-02-25 08:21:33 +0100  23) import android.app.Activity;
f7649804 (Matthias Baumann 2013-02-25 08:21:33 +0100  24) import android.content.Intent;
f7649804 (Matthias Baumann 2013-02-25 08:21:33 +0100  25) import android.database.Cursor;
f7649804 (Matthias Baumann 2013-02-25 08:21:33 +0100  26) import android.graphics.Bitmap;
f7649804 (Matthias Baumann 2013-02-25 08:21:33 +0100  27) import android.graphics.BitmapFactory;
f7649804 (Matthias Baumann 2013-02-25 08:21:33 +0100  28) import android.os.Bundle;
f7649804 (Matthias Baumann 2013-02-25 08:21:33 +0100  29) import android.util.SparseArray;
f7649804 (Matthias Baumann 2013-02-25 08:21:33 +0100  30) import android.view.Gravity;
f7649804 (Matthias Baumann 2013-02-25 08:21:33 +0100  31) import android.view.View;
f7649804 (Matthias Baumann 2013-02-25 08:21:33 +0100  32) import android.view.View.OnClickListener;
7392cfe5 (Matthias Baumann 2013-03-16 18:56:27 +0100  33) import android.view.View.OnLongClickListener;
f7649804 (Matthias Baumann 2013-02-25 08:21:33 +0100  34) import android.view.ViewGroup;
f7649804 (Matthias Baumann 2013-02-25 08:21:33 +0100  35) import android.widget.Button;
f7649804 (Matthias Baumann 2013-02-25 08:21:33 +0100  36) import android.widget.CheckBox;
f7649804 (Matthias Baumann 2013-02-25 08:21:33 +0100  37) import android.widget.CompoundButton;
f7649804 (Matthias Baumann 2013-02-25 08:21:33 +0100  38) import android.widget.CompoundButton.OnCheckedChangeListener;
f7649804 (Matthias Baumann 2013-02-25 08:21:33 +0100  39) import android.widget.ImageButton;
f7649804 (Matthias Baumann 2013-02-25 08:21:33 +0100  40) import android.widget.LinearLayout;
7392cfe5 (Matthias Baumann 2013-03-16 18:56:27 +0100  41) import android.widget.TextView;
f7649804 (Matthias Baumann 2013-02-25 08:21:33 +0100  42) import android.widget.Toast;
f7649804 (Matthias Baumann 2013-02-25 08:21:33 +0100  43) 
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100  44) import com.owncloud.android.Log_OC;
f7649804 (Matthias Baumann 2013-02-25 08:21:33 +0100  45) import com.owncloud.android.R;
c4ac05de (David A. Velasco 2013-06-18 11:34:08 +0200  46) import com.owncloud.android.authentication.AccountUtils;
f7649804 (Matthias Baumann 2013-02-25 08:21:33 +0100  47) import com.owncloud.android.db.DbHandler;
f7649804 (Matthias Baumann 2013-02-25 08:21:33 +0100  48) import com.owncloud.android.files.InstantUploadBroadcastReceiver;
f7649804 (Matthias Baumann 2013-02-25 08:21:33 +0100  49) import com.owncloud.android.files.services.FileUploader;
7392cfe5 (Matthias Baumann 2013-03-16 18:56:27 +0100  50) import com.owncloud.android.utils.FileStorageUtils;
f7649804 (Matthias Baumann 2013-02-25 08:21:33 +0100  51) 
f7649804 (Matthias Baumann 2013-02-25 08:21:33 +0100  52) /**
f7649804 (Matthias Baumann 2013-02-25 08:21:33 +0100  53)  * This Activity is used to display a list with images they could not be
f7649804 (Matthias Baumann 2013-02-25 08:21:33 +0100  54)  * uploaded instantly. The images can be selected for delete or for a try again
f7649804 (Matthias Baumann 2013-02-25 08:21:33 +0100  55)  * upload
f7649804 (Matthias Baumann 2013-02-25 08:21:33 +0100  56)  * 
6c5f5613 (Matthias Baumann 2013-03-17 10:38:42 +0100  57)  * The entry-point for this activity is the 'Failed upload Notification" and a
6c5f5613 (Matthias Baumann 2013-03-17 10:38:42 +0100  58)  * sub-menu underneath the 'Upload' menu-item
f7649804 (Matthias Baumann 2013-02-25 08:21:33 +0100  59)  * 
f7649804 (Matthias Baumann 2013-02-25 08:21:33 +0100  60)  * @author andomaex / Matthias Baumann
f7649804 (Matthias Baumann 2013-02-25 08:21:33 +0100  61)  */
f7649804 (Matthias Baumann 2013-02-25 08:21:33 +0100  62) public class InstantUploadActivity extends Activity {
f7649804 (Matthias Baumann 2013-02-25 08:21:33 +0100  63) 
f7649804 (Matthias Baumann 2013-02-25 08:21:33 +0100  64)     private static final String LOG_TAG = InstantUploadActivity.class.getSimpleName();
f7649804 (Matthias Baumann 2013-02-25 08:21:33 +0100  65)     private LinearLayout listView;
f7649804 (Matthias Baumann 2013-02-25 08:21:33 +0100  66)     private static final String retry_chexbox_tag = "retry_chexbox_tag";
9b758dfa (David A. Velasco 2013-04-04 17:15:53 +0200  67)     public static final boolean IS_ENABLED = false;
f7649804 (Matthias Baumann 2013-02-25 08:21:33 +0100  68)     private static int MAX_LOAD_IMAGES = 5;
f7649804 (Matthias Baumann 2013-02-25 08:21:33 +0100  69)     private int lastLoadImageIdx = 0;
f7649804 (Matthias Baumann 2013-02-25 08:21:33 +0100  70) 
f7649804 (Matthias Baumann 2013-02-25 08:21:33 +0100  71)     private SparseArray<String> fileList = null;
7392cfe5 (Matthias Baumann 2013-03-16 18:56:27 +0100  72)     CheckBox failed_upload_all_cb;
f7649804 (Matthias Baumann 2013-02-25 08:21:33 +0100  73) 
f7649804 (Matthias Baumann 2013-02-25 08:21:33 +0100  74)     @Override
f7649804 (Matthias Baumann 2013-02-25 08:21:33 +0100  75)     protected void onCreate(Bundle savedInstanceState) {
f7649804 (Matthias Baumann 2013-02-25 08:21:33 +0100  76)         super.onCreate(savedInstanceState);
f7649804 (Matthias Baumann 2013-02-25 08:21:33 +0100  77)         setContentView(R.layout.failed_upload_files);
f7649804 (Matthias Baumann 2013-02-25 08:21:33 +0100  78) 
f7649804 (Matthias Baumann 2013-02-25 08:21:33 +0100  79)         Button delete_all_btn = (Button) findViewById(R.id.failed_upload_delete_all_btn);
f7649804 (Matthias Baumann 2013-02-25 08:21:33 +0100  80)         delete_all_btn.setOnClickListener(getDeleteListner());
f7649804 (Matthias Baumann 2013-02-25 08:21:33 +0100  81)         Button retry_all_btn = (Button) findViewById(R.id.failed_upload_retry_all_btn);
f7649804 (Matthias Baumann 2013-02-25 08:21:33 +0100  82)         retry_all_btn.setOnClickListener(getRetryListner());
7392cfe5 (Matthias Baumann 2013-03-16 18:56:27 +0100  83)         this.failed_upload_all_cb = (CheckBox) findViewById(R.id.failed_upload_headline_cb);
f7649804 (Matthias Baumann 2013-02-25 08:21:33 +0100  84)         failed_upload_all_cb.setOnCheckedChangeListener(getCheckAllListener());
f7649804 (Matthias Baumann 2013-02-25 08:21:33 +0100  85)         listView = (LinearLayout) findViewById(R.id.failed_upload_scrollviewlayout);
f7649804 (Matthias Baumann 2013-02-25 08:21:33 +0100  86) 
f7649804 (Matthias Baumann 2013-02-25 08:21:33 +0100  87)         loadListView(true);
f7649804 (Matthias Baumann 2013-02-25 08:21:33 +0100  88) 
f7649804 (Matthias Baumann 2013-02-25 08:21:33 +0100  89)     }
f7649804 (Matthias Baumann 2013-02-25 08:21:33 +0100  90) 
f7649804 (Matthias Baumann 2013-02-25 08:21:33 +0100  91)     /**
f7649804 (Matthias Baumann 2013-02-25 08:21:33 +0100  92)      * init the listview with ImageButtons, checkboxes and filename for every
f7649804 (Matthias Baumann 2013-02-25 08:21:33 +0100  93)      * Image that was not successfully uploaded
f7649804 (Matthias Baumann 2013-02-25 08:21:33 +0100  94)      * 
f7649804 (Matthias Baumann 2013-02-25 08:21:33 +0100  95)      * this method is call at Activity creation and on delete one ore more
f7649804 (Matthias Baumann 2013-02-25 08:21:33 +0100  96)      * list-entry an on retry the upload by clicking the ImageButton or by click
f7649804 (Matthias Baumann 2013-02-25 08:21:33 +0100  97)      * to the 'retry all' button
f7649804 (Matthias Baumann 2013-02-25 08:21:33 +0100  98)      * 
f7649804 (Matthias Baumann 2013-02-25 08:21:33 +0100  99)      */
f7649804 (Matthias Baumann 2013-02-25 08:21:33 +0100 100)     private void loadListView(boolean reset) {
f7649804 (Matthias Baumann 2013-02-25 08:21:33 +0100 101)         DbHandler db = new DbHandler(getApplicationContext());
f7649804 (Matthias Baumann 2013-02-25 08:21:33 +0100 102)         Cursor c = db.getFailedFiles();
f7649804 (Matthias Baumann 2013-02-25 08:21:33 +0100 103) 
f7649804 (Matthias Baumann 2013-02-25 08:21:33 +0100 104)         if (reset) {
f7649804 (Matthias Baumann 2013-02-25 08:21:33 +0100 105)             fileList = new SparseArray<String>();
f7649804 (Matthias Baumann 2013-02-25 08:21:33 +0100 106)             listView.removeAllViews();
f7649804 (Matthias Baumann 2013-02-25 08:21:33 +0100 107)             lastLoadImageIdx = 0;
f7649804 (Matthias Baumann 2013-02-25 08:21:33 +0100 108)         }
f7649804 (Matthias Baumann 2013-02-25 08:21:33 +0100 109)         if (c != null) {
f7649804 (Matthias Baumann 2013-02-25 08:21:33 +0100 110)             try {
f7649804 (Matthias Baumann 2013-02-25 08:21:33 +0100 111)                 c.moveToPosition(lastLoadImageIdx);
f7649804 (Matthias Baumann 2013-02-25 08:21:33 +0100 112) 
f7649804 (Matthias Baumann 2013-02-25 08:21:33 +0100 113)                 while (c.moveToNext()) {
f7649804 (Matthias Baumann 2013-02-25 08:21:33 +0100 114) 
f7649804 (Matthias Baumann 2013-02-25 08:21:33 +0100 115)                     lastLoadImageIdx++;
f7649804 (Matthias Baumann 2013-02-25 08:21:33 +0100 116)                     String imp_path = c.getString(1);
7392cfe5 (Matthias Baumann 2013-03-16 18:56:27 +0100 117)                     String message = c.getString(4);
f7649804 (Matthias Baumann 2013-02-25 08:21:33 +0100 118)                     fileList.put(lastLoadImageIdx, imp_path);
7392cfe5 (Matthias Baumann 2013-03-16 18:56:27 +0100 119)                     LinearLayout rowLayout = getHorizontalLinearLayout(lastLoadImageIdx);
f7649804 (Matthias Baumann 2013-02-25 08:21:33 +0100 120)                     rowLayout.addView(getFileCheckbox(lastLoadImageIdx));
f7649804 (Matthias Baumann 2013-02-25 08:21:33 +0100 121)                     rowLayout.addView(getImageButton(imp_path, lastLoadImageIdx));
7392cfe5 (Matthias Baumann 2013-03-16 18:56:27 +0100 122)                     rowLayout.addView(getFileButton(imp_path, message, lastLoadImageIdx));
f7649804 (Matthias Baumann 2013-02-25 08:21:33 +0100 123)                     listView.addView(rowLayout);
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100 124)                     Log_OC.d(LOG_TAG, imp_path + " on idx: " + lastLoadImageIdx);
f7649804 (Matthias Baumann 2013-02-25 08:21:33 +0100 125)                     if (lastLoadImageIdx % MAX_LOAD_IMAGES == 0) {
f7649804 (Matthias Baumann 2013-02-25 08:21:33 +0100 126)                         break;
f7649804 (Matthias Baumann 2013-02-25 08:21:33 +0100 127)                     }
f7649804 (Matthias Baumann 2013-02-25 08:21:33 +0100 128)                 }
f7649804 (Matthias Baumann 2013-02-25 08:21:33 +0100 129)                 if (lastLoadImageIdx > 0) {
f7649804 (Matthias Baumann 2013-02-25 08:21:33 +0100 130)                     addLoadMoreButton(listView);
f7649804 (Matthias Baumann 2013-02-25 08:21:33 +0100 131)                 }
f7649804 (Matthias Baumann 2013-02-25 08:21:33 +0100 132)             } finally {
f7649804 (Matthias Baumann 2013-02-25 08:21:33 +0100 133)                 db.close();
f7649804 (Matthias Baumann 2013-02-25 08:21:33 +0100 134)             }
f7649804 (Matthias Baumann 2013-02-25 08:21:33 +0100 135)         }
f7649804 (Matthias Baumann 2013-02-25 08:21:33 +0100 136)     }
f7649804 (Matthias Baumann 2013-02-25 08:21:33 +0100 137) 
f7649804 (Matthias Baumann 2013-02-25 08:21:33 +0100 138)     private void addLoadMoreButton(LinearLayout listView) {
f7649804 (Matthias Baumann 2013-02-25 08:21:33 +0100 139)         if (listView != null) {
f7649804 (Matthias Baumann 2013-02-25 08:21:33 +0100 140)             Button loadmoreBtn = null;
f7649804 (Matthias Baumann 2013-02-25 08:21:33 +0100 141)             View oldButton = listView.findViewById(42);
f7649804 (Matthias Baumann 2013-02-25 08:21:33 +0100 142)             if (oldButton != null) {
f7649804 (Matthias Baumann 2013-02-25 08:21:33 +0100 143)                 // remove existing button
f7649804 (Matthias Baumann 2013-02-25 08:21:33 +0100 144)                 listView.removeView(oldButton);
f7649804 (Matthias Baumann 2013-02-25 08:21:33 +0100 145)                 // to add the button at the end
f7649804 (Matthias Baumann 2013-02-25 08:21:33 +0100 146)                 loadmoreBtn = (Button) oldButton;
f7649804 (Matthias Baumann 2013-02-25 08:21:33 +0100 147)             } else {
f7649804 (Matthias Baumann 2013-02-25 08:21:33 +0100 148)                 // create a new button to add to the scoll view
f7649804 (Matthias Baumann 2013-02-25 08:21:33 +0100 149)                 loadmoreBtn = new Button(this);
f7649804 (Matthias Baumann 2013-02-25 08:21:33 +0100 150)                 loadmoreBtn.setId(42);
f7649804 (Matthias Baumann 2013-02-25 08:21:33 +0100 151)                 loadmoreBtn.setText(getString(R.string.failed_upload_load_more_images));
f7649804 (Matthias Baumann 2013-02-25 08:21:33 +0100 152)                 loadmoreBtn.setBackgroundResource(R.color.owncloud_white);
f7649804 (Matthias Baumann 2013-02-25 08:21:33 +0100 153)                 loadmoreBtn.setTextSize(12);
f7649804 (Matthias Baumann 2013-02-25 08:21:33 +0100 154)                 loadmoreBtn.setOnClickListener(new OnClickListener() {
f7649804 (Matthias Baumann 2013-02-25 08:21:33 +0100 155)                     @Override
f7649804 (Matthias Baumann 2013-02-25 08:21:33 +0100 156)                     public void onClick(View v) {
f7649804 (Matthias Baumann 2013-02-25 08:21:33 +0100 157)                         loadListView(false);
f7649804 (Matthias Baumann 2013-02-25 08:21:33 +0100 158)                     }
f7649804 (Matthias Baumann 2013-02-25 08:21:33 +0100 159) 
f7649804 (Matthias Baumann 2013-02-25 08:21:33 +0100 160)                 });
f7649804 (Matthias Baumann 2013-02-25 08:21:33 +0100 161)             }
f7649804 (Matthias Baumann 2013-02-25 08:21:33 +0100 162)             listView.addView(loadmoreBtn);
f7649804 (Matthias Baumann 2013-02-25 08:21:33 +0100 163)         }
f7649804 (Matthias Baumann 2013-02-25 08:21:33 +0100 164)     }
f7649804 (Matthias Baumann 2013-02-25 08:21:33 +0100 165) 
f7649804 (Matthias Baumann 2013-02-25 08:21:33 +0100 166)     /**
f7649804 (Matthias Baumann 2013-02-25 08:21:33 +0100 167)      * provide a list of CheckBox instances, looked up from parent listview this
f7649804 (Matthias Baumann 2013-02-25 08:21:33 +0100 168)      * list ist used to select/deselect all checkboxes at the list
f7649804 (Matthias Baumann 2013-02-25 08:21:33 +0100 169)      * 
f7649804 (Matthias Baumann 2013-02-25 08:21:33 +0100 170)      * @return List<CheckBox>
f7649804 (Matthias Baumann 2013-02-25 08:21:33 +0100 171)      */
f7649804 (Matthias Baumann 2013-02-25 08:21:33 +0100 172)     private List<CheckBox> getCheckboxList() {
f7649804 (Matthias Baumann 2013-02-25 08:21:33 +0100 173)         List<CheckBox> list = new ArrayList<CheckBox>();
f7649804 (Matthias Baumann 2013-02-25 08:21:33 +0100 174)         for (int i = 0; i < listView.getChildCount(); i++) {
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100 175)             Log_OC.d(LOG_TAG, "ListView has Childs: " + listView.getChildCount());
f7649804 (Matthias Baumann 2013-02-25 08:21:33 +0100 176)             View childView = listView.getChildAt(i);
f7649804 (Matthias Baumann 2013-02-25 08:21:33 +0100 177)             if (childView != null && childView instanceof ViewGroup) {
f7649804 (Matthias Baumann 2013-02-25 08:21:33 +0100 178)                 View checkboxView = getChildViews((ViewGroup) childView);
f7649804 (Matthias Baumann 2013-02-25 08:21:33 +0100 179)                 if (checkboxView != null && checkboxView instanceof CheckBox) {
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100 180)                     Log_OC.d(LOG_TAG, "found Child: " + checkboxView.getId() + " " + checkboxView.getClass());
f7649804 (Matthias Baumann 2013-02-25 08:21:33 +0100 181)                     list.add((CheckBox) checkboxView);
f7649804 (Matthias Baumann 2013-02-25 08:21:33 +0100 182)                 }
f7649804 (Matthias Baumann 2013-02-25 08:21:33 +0100 183)             }
f7649804 (Matthias Baumann 2013-02-25 08:21:33 +0100 184)         }
f7649804 (Matthias Baumann 2013-02-25 08:21:33 +0100 185)         return list;
f7649804 (Matthias Baumann 2013-02-25 08:21:33 +0100 186)     }
f7649804 (Matthias Baumann 2013-02-25 08:21:33 +0100 187) 
f7649804 (Matthias Baumann 2013-02-25 08:21:33 +0100 188)     /**
f7649804 (Matthias Baumann 2013-02-25 08:21:33 +0100 189)      * recursive called method, used from getCheckboxList method
f7649804 (Matthias Baumann 2013-02-25 08:21:33 +0100 190)      * 
f7649804 (Matthias Baumann 2013-02-25 08:21:33 +0100 191)      * @param View
f7649804 (Matthias Baumann 2013-02-25 08:21:33 +0100 192)      * @return View
f7649804 (Matthias Baumann 2013-02-25 08:21:33 +0100 193)      */
f7649804 (Matthias Baumann 2013-02-25 08:21:33 +0100 194)     private View getChildViews(ViewGroup view) {
f7649804 (Matthias Baumann 2013-02-25 08:21:33 +0100 195)         if (view != null) {
f7649804 (Matthias Baumann 2013-02-25 08:21:33 +0100 196)             for (int i = 0; i < view.getChildCount(); i++) {
f7649804 (Matthias Baumann 2013-02-25 08:21:33 +0100 197)                 View cb = view.getChildAt(i);
f7649804 (Matthias Baumann 2013-02-25 08:21:33 +0100 198)                 if (cb != null && cb instanceof ViewGroup) {
f7649804 (Matthias Baumann 2013-02-25 08:21:33 +0100 199)                     return getChildViews((ViewGroup) cb);
f7649804 (Matthias Baumann 2013-02-25 08:21:33 +0100 200)                 } else if (cb instanceof CheckBox) {
f7649804 (Matthias Baumann 2013-02-25 08:21:33 +0100 201)                     return cb;
f7649804 (Matthias Baumann 2013-02-25 08:21:33 +0100 202)                 }
f7649804 (Matthias Baumann 2013-02-25 08:21:33 +0100 203)             }
f7649804 (Matthias Baumann 2013-02-25 08:21:33 +0100 204)         }
f7649804 (Matthias Baumann 2013-02-25 08:21:33 +0100 205)         return null;
f7649804 (Matthias Baumann 2013-02-25 08:21:33 +0100 206)     }
f7649804 (Matthias Baumann 2013-02-25 08:21:33 +0100 207) 
f7649804 (Matthias Baumann 2013-02-25 08:21:33 +0100 208)     /**
f7649804 (Matthias Baumann 2013-02-25 08:21:33 +0100 209)      * create a new OnCheckedChangeListener for the 'check all' checkbox *
f7649804 (Matthias Baumann 2013-02-25 08:21:33 +0100 210)      * 
f7649804 (Matthias Baumann 2013-02-25 08:21:33 +0100 211)      * @return OnCheckedChangeListener to select all checkboxes at the list
f7649804 (Matthias Baumann 2013-02-25 08:21:33 +0100 212)      */
f7649804 (Matthias Baumann 2013-02-25 08:21:33 +0100 213)     private OnCheckedChangeListener getCheckAllListener() {
f7649804 (Matthias Baumann 2013-02-25 08:21:33 +0100 214)         return new OnCheckedChangeListener() {
f7649804 (Matthias Baumann 2013-02-25 08:21:33 +0100 215)             @Override
f7649804 (Matthias Baumann 2013-02-25 08:21:33 +0100 216)             public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
f7649804 (Matthias Baumann 2013-02-25 08:21:33 +0100 217)                 List<CheckBox> list = getCheckboxList();
f7649804 (Matthias Baumann 2013-02-25 08:21:33 +0100 218)                 for (CheckBox checkbox : list) {
f7649804 (Matthias Baumann 2013-02-25 08:21:33 +0100 219)                     ((CheckBox) checkbox).setChecked(isChecked);
f7649804 (Matthias Baumann 2013-02-25 08:21:33 +0100 220)                 }
f7649804 (Matthias Baumann 2013-02-25 08:21:33 +0100 221)             }
f7649804 (Matthias Baumann 2013-02-25 08:21:33 +0100 222) 
f7649804 (Matthias Baumann 2013-02-25 08:21:33 +0100 223)         };
f7649804 (Matthias Baumann 2013-02-25 08:21:33 +0100 224)     }
f7649804 (Matthias Baumann 2013-02-25 08:21:33 +0100 225) 
f7649804 (Matthias Baumann 2013-02-25 08:21:33 +0100 226)     /**
f7649804 (Matthias Baumann 2013-02-25 08:21:33 +0100 227)      * Button click Listener for the retry button at the headline
f7649804 (Matthias Baumann 2013-02-25 08:21:33 +0100 228)      * 
f7649804 (Matthias Baumann 2013-02-25 08:21:33 +0100 229)      * @return a Listener to perform a retry for all selected images
f7649804 (Matthias Baumann 2013-02-25 08:21:33 +0100 230)      */
f7649804 (Matthias Baumann 2013-02-25 08:21:33 +0100 231)     private OnClickListener getRetryListner() {
f7649804 (Matthias Baumann 2013-02-25 08:21:33 +0100 232)         return new OnClickListener() {
f7649804 (Matthias Baumann 2013-02-25 08:21:33 +0100 233) 
f7649804 (Matthias Baumann 2013-02-25 08:21:33 +0100 234)             @Override
f7649804 (Matthias Baumann 2013-02-25 08:21:33 +0100 235)             public void onClick(View v) {
f7649804 (Matthias Baumann 2013-02-25 08:21:33 +0100 236) 
f7649804 (Matthias Baumann 2013-02-25 08:21:33 +0100 237)                 try {
7392cfe5 (Matthias Baumann 2013-03-16 18:56:27 +0100 238) 
f7649804 (Matthias Baumann 2013-02-25 08:21:33 +0100 239)                     List<CheckBox> list = getCheckboxList();
f7649804 (Matthias Baumann 2013-02-25 08:21:33 +0100 240)                     for (CheckBox checkbox : list) {
f7649804 (Matthias Baumann 2013-02-25 08:21:33 +0100 241)                         boolean to_retry = checkbox.isChecked();
f7649804 (Matthias Baumann 2013-02-25 08:21:33 +0100 242) 
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100 243)                         Log_OC.d(LOG_TAG, "Checkbox for " + checkbox.getId() + " was checked: " + to_retry);
f7649804 (Matthias Baumann 2013-02-25 08:21:33 +0100 244)                         String img_path = fileList.get(checkbox.getId());
f7649804 (Matthias Baumann 2013-02-25 08:21:33 +0100 245)                         if (to_retry) {
f7649804 (Matthias Baumann 2013-02-25 08:21:33 +0100 246) 
f7649804 (Matthias Baumann 2013-02-25 08:21:33 +0100 247)                             final String msg = "Image-Path " + checkbox.getId() + " was checked: " + img_path;
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100 248)                             Log_OC.d(LOG_TAG, msg);
f7649804 (Matthias Baumann 2013-02-25 08:21:33 +0100 249)                             startUpload(img_path);
f7649804 (Matthias Baumann 2013-02-25 08:21:33 +0100 250)                         }
f7649804 (Matthias Baumann 2013-02-25 08:21:33 +0100 251) 
f7649804 (Matthias Baumann 2013-02-25 08:21:33 +0100 252)                     }
f7649804 (Matthias Baumann 2013-02-25 08:21:33 +0100 253)                 } finally {
f7649804 (Matthias Baumann 2013-02-25 08:21:33 +0100 254)                     // refresh the List
f7649804 (Matthias Baumann 2013-02-25 08:21:33 +0100 255)                     listView.removeAllViews();
f7649804 (Matthias Baumann 2013-02-25 08:21:33 +0100 256)                     loadListView(true);
7392cfe5 (Matthias Baumann 2013-03-16 18:56:27 +0100 257)                     if (failed_upload_all_cb != null) {
7392cfe5 (Matthias Baumann 2013-03-16 18:56:27 +0100 258)                         failed_upload_all_cb.setChecked(false);
7392cfe5 (Matthias Baumann 2013-03-16 18:56:27 +0100 259)                     }
f7649804 (Matthias Baumann 2013-02-25 08:21:33 +0100 260)                 }
f7649804 (Matthias Baumann 2013-02-25 08:21:33 +0100 261) 
f7649804 (Matthias Baumann 2013-02-25 08:21:33 +0100 262)             }
f7649804 (Matthias Baumann 2013-02-25 08:21:33 +0100 263)         };
f7649804 (Matthias Baumann 2013-02-25 08:21:33 +0100 264)     }
f7649804 (Matthias Baumann 2013-02-25 08:21:33 +0100 265) 
f7649804 (Matthias Baumann 2013-02-25 08:21:33 +0100 266)     /**
f7649804 (Matthias Baumann 2013-02-25 08:21:33 +0100 267)      * Button click Listener for the delete button at the headline
f7649804 (Matthias Baumann 2013-02-25 08:21:33 +0100 268)      * 
f7649804 (Matthias Baumann 2013-02-25 08:21:33 +0100 269)      * @return a Listener to perform a delete for all selected images
f7649804 (Matthias Baumann 2013-02-25 08:21:33 +0100 270)      */
f7649804 (Matthias Baumann 2013-02-25 08:21:33 +0100 271)     private OnClickListener getDeleteListner() {
f7649804 (Matthias Baumann 2013-02-25 08:21:33 +0100 272) 
f7649804 (Matthias Baumann 2013-02-25 08:21:33 +0100 273)         return new OnClickListener() {
f7649804 (Matthias Baumann 2013-02-25 08:21:33 +0100 274) 
f7649804 (Matthias Baumann 2013-02-25 08:21:33 +0100 275)             @Override
f7649804 (Matthias Baumann 2013-02-25 08:21:33 +0100 276)             public void onClick(View v) {
7392cfe5 (Matthias Baumann 2013-03-16 18:56:27 +0100 277) 
f7649804 (Matthias Baumann 2013-02-25 08:21:33 +0100 278)                 final DbHandler dbh = new DbHandler(getApplicationContext());
f7649804 (Matthias Baumann 2013-02-25 08:21:33 +0100 279)                 try {
f7649804 (Matthias Baumann 2013-02-25 08:21:33 +0100 280)                     List<CheckBox> list = getCheckboxList();
f7649804 (Matthias Baumann 2013-02-25 08:21:33 +0100 281)                     for (CheckBox checkbox : list) {
f7649804 (Matthias Baumann 2013-02-25 08:21:33 +0100 282)                         boolean to_be_delete = checkbox.isChecked();
f7649804 (Matthias Baumann 2013-02-25 08:21:33 +0100 283) 
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100 284)                         Log_OC.d(LOG_TAG, "Checkbox for " + checkbox.getId() + " was checked: " + to_be_delete);
f7649804 (Matthias Baumann 2013-02-25 08:21:33 +0100 285)                         String img_path = fileList.get(checkbox.getId());
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100 286)                         Log_OC.d(LOG_TAG, "Image-Path " + checkbox.getId() + " was checked: " + img_path);
f7649804 (Matthias Baumann 2013-02-25 08:21:33 +0100 287)                         if (to_be_delete) {
f7649804 (Matthias Baumann 2013-02-25 08:21:33 +0100 288)                             boolean deleted = dbh.removeIUPendingFile(img_path);
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100 289)                             Log_OC.d(LOG_TAG, "removing " + checkbox.getId() + " was : " + deleted);
f7649804 (Matthias Baumann 2013-02-25 08:21:33 +0100 290) 
f7649804 (Matthias Baumann 2013-02-25 08:21:33 +0100 291)                         }
f7649804 (Matthias Baumann 2013-02-25 08:21:33 +0100 292) 
f7649804 (Matthias Baumann 2013-02-25 08:21:33 +0100 293)                     }
f7649804 (Matthias Baumann 2013-02-25 08:21:33 +0100 294)                 } finally {
f7649804 (Matthias Baumann 2013-02-25 08:21:33 +0100 295)                     dbh.close();
f7649804 (Matthias Baumann 2013-02-25 08:21:33 +0100 296)                     // refresh the List
f7649804 (Matthias Baumann 2013-02-25 08:21:33 +0100 297)                     listView.removeAllViews();
f7649804 (Matthias Baumann 2013-02-25 08:21:33 +0100 298)                     loadListView(true);
7392cfe5 (Matthias Baumann 2013-03-16 18:56:27 +0100 299)                     if (failed_upload_all_cb != null) {
7392cfe5 (Matthias Baumann 2013-03-16 18:56:27 +0100 300)                         failed_upload_all_cb.setChecked(false);
7392cfe5 (Matthias Baumann 2013-03-16 18:56:27 +0100 301)                     }
f7649804 (Matthias Baumann 2013-02-25 08:21:33 +0100 302)                 }
f7649804 (Matthias Baumann 2013-02-25 08:21:33 +0100 303) 
f7649804 (Matthias Baumann 2013-02-25 08:21:33 +0100 304)             }
f7649804 (Matthias Baumann 2013-02-25 08:21:33 +0100 305)         };
f7649804 (Matthias Baumann 2013-02-25 08:21:33 +0100 306)     }
f7649804 (Matthias Baumann 2013-02-25 08:21:33 +0100 307) 
7392cfe5 (Matthias Baumann 2013-03-16 18:56:27 +0100 308)     private LinearLayout getHorizontalLinearLayout(int id) {
f7649804 (Matthias Baumann 2013-02-25 08:21:33 +0100 309)         LinearLayout linearLayout = new LinearLayout(getApplicationContext());
f7649804 (Matthias Baumann 2013-02-25 08:21:33 +0100 310)         linearLayout.setId(id);
f7649804 (Matthias Baumann 2013-02-25 08:21:33 +0100 311)         linearLayout.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT,
f7649804 (Matthias Baumann 2013-02-25 08:21:33 +0100 312)                 LinearLayout.LayoutParams.MATCH_PARENT));
f7649804 (Matthias Baumann 2013-02-25 08:21:33 +0100 313)         linearLayout.setGravity(Gravity.RIGHT);
f7649804 (Matthias Baumann 2013-02-25 08:21:33 +0100 314)         linearLayout.setOrientation(LinearLayout.HORIZONTAL);
f7649804 (Matthias Baumann 2013-02-25 08:21:33 +0100 315)         return linearLayout;
f7649804 (Matthias Baumann 2013-02-25 08:21:33 +0100 316)     }
f7649804 (Matthias Baumann 2013-02-25 08:21:33 +0100 317) 
7392cfe5 (Matthias Baumann 2013-03-16 18:56:27 +0100 318)     private LinearLayout getVerticalLinearLayout() {
7392cfe5 (Matthias Baumann 2013-03-16 18:56:27 +0100 319)         LinearLayout linearLayout = new LinearLayout(getApplicationContext());
7392cfe5 (Matthias Baumann 2013-03-16 18:56:27 +0100 320)         linearLayout.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT,
7392cfe5 (Matthias Baumann 2013-03-16 18:56:27 +0100 321)                 LinearLayout.LayoutParams.MATCH_PARENT));
7392cfe5 (Matthias Baumann 2013-03-16 18:56:27 +0100 322)         linearLayout.setGravity(Gravity.TOP);
7392cfe5 (Matthias Baumann 2013-03-16 18:56:27 +0100 323)         linearLayout.setOrientation(LinearLayout.VERTICAL);
7392cfe5 (Matthias Baumann 2013-03-16 18:56:27 +0100 324)         return linearLayout;
7392cfe5 (Matthias Baumann 2013-03-16 18:56:27 +0100 325)     }
7392cfe5 (Matthias Baumann 2013-03-16 18:56:27 +0100 326) 
7392cfe5 (Matthias Baumann 2013-03-16 18:56:27 +0100 327)     private View getFileButton(final String img_path, String message, int id) {
7392cfe5 (Matthias Baumann 2013-03-16 18:56:27 +0100 328) 
7392cfe5 (Matthias Baumann 2013-03-16 18:56:27 +0100 329)         TextView failureTextView = new TextView(this);
7392cfe5 (Matthias Baumann 2013-03-16 18:56:27 +0100 330)         failureTextView.setText(getString(R.string.failed_upload_failure_text) + message);
7392cfe5 (Matthias Baumann 2013-03-16 18:56:27 +0100 331)         failureTextView.setBackgroundResource(R.color.owncloud_white);
7392cfe5 (Matthias Baumann 2013-03-16 18:56:27 +0100 332)         failureTextView.setTextSize(8);
7392cfe5 (Matthias Baumann 2013-03-16 18:56:27 +0100 333)         failureTextView.setOnLongClickListener(getOnLongClickListener(message));
7392cfe5 (Matthias Baumann 2013-03-16 18:56:27 +0100 334)         failureTextView.setPadding(5, 5, 5, 10);
7392cfe5 (Matthias Baumann 2013-03-16 18:56:27 +0100 335)         TextView retryButton = new TextView(this);
f7649804 (Matthias Baumann 2013-02-25 08:21:33 +0100 336)         retryButton.setId(id);
f7649804 (Matthias Baumann 2013-02-25 08:21:33 +0100 337)         retryButton.setText(img_path);
f7649804 (Matthias Baumann 2013-02-25 08:21:33 +0100 338)         retryButton.setBackgroundResource(R.color.owncloud_white);
f7649804 (Matthias Baumann 2013-02-25 08:21:33 +0100 339)         retryButton.setTextSize(8);
f7649804 (Matthias Baumann 2013-02-25 08:21:33 +0100 340)         retryButton.setOnClickListener(getImageButtonOnClickListener(img_path));
7392cfe5 (Matthias Baumann 2013-03-16 18:56:27 +0100 341)         retryButton.setOnLongClickListener(getOnLongClickListener(message));
7392cfe5 (Matthias Baumann 2013-03-16 18:56:27 +0100 342)         retryButton.setPadding(5, 5, 5, 10);
7392cfe5 (Matthias Baumann 2013-03-16 18:56:27 +0100 343)         LinearLayout verticalLayout = getVerticalLinearLayout();
7392cfe5 (Matthias Baumann 2013-03-16 18:56:27 +0100 344)         verticalLayout.addView(retryButton);
7392cfe5 (Matthias Baumann 2013-03-16 18:56:27 +0100 345)         verticalLayout.addView(failureTextView);
7392cfe5 (Matthias Baumann 2013-03-16 18:56:27 +0100 346) 
7392cfe5 (Matthias Baumann 2013-03-16 18:56:27 +0100 347)         return verticalLayout;
7392cfe5 (Matthias Baumann 2013-03-16 18:56:27 +0100 348)     }
7392cfe5 (Matthias Baumann 2013-03-16 18:56:27 +0100 349) 
7392cfe5 (Matthias Baumann 2013-03-16 18:56:27 +0100 350)     private OnLongClickListener getOnLongClickListener(final String message) {
7392cfe5 (Matthias Baumann 2013-03-16 18:56:27 +0100 351)         return new OnLongClickListener() {
7392cfe5 (Matthias Baumann 2013-03-16 18:56:27 +0100 352) 
7392cfe5 (Matthias Baumann 2013-03-16 18:56:27 +0100 353)             @Override
7392cfe5 (Matthias Baumann 2013-03-16 18:56:27 +0100 354)             public boolean onLongClick(View v) {
2946d8dd (David A. Velasco 2013-04-25 19:39:22 +0200 355)                 Log_OC.d(LOG_TAG, message);
7392cfe5 (Matthias Baumann 2013-03-16 18:56:27 +0100 356)                 Toast toast = Toast.makeText(InstantUploadActivity.this, getString(R.string.failed_upload_retry_text)
7392cfe5 (Matthias Baumann 2013-03-16 18:56:27 +0100 357)                         + message, Toast.LENGTH_LONG);
7392cfe5 (Matthias Baumann 2013-03-16 18:56:27 +0100 358)                 toast.show();
7392cfe5 (Matthias Baumann 2013-03-16 18:56:27 +0100 359)                 return true;
7392cfe5 (Matthias Baumann 2013-03-16 18:56:27 +0100 360)             }
7392cfe5 (Matthias Baumann 2013-03-16 18:56:27 +0100 361) 
7392cfe5 (Matthias Baumann 2013-03-16 18:56:27 +0100 362)         };
f7649804 (Matthias Baumann 2013-02-25 08:21:33 +0100 363)     }
f7649804 (Matthias Baumann 2013-02-25 08:21:33 +0100 364) 
f7649804 (Matthias Baumann 2013-02-25 08:21:33 +0100 365)     private CheckBox getFileCheckbox(int id) {
f7649804 (Matthias Baumann 2013-02-25 08:21:33 +0100 366)         CheckBox retryCB = new CheckBox(this);
f7649804 (Matthias Baumann 2013-02-25 08:21:33 +0100 367)         retryCB.setId(id);
f7649804 (Matthias Baumann 2013-02-25 08:21:33 +0100 368)         retryCB.setBackgroundResource(R.color.owncloud_white);
f7649804 (Matthias Baumann 2013-02-25 08:21:33 +0100 369)         retryCB.setTextSize(8);
f7649804 (Matthias Baumann 2013-02-25 08:21:33 +0100 370)         retryCB.setTag(retry_chexbox_tag);
f7649804 (Matthias Baumann 2013-02-25 08:21:33 +0100 371)         return retryCB;
f7649804 (Matthias Baumann 2013-02-25 08:21:33 +0100 372)     }
f7649804 (Matthias Baumann 2013-02-25 08:21:33 +0100 373) 
f7649804 (Matthias Baumann 2013-02-25 08:21:33 +0100 374)     private ImageButton getImageButton(String img_path, int id) {
f7649804 (Matthias Baumann 2013-02-25 08:21:33 +0100 375)         ImageButton imageButton = new ImageButton(this);
f7649804 (Matthias Baumann 2013-02-25 08:21:33 +0100 376)         imageButton.setId(id);
f7649804 (Matthias Baumann 2013-02-25 08:21:33 +0100 377)         imageButton.setClickable(true);
f7649804 (Matthias Baumann 2013-02-25 08:21:33 +0100 378)         imageButton.setOnClickListener(getImageButtonOnClickListener(img_path));
f7649804 (Matthias Baumann 2013-02-25 08:21:33 +0100 379) 
f7649804 (Matthias Baumann 2013-02-25 08:21:33 +0100 380)         // scale and add a thumbnail to the imagebutton
f7649804 (Matthias Baumann 2013-02-25 08:21:33 +0100 381)         int base_scale_size = 32;
f7649804 (Matthias Baumann 2013-02-25 08:21:33 +0100 382)         if (img_path != null) {
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100 383)             Log_OC.d(LOG_TAG, "add " + img_path + " to Image Button");
f7649804 (Matthias Baumann 2013-02-25 08:21:33 +0100 384)             BitmapFactory.Options options = new BitmapFactory.Options();
f7649804 (Matthias Baumann 2013-02-25 08:21:33 +0100 385)             options.inJustDecodeBounds = true;
f7649804 (Matthias Baumann 2013-02-25 08:21:33 +0100 386)             Bitmap bitmap = BitmapFactory.decodeFile(img_path, options);
f7649804 (Matthias Baumann 2013-02-25 08:21:33 +0100 387)             int width_tpm = options.outWidth, height_tmp = options.outHeight;
f7649804 (Matthias Baumann 2013-02-25 08:21:33 +0100 388)             int scale = 3;
f7649804 (Matthias Baumann 2013-02-25 08:21:33 +0100 389)             while (true) {
f7649804 (Matthias Baumann 2013-02-25 08:21:33 +0100 390)                 if (width_tpm / 2 < base_scale_size || height_tmp / 2 < base_scale_size) {
f7649804 (Matthias Baumann 2013-02-25 08:21:33 +0100 391)                     break;
f7649804 (Matthias Baumann 2013-02-25 08:21:33 +0100 392)                 }
f7649804 (Matthias Baumann 2013-02-25 08:21:33 +0100 393)                 width_tpm /= 2;
f7649804 (Matthias Baumann 2013-02-25 08:21:33 +0100 394)                 height_tmp /= 2;
f7649804 (Matthias Baumann 2013-02-25 08:21:33 +0100 395)                 scale++;
f7649804 (Matthias Baumann 2013-02-25 08:21:33 +0100 396)             }
f7649804 (Matthias Baumann 2013-02-25 08:21:33 +0100 397) 
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100 398)             Log_OC.d(LOG_TAG, "scale Imgae with: " + scale);
f7649804 (Matthias Baumann 2013-02-25 08:21:33 +0100 399)             BitmapFactory.Options options2 = new BitmapFactory.Options();
f7649804 (Matthias Baumann 2013-02-25 08:21:33 +0100 400)             options2.inSampleSize = scale;
f7649804 (Matthias Baumann 2013-02-25 08:21:33 +0100 401)             bitmap = BitmapFactory.decodeFile(img_path, options2);
f7649804 (Matthias Baumann 2013-02-25 08:21:33 +0100 402) 
f7649804 (Matthias Baumann 2013-02-25 08:21:33 +0100 403)             if (bitmap != null) {
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100 404)                 Log_OC.d(LOG_TAG, "loaded Bitmap Bytes: " + bitmap.getRowBytes());
f7649804 (Matthias Baumann 2013-02-25 08:21:33 +0100 405)                 imageButton.setImageBitmap(bitmap);
f7649804 (Matthias Baumann 2013-02-25 08:21:33 +0100 406)             } else {
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100 407)                 Log_OC.d(LOG_TAG, "could not load imgage: " + img_path);
f7649804 (Matthias Baumann 2013-02-25 08:21:33 +0100 408)             }
f7649804 (Matthias Baumann 2013-02-25 08:21:33 +0100 409)         }
f7649804 (Matthias Baumann 2013-02-25 08:21:33 +0100 410)         return imageButton;
f7649804 (Matthias Baumann 2013-02-25 08:21:33 +0100 411)     }
f7649804 (Matthias Baumann 2013-02-25 08:21:33 +0100 412) 
f7649804 (Matthias Baumann 2013-02-25 08:21:33 +0100 413)     private OnClickListener getImageButtonOnClickListener(final String img_path) {
f7649804 (Matthias Baumann 2013-02-25 08:21:33 +0100 414)         return new OnClickListener() {
f7649804 (Matthias Baumann 2013-02-25 08:21:33 +0100 415) 
f7649804 (Matthias Baumann 2013-02-25 08:21:33 +0100 416)             @Override
f7649804 (Matthias Baumann 2013-02-25 08:21:33 +0100 417)             public void onClick(View v) {
f7649804 (Matthias Baumann 2013-02-25 08:21:33 +0100 418)                 startUpload(img_path);
f7649804 (Matthias Baumann 2013-02-25 08:21:33 +0100 419)                 loadListView(true);
f7649804 (Matthias Baumann 2013-02-25 08:21:33 +0100 420)             }
f7649804 (Matthias Baumann 2013-02-25 08:21:33 +0100 421) 
f7649804 (Matthias Baumann 2013-02-25 08:21:33 +0100 422)         };
f7649804 (Matthias Baumann 2013-02-25 08:21:33 +0100 423)     }
f7649804 (Matthias Baumann 2013-02-25 08:21:33 +0100 424) 
f7649804 (Matthias Baumann 2013-02-25 08:21:33 +0100 425)     /**
f7649804 (Matthias Baumann 2013-02-25 08:21:33 +0100 426)      * start uploading a file to the INSTANT_UPLOD_DIR
f7649804 (Matthias Baumann 2013-02-25 08:21:33 +0100 427)      * 
f7649804 (Matthias Baumann 2013-02-25 08:21:33 +0100 428)      * @param img_path
f7649804 (Matthias Baumann 2013-02-25 08:21:33 +0100 429)      */
f7649804 (Matthias Baumann 2013-02-25 08:21:33 +0100 430)     private void startUpload(String img_path) {
f7649804 (Matthias Baumann 2013-02-25 08:21:33 +0100 431)         // extract filename
9ad30bdd (David A. Velasco 2013-04-17 18:00:31 +0200 432)         String filename = FileStorageUtils.getInstantUploadFilePath(this, img_path);
f7649804 (Matthias Baumann 2013-02-25 08:21:33 +0100 433)         if (canInstantUpload()) {
f7649804 (Matthias Baumann 2013-02-25 08:21:33 +0100 434)             Account account = AccountUtils.getCurrentOwnCloudAccount(InstantUploadActivity.this);
f7649804 (Matthias Baumann 2013-02-25 08:21:33 +0100 435)             // add file again to upload queue
f7649804 (Matthias Baumann 2013-02-25 08:21:33 +0100 436)             DbHandler db = new DbHandler(InstantUploadActivity.this);
f7649804 (Matthias Baumann 2013-02-25 08:21:33 +0100 437)             try {
7392cfe5 (Matthias Baumann 2013-03-16 18:56:27 +0100 438)                 db.updateFileState(img_path, DbHandler.UPLOAD_STATUS_UPLOAD_LATER, null);
f7649804 (Matthias Baumann 2013-02-25 08:21:33 +0100 439)             } finally {
f7649804 (Matthias Baumann 2013-02-25 08:21:33 +0100 440)                 db.close();
f7649804 (Matthias Baumann 2013-02-25 08:21:33 +0100 441)             }
f7649804 (Matthias Baumann 2013-02-25 08:21:33 +0100 442) 
f7649804 (Matthias Baumann 2013-02-25 08:21:33 +0100 443)             Intent i = new Intent(InstantUploadActivity.this, FileUploader.class);
f7649804 (Matthias Baumann 2013-02-25 08:21:33 +0100 444)             i.putExtra(FileUploader.KEY_ACCOUNT, account);
f7649804 (Matthias Baumann 2013-02-25 08:21:33 +0100 445)             i.putExtra(FileUploader.KEY_LOCAL_FILE, img_path);
7392cfe5 (Matthias Baumann 2013-03-16 18:56:27 +0100 446)             i.putExtra(FileUploader.KEY_REMOTE_FILE, filename);
f7649804 (Matthias Baumann 2013-02-25 08:21:33 +0100 447)             i.putExtra(FileUploader.KEY_UPLOAD_TYPE, FileUploader.UPLOAD_SINGLE_FILE);
f7649804 (Matthias Baumann 2013-02-25 08:21:33 +0100 448)             i.putExtra(com.owncloud.android.files.services.FileUploader.KEY_INSTANT_UPLOAD, true);
f7649804 (Matthias Baumann 2013-02-25 08:21:33 +0100 449) 
f7649804 (Matthias Baumann 2013-02-25 08:21:33 +0100 450)             final String msg = "try to upload file with name :" + filename;
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100 451)             Log_OC.d(LOG_TAG, msg);
f7649804 (Matthias Baumann 2013-02-25 08:21:33 +0100 452)             Toast toast = Toast.makeText(InstantUploadActivity.this, getString(R.string.failed_upload_retry_text)
f7649804 (Matthias Baumann 2013-02-25 08:21:33 +0100 453)                     + filename, Toast.LENGTH_LONG);
f7649804 (Matthias Baumann 2013-02-25 08:21:33 +0100 454)             toast.show();
f7649804 (Matthias Baumann 2013-02-25 08:21:33 +0100 455) 
f7649804 (Matthias Baumann 2013-02-25 08:21:33 +0100 456)             startService(i);
f7649804 (Matthias Baumann 2013-02-25 08:21:33 +0100 457)         } else {
f7649804 (Matthias Baumann 2013-02-25 08:21:33 +0100 458)             Toast toast = Toast.makeText(InstantUploadActivity.this,
f7649804 (Matthias Baumann 2013-02-25 08:21:33 +0100 459)                     getString(R.string.failed_upload_retry_do_nothing_text) + filename, Toast.LENGTH_LONG);
f7649804 (Matthias Baumann 2013-02-25 08:21:33 +0100 460)             toast.show();
f7649804 (Matthias Baumann 2013-02-25 08:21:33 +0100 461)         }
f7649804 (Matthias Baumann 2013-02-25 08:21:33 +0100 462)     }
f7649804 (Matthias Baumann 2013-02-25 08:21:33 +0100 463) 
f7649804 (Matthias Baumann 2013-02-25 08:21:33 +0100 464)     private boolean canInstantUpload() {
f7649804 (Matthias Baumann 2013-02-25 08:21:33 +0100 465) 
f7649804 (Matthias Baumann 2013-02-25 08:21:33 +0100 466)         if (!InstantUploadBroadcastReceiver.isOnline(this)
f7649804 (Matthias Baumann 2013-02-25 08:21:33 +0100 467)                 || (InstantUploadBroadcastReceiver.instantUploadViaWiFiOnly(this) && !InstantUploadBroadcastReceiver
f7649804 (Matthias Baumann 2013-02-25 08:21:33 +0100 468)                         .isConnectedViaWiFi(this))) {
f7649804 (Matthias Baumann 2013-02-25 08:21:33 +0100 469)             return false;
f7649804 (Matthias Baumann 2013-02-25 08:21:33 +0100 470)         } else {
f7649804 (Matthias Baumann 2013-02-25 08:21:33 +0100 471)             return true;
f7649804 (Matthias Baumann 2013-02-25 08:21:33 +0100 472)         }
f7649804 (Matthias Baumann 2013-02-25 08:21:33 +0100 473)     }
f7649804 (Matthias Baumann 2013-02-25 08:21:33 +0100 474) 
f7649804 (Matthias Baumann 2013-02-25 08:21:33 +0100 475) }
