package dominio.pcap;

import java.io.IOException;
import java.util.Properties;
import java.util.Vector;
import java.io.File;

import javax.swing.JOptionPane;

import net.sourceforge.jpcap.net.*;
import net.sourceforge.jpcap.util.TcpdumpWriter;
import net.sourceforge.jpcap.capture.*;

/**
 * Handler que recoge los paquetes y los almacena en el fichero en tiempo real.
 * Continuo, por tiempo, por spacio
 * 
 * @author Leonardo García & JoseRamón Gutierrez
 * @version 2.0
 * 
 * @see net.sourceforge.jpcap.capture.RawPacketListener
 * @see net.sourceforge.jpcap.util.TcpdumpWriter
 * @see dominio.pcap.SaveFileName
 * @see dominio.pcap.SaveTime
 * @see dominio.pcap.SaveSpace
 */
class SaveRawPacketHandler implements RawPacketListener {
	/**
	 * para guardar el .pcap
	 */
	private static TcpdumpWriter file; //antes estaba static

	/**
	 * multifile tiempo
	 */

	public long time;

	/**
	 * multifile space
	 */
	public long space;

	/**
	 * Thread multifile para el tiempo
	 */
	private SaveTime STime;

	/**
	 * Thread multifile para el spacio
	 */
	private SaveSpace SSpace;

	/**
	 * Clase que se encarga del control del nombre en multifiles
	 */
	public SaveFileName SFName;

	/**
	 * nombre del fichero
	 */
	public String strFile;

	/**
	 * nombre del fichero auxiliar
	 */
	public String auxStrFile;

	/**
	 * padre de la funcion
	 */
	public Captura venpadre;

	/**
	 * Constructor por defecto de la clase Captura
	 */
	public SaveRawPacketHandler() {
		String aux;
		String strF;
		//para probar dejo pcap en mis documentos
		//pero hay que dejar el primer aux
		//aux = System.getProperty("user.dir") ;//+ "\\" + "captura.pcap";
		aux = "./files/Capturas";
		strF = "captura.pcap";
		System.out.println(aux);
		System.out.println(strF);
		SFName = new SaveFileName(aux, strF);
		setTime(0);
		setSpace(0);
		setPila(0);
		setFile(SFName.getFile());
		//    	setFile(SFName.getNT());
		//setTcpDumpWriter();
	}

	/**
	 * Constructor de la clase SaveRawPacketHandler
	 * 
	 * @param path
	 *            directorio donde se guardara la captura
	 * @param file
	 *            nombre del fichero
	 */
	public SaveRawPacketHandler(String path, String file) {
		SFName = new SaveFileName(path, file);
		setTime(0);
		setSpace(0);
		setPila(0);
		setFile(SFName.getFile());
	}

	/**
	 * Constructor de la clase SaveRawPacketHandler
	 * 
	 * @param fullPath
	 *            ruta y nombre donde se guardara la captura
	 */
	public SaveRawPacketHandler(String fullPath) {
		SFName = new SaveFileName(fullPath);
		setTime(0);
		setSpace(0);
		setPila(0);
		setFile(SFName.getFile());
	}
	
	/**
	 * Constructor por defecto de la clase Captura
	 */
	public SaveRawPacketHandler(SaveFileName SFN) {
		String aux;
		String strF;
		//para probar dejo pcap en mis documentos
		//pero hay que dejar el primer aux
		//aux = System.getProperty("user.dir") ;//+ "\\" + "captura.pcap";
    	aux = "./files/Capturas";
		strF = "captura.pcap";
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
		//setTcpDumpWriter();
	}

	/**
	 * Constructor de la clase SaveRawPacketHandler
	 * 
	 * @param SFN
	 * 			 sllssll
	 * @param path
	 *            directorio donde se guardara la captura
	 * @param file
	 *            nombre del fichero
	 */
	public SaveRawPacketHandler(SaveFileName SFN,String path, String file) {
		//SFName = new SaveFileName(path, file);
		SFName = SFN;
		SFName.setSaveFileName(path,file);
		setTime(0);
		setSpace(0);
		setPila(0);
		setFile(SFName.getFile());
	}
	
	/**
	 * Constructor de la clase SaveRawPacketHandler
	 * 
	 * @param SFN
	 * 			 sllssll
	 * @param fullPath
	 *            ruta y nombre donde se guardara la captura
	 */
	public SaveRawPacketHandler(SaveFileName SFN, String fullPath) {
		//SFName = new SaveFileName(fullPath);
		SFName = SFN;
		SFName.setSaveFileName(fullPath);
		setTime(0);
		setSpace(0);
		setPila(0);
		setFile(SFName.getFile());
	}

	/**
	 * Ejecuta los thread de multifiles de espacio y tiempo si ha sido activado
	 * 
	 * @param vp
	 *            captura
	 */
	public void runHilos(Captura vp) {
		venpadre = vp;
		if (getTime() != 0) {
			STime = new SaveTime(this, getTime(), SFName);
			STime.start();
		} else {
			if (getSpace() != 0) {
				SSpace = new SaveSpace(this, getSpace(), SFName);
				SSpace.start();
			} else {
				setTcpDumpWriter();
			}
		}
		//mientras este activo los hilos (time o Space) siga, sino parar
		//el este run
		// y kien llama a esta clase tiene que estar pendiente de que si esta
		// alive
	}

	/**
	 * Para la captura
	 */
	public void stopCaptura() {
		//stopHilos();
		getVenPadre().endCapture();
	}

	/**
	 * Para los thread de multifiles de espacio y yiempo si han sido activados
	 */
	public void stopHilos() {
		if (STime != null) {
			System.out.println("Destruyendo hiloSaveTiem");
			STime.stop();
		}//OJO AKI ESOT ANTES NO ESTABA
		if (SSpace != null) {
			System.out.println("Destruyendo hiloSaveSpace");
			SSpace.stop();
		}
	}

	/**
	 * Genera la cabecera en el fichero de captura .pcap
	 */
	public void setTcpDumpWriter() {
		try {
			file = new TcpdumpWriter();
			file.writeHeader(getFullName(), 1, 96);
		} catch (IOException e) {
			// TODO Bloque catch generado automáticamente
			e.printStackTrace();
		}
	}

	/**
	 * Genera la cabecera en el fichero de captura. Otro temporal donde despues
	 * pasara a ser el principal. Multifile
	 * 
	 * @param strAuxName
	 *            donde se genera la cabecera .pcap
	 */
	public void setTcpDumpWriter(String strAuxName) {
		try {
			setAuxFile(strAuxName);
			System.out.println("stcdw_aux =>" + getAuxFullName());
			file = new TcpdumpWriter();
			file.writeHeader(getAuxFullName(), 1, 96);
		} catch (IOException e) {
			// TODO Bloque catch generado automáticamente
			e.printStackTrace();
		}

		setFile(strAuxName);
	}

	/**
	 * Cuando se recibe un paquete lo guarda en el fichero .pcap
	 * 
	 * @param rawPacket
	 *            paquete .pcap
	 */
	public void rawPacketArrived(RawPacket rawPacket) {
		try {
			System.out.println("ONLINE..PArrived => " + rawPacket);
			file.appendPacket(getFullName(), rawPacket, 1);
		} catch (Exception exceptionfile) {
			exceptionfile.printStackTrace();
		}

	}

	/**
	 * 
	 * uml.property name="vRawPackets"
	 */
	public void initFilePackets() {
		try {
			System.out.println("@@@@@@@@ initFilePackets");
			file.writeHeader(getFullName(), 1, 96);
		} catch (IOException e) {
			// TODO Bloque catch generado automáticamente
			e.printStackTrace();
		}
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
	 * Retorna el nombre de padre de la clase Captura
	 * 
	 * @return captura
	 */
	public Captura getVenPadre() {
		return venpadre;
	}
}