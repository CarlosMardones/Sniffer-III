package dominio.preferences;

/**
 * Clase que contiene funciones de paso de string to boolean y vicebersa
 * 
 * @author Leonardo García & Jose Ramón Gutierrez
 * @version 2.0
 */
public class preferencesOperation {

	/**
	 * Constructor de la clase
	 */
	public preferencesOperation() {
	}

	/**
	 * Método que retorna un tipo de dato boolean de un dato String Si es "YES"
	 * retorna "true" Si es "NO" retorna "false"
	 * 
	 * @param state
	 *            cadena a convertir
	 * @return el boleano correspondiente, sino es correcto por defecto retorna
	 *         "false"
	 */
	public boolean validate(String state) {
		boolean auxB;
		if (state.toUpperCase().equals("YES"))
			auxB = true;
		else
			auxB = false;
		return auxB;
	}

	/**
	 * Método que retorna un tipo de dato String de un dato boolean Si es "true"
	 * retorna "YES" Si es "false" retorna "NO"
	 * 
	 * @param state
	 *            boolena a convertir
	 * @return la cadena correspondiente
	 */
	public String validate(boolean state) {
		String aux;
		String auxS = String.valueOf(state);
		if (auxS.toUpperCase().equals("TRUE"))
			aux = "Yes";
		else
			aux = "No";
		return aux;
	}
}