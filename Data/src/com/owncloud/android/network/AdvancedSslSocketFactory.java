48f13c8a (David A. Velasco 2012-09-05 13:46:30 +0200   1) /* ownCloud Android client application
48f13c8a (David A. Velasco 2012-09-05 13:46:30 +0200   2)  *   Copyright (C) 2012 Bartek Przybylski
bb257ec7 (David A. Velasco 2013-02-07 18:45:10 +0100   3)  *   Copyright (C) 2012-2013 ownCloud Inc.
48f13c8a (David A. Velasco 2012-09-05 13:46:30 +0200   4)  *
48f13c8a (David A. Velasco 2012-09-05 13:46:30 +0200   5)  *   This program is free software: you can redistribute it and/or modify
bdc0332c (David A. Velasco 2013-04-17 12:26:13 +0200   6)  *   it under the terms of the GNU General Public License version 2,
bdc0332c (David A. Velasco 2013-04-17 12:26:13 +0200   7)  *   as published by the Free Software Foundation.
48f13c8a (David A. Velasco 2012-09-05 13:46:30 +0200   8)  *
48f13c8a (David A. Velasco 2012-09-05 13:46:30 +0200   9)  *   This program is distributed in the hope that it will be useful,
48f13c8a (David A. Velasco 2012-09-05 13:46:30 +0200  10)  *   but WITHOUT ANY WARRANTY; without even the implied warranty of
48f13c8a (David A. Velasco 2012-09-05 13:46:30 +0200  11)  *   MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
48f13c8a (David A. Velasco 2012-09-05 13:46:30 +0200  12)  *   GNU General Public License for more details.
48f13c8a (David A. Velasco 2012-09-05 13:46:30 +0200  13)  *
48f13c8a (David A. Velasco 2012-09-05 13:46:30 +0200  14)  *   You should have received a copy of the GNU General Public License
48f13c8a (David A. Velasco 2012-09-05 13:46:30 +0200  15)  *   along with this program.  If not, see <http://www.gnu.org/licenses/>.
48f13c8a (David A. Velasco 2012-09-05 13:46:30 +0200  16)  *
48f13c8a (David A. Velasco 2012-09-05 13:46:30 +0200  17)  */
48f13c8a (David A. Velasco 2012-09-05 13:46:30 +0200  18) 
48f13c8a (David A. Velasco 2012-09-05 13:46:30 +0200  19) package com.owncloud.android.network;
48f13c8a (David A. Velasco 2012-09-05 13:46:30 +0200  20) 
48f13c8a (David A. Velasco 2012-09-05 13:46:30 +0200  21) import java.io.IOException;
48f13c8a (David A. Velasco 2012-09-05 13:46:30 +0200  22) import java.net.InetAddress;
48f13c8a (David A. Velasco 2012-09-05 13:46:30 +0200  23) import java.net.InetSocketAddress;
48f13c8a (David A. Velasco 2012-09-05 13:46:30 +0200  24) import java.net.Socket;
48f13c8a (David A. Velasco 2012-09-05 13:46:30 +0200  25) import java.net.SocketAddress;
48f13c8a (David A. Velasco 2012-09-05 13:46:30 +0200  26) import java.net.UnknownHostException;
261aaf50 (David A. Velasco 2012-09-07 14:11:08 +0200  27) import java.security.cert.X509Certificate;
48f13c8a (David A. Velasco 2012-09-05 13:46:30 +0200  28) 
48f13c8a (David A. Velasco 2012-09-05 13:46:30 +0200  29) import javax.net.SocketFactory;
48f13c8a (David A. Velasco 2012-09-05 13:46:30 +0200  30) import javax.net.ssl.SSLContext;
261aaf50 (David A. Velasco 2012-09-07 14:11:08 +0200  31) import javax.net.ssl.SSLException;
bf3cf8cd (David A. Velasco 2012-09-13 17:07:17 +0200  32) import javax.net.ssl.SSLHandshakeException;
261aaf50 (David A. Velasco 2012-09-07 14:11:08 +0200  33) import javax.net.ssl.SSLPeerUnverifiedException;
261aaf50 (David A. Velasco 2012-09-07 14:11:08 +0200  34) import javax.net.ssl.SSLSession;
48f13c8a (David A. Velasco 2012-09-05 13:46:30 +0200  35) import javax.net.ssl.SSLSocket;
48f13c8a (David A. Velasco 2012-09-05 13:46:30 +0200  36) 
48f13c8a (David A. Velasco 2012-09-05 13:46:30 +0200  37) import org.apache.commons.httpclient.ConnectTimeoutException;
48f13c8a (David A. Velasco 2012-09-05 13:46:30 +0200  38) import org.apache.commons.httpclient.params.HttpConnectionParams;
48f13c8a (David A. Velasco 2012-09-05 13:46:30 +0200  39) import org.apache.commons.httpclient.protocol.ProtocolSocketFactory;
48f13c8a (David A. Velasco 2012-09-05 13:46:30 +0200  40) import org.apache.http.conn.ssl.X509HostnameVerifier;
48f13c8a (David A. Velasco 2012-09-05 13:46:30 +0200  41) 
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100  42) import com.owncloud.android.Log_OC;
48f13c8a (David A. Velasco 2012-09-05 13:46:30 +0200  43) 
48f13c8a (David A. Velasco 2012-09-05 13:46:30 +0200  44) /**
48f13c8a (David A. Velasco 2012-09-05 13:46:30 +0200  45)  * AdvancedSSLProtocolSocketFactory allows to create SSL {@link Socket}s with 
48f13c8a (David A. Velasco 2012-09-05 13:46:30 +0200  46)  * a custom SSLContext and an optional Hostname Verifier.
48f13c8a (David A. Velasco 2012-09-05 13:46:30 +0200  47)  * 
48f13c8a (David A. Velasco 2012-09-05 13:46:30 +0200  48)  * @author David A. Velasco
48f13c8a (David A. Velasco 2012-09-05 13:46:30 +0200  49)  */
48f13c8a (David A. Velasco 2012-09-05 13:46:30 +0200  50) 
48f13c8a (David A. Velasco 2012-09-05 13:46:30 +0200  51) public class AdvancedSslSocketFactory implements ProtocolSocketFactory {
48f13c8a (David A. Velasco 2012-09-05 13:46:30 +0200  52) 
48f13c8a (David A. Velasco 2012-09-05 13:46:30 +0200  53)     private static final String TAG = AdvancedSslSocketFactory.class.getSimpleName();
48f13c8a (David A. Velasco 2012-09-05 13:46:30 +0200  54)     
48f13c8a (David A. Velasco 2012-09-05 13:46:30 +0200  55)     private SSLContext mSslContext = null;
261aaf50 (David A. Velasco 2012-09-07 14:11:08 +0200  56)     private AdvancedX509TrustManager mTrustManager = null;
261aaf50 (David A. Velasco 2012-09-07 14:11:08 +0200  57)     private X509HostnameVerifier mHostnameVerifier = null;
48f13c8a (David A. Velasco 2012-09-05 13:46:30 +0200  58) 
261aaf50 (David A. Velasco 2012-09-07 14:11:08 +0200  59)     public SSLContext getSslContext() {
261aaf50 (David A. Velasco 2012-09-07 14:11:08 +0200  60)         return mSslContext;
261aaf50 (David A. Velasco 2012-09-07 14:11:08 +0200  61)     }
261aaf50 (David A. Velasco 2012-09-07 14:11:08 +0200  62)     
48f13c8a (David A. Velasco 2012-09-05 13:46:30 +0200  63)     /**
48f13c8a (David A. Velasco 2012-09-05 13:46:30 +0200  64)      * Constructor for AdvancedSSLProtocolSocketFactory.
48f13c8a (David A. Velasco 2012-09-05 13:46:30 +0200  65)      */
261aaf50 (David A. Velasco 2012-09-07 14:11:08 +0200  66)     public AdvancedSslSocketFactory(SSLContext sslContext, AdvancedX509TrustManager trustManager, X509HostnameVerifier hostnameVerifier) {
48f13c8a (David A. Velasco 2012-09-05 13:46:30 +0200  67)         if (sslContext == null)
48f13c8a (David A. Velasco 2012-09-05 13:46:30 +0200  68)             throw new IllegalArgumentException("AdvancedSslSocketFactory can not be created with a null SSLContext");
261aaf50 (David A. Velasco 2012-09-07 14:11:08 +0200  69)         if (trustManager == null)
261aaf50 (David A. Velasco 2012-09-07 14:11:08 +0200  70)             throw new IllegalArgumentException("AdvancedSslSocketFactory can not be created with a null Trust Manager");
48f13c8a (David A. Velasco 2012-09-05 13:46:30 +0200  71)         mSslContext = sslContext;
261aaf50 (David A. Velasco 2012-09-07 14:11:08 +0200  72)         mTrustManager = trustManager;
48f13c8a (David A. Velasco 2012-09-05 13:46:30 +0200  73)         mHostnameVerifier = hostnameVerifier;
48f13c8a (David A. Velasco 2012-09-05 13:46:30 +0200  74)     }
48f13c8a (David A. Velasco 2012-09-05 13:46:30 +0200  75) 
48f13c8a (David A. Velasco 2012-09-05 13:46:30 +0200  76)     /**
261aaf50 (David A. Velasco 2012-09-07 14:11:08 +0200  77)      * @see ProtocolSocketFactory#createSocket(java.lang.String,int,java.net.InetAddress,int)
48f13c8a (David A. Velasco 2012-09-05 13:46:30 +0200  78)      */
48f13c8a (David A. Velasco 2012-09-05 13:46:30 +0200  79)     public Socket createSocket(String host, int port, InetAddress clientHost, int clientPort) throws IOException, UnknownHostException {
48f13c8a (David A. Velasco 2012-09-05 13:46:30 +0200  80)         Socket socket = mSslContext.getSocketFactory().createSocket(host, port, clientHost, clientPort);
261aaf50 (David A. Velasco 2012-09-07 14:11:08 +0200  81)         verifyPeerIdentity(host, port, socket);
48f13c8a (David A. Velasco 2012-09-05 13:46:30 +0200  82)         return socket;
48f13c8a (David A. Velasco 2012-09-05 13:46:30 +0200  83)     }
48f13c8a (David A. Velasco 2012-09-05 13:46:30 +0200  84) 
48f13c8a (David A. Velasco 2012-09-05 13:46:30 +0200  85)     
48f13c8a (David A. Velasco 2012-09-05 13:46:30 +0200  86)     /**
48f13c8a (David A. Velasco 2012-09-05 13:46:30 +0200  87)      * Attempts to get a new socket connection to the given host within the
48f13c8a (David A. Velasco 2012-09-05 13:46:30 +0200  88)      * given time limit.
48f13c8a (David A. Velasco 2012-09-05 13:46:30 +0200  89)      * 
48f13c8a (David A. Velasco 2012-09-05 13:46:30 +0200  90)      * @param host the host name/IP
48f13c8a (David A. Velasco 2012-09-05 13:46:30 +0200  91)      * @param port the port on the host
48f13c8a (David A. Velasco 2012-09-05 13:46:30 +0200  92)      * @param clientHost the local host name/IP to bind the socket to
48f13c8a (David A. Velasco 2012-09-05 13:46:30 +0200  93)      * @param clientPort the port on the local machine
48f13c8a (David A. Velasco 2012-09-05 13:46:30 +0200  94)      * @param params {@link HttpConnectionParams Http connection parameters}
48f13c8a (David A. Velasco 2012-09-05 13:46:30 +0200  95)      * 
48f13c8a (David A. Velasco 2012-09-05 13:46:30 +0200  96)      * @return Socket a new socket
48f13c8a (David A. Velasco 2012-09-05 13:46:30 +0200  97)      * 
48f13c8a (David A. Velasco 2012-09-05 13:46:30 +0200  98)      * @throws IOException if an I/O error occurs while creating the socket
48f13c8a (David A. Velasco 2012-09-05 13:46:30 +0200  99)      * @throws UnknownHostException if the IP address of the host cannot be
48f13c8a (David A. Velasco 2012-09-05 13:46:30 +0200 100)      *             determined
48f13c8a (David A. Velasco 2012-09-05 13:46:30 +0200 101)      */
48f13c8a (David A. Velasco 2012-09-05 13:46:30 +0200 102)     public Socket createSocket(final String host, final int port,
48f13c8a (David A. Velasco 2012-09-05 13:46:30 +0200 103)             final InetAddress localAddress, final int localPort,
48f13c8a (David A. Velasco 2012-09-05 13:46:30 +0200 104)             final HttpConnectionParams params) throws IOException,
48f13c8a (David A. Velasco 2012-09-05 13:46:30 +0200 105)             UnknownHostException, ConnectTimeoutException {
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100 106)         Log_OC.d(TAG, "Creating SSL Socket with remote " + host + ":" + port + ", local " + localAddress + ":" + localPort + ", params: " + params);
48f13c8a (David A. Velasco 2012-09-05 13:46:30 +0200 107)         if (params == null) {
48f13c8a (David A. Velasco 2012-09-05 13:46:30 +0200 108)             throw new IllegalArgumentException("Parameters may not be null");
261aaf50 (David A. Velasco 2012-09-07 14:11:08 +0200 109)         } 
48f13c8a (David A. Velasco 2012-09-05 13:46:30 +0200 110)         int timeout = params.getConnectionTimeout();
48f13c8a (David A. Velasco 2012-09-05 13:46:30 +0200 111)         SocketFactory socketfactory = mSslContext.getSocketFactory();
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100 112)         Log_OC.d(TAG, " ... with connection timeout " + timeout + " and socket timeout " + params.getSoTimeout());
48f13c8a (David A. Velasco 2012-09-05 13:46:30 +0200 113)         Socket socket = socketfactory.createSocket();
48f13c8a (David A. Velasco 2012-09-05 13:46:30 +0200 114)         SocketAddress localaddr = new InetSocketAddress(localAddress, localPort);
48f13c8a (David A. Velasco 2012-09-05 13:46:30 +0200 115)         SocketAddress remoteaddr = new InetSocketAddress(host, port);
48f13c8a (David A. Velasco 2012-09-05 13:46:30 +0200 116)         socket.setSoTimeout(params.getSoTimeout());
48f13c8a (David A. Velasco 2012-09-05 13:46:30 +0200 117)         socket.bind(localaddr);
48f13c8a (David A. Velasco 2012-09-05 13:46:30 +0200 118)         socket.connect(remoteaddr, timeout);
261aaf50 (David A. Velasco 2012-09-07 14:11:08 +0200 119)         verifyPeerIdentity(host, port, socket);
48f13c8a (David A. Velasco 2012-09-05 13:46:30 +0200 120)         return socket;
48f13c8a (David A. Velasco 2012-09-05 13:46:30 +0200 121)     }
48f13c8a (David A. Velasco 2012-09-05 13:46:30 +0200 122) 
48f13c8a (David A. Velasco 2012-09-05 13:46:30 +0200 123)     /**
261aaf50 (David A. Velasco 2012-09-07 14:11:08 +0200 124)      * @see ProtocolSocketFactory#createSocket(java.lang.String,int)
48f13c8a (David A. Velasco 2012-09-05 13:46:30 +0200 125)      */
48f13c8a (David A. Velasco 2012-09-05 13:46:30 +0200 126)     public Socket createSocket(String host, int port) throws IOException,
48f13c8a (David A. Velasco 2012-09-05 13:46:30 +0200 127)             UnknownHostException {
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100 128)         Log_OC.d(TAG, "Creating SSL Socket with remote " + host + ":" + port);
48f13c8a (David A. Velasco 2012-09-05 13:46:30 +0200 129)         Socket socket = mSslContext.getSocketFactory().createSocket(host, port);
261aaf50 (David A. Velasco 2012-09-07 14:11:08 +0200 130)         verifyPeerIdentity(host, port, socket);
48f13c8a (David A. Velasco 2012-09-05 13:46:30 +0200 131)         return socket; 
48f13c8a (David A. Velasco 2012-09-05 13:46:30 +0200 132)     }
48f13c8a (David A. Velasco 2012-09-05 13:46:30 +0200 133) 
48f13c8a (David A. Velasco 2012-09-05 13:46:30 +0200 134)     public boolean equals(Object obj) {
48f13c8a (David A. Velasco 2012-09-05 13:46:30 +0200 135)         return ((obj != null) && obj.getClass().equals(
48f13c8a (David A. Velasco 2012-09-05 13:46:30 +0200 136)                 AdvancedSslSocketFactory.class));
48f13c8a (David A. Velasco 2012-09-05 13:46:30 +0200 137)     }
48f13c8a (David A. Velasco 2012-09-05 13:46:30 +0200 138) 
48f13c8a (David A. Velasco 2012-09-05 13:46:30 +0200 139)     public int hashCode() {
48f13c8a (David A. Velasco 2012-09-05 13:46:30 +0200 140)         return AdvancedSslSocketFactory.class.hashCode();
48f13c8a (David A. Velasco 2012-09-05 13:46:30 +0200 141)     }
48f13c8a (David A. Velasco 2012-09-05 13:46:30 +0200 142) 
48f13c8a (David A. Velasco 2012-09-05 13:46:30 +0200 143) 
48f13c8a (David A. Velasco 2012-09-05 13:46:30 +0200 144)     public X509HostnameVerifier getHostNameVerifier() {
48f13c8a (David A. Velasco 2012-09-05 13:46:30 +0200 145)         return mHostnameVerifier;
48f13c8a (David A. Velasco 2012-09-05 13:46:30 +0200 146)     }
48f13c8a (David A. Velasco 2012-09-05 13:46:30 +0200 147)     
48f13c8a (David A. Velasco 2012-09-05 13:46:30 +0200 148)     
48f13c8a (David A. Velasco 2012-09-05 13:46:30 +0200 149)     public void setHostNameVerifier(X509HostnameVerifier hostnameVerifier) {
48f13c8a (David A. Velasco 2012-09-05 13:46:30 +0200 150)         mHostnameVerifier = hostnameVerifier;
48f13c8a (David A. Velasco 2012-09-05 13:46:30 +0200 151)     }
48f13c8a (David A. Velasco 2012-09-05 13:46:30 +0200 152)     
48f13c8a (David A. Velasco 2012-09-05 13:46:30 +0200 153)     /**
261aaf50 (David A. Velasco 2012-09-07 14:11:08 +0200 154)      * Verifies the identity of the server. 
261aaf50 (David A. Velasco 2012-09-07 14:11:08 +0200 155)      * 
261aaf50 (David A. Velasco 2012-09-07 14:11:08 +0200 156)      * The server certificate is verified first.
261aaf50 (David A. Velasco 2012-09-07 14:11:08 +0200 157)      * 
261aaf50 (David A. Velasco 2012-09-07 14:11:08 +0200 158)      * Then, the host name is compared with the content of the server certificate using the current host name verifier, if any.
48f13c8a (David A. Velasco 2012-09-05 13:46:30 +0200 159)      * @param socket
48f13c8a (David A. Velasco 2012-09-05 13:46:30 +0200 160)      */
261aaf50 (David A. Velasco 2012-09-07 14:11:08 +0200 161)     private void verifyPeerIdentity(String host, int port, Socket socket) throws IOException {
261aaf50 (David A. Velasco 2012-09-07 14:11:08 +0200 162)         try {
bf3cf8cd (David A. Velasco 2012-09-13 17:07:17 +0200 163)             CertificateCombinedException failInHandshake = null;
261aaf50 (David A. Velasco 2012-09-07 14:11:08 +0200 164)             /// 1. VERIFY THE SERVER CERTIFICATE through the registered TrustManager (that should be an instance of AdvancedX509TrustManager) 
48f13c8a (David A. Velasco 2012-09-05 13:46:30 +0200 165)             try {
261aaf50 (David A. Velasco 2012-09-07 14:11:08 +0200 166)                 SSLSocket sock = (SSLSocket) socket;    // a new SSLSession instance is created as a "side effect" 
261aaf50 (David A. Velasco 2012-09-07 14:11:08 +0200 167)                 sock.startHandshake();
bf3cf8cd (David A. Velasco 2012-09-13 17:07:17 +0200 168)                 
bf3cf8cd (David A. Velasco 2012-09-13 17:07:17 +0200 169)             } catch (RuntimeException e) {
bf3cf8cd (David A. Velasco 2012-09-13 17:07:17 +0200 170)                 
bf3cf8cd (David A. Velasco 2012-09-13 17:07:17 +0200 171)                 if (e instanceof CertificateCombinedException) {
bf3cf8cd (David A. Velasco 2012-09-13 17:07:17 +0200 172)                     failInHandshake = (CertificateCombinedException) e;
bf3cf8cd (David A. Velasco 2012-09-13 17:07:17 +0200 173)                 } else {
bf3cf8cd (David A. Velasco 2012-09-13 17:07:17 +0200 174)                     Throwable cause = e.getCause();
bf3cf8cd (David A. Velasco 2012-09-13 17:07:17 +0200 175)                     Throwable previousCause = null;
bf3cf8cd (David A. Velasco 2012-09-13 17:07:17 +0200 176)                     while (cause != null && cause != previousCause && !(cause instanceof CertificateCombinedException)) {
bf3cf8cd (David A. Velasco 2012-09-13 17:07:17 +0200 177)                         previousCause = cause;
bf3cf8cd (David A. Velasco 2012-09-13 17:07:17 +0200 178)                         cause = cause.getCause();
bf3cf8cd (David A. Velasco 2012-09-13 17:07:17 +0200 179)                     }
bf3cf8cd (David A. Velasco 2012-09-13 17:07:17 +0200 180)                     if (cause != null && cause instanceof CertificateCombinedException) {
bf3cf8cd (David A. Velasco 2012-09-13 17:07:17 +0200 181)                         failInHandshake = (CertificateCombinedException)cause;
bf3cf8cd (David A. Velasco 2012-09-13 17:07:17 +0200 182)                     }
bf3cf8cd (David A. Velasco 2012-09-13 17:07:17 +0200 183)                 }
bf3cf8cd (David A. Velasco 2012-09-13 17:07:17 +0200 184)                 if (failInHandshake == null) {
261aaf50 (David A. Velasco 2012-09-07 14:11:08 +0200 185)                     throw e;
261aaf50 (David A. Velasco 2012-09-07 14:11:08 +0200 186)                 }
bf3cf8cd (David A. Velasco 2012-09-13 17:07:17 +0200 187)                 failInHandshake.setHostInUrl(host);
bf3cf8cd (David A. Velasco 2012-09-13 17:07:17 +0200 188)                 
48f13c8a (David A. Velasco 2012-09-05 13:46:30 +0200 189)             }
261aaf50 (David A. Velasco 2012-09-07 14:11:08 +0200 190)             
261aaf50 (David A. Velasco 2012-09-07 14:11:08 +0200 191)             /// 2. VERIFY HOSTNAME
261aaf50 (David A. Velasco 2012-09-07 14:11:08 +0200 192)             SSLSession newSession = null;
261aaf50 (David A. Velasco 2012-09-07 14:11:08 +0200 193)             boolean verifiedHostname = true;
261aaf50 (David A. Velasco 2012-09-07 14:11:08 +0200 194)             if (mHostnameVerifier != null) {
261aaf50 (David A. Velasco 2012-09-07 14:11:08 +0200 195)                 if (failInHandshake != null) {
261aaf50 (David A. Velasco 2012-09-07 14:11:08 +0200 196)                     /// 2.1 : a new SSLSession instance was NOT created in the handshake
bf3cf8cd (David A. Velasco 2012-09-13 17:07:17 +0200 197)                     X509Certificate serverCert = failInHandshake.getServerCertificate();
261aaf50 (David A. Velasco 2012-09-07 14:11:08 +0200 198)                     try {
261aaf50 (David A. Velasco 2012-09-07 14:11:08 +0200 199)                         mHostnameVerifier.verify(host, serverCert);
261aaf50 (David A. Velasco 2012-09-07 14:11:08 +0200 200)                     } catch (SSLException e) {
261aaf50 (David A. Velasco 2012-09-07 14:11:08 +0200 201)                         verifiedHostname = false;
261aaf50 (David A. Velasco 2012-09-07 14:11:08 +0200 202)                     }
261aaf50 (David A. Velasco 2012-09-07 14:11:08 +0200 203)                 
261aaf50 (David A. Velasco 2012-09-07 14:11:08 +0200 204)                 } else {
261aaf50 (David A. Velasco 2012-09-07 14:11:08 +0200 205)                     /// 2.2 : a new SSLSession instance was created in the handshake
bf3cf8cd (David A. Velasco 2012-09-13 17:07:17 +0200 206)                     newSession = ((SSLSocket)socket).getSession();
bf3cf8cd (David A. Velasco 2012-09-13 17:07:17 +0200 207)                     if (!mTrustManager.isKnownServer((X509Certificate)(newSession.getPeerCertificates()[0]))) {
bf3cf8cd (David A. Velasco 2012-09-13 17:07:17 +0200 208)                         verifiedHostname = mHostnameVerifier.verify(host, newSession); 
261aaf50 (David A. Velasco 2012-09-07 14:11:08 +0200 209)                     }
261aaf50 (David A. Velasco 2012-09-07 14:11:08 +0200 210)                 }
261aaf50 (David A. Velasco 2012-09-07 14:11:08 +0200 211)             }
261aaf50 (David A. Velasco 2012-09-07 14:11:08 +0200 212) 
261aaf50 (David A. Velasco 2012-09-07 14:11:08 +0200 213)             /// 3. Combine the exceptions to throw, if any
bf3cf8cd (David A. Velasco 2012-09-13 17:07:17 +0200 214)             if (!verifiedHostname) {
bf3cf8cd (David A. Velasco 2012-09-13 17:07:17 +0200 215)                 SSLPeerUnverifiedException pue = new SSLPeerUnverifiedException("Names in the server certificate do not match to " + host + " in the URL");
bf3cf8cd (David A. Velasco 2012-09-13 17:07:17 +0200 216)                 if (failInHandshake == null) {
bf3cf8cd (David A. Velasco 2012-09-13 17:07:17 +0200 217)                     failInHandshake = new CertificateCombinedException((X509Certificate) newSession.getPeerCertificates()[0]);
bf3cf8cd (David A. Velasco 2012-09-13 17:07:17 +0200 218)                     failInHandshake.setHostInUrl(host);
261aaf50 (David A. Velasco 2012-09-07 14:11:08 +0200 219)                 }
bf3cf8cd (David A. Velasco 2012-09-13 17:07:17 +0200 220)                 failInHandshake.setSslPeerUnverifiedException(pue);
bf3cf8cd (David A. Velasco 2012-09-13 17:07:17 +0200 221)                 pue.initCause(failInHandshake);
261aaf50 (David A. Velasco 2012-09-07 14:11:08 +0200 222)                 throw pue;
bf3cf8cd (David A. Velasco 2012-09-13 17:07:17 +0200 223)                 
bf3cf8cd (David A. Velasco 2012-09-13 17:07:17 +0200 224)             } else if (failInHandshake != null) {
bf3cf8cd (David A. Velasco 2012-09-13 17:07:17 +0200 225)                 SSLHandshakeException hse = new SSLHandshakeException("Server certificate could not be verified");
bf3cf8cd (David A. Velasco 2012-09-13 17:07:17 +0200 226)                 hse.initCause(failInHandshake);
bf3cf8cd (David A. Velasco 2012-09-13 17:07:17 +0200 227)                 throw hse;
261aaf50 (David A. Velasco 2012-09-07 14:11:08 +0200 228)             }
261aaf50 (David A. Velasco 2012-09-07 14:11:08 +0200 229)             
261aaf50 (David A. Velasco 2012-09-07 14:11:08 +0200 230)         } catch (IOException io) {        
261aaf50 (David A. Velasco 2012-09-07 14:11:08 +0200 231)             try {
261aaf50 (David A. Velasco 2012-09-07 14:11:08 +0200 232)                 socket.close();
261aaf50 (David A. Velasco 2012-09-07 14:11:08 +0200 233)             } catch (Exception x) {
261aaf50 (David A. Velasco 2012-09-07 14:11:08 +0200 234)                 // NOTHING - irrelevant exception for the caller 
261aaf50 (David A. Velasco 2012-09-07 14:11:08 +0200 235)             }
261aaf50 (David A. Velasco 2012-09-07 14:11:08 +0200 236)             throw io;
48f13c8a (David A. Velasco 2012-09-05 13:46:30 +0200 237)         }
48f13c8a (David A. Velasco 2012-09-05 13:46:30 +0200 238)     }
261aaf50 (David A. Velasco 2012-09-07 14:11:08 +0200 239) 
261aaf50 (David A. Velasco 2012-09-07 14:11:08 +0200 240) }
