00000000 src/com/owncloud/android/ui/QuickAction.java (Not Committed Yet 2013-10-18 00:25:12 -0700   1) /* ownCloud Android client application
00000000 src/com/owncloud/android/ui/QuickAction.java (Not Committed Yet 2013-10-18 00:25:12 -0700   2)  *   Copyright (C) 2011  Bartek Przybylski
00000000 src/com/owncloud/android/ui/QuickAction.java (Not Committed Yet 2013-10-18 00:25:12 -0700   3)  *   Copyright (C) 2012-2013 ownCloud Inc.
00000000 src/com/owncloud/android/ui/QuickAction.java (Not Committed Yet 2013-10-18 00:25:12 -0700   4)  *
00000000 src/com/owncloud/android/ui/QuickAction.java (Not Committed Yet 2013-10-18 00:25:12 -0700   5)  *   This program is free software: you can redistribute it and/or modify
00000000 src/com/owncloud/android/ui/QuickAction.java (Not Committed Yet 2013-10-18 00:25:12 -0700   6)  *   it under the terms of the GNU General Public License version 2,
00000000 src/com/owncloud/android/ui/QuickAction.java (Not Committed Yet 2013-10-18 00:25:12 -0700   7)  *   as published by the Free Software Foundation.
00000000 src/com/owncloud/android/ui/QuickAction.java (Not Committed Yet 2013-10-18 00:25:12 -0700   8)  *
00000000 src/com/owncloud/android/ui/QuickAction.java (Not Committed Yet 2013-10-18 00:25:12 -0700   9)  *   This program is distributed in the hope that it will be useful,
00000000 src/com/owncloud/android/ui/QuickAction.java (Not Committed Yet 2013-10-18 00:25:12 -0700  10)  *   but WITHOUT ANY WARRANTY; without even the implied warranty of
00000000 src/com/owncloud/android/ui/QuickAction.java (Not Committed Yet 2013-10-18 00:25:12 -0700  11)  *   MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
00000000 src/com/owncloud/android/ui/QuickAction.java (Not Committed Yet 2013-10-18 00:25:12 -0700  12)  *   GNU General Public License for more details.
00000000 src/com/owncloud/android/ui/QuickAction.java (Not Committed Yet 2013-10-18 00:25:12 -0700  13)  *
00000000 src/com/owncloud/android/ui/QuickAction.java (Not Committed Yet 2013-10-18 00:25:12 -0700  14)  *   You should have received a copy of the GNU General Public License
00000000 src/com/owncloud/android/ui/QuickAction.java (Not Committed Yet 2013-10-18 00:25:12 -0700  15)  *   along with this program.  If not, see <http://www.gnu.org/licenses/>.
00000000 src/com/owncloud/android/ui/QuickAction.java (Not Committed Yet 2013-10-18 00:25:12 -0700  16)  *
00000000 src/com/owncloud/android/ui/QuickAction.java (Not Committed Yet 2013-10-18 00:25:12 -0700  17)  */
00000000 src/com/owncloud/android/ui/QuickAction.java (Not Committed Yet 2013-10-18 00:25:12 -0700  18) package com.owncloud.android.ui;
00000000 src/com/owncloud/android/ui/QuickAction.java (Not Committed Yet 2013-10-18 00:25:12 -0700  19) 
00000000 src/com/owncloud/android/ui/QuickAction.java (Not Committed Yet 2013-10-18 00:25:12 -0700  20) import android.content.Context;
00000000 src/com/owncloud/android/ui/QuickAction.java (Not Committed Yet 2013-10-18 00:25:12 -0700  21) 
00000000 src/com/owncloud/android/ui/QuickAction.java (Not Committed Yet 2013-10-18 00:25:12 -0700  22) import android.graphics.Rect;
00000000 src/com/owncloud/android/ui/QuickAction.java (Not Committed Yet 2013-10-18 00:25:12 -0700  23) import android.graphics.drawable.Drawable;
00000000 src/com/owncloud/android/ui/QuickAction.java (Not Committed Yet 2013-10-18 00:25:12 -0700  24) 
00000000 src/com/owncloud/android/ui/QuickAction.java (Not Committed Yet 2013-10-18 00:25:12 -0700  25) import android.widget.ImageView;
00000000 src/com/owncloud/android/ui/QuickAction.java (Not Committed Yet 2013-10-18 00:25:12 -0700  26) import android.widget.TextView;
00000000 src/com/owncloud/android/ui/QuickAction.java (Not Committed Yet 2013-10-18 00:25:12 -0700  27) import android.widget.LinearLayout;
00000000 src/com/owncloud/android/ui/QuickAction.java (Not Committed Yet 2013-10-18 00:25:12 -0700  28) import android.widget.ScrollView;
00000000 src/com/owncloud/android/ui/QuickAction.java (Not Committed Yet 2013-10-18 00:25:12 -0700  29) 
00000000 src/com/owncloud/android/ui/QuickAction.java (Not Committed Yet 2013-10-18 00:25:12 -0700  30) import android.view.Gravity;
00000000 src/com/owncloud/android/ui/QuickAction.java (Not Committed Yet 2013-10-18 00:25:12 -0700  31) import android.view.LayoutInflater;
00000000 src/com/owncloud/android/ui/QuickAction.java (Not Committed Yet 2013-10-18 00:25:12 -0700  32) import android.view.View;
00000000 src/com/owncloud/android/ui/QuickAction.java (Not Committed Yet 2013-10-18 00:25:12 -0700  33) import android.view.View.OnClickListener;
00000000 src/com/owncloud/android/ui/QuickAction.java (Not Committed Yet 2013-10-18 00:25:12 -0700  34) import android.view.ViewGroup.LayoutParams;
00000000 src/com/owncloud/android/ui/QuickAction.java (Not Committed Yet 2013-10-18 00:25:12 -0700  35) import android.view.ViewGroup;
00000000 src/com/owncloud/android/ui/QuickAction.java (Not Committed Yet 2013-10-18 00:25:12 -0700  36) 
00000000 src/com/owncloud/android/ui/QuickAction.java (Not Committed Yet 2013-10-18 00:25:12 -0700  37) import java.util.ArrayList;
00000000 src/com/owncloud/android/ui/QuickAction.java (Not Committed Yet 2013-10-18 00:25:12 -0700  38) 
00000000 src/com/owncloud/android/ui/QuickAction.java (Not Committed Yet 2013-10-18 00:25:12 -0700  39) import com.owncloud.android.R;
00000000 src/com/owncloud/android/ui/QuickAction.java (Not Committed Yet 2013-10-18 00:25:12 -0700  40) 
00000000 src/com/owncloud/android/ui/QuickAction.java (Not Committed Yet 2013-10-18 00:25:12 -0700  41) /**
00000000 src/com/owncloud/android/ui/QuickAction.java (Not Committed Yet 2013-10-18 00:25:12 -0700  42)  * Popup window, shows action list as icon and text like the one in Gallery3D
00000000 src/com/owncloud/android/ui/QuickAction.java (Not Committed Yet 2013-10-18 00:25:12 -0700  43)  * app.
00000000 src/com/owncloud/android/ui/QuickAction.java (Not Committed Yet 2013-10-18 00:25:12 -0700  44)  * 
00000000 src/com/owncloud/android/ui/QuickAction.java (Not Committed Yet 2013-10-18 00:25:12 -0700  45)  * @author Lorensius. W. T
00000000 src/com/owncloud/android/ui/QuickAction.java (Not Committed Yet 2013-10-18 00:25:12 -0700  46)  */
00000000 src/com/owncloud/android/ui/QuickAction.java (Not Committed Yet 2013-10-18 00:25:12 -0700  47) public class QuickAction extends CustomPopup {
00000000 src/com/owncloud/android/ui/QuickAction.java (Not Committed Yet 2013-10-18 00:25:12 -0700  48)     private final View root;
00000000 src/com/owncloud/android/ui/QuickAction.java (Not Committed Yet 2013-10-18 00:25:12 -0700  49)     private final ImageView mArrowUp;
00000000 src/com/owncloud/android/ui/QuickAction.java (Not Committed Yet 2013-10-18 00:25:12 -0700  50)     private final ImageView mArrowDown;
00000000 src/com/owncloud/android/ui/QuickAction.java (Not Committed Yet 2013-10-18 00:25:12 -0700  51)     private final LayoutInflater inflater;
00000000 src/com/owncloud/android/ui/QuickAction.java (Not Committed Yet 2013-10-18 00:25:12 -0700  52)     private final Context context;
00000000 src/com/owncloud/android/ui/QuickAction.java (Not Committed Yet 2013-10-18 00:25:12 -0700  53) 
00000000 src/com/owncloud/android/ui/QuickAction.java (Not Committed Yet 2013-10-18 00:25:12 -0700  54)     protected static final int ANIM_GROW_FROM_LEFT = 1;
00000000 src/com/owncloud/android/ui/QuickAction.java (Not Committed Yet 2013-10-18 00:25:12 -0700  55)     protected static final int ANIM_GROW_FROM_RIGHT = 2;
00000000 src/com/owncloud/android/ui/QuickAction.java (Not Committed Yet 2013-10-18 00:25:12 -0700  56)     protected static final int ANIM_GROW_FROM_CENTER = 3;
00000000 src/com/owncloud/android/ui/QuickAction.java (Not Committed Yet 2013-10-18 00:25:12 -0700  57)     protected static final int ANIM_REFLECT = 4;
00000000 src/com/owncloud/android/ui/QuickAction.java (Not Committed Yet 2013-10-18 00:25:12 -0700  58)     protected static final int ANIM_AUTO = 5;
00000000 src/com/owncloud/android/ui/QuickAction.java (Not Committed Yet 2013-10-18 00:25:12 -0700  59) 
00000000 src/com/owncloud/android/ui/QuickAction.java (Not Committed Yet 2013-10-18 00:25:12 -0700  60)     private int animStyle;
00000000 src/com/owncloud/android/ui/QuickAction.java (Not Committed Yet 2013-10-18 00:25:12 -0700  61)     private ViewGroup mTrack;
00000000 src/com/owncloud/android/ui/QuickAction.java (Not Committed Yet 2013-10-18 00:25:12 -0700  62)     private ScrollView scroller;
00000000 src/com/owncloud/android/ui/QuickAction.java (Not Committed Yet 2013-10-18 00:25:12 -0700  63)     private ArrayList<ActionItem> actionList;
00000000 src/com/owncloud/android/ui/QuickAction.java (Not Committed Yet 2013-10-18 00:25:12 -0700  64) 
00000000 src/com/owncloud/android/ui/QuickAction.java (Not Committed Yet 2013-10-18 00:25:12 -0700  65)     /**
00000000 src/com/owncloud/android/ui/QuickAction.java (Not Committed Yet 2013-10-18 00:25:12 -0700  66)      * Constructor
00000000 src/com/owncloud/android/ui/QuickAction.java (Not Committed Yet 2013-10-18 00:25:12 -0700  67)      * 
00000000 src/com/owncloud/android/ui/QuickAction.java (Not Committed Yet 2013-10-18 00:25:12 -0700  68)      * @param anchor {@link View} on where the popup window should be displayed
00000000 src/com/owncloud/android/ui/QuickAction.java (Not Committed Yet 2013-10-18 00:25:12 -0700  69)      */
00000000 src/com/owncloud/android/ui/QuickAction.java (Not Committed Yet 2013-10-18 00:25:12 -0700  70)     public QuickAction(View anchor) {
00000000 src/com/owncloud/android/ui/QuickAction.java (Not Committed Yet 2013-10-18 00:25:12 -0700  71)         super(anchor);
00000000 src/com/owncloud/android/ui/QuickAction.java (Not Committed Yet 2013-10-18 00:25:12 -0700  72) 
00000000 src/com/owncloud/android/ui/QuickAction.java (Not Committed Yet 2013-10-18 00:25:12 -0700  73)         actionList = new ArrayList<ActionItem>();
00000000 src/com/owncloud/android/ui/QuickAction.java (Not Committed Yet 2013-10-18 00:25:12 -0700  74)         context = anchor.getContext();
00000000 src/com/owncloud/android/ui/QuickAction.java (Not Committed Yet 2013-10-18 00:25:12 -0700  75)         inflater = (LayoutInflater) context
00000000 src/com/owncloud/android/ui/QuickAction.java (Not Committed Yet 2013-10-18 00:25:12 -0700  76)                 .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
00000000 src/com/owncloud/android/ui/QuickAction.java (Not Committed Yet 2013-10-18 00:25:12 -0700  77) 
00000000 src/com/owncloud/android/ui/QuickAction.java (Not Committed Yet 2013-10-18 00:25:12 -0700  78)         root = (ViewGroup) inflater.inflate(R.layout.popup, null);
00000000 src/com/owncloud/android/ui/QuickAction.java (Not Committed Yet 2013-10-18 00:25:12 -0700  79) 
00000000 src/com/owncloud/android/ui/QuickAction.java (Not Committed Yet 2013-10-18 00:25:12 -0700  80)         mArrowDown = (ImageView) root.findViewById(R.id.arrow_down);
00000000 src/com/owncloud/android/ui/QuickAction.java (Not Committed Yet 2013-10-18 00:25:12 -0700  81)         mArrowUp = (ImageView) root.findViewById(R.id.arrow_up);
00000000 src/com/owncloud/android/ui/QuickAction.java (Not Committed Yet 2013-10-18 00:25:12 -0700  82) 
00000000 src/com/owncloud/android/ui/QuickAction.java (Not Committed Yet 2013-10-18 00:25:12 -0700  83)         setContentView(root);
00000000 src/com/owncloud/android/ui/QuickAction.java (Not Committed Yet 2013-10-18 00:25:12 -0700  84) 
00000000 src/com/owncloud/android/ui/QuickAction.java (Not Committed Yet 2013-10-18 00:25:12 -0700  85)         mTrack = (ViewGroup) root.findViewById(R.id.tracks);
00000000 src/com/owncloud/android/ui/QuickAction.java (Not Committed Yet 2013-10-18 00:25:12 -0700  86)         scroller = (ScrollView) root.findViewById(R.id.scroller);
00000000 src/com/owncloud/android/ui/QuickAction.java (Not Committed Yet 2013-10-18 00:25:12 -0700  87)         animStyle = ANIM_AUTO;
00000000 src/com/owncloud/android/ui/QuickAction.java (Not Committed Yet 2013-10-18 00:25:12 -0700  88)     }
00000000 src/com/owncloud/android/ui/QuickAction.java (Not Committed Yet 2013-10-18 00:25:12 -0700  89) 
00000000 src/com/owncloud/android/ui/QuickAction.java (Not Committed Yet 2013-10-18 00:25:12 -0700  90)     /**
00000000 src/com/owncloud/android/ui/QuickAction.java (Not Committed Yet 2013-10-18 00:25:12 -0700  91)      * Set animation style
00000000 src/com/owncloud/android/ui/QuickAction.java (Not Committed Yet 2013-10-18 00:25:12 -0700  92)      * 
00000000 src/com/owncloud/android/ui/QuickAction.java (Not Committed Yet 2013-10-18 00:25:12 -0700  93)      * @param animStyle animation style, default is set to ANIM_AUTO
00000000 src/com/owncloud/android/ui/QuickAction.java (Not Committed Yet 2013-10-18 00:25:12 -0700  94)      */
00000000 src/com/owncloud/android/ui/QuickAction.java (Not Committed Yet 2013-10-18 00:25:12 -0700  95)     public void setAnimStyle(int animStyle) {
00000000 src/com/owncloud/android/ui/QuickAction.java (Not Committed Yet 2013-10-18 00:25:12 -0700  96)         this.animStyle = animStyle;
00000000 src/com/owncloud/android/ui/QuickAction.java (Not Committed Yet 2013-10-18 00:25:12 -0700  97)     }
00000000 src/com/owncloud/android/ui/QuickAction.java (Not Committed Yet 2013-10-18 00:25:12 -0700  98) 
00000000 src/com/owncloud/android/ui/QuickAction.java (Not Committed Yet 2013-10-18 00:25:12 -0700  99)     /**
00000000 src/com/owncloud/android/ui/QuickAction.java (Not Committed Yet 2013-10-18 00:25:12 -0700 100)      * Add action item
00000000 src/com/owncloud/android/ui/QuickAction.java (Not Committed Yet 2013-10-18 00:25:12 -0700 101)      * 
00000000 src/com/owncloud/android/ui/QuickAction.java (Not Committed Yet 2013-10-18 00:25:12 -0700 102)      * @param action {@link ActionItem} object
00000000 src/com/owncloud/android/ui/QuickAction.java (Not Committed Yet 2013-10-18 00:25:12 -0700 103)      */
00000000 src/com/owncloud/android/ui/QuickAction.java (Not Committed Yet 2013-10-18 00:25:12 -0700 104)     public void addActionItem(ActionItem action) {
00000000 src/com/owncloud/android/ui/QuickAction.java (Not Committed Yet 2013-10-18 00:25:12 -0700 105)         actionList.add(action);
00000000 src/com/owncloud/android/ui/QuickAction.java (Not Committed Yet 2013-10-18 00:25:12 -0700 106)     }
00000000 src/com/owncloud/android/ui/QuickAction.java (Not Committed Yet 2013-10-18 00:25:12 -0700 107) 
00000000 src/com/owncloud/android/ui/QuickAction.java (Not Committed Yet 2013-10-18 00:25:12 -0700 108)     /**
00000000 src/com/owncloud/android/ui/QuickAction.java (Not Committed Yet 2013-10-18 00:25:12 -0700 109)      * Show popup window. Popup is automatically positioned, on top or bottom of
00000000 src/com/owncloud/android/ui/QuickAction.java (Not Committed Yet 2013-10-18 00:25:12 -0700 110)      * anchor view.
00000000 src/com/owncloud/android/ui/QuickAction.java (Not Committed Yet 2013-10-18 00:25:12 -0700 111)      * 
00000000 src/com/owncloud/android/ui/QuickAction.java (Not Committed Yet 2013-10-18 00:25:12 -0700 112)      */
00000000 src/com/owncloud/android/ui/QuickAction.java (Not Committed Yet 2013-10-18 00:25:12 -0700 113)     public void show() {
00000000 src/com/owncloud/android/ui/QuickAction.java (Not Committed Yet 2013-10-18 00:25:12 -0700 114)         preShow();
00000000 src/com/owncloud/android/ui/QuickAction.java (Not Committed Yet 2013-10-18 00:25:12 -0700 115) 
00000000 src/com/owncloud/android/ui/QuickAction.java (Not Committed Yet 2013-10-18 00:25:12 -0700 116)         int xPos, yPos;
00000000 src/com/owncloud/android/ui/QuickAction.java (Not Committed Yet 2013-10-18 00:25:12 -0700 117) 
00000000 src/com/owncloud/android/ui/QuickAction.java (Not Committed Yet 2013-10-18 00:25:12 -0700 118)         int[] location = new int[2];
00000000 src/com/owncloud/android/ui/QuickAction.java (Not Committed Yet 2013-10-18 00:25:12 -0700 119) 
00000000 src/com/owncloud/android/ui/QuickAction.java (Not Committed Yet 2013-10-18 00:25:12 -0700 120)         mAnchor.getLocationOnScreen(location);
00000000 src/com/owncloud/android/ui/QuickAction.java (Not Committed Yet 2013-10-18 00:25:12 -0700 121) 
00000000 src/com/owncloud/android/ui/QuickAction.java (Not Committed Yet 2013-10-18 00:25:12 -0700 122)         Rect anchorRect = new Rect(location[0], location[1], location[0]
00000000 src/com/owncloud/android/ui/QuickAction.java (Not Committed Yet 2013-10-18 00:25:12 -0700 123)                 + mAnchor.getWidth(), location[1] + mAnchor.getHeight());
00000000 src/com/owncloud/android/ui/QuickAction.java (Not Committed Yet 2013-10-18 00:25:12 -0700 124) 
00000000 src/com/owncloud/android/ui/QuickAction.java (Not Committed Yet 2013-10-18 00:25:12 -0700 125)         createActionList();
00000000 src/com/owncloud/android/ui/QuickAction.java (Not Committed Yet 2013-10-18 00:25:12 -0700 126) 
00000000 src/com/owncloud/android/ui/QuickAction.java (Not Committed Yet 2013-10-18 00:25:12 -0700 127)         root.setLayoutParams(new LayoutParams(LayoutParams.WRAP_CONTENT,
00000000 src/com/owncloud/android/ui/QuickAction.java (Not Committed Yet 2013-10-18 00:25:12 -0700 128)                 LayoutParams.WRAP_CONTENT));
00000000 src/com/owncloud/android/ui/QuickAction.java (Not Committed Yet 2013-10-18 00:25:12 -0700 129)         root.measure(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
00000000 src/com/owncloud/android/ui/QuickAction.java (Not Committed Yet 2013-10-18 00:25:12 -0700 130) 
00000000 src/com/owncloud/android/ui/QuickAction.java (Not Committed Yet 2013-10-18 00:25:12 -0700 131)         int rootHeight = root.getMeasuredHeight();
00000000 src/com/owncloud/android/ui/QuickAction.java (Not Committed Yet 2013-10-18 00:25:12 -0700 132)         int rootWidth = root.getMeasuredWidth();
00000000 src/com/owncloud/android/ui/QuickAction.java (Not Committed Yet 2013-10-18 00:25:12 -0700 133) 
00000000 src/com/owncloud/android/ui/QuickAction.java (Not Committed Yet 2013-10-18 00:25:12 -0700 134)         int screenWidth = mWManager.getDefaultDisplay().getWidth();
00000000 src/com/owncloud/android/ui/QuickAction.java (Not Committed Yet 2013-10-18 00:25:12 -0700 135)         int screenHeight = mWManager.getDefaultDisplay().getHeight();
00000000 src/com/owncloud/android/ui/QuickAction.java (Not Committed Yet 2013-10-18 00:25:12 -0700 136) 
00000000 src/com/owncloud/android/ui/QuickAction.java (Not Committed Yet 2013-10-18 00:25:12 -0700 137)         // automatically get X coord of popup (top left)
00000000 src/com/owncloud/android/ui/QuickAction.java (Not Committed Yet 2013-10-18 00:25:12 -0700 138)         if ((anchorRect.left + rootWidth) > screenWidth) {
00000000 src/com/owncloud/android/ui/QuickAction.java (Not Committed Yet 2013-10-18 00:25:12 -0700 139)             xPos = anchorRect.left - (rootWidth - mAnchor.getWidth());
00000000 src/com/owncloud/android/ui/QuickAction.java (Not Committed Yet 2013-10-18 00:25:12 -0700 140)         } else {
00000000 src/com/owncloud/android/ui/QuickAction.java (Not Committed Yet 2013-10-18 00:25:12 -0700 141)             if (mAnchor.getWidth() > rootWidth) {
00000000 src/com/owncloud/android/ui/QuickAction.java (Not Committed Yet 2013-10-18 00:25:12 -0700 142)                 xPos = anchorRect.centerX() - (rootWidth / 2);
00000000 src/com/owncloud/android/ui/QuickAction.java (Not Committed Yet 2013-10-18 00:25:12 -0700 143)             } else {
00000000 src/com/owncloud/android/ui/QuickAction.java (Not Committed Yet 2013-10-18 00:25:12 -0700 144)                 xPos = anchorRect.left;
00000000 src/com/owncloud/android/ui/QuickAction.java (Not Committed Yet 2013-10-18 00:25:12 -0700 145)             }
00000000 src/com/owncloud/android/ui/QuickAction.java (Not Committed Yet 2013-10-18 00:25:12 -0700 146)         }
00000000 src/com/owncloud/android/ui/QuickAction.java (Not Committed Yet 2013-10-18 00:25:12 -0700 147) 
00000000 src/com/owncloud/android/ui/QuickAction.java (Not Committed Yet 2013-10-18 00:25:12 -0700 148)         int dyTop = anchorRect.top;
00000000 src/com/owncloud/android/ui/QuickAction.java (Not Committed Yet 2013-10-18 00:25:12 -0700 149)         int dyBottom = screenHeight - anchorRect.bottom;
00000000 src/com/owncloud/android/ui/QuickAction.java (Not Committed Yet 2013-10-18 00:25:12 -0700 150) 
00000000 src/com/owncloud/android/ui/QuickAction.java (Not Committed Yet 2013-10-18 00:25:12 -0700 151)         boolean onTop = (dyTop > dyBottom) ? true : false;
00000000 src/com/owncloud/android/ui/QuickAction.java (Not Committed Yet 2013-10-18 00:25:12 -0700 152) 
00000000 src/com/owncloud/android/ui/QuickAction.java (Not Committed Yet 2013-10-18 00:25:12 -0700 153)         if (onTop) {
00000000 src/com/owncloud/android/ui/QuickAction.java (Not Committed Yet 2013-10-18 00:25:12 -0700 154)             if (rootHeight > dyTop) {
00000000 src/com/owncloud/android/ui/QuickAction.java (Not Committed Yet 2013-10-18 00:25:12 -0700 155)                 yPos = 15;
00000000 src/com/owncloud/android/ui/QuickAction.java (Not Committed Yet 2013-10-18 00:25:12 -0700 156)                 LayoutParams l = scroller.getLayoutParams();
00000000 src/com/owncloud/android/ui/QuickAction.java (Not Committed Yet 2013-10-18 00:25:12 -0700 157)                 l.height = dyTop - mAnchor.getHeight();
00000000 src/com/owncloud/android/ui/QuickAction.java (Not Committed Yet 2013-10-18 00:25:12 -0700 158)             } else {
00000000 src/com/owncloud/android/ui/QuickAction.java (Not Committed Yet 2013-10-18 00:25:12 -0700 159)                 yPos = anchorRect.top - rootHeight;
00000000 src/com/owncloud/android/ui/QuickAction.java (Not Committed Yet 2013-10-18 00:25:12 -0700 160)             }
00000000 src/com/owncloud/android/ui/QuickAction.java (Not Committed Yet 2013-10-18 00:25:12 -0700 161)         } else {
00000000 src/com/owncloud/android/ui/QuickAction.java (Not Committed Yet 2013-10-18 00:25:12 -0700 162)             yPos = anchorRect.bottom;
00000000 src/com/owncloud/android/ui/QuickAction.java (Not Committed Yet 2013-10-18 00:25:12 -0700 163) 
00000000 src/com/owncloud/android/ui/QuickAction.java (Not Committed Yet 2013-10-18 00:25:12 -0700 164)             if (rootHeight > dyBottom) {
00000000 src/com/owncloud/android/ui/QuickAction.java (Not Committed Yet 2013-10-18 00:25:12 -0700 165)                 LayoutParams l = scroller.getLayoutParams();
00000000 src/com/owncloud/android/ui/QuickAction.java (Not Committed Yet 2013-10-18 00:25:12 -0700 166)                 l.height = dyBottom;
00000000 src/com/owncloud/android/ui/QuickAction.java (Not Committed Yet 2013-10-18 00:25:12 -0700 167)             }
00000000 src/com/owncloud/android/ui/QuickAction.java (Not Committed Yet 2013-10-18 00:25:12 -0700 168)         }
00000000 src/com/owncloud/android/ui/QuickAction.java (Not Committed Yet 2013-10-18 00:25:12 -0700 169) 
00000000 src/com/owncloud/android/ui/QuickAction.java (Not Committed Yet 2013-10-18 00:25:12 -0700 170)         showArrow(((onTop) ? R.id.arrow_down : R.id.arrow_up),
00000000 src/com/owncloud/android/ui/QuickAction.java (Not Committed Yet 2013-10-18 00:25:12 -0700 171)                 anchorRect.centerX() - xPos);
00000000 src/com/owncloud/android/ui/QuickAction.java (Not Committed Yet 2013-10-18 00:25:12 -0700 172) 
00000000 src/com/owncloud/android/ui/QuickAction.java (Not Committed Yet 2013-10-18 00:25:12 -0700 173)         setAnimationStyle(screenWidth, anchorRect.centerX(), onTop);
00000000 src/com/owncloud/android/ui/QuickAction.java (Not Committed Yet 2013-10-18 00:25:12 -0700 174) 
00000000 src/com/owncloud/android/ui/QuickAction.java (Not Committed Yet 2013-10-18 00:25:12 -0700 175)         mWindow.showAtLocation(mAnchor, Gravity.NO_GRAVITY, xPos, yPos);
00000000 src/com/owncloud/android/ui/QuickAction.java (Not Committed Yet 2013-10-18 00:25:12 -0700 176)     }
00000000 src/com/owncloud/android/ui/QuickAction.java (Not Committed Yet 2013-10-18 00:25:12 -0700 177) 
00000000 src/com/owncloud/android/ui/QuickAction.java (Not Committed Yet 2013-10-18 00:25:12 -0700 178)     /**
00000000 src/com/owncloud/android/ui/QuickAction.java (Not Committed Yet 2013-10-18 00:25:12 -0700 179)      * Set animation style
00000000 src/com/owncloud/android/ui/QuickAction.java (Not Committed Yet 2013-10-18 00:25:12 -0700 180)      * 
00000000 src/com/owncloud/android/ui/QuickAction.java (Not Committed Yet 2013-10-18 00:25:12 -0700 181)      * @param screenWidth screen width
00000000 src/com/owncloud/android/ui/QuickAction.java (Not Committed Yet 2013-10-18 00:25:12 -0700 182)      * @param requestedX distance from left edge
00000000 src/com/owncloud/android/ui/QuickAction.java (Not Committed Yet 2013-10-18 00:25:12 -0700 183)      * @param onTop flag to indicate where the popup should be displayed. Set
00000000 src/com/owncloud/android/ui/QuickAction.java (Not Committed Yet 2013-10-18 00:25:12 -0700 184)      *            TRUE if displayed on top of anchor view and vice versa
00000000 src/com/owncloud/android/ui/QuickAction.java (Not Committed Yet 2013-10-18 00:25:12 -0700 185)      */
00000000 src/com/owncloud/android/ui/QuickAction.java (Not Committed Yet 2013-10-18 00:25:12 -0700 186)     private void setAnimationStyle(int screenWidth, int requestedX,
00000000 src/com/owncloud/android/ui/QuickAction.java (Not Committed Yet 2013-10-18 00:25:12 -0700 187)             boolean onTop) {
00000000 src/com/owncloud/android/ui/QuickAction.java (Not Committed Yet 2013-10-18 00:25:12 -0700 188)         int arrowPos = requestedX - mArrowUp.getMeasuredWidth() / 2;
00000000 src/com/owncloud/android/ui/QuickAction.java (Not Committed Yet 2013-10-18 00:25:12 -0700 189) 
00000000 src/com/owncloud/android/ui/QuickAction.java (Not Committed Yet 2013-10-18 00:25:12 -0700 190)         switch (animStyle) {
00000000 src/com/owncloud/android/ui/QuickAction.java (Not Committed Yet 2013-10-18 00:25:12 -0700 191)         case ANIM_GROW_FROM_LEFT:
00000000 src/com/owncloud/android/ui/QuickAction.java (Not Committed Yet 2013-10-18 00:25:12 -0700 192)             mWindow.setAnimationStyle((onTop) ? R.style.Animations_PopUpMenu_Left
00000000 src/com/owncloud/android/ui/QuickAction.java (Not Committed Yet 2013-10-18 00:25:12 -0700 193)                     : R.style.Animations_PopDownMenu_Left);
00000000 src/com/owncloud/android/ui/QuickAction.java (Not Committed Yet 2013-10-18 00:25:12 -0700 194)             break;
00000000 src/com/owncloud/android/ui/QuickAction.java (Not Committed Yet 2013-10-18 00:25:12 -0700 195) 
00000000 src/com/owncloud/android/ui/QuickAction.java (Not Committed Yet 2013-10-18 00:25:12 -0700 196)         case ANIM_GROW_FROM_RIGHT:
00000000 src/com/owncloud/android/ui/QuickAction.java (Not Committed Yet 2013-10-18 00:25:12 -0700 197)             mWindow.setAnimationStyle((onTop) ? R.style.Animations_PopUpMenu_Right
00000000 src/com/owncloud/android/ui/QuickAction.java (Not Committed Yet 2013-10-18 00:25:12 -0700 198)                     : R.style.Animations_PopDownMenu_Right);
00000000 src/com/owncloud/android/ui/QuickAction.java (Not Committed Yet 2013-10-18 00:25:12 -0700 199)             break;
00000000 src/com/owncloud/android/ui/QuickAction.java (Not Committed Yet 2013-10-18 00:25:12 -0700 200) 
00000000 src/com/owncloud/android/ui/QuickAction.java (Not Committed Yet 2013-10-18 00:25:12 -0700 201)         case ANIM_GROW_FROM_CENTER:
00000000 src/com/owncloud/android/ui/QuickAction.java (Not Committed Yet 2013-10-18 00:25:12 -0700 202)             mWindow.setAnimationStyle((onTop) ? R.style.Animations_PopUpMenu_Center
00000000 src/com/owncloud/android/ui/QuickAction.java (Not Committed Yet 2013-10-18 00:25:12 -0700 203)                     : R.style.Animations_PopDownMenu_Center);
00000000 src/com/owncloud/android/ui/QuickAction.java (Not Committed Yet 2013-10-18 00:25:12 -0700 204)             break;
00000000 src/com/owncloud/android/ui/QuickAction.java (Not Committed Yet 2013-10-18 00:25:12 -0700 205) 
00000000 src/com/owncloud/android/ui/QuickAction.java (Not Committed Yet 2013-10-18 00:25:12 -0700 206)         case ANIM_REFLECT:
00000000 src/com/owncloud/android/ui/QuickAction.java (Not Committed Yet 2013-10-18 00:25:12 -0700 207)             mWindow.setAnimationStyle((onTop) ? R.style.Animations_PopUpMenu_Reflect
00000000 src/com/owncloud/android/ui/QuickAction.java (Not Committed Yet 2013-10-18 00:25:12 -0700 208)                     : R.style.Animations_PopDownMenu_Reflect);
00000000 src/com/owncloud/android/ui/QuickAction.java (Not Committed Yet 2013-10-18 00:25:12 -0700 209)             break;
00000000 src/com/owncloud/android/ui/QuickAction.java (Not Committed Yet 2013-10-18 00:25:12 -0700 210) 
00000000 src/com/owncloud/android/ui/QuickAction.java (Not Committed Yet 2013-10-18 00:25:12 -0700 211)         case ANIM_AUTO:
00000000 src/com/owncloud/android/ui/QuickAction.java (Not Committed Yet 2013-10-18 00:25:12 -0700 212)             if (arrowPos <= screenWidth / 4) {
00000000 src/com/owncloud/android/ui/QuickAction.java (Not Committed Yet 2013-10-18 00:25:12 -0700 213)                 mWindow.setAnimationStyle((onTop) ? R.style.Animations_PopUpMenu_Left
00000000 src/com/owncloud/android/ui/QuickAction.java (Not Committed Yet 2013-10-18 00:25:12 -0700 214)                         : R.style.Animations_PopDownMenu_Left);
00000000 src/com/owncloud/android/ui/QuickAction.java (Not Committed Yet 2013-10-18 00:25:12 -0700 215)             } else if (arrowPos > screenWidth / 4
00000000 src/com/owncloud/android/ui/QuickAction.java (Not Committed Yet 2013-10-18 00:25:12 -0700 216)                     && arrowPos < 3 * (screenWidth / 4)) {
00000000 src/com/owncloud/android/ui/QuickAction.java (Not Committed Yet 2013-10-18 00:25:12 -0700 217)                 mWindow.setAnimationStyle((onTop) ? R.style.Animations_PopUpMenu_Center
00000000 src/com/owncloud/android/ui/QuickAction.java (Not Committed Yet 2013-10-18 00:25:12 -0700 218)                         : R.style.Animations_PopDownMenu_Center);
00000000 src/com/owncloud/android/ui/QuickAction.java (Not Committed Yet 2013-10-18 00:25:12 -0700 219)             } else {
00000000 src/com/owncloud/android/ui/QuickAction.java (Not Committed Yet 2013-10-18 00:25:12 -0700 220)                 mWindow.setAnimationStyle((onTop) ? R.style.Animations_PopUpMenu_Right
00000000 src/com/owncloud/android/ui/QuickAction.java (Not Committed Yet 2013-10-18 00:25:12 -0700 221)                         : R.style.Animations_PopDownMenu_Right);
00000000 src/com/owncloud/android/ui/QuickAction.java (Not Committed Yet 2013-10-18 00:25:12 -0700 222)             }
00000000 src/com/owncloud/android/ui/QuickAction.java (Not Committed Yet 2013-10-18 00:25:12 -0700 223) 
00000000 src/com/owncloud/android/ui/QuickAction.java (Not Committed Yet 2013-10-18 00:25:12 -0700 224)             break;
00000000 src/com/owncloud/android/ui/QuickAction.java (Not Committed Yet 2013-10-18 00:25:12 -0700 225)         }
00000000 src/com/owncloud/android/ui/QuickAction.java (Not Committed Yet 2013-10-18 00:25:12 -0700 226)     }
00000000 src/com/owncloud/android/ui/QuickAction.java (Not Committed Yet 2013-10-18 00:25:12 -0700 227) 
00000000 src/com/owncloud/android/ui/QuickAction.java (Not Committed Yet 2013-10-18 00:25:12 -0700 228)     /**
00000000 src/com/owncloud/android/ui/QuickAction.java (Not Committed Yet 2013-10-18 00:25:12 -0700 229)      * Create action list
00000000 src/com/owncloud/android/ui/QuickAction.java (Not Committed Yet 2013-10-18 00:25:12 -0700 230)      */
00000000 src/com/owncloud/android/ui/QuickAction.java (Not Committed Yet 2013-10-18 00:25:12 -0700 231)     private void createActionList() {
00000000 src/com/owncloud/android/ui/QuickAction.java (Not Committed Yet 2013-10-18 00:25:12 -0700 232)         View view;
00000000 src/com/owncloud/android/ui/QuickAction.java (Not Committed Yet 2013-10-18 00:25:12 -0700 233)         String title;
00000000 src/com/owncloud/android/ui/QuickAction.java (Not Committed Yet 2013-10-18 00:25:12 -0700 234)         Drawable icon;
00000000 src/com/owncloud/android/ui/QuickAction.java (Not Committed Yet 2013-10-18 00:25:12 -0700 235)         OnClickListener listener;
00000000 src/com/owncloud/android/ui/QuickAction.java (Not Committed Yet 2013-10-18 00:25:12 -0700 236) 
00000000 src/com/owncloud/android/ui/QuickAction.java (Not Committed Yet 2013-10-18 00:25:12 -0700 237)         for (int i = 0; i < actionList.size(); i++) {
00000000 src/com/owncloud/android/ui/QuickAction.java (Not Committed Yet 2013-10-18 00:25:12 -0700 238)             title = actionList.get(i).getTitle();
00000000 src/com/owncloud/android/ui/QuickAction.java (Not Committed Yet 2013-10-18 00:25:12 -0700 239)             icon = actionList.get(i).getIcon();
00000000 src/com/owncloud/android/ui/QuickAction.java (Not Committed Yet 2013-10-18 00:25:12 -0700 240)             listener = actionList.get(i).getOnClickListerner();
00000000 src/com/owncloud/android/ui/QuickAction.java (Not Committed Yet 2013-10-18 00:25:12 -0700 241) 
00000000 src/com/owncloud/android/ui/QuickAction.java (Not Committed Yet 2013-10-18 00:25:12 -0700 242)             view = getActionItem(title, icon, listener);
00000000 src/com/owncloud/android/ui/QuickAction.java (Not Committed Yet 2013-10-18 00:25:12 -0700 243) 
00000000 src/com/owncloud/android/ui/QuickAction.java (Not Committed Yet 2013-10-18 00:25:12 -0700 244)             view.setFocusable(true);
00000000 src/com/owncloud/android/ui/QuickAction.java (Not Committed Yet 2013-10-18 00:25:12 -0700 245)             view.setClickable(true);
00000000 src/com/owncloud/android/ui/QuickAction.java (Not Committed Yet 2013-10-18 00:25:12 -0700 246) 
00000000 src/com/owncloud/android/ui/QuickAction.java (Not Committed Yet 2013-10-18 00:25:12 -0700 247)             mTrack.addView(view);
00000000 src/com/owncloud/android/ui/QuickAction.java (Not Committed Yet 2013-10-18 00:25:12 -0700 248)         }
00000000 src/com/owncloud/android/ui/QuickAction.java (Not Committed Yet 2013-10-18 00:25:12 -0700 249)     }
00000000 src/com/owncloud/android/ui/QuickAction.java (Not Committed Yet 2013-10-18 00:25:12 -0700 250) 
00000000 src/com/owncloud/android/ui/QuickAction.java (Not Committed Yet 2013-10-18 00:25:12 -0700 251)     /**
00000000 src/com/owncloud/android/ui/QuickAction.java (Not Committed Yet 2013-10-18 00:25:12 -0700 252)      * Get action item {@link View}
00000000 src/com/owncloud/android/ui/QuickAction.java (Not Committed Yet 2013-10-18 00:25:12 -0700 253)      * 
00000000 src/com/owncloud/android/ui/QuickAction.java (Not Committed Yet 2013-10-18 00:25:12 -0700 254)      * @param title action item title
00000000 src/com/owncloud/android/ui/QuickAction.java (Not Committed Yet 2013-10-18 00:25:12 -0700 255)      * @param icon {@link Drawable} action item icon
00000000 src/com/owncloud/android/ui/QuickAction.java (Not Committed Yet 2013-10-18 00:25:12 -0700 256)      * @param listener {@link View.OnClickListener} action item listener
00000000 src/com/owncloud/android/ui/QuickAction.java (Not Committed Yet 2013-10-18 00:25:12 -0700 257)      * @return action item {@link View}
00000000 src/com/owncloud/android/ui/QuickAction.java (Not Committed Yet 2013-10-18 00:25:12 -0700 258)      */
00000000 src/com/owncloud/android/ui/QuickAction.java (Not Committed Yet 2013-10-18 00:25:12 -0700 259)     private View getActionItem(String title, Drawable icon,
00000000 src/com/owncloud/android/ui/QuickAction.java (Not Committed Yet 2013-10-18 00:25:12 -0700 260)             OnClickListener listener) {
00000000 src/com/owncloud/android/ui/QuickAction.java (Not Committed Yet 2013-10-18 00:25:12 -0700 261)         LinearLayout container = (LinearLayout) inflater.inflate(
00000000 src/com/owncloud/android/ui/QuickAction.java (Not Committed Yet 2013-10-18 00:25:12 -0700 262)                 R.layout.action_item, null);
00000000 src/com/owncloud/android/ui/QuickAction.java (Not Committed Yet 2013-10-18 00:25:12 -0700 263) 
00000000 src/com/owncloud/android/ui/QuickAction.java (Not Committed Yet 2013-10-18 00:25:12 -0700 264)         ImageView img = (ImageView) container.findViewById(R.id.icon);
00000000 src/com/owncloud/android/ui/QuickAction.java (Not Committed Yet 2013-10-18 00:25:12 -0700 265)         TextView text = (TextView) container.findViewById(R.id.title);
00000000 src/com/owncloud/android/ui/QuickAction.java (Not Committed Yet 2013-10-18 00:25:12 -0700 266) 
00000000 src/com/owncloud/android/ui/QuickAction.java (Not Committed Yet 2013-10-18 00:25:12 -0700 267)         if (icon != null) {
00000000 src/com/owncloud/android/ui/QuickAction.java (Not Committed Yet 2013-10-18 00:25:12 -0700 268)             img.setImageDrawable(icon);
00000000 src/com/owncloud/android/ui/QuickAction.java (Not Committed Yet 2013-10-18 00:25:12 -0700 269)         }
00000000 src/com/owncloud/android/ui/QuickAction.java (Not Committed Yet 2013-10-18 00:25:12 -0700 270) 
00000000 src/com/owncloud/android/ui/QuickAction.java (Not Committed Yet 2013-10-18 00:25:12 -0700 271)         if (title != null) {
00000000 src/com/owncloud/android/ui/QuickAction.java (Not Committed Yet 2013-10-18 00:25:12 -0700 272)             text.setText(title);
00000000 src/com/owncloud/android/ui/QuickAction.java (Not Committed Yet 2013-10-18 00:25:12 -0700 273)         }
00000000 src/com/owncloud/android/ui/QuickAction.java (Not Committed Yet 2013-10-18 00:25:12 -0700 274) 
00000000 src/com/owncloud/android/ui/QuickAction.java (Not Committed Yet 2013-10-18 00:25:12 -0700 275)         if (listener != null) {
00000000 src/com/owncloud/android/ui/QuickAction.java (Not Committed Yet 2013-10-18 00:25:12 -0700 276)             container.setOnClickListener(listener);
00000000 src/com/owncloud/android/ui/QuickAction.java (Not Committed Yet 2013-10-18 00:25:12 -0700 277)         }
00000000 src/com/owncloud/android/ui/QuickAction.java (Not Committed Yet 2013-10-18 00:25:12 -0700 278) 
00000000 src/com/owncloud/android/ui/QuickAction.java (Not Committed Yet 2013-10-18 00:25:12 -0700 279)         return container;
00000000 src/com/owncloud/android/ui/QuickAction.java (Not Committed Yet 2013-10-18 00:25:12 -0700 280)     }
00000000 src/com/owncloud/android/ui/QuickAction.java (Not Committed Yet 2013-10-18 00:25:12 -0700 281) 
00000000 src/com/owncloud/android/ui/QuickAction.java (Not Committed Yet 2013-10-18 00:25:12 -0700 282)     /**
00000000 src/com/owncloud/android/ui/QuickAction.java (Not Committed Yet 2013-10-18 00:25:12 -0700 283)      * Show arrow
00000000 src/com/owncloud/android/ui/QuickAction.java (Not Committed Yet 2013-10-18 00:25:12 -0700 284)      * 
00000000 src/com/owncloud/android/ui/QuickAction.java (Not Committed Yet 2013-10-18 00:25:12 -0700 285)      * @param whichArrow arrow type resource id
00000000 src/com/owncloud/android/ui/QuickAction.java (Not Committed Yet 2013-10-18 00:25:12 -0700 286)      * @param requestedX distance from left screen
00000000 src/com/owncloud/android/ui/QuickAction.java (Not Committed Yet 2013-10-18 00:25:12 -0700 287)      */
00000000 src/com/owncloud/android/ui/QuickAction.java (Not Committed Yet 2013-10-18 00:25:12 -0700 288)     private void showArrow(int whichArrow, int requestedX) {
00000000 src/com/owncloud/android/ui/QuickAction.java (Not Committed Yet 2013-10-18 00:25:12 -0700 289)         final View showArrow = (whichArrow == R.id.arrow_up) ? mArrowUp
00000000 src/com/owncloud/android/ui/QuickAction.java (Not Committed Yet 2013-10-18 00:25:12 -0700 290)                 : mArrowDown;
00000000 src/com/owncloud/android/ui/QuickAction.java (Not Committed Yet 2013-10-18 00:25:12 -0700 291)         final View hideArrow = (whichArrow == R.id.arrow_up) ? mArrowDown
00000000 src/com/owncloud/android/ui/QuickAction.java (Not Committed Yet 2013-10-18 00:25:12 -0700 292)                 : mArrowUp;
00000000 src/com/owncloud/android/ui/QuickAction.java (Not Committed Yet 2013-10-18 00:25:12 -0700 293) 
00000000 src/com/owncloud/android/ui/QuickAction.java (Not Committed Yet 2013-10-18 00:25:12 -0700 294)         final int arrowWidth = mArrowUp.getMeasuredWidth();
00000000 src/com/owncloud/android/ui/QuickAction.java (Not Committed Yet 2013-10-18 00:25:12 -0700 295) 
00000000 src/com/owncloud/android/ui/QuickAction.java (Not Committed Yet 2013-10-18 00:25:12 -0700 296)         showArrow.setVisibility(View.VISIBLE);
00000000 src/com/owncloud/android/ui/QuickAction.java (Not Committed Yet 2013-10-18 00:25:12 -0700 297) 
00000000 src/com/owncloud/android/ui/QuickAction.java (Not Committed Yet 2013-10-18 00:25:12 -0700 298)         ViewGroup.MarginLayoutParams param = (ViewGroup.MarginLayoutParams) showArrow
00000000 src/com/owncloud/android/ui/QuickAction.java (Not Committed Yet 2013-10-18 00:25:12 -0700 299)                 .getLayoutParams();
00000000 src/com/owncloud/android/ui/QuickAction.java (Not Committed Yet 2013-10-18 00:25:12 -0700 300) 
00000000 src/com/owncloud/android/ui/QuickAction.java (Not Committed Yet 2013-10-18 00:25:12 -0700 301)         param.leftMargin = requestedX - arrowWidth / 2;
00000000 src/com/owncloud/android/ui/QuickAction.java (Not Committed Yet 2013-10-18 00:25:12 -0700 302) 
00000000 src/com/owncloud/android/ui/QuickAction.java (Not Committed Yet 2013-10-18 00:25:12 -0700 303)         hideArrow.setVisibility(View.INVISIBLE);
00000000 src/com/owncloud/android/ui/QuickAction.java (Not Committed Yet 2013-10-18 00:25:12 -0700 304)     }
^154bb85 src/eu/alefzero/owncloud/QuickAction.java    (Bartek Przybylski 2011-08-19 22:37:35 +0200 305) }
