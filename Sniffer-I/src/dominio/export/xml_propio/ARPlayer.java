package dominio.export.xml_propio;

import net.sourceforge.jpcap.net.*;

/**
 * Clase ARPlayer. Sinopsis: Clase que añade al documento XML que estamos
 * generando, los datos del protocolo ARP.
 * 
 * @author Leonardo García & JoseRamón Gutierrez
 * @version 2.0
 * 
 * @see org.jdom
 */

public class ARPlayer{

	/**
	 * sourceAddress => Direccion Origen.
	 */
	protected String sourceAddress;

	/**
	 * destinationAddress => Direccion Destino.
	 */
	protected String destinationAddress;

	private String xmlStr;
	/**
	 * Constructor de la clase ARPlayer
	 * 
	 * 
	 * @param arpPacket
	 *            ARP protocol packet. el paquete contiene la informacion de
	 *            cabecera y datos ARP.
	 */

	public ARPlayer(ARPPacket arpPacket) {
		xmlStr="      <Address_Resolution_Protocol>" + System.getProperty("line.separator");
		xmlStr=xmlStr + "        <Operation_ARP>" + (arpPacket.getOperation() == ARPFields.ARP_OP_REQ_CODE ? "Request" : "Reply") + "</Operation_ARP>" + System.getProperty("line.separator");
		xmlStr=xmlStr + "        <Source_Proto_Address>" + arpPacket.getSourceProtoAddress() + "</Source_Proto_Address>" + System.getProperty("line.separator");
		xmlStr=xmlStr + "        <Hardware_source_address>" + arpPacket.getSourceHwAddress() + "</Hardware_source_address>" + System.getProperty("line.separator");
		xmlStr=xmlStr + "        <Destination_Proto_Address>" + arpPacket.getDestinationProtoAddress() + "</Destination_Proto_Address>" + System.getProperty("line.separator");
		xmlStr=xmlStr + "        <Hardware_destination_address>" + arpPacket.getDestinationHwAddress() + "</Hardware_destination_address>" + System.getProperty("line.separator");
		xmlStr=xmlStr + "        <Operation_code>" + String.valueOf(arpPacket.getOperation()) + "</Operation_code>" + System.getProperty("line.separator");
		xmlStr=xmlStr + "      </Address_Resolution_Protocol>";
	}
	public String getStr(){
		return xmlStr;
	}
}