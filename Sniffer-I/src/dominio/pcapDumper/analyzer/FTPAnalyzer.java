package dominio.pcapDumper.analyzer;
import jpcap.packet.*;
/**
 * Paquetes FTP
 * 
 * Analiza los paquetes de tipo FTP
 * 
 * @author Leonardo García & Jose Ramón Gutierrez
 * @version 2.0
 * 
 * @see JDPacketAnalyzer
 * @see jpcap.packet.Packet
 */
public class FTPAnalyzer extends JDPacketAnalyzer{
	/**
	 * Constructor por defecto.
	 */
	public FTPAnalyzer(){
		layer=APPLICATION_LAYER;
	}
	
	/**
	 * Devuelve si el paquete pasado es de tipo FTP.
	 * 
	 * @param p 
	 * 			paquete.
	 * @return si es del tipo.
	 *  
	 * @see jpcap.packet.Packet
	 */
	public boolean isAnalyzable(Packet p){
		if(p instanceof TCPPacket &&
		   (((TCPPacket)p).src_port==20 || ((TCPPacket)p).dst_port==20 ||
		    ((TCPPacket)p).src_port==21 || ((TCPPacket)p).dst_port==21))
			return true;
		else return false;
	}
	
	/**
	 * Devuelve el nombre del protocolo.
	 * 
	 * @return nombre.
	 */
	public String getProtocolName(){
		return "FTP";
	}
	
	/**
	 * Devuelve el vector con los nombres de los elementos.
	 */
	public String[] getValueNames(){return null;}
	
	/**
	 * Analiza el Paquete.
	 */
	public void analyze(Packet p){}
	
	/**
	 * Devuelve el valor de elemente segun el nombre pasado.
	 * 
	 * @param s
	 * 			nombre de la variable.
	 * @return valor de la variable.
	 */
	public Object getValue(String s){ return null; }
	
	/**
	 * Devuelve el valor de elemento segun la posicion en el vector.
	 * 
	 * @param i
	 * 			posición.
	 * @return valor de la variable.
	 */
	public Object getValueAt(int i){ return null; }
	
	/**
	 *  Devuelve el vector con los valores de todos las variables
	 * 
	 *  @return todas las variables
	 * 
	 */	
	public Object[] getValues(){ return null; }
}
