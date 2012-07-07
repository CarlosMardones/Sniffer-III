package presentacion.comandos;

import java.awt.event.ActionListener;
import javax.swing.ImageIcon;
import javax.swing.JButton;

import presentacion.Mediador;
import servicioAccesoDatos.FachadaFicheroDirectorios;

/**
 * Botón para ir a la ventana de inicio de definicion de paquetes.
 * 
 * @author Carlos Mardones Muga
 * @version 1.0
 * 
 * @see javax.swing.JButton
 * @see Comando
 */
public class CBInicioDefinicion extends JButton implements Comando {
	// Atributos
	/**
	 * Receptor del comando.
	 * 
	 * @see presentacion.Mediador
	 */
	private Mediador mediador;

	// Constructor

	/**
	 * Implementa el botón para ir a la ventana de inicio de defincion de paquetes de datos.
	 * 
	 * @param mediador
	 *            receptor del comando
	 * @see presentacion.Mediador
	 * @see javax.swing.JButton
	 * @see javax.swing.JButton#addActionListener(ActionListener)
	 */
	public CBInicioDefinicion(Mediador mediador) {
		// Llamamos al constructor de la superclase
		super(new ImageIcon(
				FachadaFicheroDirectorios.getdirectorioData("DIR_IMAGES") + 
				System.getProperty("file.separator") + 
				"edit.png"));
		this.setToolTipText("Iniciar Definicion de Paquetes");
		this.setMnemonic('P');
		//this.setBorder(new
		// javax.swing.border.BevelBorder(javax.swing.border.BevelBorder.RAISED));
		this.setAlignmentY(CENTER_ALIGNMENT);
		// Indicamos el receptor de la accion
		this.mediador = mediador;
		// Añadimos un listener a este comando
		this.addActionListener(mediador);
	} // ComandoIrFuenteDatos

	// Metodo
	/**
	 * Abre la ventana de inicio de definicion de paqutes atraves del mediador.
	 * 
	 * @see presentacion.Mediador#irInicioInsert
	 */
	public void ejecutar() {
		mediador.irInicioDefinicion(true);
	} // ejecutar
} // clase 
