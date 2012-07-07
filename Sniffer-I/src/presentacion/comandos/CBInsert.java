package presentacion.comandos;


import java.awt.Font;
import java.awt.event.ActionListener;
import javax.swing.ImageIcon;
import javax.swing.JButton;

import presentacion.Mediador;
import presentacion.preferencias.PreferenciasInsercion;
import presentacion.preferencias.PreferenciasInsercionCapturados;
import servicioAccesoDatos.FachadaFicheroDirectorios;

/**
 * Abre ficheros de distintos tipos
 * captura realizada .pcap
 * 
 * @author Leonardo García & JoseRamón Gutierrez & Carlos Mardones Muga
 * @version 3.0
 * 
 * @see javax.swing.JButton
 * @see Comando
 */
public class CBInsert extends JButton implements Comando {

	// Atributos
	/**
	 * Receptor del comando.
	 * 
	 * @see presentacion.Mediador
	 */
	private Mediador mediador;

	/**
	 * Tipo de fichero 
	 */
	private String titulo;

	// Constructor

	/**
	 * Implementa el botón abrir fichero según el parámetro introducido
	 * 
	 * @param mediador
	 *            receptor del comando
	 * @see presentacion.Mediador
	 * @see javax.swing.JButton
	 * @see javax.swing.JButton#addActionListener(ActionListener)
	 */
	public CBInsert(Mediador mediador, String titulo) {
		// Llamamos al constructor de la superclase
		super();
		if (titulo.equals("Insertar Paquetes Capturados")) {
			this.setIcon(new ImageIcon(
					FachadaFicheroDirectorios.getdirectorioData("DIR_IMAGES") + 
					System.getProperty("file.separator") + 
					"open.png"));
			this.setToolTipText("Insertar Paquetes Capturados");
			this.setMnemonic('A');
		}
		if (titulo.equals("Insertar Paquetes Definidos")) {
			this.setIcon(new ImageIcon(
					FachadaFicheroDirectorios.getdirectorioData("DIR_IMAGES") + 
					System.getProperty("file.separator") + 
					"open.png"));
			this.setToolTipText("Insertar Paquetes Definidos");
			this.setMnemonic('D');
		}		
		this.setAlignmentY(CENTER_ALIGNMENT);

		this.titulo = titulo;
		// Indicamos el receptor de la accion
		this.mediador = mediador;
		// Añadimos un listener a este comando
		this.addActionListener(mediador);
	} // ComandoIrFuenteDatos

	/**
	 * Abre la ventana de inicio de captura a través del mediador.
	 * 
	 * @see presentacion.Mediador#irAAbrirElegirFichero
	 * @see presentacion.Mediador#repaintVentana
	 */
	public void ejecutar() {
		if (titulo.equals("Insertar Paquetes Capturados")) {
			mediador.irAInsertarPaquetes(titulo,PreferenciasInsercionCapturados.getRepeticiones());
		}
		if (titulo.equals("Insertar Paquetes Definidos")){
			mediador.irAInsertarPaquetes(titulo,PreferenciasInsercion.getEnvios());
		}
		
	} // ejecutar
} // clase CBAbrirFichero

