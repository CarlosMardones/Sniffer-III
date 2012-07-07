package servicioAccesoDatos;
import java.io.*;
/**
 * Fachada de acceso al contenido de un fichero con parámetros seleccionados en una exportación.
 * 
 * @author Leonardo García & Jose Ramón Gutierrez
 * @version 2.0
 *  
 * @see FabricaAccesoDatosIF
 * @see FabricaAccesoDatos
 * @see FachadaFichero
 */
public class FachadaFicheroExportacion extends FachadaFichero{
	/** * Ruta del fichero que contiene los parametros de filtrado. */
	static String nombreRutaF;
	/** * Escritor de ficheros de caracteres. * * @see java.io.FileWriter */
	public FileWriter writer;
	/** * Buffer de escritura. * * @see java.io.BufferedWriter */
	public BufferedWriter buffer;
	/**	* Buffer de lectura. * * @see java.io.BufferedReader */
	public BufferedReader in;
	/** * Escritor de ficheros de salida. * * @see java.io.PrintWriter */
	public PrintWriter output;
	
	/**
	 * Genera una fachada de un fichero con parametros de exportación.
	 *
	 * @param nombreRutaF ruta del fichero con parámetros de exportacion
	 * @exception IOException excepcion de entrada/salida
	 */
	public FachadaFicheroExportacion(String nombreRutaF)throws IOException{
		super.nombreRutaF=nombreRutaF;
		this.nombreRutaF=nombreRutaF;
	}//FachadaFicheroFiltrado
	
	/** 
	 * Devuelve el output de salida.
	 *
	 * @return output salida
	 * @see java.io.PrintWriter
	 */
	public PrintWriter getOutput(){
		return output;
	}//getOutput
	
	/**
	 * Devuelve la ruta de este fichero.
	 *
	 * @return ruta absoluta de este fichero
	 */
	public static String getRutaFichero(){
		return nombreRutaF;
	}//getRutaFichero
	
	/**
	 * Devuelve el Writer actual.
	 * 
	 * @return writer actual
	 */
	public PrintWriter getwriter (){
		try{
			if (output == null){
				output = new PrintWriter(new BufferedWriter(new FileWriter(nombreRutaF)));

			}
		} catch (FileNotFoundException ex) {
			ex.printStackTrace();
		} catch (IOException ex) {
			ex.printStackTrace();}
		return output;
	}
	
	/** 
	 * Escribe en el fichero los datos pasados.
	 * 
	 * @param contenido lo que queremos escribir en el fichero de filtrado
	 * @see java.io.IOException
	 */
	public void escribir (String contenido){
		getwriter().println(contenido);
	}//escribir
	
	/**
	 * Cierra el fichero, terminadolo correctamente.
	 */
	public void cerrar(){
		getwriter().flush();
		if (output != null){
			output.close();
		}
	}

	/**
	 * Lee el contenido del fichero de filtrado.
	 *
	 * @return array con cada linea leida en el fichero
	 * @see java.io.IOException
	 */
	public Object[] leer(){
		try{
	 	
	 		BufferedReader inlong =new BufferedReader(new FileReader(nombreRutaF));
	 		String linea;
	 		String linea2;
	 		int nLineas=0;
	 		while ((linea2=inlong.readLine())!=null){
	 			nLineas++;
	 		}
	 		in=new BufferedReader(new FileReader(nombreRutaF));
	 		Object[] lineaXML=new Object[nLineas];
	 		int i=0;
	 		while ((linea=in.readLine())!=null){
	 			lineaXML[i]=linea;
	 			i++;
	 		}
	 		in.close();
	 		return lineaXML;
	 	}
	 	catch(IOException e){
	 		System.out.println("Se ha producido un error al leer");
	 		System.err.println(e.getMessage());
	 		return null;
	 	}	 	
	 }//leer
	
	 /**
	  * Lee el contenido del fichero.
	  *
	  * @return contenido del fichero
	  * @see java.io.IOException
	  */
	 public String leerString(){
		try{
	 		FileInputStream inStream =new FileInputStream (nombreRutaF);
	 		int inBytes= inStream.available();
	 		byte inBuf[]=new byte[inBytes];
	 		int bytesRead =inStream.read(inBuf,0,inBytes);
	 		String resultado=new String(inBuf);
	 		inStream.close();
	 		return resultado;
	 		}
	 	catch (IOException e ){
	 		System.out.println(e.getMessage());
	 		return null;
	 	}
	 }//leerString
	 
	/**
	 * Reinicializa la ruta donde se encuentra el fichero de parámetros de exportación.
	 *
	 * @see #nombreRutaF
	 */
	public static void reinicializar(){
		nombreRutaF=null;
	}//reinicializar
}//Clase FachadaFicheroExportacion