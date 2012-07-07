package dominio.preferences.definicion;

import org.jdom.*; //mirar que clases necesita solamente

import dominio.preferences.preferencesBeanDefinicion;


/**
 * Crea un fichero en formato XML con la descripción
 * detallada de un protocolo 
 * 
 * @author Carlos Mardones Muga
 * @version 1.0
 * 
 * @see org.jdom.Element
 * @see dominio.preferences.preferencesBeanDefinicion
 */

public class PrefDef extends Element {
	/** * Clase que contiene las preferencias para la definicion. */
	private preferencesBeanDefinicion pBDefinicion;

	/**
	 * Constructor de la clase.
	 * 
	 * @param pBDefinicion
	 */
	public PrefDef(preferencesBeanDefinicion pBDefinicion) {
		super("DefinicionProtocolo");
		try {
			this.setPBDefinicion(pBDefinicion);
			this.setElemDefClaves();
			this.setElemDef();
			this.setTablaDef();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Establece el sub-elemto de los campos claves de la definicion
	 */
	private void setElemDef(){
		addContent(new ElemDefProtocolo(getPBDefinicion()));
	}
	
	/**
	 * Establece el sub-elemento Definicion
	 */
	private void setElemDefClaves() {
		String [] tabla;
		String claves=pBDefinicion.getCamposClave();
		tabla=claves.split("-");
		for(int i=0;i<tabla.length;i++){
			addContent(new ElemDefClavesProtocolo(getPBDefinicion(),(Integer.valueOf(tabla[i])-1),i));
		}
	}
	
	/**
	 * Establece el sub-elemento de la tabla de Definicion
	 */
	private void setTablaDef() {
		// add direccion Ip origen
		int val;
		for(int i=0;i<(pBDefinicion.getNumCampos());i++){
			val=i+1;
			addContent(new ElemDefTablaProtocolo(getPBDefinicion(),val));
		}
	}
	
	/**
	 * Establece las preferencias de definicion
	 * 
	 * @param aux preferencias
	 */
	private void setPBDefinicion(preferencesBeanDefinicion aux) {
		this.pBDefinicion = aux;
	}
	
	/**
	 * Devuelve las preferencias de definicion
	 * 
	 * @return preferencias
	 */
	private preferencesBeanDefinicion getPBDefinicion() {
		return this.pBDefinicion;
	}

}