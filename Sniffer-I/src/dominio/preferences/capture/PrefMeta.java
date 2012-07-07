package dominio.preferences.capture;

import org.jdom.*; //mirar que clases necesita solamente
import dominio.preferences.preferencesBeanMeta;

/**
 * Documento XML que nos da lainformacion necesaria del estado de una captura
 * 
 * @author Leonardo García & Jose Ramón Gutierrez
 * @version 2.0
 * 
 * @see org.jdom.Element
 * @see dominio.preferences.preferencesBeanMeta
 */
public class PrefMeta extends Element {
	/** *Clase que contiene las preferencias para meta datos. */
	private preferencesBeanMeta pBMeta;

	/**
	 * Constructor por defecto de la clase.
	 * 
	 * @param pBMeta preferencias
	 */
	public PrefMeta(preferencesBeanMeta pBMeta) {
		super("MetaCapture");
		try {
			this.setPBMeta(pBMeta);
			this.setElemLocate();
			this.setElemMultipleFile();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Establece el sub-elemento Locate
	 */
	private void setElemLocate() {
		// add direccion Ip origen
		addContent(new ElemMetaLocate(getPBMeta()));
	}

	/**
	 * Establece el sub-elemento Stop-Captura
	 */
	private void setElemMultipleFile() {
		// add direccion Ip origen
		addContent(new ElemMetaMultipleFile(getPBMeta()));
	}

	/**
	 * Establece las preferencias de captura desde fichero.
	 * 
	 * @param aux preferencias
	 */
	private void setPBMeta(preferencesBeanMeta aux) {
		this.pBMeta = aux;
	}

	/**
	 * Devuelve las preferencias de captura desde fichero.
	 * 
	 * @return preferencias
	 */
	private preferencesBeanMeta getPBMeta() {
		return this.pBMeta;
	}
}