package presentacion.comandos;

import java.awt.event.ActionListener;
import javax.swing.ImageIcon;
import javax.swing.JButton;

import presentacion.Mediador;
import servicioAccesoDatos.FachadaFicheroDirectorios;

/**
 * Bot�n para ir a la ventana de inicio de captura.
 * 
 * @author Leonardo Garc�a & JoseRam�n Gutierrez
 * @version 2.0
 * 
 * @see javax.swing.JButton
 * @see Comando
 */
public class CBInicioCapture extends JButton implements Comando {

	// Atributos
	/**
	 * Receptor del comando.
	 * 
	 * @see presentacion.Mediador
	 */
	private Mediador mediador;

	// Constructor

	/**
	 * Implementa el bot�n para ir a la ventana de inicio de captura de datos.
	 * 
	 * @param mediador
	 *            receptor del comando
	 * @see presentacion.Mediador
	 * @see javax.swing.JButton
	 * @see javax.swing.JButton#addActionListener(ActionListener)
	 */
	public CBInicioCapture(Mediador mediador) {
		// Llamamos al constructor de la superclase
		super(new ImageIcon(
				FachadaFicheroDirectorios.getdirectorioData("DIR_IMAGES") + 
				System.getProperty("file.separator") + 
				"inicio.png"));
		this.setToolTipText("Iniciar Captura");
		this.setMnemonic('I');
		//this.setBorder(new
		// javax.swing.border.BevelBorder(javax.swing.border.BevelBorder.RAISED));
		this.setAlignmentY(CENTER_ALIGNMENT);
		// Indicamos el receptor de la accion
		this.mediador = mediador;
		// A�adimos un listener a este comando
		this.addActionListener(mediador);
	} // ComandoIrFuenteDatos

	// Metodo
	/**
	 * Abre la ventana de inicio de captura a trav�s del mediador.
	 * 
	 * @see presentacion.Mediador#irInicioCapture
	 */
	public void ejecutar() {
		mediador.irInicioCapture(true);
	} // ejecutar
} // clase CBInicioCapture

