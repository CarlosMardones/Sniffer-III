package dominio.preferences;

import java.io.Serializable;

import presentacion.preferencias.PreferenciasDefinicion;

/**
 * Java Bean que contiene la informaciòn
 * necesaria para hacer la definicion de un protocolo
 * 
 * @author Carlos Mardones Muga
 * @version 1.0
 * 
 */
public class preferencesBeanDefinicion implements Serializable {
	/*Numero de campos definidos para el protocolo*/
	private int numCampos;
	private int numColumnas=8;
	private String nomProtocolo;
	private String RFC;
	private String camposClave;
	private int nivel;
	private Object[][] valores ;
	
	/**
	 * Contructor por defecto 
	 */
	public preferencesBeanDefinicion() {
		setDefaultSettings();
	}
	/**
	 * 
	 * Establece propiedades por defecto.
	 */
	public void setDefaultSettings() {
		/*sin valores para que se cree el fichero .xml vacio*/
		this.numCampos = 0;
		this.numColumnas=8;
		this.nomProtocolo = "";
		this.RFC="";
		this.camposClave="";
		this.nivel=0;
		// supongo q al ser 0 no tiene sentido valores=new Object[numCampos][numColumnas];
	}
    
	/**
	 * Devuelve el numero de campos del protocolo
     *
	 * @return
	 */
	public int getNumCampos(){
		return numCampos;
	}
	
	/**
	 *
     * Devuelve el numero de columnas de la tabla
     *
	 * @return
	 */
	public int getNumColumnas(){
		return numColumnas;
	}
	
	/**
     * Establece el numero de campos del protocolo
     *
	 * @param aux
	 */
	public void setNumCampos(int aux){
		this.numCampos=aux;
	}
	
	/**
	 * Establece el nivel del protocolo
     *
	 * @param aux
	 */
	public void setNivel(int aux){
		this.nivel=aux;
	}
	
	/**
	 * 
     * Devuelve el nombre del protocolo
     *
	 * @return
	 */
	public String getNomProtocolo(){
		return nomProtocolo;
	}
	
	/**
	 * Establece el nombre del protocolo
     *
	 * @param aux
	 */
	public void setNomProtocolo(String aux){
		this.nomProtocolo=aux;
	}
	
	/**
	 * Devuelve el RFC del protocolo
     *
	 * @return
	 */
	public String getRFCProtocolo(){
		return RFC;
	}
	
	/**
	 * Devuelve el Nivel del protocolo
	 * 
	 * @return
	 */
	public int getNivelProtocolo(){
		return nivel;
	}
	
	/**
     * Establece el RFC del protocolo
     * 
	 * @param aux
	 */
	public void setRFCProtocolo(String aux){
		this.RFC=aux;
	}
	
	/**
	 * Devuelve los campos clave del protocolo
     *
	 * @return
	 */
	public String getCamposClave(){
		return camposClave;
	}
	
	/**
	 * Establece el RFC del protocolo
     *
	 * @param aux
	 */
	public void setCamposClave(String aux){
		this.camposClave=aux;
	}
	
	/**
	 * Devuelve el valor especcificado mediante los indices
     *
	 * @param fila
	 * @param columna
	 * @return
	 */
	public Object getObjetoTabla(int fila,int columna){
		return valores[fila][columna];
	}
	
	/**
	 * Establece el valor definido en la posicion indicada
     *
	 * @param fila
	 * @param columna
	 * @param aux
	 */
	public void setObjetoTabla(int fila,int columna,Object aux){
		this.valores[fila][columna]=aux;
	}
	
	/**
	 * Establece todos los valores de la Tabla
	 * 
	 * @param tabla con los valores de la definicion
	 */
	public void setTabla(Object [][] tabla){
		valores = new Object[numCampos][numColumnas];
		if( tabla != null){
			valores = tabla;
		}
	}
	
	/**
	 * Devuelve todos los valores de la Tabla
	 * 
	 * @return
	 */
	public Object[][] getTabla(){
		return valores;
	}
	
	/**
	 * Establece el nivel del protocolo.
	 * @param aux
	 */
	public void setNivelProtocolo(int aux){
		this.nivel=aux;
	}
	
	/**
	 * Establece los valores de la definicion del protocolo
	 * @param fichero bool
	 */
	public void setValoresDefinicion(boolean fichero ){
		if(fichero == false){
		  /*Recoje los valores del formulario de definicion de protocolo*/
		  setNumCampos(PreferenciasDefinicion.getNumCamposProtocolo());
		  setNomProtocolo(PreferenciasDefinicion.getNombreProtocolo());
		  setRFCProtocolo(PreferenciasDefinicion.getRFCProtocolo());
		  setCamposClave(PreferenciasDefinicion.getCamposClave());
		  setNivelProtocolo(PreferenciasDefinicion.getNivelProtocolo());
		  setTabla(PreferenciasDefinicion.getTabla());
		}else{
		  /*Recoje los valores del fichero xml que especifa la definicion*/
		}
	}
}