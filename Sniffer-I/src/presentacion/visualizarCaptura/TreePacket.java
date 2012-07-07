package presentacion.visualizarCaptura;

import java.awt.*;
import java.util.ArrayList;

import javax.swing.JTable;
import javax.swing.JTree;
import javax.swing.table.TableColumn;
import javax.swing.tree.*;

import dominio.pcapDumper.analyzer.*;
import dominio.preferences.preferencesBeanIdentificacion;
import dominio.preferences.identificacion.PrefIdentificacion;
import jpcap.packet.*;

import javax.swing.*;
import javax.swing.tree.TreeCellRenderer;

import servicioAccesoDatos.FachadaFicheroDirectorios;

/**
 * Árbol de tipo paquete que muestra los datos que lo contiene
 * 
 * @author Leonardo García & Jose Ramón Gutierrez & Carlos Mardones Muga
 * @version 3.0
 *  
 * @see DefaultMutableTreeNode
 * @see JTree
 * 
 */

public class TreePacket {
	/** * Nodo del árbol. */
	private static DefaultMutableTreeNode Paquete;
	/** * Arblo java. */
	private JTree tree;
	/** * Numero del tree*. */
	private static int numero;

	/**
	 *  Contructor por defecto de la clase.
	 *  
	 * @param numero que se establece al true
	 * @param paquete paquete
	 */
	public TreePacket(int numero, Packet paquete) {
		DefaultMutableTreeNode child;
		Paquete = new DefaultMutableTreeNode("Packet");
		child = new DefaultMutableTreeNode("Num :" + numero);
		Paquete.add(child);
		new TreeHandler(paquete);

		DefaultTreeModel modeloArbol = new DefaultTreeModel(Paquete);
		tree = new JTree(modeloArbol);
		tree.setCellRenderer(new MiRendererDeArbol());
	}

	/**
	 * Nodo de tipo packet
	 * 
	 * @param packet paquete
	 */
	static void treepk(Packet packet) {
		DefaultMutableTreeNode LayerP;
		DefaultMutableTreeNode childP;
		String protocolName;
		String valueNames [];
		String strAux;
		int i;
		JDPacketAnalyzer packetAnalyzer= new PacketAnalyzer();
		packetAnalyzer.analyze(packet);
		protocolName = packetAnalyzer.getProtocolName();
		valueNames = packetAnalyzer.getValueNames();
		if(valueNames != null){
			for (i = 0 ;i < valueNames.length; i++){
				strAux = valueNames[i].toString();
				LayerP = new DefaultMutableTreeNode(strAux
						+ " : " + String.valueOf(packetAnalyzer.getValue(strAux)));
				Paquete.add(LayerP);
			}
		}
	}

	/**
	 * Nodo de tipo ethernet
	 * 
	 * @param packet paquete
	 */
	static void treepkether(Packet packet) {
		DefaultMutableTreeNode LayerP;
		DefaultMutableTreeNode childP;
		String protocolName;
		String valueNames [];
		String strAux;
		int i;
		
		JDPacketAnalyzer packetAnalyzer= new EthernetAnalyzer();
		packetAnalyzer.analyze(packet);
		protocolName = packetAnalyzer.getProtocolName();
		valueNames = packetAnalyzer.getValueNames();
		
		LayerP = new DefaultMutableTreeNode(protocolName);
		Paquete.add(LayerP);
		if(valueNames != null){
			for (i = 0 ;i < valueNames.length; i++){
				strAux = valueNames[i].toString();
				childP = new DefaultMutableTreeNode(strAux
						+ " : " + String.valueOf(packetAnalyzer.getValue(strAux)));
				LayerP.add(childP);
			}
		}
	}

	/**
	 * Nodo de tipo ARP
	 * 
	 * @param packet paquete
	 */
	static void treepkARP(Packet packet) {
		DefaultMutableTreeNode LayerP;
		DefaultMutableTreeNode childP;
		String protocolName;
		String valueNames [];
		String strAux;
		int i;
		
		JDPacketAnalyzer packetAnalyzer= new ARPAnalyzer();
		packetAnalyzer.analyze(packet);
		protocolName = packetAnalyzer.getProtocolName();
		valueNames = packetAnalyzer.getValueNames();
		
		LayerP = new DefaultMutableTreeNode(protocolName);
		Paquete.add(LayerP);
		if(valueNames != null){
			for (i = 0 ;i < valueNames.length; i++){
				strAux = valueNames[i].toString();
				childP = new DefaultMutableTreeNode(strAux
						+ " : " + String.valueOf(packetAnalyzer.getValue(strAux)));
				LayerP.add(childP);
			}
		}
	}

	/**
	 * Nodo de tipo IP
	 * 
	 * @param packet paquete
	 */
	static void treepkIP(Packet packet) {
		DefaultMutableTreeNode LayerP;
		DefaultMutableTreeNode childP;
		String protocolName;
		String valueNames [];
		String strAux;
		int i;
		
		JDPacketAnalyzer packetAnalyzer;
		packetAnalyzer = new IPv4Analyzer();
		if(packetAnalyzer.isAnalyzable(packet))
			packetAnalyzer.analyze(packet);
		else{
			packetAnalyzer = new IPv6Analyzer();
			packetAnalyzer.analyze(packet);
		}

		protocolName = packetAnalyzer.getProtocolName();
		valueNames = packetAnalyzer.getValueNames();
		
		LayerP = new DefaultMutableTreeNode(protocolName);
		if(valueNames != null){
			for (i = 0 ;i < valueNames.length; i++){
				strAux = valueNames[i].toString();
				childP = new DefaultMutableTreeNode(strAux
						+ " : " + String.valueOf(packetAnalyzer.getValue(strAux)));
				LayerP.add(childP);
			}
		}
		Paquete.add(LayerP);
	}

	/**
	 * Nodo de tipo ICMP
	 * 
	 * @param packet paquete
	 */
	static void treepkICMP(Packet packet) {
		DefaultMutableTreeNode LayerP;
		DefaultMutableTreeNode childP;
		String protocolName;
		String valueNames [];
		String strAux;
		int i;
		
		JDPacketAnalyzer packetAnalyzer= new ICMPAnalyzer();
		packetAnalyzer.analyze(packet);
		protocolName = packetAnalyzer.getProtocolName();
		valueNames = packetAnalyzer.getValueNames();
		
		LayerP = new DefaultMutableTreeNode(protocolName);
		Paquete.add(LayerP);
		if(valueNames != null){
			for (i = 0 ;i < valueNames.length; i++){
				strAux = valueNames[i].toString();
				childP = new DefaultMutableTreeNode(strAux
						+ " : " + String.valueOf(packetAnalyzer.getValue(strAux)));
				LayerP.add(childP);
			}
		}
	}
/*
	static void treepkIGMP(IGMPPacket igmpPacket) {



	}
*/
	/**
	 * Nodo de tipo TCP
	 * 
	 * @param packet paquete
	 */
	static void treepkTCP(Packet packet) {
		DefaultMutableTreeNode LayerP;
		DefaultMutableTreeNode childP;
		String protocolName;
		String valueNames [];
		String strAux;
		int i;
		
		JDPacketAnalyzer packetAnalyzer= new TCPAnalyzer();
		packetAnalyzer.analyze(packet);
		protocolName = packetAnalyzer.getProtocolName();
		valueNames = packetAnalyzer.getValueNames();
		
		LayerP = new DefaultMutableTreeNode(protocolName);
		Paquete.add(LayerP);
		if(valueNames != null){
			for (i = 0 ;i < valueNames.length; i++){
				strAux = valueNames[i].toString();
				childP = new DefaultMutableTreeNode(strAux
						+ " : " + String.valueOf(packetAnalyzer.getValue(strAux)));
				LayerP.add(childP);
			}
		}
	}

	/**
	 * Nodo de tipo UDP
	 * 
	 * @param packet paquete
	 */
	static void treepkUDP(Packet packet) {
		DefaultMutableTreeNode LayerP;
		DefaultMutableTreeNode childP;
		String protocolName;
		String valueNames [];
		String strAux;
		int i;
		
		JDPacketAnalyzer packetAnalyzer= new UDPAnalyzer();
		packetAnalyzer.analyze(packet);
		protocolName = packetAnalyzer.getProtocolName();
		valueNames = packetAnalyzer.getValueNames();
		
		LayerP = new DefaultMutableTreeNode(protocolName);
		Paquete.add(LayerP);
		if(valueNames != null){
			for (i = 0 ;i < valueNames.length; i++){
				strAux = valueNames[i].toString();
				childP = new DefaultMutableTreeNode(strAux
						+ " : " + String.valueOf(packetAnalyzer.getValue(strAux)));
				LayerP.add(childP);
			}
		}


	}
	
	/**
	 * Nodo de tipo FTP
	 * 
	 * @param packet paquete
	 */
	static void treepkFTP(Packet packet) {
		DefaultMutableTreeNode LayerP;
		DefaultMutableTreeNode childP;
		String protocolName;
		String valueNames [];
		String strAux;
		int i;
		String aux [][] = new String [7][2];
		
		JDPacketAnalyzer packetAnalyzer= new FTPAnalyzer();
		packetAnalyzer.analyze(packet);
		protocolName = packetAnalyzer.getProtocolName();
		valueNames = packetAnalyzer.getValueNames();
		
		if(packet.data.length > 0 && valueNames == null){
			byte auxConver [];
			aux[0][0]="Type";
			auxConver = new byte [1];
			auxConver[0]=packet.data[0];
			aux[0][1]=String.valueOf(byteArrayToInt(auxConver));
			aux[1][0]="Info Count";
			auxConver = new byte [3];
			auxConver[0]=packet.data[1];
			auxConver[1]=packet.data[2];
			auxConver[2]=packet.data[3];
			aux[1][1]=String.valueOf(byteArrayToInt(auxConver));
			aux[2][0]="Null";
			aux[2][1]="nulo";
			aux[3][0]="Seq #";
			auxConver = new byte [2];
			auxConver[0]=packet.data[5];
			auxConver[1]=packet.data[6];
			aux[3][1]=String.valueOf(byteArrayToInt(auxConver));
			aux[4][0]="Null";
			aux[4][1]="nulo";
			aux[5][0]="Filler count";
			auxConver = new byte [1];
			auxConver[0]=packet.data[8];
			aux[5][1]=String.valueOf(byteArrayToInt(auxConver));
			aux[6][0]="Data";
			aux[6][1]="";
			for(int j=9;j<packet.data.length;j++){
				aux[6][1]+=String.valueOf((char)packet.data[j]);
			}			
		}
		
		LayerP = new DefaultMutableTreeNode(protocolName);
		Paquete.add(LayerP);
		if(valueNames != null){
			for (i = 0; i < valueNames.length; i++) {
				strAux = valueNames[i].toString();
				childP = new DefaultMutableTreeNode(strAux + " : "
						+ String.valueOf(packetAnalyzer.getValue(strAux)));
				LayerP.add(childP);
			}
		}
		
		if(aux != null){
			for (i = 0; i < aux.length; i++) {
				strAux = aux[i][0];
				childP = new DefaultMutableTreeNode(strAux + " : "
						+ aux[i][1]);
				LayerP.add(childP);
			}
		}


	}

	/**
	 * Clase que convierte un array de but a su valor entero.
	 * @param b
	 * @return
	 */
	public static final int byteArrayToInt(byte [] b) {
		if(b.length == 1){
			return (b[0] & 0xFF);
		}
		if(b.length == 2){
			return ((b[0] & 0xFF) << 8)
            + (b[1] & 0xFF);
		}
		if(b.length == 3){
			return ((b[0] & 0xFF) << 16)
            + ((b[1] & 0xFF) << 8)
            + (b[2] & 0xFF);
		}
		if(b.length == 4){
			return (b[0] << 24)
            + ((b[1] & 0xFF) << 16)
            + ((b[2] & 0xFF) << 8)
            + (b[3] & 0xFF);
		}
		return 0;
        
	}

	
	/**
	 * Nodo de tipo HTTP
	 * 
	 * @param packet paquete
	 */
	static void treepkHTTP(Packet packet) {
		DefaultMutableTreeNode LayerP;
		DefaultMutableTreeNode childP;
		String protocolName;
		String valueNames [];
		String strAux;
		int i;
		
		JDPacketAnalyzer packetAnalyzer= new HTTPAnalyzer();
		packetAnalyzer.analyze(packet);
		protocolName = packetAnalyzer.getProtocolName();
		valueNames = packetAnalyzer.getValueNames();
		
		LayerP = new DefaultMutableTreeNode(protocolName);
		Paquete.add(LayerP);
		if(valueNames != null){
			for (i = 0; i < valueNames.length; i++) {
				strAux = valueNames[i].toString();
				//childP = new DefaultMutableTreeNode(strAux + " : "
				//	+ String.valueOf(packetAnalyzer.getValue(strAux)));
				//LayerP.add(childP);
			}
		}


	}

	/**
	 * Nodo de tipo POP3
	 * 
	 * @param packet paquete
	 */
	static void treepkPOP3(Packet packet) {
		DefaultMutableTreeNode LayerP;
		DefaultMutableTreeNode childP;
		String protocolName;
		String valueNames [];
		String strAux;
		int i;
		
		JDPacketAnalyzer packetAnalyzer= new POP3Analyzer();
		packetAnalyzer.analyze(packet);
		protocolName = packetAnalyzer.getProtocolName();
		valueNames = packetAnalyzer.getValueNames();
		
		LayerP = new DefaultMutableTreeNode(protocolName);
		Paquete.add(LayerP);
		if(valueNames != null){
			for (i = 0; i < valueNames.length; i++) {
				strAux = valueNames[i].toString();
				childP = new DefaultMutableTreeNode(strAux + " : "
						+ String.valueOf(packetAnalyzer.getValue(strAux)));
				LayerP.add(childP);
			}
		}


	}
	
	/**
	 * Nodo de tipo SMPT
	 * 
	 * @param packet paquete
	 */
	static void treepkSMTP(Packet packet) {
		DefaultMutableTreeNode LayerP;
		DefaultMutableTreeNode childP;
		String protocolName;
		String valueNames [];
		String strAux;
		int i;
		
		JDPacketAnalyzer packetAnalyzer= new SMTPAnalyzer();
		packetAnalyzer.analyze(packet);
		protocolName = packetAnalyzer.getProtocolName();
		valueNames = packetAnalyzer.getValueNames();
		
		LayerP = new DefaultMutableTreeNode(protocolName);
		Paquete.add(LayerP);
		if(valueNames != null){
			for (i = 0; i < valueNames.length; i++) {
				strAux = valueNames[i].toString();
				childP = new DefaultMutableTreeNode(strAux + " : "
						+ String.valueOf(packetAnalyzer.getValue(strAux)));
				LayerP.add(childP);
			}
		}


	}
	
	/**
	 * Nodo de tipo SSH
	 * 
	 * @param packet paquete
	 */
	static void treepkSSH(Packet packet) {
		DefaultMutableTreeNode LayerP;
		DefaultMutableTreeNode childP;
		String protocolName;
		String valueNames [];
		String strAux;
		int i;
		
		JDPacketAnalyzer packetAnalyzer= new SSHAnalyzer();
		packetAnalyzer.analyze(packet);
		protocolName = packetAnalyzer.getProtocolName();
		valueNames = packetAnalyzer.getValueNames();
		
		LayerP = new DefaultMutableTreeNode(protocolName);
		Paquete.add(LayerP);
		if(valueNames != null){
			for (i = 0; i < valueNames.length; i++) {
				strAux = valueNames[i].toString();
				childP = new DefaultMutableTreeNode(strAux + " : "
						+ String.valueOf(packetAnalyzer.getValue(strAux)));
				LayerP.add(childP);
			}
		}


	}
	
	/**
	 * Nodo de tipo Telenet
	 * 
	 * @param packet paquete
	 */
	static void treepkTelnet(Packet packet) {
		DefaultMutableTreeNode LayerP;
		DefaultMutableTreeNode childP;
		String protocolName;
		String valueNames [];
		String strAux;
		int i;
		
		JDPacketAnalyzer packetAnalyzer= new TelnetAnalyzer();
		packetAnalyzer.analyze(packet);
		protocolName = packetAnalyzer.getProtocolName();
		valueNames = packetAnalyzer.getValueNames();
		
		LayerP = new DefaultMutableTreeNode(protocolName);
		Paquete.add(LayerP);
		if(valueNames != null){
			for (i = 0; i < valueNames.length; i++) {
				strAux = valueNames[i].toString();
				childP = new DefaultMutableTreeNode(strAux + " : "
						+ String.valueOf(packetAnalyzer.getValue(strAux)));
				LayerP.add(childP);
			}
		}


	}
	
	/**
	 * Nodo de tipo Otro
	 * 
	 * @param packet paquete
	 */
	static void treepkOtro(Packet packet,PrefIdentificacion pref) {
		DefaultMutableTreeNode LayerP;
		DefaultMutableTreeNode childP;
		String protocolName="Desconocido";
		String valueNames [][];
		String strAux,path;
		int i;
		byte [] data;
		
		OtroAnalyzer packetAnalyzer= new OtroAnalyzer();
        //busco la definicion con la que coincide
		path=pref.getIdentificacionProtocolo(packet.data);
		if(path.equals("")==false){
			packetAnalyzer.analizar(packet.data,path,pref);
			protocolName = packetAnalyzer.getProtocolName();
			valueNames = packetAnalyzer.getValores();
			//pref.getTamaño();
			LayerP = new DefaultMutableTreeNode(protocolName);
			Paquete.add(LayerP);
			if(valueNames != null){
				for (i = 0; i < valueNames.length; i++) {
					strAux = String.valueOf(valueNames[i][0]);
					childP = new DefaultMutableTreeNode(strAux + " : "
							+ String.valueOf(valueNames[i][1]));
					LayerP.add(childP);
				}
			}
			while(pref.getTamaño()<packet.data.length){
				//recojo los valores encapsulados
				data=restoData(pref.getTamaño(),packet.data);
				path=pref.getIdentificacionProtocolo(data);
				if(path.equals("")==false){
					packetAnalyzer.analizar(data,path,pref);
					protocolName = packetAnalyzer.getProtocolName();
					valueNames = packetAnalyzer.getValores();
					//pref.getTamaño();
					LayerP = new DefaultMutableTreeNode(protocolName);
					Paquete.add(LayerP);
					if(valueNames != null){
						for (i = 0; i < valueNames.length; i++) {
							strAux = String.valueOf(valueNames[i][0]);
							childP = new DefaultMutableTreeNode(strAux + " : "
									+ String.valueOf(valueNames[i][1]));
							LayerP.add(childP);
						}
					}
				}
			}
		}
		//No hay coincidecia con ningun protocolo muestro el valor de las data
		if(path.equals("") && packet.data.length > 0){
			String dat="";
			protocolName = "Data";
			for(int h=0;h<packet.data.length;h++){
				dat+=(char)packet.data[h];
			}
			//pref.getTamaño();
			LayerP = new DefaultMutableTreeNode(protocolName);
			Paquete.add(LayerP);
					strAux = String.valueOf(dat);
					childP = new DefaultMutableTreeNode(strAux + " : "
							+ dat);
					LayerP.add(childP);
		}
			
	}
	
	
	/**
	 * Devuelve el jtree
	 * @return arbol
	 */
	public JTree Arbol() {
		return tree;
	}

	/**
	 * Rederizado de los componentes del Jtre

	 */
	class MiRendererDeArbol extends JLabel implements TreeCellRenderer {
		/** * Icono de tipo paquete. */
		private ImageIcon imgPaquete;
		/** * Icono de tipo layer. */
		private ImageIcon imgLayer;
		/** * Icono de tipo hoja. */
		private ImageIcon imghoja;
		/** * Icono de tipo falgs. */
		private ImageIcon imgFlags;
		/** * Icono de tipo TRansporte. */
		private ImageIcon imgTransport;
		/** * Iselleccionado */
		private boolean seleccionado;
		
		/**
		 * Contructor de la clase Render
		 */
		public MiRendererDeArbol() {
			// Cargamos las imgenes de las cartas
			imgPaquete = new ImageIcon(
					FachadaFicheroDirectorios.getdirectorioData("DIR_IMAGES") + 
					System.getProperty("file.separator") + "packet.png");
			imgLayer = new ImageIcon(
					FachadaFicheroDirectorios.getdirectorioData("DIR_IMAGES") + 
					System.getProperty("file.separator") + "layer.png");
			imghoja = new ImageIcon(
					FachadaFicheroDirectorios.getdirectorioData("DIR_IMAGES") + 
					System.getProperty("file.separator") + "hoja.png");
			imgFlags = new ImageIcon(
					FachadaFicheroDirectorios.getdirectorioData("DIR_IMAGES") + 
					System.getProperty("file.separator") + "Flag.png");
			imgTransport = new ImageIcon(
					FachadaFicheroDirectorios.getdirectorioData("DIR_IMAGES") + 
					System.getProperty("file.separator") + "Ids.png");

		}
		/**
		 * Establece el tipo de texto e iconos segun el elemento
		 */
		public Component getTreeCellRendererComponent(JTree arbol,
				Object valor, boolean seleccionado, boolean expandido,
				boolean rama, int fila, boolean conFoco) {
			// Hay que encontrar el nodo en que estamos y coger el
			// texto que contiene

			DefaultMutableTreeNode nodo = (DefaultMutableTreeNode) valor;
			String texto = (String) nodo.getUserObject();
			this.seleccionado = seleccionado;
			// Se fija el color de fondo en función de que esté o no
			// seleccionada la celda del árbol
			if (!seleccionado)
				setForeground(Color.black);
			else
				setForeground(Color.blue);
			// Fijamos el icono que corresponde al texto de la celda, para
			// presentar la imagen de la carta que corresponde a esa celda
			if (texto.equals("Packet")) {
				setFont(new java.awt.Font("MS Sans Serif", 3, 12));
				setIcon(imgPaquete);
			} else {
				if ((texto.equals("Ethernet Frame"))
						|| (texto.equals("ARP / RARP"))
						|| (texto.equals("IP v4"))
						|| (texto.equals("IP v6"))
						|| (texto.equals("ICMP"))
						|| (texto.equals("IGMP Protocol"))
						|| (texto.equals("TCP"))
						|| (texto.equals("UDP"))) {
					setIcon(imgLayer);
					setFont(new java.awt.Font("MS Sans Serif", 3, 12));
				} else {
					if (texto.equals("Flags")) {
						setIcon(imgFlags);
						setFont(new java.awt.Font("MS Sans Serif", 3, 11));
					} else {
						if (texto.equals("FTP")
								|| (texto.equals("HTTP"))
								|| (texto.equals("POP3"))
								|| (texto.equals("SMTP"))
								|| (texto.equals("SSH"))
								|| (texto.equals("SSH"))
								|| (texto.equals("Telnet"))){
							setIcon(imgTransport);
							setFont(new java.awt.Font("MS Sans Serif", 3, 11));
						}
						else{
						setFont(new java.awt.Font("MS Sans Serif", 0, 11));
						setIcon(imghoja);
						}
					}
				}
			}

			// A continuación del icono, ponemos el texto
			setText(texto);

			return (this);
		}

		// Sobreescribimos el método paint() para fijar el color de
		// fondo. Normalmente, un JLabel puede pintar su propio fondo,
		// pero, seguramente debido aparentemente a un bug, o a una
		// limitación en el TreeCellRenderer, es necesario recurrir
		// al método paint() para hacer esto
		/**
		 * Sobreescribimos el método paint() para fijar el color de
		 * fondo. Normalmente, un JLabel puede pintar su propio fondo,
		 * pero, seguramente debido aparentemente a un bug, o a una
		 * limitación en el TreeCellRenderer, es necesario recurrir
		 * al método paint() para hacer esto
		 * 
		 * @param g graphics
		 */
		public void paint(Graphics g) {
			Color color;
			Icon currentI = getIcon();
			// Fijamos el colos de fondo
			color = seleccionado ? Color.white : Color.white;
			g.setColor(color);
			// Rellenamos el rectángulo que ocupa el texto sobre la
			// celda del árbol
			g.fillRect(0, 0, getWidth() - 1, getHeight() - 1);

			super.paint(g);
		}
	}

	/**
	 * Convierte array de bytes a su valor en binario.
	 */
	public static String convertirBinario(byte[] data){
		String convertido="";
		
		for(int i=0;i<data.length;i++){
			convertido+=(char)data[i];
		}
		return convertido;
	}
	
	/**
	 * Devuelve los bytes de encapsulacion apartir de una determinada posicion.
	 */
	public static byte[]  restoData(int tamaño,byte [] data){
		byte []aux = new byte [data.length-tamaño];
		int j=0;
		for(int i=tamaño; i< data.length;i++ ){
			aux[j]=data[i];
			j++;
		}
		return aux;
	}
} // fin de la clase

