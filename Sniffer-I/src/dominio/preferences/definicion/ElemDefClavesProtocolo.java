package dominio.preferences.definicion;

import org.jdom.*;

import dominio.preferences.preferencesBeanDefinicion;
import dominio.preferences.preferencesOperation;

/**
 * Clase que añade al documento XML que estamos generando, los datos de la definicion
 * de las claves del protocolo
 *
 * @author Carlos Mardones Muga
 * @version 1.0
 * 
 * @see dominio.preferences.preferencesBeanDefinicion
 * @see dominio.preferences.preferencesOperation
 */
public class ElemDefClavesProtocolo extends Element {
	/** * Dato de tipo preferencias de captura. */
	private preferencesBeanDefinicion pBDefinicion;
	/** * Operaciones especias con datos. */
	private preferencesOperation pOperation;

	/**
	 * Constructor de la clase.
	 * 
	 * @param pBDefinicion
	 * @param clave
	 * @param id
	 */
	public ElemDefClavesProtocolo(preferencesBeanDefinicion pBDefinicion,int clave,int id) {
		super("Clave"+String.valueOf(id));
		try {
			//Añade especificacion resumiendo campos claves y valores esperados 
			// y posiciones donde leelos
			pOperation = new preferencesOperation();
			this.setPBDefinicion(pBDefinicion);
			this.setCamposClave(clave);
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
	 * Establece el numero del campo Clave
	 *
	 * @param campo
	 */
	private void setNumeroCampoClave(String campo){
		addContent(new Element("NumeroCampo").setText(campo));
	}
	
	/**
	 * Establece el valor del campo clave
	 *
	 * @param valor
	 * @param tipo
	 * @param tamaño
	 */
	private void setValorCampoClave(String valor,String tipo,String tamaño){
		//addContent(new Element("ValorCampo").setText(valor));
		addContent(new Element("Valor").setText(valor));
		addContent(new Element("Tipo").setText(tipo));
	}
	
	/**
	 * Establece el valor del campo clave
	 *
	 * @param valor
	 */
	private void setPosicionInicioCampoClave(String valor){
		addContent(new Element("PosicionInicio").setText(valor));
	}
	
	/**
	 * Establece el tamaño del campo Clave
	 *
	 * @param valor
	 */
	private void setTamañoCampoClave(String valor){
		addContent(new Element("Tamaño").setText(String.valueOf((Integer.valueOf(valor))/8)));
	}
	
	/**
	 * Establece el valor y caracteristicas de todos los campos claves
	 *
	 * @param i
	 */
	private void setCamposClave(int i){
		//String [] campos = valoresCampoClave(); 
		//for(int i=0;i < campos.length;i++ ){
			//this.setNumeroCampoClave(campos[i]);
			this.setValorCampoClave(valorCampoClave(i),String.valueOf(pBDefinicion.getObjetoTabla((i), 5)),String.valueOf(pBDefinicion.getObjetoTabla((i),2 )));
			this.setPosicionInicioCampoClave(valorComenzarLeer(i));
			this.setTamañoCampoClave(String.valueOf(pBDefinicion.getObjetoTabla((i), 2)));
			
		//}
	}
	
	/**
	 * Devuelve una lista de enteros con los camps clave
     *
	 * @return
	 */
	private String[] valoresCampoClave(){
    	int [] campos;
    	String str[];
		
		str = (pBDefinicion.getCamposClave()).split("-");
		//campos = new int [Integer.valueOf(str.length)];
		//for(int i=0;i<str.length ;i++){
		//	campos[i]=Integer.valueOf((str[i]));
		//}
		return str;
    }
    
    /**
     * Devuelve el valor en que debo empezar a leer los datos del campo principal
     *
     * @param fila
     * @return
     */
    private String valorComenzarLeer(int fila){
    	int cont=0;
    	
   		for(int i=0; i<fila;i++){
   			cont = cont + Integer.valueOf(String.valueOf(pBDefinicion.getObjetoTabla(i,2)));
   		}
    	cont=cont/8;
   		return String.valueOf(cont);
    }
    
    /**
     * Devuelve el valor esperado para el campo clave
     *
     * @param fila
     * @return
     */
    private String valorCampoClave(int fila){
    	return String.valueOf(pBDefinicion.getObjetoTabla((fila),3));
    }
    
    /**
     * Convierte la cadena a su valor alfanumerico.
     * @param cadena
     * @param tamaño
     * @return
     */
    private String convertirAlfanumerico(String cadena,String tamaño) {
		String binario="",aux,cero="0";
		
		for(char letra: cadena.toCharArray()){
			aux=String.format(Integer.toBinaryString(letra));
			while(aux.length()<8){
				aux=cero.concat(aux);
			}
			binario+=aux;
			
		}
		int tam=Integer.valueOf(tamaño);
		while(binario.length()!=tam ){
			binario=cero.concat(binario);
		}
		return binario;
	}

    /**
     * Convierte el valor definido a su valor binario en funcion del tipo de dato
	 *
	 * @param cadena
     * @param tipo
     * @param tamaño
     * @return
     */
	private String convertirBinario(String cadena,String tipo,String tamaño){
		if(tipo.equals("Booleano")){
			return convertirBooleano(cadena);
		}
		if(tipo.equals("Numerico")){
			return convertirNumerico(cadena,tamaño);
		}
		if(tipo.equals("Alfanumerico")){
			return convertirAlfanumerico(cadena,tamaño);
		}
		return null;
	}
	
	/**
	 * Convierte la cadena a su representacion numerica.
	 * @param cadena
	 * @param tamaño
	 * @return
	 */
	private String convertirNumerico(String cadena,String tamaño) {
		String binario,cero ="0";
		int tam=Integer.valueOf(tamaño);
		binario=String.format(Integer.toBinaryString(Integer.valueOf(cadena)));
		while(binario.length()!=tam ){
			binario=cero.concat(binario);
		}
		return binario;
	}

	/**
	 * Convierte la cadena a su represenatcion en boleano.
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
	
	/**
	 * Recoge el dato.
	 * @param cadena
	 * @param tipo
	 * @param tamaño
	 * @return
	 */
	private String recogerDato(String cadena,String tipo,String tamaño){
		if(tipo.equals("Booleano")){
			return recogerBooleano(cadena);
		}
		if(tipo.equals("Numerico")){
			return recogerNumerico(cadena,tamaño);
		}
		if(tipo.equals("Alfanumerico")){
			return recogerAlfanumerico(cadena,tamaño);
		}
		return null;
	}
	
	/**
	 * Recoge el valor booleano.
	 * @param cadena
	 * @return
	 */
	private String recogerBooleano(String cadena){
		if(cadena.equals("verdadero") || cadena.equals("true")){
			return "1";
		}else{
			return "0";
		}
	}
	
	/**
	 * Recoge el valor numerico.
	 * @param cadena
	 * @param tamaño
	 * @return
	 */
	private String recogerNumerico(String cadena,String tamaño) {
		String binario;
		String aux ="";
		char[] cad= cadena.toCharArray();
		int tam=(Integer.valueOf(tamaño)/8);
		for(int i=0;i <tam;i++){
			aux+=cad[i];
		}
		return aux;
	}
	
	/**
	 * Recoge el valor alfanumerico.
	 * @param cadena
	 * @param tamaño
	 * @return
	 */
	 private String recogerAlfanumerico(String cadena,String tamaño) {
		String binario="",aux = "",cero="0";
		
		for(char letra: cadena.toCharArray()){
			aux+=letra;
				
		}
		int tam=(Integer.valueOf(tamaño)/8);
		while(aux.length()<tam ){
			aux=cero.concat("0");
		}
		return aux;
	}

}