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
import java.io.File;
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
public class InsercionCapturados extends Thread{
	/**
	 * Insercion infinita de paquetes.
	 */
	private static final int INFINITE = -1;

	/**
	 * Numero de paquetes a insertar.
	 */
	private int PACKET_COUNT;

	/**
	 * Lista de dispositivos.
	 */
	protected String devs;

	/**
	 * Ruta de fichero de insercion.
	 */
	protected String filePathInsert;

	/**
	 * Paquete.
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
	private int total=0;
	/**
	 * Determina si la ejecucion del hilo continua.
	 */
	public boolean continuar=true;
	
	
	/**
	 * 
	 * Constructor de la clase Captura para insertar ficheros de capturas.
	 *
	 * @param dispo
	 * @param file
	 * @param rep
	 */
	public InsercionCapturados(String dispo,String file,int rep) {
		PACKET_COUNT = rep;
		devs=dispo;
		this.filePathInsert=file;		
	}
	
	/**
	 * Abre un dispostivo determinado para realizar la insercion.
	 * 
	 * @param dispositivo
	 * @throws Exception
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
			//si el packet es null no inserto nada sino las veces qeu se hayan determinado 
				if(paquete != null && devs != null){
					openInsercion(devs);
				}else if (filePathInsert != "" && devs != null){
					//si tengo un fichero para insertar inserto el fichero las veces que se determine
					openInsercion(devs);
					openFileInsercion(filePathInsert);
					while(total != PACKET_COUNT){
						insertPacketCapturados();
						captor.close();
						openFileInsercion(filePathInsert);
						total++;
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
		File fichero = new File(file);
		
		if(file != "" && fichero.length() != 0){
			captor=JpcapCaptor.openFile(file);
		}
	}
	
	/**
	 * Inserta los paquetes almacenados en un fichero.
	 * @throws IOException 
	 */
	
	public void insertPacketCapturados() throws IOException{
		try {
			openInsercion(devs);
			openFileInsercion(filePathInsert);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		while(true){
			Packet paqueteAux=captor.getPacket();
			if(paqueteAux == null || paqueteAux==Packet.EOF) break;
			sender.sendPacket(paqueteAux);
			total++;
			PreferenciasVisualizarInsercion.setCont(total);
		}
	}
	
	
	/**
	 * Establece la captura con un numero maximo de paquetes.
	 * 
	 * @param aux numero maximo de paquetes a capturar
	 */
	public void setNumPaquetes(String aux) {
		PACKET_COUNT = Integer.parseInt(aux);
	}
	
	

} // fin de la clase
