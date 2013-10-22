92080afe src/com/owncloud/android/extensions/ExtensionsAvailableDialog.java (Bartek Przybylski 2012-10-28 00:12:15 +0200  1) /* ownCloud Android client application
92080afe src/com/owncloud/android/extensions/ExtensionsAvailableDialog.java (Bartek Przybylski 2012-10-28 00:12:15 +0200  2)  *   Copyright (C) 2012 Bartek Przybylski
bb257ec7 src/com/owncloud/android/extensions/ExtensionsAvailableDialog.java (David A. Velasco  2013-02-07 18:45:10 +0100  3)  *   Copyright (C) 2012-2013 ownCloud Inc.
92080afe src/com/owncloud/android/extensions/ExtensionsAvailableDialog.java (Bartek Przybylski 2012-10-28 00:12:15 +0200  4)  *
92080afe src/com/owncloud/android/extensions/ExtensionsAvailableDialog.java (Bartek Przybylski 2012-10-28 00:12:15 +0200  5)  *   This program is free software: you can redistribute it and/or modify
bdc0332c src/com/owncloud/android/extensions/ExtensionsAvailableDialog.java (David A. Velasco  2013-04-17 12:26:13 +0200  6)  *   it under the terms of the GNU General Public License version 2,
bdc0332c src/com/owncloud/android/extensions/ExtensionsAvailableDialog.java (David A. Velasco  2013-04-17 12:26:13 +0200  7)  *   as published by the Free Software Foundation.
92080afe src/com/owncloud/android/extensions/ExtensionsAvailableDialog.java (Bartek Przybylski 2012-10-28 00:12:15 +0200  8)  *
92080afe src/com/owncloud/android/extensions/ExtensionsAvailableDialog.java (Bartek Przybylski 2012-10-28 00:12:15 +0200  9)  *   This program is distributed in the hope that it will be useful,
92080afe src/com/owncloud/android/extensions/ExtensionsAvailableDialog.java (Bartek Przybylski 2012-10-28 00:12:15 +0200 10)  *   but WITHOUT ANY WARRANTY; without even the implied warranty of
92080afe src/com/owncloud/android/extensions/ExtensionsAvailableDialog.java (Bartek Przybylski 2012-10-28 00:12:15 +0200 11)  *   MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
92080afe src/com/owncloud/android/extensions/ExtensionsAvailableDialog.java (Bartek Przybylski 2012-10-28 00:12:15 +0200 12)  *   GNU General Public License for more details.
92080afe src/com/owncloud/android/extensions/ExtensionsAvailableDialog.java (Bartek Przybylski 2012-10-28 00:12:15 +0200 13)  *
92080afe src/com/owncloud/android/extensions/ExtensionsAvailableDialog.java (Bartek Przybylski 2012-10-28 00:12:15 +0200 14)  *   You should have received a copy of the GNU General Public License
92080afe src/com/owncloud/android/extensions/ExtensionsAvailableDialog.java (Bartek Przybylski 2012-10-28 00:12:15 +0200 15)  *   along with this program.  If not, see <http://www.gnu.org/licenses/>.
92080afe src/com/owncloud/android/extensions/ExtensionsAvailableDialog.java (Bartek Przybylski 2012-10-28 00:12:15 +0200 16)  *
92080afe src/com/owncloud/android/extensions/ExtensionsAvailableDialog.java (Bartek Przybylski 2012-10-28 00:12:15 +0200 17)  */
92080afe src/com/owncloud/android/extensions/ExtensionsAvailableDialog.java (Bartek Przybylski 2012-10-28 00:12:15 +0200 18) 
a4ba6170 src/com/owncloud/android/extensions/ExtensionsAvailableDialog.java (Bartek Przybylski 2012-07-31 17:43:37 +0200 19) package com.owncloud.android.extensions;
0aeb4258 src/eu/alefzero/owncloud/extensions/ExtensionsAvailableDialog.java (Bartek Przybylski 2012-05-13 16:13:13 +0200 20) 
8e36e7cc src/com/owncloud/android/extensions/ExtensionsAvailableDialog.java (zerginator        2013-03-12 07:56:27 +0100 21) import com.owncloud.android.Log_OC;
a4ba6170 src/com/owncloud/android/extensions/ExtensionsAvailableDialog.java (Bartek Przybylski 2012-07-31 17:43:37 +0200 22) import com.owncloud.android.R;
0aeb4258 src/eu/alefzero/owncloud/extensions/ExtensionsAvailableDialog.java (Bartek Przybylski 2012-05-13 16:13:13 +0200 23) import android.content.Intent;
0aeb4258 src/eu/alefzero/owncloud/extensions/ExtensionsAvailableDialog.java (Bartek Przybylski 2012-05-13 16:13:13 +0200 24) import android.os.Bundle;
0aeb4258 src/eu/alefzero/owncloud/extensions/ExtensionsAvailableDialog.java (Bartek Przybylski 2012-05-13 16:13:13 +0200 25) import android.support.v4.app.DialogFragment;
0aeb4258 src/eu/alefzero/owncloud/extensions/ExtensionsAvailableDialog.java (Bartek Przybylski 2012-05-13 16:13:13 +0200 26) import android.view.LayoutInflater;
0aeb4258 src/eu/alefzero/owncloud/extensions/ExtensionsAvailableDialog.java (Bartek Przybylski 2012-05-13 16:13:13 +0200 27) import android.view.View;
0aeb4258 src/eu/alefzero/owncloud/extensions/ExtensionsAvailableDialog.java (Bartek Przybylski 2012-05-13 16:13:13 +0200 28) import android.view.ViewGroup;
0aeb4258 src/eu/alefzero/owncloud/extensions/ExtensionsAvailableDialog.java (Bartek Przybylski 2012-05-13 16:13:13 +0200 29) import android.view.View.OnClickListener;
0aeb4258 src/eu/alefzero/owncloud/extensions/ExtensionsAvailableDialog.java (Bartek Przybylski 2012-05-13 16:13:13 +0200 30) import android.widget.Button;
0aeb4258 src/eu/alefzero/owncloud/extensions/ExtensionsAvailableDialog.java (Bartek Przybylski 2012-05-13 16:13:13 +0200 31) 
435b31ba src/eu/alefzero/owncloud/extensions/ExtensionsAvailableDialog.java (Lennart Rosam     2012-05-16 09:48:34 +0200 32) public class ExtensionsAvailableDialog extends DialogFragment implements
435b31ba src/eu/alefzero/owncloud/extensions/ExtensionsAvailableDialog.java (Lennart Rosam     2012-05-16 09:48:34 +0200 33)         OnClickListener {
0aeb4258 src/eu/alefzero/owncloud/extensions/ExtensionsAvailableDialog.java (Bartek Przybylski 2012-05-13 16:13:13 +0200 34) 
435b31ba src/eu/alefzero/owncloud/extensions/ExtensionsAvailableDialog.java (Lennart Rosam     2012-05-16 09:48:34 +0200 35)     public ExtensionsAvailableDialog() {
435b31ba src/eu/alefzero/owncloud/extensions/ExtensionsAvailableDialog.java (Lennart Rosam     2012-05-16 09:48:34 +0200 36)     }
435b31ba src/eu/alefzero/owncloud/extensions/ExtensionsAvailableDialog.java (Lennart Rosam     2012-05-16 09:48:34 +0200 37) 
435b31ba src/eu/alefzero/owncloud/extensions/ExtensionsAvailableDialog.java (Lennart Rosam     2012-05-16 09:48:34 +0200 38)     @Override
435b31ba src/eu/alefzero/owncloud/extensions/ExtensionsAvailableDialog.java (Lennart Rosam     2012-05-16 09:48:34 +0200 39)     public View onCreateView(LayoutInflater inflater, ViewGroup container,
435b31ba src/eu/alefzero/owncloud/extensions/ExtensionsAvailableDialog.java (Lennart Rosam     2012-05-16 09:48:34 +0200 40)             Bundle savedInstanceState) {
435b31ba src/eu/alefzero/owncloud/extensions/ExtensionsAvailableDialog.java (Lennart Rosam     2012-05-16 09:48:34 +0200 41)         View view = inflater.inflate(R.layout.extensions_available_dialog,
435b31ba src/eu/alefzero/owncloud/extensions/ExtensionsAvailableDialog.java (Lennart Rosam     2012-05-16 09:48:34 +0200 42)                 container);
435b31ba src/eu/alefzero/owncloud/extensions/ExtensionsAvailableDialog.java (Lennart Rosam     2012-05-16 09:48:34 +0200 43)         Button btnYes = (Button) view.findViewById(R.id.buttonYes);
435b31ba src/eu/alefzero/owncloud/extensions/ExtensionsAvailableDialog.java (Lennart Rosam     2012-05-16 09:48:34 +0200 44)         Button btnNo = (Button) view.findViewById(R.id.buttonNo);
435b31ba src/eu/alefzero/owncloud/extensions/ExtensionsAvailableDialog.java (Lennart Rosam     2012-05-16 09:48:34 +0200 45)         btnYes.setOnClickListener(this);
435b31ba src/eu/alefzero/owncloud/extensions/ExtensionsAvailableDialog.java (Lennart Rosam     2012-05-16 09:48:34 +0200 46)         btnNo.setOnClickListener(this);
435b31ba src/eu/alefzero/owncloud/extensions/ExtensionsAvailableDialog.java (Lennart Rosam     2012-05-16 09:48:34 +0200 47)         getDialog().setTitle(R.string.extensions_avail_title);
435b31ba src/eu/alefzero/owncloud/extensions/ExtensionsAvailableDialog.java (Lennart Rosam     2012-05-16 09:48:34 +0200 48)         return view;
435b31ba src/eu/alefzero/owncloud/extensions/ExtensionsAvailableDialog.java (Lennart Rosam     2012-05-16 09:48:34 +0200 49)     }
0aeb4258 src/eu/alefzero/owncloud/extensions/ExtensionsAvailableDialog.java (Bartek Przybylski 2012-05-13 16:13:13 +0200 50) 
435b31ba src/eu/alefzero/owncloud/extensions/ExtensionsAvailableDialog.java (Lennart Rosam     2012-05-16 09:48:34 +0200 51)     @Override
435b31ba src/eu/alefzero/owncloud/extensions/ExtensionsAvailableDialog.java (Lennart Rosam     2012-05-16 09:48:34 +0200 52)     public void onClick(View v) {
435b31ba src/eu/alefzero/owncloud/extensions/ExtensionsAvailableDialog.java (Lennart Rosam     2012-05-16 09:48:34 +0200 53)         switch (v.getId()) {
435b31ba src/eu/alefzero/owncloud/extensions/ExtensionsAvailableDialog.java (Lennart Rosam     2012-05-16 09:48:34 +0200 54)         case R.id.buttonYes: {
435b31ba src/eu/alefzero/owncloud/extensions/ExtensionsAvailableDialog.java (Lennart Rosam     2012-05-16 09:48:34 +0200 55)             Intent i = new Intent(getActivity(), ExtensionsListActivity.class);
435b31ba src/eu/alefzero/owncloud/extensions/ExtensionsAvailableDialog.java (Lennart Rosam     2012-05-16 09:48:34 +0200 56)             startActivity(i);
435b31ba src/eu/alefzero/owncloud/extensions/ExtensionsAvailableDialog.java (Lennart Rosam     2012-05-16 09:48:34 +0200 57)             getActivity().finish();
435b31ba src/eu/alefzero/owncloud/extensions/ExtensionsAvailableDialog.java (Lennart Rosam     2012-05-16 09:48:34 +0200 58)         }
435b31ba src/eu/alefzero/owncloud/extensions/ExtensionsAvailableDialog.java (Lennart Rosam     2012-05-16 09:48:34 +0200 59)             break;
435b31ba src/eu/alefzero/owncloud/extensions/ExtensionsAvailableDialog.java (Lennart Rosam     2012-05-16 09:48:34 +0200 60)         case R.id.buttonNo:
435b31ba src/eu/alefzero/owncloud/extensions/ExtensionsAvailableDialog.java (Lennart Rosam     2012-05-16 09:48:34 +0200 61)             getActivity().finish();
435b31ba src/eu/alefzero/owncloud/extensions/ExtensionsAvailableDialog.java (Lennart Rosam     2012-05-16 09:48:34 +0200 62)             break;
435b31ba src/eu/alefzero/owncloud/extensions/ExtensionsAvailableDialog.java (Lennart Rosam     2012-05-16 09:48:34 +0200 63)         default:
8e36e7cc src/com/owncloud/android/extensions/ExtensionsAvailableDialog.java (zerginator        2013-03-12 07:56:27 +0100 64)             Log_OC.e("EAD", "Button with unknown id clicked " + v.getId());
0aeb4258 src/eu/alefzero/owncloud/extensions/ExtensionsAvailableDialog.java (Bartek Przybylski 2012-05-13 16:13:13 +0200 65)         }
0aeb4258 src/eu/alefzero/owncloud/extensions/ExtensionsAvailableDialog.java (Bartek Przybylski 2012-05-13 16:13:13 +0200 66)     }
0aeb4258 src/eu/alefzero/owncloud/extensions/ExtensionsAvailableDialog.java (Bartek Przybylski 2012-05-13 16:13:13 +0200 67) 
0aeb4258 src/eu/alefzero/owncloud/extensions/ExtensionsAvailableDialog.java (Bartek Przybylski 2012-05-13 16:13:13 +0200 68) }
