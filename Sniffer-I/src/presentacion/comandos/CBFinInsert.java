package presentacion.comandos;

import java.awt.event.ActionListener;
import javax.swing.ImageIcon;
import javax.swing.JButton;

import presentacion.Mediador;
import servicioAccesoDatos.FachadaFicheroDirectorios;

/**
 * implementa el boton de fin de insercion de paquetes
 * 
 * @author Carlos Mardones Muga
 * @version 1.0
 * 
 * @see javax.swing.JButton
 * @see Comando
 */
public class CBFinInsert extends JButton implements Comando {

	// Atributos
	/**
	 * Receptor del comando.
	 * 
	 * @see presentacion.Mediador
	 */
	private Mediador mediador;

	// Constructor

	/**
	 * Implemente el bonton de parada de insercion
	 * 
	 * @param mediador
	 *            receptor del comando
	 * @see presentacion.Mediador
	 * @see javax.swing.JButton
	 * @see javax.swing.JButton#addActionListener(ActionListener)
	 */
	public CBFinInsert(Mediador mediador) {
		// Llamamos al constructor de la superclase
		super(new ImageIcon(
				FachadaFicheroDirectorios.getdirectorioData("DIR_IMAGES") + 
				System.getProperty("file.separator") + "stop.png"));
		this.setToolTipText("Parar Insercion");
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
	 * Termina la insercion a través del mediador.
	 * 
	 * @see presentacion.Mediador#irFinCapture
	 */
	public void ejecutar() {
		
		System.out.println("pararEjecucion");
		mediador.irFinInsert();
		System.out.println("CBFinInsert => Ejecutar");
		mediador.cerrarVentana(mediador.getVentanaPresentacion());
	} // ejecutar
} // clase CBInicioCapture

