package presentacion.comandos;

import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import javax.swing.JMenuItem;
import javax.swing.KeyStroke;

import presentacion.Mediador;

/**
 * JMenuItem abre la ventana de configuraci�n.
 * 
 * @author Leonardo Garc�a & JoseRam�n Gutierrez
 * @version 2.0
 * 
 * @see Comando
 * @see javax.swing.JMenuItem
 */
public class CJMConfiguracion extends JMenuItem implements Comando {

	// Atributos
	/**
	 * Receptor del comando.
	 * 
	 * @see presentacion.Mediador
	 */
	private Mediador mediador;

	// Constructor
	/**
	 * Implementa el elemento de men� para abrir la configuraci�n
	 * 
	 * @param mediador
	 *            receptor del comando
	 * @see presentacion.Mediador
	 * @see javax.swing.JMenuItem
	 * @see javax.swing.JMenuItem#addActionListener(ActionListener)
	 */
	public CJMConfiguracion(Mediador mediador) {
		// Lamamos al constructor de la superclase
		super("Configuraci�n", KeyEvent.VK_G);
		KeyStroke ctrlA = KeyStroke.getKeyStroke("F5");
		this.setAccelerator(ctrlA); // Indicamos el receptor de la accion
		// Indicamos el receptor de la accion
		this.mediador = mediador;

		// A�adimos un listener a este comando
		this.addActionListener(mediador);
	} // ComandoVentanaSalir

	// Metodo
	/**
	 * Implementa el comportamiento de este elemento de men� que se encarga de
	 * abrir la ventana de salir a trav�s del mediador.
	 * 
	 * @see presentacion.Mediador#irAConfigDirectorios()
	 */
	public void ejecutar() {
		mediador.irAConfigDirectorios();
	} // ejecutar
} // clase CJMInicioCapture

