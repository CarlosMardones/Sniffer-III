package dominio.export.xml_propio;

import net.sourceforge.jpcap.capture.PacketListener;
import net.sourceforge.jpcap.net.*;

/**
 * Clase XmlHandler. Sinopsis: Gestor de paquetes Gestiona de los paquetes por
 * protocolos Desfragmenta la trama por protocolos determinando la capa (hijo)
 * correspondiente del protocolo determinado.
 * 
 * @author Leonardo García & JoseRamón Gutierrez
 * @version 2.0
 * 
 * @see net.sourceforge.jpcap.net;
 */

public class XmlPacketHandler implements PacketListener  {

	//Referencia a la clase padre crear XML.
	private CrearXMLOffline RCrearXML;

	/**
	 * Constructor de la clase XmlHandler
	 * 
	 * @param RCrearXML
	 *            Referencia a la clase padre CrearXML
	 */
	public XmlPacketHandler(CrearXMLOffline RCrearXML) {
		this.RCrearXML = RCrearXML;
	}

	/**
	 * packetarrived.
	 * 
	 * Funcion que gestiona cada paquete a su llegada. Gestionando los
	 * protocolos del paquete ( cabecera y datos )
	 * 
	 * @param packet
	 */
	public void packetArrived(Packet packet) {

		try {
			RCrearXML.xmlContadorHijo();
			// protocolo Ether ( nivel de enlace )
			if (packet instanceof EthernetPacket) {
				EthernetPacket ethernetPacket = (EthernetPacket) packet;
				RCrearXML.Etherlayer(ethernetPacket);
			}
			// protocolo ARP
			if (packet instanceof ARPPacket) {
				ARPPacket arpPacket = (ARPPacket) packet;
				RCrearXML.ARPlayer(arpPacket);

			}
			// Protocolo IP ( nivel de Red )
			if (packet instanceof IPPacket) {
				IPPacket ipPacket = (IPPacket) packet;
				RCrearXML.IPlayer(ipPacket);
			}

			// protocolo ICMP
			if (packet instanceof ICMPPacket) {
				ICMPPacket icmpPacket = (ICMPPacket) packet;
				RCrearXML.ICMPlayer(icmpPacket);
			}
			// protocolo IGMP
			if (packet instanceof IGMPPacket) {
				IGMPPacket igmpPacket = (IGMPPacket) packet;
				RCrearXML.IGMPlayer(igmpPacket);
			}
			//  protocolo TCP
			if (packet instanceof TCPPacket) {
				TCPPacket tcpPacket = (TCPPacket) packet;
				RCrearXML.TCPlayer(tcpPacket);

			}
			// protocolo UDP
			if (packet instanceof UDPPacket) {
				UDPPacket udpPacket = (UDPPacket) packet;
				RCrearXML.UDPlayer(udpPacket);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
} // fin de clase
