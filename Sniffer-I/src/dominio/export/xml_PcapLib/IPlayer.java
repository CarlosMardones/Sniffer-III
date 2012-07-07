package dominio.export.xml_PcapLib;


import dominio.pcapDumper.analyzer.IPv4Analyzer;
import dominio.pcapDumper.analyzer.IPv6Analyzer;
import dominio.pcapDumper.analyzer.JDPacketAnalyzer;
import jpcap.packet.Packet;

/**
 * Clase IPlayer. Sinopsis: Clase que añade al documento XML que estamos
 * generando, los datos del protocolo IP ( Nivel Red ).
 * 
 * @author Leonardo García & Jose Ramón Gutierrez
 * @version 2.0
 * 
 * @see jpcap.packet.Packet
 * @see dominio.pcapDumper.analyzer.IPv4Analyzer
 * @see dominio.pcapDumper.analyzer.IPv6Analyzer
 * @see dominio.pcapDumper.analyzer.JDPacketAnalyzer
 */
public class IPlayer{
	/** * Contiene el xml generado */
	private String xmlStr;
	/** * Analizador de paquetes */
	private JDPacketAnalyzer packetAnalyzer;
	/** * Nombre del protocolo */
	private String protocolName;
	/** * Variables que contiene */
	private String [] valueNames; 
	
	/** * Analizador de paquetes */
	private JDPacketAnalyzer packetAnalyzerV4;
	/** * Analizador de paquetes */
	private JDPacketAnalyzer packetAnalyzerV6;
	
	/**
	 * Constructor de la clase.
	 */
	public IPlayer() {
		packetAnalyzerV4= new IPv4Analyzer();
		packetAnalyzerV6= new IPv6Analyzer();
	}
	
	/**
	 * Analiza la información del paquete y la transforma en XML.
	 * El resultado con el metodo get.
	 * 
	 * @param packet
	 * 			IP packet
	 */
	public void analizar(Packet packet){
		int i;
		String strNameField, strCont;
		if (packetAnalyzerV4.isAnalyzable(packet)){
			packetAnalyzer = packetAnalyzerV4;
			packetAnalyzer.analyze(packet);
		}
		else{
			packetAnalyzer = packetAnalyzerV6;
			packetAnalyzer.analyze(packet);
		}
		protocolName = packetAnalyzer.getProtocolName();
		protocolName = protocolName.replaceAll(" ","_");
		valueNames = packetAnalyzer.getValueNames();
		
		xmlStr="      <" + protocolName +  ">" + System.getProperty("line.separator");
		if(valueNames != null){
			for (i = 0 ;i < valueNames.length; i++){
				strNameField = valueNames[i].toString();
				strCont = String.valueOf(packetAnalyzer.getValue(strNameField));
				strNameField = strNameField.replaceAll(" ","_");
				strNameField = strNameField.replaceAll(":","");
				strNameField = strNameField.replaceAll("'","");
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