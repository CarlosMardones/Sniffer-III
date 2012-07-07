package dominio.export.xml;

import net.sourceforge.jpcap.net.*;
import org.jdom.*;

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
public class Etherlayer extends Element {
	/**
	 * sourceAddress => Direccion MAC Origen
	 */
	protected String sourceAddress;

	/**
	 * destinationAddress => Direccion MAC destino
	 */

	protected String destinationAddress;

	/**
	 * Constructor de la clase Etherlayer
	 * 
	 * @param ethernetPacket
	 * 
	 * An ethernet packet. El paquete contiene la informacion de cabecera y
	 * datos del nivel de enlace encapsulado en un paquete Ethernet.
	 */
	public Etherlayer(EthernetPacket ethernetPacket) {
		super("Ethernet_Layer");
		// con addContent Añado hijos a Address_Resolution_Protocol
		// con setText le doy un contenido a cada etiqueta.
		sourceAddress = ethernetPacket.getSourceHwAddress();
		destinationAddress = ethernetPacket.getDestinationHwAddress();
		// add direccion MAC origen
		addContent(new Element("Source_Hardware_Address")
				.setText(sourceAddress));
		// add direccion MAC destino
		addContent(new Element("Destination_Hardware_Address")
				.setText(destinationAddress));
		// add tipo de protocolo Ethernet
		addContent(new Element("Type").setText(String.valueOf(ethernetPacket
				.getEthernetProtocol())));
		// add tiempo de llegada.
		addContent(new Element("Time_arrived").setText(ethernetPacket
				.getTimeval().toString()));
	}
}