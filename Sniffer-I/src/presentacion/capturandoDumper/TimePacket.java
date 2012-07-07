package presentacion.capturandoDumper;

import java.text.*;
import java.util.*;

import dominio.pcapDumper.CountPacketHandler;


/**
 * Lleva la estadistica de los paquetes capturados y de que tipo son.
 * 
 * @author Leonardo García & JoseRamón Gutierrez
 * @version 2.0
 * 
 * @see Fcaptura
 * @see dominio.pcapDumper.CountPacketHandler
 */
public class TimePacket extends Thread {
	/**
	 * Tipo ethernet.
	 */
	private int ethernet;
	/**
	 * Tipo ip.
	 */
	private int ip;
	/**
	 * Tipo arp.
	 */
	private int arp;
	/**
	 * Tipo icmp.
	 */
	private int icmp;
	/**
	 * Tipo igmp.
	 */
	private int igmp;
	/**
	 * Tipo tcp.
	 */
	private int tcp;
	/**
	 * Tipo udp.
	 */
	private int udp;
	/**
	 * Numero de paquetes en un peridos.
	 */
	private int numpaquetesperiodo;
	/**
	 * Numero de paquetes visualizados.
	 */
	private int npkVisualizados;
	/**
	 * Tiempo
	 */
	public long time;
	/**
	 * Tiempo de capura.
	 */
	private String timecapture;
	/**
	 * Numero de paquetes que se muestran.
	 */
	private int pkVisibles;
	/**
	 * Ven Padre.
	 */
	public Fcaptura venpadre;
	/**
	 * Contador de paquete por tipo.
	 */
	private CountPacketHandler RCountPacketHandler;

	public TimePacket(Fcaptura venpadre, long time,
			CountPacketHandler RCountPacketHandler) {
		// constructor : coger los datos
		this.RCountPacketHandler = RCountPacketHandler;
		this.venpadre = venpadre;

		this.ethernet = 0;
		this.arp = 0;
		this.ip = 0;
		this.icmp = 0;
		this.igmp = 0;
		this.tcp = 0;
		this.udp = 0;
		this.time = time;
		this.numpaquetesperiodo = 0;

	}

	/**
	 * Comienza el hilo que controla la visualizacion de lo capturado
	 * en modo grafico.
	 */
	public void run() {

		while (true) {
			try {
				timecapture = GetTime();
				ethernet = RCountPacketHandler.ethernet;
				arp = RCountPacketHandler.arp;
				ip = RCountPacketHandler.ip;
				icmp = RCountPacketHandler.icmp;
				igmp = RCountPacketHandler.igmp;
				tcp = RCountPacketHandler.tcp;
				udp = RCountPacketHandler.udp;

				/// por aqui anda

				numpaquetesperiodo = RCountPacketHandler.i;
				npkVisualizados = RCountPacketHandler.contpk;
				if (npkVisualizados > pkVisibles) {
					RCountPacketHandler.eraseContParcial();
					venpadre.borrarTextArea();
				}
				venpadre.mostrarPackets(RCountPacketHandler.pktotal, RCountPacketHandler.getPackets());
				RCountPacketHandler.delPackets();
				venpadre.intervalo(RCountPacketHandler.pktotal,numpaquetesperiodo, ethernet, arp, ip, icmp,
						igmp, tcp, udp);


				// RCountPacketHandler.erasedates();
				sleep(time); // dormir proceso

			} catch (InterruptedException e) {

			}
		} // end while
	} // end run

	/**
	 * Devuelve la hora en formato HH:mm:ss:ms .
	 * 
	 * @return hora.
	 */
	public String GetTime() {
		Date hoy;
		SimpleDateFormat sdf;
		hoy = new Date();
		sdf = new SimpleDateFormat("HH:mm:ss:ms");
		String horaActual = new String(sdf.format(hoy));

		return horaActual;
	}

	/**
	 * Número de paquetes visibles por periodo.
	 * @param contpk
	 */
	public void setFrameVisibles(int contpk) {
		this.pkVisibles = contpk;
	}

}