00000000 (Not Committed Yet 2013-10-18 00:25:12 -0700   1) /* ownCloud Android client application
00000000 (Not Committed Yet 2013-10-18 00:25:12 -0700   2)  *   Copyright (C) 2011  Bartek Przybylski
00000000 (Not Committed Yet 2013-10-18 00:25:12 -0700   3)  *   Copyright (C) 2012-2013 ownCloud Inc.
00000000 (Not Committed Yet 2013-10-18 00:25:12 -0700   4)  *
00000000 (Not Committed Yet 2013-10-18 00:25:12 -0700   5)  *   This program is free software: you can redistribute it and/or modify
00000000 (Not Committed Yet 2013-10-18 00:25:12 -0700   6)  *   it under the terms of the GNU General Public License version 2,
00000000 (Not Committed Yet 2013-10-18 00:25:12 -0700   7)  *   as published by the Free Software Foundation.
00000000 (Not Committed Yet 2013-10-18 00:25:12 -0700   8)  *
00000000 (Not Committed Yet 2013-10-18 00:25:12 -0700   9)  *   This program is distributed in the hope that it will be useful,
00000000 (Not Committed Yet 2013-10-18 00:25:12 -0700  10)  *   but WITHOUT ANY WARRANTY; without even the implied warranty of
00000000 (Not Committed Yet 2013-10-18 00:25:12 -0700  11)  *   MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
00000000 (Not Committed Yet 2013-10-18 00:25:12 -0700  12)  *   GNU General Public License for more details.
00000000 (Not Committed Yet 2013-10-18 00:25:12 -0700  13)  *
00000000 (Not Committed Yet 2013-10-18 00:25:12 -0700  14)  *   You should have received a copy of the GNU General Public License
00000000 (Not Committed Yet 2013-10-18 00:25:12 -0700  15)  *   along with this program.  If not, see <http://www.gnu.org/licenses/>.
00000000 (Not Committed Yet 2013-10-18 00:25:12 -0700  16)  *
00000000 (Not Committed Yet 2013-10-18 00:25:12 -0700  17)  */
00000000 (Not Committed Yet 2013-10-18 00:25:12 -0700  18) package com.owncloud.android.ui;
00000000 (Not Committed Yet 2013-10-18 00:25:12 -0700  19) 
00000000 (Not Committed Yet 2013-10-18 00:25:12 -0700  20) import android.content.Context;
00000000 (Not Committed Yet 2013-10-18 00:25:12 -0700  21) import android.graphics.Rect;
00000000 (Not Committed Yet 2013-10-18 00:25:12 -0700  22) import android.graphics.drawable.BitmapDrawable;
00000000 (Not Committed Yet 2013-10-18 00:25:12 -0700  23) import android.graphics.drawable.Drawable;
00000000 (Not Committed Yet 2013-10-18 00:25:12 -0700  24) import android.view.Gravity;
00000000 (Not Committed Yet 2013-10-18 00:25:12 -0700  25) import android.view.LayoutInflater;
00000000 (Not Committed Yet 2013-10-18 00:25:12 -0700  26) import android.view.MotionEvent;
00000000 (Not Committed Yet 2013-10-18 00:25:12 -0700  27) import android.view.View;
00000000 (Not Committed Yet 2013-10-18 00:25:12 -0700  28) import android.view.WindowManager;
00000000 (Not Committed Yet 2013-10-18 00:25:12 -0700  29) import android.view.View.OnTouchListener;
00000000 (Not Committed Yet 2013-10-18 00:25:12 -0700  30) import android.view.ViewGroup.LayoutParams;
00000000 (Not Committed Yet 2013-10-18 00:25:12 -0700  31) import android.widget.PopupWindow;
00000000 (Not Committed Yet 2013-10-18 00:25:12 -0700  32) 
00000000 (Not Committed Yet 2013-10-18 00:25:12 -0700  33) /**
00000000 (Not Committed Yet 2013-10-18 00:25:12 -0700  34)  * Represents a custom PopupWindows
00000000 (Not Committed Yet 2013-10-18 00:25:12 -0700  35)  * 
00000000 (Not Committed Yet 2013-10-18 00:25:12 -0700  36)  * @author Lorensius. W. T
00000000 (Not Committed Yet 2013-10-18 00:25:12 -0700  37)  * 
00000000 (Not Committed Yet 2013-10-18 00:25:12 -0700  38)  */
00000000 (Not Committed Yet 2013-10-18 00:25:12 -0700  39) public class CustomPopup {
00000000 (Not Committed Yet 2013-10-18 00:25:12 -0700  40)     protected final View mAnchor;
00000000 (Not Committed Yet 2013-10-18 00:25:12 -0700  41)     protected final PopupWindow mWindow;
00000000 (Not Committed Yet 2013-10-18 00:25:12 -0700  42)     private View root;
00000000 (Not Committed Yet 2013-10-18 00:25:12 -0700  43)     private Drawable background = null;
00000000 (Not Committed Yet 2013-10-18 00:25:12 -0700  44)     protected final WindowManager mWManager;
00000000 (Not Committed Yet 2013-10-18 00:25:12 -0700  45) 
00000000 (Not Committed Yet 2013-10-18 00:25:12 -0700  46)     public CustomPopup(View anchor) {
00000000 (Not Committed Yet 2013-10-18 00:25:12 -0700  47)         mAnchor = anchor;
00000000 (Not Committed Yet 2013-10-18 00:25:12 -0700  48)         mWindow = new PopupWindow(anchor.getContext());
00000000 (Not Committed Yet 2013-10-18 00:25:12 -0700  49) 
00000000 (Not Committed Yet 2013-10-18 00:25:12 -0700  50)         mWindow.setTouchInterceptor(new OnTouchListener() {
00000000 (Not Committed Yet 2013-10-18 00:25:12 -0700  51) 
00000000 (Not Committed Yet 2013-10-18 00:25:12 -0700  52)             public boolean onTouch(View v, MotionEvent event) {
00000000 (Not Committed Yet 2013-10-18 00:25:12 -0700  53)                 if (event.getAction() == MotionEvent.ACTION_OUTSIDE) {
00000000 (Not Committed Yet 2013-10-18 00:25:12 -0700  54)                     CustomPopup.this.dismiss();
00000000 (Not Committed Yet 2013-10-18 00:25:12 -0700  55)                     return true;
00000000 (Not Committed Yet 2013-10-18 00:25:12 -0700  56)                 }
00000000 (Not Committed Yet 2013-10-18 00:25:12 -0700  57)                 return false;
00000000 (Not Committed Yet 2013-10-18 00:25:12 -0700  58)             }
00000000 (Not Committed Yet 2013-10-18 00:25:12 -0700  59)         });
00000000 (Not Committed Yet 2013-10-18 00:25:12 -0700  60) 
00000000 (Not Committed Yet 2013-10-18 00:25:12 -0700  61)         mWManager = (WindowManager) anchor.getContext().getSystemService(
00000000 (Not Committed Yet 2013-10-18 00:25:12 -0700  62)                 Context.WINDOW_SERVICE);
00000000 (Not Committed Yet 2013-10-18 00:25:12 -0700  63)         onCreate();
00000000 (Not Committed Yet 2013-10-18 00:25:12 -0700  64)     }
00000000 (Not Committed Yet 2013-10-18 00:25:12 -0700  65) 
00000000 (Not Committed Yet 2013-10-18 00:25:12 -0700  66)     public void onCreate() {
00000000 (Not Committed Yet 2013-10-18 00:25:12 -0700  67)     }
00000000 (Not Committed Yet 2013-10-18 00:25:12 -0700  68) 
00000000 (Not Committed Yet 2013-10-18 00:25:12 -0700  69)     public void onShow() {
00000000 (Not Committed Yet 2013-10-18 00:25:12 -0700  70)     }
00000000 (Not Committed Yet 2013-10-18 00:25:12 -0700  71) 
00000000 (Not Committed Yet 2013-10-18 00:25:12 -0700  72)     public void preShow() {
00000000 (Not Committed Yet 2013-10-18 00:25:12 -0700  73)         if (root == null) {
00000000 (Not Committed Yet 2013-10-18 00:25:12 -0700  74)             throw new IllegalStateException(
00000000 (Not Committed Yet 2013-10-18 00:25:12 -0700  75)                     "setContentView called with a view to display");
00000000 (Not Committed Yet 2013-10-18 00:25:12 -0700  76)         }
00000000 (Not Committed Yet 2013-10-18 00:25:12 -0700  77) 
00000000 (Not Committed Yet 2013-10-18 00:25:12 -0700  78)         onShow();
00000000 (Not Committed Yet 2013-10-18 00:25:12 -0700  79) 
00000000 (Not Committed Yet 2013-10-18 00:25:12 -0700  80)         if (background == null) {
00000000 (Not Committed Yet 2013-10-18 00:25:12 -0700  81)             mWindow.setBackgroundDrawable(new BitmapDrawable());
00000000 (Not Committed Yet 2013-10-18 00:25:12 -0700  82)         } else {
00000000 (Not Committed Yet 2013-10-18 00:25:12 -0700  83)             mWindow.setBackgroundDrawable(background);
00000000 (Not Committed Yet 2013-10-18 00:25:12 -0700  84)         }
00000000 (Not Committed Yet 2013-10-18 00:25:12 -0700  85) 
00000000 (Not Committed Yet 2013-10-18 00:25:12 -0700  86)         mWindow.setWidth(WindowManager.LayoutParams.WRAP_CONTENT);
00000000 (Not Committed Yet 2013-10-18 00:25:12 -0700  87)         mWindow.setHeight(WindowManager.LayoutParams.WRAP_CONTENT);
00000000 (Not Committed Yet 2013-10-18 00:25:12 -0700  88)         mWindow.setTouchable(true);
00000000 (Not Committed Yet 2013-10-18 00:25:12 -0700  89)         mWindow.setFocusable(true);
00000000 (Not Committed Yet 2013-10-18 00:25:12 -0700  90)         mWindow.setOutsideTouchable(true);
00000000 (Not Committed Yet 2013-10-18 00:25:12 -0700  91) 
00000000 (Not Committed Yet 2013-10-18 00:25:12 -0700  92)         mWindow.setContentView(root);
00000000 (Not Committed Yet 2013-10-18 00:25:12 -0700  93)     }
00000000 (Not Committed Yet 2013-10-18 00:25:12 -0700  94) 
00000000 (Not Committed Yet 2013-10-18 00:25:12 -0700  95)     public void setBackgroundDrawable(Drawable background) {
00000000 (Not Committed Yet 2013-10-18 00:25:12 -0700  96)         this.background = background;
00000000 (Not Committed Yet 2013-10-18 00:25:12 -0700  97)     }
00000000 (Not Committed Yet 2013-10-18 00:25:12 -0700  98) 
00000000 (Not Committed Yet 2013-10-18 00:25:12 -0700  99)     public void setContentView(View root) {
00000000 (Not Committed Yet 2013-10-18 00:25:12 -0700 100)         this.root = root;
00000000 (Not Committed Yet 2013-10-18 00:25:12 -0700 101)         mWindow.setContentView(root);
00000000 (Not Committed Yet 2013-10-18 00:25:12 -0700 102)     }
00000000 (Not Committed Yet 2013-10-18 00:25:12 -0700 103) 
00000000 (Not Committed Yet 2013-10-18 00:25:12 -0700 104)     public void setContentView(int layoutResId) {
00000000 (Not Committed Yet 2013-10-18 00:25:12 -0700 105)         LayoutInflater inflater = (LayoutInflater) mAnchor.getContext()
00000000 (Not Committed Yet 2013-10-18 00:25:12 -0700 106)                 .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
00000000 (Not Committed Yet 2013-10-18 00:25:12 -0700 107)         setContentView(inflater.inflate(layoutResId, null));
00000000 (Not Committed Yet 2013-10-18 00:25:12 -0700 108)     }
00000000 (Not Committed Yet 2013-10-18 00:25:12 -0700 109) 
00000000 (Not Committed Yet 2013-10-18 00:25:12 -0700 110)     public void showDropDown() {
00000000 (Not Committed Yet 2013-10-18 00:25:12 -0700 111)         showDropDown(0, 0);
00000000 (Not Committed Yet 2013-10-18 00:25:12 -0700 112)     }
00000000 (Not Committed Yet 2013-10-18 00:25:12 -0700 113) 
00000000 (Not Committed Yet 2013-10-18 00:25:12 -0700 114)     public void showDropDown(int x, int y) {
00000000 (Not Committed Yet 2013-10-18 00:25:12 -0700 115)         preShow();
00000000 (Not Committed Yet 2013-10-18 00:25:12 -0700 116)         mWindow.setAnimationStyle(android.R.style.Animation_Dialog);
00000000 (Not Committed Yet 2013-10-18 00:25:12 -0700 117)         mWindow.showAsDropDown(mAnchor, x, y);
00000000 (Not Committed Yet 2013-10-18 00:25:12 -0700 118)     }
00000000 (Not Committed Yet 2013-10-18 00:25:12 -0700 119) 
00000000 (Not Committed Yet 2013-10-18 00:25:12 -0700 120)     public void showLikeQuickAction() {
00000000 (Not Committed Yet 2013-10-18 00:25:12 -0700 121)         showLikeQuickAction(0, 0);
00000000 (Not Committed Yet 2013-10-18 00:25:12 -0700 122)     }
00000000 (Not Committed Yet 2013-10-18 00:25:12 -0700 123) 
00000000 (Not Committed Yet 2013-10-18 00:25:12 -0700 124)     public void showLikeQuickAction(int x, int y) {
00000000 (Not Committed Yet 2013-10-18 00:25:12 -0700 125)         preShow();
00000000 (Not Committed Yet 2013-10-18 00:25:12 -0700 126) 
00000000 (Not Committed Yet 2013-10-18 00:25:12 -0700 127)         mWindow.setAnimationStyle(android.R.style.Animation_Dialog);
00000000 (Not Committed Yet 2013-10-18 00:25:12 -0700 128)         int[] location = new int[2];
00000000 (Not Committed Yet 2013-10-18 00:25:12 -0700 129)         mAnchor.getLocationOnScreen(location);
00000000 (Not Committed Yet 2013-10-18 00:25:12 -0700 130) 
00000000 (Not Committed Yet 2013-10-18 00:25:12 -0700 131)         Rect anchorRect = new Rect(location[0], location[1], location[0]
00000000 (Not Committed Yet 2013-10-18 00:25:12 -0700 132)                 + mAnchor.getWidth(), location[1] + mAnchor.getHeight());
00000000 (Not Committed Yet 2013-10-18 00:25:12 -0700 133) 
00000000 (Not Committed Yet 2013-10-18 00:25:12 -0700 134)         root.setLayoutParams(new LayoutParams(LayoutParams.WRAP_CONTENT,
00000000 (Not Committed Yet 2013-10-18 00:25:12 -0700 135)                 LayoutParams.WRAP_CONTENT));
00000000 (Not Committed Yet 2013-10-18 00:25:12 -0700 136)         root.measure(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
00000000 (Not Committed Yet 2013-10-18 00:25:12 -0700 137) 
00000000 (Not Committed Yet 2013-10-18 00:25:12 -0700 138)         int rootW = root.getWidth(), rootH = root.getHeight();
00000000 (Not Committed Yet 2013-10-18 00:25:12 -0700 139)         int screenW = mWManager.getDefaultDisplay().getWidth();
00000000 (Not Committed Yet 2013-10-18 00:25:12 -0700 140) 
00000000 (Not Committed Yet 2013-10-18 00:25:12 -0700 141)         int xpos = ((screenW - rootW) / 2) + x;
00000000 (Not Committed Yet 2013-10-18 00:25:12 -0700 142)         int ypos = anchorRect.top - rootH + y;
00000000 (Not Committed Yet 2013-10-18 00:25:12 -0700 143) 
00000000 (Not Committed Yet 2013-10-18 00:25:12 -0700 144)         if (rootH > anchorRect.top) {
00000000 (Not Committed Yet 2013-10-18 00:25:12 -0700 145)             ypos = anchorRect.bottom + y;
00000000 (Not Committed Yet 2013-10-18 00:25:12 -0700 146)         }
00000000 (Not Committed Yet 2013-10-18 00:25:12 -0700 147)         mWindow.showAtLocation(mAnchor, Gravity.NO_GRAVITY, xpos, ypos);
00000000 (Not Committed Yet 2013-10-18 00:25:12 -0700 148)     }
00000000 (Not Committed Yet 2013-10-18 00:25:12 -0700 149) 
00000000 (Not Committed Yet 2013-10-18 00:25:12 -0700 150)     public void dismiss() {
00000000 (Not Committed Yet 2013-10-18 00:25:12 -0700 151)         mWindow.dismiss();
00000000 (Not Committed Yet 2013-10-18 00:25:12 -0700 152)     }
00000000 (Not Committed Yet 2013-10-18 00:25:12 -0700 153) 
00000000 (Not Committed Yet 2013-10-18 00:25:12 -0700 154) }
