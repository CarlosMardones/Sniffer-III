package dominio.export.script;

/**
 *  Fachada de acceso para la creación de un Script de tipo .sh
 * 
 * @author Leonardo García & JoseRamón Gutierrez
 * @version 2.0
 * 
 * @see FachadaGenerarScript
 */
public class GenerarSh extends FachadaGenerarScript {

	/**
	 * Constructor de la clase GenerarBat
	 */
	public GenerarSh() { 
		super();
	}
	
	/**
	 * Establece los parametros de paso para el ejecutable .jar
	 * 
	 * @param tipo
	 *          tipo de parámetro
	 * @param preferencias
	 * 			fichero de preferencias
	 */
	public void setParam(String tipo, String preferencias){
		String aux;
		aux = getTipoCommand(tipo) + " " + preferencias + "";
		this.param = aux;
	}
	
	/**
	 * Crea la cabezera del .sh

	 */
	public void writeToDiskCabecera() {
		try{
			buffer.println("#!/bin/bash");
			buffer.println("# *");
			buffer.println("# LEONARDO GARCIA Y JOSE RAMON GUTIERREZ");
			buffer.println("# Fichero creado por Sniffer");
			buffer.println("# *");
			buffer.println("# *");
			buffer.println("# Fichero de captura automatica en modo comando");
			buffer.println("# *");
			buffer.println("");
		}catch (Exception e){//Catch exception if any
			System.err.println("Error: " + e.getMessage());
		}
	}
	
	/**
	 * Crea la variables del .sh
	 */
	public void writeToDiskVariables() {
		try{
			buffer.println("ruta=\"" + this.getRuta() + "\"");
			buffer.println("ejecutable=" + this.getEjecutable());
			buffer.println("");
			buffer.println("param=" + "\"" + this.getParam() +"\"");
			buffer.println("mvm=" + this.getMvm());
			buffer.println("");
			buffer.println("cd $ruta");
			buffer.println("#set PATH=.;%PATH%");
			buffer.println("");
			buffer.println("if [[ $mvm = \"0\" ]]");
			buffer.println("then");
			buffer.println(" java=java");
			buffer.println("else");
			buffer.println(" java=\"java -Xmx\"$mvm\"m\"");
			buffer.println("fi");
			buffer.println("");
		}catch (Exception e){//Catch exception if any
			System.err.println("Error: " + e.getMessage());
		}
	}
	/**
	 * Crea la cabecera a visualizar del .sh
	 */
	public void writeToDiskCabeceraVisible() {
		try{
			buffer.println("echo \"\"");
			buffer.println("echo \"           ###########################################################\"");
			buffer.println("echo \"           #                Proyecto Fin de Carrera                  #\"");
			buffer.println("echo \"           #                      SNIFFER I                          #\"");
			buffer.println("echo \"           #                                                         #\"");
			buffer.println("echo \"           #         Leonardo Garcia  - Jose Ramon Gutierrez         #\"");
			buffer.println("echo \"           #                                                         #\"");
			buffer.println("echo \"           ###########################################################\"");
			buffer.println("echo \"\"");
		}catch (Exception e){//Catch exception if any
			System.err.println("Error: " + e.getMessage());
		}
	}
	
	/**
	 * 	 * Crea el ejecutable dentro del .sh
	 */
	public void writeToDiskEjecutable() {
		try{
			buffer.println("");
			buffer.println("$java -jar $ejecutable $param");
			buffer.println("");
		}catch (Exception e){//Catch exception if any
			System.err.println("Error: " + e.getMessage());
		}
	}

} // fin de clase
