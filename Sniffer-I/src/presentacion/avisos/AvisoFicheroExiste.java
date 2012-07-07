package presentacion.avisos;

import java.io.File;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

/**
 * Panel grafico que se muestra cuando se intenta guradar un fichero con el
 * nombre de otro fichero ya existente.
 * 
 * @author Leonardo García & JoseRamón Gutierrez
 * @version 2.0
 * 
 * @see javax.swing.JDialog
 */
public class AvisoFicheroExiste extends JDialog {
	/**
	 * Fichero del que se quiere sacar el mensaje.
	 * 
	 * @see java.io.File
	 */
	File fichero;

	/**
	 * Respuesta que se da a este aviso.
	 */
	String respuesta = null;

	//Constructor
	/**
	 * Implementa un panel que permite confirmar o no si se desea sobreescribir
	 * el fichero existente o no.
	 * 
	 * @param fichero
	 *            nombre y ruta del fichero del que quiere sacar el mensaje
	 * @see #aviso()
	 */
	public AvisoFicheroExiste(File fichero) {
		this.fichero = fichero;
		aviso();
	}//AvisoFicheroExiste

	//Métodos
	/**
	 * Crea el panel de confirmación de fichero ya existente.
	 * 
	 * @see javax.swing.JFrame
	 * @see javax.swing.JOptionPane
	 */
	public void aviso() {
		JFrame frame = new JFrame();
		int res = JOptionPane
				.showConfirmDialog(
						frame,
						"<html>Existe el fichero introducido:<br> "
								+ fichero.toString()
								+ ".<br>Si continua se borran los datos del fichero.<br>¿Desea continuar?</html>",
						"Fichero existente", JOptionPane.YES_NO_OPTION);
		//variable que almacena la respuesta de usuario
		if (res == JOptionPane.YES_OPTION) {
			respuesta = "Si";
		} else {
			respuesta = "No";
		}//else
	}//aviso

	/**
	 * Respuesta pulsada en el aviso.
	 * 
	 * @return texto con la respuesta dada segun el botón pulsado. Valores que
	 *         toma: <code> "Si"</code> o <code> "No"</code>
	 */
	public String getrespuesta() {
		return respuesta;
	}//getrespuesta
}//clase AvisoFicheroExiste
