package presentacion.capturandoDumper;

/*import jpcap.JpcapCaptor;
import jpcap.PacketReceiver;
import jpcap.JpcapWriter;
import dominio.*;
*/
import dominio.pcapDumper.CountPacketHandler;
import presentacion.propiedadesVentana.CentrarVentana;
import presentacion.ventanaMenuSniffer.MenuSniffer;
import presentacion.Mediador;
import servicioAccesoDatos.FachadaFicheroDirectorios;

import java.util.*;

/**
 * Visualizacion de los paquetes capturados en tiempo real.
 * Muestra los paquetes en grupos de hasta 10.000.
 * Y la estadística total de los paquetes capturados
 * 
 * @author Leonardo García & JoseRamón Gutierrez
 * @version 2.0
 * 
 * @see Fcaptura
 * @see dominio.pcapDumper.CountPacketHandler
 */
public class Fcaptura extends javax.swing.JFrame {

	private javax.swing.JButton jButton1;

	private javax.swing.JButton jButton2;

	private javax.swing.JLabel jLabelpk;

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

	private javax.swing.JLabel jLabel20;

	private javax.swing.JLabel jLabel21;

	private javax.swing.JLabel jLabel22;

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

	private javax.swing.JProgressBar jProgressBar1;

	private javax.swing.JProgressBar jProgressBar2;

	private javax.swing.JProgressBar jProgressBar3;

	private javax.swing.JProgressBar jProgressBar4;

	private javax.swing.JProgressBar jProgressBar5;

	private javax.swing.JProgressBar jProgressBar6;

	private javax.swing.JProgressBar jProgressBar7;

	private javax.swing.JProgressBar jProgressBar8;

	private javax.swing.JScrollPane jScrollPane1;

	private javax.swing.JTextArea jTextArea1;
	/**
	 * estadística de los paquetes capturados
	 */
	public CountPacketHandler RCountPacketHandler;
	/**
	 * control de tiempo.
	 */
	public TimePacket Hilocaptura;
	/**
	 * Receptor
	 *  
	 * @see presentacion.Mediador
	 */
	public Mediador mediador;
	/**
	 * Hilo propio
	 */
	private Thread captureThread;
	/**
	 * Esta activa
	 */
	private boolean isLiveCapture;

	/**
	 * caracter fin de linea
	 */
	protected String newline = "\n";

	/**
	 * total paquetes capturados.
	 */
	private int pktotales;
	/**
	 * contador
	 */
	private int PACKET_COUNT;

	/**
	 * Calse por defecto
	 * 
	 * @param CPH estadística de paquetes
	 */
	public Fcaptura(CountPacketHandler CPH) {
		//super(MenuSniffer.getFrames()[0], "Captura", true);
		int i;
		//        this.venpadre = venpadre;
		setpacketcount();
		initComponents();
		new CentrarVentana(this);

		jProgressBar1.setMinimum(0);
		jProgressBar2.setMinimum(0);
		jProgressBar3.setMinimum(0);
		jProgressBar4.setMinimum(0);
		jProgressBar5.setMinimum(0);
		jProgressBar6.setMinimum(0);
		jProgressBar7.setMinimum(0);
		jProgressBar8.setMinimum(0);

		
		isLiveCapture = true;
		
		RCountPacketHandler = CPH;
		
		/*captureThread = new Thread(new Runnable(){
			//body of capture thread
			public void run() {
				while (captureThread != null) {
					//if (FachadaDominio.getPcapLib().getPcap().processPacket(1, RCountPacketHandler) == 0 && !isLiveCapture)
						//stopCaptureThread();
					//Thread.yield();
				}

				FachadaDominio.getPcapLib().getPcap().close();
			}
		});*/		
		Hilocaptura = new TimePacket(this, 1000, RCountPacketHandler);
		Hilocaptura.setFrameVisibles(1000);
		
		//captureThread.start();
		
		Hilocaptura.start();
		
		mediador = new Mediador();

	}
	
	/**
	 * constuctor de la clase.
	 * 
	 * @param med receptor
	 * @param CPH estadística de paquetes
	 */
	public Fcaptura(Mediador med,CountPacketHandler CPH) {
		//super(MenuSniffer.getFrames()[0], "Captura", true);
		int i;
		//        this.venpadre = venpadre;
		setpacketcount();
		initComponents();
		new CentrarVentana(this);

		jProgressBar1.setMinimum(0);
		jProgressBar2.setMinimum(0);
		jProgressBar3.setMinimum(0);
		jProgressBar4.setMinimum(0);
		jProgressBar5.setMinimum(0);
		jProgressBar6.setMinimum(0);
		jProgressBar7.setMinimum(0);
		jProgressBar8.setMinimum(0);

		
		isLiveCapture = true;
		
		RCountPacketHandler = CPH;
		
		/*captureThread = new Thread(new Runnable(){
			//body of capture thread
			public void run() {
				while (captureThread != null) {
					//if (FachadaDominio.getPcapLib().getPcap().processPacket(1, RCountPacketHandler) == 0 && !isLiveCapture)
						//System.out.println("Debera parar Capturar");
						//stopCaptureThread();
					//Thread.yield();
				}

				FachadaDominio.getPcapLib().getPcap().close();
			}
		});*/		
		Hilocaptura = new TimePacket(this, 1000, RCountPacketHandler);
		Hilocaptura.setFrameVisibles(1000);
		
		//captureThread.start();
		
		Hilocaptura.start();

		mediador = med;
		
		//this.setAlwaysOnTop(true);
	}

	/**
	 * This method is called from within the constructor to initialize the form.
	 * WARNING: Do NOT modify this code. The content of this method is always
	 * regenerated by the Form Editor.
	 */
	private void initComponents() {

		jPanel1 = new javax.swing.JPanel();

		jLabelpk = new javax.swing.JLabel();

		jLabel1 = new javax.swing.JLabel();
		jLabel2 = new javax.swing.JLabel();
		jLabel3 = new javax.swing.JLabel();
		jLabel4 = new javax.swing.JLabel();
		jLabel5 = new javax.swing.JLabel();
		jLabel6 = new javax.swing.JLabel();
		jLabel7 = new javax.swing.JLabel();
		jProgressBar1 = new javax.swing.JProgressBar();
		jProgressBar2 = new javax.swing.JProgressBar();
		jProgressBar3 = new javax.swing.JProgressBar();
		jProgressBar4 = new javax.swing.JProgressBar();
		jProgressBar5 = new javax.swing.JProgressBar();
		jProgressBar6 = new javax.swing.JProgressBar();
		jProgressBar7 = new javax.swing.JProgressBar();
		jProgressBar8 = new javax.swing.JProgressBar();
		jProgressBar8.setForeground(new java.awt.Color(0, 153, 0));

		jLabel8 = new javax.swing.JLabel();
		jLabel8.setForeground(new java.awt.Color(0, 0, 255));
		jLabel9 = new javax.swing.JLabel();
		jLabel9.setForeground(new java.awt.Color(0, 0, 255));
		jLabel10 = new javax.swing.JLabel();
		jLabel10.setForeground(new java.awt.Color(0, 0, 255));
		jLabel11 = new javax.swing.JLabel();
		jLabel11.setForeground(new java.awt.Color(0, 0, 255));
		jLabel12 = new javax.swing.JLabel();
		jLabel12.setForeground(new java.awt.Color(0, 0, 255));
		jLabel13 = new javax.swing.JLabel();
		jLabel13.setForeground(new java.awt.Color(0, 0, 255));
		jLabel14 = new javax.swing.JLabel();
		jLabel14.setForeground(new java.awt.Color(0, 0, 255));

		jLabel15 = new javax.swing.JLabel();
		jLabel15.setFont(new java.awt.Font("Tahoma", 0, 12));
		jLabel16 = new javax.swing.JLabel();
		jLabel16.setFont(new java.awt.Font("Tahoma", 0, 12));
		jLabel17 = new javax.swing.JLabel();
		jLabel17.setFont(new java.awt.Font("Tahoma", 0, 12));
		jLabel18 = new javax.swing.JLabel();
		jLabel18.setFont(new java.awt.Font("Tahoma", 0, 12));
		jLabel19 = new javax.swing.JLabel();
		jLabel19.setFont(new java.awt.Font("Tahoma", 0, 12));
		jLabel20 = new javax.swing.JLabel();
		jLabel20.setFont(new java.awt.Font("Tahoma", 0, 12));
		jLabel21 = new javax.swing.JLabel();
		jLabel21.setFont(new java.awt.Font("Tahoma", 0, 12));
		jLabel22 = new javax.swing.JLabel();
		jLabel22.setFont(new java.awt.Font("Tahoma", 1, 10));

		jPanel2 = new javax.swing.JPanel();    
		jScrollPane1 = new javax.swing.JScrollPane();
		jScrollPane1.setAutoscrolls(false);
		jTextArea1 = new javax.swing.JTextArea();

		jButton1 = new javax.swing.JButton();
		jButton2 = new javax.swing.JButton();

		getContentPane().setLayout(
				new org.netbeans.lib.awtextra.AbsoluteLayout());

		if (PACKET_COUNT == -1) {
			jProgressBar8.setEnabled(false);
			jProgressBar8.setStringPainted(false);
		} else {
			jProgressBar8.setEnabled(true);
			jProgressBar8.setStringPainted(true);
		}

		setTitle("Paquetes capturados");
		setResizable(false);
		addWindowListener(new java.awt.event.WindowAdapter() {
			public void windowClosing(java.awt.event.WindowEvent evt) {
				exitForm(evt);
			}
		});

		jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

		jPanel1
				.setBorder(new javax.swing.border.TitledBorder(
						"Estadística de Paquetes"));
		jPanel1.add(jProgressBar1,
				new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 30, 60,
						20));

		jLabel1.setText("Ethernet");
		jPanel1.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(
				10, 30, -1, -1));

		jLabel2.setText("Arp");
		jPanel1.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(
				10, 60, -1, -1));

		jLabel3.setText("Ip");
		jPanel1.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(
				10, 90, -1, -1));

		jLabel4.setText("Icmp");
		jPanel1.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(
				10, 120, -1, -1));

		jLabel5.setText("Igmp");
		jPanel1.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(
				10, 150, -1, -1));

		jLabel6.setText("Tcp");
		jPanel1.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(
				10, 180, -1, -1));

		jLabel7.setText("Udp");
		jPanel1.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(
				10, 210, -1, -1));

		jPanel1.add(jProgressBar2,
				new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 60, 60,
						20));

		jPanel1.add(jProgressBar3,
				new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 90, 60,
						20));

		jPanel1.add(jProgressBar4,
				new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 120, 60,
						20));

		jPanel1.add(jProgressBar5,
				new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 150, 60,
						20));

		jPanel1.add(jProgressBar6,
				new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 180, 60,
						20));

		// Panel 1 etiquetas correspondientes a paquetes capturados en
		// Timepacket.

		jPanel1.add(jProgressBar7,
				new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 210, 60,
						20));
		jPanel1.add(jLabel22,
				new org.netbeans.lib.awtextra.AbsoluteConstraints(185, 10, -1,
						-1));
		jPanel1.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(
				150, 30, -1, -1));
		jPanel1.add(jLabel15,
				new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 30, -1,
						-1));
		jPanel1.add(jLabel20,
				new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 180, -1,
						-1));
		jPanel1.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(
				150, 60, -1, -1));
		jPanel1.add(jLabel16,
				new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 60, -1,
						-1));
		jPanel1.add(jLabel10,
				new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 90, -1,
						-1));
		jPanel1.add(jLabel10,
				new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 90, -1,
						-1));
		jPanel1.add(jLabel17,
				new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 90, -1,
						-1));
		jPanel1.add(jLabel11,
				new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 120, -1,
						-1));
		jPanel1.add(jLabel18,
				new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 120, -1,
						-1));
		jPanel1.add(jLabel12,
				new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 150, -1,
						-1));
		jPanel1.add(jLabel19,
				new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 150, -1,
						-1));
		jPanel1.add(jLabel13,
				new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 180, -1,
						-1));
		jPanel1.add(jLabel14,
				new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 210, -1,
						-1));
		jPanel1.add(jLabel21,
				new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 210, -1,
						-1));

		getContentPane().add(
				jPanel1,
				new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 230,
						250));

		jPanel2.setLayout(new java.awt.BorderLayout());

		jPanel2.setBorder(new javax.swing.border.TitledBorder("Paquetes"));

		jTextArea1.setBackground(new java.awt.Color(0, 0, 0));
		jTextArea1.setForeground(new java.awt.Color(0, 153, 0));
		jScrollPane1.setViewportView(jTextArea1);

		jPanel2.add(jLabelpk, java.awt.BorderLayout.NORTH);
		jPanel2.add(jScrollPane1, java.awt.BorderLayout.CENTER);
//		jPanel2.add(jProgressBar8, java.awt.BorderLayout.SOUTH);

		getContentPane().add(
				jPanel2,
				new org.netbeans.lib.awtextra.AbsoluteConstraints(238, 0, 460,
						250));

		jButton1.setIcon(new javax.swing.ImageIcon(
				FachadaFicheroDirectorios.getdirectorioData("DIR_IMAGES") + 
				System.getProperty("file.separator") + 
				"stop.png"));
		jButton1.setText("PARAR Captura");
		jButton1.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				jButton1ActionPerformed(evt);
			}
		});
		getContentPane().add(
				jButton1,
				new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 260, -1,
						-1));

/*		jButton2.setIcon(new javax.swing.ImageIcon(
				FachadaFicheroDirectorios.getdirectorioData("DIR_IMAGES") + 
				System.getProperty("file.separator") + 
				"visualframe.png"));
		jButton2.setRolloverEnabled(false);
		jButton2.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				jButton2ActionPerformed(evt);
			}
		});

		getContentPane().add(
				jButton2,
				new org.netbeans.lib.awtextra.AbsoluteConstraints(640, 260, 50,
						30));
*/
		pack();
	}

	/**
	 * .
	 * @param evt .
	 */
	private void exitForm(java.awt.event.WindowEvent evt) {
		this.salir();
	}

	/**
	 * .
	 * @param evt .
	 */
	private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {
		this.salir();
	}

	/**
	 * 
	 */
	public void salir() {
		mediador.irFinCapturePcapLib();
		//mediador.deshabilitarBHelemento(0);
		//mediador.deshabilitarBHelemento(1);
		mediador.habiliatarBHelemento(3);
		mediador.habiliatarBHelemento(4);
		//mediador.deshabilitarComponenteBarraMenus(1, 0);
		mediador.deshabilitarComponenteBarraMenus(1, 1);
		//mediador.deshabilitarComponenteBarraMenus(1, 3);
		//mediador.deshabilitarComponenteBarraMenus(0, 0);
		mediador.habilitarComponenteBarraMenus(0, 1); //Archivo - Guardar Fichero de Capturas
		mediador.EnabledComponenteBarraMenus(0,3,0,true); //Archivo - Export - Captura a XML..
		this.dispose();
	}

	/**
	 * 
	 * @param evt .
	 */
	private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {
		// venpadre.visualframe = true;
		//venpadre.frame.show();
	}

	/**
	 * 
	 * @param pktotales .
	 */
	public void pkrecibidos(int pktotales) {
		this.pktotales = pktotales + 1;
	}

	/**
	 * 
	 * @param pakete .
	 */
	public void pkether(String pakete) {
		jTextArea1.append(pakete + newline);
	}

	/**
	 * 
	 * @param pakete .
	 */
	public void pkARP(String pakete) {
		jTextArea1.append(pakete + newline);
	}
	/**
	 * 
	 * @param pakete .
	 */
	public void pkIP(String pakete) {
		jTextArea1.append(pakete + newline);

	}

	/**
	 * 
	 * @param pakete .
	 */
	public void pkICMP(String pakete) {
		jTextArea1.append(pakete + newline);

	}

	/**
	 * 
	 * @param pakete .
	 */
	public void pkIGMP(String pakete) {
		jTextArea1.append(pakete + newline);

	}

	/**
	 * 
	 * @param pakete .
	 */
	public void pkTCP(String pakete) {
		jTextArea1.append(pakete + newline);

	}

	/**
	 * 
	 * @param pakete .
	 */
	public void pkUDP(String pakete) {
		jTextArea1.append(pakete + newline);

	}

	/**
	 * 
	 */
	public void setpacketcount() {
		//PACKET_COUNT = venpadre.getPacketCount();
	}

	/**
	 * 
	 */
	public void borrarTextArea() {
		jTextArea1.setText("");

	}
	
	/**
	 * Muestra los paquetes.
	 * 
	 * @param numP numero de paquetes
	 * @param aux paquetes
	 */
	public void mostrarPackets(int numP, Vector aux) {
		int numSize,cont;
		numSize =aux.size();
		for (int i = 0; i < numSize; i++) {
			cont= numP - numSize + i;
			jTextArea1.append("(" + cont + ")" + aux.elementAt(i) + newline);
		}


	}

	/**
	 * 
	 * @param total .
	 * @param numpackets .
	 * @param ethernet .
	 * @param arp .
	 * @param ip .
	 * @param icmp .
	 * @param igmp .
	 * @param tcp .
	 * @param udp .
	 */
	public void intervalo(int total,int numpackets, int ethernet, int arp, int ip,
			int icmp, int igmp, int tcp, int udp) {

		int p_ethernet;
		int p_arp;
		int p_ip;
		int p_icmp;
		int p_igmp;
		int p_tcp;
		int p_udp;

		//if ( (PACKET_COUNT != -1) && (!venpadre.hilo.isAlive()) ) {
		//if ( (PACKET_COUNT != -1)) {
		//   pktotales = PACKET_COUNT;
		//    jButton1.setEnabled(false);
		//}

		jLabel22.setText("Num.");
		jLabelpk.setText("Total paquetes :" + String.valueOf(total));

		jProgressBar8.setMaximum(PACKET_COUNT);
		jProgressBar8.setValue(total);

		if (numpackets != 0) {
			p_ethernet = (ethernet * 100) / numpackets;
			p_arp = (arp * 100) / numpackets;
			p_ip = (ip * 100) / numpackets;
			p_icmp = (icmp * 100) / numpackets;
			p_igmp = (igmp * 100) / numpackets;
			p_tcp = (tcp * 100) / numpackets;
			p_udp = (udp * 100) / numpackets;
		} else {
			p_ethernet = ethernet;
			p_arp = 0;
			p_ip = 0;
			p_icmp = 0;
			p_igmp = 0;
			p_tcp = 0;
			p_udp = 0;
		}

		jProgressBar1.setMaximum(numpackets);
		jProgressBar1.setValue(ethernet);

		jLabel8.setText(String.valueOf(p_ethernet) + "%");
		jLabel15.setText(String.valueOf(ethernet));

		jProgressBar2.setMaximum(numpackets);
		jProgressBar2.setValue(arp);

		jLabel9.setText(String.valueOf(p_arp) + "%");
		jLabel16.setText(String.valueOf(arp));

		jProgressBar3.setMaximum(numpackets);
		jProgressBar3.setValue(ip);

		jLabel10.setText(String.valueOf(p_ip) + "%");
		jLabel17.setText(String.valueOf(ip));

		jProgressBar4.setMaximum(numpackets);
		jProgressBar4.setValue(icmp);

		jLabel11.setText(String.valueOf(p_icmp) + "%");
		jLabel18.setText(String.valueOf(icmp));

		jProgressBar5.setMaximum(numpackets);
		jProgressBar5.setValue(igmp);

		jLabel12.setText(String.valueOf(p_igmp) + "%");
		jLabel19.setText(String.valueOf(igmp));

		jProgressBar6.setMaximum(numpackets);
		jProgressBar6.setValue(tcp);

		jLabel13.setText(String.valueOf(p_tcp) + "%");
		jLabel20.setText(String.valueOf(tcp));

		jProgressBar7.setMaximum(numpackets);
		jProgressBar7.setValue(udp);

		jLabel14.setText(String.valueOf(p_udp) + "%");
		jLabel21.setText(String.valueOf(udp));

		this.jTextArea1.repaint();
		this.repaint();

	}

} // fin Fcaptura

