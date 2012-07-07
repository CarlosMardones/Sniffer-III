package dominio.pcapDumper;

import java.io.File;

/**
 * Tread que controla lo que ocupa el fichero
 * 
 * @author Leonardo García & JoseRamón Gutierrez
 * @version 2.0
 * 
 * @see SaveFileName
 * @see OfflineSavePacketHandler
 */
public class OfflineSaveSpace extends Thread {
	/** * padre de la clase. */
	public OfflineSavePacketHandler venpadre;
	/** * Clase SaveFileName control de nombre de ficheros. */
	private SaveFileName SFName;
	/** * Espacio en bytes de cada fichero. */
	public long spaceBytes;
	/** * tiempo de espera. */
	private long time = 1;
	/** * Fichero. */
	private File f;

	/**
	 * Constructor por defecto
	 * 
	 * @param venpadre
	 * 			padre del hilo
	 * @param space
	 * 			espacio que debe de ocupar cada fichero
	 * @param SFN
	 * 			control de nombres
	 */
	public OfflineSaveSpace(OfflineSavePacketHandler venpadre, long space, SaveFileName SFN) {
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
		boolean primeraVez = true;
		while (true) {
			try {
				//System.out.println("\n___espace-> "	+ Long.toString(getVenPadre().getspaceLen()) + "__");
				if ((getVenPadre().getspaceLen() >= getSpace())) {
					SFName.setNext();
					if (SFName.getNext() == -1) {
						getVenPadre().stopCaptura();
					}
					else{
						if (primeraVez == true){
							primeraVez=false;
							this.getVenPadre().setTcpDumpWriter_first(SFName.getNameTime());
						}
						else{
							this.getVenPadre().setTcpDumpWriter(SFName.getNameTime());
						}
						
						//this.getVenPadre().setTcpDumpWriter(SFName.getNameTime());
						System.out.println("\n----> "	+ this.getSFName().getNameTime());
					}
				}
			} catch (Exception e) {
				
			}
		} // end while
	} // end run

	/**
	 * Establece la clase padre SaveRawPacketHandler
	 * 
	 * @param padre
	 *            padre
	 */
	private void setVenPadre(OfflineSavePacketHandler padre) {
		this.venpadre = padre;
	}

	/**
	 * Devuelve a clase padre SaveRawPacketHandler
	 * 
	 * @return padre
	 */
	private OfflineSavePacketHandler getVenPadre() {
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