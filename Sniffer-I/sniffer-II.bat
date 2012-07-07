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
echo           ÚÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄ¿
echo           ³                Proyecto Fin de Carrera                  ³
echo           ³                   SNIFFER II - V 3.0                    ³
echo           ³                                                         ³
echo           ³                Carlos Mardones Muga                     ³
echo           ³                                                         ³
echo           ÀÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÙ
echo.
echo                    ÚÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄ¿
echo                    ³                                       ³
echo                    ³   1.- Modo Grafico                    ³
echo                    ³                                       ³
echo                    ³   2.- Modo Comando                    ³
echo                    ³                                       ³
echo                    ³   3.- Configurar MVJava               ³
echo                    ³                                       ³
echo                    ³   0.- Salir                           ³ 
echo                    ÀÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÙ
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
	echo           ÚÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄ¿
	echo           ³                Proyecto Fin de Carrera                  ³
	echo           ³                   SNIFFER II - V 3.0                    ³
	echo           ³                                                         ³
	echo           ³                  Carlos Mardones Muga                   ³
	echo           ³                                                         ³
	echo           ÀÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÙ
	echo.
	echo                    ÚÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄ¿
	echo                    ³                                       ³
	echo                    ³            Config MVJ                 ³
	echo                    ³            ----------                 ³
	echo                    ³                                       ³
	echo                    ³   1.- Memoria virtual maxima          ³
	echo                    ³                                       ³
	echo                    ³   0.- Main Menu                       ³ 
	echo                    ÀÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÙ
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
