package dominio.preferences.capture;

import org.jdom.*; //mirar que clases necesita solamente

import dominio.preferences.preferencesBeanCapture;
import dominio.preferences.preferencesOperation;

/**
 * Clase que añade al documento XML que estamos generando, los datos de la captura del 
 * subelemento "File".
 *
 * @author Leonardo García & Jose Ramón Gutierrez
 * @version 2.0
 * 
 * @see dominio.preferences.preferencesBeanCapture
 * @see dominio.preferences.preferencesOperation
 */
public class ElemCapStopCapture extends Element {
	/** * Dato de tipo preferencias de captura. */
	private preferencesBeanCapture pBCapture;
	/** * Operaciones especias con datos. */
	private preferencesOperation pOperation;

	/**
	 * Constructor de la clase.
	 * 
	 * @param pBCapture
	 *            preferencias de captura.
	 */
	public ElemCapStopCapture(preferencesBeanCapture pBCapture) {
		super("Stop_Capture");
		try {
			pOperation = new preferencesOperation();
			this.setPBCapture(pBCapture);
			this.setAfterPackets();
			this.setAfterSpace();
			this.setAfterTime();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Establece el elemento Alfer_Packets del XML
	 */
	private void setAfterPackets() {
		Element eAux;
		eAux = new Element("After_Packets");
		eAux.setAttribute("Id", pOperation.validate(getPBCapture()
				.getstpAfterPacketsId()));
		eAux.setText(getPBCapture().getstpAfterPackets());
		addContent(eAux);
	}

	/**
	 * Establece el elemento After_Space del XML
	 */
	private void setAfterSpace() {
		Element eAux;
		eAux = new Element("After_Space");
		eAux.setAttribute("Id", pOperation.validate(getPBCapture()
				.getstpAfterSpaceId()));
		eAux.setAttribute("Type", getPBCapture().getstpAfterSpaceType());
		eAux.setText(getPBCapture().getstpAfterSpace());
		addContent(eAux);
	}

	/**
	 * Establece el elemento Alter_Time del XML
	 */
	private void setAfterTime() {
		Element eAux;
		eAux = new Element("After_Time");
		eAux.setAttribute("Id", pOperation.validate(getPBCapture()
				.getstpAfterTimeId()));
		eAux.setAttribute("Type", getPBCapture().getstpAfterTimeType());
		eAux.setText(getPBCapture().getstpAfterTime());
		addContent(eAux);
	}

	/**
	 * Establece la clase con las preferencias de captura
	 */
	private void setPBCapture(preferencesBeanCapture aux) {
		this.pBCapture = aux;
	}

	/**
	 * Retorna la clase con las preferencias de captura
	 */
	private preferencesBeanCapture getPBCapture() {
		return this.pBCapture;
	}
}