/**
 * Clase OfflineSaveRawPacketHandler
 * 
 * Encargada de guardar los datos recibidos del modo de lectura 
 * offline
 * 
 * @author Leonardo Garca & JoseRamn Gutierrez
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
package dominio.pcapDumper;

import java.io.IOException;

import jpcap.JpcapCaptor;
import jpcap.PacketReceiver;
import jpcap.JpcapWriter;
import jpcap.packet.Packet;

import dominio.pcapDumper.Captura;

/**
 * Handler que recoge los paquetes ne modo offline y los almacena en el fichero .
 * 
 * @author Leonardo Garca & JoseRamn Gutierrez
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
public class OfflineSavePacketHandler implements PacketReceiver {
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
	/** * . */
	private boolean otroFile=false;
	
	/**
	 * Constructor de la clase.
	 * 
	 * @param cap
	 * 			Captura venpadre
	 * @param SFN
	 * 			SaveFileName
	 * @param jpcap
	 * 			jpcap
	 * 
	 * @see dominio.pcapDumper.SaveFileName
	 * @see dominio.pcapDumper.CountPacketHandler 
	 * @see jpcap.JpcapCaptor
	 */
//	SIIIIIIIIIIIIIIII
	public OfflineSavePacketHandler(Captura cap, SaveFileName SFN, JpcapCaptor jpcap) {
		String aux;
		String strF;
		//para probar dejo pcap en mis documentos
		//pero hay que dejar el primer aux
		//aux = System.getProperty("user.dir") ;//+ "\\" + "captura.pcap";
		this.jpcap = jpcap;
		this.venpadre = cap;
    	aux = "./files/Capturas";
		strF = "capturaJpacpLib.pcap";
		System.out.println(aux);
		System.out.println(strF);
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
	}
	
	/**
	 * Constructor de la clase.
	 * 
	 * @param cap
	 * @param SFN
	 * @param jpcap
	 * @param fullPath
	 * 
	 * @see dominio.pcapDumper.SaveFileName
	 * @see dominio.pcapDumper.CountPacketHandler 
	 * @see jpcap.JpcapCaptor
	 */
	public OfflineSavePacketHandler(Captura cap, SaveFileName SFN, JpcapCaptor jpcap, String fullPath) {
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
	}
	
	/**
	 * Constructor por defecto de la clase Captura.
	 */
	public OfflineSavePacketHandler() {
		String aux;
		String strF;
		//para probar dejo pcap en mis documentos
		//pero hay que dejar el primer aux
		//aux = System.getProperty("user.dir") ;//+ "\\" + "captura.pcap";
		aux = "./files/capturas";
		strF = "captura_offline.pcap";
		System.out.println(aux);
		System.out.println(strF);
		SFName = new SaveFileName(aux, strF);
		//setTime(0);
		//setSpace(0);
		//setPila(0);
		setFile(SFName.getFile());
		//OJO si hactivamos SaveSpace o Save Time hay quitarlo de aki
		setTcpDumpWriter();
	}

	/**
	 * Constructor de la clase OfflineSaveRawPacketHandler.
	 * 
	 * @param path
	 *            directorio donde se guardara la captura.
	 * @param file
	 *            nombre del fichero.
	 */
	public OfflineSavePacketHandler(String path, String file) {
		SFName = new SaveFileName(path, file);
		setTime(0);
		setSpace(0);
		setPila(0);
		setFile(SFName.getFile());
	}

	/**
	 * Constructor de la clase OfflineSaveRawPacketHandler.
	 * 
	 * @param fullPath
	 *            ruta y nombre donde se guardara la captura.
	 */
	public OfflineSavePacketHandler(String fullPath) {
		SFName = new SaveFileName(fullPath);
		setTime(0);
		setSpace(0);
		setPila(0);
		setFile(SFName.getFile());
	}

	/**
	 * Genera la cabecera en el fichero de captura .pcap.
	 * 
	 * @see jpcap.JpcapWriter
	 */
	public void setTcpDumpWriter() {
		try {
			//System.out.println("offline_setTcpDumpWriter =>" + getFullName());
			file.close();
			file = JpcapWriter.openDumpFile(jpcap_writer,getFullName());
			resetSpaceLen();
			otroFile=false;

		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Genera la cabecera en el fichero de captura .pcap.
	 * 
	 * @see jpcap.JpcapWriter
	 */
	public void setTcpDumpWriter_first() {
		try {
			//System.out.println("offline_setTcpDumpWriter =>" + getFullName());
			file = JpcapWriter.openDumpFile(jpcap_writer,getFullName());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	

	/**
	 * Genera la cabecera en el fichero de captura. Otro temporal donde despues
	 * pasara a ser el principal. Multifile.
	 * 
	 * @param strAuxName
	 *            donde se genera la cabecera .pcap.
	 * 
	 * @see jpcap.JpcapWriter
	 */
	public void setTcpDumpWriter(String strAuxName) {
		try {
			setAuxFile(strAuxName);
			otroFile = true;
			//System.out.println("stcdw_aux =>" + getAuxFullName());
			//file = JpcapWriter.openDumpFile(jpcap,getAuxFullName());
			resetSpaceLen();
		} catch (Exception e) {
			e.printStackTrace();
		}

		setFile(strAuxName);
	}
	
	/**
	 * Genera la cabecera en el fichero de captura. Otro temporal donde despues
	 * pasara a ser el principal. Multifile.
	 * 
	 * @param strAuxName
	 *            donde se genera la cabecera .pcap.
	 * 
	 * @see jpcap.JpcapWriter
	 */
	public void setTcpDumpWriter_first(String strAuxName) {
		try {
			setAuxFile(strAuxName);
			//System.out.println("stcdw_aux =>" + getAuxFullName());
			file = JpcapWriter.openDumpFile(jpcap,getAuxFullName());
			resetSpaceLen();
		} catch (IOException e) {
			e.printStackTrace();
		}

		setFile(strAuxName);
	}

	/**
	 * Cuando se recibe un paquete lo guarda en el fichero .pcap.
	 * 
	 * @param packet
	 *            paquete .pcap.
	 * 
	 * @see jpcap.JpcapWriter
	 */
	public void receivePacket(Packet packet) {
		try {
			this.nextContPacket();
			file.writePacket(packet);
			contSpaceLen += packet.len;
			//System.out.println("cont packet => " + getNumPacket() + " numero max : " + getNumPacket());
			if (this.getContPacket() >= this.getNumPacket() && (this.getNumPacket() != 0) ){
				stopCaptura();
			}
			if (getspaceLen() >= getSpace() && getSpace() != 0) {
				SFName.setNext();
				if (SFName.getNext() == -1) {
					stopCaptura();
				}
				else{
				setTcpDumpWriter(SFName.getNameTime());
				System.out.println("\n----> "	+ SFName.getNameTime());
				setTcpDumpWriter();
				}
			}
		} catch (Exception exceptionfile) {
			exceptionfile.printStackTrace();
		}

	}


	/**
	 * Ejecuta los thread de multifiles de espacio y tiempo si ha sido activado.
	 * 
	 * @param vp
	 *            captura.
	 */
	public void runHilos(Captura vp) {
		venpadre = vp;
		if (getTime() != 0) {
			//ponerlo si.. STime = new SaveTime(this, getTime(),SFName);
			//STime.start();
		} else {
			if (getSpace() != 0) {
				//				ponerlo si.. SSpace = new SaveSpace(this, getSpace(),SFName);
				//SSpace = new OfflineSaveSpace(this,this.getSpace(),SFName);
				SFName.setNext();
				setTcpDumpWriter_first(SFName.getNameTime());
				//SSpace.start();
			} else {
				setTcpDumpWriter_first();
			}
		}
		//mientras este activo los hilos (time o Space) siga, sino parar
		//el este run
		// y kien llama a esta clase tiene que estar pendiente de que si esta
		// alive
	}

	/**
	 * Para la captura.
	 */
	public void stopCaptura() {
		//stopHilos();
		getVenPadre().stopCaptureThread();
		//System.out.println(".file.closeDumpFile() => YES" );
		file.close();
	}

	/**
	 * Para los thread de multifiles de espacio y yiempo si han sido activados.
	 */
	public void stopHilos() {
		if (STime != null) {
			System.out.println("Destruyendo hiloSaveTiem");
			STime.stop();
		} //OJO AKI ESOT ANTES NO ESTABA
		if (SSpace != null) {
			System.out.println("Destruyendo hiloSaveSpace");
			SSpace.stop();
		}
		//System.out.println("===> closeDumpFile");
		file.close();
	}

	/**
	 * Establece el tiempo para multifiles por tiempo.
	 * 
	 * @param time
	 *            tiempo de captura por cada fichero.
	 */
	public void setTime(long time) {
		this.time = time;
	}

	/**
	 * Devuelve el tiempo de multifiles por tiempo.
	 * 
	 * @return tiempo de captura por cada fichero.
	 */
	public long getTime() {
		return this.time;
	}

	/**
	 * Establece el espacio para multifiles por espacio.
	 * 
	 * @param space
	 *            espacio de captura por cada fichero
	 */
	public void setSpace(long space) {
		this.space = space;
	}

	/**
	 * Devuelve el spcace de multifiles por espacio.
	 * 
	 * @return espacio de captura por cada fichero
	 */
	public long getSpace() {
		return this.space;
	}

	/**
	 * Establece el fichero de captura.
	 * 
	 * @param file
	 *            fichero de captura.
	 */
	public void setFile(String file) {
		this.strFile = file;
	}

	/**
	 * Devuelve el fichero de captura.
	 * 
	 * @return fichero de captura.
	 */
	public String getFile() {
		return this.strFile;
	}

	/**
	 * Establece la pila de captura multifile.
	 * 
	 * @param pila
	 *            pila de ficheros.
	 */
	public void setPila(int pila) {
		SFName.setPila(pila);
	}

	/**
	 * Devuelve la pila de captura multifile.
	 * 
	 * @return pila de ficheros.
	 */
	public int getPila() {
		return SFName.getPila();
	}

	/**
	 * Establece el numero maximo de ficheros que captura.
	 * 
	 * @param max
	 *            numero maximo de ficheros.
	 */
	public void setMaximo(int max) {
		SFName.setMaximo(max);
	}

	/**
	 * Devuelve el numero maximo de ficheros que captura.
	 * 
	 * @return número máximo de ficheros.
	 */
	public int getMaximo() {
		return SFName.getMaximo();
	}

	/**
	 * Establece el fichero de captura auxiliar para el cambio de fichero sin
	 * perder informacion.
	 * 
	 * @param file
	 *            fichero de captura.
	 */
	public void setAuxFile(String file) {
		this.auxStrFile = file;
	}

	/**
	 * Devuelve el fichero de captura auxiliar para el cambio de fichero sin
	 * perder informacion.
	 * 
	 * @return file fichero de captura
	 */
	public String getAuxFile() {
		return this.auxStrFile;
	}

	/**
	 * Devuelve el nombre completo de la ruta y fichero de captura.
	 * 
	 * @return fichero de captura.
	 */
	public String getFullName() {
		return SFName.getPath() + SFName.getSeparator() + getFile();
	}

	/**
	 * Devuelve el nombre completo de la ruta y fichero de captura auxiliar.
	 * 
	 * @return fichero de captura.
	 */
	public String getAuxFullName() {
		return SFName.getPath() + SFName.getSeparator() + getAuxFile();
	}

	/**
	 * Devuelve el nombre de padre de la clase Captura
	 * 
	 * @return captura
	 */
	public Captura getVenPadre() {
		return venpadre;
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
	 * Devuelve el numero maximos de paquetes a capturar
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
	 * Devuelve el numero de paqutes capturados desde el ultimo reseteos
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
	
	
	public void setWriter (JpcapCaptor jWriter){
		this.jpcap_writer = jWriter;
	}
}