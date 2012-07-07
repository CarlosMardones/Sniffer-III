#!/bin/sh
ejecutable=sniffer.jar
mvm=0
while true
do
 clear
 echo 
 echo "         ###########################################################"
 echo "         #                Proyecto Fin de Carrera                  #"
 echo "         #                   SNIFFER I - V 2.0                     #" 
 echo "         #                                                         #" 
 echo "         #         Leonardo Garcia  - Jose Ramon Gutierrez         #" 
 echo "         #                                                         #"
 echo "         ###########################################################"
 echo
 echo "                   #########################################"
 echo "                   #                                       #" 
 echo "                   #          1.- Graphic mode             #" 
 echo "                   #                                       #"
 echo "                   #          2.- Command line             #"
 echo "                   #                                       #"         
 echo "                   #          3.- Config MVJ               #"    
 echo "                   #                                       #" 
 echo "                   #          0.- Salir                    #"    
 echo "                   #                                       #" 
 echo "                   #########################################"
 echo
 echo "Option: "
 read option
#------ Graphic Mode -------------------
 if [ $option = "1" ]; then	
  if [ $mvm = "0" ]; then
   java=java
  else
   java="java -Xmx"$mvm"m"
  fi
  #echo "$java -jar $ejecutable"
  clear
  $java -jar $ejecutable
#------ Command line -------------------
 elif [ $option = "2" ]; then
  clear
  java -jar $ejecutable -help
  echo "Pulse intro para continuar..." 
  read alo
#------ Config MVJ ---------------------
 elif [ $option = "3" ]; then
  while true
  do
   clear
   echo
   echo "         ###########################################################"
   echo "         #                Proyecto Fin de Carrera                  #"
   echo "         #                   SNIFFER I - V 2.0                     #" 
   echo "         #                                                         #" 
   echo "         #         Leonardo Garcia  - Jose Ramon Gutierrez         #" 
   echo "         #                                                         #"
   echo "         ###########################################################"
   echo
   echo "                   #########################################"
   echo "                   #                                       #" 
   echo "                   #          1.- Max Virtual Memory       #" 
   echo "                   #                                       #" 
   echo "                   #          0.- Salir                    #"    
   echo "                   #                                       #" 
   echo "                   #########################################"
   echo "Option: "
   read option
   if [ $option = "1" ]; then
    echo "==> Select max virual memory (Megas): "
    read mvm
    break
    break
   elif [ $option = "0" ]; then
    break
   else
    clear
    echo "Opcion incorrecta"
    sleep 1 
   fi
  done
#------ Exit Script --------------------
 elif [ $option = "0" ]; then
  exit
 else
  clear
  echo "Opcion incorrecta"
  sleep 1 
 fi
done
