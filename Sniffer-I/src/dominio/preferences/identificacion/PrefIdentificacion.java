package dominio.preferences.identificacion;

import presentacion.Mediador;
import dominio.preferences.preferencesBeanDefinicion;
import dominio.preferences.preferencesBeanIdentificacion;
import dominio.preferences.preferencesFileRead;
import java.io.File;
import java.util.ArrayList;

/**
 * Clase que define una lista de las claves que identifican unicamente los protocolos definidos 
 * por el usuario.
 *
 * @author Carlos Mardones Muga
 * @version 1.0
 * 
 */

public class PrefIdentificacion {

	/**
	 * Lista con los distinas identificaciones de los protocolos
	 */
	private ArrayList<preferencesBeanIdentificacion> listaIdentificacion = new ArrayList<preferencesBeanIdentificacion>(); 
	
	/**
	 * Clase que contiene la direcion donde se almacenan las definiciones xml
	 * de los protocolos.
	 */
	private File fich;
	
	/**
	 * Numero de bits empleados los protocolos superiores.
	 */
	private int tamaño=0;
	
	/**
	 * Elemento que permite recorrer todos los ficheros de el directorio de definiciones
	 */
	private preferencesFileRead leer = new preferencesFileRead();
	
	public PrefIdentificacion(){
		File todos[];
		fich = new File("./files/definiciones");
		todos=fich.listFiles();
		for(int i=0; i<todos.length;i++){
			//paso por todos los protocolos y los añado a la lista
			listaIdentificacion.add((leer.leerXMLIdentificacion(todos[i])));
		}
	}
	
	/**
	 * Devuelve lista de claves de protocolos
	 */
	public ArrayList<preferencesBeanIdentificacion> getListaIdentificacion(){
		return listaIdentificacion;
	}
	
	/**
	 * Devuelve el tamaño de la lista de claves de protocolos
	 */
	public int getTamañoListaIdentificacion(){
		return listaIdentificacion.size();
	}
	
	/**
	 * Devuelve un elemento de la lista de identificacion
	 */
	public preferencesBeanIdentificacion getElementoListaIdentificacion(int pos){
		return listaIdentificacion.get(pos);
	}
	
	/**
	 * Recibiendo una cadena devuelve el protocolo al que corresponde
	 */
	public String getIdentificacionProtocolo(byte [] cadena){
		String sms="";
		boolean coincide = true;
		//System.out.println("Tamaño: "+cadena.length());
		for(preferencesBeanIdentificacion aux: listaIdentificacion){
			for(int i=0;(aux.getIdentificador(i,0))!=null && coincide;i++){
				coincide=checkContiene(cadena,aux.getIdentificador(i,0),Integer.valueOf(aux.getIdentificador(i,1)),Integer.valueOf(aux.getIdentificador(i,2)),aux.getIdentificador(i,3));
			}
			if(coincide){
				return aux.getRutaProtocolo();
			}
			coincide=true;
		}
		return sms;
	}
	
	/**
	 *
	 * Comprueba que contiene la clave en el lugar esperado.
	 *
	 * @param protocolo
	 * @param valor
	 * @param inicio
	 * @param tamaño
	 * @param tipo
	 * @return
	 */
	private boolean checkContiene(byte[] protocolo,String valor, int inicio,int tamaño,String tipo){
		boolean iguales = true;
		if(protocolo.length != 0){
			if(tipo.equals("Booleano")){
				if(protocolo[inicio]==0){
					return true;
				}
				if(protocolo[inicio]==1){
					return true;
				}
			}
			if(tipo.equals("Alfanumerico")){
				String aux = null;
				for(int i = inicio; i<inicio+tamaño;i++){
					aux+=(char)protocolo[i];
				}
				String [] val = valor.split(",");
				if(val.length != 0){
					for(int i=0;i<val.length;i++){
						if(val[i].equals(aux)){
							return true;
						}
					}
				}
			}
			if(tipo.equals("Numerico")){
				byte [] valAux = new byte [tamaño];
				int j=0;
				for(int i = inicio;i < inicio+tamaño;i++){
					valAux[j]=protocolo[i];
					j++;
				}
			
				int valInt = byteArrayToInt(valAux);
			
				String [] val1 = valor.split(",");
				String [] val2 = valor.split("-");
				if(val1.length != 0 && val2.length==1){
					for(int i=0;i<val1.length;i++){
						if(Integer.valueOf(val1[i]).intValue()==valInt){
							return true;
						}
					}
				}
				
				if(val2.length == 2 && val1.length==1){
					if(Integer.valueOf(val2[0]) > Integer.valueOf(val2[1])){
						String aux = val2[0];
						val2[0]=val2[1];
						val2[1]=aux;
					}
					if(valInt >= Integer.valueOf(val2[0]).intValue() && valInt <= Integer.valueOf(val2[1]).intValue()){
						return true;
					}	
				}
			}
		}
		return false;
	}
	
	/**
	 * Clase que convierte un array de but a su valor entero.
	 * @param b
	 * @return
	 */
	public static final int byteArrayToInt(byte [] b) {
		if(b.length == 1){
			return (b[0] & 0xFF);
		}
		if(b.length == 2){
			return ((b[0] & 0xFF) << 8)
            + (b[1] & 0xFF);
		}
		if(b.length == 3){
			return ((b[0] & 0xFF) << 16)
            + ((b[1] & 0xFF) << 8)
            + (b[2] & 0xFF);
		}
		if(b.length == 4){
			return (b[0] << 24)
            + ((b[1] & 0xFF) << 16)
            + ((b[2] & 0xFF) << 8)
            + (b[3] & 0xFF);
		}
		return 0;
        
}
	
	/**
	 * Recoje los valores de la definicion y carga todos los datos identificados.
	 *
	 * @param file
	 * @param cadena
	 * @return
	 */
	public String [][] getCamposProtocoloIdentificado(String file,byte[] arr){
		int contPos=0;
		String dato;
		//char[] cadenaAux=cadena.toCharArray(); 
		preferencesBeanDefinicion prefAux;
		File aux = new File(file);
		
		prefAux=leer.leerXMLProtocoloIdentificado(aux);
		String [][] datos =new String [prefAux.getNumCampos()][2];
		//con los valores y tamaños del array debo sacar cada campo
		for(int i=0;i<prefAux.getNumCampos();i++){
			datos[i][0]=String.valueOf(prefAux.getObjetoTabla(i, 1));
			datos[i][1]=setValor(String.valueOf(prefAux.getObjetoTabla(i, 5)),contPos,Integer.valueOf(String.valueOf(prefAux.getObjetoTabla(i, 2)))/8,arr);
			//datos[i][1]= getCampoLeido(cadenaAux,(Integer.valueOf(String.valueOf(prefAux.getObjetoTabla(i, 2)))/8),contPos,String.valueOf(prefAux.getObjetoTabla(i, 5)));
			//dato=convertir(datos[i][1],String.valueOf(prefAux.getObjetoTabla(i, 5)));
			//datos[i][1]=dato;
			contPos=contPos+(Integer.valueOf(String.valueOf(prefAux.getObjetoTabla(i, 2)))/8);
			tamaño=tamaño+(Integer.valueOf(String.valueOf(prefAux.getObjetoTabla(i, 2)))/8);
		}
		return datos;
	}
	
	/**
	 * Determina el valor de la cadena de bytes en funcion del tipo de  dato determinado en la definicion con la que coincide.
	 * 
	 * @param tipo
	 * @param inicio
	 * @param tamaño
	 * @param protocolo
	 * @return
	 */
	private static String setValor(String tipo,int inicio,int tamaño,byte [] protocolo){
		if(tipo.equals("Booleano")){
			if(protocolo[inicio]==0){
				return "Verdadero";
			}
			if(protocolo[inicio]==1){
				return "False";
			}
		}
		if(tipo.equals("Alfanumerico")){
			String aux = "";
			for(int i = inicio; i<inicio+tamaño;i++){
				aux+=(char)protocolo[i];
			}
			return aux;
		}
		if(tipo.equals("Numerico")){
			byte [] valAux = new byte [tamaño];
			int j=0;
			for(int i = inicio;i < inicio+tamaño;i++){
				valAux[j]=protocolo[i];
				j++;
			}
			
			int valInt = byteArrayToInt(valAux);
			return String.valueOf(valInt);
		}
		return "";
	}
	
	/**
	 * Devuelve un campo del protocolo
	 * @param tipo 
	 */
	private String getCampoLeido(char[] cadena,int tamaño,int inicio, String  tipo){
		char [] campo= new char[tamaño];
		String auxCampo;
		int j=0;
		if(tipo.equals("Alfanumerico")){
			for(int i=inicio; i<((inicio)+tamaño);i++){
				campo[j]=cadena[i];
				j++;
			}
		}
		if(tipo.equals("Booleano")){
			if(cadena[inicio] == '1'){
				return "true";
			}
			if(cadena[inicio] == '0'){
				return "false";
			}
		}
		if(tipo.equals("Numerico")){
			j=0;
			for(int i=inicio; i<((inicio)+tamaño);i++){
				campo[j]=cadena[i];
				j++;
			}
			
			String codificacion="";
			for(int i=0; i< campo.length;i++){
				String cero = "0";
				codificacion+=Integer.toBinaryString(campo[i]);
				while(codificacion.length() < 8){
					cero+=codificacion;
					codificacion=cero;
					cero="0";
				}
			}
			int pot=0;
			int res=0;
			char aux[]=codificacion.toCharArray();
			for(int i=(aux.length-1); i > 0; i--){
				if(aux[i]=='1'){
					res+=Math.pow(2, pot);
				}
				pot++;
			}
			return String.valueOf(res);
		}		
		return auxCampo = new String(campo);
	}
	
	
	/**
	 * Devuelve el tamaño de los valores encapsulados.
	 *
	 * @return
	 */
	public int getTamaño(){
		return tamaño;
	}
	
}
