package dominio.pcapDumper.analyzer;
import jpcap.packet.*;
/**
 * Paquetes
 * 
 * Analiza los paquetes
 * 
 * @author Leonardo García & Jose Ramón Gutierrez
 * @version 2.0
 * 
 * @see JDPacketAnalyzer
 * @see jpcap.packet.Packet
 */
public class PacketAnalyzer extends JDPacketAnalyzer{
	/** * Contiene los elementos. */
	private static final String[] valueNames={"Captured Time","Captured Length"};
	/** * Paquete. */
	private Packet packet;
	
	/**
	 * Devuelve si es paquete.
	 * 
	 * @param packet 
	 * 			paquete.
	 * @return si es del tipo.
	 *  
	 * @see jpcap.packet.Packet
	 */
	public boolean isAnalyzable(Packet packet){
		return true;
	}
	
	/**
	 * Devuelve el nombre del protocolo.
	 * 
	 * @return nombre.
	 */
	public String getProtocolName(){
		return "Packet Information";
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
		packet=p;
	}

	/**
	 * Devuelve el valor de elemente segun el nombre pasado.
	 * 
	 * @param name
	 * 			nombre de la variable.
	 * @return valor de la variable.
	 */	
	public Object getValue(String name){
		if(name.equals(valueNames[0]))
			return new java.util.Date(packet.sec*1000+packet.usec/1000).toString();
		else if(name.equals(valueNames[1]))
			return new Integer(packet.caplen);
		else return null;
	}
	
	/**
	 * Devuelve el valor de elemento segun la posicion en el vector.
	 * 
	 * @param index
	 * 			posición.
	 * @return valor de la variable.
	 */
	Object getValueAt(int index){
		switch(index){
			case 0: return new java.util.Date(packet.sec*1000+packet.usec/1000).toString();
			case 1: return new Integer(packet.caplen);
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
		Object[] v=new Object[2];
		v[0]=new java.util.Date(packet.sec*1000+packet.usec/1000).toString();
		v[1]=new Integer(packet.caplen);
		
		return v;
	}
}