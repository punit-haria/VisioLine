00000000 (Not Committed Yet 2013-10-18 00:25:13 -0700  1) /* ownCloud Android client application
00000000 (Not Committed Yet 2013-10-18 00:25:13 -0700  2)  *   Copyright (C) 2011  Bartek Przybylski
00000000 (Not Committed Yet 2013-10-18 00:25:13 -0700  3)  *   Copyright (C) 2012-2013 ownCloud Inc.
00000000 (Not Committed Yet 2013-10-18 00:25:13 -0700  4)  *
00000000 (Not Committed Yet 2013-10-18 00:25:13 -0700  5)  *   This program is free software: you can redistribute it and/or modify
00000000 (Not Committed Yet 2013-10-18 00:25:13 -0700  6)  *   it under the terms of the GNU General Public License version 2,
00000000 (Not Committed Yet 2013-10-18 00:25:13 -0700  7)  *   as published by the Free Software Foundation.
00000000 (Not Committed Yet 2013-10-18 00:25:13 -0700  8)  *
00000000 (Not Committed Yet 2013-10-18 00:25:13 -0700  9)  *   This program is distributed in the hope that it will be useful,
00000000 (Not Committed Yet 2013-10-18 00:25:13 -0700 10)  *   but WITHOUT ANY WARRANTY; without even the implied warranty of
00000000 (Not Committed Yet 2013-10-18 00:25:13 -0700 11)  *   MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
00000000 (Not Committed Yet 2013-10-18 00:25:13 -0700 12)  *   GNU General Public License for more details.
00000000 (Not Committed Yet 2013-10-18 00:25:13 -0700 13)  *
00000000 (Not Committed Yet 2013-10-18 00:25:13 -0700 14)  *   You should have received a copy of the GNU General Public License
00000000 (Not Committed Yet 2013-10-18 00:25:13 -0700 15)  *   along with this program.  If not, see <http://www.gnu.org/licenses/>.
00000000 (Not Committed Yet 2013-10-18 00:25:13 -0700 16)  *
00000000 (Not Committed Yet 2013-10-18 00:25:13 -0700 17)  */
00000000 (Not Committed Yet 2013-10-18 00:25:13 -0700 18) package com.owncloud.android.ui.fragment;
00000000 (Not Committed Yet 2013-10-18 00:25:13 -0700 19) 
00000000 (Not Committed Yet 2013-10-18 00:25:13 -0700 20) import com.actionbarsherlock.app.SherlockFragment;
00000000 (Not Committed Yet 2013-10-18 00:25:13 -0700 21) import com.owncloud.android.ui.activity.LandingActivity;
00000000 (Not Committed Yet 2013-10-18 00:25:13 -0700 22) import com.owncloud.android.ui.adapter.LandingScreenAdapter;
00000000 (Not Committed Yet 2013-10-18 00:25:13 -0700 23) 
00000000 (Not Committed Yet 2013-10-18 00:25:13 -0700 24) import android.os.Bundle;
00000000 (Not Committed Yet 2013-10-18 00:25:13 -0700 25) import android.view.LayoutInflater;
00000000 (Not Committed Yet 2013-10-18 00:25:13 -0700 26) import android.view.View;
00000000 (Not Committed Yet 2013-10-18 00:25:13 -0700 27) import android.view.ViewGroup;
00000000 (Not Committed Yet 2013-10-18 00:25:13 -0700 28) import android.widget.ListView;
00000000 (Not Committed Yet 2013-10-18 00:25:13 -0700 29) import com.owncloud.android.R;
00000000 (Not Committed Yet 2013-10-18 00:25:13 -0700 30) 
00000000 (Not Committed Yet 2013-10-18 00:25:13 -0700 31) /**
00000000 (Not Committed Yet 2013-10-18 00:25:13 -0700 32)  * Used on the Landing page to display what Components of the ownCloud there
00000000 (Not Committed Yet 2013-10-18 00:25:13 -0700 33)  * are. Like Files, Music, Contacts, etc.
00000000 (Not Committed Yet 2013-10-18 00:25:13 -0700 34)  * 
00000000 (Not Committed Yet 2013-10-18 00:25:13 -0700 35)  * @author Lennart Rosam
00000000 (Not Committed Yet 2013-10-18 00:25:13 -0700 36)  * 
00000000 (Not Committed Yet 2013-10-18 00:25:13 -0700 37)  */
00000000 (Not Committed Yet 2013-10-18 00:25:13 -0700 38) public class LandingPageFragment extends SherlockFragment {
00000000 (Not Committed Yet 2013-10-18 00:25:13 -0700 39) 
00000000 (Not Committed Yet 2013-10-18 00:25:13 -0700 40)     @Override
00000000 (Not Committed Yet 2013-10-18 00:25:13 -0700 41)     public View onCreateView(LayoutInflater inflater, ViewGroup container,
00000000 (Not Committed Yet 2013-10-18 00:25:13 -0700 42)             Bundle savedInstanceState) {
00000000 (Not Committed Yet 2013-10-18 00:25:13 -0700 43)         View root = inflater.inflate(R.layout.landing_page_fragment, container);
00000000 (Not Committed Yet 2013-10-18 00:25:13 -0700 44)         return root;
00000000 (Not Committed Yet 2013-10-18 00:25:13 -0700 45)     }
00000000 (Not Committed Yet 2013-10-18 00:25:13 -0700 46) 
00000000 (Not Committed Yet 2013-10-18 00:25:13 -0700 47)     @Override
00000000 (Not Committed Yet 2013-10-18 00:25:13 -0700 48)     public void onActivityCreated(Bundle savedInstanceState) {
00000000 (Not Committed Yet 2013-10-18 00:25:13 -0700 49)         super.onActivityCreated(savedInstanceState);
00000000 (Not Committed Yet 2013-10-18 00:25:13 -0700 50) 
00000000 (Not Committed Yet 2013-10-18 00:25:13 -0700 51)         ListView landingScreenItems = (ListView) getView().findViewById(
00000000 (Not Committed Yet 2013-10-18 00:25:13 -0700 52)                 R.id.homeScreenList);
00000000 (Not Committed Yet 2013-10-18 00:25:13 -0700 53)         landingScreenItems.setAdapter(new LandingScreenAdapter(getActivity()));
00000000 (Not Committed Yet 2013-10-18 00:25:13 -0700 54)         landingScreenItems
00000000 (Not Committed Yet 2013-10-18 00:25:13 -0700 55)                 .setOnItemClickListener((LandingActivity) getActivity());
00000000 (Not Committed Yet 2013-10-18 00:25:13 -0700 56)     }
00000000 (Not Committed Yet 2013-10-18 00:25:13 -0700 57) 
00000000 (Not Committed Yet 2013-10-18 00:25:13 -0700 58) }
