package dominio.export.xml;

import net.sourceforge.jpcap.net.*;
import org.jdom.*;

/**
 * Clase TCPflags. Sinopsis: Clase que añade al documento XML que estamos
 * generando, los datos de los indicadores de control del protocolo TCP ( Nivel
 * Transporte ).
 * 
 * @author Leonardo García & JoseRamón Gutierrez
 * @version 2.0
 * 
 * @see net.sourceforge.jpcap.net
 * @see org.jdom
 */

public class TCPflags extends Element {

	/**
	 * Constructor de la clase TCPflags
	 * 
	 * Indicadores de control (6 bits): Cada uno de los bits recibe el nombre de
	 * indicador y cuando está a 1 indica una función específica del protocolo.
	 * 
	 * URG: Hay datos urgentes y en el campo "puntero urgente" se indica el
	 * número de datos urgentes que hay en el segmento. ACK: Indica que tiene
	 * significado el número que hay almacenado en el campo "número de
	 * confirmación". PSH: Sirve para invocar la función de carga. RST: Sirve
	 * para hacer un reset de la conexión. SYN: Sirve para sincronizar los
	 * números de secuencia. FIN: Sirve para indicar que el emisor no tiene mas
	 * datos para enviar.
	 * 
	 * 
	 * @param tcpPacket
	 * 
	 * TCP protocol packet Añade la cabecera y datos TCP.
	 */
	public TCPflags(TCPPacket tcpPacket) {
		super("TCPflags");
		//  con addContent Añado hijos a TCP_Protocol
		// con setText le doy un contenido a cada etiqueta.

		// si existe Añade etiqueta URG
		if (tcpPacket.isUrg()) {
			addContent(new Element("URG").setText(Integer.toHexString(tcpPacket
					.getUrgentPointer())));
		}
		//  si existe Añade etiqueta ACK
		if (tcpPacket.isAck()) {
			addContent(new Element("ACK").setText(Long.toHexString(tcpPacket
					.getAcknowledgmentNumber())));
		}
		//  si existe Añade etiqueta PSH
		if (tcpPacket.isPsh()) {
			addContent(new Element("PSH"));
		}
		//  si existe Añade etiqueta RST
		if (tcpPacket.isRst()) {
			addContent(new Element("RST"));
		}
		//  si existe Añade etiqueta SYN
		if (tcpPacket.isSyn()) {
			addContent(new Element("SYN").setText(Long.toHexString(tcpPacket
					.getSequenceNumber())));
		}
		//  si existe Añade etiqueta FIN
		if (tcpPacket.isFin()) {
			addContent(new Element("FIN"));
		}
	}
}