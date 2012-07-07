package dominio.preferences.definicion;

import dominio.preferences.*;
import java.io.*;
import org.jdom.*;
import org.jdom.output.*;

/**
 * Crea un fichero en formato XML con la descripción
 * detallada de un protocolo generado por el usuario.
 * 
 * @author Carlos Mardones Muga
 * @version 3.0
 * 
 * @see org.jdom.output.XMLOutputter
 * @see dominio.preferences.preferencesBeanDefinicion
 */
public class PreferencesSnifferDefinicion {
	/** * Documento padre de el documento XML. */
	protected static Document doc;
	/** * Elemento padre de una rama. */
	protected static Element root;
	/** * Elemento hijo de element root, padre de una rama. */
	protected static Element elem;
	/** * Donde se grabara el XML */
	private String fichero;
	/** *Clase que contiene las preferencias para definicion de protocolo. */
	private preferencesBeanDefinicion pBDefinicion;
	
	/**
	 * Constructor por defecto de la clase PreferencesSniffer.
	 */
	public PreferencesSnifferDefinicion() {
		doc = new Document();
		root = new Element("DefinicionProtocoloSniffer");
	}

	/**
	 * Genera el fichero XML creando la definicion del protocolo .
	 * 
	 * @param pBDefinicion preferencias
	 */
	public void GenerateDefinicion(preferencesBeanDefinicion pBDefinicion) {
		setPrefDefinicion(pBDefinicion);
	}
	
	/**
	 * Establece preferencias para los ficheros de definicion de protocolos.
	 * 
	 * @param pBDefinicion
	 */
	private void setPrefDefinicion(preferencesBeanDefinicion pBDefinicion) {
		elem = new PrefDef(pBDefinicion);
		root.addContent(elem);
	}
	
	/**
	 * Guarda el fichero de definicion generado en. XML.
	 */
	public void savePreferences() {
		String nombrefichero;

		nombrefichero = getFile();
		try {

			doc = new Document(root);

			XMLOutputter out = new XMLOutputter(Format.getPrettyFormat());
			FileOutputStream file = new FileOutputStream(nombrefichero);
			out.output(doc, file);
			file.flush();
			file.close();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Establece el fichero de preferencias.
	 * 
	 * @param aux ruta
	 */
	public void setFile(String aux) {
		this.fichero = aux;
	}

	/**
	 * Devuelve el fichero de preferencias.
	 * 
	 * @return ruta
	 */
	public String getFile() {
		String aux;
		if (fichero == null || fichero == "") {
			aux = getDefaultFile();
		} else {
			aux = fichero;
		}
		return aux;
	}

	/**
	 * Devuelve el fichero por defecto. 
	 * 
	 * @return ruta
	 */
	public String getDefaultFile() {
		String aux;
		String strF;
		String nombrefichero;
		aux = "./files/preferenciasDefinicion";
		strF = "prefencesSnifferDefinicion";
		// nombre del fichero tipo XML
		nombrefichero = aux + "/" + strF + ".xml";
		return nombrefichero;
	}
} // fin de la clase

