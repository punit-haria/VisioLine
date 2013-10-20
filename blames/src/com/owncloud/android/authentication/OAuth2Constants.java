69d6d821 (David A. Velasco 2013-03-21 12:21:48 +0100  1) /* ownCloud Android client application
69d6d821 (David A. Velasco 2013-03-21 12:21:48 +0100  2)  *   Copyright (C) 2012-2013 ownCloud Inc.
69d6d821 (David A. Velasco 2013-03-21 12:21:48 +0100  3)  *
69d6d821 (David A. Velasco 2013-03-21 12:21:48 +0100  4)  *   This program is free software: you can redistribute it and/or modify
2946d8dd (David A. Velasco 2013-04-25 19:39:22 +0200  5)  *   it under the terms of the GNU General Public License version 2,
2946d8dd (David A. Velasco 2013-04-25 19:39:22 +0200  6)  *   as published by the Free Software Foundation.
69d6d821 (David A. Velasco 2013-03-21 12:21:48 +0100  7)  *
69d6d821 (David A. Velasco 2013-03-21 12:21:48 +0100  8)  *   This program is distributed in the hope that it will be useful,
69d6d821 (David A. Velasco 2013-03-21 12:21:48 +0100  9)  *   but WITHOUT ANY WARRANTY; without even the implied warranty of
69d6d821 (David A. Velasco 2013-03-21 12:21:48 +0100 10)  *   MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
69d6d821 (David A. Velasco 2013-03-21 12:21:48 +0100 11)  *   GNU General Public License for more details.
69d6d821 (David A. Velasco 2013-03-21 12:21:48 +0100 12)  *
69d6d821 (David A. Velasco 2013-03-21 12:21:48 +0100 13)  *   You should have received a copy of the GNU General Public License
69d6d821 (David A. Velasco 2013-03-21 12:21:48 +0100 14)  *   along with this program.  If not, see <http://www.gnu.org/licenses/>.
69d6d821 (David A. Velasco 2013-03-21 12:21:48 +0100 15)  *
69d6d821 (David A. Velasco 2013-03-21 12:21:48 +0100 16)  */
69d6d821 (David A. Velasco 2013-03-21 12:21:48 +0100 17) 
69d6d821 (David A. Velasco 2013-03-21 12:21:48 +0100 18) package com.owncloud.android.authentication;
69d6d821 (David A. Velasco 2013-03-21 12:21:48 +0100 19) 
69d6d821 (David A. Velasco 2013-03-21 12:21:48 +0100 20) /** 
69d6d821 (David A. Velasco 2013-03-21 12:21:48 +0100 21)  * Constant values for OAuth 2 protocol.
69d6d821 (David A. Velasco 2013-03-21 12:21:48 +0100 22)  * 
69d6d821 (David A. Velasco 2013-03-21 12:21:48 +0100 23)  * Includes required and optional parameter NAMES used in the 'authorization code' grant type.
69d6d821 (David A. Velasco 2013-03-21 12:21:48 +0100 24)  *  
69d6d821 (David A. Velasco 2013-03-21 12:21:48 +0100 25)  * @author David A. Velasco
69d6d821 (David A. Velasco 2013-03-21 12:21:48 +0100 26)  */
69d6d821 (David A. Velasco 2013-03-21 12:21:48 +0100 27) 
69d6d821 (David A. Velasco 2013-03-21 12:21:48 +0100 28) public class OAuth2Constants {
69d6d821 (David A. Velasco 2013-03-21 12:21:48 +0100 29)     
69d6d821 (David A. Velasco 2013-03-21 12:21:48 +0100 30)     /// Parameters to send to the Authorization Endpoint
69d6d821 (David A. Velasco 2013-03-21 12:21:48 +0100 31)     public static final String KEY_RESPONSE_TYPE = "response_type";
69d6d821 (David A. Velasco 2013-03-21 12:21:48 +0100 32)     public static final String KEY_REDIRECT_URI = "redirect_uri";
69d6d821 (David A. Velasco 2013-03-21 12:21:48 +0100 33)     public static final String KEY_CLIENT_ID = "client_id";
69d6d821 (David A. Velasco 2013-03-21 12:21:48 +0100 34)     public static final String KEY_SCOPE = "scope";
69d6d821 (David A. Velasco 2013-03-21 12:21:48 +0100 35)     public static final String KEY_STATE = "state"; 
69d6d821 (David A. Velasco 2013-03-21 12:21:48 +0100 36)     
69d6d821 (David A. Velasco 2013-03-21 12:21:48 +0100 37)     /// Additional parameters to send to the Token Endpoint
69d6d821 (David A. Velasco 2013-03-21 12:21:48 +0100 38)     public static final String KEY_GRANT_TYPE = "grant_type";
69d6d821 (David A. Velasco 2013-03-21 12:21:48 +0100 39)     public static final String KEY_CODE = "code";
69d6d821 (David A. Velasco 2013-03-21 12:21:48 +0100 40)     
69d6d821 (David A. Velasco 2013-03-21 12:21:48 +0100 41)     /// Parameters received in an OK response from the Token Endpoint 
69d6d821 (David A. Velasco 2013-03-21 12:21:48 +0100 42)     public static final String KEY_ACCESS_TOKEN = "access_token";
69d6d821 (David A. Velasco 2013-03-21 12:21:48 +0100 43)     public static final String KEY_TOKEN_TYPE = "token_type";
69d6d821 (David A. Velasco 2013-03-21 12:21:48 +0100 44)     public static final String KEY_EXPIRES_IN = "expires_in";
69d6d821 (David A. Velasco 2013-03-21 12:21:48 +0100 45)     public static final String KEY_REFRESH_TOKEN = "refresh_token";
69d6d821 (David A. Velasco 2013-03-21 12:21:48 +0100 46)     
69d6d821 (David A. Velasco 2013-03-21 12:21:48 +0100 47)     /// Parameters in an ERROR response
69d6d821 (David A. Velasco 2013-03-21 12:21:48 +0100 48)     public static final String KEY_ERROR = "error";
69d6d821 (David A. Velasco 2013-03-21 12:21:48 +0100 49)     public static final String KEY_ERROR_DESCRIPTION = "error_description";
69d6d821 (David A. Velasco 2013-03-21 12:21:48 +0100 50)     public static final String KEY_ERROR_URI = "error_uri";
69d6d821 (David A. Velasco 2013-03-21 12:21:48 +0100 51)     public static final String VALUE_ERROR_ACCESS_DENIED = "access_denied";
69d6d821 (David A. Velasco 2013-03-21 12:21:48 +0100 52)     
69d6d821 (David A. Velasco 2013-03-21 12:21:48 +0100 53) }
