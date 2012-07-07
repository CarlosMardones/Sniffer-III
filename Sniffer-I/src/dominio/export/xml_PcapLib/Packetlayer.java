package dominio.export.xml_PcapLib;

import dominio.pcapDumper.analyzer.PacketAnalyzer;
import dominio.pcapDumper.analyzer.JDPacketAnalyzer;
import jpcap.packet.Packet;

/**
 * Clase Etherlayer. Sinopsis: Clase que añade al documento XML que estamos
 * generando, los datos del protocolo Ethernet ( Nivel de Enlace ).
 * 
 * @author Leonardo García & Jose Ramón Gutierrez
 * @version 2.0
 * 
 * @see jpcap.packet.Packet
 * @see dominio.pcapDumper.analyzer.PacketAnalyzer
 * @see dominio.pcapDumper.analyzer.JDPacketAnalyzer
 */
public class Packetlayer {
	/** * Contiene el xml generado */
	private String xmlStr;
	/** * Analizador de paquetes */
	private JDPacketAnalyzer packetAnalyzer;
	/** * Nombre del protocolo */
	private String protocolName;
	/** * Variables que contiene */
	private String [] valueNames; 
	
	/**
	 * Constructor de la clase
	 */
	public Packetlayer() {
		packetAnalyzer= new PacketAnalyzer();
	}
	
	/**
	 * Analiza la información del paquete y la transforma en XML.
	 * El resultado con el metodo get.
	 * 
	 * @param packet
	 * 			Packet information
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
