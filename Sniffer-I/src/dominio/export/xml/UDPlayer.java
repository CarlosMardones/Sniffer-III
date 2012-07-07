package dominio.export.xml;

import net.sourceforge.jpcap.net.*;

import org.jdom.*;

/**
 * Clase UDPlayer. Sinopsis: Clase que añade al documento XML que estamos
 * generando, los datos del protocolo UDP ( Nivel Transporte ).
 * 
 * @author Leonardo García & JoseRamón Gutierrez
 * @version 2.0
 * 
 * @see net.sourceforge.jpcap.net
 * @see org.jdom
 */
public class UDPlayer extends Element {

	/**
	 * Constructor de la clase UDPlayer
	 * 
	 * @param udpPacket
	 * 
	 * UDP protocol packet Añade la cabecera y datos UDP.
	 */
	public UDPlayer(UDPPacket udpPacket) {
		super("UDP_Protocol");
		try {
			// add puerto origen
			addContent(new Element("UDP_Port_source").setText(String
					.valueOf(udpPacket.getSourcePort())));
			addContent(new Element("UDP_Port_source_name").setText(IPPort
					.getName(udpPacket.getSourcePort())));

			// add puerto destino

			addContent(new Element("UDP_Destination_port").setText(String
					.valueOf(udpPacket.getDestinationPort())));
			addContent(new Element("UDP_Destination_port_name").setText(IPPort
					.getName(udpPacket.getDestinationPort())));
			// add longitud
			addContent(new Element("Length").setText(String.valueOf(udpPacket
					.getLength())));
			// add suma de comprobacion
			addContent(new Element("Checksum").setText(String.valueOf(udpPacket
					.getChecksum())));

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}