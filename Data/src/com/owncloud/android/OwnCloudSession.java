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
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700 18) package com.owncloud.android;
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700 19) 
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700 20) /**
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700 21)  * Represents a session to an ownCloud instance
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700 22)  * 
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700 23)  * @author Bartek Przybylski
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700 24)  * 
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700 25)  */
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700 26) public class OwnCloudSession {
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700 27)     private String mSessionName;
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700 28)     private String mSessionUrl;
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700 29)     private int mEntryId;
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700 30) 
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700 31)     public OwnCloudSession(String name, String url, int entryId) {
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700 32)         mSessionName = name;
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700 33)         mSessionUrl = url;
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700 34)         mEntryId = entryId;
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700 35)     }
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700 36) 
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700 37)     public void setName(String name) {
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700 38)         mSessionName = name;
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700 39)     }
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700 40) 
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700 41)     public String getName() {
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700 42)         return mSessionName;
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700 43)     }
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700 44) 
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700 45)     public void setUrl(String url) {
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700 46)         mSessionUrl = url;
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700 47)     }
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700 48) 
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700 49)     public String getUrl() {
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700 50)         return mSessionUrl;
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700 51)     }
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700 52) 
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700 53)     public int getEntryId() {
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700 54)         return mEntryId;
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700 55)     }
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700 56) }
