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
00000000 (Not Committed Yet 2013-10-18 00:25:13 -0700  20) import android.accounts.Account;
00000000 (Not Committed Yet 2013-10-18 00:25:13 -0700  21) import android.content.Context;
00000000 (Not Committed Yet 2013-10-18 00:25:13 -0700  22) import android.view.LayoutInflater;
00000000 (Not Committed Yet 2013-10-18 00:25:13 -0700  23) import android.view.View;
00000000 (Not Committed Yet 2013-10-18 00:25:13 -0700  24) import android.view.ViewGroup;
00000000 (Not Committed Yet 2013-10-18 00:25:13 -0700  25) import android.widget.BaseAdapter;
00000000 (Not Committed Yet 2013-10-18 00:25:13 -0700  26) import android.widget.ImageView;
00000000 (Not Committed Yet 2013-10-18 00:25:13 -0700  27) import android.widget.ListAdapter;
00000000 (Not Committed Yet 2013-10-18 00:25:13 -0700  28) import android.widget.ListView;
00000000 (Not Committed Yet 2013-10-18 00:25:13 -0700  29) import android.widget.TextView;
00000000 (Not Committed Yet 2013-10-18 00:25:13 -0700  30) 
00000000 (Not Committed Yet 2013-10-18 00:25:13 -0700  31) import com.owncloud.android.DisplayUtils;
00000000 (Not Committed Yet 2013-10-18 00:25:13 -0700  32) import com.owncloud.android.R;
00000000 (Not Committed Yet 2013-10-18 00:25:13 -0700  33) import com.owncloud.android.authentication.AccountUtils;
00000000 (Not Committed Yet 2013-10-18 00:25:13 -0700  34) import com.owncloud.android.datamodel.DataStorageManager;
00000000 (Not Committed Yet 2013-10-18 00:25:13 -0700  35) import com.owncloud.android.datamodel.OCFile;
00000000 (Not Committed Yet 2013-10-18 00:25:13 -0700  36) import com.owncloud.android.files.services.FileDownloader.FileDownloaderBinder;
00000000 (Not Committed Yet 2013-10-18 00:25:13 -0700  37) import com.owncloud.android.files.services.FileUploader.FileUploaderBinder;
00000000 (Not Committed Yet 2013-10-18 00:25:13 -0700  38) import com.owncloud.android.ui.activity.TransferServiceGetter;
00000000 (Not Committed Yet 2013-10-18 00:25:13 -0700  39) 
00000000 (Not Committed Yet 2013-10-18 00:25:13 -0700  40) import java.util.Vector;
00000000 (Not Committed Yet 2013-10-18 00:25:13 -0700  41) 
00000000 (Not Committed Yet 2013-10-18 00:25:13 -0700  42) 
00000000 (Not Committed Yet 2013-10-18 00:25:13 -0700  43) /**
00000000 (Not Committed Yet 2013-10-18 00:25:13 -0700  44)  * This Adapter populates a ListView with all files and folders in an ownCloud
00000000 (Not Committed Yet 2013-10-18 00:25:13 -0700  45)  * instance.
00000000 (Not Committed Yet 2013-10-18 00:25:13 -0700  46)  * 
00000000 (Not Committed Yet 2013-10-18 00:25:13 -0700  47)  * @author Bartek Przybylski
00000000 (Not Committed Yet 2013-10-18 00:25:13 -0700  48)  * 
00000000 (Not Committed Yet 2013-10-18 00:25:13 -0700  49)  */
00000000 (Not Committed Yet 2013-10-18 00:25:13 -0700  50) public class FileListListAdapter extends BaseAdapter implements ListAdapter {
00000000 (Not Committed Yet 2013-10-18 00:25:13 -0700  51)     private Context mContext;
00000000 (Not Committed Yet 2013-10-18 00:25:13 -0700  52)     private OCFile mFile = null;
00000000 (Not Committed Yet 2013-10-18 00:25:13 -0700  53)     private Vector<OCFile> mFiles = null;
00000000 (Not Committed Yet 2013-10-18 00:25:13 -0700  54)     private DataStorageManager mStorageManager;
00000000 (Not Committed Yet 2013-10-18 00:25:13 -0700  55)     private Account mAccount;
00000000 (Not Committed Yet 2013-10-18 00:25:13 -0700  56)     private TransferServiceGetter mTransferServiceGetter;
00000000 (Not Committed Yet 2013-10-18 00:25:13 -0700  57)     //total size of a directory (recursive)
00000000 (Not Committed Yet 2013-10-18 00:25:13 -0700  58)     private Long totalSizeOfDirectoriesRecursive = null;
00000000 (Not Committed Yet 2013-10-18 00:25:13 -0700  59)     private Long lastModifiedOfAllSubdirectories = null;
00000000 (Not Committed Yet 2013-10-18 00:25:13 -0700  60)     
00000000 (Not Committed Yet 2013-10-18 00:25:13 -0700  61)     public FileListListAdapter(Context context, TransferServiceGetter transferServiceGetter) {
00000000 (Not Committed Yet 2013-10-18 00:25:13 -0700  62)         mContext = context;
00000000 (Not Committed Yet 2013-10-18 00:25:13 -0700  63)         mAccount = AccountUtils.getCurrentOwnCloudAccount(mContext);
00000000 (Not Committed Yet 2013-10-18 00:25:13 -0700  64)         mTransferServiceGetter = transferServiceGetter;
00000000 (Not Committed Yet 2013-10-18 00:25:13 -0700  65)     }
00000000 (Not Committed Yet 2013-10-18 00:25:13 -0700  66) 
00000000 (Not Committed Yet 2013-10-18 00:25:13 -0700  67)     @Override
00000000 (Not Committed Yet 2013-10-18 00:25:13 -0700  68)     public boolean areAllItemsEnabled() {
00000000 (Not Committed Yet 2013-10-18 00:25:13 -0700  69)         return true;
00000000 (Not Committed Yet 2013-10-18 00:25:13 -0700  70)     }
00000000 (Not Committed Yet 2013-10-18 00:25:13 -0700  71) 
00000000 (Not Committed Yet 2013-10-18 00:25:13 -0700  72)     @Override
00000000 (Not Committed Yet 2013-10-18 00:25:13 -0700  73)     public boolean isEnabled(int position) {
00000000 (Not Committed Yet 2013-10-18 00:25:13 -0700  74)         return true;
00000000 (Not Committed Yet 2013-10-18 00:25:13 -0700  75)     }
00000000 (Not Committed Yet 2013-10-18 00:25:13 -0700  76) 
00000000 (Not Committed Yet 2013-10-18 00:25:13 -0700  77)     @Override
00000000 (Not Committed Yet 2013-10-18 00:25:13 -0700  78)     public int getCount() {
00000000 (Not Committed Yet 2013-10-18 00:25:13 -0700  79)         return mFiles != null ? mFiles.size() : 0;
00000000 (Not Committed Yet 2013-10-18 00:25:13 -0700  80)     }
00000000 (Not Committed Yet 2013-10-18 00:25:13 -0700  81) 
00000000 (Not Committed Yet 2013-10-18 00:25:13 -0700  82)     @Override
00000000 (Not Committed Yet 2013-10-18 00:25:13 -0700  83)     public Object getItem(int position) {
00000000 (Not Committed Yet 2013-10-18 00:25:13 -0700  84)         if (mFiles == null || mFiles.size() <= position)
00000000 (Not Committed Yet 2013-10-18 00:25:13 -0700  85)             return null;
00000000 (Not Committed Yet 2013-10-18 00:25:13 -0700  86)         return mFiles.get(position);
00000000 (Not Committed Yet 2013-10-18 00:25:13 -0700  87)     }
00000000 (Not Committed Yet 2013-10-18 00:25:13 -0700  88) 
00000000 (Not Committed Yet 2013-10-18 00:25:13 -0700  89)     @Override
00000000 (Not Committed Yet 2013-10-18 00:25:13 -0700  90)     public long getItemId(int position) {
00000000 (Not Committed Yet 2013-10-18 00:25:13 -0700  91)         if (mFiles == null || mFiles.size() <= position)
00000000 (Not Committed Yet 2013-10-18 00:25:13 -0700  92)             return 0;
00000000 (Not Committed Yet 2013-10-18 00:25:13 -0700  93)         return mFiles.get(position).getFileId();
00000000 (Not Committed Yet 2013-10-18 00:25:13 -0700  94)     }
00000000 (Not Committed Yet 2013-10-18 00:25:13 -0700  95) 
00000000 (Not Committed Yet 2013-10-18 00:25:13 -0700  96)     @Override
00000000 (Not Committed Yet 2013-10-18 00:25:13 -0700  97)     public int getItemViewType(int position) {
00000000 (Not Committed Yet 2013-10-18 00:25:13 -0700  98)         return 0;
00000000 (Not Committed Yet 2013-10-18 00:25:13 -0700  99)     }
00000000 (Not Committed Yet 2013-10-18 00:25:13 -0700 100) 
00000000 (Not Committed Yet 2013-10-18 00:25:13 -0700 101)     @Override
00000000 (Not Committed Yet 2013-10-18 00:25:13 -0700 102)     public View getView(int position, View convertView, ViewGroup parent) {
00000000 (Not Committed Yet 2013-10-18 00:25:13 -0700 103)         View view = convertView;
00000000 (Not Committed Yet 2013-10-18 00:25:13 -0700 104)         if (view == null) {
00000000 (Not Committed Yet 2013-10-18 00:25:13 -0700 105)             LayoutInflater inflator = (LayoutInflater) mContext
00000000 (Not Committed Yet 2013-10-18 00:25:13 -0700 106)                     .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
00000000 (Not Committed Yet 2013-10-18 00:25:13 -0700 107)             view = inflator.inflate(R.layout.list_item, null);
00000000 (Not Committed Yet 2013-10-18 00:25:13 -0700 108)         }
00000000 (Not Committed Yet 2013-10-18 00:25:13 -0700 109)     
00000000 (Not Committed Yet 2013-10-18 00:25:13 -0700 110)         if (mFiles != null && mFiles.size() > position) {
00000000 (Not Committed Yet 2013-10-18 00:25:13 -0700 111)             OCFile file = mFiles.get(position);
00000000 (Not Committed Yet 2013-10-18 00:25:13 -0700 112)             TextView fileName = (TextView) view.findViewById(R.id.Filename);
00000000 (Not Committed Yet 2013-10-18 00:25:13 -0700 113)             String name = file.getFileName();
00000000 (Not Committed Yet 2013-10-18 00:25:13 -0700 114) 
00000000 (Not Committed Yet 2013-10-18 00:25:13 -0700 115)             fileName.setText(name);
00000000 (Not Committed Yet 2013-10-18 00:25:13 -0700 116)             ImageView fileIcon = (ImageView) view.findViewById(R.id.imageView1);
00000000 (Not Committed Yet 2013-10-18 00:25:13 -0700 117)             fileIcon.setImageResource(DisplayUtils.getResourceId(file.getMimetype()));
00000000 (Not Committed Yet 2013-10-18 00:25:13 -0700 118)             ImageView localStateView = (ImageView) view.findViewById(R.id.imageView2);
00000000 (Not Committed Yet 2013-10-18 00:25:13 -0700 119)             FileDownloaderBinder downloaderBinder = mTransferServiceGetter.getFileDownloaderBinder();
00000000 (Not Committed Yet 2013-10-18 00:25:13 -0700 120)             FileUploaderBinder uploaderBinder = mTransferServiceGetter.getFileUploaderBinder();
00000000 (Not Committed Yet 2013-10-18 00:25:13 -0700 121)             if (downloaderBinder != null && downloaderBinder.isDownloading(mAccount, file)) {
00000000 (Not Committed Yet 2013-10-18 00:25:13 -0700 122)                 localStateView.setImageResource(R.drawable.downloading_file_indicator);
00000000 (Not Committed Yet 2013-10-18 00:25:13 -0700 123)                 localStateView.setVisibility(View.VISIBLE);
00000000 (Not Committed Yet 2013-10-18 00:25:13 -0700 124)             } else if (uploaderBinder != null && uploaderBinder.isUploading(mAccount, file)) {
00000000 (Not Committed Yet 2013-10-18 00:25:13 -0700 125)                 localStateView.setImageResource(R.drawable.uploading_file_indicator);
00000000 (Not Committed Yet 2013-10-18 00:25:13 -0700 126)                 localStateView.setVisibility(View.VISIBLE);
00000000 (Not Committed Yet 2013-10-18 00:25:13 -0700 127)             } else if (file.isDown()) {
00000000 (Not Committed Yet 2013-10-18 00:25:13 -0700 128)                 localStateView.setImageResource(R.drawable.local_file_indicator);
00000000 (Not Committed Yet 2013-10-18 00:25:13 -0700 129)                 localStateView.setVisibility(View.VISIBLE);
00000000 (Not Committed Yet 2013-10-18 00:25:13 -0700 130)             } else {
00000000 (Not Committed Yet 2013-10-18 00:25:13 -0700 131)                 localStateView.setVisibility(View.INVISIBLE);
00000000 (Not Committed Yet 2013-10-18 00:25:13 -0700 132)             }
00000000 (Not Committed Yet 2013-10-18 00:25:13 -0700 133)             
00000000 (Not Committed Yet 2013-10-18 00:25:13 -0700 134)             TextView fileSizeV = (TextView) view.findViewById(R.id.file_size);
00000000 (Not Committed Yet 2013-10-18 00:25:13 -0700 135)             TextView lastModV = (TextView) view.findViewById(R.id.last_mod);
00000000 (Not Committed Yet 2013-10-18 00:25:13 -0700 136)             ImageView checkBoxV = (ImageView) view.findViewById(R.id.custom_checkbox);
00000000 (Not Committed Yet 2013-10-18 00:25:13 -0700 137)             
00000000 (Not Committed Yet 2013-10-18 00:25:13 -0700 138)             if (!file.isDirectory()) {
00000000 (Not Committed Yet 2013-10-18 00:25:13 -0700 139)                 fileSizeV.setVisibility(View.VISIBLE);
00000000 (Not Committed Yet 2013-10-18 00:25:13 -0700 140)                 fileSizeV.setText(DisplayUtils.bytesToHumanReadable(file.getFileLength()));
00000000 (Not Committed Yet 2013-10-18 00:25:13 -0700 141)                 lastModV.setVisibility(View.VISIBLE);
00000000 (Not Committed Yet 2013-10-18 00:25:13 -0700 142)                 lastModV.setText(DisplayUtils.unixTimeToHumanReadable(file.getModificationTimestamp()));
00000000 (Not Committed Yet 2013-10-18 00:25:13 -0700 143)                 // this if-else is needed even thoe fav icon is visible by default
00000000 (Not Committed Yet 2013-10-18 00:25:13 -0700 144)                 // because android reuses views in listview
00000000 (Not Committed Yet 2013-10-18 00:25:13 -0700 145)                 if (!file.keepInSync()) {
00000000 (Not Committed Yet 2013-10-18 00:25:13 -0700 146)                     view.findViewById(R.id.imageView3).setVisibility(View.GONE);
00000000 (Not Committed Yet 2013-10-18 00:25:13 -0700 147)                 } else {
00000000 (Not Committed Yet 2013-10-18 00:25:13 -0700 148)                     view.findViewById(R.id.imageView3).setVisibility(View.VISIBLE);
00000000 (Not Committed Yet 2013-10-18 00:25:13 -0700 149)                 }
00000000 (Not Committed Yet 2013-10-18 00:25:13 -0700 150)                 
00000000 (Not Committed Yet 2013-10-18 00:25:13 -0700 151)                 ListView parentList = (ListView)parent;
00000000 (Not Committed Yet 2013-10-18 00:25:13 -0700 152)                 if (parentList.getChoiceMode() == ListView.CHOICE_MODE_NONE) { 
00000000 (Not Committed Yet 2013-10-18 00:25:13 -0700 153)                     checkBoxV.setVisibility(View.GONE);
00000000 (Not Committed Yet 2013-10-18 00:25:13 -0700 154)                 } else {
00000000 (Not Committed Yet 2013-10-18 00:25:13 -0700 155)                     if (parentList.isItemChecked(position)) {
00000000 (Not Committed Yet 2013-10-18 00:25:13 -0700 156)                         checkBoxV.setImageResource(android.R.drawable.checkbox_on_background);
00000000 (Not Committed Yet 2013-10-18 00:25:13 -0700 157)                     } else {
00000000 (Not Committed Yet 2013-10-18 00:25:13 -0700 158)                         checkBoxV.setImageResource(android.R.drawable.checkbox_off_background);
00000000 (Not Committed Yet 2013-10-18 00:25:13 -0700 159)                     }
00000000 (Not Committed Yet 2013-10-18 00:25:13 -0700 160)                     checkBoxV.setVisibility(View.VISIBLE);
00000000 (Not Committed Yet 2013-10-18 00:25:13 -0700 161)                 }
00000000 (Not Committed Yet 2013-10-18 00:25:13 -0700 162)                 
00000000 (Not Committed Yet 2013-10-18 00:25:13 -0700 163)             } 
00000000 (Not Committed Yet 2013-10-18 00:25:13 -0700 164)             else {
00000000 (Not Committed Yet 2013-10-18 00:25:13 -0700 165)                 
00000000 (Not Committed Yet 2013-10-18 00:25:13 -0700 166)                 fileSizeV.setVisibility(View.VISIBLE);
00000000 (Not Committed Yet 2013-10-18 00:25:13 -0700 167)                 fileSizeV.setText(DisplayUtils.bytesToHumanReadable(file.getFileLength()));
00000000 (Not Committed Yet 2013-10-18 00:25:13 -0700 168)                 lastModV.setVisibility(View.VISIBLE);
00000000 (Not Committed Yet 2013-10-18 00:25:13 -0700 169)                 lastModV.setText(DisplayUtils.unixTimeToHumanReadable(file.getModificationTimestamp()));
00000000 (Not Committed Yet 2013-10-18 00:25:13 -0700 170)                checkBoxV.setVisibility(View.GONE);
00000000 (Not Committed Yet 2013-10-18 00:25:13 -0700 171)                view.findViewById(R.id.imageView3).setVisibility(View.GONE);
00000000 (Not Committed Yet 2013-10-18 00:25:13 -0700 172)             }
00000000 (Not Committed Yet 2013-10-18 00:25:13 -0700 173)         }
00000000 (Not Committed Yet 2013-10-18 00:25:13 -0700 174) 
00000000 (Not Committed Yet 2013-10-18 00:25:13 -0700 175)         return view;
00000000 (Not Committed Yet 2013-10-18 00:25:13 -0700 176)     }
00000000 (Not Committed Yet 2013-10-18 00:25:13 -0700 177) 
00000000 (Not Committed Yet 2013-10-18 00:25:13 -0700 178)     @Override
00000000 (Not Committed Yet 2013-10-18 00:25:13 -0700 179)     public int getViewTypeCount() {
00000000 (Not Committed Yet 2013-10-18 00:25:13 -0700 180)         return 1;
00000000 (Not Committed Yet 2013-10-18 00:25:13 -0700 181)     }
00000000 (Not Committed Yet 2013-10-18 00:25:13 -0700 182) 
00000000 (Not Committed Yet 2013-10-18 00:25:13 -0700 183)     @Override
00000000 (Not Committed Yet 2013-10-18 00:25:13 -0700 184)     public boolean hasStableIds() {
00000000 (Not Committed Yet 2013-10-18 00:25:13 -0700 185)         return true;
00000000 (Not Committed Yet 2013-10-18 00:25:13 -0700 186)     }
00000000 (Not Committed Yet 2013-10-18 00:25:13 -0700 187) 
00000000 (Not Committed Yet 2013-10-18 00:25:13 -0700 188)     @Override
00000000 (Not Committed Yet 2013-10-18 00:25:13 -0700 189)     public boolean isEmpty() {
00000000 (Not Committed Yet 2013-10-18 00:25:13 -0700 190)         return (mFiles == null || mFiles.isEmpty());
00000000 (Not Committed Yet 2013-10-18 00:25:13 -0700 191)     }
00000000 (Not Committed Yet 2013-10-18 00:25:13 -0700 192) 
00000000 (Not Committed Yet 2013-10-18 00:25:13 -0700 193)     /**
00000000 (Not Committed Yet 2013-10-18 00:25:13 -0700 194)      * Change the adapted directory for a new one
00000000 (Not Committed Yet 2013-10-18 00:25:13 -0700 195)      * @param directory                 New file to adapt. Can be NULL, meaning "no content to adapt".
00000000 (Not Committed Yet 2013-10-18 00:25:13 -0700 196)      * @param updatedStorageManager     Optional updated storage manager; used to replace mStorageManager if is different (and not NULL)
00000000 (Not Committed Yet 2013-10-18 00:25:13 -0700 197)      */
00000000 (Not Committed Yet 2013-10-18 00:25:13 -0700 198)     public void swapDirectory(OCFile directory, DataStorageManager updatedStorageManager) {
00000000 (Not Committed Yet 2013-10-18 00:25:13 -0700 199)         mFile = directory;
00000000 (Not Committed Yet 2013-10-18 00:25:13 -0700 200)         if (updatedStorageManager != null && updatedStorageManager != mStorageManager) {
00000000 (Not Committed Yet 2013-10-18 00:25:13 -0700 201)             mStorageManager = updatedStorageManager;
00000000 (Not Committed Yet 2013-10-18 00:25:13 -0700 202)             mAccount = AccountUtils.getCurrentOwnCloudAccount(mContext);
00000000 (Not Committed Yet 2013-10-18 00:25:13 -0700 203)         }
00000000 (Not Committed Yet 2013-10-18 00:25:13 -0700 204)         if (mStorageManager != null) {
00000000 (Not Committed Yet 2013-10-18 00:25:13 -0700 205)             mFiles = mStorageManager.getDirectoryContent(mFile);
00000000 (Not Committed Yet 2013-10-18 00:25:13 -0700 206)         } else {
00000000 (Not Committed Yet 2013-10-18 00:25:13 -0700 207)             mFiles = null;
00000000 (Not Committed Yet 2013-10-18 00:25:13 -0700 208)         }
00000000 (Not Committed Yet 2013-10-18 00:25:13 -0700 209)         notifyDataSetChanged();
00000000 (Not Committed Yet 2013-10-18 00:25:13 -0700 210)     }
00000000 (Not Committed Yet 2013-10-18 00:25:13 -0700 211)     
00000000 (Not Committed Yet 2013-10-18 00:25:13 -0700 212) }
