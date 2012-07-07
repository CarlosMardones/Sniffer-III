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
public class StateHandler extends Thread {
	
	dominio.export.xml_PcapLib.CrearXMLOffline CXMLOffline;
	
	dominio.export.xml_PcapLib.XmlPacketHandler XMLPHandler;
	
	dominio.pcap.SaveFileName SFName ;
	
	StateCaptura ficheroEstate;

	/**
	 * Constructor por defecto de la clase.
	 * 
	 * @param SFN
	 * 			control de nombres
	 *
	 * @param  ficheroEstate fichero
	 * 
	 * @see dominio.pcapDumper.SaveFileName
	 * @see dominio.pcapDumper.SavePacketHandler
	 */
	public StateHandler(dominio.pcap.SaveFileName SFN, StateCaptura ficheroEstate) {
		SFName = SFN;
		this.ficheroEstate = ficheroEstate;
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
		boolean auxBoolean;
		String nomFileOrigen, nomFileDetino;
		int aux;
		while (true) {
			try {

				sleep(500); // dormir proceso
				//this.stop();
			} catch (InterruptedException e) {
				
			}
		} // end while
	} // end run


}