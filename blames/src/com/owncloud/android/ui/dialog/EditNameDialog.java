5af0fb44 (David A. Velasco 2012-10-26 11:34:20 +0200   1) /* ownCloud Android client application
5af0fb44 (David A. Velasco 2012-10-26 11:34:20 +0200   2)  *   Copyright (C) 2011  Bartek Przybylski
bb257ec7 (David A. Velasco 2013-02-07 18:45:10 +0100   3)  *   Copyright (C) 2012-2013 ownCloud Inc.
5af0fb44 (David A. Velasco 2012-10-26 11:34:20 +0200   4)  *
5af0fb44 (David A. Velasco 2012-10-26 11:34:20 +0200   5)  *   This program is free software: you can redistribute it and/or modify
bdc0332c (David A. Velasco 2013-04-17 12:26:13 +0200   6)  *   it under the terms of the GNU General Public License version 2,
bdc0332c (David A. Velasco 2013-04-17 12:26:13 +0200   7)  *   as published by the Free Software Foundation.
5af0fb44 (David A. Velasco 2012-10-26 11:34:20 +0200   8)  *
5af0fb44 (David A. Velasco 2012-10-26 11:34:20 +0200   9)  *   This program is distributed in the hope that it will be useful,
5af0fb44 (David A. Velasco 2012-10-26 11:34:20 +0200  10)  *   but WITHOUT ANY WARRANTY; without even the implied warranty of
5af0fb44 (David A. Velasco 2012-10-26 11:34:20 +0200  11)  *   MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
5af0fb44 (David A. Velasco 2012-10-26 11:34:20 +0200  12)  *   GNU General Public License for more details.
5af0fb44 (David A. Velasco 2012-10-26 11:34:20 +0200  13)  *
5af0fb44 (David A. Velasco 2012-10-26 11:34:20 +0200  14)  *   You should have received a copy of the GNU General Public License
5af0fb44 (David A. Velasco 2012-10-26 11:34:20 +0200  15)  *   along with this program.  If not, see <http://www.gnu.org/licenses/>.
5af0fb44 (David A. Velasco 2012-10-26 11:34:20 +0200  16)  *
5af0fb44 (David A. Velasco 2012-10-26 11:34:20 +0200  17)  */
5af0fb44 (David A. Velasco 2012-10-26 11:34:20 +0200  18) 
5af0fb44 (David A. Velasco 2012-10-26 11:34:20 +0200  19) package com.owncloud.android.ui.dialog;
5af0fb44 (David A. Velasco 2012-10-26 11:34:20 +0200  20) 
d49bfd0c (David A. Velasco 2012-11-22 10:24:26 +0100  21) import android.app.AlertDialog;
d49bfd0c (David A. Velasco 2012-11-22 10:24:26 +0100  22) import android.app.Dialog;
d49bfd0c (David A. Velasco 2012-11-22 10:24:26 +0100  23) import android.content.DialogInterface;
5af0fb44 (David A. Velasco 2012-10-26 11:34:20 +0200  24) import android.os.Bundle;
5af0fb44 (David A. Velasco 2012-10-26 11:34:20 +0200  25) import android.view.LayoutInflater;
5af0fb44 (David A. Velasco 2012-10-26 11:34:20 +0200  26) import android.view.View;
5af0fb44 (David A. Velasco 2012-10-26 11:34:20 +0200  27) import android.view.WindowManager.LayoutParams;
5bcfed8e (David A. Velasco 2013-04-15 14:21:10 +0200  28) import android.widget.EditText;
5af0fb44 (David A. Velasco 2012-10-26 11:34:20 +0200  29) import android.widget.TextView;
5af0fb44 (David A. Velasco 2012-10-26 11:34:20 +0200  30) 
5af0fb44 (David A. Velasco 2012-10-26 11:34:20 +0200  31) import com.actionbarsherlock.app.SherlockDialogFragment;
5af0fb44 (David A. Velasco 2012-10-26 11:34:20 +0200  32) import com.owncloud.android.R;
5af0fb44 (David A. Velasco 2012-10-26 11:34:20 +0200  33) 
5af0fb44 (David A. Velasco 2012-10-26 11:34:20 +0200  34) 
5af0fb44 (David A. Velasco 2012-10-26 11:34:20 +0200  35) /**
d49bfd0c (David A. Velasco 2012-11-22 10:24:26 +0100  36)  * Dialog to request the user to input a name, optionally initialized with a former name.
5af0fb44 (David A. Velasco 2012-10-26 11:34:20 +0200  37)  * 
5af0fb44 (David A. Velasco 2012-10-26 11:34:20 +0200  38)  * @author Bartek Przybylski
d49bfd0c (David A. Velasco 2012-11-22 10:24:26 +0100  39)  * @author David A. Velasco
5af0fb44 (David A. Velasco 2012-10-26 11:34:20 +0200  40)  */
d49bfd0c (David A. Velasco 2012-11-22 10:24:26 +0100  41) public class EditNameDialog extends SherlockDialogFragment implements DialogInterface.OnClickListener {
5af0fb44 (David A. Velasco 2012-10-26 11:34:20 +0200  42) 
85e9a40d (David A. Velasco 2012-11-08 17:21:45 +0100  43)     public static final String TAG = EditNameDialog.class.getSimpleName();
85e9a40d (David A. Velasco 2012-11-08 17:21:45 +0100  44)     
5bcfed8e (David A. Velasco 2013-04-15 14:21:10 +0200  45)     protected static final String ARG_TITLE = "TITLE";
5bcfed8e (David A. Velasco 2013-04-15 14:21:10 +0200  46)     protected static final String ARG_NAME = "NAME";
5bcfed8e (David A. Velasco 2013-04-15 14:21:10 +0200  47)     protected static final String ARG_SELECTION_START = "SELECTION_START";
5bcfed8e (David A. Velasco 2013-04-15 14:21:10 +0200  48)     protected static final String ARG_SELECTION_END = "SELECTION_END";
d49bfd0c (David A. Velasco 2012-11-22 10:24:26 +0100  49)     
5af0fb44 (David A. Velasco 2012-10-26 11:34:20 +0200  50)     private String mNewFilename;
5af0fb44 (David A. Velasco 2012-10-26 11:34:20 +0200  51)     private boolean mResult;
5af0fb44 (David A. Velasco 2012-10-26 11:34:20 +0200  52)     private EditNameDialogListener mListener;
5af0fb44 (David A. Velasco 2012-10-26 11:34:20 +0200  53)     
d49bfd0c (David A. Velasco 2012-11-22 10:24:26 +0100  54)     /**
d49bfd0c (David A. Velasco 2012-11-22 10:24:26 +0100  55)      * Public factory method to get dialog instances.
d49bfd0c (David A. Velasco 2012-11-22 10:24:26 +0100  56)      * 
5bcfed8e (David A. Velasco 2013-04-15 14:21:10 +0200  57)      * @param title             Text to show as title in the dialog.
5bcfed8e (David A. Velasco 2013-04-15 14:21:10 +0200  58)      * @param name              Optional text to include in the text input field when the dialog is shown.
5bcfed8e (David A. Velasco 2013-04-15 14:21:10 +0200  59)      * @param listener          Instance to notify when the dialog is dismissed.
5bcfed8e (David A. Velasco 2013-04-15 14:21:10 +0200  60)      * @param selectionStart    Index to the first character to be selected in the input field; negative value for none
5bcfed8e (David A. Velasco 2013-04-15 14:21:10 +0200  61)      * @param selectionEnd      Index to the last character to be selected in the input field; negative value for none
d49bfd0c (David A. Velasco 2012-11-22 10:24:26 +0100  62)      * @return              New dialog instance, ready to show.
d49bfd0c (David A. Velasco 2012-11-22 10:24:26 +0100  63)      */
5bcfed8e (David A. Velasco 2013-04-15 14:21:10 +0200  64)     static public EditNameDialog newInstance(String title, String name, int selectionStart, int selectionEnd, EditNameDialogListener listener) {
5af0fb44 (David A. Velasco 2012-10-26 11:34:20 +0200  65)         EditNameDialog f = new EditNameDialog();
5af0fb44 (David A. Velasco 2012-10-26 11:34:20 +0200  66)         Bundle args = new Bundle();
d49bfd0c (David A. Velasco 2012-11-22 10:24:26 +0100  67)         args.putString(ARG_TITLE, title);
d49bfd0c (David A. Velasco 2012-11-22 10:24:26 +0100  68)         args.putString(ARG_NAME, name);
5bcfed8e (David A. Velasco 2013-04-15 14:21:10 +0200  69)         args.putInt(ARG_SELECTION_START, selectionStart);
5bcfed8e (David A. Velasco 2013-04-15 14:21:10 +0200  70)         args.putInt(ARG_SELECTION_END, selectionEnd);
5af0fb44 (David A. Velasco 2012-10-26 11:34:20 +0200  71)         f.setArguments(args);
d49bfd0c (David A. Velasco 2012-11-22 10:24:26 +0100  72)         f.setOnDismissListener(listener);
5af0fb44 (David A. Velasco 2012-10-26 11:34:20 +0200  73)         return f;
5af0fb44 (David A. Velasco 2012-10-26 11:34:20 +0200  74)     }
5af0fb44 (David A. Velasco 2012-10-26 11:34:20 +0200  75)     
d49bfd0c (David A. Velasco 2012-11-22 10:24:26 +0100  76)     
d49bfd0c (David A. Velasco 2012-11-22 10:24:26 +0100  77)     /**
d49bfd0c (David A. Velasco 2012-11-22 10:24:26 +0100  78)      * {@inheritDoc}
d49bfd0c (David A. Velasco 2012-11-22 10:24:26 +0100  79)      */
5af0fb44 (David A. Velasco 2012-10-26 11:34:20 +0200  80)     @Override
d49bfd0c (David A. Velasco 2012-11-22 10:24:26 +0100  81)     public Dialog onCreateDialog(Bundle savedInstanceState) {
d49bfd0c (David A. Velasco 2012-11-22 10:24:26 +0100  82)         String currentName = getArguments().getString(ARG_NAME);
5af0fb44 (David A. Velasco 2012-10-26 11:34:20 +0200  83)         if (currentName == null)
5af0fb44 (David A. Velasco 2012-10-26 11:34:20 +0200  84)             currentName = "";
d49bfd0c (David A. Velasco 2012-11-22 10:24:26 +0100  85)         String title = getArguments().getString(ARG_TITLE);
d49bfd0c (David A. Velasco 2012-11-22 10:24:26 +0100  86)         
d49bfd0c (David A. Velasco 2012-11-22 10:24:26 +0100  87)         // Inflate the layout for the dialog
d49bfd0c (David A. Velasco 2012-11-22 10:24:26 +0100  88)         LayoutInflater inflater = getSherlockActivity().getLayoutInflater();
d49bfd0c (David A. Velasco 2012-11-22 10:24:26 +0100  89)         View v = inflater.inflate(R.layout.edit_box_dialog, null);  // null parent view because it will go in the dialog layout
5bcfed8e (David A. Velasco 2013-04-15 14:21:10 +0200  90)         EditText inputText = ((EditText)v.findViewById(R.id.user_input));
d49bfd0c (David A. Velasco 2012-11-22 10:24:26 +0100  91)         inputText.setText(currentName);
5af0fb44 (David A. Velasco 2012-10-26 11:34:20 +0200  92)         
d49bfd0c (David A. Velasco 2012-11-22 10:24:26 +0100  93)         // Set it to the dialog 
d49bfd0c (David A. Velasco 2012-11-22 10:24:26 +0100  94)         AlertDialog.Builder builder = new AlertDialog.Builder(getSherlockActivity());
d49bfd0c (David A. Velasco 2012-11-22 10:24:26 +0100  95)         builder.setView(v)
d49bfd0c (David A. Velasco 2012-11-22 10:24:26 +0100  96)                .setPositiveButton(R.string.common_ok, this)
d49bfd0c (David A. Velasco 2012-11-22 10:24:26 +0100  97)                .setNegativeButton(R.string.common_cancel, this);
5af0fb44 (David A. Velasco 2012-10-26 11:34:20 +0200  98) 
d49bfd0c (David A. Velasco 2012-11-22 10:24:26 +0100  99)         if (title != null) {
d49bfd0c (David A. Velasco 2012-11-22 10:24:26 +0100 100)             builder.setTitle(title);
d49bfd0c (David A. Velasco 2012-11-22 10:24:26 +0100 101)         }
d49bfd0c (David A. Velasco 2012-11-22 10:24:26 +0100 102)         
5af0fb44 (David A. Velasco 2012-10-26 11:34:20 +0200 103)         mResult = false;
d49bfd0c (David A. Velasco 2012-11-22 10:24:26 +0100 104)         
d49bfd0c (David A. Velasco 2012-11-22 10:24:26 +0100 105)         Dialog d = builder.create();
d49bfd0c (David A. Velasco 2012-11-22 10:24:26 +0100 106) 
d49bfd0c (David A. Velasco 2012-11-22 10:24:26 +0100 107)         inputText.requestFocus();
5bcfed8e (David A. Velasco 2013-04-15 14:21:10 +0200 108)         int selectionStart = getArguments().getInt(ARG_SELECTION_START, -1);
5bcfed8e (David A. Velasco 2013-04-15 14:21:10 +0200 109)         int selectionEnd = getArguments().getInt(ARG_SELECTION_END, -1);
5bcfed8e (David A. Velasco 2013-04-15 14:21:10 +0200 110)         if (selectionStart >= 0 && selectionEnd >= 0) {
5bcfed8e (David A. Velasco 2013-04-15 14:21:10 +0200 111)             inputText.setSelection(Math.min(selectionStart, selectionEnd), Math.max(selectionStart, selectionEnd));
5bcfed8e (David A. Velasco 2013-04-15 14:21:10 +0200 112)         }
d49bfd0c (David A. Velasco 2012-11-22 10:24:26 +0100 113)         d.getWindow().setSoftInputMode(LayoutParams.SOFT_INPUT_STATE_VISIBLE);
d49bfd0c (David A. Velasco 2012-11-22 10:24:26 +0100 114)         return d;
d49bfd0c (David A. Velasco 2012-11-22 10:24:26 +0100 115)     }    
d49bfd0c (David A. Velasco 2012-11-22 10:24:26 +0100 116) 
5af0fb44 (David A. Velasco 2012-10-26 11:34:20 +0200 117)     
d49bfd0c (David A. Velasco 2012-11-22 10:24:26 +0100 118)     /**
d49bfd0c (David A. Velasco 2012-11-22 10:24:26 +0100 119)      * Performs the corresponding action when a dialog button is clicked.
d49bfd0c (David A. Velasco 2012-11-22 10:24:26 +0100 120)      * 
d49bfd0c (David A. Velasco 2012-11-22 10:24:26 +0100 121)      * Saves the text in the input field to be accessed through {@link #getNewFilename()} when the positive
d49bfd0c (David A. Velasco 2012-11-22 10:24:26 +0100 122)      * button is clicked.
d49bfd0c (David A. Velasco 2012-11-22 10:24:26 +0100 123)      * 
d49bfd0c (David A. Velasco 2012-11-22 10:24:26 +0100 124)      * Notify the current listener in any case.
d49bfd0c (David A. Velasco 2012-11-22 10:24:26 +0100 125)      */
5af0fb44 (David A. Velasco 2012-10-26 11:34:20 +0200 126)     @Override
d49bfd0c (David A. Velasco 2012-11-22 10:24:26 +0100 127)     public void onClick(DialogInterface dialog, int which) {
d49bfd0c (David A. Velasco 2012-11-22 10:24:26 +0100 128)         switch (which) {
d49bfd0c (David A. Velasco 2012-11-22 10:24:26 +0100 129)             case AlertDialog.BUTTON_POSITIVE: {
d49bfd0c (David A. Velasco 2012-11-22 10:24:26 +0100 130)                 mNewFilename = ((TextView)(getDialog().findViewById(R.id.user_input))).getText().toString();
5af0fb44 (David A. Velasco 2012-10-26 11:34:20 +0200 131)                 mResult = true;
5af0fb44 (David A. Velasco 2012-10-26 11:34:20 +0200 132)             }
d49bfd0c (David A. Velasco 2012-11-22 10:24:26 +0100 133)             case AlertDialog.BUTTON_NEGATIVE: { // fall through
5af0fb44 (David A. Velasco 2012-10-26 11:34:20 +0200 134)                 dismiss();
5af0fb44 (David A. Velasco 2012-10-26 11:34:20 +0200 135)                 if (mListener != null)
5af0fb44 (David A. Velasco 2012-10-26 11:34:20 +0200 136)                     mListener.onDismiss(this);
5af0fb44 (David A. Velasco 2012-10-26 11:34:20 +0200 137)             }
5af0fb44 (David A. Velasco 2012-10-26 11:34:20 +0200 138)         }
5af0fb44 (David A. Velasco 2012-10-26 11:34:20 +0200 139)     }
5af0fb44 (David A. Velasco 2012-10-26 11:34:20 +0200 140)     
d49bfd0c (David A. Velasco 2012-11-22 10:24:26 +0100 141)     protected void setOnDismissListener(EditNameDialogListener listener) {
5af0fb44 (David A. Velasco 2012-10-26 11:34:20 +0200 142)         mListener = listener;
5af0fb44 (David A. Velasco 2012-10-26 11:34:20 +0200 143)     }
5af0fb44 (David A. Velasco 2012-10-26 11:34:20 +0200 144)     
d49bfd0c (David A. Velasco 2012-11-22 10:24:26 +0100 145)     /**
d49bfd0c (David A. Velasco 2012-11-22 10:24:26 +0100 146)      * Returns the text in the input field after the user clicked the positive button.
d49bfd0c (David A. Velasco 2012-11-22 10:24:26 +0100 147)      * 
d49bfd0c (David A. Velasco 2012-11-22 10:24:26 +0100 148)      * @return      Text in the input field.
d49bfd0c (David A. Velasco 2012-11-22 10:24:26 +0100 149)      */
5af0fb44 (David A. Velasco 2012-10-26 11:34:20 +0200 150)     public String getNewFilename() {
5af0fb44 (David A. Velasco 2012-10-26 11:34:20 +0200 151)         return mNewFilename;
5af0fb44 (David A. Velasco 2012-10-26 11:34:20 +0200 152)     }
5af0fb44 (David A. Velasco 2012-10-26 11:34:20 +0200 153)     
d49bfd0c (David A. Velasco 2012-11-22 10:24:26 +0100 154)     /**
d49bfd0c (David A. Velasco 2012-11-22 10:24:26 +0100 155)      * 
d49bfd0c (David A. Velasco 2012-11-22 10:24:26 +0100 156)      * @return      True when the user clicked the positive button.
d49bfd0c (David A. Velasco 2012-11-22 10:24:26 +0100 157)      */
5af0fb44 (David A. Velasco 2012-10-26 11:34:20 +0200 158)     public boolean getResult() {
5af0fb44 (David A. Velasco 2012-10-26 11:34:20 +0200 159)         return mResult;
5af0fb44 (David A. Velasco 2012-10-26 11:34:20 +0200 160)     }
5af0fb44 (David A. Velasco 2012-10-26 11:34:20 +0200 161) 
5af0fb44 (David A. Velasco 2012-10-26 11:34:20 +0200 162)     
d49bfd0c (David A. Velasco 2012-11-22 10:24:26 +0100 163)     /**
d49bfd0c (David A. Velasco 2012-11-22 10:24:26 +0100 164)      * Interface to receive a notification when any button in the dialog is clicked.
d49bfd0c (David A. Velasco 2012-11-22 10:24:26 +0100 165)      */
5af0fb44 (David A. Velasco 2012-10-26 11:34:20 +0200 166)     public interface EditNameDialogListener {
5af0fb44 (David A. Velasco 2012-10-26 11:34:20 +0200 167)         public void onDismiss(EditNameDialog dialog);
5af0fb44 (David A. Velasco 2012-10-26 11:34:20 +0200 168)     }
d49bfd0c (David A. Velasco 2012-11-22 10:24:26 +0100 169) 
d49bfd0c (David A. Velasco 2012-11-22 10:24:26 +0100 170) 
5af0fb44 (David A. Velasco 2012-10-26 11:34:20 +0200 171) }
5af0fb44 (David A. Velasco 2012-10-26 11:34:20 +0200 172) 
