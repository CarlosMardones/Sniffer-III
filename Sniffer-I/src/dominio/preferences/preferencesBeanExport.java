package dominio.preferences;

import java.io.Serializable;

/**
 * Java Bean que contiene la informaci�n
 * necesaria para hacer una exportaci�n
 * 
 * @author Leonardo Garc�a & Jose Ram�n Gutierrez
 * @version 2.0
 * 
 */
public class preferencesBeanExport implements Serializable {
	/** * Tipo de exportaci�n. */
	private String expType;
	/** * Origen. */
	private String expSource;
	/** * Detino. */
	private String expDestination;
	/** * Multi fichero. */
	private boolean expMultifile;
	/** * Estadisticas. */
	private boolean expStatistic;
	
	/**
	 * Contructor por defecto 
	 */
	public preferencesBeanExport() {
		setDefaultSettings();
	}
	/**
	 * 
	 * Establece propiedades por defecto.
	 */
	public void setDefaultSettings() {
		this.expType = "";
		this.expSource = "";
		this.expDestination = "";
		setExpMultifile(true);
		this.expStatistic = false;
	}
	
	/**
	 * Devuelve el tipo de exportaci�n.
	 * 
	 * @return tipo
	 */
	public String getExpType() {
		return expType;
	}

	/**
	 * Establece el tipo de exportaci�n.
	 * 
	 * @param aux tipo
	 */
	public void setExpType(String aux) {
		this.expType = aux;
	}

	/**
	 * Devuelve el fichero de origen 
	 * 
	 * @return fichero
	 */
	public String getExpSource() {
		return expSource;
	}

	/**
	 * Establve el fichero de origen
	 * 
	 * @param aux fichero
	 */
	public void setExpSource(String aux) {
		this.expSource = aux;
	}

	/**
	 * Devuelve el destino de la exportaci�n.
	 * 
	 * @return fichero
	 */
	public String getExpDestination() {
		return expDestination;
	}

	/**
	 * Establece el destino de la exportaci�n.
	 * 
	 * @param aux fichero
	 */
	public void setExpDestination(String aux) {
		this.expDestination = aux;
	}
	
	/**
	 * Devuelve si esta activada la opcion multifichero.
	 * 
	 * @return estado
	 */
	public boolean getExpMultifile() {
		return expMultifile;
	}

	/**
	 * Establece si est� ativada la opci�n multifichero.
	 * 
	 * @param aux estado
	 */
	public void setExpMultifile(boolean aux) {
		this.expMultifile = aux;
	}

	/**
	 * Estadisticas acitivas, Desarrollo futuro.
	 * 
	 * @return estaditicas 
	 */
	public boolean getExpStatistics() {
		return expStatistic;
	}

	/**
	 * Estadisticas acitivas, Desarrollo futuro.
	 *  
	 * @param aux estad�sticas
	 */
	public void setExpStatistics(boolean aux) {
		this.expStatistic = aux;
	}
}