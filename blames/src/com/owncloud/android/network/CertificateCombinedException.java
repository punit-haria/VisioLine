bf3cf8cd (David A. Velasco 2012-09-13 17:07:17 +0200   1) /* ownCloud Android client application
bb257ec7 (David A. Velasco 2013-02-07 18:45:10 +0100   2)  *   Copyright (C) 2012-2013 ownCloud Inc.
bf3cf8cd (David A. Velasco 2012-09-13 17:07:17 +0200   3)  *
bf3cf8cd (David A. Velasco 2012-09-13 17:07:17 +0200   4)  *   This program is free software: you can redistribute it and/or modify
bdc0332c (David A. Velasco 2013-04-17 12:26:13 +0200   5)  *   it under the terms of the GNU General Public License version 2,
bdc0332c (David A. Velasco 2013-04-17 12:26:13 +0200   6)  *   as published by the Free Software Foundation.
bf3cf8cd (David A. Velasco 2012-09-13 17:07:17 +0200   7)  *
bf3cf8cd (David A. Velasco 2012-09-13 17:07:17 +0200   8)  *   This program is distributed in the hope that it will be useful,
bf3cf8cd (David A. Velasco 2012-09-13 17:07:17 +0200   9)  *   but WITHOUT ANY WARRANTY; without even the implied warranty of
bf3cf8cd (David A. Velasco 2012-09-13 17:07:17 +0200  10)  *   MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
bf3cf8cd (David A. Velasco 2012-09-13 17:07:17 +0200  11)  *   GNU General Public License for more details.
bf3cf8cd (David A. Velasco 2012-09-13 17:07:17 +0200  12)  *
bf3cf8cd (David A. Velasco 2012-09-13 17:07:17 +0200  13)  *   You should have received a copy of the GNU General Public License
bf3cf8cd (David A. Velasco 2012-09-13 17:07:17 +0200  14)  *   along with this program.  If not, see <http://www.gnu.org/licenses/>.
bf3cf8cd (David A. Velasco 2012-09-13 17:07:17 +0200  15)  *
bf3cf8cd (David A. Velasco 2012-09-13 17:07:17 +0200  16)  */
bf3cf8cd (David A. Velasco 2012-09-13 17:07:17 +0200  17) 
261aaf50 (David A. Velasco 2012-09-07 14:11:08 +0200  18) package com.owncloud.android.network;
261aaf50 (David A. Velasco 2012-09-07 14:11:08 +0200  19) 
261aaf50 (David A. Velasco 2012-09-07 14:11:08 +0200  20) import java.security.cert.CertPathValidatorException;
261aaf50 (David A. Velasco 2012-09-07 14:11:08 +0200  21) import java.security.cert.CertificateException;
261aaf50 (David A. Velasco 2012-09-07 14:11:08 +0200  22) import java.security.cert.CertificateExpiredException;
261aaf50 (David A. Velasco 2012-09-07 14:11:08 +0200  23) import java.security.cert.CertificateNotYetValidException;
261aaf50 (David A. Velasco 2012-09-07 14:11:08 +0200  24) import java.security.cert.X509Certificate;
261aaf50 (David A. Velasco 2012-09-07 14:11:08 +0200  25) 
261aaf50 (David A. Velasco 2012-09-07 14:11:08 +0200  26) import javax.net.ssl.SSLPeerUnverifiedException;
261aaf50 (David A. Velasco 2012-09-07 14:11:08 +0200  27) 
bf3cf8cd (David A. Velasco 2012-09-13 17:07:17 +0200  28) /**
bf3cf8cd (David A. Velasco 2012-09-13 17:07:17 +0200  29)  * Exception joining all the problems that {@link AdvancedX509TrustManager} can find in
bf3cf8cd (David A. Velasco 2012-09-13 17:07:17 +0200  30)  * a certificate chain for a server.
bf3cf8cd (David A. Velasco 2012-09-13 17:07:17 +0200  31)  * 
bf3cf8cd (David A. Velasco 2012-09-13 17:07:17 +0200  32)  * This was initially created as an extension of CertificateException, but some
bf3cf8cd (David A. Velasco 2012-09-13 17:07:17 +0200  33)  * implementations of the SSL socket layer in existing devices are REPLACING the CertificateException
bf3cf8cd (David A. Velasco 2012-09-13 17:07:17 +0200  34)  * instances thrown by {@link javax.net.ssl.X509TrustManager#checkServerTrusted(X509Certificate[], String)}
bf3cf8cd (David A. Velasco 2012-09-13 17:07:17 +0200  35)  * with SSLPeerUnverifiedException FORGETTING THE CAUSING EXCEPTION instead of wrapping it. 
bf3cf8cd (David A. Velasco 2012-09-13 17:07:17 +0200  36)  * 
bf3cf8cd (David A. Velasco 2012-09-13 17:07:17 +0200  37)  * Due to this, extending RuntimeException is necessary to get that the CertificateCombinedException 
bf3cf8cd (David A. Velasco 2012-09-13 17:07:17 +0200  38)  * instance reaches {@link AdvancedSslSocketFactory#verifyPeerIdentity}.
bf3cf8cd (David A. Velasco 2012-09-13 17:07:17 +0200  39)  * 
bf3cf8cd (David A. Velasco 2012-09-13 17:07:17 +0200  40)  * BE CAREFUL. As a RuntimeException extensions, Java compilers do not require to handle it
bf3cf8cd (David A. Velasco 2012-09-13 17:07:17 +0200  41)  * in client methods. Be sure to use it only when you know exactly where it will go.
bf3cf8cd (David A. Velasco 2012-09-13 17:07:17 +0200  42)  * 
bf3cf8cd (David A. Velasco 2012-09-13 17:07:17 +0200  43)  * @author David A. Velasco
bf3cf8cd (David A. Velasco 2012-09-13 17:07:17 +0200  44)  */
bf3cf8cd (David A. Velasco 2012-09-13 17:07:17 +0200  45) public class CertificateCombinedException extends RuntimeException {
bf3cf8cd (David A. Velasco 2012-09-13 17:07:17 +0200  46) 
67eb9210 (David A. Velasco 2012-10-22 15:11:59 +0200  47)     /** Generated - to refresh every time the class changes */
bf3cf8cd (David A. Velasco 2012-09-13 17:07:17 +0200  48)     private static final long serialVersionUID = -8875782030758554999L;
bf3cf8cd (David A. Velasco 2012-09-13 17:07:17 +0200  49)     
261aaf50 (David A. Velasco 2012-09-07 14:11:08 +0200  50)     private X509Certificate mServerCert = null;
bf3cf8cd (David A. Velasco 2012-09-13 17:07:17 +0200  51)     private String mHostInUrl;
bf3cf8cd (David A. Velasco 2012-09-13 17:07:17 +0200  52) 
261aaf50 (David A. Velasco 2012-09-07 14:11:08 +0200  53)     private CertificateExpiredException mCertificateExpiredException = null;
261aaf50 (David A. Velasco 2012-09-07 14:11:08 +0200  54)     private CertificateNotYetValidException mCertificateNotYetValidException = null;
261aaf50 (David A. Velasco 2012-09-07 14:11:08 +0200  55)     private CertPathValidatorException mCertPathValidatorException = null;
261aaf50 (David A. Velasco 2012-09-07 14:11:08 +0200  56)     private CertificateException mOtherCertificateException = null;
261aaf50 (David A. Velasco 2012-09-07 14:11:08 +0200  57)     private SSLPeerUnverifiedException mSslPeerUnverifiedException = null;
261aaf50 (David A. Velasco 2012-09-07 14:11:08 +0200  58)     
261aaf50 (David A. Velasco 2012-09-07 14:11:08 +0200  59)     public CertificateCombinedException(X509Certificate x509Certificate) {
261aaf50 (David A. Velasco 2012-09-07 14:11:08 +0200  60)         mServerCert = x509Certificate;
261aaf50 (David A. Velasco 2012-09-07 14:11:08 +0200  61)     }
261aaf50 (David A. Velasco 2012-09-07 14:11:08 +0200  62) 
261aaf50 (David A. Velasco 2012-09-07 14:11:08 +0200  63)     public X509Certificate getServerCertificate() {
261aaf50 (David A. Velasco 2012-09-07 14:11:08 +0200  64)         return mServerCert;
261aaf50 (David A. Velasco 2012-09-07 14:11:08 +0200  65)     }
261aaf50 (David A. Velasco 2012-09-07 14:11:08 +0200  66) 
bf3cf8cd (David A. Velasco 2012-09-13 17:07:17 +0200  67)     public String getHostInUrl() {
bf3cf8cd (David A. Velasco 2012-09-13 17:07:17 +0200  68)         return mHostInUrl;
bf3cf8cd (David A. Velasco 2012-09-13 17:07:17 +0200  69)     }
bf3cf8cd (David A. Velasco 2012-09-13 17:07:17 +0200  70) 
bf3cf8cd (David A. Velasco 2012-09-13 17:07:17 +0200  71)     public void setHostInUrl(String host) {
bf3cf8cd (David A. Velasco 2012-09-13 17:07:17 +0200  72)         mHostInUrl = host;
bf3cf8cd (David A. Velasco 2012-09-13 17:07:17 +0200  73)     }
bf3cf8cd (David A. Velasco 2012-09-13 17:07:17 +0200  74) 
261aaf50 (David A. Velasco 2012-09-07 14:11:08 +0200  75)     public CertificateExpiredException getCertificateExpiredException() {
261aaf50 (David A. Velasco 2012-09-07 14:11:08 +0200  76)         return mCertificateExpiredException;
261aaf50 (David A. Velasco 2012-09-07 14:11:08 +0200  77)     }
261aaf50 (David A. Velasco 2012-09-07 14:11:08 +0200  78) 
261aaf50 (David A. Velasco 2012-09-07 14:11:08 +0200  79)     public void setCertificateExpiredException(CertificateExpiredException c) {
261aaf50 (David A. Velasco 2012-09-07 14:11:08 +0200  80)         mCertificateExpiredException  = c;
261aaf50 (David A. Velasco 2012-09-07 14:11:08 +0200  81)     }
261aaf50 (David A. Velasco 2012-09-07 14:11:08 +0200  82) 
261aaf50 (David A. Velasco 2012-09-07 14:11:08 +0200  83)     public CertificateNotYetValidException getCertificateNotYetValidException() {
261aaf50 (David A. Velasco 2012-09-07 14:11:08 +0200  84)         return mCertificateNotYetValidException;
261aaf50 (David A. Velasco 2012-09-07 14:11:08 +0200  85)     }
261aaf50 (David A. Velasco 2012-09-07 14:11:08 +0200  86) 
261aaf50 (David A. Velasco 2012-09-07 14:11:08 +0200  87)     public void setCertificateNotYetException(CertificateNotYetValidException c) {
261aaf50 (David A. Velasco 2012-09-07 14:11:08 +0200  88)         mCertificateNotYetValidException = c;
261aaf50 (David A. Velasco 2012-09-07 14:11:08 +0200  89)     }
261aaf50 (David A. Velasco 2012-09-07 14:11:08 +0200  90) 
261aaf50 (David A. Velasco 2012-09-07 14:11:08 +0200  91)     public CertPathValidatorException getCertPathValidatorException() {
261aaf50 (David A. Velasco 2012-09-07 14:11:08 +0200  92)         return mCertPathValidatorException;
261aaf50 (David A. Velasco 2012-09-07 14:11:08 +0200  93)     }
261aaf50 (David A. Velasco 2012-09-07 14:11:08 +0200  94) 
261aaf50 (David A. Velasco 2012-09-07 14:11:08 +0200  95)     public void setCertPathValidatorException(CertPathValidatorException c) {
261aaf50 (David A. Velasco 2012-09-07 14:11:08 +0200  96)         mCertPathValidatorException = c;
261aaf50 (David A. Velasco 2012-09-07 14:11:08 +0200  97)     }
261aaf50 (David A. Velasco 2012-09-07 14:11:08 +0200  98) 
261aaf50 (David A. Velasco 2012-09-07 14:11:08 +0200  99)     public CertificateException getOtherCertificateException() {
261aaf50 (David A. Velasco 2012-09-07 14:11:08 +0200 100)         return mOtherCertificateException;
261aaf50 (David A. Velasco 2012-09-07 14:11:08 +0200 101)     }
261aaf50 (David A. Velasco 2012-09-07 14:11:08 +0200 102) 
261aaf50 (David A. Velasco 2012-09-07 14:11:08 +0200 103)     public void setOtherCertificateException(CertificateException c) {
261aaf50 (David A. Velasco 2012-09-07 14:11:08 +0200 104)         mOtherCertificateException = c;
261aaf50 (David A. Velasco 2012-09-07 14:11:08 +0200 105)     }
261aaf50 (David A. Velasco 2012-09-07 14:11:08 +0200 106) 
261aaf50 (David A. Velasco 2012-09-07 14:11:08 +0200 107)     public SSLPeerUnverifiedException getSslPeerUnverifiedException() {
261aaf50 (David A. Velasco 2012-09-07 14:11:08 +0200 108)         return mSslPeerUnverifiedException ; 
261aaf50 (David A. Velasco 2012-09-07 14:11:08 +0200 109)     }
261aaf50 (David A. Velasco 2012-09-07 14:11:08 +0200 110) 
261aaf50 (David A. Velasco 2012-09-07 14:11:08 +0200 111)     public void setSslPeerUnverifiedException(SSLPeerUnverifiedException s) {
261aaf50 (David A. Velasco 2012-09-07 14:11:08 +0200 112)         mSslPeerUnverifiedException = s;
261aaf50 (David A. Velasco 2012-09-07 14:11:08 +0200 113)     }
261aaf50 (David A. Velasco 2012-09-07 14:11:08 +0200 114) 
261aaf50 (David A. Velasco 2012-09-07 14:11:08 +0200 115)     public boolean isException() {
261aaf50 (David A. Velasco 2012-09-07 14:11:08 +0200 116)         return (mCertificateExpiredException != null ||
261aaf50 (David A. Velasco 2012-09-07 14:11:08 +0200 117)                 mCertificateNotYetValidException != null ||
261aaf50 (David A. Velasco 2012-09-07 14:11:08 +0200 118)                 mCertPathValidatorException != null ||
bf3cf8cd (David A. Velasco 2012-09-13 17:07:17 +0200 119)                 mOtherCertificateException != null ||
bf3cf8cd (David A. Velasco 2012-09-13 17:07:17 +0200 120)                 mSslPeerUnverifiedException != null);
261aaf50 (David A. Velasco 2012-09-07 14:11:08 +0200 121)     }
261aaf50 (David A. Velasco 2012-09-07 14:11:08 +0200 122) 
261aaf50 (David A. Velasco 2012-09-07 14:11:08 +0200 123)     public boolean isRecoverable() {
261aaf50 (David A. Velasco 2012-09-07 14:11:08 +0200 124)         return (mCertificateExpiredException != null ||
261aaf50 (David A. Velasco 2012-09-07 14:11:08 +0200 125)                 mCertificateNotYetValidException != null ||
bf3cf8cd (David A. Velasco 2012-09-13 17:07:17 +0200 126)                 mCertPathValidatorException != null ||
bf3cf8cd (David A. Velasco 2012-09-13 17:07:17 +0200 127)                 mSslPeerUnverifiedException != null);
261aaf50 (David A. Velasco 2012-09-07 14:11:08 +0200 128)     }
261aaf50 (David A. Velasco 2012-09-07 14:11:08 +0200 129) 
261aaf50 (David A. Velasco 2012-09-07 14:11:08 +0200 130) }
