package dominio.preferences.capture;

import dominio.preferences.*;
import java.io.*;
import org.jdom.*;
import org.jdom.output.*;

/**
 * Crea un fichero en formato XML con la descripción
 * detallada de la configuración - para la captura de paquetes. - exportacion -
 * captura de un fichero ya capturado
 * 
 * @author Leonardo García & Jose Ramón Gutierrez
 * @version 2.0
 * 
 * @see org.jdom.output.XMLOutputter
 * @see dominio.preferences.preferencesBeanCapture
 * @see dominio.preferences.preferencesBeanExport
 * @see dominio.preferences.preferencesBeanFromFile
 * @see dominio.preferences.preferencesBeanMeta
 */
public class PreferencesSniffer {
	/** * Documento padre de el documento XML. */
	protected static Document doc;
	/** * Elemento padre de una rama. */
	protected static Element root;
	/** * Elemento hijo de element root, padre de una rama. */
	protected static Element elem;
	/** * Donde se grabara el XML */
	private String fichero;
	/** * Clase que contiene las preferencias para las capturas en tiempo real. */
	private preferencesBeanCapture pBCapture;
	/** * Clase que contiene las preferencias para las exportacion. */
	private preferencesBeanExport pBExport;
	/** *Clase que contiene las preferencias para las capturas desde fichero. */
	private preferencesBeanFromFile pBFromFile;
	/** *Clase que contiene las preferencias para meta datos. */
	private preferencesBeanFromFile pBMeta;
	
	/**
	 * Constructor por defecto de la clase PreferencesSniffer
	 */
	public PreferencesSniffer() {
		doc = new Document();
		root = new Element("CaptureSniffer");
	}

	/**
	 * Genera el fichero XML creando la estructura de captura mismo.
	 * 
	 * @param pBCapture preferencias
	 */
	public void GenerateCapture(preferencesBeanCapture pBCapture) {
		setPrefCapture(pBCapture);
	}

	/**
	 * Genera el fichero XML creando la estructura de exportación.
	 * 
	 * @param pBExport preferencias
	 */
	public void GenerateExportacion(preferencesBeanExport pBExport) {
		setPrefExport(pBExport);
	}

	/**
	 * Genera el fichero XML creando la estructura de captura desde fichero.
	 * 
	 * @param pBFromFile preferencias
	 */
	public void GenerateFromto(preferencesBeanFromFile pBFromFile) {
		setPrefCaptureFromFile(pBFromFile);
	}

	/**
	 * Genera el fichero XML creando la estructura de META .
	 * 
	 * @param pBMeta preferencias
	 */
	public void GenerateMeta(preferencesBeanMeta pBMeta) {
		setPrefCaptureMeta(pBMeta);
	}

	/**
	 * Establece preferencias para las capturas.
	 * 
	 * @param pBCapture preferencias
	 */
	private void setPrefCapture(preferencesBeanCapture pBCapture) {
		elem = new PrefCapture(pBCapture);
		root.addContent(elem);
	}

	/**
	 * Establece preferencias para las exportaciones.
	 * 
	 * @param pBExport preferencias
	 */
	private void setPrefExport(preferencesBeanExport pBExport) {
		elem = new PrefExport(pBExport);
		root.addContent(elem);
	}

	/**
	 * Establece preferencias para las capturas desde fichero.
	 * 
	 * @param pBFromFile preferencias
	 */
	private void setPrefCaptureFromFile(preferencesBeanFromFile pBFromFile) {
		elem = new PrefFromFile(pBFromFile);
		root.addContent(elem);
	}

	/**
	 * Establece preferencias para los ficheros META.
	 * 
	 * @param pBMeta preferencias
	 */
	private void setPrefCaptureMeta(preferencesBeanMeta pBMeta) {
		elem = new PrefMeta(pBMeta);
		root.addContent(elem);
	}

	/**
	 * Guarda el fichero de preferencias generado en. XML.
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
	 * Establece el fichero de preferencias
	 * 
	 * @param aux ruta
	 */
	public void setFile(String aux) {
		this.fichero = aux;
	}

	/**
	 * Devuelve el fichero de preferencias
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
		aux = "./files/preferencias";
		strF = "prefencesSniffer";
		// nombre del fichero tipo XML
		nombrefichero = aux + "/" + strF + ".xml";
		return nombrefichero;
	}
} // fin de la clase

