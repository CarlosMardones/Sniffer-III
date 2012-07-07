package dominio.export.xml_propio;

import net.sourceforge.jpcap.net.*;


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
public class UDPlayer {

	private String xmlStr;
	
	/**
	 * Constructor de la clase UDPlayer
	 * 
	 * @param udpPacket
	 * 
	 * UDP protocol packet Añade la cabecera y datos UDP.
	 */
	public UDPlayer(UDPPacket udpPacket) {
		xmlStr="      <UDP_Protocol>" + System.getProperty("line.separator");
		xmlStr=xmlStr + "        <UDP_Port_source>" + String.valueOf(udpPacket.getSourcePort()) + "</UDP_Port_source>" + System.getProperty("line.separator");
		xmlStr=xmlStr + "        <UDP_Port_source_name>" + IPPort.getName(udpPacket.getSourcePort()) + "</UDP_Port_source_name>" + System.getProperty("line.separator");
		xmlStr=xmlStr + "        <UDP_Destination_port>" + String.valueOf(udpPacket.getDestinationPort()) + "</UDP_Destination_port>" + System.getProperty("line.separator");
		xmlStr=xmlStr + "        <UDP_Destination_port_name>" + IPPort.getName(udpPacket.getDestinationPort()) + "</UDP_Destination_port_name>" + System.getProperty("line.separator");
		xmlStr=xmlStr + "        <Length>" + String.valueOf(udpPacket.getLength()) + "</Length>" + System.getProperty("line.separator");
		xmlStr=xmlStr + "        <Checksum>" + String.valueOf(udpPacket.getChecksum()) + "</Checksum>" + System.getProperty("line.separator");
		xmlStr=xmlStr + "      </UDP_Protocol>";
	}
	
	public String getStr(){
		return xmlStr;
	}
}