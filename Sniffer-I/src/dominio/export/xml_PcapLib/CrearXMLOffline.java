package dominio.export.xml_PcapLib;

import java.io.*;

import servicioAccesoDatos.FabricaAccesoDatos;
import servicioAccesoDatos.FabricaAccesoDatosIF;
import servicioAccesoDatos.FachadaFichero;


import jpcap.packet.*;

/**
 * Clase CrearXML en Offline.
 * 
 * Sinopsis: Clase que crea un fichero en formato XML con la descripcion
 * detallada de las tramas de datos capturadas.
 * 
 * @author Leonardo Garc�a & JoseRam�n Gutierrez
 * @version 2.0
 * 
 * @see servicioAccesoDatos.FabricaAccesoDatos
 * @see servicioAccesoDatos.FabricaAccesoDatosIF
 * @see servicioAccesoDatos.FachadaFichero
 * @see jpcap.packet.Packet
 */

public class CrearXMLOffline {
	/** * fichero donde se guarda el xml */
	private String fichero;
	/** * Contador de paquetes que llegan */
	private static long contador;
	/** * Escritor de ficheros de caracteres. */
	public FileWriter writer;
	/** * Buffer de escritura. */
	public BufferedWriter buffer;
	/** * Escritor de ficheros de salida. */
	public PrintWriter output;
	/** * Interface de Fabrica de acceso a datos. */
	private FabricaAccesoDatosIF fabrica;
	/** * Clase abtracta de fichero. */
	private FachadaFichero f;
	/** * Analizador de tipo pachet. */
	private Packetlayer packetlayer;
	/** * Analizador de tipo Ethernet. */
	private Etherlayer ethernetlayer;
	/** * Analizador de tipo ARP. */
	private ARPlayer arplayer;
	/** * Analizador de tipo IP. */
	private IPlayer iplayer;
	/** * Analizador de tipo ICMP. */
	private ICMPlayer icmplayer;
	/** * Analizador de tipo TCP. */
	private	TCPlayer tcplayer;
	/** * Analizador de tipo UDP. */
	private UDPlayer udplayer;
	/** * Analizador de tipo FTP. */
	private FTPlayer ftplayer;
	/** * Analizador de tipo HTTP. */
	private HTTPlayer httplayer;
	/** * Analizador de tipo POP3. */
	private POP3layer pop3layer;
	/** * Analizador de tipo SMTP. */
	private SMTPlayer smtplayer;
	/** * Analizador de tipo SSH. */
	private SSHlayer sshlayer;
	/** * Analizador de tipo Telnet. */
	private Telnetlayer telnetlayer;
		
	/**
	 * Constructor de la clase.
	 * 
	 * @param fichero
	 *            Nombre del fichero XML que se va a escribir
	 * 
	 * @see servicioAccesoDatos.FabricaAccesoDatosIF
	 * @see servicioAccesoDatos.FachadaFichero
	 */
	public CrearXMLOffline(String fichero) { //estoy
		this.setFichero(fichero);
		// Creamos un elemento root
		//root = new Element("Captura");
		contador=0;
		try {
			output = new PrintWriter(new BufferedWriter(new FileWriter(fichero)));
		} catch (Exception e) {
			e.printStackTrace();
		}
		fabrica=new FabricaAccesoDatos();
		f=fabrica.creaFachadaFichero("exportacion", fichero );
		
		writeToFile("<?xml version=\"1.0\" encoding=\"UTF-8\" ?>");
		writeToFile("<Captura>");
		
		establecerLayers();
	}
	
	/**
	 * A�ade al documento la cabecera del paquete con 
	 * el numero de paquete
	 */
	public void xmlContadorHijo() {
		if (contador > 0){
			writeToFile("  </Packet>");
		}
		writeToFile("  <Packet Numero=\"" + contador + "\">");
		//System.out.println("P num=> " + contador);
		contador++;
	}
	
	/**
	 * Establece todos los layer de paquetes
	 */
	private void establecerLayers(){
		packetlayer = new Packetlayer();
		ethernetlayer = new Etherlayer();
		arplayer = new ARPlayer();
		iplayer = new IPlayer();
		icmplayer = new ICMPlayer();
		tcplayer = new TCPlayer();
		udplayer = new UDPlayer();
		ftplayer = new FTPlayer();
		httplayer = new HTTPlayer();
		pop3layer = new POP3layer();
		smtplayer = new SMTPlayer();
		sshlayer = new SSHlayer();
		telnetlayer = new Telnetlayer();

	}
	/**
	 * PacketInformation
	 * 
	 * A�ade a documento la informaci�n del paquete
	 * 
	 * @param packet 
	 * 			paquete recibido
	 */
	public void PacketInformation(Packet packet) {
		//Packetlayer packetlayer = new Packetlayer(packet);
		packetlayer.analizar(packet);
		writeToFile(packetlayer.getStr());
	}	
	
	/**
	 * Etherlayer
	 * 
	 * Funcion que a�ade a documento los datos de la capa de enlace (
	 * Ethernetlayer )
	 * 
	 * @param packet
	 */
	public void Etherlayer(Packet packet) {
		//Etherlayer ethernetlayer = new Etherlayer(packet);
		ethernetlayer.analizar(packet);
		writeToFile(ethernetlayer.getStr());
	}
	
	/**
	 * A�ade a documento la informaci�n del ARP
	 * 
	 * @param packet 
	 * 			paquete recibido
	 */
	public void ARPlayer(Packet packet) {
		//ARPlayer arplayer = new ARPlayer(packet);
		arplayer.analizar(packet);
		writeToFile(arplayer.getStr());
	}
	
	/**
	 * A�ade a documento la informaci�n de IP
	 * 
	 * @param packet 
	 * 			paquete recibido
	 */
	public void IPlayer(Packet packet) {
		//IPlayer iplayer = new IPlayer(packet);
		iplayer.analizar(packet);
		writeToFile(iplayer.getStr());
	}

	/**
	 * A�ade a documento la informaci�n de ICMP
	 * 
	 * @param packet 
	 * 			paquete recibido
	 */
	public void ICMPlayer(Packet packet) {
		//ICMPlayer icmplayer = new ICMPlayer(packet);
		icmplayer.analizar(packet);
		writeToFile(icmplayer.getStr());
	}
	
	/**
	 * A�ade a documento la informaci�n de TCP
	 * 
	 * @param packet 
	 * 			paquete recibido
	 */
	public void TCPlayer(Packet packet) {
		//TCPlayer tcplayer = new TCPlayer(packet);
		tcplayer.analizar(packet);
		writeToFile(tcplayer.getStr());
	}
	
	/**
	 * A�ade a documento la informaci�n de UDP
	 * 
	 * @param packet 
	 * 			paquete recibido
	 */
	public void UDPlayer(Packet packet) {
		//UDPlayer udplayer = new UDPlayer(packet);
		udplayer.analizar(packet);
		writeToFile(udplayer.getStr());
	}
	
	/**
	 * A�ade a documento la informaci�n de FTP
	 * 
	 * @param packet 
	 * 			paquete recibido
	 */
	public void FTPlayer(Packet packet) {
		//FTPlayer ftplayer = new FTPlayer(packet);
		ftplayer.analizar(packet);
		writeToFile(ftplayer.getStr());
	}
	
	
	/**
	 * A�ade a documento la informaci�n de HTTP
	 * 
	 * @param packet 
	 * 			paquete recibido
	 */
	public void HTTPlayer(Packet packet) {
		//HTTPlayer httplayer = new HTTPlayer(packet);
		httplayer.analizar(packet);
		writeToFile(httplayer.getStr());
	}
	
	/**
	 * A�ade a documento la informaci�n de POP3
	 * 
	 * @param packet 
	 * 			paquete recibido
	 */
	public void POP3layer(Packet packet) {
		//POP3layer pop3layer = new POP3layer(packet);
		pop3layer.analizar(packet);
		writeToFile(pop3layer.getStr());
	}
	
	/**
	 * A�ade a documento la informaci�n de SMTP
	 * 
	 * @param packet 
	 * 			paquete recibido
	 */
	public void SMTPlayer(Packet packet) {
		//SMTPlayer smtplayer = new SMTPlayer(packet);
		smtplayer.analizar(packet);
		writeToFile(smtplayer.getStr());
	}
	
	/**
	 * A�ade a documento la informaci�n de SSH
	 * 
	 * @param packet 
	 * 			paquete recibido
	 */
	public void SSHlayer(Packet packet) {
		//SSHlayer sshlayer = new SSHlayer(packet);
		sshlayer.analizar(packet);
		writeToFile(sshlayer.getStr());
	}
	
	/**
	 * A�ade a documento la informaci�n de Telnet
	 * 
	 * @param packet 
	 * 			paquete recibido
	 */
	public void Telnetlayer(Packet packet) {
		//Telnetlayer telnetlayer = new Telnetlayer(packet);
		telnetlayer.analizar(packet);
		writeToFile(telnetlayer.getStr());
	}
	
	/**
	 * Finaliza el fichero XML
	 *
	 */
	public void endFile(){
		try {
			//output.flush();
			writeToFile("  </Packet>");
			writeToFile("</Captura>");
			f.cerrar();
			//System.out.println("PAso antes de close file");
			output.close();
			//System.out.println("DEspues de close file");
		}
		catch(Exception ex) {
			ex.printStackTrace();
		}
	}
	/**
	 * Escribe en el fichero.
	 * @param aux
	 * 			cadena a escribir
	 * 
	 * @see servicioAccesoDatos.FachadaFichero
	 */
	private void writeToFile (String aux){
		try {
			//output.println(aux);
			f.escribir(aux);
		}
		catch(Exception e) {
			
		}
	}
	
	/**
	 * Devuelve el n�mero de paquetes procesados
	 * 
	 * @return n�mero de paquetes
	 */
	public long getContador(){
		return contador;
	}
	
	/**
	 * Establece el fichero donde se crea el XML.
	 * 
	 * @param fichero
	 * 			nombre
	 */
	public void setFichero(String fichero) {
		this.fichero = fichero;
	}

	/**
	 * Devuelve el fichero donde se crea el XML.
	 * 
	 * @return nombre
	 */
	public String getFichero() {
		return fichero;
	}
	
} // fin dela clase

