package presentacion.comandos;

import java.awt.event.ActionListener;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import javax.swing.JMenuItem;
import javax.swing.KeyStroke;

import presentacion.Mediador;

/**
 * JMenuItem abre la ventana de inicio de captura desde fichero.
 * 
 * @author Leonardo García & JoseRamón Gutierrez
 * @version 2.0
 * 
 * @see Comando
 * @see javax.swing.JMenuItem
 */
public class CJMInicioCaptureFromFile extends JMenuItem implements Comando {

	// Atributos
	/**
	 * Receptor del comando.
	 * 
	 * @see presentacion.Mediador
	 */
	private Mediador mediador;

	// Constructor
	/**
	 * Implementa el elemento de menú para el inicio de captura desde fichero.
	 * 
	 * @param mediador
	 *            receptor del comando
	 * @see presentacion.Mediador
	 * @see javax.swing.JMenuItem
	 * @see javax.swing.JMenuItem#addActionListener(ActionListener)
	 */
	public CJMInicioCaptureFromFile(Mediador mediador) {
		// Lamamos al constructor de la superclase
		super("Captura desde fichero", KeyEvent.VK_E);
		KeyStroke ctrlE = KeyStroke.getKeyStroke(KeyEvent.VK_E,
				InputEvent.CTRL_MASK);
		this.setAccelerator(ctrlE);
		// Indicamos el receptor de la accion
		this.mediador = mediador;

		// Añadimos un listener a este comando
		this.addActionListener(mediador);
	} // ComandoVentanaSalir

	// Metodo
	/**
	 * Implementa el comportamiento de este elemento de menú que se encarga de
	 * abrir la ventana inicio de captura a través del mediador.
	 * 
	 * @see presentacion.Mediador#irInicioCaptureFromFile(boolean)
	 */
	public void ejecutar() {
		mediador.irInicioCaptureFromFile(true);
	} // ejecutar
} // clase CJMVisualFrameCapture

