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
1ecd2b61 (David A. Velasco  2012-08-14 12:37:08 +0200  18) package com.owncloud.android.ui.fragment;
1ecd2b61 (David A. Velasco  2012-08-14 12:37:08 +0200  19) 
1ecd2b61 (David A. Velasco  2012-08-14 12:37:08 +0200  20) import java.io.File;
1ecd2b61 (David A. Velasco  2012-08-14 12:37:08 +0200  21) 
1ecd2b61 (David A. Velasco  2012-08-14 12:37:08 +0200  22) import com.owncloud.android.ui.adapter.LocalFileListAdapter;
1ecd2b61 (David A. Velasco  2012-08-14 12:37:08 +0200  23) 
1ecd2b61 (David A. Velasco  2012-08-14 12:37:08 +0200  24) import android.app.Activity;
1ecd2b61 (David A. Velasco  2012-08-14 12:37:08 +0200  25) import android.os.Bundle;
1ecd2b61 (David A. Velasco  2012-08-14 12:37:08 +0200  26) import android.os.Environment;
1ecd2b61 (David A. Velasco  2012-08-14 12:37:08 +0200  27) import android.util.SparseBooleanArray;
1ecd2b61 (David A. Velasco  2012-08-14 12:37:08 +0200  28) import android.view.LayoutInflater;
1ecd2b61 (David A. Velasco  2012-08-14 12:37:08 +0200  29) import android.view.View;
1ecd2b61 (David A. Velasco  2012-08-14 12:37:08 +0200  30) import android.view.ViewGroup;
1ecd2b61 (David A. Velasco  2012-08-14 12:37:08 +0200  31) import android.widget.AdapterView;
1f57ae3b (Bartek Przybylski 2012-08-18 12:02:21 +0200  32) import android.widget.ImageView;
1ecd2b61 (David A. Velasco  2012-08-14 12:37:08 +0200  33) import android.widget.ListView;
1ecd2b61 (David A. Velasco  2012-08-14 12:37:08 +0200  34) 
8e36e7cc (zerginator        2013-03-12 07:56:27 +0100  35) import com.owncloud.android.Log_OC;
1ecd2b61 (David A. Velasco  2012-08-14 12:37:08 +0200  36) import com.owncloud.android.R;
1ecd2b61 (David A. Velasco  2012-08-14 12:37:08 +0200  37) 
1ecd2b61 (David A. Velasco  2012-08-14 12:37:08 +0200  38) /**
1ecd2b61 (David A. Velasco  2012-08-14 12:37:08 +0200  39)  * A Fragment that lists all files and folders in a given LOCAL path.
1ecd2b61 (David A. Velasco  2012-08-14 12:37:08 +0200  40)  * 
1ecd2b61 (David A. Velasco  2012-08-14 12:37:08 +0200  41)  * @author David A. Velasco
1ecd2b61 (David A. Velasco  2012-08-14 12:37:08 +0200  42)  * 
1ecd2b61 (David A. Velasco  2012-08-14 12:37:08 +0200  43)  */
fd396289 (David A. Velasco  2013-05-30 17:53:21 +0200  44) public class LocalFileListFragment extends ExtendedListFragment {
1ecd2b61 (David A. Velasco  2012-08-14 12:37:08 +0200  45)     private static final String TAG = "LocalFileListFragment";
1ecd2b61 (David A. Velasco  2012-08-14 12:37:08 +0200  46)     
1ecd2b61 (David A. Velasco  2012-08-14 12:37:08 +0200  47)     /** Reference to the Activity which this fragment is attached to. For callbacks */
1ecd2b61 (David A. Velasco  2012-08-14 12:37:08 +0200  48)     private LocalFileListFragment.ContainerActivity mContainerActivity;
1ecd2b61 (David A. Velasco  2012-08-14 12:37:08 +0200  49)     
1ecd2b61 (David A. Velasco  2012-08-14 12:37:08 +0200  50)     /** Directory to show */
1ecd2b61 (David A. Velasco  2012-08-14 12:37:08 +0200  51)     private File mDirectory = null;
1ecd2b61 (David A. Velasco  2012-08-14 12:37:08 +0200  52)     
1ecd2b61 (David A. Velasco  2012-08-14 12:37:08 +0200  53)     /** Adapter to connect the data from the directory with the View object */
1ecd2b61 (David A. Velasco  2012-08-14 12:37:08 +0200  54)     private LocalFileListAdapter mAdapter = null;
1ecd2b61 (David A. Velasco  2012-08-14 12:37:08 +0200  55) 
1ecd2b61 (David A. Velasco  2012-08-14 12:37:08 +0200  56)     
1ecd2b61 (David A. Velasco  2012-08-14 12:37:08 +0200  57)     /**
1ecd2b61 (David A. Velasco  2012-08-14 12:37:08 +0200  58)      * {@inheritDoc}
1ecd2b61 (David A. Velasco  2012-08-14 12:37:08 +0200  59)      */
1ecd2b61 (David A. Velasco  2012-08-14 12:37:08 +0200  60)     @Override
1ecd2b61 (David A. Velasco  2012-08-14 12:37:08 +0200  61)     public void onAttach(Activity activity) {
1ecd2b61 (David A. Velasco  2012-08-14 12:37:08 +0200  62)         super.onAttach(activity);
1ecd2b61 (David A. Velasco  2012-08-14 12:37:08 +0200  63)         try {
1ecd2b61 (David A. Velasco  2012-08-14 12:37:08 +0200  64)             mContainerActivity = (ContainerActivity) activity;
1ecd2b61 (David A. Velasco  2012-08-14 12:37:08 +0200  65)         } catch (ClassCastException e) {
c6a3abf0 (David A. Velasco  2012-10-26 13:45:59 +0200  66)             throw new ClassCastException(activity.toString() + " must implement " + LocalFileListFragment.ContainerActivity.class.getSimpleName());
1ecd2b61 (David A. Velasco  2012-08-14 12:37:08 +0200  67)         }
1ecd2b61 (David A. Velasco  2012-08-14 12:37:08 +0200  68)     }
1ecd2b61 (David A. Velasco  2012-08-14 12:37:08 +0200  69)     
1ecd2b61 (David A. Velasco  2012-08-14 12:37:08 +0200  70)     
1ecd2b61 (David A. Velasco  2012-08-14 12:37:08 +0200  71)     /**
1ecd2b61 (David A. Velasco  2012-08-14 12:37:08 +0200  72)      * {@inheritDoc}
1ecd2b61 (David A. Velasco  2012-08-14 12:37:08 +0200  73)      */
1ecd2b61 (David A. Velasco  2012-08-14 12:37:08 +0200  74)     @Override
1ecd2b61 (David A. Velasco  2012-08-14 12:37:08 +0200  75)     public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
8e36e7cc (zerginator        2013-03-12 07:56:27 +0100  76)         Log_OC.i(TAG, "onCreateView() start");
d0f7de3c (David A. Velasco  2012-10-17 19:22:40 +0200  77)         View v = super.onCreateView(inflater, container, savedInstanceState);
1ecd2b61 (David A. Velasco  2012-08-14 12:37:08 +0200  78)         getListView().setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);
8e36e7cc (zerginator        2013-03-12 07:56:27 +0100  79)         Log_OC.i(TAG, "onCreateView() end");
d0f7de3c (David A. Velasco  2012-10-17 19:22:40 +0200  80)         return v;
1ecd2b61 (David A. Velasco  2012-08-14 12:37:08 +0200  81)     }    
1ecd2b61 (David A. Velasco  2012-08-14 12:37:08 +0200  82) 
1ecd2b61 (David A. Velasco  2012-08-14 12:37:08 +0200  83) 
1ecd2b61 (David A. Velasco  2012-08-14 12:37:08 +0200  84)     /**
1ecd2b61 (David A. Velasco  2012-08-14 12:37:08 +0200  85)      * {@inheritDoc}
1ecd2b61 (David A. Velasco  2012-08-14 12:37:08 +0200  86)      */
1ecd2b61 (David A. Velasco  2012-08-14 12:37:08 +0200  87)     @Override
1ecd2b61 (David A. Velasco  2012-08-14 12:37:08 +0200  88)     public void onActivityCreated(Bundle savedInstanceState) {
8e36e7cc (zerginator        2013-03-12 07:56:27 +0100  89)         Log_OC.i(TAG, "onActivityCreated() start");
1ecd2b61 (David A. Velasco  2012-08-14 12:37:08 +0200  90)         
1ecd2b61 (David A. Velasco  2012-08-14 12:37:08 +0200  91)         super.onCreate(savedInstanceState);
a2d4e98a (David A. Velasco  2012-10-03 16:48:06 +0200  92)         mAdapter = new LocalFileListAdapter(mContainerActivity.getInitialDirectory(), getActivity());
a2d4e98a (David A. Velasco  2012-10-03 16:48:06 +0200  93)         setListAdapter(mAdapter);
a2d4e98a (David A. Velasco  2012-10-03 16:48:06 +0200  94)         
8e36e7cc (zerginator        2013-03-12 07:56:27 +0100  95)         Log_OC.i(TAG, "onActivityCreated() stop");
a2d4e98a (David A. Velasco  2012-10-03 16:48:06 +0200  96)     }
a2d4e98a (David A. Velasco  2012-10-03 16:48:06 +0200  97)     
a2d4e98a (David A. Velasco  2012-10-03 16:48:06 +0200  98)     
1ecd2b61 (David A. Velasco  2012-08-14 12:37:08 +0200  99)     /**
1ecd2b61 (David A. Velasco  2012-08-14 12:37:08 +0200 100)      * Checks the file clicked over. Browses inside if it is a directory. Notifies the container activity in any case.
1ecd2b61 (David A. Velasco  2012-08-14 12:37:08 +0200 101)      */
1ecd2b61 (David A. Velasco  2012-08-14 12:37:08 +0200 102)     @Override
1ecd2b61 (David A. Velasco  2012-08-14 12:37:08 +0200 103)     public void onItemClick(AdapterView<?> l, View v, int position, long id) {
1ecd2b61 (David A. Velasco  2012-08-14 12:37:08 +0200 104)         File file = (File) mAdapter.getItem(position); 
1ecd2b61 (David A. Velasco  2012-08-14 12:37:08 +0200 105)         if (file != null) {
1ecd2b61 (David A. Velasco  2012-08-14 12:37:08 +0200 106)             /// Click on a directory
1ecd2b61 (David A. Velasco  2012-08-14 12:37:08 +0200 107)             if (file.isDirectory()) {
1ecd2b61 (David A. Velasco  2012-08-14 12:37:08 +0200 108)                 // just local updates
1ecd2b61 (David A. Velasco  2012-08-14 12:37:08 +0200 109)                 listDirectory(file);
1ecd2b61 (David A. Velasco  2012-08-14 12:37:08 +0200 110)                 // notify the click to container Activity
1ecd2b61 (David A. Velasco  2012-08-14 12:37:08 +0200 111)                 mContainerActivity.onDirectoryClick(file);
1ecd2b61 (David A. Velasco  2012-08-14 12:37:08 +0200 112)             
1ecd2b61 (David A. Velasco  2012-08-14 12:37:08 +0200 113)             } else {    /// Click on a file
1f57ae3b (Bartek Przybylski 2012-08-18 12:02:21 +0200 114)                 ImageView checkBoxV = (ImageView) v.findViewById(R.id.custom_checkbox);
1f57ae3b (Bartek Przybylski 2012-08-18 12:02:21 +0200 115)                 if (checkBoxV != null) {
1f57ae3b (Bartek Przybylski 2012-08-18 12:02:21 +0200 116)                     if (getListView().isItemChecked(position)) {
1f57ae3b (Bartek Przybylski 2012-08-18 12:02:21 +0200 117)                         checkBoxV.setImageResource(android.R.drawable.checkbox_on_background);
1f57ae3b (Bartek Przybylski 2012-08-18 12:02:21 +0200 118)                     } else {
1f57ae3b (Bartek Przybylski 2012-08-18 12:02:21 +0200 119)                         checkBoxV.setImageResource(android.R.drawable.checkbox_off_background);
1f57ae3b (Bartek Przybylski 2012-08-18 12:02:21 +0200 120)                     }
1f57ae3b (Bartek Przybylski 2012-08-18 12:02:21 +0200 121)                 }
1ecd2b61 (David A. Velasco  2012-08-14 12:37:08 +0200 122)                 // notify the change to the container Activity
1ecd2b61 (David A. Velasco  2012-08-14 12:37:08 +0200 123)                 mContainerActivity.onFileClick(file);
1ecd2b61 (David A. Velasco  2012-08-14 12:37:08 +0200 124)             }
1ecd2b61 (David A. Velasco  2012-08-14 12:37:08 +0200 125)             
1ecd2b61 (David A. Velasco  2012-08-14 12:37:08 +0200 126)         } else {
8e36e7cc (zerginator        2013-03-12 07:56:27 +0100 127)             Log_OC.w(TAG, "Null object in ListAdapter!!");
1ecd2b61 (David A. Velasco  2012-08-14 12:37:08 +0200 128)         }
1ecd2b61 (David A. Velasco  2012-08-14 12:37:08 +0200 129)     }
1ecd2b61 (David A. Velasco  2012-08-14 12:37:08 +0200 130) 
1ecd2b61 (David A. Velasco  2012-08-14 12:37:08 +0200 131)     
1ecd2b61 (David A. Velasco  2012-08-14 12:37:08 +0200 132)     /**
1ecd2b61 (David A. Velasco  2012-08-14 12:37:08 +0200 133)      * Call this, when the user presses the up button
1ecd2b61 (David A. Velasco  2012-08-14 12:37:08 +0200 134)      */
1ecd2b61 (David A. Velasco  2012-08-14 12:37:08 +0200 135)     public void onNavigateUp() {
1ecd2b61 (David A. Velasco  2012-08-14 12:37:08 +0200 136)         File parentDir = null;
1ecd2b61 (David A. Velasco  2012-08-14 12:37:08 +0200 137)         if(mDirectory != null) {
1ecd2b61 (David A. Velasco  2012-08-14 12:37:08 +0200 138)             parentDir = mDirectory.getParentFile();  // can be null
1ecd2b61 (David A. Velasco  2012-08-14 12:37:08 +0200 139)         }
1ecd2b61 (David A. Velasco  2012-08-14 12:37:08 +0200 140)         listDirectory(parentDir);
1ecd2b61 (David A. Velasco  2012-08-14 12:37:08 +0200 141)     }
1ecd2b61 (David A. Velasco  2012-08-14 12:37:08 +0200 142) 
1ecd2b61 (David A. Velasco  2012-08-14 12:37:08 +0200 143)     
1ecd2b61 (David A. Velasco  2012-08-14 12:37:08 +0200 144)     /**
1ecd2b61 (David A. Velasco  2012-08-14 12:37:08 +0200 145)      * Use this to query the {@link File} object for the directory
1ecd2b61 (David A. Velasco  2012-08-14 12:37:08 +0200 146)      * that is currently being displayed by this fragment
1ecd2b61 (David A. Velasco  2012-08-14 12:37:08 +0200 147)      * 
1ecd2b61 (David A. Velasco  2012-08-14 12:37:08 +0200 148)      * @return File     The currently displayed directory
1ecd2b61 (David A. Velasco  2012-08-14 12:37:08 +0200 149)      */
1ecd2b61 (David A. Velasco  2012-08-14 12:37:08 +0200 150)     public File getCurrentDirectory(){
1ecd2b61 (David A. Velasco  2012-08-14 12:37:08 +0200 151)         return mDirectory;
1ecd2b61 (David A. Velasco  2012-08-14 12:37:08 +0200 152)     }
1ecd2b61 (David A. Velasco  2012-08-14 12:37:08 +0200 153)     
1ecd2b61 (David A. Velasco  2012-08-14 12:37:08 +0200 154)     
1ecd2b61 (David A. Velasco  2012-08-14 12:37:08 +0200 155)     /**
1ecd2b61 (David A. Velasco  2012-08-14 12:37:08 +0200 156)      * Calls {@link LocalFileListFragment#listDirectory(File)} with a null parameter
1ecd2b61 (David A. Velasco  2012-08-14 12:37:08 +0200 157)      * to refresh the current directory.
1ecd2b61 (David A. Velasco  2012-08-14 12:37:08 +0200 158)      */
1ecd2b61 (David A. Velasco  2012-08-14 12:37:08 +0200 159)     public void listDirectory(){
1ecd2b61 (David A. Velasco  2012-08-14 12:37:08 +0200 160)         listDirectory(null);
1ecd2b61 (David A. Velasco  2012-08-14 12:37:08 +0200 161)     }
1ecd2b61 (David A. Velasco  2012-08-14 12:37:08 +0200 162)     
1ecd2b61 (David A. Velasco  2012-08-14 12:37:08 +0200 163)     
1ecd2b61 (David A. Velasco  2012-08-14 12:37:08 +0200 164)     /**
1ecd2b61 (David A. Velasco  2012-08-14 12:37:08 +0200 165)      * Lists the given directory on the view. When the input parameter is null,
bdc0332c (David A. Velasco  2013-04-17 12:26:13 +0200 166)      * it will either refresh the last known directory. list the root
1ecd2b61 (David A. Velasco  2012-08-14 12:37:08 +0200 167)      * if there never was a directory.
1ecd2b61 (David A. Velasco  2012-08-14 12:37:08 +0200 168)      * 
1ecd2b61 (David A. Velasco  2012-08-14 12:37:08 +0200 169)      * @param directory     Directory to be listed
1ecd2b61 (David A. Velasco  2012-08-14 12:37:08 +0200 170)      */
1ecd2b61 (David A. Velasco  2012-08-14 12:37:08 +0200 171)     public void listDirectory(File directory) {
1ecd2b61 (David A. Velasco  2012-08-14 12:37:08 +0200 172)         
1ecd2b61 (David A. Velasco  2012-08-14 12:37:08 +0200 173)         // Check input parameters for null
1ecd2b61 (David A. Velasco  2012-08-14 12:37:08 +0200 174)         if(directory == null) {
1ecd2b61 (David A. Velasco  2012-08-14 12:37:08 +0200 175)             if(mDirectory != null){
1ecd2b61 (David A. Velasco  2012-08-14 12:37:08 +0200 176)                 directory = mDirectory;
1ecd2b61 (David A. Velasco  2012-08-14 12:37:08 +0200 177)             } else {
1ecd2b61 (David A. Velasco  2012-08-14 12:37:08 +0200 178)                 directory = Environment.getExternalStorageDirectory();  // TODO be careful with the state of the storage; could not be available
a2d4e98a (David A. Velasco  2012-10-03 16:48:06 +0200 179)                 if (directory == null) return; // no files to show
1ecd2b61 (David A. Velasco  2012-08-14 12:37:08 +0200 180)             }
1ecd2b61 (David A. Velasco  2012-08-14 12:37:08 +0200 181)         }
1ecd2b61 (David A. Velasco  2012-08-14 12:37:08 +0200 182)         
1ecd2b61 (David A. Velasco  2012-08-14 12:37:08 +0200 183)         
1ecd2b61 (David A. Velasco  2012-08-14 12:37:08 +0200 184)         // if that's not a directory -> List its parent
1ecd2b61 (David A. Velasco  2012-08-14 12:37:08 +0200 185)         if(!directory.isDirectory()){
8e36e7cc (zerginator        2013-03-12 07:56:27 +0100 186)             Log_OC.w(TAG, "You see, that is not a directory -> " + directory.toString());
1ecd2b61 (David A. Velasco  2012-08-14 12:37:08 +0200 187)             directory = directory.getParentFile();
1ecd2b61 (David A. Velasco  2012-08-14 12:37:08 +0200 188)         }
1ecd2b61 (David A. Velasco  2012-08-14 12:37:08 +0200 189) 
a2d4e98a (David A. Velasco  2012-10-03 16:48:06 +0200 190)         mList.clearChoices();   // by now, only files in the same directory will be kept as selected
0bb59e0f (David A. Velasco  2012-10-17 13:44:36 +0200 191)         mAdapter.swapDirectory(directory);
0bb59e0f (David A. Velasco  2012-10-17 13:44:36 +0200 192)         if (mDirectory == null || !mDirectory.equals(directory)) {
0bb59e0f (David A. Velasco  2012-10-17 13:44:36 +0200 193)             mList.setSelectionFromTop(0, 0);
0bb59e0f (David A. Velasco  2012-10-17 13:44:36 +0200 194)         }
0bb59e0f (David A. Velasco  2012-10-17 13:44:36 +0200 195)         mDirectory = directory;
1ecd2b61 (David A. Velasco  2012-08-14 12:37:08 +0200 196)     }
1ecd2b61 (David A. Velasco  2012-08-14 12:37:08 +0200 197)     
1ecd2b61 (David A. Velasco  2012-08-14 12:37:08 +0200 198) 
1ecd2b61 (David A. Velasco  2012-08-14 12:37:08 +0200 199)     /**
1ecd2b61 (David A. Velasco  2012-08-14 12:37:08 +0200 200)      * Returns the fule paths to the files checked by the user
1ecd2b61 (David A. Velasco  2012-08-14 12:37:08 +0200 201)      * 
1ecd2b61 (David A. Velasco  2012-08-14 12:37:08 +0200 202)      * @return      File paths to the files checked by the user.
1ecd2b61 (David A. Velasco  2012-08-14 12:37:08 +0200 203)      */
1ecd2b61 (David A. Velasco  2012-08-14 12:37:08 +0200 204)     public String[] getCheckedFilePaths() {
1ecd2b61 (David A. Velasco  2012-08-14 12:37:08 +0200 205)         String [] result = null;
1ecd2b61 (David A. Velasco  2012-08-14 12:37:08 +0200 206)         SparseBooleanArray positions = mList.getCheckedItemPositions();
1ecd2b61 (David A. Velasco  2012-08-14 12:37:08 +0200 207)         if (positions.size() > 0) {
8e36e7cc (zerginator        2013-03-12 07:56:27 +0100 208)             Log_OC.d(TAG, "Returning " + positions.size() + " selected files");
1ecd2b61 (David A. Velasco  2012-08-14 12:37:08 +0200 209)             result = new String[positions.size()];
1ecd2b61 (David A. Velasco  2012-08-14 12:37:08 +0200 210)             for (int i=0; i<positions.size(); i++) {
1ecd2b61 (David A. Velasco  2012-08-14 12:37:08 +0200 211)                 result[i] = ((File) mList.getItemAtPosition(positions.keyAt(i))).getAbsolutePath();
1ecd2b61 (David A. Velasco  2012-08-14 12:37:08 +0200 212)             }
1ecd2b61 (David A. Velasco  2012-08-14 12:37:08 +0200 213)         }
1ecd2b61 (David A. Velasco  2012-08-14 12:37:08 +0200 214)         return result;
1ecd2b61 (David A. Velasco  2012-08-14 12:37:08 +0200 215)     }
1ecd2b61 (David A. Velasco  2012-08-14 12:37:08 +0200 216) 
1ecd2b61 (David A. Velasco  2012-08-14 12:37:08 +0200 217)     
1ecd2b61 (David A. Velasco  2012-08-14 12:37:08 +0200 218)     /**
1ecd2b61 (David A. Velasco  2012-08-14 12:37:08 +0200 219)      * Interface to implement by any Activity that includes some instance of LocalFileListFragment
1ecd2b61 (David A. Velasco  2012-08-14 12:37:08 +0200 220)      * 
1ecd2b61 (David A. Velasco  2012-08-14 12:37:08 +0200 221)      * @author David A. Velasco
1ecd2b61 (David A. Velasco  2012-08-14 12:37:08 +0200 222)      */
1ecd2b61 (David A. Velasco  2012-08-14 12:37:08 +0200 223)     public interface ContainerActivity {
1ecd2b61 (David A. Velasco  2012-08-14 12:37:08 +0200 224) 
1ecd2b61 (David A. Velasco  2012-08-14 12:37:08 +0200 225)         /**
1ecd2b61 (David A. Velasco  2012-08-14 12:37:08 +0200 226)          * Callback method invoked when a directory is clicked by the user on the files list
1ecd2b61 (David A. Velasco  2012-08-14 12:37:08 +0200 227)          *  
1ecd2b61 (David A. Velasco  2012-08-14 12:37:08 +0200 228)          * @param file
1ecd2b61 (David A. Velasco  2012-08-14 12:37:08 +0200 229)          */
1ecd2b61 (David A. Velasco  2012-08-14 12:37:08 +0200 230)         public void onDirectoryClick(File directory);
1ecd2b61 (David A. Velasco  2012-08-14 12:37:08 +0200 231)         
1ecd2b61 (David A. Velasco  2012-08-14 12:37:08 +0200 232)         /**
1ecd2b61 (David A. Velasco  2012-08-14 12:37:08 +0200 233)          * Callback method invoked when a file (non directory) is clicked by the user on the files list
1ecd2b61 (David A. Velasco  2012-08-14 12:37:08 +0200 234)          *  
1ecd2b61 (David A. Velasco  2012-08-14 12:37:08 +0200 235)          * @param file
1ecd2b61 (David A. Velasco  2012-08-14 12:37:08 +0200 236)          */
1ecd2b61 (David A. Velasco  2012-08-14 12:37:08 +0200 237)         public void onFileClick(File file);
a2d4e98a (David A. Velasco  2012-10-03 16:48:06 +0200 238)         
a2d4e98a (David A. Velasco  2012-10-03 16:48:06 +0200 239)         
a2d4e98a (David A. Velasco  2012-10-03 16:48:06 +0200 240)         /**
a2d4e98a (David A. Velasco  2012-10-03 16:48:06 +0200 241)          * Callback method invoked when the parent activity is fully created to get the directory to list firstly.
a2d4e98a (David A. Velasco  2012-10-03 16:48:06 +0200 242)          * 
a2d4e98a (David A. Velasco  2012-10-03 16:48:06 +0200 243)          * @return  Directory to list firstly. Can be NULL.
a2d4e98a (David A. Velasco  2012-10-03 16:48:06 +0200 244)          */
a2d4e98a (David A. Velasco  2012-10-03 16:48:06 +0200 245)         public File getInitialDirectory();
1ecd2b61 (David A. Velasco  2012-08-14 12:37:08 +0200 246) 
1ecd2b61 (David A. Velasco  2012-08-14 12:37:08 +0200 247)     }
1ecd2b61 (David A. Velasco  2012-08-14 12:37:08 +0200 248) 
1ecd2b61 (David A. Velasco  2012-08-14 12:37:08 +0200 249) 
1ecd2b61 (David A. Velasco  2012-08-14 12:37:08 +0200 250) }
