package presentacion.seleccionFicheros;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;

import javax.swing.JDialog;
import javax.swing.JFileChooser;

import presentacion.Mediador;
import presentacion.ventanaMenuSniffer.MenuSniffer;

/**
 * Clase que implementa el resultado de la ejecución de un FileChooser
 * normalmente provocado por la presión sobre un botón de Examinar.
 * 
 * @author Leonardo García & Jose Ramón Gutierrez
 * @version 2.0
 *  
 * @see javax.swing.JDialog
 * @see javax.swing.JFileChooser
 */

public class VentanaElegirDirectorios extends JDialog {
	/** * Ruta donde se almacena el fichero. */
	public String fRuta;
	/**	 * Nombre del fichero. */
	public String fName;
	/** * Buffer de lectura. 
	 *  @see java.io.BufferedReader
	 * */
	private BufferedReader in;
	/** * Stream de entrada. 
	 *  @see java.io.FileInputStream 
	 * */
	FileInputStream inStream;
	/**
	 * Nombre de la ventana que llama a esta clase.
	 */
	String vent;

	/**
	 * Instancia del mediador.
	 * 
	 * @see presentacion.Mediador
	 */
	Mediador mediador;

	/**
	 * Constructor.
	 * 
	 * @param ventana
	 *            para distinguir en dónde hemos pulsado Examinar.
	 * @see #inicializacionComponentes()
	 */
	public VentanaElegirDirectorios(String ventana) {
		vent = ventana;
		inicializacionComponentes();
	}//VentanaElegirDirectorios

	/**
	 * Inicializa los componentes de la ventana FileChooser.
	 */
	public void inicializacionComponentes() {
		mediador = new Mediador();
		JDialog ventana = new JDialog();
		ventana.setSize(300, 80);
		JFileChooser fc = new JFileChooser();
		fc.setDialogTitle("Directorios");
		fc.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
		int returnVal = fc.showOpenDialog(MenuSniffer.getFrames()[0]);

		//Valores que devuelve
		if (returnVal == JFileChooser.APPROVE_OPTION) {
			File file = fc.getSelectedFile();
			fRuta = file.getAbsolutePath();
			fName = file.getName();
			mediador.actualizaTfRuta(fRuta,vent);
		} else {
		}
	}//inicializacionComponentes
} // Clase VentanaElegirDirectorios
