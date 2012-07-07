package presentacion.seleccionFicheros;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;

import javax.swing.JDialog;
import javax.swing.JFileChooser;

import presentacion.Mediador;
import presentacion.avisos.Aviso;
import presentacion.ventanaMenuSniffer.MenuSniffer;
import servicioAccesoDatos.FachadaFicheroDirectorios;

/**
 * Resultado de la ejecución de un <code> JFileChooser</code> normalmente
 * provocado por la presión sobre un botón de Examinar.
 * 
 * @author Leonardo García & Jose Ramón Gutierrez & Carlos Mardones Muga
 * @version 3.0
 *  
 * @see javax.swing.JDialog
 * @see javax.swing.JFileChooser
 */

public class VentanaAbrirElegirFichero extends JDialog {
	/** * Ruta donde se almacena el fichero. */
	public String fRuta;
	/**	 * Nombre del fichero. */
	public String fName;
	/** * Buffer de lectura. * * @see java.io.BufferedReader */
	private BufferedReader in;
	/** * Stream de entrada. *  * @see java.io.FileInputStream */
	FileInputStream inStream;
	/** * Nombre de la ventana que llama a esta clase. */
	String tip;
	/** * Instancia del mediador. *  * @see presentacion.Mediador */
	Mediador mediador;

	/**
	 * Genera una nueva ventana en la que elegir que fichero se desea abrir.
	 * 
	 * @param tipo
	 *            nombre de la ventana que llama a esta clase
	 * @param med receptor
	 * @see #inicializacionComponentes()
	 */
	public VentanaAbrirElegirFichero(String tipo, Mediador med) {
		tip = tipo;
		mediador = med;
		inicializacionComponentes();

	}//VentanaAbrirElegirFichero

	/**
	 * Inicializa los componentes de la ventana <code>FileChooser</code>.
	 * Establece que directorio se debe mostrar inicialmente y los eventos
	 * asociados a los botones <code>Abrir</code> y <code>Cancelar</code>.
	 */
	public void inicializacionComponentes() {
		//mediador = new Mediador();
		JDialog ventana = new JDialog();
		ventana.setSize(300, 80);
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

		String barra = System.getProperty("file.separator");
		if (tip.equals("Abrir fichero de Capturas...")) {
			fc.setDialogTitle("Abrir fichero de Capturas...");
			fc.addChoosableFileFilter(filterpcap);
			fc.setFileFilter(filterpcap);
			File directorio = new File(FachadaFicheroDirectorios
					.getdirectorio("DIR_CAPTURAS"));
			fc.setCurrentDirectory(directorio);
			//mediador.habilitarMenuBarraMenus(3);
			//mediador.habiliatarBHelemento(2);
		}
		if (tip.equals("Abrir fichero de Capturas desde fichero...")) {
			fc.setDialogTitle("Abrir fichero de Capturas desde fichero...");
			fc.addChoosableFileFilter(filterxml);
			fc.setFileFilter(filterpcap);
			fc.setFileFilter(filterxml);
			File directorio = new File(FachadaFicheroDirectorios
					.getdirectorio("DIR_CAPTURAS"));
			fc.setCurrentDirectory(directorio);
			//mediador.habilitarMenuBarraMenus(3);
			//mediador.habiliatarBHelemento(2);
		}
		if (tip.equals("Cargar fichero de preferecias...")) {
			fc.setDialogTitle("Cargar fichero de preferecias...");
			fc.addChoosableFileFilter(filterxml);
			fc.setFileFilter(filterxml);
			File directorio = new File(FachadaFicheroDirectorios
					.getdirectorio("DIR_FICHENTRADA"));
			fc.setCurrentDirectory(directorio);
		}
		if (tip.equals("BrowseInicioCaptura")) {
			fc.setDialogTitle("Buscar Fichero de Preferencias...");
			fc.addChoosableFileFilter(filterxml);
			fc.setFileFilter(filterxml);
			//Utilizarlo para fichero para path por defecto
			File directorio = new File(FachadaFicheroDirectorios
					.getdirectorio("DIR_PARAMETRIZACION"));
			fc.setCurrentDirectory(directorio);
		}
		if (tip.equals("AbrirFicheroXMLFromFile")) {
			fc.setDialogTitle("Buscar Fichero de Preferencias...");
			fc.addChoosableFileFilter(filterxml);
			fc.setFileFilter(filterxml);
			//Utilizarlo para fichero para path por defecto
			File directorio = new File(FachadaFicheroDirectorios
					.getdirectorio("DIR_PARAMETRIZACION"));
			fc.setCurrentDirectory(directorio);
		}
		if (tip.equals("GenerarBatPreferencias")) {
			fc.setDialogTitle("Elegir fichero de preferecias...");
			fc.setApproveButtonText("Aceptar");
			fc.addChoosableFileFilter(filterpcap);
			fc.addChoosableFileFilter(filterxml);
			fc.setFileFilter(filterxml);
			File directorio = new File(FachadaFicheroDirectorios
					.getdirectorio("DIR_PARAMETRIZACION"));
			fc.setCurrentDirectory(directorio);
		}
		if (tip.equals("GenerarFromFileXML")) {
			fc.setDialogTitle("Elegir fichero de captura o metadatos...");
			fc.setApproveButtonText("Aceptar");
			fc.addChoosableFileFilter(filterpcap);
			fc.addChoosableFileFilter(filterxml);
			fc.setFileFilter(filterpcap);
			File directorio = new File(FachadaFicheroDirectorios
					.getdirectorio("DIR_CAPTURAS"));
			fc.setCurrentDirectory(directorio);
		}	
		if (tip.equals("GenerarFromFileXMLOpenXML")) {
			fc.setDialogTitle("Elegir fichero de preferecias...");
			fc.setApproveButtonText("Aceptar");
			fc.addChoosableFileFilter(filterxml);
			fc.setFileFilter(filterxml);
			File directorio = new File(FachadaFicheroDirectorios
					.getdirectorio("DIR_PARAMETRIZACION"));
			fc.setCurrentDirectory(directorio);
		}
		if (tip.equals("AbrirDefinicionProtocolo")) {
			fc.setDialogTitle("Elegir definicion de protocolo...");
			fc.setApproveButtonText("Aceptar");
			fc.addChoosableFileFilter(filterxml);
			fc.setFileFilter(filterxml);
			File directorio = new File(FachadaFicheroDirectorios
					.getdirectorio("DIR_DEFINICIONES"));
			fc.setCurrentDirectory(directorio);
		}
		if (tip.equals("AbrirDefinicionProtocoloInsercion")) {
			fc.setDialogTitle("Elegir definicion de protocolo...");
			fc.setApproveButtonText("Aceptar");
			fc.addChoosableFileFilter(filterxml);
			fc.setFileFilter(filterxml);
			File directorio = new File(FachadaFicheroDirectorios
					.getdirectorio("DIR_DEFINICIONES"));
			fc.setCurrentDirectory(directorio);
		}
		if (tip.equals("Abrir fichero de exportaciones...")) {
			fc.setDialogTitle("Elegir fichero de exportaciones...");
			fc.setApproveButtonText("Abrir");
			fc.addChoosableFileFilter(filterxml);
			fc.setFileFilter(filterxml);
			File directorio = new File(FachadaFicheroDirectorios
					.getdirectorio("DIR_EXPORTACIONES"));
			fc.setCurrentDirectory(directorio);
		}
		
		if (tip.equals("Abrir fichero de Capturas Insercion...")) {
			fc.setDialogTitle("Abrir fichero de Capturas Insercion...");
			fc.addChoosableFileFilter(filterpcap);
			fc.setFileFilter(filterpcap);
			File directorio = new File(FachadaFicheroDirectorios
					.getdirectorio("DIR_CAPTURAS"));
			fc.setCurrentDirectory(directorio);
			//mediador.habilitarMenuBarraMenus(3);
			//mediador.habiliatarBHelemento(2);
		}
		// BrowseInicioCaptura
		//Los valores devueltos
		int returnVal = fc.showOpenDialog(MenuSniffer.getFrames()[0]);
		if (returnVal == JFileChooser.APPROVE_OPTION) {
			File file = fc.getSelectedFile();
			fRuta = file.getAbsolutePath();
			if (file.exists()) {
				//Si lo ha llamado un elemento de la barra de menus.
				if (tip.equals("Abrir fichero de Capturas...")) {
					mediador.AbrirFicheroCaptura(fRuta);
					mediador.habiliatarBHelemento(3);
					mediador.habiliatarBHelemento(4);
					mediador.habilitarComponenteBarraMenus(1, 1);
					mediador.habilitarComponenteBarraMenus(0, 1);
					System.out.println("Abrir fichero de Capturas..." + "=> "
							+ file.getName() + "   " + file.getPath());
					//
					//mediador.deshabilitarBHelemento(0);
					//mediador.deshabilitarBHelemento(1);
					//mediador.deshabilitarComponenteBarraMenus(0, 0);
					mediador.EnabledComponenteBarraMenus(0, 3,0,true);
					//mediador.deshabilitarComponenteBarraMenus(1, 0);
					//mediador.deshabilitarComponenteBarraMenus(1, 1);
					//mediador.deshabilitarComponenteBarraMenus(1, 3);
				}				
				if (tip.equals("Abrir fichero de Capturas desde fichero...")) {
					//mediador.mostrarFichero(fRuta,"filtrado",true);
					mediador.setPrefCapFicheroFromFileSource(fRuta);
					System.out.println("Cargar fichero de preferecias..."
							+ "=> " + file.getName() + "   " + file.getPath());
				}
				if (tip.equals("Cargar fichero de preferecias...")) {
					//mediador.mostrarFichero(fRuta,"filtrado",true);
					System.out.println("Cargar fichero de preferecias..."
							+ "=> " + file.getName() + "   " + file.getPath());
				}
				if (tip.equals("BrowseInicioCaptura")) {
					mediador.PrefCapLeerXML(fRuta);
					System.out
							.println("Cargar fichero de preferencia de captura..."
									+ "=> "
									+ file.getName()
									+ "   "
									+ file.getPath());
				}
				if (tip.equals("AbrirFicheroXMLFromFile")) {
					mediador.PrefFFLeerXML(fRuta);
					System.out
							.println("Cargar fichero de preferencia desde fichero..."
									+ "=> "
									+ file.getName()
									+ "   "
									+ file.getPath());
				}
				if (tip.equals("GenerarBatPreferencias")) {
					mediador.setPrefGenFicheroPref(fRuta);
					System.out.println("Selección de fichero de preferencias..."
							+ "=> " + file.getName() + "   " + file.getPath());
				}
				if (tip.equals("GenerarFromFileXML")) {
					mediador.setExportFromFilePref(fRuta);
					System.out.println("Selección de fichero pcap o Meta..."
							+ "=> " + file.getName() + "   " + file.getPath());
				}
				if (tip.equals("GenerarFromFileXMLOpenXML")) {
					mediador.PrefExpLeerXML(fRuta);
					System.out.println("Selección de fichero pcap o Meta..."
							+ "=> " + file.getName() + "   " + file.getPath());
				}
				if (tip.equals("AbrirDefinicionProtocolo")) {
					mediador.PrefDefLeerXML(fRuta); //me falta crear ese metodo en el mediador;
					System.out.println("Selección de fichero XML..."
							+ "=> " + file.getName() + "   " + file.getPath());
				}
				if (tip.equals("AbrirDefinicionProtocoloInsercion")) {
					mediador.PrefDefLeerXMLInsercion(fRuta); //me falta crear ese metodo en el mediador;
					System.out.println("Selección de fichero XML..."
							+ "=> " + file.getName() + "   " + file.getPath());
				}
				if (tip.equals("Abrir fichero de exportaciones...")) {
					mediador.PrefDefLeerXMLExportaciones(fRuta); //me falta crear ese metodo en el mediador;
					System.out.println("Selección de fichero XML..."
							+ "=> " + file.getName() + "   " + file.getPath());
				}
				if (tip.equals("Abrir fichero de Capturas Insercion...")) {
					mediador.PrefInserCapRuta(fRuta); //me falta crear ese metodo en el mediador;
					System.out.println("Selección de fichero pcap..."
							+ "=> " + file.getName() + "   " + file.getPath());
				}			
			} else {
				new Aviso("El fichero seleccionado no existe.", "Error");
			}
		}
	}

} // Clase VentanaAbrirElegirFichero
