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
public class ElemCapFile extends Element {
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
	public ElemCapFile(preferencesBeanCapture pBCapture) {
		super("File");
		try {
			pOperation = new preferencesOperation();
			this.setPBCapture(pBCapture);
			this.setLocate();
			this.setMultipleFiles();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Establece el elemento File del XML
	 */
	private void setLocate() {
		// add direccion Ip origen
		addContent(new Element("Locate").setText(getPBCapture().getFilLocate()));
	}

	/**
	 * Establece el elemento Multiple_Files del XML
	 */
	private void setMultipleFiles() {
		Element eMultipleFiles;
		Element eAux;
		eMultipleFiles = new Element("Multiple_Files");
		eMultipleFiles.setAttribute("Id", pOperation.validate(getPBCapture()
				.getFilMultipleFileId()));
		eAux = new Element("Space");
		eAux.setAttribute("Id", pOperation.validate(getPBCapture()
				.getFilSpaceId()));
		eAux.setAttribute("Type", getPBCapture().getFilSpaceType());
		eAux.setText(getPBCapture().getFilSpace());
		eMultipleFiles.addContent(eAux);
		eAux = new Element("Time");
		eAux.setAttribute("Id", pOperation.validate(getPBCapture()
				.getFilTimeId()));
		eAux.setAttribute("Type", getPBCapture().getFilTimeType());
		eAux.setText(getPBCapture().getFilTime());
		eMultipleFiles.addContent(eAux);
		eAux = new Element("Ring_Buffer");
		eAux.setAttribute("Id", pOperation.validate(getPBCapture()
				.getFilRingBufferId()));
		eAux.setAttribute("Type", getPBCapture().getFilRingBufferType());
		eAux.setText(getPBCapture().getFilRingBuffer());
		eMultipleFiles.addContent(eAux);
		eAux = new Element("Stop_After");
		eAux.setAttribute("Id", pOperation.validate(getPBCapture()
				.getFilStopAfterId()));
		eAux.setAttribute("Type", getPBCapture().getFilStopAfterType());
		eAux.setText(getPBCapture().getFilStopAfter());
		eMultipleFiles.addContent(eAux);
		addContent(eMultipleFiles); //.addContent(packet.addContent(new
		// Element("Advanced").setText("Yes")));
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