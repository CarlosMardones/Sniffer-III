package dominio.export.xml_propio;

import net.sourceforge.jpcap.net.*;

/**
 * Clase Etherlayer. Sinopsis: Clase que añade al documento XML que estamos
 * generando, los datos del protocolo Ethernet ( Nivel de Enlace ).
 * 
 * @author Leonardo García & JoseRamón Gutierrez
 * @version 2.0
 * 
 * @see net.sourceforge.jpcap.net.EthernetPacket
 * @see net.sourceforge.jpcap.net.EthernetProtocols
 * @see org.jdom
 */
public class Etherlayer {
	/**
	 * sourceAddress => Direccion MAC Origen
	 */
	protected String sourceAddress;

	/**
	 * destinationAddress => Direccion MAC destino
	 */

	protected String destinationAddress;

	private String xmlStr;
	
	
	/**
	 * Constructor de la clase Etherlayer
	 * 
	 * @param ethernetPacket
	 * 
	 * An ethernet packet. El paquete contiene la informacion de cabecera y
	 * datos del nivel de enlace encapsulado en un paquete Ethernet.
	 */
	public Etherlayer(EthernetPacket ethernetPacket) {
		sourceAddress = ethernetPacket.getSourceHwAddress();
		destinationAddress = ethernetPacket.getDestinationHwAddress();
		xmlStr="      <Ethernet_Layer>" + System.getProperty("line.separator");
		xmlStr=xmlStr + "        <Source_Hardware_Address>" + sourceAddress + "</Source_Hardware_Address>" + System.getProperty("line.separator");
		xmlStr=xmlStr + "        <Destination_Hardware_Address>" + destinationAddress + "</Destination_Hardware_Address>" + System.getProperty("line.separator");
		xmlStr=xmlStr + "        <Type>" + String.valueOf(ethernetPacket.getEthernetProtocol()) + "</Type>" + System.getProperty("line.separator");
		xmlStr=xmlStr + "        <Time_arrived>" +  ethernetPacket.getTimeval().toString() + "</Time_arrived>" + System.getProperty("line.separator");
		xmlStr=xmlStr + "      </Ethernet_Layer>";
	}
	
	public String getStr(){
		return xmlStr;
	}
}
