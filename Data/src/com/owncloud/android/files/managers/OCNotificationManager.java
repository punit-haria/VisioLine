a4ba6170 (Bartek Przybylski 2012-07-31 17:43:37 +0200   1) /* ownCloud Android client application
a4ba6170 (Bartek Przybylski 2012-07-31 17:43:37 +0200   2)  *   Copyright (C) 2012  Bartek Przybylski
bb257ec7 (David A. Velasco  2013-02-07 18:45:10 +0100   3)  *   Copyright (C) 2012-2013 ownCloud Inc.
a4ba6170 (Bartek Przybylski 2012-07-31 17:43:37 +0200   4)  *
a4ba6170 (Bartek Przybylski 2012-07-31 17:43:37 +0200   5)  *   This program is free software: you can redistribute it and/or modify
bdc0332c (David A. Velasco  2013-04-17 12:26:13 +0200   6)  *   it under the terms of the GNU General Public License version 2,
bdc0332c (David A. Velasco  2013-04-17 12:26:13 +0200   7)  *   as published by the Free Software Foundation.
a4ba6170 (Bartek Przybylski 2012-07-31 17:43:37 +0200   8)  *
a4ba6170 (Bartek Przybylski 2012-07-31 17:43:37 +0200   9)  *   This program is distributed in the hope that it will be useful,
a4ba6170 (Bartek Przybylski 2012-07-31 17:43:37 +0200  10)  *   but WITHOUT ANY WARRANTY; without even the implied warranty of
a4ba6170 (Bartek Przybylski 2012-07-31 17:43:37 +0200  11)  *   MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
a4ba6170 (Bartek Przybylski 2012-07-31 17:43:37 +0200  12)  *   GNU General Public License for more details.
a4ba6170 (Bartek Przybylski 2012-07-31 17:43:37 +0200  13)  *
a4ba6170 (Bartek Przybylski 2012-07-31 17:43:37 +0200  14)  *   You should have received a copy of the GNU General Public License
a4ba6170 (Bartek Przybylski 2012-07-31 17:43:37 +0200  15)  *   along with this program.  If not, see <http://www.gnu.org/licenses/>.
a4ba6170 (Bartek Przybylski 2012-07-31 17:43:37 +0200  16)  *
a4ba6170 (Bartek Przybylski 2012-07-31 17:43:37 +0200  17)  */
a4ba6170 (Bartek Przybylski 2012-07-31 17:43:37 +0200  18) 
a4ba6170 (Bartek Przybylski 2012-07-31 17:43:37 +0200  19) package com.owncloud.android.files.managers;
a4ba6170 (Bartek Przybylski 2012-07-31 17:43:37 +0200  20) 
a4ba6170 (Bartek Przybylski 2012-07-31 17:43:37 +0200  21) import java.util.HashMap;
a4ba6170 (Bartek Przybylski 2012-07-31 17:43:37 +0200  22) import java.util.Map;
a4ba6170 (Bartek Przybylski 2012-07-31 17:43:37 +0200  23) 
a4ba6170 (Bartek Przybylski 2012-07-31 17:43:37 +0200  24) import android.app.Notification;
a4ba6170 (Bartek Przybylski 2012-07-31 17:43:37 +0200  25) import android.app.NotificationManager;
a4ba6170 (Bartek Przybylski 2012-07-31 17:43:37 +0200  26) import android.content.Context;
a4ba6170 (Bartek Przybylski 2012-07-31 17:43:37 +0200  27) import android.widget.RemoteViews;
a4ba6170 (Bartek Przybylski 2012-07-31 17:43:37 +0200  28) 
a4ba6170 (Bartek Przybylski 2012-07-31 17:43:37 +0200  29) import com.owncloud.android.R;
a4ba6170 (Bartek Przybylski 2012-07-31 17:43:37 +0200  30) 
a4ba6170 (Bartek Przybylski 2012-07-31 17:43:37 +0200  31) public class OCNotificationManager {
a4ba6170 (Bartek Przybylski 2012-07-31 17:43:37 +0200  32) 
a4ba6170 (Bartek Przybylski 2012-07-31 17:43:37 +0200  33)     enum NotificationType {
a4ba6170 (Bartek Przybylski 2012-07-31 17:43:37 +0200  34)         NOTIFICATION_SIMPLE,
a4ba6170 (Bartek Przybylski 2012-07-31 17:43:37 +0200  35)         NOTIFICATION_PROGRESS
a4ba6170 (Bartek Przybylski 2012-07-31 17:43:37 +0200  36)     }
a4ba6170 (Bartek Przybylski 2012-07-31 17:43:37 +0200  37)     
a4ba6170 (Bartek Przybylski 2012-07-31 17:43:37 +0200  38)     static public class NotificationData {
a4ba6170 (Bartek Przybylski 2012-07-31 17:43:37 +0200  39)         private String mText, mSubtitle;
a4ba6170 (Bartek Przybylski 2012-07-31 17:43:37 +0200  40)         private int mPercent;
a4ba6170 (Bartek Przybylski 2012-07-31 17:43:37 +0200  41)         private boolean mOngoing;
a4ba6170 (Bartek Przybylski 2012-07-31 17:43:37 +0200  42) 
a4ba6170 (Bartek Przybylski 2012-07-31 17:43:37 +0200  43)         public NotificationData(String text, String subtitle, boolean ongoing) {
a4ba6170 (Bartek Przybylski 2012-07-31 17:43:37 +0200  44)             this(text, subtitle, -1, ongoing);
a4ba6170 (Bartek Przybylski 2012-07-31 17:43:37 +0200  45)         }
a4ba6170 (Bartek Przybylski 2012-07-31 17:43:37 +0200  46)         
a4ba6170 (Bartek Przybylski 2012-07-31 17:43:37 +0200  47)         public NotificationData(int percent, boolean ongoing) {
a4ba6170 (Bartek Przybylski 2012-07-31 17:43:37 +0200  48)             this(null, null, percent, ongoing);
a4ba6170 (Bartek Przybylski 2012-07-31 17:43:37 +0200  49)         }
a4ba6170 (Bartek Przybylski 2012-07-31 17:43:37 +0200  50)         
a4ba6170 (Bartek Przybylski 2012-07-31 17:43:37 +0200  51)         public NotificationData(String text, int percent, boolean ongoing) {
a4ba6170 (Bartek Przybylski 2012-07-31 17:43:37 +0200  52)             this(text, null, percent, ongoing);
a4ba6170 (Bartek Przybylski 2012-07-31 17:43:37 +0200  53)         }
a4ba6170 (Bartek Przybylski 2012-07-31 17:43:37 +0200  54)         
a4ba6170 (Bartek Przybylski 2012-07-31 17:43:37 +0200  55)         public NotificationData(String text, String subtitle, int percent, boolean ongoing) {
a4ba6170 (Bartek Przybylski 2012-07-31 17:43:37 +0200  56)             mText = text;
a4ba6170 (Bartek Przybylski 2012-07-31 17:43:37 +0200  57)             mPercent = percent;
a4ba6170 (Bartek Przybylski 2012-07-31 17:43:37 +0200  58)             mSubtitle = subtitle;
a4ba6170 (Bartek Przybylski 2012-07-31 17:43:37 +0200  59)             mOngoing = ongoing;
a4ba6170 (Bartek Przybylski 2012-07-31 17:43:37 +0200  60)         }
a4ba6170 (Bartek Przybylski 2012-07-31 17:43:37 +0200  61)         
a4ba6170 (Bartek Przybylski 2012-07-31 17:43:37 +0200  62)         public String getText() { return mText; }
a4ba6170 (Bartek Przybylski 2012-07-31 17:43:37 +0200  63)         public int getPercent() { return mPercent; }
a4ba6170 (Bartek Przybylski 2012-07-31 17:43:37 +0200  64)         public String getSubtitle() { return mSubtitle; }
a4ba6170 (Bartek Przybylski 2012-07-31 17:43:37 +0200  65)         public boolean getOngoing() { return mOngoing; }
a4ba6170 (Bartek Przybylski 2012-07-31 17:43:37 +0200  66)     }
a4ba6170 (Bartek Przybylski 2012-07-31 17:43:37 +0200  67)     
a4ba6170 (Bartek Przybylski 2012-07-31 17:43:37 +0200  68)     static private OCNotificationManager mInstance = null;
a4ba6170 (Bartek Przybylski 2012-07-31 17:43:37 +0200  69) 
a4ba6170 (Bartek Przybylski 2012-07-31 17:43:37 +0200  70)     private class NotificationTypePair {
a4ba6170 (Bartek Przybylski 2012-07-31 17:43:37 +0200  71)         public Notification mNotificaiton;
a4ba6170 (Bartek Przybylski 2012-07-31 17:43:37 +0200  72)         public NotificationType mType;
a4ba6170 (Bartek Przybylski 2012-07-31 17:43:37 +0200  73)         public NotificationTypePair(Notification n, NotificationType type) {
a4ba6170 (Bartek Przybylski 2012-07-31 17:43:37 +0200  74)             mNotificaiton = n;
a4ba6170 (Bartek Przybylski 2012-07-31 17:43:37 +0200  75)             mType = type;
a4ba6170 (Bartek Przybylski 2012-07-31 17:43:37 +0200  76)         }
a4ba6170 (Bartek Przybylski 2012-07-31 17:43:37 +0200  77)     }
a4ba6170 (Bartek Przybylski 2012-07-31 17:43:37 +0200  78)     
a4ba6170 (Bartek Przybylski 2012-07-31 17:43:37 +0200  79)     private Context mContext;
a4ba6170 (Bartek Przybylski 2012-07-31 17:43:37 +0200  80)     private Map<Integer, NotificationTypePair> mNotificationMap;
a4ba6170 (Bartek Przybylski 2012-07-31 17:43:37 +0200  81)     private int mNotificationCounter;
a4ba6170 (Bartek Przybylski 2012-07-31 17:43:37 +0200  82)     NotificationManager mNM;
a4ba6170 (Bartek Przybylski 2012-07-31 17:43:37 +0200  83)     
a4ba6170 (Bartek Przybylski 2012-07-31 17:43:37 +0200  84)     static OCNotificationManager getInstance(Context context) {
a4ba6170 (Bartek Przybylski 2012-07-31 17:43:37 +0200  85)         if (mInstance == null)
a4ba6170 (Bartek Przybylski 2012-07-31 17:43:37 +0200  86)             mInstance = new OCNotificationManager(context);
a4ba6170 (Bartek Przybylski 2012-07-31 17:43:37 +0200  87)         return mInstance;
a4ba6170 (Bartek Przybylski 2012-07-31 17:43:37 +0200  88)     }
a4ba6170 (Bartek Przybylski 2012-07-31 17:43:37 +0200  89)     
a4ba6170 (Bartek Przybylski 2012-07-31 17:43:37 +0200  90)     OCNotificationManager(Context context) {
a4ba6170 (Bartek Przybylski 2012-07-31 17:43:37 +0200  91)         mContext = context;
a4ba6170 (Bartek Przybylski 2012-07-31 17:43:37 +0200  92)         mNotificationMap = new HashMap<Integer, NotificationTypePair>();
a4ba6170 (Bartek Przybylski 2012-07-31 17:43:37 +0200  93)         mNM = (NotificationManager)mContext.getSystemService(Context.NOTIFICATION_SERVICE);
a4ba6170 (Bartek Przybylski 2012-07-31 17:43:37 +0200  94)         mNotificationCounter = 0;
a4ba6170 (Bartek Przybylski 2012-07-31 17:43:37 +0200  95)     }
a4ba6170 (Bartek Przybylski 2012-07-31 17:43:37 +0200  96)     
a4ba6170 (Bartek Przybylski 2012-07-31 17:43:37 +0200  97)     public int postNotification(NotificationType type, NotificationData data) {
a4ba6170 (Bartek Przybylski 2012-07-31 17:43:37 +0200  98)         mNotificationCounter++;
a4ba6170 (Bartek Przybylski 2012-07-31 17:43:37 +0200  99)         Notification notification = null;
a4ba6170 (Bartek Przybylski 2012-07-31 17:43:37 +0200 100)         
a4ba6170 (Bartek Przybylski 2012-07-31 17:43:37 +0200 101)         switch (type) {
a4ba6170 (Bartek Przybylski 2012-07-31 17:43:37 +0200 102)             case NOTIFICATION_SIMPLE:
a4ba6170 (Bartek Przybylski 2012-07-31 17:43:37 +0200 103)                 notification = new Notification(R.drawable.icon, data.getText(), System.currentTimeMillis());
a4ba6170 (Bartek Przybylski 2012-07-31 17:43:37 +0200 104)                 break;
a4ba6170 (Bartek Przybylski 2012-07-31 17:43:37 +0200 105)             case NOTIFICATION_PROGRESS:
a4ba6170 (Bartek Przybylski 2012-07-31 17:43:37 +0200 106)                 notification = new Notification();
a4ba6170 (Bartek Przybylski 2012-07-31 17:43:37 +0200 107)                 notification.contentView = new RemoteViews(mContext.getPackageName(), R.layout.progressbar_layout);
a4ba6170 (Bartek Przybylski 2012-07-31 17:43:37 +0200 108)                 notification.contentView.setTextViewText(R.id.status_text,
a4ba6170 (Bartek Przybylski 2012-07-31 17:43:37 +0200 109)                                                          data.getText());
a4ba6170 (Bartek Przybylski 2012-07-31 17:43:37 +0200 110)                 notification.contentView.setImageViewResource(R.id.status_icon,
a4ba6170 (Bartek Przybylski 2012-07-31 17:43:37 +0200 111)                                                               R.id.icon);
a4ba6170 (Bartek Przybylski 2012-07-31 17:43:37 +0200 112)                 notification.contentView.setProgressBar(R.id.status_progress,
a4ba6170 (Bartek Przybylski 2012-07-31 17:43:37 +0200 113)                                                         100,
a4ba6170 (Bartek Przybylski 2012-07-31 17:43:37 +0200 114)                                                         data.getPercent(),
a4ba6170 (Bartek Przybylski 2012-07-31 17:43:37 +0200 115)                                                         false);
a4ba6170 (Bartek Przybylski 2012-07-31 17:43:37 +0200 116)                 break;
a4ba6170 (Bartek Przybylski 2012-07-31 17:43:37 +0200 117)             default:
a4ba6170 (Bartek Przybylski 2012-07-31 17:43:37 +0200 118)                 return -1;
a4ba6170 (Bartek Przybylski 2012-07-31 17:43:37 +0200 119)         }
a4ba6170 (Bartek Przybylski 2012-07-31 17:43:37 +0200 120)         if (data.getOngoing()) {
a4ba6170 (Bartek Przybylski 2012-07-31 17:43:37 +0200 121)             notification.flags |= notification.flags | Notification.FLAG_ONGOING_EVENT;
a4ba6170 (Bartek Przybylski 2012-07-31 17:43:37 +0200 122)         }
a4ba6170 (Bartek Przybylski 2012-07-31 17:43:37 +0200 123)         
a4ba6170 (Bartek Przybylski 2012-07-31 17:43:37 +0200 124)         mNotificationMap.put(mNotificationCounter, new NotificationTypePair(notification, type));
a4ba6170 (Bartek Przybylski 2012-07-31 17:43:37 +0200 125)         return mNotificationCounter;
a4ba6170 (Bartek Przybylski 2012-07-31 17:43:37 +0200 126)     }
a4ba6170 (Bartek Przybylski 2012-07-31 17:43:37 +0200 127)     
a4ba6170 (Bartek Przybylski 2012-07-31 17:43:37 +0200 128)     public boolean updateNotification(int notification_id, NotificationData data) {
a4ba6170 (Bartek Przybylski 2012-07-31 17:43:37 +0200 129)         if (!mNotificationMap.containsKey(notification_id)) {
a4ba6170 (Bartek Przybylski 2012-07-31 17:43:37 +0200 130)             return false;
a4ba6170 (Bartek Przybylski 2012-07-31 17:43:37 +0200 131)         }
a4ba6170 (Bartek Przybylski 2012-07-31 17:43:37 +0200 132)         NotificationTypePair pair = mNotificationMap.get(notification_id);
a4ba6170 (Bartek Przybylski 2012-07-31 17:43:37 +0200 133)         switch (pair.mType) {
a4ba6170 (Bartek Przybylski 2012-07-31 17:43:37 +0200 134)             case NOTIFICATION_PROGRESS:
a4ba6170 (Bartek Przybylski 2012-07-31 17:43:37 +0200 135)                 pair.mNotificaiton.contentView.setProgressBar(R.id.status_text,
a4ba6170 (Bartek Przybylski 2012-07-31 17:43:37 +0200 136)                                                               100,
a4ba6170 (Bartek Przybylski 2012-07-31 17:43:37 +0200 137)                                                               data.getPercent(),
a4ba6170 (Bartek Przybylski 2012-07-31 17:43:37 +0200 138)                                                               false);
a4ba6170 (Bartek Przybylski 2012-07-31 17:43:37 +0200 139)                 return true;
a4ba6170 (Bartek Przybylski 2012-07-31 17:43:37 +0200 140)             case NOTIFICATION_SIMPLE:
a4ba6170 (Bartek Przybylski 2012-07-31 17:43:37 +0200 141)                 pair.mNotificaiton = new Notification(R.drawable.icon,
a4ba6170 (Bartek Przybylski 2012-07-31 17:43:37 +0200 142)                                                       data.getText(), System.currentTimeMillis());
a4ba6170 (Bartek Przybylski 2012-07-31 17:43:37 +0200 143)                 mNM.notify(notification_id, pair.mNotificaiton);
a4ba6170 (Bartek Przybylski 2012-07-31 17:43:37 +0200 144)                 return true;
a4ba6170 (Bartek Przybylski 2012-07-31 17:43:37 +0200 145)             default:
a4ba6170 (Bartek Przybylski 2012-07-31 17:43:37 +0200 146)                 return false;
a4ba6170 (Bartek Przybylski 2012-07-31 17:43:37 +0200 147)         }
a4ba6170 (Bartek Przybylski 2012-07-31 17:43:37 +0200 148)     }
a4ba6170 (Bartek Przybylski 2012-07-31 17:43:37 +0200 149)     
a4ba6170 (Bartek Przybylski 2012-07-31 17:43:37 +0200 150)     public void discardNotification(int notification_id) {
a4ba6170 (Bartek Przybylski 2012-07-31 17:43:37 +0200 151)         mNM.cancel(notification_id);
a4ba6170 (Bartek Przybylski 2012-07-31 17:43:37 +0200 152)         mNotificationMap.remove(notification_id);
a4ba6170 (Bartek Przybylski 2012-07-31 17:43:37 +0200 153)     }
a4ba6170 (Bartek Przybylski 2012-07-31 17:43:37 +0200 154) }
