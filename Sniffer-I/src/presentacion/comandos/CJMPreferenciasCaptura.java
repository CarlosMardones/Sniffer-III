package presentacion.comandos;

import java.awt.event.ActionListener;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import javax.swing.JMenuItem;
import javax.swing.KeyStroke;

import presentacion.Mediador;

/**
 * JMenuItem abre la ventana de preferencias para la captura
 * 
 * @author Leonardo García & JoseRamón Gutierrez
 * @version 2.0
 * 
 * @see Comando
 * @see javax.swing.JMenuItem
 */
public class CJMPreferenciasCaptura extends JMenuItem implements Comando {

	// Atributos
	/**
	 * Receptor del comando.
	 * 
	 * @see presentacion.Mediador
	 */
	private Mediador mediador;

	// Constructor
	/**
	 * Implementa el elemento de menú para las preferecias de captura.
	 * 
	 * @param mediador
	 *            receptor del comando
	 * @see presentacion.Mediador
	 * @see javax.swing.JMenuItem
	 * @see javax.swing.JMenuItem#addActionListener(ActionListener)
	 */
	public CJMPreferenciasCaptura(Mediador mediador) {
		// Lamamos al constructor de la superclase
		super("Captura", KeyEvent.VK_C);
		KeyStroke ctrlC = KeyStroke.getKeyStroke(KeyEvent.VK_C,
				InputEvent.CTRL_MASK);
		this.setAccelerator(ctrlC);
		// Indicamos el receptor de la accion
		this.mediador = mediador;

		// Añadimos un listener a este comando
		this.addActionListener(mediador);
	} // ComandoVentanaSalir

	// Metodo
	/**
	 * Implementa el comportamiento de este elemento de menú que se encarga de
	 * abrir la ventana preferencias a través del mediador.
	 * 
	 * @see presentacion.Mediador#irInicioCapture(boolean)
	 */
	public void ejecutar() {
		mediador.irInicioCapture(false);
	} // ejecutar
} // clase CJMInicioCapture

