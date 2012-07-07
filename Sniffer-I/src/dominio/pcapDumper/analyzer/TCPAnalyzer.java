package dominio.pcapDumper.analyzer;
import jpcap.packet.*;
import java.util.*;
/**
 * Paquetes TCP
 * 
 * Analiza los paquetes de tipo TCP.
 * 
 * @author Leonardo García & Jose Ramón Gutierrez
 * @version 2.0
 * 
 * @see JDPacketAnalyzer
 * @see jpcap.packet.Packet
 */
public class TCPAnalyzer extends JDPacketAnalyzer{
	/** * Contiene los elementos. */
	private static final String[] valueNames={
		"Source Port",
		"Destination Port",
		"Sequence Number",
		"Ack Number",
		"URG Flag",
		"ACK Flag",
		"PSH Flag",
		"RST Flag",
		"SYN Flag",
		"FIN Flag",
		"Window Size"};
	/** * Valores temporales al analizarlo. */
	Hashtable values=new Hashtable();

	/**
	 * Constructor por defecto.
	 */
	public TCPAnalyzer(){
		layer=TRANSPORT_LAYER;
	}

	/**
	 * Devuelve si el paquete pasado es de tipo TCP.
	 * 
	 * @param p 
	 * 			paquete.
	 * @return si es del tipo.
	 *  
	 * @see jpcap.packet.Packet
	 */
	public boolean isAnalyzable(Packet p){
		return (p instanceof TCPPacket);
	}
	
	/**
	 * Devuelve el nombre del protocolo.
	 * 
	 * @return nombre.
	 */
	public String getProtocolName(){
		return "TCP";
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
	public void analyze(Packet p){
		values.clear();
		if(!isAnalyzable(p)) return;
		TCPPacket tcp=(TCPPacket)p;
		values.put(valueNames[0],new Integer(tcp.src_port));
		values.put(valueNames[1],new Integer(tcp.dst_port));
		values.put(valueNames[2],new Long(tcp.sequence));
		values.put(valueNames[3],new Long(tcp.ack_num));
		values.put(valueNames[4],new Boolean(tcp.urg));
		values.put(valueNames[5],new Boolean(tcp.ack));
		values.put(valueNames[6],new Boolean(tcp.psh));
		values.put(valueNames[7],new Boolean(tcp.rst));
		values.put(valueNames[8],new Boolean(tcp.syn));
		values.put(valueNames[9],new Boolean(tcp.fin));
		values.put(valueNames[10],new Integer(tcp.window));
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
