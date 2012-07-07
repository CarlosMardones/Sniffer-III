package presentacion.comandos;

import java.awt.event.ActionListener;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import javax.swing.JMenuItem;
import javax.swing.KeyStroke;

import presentacion.Mediador;

/**
 * JMenuItem Abre ficheros de distintos tipos
 * 
 * @author Leonardo García & JoseRamón Gutierrez
 * @version 2.0
 * 
 * @see Comando
 * @see javax.swing.JMenuItem
 */
public class CJMAbrirFichero extends JMenuItem implements Comando {

	// Atributos
	/**
	 * Receptor del comando.
	 * 
	 * @see presentacion.Mediador
	 */
	private Mediador mediador;

	/**
	 * Tipo de fichero que se quiere cargar y mostrar por pantalla.
	 */
	private String titulo;

	// Constructor
	/**
	 * 
	 * 
	 * @param mediador
	 *            receptor del comando
	 * @param titulo
	 *            contenido del <code>JMenuItem</code>
	 * 
	 * @see presentacion.Mediador
	 * @see javax.swing.JMenuItem
	 * @see javax.swing.JMenuItem#addActionListener(ActionListener)
	 */
	public CJMAbrirFichero(Mediador mediador, String titulo) {
		// Lamamos al constructor de la superclase
		super(titulo);
		if (titulo.equals("Abrir fichero de Capturas...")) {
			this.setMnemonic('A');
			KeyStroke ctrlA = KeyStroke.getKeyStroke(KeyEvent.VK_A,
					InputEvent.CTRL_MASK);
			this.setAccelerator(ctrlA);
		}
		if (titulo.equals("Cargar fichero de preferecias...")) {
			this.setMnemonic('P');
			KeyStroke ctrlP = KeyStroke.getKeyStroke(KeyEvent.VK_P,
					InputEvent.CTRL_MASK);
			this.setAccelerator(ctrlP);
		}
		this.titulo = titulo;
		// Indicamos el receptor de la accion
		this.mediador = mediador;
		// Añadimos un listener a este comando
		this.addActionListener(mediador);
	} // ComandoJMAbrirFichero

	// Metodo
	/**
	 * Implementa el comportamiento de este elemento de menú que se encarga de
	 * cargar un fichero y mostrarlo en la ventana a
	 * través del mediador.
	 * 
	 * @see presentacion.Mediador#irAVentanaAbrirElegirFichero(String)
	 */
	public void ejecutar() {
		mediador.irAAbrirElegirFichero(titulo);
	} // ejecutar
} // clase CJMAbrirFichero
