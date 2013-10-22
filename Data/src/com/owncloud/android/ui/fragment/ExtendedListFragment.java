92080afe src/com/owncloud/android/ui/FragmentListView.java              (Bartek Przybylski 2012-10-28 00:12:15 +0200   1) /* ownCloud Android client application
92080afe src/com/owncloud/android/ui/FragmentListView.java              (Bartek Przybylski 2012-10-28 00:12:15 +0200   2)  *   Copyright (C) 2012 Bartek Przybylski
bb257ec7 src/com/owncloud/android/ui/FragmentListView.java              (David A. Velasco  2013-02-07 18:45:10 +0100   3)  *   Copyright (C) 2012-2013 ownCloud Inc.
92080afe src/com/owncloud/android/ui/FragmentListView.java              (Bartek Przybylski 2012-10-28 00:12:15 +0200   4)  *
92080afe src/com/owncloud/android/ui/FragmentListView.java              (Bartek Przybylski 2012-10-28 00:12:15 +0200   5)  *   This program is free software: you can redistribute it and/or modify
bdc0332c src/com/owncloud/android/ui/FragmentListView.java              (David A. Velasco  2013-04-17 12:26:13 +0200   6)  *   it under the terms of the GNU General Public License version 2,
bdc0332c src/com/owncloud/android/ui/FragmentListView.java              (David A. Velasco  2013-04-17 12:26:13 +0200   7)  *   as published by the Free Software Foundation.
92080afe src/com/owncloud/android/ui/FragmentListView.java              (Bartek Przybylski 2012-10-28 00:12:15 +0200   8)  *
92080afe src/com/owncloud/android/ui/FragmentListView.java              (Bartek Przybylski 2012-10-28 00:12:15 +0200   9)  *   This program is distributed in the hope that it will be useful,
92080afe src/com/owncloud/android/ui/FragmentListView.java              (Bartek Przybylski 2012-10-28 00:12:15 +0200  10)  *   but WITHOUT ANY WARRANTY; without even the implied warranty of
92080afe src/com/owncloud/android/ui/FragmentListView.java              (Bartek Przybylski 2012-10-28 00:12:15 +0200  11)  *   MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
92080afe src/com/owncloud/android/ui/FragmentListView.java              (Bartek Przybylski 2012-10-28 00:12:15 +0200  12)  *   GNU General Public License for more details.
92080afe src/com/owncloud/android/ui/FragmentListView.java              (Bartek Przybylski 2012-10-28 00:12:15 +0200  13)  *
92080afe src/com/owncloud/android/ui/FragmentListView.java              (Bartek Przybylski 2012-10-28 00:12:15 +0200  14)  *   You should have received a copy of the GNU General Public License
92080afe src/com/owncloud/android/ui/FragmentListView.java              (Bartek Przybylski 2012-10-28 00:12:15 +0200  15)  *   along with this program.  If not, see <http://www.gnu.org/licenses/>.
92080afe src/com/owncloud/android/ui/FragmentListView.java              (Bartek Przybylski 2012-10-28 00:12:15 +0200  16)  *
92080afe src/com/owncloud/android/ui/FragmentListView.java              (Bartek Przybylski 2012-10-28 00:12:15 +0200  17)  */
92080afe src/com/owncloud/android/ui/FragmentListView.java              (Bartek Przybylski 2012-10-28 00:12:15 +0200  18) 
fd396289 src/com/owncloud/android/ui/fragment/ExtendedListFragment.java (David A. Velasco  2013-05-30 17:53:21 +0200  19) package com.owncloud.android.ui.fragment;
6deca0f9 src/eu/alefzero/owncloud/ui/FragmentListView.java              (Bartek Przybylski 2012-02-11 23:11:57 +0100  20) 
30c2ede8 src/eu/alefzero/owncloud/ui/FragmentListView.java              (Lennart Rosam     2012-04-10 04:40:53 +0200  21) import com.actionbarsherlock.app.SherlockFragment;
fd396289 src/com/owncloud/android/ui/fragment/ExtendedListFragment.java (David A. Velasco  2013-05-30 17:53:21 +0200  22) import com.owncloud.android.Log_OC;
d0f7de3c src/com/owncloud/android/ui/FragmentListView.java              (David A. Velasco  2012-10-17 19:22:40 +0200  23) import com.owncloud.android.R;
fd396289 src/com/owncloud/android/ui/fragment/ExtendedListFragment.java (David A. Velasco  2013-05-30 17:53:21 +0200  24) import com.owncloud.android.ui.ExtendedListView;
30c2ede8 src/eu/alefzero/owncloud/ui/FragmentListView.java              (Lennart Rosam     2012-04-10 04:40:53 +0200  25) 
6deca0f9 src/eu/alefzero/owncloud/ui/FragmentListView.java              (Bartek Przybylski 2012-02-11 23:11:57 +0100  26) import android.os.Bundle;
6deca0f9 src/eu/alefzero/owncloud/ui/FragmentListView.java              (Bartek Przybylski 2012-02-11 23:11:57 +0100  27) import android.view.LayoutInflater;
6deca0f9 src/eu/alefzero/owncloud/ui/FragmentListView.java              (Bartek Przybylski 2012-02-11 23:11:57 +0100  28) import android.view.View;
6deca0f9 src/eu/alefzero/owncloud/ui/FragmentListView.java              (Bartek Przybylski 2012-02-11 23:11:57 +0100  29) import android.view.ViewGroup;
6deca0f9 src/eu/alefzero/owncloud/ui/FragmentListView.java              (Bartek Przybylski 2012-02-11 23:11:57 +0100  30) import android.widget.AdapterView;
6deca0f9 src/eu/alefzero/owncloud/ui/FragmentListView.java              (Bartek Przybylski 2012-02-11 23:11:57 +0100  31) import android.widget.ListAdapter;
6deca0f9 src/eu/alefzero/owncloud/ui/FragmentListView.java              (Bartek Przybylski 2012-02-11 23:11:57 +0100  32) import android.widget.AdapterView.OnItemClickListener;
0bb59e0f src/com/owncloud/android/ui/FragmentListView.java              (David A. Velasco  2012-10-17 13:44:36 +0200  33) import android.widget.ListView;
6deca0f9 src/eu/alefzero/owncloud/ui/FragmentListView.java              (Bartek Przybylski 2012-02-11 23:11:57 +0100  34) 
fd396289 src/com/owncloud/android/ui/fragment/ExtendedListFragment.java (David A. Velasco  2013-05-30 17:53:21 +0200  35) /**
fd396289 src/com/owncloud/android/ui/fragment/ExtendedListFragment.java (David A. Velasco  2013-05-30 17:53:21 +0200  36)  *  TODO extending SherlockListFragment instead of SherlockFragment 
fd396289 src/com/owncloud/android/ui/fragment/ExtendedListFragment.java (David A. Velasco  2013-05-30 17:53:21 +0200  37)  */
fd396289 src/com/owncloud/android/ui/fragment/ExtendedListFragment.java (David A. Velasco  2013-05-30 17:53:21 +0200  38) public class ExtendedListFragment extends SherlockFragment implements OnItemClickListener {
fd396289 src/com/owncloud/android/ui/fragment/ExtendedListFragment.java (David A. Velasco  2013-05-30 17:53:21 +0200  39)     
fd396289 src/com/owncloud/android/ui/fragment/ExtendedListFragment.java (David A. Velasco  2013-05-30 17:53:21 +0200  40)     private static final String TAG = ExtendedListFragment.class.getSimpleName();
435b31ba src/eu/alefzero/owncloud/ui/FragmentListView.java              (Lennart Rosam     2012-05-16 09:48:34 +0200  41) 
fd396289 src/com/owncloud/android/ui/fragment/ExtendedListFragment.java (David A. Velasco  2013-05-30 17:53:21 +0200  42)     private static final String KEY_SAVED_LIST_POSITION = "SAVED_LIST_POSITION"; 
435b31ba src/eu/alefzero/owncloud/ui/FragmentListView.java              (Lennart Rosam     2012-05-16 09:48:34 +0200  43) 
fd396289 src/com/owncloud/android/ui/fragment/ExtendedListFragment.java (David A. Velasco  2013-05-30 17:53:21 +0200  44)     protected ExtendedListView mList;
fd396289 src/com/owncloud/android/ui/fragment/ExtendedListFragment.java (David A. Velasco  2013-05-30 17:53:21 +0200  45)     
435b31ba src/eu/alefzero/owncloud/ui/FragmentListView.java              (Lennart Rosam     2012-05-16 09:48:34 +0200  46)     public void setListAdapter(ListAdapter listAdapter) {
435b31ba src/eu/alefzero/owncloud/ui/FragmentListView.java              (Lennart Rosam     2012-05-16 09:48:34 +0200  47)         mList.setAdapter(listAdapter);
435b31ba src/eu/alefzero/owncloud/ui/FragmentListView.java              (Lennart Rosam     2012-05-16 09:48:34 +0200  48)         mList.invalidate();
435b31ba src/eu/alefzero/owncloud/ui/FragmentListView.java              (Lennart Rosam     2012-05-16 09:48:34 +0200  49)     }
435b31ba src/eu/alefzero/owncloud/ui/FragmentListView.java              (Lennart Rosam     2012-05-16 09:48:34 +0200  50) 
435b31ba src/eu/alefzero/owncloud/ui/FragmentListView.java              (Lennart Rosam     2012-05-16 09:48:34 +0200  51)     public ListView getListView() {
435b31ba src/eu/alefzero/owncloud/ui/FragmentListView.java              (Lennart Rosam     2012-05-16 09:48:34 +0200  52)         return mList;
435b31ba src/eu/alefzero/owncloud/ui/FragmentListView.java              (Lennart Rosam     2012-05-16 09:48:34 +0200  53)     }
0bb59e0f src/com/owncloud/android/ui/FragmentListView.java              (David A. Velasco  2012-10-17 13:44:36 +0200  54)     
fd396289 src/com/owncloud/android/ui/fragment/ExtendedListFragment.java (David A. Velasco  2013-05-30 17:53:21 +0200  55)     
435b31ba src/eu/alefzero/owncloud/ui/FragmentListView.java              (Lennart Rosam     2012-05-16 09:48:34 +0200  56)     @Override
fd396289 src/com/owncloud/android/ui/fragment/ExtendedListFragment.java (David A. Velasco  2013-05-30 17:53:21 +0200  57)     public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
fd396289 src/com/owncloud/android/ui/fragment/ExtendedListFragment.java (David A. Velasco  2013-05-30 17:53:21 +0200  58)         Log_OC.e(TAG, "onCreateView");
d0f7de3c src/com/owncloud/android/ui/FragmentListView.java              (David A. Velasco  2012-10-17 19:22:40 +0200  59)         //mList = new ExtendedListView(getActivity());
d0f7de3c src/com/owncloud/android/ui/FragmentListView.java              (David A. Velasco  2012-10-17 19:22:40 +0200  60)         View v = inflater.inflate(R.layout.list_fragment, null);
d0f7de3c src/com/owncloud/android/ui/FragmentListView.java              (David A. Velasco  2012-10-17 19:22:40 +0200  61)         mList = (ExtendedListView)(v.findViewById(R.id.list_root));
6c6d3a60 src/eu/alefzero/owncloud/ui/FragmentListView.java              (David A. Velasco  2012-06-20 13:57:56 +0200  62)         mList.setOnItemClickListener(this);
2bef10d8 src/com/owncloud/android/ui/FragmentListView.java              (David A. Velasco  2012-10-18 10:53:47 +0200  63)         //mList.setEmptyView(v.findViewById(R.id.empty_list_view));     // looks like it's not a cool idea 
d0f7de3c src/com/owncloud/android/ui/FragmentListView.java              (David A. Velasco  2012-10-17 19:22:40 +0200  64)         mList.setDivider(getResources().getDrawable(R.drawable.uploader_list_separator));
d0f7de3c src/com/owncloud/android/ui/FragmentListView.java              (David A. Velasco  2012-10-17 19:22:40 +0200  65)         mList.setDividerHeight(1);
435b31ba src/eu/alefzero/owncloud/ui/FragmentListView.java              (Lennart Rosam     2012-05-16 09:48:34 +0200  66) 
fd396289 src/com/owncloud/android/ui/fragment/ExtendedListFragment.java (David A. Velasco  2013-05-30 17:53:21 +0200  67)         if (savedInstanceState != null) {
fd396289 src/com/owncloud/android/ui/fragment/ExtendedListFragment.java (David A. Velasco  2013-05-30 17:53:21 +0200  68)             int referencePosition = savedInstanceState.getInt(KEY_SAVED_LIST_POSITION);
fd396289 src/com/owncloud/android/ui/fragment/ExtendedListFragment.java (David A. Velasco  2013-05-30 17:53:21 +0200  69)             setReferencePosition(referencePosition);
fd396289 src/com/owncloud/android/ui/fragment/ExtendedListFragment.java (David A. Velasco  2013-05-30 17:53:21 +0200  70)         }
fd396289 src/com/owncloud/android/ui/fragment/ExtendedListFragment.java (David A. Velasco  2013-05-30 17:53:21 +0200  71)         
fd396289 src/com/owncloud/android/ui/fragment/ExtendedListFragment.java (David A. Velasco  2013-05-30 17:53:21 +0200  72)         return v;
435b31ba src/eu/alefzero/owncloud/ui/FragmentListView.java              (Lennart Rosam     2012-05-16 09:48:34 +0200  73)     }
435b31ba src/eu/alefzero/owncloud/ui/FragmentListView.java              (Lennart Rosam     2012-05-16 09:48:34 +0200  74) 
fd396289 src/com/owncloud/android/ui/fragment/ExtendedListFragment.java (David A. Velasco  2013-05-30 17:53:21 +0200  75)     
435b31ba src/eu/alefzero/owncloud/ui/FragmentListView.java              (Lennart Rosam     2012-05-16 09:48:34 +0200  76)     @Override
fd396289 src/com/owncloud/android/ui/fragment/ExtendedListFragment.java (David A. Velasco  2013-05-30 17:53:21 +0200  77)     public void onSaveInstanceState(Bundle savedInstanceState) {
fd396289 src/com/owncloud/android/ui/fragment/ExtendedListFragment.java (David A. Velasco  2013-05-30 17:53:21 +0200  78)         super.onSaveInstanceState(savedInstanceState);
fd396289 src/com/owncloud/android/ui/fragment/ExtendedListFragment.java (David A. Velasco  2013-05-30 17:53:21 +0200  79)         Log_OC.e(TAG, "onSaveInstanceState()");
fd396289 src/com/owncloud/android/ui/fragment/ExtendedListFragment.java (David A. Velasco  2013-05-30 17:53:21 +0200  80)         savedInstanceState.putInt(KEY_SAVED_LIST_POSITION, getReferencePosition());
435b31ba src/eu/alefzero/owncloud/ui/FragmentListView.java              (Lennart Rosam     2012-05-16 09:48:34 +0200  81)     }
435b31ba src/eu/alefzero/owncloud/ui/FragmentListView.java              (Lennart Rosam     2012-05-16 09:48:34 +0200  82) 
d0f7de3c src/com/owncloud/android/ui/FragmentListView.java              (David A. Velasco  2012-10-17 19:22:40 +0200  83)     
d0f7de3c src/com/owncloud/android/ui/FragmentListView.java              (David A. Velasco  2012-10-17 19:22:40 +0200  84)     /**
d0f7de3c src/com/owncloud/android/ui/FragmentListView.java              (David A. Velasco  2012-10-17 19:22:40 +0200  85)      * Calculates the position of the item that will be used as a reference to reposition the visible items in the list when
d0f7de3c src/com/owncloud/android/ui/FragmentListView.java              (David A. Velasco  2012-10-17 19:22:40 +0200  86)      * the device is turned to other position. 
d0f7de3c src/com/owncloud/android/ui/FragmentListView.java              (David A. Velasco  2012-10-17 19:22:40 +0200  87)      * 
d0f7de3c src/com/owncloud/android/ui/FragmentListView.java              (David A. Velasco  2012-10-17 19:22:40 +0200  88)      * THe current policy is take as a reference the visible item in the center of the screen.  
d0f7de3c src/com/owncloud/android/ui/FragmentListView.java              (David A. Velasco  2012-10-17 19:22:40 +0200  89)      * 
d0f7de3c src/com/owncloud/android/ui/FragmentListView.java              (David A. Velasco  2012-10-17 19:22:40 +0200  90)      * @return      The position in the list of the visible item in the center of the screen.
d0f7de3c src/com/owncloud/android/ui/FragmentListView.java              (David A. Velasco  2012-10-17 19:22:40 +0200  91)      */
d0f7de3c src/com/owncloud/android/ui/FragmentListView.java              (David A. Velasco  2012-10-17 19:22:40 +0200  92)     protected int getReferencePosition() {
fd396289 src/com/owncloud/android/ui/fragment/ExtendedListFragment.java (David A. Velasco  2013-05-30 17:53:21 +0200  93)         if (mList != null) {
fd396289 src/com/owncloud/android/ui/fragment/ExtendedListFragment.java (David A. Velasco  2013-05-30 17:53:21 +0200  94)             return (mList.getFirstVisiblePosition() + mList.getLastVisiblePosition()) / 2;
fd396289 src/com/owncloud/android/ui/fragment/ExtendedListFragment.java (David A. Velasco  2013-05-30 17:53:21 +0200  95)         } else {
fd396289 src/com/owncloud/android/ui/fragment/ExtendedListFragment.java (David A. Velasco  2013-05-30 17:53:21 +0200  96)             return 0;
fd396289 src/com/owncloud/android/ui/fragment/ExtendedListFragment.java (David A. Velasco  2013-05-30 17:53:21 +0200  97)         }
d0f7de3c src/com/owncloud/android/ui/FragmentListView.java              (David A. Velasco  2012-10-17 19:22:40 +0200  98)     }
d0f7de3c src/com/owncloud/android/ui/FragmentListView.java              (David A. Velasco  2012-10-17 19:22:40 +0200  99) 
d0f7de3c src/com/owncloud/android/ui/FragmentListView.java              (David A. Velasco  2012-10-17 19:22:40 +0200 100)     
d0f7de3c src/com/owncloud/android/ui/FragmentListView.java              (David A. Velasco  2012-10-17 19:22:40 +0200 101)     /**
d0f7de3c src/com/owncloud/android/ui/FragmentListView.java              (David A. Velasco  2012-10-17 19:22:40 +0200 102)      * Sets the visible part of the list from the reference position.
d0f7de3c src/com/owncloud/android/ui/FragmentListView.java              (David A. Velasco  2012-10-17 19:22:40 +0200 103)      * 
d0f7de3c src/com/owncloud/android/ui/FragmentListView.java              (David A. Velasco  2012-10-17 19:22:40 +0200 104)      * @param   position    Reference position previously returned by {@link LocalFileListFragment#getReferencePosition()}
d0f7de3c src/com/owncloud/android/ui/FragmentListView.java              (David A. Velasco  2012-10-17 19:22:40 +0200 105)      */
d0f7de3c src/com/owncloud/android/ui/FragmentListView.java              (David A. Velasco  2012-10-17 19:22:40 +0200 106)     protected void setReferencePosition(int position) {
fd396289 src/com/owncloud/android/ui/fragment/ExtendedListFragment.java (David A. Velasco  2013-05-30 17:53:21 +0200 107)         if (mList != null) {
fd396289 src/com/owncloud/android/ui/fragment/ExtendedListFragment.java (David A. Velasco  2013-05-30 17:53:21 +0200 108)             mList.setAndCenterSelection(position);
fd396289 src/com/owncloud/android/ui/fragment/ExtendedListFragment.java (David A. Velasco  2013-05-30 17:53:21 +0200 109)         }
fd396289 src/com/owncloud/android/ui/fragment/ExtendedListFragment.java (David A. Velasco  2013-05-30 17:53:21 +0200 110)     }
fd396289 src/com/owncloud/android/ui/fragment/ExtendedListFragment.java (David A. Velasco  2013-05-30 17:53:21 +0200 111) 
fd396289 src/com/owncloud/android/ui/fragment/ExtendedListFragment.java (David A. Velasco  2013-05-30 17:53:21 +0200 112)     @Override
fd396289 src/com/owncloud/android/ui/fragment/ExtendedListFragment.java (David A. Velasco  2013-05-30 17:53:21 +0200 113)     public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
fd396289 src/com/owncloud/android/ui/fragment/ExtendedListFragment.java (David A. Velasco  2013-05-30 17:53:21 +0200 114)         // to be @overriden  
d0f7de3c src/com/owncloud/android/ui/FragmentListView.java              (David A. Velasco  2012-10-17 19:22:40 +0200 115)     }
d0f7de3c src/com/owncloud/android/ui/FragmentListView.java              (David A. Velasco  2012-10-17 19:22:40 +0200 116) 
d0f7de3c src/com/owncloud/android/ui/FragmentListView.java              (David A. Velasco  2012-10-17 19:22:40 +0200 117)     
6deca0f9 src/eu/alefzero/owncloud/ui/FragmentListView.java              (Bartek Przybylski 2012-02-11 23:11:57 +0100 118) }
