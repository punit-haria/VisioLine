fc2af5b0 (David A. Velasco 2013-02-26 12:33:28 +0100   1) /* ownCloud Android client application
fc2af5b0 (David A. Velasco 2013-02-26 12:33:28 +0100   2)  *   Copyright (C) 2012-2013  ownCloud Inc.
fc2af5b0 (David A. Velasco 2013-02-26 12:33:28 +0100   3)  *
fc2af5b0 (David A. Velasco 2013-02-26 12:33:28 +0100   4)  *   This program is free software: you can redistribute it and/or modify
bdc0332c (David A. Velasco 2013-04-17 12:26:13 +0200   5)  *   it under the terms of the GNU General Public License version 2,
bdc0332c (David A. Velasco 2013-04-17 12:26:13 +0200   6)  *   as published by the Free Software Foundation.
fc2af5b0 (David A. Velasco 2013-02-26 12:33:28 +0100   7)  *
fc2af5b0 (David A. Velasco 2013-02-26 12:33:28 +0100   8)  *   This program is distributed in the hope that it will be useful,
fc2af5b0 (David A. Velasco 2013-02-26 12:33:28 +0100   9)  *   but WITHOUT ANY WARRANTY; without even the implied warranty of
fc2af5b0 (David A. Velasco 2013-02-26 12:33:28 +0100  10)  *   MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
fc2af5b0 (David A. Velasco 2013-02-26 12:33:28 +0100  11)  *   GNU General Public License for more details.
fc2af5b0 (David A. Velasco 2013-02-26 12:33:28 +0100  12)  *
fc2af5b0 (David A. Velasco 2013-02-26 12:33:28 +0100  13)  *   You should have received a copy of the GNU General Public License
fc2af5b0 (David A. Velasco 2013-02-26 12:33:28 +0100  14)  *   along with this program.  If not, see <http://www.gnu.org/licenses/>.
fc2af5b0 (David A. Velasco 2013-02-26 12:33:28 +0100  15)  *
fc2af5b0 (David A. Velasco 2013-02-26 12:33:28 +0100  16)  */
fc2af5b0 (David A. Velasco 2013-02-26 12:33:28 +0100  17) package com.owncloud.android.ui.preview;
fc2af5b0 (David A. Velasco 2013-02-26 12:33:28 +0100  18) 
db7eea71 (David A. Velasco 2013-03-01 14:20:25 +0100  19) import java.util.HashMap;
fc2af5b0 (David A. Velasco 2013-02-26 12:33:28 +0100  20) import java.util.HashSet;
db7eea71 (David A. Velasco 2013-03-01 14:20:25 +0100  21) import java.util.Map;
fc2af5b0 (David A. Velasco 2013-02-26 12:33:28 +0100  22) import java.util.Set;
fc2af5b0 (David A. Velasco 2013-02-26 12:33:28 +0100  23) import java.util.Vector;
fc2af5b0 (David A. Velasco 2013-02-26 12:33:28 +0100  24) 
fc2af5b0 (David A. Velasco 2013-02-26 12:33:28 +0100  25) import android.accounts.Account;
fc2af5b0 (David A. Velasco 2013-02-26 12:33:28 +0100  26) import android.support.v4.app.Fragment;
fc2af5b0 (David A. Velasco 2013-02-26 12:33:28 +0100  27) import android.support.v4.app.FragmentManager;
fc2af5b0 (David A. Velasco 2013-02-26 12:33:28 +0100  28) import android.support.v4.app.FragmentStatePagerAdapter;
fc2af5b0 (David A. Velasco 2013-02-26 12:33:28 +0100  29) import android.view.ViewGroup;
fc2af5b0 (David A. Velasco 2013-02-26 12:33:28 +0100  30) 
fc2af5b0 (David A. Velasco 2013-02-26 12:33:28 +0100  31) import com.owncloud.android.datamodel.DataStorageManager;
fc2af5b0 (David A. Velasco 2013-02-26 12:33:28 +0100  32) import com.owncloud.android.datamodel.OCFile;
86cec34b (David A. Velasco 2013-02-27 14:08:58 +0100  33) import com.owncloud.android.ui.fragment.FileFragment;
fc2af5b0 (David A. Velasco 2013-02-26 12:33:28 +0100  34) 
fc2af5b0 (David A. Velasco 2013-02-26 12:33:28 +0100  35) /**
fc2af5b0 (David A. Velasco 2013-02-26 12:33:28 +0100  36)  * Adapter class that provides Fragment instances  
fc2af5b0 (David A. Velasco 2013-02-26 12:33:28 +0100  37)  * 
fc2af5b0 (David A. Velasco 2013-02-26 12:33:28 +0100  38)  * @author David A. Velasco
fc2af5b0 (David A. Velasco 2013-02-26 12:33:28 +0100  39)  */
31827bce (David A. Velasco 2013-02-28 17:52:15 +0100  40) //public class PreviewImagePagerAdapter extends PagerAdapter {
31827bce (David A. Velasco 2013-02-28 17:52:15 +0100  41) public class PreviewImagePagerAdapter extends FragmentStatePagerAdapter {
fc2af5b0 (David A. Velasco 2013-02-26 12:33:28 +0100  42)     
fc2af5b0 (David A. Velasco 2013-02-26 12:33:28 +0100  43)     private Vector<OCFile> mImageFiles;
fc2af5b0 (David A. Velasco 2013-02-26 12:33:28 +0100  44)     private Account mAccount;
fc2af5b0 (David A. Velasco 2013-02-26 12:33:28 +0100  45)     private Set<Object> mObsoleteFragments;
86cec34b (David A. Velasco 2013-02-27 14:08:58 +0100  46)     private Set<Integer> mObsoletePositions;
db7eea71 (David A. Velasco 2013-03-01 14:20:25 +0100  47)     private Set<Integer> mDownloadErrors;
fc2af5b0 (David A. Velasco 2013-02-26 12:33:28 +0100  48)     private DataStorageManager mStorageManager;
fc2af5b0 (David A. Velasco 2013-02-26 12:33:28 +0100  49)     
db7eea71 (David A. Velasco 2013-03-01 14:20:25 +0100  50)     private Map<Integer, FileFragment> mCachedFragments;
db7eea71 (David A. Velasco 2013-03-01 14:20:25 +0100  51) 
fc2af5b0 (David A. Velasco 2013-02-26 12:33:28 +0100  52)     /**
fc2af5b0 (David A. Velasco 2013-02-26 12:33:28 +0100  53)      * Constructor.
fc2af5b0 (David A. Velasco 2013-02-26 12:33:28 +0100  54)      * 
fc2af5b0 (David A. Velasco 2013-02-26 12:33:28 +0100  55)      * @param fragmentManager   {@link FragmentManager} instance that will handle the {@link Fragment}s provided by the adapter. 
fc2af5b0 (David A. Velasco 2013-02-26 12:33:28 +0100  56)      * @param parentFolder      Folder where images will be searched for.
fc2af5b0 (David A. Velasco 2013-02-26 12:33:28 +0100  57)      * @param storageManager    Bridge to database.
fc2af5b0 (David A. Velasco 2013-02-26 12:33:28 +0100  58)      */
fc2af5b0 (David A. Velasco 2013-02-26 12:33:28 +0100  59)     public PreviewImagePagerAdapter(FragmentManager fragmentManager, OCFile parentFolder, Account account, DataStorageManager storageManager) {
31827bce (David A. Velasco 2013-02-28 17:52:15 +0100  60)         super(fragmentManager);
86cec34b (David A. Velasco 2013-02-27 14:08:58 +0100  61)         
fc2af5b0 (David A. Velasco 2013-02-26 12:33:28 +0100  62)         if (fragmentManager == null) {
fc2af5b0 (David A. Velasco 2013-02-26 12:33:28 +0100  63)             throw new IllegalArgumentException("NULL FragmentManager instance");
fc2af5b0 (David A. Velasco 2013-02-26 12:33:28 +0100  64)         }
fc2af5b0 (David A. Velasco 2013-02-26 12:33:28 +0100  65)         if (parentFolder == null) {
fc2af5b0 (David A. Velasco 2013-02-26 12:33:28 +0100  66)             throw new IllegalArgumentException("NULL parent folder");
fc2af5b0 (David A. Velasco 2013-02-26 12:33:28 +0100  67)         } 
fc2af5b0 (David A. Velasco 2013-02-26 12:33:28 +0100  68)         if (storageManager == null) {
fc2af5b0 (David A. Velasco 2013-02-26 12:33:28 +0100  69)             throw new IllegalArgumentException("NULL storage manager");
fc2af5b0 (David A. Velasco 2013-02-26 12:33:28 +0100  70)         }
fc2af5b0 (David A. Velasco 2013-02-26 12:33:28 +0100  71) 
fc2af5b0 (David A. Velasco 2013-02-26 12:33:28 +0100  72)         mAccount = account;
fc2af5b0 (David A. Velasco 2013-02-26 12:33:28 +0100  73)         mStorageManager = storageManager;
fc2af5b0 (David A. Velasco 2013-02-26 12:33:28 +0100  74)         mImageFiles = mStorageManager.getDirectoryImages(parentFolder); 
fc2af5b0 (David A. Velasco 2013-02-26 12:33:28 +0100  75)         mObsoleteFragments = new HashSet<Object>();
86cec34b (David A. Velasco 2013-02-27 14:08:58 +0100  76)         mObsoletePositions = new HashSet<Integer>();
db7eea71 (David A. Velasco 2013-03-01 14:20:25 +0100  77)         mDownloadErrors = new HashSet<Integer>();
31827bce (David A. Velasco 2013-02-28 17:52:15 +0100  78)         //mFragmentManager = fragmentManager;
db7eea71 (David A. Velasco 2013-03-01 14:20:25 +0100  79)         mCachedFragments = new HashMap<Integer, FileFragment>();
fc2af5b0 (David A. Velasco 2013-02-26 12:33:28 +0100  80)     }
fc2af5b0 (David A. Velasco 2013-02-26 12:33:28 +0100  81) 
fc2af5b0 (David A. Velasco 2013-02-26 12:33:28 +0100  82)     
fc2af5b0 (David A. Velasco 2013-02-26 12:33:28 +0100  83)     /**
fc2af5b0 (David A. Velasco 2013-02-26 12:33:28 +0100  84)      * Returns the image files handled by the adapter.
fc2af5b0 (David A. Velasco 2013-02-26 12:33:28 +0100  85)      * 
fc2af5b0 (David A. Velasco 2013-02-26 12:33:28 +0100  86)      * @return  A vector with the image files handled by the adapter.
fc2af5b0 (David A. Velasco 2013-02-26 12:33:28 +0100  87)      */
fc2af5b0 (David A. Velasco 2013-02-26 12:33:28 +0100  88)     protected OCFile getFileAt(int position) {
fc2af5b0 (David A. Velasco 2013-02-26 12:33:28 +0100  89)         return mImageFiles.get(position);
fc2af5b0 (David A. Velasco 2013-02-26 12:33:28 +0100  90)     }
fc2af5b0 (David A. Velasco 2013-02-26 12:33:28 +0100  91) 
fc2af5b0 (David A. Velasco 2013-02-26 12:33:28 +0100  92)     
fc2af5b0 (David A. Velasco 2013-02-26 12:33:28 +0100  93)     public Fragment getItem(int i) {
fc2af5b0 (David A. Velasco 2013-02-26 12:33:28 +0100  94)         OCFile file = mImageFiles.get(i);
fc2af5b0 (David A. Velasco 2013-02-26 12:33:28 +0100  95)         Fragment fragment = null;
fc2af5b0 (David A. Velasco 2013-02-26 12:33:28 +0100  96)         if (file.isDown()) {
86cec34b (David A. Velasco 2013-02-27 14:08:58 +0100  97)             fragment = new PreviewImageFragment(file, mAccount, mObsoletePositions.contains(Integer.valueOf(i)));
db7eea71 (David A. Velasco 2013-03-01 14:20:25 +0100  98)             
db7eea71 (David A. Velasco 2013-03-01 14:20:25 +0100  99)         } else if (mDownloadErrors.contains(Integer.valueOf(i))) {
db7eea71 (David A. Velasco 2013-03-01 14:20:25 +0100 100)             fragment = new FileDownloadFragment(file, mAccount, true);
db7eea71 (David A. Velasco 2013-03-01 14:20:25 +0100 101)             ((FileDownloadFragment)fragment).setError(true);
db7eea71 (David A. Velasco 2013-03-01 14:20:25 +0100 102)             mDownloadErrors.remove(Integer.valueOf(i));
db7eea71 (David A. Velasco 2013-03-01 14:20:25 +0100 103)             
fc2af5b0 (David A. Velasco 2013-02-26 12:33:28 +0100 104)         } else {
86cec34b (David A. Velasco 2013-02-27 14:08:58 +0100 105)             fragment = new FileDownloadFragment(file, mAccount, mObsoletePositions.contains(Integer.valueOf(i)));
fc2af5b0 (David A. Velasco 2013-02-26 12:33:28 +0100 106)         }
db7eea71 (David A. Velasco 2013-03-01 14:20:25 +0100 107)         mObsoletePositions.remove(Integer.valueOf(i));
fc2af5b0 (David A. Velasco 2013-02-26 12:33:28 +0100 108)         return fragment;
fc2af5b0 (David A. Velasco 2013-02-26 12:33:28 +0100 109)     }
fc2af5b0 (David A. Velasco 2013-02-26 12:33:28 +0100 110) 
def870aa (David A. Velasco 2013-02-26 12:49:15 +0100 111)     public int getFilePosition(OCFile file) {
def870aa (David A. Velasco 2013-02-26 12:49:15 +0100 112)         return mImageFiles.indexOf(file);
def870aa (David A. Velasco 2013-02-26 12:49:15 +0100 113)     }
def870aa (David A. Velasco 2013-02-26 12:49:15 +0100 114)     
fc2af5b0 (David A. Velasco 2013-02-26 12:33:28 +0100 115)     @Override
fc2af5b0 (David A. Velasco 2013-02-26 12:33:28 +0100 116)     public int getCount() {
fc2af5b0 (David A. Velasco 2013-02-26 12:33:28 +0100 117)         return mImageFiles.size();
fc2af5b0 (David A. Velasco 2013-02-26 12:33:28 +0100 118)     }
fc2af5b0 (David A. Velasco 2013-02-26 12:33:28 +0100 119) 
fc2af5b0 (David A. Velasco 2013-02-26 12:33:28 +0100 120)     @Override
fc2af5b0 (David A. Velasco 2013-02-26 12:33:28 +0100 121)     public CharSequence getPageTitle(int position) {
fc2af5b0 (David A. Velasco 2013-02-26 12:33:28 +0100 122)         return mImageFiles.get(position).getFileName();
fc2af5b0 (David A. Velasco 2013-02-26 12:33:28 +0100 123)     }
fc2af5b0 (David A. Velasco 2013-02-26 12:33:28 +0100 124) 
db7eea71 (David A. Velasco 2013-03-01 14:20:25 +0100 125)     
fc2af5b0 (David A. Velasco 2013-02-26 12:33:28 +0100 126)     public void updateFile(int position, OCFile file) {
db7eea71 (David A. Velasco 2013-03-01 14:20:25 +0100 127)         FileFragment fragmentToUpdate = mCachedFragments.get(Integer.valueOf(position));
db7eea71 (David A. Velasco 2013-03-01 14:20:25 +0100 128)         if (fragmentToUpdate != null) {
db7eea71 (David A. Velasco 2013-03-01 14:20:25 +0100 129)             mObsoleteFragments.add(fragmentToUpdate);
db7eea71 (David A. Velasco 2013-03-01 14:20:25 +0100 130)         }
86cec34b (David A. Velasco 2013-02-27 14:08:58 +0100 131)         mObsoletePositions.add(Integer.valueOf(position));
86cec34b (David A. Velasco 2013-02-27 14:08:58 +0100 132)         mImageFiles.set(position, file);
fc2af5b0 (David A. Velasco 2013-02-26 12:33:28 +0100 133)     }
fc2af5b0 (David A. Velasco 2013-02-26 12:33:28 +0100 134)     
db7eea71 (David A. Velasco 2013-03-01 14:20:25 +0100 135)     
db7eea71 (David A. Velasco 2013-03-01 14:20:25 +0100 136)     public void updateWithDownloadError(int position) {
db7eea71 (David A. Velasco 2013-03-01 14:20:25 +0100 137)         FileFragment fragmentToUpdate = mCachedFragments.get(Integer.valueOf(position));
db7eea71 (David A. Velasco 2013-03-01 14:20:25 +0100 138)         if (fragmentToUpdate != null) {
db7eea71 (David A. Velasco 2013-03-01 14:20:25 +0100 139)             mObsoleteFragments.add(fragmentToUpdate);
db7eea71 (David A. Velasco 2013-03-01 14:20:25 +0100 140)         }
db7eea71 (David A. Velasco 2013-03-01 14:20:25 +0100 141)         mDownloadErrors.add(Integer.valueOf(position));
db7eea71 (David A. Velasco 2013-03-01 14:20:25 +0100 142)     }
db7eea71 (David A. Velasco 2013-03-01 14:20:25 +0100 143)     
db7eea71 (David A. Velasco 2013-03-01 14:20:25 +0100 144)     public void clearErrorAt(int position) {
db7eea71 (David A. Velasco 2013-03-01 14:20:25 +0100 145)         FileFragment fragmentToUpdate = mCachedFragments.get(Integer.valueOf(position));
db7eea71 (David A. Velasco 2013-03-01 14:20:25 +0100 146)         if (fragmentToUpdate != null) {
db7eea71 (David A. Velasco 2013-03-01 14:20:25 +0100 147)             mObsoleteFragments.add(fragmentToUpdate);
db7eea71 (David A. Velasco 2013-03-01 14:20:25 +0100 148)         }
db7eea71 (David A. Velasco 2013-03-01 14:20:25 +0100 149)         mDownloadErrors.remove(Integer.valueOf(position));
db7eea71 (David A. Velasco 2013-03-01 14:20:25 +0100 150)     }
db7eea71 (David A. Velasco 2013-03-01 14:20:25 +0100 151)     
db7eea71 (David A. Velasco 2013-03-01 14:20:25 +0100 152)     
fc2af5b0 (David A. Velasco 2013-02-26 12:33:28 +0100 153)     @Override
fc2af5b0 (David A. Velasco 2013-02-26 12:33:28 +0100 154)     public int getItemPosition(Object object) {
fc2af5b0 (David A. Velasco 2013-02-26 12:33:28 +0100 155)         if (mObsoleteFragments.contains(object)) {
def870aa (David A. Velasco 2013-02-26 12:49:15 +0100 156)             mObsoleteFragments.remove(object);
fc2af5b0 (David A. Velasco 2013-02-26 12:33:28 +0100 157)             return POSITION_NONE;
fc2af5b0 (David A. Velasco 2013-02-26 12:33:28 +0100 158)         }
fc2af5b0 (David A. Velasco 2013-02-26 12:33:28 +0100 159)         return super.getItemPosition(object);
fc2af5b0 (David A. Velasco 2013-02-26 12:33:28 +0100 160)     }
def870aa (David A. Velasco 2013-02-26 12:49:15 +0100 161) 
def870aa (David A. Velasco 2013-02-26 12:49:15 +0100 162) 
db7eea71 (David A. Velasco 2013-03-01 14:20:25 +0100 163)     @Override
db7eea71 (David A. Velasco 2013-03-01 14:20:25 +0100 164)     public Object instantiateItem(ViewGroup container, int position) {
db7eea71 (David A. Velasco 2013-03-01 14:20:25 +0100 165)         Object fragment = super.instantiateItem(container, position);
db7eea71 (David A. Velasco 2013-03-01 14:20:25 +0100 166)         mCachedFragments.put(Integer.valueOf(position), (FileFragment)fragment);
db7eea71 (David A. Velasco 2013-03-01 14:20:25 +0100 167)         return fragment;
db7eea71 (David A. Velasco 2013-03-01 14:20:25 +0100 168)     }
db7eea71 (David A. Velasco 2013-03-01 14:20:25 +0100 169)     
db7eea71 (David A. Velasco 2013-03-01 14:20:25 +0100 170)     @Override
db7eea71 (David A. Velasco 2013-03-01 14:20:25 +0100 171)     public void destroyItem(ViewGroup container, int position, Object object) {
db7eea71 (David A. Velasco 2013-03-01 14:20:25 +0100 172)        mCachedFragments.remove(Integer.valueOf(position));
db7eea71 (David A. Velasco 2013-03-01 14:20:25 +0100 173)        super.destroyItem(container, position, object);
db7eea71 (David A. Velasco 2013-03-01 14:20:25 +0100 174)     }
db7eea71 (David A. Velasco 2013-03-01 14:20:25 +0100 175) 
db7eea71 (David A. Velasco 2013-03-01 14:20:25 +0100 176) 
db7eea71 (David A. Velasco 2013-03-01 14:20:25 +0100 177)     public boolean pendingErrorAt(int position) {
db7eea71 (David A. Velasco 2013-03-01 14:20:25 +0100 178)         return mDownloadErrors.contains(Integer.valueOf(position));
86cec34b (David A. Velasco 2013-02-27 14:08:58 +0100 179)     }
86cec34b (David A. Velasco 2013-02-27 14:08:58 +0100 180) 
31827bce (David A. Velasco 2013-02-28 17:52:15 +0100 181)     /* -*
fc2af5b0 (David A. Velasco 2013-02-26 12:33:28 +0100 182)      * Called when a change in the shown pages is going to start being made.
fc2af5b0 (David A. Velasco 2013-02-26 12:33:28 +0100 183)      * 
fc2af5b0 (David A. Velasco 2013-02-26 12:33:28 +0100 184)      * @param   container   The containing View which is displaying this adapter's page views.
31827bce (David A. Velasco 2013-02-28 17:52:15 +0100 185)      *- /
fc2af5b0 (David A. Velasco 2013-02-26 12:33:28 +0100 186)     @Override
fc2af5b0 (David A. Velasco 2013-02-26 12:33:28 +0100 187)     public void startUpdate(ViewGroup container) {
fc2af5b0 (David A. Velasco 2013-02-26 12:33:28 +0100 188)         Log.e(TAG, "** startUpdate");
fc2af5b0 (David A. Velasco 2013-02-26 12:33:28 +0100 189)     }
fc2af5b0 (David A. Velasco 2013-02-26 12:33:28 +0100 190) 
fc2af5b0 (David A. Velasco 2013-02-26 12:33:28 +0100 191)     @Override
fc2af5b0 (David A. Velasco 2013-02-26 12:33:28 +0100 192)     public Object instantiateItem(ViewGroup container, int position) {
fc2af5b0 (David A. Velasco 2013-02-26 12:33:28 +0100 193)         Log.e(TAG, "** instantiateItem " + position);
fc2af5b0 (David A. Velasco 2013-02-26 12:33:28 +0100 194)         
fc2af5b0 (David A. Velasco 2013-02-26 12:33:28 +0100 195)         if (mFragments.size() > position) {
fc2af5b0 (David A. Velasco 2013-02-26 12:33:28 +0100 196)             Fragment fragment = mFragments.get(position);
fc2af5b0 (David A. Velasco 2013-02-26 12:33:28 +0100 197)             if (fragment != null) {
fc2af5b0 (David A. Velasco 2013-02-26 12:33:28 +0100 198)                 Log.e(TAG, "** \t returning cached item");
fc2af5b0 (David A. Velasco 2013-02-26 12:33:28 +0100 199)                 return fragment;
fc2af5b0 (David A. Velasco 2013-02-26 12:33:28 +0100 200)             }
fc2af5b0 (David A. Velasco 2013-02-26 12:33:28 +0100 201)         }
fc2af5b0 (David A. Velasco 2013-02-26 12:33:28 +0100 202) 
fc2af5b0 (David A. Velasco 2013-02-26 12:33:28 +0100 203)         if (mCurTransaction == null) {
fc2af5b0 (David A. Velasco 2013-02-26 12:33:28 +0100 204)             mCurTransaction = mFragmentManager.beginTransaction();
fc2af5b0 (David A. Velasco 2013-02-26 12:33:28 +0100 205)         }
fc2af5b0 (David A. Velasco 2013-02-26 12:33:28 +0100 206) 
fc2af5b0 (David A. Velasco 2013-02-26 12:33:28 +0100 207)         Fragment fragment = getItem(position);
fc2af5b0 (David A. Velasco 2013-02-26 12:33:28 +0100 208)         if (mSavedState.size() > position) {
fc2af5b0 (David A. Velasco 2013-02-26 12:33:28 +0100 209)             Fragment.SavedState savedState = mSavedState.get(position);
fc2af5b0 (David A. Velasco 2013-02-26 12:33:28 +0100 210)             if (savedState != null) {
fc2af5b0 (David A. Velasco 2013-02-26 12:33:28 +0100 211)                 // TODO WATCH OUT:
fc2af5b0 (David A. Velasco 2013-02-26 12:33:28 +0100 212)                 // * The Fragment must currently be attached to the FragmentManager.
fc2af5b0 (David A. Velasco 2013-02-26 12:33:28 +0100 213)                 // * A new Fragment created using this saved state must be the same class type as the Fragment it was created from.
fc2af5b0 (David A. Velasco 2013-02-26 12:33:28 +0100 214)                 // * The saved state can not contain dependencies on other fragments -- that is it can't use putFragment(Bundle, String, Fragment) 
fc2af5b0 (David A. Velasco 2013-02-26 12:33:28 +0100 215)                 //   to store a fragment reference                 
fc2af5b0 (David A. Velasco 2013-02-26 12:33:28 +0100 216)                 fragment.setInitialSavedState(savedState);
fc2af5b0 (David A. Velasco 2013-02-26 12:33:28 +0100 217)             }
fc2af5b0 (David A. Velasco 2013-02-26 12:33:28 +0100 218)         }
fc2af5b0 (David A. Velasco 2013-02-26 12:33:28 +0100 219)         while (mFragments.size() <= position) {
fc2af5b0 (David A. Velasco 2013-02-26 12:33:28 +0100 220)             mFragments.add(null);
fc2af5b0 (David A. Velasco 2013-02-26 12:33:28 +0100 221)         }
fc2af5b0 (David A. Velasco 2013-02-26 12:33:28 +0100 222)         fragment.setMenuVisibility(false);
fc2af5b0 (David A. Velasco 2013-02-26 12:33:28 +0100 223)         mFragments.set(position, fragment);
86cec34b (David A. Velasco 2013-02-27 14:08:58 +0100 224)         //Log.e(TAG, "** \t adding fragment at position " + position + ", containerId " + container.getId());
fc2af5b0 (David A. Velasco 2013-02-26 12:33:28 +0100 225)         mCurTransaction.add(container.getId(), fragment);
fc2af5b0 (David A. Velasco 2013-02-26 12:33:28 +0100 226) 
fc2af5b0 (David A. Velasco 2013-02-26 12:33:28 +0100 227)         return fragment;
fc2af5b0 (David A. Velasco 2013-02-26 12:33:28 +0100 228)     }
fc2af5b0 (David A. Velasco 2013-02-26 12:33:28 +0100 229) 
fc2af5b0 (David A. Velasco 2013-02-26 12:33:28 +0100 230)     @Override
fc2af5b0 (David A. Velasco 2013-02-26 12:33:28 +0100 231)     public void destroyItem(ViewGroup container, int position, Object object) {
fc2af5b0 (David A. Velasco 2013-02-26 12:33:28 +0100 232)         Log.e(TAG, "** destroyItem " + position);
fc2af5b0 (David A. Velasco 2013-02-26 12:33:28 +0100 233)         Fragment fragment = (Fragment)object;
fc2af5b0 (David A. Velasco 2013-02-26 12:33:28 +0100 234)         
fc2af5b0 (David A. Velasco 2013-02-26 12:33:28 +0100 235)         if (mCurTransaction == null) {
fc2af5b0 (David A. Velasco 2013-02-26 12:33:28 +0100 236)             mCurTransaction = mFragmentManager.beginTransaction();
fc2af5b0 (David A. Velasco 2013-02-26 12:33:28 +0100 237)         }
fc2af5b0 (David A. Velasco 2013-02-26 12:33:28 +0100 238)         Log.e(TAG, "** \t removing fragment at position " + position);
fc2af5b0 (David A. Velasco 2013-02-26 12:33:28 +0100 239)         while (mSavedState.size() <= position) {
fc2af5b0 (David A. Velasco 2013-02-26 12:33:28 +0100 240)             mSavedState.add(null);
fc2af5b0 (David A. Velasco 2013-02-26 12:33:28 +0100 241)         }
fc2af5b0 (David A. Velasco 2013-02-26 12:33:28 +0100 242)         mSavedState.set(position, mFragmentManager.saveFragmentInstanceState(fragment));
fc2af5b0 (David A. Velasco 2013-02-26 12:33:28 +0100 243)         mFragments.set(position, null);
fc2af5b0 (David A. Velasco 2013-02-26 12:33:28 +0100 244) 
fc2af5b0 (David A. Velasco 2013-02-26 12:33:28 +0100 245)         mCurTransaction.remove(fragment);
fc2af5b0 (David A. Velasco 2013-02-26 12:33:28 +0100 246)     }
fc2af5b0 (David A. Velasco 2013-02-26 12:33:28 +0100 247) 
fc2af5b0 (David A. Velasco 2013-02-26 12:33:28 +0100 248)     @Override
fc2af5b0 (David A. Velasco 2013-02-26 12:33:28 +0100 249)     public void setPrimaryItem(ViewGroup container, int position, Object object) {
fc2af5b0 (David A. Velasco 2013-02-26 12:33:28 +0100 250)         Fragment fragment = (Fragment)object;
fc2af5b0 (David A. Velasco 2013-02-26 12:33:28 +0100 251)         if (fragment != mCurrentPrimaryItem) {
fc2af5b0 (David A. Velasco 2013-02-26 12:33:28 +0100 252)             if (mCurrentPrimaryItem != null) {
fc2af5b0 (David A. Velasco 2013-02-26 12:33:28 +0100 253)                 mCurrentPrimaryItem.setMenuVisibility(false);
fc2af5b0 (David A. Velasco 2013-02-26 12:33:28 +0100 254)             }
fc2af5b0 (David A. Velasco 2013-02-26 12:33:28 +0100 255)             if (fragment != null) {
fc2af5b0 (David A. Velasco 2013-02-26 12:33:28 +0100 256)                 fragment.setMenuVisibility(true);
fc2af5b0 (David A. Velasco 2013-02-26 12:33:28 +0100 257)             }
fc2af5b0 (David A. Velasco 2013-02-26 12:33:28 +0100 258)             mCurrentPrimaryItem = fragment;
fc2af5b0 (David A. Velasco 2013-02-26 12:33:28 +0100 259)         }
fc2af5b0 (David A. Velasco 2013-02-26 12:33:28 +0100 260)     }
fc2af5b0 (David A. Velasco 2013-02-26 12:33:28 +0100 261) 
fc2af5b0 (David A. Velasco 2013-02-26 12:33:28 +0100 262)     @Override
fc2af5b0 (David A. Velasco 2013-02-26 12:33:28 +0100 263)     public void finishUpdate(ViewGroup container) {
fc2af5b0 (David A. Velasco 2013-02-26 12:33:28 +0100 264)         Log.e(TAG, "** finishUpdate (start)");
fc2af5b0 (David A. Velasco 2013-02-26 12:33:28 +0100 265)         if (mCurTransaction != null) {
fc2af5b0 (David A. Velasco 2013-02-26 12:33:28 +0100 266)             mCurTransaction.commitAllowingStateLoss();
fc2af5b0 (David A. Velasco 2013-02-26 12:33:28 +0100 267)             mCurTransaction = null;
fc2af5b0 (David A. Velasco 2013-02-26 12:33:28 +0100 268)             mFragmentManager.executePendingTransactions();
fc2af5b0 (David A. Velasco 2013-02-26 12:33:28 +0100 269)         }
fc2af5b0 (David A. Velasco 2013-02-26 12:33:28 +0100 270)         Log.e(TAG, "** finishUpdate (end)");
fc2af5b0 (David A. Velasco 2013-02-26 12:33:28 +0100 271)     }
fc2af5b0 (David A. Velasco 2013-02-26 12:33:28 +0100 272) 
fc2af5b0 (David A. Velasco 2013-02-26 12:33:28 +0100 273)     @Override
fc2af5b0 (David A. Velasco 2013-02-26 12:33:28 +0100 274)     public boolean isViewFromObject(View view, Object object) {
fc2af5b0 (David A. Velasco 2013-02-26 12:33:28 +0100 275)         return ((Fragment)object).getView() == view;
fc2af5b0 (David A. Velasco 2013-02-26 12:33:28 +0100 276)     }
fc2af5b0 (David A. Velasco 2013-02-26 12:33:28 +0100 277) 
fc2af5b0 (David A. Velasco 2013-02-26 12:33:28 +0100 278)     @Override
fc2af5b0 (David A. Velasco 2013-02-26 12:33:28 +0100 279)     public Parcelable saveState() {
fc2af5b0 (David A. Velasco 2013-02-26 12:33:28 +0100 280)         Bundle state = null;
fc2af5b0 (David A. Velasco 2013-02-26 12:33:28 +0100 281)         if (mSavedState.size() > 0) {
fc2af5b0 (David A. Velasco 2013-02-26 12:33:28 +0100 282)             state = new Bundle();
fc2af5b0 (David A. Velasco 2013-02-26 12:33:28 +0100 283)             Fragment.SavedState[] savedStates = new Fragment.SavedState[mSavedState.size()];
fc2af5b0 (David A. Velasco 2013-02-26 12:33:28 +0100 284)             mSavedState.toArray(savedStates);
fc2af5b0 (David A. Velasco 2013-02-26 12:33:28 +0100 285)             state.putParcelableArray("states", savedStates);
fc2af5b0 (David A. Velasco 2013-02-26 12:33:28 +0100 286)         }
fc2af5b0 (David A. Velasco 2013-02-26 12:33:28 +0100 287)         for (int i=0; i<mFragments.size(); i++) {
fc2af5b0 (David A. Velasco 2013-02-26 12:33:28 +0100 288)             Fragment fragment = mFragments.get(i);
fc2af5b0 (David A. Velasco 2013-02-26 12:33:28 +0100 289)             if (fragment != null) {
fc2af5b0 (David A. Velasco 2013-02-26 12:33:28 +0100 290)                 if (state == null) {
fc2af5b0 (David A. Velasco 2013-02-26 12:33:28 +0100 291)                     state = new Bundle();
fc2af5b0 (David A. Velasco 2013-02-26 12:33:28 +0100 292)                 }
fc2af5b0 (David A. Velasco 2013-02-26 12:33:28 +0100 293)                 String key = "f" + i;
fc2af5b0 (David A. Velasco 2013-02-26 12:33:28 +0100 294)                 mFragmentManager.putFragment(state, key, fragment);
fc2af5b0 (David A. Velasco 2013-02-26 12:33:28 +0100 295)             }
fc2af5b0 (David A. Velasco 2013-02-26 12:33:28 +0100 296)         }
fc2af5b0 (David A. Velasco 2013-02-26 12:33:28 +0100 297)         return state;
fc2af5b0 (David A. Velasco 2013-02-26 12:33:28 +0100 298)     }
fc2af5b0 (David A. Velasco 2013-02-26 12:33:28 +0100 299) 
fc2af5b0 (David A. Velasco 2013-02-26 12:33:28 +0100 300)     @Override
fc2af5b0 (David A. Velasco 2013-02-26 12:33:28 +0100 301)     public void restoreState(Parcelable state, ClassLoader loader) {
fc2af5b0 (David A. Velasco 2013-02-26 12:33:28 +0100 302)         if (state != null) {
fc2af5b0 (David A. Velasco 2013-02-26 12:33:28 +0100 303)             Bundle bundle = (Bundle)state;
fc2af5b0 (David A. Velasco 2013-02-26 12:33:28 +0100 304)             bundle.setClassLoader(loader);
fc2af5b0 (David A. Velasco 2013-02-26 12:33:28 +0100 305)             Parcelable[] states = bundle.getParcelableArray("states");
fc2af5b0 (David A. Velasco 2013-02-26 12:33:28 +0100 306)             mSavedState.clear();
fc2af5b0 (David A. Velasco 2013-02-26 12:33:28 +0100 307)             mFragments.clear();
fc2af5b0 (David A. Velasco 2013-02-26 12:33:28 +0100 308)             if (states != null) {
fc2af5b0 (David A. Velasco 2013-02-26 12:33:28 +0100 309)                 for (int i=0; i<states.length; i++) {
fc2af5b0 (David A. Velasco 2013-02-26 12:33:28 +0100 310)                     mSavedState.add((Fragment.SavedState)states[i]);
fc2af5b0 (David A. Velasco 2013-02-26 12:33:28 +0100 311)                 }
fc2af5b0 (David A. Velasco 2013-02-26 12:33:28 +0100 312)             }
fc2af5b0 (David A. Velasco 2013-02-26 12:33:28 +0100 313)             Iterable<String> keys = bundle.keySet();
fc2af5b0 (David A. Velasco 2013-02-26 12:33:28 +0100 314)             for (String key: keys) {
fc2af5b0 (David A. Velasco 2013-02-26 12:33:28 +0100 315)                 if (key.startsWith("f")) {
fc2af5b0 (David A. Velasco 2013-02-26 12:33:28 +0100 316)                     int index = Integer.parseInt(key.substring(1));
fc2af5b0 (David A. Velasco 2013-02-26 12:33:28 +0100 317)                     Fragment f = mFragmentManager.getFragment(bundle, key);
fc2af5b0 (David A. Velasco 2013-02-26 12:33:28 +0100 318)                     if (f != null) {
fc2af5b0 (David A. Velasco 2013-02-26 12:33:28 +0100 319)                         while (mFragments.size() <= index) {
fc2af5b0 (David A. Velasco 2013-02-26 12:33:28 +0100 320)                             mFragments.add(null);
fc2af5b0 (David A. Velasco 2013-02-26 12:33:28 +0100 321)                         }
fc2af5b0 (David A. Velasco 2013-02-26 12:33:28 +0100 322)                         f.setMenuVisibility(false);
fc2af5b0 (David A. Velasco 2013-02-26 12:33:28 +0100 323)                         mFragments.set(index, f);
fc2af5b0 (David A. Velasco 2013-02-26 12:33:28 +0100 324)                     } else {
fc2af5b0 (David A. Velasco 2013-02-26 12:33:28 +0100 325)                         Log.w(TAG, "Bad fragment at key " + key);
fc2af5b0 (David A. Velasco 2013-02-26 12:33:28 +0100 326)                     }
fc2af5b0 (David A. Velasco 2013-02-26 12:33:28 +0100 327)                 }
fc2af5b0 (David A. Velasco 2013-02-26 12:33:28 +0100 328)             }
fc2af5b0 (David A. Velasco 2013-02-26 12:33:28 +0100 329)         }
86cec34b (David A. Velasco 2013-02-27 14:08:58 +0100 330)     }
31827bce (David A. Velasco 2013-02-28 17:52:15 +0100 331)     */
fc2af5b0 (David A. Velasco 2013-02-26 12:33:28 +0100 332) }
