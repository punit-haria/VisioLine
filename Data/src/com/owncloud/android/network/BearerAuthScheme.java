11b88e0f (David A. Velasco 2012-12-19 14:12:53 +0100   1) /* ownCloud Android client application
11b88e0f (David A. Velasco 2012-12-19 14:12:53 +0100   2)  *   Copyright (C) 2012  ownCloud Inc.
11b88e0f (David A. Velasco 2012-12-19 14:12:53 +0100   3)  *
11b88e0f (David A. Velasco 2012-12-19 14:12:53 +0100   4)  *   This program is free software: you can redistribute it and/or modify
2946d8dd (David A. Velasco 2013-04-25 19:39:22 +0200   5)  *   it under the terms of the GNU General Public License version 2,
2946d8dd (David A. Velasco 2013-04-25 19:39:22 +0200   6)  *   as published by the Free Software Foundation.
11b88e0f (David A. Velasco 2012-12-19 14:12:53 +0100   7)  *
11b88e0f (David A. Velasco 2012-12-19 14:12:53 +0100   8)  *   This program is distributed in the hope that it will be useful,
11b88e0f (David A. Velasco 2012-12-19 14:12:53 +0100   9)  *   but WITHOUT ANY WARRANTY; without even the implied warranty of
11b88e0f (David A. Velasco 2012-12-19 14:12:53 +0100  10)  *   MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
11b88e0f (David A. Velasco 2012-12-19 14:12:53 +0100  11)  *   GNU General Public License for more details.
11b88e0f (David A. Velasco 2012-12-19 14:12:53 +0100  12)  *
11b88e0f (David A. Velasco 2012-12-19 14:12:53 +0100  13)  *   You should have received a copy of the GNU General Public License
11b88e0f (David A. Velasco 2012-12-19 14:12:53 +0100  14)  *   along with this program.  If not, see <http://www.gnu.org/licenses/>.
11b88e0f (David A. Velasco 2012-12-19 14:12:53 +0100  15)  *
11b88e0f (David A. Velasco 2012-12-19 14:12:53 +0100  16)  */
11b88e0f (David A. Velasco 2012-12-19 14:12:53 +0100  17) 
11b88e0f (David A. Velasco 2012-12-19 14:12:53 +0100  18) package com.owncloud.android.network;
11b88e0f (David A. Velasco 2012-12-19 14:12:53 +0100  19) 
11b88e0f (David A. Velasco 2012-12-19 14:12:53 +0100  20) import java.util.Map;
11b88e0f (David A. Velasco 2012-12-19 14:12:53 +0100  21) 
11b88e0f (David A. Velasco 2012-12-19 14:12:53 +0100  22) import org.apache.commons.httpclient.Credentials;
11b88e0f (David A. Velasco 2012-12-19 14:12:53 +0100  23) import org.apache.commons.httpclient.HttpMethod;
11b88e0f (David A. Velasco 2012-12-19 14:12:53 +0100  24) import org.apache.commons.httpclient.auth.AuthChallengeParser;
11b88e0f (David A. Velasco 2012-12-19 14:12:53 +0100  25) import org.apache.commons.httpclient.auth.AuthScheme;
11b88e0f (David A. Velasco 2012-12-19 14:12:53 +0100  26) import org.apache.commons.httpclient.auth.AuthenticationException;
11b88e0f (David A. Velasco 2012-12-19 14:12:53 +0100  27) import org.apache.commons.httpclient.auth.InvalidCredentialsException;
11b88e0f (David A. Velasco 2012-12-19 14:12:53 +0100  28) import org.apache.commons.httpclient.auth.MalformedChallengeException;
11b88e0f (David A. Velasco 2012-12-19 14:12:53 +0100  29) 
2946d8dd (David A. Velasco 2013-04-25 19:39:22 +0200  30) import com.owncloud.android.Log_OC;
11b88e0f (David A. Velasco 2012-12-19 14:12:53 +0100  31) 
11b88e0f (David A. Velasco 2012-12-19 14:12:53 +0100  32) /**
11b88e0f (David A. Velasco 2012-12-19 14:12:53 +0100  33)  * Bearer authentication scheme as defined in RFC 6750.
11b88e0f (David A. Velasco 2012-12-19 14:12:53 +0100  34)  * 
11b88e0f (David A. Velasco 2012-12-19 14:12:53 +0100  35)  * @author David A. Velasco
11b88e0f (David A. Velasco 2012-12-19 14:12:53 +0100  36)  */
11b88e0f (David A. Velasco 2012-12-19 14:12:53 +0100  37) 
11b88e0f (David A. Velasco 2012-12-19 14:12:53 +0100  38) public class BearerAuthScheme implements AuthScheme /*extends RFC2617Scheme*/ {
11b88e0f (David A. Velasco 2012-12-19 14:12:53 +0100  39)     
11b88e0f (David A. Velasco 2012-12-19 14:12:53 +0100  40)     private static final String TAG = BearerAuthScheme.class.getSimpleName();
11b88e0f (David A. Velasco 2012-12-19 14:12:53 +0100  41) 
11b88e0f (David A. Velasco 2012-12-19 14:12:53 +0100  42)     public static final String AUTH_POLICY = "Bearer";
11b88e0f (David A. Velasco 2012-12-19 14:12:53 +0100  43)     
11b88e0f (David A. Velasco 2012-12-19 14:12:53 +0100  44)     /** Whether the bearer authentication process is complete */
11b88e0f (David A. Velasco 2012-12-19 14:12:53 +0100  45)     private boolean mComplete;
11b88e0f (David A. Velasco 2012-12-19 14:12:53 +0100  46)     
11b88e0f (David A. Velasco 2012-12-19 14:12:53 +0100  47)     /** Authentication parameter map */
11b88e0f (David A. Velasco 2012-12-19 14:12:53 +0100  48)     private Map mParams = null;
11b88e0f (David A. Velasco 2012-12-19 14:12:53 +0100  49)     
11b88e0f (David A. Velasco 2012-12-19 14:12:53 +0100  50)     
11b88e0f (David A. Velasco 2012-12-19 14:12:53 +0100  51)     /**
11b88e0f (David A. Velasco 2012-12-19 14:12:53 +0100  52)      * Default constructor for the bearer authentication scheme.
11b88e0f (David A. Velasco 2012-12-19 14:12:53 +0100  53)      */
11b88e0f (David A. Velasco 2012-12-19 14:12:53 +0100  54)     public BearerAuthScheme() {
11b88e0f (David A. Velasco 2012-12-19 14:12:53 +0100  55)         mComplete = false;
11b88e0f (David A. Velasco 2012-12-19 14:12:53 +0100  56)     }
11b88e0f (David A. Velasco 2012-12-19 14:12:53 +0100  57) 
11b88e0f (David A. Velasco 2012-12-19 14:12:53 +0100  58)     /**
11b88e0f (David A. Velasco 2012-12-19 14:12:53 +0100  59)      * Constructor for the basic authentication scheme.
11b88e0f (David A. Velasco 2012-12-19 14:12:53 +0100  60)      * 
11b88e0f (David A. Velasco 2012-12-19 14:12:53 +0100  61)      * @param   challenge                       Authentication challenge
11b88e0f (David A. Velasco 2012-12-19 14:12:53 +0100  62)      * 
11b88e0f (David A. Velasco 2012-12-19 14:12:53 +0100  63)      * @throws  MalformedChallengeException     Thrown if the authentication challenge is malformed
11b88e0f (David A. Velasco 2012-12-19 14:12:53 +0100  64)      * 
11b88e0f (David A. Velasco 2012-12-19 14:12:53 +0100  65)      * @deprecated Use parameterless constructor and {@link AuthScheme#processChallenge(String)} method
11b88e0f (David A. Velasco 2012-12-19 14:12:53 +0100  66)      */
11b88e0f (David A. Velasco 2012-12-19 14:12:53 +0100  67)     public BearerAuthScheme(final String challenge) throws MalformedChallengeException {
11b88e0f (David A. Velasco 2012-12-19 14:12:53 +0100  68)         processChallenge(challenge);
11b88e0f (David A. Velasco 2012-12-19 14:12:53 +0100  69)         mComplete = true;
11b88e0f (David A. Velasco 2012-12-19 14:12:53 +0100  70)     }
11b88e0f (David A. Velasco 2012-12-19 14:12:53 +0100  71) 
11b88e0f (David A. Velasco 2012-12-19 14:12:53 +0100  72)     /**
11b88e0f (David A. Velasco 2012-12-19 14:12:53 +0100  73)      * Returns textual designation of the bearer authentication scheme.
11b88e0f (David A. Velasco 2012-12-19 14:12:53 +0100  74)      * 
11b88e0f (David A. Velasco 2012-12-19 14:12:53 +0100  75)      * @return "Bearer"
11b88e0f (David A. Velasco 2012-12-19 14:12:53 +0100  76)      */
11b88e0f (David A. Velasco 2012-12-19 14:12:53 +0100  77)     public String getSchemeName() {
11b88e0f (David A. Velasco 2012-12-19 14:12:53 +0100  78)         return "bearer";
11b88e0f (David A. Velasco 2012-12-19 14:12:53 +0100  79)     }
11b88e0f (David A. Velasco 2012-12-19 14:12:53 +0100  80) 
11b88e0f (David A. Velasco 2012-12-19 14:12:53 +0100  81)     /**
11b88e0f (David A. Velasco 2012-12-19 14:12:53 +0100  82)      * Processes the Bearer challenge.
11b88e0f (David A. Velasco 2012-12-19 14:12:53 +0100  83)      *  
11b88e0f (David A. Velasco 2012-12-19 14:12:53 +0100  84)      * @param   challenge                   The challenge string
11b88e0f (David A. Velasco 2012-12-19 14:12:53 +0100  85)      * 
11b88e0f (David A. Velasco 2012-12-19 14:12:53 +0100  86)      * @throws MalformedChallengeException  Thrown if the authentication challenge is malformed
11b88e0f (David A. Velasco 2012-12-19 14:12:53 +0100  87)      */
11b88e0f (David A. Velasco 2012-12-19 14:12:53 +0100  88)     public void processChallenge(String challenge) throws MalformedChallengeException {
11b88e0f (David A. Velasco 2012-12-19 14:12:53 +0100  89)         String s = AuthChallengeParser.extractScheme(challenge);
11b88e0f (David A. Velasco 2012-12-19 14:12:53 +0100  90)         if (!s.equalsIgnoreCase(getSchemeName())) {
11b88e0f (David A. Velasco 2012-12-19 14:12:53 +0100  91)             throw new MalformedChallengeException(
11b88e0f (David A. Velasco 2012-12-19 14:12:53 +0100  92)               "Invalid " + getSchemeName() + " challenge: " + challenge); 
11b88e0f (David A. Velasco 2012-12-19 14:12:53 +0100  93)         }
11b88e0f (David A. Velasco 2012-12-19 14:12:53 +0100  94)         mParams = AuthChallengeParser.extractParams(challenge);
11b88e0f (David A. Velasco 2012-12-19 14:12:53 +0100  95)         mComplete = true;
11b88e0f (David A. Velasco 2012-12-19 14:12:53 +0100  96)     }
11b88e0f (David A. Velasco 2012-12-19 14:12:53 +0100  97) 
11b88e0f (David A. Velasco 2012-12-19 14:12:53 +0100  98)     /**
11b88e0f (David A. Velasco 2012-12-19 14:12:53 +0100  99)      * Tests if the Bearer authentication process has been completed.
11b88e0f (David A. Velasco 2012-12-19 14:12:53 +0100 100)      * 
11b88e0f (David A. Velasco 2012-12-19 14:12:53 +0100 101)      * @return 'true' if Bearer authorization has been processed, 'false' otherwise.
11b88e0f (David A. Velasco 2012-12-19 14:12:53 +0100 102)      */
11b88e0f (David A. Velasco 2012-12-19 14:12:53 +0100 103)     public boolean isComplete() {
11b88e0f (David A. Velasco 2012-12-19 14:12:53 +0100 104)         return this.mComplete;
11b88e0f (David A. Velasco 2012-12-19 14:12:53 +0100 105)     }
11b88e0f (David A. Velasco 2012-12-19 14:12:53 +0100 106) 
11b88e0f (David A. Velasco 2012-12-19 14:12:53 +0100 107)     /**
11b88e0f (David A. Velasco 2012-12-19 14:12:53 +0100 108)      * Produces bearer authorization string for the given set of 
11b88e0f (David A. Velasco 2012-12-19 14:12:53 +0100 109)      * {@link Credentials}.
11b88e0f (David A. Velasco 2012-12-19 14:12:53 +0100 110)      * 
11b88e0f (David A. Velasco 2012-12-19 14:12:53 +0100 111)      * @param   credentials                     The set of credentials to be used for authentication
11b88e0f (David A. Velasco 2012-12-19 14:12:53 +0100 112)      * @param   method                          Method name is ignored by the bearer authentication scheme
11b88e0f (David A. Velasco 2012-12-19 14:12:53 +0100 113)      * @param   uri                             URI is ignored by the bearer authentication scheme
11b88e0f (David A. Velasco 2012-12-19 14:12:53 +0100 114)      * @throws  InvalidCredentialsException     If authentication credentials are not valid or not applicable 
11b88e0f (David A. Velasco 2012-12-19 14:12:53 +0100 115)      *                                          for this authentication scheme
11b88e0f (David A. Velasco 2012-12-19 14:12:53 +0100 116)      * @throws  AuthenticationException         If authorization string cannot be generated due to an authentication failure
11b88e0f (David A. Velasco 2012-12-19 14:12:53 +0100 117)      * @return  A bearer authorization string
11b88e0f (David A. Velasco 2012-12-19 14:12:53 +0100 118)      * 
11b88e0f (David A. Velasco 2012-12-19 14:12:53 +0100 119)      * @deprecated Use {@link #authenticate(Credentials, HttpMethod)}
11b88e0f (David A. Velasco 2012-12-19 14:12:53 +0100 120)      */
11b88e0f (David A. Velasco 2012-12-19 14:12:53 +0100 121)     public String authenticate(Credentials credentials, String method, String uri) throws AuthenticationException {
2946d8dd (David A. Velasco 2013-04-25 19:39:22 +0200 122)         Log_OC.d(TAG, "enter BearerScheme.authenticate(Credentials, String, String)");
11b88e0f (David A. Velasco 2012-12-19 14:12:53 +0100 123) 
11b88e0f (David A. Velasco 2012-12-19 14:12:53 +0100 124)         BearerCredentials bearer = null;
11b88e0f (David A. Velasco 2012-12-19 14:12:53 +0100 125)         try {
11b88e0f (David A. Velasco 2012-12-19 14:12:53 +0100 126)             bearer = (BearerCredentials) credentials;
11b88e0f (David A. Velasco 2012-12-19 14:12:53 +0100 127)         } catch (ClassCastException e) {
11b88e0f (David A. Velasco 2012-12-19 14:12:53 +0100 128)             throw new InvalidCredentialsException(
11b88e0f (David A. Velasco 2012-12-19 14:12:53 +0100 129)              "Credentials cannot be used for bearer authentication: " 
11b88e0f (David A. Velasco 2012-12-19 14:12:53 +0100 130)               + credentials.getClass().getName());
11b88e0f (David A. Velasco 2012-12-19 14:12:53 +0100 131)         }
11b88e0f (David A. Velasco 2012-12-19 14:12:53 +0100 132)         return BearerAuthScheme.authenticate(bearer);
11b88e0f (David A. Velasco 2012-12-19 14:12:53 +0100 133)     }
11b88e0f (David A. Velasco 2012-12-19 14:12:53 +0100 134) 
11b88e0f (David A. Velasco 2012-12-19 14:12:53 +0100 135)     
11b88e0f (David A. Velasco 2012-12-19 14:12:53 +0100 136)     /**
11b88e0f (David A. Velasco 2012-12-19 14:12:53 +0100 137)      * Returns 'false'. Bearer authentication scheme is request based.
11b88e0f (David A. Velasco 2012-12-19 14:12:53 +0100 138)      * 
11b88e0f (David A. Velasco 2012-12-19 14:12:53 +0100 139)      * @return 'false'.
11b88e0f (David A. Velasco 2012-12-19 14:12:53 +0100 140)      */
11b88e0f (David A. Velasco 2012-12-19 14:12:53 +0100 141)     public boolean isConnectionBased() {
11b88e0f (David A. Velasco 2012-12-19 14:12:53 +0100 142)         return false;    
11b88e0f (David A. Velasco 2012-12-19 14:12:53 +0100 143)     }
11b88e0f (David A. Velasco 2012-12-19 14:12:53 +0100 144) 
11b88e0f (David A. Velasco 2012-12-19 14:12:53 +0100 145)     /**
11b88e0f (David A. Velasco 2012-12-19 14:12:53 +0100 146)      * Produces bearer authorization string for the given set of {@link Credentials}.
11b88e0f (David A. Velasco 2012-12-19 14:12:53 +0100 147)      * 
11b88e0f (David A. Velasco 2012-12-19 14:12:53 +0100 148)      * @param   credentials                     The set of credentials to be used for authentication
11b88e0f (David A. Velasco 2012-12-19 14:12:53 +0100 149)      * @param   method                          The method being authenticated
11b88e0f (David A. Velasco 2012-12-19 14:12:53 +0100 150)      * @throws  InvalidCredentialsException     If authentication credentials are not valid or not applicable for this authentication 
11b88e0f (David A. Velasco 2012-12-19 14:12:53 +0100 151)      *                                          scheme.
11b88e0f (David A. Velasco 2012-12-19 14:12:53 +0100 152)      * @throws AuthenticationException         If authorization string cannot be generated due to an authentication failure.
11b88e0f (David A. Velasco 2012-12-19 14:12:53 +0100 153)      * 
11b88e0f (David A. Velasco 2012-12-19 14:12:53 +0100 154)      * @return a basic authorization string
11b88e0f (David A. Velasco 2012-12-19 14:12:53 +0100 155)      */
11b88e0f (David A. Velasco 2012-12-19 14:12:53 +0100 156)     public String authenticate(Credentials credentials, HttpMethod method) throws AuthenticationException {
2946d8dd (David A. Velasco 2013-04-25 19:39:22 +0200 157)         Log_OC.d(TAG, "enter BearerScheme.authenticate(Credentials, HttpMethod)");
11b88e0f (David A. Velasco 2012-12-19 14:12:53 +0100 158) 
11b88e0f (David A. Velasco 2012-12-19 14:12:53 +0100 159)         if (method == null) {
11b88e0f (David A. Velasco 2012-12-19 14:12:53 +0100 160)             throw new IllegalArgumentException("Method may not be null");
11b88e0f (David A. Velasco 2012-12-19 14:12:53 +0100 161)         }
11b88e0f (David A. Velasco 2012-12-19 14:12:53 +0100 162)         BearerCredentials bearer = null;
11b88e0f (David A. Velasco 2012-12-19 14:12:53 +0100 163)         try {
11b88e0f (David A. Velasco 2012-12-19 14:12:53 +0100 164)             bearer = (BearerCredentials) credentials;
11b88e0f (David A. Velasco 2012-12-19 14:12:53 +0100 165)         } catch (ClassCastException e) {
11b88e0f (David A. Velasco 2012-12-19 14:12:53 +0100 166)             throw new InvalidCredentialsException(
11b88e0f (David A. Velasco 2012-12-19 14:12:53 +0100 167)                     "Credentials cannot be used for bearer authentication: " 
11b88e0f (David A. Velasco 2012-12-19 14:12:53 +0100 168)                     + credentials.getClass().getName());
11b88e0f (David A. Velasco 2012-12-19 14:12:53 +0100 169)         }
11b88e0f (David A. Velasco 2012-12-19 14:12:53 +0100 170)         return BearerAuthScheme.authenticate(
11b88e0f (David A. Velasco 2012-12-19 14:12:53 +0100 171)             bearer, 
11b88e0f (David A. Velasco 2012-12-19 14:12:53 +0100 172)             method.getParams().getCredentialCharset());
11b88e0f (David A. Velasco 2012-12-19 14:12:53 +0100 173)     }
11b88e0f (David A. Velasco 2012-12-19 14:12:53 +0100 174)     
11b88e0f (David A. Velasco 2012-12-19 14:12:53 +0100 175)     /**
11b88e0f (David A. Velasco 2012-12-19 14:12:53 +0100 176)      * @deprecated Use {@link #authenticate(BearerCredentials, String)}
11b88e0f (David A. Velasco 2012-12-19 14:12:53 +0100 177)      * 
11b88e0f (David A. Velasco 2012-12-19 14:12:53 +0100 178)      * Returns a bearer Authorization header value for the given 
11b88e0f (David A. Velasco 2012-12-19 14:12:53 +0100 179)      * {@link BearerCredentials}.
11b88e0f (David A. Velasco 2012-12-19 14:12:53 +0100 180)      * 
11b88e0f (David A. Velasco 2012-12-19 14:12:53 +0100 181)      * @param   credentials     The credentials to encode.
11b88e0f (David A. Velasco 2012-12-19 14:12:53 +0100 182)      * 
11b88e0f (David A. Velasco 2012-12-19 14:12:53 +0100 183)      * @return                  A bearer authorization string
11b88e0f (David A. Velasco 2012-12-19 14:12:53 +0100 184)      */
11b88e0f (David A. Velasco 2012-12-19 14:12:53 +0100 185)     public static String authenticate(BearerCredentials credentials) {
11b88e0f (David A. Velasco 2012-12-19 14:12:53 +0100 186)         return authenticate(credentials, "ISO-8859-1");
11b88e0f (David A. Velasco 2012-12-19 14:12:53 +0100 187)     }
11b88e0f (David A. Velasco 2012-12-19 14:12:53 +0100 188) 
11b88e0f (David A. Velasco 2012-12-19 14:12:53 +0100 189)     /**
11b88e0f (David A. Velasco 2012-12-19 14:12:53 +0100 190)      * Returns a bearer Authorization header value for the given 
11b88e0f (David A. Velasco 2012-12-19 14:12:53 +0100 191)      * {@link BearerCredentials} and charset.
11b88e0f (David A. Velasco 2012-12-19 14:12:53 +0100 192)      * 
11b88e0f (David A. Velasco 2012-12-19 14:12:53 +0100 193)      * @param   credentials         The credentials to encode.
11b88e0f (David A. Velasco 2012-12-19 14:12:53 +0100 194)      * @param   charset             The charset to use for encoding the credentials
11b88e0f (David A. Velasco 2012-12-19 14:12:53 +0100 195)      * 
11b88e0f (David A. Velasco 2012-12-19 14:12:53 +0100 196)      * @return                      A bearer authorization string
11b88e0f (David A. Velasco 2012-12-19 14:12:53 +0100 197)      * 
11b88e0f (David A. Velasco 2012-12-19 14:12:53 +0100 198)      * @since 3.0
11b88e0f (David A. Velasco 2012-12-19 14:12:53 +0100 199)      */
11b88e0f (David A. Velasco 2012-12-19 14:12:53 +0100 200)     public static String authenticate(BearerCredentials credentials, String charset) {
2946d8dd (David A. Velasco 2013-04-25 19:39:22 +0200 201)         Log_OC.d(TAG, "enter BearerAuthScheme.authenticate(BearerCredentials, String)");
11b88e0f (David A. Velasco 2012-12-19 14:12:53 +0100 202) 
11b88e0f (David A. Velasco 2012-12-19 14:12:53 +0100 203)         if (credentials == null) {
11b88e0f (David A. Velasco 2012-12-19 14:12:53 +0100 204)             throw new IllegalArgumentException("Credentials may not be null"); 
11b88e0f (David A. Velasco 2012-12-19 14:12:53 +0100 205)         }
11b88e0f (David A. Velasco 2012-12-19 14:12:53 +0100 206)         if (charset == null || charset.length() == 0) {
11b88e0f (David A. Velasco 2012-12-19 14:12:53 +0100 207)             throw new IllegalArgumentException("charset may not be null or empty");
11b88e0f (David A. Velasco 2012-12-19 14:12:53 +0100 208)         }
11b88e0f (David A. Velasco 2012-12-19 14:12:53 +0100 209)         StringBuffer buffer = new StringBuffer();
11b88e0f (David A. Velasco 2012-12-19 14:12:53 +0100 210)         buffer.append(credentials.getAccessToken());
11b88e0f (David A. Velasco 2012-12-19 14:12:53 +0100 211)         
11b88e0f (David A. Velasco 2012-12-19 14:12:53 +0100 212)         //return "Bearer " + EncodingUtil.getAsciiString(EncodingUtil.getBytes(buffer.toString(), charset));
11b88e0f (David A. Velasco 2012-12-19 14:12:53 +0100 213)         return "Bearer " + buffer.toString();
11b88e0f (David A. Velasco 2012-12-19 14:12:53 +0100 214)     }
11b88e0f (David A. Velasco 2012-12-19 14:12:53 +0100 215) 
11b88e0f (David A. Velasco 2012-12-19 14:12:53 +0100 216)     /**
11b88e0f (David A. Velasco 2012-12-19 14:12:53 +0100 217)      * Returns a String identifying the authentication challenge.  This is
11b88e0f (David A. Velasco 2012-12-19 14:12:53 +0100 218)      * used, in combination with the host and port to determine if
11b88e0f (David A. Velasco 2012-12-19 14:12:53 +0100 219)      * authorization has already been attempted or not.  Schemes which
11b88e0f (David A. Velasco 2012-12-19 14:12:53 +0100 220)      * require multiple requests to complete the authentication should
11b88e0f (David A. Velasco 2012-12-19 14:12:53 +0100 221)      * return a different value for each stage in the request.
11b88e0f (David A. Velasco 2012-12-19 14:12:53 +0100 222)      * 
11b88e0f (David A. Velasco 2012-12-19 14:12:53 +0100 223)      * Additionally, the ID should take into account any changes to the
11b88e0f (David A. Velasco 2012-12-19 14:12:53 +0100 224)      * authentication challenge and return a different value when appropriate.
11b88e0f (David A. Velasco 2012-12-19 14:12:53 +0100 225)      * For example when the realm changes in basic authentication it should be
11b88e0f (David A. Velasco 2012-12-19 14:12:53 +0100 226)      * considered a different authentication attempt and a different value should
11b88e0f (David A. Velasco 2012-12-19 14:12:53 +0100 227)      * be returned.
11b88e0f (David A. Velasco 2012-12-19 14:12:53 +0100 228)      * 
11b88e0f (David A. Velasco 2012-12-19 14:12:53 +0100 229)      * This method simply returns the realm for the challenge.
11b88e0f (David A. Velasco 2012-12-19 14:12:53 +0100 230)      * 
11b88e0f (David A. Velasco 2012-12-19 14:12:53 +0100 231)      * @return String       a String identifying the authentication challenge.
11b88e0f (David A. Velasco 2012-12-19 14:12:53 +0100 232)      * 
11b88e0f (David A. Velasco 2012-12-19 14:12:53 +0100 233)      * @deprecated no longer used
11b88e0f (David A. Velasco 2012-12-19 14:12:53 +0100 234)      */
11b88e0f (David A. Velasco 2012-12-19 14:12:53 +0100 235)     @Override
11b88e0f (David A. Velasco 2012-12-19 14:12:53 +0100 236)     public String getID() {
11b88e0f (David A. Velasco 2012-12-19 14:12:53 +0100 237)         return getRealm();
11b88e0f (David A. Velasco 2012-12-19 14:12:53 +0100 238)     }
11b88e0f (David A. Velasco 2012-12-19 14:12:53 +0100 239) 
11b88e0f (David A. Velasco 2012-12-19 14:12:53 +0100 240)     /**
11b88e0f (David A. Velasco 2012-12-19 14:12:53 +0100 241)      * Returns authentication parameter with the given name, if available.
11b88e0f (David A. Velasco 2012-12-19 14:12:53 +0100 242)      * 
11b88e0f (David A. Velasco 2012-12-19 14:12:53 +0100 243)      * @param   name    The name of the parameter to be returned
11b88e0f (David A. Velasco 2012-12-19 14:12:53 +0100 244)      * 
11b88e0f (David A. Velasco 2012-12-19 14:12:53 +0100 245)      * @return          The parameter with the given name
11b88e0f (David A. Velasco 2012-12-19 14:12:53 +0100 246)      */
11b88e0f (David A. Velasco 2012-12-19 14:12:53 +0100 247)     @Override
11b88e0f (David A. Velasco 2012-12-19 14:12:53 +0100 248)     public String getParameter(String name) {
11b88e0f (David A. Velasco 2012-12-19 14:12:53 +0100 249)         if (name == null) {
11b88e0f (David A. Velasco 2012-12-19 14:12:53 +0100 250)             throw new IllegalArgumentException("Parameter name may not be null"); 
11b88e0f (David A. Velasco 2012-12-19 14:12:53 +0100 251)         }
11b88e0f (David A. Velasco 2012-12-19 14:12:53 +0100 252)         if (mParams == null) {
11b88e0f (David A. Velasco 2012-12-19 14:12:53 +0100 253)             return null;
11b88e0f (David A. Velasco 2012-12-19 14:12:53 +0100 254)         }
11b88e0f (David A. Velasco 2012-12-19 14:12:53 +0100 255)         return (String) mParams.get(name.toLowerCase());
11b88e0f (David A. Velasco 2012-12-19 14:12:53 +0100 256)     }
11b88e0f (David A. Velasco 2012-12-19 14:12:53 +0100 257) 
11b88e0f (David A. Velasco 2012-12-19 14:12:53 +0100 258)     /**
11b88e0f (David A. Velasco 2012-12-19 14:12:53 +0100 259)      * Returns authentication realm. The realm may not be null.
11b88e0f (David A. Velasco 2012-12-19 14:12:53 +0100 260)      * 
11b88e0f (David A. Velasco 2012-12-19 14:12:53 +0100 261)      * @return  The authentication realm
11b88e0f (David A. Velasco 2012-12-19 14:12:53 +0100 262)      */
11b88e0f (David A. Velasco 2012-12-19 14:12:53 +0100 263)     @Override
11b88e0f (David A. Velasco 2012-12-19 14:12:53 +0100 264)     public String getRealm() {
11b88e0f (David A. Velasco 2012-12-19 14:12:53 +0100 265)         return getParameter("realm");
11b88e0f (David A. Velasco 2012-12-19 14:12:53 +0100 266)     }
11b88e0f (David A. Velasco 2012-12-19 14:12:53 +0100 267)     
11b88e0f (David A. Velasco 2012-12-19 14:12:53 +0100 268) }
