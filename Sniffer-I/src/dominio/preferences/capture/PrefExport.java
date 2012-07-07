package dominio.preferences.capture;

import org.jdom.*; //mirar que clases necesita solamente

import dominio.preferences.preferencesBeanExport;

/**
 * Crea un fichero en formato XML con la descripci�n
 * detallada de la configuraci�n - exportacion.
 * 
 * @author Leonardo Garc�a & Jose Ram�n Gutierrez
 * @version 2.0
 * 
 * @see org.jdom.Element
 * @see dominio.preferences.preferencesBeanExport
 */
public class PrefExport extends Element {
	/** * Clase que contiene las preferencias para las exportacion. */
	private preferencesBeanExport pBExport;

	/**
	 * Constructor de la clase.
	 * 
	 * @param pBExport preferencias
	 */
	public PrefExport(preferencesBeanExport pBExport) {
		super("ExportPreferences");
		try {
			this.setPBExport(pBExport);
			this.setElemFile();
			this.setElemStatistics();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Establece el sub-elemento File
	 */
	private void setElemFile() {
		// add direccion Ip origen
		addContent(new ElemExpFile(getPBExport()));
	}

	/**
	 * Establece el sub-elemento Statatistic
	 */
	private void setElemStatistics() {
		// add direccion Ip origen
		addContent(new ElemExpStatistics(getPBExport()));
	}

	/**
	 * Establece las preferencias de exportaci�n.
	 * 
	 * @param aux preferencias
	 */
	private void setPBExport(preferencesBeanExport aux) {
		this.pBExport = aux;
	}

	/**
	 * Devuelve las preferencias de exportaci�n.
	 * 
	 * @return preferencias
	 */
	private preferencesBeanExport getPBExport() {
		return this.pBExport;
	}
}