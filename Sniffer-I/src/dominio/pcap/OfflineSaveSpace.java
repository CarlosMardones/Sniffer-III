package dominio.pcap;

import java.io.File;

/**
 * Tread que controla lo que ocupa el fichero
 * 
 * @author Leonardo García & JoseRamón Gutierrez
 * @version 2.0
 * 
 * @see dominio.pcap.SaveFileName
 * @see dominio.pcap.SaveRawPacketHandler
 */
public class OfflineSaveSpace extends Thread {
	/**
	 * padre de la clase
	 */
	public OfflineSaveRawPacketHandler venpadre;

	/**
	 * Clase SaveFileName control de nombre de ficheros
	 */
	private SaveFileName SFName;

	/**
	 * Espacio en bytes de cada fichero
	 */
	public long spaceBytes;

	/**
	 * tiempo de espera
	 */
	private long time = 500;

	/**
	 * Fichero
	 */
	private File f;

	/**
	 * Constructor por defecto de la clase SaveFileName
	 */
	public OfflineSaveSpace(OfflineSaveRawPacketHandler venpadre, long space, SaveFileName SFN) {
		// constructor : coger los datos
		setVenPadre(venpadre);
		setSpace(space);
		setSFName(SFN);
		//     this.SFName.setContador(0); se hace al inicializar la clase
		//     SFName.setTime(); Lo hago en la clase SaveFileName
	}

	/**
	 * Ejecuta el control de sapcio de cada fichor con los parametros que le
	 * fueron pasados. Se ejecuta cuando es llamado "start" del thread
	 */
	public void run() {
		String aux;
		long fileSpace = -1;
		while (true) {
			try {
				if (SFName.getNext() == -1) {
					getVenPadre().stopCaptura();
				}
				if ((fileSpace > getSpace()) || (fileSpace == -1)) {
					SFName.setNext();

					this.getVenPadre().setTcpDumpWriter(SFName.getNameTime());
					System.out.println("getNameTime=> "
							+ this.getSFName().getNameTime());
					System.out.println("SaveSpace    =>    ENTRA");
				}
				f = new File(SFName.getFullNameTime());
				//f = new File(SFName.getFullPath());
				boolean exists = f.exists();
				if (exists) {
					fileSpace = f.length();
					System.out.println("SaveSpace    =>    " + fileSpace + " => " + getSpace());
				}

				sleep(time); // dormir proceso
				//this.stop();
			} catch (InterruptedException e) {

			}
		} // end while
	} // end run

	/**
	 * Establece la clase padre SaveRawPacketHandler
	 * 
	 * @param padre
	 *            padre
	 */
	private void setVenPadre(OfflineSaveRawPacketHandler padre) {
		this.venpadre = padre;
	}

	/**
	 * Devuelve a clase padre SaveRawPacketHandler
	 * 
	 * @return padre
	 */
	private OfflineSaveRawPacketHandler getVenPadre() {
		return venpadre;
	}

	/**
	 * Establece el espacio para cada fichero multifile
	 * 
	 * @param t
	 *            espacio de fichero
	 */
	private void setSpace(long t) {
		this.spaceBytes = t;
	}

	/**
	 * Devuelve el espacio para cada fichero multifile
	 * 
	 * @return espacio de fichero
	 */
	private long getSpace() {
		return this.spaceBytes;
	}

	/**
	 * Establece la instacia de la clase que gestiona el control del nombre
	 * 
	 * @param SFN
	 *            nombre de SaveFileName
	 */
	private void setSFName(SaveFileName SFN) {
		this.SFName = SFN;
	}

	/**
	 * Devuelve la instacia de la clase que gestiona el control del nombre
	 * 
	 * @return nombre de SaveFileName
	 */
	private SaveFileName getSFName() {
		return this.SFName;
	}

}