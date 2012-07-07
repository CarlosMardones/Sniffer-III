package presentacion.preferencias;

import java.awt.event.*;
import presentacion.Mediador;
import presentacion.comandos.*;
import presentacion.propiedadesVentana.CentrarVentana;
import presentacion.ventanaMenuSniffer.MenuSniffer;
import presentacion.Mediador;
import presentacion.comandos.CBAbrirFichero;
import presentacion.comandos.CBFiltroAyuda;
import presentacion.comandos.CBGuardarFichero;
import presentacion.comandos.Comando;
import presentacion.propiedadesVentana.CentrarVentana;
import presentacion.ventanaMenuSniffer.MenuSniffer;
import javax.swing.table.*;
import javax.swing.*;
/**
 * Ventana donde vemos e introducimos las preferencias de definicion de un protocolo
 * 
 * @author Carlos Mardones Muga
 * @version 1.0
 * 
 * @see javax.swing.JDialog
 */

public class PreferenciasDefinicion extends javax.swing.JDialog {

   /** * Titulo de la ventana. */
   private static String title;
   /** * Si es captura o veer las preferencias. */
   private static boolean tipo;
   /** Nombre del protocolo definido*/
   private static String nomProtocolo="";
   /** RFC del protocolo definido*/
   private static String rfc="";
   /** Numero de campos del protocolo definido*/
   private static int numCampos=0;
   /** Campos clave que identifican el protocolo definido*/
   private static String camposClave = "";
   /** Nivel dentro de la torre de protocolos*/
   private static int nivel=0;
   private static int numColumnas = 8 ;
   private static JTable jTable1;
   /**
	 * Receptor del comando.
	 * 
	 * @see presentacion.Mediador
	 */
	Mediador mediador;
	/**
	 * Comando encargado de abrir la ventana Abrir fichero.
	 * 
	 * @see presentacion.comandos.Comando
	 * @see presentacion.comandos.CJMAbrirFichero
	 */
	Comando jButton4; 
	/**
	 * Comando encargado de abrir la ventana Abrir fichero.
	 * 
	 * @see presentacion.comandos.Comando
	 * @see presentacion.comandos.CJMAbrirFichero
	 */
	Comando jButton1; 
	/**
	 * Comando encargado de aceptar el chequeo de los valores de deinicion del prootocolo.
	 * 
	 * @see presentacion.comandos.Comando
	 * @see presentacion.comandos.CBAceptar
	 */
	Comando jButton3; 
	/**
	 * Comando encargado de guardar la definicion del protocolo en un fichero .xml.
	 * 
	 * @see presentacion.comandos.Comando
	 * @see presentacion.comandos.CBGuardarFichero
	 */
	Comando jButton7; //CBGuardarFichero
	/**
	 * Comando encargado de cerrar la ventana .
	 * 
	 * @see presentacion.comandos.Comando
	 * @see presentacion.comandos.CJMAbrirFichero
	 */
	Comando jButton2; //CBGuardarFichero
	/**
	 * Comando encargado de reordenar los valores de la tabla
	 */
	Comando jButton5;
	
   /** Creates new form PreferenciasDefinicion */
	public PreferenciasDefinicion() {
		super(MenuSniffer.getFrames()[0], "Definicion", true);
		title = "Inicio Definicion";
		tipo = true;
		mediador = new Mediador();
		initComponents();
		((CBAbrirFichero) jButton4).setVisible(true);
		((CBChequear) jButton3).setVisible(true);
		((CBGuardarFichero) jButton7).setVisible(true);
		((CBAceptar) jButton2).setVisible(true);
		this.setResizable(false);
		new CentrarVentana(this);
	}
    /**
     * Inicializacion de componentes del formulario
     */
    
    private void initComponents() {

        jPanel2 = new javax.swing.JPanel();
        jTextField2 = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jLabel3 = new javax.swing.JLabel();
        jTextField3 = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        jTextField4 = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        jTextField5 = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jTextField1 = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        jComboBox1 = new javax.swing.JComboBox();
        jComboBox2 = new javax.swing.JComboBox(); 
        
        jButton4 = new CBAbrirFichero(mediador,"AbrirDefinicionProtocolo");
        jButton7 = new CBGuardarFichero(mediador,"GuardarProtocoloDefinido");
        jButton3 = new CBChequear(mediador,"ChequearDefinicionProtocolo");
        jButton2 = new CBAceptar(mediador,"Salir");
        jButton5 = new CBAceptar(mediador,"RefrescarTablaProtocolo");
        
        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Definicion de Protocolos");
        setName("Form"); // NOI18N

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(new javax.swing.border.LineBorder(new java.awt.Color(153, 204, 255), 2, true), "Protocolo/os generado/os :", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma 11 Negrita", 1, 12))); // NOI18N
        
        jPanel2.setName("jPanel2"); // NOI18N

        jTextField2.setName("jTextField2"); // NOI18N

        jLabel2.setFont(new java.awt.Font("Tahoma 12 Negrita 12 Negrita", 1, 12)); // NOI18N
        jLabel2.setText("Dirección:");
        jLabel2.setName("jLabel2"); // NOI18N

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel2)
                .addGap(18, 18, 18)
                .addComponent(jTextField2, javax.swing.GroupLayout.DEFAULT_SIZE, 668, Short.MAX_VALUE)
                .addGap(18, 18, 18)
                .addComponent((CBAbrirFichero)jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(28, 28, 28)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2)
                    .addComponent((CBAbrirFichero)jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(35, Short.MAX_VALUE))
        );

        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder(new javax.swing.border.LineBorder(new java.awt.Color(153, 204, 255), 2, true), "Campos del protocolo", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma 11 Negrita", 1, 12))); // NOI18N
        jPanel3.setName("jPanel3"); // NOI18N

        jScrollPane1.setName("jScrollPane1"); // NOI18N

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null}
            },
            new String [] {
                "ID Campo", "Nombre del Campo", "Tamaño del campo", "Valor por defecto", "Descripcion", "Tipo de Dato", "Campo Opcional", "Campo Relacionado"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.String.class, java.lang.Integer.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        jTable1.setCellSelectionEnabled(true);
        jTable1.setName("jTable1"); // NOI18N
        jTable1.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        jTable1.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                jTable1FocusGained(evt);
            }
        });
        jScrollPane1.setViewportView(jTable1);
        jTable1.getColumnModel().getSelectionModel().setSelectionMode(javax.swing.ListSelectionModel.SINGLE_INTERVAL_SELECTION);
        jTable1.getColumnModel().getColumn(0).setResizable(false);
        jTable1.getColumnModel().getColumn(1).setResizable(false);
        jTable1.getColumnModel().getColumn(2).setResizable(false);
        jTable1.getColumnModel().getColumn(3).setResizable(false);
        jTable1.getColumnModel().getColumn(4).setResizable(false);

        jLabel3.setText("RFC :");
        jLabel3.setName("jLabel3"); // NOI18N

        jTextField3.setName("jTextField3"); // NOI18N
        jTextField3.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                jTextField3FocusLost(evt);
            }
        });

        jLabel4.setText("Nombre Protocolo :");
        jLabel4.setName("jLabel4"); // NOI18N

        jTextField4.setName("jTextField4"); // NOI18N
        jTextField4.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                jTextField4FocusLost(evt);
            }
        });

        jLabel5.setText("Campos Clave :");
        jLabel5.setName("jLabel5"); // NOI18N

        jTextField5.setName("jTextField5"); // NOI18N
        jTextField5.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jTextField5MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jTextField5MouseExited(evt);
            }
        });
        jTextField5.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                jTextField5FocusLost(evt);
            }
        });

        jLabel6.setName("jLabel6"); // NOI18N

        jLabel1.setText("Numero de Campos :");
        jLabel1.setName("jLabel1"); // NOI18N

        jLabel7.setText("Nivel :");
        jLabel7.setName("jLabel7"); // NOI18N

        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "1", "2", "3", "4", "5" }));
        jComboBox1.setName("jComboBox1"); // NOI18N
        jComboBox1.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                jComboBox1FocusLost(evt);
            }
        });
        
        jComboBox2.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "1", "2", "3", "4", "5", "6", "7", "8", "9", "0", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20" }));
        jComboBox2.setName("jComboBox2"); // NOI18N
        jComboBox2.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                jComboBox2FocusLost(evt);
            }
        });
        

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTextField3, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(26, 26, 26)
                .addComponent(jLabel4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jTextField4, javax.swing.GroupLayout.PREFERRED_SIZE, 76, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(34, 34, 34)
                .addComponent(jLabel7)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 48, Short.MAX_VALUE)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jComboBox2, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(42, 42, 42)
                .addComponent(jLabel5)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jTextField5, javax.swing.GroupLayout.PREFERRED_SIZE, 112, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap(511, Short.MAX_VALUE)
                .addComponent(jLabel6)
                .addGap(357, 357, 357))
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 858, Short.MAX_VALUE)
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap(127, Short.MAX_VALUE)
                .addComponent((CBAceptar)jButton5, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(90, 90, 90)
                .addComponent((CBChequear)jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(90, 90, 90)
                .addComponent((CBGuardarFichero)jButton7,javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(90, 90, 90)
                .addComponent((CBAceptar)jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(90, 90, 90))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(26, 26, 26)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel3)
                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jTextField3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jTextField5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel5)
                        .addComponent(jLabel1)
                        .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel7)
                        .addComponent(jTextField4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel4)
                        .addComponent(jComboBox2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addComponent(jLabel6)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 199, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(53, 53, 53)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent((CBGuardarFichero)jButton7, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent((CBChequear)jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent((CBAceptar)jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent((CBAceptar)jButton5, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(25, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jPanel3, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel2, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(16, Short.MAX_VALUE))
        );

        //añado el comboBox a la tabla 
        
        //Combo que controla el tipo de dato
        JComboBox comboTipo = new JComboBox();
        comboTipo.addItem("Booleano");
        comboTipo.addItem("Numerico");
        comboTipo.addItem("Alfanumerico");
        TableCellEditor editor = new DefaultCellEditor(comboTipo);
        jTable1.getColumnModel().getColumn(5).setCellEditor(editor);
        //Combo que controla el tamaño de los campos
        JComboBox comboTamaño = new JComboBox();
        for(int i=1;i<=32;i++){
        	comboTamaño.addItem(String.valueOf(i));
        }
        TableCellEditor editor2 = new DefaultCellEditor(comboTamaño);
        jTable1.getColumnModel().getColumn(2).setCellEditor(editor2);
        //Combo que controla si el campo es opcional 
        JComboBox comboOpcional = new JComboBox();
        comboOpcional.addItem("Si");
        comboOpcional.addItem("No");
        TableCellEditor editor3 = new DefaultCellEditor(comboOpcional);
        jTable1.getColumnModel().getColumn(6).setCellEditor(editor3);
        pack();
    }
    
   /**
    * Carga automaticamente informacion en la tabla 
    */
    private void jTable1FocusGained(java.awt.event.FocusEvent evt){
    	if(numCampos > 0){
    	  establecerID(numCampos);
    	}
    }
    
    /**
     * Reposiciona el foco de la tabla en el primer elemento 
     */
     private void jTable1FocusLost(java.awt.event.FocusEvent evt){
    	 
     }
    
  /**
   * Genera informacion para rellenar correcatmente los campos
   */
    private void jTextField5MouseEntered(java.awt.event.MouseEvent evt) {
        jLabel6.setText("Separa los campos mediante '-' ej: campo-campo");
    }

   /**
    * Elimina la informacion visualizada en la pantalla
    */
    private void jTextField5MouseExited(java.awt.event.MouseEvent evt) {
      jLabel6.setText(null);
    }
    
   /**
    * Recoje el numero de campos del protocolo
    */
    private void jComboBox2FocusLost(java.awt.event.FocusEvent evt) {
      numCampos=Integer.parseInt((jComboBox2.getSelectedItem()).toString());  
    }
    
    /**
     * Recoje el nivel del protocolo
     */
     private void jComboBox1FocusLost(java.awt.event.FocusEvent evt) {
       nivel = Integer.parseInt((jComboBox1.getSelectedItem()).toString());  
     }
     
   /**
    * Recoje el RFC del protocolo
    */
    private void jTextField3FocusLost(java.awt.event.FocusEvent evt) {
        rfc=jTextField3.getText();
    }
    
    /**
     * Recoje el nombre del protolo
     */
    private void jTextField4FocusLost(java.awt.event.FocusEvent evt) {
        nomProtocolo=jTextField4.getText();
    }

    /**
    * Recoje los campos claves del protocolo
    */
    private void jTextField5FocusLost(java.awt.event.FocusEvent evt) {
        camposClave=jTextField5.getText();
    }
    
    /**
     * Devuelve el nombre del protocolo
     */
    public static String getNombreProtocolo(){
        return nomProtocolo;
    }
    
    /**
     * Devuelve el RFC del protocolo
     */
    public static String getRFCProtocolo(){
        return rfc;
    }
    
    /**
     * Devuelve el nivel del protocolo
     */
    public static int getNivelProtocolo(){
        return nivel;
    }
    
    /**
     * Devuelve el numero de campos del protocolo
     */
    public static int getNumCamposProtocolo(){
        return numCampos;
    }
    
    /**
     * Devuelve el numero de los campos clave para el protocolo
     */
    public static String getCamposClave(){
        return camposClave;
    }
    
    /**
     * Devuelve el numero de columnas de la tabla
     */
    public  static int getColumnas(){
        return numColumnas;
    }
    /**
     * Devuelve le valor pedido de la tabla
     * @args int fila int columna
     */
    public static Object getValorTabla(int fila,int columna){
        return jTable1.getValueAt(fila, columna);
    }
    
    /**
     * Devuelve toda la tabla
     */
    public static Object[][] getTabla(){
    	Object [][] tabla = new Object [numCampos][numColumnas];
    	for(int i=0; i< numCampos;i++){
    	  for(int j=0; j<numColumnas;j++){
    		  tabla[i][j]=getValorTabla(i,j);
    	  }
    	}
    	return tabla;
    }
    
    /**
     * Establece todos los Id Campo de la definicion 
     */
    private void establecerID(int num){
    	for(int i=0; i<num; i++){
    		jTable1.setValueAt(i+1, i, 0);
    	}
    }
    
    /**
     * Establece el nombre del Protocolo
     */
    public static void setNombreProtocolo(String nombre){
    	jTextField4.setText(nombre);
    }
    
    /**
     * Establece el nombre del Protocolo
     */
    public static void setRFCProtocolo(String rfc){
    	jTextField3.setText(rfc);
    }
    
    /**
     * Establece el nombre del Protocolo
     */
    public static void setCamposClaveProtocolo(String claves){
    	jTextField5.setText(claves);
    }
    
    /**
     * Establece el nivel del Protocolo
     */
    public static void setNivelProtocolo(int nivel2){
    	jComboBox1.setSelectedIndex((nivel2-1));
    	nivel = nivel2;
    	
    }
    
    /**
     * Establece el numero de campos del Protocolo
     */
    public static void setCamposProtocolo(int campos){
    	jComboBox2.setSelectedIndex((campos-1));
    	numCampos=campos;
    }
    
    /**
     * Establece los valores de la tabla
     */
    public static void setTablaCampos(Object tabla[][]){
    	for(int i=0;i<numCampos;i++){
    		for(int j=0;j<numColumnas;j++){
    			if(tabla[i][j].equals("null") ){
    				jTable1.setValueAt(" ", i, j);	
    			}else{
    				jTable1.setValueAt(tabla[i][j], i, j);
    			}
    		}
    	}
    }
    
    /**
     * Refresca los valores de definicion estableciendo el foco por todos los elementos
     */
    public static void refrescar (){
    	jTextField3.requestFocus();
    	jTextField4.requestFocus();
    	jTextField5.requestFocus();
    	jComboBox1.requestFocus();
    	jComboBox2.requestFocus();
    	jTextField3.setFocusable(true);
    	
    }
    
    /**
	 * Establece la ruta del fichero capturado
	 */
	public static void setFicheroCapturas(String fruta){
		jTextField2.setText(fruta);
	}
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private static javax.swing.JComboBox jComboBox1;
    private static javax.swing.JComboBox jComboBox2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private static javax.swing.JTextField jTextField1;
    private static javax.swing.JTextField jTextField2;
    private static javax.swing.JTextField jTextField3;
    private static javax.swing.JTextField jTextField4;
    private static javax.swing.JTextField jTextField5;
    // End of variables declaration//GEN-END:variables
}
