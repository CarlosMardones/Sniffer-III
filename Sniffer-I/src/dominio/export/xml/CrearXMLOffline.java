package dominio.export.xml;

import java.io.*;
import org.jdom.*;
import org.jdom.output.*;


import net.sourceforge.jpcap.net.*;

/**
 * Clase CrearXML en Offline.
 * 
 * Sinopsis: Clase que crea un fichero en formato XML con la descripcion
 * detallada de las tramas de datos capturadas.
 * 
 * @author Leonardo García & JoseRamón Gutierrez
 * @version 2.0
 * 
 * @see net.sourceforge.jpcap.net
 * @see net.sourceforge.jpcap.client
 * @see org.jdom
 */

public class CrearXMLOffline {
	
	/**
	 * paquete => A network packet.( Solo paquetes Ethernet son soportados ).
	 */
	protected Packet paquete;
	
	/**
	 * root elemento padre de el documento XML.( comtendra todos los paquetes )
	 */
	protected Element root;
	
	/**
	 * packet elemento hijo de root ( contendra todos los datos de cada paquete )
	 */
	protected Element packet;
	
	
	/**
	 * fichero donde se guarda el xml
	 */
	//private File fichero;
	private String fichero;
	
	/**
	 * Contador de paquetes que llegan
	 */
	private static long contador;
	
	
	/**
	 * Constructor de la clase CrearXML
	 * 
	 * 
	 * @param history
	 *            Estructura donde se almacenan todos los paquetes de la captura
	 *            The history acts as a FIFO.
	 * @param fichero
	 *            Nombre del fichero XML
	 *  
	 */
	
	public CrearXMLOffline(String fichero) { //estoy
		this.fichero = fichero;
		// Creamos un elemento root
		root = new Element("Captura");
		contador=0;
	}
	
	public Element getRoot(){
		if (root == null) root = new Element("Captura");
		return root;
	}
	
	/**
	 * Generar
	 * 
	 * Funcion encargada de generar el fichero XML creando la estructura del
	 * mismo. La funcion utiliza el historial de paquetes ( history ).
	 * 
	 * El gestor de paquetes coge cada uno de los paquetes del historial,y los
	 * desencapsula por protocolos.
	 * 
	 * Una vez añadidos los datos de las distintas capas guardamos el fichero
	 * con nombre de fichero ( fichero ) en formato standard XML
	 *  
	 */
	public void Generar() {
		String nombrefichero;
		
		// nombre del fichero tipo XML
		nombrefichero = fichero;
		//Creamos el documento
		Document doc = new Document(root);
		
		try {
			
			XMLOutputter out = new XMLOutputter(Format.getPrettyFormat());
			FileOutputStream file = new FileOutputStream(nombrefichero);
			out.output(doc, file);
			file.flush();
			file.close();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	/**
	 * Etherlayer
	 * 
	 * Funcion que añade a documento los datos de la capa de enlace (
	 * Ethernetlayer )
	 * 
	 * @param ethernetPacket
	 */
	public void xmlContadorHijo() {
		//Creamos un hijo para el root
		packet = new Element("Packet").setAttribute("Numero", (String
				.valueOf(contador)));
		contador++;
	}
	
	public void addPacket(){
		root.addContent(packet);
	}
	
	/**
	 * Etherlayer
	 * 
	 * Funcion que añade a documento los datos de la capa de enlace (
	 * Ethernetlayer )
	 * 
	 * @param ethernetPacket
	 */
	public void Etherlayer(EthernetPacket ethernetPacket) {
		Etherlayer ethernetlayer = new Etherlayer(ethernetPacket);
		packet.addContent(ethernetlayer);
		//root.addContent(packet);
	}
	
	/**
	 * ARPlayer
	 * 
	 * Funcion que añade a documento los datos del protocolo ARP
	 * 
	 * @param arpPacket
	 */
	public void ARPlayer(ARPPacket arpPacket) {
		ARPlayer arplayer = new ARPlayer(arpPacket);
		packet.addContent(arplayer);
		//root.addContent(packet);
	}
	
	/**
	 * IPPlayer
	 * 
	 * Funcion que añade a documento los datos del protocolo IP
	 * 
	 * @param ipPacket
	 */
	public void IPlayer(IPPacket ipPacket) {
		IPlayer iplayer = new IPlayer(ipPacket);
		packet.addContent(iplayer);
		//root.addContent(packet);
	}
	
	/**
	 * ICMPlayer
	 * 
	 * Funcion que añade a documento los datos del protocolo ICMP
	 * 
	 * @param icmpPacket
	 */
	public void ICMPlayer(ICMPPacket icmpPacket) {
		ICMPlayer icmplayer = new ICMPlayer(icmpPacket);
		packet.addContent(icmplayer);
		//root.addContent(packet);
	}
	
	/**
	 * IGMPlayer
	 * 
	 * Funcion que añade a documento los datos del protocolo IGMP
	 * 
	 * @param igmpPacket
	 */
	public void IGMPlayer(IGMPPacket igmpPacket) {
		IGMPlayer igmplayer = new IGMPlayer(igmpPacket);
		packet.addContent(igmplayer);
		//root.addContent(packet);
	}
	
	/**
	 * TCPlayer
	 * 
	 * Funcion que añade a documento los datos del protocolo TCP
	 * 
	 * @param tcpPacket
	 */
	public void TCPlayer(TCPPacket tcpPacket) {
		TCPlayer tcplayer = new TCPlayer(tcpPacket);
		packet.addContent(tcplayer);
		//root.addContent(packet);
	}
	
	/**
	 * UDPlayer
	 * 
	 * Funcion que añade a documento los datos del protocolo UDP
	 * 
	 * @param udpPacket
	 */
	public void UDPlayer(UDPPacket udpPacket) {
		UDPlayer udplayer = new UDPlayer(udpPacket);
		packet.addContent(udplayer);
		//root.addContent(packet);
	}
	
} // fin dela clase

