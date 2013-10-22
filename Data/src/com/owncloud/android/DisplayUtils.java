00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700   1) /* ownCloud Android client application
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700   2)  *   Copyright (C) 2011  Bartek Przybylski
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700   3)  *   Copyright (C) 2012-2013 ownCloud Inc.
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700   4)  *
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700   5)  *   This program is free software: you can redistribute it and/or modify
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700   6)  *   it under the terms of the GNU General Public License version 2,
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700   7)  *   as published by the Free Software Foundation.
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700   8)  *
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700   9)  *   This program is distributed in the hope that it will be useful,
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700  10)  *   but WITHOUT ANY WARRANTY; without even the implied warranty of
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700  11)  *   MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700  12)  *   GNU General Public License for more details.
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700  13)  *
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700  14)  *   You should have received a copy of the GNU General Public License
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700  15)  *   along with this program.  If not, see <http://www.gnu.org/licenses/>.
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700  16)  *
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700  17)  */
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700  18) 
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700  19) package com.owncloud.android;
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700  20) 
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700  21) import java.util.Arrays;
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700  22) import java.util.Date;
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700  23) import java.util.HashMap;
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700  24) import java.util.HashSet;
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700  25) import java.util.Set;
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700  26) 
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700  27) /**
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700  28)  * A helper class for some string operations.
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700  29)  * 
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700  30)  * @author Bartek Przybylski
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700  31)  * @author David A. Velasco
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700  32)  */
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700  33) public class DisplayUtils {
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700  34)     
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700  35)     private static String TAG = DisplayUtils.class.getSimpleName(); 
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700  36)     
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700  37)     private static final String[] sizeSuffixes = { "B", "KB", "MB", "GB", "TB", "PB", "EB", "ZB", "YB" };
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700  38) 
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700  39)     private static HashMap<String, String> mimeType2HUmanReadable;
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700  40)     static {
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700  41)         mimeType2HUmanReadable = new HashMap<String, String>();
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700  42)         // images
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700  43)         mimeType2HUmanReadable.put("image/jpeg", "JPEG image");
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700  44)         mimeType2HUmanReadable.put("image/jpg", "JPEG image");
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700  45)         mimeType2HUmanReadable.put("image/png", "PNG image");
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700  46)         mimeType2HUmanReadable.put("image/bmp", "Bitmap image");
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700  47)         mimeType2HUmanReadable.put("image/gif", "GIF image");
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700  48)         mimeType2HUmanReadable.put("image/svg+xml", "JPEG image");
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700  49)         mimeType2HUmanReadable.put("image/tiff", "TIFF image");
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700  50)         // music
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700  51)         mimeType2HUmanReadable.put("audio/mpeg", "MP3 music file");
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700  52)         mimeType2HUmanReadable.put("application/ogg", "OGG music file");
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700  53) 
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700  54)     }
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700  55) 
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700  56)     private static final String TYPE_APPLICATION = "application";
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700  57)     private static final String TYPE_AUDIO = "audio";
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700  58)     private static final String TYPE_IMAGE = "image";
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700  59)     private static final String TYPE_TXT = "text";
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700  60)     private static final String TYPE_VIDEO = "video";
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700  61)     
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700  62)     private static final String SUBTYPE_PDF = "pdf";
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700  63)     private static final String[] SUBTYPES_DOCUMENT = { "msword", "mspowerpoint", "msexcel", 
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700  64)                                                         "vnd.oasis.opendocument.presentation",
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700  65)                                                         "vnd.oasis.opendocument.spreadsheet",
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700  66)                                                         "vnd.oasis.opendocument.text"
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700  67)                                                         };
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700  68)     private static Set<String> SUBTYPES_DOCUMENT_SET = new HashSet<String>(Arrays.asList(SUBTYPES_DOCUMENT));
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700  69)     private static final String[] SUBTYPES_COMPRESSED = {"x-tar", "x-gzip", "zip"};
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700  70)     private static final Set<String> SUBTYPES_COMPRESSED_SET = new HashSet<String>(Arrays.asList(SUBTYPES_COMPRESSED));
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700  71)     
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700  72)     /**
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700  73)      * Converts the file size in bytes to human readable output.
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700  74)      * 
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700  75)      * @param bytes Input file size
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700  76)      * @return Like something readable like "12 MB"
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700  77)      */
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700  78)     public static String bytesToHumanReadable(long bytes) {
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700  79)         double result = bytes;
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700  80)         int attachedsuff = 0;
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700  81)         while (result > 1024 && attachedsuff < sizeSuffixes.length) {
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700  82)             result /= 1024.;
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700  83)             attachedsuff++;
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700  84)         }
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700  85)         result = ((int) (result * 100)) / 100.;
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700  86)         return result + " " + sizeSuffixes[attachedsuff];
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700  87)     }
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700  88) 
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700  89)     /**
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700  90)      * Removes special HTML entities from a string
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700  91)      * 
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700  92)      * @param s Input string
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700  93)      * @return A cleaned version of the string
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700  94)      */
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700  95)     public static String HtmlDecode(String s) {
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700  96)         /*
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700  97)          * TODO: Perhaps we should use something more proven like:
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700  98)          * http://commons.apache.org/lang/api-2.6/org/apache/commons/lang/StringEscapeUtils.html#unescapeHtml%28java.lang.String%29
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700  99)          */
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700 100) 
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700 101)         String ret = "";
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700 102)         for (int i = 0; i < s.length(); ++i) {
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700 103)             if (s.charAt(i) == '%') {
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700 104)                 ret += (char) Integer.parseInt(s.substring(i + 1, i + 3), 16);
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700 105)                 i += 2;
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700 106)             } else {
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700 107)                 ret += s.charAt(i);
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700 108)             }
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700 109)         }
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700 110)         return ret;
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700 111)     }
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700 112) 
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700 113)     /**
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700 114)      * Converts MIME types like "image/jpg" to more end user friendly output
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700 115)      * like "JPG image".
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700 116)      * 
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700 117)      * @param mimetype MIME type to convert
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700 118)      * @return A human friendly version of the MIME type
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700 119)      */
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700 120)     public static String convertMIMEtoPrettyPrint(String mimetype) {
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700 121)         if (mimeType2HUmanReadable.containsKey(mimetype)) {
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700 122)             return mimeType2HUmanReadable.get(mimetype);
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700 123)         }
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700 124)         if (mimetype.split("/").length >= 2)
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700 125)             return mimetype.split("/")[1].toUpperCase() + " file";
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700 126)         return "Unknown type";
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700 127)     }
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700 128)     
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700 129)     
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700 130)     /**
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700 131)      * Returns the resource identifier of an image resource to use as icon associated to a 
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700 132)      * known MIME type.
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700 133)      * 
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700 134)      * @param mimetype      MIME type string.
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700 135)      * @return              Resource identifier of an image resource.
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700 136)      */
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700 137)     public static int getResourceId(String mimetype) {
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700 138) 
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700 139)         if (mimetype == null || "DIR".equals(mimetype)) {
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700 140)             return R.drawable.ic_menu_archive;
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700 141)             
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700 142)         } else {
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700 143)             String [] parts = mimetype.split("/");
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700 144)             String type = parts[0];
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700 145)             String subtype = (parts.length > 1) ? parts[1] : "";
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700 146)             
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700 147)             if(TYPE_TXT.equals(type)) {
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700 148)                 return R.drawable.file_doc;
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700 149)     
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700 150)             } else if(TYPE_IMAGE.equals(type)) {
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700 151)                 return R.drawable.file_image;
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700 152)                 
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700 153)             } else if(TYPE_VIDEO.equals(type)) {
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700 154)                 return R.drawable.file_movie;
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700 155)                 
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700 156)             } else if(TYPE_AUDIO.equals(type)) {  
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700 157)                 return R.drawable.file_sound;
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700 158)                 
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700 159)             } else if(TYPE_APPLICATION.equals(type)) {
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700 160)                 
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700 161)                 if (SUBTYPE_PDF.equals(subtype)) {
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700 162)                     return R.drawable.file_pdf;
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700 163)                     
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700 164)                 } else if (SUBTYPES_DOCUMENT_SET.contains(subtype)) {
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700 165)                     return R.drawable.file_doc;
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700 166) 
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700 167)                 } else if (SUBTYPES_COMPRESSED_SET.contains(subtype)) {
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700 168)                     return R.drawable.file_zip;
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700 169)                 }
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700 170)     
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700 171)             }
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700 172)             // problems: RAR, RTF, 3GP are send as application/octet-stream from the server ; extension in the filename should be explicitly reviewed
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700 173)         }
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700 174) 
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700 175)         // default icon
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700 176)         return R.drawable.file;
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700 177)     }
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700 178) 
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700 179)     
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700 180) 
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700 181)     /**
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700 182)      * Converts Unix time to human readable format
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700 183)      * @param miliseconds that have passed since 01/01/1970
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700 184)      * @return The human readable time for the users locale
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700 185)      */
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700 186)     public static String unixTimeToHumanReadable(long milliseconds) {
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700 187)         Date date = new Date(milliseconds);
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700 188)         return date.toLocaleString();
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700 189)     }
00000000 (Not Committed Yet 2013-10-18 00:25:10 -0700 190) }
