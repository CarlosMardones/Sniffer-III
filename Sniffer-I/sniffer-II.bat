@echo off
rem *
REM Carlos Mardones Muga
rem *


set ejecutable=.\sniffer-II.jar
set libs1=.\libs\net.sourceforge.jpcap-0.01.16.jar
set libs2=.\libs\jdom.jar
set libs3=.\libs\AbsoluteLayout.jar
set libs4=.\libs\jfreechart-0.9.21.jar
set libs5=.\libs\jcommon-0.9.6.jar
set libs=%libs1%;%libs2%;%libs3%;%libs4%;%libs5%
set mvm=
Rem set javaPath=    Poner aki el path de java sino aparece por defecto en el PATH
set PATH=.;%PATH%
echo %PATH%


:main
cls
echo.  
echo           ���������������������������������������������������������Ŀ
echo           �                Proyecto Fin de Carrera                  �
echo           �                   SNIFFER II - V 3.0                    �
echo           �                                                         �
echo           �                Carlos Mardones Muga                     �
echo           �                                                         �
echo           �����������������������������������������������������������
echo.
echo                    ���������������������������������������Ŀ
echo                    �                                       �
echo                    �   1.- Modo Grafico                    �
echo                    �                                       �
echo                    �   2.- Modo Comando                    �
echo                    �                                       �
echo                    �   3.- Configurar MVJava               �
echo                    �                                       �
echo                    �   0.- Salir                           � 
echo                    �����������������������������������������
echo.
set choice=
set /p choice=                    Select option: 
rem if not '%choice%'=='' set choice=%choice:~0,1% solo coge un caracter
if '%choice%'=='3' goto mvj
if '%choice%'=='2' goto line
if '%choice%'=='1' goto graphic
if '%choice%'=='0' goto exit
echo.
ECHO "%choice%" is not valid please try again
pause
ECHO.
goto main
:graphic
  cls
	set java=java
	if not '%mvm%' == '' set java=java -Xmx%mvm%m
	rem echo "%java% -jar %ejecutable% 1" 
	rem pause
  	%java% -jar "%ejecutable%"
	
	goto end
:line
	cls
	set java=java
	%java% -jar "%ejecutable%" -help	
	echo Pulse una tecla para continuar!!!
	echo .
	pause
	goto end
:mvj
	cls
	echo.
	echo           ���������������������������������������������������������Ŀ
	echo           �                Proyecto Fin de Carrera                  �
	echo           �                   SNIFFER II - V 3.0                    �
	echo           �                                                         �
	echo           �                  Carlos Mardones Muga                   �
	echo           �                                                         �
	echo           �����������������������������������������������������������
	echo.
	echo                    ���������������������������������������Ŀ
	echo                    �                                       �
	echo                    �            Config MVJ                 �
	echo                    �            ----------                 �
	echo                    �                                       �
	echo                    �   1.- Memoria virtual maxima          �
	echo                    �                                       �
	echo                    �   0.- Main Menu                       � 
	echo                    �����������������������������������������
	echo.
	set choice=
	set /p choice=                    Select option:  
	if '%choice%'=='0' goto main
	if '%choice%'=='1' goto virtualmemory
	echo.
	ECHO "%choice%" is not valid please try again
	pause
	ECHO.
	goto mvj
	:virtualmemory
	echo.
	set /p mvm=                    Selecciona memoria virtual maxima (Megas): 
	goto main
:exit

echo "Max Virtual Memory %mvm%"
