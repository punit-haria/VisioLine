63c213cb (Bartek Przybylski 2012-04-30 19:39:01 +0200   1) /* ownCloud Android client application
63c213cb (Bartek Przybylski 2012-04-30 19:39:01 +0200   2)  *   Copyright (C) 2012  ownCloud
63c213cb (Bartek Przybylski 2012-04-30 19:39:01 +0200   3)  *
63c213cb (Bartek Przybylski 2012-04-30 19:39:01 +0200   4)  *   This program is free software: you can redistribute it and/or modify
bdc0332c (David A. Velasco  2013-04-17 12:26:13 +0200   5)  *   it under the terms of the GNU General Public License version 2,
bdc0332c (David A. Velasco  2013-04-17 12:26:13 +0200   6)  *   as published by the Free Software Foundation.
63c213cb (Bartek Przybylski 2012-04-30 19:39:01 +0200   7)  *
63c213cb (Bartek Przybylski 2012-04-30 19:39:01 +0200   8)  *   This program is distributed in the hope that it will be useful,
63c213cb (Bartek Przybylski 2012-04-30 19:39:01 +0200   9)  *   but WITHOUT ANY WARRANTY; without even the implied warranty of
63c213cb (Bartek Przybylski 2012-04-30 19:39:01 +0200  10)  *   MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
63c213cb (Bartek Przybylski 2012-04-30 19:39:01 +0200  11)  *   GNU General Public License for more details.
63c213cb (Bartek Przybylski 2012-04-30 19:39:01 +0200  12)  *
63c213cb (Bartek Przybylski 2012-04-30 19:39:01 +0200  13)  *   You should have received a copy of the GNU General Public License
63c213cb (Bartek Przybylski 2012-04-30 19:39:01 +0200  14)  *   along with this program.  If not, see <http://www.gnu.org/licenses/>.
63c213cb (Bartek Przybylski 2012-04-30 19:39:01 +0200  15)  *
63c213cb (Bartek Przybylski 2012-04-30 19:39:01 +0200  16)  */
87814cec (Bartek Przybylski 2012-04-29 00:14:01 +0200  17) package eu.alefzero.webdav;
87814cec (Bartek Przybylski 2012-04-29 00:14:01 +0200  18) 
87814cec (Bartek Przybylski 2012-04-29 00:14:01 +0200  19) import java.util.Date;
87814cec (Bartek Przybylski 2012-04-29 00:14:01 +0200  20) 
87814cec (Bartek Przybylski 2012-04-29 00:14:01 +0200  21) import org.apache.jackrabbit.webdav.MultiStatusResponse;
87814cec (Bartek Przybylski 2012-04-29 00:14:01 +0200  22) import org.apache.jackrabbit.webdav.property.DavProperty;
87814cec (Bartek Przybylski 2012-04-29 00:14:01 +0200  23) import org.apache.jackrabbit.webdav.property.DavPropertyName;
87814cec (Bartek Przybylski 2012-04-29 00:14:01 +0200  24) import org.apache.jackrabbit.webdav.property.DavPropertySet;
87814cec (Bartek Przybylski 2012-04-29 00:14:01 +0200  25) 
8e36e7cc (zerginator        2013-03-12 07:56:27 +0100  26) import com.owncloud.android.Log_OC;
8e36e7cc (zerginator        2013-03-12 07:56:27 +0100  27) 
c6b553f6 (David A. Velasco  2012-07-17 12:59:15 +0200  28) import android.net.Uri;
87814cec (Bartek Przybylski 2012-04-29 00:14:01 +0200  29) 
87814cec (Bartek Przybylski 2012-04-29 00:14:01 +0200  30) public class WebdavEntry {
435b31ba (Lennart Rosam     2012-05-16 09:48:34 +0200  31)     private String mName, mPath, mUri, mContentType;
435b31ba (Lennart Rosam     2012-05-16 09:48:34 +0200  32)     private long mContentLength, mCreateTimestamp, mModifiedTimestamp;
435b31ba (Lennart Rosam     2012-05-16 09:48:34 +0200  33) 
cfe4273d (Bartek Przybylski 2012-05-21 22:20:18 +0200  34)     public WebdavEntry(MultiStatusResponse ms, String splitElement) {
435b31ba (Lennart Rosam     2012-05-16 09:48:34 +0200  35)         resetData();
435b31ba (Lennart Rosam     2012-05-16 09:48:34 +0200  36)         if (ms.getStatus().length != 0) {
435b31ba (Lennart Rosam     2012-05-16 09:48:34 +0200  37)             mUri = ms.getHref();
435b31ba (Lennart Rosam     2012-05-16 09:48:34 +0200  38) 
cfe4273d (Bartek Przybylski 2012-05-21 22:20:18 +0200  39)             mPath = mUri.split(splitElement, 2)[1];
435b31ba (Lennart Rosam     2012-05-16 09:48:34 +0200  40) 
435b31ba (Lennart Rosam     2012-05-16 09:48:34 +0200  41)             int status = ms.getStatus()[0].getStatusCode();
435b31ba (Lennart Rosam     2012-05-16 09:48:34 +0200  42)             DavPropertySet propSet = ms.getProperties(status);
435b31ba (Lennart Rosam     2012-05-16 09:48:34 +0200  43)             @SuppressWarnings("rawtypes")
435b31ba (Lennart Rosam     2012-05-16 09:48:34 +0200  44)             DavProperty prop = propSet.get(DavPropertyName.DISPLAYNAME);
435b31ba (Lennart Rosam     2012-05-16 09:48:34 +0200  45)             if (prop != null)
435b31ba (Lennart Rosam     2012-05-16 09:48:34 +0200  46)                 mName = (String) prop.getName().toString();
435b31ba (Lennart Rosam     2012-05-16 09:48:34 +0200  47)             else {
435b31ba (Lennart Rosam     2012-05-16 09:48:34 +0200  48)                 String[] tmp = mPath.split("/");
435b31ba (Lennart Rosam     2012-05-16 09:48:34 +0200  49)                 if (tmp.length > 0)
435b31ba (Lennart Rosam     2012-05-16 09:48:34 +0200  50)                     mName = tmp[tmp.length - 1];
435b31ba (Lennart Rosam     2012-05-16 09:48:34 +0200  51)             }
435b31ba (Lennart Rosam     2012-05-16 09:48:34 +0200  52) 
cce7e124 (Bartek Przybylski 2012-10-30 22:36:50 +0100  53)             // use unknown mimetype as default behavior
cce7e124 (Bartek Przybylski 2012-10-30 22:36:50 +0100  54)             mContentType = "application/octet-stream";
435b31ba (Lennart Rosam     2012-05-16 09:48:34 +0200  55)             prop = propSet.get(DavPropertyName.GETCONTENTTYPE);
435b31ba (Lennart Rosam     2012-05-16 09:48:34 +0200  56)             if (prop != null) {
435b31ba (Lennart Rosam     2012-05-16 09:48:34 +0200  57)                 mContentType = (String) prop.getValue();
121a462b (David A. Velasco  2012-06-28 10:59:27 +0200  58)                 // dvelasco: some builds of ownCloud server 4.0.x added a trailing ';' to the MIME type ; if looks fixed, but let's be cautious
121a462b (David A. Velasco  2012-06-28 10:59:27 +0200  59)                 if (mContentType.indexOf(";") >= 0) {
121a462b (David A. Velasco  2012-06-28 10:59:27 +0200  60)                     mContentType = mContentType.substring(0, mContentType.indexOf(";"));
121a462b (David A. Velasco  2012-06-28 10:59:27 +0200  61)                 }
4ee11fa7 (David A. Velasco  2012-10-30 11:24:09 +0100  62)             }
4ee11fa7 (David A. Velasco  2012-10-30 11:24:09 +0100  63)             
bdc0332c (David A. Velasco  2013-04-17 12:26:13 +0200  64)             // check if it's a folder in the standard way: see RFC2518 12.2 . RFC4918 14.3 
4ee11fa7 (David A. Velasco  2012-10-30 11:24:09 +0100  65)             prop = propSet.get(DavPropertyName.RESOURCETYPE);
4ee11fa7 (David A. Velasco  2012-10-30 11:24:09 +0100  66)             if (prop!= null) {
4ee11fa7 (David A. Velasco  2012-10-30 11:24:09 +0100  67)                 Object value = prop.getValue();
4ee11fa7 (David A. Velasco  2012-10-30 11:24:09 +0100  68)                 if (value != null) {
4ee11fa7 (David A. Velasco  2012-10-30 11:24:09 +0100  69)                     mContentType = "DIR";   // a specific attribute would be better, but this is enough; unless while we have no reason to distinguish MIME types for folders
4ee11fa7 (David A. Velasco  2012-10-30 11:24:09 +0100  70)                 }
435b31ba (Lennart Rosam     2012-05-16 09:48:34 +0200  71)             }
435b31ba (Lennart Rosam     2012-05-16 09:48:34 +0200  72) 
435b31ba (Lennart Rosam     2012-05-16 09:48:34 +0200  73)             prop = propSet.get(DavPropertyName.GETCONTENTLENGTH);
435b31ba (Lennart Rosam     2012-05-16 09:48:34 +0200  74)             if (prop != null)
435b31ba (Lennart Rosam     2012-05-16 09:48:34 +0200  75)                 mContentLength = Long.parseLong((String) prop.getValue());
435b31ba (Lennart Rosam     2012-05-16 09:48:34 +0200  76) 
435b31ba (Lennart Rosam     2012-05-16 09:48:34 +0200  77)             prop = propSet.get(DavPropertyName.GETLASTMODIFIED);
435b31ba (Lennart Rosam     2012-05-16 09:48:34 +0200  78)             if (prop != null) {
435b31ba (Lennart Rosam     2012-05-16 09:48:34 +0200  79)                 Date d = WebdavUtils
435b31ba (Lennart Rosam     2012-05-16 09:48:34 +0200  80)                         .parseResponseDate((String) prop.getValue());
435b31ba (Lennart Rosam     2012-05-16 09:48:34 +0200  81)                 mModifiedTimestamp = (d != null) ? d.getTime() : 0;
435b31ba (Lennart Rosam     2012-05-16 09:48:34 +0200  82)             }
435b31ba (Lennart Rosam     2012-05-16 09:48:34 +0200  83) 
435b31ba (Lennart Rosam     2012-05-16 09:48:34 +0200  84)             prop = propSet.get(DavPropertyName.CREATIONDATE);
435b31ba (Lennart Rosam     2012-05-16 09:48:34 +0200  85)             if (prop != null) {
435b31ba (Lennart Rosam     2012-05-16 09:48:34 +0200  86)                 Date d = WebdavUtils
435b31ba (Lennart Rosam     2012-05-16 09:48:34 +0200  87)                         .parseResponseDate((String) prop.getValue());
435b31ba (Lennart Rosam     2012-05-16 09:48:34 +0200  88)                 mCreateTimestamp = (d != null) ? d.getTime() : 0;
435b31ba (Lennart Rosam     2012-05-16 09:48:34 +0200  89)             }
435b31ba (Lennart Rosam     2012-05-16 09:48:34 +0200  90) 
435b31ba (Lennart Rosam     2012-05-16 09:48:34 +0200  91)         } else {
8e36e7cc (zerginator        2013-03-12 07:56:27 +0100  92)             Log_OC.e("WebdavEntry",
435b31ba (Lennart Rosam     2012-05-16 09:48:34 +0200  93)                     "General fuckup, no status for webdav response");
435b31ba (Lennart Rosam     2012-05-16 09:48:34 +0200  94)         }
435b31ba (Lennart Rosam     2012-05-16 09:48:34 +0200  95)     }
435b31ba (Lennart Rosam     2012-05-16 09:48:34 +0200  96) 
435b31ba (Lennart Rosam     2012-05-16 09:48:34 +0200  97)     public String path() {
435b31ba (Lennart Rosam     2012-05-16 09:48:34 +0200  98)         return mPath;
435b31ba (Lennart Rosam     2012-05-16 09:48:34 +0200  99)     }
c6b553f6 (David A. Velasco  2012-07-17 12:59:15 +0200 100)     
c6b553f6 (David A. Velasco  2012-07-17 12:59:15 +0200 101)     public String decodedPath() {
c6b553f6 (David A. Velasco  2012-07-17 12:59:15 +0200 102)         return Uri.decode(mPath);
c6b553f6 (David A. Velasco  2012-07-17 12:59:15 +0200 103)     }
435b31ba (Lennart Rosam     2012-05-16 09:48:34 +0200 104) 
435b31ba (Lennart Rosam     2012-05-16 09:48:34 +0200 105)     public String name() {
435b31ba (Lennart Rosam     2012-05-16 09:48:34 +0200 106)         return mName;
435b31ba (Lennart Rosam     2012-05-16 09:48:34 +0200 107)     }
435b31ba (Lennart Rosam     2012-05-16 09:48:34 +0200 108) 
435b31ba (Lennart Rosam     2012-05-16 09:48:34 +0200 109)     public boolean isDirectory() {
435b31ba (Lennart Rosam     2012-05-16 09:48:34 +0200 110)         return mContentType.equals("DIR");
435b31ba (Lennart Rosam     2012-05-16 09:48:34 +0200 111)     }
435b31ba (Lennart Rosam     2012-05-16 09:48:34 +0200 112) 
435b31ba (Lennart Rosam     2012-05-16 09:48:34 +0200 113)     public String contentType() {
435b31ba (Lennart Rosam     2012-05-16 09:48:34 +0200 114)         return mContentType;
435b31ba (Lennart Rosam     2012-05-16 09:48:34 +0200 115)     }
435b31ba (Lennart Rosam     2012-05-16 09:48:34 +0200 116) 
435b31ba (Lennart Rosam     2012-05-16 09:48:34 +0200 117)     public String uri() {
435b31ba (Lennart Rosam     2012-05-16 09:48:34 +0200 118)         return mUri;
435b31ba (Lennart Rosam     2012-05-16 09:48:34 +0200 119)     }
435b31ba (Lennart Rosam     2012-05-16 09:48:34 +0200 120) 
435b31ba (Lennart Rosam     2012-05-16 09:48:34 +0200 121)     public long contentLength() {
435b31ba (Lennart Rosam     2012-05-16 09:48:34 +0200 122)         return mContentLength;
435b31ba (Lennart Rosam     2012-05-16 09:48:34 +0200 123)     }
435b31ba (Lennart Rosam     2012-05-16 09:48:34 +0200 124) 
435b31ba (Lennart Rosam     2012-05-16 09:48:34 +0200 125)     public long createTimestamp() {
435b31ba (Lennart Rosam     2012-05-16 09:48:34 +0200 126)         return mCreateTimestamp;
435b31ba (Lennart Rosam     2012-05-16 09:48:34 +0200 127)     }
435b31ba (Lennart Rosam     2012-05-16 09:48:34 +0200 128) 
abd5f515 (David A. Velasco  2012-12-05 16:14:01 +0100 129)     public long modifiedTimestamp() {
435b31ba (Lennart Rosam     2012-05-16 09:48:34 +0200 130)         return mModifiedTimestamp;
435b31ba (Lennart Rosam     2012-05-16 09:48:34 +0200 131)     }
435b31ba (Lennart Rosam     2012-05-16 09:48:34 +0200 132) 
435b31ba (Lennart Rosam     2012-05-16 09:48:34 +0200 133)     private void resetData() {
435b31ba (Lennart Rosam     2012-05-16 09:48:34 +0200 134)         mName = mUri = mContentType = null;
435b31ba (Lennart Rosam     2012-05-16 09:48:34 +0200 135)         mContentLength = mCreateTimestamp = mModifiedTimestamp = 0;
87814cec (Bartek Przybylski 2012-04-29 00:14:01 +0200 136)     }
87814cec (Bartek Przybylski 2012-04-29 00:14:01 +0200 137) }
