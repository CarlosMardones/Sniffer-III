package presentacion.capturandoDumper;

import presentacion.Mediador;

/**
 * Tread que controla lo el tiempo que esta capurando el fichero
 * 
 * @author Leonardo García & JoseRamón Gutierrez
 * @version 2.0
 * 
 * @see presentacion.Mediador
 */
public class ControlHilos extends Thread {
	/**
	 * Número de hilos activos
	 */
	private int i;
	/**
	 * Receptor
	 *  
	 * @see presentacion.Mediador
	 */
	private Mediador med;

	/*
	 * private String nameFile; private String nameExtension; private String
	 * dateTime;
	 */

	/**
	 * Clase por defecto.
	 */
	public ControlHilos(int hilos, Mediador m) {
		this.i = hilos;
		this.med = m;
	}
	
	/**
	 * Arranca el hilo para el control de hilos
	 */
	public void run() {
		while (true) {
			try {
				//System.out.println("..." + Thread.activeCount() + " .. "+ i);
				if (Thread.activeCount() < i) { // antes tenia pueso <=
					med.getFCapture().dispose();
					//System.out.println("..ControHilos => 1  " + i + " " + Thread.activeCount());
					
					//OJOOJO añadi PcapLib
					med.irFinCapture();
					med.irFinCapturePcapLib();
					
					
					//med.AbrirDespuesCaptura();
					//System.out.println("..ControHilos => 2");


/*	EoEo				aki puede estar el problem
					porque no esta cerrado algo por ahi
					quitar esto y probarlo sin ello y no fallara
*/					
					this.stop();
				}

				sleep(500);
			} catch (InterruptedException e) {

			}
		} // end while
	} // end run

}