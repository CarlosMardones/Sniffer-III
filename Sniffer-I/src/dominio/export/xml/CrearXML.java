package dominio.export.xml;

import java.io.*;
import org.jdom.*;
import org.jdom.output.*;

import net.sourceforge.jpcap.client.*;
import net.sourceforge.jpcap.net.*;

/**
 * Clase CrearXML.
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

public class CrearXML {

	/**
	 * paquete => A network packet.( Solo paquetes Ethernet son soportados ).
	 */
	protected Packet paquete;

	/**
	 * root elemento padre de el documento XML.( comtendra todos los paquetes )
	 */
	protected static Element root;

	/**
	 * packet elemento hijo de root ( contendra todos los datos de cada paquete )
	 */
	protected static Element packet;

	/**
	 * history historico de paquetes
	 */
	private CaptureHistory history;

	/**
	 * fichero donde se guarda el xml
	 */
	private File fichero;

	/**
	 * pk_xml_file tipo
	 */
	private int pk_xml_file;

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

	public CrearXML(CaptureHistory history, File fichero, int pk_xml_file) { //estoy
		// pasando
		// como
		// parametros
		// la
		// cola
		// de
		// packets
		this.history = history;
		this.fichero = fichero;
		this.pk_xml_file = pk_xml_file;
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
		boolean fin = false;
		int part = 1;
		int i = 0;
		// nombre del fichero tipo XML
		if (pk_xml_file == -1) {
			// Un solo fichero.

			// nombre del fichero tipo XML
			nombrefichero = fichero.getAbsoluteFile() + ".xml";
			// Creamos un elemento root
			Element root = new Element("Captura");
			XmlHandler GestorPaquetes = new XmlHandler(this);
			System.out.println("tamaño:" + String.valueOf(history.size()));
			for (i = 0; i < history.size(); i++) {
				//Creamos un hijo para el root
				packet = new Element("Packet").setAttribute("Numero", (String
						.valueOf(i)));
				// Cogemos el paquete de su posicion correspondiente del
				// historial
				paquete = history.get(i);
				// llamada al gestor de paquetes
				GestorPaquetes.packetarrived(paquete);
				// añado el hijo a root
				root.addContent(packet);
			}

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

		} else {

			while (!fin) {
				nombrefichero = fichero.getAbsoluteFile()
						+ String.valueOf(part) + ".xml";
				// Creamos un elemento root
				Element root = new Element("Captura");
				XmlHandler GestorPaquetes = new XmlHandler(this);
				System.out.println("tamaño:" + String.valueOf(history.size()));
				int cont_pk = 0;
				while (cont_pk <= pk_xml_file && i < history.size()) {
					//Creamos un hijo para el root
					packet = new Element("Packet").setAttribute("Numero",
							(String.valueOf(i)));
					// Cogemos el paquete de su posicion correspondiente del
					// historial
					paquete = history.get(i);
					// llamada al gestor de paquetes
					GestorPaquetes.packetarrived(paquete);
					// añado el hijo a root
					root.addContent(packet);
					cont_pk++;
					i++;
				}
				if (i == history.size()) {
					fin = true;
				} else {
					fin = false;
				}

				//Creamos el documento
				Document doc = new Document(root);

				try {

					XMLOutputter out = new XMLOutputter(Format
							.getPrettyFormat());
					FileOutputStream file = new FileOutputStream(nombrefichero);
					out.output(doc, file);
					file.flush();
					file.close();

				} catch (Exception e) {
					e.printStackTrace();
				}
				part++;
			}// end_while
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
	public void Etherlayer(EthernetPacket ethernetPacket) {
		Etherlayer ethernetlayer = new Etherlayer(ethernetPacket);
		packet.addContent(ethernetlayer);
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
	}

} // fin dela clase

