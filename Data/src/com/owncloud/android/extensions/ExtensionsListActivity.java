92080afe src/com/owncloud/android/extensions/ExtensionsListActivity.java (Bartek Przybylski 2012-10-28 00:12:15 +0200   1) /* ownCloud Android client application
92080afe src/com/owncloud/android/extensions/ExtensionsListActivity.java (Bartek Przybylski 2012-10-28 00:12:15 +0200   2)  *   Copyright (C) 2012 Bartek Przybylski
bb257ec7 src/com/owncloud/android/extensions/ExtensionsListActivity.java (David A. Velasco  2013-02-07 18:45:10 +0100   3)  *   Copyright (C) 2012-2013 ownCloud Inc.
92080afe src/com/owncloud/android/extensions/ExtensionsListActivity.java (Bartek Przybylski 2012-10-28 00:12:15 +0200   4)  *
92080afe src/com/owncloud/android/extensions/ExtensionsListActivity.java (Bartek Przybylski 2012-10-28 00:12:15 +0200   5)  *   This program is free software: you can redistribute it and/or modify
bdc0332c src/com/owncloud/android/extensions/ExtensionsListActivity.java (David A. Velasco  2013-04-17 12:26:13 +0200   6)  *   it under the terms of the GNU General Public License version 2,
bdc0332c src/com/owncloud/android/extensions/ExtensionsListActivity.java (David A. Velasco  2013-04-17 12:26:13 +0200   7)  *   as published by the Free Software Foundation.
92080afe src/com/owncloud/android/extensions/ExtensionsListActivity.java (Bartek Przybylski 2012-10-28 00:12:15 +0200   8)  *
92080afe src/com/owncloud/android/extensions/ExtensionsListActivity.java (Bartek Przybylski 2012-10-28 00:12:15 +0200   9)  *   This program is distributed in the hope that it will be useful,
92080afe src/com/owncloud/android/extensions/ExtensionsListActivity.java (Bartek Przybylski 2012-10-28 00:12:15 +0200  10)  *   but WITHOUT ANY WARRANTY; without even the implied warranty of
92080afe src/com/owncloud/android/extensions/ExtensionsListActivity.java (Bartek Przybylski 2012-10-28 00:12:15 +0200  11)  *   MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
92080afe src/com/owncloud/android/extensions/ExtensionsListActivity.java (Bartek Przybylski 2012-10-28 00:12:15 +0200  12)  *   GNU General Public License for more details.
92080afe src/com/owncloud/android/extensions/ExtensionsListActivity.java (Bartek Przybylski 2012-10-28 00:12:15 +0200  13)  *
92080afe src/com/owncloud/android/extensions/ExtensionsListActivity.java (Bartek Przybylski 2012-10-28 00:12:15 +0200  14)  *   You should have received a copy of the GNU General Public License
92080afe src/com/owncloud/android/extensions/ExtensionsListActivity.java (Bartek Przybylski 2012-10-28 00:12:15 +0200  15)  *   along with this program.  If not, see <http://www.gnu.org/licenses/>.
92080afe src/com/owncloud/android/extensions/ExtensionsListActivity.java (Bartek Przybylski 2012-10-28 00:12:15 +0200  16)  *
92080afe src/com/owncloud/android/extensions/ExtensionsListActivity.java (Bartek Przybylski 2012-10-28 00:12:15 +0200  17)  */
92080afe src/com/owncloud/android/extensions/ExtensionsListActivity.java (Bartek Przybylski 2012-10-28 00:12:15 +0200  18) 
a4ba6170 src/com/owncloud/android/extensions/ExtensionsListActivity.java (Bartek Przybylski 2012-07-31 17:43:37 +0200  19) package com.owncloud.android.extensions;
0aeb4258 src/eu/alefzero/owncloud/extensions/ExtensionsListActivity.java (Bartek Przybylski 2012-05-13 16:13:13 +0200  20) 
0aeb4258 src/eu/alefzero/owncloud/extensions/ExtensionsListActivity.java (Bartek Przybylski 2012-05-13 16:13:13 +0200  21) import java.util.HashMap;
0aeb4258 src/eu/alefzero/owncloud/extensions/ExtensionsListActivity.java (Bartek Przybylski 2012-05-13 16:13:13 +0200  22) import java.util.LinkedList;
0aeb4258 src/eu/alefzero/owncloud/extensions/ExtensionsListActivity.java (Bartek Przybylski 2012-05-13 16:13:13 +0200  23) 
0aeb4258 src/eu/alefzero/owncloud/extensions/ExtensionsListActivity.java (Bartek Przybylski 2012-05-13 16:13:13 +0200  24) import org.apache.commons.httpclient.HttpClient;
0aeb4258 src/eu/alefzero/owncloud/extensions/ExtensionsListActivity.java (Bartek Przybylski 2012-05-13 16:13:13 +0200  25) import org.apache.commons.httpclient.methods.GetMethod;
0aeb4258 src/eu/alefzero/owncloud/extensions/ExtensionsListActivity.java (Bartek Przybylski 2012-05-13 16:13:13 +0200  26) import org.json.JSONArray;
0aeb4258 src/eu/alefzero/owncloud/extensions/ExtensionsListActivity.java (Bartek Przybylski 2012-05-13 16:13:13 +0200  27) import org.json.JSONException;
0aeb4258 src/eu/alefzero/owncloud/extensions/ExtensionsListActivity.java (Bartek Przybylski 2012-05-13 16:13:13 +0200  28) import org.json.JSONObject;
0aeb4258 src/eu/alefzero/owncloud/extensions/ExtensionsListActivity.java (Bartek Przybylski 2012-05-13 16:13:13 +0200  29) 
8e36e7cc src/com/owncloud/android/extensions/ExtensionsListActivity.java (zerginator        2013-03-12 07:56:27 +0100  30) import com.owncloud.android.Log_OC;
a4ba6170 src/com/owncloud/android/extensions/ExtensionsListActivity.java (Bartek Przybylski 2012-07-31 17:43:37 +0200  31) import com.owncloud.android.utils.OwnCloudVersion;
a4ba6170 src/com/owncloud/android/extensions/ExtensionsListActivity.java (Bartek Przybylski 2012-07-31 17:43:37 +0200  32) 
0aeb4258 src/eu/alefzero/owncloud/extensions/ExtensionsListActivity.java (Bartek Przybylski 2012-05-13 16:13:13 +0200  33) 
0aeb4258 src/eu/alefzero/owncloud/extensions/ExtensionsListActivity.java (Bartek Przybylski 2012-05-13 16:13:13 +0200  34) import android.R;
0aeb4258 src/eu/alefzero/owncloud/extensions/ExtensionsListActivity.java (Bartek Przybylski 2012-05-13 16:13:13 +0200  35) import android.app.ListActivity;
0aeb4258 src/eu/alefzero/owncloud/extensions/ExtensionsListActivity.java (Bartek Przybylski 2012-05-13 16:13:13 +0200  36) import android.os.Bundle;
0aeb4258 src/eu/alefzero/owncloud/extensions/ExtensionsListActivity.java (Bartek Przybylski 2012-05-13 16:13:13 +0200  37) import android.os.Handler;
0aeb4258 src/eu/alefzero/owncloud/extensions/ExtensionsListActivity.java (Bartek Przybylski 2012-05-13 16:13:13 +0200  38) import android.widget.SimpleAdapter;
0aeb4258 src/eu/alefzero/owncloud/extensions/ExtensionsListActivity.java (Bartek Przybylski 2012-05-13 16:13:13 +0200  39) 
0aeb4258 src/eu/alefzero/owncloud/extensions/ExtensionsListActivity.java (Bartek Przybylski 2012-05-13 16:13:13 +0200  40) public class ExtensionsListActivity extends ListActivity {
0aeb4258 src/eu/alefzero/owncloud/extensions/ExtensionsListActivity.java (Bartek Przybylski 2012-05-13 16:13:13 +0200  41) 
435b31ba src/eu/alefzero/owncloud/extensions/ExtensionsListActivity.java (Lennart Rosam     2012-05-16 09:48:34 +0200  42)     private static final String packages_url = "http://alefzero.eu/a/packages.php";
435b31ba src/eu/alefzero/owncloud/extensions/ExtensionsListActivity.java (Lennart Rosam     2012-05-16 09:48:34 +0200  43) 
435b31ba src/eu/alefzero/owncloud/extensions/ExtensionsListActivity.java (Lennart Rosam     2012-05-16 09:48:34 +0200  44)     private Thread mGetterThread;
435b31ba src/eu/alefzero/owncloud/extensions/ExtensionsListActivity.java (Lennart Rosam     2012-05-16 09:48:34 +0200  45)     private final Handler mHandler = new Handler();
0aeb4258 src/eu/alefzero/owncloud/extensions/ExtensionsListActivity.java (Bartek Przybylski 2012-05-13 16:13:13 +0200  46) 
0aeb4258 src/eu/alefzero/owncloud/extensions/ExtensionsListActivity.java (Bartek Przybylski 2012-05-13 16:13:13 +0200  47)     @Override
435b31ba src/eu/alefzero/owncloud/extensions/ExtensionsListActivity.java (Lennart Rosam     2012-05-16 09:48:34 +0200  48)     protected void onCreate(Bundle savedInstanceState) {
435b31ba src/eu/alefzero/owncloud/extensions/ExtensionsListActivity.java (Lennart Rosam     2012-05-16 09:48:34 +0200  49)         super.onCreate(savedInstanceState);
435b31ba src/eu/alefzero/owncloud/extensions/ExtensionsListActivity.java (Lennart Rosam     2012-05-16 09:48:34 +0200  50)         mGetterThread = new Thread(new JsonGetter());
435b31ba src/eu/alefzero/owncloud/extensions/ExtensionsListActivity.java (Lennart Rosam     2012-05-16 09:48:34 +0200  51)         mGetterThread.start();
435b31ba src/eu/alefzero/owncloud/extensions/ExtensionsListActivity.java (Lennart Rosam     2012-05-16 09:48:34 +0200  52)     }
435b31ba src/eu/alefzero/owncloud/extensions/ExtensionsListActivity.java (Lennart Rosam     2012-05-16 09:48:34 +0200  53) 
435b31ba src/eu/alefzero/owncloud/extensions/ExtensionsListActivity.java (Lennart Rosam     2012-05-16 09:48:34 +0200  54)     public void done(JSONArray a) {
435b31ba src/eu/alefzero/owncloud/extensions/ExtensionsListActivity.java (Lennart Rosam     2012-05-16 09:48:34 +0200  55)         LinkedList<HashMap<String, String>> ll = new LinkedList<HashMap<String, String>>();
435b31ba src/eu/alefzero/owncloud/extensions/ExtensionsListActivity.java (Lennart Rosam     2012-05-16 09:48:34 +0200  56)         for (int i = 0; i < a.length(); ++i) {
435b31ba src/eu/alefzero/owncloud/extensions/ExtensionsListActivity.java (Lennart Rosam     2012-05-16 09:48:34 +0200  57)             try {
435b31ba src/eu/alefzero/owncloud/extensions/ExtensionsListActivity.java (Lennart Rosam     2012-05-16 09:48:34 +0200  58)                 ExtensionApplicationEntry ela = new ExtensionApplicationEntry(
435b31ba src/eu/alefzero/owncloud/extensions/ExtensionsListActivity.java (Lennart Rosam     2012-05-16 09:48:34 +0200  59)                         ((JSONObject) a.get(i)));
435b31ba src/eu/alefzero/owncloud/extensions/ExtensionsListActivity.java (Lennart Rosam     2012-05-16 09:48:34 +0200  60)                 HashMap<String, String> ss = new HashMap<String, String>();
435b31ba src/eu/alefzero/owncloud/extensions/ExtensionsListActivity.java (Lennart Rosam     2012-05-16 09:48:34 +0200  61)                 ss.put("NAME", ela.getName());
435b31ba src/eu/alefzero/owncloud/extensions/ExtensionsListActivity.java (Lennart Rosam     2012-05-16 09:48:34 +0200  62)                 ss.put("DESC", ela.getDescription());
435b31ba src/eu/alefzero/owncloud/extensions/ExtensionsListActivity.java (Lennart Rosam     2012-05-16 09:48:34 +0200  63)                 ll.add(ss);
435b31ba src/eu/alefzero/owncloud/extensions/ExtensionsListActivity.java (Lennart Rosam     2012-05-16 09:48:34 +0200  64)             } catch (JSONException e) {
435b31ba src/eu/alefzero/owncloud/extensions/ExtensionsListActivity.java (Lennart Rosam     2012-05-16 09:48:34 +0200  65)                 e.printStackTrace();
435b31ba src/eu/alefzero/owncloud/extensions/ExtensionsListActivity.java (Lennart Rosam     2012-05-16 09:48:34 +0200  66)             }
435b31ba src/eu/alefzero/owncloud/extensions/ExtensionsListActivity.java (Lennart Rosam     2012-05-16 09:48:34 +0200  67)         }
435b31ba src/eu/alefzero/owncloud/extensions/ExtensionsListActivity.java (Lennart Rosam     2012-05-16 09:48:34 +0200  68)         setListAdapter(new SimpleAdapter(this, ll, R.layout.simple_list_item_2,
435b31ba src/eu/alefzero/owncloud/extensions/ExtensionsListActivity.java (Lennart Rosam     2012-05-16 09:48:34 +0200  69)                 new String[] { "NAME", "DESC" }, new int[] {
435b31ba src/eu/alefzero/owncloud/extensions/ExtensionsListActivity.java (Lennart Rosam     2012-05-16 09:48:34 +0200  70)                         android.R.id.text1, android.R.id.text2 }));
435b31ba src/eu/alefzero/owncloud/extensions/ExtensionsListActivity.java (Lennart Rosam     2012-05-16 09:48:34 +0200  71) 
435b31ba src/eu/alefzero/owncloud/extensions/ExtensionsListActivity.java (Lennart Rosam     2012-05-16 09:48:34 +0200  72)     }
435b31ba src/eu/alefzero/owncloud/extensions/ExtensionsListActivity.java (Lennart Rosam     2012-05-16 09:48:34 +0200  73) 
435b31ba src/eu/alefzero/owncloud/extensions/ExtensionsListActivity.java (Lennart Rosam     2012-05-16 09:48:34 +0200  74)     private class JsonGetter implements Runnable {
435b31ba src/eu/alefzero/owncloud/extensions/ExtensionsListActivity.java (Lennart Rosam     2012-05-16 09:48:34 +0200  75) 
0aeb4258 src/eu/alefzero/owncloud/extensions/ExtensionsListActivity.java (Bartek Przybylski 2012-05-13 16:13:13 +0200  76)         @Override
0aeb4258 src/eu/alefzero/owncloud/extensions/ExtensionsListActivity.java (Bartek Przybylski 2012-05-13 16:13:13 +0200  77)         public void run() {
435b31ba src/eu/alefzero/owncloud/extensions/ExtensionsListActivity.java (Lennart Rosam     2012-05-16 09:48:34 +0200  78)             HttpClient hc = new HttpClient();
435b31ba src/eu/alefzero/owncloud/extensions/ExtensionsListActivity.java (Lennart Rosam     2012-05-16 09:48:34 +0200  79)             GetMethod gm = new GetMethod(packages_url);
435b31ba src/eu/alefzero/owncloud/extensions/ExtensionsListActivity.java (Lennart Rosam     2012-05-16 09:48:34 +0200  80)             final JSONArray ar;
435b31ba src/eu/alefzero/owncloud/extensions/ExtensionsListActivity.java (Lennart Rosam     2012-05-16 09:48:34 +0200  81)             try {
435b31ba src/eu/alefzero/owncloud/extensions/ExtensionsListActivity.java (Lennart Rosam     2012-05-16 09:48:34 +0200  82)                 hc.executeMethod(gm);
8e36e7cc src/com/owncloud/android/extensions/ExtensionsListActivity.java (zerginator        2013-03-12 07:56:27 +0100  83)                 Log_OC.e("ASD", gm.getResponseBodyAsString() + "");
435b31ba src/eu/alefzero/owncloud/extensions/ExtensionsListActivity.java (Lennart Rosam     2012-05-16 09:48:34 +0200  84)                 ar = new JSONObject(gm.getResponseBodyAsString())
435b31ba src/eu/alefzero/owncloud/extensions/ExtensionsListActivity.java (Lennart Rosam     2012-05-16 09:48:34 +0200  85)                         .getJSONArray("apps");
435b31ba src/eu/alefzero/owncloud/extensions/ExtensionsListActivity.java (Lennart Rosam     2012-05-16 09:48:34 +0200  86)             } catch (Exception e) {
435b31ba src/eu/alefzero/owncloud/extensions/ExtensionsListActivity.java (Lennart Rosam     2012-05-16 09:48:34 +0200  87)                 e.printStackTrace();
435b31ba src/eu/alefzero/owncloud/extensions/ExtensionsListActivity.java (Lennart Rosam     2012-05-16 09:48:34 +0200  88)                 return;
435b31ba src/eu/alefzero/owncloud/extensions/ExtensionsListActivity.java (Lennart Rosam     2012-05-16 09:48:34 +0200  89)             }
435b31ba src/eu/alefzero/owncloud/extensions/ExtensionsListActivity.java (Lennart Rosam     2012-05-16 09:48:34 +0200  90) 
435b31ba src/eu/alefzero/owncloud/extensions/ExtensionsListActivity.java (Lennart Rosam     2012-05-16 09:48:34 +0200  91)             mHandler.post(new Runnable() {
435b31ba src/eu/alefzero/owncloud/extensions/ExtensionsListActivity.java (Lennart Rosam     2012-05-16 09:48:34 +0200  92)                 @Override
435b31ba src/eu/alefzero/owncloud/extensions/ExtensionsListActivity.java (Lennart Rosam     2012-05-16 09:48:34 +0200  93)                 public void run() {
435b31ba src/eu/alefzero/owncloud/extensions/ExtensionsListActivity.java (Lennart Rosam     2012-05-16 09:48:34 +0200  94)                     done(ar);
435b31ba src/eu/alefzero/owncloud/extensions/ExtensionsListActivity.java (Lennart Rosam     2012-05-16 09:48:34 +0200  95)                 }
435b31ba src/eu/alefzero/owncloud/extensions/ExtensionsListActivity.java (Lennart Rosam     2012-05-16 09:48:34 +0200  96)             });
435b31ba src/eu/alefzero/owncloud/extensions/ExtensionsListActivity.java (Lennart Rosam     2012-05-16 09:48:34 +0200  97) 
0aeb4258 src/eu/alefzero/owncloud/extensions/ExtensionsListActivity.java (Bartek Przybylski 2012-05-13 16:13:13 +0200  98)         }
435b31ba src/eu/alefzero/owncloud/extensions/ExtensionsListActivity.java (Lennart Rosam     2012-05-16 09:48:34 +0200  99) 
0aeb4258 src/eu/alefzero/owncloud/extensions/ExtensionsListActivity.java (Bartek Przybylski 2012-05-13 16:13:13 +0200 100)     }
435b31ba src/eu/alefzero/owncloud/extensions/ExtensionsListActivity.java (Lennart Rosam     2012-05-16 09:48:34 +0200 101) 
435b31ba src/eu/alefzero/owncloud/extensions/ExtensionsListActivity.java (Lennart Rosam     2012-05-16 09:48:34 +0200 102)     private class ExtensionApplicationEntry {
435b31ba src/eu/alefzero/owncloud/extensions/ExtensionsListActivity.java (Lennart Rosam     2012-05-16 09:48:34 +0200 103)         private static final String APP_NAME = "name";
435b31ba src/eu/alefzero/owncloud/extensions/ExtensionsListActivity.java (Lennart Rosam     2012-05-16 09:48:34 +0200 104)         private static final String APP_VERSION = "version";
435b31ba src/eu/alefzero/owncloud/extensions/ExtensionsListActivity.java (Lennart Rosam     2012-05-16 09:48:34 +0200 105)         private static final String APP_DESC = "description";
435b31ba src/eu/alefzero/owncloud/extensions/ExtensionsListActivity.java (Lennart Rosam     2012-05-16 09:48:34 +0200 106)         private static final String APP_ICON = "icon";
435b31ba src/eu/alefzero/owncloud/extensions/ExtensionsListActivity.java (Lennart Rosam     2012-05-16 09:48:34 +0200 107)         private static final String APP_URL = "download";
435b31ba src/eu/alefzero/owncloud/extensions/ExtensionsListActivity.java (Lennart Rosam     2012-05-16 09:48:34 +0200 108)         private static final String APP_PLAYID = "play_id";
435b31ba src/eu/alefzero/owncloud/extensions/ExtensionsListActivity.java (Lennart Rosam     2012-05-16 09:48:34 +0200 109) 
435b31ba src/eu/alefzero/owncloud/extensions/ExtensionsListActivity.java (Lennart Rosam     2012-05-16 09:48:34 +0200 110)         private String mName, mDescription, mIcon, mDownload, mPlayId;
435b31ba src/eu/alefzero/owncloud/extensions/ExtensionsListActivity.java (Lennart Rosam     2012-05-16 09:48:34 +0200 111)         private OwnCloudVersion mVersion;
435b31ba src/eu/alefzero/owncloud/extensions/ExtensionsListActivity.java (Lennart Rosam     2012-05-16 09:48:34 +0200 112) 
435b31ba src/eu/alefzero/owncloud/extensions/ExtensionsListActivity.java (Lennart Rosam     2012-05-16 09:48:34 +0200 113)         public ExtensionApplicationEntry(JSONObject appentry) {
435b31ba src/eu/alefzero/owncloud/extensions/ExtensionsListActivity.java (Lennart Rosam     2012-05-16 09:48:34 +0200 114)             try {
435b31ba src/eu/alefzero/owncloud/extensions/ExtensionsListActivity.java (Lennart Rosam     2012-05-16 09:48:34 +0200 115)                 mName = appentry.getString(APP_NAME);
435b31ba src/eu/alefzero/owncloud/extensions/ExtensionsListActivity.java (Lennart Rosam     2012-05-16 09:48:34 +0200 116)                 mDescription = appentry.getString(APP_DESC);
435b31ba src/eu/alefzero/owncloud/extensions/ExtensionsListActivity.java (Lennart Rosam     2012-05-16 09:48:34 +0200 117)                 mIcon = appentry.getString(APP_ICON);
435b31ba src/eu/alefzero/owncloud/extensions/ExtensionsListActivity.java (Lennart Rosam     2012-05-16 09:48:34 +0200 118)                 mDownload = appentry.getString(APP_URL);
435b31ba src/eu/alefzero/owncloud/extensions/ExtensionsListActivity.java (Lennart Rosam     2012-05-16 09:48:34 +0200 119)                 mPlayId = appentry.getString(APP_PLAYID);
435b31ba src/eu/alefzero/owncloud/extensions/ExtensionsListActivity.java (Lennart Rosam     2012-05-16 09:48:34 +0200 120)                 mVersion = new OwnCloudVersion(appentry.getString(APP_VERSION));
435b31ba src/eu/alefzero/owncloud/extensions/ExtensionsListActivity.java (Lennart Rosam     2012-05-16 09:48:34 +0200 121)             } catch (JSONException e) {
435b31ba src/eu/alefzero/owncloud/extensions/ExtensionsListActivity.java (Lennart Rosam     2012-05-16 09:48:34 +0200 122)                 e.printStackTrace();
435b31ba src/eu/alefzero/owncloud/extensions/ExtensionsListActivity.java (Lennart Rosam     2012-05-16 09:48:34 +0200 123)             }
435b31ba src/eu/alefzero/owncloud/extensions/ExtensionsListActivity.java (Lennart Rosam     2012-05-16 09:48:34 +0200 124)         }
435b31ba src/eu/alefzero/owncloud/extensions/ExtensionsListActivity.java (Lennart Rosam     2012-05-16 09:48:34 +0200 125) 
435b31ba src/eu/alefzero/owncloud/extensions/ExtensionsListActivity.java (Lennart Rosam     2012-05-16 09:48:34 +0200 126)         public String getName() {
435b31ba src/eu/alefzero/owncloud/extensions/ExtensionsListActivity.java (Lennart Rosam     2012-05-16 09:48:34 +0200 127)             return mName;
435b31ba src/eu/alefzero/owncloud/extensions/ExtensionsListActivity.java (Lennart Rosam     2012-05-16 09:48:34 +0200 128)         }
435b31ba src/eu/alefzero/owncloud/extensions/ExtensionsListActivity.java (Lennart Rosam     2012-05-16 09:48:34 +0200 129) 
435b31ba src/eu/alefzero/owncloud/extensions/ExtensionsListActivity.java (Lennart Rosam     2012-05-16 09:48:34 +0200 130)         public String getDescription() {
435b31ba src/eu/alefzero/owncloud/extensions/ExtensionsListActivity.java (Lennart Rosam     2012-05-16 09:48:34 +0200 131)             return mDescription;
435b31ba src/eu/alefzero/owncloud/extensions/ExtensionsListActivity.java (Lennart Rosam     2012-05-16 09:48:34 +0200 132)         }
435b31ba src/eu/alefzero/owncloud/extensions/ExtensionsListActivity.java (Lennart Rosam     2012-05-16 09:48:34 +0200 133) 
aa14479e src/com/owncloud/android/extensions/ExtensionsListActivity.java (Bartek Przybylski 2012-09-29 23:17:39 +0200 134)         @SuppressWarnings("unused")
435b31ba src/eu/alefzero/owncloud/extensions/ExtensionsListActivity.java (Lennart Rosam     2012-05-16 09:48:34 +0200 135)         public String getIcon() {
435b31ba src/eu/alefzero/owncloud/extensions/ExtensionsListActivity.java (Lennart Rosam     2012-05-16 09:48:34 +0200 136)             return mIcon;
435b31ba src/eu/alefzero/owncloud/extensions/ExtensionsListActivity.java (Lennart Rosam     2012-05-16 09:48:34 +0200 137)         }
435b31ba src/eu/alefzero/owncloud/extensions/ExtensionsListActivity.java (Lennart Rosam     2012-05-16 09:48:34 +0200 138) 
aa14479e src/com/owncloud/android/extensions/ExtensionsListActivity.java (Bartek Przybylski 2012-09-29 23:17:39 +0200 139)         @SuppressWarnings("unused")
435b31ba src/eu/alefzero/owncloud/extensions/ExtensionsListActivity.java (Lennart Rosam     2012-05-16 09:48:34 +0200 140)         public String getDownload() {
435b31ba src/eu/alefzero/owncloud/extensions/ExtensionsListActivity.java (Lennart Rosam     2012-05-16 09:48:34 +0200 141)             return mDownload;
435b31ba src/eu/alefzero/owncloud/extensions/ExtensionsListActivity.java (Lennart Rosam     2012-05-16 09:48:34 +0200 142)         }
435b31ba src/eu/alefzero/owncloud/extensions/ExtensionsListActivity.java (Lennart Rosam     2012-05-16 09:48:34 +0200 143) 
aa14479e src/com/owncloud/android/extensions/ExtensionsListActivity.java (Bartek Przybylski 2012-09-29 23:17:39 +0200 144)         @SuppressWarnings("unused")
435b31ba src/eu/alefzero/owncloud/extensions/ExtensionsListActivity.java (Lennart Rosam     2012-05-16 09:48:34 +0200 145)         public String getPlayId() {
435b31ba src/eu/alefzero/owncloud/extensions/ExtensionsListActivity.java (Lennart Rosam     2012-05-16 09:48:34 +0200 146)             return mPlayId;
435b31ba src/eu/alefzero/owncloud/extensions/ExtensionsListActivity.java (Lennart Rosam     2012-05-16 09:48:34 +0200 147)         }
435b31ba src/eu/alefzero/owncloud/extensions/ExtensionsListActivity.java (Lennart Rosam     2012-05-16 09:48:34 +0200 148) 
aa14479e src/com/owncloud/android/extensions/ExtensionsListActivity.java (Bartek Przybylski 2012-09-29 23:17:39 +0200 149)         @SuppressWarnings("unused")
435b31ba src/eu/alefzero/owncloud/extensions/ExtensionsListActivity.java (Lennart Rosam     2012-05-16 09:48:34 +0200 150)         public OwnCloudVersion getVersion() {
435b31ba src/eu/alefzero/owncloud/extensions/ExtensionsListActivity.java (Lennart Rosam     2012-05-16 09:48:34 +0200 151)             return mVersion;
435b31ba src/eu/alefzero/owncloud/extensions/ExtensionsListActivity.java (Lennart Rosam     2012-05-16 09:48:34 +0200 152)         }
0aeb4258 src/eu/alefzero/owncloud/extensions/ExtensionsListActivity.java (Bartek Przybylski 2012-05-13 16:13:13 +0200 153)     }
aa14479e src/com/owncloud/android/extensions/ExtensionsListActivity.java (Bartek Przybylski 2012-09-29 23:17:39 +0200 154)     
0aeb4258 src/eu/alefzero/owncloud/extensions/ExtensionsListActivity.java (Bartek Przybylski 2012-05-13 16:13:13 +0200 155) }
