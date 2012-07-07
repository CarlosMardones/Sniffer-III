package dominio.preferences.capture;

import org.jdom.*;
import dominio.preferences.preferencesBeanCapture;
import dominio.preferences.preferencesOperation;

/**
 * Clase que añade al documento XML que estamos generando, los datos de la captura del 
 * subelemento "Capture".
 *
 * @author Leonardo García & Jose Ramón Gutierrez
 * @version 2.0
 * 
 * @see dominio.preferences.preferencesBeanCapture
 * @see dominio.preferences.preferencesOperation
 */
public class ElemCapCapture extends Element {
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
	public ElemCapCapture(preferencesBeanCapture pBCapture) {
		super("Capture");
		try {
			pOperation = new preferencesOperation();
			this.setPBCapture(pBCapture);
			this.setInterface();
			this.setPromiscuousMode();
			this.setFiter();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Establece el sub-elemento Interface
	 */
	private void setInterface() {
		// add direccion Ip origen
		addContent(new Element("Interface").setText(getPBCapture()
				.getCapDispositive()));
	}

	/**
	 * Establece el sub-elemento Promiscuous_Mode
	 */
	private void setPromiscuousMode() {
		// add direccion Ip origen
		addContent(new Element("Promiscuous_Modo").setText(pOperation
				.validate(getPBCapture().getCapPromiscuousMode())));
	}

	/**
	 * Establece el elemento Filter
	 */
	private void setFiter() {
		Element eFilter;
		Element eAux;
		eFilter = new Element("Filter");
		eFilter.setAttribute("Id", pOperation.validate(getPBCapture()
				.getCapFilter()));
		eAux = new Element("Advanced");
		eAux.setAttribute("Id", pOperation.validate(getPBCapture()
				.getCapAdvanceId()));
		eAux.setText(getPBCapture().getCapAdvance());
		eFilter.addContent(eAux);
		eAux = getNormal();
		eFilter.addContent(eAux);
		addContent(eFilter);
	}

	/**
	 * Establece el elemento Normal
	 */
	private Element getNormal() {
		Element ePadre;
		Element eAux;
		ePadre = new Element("Normal");
		ePadre.setAttribute("Id", pOperation.validate(getPBCapture()
				.getCapNormal()));
		eAux = new Element("Host");
		eAux.setText(getPBCapture().getCapHost());
		ePadre.addContent(eAux);
		eAux = new Element("Protocol");
		eAux.setText(getPBCapture().getCapProtocol());
		ePadre.addContent(eAux);
		eAux = new Element("Port");
		eAux.setText(getPBCapture().getCapPort());
		ePadre.addContent(eAux);
		return ePadre;

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