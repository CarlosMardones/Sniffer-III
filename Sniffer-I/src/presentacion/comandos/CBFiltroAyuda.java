package presentacion.comandos;

import java.awt.event.ActionListener;
import javax.swing.ImageIcon;
import javax.swing.JButton;

import presentacion.Mediador;
import servicioAccesoDatos.FachadaFicheroDirectorios;

/**
 * Boton de ayuda de filtro
 * 
 * @author Leonardo García & JoseRamón Gutierrez
 * @version 2.0
 * 
 * @see javax.swing.JButton
 * @see Comando
 */
public class CBFiltroAyuda extends JButton implements Comando {

	// Atributos
	/**
	 * Receptor del comando.
	 * 
	 * @see presentacion.Mediador
	 */
	private Mediador mediador;

	// Constructor
	/**
	 * Implementa el botón de ir a la ayuda de filtro.
	 * 
	 * @param mediador
	 *            receptor del comando.
	 * @see presentacion.Mediador
	 * @see javax.swing.JButton
	 * @see javax.swing.JButton#addActionListener(ActionListener)
	 */
	public CBFiltroAyuda(Mediador mediador) {
		// Llamamos al constructor de la superclase
		super(new ImageIcon(
				FachadaFicheroDirectorios.getdirectorioData("DIR_IMAGES") + 
				System.getProperty("file.separator") + "help.png"));
		this.setToolTipText("Ayuda filtro");
		this.setAlignmentY(CENTER_ALIGNMENT);
		// Indicamos el receptor de la accion
		this.mediador = mediador;
		// Añadimos un listener a este comando
		this.addActionListener(mediador);
	} // ComandoBIrVentanaAyuda

	// Metodos setEnabled
	
	/**
	 * Estblece si esta activo o no
	 * 
	 * @param aux estado
	 */
	public void setEnab(boolean aux) {
		this.setEnabled(aux);
	} // ejecutar

	/**
	 * Abre la ventana de filtro mediante el mediador.
	 * 
	 * @see presentacion.Mediador#irAAyudaFilter()
	 */
	public void ejecutar() {
		mediador.irAAyudaFilter();
	} // ejecutar
} // clase CBFiltroAyuda

