package presentacion.comandos;

import java.awt.event.ActionListener;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import javax.swing.JMenuItem;
import javax.swing.KeyStroke;

import presentacion.Mediador;

/**
 * JMenuItem para parar la captura.
 * 
 * @author Leonardo García & JoseRamón Gutierrez
 * @version 2.0
 * 
 * @see Comando
 * @see javax.swing.JMenuItem
 */
public class CJMFinCapture extends JMenuItem implements Comando {

	// Atributos
	/**
	 * Receptor del comando.
	 * 
	 * @see presentacion.Mediador
	 */
	private Mediador mediador;

	// Constructor
	/**
	 * Implementa el elemento de menú para parar la Captura.
	 * 
	 * @param mediador
	 *            receptor del comando
	 * @see presentacion.Mediador
	 * @see javax.swing.JMenuItem
	 * @see javax.swing.JMenuItem#addActionListener(ActionListener)
	 */
	public CJMFinCapture(Mediador mediador) {
		// Lamamos al constructor de la superclase
		super("Finalizar Captura", KeyEvent.VK_F);
		KeyStroke ctrlF = KeyStroke.getKeyStroke(KeyEvent.VK_F,
				InputEvent.CTRL_MASK);
		this.setAccelerator(ctrlF);
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
} // clase CJMFinCapture

