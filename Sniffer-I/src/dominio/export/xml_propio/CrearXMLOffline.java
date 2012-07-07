package dominio.export.xml_propio;

import java.io.*;

import servicioAccesoDatos.FabricaAccesoDatos;
import servicioAccesoDatos.FabricaAccesoDatosIF;
import servicioAccesoDatos.FachadaFichero;


import net.sourceforge.jpcap.net.*;

/**
 * Clase CrearXML en Offline.
 * 
 * Sinopsis: Clase que crea un fichero en formato XML con la descripcion
 * detallada de las tramas de datos capturadas.
 * 
 * @author Leonardo García & JoseRamón Gutierrez
 * @version 2.0
 * 
 * @see net.sourceforge.jpcap.net
 * @see net.sourceforge.jpcap.client
 * @see org.jdom
 */

public class CrearXMLOffline {
	
	/**
	 * fichero donde se guarda el xml
	 */
	//private File fichero;
	private String fichero;
	
	/**
	 * Contador de paquetes que llegan
	 */
	private static long contador;
	
	/**
	 * Escritor de ficheros de caracteres.
	 * 
	 * @see java.io.FileWriter
	 */
	public FileWriter writer;

	/**
	 * Buffer de escritura.
	 * 
	 * @see java.io.BufferedWriter
	 */
	public BufferedWriter buffer;

	/**
	 * Escritor de ficheros de salida.
	 * 
	 * @see java.io.PrintWriter
	 */
	public PrintWriter output;
	
	private FabricaAccesoDatosIF fabrica;
	private FachadaFichero f;
	
	
	/**
	 * Constructor de la clase CrearXML
	 * 
	 * 
	 * @param history
	 *            Estructura donde se almacenan todos los paquetes de la captura
	 *            The history acts as a FIFO.
	 * @param fichero
	 *            Nombre del fichero XML
	 *  
	 */
	
	public CrearXMLOffline(String fichero) { //estoy
		this.fichero = fichero;
		// Creamos un elemento root
		//root = new Element("Captura");
		contador=0;
		try {
			output = new PrintWriter(new BufferedWriter(new FileWriter(fichero)));
		} catch (Exception e) {
			e.printStackTrace();
		}
		

		fabrica=new FabricaAccesoDatos();
		f=fabrica.creaFachadaFichero("exportacion", fichero );
		
		writeToFile("<?xml version=\"1.0\" encoding=\"UTF-8\" ?>");
		writeToFile("<Captura>");

		
	}
	
	/**
	 * Etherlayer
	 * 
	 * Funcion que añade a documento los datos de la capa de enlace (
	 * Ethernetlayer )
	 * 
	 * @param ethernetPacket
	 */
	public void xmlContadorHijo() {
		if (contador > 0){
			writeToFile("  </Packet>");
		}
		writeToFile("  <Packet Numero=\"" + contador + "\">");
		contador++;
	}
	
	/**
	 * Etherlayer
	 * 
	 * Funcion que añade a documento los datos de la capa de enlace (
	 * Ethernetlayer )
	 * 
	 * @param ethernetPacket
	 */
	public void Etherlayer(EthernetPacket ethernetPacket) {
		Etherlayer ethernetlayer = new Etherlayer(ethernetPacket);
		writeToFile(ethernetlayer.getStr());
	}
	
	/**
	 * ARPlayer
	 * 
	 * Funcion que añade a documento los datos del protocolo ARP
	 * 
	 * @param arpPacket
	 */
	public void ARPlayer(ARPPacket arpPacket) {
		ARPlayer arplayer = new ARPlayer(arpPacket);
		writeToFile(arplayer.getStr());
	}
	
	/**
	 * IPPlayer
	 * 
	 * Funcion que añade a documento los datos del protocolo IP
	 * 
	 * @param ipPacket
	 */
	public void IPlayer(IPPacket ipPacket) {
		IPlayer iplayer = new IPlayer(ipPacket);
		writeToFile(iplayer.getStr());
	}
	
	/**
	 * ICMPlayer
	 * 
	 * Funcion que añade a documento los datos del protocolo ICMP
	 * 
	 * @param icmpPacket
	 */
	public void ICMPlayer(ICMPPacket icmpPacket) {
		ICMPlayer icmplayer = new ICMPlayer(icmpPacket);
		writeToFile(icmplayer.getStr());
	}
	
	/**
	 * IGMPlayer
	 * 
	 * Funcion que añade a documento los datos del protocolo IGMP
	 * 
	 * @param igmpPacket
	 */
	public void IGMPlayer(IGMPPacket igmpPacket) {
		IGMPlayer igmplayer = new IGMPlayer(igmpPacket);
		writeToFile(igmplayer.getStr());
	}
	
	/**
	 * TCPlayer
	 * 
	 * Funcion que añade a documento los datos del protocolo TCP
	 * 
	 * @param tcpPacket
	 */
	public void TCPlayer(TCPPacket tcpPacket) {
		TCPlayer tcplayer = new TCPlayer(tcpPacket);
		writeToFile(tcplayer.getStr());
	}
	
	/**
	 * UDPlayer
	 * 
	 * Funcion que añade a documento los datos del protocolo UDP
	 * 
	 * @param udpPacket
	 */
	public void UDPlayer(UDPPacket udpPacket) {
		UDPlayer udplayer = new UDPlayer(udpPacket);
		writeToFile(udplayer.getStr());
	}
	
	public void endFile(){
		try {
			//output.flush();
			writeToFile("  </Packet>");
			writeToFile("</Captura>");
			f.cerrar();
			output.close();
			
		}
		catch(Exception ex) {
			ex.printStackTrace();
		}
	}
	
	public void writeToFile (String aux){
		try {
			//output.println(aux);
			f.escribir(aux);
		}
			catch(Exception e) {
				
			}
		/*
		try {

			FileWriter fw = new FileWriter("escribeme.txt");

			BufferedWriter bw = new BufferedWriter(fw);

			PrintWriter salida = new PrintWriter(bw);

			salida.println("Hola, soy la primera línea");

			salida.close();

//			 Modo append

			bw = new BufferedWriter(new FileWriter("escribeme.txt", true));

			salida = new PrintWriter(bw);

			salida.print("Y yo soy la segunda. ");

			double b = 123.45;

			salida.println(b);

			salida.close();

			}

		catch(java.io.IOException ioex) {
			
		}

		
	      BufferedWriter bufferedWriter = null;
	        
	        try {
	            
	            //Construct the BufferedWriter object
	            bufferedWriter = new BufferedWriter(new FileWriter(filename));
	            
	            //Start writing to the output stream
	            bufferedWriter.write("Writing line one to file");
	            bufferedWriter.newLine();
	            bufferedWriter.write("Writing line two to file");
	            
	        } catch (FileNotFoundException ex) {
	            ex.printStackTrace();
	        } catch (IOException ex) {
	            ex.printStackTrace();
	        } finally {
	            //Close the BufferedWriter
	            try {
	                if (bufferedWriter != null) {
	                    bufferedWriter.flush();
	                    bufferedWriter.close();
	                }
	            } catch (IOException ex) {
	                ex.printStackTrace();
	            }
	        }
		
	        

    		}
	        
			/* OJO
			 * 
			 * Ecribir cierrar el fichero, habria que dejarlo sin cerrar y poner
			 * las dos opciones 
			 *  flush para forzar escritura 
			 *  close para cerrar el fichero
			 */
	}
	
} // fin dela clase

