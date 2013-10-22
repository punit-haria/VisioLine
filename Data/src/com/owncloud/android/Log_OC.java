f256bdee (zerginator 2013-03-12 08:08:04 +0100   1) package com.owncloud.android;
f256bdee (zerginator 2013-03-12 08:08:04 +0100   2) 
f256bdee (zerginator 2013-03-12 08:08:04 +0100   3) import java.io.BufferedWriter;
f256bdee (zerginator 2013-03-12 08:08:04 +0100   4) import java.io.File;
f256bdee (zerginator 2013-03-12 08:08:04 +0100   5) import java.io.FileWriter;
f256bdee (zerginator 2013-03-12 08:08:04 +0100   6) import java.io.IOException;
f256bdee (zerginator 2013-03-12 08:08:04 +0100   7) import java.text.SimpleDateFormat;
f256bdee (zerginator 2013-03-12 08:08:04 +0100   8) import java.util.Date;
f256bdee (zerginator 2013-03-12 08:08:04 +0100   9) import java.util.Locale;
f256bdee (zerginator 2013-03-12 08:08:04 +0100  10) 
f256bdee (zerginator 2013-03-12 08:08:04 +0100  11) import android.util.Log;
f256bdee (zerginator 2013-03-12 08:08:04 +0100  12) 
f256bdee (zerginator 2013-03-12 08:08:04 +0100  13) 
5a22c7e7 (zerginator 2013-03-31 22:43:46 +0200  14) 
f256bdee (zerginator 2013-03-12 08:08:04 +0100  15) public class Log_OC {
f256bdee (zerginator 2013-03-12 08:08:04 +0100  16)     
5a22c7e7 (zerginator 2013-03-31 22:43:46 +0200  17) 
f256bdee (zerginator 2013-03-12 08:08:04 +0100  18)     private static boolean isEnabled = false;
f256bdee (zerginator 2013-03-12 08:08:04 +0100  19)     private static File logFile;
f256bdee (zerginator 2013-03-12 08:08:04 +0100  20)     private static File folder;
f256bdee (zerginator 2013-03-12 08:08:04 +0100  21)     private static BufferedWriter buf;  
f256bdee (zerginator 2013-03-12 08:08:04 +0100  22)     
f256bdee (zerginator 2013-03-12 08:08:04 +0100  23)     public static void i(String TAG, String message){
f256bdee (zerginator 2013-03-12 08:08:04 +0100  24)         // Printing the message to LogCat console
f256bdee (zerginator 2013-03-12 08:08:04 +0100  25)         Log.i(TAG, message);
f256bdee (zerginator 2013-03-12 08:08:04 +0100  26)         // Write the log message to the file
f256bdee (zerginator 2013-03-12 08:08:04 +0100  27)         appendLog(TAG+" : "+message);
f256bdee (zerginator 2013-03-12 08:08:04 +0100  28)     }
f256bdee (zerginator 2013-03-12 08:08:04 +0100  29) 
f256bdee (zerginator 2013-03-12 08:08:04 +0100  30)     public static void d(String TAG, String message){
f256bdee (zerginator 2013-03-12 08:08:04 +0100  31)         Log.d(TAG, message);
f256bdee (zerginator 2013-03-12 08:08:04 +0100  32)         appendLog(TAG+" : "+message);
f256bdee (zerginator 2013-03-12 08:08:04 +0100  33)     }
f256bdee (zerginator 2013-03-12 08:08:04 +0100  34)     public static void d(String TAG, String message, Exception e) {
f256bdee (zerginator 2013-03-12 08:08:04 +0100  35)         Log.d(TAG, message, e);
f256bdee (zerginator 2013-03-12 08:08:04 +0100  36)         appendLog(TAG+" : "+ message+" Exception : "+e.getStackTrace());
f256bdee (zerginator 2013-03-12 08:08:04 +0100  37)     }
f256bdee (zerginator 2013-03-12 08:08:04 +0100  38)     public static void e(String TAG, String message){
f256bdee (zerginator 2013-03-12 08:08:04 +0100  39)         Log.e(TAG, message);
f256bdee (zerginator 2013-03-12 08:08:04 +0100  40)         appendLog(TAG+" : "+message);
f256bdee (zerginator 2013-03-12 08:08:04 +0100  41)     }
f256bdee (zerginator 2013-03-12 08:08:04 +0100  42)     
f256bdee (zerginator 2013-03-12 08:08:04 +0100  43)     public static void e(String TAG, String message, Throwable e) {
f256bdee (zerginator 2013-03-12 08:08:04 +0100  44)         Log.e(TAG, message, e);
f256bdee (zerginator 2013-03-12 08:08:04 +0100  45)         appendLog(TAG+" : "+ message+" Exception : "+e.getStackTrace());
f256bdee (zerginator 2013-03-12 08:08:04 +0100  46)     }
f256bdee (zerginator 2013-03-12 08:08:04 +0100  47)     
f256bdee (zerginator 2013-03-12 08:08:04 +0100  48)     public static void v(String TAG, String message){
f256bdee (zerginator 2013-03-12 08:08:04 +0100  49)         Log.v(TAG, message);
f256bdee (zerginator 2013-03-12 08:08:04 +0100  50)         appendLog(TAG+" : "+message);
f256bdee (zerginator 2013-03-12 08:08:04 +0100  51)     }
f256bdee (zerginator 2013-03-12 08:08:04 +0100  52)     
f256bdee (zerginator 2013-03-12 08:08:04 +0100  53)     public static void w(String TAG, String message) {
f256bdee (zerginator 2013-03-12 08:08:04 +0100  54)         Log.w(TAG,message); 
f256bdee (zerginator 2013-03-12 08:08:04 +0100  55)         appendLog(TAG+" : "+message);
f256bdee (zerginator 2013-03-12 08:08:04 +0100  56)     }
f256bdee (zerginator 2013-03-12 08:08:04 +0100  57)     
f256bdee (zerginator 2013-03-12 08:08:04 +0100  58)     public static void wtf(String TAG, String message) {
f256bdee (zerginator 2013-03-12 08:08:04 +0100  59)         Log.wtf(TAG,message); 
f256bdee (zerginator 2013-03-12 08:08:04 +0100  60)         appendLog(TAG+" : "+message);
f256bdee (zerginator 2013-03-12 08:08:04 +0100  61)     }
f256bdee (zerginator 2013-03-12 08:08:04 +0100  62)     
f256bdee (zerginator 2013-03-12 08:08:04 +0100  63)     public static void startLogging(String logPath) {
5a22c7e7 (zerginator 2013-03-31 22:43:46 +0200  64)         folder = new File(logPath);
f256bdee (zerginator 2013-03-12 08:08:04 +0100  65)         logFile = new File(folder+File.separator+"log.txt");
f256bdee (zerginator 2013-03-12 08:08:04 +0100  66)         
f256bdee (zerginator 2013-03-12 08:08:04 +0100  67)         if (!folder.exists()) {
f256bdee (zerginator 2013-03-12 08:08:04 +0100  68)             folder.mkdirs();
f256bdee (zerginator 2013-03-12 08:08:04 +0100  69)         }
f256bdee (zerginator 2013-03-12 08:08:04 +0100  70)         if (logFile.exists()) {
f256bdee (zerginator 2013-03-12 08:08:04 +0100  71)             logFile.delete();
f256bdee (zerginator 2013-03-12 08:08:04 +0100  72)         }
f256bdee (zerginator 2013-03-12 08:08:04 +0100  73)         try { 
f256bdee (zerginator 2013-03-12 08:08:04 +0100  74)             logFile.createNewFile();
f256bdee (zerginator 2013-03-12 08:08:04 +0100  75)             buf = new BufferedWriter(new FileWriter(logFile, true));
f256bdee (zerginator 2013-03-12 08:08:04 +0100  76)             isEnabled = true;
f256bdee (zerginator 2013-03-12 08:08:04 +0100  77)             appendPhoneInfo();
f256bdee (zerginator 2013-03-12 08:08:04 +0100  78)         }catch (IOException e){ 
f256bdee (zerginator 2013-03-12 08:08:04 +0100  79)             e.printStackTrace(); 
f256bdee (zerginator 2013-03-12 08:08:04 +0100  80)         } 
f256bdee (zerginator 2013-03-12 08:08:04 +0100  81)     }
f256bdee (zerginator 2013-03-12 08:08:04 +0100  82)     
f256bdee (zerginator 2013-03-12 08:08:04 +0100  83)     public static void stopLogging() {
f256bdee (zerginator 2013-03-12 08:08:04 +0100  84)         SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd_HHmmss",Locale.getDefault());
f256bdee (zerginator 2013-03-12 08:08:04 +0100  85)         String currentDateandTime = sdf.format(new Date());
f256bdee (zerginator 2013-03-12 08:08:04 +0100  86)         if (logFile != null) {
5a22c7e7 (zerginator 2013-03-31 22:43:46 +0200  87)             logFile.renameTo(new File(folder+File.separator+"Owncloud_"+currentDateandTime+".log"));
5a22c7e7 (zerginator 2013-03-31 22:43:46 +0200  88)           
f256bdee (zerginator 2013-03-12 08:08:04 +0100  89)             isEnabled = false;
f256bdee (zerginator 2013-03-12 08:08:04 +0100  90)             try {
f256bdee (zerginator 2013-03-12 08:08:04 +0100  91)                 buf.close();
f256bdee (zerginator 2013-03-12 08:08:04 +0100  92)             } catch (IOException e) {
f256bdee (zerginator 2013-03-12 08:08:04 +0100  93)                 e.printStackTrace();
f256bdee (zerginator 2013-03-12 08:08:04 +0100  94)             } 
f256bdee (zerginator 2013-03-12 08:08:04 +0100  95)         
f256bdee (zerginator 2013-03-12 08:08:04 +0100  96)         }
f256bdee (zerginator 2013-03-12 08:08:04 +0100  97)         
f256bdee (zerginator 2013-03-12 08:08:04 +0100  98)     }
f256bdee (zerginator 2013-03-12 08:08:04 +0100  99)     
f256bdee (zerginator 2013-03-12 08:08:04 +0100 100)     private static void appendPhoneInfo() {
f256bdee (zerginator 2013-03-12 08:08:04 +0100 101)         appendLog("Model : " + android.os.Build.MODEL);
f256bdee (zerginator 2013-03-12 08:08:04 +0100 102)         appendLog("Brand : " + android.os.Build.BRAND);
f256bdee (zerginator 2013-03-12 08:08:04 +0100 103)         appendLog("Product : " + android.os.Build.PRODUCT);
f256bdee (zerginator 2013-03-12 08:08:04 +0100 104)         appendLog("Device : " + android.os.Build.DEVICE);
f256bdee (zerginator 2013-03-12 08:08:04 +0100 105)         appendLog("Version-Codename : " + android.os.Build.VERSION.CODENAME);
f256bdee (zerginator 2013-03-12 08:08:04 +0100 106)         appendLog("Version-Release : " + android.os.Build.VERSION.RELEASE);
f256bdee (zerginator 2013-03-12 08:08:04 +0100 107)     }
f256bdee (zerginator 2013-03-12 08:08:04 +0100 108)     
f256bdee (zerginator 2013-03-12 08:08:04 +0100 109)     private static void appendLog(String text) { 
f256bdee (zerginator 2013-03-12 08:08:04 +0100 110)         if (isEnabled) {
f256bdee (zerginator 2013-03-12 08:08:04 +0100 111)            try { 
f256bdee (zerginator 2013-03-12 08:08:04 +0100 112)                buf.append(text); 
f256bdee (zerginator 2013-03-12 08:08:04 +0100 113)                buf.newLine(); 
f256bdee (zerginator 2013-03-12 08:08:04 +0100 114)            } catch (IOException e) { 
f256bdee (zerginator 2013-03-12 08:08:04 +0100 115)                e.printStackTrace(); 
5a22c7e7 (zerginator 2013-03-31 22:43:46 +0200 116)         } 
5a22c7e7 (zerginator 2013-03-31 22:43:46 +0200 117)     }
f256bdee (zerginator 2013-03-12 08:08:04 +0100 118) }
f256bdee (zerginator 2013-03-12 08:08:04 +0100 119) 
f256bdee (zerginator 2013-03-12 08:08:04 +0100 120)     
f256bdee (zerginator 2013-03-12 08:08:04 +0100 121)    
f256bdee (zerginator 2013-03-12 08:08:04 +0100 122) 
f256bdee (zerginator 2013-03-12 08:08:04 +0100 123)   
f256bdee (zerginator 2013-03-12 08:08:04 +0100 124) 
f256bdee (zerginator 2013-03-12 08:08:04 +0100 125)    
f256bdee (zerginator 2013-03-12 08:08:04 +0100 126)    
f256bdee (zerginator 2013-03-12 08:08:04 +0100 127) }
