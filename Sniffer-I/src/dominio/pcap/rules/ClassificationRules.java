package dominio.pcap.rules;

/**
 * Clase ClassificationRules.
 * 
 * Sinopsis: Clase tipo clasificación que contendrá el tipo de regla y su
 * prioridad (clasification.config)
 * 
 * @author Leonardo García & JoseRamón Gutierrez
 * @version 2.0
 *  
 */

public class ClassificationRules {

	// Descripcion corta de la regla.
	private String Descripcion_corta;

	// Descripción larga de la regla.
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
	 * Función que establece la descripcion corta de la regla
	 * 
	 * @param Descripcion_corta
	 */
	public void setDescripcion_corta(String Descripcion_corta) {
		this.Descripcion_corta = Descripcion_corta;
	}

	/**
	 * setDescripcion_larga
	 * 
	 * Función que establece la descripcion larga de la regla
	 * 
	 * @param Descripcion_larga
	 */
	public void setDescripcion_larga(String Descripcion_larga) {
		this.Descripcion_larga = Descripcion_corta;
	}

	/**
	 * setDescripcion_Prioridad
	 * 
	 * Función que establece la prioridad de la regla
	 * 
	 * @param Prioridad
	 */
	public void setDescripcion_Prioridad(String Prioridad) {
		this.Prioridad = Prioridad;
	}

	/**
	 * getDescripcion_corta
	 * 
	 * Función que devuelve la descripcion corta de la regla
	 *  
	 */
	public String getDescripcion_corta() {
		return Descripcion_corta;
	}

	/**
	 * getDescripcion_larga
	 * 
	 * Función que devuelve la descripcion corta la regla
	 *  
	 */
	public String getDescripcion_larga() {
		return Descripcion_larga;

		/**
		 * getPrioridad
		 * 
		 * Función que devuelve la prioridad de la regla
		 * 
		 * @param prioridad
		 */

	}

	public String getPrioridad() {
		return Prioridad;
	}

}

