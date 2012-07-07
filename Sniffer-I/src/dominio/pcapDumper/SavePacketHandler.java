package dominio.pcapDumper;

import java.io.IOException;

//import jdumper.ui.JDStatFrame;


import jpcap.JpcapCaptor;
import jpcap.PacketReceiver;
import jpcap.JpcapWriter;
import jpcap.packet.Packet;
import dominio.pcapDumper.Captura;

/**
 * Handler que recoge los paquetes y los almacena en el fichero en tiempo real.
 * Continuo, por tiempo, por spacio. Puede pararse por tiempo, espacio y
 * número de paquetes
 * 
 * @author Leonardo Garca & Jose Ramón Gutierrez
 * @version 2.0
 * 
 * @see dominio.pcapDumper.Captura
 * @see dominio.pcapDumper.SaveFileName
 * @see dominio.pcapDumper.SaveTime
 * @see dominio.pcapDumper.SaveSpace
 * @see dominio.pcapDumper.CountPacketHandler
 * @see jpcap.PacketReceiver
 * @see jpcap.JpcapCaptor
 * @see jpcap.JpcapWriter
 */
class SavePacketHandler implements PacketReceiver {
	/** * para guardar el .pcap. */
	//private static TcpdumpWriter file; 
	//antes estaba static
	private JpcapWriter file;
	/** * jpcap de captura */
	JpcapCaptor jpcap;
	/** * jpcap de escritura */
	JpcapCaptor jpcap_writer;
	/** * multifile tiempo */
	public long time;
	/** * multifile space */
	public long space;
	/** * Thread multifile para el tiempo */
	private SaveTime STime;
	/** * Thread multifile para el spacio */
	private SaveSpace SSpace;
	/** * Clase que se encarga del control del nombre en multifiles */
	public SaveFileName SFName;
	/** * nombre del fichero */
	public String strFile;
	/** * nombre del fichero auxiliar */
	public String auxStrFile;
	/** * contador de paquetes */
	public long contPacket;
	/** * Numero de paquete maximos */
	public long numPacket;
	/** * Lleva el control por tipo de los paquetes recibos. */	
	public CountPacketHandler RCountPH;
	/** * padre de la funcion de tipo Captura */
	public Captura venpadre;
	/** * Lo que ocupa el fichero */
	static long contSpaceLen;
	/** * Si se lleva el control de los paquetes para su visualizacion. */
	private boolean controlPacket;
	/** * Si es tipo multifichero la captura. */
	private boolean multiFile=false;
	/** * . */
	private boolean otroFile=false;
	
	/**
	 * Constructor de la clase
	 * 
	 * @param cap
	 * 			Captura venpadre
	 * @param SFN
	 * 			SaveFileName
	 * @param CPH
	 * 			CountPacketHandler
	 * @param jpcap
	 * 			jpcap
	 * 
	 * @see dominio.pcapDumper.SaveFileName
	 * @see dominio.pcapDumper.CountPacketHandler 
	 * @see jpcap.JpcapCaptor
	 */
//SIIIIIIIIIIIIIIII
	public SavePacketHandler(Captura cap, SaveFileName SFN, CountPacketHandler CPH, JpcapCaptor jpcap) {
		String aux;
		String strF;
		//para probar dejo pcap en mis documentos
		//pero hay que dejar el primer aux
		//aux = System.getProperty("user.dir") ;//+ "\\" + "captura.pcap";
		this.jpcap = jpcap;
		this.venpadre = cap;
    	aux = "./files/Capturas";
		strF = "capturaJpacpLib.pcap";
		//System.out.println(aux);
		//System.out.println(strF);
		//SFName = new SaveFileName(aux, strF);
		SFN.setSaveFileName(aux,strF);
		SFName = SFN;
		//SFName.setSaveFileName(aux,strF);
		setTime(0);
		setSpace(0);
		setPila(0);
		setFile(SFName.getFile());
		//    	setFile(SFName.getNT());
		setNumPacket(0);
		setContPacket(0);
		RCountPH = CPH;
		contSpaceLen=0;
		controlPacket=false;
	}
	
	/** * 
	 * Constructor de la clase
	 * 
	 * @param cap
	 * @param SFN
	 * @param CPH
	 * @param jpcap
	 * @param fullPath
	 * 
	 * @see dominio.pcapDumper.SaveFileName
	 * @see dominio.pcapDumper.CountPacketHandler 
	 * @see jpcap.JpcapCaptor
	 */
	public SavePacketHandler(Captura cap, SaveFileName SFN, CountPacketHandler CPH, JpcapCaptor jpcap, String fullPath) {
		String aux;
		String strF;
		//para probar dejo pcap en mis documentos
		//pero hay que dejar el primer aux
		//aux = System.getProperty("user.dir") ;//+ "\\" + "captura.pcap";
		this.jpcap = jpcap;
		this.venpadre = cap;
		//System.out.println(fullPath);
		SFN.setSaveFileName(fullPath);
		SFName = SFN;
		//SFName.setSaveFileName(aux,strF);
		setTime(0);
		setSpace(0);
		setPila(0);
		setFile(SFName.getFile());
		//    	setFile(SFName.getNT());
		setNumPacket(0);
		setContPacket(0);
		RCountPH = CPH;
		contSpaceLen=0;
		controlPacket=false;
	}
	
	/**
	 * Constructor de la clase
	 * 
	 * @param SFN
	 * 			 SaveFileName
	 * @param fullPath
	 *            ruta y nombre donde se guardara la captura
	 * 
	 * @see dominio.pcapDumper.SaveFileName
	 * @see dominio.pcapDumper.CountPacketHandler 
	 */
//SIIIIIIIIIIIIIIII	
	public SavePacketHandler(SaveFileName SFN, String fullPath) {
		//SFName = new SaveFileName(fullPath);
		SFName = SFN;
		SFName.setSaveFileName(fullPath);
		setTime(0);
		setSpace(0);
		setPila(0);
		setFile(SFName.getFile());
		setContPacket(0);
		RCountPH = new CountPacketHandler();
		contSpaceLen=0;
		controlPacket=false;
	}

	/**
	 * Ejecuta los thread de multifiles de espacio y tiempo si han sido activado.
	 * 
	 * @see dominio.pcapDumper.SaveTime
	 * @see dominio.pcapDumper.SaveSpace
	 */
	private Thread captureThread;
	public void runHilos() {
		if (getTime() != 0) {
			STime = new SaveTime(this, getTime(), SFName);
			STime.start();
			multiFile=true;
			//System.out.println("Arranco el SaveTime");
		} else {
			if (getSpace() != 0) {
				if (this.getNumPacket() != 0){
					
					captureThread = new Thread(new Runnable(){
						//body of capture thread
						public void run() {
							while (captureThread != null) {
							}
						}
					});
					captureThread.setPriority(Thread.MIN_PRIORITY);
					captureThread.start();
				}
				//SSpace = new SaveSpace(this, getSpace(), SFName);
				//SSpace.start();
				SFName.setNext();
				setTcpDumpWriter_first(SFName.getNameTime());
				SFName.saveStateMulti(true);
				System.out.println("\n----> "	+ SFName.getNameTime());
				multiFile=true;
			} else {
				if (this.getNumPacket() != 0){
					
					captureThread = new Thread(new Runnable(){
						//body of capture thread
						public void run() {
							while (captureThread != null) {
							}
						}
					});
					captureThread.setPriority(Thread.MIN_PRIORITY);
					captureThread.start();
				}
				
				setTcpDumpWriter_first();
				SFName.saveState(true);
				
			}
		}
		//mientras este activo los hilos (time o Space) siga, sino parar
		//el este run
		// y kien llama a esta clase tiene que estar pendiente de que si esta
		// alive
	}


	/**
	 * Para los thread de multifiles de espacio y yiempo si han sido activados.
	 * 
	 * @see dominio.pcapDumper.SaveTime
	 * @see dominio.pcapDumper.SaveSpace
	 */
	public void stopHilos() {
		if (STime != null) {
			//System.out.println("Destruyendo hiloSaveTiem");
			STime.stop();
		}//OJO AKI ESOT ANTES NO ESTABA
		if (SSpace != null) {
			//System.out.println("Destruyendo hiloSaveSpace");
			//SSpace.stop();
			captureThread.stop();
		}
		if (captureThread != null) {
			//System.out.println("Destruyendo hiloNumPaquetes");
			captureThread.stop();
		}

	}

	/**
	 * Genera la cabecera en el fichero de captura .pcap
	 * 
	 * @see jpcap.JpcapWriter
	 */
	public void setTcpDumpWriter_first() {
		try {			
			file = JpcapWriter.openDumpFile(jpcap_writer,getFullName());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	/**
	 * Genera la cabecera en el fichero de captura .pcap
	 * 
	 * @see jpcap.JpcapWriter
	 */
	public void setTcpDumpWriter() {
		try {			
			file.close();
			file = JpcapWriter.openDumpFile(jpcap_writer,getFullName());
			resetSpaceLen();
			otroFile = false;
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Genera la cabecera en el fichero de captura. Otro temporal donde despues
	 * pasara a ser el principal. Multifile
	 * 
	 * @param strAuxName
	 *            donde se genera la cabecera .pcap
	 * 
	 * @see jpcap.JpcapWriter
	 */
	public void setTcpDumpWriter(String strAuxName) {
		JpcapWriter fileAux, fileAux2;
		try {
			setAuxFile(strAuxName);
			otroFile = true;
			//file.close();
			//file = JpcapWriter.openDumpFile(jpcap_writer,getAuxFullName());
			resetSpaceLen();
		} catch (Exception e) {
			e.printStackTrace();
		}
		setFile(strAuxName);
	}

	/**
	 * Genera la cabecera en el fichero de captura. Otro temporal donde despues
	 * pasara a ser el principal. Multifile
	 * 
	 * @param strAuxName
	 *            donde se genera la cabecera .pcap
	 * 
	 * @see jpcap.JpcapWriter
	 */
	public void setTcpDumpWriter_first(String strAuxName) {
		JpcapWriter fileAux, fileAux2;
		try {
			setAuxFile(strAuxName);
			file = JpcapWriter.openDumpFile(jpcap_writer,getAuxFullName());
			resetSpaceLen();
		} catch (IOException e) {
			e.printStackTrace();
		}
		setFile(strAuxName);
	}

	/**
	 * Cuando se recibe un paquete es procesado y lo guarda en el fichero .pcap
	 * 
	 * @param packet
	 *            paquete 
	 * 
	 * @see dominio.pcapDumper.CountPacketHandler
	 * @see jpcap.JpcapWriter
	 */
	public void receivePacket(Packet packet) {
		try {
			this.nextContPacket();
			System.out.print(".");
			//controlPacket=true;
			if (controlPacket == true){
				RCountPH.receivePacket(packet);
			}
			file.writePacket(packet);
			contSpaceLen += packet.len;
			
			if (otroFile == true){
				setTcpDumpWriter();
			}
			//System.out.print("("+contSpaceLen+")");

			if (contSpaceLen >=  getSpace() && getSpace() != 0){
				SFName.setNext();
				if (SFName.getNext() == -1) {
					stopCaptura();
				}
				else{
					setTcpDumpWriter(SFName.getNameTime());
					SFName.saveStateMulti(true);
					System.out.println("\n----> "	+ SFName.getNameTime());
					
				}
			}

			if (this.getContPacket() >= this.getNumPacket() && (this.getNumPacket() != 0) ){
				stopCaptura();
				//getVenPadre().stopCaptureThread();
			}
		} catch (Exception exceptionfile) {
			exceptionfile.printStackTrace();
		}
	}

	/**
	 * Para la captura y genera el fichero de state final
	 * 
	 * @see dominio.pcapDumper.SaveFileName
	 */
	public void stopCaptura() {
		//stopHilos();
		if (multiFile==true){
			SFName.saveStateMulti(false);			
		}
		else{
			SFName.saveState(false);
		}
		setSpace(0);
		
		getVenPadre().stopCaptureThread();
		//System.out.println(".file.closeDumpFile() => YES" );
		file.close();

	}

	/**
	 * retorna el tiempo de multifiles por tiempo
	 * 
	 * @return tiempo de captura por cada fichero
	 */
	public Captura getVenPadre() {
		return this.venpadre;
	}
	
	/**
	 * Establece el tiempo para multifiles por tiempo
	 * 
	 * @param time
	 *            tiempo de captura por cada fichero
	 */
	public void setTime(long time) {
		this.time = time;
	}

	/**
	 * retorna el tiempo de multifiles por tiempo
	 * 
	 * @return tiempo de captura por cada fichero
	 */
	public long getTime() {
		return this.time;
	}

	/**
	 * Establece el espacio para multifiles por espacio
	 * 
	 * @param space
	 *            espacio de captura por cada fichero
	 */
	public void setSpace(long space) {
		this.space = space;
	}

	/**
	 * retorna el spcace de multifiles por espacio
	 * 
	 * @return espacio de captura por cada fichero
	 */
	public long getSpace() {
		return this.space;
	}

	/**
	 * Establece el fichero de captura
	 * 
	 * @param file
	 *            fichero de captura
	 */
	public void setFile(String file) {
		this.strFile = file;
	}

	/**
	 * retorna el fichero de captura
	 * 
	 * @return fichero de captura
	 */
	public String getFile() {
		return this.strFile;
	}

	/**
	 * Establece la pila de captura multifile
	 * 
	 * @param pila
	 *            pila de ficheros
	 */
	public void setPila(int pila) {
		SFName.setPila(pila);
	}

	/**
	 * retorna la pila de captura multifile
	 * 
	 * @return pila de ficheros
	 */
	public int getPila() {
		return SFName.getPila();
	}

	/**
	 * Establece el numero maximo de ficheros que captura
	 * 
	 * @param max
	 *            numero maximo de ficheros
	 */
	public void setMaximo(int max) {
		SFName.setMaximo(max);
	}

	/**
	 * retorna el numero maximo de ficheros que captura
	 * 
	 * @return numero maximo de ficheros
	 */
	public int getMaximo() {
		return SFName.getMaximo();
	}

	/**
	 * Establece el fichero de captura auxiliar para el cambio de fichero sin
	 * perder informacion
	 * 
	 * @param file
	 *            fichero de captura
	 */
	public void setAuxFile(String file) {
		this.auxStrFile = file;
	}

	/**
	 * Retorna el fichero de captura auxiliar para el cambio de fichero sin
	 * perder informacion
	 * 
	 * @return file fichero de captura
	 */
	public String getAuxFile() {
		return this.auxStrFile;
	}

	/**
	 * Retorna el nombre completo de la ruta y fichero de captura
	 * 
	 * @return fichero de captura
	 */
	public String getFullName() {
		return SFName.getPath() + SFName.getSeparator() + getFile();
	}

	/**
	 * Retorna el nombre completo de la ruta y fichero de captura auxiliar
	 * 
	 * @return fichero de captura
	 */
	public String getAuxFullName() {
		return SFName.getPath() + SFName.getSeparator() + getAuxFile();
	}
	
	/**
	 * Número de paquetes máximo a captura
	 * 
	 * @param aux
	 * 			número de paquetes
	 */
	public void setNumPacket (long aux){
		this.numPacket = aux;
	}
	
	/**
	 * Devuelce el numero maximos de paquetes a capturar
	 * 
	 * @return numero paquetes
	 */
	public long getNumPacket (){
		return numPacket;
	}
	
	/**
	 * Pone el contador de paquetes al valor pasado
	 * 
	 * @param aux
	 * 			contador de paquetes
	 */
	public void setContPacket (long aux){
		this.contPacket = aux;
	}

	/**
	 * Devuelce el numero de paqutes capturados desde el ultimo reseteos
	 * 
	 * @return numero de paquetes
	 */
	public long getContPacket (){
		return contPacket;
	}
	
	/**
	 * Aumenta el numero de paquetes recibidos
	 */
	public void nextContPacket(){
		contPacket= contPacket + 1;
	}

	/**
	 * Resetea el espacio que ocupa un conjunto de paquetes
	 */
	public void resetSpaceLen(){
		contSpaceLen=0;
	}
	
	/**
	 * Devuelve lo que ocupan los paquetes capturados desde el último reseteo
	 * @return ocupan los paquetes
	 */
	public long getspaceLen(){
		return contSpaceLen;
	}
	
	/**
	 * Activa el control de paquetes para las estadisticas
	 * 
	 * @param aux
	 * 			true lo activa
	 */
	public void setControlPacket(boolean aux){
		controlPacket=aux;
	}
	
	public void setWriter (JpcapCaptor jWriter){
		this.jpcap_writer = jWriter;
	}
	
}