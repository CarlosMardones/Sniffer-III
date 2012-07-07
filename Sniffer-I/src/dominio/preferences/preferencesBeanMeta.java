package dominio.preferences;

import java.io.Serializable;

/**
 * Java Bean que contiene la información necesaria para hacer una captura
 * 
 * @author Leonardo García & Jose Ramón Gutierrez
 * @version 2.0
 * 
 */
public class preferencesBeanMeta implements Serializable {
	/** * Modo relativo */
	private boolean metLocRelativeId;
	/** * Fichero de captura relativo */
	private String metLocRelative;
	/** * Modo absoluto */
	private boolean metLocAbsotuteId;
	/** * Ruta de captura absoluta */
	private String metLocAbsotutePath;
	/** * Nombre del fichero de captura */
	private String metLocAbsotuteName;
	/** * Activado multiples ficheros */
	private boolean metMultipleFileId;
	/** * Activado pila de ficheros */
	private boolean metMFRingBufferId;
	/** * Cantidad por fichero */
	private String metMFRingBuffer;
	/** * Primer fichero de captura */
	private String metMFStar;
	/** * Fin de captura */
	private String metMFEnd;

	/**
	 * Constuctor de la clase
	 */
	public preferencesBeanMeta() {
	}

	/**
	 * Establece propiedades por defecto
	 *  
	 */
	public void setDefaultSettings() {
		this.setMetLocAbsotuteId(true);
		this.setMetLocAbsotuteName("");
		this.setMetLocAbsotutePath("");
		this.setMetLocRelative("");
		this.setMetLocRelativeId(false);
		this.setMetMFEnd("1");
		this.setMetMFRingBuffer("0");
		this.setMetMFRingBufferId(false);
		this.setMetMFStar("1");
	}

	/**
	 * Devuelve si el fichero de captura esta en ruta Relativa
	 * 
	 * @return boolean ruta relativa
	 */
	public boolean getMetLocRelativeId() {
		return metLocRelativeId;
	}

	/**
	 * Establece si el fichero de captura esta en ruta Relativa
	 * 
	 * @param aux
	 *            ruta relativa
	 */
	public void setMetLocRelativeId(boolean aux) {
		this.metLocRelativeId = aux;
	}

	/**
	 * Devuelve el nombre del fichero de captura con posicion relativa
	 * 
	 * @return boolean nombre de captura
	 */
	public String getMetLocRelative() {
		return metLocRelative;
	}

	/**
	 * Establece el nombre del fichero de captura con posicion relativa
	 * 
	 * @param aux
	 *            nombre de captura
	 */
	public void setMetLocRelative(String aux) {
		this.metLocRelative = aux;
	}

	/**
	 * Devuelve si el fichero de captura esta en ruta Absoluta
	 * 
	 * @return boolean ruta absoluta
	 */
	public boolean getMetLocAbsotuteId() {
		return metLocAbsotuteId;
	}

	/**
	 * Establece si el fichero de captura esta en ruta Absoluta
	 * 
	 * @param aux
	 *            ruta absoluta
	 */
	public void setMetLocAbsotuteId(boolean aux) {
		this.metLocAbsotuteId = aux;
	}

	/**
	 * Devuelve el path del fichero de captura
	 * 
	 * @return boolean path de captura
	 */
	public String getMetLocAbsotutePath() {
		return metLocAbsotutePath;
	}

	/**
	 * Establece el path del fichero de captura
	 * 
	 * @param aux
	 *            path de captura
	 */
	public void setMetLocAbsotutePath(String aux) {
		this.metLocAbsotutePath = aux;
	}

	/**
	 * Devuelve el nombre del fichero de captura
	 * 
	 * @return boolean nombre de captura
	 */
	public String getMetLocAbsotuteName() {
		return metLocAbsotuteName;
	}

	/**
	 * Establece el nombre del fichero de captura
	 * 
	 * @param aux
	 *            nombre de captura
	 */
	public void setMetLocAbsotuteName(String aux) {
		this.metLocAbsotuteName = aux;
	}

	/**
	 * Devuelve si la captura esta contenida en varios fichero
	 * 
	 * @return boolean varios ficheros
	 */
	public boolean getMetMultipleFileId() {
		return metMultipleFileId;
	}

	/**
	 * Establece la captura esta contenida en varios fichero
	 * 
	 * @param aux
	 *            varios ficheros
	 */
	public void setMetMultipleFileId(boolean aux) {
		this.metMultipleFileId = aux;
	}

	/**
	 * Devuelve si la pila de captura esta activada
	 * 
	 * @return boolean pila de captura
	 */
	public boolean getMetMFRingBufferId() {
		return metMFRingBufferId;
	}

	/**
	 * Establece la pila de captura esta activada
	 * 
	 * @param aux
	 *            pila de captura esta activada
	 */
	public void setMetMFRingBufferId(boolean aux) {
		this.metMFRingBufferId = aux;
	}

	/**
	 * Devuelve el numero de pila de fichero
	 * 
	 * @return string pila de fichero
	 */
	public String getMetMFRingBuffer() {
		return metMFRingBuffer;
	}

	/**
	 * Establece el numero de pila de fichero
	 * 
	 * @param aux
	 *            pila de fichero
	 */
	public void setMetMFRingBuffer(String aux) {
		this.metMFRingBuffer = aux;
	}

	/**
	 * Devuelve el numero del primer fichero multicaptura
	 * 
	 * @return string primer fichero multicaptura
	 */
	public String getMetMFStar() {
		return metMFStar;
	}

	/**
	 * Establece el numero del primer fichero multicaptura
	 * 
	 * @param aux
	 *            primer fichero multicaptura
	 */
	public void setMetMFStar(String aux) {
		this.metMFStar = aux;
	}

	/**
	 * Devuelve el numero del ultimo fichero multicaptura
	 * 
	 * @return string ultimo fichero multicaptura
	 */
	public String getMetMFEnd() {
		return metMFEnd;
	}

	/**
	 * Establece el numero del ultimo fichero multicaptura
	 * 
	 * @param aux
	 *            ultimo fichero multicaptura
	 */
	public void setMetMFEnd(String aux) {
		this.metMFEnd = aux;
	}
}