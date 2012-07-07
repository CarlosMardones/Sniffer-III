package presentacion.comandos;

import java.awt.Font;
import java.awt.event.ActionListener;
import javax.swing.ImageIcon;
import javax.swing.JButton;

import presentacion.Mediador;
import presentacion.preferencias.PreferenciasDefinicion;
import servicioAccesoDatos.FachadaFicheroDirectorios;

/**
 * Botón de Guardar fichero
 * 
 * @author Leonardo García & JoseRamón Gutierrez & Carlos Mardones Muga
 * @version 2.0
 * 
 * @see javax.swing.JButton
 * @see Comando
 */
public class CBGuardarFichero extends JButton implements Comando {

	// Atributos
	/**
	 * Receptor del comando.
	 * 
	 * @see presentacion.Mediador
	 */
	private Mediador mediador;

	/**
	 * Tipo de fichero que se quiere guardar
	 */
	private String titulo;

	// Constructor

	/**
	 * Implementa el bonton de guardar ficheros
	 * 
	 * @param mediador
	 *            receptor del comando
	 * @see presentacion.Mediador
	 * @see javax.swing.JButton
	 * @see javax.swing.JButton#addActionListener(ActionListener)
	 */
	public CBGuardarFichero(Mediador mediador, String titulo) {
		// Llamamos al constructor de la superclase
		super();

		if (titulo.equals("Guardar fichero capturado...")) {
			this.setIcon(new ImageIcon(
					FachadaFicheroDirectorios.getdirectorioData("DIR_IMAGES") + 
					System.getProperty("file.separator") + 
					"save.png"));
			this.setToolTipText("Guardar fichero capturado");
			this.setMnemonic('G');
		}
		if (titulo.equals("Guardar fichero de preferecias...")) {
			this.setIcon(new ImageIcon(
					FachadaFicheroDirectorios.getdirectorioData("DIR_IMAGES") + 
					System.getProperty("file.separator") + 
					"xml.png"));
			this.setToolTipText("Guardar fichero preferencias");
			this.setMnemonic('F');
		}
		if (titulo.equals("Exportar fichero a XML...")) {
			this.setIcon(new ImageIcon(
					FachadaFicheroDirectorios.getdirectorioData("DIR_IMAGES") + 
					System.getProperty("file.separator") + 
					"xml.png"));
			this.setToolTipText("Exportar fichero a XML...");
			this.setMnemonic('X');
		}
		if (titulo.equals("Browse...")) {
			this.setMnemonic('W');
			this.setIcon(new ImageIcon(
					FachadaFicheroDirectorios.getdirectorioData("DIR_IMAGES") + 
					System.getProperty("file.separator") + 
					"open.png"));
			//KeyStroke ctrlW=
			// KeyStroke.getKeyStroke(KeyEvent.VK_W,InputEvent.CTRL_MASK);
			//this.setAccelerator(ctrlW);
		}
		if (titulo.equals("Browse...FromFile")) {
			this.setMnemonic('W');
			this.setIcon(new ImageIcon(
					FachadaFicheroDirectorios.getdirectorioData("DIR_IMAGES") + 
					System.getProperty("file.separator") + "open.png"));
			//KeyStroke ctrlW=
			// KeyStroke.getKeyStroke(KeyEvent.VK_W,InputEvent.CTRL_MASK);
			//this.setAccelerator(ctrlW);
		}
		if (titulo.equals("GuardarInicioCapturaXML")) {
			this.setMnemonic('G');
			this.setText("Guardar");
			//KeyStroke ctrlW=
			// KeyStroke.getKeyStroke(KeyEvent.VK_W,InputEvent.CTRL_MASK);
			//this.setAccelerator(ctrlW);
		}
		if (titulo.equals("GuardarInicioCapturaXMLFromFile")) {
			this.setMnemonic('G');
			this.setText("Guardar");
			//KeyStroke ctrlW=
			// KeyStroke.getKeyStroke(KeyEvent.VK_W,InputEvent.CTRL_MASK);
			//this.setAccelerator(ctrlW);
		}
	
		if (titulo.equals("GenerarBatBat")) {
			this.setMnemonic('W');
			this.setIcon(new ImageIcon(
					FachadaFicheroDirectorios.getdirectorioData("DIR_IMAGES") + 
					System.getProperty("file.separator") + 
					"open_small.png"));
		}
		if (titulo.equals("GenerarFromFileXML")) {
			this.setMnemonic('W');
			this.setIcon(new ImageIcon(
					FachadaFicheroDirectorios.getdirectorioData("DIR_IMAGES") + 
					System.getProperty("file.separator") + 
					"open_small.png"));
		}
		
		if (titulo.equals("GenerarFromFileXMLSaveXML")) {
			this.setText(" Guardar ");
			//this.setToolTipText("Acepta");
			this.setFont(new Font("Arial", Font.BOLD, 12));
			this.setAlignmentY(CENTER_ALIGNMENT);
		}
		
		if (titulo.equals("GuardarProtocoloDefinido")) {
			this.setIcon(new ImageIcon(
					FachadaFicheroDirectorios.getdirectorioData("DIR_IMAGES") + 
					System.getProperty("file.separator") + 
					"save.png"));
			this.setToolTipText("Guardar Protocolo definido");
			this.setMnemonic('Q');
		}
		this.titulo = titulo;
		//this.setBorder(new
		// javax.swing.border.BevelBorder(javax.swing.border.BevelBorder.RAISED));
		this.setAlignmentY(CENTER_ALIGNMENT);
		// Indicamos el receptor de la accion
		this.mediador = mediador;
		// Añadimos un listener a este comando
		this.addActionListener(mediador);
	} // ComandoIrFuenteDatos

	// Metodo
	/**
	 * Abre la ventana poder establecer el fichero a guardar
	 * 
	 * @see presentacion.Mediador#irAGuardarElegirFichero(String)
	 */
	public void ejecutar() {
		mediador.irAGuardarElegirFichero(titulo);
	} // ejecutar
} // clase CBGuardarFichero

