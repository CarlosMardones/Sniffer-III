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

set param=-command -state "C:\Sniffer-I\files\capturas\capturas_090723_225914_state.xml"
set mvm=0

cd %ruta%
set PATH=.;%PATH%

set java=java
if not '%mvm%' == '0' set java=java -Xmx%mvm%m

echo.
echo           旼컴컴컴컴컴컴컴컴컴컴컴컴컴컴컴컴컴컴컴컴컴컴컴컴컴컴컴컴�
echo           �                Proyecto Fin de Carrera                  �
echo           �                      SNIFFER I                          �
echo           �                                                         �
echo           �         Leonardo Garcia  - Jose Ramon Gutierrez         �
echo           �                                                         �
echo           읕컴컴컴컴컴컴컴컴컴컴컴컴컴컴컴컴컴컴컴컴컴컴컴컴컴컴컴컴�
echo.

%java% -jar %ejecutable% %param%

