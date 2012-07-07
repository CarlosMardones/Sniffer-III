package dominio.export.xml;

import net.sourceforge.jpcap.net.*;
import org.jdom.*;

/**
 * Clase TCPflags. Sinopsis: Clase que a�ade al documento XML que estamos
 * generando, los datos de los indicadores de control del protocolo TCP ( Nivel
 * Transporte ).
 * 
 * @author Leonardo Garc�a & JoseRam�n Gutierrez
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
	 * indicador y cuando est� a 1 indica una funci�n espec�fica del protocolo.
	 * 
	 * URG: Hay datos urgentes y en el campo "puntero urgente" se indica el
	 * n�mero de datos urgentes que hay en el segmento. ACK: Indica que tiene
	 * significado el n�mero que hay almacenado en el campo "n�mero de
	 * confirmaci�n". PSH: Sirve para invocar la funci�n de carga. RST: Sirve
	 * para hacer un reset de la conexi�n. SYN: Sirve para sincronizar los
	 * n�meros de secuencia. FIN: Sirve para indicar que el emisor no tiene mas
	 * datos para enviar.
	 * 
	 * 
	 * @param tcpPacket
	 * 
	 * TCP protocol packet A�ade la cabecera y datos TCP.
	 */
	public TCPflags(TCPPacket tcpPacket) {
		super("TCPflags");
		//  con addContent A�ado hijos a TCP_Protocol
		// con setText le doy un contenido a cada etiqueta.

		// si existe A�ade etiqueta URG
		if (tcpPacket.isUrg()) {
			addContent(new Element("URG").setText(Integer.toHexString(tcpPacket
					.getUrgentPointer())));
		}
		//  si existe A�ade etiqueta ACK
		if (tcpPacket.isAck()) {
			addContent(new Element("ACK").setText(Long.toHexString(tcpPacket
					.getAcknowledgmentNumber())));
		}
		//  si existe A�ade etiqueta PSH
		if (tcpPacket.isPsh()) {
			addContent(new Element("PSH"));
		}
		//  si existe A�ade etiqueta RST
		if (tcpPacket.isRst()) {
			addContent(new Element("RST"));
		}
		//  si existe A�ade etiqueta SYN
		if (tcpPacket.isSyn()) {
			addContent(new Element("SYN").setText(Long.toHexString(tcpPacket
					.getSequenceNumber())));
		}
		//  si existe A�ade etiqueta FIN
		if (tcpPacket.isFin()) {
			addContent(new Element("FIN"));
		}
	}
}