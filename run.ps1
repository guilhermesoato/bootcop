# Sleep Tracker - Executável com PowerShell
$env:JAVA_HOME = "C:\Program Files\Java\jdk-25.0.2"
$mvnPath = "C:\Users\s029585781\.maven\maven-3.9.14(1)\bin\mvn.cmd"

Write-Host "===========================================" -ForegroundColor Cyan
Write-Host "  Sleep Tracker - Java 21 LTS" -ForegroundColor Green
Write-Host "===========================================" -ForegroundColor Cyan
Write-Host ""
Write-Host "Iniciando aplicação..."
Write-Host ""

Set-Location "C:\Users\s029585781\Documents\GitHub\bootcamp\sleep-tracker"

# Compilar e executar
& $mvnPath compile exec:java@sleep-tracker
