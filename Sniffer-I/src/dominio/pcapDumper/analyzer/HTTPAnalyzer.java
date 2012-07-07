package dominio.pcapDumper.analyzer;
import jpcap.packet.*;
import java.util.*;
import java.io.*;
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
public class HTTPAnalyzer extends JDPacketAnalyzer{
	/** * Contiene los elementos. */
	private static final String[] valueNames={
		"Method",
		"Header"
	};
	/** * Contiene los metodos. */
	String method;
	/** * Contiene las cabeceras. */
	Vector headers=new Vector();

	/**
	 * Constructor por defecto.
	 */
	public HTTPAnalyzer(){
		layer=APPLICATION_LAYER;
	}
	
	/**
	 * Devuelve si el paquete pasado es de tipo HTTP.
	 * 
	 * @param p 
	 * 			paquete.
	 * @return si es del tipo.
	 *  
	 * @see jpcap.packet.Packet
	 */
	public boolean isAnalyzable(Packet p){
		if(p instanceof TCPPacket &&
		   (((TCPPacket)p).src_port==80 || ((TCPPacket)p).dst_port==80))
			return true;
		else return false;
	}

	/**
	 * Devuelve el nombre del protocolo.
	 * 
	 * @return nombre.
	 */
	public String getProtocolName(){
		return "HTTP";
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
		method="";
		headers.removeAllElements();
		if(!isAnalyzable(p)) return;
		
		try{
			BufferedReader in=new BufferedReader(new StringReader(new String(p.data)));
			
			method=in.readLine();
			if(method==null || method.indexOf("HTTP")==-1){
				// this packet doesn't contain HTTP header
				method="Not HTTP Header";
				return;
			}
			else{
			String l;
			//read headers
			while((l=in.readLine()).length()>0)
				headers.addElement(l);
			}
		}catch(IOException e){
			
		}
		catch(Exception au){
			//System.out.println("En el paquete----: ");
			//nullpointer asigment en while((l=in.readLine()).length()>0) pero no se porque
			//ojo
		}
	}

	/**
	 * Devuelve el valor de elemente segun el nombre pasado.
	 * 
	 * @param valueName
	 * 			nombre de la variable.
	 * @return valor de la variable.
	 */
	public Object getValue(String valueName){
		if(valueNames[0].equals(valueName)) return method;
		if(valueNames[1].equals(valueName)) return headers;
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
		if(index==0) return method;
		if(index==1) return headers;
		return null;
	}
	
	/**
	 *  Devuelve el vector con los valores de todos las variables
	 * 
	 *  @return todas las variables
	 * 
	 */	
	public Object[] getValues(){
		Object[] values=new Object[2];
		values[0]=method;
		values[1]=headers;
		
		return values;
	}
}
