00000000 (Not Committed Yet 2013-10-18 00:25:12 -0700  1) /* ownCloud Android client application
00000000 (Not Committed Yet 2013-10-18 00:25:12 -0700  2)  *   Copyright (C) 2011  Bartek Przybylski
00000000 (Not Committed Yet 2013-10-18 00:25:12 -0700  3)  *   Copyright (C) 2012-2013 ownCloud Inc.
00000000 (Not Committed Yet 2013-10-18 00:25:12 -0700  4)  *
00000000 (Not Committed Yet 2013-10-18 00:25:12 -0700  5)  *   This program is free software: you can redistribute it and/or modify
00000000 (Not Committed Yet 2013-10-18 00:25:12 -0700  6)  *   it under the terms of the GNU General Public License version 2,
00000000 (Not Committed Yet 2013-10-18 00:25:12 -0700  7)  *   as published by the Free Software Foundation.
00000000 (Not Committed Yet 2013-10-18 00:25:12 -0700  8)  *
00000000 (Not Committed Yet 2013-10-18 00:25:12 -0700  9)  *   This program is distributed in the hope that it will be useful,
00000000 (Not Committed Yet 2013-10-18 00:25:12 -0700 10)  *   but WITHOUT ANY WARRANTY; without even the implied warranty of
00000000 (Not Committed Yet 2013-10-18 00:25:12 -0700 11)  *   MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
00000000 (Not Committed Yet 2013-10-18 00:25:12 -0700 12)  *   GNU General Public License for more details.
00000000 (Not Committed Yet 2013-10-18 00:25:12 -0700 13)  *
00000000 (Not Committed Yet 2013-10-18 00:25:12 -0700 14)  *   You should have received a copy of the GNU General Public License
00000000 (Not Committed Yet 2013-10-18 00:25:12 -0700 15)  *   along with this program.  If not, see <http://www.gnu.org/licenses/>.
00000000 (Not Committed Yet 2013-10-18 00:25:12 -0700 16)  *
00000000 (Not Committed Yet 2013-10-18 00:25:12 -0700 17)  */
00000000 (Not Committed Yet 2013-10-18 00:25:12 -0700 18) package com.owncloud.android.ui;
00000000 (Not Committed Yet 2013-10-18 00:25:12 -0700 19) 
00000000 (Not Committed Yet 2013-10-18 00:25:12 -0700 20) import android.graphics.drawable.Drawable;
00000000 (Not Committed Yet 2013-10-18 00:25:12 -0700 21) import android.view.View.OnClickListener;
00000000 (Not Committed Yet 2013-10-18 00:25:12 -0700 22) 
00000000 (Not Committed Yet 2013-10-18 00:25:12 -0700 23) /**
00000000 (Not Committed Yet 2013-10-18 00:25:12 -0700 24)  * Represents an Item on the ActionBar.
00000000 (Not Committed Yet 2013-10-18 00:25:12 -0700 25)  * 
00000000 (Not Committed Yet 2013-10-18 00:25:12 -0700 26)  * @author Bartek Przybylski
00000000 (Not Committed Yet 2013-10-18 00:25:12 -0700 27)  * 
00000000 (Not Committed Yet 2013-10-18 00:25:12 -0700 28)  */
00000000 (Not Committed Yet 2013-10-18 00:25:12 -0700 29) public class ActionItem {
00000000 (Not Committed Yet 2013-10-18 00:25:12 -0700 30)     private Drawable mIcon;
00000000 (Not Committed Yet 2013-10-18 00:25:12 -0700 31)     private String mTitle;
00000000 (Not Committed Yet 2013-10-18 00:25:12 -0700 32)     private OnClickListener mClickListener;
00000000 (Not Committed Yet 2013-10-18 00:25:12 -0700 33) 
00000000 (Not Committed Yet 2013-10-18 00:25:12 -0700 34)     public ActionItem() {
00000000 (Not Committed Yet 2013-10-18 00:25:12 -0700 35)     }
00000000 (Not Committed Yet 2013-10-18 00:25:12 -0700 36) 
00000000 (Not Committed Yet 2013-10-18 00:25:12 -0700 37)     public void setTitle(String title) {
00000000 (Not Committed Yet 2013-10-18 00:25:12 -0700 38)         mTitle = title;
00000000 (Not Committed Yet 2013-10-18 00:25:12 -0700 39)     }
00000000 (Not Committed Yet 2013-10-18 00:25:12 -0700 40) 
00000000 (Not Committed Yet 2013-10-18 00:25:12 -0700 41)     public String getTitle() {
00000000 (Not Committed Yet 2013-10-18 00:25:12 -0700 42)         return mTitle;
00000000 (Not Committed Yet 2013-10-18 00:25:12 -0700 43)     }
00000000 (Not Committed Yet 2013-10-18 00:25:12 -0700 44) 
00000000 (Not Committed Yet 2013-10-18 00:25:12 -0700 45)     public void setIcon(Drawable icon) {
00000000 (Not Committed Yet 2013-10-18 00:25:12 -0700 46)         mIcon = icon;
00000000 (Not Committed Yet 2013-10-18 00:25:12 -0700 47)     }
00000000 (Not Committed Yet 2013-10-18 00:25:12 -0700 48) 
00000000 (Not Committed Yet 2013-10-18 00:25:12 -0700 49)     public Drawable getIcon() {
00000000 (Not Committed Yet 2013-10-18 00:25:12 -0700 50)         return mIcon;
00000000 (Not Committed Yet 2013-10-18 00:25:12 -0700 51)     }
00000000 (Not Committed Yet 2013-10-18 00:25:12 -0700 52) 
00000000 (Not Committed Yet 2013-10-18 00:25:12 -0700 53)     public void setOnClickListener(OnClickListener listener) {
00000000 (Not Committed Yet 2013-10-18 00:25:12 -0700 54)         mClickListener = listener;
00000000 (Not Committed Yet 2013-10-18 00:25:12 -0700 55)     }
00000000 (Not Committed Yet 2013-10-18 00:25:12 -0700 56) 
00000000 (Not Committed Yet 2013-10-18 00:25:12 -0700 57)     public OnClickListener getOnClickListerner() {
00000000 (Not Committed Yet 2013-10-18 00:25:12 -0700 58)         return mClickListener;
00000000 (Not Committed Yet 2013-10-18 00:25:12 -0700 59)     }
00000000 (Not Committed Yet 2013-10-18 00:25:12 -0700 60) 
00000000 (Not Committed Yet 2013-10-18 00:25:12 -0700 61) }
