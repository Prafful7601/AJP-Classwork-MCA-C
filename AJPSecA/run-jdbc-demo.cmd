@echo off
setlocal

set "PROJECT_DIR=%~dp0"
set "BIN_DIR=%PROJECT_DIR%bin"
set "DRIVER_JAR=%PROJECT_DIR%lib\mysql-connector-j-9.6.0.jar"
set "SOURCE_FILE=%PROJECT_DIR%src\jdbc\MyDB.java"

if not exist "%BIN_DIR%" mkdir "%BIN_DIR%"

javac -cp "%DRIVER_JAR%" -d "%BIN_DIR%" "%SOURCE_FILE%"
if errorlevel 1 exit /b 1

pushd "%PROJECT_DIR%"
java -cp "%BIN_DIR%;%DRIVER_JAR%" jdbc.MyDB
popd
