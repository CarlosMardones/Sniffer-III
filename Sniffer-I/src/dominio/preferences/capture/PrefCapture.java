package dominio.preferences.capture;

import org.jdom.*; //mirar que clases necesita solamente
import dominio.preferences.preferencesBeanCapture;

/**
 * Crea un fichero en formato XML con la descripción
 * detallada de la configuración - para la captura de paquetes.
 * 
 * @author Leonardo García & Jose Ramón Gutierrez
 * @version 2.0
 * 
 * @see org.jdom.Element
 * @see dominio.preferences.preferencesBeanCapture
 */
public class PrefCapture extends Element {
	/** * Clase que contiene las preferencias para las capturas en tiempo real. */
	private preferencesBeanCapture pBCapture;

	/**
	 * Constructor de la clase.
	 * 
	 * @param pBCapture preferencias
	 */
	public PrefCapture(preferencesBeanCapture pBCapture) {
		super("CapturePreferences");
		try {
			this.setPBCapture(pBCapture);
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
		addContent(new ElemCapCapture(getPBCapture()));
	}

	/**
	 * Establece el sub-elemento Promiscuous_Mode
	 */
	private void setElemFile() {
		// add direccion Ip origen
		addContent(new ElemCapFile(getPBCapture()));
	}

	/**
	 * Establece el sub-elemento Filter
	 */
	private void setElemStopCapture() {
		addContent(new ElemCapStopCapture(getPBCapture()));
	}

	/**
	 * Establece las preferencias de captura
	 * 
	 * @param aux preferencias
	 */
	private void setPBCapture(preferencesBeanCapture aux) {
		this.pBCapture = aux;
	}

	/**
	 * Devuelve las preferencias de captura
	 * 
	 * @return preferencias
	 */
	private preferencesBeanCapture getPBCapture() {
		return this.pBCapture;
	}
}