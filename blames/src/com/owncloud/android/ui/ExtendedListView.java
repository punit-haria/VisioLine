92080afe (Bartek Przybylski 2012-10-28 00:12:15 +0200  1) /* ownCloud Android client application
92080afe (Bartek Przybylski 2012-10-28 00:12:15 +0200  2)  *   Copyright (C) 2012 Bartek Przybylski
bb257ec7 (David A. Velasco  2013-02-07 18:45:10 +0100  3)  *   Copyright (C) 2012-2013 ownCloud Inc.
92080afe (Bartek Przybylski 2012-10-28 00:12:15 +0200  4)  *
92080afe (Bartek Przybylski 2012-10-28 00:12:15 +0200  5)  *   This program is free software: you can redistribute it and/or modify
bdc0332c (David A. Velasco  2013-04-17 12:26:13 +0200  6)  *   it under the terms of the GNU General Public License version 2,
bdc0332c (David A. Velasco  2013-04-17 12:26:13 +0200  7)  *   as published by the Free Software Foundation.
92080afe (Bartek Przybylski 2012-10-28 00:12:15 +0200  8)  *
92080afe (Bartek Przybylski 2012-10-28 00:12:15 +0200  9)  *   This program is distributed in the hope that it will be useful,
92080afe (Bartek Przybylski 2012-10-28 00:12:15 +0200 10)  *   but WITHOUT ANY WARRANTY; without even the implied warranty of
92080afe (Bartek Przybylski 2012-10-28 00:12:15 +0200 11)  *   MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
92080afe (Bartek Przybylski 2012-10-28 00:12:15 +0200 12)  *   GNU General Public License for more details.
92080afe (Bartek Przybylski 2012-10-28 00:12:15 +0200 13)  *
92080afe (Bartek Przybylski 2012-10-28 00:12:15 +0200 14)  *   You should have received a copy of the GNU General Public License
92080afe (Bartek Przybylski 2012-10-28 00:12:15 +0200 15)  *   along with this program.  If not, see <http://www.gnu.org/licenses/>.
92080afe (Bartek Przybylski 2012-10-28 00:12:15 +0200 16)  *
92080afe (Bartek Przybylski 2012-10-28 00:12:15 +0200 17)  */
92080afe (Bartek Przybylski 2012-10-28 00:12:15 +0200 18) 
0bb59e0f (David A. Velasco  2012-10-17 13:44:36 +0200 19) package com.owncloud.android.ui;
0bb59e0f (David A. Velasco  2012-10-17 13:44:36 +0200 20) 
0bb59e0f (David A. Velasco  2012-10-17 13:44:36 +0200 21) import android.content.Context;
0bb59e0f (David A. Velasco  2012-10-17 13:44:36 +0200 22) import android.graphics.Canvas;
0bb59e0f (David A. Velasco  2012-10-17 13:44:36 +0200 23) import android.util.AttributeSet;
0bb59e0f (David A. Velasco  2012-10-17 13:44:36 +0200 24) import android.widget.ListView;
0bb59e0f (David A. Velasco  2012-10-17 13:44:36 +0200 25) 
0bb59e0f (David A. Velasco  2012-10-17 13:44:36 +0200 26) /**
0bb59e0f (David A. Velasco  2012-10-17 13:44:36 +0200 27)  * ListView allowing to specify the position of an item that should be centered in the visible area, if possible.
0bb59e0f (David A. Velasco  2012-10-17 13:44:36 +0200 28)  * 
0bb59e0f (David A. Velasco  2012-10-17 13:44:36 +0200 29)  * The cleanest way I found to overcome the problem due to getHeight() returns 0 until the view is really drawn. 
0bb59e0f (David A. Velasco  2012-10-17 13:44:36 +0200 30)  *  
0bb59e0f (David A. Velasco  2012-10-17 13:44:36 +0200 31)  * @author David A. Velasco
0bb59e0f (David A. Velasco  2012-10-17 13:44:36 +0200 32)  */
0bb59e0f (David A. Velasco  2012-10-17 13:44:36 +0200 33) public class ExtendedListView extends ListView {
0bb59e0f (David A. Velasco  2012-10-17 13:44:36 +0200 34) 
0bb59e0f (David A. Velasco  2012-10-17 13:44:36 +0200 35)     private int mPositionToSetAndCenter;
0bb59e0f (David A. Velasco  2012-10-17 13:44:36 +0200 36) 
0bb59e0f (David A. Velasco  2012-10-17 13:44:36 +0200 37)     public ExtendedListView(Context context) {
0bb59e0f (David A. Velasco  2012-10-17 13:44:36 +0200 38)         super(context);
0bb59e0f (David A. Velasco  2012-10-17 13:44:36 +0200 39)     }
0bb59e0f (David A. Velasco  2012-10-17 13:44:36 +0200 40) 
0bb59e0f (David A. Velasco  2012-10-17 13:44:36 +0200 41)     public ExtendedListView(Context context, AttributeSet attrs) {
0bb59e0f (David A. Velasco  2012-10-17 13:44:36 +0200 42)         super(context, attrs);
0bb59e0f (David A. Velasco  2012-10-17 13:44:36 +0200 43)     }
0bb59e0f (David A. Velasco  2012-10-17 13:44:36 +0200 44) 
0bb59e0f (David A. Velasco  2012-10-17 13:44:36 +0200 45)     public ExtendedListView(Context context, AttributeSet attrs, int defStyle) {
0bb59e0f (David A. Velasco  2012-10-17 13:44:36 +0200 46)         super(context, attrs, defStyle);
0bb59e0f (David A. Velasco  2012-10-17 13:44:36 +0200 47)     }
0bb59e0f (David A. Velasco  2012-10-17 13:44:36 +0200 48) 
0bb59e0f (David A. Velasco  2012-10-17 13:44:36 +0200 49)     /**
0bb59e0f (David A. Velasco  2012-10-17 13:44:36 +0200 50)      * {@inheritDoc}
0bb59e0f (David A. Velasco  2012-10-17 13:44:36 +0200 51)      * 
0bb59e0f (David A. Velasco  2012-10-17 13:44:36 +0200 52)      * 
0bb59e0f (David A. Velasco  2012-10-17 13:44:36 +0200 53)      */
0bb59e0f (David A. Velasco  2012-10-17 13:44:36 +0200 54)     @Override
0bb59e0f (David A. Velasco  2012-10-17 13:44:36 +0200 55)     protected void onDraw (Canvas canvas) {
0bb59e0f (David A. Velasco  2012-10-17 13:44:36 +0200 56)         super.onDraw(canvas);
0bb59e0f (David A. Velasco  2012-10-17 13:44:36 +0200 57)         if (mPositionToSetAndCenter > 0) {
0bb59e0f (David A. Velasco  2012-10-17 13:44:36 +0200 58)             this.setSelectionFromTop(mPositionToSetAndCenter, getHeight() / 2);
0bb59e0f (David A. Velasco  2012-10-17 13:44:36 +0200 59)             mPositionToSetAndCenter = 0;
0bb59e0f (David A. Velasco  2012-10-17 13:44:36 +0200 60)         }
0bb59e0f (David A. Velasco  2012-10-17 13:44:36 +0200 61)     }
0bb59e0f (David A. Velasco  2012-10-17 13:44:36 +0200 62)     
0bb59e0f (David A. Velasco  2012-10-17 13:44:36 +0200 63)     /**
0bb59e0f (David A. Velasco  2012-10-17 13:44:36 +0200 64)      * Public method to set the position of the item that should be centered in the visible area of the view.
0bb59e0f (David A. Velasco  2012-10-17 13:44:36 +0200 65)      * 
0bb59e0f (David A. Velasco  2012-10-17 13:44:36 +0200 66)      * The position is saved here and checked in onDraw().
0bb59e0f (David A. Velasco  2012-10-17 13:44:36 +0200 67)      *  
0bb59e0f (David A. Velasco  2012-10-17 13:44:36 +0200 68)      * @param position         Position (in the list of items) of the item to center in the visible area.     
0bb59e0f (David A. Velasco  2012-10-17 13:44:36 +0200 69)      */
0bb59e0f (David A. Velasco  2012-10-17 13:44:36 +0200 70)     public void setAndCenterSelection(int position) {
0bb59e0f (David A. Velasco  2012-10-17 13:44:36 +0200 71)         mPositionToSetAndCenter = position;
0bb59e0f (David A. Velasco  2012-10-17 13:44:36 +0200 72)     }
0bb59e0f (David A. Velasco  2012-10-17 13:44:36 +0200 73) }
