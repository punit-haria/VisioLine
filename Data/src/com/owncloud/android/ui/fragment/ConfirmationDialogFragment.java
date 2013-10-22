92080afe src/com/owncloud/android/ui/fragment/ConfirmationDialogFragment.java (Bartek Przybylski 2012-10-28 00:12:15 +0200   1) /* ownCloud Android client application
92080afe src/com/owncloud/android/ui/fragment/ConfirmationDialogFragment.java (Bartek Przybylski 2012-10-28 00:12:15 +0200   2)  *   Copyright (C) 2012 Bartek Przybylski
bb257ec7 src/com/owncloud/android/ui/fragment/ConfirmationDialogFragment.java (David A. Velasco  2013-02-07 18:45:10 +0100   3)  *   Copyright (C) 2012-2013 ownCloud Inc.
92080afe src/com/owncloud/android/ui/fragment/ConfirmationDialogFragment.java (Bartek Przybylski 2012-10-28 00:12:15 +0200   4)  *
92080afe src/com/owncloud/android/ui/fragment/ConfirmationDialogFragment.java (Bartek Przybylski 2012-10-28 00:12:15 +0200   5)  *   This program is free software: you can redistribute it and/or modify
bdc0332c src/com/owncloud/android/ui/fragment/ConfirmationDialogFragment.java (David A. Velasco  2013-04-17 12:26:13 +0200   6)  *   it under the terms of the GNU General Public License version 2,
bdc0332c src/com/owncloud/android/ui/fragment/ConfirmationDialogFragment.java (David A. Velasco  2013-04-17 12:26:13 +0200   7)  *   as published by the Free Software Foundation.
92080afe src/com/owncloud/android/ui/fragment/ConfirmationDialogFragment.java (Bartek Przybylski 2012-10-28 00:12:15 +0200   8)  *
92080afe src/com/owncloud/android/ui/fragment/ConfirmationDialogFragment.java (Bartek Przybylski 2012-10-28 00:12:15 +0200   9)  *   This program is distributed in the hope that it will be useful,
92080afe src/com/owncloud/android/ui/fragment/ConfirmationDialogFragment.java (Bartek Przybylski 2012-10-28 00:12:15 +0200  10)  *   but WITHOUT ANY WARRANTY; without even the implied warranty of
92080afe src/com/owncloud/android/ui/fragment/ConfirmationDialogFragment.java (Bartek Przybylski 2012-10-28 00:12:15 +0200  11)  *   MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
92080afe src/com/owncloud/android/ui/fragment/ConfirmationDialogFragment.java (Bartek Przybylski 2012-10-28 00:12:15 +0200  12)  *   GNU General Public License for more details.
92080afe src/com/owncloud/android/ui/fragment/ConfirmationDialogFragment.java (Bartek Przybylski 2012-10-28 00:12:15 +0200  13)  *
92080afe src/com/owncloud/android/ui/fragment/ConfirmationDialogFragment.java (Bartek Przybylski 2012-10-28 00:12:15 +0200  14)  *   You should have received a copy of the GNU General Public License
92080afe src/com/owncloud/android/ui/fragment/ConfirmationDialogFragment.java (Bartek Przybylski 2012-10-28 00:12:15 +0200  15)  *   along with this program.  If not, see <http://www.gnu.org/licenses/>.
92080afe src/com/owncloud/android/ui/fragment/ConfirmationDialogFragment.java (Bartek Przybylski 2012-10-28 00:12:15 +0200  16)  *
92080afe src/com/owncloud/android/ui/fragment/ConfirmationDialogFragment.java (Bartek Przybylski 2012-10-28 00:12:15 +0200  17)  */
92080afe src/com/owncloud/android/ui/fragment/ConfirmationDialogFragment.java (Bartek Przybylski 2012-10-28 00:12:15 +0200  18) 
a4ba6170 src/com/owncloud/android/ui/fragment/ConfirmationDialogFragment.java (Bartek Przybylski 2012-07-31 17:43:37 +0200  19) package com.owncloud.android.ui.fragment;
6701c984 src/eu/alefzero/owncloud/ui/fragment/ConfirmationDialogFragment.java (David A. Velasco  2012-07-11 18:46:47 +0200  20) 
6701c984 src/eu/alefzero/owncloud/ui/fragment/ConfirmationDialogFragment.java (David A. Velasco  2012-07-11 18:46:47 +0200  21) import android.app.AlertDialog;
6701c984 src/eu/alefzero/owncloud/ui/fragment/ConfirmationDialogFragment.java (David A. Velasco  2012-07-11 18:46:47 +0200  22) import android.app.Dialog;
6701c984 src/eu/alefzero/owncloud/ui/fragment/ConfirmationDialogFragment.java (David A. Velasco  2012-07-11 18:46:47 +0200  23) import android.content.DialogInterface;
6701c984 src/eu/alefzero/owncloud/ui/fragment/ConfirmationDialogFragment.java (David A. Velasco  2012-07-11 18:46:47 +0200  24) import android.os.Bundle;
6701c984 src/eu/alefzero/owncloud/ui/fragment/ConfirmationDialogFragment.java (David A. Velasco  2012-07-11 18:46:47 +0200  25) 
6701c984 src/eu/alefzero/owncloud/ui/fragment/ConfirmationDialogFragment.java (David A. Velasco  2012-07-11 18:46:47 +0200  26) import com.actionbarsherlock.app.SherlockDialogFragment;
8e36e7cc src/com/owncloud/android/ui/fragment/ConfirmationDialogFragment.java (zerginator        2013-03-12 07:56:27 +0100  27) import com.owncloud.android.Log_OC;
6701c984 src/eu/alefzero/owncloud/ui/fragment/ConfirmationDialogFragment.java (David A. Velasco  2012-07-11 18:46:47 +0200  28) 
6701c984 src/eu/alefzero/owncloud/ui/fragment/ConfirmationDialogFragment.java (David A. Velasco  2012-07-11 18:46:47 +0200  29) public class ConfirmationDialogFragment extends SherlockDialogFragment {
6701c984 src/eu/alefzero/owncloud/ui/fragment/ConfirmationDialogFragment.java (David A. Velasco  2012-07-11 18:46:47 +0200  30) 
7f75f13f src/eu/alefzero/owncloud/ui/fragment/ConfirmationDialogFragment.java (Bartek Przybylski 2012-07-23 19:09:44 +0200  31)     public final static String ARG_CONF_RESOURCE_ID = "resource_id";
7f75f13f src/eu/alefzero/owncloud/ui/fragment/ConfirmationDialogFragment.java (Bartek Przybylski 2012-07-23 19:09:44 +0200  32)     public final static String ARG_CONF_ARGUMENTS = "string_array";
6701c984 src/eu/alefzero/owncloud/ui/fragment/ConfirmationDialogFragment.java (David A. Velasco  2012-07-11 18:46:47 +0200  33)     
0cced280 src/eu/alefzero/owncloud/ui/fragment/ConfirmationDialogFragment.java (Bartek Przybylski 2012-07-28 21:15:41 +0200  34)     public final static String ARG_POSITIVE_BTN_RES = "positive_btn_res";
0cced280 src/eu/alefzero/owncloud/ui/fragment/ConfirmationDialogFragment.java (Bartek Przybylski 2012-07-28 21:15:41 +0200  35)     public final static String ARG_NEUTRAL_BTN_RES = "neutral_btn_res";
0cced280 src/eu/alefzero/owncloud/ui/fragment/ConfirmationDialogFragment.java (Bartek Przybylski 2012-07-28 21:15:41 +0200  36)     public final static String ARG_NEGATIVE_BTN_RES = "negative_btn_res";
6701c984 src/eu/alefzero/owncloud/ui/fragment/ConfirmationDialogFragment.java (David A. Velasco  2012-07-11 18:46:47 +0200  37)     
f2474ae2 src/com/owncloud/android/ui/fragment/ConfirmationDialogFragment.java (David A. Velasco  2013-02-18 11:23:18 +0100  38)     public static final String FTAG_CONFIRMATION = "CONFIRMATION_FRAGMENT";
f2474ae2 src/com/owncloud/android/ui/fragment/ConfirmationDialogFragment.java (David A. Velasco  2013-02-18 11:23:18 +0100  39) 
0cced280 src/eu/alefzero/owncloud/ui/fragment/ConfirmationDialogFragment.java (Bartek Przybylski 2012-07-28 21:15:41 +0200  40)     private ConfirmationDialogFragmentListener mListener;
0cced280 src/eu/alefzero/owncloud/ui/fragment/ConfirmationDialogFragment.java (Bartek Przybylski 2012-07-28 21:15:41 +0200  41)     
45588662 src/com/owncloud/android/ui/fragment/ConfirmationDialogFragment.java (David A. Velasco  2012-12-04 14:20:02 +0100  42)     /**
45588662 src/com/owncloud/android/ui/fragment/ConfirmationDialogFragment.java (David A. Velasco  2012-12-04 14:20:02 +0100  43)      * Public factory method to create new ConfirmationDialogFragment instances.
45588662 src/com/owncloud/android/ui/fragment/ConfirmationDialogFragment.java (David A. Velasco  2012-12-04 14:20:02 +0100  44)      * 
45588662 src/com/owncloud/android/ui/fragment/ConfirmationDialogFragment.java (David A. Velasco  2012-12-04 14:20:02 +0100  45)      * @param string_id         Resource id for a message to show in the dialog.
45588662 src/com/owncloud/android/ui/fragment/ConfirmationDialogFragment.java (David A. Velasco  2012-12-04 14:20:02 +0100  46)      * @param arguments         Arguments to complete the message, if it's a format string.
45588662 src/com/owncloud/android/ui/fragment/ConfirmationDialogFragment.java (David A. Velasco  2012-12-04 14:20:02 +0100  47)      * @param posBtn            Resource id for the text of the positive button.
45588662 src/com/owncloud/android/ui/fragment/ConfirmationDialogFragment.java (David A. Velasco  2012-12-04 14:20:02 +0100  48)      * @param neuBtn            Resource id for the text of the neutral button.
45588662 src/com/owncloud/android/ui/fragment/ConfirmationDialogFragment.java (David A. Velasco  2012-12-04 14:20:02 +0100  49)      * @param negBtn            Resource id for the text of the negative button.
45588662 src/com/owncloud/android/ui/fragment/ConfirmationDialogFragment.java (David A. Velasco  2012-12-04 14:20:02 +0100  50)      * @return                  Dialog ready to show.
45588662 src/com/owncloud/android/ui/fragment/ConfirmationDialogFragment.java (David A. Velasco  2012-12-04 14:20:02 +0100  51)      */
0cced280 src/eu/alefzero/owncloud/ui/fragment/ConfirmationDialogFragment.java (Bartek Przybylski 2012-07-28 21:15:41 +0200  52)     public static ConfirmationDialogFragment newInstance(int string_id, String[] arguments, int posBtn, int neuBtn, int negBtn) {
6701c984 src/eu/alefzero/owncloud/ui/fragment/ConfirmationDialogFragment.java (David A. Velasco  2012-07-11 18:46:47 +0200  53)         ConfirmationDialogFragment frag = new ConfirmationDialogFragment();
6701c984 src/eu/alefzero/owncloud/ui/fragment/ConfirmationDialogFragment.java (David A. Velasco  2012-07-11 18:46:47 +0200  54)         Bundle args = new Bundle();
7f75f13f src/eu/alefzero/owncloud/ui/fragment/ConfirmationDialogFragment.java (Bartek Przybylski 2012-07-23 19:09:44 +0200  55)         args.putInt(ARG_CONF_RESOURCE_ID, string_id);
7f75f13f src/eu/alefzero/owncloud/ui/fragment/ConfirmationDialogFragment.java (Bartek Przybylski 2012-07-23 19:09:44 +0200  56)         args.putStringArray(ARG_CONF_ARGUMENTS, arguments);
0cced280 src/eu/alefzero/owncloud/ui/fragment/ConfirmationDialogFragment.java (Bartek Przybylski 2012-07-28 21:15:41 +0200  57)         args.putInt(ARG_POSITIVE_BTN_RES, posBtn);
0cced280 src/eu/alefzero/owncloud/ui/fragment/ConfirmationDialogFragment.java (Bartek Przybylski 2012-07-28 21:15:41 +0200  58)         args.putInt(ARG_NEUTRAL_BTN_RES, neuBtn);
0cced280 src/eu/alefzero/owncloud/ui/fragment/ConfirmationDialogFragment.java (Bartek Przybylski 2012-07-28 21:15:41 +0200  59)         args.putInt(ARG_NEGATIVE_BTN_RES, negBtn);
6701c984 src/eu/alefzero/owncloud/ui/fragment/ConfirmationDialogFragment.java (David A. Velasco  2012-07-11 18:46:47 +0200  60)         frag.setArguments(args);
6701c984 src/eu/alefzero/owncloud/ui/fragment/ConfirmationDialogFragment.java (David A. Velasco  2012-07-11 18:46:47 +0200  61)         return frag;
6701c984 src/eu/alefzero/owncloud/ui/fragment/ConfirmationDialogFragment.java (David A. Velasco  2012-07-11 18:46:47 +0200  62)     }
6701c984 src/eu/alefzero/owncloud/ui/fragment/ConfirmationDialogFragment.java (David A. Velasco  2012-07-11 18:46:47 +0200  63)     
6701c984 src/eu/alefzero/owncloud/ui/fragment/ConfirmationDialogFragment.java (David A. Velasco  2012-07-11 18:46:47 +0200  64)     public void setOnConfirmationListener(ConfirmationDialogFragmentListener listener) {
6701c984 src/eu/alefzero/owncloud/ui/fragment/ConfirmationDialogFragment.java (David A. Velasco  2012-07-11 18:46:47 +0200  65)         mListener = listener;
6701c984 src/eu/alefzero/owncloud/ui/fragment/ConfirmationDialogFragment.java (David A. Velasco  2012-07-11 18:46:47 +0200  66)     }
6701c984 src/eu/alefzero/owncloud/ui/fragment/ConfirmationDialogFragment.java (David A. Velasco  2012-07-11 18:46:47 +0200  67) 
6701c984 src/eu/alefzero/owncloud/ui/fragment/ConfirmationDialogFragment.java (David A. Velasco  2012-07-11 18:46:47 +0200  68)     @Override
6701c984 src/eu/alefzero/owncloud/ui/fragment/ConfirmationDialogFragment.java (David A. Velasco  2012-07-11 18:46:47 +0200  69)     public Dialog onCreateDialog(Bundle savedInstanceState) {
7f75f13f src/eu/alefzero/owncloud/ui/fragment/ConfirmationDialogFragment.java (Bartek Przybylski 2012-07-23 19:09:44 +0200  70)         Object[] confirmationTarget = getArguments().getStringArray(ARG_CONF_ARGUMENTS);
7f75f13f src/eu/alefzero/owncloud/ui/fragment/ConfirmationDialogFragment.java (Bartek Przybylski 2012-07-23 19:09:44 +0200  71)         int resourceId = getArguments().getInt(ARG_CONF_RESOURCE_ID, -1);
0cced280 src/eu/alefzero/owncloud/ui/fragment/ConfirmationDialogFragment.java (Bartek Przybylski 2012-07-28 21:15:41 +0200  72)         int posBtn = getArguments().getInt(ARG_POSITIVE_BTN_RES, -1);
0cced280 src/eu/alefzero/owncloud/ui/fragment/ConfirmationDialogFragment.java (Bartek Przybylski 2012-07-28 21:15:41 +0200  73)         int neuBtn = getArguments().getInt(ARG_NEUTRAL_BTN_RES, -1);
0cced280 src/eu/alefzero/owncloud/ui/fragment/ConfirmationDialogFragment.java (Bartek Przybylski 2012-07-28 21:15:41 +0200  74)         int negBtn = getArguments().getInt(ARG_NEGATIVE_BTN_RES, -1);
0cced280 src/eu/alefzero/owncloud/ui/fragment/ConfirmationDialogFragment.java (Bartek Przybylski 2012-07-28 21:15:41 +0200  75)         
7f75f13f src/eu/alefzero/owncloud/ui/fragment/ConfirmationDialogFragment.java (Bartek Przybylski 2012-07-23 19:09:44 +0200  76)         if (confirmationTarget == null || resourceId == -1) {
8e36e7cc src/com/owncloud/android/ui/fragment/ConfirmationDialogFragment.java (zerginator        2013-03-12 07:56:27 +0100  77)             Log_OC.wtf(getTag(), "Calling confirmation dialog without resource or arguments");
7f75f13f src/eu/alefzero/owncloud/ui/fragment/ConfirmationDialogFragment.java (Bartek Przybylski 2012-07-23 19:09:44 +0200  78)             return null;
7f75f13f src/eu/alefzero/owncloud/ui/fragment/ConfirmationDialogFragment.java (Bartek Przybylski 2012-07-23 19:09:44 +0200  79)         }
6701c984 src/eu/alefzero/owncloud/ui/fragment/ConfirmationDialogFragment.java (David A. Velasco  2012-07-11 18:46:47 +0200  80) 
0cced280 src/eu/alefzero/owncloud/ui/fragment/ConfirmationDialogFragment.java (Bartek Przybylski 2012-07-28 21:15:41 +0200  81)         AlertDialog.Builder builder = new AlertDialog.Builder(getActivity())
0cced280 src/eu/alefzero/owncloud/ui/fragment/ConfirmationDialogFragment.java (Bartek Przybylski 2012-07-28 21:15:41 +0200  82)             .setIcon(android.R.drawable.ic_dialog_alert)
0cced280 src/eu/alefzero/owncloud/ui/fragment/ConfirmationDialogFragment.java (Bartek Przybylski 2012-07-28 21:15:41 +0200  83)             .setMessage(String.format(getString(resourceId), confirmationTarget))
0cced280 src/eu/alefzero/owncloud/ui/fragment/ConfirmationDialogFragment.java (Bartek Przybylski 2012-07-28 21:15:41 +0200  84)             .setTitle(android.R.string.dialog_alert_title);
e8852fa7 src/eu/alefzero/owncloud/ui/fragment/ConfirmationDialogFragment.java (David A. Velasco  2012-07-30 09:46:24 +0200  85)         if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.HONEYCOMB) {
e8852fa7 src/eu/alefzero/owncloud/ui/fragment/ConfirmationDialogFragment.java (David A. Velasco  2012-07-30 09:46:24 +0200  86)             builder.setIconAttribute(android.R.attr.alertDialogIcon);
e8852fa7 src/eu/alefzero/owncloud/ui/fragment/ConfirmationDialogFragment.java (David A. Velasco  2012-07-30 09:46:24 +0200  87)         }
0cced280 src/eu/alefzero/owncloud/ui/fragment/ConfirmationDialogFragment.java (Bartek Przybylski 2012-07-28 21:15:41 +0200  88)         
0cced280 src/eu/alefzero/owncloud/ui/fragment/ConfirmationDialogFragment.java (Bartek Przybylski 2012-07-28 21:15:41 +0200  89)         if (posBtn != -1)
0cced280 src/eu/alefzero/owncloud/ui/fragment/ConfirmationDialogFragment.java (Bartek Przybylski 2012-07-28 21:15:41 +0200  90)             builder.setPositiveButton(posBtn,
6701c984 src/eu/alefzero/owncloud/ui/fragment/ConfirmationDialogFragment.java (David A. Velasco  2012-07-11 18:46:47 +0200  91)                     new DialogInterface.OnClickListener() {
6701c984 src/eu/alefzero/owncloud/ui/fragment/ConfirmationDialogFragment.java (David A. Velasco  2012-07-11 18:46:47 +0200  92)                         public void onClick(DialogInterface dialog, int whichButton) {
0cced280 src/eu/alefzero/owncloud/ui/fragment/ConfirmationDialogFragment.java (Bartek Przybylski 2012-07-28 21:15:41 +0200  93)                             mListener.onConfirmation(getTag()); 
f2474ae2 src/com/owncloud/android/ui/fragment/ConfirmationDialogFragment.java (David A. Velasco  2013-02-18 11:23:18 +0100  94)                             dialog.dismiss();
6701c984 src/eu/alefzero/owncloud/ui/fragment/ConfirmationDialogFragment.java (David A. Velasco  2012-07-11 18:46:47 +0200  95)                         }
0cced280 src/eu/alefzero/owncloud/ui/fragment/ConfirmationDialogFragment.java (Bartek Przybylski 2012-07-28 21:15:41 +0200  96)                     });
0cced280 src/eu/alefzero/owncloud/ui/fragment/ConfirmationDialogFragment.java (Bartek Przybylski 2012-07-28 21:15:41 +0200  97)         if (neuBtn != -1)
0cced280 src/eu/alefzero/owncloud/ui/fragment/ConfirmationDialogFragment.java (Bartek Przybylski 2012-07-28 21:15:41 +0200  98)             builder.setNeutralButton(neuBtn,
6701c984 src/eu/alefzero/owncloud/ui/fragment/ConfirmationDialogFragment.java (David A. Velasco  2012-07-11 18:46:47 +0200  99)                     new DialogInterface.OnClickListener() {
6701c984 src/eu/alefzero/owncloud/ui/fragment/ConfirmationDialogFragment.java (David A. Velasco  2012-07-11 18:46:47 +0200 100)                         public void onClick(DialogInterface dialog, int whichButton) {
0cced280 src/eu/alefzero/owncloud/ui/fragment/ConfirmationDialogFragment.java (Bartek Przybylski 2012-07-28 21:15:41 +0200 101)                             mListener.onNeutral(getTag()); 
f2474ae2 src/com/owncloud/android/ui/fragment/ConfirmationDialogFragment.java (David A. Velasco  2013-02-18 11:23:18 +0100 102)                             dialog.dismiss();
0cced280 src/eu/alefzero/owncloud/ui/fragment/ConfirmationDialogFragment.java (Bartek Przybylski 2012-07-28 21:15:41 +0200 103)                         }
0cced280 src/eu/alefzero/owncloud/ui/fragment/ConfirmationDialogFragment.java (Bartek Przybylski 2012-07-28 21:15:41 +0200 104)                     });
0cced280 src/eu/alefzero/owncloud/ui/fragment/ConfirmationDialogFragment.java (Bartek Przybylski 2012-07-28 21:15:41 +0200 105)         if (negBtn != -1)
0cced280 src/eu/alefzero/owncloud/ui/fragment/ConfirmationDialogFragment.java (Bartek Przybylski 2012-07-28 21:15:41 +0200 106)             builder.setNegativeButton(negBtn,
0cced280 src/eu/alefzero/owncloud/ui/fragment/ConfirmationDialogFragment.java (Bartek Przybylski 2012-07-28 21:15:41 +0200 107)                     new DialogInterface.OnClickListener() {
0cced280 src/eu/alefzero/owncloud/ui/fragment/ConfirmationDialogFragment.java (Bartek Przybylski 2012-07-28 21:15:41 +0200 108)                         @Override
0cced280 src/eu/alefzero/owncloud/ui/fragment/ConfirmationDialogFragment.java (Bartek Przybylski 2012-07-28 21:15:41 +0200 109)                         public void onClick(DialogInterface dialog, int which) {
0cced280 src/eu/alefzero/owncloud/ui/fragment/ConfirmationDialogFragment.java (Bartek Przybylski 2012-07-28 21:15:41 +0200 110)                             mListener.onCancel(getTag());
f2474ae2 src/com/owncloud/android/ui/fragment/ConfirmationDialogFragment.java (David A. Velasco  2013-02-18 11:23:18 +0100 111)                             dialog.dismiss();
6701c984 src/eu/alefzero/owncloud/ui/fragment/ConfirmationDialogFragment.java (David A. Velasco  2012-07-11 18:46:47 +0200 112)                         }
0cced280 src/eu/alefzero/owncloud/ui/fragment/ConfirmationDialogFragment.java (Bartek Przybylski 2012-07-28 21:15:41 +0200 113)                     });
0cced280 src/eu/alefzero/owncloud/ui/fragment/ConfirmationDialogFragment.java (Bartek Przybylski 2012-07-28 21:15:41 +0200 114)       return builder.create();
6701c984 src/eu/alefzero/owncloud/ui/fragment/ConfirmationDialogFragment.java (David A. Velasco  2012-07-11 18:46:47 +0200 115)     }
6701c984 src/eu/alefzero/owncloud/ui/fragment/ConfirmationDialogFragment.java (David A. Velasco  2012-07-11 18:46:47 +0200 116)     
6701c984 src/eu/alefzero/owncloud/ui/fragment/ConfirmationDialogFragment.java (David A. Velasco  2012-07-11 18:46:47 +0200 117)     
6701c984 src/eu/alefzero/owncloud/ui/fragment/ConfirmationDialogFragment.java (David A. Velasco  2012-07-11 18:46:47 +0200 118)     public interface ConfirmationDialogFragmentListener {
0cced280 src/eu/alefzero/owncloud/ui/fragment/ConfirmationDialogFragment.java (Bartek Przybylski 2012-07-28 21:15:41 +0200 119)         public void onConfirmation(String callerTag);
0cced280 src/eu/alefzero/owncloud/ui/fragment/ConfirmationDialogFragment.java (Bartek Przybylski 2012-07-28 21:15:41 +0200 120)         public void onNeutral(String callerTag);
0cced280 src/eu/alefzero/owncloud/ui/fragment/ConfirmationDialogFragment.java (Bartek Przybylski 2012-07-28 21:15:41 +0200 121)         public void onCancel(String callerTag);
6701c984 src/eu/alefzero/owncloud/ui/fragment/ConfirmationDialogFragment.java (David A. Velasco  2012-07-11 18:46:47 +0200 122)     }
6701c984 src/eu/alefzero/owncloud/ui/fragment/ConfirmationDialogFragment.java (David A. Velasco  2012-07-11 18:46:47 +0200 123)     
6701c984 src/eu/alefzero/owncloud/ui/fragment/ConfirmationDialogFragment.java (David A. Velasco  2012-07-11 18:46:47 +0200 124) }
6701c984 src/eu/alefzero/owncloud/ui/fragment/ConfirmationDialogFragment.java (David A. Velasco  2012-07-11 18:46:47 +0200 125) 
