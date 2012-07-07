package dominio.preferences.capture;

import org.jdom.*; //mirar que clases necesita solamente
import dominio.preferences.preferencesBeanFromFile;

/**
 * Crea un fichero en formato XML con la descripción
 * detallada de la configuración - captura de un fichero ya capturado.
 * 
 * @author Leonardo García & Jose Ramón Gutierrez
 * @version 2.0
 * 
 * @see org.jdom.Element
 * @see dominio.preferences.preferencesBeanFromFile
 */
public class PrefFromFile extends Element {
	/** * Clase que contiene las preferencias para las capturas desde fichero. */
	private preferencesBeanFromFile pBFromFile;

	/**
	 * Constructor de la clase
	 * 
	 * @param pBFromFile preferencias
	 */
	public PrefFromFile(preferencesBeanFromFile pBFromFile) {
		super("FromFilePreferences");
		try {
			this.setPBFromFile(pBFromFile);
			this.setElemCapture();
			this.setElemFile();
			this.setElemStopCapture();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Establece el sub-elemento Capture
	 */
	private void setElemCapture() {
		// add direccion Ip origen
		addContent(new ElemFromCapture(getPBFromFile()));
	}

	/**
	 * Establece el sub-elemento Promiscuous_Mode
	 */
	private void setElemFile() {
		// add direccion Ip origen
		addContent(new ElemFromFile(getPBFromFile()));
	}

	/**
	 * Establece el sub-elemento Filter
	 */
	private void setElemStopCapture() {
		addContent(new ElemFromStopCapture(getPBFromFile()));
	}

	/**
	 * Establece las preferencias de captura desde fichero.
	 * 
	 * @param aux preferencias
	 */
	private void setPBFromFile(preferencesBeanFromFile aux) {
		this.pBFromFile = aux;
	}

	/**
	 * Devuelve las preferencias de captura desde fichero.
	 * 
	 * @return preferencias
	 */
	private preferencesBeanFromFile getPBFromFile() {
		return this.pBFromFile;
	}
}