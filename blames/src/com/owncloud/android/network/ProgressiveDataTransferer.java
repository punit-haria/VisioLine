24dd5136 (David A. Velasco 2013-02-19 13:28:01 +0100  1) /* ownCloud Android client application
24dd5136 (David A. Velasco 2013-02-19 13:28:01 +0100  2)  *   Copyright (C) 2012-2013 ownCloud Inc.
24dd5136 (David A. Velasco 2013-02-19 13:28:01 +0100  3)  *
24dd5136 (David A. Velasco 2013-02-19 13:28:01 +0100  4)  *   This program is free software: you can redistribute it and/or modify
bdc0332c (David A. Velasco 2013-04-17 12:26:13 +0200  5)  *   it under the terms of the GNU General Public License version 2,
bdc0332c (David A. Velasco 2013-04-17 12:26:13 +0200  6)  *   as published by the Free Software Foundation.
24dd5136 (David A. Velasco 2013-02-19 13:28:01 +0100  7)  *
24dd5136 (David A. Velasco 2013-02-19 13:28:01 +0100  8)  *   This program is distributed in the hope that it will be useful,
24dd5136 (David A. Velasco 2013-02-19 13:28:01 +0100  9)  *   but WITHOUT ANY WARRANTY; without even the implied warranty of
24dd5136 (David A. Velasco 2013-02-19 13:28:01 +0100 10)  *   MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
24dd5136 (David A. Velasco 2013-02-19 13:28:01 +0100 11)  *   GNU General Public License for more details.
24dd5136 (David A. Velasco 2013-02-19 13:28:01 +0100 12)  *
24dd5136 (David A. Velasco 2013-02-19 13:28:01 +0100 13)  *   You should have received a copy of the GNU General Public License
24dd5136 (David A. Velasco 2013-02-19 13:28:01 +0100 14)  *   along with this program.  If not, see <http://www.gnu.org/licenses/>.
24dd5136 (David A. Velasco 2013-02-19 13:28:01 +0100 15)  *
24dd5136 (David A. Velasco 2013-02-19 13:28:01 +0100 16)  */
24dd5136 (David A. Velasco 2013-02-19 13:28:01 +0100 17) 
24dd5136 (David A. Velasco 2013-02-19 13:28:01 +0100 18) package com.owncloud.android.network;
24dd5136 (David A. Velasco 2013-02-19 13:28:01 +0100 19) 
24dd5136 (David A. Velasco 2013-02-19 13:28:01 +0100 20) import java.util.Collection;
24dd5136 (David A. Velasco 2013-02-19 13:28:01 +0100 21) 
24dd5136 (David A. Velasco 2013-02-19 13:28:01 +0100 22) import eu.alefzero.webdav.OnDatatransferProgressListener;
24dd5136 (David A. Velasco 2013-02-19 13:28:01 +0100 23) 
24dd5136 (David A. Velasco 2013-02-19 13:28:01 +0100 24) public interface ProgressiveDataTransferer {
24dd5136 (David A. Velasco 2013-02-19 13:28:01 +0100 25) 
24dd5136 (David A. Velasco 2013-02-19 13:28:01 +0100 26)     public void addDatatransferProgressListener (OnDatatransferProgressListener listener);
24dd5136 (David A. Velasco 2013-02-19 13:28:01 +0100 27)     
24dd5136 (David A. Velasco 2013-02-19 13:28:01 +0100 28)     public void addDatatransferProgressListeners(Collection<OnDatatransferProgressListener> listeners);
24dd5136 (David A. Velasco 2013-02-19 13:28:01 +0100 29) 
24dd5136 (David A. Velasco 2013-02-19 13:28:01 +0100 30)     public void removeDatatransferProgressListener(OnDatatransferProgressListener listener);
24dd5136 (David A. Velasco 2013-02-19 13:28:01 +0100 31) 
24dd5136 (David A. Velasco 2013-02-19 13:28:01 +0100 32) }
