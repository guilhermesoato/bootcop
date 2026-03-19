@echo off
setlocal enabledelayedexpansion

REM ============================================================
REM  Sleep Tracker - Execucao (JavaFX via Maven instalado)
REM  - Mantem janela aberta
REM  - Faz log em run_last.log
REM  - Detecta mvn mesmo se PATH do Explorer nao estiver atualizado
REM ============================================================

REM Ir para a pasta deste .bat (raiz do projeto)
cd /d "%~dp0"

REM Arquivo de log (na raiz do projeto)
set "LOG=%~dp0run_last.log"
echo [%DATE% %TIME%] Iniciando execucao > "%LOG%"
echo.

echo ==========================================
echo   Sleep Tracker - JavaFX (via Maven)
echo ==========================================
echo.
echo [INFO] Pasta do projeto: %CD%
echo [INFO] Log: %LOG%
echo.

REM ------------------------------------------------------------
REM 1) Descobrir caminho do mvn.cmd
REM ------------------------------------------------------------
set "MVN_BIN="
REM Tenta via PATH
for /f "delims=" %%P in ('where mvn 2^>nul') do (
  set "MVN_BIN=%%P"
  goto :MVN_FOUND
)

REM Se nao achou no PATH, tenta em pastas comuns (ajuste se precisar)
for /f "delims=" %%P in ('dir /b /s "C:\Program Files\apache-maven*\*\bin\mvn.cmd" 2^>nul') do (
  set "MVN_BIN=%%P"
  goto :MVN_FOUND
)
for /f "delims=" %%P in ('dir /b /s "C:\apache-maven*\bin\mvn.cmd" 2^>nul') do (
  set "MVN_BIN=%%P"
  goto :MVN_FOUND
)

echo [ERRO] Maven (mvn.cmd) nao encontrado.
echo [ERRO] Adicione o Maven ao PATH OU instale em "C:\Program Files\apache-maven-<versao>\"
echo [ERRO] Depois feche e reabra o Explorer/Windows para o PATH surtir efeito.
echo.>> "%LOG%"
echo [ERRO] Maven (mvn.cmd) nao encontrado. >> "%LOG%"
goto :END

:MVN_FOUND
echo [OK] Usando Maven em: "%MVN_BIN%"
echo [OK] Usando Maven em: "%MVN_BIN%" >> "%LOG%"
echo.

REM ------------------------------------------------------------
REM 2) Build + Execucao com o plugin JavaFX (forma oficial)
REM ------------------------------------------------------------
echo [INFO] Compilando e executando com JavaFX...
echo [INFO] Compilando e executando com JavaFX... >> "%LOG%"
echo.>> "%LOG%"

REM -e para stacktrace; retire -q para ver toda a saida
call "%MVN_BIN%" -e clean javafx:run 1>> "%LOG%" 2>>&1
set "ERR=%ERRORLEVEL%"

echo.
if "%ERR%"=="0" (
  echo [OK] Aplicacao finalizada com sucesso.
  echo [OK] Aplicacao finalizada com sucesso. >> "%LOG%"
) else (
  echo [ERRO] Execucao falhou (codigo %ERR%). Veja o arquivo de log: "%LOG%"
  echo [ERRO] Execucao falhou (codigo %ERR%). >> "%LOG%"
)

:END
echo.
echo (Pressione qualquer tecla para fechar)
pause >nul
exit /b %ERR%