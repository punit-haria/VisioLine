bb257ec7 src/com/owncloud/android/ui/activity/GenericExplanationActivity.java (David A. Velasco 2013-02-07 18:45:10 +0100   1) /* ownCloud Android client application
bb257ec7 src/com/owncloud/android/ui/activity/GenericExplanationActivity.java (David A. Velasco 2013-02-07 18:45:10 +0100   2)  *   Copyright (C) 2012-2013 ownCloud Inc.
bb257ec7 src/com/owncloud/android/ui/activity/GenericExplanationActivity.java (David A. Velasco 2013-02-07 18:45:10 +0100   3)  *
bb257ec7 src/com/owncloud/android/ui/activity/GenericExplanationActivity.java (David A. Velasco 2013-02-07 18:45:10 +0100   4)  *   This program is free software: you can redistribute it and/or modify
bdc0332c src/com/owncloud/android/ui/activity/GenericExplanationActivity.java (David A. Velasco 2013-04-17 12:26:13 +0200   5)  *   it under the terms of the GNU General Public License version 2,
bdc0332c src/com/owncloud/android/ui/activity/GenericExplanationActivity.java (David A. Velasco 2013-04-17 12:26:13 +0200   6)  *   as published by the Free Software Foundation.
bb257ec7 src/com/owncloud/android/ui/activity/GenericExplanationActivity.java (David A. Velasco 2013-02-07 18:45:10 +0100   7)  *
bb257ec7 src/com/owncloud/android/ui/activity/GenericExplanationActivity.java (David A. Velasco 2013-02-07 18:45:10 +0100   8)  *   This program is distributed in the hope that it will be useful,
bb257ec7 src/com/owncloud/android/ui/activity/GenericExplanationActivity.java (David A. Velasco 2013-02-07 18:45:10 +0100   9)  *   but WITHOUT ANY WARRANTY; without even the implied warranty of
bb257ec7 src/com/owncloud/android/ui/activity/GenericExplanationActivity.java (David A. Velasco 2013-02-07 18:45:10 +0100  10)  *   MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
bb257ec7 src/com/owncloud/android/ui/activity/GenericExplanationActivity.java (David A. Velasco 2013-02-07 18:45:10 +0100  11)  *   GNU General Public License for more details.
bb257ec7 src/com/owncloud/android/ui/activity/GenericExplanationActivity.java (David A. Velasco 2013-02-07 18:45:10 +0100  12)  *
bb257ec7 src/com/owncloud/android/ui/activity/GenericExplanationActivity.java (David A. Velasco 2013-02-07 18:45:10 +0100  13)  *   You should have received a copy of the GNU General Public License
bb257ec7 src/com/owncloud/android/ui/activity/GenericExplanationActivity.java (David A. Velasco 2013-02-07 18:45:10 +0100  14)  *   along with this program.  If not, see <http://www.gnu.org/licenses/>.
bb257ec7 src/com/owncloud/android/ui/activity/GenericExplanationActivity.java (David A. Velasco 2013-02-07 18:45:10 +0100  15)  *
bb257ec7 src/com/owncloud/android/ui/activity/GenericExplanationActivity.java (David A. Velasco 2013-02-07 18:45:10 +0100  16)  */
bb257ec7 src/com/owncloud/android/ui/activity/GenericExplanationActivity.java (David A. Velasco 2013-02-07 18:45:10 +0100  17) 
2a913bfb src/com/owncloud/android/ui/activity/ExplanationActivity.java        (David A. Velasco 2012-11-30 11:29:18 +0100  18) package com.owncloud.android.ui.activity;
2a913bfb src/com/owncloud/android/ui/activity/ExplanationActivity.java        (David A. Velasco 2012-11-30 11:29:18 +0100  19) 
2a913bfb src/com/owncloud/android/ui/activity/ExplanationActivity.java        (David A. Velasco 2012-11-30 11:29:18 +0100  20) import java.util.ArrayList;
2a913bfb src/com/owncloud/android/ui/activity/ExplanationActivity.java        (David A. Velasco 2012-11-30 11:29:18 +0100  21) 
2a913bfb src/com/owncloud/android/ui/activity/ExplanationActivity.java        (David A. Velasco 2012-11-30 11:29:18 +0100  22) import android.content.Context;
2a913bfb src/com/owncloud/android/ui/activity/ExplanationActivity.java        (David A. Velasco 2012-11-30 11:29:18 +0100  23) import android.content.Intent;
2a913bfb src/com/owncloud/android/ui/activity/ExplanationActivity.java        (David A. Velasco 2012-11-30 11:29:18 +0100  24) import android.os.Bundle;
3ef64834 src/com/owncloud/android/ui/activity/GenericExplanationActivity.java (David A. Velasco 2012-11-30 11:58:46 +0100  25) import android.text.method.ScrollingMovementMethod;
2a913bfb src/com/owncloud/android/ui/activity/ExplanationActivity.java        (David A. Velasco 2012-11-30 11:29:18 +0100  26) import android.view.View;
2a913bfb src/com/owncloud/android/ui/activity/ExplanationActivity.java        (David A. Velasco 2012-11-30 11:29:18 +0100  27) import android.view.ViewGroup;
2a913bfb src/com/owncloud/android/ui/activity/ExplanationActivity.java        (David A. Velasco 2012-11-30 11:29:18 +0100  28) import android.widget.ArrayAdapter;
2a913bfb src/com/owncloud/android/ui/activity/ExplanationActivity.java        (David A. Velasco 2012-11-30 11:29:18 +0100  29) import android.widget.ListAdapter;
2a913bfb src/com/owncloud/android/ui/activity/ExplanationActivity.java        (David A. Velasco 2012-11-30 11:29:18 +0100  30) import android.widget.ListView;
2a913bfb src/com/owncloud/android/ui/activity/ExplanationActivity.java        (David A. Velasco 2012-11-30 11:29:18 +0100  31) import android.widget.TextView;
2a913bfb src/com/owncloud/android/ui/activity/ExplanationActivity.java        (David A. Velasco 2012-11-30 11:29:18 +0100  32) 
2a913bfb src/com/owncloud/android/ui/activity/ExplanationActivity.java        (David A. Velasco 2012-11-30 11:29:18 +0100  33) import com.actionbarsherlock.app.SherlockFragmentActivity;
2a913bfb src/com/owncloud/android/ui/activity/ExplanationActivity.java        (David A. Velasco 2012-11-30 11:29:18 +0100  34) import com.owncloud.android.R;
2a913bfb src/com/owncloud/android/ui/activity/ExplanationActivity.java        (David A. Velasco 2012-11-30 11:29:18 +0100  35) 
2a913bfb src/com/owncloud/android/ui/activity/ExplanationActivity.java        (David A. Velasco 2012-11-30 11:29:18 +0100  36) /**
f68d10ab src/com/owncloud/android/ui/activity/GenericExplanationActivity.java (David A. Velasco 2012-12-04 12:49:18 +0100  37)  * Activity showing a text message and, optionally, a couple list of single or paired text strings.
2a913bfb src/com/owncloud/android/ui/activity/ExplanationActivity.java        (David A. Velasco 2012-11-30 11:29:18 +0100  38)  * 
2a913bfb src/com/owncloud/android/ui/activity/ExplanationActivity.java        (David A. Velasco 2012-11-30 11:29:18 +0100  39)  * Added to show explanations for notifications when the user clicks on them, and there no place
2a913bfb src/com/owncloud/android/ui/activity/ExplanationActivity.java        (David A. Velasco 2012-11-30 11:29:18 +0100  40)  * better to show them.
2a913bfb src/com/owncloud/android/ui/activity/ExplanationActivity.java        (David A. Velasco 2012-11-30 11:29:18 +0100  41)  * 
2a913bfb src/com/owncloud/android/ui/activity/ExplanationActivity.java        (David A. Velasco 2012-11-30 11:29:18 +0100  42)  * @author David A. Velasco
2a913bfb src/com/owncloud/android/ui/activity/ExplanationActivity.java        (David A. Velasco 2012-11-30 11:29:18 +0100  43)  */
3ef64834 src/com/owncloud/android/ui/activity/GenericExplanationActivity.java (David A. Velasco 2012-11-30 11:58:46 +0100  44) public class GenericExplanationActivity  extends SherlockFragmentActivity {
2a913bfb src/com/owncloud/android/ui/activity/ExplanationActivity.java        (David A. Velasco 2012-11-30 11:29:18 +0100  45) 
3ef64834 src/com/owncloud/android/ui/activity/GenericExplanationActivity.java (David A. Velasco 2012-11-30 11:58:46 +0100  46)     public static final String EXTRA_LIST = GenericExplanationActivity.class.getCanonicalName() + ".EXTRA_LIST";
3ef64834 src/com/owncloud/android/ui/activity/GenericExplanationActivity.java (David A. Velasco 2012-11-30 11:58:46 +0100  47)     public static final String EXTRA_LIST_2 = GenericExplanationActivity.class.getCanonicalName() + ".EXTRA_LIST_2";
3ef64834 src/com/owncloud/android/ui/activity/GenericExplanationActivity.java (David A. Velasco 2012-11-30 11:58:46 +0100  48)     public static final String MESSAGE = GenericExplanationActivity.class.getCanonicalName() + ".MESSAGE";
2a913bfb src/com/owncloud/android/ui/activity/ExplanationActivity.java        (David A. Velasco 2012-11-30 11:29:18 +0100  49)     
2a913bfb src/com/owncloud/android/ui/activity/ExplanationActivity.java        (David A. Velasco 2012-11-30 11:29:18 +0100  50)     
2a913bfb src/com/owncloud/android/ui/activity/ExplanationActivity.java        (David A. Velasco 2012-11-30 11:29:18 +0100  51)     @Override
2a913bfb src/com/owncloud/android/ui/activity/ExplanationActivity.java        (David A. Velasco 2012-11-30 11:29:18 +0100  52)     protected void onCreate(Bundle savedInstanceState) {
2a913bfb src/com/owncloud/android/ui/activity/ExplanationActivity.java        (David A. Velasco 2012-11-30 11:29:18 +0100  53)         super.onCreate(savedInstanceState);
2a913bfb src/com/owncloud/android/ui/activity/ExplanationActivity.java        (David A. Velasco 2012-11-30 11:29:18 +0100  54)         
2a913bfb src/com/owncloud/android/ui/activity/ExplanationActivity.java        (David A. Velasco 2012-11-30 11:29:18 +0100  55)         Intent intent = getIntent();
2a913bfb src/com/owncloud/android/ui/activity/ExplanationActivity.java        (David A. Velasco 2012-11-30 11:29:18 +0100  56)         String message = intent.getStringExtra(MESSAGE); 
2a913bfb src/com/owncloud/android/ui/activity/ExplanationActivity.java        (David A. Velasco 2012-11-30 11:29:18 +0100  57)         ArrayList<String> list = intent.getStringArrayListExtra(EXTRA_LIST);
2a913bfb src/com/owncloud/android/ui/activity/ExplanationActivity.java        (David A. Velasco 2012-11-30 11:29:18 +0100  58)         ArrayList<String> list2 = intent.getStringArrayListExtra(EXTRA_LIST_2);
2a913bfb src/com/owncloud/android/ui/activity/ExplanationActivity.java        (David A. Velasco 2012-11-30 11:29:18 +0100  59)         
3ef64834 src/com/owncloud/android/ui/activity/GenericExplanationActivity.java (David A. Velasco 2012-11-30 11:58:46 +0100  60)         setContentView(R.layout.generic_explanation);
2a913bfb src/com/owncloud/android/ui/activity/ExplanationActivity.java        (David A. Velasco 2012-11-30 11:29:18 +0100  61)         
2a913bfb src/com/owncloud/android/ui/activity/ExplanationActivity.java        (David A. Velasco 2012-11-30 11:29:18 +0100  62)         if (message != null) {
2a913bfb src/com/owncloud/android/ui/activity/ExplanationActivity.java        (David A. Velasco 2012-11-30 11:29:18 +0100  63)             TextView textView = (TextView) findViewById(R.id.message);
2a913bfb src/com/owncloud/android/ui/activity/ExplanationActivity.java        (David A. Velasco 2012-11-30 11:29:18 +0100  64)             textView.setText(message);
3ef64834 src/com/owncloud/android/ui/activity/GenericExplanationActivity.java (David A. Velasco 2012-11-30 11:58:46 +0100  65)             textView.setMovementMethod(new ScrollingMovementMethod());
2a913bfb src/com/owncloud/android/ui/activity/ExplanationActivity.java        (David A. Velasco 2012-11-30 11:29:18 +0100  66)         }
2a913bfb src/com/owncloud/android/ui/activity/ExplanationActivity.java        (David A. Velasco 2012-11-30 11:29:18 +0100  67)         
2a913bfb src/com/owncloud/android/ui/activity/ExplanationActivity.java        (David A. Velasco 2012-11-30 11:29:18 +0100  68)         ListView listView = (ListView) findViewById(R.id.list);
2a913bfb src/com/owncloud/android/ui/activity/ExplanationActivity.java        (David A. Velasco 2012-11-30 11:29:18 +0100  69)         if (list != null && list.size() > 0) {
2a913bfb src/com/owncloud/android/ui/activity/ExplanationActivity.java        (David A. Velasco 2012-11-30 11:29:18 +0100  70)             //ListAdapter adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, list);
2a913bfb src/com/owncloud/android/ui/activity/ExplanationActivity.java        (David A. Velasco 2012-11-30 11:29:18 +0100  71)             ListAdapter adapter = new ExplanationListAdapterView(this, list, list2);
2a913bfb src/com/owncloud/android/ui/activity/ExplanationActivity.java        (David A. Velasco 2012-11-30 11:29:18 +0100  72)             listView.setAdapter(adapter);
2a913bfb src/com/owncloud/android/ui/activity/ExplanationActivity.java        (David A. Velasco 2012-11-30 11:29:18 +0100  73)         } else {
2a913bfb src/com/owncloud/android/ui/activity/ExplanationActivity.java        (David A. Velasco 2012-11-30 11:29:18 +0100  74)             listView.setVisibility(View.GONE);
2a913bfb src/com/owncloud/android/ui/activity/ExplanationActivity.java        (David A. Velasco 2012-11-30 11:29:18 +0100  75)         }
2a913bfb src/com/owncloud/android/ui/activity/ExplanationActivity.java        (David A. Velasco 2012-11-30 11:29:18 +0100  76)     }
2a913bfb src/com/owncloud/android/ui/activity/ExplanationActivity.java        (David A. Velasco 2012-11-30 11:29:18 +0100  77)     
2a913bfb src/com/owncloud/android/ui/activity/ExplanationActivity.java        (David A. Velasco 2012-11-30 11:29:18 +0100  78)     public class ExplanationListAdapterView extends ArrayAdapter<String> {
2a913bfb src/com/owncloud/android/ui/activity/ExplanationActivity.java        (David A. Velasco 2012-11-30 11:29:18 +0100  79)         
2a913bfb src/com/owncloud/android/ui/activity/ExplanationActivity.java        (David A. Velasco 2012-11-30 11:29:18 +0100  80)         ArrayList<String> mList;
2a913bfb src/com/owncloud/android/ui/activity/ExplanationActivity.java        (David A. Velasco 2012-11-30 11:29:18 +0100  81)         ArrayList<String> mList2;
2a913bfb src/com/owncloud/android/ui/activity/ExplanationActivity.java        (David A. Velasco 2012-11-30 11:29:18 +0100  82)         
2a913bfb src/com/owncloud/android/ui/activity/ExplanationActivity.java        (David A. Velasco 2012-11-30 11:29:18 +0100  83)         ExplanationListAdapterView(Context context, ArrayList<String> list, ArrayList<String> list2) {
2a913bfb src/com/owncloud/android/ui/activity/ExplanationActivity.java        (David A. Velasco 2012-11-30 11:29:18 +0100  84)             super(context, android.R.layout.two_line_list_item, android.R.id.text1, list);
2a913bfb src/com/owncloud/android/ui/activity/ExplanationActivity.java        (David A. Velasco 2012-11-30 11:29:18 +0100  85)             mList = list;
2a913bfb src/com/owncloud/android/ui/activity/ExplanationActivity.java        (David A. Velasco 2012-11-30 11:29:18 +0100  86)             mList2 = list2;
2a913bfb src/com/owncloud/android/ui/activity/ExplanationActivity.java        (David A. Velasco 2012-11-30 11:29:18 +0100  87)         }
2a913bfb src/com/owncloud/android/ui/activity/ExplanationActivity.java        (David A. Velasco 2012-11-30 11:29:18 +0100  88) 
2a913bfb src/com/owncloud/android/ui/activity/ExplanationActivity.java        (David A. Velasco 2012-11-30 11:29:18 +0100  89)         @Override
2a913bfb src/com/owncloud/android/ui/activity/ExplanationActivity.java        (David A. Velasco 2012-11-30 11:29:18 +0100  90)         public boolean isEnabled(int position) {
2a913bfb src/com/owncloud/android/ui/activity/ExplanationActivity.java        (David A. Velasco 2012-11-30 11:29:18 +0100  91)             return false;
2a913bfb src/com/owncloud/android/ui/activity/ExplanationActivity.java        (David A. Velasco 2012-11-30 11:29:18 +0100  92)         }
2a913bfb src/com/owncloud/android/ui/activity/ExplanationActivity.java        (David A. Velasco 2012-11-30 11:29:18 +0100  93)         
2a913bfb src/com/owncloud/android/ui/activity/ExplanationActivity.java        (David A. Velasco 2012-11-30 11:29:18 +0100  94)         /**
2a913bfb src/com/owncloud/android/ui/activity/ExplanationActivity.java        (David A. Velasco 2012-11-30 11:29:18 +0100  95)          * {@inheritDoc}
2a913bfb src/com/owncloud/android/ui/activity/ExplanationActivity.java        (David A. Velasco 2012-11-30 11:29:18 +0100  96)          */
2a913bfb src/com/owncloud/android/ui/activity/ExplanationActivity.java        (David A. Velasco 2012-11-30 11:29:18 +0100  97)         @Override
2a913bfb src/com/owncloud/android/ui/activity/ExplanationActivity.java        (David A. Velasco 2012-11-30 11:29:18 +0100  98)         public View getView (int position, View convertView, ViewGroup parent) {
2a913bfb src/com/owncloud/android/ui/activity/ExplanationActivity.java        (David A. Velasco 2012-11-30 11:29:18 +0100  99)             View view = super.getView(position, convertView, parent);
2a913bfb src/com/owncloud/android/ui/activity/ExplanationActivity.java        (David A. Velasco 2012-11-30 11:29:18 +0100 100)             if (view != null)  {
2a913bfb src/com/owncloud/android/ui/activity/ExplanationActivity.java        (David A. Velasco 2012-11-30 11:29:18 +0100 101)                 if (mList2 != null && mList2.size() > 0 && position >= 0 && position < mList2.size()) {
2a913bfb src/com/owncloud/android/ui/activity/ExplanationActivity.java        (David A. Velasco 2012-11-30 11:29:18 +0100 102)                     TextView text2 = (TextView) view.findViewById(android.R.id.text2);
2a913bfb src/com/owncloud/android/ui/activity/ExplanationActivity.java        (David A. Velasco 2012-11-30 11:29:18 +0100 103)                     if (text2 != null) {
2a913bfb src/com/owncloud/android/ui/activity/ExplanationActivity.java        (David A. Velasco 2012-11-30 11:29:18 +0100 104)                         text2.setText(mList2.get(position));
2a913bfb src/com/owncloud/android/ui/activity/ExplanationActivity.java        (David A. Velasco 2012-11-30 11:29:18 +0100 105)                     }
2a913bfb src/com/owncloud/android/ui/activity/ExplanationActivity.java        (David A. Velasco 2012-11-30 11:29:18 +0100 106)                 }
2a913bfb src/com/owncloud/android/ui/activity/ExplanationActivity.java        (David A. Velasco 2012-11-30 11:29:18 +0100 107)             }
2a913bfb src/com/owncloud/android/ui/activity/ExplanationActivity.java        (David A. Velasco 2012-11-30 11:29:18 +0100 108)             return view;
2a913bfb src/com/owncloud/android/ui/activity/ExplanationActivity.java        (David A. Velasco 2012-11-30 11:29:18 +0100 109)         }
2a913bfb src/com/owncloud/android/ui/activity/ExplanationActivity.java        (David A. Velasco 2012-11-30 11:29:18 +0100 110)     }
2a913bfb src/com/owncloud/android/ui/activity/ExplanationActivity.java        (David A. Velasco 2012-11-30 11:29:18 +0100 111) 
2a913bfb src/com/owncloud/android/ui/activity/ExplanationActivity.java        (David A. Velasco 2012-11-30 11:29:18 +0100 112) }
