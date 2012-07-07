package dominio.export.xml;

import net.sourceforge.jpcap.net.*;
import org.jdom.*;

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

public class ICMPlayer extends Element {
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

	/**
	 * Constructor de la clase ICMPlayer
	 * 
	 * @param icmpPacket
	 * 
	 * An ICMP packet. Añade la cabecera y datos ICMP.
	 */
	public ICMPlayer(ICMPPacket icmpPacket) {
		super("ICMP_Protocol");
		// con addContent Añado hijos a ICMP_Protocol
		// con setText le doy un contenido a cada etiqueta.
		sourceAddress = icmpPacket.getSourceAddress();
		destinationAddress = icmpPacket.getDestinationAddress();

		try {
			// tipo de mensaje ICMP.
			addContent(new Element("Type").setText(ICMPMessage
					.getDescription(icmpPacket.getMessageCode())));
			addContent(new Element("Code").setText(String.valueOf(icmpPacket
					.getMessageCode())));
			addContent(new Element("Checksum").setText(String
					.valueOf(icmpPacket.getChecksum())));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}