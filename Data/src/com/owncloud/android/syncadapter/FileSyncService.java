00000000 (Not Committed Yet 2013-10-18 00:25:12 -0700  1) /* ownCloud Android client application
00000000 (Not Committed Yet 2013-10-18 00:25:12 -0700  2)  *   Copyright (C) 2011  Bartek Przybylski
00000000 (Not Committed Yet 2013-10-18 00:25:12 -0700  3)  *   Copyright (C) 2012-2013 ownCloud Inc.
00000000 (Not Committed Yet 2013-10-18 00:25:12 -0700  4)  *
00000000 (Not Committed Yet 2013-10-18 00:25:12 -0700  5)  *   This program is free software: you can redistribute it and/or modify
00000000 (Not Committed Yet 2013-10-18 00:25:12 -0700  6)  *   it under the terms of the GNU General Public License version 2,
00000000 (Not Committed Yet 2013-10-18 00:25:12 -0700  7)  *   as published by the Free Software Foundation.
00000000 (Not Committed Yet 2013-10-18 00:25:12 -0700  8)  *
00000000 (Not Committed Yet 2013-10-18 00:25:12 -0700  9)  *   This program is distributed in the hope that it will be useful,
00000000 (Not Committed Yet 2013-10-18 00:25:12 -0700 10)  *   but WITHOUT ANY WARRANTY; without even the implied warranty of
00000000 (Not Committed Yet 2013-10-18 00:25:12 -0700 11)  *   MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
00000000 (Not Committed Yet 2013-10-18 00:25:12 -0700 12)  *   GNU General Public License for more details.
00000000 (Not Committed Yet 2013-10-18 00:25:12 -0700 13)  *
00000000 (Not Committed Yet 2013-10-18 00:25:12 -0700 14)  *   You should have received a copy of the GNU General Public License
00000000 (Not Committed Yet 2013-10-18 00:25:12 -0700 15)  *   along with this program.  If not, see <http://www.gnu.org/licenses/>.
00000000 (Not Committed Yet 2013-10-18 00:25:12 -0700 16)  *
00000000 (Not Committed Yet 2013-10-18 00:25:12 -0700 17)  */
00000000 (Not Committed Yet 2013-10-18 00:25:12 -0700 18) package com.owncloud.android.syncadapter;
00000000 (Not Committed Yet 2013-10-18 00:25:12 -0700 19) 
00000000 (Not Committed Yet 2013-10-18 00:25:12 -0700 20) import android.app.Service;
00000000 (Not Committed Yet 2013-10-18 00:25:12 -0700 21) import android.content.Intent;
00000000 (Not Committed Yet 2013-10-18 00:25:12 -0700 22) import android.os.IBinder;
00000000 (Not Committed Yet 2013-10-18 00:25:12 -0700 23) 
00000000 (Not Committed Yet 2013-10-18 00:25:12 -0700 24) /**
00000000 (Not Committed Yet 2013-10-18 00:25:12 -0700 25)  * Background service for syncing files to our local Database
00000000 (Not Committed Yet 2013-10-18 00:25:12 -0700 26)  * 
00000000 (Not Committed Yet 2013-10-18 00:25:12 -0700 27)  * @author Bartek Przybylski
00000000 (Not Committed Yet 2013-10-18 00:25:12 -0700 28)  * 
00000000 (Not Committed Yet 2013-10-18 00:25:12 -0700 29)  */
00000000 (Not Committed Yet 2013-10-18 00:25:12 -0700 30) public class FileSyncService extends Service {
00000000 (Not Committed Yet 2013-10-18 00:25:12 -0700 31)     public static final String SYNC_MESSAGE = "ACCOUNT_SYNC";
00000000 (Not Committed Yet 2013-10-18 00:25:12 -0700 32)     public static final String SYNC_FOLDER_REMOTE_PATH = "SYNC_FOLDER_REMOTE_PATH";
00000000 (Not Committed Yet 2013-10-18 00:25:12 -0700 33)     public static final String IN_PROGRESS = "SYNC_IN_PROGRESS";
00000000 (Not Committed Yet 2013-10-18 00:25:12 -0700 34)     public static final String ACCOUNT_NAME = "ACCOUNT_NAME";
00000000 (Not Committed Yet 2013-10-18 00:25:12 -0700 35)     public static final String SYNC_RESULT = "SYNC_RESULT";
00000000 (Not Committed Yet 2013-10-18 00:25:12 -0700 36) 
00000000 (Not Committed Yet 2013-10-18 00:25:12 -0700 37)     /*
00000000 (Not Committed Yet 2013-10-18 00:25:12 -0700 38)      * {@inheritDoc}
00000000 (Not Committed Yet 2013-10-18 00:25:12 -0700 39)      */
00000000 (Not Committed Yet 2013-10-18 00:25:12 -0700 40)     @Override
00000000 (Not Committed Yet 2013-10-18 00:25:12 -0700 41)     public void onCreate() {
00000000 (Not Committed Yet 2013-10-18 00:25:12 -0700 42)     }
00000000 (Not Committed Yet 2013-10-18 00:25:12 -0700 43) 
00000000 (Not Committed Yet 2013-10-18 00:25:12 -0700 44)     /*
00000000 (Not Committed Yet 2013-10-18 00:25:12 -0700 45)      * {@inheritDoc}
00000000 (Not Committed Yet 2013-10-18 00:25:12 -0700 46)      */
00000000 (Not Committed Yet 2013-10-18 00:25:12 -0700 47)     @Override
00000000 (Not Committed Yet 2013-10-18 00:25:12 -0700 48)     public IBinder onBind(Intent intent) {
00000000 (Not Committed Yet 2013-10-18 00:25:12 -0700 49)        return new FileSyncAdapter(getApplicationContext(), true).getSyncAdapterBinder();
00000000 (Not Committed Yet 2013-10-18 00:25:12 -0700 50)     }
00000000 (Not Committed Yet 2013-10-18 00:25:12 -0700 51) }
