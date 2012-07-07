package presentacion.propiedadesVentana;

import java.awt.*;

/**
 * Clase encargada de centrar una determinada ventana en la pantalla.
 * 
 * @author Leonardo García & Jose Ramón Gutierrez
 * @version 2.0
 *  
 */
public class CentrarVentana {
	/**
	 * Centra la ventana en la pantalla.
	 * 
	 * @param o
	 *            ventana que se quiere centrar
	 */
	public CentrarVentana(Window o) {
		Dimension tamanoPantalla = Toolkit.getDefaultToolkit().getScreenSize();
		if (o.getHeight() > tamanoPantalla.height)
			o.setSize(o.getWidth(), tamanoPantalla.height);
		if (o.getWidth() > tamanoPantalla.width)
			o.setSize(tamanoPantalla.width, o.getHeight());
		o.setLocation((tamanoPantalla.width - o.getWidth()) / 2,
				(tamanoPantalla.height - o.getHeight()) / 2);
	}//CentrarVentana
}//Clase CentrarVentana
