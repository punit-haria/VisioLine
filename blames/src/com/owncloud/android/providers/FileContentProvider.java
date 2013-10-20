8e36e7cc (zerginator       2013-03-12 07:56:27 +0100   1) /* ownCloud Android client application
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100   2)  *   Copyright (C) 2011  Bartek Przybylski
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100   3)  *   Copyright (C) 2012-2013 ownCloud Inc.
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100   4)  *
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100   5)  *   This program is free software: you can redistribute it and/or modify
bdc0332c (David A. Velasco 2013-04-17 12:26:13 +0200   6)  *   it under the terms of the GNU General Public License version 2,
bdc0332c (David A. Velasco 2013-04-17 12:26:13 +0200   7)  *   as published by the Free Software Foundation.
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100   8)  *
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100   9)  *   This program is distributed in the hope that it will be useful,
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100  10)  *   but WITHOUT ANY WARRANTY; without even the implied warranty of
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100  11)  *   MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100  12)  *   GNU General Public License for more details.
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100  13)  *
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100  14)  *   You should have received a copy of the GNU General Public License
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100  15)  *   along with this program.  If not, see <http://www.gnu.org/licenses/>.
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100  16)  *
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100  17)  */
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100  18) 
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100  19) package com.owncloud.android.providers;
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100  20) 
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100  21) import java.util.HashMap;
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100  22) 
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100  23) import com.owncloud.android.Log_OC;
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100  24) import com.owncloud.android.db.ProviderMeta;
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100  25) import com.owncloud.android.db.ProviderMeta.ProviderTableMeta;
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100  26) 
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100  27) 
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100  28) import android.content.ContentProvider;
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100  29) import android.content.ContentUris;
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100  30) import android.content.ContentValues;
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100  31) import android.content.Context;
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100  32) import android.content.UriMatcher;
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100  33) import android.database.Cursor;
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100  34) import android.database.SQLException;
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100  35) import android.database.sqlite.SQLiteDatabase;
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100  36) import android.database.sqlite.SQLiteOpenHelper;
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100  37) import android.database.sqlite.SQLiteQueryBuilder;
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100  38) import android.net.Uri;
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100  39) import android.text.TextUtils;
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100  40) 
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100  41) /**
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100  42)  * The ContentProvider for the ownCloud App.
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100  43)  * 
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100  44)  * @author Bartek Przybylski
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100  45)  * 
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100  46)  */
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100  47) public class FileContentProvider extends ContentProvider {
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100  48) 
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100  49)     private DataBaseHelper mDbHelper;
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100  50) 
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100  51)     private static HashMap<String, String> mProjectionMap;
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100  52)     static {
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100  53)         mProjectionMap = new HashMap<String, String>();
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100  54)         mProjectionMap.put(ProviderTableMeta._ID, ProviderTableMeta._ID);
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100  55)         mProjectionMap.put(ProviderTableMeta.FILE_PARENT,
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100  56)                 ProviderTableMeta.FILE_PARENT);
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100  57)         mProjectionMap.put(ProviderTableMeta.FILE_PATH,
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100  58)                 ProviderTableMeta.FILE_PATH);
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100  59)         mProjectionMap.put(ProviderTableMeta.FILE_NAME,
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100  60)                 ProviderTableMeta.FILE_NAME);
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100  61)         mProjectionMap.put(ProviderTableMeta.FILE_CREATION,
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100  62)                 ProviderTableMeta.FILE_CREATION);
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100  63)         mProjectionMap.put(ProviderTableMeta.FILE_MODIFIED,
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100  64)                 ProviderTableMeta.FILE_MODIFIED);
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100  65)         mProjectionMap.put(ProviderTableMeta.FILE_MODIFIED_AT_LAST_SYNC_FOR_DATA,
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100  66)                 ProviderTableMeta.FILE_MODIFIED_AT_LAST_SYNC_FOR_DATA);
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100  67)         mProjectionMap.put(ProviderTableMeta.FILE_CONTENT_LENGTH,
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100  68)                 ProviderTableMeta.FILE_CONTENT_LENGTH);
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100  69)         mProjectionMap.put(ProviderTableMeta.FILE_CONTENT_TYPE,
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100  70)                 ProviderTableMeta.FILE_CONTENT_TYPE);
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100  71)         mProjectionMap.put(ProviderTableMeta.FILE_STORAGE_PATH,
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100  72)                 ProviderTableMeta.FILE_STORAGE_PATH);
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100  73)         mProjectionMap.put(ProviderTableMeta.FILE_LAST_SYNC_DATE,
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100  74)                 ProviderTableMeta.FILE_LAST_SYNC_DATE);
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100  75)         mProjectionMap.put(ProviderTableMeta.FILE_LAST_SYNC_DATE_FOR_DATA,
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100  76)                 ProviderTableMeta.FILE_LAST_SYNC_DATE_FOR_DATA);
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100  77)         mProjectionMap.put(ProviderTableMeta.FILE_KEEP_IN_SYNC,
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100  78)                 ProviderTableMeta.FILE_KEEP_IN_SYNC);
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100  79)         mProjectionMap.put(ProviderTableMeta.FILE_ACCOUNT_OWNER,
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100  80)                 ProviderTableMeta.FILE_ACCOUNT_OWNER);
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100  81)     }
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100  82) 
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100  83)     private static final int SINGLE_FILE = 1;
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100  84)     private static final int DIRECTORY = 2;
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100  85)     private static final int ROOT_DIRECTORY = 3;
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100  86)     private static final UriMatcher mUriMatcher;
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100  87)     static {
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100  88)         mUriMatcher = new UriMatcher(UriMatcher.NO_MATCH);
4b465cfc (Mik              2013-07-29 19:12:55 +0200  89)         mUriMatcher.addURI(ProviderMeta.AUTHORITY_FILES, null, ROOT_DIRECTORY);
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100  90)         mUriMatcher.addURI(ProviderMeta.AUTHORITY_FILES, "file/", SINGLE_FILE);
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100  91)         mUriMatcher.addURI(ProviderMeta.AUTHORITY_FILES, "file/#", SINGLE_FILE);
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100  92)         mUriMatcher.addURI(ProviderMeta.AUTHORITY_FILES, "dir/#", DIRECTORY);
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100  93)     }
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100  94) 
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100  95)     @Override
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100  96)     public int delete(Uri uri, String where, String[] whereArgs) {
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100  97)         SQLiteDatabase db = mDbHelper.getWritableDatabase();
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100  98)         int count = 0;
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100  99)         switch (mUriMatcher.match(uri)) {
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100 100)         case SINGLE_FILE:
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100 101)             count = db.delete(ProviderTableMeta.DB_NAME,
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100 102)                     ProviderTableMeta._ID
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100 103)                             + "="
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100 104)                             + uri.getPathSegments().get(1)
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100 105)                             + (!TextUtils.isEmpty(where) ? " AND (" + where
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100 106)                                     + ")" : ""), whereArgs);
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100 107)             break;
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100 108)         case ROOT_DIRECTORY:
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100 109)             count = db.delete(ProviderTableMeta.DB_NAME, where, whereArgs);
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100 110)             break;
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100 111)         default:
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100 112)             throw new IllegalArgumentException("Unknown uri: " + uri.toString());
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100 113)         }
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100 114)         getContext().getContentResolver().notifyChange(uri, null);
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100 115)         return count;
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100 116)     }
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100 117) 
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100 118)     @Override
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100 119)     public String getType(Uri uri) {
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100 120)         switch (mUriMatcher.match(uri)) {
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100 121)         case ROOT_DIRECTORY:
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100 122)             return ProviderTableMeta.CONTENT_TYPE;
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100 123)         case SINGLE_FILE:
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100 124)             return ProviderTableMeta.CONTENT_TYPE_ITEM;
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100 125)         default:
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100 126)             throw new IllegalArgumentException("Unknown Uri id."
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100 127)                     + uri.toString());
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100 128)         }
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100 129)     }
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100 130) 
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100 131)     @Override
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100 132)     public Uri insert(Uri uri, ContentValues values) {
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100 133)         if (mUriMatcher.match(uri) != SINGLE_FILE &&
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100 134)             mUriMatcher.match(uri) != ROOT_DIRECTORY) {
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100 135)             
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100 136)             throw new IllegalArgumentException("Unknown uri id: " + uri);
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100 137)         }
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100 138) 
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100 139)         SQLiteDatabase db = mDbHelper.getWritableDatabase();
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100 140)         long rowId = db.insert(ProviderTableMeta.DB_NAME, null, values);
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100 141)         if (rowId > 0) {
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100 142)             Uri insertedFileUri = ContentUris.withAppendedId(
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100 143)                     ProviderTableMeta.CONTENT_URI_FILE, rowId);
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100 144)             getContext().getContentResolver().notifyChange(insertedFileUri,
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100 145)                     null);
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100 146)             return insertedFileUri;
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100 147)         }
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100 148)         throw new SQLException("ERROR " + uri);
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100 149)     }
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100 150) 
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100 151)     @Override
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100 152)     public boolean onCreate() {
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100 153)         mDbHelper = new DataBaseHelper(getContext());
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100 154)         return true;
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100 155)     }
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100 156) 
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100 157)     @Override
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100 158)     public Cursor query(Uri uri, String[] projection, String selection,
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100 159)             String[] selectionArgs, String sortOrder) {
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100 160)         SQLiteQueryBuilder sqlQuery = new SQLiteQueryBuilder();
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100 161) 
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100 162)         sqlQuery.setTables(ProviderTableMeta.DB_NAME);
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100 163)         sqlQuery.setProjectionMap(mProjectionMap);
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100 164) 
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100 165)         switch (mUriMatcher.match(uri)) {
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100 166)         case ROOT_DIRECTORY:
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100 167)             break;
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100 168)         case DIRECTORY:
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100 169)             sqlQuery.appendWhere(ProviderTableMeta.FILE_PARENT + "="
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100 170)                     + uri.getPathSegments().get(1));
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100 171)             break;
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100 172)         case SINGLE_FILE:
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100 173)             if (uri.getPathSegments().size() > 1) {
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100 174)                 sqlQuery.appendWhere(ProviderTableMeta._ID + "="
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100 175)                         + uri.getPathSegments().get(1));
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100 176)             }
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100 177)             break;
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100 178)         default:
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100 179)             throw new IllegalArgumentException("Unknown uri id: " + uri);
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100 180)         }
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100 181) 
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100 182)         String order;
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100 183)         if (TextUtils.isEmpty(sortOrder)) {
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100 184)             order = ProviderTableMeta.DEFAULT_SORT_ORDER;
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100 185)         } else {
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100 186)             order = sortOrder;
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100 187)         }
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100 188) 
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100 189)         SQLiteDatabase db = mDbHelper.getReadableDatabase();
68d4c74f (masensio         2013-09-16 10:54:20 +0200 190)         // DB case_sensitive
68d4c74f (masensio         2013-09-16 10:54:20 +0200 191)         db.execSQL("PRAGMA case_sensitive_like = true");
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100 192)         Cursor c = sqlQuery.query(db, projection, selection, selectionArgs,
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100 193)                 null, null, order);
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100 194) 
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100 195)         c.setNotificationUri(getContext().getContentResolver(), uri);
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100 196) 
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100 197)         return c;
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100 198)     }
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100 199) 
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100 200)     @Override
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100 201)     public int update(Uri uri, ContentValues values, String selection,
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100 202)             String[] selectionArgs) {
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100 203)         return mDbHelper.getWritableDatabase().update(
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100 204)                 ProviderTableMeta.DB_NAME, values, selection, selectionArgs);
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100 205)     }
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100 206) 
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100 207)     class DataBaseHelper extends SQLiteOpenHelper {
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100 208) 
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100 209)         public DataBaseHelper(Context context) {
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100 210)             super(context, ProviderMeta.DB_NAME, null, ProviderMeta.DB_VERSION);
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100 211) 
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100 212)         }
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100 213) 
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100 214)         @Override
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100 215)         public void onCreate(SQLiteDatabase db) {
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100 216)             // files table
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100 217)             Log_OC.i("SQL", "Entering in onCreate");
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100 218)             db.execSQL("CREATE TABLE " + ProviderTableMeta.DB_NAME + "("
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100 219)                     + ProviderTableMeta._ID + " INTEGER PRIMARY KEY, "
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100 220)                     + ProviderTableMeta.FILE_NAME + " TEXT, "
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100 221)                     + ProviderTableMeta.FILE_PATH + " TEXT, "
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100 222)                     + ProviderTableMeta.FILE_PARENT + " INTEGER, "
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100 223)                     + ProviderTableMeta.FILE_CREATION + " INTEGER, "
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100 224)                     + ProviderTableMeta.FILE_MODIFIED + " INTEGER, "
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100 225)                     + ProviderTableMeta.FILE_CONTENT_TYPE + " TEXT, "
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100 226)                     + ProviderTableMeta.FILE_CONTENT_LENGTH + " INTEGER, "
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100 227)                     + ProviderTableMeta.FILE_STORAGE_PATH + " TEXT, "
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100 228)                     + ProviderTableMeta.FILE_ACCOUNT_OWNER + " TEXT, "
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100 229)                     + ProviderTableMeta.FILE_LAST_SYNC_DATE + " INTEGER, "
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100 230)                     + ProviderTableMeta.FILE_KEEP_IN_SYNC + " INTEGER, "
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100 231)                     + ProviderTableMeta.FILE_LAST_SYNC_DATE_FOR_DATA + " INTEGER, "
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100 232)                     + ProviderTableMeta.FILE_MODIFIED_AT_LAST_SYNC_FOR_DATA + " INTEGER );"
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100 233)                     );
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100 234)         }
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100 235) 
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100 236)         @Override
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100 237)         public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100 238)             Log_OC.i("SQL", "Entering in onUpgrade");
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100 239)             boolean upgraded = false; 
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100 240)             if (oldVersion == 1 && newVersion >= 2) {
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100 241)                 Log_OC.i("SQL", "Entering in the #1 ADD in onUpgrade");
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100 242)                 db.execSQL("ALTER TABLE " + ProviderTableMeta.DB_NAME +
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100 243)                            " ADD COLUMN " + ProviderTableMeta.FILE_KEEP_IN_SYNC  + " INTEGER " +
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100 244)                            " DEFAULT 0");
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100 245)                 upgraded = true;
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100 246)             }
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100 247)             if (oldVersion < 3 && newVersion >= 3) {
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100 248)                 Log_OC.i("SQL", "Entering in the #2 ADD in onUpgrade");
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100 249)                 db.beginTransaction();
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100 250)                 try {
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100 251)                     db.execSQL("ALTER TABLE " + ProviderTableMeta.DB_NAME +
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100 252)                                " ADD COLUMN " + ProviderTableMeta.FILE_LAST_SYNC_DATE_FOR_DATA  + " INTEGER " +
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100 253)                                " DEFAULT 0");
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100 254)                     
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100 255)                     // assume there are not local changes pending to upload
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100 256)                     db.execSQL("UPDATE " + ProviderTableMeta.DB_NAME + 
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100 257)                             " SET " + ProviderTableMeta.FILE_LAST_SYNC_DATE_FOR_DATA + " = " + System.currentTimeMillis() + 
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100 258)                             " WHERE " + ProviderTableMeta.FILE_STORAGE_PATH + " IS NOT NULL");
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100 259)                  
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100 260)                     upgraded = true;
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100 261)                     db.setTransactionSuccessful();
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100 262)                 } finally {
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100 263)                     db.endTransaction();
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100 264)                 }
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100 265)             }
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100 266)             if (oldVersion < 4 && newVersion >= 4) {
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100 267)                 Log_OC.i("SQL", "Entering in the #3 ADD in onUpgrade");
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100 268)                 db.beginTransaction();
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100 269)                 try {
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100 270)                     db .execSQL("ALTER TABLE " + ProviderTableMeta.DB_NAME +
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100 271)                            " ADD COLUMN " + ProviderTableMeta.FILE_MODIFIED_AT_LAST_SYNC_FOR_DATA  + " INTEGER " +
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100 272)                            " DEFAULT 0");
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100 273)                 
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100 274)                     db.execSQL("UPDATE " + ProviderTableMeta.DB_NAME + 
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100 275)                            " SET " + ProviderTableMeta.FILE_MODIFIED_AT_LAST_SYNC_FOR_DATA + " = " + ProviderTableMeta.FILE_MODIFIED + 
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100 276)                            " WHERE " + ProviderTableMeta.FILE_STORAGE_PATH + " IS NOT NULL");
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100 277)                 
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100 278)                     upgraded = true;
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100 279)                     db.setTransactionSuccessful();
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100 280)                 } finally {
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100 281)                     db.endTransaction();
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100 282)                 }
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100 283)             }
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100 284)             if (!upgraded)
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100 285)                 Log_OC.i("SQL", "OUT of the ADD in onUpgrade; oldVersion == " + oldVersion + ", newVersion == " + newVersion);
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100 286)         }
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100 287) 
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100 288)     }
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100 289) 
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100 290) }
