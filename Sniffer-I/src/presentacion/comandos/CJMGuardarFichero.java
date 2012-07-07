package presentacion.comandos;

import java.awt.event.ActionListener;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import javax.swing.JMenuItem;
import javax.swing.KeyStroke;

import presentacion.Mediador;

/**
 * JMenuItem abre la ventana para guardar fichero.
 * 
 * @author Leonardo García & JoseRamón Gutierrez
 * @version 2.0
 * 
 * @see Comando
 * @see javax.swing.JMenuItem
 */
public class CJMGuardarFichero extends JMenuItem implements Comando {

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
	 *   abre la ventana para guardar fichero de distintos tipos
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
	public CJMGuardarFichero(Mediador mediador, String titulo) {
		// Lamamos al constructor de la superclase
		super(titulo);
		if (titulo.equals("Guardar fichero capturado...")) {
			this.setMnemonic('G');
			KeyStroke ctrlG = KeyStroke.getKeyStroke(KeyEvent.VK_G,
					InputEvent.CTRL_MASK);
			this.setAccelerator(ctrlG);
		}
		if (titulo.equals("Guardar fichero de preferecias...")) {
			this.setMnemonic('F');
			KeyStroke ctrlF = KeyStroke.getKeyStroke(KeyEvent.VK_F,
					InputEvent.CTRL_MASK);
			this.setAccelerator(ctrlF);
		}
		if (titulo.equals("Captura a XML...")) {
			this.setMnemonic('X');
			KeyStroke ctrlX = KeyStroke.getKeyStroke(KeyEvent.VK_X,
					InputEvent.CTRL_MASK);
			this.setAccelerator(ctrlX);
		}
		if (titulo.equals("... desde fichero a XML...")) {
			this.setMnemonic('D');
			KeyStroke ctrlX = KeyStroke.getKeyStroke(KeyEvent.VK_D,
					InputEvent.CTRL_MASK);
			this.setAccelerator(ctrlX);
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
	 * cargar un fichero y mostrarlo en la ventana <code>MenuSniffer</code> a
	 * través del mediador.
	 * 
	 * @see presentacion.Mediador#irAVentanaAbrirElegirFichero(String)
	 */
	public void ejecutar() {
		
		if (titulo.equals("... desde fichero a XML...")) {
			mediador.irAExportFromFile(true);
		}
		else{
			mediador.irAGuardarElegirFichero(titulo);
		}
	} // ejecutar
} // clase CJMGuardarFichero
