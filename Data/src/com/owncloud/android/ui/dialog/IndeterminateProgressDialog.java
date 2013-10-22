bb257ec7 (David A. Velasco 2013-02-07 18:45:10 +0100  1) /* ownCloud Android client application
bb257ec7 (David A. Velasco 2013-02-07 18:45:10 +0100  2)  *   Copyright (C) 2012-2013 ownCloud Inc.
bb257ec7 (David A. Velasco 2013-02-07 18:45:10 +0100  3)  *
bb257ec7 (David A. Velasco 2013-02-07 18:45:10 +0100  4)  *   This program is free software: you can redistribute it and/or modify
bdc0332c (David A. Velasco 2013-04-17 12:26:13 +0200  5)  *   it under the terms of the GNU General Public License version 2,
bdc0332c (David A. Velasco 2013-04-17 12:26:13 +0200  6)  *   as published by the Free Software Foundation.
bb257ec7 (David A. Velasco 2013-02-07 18:45:10 +0100  7)  *
bb257ec7 (David A. Velasco 2013-02-07 18:45:10 +0100  8)  *   This program is distributed in the hope that it will be useful,
bb257ec7 (David A. Velasco 2013-02-07 18:45:10 +0100  9)  *   but WITHOUT ANY WARRANTY; without even the implied warranty of
bb257ec7 (David A. Velasco 2013-02-07 18:45:10 +0100 10)  *   MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
bb257ec7 (David A. Velasco 2013-02-07 18:45:10 +0100 11)  *   GNU General Public License for more details.
bb257ec7 (David A. Velasco 2013-02-07 18:45:10 +0100 12)  *
bb257ec7 (David A. Velasco 2013-02-07 18:45:10 +0100 13)  *   You should have received a copy of the GNU General Public License
bb257ec7 (David A. Velasco 2013-02-07 18:45:10 +0100 14)  *   along with this program.  If not, see <http://www.gnu.org/licenses/>.
bb257ec7 (David A. Velasco 2013-02-07 18:45:10 +0100 15)  *
bb257ec7 (David A. Velasco 2013-02-07 18:45:10 +0100 16)  */
bb257ec7 (David A. Velasco 2013-02-07 18:45:10 +0100 17) 
f68d10ab (David A. Velasco 2012-12-04 12:49:18 +0100 18) package com.owncloud.android.ui.dialog;
f68d10ab (David A. Velasco 2012-12-04 12:49:18 +0100 19) 
f68d10ab (David A. Velasco 2012-12-04 12:49:18 +0100 20) import android.app.Dialog;
f68d10ab (David A. Velasco 2012-12-04 12:49:18 +0100 21) import android.app.ProgressDialog;
f68d10ab (David A. Velasco 2012-12-04 12:49:18 +0100 22) import android.content.DialogInterface;
f68d10ab (David A. Velasco 2012-12-04 12:49:18 +0100 23) import android.content.DialogInterface.OnKeyListener;
f68d10ab (David A. Velasco 2012-12-04 12:49:18 +0100 24) import android.os.Bundle;
f68d10ab (David A. Velasco 2012-12-04 12:49:18 +0100 25) import android.view.KeyEvent;
f68d10ab (David A. Velasco 2012-12-04 12:49:18 +0100 26) 
f68d10ab (David A. Velasco 2012-12-04 12:49:18 +0100 27) import com.actionbarsherlock.app.SherlockDialogFragment;
f68d10ab (David A. Velasco 2012-12-04 12:49:18 +0100 28) import com.owncloud.android.R;
f68d10ab (David A. Velasco 2012-12-04 12:49:18 +0100 29) 
f68d10ab (David A. Velasco 2012-12-04 12:49:18 +0100 30) public class IndeterminateProgressDialog extends SherlockDialogFragment {
f68d10ab (David A. Velasco 2012-12-04 12:49:18 +0100 31) 
f68d10ab (David A. Velasco 2012-12-04 12:49:18 +0100 32)     private static final String ARG_MESSAGE_ID = IndeterminateProgressDialog.class.getCanonicalName() + ".ARG_MESSAGE_ID";
f68d10ab (David A. Velasco 2012-12-04 12:49:18 +0100 33)     private static final String ARG_CANCELABLE = IndeterminateProgressDialog.class.getCanonicalName() + ".ARG_CANCELABLE";
f68d10ab (David A. Velasco 2012-12-04 12:49:18 +0100 34) 
f68d10ab (David A. Velasco 2012-12-04 12:49:18 +0100 35) 
f68d10ab (David A. Velasco 2012-12-04 12:49:18 +0100 36)     /**
f68d10ab (David A. Velasco 2012-12-04 12:49:18 +0100 37)      * Public factory method to get dialog instances.
f68d10ab (David A. Velasco 2012-12-04 12:49:18 +0100 38)      * 
f68d10ab (David A. Velasco 2012-12-04 12:49:18 +0100 39)      * @param messageId     Resource id for a message to show in the dialog.
f68d10ab (David A. Velasco 2012-12-04 12:49:18 +0100 40)      * @param cancelable    If 'true', the dialog can be cancelled by the user input (BACK button, touch outside...)
f68d10ab (David A. Velasco 2012-12-04 12:49:18 +0100 41)      * @return              New dialog instance, ready to show.
f68d10ab (David A. Velasco 2012-12-04 12:49:18 +0100 42)      */
f68d10ab (David A. Velasco 2012-12-04 12:49:18 +0100 43)     public static IndeterminateProgressDialog newInstance(int messageId, boolean cancelable) {
f68d10ab (David A. Velasco 2012-12-04 12:49:18 +0100 44)         IndeterminateProgressDialog fragment = new IndeterminateProgressDialog();
f68d10ab (David A. Velasco 2012-12-04 12:49:18 +0100 45)         Bundle args = new Bundle();
f68d10ab (David A. Velasco 2012-12-04 12:49:18 +0100 46)         args.putInt(ARG_MESSAGE_ID, messageId);
f68d10ab (David A. Velasco 2012-12-04 12:49:18 +0100 47)         args.putBoolean(ARG_CANCELABLE, cancelable);
f68d10ab (David A. Velasco 2012-12-04 12:49:18 +0100 48)         fragment.setArguments(args);
f68d10ab (David A. Velasco 2012-12-04 12:49:18 +0100 49)         return fragment;
f68d10ab (David A. Velasco 2012-12-04 12:49:18 +0100 50)     }
f68d10ab (David A. Velasco 2012-12-04 12:49:18 +0100 51) 
f68d10ab (David A. Velasco 2012-12-04 12:49:18 +0100 52)     
f68d10ab (David A. Velasco 2012-12-04 12:49:18 +0100 53)     /**
f68d10ab (David A. Velasco 2012-12-04 12:49:18 +0100 54)      * {@inheritDoc}
f68d10ab (David A. Velasco 2012-12-04 12:49:18 +0100 55)      */
f68d10ab (David A. Velasco 2012-12-04 12:49:18 +0100 56)     @Override
f68d10ab (David A. Velasco 2012-12-04 12:49:18 +0100 57)     public Dialog onCreateDialog(Bundle savedInstanceState) {
f68d10ab (David A. Velasco 2012-12-04 12:49:18 +0100 58)         /// create indeterminate progress dialog
f68d10ab (David A. Velasco 2012-12-04 12:49:18 +0100 59)         final ProgressDialog dialog = new ProgressDialog(getActivity());
f68d10ab (David A. Velasco 2012-12-04 12:49:18 +0100 60)         dialog.setIndeterminate(true);
f68d10ab (David A. Velasco 2012-12-04 12:49:18 +0100 61)         
f68d10ab (David A. Velasco 2012-12-04 12:49:18 +0100 62)         /// set message
24dd5136 (David A. Velasco 2013-02-19 13:28:01 +0100 63)         int messageId = getArguments().getInt(ARG_MESSAGE_ID, R.string.placeholder_sentence);
f68d10ab (David A. Velasco 2012-12-04 12:49:18 +0100 64)         dialog.setMessage(getString(messageId));
f68d10ab (David A. Velasco 2012-12-04 12:49:18 +0100 65)         
f68d10ab (David A. Velasco 2012-12-04 12:49:18 +0100 66)         /// set cancellation behavior
f68d10ab (David A. Velasco 2012-12-04 12:49:18 +0100 67)         boolean cancelable = getArguments().getBoolean(ARG_CANCELABLE, false);
f68d10ab (David A. Velasco 2012-12-04 12:49:18 +0100 68)         if (!cancelable) {
f68d10ab (David A. Velasco 2012-12-04 12:49:18 +0100 69)             dialog.setCancelable(false);
f68d10ab (David A. Velasco 2012-12-04 12:49:18 +0100 70)             // disable the back button
f68d10ab (David A. Velasco 2012-12-04 12:49:18 +0100 71)             OnKeyListener keyListener = new OnKeyListener() {
f68d10ab (David A. Velasco 2012-12-04 12:49:18 +0100 72)                 @Override
f68d10ab (David A. Velasco 2012-12-04 12:49:18 +0100 73)                 public boolean onKey(DialogInterface dialog, int keyCode,
f68d10ab (David A. Velasco 2012-12-04 12:49:18 +0100 74)                         KeyEvent event) {
f68d10ab (David A. Velasco 2012-12-04 12:49:18 +0100 75) 
f68d10ab (David A. Velasco 2012-12-04 12:49:18 +0100 76)                     if( keyCode == KeyEvent.KEYCODE_BACK){                  
f68d10ab (David A. Velasco 2012-12-04 12:49:18 +0100 77)                         return true;
f68d10ab (David A. Velasco 2012-12-04 12:49:18 +0100 78)                     }
f68d10ab (David A. Velasco 2012-12-04 12:49:18 +0100 79)                     return false;
f68d10ab (David A. Velasco 2012-12-04 12:49:18 +0100 80)                 }
f68d10ab (David A. Velasco 2012-12-04 12:49:18 +0100 81) 
f68d10ab (David A. Velasco 2012-12-04 12:49:18 +0100 82)             };
f68d10ab (David A. Velasco 2012-12-04 12:49:18 +0100 83)             dialog.setOnKeyListener(keyListener);
f68d10ab (David A. Velasco 2012-12-04 12:49:18 +0100 84)         }
f68d10ab (David A. Velasco 2012-12-04 12:49:18 +0100 85)         
f68d10ab (David A. Velasco 2012-12-04 12:49:18 +0100 86)         return dialog;
f68d10ab (David A. Velasco 2012-12-04 12:49:18 +0100 87)     }    
f68d10ab (David A. Velasco 2012-12-04 12:49:18 +0100 88)     
f68d10ab (David A. Velasco 2012-12-04 12:49:18 +0100 89) }
f68d10ab (David A. Velasco 2012-12-04 12:49:18 +0100 90) 
f68d10ab (David A. Velasco 2012-12-04 12:49:18 +0100 91) 
