00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700    1) /* ownCloud Android client application
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700    2)  *   Copyright (C) 2012  Bartek Przybylski
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700    3)  *   Copyright (C) 2012-2013 ownCloud Inc.
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700    4)  *
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700    5)  *   This program is free software: you can redistribute it and/or modify
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700    6)  *   it under the terms of the GNU General Public License version 2,
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700    7)  *   as published by the Free Software Foundation.
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700    8)  *
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700    9)  *   This program is distributed in the hope that it will be useful,
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700   10)  *   but WITHOUT ANY WARRANTY; without even the implied warranty of
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700   11)  *   MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700   12)  *   GNU General Public License for more details.
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700   13)  *
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700   14)  *   You should have received a copy of the GNU General Public License
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700   15)  *   along with this program.  If not, see <http://www.gnu.org/licenses/>.
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700   16)  *
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700   17)  */
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700   18) 
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700   19) package com.owncloud.android.authentication;
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700   20) 
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700   21) import android.accounts.Account;
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700   22) import android.accounts.AccountManager;
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700   23) import android.app.AlertDialog;
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700   24) import android.app.Dialog;
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700   25) import android.app.ProgressDialog;
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700   26) import android.content.ContentResolver;
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700   27) import android.content.DialogInterface;
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700   28) import android.content.Intent;
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700   29) import android.content.SharedPreferences;
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700   30) import android.graphics.Rect;
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700   31) import android.graphics.drawable.Drawable;
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700   32) import android.net.Uri;
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700   33) import android.os.Bundle;
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700   34) import android.os.Handler;
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700   35) import android.preference.PreferenceManager;
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700   36) import android.support.v4.app.Fragment;
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700   37) import android.text.Editable;
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700   38) import android.text.InputType;
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700   39) import android.text.TextWatcher;
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700   40) import android.view.KeyEvent;
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700   41) import android.view.MotionEvent;
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700   42) import android.view.View;
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700   43) import android.view.View.OnFocusChangeListener;
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700   44) import android.view.View.OnTouchListener;
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700   45) import android.view.Window;
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700   46) import android.view.inputmethod.EditorInfo;
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700   47) import android.widget.Button;
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700   48) import android.widget.CheckBox;
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700   49) import android.widget.EditText;
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700   50) import android.widget.TextView;
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700   51) import android.widget.TextView.OnEditorActionListener;
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700   52) 
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700   53) import com.actionbarsherlock.app.SherlockDialogFragment;
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700   54) import com.owncloud.android.Log_OC;
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700   55) import com.owncloud.android.R;
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700   56) import com.owncloud.android.authentication.SsoWebViewClient.SsoWebViewClientListener;
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700   57) import com.owncloud.android.network.OwnCloudClientUtils;
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700   58) import com.owncloud.android.operations.ExistenceCheckOperation;
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700   59) import com.owncloud.android.operations.OAuth2GetAccessToken;
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700   60) import com.owncloud.android.operations.OnRemoteOperationListener;
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700   61) import com.owncloud.android.operations.OwnCloudServerCheckOperation;
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700   62) import com.owncloud.android.operations.RemoteOperation;
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700   63) import com.owncloud.android.operations.RemoteOperationResult;
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700   64) import com.owncloud.android.operations.RemoteOperationResult.ResultCode;
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700   65) import com.owncloud.android.ui.dialog.SamlWebViewDialog;
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700   66) import com.owncloud.android.ui.dialog.SslValidatorDialog;
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700   67) import com.owncloud.android.ui.dialog.SslValidatorDialog.OnSslValidatorListener;
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700   68) import com.owncloud.android.utils.OwnCloudVersion;
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700   69) 
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700   70) import eu.alefzero.webdav.WebdavClient;
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700   71) 
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700   72) /**
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700   73)  * This Activity is used to add an ownCloud account to the App
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700   74)  * 
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700   75)  * @author Bartek Przybylski
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700   76)  * @author David A. Velasco
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700   77)  */
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700   78) public class AuthenticatorActivity extends AccountAuthenticatorActivity
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700   79) implements  OnRemoteOperationListener, OnSslValidatorListener, OnFocusChangeListener, OnEditorActionListener, SsoWebViewClientListener{
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700   80) 
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700   81)     private static final String TAG = AuthenticatorActivity.class.getSimpleName();
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700   82) 
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700   83)     public static final String EXTRA_ACCOUNT = "ACCOUNT";
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700   84)     public static final String EXTRA_USER_NAME = "USER_NAME";
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700   85)     public static final String EXTRA_HOST_NAME = "HOST_NAME";
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700   86)     public static final String EXTRA_ACTION = "ACTION";
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700   87)     public static final String EXTRA_ENFORCED_UPDATE = "ENFORCE_UPDATE";
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700   88) 
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700   89)     private static final String KEY_AUTH_MESSAGE_VISIBILITY = "AUTH_MESSAGE_VISIBILITY";
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700   90)     private static final String KEY_AUTH_MESSAGE_TEXT = "AUTH_MESSAGE_TEXT";
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700   91)     private static final String KEY_HOST_URL_TEXT = "HOST_URL_TEXT";
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700   92)     private static final String KEY_OC_VERSION = "OC_VERSION";
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700   93)     private static final String KEY_ACCOUNT = "ACCOUNT";
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700   94)     private static final String KEY_SERVER_VALID = "SERVER_VALID";
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700   95)     private static final String KEY_SERVER_CHECKED = "SERVER_CHECKED";
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700   96)     private static final String KEY_SERVER_CHECK_IN_PROGRESS = "SERVER_CHECK_IN_PROGRESS"; 
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700   97)     private static final String KEY_SERVER_STATUS_TEXT = "SERVER_STATUS_TEXT";
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700   98)     private static final String KEY_SERVER_STATUS_ICON = "SERVER_STATUS_ICON";
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700   99)     private static final String KEY_IS_SSL_CONN = "IS_SSL_CONN";
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700  100)     private static final String KEY_PASSWORD_VISIBLE = "PASSWORD_VISIBLE";
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700  101)     private static final String KEY_AUTH_STATUS_TEXT = "AUTH_STATUS_TEXT";
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700  102)     private static final String KEY_AUTH_STATUS_ICON = "AUTH_STATUS_ICON";
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700  103)     private static final String KEY_REFRESH_BUTTON_ENABLED = "KEY_REFRESH_BUTTON_ENABLED";
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700  104)     
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700  105)     private static final String KEY_OC_USERNAME_EQUALS = "oc_username=";
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700  106) 
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700  107)     private static final String AUTH_ON = "on";
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700  108)     private static final String AUTH_OFF = "off";
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700  109)     private static final String AUTH_OPTIONAL = "optional";
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700  110)     
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700  111)     private static final int DIALOG_LOGIN_PROGRESS = 0;
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700  112)     private static final int DIALOG_SSL_VALIDATOR = 1;
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700  113)     private static final int DIALOG_CERT_NOT_SAVED = 2;
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700  114)     private static final int DIALOG_OAUTH2_LOGIN_PROGRESS = 3;
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700  115) 
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700  116)     public static final byte ACTION_CREATE = 0;
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700  117)     public static final byte ACTION_UPDATE_TOKEN = 1;
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700  118) 
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700  119)     private static final String TAG_SAML_DIALOG = "samlWebViewDialog";
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700  120)     
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700  121)     private String mHostBaseUrl;
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700  122)     private OwnCloudVersion mDiscoveredVersion;
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700  123) 
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700  124)     private String mAuthMessageText;
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700  125)     private int mAuthMessageVisibility, mServerStatusText, mServerStatusIcon;
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700  126)     private boolean mServerIsChecked, mServerIsValid, mIsSslConn;
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700  127)     private int mAuthStatusText, mAuthStatusIcon;    
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700  128)     private TextView mAuthStatusLayout;
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700  129) 
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700  130)     private final Handler mHandler = new Handler();
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700  131)     private Thread mOperationThread;
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700  132)     private OwnCloudServerCheckOperation mOcServerChkOperation;
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700  133)     private ExistenceCheckOperation mAuthCheckOperation;
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700  134)     private RemoteOperationResult mLastSslUntrustedServerResult;
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700  135) 
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700  136)     private Uri mNewCapturedUriFromOAuth2Redirection;
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700  137) 
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700  138)     private AccountManager mAccountMgr;
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700  139)     private boolean mJustCreated;
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700  140)     private byte mAction;
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700  141)     private Account mAccount;
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700  142) 
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700  143)     private TextView mAuthMessage;
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700  144)     
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700  145)     private EditText mHostUrlInput;
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700  146)     private boolean mHostUrlInputEnabled;
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700  147)     private View mRefreshButton;
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700  148) 
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700  149)     private String mAuthTokenType;
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700  150)     
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700  151)     private EditText mUsernameInput;
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700  152)     private EditText mPasswordInput;
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700  153)     
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700  154)     private CheckBox mOAuth2Check;
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700  155)     
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700  156)     private TextView mOAuthAuthEndpointText;
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700  157)     private TextView mOAuthTokenEndpointText;
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700  158)     
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700  159)     private SamlWebViewDialog mSamlDialog;
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700  160)     
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700  161)     private View mOkButton;
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700  162)     
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700  163)     private String mAuthToken;
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700  164)     
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700  165)     private boolean mResumed; // Control if activity is resumed
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700  166) 
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700  167) 
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700  168)     /**
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700  169)      * {@inheritDoc}
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700  170)      * 
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700  171)      * IMPORTANT ENTRY POINT 1: activity is shown to the user
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700  172)      */
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700  173)     @Override
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700  174)     protected void onCreate(Bundle savedInstanceState) {
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700  175)         super.onCreate(savedInstanceState);
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700  176)         getWindow().requestFeature(Window.FEATURE_NO_TITLE);
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700  177) 
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700  178)         /// set view and get references to view elements
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700  179)         setContentView(R.layout.account_setup);
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700  180)         mAuthMessage = (TextView) findViewById(R.id.auth_message);
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700  181)         mHostUrlInput = (EditText) findViewById(R.id.hostUrlInput);
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700  182)         mHostUrlInput.setText(getString(R.string.server_url));  // valid although R.string.server_url is an empty string
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700  183)         mUsernameInput = (EditText) findViewById(R.id.account_username);
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700  184)         mPasswordInput = (EditText) findViewById(R.id.account_password);
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700  185)         mOAuthAuthEndpointText = (TextView)findViewById(R.id.oAuthEntryPoint_1);
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700  186)         mOAuthTokenEndpointText = (TextView)findViewById(R.id.oAuthEntryPoint_2);
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700  187)         mOAuth2Check = (CheckBox) findViewById(R.id.oauth_onOff_check);
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700  188)         mOkButton = findViewById(R.id.buttonOK);
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700  189)         mAuthStatusLayout = (TextView) findViewById(R.id.auth_status_text); 
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700  190)         
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700  191)         /// set Host Url Input Enabled
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700  192)         mHostUrlInputEnabled = getResources().getBoolean(R.bool.show_server_url_input);
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700  193)         
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700  194) 
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700  195)         /// complete label for 'register account' button
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700  196)         Button b = (Button) findViewById(R.id.account_register);
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700  197)         if (b != null) {
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700  198)             b.setText(String.format(getString(R.string.auth_register), getString(R.string.app_name)));
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700  199)         }
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700  200) 
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700  201)         /// initialization
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700  202)         mAccountMgr = AccountManager.get(this);
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700  203)         mNewCapturedUriFromOAuth2Redirection = null;
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700  204)         mAction = getIntent().getByteExtra(EXTRA_ACTION, ACTION_CREATE); 
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700  205)         mAccount = null;
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700  206)         mHostBaseUrl = "";
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700  207)         boolean refreshButtonEnabled = false;
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700  208)         
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700  209)         // URL input configuration applied
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700  210)         if (!mHostUrlInputEnabled)
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700  211)         {
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700  212)             findViewById(R.id.hostUrlFrame).setVisibility(View.GONE);
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700  213)             mRefreshButton = findViewById(R.id.centeredRefreshButton);
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700  214) 
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700  215)         } else {
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700  216)             mRefreshButton = findViewById(R.id.embeddedRefreshButton);
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700  217)         }
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700  218) 
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700  219)         if (savedInstanceState == null) {
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700  220)             mResumed = false;
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700  221)             /// connection state and info
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700  222)             mAuthMessageVisibility = View.GONE;
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700  223)             mServerStatusText = mServerStatusIcon = 0;
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700  224)             mServerIsValid = false;
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700  225)             mServerIsChecked = false;
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700  226)             mIsSslConn = false;
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700  227)             mAuthStatusText = mAuthStatusIcon = 0;
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700  228) 
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700  229)             /// retrieve extras from intent
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700  230)             mAccount = getIntent().getExtras().getParcelable(EXTRA_ACCOUNT);
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700  231)             if (mAccount != null) {
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700  232)                 String ocVersion = mAccountMgr.getUserData(mAccount, AccountAuthenticator.KEY_OC_VERSION);
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700  233)                 if (ocVersion != null) {
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700  234)                     mDiscoveredVersion = new OwnCloudVersion(ocVersion);
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700  235)                 }
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700  236)                 mHostBaseUrl = normalizeUrl(mAccountMgr.getUserData(mAccount, AccountAuthenticator.KEY_OC_BASE_URL));
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700  237)                 mHostUrlInput.setText(mHostBaseUrl);
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700  238)                 String userName = mAccount.name.substring(0, mAccount.name.lastIndexOf('@'));
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700  239)                 mUsernameInput.setText(userName);
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700  240)             }
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700  241)             initAuthorizationMethod();  // checks intent and setup.xml to determine mCurrentAuthorizationMethod
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700  242)             mJustCreated = true;
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700  243)             
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700  244)             if (mAction == ACTION_UPDATE_TOKEN || !mHostUrlInputEnabled) {
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700  245)                 checkOcServer(); 
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700  246)             }
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700  247)             
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700  248)         } else {
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700  249)             mResumed = true;
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700  250)             /// connection state and info
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700  251)             mAuthMessageVisibility = savedInstanceState.getInt(KEY_AUTH_MESSAGE_VISIBILITY);
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700  252)             mAuthMessageText = savedInstanceState.getString(KEY_AUTH_MESSAGE_TEXT);
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700  253)             mServerIsValid = savedInstanceState.getBoolean(KEY_SERVER_VALID);
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700  254)             mServerIsChecked = savedInstanceState.getBoolean(KEY_SERVER_CHECKED);
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700  255)             mServerStatusText = savedInstanceState.getInt(KEY_SERVER_STATUS_TEXT);
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700  256)             mServerStatusIcon = savedInstanceState.getInt(KEY_SERVER_STATUS_ICON);
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700  257)             mIsSslConn = savedInstanceState.getBoolean(KEY_IS_SSL_CONN);
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700  258)             mAuthStatusText = savedInstanceState.getInt(KEY_AUTH_STATUS_TEXT);
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700  259)             mAuthStatusIcon = savedInstanceState.getInt(KEY_AUTH_STATUS_ICON);
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700  260)             if (savedInstanceState.getBoolean(KEY_PASSWORD_VISIBLE, false)) {
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700  261)                 showPassword();
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700  262)             }
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700  263)             
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700  264)             /// server data
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700  265)             String ocVersion = savedInstanceState.getString(KEY_OC_VERSION);
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700  266)             if (ocVersion != null) {
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700  267)                 mDiscoveredVersion = new OwnCloudVersion(ocVersion);
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700  268)             }
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700  269)             mHostBaseUrl = savedInstanceState.getString(KEY_HOST_URL_TEXT);
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700  270) 
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700  271)             // account data, if updating
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700  272)             mAccount = savedInstanceState.getParcelable(KEY_ACCOUNT);
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700  273)             mAuthTokenType = savedInstanceState.getString(AccountAuthenticator.KEY_AUTH_TOKEN_TYPE);
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700  274)             if (mAuthTokenType == null) {
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700  275)                 mAuthTokenType =  AccountAuthenticator.AUTH_TOKEN_TYPE_PASSWORD;
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700  276)                 
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700  277)             }
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700  278) 
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700  279)             // check if server check was interrupted by a configuration change
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700  280)             if (savedInstanceState.getBoolean(KEY_SERVER_CHECK_IN_PROGRESS, false)) {
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700  281)                 checkOcServer();
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700  282)             }            
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700  283)             
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700  284)             // refresh button enabled
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700  285)             refreshButtonEnabled = savedInstanceState.getBoolean(KEY_REFRESH_BUTTON_ENABLED);
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700  286)             
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700  287) 
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700  288)         }
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700  289) 
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700  290)         if (mAuthMessageVisibility== View.VISIBLE) {
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700  291)             showAuthMessage(mAuthMessageText);
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700  292)         }
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700  293)         else {
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700  294)             hideAuthMessage();
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700  295)         }
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700  296)         adaptViewAccordingToAuthenticationMethod();
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700  297)         showServerStatus();
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700  298)         showAuthStatus();
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700  299)         
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700  300)         if (mAction == ACTION_UPDATE_TOKEN) {
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700  301)             /// lock things that should not change
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700  302)             mHostUrlInput.setEnabled(false);
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700  303)             mHostUrlInput.setFocusable(false);
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700  304)             mUsernameInput.setEnabled(false);
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700  305)             mUsernameInput.setFocusable(false);
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700  306)             mOAuth2Check.setVisibility(View.GONE);
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700  307)         }
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700  308)         
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700  309)         //if (mServerIsChecked && !mServerIsValid && mRefreshButtonEnabled) showRefreshButton();
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700  310)         if (mServerIsChecked && !mServerIsValid && refreshButtonEnabled) showRefreshButton();
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700  311)         mOkButton.setEnabled(mServerIsValid); // state not automatically recovered in configuration changes
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700  312) 
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700  313)         if (AccountAuthenticator.AUTH_TOKEN_TYPE_SAML_WEB_SSO_SESSION_COOKIE.equals(mAuthTokenType) || 
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700  314)                 !AUTH_OPTIONAL.equals(getString(R.string.auth_method_oauth2))) {
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700  315)             mOAuth2Check.setVisibility(View.GONE);
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700  316)         }
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700  317) 
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700  318)         mPasswordInput.setText("");     // clean password to avoid social hacking (disadvantage: password in removed if the device is turned aside)
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700  319) 
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700  320)         /// bind view elements to listeners and other friends
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700  321)         mHostUrlInput.setOnFocusChangeListener(this);
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700  322)         mHostUrlInput.setImeOptions(EditorInfo.IME_ACTION_NEXT);
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700  323)         mHostUrlInput.setOnEditorActionListener(this);
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700  324)         mHostUrlInput.addTextChangedListener(new TextWatcher() {
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700  325) 
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700  326)             @Override
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700  327)             public void afterTextChanged(Editable s) {
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700  328)                 if (!mHostBaseUrl.equals(normalizeUrl(mHostUrlInput.getText().toString()))) {
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700  329)                     mOkButton.setEnabled(false);
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700  330)                 }
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700  331)             }
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700  332) 
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700  333)             @Override
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700  334)             public void beforeTextChanged(CharSequence s, int start, int count, int after) {
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700  335)             }
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700  336) 
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700  337)             @Override
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700  338)             public void onTextChanged(CharSequence s, int start, int before, int count) {
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700  339)                 if (!mResumed) {
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700  340)                     mAuthStatusIcon = 0;
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700  341)                     mAuthStatusText = 0;
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700  342)                     showAuthStatus();                    
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700  343)                 }
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700  344)                 mResumed = false;
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700  345)             }
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700  346)         });
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700  347)         
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700  348)         mPasswordInput.setOnFocusChangeListener(this);
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700  349)         mPasswordInput.setImeOptions(EditorInfo.IME_ACTION_DONE);
472b6b48 (masensio          2013-06-10 13:19:11 +0200  350)         mPasswordInput.setOnEditorActionListener(this);
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700  351)         mPasswordInput.setOnTouchListener(new RightDrawableOnTouchListener() {
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700  352)             @Override
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700  353)             public boolean onDrawableTouch(final MotionEvent event) {
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700  354)                 if (event.getAction() == MotionEvent.ACTION_UP) {
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700  355)                     AuthenticatorActivity.this.onViewPasswordClick();
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700  356)                 }
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700  357)                 return true;
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700  358)             }
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700  359)         });
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700  360)         
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700  361)         findViewById(R.id.scroll).setOnTouchListener(new OnTouchListener() {
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700  362)             @Override
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700  363)             public boolean onTouch(View view, MotionEvent event) {
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700  364)                 if (event.getAction() == MotionEvent.ACTION_DOWN) {
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700  365)                     if (AccountAuthenticator.AUTH_TOKEN_TYPE_SAML_WEB_SSO_SESSION_COOKIE.equals(mAuthTokenType) &&
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700  366)                             mHostUrlInput.hasFocus()) {
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700  367)                         checkOcServer();
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700  368)                     }
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700  369)                 }
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700  370)                 return false;
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700  371)             }
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700  372)         });
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700  373)     }
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700  374)     
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700  375)    
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700  376) 
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700  377)     private void initAuthorizationMethod() {
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700  378)         boolean oAuthRequired = false;
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700  379)         boolean samlWebSsoRequired = false;
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700  380) 
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700  381)         mAuthTokenType = getIntent().getExtras().getString(AccountAuthenticator.KEY_AUTH_TOKEN_TYPE);
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700  382)         mAccount = getIntent().getExtras().getParcelable(EXTRA_ACCOUNT);
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700  383)         
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700  384)         // TODO could be a good moment to validate the received token type, if not null
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700  385)         
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700  386)         if (mAuthTokenType == null) {    
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700  387)             if (mAccount != null) {
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700  388)                 /// same authentication method than the one used to create the account to update
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700  389)                 oAuthRequired = (mAccountMgr.getUserData(mAccount, AccountAuthenticator.KEY_SUPPORTS_OAUTH2) != null);
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700  390)                 samlWebSsoRequired = (mAccountMgr.getUserData(mAccount, AccountAuthenticator.KEY_SUPPORTS_SAML_WEB_SSO) != null);
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700  391)             
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700  392)             } else {
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700  393)                 /// use the one set in setup.xml
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700  394)                 oAuthRequired = AUTH_ON.equals(getString(R.string.auth_method_oauth2));
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700  395)                 samlWebSsoRequired = AUTH_ON.equals(getString(R.string.auth_method_saml_web_sso));            
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700  396)             }
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700  397)             if (oAuthRequired) {
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700  398)                 mAuthTokenType = AccountAuthenticator.AUTH_TOKEN_TYPE_ACCESS_TOKEN;
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700  399)             } else if (samlWebSsoRequired) {
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700  400)                 mAuthTokenType = AccountAuthenticator.AUTH_TOKEN_TYPE_SAML_WEB_SSO_SESSION_COOKIE;
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700  401)             } else {
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700  402)                 mAuthTokenType = AccountAuthenticator.AUTH_TOKEN_TYPE_PASSWORD;
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700  403)             }
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700  404)         }
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700  405)     
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700  406)         if (mAccount != null) {
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700  407)             String userName = mAccount.name.substring(0, mAccount.name.lastIndexOf('@'));
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700  408)             mUsernameInput.setText(userName);
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700  409)         }
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700  410)         
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700  411)         mOAuth2Check.setChecked(AccountAuthenticator.AUTH_TOKEN_TYPE_ACCESS_TOKEN.equals(mAuthTokenType));
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700  412)         
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700  413)     }
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700  414) 
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700  415)     /**
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700  416)      * Saves relevant state before {@link #onPause()}
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700  417)      * 
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700  418)      * Do NOT save {@link #mNewCapturedUriFromOAuth2Redirection}; it keeps a temporal flag, intended to defer the 
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700  419)      * processing of the redirection caught in {@link #onNewIntent(Intent)} until {@link #onResume()} 
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700  420)      * 
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700  421)      * See {@link #loadSavedInstanceState(Bundle)}
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700  422)      */
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700  423)     @Override
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700  424)     protected void onSaveInstanceState(Bundle outState) {
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700  425)         super.onSaveInstanceState(outState);
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700  426) 
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700  427)         /// connection state and info
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700  428)         outState.putInt(KEY_AUTH_MESSAGE_VISIBILITY, mAuthMessage.getVisibility());
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700  429)         outState.putString(KEY_AUTH_MESSAGE_TEXT, mAuthMessage.getText().toString());
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700  430)         outState.putInt(KEY_SERVER_STATUS_TEXT, mServerStatusText);
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700  431)         outState.putInt(KEY_SERVER_STATUS_ICON, mServerStatusIcon);
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700  432)         outState.putBoolean(KEY_SERVER_VALID, mServerIsValid);
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700  433)         outState.putBoolean(KEY_SERVER_CHECKED, mServerIsChecked);
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700  434)         outState.putBoolean(KEY_SERVER_CHECK_IN_PROGRESS, (!mServerIsValid && mOcServerChkOperation != null));
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700  435)         outState.putBoolean(KEY_IS_SSL_CONN, mIsSslConn);
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700  436)         outState.putBoolean(KEY_PASSWORD_VISIBLE, isPasswordVisible());
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700  437)         outState.putInt(KEY_AUTH_STATUS_ICON, mAuthStatusIcon);
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700  438)         outState.putInt(KEY_AUTH_STATUS_TEXT, mAuthStatusText);
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700  439) 
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700  440)         /// server data
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700  441)         if (mDiscoveredVersion != null) {
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700  442)             outState.putString(KEY_OC_VERSION, mDiscoveredVersion.toString());
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700  443)         }
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700  444)         outState.putString(KEY_HOST_URL_TEXT, mHostBaseUrl);
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700  445) 
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700  446)         /// account data, if updating
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700  447)         if (mAccount != null) {
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700  448)             outState.putParcelable(KEY_ACCOUNT, mAccount);
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700  449)         }
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700  450)         outState.putString(AccountAuthenticator.KEY_AUTH_TOKEN_TYPE, mAuthTokenType);
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700  451)         
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700  452)         // refresh button enabled
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700  453)         outState.putBoolean(KEY_REFRESH_BUTTON_ENABLED, (mRefreshButton.getVisibility() == View.VISIBLE));
da774062 (masensio          2013-08-27 12:15:06 +0200  454)         
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700  455) 
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700  456)     }
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700  457) 
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700  458) 
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700  459)     /**
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700  460)      * The redirection triggered by the OAuth authentication server as response to the GET AUTHORIZATION request
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700  461)      * is caught here.
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700  462)      * 
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700  463)      * To make this possible, this activity needs to be qualified with android:launchMode = "singleTask" in the
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700  464)      * AndroidManifest.xml file.
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700  465)      */
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700  466)     @Override
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700  467)     protected void onNewIntent (Intent intent) {
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700  468)         Log_OC.d(TAG, "onNewIntent()");
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700  469)         Uri data = intent.getData();
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700  470)         if (data != null && data.toString().startsWith(getString(R.string.oauth2_redirect_uri))) {
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700  471)             mNewCapturedUriFromOAuth2Redirection = data;
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700  472)         }
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700  473)     }
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700  474) 
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700  475) 
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700  476)     /**
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700  477)      * The redirection triggered by the OAuth authentication server as response to the GET AUTHORIZATION, and 
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700  478)      * deferred in {@link #onNewIntent(Intent)}, is processed here.
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700  479)      */
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700  480)     @Override
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700  481)     protected void onResume() {
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700  482)         super.onResume();
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700  483)         if (mAction == ACTION_UPDATE_TOKEN && mJustCreated && getIntent().getBooleanExtra(EXTRA_ENFORCED_UPDATE, false)) {
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700  484)             if (AccountAuthenticator.AUTH_TOKEN_TYPE_ACCESS_TOKEN.equals(mAuthTokenType)) {
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700  485)                 //Toast.makeText(this, R.string.auth_expired_oauth_token_toast, Toast.LENGTH_LONG).show();
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700  486)                 showAuthMessage(getString(R.string.auth_expired_oauth_token_toast));
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700  487)             } else if (AccountAuthenticator.AUTH_TOKEN_TYPE_SAML_WEB_SSO_SESSION_COOKIE.equals(mAuthTokenType)) {
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700  488)                 //Toast.makeText(this, R.string.auth_expired_saml_sso_token_toast, Toast.LENGTH_LONG).show();
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700  489)                 showAuthMessage(getString(R.string.auth_expired_saml_sso_token_toast));
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700  490)             } else {
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700  491)                 //Toast.makeText(this, R.string.auth_expired_basic_auth_toast, Toast.LENGTH_LONG).show();
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700  492)                 showAuthMessage(getString(R.string.auth_expired_basic_auth_toast));
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700  493)             }
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700  494)         }
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700  495) 
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700  496)         if (mNewCapturedUriFromOAuth2Redirection != null) {
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700  497)             getOAuth2AccessTokenFromCapturedRedirection();            
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700  498)         }
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700  499) 
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700  500)         mJustCreated = false;
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700  501)         
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700  502)     }
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700  503) 
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700  504) 
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700  505)     /**
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700  506)      * Parses the redirection with the response to the GET AUTHORIZATION request to the 
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700  507)      * oAuth server and requests for the access token (GET ACCESS TOKEN)
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700  508)      */
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700  509)     private void getOAuth2AccessTokenFromCapturedRedirection() {
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700  510)         /// Parse data from OAuth redirection
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700  511)         String queryParameters = mNewCapturedUriFromOAuth2Redirection.getQuery();
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700  512)         mNewCapturedUriFromOAuth2Redirection = null;
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700  513) 
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700  514)         /// Showing the dialog with instructions for the user.
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700  515)         showDialog(DIALOG_OAUTH2_LOGIN_PROGRESS);
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700  516) 
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700  517)         /// GET ACCESS TOKEN to the oAuth server 
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700  518)         RemoteOperation operation = new OAuth2GetAccessToken(   getString(R.string.oauth2_client_id), 
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700  519)                 getString(R.string.oauth2_redirect_uri),       
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700  520)                 getString(R.string.oauth2_grant_type),
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700  521)                 queryParameters);
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700  522)         //WebdavClient client = OwnCloudClientUtils.createOwnCloudClient(Uri.parse(getString(R.string.oauth2_url_endpoint_access)), getApplicationContext());
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700  523)         WebdavClient client = OwnCloudClientUtils.createOwnCloudClient(Uri.parse(mOAuthTokenEndpointText.getText().toString().trim()), getApplicationContext(), true);
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700  524)         operation.execute(client, this, mHandler);
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700  525)     }
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700  526) 
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700  527) 
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700  528) 
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700  529)     /**
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700  530)      * Handles the change of focus on the text inputs for the server URL and the password
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700  531)      */
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700  532)     public void onFocusChange(View view, boolean hasFocus) {
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700  533)         if (view.getId() == R.id.hostUrlInput) {   
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700  534)             if (!hasFocus) {
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700  535)                 onUrlInputFocusLost((TextView) view);
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700  536)             }
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700  537)             else {
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700  538)                 hideRefreshButton();
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700  539)             }
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700  540) 
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700  541)         } else if (view.getId() == R.id.account_password) {
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700  542)             onPasswordFocusChanged((TextView) view, hasFocus);
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700  543)         }
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700  544)     }
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700  545) 
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700  546) 
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700  547)     /**
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700  548)      * Handles changes in focus on the text input for the server URL.
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700  549)      * 
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700  550)      * IMPORTANT ENTRY POINT 2: When (!hasFocus), user wrote the server URL and changed to 
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700  551)      * other field. The operation to check the existence of the server in the entered URL is
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700  552)      * started. 
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700  553)      * 
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700  554)      * When hasFocus:    user 'comes back' to write again the server URL.
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700  555)      * 
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700  556)      * @param hostInput     TextView with the URL input field receiving the change of focus.
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700  557)      */
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700  558)     private void onUrlInputFocusLost(TextView hostInput) {
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700  559)         if (!mHostBaseUrl.equals(normalizeUrl(mHostUrlInput.getText().toString()))) {
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700  560)             checkOcServer();
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700  561)         } else {
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700  562)             mOkButton.setEnabled(mServerIsValid);
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700  563)             if (!mServerIsValid) {
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700  564)                 showRefreshButton();
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700  565)             }
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700  566)         }
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700  567)     }
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700  568) 
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700  569) 
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700  570)     private void checkOcServer() {
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700  571)         String uri = trimUrlWebdav(mHostUrlInput.getText().toString().trim());
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700  572)         
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700  573)         if (!mHostUrlInputEnabled){
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700  574)             uri = getString(R.string.server_url);
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700  575)         }
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700  576)         
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700  577)         mServerIsValid = false;
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700  578)         mServerIsChecked = false;
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700  579)         mOkButton.setEnabled(false);
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700  580)         mDiscoveredVersion = null;
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700  581)         hideRefreshButton();
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700  582)         if (uri.length() != 0) {
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700  583)             mServerStatusText = R.string.auth_testing_connection;
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700  584)             mServerStatusIcon = R.drawable.progress_small;
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700  585)             showServerStatus();
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700  586)             mOcServerChkOperation = new  OwnCloudServerCheckOperation(uri, this);
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700  587)             WebdavClient client = OwnCloudClientUtils.createOwnCloudClient(Uri.parse(uri), this, true);
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700  588)             mOperationThread = mOcServerChkOperation.execute(client, this, mHandler);
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700  589)         } else {
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700  590)             mServerStatusText = 0;
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700  591)             mServerStatusIcon = 0;
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700  592)             showServerStatus();
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700  593)         }
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700  594)     }
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700  595) 
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700  596) 
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700  597)     /**
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700  598)      * Handles changes in focus on the text input for the password (basic authorization).
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700  599)      * 
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700  600)      * When (hasFocus), the button to toggle password visibility is shown.
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700  601)      * 
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700  602)      * When (!hasFocus), the button is made invisible and the password is hidden.
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700  603)      * 
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700  604)      * @param passwordInput    TextView with the password input field receiving the change of focus.
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700  605)      * @param hasFocus          'True' if focus is received, 'false' if is lost
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700  606)      */
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700  607)     private void onPasswordFocusChanged(TextView passwordInput, boolean hasFocus) {
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700  608)         if (hasFocus) {
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700  609)             showViewPasswordButton();
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700  610)         } else {
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700  611)             hidePassword();
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700  612)             hidePasswordButton();
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700  613)         }
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700  614)     }
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700  615) 
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700  616) 
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700  617)     private void showViewPasswordButton() {
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700  618)         //int drawable = android.R.drawable.ic_menu_view;
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700  619)         int drawable = R.drawable.ic_view;
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700  620)         if (isPasswordVisible()) {
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700  621)             //drawable = android.R.drawable.ic_secure;
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700  622)             drawable = R.drawable.ic_hide;
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700  623)         }
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700  624)         mPasswordInput.setCompoundDrawablesWithIntrinsicBounds(0, 0, drawable, 0);
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700  625)     }
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700  626) 
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700  627)     private boolean isPasswordVisible() {
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700  628)         return ((mPasswordInput.getInputType() & InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD) == InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD);
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700  629)     }
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700  630)     
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700  631)     private void hidePasswordButton() {
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700  632)         mPasswordInput.setCompoundDrawablesWithIntrinsicBounds(0, 0, 0, 0);
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700  633)     }
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700  634) 
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700  635)     private void showPassword() {
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700  636)         mPasswordInput.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD);
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700  637)         showViewPasswordButton();
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700  638)     }
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700  639)     
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700  640)     private void hidePassword() {
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700  641)         mPasswordInput.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700  642)         showViewPasswordButton();
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700  643)     }
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700  644)     
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700  645)     
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700  646)     /**
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700  647)      * Cancels the authenticator activity
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700  648)      * 
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700  649)      * IMPORTANT ENTRY POINT 3: Never underestimate the importance of cancellation
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700  650)      * 
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700  651)      * This method is bound in the layout/acceoun_setup.xml resource file.
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700  652)      * 
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700  653)      * @param view      Cancel button
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700  654)      */
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700  655)     public void onCancelClick(View view) {
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700  656)         setResult(RESULT_CANCELED);     // TODO review how is this related to AccountAuthenticator (debugging)
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700  657)         finish();
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700  658)     }
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700  659) 
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700  660) 
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700  661) 
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700  662)     /**
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700  663)      * Checks the credentials of the user in the root of the ownCloud server
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700  664)      * before creating a new local account.
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700  665)      * 
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700  666)      * For basic authorization, a check of existence of the root folder is
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700  667)      * performed.
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700  668)      * 
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700  669)      * For OAuth, starts the flow to get an access token; the credentials test 
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700  670)      * is postponed until it is available.
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700  671)      * 
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700  672)      * IMPORTANT ENTRY POINT 4
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700  673)      * 
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700  674)      * @param view      OK button
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700  675)      */
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700  676)     public void onOkClick(View view) {
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700  677)         // this check should be unnecessary
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700  678)         if (mDiscoveredVersion == null || !mDiscoveredVersion.isVersionValid()  || mHostBaseUrl == null || mHostBaseUrl.length() == 0) {
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700  679)             mServerStatusIcon = R.drawable.common_error;
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700  680)             mServerStatusText = R.string.auth_wtf_reenter_URL;
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700  681)             showServerStatus();
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700  682)             mOkButton.setEnabled(false);
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700  683)             Log_OC.wtf(TAG,  "The user was allowed to click 'connect' to an unchecked server!!");
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700  684)             return;
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700  685)         }
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700  686) 
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700  687)         if (AccountAuthenticator.AUTH_TOKEN_TYPE_ACCESS_TOKEN.equals(mAuthTokenType)) {
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700  688)             startOauthorization();
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700  689)         } else if (AccountAuthenticator.AUTH_TOKEN_TYPE_SAML_WEB_SSO_SESSION_COOKIE.equals(mAuthTokenType)) { 
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700  690)             startSamlBasedFederatedSingleSignOnAuthorization();
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700  691)         } else {
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700  692)             checkBasicAuthorization();
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700  693)         }
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700  694)     }
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700  695) 
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700  696) 
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700  697)     /**
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700  698)      * Tests the credentials entered by the user performing a check of existence on 
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700  699)      * the root folder of the ownCloud server.
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700  700)      */
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700  701)     private void checkBasicAuthorization() {
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700  702)         /// get the path to the root folder through WebDAV from the version server
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700  703)         String webdav_path = AccountUtils.getWebdavPath(mDiscoveredVersion, mAuthTokenType);
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700  704) 
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700  705)         /// get basic credentials entered by user
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700  706)         String username = mUsernameInput.getText().toString();
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700  707)         String password = mPasswordInput.getText().toString();
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700  708) 
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700  709)         /// be gentle with the user
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700  710)         showDialog(DIALOG_LOGIN_PROGRESS);
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700  711) 
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700  712)         /// test credentials accessing the root folder
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700  713)         mAuthCheckOperation = new  ExistenceCheckOperation("", this, false);
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700  714)         WebdavClient client = OwnCloudClientUtils.createOwnCloudClient(Uri.parse(mHostBaseUrl + webdav_path), this, true);
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700  715)         client.setBasicCredentials(username, password);
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700  716)         mOperationThread = mAuthCheckOperation.execute(client, this, mHandler);
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700  717)     }
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700  718) 
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700  719) 
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700  720)     /**
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700  721)      * Starts the OAuth 'grant type' flow to get an access token, with 
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700  722)      * a GET AUTHORIZATION request to the BUILT-IN authorization server. 
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700  723)      */
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700  724)     private void startOauthorization() {
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700  725)         // be gentle with the user
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700  726)         mAuthStatusIcon = R.drawable.progress_small;
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700  727)         mAuthStatusText = R.string.oauth_login_connection;
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700  728)         showAuthStatus();
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700  729)         
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700  730) 
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700  731)         // GET AUTHORIZATION request
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700  732)         //Uri uri = Uri.parse(getString(R.string.oauth2_url_endpoint_auth));
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700  733)         Uri uri = Uri.parse(mOAuthAuthEndpointText.getText().toString().trim());
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700  734)         Uri.Builder uriBuilder = uri.buildUpon();
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700  735)         uriBuilder.appendQueryParameter(OAuth2Constants.KEY_RESPONSE_TYPE, getString(R.string.oauth2_response_type));
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700  736)         uriBuilder.appendQueryParameter(OAuth2Constants.KEY_REDIRECT_URI, getString(R.string.oauth2_redirect_uri));   
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700  737)         uriBuilder.appendQueryParameter(OAuth2Constants.KEY_CLIENT_ID, getString(R.string.oauth2_client_id));
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700  738)         uriBuilder.appendQueryParameter(OAuth2Constants.KEY_SCOPE, getString(R.string.oauth2_scope));
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700  739)         //uriBuilder.appendQueryParameter(OAuth2Constants.KEY_STATE, whateverwewant);
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700  740)         uri = uriBuilder.build();
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700  741)         Log_OC.d(TAG, "Starting browser to view " + uri.toString());
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700  742)         Intent i = new Intent(Intent.ACTION_VIEW, uri);
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700  743)         startActivity(i);
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700  744)     }
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700  745) 
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700  746) 
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700  747)     /**
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700  748)      * Starts the Web Single Sign On flow to get access to the root folder
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700  749)      * in the server.
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700  750)      */
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700  751)     private void startSamlBasedFederatedSingleSignOnAuthorization() {
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700  752)         // be gentle with the user
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700  753)         mAuthStatusIcon = R.drawable.progress_small;
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700  754)         mAuthStatusText = R.string.auth_connecting_auth_server;
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700  755)         showAuthStatus();
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700  756)         showDialog(DIALOG_LOGIN_PROGRESS);
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700  757)         
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700  758)         /// get the path to the root folder through WebDAV from the version server
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700  759)         String webdav_path = AccountUtils.getWebdavPath(mDiscoveredVersion, mAuthTokenType);
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700  760) 
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700  761)         /// test credentials accessing the root folder
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700  762)         mAuthCheckOperation = new  ExistenceCheckOperation("", this, false);
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700  763)         WebdavClient client = OwnCloudClientUtils.createOwnCloudClient(Uri.parse(mHostBaseUrl + webdav_path), this, false);
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700  764)         mOperationThread = mAuthCheckOperation.execute(client, this, mHandler);
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700  765)       
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700  766)     }
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700  767) 
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700  768)     /**
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700  769)      * Callback method invoked when a RemoteOperation executed by this Activity finishes.
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700  770)      * 
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700  771)      * Dispatches the operation flow to the right method.
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700  772)      */
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700  773)     @Override
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700  774)     public void onRemoteOperationFinish(RemoteOperation operation, RemoteOperationResult result) {
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700  775) 
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700  776)         if (operation instanceof OwnCloudServerCheckOperation) {
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700  777)             onOcServerCheckFinish((OwnCloudServerCheckOperation) operation, result);
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700  778) 
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700  779)         } else if (operation instanceof OAuth2GetAccessToken) {
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700  780)             onGetOAuthAccessTokenFinish((OAuth2GetAccessToken)operation, result);
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700  781) 
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700  782)         } else if (operation instanceof ExistenceCheckOperation)  {
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700  783)             if (AccountAuthenticator.AUTH_TOKEN_TYPE_SAML_WEB_SSO_SESSION_COOKIE.equals(mAuthTokenType)) {
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700  784)                 onSamlBasedFederatedSingleSignOnAuthorizationStart(operation, result);
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700  785)                 
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700  786)             } else {
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700  787)                 onAuthorizationCheckFinish((ExistenceCheckOperation)operation, result);
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700  788)             }
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700  789)         }
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700  790)     }
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700  791)     
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700  792)     
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700  793)     private void onSamlBasedFederatedSingleSignOnAuthorizationStart(RemoteOperation operation, RemoteOperationResult result) {
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700  794)         try {
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700  795)             dismissDialog(DIALOG_LOGIN_PROGRESS);
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700  796)         } catch (IllegalArgumentException e) {
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700  797)             // NOTHING TO DO ; can't find out what situation that leads to the exception in this code, but user logs signal that it happens
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700  798)         }
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700  799)         
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700  800)         //if (result.isTemporalRedirection() && result.isIdPRedirection()) {
da774062 (masensio          2013-08-27 12:15:06 +0200  801)         if (result.isIdPRedirection()) {
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700  802)             String url = result.getRedirectedLocation();
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700  803)             String targetUrl = mHostBaseUrl + AccountUtils.getWebdavPath(mDiscoveredVersion, mAuthTokenType);
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700  804)             
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700  805)             // Show dialog
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700  806)             mSamlDialog = SamlWebViewDialog.newInstance(url, targetUrl);            
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700  807)             mSamlDialog.show(getSupportFragmentManager(), TAG_SAML_DIALOG);
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700  808)             
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700  809)             mAuthStatusIcon = 0;
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700  810)             mAuthStatusText = 0;
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700  811)             
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700  812)         } else {
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700  813)             mAuthStatusIcon = R.drawable.common_error;
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700  814)             mAuthStatusText = R.string.auth_unsupported_auth_method;
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700  815)             
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700  816)         }
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700  817)         showAuthStatus();
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700  818)     }
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700  819) 
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700  820) 
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700  821)     /**
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700  822)      * Processes the result of the server check performed when the user finishes the enter of the
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700  823)      * server URL.
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700  824)      * 
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700  825)      * @param operation     Server check performed.
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700  826)      * @param result        Result of the check.
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700  827)      */
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700  828)     private void onOcServerCheckFinish(OwnCloudServerCheckOperation operation, RemoteOperationResult result) {
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700  829)         if (operation.equals(mOcServerChkOperation)) {
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700  830)             /// save result state
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700  831)             mServerIsChecked = true;
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700  832)             mServerIsValid = result.isSuccess();
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700  833)             mIsSslConn = (result.getCode() == ResultCode.OK_SSL);
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700  834)             mOcServerChkOperation = null;
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700  835) 
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700  836)             /// update status icon and text
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700  837)             if (mServerIsValid) {
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700  838)                 hideRefreshButton();
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700  839)             } else {
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700  840)                 showRefreshButton();
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700  841)             }
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700  842)             updateServerStatusIconAndText(result);
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700  843)             showServerStatus();
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700  844) 
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700  845)             /// very special case (TODO: move to a common place for all the remote operations)
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700  846)             if (result.getCode() == ResultCode.SSL_RECOVERABLE_PEER_UNVERIFIED) {
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700  847)                 mLastSslUntrustedServerResult = result;
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700  848)                 showDialog(DIALOG_SSL_VALIDATOR); 
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700  849)             }
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700  850) 
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700  851)             /// retrieve discovered version and normalize server URL
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700  852)             mDiscoveredVersion = operation.getDiscoveredVersion();
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700  853)             mHostBaseUrl = normalizeUrl(mHostUrlInput.getText().toString());
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700  854) 
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700  855)             /// allow or not the user try to access the server
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700  856)             mOkButton.setEnabled(mServerIsValid);
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700  857) 
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700  858)         }   // else nothing ; only the last check operation is considered; 
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700  859)         // multiple can be triggered if the user amends a URL before a previous check can be triggered
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700  860)     }
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700  861) 
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700  862) 
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700  863)     private String normalizeUrl(String url) {
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700  864)         if (url != null && url.length() > 0) {
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700  865)             url = url.trim();
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700  866)             if (!url.toLowerCase().startsWith("http://") &&
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700  867)                     !url.toLowerCase().startsWith("https://")) {
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700  868)                 if (mIsSslConn) {
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700  869)                     url = "https://" + url;
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700  870)                 } else {
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700  871)                     url = "http://" + url;
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700  872)                 }
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700  873)             }
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700  874) 
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700  875)             // OC-208: Add suffix remote.php/webdav to normalize (OC-34)            
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700  876)             url = trimUrlWebdav(url);
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700  877) 
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700  878)             if (url.endsWith("/")) {
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700  879)                 url = url.substring(0, url.length() - 1);
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700  880)             }
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700  881) 
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700  882)         }
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700  883)         return (url != null ? url : "");
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700  884)     }
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700  885) 
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700  886) 
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700  887)     private String trimUrlWebdav(String url){       
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700  888)         if(url.toLowerCase().endsWith(AccountUtils.WEBDAV_PATH_4_0)){
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700  889)             url = url.substring(0, url.length() - AccountUtils.WEBDAV_PATH_4_0.length());             
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700  890)         } else if(url.toLowerCase().endsWith(AccountUtils.WEBDAV_PATH_2_0)){
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700  891)             url = url.substring(0, url.length() - AccountUtils.WEBDAV_PATH_2_0.length());             
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700  892)         } else if (url.toLowerCase().endsWith(AccountUtils.WEBDAV_PATH_1_2)){
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700  893)             url = url.substring(0, url.length() - AccountUtils.WEBDAV_PATH_1_2.length());             
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700  894)         } 
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700  895)         return (url != null ? url : "");
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700  896)     }
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700  897)     
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700  898)     
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700  899)     /**
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700  900)      * Chooses the right icon and text to show to the user for the received operation result.
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700  901)      * 
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700  902)      * @param result    Result of a remote operation performed in this activity
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700  903)      */
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700  904)     private void updateServerStatusIconAndText(RemoteOperationResult result) {
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700  905)         mServerStatusIcon = R.drawable.common_error;    // the most common case in the switch below
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700  906) 
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700  907)         switch (result.getCode()) {
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700  908)         case OK_SSL:
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700  909)             mServerStatusIcon = android.R.drawable.ic_secure;
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700  910)             mServerStatusText = R.string.auth_secure_connection;
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700  911)             break;
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700  912) 
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700  913)         case OK_NO_SSL:
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700  914)         case OK:
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700  915)             if (mHostUrlInput.getText().toString().trim().toLowerCase().startsWith("http://") ) {
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700  916)                 mServerStatusText = R.string.auth_connection_established;
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700  917)                 mServerStatusIcon = R.drawable.ic_ok;
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700  918)             } else {
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700  919)                 mServerStatusText = R.string.auth_nossl_plain_ok_title;
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700  920)                 mServerStatusIcon = android.R.drawable.ic_partial_secure;
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700  921)             }
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700  922)             break;
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700  923) 
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700  924)         case NO_NETWORK_CONNECTION:
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700  925)             mServerStatusIcon = R.drawable.no_network;
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700  926)             mServerStatusText = R.string.auth_no_net_conn_title;
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700  927)             break;
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700  928) 
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700  929)         case SSL_RECOVERABLE_PEER_UNVERIFIED:
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700  930)             mServerStatusText = R.string.auth_ssl_unverified_server_title;
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700  931)             break;
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700  932)         case BAD_OC_VERSION:
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700  933)             mServerStatusText = R.string.auth_bad_oc_version_title;
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700  934)             break;
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700  935)         case WRONG_CONNECTION:
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700  936)             mServerStatusText = R.string.auth_wrong_connection_title;
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700  937)             break;
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700  938)         case TIMEOUT:
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700  939)             mServerStatusText = R.string.auth_timeout_title;
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700  940)             break;
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700  941)         case INCORRECT_ADDRESS:
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700  942)             mServerStatusText = R.string.auth_incorrect_address_title;
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700  943)             break;
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700  944)         case SSL_ERROR:
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700  945)             mServerStatusText = R.string.auth_ssl_general_error_title;
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700  946)             break;
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700  947)         case UNAUTHORIZED:
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700  948)             mServerStatusText = R.string.auth_unauthorized;
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700  949)             break;
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700  950)         case HOST_NOT_AVAILABLE:
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700  951)             mServerStatusText = R.string.auth_unknown_host_title;
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700  952)             break;
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700  953)         case INSTANCE_NOT_CONFIGURED:
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700  954)             mServerStatusText = R.string.auth_not_configured_title;
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700  955)             break;
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700  956)         case FILE_NOT_FOUND:
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700  957)             mServerStatusText = R.string.auth_incorrect_path_title;
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700  958)             break;
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700  959)         case OAUTH2_ERROR:
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700  960)             mServerStatusText = R.string.auth_oauth_error;
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700  961)             break;
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700  962)         case OAUTH2_ERROR_ACCESS_DENIED:
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700  963)             mServerStatusText = R.string.auth_oauth_error_access_denied;
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700  964)             break;
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700  965)         case UNHANDLED_HTTP_CODE:
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700  966)         case UNKNOWN_ERROR:
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700  967)             mServerStatusText = R.string.auth_unknown_error_title;
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700  968)             break;
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700  969)         default:
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700  970)             mServerStatusText = 0;
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700  971)             mServerStatusIcon = 0;
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700  972)         }
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700  973)     }
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700  974) 
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700  975) 
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700  976)     /**
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700  977)      * Chooses the right icon and text to show to the user for the received operation result.
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700  978)      * 
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700  979)      * @param result    Result of a remote operation performed in this activity
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700  980)      */
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700  981)     private void updateAuthStatusIconAndText(RemoteOperationResult result) {
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700  982)         mAuthStatusIcon = R.drawable.common_error;    // the most common case in the switch below
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700  983) 
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700  984)         switch (result.getCode()) {
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700  985)         case OK_SSL:
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700  986)             mAuthStatusIcon = android.R.drawable.ic_secure;
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700  987)             mAuthStatusText = R.string.auth_secure_connection;
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700  988)             break;
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700  989) 
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700  990)         case OK_NO_SSL:
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700  991)         case OK:
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700  992)             if (mHostUrlInput.getText().toString().trim().toLowerCase().startsWith("http://") ) {
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700  993)                 mAuthStatusText = R.string.auth_connection_established;
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700  994)                 mAuthStatusIcon = R.drawable.ic_ok;
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700  995)             } else {
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700  996)                 mAuthStatusText = R.string.auth_nossl_plain_ok_title;
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700  997)                 mAuthStatusIcon = android.R.drawable.ic_partial_secure;
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700  998)             }
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700  999)             break;
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700 1000) 
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700 1001)         case NO_NETWORK_CONNECTION:
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700 1002)             mAuthStatusIcon = R.drawable.no_network;
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700 1003)             mAuthStatusText = R.string.auth_no_net_conn_title;
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700 1004)             break;
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700 1005) 
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700 1006)         case SSL_RECOVERABLE_PEER_UNVERIFIED:
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700 1007)             mAuthStatusText = R.string.auth_ssl_unverified_server_title;
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700 1008)             break;
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700 1009)         case BAD_OC_VERSION:
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700 1010)             mAuthStatusText = R.string.auth_bad_oc_version_title;
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700 1011)             break;
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700 1012)         case WRONG_CONNECTION:
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700 1013)             mAuthStatusText = R.string.auth_wrong_connection_title;
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700 1014)             break;
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700 1015)         case TIMEOUT:
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700 1016)             mAuthStatusText = R.string.auth_timeout_title;
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700 1017)             break;
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700 1018)         case INCORRECT_ADDRESS:
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700 1019)             mAuthStatusText = R.string.auth_incorrect_address_title;
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700 1020)             break;
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700 1021)         case SSL_ERROR:
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700 1022)             mAuthStatusText = R.string.auth_ssl_general_error_title;
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700 1023)             break;
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700 1024)         case UNAUTHORIZED:
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700 1025)             mAuthStatusText = R.string.auth_unauthorized;
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700 1026)             break;
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700 1027)         case HOST_NOT_AVAILABLE:
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700 1028)             mAuthStatusText = R.string.auth_unknown_host_title;
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700 1029)             break;
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700 1030)         case INSTANCE_NOT_CONFIGURED:
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700 1031)             mAuthStatusText = R.string.auth_not_configured_title;
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700 1032)             break;
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700 1033)         case FILE_NOT_FOUND:
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700 1034)             mAuthStatusText = R.string.auth_incorrect_path_title;
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700 1035)             break;
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700 1036)         case OAUTH2_ERROR:
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700 1037)             mAuthStatusText = R.string.auth_oauth_error;
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700 1038)             break;
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700 1039)         case OAUTH2_ERROR_ACCESS_DENIED:
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700 1040)             mAuthStatusText = R.string.auth_oauth_error_access_denied;
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700 1041)             break;
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700 1042)         case ACCOUNT_NOT_NEW:
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700 1043)             mAuthStatusText = R.string.auth_account_not_new;
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700 1044)             break;
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700 1045)         case ACCOUNT_NOT_THE_SAME:
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700 1046)             mAuthStatusText = R.string.auth_account_not_the_same;
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700 1047)             break;
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700 1048)         case UNHANDLED_HTTP_CODE:
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700 1049)         case UNKNOWN_ERROR:
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700 1050)             mAuthStatusText = R.string.auth_unknown_error_title;
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700 1051)             break;
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700 1052)         default:
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700 1053)             mAuthStatusText = 0;
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700 1054)             mAuthStatusIcon = 0;
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700 1055)         }
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700 1056)     }
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700 1057) 
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700 1058) 
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700 1059)     /**
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700 1060)      * Processes the result of the request for and access token send 
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700 1061)      * to an OAuth authorization server.
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700 1062)      * 
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700 1063)      * @param operation     Operation performed requesting the access token.
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700 1064)      * @param result        Result of the operation.
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700 1065)      */
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700 1066)     private void onGetOAuthAccessTokenFinish(OAuth2GetAccessToken operation, RemoteOperationResult result) {
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700 1067)         try {
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700 1068)             dismissDialog(DIALOG_OAUTH2_LOGIN_PROGRESS);
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700 1069)         } catch (IllegalArgumentException e) {
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700 1070)             // NOTHING TO DO ; can't find out what situation that leads to the exception in this code, but user logs signal that it happens
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700 1071)         }
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700 1072) 
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700 1073)         String webdav_path = AccountUtils.getWebdavPath(mDiscoveredVersion, mAuthTokenType);
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700 1074)         if (result.isSuccess() && webdav_path != null) {
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700 1075)             /// be gentle with the user
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700 1076)             showDialog(DIALOG_LOGIN_PROGRESS);
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700 1077) 
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700 1078)             /// time to test the retrieved access token on the ownCloud server
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700 1079)             mAuthToken = ((OAuth2GetAccessToken)operation).getResultTokenMap().get(OAuth2Constants.KEY_ACCESS_TOKEN);
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700 1080)             Log_OC.d(TAG, "Got ACCESS TOKEN: " + mAuthToken);
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700 1081)             mAuthCheckOperation = new ExistenceCheckOperation("", this, false);
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700 1082)             WebdavClient client = OwnCloudClientUtils.createOwnCloudClient(Uri.parse(mHostBaseUrl + webdav_path), this, true);
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700 1083)             client.setBearerCredentials(mAuthToken);
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700 1084)             mAuthCheckOperation.execute(client, this, mHandler);
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700 1085) 
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700 1086)         } else {
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700 1087)             updateAuthStatusIconAndText(result);
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700 1088)             showAuthStatus();
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700 1089)             Log_OC.d(TAG, "Access failed: " + result.getLogMessage());
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700 1090)         }
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700 1091)     }
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700 1092) 
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700 1093) 
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700 1094)     /**
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700 1095)      * Processes the result of the access check performed to try the user credentials.
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700 1096)      * 
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700 1097)      * Creates a new account through the AccountManager.
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700 1098)      * 
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700 1099)      * @param operation     Access check performed.
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700 1100)      * @param result        Result of the operation.
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700 1101)      */
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700 1102)     private void onAuthorizationCheckFinish(ExistenceCheckOperation operation, RemoteOperationResult result) {
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700 1103)         try {
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700 1104)             dismissDialog(DIALOG_LOGIN_PROGRESS);
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700 1105)         } catch (IllegalArgumentException e) {
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700 1106)             // NOTHING TO DO ; can't find out what situation that leads to the exception in this code, but user logs signal that it happens
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700 1107)         }
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700 1108) 
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700 1109)         if (result.isSuccess()) {
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700 1110)             Log_OC.d(TAG, "Successful access - time to save the account");
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700 1111) 
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700 1112)             boolean success = false;
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700 1113)             if (mAction == ACTION_CREATE) {
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700 1114)                 success = createAccount();
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700 1115) 
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700 1116)             } else {
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700 1117)                 success = updateToken();
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700 1118)             }
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700 1119) 
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700 1120)             if (success) {
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700 1121)                 finish();
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700 1122)             }
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700 1123) 
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700 1124)         } else if (result.isServerFail() || result.isException()) {
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700 1125)             /// if server fail or exception in authorization, the UI is updated as when a server check failed
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700 1126)             mServerIsChecked = true;
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700 1127)             mServerIsValid = false;
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700 1128)             mIsSslConn = false;
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700 1129)             mOcServerChkOperation = null;
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700 1130)             mDiscoveredVersion = null;
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700 1131)             mHostBaseUrl = normalizeUrl(mHostUrlInput.getText().toString());
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700 1132) 
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700 1133)             // update status icon and text
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700 1134)             updateServerStatusIconAndText(result);
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700 1135)             showServerStatus();
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700 1136)             mAuthStatusIcon = 0;
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700 1137)             mAuthStatusText = 0;
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700 1138)             showAuthStatus();
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700 1139)             
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700 1140)             // update input controls state
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700 1141)             showRefreshButton();
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700 1142)             mOkButton.setEnabled(false);
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700 1143) 
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700 1144)             // very special case (TODO: move to a common place for all the remote operations) (dangerous here?)
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700 1145)             if (result.getCode() == ResultCode.SSL_RECOVERABLE_PEER_UNVERIFIED) {
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700 1146)                 mLastSslUntrustedServerResult = result;
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700 1147)                 showDialog(DIALOG_SSL_VALIDATOR); 
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700 1148)             }
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700 1149) 
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700 1150)         } else {    // authorization fail due to client side - probably wrong credentials
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700 1151)             updateAuthStatusIconAndText(result);
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700 1152)             showAuthStatus();
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700 1153)             Log_OC.d(TAG, "Access failed: " + result.getLogMessage());
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700 1154)         }
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700 1155) 
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700 1156)     }
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700 1157) 
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700 1158) 
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700 1159)     /**
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700 1160)      * Sets the proper response to get that the Account Authenticator that started this activity saves 
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700 1161)      * a new authorization token for mAccount.
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700 1162)      */
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700 1163)     private boolean updateToken() {
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700 1164)         Bundle response = new Bundle();
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700 1165)         response.putString(AccountManager.KEY_ACCOUNT_NAME, mAccount.name);
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700 1166)         response.putString(AccountManager.KEY_ACCOUNT_TYPE, mAccount.type);
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700 1167)         
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700 1168)         if (AccountAuthenticator.AUTH_TOKEN_TYPE_ACCESS_TOKEN.equals(mAuthTokenType)) { 
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700 1169)             response.putString(AccountManager.KEY_AUTHTOKEN, mAuthToken);
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700 1170)             // the next line is necessary; by now, notifications are calling directly to the AuthenticatorActivity to update, without AccountManager intervention
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700 1171)             mAccountMgr.setAuthToken(mAccount, mAuthTokenType, mAuthToken);
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700 1172)             
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700 1173)         } else if (AccountAuthenticator.AUTH_TOKEN_TYPE_SAML_WEB_SSO_SESSION_COOKIE.equals(mAuthTokenType)) {
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700 1174)             String username = getUserNameForSamlSso();
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700 1175)             if (!mUsernameInput.getText().toString().equals(username)) {
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700 1176)                 // fail - not a new account, but an existing one; disallow
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700 1177)                 RemoteOperationResult result = new RemoteOperationResult(ResultCode.ACCOUNT_NOT_THE_SAME); 
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700 1178)                 updateAuthStatusIconAndText(result);
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700 1179)                 showAuthStatus();
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700 1180)                 Log_OC.d(TAG, result.getLogMessage());
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700 1181)                 
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700 1182)                 return false;
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700 1183)             }
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700 1184)             
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700 1185)             response.putString(AccountManager.KEY_AUTHTOKEN, mAuthToken);
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700 1186)             // the next line is necessary; by now, notifications are calling directly to the AuthenticatorActivity to update, without AccountManager intervention
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700 1187)             mAccountMgr.setAuthToken(mAccount, mAuthTokenType, mAuthToken);
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700 1188)             
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700 1189)         } else {
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700 1190)             response.putString(AccountManager.KEY_AUTHTOKEN, mPasswordInput.getText().toString());
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700 1191)             mAccountMgr.setPassword(mAccount, mPasswordInput.getText().toString());
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700 1192)         }
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700 1193)         setAccountAuthenticatorResult(response);
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700 1194)         
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700 1195)         return true;
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700 1196)     }
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700 1197) 
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700 1198) 
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700 1199)     /**
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700 1200)      * Creates a new account through the Account Authenticator that started this activity. 
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700 1201)      * 
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700 1202)      * This makes the account permanent.
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700 1203)      * 
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700 1204)      * TODO Decide how to name the OAuth accounts
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700 1205)      */
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700 1206)     private boolean createAccount() {
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700 1207)         /// create and save new ownCloud account
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700 1208)         boolean isOAuth = AccountAuthenticator.AUTH_TOKEN_TYPE_ACCESS_TOKEN.equals(mAuthTokenType);
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700 1209)         boolean isSaml =  AccountAuthenticator.AUTH_TOKEN_TYPE_SAML_WEB_SSO_SESSION_COOKIE.equals(mAuthTokenType);
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700 1210) 
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700 1211)         Uri uri = Uri.parse(mHostBaseUrl);
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700 1212)         String username = mUsernameInput.getText().toString().trim();
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700 1213)         if (isSaml) {
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700 1214)             username = getUserNameForSamlSso();
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700 1215)             
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700 1216)         } else if (isOAuth) {
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700 1217)             username = "OAuth_user" + (new java.util.Random(System.currentTimeMillis())).nextLong();
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700 1218)         }            
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700 1219)         String accountName = username + "@" + uri.getHost();
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700 1220)         if (uri.getPort() >= 0) {
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700 1221)             accountName += ":" + uri.getPort();
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700 1222)         }
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700 1223)         mAccount = new Account(accountName, AccountAuthenticator.ACCOUNT_TYPE);
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700 1224)         if (AccountUtils.exists(mAccount, getApplicationContext())) {
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700 1225)             // fail - not a new account, but an existing one; disallow
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700 1226)             RemoteOperationResult result = new RemoteOperationResult(ResultCode.ACCOUNT_NOT_NEW); 
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700 1227)             updateAuthStatusIconAndText(result);
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700 1228)             showAuthStatus();
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700 1229)             Log_OC.d(TAG, result.getLogMessage());
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700 1230)             return false;
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700 1231)             
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700 1232)         } else {
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700 1233)         
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700 1234)             if (isOAuth || isSaml) {
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700 1235)                 mAccountMgr.addAccountExplicitly(mAccount, "", null);  // with external authorizations, the password is never input in the app
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700 1236)             } else {
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700 1237)                 mAccountMgr.addAccountExplicitly(mAccount, mPasswordInput.getText().toString(), null);
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700 1238)             }
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700 1239)     
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700 1240)             /// add the new account as default in preferences, if there is none already
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700 1241)             Account defaultAccount = AccountUtils.getCurrentOwnCloudAccount(this);
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700 1242)             if (defaultAccount == null) {
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700 1243)                 SharedPreferences.Editor editor = PreferenceManager
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700 1244)                         .getDefaultSharedPreferences(this).edit();
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700 1245)                 editor.putString("select_oc_account", accountName);
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700 1246)                 editor.commit();
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700 1247)             }
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700 1248)     
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700 1249)             /// prepare result to return to the Authenticator
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700 1250)             //  TODO check again what the Authenticator makes with it; probably has the same effect as addAccountExplicitly, but it's not well done
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700 1251)             final Intent intent = new Intent();       
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700 1252)             intent.putExtra(AccountManager.KEY_ACCOUNT_TYPE,    AccountAuthenticator.ACCOUNT_TYPE);
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700 1253)             intent.putExtra(AccountManager.KEY_ACCOUNT_NAME,    mAccount.name);
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700 1254)             /*if (!isOAuth)
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700 1255)                 intent.putExtra(AccountManager.KEY_AUTHTOKEN,   AccountAuthenticator.ACCOUNT_TYPE); */
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700 1256)             intent.putExtra(AccountManager.KEY_USERDATA,        username);
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700 1257)             if (isOAuth || isSaml) {
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700 1258)                 mAccountMgr.setAuthToken(mAccount, mAuthTokenType, mAuthToken);
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700 1259)             }
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700 1260)             /// add user data to the new account; TODO probably can be done in the last parameter addAccountExplicitly, or in KEY_USERDATA
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700 1261)             mAccountMgr.setUserData(mAccount, AccountAuthenticator.KEY_OC_VERSION,    mDiscoveredVersion.toString());
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700 1262)             mAccountMgr.setUserData(mAccount, AccountAuthenticator.KEY_OC_BASE_URL,   mHostBaseUrl);
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700 1263)             if (isSaml) {
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700 1264)                 mAccountMgr.setUserData(mAccount, AccountAuthenticator.KEY_SUPPORTS_SAML_WEB_SSO, "TRUE"); 
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700 1265)             } else if (isOAuth) {
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700 1266)                 mAccountMgr.setUserData(mAccount, AccountAuthenticator.KEY_SUPPORTS_OAUTH2, "TRUE");  
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700 1267)             }
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700 1268)     
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700 1269)             setAccountAuthenticatorResult(intent.getExtras());
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700 1270)             setResult(RESULT_OK, intent);
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700 1271)     
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700 1272)             /// immediately request for the synchronization of the new account
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700 1273)             Bundle bundle = new Bundle();
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700 1274)             bundle.putBoolean(ContentResolver.SYNC_EXTRAS_MANUAL, true);
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700 1275)             ContentResolver.requestSync(mAccount, AccountAuthenticator.AUTHORITY, bundle);
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700 1276)             syncAccount();
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700 1277) //          Bundle bundle = new Bundle();
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700 1278) //          bundle.putBoolean(ContentResolver.SYNC_EXTRAS_MANUAL, true);
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700 1279) //          ContentResolver.requestSync(mAccount, AccountAuthenticator.AUTHORITY, bundle);
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700 1280)             return true;
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700 1281)         }
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700 1282)     }
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700 1283) 
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700 1284)     
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700 1285)     private String getUserNameForSamlSso() {
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700 1286)         if (mAuthToken != null) {
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700 1287)             String [] cookies = mAuthToken.split(";");
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700 1288)             for (int i=0; i<cookies.length; i++) {
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700 1289)                 if (cookies[i].startsWith(KEY_OC_USERNAME_EQUALS )) {
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700 1290)                     String value = Uri.decode(cookies[i].substring(KEY_OC_USERNAME_EQUALS.length()));
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700 1291)                     return value;
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700 1292)                 }
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700 1293)             }
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700 1294)         }
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700 1295)         return "";
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700 1296)     }
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700 1297) 
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700 1298) 
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700 1299)     /**
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700 1300)      * {@inheritDoc}
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700 1301)      * 
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700 1302)      * Necessary to update the contents of the SSL Dialog
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700 1303)      * 
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700 1304)      * TODO move to some common place for all possible untrusted SSL failures
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700 1305)      */
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700 1306)     @Override
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700 1307)     protected void onPrepareDialog(int id, Dialog dialog, Bundle args) {
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700 1308)         switch (id) {
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700 1309)         case DIALOG_LOGIN_PROGRESS:
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700 1310)         case DIALOG_CERT_NOT_SAVED:
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700 1311)         case DIALOG_OAUTH2_LOGIN_PROGRESS:
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700 1312)             break;
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700 1313)         case DIALOG_SSL_VALIDATOR: {
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700 1314)             ((SslValidatorDialog)dialog).updateResult(mLastSslUntrustedServerResult);
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700 1315)             break;
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700 1316)         }
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700 1317)         default:
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700 1318)             Log_OC.e(TAG, "Incorrect dialog called with id = " + id);
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700 1319)         }
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700 1320)     }
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700 1321) 
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700 1322) 
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700 1323)     /**
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700 1324)      * {@inheritDoc}
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700 1325)      */
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700 1326)     @Override
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700 1327)     protected Dialog onCreateDialog(int id) {
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700 1328)         Dialog dialog = null;
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700 1329)         switch (id) {
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700 1330)         case DIALOG_LOGIN_PROGRESS: {
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700 1331)             /// simple progress dialog
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700 1332)             ProgressDialog working_dialog = new ProgressDialog(this);
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700 1333)             working_dialog.setMessage(getResources().getString(R.string.auth_trying_to_login));
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700 1334)             working_dialog.setIndeterminate(true);
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700 1335)             working_dialog.setCancelable(true);
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700 1336)             working_dialog
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700 1337)             .setOnCancelListener(new DialogInterface.OnCancelListener() {
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700 1338)                 @Override
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700 1339)                 public void onCancel(DialogInterface dialog) {
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700 1340)                     /// TODO study if this is enough
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700 1341)                     Log_OC.i(TAG, "Login canceled");
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700 1342)                     if (mOperationThread != null) {
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700 1343)                         mOperationThread.interrupt();
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700 1344)                         finish();
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700 1345)                     }
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700 1346)                 }
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700 1347)             });
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700 1348)             dialog = working_dialog;
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700 1349)             break;
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700 1350)         }
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700 1351)         case DIALOG_OAUTH2_LOGIN_PROGRESS: {
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700 1352)             ProgressDialog working_dialog = new ProgressDialog(this);
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700 1353)             working_dialog.setMessage(String.format("Getting authorization")); 
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700 1354)             working_dialog.setIndeterminate(true);
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700 1355)             working_dialog.setCancelable(true);
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700 1356)             working_dialog
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700 1357)             .setOnCancelListener(new DialogInterface.OnCancelListener() {
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700 1358)                 @Override
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700 1359)                 public void onCancel(DialogInterface dialog) {
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700 1360)                     Log_OC.i(TAG, "Login canceled");
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700 1361)                     finish();
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700 1362)                 }
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700 1363)             });
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700 1364)             dialog = working_dialog;
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700 1365)             break;
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700 1366)         }
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700 1367)         case DIALOG_SSL_VALIDATOR: {
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700 1368)             /// TODO start to use new dialog interface, at least for this (it is a FragmentDialog already)
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700 1369)             dialog = SslValidatorDialog.newInstance(this, mLastSslUntrustedServerResult, this);
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700 1370)             break;
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700 1371)         }
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700 1372)         case DIALOG_CERT_NOT_SAVED: {
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700 1373)             AlertDialog.Builder builder = new AlertDialog.Builder(this);
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700 1374)             builder.setMessage(getResources().getString(R.string.ssl_validator_not_saved));
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700 1375)             builder.setCancelable(false);
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700 1376)             builder.setPositiveButton(R.string.common_ok, new DialogInterface.OnClickListener() {
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700 1377)                 @Override
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700 1378)                 public void onClick(DialogInterface dialog, int which) {
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700 1379)                     dialog.dismiss();
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700 1380)                 };
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700 1381)             });
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700 1382)             dialog = builder.create();
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700 1383)             break;
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700 1384)         }
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700 1385)         default:
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700 1386)             Log_OC.e(TAG, "Incorrect dialog called with id = " + id);
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700 1387)         }
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700 1388)         return dialog;
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700 1389)     }
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700 1390) 
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700 1391) 
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700 1392)     /**
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700 1393)      * Starts and activity to open the 'new account' page in the ownCloud web site
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700 1394)      * 
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700 1395)      * @param view      'Account register' button
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700 1396)      */
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700 1397)     public void onRegisterClick(View view) {
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700 1398)         Intent register = new Intent(Intent.ACTION_VIEW, Uri.parse(getString(R.string.url_account_register)));
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700 1399)         setResult(RESULT_CANCELED);
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700 1400)         startActivity(register);
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700 1401)     }
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700 1402) 
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700 1403) 
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700 1404)     /**
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700 1405)      * Updates the content and visibility state of the icon and text associated
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700 1406)      * to the last check on the ownCloud server.
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700 1407)      */
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700 1408)     private void showServerStatus() {
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700 1409)         TextView tv = (TextView) findViewById(R.id.server_status_text);
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700 1410) 
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700 1411)         if (mServerStatusIcon == 0 && mServerStatusText == 0) {
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700 1412)             tv.setVisibility(View.INVISIBLE);
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700 1413) 
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700 1414)         } else {
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700 1415)             tv.setText(mServerStatusText);
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700 1416)             tv.setCompoundDrawablesWithIntrinsicBounds(mServerStatusIcon, 0, 0, 0);
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700 1417)             tv.setVisibility(View.VISIBLE);
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700 1418)         }
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700 1419) 
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700 1420)     }
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700 1421) 
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700 1422) 
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700 1423)     /**
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700 1424)      * Updates the content and visibility state of the icon and text associated
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700 1425)      * to the interactions with the OAuth authorization server.
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700 1426)      */
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700 1427)     private void showAuthStatus() {
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700 1428)         if (mAuthStatusIcon == 0 && mAuthStatusText == 0) {
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700 1429)             mAuthStatusLayout.setVisibility(View.INVISIBLE);
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700 1430) 
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700 1431)         } else {
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700 1432)             mAuthStatusLayout.setText(mAuthStatusText);
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700 1433)             mAuthStatusLayout.setCompoundDrawablesWithIntrinsicBounds(mAuthStatusIcon, 0, 0, 0);
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700 1434)             mAuthStatusLayout.setVisibility(View.VISIBLE);
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700 1435)         }
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700 1436)     }     
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700 1437) 
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700 1438) 
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700 1439)     private void showRefreshButton() {
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700 1440)         mRefreshButton.setVisibility(View.VISIBLE);
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700 1441)     }
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700 1442) 
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700 1443)     private void hideRefreshButton() {
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700 1444)         mRefreshButton.setVisibility(View.GONE);
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700 1445)     }
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700 1446) 
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700 1447)     /**
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700 1448)      * Called when the refresh button in the input field for ownCloud host is clicked.
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700 1449)      * 
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700 1450)      * Performs a new check on the URL in the input field.
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700 1451)      * 
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700 1452)      * @param view      Refresh 'button'
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700 1453)      */
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700 1454)     public void onRefreshClick(View view) {
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700 1455)         checkOcServer();
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700 1456)     }
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700 1457)     
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700 1458)     
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700 1459)     /**
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700 1460)      * Called when the eye icon in the password field is clicked.
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700 1461)      * 
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700 1462)      * Toggles the visibility of the password in the field. 
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700 1463)      */
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700 1464)     public void onViewPasswordClick() {
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700 1465)         int selectionStart = mPasswordInput.getSelectionStart();
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700 1466)         int selectionEnd = mPasswordInput.getSelectionEnd();
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700 1467)         if (isPasswordVisible()) {
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700 1468)             hidePassword();
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700 1469)         } else {
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700 1470)             showPassword();
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700 1471)         }
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700 1472)         mPasswordInput.setSelection(selectionStart, selectionEnd);
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700 1473)     }    
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700 1474) 
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700 1475) 
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700 1476)     /**
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700 1477)      * Called when the checkbox for OAuth authorization is clicked.
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700 1478)      * 
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700 1479)      * Hides or shows the input fields for user & password. 
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700 1480)      * 
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700 1481)      * @param view      'View password' 'button'
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700 1482)      */
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700 1483)     public void onCheckClick(View view) {
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700 1484)         CheckBox oAuth2Check = (CheckBox)view;
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700 1485)         if (oAuth2Check.isChecked()) {
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700 1486)             mAuthTokenType = AccountAuthenticator.AUTH_TOKEN_TYPE_ACCESS_TOKEN;
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700 1487)         } else {
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700 1488)             mAuthTokenType = AccountAuthenticator.AUTH_TOKEN_TYPE_PASSWORD;
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700 1489)         }
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700 1490)         adaptViewAccordingToAuthenticationMethod();
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700 1491)     }
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700 1492) 
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700 1493)     
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700 1494)     /**
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700 1495)      * Changes the visibility of input elements depending on
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700 1496)      * the current authorization method.
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700 1497)      */
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700 1498)     private void adaptViewAccordingToAuthenticationMethod () {
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700 1499)         if (AccountAuthenticator.AUTH_TOKEN_TYPE_ACCESS_TOKEN.equals(mAuthTokenType)) {
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700 1500)             // OAuth 2 authorization
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700 1501)             mOAuthAuthEndpointText.setVisibility(View.VISIBLE);
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700 1502)             mOAuthTokenEndpointText.setVisibility(View.VISIBLE);
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700 1503)             mUsernameInput.setVisibility(View.GONE);
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700 1504)             mPasswordInput.setVisibility(View.GONE);
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700 1505)             
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700 1506)         } else if (AccountAuthenticator.AUTH_TOKEN_TYPE_SAML_WEB_SSO_SESSION_COOKIE.equals(mAuthTokenType)) {
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700 1507)             // SAML-based web Single Sign On
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700 1508)             mOAuthAuthEndpointText.setVisibility(View.GONE);
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700 1509)             mOAuthTokenEndpointText.setVisibility(View.GONE);
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700 1510)             mUsernameInput.setVisibility(View.GONE);
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700 1511)             mPasswordInput.setVisibility(View.GONE);
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700 1512)         } else {
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700 1513)             // basic HTTP authorization
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700 1514)             mOAuthAuthEndpointText.setVisibility(View.GONE);
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700 1515)             mOAuthTokenEndpointText.setVisibility(View.GONE);
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700 1516)             mUsernameInput.setVisibility(View.VISIBLE);
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700 1517)             mPasswordInput.setVisibility(View.VISIBLE);
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700 1518)         }
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700 1519)     }
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700 1520)     
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700 1521)     /**
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700 1522)      * Called from SslValidatorDialog when a new server certificate was correctly saved.
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700 1523)      */
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700 1524)     public void onSavedCertificate() {
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700 1525)         checkOcServer();
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700 1526)     }
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700 1527) 
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700 1528)     /**
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700 1529)      * Called from SslValidatorDialog when a new server certificate could not be saved 
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700 1530)      * when the user requested it.
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700 1531)      */
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700 1532)     @Override
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700 1533)     public void onFailedSavingCertificate() {
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700 1534)         showDialog(DIALOG_CERT_NOT_SAVED);
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700 1535)     }
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700 1536) 
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700 1537) 
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700 1538)     /**
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700 1539)      *  Called when the 'action' button in an IME is pressed ('enter' in software keyboard).
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700 1540)      * 
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700 1541)      *  Used to trigger the authentication check when the user presses 'enter' after writing the password, 
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700 1542)      *  or to throw the server test when the only field on screen is the URL input field.
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700 1543)      */
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700 1544)     @Override
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700 1545)     public boolean onEditorAction(TextView inputField, int actionId, KeyEvent event) {
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700 1546)         if (actionId == EditorInfo.IME_ACTION_DONE && inputField != null && inputField.equals(mPasswordInput)) {
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700 1547)             if (mOkButton.isEnabled()) {
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700 1548)                 mOkButton.performClick();
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700 1549)             }
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700 1550)             
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700 1551)         } else if (actionId == EditorInfo.IME_ACTION_NEXT && inputField != null && inputField.equals(mHostUrlInput)) {
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700 1552)             if (AccountAuthenticator.AUTH_TOKEN_TYPE_SAML_WEB_SSO_SESSION_COOKIE.equals(mAuthTokenType)) {
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700 1553)                 checkOcServer();
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700 1554)             }
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700 1555)         }
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700 1556)         return false;   // always return false to grant that the software keyboard is hidden anyway
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700 1557)     }
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700 1558) 
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700 1559) 
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700 1560)     private abstract static class RightDrawableOnTouchListener implements OnTouchListener  {
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700 1561) 
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700 1562)         private int fuzz = 75;
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700 1563)         
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700 1564)         /**
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700 1565)          * {@inheritDoc}
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700 1566)          */
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700 1567)         @Override
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700 1568)         public boolean onTouch(View view, MotionEvent event) {
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700 1569)             Drawable rightDrawable = null;
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700 1570)             if (view instanceof TextView) {
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700 1571)                 Drawable[] drawables = ((TextView)view).getCompoundDrawables();
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700 1572)                 if (drawables.length > 2) {
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700 1573)                     rightDrawable = drawables[2];
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700 1574)                 }
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700 1575)             }
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700 1576)             if (rightDrawable != null) {
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700 1577)                 final int x = (int) event.getX();
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700 1578)                 final int y = (int) event.getY();
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700 1579)                 final Rect bounds = rightDrawable.getBounds();
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700 1580)                 if (x >= (view.getRight() - bounds.width() - fuzz) && x <= (view.getRight() - view.getPaddingRight() + fuzz)
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700 1581)                     && y >= (view.getPaddingTop() - fuzz) && y <= (view.getHeight() - view.getPaddingBottom()) + fuzz) {
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700 1582)                     
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700 1583)                     return onDrawableTouch(event);
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700 1584)                 }
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700 1585)             }
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700 1586)             return false;
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700 1587)         }
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700 1588) 
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700 1589)         public abstract boolean onDrawableTouch(final MotionEvent event);
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700 1590)     }
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700 1591) 
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700 1592) 
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700 1593)     public void onSamlDialogSuccess(String sessionCookie){
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700 1594)         mAuthToken = sessionCookie;
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700 1595)         
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700 1596)         if (sessionCookie != null && sessionCookie.length() > 0) {
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700 1597)             mAuthToken = sessionCookie;
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700 1598)             boolean success = false;
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700 1599)             if (mAction == ACTION_CREATE) {
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700 1600)                 success = createAccount();
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700 1601)         
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700 1602)             } else {
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700 1603)                 success = updateToken();
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700 1604)             }
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700 1605)             if (success) {
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700 1606)                 finish();
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700 1607)             }
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700 1608)         }
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700 1609) 
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700 1610)             
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700 1611)     }
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700 1612) 
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700 1613) 
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700 1614)     @Override
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700 1615)     public void onSsoFinished(String sessionCookies) {
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700 1616)         //Toast.makeText(this, "got cookies: " + sessionCookie, Toast.LENGTH_LONG).show();
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700 1617) 
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700 1618)         if (sessionCookies != null && sessionCookies.length() > 0) {
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700 1619)             Log_OC.d(TAG, "Successful SSO - time to save the account");
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700 1620)             onSamlDialogSuccess(sessionCookies);
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700 1621)             Fragment fd = getSupportFragmentManager().findFragmentByTag(TAG_SAML_DIALOG);
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700 1622)             if (fd != null && fd instanceof SherlockDialogFragment) {
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700 1623)                 Dialog d = ((SherlockDialogFragment)fd).getDialog();
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700 1624)                 if (d != null && d.isShowing()) {
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700 1625)                     d.dismiss();
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700 1626)                 }
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700 1627)             }
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700 1628) 
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700 1629)         } else { 
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700 1630)             // TODO - show fail
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700 1631)             Log_OC.d(TAG, "SSO failed");
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700 1632)         }
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700 1633)     
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700 1634)     }
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700 1635)     
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700 1636)     /** Show auth_message 
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700 1637)      * 
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700 1638)      * @param message
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700 1639)      */
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700 1640)     private void showAuthMessage(String message) {
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700 1641)        mAuthMessage.setVisibility(View.VISIBLE);
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700 1642)        mAuthMessage.setText(message);
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700 1643)     }
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700 1644)     
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700 1645)     private void hideAuthMessage() {
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700 1646)         mAuthMessage.setVisibility(View.GONE);
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700 1647)     }
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700 1648) 
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700 1649)     private void syncAccount(){
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700 1650)         /// immediately request for the synchronization of the new account
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700 1651)         Bundle bundle = new Bundle();
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700 1652)         bundle.putBoolean(ContentResolver.SYNC_EXTRAS_MANUAL, true);
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700 1653)         ContentResolver.requestSync(mAccount, AccountAuthenticator.AUTHORITY, bundle);
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700 1654)     }
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700 1655)     
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700 1656)     @Override
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700 1657)     public boolean onTouchEvent(MotionEvent event) {
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700 1658)         if (AccountAuthenticator.AUTH_TOKEN_TYPE_SAML_WEB_SSO_SESSION_COOKIE.equals(mAuthTokenType) &&
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700 1659)                 mHostUrlInput.hasFocus() && event.getAction() == MotionEvent.ACTION_DOWN) {
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700 1660)             checkOcServer();
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700 1661)         }
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700 1662)         return super.onTouchEvent(event);
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700 1663)     }
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700 1664) }
