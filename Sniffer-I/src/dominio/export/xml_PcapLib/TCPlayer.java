package dominio.export.xml_PcapLib;

import dominio.pcapDumper.analyzer.TCPAnalyzer;
import dominio.pcapDumper.analyzer.JDPacketAnalyzer;
import jpcap.packet.Packet;

/**
 * Clase TCPlayer. Sinopsis: Clase que añade al documento XML que estamos
 * generando, los datos del protocolo TCP ( Nivel Transporte ).
 * 
 * @author Leonardo García & Jose Ramón Gutierrez
 * @version 2.0
 * 
 * @see jpcap.packet.Packet
 * @see dominio.pcapDumper.analyzer.SSHAnalyzer
 * @see dominio.pcapDumper.analyzer.JDPacketAnalyzer
 */
public class TCPlayer{
	/** * Contiene el xml generado */
	private String xmlStr;
	/** * Analizador de paquetes */
	private JDPacketAnalyzer packetAnalyzer;
	/** * Nombre del protocolo */
	private String protocolName;
	/** * Variables que contiene */
	private String [] valueNames; 
	
	/**
	 * Constructor de la clase.
	 */
	public TCPlayer() {
		packetAnalyzer= new TCPAnalyzer();
	}
	
	/**
	 * Analiza la información del paquete y la transforma en XML.
	 * El resultado con el metodo get.
	 * 
	 * @param packet
	 * 			TCP packet
	 */
	public void analizar(Packet packet){
		int i;
		String strNameField, strCont;

		packetAnalyzer.analyze(packet);
		protocolName = packetAnalyzer.getProtocolName();
		protocolName = protocolName.replaceAll(" ","_");
		valueNames = packetAnalyzer.getValueNames();
		
		xmlStr="      <" + protocolName +  ">" + System.getProperty("line.separator");
		if(valueNames != null){
			for (i = 0 ;i < valueNames.length; i++){
				strNameField = valueNames[i].toString();
				strCont = String.valueOf(packetAnalyzer.getValue(strNameField));
				strNameField = strNameField.replaceAll(" ","_");
				xmlStr=xmlStr + "        <" + strNameField + ">" + strCont + "</" + strNameField + ">" + System.getProperty("line.separator");
			}
		}
		xmlStr=xmlStr + "      </" + protocolName +  ">";
	}
	
	/**
	 * Devuelve el xml generado.
	 * @return cadena de xml
	 */
	public String getStr(){
		return xmlStr;
	}
}