package servicioAccesoDatos;

import java.io.*;

/**
 * Definicion del conjunto de metodos a implementar por una fachada de acceso al
 * contenido de los diferentes ficheros que utiliza la aplicación.
 * 
 * @author Leonardo García & Jose Ramón Gutierrez
 * @version 2.0
 * 
 * @see FabricaAccesoDatos
 * @see FabricaAccesoDatosIF
 */
public abstract class FachadaFichero {
	/** * Ruta del fichero. */
	static String nombreRutaF;
	/** * Escritor de ficheros de caracteres. * * @see java.io.FileWriter */
	public FileWriter writer;
	/** * Buffer de escritura. * * @see java.io.BufferedWriter*/
	public BufferedWriter buffer;
	/** * Escritor de ficheros de salida.*  * @see java.io.PrintWriter */
	public PrintWriter output;

	/**
	 * Fichero.
	 * 
	 * @see java.io.File
	 */
	public File fEtiquetas;

	/**
	 * Crea el fichero pasado a través de la ruta.
	 * 
	 * @param nombreRutaF
	 *            ruta en la que crear el fichero, nombre y extensión
	 * @exception IOException
	 *                excepción de entrada/salida
	 */
	public void creaFichero(String nombreRutaF) throws IOException {
		try {
			fEtiquetas = new File(nombreRutaF);
			//creamos este outputStream para que nos genere fisicamente el
			// fichero pero luego lo cerramos esto quizas
			//lo debería hacer el abrir
			FileOutputStream fosEtiquetas = new FileOutputStream(fEtiquetas);
			//System.out.println("ya he llamado a crear fichero");
		} catch (FileNotFoundException fileException) {
			System.err.println("Error al crear el fichero");
			throw fileException;
		}
	}//creaFichero

	//Apertura de fichero de texto
	/**
	 * Abre un fichero de texto.
	 * 
	 * @param nombreRutaF
	 *            ruta del fichero que tiene que abrirse
	 * @exception IOException
	 *                excepción de entrada/salida
	 */
	public void abrir(String nombreRutaF) throws IOException {
		try {
			writer = new FileWriter(nombreRutaF);
			buffer = new BufferedWriter(writer);
			output = new PrintWriter(writer);
		} catch (SecurityException securityException) {
			System.err.println("No tiene permiso para escribir en el fichero");
			throw securityException;
		} catch (FileNotFoundException fileException) {
			System.err.println("Error al crear el fichero");
			throw fileException;
		}
	}//abrir

	//Cierre del fichero
	/**
	 * Cierra un fichero.
	 * 
	 * @throws IOException
	 *             excepción de entrada/salida
	 */
	//public abstract void cerrar() throws IOException ;
	public void cerrar() throws IOException {
		if (output != null)
			output.close();
	}

	//QUEDAN POR IMPLEMENTAR
	/**
	 * Devuelve el escritor del fichero de salida.
	 * 
	 * @return escritor del fichero
	 * @see java.io.PrintWriter
	 */
	public abstract PrintWriter getOutput();

	/**
	 * Escribe datos en el fichero.
	 * 
	 * @param contenido
	 *            datos a escribir en el fichero
	 */
	public abstract void escribir(String contenido);

	/**
	 * Lee el fichero por lineas.
	 * 
	 * @return array con las diferentes lineas leidas en el fichero
	 */
	public abstract Object[] leer();

	/**
	 * Lee el fichero.
	 * 
	 * @return texto leído en el fichero
	 */
	public abstract String leerString();

} // clase
