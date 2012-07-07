package dominio.pcap;

import java.util.Vector;

import net.sourceforge.jpcap.capture.*;
import net.sourceforge.jpcap.client.*;
import net.sourceforge.jpcap.net.*;
import net.sourceforge.jpcap.util.*;

import dominio.FachadaDominio;
import dominio.pcap.SaveRawPacketHandler;

//Declaraciones necesarias para la prueba
import java.awt.Frame;


/**
 * Clase Captura.
 * 
 * Captura de ficheros. Nos proporciona todo lo relacionado con la Captura de
 * ficheros.
 * 
 * @author Leonardo García & JoseRamón Gutierrez
 * @version 2.0
 * 
 * @see net.sourceforge.jpcap.net
 * @see net.sourceforge.jpcap.client
 * @see dominio.FachadaDominio
 */
public class Captura extends Thread {
	/**
	 * Captura infinita de paquetes
	 */
	private static final int INFINITE = -1;

	/**
	 * Numero de paquetes a capturar
	 */
	private int PACKET_COUNT;

	/**
	 * Lista de dispositivos
	 */
	protected String[] devs;

	/**
	 * Ruta de fichero de captura
	 */
	protected String filePathCapture;

	/**
	 * Modo de captura
	 */
	private boolean promiscuo;

	/**
	 * paquete
	 */
	protected Packet paquete;

	/**
	 * Paquete de cpatura
	 */
	protected PacketCapture pcap;

	/**
	 * Listener encargado de captura en tiempo real
	 */
	private SaveRawPacketHandler SaveRPH;

	/**
	 * Instalcia de la clase encargada del control de nombre de capturas.
	 */
	public SaveFileName sfn;

	/**
	 * Fitro de captura
	 */
	private Filtro filtro;

	/**
	 * Cadena de filtro
	 */
	private String FILTER;

	/**
	 * historico de captura
	 */
	protected CaptureHistory history;

	/**
	 * Vector de captura
	 */
	private Vector rawPacket; //vector que contiene los paquetes

	/**
	 * Constructor de la clase Captura
	 */
	public Captura() {
		pcap = new PacketCapture();
		filtro = new Filtro();
		PACKET_COUNT = INFINITE;
		history = new CaptureHistory();
		rawPacket = null;
		this.setPromiscuo(true);
	}

	/**
	 * Obtine los dispositivos de captura
	 * 
	 * @return vector de capturas
	 */
	public String[] capDispositivos() {
		try {
			devs = PacketCapture.lookupDevices();
			String dispo = pcap.findDevice();
			if (devs.length == 0) {
				devs[0] = dispo;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return devs;
	}

	/**
	 * Abre un dispostivo determinado para realizar la captura
	 * 
	 * @param dispositivo
	 *            dispositivo de captura
	 */
	public void openCaptura(String dispositivo) throws Exception {
		try {
			System.out.println("");
			String osName = System.getProperty("os.name");
			System.out.println("=> " + osName);
			if (osName.compareTo("Linux") == 0) {
				pcap.open(dispositivo, promiscuo);
			} else {
				dispositivo = dispositivo.substring(0, dispositivo
						.indexOf("\n"));
				//pcap.open(dispositivo,promiscuo);
				//pcap.open(dispositivo, 96,promiscuo,1000);
				pcap.open(dispositivo, 1514, promiscuo, 1000);
			}

		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}

	}

	/**
	 * Ejecuta la captura con los parametros que le fueron pasados. Se ejecuta
	 * cuando es llamado "start" del thread
	 */
	public void run() {
		//Bueno
	
		try {
			FILTER = filtro.getfilter();
			pcap.setFilter(FILTER, true);
			System.out.println("=> Filter => " + FILTER);

			//SaveRPH = new SaveRawPacketHandler();
			SaveRPH.runHilos(this);
			pcap.addRawPacketListener(SaveRPH);

			//PACKET_COUNT = INFINITE;
			runCapture();

			//System.out.println("Paro----------->");
			//endCapture();
			//System.out.println("Paro->Fin de captura normal");
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	/**
	 * Ejecuta la captura
	 */
	public void runCapture() {
		try {
			pcap.capture(PACKET_COUNT);// Numero de paketes a capturar
		} catch (Exception e) {
			//e.printStackTrace();
			System.out.println("<<<<< ===== runCapture ===== >>>>> ");
		}
	}

	public void closeCap(){
		//pcap.close();
		//pcap = null;
		
		pcap.endCapture();
		pcap.close();
		pcap = null;
		System.err.println("Cerro Captura ");
	}
	
	/**
	 * Termina la captura de forma correcta.
	 */
	public void endCapture() {
		
		if (pcap != null) {

			System.out.println("NOOta a null ====>>>> ");
			//pcap.endCapture();
			closeCap();
			//pcap.close();
			//pcap = null;
			//kirta esto y ponerlo en DominoFachada		
			FachadaDominio.saveMetaCaptura();

			if (SaveRPH != null) {
				System.out.println("endCApture => No SaveRPH");
				SaveRPH.stopHilos();
			}
			else {
				System.out.println("endCApture => Si SaveRPH");
			}
			if (this.isAlive()) {
				System.out.println("endCApture => Destruyendo hilo Captura");
				this.stop();
			}
			System.out.println("null ====>>>> ");

		}
		else {
			System.out.println("esta a null ====>>>> ");

		}
	}

	/**
	 * Establece el listener que se encarga de guardar los snifado
	 */
	public void setListener() {//OJO seria mejor hacerlo al reves el if
		//listener para capturar
		String aux = getFilePathCapture().trim();
		setSFName();
		if (aux.equals("")) {
			SaveRPH = new SaveRawPacketHandler(getSFname());
			//SaveRPH = new SaveRawPacketHandler();
		} else {
			SaveRPH = new SaveRawPacketHandler(getSFname(),aux);
		}
	}

	/**
	 * Establece la clase SaveFileNAme encargada del control de nombres de
	 * captrura
	 * 
	 */
	public void setSFName() {
		if (sfn == null)
			sfn = new SaveFileName();
	} //setSFName

	/**
	 * Devuelve la clase SaveFileNAme encargada del control de nombres de
	 * captrura
	 * 
	 * @see dominio.preferences.preferencesBeanCapture
	 */
	public SaveFileName getSFname() {
		return sfn;
	} //getSFname

	/**
	 * Establece la ruta y fichero de Captura
	 * 
	 * @param aux
	 *            ruta del fichero de captura
	 */
	public void setFilePathCapture(String aux) {
		this.filePathCapture = aux;
	}

	/**
	 * Devuelve la ruta y el fichero de Captura
	 * 
	 * @return ruta del fichero de captura
	 */
	public String getFilePathCapture() {
		return this.filePathCapture;
	}

	/**
	 * Establece la captura como Multifile. Cuando llega a un maximo de espacio
	 * del fichero, genera otro consecutivo
	 * 
	 * @param aux
	 *            espacio medido en bytes
	 */
	public void setMFilSpace(String aux) {
		try {
			System.out.println("  -->M Fil Space " + aux);
			long lSpace = Long.parseLong(aux);
			SaveRPH.setSpace(lSpace);
		} catch (NumberFormatException nfe) {
			System.out.println("NumberFormatException: " + nfe.getMessage());
		}
	}

	/**
	 * Establece la captura como Multifile. Cuando llega a un maximo de tiempo
	 * de captura, genera otro consecutivo
	 * 
	 * @param aux
	 *            tiempo medido en milisegundos
	 */
	public void setMFilTime(String aux) {
		try {
			System.out.println("  -->M Fil Time " + aux);
			long lTime = Long.parseLong(aux);
			SaveRPH.setTime(lTime);
		} catch (NumberFormatException nfe) {
			System.out.println("NumberFormatException: " + nfe.getMessage());
		}
	}

	/**
	 * Establece la opcion "Pila" Multifile. Cuando llega a un maximo de
	 * ficheros capturados, vuelve a nombrarlo desde el 1
	 * 
	 * @param aux
	 *            numero de pila
	 */
	public void setMFilPila(String aux) {
		try {
			System.out.println("  -->M Fil Pila " + aux);
			int iTime = Integer.parseInt(aux);
			SaveRPH.setPila(iTime);
		} catch (NumberFormatException nfe) {
			System.out.println("NumberFormatException: " + nfe.getMessage());
		}
	}

	/**
	 * Establece la captura "Stop" Multifile. Cuando llega a un maximo de
	 * fichero para la captura
	 * 
	 * @param aux
	 *            maximo de ficheros
	 */
	public void setMFilStop(String aux) {
		try {
			System.out.println("  -->M Fil Stop " + aux);
			int iMax = Integer.parseInt(aux);
			SaveRPH.setMaximo(iMax);
		} catch (NumberFormatException nfe) {
			System.out.println("NumberFormatException: " + nfe.getMessage());
		}
	}

	/**
	 * Establece el filtro de captura
	 * 
	 * @param aux
	 *            fitro de captura
	 */
	public void setFiltro(Filtro aux) {
		filtro = aux;
	}

	/**
	 * Establece la captura en modo promiscuo
	 * 
	 * @param aux
	 *            activado o no
	 */
	public void setPromiscuo(boolean aux) {
		promiscuo = aux;
	}

	/**
	 * Establece la captura con un numero maximo de paquetes.
	 * 
	 * @param aux
	 *            numero maximo de paquetes a capturar
	 */
	public void setNumPaquetes(String aux) {
		PACKET_COUNT = Integer.parseInt(aux);
	}

	/**
	 * Establece el listener de tipo PacketListener
	 * 
	 * @param aux
	 *            PacketListener
	 */
	public void addPacketListener(PacketListener aux) {
		pcap.addPacketListener(aux);
	}

	/**
	 * Establece el listener de tipo RawPacketListener
	 * 
	 * @param aux
	 *            RawPacketListener
	 */
	public void addRawPacketListener(RawPacketListener aux) {
		pcap.addRawPacketListener(aux);
	}

	/**
	 * Establece el historico de paquetes
	 * 
	 * @param pakete
	 *            Packet
	 */
	public void addpackethistory(Packet pakete) {
		history.add(pakete);
	}

	/**
	 * Borra el historia de paquetes
	 */
	public void clearHistory() {
		history.clear();
	}

	/**
	 * Obtiene las paquetes del historico
	 * 
	 * @return lista del historico de paquetes
	 */
	public CaptureHistory getpackethistory() {
		return history;
	}

	/**
	 * getLinkLayer
	 * 
	 * @return getLinkLayer
	 */
	public int getLinkLayer() {
		int Linklayer = -1;
		try {
			Linklayer = pcap.getLinkLayerType();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return Linklayer;
	}

	/**
	 * Captura en modo offline, Captura desde fichero. Fichero .pcap
	 * 
	 * @param ruta
	 *            Ruta de donde lee el fichero de captura
	 */
	public void openOffline(String ruta)
			throws net.sourceforge.jpcap.capture.CaptureFileOpenException {
		try {
			System.out.println("--> openOffline => " + ruta);
			pcap.openOffline(ruta);
			//pcap.linkType = LinkLayers.EN10MB;
		} catch (Exception nfe) {

		}
	}

	/**
	 * establece el vector de ficheros capturados
	 * 
	 * @param RawP
	 *            vector de capturas
	 */
	public void setPcapV(Vector RawP) {
		this.rawPacket = RawP;
	}

	/**
	 * Obitiene el vector de capturas
	 * 
	 * @return vector de capturas
	 */
	public Vector getPcapV() {
		return this.rawPacket;
	}

	/**
	 * Guarda la captura desde el vector de ficheros capturados
	 * 
	 * @param nomFile
	 *            ruta y fichero de captura
	 */
	public int savePcapFile(String nomFile) {
		TcpdumpWriter file;
		int i;
		file = new TcpdumpWriter();
		try {
			file.writeHeader(nomFile, 1, 96);
			System.out.println(nomFile.toString());
		} catch (Exception exceptionfile) {
			exceptionfile.printStackTrace();
		}
		if (getPcapV() != null) {
			for (i = 0; i < getPcapV().size(); i++) {
				try {
					RawPacket rawPacket = (RawPacket) getPcapV().elementAt(i);
					file.appendPacket(nomFile, rawPacket, 1);
				} catch (Exception exception) {
					exception.printStackTrace();
				}

			}

			return 0; //JOptionPane.showMessageDialog(frameinf, "File Save");
		} else {
			return 1; //JOptionPane.showMessageDialog(frameinf, "Error no
			// Capture ");
		}

	}
	public void pruebaCaptura(String dispositivo){
		Vector VectorConexiones;
		Frame frame;
		boolean visualframe;
		SaveRawPacketHandler SaveRPH;
		
		visualframe = false;
		
		//VectorConexiones = new Vector();   // Creo un objeto tipo Vector de Conexiones      
		//frame = new CaptureViewFrame();
		pcap = new PacketCapture();  
		history = new CaptureHistory();
		//listener por defecto con ruta por defecto
		SaveRPH = new SaveRawPacketHandler();
		
		
		
		try{
			String osName = System.getProperty("os.name");
			System.out.println("os.Name:"+osName);
			String tmp=System.getProperty("user.dir");
			System.out.println("Working directory del usuario: "+tmp);


			if (osName.compareTo("Linux")== 0){
				pcap.open(dispositivo,promiscuo);
			}else{ 
				dispositivo = dispositivo.substring(0,dispositivo.indexOf("\n"));
				//pcap.open(dispositivo,promiscuo);
				//pcap.open(dispositivo, 96,promiscuo,1000);
				pcap.open(dispositivo, 1514,promiscuo,1000);
			}
			

			FILTER = "";
			
			pcap.setFilter(FILTER,true);  

			SaveRPH = new SaveRawPacketHandler();
			SaveRPH.runHilos(this);
		
			pcap.addRawPacketListener(SaveRPH);
			
			pcap.capture(-1); // Numero de paketes a capturar
			pcap.close();
			
			
			
		} catch (InvalidFilterException e) {
	
			
		} catch(Exception e) {
			e.printStackTrace();
		}
		
	}

} // fin de la clase
