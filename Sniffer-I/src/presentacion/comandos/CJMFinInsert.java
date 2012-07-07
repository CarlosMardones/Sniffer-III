package presentacion.comandos;

import java.awt.event.ActionListener;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import javax.swing.JMenuItem;
import javax.swing.KeyStroke;

import presentacion.Mediador;

/**
 * JMenuItem para la insercion.
 * 
 * @author Carlos Mardones Muga
 * @version 2.0
 * 
 * @see Comando
 * @see javax.swing.JMenuItem
 */
public class CJMFinInsert extends JMenuItem implements Comando {

	// Atributos
	/**
	 * Receptor del comando.
	 * 
	 * @see presentacion.Mediador
	 */
	private Mediador mediador;

	// Constructor
	/**
	 * Implementa el elemento de menú para parar la Insercion.
	 * 
	 * @param mediador
	 *            receptor del comando
	 * @see presentacion.Mediador
	 * @see javax.swing.JMenuItem
	 * @see javax.swing.JMenuItem#addActionListener(ActionListener)
	 */
	public CJMFinInsert(Mediador mediador) {
		// Lamamos al constructor de la superclase
		super("Finalizar Insercion", KeyEvent.VK_M);
		KeyStroke ctrlM = KeyStroke.getKeyStroke(KeyEvent.VK_M,
				InputEvent.CTRL_MASK);
		this.setAccelerator(ctrlM);
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
	 */
	public void ejecutar() {
		mediador.freePantallaCaptura();
	} // ejecutar
} // clase CJMFinInsert

