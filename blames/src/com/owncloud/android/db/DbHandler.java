f7649804 (Matthias Baumann 2013-02-25 08:21:33 +0100   1) /* ownCloud Android client application
f7649804 (Matthias Baumann 2013-02-25 08:21:33 +0100   2)  *   Copyright (C) 2011-2012  Bartek Przybylski
bdc0332c (David A. Velasco 2013-04-17 12:26:13 +0200   3)  *   Copyright (C) 2012-2013 ownCloud Inc.
f7649804 (Matthias Baumann 2013-02-25 08:21:33 +0100   4)  *
f7649804 (Matthias Baumann 2013-02-25 08:21:33 +0100   5)  *   This program is free software: you can redistribute it and/or modify
bdc0332c (David A. Velasco 2013-04-17 12:26:13 +0200   6)  *   it under the terms of the GNU General Public License version 2,
bdc0332c (David A. Velasco 2013-04-17 12:26:13 +0200   7)  *   as published by the Free Software Foundation.
f7649804 (Matthias Baumann 2013-02-25 08:21:33 +0100   8)  *
f7649804 (Matthias Baumann 2013-02-25 08:21:33 +0100   9)  *   This program is distributed in the hope that it will be useful,
f7649804 (Matthias Baumann 2013-02-25 08:21:33 +0100  10)  *   but WITHOUT ANY WARRANTY; without even the implied warranty of
f7649804 (Matthias Baumann 2013-02-25 08:21:33 +0100  11)  *   MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
f7649804 (Matthias Baumann 2013-02-25 08:21:33 +0100  12)  *   GNU General Public License for more details.
f7649804 (Matthias Baumann 2013-02-25 08:21:33 +0100  13)  *
f7649804 (Matthias Baumann 2013-02-25 08:21:33 +0100  14)  *   You should have received a copy of the GNU General Public License
f7649804 (Matthias Baumann 2013-02-25 08:21:33 +0100  15)  *   along with this program.  If not, see <http://www.gnu.org/licenses/>.
f7649804 (Matthias Baumann 2013-02-25 08:21:33 +0100  16)  *
f7649804 (Matthias Baumann 2013-02-25 08:21:33 +0100  17)  */
f7649804 (Matthias Baumann 2013-02-25 08:21:33 +0100  18) package com.owncloud.android.db;
f7649804 (Matthias Baumann 2013-02-25 08:21:33 +0100  19) 
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100  20) import com.owncloud.android.Log_OC;
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100  21) 
f7649804 (Matthias Baumann 2013-02-25 08:21:33 +0100  22) import android.content.ContentValues;
f7649804 (Matthias Baumann 2013-02-25 08:21:33 +0100  23) import android.content.Context;
f7649804 (Matthias Baumann 2013-02-25 08:21:33 +0100  24) import android.database.Cursor;
f7649804 (Matthias Baumann 2013-02-25 08:21:33 +0100  25) import android.database.sqlite.SQLiteDatabase;
f7649804 (Matthias Baumann 2013-02-25 08:21:33 +0100  26) import android.database.sqlite.SQLiteOpenHelper;
f7649804 (Matthias Baumann 2013-02-25 08:21:33 +0100  27) 
f7649804 (Matthias Baumann 2013-02-25 08:21:33 +0100  28) /**
f7649804 (Matthias Baumann 2013-02-25 08:21:33 +0100  29)  * Custom database helper for ownCloud
f7649804 (Matthias Baumann 2013-02-25 08:21:33 +0100  30)  * 
f7649804 (Matthias Baumann 2013-02-25 08:21:33 +0100  31)  * @author Bartek Przybylski
f7649804 (Matthias Baumann 2013-02-25 08:21:33 +0100  32)  * 
f7649804 (Matthias Baumann 2013-02-25 08:21:33 +0100  33)  */
f7649804 (Matthias Baumann 2013-02-25 08:21:33 +0100  34) public class DbHandler {
f7649804 (Matthias Baumann 2013-02-25 08:21:33 +0100  35)     private SQLiteDatabase mDB;
f7649804 (Matthias Baumann 2013-02-25 08:21:33 +0100  36)     private OpenerHelper mHelper;
f7649804 (Matthias Baumann 2013-02-25 08:21:33 +0100  37)     private final String mDatabaseName = "ownCloud";
5a22c7e7 (zerginator       2013-03-31 22:43:46 +0200  38)     private final int mDatabaseVersion = 3;
f7649804 (Matthias Baumann 2013-02-25 08:21:33 +0100  39) 
f7649804 (Matthias Baumann 2013-02-25 08:21:33 +0100  40)     private final String TABLE_INSTANT_UPLOAD = "instant_upload";
f7649804 (Matthias Baumann 2013-02-25 08:21:33 +0100  41) 
f7649804 (Matthias Baumann 2013-02-25 08:21:33 +0100  42)     public static final int UPLOAD_STATUS_UPLOAD_LATER = 0;
f7649804 (Matthias Baumann 2013-02-25 08:21:33 +0100  43)     public static final int UPLOAD_STATUS_UPLOAD_FAILED = 1;
f7649804 (Matthias Baumann 2013-02-25 08:21:33 +0100  44) 
f7649804 (Matthias Baumann 2013-02-25 08:21:33 +0100  45)     public DbHandler(Context context) {
f7649804 (Matthias Baumann 2013-02-25 08:21:33 +0100  46)         mHelper = new OpenerHelper(context);
f7649804 (Matthias Baumann 2013-02-25 08:21:33 +0100  47)         mDB = mHelper.getWritableDatabase();
f7649804 (Matthias Baumann 2013-02-25 08:21:33 +0100  48)     }
f7649804 (Matthias Baumann 2013-02-25 08:21:33 +0100  49) 
f7649804 (Matthias Baumann 2013-02-25 08:21:33 +0100  50)     public void close() {
f7649804 (Matthias Baumann 2013-02-25 08:21:33 +0100  51)         mDB.close();
f7649804 (Matthias Baumann 2013-02-25 08:21:33 +0100  52)     }
f7649804 (Matthias Baumann 2013-02-25 08:21:33 +0100  53) 
7392cfe5 (Matthias Baumann 2013-03-16 18:56:27 +0100  54)     public boolean putFileForLater(String filepath, String account, String message) {
f7649804 (Matthias Baumann 2013-02-25 08:21:33 +0100  55)         ContentValues cv = new ContentValues();
f7649804 (Matthias Baumann 2013-02-25 08:21:33 +0100  56)         cv.put("path", filepath);
f7649804 (Matthias Baumann 2013-02-25 08:21:33 +0100  57)         cv.put("account", account);
f7649804 (Matthias Baumann 2013-02-25 08:21:33 +0100  58)         cv.put("attempt", UPLOAD_STATUS_UPLOAD_LATER);
7392cfe5 (Matthias Baumann 2013-03-16 18:56:27 +0100  59)         cv.put("message", message);
f7649804 (Matthias Baumann 2013-02-25 08:21:33 +0100  60)         long result = mDB.insert(TABLE_INSTANT_UPLOAD, null, cv);
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100  61)         Log_OC.d(TABLE_INSTANT_UPLOAD, "putFileForLater returns with: " + result + " for file: " + filepath);
f7649804 (Matthias Baumann 2013-02-25 08:21:33 +0100  62)         return result != -1;
f7649804 (Matthias Baumann 2013-02-25 08:21:33 +0100  63)     }
f7649804 (Matthias Baumann 2013-02-25 08:21:33 +0100  64) 
7392cfe5 (Matthias Baumann 2013-03-16 18:56:27 +0100  65)     public int updateFileState(String filepath, Integer status, String message) {
f7649804 (Matthias Baumann 2013-02-25 08:21:33 +0100  66)         ContentValues cv = new ContentValues();
f7649804 (Matthias Baumann 2013-02-25 08:21:33 +0100  67)         cv.put("attempt", status);
7392cfe5 (Matthias Baumann 2013-03-16 18:56:27 +0100  68)         cv.put("message", message);
f7649804 (Matthias Baumann 2013-02-25 08:21:33 +0100  69)         int result = mDB.update(TABLE_INSTANT_UPLOAD, cv, "path=?", new String[] { filepath });
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100  70)         Log_OC.d(TABLE_INSTANT_UPLOAD, "updateFileState returns with: " + result + " for file: " + filepath);
f7649804 (Matthias Baumann 2013-02-25 08:21:33 +0100  71)         return result;
f7649804 (Matthias Baumann 2013-02-25 08:21:33 +0100  72)     }
f7649804 (Matthias Baumann 2013-02-25 08:21:33 +0100  73) 
f7649804 (Matthias Baumann 2013-02-25 08:21:33 +0100  74)     public Cursor getAwaitingFiles() {
f7649804 (Matthias Baumann 2013-02-25 08:21:33 +0100  75)         return mDB.query(TABLE_INSTANT_UPLOAD, null, "attempt=" + UPLOAD_STATUS_UPLOAD_LATER, null, null, null, null);
f7649804 (Matthias Baumann 2013-02-25 08:21:33 +0100  76)     }
f7649804 (Matthias Baumann 2013-02-25 08:21:33 +0100  77) 
f7649804 (Matthias Baumann 2013-02-25 08:21:33 +0100  78)     public Cursor getFailedFiles() {
f7649804 (Matthias Baumann 2013-02-25 08:21:33 +0100  79)         return mDB.query(TABLE_INSTANT_UPLOAD, null, "attempt>" + UPLOAD_STATUS_UPLOAD_LATER, null, null, null, null);
f7649804 (Matthias Baumann 2013-02-25 08:21:33 +0100  80)     }
f7649804 (Matthias Baumann 2013-02-25 08:21:33 +0100  81) 
f7649804 (Matthias Baumann 2013-02-25 08:21:33 +0100  82)     public void clearFiles() {
f7649804 (Matthias Baumann 2013-02-25 08:21:33 +0100  83)         mDB.delete(TABLE_INSTANT_UPLOAD, null, null);
f7649804 (Matthias Baumann 2013-02-25 08:21:33 +0100  84)     }
f7649804 (Matthias Baumann 2013-02-25 08:21:33 +0100  85) 
f7649804 (Matthias Baumann 2013-02-25 08:21:33 +0100  86)     /**
f7649804 (Matthias Baumann 2013-02-25 08:21:33 +0100  87)      * 
f7649804 (Matthias Baumann 2013-02-25 08:21:33 +0100  88)      * @param localPath
f7649804 (Matthias Baumann 2013-02-25 08:21:33 +0100  89)      * @return true when one or more pending files was removed
f7649804 (Matthias Baumann 2013-02-25 08:21:33 +0100  90)      */
f7649804 (Matthias Baumann 2013-02-25 08:21:33 +0100  91)     public boolean removeIUPendingFile(String localPath) {
f7649804 (Matthias Baumann 2013-02-25 08:21:33 +0100  92)         long result = mDB.delete(TABLE_INSTANT_UPLOAD, "path = ?", new String[] { localPath });
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100  93)         Log_OC.d(TABLE_INSTANT_UPLOAD, "delete returns with: " + result + " for file: " + localPath);
f7649804 (Matthias Baumann 2013-02-25 08:21:33 +0100  94)         return result != 0;
f7649804 (Matthias Baumann 2013-02-25 08:21:33 +0100  95) 
f7649804 (Matthias Baumann 2013-02-25 08:21:33 +0100  96)     }
f7649804 (Matthias Baumann 2013-02-25 08:21:33 +0100  97) 
f7649804 (Matthias Baumann 2013-02-25 08:21:33 +0100  98)     private class OpenerHelper extends SQLiteOpenHelper {
f7649804 (Matthias Baumann 2013-02-25 08:21:33 +0100  99)         public OpenerHelper(Context context) {
f7649804 (Matthias Baumann 2013-02-25 08:21:33 +0100 100)             super(context, mDatabaseName, null, mDatabaseVersion);
f7649804 (Matthias Baumann 2013-02-25 08:21:33 +0100 101)         }
f7649804 (Matthias Baumann 2013-02-25 08:21:33 +0100 102) 
f7649804 (Matthias Baumann 2013-02-25 08:21:33 +0100 103)         @Override
f7649804 (Matthias Baumann 2013-02-25 08:21:33 +0100 104)         public void onCreate(SQLiteDatabase db) {
f7649804 (Matthias Baumann 2013-02-25 08:21:33 +0100 105)             db.execSQL("CREATE TABLE " + TABLE_INSTANT_UPLOAD + " (" + " _id INTEGER PRIMARY KEY, " + " path TEXT,"
7392cfe5 (Matthias Baumann 2013-03-16 18:56:27 +0100 106)                     + " account TEXT,attempt INTEGER,message TEXT);");
f7649804 (Matthias Baumann 2013-02-25 08:21:33 +0100 107)         }
f7649804 (Matthias Baumann 2013-02-25 08:21:33 +0100 108) 
f7649804 (Matthias Baumann 2013-02-25 08:21:33 +0100 109)         @Override
f7649804 (Matthias Baumann 2013-02-25 08:21:33 +0100 110)         public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
7392cfe5 (Matthias Baumann 2013-03-16 18:56:27 +0100 111)             if (oldVersion < 2) {
7392cfe5 (Matthias Baumann 2013-03-16 18:56:27 +0100 112)                 db.execSQL("ALTER TABLE " + TABLE_INSTANT_UPLOAD + " ADD COLUMN attempt INTEGER;");
7392cfe5 (Matthias Baumann 2013-03-16 18:56:27 +0100 113)             }
7392cfe5 (Matthias Baumann 2013-03-16 18:56:27 +0100 114)             db.execSQL("ALTER TABLE " + TABLE_INSTANT_UPLOAD + " ADD COLUMN message TEXT;");
f7649804 (Matthias Baumann 2013-02-25 08:21:33 +0100 115) 
f7649804 (Matthias Baumann 2013-02-25 08:21:33 +0100 116)         }
f7649804 (Matthias Baumann 2013-02-25 08:21:33 +0100 117)     }
f7649804 (Matthias Baumann 2013-02-25 08:21:33 +0100 118) }
