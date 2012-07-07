package dominio.preferences;

import java.io.Serializable;
import java.util.ArrayList;

import presentacion.preferencias.PreferenciasDefinicion;

/**
 * Java Bean que contiene la informaciòn
 * necesaria para hacer la definicion de un protocolo
 * 
 * @author Carlos Mardones Muga
 * @version 1.0
 * 
 */
public class preferencesBeanExportInsercion implements Serializable {
	/*Numero de campos definidos para el protocolo*/
	ArrayList<String> ipOrigen;
	ArrayList<String> ipDestino;
	ArrayList<String> macOrigen;
	ArrayList<String> macDestino;
	
	/**
	 * Contructor por defecto. 
	 */
	public preferencesBeanExportInsercion() {
		setDefaultSettings();
	}
	
	/**
	 * Establece propiedades por defecto.
	 */
	public void setDefaultSettings() {
		ipOrigen=new ArrayList<String>();
		ipDestino=new ArrayList<String>();
		macOrigen=new ArrayList<String>();
		macDestino=new ArrayList<String>();
	}
    
	
	/**
	 * Establece las direcciones ip de origen
	 * @param text
	 */
	public void setIpOrigen(String text) {
		ipOrigen.add(text);
	}
	
	/**
	 * Establece las direcciones ip de destino
	 * @param text
	 */
	public void setIpDestino(String text) {
		ipDestino.add(text);
	}
	
	/**
	 * Establece las direcciones mac de origen
	 * @param text
	 */
	public void setMacOrigen(String text) {
		macOrigen.add(text);
	}
	
	/**
	 * Establece las direcciones mac de destino
	 * @param text
	 */
	public void setMacDestino(String text) {
		macDestino.add(text);	
	}
	
	/**
	 * Devuelve la lista de ip de origen
	 * @return
	 */
	public ArrayList<String> getIpOrigen() {
		return ipOrigen;
	}
	
	/**
	 * Devuelve la lista de ip de destino
	 * @return
	 */
	public ArrayList<String> getIpDestino() {
		return ipDestino;
	}
	
	/**
	 * Devuelve la lista de mac de origen
	 * @return
	 */
	public ArrayList<String> getMacOrigen() {
		return macOrigen;
	}
	
	/**
	 * Devuelve la lista de mac de destino
	 * @return
	 */
	public ArrayList<String> getMacDestino() {
		return macDestino;
	}
}