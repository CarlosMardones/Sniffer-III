package presentacion.comandos;

import java.awt.event.WindowListener;
import javax.swing.JDialog;
import presentacion.Mediador;

/**
 * Evento asociado icono cerrar ventana.
 * 
 * @author Leonardo García & JoseRamón Gutierrez
 * @version 2.0
 * 
 * @see javax.swing.JButton
 * @see Comando
 */
public class CBWindowClosing extends JDialog implements Comando {

	private Mediador mediador;

	// Constructor
	/**
	 * Implementa el comando para cerrar una ventana.
	 * 
	 * @param mediador
	 *            receptor del comando
	 * @param ventana
	 *            ventana que se quiere cerrar
	 * @see presentacion.Mediador
	 * @see javax.swing.JDialog#addWindowListener(WindowListener)
	 */
	public CBWindowClosing(Mediador mediador, JDialog ventana) {
		// Indicamos el receptor de la accion
		this.mediador = mediador;
		// Añadimos un listener a este comando
		this.addWindowListener(mediador);
	} // ComandoVentWindowClosing

	// Metodo
	/**
	 * Cierra una ventana a través del mediador.
	 * 
	 * @see javax.swing.JDialog#dispose()
	 */
	public void ejecutar() {
		dispose();
		mediador.setPanelEstado("Se ha cerrado la ventana");
	} // ejecutar
} // clase CBWindowClosing

