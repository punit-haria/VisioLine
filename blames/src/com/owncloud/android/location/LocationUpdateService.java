6e43d84b src/eu/alefzero/owncloud/location/LocationUpdateService.java (Lennart Rosam     2012-04-15 17:31:08 +0200   1) /* ownCloud Android client application
6e43d84b src/eu/alefzero/owncloud/location/LocationUpdateService.java (Lennart Rosam     2012-04-15 17:31:08 +0200   2)  *   Copyright (C) 2011  Bartek Przybylski
bb257ec7 src/com/owncloud/android/location/LocationUpdateService.java (David A. Velasco  2013-02-07 18:45:10 +0100   3)  *   Copyright (C) 2012-2013 ownCloud Inc.
6e43d84b src/eu/alefzero/owncloud/location/LocationUpdateService.java (Lennart Rosam     2012-04-15 17:31:08 +0200   4)  *
6e43d84b src/eu/alefzero/owncloud/location/LocationUpdateService.java (Lennart Rosam     2012-04-15 17:31:08 +0200   5)  *   This program is free software: you can redistribute it and/or modify
bdc0332c src/com/owncloud/android/location/LocationUpdateService.java (David A. Velasco  2013-04-17 12:26:13 +0200   6)  *   it under the terms of the GNU General Public License version 2,
bdc0332c src/com/owncloud/android/location/LocationUpdateService.java (David A. Velasco  2013-04-17 12:26:13 +0200   7)  *   as published by the Free Software Foundation.
6e43d84b src/eu/alefzero/owncloud/location/LocationUpdateService.java (Lennart Rosam     2012-04-15 17:31:08 +0200   8)  *
6e43d84b src/eu/alefzero/owncloud/location/LocationUpdateService.java (Lennart Rosam     2012-04-15 17:31:08 +0200   9)  *   This program is distributed in the hope that it will be useful,
6e43d84b src/eu/alefzero/owncloud/location/LocationUpdateService.java (Lennart Rosam     2012-04-15 17:31:08 +0200  10)  *   but WITHOUT ANY WARRANTY; without even the implied warranty of
6e43d84b src/eu/alefzero/owncloud/location/LocationUpdateService.java (Lennart Rosam     2012-04-15 17:31:08 +0200  11)  *   MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
6e43d84b src/eu/alefzero/owncloud/location/LocationUpdateService.java (Lennart Rosam     2012-04-15 17:31:08 +0200  12)  *   GNU General Public License for more details.
6e43d84b src/eu/alefzero/owncloud/location/LocationUpdateService.java (Lennart Rosam     2012-04-15 17:31:08 +0200  13)  *
6e43d84b src/eu/alefzero/owncloud/location/LocationUpdateService.java (Lennart Rosam     2012-04-15 17:31:08 +0200  14)  *   You should have received a copy of the GNU General Public License
6e43d84b src/eu/alefzero/owncloud/location/LocationUpdateService.java (Lennart Rosam     2012-04-15 17:31:08 +0200  15)  *   along with this program.  If not, see <http://www.gnu.org/licenses/>.
6e43d84b src/eu/alefzero/owncloud/location/LocationUpdateService.java (Lennart Rosam     2012-04-15 17:31:08 +0200  16)  *
6e43d84b src/eu/alefzero/owncloud/location/LocationUpdateService.java (Lennart Rosam     2012-04-15 17:31:08 +0200  17)  */
a4ba6170 src/com/owncloud/android/location/LocationUpdateService.java (Bartek Przybylski 2012-07-31 17:43:37 +0200  18) package com.owncloud.android.location;
6e43d84b src/eu/alefzero/owncloud/location/LocationUpdateService.java (Lennart Rosam     2012-04-15 17:31:08 +0200  19) 
6e43d84b src/eu/alefzero/owncloud/location/LocationUpdateService.java (Lennart Rosam     2012-04-15 17:31:08 +0200  20) import android.app.IntentService;
6e43d84b src/eu/alefzero/owncloud/location/LocationUpdateService.java (Lennart Rosam     2012-04-15 17:31:08 +0200  21) import android.content.Intent;
6e43d84b src/eu/alefzero/owncloud/location/LocationUpdateService.java (Lennart Rosam     2012-04-15 17:31:08 +0200  22) import android.content.SharedPreferences;
6e43d84b src/eu/alefzero/owncloud/location/LocationUpdateService.java (Lennart Rosam     2012-04-15 17:31:08 +0200  23) import android.location.Criteria;
6e43d84b src/eu/alefzero/owncloud/location/LocationUpdateService.java (Lennart Rosam     2012-04-15 17:31:08 +0200  24) import android.location.Location;
6e43d84b src/eu/alefzero/owncloud/location/LocationUpdateService.java (Lennart Rosam     2012-04-15 17:31:08 +0200  25) import android.location.LocationListener;
6e43d84b src/eu/alefzero/owncloud/location/LocationUpdateService.java (Lennart Rosam     2012-04-15 17:31:08 +0200  26) import android.location.LocationManager;
6e43d84b src/eu/alefzero/owncloud/location/LocationUpdateService.java (Lennart Rosam     2012-04-15 17:31:08 +0200  27) import android.location.LocationProvider;
6e43d84b src/eu/alefzero/owncloud/location/LocationUpdateService.java (Lennart Rosam     2012-04-15 17:31:08 +0200  28) import android.os.Bundle;
6e43d84b src/eu/alefzero/owncloud/location/LocationUpdateService.java (Lennart Rosam     2012-04-15 17:31:08 +0200  29) import android.preference.PreferenceManager;
6e43d84b src/eu/alefzero/owncloud/location/LocationUpdateService.java (Lennart Rosam     2012-04-15 17:31:08 +0200  30) import android.widget.Toast;
6e43d84b src/eu/alefzero/owncloud/location/LocationUpdateService.java (Lennart Rosam     2012-04-15 17:31:08 +0200  31) 
8e36e7cc src/com/owncloud/android/location/LocationUpdateService.java (zerginator        2013-03-12 07:56:27 +0100  32) import com.owncloud.android.Log_OC;
a4ba6170 src/com/owncloud/android/location/LocationUpdateService.java (Bartek Przybylski 2012-07-31 17:43:37 +0200  33) import com.owncloud.android.R;
a4ba6170 src/com/owncloud/android/location/LocationUpdateService.java (Bartek Przybylski 2012-07-31 17:43:37 +0200  34) 
435b31ba src/eu/alefzero/owncloud/location/LocationUpdateService.java (Lennart Rosam     2012-05-16 09:48:34 +0200  35) public class LocationUpdateService extends IntentService implements
435b31ba src/eu/alefzero/owncloud/location/LocationUpdateService.java (Lennart Rosam     2012-05-16 09:48:34 +0200  36)         LocationListener {
6e43d84b src/eu/alefzero/owncloud/location/LocationUpdateService.java (Lennart Rosam     2012-04-15 17:31:08 +0200  37) 
435b31ba src/eu/alefzero/owncloud/location/LocationUpdateService.java (Lennart Rosam     2012-05-16 09:48:34 +0200  38)     public static final String TAG = "LocationUpdateService";
435b31ba src/eu/alefzero/owncloud/location/LocationUpdateService.java (Lennart Rosam     2012-05-16 09:48:34 +0200  39) 
435b31ba src/eu/alefzero/owncloud/location/LocationUpdateService.java (Lennart Rosam     2012-05-16 09:48:34 +0200  40)     private LocationManager mLocationManager;
435b31ba src/eu/alefzero/owncloud/location/LocationUpdateService.java (Lennart Rosam     2012-05-16 09:48:34 +0200  41)     private LocationProvider mLocationProvider;
435b31ba src/eu/alefzero/owncloud/location/LocationUpdateService.java (Lennart Rosam     2012-05-16 09:48:34 +0200  42)     private SharedPreferences mPreferences;
435b31ba src/eu/alefzero/owncloud/location/LocationUpdateService.java (Lennart Rosam     2012-05-16 09:48:34 +0200  43) 
435b31ba src/eu/alefzero/owncloud/location/LocationUpdateService.java (Lennart Rosam     2012-05-16 09:48:34 +0200  44)     public LocationUpdateService() {
435b31ba src/eu/alefzero/owncloud/location/LocationUpdateService.java (Lennart Rosam     2012-05-16 09:48:34 +0200  45)         super(TAG);
435b31ba src/eu/alefzero/owncloud/location/LocationUpdateService.java (Lennart Rosam     2012-05-16 09:48:34 +0200  46)     }
435b31ba src/eu/alefzero/owncloud/location/LocationUpdateService.java (Lennart Rosam     2012-05-16 09:48:34 +0200  47) 
435b31ba src/eu/alefzero/owncloud/location/LocationUpdateService.java (Lennart Rosam     2012-05-16 09:48:34 +0200  48)     @Override
435b31ba src/eu/alefzero/owncloud/location/LocationUpdateService.java (Lennart Rosam     2012-05-16 09:48:34 +0200  49)     protected void onHandleIntent(Intent intent) {
435b31ba src/eu/alefzero/owncloud/location/LocationUpdateService.java (Lennart Rosam     2012-05-16 09:48:34 +0200  50)         mLocationManager = (LocationManager) getSystemService(LOCATION_SERVICE);
435b31ba src/eu/alefzero/owncloud/location/LocationUpdateService.java (Lennart Rosam     2012-05-16 09:48:34 +0200  51)         // Determine, how we can track the device
435b31ba src/eu/alefzero/owncloud/location/LocationUpdateService.java (Lennart Rosam     2012-05-16 09:48:34 +0200  52)         Criteria criteria = new Criteria();
435b31ba src/eu/alefzero/owncloud/location/LocationUpdateService.java (Lennart Rosam     2012-05-16 09:48:34 +0200  53)         criteria.setAccuracy(Criteria.ACCURACY_FINE);
435b31ba src/eu/alefzero/owncloud/location/LocationUpdateService.java (Lennart Rosam     2012-05-16 09:48:34 +0200  54)         criteria.setPowerRequirement(Criteria.POWER_LOW);
435b31ba src/eu/alefzero/owncloud/location/LocationUpdateService.java (Lennart Rosam     2012-05-16 09:48:34 +0200  55)         mLocationProvider = mLocationManager.getProvider(mLocationManager
435b31ba src/eu/alefzero/owncloud/location/LocationUpdateService.java (Lennart Rosam     2012-05-16 09:48:34 +0200  56)                 .getBestProvider(criteria, true));
435b31ba src/eu/alefzero/owncloud/location/LocationUpdateService.java (Lennart Rosam     2012-05-16 09:48:34 +0200  57) 
435b31ba src/eu/alefzero/owncloud/location/LocationUpdateService.java (Lennart Rosam     2012-05-16 09:48:34 +0200  58)         // Notify user if there is no way to track the device
435b31ba src/eu/alefzero/owncloud/location/LocationUpdateService.java (Lennart Rosam     2012-05-16 09:48:34 +0200  59)         if (mLocationProvider == null) {
236895e7 src/com/owncloud/android/location/LocationUpdateService.java (David A. Velasco  2012-11-29 12:26:55 +0100  60)             String message = String.format(getString(R.string.location_no_provider), getString(R.string.app_name));
435b31ba src/eu/alefzero/owncloud/location/LocationUpdateService.java (Lennart Rosam     2012-05-16 09:48:34 +0200  61)             Toast.makeText(this,
236895e7 src/com/owncloud/android/location/LocationUpdateService.java (David A. Velasco  2012-11-29 12:26:55 +0100  62)                     message,
236895e7 src/com/owncloud/android/location/LocationUpdateService.java (David A. Velasco  2012-11-29 12:26:55 +0100  63)                     Toast.LENGTH_LONG).show();
435b31ba src/eu/alefzero/owncloud/location/LocationUpdateService.java (Lennart Rosam     2012-05-16 09:48:34 +0200  64)             stopSelf();
435b31ba src/eu/alefzero/owncloud/location/LocationUpdateService.java (Lennart Rosam     2012-05-16 09:48:34 +0200  65)             return;
435b31ba src/eu/alefzero/owncloud/location/LocationUpdateService.java (Lennart Rosam     2012-05-16 09:48:34 +0200  66)         }
435b31ba src/eu/alefzero/owncloud/location/LocationUpdateService.java (Lennart Rosam     2012-05-16 09:48:34 +0200  67) 
435b31ba src/eu/alefzero/owncloud/location/LocationUpdateService.java (Lennart Rosam     2012-05-16 09:48:34 +0200  68)         // Get preferences for device tracking
435b31ba src/eu/alefzero/owncloud/location/LocationUpdateService.java (Lennart Rosam     2012-05-16 09:48:34 +0200  69)         mPreferences = PreferenceManager.getDefaultSharedPreferences(this);
435b31ba src/eu/alefzero/owncloud/location/LocationUpdateService.java (Lennart Rosam     2012-05-16 09:48:34 +0200  70)         boolean trackDevice = mPreferences.getBoolean("enable_devicetracking",
435b31ba src/eu/alefzero/owncloud/location/LocationUpdateService.java (Lennart Rosam     2012-05-16 09:48:34 +0200  71)                 true);
435b31ba src/eu/alefzero/owncloud/location/LocationUpdateService.java (Lennart Rosam     2012-05-16 09:48:34 +0200  72)         int updateIntervall = Integer.parseInt(mPreferences.getString(
435b31ba src/eu/alefzero/owncloud/location/LocationUpdateService.java (Lennart Rosam     2012-05-16 09:48:34 +0200  73)                 "devicetracking_update_intervall", "30")) * 60 * 1000;
435b31ba src/eu/alefzero/owncloud/location/LocationUpdateService.java (Lennart Rosam     2012-05-16 09:48:34 +0200  74)         int distanceBetweenLocationChecks = 50;
435b31ba src/eu/alefzero/owncloud/location/LocationUpdateService.java (Lennart Rosam     2012-05-16 09:48:34 +0200  75) 
435b31ba src/eu/alefzero/owncloud/location/LocationUpdateService.java (Lennart Rosam     2012-05-16 09:48:34 +0200  76)         // If we do shall track the device -> Stop
435b31ba src/eu/alefzero/owncloud/location/LocationUpdateService.java (Lennart Rosam     2012-05-16 09:48:34 +0200  77)         if (!trackDevice) {
8e36e7cc src/com/owncloud/android/location/LocationUpdateService.java (zerginator        2013-03-12 07:56:27 +0100  78)             Log_OC.d(TAG, "Devicetracking is disabled");
435b31ba src/eu/alefzero/owncloud/location/LocationUpdateService.java (Lennart Rosam     2012-05-16 09:48:34 +0200  79)             stopSelf();
435b31ba src/eu/alefzero/owncloud/location/LocationUpdateService.java (Lennart Rosam     2012-05-16 09:48:34 +0200  80)             return;
435b31ba src/eu/alefzero/owncloud/location/LocationUpdateService.java (Lennart Rosam     2012-05-16 09:48:34 +0200  81)         }
435b31ba src/eu/alefzero/owncloud/location/LocationUpdateService.java (Lennart Rosam     2012-05-16 09:48:34 +0200  82) 
435b31ba src/eu/alefzero/owncloud/location/LocationUpdateService.java (Lennart Rosam     2012-05-16 09:48:34 +0200  83)         mLocationManager.requestLocationUpdates(mLocationProvider.getName(),
435b31ba src/eu/alefzero/owncloud/location/LocationUpdateService.java (Lennart Rosam     2012-05-16 09:48:34 +0200  84)                 updateIntervall, distanceBetweenLocationChecks, this);
435b31ba src/eu/alefzero/owncloud/location/LocationUpdateService.java (Lennart Rosam     2012-05-16 09:48:34 +0200  85)     }
435b31ba src/eu/alefzero/owncloud/location/LocationUpdateService.java (Lennart Rosam     2012-05-16 09:48:34 +0200  86) 
435b31ba src/eu/alefzero/owncloud/location/LocationUpdateService.java (Lennart Rosam     2012-05-16 09:48:34 +0200  87)     @Override
435b31ba src/eu/alefzero/owncloud/location/LocationUpdateService.java (Lennart Rosam     2012-05-16 09:48:34 +0200  88)     public void onLocationChanged(Location location) {
8e36e7cc src/com/owncloud/android/location/LocationUpdateService.java (zerginator        2013-03-12 07:56:27 +0100  89)         Log_OC.d(TAG, "Location changed: " + location);
435b31ba src/eu/alefzero/owncloud/location/LocationUpdateService.java (Lennart Rosam     2012-05-16 09:48:34 +0200  90) 
435b31ba src/eu/alefzero/owncloud/location/LocationUpdateService.java (Lennart Rosam     2012-05-16 09:48:34 +0200  91)     }
435b31ba src/eu/alefzero/owncloud/location/LocationUpdateService.java (Lennart Rosam     2012-05-16 09:48:34 +0200  92) 
435b31ba src/eu/alefzero/owncloud/location/LocationUpdateService.java (Lennart Rosam     2012-05-16 09:48:34 +0200  93)     @Override
435b31ba src/eu/alefzero/owncloud/location/LocationUpdateService.java (Lennart Rosam     2012-05-16 09:48:34 +0200  94)     public void onProviderDisabled(String arg0) {
435b31ba src/eu/alefzero/owncloud/location/LocationUpdateService.java (Lennart Rosam     2012-05-16 09:48:34 +0200  95)         // TODO Auto-generated method stub
435b31ba src/eu/alefzero/owncloud/location/LocationUpdateService.java (Lennart Rosam     2012-05-16 09:48:34 +0200  96) 
435b31ba src/eu/alefzero/owncloud/location/LocationUpdateService.java (Lennart Rosam     2012-05-16 09:48:34 +0200  97)     }
435b31ba src/eu/alefzero/owncloud/location/LocationUpdateService.java (Lennart Rosam     2012-05-16 09:48:34 +0200  98) 
435b31ba src/eu/alefzero/owncloud/location/LocationUpdateService.java (Lennart Rosam     2012-05-16 09:48:34 +0200  99)     @Override
435b31ba src/eu/alefzero/owncloud/location/LocationUpdateService.java (Lennart Rosam     2012-05-16 09:48:34 +0200 100)     public void onProviderEnabled(String arg0) {
435b31ba src/eu/alefzero/owncloud/location/LocationUpdateService.java (Lennart Rosam     2012-05-16 09:48:34 +0200 101)         // TODO Auto-generated method stub
435b31ba src/eu/alefzero/owncloud/location/LocationUpdateService.java (Lennart Rosam     2012-05-16 09:48:34 +0200 102) 
435b31ba src/eu/alefzero/owncloud/location/LocationUpdateService.java (Lennart Rosam     2012-05-16 09:48:34 +0200 103)     }
435b31ba src/eu/alefzero/owncloud/location/LocationUpdateService.java (Lennart Rosam     2012-05-16 09:48:34 +0200 104) 
435b31ba src/eu/alefzero/owncloud/location/LocationUpdateService.java (Lennart Rosam     2012-05-16 09:48:34 +0200 105)     @Override
435b31ba src/eu/alefzero/owncloud/location/LocationUpdateService.java (Lennart Rosam     2012-05-16 09:48:34 +0200 106)     public void onStatusChanged(String arg0, int arg1, Bundle arg2) {
435b31ba src/eu/alefzero/owncloud/location/LocationUpdateService.java (Lennart Rosam     2012-05-16 09:48:34 +0200 107)         // TODO Auto-generated method stub
435b31ba src/eu/alefzero/owncloud/location/LocationUpdateService.java (Lennart Rosam     2012-05-16 09:48:34 +0200 108) 
435b31ba src/eu/alefzero/owncloud/location/LocationUpdateService.java (Lennart Rosam     2012-05-16 09:48:34 +0200 109)     }
6e43d84b src/eu/alefzero/owncloud/location/LocationUpdateService.java (Lennart Rosam     2012-04-15 17:31:08 +0200 110) 
6e43d84b src/eu/alefzero/owncloud/location/LocationUpdateService.java (Lennart Rosam     2012-04-15 17:31:08 +0200 111) }
