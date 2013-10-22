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
6c323657 (Bartek Przybylski 2012-10-27 12:20:52 +0200  19) package com.owncloud.android.ui.dialog;
6c323657 (Bartek Przybylski 2012-10-27 12:20:52 +0200  20) 
6c323657 (Bartek Przybylski 2012-10-27 12:20:52 +0200  21) import android.app.AlertDialog;
6c323657 (Bartek Przybylski 2012-10-27 12:20:52 +0200  22) import android.app.Dialog;
6c323657 (Bartek Przybylski 2012-10-27 12:20:52 +0200  23) import android.content.DialogInterface;
6c323657 (Bartek Przybylski 2012-10-27 12:20:52 +0200  24) import android.os.Bundle;
6c323657 (Bartek Przybylski 2012-10-27 12:20:52 +0200  25) import android.support.v4.app.Fragment;
6c323657 (Bartek Przybylski 2012-10-27 12:20:52 +0200  26) import android.support.v4.app.FragmentTransaction;
6c323657 (Bartek Przybylski 2012-10-27 12:20:52 +0200  27) 
6c323657 (Bartek Przybylski 2012-10-27 12:20:52 +0200  28) import com.actionbarsherlock.app.SherlockDialogFragment;
6c323657 (Bartek Przybylski 2012-10-27 12:20:52 +0200  29) import com.actionbarsherlock.app.SherlockFragmentActivity;
6c323657 (Bartek Przybylski 2012-10-27 12:20:52 +0200  30) import com.owncloud.android.R;
6c323657 (Bartek Przybylski 2012-10-27 12:20:52 +0200  31) 
92080afe (Bartek Przybylski 2012-10-28 00:12:15 +0200  32) /**
92080afe (Bartek Przybylski 2012-10-28 00:12:15 +0200  33)  * Dialog which will be displayed to user upon keep-in-sync file conflict.
92080afe (Bartek Przybylski 2012-10-28 00:12:15 +0200  34)  * 
92080afe (Bartek Przybylski 2012-10-28 00:12:15 +0200  35)  * @author Bartek Przybylski
92080afe (Bartek Przybylski 2012-10-28 00:12:15 +0200  36)  *
92080afe (Bartek Przybylski 2012-10-28 00:12:15 +0200  37)  */
6c323657 (Bartek Przybylski 2012-10-27 12:20:52 +0200  38) public class ConflictsResolveDialog extends SherlockDialogFragment {
6c323657 (Bartek Przybylski 2012-10-27 12:20:52 +0200  39) 
6c323657 (Bartek Przybylski 2012-10-27 12:20:52 +0200  40)     public static enum Decision { 
6c323657 (Bartek Przybylski 2012-10-27 12:20:52 +0200  41)         CANCEL,
6c323657 (Bartek Przybylski 2012-10-27 12:20:52 +0200  42)         KEEP_BOTH,
6c323657 (Bartek Przybylski 2012-10-27 12:20:52 +0200  43)         OVERWRITE
6c323657 (Bartek Przybylski 2012-10-27 12:20:52 +0200  44)     }
6c323657 (Bartek Przybylski 2012-10-27 12:20:52 +0200  45)     
6c323657 (Bartek Przybylski 2012-10-27 12:20:52 +0200  46)     OnConflictDecisionMadeListener mListener;
6c323657 (Bartek Przybylski 2012-10-27 12:20:52 +0200  47)     
6c323657 (Bartek Przybylski 2012-10-27 12:20:52 +0200  48)     public static ConflictsResolveDialog newInstance(String path, OnConflictDecisionMadeListener listener) {
6c323657 (Bartek Przybylski 2012-10-27 12:20:52 +0200  49)         ConflictsResolveDialog f = new ConflictsResolveDialog();
6c323657 (Bartek Przybylski 2012-10-27 12:20:52 +0200  50)         Bundle args = new Bundle();
6c323657 (Bartek Przybylski 2012-10-27 12:20:52 +0200  51)         args.putString("remotepath", path);
6c323657 (Bartek Przybylski 2012-10-27 12:20:52 +0200  52)         f.setArguments(args);
6c323657 (Bartek Przybylski 2012-10-27 12:20:52 +0200  53)         f.mListener = listener;
6c323657 (Bartek Przybylski 2012-10-27 12:20:52 +0200  54)         return f;
6c323657 (Bartek Przybylski 2012-10-27 12:20:52 +0200  55)     }
6c323657 (Bartek Przybylski 2012-10-27 12:20:52 +0200  56)     
6c323657 (Bartek Przybylski 2012-10-27 12:20:52 +0200  57)     @Override
6c323657 (Bartek Przybylski 2012-10-27 12:20:52 +0200  58)     public Dialog onCreateDialog(Bundle savedInstanceState) {
6c323657 (Bartek Przybylski 2012-10-27 12:20:52 +0200  59)         String remotepath = getArguments().getString("remotepath");
6c323657 (Bartek Przybylski 2012-10-27 12:20:52 +0200  60)         return new AlertDialog.Builder(getSherlockActivity())
6c323657 (Bartek Przybylski 2012-10-27 12:20:52 +0200  61)                    .setIcon(R.drawable.icon)
6c323657 (Bartek Przybylski 2012-10-27 12:20:52 +0200  62)                    .setTitle(R.string.conflict_title)
6c323657 (Bartek Przybylski 2012-10-27 12:20:52 +0200  63)                    .setMessage(String.format(getString(R.string.conflict_message), remotepath))
6c323657 (Bartek Przybylski 2012-10-27 12:20:52 +0200  64)                    .setPositiveButton(R.string.conflict_overwrite,
6c323657 (Bartek Przybylski 2012-10-27 12:20:52 +0200  65)                        new DialogInterface.OnClickListener() {
6c323657 (Bartek Przybylski 2012-10-27 12:20:52 +0200  66) 
6c323657 (Bartek Przybylski 2012-10-27 12:20:52 +0200  67)                            @Override
6c323657 (Bartek Przybylski 2012-10-27 12:20:52 +0200  68)                            public void onClick(DialogInterface dialog, int which) {
6c323657 (Bartek Przybylski 2012-10-27 12:20:52 +0200  69)                                if (mListener != null)
6c323657 (Bartek Przybylski 2012-10-27 12:20:52 +0200  70)                                    mListener.ConflictDecisionMade(Decision.OVERWRITE);
6c323657 (Bartek Przybylski 2012-10-27 12:20:52 +0200  71)                            }
6c323657 (Bartek Przybylski 2012-10-27 12:20:52 +0200  72)                        })
6c323657 (Bartek Przybylski 2012-10-27 12:20:52 +0200  73)                    .setNeutralButton(R.string.conflict_keep_both,
6c323657 (Bartek Przybylski 2012-10-27 12:20:52 +0200  74)                        new DialogInterface.OnClickListener() {
6c323657 (Bartek Przybylski 2012-10-27 12:20:52 +0200  75)                             @Override
6c323657 (Bartek Przybylski 2012-10-27 12:20:52 +0200  76)                             public void onClick(DialogInterface dialog, int which) {
6c323657 (Bartek Przybylski 2012-10-27 12:20:52 +0200  77)                                 if (mListener != null)
6c323657 (Bartek Przybylski 2012-10-27 12:20:52 +0200  78)                                     mListener.ConflictDecisionMade(Decision.KEEP_BOTH);
6c323657 (Bartek Przybylski 2012-10-27 12:20:52 +0200  79)                             }
6c323657 (Bartek Przybylski 2012-10-27 12:20:52 +0200  80)                         })
6c323657 (Bartek Przybylski 2012-10-27 12:20:52 +0200  81)                    .setNegativeButton(R.string.conflict_dont_upload,
6c323657 (Bartek Przybylski 2012-10-27 12:20:52 +0200  82)                        new DialogInterface.OnClickListener() {
6c323657 (Bartek Przybylski 2012-10-27 12:20:52 +0200  83)                            @Override
6c323657 (Bartek Przybylski 2012-10-27 12:20:52 +0200  84)                            public void onClick(DialogInterface dialog, int which) {
6c323657 (Bartek Przybylski 2012-10-27 12:20:52 +0200  85)                                if (mListener != null)
6c323657 (Bartek Przybylski 2012-10-27 12:20:52 +0200  86)                                    mListener.ConflictDecisionMade(Decision.CANCEL);
6c323657 (Bartek Przybylski 2012-10-27 12:20:52 +0200  87)                            }
6c323657 (Bartek Przybylski 2012-10-27 12:20:52 +0200  88)                    })
6c323657 (Bartek Przybylski 2012-10-27 12:20:52 +0200  89)                    .create();
6c323657 (Bartek Przybylski 2012-10-27 12:20:52 +0200  90)     }
6c323657 (Bartek Przybylski 2012-10-27 12:20:52 +0200  91)     
6c323657 (Bartek Przybylski 2012-10-27 12:20:52 +0200  92)     public void showDialog(SherlockFragmentActivity activity) {
6c323657 (Bartek Przybylski 2012-10-27 12:20:52 +0200  93)         Fragment prev = activity.getSupportFragmentManager().findFragmentByTag("dialog");
6c323657 (Bartek Przybylski 2012-10-27 12:20:52 +0200  94)         FragmentTransaction ft = activity.getSupportFragmentManager().beginTransaction();
6c323657 (Bartek Przybylski 2012-10-27 12:20:52 +0200  95)         if (prev != null) {
6c323657 (Bartek Przybylski 2012-10-27 12:20:52 +0200  96)             ft.remove(prev);
6c323657 (Bartek Przybylski 2012-10-27 12:20:52 +0200  97)         }
6c323657 (Bartek Przybylski 2012-10-27 12:20:52 +0200  98)         ft.addToBackStack(null);
6c323657 (Bartek Przybylski 2012-10-27 12:20:52 +0200  99) 
6c323657 (Bartek Przybylski 2012-10-27 12:20:52 +0200 100)         this.show(ft, "dialog");
6c323657 (Bartek Przybylski 2012-10-27 12:20:52 +0200 101)     }
6c323657 (Bartek Przybylski 2012-10-27 12:20:52 +0200 102) 
92080afe (Bartek Przybylski 2012-10-28 00:12:15 +0200 103)     public void dismissDialog(SherlockFragmentActivity activity) {
92080afe (Bartek Przybylski 2012-10-28 00:12:15 +0200 104)         Fragment prev = activity.getSupportFragmentManager().findFragmentByTag(getTag());
6c323657 (Bartek Przybylski 2012-10-27 12:20:52 +0200 105)         if (prev != null) {
6c323657 (Bartek Przybylski 2012-10-27 12:20:52 +0200 106)             FragmentTransaction ft = activity.getSupportFragmentManager().beginTransaction();
6c323657 (Bartek Przybylski 2012-10-27 12:20:52 +0200 107)             ft.remove(prev);
6c323657 (Bartek Przybylski 2012-10-27 12:20:52 +0200 108)             ft.commit();
6c323657 (Bartek Przybylski 2012-10-27 12:20:52 +0200 109)         }
6c323657 (Bartek Przybylski 2012-10-27 12:20:52 +0200 110)     }
6c323657 (Bartek Przybylski 2012-10-27 12:20:52 +0200 111)     
6de96a98 (David A. Velasco  2012-11-21 17:48:56 +0100 112)     @Override
6de96a98 (David A. Velasco  2012-11-21 17:48:56 +0100 113)     public void onCancel(DialogInterface dialog) {
6de96a98 (David A. Velasco  2012-11-21 17:48:56 +0100 114)         mListener.ConflictDecisionMade(Decision.CANCEL);
6de96a98 (David A. Velasco  2012-11-21 17:48:56 +0100 115)     }
6de96a98 (David A. Velasco  2012-11-21 17:48:56 +0100 116)     
6c323657 (Bartek Przybylski 2012-10-27 12:20:52 +0200 117)     public interface OnConflictDecisionMadeListener {
6c323657 (Bartek Przybylski 2012-10-27 12:20:52 +0200 118)         public void ConflictDecisionMade(Decision decision);
6c323657 (Bartek Przybylski 2012-10-27 12:20:52 +0200 119)     }
6c323657 (Bartek Przybylski 2012-10-27 12:20:52 +0200 120) }
