package presentacion.visualizarCaptura;

import jpcap.PacketReceiver;
import jpcap.packet.*;

import java.text.*;
import java.util.*;

//import net.sourceforge.jpcap.net.IPPacket;
//import net.sourceforge.jpcap.net.IPProtocol;

import dominio.pcapDumper.analyzer.*;

public class PacketHandlerPcapLib implements PacketReceiver {
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

	/**
	 * Contructor por defecto de la clase.
	 * 
	 * @param venpadre VisualizarCaptura
	 * @param RTablePane TablePane
	 */
	public PacketHandlerPcapLib(VisualizarCaptura venpadre, TablePane RTablePane) {
		// EL constructor lo hace una sola vez.
		i = 0;
		this.venpadre = venpadre;
		this.RTablePane = RTablePane;
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
	public void receivePacket(Packet packet) {
		JDPacketAnalyzer packetAnalyzer;
		// Por cada paquete que llega tengo que actualizar los campos

		String macsource = "----";
		String macdest = "----";
		String frame = "----";
		String protocol = "---- ";
		String desc_protocol_short = "11";
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
			//FachadaDominio.getPcap().addpackethistory(packet);
			//venpadre.addpackethistory(packet);
			//venpadre.cogerpaquete(packet);
			packetAnalyzer= new EthernetAnalyzer();
			if (packetAnalyzer.isAnalyzable(packet)){
				packetAnalyzer.analyze(packet);
				frame = String.valueOf(packetAnalyzer.getValue("Frame Type"));
				macsource = String.valueOf(packetAnalyzer.getValue("Source MAC"));
				macdest = String.valueOf(packetAnalyzer.getValue("Destination MAC"));
				length = "";
				protocol = packetAnalyzer.getProtocolName();
				//System.out.println("");
			}
			packetAnalyzer= new IPv4Analyzer();
			if (packetAnalyzer.isAnalyzable(packet)){
				packetAnalyzer.analyze(packet);
				desc_protocol_short = new String("ip");
				ipsrc = String.valueOf(packetAnalyzer.getValue("Source IP"));
				ipdest = String.valueOf(packetAnalyzer.getValue("Destination IP"));
				protocol = packetAnalyzer.getProtocolName();

			}
			packetAnalyzer= new IPv6Analyzer();
			if (packetAnalyzer.isAnalyzable(packet)){
				packetAnalyzer.analyze(packet);
				desc_protocol_short = new String("ip");
				ipsrc = String.valueOf(packetAnalyzer.getValue("Source IP"));
				ipdest = String.valueOf(packetAnalyzer.getValue("Destination IP"));
				protocol = packetAnalyzer.getProtocolName();

			}
			packetAnalyzer= new TCPAnalyzer();
			if (packetAnalyzer.isAnalyzable(packet)){
				packetAnalyzer.analyze(packet);
				PuertoOrigen = ((Integer)packetAnalyzer.getValue("Source Port")).intValue();
				PuertoDestino= ((Integer)packetAnalyzer.getValue("Destination Port")).intValue();
				portsrc = String.valueOf(packetAnalyzer.getValue("Source Port"));
				portdest = String.valueOf(packetAnalyzer.getValue("Destination Port"));
				ack = String.valueOf(packetAnalyzer.getValue("Ack Number"));
				seq = String.valueOf(packetAnalyzer.getValue("Sequence Number"));
				protocol = packetAnalyzer.getProtocolName();
				
				venpadre.DatosConexion(ipsrc, ipdest, PuertoOrigen,
						PuertoDestino,  i+1);

			}
			packetAnalyzer= new UDPAnalyzer();
			if (packetAnalyzer.isAnalyzable(packet)){
				packetAnalyzer.analyze(packet);
				portsrc = String.valueOf(packetAnalyzer.getValue("Source Port"));
				portdest = String.valueOf(packetAnalyzer.getValue("Destination Port"));
				protocol = packetAnalyzer.getProtocolName();
			}
			

			// Introducel los datos del paquete en los vectores de datos y de conexion
			RTablePane.DatosPk();
			RTablePane.DatosRawPaquete(String.valueOf(i), String.valueOf(new java.util.Date(packet.sec*1000+packet.usec/1000).toString())
					, String.valueOf(packet.caplen));
			RTablePane.DatosPaquete(macsource, macdest, frame, protocol, ipsrc,
					ipdest, portsrc, portdest, seq, ack, length);

			venpadre.addpackethistory(packet);
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

