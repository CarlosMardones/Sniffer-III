package dominio.pcapDumper.analyzer;
//import java.net.InetAddress;
import java.util.Hashtable;
import jpcap.packet.IPPacket;
import jpcap.packet.Packet;
/**
 * Paquetes IPv4
 * 
 * Analiza los paquetes de tipo IPv4
 * 
 * @author Leonardo García & Jose Ramón Gutierrez
 * @version 2.0
 * 
 * @see JDPacketAnalyzer
 * @see jpcap.packet.Packet
 */
public class IPv4Analyzer extends JDPacketAnalyzer{
	/** * Contiene los elementos. */
	private static final String[] valueNames={"Version",
		"TOS: Priority",
		"TOS: Throughput",
		"TOS: Reliability",
		"Length",
		"Identification",
		"Fragment: Don't Fragment",
		"Fragment: More Fragment",
		"Fragment Offset",
		"Time To Live",
		"Protocol",
		"Source IP",
		"Destination IP",
		"Source Host Name",
		"Destination Host Name"};
	/** * Valores temporales al analizarlo. */
	private Hashtable values=new Hashtable();

	/**
	 * Constructor por defecto.
	 */
	public IPv4Analyzer(){
		layer=NETWORK_LAYER;
	}
	
	/**
	 * Devuelve si el paquete pasado es de tipo ICMP.
	 * 
	 * @param p 
	 * 			paquete.
	 * @return si es del tipo.
	 *  
	 * @see jpcap.packet.Packet
	 */
	public boolean isAnalyzable(Packet p){
		if(p instanceof IPPacket && ((IPPacket)p).version==4) return true;
		else return false;
	}

	/**
	 * Devuelve el nombre del protocolo.
	 * 
	 * @return nombre.
	 */
	public String getProtocolName(){
		return "IP v4";
	}

	/**
	 * Devuelve el vector con los nombres de los elementos.
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
		final IPPacket ip=(IPPacket)packet;
		values.put(valueNames[0],new Integer(4));
		values.put(valueNames[1],new Integer(ip.priority));
		values.put(valueNames[2],new Boolean(ip.t_flag));
		values.put(valueNames[3],new Boolean(ip.r_flag));
		values.put(valueNames[4],new Integer(ip.length));
		values.put(valueNames[5],new Integer(ip.ident));
		values.put(valueNames[6],new Boolean(ip.dont_frag));
		values.put(valueNames[7],new Boolean(ip.more_frag));
		values.put(valueNames[8],new Integer(ip.offset));
		values.put(valueNames[9],new Integer(ip.hop_limit));
		values.put(valueNames[10],new Integer(ip.protocol));
		values.put(valueNames[11],ip.src_ip.getHostAddress());
		values.put(valueNames[12],ip.dst_ip.getHostAddress());
		values.put(valueNames[13],ip.src_ip);
		values.put(valueNames[14],ip.dst_ip);
	}

	/**
	 * Devuelve el valor de elemente segun el nombre pasado.
	 * 
	 * @param valueName
	 * 			nombre de la variable.
	 * @return valor de la variable.
	 */
	public Object getValue(String valueName){
/*		if((valueNames[13].equals(valueName) && values.get(valueName) instanceof InetAddress) ||
		   (valueNames[14].equals(valueName) && values.get(valueName) instanceof InetAddress)){
			
			InetAddress addr=(InetAddress)values.get(valueName);
			//if(JDCaptor.hostnameCache.containsKey(addr))
			//	values.put(valueName,JDCaptor.hostnameCache.get(addr));
			//else{
				values.put(valueName,addr.getHostName());
				
			//}
		}
*/
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
		
		return getValue(valueNames[index]);
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
			v[i]=getValueAt(i);
		
		return v;
	}
}
