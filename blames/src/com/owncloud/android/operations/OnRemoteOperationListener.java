92080afe (Bartek Przybylski 2012-10-28 00:12:15 +0200  1) /* ownCloud Android client application
92080afe (Bartek Przybylski 2012-10-28 00:12:15 +0200  2)  *   Copyright (C) 2012 Bartek Przybylski
bb257ec7 (David A. Velasco  2013-02-07 18:45:10 +0100  3)  *   Copyright (C) 2012-2013 ownCloud Inc.
92080afe (Bartek Przybylski 2012-10-28 00:12:15 +0200  4)  *
92080afe (Bartek Przybylski 2012-10-28 00:12:15 +0200  5)  *   This program is free software: you can redistribute it and/or modify
bdc0332c (David A. Velasco  2013-04-17 12:26:13 +0200  6)  *   it under the terms of the GNU General Public License version 2,
bdc0332c (David A. Velasco  2013-04-17 12:26:13 +0200  7)  *   as published by the Free Software Foundation.
92080afe (Bartek Przybylski 2012-10-28 00:12:15 +0200  8)  *
92080afe (Bartek Przybylski 2012-10-28 00:12:15 +0200  9)  *   This program is distributed in the hope that it will be useful,
92080afe (Bartek Przybylski 2012-10-28 00:12:15 +0200 10)  *   but WITHOUT ANY WARRANTY; without even the implied warranty of
92080afe (Bartek Przybylski 2012-10-28 00:12:15 +0200 11)  *   MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
92080afe (Bartek Przybylski 2012-10-28 00:12:15 +0200 12)  *   GNU General Public License for more details.
92080afe (Bartek Przybylski 2012-10-28 00:12:15 +0200 13)  *
92080afe (Bartek Przybylski 2012-10-28 00:12:15 +0200 14)  *   You should have received a copy of the GNU General Public License
92080afe (Bartek Przybylski 2012-10-28 00:12:15 +0200 15)  *   along with this program.  If not, see <http://www.gnu.org/licenses/>.
92080afe (Bartek Przybylski 2012-10-28 00:12:15 +0200 16)  *
92080afe (Bartek Przybylski 2012-10-28 00:12:15 +0200 17)  */
92080afe (Bartek Przybylski 2012-10-28 00:12:15 +0200 18) 
48f13c8a (David A. Velasco  2012-09-05 13:46:30 +0200 19) package com.owncloud.android.operations;
48f13c8a (David A. Velasco  2012-09-05 13:46:30 +0200 20) 
48f13c8a (David A. Velasco  2012-09-05 13:46:30 +0200 21) public interface OnRemoteOperationListener {
48f13c8a (David A. Velasco  2012-09-05 13:46:30 +0200 22) 
48f13c8a (David A. Velasco  2012-09-05 13:46:30 +0200 23) 	void onRemoteOperationFinish(RemoteOperation caller, RemoteOperationResult result);
53b67429 (David A. Velasco  2012-09-12 16:16:56 +0200 24) 	
48f13c8a (David A. Velasco  2012-09-05 13:46:30 +0200 25) }
