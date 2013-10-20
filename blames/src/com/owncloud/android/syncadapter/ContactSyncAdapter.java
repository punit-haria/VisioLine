92080afe src/com/owncloud/android/syncadapter/ContactSyncAdapter.java (Bartek Przybylski 2012-10-28 00:12:15 +0200   1) /* ownCloud Android client application
92080afe src/com/owncloud/android/syncadapter/ContactSyncAdapter.java (Bartek Przybylski 2012-10-28 00:12:15 +0200   2)  *   Copyright (C) 2012 Bartek Przybylski
bb257ec7 src/com/owncloud/android/syncadapter/ContactSyncAdapter.java (David A. Velasco  2013-02-07 18:45:10 +0100   3)  *   Copyright (C) 2012-2013 ownCloud Inc.
92080afe src/com/owncloud/android/syncadapter/ContactSyncAdapter.java (Bartek Przybylski 2012-10-28 00:12:15 +0200   4)  *
92080afe src/com/owncloud/android/syncadapter/ContactSyncAdapter.java (Bartek Przybylski 2012-10-28 00:12:15 +0200   5)  *   This program is free software: you can redistribute it and/or modify
bdc0332c src/com/owncloud/android/syncadapter/ContactSyncAdapter.java (David A. Velasco  2013-04-17 12:26:13 +0200   6)  *   it under the terms of the GNU General Public License version 2,
bdc0332c src/com/owncloud/android/syncadapter/ContactSyncAdapter.java (David A. Velasco  2013-04-17 12:26:13 +0200   7)  *   as published by the Free Software Foundation.
92080afe src/com/owncloud/android/syncadapter/ContactSyncAdapter.java (Bartek Przybylski 2012-10-28 00:12:15 +0200   8)  *
92080afe src/com/owncloud/android/syncadapter/ContactSyncAdapter.java (Bartek Przybylski 2012-10-28 00:12:15 +0200   9)  *   This program is distributed in the hope that it will be useful,
92080afe src/com/owncloud/android/syncadapter/ContactSyncAdapter.java (Bartek Przybylski 2012-10-28 00:12:15 +0200  10)  *   but WITHOUT ANY WARRANTY; without even the implied warranty of
92080afe src/com/owncloud/android/syncadapter/ContactSyncAdapter.java (Bartek Przybylski 2012-10-28 00:12:15 +0200  11)  *   MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
92080afe src/com/owncloud/android/syncadapter/ContactSyncAdapter.java (Bartek Przybylski 2012-10-28 00:12:15 +0200  12)  *   GNU General Public License for more details.
92080afe src/com/owncloud/android/syncadapter/ContactSyncAdapter.java (Bartek Przybylski 2012-10-28 00:12:15 +0200  13)  *
92080afe src/com/owncloud/android/syncadapter/ContactSyncAdapter.java (Bartek Przybylski 2012-10-28 00:12:15 +0200  14)  *   You should have received a copy of the GNU General Public License
92080afe src/com/owncloud/android/syncadapter/ContactSyncAdapter.java (Bartek Przybylski 2012-10-28 00:12:15 +0200  15)  *   along with this program.  If not, see <http://www.gnu.org/licenses/>.
92080afe src/com/owncloud/android/syncadapter/ContactSyncAdapter.java (Bartek Przybylski 2012-10-28 00:12:15 +0200  16)  *
92080afe src/com/owncloud/android/syncadapter/ContactSyncAdapter.java (Bartek Przybylski 2012-10-28 00:12:15 +0200  17)  */
92080afe src/com/owncloud/android/syncadapter/ContactSyncAdapter.java (Bartek Przybylski 2012-10-28 00:12:15 +0200  18) 
a4ba6170 src/com/owncloud/android/syncadapter/ContactSyncAdapter.java (Bartek Przybylski 2012-07-31 17:43:37 +0200  19) package com.owncloud.android.syncadapter;
cf2c2eed src/eu/alefzero/owncloud/syncadapter/ContactSyncAdapter.java (Bartek Przybylski 2012-04-08 18:41:23 +0200  20) 
cf2c2eed src/eu/alefzero/owncloud/syncadapter/ContactSyncAdapter.java (Bartek Przybylski 2012-04-08 18:41:23 +0200  21) import java.io.FileInputStream;
cf2c2eed src/eu/alefzero/owncloud/syncadapter/ContactSyncAdapter.java (Bartek Przybylski 2012-04-08 18:41:23 +0200  22) import java.io.IOException;
cf2c2eed src/eu/alefzero/owncloud/syncadapter/ContactSyncAdapter.java (Bartek Przybylski 2012-04-08 18:41:23 +0200  23) 
cf2c2eed src/eu/alefzero/owncloud/syncadapter/ContactSyncAdapter.java (Bartek Przybylski 2012-04-08 18:41:23 +0200  24) import org.apache.http.client.methods.HttpPut;
cf2c2eed src/eu/alefzero/owncloud/syncadapter/ContactSyncAdapter.java (Bartek Przybylski 2012-04-08 18:41:23 +0200  25) import org.apache.http.entity.ByteArrayEntity;
cf2c2eed src/eu/alefzero/owncloud/syncadapter/ContactSyncAdapter.java (Bartek Przybylski 2012-04-08 18:41:23 +0200  26) 
69d6d821 src/com/owncloud/android/syncadapter/ContactSyncAdapter.java (David A. Velasco  2013-03-21 12:21:48 +0100  27) import com.owncloud.android.authentication.AccountAuthenticator;
c4ac05de src/com/owncloud/android/syncadapter/ContactSyncAdapter.java (David A. Velasco  2013-06-18 11:34:08 +0200  28) import com.owncloud.android.authentication.AccountUtils;
a4ba6170 src/com/owncloud/android/syncadapter/ContactSyncAdapter.java (Bartek Przybylski 2012-07-31 17:43:37 +0200  29) 
cf2c2eed src/eu/alefzero/owncloud/syncadapter/ContactSyncAdapter.java (Bartek Przybylski 2012-04-08 18:41:23 +0200  30) import android.accounts.Account;
cf2c2eed src/eu/alefzero/owncloud/syncadapter/ContactSyncAdapter.java (Bartek Przybylski 2012-04-08 18:41:23 +0200  31) import android.accounts.AccountManager;
cf2c2eed src/eu/alefzero/owncloud/syncadapter/ContactSyncAdapter.java (Bartek Przybylski 2012-04-08 18:41:23 +0200  32) import android.accounts.AuthenticatorException;
cf2c2eed src/eu/alefzero/owncloud/syncadapter/ContactSyncAdapter.java (Bartek Przybylski 2012-04-08 18:41:23 +0200  33) import android.accounts.OperationCanceledException;
cf2c2eed src/eu/alefzero/owncloud/syncadapter/ContactSyncAdapter.java (Bartek Przybylski 2012-04-08 18:41:23 +0200  34) import android.content.ContentProviderClient;
cf2c2eed src/eu/alefzero/owncloud/syncadapter/ContactSyncAdapter.java (Bartek Przybylski 2012-04-08 18:41:23 +0200  35) import android.content.Context;
cf2c2eed src/eu/alefzero/owncloud/syncadapter/ContactSyncAdapter.java (Bartek Przybylski 2012-04-08 18:41:23 +0200  36) import android.content.SyncResult;
cf2c2eed src/eu/alefzero/owncloud/syncadapter/ContactSyncAdapter.java (Bartek Przybylski 2012-04-08 18:41:23 +0200  37) import android.content.res.AssetFileDescriptor;
cf2c2eed src/eu/alefzero/owncloud/syncadapter/ContactSyncAdapter.java (Bartek Przybylski 2012-04-08 18:41:23 +0200  38) import android.database.Cursor;
cf2c2eed src/eu/alefzero/owncloud/syncadapter/ContactSyncAdapter.java (Bartek Przybylski 2012-04-08 18:41:23 +0200  39) import android.net.Uri;
cf2c2eed src/eu/alefzero/owncloud/syncadapter/ContactSyncAdapter.java (Bartek Przybylski 2012-04-08 18:41:23 +0200  40) import android.os.Bundle;
cf2c2eed src/eu/alefzero/owncloud/syncadapter/ContactSyncAdapter.java (Bartek Przybylski 2012-04-08 18:41:23 +0200  41) import android.provider.ContactsContract;
cf2c2eed src/eu/alefzero/owncloud/syncadapter/ContactSyncAdapter.java (Bartek Przybylski 2012-04-08 18:41:23 +0200  42) 
cf2c2eed src/eu/alefzero/owncloud/syncadapter/ContactSyncAdapter.java (Bartek Przybylski 2012-04-08 18:41:23 +0200  43) public class ContactSyncAdapter extends AbstractOwnCloudSyncAdapter {
435b31ba src/eu/alefzero/owncloud/syncadapter/ContactSyncAdapter.java (Lennart Rosam     2012-05-16 09:48:34 +0200  44)     private String mAddrBookUri;
cf2c2eed src/eu/alefzero/owncloud/syncadapter/ContactSyncAdapter.java (Bartek Przybylski 2012-04-08 18:41:23 +0200  45) 
435b31ba src/eu/alefzero/owncloud/syncadapter/ContactSyncAdapter.java (Lennart Rosam     2012-05-16 09:48:34 +0200  46)     public ContactSyncAdapter(Context context, boolean autoInitialize) {
435b31ba src/eu/alefzero/owncloud/syncadapter/ContactSyncAdapter.java (Lennart Rosam     2012-05-16 09:48:34 +0200  47)         super(context, autoInitialize);
435b31ba src/eu/alefzero/owncloud/syncadapter/ContactSyncAdapter.java (Lennart Rosam     2012-05-16 09:48:34 +0200  48)         mAddrBookUri = null;
cf2c2eed src/eu/alefzero/owncloud/syncadapter/ContactSyncAdapter.java (Bartek Przybylski 2012-04-08 18:41:23 +0200  49)     }
cf2c2eed src/eu/alefzero/owncloud/syncadapter/ContactSyncAdapter.java (Bartek Przybylski 2012-04-08 18:41:23 +0200  50) 
435b31ba src/eu/alefzero/owncloud/syncadapter/ContactSyncAdapter.java (Lennart Rosam     2012-05-16 09:48:34 +0200  51)     @Override
435b31ba src/eu/alefzero/owncloud/syncadapter/ContactSyncAdapter.java (Lennart Rosam     2012-05-16 09:48:34 +0200  52)     public void onPerformSync(Account account, Bundle extras, String authority,
435b31ba src/eu/alefzero/owncloud/syncadapter/ContactSyncAdapter.java (Lennart Rosam     2012-05-16 09:48:34 +0200  53)             ContentProviderClient provider, SyncResult syncResult) {
435b31ba src/eu/alefzero/owncloud/syncadapter/ContactSyncAdapter.java (Lennart Rosam     2012-05-16 09:48:34 +0200  54)         setAccount(account);
435b31ba src/eu/alefzero/owncloud/syncadapter/ContactSyncAdapter.java (Lennart Rosam     2012-05-16 09:48:34 +0200  55)         setContentProvider(provider);
435b31ba src/eu/alefzero/owncloud/syncadapter/ContactSyncAdapter.java (Lennart Rosam     2012-05-16 09:48:34 +0200  56)         Cursor c = getLocalContacts(false);
435b31ba src/eu/alefzero/owncloud/syncadapter/ContactSyncAdapter.java (Lennart Rosam     2012-05-16 09:48:34 +0200  57)         if (c.moveToFirst()) {
435b31ba src/eu/alefzero/owncloud/syncadapter/ContactSyncAdapter.java (Lennart Rosam     2012-05-16 09:48:34 +0200  58)             do {
435b31ba src/eu/alefzero/owncloud/syncadapter/ContactSyncAdapter.java (Lennart Rosam     2012-05-16 09:48:34 +0200  59)                 String lookup = c.getString(c
435b31ba src/eu/alefzero/owncloud/syncadapter/ContactSyncAdapter.java (Lennart Rosam     2012-05-16 09:48:34 +0200  60)                         .getColumnIndex(ContactsContract.Contacts.LOOKUP_KEY));
435b31ba src/eu/alefzero/owncloud/syncadapter/ContactSyncAdapter.java (Lennart Rosam     2012-05-16 09:48:34 +0200  61)                 String a = getAddressBookUri();
435b31ba src/eu/alefzero/owncloud/syncadapter/ContactSyncAdapter.java (Lennart Rosam     2012-05-16 09:48:34 +0200  62)                 String uri = a + lookup + ".vcf";
435b31ba src/eu/alefzero/owncloud/syncadapter/ContactSyncAdapter.java (Lennart Rosam     2012-05-16 09:48:34 +0200  63)                 FileInputStream f;
435b31ba src/eu/alefzero/owncloud/syncadapter/ContactSyncAdapter.java (Lennart Rosam     2012-05-16 09:48:34 +0200  64)                 try {
435b31ba src/eu/alefzero/owncloud/syncadapter/ContactSyncAdapter.java (Lennart Rosam     2012-05-16 09:48:34 +0200  65)                     f = getContactVcard(lookup);
435b31ba src/eu/alefzero/owncloud/syncadapter/ContactSyncAdapter.java (Lennart Rosam     2012-05-16 09:48:34 +0200  66)                     HttpPut query = new HttpPut(uri);
435b31ba src/eu/alefzero/owncloud/syncadapter/ContactSyncAdapter.java (Lennart Rosam     2012-05-16 09:48:34 +0200  67)                     byte[] b = new byte[f.available()];
435b31ba src/eu/alefzero/owncloud/syncadapter/ContactSyncAdapter.java (Lennart Rosam     2012-05-16 09:48:34 +0200  68)                     f.read(b);
435b31ba src/eu/alefzero/owncloud/syncadapter/ContactSyncAdapter.java (Lennart Rosam     2012-05-16 09:48:34 +0200  69)                     query.setEntity(new ByteArrayEntity(b));
aa14479e src/com/owncloud/android/syncadapter/ContactSyncAdapter.java (Bartek Przybylski 2012-09-29 23:17:39 +0200  70)                     fireRawRequest(query);
435b31ba src/eu/alefzero/owncloud/syncadapter/ContactSyncAdapter.java (Lennart Rosam     2012-05-16 09:48:34 +0200  71)                 } catch (IOException e) {
435b31ba src/eu/alefzero/owncloud/syncadapter/ContactSyncAdapter.java (Lennart Rosam     2012-05-16 09:48:34 +0200  72)                     e.printStackTrace();
435b31ba src/eu/alefzero/owncloud/syncadapter/ContactSyncAdapter.java (Lennart Rosam     2012-05-16 09:48:34 +0200  73)                     return;
435b31ba src/eu/alefzero/owncloud/syncadapter/ContactSyncAdapter.java (Lennart Rosam     2012-05-16 09:48:34 +0200  74)                 } catch (OperationCanceledException e) {
435b31ba src/eu/alefzero/owncloud/syncadapter/ContactSyncAdapter.java (Lennart Rosam     2012-05-16 09:48:34 +0200  75)                     // TODO Auto-generated catch block
435b31ba src/eu/alefzero/owncloud/syncadapter/ContactSyncAdapter.java (Lennart Rosam     2012-05-16 09:48:34 +0200  76)                     e.printStackTrace();
435b31ba src/eu/alefzero/owncloud/syncadapter/ContactSyncAdapter.java (Lennart Rosam     2012-05-16 09:48:34 +0200  77)                 } catch (AuthenticatorException e) {
435b31ba src/eu/alefzero/owncloud/syncadapter/ContactSyncAdapter.java (Lennart Rosam     2012-05-16 09:48:34 +0200  78)                     // TODO Auto-generated catch block
435b31ba src/eu/alefzero/owncloud/syncadapter/ContactSyncAdapter.java (Lennart Rosam     2012-05-16 09:48:34 +0200  79)                     e.printStackTrace();
435b31ba src/eu/alefzero/owncloud/syncadapter/ContactSyncAdapter.java (Lennart Rosam     2012-05-16 09:48:34 +0200  80)                 }
435b31ba src/eu/alefzero/owncloud/syncadapter/ContactSyncAdapter.java (Lennart Rosam     2012-05-16 09:48:34 +0200  81)             } while (c.moveToNext());
435b31ba src/eu/alefzero/owncloud/syncadapter/ContactSyncAdapter.java (Lennart Rosam     2012-05-16 09:48:34 +0200  82)             // } while (c.moveToNext());
435b31ba src/eu/alefzero/owncloud/syncadapter/ContactSyncAdapter.java (Lennart Rosam     2012-05-16 09:48:34 +0200  83)         }
cf2c2eed src/eu/alefzero/owncloud/syncadapter/ContactSyncAdapter.java (Bartek Przybylski 2012-04-08 18:41:23 +0200  84) 
435b31ba src/eu/alefzero/owncloud/syncadapter/ContactSyncAdapter.java (Lennart Rosam     2012-05-16 09:48:34 +0200  85)     }
435b31ba src/eu/alefzero/owncloud/syncadapter/ContactSyncAdapter.java (Lennart Rosam     2012-05-16 09:48:34 +0200  86) 
435b31ba src/eu/alefzero/owncloud/syncadapter/ContactSyncAdapter.java (Lennart Rosam     2012-05-16 09:48:34 +0200  87)     private String getAddressBookUri() {
435b31ba src/eu/alefzero/owncloud/syncadapter/ContactSyncAdapter.java (Lennart Rosam     2012-05-16 09:48:34 +0200  88)         if (mAddrBookUri != null)
435b31ba src/eu/alefzero/owncloud/syncadapter/ContactSyncAdapter.java (Lennart Rosam     2012-05-16 09:48:34 +0200  89)             return mAddrBookUri;
435b31ba src/eu/alefzero/owncloud/syncadapter/ContactSyncAdapter.java (Lennart Rosam     2012-05-16 09:48:34 +0200  90) 
435b31ba src/eu/alefzero/owncloud/syncadapter/ContactSyncAdapter.java (Lennart Rosam     2012-05-16 09:48:34 +0200  91)         AccountManager am = getAccountManager();
981bf054 src/com/owncloud/android/syncadapter/ContactSyncAdapter.java (Bartek Przybylski 2012-09-29 23:55:19 +0200  92)         @SuppressWarnings("deprecation")
435b31ba src/eu/alefzero/owncloud/syncadapter/ContactSyncAdapter.java (Lennart Rosam     2012-05-16 09:48:34 +0200  93)         String uri = am.getUserData(getAccount(),
435b31ba src/eu/alefzero/owncloud/syncadapter/ContactSyncAdapter.java (Lennart Rosam     2012-05-16 09:48:34 +0200  94)                 AccountAuthenticator.KEY_OC_URL).replace(
435b31ba src/eu/alefzero/owncloud/syncadapter/ContactSyncAdapter.java (Lennart Rosam     2012-05-16 09:48:34 +0200  95)                 AccountUtils.WEBDAV_PATH_2_0, AccountUtils.CARDDAV_PATH_2_0);
435b31ba src/eu/alefzero/owncloud/syncadapter/ContactSyncAdapter.java (Lennart Rosam     2012-05-16 09:48:34 +0200  96)         uri += "/addressbooks/"
435b31ba src/eu/alefzero/owncloud/syncadapter/ContactSyncAdapter.java (Lennart Rosam     2012-05-16 09:48:34 +0200  97)                 + getAccount().name.substring(0,
435b31ba src/eu/alefzero/owncloud/syncadapter/ContactSyncAdapter.java (Lennart Rosam     2012-05-16 09:48:34 +0200  98)                         getAccount().name.lastIndexOf('@')) + "/default/";
435b31ba src/eu/alefzero/owncloud/syncadapter/ContactSyncAdapter.java (Lennart Rosam     2012-05-16 09:48:34 +0200  99)         mAddrBookUri = uri;
435b31ba src/eu/alefzero/owncloud/syncadapter/ContactSyncAdapter.java (Lennart Rosam     2012-05-16 09:48:34 +0200 100)         return uri;
435b31ba src/eu/alefzero/owncloud/syncadapter/ContactSyncAdapter.java (Lennart Rosam     2012-05-16 09:48:34 +0200 101)     }
435b31ba src/eu/alefzero/owncloud/syncadapter/ContactSyncAdapter.java (Lennart Rosam     2012-05-16 09:48:34 +0200 102) 
435b31ba src/eu/alefzero/owncloud/syncadapter/ContactSyncAdapter.java (Lennart Rosam     2012-05-16 09:48:34 +0200 103)     private FileInputStream getContactVcard(String lookupKey)
435b31ba src/eu/alefzero/owncloud/syncadapter/ContactSyncAdapter.java (Lennart Rosam     2012-05-16 09:48:34 +0200 104)             throws IOException {
435b31ba src/eu/alefzero/owncloud/syncadapter/ContactSyncAdapter.java (Lennart Rosam     2012-05-16 09:48:34 +0200 105)         Uri uri = Uri.withAppendedPath(
435b31ba src/eu/alefzero/owncloud/syncadapter/ContactSyncAdapter.java (Lennart Rosam     2012-05-16 09:48:34 +0200 106)                 ContactsContract.Contacts.CONTENT_VCARD_URI, lookupKey);
435b31ba src/eu/alefzero/owncloud/syncadapter/ContactSyncAdapter.java (Lennart Rosam     2012-05-16 09:48:34 +0200 107)         AssetFileDescriptor fd = getContext().getContentResolver()
435b31ba src/eu/alefzero/owncloud/syncadapter/ContactSyncAdapter.java (Lennart Rosam     2012-05-16 09:48:34 +0200 108)                 .openAssetFileDescriptor(uri, "r");
435b31ba src/eu/alefzero/owncloud/syncadapter/ContactSyncAdapter.java (Lennart Rosam     2012-05-16 09:48:34 +0200 109)         return fd.createInputStream();
435b31ba src/eu/alefzero/owncloud/syncadapter/ContactSyncAdapter.java (Lennart Rosam     2012-05-16 09:48:34 +0200 110)     }
435b31ba src/eu/alefzero/owncloud/syncadapter/ContactSyncAdapter.java (Lennart Rosam     2012-05-16 09:48:34 +0200 111) 
435b31ba src/eu/alefzero/owncloud/syncadapter/ContactSyncAdapter.java (Lennart Rosam     2012-05-16 09:48:34 +0200 112)     private Cursor getLocalContacts(boolean include_hidden_contacts) {
435b31ba src/eu/alefzero/owncloud/syncadapter/ContactSyncAdapter.java (Lennart Rosam     2012-05-16 09:48:34 +0200 113)         return getContext().getContentResolver().query(
435b31ba src/eu/alefzero/owncloud/syncadapter/ContactSyncAdapter.java (Lennart Rosam     2012-05-16 09:48:34 +0200 114)                 ContactsContract.Contacts.CONTENT_URI,
435b31ba src/eu/alefzero/owncloud/syncadapter/ContactSyncAdapter.java (Lennart Rosam     2012-05-16 09:48:34 +0200 115)                 new String[] { ContactsContract.Contacts._ID,
435b31ba src/eu/alefzero/owncloud/syncadapter/ContactSyncAdapter.java (Lennart Rosam     2012-05-16 09:48:34 +0200 116)                         ContactsContract.Contacts.LOOKUP_KEY },
435b31ba src/eu/alefzero/owncloud/syncadapter/ContactSyncAdapter.java (Lennart Rosam     2012-05-16 09:48:34 +0200 117)                 ContactsContract.Contacts.IN_VISIBLE_GROUP + " = ?",
435b31ba src/eu/alefzero/owncloud/syncadapter/ContactSyncAdapter.java (Lennart Rosam     2012-05-16 09:48:34 +0200 118)                 new String[] { (include_hidden_contacts ? "0" : "1") },
435b31ba src/eu/alefzero/owncloud/syncadapter/ContactSyncAdapter.java (Lennart Rosam     2012-05-16 09:48:34 +0200 119)                 ContactsContract.Contacts._ID + " DESC");
435b31ba src/eu/alefzero/owncloud/syncadapter/ContactSyncAdapter.java (Lennart Rosam     2012-05-16 09:48:34 +0200 120)     }
cf2c2eed src/eu/alefzero/owncloud/syncadapter/ContactSyncAdapter.java (Bartek Przybylski 2012-04-08 18:41:23 +0200 121) 
cf2c2eed src/eu/alefzero/owncloud/syncadapter/ContactSyncAdapter.java (Bartek Przybylski 2012-04-08 18:41:23 +0200 122) }
