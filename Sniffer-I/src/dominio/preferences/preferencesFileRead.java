package dominio.preferences;

import org.jdom.*; //mirar que clases necesita solamente
import org.jdom.input.*;

import java.io.*;
import java.util.*;

/**
 * Clase que lee el fichero de preferencias de tipo .XML
 * y carga los datos en la clases correspondientes
 * 
 * @author Leonardo García & Jose Ramón Gutierrez
 * @version 2.0
 * 
 * @see org.jdom.input.SAXBuilder
 * @see dominio.preferences.preferencesBeanCapture
 * @see dominio.preferences.preferencesBeanExport
 * @see dominio.preferences.preferencesBeanFromFile
 * @see dominio.preferences.preferencesBeanMeta
 */

public class preferencesFileRead {
	/** * Dato de tipo preferencias de captura. */
	private preferencesBeanCapture pBCapture;
	/** * Dato de tipo preferencias de exportación. */
	private preferencesBeanExport pBExport;
	/** * Dato de tipo preferencias de exportación insercion. */
	private preferencesBeanExportInsercion pBExportInsert;
	/** * Datos de tipo preferencias de captura de fichero. */
	private preferencesBeanFromFile pBFromFile;
	/** * Datos de tipo preferencias de captura desde fichero. */
	private preferencesBeanMeta pBMeta;
	/** * Datos de tipo preferencias de definicion de protocolo. */
	private preferencesBeanDefinicion pBDefinicion;
	/** * Datos de tipo preferencias de identificacion de protocolo. */
	private preferencesBeanIdentificacion pBIdentificacion;
	/** * Datos de tipo string que contiene la ruta del fichero de preferecias de captura. */
	private String ficheroCapture;
	/** * Datos de tipo string que contiene la ruta del fichero de preferecias de Exportación. */
	private String ficheroExport;
	/** * Datos de tipo string que contiene la ruta del fichero de preferecias de from file. */
	private String ficheroFromFile;
	/** * Datos de tipo string que contiene la informacion META datos. */
	private String ficheroMETA;
	/** * Datos de tipo string que contiene la ruta del fichero de definicion del protocolo. */
	private String ficheroDefinicion;
	
	/**
	 * Constructor por defector de la clase
	 */	
	public preferencesFileRead() {
	}

	/**
	 * Establece el fichero que va a ser leido
	 * 
	 * @param ruta
	 *            ruta y fichero de preferencias
	 */
	public void setFileReadCaptura(String ruta) {
		this.setFileCapture(ruta);
		
	}

	/**
	 * Lectura de las preferencias del fichero XML.
	 * Captura, Export, Definicion Desde fichero
	 *  
	 * 
	 */
	public void leerXML() {
		boolean aux;
		aux = leerXMLCapture(true);
		if (aux){System.out.println("Fichero de preferencias de captura leido correctamente");}
		else{System.out.println("Fichero de preferencias de captura no encontrado!!");
		System.out.println("---Se cargará parametros por defecto");}
		
		aux = leerXMLExport();
		if (aux){System.out.println("Fichero de preferencias de exportacion leido correctamente");}
		else{System.out.println("Fichero de preferencias de exportación no encontrado!!");
		System.out.println("---Se cargará parametros por defecto");}
		
		aux = leerXMLFromFile();
		if (aux){System.out.println("Fichero de preferencias de lectura desde fichero leido correctamente");}
		else{System.out.println("Fichero de preferencias de lectura desde fichero no encontrado!!");
		System.out.println("---Se cargará parametros por defecto");}
		
		//aux = leerXMLDefinicion();
		//if (aux){System.out.println("Fichero de defincion de protocolo leido correctamente");}
		//else{System.out.println("Fichero de defincion de protocolo no encontrado!!");
		//System.out.println("---Se cargará parametros por defecto");}
	}

	/**
	 * Lectura de las preferencias de captura del fichero XML, puede leer unas por defecto
	 * y devuelve el estado de la lectura.
	 * 
	 * @param porDefecto activa las propiedades por defecto
	 * @return estado
	 */
	public boolean leerXMLCapture(boolean porDefecto) {
		String auxFileName;
		boolean exists=true;
		try {
			// Request document building without validation
			SAXBuilder builder = new SAXBuilder(false);
			exists = (new File(getFileCapture())).exists();
			if (exists) {
				Document doc = builder.build(new File(getFileCapture()));
				// Get the root element
				Element root = doc.getRootElement();
				// Print servlet information
				List preferences = root.getChildren();
				//System.out.println("This XML has " + preferences.size()
				//		+ " registered preferences:");
				//System.out.println(" =>" preferences. +" registered
				// preferences:");
				exists=false;
				Iterator i = preferences.iterator();
				while (i.hasNext()) {
					Element pref = (Element) i.next();
					String auxName = pref.getName();
					//System.out.print("=>" + auxName.toString());
					if (auxName.equals("CapturePreferences")) {
						//System.out.print("\t" + auxName.toString());
						exists=true;
						pBCapture = new preferencesBeanCapture();
						prefCapture(pref);
					}
				}
			} 
			if (exists==false) {
				if (porDefecto) pBCapture = new preferencesBeanCapture();
				//pBCapture.setDefaultSettings();
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return exists;
	}
	
	/**
	 * Lectura de las preferencias de exportación del fichero XML.
	 *  
	 * @return estado
	 */
	public boolean leerXMLExport() {
		String auxFileName;
		boolean exists=true;
		try {
			// Request document building without validation
			SAXBuilder builder = new SAXBuilder(false);
			exists = (new File(getFileExport())).exists();
			if (exists) {
				Document doc = builder.build(new File(getFileExport()));
				// Get the root element
				Element root = doc.getRootElement();
				// Print servlet information
				List preferences = root.getChildren();
				//System.out.println("This XML has " + preferences.size()
				//		+ " registered preferences:");
				//System.out.println(" =>" preferences. +" registered
				// preferences:");
				Iterator i = preferences.iterator();
				exists=false;
				while (i.hasNext()) {
					Element pref = (Element) i.next();
					String auxName = pref.getName();
					//System.out.print("=>" + auxName.toString());
					if (auxName.equals("ExportPreferences")) {
						exists=true;
						pBExport = new preferencesBeanExport();
						prefExport(pref);
						//System.out.print("\t" + auxName.toString());
					}
				}
			} 
			if (exists==false){
				pBExport = new preferencesBeanExport();
				//pBCapture.setDefaultSettings();
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return exists;
	}
	
	/**
	 * Lectura de las preferencias de lectura desde fichero del fichero XML.
	 *  
	 * @return estado
	 */
	public boolean leerXMLFromFile() {
		String auxFileName;
		boolean exists=true;
		try {
			// Request document building without validation
			SAXBuilder builder = new SAXBuilder(false);
			exists = (new File(getFileFromFile())).exists();
			if (exists) {
				Document doc = builder.build(new File(getFileFromFile()));
				// Get the root element
				Element root = doc.getRootElement();
				// Print servlet information
				List preferences = root.getChildren();
				//System.out.println("This XML has " + preferences.size()
				//		+ " registered preferences:");
				//System.out.println(" =>" preferences. +" registered
				// preferences:");
				Iterator i = preferences.iterator();
				exists=false;
				while (i.hasNext()) {
					Element pref = (Element) i.next();
					String auxName = pref.getName();
					//System.out.print("=>" + auxName.toString());
					if (auxName.equals("FromFilePreferences")) {
						exists=true;
						pBFromFile = new preferencesBeanFromFile();
						prefFromFile(pref);
						//System.out.print("\t" + auxName.toString());
					}
				}
			} 
			if (exists==false){
				pBFromFile = new preferencesBeanFromFile();
				pBFromFile.setDefaultSettings();
				//pBCapture.setDefaultSettings();
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return exists;
	}
		
	/**
	 * Lectura de las preferencias de METADATOS del fichero XML
	 *  
	 * @return estado
	 */
	public boolean leerXMLMETA() {
		String auxFileName;
		boolean exists=true;
		try {
			// Request document building without validation
			SAXBuilder builder = new SAXBuilder(false);
			exists = (new File(getFileMETA())).exists();
			if (exists) {
				Document doc = builder.build(new File(getFileMETA()));
				// Get the root element
				Element root = doc.getRootElement();
				// Print servlet information
				List preferences = root.getChildren();
				//System.out.println("This XML has " + preferences.size()
				//		+ " registered preferences:");
				//System.out.println(" =>" preferences. +" registered
				// preferences:");
				Iterator i = preferences.iterator();
				while (i.hasNext()) {
					Element pref = (Element) i.next();
					String auxName = pref.getName();
					//System.out.print("=>" + auxName.toString());
					if (auxName.equals("MetaCapture")) {
						pBMeta = new preferencesBeanMeta();
						prefMeta(pref);
					//	System.out.print("\t" + auxName.toString());
					}
				}
			} else {
				pBMeta = new preferencesBeanMeta();
				//pBCapture.setDefaultSettings();
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return exists;
	}
	
	/**
	 * Lectura de la definicion del protocolo del fichero XML
	 *  
	 * @return estado
	 */
	public preferencesBeanDefinicion leerXMLDefinicion() {
		String auxFileName;
		boolean exists=true;
		try {
			
			SAXBuilder builder = new SAXBuilder(false);
			exists = (new File(getFileDefinicion())).exists();
			if (exists) {
				Document doc = builder.build(new File(getFileDefinicion()));
				// Get the root element
				Element root = doc.getRootElement();
				// Print servlet information
				List preferences = root.getChildren();
				Iterator i = preferences.iterator();
				while (i.hasNext()) {
					Element pref = (Element) i.next();
					String auxName = pref.getName();
					if (auxName.equals("DefinicionProtocolo")) {
						pBDefinicion = new preferencesBeanDefinicion();
						prefDefinicion(pref);
					}
				}
			} else {
				pBDefinicion = new preferencesBeanDefinicion();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return pBDefinicion;
}
	
	/**
	 * Lectura de la definicion del protocolo del fichero XML
	 *  
	 * @return estado
	 */
	public preferencesBeanIdentificacion leerXMLIdentificacion(File aux) {
		boolean exists=true;
		try {
			
			SAXBuilder builder = new SAXBuilder(false);
			//exists = (new File(getFileDefinicion())).exists();
			if (exists) {
				Document doc = builder.build(aux);
				// Get the root element
				Element root = doc.getRootElement();
				// Print servlet information
				List preferences = root.getChildren();
				Iterator i = preferences.iterator();
				while (i.hasNext()) {
					Element pref = (Element) i.next();
					String auxName = pref.getName();
					if (auxName.equals("DefinicionProtocolo")) {
						pBIdentificacion = new preferencesBeanIdentificacion();
						//pBIdentificacion.setNomProtocolo(aux.getAbsolutePath());
						pBIdentificacion.setRutaProtocolo(aux.getAbsolutePath());
						prefIdentificacion(pref);
					}
				}
			} else {
				pBIdentificacion = new preferencesBeanIdentificacion();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return pBIdentificacion;
}
	
	/**
	 * Lectura de la definicion del protocolo del fichero XML
	 *  
	 * @return estado
	 */
	public preferencesBeanDefinicion leerXMLProtocoloIdentificado(File aux) {
		boolean exists=true;
		try {
			
			SAXBuilder builder = new SAXBuilder(false);
			//exists = (new File(getFileDefinicion())).exists();
			if (exists) {
				Document doc = builder.build(aux);
				// Get the root element
				Element root = doc.getRootElement();
				// Print servlet information
				List preferences = root.getChildren();
				Iterator i = preferences.iterator();
				while (i.hasNext()) {
					Element pref = (Element) i.next();
					String auxName = pref.getName();
					if (auxName.equals("DefinicionProtocolo")) {
						pBDefinicion = new preferencesBeanDefinicion();
						prefDefinicion(pref);
					}
				}
			} else {
				pBDefinicion = new preferencesBeanDefinicion();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return pBDefinicion;
}
	
	/**
	 * Lectura de las preferencias de exportación del fichero XML para la insercion.
	 *  
	 * @return estado
	 */
	public boolean leerXMLExportInsercion() {
		String auxFileName;
		boolean exists=true;
		try {
			SAXBuilder builder = new SAXBuilder(false);
			exists = (new File(getFileExport())).exists();
			if (exists) {
				Document doc = builder.build(new File(getFileExport()));
				Element root = doc.getRootElement();
				List preferences = root.getChildren();
				Iterator i = preferences.iterator();
				exists=false;
				pBExportInsert = new preferencesBeanExportInsercion();
				while (i.hasNext()) {
					Element pref = (Element) i.next();
					String auxName = pref.getName();
					if (auxName.equals("Packet")) {
						exists=true;
						prefExportInsercion(pref);
					}
				}
			} 
			if (exists==false){
				pBExportInsert = new preferencesBeanExportInsercion();
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return exists;
	}
	
/*
	private void leerXML_old() {
		String auxFileName;
		boolean exists;
		try {
			// Request document building without validation
			SAXBuilder builder = new SAXBuilder(false);
			exists = (new File(getFileCapture())).exists();
			if (exists) {
				Document doc = builder.build(new File(getFileCapture()));
				// Get the root element
				Element root = doc.getRootElement();
				// Print servlet information
				List preferences = root.getChildren();
				System.out.println("This XML has " + preferences.size()
						+ " registered preferences:");
				//System.out.println(" =>" preferences. +" registered
				// preferences:");
				Iterator i = preferences.iterator();
				while (i.hasNext()) {
					Element pref = (Element) i.next();
					String auxName = pref.getName();
					//System.out.print("=>" + auxName.toString());
					if (auxName.equals("CapturePreferences")) {
						System.out.print("\t" + auxName.toString());
						pBCapture = new preferencesBeanCapture();
						prefCapture(pref);
					} else {
						if (auxName.equals("ExportPreferences")) {
							pBExport = new preferencesBeanExport();
							prefExport(pref);
							System.out.print("\t" + auxName.toString());
						} else {
							if (auxName.equals("FromFilePreferences")) {
								pBFromFile = new preferencesBeanFromFile();
								prefFromFile(pref);
								System.out.print("\t" + auxName.toString());
							} else {
								if (auxName.equals("MetaCapture")) {
									pBMeta = new preferencesBeanMeta();
									prefMeta(pref);
									System.out.print("\t" + auxName.toString());
								}
							}
						}
					}
				}
			} else {
				pBCapture = new preferencesBeanCapture();
				pBCapture.setDefaultSettings();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
*/
	/**
	 * Establece el fichero de propiedades de captura.
	 * 
	 * @param aux
	 *            ruta 
	 */
	public void setFileCapture(String aux) {
		this.ficheroCapture = aux;
	}

	/**
	 * Devuelve las propiedades de captura.
	 * 
	 * @return String ruta 
	 */
	public String getFileCapture() {
		String aux;
		if (ficheroCapture == null || ficheroCapture == "") {
			aux = getDefaultFileCapture();
		} else {
			aux = ficheroCapture;
		}
		return aux;
	}

	/**
	 * Devuelve el fichero por defecto de las propiedades de captura.
	 * 
	 * @return String ruta 
	 */
	public String getDefaultFileCapture() {
		return "./files/parametrizacion/DefaultPreferencesCapture.xml";
	}

	/**
	 * Establece el fichero de propiedades de exportación.
	 * 
	 * @param aux
	 *            ruta 
	 */
	public void setFileExport(String aux) {
		this.ficheroExport = aux;
	}

	/**
	 * Establece el fichero de definicion del protocolo.
	 * 
	 * @param aux
	 *            ruta 
	 */
	public void setFileDefinicion(String aux) {
		this.ficheroDefinicion = aux;
	}
	
	/**
	 * Devuelve el fichero de propiedades de exportación.
	 * 
	 * @return String ruta 
	 */
	public String getFileExport() {
		String aux;
		if (ficheroExport == null || ficheroExport == "") {
			aux = getDefaultFileExport();
		} else {
			aux = ficheroExport;
		}
		return aux;
	}

	/**
	 * Devuelve el fichero de propiedades de exportación.
	 * 
	 * @return String ruta.
	 */
	public String getDefaultFileExport() {
		return "./files/parametrizacion/DefaultPreferencesExport.xml";
	}
	
	/**
	 * Establece el fichero de propiedades de captura desde dichero.
	 * 
	 * @param aux
	 *            ruta
	 */
	public void setFileFromFile(String aux) {
		this.ficheroFromFile = aux;
	}

	/**
	 * Devuelve el fichero de propiedades de captura desde dichero.
	 * 
	 * @return String ruta
	 */
	public String getFileFromFile() {
		String aux;
		if (ficheroFromFile == null || ficheroFromFile == "") {
			aux = getDefaultFileFromFile();
		} else {
			aux = ficheroFromFile;
		}
		return aux;
	}

	/**
	 * Devuelve el fichero por defecto de propiedades de captura desde dichero.
	 * 
	 * @return String ruta
	 */
	public String getDefaultFileFromFile() {
		return "./files/parametrizacion/DefaultPreferencesFromFile.xml";
	}
	
	/**
	 * Establece el fichero de propiedades del META fichero.
	 * 
	 * @param aux
	 *            ruta
	 */
	public void setFileMETA(String aux) {
		this.ficheroMETA = aux;
	}

	/**
	 * Devuelve el fichero de propiedades del META fichero
	 * 
	 * @return String ruta.
	 */
	public String getFileMETA() {
		String aux;
		if (ficheroMETA == null || ficheroMETA == "") {
			aux = getDefaultFileMETA();
		} else {
			aux = ficheroMETA;
		}
		return aux;
	}

	/**
	 * Devuelve el fichero de definicion del protocolo
	 * 
	 * @return String ruta.
	 */
	public String getFileDefinicion() {
		return ficheroDefinicion;
	}
	
	/**
	 * Devuelve el fichero por defecto de propiedades del META fichero
	 * 
	 * @return String ruta
	 */
	public String getDefaultFileMETA() {
		return "./files/preferencias/DefaultPreferencesFromFile.xml";
	}
	
	/**
	 * Devuelve las preferencias de captura
	 * 
	 * @return instancia de propiedades de capture
	 */
	public preferencesBeanCapture getPBCapture() {
		if (this.pBCapture == null) pBCapture = new preferencesBeanCapture();
		return this.pBCapture;
		// AKI poner si es null new , sino hacer uno nuevo
	}

	/**
	 * Devuelve las preferecias de exportacin
	 * 
	 * @return instancia de preferencias de exportación
	 */
	public preferencesBeanExport getPBExport() {
		if (this.pBExport == null) pBExport = new preferencesBeanExport();
		return this.pBExport;
	}

	/**
	 * Devuelve las preferecias de captura desde fichero
	 * 
	 * @return instancia de preferencias de captura de fichero
	 */
	public preferencesBeanFromFile getPBFromFile() {
		if (this.pBFromFile == null) pBFromFile = new preferencesBeanFromFile();
		return this.pBFromFile;
	}

	/**
	 * Devuelve las preferecias de meta captura
	 * 
	 * @return instancia de preferencias de meta captura
	 */
	public preferencesBeanMeta getPBMeta() {
		if (this.pBMeta == null) pBMeta = new preferencesBeanMeta();
		return this.pBMeta;
	}

	/**
	 * Devuelve las preferencias de definicion
	 * 
	 * @return instancia de propiedades de definicion
	 */
	public preferencesBeanDefinicion getPBDefinicion() {
		if (this.pBDefinicion == null) pBDefinicion = new preferencesBeanDefinicion();
		return this.pBDefinicion;
		// AKI poner si es null new , sino hacer uno nuevo
	}
	
	/**
	 * Genera las preferencias de captura leídas desde el xml
	 * 
	 * @param auxElement
	 *            del xml de tipo CapurePreferences
	 */
	private void prefCapture(Element auxElement) {

		prefCaptureCap(auxElement.getChild("Capture"));
		prefCaptureFile(auxElement.getChild("File"));
		prefCaptureStop(auxElement.getChild("Stop_Capture"));
	}

	/**
	 * Genera las preferencias de exportación leídas desde el xml
	 * 
	 * @param auxElement
	 *            del xml de tipo ExportPreferences
	 */
	private void prefExport(Element auxElement) {

		prefExportFile(auxElement.getChild("File"));
		prefExportStatistics(auxElement.getChild("Statistics"));
	}

	/**
	 * Genera las preferencias de captura desde fichero leídas desde el xml
	 * 
	 * @param auxElement
	 *            del xml de tipo FromFilePreferences
	 */
	private void prefFromFile(Element auxElement) {

		prefFromFileCap(auxElement.getChild("Capture"));
		prefFromFileFile(auxElement.getChild("File"));
		prefFromFileStop(auxElement.getChild("Stop_Capture"));
		
	}

	/**
	 * Genera las preferencias de captura desde fichero leídas desde el xml
	 * 
	 * @param auxElement
	 *            del xml de tipo CapureMETA
	 */
	private void prefMeta(Element auxElement) {

		prefMetaLocate(auxElement.getChild("Locate"));
		prefMetaMultipleFiles(auxElement.getChild("Multiple_Files"));
	}

	/**
	 * Genera las preferencias de Captura leídas desde el xml Dentro de este la
	 * rama CapturePreferences
	 * 
	 * @param auxElement
	 *            del xml de tipo Capture
	 */
	private void prefCaptureCap(Element auxElement) {
		Element tempElement;
		String tempString;
		pBCapture.setCapDispositive(auxElement.getChild("Interface").getText());
		pBCapture.setCapPromiscuousMode(validate(auxElement.getChild(
				"Promiscuous_Modo").getText()));
		tempElement = auxElement.getChild("Filter");
		pBCapture.setCapFilter(validate(tempElement.getAttributeValue("Id")));
		pBCapture.setCapAdvanceId(validate(tempElement.getChild("Advanced")
				.getAttributeValue("Id")));
		pBCapture.setCapAdvance(tempElement.getChild("Advanced").getText());
		tempElement = tempElement.getChild("Normal");
		pBCapture.setCapNormal(validate(tempElement.getAttributeValue("Id")));
		pBCapture.setCapHost(tempElement.getChild("Host").getText());
		pBCapture.setCapProtocol(tempElement.getChild("Protocol").getText());
		pBCapture.setCapPort(tempElement.getChild("Port").getText());
	}

	/**
	 * Genera las preferencias de Captura leídas desde el xml Dentro de este la
	 * rama CapturePreferences
	 * 
	 * @param auxElement
	 *            del xml de tipo File
	 */
	private void prefCaptureFile(Element auxElement) {
		Element tempElement;
		String tempString;
		pBCapture.setFilLocate(auxElement.getChild("Locate").getText());
		tempElement = auxElement.getChild("Multiple_Files");
		pBCapture.setFilMultipleFileId(validate(tempElement
				.getAttributeValue("Id")));
		pBCapture.setFilSpaceId(validate(tempElement.getChild("Space")
				.getAttributeValue("Id")));
		pBCapture.setFilSpaceType(tempElement.getChild("Space")
				.getAttributeValue("Type"));
		pBCapture.setFilSpace(tempElement.getChild("Space").getText());
		pBCapture.setFilTimeId(validate(tempElement.getChild("Time")
				.getAttributeValue("Id")));
		pBCapture.setFilTimeType(tempElement.getChild("Time")
				.getAttributeValue("Type"));
		pBCapture.setFilTime(tempElement.getChild("Time").getText());
		pBCapture.setFilRingBufferId(validate(tempElement.getChild(
				"Ring_Buffer").getAttributeValue("Id")));
		pBCapture.setFilRingBufferType(tempElement.getChild("Ring_Buffer")
				.getAttributeValue("Type"));
		pBCapture.setFilRingBuffer(tempElement.getChild("Ring_Buffer")
				.getText());
		pBCapture.setFilStopAfterId(validate(tempElement.getChild("Stop_After")
				.getAttributeValue("Id")));
		pBCapture.setFilStopAfterType(tempElement.getChild("Stop_After")
				.getAttributeValue("Type"));
		pBCapture.setFilStopAfter(tempElement.getChild("Stop_After").getText());
	}

	/**
	 * Genera las preferencias de Captura leídas desde el xml Dentro de este la
	 * rama CapturePreferences
	 * 
	 * @param auxElement
	 *            del xml de tipo Stop_Capture
	 */
	private void prefCaptureStop(Element auxElement) {
		pBCapture.setstpAfterPacketsId(validate(auxElement.getChild(
				"After_Packets").getAttributeValue("Id")));
		pBCapture.setstpAfterPackets(auxElement.getChild("After_Packets")
				.getText());
		pBCapture.setstpAfterSpaceId(validate(auxElement
				.getChild("After_Space").getAttributeValue("Id")));
		pBCapture.setstpAfterSpaceType(auxElement.getChild("After_Space")
				.getAttributeValue("Type"));
		pBCapture
				.setstpAfterSpace(auxElement.getChild("After_Space").getText());
		pBCapture.setstpAfterTimeId(validate(auxElement.getChild("After_Time")
				.getAttributeValue("Id")));
		pBCapture.setstpAfterTimeType(auxElement.getChild("After_Time")
				.getAttributeValue("Type"));
		pBCapture.setstpAfterTime(auxElement.getChild("After_Time").getText());
	}

	/**
	 * Genera las preferencias de Exportación leídas desde el xml Dentro de este
	 * la rama ExportPreferences
	 * 
	 * @param auxElement
	 *            del xml de tipo File
	 */
	private void prefExportFile(Element auxElement) {
		pBExport.setExpType(auxElement.getChild("Type").getText());
		pBExport.setExpSource(auxElement.getChild("Source").getText());
		pBExport
				.setExpDestination(auxElement.getChild("Destination").getText());
		pBExport.setExpMultifile(validate(auxElement.getChild("Multiple_Files").getText()));
	}

	/**
	 * Genera las preferencias de Exportación leídas desde el xml Dentro de este
	 * la rama ExportPreferences
	 * 
	 * @param auxElement
	 *            del xml de tipo Statistics
	 */
	private void prefExportStatistics(Element auxElement) {
		pBExport.setExpStatistics(validate(auxElement.getAttributeValue("Id")));
	}

	/**
	 * Genera las preferencias de captura desde fichero leídas desde el xml
	 * Dentro de este la rama FromFilePreferences
	 * 
	 * @param auxElement
	 *            del xml de tipo Capture
	 */
	private void prefFromFileCap(Element auxElement) {
		Element tempElement;
		String tempString;
		pBFromFile.setffCapSource(auxElement.getChild("Source").getText());
		tempElement = auxElement.getChild("Filter");
		pBFromFile
				.setffCapFilterId(validate(tempElement.getAttributeValue("Id")));
		pBFromFile.setffCapAdvanceId(validate(tempElement.getChild("Advanced")
				.getAttributeValue("Id")));
		pBFromFile.setffCapAdvance(tempElement.getChild("Advanced").getText());
		tempElement = tempElement.getChild("Normal");
		pBFromFile
				.setffCapNormal(validate(tempElement.getAttributeValue("Id")));
		pBFromFile.setffCapHost(tempElement.getChild("Host").getText());
		pBFromFile.setffCapProtocol(tempElement.getChild("Protocol").getText());
		pBFromFile.setffCapPort(tempElement.getChild("Protocol").getText());
	}

	/**
	 * Genera las preferencias de captura desde fichero leídas desde el xml
	 * Dentro de este la rama FromFilePreferences
	 * 
	 * @param auxElement
	 *            del xml de tipo File
	 */
	private void prefFromFileFile(Element auxElement) {
		Element tempElement;
		String tempString;
		pBFromFile.setffFilLocate(auxElement.getChild("Locate").getText());
		tempElement = auxElement.getChild("Multiple_Files");
		pBFromFile.setffFilMultipleFileId(validate(tempElement
				.getAttributeValue("Id")));
		
		pBFromFile.setffFilSpaceId(validate(tempElement.getChild("Space")
				.getAttributeValue("Id")));
		pBFromFile.setffFilSpaceType(tempElement.getChild("Space")
				.getAttributeValue("Type"));
		pBFromFile.setffFilSpace(tempElement.getChild("Space").getText());
		pBFromFile.setffFilRingBufferId(validate(tempElement.getChild(
				"Ring_Buffer").getAttributeValue("Id")));
		pBFromFile.setffFilRingBufferType(tempElement.getChild("Ring_Buffer")
				.getAttributeValue("Type"));
		pBFromFile.setffFilRingBuffer(tempElement.getChild("Ring_Buffer")
				.getText());
		pBFromFile.setffFilStopAfterId(validate(tempElement.getChild("Stop_After")
				.getAttributeValue("Id")));
		pBFromFile.setffFilStopAfterType(tempElement.getChild("Stop_After")
				.getAttributeValue("Type"));
		pBFromFile.setffFilStopAfter(tempElement.getChild("Stop_After").getText());
	}

	/**
	 * Genera las preferencias de captura desde fichero leídas desde el xml
	 * Dentro de este la rama FromFilePreferences
	 * 
	 * @param auxElement
	 *            del xml de tipo Stop_Capture
	 */
	private void prefFromFileStop(Element auxElement) {
		pBFromFile.setffStpAfterPacketsId(validate(auxElement.getChild(
				"After_Packets").getAttributeValue("Id")));
		pBFromFile.setffStpAfterPackets(auxElement.getChild("After_Packets")
				.getText());
	}

	/**
	 * Genera las preferencias de Captura leídas desde el xml Dentro de este la
	 * rama Locate
	 * 
	 * @param auxElement
	 *            del xml de tipo MetaCapture
	 */
	private void prefMetaLocate(Element auxElement) {
		Element tempElement;
		String tempString;
		pBMeta.setMetLocRelativeId(validate(auxElement.getChild("Relative")
				.getAttributeValue("Id")));
		pBMeta.setMetLocRelative(auxElement.getChild("Relative").getText());
		tempElement = auxElement.getChild("Absolute");
		pBMeta
				.setMetLocAbsotuteId(validate(tempElement
						.getAttributeValue("Id")));
		pBMeta.setMetLocAbsotutePath(tempElement.getChild("Path").getText());
		pBMeta.setMetLocAbsotuteName(tempElement.getChild("Name").getText());
	}

	/**
	 * Genera las preferencias de Captura leídas desde el xml Dentro de este la
	 * rama Multiple_Files
	 * 
	 * @param auxElement
	 *            del xml de tipo MetaCapture
	 */
	private void prefMetaMultipleFiles(Element auxElement) {
		pBMeta
				.setMetMultipleFileId(validate(auxElement
						.getAttributeValue("Id")));
		pBMeta.setMetMFRingBufferId(validate(auxElement.getChild("Ring_Buffer")
				.getAttributeValue("Id")));
		pBMeta.setMetMFRingBuffer(auxElement.getChild("Ring_Buffer").getText());
		pBMeta.setMetMFStar(auxElement.getChild("Start").getText());
		pBMeta.setMetMFEnd(auxElement.getChild("End").getText());
	}

	/**
	 * Genera las preferencias de definicion leídas desde el xml
	 * 
	 * @param auxElement
	 *            del xml de tipo DefinicionPreferences
	 */
	private void prefExportInsercion(Element auxElement) {
		if(auxElement.getChild("IP_v4") !=null){
			prefExportIp(auxElement.getChild("IP_v4"));//"IP_V4"
		}
		if(auxElement.getChild("Ethernet_Frame")!=null){
			prefExportMac(auxElement.getChild("Ethernet_Frame"));//"Ethernet_Frame"
		}
	}
	
	/**
	 * Genera las preferencias de exportacion para insercion leídas desde el xml.
	 * 
	 * @param auxElement
	 *            del xml de tipo Definicion
	 */
	
	private void prefExportIp(Element auxElement) {
		
		//me pasa cada compo y solo tengo que recoger los valores
		//for(int columna=0;columna<pBDefinicion.getNumColumnas();columna++){
		if(auxElement.getChild("Source_IP")!= null){
			if(pBExportInsert.ipOrigen.contains(auxElement.getChild("Source_IP").getText()) == false){
				pBExportInsert.setIpOrigen(auxElement.getChild("Source_IP").getText());
		
			}
		}
		if(auxElement.getChild("Destination_IP")!=null){
			if(pBExportInsert.ipDestino.contains(auxElement.getChild("Destination_IP").getText()) == false){
				pBExportInsert.setIpDestino(auxElement.getChild("Destination_IP").getText());
		
			}	
	
		}
	}
	/**
	 * Genera las preferencias de exportacion para insercion leídas desde el xml.
	 * 
	 * @param auxElement
	 *            del xml de tipo Definicion
	 */
	
	private void prefExportMac(Element auxElement) {
		
		//me pasa cada compo y solo tengo que recoger los valores
		//for(int columna=0;columna<pBDefinicion.getNumColumnas();columna++){
		if(auxElement.getChild("Source_MAC")!= null){
			if(pBExportInsert.macOrigen.contains(auxElement.getChild("Source_MAC").getText()) == false){
				pBExportInsert.setMacOrigen(auxElement.getChild("Source_MAC").getText());
			}
		}
		if(auxElement.getChild("Destination_MAC")!= null){
			if(pBExportInsert.macDestino.contains(auxElement.getChild("Destination_MAC").getText()) == false){
				pBExportInsert.setMacDestino(auxElement.getChild("Destination_MAC").getText());
			}
		}
	}

	
	/**
	 * Genera las preferencias de Definicion leídas desde el xml Dentro de este la
	 * rama Definicion
	 * 
	 * @param auxElement
	 *            del xml de tipo Definicion
	 */
	private void prefDefinicionUnicas(Element auxElement) {
			pBDefinicion.setNomProtocolo(auxElement.getChild("Protocolo").getText());
			pBDefinicion.setNivel(Integer.parseInt(auxElement.getChild("Nivel").getText()));
			pBDefinicion.setNumCampos(Integer.parseInt(auxElement.getChild("NumeroCampos").getText()));
			pBDefinicion.setCamposClave(auxElement.getChild("CamposClave").getText());
			pBDefinicion.setRFCProtocolo(auxElement.getChild("RFC").getText());
	}
	
	/**
	 * Genera las preferencias de Definicion leídas desde el xml Dentro de este la
	 * rama Definicion Tabla
	 * 
	 * @param auxElement
	 *            del xml de tipo Definicion
	 */
	
	private void prefDefinicionTabla(Element auxElement,int fila) {
		
		//me pasa cada compo y solo tengo que recoger los valores
		//for(int columna=0;columna<pBDefinicion.getNumColumnas();columna++){
			pBDefinicion.setObjetoTabla(fila,0,auxElement.getChild("IDCampo").getText());
			pBDefinicion.setObjetoTabla(fila,1,auxElement.getChild("NombreCampo").getText());
			pBDefinicion.setObjetoTabla(fila,2,auxElement.getChild("TamañoCampo").getText());
			pBDefinicion.setObjetoTabla(fila,3,auxElement.getChild("ValorDefectoCampo").getText());
			pBDefinicion.setObjetoTabla(fila,4,auxElement.getChild("DescripcionCampo").getText());
			pBDefinicion.setObjetoTabla(fila,5,auxElement.getChild("TipoDato").getText());
			pBDefinicion.setObjetoTabla(fila,6,auxElement.getChild("Opcional").getText());
			pBDefinicion.setObjetoTabla(fila,7,auxElement.getChild("CampoReferenciado").getText());
		//}
	}
	
	/**
	 * Genera las preferencias de identificacion de protocolo leídas desde el xml Dentro de este la
	 * rama Definicion
	 * 
	 * @param auxElement
	 *            del xml de tipo Definicion
	 */
	private void prefIdentificacion(Element auxElement) {
		int id=0;
		Element auxE;
		String clave="Clave";
		String clav="Clave";
		String aux;
		clave+= String.valueOf(id);
		//auxE=auxElement.getChild("Clave0");
		//aux=auxE.getText();
		pBIdentificacion.setNomProtocolo(auxElement.getChild("CamposProtocolo").getChild("Protocolo").getText());
		pBIdentificacion.setNivelProtocolo(auxElement.getChild("CamposProtocolo").getChild("Nivel").getText());
		while(auxElement.getChild(clave) != null){
			//pBIdentificacion.setNomProtocolo(auxElement.getChild("NumeroCampo").getText());
			pBIdentificacion.setIdentificador(id,0,auxElement.getChild(clave).getChild("Valor").getText());
			pBIdentificacion.setIdentificador(id,1,String.valueOf((Integer.valueOf(auxElement.getChild(clave).getChild("PosicionInicio").getText()))));
			pBIdentificacion.setIdentificador(id,2,auxElement.getChild(clave).getChild("Tamaño").getText());
			pBIdentificacion.setIdentificador(id,3,auxElement.getChild(clave).getChild("Tipo").getText());
			id++;
			clave=clav;
			clave+= String.valueOf(id);
			//aux=(auxElement.getChild(clave)).getText();
		}
	}
	
	/**
	 * Genera las preferencias de exportacion para insercion leídas desde el xml
	 * 
	 * @param auxElement
	 *            del xml de tipo DefinicionPreferences
	 */
	private void prefDefinicion(Element auxElement) {
		int campo=1;
		String camp="Campo";
		String cam="Campo";
		prefDefinicionUnicas(auxElement.getChild("CamposProtocolo"));
		//bucle para cargar todoos los datos de los campos
		pBDefinicion.setTabla(pBDefinicion.getTabla());
		for(int i=0;i<pBDefinicion.getNumCampos();i++){
			camp+= String.valueOf(campo);
			prefDefinicionTabla(auxElement.getChild(camp),i);
			campo++;
			camp=cam;
		}
		
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
	private boolean validate(String state) {
		boolean auxB;
		if (state.toUpperCase().equals("YES"))
			auxB = true;
		else
			auxB = false;
		return auxB;
	}

	/**
	 * Visualiza el estado de la lectura del xml en ventana
	 */
	public void printState() {
		//		System.out.println(" =>" preferences. +" registered preferences:");
		System.out.println("\n<CaptureSniffer>");
		if (pBCapture != null) {
			System.out.println("\t<CapturePreferences>");
			System.out.println("\t\t<Capture>");
			System.out.println("\t\t\t<Interface>"
					+ noNull(pBCapture.getCapDispositive()) + "</Interface>");
			System.out.println("\t\t\t<Promiscuous_Modo>"
					+ noNull(pBCapture.getCapPromiscuousMode())
					+ "</Promiscuous_Modo>");
			System.out.println("\t\t\t<Filter Id=\""
					+ noNull(pBCapture.getCapFilter()) + "\">");
			System.out.println("\t\t\t\t<Advance Id=\""
					+ noNull(pBCapture.getCapAdvanceId()) + "\">"
					+ noNull(pBCapture.getCapAdvance()) + "</Advance>");
			System.out.println("\t\t\t\t<Normal Id=\""
					+ noNull(pBCapture.getCapNormal()) + "\">");
			System.out.println("\t\t\t\t\t<Host>"
					+ noNull(pBCapture.getCapHost()) + "</Host>");
			System.out.println("\t\t\t\t\t<Protocol>"
					+ noNull(pBCapture.getCapProtocol()) + "</Protocol>");
			System.out.println("\t\t\t\t\t<Port>"
					+ noNull(pBCapture.getCapPort()) + "</Port>");
			System.out.println("\t\t\t\t</Normal>");
			System.out.println("\t\t\t</Filter>");
			System.out.println("\t\t</Capture>");
			//----------------------------------
			System.out.println("\t\t<File>");
			System.out.println("\t\t\t<Locate>"
					+ noNull(pBCapture.getFilLocate()) + "</Locate>");
			System.out.println("\t\t\t<Multiple_Files Id=\""
					+ noNull(pBCapture.getFilMultipleFileId()) + "\">");
			System.out.println("\t\t\t\t<Space Id=\""
					+ noNull(pBCapture.getFilSpaceId()) + "\" Type=\""
					+ noNull(pBCapture.getFilSpaceType()) + "\">"
					+ noNull(pBCapture.getFilSpace()) + "</Space>");
			System.out.println("\t\t\t\t<Time Id=\""
					+ noNull(pBCapture.getFilTimeId()) + "\" Type=\""
					+ noNull(pBCapture.getFilTimeType()) + "\">"
					+ noNull(pBCapture.getFilTime()) + "</Time>");
			System.out.println("\t\t\t\t<Ring_Buffer Id=\""
					+ noNull(pBCapture.getFilRingBufferId()) + "\" Type=\""
					+ noNull(pBCapture.getFilRingBufferType()) + "\">"
					+ noNull(pBCapture.getFilRingBuffer()) + "</Ring_Buffer>");
			System.out.println("\t\t\t\t<Stop_After Id=\""
					+ noNull(pBCapture.getFilStopAfterId()) + "\" Type=\""
					+ noNull(pBCapture.getFilStopAfterType()) + "\">"
					+ noNull(pBCapture.getFilStopAfter()) + "</Stop_After>");
			System.out.println("\t\t\t</Multiple_Files>");
			System.out.println("\t\t</File>");
			//----------------------------------
			System.out.println("\t\t<Stop_Capture>");
			System.out.println("\t\t\t<After_Packets Id=\""
					+ noNull(pBCapture.getstpAfterPacketsId()) + "\"" + ">"
					+ noNull(pBCapture.getstpAfterPackets())
					+ "</After_Packets>");
			System.out.println("\t\t\t<After_Space Id=\""
					+ noNull(pBCapture.getstpAfterSpaceId()) + "\" Type=\""
					+ noNull(pBCapture.getstpAfterSpaceType()) + "\">"
					+ noNull(pBCapture.getstpAfterSpace()) + "</After_Space>");
			System.out.println("\t\t\t<After_Time Id=\""
					+ noNull(pBCapture.getstpAfterTimeId()) + "\" Type=\""
					+ noNull(pBCapture.getstpAfterTimeType()) + "\">"
					+ noNull(pBCapture.getstpAfterTime()) + "</After_Time>");
			System.out.println("\t\t</Stop_Capture>");
			//----------------------------------
			System.out.println("\t</CapturePreferences>");
		}
		//----------------------------------
		//----------------------------------
		//----------------------------------
		if (pBExport != null) {
			System.out.println("\t<ExportPreferences>");
			System.out.println("\t\t<File>");
			System.out.println("\t\t\t<Type>" + noNull(pBExport.getExpType())
					+ "</Type>");
			System.out.println("\t\t\t<Source>"
					+ noNull(pBExport.getExpSource()) + "</Source>");
			System.out.println("\t\t\t<Destination>"
					+ noNull(pBExport.getExpDestination()) + "</Destination>");
			System.out.println("\t\t\t<Multiple_Files>" 
					+ noNull(pBExport.getExpMultifile()) + "</Multiple_Files>");
			System.out.println("\t\t</File>");
			System.out.println("\t\t<Statistics>");
			System.out.println("\t\t</Statistics>");
			System.out.println("\t</ExportPreferences>");
		}
		//----------------------------------
		//----------------------------------
		//----------------------------------
		if (pBFromFile != null) {
			System.out.println("\t<FromFilePreferences>");
			System.out.println("\t\t<Capture>");
			System.out.println("\t\t\t<Source>"
					+ noNull(pBFromFile.getffCapSource()) + "</Source>");
			System.out.println("\t\t\t<Filter Id=\""
					+ noNull(pBFromFile.getffCapFilterId()) + "\">");
			System.out.println("\t\t\t\t<Advance Id=\""
					+ noNull(pBFromFile.getffCapAdvanceId()) + "\">"
					+ noNull(pBFromFile.getffCapAdvance()) + "</Advance>");
			System.out.println("\t\t\t\t<Normal Id=\""
					+ noNull(pBFromFile.getffCapNormal()) + "\">");
			System.out.println("\t\t\t\t\t<Host>"
					+ noNull(pBFromFile.getffCapHost()) + "</Host>");
			System.out.println("\t\t\t\t\t<Protocol>"
					+ noNull(pBFromFile.getffCapProtocol()) + "</Protocol>");
			System.out.println("\t\t\t\t\t<Port>"
					+ noNull(pBFromFile.getffCapPort()) + "</Port>");
			System.out.println("\t\t\t\t</Normal>");
			System.out.println("\t\t\t</Filter>");
			System.out.println("\t\t</Capture>");
			//----------------------------------
			System.out.println("\t\t<File>");
			System.out.println("\t\t\t<Locate>"
					+ noNull(pBFromFile.getffFilLocate()) + "</Locate>");
			System.out.println("\t\t\t<Multiple_Files Id=\""
					+ noNull(pBFromFile.getffFilMultipleFileId()) + "\">");
			System.out.println("\t\t\t</Multiple_Files>");
			System.out.println("\t\t</File>");
			//----------------------------------
			System.out.println("\t\t<Stop_Capture>");
			System.out.println("\t\t\t<After_Packets Id=\""
					+ noNull(pBFromFile.getffStpAfterPacketsId()) + "\"" + ">"
					+ noNull(pBFromFile.getffStpAfterPackets())
					+ "</After_Packets>");
			System.out.println("\t\t</Stop_Capture>");
			//----------------------------------
			System.out.println("\t</FromFilePreferences>");
		}
		if (pBMeta != null) {
			System.out.println("\t<MetaCapture>");
			System.out.println("\t\t<Locate>");
			System.out.println("\t\t\t<Relative Id=\""
					+ pBMeta.getMetLocRelativeId() + "\">"
					+ pBMeta.getMetLocRelative() + "</Relative>");
			System.out.println("\t\t\t<Absolute Id=\""
					+ pBMeta.getMetLocAbsotuteId() + "\">");
			System.out.println("\t\t\t\t<Path>"
					+ pBMeta.getMetLocAbsotutePath() + "</Path>");
			System.out.println("\t\t\t\t<Name>"
					+ pBMeta.getMetLocAbsotuteName() + "</Name>");
			System.out.println("\t\t\t</Absolute>");
			System.out.println("\t\t</Locate>");
			//----------------------------------
			System.out.println("\t\t<Multiple_Files Id=\""
					+ pBMeta.getMetMultipleFileId() + "\">");
			System.out.println("\t\t\t<Ring_Buffer Id=\""
					+ pBMeta.getMetMFRingBufferId() + "\">"
					+ pBMeta.getMetMFRingBuffer() + "</Ring_Buffer>");
			System.out.println("\t\t\t<Start>" + pBMeta.getMetMFStar()
					+ "</Start>");
			System.out.println("\t\t\t<End>" + pBMeta.getMetMFEnd() + "</End>");
			System.out.println("\t\t</Multiple_Files>");
			//----------------------------------
			System.out.println("\t</MetaCapture>");
		}
		System.out.println("</CaptureSniffer>");
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

	/**
	 * 
	 * @return
	 */
	public preferencesBeanExportInsercion getPBExportInsercion() {
		if (this.pBExportInsert == null) pBExportInsert = new preferencesBeanExportInsercion();
		return this.pBExportInsert;
	}

	
}