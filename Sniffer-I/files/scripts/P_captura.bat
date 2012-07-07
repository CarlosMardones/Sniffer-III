@echo off
rem *
REM LEONARDO GARCIA Y JOSE RAMON GUTIERREZ
rem Fichero creado por Sniffer
rem *
rem *
rem Fichero de captura automatica en modo comando
rem *

set ruta=C:\Sniffer-I
set ejecutable=sniffer.jar

set param=-command -scan "C:\Sniffer-I\files\parametrizacion\DefaultPreferencesCapture.xml"
set mvm=0

cd %ruta%
set PATH=.;%PATH%

set java=java
if not '%mvm%' == '0' set java=java -Xmx%mvm%m

echo.
echo           ÚÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄ¿
echo           ³                Proyecto Fin de Carrera                  ³
echo           ³                      SNIFFER I                          ³
echo           ³                                                         ³
echo           ³         Leonardo Garcia  - Jose Ramon Gutierrez         ³
echo           ³                                                         ³
echo           ÀÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÙ
echo.

%java% -jar %ejecutable% %param%

