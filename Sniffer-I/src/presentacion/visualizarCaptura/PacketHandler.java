package presentacion.visualizarCaptura;

import net.sourceforge.jpcap.net.*;
import net.sourceforge.jpcap.capture.*;
import java.text.*;
import java.util.*;

import javax.swing.JTable;
import javax.swing.table.TableColumn;

import dominio.FachadaDominio;
import dominio.pcap.rules.*;

/**
 * Listener para la carde de datos en las tablas de paquetes y de conexiones
 * en el entorno grafico
 * 
 * @author Leonardo García & Jose Ramón Gutierrez
 * @version 2.0
 *  
 */
class PacketHandler implements PacketListener {
	/** * IP Origen.*/
	private String IpOrigen;
	/** * IP destino.*/
	private String IpDestino;
	/** * Puerto Origen.*/
	private int PuertoOrigen;
	/** * Puerto destino.*/
	private int PuertoDestino;
	/** * Datos del paquete.*/
	private String datos;
	/** * data byte.*/
	private byte[] data;
	/** * numero contador interno.*/
	public int i;
	/** * Padre. tipo VisualizaCaptura.*/
	public VisualizarCaptura venpadre;
	/** * Padre. Tipo TablePane.*/
	public TablePane RTablePane;
	/** * Reglas.*/
	public Rules Reglas;
	
	/**
	 * Contructor por defecto de la clase.
	 * 
	 * @param venpadre VisualizarCaptura
	 * @param RTablePane TablePane
	 * @param Reglas Rules
	 */
	public PacketHandler(VisualizarCaptura venpadre, TablePane RTablePane,
			Rules Reglas) {
		// EL constructor lo hace una sola vez.
		i = 0;
		this.venpadre = venpadre;
		this.RTablePane = RTablePane;
		this.Reglas = Reglas;
	}

	/**
	 * Cuando se recibe un paquete es procesado
	 * 
	 * @param packet
	 *            paquete 
	 * 
	 * @see dominio.pcapDumper.CountPacketHandler
	 * @see jpcap.JpcapWriter
	 */
	public void packetArrived(Packet packet) {

		// Por cada paquete que llega tengo que actualizar los campos

		String macsource = "----";
		String macdest = "----";
		String frame = "----";
		String protocol = "---- ";
		String desc_protocol_short = "";
		String ipsrc = "NO IP Address";
		String ipdest = "NO IP Address";
		String portsrc = "----";
		String portdest = "----";
		String seq = "----";
		String ack = "----";
		String length = "----";
		String itype = "";
		String icode = "";
		String dsize = "";
		String id = "";
		String ttl = "";
		String flags = "";

		try {
			FachadaDominio.getPcap().addpackethistory(packet);
			//venpadre.addpackethistory(packet);
			//venpadre.cogerpaquete(packet);

			if (packet instanceof EthernetPacket) {

				EthernetPacket ethernetPacket = (EthernetPacket) packet;
				frame = String.valueOf(LinkLayer.getDescription(FachadaDominio
						.getPcap().getLinkLayer()));
				macsource = ethernetPacket.getSourceHwAddress();
				macdest = ethernetPacket.getDestinationHwAddress();
				length = String.valueOf(ethernetPacket.getHeaderLength());
				desc_protocol_short = String.valueOf(ethernetPacket
						.getProtocol());
				//System.out.println("");
			}

			if (packet instanceof ARPPacket) {
				desc_protocol_short = new String("arp");
				ARPPacket arpPacket = (ARPPacket) packet;

			}

			if (packet instanceof IPPacket) {
				desc_protocol_short = new String("ip");
				IPPacket ipPacket = (IPPacket) packet;
				data = ipPacket.getData();

				dsize = String.valueOf(ipPacket.getLength());
				id = String.valueOf(ipPacket.getId());
				ttl = String.valueOf(ipPacket.getTimeToLive());
				ipsrc = ipPacket.getSourceAddress();
				ipdest = ipPacket.getDestinationAddress();
				protocol = IPProtocol.getDescription(ipPacket.getIPProtocol());

			}

			if (packet instanceof ICMPPacket) {
				desc_protocol_short = new String("icmp");
				ICMPPacket icmpPacket = (ICMPPacket) packet;
				itype = String.valueOf(icmpPacket.getMessageType());
				icode = String.valueOf(icmpPacket.getMessageCode());

			}

			if (packet instanceof IGMPPacket) {
				desc_protocol_short = new String("igmp");
				IGMPPacket igmpPacket = (IGMPPacket) packet;

			}

			if (packet instanceof TCPPacket) {
				desc_protocol_short = new String("tcp");
				TCPPacket tcpPacket = (TCPPacket) packet;
				//   IpOrigen = tcpPacket.getSourceAddress();
				//   IpDestino = tcpPacket.getDestinationAddress();
				PuertoOrigen = tcpPacket.getSourcePort();
				PuertoDestino = tcpPacket.getDestinationPort();
				portsrc = String.valueOf(tcpPacket.getSourcePort());
				portdest = String.valueOf(tcpPacket.getDestinationPort());
				ack = "0x"
						+ Long.toHexString(tcpPacket.getAcknowledgmentNumber());
				seq = "0x" + Long.toHexString(tcpPacket.getSequenceNumber());
				// flags = String.valueOf(tcpPacket.getFragmentFlags());
				if (tcpPacket.isUrg()) {
					flags = flags + "U";
				}
				if (tcpPacket.isAck()) {
					flags = flags + "A";
				}
				if (tcpPacket.isPsh()) {
					flags = flags + "P";
				}
				if (tcpPacket.isRst()) {
					flags = flags + "R";
				}
				if (tcpPacket.isSyn()) {
					flags = flags + "S";
				}
				if (tcpPacket.isFin()) {
					flags = flags + "F";
				}
				// Introduce los datos de la conexion
				venpadre.DatosConexion(ipsrc, ipdest, PuertoOrigen,
						PuertoDestino, i);

			}

			if (packet instanceof UDPPacket) {
				desc_protocol_short = new String("udp");
				UDPPacket udpPacket = (UDPPacket) packet;
				portsrc = String.valueOf(udpPacket.getSourcePort());
				portdest = String.valueOf(udpPacket.getDestinationPort());
			}

			//////////////////////////////////////////////////////
			// En principio los datos siempre se cogeran del paquete ip

			if (Reglas != null && (packet instanceof IPPacket)) {
				Reglas.DatosRules(ipsrc, ipdest, portsrc, portdest,
						desc_protocol_short, data, itype, icode, dsize, id,
						ttl, flags, ack, seq, packet, i);
			}
			
			//Introduce los datos del paquete
			RTablePane.DatosPaquete(macsource, macdest, frame, protocol, ipsrc,
					ipdest, portsrc, portdest, seq, ack, length);

			++i;
		} catch (Exception e) {
			e.printStackTrace();
		}
		//System.err.println("vm total memory: " + Runtime.getRuntime().totalMemory());
	}

	/**
	 * Devuelce la fecha actul
	 * 
	 * @return hora
	 */
	public String GetTime() {
		Date hoy;
		SimpleDateFormat sdf;

		hoy = new Date();
		sdf = new SimpleDateFormat("HH:mm:ss:ms");
		String horaActual = new String(sdf.format(hoy));

		return horaActual;
	}

}

