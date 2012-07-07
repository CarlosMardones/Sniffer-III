package dominio.export.script;

import java.io.*;

/**
 * Definición del conjunto de métodos a implementar por una fachada
 * para la generación de Script
 * 
 * @author Leonardo García & Jose Ramón Gutierrez
 * @version 2.0
 */

public abstract class FachadaGenerarScript {
	/** * Path donde se encuentra el ejecutable del programa */
	private String ruta;
	/** * Nombre del ejecutable */
	private String ejecutable;
	/** * Uso de memoria de la maquina virtual */
	public String mvm;
	/** * Path donde guardamos el .bat generado */
	public String rutaBat;
	/** * paramatres de paso para el .jar */
	public String param;
	/** * Ruta del fichero que contiene los parametros de filtrado.	 */
	public FileOutputStream writer;	
	/** * Buffer de escritura. * * @see java.io.BufferedWriter */
	public PrintStream buffer;
	

	/**
	 * Constructor de la clase
	 */
	public FachadaGenerarScript() {
		setRuta(System.getProperty("user.dir"));
		setEjecutable("sniffer.jar");
		setRutaBat("");
		setMvm("");
		setParam("","");
	}
	
	/**
	 * Genera el Script
	 */
	public void writeToDisk() {
		try{
			writer = new FileOutputStream (this.getRutaBat());				
			buffer = new PrintStream(writer);
			this.writeToDiskCabecera();
			this.writeToDiskVariables();
			this.writeToDiskCabeceraVisible();
			this.writeToDiskEjecutable();
			buffer.close();
			writer.close();
		}catch (Exception e){//Catch exception if any
			System.err.println("Error: " + e.getMessage());
		}
	}

	/** * Clase abstracta para crear la cabezera del script */
	public abstract void writeToDiskCabecera();
	
	/** * Clase abstracta para crear las variables del script */
	public abstract void writeToDiskVariables();
	
	/** * Clase abstracta para crear la cabecera a visualizar del script */
	public abstract void writeToDiskCabeceraVisible();
	
	/** * Clase abstracta para crear el ejecutable dentro del script */
	public abstract void writeToDiskEjecutable();
	
	/**
	 * Establece el path donde se encuentra el ejecutable del programa
	 * 
	 * @param aux
	 *            path
	 */
	private void setRuta(String aux) {
		this.ruta = aux;
	}

	/**
	 * Devuelve el path donde se encuentra el ejecutable del programa
	 * 
	 * @return path del programa
	 */
	public String getRuta() {
		return this.ruta;
	}
	/**
	 * Establece el nombre del fichero ejecutable del programa
	 * 
	 * @param aux
	 *            nombre .jar
	 */
	private void setEjecutable(String aux) {
		this.ejecutable = aux;
	}

	/**
	 * Devuelve el nombre del fichero ejecutable del programa
	 * 
	 * @return nombre .jar
	 */
	public String getEjecutable() {
		return this.ejecutable;
	}
	/**
	 * Establece los megas que se van a utlizar por la máquina virtal java
	 * 
	 * @param aux
	 *            megas Vitual Machine
	 */
	public void setMvm(String aux) {
		this.mvm = aux;
	}

	/**
	 * Devuelve los megas que se van a utlizar por la máquina virtal java
	 * 
	 * @return megas Vitual Machine
	 */
	public String getMvm() {
		String aux;

		if (this.mvm.equals("-1")) aux="0";
		else aux=this.mvm;
		
		return aux;
	}
	/**
	 * Establece el path donde se encuentra el ejecutable del programa .bat
	 * 
	 * @param aux
	 *            path
	 */
	public void setRutaBat(String aux) {
		this.rutaBat = aux;
	}

	/**
	 * Devuelve el path donde se encuentra el ejecutable del programa .bat
	 * 
	 * @return path del programa
	 */
	public String getRutaBat() {
		return this.rutaBat;
	}

	/**
	 * Establece los parametros de paso para el ejecutable .jar
	 * 
	 * @param tipo
	 *          tipo de parámetro
	 * @param preferencias
	 * 			fichero de preferencias
	 */
	public abstract void setParam(String tipo, String preferencias);
	
	/**
	 * Devuelve el path donde se encuentra el ejecutable del programa .bat
	 * 
	 * @return path del programa
	 */
	public String getParam() {

		return this.param;
	}
	
	/**
	 * Devuelve el tipo de commando según el dato pasado por parámetro
	 * 
	 * @return commando
	 */
	public String getTipoCommand(String tipo) {
		String aux;
		aux = "-command ";
		if (tipo.equals("captura")) aux = aux + "-scan";
		if (tipo.equals("exporta")) aux = aux + "-export";
		if (tipo.equals("desde fichero")) aux = aux + "-fromfile";
		return aux;
	}
	
} // fin de clase
