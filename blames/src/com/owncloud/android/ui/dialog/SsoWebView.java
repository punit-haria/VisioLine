37297fb7 (David A. Velasco 2013-08-21 18:51:13 +0200  1) /* ownCloud Android client application
37297fb7 (David A. Velasco 2013-08-21 18:51:13 +0200  2)  *   Copyright (C) 2012-2013 ownCloud Inc.
37297fb7 (David A. Velasco 2013-08-21 18:51:13 +0200  3)  *
37297fb7 (David A. Velasco 2013-08-21 18:51:13 +0200  4)  *   This program is free software: you can redistribute it and/or modify
37297fb7 (David A. Velasco 2013-08-21 18:51:13 +0200  5)  *   it under the terms of the GNU General Public License version 2,
37297fb7 (David A. Velasco 2013-08-21 18:51:13 +0200  6)  *   as published by the Free Software Foundation.
37297fb7 (David A. Velasco 2013-08-21 18:51:13 +0200  7)  *
37297fb7 (David A. Velasco 2013-08-21 18:51:13 +0200  8)  *   This program is distributed in the hope that it will be useful,
37297fb7 (David A. Velasco 2013-08-21 18:51:13 +0200  9)  *   but WITHOUT ANY WARRANTY; without even the implied warranty of
37297fb7 (David A. Velasco 2013-08-21 18:51:13 +0200 10)  *   MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
37297fb7 (David A. Velasco 2013-08-21 18:51:13 +0200 11)  *   GNU General Public License for more details.
37297fb7 (David A. Velasco 2013-08-21 18:51:13 +0200 12)  *
37297fb7 (David A. Velasco 2013-08-21 18:51:13 +0200 13)  *   You should have received a copy of the GNU General Public License
37297fb7 (David A. Velasco 2013-08-21 18:51:13 +0200 14)  *   along with this program.  If not, see <http://www.gnu.org/licenses/>.
37297fb7 (David A. Velasco 2013-08-21 18:51:13 +0200 15)  *
37297fb7 (David A. Velasco 2013-08-21 18:51:13 +0200 16)  */
37297fb7 (David A. Velasco 2013-08-21 18:51:13 +0200 17) 
37297fb7 (David A. Velasco 2013-08-21 18:51:13 +0200 18) package com.owncloud.android.ui.dialog;
37297fb7 (David A. Velasco 2013-08-21 18:51:13 +0200 19) 
37297fb7 (David A. Velasco 2013-08-21 18:51:13 +0200 20) import android.content.Context;
37297fb7 (David A. Velasco 2013-08-21 18:51:13 +0200 21) import android.util.AttributeSet;
37297fb7 (David A. Velasco 2013-08-21 18:51:13 +0200 22) import android.webkit.WebView;
37297fb7 (David A. Velasco 2013-08-21 18:51:13 +0200 23) 
37297fb7 (David A. Velasco 2013-08-21 18:51:13 +0200 24) public class SsoWebView extends WebView {
37297fb7 (David A. Velasco 2013-08-21 18:51:13 +0200 25)     
37297fb7 (David A. Velasco 2013-08-21 18:51:13 +0200 26)     public SsoWebView(Context context) {
37297fb7 (David A. Velasco 2013-08-21 18:51:13 +0200 27)         super(context);
37297fb7 (David A. Velasco 2013-08-21 18:51:13 +0200 28)     }
37297fb7 (David A. Velasco 2013-08-21 18:51:13 +0200 29)     
37297fb7 (David A. Velasco 2013-08-21 18:51:13 +0200 30)     public SsoWebView(Context context, AttributeSet attr) {
37297fb7 (David A. Velasco 2013-08-21 18:51:13 +0200 31)         super(context, attr);
37297fb7 (David A. Velasco 2013-08-21 18:51:13 +0200 32)     }
37297fb7 (David A. Velasco 2013-08-21 18:51:13 +0200 33)     
37297fb7 (David A. Velasco 2013-08-21 18:51:13 +0200 34)     @Override
37297fb7 (David A. Velasco 2013-08-21 18:51:13 +0200 35)     public boolean onCheckIsTextEditor () {
37297fb7 (David A. Velasco 2013-08-21 18:51:13 +0200 36)         return false;
37297fb7 (David A. Velasco 2013-08-21 18:51:13 +0200 37)     }
37297fb7 (David A. Velasco 2013-08-21 18:51:13 +0200 38)     
37297fb7 (David A. Velasco 2013-08-21 18:51:13 +0200 39) }
37297fb7 (David A. Velasco 2013-08-21 18:51:13 +0200 40) 
