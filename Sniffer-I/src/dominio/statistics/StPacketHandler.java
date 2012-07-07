package dominio.statistics;
import net.sourceforge.jpcap.net.*;


class StPacketHandler {
    
      public int i = 0;
      
      public StPacketHandler (Packet packet) {
 	    ++i;	 
            try {
  
	     if  ((packet instanceof ICMPPacket)||(packet instanceof IGMPPacket) 
	         ||(packet instanceof TCPPacket)||(packet instanceof UDPPacket)){
	     	// Paquetes ICMP
	     	if (packet instanceof ICMPPacket) {
	     		ICMPPacket icmpPacket = (ICMPPacket) packet;
	     	 	Estadisticas.ICMPPacket (icmpPacket);
	     	}
	     	// Paquet IGMP
	     	if (packet instanceof IGMPPacket) {
	     		IGMPPacket igmpPacket = (IGMPPacket) packet;
	     		Estadisticas.IGMPPacket (igmpPacket);
	     	}  
	     	if(packet instanceof TCPPacket) {
	     		//Paketes TCP
	     		TCPPacket tcpPacket = (TCPPacket)packet;
	     		Estadisticas.TCPPacket (tcpPacket);  
	     	}
	     	if (packet instanceof UDPPacket) {
	     		//Paketes UDP
	     		UDPPacket udpPacket = (UDPPacket)packet;
	     		Estadisticas.UDPPacket (udpPacket);  
	     	} 
	     }else{
	     	  if((packet instanceof ARPPacket)||(packet instanceof IPPacket)){   
	     	     if(packet instanceof ARPPacket){ 	
	    	      // Paketes ARP	
	    		   ARPPacket arpPacket = (ARPPacket) packet;
                           Estadisticas.ARPPacket (arpPacket);  
                     }
	    	     // Paquetes IP
	    	      if (packet instanceof IPPacket) {
	    	          IPPacket ipPacket = (IPPacket) packet;	
	    	          Estadisticas.IPPacket (ipPacket);  
	    	      }
	     	  }else{
	     	       if (packet instanceof EthernetPacket) {
	 		  EthernetPacket ethernetPacket = (EthernetPacket) packet;
	 		  Estadisticas.EtherPacket (ethernetPacket);  
	 	       }
	     	  }
	     }
	     
	    } catch( Exception e ) {
	      e.printStackTrace();
	    }
	  }
	}
