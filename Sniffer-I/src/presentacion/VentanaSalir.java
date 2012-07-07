package presentacion;

import javax.swing.*;
import presentacion.ventanaMenuSniffer.*;


/**
 * Ventana que que contiene un panel para confiramar la salida de la aplicación.
 * 
 * @author Leonardo García & JoseRamón Gutierrez
 * @version 2.0
 * 
 * @see javax.swing.JDialog
 */
public class VentanaSalir extends JDialog {
	/**
	 * Receptor
	 */
	Mediador venpadre;
	
	//Constructor
	/**
	 * Genera un nuevo panel que permite confirmar si se desea salir de la
	 * aplicación.
	 * 
	 * @see #confirmarSalida()
	 * @see presentacion.comandos.CBWindowClosing
	 */
	public VentanaSalir(Mediador aux) {
		super(MenuSniffer.getFrames()[0], "Salir", true);
		venpadre = aux;
		confirmarSalida();
		this.setResizable(false);
	}//VentanaSalir

	//Métodos
	/**
	 * Recoger la respuesta a confirmar salida.
	 * 
	 * @see javax.swing.JOptionPane
	 */
	public void confirmarSalida() {
		//variable auxiliar
		int res = JOptionPane.showConfirmDialog(this,
				"¿Está seguro de que desea Salir?", "Sniffer II",
				JOptionPane.YES_NO_OPTION);
		//variable que almacena la respuesta de usuario
		String respuesta = null;

		if (res == JOptionPane.YES_OPTION) {
			respuesta = "Si";
			venpadre.grabarProperties();
			System.exit(1);
		} else {
			respuesta = "No";
			this.dispose();
		}//else
	}//confirmarSalida
}//Clase Salir
