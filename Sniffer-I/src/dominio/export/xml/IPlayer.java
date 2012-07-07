package dominio.export.xml;

import net.sourceforge.jpcap.net.*;
import org.jdom.*;

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

public class IPlayer extends Element {
	/**
	 * Direccion Ip origen
	 */
	protected String sourceAddress;

	/**
	 * Direccion Ip destino
	 */
	protected String destinationAddress;

	/**
	 * Constructor de la clase IPlayer
	 * 
	 * @param ipPacket
	 * 
	 * IP protocol packet Añade añade la cabecera y datos IP.
	 */
	public IPlayer(IPPacket ipPacket) {
		super("IP_Protocol");

		// con addContent Añado hijos a IP_Protocol
		// con setText le doy un contenido a cada etiqueta.
		sourceAddress = ipPacket.getSourceAddress();
		destinationAddress = ipPacket.getDestinationAddress();
		try {
			// add direccion Ip origen
			addContent(new Element("Source_IP_Address").setText(sourceAddress));
			// add direccion Ip destino
			addContent(new Element("Destination_IP_Address")
					.setText(destinationAddress));
			// add longitud de la cabecera del datagrama ( en bytes ).
			addContent(new Element("Headler_Length").setText(String
					.valueOf(ipPacket.getHeaderLength())));
			// add version Ip utilizada
			addContent(new Element("Version").setText(String.valueOf(ipPacket
					.getVersion())));
			// add tipo de servicio ( Especifica la calidad del servicio
			// solicitado por la capa.
			addContent(new Element("Type").setText(String.valueOf(ipPacket
					.getTypeOfService())));
			// add time to live ( Numero maximo de saltos que puede sufrir el
			// datagrama.
			addContent(new Element("TTL").setText(String.valueOf(ipPacket
					.getTimeToLive())));
			// add protocolo de nivel superior dentro del datagrama IP.
			addContent(new Element("Protocol").setText(String.valueOf(ipPacket
					.getIPProtocol())));
			// add Descripcion del protocolo.
			addContent(new Element("Protocol_Description").setText(IPProtocol
					.getDescription(ipPacket.getIPProtocol())));
			// add Identificacion del datagrama
			// asignado por el emisor y utilizado en el rensamblado de paquetes.
			addContent(new Element("ID").setText(String.valueOf(ipPacket
					.getId())));
			// add longitud total del datagrama incluida cabecera.
			addContent(new Element("Length").setText(String.valueOf(ipPacket
					.getLength())));
			// add fragmentation flags.(NU DF MF)
			addContent(new Element("Flags").setText(String.valueOf(ipPacket
					.getFragmentFlags())));
			// add fragmentation offset.
			// Indica la posicion que ocupa el primer caracter del campo de
			// datos
			// en el datagrama original.
			addContent(new Element("Offset").setText(String.valueOf(ipPacket
					.getFragmentOffset())));
			// add header checksum suma de comprobacion que afecta a la
			// cabecera.
			addContent(new Element("Check_Sum").setText(String.valueOf(ipPacket
					.getChecksum())));

		} catch (Exception e) {
			e.printStackTrace();
		}

	}
}