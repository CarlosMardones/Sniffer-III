package dominio.preferences.capture;

import org.jdom.*; //mirar que clases necesita solamente

import dominio.preferences.preferencesBeanMeta;
import dominio.preferences.preferencesOperation;

/**
 * Clase que añade al documento XML que estamos generando, los datos de la META
 * desde fichero del * subelemento "Multiple_Files".
 *
 * @author Leonardo García & Jose Ramón Gutierrez
 * @version 2.0
 * 
 * @see dominio.preferences.preferencesBeanCapture
 * @see dominio.preferences.preferencesOperation
 */
public class ElemMetaMultipleFile extends Element {
	/** * Dato de tipo preferencias de exportación. */
	private preferencesBeanMeta pBMeta;
	/** * Operaciones especias con datos. */
	private preferencesOperation pOperation;

	/**
	 * Constructor de la clase.
	 * 
	 * @param pBMeta
	 *            preferencias de META.
	 */
	public ElemMetaMultipleFile(preferencesBeanMeta pBMeta) {
		super("Multiple_Files");

		//this.setAttribute("Id","YES");
		try {
			this.setPBMeta(pBMeta);
			pOperation = new preferencesOperation();
			this.setMultiple();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Establece el sub-elemento Multiple Files
	 */
	private void setMultiple() {
		Element eAux;
		boolean auxMultipleFile;
		
		auxMultipleFile = getPBMeta().getMetMultipleFileId();
		this.setAttribute("Id",pOperation.validate(auxMultipleFile));
		
		eAux = new Element("Ring_Buffer");
		eAux.setAttribute("Id", pOperation.validate(getPBMeta()
				.getMetMFRingBufferId()));
		if (auxMultipleFile == true) {
			eAux.setText(getPBMeta().getMetMFRingBuffer());
			addContent(eAux);
			eAux = new Element("Start");
			eAux.setText(getPBMeta().getMetMFStar());
			addContent(eAux);
			eAux = new Element("End");
			eAux.setText(getPBMeta().getMetMFEnd());
			addContent(eAux);
		} else {
			eAux.setText("0");
			addContent(eAux);
			eAux = new Element("Start");
			eAux.setText("0");
			addContent(eAux);
			eAux = new Element("End");
			eAux.setText("0");
			addContent(eAux);
		}
	}

	/**
	 * Establece la clase con las preferencias de META.
	 */
	private void setPBMeta(preferencesBeanMeta aux) {
		this.pBMeta = aux;
	}

	/**
	 * Retorna la clase con las preferencias de META.
	 */
	private preferencesBeanMeta getPBMeta() {
		return this.pBMeta;
	}
}