package dominio.pcapDumper.analyzer;
import jpcap.packet.*;
import java.util.*;
/**
 * Paquetes IPv6
 * 
 * Analiza los paquetes de tipo IPv6
 * 
 * @author Leonardo García & Jose Ramón Gutierrez
 * @version 2.0
 * 
 * @see JDPacketAnalyzer
 * @see jpcap.packet.Packet
 */
public class IPv6Analyzer extends JDPacketAnalyzer{
	/** * Contiene los elementos. */
	private static final String[] valueNames={
		"Version",
		"Class",
		"Flow Label",
		"Length",
		"Protocol",
		"Hop Limit",
		"Source IP",
		"Destination IP",
		"Source Host Name",
		"Destination Host Name"};
	/** * Valores temporales al analizarlo. */
	Hashtable values=new Hashtable();

	/**
	 * Constructor por defecto.
	 */
	public IPv6Analyzer(){
		layer=NETWORK_LAYER;
	}
	
	/**
	 * Devuelve si el paquete pasado es de tipo IPv6.
	 * 
	 * @param p 
	 * 			paquete.
	 * @return si es del tipo.
	 *  
	 * @see jpcap.packet.Packet
	 */
	public boolean isAnalyzable(Packet p){
		if(p instanceof IPPacket && ((IPPacket)p).version==6) return true;
		else return false;
	}
	
	/**
	 * Devuelve el nombre del protocolo.
	 * 
	 * @return nombre.
	 */
	public String getProtocolName(){
		return "IP v6";
	}

	/**
	 * Devuelve el vector con los nombres de los elementos.
	 * 
	 * @return nombre
	 */
	public String[] getValueNames(){
		return valueNames;
	}

	/**
	 * Analiza el Paquete.
	 */
	public void analyze(Packet packet){
		values.clear();
		if(!isAnalyzable(packet))	return;
		IPPacket ip=(IPPacket)packet;
		values.put(valueNames[0],new Integer(6));
		values.put(valueNames[1],new Integer(ip.priority));
		values.put(valueNames[2],new Integer(ip.flow_label));
		values.put(valueNames[3],new Integer(ip.length));
		values.put(valueNames[4],new Integer(ip.protocol));
		values.put(valueNames[5],new Integer(ip.hop_limit));
		values.put(valueNames[6],ip.src_ip.getHostAddress());
		values.put(valueNames[7],ip.dst_ip.getHostAddress());
		values.put(valueNames[8],ip.src_ip.getHostName());
		values.put(valueNames[9],ip.dst_ip.getHostName());
	}

	/**
	 * Devuelve el valor de elemente segun el nombre pasado.
	 * 
	 * @param valueName
	 * 			nombre de la variable.
	 * @return valor de la variable.
	 */
	public Object getValue(String valueName){
		return values.get(valueName);
	}
	
	/**
	 * Devuelve el valor de elemento segun la posicion en el vector.
	 * 
	 * @param index
	 * 			posición.
	 * @return valor de la variable.
	 */
	Object getValueAt(int index){
		if(index<0 || index>=valueNames.length) return null;
		return values.get(valueNames[index]);
	}
	
	/**
	 *  Devuelve el vector con los valores de todos las variables
	 * 
	 *  @return todas las variables
	 * 
	 */	
	public Object[] getValues(){
		Object[] v=new Object[valueNames.length];
		
		for(int i=0;i<valueNames.length;i++)
			v[i]=values.get(valueNames[i]);
		
		return v;
	}
}
