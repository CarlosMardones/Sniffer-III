package servicioAccesoDatos;

import java.io.*;

/**
 * Fábrica de creación de una fachada de acceso al contenido de los diferentes
 * ficheros de la aplicación.
 * 
 * @author Leonardo García & Jose Ramón Gutierrez
 * @version 2.0
 * 
 * @see FabricaAccesoDatosIF
 * @see FachadaFichero
 * @see FachadaFicheroDirectorios
 */
public class FabricaAccesoDatos extends FabricaAccesoDatosIF {

	//Atributos
	/** * Instancia de esta clase. */
	private static FabricaAccesoDatos instancia;

	// Metodo de creacion

	/**
	 * Crea y devuelve una fachada de acceso a los diferentes ficheros.
	 * <p>
	 * Tipos de ficheros que se pueden crear:"directorios"
	 * 
	 * @param tipof
	 *            tipo de fichero
	 * @param nombreRutaF
	 *            nombre del fichero
	 * @return fachada de un fichero.
	 * @see FachadaFicheroDirectorios
	 */
	public FachadaFichero creaFachadaFichero(String tipof, String nombreRutaF) {
		if (tipof.equals("directorios")) {
			try {
				return new FachadaFicheroDirectorios(nombreRutaF);
			} catch (IOException e) {
				System.err.println(e.getMessage());
			}
		}
		if(tipof.equals("exportacion")){
			try{
				return new FachadaFicheroExportacion(nombreRutaF);
			}
			catch(IOException e){
				System.err.println(e.getMessage());
			}
		}
		return null;
	} // FachadaTablaFotografia

	// Metodo de instanciacion
	/**
	 * Devuelve una única instancia de esta clase.
	 * 
	 * @return instancia de tipo FabricaAccesoDatos.
	 */
	public static FabricaAccesoDatos getInstancia() {
		if (instancia == null) {
			instancia = new FabricaAccesoDatos();
		}
		return instancia;
	} // getInstancia

} //clase FabricaAccess
