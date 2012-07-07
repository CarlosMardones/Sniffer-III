package dominio.preferences.definicion;

import presentacion.preferencias.PreferenciasDefinicion;
import presentacion.Mediador;

/**
 * Clase que comprueba los valores introducidos por el usuario y determina si son validos
 * para definir la estructura del protocolo o por si lo contrario necesitan ser modificados 
 * o falta algun valor necesario.
 *
 * @author Carlos Mardones Muga
 * @version 1.0
 * 
 */

public class PreferencesCheckDefinicion {
	
	Object valores [][];
	Mediador med = new Mediador();
	
	/**
	 * Constructor de la Clase.
	 * 	1 comprobacion de campos como nombre rfc numCampos ...
	 * 	2 comprobacion si las columnas estan completamente rellenadas
	 * 	3 comprobacion si el tamaño de los campos coincide con 32
	 * 	4 comprobacion si los valores metidos coinciden con el tipo de dato definido 
	 */
	public PreferencesCheckDefinicion(){
		valores = new Object [PreferenciasDefinicion.getNumCamposProtocolo()][5];
		boolean valido = true;
		
		valores=PreferenciasDefinicion.getTabla();
		//Compruebo cada funcion y si una falla no pasa a la siguiente
		valido = checkCamposDefinicion();
		if(valido){
		valido=checkColumnasRellenas();
		}if(valido){
			valido=checkTamañoCampos();
		}if(valido){
			valido=checkValoresBoolean();
		}if(valido){	valido=checkIsNumeric();		
		}if(valido){
			valido = checkCamposClave();
		}if(valido){
			valido = checkCamposOpcionales();
		}
	}
	
	/**
	 * Comprueba si los campos de definicion del protocolo existen
	 * @return
	 */
	private boolean checkCamposDefinicion(){
		String mensaje="Faltan campos por rellenar";
		
		if((PreferenciasDefinicion.getNombreProtocolo()).equals("") == true ){
			med.lanzaraviso(mensaje,"Error");
			return false;
		}
		if((PreferenciasDefinicion.getRFCProtocolo()).equals("") == true ){
			med.lanzaraviso(mensaje,"Error");
			return false;
		}
		if((PreferenciasDefinicion.getCamposClave()).equals("") == true ){
			med.lanzaraviso(mensaje,"Error");
			return false;
		}
		if((PreferenciasDefinicion.getNumCamposProtocolo()) == 0 ){
			med.lanzaraviso(mensaje,"Error");
			return false;
		}
		if((PreferenciasDefinicion.getNivelProtocolo()) == 0 ){
			med.lanzaraviso(mensaje,"Error");
			return false;
		}
		return true;
	}
  
	/**
	 * Comprueba si las columnas de nombre de campo, tamaño de campo y tipo de dato estan
	 * completamente rellenadas.
	 * 
	 * @return
	 */
	private boolean checkColumnasRellenas(){
		boolean ok=true;
		int j,i;
		String mensaje="Las Columnas de Nombre de Campo, Tamaño y tipo de Dato deben estar completamente rellenadas";
		
		for(j=1;j<=5 && ok == true;j++){
			for(i=0;i<valores.length && ok == true;i++){
				if(valores[i][j] != null && String.valueOf(valores[i][j]) != ""){
					ok = true;
				}else{
					ok=false;
				}
			}
			if( j==2) j=j+2;
		}
		if(ok == false){
			med.lanzaraviso(mensaje,"Error");
		}
		return ok;
	}
	
	/**
	 * Comprueba si para valores definidos como Boolean se introducen valores por defecto Boolean
	 * 
	 * @return
	 */
	private boolean checkValoresBoolean(){
		boolean ok;
		int i;
		ok=true;
		String mensaje="Valores validos: TRUE, VERDADERO,FALSE O FALSO";
		for(i=0;i<valores.length && ok == true;i++){
			if(valores[i][3] == null || (String.valueOf(valores[i][3])).equals("")){
				ok=true;
			}else{ 
					if(String.valueOf(valores[i][5]).equals("Booleano")){
							if((String.valueOf(valores[i][3]).toLowerCase()).equals("false") || (String.valueOf(valores[i][3]).toLowerCase()).equals("falso")
								|| (String.valueOf(valores[i][3]).toLowerCase()).equals("true")	|| (String.valueOf(valores[i][3]).toLowerCase()).equals("verdadero")){
									ok = true;
							}else{
								ok = false;
								med.lanzaraviso(mensaje,"Error");
							}
					}
			}
		}
		return ok;
	}
	
	/**
	 * Comprueba si el tamaño de los valores de los campos cumplen las condiciones establecidas
	 *
	 * @return
	 */
	private boolean checkTamañoCampos(){
		int j=2;
		String mensaje=("Tamaño de campos Incorrecto a partir del Campo: ");
		int sum=0,sum2=0;
		int resto=0;
		boolean ok=true;
		int i;
		for(i=0;i<valores.length && ok==true;i++){
			if(sum == 0){
				sum=Integer.parseInt(String.valueOf(valores[i][j]));
				if(sum == 32){
					sum=0;
				}else{
					resto = 32-sum;
					ok=true;
				}
			}else{
				sum2=Integer.parseInt(String.valueOf(valores[i][j]));
				if(resto == sum2){
					sum = 0;
					resto = 0;
				}else if (resto > sum2){
					resto=resto-sum2;
					sum = sum +sum2;
				}else{
					ok = false;
					mensaje+=String.valueOf(i+1);
					med.lanzaraviso(mensaje,"Error");
				}	
			}
		}
		if(resto != 0 && ok == true){
			mensaje="Recuerda el tamaño de los campos en su totalidad debe ser multiplo de 32";
			med.lanzaraviso(mensaje,"Error");
		}
		return ok;
	}
	
	/**
	 * Comprueba si los campos clave son validos
	 * 
	 * @return
	 */
	private boolean checkCamposClave(){
		String claves = PreferenciasDefinicion.getCamposClave();
		String str[],mensaje;
		boolean ok = true;
		
		str = claves.split("-");
		
		//recorro los elemenntos y los valores definidos deben ser numeros y con un valor igual
		// o menor que el numero de campos que se defina
		for(int i=0;i<str.length && ok ;i++){
			ok = isNumeric(str[i]);
			if(ok){
				ok = camposValidos(str[i]);
			}
		}
		if(!ok){
			mensaje="Especifica bien los Campos Clave";
			med.lanzaraviso(mensaje,"Error");	
		}
		return ok;
	}
	
	/**
	 * Comprueba si los campos numericos son numericos 
	 */
	private boolean checkNumericos(String valor){
		String valores[],mensaje;
		int i;
		if(valor.indexOf("-") > 0 ){
			valores = valor.split("-");
			if(valores.length != 2){
				mensaje="Especificacion de Rango incorrecta Ej: 12-27";
				med.lanzaraviso(mensaje,"Error");	
			}else{
				if(isNumeric(valores[0]) && isNumeric(valores[1]) && (Integer.parseInt(valores[0])< Integer.parseInt(valores[1]))){
					return true;
				}
			}
		}else{
			if(valor.indexOf(",") > 0){
				valores = valor.split(",");
				for(i=0;i<valores.length;i++){
					if(!isNumeric(valores[i])){
						return false;
					}
				}
				return true;
			}else if(isNumeric(valor)){
				return true;
			}
		}
		return false;
	}
	
	/**
	 * Comprueba si una cadena unicamente es un numero
	 * 
	 * @param cadena
	 * @return
	 */
	private boolean isNumeric(String cadena){
		try {
			Integer.parseInt(cadena);
			return true;
		} catch (NumberFormatException nfe){
			return false;	
		}
	}
	
	/**
	 * Comprueba si los campos Clave estan dentro de los posibles campos
	 * 
	 * @param campo
	 * @return
	 */
	private boolean camposValidos(String campo){
		if(Integer.parseInt(campo)> 0 && Integer.parseInt(campo) <= PreferenciasDefinicion.getNumCamposProtocolo() ){
			return true;
		}else{
			return false;
		}
	}
	
	/**
	 * Comprueba si cuando se define un campo de tipo numeric los valores unicamente sean numeros
	 * 
	 * @return
	 */
	private boolean checkIsNumeric(){
		boolean ok;
		int i;
		ok=true;
		String mensaje="Para tipos de datos Numericos no son validos los caracteres";
		for(i=0;i<valores.length && ok == true;i++){
			if(valores[i][3] == null || (String.valueOf(valores[i][3])).equals("")){
				ok=true;
			}else{
				if(String.valueOf(valores[i][5]).equals("Numerico")){
					if(checkNumericos(String.valueOf(valores[i][3]))){
						ok=true;
					}else{
						ok = false;
						med.lanzaraviso(mensaje,"Error");
					}
				}
			}
		}
		return ok;
	}
	
	/**
	 * Comprueba que el valor de los campos en biario coincide con el tamaño definido
	 *
	 * @return
	 */
	private boolean checkValorBinario(){
		String mensaje;
		boolean ok=true;
		int i;
		for(i=0;i<PreferenciasDefinicion.getNumCamposProtocolo() && ok==true;i++){
			if(PreferenciasDefinicion.getValorTabla(i, 5).equals("Booleano")){
				if(PreferenciasDefinicion.getValorTabla(i, 4).equals("1") == false){
					ok=false;
				}
			}
			if(PreferenciasDefinicion.getValorTabla(i, 4).equals("Numerico")){
				
			}
			if(PreferenciasDefinicion.getValorTabla(i, 4).equals("Alfanumerico")){
				
			}
		}
		if( ok == false){
			mensaje="Numero de bits incorrecto para el tipo de dato";
			med.lanzaraviso(mensaje,"Error");
		}
		return ok;
	}
	
	/**
	 * Comprueba que los campos clave no sean opcionales
	 *
	 * @return
	 */
	private boolean checkCamposOpcionales(){
		String mensaje=("Campos Claves Opcionales");
		String claves = PreferenciasDefinicion.getCamposClave();
		String str[];
		boolean ok = true;
		
		str = claves.split("-");
		//compruebo que para cada campo clave no esta determinado como opcional
		for(int i=0;i<str.length && ok ;i++){
			if(PreferenciasDefinicion.getValorTabla((Integer.valueOf(str[i])-1),6).equals("Si")){
				ok = false;
			}
		}
		if(!ok){
			mensaje="Especifica bien los Campos Clave";
			med.lanzaraviso(mensaje,"Error");	
		}
		return ok;
	}
}
	

