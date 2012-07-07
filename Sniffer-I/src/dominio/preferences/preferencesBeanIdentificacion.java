package dominio.preferences;

import java.io.Serializable;
import java.io.File;
import java.util.*;

import presentacion.preferencias.PreferenciasDefinicion;

/**
 * Java Bean que contiene la informaciòn
 * necesaria para hacer la identificacion de un protocolo
 * 
 * @author Carlos Mardones Muga
 * @version 1.0
 * 
 */
public class preferencesBeanIdentificacion implements Serializable {
	
	private String nomProtocolo;
	private String[][] identificadores = new String [20][4];
	private String nivel;
	private String ruta;
	
	/**
	 * Contructor por defecto .
	 */
	public preferencesBeanIdentificacion() {
		setDefaultSettings();
	}

	/**
	 * Establece los valores por defecto.
	 */
	public void setDefaultSettings(){
		this.nomProtocolo="";
		
	}
	
	/**
	 * Establece el nombre del protocolo.
	 *
	 * @param nombre
	 */
	public void setNomProtocolo(String nombre){
		this.nomProtocolo=nombre;
	}
	
	/**
	 * Devuelve el nombre del protocolo.
	 *
	 * @return
	 */
	public String getNomProtocolo(){
		return this.nomProtocolo;
	}
	
	/**
	 * Devuelve el nivel del protocolo.
	 *
	 * @return
	 */
	public String getNivelProtocolo(){
		return this.nivel;
	}
	
	/**
	 * Establece el nivel del protocolo.
	 *
	 * @param niv
	 */
	public void setNivelProtocolo(String niv){
		 this.nivel=niv;
	}
	
	/**
	 * Devuelve la ruta de la definicion del protocolo.
	 *
	 * @return
	 */
	public String getRutaProtocolo(){
		return this.ruta;
	}
	
	/**
	 * Establece la ruta de la definicion del protocolo.
	 *
	 * @param rut
	 */
	public void setRutaProtocolo(String rut){
		 this.ruta=rut;
	}
	
	/**
	 * Devuelve el valor especcificado mediante los indices.
     *
	 * @param fila
	 * @param columna
	 * @return
	 */
	public String getIdentificador(int fila,int columna){
		return identificadores[fila][columna];
	}
	
	/**
	 * Establece el valor definido en la posicion indicada
     * 
	 * @param fila
	 * @param columna
	 * @param aux
	 */
	public void setIdentificador(int fila,int columna,String aux){
		this.identificadores[fila][columna]=aux;
	}
	
	/**
	 * Devuelve la tabla de identificadores
	 * 
	 * @return
	 */
	public String[][] getIdentificadores(){
		return this.identificadores;
	}
}