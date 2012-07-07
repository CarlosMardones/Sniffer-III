package presentacion.capturando;

import java.text.*;
import java.util.*;


import presentacion.capturando.*;

public class TimePacket extends Thread {

	private int ethernet;

	private int ip;

	private int arp;

	private int icmp;

	private int igmp;

	private int tcp;

	private int udp;

	private int numpaquetesperiodo;

	private int npkVisualizados;

	public long time;

	private String timecapture;

	private int pkVisibles;

	public Fcaptura venpadre;

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

				venpadre.intervalo(numpaquetesperiodo, ethernet, arp, ip, icmp,
						igmp, tcp, udp);

				// RCountPacketHandler.erasedates();
				sleep(time); // dormir proceso

			} catch (InterruptedException e) {

			}
		} // end while
	} // end run

	public String GetTime() {
		Date hoy;
		SimpleDateFormat sdf;
		hoy = new Date();
		sdf = new SimpleDateFormat("HH:mm:ss:ms");
		String horaActual = new String(sdf.format(hoy));

		return horaActual;
	}

	public void setFrameVisibles(int contpk) {
		this.pkVisibles = contpk;
	}

}