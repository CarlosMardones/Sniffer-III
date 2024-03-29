package presentacion.preferencias;

import java.io.IOException;

import javax.swing.ImageIcon;

import jpcap.packet.Packet;
import dominio.pcap.Insercion;
import presentacion.Mediador;
import presentacion.comandos.CBAbrirFichero;
import presentacion.comandos.CBAceptar;
import presentacion.comandos.CBFinInsert;
import presentacion.comandos.CBGuardarFichero;
import presentacion.comandos.Comando;
import presentacion.propiedadesVentana.CentrarVentana;
import presentacion.ventanaMenuSniffer.MenuSniffer;
import servicioAccesoDatos.FachadaFicheroDirectorios;


/**
 * Ventana que muestra el numero de paquetes insertados.
 * 
 * @author Carlos Mardones Muga.
 *
 */
public class PreferenciasVisualizarInsercion extends javax.swing.JDialog {
	
	 /** * Titulo de la ventana. */
	   private static String title;
	 /** * NUmero de paquetes a insertar*/
	   private int tot;
	 /**
	  * Receptor del comando.
	  * 
	  * @see presentacion.Mediador
	 */
		static Mediador mediador;
	/**
	  * Comando encargado de abrir la ventana Abrir fichero.
	  * 
	  * @see presentacion.comandos.Comando
	  * @see presentacion.comandos.CJMAbrirFichero
	  */
	 /**
	  * Hilo de insercion.
	  */
	 static public Insercion insert=null;
	   
    /** Creates new form PreferenciasVisualizarInsercion */
    public PreferenciasVisualizarInsercion(String titulo,int total,Insercion ins) {
    	super(MenuSniffer.getFrames()[0], "Insercion", true);
		title = titulo;
		tot=total;
		insert=ins;
		initComponents();
		this.setResizable(false);
		new CentrarVentana(this);
		
		//this.setVisible(true);
		//insert.start();
    }

 

	/** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    private void initComponents() {

    	jLabel1 = new javax.swing.JLabel();
        jlbNumIns = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jlbImabgen = new javax.swing.JLabel();
        jBSalir = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setName("Form"); // NOI18N

        jLabel1.setText("Insertados :");
        jLabel1.setName("jLabel1"); // NOI18N

        jlbNumIns.setName("jlbNumIns"); // NOI18N

        jButton1.setText("Cancelar");
        jButton1.setName("jButton1"); // NOI18N
        jButton1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButton1MouseClicked(evt);
            }
        });
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jlbImabgen.setName("jlbImabgen"); // NOI18N

        jBSalir.setText("Salir");
        jBSalir.setName("jBSalir"); // NOI18N
        jBSalir.setOpaque(false);
        jBSalir.setRequestFocusEnabled(false);
        jBSalir.setRolloverEnabled(false);
        jBSalir.setVerifyInputWhenFocusTarget(false);
        jBSalir.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jBSalirMouseClicked(evt);
            }
        });
        jBSalir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBSalirActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jlbNumIns, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(28, 28, 28)
                        .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 87, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 76, Short.MAX_VALUE)
                        .addComponent(jBSalir, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(25, Short.MAX_VALUE)
                .addComponent(jlbImabgen, javax.swing.GroupLayout.PREFERRED_SIZE, 238, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(23, 23, 23))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jlbImabgen, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1)
                    .addComponent(jlbNumIns, javax.swing.GroupLayout.PREFERRED_SIZE, 17, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButton1, javax.swing.GroupLayout.DEFAULT_SIZE, 40, Short.MAX_VALUE)
                    .addComponent(jBSalir, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        pack();
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        
        jButton1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButton1MouseClicked(evt);
            }
        });
        
        jlbImabgen.setIcon(new ImageIcon(
				FachadaFicheroDirectorios.getdirectorioData("DIR_IMAGES") + 
				System.getProperty("file.separator") + "transfer.gif"));
       // iniciarInsercion(tot);
        pack();
    }// </editor-fold>
    
    /**
     * Inicializa la insercion de paquetes. 
     * @param tot
     */
    public static void iniciarInsercion(){
    	if(title.equals("Insercion Paquetes Capturados")){
    		//insert = new Insercion(PreferenciasInsercionCapturados.getDispositivo(),PreferenciasInsercionCapturados.getRuta(),PreferenciasInsercionCapturados.getRepeticiones(),2);
    		insert.start();
			
    	}
    	if(title.equals("Insertar Paquetes Definidos")){
			System.out.println("entra en insertar paquetes");
				//p = PreferenciasInsercion.crearPaquete();
		   	    //insert = new Insercion(PreferenciasInsercion.getDispositivo(),p,1);
			insert.start();
		
    	}
    }
    
    /**
     * Detiene la insercion.
     */
    public static void detenerInsercion(){
    	insert.stop();
    }
    
    /**
     * Establece el numero de paquetes insertados.
     */
    public static void setCont(int num){
    	jlbNumIns.setText(String.valueOf(num));
    }
    
    /**
     * Finaliza la insercion.
     * @param evt
     */
    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {
        insert.stop();
    }
    
    private void jButton1MouseClicked(java.awt.event.MouseEvent evt) {
    	insert.stop();
    	detenerInsercion();
    }
    
    public static void cambiarParada(){
    	jlbImabgen.setIcon(new ImageIcon(
				FachadaFicheroDirectorios.getdirectorioData("DIR_IMAGES") + 
				System.getProperty("file.separator") + "fin.gif"));
    }
    
    private void jBSalirMouseClicked(java.awt.event.MouseEvent evt) {                                     
        // TODO add your handling code here:
    	this.dispose();
    }                                    

    private void jBSalirActionPerformed(java.awt.event.ActionEvent evt) {                                        
    	this.dispose();
    }                                       

    
    // Variables declaration - do not modify
    private javax.swing.JButton jBSalir;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private static javax.swing.JLabel jlbImabgen;
    private static javax.swing.JLabel jlbNumIns;
    // End of variables declaration

}
