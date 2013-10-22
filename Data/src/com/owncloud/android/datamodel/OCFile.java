0174908b src/eu/alefzero/owncloud/datamodel/OCFile.java (Bartek Przybylski 2012-04-07 14:37:03 +0200   1) /* ownCloud Android client application
0174908b src/eu/alefzero/owncloud/datamodel/OCFile.java (Bartek Przybylski 2012-04-07 14:37:03 +0200   2)  *   Copyright (C) 2012  Bartek Przybylski
bb257ec7 src/com/owncloud/android/datamodel/OCFile.java (David A. Velasco  2013-02-07 18:45:10 +0100   3)  *   Copyright (C) 2012-2013 ownCloud Inc.
0174908b src/eu/alefzero/owncloud/datamodel/OCFile.java (Bartek Przybylski 2012-04-07 14:37:03 +0200   4)  *
0174908b src/eu/alefzero/owncloud/datamodel/OCFile.java (Bartek Przybylski 2012-04-07 14:37:03 +0200   5)  *   This program is free software: you can redistribute it and/or modify
bdc0332c src/com/owncloud/android/datamodel/OCFile.java (David A. Velasco  2013-04-17 12:26:13 +0200   6)  *   it under the terms of the GNU General Public License version 2,
bdc0332c src/com/owncloud/android/datamodel/OCFile.java (David A. Velasco  2013-04-17 12:26:13 +0200   7)  *   as published by the Free Software Foundation.
0174908b src/eu/alefzero/owncloud/datamodel/OCFile.java (Bartek Przybylski 2012-04-07 14:37:03 +0200   8)  *
0174908b src/eu/alefzero/owncloud/datamodel/OCFile.java (Bartek Przybylski 2012-04-07 14:37:03 +0200   9)  *   This program is distributed in the hope that it will be useful,
0174908b src/eu/alefzero/owncloud/datamodel/OCFile.java (Bartek Przybylski 2012-04-07 14:37:03 +0200  10)  *   but WITHOUT ANY WARRANTY; without even the implied warranty of
0174908b src/eu/alefzero/owncloud/datamodel/OCFile.java (Bartek Przybylski 2012-04-07 14:37:03 +0200  11)  *   MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
0174908b src/eu/alefzero/owncloud/datamodel/OCFile.java (Bartek Przybylski 2012-04-07 14:37:03 +0200  12)  *   GNU General Public License for more details.
0174908b src/eu/alefzero/owncloud/datamodel/OCFile.java (Bartek Przybylski 2012-04-07 14:37:03 +0200  13)  *
0174908b src/eu/alefzero/owncloud/datamodel/OCFile.java (Bartek Przybylski 2012-04-07 14:37:03 +0200  14)  *   You should have received a copy of the GNU General Public License
0174908b src/eu/alefzero/owncloud/datamodel/OCFile.java (Bartek Przybylski 2012-04-07 14:37:03 +0200  15)  *   along with this program.  If not, see <http://www.gnu.org/licenses/>.
0174908b src/eu/alefzero/owncloud/datamodel/OCFile.java (Bartek Przybylski 2012-04-07 14:37:03 +0200  16)  *
0174908b src/eu/alefzero/owncloud/datamodel/OCFile.java (Bartek Przybylski 2012-04-07 14:37:03 +0200  17)  */
0174908b src/eu/alefzero/owncloud/datamodel/OCFile.java (Bartek Przybylski 2012-04-07 14:37:03 +0200  18) 
a4ba6170 src/com/owncloud/android/datamodel/OCFile.java (Bartek Przybylski 2012-07-31 17:43:37 +0200  19) package com.owncloud.android.datamodel;
0174908b src/eu/alefzero/owncloud/datamodel/OCFile.java (Bartek Przybylski 2012-04-07 14:37:03 +0200  20) 
58efa22c src/eu/alefzero/owncloud/datamodel/OCFile.java (Bartek Przybylski 2012-04-07 15:07:14 +0200  21) import java.io.File;
0174908b src/eu/alefzero/owncloud/datamodel/OCFile.java (Bartek Przybylski 2012-04-07 14:37:03 +0200  22) 
8e36e7cc src/com/owncloud/android/datamodel/OCFile.java (zerginator        2013-03-12 07:56:27 +0100  23) import com.owncloud.android.Log_OC;
8e36e7cc src/com/owncloud/android/datamodel/OCFile.java (zerginator        2013-03-12 07:56:27 +0100  24) 
020d2885 src/eu/alefzero/owncloud/datamodel/OCFile.java (Lennart Rosam     2012-05-15 23:12:07 +0200  25) import android.os.Parcel;
020d2885 src/eu/alefzero/owncloud/datamodel/OCFile.java (Lennart Rosam     2012-05-15 23:12:07 +0200  26) import android.os.Parcelable;
a212611a src/com/owncloud/android/datamodel/OCFile.java (David A. Velasco  2013-03-01 14:55:59 +0100  27) import android.webkit.MimeTypeMap;
399b8409 src/eu/alefzero/owncloud/datamodel/OCFile.java (Lennart Rosam     2012-04-14 13:53:56 +0200  28) 
49ad2498 src/eu/alefzero/owncloud/datamodel/OCFile.java (Bartek Przybylski 2012-05-23 22:40:31 +0200  29) public class OCFile implements Parcelable, Comparable<OCFile> {
020d2885 src/eu/alefzero/owncloud/datamodel/OCFile.java (Lennart Rosam     2012-05-15 23:12:07 +0200  30) 
435b31ba src/eu/alefzero/owncloud/datamodel/OCFile.java (Lennart Rosam     2012-05-16 09:48:34 +0200  31)     public static final Parcelable.Creator<OCFile> CREATOR = new Parcelable.Creator<OCFile>() {
435b31ba src/eu/alefzero/owncloud/datamodel/OCFile.java (Lennart Rosam     2012-05-16 09:48:34 +0200  32)         @Override
435b31ba src/eu/alefzero/owncloud/datamodel/OCFile.java (Lennart Rosam     2012-05-16 09:48:34 +0200  33)         public OCFile createFromParcel(Parcel source) {
435b31ba src/eu/alefzero/owncloud/datamodel/OCFile.java (Lennart Rosam     2012-05-16 09:48:34 +0200  34)             return new OCFile(source);
435b31ba src/eu/alefzero/owncloud/datamodel/OCFile.java (Lennart Rosam     2012-05-16 09:48:34 +0200  35)         }
435b31ba src/eu/alefzero/owncloud/datamodel/OCFile.java (Lennart Rosam     2012-05-16 09:48:34 +0200  36) 
435b31ba src/eu/alefzero/owncloud/datamodel/OCFile.java (Lennart Rosam     2012-05-16 09:48:34 +0200  37)         @Override
435b31ba src/eu/alefzero/owncloud/datamodel/OCFile.java (Lennart Rosam     2012-05-16 09:48:34 +0200  38)         public OCFile[] newArray(int size) {
435b31ba src/eu/alefzero/owncloud/datamodel/OCFile.java (Lennart Rosam     2012-05-16 09:48:34 +0200  39)             return new OCFile[size];
435b31ba src/eu/alefzero/owncloud/datamodel/OCFile.java (Lennart Rosam     2012-05-16 09:48:34 +0200  40)         }
435b31ba src/eu/alefzero/owncloud/datamodel/OCFile.java (Lennart Rosam     2012-05-16 09:48:34 +0200  41)     };
435b31ba src/eu/alefzero/owncloud/datamodel/OCFile.java (Lennart Rosam     2012-05-16 09:48:34 +0200  42) 
c6b553f6 src/eu/alefzero/owncloud/datamodel/OCFile.java (David A. Velasco  2012-07-17 12:59:15 +0200  43)     public static final String PATH_SEPARATOR = "/";
85e9a40d src/com/owncloud/android/datamodel/OCFile.java (David A. Velasco  2012-11-08 17:21:45 +0100  44) 
85e9a40d src/com/owncloud/android/datamodel/OCFile.java (David A. Velasco  2012-11-08 17:21:45 +0100  45)     private static final String TAG = OCFile.class.getSimpleName();
c6b553f6 src/eu/alefzero/owncloud/datamodel/OCFile.java (David A. Velasco  2012-07-17 12:59:15 +0200  46)     
435b31ba src/eu/alefzero/owncloud/datamodel/OCFile.java (Lennart Rosam     2012-05-16 09:48:34 +0200  47)     private long mId;
435b31ba src/eu/alefzero/owncloud/datamodel/OCFile.java (Lennart Rosam     2012-05-16 09:48:34 +0200  48)     private long mParentId;
435b31ba src/eu/alefzero/owncloud/datamodel/OCFile.java (Lennart Rosam     2012-05-16 09:48:34 +0200  49)     private long mLength;
435b31ba src/eu/alefzero/owncloud/datamodel/OCFile.java (Lennart Rosam     2012-05-16 09:48:34 +0200  50)     private long mCreationTimestamp;
435b31ba src/eu/alefzero/owncloud/datamodel/OCFile.java (Lennart Rosam     2012-05-16 09:48:34 +0200  51)     private long mModifiedTimestamp;
abd5f515 src/com/owncloud/android/datamodel/OCFile.java (David A. Velasco  2012-12-05 16:14:01 +0100  52)     private long mModifiedTimestampAtLastSyncForData;
435b31ba src/eu/alefzero/owncloud/datamodel/OCFile.java (Lennart Rosam     2012-05-16 09:48:34 +0200  53)     private String mRemotePath;
435b31ba src/eu/alefzero/owncloud/datamodel/OCFile.java (Lennart Rosam     2012-05-16 09:48:34 +0200  54)     private String mLocalPath;
435b31ba src/eu/alefzero/owncloud/datamodel/OCFile.java (Lennart Rosam     2012-05-16 09:48:34 +0200  55)     private String mMimeType;
435b31ba src/eu/alefzero/owncloud/datamodel/OCFile.java (Lennart Rosam     2012-05-16 09:48:34 +0200  56)     private boolean mNeedsUpdating;
ff2271a8 src/com/owncloud/android/datamodel/OCFile.java (David A. Velasco  2012-11-15 17:18:04 +0100  57)     private long mLastSyncDateForProperties;
ff2271a8 src/com/owncloud/android/datamodel/OCFile.java (David A. Velasco  2012-11-15 17:18:04 +0100  58)     private long mLastSyncDateForData;
3113c459 src/eu/alefzero/owncloud/datamodel/OCFile.java (Bartek Przybylski 2012-07-08 16:57:48 +0200  59)     private boolean mKeepInSync;
435b31ba src/eu/alefzero/owncloud/datamodel/OCFile.java (Lennart Rosam     2012-05-16 09:48:34 +0200  60) 
ff2271a8 src/com/owncloud/android/datamodel/OCFile.java (David A. Velasco  2012-11-15 17:18:04 +0100  61)     private String mEtag;
ff2271a8 src/com/owncloud/android/datamodel/OCFile.java (David A. Velasco  2012-11-15 17:18:04 +0100  62) 
435b31ba src/eu/alefzero/owncloud/datamodel/OCFile.java (Lennart Rosam     2012-05-16 09:48:34 +0200  63)     /**
c6b553f6 src/eu/alefzero/owncloud/datamodel/OCFile.java (David A. Velasco  2012-07-17 12:59:15 +0200  64)      * Create new {@link OCFile} with given path.
c6b553f6 src/eu/alefzero/owncloud/datamodel/OCFile.java (David A. Velasco  2012-07-17 12:59:15 +0200  65)      * 
c6b553f6 src/eu/alefzero/owncloud/datamodel/OCFile.java (David A. Velasco  2012-07-17 12:59:15 +0200  66)      * The path received must be URL-decoded. Path separator must be OCFile.PATH_SEPARATOR, and it must be the first character in 'path'.
435b31ba src/eu/alefzero/owncloud/datamodel/OCFile.java (Lennart Rosam     2012-05-16 09:48:34 +0200  67)      * 
c6b553f6 src/eu/alefzero/owncloud/datamodel/OCFile.java (David A. Velasco  2012-07-17 12:59:15 +0200  68)      * @param path The remote path of the file.
435b31ba src/eu/alefzero/owncloud/datamodel/OCFile.java (Lennart Rosam     2012-05-16 09:48:34 +0200  69)      */
435b31ba src/eu/alefzero/owncloud/datamodel/OCFile.java (Lennart Rosam     2012-05-16 09:48:34 +0200  70)     public OCFile(String path) {
435b31ba src/eu/alefzero/owncloud/datamodel/OCFile.java (Lennart Rosam     2012-05-16 09:48:34 +0200  71)         resetData();
435b31ba src/eu/alefzero/owncloud/datamodel/OCFile.java (Lennart Rosam     2012-05-16 09:48:34 +0200  72)         mNeedsUpdating = false;
c6b553f6 src/eu/alefzero/owncloud/datamodel/OCFile.java (David A. Velasco  2012-07-17 12:59:15 +0200  73)         if (path == null || path.length() <= 0 || !path.startsWith(PATH_SEPARATOR)) {
c6b553f6 src/eu/alefzero/owncloud/datamodel/OCFile.java (David A. Velasco  2012-07-17 12:59:15 +0200  74)             throw new IllegalArgumentException("Trying to create a OCFile with a non valid remote path: " + path);
c6b553f6 src/eu/alefzero/owncloud/datamodel/OCFile.java (David A. Velasco  2012-07-17 12:59:15 +0200  75)         }
c6b553f6 src/eu/alefzero/owncloud/datamodel/OCFile.java (David A. Velasco  2012-07-17 12:59:15 +0200  76)         mRemotePath = path;
435b31ba src/eu/alefzero/owncloud/datamodel/OCFile.java (Lennart Rosam     2012-05-16 09:48:34 +0200  77)     }
435b31ba src/eu/alefzero/owncloud/datamodel/OCFile.java (Lennart Rosam     2012-05-16 09:48:34 +0200  78) 
435b31ba src/eu/alefzero/owncloud/datamodel/OCFile.java (Lennart Rosam     2012-05-16 09:48:34 +0200  79)     /**
435b31ba src/eu/alefzero/owncloud/datamodel/OCFile.java (Lennart Rosam     2012-05-16 09:48:34 +0200  80)      * Reconstruct from parcel
435b31ba src/eu/alefzero/owncloud/datamodel/OCFile.java (Lennart Rosam     2012-05-16 09:48:34 +0200  81)      * 
ad8dbb31 src/eu/alefzero/owncloud/datamodel/OCFile.java (Lennart Rosam     2012-05-16 11:07:39 +0200  82)      * @param source The source parcel
435b31ba src/eu/alefzero/owncloud/datamodel/OCFile.java (Lennart Rosam     2012-05-16 09:48:34 +0200  83)      */
435b31ba src/eu/alefzero/owncloud/datamodel/OCFile.java (Lennart Rosam     2012-05-16 09:48:34 +0200  84)     private OCFile(Parcel source) {
435b31ba src/eu/alefzero/owncloud/datamodel/OCFile.java (Lennart Rosam     2012-05-16 09:48:34 +0200  85)         mId = source.readLong();
435b31ba src/eu/alefzero/owncloud/datamodel/OCFile.java (Lennart Rosam     2012-05-16 09:48:34 +0200  86)         mParentId = source.readLong();
435b31ba src/eu/alefzero/owncloud/datamodel/OCFile.java (Lennart Rosam     2012-05-16 09:48:34 +0200  87)         mLength = source.readLong();
435b31ba src/eu/alefzero/owncloud/datamodel/OCFile.java (Lennart Rosam     2012-05-16 09:48:34 +0200  88)         mCreationTimestamp = source.readLong();
435b31ba src/eu/alefzero/owncloud/datamodel/OCFile.java (Lennart Rosam     2012-05-16 09:48:34 +0200  89)         mModifiedTimestamp = source.readLong();
abd5f515 src/com/owncloud/android/datamodel/OCFile.java (David A. Velasco  2012-12-05 16:14:01 +0100  90)         mModifiedTimestampAtLastSyncForData = source.readLong();
435b31ba src/eu/alefzero/owncloud/datamodel/OCFile.java (Lennart Rosam     2012-05-16 09:48:34 +0200  91)         mRemotePath = source.readString();
435b31ba src/eu/alefzero/owncloud/datamodel/OCFile.java (Lennart Rosam     2012-05-16 09:48:34 +0200  92)         mLocalPath = source.readString();
435b31ba src/eu/alefzero/owncloud/datamodel/OCFile.java (Lennart Rosam     2012-05-16 09:48:34 +0200  93)         mMimeType = source.readString();
435b31ba src/eu/alefzero/owncloud/datamodel/OCFile.java (Lennart Rosam     2012-05-16 09:48:34 +0200  94)         mNeedsUpdating = source.readInt() == 0;
3113c459 src/eu/alefzero/owncloud/datamodel/OCFile.java (Bartek Przybylski 2012-07-08 16:57:48 +0200  95)         mKeepInSync = source.readInt() == 1;
ff2271a8 src/com/owncloud/android/datamodel/OCFile.java (David A. Velasco  2012-11-15 17:18:04 +0100  96)         mLastSyncDateForProperties = source.readLong();
ff2271a8 src/com/owncloud/android/datamodel/OCFile.java (David A. Velasco  2012-11-15 17:18:04 +0100  97)         mLastSyncDateForData = source.readLong();
435b31ba src/eu/alefzero/owncloud/datamodel/OCFile.java (Lennart Rosam     2012-05-16 09:48:34 +0200  98)     }
435b31ba src/eu/alefzero/owncloud/datamodel/OCFile.java (Lennart Rosam     2012-05-16 09:48:34 +0200  99) 
3113c459 src/eu/alefzero/owncloud/datamodel/OCFile.java (Bartek Przybylski 2012-07-08 16:57:48 +0200 100)     @Override
3113c459 src/eu/alefzero/owncloud/datamodel/OCFile.java (Bartek Przybylski 2012-07-08 16:57:48 +0200 101)     public void writeToParcel(Parcel dest, int flags) {
3113c459 src/eu/alefzero/owncloud/datamodel/OCFile.java (Bartek Przybylski 2012-07-08 16:57:48 +0200 102)         dest.writeLong(mId);
3113c459 src/eu/alefzero/owncloud/datamodel/OCFile.java (Bartek Przybylski 2012-07-08 16:57:48 +0200 103)         dest.writeLong(mParentId);
3113c459 src/eu/alefzero/owncloud/datamodel/OCFile.java (Bartek Przybylski 2012-07-08 16:57:48 +0200 104)         dest.writeLong(mLength);
3113c459 src/eu/alefzero/owncloud/datamodel/OCFile.java (Bartek Przybylski 2012-07-08 16:57:48 +0200 105)         dest.writeLong(mCreationTimestamp);
3113c459 src/eu/alefzero/owncloud/datamodel/OCFile.java (Bartek Przybylski 2012-07-08 16:57:48 +0200 106)         dest.writeLong(mModifiedTimestamp);
abd5f515 src/com/owncloud/android/datamodel/OCFile.java (David A. Velasco  2012-12-05 16:14:01 +0100 107)         dest.writeLong(mModifiedTimestampAtLastSyncForData);
3113c459 src/eu/alefzero/owncloud/datamodel/OCFile.java (Bartek Przybylski 2012-07-08 16:57:48 +0200 108)         dest.writeString(mRemotePath);
3113c459 src/eu/alefzero/owncloud/datamodel/OCFile.java (Bartek Przybylski 2012-07-08 16:57:48 +0200 109)         dest.writeString(mLocalPath);
3113c459 src/eu/alefzero/owncloud/datamodel/OCFile.java (Bartek Przybylski 2012-07-08 16:57:48 +0200 110)         dest.writeString(mMimeType);
3113c459 src/eu/alefzero/owncloud/datamodel/OCFile.java (Bartek Przybylski 2012-07-08 16:57:48 +0200 111)         dest.writeInt(mNeedsUpdating ? 1 : 0);
3113c459 src/eu/alefzero/owncloud/datamodel/OCFile.java (Bartek Przybylski 2012-07-08 16:57:48 +0200 112)         dest.writeInt(mKeepInSync ? 1 : 0);
ff2271a8 src/com/owncloud/android/datamodel/OCFile.java (David A. Velasco  2012-11-15 17:18:04 +0100 113)         dest.writeLong(mLastSyncDateForProperties);
ff2271a8 src/com/owncloud/android/datamodel/OCFile.java (David A. Velasco  2012-11-15 17:18:04 +0100 114)         dest.writeLong(mLastSyncDateForData);
3113c459 src/eu/alefzero/owncloud/datamodel/OCFile.java (Bartek Przybylski 2012-07-08 16:57:48 +0200 115)     }
3113c459 src/eu/alefzero/owncloud/datamodel/OCFile.java (Bartek Przybylski 2012-07-08 16:57:48 +0200 116)     
435b31ba src/eu/alefzero/owncloud/datamodel/OCFile.java (Lennart Rosam     2012-05-16 09:48:34 +0200 117)     /**
435b31ba src/eu/alefzero/owncloud/datamodel/OCFile.java (Lennart Rosam     2012-05-16 09:48:34 +0200 118)      * Gets the ID of the file
435b31ba src/eu/alefzero/owncloud/datamodel/OCFile.java (Lennart Rosam     2012-05-16 09:48:34 +0200 119)      * 
435b31ba src/eu/alefzero/owncloud/datamodel/OCFile.java (Lennart Rosam     2012-05-16 09:48:34 +0200 120)      * @return the file ID
435b31ba src/eu/alefzero/owncloud/datamodel/OCFile.java (Lennart Rosam     2012-05-16 09:48:34 +0200 121)      */
435b31ba src/eu/alefzero/owncloud/datamodel/OCFile.java (Lennart Rosam     2012-05-16 09:48:34 +0200 122)     public long getFileId() {
435b31ba src/eu/alefzero/owncloud/datamodel/OCFile.java (Lennart Rosam     2012-05-16 09:48:34 +0200 123)         return mId;
435b31ba src/eu/alefzero/owncloud/datamodel/OCFile.java (Lennart Rosam     2012-05-16 09:48:34 +0200 124)     }
435b31ba src/eu/alefzero/owncloud/datamodel/OCFile.java (Lennart Rosam     2012-05-16 09:48:34 +0200 125) 
435b31ba src/eu/alefzero/owncloud/datamodel/OCFile.java (Lennart Rosam     2012-05-16 09:48:34 +0200 126)     /**
5ae7704e src/eu/alefzero/owncloud/datamodel/OCFile.java (Lennart Rosam     2012-05-23 22:39:37 +0200 127)      * Returns the remote path of the file on ownCloud
435b31ba src/eu/alefzero/owncloud/datamodel/OCFile.java (Lennart Rosam     2012-05-16 09:48:34 +0200 128)      * 
5ae7704e src/eu/alefzero/owncloud/datamodel/OCFile.java (Lennart Rosam     2012-05-23 22:39:37 +0200 129)      * @return The remote path to the file
435b31ba src/eu/alefzero/owncloud/datamodel/OCFile.java (Lennart Rosam     2012-05-16 09:48:34 +0200 130)      */
5ae7704e src/eu/alefzero/owncloud/datamodel/OCFile.java (Lennart Rosam     2012-05-23 22:39:37 +0200 131)     public String getRemotePath() {
435b31ba src/eu/alefzero/owncloud/datamodel/OCFile.java (Lennart Rosam     2012-05-16 09:48:34 +0200 132)         return mRemotePath;
435b31ba src/eu/alefzero/owncloud/datamodel/OCFile.java (Lennart Rosam     2012-05-16 09:48:34 +0200 133)     }
435b31ba src/eu/alefzero/owncloud/datamodel/OCFile.java (Lennart Rosam     2012-05-16 09:48:34 +0200 134) 
435b31ba src/eu/alefzero/owncloud/datamodel/OCFile.java (Lennart Rosam     2012-05-16 09:48:34 +0200 135)     /**
435b31ba src/eu/alefzero/owncloud/datamodel/OCFile.java (Lennart Rosam     2012-05-16 09:48:34 +0200 136)      * Can be used to check, whether or not this file exists in the database
435b31ba src/eu/alefzero/owncloud/datamodel/OCFile.java (Lennart Rosam     2012-05-16 09:48:34 +0200 137)      * already
435b31ba src/eu/alefzero/owncloud/datamodel/OCFile.java (Lennart Rosam     2012-05-16 09:48:34 +0200 138)      * 
435b31ba src/eu/alefzero/owncloud/datamodel/OCFile.java (Lennart Rosam     2012-05-16 09:48:34 +0200 139)      * @return true, if the file exists in the database
435b31ba src/eu/alefzero/owncloud/datamodel/OCFile.java (Lennart Rosam     2012-05-16 09:48:34 +0200 140)      */
435b31ba src/eu/alefzero/owncloud/datamodel/OCFile.java (Lennart Rosam     2012-05-16 09:48:34 +0200 141)     public boolean fileExists() {
435b31ba src/eu/alefzero/owncloud/datamodel/OCFile.java (Lennart Rosam     2012-05-16 09:48:34 +0200 142)         return mId != -1;
435b31ba src/eu/alefzero/owncloud/datamodel/OCFile.java (Lennart Rosam     2012-05-16 09:48:34 +0200 143)     }
435b31ba src/eu/alefzero/owncloud/datamodel/OCFile.java (Lennart Rosam     2012-05-16 09:48:34 +0200 144) 
435b31ba src/eu/alefzero/owncloud/datamodel/OCFile.java (Lennart Rosam     2012-05-16 09:48:34 +0200 145)     /**
435b31ba src/eu/alefzero/owncloud/datamodel/OCFile.java (Lennart Rosam     2012-05-16 09:48:34 +0200 146)      * Use this to find out if this file is a Directory
435b31ba src/eu/alefzero/owncloud/datamodel/OCFile.java (Lennart Rosam     2012-05-16 09:48:34 +0200 147)      * 
435b31ba src/eu/alefzero/owncloud/datamodel/OCFile.java (Lennart Rosam     2012-05-16 09:48:34 +0200 148)      * @return true if it is a directory
435b31ba src/eu/alefzero/owncloud/datamodel/OCFile.java (Lennart Rosam     2012-05-16 09:48:34 +0200 149)      */
435b31ba src/eu/alefzero/owncloud/datamodel/OCFile.java (Lennart Rosam     2012-05-16 09:48:34 +0200 150)     public boolean isDirectory() {
435b31ba src/eu/alefzero/owncloud/datamodel/OCFile.java (Lennart Rosam     2012-05-16 09:48:34 +0200 151)         return mMimeType != null && mMimeType.equals("DIR");
435b31ba src/eu/alefzero/owncloud/datamodel/OCFile.java (Lennart Rosam     2012-05-16 09:48:34 +0200 152)     }
435b31ba src/eu/alefzero/owncloud/datamodel/OCFile.java (Lennart Rosam     2012-05-16 09:48:34 +0200 153) 
435b31ba src/eu/alefzero/owncloud/datamodel/OCFile.java (Lennart Rosam     2012-05-16 09:48:34 +0200 154)     /**
435b31ba src/eu/alefzero/owncloud/datamodel/OCFile.java (Lennart Rosam     2012-05-16 09:48:34 +0200 155)      * Use this to check if this file is available locally
435b31ba src/eu/alefzero/owncloud/datamodel/OCFile.java (Lennart Rosam     2012-05-16 09:48:34 +0200 156)      * 
435b31ba src/eu/alefzero/owncloud/datamodel/OCFile.java (Lennart Rosam     2012-05-16 09:48:34 +0200 157)      * @return true if it is
435b31ba src/eu/alefzero/owncloud/datamodel/OCFile.java (Lennart Rosam     2012-05-16 09:48:34 +0200 158)      */
2a04c6ae src/eu/alefzero/owncloud/datamodel/OCFile.java (David A. Velasco  2012-07-20 10:13:50 +0200 159)     public boolean isDown() {
2a04c6ae src/eu/alefzero/owncloud/datamodel/OCFile.java (David A. Velasco  2012-07-20 10:13:50 +0200 160)         if (mLocalPath != null && mLocalPath.length() > 0) {
2a04c6ae src/eu/alefzero/owncloud/datamodel/OCFile.java (David A. Velasco  2012-07-20 10:13:50 +0200 161)             File file = new File(mLocalPath);
6f189bff src/eu/alefzero/owncloud/datamodel/OCFile.java (David A. Velasco  2012-07-23 13:55:58 +0200 162)             return (file.exists());
2a04c6ae src/eu/alefzero/owncloud/datamodel/OCFile.java (David A. Velasco  2012-07-20 10:13:50 +0200 163)         }
2a04c6ae src/eu/alefzero/owncloud/datamodel/OCFile.java (David A. Velasco  2012-07-20 10:13:50 +0200 164)         return false;
2a04c6ae src/eu/alefzero/owncloud/datamodel/OCFile.java (David A. Velasco  2012-07-20 10:13:50 +0200 165)     }
2a04c6ae src/eu/alefzero/owncloud/datamodel/OCFile.java (David A. Velasco  2012-07-20 10:13:50 +0200 166)     
2a04c6ae src/eu/alefzero/owncloud/datamodel/OCFile.java (David A. Velasco  2012-07-20 10:13:50 +0200 167)     /**
435b31ba src/eu/alefzero/owncloud/datamodel/OCFile.java (Lennart Rosam     2012-05-16 09:48:34 +0200 168)      * The path, where the file is stored locally
435b31ba src/eu/alefzero/owncloud/datamodel/OCFile.java (Lennart Rosam     2012-05-16 09:48:34 +0200 169)      * 
435b31ba src/eu/alefzero/owncloud/datamodel/OCFile.java (Lennart Rosam     2012-05-16 09:48:34 +0200 170)      * @return The local path to the file
435b31ba src/eu/alefzero/owncloud/datamodel/OCFile.java (Lennart Rosam     2012-05-16 09:48:34 +0200 171)      */
435b31ba src/eu/alefzero/owncloud/datamodel/OCFile.java (Lennart Rosam     2012-05-16 09:48:34 +0200 172)     public String getStoragePath() {
435b31ba src/eu/alefzero/owncloud/datamodel/OCFile.java (Lennart Rosam     2012-05-16 09:48:34 +0200 173)         return mLocalPath;
435b31ba src/eu/alefzero/owncloud/datamodel/OCFile.java (Lennart Rosam     2012-05-16 09:48:34 +0200 174)     }
435b31ba src/eu/alefzero/owncloud/datamodel/OCFile.java (Lennart Rosam     2012-05-16 09:48:34 +0200 175) 
435b31ba src/eu/alefzero/owncloud/datamodel/OCFile.java (Lennart Rosam     2012-05-16 09:48:34 +0200 176)     /**
435b31ba src/eu/alefzero/owncloud/datamodel/OCFile.java (Lennart Rosam     2012-05-16 09:48:34 +0200 177)      * Can be used to set the path where the file is stored
435b31ba src/eu/alefzero/owncloud/datamodel/OCFile.java (Lennart Rosam     2012-05-16 09:48:34 +0200 178)      * 
ad8dbb31 src/eu/alefzero/owncloud/datamodel/OCFile.java (Lennart Rosam     2012-05-16 11:07:39 +0200 179)      * @param storage_path to set
435b31ba src/eu/alefzero/owncloud/datamodel/OCFile.java (Lennart Rosam     2012-05-16 09:48:34 +0200 180)      */
435b31ba src/eu/alefzero/owncloud/datamodel/OCFile.java (Lennart Rosam     2012-05-16 09:48:34 +0200 181)     public void setStoragePath(String storage_path) {
435b31ba src/eu/alefzero/owncloud/datamodel/OCFile.java (Lennart Rosam     2012-05-16 09:48:34 +0200 182)         mLocalPath = storage_path;
435b31ba src/eu/alefzero/owncloud/datamodel/OCFile.java (Lennart Rosam     2012-05-16 09:48:34 +0200 183)     }
435b31ba src/eu/alefzero/owncloud/datamodel/OCFile.java (Lennart Rosam     2012-05-16 09:48:34 +0200 184) 
435b31ba src/eu/alefzero/owncloud/datamodel/OCFile.java (Lennart Rosam     2012-05-16 09:48:34 +0200 185)     /**
435b31ba src/eu/alefzero/owncloud/datamodel/OCFile.java (Lennart Rosam     2012-05-16 09:48:34 +0200 186)      * Get a UNIX timestamp of the file creation time
435b31ba src/eu/alefzero/owncloud/datamodel/OCFile.java (Lennart Rosam     2012-05-16 09:48:34 +0200 187)      * 
435b31ba src/eu/alefzero/owncloud/datamodel/OCFile.java (Lennart Rosam     2012-05-16 09:48:34 +0200 188)      * @return A UNIX timestamp of the time that file was created
435b31ba src/eu/alefzero/owncloud/datamodel/OCFile.java (Lennart Rosam     2012-05-16 09:48:34 +0200 189)      */
435b31ba src/eu/alefzero/owncloud/datamodel/OCFile.java (Lennart Rosam     2012-05-16 09:48:34 +0200 190)     public long getCreationTimestamp() {
435b31ba src/eu/alefzero/owncloud/datamodel/OCFile.java (Lennart Rosam     2012-05-16 09:48:34 +0200 191)         return mCreationTimestamp;
435b31ba src/eu/alefzero/owncloud/datamodel/OCFile.java (Lennart Rosam     2012-05-16 09:48:34 +0200 192)     }
435b31ba src/eu/alefzero/owncloud/datamodel/OCFile.java (Lennart Rosam     2012-05-16 09:48:34 +0200 193) 
435b31ba src/eu/alefzero/owncloud/datamodel/OCFile.java (Lennart Rosam     2012-05-16 09:48:34 +0200 194)     /**
435b31ba src/eu/alefzero/owncloud/datamodel/OCFile.java (Lennart Rosam     2012-05-16 09:48:34 +0200 195)      * Set a UNIX timestamp of the time the file was created
435b31ba src/eu/alefzero/owncloud/datamodel/OCFile.java (Lennart Rosam     2012-05-16 09:48:34 +0200 196)      * 
ad8dbb31 src/eu/alefzero/owncloud/datamodel/OCFile.java (Lennart Rosam     2012-05-16 11:07:39 +0200 197)      * @param creation_timestamp to set
435b31ba src/eu/alefzero/owncloud/datamodel/OCFile.java (Lennart Rosam     2012-05-16 09:48:34 +0200 198)      */
435b31ba src/eu/alefzero/owncloud/datamodel/OCFile.java (Lennart Rosam     2012-05-16 09:48:34 +0200 199)     public void setCreationTimestamp(long creation_timestamp) {
435b31ba src/eu/alefzero/owncloud/datamodel/OCFile.java (Lennart Rosam     2012-05-16 09:48:34 +0200 200)         mCreationTimestamp = creation_timestamp;
435b31ba src/eu/alefzero/owncloud/datamodel/OCFile.java (Lennart Rosam     2012-05-16 09:48:34 +0200 201)     }
435b31ba src/eu/alefzero/owncloud/datamodel/OCFile.java (Lennart Rosam     2012-05-16 09:48:34 +0200 202) 
435b31ba src/eu/alefzero/owncloud/datamodel/OCFile.java (Lennart Rosam     2012-05-16 09:48:34 +0200 203)     /**
abd5f515 src/com/owncloud/android/datamodel/OCFile.java (David A. Velasco  2012-12-05 16:14:01 +0100 204)      * Get a UNIX timestamp of the file modification time.
abd5f515 src/com/owncloud/android/datamodel/OCFile.java (David A. Velasco  2012-12-05 16:14:01 +0100 205)      *
abd5f515 src/com/owncloud/android/datamodel/OCFile.java (David A. Velasco  2012-12-05 16:14:01 +0100 206)      * @return  A UNIX timestamp of the modification time, corresponding to the value returned by the server
abd5f515 src/com/owncloud/android/datamodel/OCFile.java (David A. Velasco  2012-12-05 16:14:01 +0100 207)      *          in the last synchronization of the properties of this file. 
435b31ba src/eu/alefzero/owncloud/datamodel/OCFile.java (Lennart Rosam     2012-05-16 09:48:34 +0200 208)      */
435b31ba src/eu/alefzero/owncloud/datamodel/OCFile.java (Lennart Rosam     2012-05-16 09:48:34 +0200 209)     public long getModificationTimestamp() {
435b31ba src/eu/alefzero/owncloud/datamodel/OCFile.java (Lennart Rosam     2012-05-16 09:48:34 +0200 210)         return mModifiedTimestamp;
435b31ba src/eu/alefzero/owncloud/datamodel/OCFile.java (Lennart Rosam     2012-05-16 09:48:34 +0200 211)     }
435b31ba src/eu/alefzero/owncloud/datamodel/OCFile.java (Lennart Rosam     2012-05-16 09:48:34 +0200 212) 
435b31ba src/eu/alefzero/owncloud/datamodel/OCFile.java (Lennart Rosam     2012-05-16 09:48:34 +0200 213)     /**
435b31ba src/eu/alefzero/owncloud/datamodel/OCFile.java (Lennart Rosam     2012-05-16 09:48:34 +0200 214)      * Set a UNIX timestamp of the time the time the file was modified.
435b31ba src/eu/alefzero/owncloud/datamodel/OCFile.java (Lennart Rosam     2012-05-16 09:48:34 +0200 215)      * 
abd5f515 src/com/owncloud/android/datamodel/OCFile.java (David A. Velasco  2012-12-05 16:14:01 +0100 216)      * To update with the value returned by the server in every synchronization of the properties 
abd5f515 src/com/owncloud/android/datamodel/OCFile.java (David A. Velasco  2012-12-05 16:14:01 +0100 217)      * of this file.
abd5f515 src/com/owncloud/android/datamodel/OCFile.java (David A. Velasco  2012-12-05 16:14:01 +0100 218)      * 
ad8dbb31 src/eu/alefzero/owncloud/datamodel/OCFile.java (Lennart Rosam     2012-05-16 11:07:39 +0200 219)      * @param modification_timestamp to set
435b31ba src/eu/alefzero/owncloud/datamodel/OCFile.java (Lennart Rosam     2012-05-16 09:48:34 +0200 220)      */
435b31ba src/eu/alefzero/owncloud/datamodel/OCFile.java (Lennart Rosam     2012-05-16 09:48:34 +0200 221)     public void setModificationTimestamp(long modification_timestamp) {
435b31ba src/eu/alefzero/owncloud/datamodel/OCFile.java (Lennart Rosam     2012-05-16 09:48:34 +0200 222)         mModifiedTimestamp = modification_timestamp;
435b31ba src/eu/alefzero/owncloud/datamodel/OCFile.java (Lennart Rosam     2012-05-16 09:48:34 +0200 223)     }
435b31ba src/eu/alefzero/owncloud/datamodel/OCFile.java (Lennart Rosam     2012-05-16 09:48:34 +0200 224) 
abd5f515 src/com/owncloud/android/datamodel/OCFile.java (David A. Velasco  2012-12-05 16:14:01 +0100 225)     
abd5f515 src/com/owncloud/android/datamodel/OCFile.java (David A. Velasco  2012-12-05 16:14:01 +0100 226)     /**
abd5f515 src/com/owncloud/android/datamodel/OCFile.java (David A. Velasco  2012-12-05 16:14:01 +0100 227)      * Get a UNIX timestamp of the file modification time.
abd5f515 src/com/owncloud/android/datamodel/OCFile.java (David A. Velasco  2012-12-05 16:14:01 +0100 228)      *
abd5f515 src/com/owncloud/android/datamodel/OCFile.java (David A. Velasco  2012-12-05 16:14:01 +0100 229)      * @return  A UNIX timestamp of the modification time, corresponding to the value returned by the server
abd5f515 src/com/owncloud/android/datamodel/OCFile.java (David A. Velasco  2012-12-05 16:14:01 +0100 230)      *          in the last synchronization of THE CONTENTS of this file. 
abd5f515 src/com/owncloud/android/datamodel/OCFile.java (David A. Velasco  2012-12-05 16:14:01 +0100 231)      */
abd5f515 src/com/owncloud/android/datamodel/OCFile.java (David A. Velasco  2012-12-05 16:14:01 +0100 232)     public long getModificationTimestampAtLastSyncForData() {
abd5f515 src/com/owncloud/android/datamodel/OCFile.java (David A. Velasco  2012-12-05 16:14:01 +0100 233)         return mModifiedTimestampAtLastSyncForData;
abd5f515 src/com/owncloud/android/datamodel/OCFile.java (David A. Velasco  2012-12-05 16:14:01 +0100 234)     }
abd5f515 src/com/owncloud/android/datamodel/OCFile.java (David A. Velasco  2012-12-05 16:14:01 +0100 235) 
abd5f515 src/com/owncloud/android/datamodel/OCFile.java (David A. Velasco  2012-12-05 16:14:01 +0100 236)     /**
abd5f515 src/com/owncloud/android/datamodel/OCFile.java (David A. Velasco  2012-12-05 16:14:01 +0100 237)      * Set a UNIX timestamp of the time the time the file was modified.
abd5f515 src/com/owncloud/android/datamodel/OCFile.java (David A. Velasco  2012-12-05 16:14:01 +0100 238)      * 
abd5f515 src/com/owncloud/android/datamodel/OCFile.java (David A. Velasco  2012-12-05 16:14:01 +0100 239)      * To update with the value returned by the server in every synchronization of THE CONTENTS 
abd5f515 src/com/owncloud/android/datamodel/OCFile.java (David A. Velasco  2012-12-05 16:14:01 +0100 240)      * of this file.
abd5f515 src/com/owncloud/android/datamodel/OCFile.java (David A. Velasco  2012-12-05 16:14:01 +0100 241)      * 
abd5f515 src/com/owncloud/android/datamodel/OCFile.java (David A. Velasco  2012-12-05 16:14:01 +0100 242)      * @param modification_timestamp to set
abd5f515 src/com/owncloud/android/datamodel/OCFile.java (David A. Velasco  2012-12-05 16:14:01 +0100 243)      */
abd5f515 src/com/owncloud/android/datamodel/OCFile.java (David A. Velasco  2012-12-05 16:14:01 +0100 244)     public void setModificationTimestampAtLastSyncForData(long modificationTimestamp) {
abd5f515 src/com/owncloud/android/datamodel/OCFile.java (David A. Velasco  2012-12-05 16:14:01 +0100 245)         mModifiedTimestampAtLastSyncForData = modificationTimestamp;
abd5f515 src/com/owncloud/android/datamodel/OCFile.java (David A. Velasco  2012-12-05 16:14:01 +0100 246)     }
abd5f515 src/com/owncloud/android/datamodel/OCFile.java (David A. Velasco  2012-12-05 16:14:01 +0100 247) 
abd5f515 src/com/owncloud/android/datamodel/OCFile.java (David A. Velasco  2012-12-05 16:14:01 +0100 248)     
abd5f515 src/com/owncloud/android/datamodel/OCFile.java (David A. Velasco  2012-12-05 16:14:01 +0100 249)     
435b31ba src/eu/alefzero/owncloud/datamodel/OCFile.java (Lennart Rosam     2012-05-16 09:48:34 +0200 250)     /**
435b31ba src/eu/alefzero/owncloud/datamodel/OCFile.java (Lennart Rosam     2012-05-16 09:48:34 +0200 251)      * Returns the filename and "/" for the root directory
435b31ba src/eu/alefzero/owncloud/datamodel/OCFile.java (Lennart Rosam     2012-05-16 09:48:34 +0200 252)      * 
435b31ba src/eu/alefzero/owncloud/datamodel/OCFile.java (Lennart Rosam     2012-05-16 09:48:34 +0200 253)      * @return The name of the file
435b31ba src/eu/alefzero/owncloud/datamodel/OCFile.java (Lennart Rosam     2012-05-16 09:48:34 +0200 254)      */
435b31ba src/eu/alefzero/owncloud/datamodel/OCFile.java (Lennart Rosam     2012-05-16 09:48:34 +0200 255)     public String getFileName() {
c6b553f6 src/eu/alefzero/owncloud/datamodel/OCFile.java (David A. Velasco  2012-07-17 12:59:15 +0200 256)         File f = new File(getRemotePath());
1baa27a4 src/com/owncloud/android/datamodel/OCFile.java (David A. Velasco  2012-11-06 13:35:08 +0100 257)         return f.getName().length() == 0 ? PATH_SEPARATOR : f.getName();
1baa27a4 src/com/owncloud/android/datamodel/OCFile.java (David A. Velasco  2012-11-06 13:35:08 +0100 258)     }
1baa27a4 src/com/owncloud/android/datamodel/OCFile.java (David A. Velasco  2012-11-06 13:35:08 +0100 259)     
1baa27a4 src/com/owncloud/android/datamodel/OCFile.java (David A. Velasco  2012-11-06 13:35:08 +0100 260)     /**
1baa27a4 src/com/owncloud/android/datamodel/OCFile.java (David A. Velasco  2012-11-06 13:35:08 +0100 261)      * Sets the name of the file
1baa27a4 src/com/owncloud/android/datamodel/OCFile.java (David A. Velasco  2012-11-06 13:35:08 +0100 262)      * 
1baa27a4 src/com/owncloud/android/datamodel/OCFile.java (David A. Velasco  2012-11-06 13:35:08 +0100 263)      * Does nothing if the new name is null, empty or includes "/" ; or if the file is the root directory 
1baa27a4 src/com/owncloud/android/datamodel/OCFile.java (David A. Velasco  2012-11-06 13:35:08 +0100 264)      */
1baa27a4 src/com/owncloud/android/datamodel/OCFile.java (David A. Velasco  2012-11-06 13:35:08 +0100 265)     public void setFileName(String name) {
8e36e7cc src/com/owncloud/android/datamodel/OCFile.java (zerginator        2013-03-12 07:56:27 +0100 266)         Log_OC.d(TAG, "OCFile name changin from " + mRemotePath);
1baa27a4 src/com/owncloud/android/datamodel/OCFile.java (David A. Velasco  2012-11-06 13:35:08 +0100 267)         if (name != null && name.length() > 0 && !name.contains(PATH_SEPARATOR) && !mRemotePath.equals(PATH_SEPARATOR)) {
85e9a40d src/com/owncloud/android/datamodel/OCFile.java (David A. Velasco  2012-11-08 17:21:45 +0100 268)             String parent = (new File(getRemotePath())).getParent();
85e9a40d src/com/owncloud/android/datamodel/OCFile.java (David A. Velasco  2012-11-08 17:21:45 +0100 269)             parent = (parent.endsWith(PATH_SEPARATOR)) ? parent : parent + PATH_SEPARATOR;
85e9a40d src/com/owncloud/android/datamodel/OCFile.java (David A. Velasco  2012-11-08 17:21:45 +0100 270)             mRemotePath =  parent + name;
1baa27a4 src/com/owncloud/android/datamodel/OCFile.java (David A. Velasco  2012-11-06 13:35:08 +0100 271)             if (isDirectory()) {
1baa27a4 src/com/owncloud/android/datamodel/OCFile.java (David A. Velasco  2012-11-06 13:35:08 +0100 272)                 mRemotePath += PATH_SEPARATOR;
1baa27a4 src/com/owncloud/android/datamodel/OCFile.java (David A. Velasco  2012-11-06 13:35:08 +0100 273)             }
8e36e7cc src/com/owncloud/android/datamodel/OCFile.java (zerginator        2013-03-12 07:56:27 +0100 274)             Log_OC.d(TAG, "OCFile name changed to " + mRemotePath);
1baa27a4 src/com/owncloud/android/datamodel/OCFile.java (David A. Velasco  2012-11-06 13:35:08 +0100 275)         }
435b31ba src/eu/alefzero/owncloud/datamodel/OCFile.java (Lennart Rosam     2012-05-16 09:48:34 +0200 276)     }
435b31ba src/eu/alefzero/owncloud/datamodel/OCFile.java (Lennart Rosam     2012-05-16 09:48:34 +0200 277) 
435b31ba src/eu/alefzero/owncloud/datamodel/OCFile.java (Lennart Rosam     2012-05-16 09:48:34 +0200 278)     /**
435b31ba src/eu/alefzero/owncloud/datamodel/OCFile.java (Lennart Rosam     2012-05-16 09:48:34 +0200 279)      * Can be used to get the Mimetype
435b31ba src/eu/alefzero/owncloud/datamodel/OCFile.java (Lennart Rosam     2012-05-16 09:48:34 +0200 280)      * 
435b31ba src/eu/alefzero/owncloud/datamodel/OCFile.java (Lennart Rosam     2012-05-16 09:48:34 +0200 281)      * @return the Mimetype as a String
435b31ba src/eu/alefzero/owncloud/datamodel/OCFile.java (Lennart Rosam     2012-05-16 09:48:34 +0200 282)      */
435b31ba src/eu/alefzero/owncloud/datamodel/OCFile.java (Lennart Rosam     2012-05-16 09:48:34 +0200 283)     public String getMimetype() {
435b31ba src/eu/alefzero/owncloud/datamodel/OCFile.java (Lennart Rosam     2012-05-16 09:48:34 +0200 284)         return mMimeType;
435b31ba src/eu/alefzero/owncloud/datamodel/OCFile.java (Lennart Rosam     2012-05-16 09:48:34 +0200 285)     }
435b31ba src/eu/alefzero/owncloud/datamodel/OCFile.java (Lennart Rosam     2012-05-16 09:48:34 +0200 286) 
435b31ba src/eu/alefzero/owncloud/datamodel/OCFile.java (Lennart Rosam     2012-05-16 09:48:34 +0200 287)     /**
435b31ba src/eu/alefzero/owncloud/datamodel/OCFile.java (Lennart Rosam     2012-05-16 09:48:34 +0200 288)      * Adds a file to this directory. If this file is not a directory, an
435b31ba src/eu/alefzero/owncloud/datamodel/OCFile.java (Lennart Rosam     2012-05-16 09:48:34 +0200 289)      * exception gets thrown.
435b31ba src/eu/alefzero/owncloud/datamodel/OCFile.java (Lennart Rosam     2012-05-16 09:48:34 +0200 290)      * 
ad8dbb31 src/eu/alefzero/owncloud/datamodel/OCFile.java (Lennart Rosam     2012-05-16 11:07:39 +0200 291)      * @param file to add
ad8dbb31 src/eu/alefzero/owncloud/datamodel/OCFile.java (Lennart Rosam     2012-05-16 11:07:39 +0200 292)      * @throws IllegalStateException if you try to add a something and this is
ad8dbb31 src/eu/alefzero/owncloud/datamodel/OCFile.java (Lennart Rosam     2012-05-16 11:07:39 +0200 293)      *             not a directory
435b31ba src/eu/alefzero/owncloud/datamodel/OCFile.java (Lennart Rosam     2012-05-16 09:48:34 +0200 294)      */
435b31ba src/eu/alefzero/owncloud/datamodel/OCFile.java (Lennart Rosam     2012-05-16 09:48:34 +0200 295)     public void addFile(OCFile file) throws IllegalStateException {
435b31ba src/eu/alefzero/owncloud/datamodel/OCFile.java (Lennart Rosam     2012-05-16 09:48:34 +0200 296)         if (isDirectory()) {
435b31ba src/eu/alefzero/owncloud/datamodel/OCFile.java (Lennart Rosam     2012-05-16 09:48:34 +0200 297)             file.mParentId = mId;
435b31ba src/eu/alefzero/owncloud/datamodel/OCFile.java (Lennart Rosam     2012-05-16 09:48:34 +0200 298)             mNeedsUpdating = true;
435b31ba src/eu/alefzero/owncloud/datamodel/OCFile.java (Lennart Rosam     2012-05-16 09:48:34 +0200 299)             return;
435b31ba src/eu/alefzero/owncloud/datamodel/OCFile.java (Lennart Rosam     2012-05-16 09:48:34 +0200 300)         }
435b31ba src/eu/alefzero/owncloud/datamodel/OCFile.java (Lennart Rosam     2012-05-16 09:48:34 +0200 301)         throw new IllegalStateException(
435b31ba src/eu/alefzero/owncloud/datamodel/OCFile.java (Lennart Rosam     2012-05-16 09:48:34 +0200 302)                 "This is not a directory where you can add stuff to!");
435b31ba src/eu/alefzero/owncloud/datamodel/OCFile.java (Lennart Rosam     2012-05-16 09:48:34 +0200 303)     }
435b31ba src/eu/alefzero/owncloud/datamodel/OCFile.java (Lennart Rosam     2012-05-16 09:48:34 +0200 304) 
435b31ba src/eu/alefzero/owncloud/datamodel/OCFile.java (Lennart Rosam     2012-05-16 09:48:34 +0200 305)     /**
435b31ba src/eu/alefzero/owncloud/datamodel/OCFile.java (Lennart Rosam     2012-05-16 09:48:34 +0200 306)      * Used internally. Reset all file properties
435b31ba src/eu/alefzero/owncloud/datamodel/OCFile.java (Lennart Rosam     2012-05-16 09:48:34 +0200 307)      */
435b31ba src/eu/alefzero/owncloud/datamodel/OCFile.java (Lennart Rosam     2012-05-16 09:48:34 +0200 308)     private void resetData() {
435b31ba src/eu/alefzero/owncloud/datamodel/OCFile.java (Lennart Rosam     2012-05-16 09:48:34 +0200 309)         mId = -1;
435b31ba src/eu/alefzero/owncloud/datamodel/OCFile.java (Lennart Rosam     2012-05-16 09:48:34 +0200 310)         mRemotePath = null;
435b31ba src/eu/alefzero/owncloud/datamodel/OCFile.java (Lennart Rosam     2012-05-16 09:48:34 +0200 311)         mParentId = 0;
435b31ba src/eu/alefzero/owncloud/datamodel/OCFile.java (Lennart Rosam     2012-05-16 09:48:34 +0200 312)         mLocalPath = null;
435b31ba src/eu/alefzero/owncloud/datamodel/OCFile.java (Lennart Rosam     2012-05-16 09:48:34 +0200 313)         mMimeType = null;
435b31ba src/eu/alefzero/owncloud/datamodel/OCFile.java (Lennart Rosam     2012-05-16 09:48:34 +0200 314)         mLength = 0;
435b31ba src/eu/alefzero/owncloud/datamodel/OCFile.java (Lennart Rosam     2012-05-16 09:48:34 +0200 315)         mCreationTimestamp = 0;
435b31ba src/eu/alefzero/owncloud/datamodel/OCFile.java (Lennart Rosam     2012-05-16 09:48:34 +0200 316)         mModifiedTimestamp = 0;
abd5f515 src/com/owncloud/android/datamodel/OCFile.java (David A. Velasco  2012-12-05 16:14:01 +0100 317)         mModifiedTimestampAtLastSyncForData = 0;
ff2271a8 src/com/owncloud/android/datamodel/OCFile.java (David A. Velasco  2012-11-15 17:18:04 +0100 318)         mLastSyncDateForProperties = 0;
ff2271a8 src/com/owncloud/android/datamodel/OCFile.java (David A. Velasco  2012-11-15 17:18:04 +0100 319)         mLastSyncDateForData = 0;
3113c459 src/eu/alefzero/owncloud/datamodel/OCFile.java (Bartek Przybylski 2012-07-08 16:57:48 +0200 320)         mKeepInSync = false;
3113c459 src/eu/alefzero/owncloud/datamodel/OCFile.java (Bartek Przybylski 2012-07-08 16:57:48 +0200 321)         mNeedsUpdating = false;
435b31ba src/eu/alefzero/owncloud/datamodel/OCFile.java (Lennart Rosam     2012-05-16 09:48:34 +0200 322)     }
435b31ba src/eu/alefzero/owncloud/datamodel/OCFile.java (Lennart Rosam     2012-05-16 09:48:34 +0200 323) 
435b31ba src/eu/alefzero/owncloud/datamodel/OCFile.java (Lennart Rosam     2012-05-16 09:48:34 +0200 324)     /**
435b31ba src/eu/alefzero/owncloud/datamodel/OCFile.java (Lennart Rosam     2012-05-16 09:48:34 +0200 325)      * Sets the ID of the file
435b31ba src/eu/alefzero/owncloud/datamodel/OCFile.java (Lennart Rosam     2012-05-16 09:48:34 +0200 326)      * 
ad8dbb31 src/eu/alefzero/owncloud/datamodel/OCFile.java (Lennart Rosam     2012-05-16 11:07:39 +0200 327)      * @param file_id to set
435b31ba src/eu/alefzero/owncloud/datamodel/OCFile.java (Lennart Rosam     2012-05-16 09:48:34 +0200 328)      */
435b31ba src/eu/alefzero/owncloud/datamodel/OCFile.java (Lennart Rosam     2012-05-16 09:48:34 +0200 329)     public void setFileId(long file_id) {
435b31ba src/eu/alefzero/owncloud/datamodel/OCFile.java (Lennart Rosam     2012-05-16 09:48:34 +0200 330)         mId = file_id;
435b31ba src/eu/alefzero/owncloud/datamodel/OCFile.java (Lennart Rosam     2012-05-16 09:48:34 +0200 331)     }
435b31ba src/eu/alefzero/owncloud/datamodel/OCFile.java (Lennart Rosam     2012-05-16 09:48:34 +0200 332) 
435b31ba src/eu/alefzero/owncloud/datamodel/OCFile.java (Lennart Rosam     2012-05-16 09:48:34 +0200 333)     /**
435b31ba src/eu/alefzero/owncloud/datamodel/OCFile.java (Lennart Rosam     2012-05-16 09:48:34 +0200 334)      * Sets the Mime-Type of the
435b31ba src/eu/alefzero/owncloud/datamodel/OCFile.java (Lennart Rosam     2012-05-16 09:48:34 +0200 335)      * 
ad8dbb31 src/eu/alefzero/owncloud/datamodel/OCFile.java (Lennart Rosam     2012-05-16 11:07:39 +0200 336)      * @param mimetype to set
435b31ba src/eu/alefzero/owncloud/datamodel/OCFile.java (Lennart Rosam     2012-05-16 09:48:34 +0200 337)      */
435b31ba src/eu/alefzero/owncloud/datamodel/OCFile.java (Lennart Rosam     2012-05-16 09:48:34 +0200 338)     public void setMimetype(String mimetype) {
435b31ba src/eu/alefzero/owncloud/datamodel/OCFile.java (Lennart Rosam     2012-05-16 09:48:34 +0200 339)         mMimeType = mimetype;
435b31ba src/eu/alefzero/owncloud/datamodel/OCFile.java (Lennart Rosam     2012-05-16 09:48:34 +0200 340)     }
435b31ba src/eu/alefzero/owncloud/datamodel/OCFile.java (Lennart Rosam     2012-05-16 09:48:34 +0200 341) 
435b31ba src/eu/alefzero/owncloud/datamodel/OCFile.java (Lennart Rosam     2012-05-16 09:48:34 +0200 342)     /**
435b31ba src/eu/alefzero/owncloud/datamodel/OCFile.java (Lennart Rosam     2012-05-16 09:48:34 +0200 343)      * Sets the ID of the parent folder
435b31ba src/eu/alefzero/owncloud/datamodel/OCFile.java (Lennart Rosam     2012-05-16 09:48:34 +0200 344)      * 
ad8dbb31 src/eu/alefzero/owncloud/datamodel/OCFile.java (Lennart Rosam     2012-05-16 11:07:39 +0200 345)      * @param parent_id to set
435b31ba src/eu/alefzero/owncloud/datamodel/OCFile.java (Lennart Rosam     2012-05-16 09:48:34 +0200 346)      */
435b31ba src/eu/alefzero/owncloud/datamodel/OCFile.java (Lennart Rosam     2012-05-16 09:48:34 +0200 347)     public void setParentId(long parent_id) {
435b31ba src/eu/alefzero/owncloud/datamodel/OCFile.java (Lennart Rosam     2012-05-16 09:48:34 +0200 348)         mParentId = parent_id;
435b31ba src/eu/alefzero/owncloud/datamodel/OCFile.java (Lennart Rosam     2012-05-16 09:48:34 +0200 349)     }
435b31ba src/eu/alefzero/owncloud/datamodel/OCFile.java (Lennart Rosam     2012-05-16 09:48:34 +0200 350) 
435b31ba src/eu/alefzero/owncloud/datamodel/OCFile.java (Lennart Rosam     2012-05-16 09:48:34 +0200 351)     /**
435b31ba src/eu/alefzero/owncloud/datamodel/OCFile.java (Lennart Rosam     2012-05-16 09:48:34 +0200 352)      * Sets the file size in bytes
435b31ba src/eu/alefzero/owncloud/datamodel/OCFile.java (Lennart Rosam     2012-05-16 09:48:34 +0200 353)      * 
ad8dbb31 src/eu/alefzero/owncloud/datamodel/OCFile.java (Lennart Rosam     2012-05-16 11:07:39 +0200 354)      * @param file_len to set
435b31ba src/eu/alefzero/owncloud/datamodel/OCFile.java (Lennart Rosam     2012-05-16 09:48:34 +0200 355)      */
435b31ba src/eu/alefzero/owncloud/datamodel/OCFile.java (Lennart Rosam     2012-05-16 09:48:34 +0200 356)     public void setFileLength(long file_len) {
435b31ba src/eu/alefzero/owncloud/datamodel/OCFile.java (Lennart Rosam     2012-05-16 09:48:34 +0200 357)         mLength = file_len;
435b31ba src/eu/alefzero/owncloud/datamodel/OCFile.java (Lennart Rosam     2012-05-16 09:48:34 +0200 358)     }
435b31ba src/eu/alefzero/owncloud/datamodel/OCFile.java (Lennart Rosam     2012-05-16 09:48:34 +0200 359) 
435b31ba src/eu/alefzero/owncloud/datamodel/OCFile.java (Lennart Rosam     2012-05-16 09:48:34 +0200 360)     /**
435b31ba src/eu/alefzero/owncloud/datamodel/OCFile.java (Lennart Rosam     2012-05-16 09:48:34 +0200 361)      * Returns the size of the file in bytes
435b31ba src/eu/alefzero/owncloud/datamodel/OCFile.java (Lennart Rosam     2012-05-16 09:48:34 +0200 362)      * 
435b31ba src/eu/alefzero/owncloud/datamodel/OCFile.java (Lennart Rosam     2012-05-16 09:48:34 +0200 363)      * @return The filesize in bytes
435b31ba src/eu/alefzero/owncloud/datamodel/OCFile.java (Lennart Rosam     2012-05-16 09:48:34 +0200 364)      */
435b31ba src/eu/alefzero/owncloud/datamodel/OCFile.java (Lennart Rosam     2012-05-16 09:48:34 +0200 365)     public long getFileLength() {
435b31ba src/eu/alefzero/owncloud/datamodel/OCFile.java (Lennart Rosam     2012-05-16 09:48:34 +0200 366)         return mLength;
435b31ba src/eu/alefzero/owncloud/datamodel/OCFile.java (Lennart Rosam     2012-05-16 09:48:34 +0200 367)     }
435b31ba src/eu/alefzero/owncloud/datamodel/OCFile.java (Lennart Rosam     2012-05-16 09:48:34 +0200 368) 
435b31ba src/eu/alefzero/owncloud/datamodel/OCFile.java (Lennart Rosam     2012-05-16 09:48:34 +0200 369)     /**
435b31ba src/eu/alefzero/owncloud/datamodel/OCFile.java (Lennart Rosam     2012-05-16 09:48:34 +0200 370)      * Returns the ID of the parent Folder
435b31ba src/eu/alefzero/owncloud/datamodel/OCFile.java (Lennart Rosam     2012-05-16 09:48:34 +0200 371)      * 
435b31ba src/eu/alefzero/owncloud/datamodel/OCFile.java (Lennart Rosam     2012-05-16 09:48:34 +0200 372)      * @return The ID
435b31ba src/eu/alefzero/owncloud/datamodel/OCFile.java (Lennart Rosam     2012-05-16 09:48:34 +0200 373)      */
435b31ba src/eu/alefzero/owncloud/datamodel/OCFile.java (Lennart Rosam     2012-05-16 09:48:34 +0200 374)     public long getParentId() {
435b31ba src/eu/alefzero/owncloud/datamodel/OCFile.java (Lennart Rosam     2012-05-16 09:48:34 +0200 375)         return mParentId;
435b31ba src/eu/alefzero/owncloud/datamodel/OCFile.java (Lennart Rosam     2012-05-16 09:48:34 +0200 376)     }
435b31ba src/eu/alefzero/owncloud/datamodel/OCFile.java (Lennart Rosam     2012-05-16 09:48:34 +0200 377) 
435b31ba src/eu/alefzero/owncloud/datamodel/OCFile.java (Lennart Rosam     2012-05-16 09:48:34 +0200 378)     /**
435b31ba src/eu/alefzero/owncloud/datamodel/OCFile.java (Lennart Rosam     2012-05-16 09:48:34 +0200 379)      * Check, if this file needs updating
435b31ba src/eu/alefzero/owncloud/datamodel/OCFile.java (Lennart Rosam     2012-05-16 09:48:34 +0200 380)      * 
435b31ba src/eu/alefzero/owncloud/datamodel/OCFile.java (Lennart Rosam     2012-05-16 09:48:34 +0200 381)      * @return
435b31ba src/eu/alefzero/owncloud/datamodel/OCFile.java (Lennart Rosam     2012-05-16 09:48:34 +0200 382)      */
435b31ba src/eu/alefzero/owncloud/datamodel/OCFile.java (Lennart Rosam     2012-05-16 09:48:34 +0200 383)     public boolean needsUpdatingWhileSaving() {
435b31ba src/eu/alefzero/owncloud/datamodel/OCFile.java (Lennart Rosam     2012-05-16 09:48:34 +0200 384)         return mNeedsUpdating;
435b31ba src/eu/alefzero/owncloud/datamodel/OCFile.java (Lennart Rosam     2012-05-16 09:48:34 +0200 385)     }
65523409 src/eu/alefzero/owncloud/datamodel/OCFile.java (Bartek Przybylski 2012-05-19 18:30:03 +0200 386)     
ff2271a8 src/com/owncloud/android/datamodel/OCFile.java (David A. Velasco  2012-11-15 17:18:04 +0100 387)     public long getLastSyncDateForProperties() {
ff2271a8 src/com/owncloud/android/datamodel/OCFile.java (David A. Velasco  2012-11-15 17:18:04 +0100 388)         return mLastSyncDateForProperties;
ff2271a8 src/com/owncloud/android/datamodel/OCFile.java (David A. Velasco  2012-11-15 17:18:04 +0100 389)     }
ff2271a8 src/com/owncloud/android/datamodel/OCFile.java (David A. Velasco  2012-11-15 17:18:04 +0100 390)     
ff2271a8 src/com/owncloud/android/datamodel/OCFile.java (David A. Velasco  2012-11-15 17:18:04 +0100 391)     public void setLastSyncDateForProperties(long lastSyncDate) {
ff2271a8 src/com/owncloud/android/datamodel/OCFile.java (David A. Velasco  2012-11-15 17:18:04 +0100 392)         mLastSyncDateForProperties = lastSyncDate;
65523409 src/eu/alefzero/owncloud/datamodel/OCFile.java (Bartek Przybylski 2012-05-19 18:30:03 +0200 393)     }
65523409 src/eu/alefzero/owncloud/datamodel/OCFile.java (Bartek Przybylski 2012-05-19 18:30:03 +0200 394)     
ff2271a8 src/com/owncloud/android/datamodel/OCFile.java (David A. Velasco  2012-11-15 17:18:04 +0100 395)     public long getLastSyncDateForData() {
ff2271a8 src/com/owncloud/android/datamodel/OCFile.java (David A. Velasco  2012-11-15 17:18:04 +0100 396)         return mLastSyncDateForData;
ff2271a8 src/com/owncloud/android/datamodel/OCFile.java (David A. Velasco  2012-11-15 17:18:04 +0100 397)     }
ff2271a8 src/com/owncloud/android/datamodel/OCFile.java (David A. Velasco  2012-11-15 17:18:04 +0100 398) 
ff2271a8 src/com/owncloud/android/datamodel/OCFile.java (David A. Velasco  2012-11-15 17:18:04 +0100 399)     public void setLastSyncDateForData(long lastSyncDate) {
ff2271a8 src/com/owncloud/android/datamodel/OCFile.java (David A. Velasco  2012-11-15 17:18:04 +0100 400)         mLastSyncDateForData = lastSyncDate;
65523409 src/eu/alefzero/owncloud/datamodel/OCFile.java (Bartek Przybylski 2012-05-19 18:30:03 +0200 401)     }
435b31ba src/eu/alefzero/owncloud/datamodel/OCFile.java (Lennart Rosam     2012-05-16 09:48:34 +0200 402) 
3113c459 src/eu/alefzero/owncloud/datamodel/OCFile.java (Bartek Przybylski 2012-07-08 16:57:48 +0200 403)     public void setKeepInSync(boolean keepInSync) {
3113c459 src/eu/alefzero/owncloud/datamodel/OCFile.java (Bartek Przybylski 2012-07-08 16:57:48 +0200 404)         mKeepInSync = keepInSync;
3113c459 src/eu/alefzero/owncloud/datamodel/OCFile.java (Bartek Przybylski 2012-07-08 16:57:48 +0200 405)     }
3113c459 src/eu/alefzero/owncloud/datamodel/OCFile.java (Bartek Przybylski 2012-07-08 16:57:48 +0200 406)     
3113c459 src/eu/alefzero/owncloud/datamodel/OCFile.java (Bartek Przybylski 2012-07-08 16:57:48 +0200 407)     public boolean keepInSync() {
3113c459 src/eu/alefzero/owncloud/datamodel/OCFile.java (Bartek Przybylski 2012-07-08 16:57:48 +0200 408)         return mKeepInSync;
3113c459 src/eu/alefzero/owncloud/datamodel/OCFile.java (Bartek Przybylski 2012-07-08 16:57:48 +0200 409)     }
3113c459 src/eu/alefzero/owncloud/datamodel/OCFile.java (Bartek Przybylski 2012-07-08 16:57:48 +0200 410)     
435b31ba src/eu/alefzero/owncloud/datamodel/OCFile.java (Lennart Rosam     2012-05-16 09:48:34 +0200 411)     @Override
435b31ba src/eu/alefzero/owncloud/datamodel/OCFile.java (Lennart Rosam     2012-05-16 09:48:34 +0200 412)     public int describeContents() {
435b31ba src/eu/alefzero/owncloud/datamodel/OCFile.java (Lennart Rosam     2012-05-16 09:48:34 +0200 413)         return this.hashCode();
435b31ba src/eu/alefzero/owncloud/datamodel/OCFile.java (Lennart Rosam     2012-05-16 09:48:34 +0200 414)     }
435b31ba src/eu/alefzero/owncloud/datamodel/OCFile.java (Lennart Rosam     2012-05-16 09:48:34 +0200 415) 
435b31ba src/eu/alefzero/owncloud/datamodel/OCFile.java (Lennart Rosam     2012-05-16 09:48:34 +0200 416)     @Override
49ad2498 src/eu/alefzero/owncloud/datamodel/OCFile.java (Bartek Przybylski 2012-05-23 22:40:31 +0200 417)     public int compareTo(OCFile another) {
49ad2498 src/eu/alefzero/owncloud/datamodel/OCFile.java (Bartek Przybylski 2012-05-23 22:40:31 +0200 418)         if (isDirectory() && another.isDirectory()) {
ec38629e src/eu/alefzero/owncloud/datamodel/OCFile.java (David A. Velasco  2012-06-25 15:18:27 +0200 419)             return getRemotePath().toLowerCase().compareTo(another.getRemotePath().toLowerCase());
49ad2498 src/eu/alefzero/owncloud/datamodel/OCFile.java (Bartek Przybylski 2012-05-23 22:40:31 +0200 420)         } else if (isDirectory()) {
49ad2498 src/eu/alefzero/owncloud/datamodel/OCFile.java (Bartek Przybylski 2012-05-23 22:40:31 +0200 421)             return -1;
49ad2498 src/eu/alefzero/owncloud/datamodel/OCFile.java (Bartek Przybylski 2012-05-23 22:40:31 +0200 422)         } else if (another.isDirectory()) {
49ad2498 src/eu/alefzero/owncloud/datamodel/OCFile.java (Bartek Przybylski 2012-05-23 22:40:31 +0200 423)             return 1;
49ad2498 src/eu/alefzero/owncloud/datamodel/OCFile.java (Bartek Przybylski 2012-05-23 22:40:31 +0200 424)         }
ec38629e src/eu/alefzero/owncloud/datamodel/OCFile.java (David A. Velasco  2012-06-25 15:18:27 +0200 425)         return getRemotePath().toLowerCase().compareTo(another.getRemotePath().toLowerCase());
49ad2498 src/eu/alefzero/owncloud/datamodel/OCFile.java (Bartek Przybylski 2012-05-23 22:40:31 +0200 426)     }
49ad2498 src/eu/alefzero/owncloud/datamodel/OCFile.java (Bartek Przybylski 2012-05-23 22:40:31 +0200 427) 
981bf054 src/com/owncloud/android/datamodel/OCFile.java (Bartek Przybylski 2012-09-29 23:55:19 +0200 428)     @Override
5ae7704e src/eu/alefzero/owncloud/datamodel/OCFile.java (Lennart Rosam     2012-05-23 22:39:37 +0200 429)     public boolean equals(Object o) {
00ef45c1 src/eu/alefzero/owncloud/datamodel/OCFile.java (Lennart Rosam     2012-05-25 02:56:23 +0200 430)         if(o instanceof OCFile){
00ef45c1 src/eu/alefzero/owncloud/datamodel/OCFile.java (Lennart Rosam     2012-05-25 02:56:23 +0200 431)             OCFile that = (OCFile) o;
00ef45c1 src/eu/alefzero/owncloud/datamodel/OCFile.java (Lennart Rosam     2012-05-25 02:56:23 +0200 432)             if(that != null){
00ef45c1 src/eu/alefzero/owncloud/datamodel/OCFile.java (Lennart Rosam     2012-05-25 02:56:23 +0200 433)                 return this.mId == that.mId;
00ef45c1 src/eu/alefzero/owncloud/datamodel/OCFile.java (Lennart Rosam     2012-05-25 02:56:23 +0200 434)             }
00ef45c1 src/eu/alefzero/owncloud/datamodel/OCFile.java (Lennart Rosam     2012-05-25 02:56:23 +0200 435)         }
00ef45c1 src/eu/alefzero/owncloud/datamodel/OCFile.java (Lennart Rosam     2012-05-25 02:56:23 +0200 436)         
00ef45c1 src/eu/alefzero/owncloud/datamodel/OCFile.java (Lennart Rosam     2012-05-25 02:56:23 +0200 437)         return false;
5ae7704e src/eu/alefzero/owncloud/datamodel/OCFile.java (Lennart Rosam     2012-05-23 22:39:37 +0200 438)     }
5ae7704e src/eu/alefzero/owncloud/datamodel/OCFile.java (Lennart Rosam     2012-05-23 22:39:37 +0200 439) 
5ae7704e src/eu/alefzero/owncloud/datamodel/OCFile.java (Lennart Rosam     2012-05-23 22:39:37 +0200 440)     @Override
5ae7704e src/eu/alefzero/owncloud/datamodel/OCFile.java (Lennart Rosam     2012-05-23 22:39:37 +0200 441)     public String toString() {
a032bdee src/eu/alefzero/owncloud/datamodel/OCFile.java (David A. Velasco  2012-07-26 10:44:24 +0200 442)         String asString = "[id=%s, name=%s, mime=%s, downloaded=%s, local=%s, remote=%s, parentId=%s, keepInSinc=%s]";
1baa27a4 src/com/owncloud/android/datamodel/OCFile.java (David A. Velasco  2012-11-06 13:35:08 +0100 443)         asString = String.format(asString, Long.valueOf(mId), getFileName(), mMimeType, isDown(), mLocalPath, mRemotePath, Long.valueOf(mParentId), Boolean.valueOf(mKeepInSync));
5ae7704e src/eu/alefzero/owncloud/datamodel/OCFile.java (Lennart Rosam     2012-05-23 22:39:37 +0200 444)         return asString;
5ae7704e src/eu/alefzero/owncloud/datamodel/OCFile.java (Lennart Rosam     2012-05-23 22:39:37 +0200 445)     }
5ae7704e src/eu/alefzero/owncloud/datamodel/OCFile.java (Lennart Rosam     2012-05-23 22:39:37 +0200 446) 
ff2271a8 src/com/owncloud/android/datamodel/OCFile.java (David A. Velasco  2012-11-15 17:18:04 +0100 447)     public String getEtag() {
ff2271a8 src/com/owncloud/android/datamodel/OCFile.java (David A. Velasco  2012-11-15 17:18:04 +0100 448)         return mEtag;
ff2271a8 src/com/owncloud/android/datamodel/OCFile.java (David A. Velasco  2012-11-15 17:18:04 +0100 449)     }
ff2271a8 src/com/owncloud/android/datamodel/OCFile.java (David A. Velasco  2012-11-15 17:18:04 +0100 450) 
ff2271a8 src/com/owncloud/android/datamodel/OCFile.java (David A. Velasco  2012-11-15 17:18:04 +0100 451)     public long getLocalModificationTimestamp() {
ff2271a8 src/com/owncloud/android/datamodel/OCFile.java (David A. Velasco  2012-11-15 17:18:04 +0100 452)         if (mLocalPath != null && mLocalPath.length() > 0) {
ff2271a8 src/com/owncloud/android/datamodel/OCFile.java (David A. Velasco  2012-11-15 17:18:04 +0100 453)             File f = new File(mLocalPath);
ff2271a8 src/com/owncloud/android/datamodel/OCFile.java (David A. Velasco  2012-11-15 17:18:04 +0100 454)             return f.lastModified();
ff2271a8 src/com/owncloud/android/datamodel/OCFile.java (David A. Velasco  2012-11-15 17:18:04 +0100 455)         }
ff2271a8 src/com/owncloud/android/datamodel/OCFile.java (David A. Velasco  2012-11-15 17:18:04 +0100 456)         return 0;
ff2271a8 src/com/owncloud/android/datamodel/OCFile.java (David A. Velasco  2012-11-15 17:18:04 +0100 457)     }
ff2271a8 src/com/owncloud/android/datamodel/OCFile.java (David A. Velasco  2012-11-15 17:18:04 +0100 458) 
7101a042 src/com/owncloud/android/datamodel/OCFile.java (David A. Velasco  2013-02-14 19:21:09 +0100 459)     /** @return  'True' if the file contains audio */
18bf35a8 src/com/owncloud/android/datamodel/OCFile.java (David A. Velasco  2013-02-06 14:14:55 +0100 460)     public boolean isAudio() {
586793a2 src/com/owncloud/android/datamodel/OCFile.java (David A. Velasco  2013-02-07 11:09:28 +0100 461)         return (mMimeType != null && mMimeType.startsWith("audio/"));
586793a2 src/com/owncloud/android/datamodel/OCFile.java (David A. Velasco  2013-02-07 11:09:28 +0100 462)     }
586793a2 src/com/owncloud/android/datamodel/OCFile.java (David A. Velasco  2013-02-07 11:09:28 +0100 463) 
7101a042 src/com/owncloud/android/datamodel/OCFile.java (David A. Velasco  2013-02-14 19:21:09 +0100 464)     /** @return  'True' if the file contains video */
586793a2 src/com/owncloud/android/datamodel/OCFile.java (David A. Velasco  2013-02-07 11:09:28 +0100 465)     public boolean isVideo() {
586793a2 src/com/owncloud/android/datamodel/OCFile.java (David A. Velasco  2013-02-07 11:09:28 +0100 466)         return (mMimeType != null && mMimeType.startsWith("video/"));
18bf35a8 src/com/owncloud/android/datamodel/OCFile.java (David A. Velasco  2013-02-06 14:14:55 +0100 467)     }
18bf35a8 src/com/owncloud/android/datamodel/OCFile.java (David A. Velasco  2013-02-06 14:14:55 +0100 468) 
7101a042 src/com/owncloud/android/datamodel/OCFile.java (David A. Velasco  2013-02-14 19:21:09 +0100 469)     /** @return  'True' if the file contains an image */
7101a042 src/com/owncloud/android/datamodel/OCFile.java (David A. Velasco  2013-02-14 19:21:09 +0100 470)     public boolean isImage() {
a212611a src/com/owncloud/android/datamodel/OCFile.java (David A. Velasco  2013-03-01 14:55:59 +0100 471)         return ((mMimeType != null && mMimeType.startsWith("image/")) ||
a212611a src/com/owncloud/android/datamodel/OCFile.java (David A. Velasco  2013-03-01 14:55:59 +0100 472)                  getMimeTypeFromName().startsWith("image/"));
a212611a src/com/owncloud/android/datamodel/OCFile.java (David A. Velasco  2013-03-01 14:55:59 +0100 473)     }
a212611a src/com/owncloud/android/datamodel/OCFile.java (David A. Velasco  2013-03-01 14:55:59 +0100 474)     
a212611a src/com/owncloud/android/datamodel/OCFile.java (David A. Velasco  2013-03-01 14:55:59 +0100 475)     public String getMimeTypeFromName() {
a212611a src/com/owncloud/android/datamodel/OCFile.java (David A. Velasco  2013-03-01 14:55:59 +0100 476)         String extension = "";
a212611a src/com/owncloud/android/datamodel/OCFile.java (David A. Velasco  2013-03-01 14:55:59 +0100 477)         int pos = mRemotePath.lastIndexOf('.');
a212611a src/com/owncloud/android/datamodel/OCFile.java (David A. Velasco  2013-03-01 14:55:59 +0100 478)         if (pos >= 0) {
a212611a src/com/owncloud/android/datamodel/OCFile.java (David A. Velasco  2013-03-01 14:55:59 +0100 479)             extension = mRemotePath.substring(pos + 1);
a212611a src/com/owncloud/android/datamodel/OCFile.java (David A. Velasco  2013-03-01 14:55:59 +0100 480)         }
a212611a src/com/owncloud/android/datamodel/OCFile.java (David A. Velasco  2013-03-01 14:55:59 +0100 481)         String result = MimeTypeMap.getSingleton().getMimeTypeFromExtension(extension.toLowerCase());
a212611a src/com/owncloud/android/datamodel/OCFile.java (David A. Velasco  2013-03-01 14:55:59 +0100 482)         return (result != null) ? result : "";
7101a042 src/com/owncloud/android/datamodel/OCFile.java (David A. Velasco  2013-02-14 19:21:09 +0100 483)     }
7101a042 src/com/owncloud/android/datamodel/OCFile.java (David A. Velasco  2013-02-14 19:21:09 +0100 484) 
0174908b src/eu/alefzero/owncloud/datamodel/OCFile.java (Bartek Przybylski 2012-04-07 14:37:03 +0200 485) }
