package presentacion.seleccionFicheros;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;

import javax.swing.JDialog;
import javax.swing.JFileChooser;

import presentacion.Mediador;
import presentacion.avisos.AvisoFicheroExiste;
import presentacion.ventanaMenuSniffer.MenuSniffer;
import servicioAccesoDatos.FachadaFicheroDirectorios;

//import presentacion.ventanaExportacion.*;
/**
 * Clase que implementa el resultado de la ejecución de un FileChooser
 * normalmente provocado por la presión sobre un botón de Examinar.
 * 
 * @author Leonardo García & Jose Ramón Gutierrez & Carlos Mardones Muga
 * @version 3.0
 *  
 * @see javax.swing.JDialog
 * @see javax.swing.JFileChooser
 */

public class VentanaGuardarElegirFichero extends JDialog {
	//Atributos
	/** * Ruta donde se almacena el fichero. */
	public static String fRuta;
	/** * Nombre del fichero. */
	public String fName;
	/** * Buffer de lectura.
	 * 
	 * @see java.io.BufferedReader
	 */
	private BufferedReader in;
	/**
	 * Stream de entrada.
	 * 
	 * @see java.io.FileInputStream
	 */
	FileInputStream inStream;
	/**
	 * Instancia del mediador.
	 * 
	 * @see presentacion.Mediador
	 */
	Mediador mediador;
	/** * Nombre de la ventana que llama a la clase. */
	String tip;

	//Para inicializar el constructor
	/**
	 * Constructor.
	 * 
	 * @param tip
	 *            nombre de la ventana desde la que se llama a
	 *            GuardarElegirFichero. 
	 * @see #inicializacionComponentes()
	 */
	public VentanaGuardarElegirFichero(String tip) {
		this.tip = tip;
		inicializacionComponentes();
	}//VentanaGuardarElegirFichero

	/**
	 * Inicializa los componentes de la ventana FileChooser.
	 */
	public void inicializacionComponentes() {
		JDialog ventana = new JDialog();
		ventana.setSize(300, 80);
		mediador = new Mediador();
		JFileChooser fc = new JFileChooser();
		//Se establece el filtro de ficheros
		FiltroFileChooser filtertxt = new FiltroFileChooser();
		filtertxt.addExtension("txt");
		filtertxt.setDescription("Ficheros de texto ");
		FiltroFileChooser filterxml = new FiltroFileChooser();
		filterxml.addExtension("xml");
		filterxml.setDescription("Ficheros XML ");
		FiltroFileChooser filterpcap = new FiltroFileChooser();
		filterpcap.addExtension("pcap");
		filterpcap.setDescription("Ficheros de Captuta ");
		FiltroFileChooser filterbat = new FiltroFileChooser();
		filterbat.addExtension("bat");
		filterbat.setDescription("Proceso por lotes ");
		FiltroFileChooser filtersh = new FiltroFileChooser();
		filtersh.addExtension("sh");
		filtersh.setDescription("Script Unix ");
		
		String barra = System.getProperty("file.separator");

		
		
		if (tip.equals("Guardar fichero capturado...")) {
			fc.setDialogTitle("Guardar fichero capturado...");
			fc.setApproveButtonText("Guardar");
			fc.addChoosableFileFilter(filterpcap);
			fc.setFileFilter(filterpcap);
			File directorio = new File(FachadaFicheroDirectorios
					.getdirectorio("DIR_CAPTURAS"));
			fc.setCurrentDirectory(directorio);
			//mediador.habilitarMenuBarraMenus(3);
			//mediador.habiliatarBHelemento(2);
		}
		if (tip.equals("Guardar fichero de preferecias...")) {
			fc.setDialogTitle("Guardar fichero de preferecias...");
			fc.setApproveButtonText("Guardar");
			fc.addChoosableFileFilter(filterxml);
			fc.setFileFilter(filterxml);
			File directorio = new File(FachadaFicheroDirectorios
					.getdirectorio("DIR_CAPTURAS"));
			fc.setCurrentDirectory(directorio);
		}
		if (tip.equals("Exportar fichero a XML...") || tip.equals("Captura a XML...") ) {
			fc.setDialogTitle("Exportar fichero...");
			fc.setApproveButtonText("Exportar");
			fc.addChoosableFileFilter(filterxml);
			fc.setFileFilter(filterxml);
			File directorio = new File(FachadaFicheroDirectorios
					.getdirectorio("DIR_EXPORTACIONES"));
			fc.setCurrentDirectory(directorio);
		}
		if (tip.equals("Browse...")) {
			fc.setDialogTitle("Elegir ruta y fichero donde capturar...");
			fc.addChoosableFileFilter(filterpcap);
			fc.setFileFilter(filterpcap);
			File directorio = new File(FachadaFicheroDirectorios
					.getdirectorio("DIR_CAPTURAS"));
			fc.setCurrentDirectory(directorio);
		}
		if (tip.equals("Browse...FromFile")) {
			fc.setDialogTitle("Elegir ruta y fichero donde capturar...");
			fc.addChoosableFileFilter(filterpcap);
			fc.setFileFilter(filterpcap);
			File directorio = new File(FachadaFicheroDirectorios
					.getdirectorio("DIR_CAPTURAS"));
			fc.setCurrentDirectory(directorio);
		}
		if (tip.equals("GuardarInicioCapturaXML")) {
			fc.setDialogTitle("Guardar Preferencias de Captura...");
			fc.setApproveButtonText("Guardar");
			fc.addChoosableFileFilter(filterxml);
			fc.setFileFilter(filterxml);
			File directorio = new File(FachadaFicheroDirectorios
					.getdirectorio("DIR_PARAMETRIZACION"));
			fc.setCurrentDirectory(directorio);
		}
		if (tip.equals("GuardarInicioCapturaXMLFromFile")) {
			fc.setDialogTitle("Guardar Preferencias de Captura desde fichero...");
			fc.setApproveButtonText("Guardar");
			fc.addChoosableFileFilter(filterxml);
			fc.setFileFilter(filterxml);
			File directorio = new File(FachadaFicheroDirectorios
					.getdirectorio("DIR_PARAMETRIZACION"));
			fc.setCurrentDirectory(directorio);
		}
		if (tip.equals("GenerarBatBat")) {
			fc.setDialogTitle("Guardar fichero .bat...");
			fc.setApproveButtonText("Aceptar");
			fc.setFileFilter(filterbat);
			fc.setFileFilter(filtersh);
			File directorio = new File(FachadaFicheroDirectorios
					.getdirectorio("DIR_SCRIPTS"));
			fc.setCurrentDirectory(directorio);
		}
		if (tip.equals("GenerarFromFileXML")) {
			fc.setDialogTitle("Guardar fichero .XML...");
			fc.setApproveButtonText("Aceptar");
			fc.addChoosableFileFilter(filterxml);
			fc.setFileFilter(filterxml);
			File directorio = new File(FachadaFicheroDirectorios
					.getdirectorio("DIR_EXPORTACIONES"));
			fc.setCurrentDirectory(directorio);
		}
		
		if (tip.equals("GenerarFromFileXMLSaveXML")) {
			fc.setDialogTitle("Guardar Preferencias de Exportación...");
			fc.setApproveButtonText("Guardar");
			fc.addChoosableFileFilter(filterxml);
			fc.setFileFilter(filterxml);
			File directorio = new File(FachadaFicheroDirectorios
					.getdirectorio("DIR_PARAMETRIZACION"));
			fc.setCurrentDirectory(directorio);
		}
		
		if (tip.equals("GuardarProtocoloDefinido")) {
			fc.setDialogTitle("Guardar definicion de protocolo...");
			fc.setApproveButtonText("Guardar");
			fc.addChoosableFileFilter(filterxml);
			fc.setFileFilter(filterxml);
			File directorio = new File(FachadaFicheroDirectorios
					.getdirectorio("DIR_DEFINICIONES"));
			fc.setCurrentDirectory(directorio);
		}
		//Los valores devueltos
		//
		int returnVal = fc.showOpenDialog(MenuSniffer.getFrames()[0]);
		if (returnVal == JFileChooser.APPROVE_OPTION) {
			File file = fc.getSelectedFile();
			String nombre = file.getName();
			fRuta = file.getAbsolutePath();
			String sobreescribir = "Si";
			if (new File(fRuta).exists() == true) {
				String respuesta = new AvisoFicheroExiste(new File(fRuta))
						.getrespuesta();
				if (respuesta.equals("Si")) {
					sobreescribir = "Si";
				} else {
					sobreescribir = "No";
				}
			}
			if (sobreescribir == "Si") {
				//Si lo ha llamado un elemento de la barra de menus.
				if (tip.equals("Guardar fichero capturado...")) {
					mediador.savePcapFileCaptured(fRuta);
					System.out.println("Guardar fichero capturado..." + "=> "
							+ file.getName() + "   " + file.getPath());
				}
				if (tip.equals("Guardar fichero de preferecias...")) {
					mediador.PrefCapGuardarXML(1,fRuta);
					System.out.println("Guardar fichero de preferecias..."
							+ "=> " + file.getName() + "   " + file.getPath());
				}
				System.out.println("=>");
				System.out.println("=>");
				System.out.println(tip.toString());
				System.out.println("=>");
				if (tip.equals("Exportar fichero a XML...") || tip.equals("Exportar Captura a XML...") || tip.equals("Captura a XML...")) {
					//mediador.mostrarFichero(fRuta,"filtrado",true);
					
					mediador.guardarXML(file);
					System.out.println("Exportar fichero......" + "=> "
							+ file.getName() + "   " + file.getPath());
				}
				if (tip.equals("Browse...")) {
					mediador.setPrefCapFichero(fRuta);
					System.out
							.println("Fichero donde se guardara la captura..."
									+ "=> " + file.getName() + "   "
									+ file.getPath());
				}
				if (tip.equals("Browse...FromFile")) {
					mediador.setPrefCapFicheroFromFileFichero(fRuta);
					System.out
							.println("Fichero donde se guardara la captura..."
									+ "=> " + file.getName() + "   "
									+ file.getPath());
				}
				if (tip.equals("GuardarInicioCapturaXML")) {
					mediador.PrefCapGuardarXML(1,fRuta);
					System.out.println("Guardar fichero de preferecias..."
							+ "=> " + file.getName() + "   " + file.getPath());
				}
				if (tip.equals("GuardarInicioCapturaXMLFromFile")) {
					mediador.PrefCapGuardarXMLFromFile(3,fRuta);
					System.out.println("Guardar fichero de preferecias..."
							+ "=> " + file.getName() + "   " + file.getPath());
				}
				if (tip.equals("GenerarBatBat")) {
					mediador.setPrefGenFicheroBat(fRuta);
					System.out.println("Seleccion de fichero .bat ....."
							+ "=> " + file.getName() + "   " + file.getPath());
				}
				if (tip.equals("GenerarFromFileXML")) {
					mediador.setExportFromFileXML(fRuta);
					System.out.println("Seleccion de fichero .XML ....."
							+ "=> " + file.getName() + "   " + file.getPath());
				}
				if (tip.equals("GenerarFromFileXMLSaveXML")) {
					mediador.PrefExpGuardarXML(2,fRuta);
					System.out.println("Guardar fichero de preferecias..."
							+ "=> " + file.getName() + "   " + file.getPath());
				}
				if (tip.equals("GuardarProtocoloDefinido")) {
					mediador.PrefDefGuardarXML(7,fRuta);
					System.out.println("Guardar definicion de protocolo..."
							+ "=> " + file.getName() + "   " + file.getPath());
				}
			}
		} else {
			System.out.println("Cancelado");
		}
	}//inicializacionComponentes

	/**
	 * Retorna la ruta absoluta del fichero que se ha elegido en el
	 * <code>JFileChooser</code>.
	 * 
	 * @return ruta absoluta del fichero que se ha guardado
	 */
	public static String getrutaFichero() {
		return fRuta;
	}//getrutaFichero
}//Clase VentanaGuardarElegirFichero
