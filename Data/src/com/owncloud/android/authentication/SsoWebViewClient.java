d2ee9062 (David A. Velasco 2013-07-30 13:02:13 +0200   1) /* ownCloud Android client application
d2ee9062 (David A. Velasco 2013-07-30 13:02:13 +0200   2)  *   Copyright (C) 2012-2013 ownCloud Inc.
d2ee9062 (David A. Velasco 2013-07-30 13:02:13 +0200   3)  *
d2ee9062 (David A. Velasco 2013-07-30 13:02:13 +0200   4)  *   This program is free software: you can redistribute it and/or modify
d2ee9062 (David A. Velasco 2013-07-30 13:02:13 +0200   5)  *   it under the terms of the GNU General Public License version 2,
d2ee9062 (David A. Velasco 2013-07-30 13:02:13 +0200   6)  *   as published by the Free Software Foundation.
d2ee9062 (David A. Velasco 2013-07-30 13:02:13 +0200   7)  *
d2ee9062 (David A. Velasco 2013-07-30 13:02:13 +0200   8)  *   This program is distributed in the hope that it will be useful,
d2ee9062 (David A. Velasco 2013-07-30 13:02:13 +0200   9)  *   but WITHOUT ANY WARRANTY; without even the implied warranty of
d2ee9062 (David A. Velasco 2013-07-30 13:02:13 +0200  10)  *   MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
d2ee9062 (David A. Velasco 2013-07-30 13:02:13 +0200  11)  *   GNU General Public License for more details.
d2ee9062 (David A. Velasco 2013-07-30 13:02:13 +0200  12)  *
d2ee9062 (David A. Velasco 2013-07-30 13:02:13 +0200  13)  *   You should have received a copy of the GNU General Public License
d2ee9062 (David A. Velasco 2013-07-30 13:02:13 +0200  14)  *   along with this program.  If not, see <http://www.gnu.org/licenses/>.
d2ee9062 (David A. Velasco 2013-07-30 13:02:13 +0200  15)  *
d2ee9062 (David A. Velasco 2013-07-30 13:02:13 +0200  16)  */
d2ee9062 (David A. Velasco 2013-07-30 13:02:13 +0200  17) 
d2ee9062 (David A. Velasco 2013-07-30 13:02:13 +0200  18) package com.owncloud.android.authentication;
d2ee9062 (David A. Velasco 2013-07-30 13:02:13 +0200  19) 
52bc433b (David A. Velasco 2013-08-01 17:47:09 +0200  20) import java.lang.ref.WeakReference;
52bc433b (David A. Velasco 2013-08-01 17:47:09 +0200  21) 
37297fb7 (David A. Velasco 2013-08-21 18:51:13 +0200  22) import com.owncloud.android.Log_OC;
37297fb7 (David A. Velasco 2013-08-21 18:51:13 +0200  23) 
d2ee9062 (David A. Velasco 2013-07-30 13:02:13 +0200  24) import android.graphics.Bitmap;
52bc433b (David A. Velasco 2013-08-01 17:47:09 +0200  25) import android.os.Handler;
c8884391 (David A. Velasco 2013-08-17 12:56:29 +0200  26) import android.os.Message;
d2ee9062 (David A. Velasco 2013-07-30 13:02:13 +0200  27) import android.view.View;
d2ee9062 (David A. Velasco 2013-07-30 13:02:13 +0200  28) import android.webkit.CookieManager;
d2ee9062 (David A. Velasco 2013-07-30 13:02:13 +0200  29) import android.webkit.WebView;
d2ee9062 (David A. Velasco 2013-07-30 13:02:13 +0200  30) import android.webkit.WebViewClient;
d2ee9062 (David A. Velasco 2013-07-30 13:02:13 +0200  31) 
d2ee9062 (David A. Velasco 2013-07-30 13:02:13 +0200  32) 
d2ee9062 (David A. Velasco 2013-07-30 13:02:13 +0200  33) /**
d2ee9062 (David A. Velasco 2013-07-30 13:02:13 +0200  34)  * Custom {@link WebViewClient} client aimed to catch the end of a single-sign-on process 
d2ee9062 (David A. Velasco 2013-07-30 13:02:13 +0200  35)  * running in the {@link WebView} that is attached to.
d2ee9062 (David A. Velasco 2013-07-30 13:02:13 +0200  36)  * 
d2ee9062 (David A. Velasco 2013-07-30 13:02:13 +0200  37)  * Assumes that the single-sign-on is kept thanks to a cookie set at the end of the
d2ee9062 (David A. Velasco 2013-07-30 13:02:13 +0200  38)  * authentication process.
d2ee9062 (David A. Velasco 2013-07-30 13:02:13 +0200  39)  *   
d2ee9062 (David A. Velasco 2013-07-30 13:02:13 +0200  40)  * @author David A. Velasco
d2ee9062 (David A. Velasco 2013-07-30 13:02:13 +0200  41)  */
d2ee9062 (David A. Velasco 2013-07-30 13:02:13 +0200  42) public class SsoWebViewClient extends WebViewClient {
d2ee9062 (David A. Velasco 2013-07-30 13:02:13 +0200  43)         
37297fb7 (David A. Velasco 2013-08-21 18:51:13 +0200  44)     private static final String TAG = SsoWebViewClient.class.getSimpleName();
d2ee9062 (David A. Velasco 2013-07-30 13:02:13 +0200  45)     
52bc433b (David A. Velasco 2013-08-01 17:47:09 +0200  46)     public interface SsoWebViewClientListener {
52bc433b (David A. Velasco 2013-08-01 17:47:09 +0200  47)         public void onSsoFinished(String sessionCookie);
52bc433b (David A. Velasco 2013-08-01 17:47:09 +0200  48)     }
52bc433b (David A. Velasco 2013-08-01 17:47:09 +0200  49)     
52bc433b (David A. Velasco 2013-08-01 17:47:09 +0200  50)     private Handler mListenerHandler;
52bc433b (David A. Velasco 2013-08-01 17:47:09 +0200  51)     private WeakReference<SsoWebViewClientListener> mListenerRef;
d2ee9062 (David A. Velasco 2013-07-30 13:02:13 +0200  52)     private String mTargetUrl;
37297fb7 (David A. Velasco 2013-08-21 18:51:13 +0200  53)     private String mLastReloadedUrlAtError;
d2ee9062 (David A. Velasco 2013-07-30 13:02:13 +0200  54)     
52bc433b (David A. Velasco 2013-08-01 17:47:09 +0200  55)     public SsoWebViewClient (Handler listenerHandler, SsoWebViewClientListener listener) {
52bc433b (David A. Velasco 2013-08-01 17:47:09 +0200  56)         mListenerHandler = listenerHandler;
52bc433b (David A. Velasco 2013-08-01 17:47:09 +0200  57)         mListenerRef = new WeakReference<SsoWebViewClient.SsoWebViewClientListener>(listener);
d2ee9062 (David A. Velasco 2013-07-30 13:02:13 +0200  58)         mTargetUrl = "fake://url.to.be.set";
37297fb7 (David A. Velasco 2013-08-21 18:51:13 +0200  59)         mLastReloadedUrlAtError = null;
d2ee9062 (David A. Velasco 2013-07-30 13:02:13 +0200  60)     }
d2ee9062 (David A. Velasco 2013-07-30 13:02:13 +0200  61)     
d2ee9062 (David A. Velasco 2013-07-30 13:02:13 +0200  62)     public String getTargetUrl() {
d2ee9062 (David A. Velasco 2013-07-30 13:02:13 +0200  63)         return mTargetUrl;
d2ee9062 (David A. Velasco 2013-07-30 13:02:13 +0200  64)     }
d2ee9062 (David A. Velasco 2013-07-30 13:02:13 +0200  65)     
d2ee9062 (David A. Velasco 2013-07-30 13:02:13 +0200  66)     public void setTargetUrl(String targetUrl) {
d2ee9062 (David A. Velasco 2013-07-30 13:02:13 +0200  67)         mTargetUrl = targetUrl;
d2ee9062 (David A. Velasco 2013-07-30 13:02:13 +0200  68)     }
d2ee9062 (David A. Velasco 2013-07-30 13:02:13 +0200  69) 
d2ee9062 (David A. Velasco 2013-07-30 13:02:13 +0200  70)     @Override
d2ee9062 (David A. Velasco 2013-07-30 13:02:13 +0200  71)     public void onPageStarted (WebView view, String url, Bitmap favicon) {
37297fb7 (David A. Velasco 2013-08-21 18:51:13 +0200  72)         Log_OC.d(TAG, "onPageStarted : " + url);
d7c6472b (David A. Velasco 2013-08-22 17:38:26 +0200  73)         super.onPageStarted(view, url, favicon);
d2ee9062 (David A. Velasco 2013-07-30 13:02:13 +0200  74)     }
d2ee9062 (David A. Velasco 2013-07-30 13:02:13 +0200  75)     
d2ee9062 (David A. Velasco 2013-07-30 13:02:13 +0200  76)     @Override
c8884391 (David A. Velasco 2013-08-17 12:56:29 +0200  77)     public void onFormResubmission (WebView view, Message dontResend, Message resend) {
37297fb7 (David A. Velasco 2013-08-21 18:51:13 +0200  78)         Log_OC.d(TAG, "onFormResubMission ");
37297fb7 (David A. Velasco 2013-08-21 18:51:13 +0200  79) 
c8884391 (David A. Velasco 2013-08-17 12:56:29 +0200  80)         // necessary to grant reload of last page when device orientation is changed after sending a form
37297fb7 (David A. Velasco 2013-08-21 18:51:13 +0200  81)         resend.sendToTarget();
c8884391 (David A. Velasco 2013-08-17 12:56:29 +0200  82)     }
c8884391 (David A. Velasco 2013-08-17 12:56:29 +0200  83) 
c8884391 (David A. Velasco 2013-08-17 12:56:29 +0200  84)     @Override
d2ee9062 (David A. Velasco 2013-07-30 13:02:13 +0200  85)     public boolean shouldOverrideUrlLoading(WebView view, String url) {
d2ee9062 (David A. Velasco 2013-07-30 13:02:13 +0200  86)         return false;
d2ee9062 (David A. Velasco 2013-07-30 13:02:13 +0200  87)     }
d2ee9062 (David A. Velasco 2013-07-30 13:02:13 +0200  88)     
d2ee9062 (David A. Velasco 2013-07-30 13:02:13 +0200  89)     @Override
d2ee9062 (David A. Velasco 2013-07-30 13:02:13 +0200  90)     public void onReceivedError (WebView view, int errorCode, String description, String failingUrl) {
37297fb7 (David A. Velasco 2013-08-21 18:51:13 +0200  91)         Log_OC.e(TAG, "onReceivedError : " + failingUrl + ", code " + errorCode + ", description: " + description);
37297fb7 (David A. Velasco 2013-08-21 18:51:13 +0200  92)         if (!failingUrl.equals(mLastReloadedUrlAtError)) {
37297fb7 (David A. Velasco 2013-08-21 18:51:13 +0200  93)             view.reload();
37297fb7 (David A. Velasco 2013-08-21 18:51:13 +0200  94)             mLastReloadedUrlAtError = failingUrl;
37297fb7 (David A. Velasco 2013-08-21 18:51:13 +0200  95)         } else {
37297fb7 (David A. Velasco 2013-08-21 18:51:13 +0200  96)             mLastReloadedUrlAtError = null;
b6213a0d (David A. Velasco 2013-08-22 11:48:57 +0200  97)             super.onReceivedError(view, errorCode, description, failingUrl);
37297fb7 (David A. Velasco 2013-08-21 18:51:13 +0200  98)         }
d2ee9062 (David A. Velasco 2013-07-30 13:02:13 +0200  99)     }
37297fb7 (David A. Velasco 2013-08-21 18:51:13 +0200 100)     
37297fb7 (David A. Velasco 2013-08-21 18:51:13 +0200 101)     @Override
37297fb7 (David A. Velasco 2013-08-21 18:51:13 +0200 102)     public void onPageFinished (WebView view, String url) {
37297fb7 (David A. Velasco 2013-08-21 18:51:13 +0200 103)         Log_OC.d(TAG, "onPageFinished : " + url);
37297fb7 (David A. Velasco 2013-08-21 18:51:13 +0200 104)         mLastReloadedUrlAtError = null;
d7c6472b (David A. Velasco 2013-08-22 17:38:26 +0200 105)         if (url.startsWith(mTargetUrl)) {
d7c6472b (David A. Velasco 2013-08-22 17:38:26 +0200 106)             view.setVisibility(View.GONE);
d7c6472b (David A. Velasco 2013-08-22 17:38:26 +0200 107)             CookieManager cookieManager = CookieManager.getInstance();
d7c6472b (David A. Velasco 2013-08-22 17:38:26 +0200 108)             final String cookies = cookieManager.getCookie(url);
d7c6472b (David A. Velasco 2013-08-22 17:38:26 +0200 109)             //Log_OC.d(TAG, "Cookies: " + cookies);
d7c6472b (David A. Velasco 2013-08-22 17:38:26 +0200 110)             if (mListenerHandler != null && mListenerRef != null) {
d7c6472b (David A. Velasco 2013-08-22 17:38:26 +0200 111)                 // this is good idea because onPageFinished is not running in the UI thread
d7c6472b (David A. Velasco 2013-08-22 17:38:26 +0200 112)                 mListenerHandler.post(new Runnable() {
d7c6472b (David A. Velasco 2013-08-22 17:38:26 +0200 113)                     @Override
d7c6472b (David A. Velasco 2013-08-22 17:38:26 +0200 114)                     public void run() {
d7c6472b (David A. Velasco 2013-08-22 17:38:26 +0200 115)                         SsoWebViewClientListener listener = mListenerRef.get();
d7c6472b (David A. Velasco 2013-08-22 17:38:26 +0200 116)                         if (listener != null) {
d7c6472b (David A. Velasco 2013-08-22 17:38:26 +0200 117)                             listener.onSsoFinished(cookies);
d7c6472b (David A. Velasco 2013-08-22 17:38:26 +0200 118)                         }
d7c6472b (David A. Velasco 2013-08-22 17:38:26 +0200 119)                     }
d7c6472b (David A. Velasco 2013-08-22 17:38:26 +0200 120)                 });
d7c6472b (David A. Velasco 2013-08-22 17:38:26 +0200 121)             }
d7c6472b (David A. Velasco 2013-08-22 17:38:26 +0200 122)         }
d7c6472b (David A. Velasco 2013-08-22 17:38:26 +0200 123) 
37297fb7 (David A. Velasco 2013-08-21 18:51:13 +0200 124)     }
37297fb7 (David A. Velasco 2013-08-21 18:51:13 +0200 125)     
37297fb7 (David A. Velasco 2013-08-21 18:51:13 +0200 126)     /*
d2ee9062 (David A. Velasco 2013-07-30 13:02:13 +0200 127)     @Override
d2ee9062 (David A. Velasco 2013-07-30 13:02:13 +0200 128)     public void doUpdateVisitedHistory (WebView view, String url, boolean isReload) {
37297fb7 (David A. Velasco 2013-08-21 18:51:13 +0200 129)         Log_OC.d(TAG, "doUpdateVisitedHistory : " + url);
d2ee9062 (David A. Velasco 2013-07-30 13:02:13 +0200 130)     }
d2ee9062 (David A. Velasco 2013-07-30 13:02:13 +0200 131)     
d2ee9062 (David A. Velasco 2013-07-30 13:02:13 +0200 132)     @Override
d2ee9062 (David A. Velasco 2013-07-30 13:02:13 +0200 133)     public void onReceivedSslError (WebView view, SslErrorHandler handler, SslError error) {
37297fb7 (David A. Velasco 2013-08-21 18:51:13 +0200 134)         Log_OC.d(TAG, "onReceivedSslError : " + error);
d2ee9062 (David A. Velasco 2013-07-30 13:02:13 +0200 135)     }
d2ee9062 (David A. Velasco 2013-07-30 13:02:13 +0200 136)     
d2ee9062 (David A. Velasco 2013-07-30 13:02:13 +0200 137)     @Override
d2ee9062 (David A. Velasco 2013-07-30 13:02:13 +0200 138)     public void onReceivedHttpAuthRequest (WebView view, HttpAuthHandler handler, String host, String realm) {
37297fb7 (David A. Velasco 2013-08-21 18:51:13 +0200 139)         Log_OC.d(TAG, "onReceivedHttpAuthRequest : " + host);
d2ee9062 (David A. Velasco 2013-07-30 13:02:13 +0200 140)     }
d2ee9062 (David A. Velasco 2013-07-30 13:02:13 +0200 141) 
d2ee9062 (David A. Velasco 2013-07-30 13:02:13 +0200 142)     @Override
d2ee9062 (David A. Velasco 2013-07-30 13:02:13 +0200 143)     public WebResourceResponse shouldInterceptRequest (WebView view, String url) {
37297fb7 (David A. Velasco 2013-08-21 18:51:13 +0200 144)         Log_OC.d(TAG, "shouldInterceptRequest : " + url);
d2ee9062 (David A. Velasco 2013-07-30 13:02:13 +0200 145)         return null;
d2ee9062 (David A. Velasco 2013-07-30 13:02:13 +0200 146)     }
d2ee9062 (David A. Velasco 2013-07-30 13:02:13 +0200 147)     
d2ee9062 (David A. Velasco 2013-07-30 13:02:13 +0200 148)     @Override
d2ee9062 (David A. Velasco 2013-07-30 13:02:13 +0200 149)     public void onLoadResource (WebView view, String url) {
37297fb7 (David A. Velasco 2013-08-21 18:51:13 +0200 150)         Log_OC.d(TAG, "onLoadResource : " + url);   
d2ee9062 (David A. Velasco 2013-07-30 13:02:13 +0200 151)     }
d2ee9062 (David A. Velasco 2013-07-30 13:02:13 +0200 152)     
d2ee9062 (David A. Velasco 2013-07-30 13:02:13 +0200 153)     @Override
d2ee9062 (David A. Velasco 2013-07-30 13:02:13 +0200 154)     public void onReceivedLoginRequest (WebView view, String realm, String account, String args) {
37297fb7 (David A. Velasco 2013-08-21 18:51:13 +0200 155)         Log_OC.d(TAG, "onReceivedLoginRequest : " + realm + ", " + account + ", " + args);
d2ee9062 (David A. Velasco 2013-07-30 13:02:13 +0200 156)     }
d2ee9062 (David A. Velasco 2013-07-30 13:02:13 +0200 157)     
d2ee9062 (David A. Velasco 2013-07-30 13:02:13 +0200 158)     @Override
d2ee9062 (David A. Velasco 2013-07-30 13:02:13 +0200 159)     public void onScaleChanged (WebView view, float oldScale, float newScale) {
37297fb7 (David A. Velasco 2013-08-21 18:51:13 +0200 160)         Log_OC.d(TAG, "onScaleChanged : " + oldScale + " -> " + newScale);
37297fb7 (David A. Velasco 2013-08-21 18:51:13 +0200 161)         super.onScaleChanged(view, oldScale, newScale);
d2ee9062 (David A. Velasco 2013-07-30 13:02:13 +0200 162)     }
37297fb7 (David A. Velasco 2013-08-21 18:51:13 +0200 163) 
d2ee9062 (David A. Velasco 2013-07-30 13:02:13 +0200 164)     @Override
d2ee9062 (David A. Velasco 2013-07-30 13:02:13 +0200 165)     public void onUnhandledKeyEvent (WebView view, KeyEvent event) {
37297fb7 (David A. Velasco 2013-08-21 18:51:13 +0200 166)         Log_OC.d(TAG, "onUnhandledKeyEvent : " + event);
d2ee9062 (David A. Velasco 2013-07-30 13:02:13 +0200 167)     }
d2ee9062 (David A. Velasco 2013-07-30 13:02:13 +0200 168)     
d2ee9062 (David A. Velasco 2013-07-30 13:02:13 +0200 169)     @Override
d2ee9062 (David A. Velasco 2013-07-30 13:02:13 +0200 170)     public boolean shouldOverrideKeyEvent (WebView view, KeyEvent event) {
37297fb7 (David A. Velasco 2013-08-21 18:51:13 +0200 171)         Log_OC.d(TAG, "shouldOverrideKeyEvent : " + event);
d2ee9062 (David A. Velasco 2013-07-30 13:02:13 +0200 172)         return false;
d2ee9062 (David A. Velasco 2013-07-30 13:02:13 +0200 173)     }
d2ee9062 (David A. Velasco 2013-07-30 13:02:13 +0200 174)     */
d2ee9062 (David A. Velasco 2013-07-30 13:02:13 +0200 175) }
