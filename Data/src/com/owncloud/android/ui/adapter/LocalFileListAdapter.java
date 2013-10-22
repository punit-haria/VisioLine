1ecd2b61 (David A. Velasco  2012-08-14 12:37:08 +0200   1) /* ownCloud Android client application
1ecd2b61 (David A. Velasco  2012-08-14 12:37:08 +0200   2)  *   Copyright (C) 2011  Bartek Przybylski
bb257ec7 (David A. Velasco  2013-02-07 18:45:10 +0100   3)  *   Copyright (C) 2012-2013 ownCloud Inc.
1ecd2b61 (David A. Velasco  2012-08-14 12:37:08 +0200   4)  *
1ecd2b61 (David A. Velasco  2012-08-14 12:37:08 +0200   5)  *   This program is free software: you can redistribute it and/or modify
bdc0332c (David A. Velasco  2013-04-17 12:26:13 +0200   6)  *   it under the terms of the GNU General Public License version 2,
bdc0332c (David A. Velasco  2013-04-17 12:26:13 +0200   7)  *   as published by the Free Software Foundation.
1ecd2b61 (David A. Velasco  2012-08-14 12:37:08 +0200   8)  *
1ecd2b61 (David A. Velasco  2012-08-14 12:37:08 +0200   9)  *   This program is distributed in the hope that it will be useful,
1ecd2b61 (David A. Velasco  2012-08-14 12:37:08 +0200  10)  *   but WITHOUT ANY WARRANTY; without even the implied warranty of
1ecd2b61 (David A. Velasco  2012-08-14 12:37:08 +0200  11)  *   MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
1ecd2b61 (David A. Velasco  2012-08-14 12:37:08 +0200  12)  *   GNU General Public License for more details.
1ecd2b61 (David A. Velasco  2012-08-14 12:37:08 +0200  13)  *
1ecd2b61 (David A. Velasco  2012-08-14 12:37:08 +0200  14)  *   You should have received a copy of the GNU General Public License
1ecd2b61 (David A. Velasco  2012-08-14 12:37:08 +0200  15)  *   along with this program.  If not, see <http://www.gnu.org/licenses/>.
1ecd2b61 (David A. Velasco  2012-08-14 12:37:08 +0200  16)  *
1ecd2b61 (David A. Velasco  2012-08-14 12:37:08 +0200  17)  */
1ecd2b61 (David A. Velasco  2012-08-14 12:37:08 +0200  18) package com.owncloud.android.ui.adapter;
1ecd2b61 (David A. Velasco  2012-08-14 12:37:08 +0200  19) 
1ecd2b61 (David A. Velasco  2012-08-14 12:37:08 +0200  20) import java.io.File;
1ecd2b61 (David A. Velasco  2012-08-14 12:37:08 +0200  21) import java.util.Arrays;
1ecd2b61 (David A. Velasco  2012-08-14 12:37:08 +0200  22) import java.util.Comparator;
1ecd2b61 (David A. Velasco  2012-08-14 12:37:08 +0200  23) 
1ecd2b61 (David A. Velasco  2012-08-14 12:37:08 +0200  24) import com.owncloud.android.DisplayUtils;
1ecd2b61 (David A. Velasco  2012-08-14 12:37:08 +0200  25) import com.owncloud.android.R;
1ecd2b61 (David A. Velasco  2012-08-14 12:37:08 +0200  26) 
1ecd2b61 (David A. Velasco  2012-08-14 12:37:08 +0200  27) import android.content.Context;
1ecd2b61 (David A. Velasco  2012-08-14 12:37:08 +0200  28) import android.view.LayoutInflater;
1ecd2b61 (David A. Velasco  2012-08-14 12:37:08 +0200  29) import android.view.View;
1ecd2b61 (David A. Velasco  2012-08-14 12:37:08 +0200  30) import android.view.ViewGroup;
1ecd2b61 (David A. Velasco  2012-08-14 12:37:08 +0200  31) import android.widget.BaseAdapter;
1ecd2b61 (David A. Velasco  2012-08-14 12:37:08 +0200  32) import android.widget.ImageView;
1ecd2b61 (David A. Velasco  2012-08-14 12:37:08 +0200  33) import android.widget.ListAdapter;
1ecd2b61 (David A. Velasco  2012-08-14 12:37:08 +0200  34) import android.widget.ListView;
1ecd2b61 (David A. Velasco  2012-08-14 12:37:08 +0200  35) import android.widget.TextView;
1ecd2b61 (David A. Velasco  2012-08-14 12:37:08 +0200  36) 
1ecd2b61 (David A. Velasco  2012-08-14 12:37:08 +0200  37) /**
1ecd2b61 (David A. Velasco  2012-08-14 12:37:08 +0200  38)  * This Adapter populates a ListView with all files and directories contained
1ecd2b61 (David A. Velasco  2012-08-14 12:37:08 +0200  39)  * in a local directory
1ecd2b61 (David A. Velasco  2012-08-14 12:37:08 +0200  40)  * 
1ecd2b61 (David A. Velasco  2012-08-14 12:37:08 +0200  41)  * @author David A. Velasco
1ecd2b61 (David A. Velasco  2012-08-14 12:37:08 +0200  42)  * 
1ecd2b61 (David A. Velasco  2012-08-14 12:37:08 +0200  43)  */
1ecd2b61 (David A. Velasco  2012-08-14 12:37:08 +0200  44) public class LocalFileListAdapter extends BaseAdapter implements ListAdapter {
1ecd2b61 (David A. Velasco  2012-08-14 12:37:08 +0200  45)     
1ecd2b61 (David A. Velasco  2012-08-14 12:37:08 +0200  46)     private Context mContext;
1ecd2b61 (David A. Velasco  2012-08-14 12:37:08 +0200  47)     private File mDirectory;
1ecd2b61 (David A. Velasco  2012-08-14 12:37:08 +0200  48)     private File[] mFiles = null;
1ecd2b61 (David A. Velasco  2012-08-14 12:37:08 +0200  49) 
1ecd2b61 (David A. Velasco  2012-08-14 12:37:08 +0200  50)     public LocalFileListAdapter(File directory, Context context) {
1ecd2b61 (David A. Velasco  2012-08-14 12:37:08 +0200  51)         mContext = context;
1ecd2b61 (David A. Velasco  2012-08-14 12:37:08 +0200  52)         swapDirectory(directory);
1ecd2b61 (David A. Velasco  2012-08-14 12:37:08 +0200  53)     }
1ecd2b61 (David A. Velasco  2012-08-14 12:37:08 +0200  54) 
1ecd2b61 (David A. Velasco  2012-08-14 12:37:08 +0200  55)     @Override
1ecd2b61 (David A. Velasco  2012-08-14 12:37:08 +0200  56)     public boolean areAllItemsEnabled() {
1ecd2b61 (David A. Velasco  2012-08-14 12:37:08 +0200  57)         return true;
1ecd2b61 (David A. Velasco  2012-08-14 12:37:08 +0200  58)     }
1ecd2b61 (David A. Velasco  2012-08-14 12:37:08 +0200  59) 
1ecd2b61 (David A. Velasco  2012-08-14 12:37:08 +0200  60)     @Override
1ecd2b61 (David A. Velasco  2012-08-14 12:37:08 +0200  61)     public boolean isEnabled(int position) {
1ecd2b61 (David A. Velasco  2012-08-14 12:37:08 +0200  62)         return true;
1ecd2b61 (David A. Velasco  2012-08-14 12:37:08 +0200  63)     }
1ecd2b61 (David A. Velasco  2012-08-14 12:37:08 +0200  64) 
1ecd2b61 (David A. Velasco  2012-08-14 12:37:08 +0200  65)     @Override
1ecd2b61 (David A. Velasco  2012-08-14 12:37:08 +0200  66)     public int getCount() {
1ecd2b61 (David A. Velasco  2012-08-14 12:37:08 +0200  67)         return mFiles != null ? mFiles.length : 0;
1ecd2b61 (David A. Velasco  2012-08-14 12:37:08 +0200  68)     }
1ecd2b61 (David A. Velasco  2012-08-14 12:37:08 +0200  69) 
1ecd2b61 (David A. Velasco  2012-08-14 12:37:08 +0200  70)     @Override
1ecd2b61 (David A. Velasco  2012-08-14 12:37:08 +0200  71)     public Object getItem(int position) {
1ecd2b61 (David A. Velasco  2012-08-14 12:37:08 +0200  72)         if (mFiles == null || mFiles.length <= position)
1ecd2b61 (David A. Velasco  2012-08-14 12:37:08 +0200  73)             return null;
1ecd2b61 (David A. Velasco  2012-08-14 12:37:08 +0200  74)         return mFiles[position];
1ecd2b61 (David A. Velasco  2012-08-14 12:37:08 +0200  75)     }
1ecd2b61 (David A. Velasco  2012-08-14 12:37:08 +0200  76) 
1ecd2b61 (David A. Velasco  2012-08-14 12:37:08 +0200  77)     @Override
1ecd2b61 (David A. Velasco  2012-08-14 12:37:08 +0200  78)     public long getItemId(int position) {
1ecd2b61 (David A. Velasco  2012-08-14 12:37:08 +0200  79)         return mFiles != null && mFiles.length <= position ? position : -1;
1ecd2b61 (David A. Velasco  2012-08-14 12:37:08 +0200  80)     }
1ecd2b61 (David A. Velasco  2012-08-14 12:37:08 +0200  81) 
1ecd2b61 (David A. Velasco  2012-08-14 12:37:08 +0200  82)     @Override
1ecd2b61 (David A. Velasco  2012-08-14 12:37:08 +0200  83)     public int getItemViewType(int position) {
1ecd2b61 (David A. Velasco  2012-08-14 12:37:08 +0200  84)         return 0;
1ecd2b61 (David A. Velasco  2012-08-14 12:37:08 +0200  85)     }
1ecd2b61 (David A. Velasco  2012-08-14 12:37:08 +0200  86) 
1ecd2b61 (David A. Velasco  2012-08-14 12:37:08 +0200  87)     @Override
1ecd2b61 (David A. Velasco  2012-08-14 12:37:08 +0200  88)     public View getView(int position, View convertView, ViewGroup parent) {
1ecd2b61 (David A. Velasco  2012-08-14 12:37:08 +0200  89)         View view = convertView;
1ecd2b61 (David A. Velasco  2012-08-14 12:37:08 +0200  90)         if (view == null) {
1ecd2b61 (David A. Velasco  2012-08-14 12:37:08 +0200  91)             LayoutInflater inflator = (LayoutInflater) mContext
1ecd2b61 (David A. Velasco  2012-08-14 12:37:08 +0200  92)                     .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
d0f7de3c (David A. Velasco  2012-10-17 19:22:40 +0200  93)             view = inflator.inflate(R.layout.list_item, null);
1ecd2b61 (David A. Velasco  2012-08-14 12:37:08 +0200  94)         }
1ecd2b61 (David A. Velasco  2012-08-14 12:37:08 +0200  95)         if (mFiles != null && mFiles.length > position) {
1ecd2b61 (David A. Velasco  2012-08-14 12:37:08 +0200  96)             File file = mFiles[position];
1ecd2b61 (David A. Velasco  2012-08-14 12:37:08 +0200  97)             
1ecd2b61 (David A. Velasco  2012-08-14 12:37:08 +0200  98)             TextView fileName = (TextView) view.findViewById(R.id.Filename);
1ecd2b61 (David A. Velasco  2012-08-14 12:37:08 +0200  99)             String name = file.getName();
1ecd2b61 (David A. Velasco  2012-08-14 12:37:08 +0200 100)             fileName.setText(name);
1ecd2b61 (David A. Velasco  2012-08-14 12:37:08 +0200 101)             
1ecd2b61 (David A. Velasco  2012-08-14 12:37:08 +0200 102)             ImageView fileIcon = (ImageView) view.findViewById(R.id.imageView1);
1ecd2b61 (David A. Velasco  2012-08-14 12:37:08 +0200 103)             if (!file.isDirectory()) {
1ecd2b61 (David A. Velasco  2012-08-14 12:37:08 +0200 104)                 fileIcon.setImageResource(R.drawable.file);
1ecd2b61 (David A. Velasco  2012-08-14 12:37:08 +0200 105)             } else {
1ecd2b61 (David A. Velasco  2012-08-14 12:37:08 +0200 106)                 fileIcon.setImageResource(R.drawable.ic_menu_archive);
1ecd2b61 (David A. Velasco  2012-08-14 12:37:08 +0200 107)             }
1ecd2b61 (David A. Velasco  2012-08-14 12:37:08 +0200 108) 
1ecd2b61 (David A. Velasco  2012-08-14 12:37:08 +0200 109)             TextView fileSizeV = (TextView) view.findViewById(R.id.file_size);
1ecd2b61 (David A. Velasco  2012-08-14 12:37:08 +0200 110)             TextView lastModV = (TextView) view.findViewById(R.id.last_mod);
1ecd2b61 (David A. Velasco  2012-08-14 12:37:08 +0200 111)             ImageView checkBoxV = (ImageView) view.findViewById(R.id.custom_checkbox);
1ecd2b61 (David A. Velasco  2012-08-14 12:37:08 +0200 112)             if (!file.isDirectory()) {
1ecd2b61 (David A. Velasco  2012-08-14 12:37:08 +0200 113)                 fileSizeV.setVisibility(View.VISIBLE);
1ecd2b61 (David A. Velasco  2012-08-14 12:37:08 +0200 114)                 fileSizeV.setText(DisplayUtils.bytesToHumanReadable(file.length()));
1ecd2b61 (David A. Velasco  2012-08-14 12:37:08 +0200 115)                 lastModV.setVisibility(View.VISIBLE);
1ecd2b61 (David A. Velasco  2012-08-14 12:37:08 +0200 116)                 lastModV.setText(DisplayUtils.unixTimeToHumanReadable(file.lastModified()));
1ecd2b61 (David A. Velasco  2012-08-14 12:37:08 +0200 117)                 ListView parentList = (ListView)parent;
1ecd2b61 (David A. Velasco  2012-08-14 12:37:08 +0200 118)                 if (parentList.getChoiceMode() == ListView.CHOICE_MODE_NONE) { 
1ecd2b61 (David A. Velasco  2012-08-14 12:37:08 +0200 119)                     checkBoxV.setVisibility(View.GONE);
1ecd2b61 (David A. Velasco  2012-08-14 12:37:08 +0200 120)                 } else {
1ecd2b61 (David A. Velasco  2012-08-14 12:37:08 +0200 121)                     if (parentList.isItemChecked(position)) {
1ecd2b61 (David A. Velasco  2012-08-14 12:37:08 +0200 122)                         checkBoxV.setImageResource(android.R.drawable.checkbox_on_background);
1ecd2b61 (David A. Velasco  2012-08-14 12:37:08 +0200 123)                     } else {
1ecd2b61 (David A. Velasco  2012-08-14 12:37:08 +0200 124)                         checkBoxV.setImageResource(android.R.drawable.checkbox_off_background);
1ecd2b61 (David A. Velasco  2012-08-14 12:37:08 +0200 125)                     }
1f57ae3b (Bartek Przybylski 2012-08-18 12:02:21 +0200 126)                     checkBoxV.setVisibility(View.VISIBLE);
1ecd2b61 (David A. Velasco  2012-08-14 12:37:08 +0200 127)                 }
1ecd2b61 (David A. Velasco  2012-08-14 12:37:08 +0200 128) 
1ecd2b61 (David A. Velasco  2012-08-14 12:37:08 +0200 129)             } else {
1ecd2b61 (David A. Velasco  2012-08-14 12:37:08 +0200 130)                 fileSizeV.setVisibility(View.GONE);
1ecd2b61 (David A. Velasco  2012-08-14 12:37:08 +0200 131)                 lastModV.setVisibility(View.GONE);
1ecd2b61 (David A. Velasco  2012-08-14 12:37:08 +0200 132)                 checkBoxV.setVisibility(View.GONE);
1ecd2b61 (David A. Velasco  2012-08-14 12:37:08 +0200 133)             }
1ecd2b61 (David A. Velasco  2012-08-14 12:37:08 +0200 134)             
1ecd2b61 (David A. Velasco  2012-08-14 12:37:08 +0200 135)             view.findViewById(R.id.imageView2).setVisibility(View.INVISIBLE);   // not GONE; the alignment changes; ugly way to keep it
1ecd2b61 (David A. Velasco  2012-08-14 12:37:08 +0200 136)             view.findViewById(R.id.imageView3).setVisibility(View.GONE);
1ecd2b61 (David A. Velasco  2012-08-14 12:37:08 +0200 137)         }
1ecd2b61 (David A. Velasco  2012-08-14 12:37:08 +0200 138) 
1ecd2b61 (David A. Velasco  2012-08-14 12:37:08 +0200 139)         return view;
1ecd2b61 (David A. Velasco  2012-08-14 12:37:08 +0200 140)     }
1ecd2b61 (David A. Velasco  2012-08-14 12:37:08 +0200 141) 
1ecd2b61 (David A. Velasco  2012-08-14 12:37:08 +0200 142)     @Override
1ecd2b61 (David A. Velasco  2012-08-14 12:37:08 +0200 143)     public int getViewTypeCount() {
1ecd2b61 (David A. Velasco  2012-08-14 12:37:08 +0200 144)         return 1;
1ecd2b61 (David A. Velasco  2012-08-14 12:37:08 +0200 145)     }
1ecd2b61 (David A. Velasco  2012-08-14 12:37:08 +0200 146) 
1ecd2b61 (David A. Velasco  2012-08-14 12:37:08 +0200 147)     @Override
1ecd2b61 (David A. Velasco  2012-08-14 12:37:08 +0200 148)     public boolean hasStableIds() {
1ecd2b61 (David A. Velasco  2012-08-14 12:37:08 +0200 149)         return false;
1ecd2b61 (David A. Velasco  2012-08-14 12:37:08 +0200 150)     }
1ecd2b61 (David A. Velasco  2012-08-14 12:37:08 +0200 151) 
1ecd2b61 (David A. Velasco  2012-08-14 12:37:08 +0200 152)     @Override
1ecd2b61 (David A. Velasco  2012-08-14 12:37:08 +0200 153)     public boolean isEmpty() {
1ecd2b61 (David A. Velasco  2012-08-14 12:37:08 +0200 154)         return (mFiles == null || mFiles.length == 0);
1ecd2b61 (David A. Velasco  2012-08-14 12:37:08 +0200 155)     }
1ecd2b61 (David A. Velasco  2012-08-14 12:37:08 +0200 156) 
1ecd2b61 (David A. Velasco  2012-08-14 12:37:08 +0200 157)     /**
1ecd2b61 (David A. Velasco  2012-08-14 12:37:08 +0200 158)      * Change the adapted directory for a new one
a2d4e98a (David A. Velasco  2012-10-03 16:48:06 +0200 159)      * @param directory     New file to adapt. Can be NULL, meaning "no content to adapt".
1ecd2b61 (David A. Velasco  2012-08-14 12:37:08 +0200 160)      */
1ecd2b61 (David A. Velasco  2012-08-14 12:37:08 +0200 161)     public void swapDirectory(File directory) {
1ecd2b61 (David A. Velasco  2012-08-14 12:37:08 +0200 162)         mDirectory = directory;
a2d4e98a (David A. Velasco  2012-10-03 16:48:06 +0200 163)         mFiles = (mDirectory != null ? mDirectory.listFiles() : null);
1ecd2b61 (David A. Velasco  2012-08-14 12:37:08 +0200 164)         if (mFiles != null) {
1ecd2b61 (David A. Velasco  2012-08-14 12:37:08 +0200 165)             Arrays.sort(mFiles, new Comparator<File>() {
1ecd2b61 (David A. Velasco  2012-08-14 12:37:08 +0200 166)                 @Override
1ecd2b61 (David A. Velasco  2012-08-14 12:37:08 +0200 167)                 public int compare(File lhs, File rhs) {
1ecd2b61 (David A. Velasco  2012-08-14 12:37:08 +0200 168)                     if (lhs.isDirectory() && !rhs.isDirectory()) {
1ecd2b61 (David A. Velasco  2012-08-14 12:37:08 +0200 169)                         return -1;
1ecd2b61 (David A. Velasco  2012-08-14 12:37:08 +0200 170)                     } else if (!lhs.isDirectory() && rhs.isDirectory()) {
1ecd2b61 (David A. Velasco  2012-08-14 12:37:08 +0200 171)                         return 1;
1ecd2b61 (David A. Velasco  2012-08-14 12:37:08 +0200 172)                     }
1ecd2b61 (David A. Velasco  2012-08-14 12:37:08 +0200 173)                     return compareNames(lhs, rhs);
1ecd2b61 (David A. Velasco  2012-08-14 12:37:08 +0200 174)                 }
1ecd2b61 (David A. Velasco  2012-08-14 12:37:08 +0200 175)             
1ecd2b61 (David A. Velasco  2012-08-14 12:37:08 +0200 176)                 private int compareNames(File lhs, File rhs) {
1ecd2b61 (David A. Velasco  2012-08-14 12:37:08 +0200 177)                     return lhs.getName().toLowerCase().compareTo(rhs.getName().toLowerCase());                
1ecd2b61 (David A. Velasco  2012-08-14 12:37:08 +0200 178)                 }
1ecd2b61 (David A. Velasco  2012-08-14 12:37:08 +0200 179)             
1ecd2b61 (David A. Velasco  2012-08-14 12:37:08 +0200 180)             });
1ecd2b61 (David A. Velasco  2012-08-14 12:37:08 +0200 181)         }
a2d4e98a (David A. Velasco  2012-10-03 16:48:06 +0200 182)         notifyDataSetChanged();
1ecd2b61 (David A. Velasco  2012-08-14 12:37:08 +0200 183)     }
1ecd2b61 (David A. Velasco  2012-08-14 12:37:08 +0200 184) }
