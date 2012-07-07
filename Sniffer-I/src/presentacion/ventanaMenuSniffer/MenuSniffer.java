package presentacion.ventanaMenuSniffer;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

import java.util.Vector;

import presentacion.visualizarCaptura.*;
import presentacion.*;
import servicioAccesoDatos.FachadaFicheroDirectorios;

/**
 * Interface principal de aplicación. Esta compuesta por una barra de
 * herramientas, una barra de menus, un panel en el que se muestra los datos de
 * captura (paquetest y conexiones) y un panel inferior donde se detalla en forma
 * de árbol el paquete seleccionado.
 * 
 * @author Leonardo García & Jose Ramón Gutierrez
 * @version 2.0
 *  
 * @see javax.swing.JFrame
 * @see BarraMenu
 * @see BarraHerramientas
 */
public class MenuSniffer extends JFrame {
	/** * Panel de estado en el que se muestra el estado de la aplicación. */
	JPanel statusPanel;
	/** * Etiqueta del panel estado que contiene el texto <code>Estado:</code>. */
	static JLabel statusMsg1;
	/** * Etiqueta del panel estado que indica el estado de la aplicación. */
	static JLabel statusMsg2;
	/** * Cuerpo del programa sniffer que contiene la visualización de la captura */
	VisualizarCaptura VC;
	/** * Receptor del comando. * * @see presentacion.Mediador */
	Mediador mediador;
	/** * Posicionanmiento X del proegrama. */
	public int WinX;
	/** * Posicionanmiento Y del programa. */
	public int WinY;
	/** * Ancho del programa. */
	public int WinWidth;
	/** * Alto del programa. */
	public int WinHeight;
	//Constructor
	/**
	 * Genera una nueva ventana principal del programa.
	 * 
	 * @see BarraMenu
	 * @see BarraHerramientas

	 */
	public MenuSniffer(Mediador med) {
		super("Sniffer II");
		this.setVisible(false);
		mediador = med;
		setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
		addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				new VentanaSalir(mediador);
				System.out.println("Cerro Correctamente");
			}

			public void windowClosed(WindowEvent e) {
			}

			public void windowDeactivated(WindowEvent e) {
			}

			public void windowActivated(WindowEvent e) {
			}

			public void windowIconified(WindowEvent e) {

			}

			public void windowDeiconified(WindowEvent e) {
				//toFront();

			}
		});
		this.setVisible(false);
		//this.setSize(799, 599);
		
		// AKI
		//this.addWindowListener(mediador);
		//this.setExtendedState(JFrame.MAXIMIZED_BOTH);
		this
				.setIconImage(Toolkit.getDefaultToolkit().getImage(
						FachadaFicheroDirectorios.getdirectorioData("DIR_IMAGES") + 
						System.getProperty("file.separator") + 
						"sniffer.gif"));
		Container contentPane = getContentPane();
		//        split= new JSplitPane(JSplitPane.HORIZONTAL_SPLIT);
		//        add(split);
		// Creamos las pestañas en las que se mostrarán los ficheros
		//PanelPestanas panelpestanas=new PanelPestanas();

		//	split.setRightComponent(panelpestanas.getPestana());
		//  split.setOneTouchExpandable(true);
		//  split.setDividerSize(20);

		show();

		//Crea barra de menus
		BarraMenu menus = new BarraMenu(mediador);
		this.setJMenuBar(menus.getMenu());

		//Crea la barra de herramienta1s.
		//BarraHerramientas barraHerramientas=new BarraHerramientas();
		BarraHerramientas barraHerramientas = new BarraHerramientas(mediador);
		//Añadimos la barra de herramientas al panel contenedor.
		contentPane.add(barraHerramientas.getBarraHerramientas(),
				BorderLayout.NORTH);
		setContentPane(contentPane);
		//deshabilitamos los botones no operativos por el momento
		med.deshabilitarBHelemento(3);
		med.deshabilitarBHelemento(4);
		med.deshabilitarComponenteBarraMenus(1, 1);
		med.deshabilitarComponenteBarraMenus(0, 1);
		//med.deshabilitarComponenteBarraMenus(0, 3);
		med.EnabledComponenteBarraMenus(0,3,0,false);
		//Crea la barra de estado
		statusPanel = new JPanel();
		statusPanel.setLayout(new BorderLayout());

		//crea las etiquetas para la barra de estado
		statusMsg1 = new JLabel("Estado: ");
		statusMsg1.setFont(new Font("Times-Roman", Font.PLAIN, 12));

		statusMsg2 = new JLabel("Sniffer-II iniciado");
		statusMsg2.setFont(new Font("Times-Roman", Font.PLAIN, 12));
		statusPanel.add(statusMsg1, BorderLayout.LINE_START);
		statusPanel.add(statusMsg2, BorderLayout.CENTER);
		statusPanel.setBorder(new javax.swing.border.EtchedBorder(
				javax.swing.border.BevelBorder.RAISED));
		//Agrega el panel de satus al sur del contenedor
		this.getContentPane().add(statusPanel, BorderLayout.SOUTH);

		VC = new VisualizarCaptura(mediador);
		contentPane.add(VC.getPanel(), java.awt.BorderLayout.CENTER);
		
		mediador.leerProperties(this);

		
		//setSize(Integer.parseInt(this.WinWidth),Integer.parseInt(this.WinHeight));

	}//MenuSniffer

	/**
	 * Visualiza los datos de un fichero de captura
	 *
	 */
	public void runVisualizacionCaptura() {
		VC.run();
	}

	/**
	 * Abre un fichero de captura y lo visualiza
	 * 
	 * @param file fichero capturado
	 */
	public void abrirFichero(String file) {
		VC.setFile(file);
		runVisualizacionCaptura();
	}

	//Métodos

	/**
	 * Modifica el contenido de la barra de esado.
	 * 
	 * @param estado
	 *            estado que debe mostrar la barra de estados
	 * @see #statusMsg2
	 */
	public static void setStatusPanel(String estado) {
		statusMsg2.setText(estado);
	}//setStatusPanel

	/**
	 * Refresca los datos de visualización.
	 */
	public void recargarDatos() {
		//VC.repaint();
		//VC.show();
		VC.refreshCapture();
	}
	
	/**
	 * Devuelve la posición de una columna
	 * 
	 * @return posición
	 */
	public String getColumPosition(){
		return VC.getColumnPosition();
	}
	
	/**
	 * Borra los datos de visualización
	 *
	 */
	public void clearScreen(){
		VC.resetGraficos();
	}
}//Clase MenuSniffer
