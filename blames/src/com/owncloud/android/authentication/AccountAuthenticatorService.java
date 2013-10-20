^154bb85 src/eu/alefzero/owncloud/authenticator/AccountAuthenticatorService.java  (Bartek Przybylski 2011-08-19 22:37:35 +0200  1) /* ownCloud Android client application
^154bb85 src/eu/alefzero/owncloud/authenticator/AccountAuthenticatorService.java  (Bartek Przybylski 2011-08-19 22:37:35 +0200  2)  *   Copyright (C) 2011  Bartek Przybylski
bb257ec7 src/com/owncloud/android/authenticator/AccountAuthenticatorService.java  (David A. Velasco  2013-02-07 18:45:10 +0100  3)  *   Copyright (C) 2012-2013 ownCloud Inc.
^154bb85 src/eu/alefzero/owncloud/authenticator/AccountAuthenticatorService.java  (Bartek Przybylski 2011-08-19 22:37:35 +0200  4)  *
^154bb85 src/eu/alefzero/owncloud/authenticator/AccountAuthenticatorService.java  (Bartek Przybylski 2011-08-19 22:37:35 +0200  5)  *   This program is free software: you can redistribute it and/or modify
bdc0332c src/com/owncloud/android/authenticator/AccountAuthenticatorService.java  (David A. Velasco  2013-04-17 12:26:13 +0200  6)  *   it under the terms of the GNU General Public License version 2,
bdc0332c src/com/owncloud/android/authenticator/AccountAuthenticatorService.java  (David A. Velasco  2013-04-17 12:26:13 +0200  7)  *   as published by the Free Software Foundation.
^154bb85 src/eu/alefzero/owncloud/authenticator/AccountAuthenticatorService.java  (Bartek Przybylski 2011-08-19 22:37:35 +0200  8)  *
^154bb85 src/eu/alefzero/owncloud/authenticator/AccountAuthenticatorService.java  (Bartek Przybylski 2011-08-19 22:37:35 +0200  9)  *   This program is distributed in the hope that it will be useful,
^154bb85 src/eu/alefzero/owncloud/authenticator/AccountAuthenticatorService.java  (Bartek Przybylski 2011-08-19 22:37:35 +0200 10)  *   but WITHOUT ANY WARRANTY; without even the implied warranty of
^154bb85 src/eu/alefzero/owncloud/authenticator/AccountAuthenticatorService.java  (Bartek Przybylski 2011-08-19 22:37:35 +0200 11)  *   MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
^154bb85 src/eu/alefzero/owncloud/authenticator/AccountAuthenticatorService.java  (Bartek Przybylski 2011-08-19 22:37:35 +0200 12)  *   GNU General Public License for more details.
^154bb85 src/eu/alefzero/owncloud/authenticator/AccountAuthenticatorService.java  (Bartek Przybylski 2011-08-19 22:37:35 +0200 13)  *
^154bb85 src/eu/alefzero/owncloud/authenticator/AccountAuthenticatorService.java  (Bartek Przybylski 2011-08-19 22:37:35 +0200 14)  *   You should have received a copy of the GNU General Public License
^154bb85 src/eu/alefzero/owncloud/authenticator/AccountAuthenticatorService.java  (Bartek Przybylski 2011-08-19 22:37:35 +0200 15)  *   along with this program.  If not, see <http://www.gnu.org/licenses/>.
^154bb85 src/eu/alefzero/owncloud/authenticator/AccountAuthenticatorService.java  (Bartek Przybylski 2011-08-19 22:37:35 +0200 16)  *
^154bb85 src/eu/alefzero/owncloud/authenticator/AccountAuthenticatorService.java  (Bartek Przybylski 2011-08-19 22:37:35 +0200 17)  */
^154bb85 src/eu/alefzero/owncloud/authenticator/AccountAuthenticatorService.java  (Bartek Przybylski 2011-08-19 22:37:35 +0200 18) 
69d6d821 src/com/owncloud/android/authentication/AccountAuthenticatorService.java (David A. Velasco  2013-03-21 12:21:48 +0100 19) package com.owncloud.android.authentication;
^154bb85 src/eu/alefzero/owncloud/authenticator/AccountAuthenticatorService.java  (Bartek Przybylski 2011-08-19 22:37:35 +0200 20) 
^154bb85 src/eu/alefzero/owncloud/authenticator/AccountAuthenticatorService.java  (Bartek Przybylski 2011-08-19 22:37:35 +0200 21) import android.app.Service;
^154bb85 src/eu/alefzero/owncloud/authenticator/AccountAuthenticatorService.java  (Bartek Przybylski 2011-08-19 22:37:35 +0200 22) import android.content.Intent;
^154bb85 src/eu/alefzero/owncloud/authenticator/AccountAuthenticatorService.java  (Bartek Przybylski 2011-08-19 22:37:35 +0200 23) import android.os.IBinder;
^154bb85 src/eu/alefzero/owncloud/authenticator/AccountAuthenticatorService.java  (Bartek Przybylski 2011-08-19 22:37:35 +0200 24) 
^154bb85 src/eu/alefzero/owncloud/authenticator/AccountAuthenticatorService.java  (Bartek Przybylski 2011-08-19 22:37:35 +0200 25) public class AccountAuthenticatorService extends Service {
^154bb85 src/eu/alefzero/owncloud/authenticator/AccountAuthenticatorService.java  (Bartek Przybylski 2011-08-19 22:37:35 +0200 26) 
435b31ba src/eu/alefzero/owncloud/authenticator/AccountAuthenticatorService.java  (Lennart Rosam     2012-05-16 09:48:34 +0200 27)     private AccountAuthenticator mAuthenticator;
435b31ba src/eu/alefzero/owncloud/authenticator/AccountAuthenticatorService.java  (Lennart Rosam     2012-05-16 09:48:34 +0200 28)     static final public String ACCOUNT_TYPE = "owncloud";
435b31ba src/eu/alefzero/owncloud/authenticator/AccountAuthenticatorService.java  (Lennart Rosam     2012-05-16 09:48:34 +0200 29) 
435b31ba src/eu/alefzero/owncloud/authenticator/AccountAuthenticatorService.java  (Lennart Rosam     2012-05-16 09:48:34 +0200 30)     @Override
435b31ba src/eu/alefzero/owncloud/authenticator/AccountAuthenticatorService.java  (Lennart Rosam     2012-05-16 09:48:34 +0200 31)     public void onCreate() {
435b31ba src/eu/alefzero/owncloud/authenticator/AccountAuthenticatorService.java  (Lennart Rosam     2012-05-16 09:48:34 +0200 32)         super.onCreate();
435b31ba src/eu/alefzero/owncloud/authenticator/AccountAuthenticatorService.java  (Lennart Rosam     2012-05-16 09:48:34 +0200 33)         mAuthenticator = new AccountAuthenticator(this);
435b31ba src/eu/alefzero/owncloud/authenticator/AccountAuthenticatorService.java  (Lennart Rosam     2012-05-16 09:48:34 +0200 34)     }
435b31ba src/eu/alefzero/owncloud/authenticator/AccountAuthenticatorService.java  (Lennart Rosam     2012-05-16 09:48:34 +0200 35) 
435b31ba src/eu/alefzero/owncloud/authenticator/AccountAuthenticatorService.java  (Lennart Rosam     2012-05-16 09:48:34 +0200 36)     @Override
435b31ba src/eu/alefzero/owncloud/authenticator/AccountAuthenticatorService.java  (Lennart Rosam     2012-05-16 09:48:34 +0200 37)     public IBinder onBind(Intent intent) {
435b31ba src/eu/alefzero/owncloud/authenticator/AccountAuthenticatorService.java  (Lennart Rosam     2012-05-16 09:48:34 +0200 38)         return mAuthenticator.getIBinder();
435b31ba src/eu/alefzero/owncloud/authenticator/AccountAuthenticatorService.java  (Lennart Rosam     2012-05-16 09:48:34 +0200 39)     }
^154bb85 src/eu/alefzero/owncloud/authenticator/AccountAuthenticatorService.java  (Bartek Przybylski 2011-08-19 22:37:35 +0200 40) 
^154bb85 src/eu/alefzero/owncloud/authenticator/AccountAuthenticatorService.java  (Bartek Przybylski 2011-08-19 22:37:35 +0200 41) }
