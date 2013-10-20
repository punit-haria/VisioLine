^154bb85 (Bartek Przybylski 2011-08-19 22:37:35 +0200  1) /* ownCloud Android client application
0aeb4258 (Bartek Przybylski 2012-05-13 16:13:13 +0200  2)  *   Copyright (C) 2012  Bartek Przybylski
bb257ec7 (David A. Velasco  2013-02-07 18:45:10 +0100  3)  *   Copyright (C) 2012-2013 ownCloud Inc.
^154bb85 (Bartek Przybylski 2011-08-19 22:37:35 +0200  4)  *
^154bb85 (Bartek Przybylski 2011-08-19 22:37:35 +0200  5)  *   This program is free software: you can redistribute it and/or modify
bdc0332c (David A. Velasco  2013-04-17 12:26:13 +0200  6)  *   it under the terms of the GNU General Public License version 2,
bdc0332c (David A. Velasco  2013-04-17 12:26:13 +0200  7)  *   as published by the Free Software Foundation.
^154bb85 (Bartek Przybylski 2011-08-19 22:37:35 +0200  8)  *
^154bb85 (Bartek Przybylski 2011-08-19 22:37:35 +0200  9)  *   This program is distributed in the hope that it will be useful,
^154bb85 (Bartek Przybylski 2011-08-19 22:37:35 +0200 10)  *   but WITHOUT ANY WARRANTY; without even the implied warranty of
^154bb85 (Bartek Przybylski 2011-08-19 22:37:35 +0200 11)  *   MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
^154bb85 (Bartek Przybylski 2011-08-19 22:37:35 +0200 12)  *   GNU General Public License for more details.
^154bb85 (Bartek Przybylski 2011-08-19 22:37:35 +0200 13)  *
^154bb85 (Bartek Przybylski 2011-08-19 22:37:35 +0200 14)  *   You should have received a copy of the GNU General Public License
^154bb85 (Bartek Przybylski 2011-08-19 22:37:35 +0200 15)  *   along with this program.  If not, see <http://www.gnu.org/licenses/>.
^154bb85 (Bartek Przybylski 2011-08-19 22:37:35 +0200 16)  *
^154bb85 (Bartek Przybylski 2011-08-19 22:37:35 +0200 17)  */
^154bb85 (Bartek Przybylski 2011-08-19 22:37:35 +0200 18) 
^154bb85 (Bartek Przybylski 2011-08-19 22:37:35 +0200 19) package eu.alefzero.webdav;
^154bb85 (Bartek Przybylski 2011-08-19 22:37:35 +0200 20) 
^154bb85 (Bartek Przybylski 2011-08-19 22:37:35 +0200 21) import java.text.ParseException;
^154bb85 (Bartek Przybylski 2011-08-19 22:37:35 +0200 22) import java.text.SimpleDateFormat;
^154bb85 (Bartek Przybylski 2011-08-19 22:37:35 +0200 23) import java.util.Date;
^154bb85 (Bartek Przybylski 2011-08-19 22:37:35 +0200 24) import java.util.Locale;
^154bb85 (Bartek Przybylski 2011-08-19 22:37:35 +0200 25) 
c6b553f6 (David A. Velasco  2012-07-17 12:59:15 +0200 26) import android.net.Uri;
c6b553f6 (David A. Velasco  2012-07-17 12:59:15 +0200 27) 
^154bb85 (Bartek Przybylski 2011-08-19 22:37:35 +0200 28) public class WebdavUtils {
435b31ba (Lennart Rosam     2012-05-16 09:48:34 +0200 29)     public static final SimpleDateFormat DISPLAY_DATE_FORMAT = new SimpleDateFormat(
435b31ba (Lennart Rosam     2012-05-16 09:48:34 +0200 30)             "dd.MM.yyyy hh:mm");
435b31ba (Lennart Rosam     2012-05-16 09:48:34 +0200 31)     private static final SimpleDateFormat DATETIME_FORMATS[] = {
435b31ba (Lennart Rosam     2012-05-16 09:48:34 +0200 32)             new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'", Locale.US),
435b31ba (Lennart Rosam     2012-05-16 09:48:34 +0200 33)             new SimpleDateFormat("EEE, dd MMM yyyy HH:mm:ss zzz", Locale.US),
435b31ba (Lennart Rosam     2012-05-16 09:48:34 +0200 34)             new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.sss'Z'", Locale.US),
435b31ba (Lennart Rosam     2012-05-16 09:48:34 +0200 35)             new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssZ", Locale.US),
435b31ba (Lennart Rosam     2012-05-16 09:48:34 +0200 36)             new SimpleDateFormat("EEE MMM dd HH:mm:ss zzz yyyy", Locale.US),
435b31ba (Lennart Rosam     2012-05-16 09:48:34 +0200 37)             new SimpleDateFormat("EEEEEE, dd-MMM-yy HH:mm:ss zzz", Locale.US),
435b31ba (Lennart Rosam     2012-05-16 09:48:34 +0200 38)             new SimpleDateFormat("EEE MMMM d HH:mm:ss yyyy", Locale.US) };
435b31ba (Lennart Rosam     2012-05-16 09:48:34 +0200 39) 
435b31ba (Lennart Rosam     2012-05-16 09:48:34 +0200 40)     public static String prepareXmlForPropFind() {
435b31ba (Lennart Rosam     2012-05-16 09:48:34 +0200 41)         String ret = "<?xml version=\"1.0\" ?><D:propfind xmlns:D=\"DAV:\"><D:allprop/></D:propfind>";
435b31ba (Lennart Rosam     2012-05-16 09:48:34 +0200 42)         return ret;
435b31ba (Lennart Rosam     2012-05-16 09:48:34 +0200 43)     }
435b31ba (Lennart Rosam     2012-05-16 09:48:34 +0200 44) 
435b31ba (Lennart Rosam     2012-05-16 09:48:34 +0200 45)     public static String prepareXmlForPatch() {
435b31ba (Lennart Rosam     2012-05-16 09:48:34 +0200 46)         return "<?xml version=\"1.0\" ?><D:propertyupdate xmlns:D=\"DAV:\"></D:propertyupdate>";
435b31ba (Lennart Rosam     2012-05-16 09:48:34 +0200 47)     }
435b31ba (Lennart Rosam     2012-05-16 09:48:34 +0200 48) 
435b31ba (Lennart Rosam     2012-05-16 09:48:34 +0200 49)     public static Date parseResponseDate(String date) {
435b31ba (Lennart Rosam     2012-05-16 09:48:34 +0200 50)         Date returnDate = null;
435b31ba (Lennart Rosam     2012-05-16 09:48:34 +0200 51)         for (int i = 0; i < DATETIME_FORMATS.length; ++i) {
435b31ba (Lennart Rosam     2012-05-16 09:48:34 +0200 52)             try {
435b31ba (Lennart Rosam     2012-05-16 09:48:34 +0200 53)                 returnDate = DATETIME_FORMATS[i].parse(date);
435b31ba (Lennart Rosam     2012-05-16 09:48:34 +0200 54)                 return returnDate;
435b31ba (Lennart Rosam     2012-05-16 09:48:34 +0200 55)             } catch (ParseException e) {
435b31ba (Lennart Rosam     2012-05-16 09:48:34 +0200 56)             }
435b31ba (Lennart Rosam     2012-05-16 09:48:34 +0200 57)         }
435b31ba (Lennart Rosam     2012-05-16 09:48:34 +0200 58)         return null;
^154bb85 (Bartek Przybylski 2011-08-19 22:37:35 +0200 59)     }
c6b553f6 (David A. Velasco  2012-07-17 12:59:15 +0200 60) 
c6b553f6 (David A. Velasco  2012-07-17 12:59:15 +0200 61)     /**
c6b553f6 (David A. Velasco  2012-07-17 12:59:15 +0200 62)      * Encodes a path according to URI RFC 2396. 
c6b553f6 (David A. Velasco  2012-07-17 12:59:15 +0200 63)      * 
c6b553f6 (David A. Velasco  2012-07-17 12:59:15 +0200 64)      * If the received path doesn't start with "/", the method adds it.
c6b553f6 (David A. Velasco  2012-07-17 12:59:15 +0200 65)      * 
c6b553f6 (David A. Velasco  2012-07-17 12:59:15 +0200 66)      * @param remoteFilePath    Path
c6b553f6 (David A. Velasco  2012-07-17 12:59:15 +0200 67)      * @return                  Encoded path according to RFC 2396, always starting with "/"
c6b553f6 (David A. Velasco  2012-07-17 12:59:15 +0200 68)      */
45f89711 (David A. Velasco  2012-07-17 14:26:18 +0200 69)     public static String encodePath(String remoteFilePath) {
c6b553f6 (David A. Velasco  2012-07-17 12:59:15 +0200 70)         String encodedPath = Uri.encode(remoteFilePath, "/");
c6b553f6 (David A. Velasco  2012-07-17 12:59:15 +0200 71)         if (!encodedPath.startsWith("/"))
c6b553f6 (David A. Velasco  2012-07-17 12:59:15 +0200 72)             encodedPath = "/" + encodedPath;
c6b553f6 (David A. Velasco  2012-07-17 12:59:15 +0200 73)         return encodedPath;
c6b553f6 (David A. Velasco  2012-07-17 12:59:15 +0200 74)     }
cffdf90b (David A. Velasco  2012-07-18 14:20:09 +0200 75)     
^154bb85 (Bartek Przybylski 2011-08-19 22:37:35 +0200 76) }
