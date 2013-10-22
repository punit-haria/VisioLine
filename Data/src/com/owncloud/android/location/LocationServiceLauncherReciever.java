6e43d84b src/eu/alefzero/owncloud/location/LocationServiceLauncherReciever.java (Lennart Rosam     2012-04-15 17:31:08 +0200  1) /* ownCloud Android client application
6e43d84b src/eu/alefzero/owncloud/location/LocationServiceLauncherReciever.java (Lennart Rosam     2012-04-15 17:31:08 +0200  2)  *   Copyright (C) 2011  Bartek Przybylski
bb257ec7 src/com/owncloud/android/location/LocationServiceLauncherReciever.java (David A. Velasco  2013-02-07 18:45:10 +0100  3)  *   Copyright (C) 2012-2013 ownCloud Inc.
6e43d84b src/eu/alefzero/owncloud/location/LocationServiceLauncherReciever.java (Lennart Rosam     2012-04-15 17:31:08 +0200  4)  *
6e43d84b src/eu/alefzero/owncloud/location/LocationServiceLauncherReciever.java (Lennart Rosam     2012-04-15 17:31:08 +0200  5)  *   This program is free software: you can redistribute it and/or modify
bdc0332c src/com/owncloud/android/location/LocationServiceLauncherReciever.java (David A. Velasco  2013-04-17 12:26:13 +0200  6)  *   it under the terms of the GNU General Public License version 2,
bdc0332c src/com/owncloud/android/location/LocationServiceLauncherReciever.java (David A. Velasco  2013-04-17 12:26:13 +0200  7)  *   as published by the Free Software Foundation.
6e43d84b src/eu/alefzero/owncloud/location/LocationServiceLauncherReciever.java (Lennart Rosam     2012-04-15 17:31:08 +0200  8)  *
6e43d84b src/eu/alefzero/owncloud/location/LocationServiceLauncherReciever.java (Lennart Rosam     2012-04-15 17:31:08 +0200  9)  *   This program is distributed in the hope that it will be useful,
6e43d84b src/eu/alefzero/owncloud/location/LocationServiceLauncherReciever.java (Lennart Rosam     2012-04-15 17:31:08 +0200 10)  *   but WITHOUT ANY WARRANTY; without even the implied warranty of
6e43d84b src/eu/alefzero/owncloud/location/LocationServiceLauncherReciever.java (Lennart Rosam     2012-04-15 17:31:08 +0200 11)  *   MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
6e43d84b src/eu/alefzero/owncloud/location/LocationServiceLauncherReciever.java (Lennart Rosam     2012-04-15 17:31:08 +0200 12)  *   GNU General Public License for more details.
6e43d84b src/eu/alefzero/owncloud/location/LocationServiceLauncherReciever.java (Lennart Rosam     2012-04-15 17:31:08 +0200 13)  *
6e43d84b src/eu/alefzero/owncloud/location/LocationServiceLauncherReciever.java (Lennart Rosam     2012-04-15 17:31:08 +0200 14)  *   You should have received a copy of the GNU General Public License
6e43d84b src/eu/alefzero/owncloud/location/LocationServiceLauncherReciever.java (Lennart Rosam     2012-04-15 17:31:08 +0200 15)  *   along with this program.  If not, see <http://www.gnu.org/licenses/>.
6e43d84b src/eu/alefzero/owncloud/location/LocationServiceLauncherReciever.java (Lennart Rosam     2012-04-15 17:31:08 +0200 16)  *
6e43d84b src/eu/alefzero/owncloud/location/LocationServiceLauncherReciever.java (Lennart Rosam     2012-04-15 17:31:08 +0200 17)  */
a4ba6170 src/com/owncloud/android/location/LocationServiceLauncherReciever.java (Bartek Przybylski 2012-07-31 17:43:37 +0200 18) package com.owncloud.android.location;
6e43d84b src/eu/alefzero/owncloud/location/LocationServiceLauncherReciever.java (Lennart Rosam     2012-04-15 17:31:08 +0200 19) 
8e36e7cc src/com/owncloud/android/location/LocationServiceLauncherReciever.java (zerginator        2013-03-12 07:56:27 +0100 20) import com.owncloud.android.Log_OC;
8e36e7cc src/com/owncloud/android/location/LocationServiceLauncherReciever.java (zerginator        2013-03-12 07:56:27 +0100 21) 
6e43d84b src/eu/alefzero/owncloud/location/LocationServiceLauncherReciever.java (Lennart Rosam     2012-04-15 17:31:08 +0200 22) import android.app.ActivityManager;
6e43d84b src/eu/alefzero/owncloud/location/LocationServiceLauncherReciever.java (Lennart Rosam     2012-04-15 17:31:08 +0200 23) import android.app.ActivityManager.RunningServiceInfo;
6e43d84b src/eu/alefzero/owncloud/location/LocationServiceLauncherReciever.java (Lennart Rosam     2012-04-15 17:31:08 +0200 24) import android.content.BroadcastReceiver;
6e43d84b src/eu/alefzero/owncloud/location/LocationServiceLauncherReciever.java (Lennart Rosam     2012-04-15 17:31:08 +0200 25) import android.content.Context;
6e43d84b src/eu/alefzero/owncloud/location/LocationServiceLauncherReciever.java (Lennart Rosam     2012-04-15 17:31:08 +0200 26) import android.content.Intent;
6e43d84b src/eu/alefzero/owncloud/location/LocationServiceLauncherReciever.java (Lennart Rosam     2012-04-15 17:31:08 +0200 27) import android.content.SharedPreferences;
6e43d84b src/eu/alefzero/owncloud/location/LocationServiceLauncherReciever.java (Lennart Rosam     2012-04-15 17:31:08 +0200 28) import android.preference.PreferenceManager;
6e43d84b src/eu/alefzero/owncloud/location/LocationServiceLauncherReciever.java (Lennart Rosam     2012-04-15 17:31:08 +0200 29) 
6e43d84b src/eu/alefzero/owncloud/location/LocationServiceLauncherReciever.java (Lennart Rosam     2012-04-15 17:31:08 +0200 30) public class LocationServiceLauncherReciever extends BroadcastReceiver {
6e43d84b src/eu/alefzero/owncloud/location/LocationServiceLauncherReciever.java (Lennart Rosam     2012-04-15 17:31:08 +0200 31) 
435b31ba src/eu/alefzero/owncloud/location/LocationServiceLauncherReciever.java (Lennart Rosam     2012-05-16 09:48:34 +0200 32)     private final String TAG = getClass().getSimpleName();
435b31ba src/eu/alefzero/owncloud/location/LocationServiceLauncherReciever.java (Lennart Rosam     2012-05-16 09:48:34 +0200 33) 
435b31ba src/eu/alefzero/owncloud/location/LocationServiceLauncherReciever.java (Lennart Rosam     2012-05-16 09:48:34 +0200 34)     @Override
435b31ba src/eu/alefzero/owncloud/location/LocationServiceLauncherReciever.java (Lennart Rosam     2012-05-16 09:48:34 +0200 35)     public void onReceive(Context context, Intent intent) {
435b31ba src/eu/alefzero/owncloud/location/LocationServiceLauncherReciever.java (Lennart Rosam     2012-05-16 09:48:34 +0200 36)         Intent deviceTrackingIntent = new Intent();
435b31ba src/eu/alefzero/owncloud/location/LocationServiceLauncherReciever.java (Lennart Rosam     2012-05-16 09:48:34 +0200 37)         deviceTrackingIntent
6b765c7a src/com/owncloud/android/location/LocationServiceLauncherReciever.java (David A. Velasco  2012-08-01 10:00:43 +0200 38)                 .setAction("com.owncloud.android.location.LocationUpdateService");
435b31ba src/eu/alefzero/owncloud/location/LocationServiceLauncherReciever.java (Lennart Rosam     2012-05-16 09:48:34 +0200 39)         SharedPreferences preferences = PreferenceManager
435b31ba src/eu/alefzero/owncloud/location/LocationServiceLauncherReciever.java (Lennart Rosam     2012-05-16 09:48:34 +0200 40)                 .getDefaultSharedPreferences(context);
435b31ba src/eu/alefzero/owncloud/location/LocationServiceLauncherReciever.java (Lennart Rosam     2012-05-16 09:48:34 +0200 41)         boolean trackDevice = preferences.getBoolean("enable_devicetracking",
435b31ba src/eu/alefzero/owncloud/location/LocationServiceLauncherReciever.java (Lennart Rosam     2012-05-16 09:48:34 +0200 42)                 true);
435b31ba src/eu/alefzero/owncloud/location/LocationServiceLauncherReciever.java (Lennart Rosam     2012-05-16 09:48:34 +0200 43) 
435b31ba src/eu/alefzero/owncloud/location/LocationServiceLauncherReciever.java (Lennart Rosam     2012-05-16 09:48:34 +0200 44)         // Used in Preferences activity so that tracking is disabled or
435b31ba src/eu/alefzero/owncloud/location/LocationServiceLauncherReciever.java (Lennart Rosam     2012-05-16 09:48:34 +0200 45)         // reenabled
435b31ba src/eu/alefzero/owncloud/location/LocationServiceLauncherReciever.java (Lennart Rosam     2012-05-16 09:48:34 +0200 46)         if (intent.hasExtra("TRACKING_SETTING")) {
435b31ba src/eu/alefzero/owncloud/location/LocationServiceLauncherReciever.java (Lennart Rosam     2012-05-16 09:48:34 +0200 47)             trackDevice = intent.getBooleanExtra("TRACKING_SETTING", true);
435b31ba src/eu/alefzero/owncloud/location/LocationServiceLauncherReciever.java (Lennart Rosam     2012-05-16 09:48:34 +0200 48)         }
435b31ba src/eu/alefzero/owncloud/location/LocationServiceLauncherReciever.java (Lennart Rosam     2012-05-16 09:48:34 +0200 49) 
435b31ba src/eu/alefzero/owncloud/location/LocationServiceLauncherReciever.java (Lennart Rosam     2012-05-16 09:48:34 +0200 50)         startOrStopDeviceTracking(context, trackDevice);
435b31ba src/eu/alefzero/owncloud/location/LocationServiceLauncherReciever.java (Lennart Rosam     2012-05-16 09:48:34 +0200 51)     }
435b31ba src/eu/alefzero/owncloud/location/LocationServiceLauncherReciever.java (Lennart Rosam     2012-05-16 09:48:34 +0200 52) 
435b31ba src/eu/alefzero/owncloud/location/LocationServiceLauncherReciever.java (Lennart Rosam     2012-05-16 09:48:34 +0200 53)     /**
435b31ba src/eu/alefzero/owncloud/location/LocationServiceLauncherReciever.java (Lennart Rosam     2012-05-16 09:48:34 +0200 54)      * Used internally. Starts or stops the device tracking service
435b31ba src/eu/alefzero/owncloud/location/LocationServiceLauncherReciever.java (Lennart Rosam     2012-05-16 09:48:34 +0200 55)      * 
ad8dbb31 src/eu/alefzero/owncloud/location/LocationServiceLauncherReciever.java (Lennart Rosam     2012-05-16 11:07:39 +0200 56)      * @param trackDevice true to start the service, false to stop it
435b31ba src/eu/alefzero/owncloud/location/LocationServiceLauncherReciever.java (Lennart Rosam     2012-05-16 09:48:34 +0200 57)      */
435b31ba src/eu/alefzero/owncloud/location/LocationServiceLauncherReciever.java (Lennart Rosam     2012-05-16 09:48:34 +0200 58)     private void startOrStopDeviceTracking(Context context, boolean trackDevice) {
435b31ba src/eu/alefzero/owncloud/location/LocationServiceLauncherReciever.java (Lennart Rosam     2012-05-16 09:48:34 +0200 59)         Intent deviceTrackingIntent = new Intent();
435b31ba src/eu/alefzero/owncloud/location/LocationServiceLauncherReciever.java (Lennart Rosam     2012-05-16 09:48:34 +0200 60)         deviceTrackingIntent
6b765c7a src/com/owncloud/android/location/LocationServiceLauncherReciever.java (David A. Velasco  2012-08-01 10:00:43 +0200 61)                 .setAction("com.owncloud.android.location.LocationUpdateService");
435b31ba src/eu/alefzero/owncloud/location/LocationServiceLauncherReciever.java (Lennart Rosam     2012-05-16 09:48:34 +0200 62)         if (!isDeviceTrackingServiceRunning(context) && trackDevice) {
8e36e7cc src/com/owncloud/android/location/LocationServiceLauncherReciever.java (zerginator        2013-03-12 07:56:27 +0100 63)             Log_OC.d(TAG, "Starting device tracker service");
435b31ba src/eu/alefzero/owncloud/location/LocationServiceLauncherReciever.java (Lennart Rosam     2012-05-16 09:48:34 +0200 64)             context.startService(deviceTrackingIntent);
435b31ba src/eu/alefzero/owncloud/location/LocationServiceLauncherReciever.java (Lennart Rosam     2012-05-16 09:48:34 +0200 65)         } else if (isDeviceTrackingServiceRunning(context) && !trackDevice) {
8e36e7cc src/com/owncloud/android/location/LocationServiceLauncherReciever.java (zerginator        2013-03-12 07:56:27 +0100 66)             Log_OC.d(TAG, "Stopping device tracker service");
435b31ba src/eu/alefzero/owncloud/location/LocationServiceLauncherReciever.java (Lennart Rosam     2012-05-16 09:48:34 +0200 67)             context.stopService(deviceTrackingIntent);
435b31ba src/eu/alefzero/owncloud/location/LocationServiceLauncherReciever.java (Lennart Rosam     2012-05-16 09:48:34 +0200 68)         }
435b31ba src/eu/alefzero/owncloud/location/LocationServiceLauncherReciever.java (Lennart Rosam     2012-05-16 09:48:34 +0200 69)     }
435b31ba src/eu/alefzero/owncloud/location/LocationServiceLauncherReciever.java (Lennart Rosam     2012-05-16 09:48:34 +0200 70) 
435b31ba src/eu/alefzero/owncloud/location/LocationServiceLauncherReciever.java (Lennart Rosam     2012-05-16 09:48:34 +0200 71)     /**
435b31ba src/eu/alefzero/owncloud/location/LocationServiceLauncherReciever.java (Lennart Rosam     2012-05-16 09:48:34 +0200 72)      * Checks to see whether or not the LocationUpdateService is running
435b31ba src/eu/alefzero/owncloud/location/LocationServiceLauncherReciever.java (Lennart Rosam     2012-05-16 09:48:34 +0200 73)      * 
435b31ba src/eu/alefzero/owncloud/location/LocationServiceLauncherReciever.java (Lennart Rosam     2012-05-16 09:48:34 +0200 74)      * @return true, if it is. Otherwise false
435b31ba src/eu/alefzero/owncloud/location/LocationServiceLauncherReciever.java (Lennart Rosam     2012-05-16 09:48:34 +0200 75)      */
435b31ba src/eu/alefzero/owncloud/location/LocationServiceLauncherReciever.java (Lennart Rosam     2012-05-16 09:48:34 +0200 76)     private boolean isDeviceTrackingServiceRunning(Context context) {
435b31ba src/eu/alefzero/owncloud/location/LocationServiceLauncherReciever.java (Lennart Rosam     2012-05-16 09:48:34 +0200 77)         ActivityManager manager = (ActivityManager) context
435b31ba src/eu/alefzero/owncloud/location/LocationServiceLauncherReciever.java (Lennart Rosam     2012-05-16 09:48:34 +0200 78)                 .getSystemService(Context.ACTIVITY_SERVICE);
435b31ba src/eu/alefzero/owncloud/location/LocationServiceLauncherReciever.java (Lennart Rosam     2012-05-16 09:48:34 +0200 79)         for (RunningServiceInfo service : manager
435b31ba src/eu/alefzero/owncloud/location/LocationServiceLauncherReciever.java (Lennart Rosam     2012-05-16 09:48:34 +0200 80)                 .getRunningServices(Integer.MAX_VALUE)) {
435b31ba src/eu/alefzero/owncloud/location/LocationServiceLauncherReciever.java (Lennart Rosam     2012-05-16 09:48:34 +0200 81)             if (getClass().getName().equals(service.service.getClassName())) {
435b31ba src/eu/alefzero/owncloud/location/LocationServiceLauncherReciever.java (Lennart Rosam     2012-05-16 09:48:34 +0200 82)                 return true;
435b31ba src/eu/alefzero/owncloud/location/LocationServiceLauncherReciever.java (Lennart Rosam     2012-05-16 09:48:34 +0200 83)             }
435b31ba src/eu/alefzero/owncloud/location/LocationServiceLauncherReciever.java (Lennart Rosam     2012-05-16 09:48:34 +0200 84)         }
435b31ba src/eu/alefzero/owncloud/location/LocationServiceLauncherReciever.java (Lennart Rosam     2012-05-16 09:48:34 +0200 85)         return false;
435b31ba src/eu/alefzero/owncloud/location/LocationServiceLauncherReciever.java (Lennart Rosam     2012-05-16 09:48:34 +0200 86)     }
6e43d84b src/eu/alefzero/owncloud/location/LocationServiceLauncherReciever.java (Lennart Rosam     2012-04-15 17:31:08 +0200 87) 
6e43d84b src/eu/alefzero/owncloud/location/LocationServiceLauncherReciever.java (Lennart Rosam     2012-04-15 17:31:08 +0200 88) }
