package dominio.export.xml;

import net.sourceforge.jpcap.net.*;
import org.jdom.*;

/**
 * Clase ARPlayer. Sinopsis: Clase que añade al documento XML que estamos
 * generando, los datos del protocolo ARP.
 * 
 * @author Leonardo García & JoseRamón Gutierrez
 * @version 2.0
 * 
 * @see net.sourceforge.jpcap.net
 * @see org.jdom
 */

public class ARPlayer extends Element {

	/**
	 * sourceAddress => Direccion Origen.
	 */
	protected String sourceAddress;

	/**
	 * destinationAddress => Direccion Destino.
	 */
	protected String destinationAddress;

	/**
	 * Constructor de la clase ARPlayer
	 * 
	 * 
	 * @param arpPacket
	 *            ARP protocol packet. el paquete contiene la informacion de
	 *            cabecera y datos ARP.
	 */

	public ARPlayer(ARPPacket arpPacket) {
		super("Address_Resolution_Protocol");
		// con addContent Añado hijos a Address_Resolution_Protocol
		// con setText le doy un contenido a cada etiqueta.
		addContent(new Element("Operation_ARP").setText(arpPacket
				.getOperation() == ARPFields.ARP_OP_REQ_CODE ? "Request"
				: "Reply"));
		// Direccion IP origen
		addContent(new Element("Source_Proto_Address").setText(arpPacket
				.getSourceProtoAddress()));
		// Direccion MAC origen
		addContent(new Element("Hardware_source_address").setText(arpPacket
				.getSourceHwAddress()));
		// Direccion IP destino
		addContent(new Element("Destination_Proto_Address").setText(arpPacket
				.getDestinationProtoAddress()));
		// Direccion MAC destino
		addContent(new Element("Hardware_destination_address")
				.setText(arpPacket.getDestinationHwAddress()));
		// Codigo operacion
		addContent(new Element("Operation_code").setText(String
				.valueOf(arpPacket.getOperation())));
	}
}