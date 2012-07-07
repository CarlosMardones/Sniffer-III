package dominio.export.xml_propio;

import net.sourceforge.jpcap.net.*;

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

public class TCPlayer{
	
	private String xmlStr;
	
	/**
	 * Constructor de la clase IPlayer
	 * 
	 * @param tcpPacket
	 * 
	 * TCP protocol packet Añade la cabecera y datos TCP.
	 */
	public TCPlayer(TCPPacket tcpPacket) {
		xmlStr="      <TCP_Protocol>" + System.getProperty("line.separator");
		xmlStr=xmlStr + "        <Port_source>" + String.valueOf(tcpPacket.getDestinationPort()) + "</Port_source>" + System.getProperty("line.separator");
		xmlStr=xmlStr + "        <Destination_port>" + IPPort.getName(tcpPacket.getDestinationPort()) + "</Destination_port>" + System.getProperty("line.separator");
		xmlStr=xmlStr + "        <Sequence_Number>" + String.valueOf(tcpPacket.getAcknowledgmentNumber()) + "</Sequence_Number>" + System.getProperty("line.separator");
		xmlStr=xmlStr + "        <Acknowledgment_Number>" + String.valueOf(tcpPacket.getAcknowledgmentNumber()) + "</Acknowledgment_Number>" + System.getProperty("line.separator");
		xmlStr=xmlStr + "        <Header_Length>" + String.valueOf(tcpPacket.getTCPHeaderLength()) + "</Header_Length>" + System.getProperty("line.separator");
		xmlStr=xmlStr + "        <TCPflags>" + System.getProperty("line.separator");
		if (tcpPacket.isUrg()) {
			xmlStr=xmlStr + "          <URG>" + Integer.toHexString(tcpPacket.getUrgentPointer()) + "</URG>" + System.getProperty("line.separator");
		}
		if (tcpPacket.isAck()) {
			xmlStr=xmlStr + "          <ACK>" + Long.toHexString(tcpPacket.getAcknowledgmentNumber()) + "</ACK>" + System.getProperty("line.separator");
		}
		if (tcpPacket.isPsh()) {
			xmlStr=xmlStr + "          <PSH>" + "</PSH>" + System.getProperty("line.separator");
		}
		if (tcpPacket.isRst()) {
			xmlStr=xmlStr + "          <RST>" + "</RST>" + System.getProperty("line.separator");
		}
		if (tcpPacket.isSyn()) {
			xmlStr=xmlStr + "          <SYN>" + Long.toHexString(tcpPacket.getSequenceNumber()) + "</SYN>" + System.getProperty("line.separator");
		}
		if (tcpPacket.isFin()) {
			xmlStr=xmlStr + "          <FIN>" + "</FIN>" + System.getProperty("line.separator");
		}
		xmlStr=xmlStr + "        </TCPflags>" + System.getProperty("line.separator");
		xmlStr=xmlStr + "        <Window_Size>" + tcpPacket.getWindowSize() + "</Window_Size>" + System.getProperty("line.separator");
		xmlStr=xmlStr + "        <Checksum>" + String.valueOf(tcpPacket.getChecksum()) + "</Checksum>" + System.getProperty("line.separator");
		xmlStr=xmlStr + "        <UrgentPointer>" + tcpPacket.getUrgentPointer() + "</UrgentPointer>" + System.getProperty("line.separator");
		xmlStr=xmlStr + "      </TCP_Protocol>";
	}
	
	public String getStr(){
		return xmlStr;
	}
}