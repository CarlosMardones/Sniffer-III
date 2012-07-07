package dominio.export.xml_propio;

import net.sourceforge.jpcap.net.*;

/**
 * Clase ICMPlayer. Sinopsis: Clase que añade al documento XML que estamos
 * generando, los datos del protocolo ICMP (Protocolo de Mensajes de Control).
 * 
 * @author Leonardo García & JoseRamón Gutierrez
 * @version 2.0
 * 
 * @see net.sourceforge.jpcap.net
 * @see org.jdom
 */

public class ICMPlayer{
	/**
	 * sourceAddress => Direccion ip origen.
	 */
	protected String sourceAddress;

	/**
	 * destinationAddress => Direccion IP destino.
	 */
	protected String destinationAddress;

	/**
	 * Paquete datos
	 */
	protected String dataPacket;

	private String xmlStr;
	
	/**
	 * Constructor de la clase ICMPlayer
	 * 
	 * @param icmpPacket
	 * 
	 * An ICMP packet. Añade la cabecera y datos ICMP.
	 */
	public ICMPlayer(ICMPPacket icmpPacket) {
		sourceAddress = icmpPacket.getSourceAddress();
		destinationAddress = icmpPacket.getDestinationAddress();
		xmlStr="      <ICMP_Protocol>" + System.getProperty("line.separator");
		xmlStr=xmlStr + "        <Source_ICMP_Addres>" + sourceAddress + "</Source_ICMP_Addres>" + System.getProperty("line.separator");
		xmlStr=xmlStr + "        <Destination_ICMP_Addres>" + destinationAddress + "</Destination_ICMP_Addres>" + System.getProperty("line.separator");
		xmlStr=xmlStr + "        <Type>" + ICMPMessage.getDescription(icmpPacket.getMessageCode()) + "</Type>" + System.getProperty("line.separator");
		xmlStr=xmlStr + "        <Code>" + String.valueOf(icmpPacket.getMessageCode()) + "</Code>" + System.getProperty("line.separator");
		xmlStr=xmlStr + "        <Checksum>" + String.valueOf(icmpPacket.getChecksum()) + "</Checksum>" + System.getProperty("line.separator");
		xmlStr=xmlStr + "      </ICMP_Protocol>";
	}
	
	public String getStr(){
		return xmlStr;
	}
}