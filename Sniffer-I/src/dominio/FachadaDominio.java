package dominio;

import java.io.File;
import java.io.IOException;
import java.util.Vector;

import jpcap.NetworkInterface;

//import dominio.pcap.*;
import dominio.pcapDumper.*;
import dominio.export.script.*;
import dominio.preferences.*;
import dominio.properties.*;
import dominio.preferences.capture.*;
import dominio.preferences.definicion.PreferencesCheckDefinicion;
import dominio.preferences.definicion.PreferencesSnifferDefinicion;
import dominio.browser.*;


/**
 * Fachada para acceder a la parte de dominio de la aplicación.
 * 
 * @author Leonardo Garca & Jose Ramón Gutierrez & Carlos Mardones Muga
 * @version 3.0
 * 
 * @see dominio.browser.UtilNavegador
 * @see dominio.pcapDumper.Captura
 * @see dominio.properties.PropertiesFileRead
 * @see dominio.preferences.preferencesBeanCapture
 * @see dominio.preferences.preferencesBeanExport
 * @see dominio.preferences.preferencesBeanFromFile
 * @see dominio.preferences.preferencesBeanMeta
 * @see dominio.preferences.preferencesBeanDefinicion
 * @see jpcap.NetworkInterface
 */
public class FachadaDominio {
	/** * Preferencias del programa. */
	private static preferencesFileRead preferences;
	/** * Propiedades del programa. */
	private static PropertiesFileRead properties;
	/** * Instalcia de la captura. */
	private static dominio.pcap.Captura pcap;
	/** * Estado de captura. */
	private static boolean Capturando;
	/** * variable de captura. */
	private static dominio.pcapDumper.Captura jpcap;
	/** * Último fichero leído. */
	private static String lastFileReaded;
	/** * Utilizada en modo comando y es para saber si se ha iniciado
	 * una exportacion o un fromfile. */
	private static boolean stateCommandActivo=true;

	/**
	 * Lanza el programa por defecto que se encarga de abrir ficheros html.
	 * 
	 * @param ruta
	 *            ruta del fichero html que contenga el indice de la ayuda.
	 * @see dominio.browser.UtilNavegador
	 * @see dominio.browser.UtilNavegador#abrirURL(String)
	 */
	public static void mostrarAyudaSniffer(String ruta) {
		try {
			UtilNavegador.abrirURL(ruta);
		} catch (IOException e) {
			System.out.println("El fichero xml no se ha podido abrir");
		}
	}//mostrarAyudaSniffer

	
	////////////-----__________-----
	////////////-----__________-----
	//////////// Nueva pcap lib
	////////////-----__________-----
	////////////-----__________-----
	/**
	 * Crea la instancia de la captura.
	 * 
	 * @return instancia de la captura
	 * @see dominio.pcapDumper.Captura
	 */
	public static dominio.pcapDumper.Captura crearPcapLib() {
		if(jpcap!=null){
			//pcap.stop();
			//jpcap=null;
		}
		dominio.pcapDumper.Captura aux = new dominio.pcapDumper.Captura();
		jpcap = aux;
		//System.out.println("--> Create Captura");

		return jpcap;
	}//crearPcap

	/**
	 * Crea la instancia de captura
	 * 
	 * @see dominio.pcapDumper.Captura 
	 */
	public static void setPcapLib() {
		jpcap = FachadaDominio.crearPcapLib();
	}//getPcap
	
	/**
	 * Devuelve las instancia de captura.
	 * 
	 * @return instacia de captura
	 * @see dominio.pcapDumper.Captura
	 */
	public static dominio.pcapDumper.Captura getPcapLib() {
		return jpcap;
	}//getPcap
	
	/**
	 * Devuelve los dispositivos detectado para la captura.
	 * 
	 * @return dispositivos de captura
	 * 
	 * @see jpcap.NetworkInterface
	 * @see dominio.pcapDumper.Captura 
	 */
	public static NetworkInterface[] getCapDispositivosPcapLib() {
		NetworkInterface[] aux;
		if (jpcap!=null){
			if (jpcap.getPcap() != null ){
				jpcap.getPcap().close();
			}
			if (jpcap.getPcap_writer() !=null){
			//	jpcap.getPcap_writer().close();
			}
		}
		dominio.pcapDumper.Captura jpcap = crearPcapLib();
		aux = jpcap.capDispositivos();
		return aux;
	}//getCapDispositivos

	/**
	 * Devuelve los dispositivos detectado para la captura.
	 * 
	 * @return dispositivos de captura
	 * @see jpcap.NetworkInterface
	 * @see dominio.pcapDumper.Captura
	 */
	public static String[] getCapDispositivosPcapLibDes() {
		NetworkInterface[] devices;
		devices = getCapDispositivosPcapLib();
		String[] names=new String[devices.length];
		for(int i=0;i<names.length;i++)
			names[i]=(devices[i].description==null?devices[i].name:devices[i].description);
		return names;
	}//getCapDispositivos
	
	/**
	 * Abre la captura con el dispositivo leído o seleccionado.
	 * 
	 * @see dominio.pcapDumper.Captura 
	 * @see dominio.preferences.preferencesBeanCapture
	 */
	public static void openCapturaPcapLib() throws Exception {
		String aux;
		NetworkInterface dispo;
		
		try {
			//System.out.println("----> Open Captura START");
			dominio.pcapDumper.Captura jpcap = crearPcapLib();
			System.out.println("----> Dispositivo => " + getPrefBeanCaptura().getCapDispositive());
			//jpcap.openCaptura(getPrefBeanCaptura().getCapDispositive());
			//aux=getPrefBeanCaptura().getCapDispositive();
			//System.out.println("--> " + aux);
			//dispo = isDispositivo(aux);
			//System.out.println("--> " + dispo.toString().trim());
			jpcap.openCaptura(isDispositivo(getPrefBeanCaptura().getCapDispositive()));
			//pcap.start();
			//System.out.println("--> Open Captura END");
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}

	}//openCaptura
	
	/**
	 * Comienza la captura real en fichero.
	 * 
	 * @see dominio.pcapDumper.Captura
	 */
	public static void startCapturaPcapLib() {
		//System.out.println("--> Start FromFile");
		Capturando = true;
		getPcapLib().start();
		//System.out.println("");
	}//startCaptura
	
	/**
	 * Comienza la captura desde fichero.
	 * 
	 * @see dominio.pcapDumper.Captura
	 */
	public static void startCapturaPcapLibMeta() {
		//System.out.println("--> Start FromFile");
		Capturando = true;
		getPcapLib().stop();
		getPcapLib().interrupt();
		getPcapLib().start();
		//System.out.println("");
	}//startCaptura
	
	
	/**
	 * Obtine las preferencias que han sido leídas o actualizadas en el programa.
	 * Estableciendo los listener para la multicaptura.
	 * 
	 * @see dominio.pcapDumper.Captura
	 * @see dominio.preferences.preferencesBeanCapture
	 */
	public static void setListenerPcapLib() {
		String aux = "0";
		getPcapLib().setListener();
		getPcapLib().setTypeOffline(false);
		if (getPrefBeanCaptura().getFilMultipleFileId() == true) {
			if (getPrefBeanCaptura().getFilSpaceId() == true) {
				aux = getMFilSpaceBytes(getPrefBeanCaptura().getFilSpace(),
						getPrefBeanCaptura().getFilSpaceType());
				getPcapLib().setMFilSpace(aux);
			}
			if (getPrefBeanCaptura().getFilTimeId() == true) {
				aux = getMFilTimeSegundos(getPrefBeanCaptura().getFilTime(),
						getPrefBeanCaptura().getFilTimeType());
				getPcapLib().setMFilTime(aux);
			}

			if (getPrefBeanCaptura().getFilRingBufferId() == true) {
				getPcapLib().setMFilPila(getPrefBeanCaptura().getFilRingBuffer());
			}
			if (getPrefBeanCaptura().getFilStopAfterId() == true) {
				getPcapLib().setMFilStop(getPrefBeanCaptura().getFilStopAfter());
			}
		} else {
			if (getPrefBeanCaptura().getstpAfterSpaceId() == true) {
				aux = getMFilSpaceBytes(
						getPrefBeanCaptura().getstpAfterSpace(),
						getPrefBeanCaptura().getstpAfterSpaceType());
				getPcapLib().setMFilSpace(aux);
				getPcapLib().setMFilStop("1");
			} else {
				if (getPrefBeanCaptura().getstpAfterTimeId() == true) {
					aux = getMFilTimeSegundos(getPrefBeanCaptura()
							.getstpAfterTime(), getPrefBeanCaptura()
							.getstpAfterTimeType());
					getPcapLib().setMFilTime(aux);
					getPcapLib().setMFilStop("1");
				}
			}
		}
		if (getPrefBeanCaptura().getstpAfterPacketsId() == true){
			getPcapLib().setNumPaquetes(getPrefBeanCaptura().getstpAfterPackets());
		}

	}//setListener
	
	/**
	 * Establece el control de paquetes.
	 * @param aux
	 * 			true si se activa, false desactivado
	 * @see dominio.pcapDumper.Captura
	 */
	public static void setControlPacket(boolean aux) {
		getPcapLib().setControlPacket(aux);
	}
	
	/**
	 * Establece el fichero de captura desde fichero.
	 * 
	 * @see dominio.preferences.preferencesBeanCapture
	 * @see dominio.pcapDumper.Captura
	 */
	public static void setFilePcapLibOpenOffline() {
		FachadaDominio.setPcapLib();
		FachadaDominio.getPcapLib().openOffline(getPrefBeanFromFile().getffCapSource());
	}//setListener
	
	/**
	 * Obtine las preferencias que han sido leidas o actualizadas en el programa.
	 * Estableciendo los listener para la multicaptura. Captura desde fichero.
	 * 
	 * @see dominio.preferences.preferencesBeanCapture
	 * @see dominio.pcapDumper.Captura
	 */
	public static void setListenerPcapLibOffline() {
		String aux = "0";
		getPcapLib().setListenerOffline();
		getPcapLib().setTypeOffline(true);
		if (getPrefBeanFromFile().getffFilMultipleFileId() == true) {
			if (getPrefBeanFromFile().getffFilSpaceId() == true) {
				aux = getMFilSpaceBytes(getPrefBeanFromFile().getffFilSpace(),
						getPrefBeanFromFile().getffFilSpaceType());
				getPcapLib().setMFilSpaceOffline(aux);
			}
			/*
			 * if (getPrefBeanFromFile().getffFilTimeId() == true) {
				aux = getMFilTimeSegundos(getPrefBeanFromFile().getFilTime(),
						getPrefBeanFromFile().getFilTimeType());
				getPcapLib().setMFilTimeOffline(aux);
			}
			*/
			if (getPrefBeanFromFile().getffFilRingBufferId() == true) {
				getPcapLib().setMFilPilaOffline(getPrefBeanFromFile().getffFilRingBuffer());
			}
			if (getPrefBeanFromFile().getffFilStopAfterId() == true) {
				getPcapLib().setMFilStopOffline(getPrefBeanFromFile().getffFilStopAfter());
			}
		} else {
			/*
			if (getPrefBeanFromFile().getstpAfterSpaceId() == true) {
				aux = getMFilSpaceBytes(
						getPrefBeanFromFile().getstpAfterSpace(),
						getPrefBeanFromFile().getstpAfterSpaceType());
				getPcapLib().setMFilSpace(aux);
				getPcapLib().setMFilStop("1");
			} else {
				if (getPrefBeanFromFile().getstpAfterTimeId() == true) {
					aux = getMFilTimeSegundos(getPrefBeanFromFile()
							.getstpAfterTime(), getPrefBeanFromFile()
							.getstpAfterTimeType());
					getPcapLib().setMFilTime(aux);
					getPcapLib().setMFilStop("1");
				}
			}*/
		}
		if (getPrefBeanFromFile().getffStpAfterPacketsId() == true){
			getPcapLib().setNumPaquetes(getPrefBeanFromFile().getffStpAfterPackets());
		}
	}//setListener
	/**
	 * Devuelve el numero de thread activos en una captura.
	 * 
	 * @return numeros de thread activos
	 */
	public static CountPacketHandler getCountPacketHandler(){
		return getPcapLib().getCountPacketHandler(); 
	}
	
	/**
	 * Para la captura.
	 *  
	 * @see dominio.pcapDumper.Captura
	 */
	public static void stopCapturaDumper() {
		getPcapLib().endCapture(false);
		//System.out.println("-Fachada-> Stop Captura");
		if (getPcapLib() != null)
			jpcap = null;
		Capturando = false;

	}//stopCaptura
	

	/**
	 * Para la captura.
	 *  
	 * @see dominio.pcapDumper.Captura
	 */
	public static void stopCaptura() {
		//System.out.println("-- Fachada -> StopCaptura");
		getPcapLib().endCapture(false);
		if (getPcap() != null)
			pcap = null;
		Capturando = false;

	}//stopCaptura
	
	/**
	 * Para la captura.
	 *  
	 * @see dominio.pcapDumper.Captura
	 */
	public static void stopCapturaPcapLib() {
//		System.out.println("-- Fachada -> StopCaptura");
		//getPcapLib().endCapture(false);
		getPcapLib().stopCaptureThread();
		Capturando = false;
		
//		System.out.println("-- Fachada -> StopCaptura FIN");
	}//stopCaptura
	
	/**
	 * Para la captura.
	 *  
	 * @see dominio.pcapDumper.Captura
	 */
	public static boolean getEndCapture(){
		return getPcapLib().getEndCapture();
	}
	/**
	 * Para la captura
	 *  
	 * @see dominio.pcapDumper.Captura
	 */
	public static boolean getEndAll(){
		boolean state;
		state = getPcapLib().finSaveMeta;
		if  (state == true){
		if (getPcapLib() != null){
			pcap = null;
		}
		}
		return state;
	}
	/**
	 * Devuelve estado de la captura. true capturando, false no capturando.
	 * 
	 * @return el estado de la captura
	 */
	public static boolean getEstadoCaptura(){
		return Capturando;
	}
	
	/**
	 * Devuelve estado de la exportacoin. True exportando, false no exportando.
	 * 
	 * @return el estado de la exportacion
	 */
	public static boolean getRunningExport(){
		return stateCommandActivo;
	}
	/**
	 * Devuelve estado de la exportacoin. True exportando, false no exportando.
	 * 
	 * @return el estado de la exportacion
	 * @see dominio.pcapDumper.Captura
	 */
	public static boolean getEndFromFile(){
		return getPcapLib().getEndCapture();
	}
	/**
	 * Para la captura desde el modo comando
	 * @see dominio.pcapDumper.Captura 
	 */
	public static void stopCapturaDumperCommand() {
		getPcapLib().endCaptureCommand();
		//FachadaDominio.getPcapLib().stop();

	}//stopCaptura

	/**
	 * Para la captura desde el modo comando
	 * @see dominio.pcapDumper.Captura 
	 */
	public static void stopCapturaDumperCommandOnly() {
		//getPcapLib().endCaptureCommand();
		getPcapLib().endCaptureCommand();

	}//stopCaptura
	/**
	 * Establece el fichero leído.
	 * 
	 * @param ffile
	 * 			fichero leído
	 */
	public static void setFileReaded(String ffile){
		lastFileReaded = ffile;
	}
	/**
	 * Devuelve el fichero leído
	 * 
	 * @return fichero
	 */
	public static String getFileReaded(){
		return lastFileReaded;
	}
	/**
	 * Guarda el fichero .META con las opciones de captura y lo capturado.
	 * 
	 * @see dominio.preferences.preferencesBeanCapture
	 * @see dominio.pcapDumper.Captura
	 */
	public static void saveMetaCaptura() {
		dominio.pcapDumper.SaveFileName auxSFN;
		preferencesBeanMeta pBM;
		preferencesBeanCapture pBC;
		PreferencesSniffer PS;
		String sNameFile;
		String sNamePathFile;
		
		auxSFN = getPcapLib().getSFname();
		/*System.out.println("CREANDO META DATOS");
		System.out.println("-> Pila: " + auxSFN.getPila());
		System.out.println("-> Start :" + auxSFN.getPrimero());
		System.out.println("-> End :" + auxSFN.getUltimo());
		System.out.println("-> Path :" + auxSFN.getPath());
		System.out.println("-> File :" + auxSFN.getNameFile());
		System.out.println("-> File :" + auxSFN.getFile());
		*/
		//Si multifiles activado entondes auxSFN.getNT(), sino auxSFN.getFile()
		pBM = getPrefBeanMeta();
		pBC = getPrefBeanCaptura();
		pBM.setMetLocRelativeId(false);
		pBM.setMetLocRelative("");
		pBM.setMetLocAbsotuteId(true);
		if (pBC.getFilMultipleFileId() == true|| (pBC.getstpAfterSpaceId() == true || pBC.getstpAfterTimeId() ==true)) sNameFile =auxSFN.getNTwithout();
		else sNameFile = auxSFN.getNameFile();
		sNamePathFile = auxSFN.getPath() + auxSFN.getBarra() + sNameFile;
		
		pBM.setMetLocAbsotutePath(auxSFN.getPath());
		pBM.setMetLocAbsotuteName(sNameFile);
		
		pBM.setMetMultipleFileId(pBC.getFilMultipleFileId());
		pBM.setMetMFRingBufferId(pBC.getFilRingBufferId());
		pBM.setMetMFRingBuffer(pBC.getFilRingBuffer());
		if ((auxSFN.getPrimero()> auxSFN.getPila()) && pBC.getFilRingBufferId() == true) auxSFN.setNext();
		pBM.setMetMFStar(Integer.toString(auxSFN.getPrimero()));
		pBM.setMetMFEnd(Integer.toString(auxSFN.getUltimo()));
		
		PS = new PreferencesSniffer();
		PS.GenerateCapture(pBC);
		PS.GenerateMeta(pBM);
		//setfile -> para poner el nombre y lugar del fichero
		PS.setFile(sNamePathFile + "_META.XML");
		PS.savePreferences();
		//System.out.println("Paso Grabar Preferencias");
		
	}//stopCaptura
	
	/**
	 * Guarda el fichero .META con las opciones de captura desde fichero y lo capturado.
	 * 
	 * @see dominio.preferences.preferencesBeanCapture
	 * @see dominio.pcapDumper.Captura
	 */
	public static void saveMetaCapturaOffline() {
		dominio.pcapDumper.SaveFileName auxSFN;
		preferencesBeanMeta pBM;
		preferencesBeanFromFile PBFF;
		PreferencesSniffer PS;
		String sNameFile;
		String sNamePathFile;
		
		auxSFN = getPcapLib().getSFname();
		/*System.out.println("CREANDO META DATOS");
		System.out.println("-> Pila: " + auxSFN.getPila());
		System.out.println("-> Start :" + auxSFN.getPrimero());
		System.out.println("-> End :" + auxSFN.getUltimo());
		System.out.println("-> Path :" + auxSFN.getPath());
		System.out.println("-> File :" + auxSFN.getNameFile());
		System.out.println("-> File :" + auxSFN.getFile());
		*/
		//Si multifiles activado entondes auxSFN.getNT(), sino auxSFN.getFile()
		pBM = getPrefBeanMeta();
		PBFF = getPrefBeanFromFile();
		pBM.setMetLocRelativeId(false);
		pBM.setMetLocRelative("");
		pBM.setMetLocAbsotuteId(true);
		if (PBFF.getffFilMultipleFileId() == true) sNameFile =auxSFN.getNTwithout();
		else sNameFile = auxSFN.getNameFile();
		sNamePathFile = auxSFN.getPath() + auxSFN.getBarra() + sNameFile;
		
		pBM.setMetLocAbsotutePath(auxSFN.getPath());
		pBM.setMetLocAbsotuteName(sNameFile);
		
		pBM.setMetMultipleFileId(PBFF.getffFilMultipleFileId());
		pBM.setMetMFRingBufferId(PBFF.getffFilRingBufferId());
		pBM.setMetMFRingBuffer(PBFF.getffFilRingBuffer());
		if ((auxSFN.getPrimero()> auxSFN.getPila()) && PBFF.getffFilRingBufferId() == true) auxSFN.setNext();
		pBM.setMetMFStar(Integer.toString(auxSFN.getPrimero()));
		pBM.setMetMFEnd(Integer.toString(auxSFN.getUltimo()));
		
		PS = new PreferencesSniffer();
		PS.GenerateFromto(PBFF);
		PS.GenerateMeta(pBM);
		//setfile -> para poner el nombre y lugar del fichero
		PS.setFile(sNamePathFile + "_META.XML");
		PS.savePreferences();
		//System.out.println("-> parada");
		
	}//stopCaptura
	
/*	
	public static void savePreOffline() {
		preferencesBeanFromFile pBFf;
		PreferencesSniffer PS;

		pBFf = getPrefBeanFromFile();
		
		PS = new PreferencesSniffer();
		PS.GenerateFromto(pBFf);

		PS.setFile("./files/preferencias/" + "DefaultPreferencesFromFile.xml");
		PS.savePreferences();
		System.out.println("-> Fichero de preferencias generado Correctamente");
	}
*/	

	/**
	 * Obtine las propiedades de captura que han sido leidas o actualizadas en el programa.
	 * 
	 * @see dominio.preferences.preferencesBeanCapture
	 * @see dominio.pcapDumper.Captura
	 */
	public static void setPrefCapturaDumper() {
		getPcapLib().setFilePathCapture(getPrefBeanCaptura().getFilLocate());
		getPcapLib().setFiltro(setFilterDumper());
		if (getPrefBeanCaptura().getstpAfterPacketsId() == true) {
			getPcapLib().setNumPaquetes(getPrefBeanCaptura().getstpAfterPackets());
		}
		if (getPrefBeanCaptura().getCapPromiscuousMode() == true)
			getPcapLib().setPromiscuo(true);
		else
			getPcapLib().setPromiscuo(true);
		if (getPrefBeanCaptura().getstpAfterPacketsId() == true)
			getPcapLib().setNumPaquetes(getPrefBeanCaptura().getstpAfterPackets());
		//System.out.println("--> Set Pref Captura");

	}//setPrefCapturaDumper
	
	/**
	 * Obtine las propiedades de captura desde fichero
	 *  que han sido leidas o actualizadas en el programa.
	 * 
	 * @see dominio.preferences.preferencesBeanFromFile
	 * @see dominio.pcapDumper.Captura
	 */
	public static void setPrefFromFileDumper() {
		getPcapLib().setFilePathCapture(getPrefBeanFromFile().getffFilLocate());
		getPcapLib().setFiltro(setFilterDumperFromFile());
		if (getPrefBeanFromFile().getffStpAfterPacketsId() == true) {
			getPcapLib().setNumPaquetes(getPrefBeanFromFile().getffStpAfterPackets());
		}
		//if (getPrefBeanFromFile().getffCapPromiscuousMode() == true)
		//	getPcapLib().setPromiscuo(true);
		//else
		//	getPcapLib().setPromiscuo(false);
		if (getPrefBeanFromFile().getffStpAfterPacketsId() == true)
			getPcapLib().setNumPaquetes(getPrefBeanFromFile().getffStpAfterPackets());
		//System.out.println("--> Set Pref Captura");

	}//setPrefCapturaDumper
	
	//Caso raro, pero tenia que estar en algun sutio
	// se encarga de la visualizacion de los paquetes que se estan capturando
/*	public static void setCapturando() {
		/*
		 * pcap.addPacketListener(new CountPacketHandler(FCaptura)); Hilocaptura =
		 * new TimePacket(FCaptura,1000,RCountPacketHandler);
		 * Hilocaptura.setFrameVisibles(1000); Hilocaptura.start();
	
	}//setCapturando
	 */
	
	/**
	 * Establece el filtro de captura.
	 * 
	 * @see dominio.preferences.preferencesBeanCapture
	 * @see dominio.pcapDumper.Captura
	 */
	public static dominio.pcapDumper.Filtro setFilterDumper() {
		dominio.pcapDumper.Filtro fil = new dominio.pcapDumper.Filtro();
		if (getPrefBeanCaptura().getCapFilter() == true) {
			if (getPrefBeanCaptura().getCapAdvanceId() == true) {
				fil.setFiltro_AV(getPrefBeanCaptura().getCapAdvance());
			} else {
				fil.setFiltro(getPrefBeanCaptura().getCapHost(),
						getPrefBeanCaptura().getCapProtocol(),
						getPrefBeanCaptura().getCapPort());
			}
		}
		return fil;
	}//setFilter

	/**
	 * Establece el filtro de captura desde fichero
	 * 
	 * @see dominio.preferences.preferencesBeanFromFile
	 * @see dominio.pcapDumper.Captura
	 */
	public static dominio.pcapDumper.Filtro setFilterDumperFromFile() {
		dominio.pcapDumper.Filtro fil = new dominio.pcapDumper.Filtro();
		if (getPrefBeanFromFile().getffCapFilterId() == true) {
			if (getPrefBeanFromFile().getffCapAdvanceId() == true) {
				fil.setFiltro_AV(getPrefBeanFromFile().getffCapAdvance());
			} else {
				fil.setFiltro(getPrefBeanFromFile().getffCapHost(),
						getPrefBeanFromFile().getffCapProtocol(),
						getPrefBeanFromFile().getffCapPort());
			}
		}
		return fil;
	}//setFilter

	/**
	 * Salva a fichero .pcpa desde otro .pcap o un _META.xml.
	 * Los ficheros origen y destino tiene que ser inicializados en 
	 * las preferecias de ccaptura desde fichero.
	 * 
	 * @return estado del proceso realizado con exitos igual a 0.
	 * 
	 * @see dominio.preferences.preferencesBeanFromFile
	 * @see dominio.pcapDumper.Captura
	 */
	//falta establecer valores de errro para retornar en la funcion
	public static int saveMetaOrPcapToFile(){ //(String nomFileOrigen, String nomFileDetino){
		String nomFileOrigen, nomFileDestino, ext;
		int valorReturn=0;
		boolean exists;
		File fOrigen;
		nomFileOrigen = FachadaDominio.getPrefBeanFromFile().getffCapSource();
		nomFileDestino = FachadaDominio.getPrefBeanFromFile().getffFilLocate();
		System.out.println("----> Fichero Origen => " + nomFileOrigen);
		System.out.println("----> Fichero Destino => " + nomFileDestino);
		System.out.println("--> Start FromFile");
		fOrigen = new File(nomFileOrigen);
		exists = fOrigen.exists();
		if (exists) {
			//System.out.println("Nombre fichero "+ fOrigen.getName());
			int dotPlace = nomFileOrigen.lastIndexOf(".");
			if (dotPlace >= 0) {
				// possibly empty
				ext = nomFileOrigen.substring(dotPlace);
			} else {
				ext = "";
			}
			if (ext.toLowerCase().equals(".pcap")){
				savePcapToFile();//(nomFileOrigen,nomFileDetino);
			}
			else{ 
				if(ext.toLowerCase().equals(".xml")){
				saveMetaToFile();//(nomFileOrigen,nomFileDetino);
				}
				else{
				}
			}
		}
		return valorReturn;
	}
	
	/**
	 * Guarda el pcap leido en otro pcap (pasado como parametro) con las settings 
	 * por defecto.
	 * 
	 * @param auxFile
	 *            Ruta donde se guarda el fichero.
	 * @return estado del proceso realizado con exitos igual a 0.
	 * 
	 * @see dominio.preferences.preferencesBeanFromFile
	 */
	public static int savePcapToFile(String auxFile) {
		FachadaDominio.getPreferencesFromFile();
		FachadaDominio.getPrefBeanFromFile().setDefaultSettings();
		FachadaDominio.getPrefBeanFromFile().setffCapSource(getFileReaded());
		FachadaDominio.getPrefBeanFromFile().setffFilLocate(auxFile);
		return savePcapToFile();
	}//savePcapFile
	
	/**
	 * Guarda la captura desde un fichero de captura a otro fichero de captura
	 * Ambos son .pcap. 	 
	 * Los ficheros origen y destino tiene que ser inicializados en 
	 * las preferecias de ccaptura desde fichero.
	 * 
	 * @return estado del proceso realizado con exitos igual a 0.
	 * 
	 * @see dominio.preferences.preferencesBeanFromFile
	 */
	private static int savePcapToFile(){//(String nomOrigen, String nomDestino) {
		int iRingBuffer, iStart, iEnd; 
		int aux, valorReturn, valorErrorSFName;
		dominio.pcapDumper.OfflineSavePacketHandler OSRPH;
		valorReturn = 0;
		try {

			FachadaDominio.setFilePcapLibOpenOffline();
			FachadaDominio.setPrefFromFileDumper();
			FachadaDominio.setListenerPcapLibOffline();
			FachadaDominio.startCapturaPcapLib();
			
		} catch (Exception exceptionfile) {
			exceptionfile.printStackTrace();
			
		}

		return valorReturn;
	}//savePcapToFile
	
	/**
	 * Guarda la captura desde un fichero _META.xml a otro fichero de captura.
	 * Los ficheros origen y destino tiene que ser inicializados en 
	 * las preferecias de ccaptura desde fichero.
	 * 
	 * @return estado del proceso realizado con exitos igual a 0.
	 * 
	 * @see dominio.preferences.preferencesBeanFromFile
	 */
	private static int saveMetaToFile(){//(String nomFileOrigen, String nomFileDetino){
		int iRingBuffer, iStart, iEnd; 
		int aux, valorReturn, valorErrorSFName;
		String nomFileOrigen, nomFileDetino,auxStr;
		dominio.pcap.SaveFileName SFName;
		valorReturn = 0;
		boolean unaVez=false;
		
		nomFileOrigen = FachadaDominio.getPrefBeanFromFile().getffCapSource();
		nomFileDetino = FachadaDominio.getPrefBeanFromFile().getffFilLocate();
		FachadaDominio.setPreferencesMETA(nomFileOrigen);
		FachadaDominio.getPreferences().leerXMLMETA();

		try {

			if (getPrefBeanMeta().getMetLocRelativeId()== true){
				SFName = new dominio.pcap.SaveFileName(getPreferences().getFileMETA());
				SFName.setFile(getPrefBeanMeta().getMetLocRelative());
				SFName.getPartFile();
			}
			else{
				SFName = new dominio.pcap.SaveFileName(getPrefBeanMeta().getMetLocAbsotutePath(),getPrefBeanMeta().getMetLocAbsotuteName());
			}
			if (getPrefBeanMeta().getMetMultipleFileId() == true){
				if (getPrefBeanMeta().getMetMFRingBufferId() == true) iRingBuffer = Integer.parseInt(getPrefBeanMeta().getMetMFRingBuffer());
				else iRingBuffer = 0;
				iStart = Integer.parseInt(getPrefBeanMeta().getMetMFStar());
				iEnd = Integer.parseInt(getPrefBeanMeta().getMetMFEnd());
				valorErrorSFName = SFName.SFNOffline(iRingBuffer, iStart - 1, iEnd);
				if (valorErrorSFName != 0)
					valorReturn = 2;
				else {
					SFName.setNext();
					aux = SFName.getNext();
					String auxFileName; 
					FachadaDominio.setPcapLib();
					//System.out.println("aki");
					if (aux > 0 ) FachadaDominio.getPcapLib().openOffline_writer(SFName.getFullPathOffline());
					while (aux > 0) {
						FachadaDominio.getPcapLib().openOffline_read(SFName.getFullPathOffline());
						FachadaDominio.setPrefFromFileDumper();
						if (unaVez==false){
							FachadaDominio.setListenerPcapLibOffline();
							FachadaDominio.getPcapLib().runHilosCapture();
							unaVez=true;
						}
						FachadaDominio.getPcapLib().offfffff();
						SFName.setNext();
						aux = SFName.getNext();
						FachadaDominio.getPcapLib().getPcap().close();
					}
					FachadaDominio.getPcapLib().eCOF();
				}
			}
			else{
			//	auxStr = SFName.getFullPathOffline();
			//	auxStr = SFName.getFullPath();
				FachadaDominio.setPcapLib();
				//System.out.println("aki");	
				FachadaDominio.getPcapLib().openOffline(SFName.getFullPathOffline());
				FachadaDominio.setPrefFromFileDumper();
				FachadaDominio.setListenerPcapLibOffline();
				FachadaDominio.getPcapLib().runHilosCapture();
				FachadaDominio.getPcapLib().offfffff();
				FachadaDominio.getPcapLib().eCOF();
			}
		} catch (Exception exceptionfile) {
			exceptionfile.printStackTrace();
		}
		return valorReturn;
	}//saveMetaToFile
	
	/**
	 * Guarda la captura desde un fichero _META.xml o pcap a un XML.
	 * Los ficheros origen y destino tiene que ser inicializados en 
	 * las preferecias de ccaptura desde fichero.
	 * 
	 * @return estado del proceso realizado con exitos igual a 0.
	 */
	public static int saveMetaOrPcapToXML(String fileOrigen, String fileXml, boolean multifile){ //(String nomFileOrigen, String nomFileDetino){
		String  ext;
		int valorReturn=0;
		boolean exists;
		File fOrigen;
		fOrigen = new File(fileOrigen);
		exists = fOrigen.exists();
		if (exists) {
			int dotPlace = fileOrigen.lastIndexOf(".");
			if (dotPlace >= 0) {// possibly empty
				ext = fileOrigen.substring(dotPlace);
			} else {
				ext = "";
			}
			
			if (ext.toLowerCase().equals(".pcap")){
				saveXMLOfflinePcapLib(fileOrigen,fileXml);
			}
			else{ 
				if(ext.toLowerCase().equals(".xml")){
				saveMetaXMLOfflinePcapLib(fileOrigen,fileXml,multifile);
				}
				else{
				
				}
			}
		}
		return valorReturn;
	}


	/**
	 * Guarda a .XML el último fichero de la última captura realizada. 
	 * 
	 * @param file
	 *            fichero donde se guarda el fichero.
	 * @return estado del proceso realizado con exitos igual a 0.
	 * 
	 * @see dominio.preferences.preferencesBeanFromFile
	 * @see dominio.pcapDumper.Captura
	 */
	public static int saveXML(File file) {
		int returnVal;
		if ( file != null){
			FachadaDominio.getPreferencesExport();
			FachadaDominio.getPrefBeanExport().setExpSource(getFileReaded());
			FachadaDominio.getPrefBeanExport().setExpDestination(file.getAbsoluteFile() + ".xml");
			FachadaDominio.saveXMLOffline(FachadaDominio.getPrefBeanExport());
			returnVal = 1;
		}else{
			returnVal = 0;
		}
		return returnVal;
	}
	
	
	/**
	 * Guarda a .xml según las propiedades pasadas.
	 * @param pBE
	 * 			propiedades de exportación.
	 * @return estado del proceso realizado con exitos igual a 0.
	 */
	public static int saveXMLOffline(preferencesBeanExport pBE) {
		String nomFileOrigen; 
		String nomFileDestino;
		boolean multiFile;
		try {
			nomFileOrigen = pBE.getExpSource();
			nomFileDestino = pBE.getExpDestination();
			multiFile = pBE.getExpMultifile();
			System.out.println("----> Fichero Origen => " + nomFileOrigen);
			System.out.println("----> Fichero Destino => " + nomFileDestino);
			System.out.println("--> Start Export");
			saveMetaOrPcapToXML(nomFileOrigen,nomFileDestino,multiFile);
		} catch (Exception exceptionfile) {
			exceptionfile.printStackTrace();

		}
		return 0;
	}
	
	/**
	 *  Guarda a .xml.
	 * 
	 * @param file
	 *            fichero pcap del que se lee.
	 * @param fileXml
	 *            fichero donde se guarda el fichero XML.
	 * 
	 * @see dominio.pcapDumper.Captura
	 * @see dominio.export.xml_PcapLib.CrearXMLOffline
	 * @see dominio.export.xml_PcapLib.XmlPacketHandler
	 */
	public static int saveXMLOfflinePcapLib(String file, String fileXml) {
		String fichero;
		dominio.export.xml_PcapLib.CrearXMLOffline CXMLOffline;
		dominio.export.xml_PcapLib.XmlPacketHandler XMLPHandler;
		fichero = file;
		//System.out.println("---------------------------" + fichero);
		//System.out.println("-----Exportando a XML------" + fichero);
		//pack();
		try {
			CXMLOffline = new dominio.export.xml_PcapLib.CrearXMLOffline(fileXml);
			XMLPHandler = new dominio.export.xml_PcapLib.XmlPacketHandler(CXMLOffline);
			
			FachadaDominio.setPcapLib();
			FachadaDominio.getPcapLib().openOffline(file);
			FachadaDominio.getPcapLib().offline_xml(XMLPHandler);

			CXMLOffline.endFile();
		} catch (Exception exceptionfile) {
			exceptionfile.printStackTrace();
		}
		//System.out.println("---------------------------" + fichero);
		//System.out.println("--Exportado correctamente--" + fichero);
		stateCommandActivo = false;
		return 0;
	}
	
	/**
	 *  Guarda a .xml desde _META.XML.
	 * 
	 * @param fileOrigen
	 *            fichero pcap del que se lee.
	 * @param fileXml
	 *            fichero donde se guarda el fichero XML.
	 * @param multifile
	 *            a true por cada fichero .pcap genera un .XML.
	 * 			  a false todos los .pcap en un sólo .XML.
	 * 
	 * @see dominio.preferences.preferencesBeanMeta
	 * @see dominio.pcapDumper.Captura
	 * @see dominio.export.xml_PcapLib.XmlPacketHandler
	 */	
	public static int saveMetaXMLOfflinePcapLib(String fileOrigen, String fileXml, boolean multifile) {
		int iRingBuffer, iStart, iEnd; 
		int aux, valorReturn, valorErrorSFName;
		String nomFileOrigen, nomFileDetino;
		dominio.pcap.SaveFileName SFName;
		valorReturn = 0;
		
		dominio.export.xml_PcapLib.CrearXMLOffline CXMLOffline;
		dominio.export.xml_PcapLib.XmlPacketHandler XMLPHandler;
		
		
		
		
		nomFileOrigen = fileOrigen;
		nomFileDetino = fileXml;
		FachadaDominio.setPreferencesMETA(nomFileOrigen);
		FachadaDominio.getPreferences().leerXMLMETA();
		
		try {
			
			if (getPrefBeanMeta().getMetLocRelativeId()== true){
				SFName = new dominio.pcap.SaveFileName(getPreferences().getFileMETA());
				SFName.setFile(getPrefBeanMeta().getMetLocRelative());
				SFName.getPartFile();
			}
			else{
				SFName = new dominio.pcap.SaveFileName(getPrefBeanMeta().getMetLocAbsotutePath(),getPrefBeanMeta().getMetLocAbsotuteName());
			}
			if (getPrefBeanMeta().getMetMultipleFileId() == true){
				if (getPrefBeanMeta().getMetMFRingBufferId() == true) iRingBuffer = Integer.parseInt(getPrefBeanMeta().getMetMFRingBuffer());
				else iRingBuffer = 0;
				iStart = Integer.parseInt(getPrefBeanMeta().getMetMFStar());
				iEnd = Integer.parseInt(getPrefBeanMeta().getMetMFEnd());
				valorErrorSFName = SFName.SFNOffline(iRingBuffer, iStart - 1, iEnd);
				if (valorErrorSFName != 0)
					valorReturn = 2;
				else {
					if (multifile == false) {
						// nomFileOrigen);
						//System.out.println("-----Exportando a XML------" +
						// nomFileOrigen);
						SFName.setNext();
						aux = SFName.getNext();
						String auxFileName;
						FachadaDominio.setPcapLib();
						CXMLOffline = new dominio.export.xml_PcapLib.CrearXMLOffline(
								fileXml);
						XMLPHandler = new dominio.export.xml_PcapLib.XmlPacketHandler(
								CXMLOffline);
						//System.out.println("aki");
						System.out.println("--.--> " + fileXml);
						while (aux > 0) {
							FachadaDominio.getPcapLib().openOffline(
									SFName.getFullPathOffline());
							FachadaDominio.getPcapLib().offline_xml(XMLPHandler);
							//hacer que cierre /packet
							SFName.setNext();
							aux = SFName.getNext();
							FachadaDominio.getPcapLib().both_ECOF();
						}
						CXMLOffline.endFile();

						//System.out.println("---------------------------" +
						// nomFileOrigen);
						//System.out.println("--Exportado correctamente--" +
						// nomFileOrigen);
					}
					else {
						//System.out.println("---------------------------" +
						// nomFileOrigen);
						//System.out.println("-----Exportando a XML------" +
						// nomFileOrigen);
						SFName.setNext();
						aux = SFName.getNext();
						String auxFileName;
						FachadaDominio.setPcapLib();
						while (aux > 0) {
							System.out.println("--:--> " + fileXml+"_"+aux+".xml");
							CXMLOffline = new dominio.export.xml_PcapLib.CrearXMLOffline(
									fileXml+"_"+aux+".xml");
							XMLPHandler = new dominio.export.xml_PcapLib.XmlPacketHandler(
									CXMLOffline);
							//System.out.println("aki");
							//while (aux > 0) {
							FachadaDominio.getPcapLib().openOffline(
									SFName.getFullPathOffline());
							FachadaDominio.getPcapLib().offline_xml(XMLPHandler);
							//hacer que cierre /packet
							SFName.setNext();
							aux = SFName.getNext();
							CXMLOffline.endFile();
							FachadaDominio.getPcapLib().both_ECOF();
						}
						//CXMLOffline.endFile();
						//System.out.println("---------------------------" +
						// nomFileOrigen);
						//System.out.println("--Exportado correctamente--" +
						// nomFileOrigen);
					}
				}
			}
			else{
				//System.out.println("-----Exportando a XML------");

				String auxFileName;
				FachadaDominio.setPcapLib();
				CXMLOffline = new dominio.export.xml_PcapLib.CrearXMLOffline(
						fileXml);
				XMLPHandler = new dominio.export.xml_PcapLib.XmlPacketHandler(
						CXMLOffline);
				//System.out.println("aki");
				System.out.println("--.--> " + fileXml);
				FachadaDominio.getPcapLib().openOffline(SFName.getFullPathOffline());
				FachadaDominio.getPcapLib().offline_xml(XMLPHandler);
				CXMLOffline.endFile();
				FachadaDominio.getPcapLib().both_ECOF();
			}
			
		} catch (Exception exceptionfile) {
			exceptionfile.printStackTrace();
		}
		stateCommandActivo = false;
		return valorReturn;
		
		
	}
	
	
	/**
	 * Método que retorna un tipo de dato boolean de un dato String Si es "YES"
	 * retorna "true" Si es "NO" retorna "false"
	 * 
	 * @param state
	 *            cadena a convertir
	 * @return el boleano correspondiente, sino es correcto por defecto retorna
	 *         "false"
	 */
	private static boolean validate(String state) {
		boolean auxB;
		if (state.toUpperCase().equals("YES"))
			auxB = true;
		else
			auxB = false;
		return auxB;
	}
	
	/**
	 * Guarda a .XML los ficheros de la captura/as establecidos en el 
	 * fichero de estado _estate.xml. 
	 * 
	 * @param file
	 *            fichero donde se guarda el fichero.
	 * @return estado del proceso realizado con exitos igual a 0.
	 * 
	 * @see dominio.preferences.preferencesBeanFromFile
	 * @see dominio.pcapDumper.Captura
	 */
	public static int saveXMLfromEstate(String file) {
		int returnVal;
		StateCaptura ficheroEstate = new  StateCaptura();
		//SaveFileName controSFN = new SaveFileName();
		
		if ( file != null){
			FachadaDominio.getPrefBeanMeta().setDefaultSettings();
			ficheroEstate.setFile(file);
			ficheroEstate.leerPorperties();
			FachadaDominio.getPrefBeanMeta().setMetLocAbsotutePath(ficheroEstate.getLocate());
			FachadaDominio.getPrefBeanMeta().setMetLocAbsotuteName(ficheroEstate.getName());
			FachadaDominio.getPrefBeanMeta().setMetMultipleFileId(validate(ficheroEstate.getMultipleFile()));
			FachadaDominio.getPrefBeanMeta().setMetMFRingBuffer(ficheroEstate.getRingBuffer());
			
			saveFromEstate(file,ficheroEstate);
			returnVal = 1;
		}else{
			returnVal = 0;
		}
		return returnVal;
	}
	
	

	private static int saveFromEstate(String file, StateCaptura ficheroEstate ){
		int iRingBuffer, iStart, iEnd,cont; 
		int aux, valorReturn, valorErrorSFName;
		String nomFileOrigen, nomFileDetino;
		dominio.pcap.SaveFileName SFName;
		valorReturn = 0;
		boolean auxBoolean;
		
		dominio.export.xml_PcapLib.CrearXMLOffline CXMLOffline;
		dominio.export.xml_PcapLib.XmlPacketHandler XMLPHandler;
		StateHandler StateH;
		//nomFileOrigen = fileOrigen;
		//nomFileDetino = fileXml;
		//FachadaDominio.setPreferencesMETA(nomFileOrigen);
		//FachadaDominio.getPreferences().leerXMLMETA();
		
		try {
			SFName = new dominio.pcap.SaveFileName(getPrefBeanMeta().getMetLocAbsotutePath(),getPrefBeanMeta().getMetLocAbsotuteName());
			if (getPrefBeanMeta().getMetMultipleFileId() == true){
				if (getPrefBeanMeta().getMetMFRingBufferId() == true) iRingBuffer = Integer.parseInt(getPrefBeanMeta().getMetMFRingBuffer());
				else iRingBuffer = 0;
				iStart = Integer.parseInt(getPrefBeanMeta().getMetMFStar());
				iEnd = Integer.parseInt(getPrefBeanMeta().getMetMFEnd());
				//valorErrorSFName = SFName.SFNOffline(iRingBuffer, iStart - 1, iEnd);
				valorErrorSFName = 0;
				if (valorErrorSFName != 0)
					valorReturn = 2;
				else {
					//StateH= new StateHandler(SFName,ficheroEstate);
					FachadaDominio.setPcapLib();
					
					SFName.setNext();
					aux = SFName.getNext();

					while (aux > 0) {
						if(ficheroEstate.getLastNumFileCapturado().equals(String.valueOf(aux))){
							auxBoolean = false;
						}
						else{
							auxBoolean = true;
						}
						if ( ((ficheroEstate.getCapturando().equals("Yes") && auxBoolean)) 
								|| (ficheroEstate.getCapturando().equals("No"))) {
							nomFileDetino = SFName.getPath() + SFName.getBarra() + SFName.getNameFile() + "_" + aux + ".xml";
							System.out.println("--:--> " + nomFileDetino);
							CXMLOffline = new dominio.export.xml_PcapLib.CrearXMLOffline(
									nomFileDetino);
							XMLPHandler = new dominio.export.xml_PcapLib.XmlPacketHandler(
									CXMLOffline);
							nomFileOrigen = SFName.getPath() + SFName.getBarra() + SFName.getNameFile() + "_" + aux + ficheroEstate.getExtension();
							FachadaDominio.getPcapLib().openOffline(nomFileOrigen);
							FachadaDominio.getPcapLib().offline_xml(XMLPHandler);
							SFName.setNext();
							aux = SFName.getNext();
							CXMLOffline.endFile();
							FachadaDominio.getPcapLib().both_ECOF();
							if (auxBoolean == false) aux = -1;
						}
						if (aux != -1){
							cont = 0;
							while (cont < 10000)cont ++;
							ficheroEstate.leerPorperties();
						}

					}
						//CXMLOffline.endFile();
						//System.out.println("---------------------------" +
						// nomFileOrigen);
						//System.out.println("--Exportado correctamente--" +
						// nomFileOrigen);
				}
			}
			else{
				//System.out.println("-----Exportando a XML------");

				aux = 1;
				while (aux > 0) {
					if (ficheroEstate.getCapturando().equals("No")){
						FachadaDominio.setPcapLib();
						nomFileDetino = SFName.getPath() + SFName.getBarra() + SFName.getNameFile() + ".xml";
						CXMLOffline = new dominio.export.xml_PcapLib.CrearXMLOffline(
								nomFileDetino);
						XMLPHandler = new dominio.export.xml_PcapLib.XmlPacketHandler(
								CXMLOffline);
						System.out.println("--.--> " + nomFileDetino);
						nomFileOrigen = SFName.getPath() + SFName.getBarra() + 
							SFName.getNameFile() + ficheroEstate.getExtension();
						FachadaDominio.getPcapLib().openOffline(SFName.getFullPathOffline());
						FachadaDominio.getPcapLib().offline_xml(XMLPHandler);
						CXMLOffline.endFile();
						FachadaDominio.getPcapLib().both_ECOF();
						aux=-1;
					}
					if (aux != -1){
						cont = 0;
						while (cont < 10000)cont ++;
						ficheroEstate.leerPorperties();
					}
				}
			}
			
		} catch (Exception exceptionfile) {
			exceptionfile.printStackTrace();
		}
		stateCommandActivo = false;
		return valorReturn;
		
		

	}
	
	/**
	 * Guarda a .XML los ficheros de la captura/as establecidos en el 
	 * fichero de estado _estate.xml. 
	 * 
	 * @param file
	 *            fichero donde se guarda el fichero.
	 * @return estado del proceso realizado con exitos igual a 0.
	 * 
	 * @see dominio.preferences.preferencesBeanFromFile
	 * @see dominio.pcapDumper.Captura
	 */
	private static int savefromEstate(File file) {
		int returnVal;
		StateCaptura ficheroEstate = new  StateCaptura();
		SaveFileName controSFN = new SaveFileName();
		
		if ( file != null){
			FachadaDominio.getPrefBeanMeta().setDefaultSettings();
			ficheroEstate.setFile(file.getAbsolutePath());
			ficheroEstate.leerPorperties();
			FachadaDominio.getPrefBeanMeta().setMetLocAbsotutePath(ficheroEstate.getLocate());
			FachadaDominio.getPrefBeanMeta().setMetLocAbsotuteName(ficheroEstate.getName());
			FachadaDominio.getPrefBeanMeta().setMetMultipleFileId(validate(ficheroEstate.getMultipleFile()));
			
			returnVal = 1;
		}else{
			returnVal = 0;
		}
		return returnVal;
	}
	
	
	/**
	 * Obtiene la instacia que contiene las preferencias de la ruta por defecto.
	 * Captura o Captura desde fichero o exportacin o metadatos o definicion .
	 * 
	 * @return preferencias de sniffer
	 * 
	 * @see dominio.preferences.preferencesFileRead
	 */
	private static preferencesFileRead getPreferences() {
		if (preferences == null) {
			preferencesFileRead aux = new preferencesFileRead();
			preferences = aux;
			//preferences.printState();
		}
		return preferences;
	}//getPreferences

	/**
	 * Obtiene la instacia que contiene las preferencias de la ruta por defecto
	 * Captura o Captura desde fichero o exportacin o metadatos.
	 * 
	 * @see dominio.preferences.preferencesFileRead
	 */
	public static void leerPreferences() {
		getPreferences().leerXML();
	}//getPreferences

	/**
	 * Establece la ruta donde estn las preferencias de captura.
	 * 
	 * @param ruta
	 *            Ruta del fichero de preferencias
	 * 
	 * @see dominio.preferences.preferencesFileRead
	 */
	public static void setPreferencesCapture(String ruta) {
		getPreferences().setFileCapture(ruta);
	}//setPreferencesCapture
	
	/**
	 * Establece la ruta donde estan las preferencias de exportación.
	 * 
	 * @param ruta
	 *            Ruta del fichero de preferencias
	 * 
	 * @see dominio.preferences.preferencesFileRead
	 */
	public static void setPreferencesExport(String ruta) {
		getPreferences().setFileExport(ruta);
	}//setPreferencesCapture
	
	/**
	 * Establece la ruta donde estn las preferencias de captura desde fichero.
	 * 
	 * @param ruta
	 *            Ruta del fichero de preferencias
	 * 
	 * @see dominio.preferences.preferencesFileRead
	 */
	public static void setPreferencesFromFile(String ruta) {
		getPreferences().setFileFromFile(ruta);
	}//setPreferencesCapture
	
	
	/**
	 * Establece la ruta donde estn las preferencias de captura.
	 * 
	 * @param ruta
	 *            Ruta del fichero de preferencias
	 * 
	 * @see dominio.preferences.preferencesFileRead
	 */
	public static void setPreferencesMETA(String ruta) {
		getPreferences().setFileMETA(ruta);
	}//setPreferencesCapture
	
	/**
	 * Establece la ruta donde estan las definicion del protocolo.
	 * 
	 * @param ruta
	 *            Ruta del fichero de definicion
	 * 
	 * @see dominio.preferences.preferencesFileRead
	 */
	public static void setPreferencesDefinicion(String ruta) {
		getPreferences().setFileDefinicion(ruta);
	}//setPreferencesCapture
	
	/**
	 * Lee las propiedades de captura.
	 * 
	 * @param porDefecto
	 * 			fichero del preferencias de captura.
	 * @return 0 si ha sido leído correctamente
	 * 
	 * @see dominio.preferences.preferencesFileRead
	 */
	public static boolean getPreferencesCapture(boolean porDefecto) {
		return getPreferences().leerXMLCapture(porDefecto);
	}//getPreferencesCapture
	
	/**
	 * Lee las preferencias de captura exportación.
	 * 
	 * @see dominio.preferences.preferencesFileRead
	 */
	public static void getPreferencesExport() {
		getPreferences().leerXMLExport();
	}//getPreferencesCapture

	/**
	 * Lee las preferencias de captura exportación.
	 * 
	 * @see dominio.preferences.preferencesFileRead
	 */
	public static void getPreferencesExportInsercion() {
		getPreferences().leerXMLExportInsercion();
	}//getPreferencesCapture
	
	/**
	 * Lee las preferencias de captura desde fichero.
	 * 
	 * @see dominio.preferences.preferencesFileRead
	 */
	public static void getPreferencesFromFile() {
		getPreferences().leerXMLFromFile();
	}//getPreferencesCapture
	
	/**
	 * Lee las preferencias de tipo _META.
	 * 
	 * @see dominio.preferences.preferencesFileRead
	 */
	public static void getPreferencesMETA() {
		getPreferences().leerXMLMETA();
	}//getPreferencesCapture
	
	/**
	 * Lee las preferencias de definicion.
	 * 
	 * @see dominio.preferences.preferencesFileRead
	 */
	public static void getPreferencesDefinicion() {
		getPreferences().leerXMLDefinicion();
	}
	
	/**
	 * Obtiene las preferencias de captura.
	 * 
	 * @return preferencias de captura.
	 * 
	 * @see dominio.preferences.preferencesFileRead
	 */
	public static preferencesBeanCapture getPrefBeanCaptura() {
		preferencesFileRead aux = getPreferences();
		return aux.getPBCapture();
	}//getPrefBeanCaptura

	/**
	 * Obtiene las preferencias de exportacion.
	 * 
	 * @return preferencias de exportacion.
	 * 
	 * @see dominio.preferences.preferencesFileRead
	 */
	public static preferencesBeanExport getPrefBeanExport() {
		preferencesFileRead aux = getPreferences();
		return aux.getPBExport();
	}//getPrefBeanExport

	/**
	 * Obtiene las preferencias desde fichero.
	 * 
	 * @return preferencias desde fichero.
	 * 
	 * @see dominio.preferences.preferencesFileRead
	 */
	public static preferencesBeanFromFile getPrefBeanFromFile() {
		preferencesFileRead aux = getPreferences();
		return aux.getPBFromFile();
	}//getPrefBeanFromFile

	/**
	 * Obtiene las preferencias de metadatos.
	 * 
	 * @return preferencias de metadatos.
	 * 
	 * @see dominio.preferences.preferencesFileRead
	 */
	public static preferencesBeanMeta getPrefBeanMeta() {
		preferencesFileRead aux = getPreferences();
		return aux.getPBMeta();
	}//getPrefBeanMeta

	/**
	 * Obtiene las preferencias de definicion.
	 * 
	 * @return preferencias de definicion.
	 * 
	 * @see dominio.preferences.preferencesFileRead
	 */
	public static preferencesBeanDefinicion getPrefBeanDefinicion() {
		preferencesFileRead aux = getPreferences();
		return aux.getPBDefinicion();
	}
	
	/**
	 * Obtiene las preferencias de exportaciones para insercion.
	 * 
	 * @return preferencias de exportacion.
	 * 
	 * @see dominio.preferences.preferencesFileRead
	 */
	public static preferencesBeanExportInsercion getPrefBeanExportInsercion() {
		preferencesFileRead aux = getPreferences();
		return aux.getPBExportInsercion();
	}
	
	/**
	 * Obtiene la instacia que contiene las properties del programa.
	 * 
	 * @return preferencias de sniffer.
	 * 
	 * @see dominio.preferences.preferencesFileRead
	 */
	private static PropertiesFileRead getProperties() {
		if (properties == null) {
			PropertiesFileRead aux = new PropertiesFileRead();
			properties = aux;
		}
		return properties;
	}//getProperties
	
	/**
	 * Lee las preferencias del programa.
	 * 
	 * @see dominio.preferences.preferencesFileRead
	 */
	public static void leerProperties() {
		getProperties().leerPorperties();
	}//getPreferences

	
	/**
	 * Establece las prefernecias del programa.
	 * 
	 * @param aux
	 * 			preferencias de sniffer.
	 * 
	 * @see dominio.preferences.preferencesFileRead
	 */
	public static void setPropertiesColumns(String aux) {
		getProperties().getPBSniffer().setTableView(aux);
	}//getPreferences
	
	/**
	 * Graba las preferencias del programa a fichero
	 * 
	 * @see dominio.preferences.preferencesFileRead
	 */
	public static void grabarProperties() {
		getProperties().grabarPorperties();
	}//getPreferences
	
	/**
	 * Establece la ruta donde estn las properties del programa
	 * 
	 * @param ruta
	 *            Ruta del fichero de properties
	 * 
	 * @see dominio.preferences.preferencesFileRead
	 */
	public static void setPropertiesSniffer(String ruta) {
		getProperties().setFileSniffer(ruta);
	}//setPropertiesSniffer
	
	
	/**
	 * lee las preferencias del programa.
	 * 
	 * @return preferencias de captura
	 * 
	 * @see dominio.preferences.preferencesFileRead
	 */
	public static PropertiesBeanSniffer getPropBeanSniffer() {
		PropertiesFileRead aux = getProperties();
		aux.leerPropertiesSniffer();
		return aux.getPBSniffer();
	}//getPrefBeanCaptura
	
	/**
	 * Guarda las preferencias en el fichero Puede ser de tipo Captura,
	 * Exportacion, FormFile o MetaDatos
	 * 
	 * @param estado
	 *            Para establece cual guardar
	 * @param ruta
	 *            Ruta donde se guarda el fichero de preferencias
	 */
	//aaqquuii
	public static void savePreferences(int estado, String ruta) {
		PreferencesSniffer prue = new PreferencesSniffer();
		prue.setFile(ruta);
		preferencesFileRead aux = getPreferences();
		if ((estado == 1) || (estado == 4))
			prue.GenerateCapture(aux.getPBCapture());
		if ((estado == 2) || (estado == 5))
			prue.GenerateExportacion(aux.getPBExport());
		if ((estado == 3) || (estado == 6))
			prue.GenerateFromto(aux.getPBFromFile());
		if ((estado == 4) || (estado == 5) || (estado == 6))
			prue.GenerateMeta(aux.getPBMeta());
		prue.savePreferences();
		System.out.println("Guarda XML");
	}//savePreferences
    
	/**
	 * Guarda la definicion del protocolo en el fichero definido
	 * 
	 * @param estado
	 *            Para establece cual guardar
	 * @param ruta
	 *            Ruta donde se guarda el fichero de preferencias
	 */
	
	public static void savePreferencesDefinicion(int estado, String ruta, preferencesBeanDefinicion aux) {
		PreferencesSnifferDefinicion prue = new PreferencesSnifferDefinicion();
		prue.setFile(ruta);
		if ( estado == 7 )
			prue.GenerateDefinicion(aux);
		prue.savePreferences();
		System.out.println("Guarda XML Protocolo");
	}

	
	/**
	 * Pasando la cantidad de medida y la medida, nos da la cantidad en bytes
	 * 
	 * @param bytes
	 *            cantidad de medida establecida por tipo
	 * @param tipo
	 *            tipo de medida de espacio
	 * @return la catidad de espacio en bytes
	 */
	private static String getMFilSpaceBytes(String bytes, String tipo) {
		String aux;
		long lSpace = 0;
		try {
			lSpace = Long.parseLong(bytes);
		} catch (NumberFormatException e) {
			System.out.println(">>__ " + e.toString());
			lSpace = 0;
		}
		if (tipo.toLowerCase().contains("kilo")) {
			//System.out.println(">> " + lSpace);
			lSpace = lSpace * 1024;
			//System.out.println(">> " + lSpace);
		} else {
			if (tipo.toLowerCase().contains("mega")) {
			//	System.out.println(">> " + lSpace);
				lSpace = lSpace * 1024;
				lSpace = lSpace * 1024;
			//	System.out.println(">> " + lSpace);
			} else {
				if (tipo.toLowerCase().contains("giga")) {
				//	System.out.println(">> " + lSpace);
					lSpace = lSpace * 1024;
					lSpace = lSpace * 1024;
					lSpace = lSpace * 1024;
				//	System.out.println(">> " + lSpace);
				} else {
					lSpace = 0;
					System.out
							.println("Tipo de espacio incorrecto. Debe ser kilobyte, megabyte, gigabyte");
				}
			}
		}
		aux = String.valueOf(lSpace);

		return aux;
	}//getMFilSpaceBytes

	/**
	 * Pasando la cantidad de medida y la medida, nos da la cantidad en
	 * milisegundos
	 * 
	 * @param tiempo
	 *            cantidad de medida establecida por tipo
	 * @param tipo
	 *            tipo de medida de tiempo
	 * @return la catidad de espacio en milisegundos
	 */
	private static String getMFilTimeSegundos(String tiempo, String tipo) {
		String aux;
		long lTime = 0;
		try {
			lTime = Long.parseLong(tiempo);
		} catch (NumberFormatException e) {
			System.out.println(">>__ " + e.toString());
			lTime = 0;
		}
		if (tipo.toLowerCase().contains("segundo")) {
			//System.out.println(">> " + lTime);
			lTime = lTime * 1000;
			//System.out.println(">> " + lTime);
		} else {
			if (tipo.toLowerCase().contains("minuto")) {
				//System.out.println(">> " + lTime);
				lTime = lTime * 1000;
				lTime = lTime * 60;
				//System.out.println(">> " + lTime);
			} else {
				if (tipo.toLowerCase().contains("hora")) {
					//System.out.println(">> " + lTime);
					lTime = lTime * 1000;
					lTime = lTime * 60;
					lTime = lTime * 60;
					//System.out.println(">> " + lTime);
				} else {
					if (tipo.toLowerCase().contains("dia")) {
						//System.out.println(">> " + lTime);
						lTime = lTime * 1000;
						lTime = lTime * 60;
						lTime = lTime * 60;
						lTime = lTime * 24;
						//System.out.println(">> " + lTime);
					} else {
						lTime = 0;
						System.out
								.println("Tipo de tiempo incorrecto. Debe ser segundo, minuto, hora, dia");
					}
				}
			}
		}
		aux = String.valueOf(lTime);

		return aux;
	}//getMFilTimeSegundos
	/**
	 * Genera el script encargado de hacer una captura o exportación o captura de fichero
	 * 
	 * @param sRutaPref
	 * 			fichero de preferencias.
	 * @param sRutaBat
	 * 			fichero de script.
	 * @param Tipo
	 * 			tipo de acción.
	 * @param sMvm
	 * 			memoria de la maquina virtual java.
	 * @param sSO
	 * 			sistema operativo.
	 * 
	 * @see dominio.export.script.FachadaGenerarScript
	 */
	public static void setFileBat(String sRutaPref, String sRutaBat,  String Tipo, String sMvm, String sSO) {
		FachadaGenerarScript genBat;
		if (sSO.equals("Windows")){
			genBat = new GenerarBat();
		}
		else{
			genBat = new GenerarSh();
		}
		genBat.setRutaBat(sRutaBat);
		genBat.setMvm(sMvm);
		genBat.setParam(Tipo,sRutaPref);
		genBat.writeToDisk();
	}
	
	/**
	 * Dermina si la cadena de texto pasada es un dispositico de 
	 * captura de nuestro sistema.
	 * @param dispoSeleccionado
	 * 			dispositivo
	 * @return null si no es un dispositivo,
	 * 			sino devuleve la primera correspondencia del dispositivo.
	 * 
	 * @see jpcap.NetworkInterface
	 */
	public static NetworkInterface isDispositivo(String dispoSeleccionado) {
		NetworkInterface name=null;
		String ooo;
		NetworkInterface dispo[];
		dispo=FachadaDominio.getCapDispositivosPcapLib();
		dispoSeleccionado = dispoSeleccionado.toString().trim();
		for (int i = 0; i < dispo.length; i++) {
			if (dispo[i].description == null){
				ooo  = dispo[i].name.toString().trim();
			}
			else
			{
				ooo = dispo[i].description.toString().trim();
			}
			if (dispoSeleccionado.contains(ooo)) {
				name = dispo[i];
				i= dispo.length;
			}
		}
		return name;
	}
	/**
	 * Dermina si la cadena de texto pasada es un dispositico de 
	 * captura de nuestro sistema.
	 * @param dispoSeleccionado
	 * 			dispositivo
	 * @return null si no es un dispositivo,
	 * 			sino devuleve la descripción del dispositivo.
	 * 
	 * @see jpcap.NetworkInterface
	 */
	public static String isDispositivo(int dispoSeleccionado) {
		NetworkInterface dispo[];
		dispo=FachadaDominio.getCapDispositivosPcapLib();
		if ((dispoSeleccionado-1) >=0 && dispoSeleccionado < dispo.length){
			return dispo[dispoSeleccionado-1].description;
		}
		else{
			return null;
		}
	}
	
	////////////-----__________-----
	////////////-----__________-----
	//////////// Libreria vieja. Quitarla
	////////////-----__________-----
	////////////-----__________-----
	/** * Libreria vieja. Quitarla * */
	public static dominio.pcap.Captura crearPcap() {
		if(pcap!=null){
			pcap.stop();
			pcap=null;
		}
		dominio.pcap.Captura aux = new dominio.pcap.Captura();
		pcap = aux;
		return pcap;
	}//crearPcap

	/** * Libreria vieja. Quitarla * */
	public static dominio.pcap.Captura getPcap() {
		return pcap;
	}//getPcap

	/** * Libreria vieja. Quitarla * */
	public static String[] getCapDispositivos() {
		String[] aux;
		dominio.pcap.Captura pcap = crearPcap();
		aux = pcap.capDispositivos();
		return aux;
	}//getCapDispositivos

	/** * Libreria vieja. Quitarla * */
	public static void openCaptura() throws Exception {
		try {
			System.out.println("--> Open Captura START");
			dominio.pcap.Captura pcap = crearPcap();
			System.out.println(getPrefBeanCaptura().getCapDispositive());
			pcap.openCaptura(getPrefBeanCaptura().getCapDispositive());
			//pcap.start();
			System.out.println("--> Open Captura END");
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}

	}//openCaptura
	/** * Libreria vieja. Quitarla * */
	public static void setListener() {
		String aux = "0";
		getPcap().setListener();
		if (getPrefBeanCaptura().getFilMultipleFileId() == true) {
			if (getPrefBeanCaptura().getFilSpaceId() == true) {
				aux = getMFilSpaceBytes(getPrefBeanCaptura().getFilSpace(),
						getPrefBeanCaptura().getFilSpaceType());
				getPcap().setMFilSpace(aux);
			}
			if (getPrefBeanCaptura().getFilTimeId() == true) {
				aux = getMFilTimeSegundos(getPrefBeanCaptura().getFilTime(),
						getPrefBeanCaptura().getFilTimeType());
				getPcap().setMFilTime(aux);
			}

			if (getPrefBeanCaptura().getFilRingBufferId() == true) {
				getPcap().setMFilPila(getPrefBeanCaptura().getFilRingBuffer());
			}
			if (getPrefBeanCaptura().getFilStopAfterId() == true) {
				getPcap().setMFilStop(getPrefBeanCaptura().getFilStopAfter());
			}
		} else {
			if (getPrefBeanCaptura().getstpAfterSpaceId() == true) {
				aux = getMFilSpaceBytes(
						getPrefBeanCaptura().getstpAfterSpace(),
						getPrefBeanCaptura().getstpAfterSpaceType());
				getPcap().setMFilSpace(aux);
				getPcap().setMFilStop("1");
			} else {
				if (getPrefBeanCaptura().getstpAfterTimeId() == true) {
					aux = getMFilTimeSegundos(getPrefBeanCaptura()
							.getstpAfterTime(), getPrefBeanCaptura()
							.getstpAfterTimeType());
					getPcap().setMFilTime(aux);
					getPcap().setMFilStop("1");
				}
			}
		}
	}//setListener

	/** * Libreria vieja. Quitarla * */
	public static void startCaptura() {
		Capturando = true;
		getPcap().start();
		System.out.println("");
		System.out.println("--> Start Captura");
	}//startCaptura
	
	/** * Libreria vieja. Quitarla * */
	public static void setPrefCaptura() {
		getPcap().setFilePathCapture(getPrefBeanCaptura().getFilLocate());
		getPcap().setFiltro(setFilter());
		if (getPrefBeanCaptura().getstpAfterPacketsId() == true) {
			getPcap().setNumPaquetes(getPrefBeanCaptura().getstpAfterPackets());
		}
		if (getPrefBeanCaptura().getCapPromiscuousMode() == true)
			getPcap().setPromiscuo(true);
		else
			getPcap().setPromiscuo(false);
		if (getPrefBeanCaptura().getstpAfterPacketsId() == true)
			getPcap().setNumPaquetes(getPrefBeanCaptura().getstpAfterPackets());
		//System.out.println("--> Set Pref Captura");

	}//setPrefCaptura
	
	/** * Libreria vieja. Quitarla * */
	public static dominio.pcap.Filtro setFilter() {
		dominio.pcap.Filtro fil = new dominio.pcap.Filtro();
		if (getPrefBeanCaptura().getCapFilter() == true) {
			if (getPrefBeanCaptura().getCapAdvanceId() == true) {
				fil.setFiltro_AV(getPrefBeanCaptura().getCapAdvance());
			} else {
				fil.setFiltro(getPrefBeanCaptura().getCapHost(),
						getPrefBeanCaptura().getCapProtocol(),
						getPrefBeanCaptura().getCapPort());
			}
		}
		return fil;
	}//setFilter
	

	/** * Libreria vieja. Quitarla * */
	public static void setPcapV(Vector RawP) {
		getPcap().setPcapV(RawP);
	}//setPcapV
	
	/** * Libreria vieja. Quitarla * */
	public static int savePcapToFile_dll_old(String nomOrigen, String nomDestino) {
		int iRingBuffer, iStart, iEnd; 
		int aux, valorReturn, valorErrorSFName;
		valorReturn = 0;
		try {
			dominio.pcap.OfflineSaveRawPacketHandler OSRPH;
			if (nomDestino.equals("")){
				OSRPH = new dominio.pcap.OfflineSaveRawPacketHandler();
			}
			else{
				OSRPH = new dominio.pcap.OfflineSaveRawPacketHandler(nomDestino);
			}
			OSRPH.setTcpDumpWriter();
			System.out.println("---savePcapFile--->Origen "+ nomOrigen);
			System.out.println("---savePcapFile--->Destino "+ nomDestino);
			
			FachadaDominio.crearPcap();
			FachadaDominio.getPcap().openOffline(nomOrigen);
			FachadaDominio.getPcap().addRawPacketListener(OSRPH);
			//pcap.addPacketListener(new
			// PacketHandler(this,TablaPaquetes,Reglas));
			FachadaDominio.getPcap().runCapture();
			
		} catch (net.sourceforge.jpcap.capture.CaptureFileOpenException e) {
			/*
			 * JOptionPane.showMessageDialog(frameinf, "Fichero con formato
			 * incorrecto"); System.err.println("Fichero con formato
			 * incorrecto"); show();
			 */
		} catch (Exception exceptionfile) {
			exceptionfile.printStackTrace();
			
		}
		
		return valorReturn;
	}//savePcapToFile_dll_old

	/** * Libreria vieja. Quitarla * */
	public static int saveMetaToFile_dll_old(String nomFileOrigen, String nomFileDetino){
		int iRingBuffer, iStart, iEnd; 
		int aux, valorReturn, valorErrorSFName;
		dominio.pcap.SaveFileName SFName;
		valorReturn = 0;

		try {

			if (getPrefBeanMeta().getMetLocRelativeId()== true){
				SFName = new dominio.pcap.SaveFileName(getPreferences().getFileMETA());
				SFName.setFile(getPrefBeanMeta().getMetLocRelative());
				SFName.getPartFile();
			}
			else{
				SFName = new dominio.pcap.SaveFileName(getPrefBeanMeta().getMetLocAbsotutePath(),getPrefBeanMeta().getMetLocAbsotuteName());
			}
			if (getPrefBeanMeta().getMetMFRingBufferId() == true) iRingBuffer = Integer.parseInt(getPrefBeanMeta().getMetMFRingBuffer());
			else iRingBuffer = 0;
			iStart = Integer.parseInt(getPrefBeanMeta().getMetMFStar());
			iEnd = Integer.parseInt(getPrefBeanMeta().getMetMFEnd());
			valorErrorSFName = SFName.SFNOffline(iRingBuffer, iStart - 1, iEnd);
			if (valorErrorSFName != 0)
				valorReturn = 2;
			else {
				SFName.setNext();
				aux = SFName.getNext();
				dominio.pcap.OfflineSaveRawPacketHandler OSRPH;
				if (nomFileDetino.equals("")){
					OSRPH = new dominio.pcap.OfflineSaveRawPacketHandler();
				}
				else{
					OSRPH = new dominio.pcap.OfflineSaveRawPacketHandler(nomFileDetino);
				}
				OSRPH.setSpace(200000);
				OSRPH.runHilos(FachadaDominio.getPcap());
				
				
				while (aux >= 0) {
					System.out.println("entro---aaa--- "+ SFName.getFullPathOffline());
					FachadaDominio.crearPcap();
					FachadaDominio.getPcap().openOffline(SFName.getFullPathOffline());
					FachadaDominio.getPcap().addRawPacketListener(OSRPH);
					//pcap.addPacketListener(new
					// PacketHandler(this,TablaPaquetes,Reglas));
					FachadaDominio.getPcap().runCapture();
					
					SFName.setNext();
					aux = SFName.getNext();
				}
				OSRPH.stopCaptura();
			}
		} catch (net.sourceforge.jpcap.capture.CaptureFileOpenException e) {
			/*
			 * JOptionPane.showMessageDialog(frameinf, "Fichero con formato
			 * incorrecto"); System.err.println("Fichero con formato
			 * incorrecto"); show();
			 */
		} catch (Exception exceptionfile) {
			exceptionfile.printStackTrace();

		}
		return valorReturn;
	}//saveMetaToFile_dll_old
   
	/**
     * Cheuqea si los valores pasados de para la definicion del protocolo son validos
     *  en cualquier caso lanza un mensaje informando de lo ocurrido
     *  
     *  @param numCampos String
     *  
     *  @param array
     *  
     *  @return mensaje de confirmacion
     */
	public static void chequearDefinicionProtocolo(){
		PreferencesCheckDefinicion check = new PreferencesCheckDefinicion();
	}
	
	/**
	 * Estable las preferencias de exportacion.
	 * @param fRuta
	 */
	public static void setPreferencesExportacion(String fRuta) {
		getPreferences().setFileExport(fRuta);
	}

	/**
	 * Recoge las preferencias de exportacion.
	 */
	public static void getPreferencesExportacion() {
		getPreferences().leerXMLExportInsercion();
	}


	
}//Clase FachadaDominio
