package dominio.export.xml;

import net.sourceforge.jpcap.net.*;

import org.jdom.*;

/**
 * Clase TCPlayer. Sinopsis: Clase que añade al documento XML que estamos
 * generando, los datos del protocolo TCP ( Nivel Transporte ).
 * 
 * @author Leonardo García & JoseRamón Gutierrez
 * @version 2.0
 * 
 * @see net.sourceforge.jpcap.net
 * @see org.jdom
 */

public class TCPlayer extends Element {
	/**
	 * Constructor de la clase IPlayer
	 * 
	 * @param tcpPacket
	 * 
	 * TCP protocol packet Añade la cabecera y datos TCP.
	 */
	public TCPlayer(TCPPacket tcpPacket) {
		super("TCP_Protocol");
		// nuevo objeto tcpflags ( para los indicadores de control del paquete )
		TCPflags tcpflags = new TCPflags(tcpPacket);
		// con addContent Añado hijos a TCP_Protocol
		// con setText le doy un contenido a cada etiqueta.
		try {
			// add numero de puerto origen.
			addContent(new Element("Port_source").setText(String
					.valueOf(tcpPacket.getDestinationPort())));
			// add numero de puerto destino.
			addContent(new Element("Destination_port").setText(IPPort
					.getName(tcpPacket.getDestinationPort())));
			// add numero desecuencia
			// Identifica el primer byte del campo de datos.
			addContent(new Element("Sequence_Number").setText(String
					.valueOf(tcpPacket.getAcknowledgmentNumber())));
			// add numero de confirmacion
			// El protocolo TCP utiliza la técnica de piggybacking para
			// reconocer los datos.
			addContent(new Element("Acknowledgment_Number").setText(String
					.valueOf(tcpPacket.getAcknowledgmentNumber())));
			// add longitud de la cabecera.
			addContent(new Element("Header_Length").setText(String
					.valueOf(tcpPacket.getTCPHeaderLength())));
			// add flags ( indicadores de control )
			addContent(tcpflags);
			// add tamaño de ventana.
			// Indica cuantos bytes tiene la ventana de transmisión del
			// protocolo de control
			// de flujo utilizando el mecanismo de ventanas deslizantes.
			addContent(new Element("Window_Size").setText(String
					.valueOf(tcpPacket.getWindowSize())));
			// add suma de control.
			addContent(new Element("Checksum").setText(String.valueOf(tcpPacket
					.getChecksum())));
			// add puntero urgente.
			addContent(new Element("UrgentPointer").setText(String
					.valueOf(tcpPacket.getUrgentPointer())));

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}