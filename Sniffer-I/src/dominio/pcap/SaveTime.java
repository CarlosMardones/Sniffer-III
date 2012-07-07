package dominio.pcap;

/**
 * Tread que controla lo el tiempo que esta capurando el fichero
 * 
 * @author Leonardo García & JoseRamón Gutierrez
 * @version 2.0
 * 
 * @see SaveFileName
 * @see SaveRawPacketHandler
 */
public class SaveTime extends Thread {

	public SaveRawPacketHandler venpadre;

	private SaveFileName SFName;

	public long timeSleep;

	private int i;

	/*
	 * private String nameFile; private String nameExtension; private String
	 * dateTime;
	 */

	public SaveTime(SaveRawPacketHandler venpadre, long time, SaveFileName SFN) {
		// constructor : coger los datos
		setVenPadre(venpadre);
		setTime(time);
		setSFName(SFN);
		//     this.SFName.setContador(0); se hace al inicializar la clase
		//     SFName.setTime(); Lo hago en la clase SaveFileName
	}

	public void run() {
		String aux;
		while (true) {
			try {
				SFName.setNext();
				if (SFName.getNext() == -1) {
					getVenPadre().stopCaptura();
				}
				this.getVenPadre().setTcpDumpWriter(SFName.getNameTime());
				System.out.println("getNameTime=> "
						+ this.getSFName().getNameTime());
				sleep(this.getTime()); // dormir proceso
				//this.stop();
			} catch (InterruptedException e) {

			}
		} // end while
	} // end run

	private void setVenPadre(SaveRawPacketHandler padre) {
		this.venpadre = padre;
	}

	private SaveRawPacketHandler getVenPadre() {
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