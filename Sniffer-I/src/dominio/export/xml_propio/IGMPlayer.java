package dominio.export.xml_propio;

import net.sourceforge.jpcap.net.*;


/**
 * Clase IGMPlayer. Sinopsis: Clase que añade al documento XML que estamos
 * generando, los datos del protocolo IGMP ( Internet Group Management Protocol ).
 * 
 * @author Leonardo García & JoseRamón Gutierrez
 * @version 2.0
 * 
 * @see net.sourceforge.jpcap.net
 * @see org.jdom
 */
public class IGMPlayer{
	/**
	 * sourceAddress => Direccion ip origen
	 */
	protected String sourceAddress;

	/**
	 * destinationAddress => Direccion IP destino.
	 */
	protected String destinationAddress;

	private String xmlStr;
	
	/**
	 * Constructor de la clase IGMPlayer
	 * 
	 * @param igmpPacket
	 * 
	 * An IGMP packet. Añade añade la cabecera y datos IGMP.
	 */
	public IGMPlayer(IGMPPacket igmpPacket) {
		xmlStr="      <IGMP_Protocol>" + System.getProperty("ICMP_Protocol.separator");
		xmlStr=xmlStr + "        <Type>" + ICMPMessage.getDescription(igmpPacket.getMessageType()) + "</Type>" + System.getProperty("line.separator");
		xmlStr=xmlStr + "        <Group_address>" + igmpPacket.getGroupAddress() + "</Group_address>" + System.getProperty("line.separator");
		xmlStr=xmlStr + "        <max_response_time>" + String.valueOf(igmpPacket.getMaxResponseTime()) + "</max_response_time>" + System.getProperty("line.separator");
		xmlStr=xmlStr + "        <Checksum>" + String.valueOf(igmpPacket.getChecksum()) + "</Checksum>" + System.getProperty("line.separator");
		xmlStr=xmlStr + "      </IGMP_Protocol>";			
	}
	
	public String getStr(){
		return xmlStr;
	}
}