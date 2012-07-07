package dominio.preferences.capture;

import org.jdom.*; //mirar que clases necesita solamente

import dominio.preferences.preferencesBeanExport;
import dominio.preferences.preferencesOperation;

/**
 * Clase que añade al documento XML que estamos generando, los datos de la exportación del 
 * subelemento "File".
 *
 * @author Leonardo García & Jose Ramón Gutierrez
 * @version 2.0
 * 
 * @see dominio.preferences.preferencesBeanCapture
 * @see dominio.preferences.preferencesOperation
 */
public class ElemExpFile extends Element {
	/** * Dato de tipo preferencias de exportación. */
	private preferencesBeanExport pBExport;
	/** * Operaciones especias con datos. */
	private preferencesOperation pOperation;

	/**
	 * Constructor de la clase.
	 * 
	 * @param pBExport
	 *            preferencias de exportación.
	 */
	public ElemExpFile(preferencesBeanExport pBExport) {
		super("File");
		try {
			pOperation = new preferencesOperation();
			this.setPBExport(pBExport);
			this.setType();
			this.setSource();
			this.setDestination();
			this.setMultipleFiles();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Establece el sub-elemento Type
	 */
	private void setType() {
		// add direccion Ip origen
		addContent(new Element("Type").setText(getPBExport().getExpType()));
	}

	/**
	 * Establece el sub-elemento Source
	 */
	private void setSource() {
		Element eAux;
		eAux = new Element("Source");
		//eAux.setAttribute("Type","pcap o xml");
		eAux.setText(getPBExport().getExpSource());
		addContent(eAux);
	}

	/**
	 * Establece el sub-elemento Destination
	 */
	private void setDestination() {
		// add direccion Ip origen
		addContent(new Element("Destination").setText(getPBExport()
				.getExpDestination()));
	}
	
	/**
	 * Establece el sub-elemento Multiple Files
	 */
	private void setMultipleFiles() {
		// add direccion Ip origen
		addContent(new Element("Multiple_Files").setText(pOperation
				.validate(getPBExport().getExpMultifile())));
	}

	/**
	 * Establece la clase con las preferencias de exportación.
	 */
	private void setPBExport(preferencesBeanExport aux) {
		this.pBExport = aux;
	}

	/**
	 * Retorna la clase con las preferencias de exportación
	 */
	private preferencesBeanExport getPBExport() {
		return this.pBExport;
	}
}