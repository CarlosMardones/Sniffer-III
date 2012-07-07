package servicioAccesoDatos;

import java.io.*;

/**
 * Estructura de metodos a implementar por una fábrica de creación de una
 * fachada de acceso al contenido de los diferentes ficheros que utiliza la
 * aplicación.
 * 
 * @author Leonardo García & Jose Ramón Gutierrez
 * @version 2.0
 * 
 * @see FabricaAccesoDatos
 * @see FachadaFichero
 * @see FachadaFicheroDirectorios
 * @see FachadaFicheroExportacion
 * @see java.io.Serializable
 */
public abstract class FabricaAccesoDatosIF implements Serializable {

	// Metodo abstractos
	/**
	 * Crea y devuelve una fachada de acceso a los diferentes ficheros.
	 * <p>
	 * Tipos de ficheros que se pueden crear:"Exportacion","directorios".
	 * 
	 * @param tipof
	 *            nombre del tipo de fachada que se quiere obtener.
	 * @param nombreRutaF
	 *            ruta del fichero para el que se quiere crear la fachada
	 * @return fachada de los diferentes ficheros que utiliza la aplicación
	 */
	public abstract FachadaFichero creaFachadaFichero(String tipof,
			String nombreRutaF);
} // clase FabricaAccesoDatosIF
