package dominio.preferences.capture;

import org.jdom.*; //mirar que clases necesita solamente

import dominio.preferences.preferencesBeanFromFile;
import dominio.preferences.preferencesOperation;

/**
 * Clase que añade al documento XML que estamos generando, los datos de la captura
 * desde fichero del * subelemento "Capture".
 *
 * @author Leonardo García & Jose Ramón Gutierrez
 * @version 2.0
 * 
 * @see dominio.preferences.preferencesBeanCapture
 * @see dominio.preferences.preferencesOperation
 */
public class ElemFromCapture extends Element {
	/** * Dato de tipo preferencias de exportación. */
	private preferencesBeanFromFile pBFromFile;
	/** * Operaciones especias con datos. */
	private preferencesOperation pOperation;


	/**
	 * Constructor de la clase.
	 * 
	 * @param pBFromFile
	 *            preferencias de captura desde fichero.
	 */
	public ElemFromCapture(preferencesBeanFromFile pBFromFile) {
		super("Capture");
		try {
			pOperation = new preferencesOperation();
			this.setPBFromFile(pBFromFile);
			this.setSource();
			this.setFiter();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Establece el sub-elemento Source
	 */
	private void setSource() {
		Element eAux;
		eAux = new Element("Source");
		//eAux.setAttribute("Type","pcap o xml");
		eAux.setText(getPBFromFile().getffCapSource());
		addContent(eAux);
	}

	/**
	 * Establece el sub-elemento Filter
	 */
	private void setFiter() {
		Element eFilter;
		Element eAux;
		eFilter = new Element("Filter");
		eFilter.setAttribute("Id", pOperation.validate(getPBFromFile()
				.getffCapFilterId()));
		eAux = new Element("Advanced");
		eAux.setAttribute("Id", pOperation.validate(getPBFromFile()
				.getffCapAdvanceId()));
		eAux.setText(getPBFromFile().getffCapAdvance());
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
		ePadre.setAttribute("Id", pOperation.validate(getPBFromFile()
				.getffCapNormal()));
		eAux = new Element("Host");
		eAux.setText(getPBFromFile().getffCapHost());
		ePadre.addContent(eAux);
		eAux = new Element("Protocol");
		eAux.setText(getPBFromFile().getffCapProtocol());
		ePadre.addContent(eAux);
		eAux = new Element("Port");
		eAux.setText(getPBFromFile().getffCapPort());
		ePadre.addContent(eAux);
		return ePadre;
	}

	/**
	 * Establece la clase con las preferencias de captura desde fichero.
	 */
	private void setPBFromFile(preferencesBeanFromFile aux) {
		this.pBFromFile = aux;
	}

	/**
	 * Retorna la clase con las preferencias de captura desde fichero.
	 */
	private preferencesBeanFromFile getPBFromFile() {
		return this.pBFromFile;
	}
}