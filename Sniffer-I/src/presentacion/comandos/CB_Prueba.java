package presentacion.comandos;

import java.awt.event.ActionListener;
import javax.swing.ImageIcon;
import javax.swing.JButton;

import presentacion.Mediador;
import servicioAccesoDatos.FachadaFicheroDirectorios;

/**
 * Evento asociado al botón de la barra de herramientas de la aplicación para ir
 * a la ventana de ayuda.
 * 
 * @author Leonardo García
 * @author JoseRamon Gutierrez
 * @version 2.0
 * 
 * @see Comando
 * @see javax.swing.JButton
 * @see presentacion.ventanaMenuSniffer.BarraHerramientas
 */
public class CB_Prueba extends JButton implements Comando {

	// Atributos
	/**
	 * Receptor del comando.
	 * 
	 * @see presentacion.Mediador
	 */
	private Mediador mediador;

	// Constructor
	/**
	 * Implementa el botón de la barra de herramientas
	 * <code>ventanaMenuSniffer.BarraHerramientas</code> para ir a la ventana
	 * de ayuda.
	 * 
	 * @param mediador
	 *            receptor del comando.
	 * @see presentacion.Mediador
	 * @see javax.swing.JButton
	 * @see javax.swing.JButton#addActionListener(ActionListener)
	 */
	public CB_Prueba(Mediador mediador) {
		// Llamamos al constructor de la superclase
		super(new ImageIcon(
				FachadaFicheroDirectorios.getdirectorioData("DIR_IMAGES") + 
				System.getProperty("file.separator") + 
				"tic.gif"));
		this.setToolTipText("Prueba");
		this.setAlignmentY(CENTER_ALIGNMENT);
		// Indicamos el receptor de la accion
		this.mediador = mediador;
		// Añadimos un listener a este comando
		this.addActionListener(mediador);
	} // ComandoBIrVentanaAyuda

	// Metodos
	/**
	 * Abre la ventana de Ayuda mediante el mediador.
	 * 
	 * @see presentacion.Mediador#irAAyuda()
	 */
	public void ejecutar() {
		mediador.irAPrueba();
	} // ejecutar
} // clase CBVentanaAyuda

