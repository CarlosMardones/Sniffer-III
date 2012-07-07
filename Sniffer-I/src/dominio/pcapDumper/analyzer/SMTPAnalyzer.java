package dominio.pcapDumper.analyzer;
import jpcap.packet.*;
/**
 * Paquetes SMPT
 * 
 * Analiza los paquetes de tipo SMTP
 * 
 * @author Leonardo García & Jose Ramón Gutierrez
 * @version 2.0
 * 
 * @see JDPacketAnalyzer
 * @see jpcap.packet.Packet
 */
public class SMTPAnalyzer extends JDPacketAnalyzer{
	/**
	 * Constructor por defecto.
	 */
	public SMTPAnalyzer(){
		layer=APPLICATION_LAYER;
	}
	
	/**
	 * Devuelve si el paquete pasado es de tipo SMTP.
	 * 
	 * @param p 
	 * 			paquete.
	 * @return si es del tipo.
	 *  
	 * @see jpcap.packet.Packet
	 */
	public boolean isAnalyzable(Packet p){
		if(p instanceof TCPPacket &&
		   (((TCPPacket)p).src_port==25 || ((TCPPacket)p).dst_port==25))
			return true;
		else return false;
	}
	
	/**
	 * Devuelve el nombre del protocolo.
	 * 
	 * @return nombre.
	 */
	public String getProtocolName(){
		return "SMTP";
	}

	/**
	 * Devuelve el vector con los nombres de los elementos.
	 * 
	 * @return nombre
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
