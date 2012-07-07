package presentacion.comandos;

import java.awt.Font;
import java.awt.event.ActionListener;
import javax.swing.ImageIcon;
import javax.swing.JButton;

import presentacion.Mediador;
import servicioAccesoDatos.FachadaFicheroDirectorios;

/**
 * Abre ficheros de distintos tipos
 * captura realizada .pcap
 * 
 * @author Leonardo García & JoseRamón Gutierrez & Carlos Mardones Muga
 * @version 3.0
 * 
 * @see javax.swing.JButton
 * @see Comando
 */
public class CBAbrirFichero extends JButton implements Comando {

	// Atributos
	/**
	 * Receptor del comando.
	 * 
	 * @see presentacion.Mediador
	 */
	private Mediador mediador;

	/**
	 * Tipo de fichero 
	 */
	private String titulo;

	// Constructor

	/**
	 * Implementa el botón abrir fichero según el parámetro introducido
	 * 
	 * @param mediador
	 *            receptor del comando
	 * @see presentacion.Mediador
	 * @see javax.swing.JButton
	 * @see javax.swing.JButton#addActionListener(ActionListener)
	 */
	public CBAbrirFichero(Mediador mediador, String titulo) {
		// Llamamos al constructor de la superclase
		super();
		if (titulo.equals("Abrir fichero de Capturas...")) {
			this.setIcon(new ImageIcon(
					FachadaFicheroDirectorios.getdirectorioData("DIR_IMAGES") + 
					System.getProperty("file.separator") + 
					"open.png"));
			this.setToolTipText("Abrir Captura");
			this.setMnemonic('A');
		}
		if (titulo.equals("Abrir fichero de Capturas desde fichero...")) {
			this.setIcon(new ImageIcon(
					FachadaFicheroDirectorios.getdirectorioData("DIR_IMAGES") + 
					System.getProperty("file.separator") + 
					"open.png"));
			this.setToolTipText("Abrir Captura desde fichero");
			this.setMnemonic('D');
		}		
		if (titulo.equals("Cargar fichero de preferecias...")) {
			this.setIcon(new ImageIcon(
					FachadaFicheroDirectorios.getdirectorioData("DIR_IMAGES") + 
					System.getProperty("file.separator") + 
					"open.png"));
			this.setToolTipText("Abrir fichero preferencias");
			this.setMnemonic('P');
		}
		if (titulo.equals("BrowseInicioCaptura")) {
			this.setText("Abrir");
			this.setMnemonic('A');
		}
		if (titulo.equals("AbrirFicheroXMLFromFile")) {
			this.setText("Abrir");
			this.setMnemonic('A');
		}
		if (titulo.equals("GenerarBatPreferencias")) {
			this.setMnemonic('W');
			this.setIcon(new ImageIcon(
					FachadaFicheroDirectorios.getdirectorioData("DIR_IMAGES") + 
					System.getProperty("file.separator") + 
					"open_small.png"));
		}
		if (titulo.equals("GenerarFromFileXML")) {
			this.setMnemonic('P');
			this.setIcon(new ImageIcon(
					FachadaFicheroDirectorios.getdirectorioData("DIR_IMAGES") + 
					System.getProperty("file.separator") + 
					"open_small.png"));
		}
		if (titulo.equals("GenerarFromFileXMLOpenXML")) {
			this.setText("  Abrir  ");
			//this.setToolTipText("Acepta");
			this.setFont(new Font("Arial", Font.BOLD, 12));
			this.setAlignmentY(CENTER_ALIGNMENT);
		}
		if (titulo.equals("AbrirDefinicionProtocolo")) {
			this.setIcon(new ImageIcon(
					FachadaFicheroDirectorios.getdirectorioData("DIR_IMAGES") + 
					System.getProperty("file.separator") + 
					"open.png"));
			this.setToolTipText("Abrir Definicion Protocolo");
			this.setMnemonic('A');
		}
		if (titulo.equals("AbrirDefinicionProtocoloInsercion")) {
			this.setIcon(new ImageIcon(
					FachadaFicheroDirectorios.getdirectorioData("DIR_IMAGES") + 
					System.getProperty("file.separator") + 
					"open.png"));
			this.setToolTipText("Abrir Definicion Protocolo para Insercion");
			this.setMnemonic('A');
		}
		if (titulo.equals("Abrir fichero de exportaciones...")) {
			this.setIcon(new ImageIcon(
					FachadaFicheroDirectorios.getdirectorioData("DIR_IMAGES") + 
					System.getProperty("file.separator") + 
					"open.png"));
			this.setToolTipText("Abrir Exportaciones");
			this.setMnemonic('A');
		}
		
		if (titulo.equals("Abrir fichero de Capturas Insercion...")) {
			this.setIcon(new ImageIcon(
					FachadaFicheroDirectorios.getdirectorioData("DIR_IMAGES") + 
					System.getProperty("file.separator") + 
					"open.png"));
			this.setToolTipText("Abrir Captura");
			this.setMnemonic('A');
		}
		//this.setBorder(new
		// javax.swing.border.BevelBorder(javax.swing.border.BevelBorder.RAISED));
		this.setAlignmentY(CENTER_ALIGNMENT);

		this.titulo = titulo;
		// Indicamos el receptor de la accion
		this.mediador = mediador;
		// Añadimos un listener a este comando
		this.addActionListener(mediador);
	} // ComandoIrFuenteDatos

	// Metodo
	/**
	 * Abre la ventana de inicio de captura a través del mediador.
	 * 
	 * @see presentacion.Mediador#irAAbrirElegirFichero
	 * @see presentacion.Mediador#repaintVentana
	 */
	public void ejecutar() {
		mediador.irAAbrirElegirFichero(titulo);
		if (titulo.equals("Abrir fichero de Capturas...")) {
			mediador.repaintVentana(mediador.getVentanaMenuSniffer());
		}
	} // ejecutar
} // clase CBAbrirFichero

