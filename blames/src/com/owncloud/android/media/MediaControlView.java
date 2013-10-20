d68b3246 (David A. Velasco 2013-03-08 13:32:10 +0100   1) /* ownCloud Android client application
d68b3246 (David A. Velasco 2013-03-08 13:32:10 +0100   2)  * 
d68b3246 (David A. Velasco 2013-03-08 13:32:10 +0100   3)  *   Copyright (C) 2012-2013  ownCloud Inc.
d68b3246 (David A. Velasco 2013-03-08 13:32:10 +0100   4)  *
d68b3246 (David A. Velasco 2013-03-08 13:32:10 +0100   5)  *   This program is free software: you can redistribute it and/or modify
bdc0332c (David A. Velasco 2013-04-17 12:26:13 +0200   6)  *   it under the terms of the GNU General Public License version 2,
bdc0332c (David A. Velasco 2013-04-17 12:26:13 +0200   7)  *   as published by the Free Software Foundation.
d68b3246 (David A. Velasco 2013-03-08 13:32:10 +0100   8)  *
d68b3246 (David A. Velasco 2013-03-08 13:32:10 +0100   9)  *   This program is distributed in the hope that it will be useful,
d68b3246 (David A. Velasco 2013-03-08 13:32:10 +0100  10)  *   but WITHOUT ANY WARRANTY; without even the implied warranty of
d68b3246 (David A. Velasco 2013-03-08 13:32:10 +0100  11)  *   MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
d68b3246 (David A. Velasco 2013-03-08 13:32:10 +0100  12)  *   GNU General Public License for more details.
d68b3246 (David A. Velasco 2013-03-08 13:32:10 +0100  13)  *
d68b3246 (David A. Velasco 2013-03-08 13:32:10 +0100  14)  *   You should have received a copy of the GNU General Public License
d68b3246 (David A. Velasco 2013-03-08 13:32:10 +0100  15)  *   along with this program.  If not, see <http://www.gnu.org/licenses/>.
d68b3246 (David A. Velasco 2013-03-08 13:32:10 +0100  16)  *
d68b3246 (David A. Velasco 2013-03-08 13:32:10 +0100  17)  */
d68b3246 (David A. Velasco 2013-03-08 13:32:10 +0100  18) package com.owncloud.android.media;
d68b3246 (David A. Velasco 2013-03-08 13:32:10 +0100  19) 
d68b3246 (David A. Velasco 2013-03-08 13:32:10 +0100  20) import android.content.Context;
d68b3246 (David A. Velasco 2013-03-08 13:32:10 +0100  21) import android.media.MediaPlayer;
d68b3246 (David A. Velasco 2013-03-08 13:32:10 +0100  22) import android.os.Handler;
d68b3246 (David A. Velasco 2013-03-08 13:32:10 +0100  23) import android.os.Message;
d68b3246 (David A. Velasco 2013-03-08 13:32:10 +0100  24) import android.util.AttributeSet;
d68b3246 (David A. Velasco 2013-03-08 13:32:10 +0100  25) import android.view.KeyEvent;
d68b3246 (David A. Velasco 2013-03-08 13:32:10 +0100  26) import android.view.LayoutInflater;
d68b3246 (David A. Velasco 2013-03-08 13:32:10 +0100  27) import android.view.View;
ca8a1985 (David A. Velasco 2013-03-11 10:20:04 +0100  28) import android.view.View.OnClickListener;
d68b3246 (David A. Velasco 2013-03-08 13:32:10 +0100  29) import android.view.ViewGroup;
d68b3246 (David A. Velasco 2013-03-08 13:32:10 +0100  30) import android.view.accessibility.AccessibilityEvent;
d68b3246 (David A. Velasco 2013-03-08 13:32:10 +0100  31) import android.view.accessibility.AccessibilityNodeInfo;
d68b3246 (David A. Velasco 2013-03-08 13:32:10 +0100  32) import android.widget.FrameLayout;
d68b3246 (David A. Velasco 2013-03-08 13:32:10 +0100  33) import android.widget.ImageButton;
d68b3246 (David A. Velasco 2013-03-08 13:32:10 +0100  34) import android.widget.MediaController.MediaPlayerControl;
d68b3246 (David A. Velasco 2013-03-08 13:32:10 +0100  35) import android.widget.ProgressBar;
d68b3246 (David A. Velasco 2013-03-08 13:32:10 +0100  36) import android.widget.SeekBar;
d68b3246 (David A. Velasco 2013-03-08 13:32:10 +0100  37) import android.widget.SeekBar.OnSeekBarChangeListener;
d68b3246 (David A. Velasco 2013-03-08 13:32:10 +0100  38) import android.widget.TextView;
d68b3246 (David A. Velasco 2013-03-08 13:32:10 +0100  39) 
d68b3246 (David A. Velasco 2013-03-08 13:32:10 +0100  40) import java.util.Formatter;
d68b3246 (David A. Velasco 2013-03-08 13:32:10 +0100  41) import java.util.Locale;
d68b3246 (David A. Velasco 2013-03-08 13:32:10 +0100  42) 
d68b3246 (David A. Velasco 2013-03-08 13:32:10 +0100  43) import com.owncloud.android.R;
d68b3246 (David A. Velasco 2013-03-08 13:32:10 +0100  44) 
d68b3246 (David A. Velasco 2013-03-08 13:32:10 +0100  45) /**
d68b3246 (David A. Velasco 2013-03-08 13:32:10 +0100  46)  * View containing controls for a {@link MediaPlayer}. 
d68b3246 (David A. Velasco 2013-03-08 13:32:10 +0100  47)  * 
d68b3246 (David A. Velasco 2013-03-08 13:32:10 +0100  48)  * Holds buttons "play / pause", "rewind", "fast forward" 
d68b3246 (David A. Velasco 2013-03-08 13:32:10 +0100  49)  * and a progress slider. 
d68b3246 (David A. Velasco 2013-03-08 13:32:10 +0100  50)  * 
d68b3246 (David A. Velasco 2013-03-08 13:32:10 +0100  51)  * It synchronizes itself with the state of the 
d68b3246 (David A. Velasco 2013-03-08 13:32:10 +0100  52)  * {@link MediaPlayer}.
d68b3246 (David A. Velasco 2013-03-08 13:32:10 +0100  53)  * 
d68b3246 (David A. Velasco 2013-03-08 13:32:10 +0100  54)  * @author David A. Velasco
d68b3246 (David A. Velasco 2013-03-08 13:32:10 +0100  55)  */
d68b3246 (David A. Velasco 2013-03-08 13:32:10 +0100  56) 
ca8a1985 (David A. Velasco 2013-03-11 10:20:04 +0100  57) public class MediaControlView extends FrameLayout /* implements OnLayoutChangeListener, OnTouchListener */ implements OnClickListener, OnSeekBarChangeListener {
d68b3246 (David A. Velasco 2013-03-08 13:32:10 +0100  58) 
d68b3246 (David A. Velasco 2013-03-08 13:32:10 +0100  59)     private MediaPlayerControl  mPlayer;
d68b3246 (David A. Velasco 2013-03-08 13:32:10 +0100  60)     private Context             mContext;
d68b3246 (David A. Velasco 2013-03-08 13:32:10 +0100  61)     private View                mRoot;
d68b3246 (David A. Velasco 2013-03-08 13:32:10 +0100  62)     private ProgressBar         mProgress;
d68b3246 (David A. Velasco 2013-03-08 13:32:10 +0100  63)     private TextView            mEndTime, mCurrentTime;
d68b3246 (David A. Velasco 2013-03-08 13:32:10 +0100  64)     private boolean             mDragging;
ca8a1985 (David A. Velasco 2013-03-11 10:20:04 +0100  65)     private static final int    SHOW_PROGRESS = 1;
d68b3246 (David A. Velasco 2013-03-08 13:32:10 +0100  66)     StringBuilder               mFormatBuilder;
d68b3246 (David A. Velasco 2013-03-08 13:32:10 +0100  67)     Formatter                   mFormatter;
d68b3246 (David A. Velasco 2013-03-08 13:32:10 +0100  68)     private ImageButton         mPauseButton;
d68b3246 (David A. Velasco 2013-03-08 13:32:10 +0100  69)     private ImageButton         mFfwdButton;
d68b3246 (David A. Velasco 2013-03-08 13:32:10 +0100  70)     private ImageButton         mRewButton;
d68b3246 (David A. Velasco 2013-03-08 13:32:10 +0100  71)     
d68b3246 (David A. Velasco 2013-03-08 13:32:10 +0100  72)     public MediaControlView(Context context, AttributeSet attrs) {
d68b3246 (David A. Velasco 2013-03-08 13:32:10 +0100  73)         super(context, attrs);
d68b3246 (David A. Velasco 2013-03-08 13:32:10 +0100  74)         mContext = context;
d68b3246 (David A. Velasco 2013-03-08 13:32:10 +0100  75)         
d68b3246 (David A. Velasco 2013-03-08 13:32:10 +0100  76)         FrameLayout.LayoutParams frameParams = new FrameLayout.LayoutParams(
d68b3246 (David A. Velasco 2013-03-08 13:32:10 +0100  77)                 ViewGroup.LayoutParams.MATCH_PARENT,
d68b3246 (David A. Velasco 2013-03-08 13:32:10 +0100  78)                 ViewGroup.LayoutParams.MATCH_PARENT
d68b3246 (David A. Velasco 2013-03-08 13:32:10 +0100  79)         );
d68b3246 (David A. Velasco 2013-03-08 13:32:10 +0100  80)         LayoutInflater inflate = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
d68b3246 (David A. Velasco 2013-03-08 13:32:10 +0100  81)         mRoot = inflate.inflate(R.layout.media_control, null);
d68b3246 (David A. Velasco 2013-03-08 13:32:10 +0100  82)         initControllerView(mRoot);
d68b3246 (David A. Velasco 2013-03-08 13:32:10 +0100  83)         addView(mRoot, frameParams);
d68b3246 (David A. Velasco 2013-03-08 13:32:10 +0100  84)         
d68b3246 (David A. Velasco 2013-03-08 13:32:10 +0100  85)         setFocusable(true);
d68b3246 (David A. Velasco 2013-03-08 13:32:10 +0100  86)         setFocusableInTouchMode(true);
d68b3246 (David A. Velasco 2013-03-08 13:32:10 +0100  87)         setDescendantFocusability(ViewGroup.FOCUS_AFTER_DESCENDANTS);
d68b3246 (David A. Velasco 2013-03-08 13:32:10 +0100  88)         requestFocus();
d68b3246 (David A. Velasco 2013-03-08 13:32:10 +0100  89)     }
d68b3246 (David A. Velasco 2013-03-08 13:32:10 +0100  90) 
d68b3246 (David A. Velasco 2013-03-08 13:32:10 +0100  91)     @Override
d68b3246 (David A. Velasco 2013-03-08 13:32:10 +0100  92)     public void onFinishInflate() {
ca8a1985 (David A. Velasco 2013-03-11 10:20:04 +0100  93)         /*
d68b3246 (David A. Velasco 2013-03-08 13:32:10 +0100  94)         if (mRoot != null)
d68b3246 (David A. Velasco 2013-03-08 13:32:10 +0100  95)             initControllerView(mRoot);
ca8a1985 (David A. Velasco 2013-03-11 10:20:04 +0100  96)          */
d68b3246 (David A. Velasco 2013-03-08 13:32:10 +0100  97)     }
d68b3246 (David A. Velasco 2013-03-08 13:32:10 +0100  98) 
d68b3246 (David A. Velasco 2013-03-08 13:32:10 +0100  99)     /* TODO REMOVE
d68b3246 (David A. Velasco 2013-03-08 13:32:10 +0100 100)     public MediaControlView(Context context, boolean useFastForward) {
d68b3246 (David A. Velasco 2013-03-08 13:32:10 +0100 101)         super(context);
d68b3246 (David A. Velasco 2013-03-08 13:32:10 +0100 102)         mContext = context;
d68b3246 (David A. Velasco 2013-03-08 13:32:10 +0100 103)         mUseFastForward = useFastForward;
d68b3246 (David A. Velasco 2013-03-08 13:32:10 +0100 104)         initFloatingWindowLayout();
d68b3246 (David A. Velasco 2013-03-08 13:32:10 +0100 105)         //initFloatingWindow();
d68b3246 (David A. Velasco 2013-03-08 13:32:10 +0100 106)     }
d68b3246 (David A. Velasco 2013-03-08 13:32:10 +0100 107)     */
d68b3246 (David A. Velasco 2013-03-08 13:32:10 +0100 108) 
d68b3246 (David A. Velasco 2013-03-08 13:32:10 +0100 109)     /* TODO REMOVE
d68b3246 (David A. Velasco 2013-03-08 13:32:10 +0100 110)     public MediaControlView(Context context) {
d68b3246 (David A. Velasco 2013-03-08 13:32:10 +0100 111)         this(context, true);
d68b3246 (David A. Velasco 2013-03-08 13:32:10 +0100 112)     }
d68b3246 (David A. Velasco 2013-03-08 13:32:10 +0100 113)     */
d68b3246 (David A. Velasco 2013-03-08 13:32:10 +0100 114)     
d68b3246 (David A. Velasco 2013-03-08 13:32:10 +0100 115)     /* T
d68b3246 (David A. Velasco 2013-03-08 13:32:10 +0100 116)     private void initFloatingWindow() {
d68b3246 (David A. Velasco 2013-03-08 13:32:10 +0100 117)         mWindowManager = (WindowManager)mContext.getSystemService(Context.WINDOW_SERVICE);
d68b3246 (David A. Velasco 2013-03-08 13:32:10 +0100 118)         mWindow = PolicyManager.makeNewWindow(mContext);
d68b3246 (David A. Velasco 2013-03-08 13:32:10 +0100 119)         mWindow.setWindowManager(mWindowManager, null, null);
d68b3246 (David A. Velasco 2013-03-08 13:32:10 +0100 120)         mWindow.requestFeature(Window.FEATURE_NO_TITLE);
d68b3246 (David A. Velasco 2013-03-08 13:32:10 +0100 121)         mDecor = mWindow.getDecorView();
d68b3246 (David A. Velasco 2013-03-08 13:32:10 +0100 122)         mDecor.setOnTouchListener(mTouchListener);
d68b3246 (David A. Velasco 2013-03-08 13:32:10 +0100 123)         mWindow.setContentView(this);
d68b3246 (David A. Velasco 2013-03-08 13:32:10 +0100 124)         mWindow.setBackgroundDrawableResource(android.R.color.transparent);
d68b3246 (David A. Velasco 2013-03-08 13:32:10 +0100 125)         
d68b3246 (David A. Velasco 2013-03-08 13:32:10 +0100 126)         // While the media controller is up, the volume control keys should
d68b3246 (David A. Velasco 2013-03-08 13:32:10 +0100 127)         // affect the media stream type
d68b3246 (David A. Velasco 2013-03-08 13:32:10 +0100 128)         mWindow.setVolumeControlStream(AudioManager.STREAM_MUSIC);
d68b3246 (David A. Velasco 2013-03-08 13:32:10 +0100 129) 
d68b3246 (David A. Velasco 2013-03-08 13:32:10 +0100 130)         setFocusable(true);
d68b3246 (David A. Velasco 2013-03-08 13:32:10 +0100 131)         setFocusableInTouchMode(true);
d68b3246 (David A. Velasco 2013-03-08 13:32:10 +0100 132)         setDescendantFocusability(ViewGroup.FOCUS_AFTER_DESCENDANTS);
d68b3246 (David A. Velasco 2013-03-08 13:32:10 +0100 133)         requestFocus();
d68b3246 (David A. Velasco 2013-03-08 13:32:10 +0100 134)     }
d68b3246 (David A. Velasco 2013-03-08 13:32:10 +0100 135)     */
d68b3246 (David A. Velasco 2013-03-08 13:32:10 +0100 136) 
d68b3246 (David A. Velasco 2013-03-08 13:32:10 +0100 137)     /*
d68b3246 (David A. Velasco 2013-03-08 13:32:10 +0100 138)     // Allocate and initialize the static parts of mDecorLayoutParams. Must
d68b3246 (David A. Velasco 2013-03-08 13:32:10 +0100 139)     // also call updateFloatingWindowLayout() to fill in the dynamic parts
d68b3246 (David A. Velasco 2013-03-08 13:32:10 +0100 140)     // (y and width) before mDecorLayoutParams can be used.
d68b3246 (David A. Velasco 2013-03-08 13:32:10 +0100 141)     private void initFloatingWindowLayout() {
d68b3246 (David A. Velasco 2013-03-08 13:32:10 +0100 142)         mDecorLayoutParams = new WindowManager.LayoutParams();
d68b3246 (David A. Velasco 2013-03-08 13:32:10 +0100 143)         WindowManager.LayoutParams p = mDecorLayoutParams;
d68b3246 (David A. Velasco 2013-03-08 13:32:10 +0100 144)         p.gravity = Gravity.TOP;
d68b3246 (David A. Velasco 2013-03-08 13:32:10 +0100 145)         p.height = LayoutParams.WRAP_CONTENT;
d68b3246 (David A. Velasco 2013-03-08 13:32:10 +0100 146)         p.x = 0;
d68b3246 (David A. Velasco 2013-03-08 13:32:10 +0100 147)         p.format = PixelFormat.TRANSLUCENT;
d68b3246 (David A. Velasco 2013-03-08 13:32:10 +0100 148)         p.type = WindowManager.LayoutParams.TYPE_APPLICATION_PANEL;
d68b3246 (David A. Velasco 2013-03-08 13:32:10 +0100 149)         p.flags |= WindowManager.LayoutParams.FLAG_ALT_FOCUSABLE_IM
d68b3246 (David A. Velasco 2013-03-08 13:32:10 +0100 150)                 | WindowManager.LayoutParams.FLAG_NOT_TOUCH_MODAL
d68b3246 (David A. Velasco 2013-03-08 13:32:10 +0100 151)                 | WindowManager.LayoutParams.FLAG_SPLIT_TOUCH;
d68b3246 (David A. Velasco 2013-03-08 13:32:10 +0100 152)         p.token = null;
d68b3246 (David A. Velasco 2013-03-08 13:32:10 +0100 153)         p.windowAnimations = 0; // android.R.style.DropDownAnimationDown;
d68b3246 (David A. Velasco 2013-03-08 13:32:10 +0100 154)     }
d68b3246 (David A. Velasco 2013-03-08 13:32:10 +0100 155)     */
d68b3246 (David A. Velasco 2013-03-08 13:32:10 +0100 156) 
d68b3246 (David A. Velasco 2013-03-08 13:32:10 +0100 157)     // Update the dynamic parts of mDecorLayoutParams
d68b3246 (David A. Velasco 2013-03-08 13:32:10 +0100 158)     // Must be called with mAnchor != NULL.
ca8a1985 (David A. Velasco 2013-03-11 10:20:04 +0100 159)     /*
d68b3246 (David A. Velasco 2013-03-08 13:32:10 +0100 160)     private void updateFloatingWindowLayout() {
d68b3246 (David A. Velasco 2013-03-08 13:32:10 +0100 161)         int [] anchorPos = new int[2];
d68b3246 (David A. Velasco 2013-03-08 13:32:10 +0100 162)         mAnchor.getLocationOnScreen(anchorPos);
d68b3246 (David A. Velasco 2013-03-08 13:32:10 +0100 163) 
d68b3246 (David A. Velasco 2013-03-08 13:32:10 +0100 164)         WindowManager.LayoutParams p = mDecorLayoutParams;
d68b3246 (David A. Velasco 2013-03-08 13:32:10 +0100 165)         p.width = mAnchor.getWidth();
d68b3246 (David A. Velasco 2013-03-08 13:32:10 +0100 166)         p.y = anchorPos[1] + mAnchor.getHeight();
d68b3246 (David A. Velasco 2013-03-08 13:32:10 +0100 167)     }
ca8a1985 (David A. Velasco 2013-03-11 10:20:04 +0100 168)     */
d68b3246 (David A. Velasco 2013-03-08 13:32:10 +0100 169) 
d68b3246 (David A. Velasco 2013-03-08 13:32:10 +0100 170)     /*
d68b3246 (David A. Velasco 2013-03-08 13:32:10 +0100 171)     // This is called whenever mAnchor's layout bound changes
d68b3246 (David A. Velasco 2013-03-08 13:32:10 +0100 172)     public void onLayoutChange(View v, int left, int top, int right,
d68b3246 (David A. Velasco 2013-03-08 13:32:10 +0100 173)             int bottom, int oldLeft, int oldTop, int oldRight,
d68b3246 (David A. Velasco 2013-03-08 13:32:10 +0100 174)             int oldBottom) {
d68b3246 (David A. Velasco 2013-03-08 13:32:10 +0100 175)         //updateFloatingWindowLayout();
d68b3246 (David A. Velasco 2013-03-08 13:32:10 +0100 176)         if (mShowing) {
d68b3246 (David A. Velasco 2013-03-08 13:32:10 +0100 177)             mWindowManager.updateViewLayout(mDecor, mDecorLayoutParams);
d68b3246 (David A. Velasco 2013-03-08 13:32:10 +0100 178)         }
d68b3246 (David A. Velasco 2013-03-08 13:32:10 +0100 179)     }
d68b3246 (David A. Velasco 2013-03-08 13:32:10 +0100 180)     */
d68b3246 (David A. Velasco 2013-03-08 13:32:10 +0100 181)     
ca8a1985 (David A. Velasco 2013-03-11 10:20:04 +0100 182)     /*
d68b3246 (David A. Velasco 2013-03-08 13:32:10 +0100 183)     public boolean onTouch(View v, MotionEvent event) {
d68b3246 (David A. Velasco 2013-03-08 13:32:10 +0100 184)         if (event.getAction() == MotionEvent.ACTION_DOWN) {
d68b3246 (David A. Velasco 2013-03-08 13:32:10 +0100 185)             if (mShowing) {
d68b3246 (David A. Velasco 2013-03-08 13:32:10 +0100 186)                 hide();
d68b3246 (David A. Velasco 2013-03-08 13:32:10 +0100 187)             }
d68b3246 (David A. Velasco 2013-03-08 13:32:10 +0100 188)         }
d68b3246 (David A. Velasco 2013-03-08 13:32:10 +0100 189)             return false;
d68b3246 (David A. Velasco 2013-03-08 13:32:10 +0100 190)     }
ca8a1985 (David A. Velasco 2013-03-11 10:20:04 +0100 191)     */
d68b3246 (David A. Velasco 2013-03-08 13:32:10 +0100 192)     
d68b3246 (David A. Velasco 2013-03-08 13:32:10 +0100 193)     
d68b3246 (David A. Velasco 2013-03-08 13:32:10 +0100 194)     public void setMediaPlayer(MediaPlayerControl player) {
d68b3246 (David A. Velasco 2013-03-08 13:32:10 +0100 195)         mPlayer = player;
ca8a1985 (David A. Velasco 2013-03-11 10:20:04 +0100 196)         mHandler.sendEmptyMessage(SHOW_PROGRESS);
d68b3246 (David A. Velasco 2013-03-08 13:32:10 +0100 197)         updatePausePlay();
d68b3246 (David A. Velasco 2013-03-08 13:32:10 +0100 198)     }
d68b3246 (David A. Velasco 2013-03-08 13:32:10 +0100 199) 
d68b3246 (David A. Velasco 2013-03-08 13:32:10 +0100 200)     
d68b3246 (David A. Velasco 2013-03-08 13:32:10 +0100 201)     private void initControllerView(View v) {
d68b3246 (David A. Velasco 2013-03-08 13:32:10 +0100 202)         mPauseButton = (ImageButton) v.findViewById(R.id.playBtn);
d68b3246 (David A. Velasco 2013-03-08 13:32:10 +0100 203)         if (mPauseButton != null) {
d68b3246 (David A. Velasco 2013-03-08 13:32:10 +0100 204)             mPauseButton.requestFocus();
ca8a1985 (David A. Velasco 2013-03-11 10:20:04 +0100 205)             mPauseButton.setOnClickListener(this);
d68b3246 (David A. Velasco 2013-03-08 13:32:10 +0100 206)         }
d68b3246 (David A. Velasco 2013-03-08 13:32:10 +0100 207) 
d68b3246 (David A. Velasco 2013-03-08 13:32:10 +0100 208)         mFfwdButton = (ImageButton) v.findViewById(R.id.forwardBtn);
d68b3246 (David A. Velasco 2013-03-08 13:32:10 +0100 209)         if (mFfwdButton != null) {
ca8a1985 (David A. Velasco 2013-03-11 10:20:04 +0100 210)             mFfwdButton.setOnClickListener(this);
d68b3246 (David A. Velasco 2013-03-08 13:32:10 +0100 211)         }
d68b3246 (David A. Velasco 2013-03-08 13:32:10 +0100 212) 
d68b3246 (David A. Velasco 2013-03-08 13:32:10 +0100 213)         mRewButton = (ImageButton) v.findViewById(R.id.rewindBtn);
d68b3246 (David A. Velasco 2013-03-08 13:32:10 +0100 214)         if (mRewButton != null) {
ca8a1985 (David A. Velasco 2013-03-11 10:20:04 +0100 215)             mRewButton.setOnClickListener(this);
d68b3246 (David A. Velasco 2013-03-08 13:32:10 +0100 216)         }
d68b3246 (David A. Velasco 2013-03-08 13:32:10 +0100 217) 
d68b3246 (David A. Velasco 2013-03-08 13:32:10 +0100 218)         mProgress = (ProgressBar) v.findViewById(R.id.progressBar);
d68b3246 (David A. Velasco 2013-03-08 13:32:10 +0100 219)         if (mProgress != null) {
d68b3246 (David A. Velasco 2013-03-08 13:32:10 +0100 220)             if (mProgress instanceof SeekBar) {
d68b3246 (David A. Velasco 2013-03-08 13:32:10 +0100 221)                 SeekBar seeker = (SeekBar) mProgress;
ca8a1985 (David A. Velasco 2013-03-11 10:20:04 +0100 222)                 seeker.setOnSeekBarChangeListener(this);
d68b3246 (David A. Velasco 2013-03-08 13:32:10 +0100 223)             }
d68b3246 (David A. Velasco 2013-03-08 13:32:10 +0100 224)             mProgress.setMax(1000);
d68b3246 (David A. Velasco 2013-03-08 13:32:10 +0100 225)         }
d68b3246 (David A. Velasco 2013-03-08 13:32:10 +0100 226) 
d68b3246 (David A. Velasco 2013-03-08 13:32:10 +0100 227)         mEndTime = (TextView) v.findViewById(R.id.totalTimeText);
d68b3246 (David A. Velasco 2013-03-08 13:32:10 +0100 228)         mCurrentTime = (TextView) v.findViewById(R.id.currentTimeText);
d68b3246 (David A. Velasco 2013-03-08 13:32:10 +0100 229)         mFormatBuilder = new StringBuilder();
d68b3246 (David A. Velasco 2013-03-08 13:32:10 +0100 230)         mFormatter = new Formatter(mFormatBuilder, Locale.getDefault());
d68b3246 (David A. Velasco 2013-03-08 13:32:10 +0100 231) 
d68b3246 (David A. Velasco 2013-03-08 13:32:10 +0100 232)     }
d68b3246 (David A. Velasco 2013-03-08 13:32:10 +0100 233) 
ca8a1985 (David A. Velasco 2013-03-11 10:20:04 +0100 234)     
d68b3246 (David A. Velasco 2013-03-08 13:32:10 +0100 235)     /**
d68b3246 (David A. Velasco 2013-03-08 13:32:10 +0100 236)      * Disable pause or seek buttons if the stream cannot be paused or seeked.
d68b3246 (David A. Velasco 2013-03-08 13:32:10 +0100 237)      * This requires the control interface to be a MediaPlayerControlExt
d68b3246 (David A. Velasco 2013-03-08 13:32:10 +0100 238)      */
d68b3246 (David A. Velasco 2013-03-08 13:32:10 +0100 239)     private void disableUnsupportedButtons() {
d68b3246 (David A. Velasco 2013-03-08 13:32:10 +0100 240)         try {
d68b3246 (David A. Velasco 2013-03-08 13:32:10 +0100 241)             if (mPauseButton != null && !mPlayer.canPause()) {
d68b3246 (David A. Velasco 2013-03-08 13:32:10 +0100 242)                 mPauseButton.setEnabled(false);
d68b3246 (David A. Velasco 2013-03-08 13:32:10 +0100 243)             }
d68b3246 (David A. Velasco 2013-03-08 13:32:10 +0100 244)             if (mRewButton != null && !mPlayer.canSeekBackward()) {
d68b3246 (David A. Velasco 2013-03-08 13:32:10 +0100 245)                 mRewButton.setEnabled(false);
d68b3246 (David A. Velasco 2013-03-08 13:32:10 +0100 246)             }
d68b3246 (David A. Velasco 2013-03-08 13:32:10 +0100 247)             if (mFfwdButton != null && !mPlayer.canSeekForward()) {
d68b3246 (David A. Velasco 2013-03-08 13:32:10 +0100 248)                 mFfwdButton.setEnabled(false);
d68b3246 (David A. Velasco 2013-03-08 13:32:10 +0100 249)             }
d68b3246 (David A. Velasco 2013-03-08 13:32:10 +0100 250)         } catch (IncompatibleClassChangeError ex) {
d68b3246 (David A. Velasco 2013-03-08 13:32:10 +0100 251)             // We were given an old version of the interface, that doesn't have
d68b3246 (David A. Velasco 2013-03-08 13:32:10 +0100 252)             // the canPause/canSeekXYZ methods. This is OK, it just means we
d68b3246 (David A. Velasco 2013-03-08 13:32:10 +0100 253)             // assume the media can be paused and seeked, and so we don't disable
d68b3246 (David A. Velasco 2013-03-08 13:32:10 +0100 254)             // the buttons.
d68b3246 (David A. Velasco 2013-03-08 13:32:10 +0100 255)         }
d68b3246 (David A. Velasco 2013-03-08 13:32:10 +0100 256)     }
d68b3246 (David A. Velasco 2013-03-08 13:32:10 +0100 257)     
d68b3246 (David A. Velasco 2013-03-08 13:32:10 +0100 258)     
d68b3246 (David A. Velasco 2013-03-08 13:32:10 +0100 259)     private Handler mHandler = new Handler() {
d68b3246 (David A. Velasco 2013-03-08 13:32:10 +0100 260)         @Override
d68b3246 (David A. Velasco 2013-03-08 13:32:10 +0100 261)         public void handleMessage(Message msg) {
d68b3246 (David A. Velasco 2013-03-08 13:32:10 +0100 262)             int pos;
d68b3246 (David A. Velasco 2013-03-08 13:32:10 +0100 263)             switch (msg.what) {
d68b3246 (David A. Velasco 2013-03-08 13:32:10 +0100 264)                 case SHOW_PROGRESS:
d68b3246 (David A. Velasco 2013-03-08 13:32:10 +0100 265)                     pos = setProgress();
ca8a1985 (David A. Velasco 2013-03-11 10:20:04 +0100 266)                     if (!mDragging) {
d68b3246 (David A. Velasco 2013-03-08 13:32:10 +0100 267)                         msg = obtainMessage(SHOW_PROGRESS);
d68b3246 (David A. Velasco 2013-03-08 13:32:10 +0100 268)                         sendMessageDelayed(msg, 1000 - (pos % 1000));
d68b3246 (David A. Velasco 2013-03-08 13:32:10 +0100 269)                     }
d68b3246 (David A. Velasco 2013-03-08 13:32:10 +0100 270)                     break;
d68b3246 (David A. Velasco 2013-03-08 13:32:10 +0100 271)             }
d68b3246 (David A. Velasco 2013-03-08 13:32:10 +0100 272)         }
d68b3246 (David A. Velasco 2013-03-08 13:32:10 +0100 273)     };
d68b3246 (David A. Velasco 2013-03-08 13:32:10 +0100 274) 
d68b3246 (David A. Velasco 2013-03-08 13:32:10 +0100 275)     private String stringForTime(int timeMs) {
d68b3246 (David A. Velasco 2013-03-08 13:32:10 +0100 276)         int totalSeconds = timeMs / 1000;
d68b3246 (David A. Velasco 2013-03-08 13:32:10 +0100 277) 
d68b3246 (David A. Velasco 2013-03-08 13:32:10 +0100 278)         int seconds = totalSeconds % 60;
d68b3246 (David A. Velasco 2013-03-08 13:32:10 +0100 279)         int minutes = (totalSeconds / 60) % 60;
d68b3246 (David A. Velasco 2013-03-08 13:32:10 +0100 280)         int hours   = totalSeconds / 3600;
d68b3246 (David A. Velasco 2013-03-08 13:32:10 +0100 281) 
d68b3246 (David A. Velasco 2013-03-08 13:32:10 +0100 282)         mFormatBuilder.setLength(0);
d68b3246 (David A. Velasco 2013-03-08 13:32:10 +0100 283)         if (hours > 0) {
d68b3246 (David A. Velasco 2013-03-08 13:32:10 +0100 284)             return mFormatter.format("%d:%02d:%02d", hours, minutes, seconds).toString();
d68b3246 (David A. Velasco 2013-03-08 13:32:10 +0100 285)         } else {
d68b3246 (David A. Velasco 2013-03-08 13:32:10 +0100 286)             return mFormatter.format("%02d:%02d", minutes, seconds).toString();
d68b3246 (David A. Velasco 2013-03-08 13:32:10 +0100 287)         }
d68b3246 (David A. Velasco 2013-03-08 13:32:10 +0100 288)     }
d68b3246 (David A. Velasco 2013-03-08 13:32:10 +0100 289) 
d68b3246 (David A. Velasco 2013-03-08 13:32:10 +0100 290)     private int setProgress() {
d68b3246 (David A. Velasco 2013-03-08 13:32:10 +0100 291)         if (mPlayer == null || mDragging) {
d68b3246 (David A. Velasco 2013-03-08 13:32:10 +0100 292)             return 0;
d68b3246 (David A. Velasco 2013-03-08 13:32:10 +0100 293)         }
d68b3246 (David A. Velasco 2013-03-08 13:32:10 +0100 294)         int position = mPlayer.getCurrentPosition();
d68b3246 (David A. Velasco 2013-03-08 13:32:10 +0100 295)         int duration = mPlayer.getDuration();
d68b3246 (David A. Velasco 2013-03-08 13:32:10 +0100 296)         if (mProgress != null) {
d68b3246 (David A. Velasco 2013-03-08 13:32:10 +0100 297)             if (duration > 0) {
d68b3246 (David A. Velasco 2013-03-08 13:32:10 +0100 298)                 // use long to avoid overflow
d68b3246 (David A. Velasco 2013-03-08 13:32:10 +0100 299)                 long pos = 1000L * position / duration;
d68b3246 (David A. Velasco 2013-03-08 13:32:10 +0100 300)                 mProgress.setProgress( (int) pos);
d68b3246 (David A. Velasco 2013-03-08 13:32:10 +0100 301)             }
d68b3246 (David A. Velasco 2013-03-08 13:32:10 +0100 302)             int percent = mPlayer.getBufferPercentage();
d68b3246 (David A. Velasco 2013-03-08 13:32:10 +0100 303)             mProgress.setSecondaryProgress(percent * 10);
d68b3246 (David A. Velasco 2013-03-08 13:32:10 +0100 304)         }
d68b3246 (David A. Velasco 2013-03-08 13:32:10 +0100 305) 
d68b3246 (David A. Velasco 2013-03-08 13:32:10 +0100 306)         if (mEndTime != null)
d68b3246 (David A. Velasco 2013-03-08 13:32:10 +0100 307)             mEndTime.setText(stringForTime(duration));
d68b3246 (David A. Velasco 2013-03-08 13:32:10 +0100 308)         if (mCurrentTime != null)
d68b3246 (David A. Velasco 2013-03-08 13:32:10 +0100 309)             mCurrentTime.setText(stringForTime(position));
d68b3246 (David A. Velasco 2013-03-08 13:32:10 +0100 310) 
d68b3246 (David A. Velasco 2013-03-08 13:32:10 +0100 311)         return position;
d68b3246 (David A. Velasco 2013-03-08 13:32:10 +0100 312)     }
ca8a1985 (David A. Velasco 2013-03-11 10:20:04 +0100 313)     
d68b3246 (David A. Velasco 2013-03-08 13:32:10 +0100 314) 
d68b3246 (David A. Velasco 2013-03-08 13:32:10 +0100 315)     @Override
d68b3246 (David A. Velasco 2013-03-08 13:32:10 +0100 316)     public boolean dispatchKeyEvent(KeyEvent event) {
d68b3246 (David A. Velasco 2013-03-08 13:32:10 +0100 317)         int keyCode = event.getKeyCode();
d68b3246 (David A. Velasco 2013-03-08 13:32:10 +0100 318)         final boolean uniqueDown = event.getRepeatCount() == 0
d68b3246 (David A. Velasco 2013-03-08 13:32:10 +0100 319)                 && event.getAction() == KeyEvent.ACTION_DOWN;
d68b3246 (David A. Velasco 2013-03-08 13:32:10 +0100 320)         if (keyCode ==  KeyEvent.KEYCODE_HEADSETHOOK
d68b3246 (David A. Velasco 2013-03-08 13:32:10 +0100 321)                 || keyCode == KeyEvent.KEYCODE_MEDIA_PLAY_PAUSE
d68b3246 (David A. Velasco 2013-03-08 13:32:10 +0100 322)                 || keyCode == KeyEvent.KEYCODE_SPACE) {
d68b3246 (David A. Velasco 2013-03-08 13:32:10 +0100 323)             if (uniqueDown) {
d68b3246 (David A. Velasco 2013-03-08 13:32:10 +0100 324)                 doPauseResume();
ca8a1985 (David A. Velasco 2013-03-11 10:20:04 +0100 325)                 //show(sDefaultTimeout);
d68b3246 (David A. Velasco 2013-03-08 13:32:10 +0100 326)                 if (mPauseButton != null) {
d68b3246 (David A. Velasco 2013-03-08 13:32:10 +0100 327)                     mPauseButton.requestFocus();
d68b3246 (David A. Velasco 2013-03-08 13:32:10 +0100 328)                 }
d68b3246 (David A. Velasco 2013-03-08 13:32:10 +0100 329)             }
d68b3246 (David A. Velasco 2013-03-08 13:32:10 +0100 330)             return true;
d68b3246 (David A. Velasco 2013-03-08 13:32:10 +0100 331)         } else if (keyCode == KeyEvent.KEYCODE_MEDIA_PLAY) {
d68b3246 (David A. Velasco 2013-03-08 13:32:10 +0100 332)             if (uniqueDown && !mPlayer.isPlaying()) {
d68b3246 (David A. Velasco 2013-03-08 13:32:10 +0100 333)                 mPlayer.start();
d68b3246 (David A. Velasco 2013-03-08 13:32:10 +0100 334)                 updatePausePlay();
ca8a1985 (David A. Velasco 2013-03-11 10:20:04 +0100 335)                 //show(sDefaultTimeout);
d68b3246 (David A. Velasco 2013-03-08 13:32:10 +0100 336)             }
d68b3246 (David A. Velasco 2013-03-08 13:32:10 +0100 337)             return true;
d68b3246 (David A. Velasco 2013-03-08 13:32:10 +0100 338)         } else if (keyCode == KeyEvent.KEYCODE_MEDIA_STOP
d68b3246 (David A. Velasco 2013-03-08 13:32:10 +0100 339)                 || keyCode == KeyEvent.KEYCODE_MEDIA_PAUSE) {
d68b3246 (David A. Velasco 2013-03-08 13:32:10 +0100 340)             if (uniqueDown && mPlayer.isPlaying()) {
d68b3246 (David A. Velasco 2013-03-08 13:32:10 +0100 341)                 mPlayer.pause();
d68b3246 (David A. Velasco 2013-03-08 13:32:10 +0100 342)                 updatePausePlay();
ca8a1985 (David A. Velasco 2013-03-11 10:20:04 +0100 343)                 //show(sDefaultTimeout);
d68b3246 (David A. Velasco 2013-03-08 13:32:10 +0100 344)             }
d68b3246 (David A. Velasco 2013-03-08 13:32:10 +0100 345)             return true;
d68b3246 (David A. Velasco 2013-03-08 13:32:10 +0100 346)         }
d68b3246 (David A. Velasco 2013-03-08 13:32:10 +0100 347) 
ca8a1985 (David A. Velasco 2013-03-11 10:20:04 +0100 348)         //show(sDefaultTimeout);
d68b3246 (David A. Velasco 2013-03-08 13:32:10 +0100 349)         return super.dispatchKeyEvent(event);
d68b3246 (David A. Velasco 2013-03-08 13:32:10 +0100 350)     }
d68b3246 (David A. Velasco 2013-03-08 13:32:10 +0100 351) 
ca8a1985 (David A. Velasco 2013-03-11 10:20:04 +0100 352)     public void updatePausePlay() {
d68b3246 (David A. Velasco 2013-03-08 13:32:10 +0100 353)         if (mRoot == null || mPauseButton == null)
d68b3246 (David A. Velasco 2013-03-08 13:32:10 +0100 354)             return;
d68b3246 (David A. Velasco 2013-03-08 13:32:10 +0100 355) 
d68b3246 (David A. Velasco 2013-03-08 13:32:10 +0100 356)         if (mPlayer.isPlaying()) {
d68b3246 (David A. Velasco 2013-03-08 13:32:10 +0100 357)             mPauseButton.setImageResource(android.R.drawable.ic_media_pause);
d68b3246 (David A. Velasco 2013-03-08 13:32:10 +0100 358)         } else {
d68b3246 (David A. Velasco 2013-03-08 13:32:10 +0100 359)             mPauseButton.setImageResource(android.R.drawable.ic_media_play);
d68b3246 (David A. Velasco 2013-03-08 13:32:10 +0100 360)         }
d68b3246 (David A. Velasco 2013-03-08 13:32:10 +0100 361)     }
d68b3246 (David A. Velasco 2013-03-08 13:32:10 +0100 362) 
d68b3246 (David A. Velasco 2013-03-08 13:32:10 +0100 363)     private void doPauseResume() {
d68b3246 (David A. Velasco 2013-03-08 13:32:10 +0100 364)         if (mPlayer.isPlaying()) {
d68b3246 (David A. Velasco 2013-03-08 13:32:10 +0100 365)             mPlayer.pause();
d68b3246 (David A. Velasco 2013-03-08 13:32:10 +0100 366)         } else {
d68b3246 (David A. Velasco 2013-03-08 13:32:10 +0100 367)             mPlayer.start();
d68b3246 (David A. Velasco 2013-03-08 13:32:10 +0100 368)         }
d68b3246 (David A. Velasco 2013-03-08 13:32:10 +0100 369)         updatePausePlay();
d68b3246 (David A. Velasco 2013-03-08 13:32:10 +0100 370)     }
d68b3246 (David A. Velasco 2013-03-08 13:32:10 +0100 371) 
d68b3246 (David A. Velasco 2013-03-08 13:32:10 +0100 372)     @Override
d68b3246 (David A. Velasco 2013-03-08 13:32:10 +0100 373)     public void setEnabled(boolean enabled) {
d68b3246 (David A. Velasco 2013-03-08 13:32:10 +0100 374)         if (mPauseButton != null) {
d68b3246 (David A. Velasco 2013-03-08 13:32:10 +0100 375)             mPauseButton.setEnabled(enabled);
d68b3246 (David A. Velasco 2013-03-08 13:32:10 +0100 376)         }
d68b3246 (David A. Velasco 2013-03-08 13:32:10 +0100 377)         if (mFfwdButton != null) {
d68b3246 (David A. Velasco 2013-03-08 13:32:10 +0100 378)             mFfwdButton.setEnabled(enabled);
d68b3246 (David A. Velasco 2013-03-08 13:32:10 +0100 379)         }
d68b3246 (David A. Velasco 2013-03-08 13:32:10 +0100 380)         if (mRewButton != null) {
d68b3246 (David A. Velasco 2013-03-08 13:32:10 +0100 381)             mRewButton.setEnabled(enabled);
d68b3246 (David A. Velasco 2013-03-08 13:32:10 +0100 382)         }
d68b3246 (David A. Velasco 2013-03-08 13:32:10 +0100 383)         if (mProgress != null) {
d68b3246 (David A. Velasco 2013-03-08 13:32:10 +0100 384)             mProgress.setEnabled(enabled);
d68b3246 (David A. Velasco 2013-03-08 13:32:10 +0100 385)         }
d68b3246 (David A. Velasco 2013-03-08 13:32:10 +0100 386)         disableUnsupportedButtons();
d68b3246 (David A. Velasco 2013-03-08 13:32:10 +0100 387)         super.setEnabled(enabled);
d68b3246 (David A. Velasco 2013-03-08 13:32:10 +0100 388)     }
d68b3246 (David A. Velasco 2013-03-08 13:32:10 +0100 389) 
d68b3246 (David A. Velasco 2013-03-08 13:32:10 +0100 390)     @Override
ca8a1985 (David A. Velasco 2013-03-11 10:20:04 +0100 391)     public void onClick(View v) {
ca8a1985 (David A. Velasco 2013-03-11 10:20:04 +0100 392)         int pos;
a11e7fd9 (David A. Velasco 2013-04-10 13:07:10 +0200 393)         boolean playing = mPlayer.isPlaying();
ca8a1985 (David A. Velasco 2013-03-11 10:20:04 +0100 394)         switch (v.getId()) {
ca8a1985 (David A. Velasco 2013-03-11 10:20:04 +0100 395)         
ca8a1985 (David A. Velasco 2013-03-11 10:20:04 +0100 396)         case R.id.playBtn: 
ca8a1985 (David A. Velasco 2013-03-11 10:20:04 +0100 397)             doPauseResume();
ca8a1985 (David A. Velasco 2013-03-11 10:20:04 +0100 398)             break;
d68b3246 (David A. Velasco 2013-03-08 13:32:10 +0100 399) 
ca8a1985 (David A. Velasco 2013-03-11 10:20:04 +0100 400)         case R.id.rewindBtn:
ca8a1985 (David A. Velasco 2013-03-11 10:20:04 +0100 401)             pos = mPlayer.getCurrentPosition();
ca8a1985 (David A. Velasco 2013-03-11 10:20:04 +0100 402)             pos -= 5000;
d68b3246 (David A. Velasco 2013-03-08 13:32:10 +0100 403)             mPlayer.seekTo(pos);
a11e7fd9 (David A. Velasco 2013-04-10 13:07:10 +0200 404)             if (!playing) mPlayer.pause();  // necessary in some 2.3.x devices 
d68b3246 (David A. Velasco 2013-03-08 13:32:10 +0100 405)             setProgress();
ca8a1985 (David A. Velasco 2013-03-11 10:20:04 +0100 406)             break;
d68b3246 (David A. Velasco 2013-03-08 13:32:10 +0100 407) 
ca8a1985 (David A. Velasco 2013-03-11 10:20:04 +0100 408)         case R.id.forwardBtn:
ca8a1985 (David A. Velasco 2013-03-11 10:20:04 +0100 409)             pos = mPlayer.getCurrentPosition();
ca8a1985 (David A. Velasco 2013-03-11 10:20:04 +0100 410)             pos += 15000;
d68b3246 (David A. Velasco 2013-03-08 13:32:10 +0100 411)             mPlayer.seekTo(pos);
a11e7fd9 (David A. Velasco 2013-04-10 13:07:10 +0200 412)             if (!playing) mPlayer.pause(); // necessary in some 2.3.x devices
d68b3246 (David A. Velasco 2013-03-08 13:32:10 +0100 413)             setProgress();
ca8a1985 (David A. Velasco 2013-03-11 10:20:04 +0100 414)             break;
ca8a1985 (David A. Velasco 2013-03-11 10:20:04 +0100 415)         
d68b3246 (David A. Velasco 2013-03-08 13:32:10 +0100 416)         }
ca8a1985 (David A. Velasco 2013-03-11 10:20:04 +0100 417)     }
ca8a1985 (David A. Velasco 2013-03-11 10:20:04 +0100 418)     
ca8a1985 (David A. Velasco 2013-03-11 10:20:04 +0100 419)     
ca8a1985 (David A. Velasco 2013-03-11 10:20:04 +0100 420)     @Override
ca8a1985 (David A. Velasco 2013-03-11 10:20:04 +0100 421)     public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
ca8a1985 (David A. Velasco 2013-03-11 10:20:04 +0100 422)         if (!fromUser) {
ca8a1985 (David A. Velasco 2013-03-11 10:20:04 +0100 423)             // We're not interested in programmatically generated changes to
ca8a1985 (David A. Velasco 2013-03-11 10:20:04 +0100 424)             // the progress bar's position.
ca8a1985 (David A. Velasco 2013-03-11 10:20:04 +0100 425)             return;
d68b3246 (David A. Velasco 2013-03-08 13:32:10 +0100 426)         }
d68b3246 (David A. Velasco 2013-03-08 13:32:10 +0100 427) 
ca8a1985 (David A. Velasco 2013-03-11 10:20:04 +0100 428)         long duration = mPlayer.getDuration();
ca8a1985 (David A. Velasco 2013-03-11 10:20:04 +0100 429)         long newposition = (duration * progress) / 1000L;
ca8a1985 (David A. Velasco 2013-03-11 10:20:04 +0100 430)         mPlayer.seekTo( (int) newposition);
ca8a1985 (David A. Velasco 2013-03-11 10:20:04 +0100 431)         if (mCurrentTime != null)
ca8a1985 (David A. Velasco 2013-03-11 10:20:04 +0100 432)             mCurrentTime.setText(stringForTime( (int) newposition));
ca8a1985 (David A. Velasco 2013-03-11 10:20:04 +0100 433)     }
ca8a1985 (David A. Velasco 2013-03-11 10:20:04 +0100 434)     
ca8a1985 (David A. Velasco 2013-03-11 10:20:04 +0100 435)     /**
ca8a1985 (David A. Velasco 2013-03-11 10:20:04 +0100 436)      * Called in devices with touchpad when the user starts to adjust the 
ca8a1985 (David A. Velasco 2013-03-11 10:20:04 +0100 437)      * position of the seekbar's thumb.
ca8a1985 (David A. Velasco 2013-03-11 10:20:04 +0100 438)      * 
ca8a1985 (David A. Velasco 2013-03-11 10:20:04 +0100 439)      * Will be followed by several onProgressChanged notifications.
ca8a1985 (David A. Velasco 2013-03-11 10:20:04 +0100 440)      */
ca8a1985 (David A. Velasco 2013-03-11 10:20:04 +0100 441)     @Override
ca8a1985 (David A. Velasco 2013-03-11 10:20:04 +0100 442)     public void onStartTrackingTouch(SeekBar seekBar) {
ca8a1985 (David A. Velasco 2013-03-11 10:20:04 +0100 443)         mDragging = true;                           // monitors the duration of dragging 
ca8a1985 (David A. Velasco 2013-03-11 10:20:04 +0100 444)         mHandler.removeMessages(SHOW_PROGRESS);     // grants no more updates with media player progress while dragging 
d68b3246 (David A. Velasco 2013-03-08 13:32:10 +0100 445)     }
d68b3246 (David A. Velasco 2013-03-08 13:32:10 +0100 446) 
ca8a1985 (David A. Velasco 2013-03-11 10:20:04 +0100 447)     
ca8a1985 (David A. Velasco 2013-03-11 10:20:04 +0100 448)     /**
ca8a1985 (David A. Velasco 2013-03-11 10:20:04 +0100 449)      * Called in devices with touchpad when the user finishes the
ca8a1985 (David A. Velasco 2013-03-11 10:20:04 +0100 450)      * adjusting of the seekbar.
ca8a1985 (David A. Velasco 2013-03-11 10:20:04 +0100 451)      */
ca8a1985 (David A. Velasco 2013-03-11 10:20:04 +0100 452)     @Override
ca8a1985 (David A. Velasco 2013-03-11 10:20:04 +0100 453)     public void onStopTrackingTouch(SeekBar seekBar) {
ca8a1985 (David A. Velasco 2013-03-11 10:20:04 +0100 454)         mDragging = false;
ca8a1985 (David A. Velasco 2013-03-11 10:20:04 +0100 455)         setProgress();
ca8a1985 (David A. Velasco 2013-03-11 10:20:04 +0100 456)         updatePausePlay();
ca8a1985 (David A. Velasco 2013-03-11 10:20:04 +0100 457)         mHandler.sendEmptyMessage(SHOW_PROGRESS);    // grants future updates with media player progress 
ca8a1985 (David A. Velasco 2013-03-11 10:20:04 +0100 458)     }
d68b3246 (David A. Velasco 2013-03-08 13:32:10 +0100 459) 
ca8a1985 (David A. Velasco 2013-03-11 10:20:04 +0100 460)     @Override
ca8a1985 (David A. Velasco 2013-03-11 10:20:04 +0100 461)     public void onInitializeAccessibilityEvent(AccessibilityEvent event) {
ca8a1985 (David A. Velasco 2013-03-11 10:20:04 +0100 462)         super.onInitializeAccessibilityEvent(event);
ca8a1985 (David A. Velasco 2013-03-11 10:20:04 +0100 463)         event.setClassName(MediaControlView.class.getName());
d68b3246 (David A. Velasco 2013-03-08 13:32:10 +0100 464)     }
d68b3246 (David A. Velasco 2013-03-08 13:32:10 +0100 465) 
ca8a1985 (David A. Velasco 2013-03-11 10:20:04 +0100 466)     @Override
ca8a1985 (David A. Velasco 2013-03-11 10:20:04 +0100 467)     public void onInitializeAccessibilityNodeInfo(AccessibilityNodeInfo info) {
ca8a1985 (David A. Velasco 2013-03-11 10:20:04 +0100 468)         super.onInitializeAccessibilityNodeInfo(info);
ca8a1985 (David A. Velasco 2013-03-11 10:20:04 +0100 469)         info.setClassName(MediaControlView.class.getName());
ca8a1985 (David A. Velasco 2013-03-11 10:20:04 +0100 470)     }
ca8a1985 (David A. Velasco 2013-03-11 10:20:04 +0100 471)     
d68b3246 (David A. Velasco 2013-03-08 13:32:10 +0100 472) }
