package dominio.export.xml_PcapLib;

import dominio.pcapDumper.analyzer.POP3Analyzer;
import dominio.pcapDumper.analyzer.JDPacketAnalyzer;
import jpcap.packet.Packet;

/**
 * Clase ARPlayer. Sinopsis: Clase que a�ade al documento XML que estamos
 * generando, los datos del protocolo ARP.
 * 
 * @author Leonardo Garc�a & Jose Ram�n Gutierrez
 * @version 2.0
 * 
 * @see jpcap.packet.Packet
 * @see dominio.pcapDumper.analyzer.POP3Analyzer
 * @see dominio.pcapDumper.analyzer.JDPacketAnalyzer
 */
public class POP3layer{
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
	public POP3layer() {
		packetAnalyzer= new POP3Analyzer();
	}
	
	/**
	 * Analiza la informaci�n del paquete y la transforma en XML.
	 * El resultado con el metodo get.
	 * 
	 * @param packet
	 * 			POP3 packet
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