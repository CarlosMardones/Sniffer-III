package presentacion.visualizarCaptura;

import java.util.ArrayList;

import javax.swing.JTable;
import javax.swing.table.TableColumn;

import dominio.pcapDumper.analyzer.*;
import dominio.preferences.preferencesBeanIdentificacion;
import dominio.preferences.identificacion.PrefIdentificacion;
import jpcap.packet.*;

/**
 * Establece los datos de árbol que contiene los datos de un conexion
 * 
 * @author Leonardo García & Jose Ramón Gutierrez && Carlos Mardones Muga
 * @version 3.0
 *  
 * @see MiTablaModeloCx
 * @see JTable
 * @see TableColumn
 * @see TablePane
 * 
 */
public class TreeHandler {

	/**
	 * Constructor por defecto de la clase.
	 * 
	 * @param packet paquete 
	 */
	public TreeHandler(Packet packet) {
		JDPacketAnalyzer packetAnalyzer;
		OtroAnalyzer otro;
		PrefIdentificacion pref=new PrefIdentificacion();
		
		try {
			if (packet instanceof Packet) {
				//Packet packetInformation = (Packet) packet;
				TreePacket.treepk(packet);
			}
			if (packet.datalink instanceof EthernetPacket) {
				TreePacket.treepkether(packet);

			}
			if (packet instanceof ARPPacket) {
				TreePacket.treepkARP(packet);
			}

			if (packet instanceof IPPacket) {
				TreePacket.treepkIP(packet);
			}

			if (packet instanceof ICMPPacket) {
				TreePacket.treepkICMP(packet);
			}
/*
			if (packet instanceof IGMPPacket) {
				IGMPPacket igmpPacket = (IGMPPacket) packet;
				TreePacket.treepkIGMP(igmpPacket);

			}
*/			
			if (packet instanceof TCPPacket) {
				TreePacket.treepkTCP(packet);

			}
			if (packet instanceof UDPPacket) {
				TreePacket.treepkUDP(packet);
			}
			packetAnalyzer= new FTPAnalyzer();
			if (packetAnalyzer.isAnalyzable(packet)){
				TreePacket.treepkFTP(packet);
				for(int i=0;i< packet.data.length; i++){
					System.out.print("Pos["+ i + "]: "  + packet.data[i]);
				}
			}
			packetAnalyzer= new HTTPAnalyzer();
			if (packetAnalyzer.isAnalyzable(packet)){
				TreePacket.treepkHTTP(packet);
			}
			packetAnalyzer= new POP3Analyzer();
			if (packetAnalyzer.isAnalyzable(packet)){
				TreePacket.treepkPOP3(packet);
			}
			packetAnalyzer= new SMTPAnalyzer();
			if (packetAnalyzer.isAnalyzable(packet)){
				TreePacket.treepkSMTP(packet);
			}
			packetAnalyzer= new SSHAnalyzer();
			if (packetAnalyzer.isAnalyzable(packet)){
				TreePacket.treepkSSH(packet);
			}
			packetAnalyzer= new TelnetAnalyzer();
			if(packetAnalyzer.isAnalyzable(packet)){
				TreePacket.treepkTelnet(packet);
			}
			
			//SINO HA COINCIDIDO CON NIGUN PROTOCOLO COMPRUEBO 
			//EN LOS DEFINIDOS POR LA APLICACION
			
			otro = new OtroAnalyzer();
			packetAnalyzer= new FTPAnalyzer();
			if(otro.isAnalyzable(packet) && !packetAnalyzer.isAnalyzable(packet)){
				//OtroAnalyzer.analizar(packet.data, " ", pref);
				TreePacket.treepkOtro(packet,pref);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}