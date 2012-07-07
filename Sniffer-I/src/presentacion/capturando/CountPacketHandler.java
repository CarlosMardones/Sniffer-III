package presentacion.capturando;

import presentacion.capturando.Fcaptura;
import net.sourceforge.jpcap.net.*;
import net.sourceforge.jpcap.capture.*;

/**
 * Clase que almacena el numero de paquetes de cada tipo, para luego pasarlos en
 * la construccion de una grafica *
 */

class CountPacketHandler implements PacketListener {

	public int ethernet = 0;

	public int ip = 0;

	public int arp = 0;

	public int icmp = 0;

	public int igmp = 0;

	public int tcp = 0;

	public int udp = 0;

	public int pktotal = 0;

	//packetArrived ===> metodo abstracto de PacketListener
	public int i = 0; //numero de paquetes totales

	public int contpk = 0;

	public String strNumero;

	/**
	 * 
	 * @uml.associationEnd multiplicity="(0 1)"
	 */
	private Fcaptura RFcaptura;

	public CountPacketHandler(Fcaptura RFcaptura) {
		// EL constructor lo hace una sola vez.
		this.RFcaptura = RFcaptura;
		//RFcaptura.venpadre.getCountPacketHandler(this);
		pktotal = 0;
		contpk = 0;
	}

	public void packetArrived(Packet packet) {

		try {

			strNumero = "(" + Integer.toString(pktotal) + ") ";

			if ((packet instanceof ICMPPacket)
					|| (packet instanceof IGMPPacket)
					|| (packet instanceof TCPPacket)
					|| (packet instanceof UDPPacket)) {
				// Paquetes ICMP
				if (packet instanceof ICMPPacket) {
					ICMPPacket icmpPacket = (ICMPPacket) packet;
					icmp++;
					RFcaptura.pkether(strNumero + icmpPacket.toString());
				}

				// Paquet IGMP
				if (packet instanceof IGMPPacket) {
					IGMPPacket igmpPacket = (IGMPPacket) packet;
					igmp++;
					RFcaptura.pkIGMP(strNumero + igmpPacket.toString());
				}

				if (packet instanceof TCPPacket) {
					//Paketes TCP
					TCPPacket tcpPacket = (TCPPacket) packet;
					tcp++;
					RFcaptura.pkTCP(strNumero + tcpPacket.toString());
				}

				if (packet instanceof UDPPacket) {
					//Paketes UDP
					UDPPacket udpPacket = (UDPPacket) packet;
					udp++;
					RFcaptura.pkUDP(strNumero + udpPacket.toString());
				}

			} else {
				if ((packet instanceof ARPPacket)
						|| (packet instanceof IPPacket)) {
					if (packet instanceof ARPPacket) {
						// Paketes ARP
						ARPPacket arpPacket = (ARPPacket) packet;
						arp++;
						RFcaptura.pkARP(strNumero + arpPacket.toString());
					}
					// Paquetes IP
					if (packet instanceof IPPacket) {
						IPPacket ipPacket = (IPPacket) packet;
						ip++;
						RFcaptura.pkIP(strNumero + ipPacket.toString());
					}
				} else {
					if (packet instanceof EthernetPacket) {
						EthernetPacket ethernetPacket = (EthernetPacket) packet;
						ethernet++;
						RFcaptura
								.pkether(strNumero + ethernetPacket.toString());
					}
				}
			}

			RFcaptura.pkrecibidos(pktotal);

		} catch (Exception e) {
			e.printStackTrace();
		}
		i++;
		pktotal++;
		contpk++;
	} // end packet arrived

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

	public void eraseContParcial() {
		contpk = 0;
		RFcaptura.borrarTextArea();
	}

}// Fin de la clase

