37297fb7 (David A. Velasco 2013-08-21 18:51:13 +0200   1) /* ownCloud Android client application
37297fb7 (David A. Velasco 2013-08-21 18:51:13 +0200   2)  *   Copyright (C) 2012-2013 ownCloud Inc.
37297fb7 (David A. Velasco 2013-08-21 18:51:13 +0200   3)  *
37297fb7 (David A. Velasco 2013-08-21 18:51:13 +0200   4)  *   This program is free software: you can redistribute it and/or modify
37297fb7 (David A. Velasco 2013-08-21 18:51:13 +0200   5)  *   it under the terms of the GNU General Public License version 2,
37297fb7 (David A. Velasco 2013-08-21 18:51:13 +0200   6)  *   as published by the Free Software Foundation.
37297fb7 (David A. Velasco 2013-08-21 18:51:13 +0200   7)  *
37297fb7 (David A. Velasco 2013-08-21 18:51:13 +0200   8)  *   This program is distributed in the hope that it will be useful,
37297fb7 (David A. Velasco 2013-08-21 18:51:13 +0200   9)  *   but WITHOUT ANY WARRANTY; without even the implied warranty of
37297fb7 (David A. Velasco 2013-08-21 18:51:13 +0200  10)  *   MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
37297fb7 (David A. Velasco 2013-08-21 18:51:13 +0200  11)  *   GNU General Public License for more details.
37297fb7 (David A. Velasco 2013-08-21 18:51:13 +0200  12)  *
37297fb7 (David A. Velasco 2013-08-21 18:51:13 +0200  13)  *   You should have received a copy of the GNU General Public License
37297fb7 (David A. Velasco 2013-08-21 18:51:13 +0200  14)  *   along with this program.  If not, see <http://www.gnu.org/licenses/>.
37297fb7 (David A. Velasco 2013-08-21 18:51:13 +0200  15)  *
37297fb7 (David A. Velasco 2013-08-21 18:51:13 +0200  16)  */
37297fb7 (David A. Velasco 2013-08-21 18:51:13 +0200  17) 
9dacdbec (masensio         2013-08-13 12:08:46 +0200  18) package com.owncloud.android.ui.dialog;
9dacdbec (masensio         2013-08-13 12:08:46 +0200  19) 
9dacdbec (masensio         2013-08-13 12:08:46 +0200  20) import android.annotation.SuppressLint;
55f2bd23 (masensio         2013-08-13 13:28:45 +0200  21) import android.app.Activity;
9dacdbec (masensio         2013-08-13 12:08:46 +0200  22) import android.app.Dialog;
37297fb7 (David A. Velasco 2013-08-21 18:51:13 +0200  23) import android.content.DialogInterface;
9dacdbec (masensio         2013-08-13 12:08:46 +0200  24) import android.os.Bundle;
9dacdbec (masensio         2013-08-13 12:08:46 +0200  25) import android.os.Handler;
37297fb7 (David A. Velasco 2013-08-21 18:51:13 +0200  26) import android.support.v4.app.FragmentTransaction;
37297fb7 (David A. Velasco 2013-08-21 18:51:13 +0200  27) import android.support.v4.app.FragmentManager;
37297fb7 (David A. Velasco 2013-08-21 18:51:13 +0200  28) import android.view.LayoutInflater;
37297fb7 (David A. Velasco 2013-08-21 18:51:13 +0200  29) import android.view.View;
37297fb7 (David A. Velasco 2013-08-21 18:51:13 +0200  30) import android.view.ViewGroup;
9dacdbec (masensio         2013-08-13 12:08:46 +0200  31) import android.webkit.CookieManager;
b2f18e0f (David A. Velasco 2013-08-22 19:03:21 +0200  32) import android.webkit.CookieSyncManager;
37297fb7 (David A. Velasco 2013-08-21 18:51:13 +0200  33) import android.webkit.WebBackForwardList;
9dacdbec (masensio         2013-08-13 12:08:46 +0200  34) import android.webkit.WebSettings;
9dacdbec (masensio         2013-08-13 12:08:46 +0200  35) import android.webkit.WebView;
9dacdbec (masensio         2013-08-13 12:08:46 +0200  36) 
37297fb7 (David A. Velasco 2013-08-21 18:51:13 +0200  37) import com.actionbarsherlock.app.SherlockDialogFragment;
9dacdbec (masensio         2013-08-13 12:08:46 +0200  38) import com.owncloud.android.Log_OC;
37297fb7 (David A. Velasco 2013-08-21 18:51:13 +0200  39) import com.owncloud.android.R;
9dacdbec (masensio         2013-08-13 12:08:46 +0200  40) import com.owncloud.android.authentication.SsoWebViewClient;
9dacdbec (masensio         2013-08-13 12:08:46 +0200  41) import com.owncloud.android.authentication.SsoWebViewClient.SsoWebViewClientListener;
9dacdbec (masensio         2013-08-13 12:08:46 +0200  42) 
9dacdbec (masensio         2013-08-13 12:08:46 +0200  43) import eu.alefzero.webdav.WebdavClient;
9dacdbec (masensio         2013-08-13 12:08:46 +0200  44) 
9dacdbec (masensio         2013-08-13 12:08:46 +0200  45) /**
9dacdbec (masensio         2013-08-13 12:08:46 +0200  46)  * Dialog to show the WebView for SAML Authentication
9dacdbec (masensio         2013-08-13 12:08:46 +0200  47)  * 
9dacdbec (masensio         2013-08-13 12:08:46 +0200  48)  * @author Maria Asensio
37297fb7 (David A. Velasco 2013-08-21 18:51:13 +0200  49)  * @author David A. Velasco
9dacdbec (masensio         2013-08-13 12:08:46 +0200  50)  */
37297fb7 (David A. Velasco 2013-08-21 18:51:13 +0200  51) public class SamlWebViewDialog extends SherlockDialogFragment {
9dacdbec (masensio         2013-08-13 12:08:46 +0200  52) 
9dacdbec (masensio         2013-08-13 12:08:46 +0200  53)     public final String SAML_DIALOG_TAG = "SamlWebViewDialog";
9dacdbec (masensio         2013-08-13 12:08:46 +0200  54)     
9dacdbec (masensio         2013-08-13 12:08:46 +0200  55)     private final static String TAG =  SamlWebViewDialog.class.getSimpleName();
37297fb7 (David A. Velasco 2013-08-21 18:51:13 +0200  56) 
37297fb7 (David A. Velasco 2013-08-21 18:51:13 +0200  57)     private static final String ARG_INITIAL_URL = "INITIAL_URL";
37297fb7 (David A. Velasco 2013-08-21 18:51:13 +0200  58)     private static final String ARG_TARGET_URL = "TARGET_URL";
37297fb7 (David A. Velasco 2013-08-21 18:51:13 +0200  59)     private static final String KEY_WEBVIEW_STATE = "WEBVIEW_STATE";
9dacdbec (masensio         2013-08-13 12:08:46 +0200  60)     
b6213a0d (David A. Velasco 2013-08-22 11:48:57 +0200  61)     private WebView mSsoWebView;
9dacdbec (masensio         2013-08-13 12:08:46 +0200  62)     private SsoWebViewClient mWebViewClient;
9dacdbec (masensio         2013-08-13 12:08:46 +0200  63)     
37297fb7 (David A. Velasco 2013-08-21 18:51:13 +0200  64)     private String mInitialUrl;
37297fb7 (David A. Velasco 2013-08-21 18:51:13 +0200  65)     private String mTargetUrl;
9dacdbec (masensio         2013-08-13 12:08:46 +0200  66)     
55f2bd23 (masensio         2013-08-13 13:28:45 +0200  67)     private Handler mHandler;
55f2bd23 (masensio         2013-08-13 13:28:45 +0200  68) 
55f2bd23 (masensio         2013-08-13 13:28:45 +0200  69)     private SsoWebViewClientListener mSsoWebViewClientListener;
37297fb7 (David A. Velasco 2013-08-21 18:51:13 +0200  70) 
37297fb7 (David A. Velasco 2013-08-21 18:51:13 +0200  71)     //private View mSsoRootView;
37297fb7 (David A. Velasco 2013-08-21 18:51:13 +0200  72) 
9dacdbec (masensio         2013-08-13 12:08:46 +0200  73) 
9dacdbec (masensio         2013-08-13 12:08:46 +0200  74)     /**
9dacdbec (masensio         2013-08-13 12:08:46 +0200  75)      * Public factory method to get dialog instances.
9dacdbec (masensio         2013-08-13 12:08:46 +0200  76)      * 
9dacdbec (masensio         2013-08-13 12:08:46 +0200  77)      * @param handler
9dacdbec (masensio         2013-08-13 12:08:46 +0200  78)      * @param Url           Url to open at WebView
9dacdbec (masensio         2013-08-13 12:08:46 +0200  79)      * @param targetURL     mHostBaseUrl + AccountUtils.getWebdavPath(mDiscoveredVersion, mCurrentAuthTokenType)
9dacdbec (masensio         2013-08-13 12:08:46 +0200  80)      * @return              New dialog instance, ready to show.
9dacdbec (masensio         2013-08-13 12:08:46 +0200  81)      */
55f2bd23 (masensio         2013-08-13 13:28:45 +0200  82)     public static SamlWebViewDialog newInstance(String url, String targetUrl) {
37297fb7 (David A. Velasco 2013-08-21 18:51:13 +0200  83)         Log_OC.d(TAG, "New instance");
9dacdbec (masensio         2013-08-13 12:08:46 +0200  84)         SamlWebViewDialog fragment = new SamlWebViewDialog();
37297fb7 (David A. Velasco 2013-08-21 18:51:13 +0200  85)         Bundle args = new Bundle();
37297fb7 (David A. Velasco 2013-08-21 18:51:13 +0200  86)         args.putString(ARG_INITIAL_URL, url);
37297fb7 (David A. Velasco 2013-08-21 18:51:13 +0200  87)         args.putString(ARG_TARGET_URL, targetUrl);
37297fb7 (David A. Velasco 2013-08-21 18:51:13 +0200  88)         fragment.setArguments(args);
9dacdbec (masensio         2013-08-13 12:08:46 +0200  89)         return fragment;
9dacdbec (masensio         2013-08-13 12:08:46 +0200  90)     }
9dacdbec (masensio         2013-08-13 12:08:46 +0200  91)     
9dacdbec (masensio         2013-08-13 12:08:46 +0200  92)     
37297fb7 (David A. Velasco 2013-08-21 18:51:13 +0200  93)     public SamlWebViewDialog() {
37297fb7 (David A. Velasco 2013-08-21 18:51:13 +0200  94)         super();
37297fb7 (David A. Velasco 2013-08-21 18:51:13 +0200  95)         Log_OC.d(TAG, "constructor");
37297fb7 (David A. Velasco 2013-08-21 18:51:13 +0200  96)     }
37297fb7 (David A. Velasco 2013-08-21 18:51:13 +0200  97)     
37297fb7 (David A. Velasco 2013-08-21 18:51:13 +0200  98)     
9dacdbec (masensio         2013-08-13 12:08:46 +0200  99)     @Override
37297fb7 (David A. Velasco 2013-08-21 18:51:13 +0200 100)     public void onAttach(Activity activity) {
37297fb7 (David A. Velasco 2013-08-21 18:51:13 +0200 101)         Log_OC.d(TAG, "onAttach");
37297fb7 (David A. Velasco 2013-08-21 18:51:13 +0200 102)         super.onAttach(activity);
37297fb7 (David A. Velasco 2013-08-21 18:51:13 +0200 103)         try {
37297fb7 (David A. Velasco 2013-08-21 18:51:13 +0200 104)             mSsoWebViewClientListener = (SsoWebViewClientListener) activity;
37297fb7 (David A. Velasco 2013-08-21 18:51:13 +0200 105)             mHandler = new Handler();
37297fb7 (David A. Velasco 2013-08-21 18:51:13 +0200 106)             mWebViewClient = new SsoWebViewClient(mHandler, mSsoWebViewClientListener);
37297fb7 (David A. Velasco 2013-08-21 18:51:13 +0200 107)             
37297fb7 (David A. Velasco 2013-08-21 18:51:13 +0200 108)        } catch (ClassCastException e) {
37297fb7 (David A. Velasco 2013-08-21 18:51:13 +0200 109)             throw new ClassCastException(activity.toString() + " must implement " + SsoWebViewClientListener.class.getSimpleName());
37297fb7 (David A. Velasco 2013-08-21 18:51:13 +0200 110)         }
9dacdbec (masensio         2013-08-13 12:08:46 +0200 111)     }
9dacdbec (masensio         2013-08-13 12:08:46 +0200 112) 
37297fb7 (David A. Velasco 2013-08-21 18:51:13 +0200 113)     
55f2bd23 (masensio         2013-08-13 13:28:45 +0200 114)     @SuppressLint("SetJavaScriptEnabled")
37297fb7 (David A. Velasco 2013-08-21 18:51:13 +0200 115)     @Override
37297fb7 (David A. Velasco 2013-08-21 18:51:13 +0200 116)     public void onCreate(Bundle savedInstanceState) {
37297fb7 (David A. Velasco 2013-08-21 18:51:13 +0200 117)         Log_OC.d(TAG, "onCreate");
37297fb7 (David A. Velasco 2013-08-21 18:51:13 +0200 118)         super.onCreate(savedInstanceState);
b2f18e0f (David A. Velasco 2013-08-22 19:03:21 +0200 119)         
b2f18e0f (David A. Velasco 2013-08-22 19:03:21 +0200 120)         CookieSyncManager.createInstance(getActivity());
37297fb7 (David A. Velasco 2013-08-21 18:51:13 +0200 121) 
37297fb7 (David A. Velasco 2013-08-21 18:51:13 +0200 122)         if (savedInstanceState == null) {
37297fb7 (David A. Velasco 2013-08-21 18:51:13 +0200 123)             mInitialUrl = getArguments().getString(ARG_INITIAL_URL);
37297fb7 (David A. Velasco 2013-08-21 18:51:13 +0200 124)             mTargetUrl = getArguments().getString(ARG_TARGET_URL);
37297fb7 (David A. Velasco 2013-08-21 18:51:13 +0200 125)         } else {
37297fb7 (David A. Velasco 2013-08-21 18:51:13 +0200 126)             mInitialUrl = savedInstanceState.getString(ARG_INITIAL_URL);
37297fb7 (David A. Velasco 2013-08-21 18:51:13 +0200 127)             mTargetUrl = savedInstanceState.getString(ARG_TARGET_URL);
37297fb7 (David A. Velasco 2013-08-21 18:51:13 +0200 128)         }
37297fb7 (David A. Velasco 2013-08-21 18:51:13 +0200 129)         
37297fb7 (David A. Velasco 2013-08-21 18:51:13 +0200 130)         setStyle(SherlockDialogFragment.STYLE_NO_TITLE, R.style.Theme_ownCloud_Dialog);
37297fb7 (David A. Velasco 2013-08-21 18:51:13 +0200 131)     }
37297fb7 (David A. Velasco 2013-08-21 18:51:13 +0200 132)     
37297fb7 (David A. Velasco 2013-08-21 18:51:13 +0200 133)     @Override
9dacdbec (masensio         2013-08-13 12:08:46 +0200 134)     public Dialog onCreateDialog(Bundle savedInstanceState) {
37297fb7 (David A. Velasco 2013-08-21 18:51:13 +0200 135)         Log_OC.d(TAG, "onCreateDialog");
9dacdbec (masensio         2013-08-13 12:08:46 +0200 136) 
37297fb7 (David A. Velasco 2013-08-21 18:51:13 +0200 137)         /*
37297fb7 (David A. Velasco 2013-08-21 18:51:13 +0200 138)         // build the dialog
37297fb7 (David A. Velasco 2013-08-21 18:51:13 +0200 139)         AlertDialog.Builder builder = new AlertDialog.Builder(getSherlockActivity());
37297fb7 (David A. Velasco 2013-08-21 18:51:13 +0200 140)         if (mSsoRootView.getParent() != null) {
37297fb7 (David A. Velasco 2013-08-21 18:51:13 +0200 141)             ((ViewGroup)(mSsoRootView.getParent())).removeView(mSsoRootView);
37297fb7 (David A. Velasco 2013-08-21 18:51:13 +0200 142)         }
37297fb7 (David A. Velasco 2013-08-21 18:51:13 +0200 143)         builder.setView(mSsoRootView);
37297fb7 (David A. Velasco 2013-08-21 18:51:13 +0200 144)         //builder.setView(mSsoWebView);
37297fb7 (David A. Velasco 2013-08-21 18:51:13 +0200 145)         Dialog dialog = builder.create();
37297fb7 (David A. Velasco 2013-08-21 18:51:13 +0200 146)         */
55f2bd23 (masensio         2013-08-13 13:28:45 +0200 147)         
37297fb7 (David A. Velasco 2013-08-21 18:51:13 +0200 148)         return super.onCreateDialog(savedInstanceState);
37297fb7 (David A. Velasco 2013-08-21 18:51:13 +0200 149)     }
55f2bd23 (masensio         2013-08-13 13:28:45 +0200 150) 
37297fb7 (David A. Velasco 2013-08-21 18:51:13 +0200 151)     @SuppressLint("SetJavaScriptEnabled")
37297fb7 (David A. Velasco 2013-08-21 18:51:13 +0200 152)     @Override
37297fb7 (David A. Velasco 2013-08-21 18:51:13 +0200 153)     public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
37297fb7 (David A. Velasco 2013-08-21 18:51:13 +0200 154)         Log_OC.d(TAG, "onCreateView");
55f2bd23 (masensio         2013-08-13 13:28:45 +0200 155)         
37297fb7 (David A. Velasco 2013-08-21 18:51:13 +0200 156)         // Inflate layout of the dialog  
37297fb7 (David A. Velasco 2013-08-21 18:51:13 +0200 157)         View rootView = inflater.inflate(R.layout.sso_dialog, container, false);  // null parent view because it will go in the dialog layout
b6213a0d (David A. Velasco 2013-08-22 11:48:57 +0200 158)         mSsoWebView  = (WebView) rootView.findViewById(R.id.sso_webview);
37297fb7 (David A. Velasco 2013-08-21 18:51:13 +0200 159)             
55f2bd23 (masensio         2013-08-13 13:28:45 +0200 160)         mWebViewClient.setTargetUrl(mTargetUrl);
b6213a0d (David A. Velasco 2013-08-22 11:48:57 +0200 161)         mSsoWebView.setWebViewClient(mWebViewClient);
55f2bd23 (masensio         2013-08-13 13:28:45 +0200 162)         
37297fb7 (David A. Velasco 2013-08-21 18:51:13 +0200 163)         if (savedInstanceState == null) {
37297fb7 (David A. Velasco 2013-08-21 18:51:13 +0200 164)             Log_OC.d(TAG,  "   initWebView start");
37297fb7 (David A. Velasco 2013-08-21 18:51:13 +0200 165)             CookieManager cookieManager = CookieManager.getInstance();
37297fb7 (David A. Velasco 2013-08-21 18:51:13 +0200 166)             cookieManager.setAcceptCookie(true);
37297fb7 (David A. Velasco 2013-08-21 18:51:13 +0200 167)             cookieManager.removeAllCookie();
b6213a0d (David A. Velasco 2013-08-22 11:48:57 +0200 168)             mSsoWebView.loadUrl(mInitialUrl);
37297fb7 (David A. Velasco 2013-08-21 18:51:13 +0200 169)             
37297fb7 (David A. Velasco 2013-08-21 18:51:13 +0200 170)         } else {
37297fb7 (David A. Velasco 2013-08-21 18:51:13 +0200 171)             Log_OC.d(TAG, "   restoreWebView start");
b6213a0d (David A. Velasco 2013-08-22 11:48:57 +0200 172)             WebBackForwardList history = mSsoWebView.restoreState(savedInstanceState.getBundle(KEY_WEBVIEW_STATE));
37297fb7 (David A. Velasco 2013-08-21 18:51:13 +0200 173)             if (history == null) {
37297fb7 (David A. Velasco 2013-08-21 18:51:13 +0200 174)                 Log_OC.e(TAG, "Error restoring WebView state ; back to starting URL");
b6213a0d (David A. Velasco 2013-08-22 11:48:57 +0200 175)                 mSsoWebView.loadUrl(mInitialUrl);
37297fb7 (David A. Velasco 2013-08-21 18:51:13 +0200 176)             }
37297fb7 (David A. Velasco 2013-08-21 18:51:13 +0200 177)         }
37297fb7 (David A. Velasco 2013-08-21 18:51:13 +0200 178) 
b6213a0d (David A. Velasco 2013-08-22 11:48:57 +0200 179)         WebSettings webSettings = mSsoWebView.getSettings();
55f2bd23 (masensio         2013-08-13 13:28:45 +0200 180)         webSettings.setJavaScriptEnabled(true);
55f2bd23 (masensio         2013-08-13 13:28:45 +0200 181)         webSettings.setBuiltInZoomControls(true);
55f2bd23 (masensio         2013-08-13 13:28:45 +0200 182)         webSettings.setLoadWithOverviewMode(false);
55f2bd23 (masensio         2013-08-13 13:28:45 +0200 183)         webSettings.setSavePassword(false);
55f2bd23 (masensio         2013-08-13 13:28:45 +0200 184)         webSettings.setUserAgentString(WebdavClient.USER_AGENT);
37297fb7 (David A. Velasco 2013-08-21 18:51:13 +0200 185)         webSettings.setSaveFormData(false);
55f2bd23 (masensio         2013-08-13 13:28:45 +0200 186)         
37297fb7 (David A. Velasco 2013-08-21 18:51:13 +0200 187)         return rootView;
37297fb7 (David A. Velasco 2013-08-21 18:51:13 +0200 188)     }
37297fb7 (David A. Velasco 2013-08-21 18:51:13 +0200 189) 
37297fb7 (David A. Velasco 2013-08-21 18:51:13 +0200 190)     @Override
37297fb7 (David A. Velasco 2013-08-21 18:51:13 +0200 191)     public void onSaveInstanceState(Bundle outState) {
37297fb7 (David A. Velasco 2013-08-21 18:51:13 +0200 192)         Log_OC.d(SAML_DIALOG_TAG, "onSaveInstanceState being CALLED");
37297fb7 (David A. Velasco 2013-08-21 18:51:13 +0200 193)         super.onSaveInstanceState(outState);
9dacdbec (masensio         2013-08-13 12:08:46 +0200 194)         
37297fb7 (David A. Velasco 2013-08-21 18:51:13 +0200 195)         // save URLs
37297fb7 (David A. Velasco 2013-08-21 18:51:13 +0200 196)         outState.putString(ARG_INITIAL_URL, mInitialUrl);
37297fb7 (David A. Velasco 2013-08-21 18:51:13 +0200 197)         outState.putString(ARG_TARGET_URL, mTargetUrl);
9dacdbec (masensio         2013-08-13 12:08:46 +0200 198)         
37297fb7 (David A. Velasco 2013-08-21 18:51:13 +0200 199)         // Save the state of the WebView
37297fb7 (David A. Velasco 2013-08-21 18:51:13 +0200 200)         Bundle webviewState = new Bundle();
b6213a0d (David A. Velasco 2013-08-22 11:48:57 +0200 201)         mSsoWebView.saveState(webviewState);
37297fb7 (David A. Velasco 2013-08-21 18:51:13 +0200 202)         outState.putBundle(KEY_WEBVIEW_STATE, webviewState);
9dacdbec (masensio         2013-08-13 12:08:46 +0200 203)     }
9dacdbec (masensio         2013-08-13 12:08:46 +0200 204) 
37297fb7 (David A. Velasco 2013-08-21 18:51:13 +0200 205)     @Override
37297fb7 (David A. Velasco 2013-08-21 18:51:13 +0200 206)     public void onDestroyView() {
37297fb7 (David A. Velasco 2013-08-21 18:51:13 +0200 207)         Log_OC.d(TAG, "onDestroyView");
37297fb7 (David A. Velasco 2013-08-21 18:51:13 +0200 208)         
b6213a0d (David A. Velasco 2013-08-22 11:48:57 +0200 209)         mSsoWebView.setWebViewClient(null);
b6213a0d (David A. Velasco 2013-08-22 11:48:57 +0200 210)         
37297fb7 (David A. Velasco 2013-08-21 18:51:13 +0200 211)         // Work around bug: http://code.google.com/p/android/issues/detail?id=17423
37297fb7 (David A. Velasco 2013-08-21 18:51:13 +0200 212)         Dialog dialog = getDialog();
37297fb7 (David A. Velasco 2013-08-21 18:51:13 +0200 213)         if ((dialog != null)) {
37297fb7 (David A. Velasco 2013-08-21 18:51:13 +0200 214)             dialog.setOnDismissListener(null);
37297fb7 (David A. Velasco 2013-08-21 18:51:13 +0200 215)             //dialog.dismiss();
37297fb7 (David A. Velasco 2013-08-21 18:51:13 +0200 216)             //dialog.setDismissMessage(null);
37297fb7 (David A. Velasco 2013-08-21 18:51:13 +0200 217)         }
37297fb7 (David A. Velasco 2013-08-21 18:51:13 +0200 218)         
37297fb7 (David A. Velasco 2013-08-21 18:51:13 +0200 219)         super.onDestroyView();
37297fb7 (David A. Velasco 2013-08-21 18:51:13 +0200 220)     }
37297fb7 (David A. Velasco 2013-08-21 18:51:13 +0200 221)     
37297fb7 (David A. Velasco 2013-08-21 18:51:13 +0200 222)     @Override
37297fb7 (David A. Velasco 2013-08-21 18:51:13 +0200 223)     public void onDestroy() {
37297fb7 (David A. Velasco 2013-08-21 18:51:13 +0200 224)         Log_OC.d(TAG, "onDestroy");
37297fb7 (David A. Velasco 2013-08-21 18:51:13 +0200 225)         super.onDestroy();
9dacdbec (masensio         2013-08-13 12:08:46 +0200 226)     }
9dacdbec (masensio         2013-08-13 12:08:46 +0200 227) 
37297fb7 (David A. Velasco 2013-08-21 18:51:13 +0200 228)     @Override
37297fb7 (David A. Velasco 2013-08-21 18:51:13 +0200 229)     public void onDetach() {
37297fb7 (David A. Velasco 2013-08-21 18:51:13 +0200 230)         Log_OC.d(TAG, "onDetach");
37297fb7 (David A. Velasco 2013-08-21 18:51:13 +0200 231)         mSsoWebViewClientListener = null;
37297fb7 (David A. Velasco 2013-08-21 18:51:13 +0200 232)         mWebViewClient = null;
37297fb7 (David A. Velasco 2013-08-21 18:51:13 +0200 233)         super.onDetach();
55f2bd23 (masensio         2013-08-13 13:28:45 +0200 234)     }
55f2bd23 (masensio         2013-08-13 13:28:45 +0200 235)     
37297fb7 (David A. Velasco 2013-08-21 18:51:13 +0200 236)     @Override
37297fb7 (David A. Velasco 2013-08-21 18:51:13 +0200 237)     public void onCancel (DialogInterface dialog) {
37297fb7 (David A. Velasco 2013-08-21 18:51:13 +0200 238)         Log_OC.d(SAML_DIALOG_TAG, "onCancel");
37297fb7 (David A. Velasco 2013-08-21 18:51:13 +0200 239)         super.onCancel(dialog);
37297fb7 (David A. Velasco 2013-08-21 18:51:13 +0200 240)     }
55f2bd23 (masensio         2013-08-13 13:28:45 +0200 241)     
9dacdbec (masensio         2013-08-13 12:08:46 +0200 242)     @Override
37297fb7 (David A. Velasco 2013-08-21 18:51:13 +0200 243)     public void onDismiss (DialogInterface dialog) {
37297fb7 (David A. Velasco 2013-08-21 18:51:13 +0200 244)         Log_OC.d(SAML_DIALOG_TAG, "onDismiss");
37297fb7 (David A. Velasco 2013-08-21 18:51:13 +0200 245)         super.onDismiss(dialog);
37297fb7 (David A. Velasco 2013-08-21 18:51:13 +0200 246)     }
37297fb7 (David A. Velasco 2013-08-21 18:51:13 +0200 247)     
37297fb7 (David A. Velasco 2013-08-21 18:51:13 +0200 248)     @Override
37297fb7 (David A. Velasco 2013-08-21 18:51:13 +0200 249)     public void onStart() {
37297fb7 (David A. Velasco 2013-08-21 18:51:13 +0200 250)         Log_OC.d(SAML_DIALOG_TAG, "onStart");
37297fb7 (David A. Velasco 2013-08-21 18:51:13 +0200 251)         super.onStart();
37297fb7 (David A. Velasco 2013-08-21 18:51:13 +0200 252)     }
9dacdbec (masensio         2013-08-13 12:08:46 +0200 253) 
37297fb7 (David A. Velasco 2013-08-21 18:51:13 +0200 254)     @Override
37297fb7 (David A. Velasco 2013-08-21 18:51:13 +0200 255)     public void onStop() {
37297fb7 (David A. Velasco 2013-08-21 18:51:13 +0200 256)         Log_OC.d(SAML_DIALOG_TAG, "onStop");
37297fb7 (David A. Velasco 2013-08-21 18:51:13 +0200 257)         super.onStop();
9dacdbec (masensio         2013-08-13 12:08:46 +0200 258)     }
9dacdbec (masensio         2013-08-13 12:08:46 +0200 259) 
37297fb7 (David A. Velasco 2013-08-21 18:51:13 +0200 260)     @Override
37297fb7 (David A. Velasco 2013-08-21 18:51:13 +0200 261)     public void onResume() {
37297fb7 (David A. Velasco 2013-08-21 18:51:13 +0200 262)         Log_OC.d(SAML_DIALOG_TAG, "onResume");
37297fb7 (David A. Velasco 2013-08-21 18:51:13 +0200 263)         super.onResume();
37297fb7 (David A. Velasco 2013-08-21 18:51:13 +0200 264)     }
9dacdbec (masensio         2013-08-13 12:08:46 +0200 265) 
9dacdbec (masensio         2013-08-13 12:08:46 +0200 266)     @Override
37297fb7 (David A. Velasco 2013-08-21 18:51:13 +0200 267)     public void onPause() {
37297fb7 (David A. Velasco 2013-08-21 18:51:13 +0200 268)         Log_OC.d(SAML_DIALOG_TAG, "onPause");
37297fb7 (David A. Velasco 2013-08-21 18:51:13 +0200 269)         super.onPause();
37297fb7 (David A. Velasco 2013-08-21 18:51:13 +0200 270)     }
37297fb7 (David A. Velasco 2013-08-21 18:51:13 +0200 271)     
37297fb7 (David A. Velasco 2013-08-21 18:51:13 +0200 272)     @Override
37297fb7 (David A. Velasco 2013-08-21 18:51:13 +0200 273)     public int show (FragmentTransaction transaction, String tag) {
37297fb7 (David A. Velasco 2013-08-21 18:51:13 +0200 274)         Log_OC.d(SAML_DIALOG_TAG, "show (transaction)");
37297fb7 (David A. Velasco 2013-08-21 18:51:13 +0200 275)         return super.show(transaction, tag);
9dacdbec (masensio         2013-08-13 12:08:46 +0200 276)     }
37297fb7 (David A. Velasco 2013-08-21 18:51:13 +0200 277) 
37297fb7 (David A. Velasco 2013-08-21 18:51:13 +0200 278)     @Override
37297fb7 (David A. Velasco 2013-08-21 18:51:13 +0200 279)     public void show (FragmentManager manager, String tag) {
37297fb7 (David A. Velasco 2013-08-21 18:51:13 +0200 280)         Log_OC.d(SAML_DIALOG_TAG, "show (manager)");
37297fb7 (David A. Velasco 2013-08-21 18:51:13 +0200 281)         super.show(manager, tag);
37297fb7 (David A. Velasco 2013-08-21 18:51:13 +0200 282)     }
37297fb7 (David A. Velasco 2013-08-21 18:51:13 +0200 283) 
9dacdbec (masensio         2013-08-13 12:08:46 +0200 284) }
