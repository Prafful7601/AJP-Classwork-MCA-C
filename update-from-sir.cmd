@echo off
setlocal

set "UPSTREAM_NAME=upstream"
set "UPSTREAM_URL=https://github.com/DrVipinKumar/AJP2026_A_C.git"
set "UPSTREAM_BRANCH=main"

git rev-parse --is-inside-work-tree >nul 2>&1
if errorlevel 1 (
    echo Run this script from inside your git repository.
    exit /b 1
)

git remote get-url %UPSTREAM_NAME% >nul 2>&1
if errorlevel 1 (
    echo Adding %UPSTREAM_NAME% remote...
    git remote add %UPSTREAM_NAME% %UPSTREAM_URL%
    if errorlevel 1 exit /b 1
)

echo Fetching latest code from sir's repo...
git fetch %UPSTREAM_NAME%
if errorlevel 1 exit /b 1

echo.
echo Important code/project files changed in sir's repo:
git diff --name-only HEAD..%UPSTREAM_NAME%/%UPSTREAM_BRANCH% | findstr /V /I ".metadata/" | findstr /I /R "\.java$ \.sql$ \.md$ \.cmd$ \.classpath$ \.project$"

echo.
echo JDBC/student-related files in sir's latest repo:
git ls-tree -r --name-only %UPSTREAM_NAME%/%UPSTREAM_BRANCH% | findstr /I "jdbc student"

echo.
echo Full changed-file list excluding Eclipse metadata:
git diff --name-status HEAD..%UPSTREAM_NAME%/%UPSTREAM_BRANCH% | findstr /V /I ".metadata/"

echo.
echo To copy one file from sir's repo into your repo:
echo   git checkout %UPSTREAM_NAME%/%UPSTREAM_BRANCH% -- path\to\file.java

echo.
echo Example:
echo   git checkout %UPSTREAM_NAME%/%UPSTREAM_BRANCH% -- AJPSecA\src\jdbc\MyDB.java
