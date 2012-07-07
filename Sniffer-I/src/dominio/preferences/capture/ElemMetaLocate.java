package dominio.preferences.capture;

import org.jdom.*; //mirar que clases necesita solamente

import dominio.preferences.preferencesBeanMeta;
import dominio.preferences.preferencesOperation;

/**
 * Clase que añade al documento XML que estamos generando, los datos de la META
 * desde fichero del * subelemento "Locate".
 *
 * @author Leonardo García & Jose Ramón Gutierrez
 * @version 2.0
 * 
 * @see dominio.preferences.preferencesBeanCapture
 * @see dominio.preferences.preferencesOperation
 */
public class ElemMetaLocate extends Element {
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
	public ElemMetaLocate(preferencesBeanMeta pBMeta) {
		super("Locate");
		try {
			this.setPBMeta(pBMeta);
			pOperation = new preferencesOperation();
			this.setRelative();
			this.setAbsolute();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Establece el sub-elemento Relative
	 */
	private void setRelative() {
		Element eAux;
		eAux = new Element("Relative");
		eAux.setAttribute("Id", pOperation.validate(getPBMeta()
				.getMetLocRelativeId()));
		eAux.setText(getPBMeta().getMetLocRelative());
		addContent(eAux);
	}

	/**
	 * Establece el sub-elemento Absolute
	 */
	private void setAbsolute() {
		Element eAux;
		Element eAbsolute;
		eAbsolute = new Element("Absolute");
		eAbsolute.setAttribute("Id", pOperation.validate(getPBMeta()
				.getMetLocAbsotuteId()));
		eAux = new Element("Path");
		eAux.setText(getPBMeta().getMetLocAbsotutePath());
		eAbsolute.addContent(eAux);
		eAux = new Element("Name");
		eAux.setText(getPBMeta().getMetLocAbsotuteName());
		eAbsolute.addContent(eAux);
		addContent(eAbsolute);
	}

	/**
	 * Establece la clase con las preferencias de META
	 */
	private void setPBMeta(preferencesBeanMeta aux) {
		this.pBMeta = aux;
	}

	/**
	 * Retorna la clase con las preferencias de META
	 */
	private preferencesBeanMeta getPBMeta() {
		return this.pBMeta;
	}
}