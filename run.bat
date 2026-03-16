@echo off
setlocal enabledelayedexpansion

REM Sleep Tracker - Application Launcher
set JAVA_HOME=C:\Program Files\Java\jdk-25.0.2
set PATH=%JAVA_HOME%\bin;%PATH%
set APP_DIR=%~dp0

echo.
echo ==========================================
echo   Sleep Tracker - Java 21 LTS
echo ==========================================
echo.
echo Compilando e executando...
echo.

cd /d "%APP_DIR%"

REM Usar Maven para compilar e executar
"C:\Users\s029585781\.maven\maven-3.9.14(1)\bin\mvn.cmd" compile ^
    exec:java ^
    -Dexec.mainClass="com.habitsleep.SleepTrackerApp"

pause
