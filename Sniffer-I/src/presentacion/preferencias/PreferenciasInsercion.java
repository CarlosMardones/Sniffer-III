package presentacion.preferencias;

import java.awt.Component;
import java.awt.event.*;
import java.io.IOException;
import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Arrays;

import javax.swing.JTable;

import jpcap.JpcapCaptor;
import jpcap.JpcapSender;
import jpcap.NetworkInterface;
import jpcap.NetworkInterfaceAddress;
import jpcap.packet.ARPPacket;
import jpcap.packet.EthernetPacket;
import jpcap.packet.ICMPPacket;
import jpcap.packet.IPPacket;
import jpcap.packet.Packet;
import jpcap.packet.TCPPacket;
import jpcap.packet.UDPPacket;

import dominio.FachadaDominio;
import dominio.preferences.preferencesBeanDefinicion;
import dominio.preferences.identificacion.PrefIdentificacion;

import presentacion.Mediador;
import presentacion.comandos.*;
import presentacion.propiedadesVentana.CentrarVentana;
import presentacion.ventanaMenuSniffer.MenuSniffer;

/**
 * Permite seleccionar la estructura de un protocolo definido y generar un paquete de datos con dicha estructura 
 * 
 * @author Carlos Mardones Muga
 * @version 1.0
 * 
 * @see javax.swing.JDialog
 */
public class PreferenciasInsercion extends javax.swing.JDialog {
	
	/** * Titulo de la ventana. */
	  private static String title;
	/** * Si es captura o veer las preferencias. */
	  private static boolean tipo;
	/** * Lista de dispositivos. */
	  private static String[] Dispositivos = { "Seleccione un dispositivo", };
	/** * Lista de dispositivos. */
	  private static String[] Dispo ;
	/** *Dispositivo selecionado. */
	  private static String dispoSel="";
	/** *Tabla de valores */
	  private static JTable jTable1;
	/** *Numero de campos	   */
	  private static int numColumnas = 4 ;
	/**	*Puerto Origen 		 */
	//  private static String pOrigen;
	/**	*Puerto Destino 	 */
	//  private static String pDestino;
	/** *Direcion ip Origen   */
	  private static String ipOr;
	/**	 *Direcion ip Destino 	*/
	  private static String ipDes;
	/**	 *Direcion mac Origen 	*/
	  private static String macOr="";
	/**	 *Direcion mac Destino 	 */
	  private static String macDes="";  
	/** *Orden de encapsulacion */ 
	  private static ArrayList<String> encapsulacion = new ArrayList ();
	/** * Nivel de enlace   */
	  private static String enlace="";
	/** * Nivel de red	*/
	  private static String red="";
	/** * Nivel de transporte */
	  private static String transporte="";
	/** * Nivel de aplicacion */
	  private static ArrayList<String> aplicacion = new ArrayList <String> ();
	/** * Identificadores de los protocolos definidos  */
	  private PrefIdentificacion pref;
	/** *Orden de encapsulacion */
	  private String orden="";
	/** *Puerto destino */
	  private static String  puertoDestino="";
	/** *Puerto destino */
	  private static String  puertoOrigen="";
	/** *Datos del paquete definido*/
	  private static ArrayList<byte []> definidos = new ArrayList<byte []>();
	/** *Numero de paquetes a insertar */
	  private static int tot=-1;
	
	/**
	 * Receptor del comando.
	 * 
	 * @see presentacion.Mediador
	 */
	Mediador mediador;
	
	/**
	 * Comando encargado de insertar paquetes en la red.
	 * 
	 * @see presentacion.comandos.Comando
	 * @see presentacion.comandos.CJMAbrirFichero
	 */
	Comando jBInsertar;
	/**
	 * Comando encargado de cerrar la ventana .
	 * 
	 * @see presentacion.comandos.Comando
	 * @see presentacion.comandos.CJMAbrirFichero
	 */
	Comando jBCancelar;
	/**
	 * Comando encargado de inicializar las ventanas.
	 * 
	 * @see presentacion.comandos.Comando
	 * @see presentacion.comandos.CJMAbrirFichero
	 */
	//Comando jBLimpiar;
	/**
	 * Comando encargado de abrir la ventana Abrir fichero.
	 * 
	 * @see presentacion.comandos.Comando
	 * @see presentacion.comandos.CJMAbrirFichero
	 */
	Comando jBDefinicion; 
	/**
	 * Comando encargado de seleccionar los distintos dispositivos.
	 * 
	 * @see presentacion.comandos.Comando
	 * @see presentacion.comandos.CBGuardarFichero
	 */
	Comando jBDispositivo; //CBGuardarFichero
	/**
	 * Comando encargado de abrir la ventana Capturas guardadas.
	 * 
	 * @see presentacion.comandos.Comando
	 * @see presentacion.comandos.CJMAbrirFichero
	 */
	Comando jBCaptura; 
	
	
	/** Creates new form PreferenciasInsercion */
	public PreferenciasInsercion() {
		super(MenuSniffer.getFrames()[0], "Insercion de paquetes definidos", true);
		title = "Inicio Definicion";
		tipo = true;
		mediador = new Mediador();
		initComponents();
		((CBAceptar) jBCancelar).setVisible(true);
		((CBAceptar) jBInsertar).setVisible(true);
		//((CBAceptar) jBLimpiar).setVisible(true);
		((CBAbrirFichero) jBDefinicion).setVisible(true);
		((CBAbrirFichero) jBCaptura).setVisible(true);
		((CBAbrirFichero) jBDispositivo).setVisible(true);
		this.setResizable(false);
		new CentrarVentana(this);
		inicializarTodo();
	}

    /**
     * Inicializa los valores de la clase
     */
      private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        buttonGroup2 = new javax.swing.ButtonGroup();
        buttonGroup3 = new javax.swing.ButtonGroup();
        buttonGroup4 = new javax.swing.ButtonGroup();
        jPanel3 = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jcmbNivel5 = new javax.swing.JComboBox();
        jLabel14 = new javax.swing.JLabel();
        jcmbNivel1 = new javax.swing.JComboBox();
        jcmbNivel2 = new javax.swing.JComboBox();
        jcmbNivel3 = new javax.swing.JComboBox();
        jcmbNivel4 = new javax.swing.JComboBox();
        jTextField9 = new javax.swing.JTextField();
        jLabel15 = new javax.swing.JLabel();
        jBEncapsular = new javax.swing.JButton();
        jPanel4 = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        jrIpOmanual = new javax.swing.JRadioButton();
        jrIpOcap = new javax.swing.JRadioButton();
        jcmbIpOcap = new javax.swing.JComboBox();
        jtxtIpOmanual = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        jrIpDmanual = new javax.swing.JRadioButton();
        jrIpDcap = new javax.swing.JRadioButton();
        jtxtIpDmanual = new javax.swing.JTextField();
        jcmbIpDcap = new javax.swing.JComboBox();
        jPanel5 = new javax.swing.JPanel();
        jLabel7 = new javax.swing.JLabel();
        jrMacOmanual = new javax.swing.JRadioButton();
        jrMacOcap = new javax.swing.JRadioButton();
        jtxtMacOmanual = new javax.swing.JTextField();
        jcmbMacOcap = new javax.swing.JComboBox();
        jLabel16 = new javax.swing.JLabel();
        jrMacDmanual = new javax.swing.JRadioButton();
        jrMacDcap = new javax.swing.JRadioButton();
        jtxtMacDmanual = new javax.swing.JTextField();
        jcmbMacDcap = new javax.swing.JComboBox();
        jPanel6 = new javax.swing.JPanel();
        jLabel17 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        jtxtPorigen = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jtxtCapturas = new javax.swing.JTextField();
       // jBCaptura = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        jcmbDisp = new javax.swing.JComboBox();
        jLabel19 = new javax.swing.JLabel();
        jtxtPdestino = new javax.swing.JTextField();
        jPanel2 = new javax.swing.JPanel();
        //jBDefinicion = new javax.swing.JButton();
        jtxtDirDef = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jLabel5 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jtxtNombre = new javax.swing.JTextField();
        jtxtRfc = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        jtxtNivel = new javax.swing.JTextField();
        jBCheck = new javax.swing.JButton();
        jBEncapDefinidos = new javax.swing.JButton();
        jBDesencap = new javax.swing.JButton();
        jPanel7 = new javax.swing.JPanel();
        jLabel14 = new javax.swing.JLabel();
        jtxtIp6origen = new javax.swing.JTextField();
        jLabel20 = new javax.swing.JLabel();
        jtxtIp6destino = new javax.swing.JTextField();
        jBLimpiar = new javax.swing.JButton();
        //jBCancelar = new javax.swing.JButton();
        //jBInsertar = new javax.swing.JButton();
        
        jBDefinicion = new CBAbrirFichero(mediador,"AbrirDefinicionProtocoloInsercion");
        jBCaptura = new CBAbrirFichero(mediador,"Abrir fichero de exportaciones...");
        jBDispositivo = new CBAbrirFichero(mediador,"AbrirDefinicionProtocolo");
        jBCancelar = new CBAceptar(mediador,"Salir");
        jBInsertar = new CBAceptar(mediador,"Insertar Paquetes Definidos");
        //jBLimpiar = new CBAceptar(mediador,"Salir");
        

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setMinimumSize(new java.awt.Dimension(1300, 650));
        setName("Form"); // NOI18N
        setResizable(false);

        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder(new javax.swing.border.LineBorder(new java.awt.Color(153, 204, 255), 2, true), "Inserción", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma 11 Negrita 12 Simple 12 Simple", 1, 14))); // NOI18N
        jPanel3.setName("jPanel3"); // NOI18N
        jPanel3.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(new javax.swing.border.LineBorder(new java.awt.Color(153, 204, 255), 2, true), "Orden de encapsulacion", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 14))); // NOI18N
        jPanel1.setName("jPanel1"); // NOI18N
        jPanel1.setPreferredSize(new java.awt.Dimension(670, 172));

        jLabel10.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel10.setText("Niv Enlace:");
        jLabel10.setName("jLabel10"); // NOI18N

        jLabel11.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel11.setText("Niv Red :");
        jLabel11.setName("jLabel11"); // NOI18N

        jLabel13.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel13.setText("Niv Aplic:");
        jLabel13.setName("jLabel13"); // NOI18N

        jLabel12.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel12.setText("Niv Transp:");
        jLabel12.setName("jLabel12"); // NOI18N

        jcmbNivel1.setName("jcmbNivel1"); // NOI18N
        jcmbNivel1.setPreferredSize(new java.awt.Dimension(23, 18));
               jcmbNivel1.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                jcmbNivel1FocusLost(evt);
            }
        });

        jcmbNivel2.setName("jcmbNivel2"); // NOI18N
        jcmbNivel2.setPreferredSize(new java.awt.Dimension(23, 18));
        jcmbNivel2.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                jcmbNivel2FocusLost(evt);
            }
        });

        jcmbNivel3.setName("jcmbNivel3"); // NOI18N
        jcmbNivel3.setPreferredSize(new java.awt.Dimension(23, 18));
        jcmbNivel3.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                jcmbNivel3FocusLost(evt);
            }
        });

        jTextField9.setName("jTextField9"); // NOI18N

        jLabel15.setText("Orden de encapsulacion");
        jLabel15.setName("jLabel15"); // NOI18N

        jBEncapsular.setText("Encapsular");
        jBEncapsular.setName("jBEncapsular"); // NOI18N
        jBEncapsular.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jBEncapsularMouseClicked(evt);
            }
        });

        jBDesencap.setFont(new java.awt.Font("Tahoma 11 Negrita 12 Negrita 12 Simple", 0, 12));
        jBDesencap.setText("Desencapsular");
        jBDesencap.setName("jBDesencap"); // NOI18N

        jcmbNivel4.setName("jcmbNivel4"); // NOI18N
        jcmbNivel4.setPreferredSize(new java.awt.Dimension(20, 18));
        jcmbNivel4.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                jcmbNivel4FocusLost(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel15))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jTextField9, javax.swing.GroupLayout.PREFERRED_SIZE, 554, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(121, 121, 121)
                                .addComponent(jBEncapsular, javax.swing.GroupLayout.PREFERRED_SIZE, 131, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(10, 10, 10)
                                .addComponent(jLabel10)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jcmbNivel1, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jLabel11)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jcmbNivel2, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(18, 18, 18)
                        .addComponent(jLabel12)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                .addComponent(jcmbNivel3, javax.swing.GroupLayout.PREFERRED_SIZE, 83, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jLabel13)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jcmbNivel4, javax.swing.GroupLayout.PREFERRED_SIZE, 74, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(6, 6, 6))
                            .addComponent(jBDesencap))))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(4, 4, 4)
                        .addComponent(jLabel10))
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jcmbNivel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jcmbNivel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel12)
                        .addComponent(jLabel11)
                        .addComponent(jLabel13)
                        .addComponent(jcmbNivel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jcmbNivel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(27, 27, 27)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jBDesencap, 0, 0, Short.MAX_VALUE)
                    .addComponent(jBEncapsular))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 8, Short.MAX_VALUE)
                .addComponent(jLabel15)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTextField9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        jPanel3.add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(670, 460, 640, -1));

        jPanel4.setBorder(javax.swing.BorderFactory.createTitledBorder(new javax.swing.border.LineBorder(new java.awt.Color(153, 204, 255), 2, true), "Seleccion IP v4", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 14))); // NOI18N
        jPanel4.setName("jPanel4"); // NOI18N
        jPanel4.setPreferredSize(new java.awt.Dimension(640, 120));
            
        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel4.setText("IP Origen :");
        jLabel4.setName("jLabel4"); // NOI1
        
        buttonGroup1.add(jrIpOmanual);
        jrIpOmanual.setText("Manual");
        jrIpOmanual.setName("jrIpOmanual"); // NOI18N
        jrIpOmanual.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                jrIpOmanualFocusGained(evt);
            }
        });

        buttonGroup1.add(jrIpOcap);
        jrIpOcap.setText("Capturada");
        jrIpOcap.setName("jrIpOcap"); // NOI18N
        jrIpOcap.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                jrIpOcapFocusGained(evt);
            }
        });

        jtxtIpOmanual.setEnabled(false);
        jtxtIpOmanual.setName("jtxtIpOmanual"); // NOI18N
        jtxtIpOmanual.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                jtxtIpOmanualFocusLost(evt);
            }
        });

        jLabel6.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel6.setText("IP Destino :");
        jLabel6.setName("jLabel6"); // NOI18N

        buttonGroup2.add(jrIpDmanual);
        jrIpDmanual.setText("Manual");
        jrIpDmanual.setName("jrIpDmanual"); // NOI18N
        jrIpDmanual.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                jrIpDmanualFocusGained(evt);
            }
        });

        buttonGroup2.add(jrIpDcap);
        jrIpDcap.setText("Capturada");
        jrIpDcap.setName("jrIpDcap"); // NOI18N
        jrIpDcap.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                jrIpDcapFocusGained(evt);
            }
        });

        jtxtIpDmanual.setEnabled(false);
        jtxtIpDmanual.setName("jtxtIpDmanual"); // NOI18N
        jtxtIpDmanual.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                jtxtIpDmanualFocusLost(evt);
            }
        });

        jcmbIpOcap.setEnabled(false);
        jcmbIpOcap.setName("jcmbIpOcap"); // NOI18N
        jcmbIpOcap.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                jcmbIpOcapFocusLost(evt);
            }
        });

        jcmbIpDcap.setEnabled(false);
        jcmbIpDcap.setName("jcmbIpDcap"); // NOI18N
        jcmbIpDcap.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                jcmbIpDcapFocusLost(evt);
            }
        });

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(74, 74, 74)
                        .addComponent(jrIpOmanual)
                        .addGap(22, 22, 22)
                        .addComponent(jtxtIpOmanual, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(114, 114, 114)
                        .addComponent(jrIpDmanual)
                        .addGap(24, 24, 24)
                        .addComponent(jtxtIpDmanual, javax.swing.GroupLayout.PREFERRED_SIZE, 112, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel4)
                        .addGap(243, 243, 243)
                        .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(74, 74, 74)
                        .addComponent(jrIpOcap)
                        .addGap(8, 8, 8)
                        .addComponent(jcmbIpOcap, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(114, 114, 114)
                        .addComponent(jrIpDcap)
                        .addGap(6, 6, 6)
                        .addComponent(jcmbIpDcap, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(40, 40, 40))
        );
        jPanel4Layout.setVerticalGroup(
                jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel4Layout.createSequentialGroup()
                    .addGap(9, 9, 9)
                    .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel4Layout.createSequentialGroup()
                            .addGap(1, 1, 1)
                            .addComponent(jrIpOmanual))
                        .addGroup(jPanel4Layout.createSequentialGroup()
                            .addGap(1, 1, 1)
                            .addComponent(jtxtIpOmanual, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addComponent(jrIpDmanual)
                        .addGroup(jPanel4Layout.createSequentialGroup()
                            .addGap(1, 1, 1)
                            .addComponent(jtxtIpDmanual, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGap(3, 3, 3)
                    .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jLabel6)
                        .addComponent(jLabel4))
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jrIpOcap)
                        .addGroup(jPanel4Layout.createSequentialGroup()
                            .addGap(2, 2, 2)
                            .addComponent(jcmbIpOcap, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addComponent(jrIpDcap)
                        .addGroup(jPanel4Layout.createSequentialGroup()
                            .addGap(1, 1, 1)
                            .addComponent(jcmbIpDcap, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
            );

        jPanel3.add(jPanel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 230, 640, 120));

        jPanel5.setBorder(javax.swing.BorderFactory.createTitledBorder(new javax.swing.border.LineBorder(new java.awt.Color(153, 204, 255), 2, true), "Seleccion IP v6", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 14))); // NOI18N
        jPanel5.setName("jPanel5"); // NOI18N

        jLabel7.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel7.setText("MAC Origen :");
        jLabel7.setName("jLabel7"); // NOI18N
        
        jLabel16.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel16.setText("MAC Destino :");
        jLabel16.setName("jLabel16"); 

        buttonGroup3.add(jrMacOmanual);
        jrMacOmanual.setText("Manual");
        jrMacOmanual.setName("jrMacOmanual"); // NOI18N

        buttonGroup3.add(jrMacOcap);
        jrMacOcap.setText("Capturada");
        jrMacOcap.setName("jrMacOcap"); // NOI18N

        jtxtMacOmanual.setEnabled(false);
        jtxtMacOmanual.setName("jtxtMacOmanual"); // NOI18N

        jcmbMacOcap.setEnabled(false);
        jcmbMacOcap.setName("jcmbMacOcap"); // NOI18N

        jLabel16.setText("MAC Destino :");
        jLabel16.setName("jLabel16"); // NOI18N

        buttonGroup4.add(jrMacDmanual);
        jrMacDmanual.setText("Manual");
        jrMacDmanual.setName("jrMacDmanual"); // NOI18N

        buttonGroup4.add(jrMacDcap);
        jrMacDcap.setText("Capturada");
        jrMacDcap.setName("jrMacDcap"); // NOI18N

        jtxtMacDmanual.setEnabled(false);
        jtxtMacDmanual.setName("jtxtMacDmanual"); // NOI18N

        jcmbMacDcap.setEnabled(false);
        jcmbMacDcap.setName("jcmbMacDcap"); // NOI18N

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGap(5, 5, 5)
                        .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 79, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jrMacOmanual)
                        .addGap(25, 25, 25)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel5Layout.createSequentialGroup()
                                .addComponent(jtxtMacOmanual, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(102, 102, 102))
                            .addGroup(jPanel5Layout.createSequentialGroup()
                                .addComponent(jLabel16)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)))
                        .addComponent(jrMacDmanual, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jtxtMacDmanual, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGap(84, 84, 84)
                        .addComponent(jrMacOcap)
                        .addGap(11, 11, 11)
                        .addComponent(jcmbMacOcap, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(103, 103, 103)
                        .addComponent(jrMacDcap)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jcmbMacDcap, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap(19, Short.MAX_VALUE))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jrMacOmanual)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGap(20, 20, 20)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel16)
                            .addComponent(jLabel7)))
                    .addComponent(jtxtMacOmanual, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jrMacDmanual)
                        .addComponent(jtxtMacDmanual, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(6, 6, 6)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jrMacOcap)
                    .addComponent(jcmbMacOcap, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jrMacDcap)
                        .addComponent(jcmbMacDcap, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
        );

        jPanel3.add(jPanel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 420, 640, 110));

        jPanel6.setBorder(javax.swing.BorderFactory.createTitledBorder(new javax.swing.border.LineBorder(new java.awt.Color(153, 204, 255), 2, true), "Seleccion de Envio", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 14))); // NOI18N
        jPanel6.setName("jPanel6"); // NOI18N
        jPanel6.setPreferredSize(new java.awt.Dimension(600, 99));

        jLabel17.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel17.setText("Puerto Origen :");
        jLabel17.setName("jLabel17"); // NOI18N

     
      
        jtxtPorigen.setName("jtxtPorigen"); // NOI18N
        jtxtPorigen.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                jtxtPorigenFocusLost(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel1.setText("Capturas :");
        jLabel1.setName("jLabel1"); // NOI18N

        jtxtCapturas.setText("Seleción de fichero de capturas");
        jtxtCapturas.setName("jtxtCapturas"); // NOI18N

        //jBCaptura.setFont(new java.awt.Font("Tahoma 11 Negrita 12 Negrita 12 Negrita 12 Simple", 0, 12));
        //jBCaptura.setText("BUSCAR");
        //jBCaptura.setName("jBCaptura"); // NOI18N

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel3.setText("Dispositivo :");
        jLabel3.setName("jLabel3"); // NOI18N


        jcmbDisp.setName("jcmbDisp"); // NOI18N
        jcmbDisp.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                jcmbDispFocusLost(evt);
            }
        });

        jLabel19.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel19.setText("Puerto Destino :");
        jLabel19.setName("jLabel19");  // NOI18N

        jtxtPdestino.setName("jtxtPdestino"); // NOI18N
        jtxtPdestino.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                jtxtPdestinoFocusLost(evt);
            }
        });

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addGap(4, 4, 4)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addComponent(jLabel3)
                        .addGap(3, 3, 3)
                        .addComponent(jcmbDisp, javax.swing.GroupLayout.PREFERRED_SIZE, 420, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addGroup(jPanel6Layout.createSequentialGroup()
                                .addComponent(jLabel17)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jtxtPorigen, javax.swing.GroupLayout.PREFERRED_SIZE, 86, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel19)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jtxtPdestino, javax.swing.GroupLayout.PREFERRED_SIZE, 86, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel6Layout.createSequentialGroup()
                                .addComponent(jLabel1)
                                .addGap(13, 13, 13)
                                .addComponent(jtxtCapturas, javax.swing.GroupLayout.PREFERRED_SIZE, 420, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(20, 20, 20)
                        .addComponent((CBAbrirFichero)jBCaptura, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(19, Short.MAX_VALUE))
        );
        jPanel6Layout.setVerticalGroup(
                jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel6Layout.createSequentialGroup()
                    .addGap(10, 10, 10)
                    .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel17)
                            .addComponent(jtxtPorigen, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jtxtPdestino, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel19)))
                    .addGap(20, 20, 20)
                    .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel6Layout.createSequentialGroup()
                            .addGap(10, 10, 10)
                            .addComponent(jLabel1))
                        .addGroup(jPanel6Layout.createSequentialGroup()
                            .addGap(10, 10, 10)
                            .addComponent(jtxtCapturas, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addComponent((CBAbrirFichero)jBCaptura, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGap(25, 25, 25)
                    .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jLabel3)
                        .addComponent(jcmbDisp, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
            );

        jPanel3.add(jPanel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 30, 640, 200));

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(new javax.swing.border.LineBorder(new java.awt.Color(153, 204, 255), 2, true), "Implementacion de Protocolo", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 14))); // NOI18N
        jPanel2.setName("jPanel2"); // NOI18N
        jPanel2.setPreferredSize(new java.awt.Dimension(670, 432));

        jtxtDirDef.setName("jtxtDirDef"); // NOI18N

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel2.setText("Direccion:");
        jLabel2.setName("jLabel2"); // NOI18N
        jScrollPane1.setName("jScrollPane1"); // NOI18N

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null,null},
                {null, null, null, null,null},
                {null, null, null, null,null},
                {null, null, null, null,null},
                {null, null, null, null,null},
                {null, null, null, null,null},
                {null, null, null, null,null},
                {null, null, null, null,null},
                {null, null, null, null,null},
                {null, null, null, null,null},
                {null, null, null, null,null},
                {null, null, null, null,null},
                {null, null, null, null,null},
                {null, null, null, null,null},
                {null, null, null, null,null},
                {null, null, null, null,null},
                {null, null, null, null,null},
                {null, null, null, null,null},
                {null, null, null, null,null},
                {null, null, null, null,null}
            },
            new String [] {
                "Nombre del Campo", "Valor del campo", "Tamaño del campo", "Tipo de dato","Opcional"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.Integer.class, java.lang.String.class,java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, true, false, false,false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jTable1.setName("jTable1"); // NOI18N
        jScrollPane1.setViewportView(jTable1);
        jTable1.getColumnModel().getColumn(0).setResizable(false);
        jTable1.getColumnModel().getColumn(0).setHeaderValue("Nombre del Campo");
        jTable1.getColumnModel().getColumn(1).setResizable(false);
        jTable1.getColumnModel().getColumn(1).setHeaderValue("Valor del campo");
        jTable1.getColumnModel().getColumn(2).setResizable(false);
        jTable1.getColumnModel().getColumn(2).setHeaderValue("Tamaño del campo");
        jTable1.getColumnModel().getColumn(3).setResizable(false);
        jTable1.getColumnModel().getColumn(3).setHeaderValue("Tipo de dato");
        jTable1.getColumnModel().getColumn(4).setResizable(false);
        jTable1.getColumnModel().getColumn(4).setHeaderValue("Opcional");
        
        jLabel5.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel5.setText("Nombre Protocolo :");
        jLabel5.setName("jLabel5"); // NOI18N

        jLabel8.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel8.setText("RFC :");
        jLabel8.setName("jLabel8"); // NOI18N

        jtxtNombre.setName("jtxtNombre"); // NOI18N

        jtxtRfc.setName("jtxtRfc"); // NOI18N

        jLabel9.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel9.setText("Nivel del Protocolo :");
        jLabel9.setName("jLabel9"); // NOI18N

        jtxtNivel.setName("jtxtNivel"); // NOI18N

        jBEncapDefinidos.setText("Encapsular Protocolo Generado");
        jBEncapDefinidos.setFont(new java.awt.Font("Tahoma 11 Negrita 12 Negrita 12 Simple", 1, 12));
        jBEncapDefinidos.setName("jBEncapDefinidos"); // NOI18N
        jBEncapDefinidos.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jBEncapDefinidosMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addGroup(jPanel2Layout.createSequentialGroup()
                            .addGap(10, 10, 10)
                            .addComponent(jLabel5)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(jtxtNombre, javax.swing.GroupLayout.PREFERRED_SIZE, 69, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(42, 42, 42)
                            .addComponent(jLabel8)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(jtxtRfc, javax.swing.GroupLayout.PREFERRED_SIZE, 69, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(35, 35, 35)
                            .addComponent(jLabel9)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(jtxtNivel, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(jPanel2Layout.createSequentialGroup()
                            .addGap(10, 10, 10)
                            .addComponent(jLabel2)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(jtxtDirDef))
                        .addGroup(jPanel2Layout.createSequentialGroup()
                            .addContainerGap()
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 602, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(88, 88, 88)
                        .addComponent(jBEncapDefinidos, javax.swing.GroupLayout.PREFERRED_SIZE, 467, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(16, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(20, 20, 20)
                        .addComponent(jLabel2))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(19, 19, 19)
                        .addComponent(jtxtDirDef, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(28, 28, 28)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(jtxtNombre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel8)
                    .addComponent(jtxtRfc, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel9)
                    .addComponent(jtxtNivel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(14, 14, 14)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 239, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jBEncapDefinidos, javax.swing.GroupLayout.DEFAULT_SIZE, 35, Short.MAX_VALUE)
                .addContainerGap())
        );

        jPanel3.add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(670, 22, 640, -1));

        jBLimpiar.setText("Limpiar");
        jBLimpiar.setName("jBLimpiar"); // NOI18N
        jBLimpiar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jBLimpiarMouseClicked(evt);
            }
        });
        jPanel3.add(jBLimpiar, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 570, 160, 31));

        //jBCancelar.setText("CANCELAR");
        //jBCancelar.setName("jBCancelar"); // NOI18N
        jPanel3.add((CBAceptar)jBCancelar, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 570, 160, 31));

        //jBInsertar.setText("INSERTAR");
        //jBInsertar.setName("jBInsertar"); // NOI18N
        jPanel3.add((CBAceptar)jBInsertar, new org.netbeans.lib.awtextra.AbsoluteConstraints(430, 570, 160, 31));

        jPanel7.setBorder(javax.swing.BorderFactory.createTitledBorder(new javax.swing.border.LineBorder(new java.awt.Color(153, 204, 255), 2, true), "Seleccion IPv6", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 14))); // NOI18N
        jPanel7.setName("jPanel7"); // NOI18N

        jLabel14.setText("IP Origen :");
        jLabel14.setName("jLabel14"); // NOI18N

        jtxtIp6origen.setName("jtxtIp6origen"); // NOI18N
        jtxtIp6origen.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                jtxtIp6origenFocusLost(evt);
            }
        });

        jLabel20.setText("IP Destino:");
        jLabel20.setName("jLabel20"); // NOI18N

        jtxtIp6destino.setName("jtxtIp6destino"); // NOI18N
        jtxtIp6destino.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                jtxtIp6destinoFocusLost(evt);
            }
        });

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel14)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 30, Short.MAX_VALUE)
                .addComponent(jtxtIp6origen, javax.swing.GroupLayout.PREFERRED_SIZE, 174, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(61, 61, 61)
                .addComponent(jLabel20)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jtxtIp6destino, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(40, 40, 40))
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel14)
                    .addComponent(jtxtIp6origen, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel20)
                    .addComponent(jtxtIp6destino, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel3.add(jPanel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 350, 640, 60));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, 1333, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, 663, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        
        jcmbNivel1.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                jcmbNivel1FocusLost(evt);
            }
        });
        
        jcmbNivel2.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                jcmbNivel2FocusLost(evt);
            }
        });
        
        jcmbNivel3.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                jcmbNivel3FocusLost(evt);
            }
        });
        
        jrIpOmanual.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                jrIpOmanualFocusGained(evt);
            }
        });
        
        jrIpOcap.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                jrIpOcapFocusGained(evt);
            }
        });
        
        jrIpDmanual.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                jrIpDmanualFocusGained(evt);
            }
        });
        
        jrIpDcap.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                jrIpDcapFocusGained(evt);
            }
        });
        
        jBEncapsular.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jBEncapsularMouseClicked(evt);
            }
        });
        
        
        jcmbDisp.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                jcmbDispFocusLost(evt);
            }
        });
        
        jtxtPorigen.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                jtxtPorigenFocusLost(evt);
            }
        });
        
        jtxtPdestino.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                jtxtPdestinoFocusLost(evt);
            }
        });
        
        jcmbIpOcap.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                jcmbIpOcapFocusLost(evt);
            }
        });
        
        jcmbIpDcap.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                jcmbIpDcapFocusLost(evt);
            }
        });
        
        jtxtIpOmanual.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                jtxtIpOmanualFocusLost(evt);
            }
        });
        
        jtxtIpDmanual.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                jtxtIpDmanualFocusLost(evt);
            }
        });
        
        jtxtIp6origen.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                jtxtIp6origenFocusLost(evt);
            }
        });
        
        jtxtIp6destino.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                jtxtIp6destinoFocusLost(evt);
            }
        });
        
        jBDesencap.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jBDesencapMouseClicked(evt);
            }
        });
        
        jrMacOmanual.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                jrMacOmanualFocusGained(evt);
            }
        });
        
        jrMacOcap.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                jrMacOcapFocusGained(evt);
            }
        });
        
        jrMacDmanual.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                jrMacDmanualFocusGained(evt);
            }
        });
        
        jrMacDcap.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                jrMacDcapFocusGained(evt);
            }
        });
        
        jtxtMacOmanual.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                jtxtMacOmanualFocusLost(evt);
            }
        });
        
        jtxtMacDmanual.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                jtxtMacDmanualFocusLost(evt);
            }
        });
        
        jcmbMacDcap.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                jcmbMacDcapFocusLost(evt);
            }
        });
        
        jcmbMacOcap.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                jcmbMacOcapFocusLost(evt);
            }
        });
        
        jcmbDisp.setModel(new javax.swing.DefaultComboBoxModel(Dispositivos));
        rellenarNiveles();
        
        jcmbNivel1.addItem("Seleccione");
        jcmbNivel1.addItem("Ethernet");

       // jcmbNivel2.setName("jcmbNivel2"); // NOI18N
        jcmbNivel2.addItem("Seleccione");
        jcmbNivel2.addItem("IP v4");
        jcmbNivel2.addItem("IP v6");
        jcmbNivel2.addItem("ARP");
        jcmbNivel2.addItem("ARP Man");
        jcmbNivel2.addItem("RARP");

        //jcmbNivel3.setName("jcmbNivel3"); // NOI18N
        jcmbNivel3.addItem("Seleccione");
        jcmbNivel3.addItem("TCP");
        jcmbNivel3.addItem("UDP");
        jcmbNivel3.addItem("ICMP");

        //jcmbNivel4.setName("jcmbNivel4"); // NOI18N
        jcmbNivel4.addItem("Seleccione");

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(new javax.swing.border.LineBorder(new java.awt.Color(153, 204, 255), 2, true), "Orden de encapsulacion", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 14))); // NOI18N
        jPanel1.setName("jPanel1"); // NOI18N
        jPanel1.setPreferredSize(new java.awt.Dimension(670, 172));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel10.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel10.setText("Enlace:");
        jLabel10.setName("jLabel10"); // NOI18N
        jPanel1.add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(16, 41, 50, -1));

        jLabel11.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel11.setText("Red :");
        jLabel11.setName("jLabel11"); // NOI18N
        jPanel1.add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 40, -1, -1));

        jLabel13.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel13.setText("Aplicacion:");
        jLabel13.setName("jLabel13"); // NOI18N
        jPanel1.add(jLabel13, new org.netbeans.lib.awtextra.AbsoluteConstraints(488, 38, -1, -1));

        jLabel12.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel12.setText("Transp:");
        jLabel12.setName("jLabel12"); // NOI18N
        jPanel1.add(jLabel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(316, 38, -1, -1));

        jcmbNivel1.setName("jcmbNivel1"); // NOI18N
        jcmbNivel1.setPreferredSize(new java.awt.Dimension(23, 18));
        jcmbNivel1.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                jcmbNivel1FocusLost(evt);
            }
        });
        jPanel1.add(jcmbNivel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(65, 40, 80, -1));

        jcmbNivel2.setName("jcmbNivel2"); // NOI18N
        jcmbNivel2.setPreferredSize(new java.awt.Dimension(23, 18));
        jcmbNivel2.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                jcmbNivel2FocusLost(evt);
            }
        });
        jPanel1.add(jcmbNivel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 40, 90, -1));

        jcmbNivel3.setName("jcmbNivel3"); // NOI18N
        jcmbNivel3.setPreferredSize(new java.awt.Dimension(23, 18));
        jcmbNivel3.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                jcmbNivel3FocusLost(evt);
            }
        });
        jPanel1.add(jcmbNivel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(363, 38, 100, -1));

        jTextField9.setName("jTextField9"); // NOI18N
        jPanel1.add(jTextField9, new org.netbeans.lib.awtextra.AbsoluteConstraints(16, 135, 554, -1));

        jLabel15.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel15.setText("Orden de encapsulacion");
        jLabel15.setName("jLabel15"); // NOI18N
        jPanel1.add(jLabel15, new org.netbeans.lib.awtextra.AbsoluteConstraints(16, 114, -1, -1));

        jBEncapsular.setText("Encapsular");
        jBEncapsular.setName("jBEncapsular"); // NOI18N
        jBEncapsular.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jBEncapsularMouseClicked(evt);
            }
        });
       
        jPanel1.add(jBEncapsular, new org.netbeans.lib.awtextra.AbsoluteConstraints(127, 83, 131, -1));

        jBDesencap.setFont(new java.awt.Font("Tahoma 11 Negrita 12 Negrita 12 Simple", 1, 12));
        jBDesencap.setText("Desencapsular");
        jBDesencap.setName("jBDesencap"); // NOI18N
        jBDesencap.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jBDesencapMouseClicked(evt);
            }
        });
        jPanel1.add(jBDesencap, new org.netbeans.lib.awtextra.AbsoluteConstraints(387, 83, -1, 23));

        jcmbNivel4.setName("jcmbNivel4"); // NOI18N
        jcmbNivel4.setPreferredSize(new java.awt.Dimension(23, 18));
        jcmbNivel4.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                jcmbNivel4FocusLost(evt);
            }
        });
        jPanel1.add(jcmbNivel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(556, 37, 74, -1));
        
        jPanel5.setBorder(javax.swing.BorderFactory.createTitledBorder(new javax.swing.border.LineBorder(new java.awt.Color(153, 204, 255), 2, true), "Seleccion MAC", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 14))); // NOI18N
        jPanel5.setName("jPanel5"); // NOI18N
        jPanel5.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel7.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel7.setText("MAC Origen :");
        jLabel7.setName("jLabel7"); // NOI18N
        jPanel5.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(11, 53, 79, -1));

        buttonGroup3.add(jrMacOmanual);
        jrMacOmanual.setText("Manual");
        jrMacOmanual.setName("jrMacOmanual"); // NOI18N
        jrMacOmanual.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                jrMacOmanualFocusGained(evt);
            }
        });
        jPanel5.add(jrMacOmanual, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 33, -1, -1));

        buttonGroup3.add(jrMacOcap);
        jrMacOcap.setText("Capturada");
        jrMacOcap.setName("jrMacOcap"); // NOI18N
        jrMacOcap.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                jrMacOcapFocusGained(evt);
            }
        });
        jPanel5.add(jrMacOcap, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 73, -1, -1));

        jtxtMacOmanual.setEnabled(false);
        jtxtMacOmanual.setName("jtxtMacOmanual"); // NOI18N
        jtxtMacOmanual.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                jtxtMacOmanualFocusLost(evt);
            }
        });
        jPanel5.add(jtxtMacOmanual, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 33, 110, -1));

        jcmbMacOcap.setEnabled(false);
        jcmbMacOcap.setName("jcmbMacOcap"); // NOI18N
        jcmbMacOcap.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                jcmbMacOcapFocusLost(evt);
            }
        });
        jPanel5.add(jcmbMacOcap, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 73, 110, -1));

        jLabel16.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel16.setText("MAC Destino :");
        jLabel16.setName("jLabel16"); // NOI18N
        jPanel5.add(jLabel16, new org.netbeans.lib.awtextra.AbsoluteConstraints(335, 53, -1, -1));

        buttonGroup4.add(jrMacDmanual);
        jrMacDmanual.setText("Manual");
        jrMacDmanual.setName("jrMacDmanual"); // NOI18N
        jPanel5.add(jrMacDmanual, new org.netbeans.lib.awtextra.AbsoluteConstraints(412, 33, 98, -1));

        buttonGroup4.add(jrMacDcap);
        jrMacDcap.setText("Capturada");
        jrMacDcap.setName("jrMacDcap"); // NOI18N
        jPanel5.add(jrMacDcap, new org.netbeans.lib.awtextra.AbsoluteConstraints(413, 73, -1, -1));

        jtxtMacDmanual.setEnabled(false);
        jtxtMacDmanual.setName("jtxtMacDmanual"); // NOI18N
        jtxtMacDmanual.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                jtxtMacDmanualFocusLost(evt);
            }
        });
        jPanel5.add(jtxtMacDmanual, new org.netbeans.lib.awtextra.AbsoluteConstraints(512, 34, 103, -1));

        jcmbMacDcap.setEnabled(false);
        jcmbMacDcap.setName("jcmbMacDcap"); // NOI18N
        jcmbMacDcap.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                jcmbMacDcapFocusLost(evt);
            }
        });
        jPanel5.add(jcmbMacDcap, new org.netbeans.lib.awtextra.AbsoluteConstraints(514, 74, 101, -1));
        pack();
    }// </editor-fold>//GEN-END:initComponents

 
    /**
	 * Añade el dispositivo
	 * 
	 * @param dispo dispositivo
	 * @param des descripcion
	 */
	public static void addDispositivo(String dispo[], String des[]) {
		// TODO: Agrege su codigo aqui: Multiples fichero
		String name;
		String sAux;
		Dispo = new String[dispo.length + 1];
		Dispo[0]= "";
		for (int i = 0; i < dispo.length; i++){
			sAux = des[i].toString().trim();
			if (sAux != "") {
				name = sAux;
			} else {
				// was no extension
				name = dispo[i].toString().trim();
			}
			jcmbDisp.addItem(name);
			Dispo[i+1] = dispo[i];
		}
	}
	
	/**
     * Establece los valores de la tabla
     */
    public static void setTablaCampos(Object tabla[][]){
    	int col=1;
    	int j;
    	for(int i=0;i<tabla.length;i++){
    		//for(int j=0;j<numColumnas;j++){
    			j=0;
    			col=1;
    			if(tabla[i][col].equals("null") ){
    				jTable1.setValueAt("", i, j);	
    			}else{
    				jTable1.setValueAt(tabla[i][col], i, j);
    			}
    			j=1;
    			col=3;
    			j=2;
    			col=2;
    			if(tabla[i][col].equals("null") ){
    				jTable1.setValueAt("", i, j);	
    			}else{
    				jTable1.setValueAt(tabla[i][col], i, j);
    			}
    			j=3;
    			col=5;
    			if(tabla[i][col].equals("null") ){
    				jTable1.setValueAt("", i, j);	
    			}else{
    				jTable1.setValueAt(tabla[i][col], i, j);
    			}
    			col=6;
    			j=4;
    			if(tabla[i][col].equals("null") ){
    				jTable1.setValueAt("", i, j);	
    			}else{
    				jTable1.setValueAt(tabla[i][col], i, j);
    			}
    		//}
    	}
    }
    
    /**
     * Establece erfc del protocolo
     * @param rfc
     */
    public static void setRFCProtocolo(String rfc){
    	jtxtRfc.setText(rfc);
    }
    
    /**
     * Establece el nombre del protocolo
     * @param nombre
     */
	public static void setNombreProtocolo(String nombre){
		jtxtNombre.setText(nombre);
	}
	
	/**
	 * Establece el nivel del protocolo
	 * @param nivel
	 */
	public static void setNivelProtocolo(String nivel){
		jtxtNivel.setText(nivel);
	}
	
	/**
     * Establece las direcciones ip origen
     * @param ipOrigen
     */
	public static void setIpOrigen(ArrayList<String> ipOrigenCap) {
		for(int i=0;i<ipOrigenCap.size();i++){
			jcmbIpOcap.addItem(ipOrigenCap.get(i));
		}
	}

	/**
	 * Establece las direciones ip destino
	 * @param ipDestino
	 */
	public static void setIpDestino(ArrayList<String> ipDestinoCap) {
		for(int i=0;i<ipDestinoCap.size();i++){
			jcmbIpDcap.addItem(ipDestinoCap.get(i));
		}
	}

	/**
	 * Establece las direcciones mac origen
	 * @param macOrigen
	 */
	public static void setMacOrigen(ArrayList<String> macOrigenCap) {
		for(int i=0;i<macOrigenCap.size();i++){
			jcmbMacOcap.addItem(macOrigenCap.get(i));
		}
	}

	/**
	 * Esstablece las direciones mac destino
	 * @param macDestino
	 */
	public static void setMacDestino(ArrayList<String> macDestinoCap) {
		for(int i=0;i<macDestinoCap.size();i++){
			jcmbMacDcap.addItem(macDestinoCap.get(i));
		}
	}

	/**
	 * Recoge el valor del Nivel de enlace.
	 * @param evt
	 */
	private void jcmbNivel1FocusLost(java.awt.event.FocusEvent evt) {
	   enlace = String.valueOf(jcmbNivel1.getSelectedItem());
	   if(enlace.equals("Ethernet") == false && enlace.equals("Seleccione") == false){
		   //cargar definicion de protocolo en la ventana
		   //despues cuando se de a encapsular se crea paquet
		   cargarPaquete(String.valueOf(jcmbNivel1.getSelectedItem()));
	   }
	   if(!encapsulacion.contains(enlace) && enlace.equals("Seleccione") == false){
		   encapsulacion.add(enlace);
	   }
	}
    
	/**
	 * Recoge el valor del nivel de red.
	 * @param evt
	 */
	private void jcmbNivel2FocusLost(java.awt.event.FocusEvent evt) {
		 red = String.valueOf(jcmbNivel2.getSelectedItem());
		 if(red.equals("IP v4") == false && red.equals("IP v6")==false && red.equals("ARP")== false && red.equals("RARP") == false && red.equals("Seleccione") == false){
			   //cargar definicion de protocolo en la ventana
			   //despues cuando se de a encapsular se crea paquet
			 cargarPaquete(String.valueOf(jcmbNivel2.getSelectedItem()));
		  }
		 if(!encapsulacion.contains(red) && red.equals("Seleccione") == false){
			 if(encapsulacion.contains("IP v4") || encapsulacion.contains("IP v6")){
			  	 encapsulacion.clear();
				 encapsulacion.add(enlace);
				 encapsulacion.add(red);
			 }else{
				 encapsulacion.add(red);
			 }
		   }
	}
	
	/**
	 * Recoge el valor del nivel de transporte.
	 * @param evt
	 */
	private void jcmbNivel3FocusLost(java.awt.event.FocusEvent evt) {
        transporte = String.valueOf(jcmbNivel3.getSelectedItem());
        if(!encapsulacion.contains(transporte) && transporte.equals("Seleccione") == false){
 		   encapsulacion.add(transporte);
 	   }
    }
	
	/**
	 * Recoge el valor del nivel de aplicacion.
	 * @param evt
	 */
	private void jcmbNivel4FocusLost(java.awt.event.FocusEvent evt) {
       if(aplicacion.contains(String.valueOf(jcmbNivel4.getSelectedItem())) == false){
    	   aplicacion.add(String.valueOf(jcmbNivel4.getSelectedItem()));
    	   //cargar definicion de protocolo en la ventana
		   //despues cuando se de a encapsular se crea paquet
    	   inicializarTable();
    	   cargarPaquete(String.valueOf(jcmbNivel4.getSelectedItem()));
       }
    }
	
	/**
	 * Carga la definicion del protocolo especificado para determinar los valores.
	 * @param valueOf
	 */
	private void cargarPaquete(String valueOf) {
		boolean encontrado = true;
		String ruta="";	
		
		for(int i=0;i<pref.getTamañoListaIdentificacion() && encontrado == true;i++ ){
			if(pref.getElementoListaIdentificacion(i).getNomProtocolo().equals(valueOf)){
				ruta = pref.getElementoListaIdentificacion(i).getRutaProtocolo();
				encontrado=false;
			}
		}
		if(!ruta.equals("")){
			mediador.PrefDefLeerXMLInsercion(ruta);
		}
	}

	/**
	 * Habilitar 
	 * @param evt
	 */
	private void jrIpOmanualFocusGained(java.awt.event.FocusEvent evt) {
        jtxtIpOmanual.setEnabled(true);
        jcmbIpOcap.setEnabled(false);
    }
	
	/**
	 * Habilitar 
	 * @param evt
	 */
	private void jrIpOcapFocusGained(java.awt.event.FocusEvent evt) {
	    jtxtIpOmanual.setEnabled(false);
	    jcmbIpOcap.setEnabled(true);
	}
	 
	/**
	 * Habilitar 
	 * @param evt
	 */
	private void jrIpDmanualFocusGained(java.awt.event.FocusEvent evt) {
		jtxtIpDmanual.setEnabled(true);
	    jcmbIpDcap.setEnabled(false);
	}
	
	/**
	 * Encapsula la definicion del protocolo creada por el usuario.
	 * @param evt
	 */
	private void jBEncapDefinidosMouseClicked(java.awt.event.MouseEvent evt) {
		jtxtDirDef.requestFocus();
		
		if(!encapsulacion.contains(String.valueOf(jcmbNivel4.getSelectedItem())) && enlace != "" && red!="" && (transporte.equals("TCP") || transporte.equals("UDP"))){
			if(obligatoriosRellenados()){
				definidos.add(datosDefinidos());
				//ordenarEncapsulacion();
				//orden+=String.valueOf(jcmbNivel4.getSelectedItem());
				encapsulacion.add(String.valueOf(jcmbNivel4.getSelectedItem()));
				if(orden==""){
					orden+=jcmbNivel4.getSelectedItem();
				}else{
					orden+="->";
					orden+=jcmbNivel4.getSelectedItem();
				}
			}else{
				Mediador med = new Mediador();
				med.lanzaraviso("Campos Obligatorios Vacios", "Advertencia");
			}
		}
		 jTextField9.setText(orden);
	        
	}
	
	/**
	 * Comrpueba si los campos obligatorios estan rellenados.
	 * @return
	 */
	private boolean obligatoriosRellenados() {
		int fila=0;
		while(getValorTabla(fila,0)!= null){
			if(getValorTabla(fila,4).equals("No") && getValorTabla(fila,1).equals("")){
				return false;
			}
			fila++;
		}
		return true;
	}

	/**
	 * Recoge los datos definidos para la creacion del protocolo.
	 * @return
	 */
	private byte[] datosDefinidos() {
		byte [] data ;
		String aux;
		int fila=0,cont=0,tam=0;
		
		while(getValorTabla(fila,1)!= null){
			tam = Integer.valueOf(String.valueOf(getValorTabla(fila,2))).intValue()/8;
			cont+=tam;
			fila++;
		}
		data = new byte [cont];
		fila=0;
		int pos = 0;
		while(getValorTabla(fila,0)!= null){
			if(getValorTabla(fila,4)!= ""){
				aux=String.valueOf(getValorTabla(fila,1));
				byte cambio [] = new byte[Integer.valueOf(String.valueOf(getValorTabla(fila,2))).intValue()/8];
				//data+=convertirBinario(aux,String.valueOf(getValorTabla(fila,3)),String.valueOf(getValorTabla(fila,2)));
				//recoger lo definido pero comom cadena
				cambio=recogerDatos(aux,String.valueOf(getValorTabla(fila,3)),String.valueOf(getValorTabla(fila,2)));
				if(cambio!=null){
				    int j=0;
					while (pos < data.length && j < cambio.length){
						data[pos]=cambio[j];
						pos++;
						j++;
					}
				}
				fila++;
			}
		}
		return data;
		
	}

	/**
	 * Convierte valor definido como alfanumerico a su correspondiente valor en binario.
	 * @param cadena
	 * @param tamaño
	 * @return
	 */
	private String convertirAlfanumerico(String cadena,String tamaño) {
		String binario="",aux,cero="0";
		char letra;
		int tam=Integer.valueOf(tamaño);
		
		for(int i=0;i<(tam/8) && i<cadena.length() ;i++){
		    letra=cadena.charAt(i);
			aux=String.format(Integer.toBinaryString(letra));
			while(aux.length()<8){
				aux=cero.concat(aux);
			}
			binario+=aux;
			
		}
		
		while(binario.length()!=tam ){
			binario=cero.concat(binario);
		}
		return binario;
	}

    /**
     * Convierte el valor definido a su valor binario en funcion del tipo de dato.
     * @param cadena
     * @param tipo
     * @param tamaño
     * @return
     */
	private String convertirBinario(String cadena,String tipo,String tamaño){
		String aux[] = cadena.split(" ");
		String cad="";
		for(int i=0;i<aux.length;i++){
			cad+=aux[i];
		}
		if(tipo.equals("Booleano")){
			return convertirBooleano(cadena,tamaño);
		}
		if(tipo.equals("Numerico")){
			return convertirNumerico(cadena,tamaño);
		}
		if(tipo.equals("Alfanumerico")){
			return convertirAlfanumerico(cadena,tamaño);
		}
		return null;
	}
	/**
	 * Convierte cadena de tipo numerica a su correspondiente valor en binario.
	 * @param cadena
	 * @param tamaño
	 * @return
	 */
	private String convertirNumerico(String cadena,String tamaño) {
		String binario,cero ="0";
		String aux="";
		//solo recojo los caracteres que determina el tamaño
		int tam=Integer.valueOf(tamaño);
		
		for(int i=0;i<(tam/8) && i<cadena.length();i++){
			aux+=cadena.charAt(i);
		}
		binario=String.format(Integer.toBinaryString(Integer.valueOf(aux)));
		while(binario.length()!=tam ){
			//si el tamaño no coincide con el maximo concateno 0 por la izq
			binario=cero.concat(binario);
		}
		return binario;
	}

	/**
	 * Convierte valor definido como booleano a su correspondiente valor en binario.
	 * @param cadena
	 * @param tamaño
	 * @return
	 */
	private String convertirBooleano(String cadena,String tamaño){
		int tam=Integer.valueOf(tamaño);
		String aux="";
		if(cadena.equals("verdadero") || cadena.equals("true")){
			aux+="1";
			for(int i=0;i<tam;i++){
				aux+="0";
			}
			return aux;
		}else{
			for(int i=0;i<tam;i++){
				aux+="0";
			}
			return aux;
		}
	}
	
	/**
     * Devuelve le valor pedido de la tabla
     * @args int fila int columna
     */
    public static Object getValorTabla(int fila,int columna){
        return jTable1.getValueAt(fila, columna);
    }

	/**
	 * Rellena los Niveles de protocolo con los datos de los protcoloc definidos.
	 */
	private void rellenarNiveles(){
		pref = new PrefIdentificacion();
		if(this.pref != null){
			for(int i=0;i< pref.getTamañoListaIdentificacion();i++){
				if(pref.getElementoListaIdentificacion(i).getNivelProtocolo().equals("2")){
					jcmbNivel1.addItem(pref.getElementoListaIdentificacion(i).getNomProtocolo());
				}
				if(pref.getElementoListaIdentificacion(i).getNivelProtocolo().equals("3")){
					jcmbNivel2.addItem(pref.getElementoListaIdentificacion(i).getNomProtocolo());
				}
				if(pref.getElementoListaIdentificacion(i).getNivelProtocolo().equals("4")){
					jcmbNivel3.addItem(pref.getElementoListaIdentificacion(i).getNomProtocolo());
				}
				if(pref.getElementoListaIdentificacion(i).getNivelProtocolo().equals("5")){
					jcmbNivel4.addItem(pref.getElementoListaIdentificacion(i).getNomProtocolo());
				}
				
			}
		}
	}
	
	
	
	/**
	 * Habilitar 
	 * @param evt
	 */
	 private void jrIpDcapFocusGained(java.awt.event.FocusEvent evt) {
		 jtxtIpDmanual.setEnabled(false);
		 jcmbIpDcap.setEnabled(true);
	 }
	 
	 /**
	  * Muestra el orden de encapsulacion definido para el paquete.
	  */
	 private void mostrarOrden(){
		 orden="";
		 for(int i=0;i<encapsulacion.size();i++){
			 orden+=encapsulacion.get(i);
			 orden+="->";
		 }
		 ordenarEncapsulacion();
		 jTextField9.setText(orden);
	 }
	 
	 /**
	  * Carga el orden de encapsulacion.
	  * @param evt
	  */
	 private void jBEncapsularMouseClicked(java.awt.event.MouseEvent evt) {
		 mostrarOrden();
	 }
	 
	 /**
	  * Devuelve el orden de encapsulacion.
	  */
	 public String getOrdenEncapsulacion(){
		 return this.orden;
	 }
	 
	 /**
	  * Establece el dispositivo seleccionado.
	  * @param evt
	  */
	 private void jcmbDispFocusLost(java.awt.event.FocusEvent evt) {
		  dispoSel= String.valueOf(jcmbDisp.getSelectedItem());
	 }
	 
	 /**
	  * Devuelve el dispositivo seleccionado. 
	  * @return
	  */
	  public static String getDispositivo(){
		  return dispoSel;
	  }
	
	 /**
	  * Crea instancia del paqute defindo por el usuario.   
	 * @throws IOException 
	  */

	public static Packet crearPaquete() throws IOException {
		 EthernetPacket ether=new EthernetPacket();
		 TCPPacket p1 = null;
		 UDPPacket p2 = null;
		 ICMPPacket p3 = null;
		 ARPPacket p4 = null;
		 ARPPacket p5 = null;
		 
		String[] lista = jTextField9.getText().split("->");
		if(!validar()){ 
			return null;
		}else{
		if(lista[0].equals("Ethernet")){
			ether.frametype=EthernetPacket.ETHERTYPE_IP;
			//set source and destination MAC addresses
			//añadir las seleccionados
			if(macOr.equals("")){
				ether.src_mac=new byte[]{(byte)1,(byte)2,(byte)3,(byte)4,(byte)5,(byte)6};
			}else{
				String [] mac = macOr.split(":");
				Integer [] aux = new Integer [mac.length];
				for(int i=0;i<mac.length;i++){
					aux[i]=Integer.parseInt(mac[i], 16);
				}
				byte[] macAux = new byte [aux.length];
				for(int i=0;i<aux.length;i++){
					macAux[i]=(byte)aux[i].byteValue();
				}
				ether.src_mac=macAux;
			}
			if(macDes.equals("")){
				ether.dst_mac=new byte[]{(byte)0,(byte)6,(byte)7,(byte)8,(byte)9,(byte)10};
			}else{
				String [] mac = macDes.split(":");
				Integer [] aux = new Integer [mac.length];
				for(int i=0;i<mac.length;i++){
					aux[i]=Integer.parseInt(mac[i], 16);
				}
				byte[] macAux = new byte [aux.length];
				for(int i=0;i<aux.length;i++){
					macAux[i]=(byte)aux[i].byteValue();
				}
				ether.dst_mac=macAux;
			}
		}
		if(lista.length>2){
			if(lista[2].equals("TCP")){
				p1=new TCPPacket(Integer.valueOf(puertoOrigen),Integer.valueOf(puertoDestino),56,78,false,false,false,false,true,true,true,true,10,10);
			}
			if(lista[2].equals("UDP")){
				p2=new UDPPacket(Integer.valueOf(puertoOrigen),Integer.valueOf(puertoDestino));
			}	
			if(lista[2].equals("ICMP")){
				p3=new ICMPPacket();
			}			
			if(lista[1].equals("IP v4") && lista[2].equals("TCP")){
				try {
					p1.setIPv4Parameter(0,false,false,false,0,false,false,false,0,1010101,100,IPPacket.IPPROTO_TCP,
						InetAddress.getByName(ipOr),InetAddress.getByName(ipDes));
				} catch (UnknownHostException e) {
					e.printStackTrace();
				}
			}
		    if(lista[1].equals("IP v4") && lista[2].equals("UDP")){
				try {
					p2.setIPv4Parameter(0,false,false,false,0,false,false,false,0,1010101,100,IPPacket.IPPROTO_UDP,
							InetAddress.getByName(ipOr),InetAddress.getByName(ipDes));
				} catch (UnknownHostException e) {
					e.printStackTrace();
				}
			}
			if(lista[1].equals("IP v4") && lista[2].equals("ICMP")){
				try {
					p3.setIPv4Parameter(0,false,false,false,0,false,false,false,0,1010101,100,IPPacket.IPPROTO_ICMP,
						InetAddress.getByName(ipOr),InetAddress.getByName(ipDes));
				} catch (UnknownHostException e) {
					e.printStackTrace();
				}
			}
			if(lista[1].equals("IP v6") && lista[2].equals("TCP")){
				try {
					p1.setIPv6Parameter(10, 10, 10, 100,InetAddress.getByAddress(convertirIp6(ipOr)),InetAddress.getByAddress(convertirIp6(ipDes)));
				} catch (UnknownHostException e) {
					e.printStackTrace();
				}
			}
			if(lista[1].equals("IP v6") && lista[2].equals("UDP")){
				try {
					p2.setIPv6Parameter(10, 10, 10, 100,InetAddress.getByAddress(convertirIp6(ipOr)),InetAddress.getByAddress(convertirIp6(ipDes)));
				} catch (UnknownHostException e) {
					e.printStackTrace();
				}
			}
			if(lista[1].equals("IP v6") && lista[2].equals("ICMP")){
				try {
					p3.setIPv6Parameter(10, 10, 10, 100,InetAddress.getByAddress(convertirIp6(ipOr)),InetAddress.getByAddress(convertirIp6(ipDes)));
				} catch (UnknownHostException e) {
					e.printStackTrace();
				}
			}
		}
		//Creacion de paquetes arp y rarp
		if(lista[1].equals("ARP")){
			p4=new ARPPacket();
			p4.data=("data").getBytes();
		}		
		
		if(lista[1].equals("ARP Man")){
			p5=new ARPPacket();
			p5.data=("data").getBytes();
		}
		
		//crteacion de icmp
		if(lista.length>2){
			if(lista[2].equals("ICMP")){
				p3.type=ICMPPacket.ICMP_TSTAMP;
				p3.seq=1000;
				p3.id=999;
				p3.orig_timestamp=123;
				p3.trans_timestamp=456;
				p3.recv_timestamp=789;
			}
		}
		if(definidos.size()!=0){
			if(p1!=null){
				int pos = 0;
				for(int i=0;i<definidos.size();i++){
					for(int j=0;j<definidos.get(i).length;j++){
						pos++;
					}
				}
				
				byte datAux [] = new  byte [pos];
				pos =0;
				for(int i=0;i<definidos.size();i++){
					for(int j=0;j<definidos.get(i).length;j++){
						datAux[pos]=definidos.get(i)[j];
						pos++;
					}
				}
				p1.data = datAux;
			}
			if(p2!=null){
				int pos = 0;
				for(int i=0;i<definidos.size();i++){
					for(int j=0;j<definidos.get(i).length;j++){
						p2.data[pos]=definidos.get(i)[j];
						pos++;
					}
				}
			}
		}else{
			if(p1 != null){
				p1.data=("data").getBytes();
			}
			if(p2 != null){
				p2.data=("data").getBytes();
			}
			if(p3 != null){
				p3.data=("data").getBytes();
			}
			if(p4 != null){
				p4.data=("data").getBytes();
			}
			if(p5 != null){
				p5.data=("data").getBytes();
			}
		}
		
		if(p1!=null){
			//set the datalink frame of the packet p as ether
			p1.datalink=ether;
			return p1;
		}
		if(p2 != null){
			p2.datalink=ether;
			return p2;
		}
		if(p3!=null){
			//p3.data=("data").getBytes();
			p3.datalink=ether;
			return p3;
		}
		if(p4!=null){
			//EthernetPacket ether4=new EthernetPacket();
			ether.frametype=EthernetPacket.ETHERTYPE_ARP;
			//ether4.src_mac=InetAddress.getByName(macOr).getAddress();
			//ether4.dst_mac=InetAddress.getByName(macDes).getAddress();
			
			//ether4.src_mac=device.mac_address;
			//ether4.dst_mac=broadcast;
			InetAddress ip = InetAddress.getByName(ipOr);
			NetworkInterface[] devices=JpcapCaptor.getDeviceList();
			NetworkInterface device=null;

			loop:	for(NetworkInterface d:devices){
				for(NetworkInterfaceAddress addr:d.addresses){
					if(!(addr.address instanceof Inet4Address)) continue;
					byte[] bip=ip.getAddress();
					byte[] subnet=addr.subnet.getAddress();
					byte[] bif=addr.address.getAddress();
					for(int i=0;i<4;i++){
						bip[i]=(byte)(bip[i]&subnet[i]);
						bif[i]=(byte)(bif[i]&subnet[i]);
					}
					if(Arrays.equals(bip,bif)){
						device=d;
						break loop;
					}
				}
			}
			
			if(device==null)
				throw new IllegalArgumentException(ip+" is not a local address");
			
			//open Jpcap
			JpcapCaptor captor=JpcapCaptor.openDevice(device,2000,false,3000);
			captor.setFilter("arp",true);
			JpcapSender sender=captor.getJpcapSenderInstance();
			
			InetAddress srcip=null;
			for(NetworkInterfaceAddress addr:device.addresses)
				if(addr.address instanceof Inet4Address){
					srcip=addr.address;
					break;
				}

			byte[] broadcast=new byte[]{(byte)255,(byte)255,(byte)255,(byte)255,(byte)255,(byte)255};
			//ARPPacket arp=new ARPPacket();
			p4.hardtype=ARPPacket.HARDTYPE_ETHER;
			p4.prototype=ARPPacket.PROTOTYPE_IP;
			p4.operation=ARPPacket.ARP_REQUEST;
			p4.hlen=6;
			p4.plen=4;
			p4.sender_hardaddr=device.mac_address;
			p4.sender_protoaddr=srcip.getAddress();
			p4.target_hardaddr=broadcast;
			p4.target_protoaddr=ip.getAddress();
			
			//EthernetPacket ether=new EthernetPacket();
			ether.frametype=EthernetPacket.ETHERTYPE_ARP;
			ether.src_mac=device.mac_address;
			ether.dst_mac=broadcast;
			p4.datalink=ether;
			return p4;
		}
		
		if(p5!=null){
			ether.frametype=EthernetPacket.ETHERTYPE_ARP;
			InetAddress ip = InetAddress.getByName(ipOr);
			InetAddress ipD = InetAddress.getByName(ipDes);
			InetAddress masc = InetAddress.getByName("255.255.255.0");
			InetAddress broad = InetAddress.getByName("255.255.255.255");
			NetworkInterfaceAddress aux = new NetworkInterfaceAddress(ip.getAddress(),masc.getAddress(),broad.getAddress(),ipD.getAddress()); 
			
			if(!(aux.address instanceof Inet4Address)){
				byte[] bip=ip.getAddress();
				byte[] subnet=aux.subnet.getAddress();
				byte[] bif=aux.address.getAddress();
				for(int i=0;i<4;i++){
					bip[i]=(byte)(bip[i]&subnet[i]);
					bif[i]=(byte)(bif[i]&subnet[i]);
				}
			}
	
			InetAddress srcip=null;
			//for(NetworkInterfaceAddress addr:device.addresses)
			//	if(addr.address instanceof Inet4Address){
					srcip=aux.address;
			//		break;
			//	}

			byte[] broadcast=new byte[]{(byte)255,(byte)255,(byte)255,(byte)255,(byte)255,(byte)255};
			//ARPPacket arp=new ARPPacket();
			p5.hardtype=ARPPacket.HARDTYPE_ETHER;
			p5.prototype=ARPPacket.PROTOTYPE_IP;
			p5.operation=ARPPacket.ARP_REQUEST;
			p5.hlen=6;
			p5.plen=4;
			
			String [] mac = macOr.split(":");
			Integer [] auxM = new Integer [mac.length];
			for(int i=0;i<mac.length;i++){
				auxM[i]=Integer.parseInt(mac[i], 16);
			}
			byte[] macAux = new byte [auxM.length];
			for(int i=0;i<auxM.length;i++){
				macAux[i]=(byte)auxM[i].byteValue();
			}
			p5.sender_hardaddr=macAux;
			p5.sender_protoaddr=srcip.getAddress();
			p5.target_hardaddr=broadcast;
			p5.target_protoaddr=ip.getAddress();
			
			//EthernetPacket ether=new EthernetPacket();
			ether.frametype=EthernetPacket.ETHERTYPE_ARP;
			ether.src_mac=macOr.getBytes();
			ether.dst_mac=broadcast;
			p5.datalink=ether;
			return p5;
		}
	}
		return null;
	 }
	  
	/**
	 * Comprueba si los valores necesarios para la insercion estan correctamente creados. 
	 * @return
	 */
	private static  boolean validar() {
		String[] lista = jTextField9.getText().split("->");
		
		if(lista[0].equals("")){
			return false;
		}
		if(puertoOrigen.equals("")){
			return false;
		}
		if(puertoDestino.equals("")){
			return false;
		}
		return true;
	}

	/**
	 * Recoge el valore del puerto origen. 
	 */
	  private void jtxtPorigenFocusLost(java.awt.event.FocusEvent evt) {
			  this.puertoOrigen=jtxtPorigen.getText();	  
	  }
	  
	/**
	 * Recoge le valor del puerto destino.
	 */
	  private void jtxtPdestinoFocusLost(java.awt.event.FocusEvent evt) {
			  this.puertoDestino=jtxtPdestino.getText();
	  }
	  
	/**
	 * Convierte cadena a byte[].  
	 * @param dirIp4
	 * @return
	 */
	private static byte[] convertirIp4(String dirIp4){
		return dirIp4.getBytes();	
	}
	
	/**
	 * Convierte cadena a byte[].  
	 * @param dirIp6
	 * @return
	 */
	private static byte[] convertirIp6(String dirIp6){

		return dirIp6.getBytes(); 
		
	}
	
	/**
	 * Recoge el valor seleccionado de las ip capturadas de destino.
	 * @param evt
	 */
	private void jcmbIpDcapFocusLost(java.awt.event.FocusEvent evt) {
	        ipDes=String.valueOf(jcmbIpDcap.getSelectedItem());
	}
	
	/**
	 * Recoge el valor de las ip capturadas de origen.
	 * @param evt
	 */
	private void jcmbIpOcapFocusLost(java.awt.event.FocusEvent evt) {
        ipOr=String.valueOf(jcmbIpOcap.getSelectedItem());
    }
	  
	private void jtxtIpOmanualFocusLost(java.awt.event.FocusEvent evt) {
		ipOr=String.valueOf(jtxtIpOmanual.getText());
	}
	
	private void jtxtIpDmanualFocusLost(java.awt.event.FocusEvent evt) {
		ipDes=String.valueOf(jtxtIpDmanual.getText());
    }
	
	private void jtxtIp6origenFocusLost(java.awt.event.FocusEvent evt) {
		ipOr=String.valueOf(jtxtIp6origen.getText());
    }
	
	 private void jtxtIp6destinoFocusLost(java.awt.event.FocusEvent evt) {
		 ipDes=String.valueOf(jtxtIp6destino.getText());
	}
	
	/**
	 * Deveuleve el numero de paquetes a enviar.
	 * @return
	 */
	public static int getEnvios(){
		return tot;
	}
	
	/**
	 * Desencapsulacion del ultimo protocolo encapsulado.
	 */
	 private void jBDesencapMouseClicked(java.awt.event.MouseEvent evt) {
		red="";
		enlace="";
		transporte="";
		encapsulacion.clear();
		aplicacion.clear();
		jTextField9.setText("");
		inicializarTable();
		 //String[] lista = jTextField9.getText().split("->");
		// int tam = encapsulacion.size();
		 //encapsulacion.remove(tam-1);
		 //if(definidos.size()!= 0){
		//	 tam = definidos.size();
			 //elimino el valor de los definidos
		//	 definidos.remove((tam-1));
		// }
		// tam = encapsulacion.size();
		// if(tam==0){
		//	 enlace="";
		// }
		// if(tam==1){
		//	 red="";
		// }
		// if(tam==2){
		//	 transporte="";
		// }
		// ordenarEncapsulacion(); 
		// jTextField9.setText(orden);
	 }
	 
	 private void ordenarEncapsulacion(){
		 String encapsular="";
		 if(enlace!=""){
			 
			 encapsular=enlace;
			 encapsulacion.remove(0);
			 encapsulacion.add(0, enlace);
		 }
		 if(enlace != "" && red!=""){
			 //transporte="";
			 encapsular+="->";
			 encapsular+=red;
			 encapsulacion.remove(1);
			 encapsulacion.add(1, red);
		 }
		 if(enlace != "" && red!="" && transporte!=""){
			 encapsular+="->";
			 encapsular+=transporte;
			 encapsulacion.remove(2);
			 encapsulacion.add(2, transporte);
		 }
		 //if(enlace != "" && red!="" && (transporte.equals("TCP") || transporte.equals("UDP")) && aplicacion.size()!=0){ 
			 //solo  puede añadir si contiene udp o tcp
		//	 if(definidos.size() == aplicacion.size()){
		//		 int i=0;
		//		 while(i<aplicacion.size()){
		//			 encapsular+="->";
		//			 encapsular+=aplicacion.get(i);
		//			 encapsulacion.add(aplicacion.get(i));
		//			 i++;
		//		 }
		//	 }
		// }
		 this.orden=encapsular;
	 }
	 
	/**
	 * Inicializa los valores de la tabla de protocolos. 
	 */
	 private void inicializarTable(){
		 for(int i=0;i<20;i++){
			 for(int j=0;j<5;j++){
				 jTable1.setValueAt("", i, j);
				 jTable1.setValueAt(null, i, j);
			 }
		 }
		 jtxtNivel.setText("");
		 jtxtRfc.setText("");
		 jtxtNombre.setText("");
		 jtxtDirDef.setText("");
	}
	 
	/**
	 * inicializa todos los valores de la tabla.
	 */
	 private void inicializarTodo(){
		 inicializarTable();
		 red="";
		 enlace="";
		 aplicacion.clear();
		 transporte="";
		 ipOr="";
		 ipDes="";
		 definidos.clear();
		 dispoSel="";
		 encapsulacion.clear();
		 macDes="";
		 macOr="";
		 puertoDestino="";
		 puertoOrigen="";
		 jtxtMacDmanual.setText("");
		 jtxtMacOmanual.setText("");
		 jtxtPdestino.setText("");
		 jtxtPorigen.setText("");
		 jTextField9.setText("");
		 jtxtIp6destino.setText("");
		 jtxtIp6origen.setText("");
		 jtxtIpDmanual.setText("");
		 jtxtIpOmanual.setText("");
		 definidos.clear();
		 
			
	 }
	 
	 /**
	  * Inicializa los valores del formulario.
	  * @param evt
	  */
	 private void jBLimpiarMouseClicked(java.awt.event.MouseEvent evt) {
		 inicializarTodo();
	 }
	 
	 /**
	  * Recoge los valores definidos.
	  */
	 private byte[] recogerDatos(String cadena, String tipo, String tamaño) {
		String aux="";
		char letra;
		int tam=Integer.valueOf(tamaño);
		
		if(tipo.equals("Alfanumerico")){
			char cad [] = cadena.toCharArray();
			if(cad.length <= tam/8){
				byte cadAux [] = new byte [cad.length];
				for(int i=0;i<cad.length;i++){
					cadAux[i]=(byte)cad[i];
				}
				return cadAux;
			}else{
				mediador.lanzaraviso("La cadena "+cadena+" es demasiado grande","Error");
			}
		}
		if(tipo.equals("Numerico")){
			if(validarNumericos(cadena)){
				Integer pas=Integer.valueOf(cadena);
				int res = tam/8;
				if(res == 1){
					byte[] auxB = new byte[] {
							(byte)pas.intValue()};
					return auxB;
				}
				if(res == 2){
					byte[] auxB = new byte[] {
							(byte)(pas.intValue() >>> 8),
			                (byte)pas.intValue()};
					return auxB;
				}
				if (res==3){
					byte[] auxB = new byte[] {
							(byte)(pas.intValue() >>> 16),
							(byte)(pas.intValue() >>> 8),
							(byte)pas.intValue()};
					return auxB;
				}
				if(res == 4){
					byte[] auxB = new byte[] {
							(byte)(pas.intValue() >>> 24),
							(byte)(pas.intValue() >>> 16),
							(byte)(pas.intValue() >>> 8),
							(byte)pas.intValue()};
					return auxB;
				}
			}else{
				mediador.lanzaraviso("El valor "+cadena+" no es numerico","Error");
			}
		}
		if(tipo.equals("Booleano")){
			cadena=cadena.toLowerCase();
			if(cadena.equals("true") || tipo.equals("verdadero")){
				byte auxB [] = new byte [1];
				auxB[0]=(byte)1;
				return auxB;
			}
			if(cadena.equals("false") || tipo.equals("falso")){
				byte auxB [] = new byte [1];
				auxB[0]=(byte)0;
				return auxB;
			}
		}
		return null;	
	 }
	 
	 private void jrMacOmanualFocusGained(java.awt.event.FocusEvent evt) {
		 jtxtMacOmanual.setEnabled(true);
	     jcmbMacOcap.setEnabled(false);
	 } 
	
	 private void jrMacOcapFocusGained(java.awt.event.FocusEvent evt) {
		 jtxtMacOmanual.setEnabled(false);
	     jcmbMacOcap.setEnabled(true);
	}
	 
	 private void jrMacDmanualFocusGained(java.awt.event.FocusEvent evt) {
		 jtxtMacDmanual.setEnabled(true);
	     jcmbMacDcap.setEnabled(false);
	 } 
	
	 private void jrMacDcapFocusGained(java.awt.event.FocusEvent evt) {
		 jtxtMacDmanual.setEnabled(false);
	     jcmbMacDcap.setEnabled(true);
	} 
	
	 /**
		 * Recoge el valor seleccionado de las ip capturadas de destino.
		 * @param evt
		 */
		private void jcmbMacDcapFocusLost(java.awt.event.FocusEvent evt) {
			macDes=String.valueOf(jcmbMacDcap.getSelectedItem());
		}
		
		/**
		 * Recoge el valor de las ip capturadas de origen.
		 * @param evt
		 */
		private void jcmbMacOcapFocusLost(java.awt.event.FocusEvent evt) {
	        macOr=String.valueOf(jcmbMacOcap.getSelectedItem());
	    }
		  
		private void jtxtMacOmanualFocusLost(java.awt.event.FocusEvent evt) {
			macOr=String.valueOf(jtxtMacOmanual.getText());
		}
		
		private void jtxtMacDmanualFocusLost(java.awt.event.FocusEvent evt) {
			macDes=String.valueOf(jtxtMacDmanual.getText());
	    }
	 
		 /**
		 * Establece la ruta del fichero de definicion
		 */
		public static void setFicheroDefinicion(String fruta){
			jtxtDirDef.setText(fruta);
		}
		
		/**
		 * Comprueba que el valor pasado unicamnete sea numerico.
		 */
		private static boolean validarNumericos(String cadena){
			try {
				Integer.parseInt(cadena);
				return true;
			} catch (NumberFormatException e) {
				return false;
			}
			
		}
	// Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.ButtonGroup buttonGroup2;
    private javax.swing.ButtonGroup buttonGroup3;
    private javax.swing.ButtonGroup buttonGroup4;
    //private javax.swing.JButton jBCancelar;
    //private javax.swing.JButton jBCaptura;
    private javax.swing.JButton jBCheck;
    //private javax.swing.JButton jBDefinicion;
    private javax.swing.JButton jBEncapsular;
    //private javax.swing.JButton jBInsertar;
    private javax.swing.JButton jBLimpiar;
    private javax.swing.JButton jBDesencap;
    private javax.swing.JButton jBEncapDefinidos;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JScrollPane jScrollPane1;
   // private javax.swing.JTable jTable1;
    private static javax.swing.JTextField jTextField9;
    private static javax.swing.JComboBox jcmbDisp;
    private static javax.swing.JComboBox jcmbIpDcap;
    private static javax.swing.JComboBox jcmbIpOcap;
    private static javax.swing.JComboBox jcmbMacDcap;
    private static javax.swing.JComboBox jcmbMacOcap;
    private javax.swing.JComboBox jcmbNivel1;
    private javax.swing.JComboBox jcmbNivel2;
    private javax.swing.JComboBox jcmbNivel3;
    private javax.swing.JComboBox jcmbNivel4;
    private javax.swing.JComboBox jcmbNivel5;
    private javax.swing.JRadioButton jrIpDcap;
    private javax.swing.JRadioButton jrIpDmanual;
    private javax.swing.JRadioButton jrIpOcap;
    private javax.swing.JRadioButton jrIpOmanual;
    private javax.swing.JRadioButton jrMacDcap;
    private javax.swing.JRadioButton jrMacDmanual;
    private javax.swing.JRadioButton jrMacOcap;
    private javax.swing.JRadioButton jrMacOmanual;
    private javax.swing.JTextField jtxtCapturas;
    private static javax.swing.JTextField jtxtDirDef;
    private javax.swing.JTextField jtxtIpDmanual;
    private javax.swing.JTextField jtxtIpOmanual;
    private javax.swing.JTextField jtxtMacDmanual;
    private javax.swing.JTextField jtxtMacOmanual;
    private static javax.swing.JTextField jtxtNivel;
    private static javax.swing.JTextField jtxtNombre;
    private javax.swing.JTextField jtxtPdestino;
    private javax.swing.JTextField jtxtPorigen;
    private static javax.swing.JTextField jtxtRfc;
    private javax.swing.JTextField jtxtTimestamp;
    private javax.swing.JTextField jtxtIp6destino;
    private javax.swing.JTextField jtxtIp6origen;
    // End of variables declaration//GEN-END:variables
    
}
