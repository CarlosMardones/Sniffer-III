package dominio.statistics;
import net.sourceforge.jpcap.net.*;
 

class StPacketHandlerTCP {
   
      public StPacketHandlerTCP (Packet packet) {	 	 
            try {
	     	if(packet instanceof TCPPacket) {
	     		TCPPacket tcpPacket = (TCPPacket)packet;
                        StAplicationLayer.TCPPacket(tcpPacket);
	     	}
	     
	    } catch( Exception e ) {
	      e.printStackTrace();
	    }
      }
}
