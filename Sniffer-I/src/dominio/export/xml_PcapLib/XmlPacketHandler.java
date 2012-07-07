package dominio.export.xml_PcapLib;

import dominio.pcapDumper.analyzer.FTPAnalyzer;
import dominio.pcapDumper.analyzer.HTTPAnalyzer;
import dominio.pcapDumper.analyzer.POP3Analyzer;
import dominio.pcapDumper.analyzer.SMTPAnalyzer;
import dominio.pcapDumper.analyzer.SSHAnalyzer;
import dominio.pcapDumper.analyzer.TelnetAnalyzer;
import jpcap.PacketReceiver;
import jpcap.packet.*;


/**
 * Clase XmlHandler. Sinopsis: Gestor de los paquetes por
 * protocolos Desfragmenta la trama por protocolos determinando la capa (hijo)
 * correspondiente del protocolo determinado.
 * 
 * @author Leonardo García & JoseRamón Gutierrez
 * @version 2.0
 * 
 * @see jpcap.PacketReceiver
 * @see dominio.export.xml_PcapLib.CrearXMLOffline
 */

public class XmlPacketHandler implements PacketReceiver  {
	/** * Referencia a la clase padre crear XML. */
	private CrearXMLOffline RCrearXML;
	//private JDPacketAnalyzer packetAnalyzer;
	/** * Analizador de tipo FTP. */
	private FTPAnalyzer FTPAnalyzer;
	/** * Analizador de tipo HTTP. */
	private HTTPAnalyzer HTTPAnalyzer;
	/** * Analizador de tipo POP3. */
	private POP3Analyzer POP3Analyzer;
	/** * Analizador de tipo SMTP. */
	private SMTPAnalyzer SMTPAnalyzer;
	/** * Analizador de tipo SSH. */
	private SSHAnalyzer SSHAnalyzer;
	/** * Analizador de tipo Telnet. */
	private TelnetAnalyzer TelnetAnalyzer;

	/**
	 * Constructor de la clase
	 * 
	 * @param RCrearXML
	 *            Referencia a la clase padre CrearXML
	 */
	public XmlPacketHandler(CrearXMLOffline RCrearXML) {
		this.RCrearXML = RCrearXML;
		establecerLayers();
	}
	
	/**
	 * Establece los layers de Transporte
	 */
	private void establecerLayers(){
		FTPAnalyzer = new FTPAnalyzer();
		HTTPAnalyzer = new HTTPAnalyzer();
		POP3Analyzer = new POP3Analyzer();
		SMTPAnalyzer = new SMTPAnalyzer();;
		SSHAnalyzer = new SSHAnalyzer();
		TelnetAnalyzer = new TelnetAnalyzer();
	}

	/**
	 * packetarrived.
	 * 
	 * Funcion que gestiona cada paquete a su llegada. Gestionando los
	 * protocolos del paquete ( cabecera y datos )
	 * 
	 * @param packet
	 */
	public void receivePacket(Packet packet) {

		try {
			RCrearXML.xmlContadorHijo();
			if (packet instanceof Packet) {
				RCrearXML.PacketInformation(packet);
			}
		
			if (packet.datalink instanceof EthernetPacket) {
				RCrearXML.Etherlayer(packet);
			}
				
			if (packet instanceof ARPPacket) {
				RCrearXML.ARPlayer(packet);
			}

			if (packet instanceof IPPacket) {
				RCrearXML.IPlayer(packet);
			}

			if (packet instanceof ICMPPacket) {
				RCrearXML.ICMPlayer(packet);
			}
		
			if (packet instanceof TCPPacket) {
				RCrearXML.TCPlayer(packet);
			}
		
			if (packet instanceof UDPPacket) {
				RCrearXML.UDPlayer(packet);
			}
			
			//packetAnalyzer=FTPAnalyzer;
			if (FTPAnalyzer.isAnalyzable(packet)){
				RCrearXML.FTPlayer(packet);
			}
		
			//packetAnalyzer= HTTPAnalyzer;
			if (HTTPAnalyzer.isAnalyzable(packet)){
				RCrearXML.HTTPlayer(packet);
			}

			//packetAnalyzer= POP3Analyzer;
			if (POP3Analyzer.isAnalyzable(packet)){
				RCrearXML.POP3layer(packet);
			}

			//packetAnalyzer= SMTPAnalyzer;
			if (SMTPAnalyzer.isAnalyzable(packet)){
				RCrearXML.SMTPlayer(packet);
			}
	
			//packetAnalyzer = SSHAnalyzer;
			if (SSHAnalyzer.isAnalyzable(packet)){
				RCrearXML.SSHlayer(packet);
			}

			//packetAnalyzer= TelnetAnalyzer;
			if(TelnetAnalyzer.isAnalyzable(packet)){
				RCrearXML.Telnetlayer(packet);
			}

		} catch (Exception e) {
			System.out.println("En el paquete: " + RCrearXML.getContador());	
			e.printStackTrace();
		}
	}
} // fin de clase
