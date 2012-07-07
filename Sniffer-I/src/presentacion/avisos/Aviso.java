package presentacion.avisos;

import java.awt.Component;

import javax.swing.ImageIcon;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

import servicioAccesoDatos.FachadaFicheroDirectorios;

/**
 * Aviso de error.

 * @author Leonardo García & JoseRamón Gutierrez
 * @version 2.0
 * 
 * @see javax.swing.JDialog
 */
public class Aviso extends JDialog {
	/**
	 * Mensaje a mostrar.
	 */
	String mensaje;

	/**
	 * Tipo de mensaje a mostrar.
	 */
	String tipo;

	//Constructor
	/**
	 * Crea un nuevo panel de aviso que nos informa de algún evento producido
	 * durante la ejecución del programa.
	 * 
	 * @param mensaje
	 *            texto que se quiere mostrar en el aviso
	 * @param tipo
	 *            tipomensaje tipo de mensaje que se quiere lanzar. Valores que
	 *            toma: <code>"Error","Advertencia","Informacion"</code>
	 * @see #aviso()
	 */
	public Aviso(String mensaje, String tipo) {
		this.mensaje = mensaje;
		this.tipo = tipo;
		aviso();
	}//Aviso

	//Métodos
	/**
	 * Crea el panel de aviso.
	 * 
	 * @see javax.swing.JFrame
	 * @see javax.swing.JOptionPane
	 * @see javax.swing.JOptionPane#showMessageDialog(Component,Object,String,
	 *      int)
	 */
	public void aviso() {
		JFrame frame = new JFrame();
		if (tipo.equals("Error")) {
			JOptionPane.showMessageDialog(frame, mensaje, "Error",
					JOptionPane.ERROR_MESSAGE);
		} else {
			if (tipo.equals("Advertencia")) {
				JOptionPane.showMessageDialog(frame, mensaje, "Atención",
						JOptionPane.WARNING_MESSAGE);
			} else {
				if (tipo.equals("Informacion")) {
					JOptionPane.showMessageDialog(frame, mensaje,
							"Información", JOptionPane.INFORMATION_MESSAGE);
				} else {
					if (tipo.equals("Terminado")) {
						ImageIcon ico = new ImageIcon(
								FachadaFicheroDirectorios.getdirectorioData("DIR_IMAGES") + 
								System.getProperty("file.separator") + "tic.jpg");
						JOptionPane.showMessageDialog(frame, mensaje,
								"Mensaje", JOptionPane.PLAIN_MESSAGE, ico);

					} else {
						JOptionPane.showMessageDialog(frame, mensaje,
								"Mensaje", JOptionPane.PLAIN_MESSAGE);
					}

				}
			}
		}
	}//aviso
}//clase Aviso
