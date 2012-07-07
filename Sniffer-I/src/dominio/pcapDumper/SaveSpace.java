package dominio.pcapDumper;

import java.io.File;

/**
 * Tread que controla lo que ocupa el fichero
 * 
 * @author Leonardo García & JoseRamón Gutierrez
 * @version 2.0
 * 
 * @see dominio.pcapDumper.SaveFileName
 * @see dominio.pcapDumper.SavePacketHandler
 */
public class SaveSpace extends Thread {
	/** * Padre de la clase. */
	public SavePacketHandler venpadre;
	/** * Clase SaveFileName control de nombre de ficheros. */
	private SaveFileName SFName;
	/** * Espacio en bytes de cada fichero. */
	public long spaceBytes;
	/** * tiempo de espera. */
	private long time = 500;
	/** * Fichero. */
	private File f;

	/**
	 * Constructor por defecto de la clase.
	 * 
	 * @param venpadre
	 * 			padre del hilo
	 * @param space
	 * 			espacio que debe de ocupar cada fichero
	 * @param SFN
	 * 			control de nombres
	 * 
	 * @see dominio.pcapDumper.SaveFileName
	 * @see dominio.pcapDumper.SavePacketHandler
	 */
	public SaveSpace(SavePacketHandler venpadre, long space, SaveFileName SFN) {
		// constructor : coger los datos
		setVenPadre(venpadre);
		setSpace(space);
		setSFName(SFN);
		//     this.SFName.setContador(0); se hace al inicializar la clase
		//     SFName.setTime(); Lo hago en la clase SaveFileName
	}

	/**
	 * Ejecuta el control de spacio de cada fichero con los parametros que le
	 * fueron pasados. Se ejecuta cuando es llamado "start" del thread
	 * 
	 * @see dominio.pcapDumper.SaveFileName
	 * @see dominio.pcapDumper.SavePacketHandler
	 */
	public void run() {
		String aux;
			try {
				//System.out.println("\n___espace-> "	+ Long.toString(getVenPadre().getspaceLen()) + "__");
				if ((getVenPadre().getspaceLen() >= getSpace())) {
					SFName.setNext();
					if (SFName.getNext() == -1) {
						getVenPadre().stopCaptura();
					}
					else{
						this.getVenPadre().setTcpDumpWriter(SFName.getNameTime());
						SFName.saveStateMulti(true);
						System.out.println("\n----> "	+ this.getSFName().getNameTime());
						
					}
				}
			} catch (Exception e) {

			}
	} // end run

	/**
	 * Establece la clase padre SaveRawPacketHandler
	 * 
	 * @param padre
	 *            padre
	 * 
	 * @see dominio.pcapDumper.SavePacketHandler
	 */
	private void setVenPadre(SavePacketHandler padre) {
		this.venpadre = padre;
	}

	/**
	 * Devuelve a clase padre SaveRawPacketHandler
	 * 
	 * @return padre
	 * 
	 * @see dominio.pcapDumper.SavePacketHandler
	 */
	private SavePacketHandler getVenPadre() {
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
	 * 
	 * @see dominio.pcapDumper.SaveFileName
	 */
	private void setSFName(SaveFileName SFN) {
		this.SFName = SFN;
	}

	/**
	 * Devuelve la instacia de la clase que gestiona el control del nombre
	 * 
	 * @return nombre de SaveFileName
	 * 
	 * 
	 * @see dominio.pcapDumper.SaveFileName
	 * @see dominio.pcapDumper.SavePacketHandler
	 */
	private SaveFileName getSFName() {
		return this.SFName;
	}
}