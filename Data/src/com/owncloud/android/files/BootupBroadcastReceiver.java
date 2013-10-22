92080afe (Bartek Przybylski 2012-10-28 00:12:15 +0200  1) /* ownCloud Android client application
92080afe (Bartek Przybylski 2012-10-28 00:12:15 +0200  2)  *   Copyright (C) 2012 Bartek Przybylski
bb257ec7 (David A. Velasco  2013-02-07 18:45:10 +0100  3)  *   Copyright (C) 2012-2013 ownCloud Inc.
92080afe (Bartek Przybylski 2012-10-28 00:12:15 +0200  4)  *
92080afe (Bartek Przybylski 2012-10-28 00:12:15 +0200  5)  *   This program is free software: you can redistribute it and/or modify
bdc0332c (David A. Velasco  2013-04-17 12:26:13 +0200  6)  *   it under the terms of the GNU General Public License version 2,
bdc0332c (David A. Velasco  2013-04-17 12:26:13 +0200  7)  *   as published by the Free Software Foundation.
92080afe (Bartek Przybylski 2012-10-28 00:12:15 +0200  8)  *
92080afe (Bartek Przybylski 2012-10-28 00:12:15 +0200  9)  *   This program is distributed in the hope that it will be useful,
92080afe (Bartek Przybylski 2012-10-28 00:12:15 +0200 10)  *   but WITHOUT ANY WARRANTY; without even the implied warranty of
92080afe (Bartek Przybylski 2012-10-28 00:12:15 +0200 11)  *   MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
92080afe (Bartek Przybylski 2012-10-28 00:12:15 +0200 12)  *   GNU General Public License for more details.
92080afe (Bartek Przybylski 2012-10-28 00:12:15 +0200 13)  *
92080afe (Bartek Przybylski 2012-10-28 00:12:15 +0200 14)  *   You should have received a copy of the GNU General Public License
92080afe (Bartek Przybylski 2012-10-28 00:12:15 +0200 15)  *   along with this program.  If not, see <http://www.gnu.org/licenses/>.
92080afe (Bartek Przybylski 2012-10-28 00:12:15 +0200 16)  *
92080afe (Bartek Przybylski 2012-10-28 00:12:15 +0200 17)  */
92080afe (Bartek Przybylski 2012-10-28 00:12:15 +0200 18) 
ba148a82 (Bartek Przybylski 2012-08-22 19:32:42 +0200 19) package com.owncloud.android.files;
ba148a82 (Bartek Przybylski 2012-08-22 19:32:42 +0200 20) 
8e36e7cc (zerginator        2013-03-12 07:56:27 +0100 21) import com.owncloud.android.Log_OC;
ba148a82 (Bartek Przybylski 2012-08-22 19:32:42 +0200 22) import com.owncloud.android.files.services.FileObserverService;
ba148a82 (Bartek Przybylski 2012-08-22 19:32:42 +0200 23) 
ba148a82 (Bartek Przybylski 2012-08-22 19:32:42 +0200 24) import android.content.BroadcastReceiver;
ba148a82 (Bartek Przybylski 2012-08-22 19:32:42 +0200 25) import android.content.Context;
ba148a82 (Bartek Przybylski 2012-08-22 19:32:42 +0200 26) import android.content.Intent;
ba148a82 (Bartek Przybylski 2012-08-22 19:32:42 +0200 27) 
ba148a82 (Bartek Przybylski 2012-08-22 19:32:42 +0200 28) public class BootupBroadcastReceiver extends BroadcastReceiver {
ba148a82 (Bartek Przybylski 2012-08-22 19:32:42 +0200 29) 
ba148a82 (Bartek Przybylski 2012-08-22 19:32:42 +0200 30)     private static String TAG = "BootupBroadcastReceiver";
ba148a82 (Bartek Przybylski 2012-08-22 19:32:42 +0200 31)     
ba148a82 (Bartek Przybylski 2012-08-22 19:32:42 +0200 32)     @Override
ba148a82 (Bartek Przybylski 2012-08-22 19:32:42 +0200 33)     public void onReceive(Context context, Intent intent) {
ba148a82 (Bartek Przybylski 2012-08-22 19:32:42 +0200 34)         if (!intent.getAction().equals(Intent.ACTION_BOOT_COMPLETED)) {
8e36e7cc (zerginator        2013-03-12 07:56:27 +0100 35)             Log_OC.wtf(TAG, "Incorrect action sent " + intent.getAction());
ba148a82 (Bartek Przybylski 2012-08-22 19:32:42 +0200 36)             return;
ba148a82 (Bartek Przybylski 2012-08-22 19:32:42 +0200 37)         }
8e36e7cc (zerginator        2013-03-12 07:56:27 +0100 38)         Log_OC.d(TAG, "Starting file observer service...");
ba148a82 (Bartek Przybylski 2012-08-22 19:32:42 +0200 39)         Intent i = new Intent(context, FileObserverService.class);
ba148a82 (Bartek Przybylski 2012-08-22 19:32:42 +0200 40)         i.putExtra(FileObserverService.KEY_FILE_CMD,
ba148a82 (Bartek Przybylski 2012-08-22 19:32:42 +0200 41)                    FileObserverService.CMD_INIT_OBSERVED_LIST);
ba148a82 (Bartek Przybylski 2012-08-22 19:32:42 +0200 42)         context.startService(i);
8e36e7cc (zerginator        2013-03-12 07:56:27 +0100 43)         Log_OC.d(TAG, "DONE");
ba148a82 (Bartek Przybylski 2012-08-22 19:32:42 +0200 44)     }
ba148a82 (Bartek Przybylski 2012-08-22 19:32:42 +0200 45) 
ba148a82 (Bartek Przybylski 2012-08-22 19:32:42 +0200 46) }
