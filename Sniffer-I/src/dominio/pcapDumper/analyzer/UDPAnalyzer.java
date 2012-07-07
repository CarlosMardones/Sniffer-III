package dominio.pcapDumper.analyzer;
import jpcap.packet.*;

public class UDPAnalyzer extends JDPacketAnalyzer
{
	/** * Contiene los elementos. */
	private static final String[] valueNames={
		"Source Port",
		"Destination Port",
		"Packet Length"
	};
	/** * UDPPacket */
	private UDPPacket udp;

	/**
	 * Constructor por defecto.
	 */
	public UDPAnalyzer(){
		layer=TRANSPORT_LAYER;
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
		return (p instanceof UDPPacket);
	}
	
	/**
	 * Devuelve el nombre del protocolo.
	 * 
	 * @return nombre.
	 */
	public String getProtocolName(){
		return "UDP";
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
		if(!isAnalyzable(p)) return;
		udp=(UDPPacket)p;
	}

	/**
	 * Devuelve el valor de elemente segun el nombre pasado.
	 * 
	 * @param valueName
	 * 			nombre de la variable.
	 * @return valor de la variable.
	 */
	public Object getValue(String valueName){
		for(int i=0;i<valueNames.length;i++)
			if(valueNames[i].equals(valueName))
				return getValueAt(i);
		
		return null;
	}
	
	/**
	 * Devuelve el valor de elemento segun la posicion en el vector.
	 * 
	 * @param index
	 * 			posición.
	 * @return valor de la variable.
	 */
	public Object getValueAt(int index){
		switch(index){
			case 0: return new Integer(udp.src_port);
			case 1: return new Integer(udp.dst_port);
			case 2: return new Integer(udp.length);
			default: return null;
		}
	}
	
	/**
	 *  Devuelve el vector con los valores de todos las variables
	 * 
	 *  @return todas las variables
	 * 
	 */	
	public Object[] getValues(){
		Object[] v=new Object[3];
		for(int i=0;i<3;i++)
			v[i]=getValueAt(i);
		
		return v;
	}
}
