package dominio.pcap.rules;

/**
 * Clase ClassificationRules.
 * 
 * Sinopsis: Clase tipo clasificaci�n que contendr� el tipo de regla y su
 * prioridad (clasification.config)
 * 
 * @author Leonardo Garc�a & JoseRam�n Gutierrez
 * @version 2.0
 *  
 */

public class ClassificationRules {

	// Descripcion corta de la regla.
	private String Descripcion_corta;

	// Descripci�n larga de la regla.
	private String Descripcion_larga;

	// Prioridad
	private String Prioridad;

	/**
	 * Constructor de la clase Classification Rules
	 *  
	 */
	public ClassificationRules() {

	}

	/**
	 * setDescripcion_corta
	 * 
	 * Funci�n que establece la descripcion corta de la regla
	 * 
	 * @param Descripcion_corta
	 */
	public void setDescripcion_corta(String Descripcion_corta) {
		this.Descripcion_corta = Descripcion_corta;
	}

	/**
	 * setDescripcion_larga
	 * 
	 * Funci�n que establece la descripcion larga de la regla
	 * 
	 * @param Descripcion_larga
	 */
	public void setDescripcion_larga(String Descripcion_larga) {
		this.Descripcion_larga = Descripcion_corta;
	}

	/**
	 * setDescripcion_Prioridad
	 * 
	 * Funci�n que establece la prioridad de la regla
	 * 
	 * @param Prioridad
	 */
	public void setDescripcion_Prioridad(String Prioridad) {
		this.Prioridad = Prioridad;
	}

	/**
	 * getDescripcion_corta
	 * 
	 * Funci�n que devuelve la descripcion corta de la regla
	 *  
	 */
	public String getDescripcion_corta() {
		return Descripcion_corta;
	}

	/**
	 * getDescripcion_larga
	 * 
	 * Funci�n que devuelve la descripcion corta la regla
	 *  
	 */
	public String getDescripcion_larga() {
		return Descripcion_larga;

		/**
		 * getPrioridad
		 * 
		 * Funci�n que devuelve la prioridad de la regla
		 * 
		 * @param prioridad
		 */

	}

	public String getPrioridad() {
		return Prioridad;
	}

}

