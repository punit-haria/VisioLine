0aeb4258 src/eu/alefzero/owncloud/utils/OwnCloudVersion.java (Bartek Przybylski 2012-05-13 16:13:13 +0200  1) /* ownCloud Android client application
0aeb4258 src/eu/alefzero/owncloud/utils/OwnCloudVersion.java (Bartek Przybylski 2012-05-13 16:13:13 +0200  2)  *   Copyright (C) 2012  Bartek Przybylski
bb257ec7 src/com/owncloud/android/utils/OwnCloudVersion.java (David A. Velasco  2013-02-07 18:45:10 +0100  3)  *   Copyright (C) 2012-2013 ownCloud Inc.
0aeb4258 src/eu/alefzero/owncloud/utils/OwnCloudVersion.java (Bartek Przybylski 2012-05-13 16:13:13 +0200  4)  *
0aeb4258 src/eu/alefzero/owncloud/utils/OwnCloudVersion.java (Bartek Przybylski 2012-05-13 16:13:13 +0200  5)  *   This program is free software: you can redistribute it and/or modify
bdc0332c src/com/owncloud/android/utils/OwnCloudVersion.java (David A. Velasco  2013-04-17 12:26:13 +0200  6)  *   it under the terms of the GNU General Public License version 2,
bdc0332c src/com/owncloud/android/utils/OwnCloudVersion.java (David A. Velasco  2013-04-17 12:26:13 +0200  7)  *   as published by the Free Software Foundation.
0aeb4258 src/eu/alefzero/owncloud/utils/OwnCloudVersion.java (Bartek Przybylski 2012-05-13 16:13:13 +0200  8)  *
0aeb4258 src/eu/alefzero/owncloud/utils/OwnCloudVersion.java (Bartek Przybylski 2012-05-13 16:13:13 +0200  9)  *   This program is distributed in the hope that it will be useful,
0aeb4258 src/eu/alefzero/owncloud/utils/OwnCloudVersion.java (Bartek Przybylski 2012-05-13 16:13:13 +0200 10)  *   but WITHOUT ANY WARRANTY; without even the implied warranty of
0aeb4258 src/eu/alefzero/owncloud/utils/OwnCloudVersion.java (Bartek Przybylski 2012-05-13 16:13:13 +0200 11)  *   MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
0aeb4258 src/eu/alefzero/owncloud/utils/OwnCloudVersion.java (Bartek Przybylski 2012-05-13 16:13:13 +0200 12)  *   GNU General Public License for more details.
0aeb4258 src/eu/alefzero/owncloud/utils/OwnCloudVersion.java (Bartek Przybylski 2012-05-13 16:13:13 +0200 13)  *
0aeb4258 src/eu/alefzero/owncloud/utils/OwnCloudVersion.java (Bartek Przybylski 2012-05-13 16:13:13 +0200 14)  *   You should have received a copy of the GNU General Public License
0aeb4258 src/eu/alefzero/owncloud/utils/OwnCloudVersion.java (Bartek Przybylski 2012-05-13 16:13:13 +0200 15)  *   along with this program.  If not, see <http://www.gnu.org/licenses/>.
0aeb4258 src/eu/alefzero/owncloud/utils/OwnCloudVersion.java (Bartek Przybylski 2012-05-13 16:13:13 +0200 16)  *
0aeb4258 src/eu/alefzero/owncloud/utils/OwnCloudVersion.java (Bartek Przybylski 2012-05-13 16:13:13 +0200 17)  */
0aeb4258 src/eu/alefzero/owncloud/utils/OwnCloudVersion.java (Bartek Przybylski 2012-05-13 16:13:13 +0200 18) 
a4ba6170 src/com/owncloud/android/utils/OwnCloudVersion.java (Bartek Przybylski 2012-07-31 17:43:37 +0200 19) package com.owncloud.android.utils;
0aeb4258 src/eu/alefzero/owncloud/utils/OwnCloudVersion.java (Bartek Przybylski 2012-05-13 16:13:13 +0200 20) 
0aeb4258 src/eu/alefzero/owncloud/utils/OwnCloudVersion.java (Bartek Przybylski 2012-05-13 16:13:13 +0200 21) public class OwnCloudVersion implements Comparable<OwnCloudVersion> {
435b31ba src/eu/alefzero/owncloud/utils/OwnCloudVersion.java (Lennart Rosam     2012-05-16 09:48:34 +0200 22)     public static final OwnCloudVersion owncloud_v1 = new OwnCloudVersion(
435b31ba src/eu/alefzero/owncloud/utils/OwnCloudVersion.java (Lennart Rosam     2012-05-16 09:48:34 +0200 23)             0x010000);
435b31ba src/eu/alefzero/owncloud/utils/OwnCloudVersion.java (Lennart Rosam     2012-05-16 09:48:34 +0200 24)     public static final OwnCloudVersion owncloud_v2 = new OwnCloudVersion(
435b31ba src/eu/alefzero/owncloud/utils/OwnCloudVersion.java (Lennart Rosam     2012-05-16 09:48:34 +0200 25)             0x020000);
435b31ba src/eu/alefzero/owncloud/utils/OwnCloudVersion.java (Lennart Rosam     2012-05-16 09:48:34 +0200 26)     public static final OwnCloudVersion owncloud_v3 = new OwnCloudVersion(
435b31ba src/eu/alefzero/owncloud/utils/OwnCloudVersion.java (Lennart Rosam     2012-05-16 09:48:34 +0200 27)             0x030000);
435b31ba src/eu/alefzero/owncloud/utils/OwnCloudVersion.java (Lennart Rosam     2012-05-16 09:48:34 +0200 28)     public static final OwnCloudVersion owncloud_v4 = new OwnCloudVersion(
435b31ba src/eu/alefzero/owncloud/utils/OwnCloudVersion.java (Lennart Rosam     2012-05-16 09:48:34 +0200 29)             0x040000);
53b67429 src/com/owncloud/android/utils/OwnCloudVersion.java (David A. Velasco  2012-09-12 16:16:56 +0200 30)     public static final OwnCloudVersion owncloud_v4_5 = new OwnCloudVersion(
53b67429 src/com/owncloud/android/utils/OwnCloudVersion.java (David A. Velasco  2012-09-12 16:16:56 +0200 31)             0x040500);
0aeb4258 src/eu/alefzero/owncloud/utils/OwnCloudVersion.java (Bartek Przybylski 2012-05-13 16:13:13 +0200 32) 
435b31ba src/eu/alefzero/owncloud/utils/OwnCloudVersion.java (Lennart Rosam     2012-05-16 09:48:34 +0200 33)     // format is in version
435b31ba src/eu/alefzero/owncloud/utils/OwnCloudVersion.java (Lennart Rosam     2012-05-16 09:48:34 +0200 34)     // 0xAABBCC
435b31ba src/eu/alefzero/owncloud/utils/OwnCloudVersion.java (Lennart Rosam     2012-05-16 09:48:34 +0200 35)     // for version AA.BB.CC
2623e9c1 src/com/owncloud/android/utils/OwnCloudVersion.java (David A. Velasco  2013-02-08 09:22:46 +0100 36)     // ie version 2.0.3 will be stored as 0x030003
435b31ba src/eu/alefzero/owncloud/utils/OwnCloudVersion.java (Lennart Rosam     2012-05-16 09:48:34 +0200 37)     private int mVersion;
435b31ba src/eu/alefzero/owncloud/utils/OwnCloudVersion.java (Lennart Rosam     2012-05-16 09:48:34 +0200 38)     private boolean mIsValid;
435b31ba src/eu/alefzero/owncloud/utils/OwnCloudVersion.java (Lennart Rosam     2012-05-16 09:48:34 +0200 39) 
435b31ba src/eu/alefzero/owncloud/utils/OwnCloudVersion.java (Lennart Rosam     2012-05-16 09:48:34 +0200 40)     public OwnCloudVersion(int version) {
435b31ba src/eu/alefzero/owncloud/utils/OwnCloudVersion.java (Lennart Rosam     2012-05-16 09:48:34 +0200 41)         mVersion = version;
435b31ba src/eu/alefzero/owncloud/utils/OwnCloudVersion.java (Lennart Rosam     2012-05-16 09:48:34 +0200 42)         mIsValid = true;
435b31ba src/eu/alefzero/owncloud/utils/OwnCloudVersion.java (Lennart Rosam     2012-05-16 09:48:34 +0200 43)     }
435b31ba src/eu/alefzero/owncloud/utils/OwnCloudVersion.java (Lennart Rosam     2012-05-16 09:48:34 +0200 44) 
435b31ba src/eu/alefzero/owncloud/utils/OwnCloudVersion.java (Lennart Rosam     2012-05-16 09:48:34 +0200 45)     public OwnCloudVersion(String version) {
435b31ba src/eu/alefzero/owncloud/utils/OwnCloudVersion.java (Lennart Rosam     2012-05-16 09:48:34 +0200 46)         mVersion = 0;
435b31ba src/eu/alefzero/owncloud/utils/OwnCloudVersion.java (Lennart Rosam     2012-05-16 09:48:34 +0200 47)         mIsValid = false;
435b31ba src/eu/alefzero/owncloud/utils/OwnCloudVersion.java (Lennart Rosam     2012-05-16 09:48:34 +0200 48)         parseVersionString(version);
435b31ba src/eu/alefzero/owncloud/utils/OwnCloudVersion.java (Lennart Rosam     2012-05-16 09:48:34 +0200 49)     }
435b31ba src/eu/alefzero/owncloud/utils/OwnCloudVersion.java (Lennart Rosam     2012-05-16 09:48:34 +0200 50) 
435b31ba src/eu/alefzero/owncloud/utils/OwnCloudVersion.java (Lennart Rosam     2012-05-16 09:48:34 +0200 51)     public String toString() {
435b31ba src/eu/alefzero/owncloud/utils/OwnCloudVersion.java (Lennart Rosam     2012-05-16 09:48:34 +0200 52)         return ((mVersion >> 16) % 256) + "." + ((mVersion >> 8) % 256) + "."
435b31ba src/eu/alefzero/owncloud/utils/OwnCloudVersion.java (Lennart Rosam     2012-05-16 09:48:34 +0200 53)                 + ((mVersion) % 256);
435b31ba src/eu/alefzero/owncloud/utils/OwnCloudVersion.java (Lennart Rosam     2012-05-16 09:48:34 +0200 54)     }
435b31ba src/eu/alefzero/owncloud/utils/OwnCloudVersion.java (Lennart Rosam     2012-05-16 09:48:34 +0200 55) 
435b31ba src/eu/alefzero/owncloud/utils/OwnCloudVersion.java (Lennart Rosam     2012-05-16 09:48:34 +0200 56)     public boolean isVersionValid() {
435b31ba src/eu/alefzero/owncloud/utils/OwnCloudVersion.java (Lennart Rosam     2012-05-16 09:48:34 +0200 57)         return mIsValid;
435b31ba src/eu/alefzero/owncloud/utils/OwnCloudVersion.java (Lennart Rosam     2012-05-16 09:48:34 +0200 58)     }
435b31ba src/eu/alefzero/owncloud/utils/OwnCloudVersion.java (Lennart Rosam     2012-05-16 09:48:34 +0200 59) 
435b31ba src/eu/alefzero/owncloud/utils/OwnCloudVersion.java (Lennart Rosam     2012-05-16 09:48:34 +0200 60)     @Override
435b31ba src/eu/alefzero/owncloud/utils/OwnCloudVersion.java (Lennart Rosam     2012-05-16 09:48:34 +0200 61)     public int compareTo(OwnCloudVersion another) {
435b31ba src/eu/alefzero/owncloud/utils/OwnCloudVersion.java (Lennart Rosam     2012-05-16 09:48:34 +0200 62)         return another.mVersion == mVersion ? 0
435b31ba src/eu/alefzero/owncloud/utils/OwnCloudVersion.java (Lennart Rosam     2012-05-16 09:48:34 +0200 63)                 : another.mVersion < mVersion ? 1 : -1;
435b31ba src/eu/alefzero/owncloud/utils/OwnCloudVersion.java (Lennart Rosam     2012-05-16 09:48:34 +0200 64)     }
435b31ba src/eu/alefzero/owncloud/utils/OwnCloudVersion.java (Lennart Rosam     2012-05-16 09:48:34 +0200 65) 
435b31ba src/eu/alefzero/owncloud/utils/OwnCloudVersion.java (Lennart Rosam     2012-05-16 09:48:34 +0200 66)     private void parseVersionString(String version) {
435b31ba src/eu/alefzero/owncloud/utils/OwnCloudVersion.java (Lennart Rosam     2012-05-16 09:48:34 +0200 67)         try {
435b31ba src/eu/alefzero/owncloud/utils/OwnCloudVersion.java (Lennart Rosam     2012-05-16 09:48:34 +0200 68)             String[] nums = version.split("\\.");
435b31ba src/eu/alefzero/owncloud/utils/OwnCloudVersion.java (Lennart Rosam     2012-05-16 09:48:34 +0200 69)             if (nums.length > 0) {
435b31ba src/eu/alefzero/owncloud/utils/OwnCloudVersion.java (Lennart Rosam     2012-05-16 09:48:34 +0200 70)                 mVersion += Integer.parseInt(nums[0]);
435b31ba src/eu/alefzero/owncloud/utils/OwnCloudVersion.java (Lennart Rosam     2012-05-16 09:48:34 +0200 71)             }
435b31ba src/eu/alefzero/owncloud/utils/OwnCloudVersion.java (Lennart Rosam     2012-05-16 09:48:34 +0200 72)             mVersion = mVersion << 8;
435b31ba src/eu/alefzero/owncloud/utils/OwnCloudVersion.java (Lennart Rosam     2012-05-16 09:48:34 +0200 73)             if (nums.length > 1) {
435b31ba src/eu/alefzero/owncloud/utils/OwnCloudVersion.java (Lennart Rosam     2012-05-16 09:48:34 +0200 74)                 mVersion += Integer.parseInt(nums[1]);
435b31ba src/eu/alefzero/owncloud/utils/OwnCloudVersion.java (Lennart Rosam     2012-05-16 09:48:34 +0200 75)             }
435b31ba src/eu/alefzero/owncloud/utils/OwnCloudVersion.java (Lennart Rosam     2012-05-16 09:48:34 +0200 76)             mVersion = mVersion << 8;
435b31ba src/eu/alefzero/owncloud/utils/OwnCloudVersion.java (Lennart Rosam     2012-05-16 09:48:34 +0200 77)             if (nums.length > 2) {
435b31ba src/eu/alefzero/owncloud/utils/OwnCloudVersion.java (Lennart Rosam     2012-05-16 09:48:34 +0200 78)                 mVersion += Integer.parseInt(nums[2]);
435b31ba src/eu/alefzero/owncloud/utils/OwnCloudVersion.java (Lennart Rosam     2012-05-16 09:48:34 +0200 79)             }
435b31ba src/eu/alefzero/owncloud/utils/OwnCloudVersion.java (Lennart Rosam     2012-05-16 09:48:34 +0200 80)             mIsValid = true;
435b31ba src/eu/alefzero/owncloud/utils/OwnCloudVersion.java (Lennart Rosam     2012-05-16 09:48:34 +0200 81)         } catch (Exception e) {
435b31ba src/eu/alefzero/owncloud/utils/OwnCloudVersion.java (Lennart Rosam     2012-05-16 09:48:34 +0200 82)             mIsValid = false;
435b31ba src/eu/alefzero/owncloud/utils/OwnCloudVersion.java (Lennart Rosam     2012-05-16 09:48:34 +0200 83)         }
0aeb4258 src/eu/alefzero/owncloud/utils/OwnCloudVersion.java (Bartek Przybylski 2012-05-13 16:13:13 +0200 84)     }
0aeb4258 src/eu/alefzero/owncloud/utils/OwnCloudVersion.java (Bartek Przybylski 2012-05-13 16:13:13 +0200 85) }
