package presentacion.comandos;

import java.awt.event.ActionListener;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import javax.swing.JMenuItem;
import javax.swing.KeyStroke;

import presentacion.Mediador;

/**
 * Item del JMenu para salir de la aplicación
 * 
 * @author Leonardo García & JoseRamón Gutierrez
 * @version 2.0
 * 
 * @see javax.swing.JButton
 * @see Comando
 */
public class CBVentanaSalir extends JMenuItem implements Comando {

	// Atributos
	/**
	 * Receptor del comando.
	 * 
	 * @see presentacion.Mediador
	 */
	private Mediador mediador;

	// Constructor
	/**
	 * Implementa el elemento de menú <code> JMenuItem</code> para abrir la
	 * ventana de salir.
	 * 
	 * @param mediador
	 *            receptor del comando
	 * @see presentacion.Mediador
	 * @see javax.swing.JMenuItem
	 * @see javax.swing.JMenuItem#addActionListener(ActionListener)
	 */
	public CBVentanaSalir(Mediador mediador) {
		// Lamamos al constructor de la superclase
		super("Salir", KeyEvent.VK_S);
		KeyStroke ctrlS = KeyStroke.getKeyStroke(KeyEvent.VK_S,
				InputEvent.CTRL_MASK);
		this.setAccelerator(ctrlS);
		// Indicamos el receptor de la accion
		this.mediador = mediador;

		// Añadimos un listener a este comando
		this.addActionListener(mediador);
	} // ComandoVentanaSalir

	// Metodo
	/**
	 * Implementa el comportamiento de este elemento de menú que se encarga de
	 * abrir la ventana de salir a través del mediador.
	 * 
	 * @see presentacion.Mediador#irAVentanaSalir()
	 */
	public void ejecutar() {
		mediador.irAVentanaSalir();
	} // ejecutar
} // clase CBVentanaSalir

