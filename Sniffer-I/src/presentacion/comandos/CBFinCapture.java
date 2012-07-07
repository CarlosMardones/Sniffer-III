package presentacion.comandos;

import java.awt.event.ActionListener;
import javax.swing.ImageIcon;
import javax.swing.JButton;

import presentacion.Mediador;
import servicioAccesoDatos.FachadaFicheroDirectorios;

/**
 * Boton de fin de captura
 * 
 * @author Leonardo García & JoseRamón Gutierrez
 * @version 2.0
 * 
 * @see javax.swing.JButton
 * @see Comando
 */
public class CBFinCapture extends JButton implements Comando {

	// Atributos
	/**
	 * Receptor del comando.
	 * 
	 * @see presentacion.Mediador
	 */
	private Mediador mediador;

	// Constructor

	/**
	 * Implemente el bonton de parada de captura
	 * 
	 * @param mediador
	 *            receptor del comando
	 * @see presentacion.Mediador
	 * @see javax.swing.JButton
	 * @see javax.swing.JButton#addActionListener(ActionListener)
	 */
	public CBFinCapture(Mediador mediador) {
		// Llamamos al constructor de la superclase
		super(new ImageIcon(
				FachadaFicheroDirectorios.getdirectorioData("DIR_IMAGES") + 
				System.getProperty("file.separator") + "fin.png"));
		this.setToolTipText("Parar Captura");
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
	 * Termina la captura a través del mediador.
	 * 
	 * @see presentacion.Mediador#irFinCapture
	 */
	public void ejecutar() {
		mediador.irFinCapture();
		System.out.println("CBFinCapture => Ejecutar");
	} // ejecutar
} // clase CBInicioCapture

