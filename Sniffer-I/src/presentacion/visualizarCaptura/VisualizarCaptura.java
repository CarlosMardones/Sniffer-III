package presentacion.visualizarCaptura;

import java.io.*;
import java.awt.*;
import java.awt.event.*;

import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JCheckBoxMenuItem;
import javax.swing.JMenuBar;
import javax.swing.JTextArea;
import javax.swing.JScrollPane;
import javax.swing.JFrame;
import javax.swing.JToolBar;
import javax.swing.JSeparator;
import javax.swing.JSplitPane;
import javax.swing.JOptionPane;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.filechooser.*;
import javax.swing.JTabbedPane;
import javax.swing.BorderFactory;
import javax.swing.border.Border;


import presentacion.Mediador;
import servicioAccesoDatos.FachadaFicheroDirectorios;

import dominio.pcap.rules.*;
import dominio.*;

import jpcap.PacketReceiver;
import jpcap.packet.*;

import java.util.Collection;
import java.util.Vector;

/**
 * TODO Para cambiar la plantilla de este comentario generado, vaya a Ventana -
 * Preferencias - Java - Estilo de código - Plantillas de código
 */
public class VisualizarCaptura extends JPanel implements Runnable {
	/** * . */
	private Vector VectorConexiones;
	/** * . */
	private Rules Reglas;
	/** * . */
	private XMLlog log;
	/** * . */
	private Vector VectorPacket;
	/** * . */
	protected Conexion NuevaConexion;
	/** * . */
	private int contadorarbol = 0;
	/** * . */
	protected Vector history;
	/** * . */
	protected JFrame frameinf;
	/** * . */
	protected Packet paquete;
	/** * . */
	public boolean visualframe;
	/** * . */
	protected Packet pcap; // Para finalizar la captura de datos.
	/** * . */
	protected Packet pcapLectura;
	/** * . */
	private String fichero;
	/** * . */
	protected Thread hilo = null;
	/** * . */
	protected Collection Historial;
	/** * . */
	private TablePane TablaPaquetes;
	/** * . */
	private TableConexions TablaConexiones;
	/** * . */
	public TableAlerts TablaAlertas;
	/** * . */
	public FAlertsLoad FAlertsLoad;
	/** * . */
	public Mediador mediador;
	/** * . */
	private TreePacket Arbol;
	/** * . */
	public Vector x;
	/** * . */
	public JTabbedPane jTabbedPane1;
	/** * . */
	public JPanel Paneaux;
	/** * . */
	public JPanel Paneaux2;
	/** * . */
	public JPanel Paneauxtree;
	/** * . */
	public JPanel Paneauxtree1;
	/** * . */
	public JPanel Paneauxtree2;
	/** * . */
	public JPanel Paneauxtree3;
	/** * . */
	public JPanel Paneconexiones;
	/** * . */
	public JTextArea output;
	/** * . */
	public JTextArea output2;
	/** * . */
	public JScrollPane scroll;
	/** * . */
	public JScrollPane scrollPane;
	/** * . */
	public JScrollPane scrollPane2;
	/** * . */
	public JScrollPane scrollPane3;
	/** * . */
	public JSplitPane splitPane;
	/** * . */
	public JSplitPane splitPane2;
	/** * . */
	public JToolBar toolBar;
	/** * . */
	public JSeparator jSeparator;
	/** * . */
	public JLabel Label;
	
	/**
	 * Contructor de la clase.
	 * 
	 * @param med mediador
	 */
	public VisualizarCaptura(Mediador med) {

		//super("Sniffer - Ubu");
		mediador = med;
		mediador.setPanelEstado("Cargado datos desde fichero");
		//System.out.println("Cargando datos desde fichero");

		VectorConexiones = new Vector(); // Creo un objeto tipo Vector de
		// Conexiones
		//frame = new CaptureViewFrame(); // para visualiza las capturas en
		// modo grafico
		//pcap = new PacketCapture();
		//history = new CaptureHistory();
		history = new Vector();
		VentanaCaptura();
	}
	
	/**
	 * 
	 */
	public void VentanaCaptura() {

		//fc = new JFileChooser(); //Lo utiliza en GenerarXML

		Label = new javax.swing.JLabel();
		Label.setIcon(new javax.swing.ImageIcon(
				FachadaFicheroDirectorios.getdirectorioData("DIR_IMAGES") + 
				System.getProperty("file.separator") + 
				"tcp.png"));
		Label.setText("Conexiones TCP");

		//VectorConexiones = new Vector();
		TablaPaquetes = new TablePane(this);
		TablaConexiones = new TableConexions(TablaPaquetes);

		Paneaux = new JPanel();
		Paneaux2 = new JPanel();
		Paneauxtree1 = new JPanel();
		Paneauxtree2 = new JPanel();
		Paneauxtree3 = new JPanel();

		Paneauxtree = new JPanel();
		Paneconexiones = new JPanel();

		Paneaux.setLayout(new java.awt.GridLayout(0, 1));
		Paneaux2.setLayout(new java.awt.GridLayout(0, 1));
		Paneauxtree.setLayout(new java.awt.GridLayout(1, 3));
		Paneauxtree1.setLayout(new java.awt.BorderLayout());
		Paneauxtree2.setLayout(new java.awt.BorderLayout());
		Paneauxtree3.setLayout(new java.awt.BorderLayout());

		Paneauxtree.add(Paneauxtree1);
		Paneauxtree.add(Paneauxtree2);
		Paneauxtree.add(Paneauxtree3);

		Paneconexiones.setBorder(new javax.swing.border.BevelBorder(
				javax.swing.border.BevelBorder.RAISED));
		Paneconexiones.setLayout(new java.awt.BorderLayout());
		//Para El historico, pero creo que no está activado
		output = new JTextArea(37, 45);
		output2 = new JTextArea(12, 60);
		output2.setBackground(new Color(0, 0, 0));
		output2.setForeground(new Color(51, 255, 0));
		output.setEditable(false);
		output2.setEditable(false);

		scrollPane = new JScrollPane(TablaPaquetes.Tabla());
		scrollPane2 = new JScrollPane(TablaConexiones.Tabla());

		Paneconexiones.add(Label, java.awt.BorderLayout.NORTH);
		Paneconexiones.add(scrollPane2, java.awt.BorderLayout.CENTER);

		JSplitPane splitPane = new JSplitPane(JSplitPane.VERTICAL_SPLIT);
		splitPane.setTopComponent(scrollPane);
		splitPane.setBottomComponent(Paneconexiones);

		splitPane.setDividerLocation(200);
		Paneaux.add(splitPane);

		JSplitPane splitPane2 = new JSplitPane(JSplitPane.VERTICAL_SPLIT);
		splitPane2.setTopComponent(Paneaux);
		splitPane2.setBottomComponent(Paneauxtree);

		splitPane2.setDividerLocation(300);
		Paneaux2.add(splitPane2);

		Paneaux2.setPreferredSize(new Dimension(1023, 760));

		//contentPane.add(Paneaux2, java.awt.BorderLayout.CENTER);

	}

	/**
	 * 
	 * @return .
	 */
	public JPanel getPanel() {
		return Paneaux2;
	}

	/**
	 * 
	 */
	public void run() {
		resetGraficos();
		AbrirFile(getFile());
	}

	/**
	 * 
	 * @param aux .
	 */
	public void setFile(String aux) {
		fichero = aux;
	}

	/**
	 * 
	 * @return .
	 */
	public String getFile() {
		return fichero;
	}
	
	/**
	 * 
	 */
	public void resetGraficos(){
		VectorConexiones.clear();
		history.clear(); 
		TablaPaquetes.clearTable();
		TablaConexiones.clearTable();
		Paneauxtree1.removeAll();
		Paneauxtree2.removeAll();
		Paneauxtree3.removeAll();
		contadorarbol=0;		
	}

	/**
	 * 
	 * @return .
	 */
	public int getLinkLayer() {
		int Linklayer = -1;
		try {//OJO
			//Linklayer = pcap.getLinkLayerType();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return Linklayer;
	}

	/**
	 * 
	 * @param nombre .
	 */
	public void AbrirFile(String nombre) {
		PacketHandlerPcapLib PacketHPL;
		String fichero;
		fichero = nombre;
		//fichero = nombre.getAbsolutePath();
		System.out.println("1............" + fichero);
		//pack();
		PacketHPL = new PacketHandlerPcapLib(this, TablaPaquetes);
		try {
			FachadaDominio.crearPcapLib();
			FachadaDominio.getPcapLib().openOffline(fichero);
			//FachadaDominio.getPcap()
			FachadaDominio.getPcapLib().offline_VisualizarCaptura(PacketHPL);
			//pcap.capture(-1); // Numero de paketes a captura
			System.out.println("5............");
			AddConexionesTabla();

		

			//mediador.getVentanaMenuSniffer().repaint();
			System.err.println("Establecio fichero leido => " + fichero);
			mediador.setFileReaded(fichero);
			mediador.getVentanaMenuSniffer().dispose();

			FachadaDominio.getPcapLib().eCOFwithoutSaveMeta();
			System.out.println("...paso");
		// OJOJ JO JOJ OJ OJ OJ O
			//SE DEBERIA CERRAR EL FICHERO
			//FachadaDominio.getPcapLib().closeCap();
			if (Reglas != null) {
				//				Idsinformes.setEnabled(true);
				//				IdsRulesLoad.setEnabled(true);
				//				IdsButton.setEnabled(true);
				log.CrearLog();
			}


		} catch (Exception exceptionfile) {
			exceptionfile.printStackTrace();

		}
	} // Abrir file

	/**
	 * 
	 * @param numero .
	 */
	public void CrearArbol(int numero) {
		Border emptyBorder, selectBorder;
		JScrollPane auxJSPanel;

		contadorarbol++;
		Packet paquete;
		//paquete = FachadaDominio.getPcap().getpackethistory().get(numero); //  coger
		paquete = (Packet)history.get(numero); //  coger
		// el
		// primer
		// paquete
		String s;
		Byte oto; 
		
		byte arr[]=paquete.data;
		//System.out.println( "Packet al principio: "+paquete.data);
		//System.out.println("antes!!!!!!");
		//for(int i=0;i<arr.length;i++){
		//	System.out.println("si");
		//	oto = new Byte(arr[i]);
		//	s = Integer.toBinaryString(arr[i] & 0xFF);
		//	System.out.println("byte "+i+": "+s);
		//}
		
		emptyBorder = BorderFactory.createLoweredBevelBorder();
		selectBorder = BorderFactory.createMatteBorder(
                2, 2, 2, 2, Color.red);
		
		Arbol = new TreePacket(numero, paquete);
		
		scrollPane3 = new JScrollPane(Arbol.Arbol());
		scrollPane3.setBorder(selectBorder);


		if (contadorarbol == 1) {
			Paneauxtree1.removeAll();
			Paneauxtree1.add(scrollPane3, java.awt.BorderLayout.CENTER);
			if (Paneauxtree3.getComponentCount() > 0){
				auxJSPanel = (JScrollPane)Paneauxtree3.getComponent(0);
				auxJSPanel.setBorder(emptyBorder);
			}			
		} else {
			if (contadorarbol == 2) {
				Paneauxtree2.removeAll();
				Paneauxtree2.add(scrollPane3, java.awt.BorderLayout.CENTER);
				if (Paneauxtree2.getComponentCount() > 0){
					auxJSPanel = (JScrollPane)Paneauxtree1.getComponent(0);
					auxJSPanel.setBorder(emptyBorder);
				}

			} else {
				if (contadorarbol == 3) {
					Paneauxtree3.removeAll();
					Paneauxtree3.add(scrollPane3, java.awt.BorderLayout.CENTER);
					contadorarbol = 0;
					if (Paneauxtree2.getComponentCount() > 0){
						auxJSPanel = (JScrollPane)Paneauxtree2.getComponent(0);
						auxJSPanel.setBorder(emptyBorder);
					}

				}
			}
		}

		//this.repaint();
		//jTabbedPane1.repaint();
		// show();
		mediador.repaintVentana(mediador.getVentanaMenuSniffer());
	}
	/**
	 * .
	 */
	public void dumpHistory() {
/*		Historial = history.getCollection();
		output2.append("TAmaño historial ==>>"
				+ String.valueOf(Historial.size()));
		output2.append("Historial ==>>" + Historial.toString());
		history.dump(false); // history a salida standard
*/
	}

	/**
	 * .
	 */
	public void refreshCapture() {
		this.Paneaux.repaint();
		this.Paneaux2.repaint();
		this.repaint();
		System.out.println("refres...paso");
	}

	/**
	 * Funcion LoadFileRules Sinopsis: Funcion que se encarga de cargar las
	 * raglas control de los ficheros pasados como parametros.
	 * 
	 * @param nombre
	 *            monbre al archivo de parametros a cargar el archivo será de
	 *            tipo .rule
	 *  
	 */
	public void LoadFileRules(File nombre) {

		int i;

		try {
			String nombreficheroreglas = nombre.getAbsoluteFile() + "";
			if (nombreficheroreglas.indexOf(".rules") == -1) {
				throw (new Exception("Fichero no de reglas"));
			}

			if (Reglas == null) {
				this.log = new XMLlog(1);
				this.TablaAlertas = new TableAlerts();
				this.FAlertsLoad = new FAlertsLoad();
				this.Reglas = new Rules(TablaAlertas, log);
			}

			Reglas.LoadRules(nombre);
			FAlertsLoad.DatosTablaAlertsLoad(nombre.getAbsolutePath());
			// Ordena las reglas por prioridad
			Reglas.OrdenarReglas();
			JOptionPane.showMessageDialog(frameinf, "Fichero de reglas "
					+ nombreficheroreglas + " añadido");

		} catch (Exception e) {

			JOptionPane.showMessageDialog(frameinf,
					"Formato incorrecto de fichero de reglas");
		}

	}

	/**
	 * 
	 * @return .
	 */
	public Rules getReglas() {
		return Reglas;
	}

	/**
	 * .
	 */
	public void clearHistory() {
		history.clear();
	}

	/**
	 * .
	 * @param pakete
	 */
	public void addpackethistory(Packet pakete) {
		history.add(pakete);
	}

	/**
	 * 
	 * @return .
	 */
	public Vector getConexionesTCP() {
		// Devuelve el vector de conexiones TCP
		return VectorConexiones;
	}

	/**
	 * .
	 * @param IpOrigen .
	 * @param IpDestino .
	 * @param PuertoOrigen .
	 * @param PuertoDestino .
	 * @param numeropaquete .
	 */
	public void DatosConexion(String IpOrigen, String IpDestino,
			int PuertoOrigen, int PuertoDestino,int numeropaquete) {

		int i = 0;
		boolean existente = false;

		NuevaConexion = new Conexion(IpOrigen, IpDestino, PuertoOrigen,
				PuertoDestino);

		if (VectorConexiones.size() != 0) { // El vector no esta vacio
			while ((i < VectorConexiones.size()) && (!existente)) {
				//mientras el paquete no pertenezca a ninguna conexion
				Conexion objetoConexion = (Conexion) VectorConexiones
						.elementAt(i);
				if ((((objetoConexion.getIpSrc().compareTo(IpOrigen) == 0) && (objetoConexion
						.getIpDest().compareTo(IpDestino) == 0)) || ((objetoConexion
						.getIpSrc().compareTo(IpDestino) == 0) && (objetoConexion
						.getIpDest().compareTo(IpOrigen) == 0)))
						&& ((objetoConexion.getPuertoSrc() == PuertoOrigen)
								&& (objetoConexion.getPuertoDst() == PuertoDestino) || (objetoConexion
								.getPuertoSrc() == PuertoDestino)
								&& (objetoConexion.getPuertoDst() == PuertoOrigen))) {
					// Conexion Existente
					// incremento el numero de paquetes de la conexion.
					objetoConexion.addpaquete(numeropaquete);
					existente = true;
				} // end_if
				i++;
			} // end_while

			// Salimos del bucle bien xq ya existe una conexion con los
			// parametros
			// del paquete o xq no existe una conexion con esos parametros
			if ((i == VectorConexiones.size()) && (!existente)) {
				// conexion nueva
				NuevaConexion.addpaquete(numeropaquete);
				VectorConexiones.add(NuevaConexion);
			}

		} else {
			// primer elemento del vector
			// primera conexion
			NuevaConexion.addpaquete(numeropaquete);
			VectorConexiones.add(NuevaConexion);
		}

	}

	/**
	 * .
	 */
	public void AddConexionesTabla() {
		int i = 0;
		for (i = 0; i < VectorConexiones.size(); i++) {
			Conexion objetoConexion = (Conexion) VectorConexiones.elementAt(i); //cast
			TablaConexiones.DatosTablaConexion(String.valueOf(i),
					objetoConexion.getTimeEstablecimiento(), objetoConexion
							.getIpSrc(), objetoConexion.getIpDest(), String
							.valueOf(objetoConexion.getPuertoSrc()), String
							.valueOf(objetoConexion.getPuertoDst()), String
							.valueOf(objetoConexion.getnumeropaquetes()));
		}

	}
	/**
	 * 
	 * @return .
	 */
	public String leerPTV(){
		return mediador.leerPropertiesTableView();
	}
	
	/**
	 * .
	 * @return .
	 */
	public String getColumnPosition(){
		return TablaPaquetes.obtenerOrdenColumnas();
	}
} // fin de la clase
