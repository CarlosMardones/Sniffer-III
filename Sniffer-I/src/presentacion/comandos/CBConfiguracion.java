package presentacion.comandos;

import java.awt.event.ActionListener;
import javax.swing.ImageIcon;
import javax.swing.JButton;

import presentacion.Mediador;
import servicioAccesoDatos.FachadaFicheroDirectorios;

/**
 * Boton configuracion
 * 
 * @author Leonardo García & JoseRamón Gutierrez
 * @version 2.0
 * 
 * @see javax.swing.JButton
 * @see Comando
 */
public class CBConfiguracion extends JButton implements Comando {

	// Atributos
	/**
	 * Receptor del comando.
	 * 
	 * @see presentacion.Mediador
	 */
	private Mediador mediador;

	// Constructor
	/**
	 * Implementa el botón de configuración.
	 * 
	 * @param mediador
	 *            receptor del comando.
	 * @see presentacion.Mediador
	 * @see javax.swing.JButton
	 * @see javax.swing.JButton#addActionListener(ActionListener)
	 */
	public CBConfiguracion(Mediador mediador) {
		// Llamamos al constructor de la superclase
		super(new ImageIcon(
				FachadaFicheroDirectorios.getdirectorioData("DIR_IMAGES") + 
				System.getProperty("file.separator") + 
				"configuracion.gif"));
		this.setToolTipText("Configuracion");
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
		mediador.irAConfigDirectorios();
	} // ejecutar
} // clase CBVentanaAyuda

