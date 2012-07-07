package presentacion.comandos;

import java.awt.*;
import java.awt.event.*;

import javax.swing.*;

import presentacion.*;
import presentacion.preferencias.PreferenciasInsercion;

/**
 * Boton aceptar
 * 
 * @author Leonardo García & JoseRamón Gutierrez & Carlos Mardones Muga
 * @version 3.0
 * 
 * @see javax.swing.JButton
 * @see Comando
 */
public class CBAceptar extends JButton implements Comando {

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
	 * Implementa el botón aceptar según el parámetro introducido
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
	public CBAceptar(Mediador mediador, String ventana) {
		// Llamamos al constructor de la superclase
		super();
		if (ventana.toLowerCase().equals("salir")){
			this.setText("   Salir   ");
			this.setMnemonic('S');
		}
		else{
			this.setText("Aceptar");
			this.setMnemonic('A');
		}
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
	 * Implementa el comportamiento del botón aceptar a través del mediador.
	 * 
	 * @see presentacion.Mediador#botonAceptarInicoCapturaDumper
	 * @see presentacion.Mediador#botonAceptarConfiguracion
	 * @see presentacion.Mediador#botonAceptarInicoCapturaDumperFromFile
	 * @see presentacion.Mediador#GenerarBatCaptura
	 * @see presentacion.Mediador#GenerarXMLFromFile
	 * @see presentacion.Mediador#cerrarVentana(Frame)
	 */
	public void ejecutar() {
		boolean estado=true;
		if (titulo.equals("AceptarInicioCaptura")) { //botonAceptar
			//mediador.botonAceptarInicoCaptura();
			estado = mediador.botonAceptarInicoCapturaDumper();
			
		}
		if (titulo.equals("AceptarConfiguracion")) { //botonAceptar
			mediador.botonAceptarConfiguracion();
			//mediador.botonAceptarInicoCapturaDumper();
			
		}
		if (titulo.equals("AceptarInicioCapturaFromFile")) { //botonAceptar
			//mediador.botonAceptarInicoCaptura();
			mediador.botonAceptarInicoCapturaDumperFromFile();
			
		}
		if (titulo.equals("GenerarScript")) { //botonAceptar
			mediador.GenerarBatCaptura();
		}
		if (titulo.equals("GenerarXML")) { //botonAceptar
			mediador.GenerarXMLFromFile();
		}
		if (titulo.equals("GenerarXMLProtocolo")) { //botonAceptar
			mediador.GenerarXMLProtocolo();
		}
		if (titulo.equals("ChequearDefinicionProtocolo")) { //botonAceptar
			mediador.ChequearDefinicionProtocolo();
			estado=false;
		}
		if (titulo.equals("RefrescarTablaProtocolo")) { //botonAceptar
			mediador.ChequearIdentificacionProtocolo();
			estado=false;
		}
		if (titulo.equals("Insertar Paquetes Capturados")) { //botonAceptar
			mediador.irAInsertarPaquetes("Insertar Paquetes Capturados",PreferenciasInsercion.getEnvios());
			estado=false;
		}
		if (titulo.equals("Insertar Paquetes Definidos")) { //botonAceptar
			mediador.irAInsertarPaquetes("Insertar Paquetes Definidos",PreferenciasInsercion.getEnvios());
			estado=false;
		}
		
		if (estado==true)
			mediador.cerrarVentana(mediador.getVentanaPresentacion());
	} // ejecutar
} // clase CBAceptar
