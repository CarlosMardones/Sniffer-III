package dominio.pcapDumper.analyzer;

import dominio.preferences.identificacion.PrefIdentificacion;
import jpcap.packet.*;
/**
 * Paquetes definidos mediante la aplicacion·
 * 
 * Analiza los paquetes de cualquier tipo definidos por la aplicacion
 * 
 * @author Carlos Mardones Muga
 * @version 1.0
 * 
 * @see JDPacketAnalyzer
 * @see jpcap.packet.Packet
 */
public class OtroAnalyzer {
	static int tamaño=0;
	static String nombre;
	static String [][] values;
	/** Tipo. */
	public int layer=DATALINK_LAYER;
	/** Tipo DATALINK. */
	public static int DATALINK_LAYER=0;
	/** Tipo NETWORK. */
	public static int NETWORK_LAYER=1;
	/** Tipo TRANSPORT. */
	public static int TRANSPORT_LAYER=2;
	/** Tipo APPLICATION. */
	public static int APPLICATION_LAYER=3;

	
	/**
	 * Constructor por defecto.
	 */
	public OtroAnalyzer(){
		layer=APPLICATION_LAYER;
	}
		
	/**
	 * Devuelve el nombre del protocolo.
	 * 
	 * @return nombre.
	 */
	public String getProtocolName(){
		if(nombre == null){
			return "DESCONOCIDO";
		}else{
			return nombre;
		}
	}

	/**
	 * 
	 * Determina si es analizable.
	 *
	 * @param packet
	 * @return
	 */
	public boolean isAnalyzable(Packet packet){
		if(packet.toString().length()>0){
			return true;
		}
		return false;
	}
			
	
	/**
	 * Devuelve el vector con los nombres de los elementos.
	 * 
	 * @return nombre
	 */
	public String[][] getValores(){
		return values;
	}
	
	
	/**
	 * Identifica los protocolos empleados para la definicion de los datos de la "data" del paquete.
	 * @param arr
	 * @param path
	 * @param pref
	 */
	public static void analizar(byte[]  arr,String path,PrefIdentificacion pref){
		String s,cad="";
		String prot="";
		
		//cad=dataCaracter(arr);
		nombre=pref.getIdentificacionProtocolo(arr);
		values=pref.getCamposProtocoloIdentificado(nombre, arr);
		pref.getTamaño();
	}
	
	/**
	 * 
	 * Convierte los data de su vaslor ascii a char.
	 *
	 * @param arr
	 * @return
	 */
	private static String dataCaracter(byte[]  arr){
		String s,cad="";
		
		for(int i=0;i<arr.length;i++){
			cad+=(char)arr[i];
		}
		return cad;
	}
	
	
	/**
	 * Convierte los data del paquete en su valor binario.
	 * @param arr
	 * @return
	 */
	private static String dataBinario(byte[]  arr){
		String s,cad="";
		
		for(int i=0;i<arr.length;i++){
			if(arr[i]==48){
				cad+="0";
			}if(arr[i]==49){
				cad+="1";
			}
		}
		return cad;
	}
}
