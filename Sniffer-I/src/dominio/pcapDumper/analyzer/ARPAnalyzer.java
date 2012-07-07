package dominio.pcapDumper.analyzer;
import jpcap.packet.*;
/**
 * Paquetes ARP
 * 
 * Analiza los paquetes de tipo ARP
 * 
 * @author Leonardo García & Jose Ramón Gutierrez
 * @version 2.0
 * 
 * @see JDPacketAnalyzer
 * @see jpcap.packet.Packet
 */
public class ARPAnalyzer extends JDPacketAnalyzer{
	/** * Contiene los elementos. */
	private static final String[] valueNames={
		"Hardware Type",
		"Protocol Type",
		"Hardware Address Length",
		"Protocol Address Length",
		"Operation",
		"Sender Hardware Address",
		"Sender Protocol Address",
		"Target Hardware Address",
		"Target Protocol Address"
	};
	/** * Paquete de tipo ARP. */
	private ARPPacket arp;
	
	/**
	 * Constructor por defecto.
	 */
	public ARPAnalyzer(){
		layer=NETWORK_LAYER;
	}
	/**
	 * Devuelve si el paquete pasado es de tipo ARP.
	 * 
	 * @param p 
	 * 			paquete.
	 * @return si es del tipo.
	 *  
	 * @see jpcap.packet.Packet
	 */
	public boolean isAnalyzable(Packet p){
		return (p instanceof ARPPacket);
	}
	
	/**
	 * Devuelve el nombre del protocolo.
	 * 
	 * @return nombre.
	 */
	public String getProtocolName(){
		return "ARP RARP";
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
		arp=(ARPPacket)p;
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
			case 0: 
			switch(arp.hardtype){
				case ARPPacket.HARDTYPE_ETHER: return "Ethernet ("+arp.hardtype+")";
				case ARPPacket.HARDTYPE_IEEE802: return "Token ring ("+arp.hardtype+")";
				case ARPPacket.HARDTYPE_FRAMERELAY: return "Frame relay ("+arp.hardtype+")";
				default: return new Integer(arp.hardtype);
			}
			case 1:
			switch(arp.prototype){
				case ARPPacket.PROTOTYPE_IP: return "IP ("+arp.prototype+")";
				default: return new Integer(arp.prototype);
			}
			case 2: return new Integer(arp.hlen);
			case 3: return new Integer(arp.plen);
			case 4:
			switch(arp.operation){
				case ARPPacket.ARP_REQUEST: return "ARP Request";
				case ARPPacket.ARP_REPLY: return "ARP Reply";
				case ARPPacket.RARP_REQUEST: return "Reverse ARP Request";
				case ARPPacket.RARP_REPLY: return "Reverse ARP Reply";
				case ARPPacket.INV_REQUEST: return "Identify peer Request";
				case ARPPacket.INV_REPLY: return "Identify peer Reply";
				default: return new Integer(arp.operation);
			}
			case 5: return arp.getSenderHardwareAddress();
			case 6: return arp.getSenderProtocolAddress();
			case 7: return arp.getTargetHardwareAddress();
			case 8: return arp.getTargetProtocolAddress();
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
		Object[] v=new Object[valueNames.length];
		for(int i=0;i<valueNames.length;i++)
			v[i]=getValueAt(i);
		
		return v;
	}
}
