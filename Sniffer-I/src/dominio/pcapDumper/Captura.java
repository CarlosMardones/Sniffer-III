package dominio.pcapDumper;


import java.util.Vector;


import dominio.FachadaDominio;
import dominio.export.xml_PcapLib.XmlPacketHandler;

import javax.swing.JOptionPane;

import jpcap.packet.*;
import jpcap.NetworkInterface;
import jpcap.JpcapCaptor;
import jpcap.JpcapWriter;

import presentacion.visualizarCaptura.PacketHandlerPcapLib;

/**
 * Clase Captura.
 * 
 * Captura de ficheros. Nos proporciona todo lo relacionado con la Captura de
 * ficheros.
 * 
 * @author Leonardo Garca & JoseRamn Gutierrez
 * @version 2.0
 * 
 * @see dominio.FachadaDominio
 * @see dominio.export.xml_PcapLib.XmlPacketHandler
 * @see presentacion.visualizarCaptura.PacketHandlerPcapLib
 * @see jpcap.packet.Packet
 * @see jpcap.NetworkInterface
 * @see jpcap.JpcapCaptor
 * @see jpcap.JpcapWriter
 */
public class Captura extends Thread {
	/** * Captura infinita de paquetes. */
	private static final int INFINITE = 0;
	/** * Numero de paquetes a capturar. */
	private int PACKET_COUNT;
	/** * Lista de dispositivos. */
	protected String[] devs;
	/** * Ruta de fichero de captura. */
	protected String filePathCapture;
	/** * Modo de captura. */
	private boolean promiscuo;
	/** * Paquete. */
	protected Packet paquete;
	/** * Listener encargado de captura en tiempo real. */
	private SavePacketHandler SavePH;
	/** * Listener encargado de captura en modo offline. */
	private OfflineSavePacketHandler OfflineSPH;
	/** * Listener encargado de exportar a XML. */
	private XmlPacketHandler XmlPH;
	/** * Listener para el modo visual. */
	private PacketHandlerPcapLib PacketHPL;
	/** * Instancia de la clase encargada del control de nombre de capturas. */
	public SaveFileName sfn;
	/** * Fitro de captura. */
	private Filtro filtro;
	/** * Cadena de filtro. */
	private String FILTER;
	/** * Vector de captura */
	private Vector rawPacket; //vector que contiene los paquetes
	/** * clase jpcap para escuchar*/
	static JpcapCaptor jpcap;
	/** * clase jpcap para grabar a fichero*/
	static JpcapCaptor jpcap_write;
	/** * clase para escribir un paquete. */
	JpcapWriter writerToFile;
	/** * Num bites por paquetes. */
	String caplen;
	/** * Capturando, */
	boolean isLiveCapture;
	/** * Grabar metadatos */
	public boolean finSaveMeta;
	/** * Tipo offline. */
	boolean typeOffline;
	/** * . */
	public boolean finOneFile;
	/** * Lleva el control por tipo de los paquetes recibos. */
	CountPacketHandler RCountPacketHandler;
	/** * Finalizó la captura. */
	public boolean endCapture;
	
	/**
	 * Constructor de la clase.
	 */
	public Captura() {
		//jpcap = new Jpcap();
		jpcap = null;
		filtro = new Filtro();
		PACKET_COUNT = INFINITE;
		this.setPromiscuo(true);
		caplen = "1514";
		setTypeOffline(false);
		endCapture = false;
	}

	/**
	 * Obtine los dispositivos de captura
	 * 
	 * @return NetworkInterface de capturas
	 * 
	 * @see jpcap.NetworkInterface
	 */
	public NetworkInterface[] capDispositivos() {
		NetworkInterface[] devices=null;
		try {
			 devices=JpcapCaptor.getDeviceList();
			if(devices.length==0){
				JOptionPane.showMessageDialog(null,"No network interface found.\nYou need to be admin/su to capture packets.",
						"Warning",JOptionPane.WARNING_MESSAGE);
			}

		}catch(UnsatisfiedLinkError e){
			JOptionPane.showMessageDialog(null,"Cannot find Jpcap and/or libpcap/WinPcap.\n Please install Jpcap and libpcap/WinPcap.",
					"Error",JOptionPane.ERROR_MESSAGE);
			System.exit(-1);
		}
		
		return devices;
	}

	/**
	 * Abre un dispostivo determinado para realizar la captura
	 * 
	 * @param dispositivo
	 *            dispositivo de captura
	 * 
	 * @see jpcap.JpcapCaptor#openDevice
	 */
	public void openCaptura(NetworkInterface dispositivo) throws Exception {
		try {
			jpcap=JpcapCaptor.openDevice(dispositivo,Integer.parseInt(caplen),
					getPromiscuo(),10);
			jpcap_write= JpcapCaptor.openDevice(dispositivo,Integer.parseInt(caplen),
					getPromiscuo(),10);
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}

	}
	
	/**
	 * Captura en modo offline, Captura desde fichero. Fichero .pcap
	 * 
	 * @param ruta
	 *            Ruta de donde lee el fichero de captura
	 * 
	 * @see jpcap.JpcapCaptor#openDevice
	 */
	public void openOffline(String ruta){
		try {
			jpcap = JpcapCaptor.openFile(ruta);
			//pcap.linkType = LinkLayers.EN10MB;
			jpcap_write = JpcapCaptor.openFile(ruta);
		} catch (Exception nfe) {
			nfe.printStackTrace();
		}
	}
	
	
	public void openOffline_read(String ruta){
		try {
			jpcap = JpcapCaptor.openFile(ruta);
		} catch (Exception nfe) {
			nfe.printStackTrace();
		}
	}
	
	public void openOffline_writer(String ruta){
		try {
			jpcap_write = JpcapCaptor.openFile(ruta);
		} catch (Exception nfe) {
			nfe.printStackTrace();
		}
	}
	
	
	/** * thread privado de captura*/
	private Thread captureThread;
	
	/**
	 * Ejecuta la captura con los parametros que le fueron pasados. Se ejecuta
	 * cuando es llamado "start" del thread
	 * Captura de paquetes
	 */
	public void run() {
		try {
			if (getTypeOffline() == false){
				FILTER = filtro.getfilter();
				jpcap.setFilter(FILTER,true);
				//System.out.println("=> Filter => " + FILTER);
				
				isLiveCapture = true;
				finSaveMeta = false;
				
				SavePH.runHilos();
				while (isLiveCapture==true) {
					if (jpcap.processPacket(1, SavePH) == 0 && !isLiveCapture){
						//System.out.println("PasSSSso----hay que parar------->"); //stopCaptureThread();
						stopCaptureThread();
					}
					Thread.yield();
				}
				endCapture(true);
			}
			else{
				FILTER = filtro.getfilter();
				jpcap.setFilter(FILTER,true);
				finOneFile=false;
				isLiveCapture = true;
				finSaveMeta = false;
				//System.out.println("---------------------------");
				//System.out.println("-------MODO OFFLINE--------");
				//System.out.println("---------------------------");
				
				OfflineSPH.runHilos(this);
				captureThread = new Thread(new Runnable(){
					//body of capture thread
					int aux;
					public void run() {
						while (captureThread != null) {
							aux= jpcap.processPacket(1, OfflineSPH);
							if ( aux == 0 && isLiveCapture)
								stopCaptureThread();
							Thread.yield();
						}
						endCaptureOffline(true);
					}
				});
				captureThread.setPriority(Thread.MIN_PRIORITY);
				captureThread.start();
				while (finOneFile==false){}
				jpcap_write.close();
				endCapture=true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	/** 
	 * Captura de paquetes en modo offline
	 */
	public void offfffff(){
		
		finOneFile=false;
		isLiveCapture = true;
		finSaveMeta = false;
		//System.out.println("---------------------------");
		//System.out.println("-------MODO OFFLINE--------");
		//System.out.println("---------------------------");
		
		FILTER = filtro.getfilter();
		try {
			jpcap.setFilter(FILTER,true);
		} catch (java.io.IOException e) {
			e.printStackTrace();
		}
		
		//OfflineSPH.runHilos(this);
		captureThread = new Thread(new Runnable(){
			//body of capture thread
			int aux;
			public void run() {
				while (captureThread != null) {
					aux= jpcap.processPacket(1, OfflineSPH);
					if ( aux == 0 && isLiveCapture){
						stopCaptureThread();
						finOneFile=true;
					}
					
					Thread.yield();
				}
				
			}
		});
		captureThread.setPriority(Thread.MIN_PRIORITY);
		captureThread.start();
		while (finOneFile==false){}
		endCapture=true;
		//System.out.println("---termino modo offline con one file");
	}
	
	/**
	 * Lectura offline de una captura para pasarla a XML
	 * 
	 * @param XmlPH
	 * 			Hanlder del XML
	 * 
	 * @see dominio.export.xml_PcapLib.XmlPacketHandler
	 */
	public void offline_xml(XmlPacketHandler XmlPH){
		finOneFile=false;
		isLiveCapture = true;
		finSaveMeta = false;
		//OfflineSPH.runHilos(this);
		setXmlHalder(XmlPH);
		captureThread = new Thread(new Runnable(){
			//body of capture thread
			int aux;
			public void run() {
				while (captureThread != null) {
					aux= jpcap.processPacket(1, getXmlHalder());
					if ( aux == 0 && isLiveCapture){
						stopCaptureThread();
						finOneFile=true;
					}
					
					Thread.yield();
				}
				
			}
		});
		captureThread.setPriority(Thread.MIN_PRIORITY);
		captureThread.start();
		while (finOneFile==false){}
		endCapture=true;
		//System.out.println("---termino modo offline con one file");
	}	
	/**
	 * Lectura de fichero para el modo gráfico
	 * 
	 * @param auxPacketHPL
	 * 			Handler para la visualizacion de la captura.
	 * 
	 * @see presentacion.visualizarCaptura.PacketHandlerPcapLib
	 */
	public void offline_VisualizarCaptura(PacketHandlerPcapLib auxPacketHPL){
		finOneFile=false;
		isLiveCapture = true;
		finSaveMeta = false;
		PacketHPL = auxPacketHPL;
		captureThread = new Thread(new Runnable(){
			//body of capture thread
			int aux;
			public void run() {
				while (captureThread != null) {
					aux= jpcap.processPacket(1, PacketHPL);
					if ( aux == 0 && isLiveCapture){
						stopCaptureThread();
						finOneFile=true;
					}
					
					Thread.yield();
				}
				
			}
		});
		captureThread.setPriority(Thread.MIN_PRIORITY);
		captureThread.start();
		while (finOneFile==false){}
		//System.out.println("---termino modo offline con one file");
	}	
	
	/**
	 * Estrablece el handler para la exportación XML
	 * 
	 * @param auxXmlPH
	 * 			halder del XML
	 * 
	 * @see dominio.export.xml_PcapLib.XmlPacketHandler
	 */
	public void setXmlHalder(XmlPacketHandler auxXmlPH){
		XmlPH=auxXmlPH;
	}
	
	/**
	 * Devuelve el handler para la exportación del XML
	 * @return halder xml
	 * 
	 * @see dominio.export.xml_PcapLib.XmlPacketHandler
	 */
	public XmlPacketHandler getXmlHalder(){
		return this.XmlPH;
	}

	/**
	 * Arranca los hilos de la captura desde fichero
	 * 
	 * @see dominio.pcapDumper.OfflineSavePacketHandler
	 */
	public void runHilosCapture() {
		OfflineSPH.runHilos(this);
	}
	
	/**
	 * Para los hilos de captura 
	 */
	public void stopCaptureThread() {
		captureThread = null;
		isLiveCapture=false;
	}

	/**
	 * Termina la captura desde fichero de forma correcta.
	 * 
	 * @param tipo
	 * 			captura o offline
	 * 
	 * @see dominio.pcapDumper.OfflineSavePacketHandler
	 * @see FachadaDominio#saveMetaCapturaOffline()
	 */
	public void endCaptureOffline(boolean tipo) {
		endCapture = true;
		isLiveCapture=false;
		if (OfflineSPH != null) {
			//OfflineSPH.stopCaptura();
		}
		else {
		}

		if (tipo == true && jpcap != null){
			jpcap.close();
			jpcap = null;
			jpcap_write.close();
			jpcap_write = null;
			FachadaDominio.saveMetaCapturaOffline();
			finSaveMeta = true;
			if (this.isAlive()) {
				this.stop();
			}
		}
	}
	
	/**
	 * Para la captura de forma segura y graba el fichero metadatos 
	 * 
	 * @see dominio.pcapDumper.OfflineSavePacketHandler
	 * @see FachadaDominio#saveMetaCapturaOffline()
	 */
	public void eCOF(){
		endCapture = true;
		if (OfflineSPH != null) {
			OfflineSPH.stopCaptura();
		}
		jpcap.close();
		jpcap = null;
		jpcap_write.close();
		jpcap_write = null;
		FachadaDominio.saveMetaCapturaOffline();
		
	}
	
	/**
	 * Para la captura de forma segura para utlizada en_estate
	 * 
	 */
	public void both_ECOF(){
		endCapture = true;
		jpcap.close();
		jpcap_write.close();
		System.out.println("cerradondo fichero");
		jpcap = null;
				
	}
	
	/**
	 * Para la captura de forma segura y NO graba el fichero metadatos 
	 * 
	 * @see dominio.pcapDumper.OfflineSavePacketHandler#stopCaptura()
	 */
	public void eCOFwithoutSaveMeta(){
		if (OfflineSPH != null) {
			OfflineSPH.stopCaptura();
		}
		jpcap.close();
		jpcap = null;
	}
	
	/**
	 * Termina la captura de forma correcta.
	 * 
	 * @param tipo
	 * 			captura o offline
	 * 
	 * @see dominio.pcapDumper.SavePacketHandler
	 * @see dominio.FachadaDominio#saveMetaCaptura()
	 */
	public void endCapture(boolean tipo) {
		endCapture = true;
		isLiveCapture=false;
		SavePH.stopCaptura();
		if (SavePH != null) {
			SavePH.stopHilos();
		}
		else {
		}
		if (tipo == true){
			jpcap.close();
			jpcap_write.close();
			FachadaDominio.saveMetaCaptura();
			finSaveMeta = true;
			if (this.isAlive()) {
				this.stop();
			}
		}
		endCapture = true;
	}
	
	/**
	 * Termina la captura de forma correcta.
	 * 
	 * @see dominio.pcapDumper.SavePacketHandler
	 * @see dominio.FachadaDominio#saveMetaCaptura()
	 */
	public void endCaptureCommand() {
		endCapture = true;
		SavePH.stopCaptura();
		if (SavePH != null) {
			SavePH.stopHilos();
		}
		jpcap.close();
		jpcap_write.close();
		//FachadaDominio.saveMetaCaptura();
		if (this.isAlive()) {
				this.stop();
		}
		//endCapture = true;
	}
	

	/**
	 * Establece el listener de captura que se encarga de guardar los paquetes
	 * 
	 * @see dominio.pcapDumper.CountPacketHandler
	 * @see dominio.pcapDumper.SavePacketHandler
	 * @see dominio.pcapDumper.SaveFileName
	 */
	public void setListener() {//OJO seria mejor hacerlo al reves el if
		//listener para capturar
		String aux="";
		aux = getFilePathCapture().trim();
		setSFName();
		RCountPacketHandler = new CountPacketHandler();
		if (aux.equals("")) {
			SavePH = new SavePacketHandler(this, getSFname(), RCountPacketHandler, jpcap);
			//SaveRPH = new SaveRawPacketHandler();
		} else {
			SavePH = new SavePacketHandler(this, getSFname(), RCountPacketHandler, jpcap, aux);
		}
		SavePH.setWriter(jpcap_write);
		SavePH.setNumPacket(this.getNumPaquetes());
	}
	
    public void setControlPacket(boolean aux){
    	SavePH.setControlPacket(aux);
    }
    
	/**
	 * Establece el listener de captura desde fichero que se encarga de guardar los paquetes
	 * 
	 * @see dominio.pcapDumper.CountPacketHandler
	 * @see dominio.pcapDumper.OfflineSavePacketHandler
	 * @see dominio.pcapDumper.SaveFileName
	 */
	public void setListenerOffline() {//OJO seria mejor hacerlo al reves el if
		//listener para capturar
		String aux="";
		//le tiene que llegar el fichero donde guardarlo
		//aux = getFilePathCapture().trim();
		aux = FachadaDominio.getPrefBeanFromFile().getffFilLocate();
		setSFName();
		if (aux.equals("")) {
			OfflineSPH = new OfflineSavePacketHandler(this, getSFname(), jpcap);
			//SaveRPH = new SaveRawPacketHandler();
		} else {
			OfflineSPH = new OfflineSavePacketHandler(this, getSFname(), jpcap, aux);
		}
		OfflineSPH.setNumPacket(this.getNumPaquetes());
		OfflineSPH.setWriter(jpcap_write);
		//OfflineSPH.setTcpDumpWriter();
		
	}
	
	/**
	 * Devuleve la instacia de la clase que lleva la estadísca de los paquetes.
	 * 
	 * @return instancia
	 * 
	 * @see dominio.pcapDumper.CountPacketHandler
	 */
	public CountPacketHandler getCountPacketHandler(){
		return RCountPacketHandler;
	}

	/**
	 * Establece la clase SaveFileNAme encargada del control de nombres de
	 * captura.
	 * 
	 * @see dominio.pcapDumper.SaveFileName
	 */
	public void setSFName() {
		if (sfn == null)
			sfn = new SaveFileName();
	} //setSFName

	/**
	 * Devuelve la clase SaveFileName encargada del control de nombres de
	 * captrura
	 * 
	 * @see dominio.pcapDumper.SaveFileName
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
	 * 
	 * @see dominio.pcapDumper.SavePacketHandler
	 */
	public void setMFilSpace(String aux) {
		try {
			//System.out.println("  -->M Fil Space " + aux);
			long lSpace = Long.parseLong(aux);
			SavePH.setSpace(lSpace);
		} catch (NumberFormatException nfe) {
			System.out.println("NumberFormatException: " + nfe.getMessage());
		}
	}
	
	/**
	 * Devuelve el maximo de espacio del cada fichero
	 * 
	 * @return espacio medido en bytes
	 * 
	 * @see dominio.pcapDumper.SavePacketHandler
	 */
	public long getMFilSpace() {
		return SavePH.getSpace();
	}
	
	/**
	 * Establece la captura desde fichero como Multifile. 
	 * Cuando llega a un maximo de espacio
	 * del fichero, genera otro consecutivo.
	 * 
	 * @param aux
	 *            espacio medido en bytes
	 * 
	 * @see dominio.pcapDumper.OfflineSavePacketHandler
	 */
	public void setMFilSpaceOffline(String aux) {
		try {
			//System.out.println("  -->M Fil Space " + aux);
			long lSpace = Long.parseLong(aux);
			OfflineSPH.setSpace(lSpace);
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
	 * 
	 * @see dominio.pcapDumper.SavePacketHandler
	 */
	public void setMFilTime(String aux) {
		try {
			//System.out.println("  -->M Fil Time " + aux);
			long lTime = Long.parseLong(aux);
			SavePH.setTime(lTime);
		} catch (NumberFormatException nfe) {
			System.out.println("NumberFormatException: " + nfe.getMessage());
		}
	}
	
	/**
	 * Devuelve el maximo de tiempo del cada fichero
	 * 
	 * @return tiempo medido en milisegundos
	 * 
	 * @see dominio.pcapDumper.SavePacketHandler
	 */
	public long getMFilTime() {
		return	SavePH.getTime();
	}

	/**
	 * Establece la opcion "Pila" Multifile. Cuando llega a un maximo de
	 * ficheros capturados, vuelve a nombrarlo desde el 1
	 * 
	 * @param aux
	 *            numero de pila
	 * 
	 * @see dominio.pcapDumper.SavePacketHandler
	 */
	public void setMFilPila(String aux) {
		try {
			//System.out.println("  -->M Fil Pila " + aux);
			int iTime = Integer.parseInt(aux);
			SavePH.setPila(iTime);
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
	 * 
	 * @see dominio.pcapDumper.SavePacketHandler
	 */
	public void setMFilPilaOffline(String aux) {
		try {
			//System.out.println("  -->M Fil Pila " + aux);
			int iTime = Integer.parseInt(aux);
			OfflineSPH.setPila(iTime);
		} catch (NumberFormatException nfe) {
			System.out.println("NumberFormatException: " + nfe.getMessage());
		}
	}
	
	/**
	 * Devuelve la pila de ficheros establecida.
	 * 
	 * @return numero de pila
	 * 
	 * @see dominio.pcapDumper.SavePacketHandler
	 */
	public int getMFilPila() {
		return SavePH.getPila();
	}

	/**
	 * Establece la captura "Stop" Multifile. Cuando llega a un maximo de
	 * fichero para la captura
	 * 
	 * @param aux
	 *            maximo de ficheros
	 * 
	 * @see dominio.pcapDumper.SavePacketHandler
	 */
	public void setMFilStop(String aux) {
		try {
			//System.out.println("  -->M Fil Stop " + aux);
			int iMax = Integer.parseInt(aux);
			SavePH.setMaximo(iMax);
		} catch (NumberFormatException nfe) {
			System.out.println("NumberFormatException: " + nfe.getMessage());
		}
	}
	
	/**
	 * Establece la captura desde fichero "Stop" Multifile. Cuando llega a un maximo de
	 * fichero para la captura
	 * 
	 * @param aux
	 *            maximo de ficheros
	 * 
	 * @see dominio.pcapDumper.OfflineSavePacketHandler
	 */
	public void setMFilStopOffline(String aux) {
		try {
			//System.out.println("  -->M Fil Stop " + aux);
			int iMax = Integer.parseInt(aux);
			OfflineSPH.setMaximo(iMax);
		} catch (NumberFormatException nfe) {
			System.out.println("NumberFormatException: " + nfe.getMessage());
		}
	}

	/**
	 * Devuelve la captura "Stop" Multifile. Cuando llega a un maximo de
	 * fichero para la captura
	 * 
	 * @return maximo de ficheros
	 * 
	 * @see dominio.pcapDumper.SavePacketHandler
	 */
	public int getMFilStop() {
		return SavePH.getMaximo();
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
	 * Establece la captura en modo promiscuo
	 */
	public boolean getPromiscuo() {
		return promiscuo;
	}
	/**
	 * Establece la captura con un numero maximo de paquetes.
	 * 
	 * @param aux
	 *            numero maximo de paquetes a capturar
	 */
	public void setNumPaquetes(String aux) {
		if (aux!="")PACKET_COUNT = Integer.parseInt(aux);
		else PACKET_COUNT=0;
	}
	
	/**
	 * Establece la captura con un numero maximo de paquetes.
	 */
	public long getNumPaquetes() {
		return PACKET_COUNT;
	}
	
	/**
	 * Establece el vector de ficheros capturados.
	 * 
	 * @param RawP
	 *            vector de capturas
	 */
	public void setPcapV(Vector RawP) {
		this.rawPacket = RawP;
	}

	/**
	 * Obitiene el vector de capturas.
	 * 
	 * @return vector de capturas
	 */
	public Vector getPcapV() {
		return this.rawPacket;
	}

	/**
	 * Establece modo de cartura offline.
	 * @param aux true es offline
	 */
	public void setTypeOffline (boolean aux){
		typeOffline = aux;
	}
	
	/**
	 * Devuelve el tipo de captura.
	 * 
	 * @return true si es offline
	 */
	public boolean getTypeOffline (){
		return typeOffline;
	}
	
	/**
	 * Devuelve el jpcap de captura.
	 * 
	 * @return japcap
	 */
	public JpcapCaptor getPcap(){
		return jpcap;
	}
	
	/**
	 * Devuelve el jpcap de captura.
	 * 
	 * @return japcap
	 */
	public JpcapCaptor getPcap_writer(){
		return jpcap_write;
	}
	/**
	 * Devuelve si ha terminado la captura.
	 * 
	 * @return si la captura terminó
	 */
	public boolean getEndCapture(){
		return endCapture;
	}
} // fin de la clase
