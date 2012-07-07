package dominio.preferences.capture;

import org.jdom.*; //mirar que clases necesita solamente
import dominio.preferences.preferencesBeanFromFile;
import dominio.preferences.preferencesOperation;

/**
 * Clase que añade al documento XML que estamos generando, los datos de la captura
 * desde fichero del * subelemento "File".
 *
 * @author Leonardo García & Jose Ramón Gutierrez
 * @version 2.0
 * 
 * @see dominio.preferences.preferencesBeanCapture
 * @see dominio.preferences.preferencesOperation
 */
public class ElemFromFile extends Element {
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
	public ElemFromFile(preferencesBeanFromFile pBFromFile) {
		super("File");
		try {
			pOperation = new preferencesOperation();
			this.setPBFromFile(pBFromFile);
			this.setLocate();
			this.setMultipleFiles();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Establece el elemento Locate
	 */
	private void setLocate() {
		// add direccion Ip origen
		addContent(new Element("Locate").setText(getPBFromFile()
				.getffFilLocate()));
	}

	/**
	 * Establece el elemento Multiple_Files del XML
	 */
	private void setMultipleFiles() {
		Element eMultipleFiles;
		Element eAux;
		eMultipleFiles = new Element("Multiple_Files");
		eMultipleFiles.setAttribute("Id", pOperation.validate(getPBFromFile()
				.getffFilMultipleFileId()));

		eAux = new Element("Space");
		eAux.setAttribute("Id", pOperation.validate(getPBFromFile()
				.getffFilSpaceId()));
		eAux.setAttribute("Type", getPBFromFile().getffFilSpaceType());
		eAux.setText(getPBFromFile().getffFilSpace());
		eMultipleFiles.addContent(eAux);

		eAux = new Element("Ring_Buffer");
		eAux.setAttribute("Id", pOperation.validate(getPBFromFile()
				.getffFilRingBufferId()));
		eAux.setAttribute("Type", getPBFromFile().getffFilRingBufferType());
		eAux.setText(getPBFromFile().getffFilRingBuffer());
		eMultipleFiles.addContent(eAux);

		eAux = new Element("Stop_After");
		eAux.setAttribute("Id", pOperation.validate(getPBFromFile()
				.getffFilStopAfterId()));
		eAux.setAttribute("Type", getPBFromFile().getffFilStopAfterType());
		eAux.setText(getPBFromFile().getffFilStopAfter());
		eMultipleFiles.addContent(eAux);

		addContent(eMultipleFiles); //.addContent(packet.addContent(new
		// Element("Advanced").setText("Yes")));
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