package dominio.preferences.capture;

import org.jdom.*; //mirar que clases necesita solamente
import dominio.preferences.preferencesBeanFromFile;
import dominio.preferences.preferencesOperation;

/**
 * Clase que añade al documento XML que estamos generando, los datos de la captura
 * desde fichero del * subelemento "Stop_Capture".
 *
 * @author Leonardo García & Jose Ramón Gutierrez
 * @version 2.0
 * 
 * @see dominio.preferences.preferencesBeanCapture
 * @see dominio.preferences.preferencesOperation
 */
public class ElemFromStopCapture extends Element {
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
	public ElemFromStopCapture(preferencesBeanFromFile pBFromFile) {
		super("Stop_Capture");
		try {
			pOperation = new preferencesOperation();
			this.setPBFromFile(pBFromFile);
			this.setAfterPackets();
			//this.setAfterSpace();
			//this.setAfterTime();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Establece el sub-elemento Alfer_Packets del XML
	 */
	private void setAfterPackets() {
		Element eAux;
		eAux = new Element("After_Packets");
		eAux.setAttribute("Id", pOperation.validate(getPBFromFile()
				.getffFilMultipleFileId()));
		eAux.setText(getPBFromFile().getffStpAfterPackets());
		addContent(eAux);
	}

	/**
	 * Establece el sub-elemento After_Space del XML
	 */
	private void setAfterSpace() {
		Element eAux;
		eAux = new Element("After_Space");
		eAux.setAttribute("Id", "Yes");
		eAux.setAttribute("Type", "Bytes");
		eAux.setText("1000");
		addContent(eAux);
	}

	/**
	 * Establece el sub-elemento Alter_Time del XML
	 */
	private void setAfterTime() {
		Element eAux;
		eAux = new Element("After_Time");
		eAux.setAttribute("Id", "No");
		eAux.setAttribute("No", "Seconds");
		eAux.setText("1000");
		addContent(eAux);
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