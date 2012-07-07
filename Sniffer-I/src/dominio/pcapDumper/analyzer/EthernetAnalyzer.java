package dominio.pcapDumper.analyzer;
import jpcap.packet.*;
/**
 * Paquetes Ethernet
 * 
 * Analiza los paquetes de tipo Ethernet
 * 
 * @author Leonardo García & Jose Ramón Gutierrez
 * @version 2.0
 * 
 * @see JDPacketAnalyzer
 * @see jpcap.packet.Packet
 */
public class EthernetAnalyzer extends JDPacketAnalyzer{
	/** * Contiene los elementos. */
	private static final String[] valueNames={
		"Frame Type",
		"Source MAC",
		"Destination MAC"
	};
	/** * Paquete de tipo Ethernet. */
	private EthernetPacket eth;

	/**
	 * Constructor por defecto.
	 */
	public EthernetAnalyzer(){
		layer=DATALINK_LAYER;
	}
	
	/**
	 * Devuelve si el paquete pasado es de tipo Ethernet.
	 * 
	 * @param p 
	 * 			paquete.
	 * @return si es del tipo.
	 *  
	 * @see jpcap.packet.Packet
	 */
	public boolean isAnalyzable(Packet p){
		return (p.datalink!=null && p.datalink instanceof EthernetPacket);
	}

	/**
	 * Devuelve el nombre del protocolo.
	 * 
	 * @return nombre.
	 */
	public String getProtocolName(){
		return "Ethernet Frame";
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
	public void analyze(Packet p){
		if(!isAnalyzable(p)) return;
		eth=(EthernetPacket)p.datalink;
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
	Object getValueAt(int index){
		switch(index){
		case 0: return new Integer(eth.frametype);
		case 1: return eth.getSourceAddress();
		case 2: return eth.getDestinationAddress();
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
