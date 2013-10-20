48f13c8a (David A. Velasco 2012-09-05 13:46:30 +0200   1) /* ownCloud Android client application
bb257ec7 (David A. Velasco 2013-02-07 18:45:10 +0100   2)  *   Copyright (C) 2012-2013 ownCloud Inc.
48f13c8a (David A. Velasco 2012-09-05 13:46:30 +0200   3)  *
48f13c8a (David A. Velasco 2012-09-05 13:46:30 +0200   4)  *   This program is free software: you can redistribute it and/or modify
bdc0332c (David A. Velasco 2013-04-17 12:26:13 +0200   5)  *   it under the terms of the GNU General Public License version 2,
bdc0332c (David A. Velasco 2013-04-17 12:26:13 +0200   6)  *   as published by the Free Software Foundation.
48f13c8a (David A. Velasco 2012-09-05 13:46:30 +0200   7)  *
48f13c8a (David A. Velasco 2012-09-05 13:46:30 +0200   8)  *   This program is distributed in the hope that it will be useful,
48f13c8a (David A. Velasco 2012-09-05 13:46:30 +0200   9)  *   but WITHOUT ANY WARRANTY; without even the implied warranty of
48f13c8a (David A. Velasco 2012-09-05 13:46:30 +0200  10)  *   MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
48f13c8a (David A. Velasco 2012-09-05 13:46:30 +0200  11)  *   GNU General Public License for more details.
48f13c8a (David A. Velasco 2012-09-05 13:46:30 +0200  12)  *
48f13c8a (David A. Velasco 2012-09-05 13:46:30 +0200  13)  *   You should have received a copy of the GNU General Public License
48f13c8a (David A. Velasco 2012-09-05 13:46:30 +0200  14)  *   along with this program.  If not, see <http://www.gnu.org/licenses/>.
48f13c8a (David A. Velasco 2012-09-05 13:46:30 +0200  15)  *
48f13c8a (David A. Velasco 2012-09-05 13:46:30 +0200  16)  */
48f13c8a (David A. Velasco 2012-09-05 13:46:30 +0200  17) 
48f13c8a (David A. Velasco 2012-09-05 13:46:30 +0200  18) package com.owncloud.android.network;
48f13c8a (David A. Velasco 2012-09-05 13:46:30 +0200  19) 
48f13c8a (David A. Velasco 2012-09-05 13:46:30 +0200  20) import java.security.KeyStore;
48f13c8a (David A. Velasco 2012-09-05 13:46:30 +0200  21) import java.security.KeyStoreException;
48f13c8a (David A. Velasco 2012-09-05 13:46:30 +0200  22) import java.security.NoSuchAlgorithmException;
261aaf50 (David A. Velasco 2012-09-07 14:11:08 +0200  23) import java.security.cert.CertPathValidatorException;
48f13c8a (David A. Velasco 2012-09-05 13:46:30 +0200  24) import java.security.cert.CertStoreException;
48f13c8a (David A. Velasco 2012-09-05 13:46:30 +0200  25) import java.security.cert.CertificateException;
261aaf50 (David A. Velasco 2012-09-07 14:11:08 +0200  26) import java.security.cert.CertificateExpiredException;
261aaf50 (David A. Velasco 2012-09-07 14:11:08 +0200  27) import java.security.cert.CertificateNotYetValidException;
48f13c8a (David A. Velasco 2012-09-05 13:46:30 +0200  28) import java.security.cert.X509Certificate;
48f13c8a (David A. Velasco 2012-09-05 13:46:30 +0200  29) 
48f13c8a (David A. Velasco 2012-09-05 13:46:30 +0200  30) import javax.net.ssl.TrustManager;
48f13c8a (David A. Velasco 2012-09-05 13:46:30 +0200  31) import javax.net.ssl.TrustManagerFactory;
48f13c8a (David A. Velasco 2012-09-05 13:46:30 +0200  32) import javax.net.ssl.X509TrustManager;
48f13c8a (David A. Velasco 2012-09-05 13:46:30 +0200  33) 
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100  34) import com.owncloud.android.Log_OC;
48f13c8a (David A. Velasco 2012-09-05 13:46:30 +0200  35) 
48f13c8a (David A. Velasco 2012-09-05 13:46:30 +0200  36) /**
48f13c8a (David A. Velasco 2012-09-05 13:46:30 +0200  37)  * @author David A. Velasco
48f13c8a (David A. Velasco 2012-09-05 13:46:30 +0200  38)  */
48f13c8a (David A. Velasco 2012-09-05 13:46:30 +0200  39) public class AdvancedX509TrustManager implements X509TrustManager {
48f13c8a (David A. Velasco 2012-09-05 13:46:30 +0200  40)     
48f13c8a (David A. Velasco 2012-09-05 13:46:30 +0200  41)     private static final String TAG = AdvancedX509TrustManager.class.getSimpleName();
48f13c8a (David A. Velasco 2012-09-05 13:46:30 +0200  42) 
48f13c8a (David A. Velasco 2012-09-05 13:46:30 +0200  43)     private X509TrustManager mStandardTrustManager = null;
48f13c8a (David A. Velasco 2012-09-05 13:46:30 +0200  44)     private KeyStore mKnownServersKeyStore;
48f13c8a (David A. Velasco 2012-09-05 13:46:30 +0200  45) 
48f13c8a (David A. Velasco 2012-09-05 13:46:30 +0200  46)     /**
48f13c8a (David A. Velasco 2012-09-05 13:46:30 +0200  47)      * Constructor for AdvancedX509TrustManager
48f13c8a (David A. Velasco 2012-09-05 13:46:30 +0200  48)      * 
48f13c8a (David A. Velasco 2012-09-05 13:46:30 +0200  49)      * @param  knownServersCertStore    Local certificates store with server certificates explicitly trusted by the user.
48f13c8a (David A. Velasco 2012-09-05 13:46:30 +0200  50)      * @throws CertStoreException       When no default X509TrustManager instance was found in the system.
48f13c8a (David A. Velasco 2012-09-05 13:46:30 +0200  51)      */
48f13c8a (David A. Velasco 2012-09-05 13:46:30 +0200  52)     public AdvancedX509TrustManager(KeyStore knownServersKeyStore)
48f13c8a (David A. Velasco 2012-09-05 13:46:30 +0200  53)             throws NoSuchAlgorithmException, KeyStoreException, CertStoreException {
48f13c8a (David A. Velasco 2012-09-05 13:46:30 +0200  54)         super();
48f13c8a (David A. Velasco 2012-09-05 13:46:30 +0200  55)         TrustManagerFactory factory = TrustManagerFactory
48f13c8a (David A. Velasco 2012-09-05 13:46:30 +0200  56)                 .getInstance(TrustManagerFactory.getDefaultAlgorithm());
48f13c8a (David A. Velasco 2012-09-05 13:46:30 +0200  57)         factory.init((KeyStore)null);
48f13c8a (David A. Velasco 2012-09-05 13:46:30 +0200  58)         mStandardTrustManager = findX509TrustManager(factory);
48f13c8a (David A. Velasco 2012-09-05 13:46:30 +0200  59) 
48f13c8a (David A. Velasco 2012-09-05 13:46:30 +0200  60)         mKnownServersKeyStore = knownServersKeyStore;
48f13c8a (David A. Velasco 2012-09-05 13:46:30 +0200  61)     }
48f13c8a (David A. Velasco 2012-09-05 13:46:30 +0200  62)     
48f13c8a (David A. Velasco 2012-09-05 13:46:30 +0200  63)     
48f13c8a (David A. Velasco 2012-09-05 13:46:30 +0200  64)     /**
48f13c8a (David A. Velasco 2012-09-05 13:46:30 +0200  65)      * Locates the first X509TrustManager provided by a given TrustManagerFactory
48f13c8a (David A. Velasco 2012-09-05 13:46:30 +0200  66)      * @param factory               TrustManagerFactory to inspect in the search for a X509TrustManager
48f13c8a (David A. Velasco 2012-09-05 13:46:30 +0200  67)      * @return                      The first X509TrustManager found in factory.
48f13c8a (David A. Velasco 2012-09-05 13:46:30 +0200  68)      * @throws CertStoreException   When no X509TrustManager instance was found in factory
48f13c8a (David A. Velasco 2012-09-05 13:46:30 +0200  69)      */
48f13c8a (David A. Velasco 2012-09-05 13:46:30 +0200  70)     private X509TrustManager findX509TrustManager(TrustManagerFactory factory) throws CertStoreException {
48f13c8a (David A. Velasco 2012-09-05 13:46:30 +0200  71)         TrustManager tms[] = factory.getTrustManagers();
48f13c8a (David A. Velasco 2012-09-05 13:46:30 +0200  72)         for (int i = 0; i < tms.length; i++) {
48f13c8a (David A. Velasco 2012-09-05 13:46:30 +0200  73)             if (tms[i] instanceof X509TrustManager) {
48f13c8a (David A. Velasco 2012-09-05 13:46:30 +0200  74)                 return (X509TrustManager) tms[i];
48f13c8a (David A. Velasco 2012-09-05 13:46:30 +0200  75)             }
48f13c8a (David A. Velasco 2012-09-05 13:46:30 +0200  76)         }
48f13c8a (David A. Velasco 2012-09-05 13:46:30 +0200  77)         return null;
48f13c8a (David A. Velasco 2012-09-05 13:46:30 +0200  78)     }
48f13c8a (David A. Velasco 2012-09-05 13:46:30 +0200  79)     
48f13c8a (David A. Velasco 2012-09-05 13:46:30 +0200  80) 
48f13c8a (David A. Velasco 2012-09-05 13:46:30 +0200  81)     /**
48f13c8a (David A. Velasco 2012-09-05 13:46:30 +0200  82)      * @see javax.net.ssl.X509TrustManager#checkClientTrusted(X509Certificate[],
48f13c8a (David A. Velasco 2012-09-05 13:46:30 +0200  83)      *      String authType)
48f13c8a (David A. Velasco 2012-09-05 13:46:30 +0200  84)      */
48f13c8a (David A. Velasco 2012-09-05 13:46:30 +0200  85)     public void checkClientTrusted(X509Certificate[] certificates, String authType) throws CertificateException {
48f13c8a (David A. Velasco 2012-09-05 13:46:30 +0200  86)         mStandardTrustManager.checkClientTrusted(certificates, authType);
48f13c8a (David A. Velasco 2012-09-05 13:46:30 +0200  87)     }
48f13c8a (David A. Velasco 2012-09-05 13:46:30 +0200  88) 
48f13c8a (David A. Velasco 2012-09-05 13:46:30 +0200  89)     
48f13c8a (David A. Velasco 2012-09-05 13:46:30 +0200  90)     /**
48f13c8a (David A. Velasco 2012-09-05 13:46:30 +0200  91)      * @see javax.net.ssl.X509TrustManager#checkServerTrusted(X509Certificate[],
48f13c8a (David A. Velasco 2012-09-05 13:46:30 +0200  92)      *      String authType)
48f13c8a (David A. Velasco 2012-09-05 13:46:30 +0200  93)      */
48f13c8a (David A. Velasco 2012-09-05 13:46:30 +0200  94)     public void checkServerTrusted(X509Certificate[] certificates, String authType) throws CertificateException {
48f13c8a (David A. Velasco 2012-09-05 13:46:30 +0200  95)         if (!isKnownServer(certificates[0])) {
261aaf50 (David A. Velasco 2012-09-07 14:11:08 +0200  96)         	CertificateCombinedException result = new CertificateCombinedException(certificates[0]);
261aaf50 (David A. Velasco 2012-09-07 14:11:08 +0200  97)         	try {
261aaf50 (David A. Velasco 2012-09-07 14:11:08 +0200  98)         		certificates[0].checkValidity();
261aaf50 (David A. Velasco 2012-09-07 14:11:08 +0200  99)         	} catch (CertificateExpiredException c) {
261aaf50 (David A. Velasco 2012-09-07 14:11:08 +0200 100)         		result.setCertificateExpiredException(c);
261aaf50 (David A. Velasco 2012-09-07 14:11:08 +0200 101)         		
261aaf50 (David A. Velasco 2012-09-07 14:11:08 +0200 102)         	} catch (CertificateNotYetValidException c) {
261aaf50 (David A. Velasco 2012-09-07 14:11:08 +0200 103)                 result.setCertificateNotYetException(c);
261aaf50 (David A. Velasco 2012-09-07 14:11:08 +0200 104)         	}
261aaf50 (David A. Velasco 2012-09-07 14:11:08 +0200 105)         	
261aaf50 (David A. Velasco 2012-09-07 14:11:08 +0200 106)         	try {
261aaf50 (David A. Velasco 2012-09-07 14:11:08 +0200 107)         	    mStandardTrustManager.checkServerTrusted(certificates, authType);
261aaf50 (David A. Velasco 2012-09-07 14:11:08 +0200 108)         	} catch (CertificateException c) {
261aaf50 (David A. Velasco 2012-09-07 14:11:08 +0200 109)                 Throwable cause = c.getCause();
261aaf50 (David A. Velasco 2012-09-07 14:11:08 +0200 110)                 Throwable previousCause = null;
261aaf50 (David A. Velasco 2012-09-07 14:11:08 +0200 111)                 while (cause != null && cause != previousCause && !(cause instanceof CertPathValidatorException)) {     // getCause() is not funny
261aaf50 (David A. Velasco 2012-09-07 14:11:08 +0200 112)                     previousCause = cause;
261aaf50 (David A. Velasco 2012-09-07 14:11:08 +0200 113)                     cause = cause.getCause();
261aaf50 (David A. Velasco 2012-09-07 14:11:08 +0200 114)                 }
261aaf50 (David A. Velasco 2012-09-07 14:11:08 +0200 115)                 if (cause != null && cause instanceof CertPathValidatorException) {
261aaf50 (David A. Velasco 2012-09-07 14:11:08 +0200 116)                 	result.setCertPathValidatorException((CertPathValidatorException)cause);
261aaf50 (David A. Velasco 2012-09-07 14:11:08 +0200 117)                 } else {
261aaf50 (David A. Velasco 2012-09-07 14:11:08 +0200 118)                 	result.setOtherCertificateException(c);
261aaf50 (David A. Velasco 2012-09-07 14:11:08 +0200 119)                 }
261aaf50 (David A. Velasco 2012-09-07 14:11:08 +0200 120)         	}
261aaf50 (David A. Velasco 2012-09-07 14:11:08 +0200 121)         	
261aaf50 (David A. Velasco 2012-09-07 14:11:08 +0200 122)         	if (result.isException())
261aaf50 (David A. Velasco 2012-09-07 14:11:08 +0200 123)         		throw result;
261aaf50 (David A. Velasco 2012-09-07 14:11:08 +0200 124) 
48f13c8a (David A. Velasco 2012-09-05 13:46:30 +0200 125)         }
48f13c8a (David A. Velasco 2012-09-05 13:46:30 +0200 126)     }
48f13c8a (David A. Velasco 2012-09-05 13:46:30 +0200 127)     
48f13c8a (David A. Velasco 2012-09-05 13:46:30 +0200 128)     
48f13c8a (David A. Velasco 2012-09-05 13:46:30 +0200 129)     /**
48f13c8a (David A. Velasco 2012-09-05 13:46:30 +0200 130)      * @see javax.net.ssl.X509TrustManager#getAcceptedIssuers()
48f13c8a (David A. Velasco 2012-09-05 13:46:30 +0200 131)      */
48f13c8a (David A. Velasco 2012-09-05 13:46:30 +0200 132)     public X509Certificate[] getAcceptedIssuers() {
48f13c8a (David A. Velasco 2012-09-05 13:46:30 +0200 133)         return mStandardTrustManager.getAcceptedIssuers();
48f13c8a (David A. Velasco 2012-09-05 13:46:30 +0200 134)     }
48f13c8a (David A. Velasco 2012-09-05 13:46:30 +0200 135) 
48f13c8a (David A. Velasco 2012-09-05 13:46:30 +0200 136)     
261aaf50 (David A. Velasco 2012-09-07 14:11:08 +0200 137)     public boolean isKnownServer(X509Certificate cert) {
48f13c8a (David A. Velasco 2012-09-05 13:46:30 +0200 138)         try {
48f13c8a (David A. Velasco 2012-09-05 13:46:30 +0200 139)             return (mKnownServersKeyStore.getCertificateAlias(cert) != null);
48f13c8a (David A. Velasco 2012-09-05 13:46:30 +0200 140)         } catch (KeyStoreException e) {
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100 141)             Log_OC.d(TAG, "Fail while checking certificate in the known-servers store");
48f13c8a (David A. Velasco 2012-09-05 13:46:30 +0200 142)             return false;
48f13c8a (David A. Velasco 2012-09-05 13:46:30 +0200 143)         }
48f13c8a (David A. Velasco 2012-09-05 13:46:30 +0200 144)     }
48f13c8a (David A. Velasco 2012-09-05 13:46:30 +0200 145)     
48f13c8a (David A. Velasco 2012-09-05 13:46:30 +0200 146) }
