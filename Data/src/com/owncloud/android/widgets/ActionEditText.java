92080afe src/com/owncloud/android/widgets/ActionEditText.java (Bartek Przybylski 2012-10-28 00:12:15 +0200   1) /* ownCloud Android client application
92080afe src/com/owncloud/android/widgets/ActionEditText.java (Bartek Przybylski 2012-10-28 00:12:15 +0200   2)  *   Copyright (C) 2012 Bartek Przybylski
bb257ec7 src/com/owncloud/android/widgets/ActionEditText.java (David A. Velasco  2013-02-07 18:45:10 +0100   3)  *   Copyright (C) 2012-2013 ownCloud Inc.
92080afe src/com/owncloud/android/widgets/ActionEditText.java (Bartek Przybylski 2012-10-28 00:12:15 +0200   4)  *
92080afe src/com/owncloud/android/widgets/ActionEditText.java (Bartek Przybylski 2012-10-28 00:12:15 +0200   5)  *   This program is free software: you can redistribute it and/or modify
bdc0332c src/com/owncloud/android/widgets/ActionEditText.java (David A. Velasco  2013-04-17 12:26:13 +0200   6)  *   it under the terms of the GNU General Public License version 2,
bdc0332c src/com/owncloud/android/widgets/ActionEditText.java (David A. Velasco  2013-04-17 12:26:13 +0200   7)  *   as published by the Free Software Foundation.
92080afe src/com/owncloud/android/widgets/ActionEditText.java (Bartek Przybylski 2012-10-28 00:12:15 +0200   8)  *
92080afe src/com/owncloud/android/widgets/ActionEditText.java (Bartek Przybylski 2012-10-28 00:12:15 +0200   9)  *   This program is distributed in the hope that it will be useful,
92080afe src/com/owncloud/android/widgets/ActionEditText.java (Bartek Przybylski 2012-10-28 00:12:15 +0200  10)  *   but WITHOUT ANY WARRANTY; without even the implied warranty of
92080afe src/com/owncloud/android/widgets/ActionEditText.java (Bartek Przybylski 2012-10-28 00:12:15 +0200  11)  *   MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
92080afe src/com/owncloud/android/widgets/ActionEditText.java (Bartek Przybylski 2012-10-28 00:12:15 +0200  12)  *   GNU General Public License for more details.
92080afe src/com/owncloud/android/widgets/ActionEditText.java (Bartek Przybylski 2012-10-28 00:12:15 +0200  13)  *
92080afe src/com/owncloud/android/widgets/ActionEditText.java (Bartek Przybylski 2012-10-28 00:12:15 +0200  14)  *   You should have received a copy of the GNU General Public License
92080afe src/com/owncloud/android/widgets/ActionEditText.java (Bartek Przybylski 2012-10-28 00:12:15 +0200  15)  *   along with this program.  If not, see <http://www.gnu.org/licenses/>.
92080afe src/com/owncloud/android/widgets/ActionEditText.java (Bartek Przybylski 2012-10-28 00:12:15 +0200  16)  *
92080afe src/com/owncloud/android/widgets/ActionEditText.java (Bartek Przybylski 2012-10-28 00:12:15 +0200  17)  */
92080afe src/com/owncloud/android/widgets/ActionEditText.java (Bartek Przybylski 2012-10-28 00:12:15 +0200  18) 
a4ba6170 src/com/owncloud/android/widgets/ActionEditText.java (Bartek Przybylski 2012-07-31 17:43:37 +0200  19) package com.owncloud.android.widgets;
dc7cafe6 src/eu/alefzero/owncloud/widgets/ActionEditText.java (Bartek Przybylski 2012-05-01 23:37:37 +0200  20) 
dc7cafe6 src/eu/alefzero/owncloud/widgets/ActionEditText.java (Bartek Przybylski 2012-05-01 23:37:37 +0200  21) import java.lang.reflect.InvocationTargetException;
dc7cafe6 src/eu/alefzero/owncloud/widgets/ActionEditText.java (Bartek Przybylski 2012-05-01 23:37:37 +0200  22) import java.lang.reflect.Method;
dc7cafe6 src/eu/alefzero/owncloud/widgets/ActionEditText.java (Bartek Przybylski 2012-05-01 23:37:37 +0200  23) 
a4ba6170 src/com/owncloud/android/widgets/ActionEditText.java (Bartek Przybylski 2012-07-31 17:43:37 +0200  24) import com.owncloud.android.R;
dc7cafe6 src/eu/alefzero/owncloud/widgets/ActionEditText.java (Bartek Przybylski 2012-05-01 23:37:37 +0200  25) import android.content.Context;
dc7cafe6 src/eu/alefzero/owncloud/widgets/ActionEditText.java (Bartek Przybylski 2012-05-01 23:37:37 +0200  26) import android.content.res.TypedArray;
dc7cafe6 src/eu/alefzero/owncloud/widgets/ActionEditText.java (Bartek Przybylski 2012-05-01 23:37:37 +0200  27) import android.graphics.Canvas;
dc7cafe6 src/eu/alefzero/owncloud/widgets/ActionEditText.java (Bartek Przybylski 2012-05-01 23:37:37 +0200  28) import android.graphics.Color;
dc7cafe6 src/eu/alefzero/owncloud/widgets/ActionEditText.java (Bartek Przybylski 2012-05-01 23:37:37 +0200  29) import android.graphics.Paint;
dc7cafe6 src/eu/alefzero/owncloud/widgets/ActionEditText.java (Bartek Przybylski 2012-05-01 23:37:37 +0200  30) import android.graphics.Rect;
dc7cafe6 src/eu/alefzero/owncloud/widgets/ActionEditText.java (Bartek Przybylski 2012-05-01 23:37:37 +0200  31) import android.util.AttributeSet;
dc7cafe6 src/eu/alefzero/owncloud/widgets/ActionEditText.java (Bartek Przybylski 2012-05-01 23:37:37 +0200  32) import android.view.MotionEvent;
dc7cafe6 src/eu/alefzero/owncloud/widgets/ActionEditText.java (Bartek Przybylski 2012-05-01 23:37:37 +0200  33) import android.widget.EditText;
dc7cafe6 src/eu/alefzero/owncloud/widgets/ActionEditText.java (Bartek Przybylski 2012-05-01 23:37:37 +0200  34) 
dc7cafe6 src/eu/alefzero/owncloud/widgets/ActionEditText.java (Bartek Przybylski 2012-05-01 23:37:37 +0200  35) public class ActionEditText extends EditText {
435b31ba src/eu/alefzero/owncloud/widgets/ActionEditText.java (Lennart Rosam     2012-05-16 09:48:34 +0200  36)     private String s;
435b31ba src/eu/alefzero/owncloud/widgets/ActionEditText.java (Lennart Rosam     2012-05-16 09:48:34 +0200  37)     private String optionOneString;
435b31ba src/eu/alefzero/owncloud/widgets/ActionEditText.java (Lennart Rosam     2012-05-16 09:48:34 +0200  38)     private int optionOneColor;
435b31ba src/eu/alefzero/owncloud/widgets/ActionEditText.java (Lennart Rosam     2012-05-16 09:48:34 +0200  39)     private String optionTwoString;
435b31ba src/eu/alefzero/owncloud/widgets/ActionEditText.java (Lennart Rosam     2012-05-16 09:48:34 +0200  40)     private int optionTwoColor;
435b31ba src/eu/alefzero/owncloud/widgets/ActionEditText.java (Lennart Rosam     2012-05-16 09:48:34 +0200  41)     private Rect mTextBounds, mButtonRect;
435b31ba src/eu/alefzero/owncloud/widgets/ActionEditText.java (Lennart Rosam     2012-05-16 09:48:34 +0200  42) 
435b31ba src/eu/alefzero/owncloud/widgets/ActionEditText.java (Lennart Rosam     2012-05-16 09:48:34 +0200  43)     private String badgeClickCallback;
435b31ba src/eu/alefzero/owncloud/widgets/ActionEditText.java (Lennart Rosam     2012-05-16 09:48:34 +0200  44)     private Rect btn_rect;
435b31ba src/eu/alefzero/owncloud/widgets/ActionEditText.java (Lennart Rosam     2012-05-16 09:48:34 +0200  45) 
435b31ba src/eu/alefzero/owncloud/widgets/ActionEditText.java (Lennart Rosam     2012-05-16 09:48:34 +0200  46)     public ActionEditText(Context context, AttributeSet attrs) {
435b31ba src/eu/alefzero/owncloud/widgets/ActionEditText.java (Lennart Rosam     2012-05-16 09:48:34 +0200  47)         super(context, attrs);
435b31ba src/eu/alefzero/owncloud/widgets/ActionEditText.java (Lennart Rosam     2012-05-16 09:48:34 +0200  48)         getAttrs(attrs);
435b31ba src/eu/alefzero/owncloud/widgets/ActionEditText.java (Lennart Rosam     2012-05-16 09:48:34 +0200  49)         s = optionOneString;
435b31ba src/eu/alefzero/owncloud/widgets/ActionEditText.java (Lennart Rosam     2012-05-16 09:48:34 +0200  50)         mTextBounds = new Rect();
435b31ba src/eu/alefzero/owncloud/widgets/ActionEditText.java (Lennart Rosam     2012-05-16 09:48:34 +0200  51)         mButtonRect = new Rect();
435b31ba src/eu/alefzero/owncloud/widgets/ActionEditText.java (Lennart Rosam     2012-05-16 09:48:34 +0200  52)     }
435b31ba src/eu/alefzero/owncloud/widgets/ActionEditText.java (Lennart Rosam     2012-05-16 09:48:34 +0200  53) 
435b31ba src/eu/alefzero/owncloud/widgets/ActionEditText.java (Lennart Rosam     2012-05-16 09:48:34 +0200  54)     public ActionEditText(Context context, AttributeSet attrs, int defStyle) {
435b31ba src/eu/alefzero/owncloud/widgets/ActionEditText.java (Lennart Rosam     2012-05-16 09:48:34 +0200  55)         super(context, attrs, defStyle);
435b31ba src/eu/alefzero/owncloud/widgets/ActionEditText.java (Lennart Rosam     2012-05-16 09:48:34 +0200  56)         getAttrs(attrs);
435b31ba src/eu/alefzero/owncloud/widgets/ActionEditText.java (Lennart Rosam     2012-05-16 09:48:34 +0200  57)         s = optionOneString;
435b31ba src/eu/alefzero/owncloud/widgets/ActionEditText.java (Lennart Rosam     2012-05-16 09:48:34 +0200  58)         mTextBounds = new Rect();
435b31ba src/eu/alefzero/owncloud/widgets/ActionEditText.java (Lennart Rosam     2012-05-16 09:48:34 +0200  59)         mButtonRect = new Rect();
435b31ba src/eu/alefzero/owncloud/widgets/ActionEditText.java (Lennart Rosam     2012-05-16 09:48:34 +0200  60)     }
435b31ba src/eu/alefzero/owncloud/widgets/ActionEditText.java (Lennart Rosam     2012-05-16 09:48:34 +0200  61) 
435b31ba src/eu/alefzero/owncloud/widgets/ActionEditText.java (Lennart Rosam     2012-05-16 09:48:34 +0200  62)     @Override
435b31ba src/eu/alefzero/owncloud/widgets/ActionEditText.java (Lennart Rosam     2012-05-16 09:48:34 +0200  63)     protected void onDraw(Canvas canvas) {
435b31ba src/eu/alefzero/owncloud/widgets/ActionEditText.java (Lennart Rosam     2012-05-16 09:48:34 +0200  64)         super.onDraw(canvas);
435b31ba src/eu/alefzero/owncloud/widgets/ActionEditText.java (Lennart Rosam     2012-05-16 09:48:34 +0200  65) 
435b31ba src/eu/alefzero/owncloud/widgets/ActionEditText.java (Lennart Rosam     2012-05-16 09:48:34 +0200  66)         Paint p = getPaint();
435b31ba src/eu/alefzero/owncloud/widgets/ActionEditText.java (Lennart Rosam     2012-05-16 09:48:34 +0200  67) 
435b31ba src/eu/alefzero/owncloud/widgets/ActionEditText.java (Lennart Rosam     2012-05-16 09:48:34 +0200  68)         p.getTextBounds(s, 0, s.length(), mTextBounds);
435b31ba src/eu/alefzero/owncloud/widgets/ActionEditText.java (Lennart Rosam     2012-05-16 09:48:34 +0200  69) 
435b31ba src/eu/alefzero/owncloud/widgets/ActionEditText.java (Lennart Rosam     2012-05-16 09:48:34 +0200  70)         getDrawingRect(mButtonRect);
435b31ba src/eu/alefzero/owncloud/widgets/ActionEditText.java (Lennart Rosam     2012-05-16 09:48:34 +0200  71)         mButtonRect.top += 10;
435b31ba src/eu/alefzero/owncloud/widgets/ActionEditText.java (Lennart Rosam     2012-05-16 09:48:34 +0200  72)         mButtonRect.bottom -= 10;
435b31ba src/eu/alefzero/owncloud/widgets/ActionEditText.java (Lennart Rosam     2012-05-16 09:48:34 +0200  73)         mButtonRect.left = (int) (getWidth() - mTextBounds.width() - 18);
435b31ba src/eu/alefzero/owncloud/widgets/ActionEditText.java (Lennart Rosam     2012-05-16 09:48:34 +0200  74)         mButtonRect.right = getWidth() - 10;
435b31ba src/eu/alefzero/owncloud/widgets/ActionEditText.java (Lennart Rosam     2012-05-16 09:48:34 +0200  75)         btn_rect = mButtonRect;
435b31ba src/eu/alefzero/owncloud/widgets/ActionEditText.java (Lennart Rosam     2012-05-16 09:48:34 +0200  76) 
435b31ba src/eu/alefzero/owncloud/widgets/ActionEditText.java (Lennart Rosam     2012-05-16 09:48:34 +0200  77)         if (s.equals(optionOneString))
435b31ba src/eu/alefzero/owncloud/widgets/ActionEditText.java (Lennart Rosam     2012-05-16 09:48:34 +0200  78)             p.setColor(optionOneColor);
435b31ba src/eu/alefzero/owncloud/widgets/ActionEditText.java (Lennart Rosam     2012-05-16 09:48:34 +0200  79)         else
435b31ba src/eu/alefzero/owncloud/widgets/ActionEditText.java (Lennart Rosam     2012-05-16 09:48:34 +0200  80)             p.setColor(optionTwoColor);
435b31ba src/eu/alefzero/owncloud/widgets/ActionEditText.java (Lennart Rosam     2012-05-16 09:48:34 +0200  81)         canvas.drawRect(mButtonRect, p);
435b31ba src/eu/alefzero/owncloud/widgets/ActionEditText.java (Lennart Rosam     2012-05-16 09:48:34 +0200  82)         p.setColor(Color.GRAY);
435b31ba src/eu/alefzero/owncloud/widgets/ActionEditText.java (Lennart Rosam     2012-05-16 09:48:34 +0200  83) 
435b31ba src/eu/alefzero/owncloud/widgets/ActionEditText.java (Lennart Rosam     2012-05-16 09:48:34 +0200  84)         canvas.drawText(s, mButtonRect.left + 3, mButtonRect.bottom
435b31ba src/eu/alefzero/owncloud/widgets/ActionEditText.java (Lennart Rosam     2012-05-16 09:48:34 +0200  85)                 - (mTextBounds.height() / 2), p);
435b31ba src/eu/alefzero/owncloud/widgets/ActionEditText.java (Lennart Rosam     2012-05-16 09:48:34 +0200  86) 
435b31ba src/eu/alefzero/owncloud/widgets/ActionEditText.java (Lennart Rosam     2012-05-16 09:48:34 +0200  87)         invalidate();
435b31ba src/eu/alefzero/owncloud/widgets/ActionEditText.java (Lennart Rosam     2012-05-16 09:48:34 +0200  88)     }
435b31ba src/eu/alefzero/owncloud/widgets/ActionEditText.java (Lennart Rosam     2012-05-16 09:48:34 +0200  89) 
435b31ba src/eu/alefzero/owncloud/widgets/ActionEditText.java (Lennart Rosam     2012-05-16 09:48:34 +0200  90)     @Override
435b31ba src/eu/alefzero/owncloud/widgets/ActionEditText.java (Lennart Rosam     2012-05-16 09:48:34 +0200  91)     public boolean onTouchEvent(MotionEvent event) {
435b31ba src/eu/alefzero/owncloud/widgets/ActionEditText.java (Lennart Rosam     2012-05-16 09:48:34 +0200  92)         int touchX = (int) event.getX();
435b31ba src/eu/alefzero/owncloud/widgets/ActionEditText.java (Lennart Rosam     2012-05-16 09:48:34 +0200  93)         int touchY = (int) event.getY();
435b31ba src/eu/alefzero/owncloud/widgets/ActionEditText.java (Lennart Rosam     2012-05-16 09:48:34 +0200  94)         boolean r = super.onTouchEvent(event);
435b31ba src/eu/alefzero/owncloud/widgets/ActionEditText.java (Lennart Rosam     2012-05-16 09:48:34 +0200  95)         if (event.getAction() == MotionEvent.ACTION_UP) {
435b31ba src/eu/alefzero/owncloud/widgets/ActionEditText.java (Lennart Rosam     2012-05-16 09:48:34 +0200  96)             if (btn_rect.contains(touchX, touchY)) {
435b31ba src/eu/alefzero/owncloud/widgets/ActionEditText.java (Lennart Rosam     2012-05-16 09:48:34 +0200  97)                 if (s.equals(optionTwoString))
435b31ba src/eu/alefzero/owncloud/widgets/ActionEditText.java (Lennart Rosam     2012-05-16 09:48:34 +0200  98)                     s = optionOneString;
435b31ba src/eu/alefzero/owncloud/widgets/ActionEditText.java (Lennart Rosam     2012-05-16 09:48:34 +0200  99)                 else
435b31ba src/eu/alefzero/owncloud/widgets/ActionEditText.java (Lennart Rosam     2012-05-16 09:48:34 +0200 100)                     s = optionTwoString;
435b31ba src/eu/alefzero/owncloud/widgets/ActionEditText.java (Lennart Rosam     2012-05-16 09:48:34 +0200 101)                 if (badgeClickCallback != null) {
435b31ba src/eu/alefzero/owncloud/widgets/ActionEditText.java (Lennart Rosam     2012-05-16 09:48:34 +0200 102)                     @SuppressWarnings("rawtypes")
435b31ba src/eu/alefzero/owncloud/widgets/ActionEditText.java (Lennart Rosam     2012-05-16 09:48:34 +0200 103)                     Class[] paramtypes = new Class[2];
435b31ba src/eu/alefzero/owncloud/widgets/ActionEditText.java (Lennart Rosam     2012-05-16 09:48:34 +0200 104)                     paramtypes[0] = android.view.View.class;
435b31ba src/eu/alefzero/owncloud/widgets/ActionEditText.java (Lennart Rosam     2012-05-16 09:48:34 +0200 105)                     paramtypes[1] = String.class;
435b31ba src/eu/alefzero/owncloud/widgets/ActionEditText.java (Lennart Rosam     2012-05-16 09:48:34 +0200 106)                     Method method;
435b31ba src/eu/alefzero/owncloud/widgets/ActionEditText.java (Lennart Rosam     2012-05-16 09:48:34 +0200 107)                     try {
435b31ba src/eu/alefzero/owncloud/widgets/ActionEditText.java (Lennart Rosam     2012-05-16 09:48:34 +0200 108) 
435b31ba src/eu/alefzero/owncloud/widgets/ActionEditText.java (Lennart Rosam     2012-05-16 09:48:34 +0200 109)                         method = getContext().getClass().getMethod(
435b31ba src/eu/alefzero/owncloud/widgets/ActionEditText.java (Lennart Rosam     2012-05-16 09:48:34 +0200 110)                                 badgeClickCallback, paramtypes);
435b31ba src/eu/alefzero/owncloud/widgets/ActionEditText.java (Lennart Rosam     2012-05-16 09:48:34 +0200 111)                         method.invoke(getContext(), this, s);
435b31ba src/eu/alefzero/owncloud/widgets/ActionEditText.java (Lennart Rosam     2012-05-16 09:48:34 +0200 112) 
435b31ba src/eu/alefzero/owncloud/widgets/ActionEditText.java (Lennart Rosam     2012-05-16 09:48:34 +0200 113)                     } catch (NoSuchMethodException e) {
435b31ba src/eu/alefzero/owncloud/widgets/ActionEditText.java (Lennart Rosam     2012-05-16 09:48:34 +0200 114)                         e.printStackTrace();
435b31ba src/eu/alefzero/owncloud/widgets/ActionEditText.java (Lennart Rosam     2012-05-16 09:48:34 +0200 115)                     } catch (IllegalArgumentException e) {
435b31ba src/eu/alefzero/owncloud/widgets/ActionEditText.java (Lennart Rosam     2012-05-16 09:48:34 +0200 116)                         e.printStackTrace();
435b31ba src/eu/alefzero/owncloud/widgets/ActionEditText.java (Lennart Rosam     2012-05-16 09:48:34 +0200 117)                     } catch (IllegalAccessException e) {
435b31ba src/eu/alefzero/owncloud/widgets/ActionEditText.java (Lennart Rosam     2012-05-16 09:48:34 +0200 118)                         e.printStackTrace();
435b31ba src/eu/alefzero/owncloud/widgets/ActionEditText.java (Lennart Rosam     2012-05-16 09:48:34 +0200 119)                     } catch (InvocationTargetException e) {
435b31ba src/eu/alefzero/owncloud/widgets/ActionEditText.java (Lennart Rosam     2012-05-16 09:48:34 +0200 120)                         e.printStackTrace();
435b31ba src/eu/alefzero/owncloud/widgets/ActionEditText.java (Lennart Rosam     2012-05-16 09:48:34 +0200 121)                     }
435b31ba src/eu/alefzero/owncloud/widgets/ActionEditText.java (Lennart Rosam     2012-05-16 09:48:34 +0200 122) 
435b31ba src/eu/alefzero/owncloud/widgets/ActionEditText.java (Lennart Rosam     2012-05-16 09:48:34 +0200 123)                     invalidate();
435b31ba src/eu/alefzero/owncloud/widgets/ActionEditText.java (Lennart Rosam     2012-05-16 09:48:34 +0200 124)                 }
435b31ba src/eu/alefzero/owncloud/widgets/ActionEditText.java (Lennart Rosam     2012-05-16 09:48:34 +0200 125)             }
dc7cafe6 src/eu/alefzero/owncloud/widgets/ActionEditText.java (Bartek Przybylski 2012-05-01 23:37:37 +0200 126)         }
435b31ba src/eu/alefzero/owncloud/widgets/ActionEditText.java (Lennart Rosam     2012-05-16 09:48:34 +0200 127)         return r;
dc7cafe6 src/eu/alefzero/owncloud/widgets/ActionEditText.java (Bartek Przybylski 2012-05-01 23:37:37 +0200 128)     }
435b31ba src/eu/alefzero/owncloud/widgets/ActionEditText.java (Lennart Rosam     2012-05-16 09:48:34 +0200 129) 
435b31ba src/eu/alefzero/owncloud/widgets/ActionEditText.java (Lennart Rosam     2012-05-16 09:48:34 +0200 130)     private void getAttrs(AttributeSet attr) {
435b31ba src/eu/alefzero/owncloud/widgets/ActionEditText.java (Lennart Rosam     2012-05-16 09:48:34 +0200 131)         TypedArray a = getContext().obtainStyledAttributes(attr,
435b31ba src/eu/alefzero/owncloud/widgets/ActionEditText.java (Lennart Rosam     2012-05-16 09:48:34 +0200 132)                 R.styleable.ActionEditText);
435b31ba src/eu/alefzero/owncloud/widgets/ActionEditText.java (Lennart Rosam     2012-05-16 09:48:34 +0200 133)         optionOneString = a
435b31ba src/eu/alefzero/owncloud/widgets/ActionEditText.java (Lennart Rosam     2012-05-16 09:48:34 +0200 134)                 .getString(R.styleable.ActionEditText_optionOneString);
435b31ba src/eu/alefzero/owncloud/widgets/ActionEditText.java (Lennart Rosam     2012-05-16 09:48:34 +0200 135)         optionTwoString = a
435b31ba src/eu/alefzero/owncloud/widgets/ActionEditText.java (Lennart Rosam     2012-05-16 09:48:34 +0200 136)                 .getString(R.styleable.ActionEditText_optionTwoString);
435b31ba src/eu/alefzero/owncloud/widgets/ActionEditText.java (Lennart Rosam     2012-05-16 09:48:34 +0200 137)         optionOneColor = a.getColor(R.styleable.ActionEditText_optionOneColor,
435b31ba src/eu/alefzero/owncloud/widgets/ActionEditText.java (Lennart Rosam     2012-05-16 09:48:34 +0200 138)                 0x00ff00);
435b31ba src/eu/alefzero/owncloud/widgets/ActionEditText.java (Lennart Rosam     2012-05-16 09:48:34 +0200 139)         optionTwoColor = a.getColor(R.styleable.ActionEditText_optionTwoColor,
435b31ba src/eu/alefzero/owncloud/widgets/ActionEditText.java (Lennart Rosam     2012-05-16 09:48:34 +0200 140)                 0xff0000);
435b31ba src/eu/alefzero/owncloud/widgets/ActionEditText.java (Lennart Rosam     2012-05-16 09:48:34 +0200 141)         badgeClickCallback = a
435b31ba src/eu/alefzero/owncloud/widgets/ActionEditText.java (Lennart Rosam     2012-05-16 09:48:34 +0200 142)                 .getString(R.styleable.ActionEditText_onBadgeClick);
435b31ba src/eu/alefzero/owncloud/widgets/ActionEditText.java (Lennart Rosam     2012-05-16 09:48:34 +0200 143)     }
435b31ba src/eu/alefzero/owncloud/widgets/ActionEditText.java (Lennart Rosam     2012-05-16 09:48:34 +0200 144) 
dc7cafe6 src/eu/alefzero/owncloud/widgets/ActionEditText.java (Bartek Przybylski 2012-05-01 23:37:37 +0200 145) }
