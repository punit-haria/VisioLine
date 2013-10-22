92080afe (Bartek Przybylski 2012-10-28 00:12:15 +0200   1) /* ownCloud Android client application
92080afe (Bartek Przybylski 2012-10-28 00:12:15 +0200   2)  *   Copyright (C) 2012 Bartek Przybylski
bb257ec7 (David A. Velasco  2013-02-07 18:45:10 +0100   3)  *   Copyright (C) 2012-2013 ownCloud Inc.
92080afe (Bartek Przybylski 2012-10-28 00:12:15 +0200   4)  *
92080afe (Bartek Przybylski 2012-10-28 00:12:15 +0200   5)  *   This program is free software: you can redistribute it and/or modify
bdc0332c (David A. Velasco  2013-04-17 12:26:13 +0200   6)  *   it under the terms of the GNU General Public License version 2,
bdc0332c (David A. Velasco  2013-04-17 12:26:13 +0200   7)  *   as published by the Free Software Foundation.
92080afe (Bartek Przybylski 2012-10-28 00:12:15 +0200   8)  *
92080afe (Bartek Przybylski 2012-10-28 00:12:15 +0200   9)  *   This program is distributed in the hope that it will be useful,
92080afe (Bartek Przybylski 2012-10-28 00:12:15 +0200  10)  *   but WITHOUT ANY WARRANTY; without even the implied warranty of
92080afe (Bartek Przybylski 2012-10-28 00:12:15 +0200  11)  *   MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
92080afe (Bartek Przybylski 2012-10-28 00:12:15 +0200  12)  *   GNU General Public License for more details.
92080afe (Bartek Przybylski 2012-10-28 00:12:15 +0200  13)  *
92080afe (Bartek Przybylski 2012-10-28 00:12:15 +0200  14)  *   You should have received a copy of the GNU General Public License
92080afe (Bartek Przybylski 2012-10-28 00:12:15 +0200  15)  *   along with this program.  If not, see <http://www.gnu.org/licenses/>.
92080afe (Bartek Przybylski 2012-10-28 00:12:15 +0200  16)  *
92080afe (Bartek Przybylski 2012-10-28 00:12:15 +0200  17)  */
92080afe (Bartek Przybylski 2012-10-28 00:12:15 +0200  18) 
ba148a82 (Bartek Przybylski 2012-08-22 19:32:42 +0200  19) package com.owncloud.android.utils;
ba148a82 (Bartek Przybylski 2012-08-22 19:32:42 +0200  20) 
ba148a82 (Bartek Przybylski 2012-08-22 19:32:42 +0200  21) import java.io.File;
ba148a82 (Bartek Przybylski 2012-08-22 19:32:42 +0200  22) import java.util.ArrayList;
ba148a82 (Bartek Przybylski 2012-08-22 19:32:42 +0200  23) import java.util.List;
ba148a82 (Bartek Przybylski 2012-08-22 19:32:42 +0200  24) import java.util.Stack;
ba148a82 (Bartek Przybylski 2012-08-22 19:32:42 +0200  25) 
ba148a82 (Bartek Przybylski 2012-08-22 19:32:42 +0200  26) import android.os.FileObserver;
ba148a82 (Bartek Przybylski 2012-08-22 19:32:42 +0200  27) 
ba148a82 (Bartek Przybylski 2012-08-22 19:32:42 +0200  28) public class RecursiveFileObserver extends FileObserver {
ba148a82 (Bartek Przybylski 2012-08-22 19:32:42 +0200  29) 
ba148a82 (Bartek Przybylski 2012-08-22 19:32:42 +0200  30)     public static int CHANGES_ONLY = CLOSE_WRITE | MOVE_SELF | MOVED_FROM;
ba148a82 (Bartek Przybylski 2012-08-22 19:32:42 +0200  31)     
ba148a82 (Bartek Przybylski 2012-08-22 19:32:42 +0200  32)     List<SingleFileObserver> mObservers;
ba148a82 (Bartek Przybylski 2012-08-22 19:32:42 +0200  33)     String mPath;
ba148a82 (Bartek Przybylski 2012-08-22 19:32:42 +0200  34)     int mMask;
ba148a82 (Bartek Przybylski 2012-08-22 19:32:42 +0200  35)     
ba148a82 (Bartek Przybylski 2012-08-22 19:32:42 +0200  36)     public RecursiveFileObserver(String path) {
ba148a82 (Bartek Przybylski 2012-08-22 19:32:42 +0200  37)         this(path, ALL_EVENTS);
ba148a82 (Bartek Przybylski 2012-08-22 19:32:42 +0200  38)     }
ba148a82 (Bartek Przybylski 2012-08-22 19:32:42 +0200  39)     
ba148a82 (Bartek Przybylski 2012-08-22 19:32:42 +0200  40)     public RecursiveFileObserver(String path, int mask) {
ba148a82 (Bartek Przybylski 2012-08-22 19:32:42 +0200  41)         super(path, mask);
ba148a82 (Bartek Przybylski 2012-08-22 19:32:42 +0200  42)         mPath = path;
ba148a82 (Bartek Przybylski 2012-08-22 19:32:42 +0200  43)         mMask = mask;
ba148a82 (Bartek Przybylski 2012-08-22 19:32:42 +0200  44)     }
ba148a82 (Bartek Przybylski 2012-08-22 19:32:42 +0200  45) 
ba148a82 (Bartek Przybylski 2012-08-22 19:32:42 +0200  46)     @Override
ba148a82 (Bartek Przybylski 2012-08-22 19:32:42 +0200  47)     public void startWatching() {
ba148a82 (Bartek Przybylski 2012-08-22 19:32:42 +0200  48)         if (mObservers != null) return;
ba148a82 (Bartek Przybylski 2012-08-22 19:32:42 +0200  49)         mObservers = new ArrayList<SingleFileObserver>();
ba148a82 (Bartek Przybylski 2012-08-22 19:32:42 +0200  50)         Stack<String> stack = new Stack<String>();
ba148a82 (Bartek Przybylski 2012-08-22 19:32:42 +0200  51)         stack.push(mPath);
ba148a82 (Bartek Przybylski 2012-08-22 19:32:42 +0200  52)         
ba148a82 (Bartek Przybylski 2012-08-22 19:32:42 +0200  53)         while (!stack.empty()) {
ba148a82 (Bartek Przybylski 2012-08-22 19:32:42 +0200  54)             String parent = stack.pop();
ba148a82 (Bartek Przybylski 2012-08-22 19:32:42 +0200  55)             mObservers.add(new SingleFileObserver(parent, mMask));
ba148a82 (Bartek Przybylski 2012-08-22 19:32:42 +0200  56)             File path = new File(parent);
ba148a82 (Bartek Przybylski 2012-08-22 19:32:42 +0200  57)             File[] files = path.listFiles();
ba148a82 (Bartek Przybylski 2012-08-22 19:32:42 +0200  58)             if (files == null) continue;
ba148a82 (Bartek Przybylski 2012-08-22 19:32:42 +0200  59)             for (int i = 0; i < files.length; ++i) {
ba148a82 (Bartek Przybylski 2012-08-22 19:32:42 +0200  60)                 if (files[i].isDirectory() && !files[i].getName().equals(".")
ba148a82 (Bartek Przybylski 2012-08-22 19:32:42 +0200  61)                     && !files[i].getName().equals("..")) {
ba148a82 (Bartek Przybylski 2012-08-22 19:32:42 +0200  62)                     stack.push(files[i].getPath());
ba148a82 (Bartek Przybylski 2012-08-22 19:32:42 +0200  63)                 }
ba148a82 (Bartek Przybylski 2012-08-22 19:32:42 +0200  64)             }
ba148a82 (Bartek Przybylski 2012-08-22 19:32:42 +0200  65)         }
ba148a82 (Bartek Przybylski 2012-08-22 19:32:42 +0200  66)         for (int i = 0; i < mObservers.size(); i++)
ba148a82 (Bartek Przybylski 2012-08-22 19:32:42 +0200  67)             mObservers.get(i).startWatching();
ba148a82 (Bartek Przybylski 2012-08-22 19:32:42 +0200  68)     }
ba148a82 (Bartek Przybylski 2012-08-22 19:32:42 +0200  69)     
ba148a82 (Bartek Przybylski 2012-08-22 19:32:42 +0200  70)     @Override
ba148a82 (Bartek Przybylski 2012-08-22 19:32:42 +0200  71)     public void stopWatching() {
ba148a82 (Bartek Przybylski 2012-08-22 19:32:42 +0200  72)         if (mObservers == null) return;
ba148a82 (Bartek Przybylski 2012-08-22 19:32:42 +0200  73)         
ba148a82 (Bartek Przybylski 2012-08-22 19:32:42 +0200  74)         for (int i = 0; i < mObservers.size(); ++i)
ba148a82 (Bartek Przybylski 2012-08-22 19:32:42 +0200  75)             mObservers.get(i).stopWatching();
ba148a82 (Bartek Przybylski 2012-08-22 19:32:42 +0200  76) 
ba148a82 (Bartek Przybylski 2012-08-22 19:32:42 +0200  77)         mObservers.clear();
ba148a82 (Bartek Przybylski 2012-08-22 19:32:42 +0200  78)         mObservers = null;
ba148a82 (Bartek Przybylski 2012-08-22 19:32:42 +0200  79)     }
ba148a82 (Bartek Przybylski 2012-08-22 19:32:42 +0200  80)     
ba148a82 (Bartek Przybylski 2012-08-22 19:32:42 +0200  81)     @Override
ba148a82 (Bartek Przybylski 2012-08-22 19:32:42 +0200  82)     public void onEvent(int event, String path) {
ba148a82 (Bartek Przybylski 2012-08-22 19:32:42 +0200  83)         
ba148a82 (Bartek Przybylski 2012-08-22 19:32:42 +0200  84)     }
ba148a82 (Bartek Przybylski 2012-08-22 19:32:42 +0200  85)     
ba148a82 (Bartek Przybylski 2012-08-22 19:32:42 +0200  86)     private class SingleFileObserver extends FileObserver {
ba148a82 (Bartek Przybylski 2012-08-22 19:32:42 +0200  87)         private String mPath;
ba148a82 (Bartek Przybylski 2012-08-22 19:32:42 +0200  88) 
ba148a82 (Bartek Przybylski 2012-08-22 19:32:42 +0200  89)         public SingleFileObserver(String path, int mask) {
ba148a82 (Bartek Przybylski 2012-08-22 19:32:42 +0200  90)             super(path, mask);
ba148a82 (Bartek Przybylski 2012-08-22 19:32:42 +0200  91)             mPath = path;
ba148a82 (Bartek Przybylski 2012-08-22 19:32:42 +0200  92)         }
ba148a82 (Bartek Przybylski 2012-08-22 19:32:42 +0200  93)         
ba148a82 (Bartek Przybylski 2012-08-22 19:32:42 +0200  94)         @Override
ba148a82 (Bartek Przybylski 2012-08-22 19:32:42 +0200  95)         public void onEvent(int event, String path) {
ba148a82 (Bartek Przybylski 2012-08-22 19:32:42 +0200  96)             String newPath = mPath + "/" + path;
ba148a82 (Bartek Przybylski 2012-08-22 19:32:42 +0200  97)             RecursiveFileObserver.this.onEvent(event, newPath);
ba148a82 (Bartek Przybylski 2012-08-22 19:32:42 +0200  98)         } 
ba148a82 (Bartek Przybylski 2012-08-22 19:32:42 +0200  99)         
ba148a82 (Bartek Przybylski 2012-08-22 19:32:42 +0200 100)     }
ba148a82 (Bartek Przybylski 2012-08-22 19:32:42 +0200 101) }
