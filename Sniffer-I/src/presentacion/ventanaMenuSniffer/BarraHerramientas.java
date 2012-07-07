package presentacion.ventanaMenuSniffer;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JToolBar;
import presentacion.Mediador;
import presentacion.comandos.CBAbrirFichero;
import presentacion.comandos.CBGuardarFichero;
import presentacion.comandos.CBConfiguracion;
import presentacion.comandos.CBInicioCapture;
import presentacion.comandos.CBInicioInsert;
import presentacion.comandos.CBInicioDefinicion;
import presentacion.comandos.CBFinInsert;
import presentacion.comandos.CBFinCapture;
import presentacion.comandos.CBInicioInsertCap;
import presentacion.comandos.CB_Prueba;
import presentacion.comandos.CB_Prueba2;
import presentacion.comandos.CBVentanaAyuda;
import presentacion.comandos.Comando;

/**
 * Barra de herramnientas de la pantalla principal de la aplicación.
 * 
 * @author Leonardo García & Jose Ramón Gutierrez & Carlos Mardones Muga
 * @version 3.0
 *  
 * @see javax.swing.JPanel
 * @see BarraMenu
 * @see MenuSniffer
 */
public class BarraHerramientas extends JPanel {
	/** * Barra de herramientas. * * @see javax.swing.JToolBar */
	static JToolBar toolBar;
	/** * Receptor del comando. *  * @see presentacion.Mediador */
	Mediador mediador;
	/**
	 * Comando encargado de abrir la ventana de inicio de captura de datos
	 * 
	 * @see presentacion.comandos.Comando
	 * @see presentacion.comandos.CBInicioCapture
	 */
	Comando cBIrInicioCapture;

	/**
	 * Comando encargado de parar la captura de datos
	 * 
	 * @see presentacion.comandos.Comando
	 * @see presentacion.comandos.CBFinCapture
	 */
	Comando cBFinCapture;
	/**
	 * Comando encargado de definir paquetes de datos
	 * 
	 * @see presentacion.comandos.Comando
	 * @see presentacion.comandos.CBFinCapture
	 */
	Comando cBIrInicioDefinicion;
	
	/**
	 * Comando encargado de insertar paquetes de datos
	 * 
	 * @see presentacion.comandos.Comando
	 * @see presentacion.comandos.CBFinCapture
	 */
	Comando cBIrInicioInsert;
	
	/**
	 * Comando encargado de insertar paquetes de datos capturados
	 * 
	 * @see presentacion.comandos.Comando
	 * @see presentacion.comandos.CBFinCapture
	 */
	Comando cBIrInicioInsertCap;
	/**
	 * Comando encargado de parar la insercion de datos
	 * 
	 * @see presentacion.comandos.Comando
	 * @see presentacion.comandos.CBFinCapture
	 */
	Comando cBIrFinInsert;
	
	/**
	 * Comando encargado de abrir un fichero según el tipo pasado
	 * 
	 * @see presentacion.comandos.Comando
	 * @see presentacion.comandos.CBAbrirFichero
	 */
	Comando cBAbrirFichero;

	/**
	 * Comando encargado de guardar un fichero según el tipo pasado
	 * 
	 * @see presentacion.comandos.Comando
	 * @see presentacion.comandos.CBGuardarFichero
	 */
	Comando cBGuardarFichero;

	/**
	 * Comando encargado de guardar un fichero según el tipo pasado
	 * 
	 * @see presentacion.comandos.Comando
	 * @see presentacion.comandos.CBGuardarFichero
	 */
	Comando cBExportarXMLFichero;

	/**
	 * Comando encargado de abrir la ventana de Ayuda.
	 * 
	 * @see presentacion.comandos.Comando
	 * @see presentacion.comandos.CBVentanaAyuda
	 */
	Comando cBVentanaAyuda;
	
	/**
	 * Comando encargado de abrir la ventana de Ayuda.
	 * 
	 * @see presentacion.comandos.Comando
	 * @see presentacion.comandos.CBVentanaAyuda
	 */
	Comando cBConfiguracion;

	/**
	 * Comando encargado de ejecutar una prueba
	 * <code>VentanaFuenteDatos</code>.
	 * 
	 * @see presentacion.comandos.Comando
	 * @see presentacion.comandos.CB_Prueba
	 */
	Comando cB_Prueba;
	
	/**
	 * Comando encargado de ejecutar una prueba
	 * <code>VentanaFuenteDatos</code>.
	 * 
	 * @see presentacion.comandos.Comando
	 * @see presentacion.comandos.CB_Prueba2
	 */
	Comando cB_Prueba2;

	//Constructor
	/**
	 * Genera una nueva barra de herramientas.
	 * 
	 * @see #addButtons(JToolBar)
	 * @see javax.swing.JToolBar
	 */
	public BarraHerramientas(Mediador med) {
		//Crea la barra de herramientas.
		mediador = med;
		toolBar = new JToolBar();
		toolBar.setFloatable(false);
		addButtons(toolBar);
	}//BarraHerramientas

	/**
	 * Añade los botones de la barra de herramientas.
	 * 
	 * @param toolBar
	 *            asigna la barra de herramientas
	 * @see presentacion.comandos.CBInicioCapture
	 * @see presentacion.comandos.CBAbrirFichero
	 * @see presentacion.comandos.CBGuardarFichero
	 * @see presentacion.comandos.CBVentanaAyuda
	 * @see presentacion.Mediador
	 */
	protected void addButtons(JToolBar toolBar) {
		//Boton
		JButton button = null;
		//mediador=new Mediador();
		//primer boton
		cBIrInicioCapture = new CBInicioCapture(mediador);
		toolBar.add((CBInicioCapture) cBIrInicioCapture);
		//primer boton
		//cBFinCapture=new CBFinCapture(mediador);
		//toolBar.add((CBFinCapture)cBFinCapture);
		//segundo boton
		cBAbrirFichero = new CBAbrirFichero(mediador,
				"Abrir fichero de Capturas...");
		toolBar.add((CBAbrirFichero) cBAbrirFichero);
		//separator
		toolBar.addSeparator();
		
		//tercer boton
		cBGuardarFichero = new CBGuardarFichero(mediador,
				"Guardar fichero capturado...");
		toolBar.add((CBGuardarFichero) cBGuardarFichero);
		//cuarto boton Exportar fichero...
		cBExportarXMLFichero = new CBGuardarFichero(mediador,
				"Exportar fichero a XML...");
		toolBar.add((CBGuardarFichero) cBExportarXMLFichero);
		//separator
		toolBar.addSeparator();
		//quinto boton
		cBIrInicioDefinicion = new CBInicioDefinicion(mediador);
		toolBar.add((CBInicioDefinicion) cBIrInicioDefinicion);
		//separator
		toolBar.addSeparator();
        //sexto boton
		cBIrInicioInsert = new CBInicioInsert(mediador);
		toolBar.add((CBInicioInsert) cBIrInicioInsert);
		//septimo boton
		cBIrInicioInsertCap = new CBInicioInsertCap(mediador);
		toolBar.add((CBInicioInsertCap) cBIrInicioInsertCap);
		toolBar.addSeparator();
		//octavo boton
		cBVentanaAyuda = new CBVentanaAyuda(mediador);
		toolBar.add((CBVentanaAyuda) cBVentanaAyuda);
		//separator
		toolBar.addSeparator();
		//noveno boton
		cBConfiguracion = new CBConfiguracion(mediador);
		toolBar.add((CBConfiguracion) cBConfiguracion);
		toolBar.addSeparator();
	}//addButtons

	/**
	 * Devuelve la barra de herramientas creada.
	 * 
	 * @return instancia de la barra de herramientas
	 * @see #toolBar
	 */
	public static JToolBar getBarraHerramientas() {
		return toolBar;
	}//getBarraHerramientas

	/**
	 * Deshabilita un componente de la barra de herramientas.
	 * 
	 * @param posicion
	 *            posición del elemento de la barra de herramientas que se
	 *            quiere deshabilitar.Entero del 0 al número de elementos.
	 */
	public static void deshabilitarComponenteBarraHerramientas(int posicion) {
		if (posicion < toolBar.getComponentCount()) {
			toolBar.getComponentAtIndex(posicion).setEnabled(false);
			toolBar.repaint();
		} else {
			System.out
					.println("Te has salido del rango de componentes de la barra de herramientas");
		}
	}//deshabilitarComponenteBarraHerramientas

	/**
	 * Habilita un componente de la barra de herramientas.
	 * 
	 * @param posicion
	 *            posición del elemento de la barra de herramientas que se
	 *            quiere habilitar.Entero del 0 al número de elementos.
	 */
	public static void habilitarComponenteBarraHerramientas(int posicion) {
		if (posicion < toolBar.getComponentCount()) {
			toolBar.getComponentAtIndex(posicion).setEnabled(true);
			toolBar.repaint();
		} else {
			System.out
					.println("Te has salido del rango de componentes de la barra de herramientas");
		}
	}//habilitarComponeneteBarraHerramientas
}//Clase BarraHerramientas
