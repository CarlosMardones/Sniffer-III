package dominio.pcapDumper;

import java.io.*;
import java.util.*;

/**
 * Genera y lee el fichero state. Es el fichero de estado de la captura.
 * 
 * @author Leonardo García & JoseRamón Gutierrez
 * @version 2.0
 * 
 * @see java.util.Properties
 */

public class StateCaptura {
	/** * Dato de tipo properties. */
	private Properties p;
	/** * Contiene la ruta del fichero de propiedades. */
	private String fichero;
	/** * Estado de la captura. */
	private String strCapturando;
	/** * Ruta de la captura. */
	private String strLocate;
	/** * Nombre de la captura. */
	private String strName;
	/** * Extensión de la captura. */
	private String strExtension;
	/** * Multiples ficheros. */
	private String strMultipleFile;
	/** * RingBuffer. */
	private String strRingBuffer;
	/** * Parar despues de x ficheros. */
	private String strStopAfter;
	/** * Ultimo fichero capturado. */
	private String strLastCaptureFile;
	/** * Ultimo numero de fichero capturado. */
	private String strLastNumFileCapturado;

	/**
	 * Constructor de la clase.
	 */
	public StateCaptura() {
		p = new Properties(System.getProperties());
	}

	/**
	 * Establece la ruta donde se encuentra el fichero de state
	 * 
	 * @param ruta
	 *            ruta y fichero de preferencias
	 */
	public void setFileReadSniffer(String ruta) {
		this.setFile(ruta);
		
	}
		
	/**
	 * Leectura de las preferencias del fichero XML.
	 */
	public boolean leerPorperties() {
		boolean aux;
		aux = leerPropertiesState();
		if (aux){;}
		else{System.out.println("Fichero de propiedades del programa no encontrado!!");
		System.out.println("---Se cargará parametros por defecto");}
		return aux;
	}

	/**
	 * Lectura de las preferencias del fichero XML
	 */
	private boolean leerPropertiesState() {
		String auxFileName;
		boolean exists=true;
		
		try {
			//pBSniffer = new PropertiesBeanSniffer(); 
			exists = (new File(getFile())).exists();
			if (exists) {
				FileInputStream propSniffer = new FileInputStream(getFile());
				//p.load(propSniffer);
				p.loadFromXML(propSniffer);
				if(p.getProperty("Capturando")!=null){
					setCapturando(p.getProperty("Capturando"));
				}
				if(p.getProperty("Locate")!=null){
					setLocate(p.getProperty("Locate"));
				}
				if(p.getProperty("Name")!=null){
					setName(p.getProperty("Name"));
				}
				if(p.getProperty("Extension")!=null){
					setExtension(p.getProperty("Extension"));
				}
				if(p.getProperty("MultipleFile")!=null){
					setMultipleFile(p.getProperty("MultipleFile"));
				}
				if(p.getProperty("RingBuffer")!=null){
					setRingBuffer(p.getProperty("RingBuffer"));
				}
				if(p.getProperty("StopAfter")!=null){
					setStopAfter(p.getProperty("StopAfter"));
				}
				if(p.getProperty("LastCaptureFile")!=null){
					setLastCaptureFile(p.getProperty("LastCaptureFile"));
				}
				if(p.getProperty("LastNumCaptureFile")!=null){
					setLastNumFileCapturado(p.getProperty("LastNumCaptureFile"));
				}

			}
		} catch (Exception e) {
			//e.printStackTrace();
		}
		return exists;
	}

	/**
	 * Graba las preferencias en el fichero XML.
	 */
	public void grabarPorperties(boolean state) {
		boolean aux;
		setCapturando(state);
		aux = grabarProperties();
		if (!aux){System.out.println("No grabado fichero de estado de captura");}
	}

	/**
	 * Graba las preferencias en el fichero XML.
	 */
	private boolean grabarProperties() {
		String auxFileName;
		boolean exists=false;
		try {
			p.put("Capturando",getCapturando());
			p.put("Locate",getLocate());
			p.put("Name",getName());
			p.put("Extension",getExtension());
			p.put("MultipleFile",getMultipleFile());
			p.put("RingBuffer",getRingBuffer());
			p.put("StopAfter",getStopAfter());
			p.put("LastCaptureFile",getLastCaptureFile());
			p.put("LastNumCaptureFile",getLastNumFileCapturado());
			//p.store((OutputStream)new FileOutputStream(getFile() + ".state"),"Capture File");
			p.storeToXML((OutputStream)new FileOutputStream(getFile() + "_state.xml"),"Capture File");
			exists = true;
		} catch (FileNotFoundException eFNF){
			System.out.println(eFNF.getMessage());
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return exists;
	}

	
	/**
	 * Establece el fichero de preferencias.
	 * 
	 * @param aux
	 *            ruta donde esta el fichero de preferencias de Captura.
	 */
	public void setFile(String aux) {
		this.fichero = aux;
	}

	/**
	 * Devuelve las preferecias de captura.
	 * 
	 * @return String ruta donde esta el fichero de preferencias de captura.
	 */
	public String getFile() {
		return fichero;
	}
	
	/**
	 * Establece el estado de la captura.
	 * 
	 * @param aux
	 * 			estado de la captura.
	 */
	public void setCapturando (String aux){
		strCapturando = aux;
	}
	
	/**
	 * Establece el estado de la captura.
	 * 
	 * @param aux estado
	 */
	public void setCapturando (boolean aux){
		if (aux == true) strCapturando = "Yes";
		else strCapturando = "No";
	}
	
	/**
	 * Devuelve el estado del la capura.
	 * 
	 * @return estado
	 */
	public String getCapturando (){
		return strCapturando;
	}
	
	/**
	 * Establece la localización de la captura.
	 * 
	 * @param aux ruta
	 */
	public void setLocate (String aux){
		strLocate = aux;
	}
	
	/**
	 * Devuelve la localización de la captura.
	 * 
	 * @return path
	 */
	public String getLocate (){
		return strLocate;
	}
	
	/**
	 * Establece el nombre de la captura.
	 * 
	 * @param aux nombre
	 */
	public void setName (String aux){
		strName = aux;
	}
	
	/**
	 * Devuelve el nombre de la captura.
	 * 
	 * @return nombre
	 */
	public String getName (){
		return strName;
	}
	
	/**
	 * Establece la extension de la captura.
	 * 
	 * @param aux extensión
	 */
	public void setExtension (String aux){
		strExtension = aux;
	}
	
	/**
	 * Devuelve la extensión de la captura.
	 * 
	 * @return extensión
	 */
	public String getExtension (){
		return strExtension;
	}
	
	/**
	 * Establece si son multiples ficheros.
	 * 
	 * @param aux multiples ficheros
	 */
	public void setMultipleFile (String aux){
		strMultipleFile = aux;
	}
	
	/**
	 * Retorna si son multiples ficheros.
	 * 
	 * @return multiples ficheros
	 */
	public String getMultipleFile (){
		return strMultipleFile;
	}
	
	/**
	 * Establece si hay Ring Buffer
	 * 
	 * @param aux Ring Buffer
	 */
	public void setRingBuffer (String aux){
		strRingBuffer = aux;
	}
	
	/**
	 * Devuelve el Ring Buffer.
	 * 
	 * @return Ring Buffer
	 */
	public String getRingBuffer (){
		return strRingBuffer;
	}
	
	/**
	 * Establece si para después de x ficheros.
	 * 
	 * @param aux ficheros
	 */
	public void setStopAfter (String aux){
		strStopAfter = aux;
	}
	
	/**
	 * Devuelve si para después de x ficheros.
	 * 
	 * @return ficheros
	 */
	public String getStopAfter (){
		return strStopAfter;
	}
	
	/**
	 * Establece el último fichero capturado.
	 * 
	 * @param aux fichero
	 */
	public void setLastCaptureFile (String aux){
		strLastCaptureFile = aux;
	}
	
	/**
	 * Devuelce el último fichero capturado.
	 * 
	 * @return fichero
	 */
	public String getLastCaptureFile (){
		return strLastCaptureFile;
	}
		
	/**
	 * Establece el último número de fichero capturado.
	 * 
	 * @param aux numero
	 */
	public void setLastNumFileCapturado (String aux){
		strLastNumFileCapturado = aux;
	}
	
	/**
	 * Devuelce el último número de  fichero capturado.
	 * 
	 * @return número
	 */
	public String getLastNumFileCapturado (){
		return strLastNumFileCapturado;
	}
	
	/**
	 * Si un String es null returna "null" en String
	 * 
	 * @param aux
	 * 			cadena a comprobar
	 * @return
	 * 			cadena comprobada
	 */
	private String noNull(String aux) {
		if (aux == null) {
			aux = "null";
		}
		return aux;
	}

	/**
	 * Si un boolean es null returna
	 * 
	 * @param aux
	 * 			cadena a comprobar
	 * @return
	 * 			cadena comprobada
	 */
	private boolean noNull(boolean aux) {
		return aux;
	}
}