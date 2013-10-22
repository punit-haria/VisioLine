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
bb257ec7 (David A. Velasco 2013-02-07 18:45:10 +0100  17) 
48f13c8a (David A. Velasco 2012-09-05 13:46:30 +0200  18) package com.owncloud.android.ui.dialog;
48f13c8a (David A. Velasco 2012-09-05 13:46:30 +0200  19) 
48f13c8a (David A. Velasco 2012-09-05 13:46:30 +0200  20) import java.io.IOException;
04803ae8 (David A. Velasco 2013-06-26 12:45:35 +0200  21) import java.security.GeneralSecurityException;
48f13c8a (David A. Velasco 2012-09-05 13:46:30 +0200  22) import java.security.KeyStoreException;
48f13c8a (David A. Velasco 2012-09-05 13:46:30 +0200  23) import java.security.NoSuchAlgorithmException;
48f13c8a (David A. Velasco 2012-09-05 13:46:30 +0200  24) import java.security.cert.CertificateException;
48f13c8a (David A. Velasco 2012-09-05 13:46:30 +0200  25) import java.security.cert.X509Certificate;
cc1eb7a3 (David A. Velasco 2012-10-29 14:19:56 +0100  26) import java.util.Date;
cc1eb7a3 (David A. Velasco 2012-10-29 14:19:56 +0100  27) import java.util.HashMap;
cc1eb7a3 (David A. Velasco 2012-10-29 14:19:56 +0100  28) import java.util.Map;
cc1eb7a3 (David A. Velasco 2012-10-29 14:19:56 +0100  29) 
cc1eb7a3 (David A. Velasco 2012-10-29 14:19:56 +0100  30) import javax.security.auth.x500.X500Principal;
48f13c8a (David A. Velasco 2012-09-05 13:46:30 +0200  31) 
48f13c8a (David A. Velasco 2012-09-05 13:46:30 +0200  32) import android.app.Dialog;
48f13c8a (David A. Velasco 2012-09-05 13:46:30 +0200  33) import android.content.Context;
48f13c8a (David A. Velasco 2012-09-05 13:46:30 +0200  34) import android.os.Bundle;
48f13c8a (David A. Velasco 2012-09-05 13:46:30 +0200  35) import android.view.View;
48f13c8a (David A. Velasco 2012-09-05 13:46:30 +0200  36) import android.view.Window;
cc1eb7a3 (David A. Velasco 2012-10-29 14:19:56 +0100  37) import android.widget.Button;
48f13c8a (David A. Velasco 2012-09-05 13:46:30 +0200  38) import android.widget.TextView;
48f13c8a (David A. Velasco 2012-09-05 13:46:30 +0200  39) 
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100  40) import com.owncloud.android.Log_OC;
48f13c8a (David A. Velasco 2012-09-05 13:46:30 +0200  41) import com.owncloud.android.R;
261aaf50 (David A. Velasco 2012-09-07 14:11:08 +0200  42) import com.owncloud.android.network.CertificateCombinedException;
48f13c8a (David A. Velasco 2012-09-05 13:46:30 +0200  43) import com.owncloud.android.network.OwnCloudClientUtils;
48f13c8a (David A. Velasco 2012-09-05 13:46:30 +0200  44) import com.owncloud.android.operations.RemoteOperationResult;
48f13c8a (David A. Velasco 2012-09-05 13:46:30 +0200  45) 
48f13c8a (David A. Velasco 2012-09-05 13:46:30 +0200  46) /**
48f13c8a (David A. Velasco 2012-09-05 13:46:30 +0200  47)  * Dialog to request the user about a certificate that could not be validated with the certificates store in the system.
48f13c8a (David A. Velasco 2012-09-05 13:46:30 +0200  48)  * 
48f13c8a (David A. Velasco 2012-09-05 13:46:30 +0200  49)  * @author David A. Velasco
48f13c8a (David A. Velasco 2012-09-05 13:46:30 +0200  50)  */
48f13c8a (David A. Velasco 2012-09-05 13:46:30 +0200  51) public class SslValidatorDialog extends Dialog {
48f13c8a (David A. Velasco 2012-09-05 13:46:30 +0200  52) 
48f13c8a (David A. Velasco 2012-09-05 13:46:30 +0200  53)     private final static String TAG = SslValidatorDialog.class.getSimpleName();
48f13c8a (David A. Velasco 2012-09-05 13:46:30 +0200  54) 
48f13c8a (David A. Velasco 2012-09-05 13:46:30 +0200  55)     private OnSslValidatorListener mListener;
261aaf50 (David A. Velasco 2012-09-07 14:11:08 +0200  56)     private CertificateCombinedException mException = null;
48f13c8a (David A. Velasco 2012-09-05 13:46:30 +0200  57)     private View mView;
48f13c8a (David A. Velasco 2012-09-05 13:46:30 +0200  58)     
48f13c8a (David A. Velasco 2012-09-05 13:46:30 +0200  59)     
48f13c8a (David A. Velasco 2012-09-05 13:46:30 +0200  60)     /**
48f13c8a (David A. Velasco 2012-09-05 13:46:30 +0200  61)      * Creates a new SslValidatorDialog to ask the user if an untrusted certificate from a server should
48f13c8a (David A. Velasco 2012-09-05 13:46:30 +0200  62)      * be trusted.
48f13c8a (David A. Velasco 2012-09-05 13:46:30 +0200  63)      * 
48f13c8a (David A. Velasco 2012-09-05 13:46:30 +0200  64)      * @param context       Android context where the dialog will live.
48f13c8a (David A. Velasco 2012-09-05 13:46:30 +0200  65)      * @param result        Result of a failed remote operation.
48f13c8a (David A. Velasco 2012-09-05 13:46:30 +0200  66)      * @param listener      Object to notice when the server certificate was added to the local certificates store.
bdc0332c (David A. Velasco 2013-04-17 12:26:13 +0200  67)      * @return              A new SslValidatorDialog instance. NULL if the operation can not be recovered
48f13c8a (David A. Velasco 2012-09-05 13:46:30 +0200  68)      *                      by setting the certificate as reliable.
48f13c8a (David A. Velasco 2012-09-05 13:46:30 +0200  69)      */
48f13c8a (David A. Velasco 2012-09-05 13:46:30 +0200  70)     public static SslValidatorDialog newInstance(Context context, RemoteOperationResult result, OnSslValidatorListener listener) {
eda72431 (David A. Velasco 2012-09-14 11:33:25 +0200  71)         if (result != null && result.isSslRecoverableException()) {
48f13c8a (David A. Velasco 2012-09-05 13:46:30 +0200  72)             SslValidatorDialog dialog = new SslValidatorDialog(context, listener);
48f13c8a (David A. Velasco 2012-09-05 13:46:30 +0200  73)             return dialog;
48f13c8a (David A. Velasco 2012-09-05 13:46:30 +0200  74)         } else {
48f13c8a (David A. Velasco 2012-09-05 13:46:30 +0200  75)             return null;
48f13c8a (David A. Velasco 2012-09-05 13:46:30 +0200  76)         }
48f13c8a (David A. Velasco 2012-09-05 13:46:30 +0200  77)     }
48f13c8a (David A. Velasco 2012-09-05 13:46:30 +0200  78) 
48f13c8a (David A. Velasco 2012-09-05 13:46:30 +0200  79)     /**
48f13c8a (David A. Velasco 2012-09-05 13:46:30 +0200  80)      * Private constructor. 
48f13c8a (David A. Velasco 2012-09-05 13:46:30 +0200  81)      * 
48f13c8a (David A. Velasco 2012-09-05 13:46:30 +0200  82)      * Instances have to be created through static {@link SslValidatorDialog#newInstance}.
48f13c8a (David A. Velasco 2012-09-05 13:46:30 +0200  83)      * 
48f13c8a (David A. Velasco 2012-09-05 13:46:30 +0200  84)      * @param context       Android context where the dialog will live
48f13c8a (David A. Velasco 2012-09-05 13:46:30 +0200  85)      * @param e             Exception causing the need of prompt the user about the server certificate.
48f13c8a (David A. Velasco 2012-09-05 13:46:30 +0200  86)      * @param listener      Object to notice when the server certificate was added to the local certificates store.
48f13c8a (David A. Velasco 2012-09-05 13:46:30 +0200  87)      */
48f13c8a (David A. Velasco 2012-09-05 13:46:30 +0200  88)     private SslValidatorDialog(Context context, OnSslValidatorListener listener) {
48f13c8a (David A. Velasco 2012-09-05 13:46:30 +0200  89)         super(context);
48f13c8a (David A. Velasco 2012-09-05 13:46:30 +0200  90)         mListener = listener;
48f13c8a (David A. Velasco 2012-09-05 13:46:30 +0200  91)     }
48f13c8a (David A. Velasco 2012-09-05 13:46:30 +0200  92)     
48f13c8a (David A. Velasco 2012-09-05 13:46:30 +0200  93)     
48f13c8a (David A. Velasco 2012-09-05 13:46:30 +0200  94)     /**
48f13c8a (David A. Velasco 2012-09-05 13:46:30 +0200  95)      * {@inheritDoc}
48f13c8a (David A. Velasco 2012-09-05 13:46:30 +0200  96)      */
48f13c8a (David A. Velasco 2012-09-05 13:46:30 +0200  97)     protected void onCreate(Bundle savedInstanceState) {
48f13c8a (David A. Velasco 2012-09-05 13:46:30 +0200  98)         super.onCreate(savedInstanceState);
48f13c8a (David A. Velasco 2012-09-05 13:46:30 +0200  99)         requestWindowFeature(Window.FEATURE_NO_TITLE);
48f13c8a (David A. Velasco 2012-09-05 13:46:30 +0200 100)         mView = getLayoutInflater().inflate(R.layout.ssl_validator_layout, null);
48f13c8a (David A. Velasco 2012-09-05 13:46:30 +0200 101)         setContentView(mView); 
48f13c8a (David A. Velasco 2012-09-05 13:46:30 +0200 102)         
48f13c8a (David A. Velasco 2012-09-05 13:46:30 +0200 103)         mView.findViewById(R.id.ok).setOnClickListener( 
48f13c8a (David A. Velasco 2012-09-05 13:46:30 +0200 104)                 new View.OnClickListener() {
48f13c8a (David A. Velasco 2012-09-05 13:46:30 +0200 105)                     @Override
48f13c8a (David A. Velasco 2012-09-05 13:46:30 +0200 106)                     public void onClick(View v) {
48f13c8a (David A. Velasco 2012-09-05 13:46:30 +0200 107)                         try {
48f13c8a (David A. Velasco 2012-09-05 13:46:30 +0200 108)                             saveServerCert();
48f13c8a (David A. Velasco 2012-09-05 13:46:30 +0200 109)                             dismiss();
48f13c8a (David A. Velasco 2012-09-05 13:46:30 +0200 110)                             if (mListener != null)
48f13c8a (David A. Velasco 2012-09-05 13:46:30 +0200 111)                                 mListener.onSavedCertificate();
48f13c8a (David A. Velasco 2012-09-05 13:46:30 +0200 112)                             else
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100 113)                                 Log_OC.d(TAG, "Nobody there to notify the certificate was saved");
48f13c8a (David A. Velasco 2012-09-05 13:46:30 +0200 114)                             
04803ae8 (David A. Velasco 2013-06-26 12:45:35 +0200 115)                         } catch (GeneralSecurityException e) {
04803ae8 (David A. Velasco 2013-06-26 12:45:35 +0200 116)                             dismiss();
04803ae8 (David A. Velasco 2013-06-26 12:45:35 +0200 117)                             if (mListener != null)
04803ae8 (David A. Velasco 2013-06-26 12:45:35 +0200 118)                                 mListener.onFailedSavingCertificate();
04803ae8 (David A. Velasco 2013-06-26 12:45:35 +0200 119)                             Log_OC.e(TAG, "Server certificate could not be saved in the known servers trust store ", e);
04803ae8 (David A. Velasco 2013-06-26 12:45:35 +0200 120)                             
04803ae8 (David A. Velasco 2013-06-26 12:45:35 +0200 121)                         } catch (IOException e) {
48f13c8a (David A. Velasco 2012-09-05 13:46:30 +0200 122)                             dismiss();
48f13c8a (David A. Velasco 2012-09-05 13:46:30 +0200 123)                             if (mListener != null)
48f13c8a (David A. Velasco 2012-09-05 13:46:30 +0200 124)                                 mListener.onFailedSavingCertificate();
8e36e7cc (zerginator       2013-03-12 07:56:27 +0100 125)                             Log_OC.e(TAG, "Server certificate could not be saved in the known servers trust store ", e);
48f13c8a (David A. Velasco 2012-09-05 13:46:30 +0200 126)                         }
48f13c8a (David A. Velasco 2012-09-05 13:46:30 +0200 127)                     }
48f13c8a (David A. Velasco 2012-09-05 13:46:30 +0200 128)                 });
48f13c8a (David A. Velasco 2012-09-05 13:46:30 +0200 129)         
48f13c8a (David A. Velasco 2012-09-05 13:46:30 +0200 130)         mView.findViewById(R.id.cancel).setOnClickListener(
48f13c8a (David A. Velasco 2012-09-05 13:46:30 +0200 131)                 new View.OnClickListener() {
48f13c8a (David A. Velasco 2012-09-05 13:46:30 +0200 132)                     @Override
48f13c8a (David A. Velasco 2012-09-05 13:46:30 +0200 133)                     public void onClick(View v) {
48f13c8a (David A. Velasco 2012-09-05 13:46:30 +0200 134)                         cancel();
48f13c8a (David A. Velasco 2012-09-05 13:46:30 +0200 135)                     }
48f13c8a (David A. Velasco 2012-09-05 13:46:30 +0200 136)                 });
cc1eb7a3 (David A. Velasco 2012-10-29 14:19:56 +0100 137)         
cc1eb7a3 (David A. Velasco 2012-10-29 14:19:56 +0100 138)         mView.findViewById(R.id.details_btn).setOnClickListener(
cc1eb7a3 (David A. Velasco 2012-10-29 14:19:56 +0100 139)                 new View.OnClickListener() {
cc1eb7a3 (David A. Velasco 2012-10-29 14:19:56 +0100 140)                    @Override
cc1eb7a3 (David A. Velasco 2012-10-29 14:19:56 +0100 141)                     public void onClick(View v) {
cc1eb7a3 (David A. Velasco 2012-10-29 14:19:56 +0100 142)                        View detailsScroll = findViewById(R.id.details_scroll);
cc1eb7a3 (David A. Velasco 2012-10-29 14:19:56 +0100 143)                        if (detailsScroll.getVisibility() == View.VISIBLE) {
cc1eb7a3 (David A. Velasco 2012-10-29 14:19:56 +0100 144)                            detailsScroll.setVisibility(View.GONE);
cc1eb7a3 (David A. Velasco 2012-10-29 14:19:56 +0100 145)                            ((Button)v).setText(R.string.ssl_validator_btn_details_see);
cc1eb7a3 (David A. Velasco 2012-10-29 14:19:56 +0100 146)                            
cc1eb7a3 (David A. Velasco 2012-10-29 14:19:56 +0100 147)                        } else {
cc1eb7a3 (David A. Velasco 2012-10-29 14:19:56 +0100 148)                            detailsScroll.setVisibility(View.VISIBLE);
cc1eb7a3 (David A. Velasco 2012-10-29 14:19:56 +0100 149)                            ((Button)v).setText(R.string.ssl_validator_btn_details_hide);
cc1eb7a3 (David A. Velasco 2012-10-29 14:19:56 +0100 150)                        }
cc1eb7a3 (David A. Velasco 2012-10-29 14:19:56 +0100 151)                     }
cc1eb7a3 (David A. Velasco 2012-10-29 14:19:56 +0100 152)                 });
48f13c8a (David A. Velasco 2012-09-05 13:46:30 +0200 153)     }
48f13c8a (David A. Velasco 2012-09-05 13:46:30 +0200 154)     
48f13c8a (David A. Velasco 2012-09-05 13:46:30 +0200 155)     
48f13c8a (David A. Velasco 2012-09-05 13:46:30 +0200 156)     public void updateResult(RemoteOperationResult result) {
bf3cf8cd (David A. Velasco 2012-09-13 17:07:17 +0200 157)         if (result.isSslRecoverableException()) {
bf3cf8cd (David A. Velasco 2012-09-13 17:07:17 +0200 158)             mException = (CertificateCombinedException) result.getException();
bf3cf8cd (David A. Velasco 2012-09-13 17:07:17 +0200 159)             
bf3cf8cd (David A. Velasco 2012-09-13 17:07:17 +0200 160)             /// clean
cc1eb7a3 (David A. Velasco 2012-10-29 14:19:56 +0100 161)             mView.findViewById(R.id.reason_cert_not_trusted).setVisibility(View.GONE);
cc1eb7a3 (David A. Velasco 2012-10-29 14:19:56 +0100 162)             mView.findViewById(R.id.reason_cert_expired).setVisibility(View.GONE);
cc1eb7a3 (David A. Velasco 2012-10-29 14:19:56 +0100 163)             mView.findViewById(R.id.reason_cert_not_yet_valid).setVisibility(View.GONE);
cc1eb7a3 (David A. Velasco 2012-10-29 14:19:56 +0100 164)             mView.findViewById(R.id.reason_hostname_not_verified).setVisibility(View.GONE);
cc1eb7a3 (David A. Velasco 2012-10-29 14:19:56 +0100 165)             mView.findViewById(R.id.details_scroll).setVisibility(View.GONE);
261aaf50 (David A. Velasco 2012-09-07 14:11:08 +0200 166) 
bf3cf8cd (David A. Velasco 2012-09-13 17:07:17 +0200 167)             /// refresh
261aaf50 (David A. Velasco 2012-09-07 14:11:08 +0200 168)             if (mException.getCertPathValidatorException() != null) {
261aaf50 (David A. Velasco 2012-09-07 14:11:08 +0200 169)                 ((TextView)mView.findViewById(R.id.reason_cert_not_trusted)).setVisibility(View.VISIBLE);
261aaf50 (David A. Velasco 2012-09-07 14:11:08 +0200 170)             }
48f13c8a (David A. Velasco 2012-09-05 13:46:30 +0200 171)             
261aaf50 (David A. Velasco 2012-09-07 14:11:08 +0200 172)             if (mException.getCertificateExpiredException() != null) {
261aaf50 (David A. Velasco 2012-09-07 14:11:08 +0200 173)                 ((TextView)mView.findViewById(R.id.reason_cert_expired)).setVisibility(View.VISIBLE);
261aaf50 (David A. Velasco 2012-09-07 14:11:08 +0200 174)             }
48f13c8a (David A. Velasco 2012-09-05 13:46:30 +0200 175)             
261aaf50 (David A. Velasco 2012-09-07 14:11:08 +0200 176)             if (mException.getCertificateNotYetValidException() != null) {
261aaf50 (David A. Velasco 2012-09-07 14:11:08 +0200 177)                 ((TextView)mView.findViewById(R.id.reason_cert_not_yet_valid)).setVisibility(View.VISIBLE);
261aaf50 (David A. Velasco 2012-09-07 14:11:08 +0200 178)             } 
261aaf50 (David A. Velasco 2012-09-07 14:11:08 +0200 179) 
261aaf50 (David A. Velasco 2012-09-07 14:11:08 +0200 180)             if (mException.getSslPeerUnverifiedException() != null ) {
261aaf50 (David A. Velasco 2012-09-07 14:11:08 +0200 181)                 ((TextView)mView.findViewById(R.id.reason_hostname_not_verified)).setVisibility(View.VISIBLE);
261aaf50 (David A. Velasco 2012-09-07 14:11:08 +0200 182)             }
48f13c8a (David A. Velasco 2012-09-05 13:46:30 +0200 183)             
261aaf50 (David A. Velasco 2012-09-07 14:11:08 +0200 184)             
261aaf50 (David A. Velasco 2012-09-07 14:11:08 +0200 185)             showCertificateData(mException.getServerCertificate());
48f13c8a (David A. Velasco 2012-09-05 13:46:30 +0200 186)         }
48f13c8a (David A. Velasco 2012-09-05 13:46:30 +0200 187)         
48f13c8a (David A. Velasco 2012-09-05 13:46:30 +0200 188)     }
48f13c8a (David A. Velasco 2012-09-05 13:46:30 +0200 189)     
261aaf50 (David A. Velasco 2012-09-07 14:11:08 +0200 190)     private void showCertificateData(X509Certificate cert) {
cc1eb7a3 (David A. Velasco 2012-10-29 14:19:56 +0100 191) 
261aaf50 (David A. Velasco 2012-09-07 14:11:08 +0200 192)         if (cert != null) {
cc1eb7a3 (David A. Velasco 2012-10-29 14:19:56 +0100 193)             showSubject(cert.getSubjectX500Principal());
cc1eb7a3 (David A. Velasco 2012-10-29 14:19:56 +0100 194)             showIssuer(cert.getIssuerX500Principal());
cc1eb7a3 (David A. Velasco 2012-10-29 14:19:56 +0100 195)             showValidity(cert.getNotBefore(), cert.getNotAfter());
cc1eb7a3 (David A. Velasco 2012-10-29 14:19:56 +0100 196)             showSignature(cert);
cc1eb7a3 (David A. Velasco 2012-10-29 14:19:56 +0100 197)             
bf3cf8cd (David A. Velasco 2012-09-13 17:07:17 +0200 198)         } else {
bf3cf8cd (David A. Velasco 2012-09-13 17:07:17 +0200 199)             // this should not happen
cc1eb7a3 (David A. Velasco 2012-10-29 14:19:56 +0100 200)             // TODO
cc1eb7a3 (David A. Velasco 2012-10-29 14:19:56 +0100 201)         }
cc1eb7a3 (David A. Velasco 2012-10-29 14:19:56 +0100 202)     }
cc1eb7a3 (David A. Velasco 2012-10-29 14:19:56 +0100 203) 
cc1eb7a3 (David A. Velasco 2012-10-29 14:19:56 +0100 204)     private void showSignature(X509Certificate cert) {
cc1eb7a3 (David A. Velasco 2012-10-29 14:19:56 +0100 205)         TextView sigView = ((TextView)mView.findViewById(R.id.value_signature));
cc1eb7a3 (David A. Velasco 2012-10-29 14:19:56 +0100 206)         TextView algorithmView = ((TextView)mView.findViewById(R.id.value_signature_algorithm));
cc1eb7a3 (David A. Velasco 2012-10-29 14:19:56 +0100 207)         sigView.setText(getHex(cert.getSignature()));
cc1eb7a3 (David A. Velasco 2012-10-29 14:19:56 +0100 208)         algorithmView.setText(cert.getSigAlgName());
cc1eb7a3 (David A. Velasco 2012-10-29 14:19:56 +0100 209)     }
cc1eb7a3 (David A. Velasco 2012-10-29 14:19:56 +0100 210)     
cc1eb7a3 (David A. Velasco 2012-10-29 14:19:56 +0100 211)     public String getHex(final byte [] raw) {
cc1eb7a3 (David A. Velasco 2012-10-29 14:19:56 +0100 212)         if (raw == null) {
cc1eb7a3 (David A. Velasco 2012-10-29 14:19:56 +0100 213)            return null;
cc1eb7a3 (David A. Velasco 2012-10-29 14:19:56 +0100 214)         }
cc1eb7a3 (David A. Velasco 2012-10-29 14:19:56 +0100 215)         final StringBuilder hex = new StringBuilder(2 * raw.length);
cc1eb7a3 (David A. Velasco 2012-10-29 14:19:56 +0100 216)         for (final byte b : raw) {
cc1eb7a3 (David A. Velasco 2012-10-29 14:19:56 +0100 217)            final int hiVal = (b & 0xF0) >> 4;
cc1eb7a3 (David A. Velasco 2012-10-29 14:19:56 +0100 218)            final int loVal = b & 0x0F;
cc1eb7a3 (David A. Velasco 2012-10-29 14:19:56 +0100 219)            hex.append((char) ('0' + (hiVal + (hiVal / 10 * 7))));
cc1eb7a3 (David A. Velasco 2012-10-29 14:19:56 +0100 220)            hex.append((char) ('0' + (loVal + (loVal / 10 * 7))));
cc1eb7a3 (David A. Velasco 2012-10-29 14:19:56 +0100 221)         }
cc1eb7a3 (David A. Velasco 2012-10-29 14:19:56 +0100 222)         return hex.toString();
cc1eb7a3 (David A. Velasco 2012-10-29 14:19:56 +0100 223)      }    
cc1eb7a3 (David A. Velasco 2012-10-29 14:19:56 +0100 224) 
cc1eb7a3 (David A. Velasco 2012-10-29 14:19:56 +0100 225)     private void showValidity(Date notBefore, Date notAfter) {
cc1eb7a3 (David A. Velasco 2012-10-29 14:19:56 +0100 226)         TextView fromView = ((TextView)mView.findViewById(R.id.value_validity_from));
cc1eb7a3 (David A. Velasco 2012-10-29 14:19:56 +0100 227)         TextView toView = ((TextView)mView.findViewById(R.id.value_validity_to));
cc1eb7a3 (David A. Velasco 2012-10-29 14:19:56 +0100 228)         fromView.setText(notBefore.toLocaleString());
cc1eb7a3 (David A. Velasco 2012-10-29 14:19:56 +0100 229)         toView.setText(notAfter.toLocaleString());
cc1eb7a3 (David A. Velasco 2012-10-29 14:19:56 +0100 230)     }
cc1eb7a3 (David A. Velasco 2012-10-29 14:19:56 +0100 231) 
cc1eb7a3 (David A. Velasco 2012-10-29 14:19:56 +0100 232)     private void showSubject(X500Principal subject) {
cc1eb7a3 (David A. Velasco 2012-10-29 14:19:56 +0100 233)         Map<String, String> s = parsePrincipal(subject);
cc1eb7a3 (David A. Velasco 2012-10-29 14:19:56 +0100 234)         TextView cnView = ((TextView)mView.findViewById(R.id.value_subject_CN));
cc1eb7a3 (David A. Velasco 2012-10-29 14:19:56 +0100 235)         TextView oView = ((TextView)mView.findViewById(R.id.value_subject_O));
cc1eb7a3 (David A. Velasco 2012-10-29 14:19:56 +0100 236)         TextView ouView = ((TextView)mView.findViewById(R.id.value_subject_OU));
cc1eb7a3 (David A. Velasco 2012-10-29 14:19:56 +0100 237)         TextView cView = ((TextView)mView.findViewById(R.id.value_subject_C));
cc1eb7a3 (David A. Velasco 2012-10-29 14:19:56 +0100 238)         TextView stView = ((TextView)mView.findViewById(R.id.value_subject_ST));
cc1eb7a3 (David A. Velasco 2012-10-29 14:19:56 +0100 239)         TextView lView = ((TextView)mView.findViewById(R.id.value_subject_L));
cc1eb7a3 (David A. Velasco 2012-10-29 14:19:56 +0100 240)         
cc1eb7a3 (David A. Velasco 2012-10-29 14:19:56 +0100 241)         if (s.get("CN") != null) {
cc1eb7a3 (David A. Velasco 2012-10-29 14:19:56 +0100 242)             cnView.setText(s.get("CN"));
cc1eb7a3 (David A. Velasco 2012-10-29 14:19:56 +0100 243)             cnView.setVisibility(View.VISIBLE);
cc1eb7a3 (David A. Velasco 2012-10-29 14:19:56 +0100 244)         } else {
cc1eb7a3 (David A. Velasco 2012-10-29 14:19:56 +0100 245)             cnView.setVisibility(View.GONE);
cc1eb7a3 (David A. Velasco 2012-10-29 14:19:56 +0100 246)         }
cc1eb7a3 (David A. Velasco 2012-10-29 14:19:56 +0100 247)         if (s.get("O") != null) {
cc1eb7a3 (David A. Velasco 2012-10-29 14:19:56 +0100 248)             oView.setText(s.get("O"));
cc1eb7a3 (David A. Velasco 2012-10-29 14:19:56 +0100 249)             oView.setVisibility(View.VISIBLE);
cc1eb7a3 (David A. Velasco 2012-10-29 14:19:56 +0100 250)         } else {
cc1eb7a3 (David A. Velasco 2012-10-29 14:19:56 +0100 251)             oView.setVisibility(View.GONE);
cc1eb7a3 (David A. Velasco 2012-10-29 14:19:56 +0100 252)         }
cc1eb7a3 (David A. Velasco 2012-10-29 14:19:56 +0100 253)         if (s.get("OU") != null) {
cc1eb7a3 (David A. Velasco 2012-10-29 14:19:56 +0100 254)             ouView.setText(s.get("OU"));
cc1eb7a3 (David A. Velasco 2012-10-29 14:19:56 +0100 255)             ouView.setVisibility(View.VISIBLE);
cc1eb7a3 (David A. Velasco 2012-10-29 14:19:56 +0100 256)         } else {
cc1eb7a3 (David A. Velasco 2012-10-29 14:19:56 +0100 257)             ouView.setVisibility(View.GONE);
cc1eb7a3 (David A. Velasco 2012-10-29 14:19:56 +0100 258)         }
cc1eb7a3 (David A. Velasco 2012-10-29 14:19:56 +0100 259)         if (s.get("C") != null) {
cc1eb7a3 (David A. Velasco 2012-10-29 14:19:56 +0100 260)             cView.setText(s.get("C"));
cc1eb7a3 (David A. Velasco 2012-10-29 14:19:56 +0100 261)             cView.setVisibility(View.VISIBLE);
cc1eb7a3 (David A. Velasco 2012-10-29 14:19:56 +0100 262)         } else {
cc1eb7a3 (David A. Velasco 2012-10-29 14:19:56 +0100 263)             cView.setVisibility(View.GONE);
cc1eb7a3 (David A. Velasco 2012-10-29 14:19:56 +0100 264)         }
cc1eb7a3 (David A. Velasco 2012-10-29 14:19:56 +0100 265)         if (s.get("ST") != null) {
cc1eb7a3 (David A. Velasco 2012-10-29 14:19:56 +0100 266)             stView.setText(s.get("ST"));
cc1eb7a3 (David A. Velasco 2012-10-29 14:19:56 +0100 267)             stView.setVisibility(View.VISIBLE);
cc1eb7a3 (David A. Velasco 2012-10-29 14:19:56 +0100 268)         } else {
cc1eb7a3 (David A. Velasco 2012-10-29 14:19:56 +0100 269)             stView.setVisibility(View.GONE);
cc1eb7a3 (David A. Velasco 2012-10-29 14:19:56 +0100 270)         }
cc1eb7a3 (David A. Velasco 2012-10-29 14:19:56 +0100 271)         if (s.get("L") != null) {
cc1eb7a3 (David A. Velasco 2012-10-29 14:19:56 +0100 272)             lView.setText(s.get("L"));
cc1eb7a3 (David A. Velasco 2012-10-29 14:19:56 +0100 273)             lView.setVisibility(View.VISIBLE);
cc1eb7a3 (David A. Velasco 2012-10-29 14:19:56 +0100 274)         } else {
cc1eb7a3 (David A. Velasco 2012-10-29 14:19:56 +0100 275)             lView.setVisibility(View.GONE);
cc1eb7a3 (David A. Velasco 2012-10-29 14:19:56 +0100 276)         }
cc1eb7a3 (David A. Velasco 2012-10-29 14:19:56 +0100 277)     }
cc1eb7a3 (David A. Velasco 2012-10-29 14:19:56 +0100 278)     
cc1eb7a3 (David A. Velasco 2012-10-29 14:19:56 +0100 279)     private void showIssuer(X500Principal issuer) {
cc1eb7a3 (David A. Velasco 2012-10-29 14:19:56 +0100 280)         Map<String, String> s = parsePrincipal(issuer);
cc1eb7a3 (David A. Velasco 2012-10-29 14:19:56 +0100 281)         TextView cnView = ((TextView)mView.findViewById(R.id.value_issuer_CN));
cc1eb7a3 (David A. Velasco 2012-10-29 14:19:56 +0100 282)         TextView oView = ((TextView)mView.findViewById(R.id.value_issuer_O));
cc1eb7a3 (David A. Velasco 2012-10-29 14:19:56 +0100 283)         TextView ouView = ((TextView)mView.findViewById(R.id.value_issuer_OU));
cc1eb7a3 (David A. Velasco 2012-10-29 14:19:56 +0100 284)         TextView cView = ((TextView)mView.findViewById(R.id.value_issuer_C));
cc1eb7a3 (David A. Velasco 2012-10-29 14:19:56 +0100 285)         TextView stView = ((TextView)mView.findViewById(R.id.value_issuer_ST));
cc1eb7a3 (David A. Velasco 2012-10-29 14:19:56 +0100 286)         TextView lView = ((TextView)mView.findViewById(R.id.value_issuer_L));
cc1eb7a3 (David A. Velasco 2012-10-29 14:19:56 +0100 287)         
cc1eb7a3 (David A. Velasco 2012-10-29 14:19:56 +0100 288)         if (s.get("CN") != null) {
cc1eb7a3 (David A. Velasco 2012-10-29 14:19:56 +0100 289)             cnView.setText(s.get("CN"));
cc1eb7a3 (David A. Velasco 2012-10-29 14:19:56 +0100 290)             cnView.setVisibility(View.VISIBLE);
cc1eb7a3 (David A. Velasco 2012-10-29 14:19:56 +0100 291)         } else {
cc1eb7a3 (David A. Velasco 2012-10-29 14:19:56 +0100 292)             cnView.setVisibility(View.GONE);
cc1eb7a3 (David A. Velasco 2012-10-29 14:19:56 +0100 293)         }
cc1eb7a3 (David A. Velasco 2012-10-29 14:19:56 +0100 294)         if (s.get("O") != null) {
cc1eb7a3 (David A. Velasco 2012-10-29 14:19:56 +0100 295)             oView.setText(s.get("O"));
cc1eb7a3 (David A. Velasco 2012-10-29 14:19:56 +0100 296)             oView.setVisibility(View.VISIBLE);
cc1eb7a3 (David A. Velasco 2012-10-29 14:19:56 +0100 297)         } else {
cc1eb7a3 (David A. Velasco 2012-10-29 14:19:56 +0100 298)             oView.setVisibility(View.GONE);
cc1eb7a3 (David A. Velasco 2012-10-29 14:19:56 +0100 299)         }
cc1eb7a3 (David A. Velasco 2012-10-29 14:19:56 +0100 300)         if (s.get("OU") != null) {
cc1eb7a3 (David A. Velasco 2012-10-29 14:19:56 +0100 301)             ouView.setText(s.get("OU"));
cc1eb7a3 (David A. Velasco 2012-10-29 14:19:56 +0100 302)             ouView.setVisibility(View.VISIBLE);
cc1eb7a3 (David A. Velasco 2012-10-29 14:19:56 +0100 303)         } else {
cc1eb7a3 (David A. Velasco 2012-10-29 14:19:56 +0100 304)             ouView.setVisibility(View.GONE);
cc1eb7a3 (David A. Velasco 2012-10-29 14:19:56 +0100 305)         }
cc1eb7a3 (David A. Velasco 2012-10-29 14:19:56 +0100 306)         if (s.get("C") != null) {
cc1eb7a3 (David A. Velasco 2012-10-29 14:19:56 +0100 307)             cView.setText(s.get("C"));
cc1eb7a3 (David A. Velasco 2012-10-29 14:19:56 +0100 308)             cView.setVisibility(View.VISIBLE);
cc1eb7a3 (David A. Velasco 2012-10-29 14:19:56 +0100 309)         } else {
cc1eb7a3 (David A. Velasco 2012-10-29 14:19:56 +0100 310)             cView.setVisibility(View.GONE);
cc1eb7a3 (David A. Velasco 2012-10-29 14:19:56 +0100 311)         }
cc1eb7a3 (David A. Velasco 2012-10-29 14:19:56 +0100 312)         if (s.get("ST") != null) {
cc1eb7a3 (David A. Velasco 2012-10-29 14:19:56 +0100 313)             stView.setText(s.get("ST"));
cc1eb7a3 (David A. Velasco 2012-10-29 14:19:56 +0100 314)             stView.setVisibility(View.VISIBLE);
cc1eb7a3 (David A. Velasco 2012-10-29 14:19:56 +0100 315)         } else {
cc1eb7a3 (David A. Velasco 2012-10-29 14:19:56 +0100 316)             stView.setVisibility(View.GONE);
cc1eb7a3 (David A. Velasco 2012-10-29 14:19:56 +0100 317)         }
cc1eb7a3 (David A. Velasco 2012-10-29 14:19:56 +0100 318)         if (s.get("L") != null) {
cc1eb7a3 (David A. Velasco 2012-10-29 14:19:56 +0100 319)             lView.setText(s.get("L"));
cc1eb7a3 (David A. Velasco 2012-10-29 14:19:56 +0100 320)             lView.setVisibility(View.VISIBLE);
cc1eb7a3 (David A. Velasco 2012-10-29 14:19:56 +0100 321)         } else {
cc1eb7a3 (David A. Velasco 2012-10-29 14:19:56 +0100 322)             lView.setVisibility(View.GONE);
cc1eb7a3 (David A. Velasco 2012-10-29 14:19:56 +0100 323)         }
cc1eb7a3 (David A. Velasco 2012-10-29 14:19:56 +0100 324)     }
cc1eb7a3 (David A. Velasco 2012-10-29 14:19:56 +0100 325)     
cc1eb7a3 (David A. Velasco 2012-10-29 14:19:56 +0100 326) 
cc1eb7a3 (David A. Velasco 2012-10-29 14:19:56 +0100 327)     private Map<String, String> parsePrincipal(X500Principal principal) {
cc1eb7a3 (David A. Velasco 2012-10-29 14:19:56 +0100 328)         Map<String, String> result = new HashMap<String, String>();
cc1eb7a3 (David A. Velasco 2012-10-29 14:19:56 +0100 329)         String toParse = principal.getName();
cc1eb7a3 (David A. Velasco 2012-10-29 14:19:56 +0100 330)         String[] pieces = toParse.split(",");
cc1eb7a3 (David A. Velasco 2012-10-29 14:19:56 +0100 331)         String[] tokens = {"CN", "O", "OU", "C", "ST", "L"}; 
cc1eb7a3 (David A. Velasco 2012-10-29 14:19:56 +0100 332)         for (int i=0; i < pieces.length ; i++) {
cc1eb7a3 (David A. Velasco 2012-10-29 14:19:56 +0100 333)             for (int j=0; j<tokens.length; j++) {
cc1eb7a3 (David A. Velasco 2012-10-29 14:19:56 +0100 334)                 if (pieces[i].startsWith(tokens[j] + "=")) {
cc1eb7a3 (David A. Velasco 2012-10-29 14:19:56 +0100 335)                     result.put(tokens[j], pieces[i].substring(tokens[j].length()+1));
cc1eb7a3 (David A. Velasco 2012-10-29 14:19:56 +0100 336)                 }
cc1eb7a3 (David A. Velasco 2012-10-29 14:19:56 +0100 337)             }
48f13c8a (David A. Velasco 2012-09-05 13:46:30 +0200 338)         }
cc1eb7a3 (David A. Velasco 2012-10-29 14:19:56 +0100 339)         return result;
48f13c8a (David A. Velasco 2012-09-05 13:46:30 +0200 340)     }
48f13c8a (David A. Velasco 2012-09-05 13:46:30 +0200 341) 
48f13c8a (David A. Velasco 2012-09-05 13:46:30 +0200 342)     private void saveServerCert() throws KeyStoreException, NoSuchAlgorithmException, CertificateException, IOException {
261aaf50 (David A. Velasco 2012-09-07 14:11:08 +0200 343)         if (mException.getServerCertificate() != null) {
cc1eb7a3 (David A. Velasco 2012-10-29 14:19:56 +0100 344)             // TODO make this asynchronously, it can take some time
261aaf50 (David A. Velasco 2012-09-07 14:11:08 +0200 345)             OwnCloudClientUtils.addCertToKnownServersStore(mException.getServerCertificate(), getContext());
48f13c8a (David A. Velasco 2012-09-05 13:46:30 +0200 346)         }
48f13c8a (David A. Velasco 2012-09-05 13:46:30 +0200 347)     }
48f13c8a (David A. Velasco 2012-09-05 13:46:30 +0200 348) 
48f13c8a (David A. Velasco 2012-09-05 13:46:30 +0200 349)     
48f13c8a (David A. Velasco 2012-09-05 13:46:30 +0200 350)     public interface OnSslValidatorListener {
48f13c8a (David A. Velasco 2012-09-05 13:46:30 +0200 351)         public void onSavedCertificate();
48f13c8a (David A. Velasco 2012-09-05 13:46:30 +0200 352)         public void onFailedSavingCertificate();
48f13c8a (David A. Velasco 2012-09-05 13:46:30 +0200 353)     }
48f13c8a (David A. Velasco 2012-09-05 13:46:30 +0200 354) }
48f13c8a (David A. Velasco 2012-09-05 13:46:30 +0200 355) 
