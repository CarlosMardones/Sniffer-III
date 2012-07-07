package dominio.pcapDumper;

import java.util.Vector;

import jpcap.PacketReceiver;
import jpcap.packet.*;


/**
 * Clase que almacena el numero de paquetes de cada tipo, para luego pasarlos en
 * la construccion de una gráfica
 * 
 * @see jpcap.PacketReceiver
 */

public class CountPacketHandler implements PacketReceiver {
	/** * Número de paquetes de tipo ethernet. */
	public int ethernet = 0;
	/** * Número de paquetes de tipo ip. */
	public int ip = 0;
	/** * Número de paquetes de tipo arp. */
	public int arp = 0;
	/** * Número de paquetes de tipo icmp. */
	public int icmp = 0;
	/** * Número de paquetes de tipo ifmp. */
	public int igmp = 0;
	/** * Número de paquetes de tipo tcp. */
	public int tcp = 0;
	/** * Número de paquetes de tipo udp. */
	public int udp = 0;
	/** * Número de paquetes totales. */
	public int pktotal = 0;
	/** * Número de paquetes totales antes de un reseteo . */
	//packetArrived ===> metodo abstracto de PacketListener
	public int i = 0; //numero de paquetes totales
	/** * Número de aquetes de tipo . */
	public int contpk = 0;
	/** * Vector de paquetes recibidos. */
	private Vector packets = new Vector();

	/**
	 * Constructor de la clase
	 *
	 */
	public CountPacketHandler() {
		pktotal = 0;
		contpk = 0;
	}
	/**
	 * Trata cada paquete recibido para hacer la estadistica de los mismos
	 * 
	 * @see jpcap.PacketReceiver
	 */
	public void receivePacket(Packet packet) {
		try {
			packets.addElement(packet);
			if ((packet instanceof ICMPPacket)
					|| (packet instanceof TCPPacket)
					|| (packet instanceof UDPPacket)) {
				// Paquetes ICMP
				if (packet instanceof ICMPPacket) {
					ICMPPacket icmpPacket = (ICMPPacket) packet;
					icmp++;
					//System.out.println(strNumero + icmpPacket.toString());
				}
				if (packet instanceof TCPPacket) {
					//Paketes TCP
					TCPPacket tcpPacket = (TCPPacket) packet;
					tcp++;
					//System.out.println(strNumero + tcpPacket.toString());
				}
				if (packet instanceof UDPPacket) {
					//Paketes UDP
					UDPPacket udpPacket = (UDPPacket) packet;
					udp++;
					//System.out.println(strNumero + udpPacket.toString());
				}
			} else {
				if ((packet instanceof ARPPacket)
						|| (packet instanceof IPPacket)) {
					if (packet instanceof ARPPacket) {
						// Paketes ARP
						ARPPacket arpPacket = (ARPPacket) packet;
						arp++;
						//System.out.println(strNumero + arpPacket.toString());
					}
					// Paquetes IP
					if (packet instanceof IPPacket) {
						IPPacket ipPacket = (IPPacket) packet;
						ip++;
						//System.out.println(strNumero + ipPacket.toString());
					}
				} else {
					if (packet.datalink instanceof EthernetPacket) {
						//EthernetPacket ethernetPacket = (EthernetPacket) packet;
						ethernet++;
						//RFcaptura.pkether(strNumero + ethernetPacket.toString()); ANtes
						//System.out.println(strNumero + packet.toString());
					}
				}
			}
			//RFcaptura.pkrecibidos(pktotal);
		} catch (Exception e) {
			e.printStackTrace();
		}
		i++;
		pktotal++;
		contpk++;
	} // end packet arrived

	/**
	 * Resetea la estadística de los paquetes capturados
	 */
	public void erasedates() {
		ethernet = 0;
		ip = 0;
		arp = 0;
		icmp = 0;
		igmp = 0;
		tcp = 0;
		udp = 0;
		i = 0;
	}

	/**
	 * Borra el contador parcial de paquetes
	 */
	public void eraseContParcial() {
		contpk = 0;
		//RFcaptura.borrarTextArea();
	}
	
	/**
	 * Devuelve el vector de paquetes
	 * 
	 * @return vector
	 */
	public Vector getPackets(){
		//Vector aux = new Vector();
		//aux = packets;
		//packets.clear();
		return packets;		
	}
	
	/**
	 * Borra el vector de paquetes, y reinicializa. Son los que se están siendo
	 * visualizados
	 *
	 */
	public void delPackets(){
		packets.clear();
		packets.removeAllElements();
	}

}// Fin de la clase

