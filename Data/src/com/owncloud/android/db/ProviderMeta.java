00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700  1) /* ownCloud Android client application
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700  2)  *   Copyright (C) 2011  Bartek Przybylski
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700  3)  *   Copyright (C) 2012-2013 ownCloud Inc.
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700  4)  *
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700  5)  *   This program is free software: you can redistribute it and/or modify
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700  6)  *   it under the terms of the GNU General Public License version 2,
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700  7)  *   as published by the Free Software Foundation.
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700  8)  *
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700  9)  *   This program is distributed in the hope that it will be useful,
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700 10)  *   but WITHOUT ANY WARRANTY; without even the implied warranty of
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700 11)  *   MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700 12)  *   GNU General Public License for more details.
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700 13)  *
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700 14)  *   You should have received a copy of the GNU General Public License
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700 15)  *   along with this program.  If not, see <http://www.gnu.org/licenses/>.
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700 16)  *
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700 17)  */
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700 18) package com.owncloud.android.db;
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700 19) 
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700 20) import android.net.Uri;
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700 21) import android.provider.BaseColumns;
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700 22) 
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700 23) /**
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700 24)  * Meta-Class that holds various static field information
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700 25)  * 
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700 26)  * @author Bartek Przybylski
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700 27)  * 
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700 28)  */
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700 29) public class ProviderMeta {
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700 30) 
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700 31)     public static final String AUTHORITY_FILES = "org.owncloud";
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700 32)     public static final String DB_FILE = "owncloud.db";
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700 33)     public static final String DB_NAME = "filelist";
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700 34)     public static final int DB_VERSION = 4;
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700 35) 
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700 36)     private ProviderMeta() {
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700 37)     }
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700 38) 
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700 39)     static public class ProviderTableMeta implements BaseColumns {
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700 40)         public static final String DB_NAME = "filelist";
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700 41)         public static final Uri CONTENT_URI = Uri.parse("content://"
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700 42)                 + AUTHORITY_FILES + "/");
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700 43)         public static final Uri CONTENT_URI_FILE = Uri.parse("content://"
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700 44)                 + AUTHORITY_FILES + "/file");
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700 45)         public static final Uri CONTENT_URI_DIR = Uri.parse("content://"
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700 46)                 + AUTHORITY_FILES + "/dir");
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700 47) 
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700 48)         public static final String CONTENT_TYPE = "vnd.android.cursor.dir/vnd.owncloud.file";
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700 49)         public static final String CONTENT_TYPE_ITEM = "vnd.android.cursor.item/vnd.owncloud.file";
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700 50) 
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700 51)         public static final String FILE_PARENT = "parent";
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700 52)         public static final String FILE_NAME = "filename";
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700 53)         public static final String FILE_CREATION = "created";
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700 54)         public static final String FILE_MODIFIED = "modified";
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700 55)         public static final String FILE_MODIFIED_AT_LAST_SYNC_FOR_DATA = "modified_at_last_sync_for_data";
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700 56)         public static final String FILE_CONTENT_LENGTH = "content_length";
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700 57)         public static final String FILE_CONTENT_TYPE = "content_type";
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700 58)         public static final String FILE_STORAGE_PATH = "media_path";
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700 59)         public static final String FILE_PATH = "path";
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700 60)         public static final String FILE_ACCOUNT_OWNER = "file_owner";
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700 61)         public static final String FILE_LAST_SYNC_DATE = "last_sync_date";  // _for_properties, but let's keep it as it is
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700 62)         public static final String FILE_LAST_SYNC_DATE_FOR_DATA = "last_sync_date_for_data";
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700 63)         public static final String FILE_KEEP_IN_SYNC = "keep_in_sync";
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700 64) 
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700 65)         public static final String DEFAULT_SORT_ORDER = FILE_NAME
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700 66)                 + " collate nocase asc";
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700 67) 
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700 68)     }
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700 69) }
