f6780fdc (David A. Velasco 2013-05-07 12:54:31 +0200   1) /* ownCloud Android client application
f6780fdc (David A. Velasco 2013-05-07 12:54:31 +0200   2)  *   Copyright (C) 2011  Bartek Przybylski
f6780fdc (David A. Velasco 2013-05-07 12:54:31 +0200   3)  *   Copyright (C) 2012-2013 ownCloud Inc.
f6780fdc (David A. Velasco 2013-05-07 12:54:31 +0200   4)  *
f6780fdc (David A. Velasco 2013-05-07 12:54:31 +0200   5)  *   This program is free software: you can redistribute it and/or modify
f6780fdc (David A. Velasco 2013-05-07 12:54:31 +0200   6)  *   it under the terms of the GNU General Public License version 2,
f6780fdc (David A. Velasco 2013-05-07 12:54:31 +0200   7)  *   as published by the Free Software Foundation.
f6780fdc (David A. Velasco 2013-05-07 12:54:31 +0200   8)  *
f6780fdc (David A. Velasco 2013-05-07 12:54:31 +0200   9)  *   This program is distributed in the hope that it will be useful,
f6780fdc (David A. Velasco 2013-05-07 12:54:31 +0200  10)  *   but WITHOUT ANY WARRANTY; without even the implied warranty of
f6780fdc (David A. Velasco 2013-05-07 12:54:31 +0200  11)  *   MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
f6780fdc (David A. Velasco 2013-05-07 12:54:31 +0200  12)  *   GNU General Public License for more details.
f6780fdc (David A. Velasco 2013-05-07 12:54:31 +0200  13)  *
f6780fdc (David A. Velasco 2013-05-07 12:54:31 +0200  14)  *   You should have received a copy of the GNU General Public License
f6780fdc (David A. Velasco 2013-05-07 12:54:31 +0200  15)  *   along with this program.  If not, see <http://www.gnu.org/licenses/>.
f6780fdc (David A. Velasco 2013-05-07 12:54:31 +0200  16)  *
f6780fdc (David A. Velasco 2013-05-07 12:54:31 +0200  17)  */
f6780fdc (David A. Velasco 2013-05-07 12:54:31 +0200  18) 
f6780fdc (David A. Velasco 2013-05-07 12:54:31 +0200  19) package com.owncloud.android.ui.activity;
f6780fdc (David A. Velasco 2013-05-07 12:54:31 +0200  20) 
f6780fdc (David A. Velasco 2013-05-07 12:54:31 +0200  21) import android.accounts.Account;
f6780fdc (David A. Velasco 2013-05-07 12:54:31 +0200  22) import android.accounts.AccountManager;
f6780fdc (David A. Velasco 2013-05-07 12:54:31 +0200  23) import android.accounts.AccountManagerCallback;
f6780fdc (David A. Velasco 2013-05-07 12:54:31 +0200  24) import android.accounts.AccountManagerFuture;
f6780fdc (David A. Velasco 2013-05-07 12:54:31 +0200  25) import android.accounts.OperationCanceledException;
edb380e0 (David A. Velasco 2013-05-27 15:01:54 +0200  26) import android.content.Intent;
edb380e0 (David A. Velasco 2013-05-27 15:01:54 +0200  27) import android.net.Uri;
f6780fdc (David A. Velasco 2013-05-07 12:54:31 +0200  28) import android.os.Bundle;
edb380e0 (David A. Velasco 2013-05-27 15:01:54 +0200  29) import android.webkit.MimeTypeMap;
f6780fdc (David A. Velasco 2013-05-07 12:54:31 +0200  30) 
f6780fdc (David A. Velasco 2013-05-07 12:54:31 +0200  31) import com.actionbarsherlock.app.SherlockFragmentActivity;
f6780fdc (David A. Velasco 2013-05-07 12:54:31 +0200  32) import com.owncloud.android.Log_OC;
f767bf3f (David A. Velasco 2013-05-28 13:11:57 +0200  33) import com.owncloud.android.R;
f6780fdc (David A. Velasco 2013-05-07 12:54:31 +0200  34) import com.owncloud.android.authentication.AccountAuthenticator;
c4ac05de (David A. Velasco 2013-06-18 11:34:08 +0200  35) import com.owncloud.android.authentication.AccountUtils;
f6780fdc (David A. Velasco 2013-05-07 12:54:31 +0200  36) import com.owncloud.android.datamodel.OCFile;
edb380e0 (David A. Velasco 2013-05-27 15:01:54 +0200  37) 
edb380e0 (David A. Velasco 2013-05-27 15:01:54 +0200  38) import eu.alefzero.webdav.WebdavUtils;
f6780fdc (David A. Velasco 2013-05-07 12:54:31 +0200  39) 
f6780fdc (David A. Velasco 2013-05-07 12:54:31 +0200  40) /**
f6780fdc (David A. Velasco 2013-05-07 12:54:31 +0200  41)  * Activity with common behaviour for activities handling {@link OCFile}s in ownCloud {@link Account}s .
f6780fdc (David A. Velasco 2013-05-07 12:54:31 +0200  42)  * 
f6780fdc (David A. Velasco 2013-05-07 12:54:31 +0200  43)  * @author David A. Velasco
f6780fdc (David A. Velasco 2013-05-07 12:54:31 +0200  44)  */
fd396289 (David A. Velasco 2013-05-30 17:53:21 +0200  45) public abstract class FileActivity extends SherlockFragmentActivity {
f6780fdc (David A. Velasco 2013-05-07 12:54:31 +0200  46) 
f6780fdc (David A. Velasco 2013-05-07 12:54:31 +0200  47)     public static final String EXTRA_FILE = "com.owncloud.android.ui.activity.FILE";
f6780fdc (David A. Velasco 2013-05-07 12:54:31 +0200  48)     public static final String EXTRA_ACCOUNT = "com.owncloud.android.ui.activity.ACCOUNT";
c70f3333 (David A. Velasco 2013-06-04 14:30:29 +0200  49)     public static final String EXTRA_WAITING_TO_PREVIEW = "com.owncloud.android.ui.activity.WAITING_TO_PREVIEW";
f6780fdc (David A. Velasco 2013-05-07 12:54:31 +0200  50)     
f6780fdc (David A. Velasco 2013-05-07 12:54:31 +0200  51)     public static final String TAG = FileActivity.class.getSimpleName(); 
f6780fdc (David A. Velasco 2013-05-07 12:54:31 +0200  52)     
f6780fdc (David A. Velasco 2013-05-07 12:54:31 +0200  53)     
f6780fdc (David A. Velasco 2013-05-07 12:54:31 +0200  54)     /** OwnCloud {@link Account} where the main {@link OCFile} handled by the activity is located. */
f6780fdc (David A. Velasco 2013-05-07 12:54:31 +0200  55)     private Account mAccount;
f6780fdc (David A. Velasco 2013-05-07 12:54:31 +0200  56)     
f6780fdc (David A. Velasco 2013-05-07 12:54:31 +0200  57)     /** Main {@link OCFile} handled by the activity.*/
f6780fdc (David A. Velasco 2013-05-07 12:54:31 +0200  58)     private OCFile mFile;
f6780fdc (David A. Velasco 2013-05-07 12:54:31 +0200  59)     
f6780fdc (David A. Velasco 2013-05-07 12:54:31 +0200  60)     /** Flag to signal that the activity will is finishing to enforce the creation of an ownCloud {@link Account} */
f6780fdc (David A. Velasco 2013-05-07 12:54:31 +0200  61)     private boolean mRedirectingToSetupAccount = false;
edb380e0 (David A. Velasco 2013-05-27 15:01:54 +0200  62)     
f9babe7b (David A. Velasco 2013-06-14 12:53:45 +0200  63)     /** Flag to signal when the value of mAccount was set */ 
f9babe7b (David A. Velasco 2013-06-14 12:53:45 +0200  64)     private boolean mAccountWasSet;
f9babe7b (David A. Velasco 2013-06-14 12:53:45 +0200  65)     
f9babe7b (David A. Velasco 2013-06-14 12:53:45 +0200  66)     /** Flag to signal when the value of mAccount was restored from a saved state */ 
f9babe7b (David A. Velasco 2013-06-14 12:53:45 +0200  67)     private boolean mAccountWasRestored;
c70f3333 (David A. Velasco 2013-06-04 14:30:29 +0200  68) 
82375a91 (David A. Velasco 2013-07-02 12:41:10 +0200  69)     
c70f3333 (David A. Velasco 2013-06-04 14:30:29 +0200  70)     /**
82375a91 (David A. Velasco 2013-07-02 12:41:10 +0200  71)      * Loads the ownCloud {@link Account} and main {@link OCFile} to be handled by the instance of 
c70f3333 (David A. Velasco 2013-06-04 14:30:29 +0200  72)      * the {@link FileActivity}.
c70f3333 (David A. Velasco 2013-06-04 14:30:29 +0200  73)      * 
c70f3333 (David A. Velasco 2013-06-04 14:30:29 +0200  74)      * Grants that a valid ownCloud {@link Account} is associated to the instance, or that the user 
c70f3333 (David A. Velasco 2013-06-04 14:30:29 +0200  75)      * is requested to create a new one.
c70f3333 (David A. Velasco 2013-06-04 14:30:29 +0200  76)      */
f6780fdc (David A. Velasco 2013-05-07 12:54:31 +0200  77)     @Override
979bdc83 (David A. Velasco 2013-05-07 13:05:40 +0200  78)     protected void onCreate(Bundle savedInstanceState) {
f6780fdc (David A. Velasco 2013-05-07 12:54:31 +0200  79)         super.onCreate(savedInstanceState);
c70f3333 (David A. Velasco 2013-06-04 14:30:29 +0200  80) 
82375a91 (David A. Velasco 2013-07-02 12:41:10 +0200  81)         Account account;
f6780fdc (David A. Velasco 2013-05-07 12:54:31 +0200  82)         if(savedInstanceState != null) {
82375a91 (David A. Velasco 2013-07-02 12:41:10 +0200  83)             account = savedInstanceState.getParcelable(FileActivity.EXTRA_ACCOUNT);
f6780fdc (David A. Velasco 2013-05-07 12:54:31 +0200  84)             mFile = savedInstanceState.getParcelable(FileActivity.EXTRA_FILE);
f6780fdc (David A. Velasco 2013-05-07 12:54:31 +0200  85)         } else {
82375a91 (David A. Velasco 2013-07-02 12:41:10 +0200  86)             account = getIntent().getParcelableExtra(FileActivity.EXTRA_ACCOUNT);
f6780fdc (David A. Velasco 2013-05-07 12:54:31 +0200  87)             mFile = getIntent().getParcelableExtra(FileActivity.EXTRA_FILE);
f6780fdc (David A. Velasco 2013-05-07 12:54:31 +0200  88)         }
c70f3333 (David A. Velasco 2013-06-04 14:30:29 +0200  89) 
82375a91 (David A. Velasco 2013-07-02 12:41:10 +0200  90)         setAccount(account, savedInstanceState != null);
f6780fdc (David A. Velasco 2013-05-07 12:54:31 +0200  91)     }
f6780fdc (David A. Velasco 2013-05-07 12:54:31 +0200  92) 
f6780fdc (David A. Velasco 2013-05-07 12:54:31 +0200  93)     
979bdc83 (David A. Velasco 2013-05-07 13:05:40 +0200  94)     /**
c70f3333 (David A. Velasco 2013-06-04 14:30:29 +0200  95)      *  Since ownCloud {@link Account}s can be managed from the system setting menu, 
c70f3333 (David A. Velasco 2013-06-04 14:30:29 +0200  96)      *  the existence of the {@link Account} associated to the instance must be checked 
c70f3333 (David A. Velasco 2013-06-04 14:30:29 +0200  97)      *  every time it is restarted.
979bdc83 (David A. Velasco 2013-05-07 13:05:40 +0200  98)      */
f6780fdc (David A. Velasco 2013-05-07 12:54:31 +0200  99)     @Override
c70f3333 (David A. Velasco 2013-06-04 14:30:29 +0200 100)     protected void onRestart() {
c70f3333 (David A. Velasco 2013-06-04 14:30:29 +0200 101)         super.onRestart();
c70f3333 (David A. Velasco 2013-06-04 14:30:29 +0200 102)         boolean validAccount = (mAccount != null && AccountUtils.setCurrentOwnCloudAccount(getApplicationContext(), mAccount.name));
c70f3333 (David A. Velasco 2013-06-04 14:30:29 +0200 103)         if (!validAccount) {
82375a91 (David A. Velasco 2013-07-02 12:41:10 +0200 104)             swapToDefaultAccount();
f6780fdc (David A. Velasco 2013-05-07 12:54:31 +0200 105)         }
82375a91 (David A. Velasco 2013-07-02 12:41:10 +0200 106)         
f6780fdc (David A. Velasco 2013-05-07 12:54:31 +0200 107)     }
82375a91 (David A. Velasco 2013-07-02 12:41:10 +0200 108) 
c70f3333 (David A. Velasco 2013-06-04 14:30:29 +0200 109)     
f9babe7b (David A. Velasco 2013-06-14 12:53:45 +0200 110)     @Override 
f9babe7b (David A. Velasco 2013-06-14 12:53:45 +0200 111)     protected void onStart() {
f9babe7b (David A. Velasco 2013-06-14 12:53:45 +0200 112)         super.onStart();
f9babe7b (David A. Velasco 2013-06-14 12:53:45 +0200 113)         if (mAccountWasSet) {
f9babe7b (David A. Velasco 2013-06-14 12:53:45 +0200 114)             onAccountSet(mAccountWasRestored);
f9babe7b (David A. Velasco 2013-06-14 12:53:45 +0200 115)         }
f9babe7b (David A. Velasco 2013-06-14 12:53:45 +0200 116)     }
f9babe7b (David A. Velasco 2013-06-14 12:53:45 +0200 117)     
f9babe7b (David A. Velasco 2013-06-14 12:53:45 +0200 118)     
f6780fdc (David A. Velasco 2013-05-07 12:54:31 +0200 119)     /**
82375a91 (David A. Velasco 2013-07-02 12:41:10 +0200 120)      *  Sets and validates the ownCloud {@link Account} associated to the Activity. 
82375a91 (David A. Velasco 2013-07-02 12:41:10 +0200 121)      * 
82375a91 (David A. Velasco 2013-07-02 12:41:10 +0200 122)      *  If not valid, tries to swap it for other valid and existing ownCloud {@link Account}.
82375a91 (David A. Velasco 2013-07-02 12:41:10 +0200 123)      *  
82375a91 (David A. Velasco 2013-07-02 12:41:10 +0200 124)      *  POSTCONDITION: updates {@link #mAccountWasSet} and {@link #mAccountWasRestored}. 
82375a91 (David A. Velasco 2013-07-02 12:41:10 +0200 125)      * 
82375a91 (David A. Velasco 2013-07-02 12:41:10 +0200 126)      *  @param account          New {@link Account} to set.
82375a91 (David A. Velasco 2013-07-02 12:41:10 +0200 127)      *  @param savedAccount     When 'true', account was retrieved from a saved instance state.
82375a91 (David A. Velasco 2013-07-02 12:41:10 +0200 128)      */
82375a91 (David A. Velasco 2013-07-02 12:41:10 +0200 129)     private void setAccount(Account account, boolean savedAccount) {
82375a91 (David A. Velasco 2013-07-02 12:41:10 +0200 130)         Account oldAccount = mAccount;
82375a91 (David A. Velasco 2013-07-02 12:41:10 +0200 131)         boolean validAccount = (account != null && AccountUtils.setCurrentOwnCloudAccount(getApplicationContext(), account.name));
82375a91 (David A. Velasco 2013-07-02 12:41:10 +0200 132)         if (validAccount) {
82375a91 (David A. Velasco 2013-07-02 12:41:10 +0200 133)             mAccount = account;
82375a91 (David A. Velasco 2013-07-02 12:41:10 +0200 134)             mAccountWasSet = true;
82375a91 (David A. Velasco 2013-07-02 12:41:10 +0200 135)             mAccountWasRestored = (savedAccount || mAccount.equals(oldAccount));
82375a91 (David A. Velasco 2013-07-02 12:41:10 +0200 136)             
82375a91 (David A. Velasco 2013-07-02 12:41:10 +0200 137)         } else {
82375a91 (David A. Velasco 2013-07-02 12:41:10 +0200 138)             swapToDefaultAccount();
82375a91 (David A. Velasco 2013-07-02 12:41:10 +0200 139)         }
82375a91 (David A. Velasco 2013-07-02 12:41:10 +0200 140)     }
82375a91 (David A. Velasco 2013-07-02 12:41:10 +0200 141) 
82375a91 (David A. Velasco 2013-07-02 12:41:10 +0200 142)     
82375a91 (David A. Velasco 2013-07-02 12:41:10 +0200 143)     /**
82375a91 (David A. Velasco 2013-07-02 12:41:10 +0200 144)      *  Tries to swap the current ownCloud {@link Account} for other valid and existing. 
82375a91 (David A. Velasco 2013-07-02 12:41:10 +0200 145)      * 
82375a91 (David A. Velasco 2013-07-02 12:41:10 +0200 146)      *  If no valid ownCloud {@link Account} exists, the the user is requested 
82375a91 (David A. Velasco 2013-07-02 12:41:10 +0200 147)      *  to create a new ownCloud {@link Account}.
82375a91 (David A. Velasco 2013-07-02 12:41:10 +0200 148)      *  
82375a91 (David A. Velasco 2013-07-02 12:41:10 +0200 149)      *  POSTCONDITION: updates {@link #mAccountWasSet} and {@link #mAccountWasRestored}.
82375a91 (David A. Velasco 2013-07-02 12:41:10 +0200 150)      *   
82375a91 (David A. Velasco 2013-07-02 12:41:10 +0200 151)      *  @return     'True' if the checked {@link Account} was valid.
82375a91 (David A. Velasco 2013-07-02 12:41:10 +0200 152)      */
82375a91 (David A. Velasco 2013-07-02 12:41:10 +0200 153)     private void swapToDefaultAccount() {
82375a91 (David A. Velasco 2013-07-02 12:41:10 +0200 154)         // default to the most recently used account
82375a91 (David A. Velasco 2013-07-02 12:41:10 +0200 155)         Account newAccount  = AccountUtils.getCurrentOwnCloudAccount(getApplicationContext());
82375a91 (David A. Velasco 2013-07-02 12:41:10 +0200 156)         if (newAccount == null) {
82375a91 (David A. Velasco 2013-07-02 12:41:10 +0200 157)             /// no account available: force account creation
82375a91 (David A. Velasco 2013-07-02 12:41:10 +0200 158)             createFirstAccount();
82375a91 (David A. Velasco 2013-07-02 12:41:10 +0200 159)             mRedirectingToSetupAccount = true;
82375a91 (David A. Velasco 2013-07-02 12:41:10 +0200 160)             mAccountWasSet = false;
82375a91 (David A. Velasco 2013-07-02 12:41:10 +0200 161)             mAccountWasRestored = false;
82375a91 (David A. Velasco 2013-07-02 12:41:10 +0200 162)             
82375a91 (David A. Velasco 2013-07-02 12:41:10 +0200 163)         } else {
82375a91 (David A. Velasco 2013-07-02 12:41:10 +0200 164)             mAccountWasSet = true;
82375a91 (David A. Velasco 2013-07-02 12:41:10 +0200 165)             mAccountWasRestored = (newAccount.equals(mAccount));
82375a91 (David A. Velasco 2013-07-02 12:41:10 +0200 166)             mAccount = newAccount;
82375a91 (David A. Velasco 2013-07-02 12:41:10 +0200 167)         }
82375a91 (David A. Velasco 2013-07-02 12:41:10 +0200 168)     }
82375a91 (David A. Velasco 2013-07-02 12:41:10 +0200 169) 
82375a91 (David A. Velasco 2013-07-02 12:41:10 +0200 170) 
82375a91 (David A. Velasco 2013-07-02 12:41:10 +0200 171)     /**
f6780fdc (David A. Velasco 2013-05-07 12:54:31 +0200 172)      * Launches the account creation activity. To use when no ownCloud account is available
f6780fdc (David A. Velasco 2013-05-07 12:54:31 +0200 173)      */
f6780fdc (David A. Velasco 2013-05-07 12:54:31 +0200 174)     private void createFirstAccount() {
f6780fdc (David A. Velasco 2013-05-07 12:54:31 +0200 175)         AccountManager am = AccountManager.get(getApplicationContext());
f6780fdc (David A. Velasco 2013-05-07 12:54:31 +0200 176)         am.addAccount(AccountAuthenticator.ACCOUNT_TYPE, 
5a65ff53 (David A. Velasco 2013-07-26 13:05:01 +0200 177)                         null,
f6780fdc (David A. Velasco 2013-05-07 12:54:31 +0200 178)                         null, 
f6780fdc (David A. Velasco 2013-05-07 12:54:31 +0200 179)                         null, 
f6780fdc (David A. Velasco 2013-05-07 12:54:31 +0200 180)                         this, 
f6780fdc (David A. Velasco 2013-05-07 12:54:31 +0200 181)                         new AccountCreationCallback(),                        
f6780fdc (David A. Velasco 2013-05-07 12:54:31 +0200 182)                         null);
f6780fdc (David A. Velasco 2013-05-07 12:54:31 +0200 183)     }
f6780fdc (David A. Velasco 2013-05-07 12:54:31 +0200 184) 
f6780fdc (David A. Velasco 2013-05-07 12:54:31 +0200 185)     
979bdc83 (David A. Velasco 2013-05-07 13:05:40 +0200 186)     /**
979bdc83 (David A. Velasco 2013-05-07 13:05:40 +0200 187)      * {@inheritDoc}
979bdc83 (David A. Velasco 2013-05-07 13:05:40 +0200 188)      */
f6780fdc (David A. Velasco 2013-05-07 12:54:31 +0200 189)     @Override
f6780fdc (David A. Velasco 2013-05-07 12:54:31 +0200 190)     protected void onSaveInstanceState(Bundle outState) {
f6780fdc (David A. Velasco 2013-05-07 12:54:31 +0200 191)         super.onSaveInstanceState(outState);
f6780fdc (David A. Velasco 2013-05-07 12:54:31 +0200 192)         outState.putParcelable(FileActivity.EXTRA_FILE, mFile);
f6780fdc (David A. Velasco 2013-05-07 12:54:31 +0200 193)         outState.putParcelable(FileActivity.EXTRA_ACCOUNT, mAccount);
f6780fdc (David A. Velasco 2013-05-07 12:54:31 +0200 194)     }
f6780fdc (David A. Velasco 2013-05-07 12:54:31 +0200 195)     
f6780fdc (David A. Velasco 2013-05-07 12:54:31 +0200 196)     
f6780fdc (David A. Velasco 2013-05-07 12:54:31 +0200 197)     /**
f6780fdc (David A. Velasco 2013-05-07 12:54:31 +0200 198)      * Getter for the main {@link OCFile} handled by the activity.
f6780fdc (David A. Velasco 2013-05-07 12:54:31 +0200 199)      * 
f6780fdc (David A. Velasco 2013-05-07 12:54:31 +0200 200)      * @return  Main {@link OCFile} handled by the activity.
f6780fdc (David A. Velasco 2013-05-07 12:54:31 +0200 201)      */
f6780fdc (David A. Velasco 2013-05-07 12:54:31 +0200 202)     public OCFile getFile() {
f6780fdc (David A. Velasco 2013-05-07 12:54:31 +0200 203)         return mFile;
f6780fdc (David A. Velasco 2013-05-07 12:54:31 +0200 204)     }
f6780fdc (David A. Velasco 2013-05-07 12:54:31 +0200 205) 
f6780fdc (David A. Velasco 2013-05-07 12:54:31 +0200 206)     
f6780fdc (David A. Velasco 2013-05-07 12:54:31 +0200 207)     /**
f6780fdc (David A. Velasco 2013-05-07 12:54:31 +0200 208)      * Setter for the main {@link OCFile} handled by the activity.
f6780fdc (David A. Velasco 2013-05-07 12:54:31 +0200 209)      * 
f6780fdc (David A. Velasco 2013-05-07 12:54:31 +0200 210)      * @param file  Main {@link OCFile} to be handled by the activity.
f6780fdc (David A. Velasco 2013-05-07 12:54:31 +0200 211)      */
f6780fdc (David A. Velasco 2013-05-07 12:54:31 +0200 212)     public void setFile(OCFile file) {
f6780fdc (David A. Velasco 2013-05-07 12:54:31 +0200 213)         mFile = file;
f6780fdc (David A. Velasco 2013-05-07 12:54:31 +0200 214)     }
f6780fdc (David A. Velasco 2013-05-07 12:54:31 +0200 215) 
f6780fdc (David A. Velasco 2013-05-07 12:54:31 +0200 216)     
f6780fdc (David A. Velasco 2013-05-07 12:54:31 +0200 217)     /**
f6780fdc (David A. Velasco 2013-05-07 12:54:31 +0200 218)      * Getter for the ownCloud {@link Account} where the main {@link OCFile} handled by the activity is located.
f6780fdc (David A. Velasco 2013-05-07 12:54:31 +0200 219)      * 
f6780fdc (David A. Velasco 2013-05-07 12:54:31 +0200 220)      * @return  OwnCloud {@link Account} where the main {@link OCFile} handled by the activity is located.
f6780fdc (David A. Velasco 2013-05-07 12:54:31 +0200 221)      */
f6780fdc (David A. Velasco 2013-05-07 12:54:31 +0200 222)     public Account getAccount() {
f6780fdc (David A. Velasco 2013-05-07 12:54:31 +0200 223)         return mAccount;
f6780fdc (David A. Velasco 2013-05-07 12:54:31 +0200 224)     }
f6780fdc (David A. Velasco 2013-05-07 12:54:31 +0200 225) 
f6780fdc (David A. Velasco 2013-05-07 12:54:31 +0200 226)     
f6780fdc (David A. Velasco 2013-05-07 12:54:31 +0200 227)     /**
f6780fdc (David A. Velasco 2013-05-07 12:54:31 +0200 228)      * @return  'True' when the Activity is finishing to enforce the setup of a new account.
f6780fdc (David A. Velasco 2013-05-07 12:54:31 +0200 229)      */
f6780fdc (David A. Velasco 2013-05-07 12:54:31 +0200 230)     protected boolean isRedirectingToSetupAccount() {
f6780fdc (David A. Velasco 2013-05-07 12:54:31 +0200 231)         return mRedirectingToSetupAccount;
f6780fdc (David A. Velasco 2013-05-07 12:54:31 +0200 232)     }
f6780fdc (David A. Velasco 2013-05-07 12:54:31 +0200 233)     
f6780fdc (David A. Velasco 2013-05-07 12:54:31 +0200 234)     
f6780fdc (David A. Velasco 2013-05-07 12:54:31 +0200 235)     /**
f6780fdc (David A. Velasco 2013-05-07 12:54:31 +0200 236)      * Helper class handling a callback from the {@link AccountManager} after the creation of
f6780fdc (David A. Velasco 2013-05-07 12:54:31 +0200 237)      * a new ownCloud {@link Account} finished, successfully or not.
f6780fdc (David A. Velasco 2013-05-07 12:54:31 +0200 238)      * 
f6780fdc (David A. Velasco 2013-05-07 12:54:31 +0200 239)      * At this moment, only called after the creation of the first account.
f6780fdc (David A. Velasco 2013-05-07 12:54:31 +0200 240)      * 
f6780fdc (David A. Velasco 2013-05-07 12:54:31 +0200 241)      * @author David A. Velasco
f6780fdc (David A. Velasco 2013-05-07 12:54:31 +0200 242)      */
f6780fdc (David A. Velasco 2013-05-07 12:54:31 +0200 243)     public class AccountCreationCallback implements AccountManagerCallback<Bundle> {
f6780fdc (David A. Velasco 2013-05-07 12:54:31 +0200 244) 
f6780fdc (David A. Velasco 2013-05-07 12:54:31 +0200 245)         @Override
f6780fdc (David A. Velasco 2013-05-07 12:54:31 +0200 246)         public void run(AccountManagerFuture<Bundle> future) {
f6780fdc (David A. Velasco 2013-05-07 12:54:31 +0200 247)             FileActivity.this.mRedirectingToSetupAccount = false;
82375a91 (David A. Velasco 2013-07-02 12:41:10 +0200 248)             boolean accountWasSet = false;
f6780fdc (David A. Velasco 2013-05-07 12:54:31 +0200 249)             if (future != null) {
f6780fdc (David A. Velasco 2013-05-07 12:54:31 +0200 250)                 try {
f6780fdc (David A. Velasco 2013-05-07 12:54:31 +0200 251)                     Bundle result;
f6780fdc (David A. Velasco 2013-05-07 12:54:31 +0200 252)                     result = future.getResult();
f6780fdc (David A. Velasco 2013-05-07 12:54:31 +0200 253)                     String name = result.getString(AccountManager.KEY_ACCOUNT_NAME);
f6780fdc (David A. Velasco 2013-05-07 12:54:31 +0200 254)                     String type = result.getString(AccountManager.KEY_ACCOUNT_TYPE);
f6780fdc (David A. Velasco 2013-05-07 12:54:31 +0200 255)                     if (AccountUtils.setCurrentOwnCloudAccount(getApplicationContext(), name)) {
82375a91 (David A. Velasco 2013-07-02 12:41:10 +0200 256)                         setAccount(new Account(name, type), false);
82375a91 (David A. Velasco 2013-07-02 12:41:10 +0200 257)                         accountWasSet = true;
f6780fdc (David A. Velasco 2013-05-07 12:54:31 +0200 258)                     }
f6780fdc (David A. Velasco 2013-05-07 12:54:31 +0200 259)                 } catch (OperationCanceledException e) {
f9babe7b (David A. Velasco 2013-06-14 12:53:45 +0200 260)                     Log_OC.d(TAG, "Account creation canceled");
f6780fdc (David A. Velasco 2013-05-07 12:54:31 +0200 261)                     
f6780fdc (David A. Velasco 2013-05-07 12:54:31 +0200 262)                 } catch (Exception e) {
f6780fdc (David A. Velasco 2013-05-07 12:54:31 +0200 263)                     Log_OC.e(TAG, "Account creation finished in exception: ", e);
f6780fdc (David A. Velasco 2013-05-07 12:54:31 +0200 264)                 }
f6780fdc (David A. Velasco 2013-05-07 12:54:31 +0200 265)                     
f6780fdc (David A. Velasco 2013-05-07 12:54:31 +0200 266)             } else {
f6780fdc (David A. Velasco 2013-05-07 12:54:31 +0200 267)                 Log_OC.e(TAG, "Account creation callback with null bundle");
f6780fdc (David A. Velasco 2013-05-07 12:54:31 +0200 268)             }
82375a91 (David A. Velasco 2013-07-02 12:41:10 +0200 269)             if (!accountWasSet) {
f9babe7b (David A. Velasco 2013-06-14 12:53:45 +0200 270)                 moveTaskToBack(true);
f6780fdc (David A. Velasco 2013-05-07 12:54:31 +0200 271)             }
f6780fdc (David A. Velasco 2013-05-07 12:54:31 +0200 272)         }
f6780fdc (David A. Velasco 2013-05-07 12:54:31 +0200 273)         
f6780fdc (David A. Velasco 2013-05-07 12:54:31 +0200 274)     }
edb380e0 (David A. Velasco 2013-05-27 15:01:54 +0200 275)     
edb380e0 (David A. Velasco 2013-05-27 15:01:54 +0200 276)     
f6780fdc (David A. Velasco 2013-05-07 12:54:31 +0200 277)     /**
f6780fdc (David A. Velasco 2013-05-07 12:54:31 +0200 278)      *  Called when the ownCloud {@link Account} associated to the Activity was just updated.
f6780fdc (David A. Velasco 2013-05-07 12:54:31 +0200 279)      * 
f6780fdc (David A. Velasco 2013-05-07 12:54:31 +0200 280)      *  Child classes must grant that state depending on the {@link Account} is updated.
f6780fdc (David A. Velasco 2013-05-07 12:54:31 +0200 281)      */
c70f3333 (David A. Velasco 2013-06-04 14:30:29 +0200 282)     protected abstract void onAccountSet(boolean stateWasRecovered);
edb380e0 (David A. Velasco 2013-05-27 15:01:54 +0200 283)     
edb380e0 (David A. Velasco 2013-05-27 15:01:54 +0200 284)     
fd396289 (David A. Velasco 2013-05-30 17:53:21 +0200 285) 
fd396289 (David A. Velasco 2013-05-30 17:53:21 +0200 286)     public void openFile(OCFile file) {
fd396289 (David A. Velasco 2013-05-30 17:53:21 +0200 287)         if (file != null) {
fd396289 (David A. Velasco 2013-05-30 17:53:21 +0200 288)             String storagePath = file.getStoragePath();
fd396289 (David A. Velasco 2013-05-30 17:53:21 +0200 289)             String encodedStoragePath = WebdavUtils.encodePath(storagePath);
fd396289 (David A. Velasco 2013-05-30 17:53:21 +0200 290)             
fd396289 (David A. Velasco 2013-05-30 17:53:21 +0200 291)             Intent intentForSavedMimeType = new Intent(Intent.ACTION_VIEW);
fd396289 (David A. Velasco 2013-05-30 17:53:21 +0200 292)             intentForSavedMimeType.setDataAndType(Uri.parse("file://"+ encodedStoragePath), file.getMimetype());
fd396289 (David A. Velasco 2013-05-30 17:53:21 +0200 293)             intentForSavedMimeType.setFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION | Intent.FLAG_GRANT_WRITE_URI_PERMISSION);
fd396289 (David A. Velasco 2013-05-30 17:53:21 +0200 294)             
fd396289 (David A. Velasco 2013-05-30 17:53:21 +0200 295)             Intent intentForGuessedMimeType = null;
fd396289 (David A. Velasco 2013-05-30 17:53:21 +0200 296)             if (storagePath.lastIndexOf('.') >= 0) {
fd396289 (David A. Velasco 2013-05-30 17:53:21 +0200 297)                 String guessedMimeType = MimeTypeMap.getSingleton().getMimeTypeFromExtension(storagePath.substring(storagePath.lastIndexOf('.') + 1));
fd396289 (David A. Velasco 2013-05-30 17:53:21 +0200 298)                 if (guessedMimeType != null && !guessedMimeType.equals(file.getMimetype())) {
fd396289 (David A. Velasco 2013-05-30 17:53:21 +0200 299)                     intentForGuessedMimeType = new Intent(Intent.ACTION_VIEW);
fd396289 (David A. Velasco 2013-05-30 17:53:21 +0200 300)                     intentForGuessedMimeType.setDataAndType(Uri.parse("file://"+ encodedStoragePath), guessedMimeType);
fd396289 (David A. Velasco 2013-05-30 17:53:21 +0200 301)                     intentForGuessedMimeType.setFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION | Intent.FLAG_GRANT_WRITE_URI_PERMISSION);
99d58002 (David A. Velasco 2013-05-28 10:29:03 +0200 302)                 }
fd396289 (David A. Velasco 2013-05-30 17:53:21 +0200 303)             }
fd396289 (David A. Velasco 2013-05-30 17:53:21 +0200 304)             
fd396289 (David A. Velasco 2013-05-30 17:53:21 +0200 305)             Intent chooserIntent = null;
fd396289 (David A. Velasco 2013-05-30 17:53:21 +0200 306)             if (intentForGuessedMimeType != null) {
fd396289 (David A. Velasco 2013-05-30 17:53:21 +0200 307)                 chooserIntent = Intent.createChooser(intentForGuessedMimeType, getString(R.string.actionbar_open_with));
edb380e0 (David A. Velasco 2013-05-27 15:01:54 +0200 308)             } else {
fd396289 (David A. Velasco 2013-05-30 17:53:21 +0200 309)                 chooserIntent = Intent.createChooser(intentForSavedMimeType, getString(R.string.actionbar_open_with));
edb380e0 (David A. Velasco 2013-05-27 15:01:54 +0200 310)             }
fd396289 (David A. Velasco 2013-05-30 17:53:21 +0200 311)             
fd396289 (David A. Velasco 2013-05-30 17:53:21 +0200 312)             startActivity(chooserIntent);
fd396289 (David A. Velasco 2013-05-30 17:53:21 +0200 313)             
fd396289 (David A. Velasco 2013-05-30 17:53:21 +0200 314)         } else {
fd396289 (David A. Velasco 2013-05-30 17:53:21 +0200 315)             Log_OC.wtf(TAG, "Trying to open a NULL OCFile");
edb380e0 (David A. Velasco 2013-05-27 15:01:54 +0200 316)         }
edb380e0 (David A. Velasco 2013-05-27 15:01:54 +0200 317)     }
fd396289 (David A. Velasco 2013-05-30 17:53:21 +0200 318)     
f6780fdc (David A. Velasco 2013-05-07 12:54:31 +0200 319) }
