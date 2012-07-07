package dominio;

/**
 * Clase que accede a la FachadaDominio en modo comando
 * para realizar las operaciones de captura, exportación y from file.
 * 
 * @author Leonardo García & Jose Ramón Gutierrez
 * @version 2.0
 * 
 * @see FachadaDominio
 * @see ModoComando
 * @see EstadoAcciones
 */

public class ComandoAcciones {
	/** * Variable de tipo ModoComando */
	private ModoComando CommandMode = new ModoComando(this);
	/** * Variable de tipo EstadoAcciones*/
	private EstadoAcciones AccionsState = new EstadoAcciones(this);

	/**
	 * Realiza la captura con las configuraciones establecidas en el fichero XML
	 * pasado como parámetros.
	 * 
	 * @param ficheroXML 
	 * 			ruta del fichero XML que contiene la configuración.
	 * @see dominio.FachadaDominio
	 */
	public void runScan(String ficheroXML){
		String strDispo;
		System.out.println("--> Run Scan");
		System.out.println("----> Fichero Parametros => " + ficheroXML);
		try{
			
			if (runScanGetPreferencesCapture(ficheroXML)){
//				FachadaDominio.getPrefBeanCaptura();
				FachadaDominio.openCapturaPcapLib();
				FachadaDominio.setPrefCapturaDumper();
				FachadaDominio.setListenerPcapLib();
				FachadaDominio.startCapturaPcapLib();
				
				setCommandListeners(1);
			}
			else{
				System.out.println("\n-----> FALLO en el fichero de parametrización o no existe");
				endScan(false);
			}
		}catch(java.lang.Exception e){
			
		}	
		//
	}//runScan
	
	/**
	 * Realiza un Snifado con las configuraciones establecidas en el fichero XML
	 * pasado como parametros. Pero el dispositivo de captura es pasado como parametro.
	 *
	 * @param ficheroXML
	 * 			ruta del fichero XML que contiene la configuración.
	 * @param dispositivo
	 * 			posicion en la que se encuentra el dispisitovo al ser mostrado.
	 * @see dominio.FachadaDominio
	 */
	public void runScanDispo(String ficheroXML, int dispositivo){
		String strDispo;
		System.out.println("--> Run Scan");
		System.out.println("----> Fichero Parametros => " + ficheroXML);
		try{

			strDispo = FachadaDominio.isDispositivo(dispositivo);
			if (strDispo != null){
				if (runScanGetPreferencesCapture(ficheroXML)){
					FachadaDominio.getPrefBeanCaptura().setCapDispositive(strDispo);
					FachadaDominio.openCapturaPcapLib();
					FachadaDominio.setPrefCapturaDumper();
					FachadaDominio.setListenerPcapLib();
					FachadaDominio.startCapturaPcapLib();
					
					setCommandListeners(1);
				}
				else{
					System.out.println("\n-----> FALLO en el fichero de parametrización o no existe");
					endScan(false);
				}
			}
			else {
				System.out.println("\n-----> FALLO en la lectura del dispositivo");
				endScan(false);
			}
		}catch(java.lang.Exception e){
			e.printStackTrace();
		}	
		//
	}//runScan
	
	/**
	 * Establece las preferencias de Captura mediante el fichero pasado.
	 *
	 * @param ficheroXML
	 * 			ruta del fichero XML que contiene la configuración
	 * @return true si la configuarcion existe y es correcta
	 * @see dominio.FachadaDominio
	 */
	private boolean runScanGetPreferencesCapture(String ficheroXML){
		FachadaDominio.setPreferencesCapture(ficheroXML);
		return FachadaDominio.getPreferencesCapture(false);
	}
	
	/**
	 * Finalización de la captura. Con el parametro a true fuerza la salida de 
	 * forma correcta.
	 *
	 * @param grabar
	 * 			establece si se va a parar por peticion de usuario.
	 * @see dominio.FachadaDominio
	 */
	public void endScan(boolean grabar){
		if (grabar){
			AccionsState.setEstado(false);
			System.out.println("--> Parado por usuario");
			FachadaDominio.stopCapturaDumperCommand();
			FachadaDominio.saveMetaCaptura();
			//System.out.println("--> Paaaarado por usuario");
			System.out.println("\n--> End Scan");
			System.out.println("-> Fin.Modo Texto");
			//CommandMode.stop();

		}
		else {
			FachadaDominio.stopCapturaDumperCommandOnly();
			System.out.println("\n--> End Scan");
			System.out.println("-> Fin Modo Texto");
			//CommandMode.interrupt();
			//if (CommandMode.isInterrupted()==true){
			//	System.out.println("-> Fue interrumpido");
			//}
			
			CommandMode.stop();
			//AccionsState.stop();
			System.exit(1);
			
		}
	}
	
	/**
	 * Realiza una exportacion de una captura con las configuraciones establecidas 
	 * en el fichero XML pasado como parametro.
	 *
	 * @param ficheroXML
	 * 			ruta del fichero XML que contiene la configuración
	 * @see dominio.FachadaDominio
	 */
	public void runExport(String ficheroXML){
		System.out.println("--> Run Export");
		System.out.println("----> Fichero Parametros => " + ficheroXML);
		try{
			setCommandListeners(2);
			
			FachadaDominio.setPreferencesExport(ficheroXML);
			FachadaDominio.getPreferencesExport();
			FachadaDominio.saveXMLOffline(FachadaDominio.getPrefBeanExport());
		}
		catch(Exception e){
			System.out.println("Algo fallo en la exportacion");
		}
	}//runExport
	
	/**
	 * Finalización de la exportación. Con el parametro a true fuerza la salida pero de 
	 * forma abrupta.
	 *
	 * @param grabar
	 * 			establece si se va a parar por peticion de usuario.
	 * @see dominio.FachadaDominio
	 */
	public void endExport(boolean grabar){
		System.out.println("--> End Export");
		System.out.println("-> Fin Modo Texto");
		System.exit(1);
	}
	
	/**
	 * Realiza una captura desde un fichero con las configuraciones establecidas 
	 * en el fichero XML pasado como parametro.
	 *
	 * @param ficheroXML
	 * 			ruta del fichero XML que contiene la configuración
	 * @see dominio.FachadaDominio
	 */
	public void runFromFile(String ficheroXML){
		System.out.println("--> Run FromFile");
		System.out.println("----> Fichero Parametros => " + ficheroXML);
		try{
			FachadaDominio.setPreferencesFromFile(ficheroXML);
			FachadaDominio.getPreferencesFromFile();
			FachadaDominio.saveMetaOrPcapToFile();
			// REvisar hay que entrar dentro de los hilos
			// o poner otro bit de parada distinto
			//setCommandListeners(3);

		}catch(Exception e ){
			System.out.println("Algo fallo en la captura desde fichero...");
		}

	}//runFromFile
	
	/**
	 * Finalización de la Captura desde fichero. Con el parametro a true fuerza la salida pero de 
	 * forma abrupta.
	 *
	 * @param grabar
	 * 			establece si se va a parar por peticion de usuario.
	 */
	public void endFromFile(boolean grabar){
		System.out.println("--> End FromFile");
		System.out.println("-> Fin Modo Texto");
		System.exit(1);
	}
	
	/**
	 * Realiza una exportación con los parámetros establecidos desde 
	 * el fichero pasado por parametro _estate.
	 *
	 * @param ficheroXML
	 * 			ruta del fichero XML que contiene la configuración
	 * @see dominio.FachadaDominio
	 */
	public void runState(String ficheroXML){
		System.out.println("--> Run State");
		System.out.println("----> Fichero Parametros => " + ficheroXML);
		try{
			setCommandListeners(4);
			FachadaDominio.saveXMLfromEstate(ficheroXML);
		}catch(Exception e ){
			System.out.println("Algo fallo en la captura desde fichero...");
		}

	}//runFromFile
	
	/**
	 * Finalización de exportación desde _estate. Con el parametro a true fuerza la salida pero de 
	 * forma abrupta.
	 *
	 * @param grabar
	 * 			establece si se va a parar por peticion de usuario.
	 */
	public void endState(boolean grabar){
		System.out.println("--> End Estate");
		System.out.println("-> Fin Modo Texto");
		System.exit(1);
	}
	
	/**
	 * Establece los listenes necesarios para que en los procesos de captura
	 * , esportación o captura desde fichero el usuario pueda pararlos.
	 *
	 * @param tipo
	 * 			1 modo captura
	 * 			2 modo exportación
	 * 			3 modo captura desde fichero
	 * @see dominio.ModoComando
	 * @see dominio.ComandoAcciones
	 */
	private void setCommandListeners(int tipo){
		CommandMode.setTipo(tipo);
		AccionsState.setTipo(tipo);
		CommandMode.start();
		AccionsState.start();
	}
	
	/**
	 * Muestra o mustra y pide el dispositivo de captura.
	 * 
	 * @param pedirDispo
	 * 			true muestra los dispositivos y pide uno de ellos,
	 * 			false sólo muestra los dispositivos.
	 * @return -1 di erro,
	 * 			0 para salir si seleccionar ninguno,
	 * 			num la opción elegida.
	 * @see dominio.ModoComando
	 */
	public int pedirDispositivoCaptura(boolean pedirDispo){
		String dispo[];
		String name;
		String lee = "";
		int opc;
		try{	
			dispo = FachadaDominio.getCapDispositivosPcapLibDes();

			if (pedirDispo){
				opc=-1; 
				while (opc < 0 || opc > dispo.length){
					System.out.println("\nDispositivos disponibles:\n");
					for (int i = 0; i < dispo.length; i++){
						name = dispo[i].toString().trim();
						System.out.println(" " + (i+1) + " -> " + name);
					}
					System.out.println("\n Pulse 0 para Salir -> ");
					System.out.print("\nSelecciona el dispositivo: ");
					lee=CommandMode.pedirPorTeclado();
					opc=Integer.parseInt(lee);
					if ((opc < 0 || opc > dispo.length)){
						System.out.println("\n!!!Opcion no valida: \n");
					}
				}
			}
			else{
				opc=0; 
				System.out.println("\nDispositivos disponibles:\n");

				for (int i = 0; i < dispo.length; i++){
					name = dispo[i].toString().trim();
					System.out.println(" " + (i+1) + " -> " + name);
				}
			}
			return opc;
		} catch (java.lang.NumberFormatException e) {
			return -1;
		}
	}
	
	
}//Clase ModoComando
