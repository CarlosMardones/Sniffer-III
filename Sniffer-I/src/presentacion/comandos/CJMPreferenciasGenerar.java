package presentacion.comandos;

import java.awt.event.ActionListener;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;

import javax.swing.JMenuItem;
import javax.swing.KeyStroke;

import presentacion.Mediador;

/**
 * JMenuItem abre la ventana de generación de script.
 * 
 * @author Leonardo García & JoseRamón Gutierrez
 * @version 2.0
 * 
 * @see Comando
 * @see javax.swing.JMenuItem
 */
public class CJMPreferenciasGenerar extends JMenuItem implements Comando {

	// Atributos
	/**
	 * Receptor del comando.
	 * 
	 * @see presentacion.Mediador
	 */
	private Mediador mediador;

	// Constructor
	/**
	 * Implementa el elemento de menú para generar los script
	 * 
	 * @param mediador
	 *            receptor del comando
	 * @see presentacion.Mediador
	 * @see javax.swing.JMenuItem
	 * @see javax.swing.JMenuItem#addActionListener(ActionListener)
	 */
	public CJMPreferenciasGenerar(Mediador mediador) {
		// Lamamos al constructor de la superclase
		super("Generar Script...", KeyEvent.VK_S);
		KeyStroke ctrlB = KeyStroke.getKeyStroke(KeyEvent.VK_B,
				InputEvent.CTRL_MASK);
		this.setAccelerator(ctrlB);
		// Indicamos el receptor de la accion
		this.mediador = mediador;
		// Añadimos un listener a este comando
		this.addActionListener(mediador);
	} // ComandoVentanaSalir

	// Metodo
	/**
	 * Implementa el comportamiento de este elemento de menú que se encarga de
	 * abrir la ventana de generar script a través del mediador.
	 * 
	 * @see presentacion.Mediador#irAGenerarBat()
	 */
	public void ejecutar() {
		mediador.irAGenerarBat();
	} // ejecutar
} // clase CJMInicioCapture

