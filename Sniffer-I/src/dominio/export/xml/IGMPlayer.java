package dominio.export.xml;

import net.sourceforge.jpcap.net.*;

import org.jdom.*;

/**
 * Clase IGMPlayer. Sinopsis: Clase que a�ade al documento XML que estamos
 * generando, los datos del protocolo IGMP ( Internet Group Management Protocol ).
 * 
 * @author Leonardo Garc�a & JoseRam�n Gutierrez
 * @version 2.0
 * 
 * @see net.sourceforge.jpcap.net
 * @see org.jdom
 */
public class IGMPlayer extends Element {
	/**
	 * sourceAddress => Direccion ip origen
	 */
	protected String sourceAddress;

	/**
	 * destinationAddress => Direccion IP destino.
	 */
	protected String destinationAddress;

	/**
	 * Constructor de la clase IGMPlayer
	 * 
	 * @param igmpPacket
	 * 
	 * An IGMP packet. A�ade a�ade la cabecera y datos IGMP.
	 */
	public IGMPlayer(IGMPPacket igmpPacket) {
		super("IGMP_Protocol");
		// con addContent A�ado hijos a IGMP_Protocol
		// con setText le doy un contenido a cada etiqueta.
		try {
			// tipo de mensaje IGMP
			addContent(new Element("Type").setText(IGMPMessage
					.getDescription(igmpPacket.getMessageType())));
			// direccion de grupo
			addContent(new Element("Group_address").setText(igmpPacket
					.getGroupAddress()));
			// tiempo maximo de respuesta
			addContent(new Element("max_response_time").setText(String
					.valueOf(igmpPacket.getMaxResponseTime())));
			// suma de comprobaci�n
			addContent(new Element("Checksum").setText(String
					.valueOf(igmpPacket.getChecksum())));

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}