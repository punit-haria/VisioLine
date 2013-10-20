::This will retrieve all blames for the owncloud source directory
::cd to the src folder 
::set 'replace' to project path root
::set 'with' to a path to store blames
ECHO OFF 
E:
cd C:\git\android\src
setlocal ENABLEDELAYEDEXPANSION
SET "_replace=C:\git\android"
SET "_with=C:\git\blames"
FOR /r %%i in (*) DO SET "_newpath=%%i" & call set "_newpath=%%_newpath:!_replace!=!_with!%%" & git blame %%i > "!_newpath!"
PAUSE
::The following deletes all files in directory including files in subdirectories (but leaves subdirectories alone as long there are no '.' in subdirectory names)
::FOR /R "E:\Git\blames" %%G IN (*.*) DO del "%%G"
