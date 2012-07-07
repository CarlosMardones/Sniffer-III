package presentacion.comandos;

import java.awt.*;
import java.awt.event.*;

import javax.swing.*;

import presentacion.*;
import presentacion.preferencias.PreferenciasInsercion;

/**
 * Boton Chequear
 * 
 * @author  Carlos Mardones Muga
 * @version 1.0
 * 
 * @see javax.swing.JButton
 * @see Comando
 */
public class CBChequear extends JButton implements Comando {

	// Atributos
	// Declaracion de un mediador.
	/**
	 * Receptor del comando.
	 * 
	 * @see presentacion.Mediador
	 */
	private Mediador mediador;

	/**
	 * Titulo
	 */
	private String titulo;

	// Constructor
	/**
	 * Implementa el botón chequear según el parámetro introducido
	 * 
	 * @param mediador
	 *            receptor del comando
	 * @param ventana
	 *            ventana que llama a este comando. Valores que toma:
	 *            <code>"Analisis de Etiquetas","Filtrado de Etiquetas","Representación"</code>
	 * @see presentacion.Mediador
	 * @see javax.swing.JButton
	 * @see javax.swing.JButton#addActionListener(ActionListener)
	 */
	public CBChequear(Mediador mediador, String ventana) {
		// Llamamos al constructor de la superclase
		super();
			this.setText("   Chequear  ");
			this.setMnemonic('S');
		//this.setToolTipText("Acepta");
		this.setFont(new Font("Arial", Font.BOLD, 12));
		this.setAlignmentY(CENTER_ALIGNMENT);
		this.titulo = ventana;
		// Indicamos el receptor de la accion
		this.mediador = mediador;
		// Añadimos un listener a este comando
		this.addActionListener(mediador);

	} // ComandoBAceptarVentana

	// Metodos
	/**
	 * Implementa el comportamiento del botón chequear a través del mediador.
	 * 
	 
	 */
	public void ejecutar() {
		boolean estado=true;
			if (titulo.equals("ChequearDefinicionProtocolo")) { //botonAceptar
			mediador.ChequearDefinicionProtocolo();
			estado=false;
		}
	}
} // clase CBAceptar
