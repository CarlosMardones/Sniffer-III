package dominio.export.xml_propio;

import net.sourceforge.jpcap.net.*;

/**
 * Clase IPlayer. Sinopsis: Clase que añade al documento XML que estamos
 * generando, los datos del protocolo IP ( Nivel Red ).
 * 
 * @author Leonardo García & JoseRamón Gutierrez
 * @version 2.0
 * 
 * @see net.sourceforge.jpcap.net
 * @see org.jdom
 */

public class IPlayer{
	/**
	 * Direccion Ip origen
	 */
	protected String sourceAddress;

	/**
	 * Direccion Ip destino
	 */
	protected String destinationAddress;

	private String xmlStr;
	
	/**
	 * Constructor de la clase IPlayer
	 * 
	 * @param ipPacket
	 * 
	 * IP protocol packet Añade añade la cabecera y datos IP.
	 */
	public IPlayer(IPPacket ipPacket) {
		sourceAddress = ipPacket.getSourceAddress();
		destinationAddress = ipPacket.getDestinationAddress();
		xmlStr="      <IP_Protocol>" + System.getProperty("line.separator");
		xmlStr=xmlStr + "        <Source_IP_Address>" + sourceAddress + "</Source_IP_Address>" + System.getProperty("line.separator");
		xmlStr=xmlStr + "        <Destination_IP_Address>" + destinationAddress + "</Destination_IP_Address>" + System.getProperty("line.separator");
		xmlStr=xmlStr + "        <Headler_Length>" + String.valueOf(ipPacket.getHeaderLength()) + "</Headler_Length>" + System.getProperty("line.separator");
		xmlStr=xmlStr + "        <Version>" + String.valueOf(ipPacket.getVersion()) + "</Version>" + System.getProperty("line.separator");
		xmlStr=xmlStr + "        <Type>" + String.valueOf(ipPacket.getTypeOfService()) + "</Type>" + System.getProperty("line.separator");
		xmlStr=xmlStr + "        <TTL>" + String.valueOf(ipPacket.getTimeToLive()) + "</TTL>" + System.getProperty("line.separator");
		xmlStr=xmlStr + "        <Protocol>" + String.valueOf(ipPacket.getIPProtocol()) + "</Protocol>" + System.getProperty("line.separator");
		xmlStr=xmlStr + "        <Protocol_Description>" + String.valueOf(ipPacket.getIPProtocol()) + "</Protocol_Description>" + System.getProperty("line.separator");
		xmlStr=xmlStr + "        <ID>" + String.valueOf(ipPacket.getId()) + "</ID>" + System.getProperty("line.separator");
		xmlStr=xmlStr + "        <Length>" + String.valueOf(ipPacket.getLength()) + "</Length>" + System.getProperty("line.separator");
		xmlStr=xmlStr + "        <Flags>" + String.valueOf(ipPacket.getFragmentFlags()) + "</Flags>" + System.getProperty("line.separator");
		xmlStr=xmlStr + "        <Offset>" + String.valueOf(ipPacket.getFragmentOffset()) + "</Offset>" + System.getProperty("line.separator");
		xmlStr=xmlStr + "        <Check_Sum>" + String.valueOf(ipPacket.getChecksum()) + "</Check_Sum>" + System.getProperty("line.separator");
		xmlStr=xmlStr + "      </IP_Protocol>";
	}
	
	public String getStr(){
		return xmlStr;
	}
}