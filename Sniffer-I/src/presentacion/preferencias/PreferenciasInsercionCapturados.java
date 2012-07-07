package presentacion.preferencias;

import presentacion.Mediador;
import presentacion.comandos.CBAbrirFichero;
import presentacion.comandos.CBAceptar;
import presentacion.comandos.CBGuardarFichero;
import presentacion.comandos.CBInicioInsertCap;
import presentacion.comandos.CBInsert;
import presentacion.comandos.Comando;
import presentacion.propiedadesVentana.CentrarVentana;
import presentacion.ventanaMenuSniffer.MenuSniffer;

/**
 * Ventana donde seleccionamos el fichero de capturas y reenviamos los paquetes capturados.
 * 
 * @author Carlos Mardones Muga
 * @version 1.0
 * 
 * @see javax.swing.JDialog
 */
public class PreferenciasInsercionCapturados extends javax.swing.JDialog {
	/** * Titulo de la ventana. */
	   private static String title;
	/** * Lista de dispositivos. */
	  private static String[] Dispositivos = { "Seleccione un dispositivo", };
	/** * Lista de dispositivos. */
	  private static String[] Dispo;
	/** Numero de repeticiones para insertar el fichero*/
	  private static int rep=0;
	/** Dispositivo selecionado.*/
	  private static String dispSel="";
	
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
	Comando jBAbrirExportacion; 
	/**
	 * Comando encargado de insertar los paquetes.
	 * 
	 * @see presentacion.comandos.Comando
	 * @see presentacion.comandos.CJMAbrirFichero
	 */
	Comando jBInsertar; 
	/**
	 * Comando encargado de cerrar la ventana.
	 * 
	 * @see presentacion.comandos.Comando
	 * @see presentacion.comandos.CBAceptar
	 */
	Comando jBcancelar; 
	
	
    /** Creates new form PreferenciasInsercionCapturados */
    public PreferenciasInsercionCapturados() {
    	super(MenuSniffer.getFrames()[0], "Insercion de Paquetes Capturados", true);
    	title = "Insercion";
    	//tipo = true;
		mediador = new Mediador();
		initComponents();
		((CBAbrirFichero) jBAbrirExportacion).setVisible(true);
		((CBAceptar) jBInsertar).setVisible(true);
		((CBAceptar) jBcancelar).setVisible(true);
		this.setResizable(false);
		new CentrarVentana(this);
    }

    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        jPanel1 = new javax.swing.JPanel();
        jTextField1 = new javax.swing.JTextField();
        //jBAbrirExportacion = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jRunicas = new javax.swing.JRadioButton();
        jRdet = new javax.swing.JRadioButton();
        jRindet = new javax.swing.JRadioButton();
        jTextField2 = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jComboBox1 = new javax.swing.JComboBox();
        jLabel4 = new javax.swing.JLabel();
        jRadioButton1 = new javax.swing.JRadioButton();
        jRadioButton2 = new javax.swing.JRadioButton();
        jTextField3 = new javax.swing.JTextField();
        jComboBox2 = new javax.swing.JComboBox();
        //jBInsertar = new javax.swing.JButton();
        //jBcancelar = new javax.swing.JButton();
        
        jBAbrirExportacion = new CBAbrirFichero(mediador,"Abrir fichero de Capturas Insercion...");
        jBInsertar = new CBAceptar(mediador,"Insertar Paquetes Capturados");
        jBcancelar = new CBAceptar(mediador,"Salir");
        
        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setName("Form"); // NOI18N

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(new javax.swing.border.LineBorder(new java.awt.Color(153, 204, 255), 2, true), "", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 11), new java.awt.Color(153, 204, 255))); // NOI18N
        jPanel1.setName("jPanel1"); // NOI18N

        jTextField1.setName("jTextField1"); // NOI18N

      //  jBAbrirExportacion.setName("jBAbrirExportacion"); // NOI18N

        jLabel1.setText("Exportación:");
        jLabel1.setName("jLabel1"); // NOI18N

        jLabel2.setText("Nº Renvios:");
        jLabel2.setName("jLabel2"); // NOI18N

        buttonGroup1.add(jRunicas);
        jRunicas.setText("Unico");
        jRunicas.setName("jRunicas"); // NOI18N
        jRunicas.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                jRunicasFocusGained(evt);
            }
        });

        buttonGroup1.add(jRdet);
        jRdet.setText("Repeticiones determinadas");
        jRdet.setName("jRdet"); // NOI18N
        jRdet.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                jRdetFocusGained(evt);
            }
        });

        buttonGroup1.add(jRindet);
        jRindet.setText("Repeticiones ilimitadas");
        jRindet.setName("jRindet"); // NOI18N
        jRindet.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                jRindetFocusGained(evt);
            }
        });

        jTextField2.setName("jTextField2"); // NOI18N
        jTextField2.enable(false);
        jTextField2.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                jTextField2FocusLost(evt);
            }
        });

        jLabel3.setText("Dispositivo:");
        jLabel3.setName("jLabel3"); // NOI18N

        jComboBox1.setName("jComboBox1"); // NOI18N

    

        jRadioButton1.setText("Manual");
        jRadioButton1.setName("jRadioButton1"); // NOI18N

        jRadioButton2.setText("Capturado");
        jRadioButton2.setName("jRadioButton2"); // NOI18N

        jTextField3.setName("jTextField3"); // NOI18N

        jComboBox2.setName("jComboBox2"); // NOI18N

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, 67, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jRindet)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jRdet)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, 59, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jRunicas))
                        .addContainerGap(323, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel3)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jComboBox1, 0, 532, Short.MAX_VALUE))
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 440, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent((CBAbrirFichero)jBAbrirExportacion, javax.swing.GroupLayout.DEFAULT_SIZE, 68, Short.MAX_VALUE)))
                        .addGap(19, 19, 19))))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(28, 28, 28)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(49, 49, 49)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent((CBAbrirFichero)jBAbrirExportacion, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(29, 29, 29)
                .addComponent(jRunicas)
                .addGap(14, 14, 14)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(jRdet)
                    .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jRindet)
                .addContainerGap(19, Short.MAX_VALUE))
        );

        //jBInsertar.setName("jBInsertar"); // NOI18N

 //       jBcancelar.setName("jBcancelar"); // NOI18N

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addGap(121, 121, 121)
                .addComponent((CBAceptar)jBInsertar, javax.swing.GroupLayout.PREFERRED_SIZE, 141, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 182, Short.MAX_VALUE)
                .addComponent((CBAceptar)jBcancelar, javax.swing.GroupLayout.PREFERRED_SIZE, 141, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(161, 161, 161))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(33, 33, 33)
                        .addComponent((CBAceptar)jBInsertar, javax.swing.GroupLayout.DEFAULT_SIZE, 141, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent((CBAceptar)jBcancelar, javax.swing.GroupLayout.PREFERRED_SIZE, 141, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(32, 32, 32))
        );

       // jBInsertar.setName("jBInsertar"); // NOI18N

       // jBcancelar.setName("jBcancelar"); // NOI18N

        //javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(150, 150, 150)
                        .addComponent((CBAceptar)jBInsertar, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(140, 140, 140)
                        .addComponent((CBAceptar)jBcancelar, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent((CBAceptar)jBInsertar, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent((CBAceptar)jBcancelar, javax.swing.GroupLayout.DEFAULT_SIZE, 40, Short.MAX_VALUE))
                .addContainerGap())
        );
        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel(Dispositivos));
        jRunicas.addActionListener(null);
        jComboBox1.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                jComboBox1FocusLost(evt);
            }
        });
        

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
			jComboBox1.addItem(name);
			Dispo[i+1] = dispo[i];
		}
	}

	/**
	 * Establece la ruta del fichero capturado
	 */
	public static void setFicheroCapturas(String fruta){
		jTextField1.setText(fruta);
	}
	
	/**
	 * Establece el valor de las repeticiones para insertar el fichero capturado
	 * @param evt
	 */
	private void jRunicasFocusGained( java.awt.event.FocusEvent evt) {
		jTextField2.setText("");
        this.rep=1;
    }
	
	/**
	 * Establece el valor de las repeticiones para insertar el fichero capturado
	 * @param evt
	 */
	private void jRdetFocusGained(java.awt.event.FocusEvent evt) {
		jTextField2.enable(true);	
    }
	
	/**
	 * Establece el valor de las repeticiones para insertar el fichero capturado
	 * @param evt
	 */
    private void jRindetFocusGained(java.awt.event.FocusEvent evt) {
    	jTextField2.setText("");
    	this.rep=-1;
    }
	
    /**
     * Devuelve el valor de la ruto del fichero de capturas.
     * 
     */
    public static String getRuta(){
    	return jTextField1.getText();
    }
    
    /**
     * Devuelve el numero de repeticiones en la insercion
     */
    public static int getRepeticiones(){
    	return rep;
    }
   
   /**
    * Devuelve el dispositivo selecionado. 
    * @return
    */
   public static String getDispositivo(){
	   return dispSel;
   }
   /**
    * 
    * @param evt
    */
   private void jTextField2FocusLost(java.awt.event.FocusEvent evt) {
	   this.rep=Integer.valueOf(jTextField2.getText());
   }
   
   /**
    * Devuelve numero inserciones del fichero capturado.
    * @return
    */
   public static int getEnvios(){
		return rep;
	}
   
   private void jComboBox1FocusLost(java.awt.event.FocusEvent evt) {
	   dispSel=String.valueOf(jComboBox1.getSelectedItem());
   }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.ButtonGroup buttonGroup1;
    //private javax.swing.JButton jBAbrirExportacion;
    //private javax.swing.JButton jBInsertar;
    //private javax.swing.JButton jBcancelar;
    private static javax.swing.JComboBox jComboBox1;
    private javax.swing.JComboBox jComboBox2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JRadioButton jRadioButton1;
    private javax.swing.JRadioButton jRadioButton2;
    private javax.swing.JRadioButton jRdet;
    private javax.swing.JRadioButton jRindet;
    private javax.swing.JRadioButton jRunicas;
    private static javax.swing.JTextField jTextField1;
    private javax.swing.JTextField jTextField2;
    private javax.swing.JTextField jTextField3;
    // End of variables declaration//GEN-END:variables

}
