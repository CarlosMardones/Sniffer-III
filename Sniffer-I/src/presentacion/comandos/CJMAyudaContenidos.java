package presentacion.comandos;

import java.awt.event.ActionListener;
import javax.swing.JMenuItem;
import javax.swing.KeyStroke;

import presentacion.Mediador;

/**
 * JMenuItem abre la ventana contenidos
 * 
 * @author Leonardo Garc�a & JoseRam�n Gutierrez & Carlos Mardones Muga
 * @version 2.0
 * 
 * @see Comando
 * @see javax.swing.JMenuItem
 */
public class CJMAyudaContenidos extends JMenuItem implements Comando {

	// Atributos
	/**
	 * Receptor del comando.
	 * 
	 * @see presentacion.Mediador
	 */
	private Mediador mediador;

	// Constructor
	/**
	 * ir a la ventana "mostrar la ayuda de la aplicaci�n".
	 * 
	 * @param mediador
	 *            receptor del comando.
	 * @see presentacion.Mediador
	 * @see javax.swing.JMenuItem
	 * @see javax.swing.JMenuItem#addActionListener(ActionListener)
	 */
	public CJMAyudaContenidos(Mediador mediador) {
		// Lamamos al constructor de la superclase
		super("Contenidos");
		KeyStroke ctrlF1 = KeyStroke.getKeyStroke("F1");
		this.setAccelerator(ctrlF1); // Indicamos el receptor de la accion
		// Indicamos el receptor de la accion
		this.mediador = mediador;
		// A�adimos un listener a este comando
		this.addActionListener(mediador);
	} // ComandoJMIrAyudaContendios

	// Metodo
	/**
	 * Implementa el comportamiento de este elemento de men� que se encarga de
	 * abrir la ayuda de la aplicaci�n a trav�s del mediador.
	 * 
	 * @see presentacion.Mediador#irAAyuda()
	 */
	public void ejecutar() {
		mediador.irAAyuda();
	} // ejecutar
} // clase CJMAyudaContenidos

