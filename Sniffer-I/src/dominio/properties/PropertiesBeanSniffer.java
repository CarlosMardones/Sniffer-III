package dominio.properties;

import java.io.Serializable;

/**
 * Java Bean que contiene la informaciòn
 * necesaria de las propiedades del programa
 * 
 * @author Leonardo García & Jose Ramón Gutierrez
 * @version 2.0
 * 
 */
public class PropertiesBeanSniffer implements Serializable {
	/** * Posicion X */
	private String WinX;
	/** * Posicion Y */
	private String WinY;
	/** * Alto del programa */
	private String WinHeight;
	/** * Ancho del programa */
	private String WinWidth;
	/** * Posicion de columnas en la tabla */
	private String TableView;

	/**
	 * Constuctor de la clase
	 */
	public PropertiesBeanSniffer() {
		setDefaultSettings();
	}

	/**
	 * Establece propiedades por defecto
	 */
	public void setDefaultSettings() {
		setWinX("0");
		setWinY("0");
		setWinHeight("600");
		setWinWidth("800");
		setTableView("Num,Size,Time,MAC Src,MAC Dest,Frame,Protocol,IP Src,IP Dest,Port Src,Port Dest,SEQ,ACK,Length");
	}

	/**
	 * Devuelve Posicion X
	 * 
	 * @return Posicion X
	 */
	public String getWinX() {
		return WinX;
	}

	/**
	 * Establece el Posicion X
	 * 
	 * @param aux
	 *            Posicion X
	 */
	public void setWinX(String aux) {
		this.WinX = aux;
	}

	/**
	 * Devuelve Posicion Y
	 * 
	 * @return Posicion Y
	 */
	public String getWinY() {
		return WinY;
	}

	/**
	 * Establece el Posicion Y
	 * 
	 * @param aux
	 *            Posicion Y
	 */
	public void setWinY(String aux) {
		this.WinY = aux;
	}
	
	/**
	 * Devuelve Alto del programa
	 * 
	 * @return Alto del programa
	 */
	public String getWinHeight() {
		return WinHeight;
	}

	/**
	 * Establece Alto del programa
	 * 
	 * @param aux
	 *            Alto del programa
	 */
	public void setWinHeight(String aux) {
		this.WinHeight = aux;
	}
	
	/**
	 * Devuelve Ancho del programa
	 * 
	 * @return Ancho del programa
	 */
	public String getWinWidth() {
		return WinWidth;
	}

	/**
	 * Establece Ancho del programa
	 * 
	 * @param aux
	 *            Ancho del programa
	 */
	public void setTableView(String aux) {
		this.TableView = aux;
	}
	
	
	/**
	 * Devuelve Posicion de columnas en la tabla
	 * 
	 * @return Posicion de columnas en la tabla
	 */
	public String getTableView() {
		return TableView;
	}

	/**
	 * Establece Posicion de columnas en la tabla
	 * 
	 * @param aux
	 *            Posicion de columnas en la tabla
	 */
	public void setWinWidth(String aux) {
		this.WinWidth = aux;
	}
	
}