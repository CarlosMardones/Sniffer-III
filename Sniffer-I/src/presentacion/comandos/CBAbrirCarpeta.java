package presentacion.comandos;

import java.awt.event.ActionListener;
import javax.swing.ImageIcon;
import javax.swing.JButton;

import presentacion.Mediador;
import servicioAccesoDatos.FachadaFicheroDirectorios;

/**
 * Abre una carpeta
 * 
 * @author Leonardo García & JoseRamón Gutierrez
 * @version 2.0
 * 
 * @see javax.swing.JButton
 * @see Comando
 */
public class CBAbrirCarpeta extends JButton implements Comando {

	// Atributos
	/**
	 * Receptor del comando.
	 * 
	 * @see presentacion.Mediador
	 */
	private Mediador mediador;

	/**
	 * Titulo
	 */
	private String titulo;

	// Constructor

	/**
	 * Implementa el botón de la barra de herramientas para abrir carpetas
	 * 
	 * @param mediador
	 *            receptor del comando
	 * @see presentacion.Mediador
	 * @see javax.swing.JButton
	 * @see javax.swing.JButton#addActionListener(ActionListener)
	 */
	public CBAbrirCarpeta(Mediador mediador, String titulo) {
		// Llamamos al constructor de la superclase
		super();
		if (titulo.equals("Capturas")) {
			this.setIcon(new ImageIcon(
					FachadaFicheroDirectorios.getdirectorioData("DIR_IMAGES") + 
					System.getProperty("file.separator") + 
					"open_small.png"));
			this.setToolTipText("Seleccionar capeta para Capturas");
			this.setMnemonic('C');
		}
		if (titulo.equals("Exportaciones")) {
			this.setIcon(new ImageIcon(
					FachadaFicheroDirectorios.getdirectorioData("DIR_IMAGES") + 
					System.getProperty("file.separator") + 
					"open_small.png"));
			this.setToolTipText("Seleccionar carpeta para Exportaciones");
			this.setMnemonic('E');
		}		
		if (titulo.equals("Paremetrizacion")) {
			this.setIcon(new ImageIcon(
					FachadaFicheroDirectorios.getdirectorioData("DIR_IMAGES") + 
					System.getProperty("file.separator") + 
					"open_small.png"));
			this.setToolTipText("Seleccionar carpeta de Parametrizaciones");
			this.setMnemonic('P');
		}
		if (titulo.equals("Scripts")) {
			this.setIcon(new ImageIcon(
					FachadaFicheroDirectorios.getdirectorioData("DIR_IMAGES") + 
					System.getProperty("file.separator") + 
					"open_small.png"));
			this.setToolTipText("Seleccionar carpeta de Scripts");
			this.setMnemonic('S');
		}
		this.setAlignmentY(CENTER_ALIGNMENT);

		this.titulo = titulo;
		// Indicamos el receptor de la accion
		this.mediador = mediador;
		// Añadimos un listener a este comando
		this.addActionListener(mediador);
	} // ComandoIrFuenteDatos

	// Metodo
	/**
	 * Abre la ventana de elegir carpetas a través del mediador.
	 * 
	 * @see presentacion.Mediador#irAVentanaElegirDirectorios
	 */
	public void ejecutar() {
		mediador.irAVentanaElegirDirectorios(titulo);
	} // ejecutar
} // clase CBAbrirFichero

