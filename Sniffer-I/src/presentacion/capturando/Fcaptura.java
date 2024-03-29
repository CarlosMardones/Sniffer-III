package presentacion.capturando;

import net.sourceforge.jpcap.net.*;
import dominio.*;
import presentacion.propiedadesVentana.CentrarVentana;
import presentacion.Mediador;
import servicioAccesoDatos.FachadaFicheroDirectorios;

/**
 * @author Leonardo
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

	public CountPacketHandler RCountPacketHandler;

	public TimePacket Hilocaptura;

	public Mediador mediador;

	/**
	 * 
	 * @uml.associationEnd multiplicity="(0 1)"
	 */
	// End of variables declaration
	//	public Captura venpadre;
	protected String newline = "\n";

	private int pktotales;

	private int PACKET_COUNT;

	/** Creates new form Fcaptura */
	public Fcaptura() {

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

		RCountPacketHandler = new CountPacketHandler(this);
		FachadaDominio.getPcap().addPacketListener(RCountPacketHandler);
		Hilocaptura = new TimePacket(this, 1000, RCountPacketHandler);
		Hilocaptura.setFrameVisibles(1000);
		Hilocaptura.start();
		

		mediador = new Mediador();
	}
	public Fcaptura(Mediador med) {

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

		RCountPacketHandler = new CountPacketHandler(this);
		FachadaDominio.getPcap().addPacketListener(RCountPacketHandler);
		Hilocaptura = new TimePacket(this, 1000, RCountPacketHandler);
		Hilocaptura.setFrameVisibles(1000);
		Hilocaptura.start();

		mediador = med;
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

		setTitle("Captured Packets");
		setResizable(false);
		addWindowListener(new java.awt.event.WindowAdapter() {
			public void windowClosing(java.awt.event.WindowEvent evt) {
				exitForm(evt);
			}
		});

		jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

		jPanel1
				.setBorder(new javax.swing.border.TitledBorder(
						"CapturedPackets"));
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

		jPanel2.setBorder(new javax.swing.border.TitledBorder("Packets"));

		jTextArea1.setBackground(new java.awt.Color(0, 0, 0));
		jTextArea1.setForeground(new java.awt.Color(0, 153, 0));
		jScrollPane1.setViewportView(jTextArea1);

		jPanel2.add(jLabelpk, java.awt.BorderLayout.NORTH);
		jPanel2.add(jScrollPane1, java.awt.BorderLayout.CENTER);
		jPanel2.add(jProgressBar8, java.awt.BorderLayout.SOUTH);

		getContentPane().add(
				jPanel2,
				new org.netbeans.lib.awtextra.AbsoluteConstraints(238, 0, 460,
						250));

		jButton1.setIcon(new javax.swing.ImageIcon(
				FachadaFicheroDirectorios.getdirectorioData("DIR_IMAGES") + 
				System.getProperty("file.separator") + 
				"stop.png"));
		jButton1.setText("STOP Capture");
		jButton1.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				jButton1ActionPerformed(evt);
			}
		});
		getContentPane().add(
				jButton1,
				new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 260, -1,
						-1));

		jButton2.setIcon(new javax.swing.ImageIcon(
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

		pack();
	}

	private void exitForm(java.awt.event.WindowEvent evt) {
		this.salir();
	}

	private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {
		this.salir();
	}

	public void salir() {
		mediador.irFinCapture();
		mediador.deshabilitarBHelemento(0);
		mediador.deshabilitarBHelemento(1);
		mediador.habiliatarBHelemento(3);
		mediador.habiliatarBHelemento(4);
		mediador.deshabilitarComponenteBarraMenus(1, 0);
		mediador.deshabilitarComponenteBarraMenus(1, 1);
		mediador.deshabilitarComponenteBarraMenus(1, 3);
		mediador.deshabilitarComponenteBarraMenus(0, 0);
		mediador.habilitarComponenteBarraMenus(0, 1);
		mediador.EnabledComponenteBarraMenus(0,3,0,true);
		this.dispose();
	}

	private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {
		// venpadre.visualframe = true;
		//venpadre.frame.show();
	}

	public void pkrecibidos(int pktotales) {
		this.pktotales = pktotales + 1;
	}

	public void pkrawpacket(RawPacket pakete, int i) {
		jTextArea1.append("RawPacket " + i + ":" + pakete + newline);
	}

	public void pkether(String pakete) {
		jTextArea1.append(pakete + newline);
	}

	public void pkARP(String pakete) {
		jTextArea1.append(pakete + newline);
	}

	public void pkIP(String pakete) {
		jTextArea1.append(pakete + newline);

	}

	public void pkICMP(String pakete) {
		jTextArea1.append(pakete + newline);

	}

	public void pkIGMP(String pakete) {
		jTextArea1.append(pakete + newline);

	}

	public void pkTCP(String pakete) {
		jTextArea1.append(pakete + newline);

	}

	public void pkUDP(String pakete) {
		jTextArea1.append(pakete + newline);

	}

	public void setpacketcount() {
		//PACKET_COUNT = venpadre.getPacketCount();
	}

	public void borrarTextArea() {
		jTextArea1.setText("");

	}

	public void intervalo(int numpackets, int ethernet, int arp, int ip,
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

		jLabel22.setText("Packets");
		jLabelpk.setText("Num packets :" + String.valueOf(pktotales));

		jProgressBar8.setMaximum(PACKET_COUNT);
		jProgressBar8.setValue(pktotales);

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

		this.repaint();

	}

} // fin Fcaptura

