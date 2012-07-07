package dominio.pcap;


import java.util.Vector;

import net.sourceforge.jpcap.capture.*;
import net.sourceforge.jpcap.client.*;
import net.sourceforge.jpcap.net.*;
import net.sourceforge.jpcap.util.*;

import dominio.FachadaDominio;
import dominio.pcap.SaveRawPacketHandler;

//Declaraciones necesarias para la prueba
import java.awt.Frame;
import java.io.IOException;

import presentacion.preferencias.PreferenciasVisualizarInsercion;

import jpcap.JpcapCaptor;
import jpcap.JpcapSender;
import jpcap.NetworkInterface;
import jpcap.packet.Packet;


/**
 * Clase Insercion.
 * 
 * Insercion de paquetes. Nos proporciona todo lo relacionado con la Insercion de
 * ficheros.
 * 
 * @author Carlos Mardones Muga
 * @version 1.0
 * 
 * @see net.sourceforge.jpcap.net
 * @see net.sourceforge.jpcap.client
 * @see dominio.FachadaDominio
 */
public class Insercion extends Thread{
	/**
	 * Insercion infinita de paquetes.
	 */
	private static final int INFINITE = -1;

	/**
	 * Numero de paquetes a insertar.
	 */
	private static int PACKET_COUNT;

	/**
	 * Lista de dispositivos.
	 */
	protected String devs;

	/**
	 * Ruta de fichero de insercion.
	 */
	protected String filePathInsert;

	/**
	 * paquete.
	 */
	protected Packet paquete;

	/**
	 * Encaragado de enviar paquetes.
	 */
	protected  JpcapSender sender;
	
	/**
	 * Encaragado de enviar paquetes.
	 */
	protected static JpcapCaptor captor;
	
	/**
	 * Numero de veces que se ha insertado completamente el fichero determinado.
	 */
	private static int total=0;
	
	/**
	 * Opcion de insercion determinada, 1 insercion de paquetes definidos o 2 insercion de paquetes capturados.
	 */
	private int opcion=0;
	/**
	 * Determina si la ejecucion del hilo continua.
	 */
	public static boolean continuar=true;
	/**
	 * Preferencias de visualizacion de insercion.
	 */
	PreferenciasVisualizarInsercion pref;
	/**
	 * Constructor de la clase Captura
	 */
	public Insercion() {
		PACKET_COUNT = INFINITE;
		this.filePathInsert="";
	}

	/**
	 * 
	 * Constructor de la clase Captura para insertar ficheros de capturas.
	 *
	 * @param dispo
	 * @param file
	 * @param rep
	 * @param op
	 */
	public Insercion(String dispo,String file,int rep,int op) {
		PACKET_COUNT = rep;
		devs=dispo;
		this.filePathInsert=file;
		opcion=op;	
	}
	
	/**
	 * 
	 * Constructor de la clase de insercion para insertar paquetes generados.
	 * @param dispositivo
	 * @param p
	 * @param op
	 */
	public Insercion(String dispositivo, Packet p,int op) {
		PACKET_COUNT =-1;
		devs=dispositivo;
		this.paquete=p;
		opcion = op;
	}

	/**
	 * Abre un dispostivo determinado para realizar la insercion.
	 * 
	 * @param dispositivo
	 *            dispositivo de insercion.
	 */
	public void openInsercion(String dispositivo) throws Exception {
		try {
			System.out.println("openInsercion");
			String osName = System.getProperty("os.name");
			System.out.println("=> " + osName);
			NetworkInterface disp = FachadaDominio.isDispositivo( dispositivo);
			if(disp != null){
				if (osName.compareTo("Linux") == 0) {
					sender.openDevice(disp);
				} else {
					this.sender = JpcapSender.openDevice(disp);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}

	}

	/**
	 * Ejecuta la insercion con los parametros que le fueron pasados. Se ejecuta
	 * cuando es llamado "start" del thread.
	 */
	public void run() {
		//Bueno
	
		try {
			while(continuar){
				runInsert();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	/**
	 * Ejecuta la insercion.
	 */
	public void runInsert() {
		try {
			total=0;
			//si el packet es null no inserto nada sino las veces qeu se hayan determinado 
			if (opcion == 2) {
				if(paquete != null && devs != null){
					openInsercion(devs);
				}else if (filePathInsert != "" && devs != null){
					//si tengo un fichero para insertar inserto el fichero las veces que se determine
					openInsercion(devs);
					openFileInsercion(filePathInsert);
					while(total != PACKET_COUNT){
						insertPacketCapturados();
						sender=null;
						captor.close();
						openInsercion(devs);
						openFileInsercion(filePathInsert);
						total++;
						PreferenciasVisualizarInsercion.setCont(total);
					}
					pref.cambiarParada();
					stop();
					//pref.dispose();
				}
			}else if (opcion==1){
				if(paquete != null && devs != null){
					openInsercion(devs);
					while(total != PACKET_COUNT && sender != null){
						insertPacketDefinidos();
						total++;
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("<<<<< ===== runInsert ===== >>>>> ");
		}
	}

	/**
	 * Abre fichero con pauqetes capturados de la red.
	 * @param file
	 * @throws IOException
	 */
	public void openFileInsercion(String file) throws IOException{
		if(file != ""){
			captor=JpcapCaptor.openFile(file);
		}
	}
	
	/**
	 * Inserta los paquetes almacenados en un fichero.
	 * @throws IOException 
	 */
	
	public void insertPacketCapturados() throws IOException{
		continuar=true;
		Packet paqueteAux=captor.getPacket();
		System.out.println("->Insertando");
		while(continuar){
			paqueteAux=captor.getPacket();
			if(paqueteAux == null || paqueteAux==Packet.EOF)break;//{
			sender.sendPacket(paqueteAux);
		}
		System.out.println("->Fin Insercion");
	}
	
	/**
	 * Inserta paquetes definidos.
	 * @throws IOException
	 */
	public void insertPacketDefinidos() throws IOException{
		int cont=0;
		while(true){
			if(paquete == null ) break;//{
			try{
				sender.sendPacket(paquete);
				Thread.sleep(500);
			}catch (Exception e){
				System.out.println(e);
			}
			cont++;
			PreferenciasVisualizarInsercion.setCont(cont);
		}
	}
	
	
	/**
	 * Establece la captura con un numero maximo de paquetes.
	 * 
	 * @param aux
	 *            numero maximo de paquetes a capturar
	 */
	public void setNumPaquetes(String aux) {
		PACKET_COUNT = Integer.parseInt(aux);
	}

	/**
	 * Establece las preferencias de visualizacion.
	 * @param prefIns
	 */
	
	public void setPrefVisualizar(PreferenciasVisualizarInsercion prefIns) {
		this.pref=prefIns;
	}

	/**
	 * Determina la finalizacion del bucle.
	 */
	public static void setContinuar(){
		continuar=false;
		total=PACKET_COUNT;
	}

} // fin de la clase
