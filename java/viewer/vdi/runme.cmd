@echo off

set URL="jdbc:odbc:TEST8"
set USER="db2student8"
set PASSWORD="8"
set DRIVER="sun.jdbc.odbc.JdbcOdbcDriver"

set LANG=Ru_RU
set DB2CODEPAGE=1251

db2cmd.exe tempdb2.cmd -f viewer_start.sql

java -cp "%CLASSPATH%;viewer.jar" viewer.navigator.Viewer "%URL%" "%USER%" "%PASSWORD%" "%DRIVER%"

db2cmd.exe tempdb2.cmd -f viewer_stop.sql
