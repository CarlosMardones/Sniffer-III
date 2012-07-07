package dominio.preferences.definicion;

import org.jdom.*;

import dominio.preferences.preferencesBeanDefinicion;
import dominio.preferences.preferencesOperation;

/**
 * Clase que añade al documento XML que estamos generando, los datos de la definicion
 * del protocolo
 *
 * @author Carlos Mardones Muga
 * @version 1.0
 * 
 * @see dominio.preferences.preferencesBeanDefinicion
 * @see dominio.preferences.preferencesOperation
 */
public class ElemDefProtocolo extends Element {
	/** * Dato de tipo preferencias de captura. */
	private preferencesBeanDefinicion pBDefinicion;
	/** * Operaciones especias con datos. */
	private preferencesOperation pOperation;

	/**
	 * Constructor de la clase.
	 * 
	 * 
	 * @param pBDefinicion
	 */
	public ElemDefProtocolo(preferencesBeanDefinicion pBDefinicion) {
		super("CamposProtocolo");
		try {
			//tiene que añadir los elementos de la tabla linea a linea
			//primero los valores como nobre, rfc, numero de campos,
			//campos clave y despues añado todos los elementos que definen el protocolo
			
			pOperation = new preferencesOperation();
			this.setPBDefinicion(pBDefinicion);
			this.setNombreProtocolo();
			this.setNivelProtocolo();
			this.setCamposProtocolo();
			this.setCamposClaveProtocolo();
			this.setRFCProtocolo();
			//this.setTabla();
			/*establece los atributos*/
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Establece la clase con las preferencias de definicion
	 *
	 * @param aux
	 */
	private void setPBDefinicion(preferencesBeanDefinicion aux) {
		this.pBDefinicion = aux;
	}
	
	/**
	 * Retorna la clase con las preferencias de definicion
	 *
	 * @return
	 */
	private preferencesBeanDefinicion getPBDefinicion() {
		return this.pBDefinicion;
	}
	
	/*metodos set para añadir al xml las lineas con las cabeceras que se quiere
	 * y los valores recogidos de la definicion del protocolo*/
	
	/**
	 * Establece los valores de la tabla 
	 *
	 */
	private void setTabla(){
		Element eAux;
		//eAux = new Element("Campo1");
		
		for(int i=0; i < getPBDefinicion().getNumCampos();i++ ){
			eAux = new Element("Campo1");
			this.setValores(i);
		}
	}
	
	/**
	 * Establece el nombre del protocolo
	 */
	private void setNombreProtocolo(){
		addContent(new Element("Protocolo").setText(getPBDefinicion().getNomProtocolo()));
	}
	
	/**
	 * Establece el numero de campos del protocolo 
	 */
	private void setCamposProtocolo(){
		addContent(new Element("NumeroCampos").setText(String.valueOf((getPBDefinicion().getNumCampos()))));
	}
	
	/**
	 * Establece los campos clave del protocolo 
	 */
	private void setCamposClaveProtocolo(){
		addContent(new Element("CamposClave").setText(getPBDefinicion().getCamposClave()));
	}
	
	/**
	 * Establece la RFC a la que hace referencia el protocolo
	 */
	private void setRFCProtocolo(){
		addContent(new Element("RFC").setText(getPBDefinicion().getRFCProtocolo()));
	}
	
	/**
	 * Establece el nivel al que pertenece el protocolo
	 */
	private void setNivelProtocolo(){
		addContent(new Element("Nivel").setText(String.valueOf(getPBDefinicion().getNivelProtocolo())));
	}
	
	/**
	 * Establece el valor asociado a cada fila especifacndo el valor para cadacolumna
	 *
	 * @param val
	 */
	private void setValores(int val){
		//addContent(new Element("Campo").setText(String.valueOf(val)));
		addContent(new Element("IDCampo").setText(getPBDefinicion().getObjetoTabla(val, 0).toString()));	
		addContent(new Element("NombreCampo").setText(getPBDefinicion().getObjetoTabla(val, 1).toString()));	
		addContent(new Element("TamañoCampo").setText(getPBDefinicion().getObjetoTabla(val, 2).toString()));	
		addContent(new Element("ValorDefectoCampo").setText(getPBDefinicion().getObjetoTabla(val, 3).toString()));	
		addContent(new Element("DescripcionCampo").setText(getPBDefinicion().getObjetoTabla(val, 4).toString()));	
		addContent(new Element("TipoDato").setText(getPBDefinicion().getObjetoTabla(val, 5).toString()));
		addContent(new Element("Opcional").setText(getPBDefinicion().getObjetoTabla(val, 6).toString()));
		addContent(new Element("Otro").setText(getPBDefinicion().getObjetoTabla(val, 7).toString()));
	}
}