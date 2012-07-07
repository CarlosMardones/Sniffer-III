package dominio.pcapDumper.analyzer;
import jpcap.packet.*;
/**
 * Clase abstracta para analizar los paquetes
 * 
 * @author Leonardo García & Jose Ramón Gutierrez
 * @version 2.0
 * 
 * @see JDPacketAnalyzer
 * @see jpcap.packet.Packet
 */
public abstract class JDPacketAnalyzer{
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
	 * Devuelve si el paquete pasado es analizable
	 * 
	 * @param packet 
	 * 			paquete.
	 * @return si es del tipo.
	 *  
	 * @see jpcap.packet.Packet
	 */
	public abstract boolean isAnalyzable(Packet packet);

	/**
	 * Analiza el Paquete.
	 */
	public abstract void analyze(Packet packet);
	
	/**
	 * Devuelve el nombre del protocolo.
	 * 
	 * @return nombre.
	 */
	public abstract String getProtocolName();
	
	/**
	 * Devuelve el vector con los nombres de los elementos.
	 * 
	 * @return nombre
	 */
	public abstract String[] getValueNames();

	/**
	 * Devuelve el valor de elemente segun el nombre pasado.
	 * 
	 * @param valueName
	 * 			nombre de la variable.
	 * @return valor de la variable.
	 */
	public abstract Object getValue(String valueName);
	
	/**
	 * Devuelve el valor de elemento segun la posicion en el vector.
	 * 
	 * @param index
	 * 			posición.
	 * @return valor de la variable.
	 */
	abstract Object getValueAt(int index);
	
	
	/**
	 *  Devuelve el vector con los valores de todos las variables
	 * 
	 *  @return todas las variables
	 * 
	 */	
	public abstract Object[] getValues();
}
