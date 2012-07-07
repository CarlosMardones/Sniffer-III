package presentacion.comandos;

import java.awt.event.ActionListener;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import javax.swing.JMenuItem;
import javax.swing.KeyStroke;

import presentacion.Mediador;

/**
 * JMenuItem abre la ventana de inicio de insercion de paquetes Definidos.
 * 
 * @author Carlos Mardones Muga
 * @version 1.0
 * 
 * @see Comando
 * @see javax.swing.JMenuItem
 */
public class CJMInicioInsertDefinidos extends JMenuItem implements Comando {

	// Atributos
	/**
	 * Receptor del comando.
	 * 
	 * @see presentacion.Mediador
	 */
	private Mediador mediador;

	// Constructor
	/**
	 * Implementa el elemento de menú para el inicio de captura.
	 * 
	 * @param mediador
	 *            receptor del comando
	 * @see presentacion.Mediador
	 * @see javax.swing.JMenuItem
	 * @see javax.swing.JMenuItem#addActionListener(ActionListener)
	 */
	public CJMInicioInsertDefinidos(Mediador mediador) {
		// Lamamos al constructor de la superclase
		super("Inserción Paquetes Definidos", KeyEvent.VK_J);
		KeyStroke ctrlN = KeyStroke.getKeyStroke(KeyEvent.VK_J,
				InputEvent.CTRL_MASK);
		this.setAccelerator(ctrlN);
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
	 * @see presentacion.Mediador#irInicioInsert(boolean)
	 */
	public void ejecutar() {
		mediador.irInicioInsertDef(true);
	} // ejecutar
} // clase CJMInicioInsert

