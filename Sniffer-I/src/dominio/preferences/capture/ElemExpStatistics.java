package dominio.preferences.capture;

import org.jdom.*; //mirar que clases necesita solamente

import dominio.preferences.preferencesBeanExport;
import dominio.preferences.preferencesOperation;

/**
 * Clase que a�ade al documento XML que estamos generando, los datos de la exportaci�n del 
 * subelemento "Statistics".
 *
 * @author Leonardo Garc�a & Jose Ram�n Gutierrez
 * @version 2.0
 * 
 * @see dominio.preferences.preferencesBeanCapture
 * @see dominio.preferences.preferencesOperation
 */
public class ElemExpStatistics extends Element {
	/** * Dato de tipo preferencias de exportaci�n. */
	private preferencesBeanExport pBExport;
	/** * Operaciones especias con datos. */
	private preferencesOperation pOperation;

	/**
	 * Constructor de la clase.
	 * 
	 * @param pBExport
	 *            preferencias de exportaci�n.
	 */
	public ElemExpStatistics(preferencesBeanExport pBExport) {
		super("Statistics");
		try {
			pOperation = new preferencesOperation();
			this.setPBExport(pBExport);
			setAttribute("Id", pOperation.validate(getPBExport()
					.getExpStatistics()));
			this.setRead();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Establece el sub-elemento Read
	 */
	private void setRead() {
		// add direccion Ip origen
		addContent(new Element("Read").setText(pOperation
				.validate(getPBExport().getExpStatistics())));
	}

	/**
	 * Establece la clase con las preferencias de exportaci�n.
	 */
	private void setPBExport(preferencesBeanExport aux) {
		this.pBExport = aux;
	}

	/**
	 * Retorna la clase con las preferencias de exportaci�n.
	 */
	private preferencesBeanExport getPBExport() {
		return this.pBExport;
	}
}