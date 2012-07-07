package presentacion.comandos;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import presentacion.*;

/**
 * Bot�n que abre la ventana para salir de la aplicaci�n.
 * 
 * @author Leonardo Garc�a & JoseRam�n Gutierrez
 * @version 2.0
 * 
 * @see javax.swing.JButton
 * @see Comando
 */
public class CBIniciarSalirAplicacion extends JButton implements Comando {

	// Atributos
	/**
	 * Receptor del comando.
	 * 
	 * @see presentacion.Mediador
	 */
	private Mediador mediador;

	/**
	 * Texto que se quiere mostar en el bot�n.
	 */
	private String bnombre;

	// Constructor
	/**
	 * Implementa el bot�n para iniciar o salir de la aplicaci�n.
	 * 
	 * @param mediador
	 *            receptor del comando
	 * @param bnombre
	 *            nombre del bot�n emisor del comando
	 * @param icono
	 *            icono del bot�n emisor del comando
	 * @see presentacion.Mediador
	 * @see javax.swing.JButton
	 * @see javax.swing.JButton#addActionListener(ActionListener)
	 * @see javax.swing.JButton#addKeyListener(KeyListener)
	 */
	public CBIniciarSalirAplicacion(Mediador mediador, String bnombre,
			ImageIcon icono) {
		// Llamamos al constructor de la superclase
		super(bnombre, icono);
		this.bnombre = bnombre;
		this.setFont(new Font("Arial", Font.BOLD, 16));
		this.setBorder(new javax.swing.border.BevelBorder(
				javax.swing.border.BevelBorder.RAISED));
		// Indicamos el receptor de la accion
		this.mediador = mediador;
		this.addKeyListener(new KeyAdapter() {
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == 10) { //10 es enter
					ejecutar();//aqui va ingresar();
				}
			}
		});

		// A�adimos un listener a este comando
		this.addActionListener(mediador);
		this.requestFocus(true);
	} // ComandoIniciarSalirAplicacion

	/**
	 * Implementa el bot�n para iniciar o salir de la aplicaci�n.
	 * 
	 * @param mediador
	 *            receptor del comando
	 * @param bnombre
	 *            nombre del bot�n emisor del comando
	 */
	public CBIniciarSalirAplicacion(Mediador mediador, String bnombre) {
		// Llamamos al constructor de la superclase
		super(bnombre);
		this.bnombre = bnombre;
		this.setFont(new Font("Arial", Font.BOLD, 16));
		this.setBorder(new javax.swing.border.BevelBorder(
				javax.swing.border.BevelBorder.RAISED));
		// Indicamos el receptor de la accion
		this.mediador = mediador;
		this.addKeyListener(new KeyAdapter() {
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == 10) { //10 es enter
					ejecutar();//aqui va ingresar();
				}
			}
		});

		// A�adimos un listener a este comando
		this.addActionListener(mediador);
		this.requestFocus(true);
	} // ComandoIniciarSalirAplicacion

	// Metodo
	/**
	 * Implementa el comportamiento del bot�n para ir a la ventana
	 * correspondiente seg�n el nombre del bot�n.Si se inicia la aplicaci�n se
	 * comprueba que exista un fichero de directorios y si existe se carga en
	 * memoria.
	 * 
	 * @see presentacion.Mediador#irAventana(String)
	 * @see presentacion.Mediador#cerrarVentana(Frame)
	 */
	public void ejecutar() {
		boolean salir = mediador.irAventana(bnombre);
		String barra = System.getProperty("file.separator");
		if (salir == false) {
			mediador.cerrarVentana(mediador.getVentanaPresentacion());
		}
	} // ejecutar
} // clase CBIniciarSalirAplicacion

