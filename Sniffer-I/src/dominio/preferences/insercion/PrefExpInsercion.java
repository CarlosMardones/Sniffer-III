package dominio.preferences.insercion;

import presentacion.Mediador;
import dominio.preferences.preferencesBeanDefinicion;
import dominio.preferences.preferencesBeanIdentificacion;
import dominio.preferences.preferencesFileRead;
import java.io.File;
import java.util.ArrayList;

/**
 * Clase que define una lista de las direcciones ip, mac y timestamp de cada paqute capturado.
 *
 * @author Carlos Mardones Muga
 * @version 1.0
 * 
 */

public class PrefExpInsercion {

	/**
	 * Clase que contiene la direcion donde se almacenan las definiciones xml
	 * de los protocolos.
	 */
	private File fich;
	
	/**
	 * Elemento que permite recoger los datos del fichero de exportaciones.
	 */
	private preferencesFileRead leer = new preferencesFileRead();
	
	public PrefExpInsercion(){
	}
	
		
	
}

