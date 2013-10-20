11b88e0f (David A. Velasco 2012-12-19 14:12:53 +0100  1) /* ownCloud Android client application
11b88e0f (David A. Velasco 2012-12-19 14:12:53 +0100  2)  *   Copyright (C) 2012  ownCloud Inc.
11b88e0f (David A. Velasco 2012-12-19 14:12:53 +0100  3)  *
11b88e0f (David A. Velasco 2012-12-19 14:12:53 +0100  4)  *   This program is free software: you can redistribute it and/or modify
2946d8dd (David A. Velasco 2013-04-25 19:39:22 +0200  5)  *   it under the terms of the GNU General Public License version 2,
2946d8dd (David A. Velasco 2013-04-25 19:39:22 +0200  6)  *   as published by the Free Software Foundation.
11b88e0f (David A. Velasco 2012-12-19 14:12:53 +0100  7)  *
11b88e0f (David A. Velasco 2012-12-19 14:12:53 +0100  8)  *   This program is distributed in the hope that it will be useful,
11b88e0f (David A. Velasco 2012-12-19 14:12:53 +0100  9)  *   but WITHOUT ANY WARRANTY; without even the implied warranty of
11b88e0f (David A. Velasco 2012-12-19 14:12:53 +0100 10)  *   MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
11b88e0f (David A. Velasco 2012-12-19 14:12:53 +0100 11)  *   GNU General Public License for more details.
11b88e0f (David A. Velasco 2012-12-19 14:12:53 +0100 12)  *
11b88e0f (David A. Velasco 2012-12-19 14:12:53 +0100 13)  *   You should have received a copy of the GNU General Public License
11b88e0f (David A. Velasco 2012-12-19 14:12:53 +0100 14)  *   along with this program.  If not, see <http://www.gnu.org/licenses/>.
11b88e0f (David A. Velasco 2012-12-19 14:12:53 +0100 15)  *
11b88e0f (David A. Velasco 2012-12-19 14:12:53 +0100 16)  */
11b88e0f (David A. Velasco 2012-12-19 14:12:53 +0100 17) 
11b88e0f (David A. Velasco 2012-12-19 14:12:53 +0100 18) package com.owncloud.android.network;
11b88e0f (David A. Velasco 2012-12-19 14:12:53 +0100 19) 
11b88e0f (David A. Velasco 2012-12-19 14:12:53 +0100 20) import org.apache.commons.httpclient.Credentials;
11b88e0f (David A. Velasco 2012-12-19 14:12:53 +0100 21) import org.apache.commons.httpclient.util.LangUtils;
11b88e0f (David A. Velasco 2012-12-19 14:12:53 +0100 22) 
11b88e0f (David A. Velasco 2012-12-19 14:12:53 +0100 23) /**
11b88e0f (David A. Velasco 2012-12-19 14:12:53 +0100 24)  * Bearer token {@link Credentials}
11b88e0f (David A. Velasco 2012-12-19 14:12:53 +0100 25)  *
11b88e0f (David A. Velasco 2012-12-19 14:12:53 +0100 26)  * @author David A. Velasco
11b88e0f (David A. Velasco 2012-12-19 14:12:53 +0100 27)  */
11b88e0f (David A. Velasco 2012-12-19 14:12:53 +0100 28) public class BearerCredentials implements Credentials {
11b88e0f (David A. Velasco 2012-12-19 14:12:53 +0100 29) 
11b88e0f (David A. Velasco 2012-12-19 14:12:53 +0100 30)     
11b88e0f (David A. Velasco 2012-12-19 14:12:53 +0100 31)     private String mAccessToken;
11b88e0f (David A. Velasco 2012-12-19 14:12:53 +0100 32)     
11b88e0f (David A. Velasco 2012-12-19 14:12:53 +0100 33)     
11b88e0f (David A. Velasco 2012-12-19 14:12:53 +0100 34)     /**
11b88e0f (David A. Velasco 2012-12-19 14:12:53 +0100 35)      * The constructor with the bearer token
11b88e0f (David A. Velasco 2012-12-19 14:12:53 +0100 36)      *
11b88e0f (David A. Velasco 2012-12-19 14:12:53 +0100 37)      * @param token     The bearer token
11b88e0f (David A. Velasco 2012-12-19 14:12:53 +0100 38)      */
11b88e0f (David A. Velasco 2012-12-19 14:12:53 +0100 39)     public BearerCredentials(String token) {
7d1ea981 (David A. Velasco 2013-03-27 12:55:47 +0100 40)         /*if (token == null) {
11b88e0f (David A. Velasco 2012-12-19 14:12:53 +0100 41)             throw new IllegalArgumentException("Bearer token may not be null");            
7d1ea981 (David A. Velasco 2013-03-27 12:55:47 +0100 42)         }*/
7d1ea981 (David A. Velasco 2013-03-27 12:55:47 +0100 43)         mAccessToken = (token == null) ? "" : token;
11b88e0f (David A. Velasco 2012-12-19 14:12:53 +0100 44)     }
11b88e0f (David A. Velasco 2012-12-19 14:12:53 +0100 45) 
11b88e0f (David A. Velasco 2012-12-19 14:12:53 +0100 46) 
11b88e0f (David A. Velasco 2012-12-19 14:12:53 +0100 47)     /**
11b88e0f (David A. Velasco 2012-12-19 14:12:53 +0100 48)      * Returns the access token
11b88e0f (David A. Velasco 2012-12-19 14:12:53 +0100 49)      *
11b88e0f (David A. Velasco 2012-12-19 14:12:53 +0100 50)      * @return      The access token
11b88e0f (David A. Velasco 2012-12-19 14:12:53 +0100 51)      */
11b88e0f (David A. Velasco 2012-12-19 14:12:53 +0100 52)     public String getAccessToken() {
11b88e0f (David A. Velasco 2012-12-19 14:12:53 +0100 53)         return mAccessToken;
11b88e0f (David A. Velasco 2012-12-19 14:12:53 +0100 54)     }
11b88e0f (David A. Velasco 2012-12-19 14:12:53 +0100 55) 
11b88e0f (David A. Velasco 2012-12-19 14:12:53 +0100 56) 
11b88e0f (David A. Velasco 2012-12-19 14:12:53 +0100 57)     /**
11b88e0f (David A. Velasco 2012-12-19 14:12:53 +0100 58)      * Get this object string.
11b88e0f (David A. Velasco 2012-12-19 14:12:53 +0100 59)      *
11b88e0f (David A. Velasco 2012-12-19 14:12:53 +0100 60)      * @return  The access token
11b88e0f (David A. Velasco 2012-12-19 14:12:53 +0100 61)      */
11b88e0f (David A. Velasco 2012-12-19 14:12:53 +0100 62)     public String toString() {
11b88e0f (David A. Velasco 2012-12-19 14:12:53 +0100 63)         return mAccessToken;
11b88e0f (David A. Velasco 2012-12-19 14:12:53 +0100 64)     }
11b88e0f (David A. Velasco 2012-12-19 14:12:53 +0100 65) 
11b88e0f (David A. Velasco 2012-12-19 14:12:53 +0100 66)     /**
11b88e0f (David A. Velasco 2012-12-19 14:12:53 +0100 67)      * Does a hash of the access token.
11b88e0f (David A. Velasco 2012-12-19 14:12:53 +0100 68)      *
11b88e0f (David A. Velasco 2012-12-19 14:12:53 +0100 69)      * @return The hash code of the access token
11b88e0f (David A. Velasco 2012-12-19 14:12:53 +0100 70)      */
11b88e0f (David A. Velasco 2012-12-19 14:12:53 +0100 71)     public int hashCode() {
11b88e0f (David A. Velasco 2012-12-19 14:12:53 +0100 72)         int hash = LangUtils.HASH_SEED;
11b88e0f (David A. Velasco 2012-12-19 14:12:53 +0100 73)         hash = LangUtils.hashCode(hash, mAccessToken);
11b88e0f (David A. Velasco 2012-12-19 14:12:53 +0100 74)         return hash;
11b88e0f (David A. Velasco 2012-12-19 14:12:53 +0100 75)     }
11b88e0f (David A. Velasco 2012-12-19 14:12:53 +0100 76) 
11b88e0f (David A. Velasco 2012-12-19 14:12:53 +0100 77)     /**
11b88e0f (David A. Velasco 2012-12-19 14:12:53 +0100 78)      * These credentials are assumed equal if accessToken is the same.
11b88e0f (David A. Velasco 2012-12-19 14:12:53 +0100 79)      *
11b88e0f (David A. Velasco 2012-12-19 14:12:53 +0100 80)      * @param   o   The other object to compare with.
11b88e0f (David A. Velasco 2012-12-19 14:12:53 +0100 81)      *
11b88e0f (David A. Velasco 2012-12-19 14:12:53 +0100 82)      * @return      'True' if the object is equivalent.
11b88e0f (David A. Velasco 2012-12-19 14:12:53 +0100 83)      */
11b88e0f (David A. Velasco 2012-12-19 14:12:53 +0100 84)     public boolean equals(Object o) {
11b88e0f (David A. Velasco 2012-12-19 14:12:53 +0100 85)         if (o == null) return false;
11b88e0f (David A. Velasco 2012-12-19 14:12:53 +0100 86)         if (this == o) return true;
11b88e0f (David A. Velasco 2012-12-19 14:12:53 +0100 87)         if (this.getClass().equals(o.getClass())) {
11b88e0f (David A. Velasco 2012-12-19 14:12:53 +0100 88)             BearerCredentials that = (BearerCredentials) o;
11b88e0f (David A. Velasco 2012-12-19 14:12:53 +0100 89)             if (LangUtils.equals(mAccessToken, that.mAccessToken)) {
11b88e0f (David A. Velasco 2012-12-19 14:12:53 +0100 90)                 return true;
11b88e0f (David A. Velasco 2012-12-19 14:12:53 +0100 91)             }
11b88e0f (David A. Velasco 2012-12-19 14:12:53 +0100 92)         }
11b88e0f (David A. Velasco 2012-12-19 14:12:53 +0100 93)         return false;
11b88e0f (David A. Velasco 2012-12-19 14:12:53 +0100 94)     }
11b88e0f (David A. Velasco 2012-12-19 14:12:53 +0100 95) 
11b88e0f (David A. Velasco 2012-12-19 14:12:53 +0100 96) }
11b88e0f (David A. Velasco 2012-12-19 14:12:53 +0100 97) 
