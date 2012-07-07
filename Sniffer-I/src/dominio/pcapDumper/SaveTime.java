package dominio.pcapDumper;

/**
 * Tread que controla lo el tiempo que esta capurando el fichero
 * 
 * @author Leonardo García & JoseRamón Gutierrez
 * @version 2.0
 * 
 * @see SaveFileName
 * @see SavePacketHandler
 */
public class SaveTime extends Thread {
	/** * Padre de la clase. */
	public SavePacketHandler venpadre;
	/** * Clase SaveFileName control de nombre de ficheros. */
	private SaveFileName SFName;
	/** * Tiempo en segundos de cada fichero. */
	public long timeSleep;
	/**
	 * Constructor por defecto de la clase.
	 * 
	 * @param venpadre
	 * 			padre del hilo
	 * @param time
	 * 			espacio que debe de ocupar cada fichero
	 * @param SFN
	 * 			control de nombres
	 * 
	 * @see dominio.pcapDumper.SaveFileName
	 * @see dominio.pcapDumper.SavePacketHandler
	 */
	public SaveTime(SavePacketHandler venpadre, long time, SaveFileName SFN) {
		// constructor : coger los datos
		setVenPadre(venpadre);
		setTime(time);
		setSFName(SFN);
		//     this.SFName.setContador(0); se hace al inicializar la clase
		//     SFName.setTime(); Lo hago en la clase SaveFileName
	}

	/**
	 * Ejecuta el control de tiempo de cada fichero con los parametros que le
	 * fueron pasados. Se ejecuta cuando es llamado "start" del thread
	 * 
	 * @see dominio.pcapDumper.SaveFileName
	 * @see dominio.pcapDumper.SavePacketHandler
	 */
	public void run() {
		boolean primeraVez = true;
		String aux;
		while (true) {
			try {
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
					SFName.saveStateMulti(true);
					System.out.println("\n----> "	+ this.getSFName().getNameTime());
				}
				sleep(this.getTime()); // dormir proceso
				//this.stop();
			} catch (InterruptedException e) {
				
			}
		} // end while
	} // end run

	private void setVenPadre(SavePacketHandler padre) {
		this.venpadre = padre;
	}

	private SavePacketHandler getVenPadre() {
		return venpadre;
	}

	private void setTime(long t) {
		this.timeSleep = t;
	}

	private long getTime() {
		return this.timeSleep;
	}

	private void setSFName(SaveFileName SFN) {
		this.SFName = SFN;
	}

	private SaveFileName getSFName() {
		return this.SFName;
	}

}