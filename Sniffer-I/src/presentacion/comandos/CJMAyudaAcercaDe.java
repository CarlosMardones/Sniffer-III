package presentacion.comandos;

import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import javax.swing.JMenuItem;
import javax.swing.KeyStroke;

import presentacion.Mediador;

/**
 * JMenuItem abre la ventana ayuda acerda de...
 * 
 * @author Leonardo García & JoseRamón Gutierrez & Carlos Mardones Muga
 * @version 2.0
 * 
 * @see Comando
 * @see javax.swing.JMenuItem
 */
public class CJMAyudaAcercaDe extends JMenuItem implements Comando {

	// Atributos
	/**
	 * Receptor del comando.
	 * 
	 * @see presentacion.Mediador
	 */
	private Mediador mediador;

	// Constructor
	/**
	 * ir a la ventana "Ayuda acerca de" .
	 * 
	 * @param mediador
	 *            receptor del comando
	 * @see presentacion.Mediador
	 * @see javax.swing.JMenuItem
	 * @see javax.swing.JMenuItem#addActionListener(ActionListener)
	 */
	public CJMAyudaAcercaDe(Mediador mediador) {
		// Lamamos al constructor de la superclase
		super("Acerca de Sniffer II", KeyEvent.VK_A);
		KeyStroke ctrlA = KeyStroke.getKeyStroke("F2");
		this.setAccelerator(ctrlA); // Indicamos el receptor de la accion
		// Indicamos el receptor de la accion
		this.mediador = mediador;
		// Añadimos un listener a este comando
		this.addActionListener(mediador);
	} // ComandoIrAyudaAcercaDe

	// Metodo
	/**
	 * ir a la ventana "Ayuda acerca de" .
	 * 
	 * @see presentacion.Mediador#irAAyudaAcercaDe()
	 */
	public void ejecutar() {
		mediador.irAAyudaAcercaDe();
	} // ejecutar
} // clase CJMAyudaAcercaDe

