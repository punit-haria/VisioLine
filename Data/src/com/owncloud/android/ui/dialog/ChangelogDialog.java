5da9c095 (David A. Velasco 2013-01-30 13:46:15 +0100   1) /* ownCloud Android client application
bb257ec7 (David A. Velasco 2013-02-07 18:45:10 +0100   2)  *   Copyright (C) 2012-2013 ownCloud Inc.
5da9c095 (David A. Velasco 2013-01-30 13:46:15 +0100   3)  *
5da9c095 (David A. Velasco 2013-01-30 13:46:15 +0100   4)  *   This program is free software: you can redistribute it and/or modify
bdc0332c (David A. Velasco 2013-04-17 12:26:13 +0200   5)  *   it under the terms of the GNU General Public License version 2,
bdc0332c (David A. Velasco 2013-04-17 12:26:13 +0200   6)  *   as published by the Free Software Foundation.
5da9c095 (David A. Velasco 2013-01-30 13:46:15 +0100   7)  *
5da9c095 (David A. Velasco 2013-01-30 13:46:15 +0100   8)  *   This program is distributed in the hope that it will be useful,
5da9c095 (David A. Velasco 2013-01-30 13:46:15 +0100   9)  *   but WITHOUT ANY WARRANTY; without even the implied warranty of
5da9c095 (David A. Velasco 2013-01-30 13:46:15 +0100  10)  *   MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
5da9c095 (David A. Velasco 2013-01-30 13:46:15 +0100  11)  *   GNU General Public License for more details.
5da9c095 (David A. Velasco 2013-01-30 13:46:15 +0100  12)  *
5da9c095 (David A. Velasco 2013-01-30 13:46:15 +0100  13)  *   You should have received a copy of the GNU General Public License
5da9c095 (David A. Velasco 2013-01-30 13:46:15 +0100  14)  *   along with this program.  If not, see <http://www.gnu.org/licenses/>.
5da9c095 (David A. Velasco 2013-01-30 13:46:15 +0100  15)  *
5da9c095 (David A. Velasco 2013-01-30 13:46:15 +0100  16)  */
bb257ec7 (David A. Velasco 2013-02-07 18:45:10 +0100  17) 
5da9c095 (David A. Velasco 2013-01-30 13:46:15 +0100  18) package com.owncloud.android.ui.dialog;
5da9c095 (David A. Velasco 2013-01-30 13:46:15 +0100  19) 
5da9c095 (David A. Velasco 2013-01-30 13:46:15 +0100  20) import java.io.InputStream;
5da9c095 (David A. Velasco 2013-01-30 13:46:15 +0100  21) import java.util.Scanner;
5da9c095 (David A. Velasco 2013-01-30 13:46:15 +0100  22) 
5da9c095 (David A. Velasco 2013-01-30 13:46:15 +0100  23) import android.app.AlertDialog;
5da9c095 (David A. Velasco 2013-01-30 13:46:15 +0100  24) import android.app.Dialog;
5da9c095 (David A. Velasco 2013-01-30 13:46:15 +0100  25) import android.content.DialogInterface;
5da9c095 (David A. Velasco 2013-01-30 13:46:15 +0100  26) import android.net.Uri;
5da9c095 (David A. Velasco 2013-01-30 13:46:15 +0100  27) import android.os.Bundle;
5da9c095 (David A. Velasco 2013-01-30 13:46:15 +0100  28) import android.view.LayoutInflater;
5da9c095 (David A. Velasco 2013-01-30 13:46:15 +0100  29) import android.view.View;
5da9c095 (David A. Velasco 2013-01-30 13:46:15 +0100  30) import android.webkit.WebView;
5da9c095 (David A. Velasco 2013-01-30 13:46:15 +0100  31) import android.widget.TextView;
5da9c095 (David A. Velasco 2013-01-30 13:46:15 +0100  32) 
5da9c095 (David A. Velasco 2013-01-30 13:46:15 +0100  33) import com.actionbarsherlock.app.SherlockDialogFragment;
5da9c095 (David A. Velasco 2013-01-30 13:46:15 +0100  34) import com.owncloud.android.R;
5da9c095 (David A. Velasco 2013-01-30 13:46:15 +0100  35) 
5da9c095 (David A. Velasco 2013-01-30 13:46:15 +0100  36) /**
5da9c095 (David A. Velasco 2013-01-30 13:46:15 +0100  37)  * Dialog to show the contents of res/raw/CHANGELOG.txt
5da9c095 (David A. Velasco 2013-01-30 13:46:15 +0100  38)  */
5da9c095 (David A. Velasco 2013-01-30 13:46:15 +0100  39) public class ChangelogDialog extends SherlockDialogFragment {
5da9c095 (David A. Velasco 2013-01-30 13:46:15 +0100  40) 
5da9c095 (David A. Velasco 2013-01-30 13:46:15 +0100  41)     private static final String ARG_CANCELABLE = ChangelogDialog.class.getCanonicalName() + ".ARG_CANCELABLE";
5da9c095 (David A. Velasco 2013-01-30 13:46:15 +0100  42) 
5da9c095 (David A. Velasco 2013-01-30 13:46:15 +0100  43) 
5da9c095 (David A. Velasco 2013-01-30 13:46:15 +0100  44)     /**
5da9c095 (David A. Velasco 2013-01-30 13:46:15 +0100  45)      * Public factory method to get dialog instances.
5da9c095 (David A. Velasco 2013-01-30 13:46:15 +0100  46)      * 
5da9c095 (David A. Velasco 2013-01-30 13:46:15 +0100  47)      * @param cancelable    If 'true', the dialog can be cancelled by the user input (BACK button, touch outside...)
5da9c095 (David A. Velasco 2013-01-30 13:46:15 +0100  48)      * @return              New dialog instance, ready to show.
5da9c095 (David A. Velasco 2013-01-30 13:46:15 +0100  49)      */
5da9c095 (David A. Velasco 2013-01-30 13:46:15 +0100  50)     public static ChangelogDialog newInstance(boolean cancelable) {
5da9c095 (David A. Velasco 2013-01-30 13:46:15 +0100  51)         ChangelogDialog fragment = new ChangelogDialog();
5da9c095 (David A. Velasco 2013-01-30 13:46:15 +0100  52)         Bundle args = new Bundle();
5da9c095 (David A. Velasco 2013-01-30 13:46:15 +0100  53)         args.putBoolean(ARG_CANCELABLE, cancelable);
5da9c095 (David A. Velasco 2013-01-30 13:46:15 +0100  54)         fragment.setArguments(args);
5da9c095 (David A. Velasco 2013-01-30 13:46:15 +0100  55)         return fragment;
5da9c095 (David A. Velasco 2013-01-30 13:46:15 +0100  56)     }
5da9c095 (David A. Velasco 2013-01-30 13:46:15 +0100  57) 
5da9c095 (David A. Velasco 2013-01-30 13:46:15 +0100  58)     
5da9c095 (David A. Velasco 2013-01-30 13:46:15 +0100  59)     /**
5da9c095 (David A. Velasco 2013-01-30 13:46:15 +0100  60)      * {@inheritDoc}
5da9c095 (David A. Velasco 2013-01-30 13:46:15 +0100  61)      */
5da9c095 (David A. Velasco 2013-01-30 13:46:15 +0100  62)     @Override
5da9c095 (David A. Velasco 2013-01-30 13:46:15 +0100  63)     public Dialog onCreateDialog(Bundle savedInstanceState) {
5da9c095 (David A. Velasco 2013-01-30 13:46:15 +0100  64)         /// load the custom view to insert in the dialog, between title and 
5da9c095 (David A. Velasco 2013-01-30 13:46:15 +0100  65)         WebView webview = new WebView(getActivity());
5da9c095 (David A. Velasco 2013-01-30 13:46:15 +0100  66)         webview.loadUrl("file:///android_res/raw/" + getResources().getResourceEntryName(R.raw.changelog) + ".html");
5da9c095 (David A. Velasco 2013-01-30 13:46:15 +0100  67)         
5da9c095 (David A. Velasco 2013-01-30 13:46:15 +0100  68)         /// build the dialog
5da9c095 (David A. Velasco 2013-01-30 13:46:15 +0100  69)         AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
7d67737a (David A. Velasco 2013-03-11 13:27:56 +0100  70)         
5da9c095 (David A. Velasco 2013-01-30 13:46:15 +0100  71)         Dialog dialog = builder.setView(webview)
5da9c095 (David A. Velasco 2013-01-30 13:46:15 +0100  72)                                 .setIcon(R.drawable.icon)
7d67737a (David A. Velasco 2013-03-11 13:27:56 +0100  73)                                 //.setTitle(R.string.whats_new)
5da9c095 (David A. Velasco 2013-01-30 13:46:15 +0100  74)                                 .setPositiveButton(R.string.common_ok,
5da9c095 (David A. Velasco 2013-01-30 13:46:15 +0100  75)                                         new DialogInterface.OnClickListener() {
5da9c095 (David A. Velasco 2013-01-30 13:46:15 +0100  76)                                     @Override
5da9c095 (David A. Velasco 2013-01-30 13:46:15 +0100  77)                                     public void onClick(DialogInterface dialog, int which) {
5da9c095 (David A. Velasco 2013-01-30 13:46:15 +0100  78)                                         dialog.dismiss();
5da9c095 (David A. Velasco 2013-01-30 13:46:15 +0100  79)                                     }
5da9c095 (David A. Velasco 2013-01-30 13:46:15 +0100  80)                                 }).create();
5da9c095 (David A. Velasco 2013-01-30 13:46:15 +0100  81)         
5da9c095 (David A. Velasco 2013-01-30 13:46:15 +0100  82)         dialog.setCancelable(getArguments().getBoolean(ARG_CANCELABLE));
5da9c095 (David A. Velasco 2013-01-30 13:46:15 +0100  83)         return dialog;
5da9c095 (David A. Velasco 2013-01-30 13:46:15 +0100  84)     } 
5da9c095 (David A. Velasco 2013-01-30 13:46:15 +0100  85)     
5da9c095 (David A. Velasco 2013-01-30 13:46:15 +0100  86)     /**
5da9c095 (David A. Velasco 2013-01-30 13:46:15 +0100  87)      * {@inheritDoc}
5da9c095 (David A. Velasco 2013-01-30 13:46:15 +0100  88)      *-/
5da9c095 (David A. Velasco 2013-01-30 13:46:15 +0100  89)     @Override
5da9c095 (David A. Velasco 2013-01-30 13:46:15 +0100  90)     public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
5da9c095 (David A. Velasco 2013-01-30 13:46:15 +0100  91)         /// load the custom layout
5da9c095 (David A. Velasco 2013-01-30 13:46:15 +0100  92)         View view = inflater.inflate(R.layout.fragment_changelog, container);
5da9c095 (David A. Velasco 2013-01-30 13:46:15 +0100  93)         mEditText = (EditText) view.findViewById(R.id.txt_your_name);
5da9c095 (David A. Velasco 2013-01-30 13:46:15 +0100  94)         getDialog().setTitle(R.string.whats_new);
5da9c095 (David A. Velasco 2013-01-30 13:46:15 +0100  95)         
5da9c095 (David A. Velasco 2013-01-30 13:46:15 +0100  96)         /// read full contents of the change log file (don't make it too big)
5da9c095 (David A. Velasco 2013-01-30 13:46:15 +0100  97)         InputStream changeLogStream = getResources().openRawResource(R.raw.changelog);
5da9c095 (David A. Velasco 2013-01-30 13:46:15 +0100  98)         Scanner scanner = new java.util.Scanner(changeLogStream).useDelimiter("\\A");
5da9c095 (David A. Velasco 2013-01-30 13:46:15 +0100  99)         String text = scanner.hasNext() ? scanner.next() : "";
5da9c095 (David A. Velasco 2013-01-30 13:46:15 +0100 100)         
5da9c095 (David A. Velasco 2013-01-30 13:46:15 +0100 101)         /// make clickable the links in the change log file
5da9c095 (David A. Velasco 2013-01-30 13:46:15 +0100 102)         SpannableString sText = new SpannableString(text);
5da9c095 (David A. Velasco 2013-01-30 13:46:15 +0100 103)         Linkify.addLinks(sText, Linkify.ALL);
5da9c095 (David A. Velasco 2013-01-30 13:46:15 +0100 104) 
5da9c095 (David A. Velasco 2013-01-30 13:46:15 +0100 105)         return view;
5da9c095 (David A. Velasco 2013-01-30 13:46:15 +0100 106)     }
5da9c095 (David A. Velasco 2013-01-30 13:46:15 +0100 107)     */
5da9c095 (David A. Velasco 2013-01-30 13:46:15 +0100 108) }
5da9c095 (David A. Velasco 2013-01-30 13:46:15 +0100 109) 
5da9c095 (David A. Velasco 2013-01-30 13:46:15 +0100 110) 
