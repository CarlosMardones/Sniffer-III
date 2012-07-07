package presentacion.ventanaMenuSniffer;

import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import presentacion.Mediador;
import presentacion.comandos.CJMAbrirFichero;
import presentacion.comandos.CJMAyudaAcercaDe;
import presentacion.comandos.CJMAyudaContenidos;
import presentacion.comandos.CJMConfiguracion;
import presentacion.comandos.CJMFinCapture;
import presentacion.comandos.CJMGuardarFichero;
import presentacion.comandos.CJMInicioCapture;
import presentacion.comandos.CJMInicioDefinicion;
import presentacion.comandos.CJMInicioInsertCapturados;
import presentacion.comandos.CJMPreferenciasCaptura;
import presentacion.comandos.CJMPreferenciasExportar;
import presentacion.comandos.CJMPreferenciasFromFile;
import presentacion.comandos.CJMPreferenciasGenerar;
import presentacion.comandos.CJMSalir;
import presentacion.comandos.CJMInicioCaptureFromFile;
import presentacion.comandos.CJMInicioInsertDefinidos;
import presentacion.comandos.CJMFinInsert;
import presentacion.comandos.Comando;

/**
 * Menu de la pantalla principal de la aplicación.
 * 
 * @author Leonardo García & Jose Ramón Gutierrez & Carlos Mardones Muga
 * 
 * @version 3.0
 * @see javax.swing.JPanel
 * @see MenuSniffer
 * @see BarraHerramientas
 */
public class BarraMenu extends JPanel {
	// Definición de los menus.
	/**
	 * Menú que contiene las opciones de archivo.
	 */
	JMenu menuArchivo;
	/**
	 * Menú que contiene las opciones de Captura
	 */
	JMenu menuCaptura;
	/**
	 * Menú que contiene las opciones de definicion de paquetes
	 */
	JMenu menuDefinicion;
	/**
	 * Menú que contiene las opciones de insercion de paquetes
	 */
	JMenu menuInsercion;
	/**
	 * Menú que contiene las opciones de Parametrización
	 */
	JMenu menuParametrizacion;
	/**
	 * Menú que contiene las opciones de Preferencias
	 */
	JMenu menuPreferecias;
	/**
	 * Menú que contiene las opciones de ayuda. Contiene los submenús:
	 * <code>Ayuda... y Acerca de Sniffer II...</code>.
	 */
	JMenu menuAyuda;
	/**
	 * Menú que contiene las opciones de Exportación
	 */
	JMenu menuExportar;
	/**
	 * Menú que contiene las opciones de propiedades de captura
	 */
	JMenu menuPrefCaptura;
	/**
	 * Menú que contiene las opciones de propiedades de exportación.
	 */
	JMenu menuPrefExportar;
	/**
	 * Menú que contiene las opciones de propiedades de captura desde fichero.
	 */
	JMenu menuPrefFromFile;
	/**
	 * Menú que contiene las opciones de generar Script
	 */
	JMenu menuPrefGenerar;
	/**
	 * Barra de menús.
	 * 
	 * @see javax.swing.JMenuBar
	 */
	static JMenuBar menuBar;
	/**
	 * Elemento del menú.
	 * 
	 * @see javax.swing.JMenuItem
	 */
	JMenuItem menuItem;
	/**
	 * Receptor del comando.
	 * 
	 * @see presentacion.Mediador
	 */
	Mediador mediador;
	/**
	 * Comando encargado de abrir la ventana configuración.
	 * 
	 * @see presentacion.comandos.Comando
	 * @see presentacion.comandos.CJMConfiguracion
	 */
	Comando cJMConfiguracion;
	/**
	 * Comando encargado de abrir la ventana de abrir un fichero
	 * 
	 * @see presentacion.comandos.Comando
	 * @see presentacion.comandos.CJMAbrirFichero
	 */
	Comando cJMAbrirFichero;
	/**
	 * Comando encargado de abrir la ventana guardar un fichero.
	 * 
	 * @see presentacion.comandos.Comando
	 * @see presentacion.comandos.CJMGuardarFichero
	 */
	Comando cJMGuardarFicheroPcap;
	/**
	 * Comando encargado de abrir la ventana guardar un fichero.
	 * 
	 * @see presentacion.comandos.Comando
	 * @see presentacion.comandos.CJMGuardarFichero
	 */
	Comando cJMGuardarFicheroXML;
	/**
	 * Comando encargado de abrir la ventana guardar un fichero.
	 * 
	 * @see presentacion.comandos.Comando
	 * @see presentacion.comandos.CJMGuardarFichero
	 */
	Comando cJMGuardarDesdeFicheroAXML;
	/**
	 * Comando encargado de abrir la ventana salir.
	 * 
	 * @see presentacion.comandos.Comando
	 * @see presentacion.comandos.CBVentanaSalir
	 */
	Comando cJMSalir;
	/**
	 * Comando encargado de abrir la ventana de inicar captura.
	 * 
	 * @see presentacion.comandos.Comando
	 * @see presentacion.comandos.CJMInicioCapture
	 */
	Comando cJMInicioCapture;
	/**
	 * Comando encargado de abrir la ventana de inicar insercion de paquetes definidos.
	 * 
	 * @see presentacion.comandos.Comando
	 * @see presentacion.comandos.CJMInicioInsert
	 */
	Comando cJMInicioInsertDef;
	/**
	 * Comando encargado de abrir la ventana de inicar insercion de pauetes Capturados.
	 * 
	 * @see presentacion.comandos.Comando
	 * @see presentacion.comandos.CJMInicioInsert
	 */
	Comando cJMInicioInsertCap;
	/**
	 * Comando encargado de abrir la ventana de inicar definicion.
	 * 
	 * @see presentacion.comandos.Comando
	 * @see presentacion.comandos.CJMInicioInsert
	 */
	Comando cJMInicioDefinicion;
	/**
	 * Comando encargado de abrir la ventana finde captura .
	 * 
	 * @see presentacion.comandos.Comando
	 * @see presentacion.comandos.CJMFinCapture
	 */
	Comando cJMFinCapture;
	/**
	 * Comando encargado de abrir la ventana fin de insercion .
	 * 
	 * @see presentacion.comandos.Comando
	 * @see presentacion.comandos.CJMFinInsert
	 */
	Comando cJMFinInsert;
	/**
	 * Comando encargado de abrir la ventana inicio de captura.
	 * 
	 * @see presentacion.comandos.Comando
	 * @see presentacion.comandos.CJMInicioCaptureFromFile
	 */
	Comando cJMInicioCaptureFromFile;
	/**
	 * Comando encargado de abrir la ventana preferencias de captura.
	 * 
	 * @see presentacion.comandos.Comando
	 * @see presentacion.comandos.CJMPreferenciasCaptura
	 */
	Comando cJMPreferenciasCaptura;
	/**
	 * Comando encargado de abrir la ventana preferencias de captura desde fichero
	 * 
	 * @see presentacion.comandos.Comando
	 * @see presentacion.comandos.CJMPreferenciasFromFile
	 */
	Comando cJMPreferenciasFromFile;
	/**
	 * Comando encargado de abrir la ventana de generar script.
	 * 
	 * @see presentacion.comandos.Comando
	 * @see presentacion.comandos.CJMPreferenciasGenerar
	 */
	Comando cJMPreferenciasGenerar;
	/**
	 * Comando encargado de abrir la ventana preferencias de exportacío.
	 * 
	 * @see presentacion.comandos.Comando
	 * @see presentacion.comandos.CJMPreferenciasExportar
	 */
	Comando cJMPreferenciasExportar;
	/**
	 * Comando encargado de abrir la ventana ayuda de contenidos.
	 * 
	 * @see presentacion.comandos.Comando
	 * @see presentacion.comandos.CJMAyudaContenidos
	 */
	Comando cJMAyudaContenidos;
	/**
	 * Comando encargado de abrir la ventana ayuda acerca de.
	 * 
	 * @see presentacion.comandos.Comando
	 * @see presentacion.comandos.CJMAyudaAcercaDe
	 */
	Comando cJMAyudaAcercaDe;
	/**
	 * Comando encargado de abrir la ventana salir
	 * 
	 * @see presentacion.comandos.Comando
	 * @see presentacion.comandos.CBVentanaSalir
	 */


	//Constructor
	/**
	 * Genera una nueva barra de menús.
	 * 
	 * @see #menuBar
	 * @see #addMenus(JMenuBar)
	 */
	public BarraMenu(Mediador med) {
		//Crea la barra de menú.
		mediador = med;
		menuBar = new JMenuBar();
		menuBar.repaint();
		addMenus(menuBar);
		menuBar.repaint();

	}//BarraMenu

	/**
	 * Añade los elementos del menú a la barra de menús.
	 * 
	 * @param menuBar
	 *            asigna la barra de menús
	 */
	protected void addMenus(JMenuBar menuBar) {

		//=>Menu Archivo.
		menuArchivo = new JMenu("Archivo");
		menuBar.add(menuArchivo);
		//Menu Archivo - Abrir
		cJMAbrirFichero = new CJMAbrirFichero(mediador,
				"Abrir fichero de Capturas...");
		menuArchivo.add((CJMAbrirFichero) cJMAbrirFichero);
		//Menu Archivo - Guardar
		cJMGuardarFicheroPcap = new CJMGuardarFichero(mediador,
				"Guardar fichero capturado...");
		menuArchivo.add((CJMGuardarFichero) cJMGuardarFicheroPcap);
		//Menu Archivo - Separator
		menuArchivo.addSeparator();
		//Menu Archivo - Exportar
		menuExportar = new JMenu("Exportar");
		menuArchivo.add(menuExportar);
		//Menu Archivo - Exportar - Captura a XML
		cJMGuardarFicheroXML = new CJMGuardarFichero(mediador,
				"Captura a XML...");
		menuExportar.add((CJMGuardarFichero) cJMGuardarFicheroXML);
		//Menu Archivo - Exportar - desde fichero a XML
		cJMGuardarDesdeFicheroAXML = new CJMGuardarFichero(mediador,
		"... desde fichero a XML...");
		menuExportar.add((CJMGuardarFichero) cJMGuardarDesdeFicheroAXML);
		//Menu Archivo - Separator
		menuArchivo.addSeparator();
		//Menu Archivo - Configuracion
		cJMConfiguracion = new CJMConfiguracion(mediador);
		menuArchivo.add((CJMConfiguracion) cJMConfiguracion);
		//Menu Archivo - Separator
		menuArchivo.addSeparator();
		//Menu Archivo - Salir
		cJMSalir = new CJMSalir(mediador);
		menuArchivo.add((CJMSalir) cJMSalir);

		//=>Menu Captura
		menuCaptura = new JMenu("Captura");
		menuBar.add(menuCaptura);
		//Menu Captura - Iniciar Captura
		cJMInicioCapture = new CJMInicioCapture(mediador);
		menuCaptura.add((CJMInicioCapture) cJMInicioCapture);
		//Menu Captura - Parar Captura
		cJMFinCapture = new CJMFinCapture(mediador);
		menuCaptura.add((CJMFinCapture) cJMFinCapture);
		//Menu Captura - Separator
		menuCaptura.addSeparator();
		//Menu Captura - Visual Frame Captura
		cJMInicioCaptureFromFile = new CJMInicioCaptureFromFile(mediador);
		menuCaptura.add((CJMInicioCaptureFromFile) cJMInicioCaptureFromFile);
		
		//=>Menu Definicion
		menuDefinicion = new JMenu("Definicion");
		menuBar.add(menuDefinicion);
		//Menu Insercion - Definir paquetes
		cJMInicioDefinicion = new CJMInicioDefinicion(mediador);
		menuDefinicion.add((CJMInicioDefinicion) cJMInicioDefinicion);
		
		
		//=>Menu Insercion
		menuInsercion = new JMenu("Insercion");
		menuBar.add(menuInsercion);
		//Menu Insercion - Iniciar Insercion Definidos
		cJMInicioInsertDef = new CJMInicioInsertDefinidos(mediador);
		menuInsercion.add((CJMInicioInsertDefinidos) cJMInicioInsertDef);
		//Menu Insercion - Iniciar Insercion Capturados
		cJMInicioInsertCap = new CJMInicioInsertCapturados(mediador);
		menuInsercion.add((CJMInicioInsertCapturados) cJMInicioInsertCap);
		
		//=>Menu Preferencias
		menuParametrizacion = new JMenu("Parametrización");
		menuBar.add(menuParametrizacion);
		//Menu Preferencias - Captura
		cJMPreferenciasCaptura = new CJMPreferenciasCaptura(mediador);
		menuParametrizacion.add((CJMPreferenciasCaptura) cJMPreferenciasCaptura);
		//menuPrefCaptura = new JMenu("Captura");
		//menuParametrizacion.add(menuPrefCaptura);
		//cJMPreferenciasCaptura = new CJMPreferenciasCaptura(mediador);
		//menuPrefCaptura.add((CJMPreferenciasCaptura) cJMPreferenciasCaptura);
		
		//Menu Preferencias - Separator
		//menuParametrizacion.addSeparator();
		
		//Menu Preferencias - Exportar
		cJMPreferenciasExportar = new CJMPreferenciasExportar(mediador);
		menuParametrizacion.add((CJMPreferenciasExportar) cJMPreferenciasExportar);
		//menuPrefExportar = new JMenu("Exportar");
		//menuParametrizacion.add(menuPrefExportar);
		//cJMPreferenciasExportar = new CJMPreferenciasExportar(mediador);
		//menuPrefExportar.add((CJMPreferenciasExportar) cJMPreferenciasExportar);
		
		//Menu Preferencias - Separator
		//menuParametrizacion.addSeparator();
		//Menu Preferencias - Desde fichero
		cJMPreferenciasFromFile = new CJMPreferenciasFromFile(mediador);
		menuParametrizacion.add((CJMPreferenciasFromFile) cJMPreferenciasFromFile);
		//menuPrefFromFile = new JMenu("Desde ficheros");
		//menuParametrizacion.add(menuPrefFromFile);
		//cJMPreferenciasFromFile = new CJMPreferenciasFromFile(mediador);
		//menuPrefFromFile.add((CJMPreferenciasFromFile) cJMPreferenciasFromFile);
		
		//Menu Preferencias - Separator
		menuParametrizacion.addSeparator();
		
		//Menu Preferencias - GEnerar bat
		cJMPreferenciasGenerar = new CJMPreferenciasGenerar(mediador);
		menuParametrizacion.add((CJMPreferenciasGenerar) cJMPreferenciasGenerar);
		

		//=>Menu Ayuda
		menuAyuda = new JMenu("Ayuda");
		menuBar.add(menuAyuda);
		//Menu Ayuda - Contenidos
		cJMAyudaContenidos = new CJMAyudaContenidos(mediador);
		menuAyuda.add((CJMAyudaContenidos) cJMAyudaContenidos);
		//Menu Ayuda - Acerca de Sniffer II
		cJMAyudaAcercaDe = new CJMAyudaAcercaDe(mediador);
		menuAyuda.add((CJMAyudaAcercaDe) cJMAyudaAcercaDe);

		menuBar.show();
	}//addMenus

	/**
	 * Devuelve la barra de menús creada.
	 * 
	 * @return instancia de la barra de menús
	 * @see #menuBar
	 */
	public JMenuBar getMenu() {
		return menuBar;
	}//getMenu

	/**
	 * Deshabilita un componente del Menu.
	 * 
	 * @param posicion
	 *            posición del menu donde se encuentra el elemento que se quiere
	 *            habilitar.Entero del 0 al número de elementos.
	 * @param positem
	 *            del elemento a habilitar dentro del menu
	 * @see #menuBar
	 */
	public static void EnabledComponenteBarraMenus(int posicion, int positem, int pos, boolean bEstado) {
		JMenu aux;
		
		if (posicion < menuBar.getMenuCount()) {
			if (positem < menuBar.getMenu(posicion).getItemCount()) {
				aux= (JMenu)menuBar.getMenu(posicion).getMenuComponent(positem);
				if (pos < aux.getItemCount()){
					aux.getItem(pos).setEnabled(bEstado);
				} else {
					System.out
					.println("Te has salido del rango de componentes del sub-menu");
					menuBar.repaint();
				} 
			} else {
				System.out
				.println("Te has salido del rango de componentes del menu");
			}
		} else {
			System.out
			.println("Te has salido del rango de componentes de la barra de menus");
		}
	}//deshabilitarComponenteBarraMenus
	
	/**
	 * Deshabilita un componente del Menu.
	 * 
	 * @param posicion
	 *            posición del menu donde se encuentra el elemento que se quiere
	 *            habilitar.Entero del 0 al número de elementos.
	 * @param positem
	 *            del elemento a habilitar dentro del menu
	 * @see #menuBar
	 */
	public static void deshabilitarComponenteBarraMenus(int posicion,
			int positem) {
		if (posicion < menuBar.getMenuCount()) {
			if (positem < menuBar.getMenu(posicion).getItemCount()) {
				menuBar.getMenu(posicion).getItem(positem).setEnabled(false);
				menuBar.repaint();
			} else {
				System.out
						.println("Te has salido del rango de componentes del menu");
			}
		} else {
			System.out
					.println("Te has salido del rango de componentes de la barra de menus");
		}
	}//deshabilitarComponenteBarraMenus

	/**
	 * Habilita un elemento de la barra de menus.
	 * 
	 * @param posicion
	 *            posición del menu donde se encuentra el elemento que se quiere
	 *            habilitar.Entero del 0 al número de elementos.
	 * @param positem
	 *            del elemento a habilitar dentro del menu
	 * @see #menuBar
	 */
	public static void habilitarComponenteBarraMenus(int posicion, int positem) {
		if (posicion < menuBar.getMenuCount()) {
			if (positem < menuBar.getMenu(posicion).getItemCount()) {
				menuBar.getMenu(posicion).getItem(positem).setEnabled(true);
				menuBar.repaint();
			} else {
				System.out
						.println("Te has salido del rango de componentes del menu");
			}
		} else {
			System.out
					.println("Te has salido del rango de componentes de la barra de menus");
		}
	}//habilitarComponeneteBarraMenus

	/**
	 * Deshabilita un menu de la barra de menus.
	 * 
	 * @param posicion
	 *            posición del menu que se quiere habilitar.Entero del 0 al
	 *            número de elementos
	 * @see #menuBar
	 */
	public static void deshabilitarMenuBarraMenus(int posicion) {
		if (posicion < menuBar.getMenuCount()) {
			menuBar.getMenu(posicion).setEnabled(false);
			menuBar.repaint();
		} else {
			System.out
					.println("Te has salido del rango de componentes de la barra de menus");
		}
	}//deshabilitarMenuBarraMenus

	/**
	 * Habilita un menu de la barra de menus.
	 * 
	 * @param posicion
	 *            posición del menu que se quiere habilitar.Entero del 0 al
	 *            número de elementos.
	 * @see #menuBar
	 */
	public static void habilitarMenuBarraMenus(int posicion) {
		if (posicion < menuBar.getMenuCount()) {
			menuBar.getMenu(posicion).setEnabled(true);
			menuBar.repaint();
		} else {
			System.out
					.println("Te has salido del rango de componentes de la barra de menus");
		}
	}//habilitarMenuBarraMenus
}//Clase BarraMenu
