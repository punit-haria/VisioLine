92080afe src/com/owncloud/android/extensions/ExtensionsAvailableActivity.java (Bartek Przybylski 2012-10-28 00:12:15 +0200  1) /* ownCloud Android client application
92080afe src/com/owncloud/android/extensions/ExtensionsAvailableActivity.java (Bartek Przybylski 2012-10-28 00:12:15 +0200  2)  *   Copyright (C) 2012 Bartek Przybylski
bb257ec7 src/com/owncloud/android/extensions/ExtensionsAvailableActivity.java (David A. Velasco  2013-02-07 18:45:10 +0100  3)  *   Copyright (C) 2012-2013 ownCloud Inc.
92080afe src/com/owncloud/android/extensions/ExtensionsAvailableActivity.java (Bartek Przybylski 2012-10-28 00:12:15 +0200  4)  *
92080afe src/com/owncloud/android/extensions/ExtensionsAvailableActivity.java (Bartek Przybylski 2012-10-28 00:12:15 +0200  5)  *   This program is free software: you can redistribute it and/or modify
bdc0332c src/com/owncloud/android/extensions/ExtensionsAvailableActivity.java (David A. Velasco  2013-04-17 12:26:13 +0200  6)  *   it under the terms of the GNU General Public License version 2,
bdc0332c src/com/owncloud/android/extensions/ExtensionsAvailableActivity.java (David A. Velasco  2013-04-17 12:26:13 +0200  7)  *   as published by the Free Software Foundation.
92080afe src/com/owncloud/android/extensions/ExtensionsAvailableActivity.java (Bartek Przybylski 2012-10-28 00:12:15 +0200  8)  *
92080afe src/com/owncloud/android/extensions/ExtensionsAvailableActivity.java (Bartek Przybylski 2012-10-28 00:12:15 +0200  9)  *   This program is distributed in the hope that it will be useful,
92080afe src/com/owncloud/android/extensions/ExtensionsAvailableActivity.java (Bartek Przybylski 2012-10-28 00:12:15 +0200 10)  *   but WITHOUT ANY WARRANTY; without even the implied warranty of
92080afe src/com/owncloud/android/extensions/ExtensionsAvailableActivity.java (Bartek Przybylski 2012-10-28 00:12:15 +0200 11)  *   MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
92080afe src/com/owncloud/android/extensions/ExtensionsAvailableActivity.java (Bartek Przybylski 2012-10-28 00:12:15 +0200 12)  *   GNU General Public License for more details.
92080afe src/com/owncloud/android/extensions/ExtensionsAvailableActivity.java (Bartek Przybylski 2012-10-28 00:12:15 +0200 13)  *
92080afe src/com/owncloud/android/extensions/ExtensionsAvailableActivity.java (Bartek Przybylski 2012-10-28 00:12:15 +0200 14)  *   You should have received a copy of the GNU General Public License
92080afe src/com/owncloud/android/extensions/ExtensionsAvailableActivity.java (Bartek Przybylski 2012-10-28 00:12:15 +0200 15)  *   along with this program.  If not, see <http://www.gnu.org/licenses/>.
92080afe src/com/owncloud/android/extensions/ExtensionsAvailableActivity.java (Bartek Przybylski 2012-10-28 00:12:15 +0200 16)  *
92080afe src/com/owncloud/android/extensions/ExtensionsAvailableActivity.java (Bartek Przybylski 2012-10-28 00:12:15 +0200 17)  */
92080afe src/com/owncloud/android/extensions/ExtensionsAvailableActivity.java (Bartek Przybylski 2012-10-28 00:12:15 +0200 18) 
a4ba6170 src/com/owncloud/android/extensions/ExtensionsAvailableActivity.java (Bartek Przybylski 2012-07-31 17:43:37 +0200 19) package com.owncloud.android.extensions;
0aeb4258 src/eu/alefzero/owncloud/extensions/ExtensionsAvailableActivity.java (Bartek Przybylski 2012-05-13 16:13:13 +0200 20) 
0aeb4258 src/eu/alefzero/owncloud/extensions/ExtensionsAvailableActivity.java (Bartek Przybylski 2012-05-13 16:13:13 +0200 21) import android.os.Bundle;
0aeb4258 src/eu/alefzero/owncloud/extensions/ExtensionsAvailableActivity.java (Bartek Przybylski 2012-05-13 16:13:13 +0200 22) import android.support.v4.app.FragmentManager;
0aeb4258 src/eu/alefzero/owncloud/extensions/ExtensionsAvailableActivity.java (Bartek Przybylski 2012-05-13 16:13:13 +0200 23) 
0aeb4258 src/eu/alefzero/owncloud/extensions/ExtensionsAvailableActivity.java (Bartek Przybylski 2012-05-13 16:13:13 +0200 24) import com.actionbarsherlock.app.SherlockFragmentActivity;
0aeb4258 src/eu/alefzero/owncloud/extensions/ExtensionsAvailableActivity.java (Bartek Przybylski 2012-05-13 16:13:13 +0200 25) 
0aeb4258 src/eu/alefzero/owncloud/extensions/ExtensionsAvailableActivity.java (Bartek Przybylski 2012-05-13 16:13:13 +0200 26) public class ExtensionsAvailableActivity extends SherlockFragmentActivity {
435b31ba src/eu/alefzero/owncloud/extensions/ExtensionsAvailableActivity.java (Lennart Rosam     2012-05-16 09:48:34 +0200 27) 
435b31ba src/eu/alefzero/owncloud/extensions/ExtensionsAvailableActivity.java (Lennart Rosam     2012-05-16 09:48:34 +0200 28)     @Override
435b31ba src/eu/alefzero/owncloud/extensions/ExtensionsAvailableActivity.java (Lennart Rosam     2012-05-16 09:48:34 +0200 29)     public void onCreate(Bundle savedInstanceState) {
435b31ba src/eu/alefzero/owncloud/extensions/ExtensionsAvailableActivity.java (Lennart Rosam     2012-05-16 09:48:34 +0200 30)         super.onCreate(savedInstanceState);
435b31ba src/eu/alefzero/owncloud/extensions/ExtensionsAvailableActivity.java (Lennart Rosam     2012-05-16 09:48:34 +0200 31)         FragmentManager fm = getSupportFragmentManager();
435b31ba src/eu/alefzero/owncloud/extensions/ExtensionsAvailableActivity.java (Lennart Rosam     2012-05-16 09:48:34 +0200 32)         ExtensionsAvailableDialog ead = new ExtensionsAvailableDialog();
435b31ba src/eu/alefzero/owncloud/extensions/ExtensionsAvailableActivity.java (Lennart Rosam     2012-05-16 09:48:34 +0200 33)         ead.show(fm, "extensions_available_dialog");
435b31ba src/eu/alefzero/owncloud/extensions/ExtensionsAvailableActivity.java (Lennart Rosam     2012-05-16 09:48:34 +0200 34)     }
0aeb4258 src/eu/alefzero/owncloud/extensions/ExtensionsAvailableActivity.java (Bartek Przybylski 2012-05-13 16:13:13 +0200 35) }
