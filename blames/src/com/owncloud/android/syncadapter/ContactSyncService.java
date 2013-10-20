92080afe src/com/owncloud/android/syncadapter/ContactSyncService.java (Bartek Przybylski 2012-10-28 00:12:15 +0200  1) /* ownCloud Android client application
92080afe src/com/owncloud/android/syncadapter/ContactSyncService.java (Bartek Przybylski 2012-10-28 00:12:15 +0200  2)  *   Copyright (C) 2012 Bartek Przybylski
bb257ec7 src/com/owncloud/android/syncadapter/ContactSyncService.java (David A. Velasco  2013-02-07 18:45:10 +0100  3)  *   Copyright (C) 2012-2013 ownCloud Inc.
92080afe src/com/owncloud/android/syncadapter/ContactSyncService.java (Bartek Przybylski 2012-10-28 00:12:15 +0200  4)  *
92080afe src/com/owncloud/android/syncadapter/ContactSyncService.java (Bartek Przybylski 2012-10-28 00:12:15 +0200  5)  *   This program is free software: you can redistribute it and/or modify
bdc0332c src/com/owncloud/android/syncadapter/ContactSyncService.java (David A. Velasco  2013-04-17 12:26:13 +0200  6)  *   it under the terms of the GNU General Public License version 2,
bdc0332c src/com/owncloud/android/syncadapter/ContactSyncService.java (David A. Velasco  2013-04-17 12:26:13 +0200  7)  *   as published by the Free Software Foundation.
92080afe src/com/owncloud/android/syncadapter/ContactSyncService.java (Bartek Przybylski 2012-10-28 00:12:15 +0200  8)  *
92080afe src/com/owncloud/android/syncadapter/ContactSyncService.java (Bartek Przybylski 2012-10-28 00:12:15 +0200  9)  *   This program is distributed in the hope that it will be useful,
92080afe src/com/owncloud/android/syncadapter/ContactSyncService.java (Bartek Przybylski 2012-10-28 00:12:15 +0200 10)  *   but WITHOUT ANY WARRANTY; without even the implied warranty of
92080afe src/com/owncloud/android/syncadapter/ContactSyncService.java (Bartek Przybylski 2012-10-28 00:12:15 +0200 11)  *   MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
92080afe src/com/owncloud/android/syncadapter/ContactSyncService.java (Bartek Przybylski 2012-10-28 00:12:15 +0200 12)  *   GNU General Public License for more details.
92080afe src/com/owncloud/android/syncadapter/ContactSyncService.java (Bartek Przybylski 2012-10-28 00:12:15 +0200 13)  *
92080afe src/com/owncloud/android/syncadapter/ContactSyncService.java (Bartek Przybylski 2012-10-28 00:12:15 +0200 14)  *   You should have received a copy of the GNU General Public License
92080afe src/com/owncloud/android/syncadapter/ContactSyncService.java (Bartek Przybylski 2012-10-28 00:12:15 +0200 15)  *   along with this program.  If not, see <http://www.gnu.org/licenses/>.
92080afe src/com/owncloud/android/syncadapter/ContactSyncService.java (Bartek Przybylski 2012-10-28 00:12:15 +0200 16)  *
92080afe src/com/owncloud/android/syncadapter/ContactSyncService.java (Bartek Przybylski 2012-10-28 00:12:15 +0200 17)  */
92080afe src/com/owncloud/android/syncadapter/ContactSyncService.java (Bartek Przybylski 2012-10-28 00:12:15 +0200 18) 
a4ba6170 src/com/owncloud/android/syncadapter/ContactSyncService.java (Bartek Przybylski 2012-07-31 17:43:37 +0200 19) package com.owncloud.android.syncadapter;
cf2c2eed src/eu/alefzero/owncloud/syncadapter/ContactSyncService.java (Bartek Przybylski 2012-04-08 18:41:23 +0200 20) 
cf2c2eed src/eu/alefzero/owncloud/syncadapter/ContactSyncService.java (Bartek Przybylski 2012-04-08 18:41:23 +0200 21) import android.app.Service;
cf2c2eed src/eu/alefzero/owncloud/syncadapter/ContactSyncService.java (Bartek Przybylski 2012-04-08 18:41:23 +0200 22) import android.content.Intent;
cf2c2eed src/eu/alefzero/owncloud/syncadapter/ContactSyncService.java (Bartek Przybylski 2012-04-08 18:41:23 +0200 23) import android.os.IBinder;
cf2c2eed src/eu/alefzero/owncloud/syncadapter/ContactSyncService.java (Bartek Przybylski 2012-04-08 18:41:23 +0200 24) 
cf2c2eed src/eu/alefzero/owncloud/syncadapter/ContactSyncService.java (Bartek Przybylski 2012-04-08 18:41:23 +0200 25) public class ContactSyncService extends Service {
435b31ba src/eu/alefzero/owncloud/syncadapter/ContactSyncService.java (Lennart Rosam     2012-05-16 09:48:34 +0200 26)     private static final Object syncAdapterLock = new Object();
435b31ba src/eu/alefzero/owncloud/syncadapter/ContactSyncService.java (Lennart Rosam     2012-05-16 09:48:34 +0200 27)     private static AbstractOwnCloudSyncAdapter mSyncAdapter = null;
435b31ba src/eu/alefzero/owncloud/syncadapter/ContactSyncService.java (Lennart Rosam     2012-05-16 09:48:34 +0200 28) 
435b31ba src/eu/alefzero/owncloud/syncadapter/ContactSyncService.java (Lennart Rosam     2012-05-16 09:48:34 +0200 29)     @Override
435b31ba src/eu/alefzero/owncloud/syncadapter/ContactSyncService.java (Lennart Rosam     2012-05-16 09:48:34 +0200 30)     public void onCreate() {
435b31ba src/eu/alefzero/owncloud/syncadapter/ContactSyncService.java (Lennart Rosam     2012-05-16 09:48:34 +0200 31)         synchronized (syncAdapterLock) {
435b31ba src/eu/alefzero/owncloud/syncadapter/ContactSyncService.java (Lennart Rosam     2012-05-16 09:48:34 +0200 32)             if (mSyncAdapter == null) {
435b31ba src/eu/alefzero/owncloud/syncadapter/ContactSyncService.java (Lennart Rosam     2012-05-16 09:48:34 +0200 33)                 mSyncAdapter = new ContactSyncAdapter(getApplicationContext(),
435b31ba src/eu/alefzero/owncloud/syncadapter/ContactSyncService.java (Lennart Rosam     2012-05-16 09:48:34 +0200 34)                         true);
435b31ba src/eu/alefzero/owncloud/syncadapter/ContactSyncService.java (Lennart Rosam     2012-05-16 09:48:34 +0200 35)             }
435b31ba src/eu/alefzero/owncloud/syncadapter/ContactSyncService.java (Lennart Rosam     2012-05-16 09:48:34 +0200 36)         }
435b31ba src/eu/alefzero/owncloud/syncadapter/ContactSyncService.java (Lennart Rosam     2012-05-16 09:48:34 +0200 37)     }
435b31ba src/eu/alefzero/owncloud/syncadapter/ContactSyncService.java (Lennart Rosam     2012-05-16 09:48:34 +0200 38) 
435b31ba src/eu/alefzero/owncloud/syncadapter/ContactSyncService.java (Lennart Rosam     2012-05-16 09:48:34 +0200 39)     @Override
435b31ba src/eu/alefzero/owncloud/syncadapter/ContactSyncService.java (Lennart Rosam     2012-05-16 09:48:34 +0200 40)     public IBinder onBind(Intent arg0) {
435b31ba src/eu/alefzero/owncloud/syncadapter/ContactSyncService.java (Lennart Rosam     2012-05-16 09:48:34 +0200 41)         return mSyncAdapter.getSyncAdapterBinder();
435b31ba src/eu/alefzero/owncloud/syncadapter/ContactSyncService.java (Lennart Rosam     2012-05-16 09:48:34 +0200 42)     }
cf2c2eed src/eu/alefzero/owncloud/syncadapter/ContactSyncService.java (Bartek Przybylski 2012-04-08 18:41:23 +0200 43) 
cf2c2eed src/eu/alefzero/owncloud/syncadapter/ContactSyncService.java (Bartek Przybylski 2012-04-08 18:41:23 +0200 44) }
