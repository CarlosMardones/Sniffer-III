package presentacion;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

import presentacion.propiedadesVentana.*;
import presentacion.comandos.*;
import servicioAccesoDatos.FachadaFicheroDirectorios;

/**
 * Ventana de inicio de la aplicación. No utilizada, se quitó
 * 
 * @author Leonardo García & JoseRamón Gutierrez
 * @version 2.0
 * 
 * @see javax.swing.JFrame
 */
public class VentanaPresentacion extends JFrame {

	//Atributos.
	/**
	 * Receptor del comando.
	 * 
	 * @see presentacion.Mediador
	 */
	Mediador mediador;

	/**
	 * Comando encargado de inicar la aplicación o cerra esta ventana y salir de
	 * la aplicación.
	 * 
	 * @see presentacion.comandos.Comando
	 * @see presentacion.comandos.CBIniciarSalirAplicacion
	 */
	private Comando comandoIniciarSalirAplicacion;

	private Comando jButton1; //comandoIniciarSalirAplicacion

	//Constructor

	/**
	 * Genera una nueva ventana con un panel que permite seleccionar entre
	 * inicar la aplicación haciendo un análisis nuevo o salir de la aplicación.
	 * También muestra el nombre del programa y su versión.
	 * 
	 * @see presentacion.propiedadesVentana.CentrarVentana
	 * @see presentacion.comandos.CBIniciarSalirAplicacion
	 * @see presentacion.Mediador
	 */
	public VentanaPresentacion() {
		// Instanciamos los comandos concretos y les indicamos el receptor
		super("Presentación Sniffer II");
		initBottons();
		initComponents();
		new CentrarVentana(this);
		//this.setResizable(false);
		addWindowListener(new AdaptadorVentana());
		this
				.setIconImage(Toolkit.getDefaultToolkit().getImage(
						FachadaFicheroDirectorios.getdirectorioData("DIR_IMAGES") + 
						System.getProperty("file.separator") + 
						"sniffer.gif"));
		show();
	}//VentanaMenu

	//Metodos

	public VentanaPresentacion(boolean aux) {
		mediador = new Mediador();
		mediador.irAventana("Sniffer");
		mediador.PrefLeerPreferencias();
		mediador.PropLeerProperties();

	}				
	/**
	 * Inicializa los componentes del panel.
	 * 
	 * @see presentacion.comandos.CBIniciarSalirAplicacion
	 * @see presentacion.Mediador
	 */
	private void initBottons() {
		//Carga de botonos comunicando con el mediador y el boton
		// correspondiene
		//
		mediador = new Mediador();
		ImageIcon IconoNuevo = new ImageIcon(
				FachadaFicheroDirectorios.getdirectorioData("DIR_IMAGES") + 
				System.getProperty("file.separator") + 
				"sniffer.gif");
		jButton1 = new CBIniciarSalirAplicacion(mediador, "Sniffer", IconoNuevo);
	}

	/**
	 * Inicializa los componentes del panel.
	 * 
	 * @see presentacion.comandos.CBIniciarSalirAplicacion
	 * @see presentacion.Mediador
	 */
	private void initComponents() {

		jPanel1 = new javax.swing.JPanel();
		jPanel2 = new javax.swing.JPanel();
		jButton2 = new javax.swing.JButton();
		jPanel3 = new javax.swing.JPanel();
		jLabel1 = new javax.swing.JLabel();
		jLabel2 = new javax.swing.JLabel();
		jLabel3 = new javax.swing.JLabel();
		jLabel4 = new javax.swing.JLabel();

		jPanel1.setBorder(javax.swing.BorderFactory
				.createEtchedBorder(javax.swing.border.EtchedBorder.RAISED));
		jButton2.setIcon(new javax.swing.ImageIcon(
				FachadaFicheroDirectorios.getdirectorioData("DIR_IMAGES") + 
				System.getProperty("file.separator") + 
				"escudo1.gif"));
		jButton2.setBorderPainted(false);
		jButton2.setContentAreaFilled(false);
		jButton2.setFocusPainted(false);
		jButton2.setFocusable(false);

		org.jdesktop.layout.GroupLayout jPanel2Layout = new org.jdesktop.layout.GroupLayout(
				jPanel2);
		jPanel2.setLayout(jPanel2Layout);
		jPanel2Layout.setHorizontalGroup(jPanel2Layout.createParallelGroup(
				org.jdesktop.layout.GroupLayout.LEADING).add(jButton2,
				org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 74,
				org.jdesktop.layout.GroupLayout.PREFERRED_SIZE));
		jPanel2Layout.setVerticalGroup(jPanel2Layout.createParallelGroup(
				org.jdesktop.layout.GroupLayout.LEADING).add(
				jPanel2Layout.createSequentialGroup().addContainerGap().add(
						jButton2, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE,
						org.jdesktop.layout.GroupLayout.DEFAULT_SIZE,
						Short.MAX_VALUE).addContainerGap()));

		jLabel1.setFont(new java.awt.Font("Tahoma", 1, 36));
		jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
		jLabel1.setText("Sniffer II");

		jLabel2.setFont(new java.awt.Font("Times New Roman", 0, 12));
		jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
		jLabel2.setText("Leonardo Garc\u00eda");

		jLabel3.setFont(new java.awt.Font("Times New Roman", 0, 12));
		jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
		jLabel3.setText("Jose-Ram\u00f3n Guti\u00e9rrez");

		jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
		jLabel4.setText("Versi\u00f3n 2.0");

		org.jdesktop.layout.GroupLayout jPanel3Layout = new org.jdesktop.layout.GroupLayout(
				jPanel3);
		jPanel3.setLayout(jPanel3Layout);
		jPanel3Layout
				.setHorizontalGroup(jPanel3Layout
						.createParallelGroup(
								org.jdesktop.layout.GroupLayout.LEADING)
						.add(
								jPanel3Layout
										.createSequentialGroup()
										.addContainerGap()
										.add(
												jPanel3Layout
														.createParallelGroup(
																org.jdesktop.layout.GroupLayout.LEADING)
														.add(
																jPanel3Layout
																		.createSequentialGroup()
																		.add(
																				jPanel3Layout
																						.createParallelGroup(
																								org.jdesktop.layout.GroupLayout.LEADING)
																						.add(
																								jLabel1,
																								org.jdesktop.layout.GroupLayout.DEFAULT_SIZE,
																								org.jdesktop.layout.GroupLayout.DEFAULT_SIZE,
																								Short.MAX_VALUE)
																						.add(
																								org.jdesktop.layout.GroupLayout.TRAILING,
																								jLabel2,
																								org.jdesktop.layout.GroupLayout.DEFAULT_SIZE,
																								150,
																								Short.MAX_VALUE)
																						.add(
																								jLabel3,
																								org.jdesktop.layout.GroupLayout.DEFAULT_SIZE,
																								150,
																								Short.MAX_VALUE))
																		.addContainerGap())
														.add(
																org.jdesktop.layout.GroupLayout.TRAILING,
																jLabel4))));
		jPanel3Layout.setVerticalGroup(jPanel3Layout.createParallelGroup(
				org.jdesktop.layout.GroupLayout.LEADING).add(
				jPanel3Layout.createSequentialGroup().addContainerGap().add(
						jLabel1).add(14, 14, 14).add(jLabel2).addPreferredGap(
						org.jdesktop.layout.LayoutStyle.RELATED).add(jLabel3)
						.addPreferredGap(
								org.jdesktop.layout.LayoutStyle.RELATED, 14,
								Short.MAX_VALUE).add(jLabel4)));

		org.jdesktop.layout.GroupLayout jPanel1Layout = new org.jdesktop.layout.GroupLayout(
				jPanel1);
		jPanel1.setLayout(jPanel1Layout);
		jPanel1Layout.setHorizontalGroup(jPanel1Layout.createParallelGroup(
				org.jdesktop.layout.GroupLayout.LEADING).add(
				jPanel1Layout.createSequentialGroup().addContainerGap().add(
						jPanel2,
						org.jdesktop.layout.GroupLayout.PREFERRED_SIZE,
						org.jdesktop.layout.GroupLayout.DEFAULT_SIZE,
						org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
						.addPreferredGap(
								org.jdesktop.layout.LayoutStyle.RELATED, 24,
								Short.MAX_VALUE).add(jPanel3,
								org.jdesktop.layout.GroupLayout.PREFERRED_SIZE,
								org.jdesktop.layout.GroupLayout.DEFAULT_SIZE,
								org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
						.addContainerGap()));
		jPanel1Layout
				.setVerticalGroup(jPanel1Layout
						.createParallelGroup(
								org.jdesktop.layout.GroupLayout.LEADING)
						.add(
								jPanel1Layout
										.createSequentialGroup()
										.add(
												jPanel1Layout
														.createParallelGroup(
																org.jdesktop.layout.GroupLayout.LEADING)
														.add(
																jPanel2,
																org.jdesktop.layout.GroupLayout.PREFERRED_SIZE,
																org.jdesktop.layout.GroupLayout.DEFAULT_SIZE,
																org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
														.add(
																jPanel1Layout
																		.createSequentialGroup()
																		.add(
																				11,
																				11,
																				11)
																		.add(
																				jPanel3,
																				org.jdesktop.layout.GroupLayout.DEFAULT_SIZE,
																				org.jdesktop.layout.GroupLayout.DEFAULT_SIZE,
																				Short.MAX_VALUE)))
										.addContainerGap()));

		org.jdesktop.layout.GroupLayout layout = new org.jdesktop.layout.GroupLayout(
				getContentPane());
		getContentPane().setLayout(layout);
		layout
				.setHorizontalGroup(layout
						.createParallelGroup(
								org.jdesktop.layout.GroupLayout.LEADING)
						.add(
								layout
										.createSequentialGroup()
										.addContainerGap()
										.add(
												jPanel1,
												org.jdesktop.layout.GroupLayout.PREFERRED_SIZE,
												org.jdesktop.layout.GroupLayout.DEFAULT_SIZE,
												org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
										.addContainerGap(
												org.jdesktop.layout.GroupLayout.DEFAULT_SIZE,
												Short.MAX_VALUE)).add(
								org.jdesktop.layout.GroupLayout.TRAILING,
								layout.createSequentialGroup().addContainerGap(
										201, Short.MAX_VALUE).add(
										(CBIniciarSalirAplicacion) jButton1)
										.addContainerGap()));
		layout.setVerticalGroup(layout.createParallelGroup(
				org.jdesktop.layout.GroupLayout.LEADING).add(
				layout.createSequentialGroup().addContainerGap().add(jPanel1,
						org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 154,
						org.jdesktop.layout.GroupLayout.PREFERRED_SIZE).add(14,
						14, 14).add((CBIniciarSalirAplicacion) jButton1)
						.addContainerGap(
								org.jdesktop.layout.GroupLayout.DEFAULT_SIZE,
								Short.MAX_VALUE)));
		pack();
	}// </editor-fold>

	/**
	 * Clase interna que permite el cierre de la ventana.
	 * 
	 * @author Verónica Delgado y Patricia Cacho
	 * @version 2.0
	 * @see java.awt.event.WindowAdapter
	 */

	//(ComandoIniciarSalirAplicacion)jButton1
	private class AdaptadorVentana extends WindowAdapter {
		/**
		 * Cierra la ventana
		 * 
		 * @param e
		 *            evento asociado
		 */
		public void windowClosing(WindowEvent e) {
			System.exit(1);
		}
	}//AdaptadorVentana

	// Declaración de varibales -no modificar

	private javax.swing.JButton jButton2;

	private javax.swing.JLabel jLabel1;

	private javax.swing.JLabel jLabel2;

	private javax.swing.JLabel jLabel3;

	private javax.swing.JLabel jLabel4;

	private javax.swing.JPanel jPanel1;

	private javax.swing.JPanel jPanel2;

	private javax.swing.JPanel jPanel3;
	// Fin de declaración de variables

}//Clase VentanaMenu

