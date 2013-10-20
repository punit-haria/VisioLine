9dacdbec (masensio 2013-08-13 12:08:46 +0200  1) /*
9dacdbec (masensio 2013-08-13 12:08:46 +0200  2)  * Copyright (C) 2009 The Android Open Source Project
9dacdbec (masensio 2013-08-13 12:08:46 +0200  3)  *
9dacdbec (masensio 2013-08-13 12:08:46 +0200  4)  * Licensed under the Apache License, Version 2.0 (the "License");
9dacdbec (masensio 2013-08-13 12:08:46 +0200  5)  * you may not use this file except in compliance with the License.
9dacdbec (masensio 2013-08-13 12:08:46 +0200  6)  * You may obtain a copy of the License at
9dacdbec (masensio 2013-08-13 12:08:46 +0200  7)  *
9dacdbec (masensio 2013-08-13 12:08:46 +0200  8)  *      http://www.apache.org/licenses/LICENSE-2.0
9dacdbec (masensio 2013-08-13 12:08:46 +0200  9)  *
9dacdbec (masensio 2013-08-13 12:08:46 +0200 10)  * Unless required by applicable law or agreed to in writing, software
9dacdbec (masensio 2013-08-13 12:08:46 +0200 11)  * distributed under the License is distributed on an "AS IS" BASIS,
9dacdbec (masensio 2013-08-13 12:08:46 +0200 12)  * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
9dacdbec (masensio 2013-08-13 12:08:46 +0200 13)  * See the License for the specific language governing permissions and
9dacdbec (masensio 2013-08-13 12:08:46 +0200 14)  * limitations under the License.
9dacdbec (masensio 2013-08-13 12:08:46 +0200 15)  */
9dacdbec (masensio 2013-08-13 12:08:46 +0200 16) 
9dacdbec (masensio 2013-08-13 12:08:46 +0200 17) package com.owncloud.android.authentication;
9dacdbec (masensio 2013-08-13 12:08:46 +0200 18) 
9dacdbec (masensio 2013-08-13 12:08:46 +0200 19) import android.accounts.AccountAuthenticatorResponse;
9dacdbec (masensio 2013-08-13 12:08:46 +0200 20) import android.accounts.AccountManager;
9dacdbec (masensio 2013-08-13 12:08:46 +0200 21) import android.os.Bundle;
9dacdbec (masensio 2013-08-13 12:08:46 +0200 22) 
9dacdbec (masensio 2013-08-13 12:08:46 +0200 23) import com.actionbarsherlock.app.SherlockFragmentActivity;
9dacdbec (masensio 2013-08-13 12:08:46 +0200 24) 
9dacdbec (masensio 2013-08-13 12:08:46 +0200 25) 
9dacdbec (masensio 2013-08-13 12:08:46 +0200 26) /*
9dacdbec (masensio 2013-08-13 12:08:46 +0200 27)  * Base class for implementing an Activity that is used to help implement an AbstractAccountAuthenticator. 
9dacdbec (masensio 2013-08-13 12:08:46 +0200 28)  * If the AbstractAccountAuthenticator needs to use an activity to handle the request then it can have the activity extend 
9dacdbec (masensio 2013-08-13 12:08:46 +0200 29)  * AccountAuthenticatorActivity. The AbstractAccountAuthenticator passes in the response to the intent using the following:
9dacdbec (masensio 2013-08-13 12:08:46 +0200 30)  * intent.putExtra(AccountManager.KEY_ACCOUNT_AUTHENTICATOR_RESPONSE, response);
9dacdbec (masensio 2013-08-13 12:08:46 +0200 31)  * 
9dacdbec (masensio 2013-08-13 12:08:46 +0200 32)  * The activity then sets the result that is to be handed to the response via setAccountAuthenticatorResult(android.os.Bundle). 
9dacdbec (masensio 2013-08-13 12:08:46 +0200 33)  * This result will be sent as the result of the request when the activity finishes. If this is never set or if it is set to null 
9dacdbec (masensio 2013-08-13 12:08:46 +0200 34)  * then error AccountManager.ERROR_CODE_CANCELED will be called on the response.
9dacdbec (masensio 2013-08-13 12:08:46 +0200 35)  */
9dacdbec (masensio 2013-08-13 12:08:46 +0200 36) 
9dacdbec (masensio 2013-08-13 12:08:46 +0200 37) public class AccountAuthenticatorActivity extends SherlockFragmentActivity {
9dacdbec (masensio 2013-08-13 12:08:46 +0200 38) 
9dacdbec (masensio 2013-08-13 12:08:46 +0200 39)     private AccountAuthenticatorResponse mAccountAuthenticatorResponse = null;
9dacdbec (masensio 2013-08-13 12:08:46 +0200 40)     private Bundle mResultBundle = null;
9dacdbec (masensio 2013-08-13 12:08:46 +0200 41) 
9dacdbec (masensio 2013-08-13 12:08:46 +0200 42) 
9dacdbec (masensio 2013-08-13 12:08:46 +0200 43)     /**
9dacdbec (masensio 2013-08-13 12:08:46 +0200 44)      * Set the result that is to be sent as the result of the request that caused this Activity to be launched.
9dacdbec (masensio 2013-08-13 12:08:46 +0200 45)      * If result is null or this method is never called then the request will be canceled.
9dacdbec (masensio 2013-08-13 12:08:46 +0200 46)      * 
9dacdbec (masensio 2013-08-13 12:08:46 +0200 47)      * @param result this is returned as the result of the AbstractAccountAuthenticator request
9dacdbec (masensio 2013-08-13 12:08:46 +0200 48)      */
9dacdbec (masensio 2013-08-13 12:08:46 +0200 49)     public final void setAccountAuthenticatorResult(Bundle result) {
9dacdbec (masensio 2013-08-13 12:08:46 +0200 50)         mResultBundle = result;
9dacdbec (masensio 2013-08-13 12:08:46 +0200 51)     }
9dacdbec (masensio 2013-08-13 12:08:46 +0200 52) 
9dacdbec (masensio 2013-08-13 12:08:46 +0200 53)     /**
9dacdbec (masensio 2013-08-13 12:08:46 +0200 54)      * Retreives the AccountAuthenticatorResponse from either the intent of the icicle, if the
9dacdbec (masensio 2013-08-13 12:08:46 +0200 55)      * icicle is non-zero.
9dacdbec (masensio 2013-08-13 12:08:46 +0200 56)      * @param icicle the save instance data of this Activity, may be null
9dacdbec (masensio 2013-08-13 12:08:46 +0200 57)      */
9dacdbec (masensio 2013-08-13 12:08:46 +0200 58)     protected void onCreate(Bundle icicle) {
9dacdbec (masensio 2013-08-13 12:08:46 +0200 59)         super.onCreate(icicle);
9dacdbec (masensio 2013-08-13 12:08:46 +0200 60) 
9dacdbec (masensio 2013-08-13 12:08:46 +0200 61)         mAccountAuthenticatorResponse =
9dacdbec (masensio 2013-08-13 12:08:46 +0200 62)                 getIntent().getParcelableExtra(AccountManager.KEY_ACCOUNT_AUTHENTICATOR_RESPONSE);
9dacdbec (masensio 2013-08-13 12:08:46 +0200 63) 
9dacdbec (masensio 2013-08-13 12:08:46 +0200 64)         if (mAccountAuthenticatorResponse != null) {
9dacdbec (masensio 2013-08-13 12:08:46 +0200 65)             mAccountAuthenticatorResponse.onRequestContinued();
9dacdbec (masensio 2013-08-13 12:08:46 +0200 66)         }
9dacdbec (masensio 2013-08-13 12:08:46 +0200 67)     }
9dacdbec (masensio 2013-08-13 12:08:46 +0200 68)     
9dacdbec (masensio 2013-08-13 12:08:46 +0200 69)     /**
9dacdbec (masensio 2013-08-13 12:08:46 +0200 70)      * Sends the result or a Constants.ERROR_CODE_CANCELED error if a result isn't present.
9dacdbec (masensio 2013-08-13 12:08:46 +0200 71)      */
9dacdbec (masensio 2013-08-13 12:08:46 +0200 72)     public void finish() {
9dacdbec (masensio 2013-08-13 12:08:46 +0200 73)         if (mAccountAuthenticatorResponse != null) {
9dacdbec (masensio 2013-08-13 12:08:46 +0200 74)             // send the result bundle back if set, otherwise send an error.
9dacdbec (masensio 2013-08-13 12:08:46 +0200 75)             if (mResultBundle != null) {
9dacdbec (masensio 2013-08-13 12:08:46 +0200 76)                 mAccountAuthenticatorResponse.onResult(mResultBundle);
9dacdbec (masensio 2013-08-13 12:08:46 +0200 77)             } else {
9dacdbec (masensio 2013-08-13 12:08:46 +0200 78)                 mAccountAuthenticatorResponse.onError(AccountManager.ERROR_CODE_CANCELED,
9dacdbec (masensio 2013-08-13 12:08:46 +0200 79)                         "canceled");
9dacdbec (masensio 2013-08-13 12:08:46 +0200 80)             }
9dacdbec (masensio 2013-08-13 12:08:46 +0200 81)             mAccountAuthenticatorResponse = null;
9dacdbec (masensio 2013-08-13 12:08:46 +0200 82)         }
9dacdbec (masensio 2013-08-13 12:08:46 +0200 83)         super.finish();
9dacdbec (masensio 2013-08-13 12:08:46 +0200 84)     }
9dacdbec (masensio 2013-08-13 12:08:46 +0200 85) }
