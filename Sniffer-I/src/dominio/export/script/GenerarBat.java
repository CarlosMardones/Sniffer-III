package dominio.export.script;

/**
 *  Fachada de acceso para la creaci�n de un Script de tipo .bat
 * 
 * @author Leonardo Garc�a & JoseRam�n Gutierrez
 * @version 2.0
 * 
 * @see FachadaGenerarScript
 */
public class GenerarBat extends FachadaGenerarScript {

	/**
	 * Constructor de la clase GenerarBat
	 * 
	 */
	public GenerarBat() { 
		super();
	}
	
	/**
	 * Establece los parametros de paso para el ejecutable .jar
	 * 
	 * @param tipo
	 *          tipo de par�metro
	 * @param preferencias
	 * 			fichero de preferencias
	 */
	public void setParam(String tipo, String preferencias){
		String aux;
		aux = getTipoCommand(tipo) + " \"" + preferencias + "\"";
		this.param = aux;
	}
	
	/**
	 * Crea la cabezera del .bat
	 */
	public void writeToDiskCabecera() {
		try{
			buffer.println("@echo off");
			buffer.println("rem *");
			buffer.println("REM LEONARDO GARCIA Y JOSE RAMON GUTIERREZ");
			buffer.println("rem Fichero creado por Sniffer");
			buffer.println("rem *");
			buffer.println("rem *");
			buffer.println("rem Fichero de captura automatica en modo comando");
			buffer.println("rem *");
			buffer.println("");
		}catch (Exception e){//Catch exception if any
			System.err.println("Error: " + e.getMessage());
		}
	}
	
	/**
	 * Crea la variables del .bat
	 */
	public void writeToDiskVariables() {
		try{
			buffer.println("set ruta=" + this.getRuta());
			buffer.println("set ejecutable=" + this.getEjecutable());
			buffer.println("");
			buffer.println("set param=" + this.getParam());
			buffer.println("set mvm=" + this.getMvm());
			buffer.println("");
			buffer.println("cd %ruta%");
			buffer.println("set PATH=.;%PATH%");
			buffer.println("");
			buffer.println("set java=java");
			buffer.println("if not '%mvm%' == '0' set java=java -Xmx%mvm%m");
			buffer.println("");
		}catch (Exception e){//Catch exception if any
			System.err.println("Error: " + e.getMessage());
		}
	}
	/**
	 * Crea la cabecera a visualizar del .bat
	 */
	public void writeToDiskCabeceraVisible() {
		try{
			buffer.println("echo.");  
			buffer.println("echo           ���������������������������������������������������������Ŀ");
			buffer.println("echo           �                Proyecto Fin de Carrera                  �");
			buffer.println("echo           �                      SNIFFER I                          �");
			buffer.println("echo           �                                                         �");
			buffer.println("echo           �         Leonardo Garcia  - Jose Ramon Gutierrez         �");
			buffer.println("echo           �                                                         �");
			buffer.println("echo           �����������������������������������������������������������");
			buffer.println("echo.");
		}catch (Exception e){//Catch exception if any
			System.err.println("Error: " + e.getMessage());
		}
	}
	
	/**
	 * Crea el ejecutable dentro del .bat
	 */
	public void writeToDiskEjecutable() {
		try{
			buffer.println("");
			buffer.println("%java% -jar %ejecutable% %param%");
			buffer.println("");
		}catch (Exception e){//Catch exception if any
			System.err.println("Error: " + e.getMessage());
		}
	}

} // fin de clase
