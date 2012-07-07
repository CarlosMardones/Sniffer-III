package dominio.properties;

import servicioAccesoDatos.FachadaFicheroDirectorios;

import java.io.*;
import java.util.*;

/**
 * Clase que lee el fichero de propiedades del programa
 * y carga los datos en la clases correspondientes
 * 
 * @author Leonardo García & Jose Ramón Gutierrez
 * @version 2.0
 * 
 * @see java.util.Properties
 * @see PropertiesBeanSniffer
 */
public class PropertiesFileRead {
	/** * Dato de tipo propiedades del Sniffer */
	private PropertiesBeanSniffer pBSniffer;
	/** * propiedades te ipo java. */
	private Properties p;
	/** * Contiene la ruta del fichero de propiedades del Sniffer. */
	private String ficheroSniffer;

	/**
	 * Constructor de la clase 
	 */
	public PropertiesFileRead() {
		p = new Properties(System.getProperties());
	}

	/**
	 * Establece el fichero que va a ser leido
	 * 
	 * @param ruta
	 *            ruta y fichero de preferencias
	 */
	public void setFileReadSniffer(String ruta) {
		this.setFileSniffer(ruta);
		
	}

	/**
	 * Lectura de las preferencias del fichero .property.
	 */
	public void leerPorperties() {
		boolean aux;
		aux = leerPropertiesSniffer();
		if (aux){System.out.println("Fichero de propiedades leido correctamente");}
		else{System.out.println("Fichero de propiedades del programa no encontrado!!");
		System.out.println("---Se cargará parametros por defecto");}
		
	}

	/**
	 * Lectura de las preferencias del programa del fichero .property.
	 * 
	 * @see java.util.Properties
	 * @see PropertiesBeanSniffer
	 */
	public boolean leerPropertiesSniffer() {
		String auxFileName;
		boolean exists=true;
		
		try {
			//pBSniffer = new PropertiesBeanSniffer(); 
			exists = (new File(getFileSniffer())).exists();
			if (exists) {
				FileInputStream propSniffer = new FileInputStream(getFileSniffer());
				p.load(propSniffer);
				if(p.getProperty("WinX")!=null){
					getPBSniffer().setWinX(p.getProperty("WinX"));
				}
				if(p.getProperty("WinY")!=null){
					getPBSniffer().setWinY(p.getProperty("WinY"));
				}
				if(p.getProperty("WinHeight")!=null){
					getPBSniffer().setWinHeight(p.getProperty("WinHeight"));
				}
				if(p.getProperty("WinWidth")!=null){
					getPBSniffer().setWinWidth(p.getProperty("WinWidth"));
				}
				if(p.getProperty("TableView")!=null){
					getPBSniffer().setTableView(p.getProperty("TableView"));
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return exists;
	}

	/**
	 * Graba las preferencias del programa en el fichero .property.
	 */
	public void grabarPorperties() {
		boolean aux;
		aux = grabarPropertiesSniffer();
		if (aux){System.out.println("Fichero de propiedades grabado correctamente");}
		else{System.out.println("Fichero de propiedades del programa no encontrado!!");
		System.out.println("---Se grabará parametros por defecto");}
		
	}

	/**
	 * Graba las preferencias del programa en el fichero .property.
	 * 
	 * @see java.util.Properties
	 * @see PropertiesBeanSniffer
	 */
	private boolean grabarPropertiesSniffer() {
		String auxFileName;
		boolean exists=true;
		try {
			p.put("WinX",getPBSniffer().getWinX());
			p.put("WinY",getPBSniffer().getWinY());
			p.put("WinHeight",getPBSniffer().getWinHeight());
			p.put("WinWidth",getPBSniffer().getWinWidth());
			p.put("TableView",getPBSniffer().getTableView());
			p.store((OutputStream)new FileOutputStream(getDefaultFileSniffer()),"Sniffer I");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return exists;
	}

	
	/**
	 * Establece el fichero de preferencias del programa.
	 * 
	 * @param aux
	 *            ruta 
	 */
	public void setFileSniffer(String aux) {
		this.ficheroSniffer = aux;
	}

	/**
	 * Devuelve las preferecias de programa.
	 * 
	 * @return String ruta 
	 */
	public String getFileSniffer() {
		String aux;
		if (ficheroSniffer == null || ficheroSniffer == "") {
			aux = getDefaultFileSniffer();
		} else {
			aux = ficheroSniffer;
		}
		return aux;
	}

	/**
	 * Devuelve las preferecias por defecto de porgrama.
	 * 
	 * @return String ruta
	 */
	public String getDefaultFileSniffer() {
		return FachadaFicheroDirectorios
		.getdirectorioData("DIR_PROPERTIES") + 
		System.getProperty("file.separator") + 
		"sniffer.property";
	}

	
	/**
	 * Devuelve las preferecias de programa
	 * 
	 * @return instancia de preferencias de capture
	 */
	public PropertiesBeanSniffer getPBSniffer() {
		if (this.pBSniffer == null) pBSniffer = new PropertiesBeanSniffer();
		return this.pBSniffer;
		// AKI poner si es null new , sino hacer uno nuevo
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