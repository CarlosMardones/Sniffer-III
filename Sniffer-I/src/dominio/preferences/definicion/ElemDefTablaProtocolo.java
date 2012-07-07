package dominio.preferences.definicion;

import org.jdom.*;

import dominio.preferences.preferencesBeanCapture;
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
public class ElemDefTablaProtocolo extends Element {
	/** * Dato de tipo preferencias de captura. */
	private preferencesBeanDefinicion pBDefinicion;
	/** * Operaciones especias con datos. */
	private preferencesOperation pOperation;

	/**
	 * Constructor de la clase.
	 * 
	 * @param pBDefinicion
	 * @param campo
	 */
	public ElemDefTablaProtocolo(preferencesBeanDefinicion pBDefinicion,int campo) {
		//Añadir la etiqueta que identifica cada campo
		super("Campo"+String.valueOf(campo));
		try {
			//tiene que añadir los elementos de la tabla linea a linea
			//primero los valores como nobre, rfc, numero de campos,
			//campos clave y despues añado todos los elementos que definen el protocolo
			pOperation = new preferencesOperation();
			this.setPBDefinicion(pBDefinicion);
			this.setValores(campo);
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
	
	/**
	 * Establece el valor asociado a cada fila especifacndo el valor para cada columna
	 * @param int val Identificador del campo del protocolo a definir
	 */
	private void setValores(int val){
		//addContent(new Element("Campo").setText(String.valueOf(val)));
		val--;
		if(getPBDefinicion().getObjetoTabla(val, 0)==null){
			addContent(new Element("IDCampo").setText("null"));
		}else{
			addContent(new Element("IDCampo").setText(getPBDefinicion().getObjetoTabla(val, 0).toString()));	
		}
		if(getPBDefinicion().getObjetoTabla(val, 1)==null){
			addContent(new Element("NombreCampo").setText("null"));
		}else{
			addContent(new Element("NombreCampo").setText(getPBDefinicion().getObjetoTabla(val, 1).toString()));	
		}
		if(getPBDefinicion().getObjetoTabla(val, 2)==null){
			addContent(new Element("TamañoCampo").setText("null"));
		}else{
			addContent(new Element("TamañoCampo").setText(getPBDefinicion().getObjetoTabla(val, 2).toString()));	
	    }
		if(getPBDefinicion().getObjetoTabla(val, 3)==null){
			addContent(new Element("ValorDefectoCampo").setText("null"));
		}else{
			addContent(new Element("ValorDefectoCampo").setText(getPBDefinicion().getObjetoTabla(val, 3).toString()));
			//addContent(new Element("ValorDefectoBinario").setText(convertirBinario(getPBDefinicion().getObjetoTabla(val, 3).toString(),getPBDefinicion().getObjetoTabla(val, 5).toString())));
	    }
		if(getPBDefinicion().getObjetoTabla(val, 4)==null){
			addContent(new Element("DescripcionCampo").setText("null"));
		}else{	
			addContent(new Element("DescripcionCampo").setText(getPBDefinicion().getObjetoTabla(val, 4).toString()));	
		}
		if(getPBDefinicion().getObjetoTabla(val, 5)==null){
			addContent(new Element("TipoDato").setText("null"));
		}else{	
			addContent(new Element("TipoDato").setText(getPBDefinicion().getObjetoTabla(val, 5).toString()));	
		}
		if(getPBDefinicion().getObjetoTabla(val, 6)==null){
			addContent(new Element("Opcional").setText("No"));
		}else{	
			addContent(new Element("Opcional").setText(getPBDefinicion().getObjetoTabla(val, 6).toString()));	
		}
		if(getPBDefinicion().getObjetoTabla(val, 7)==null){
			addContent(new Element("CampoReferenciado").setText("null"));
		}else{	
			addContent(new Element("CampoReferenciado").setText(getPBDefinicion().getObjetoTabla(val, 7).toString()));	
		}
	}
	
	/**
	 * Convierte el valor definido a su valor binario en funcion del tipo de dato
	 *
	 * @param cadena
	 * @param tipo
	 * @return
	 */
	private String convertirBinario(String cadena,String tipo){
		if(tipo.equals("Booleano")){
			return convertirBooleano(cadena);
		}
		if(tipo.equals("Numerico")){
			return convertirNumerico(cadena);
		}
		if(tipo.equals("Alfanumerico")){
			return convertirAlfanumerico(cadena);
		}
		return null;
	}
	
	/**
	 * Convierte la cadena en su valor alfanumerico.
	 * @param cadena
	 * @return
	 */
	private String convertirAlfanumerico(String cadena) {
		String binario="";
		for(char letra: cadena.toCharArray()){
			binario+=String.format(Integer.toBinaryString(letra));
		}
		return binario;
	}

	/**
	 * Convierte la cadena en su representacion numerica.
	 * @param cadena
	 * @return
	 */
	private String convertirNumerico(String cadena) {
		return String.format("%16s",Integer.toBinaryString(Integer.valueOf(cadena)));
	}

	/**
	 * Convierte la cadena en su representacion booleana.
	 * @param cadena
	 * @return
	 */
	private String convertirBooleano(String cadena){
		if(cadena.equals("verdadero") || cadena.equals("true")){
			return "1";
		}else{
			return "0";
		}
	}
}