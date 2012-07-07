package servicioAccesoDatos;

import java.io.*;
import java.util.Properties;

/**
 * Fachada de acceso al contenido de un fichero de directorios.
 * 
 * @author Leonardo García & Jose Ramón Gutierrez & Carlos Mardones Muga
 * @version 3.0
 *  
 * @see FabricaAccesoDatosIF
 * @see FabricaAccesoDatos
 * @see FachadaFichero
 */
public class FachadaFicheroDirectorios extends FachadaFichero {
	// Atributos
	/**
	 * Ruta del fichero de directorios.
	 */
	static String nombreRutaF;

	/**
	 * Escritor de ficheros de caracteres.
	 * 
	 * @see java.io.FileWriter
	 */
	public FileWriter writer;

	/**
	 * Buffer de escritura.
	 * 
	 * @see java.io.BufferedWriter
	 */
	public BufferedWriter buffer;

	/**
	 * Buffer de lectura.
	 * 
	 * @see java.io.BufferedReader
	 */
	public BufferedReader in;

	/**
	 * Escritor de ficheros de salida.
	 * 
	 * @see java.io.PrintWriter
	 */
	public PrintWriter output;

	/**
	 * Barra de separacion de ficheros correspondiente a cada sistema operativo
	 * 
	 * @see java.lang.System#getProperty(String)
	 */
	//static String barra = System.getProperty("file.separator");
	static String barra = "/";
	
	/**
	 * Directorios por defecto donde se guardaran y sacaran los datos de la
	 * aplicación.
	 */
	public static String[] dir = {
			new File("." + barra + "files" + barra + "parametrizacion").getAbsolutePath(),
			new File("." + barra + "files" + barra + "capturas").getAbsolutePath(),
			new File("." + barra + "files" + barra + "rules").getAbsolutePath(),
			new File("." + barra + "files" + barra + "exportaciones").getAbsolutePath(), 
			new File("." + barra + "files" + barra + "scripts").getAbsolutePath(),
			new File("." + barra + "files" + barra + "definiciones").getAbsolutePath()
			};
	/**
	 * Nombres de cada directorio donde se guardan y sacan los datos de la
	 * aplicación.
	 */
	public static String[] nomdir = { 
			"DIR_PARAMETRIZACION", 
			"DIR_CAPTURAS",
			"DIR_RULES", 
			"DIR_EXPORTACIONES",
			"DIR_SCRIPTS",
			"DIR_DEFINICIONES"
			};
	/**
	 * Directorios por defecto donde se guardaran y sacaran los datos de la
	 * aplicación.
	 */
	public static String[] dirData = {
			new File("." + barra + "data" + barra + "properties").getAbsolutePath(),
			new File("." + barra + "data" + barra + "images").getAbsolutePath(),
			new File("." + barra + "data" + barra + "help").getAbsolutePath()};

	/**
	 * Nombres de cada directorio donde se guardan y sacan los datos de la
	 * aplicación.
	 */
	public static String[] nomdirData = { 
			"DIR_PROPERTIES",
			"DIR_IMAGES",
			"DIR_HELP"
			};
	
	//Constructor
	/**
	 * Genera una nueva fachada para un fichero que contiene directorios.
	 * 
	 * @param nombreRutaF
	 *            ruta del fichero de directorios
	 * @exception IOException
	 *                excepcion de entrada/salida
	 */
	public FachadaFicheroDirectorios(String nombreRutaF) throws IOException {
		super.nombreRutaF = nombreRutaF;
		this.nombreRutaF = nombreRutaF;
	}

	/**
	 * Devuelve el output de salida.
	 * 
	 * @return output salida
	 * @see java.io.PrintWriter
	 */
	public PrintWriter getOutput() {
		return output;
	}

	/**
	 * Devuelve la ruta de este fichero.
	 * 
	 * @return ruta absoluta de este fichero
	 */
	public static String getRutaFichero() {
		return nombreRutaF;
	}

	/**
	 * Lee el contenido del fichero de directorios y carga los directorios
	 * leidos.
	 * 
	 * @return array con cada linea leida en el fichero
	 * @see java.io.IOException
	 */
	public Object[] leer() {
		String aux1, aux2;
		try {
			BufferedReader inlong = new BufferedReader(new FileReader(
					nombreRutaF));
			String linea;
			String linea2;
			int nLineas = 0;
			while ((linea2 = inlong.readLine()) != null) {
				nLineas++;
			}

			in = new BufferedReader(new FileReader(nombreRutaF));
			Object[] etiAtriFichero = new Object[nLineas];
			int i = 0;
			while ((linea = in.readLine()) != null) {

				etiAtriFichero[i] = linea;
				i++;
			}
			in.close();
/*			dir = new String[etiAtriFichero.length];
			nomdir = new String[dir.length];
*/
			for (int j = 0; j < etiAtriFichero.length; j++) {
				int pos = etiAtriFichero[j].toString().indexOf("=");
				if (pos > -1){
					aux1 = etiAtriFichero[j].toString().substring(0, pos);
					aux2 = etiAtriFichero[j].toString().substring(pos + 1,
							etiAtriFichero[j].toString().length());
					aux2 = aux2.replace("\\\\","\\");
					aux2 = aux2.replace("\\:",":");
					setdirectorio(aux1,aux2.replace("\\\\","\\"));
				}
			}

			return etiAtriFichero;
		} catch (IOException e) {
			System.out.println("Se ha producido un error al leer");
			System.err.println(e.getMessage());
			return null;
		}

	}//leer

	//Este no creo que le utilicemos
	/**
	 * Lee el contenido del fichero.
	 * 
	 * @return contenido del fichero
	 * @see java.io.IOException
	 */
	public String leerString() {
		try {
			FileInputStream inStream = new FileInputStream(nombreRutaF);
			int inBytes = inStream.available();
			byte inBuf[] = new byte[inBytes];
			int bytesRead = inStream.read(inBuf, 0, inBytes);
			String resultado = new String(inBuf);
			inStream.close();
			return resultado;
		} catch (IOException e) {
			System.out.println(e.getMessage());
			return null;
		}

	}//leerString

	/**
	 * Escribe en el fichero los datos pasados.
	 * 
	 * @param contenido
	 *            lo que queremos escribir en el fichero de directorios
	 * @see java.io.IOException
	 */
	public void escribir(String contenido) {
		Properties p = new Properties();
		FileOutputStream file;
		String auxFileName;
		boolean exists=true;
		try {
			file = new FileOutputStream(getRutaFichero());
			for (int i = 0; i < nomdir.length ; i++){
				p.put(nomdir[i].toString(),dir[i].toString());
			}
			p.store(file,"Ficheros de Usuario");
			p.clear();
			file.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}//escribir

	/**
	 * Devuelve el directorio a partir de un tipo de directorio.
	 * <p>
	 * Tipos de directorios:
	 * "DIR_FICHENTRADA","DIR_FICHANALISIS","DIR_FICHPARAMFILT","DIR_FICHRESULTFILT",
	 * "DIR_FICHPARAMREPRE","DIR_FICHGRAFICO","DIR_FICHAPLICACION","DIR_DEFINICIONES".
	 * 
	 * @param tipo
	 *            tipo de directorio del que se quiere obtener su ruta
	 * @return texto con la ruta absoluta del tipo de directorio indicado
	 */
	public static String getdirectorio(String tipo) {
		int pos = -1;
		for (int i = 0; i < nomdir.length; i++) {
			//System.out.println("cont ==> " +  nomdir.length);
			if (nomdir[i].equals(tipo)) {
				pos = i;
			}
		}
		if (pos == -1) {
			return "";
		} else {
			return dir[pos];
		}
	}//getdirectorio
	
	/**
	 * Devuelve el directorio a partir de un tipo de directorio.
	 * <p>
	 * Tipos de directorios:
	 * "DIR_FICHENTRADA","DIR_FICHANALISIS","DIR_FICHPARAMFILT","DIR_FICHRESULTFILT",
	 * "DIR_FICHPARAMREPRE","DIR_FICHGRAFICO","DIR_FICHAPLICACION","DIR_DEFINICIONES".
	 * 
	 * @param tipo
	 *            tipo de directorio del que se quiere obtener su ruta
	 */
	public static void setdirectorio(String tipo, String ruta) {
		int pos = -1;
		for (int i = 0; i < nomdir.length; i++) {
			if (nomdir[i].equals(tipo)) {
				pos = i;
			}
		}
		if (pos == -1) {
			
		} else {
			dir[pos]=ruta;
		}
	}//getdirectorio
	
	/**
	 * Devuelve el directorio a partir de un tipo de directorio.
	 * <p>
	 * Tipos de directorios:
	 * "DIR_FICHENTRADA","DIR_FICHANALISIS","DIR_FICHPARAMFILT","DIR_FICHRESULTFILT",
	 * "DIR_FICHPARAMREPRE","DIR_FICHGRAFICO","DIR_FICHAPLICACION","DIR_DEFINICIONES".
	 * 
	 * @param tipo
	 *            tipo de directorio del que se quiere obtener su ruta
	 * @return texto con la ruta absoluta del tipo de directorio indicado
	 */
	public static String getdirectorioData(String tipo) {
		int pos = -1;
		for (int i = 0; i < nomdirData.length; i++) {
			if (nomdirData[i].equals(tipo)) {
				pos = i;
			}
		}
		if (pos == -1) {
			return "";
		} else {
			return dirData[pos];
		}
	}//getdirectorio
	
	/**
	 * Reinicializa la ruta donde se encuentra el fichero de directorios.
	 * 
	 * @see #nombreRutaF
	 */
	public static void reinicializar() {
		nombreRutaF = null;
	}//reinicializar
}//Clase FachadaFicheroDirectorios
