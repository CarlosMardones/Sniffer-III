package presentacion.visualizarCaptura;

import javax.swing.*;
import javax.swing.JTable;
import javax.swing.table.*;
import javax.swing.JScrollPane;
import javax.swing.JPanel;
import java.awt.Color;
import java.awt.event.*;
import java.io.FileInputStream;
import java.util.Properties;
import java.util.StringTokenizer;


public class TablePane {

	private boolean numeropaquete;

	private boolean time;

	private boolean macsource;

	private boolean macdest;

	private boolean frame;

	private boolean protocol;

	private boolean ipsrc;

	private boolean ipdest;

	private boolean portsrc;

	private boolean portdest;

	private boolean seq;

	private boolean ack;

	private boolean length;

	private boolean size;

	private JPanel Panel;

	protected JScrollPane scrollPane;

	private MiTablaModelo modelo;

	private JTable table;
	
	private JTable auxTable;

	private TableColumn column = null;

	private ListSelectionModel listSelecionModel;

	public VisualizarCaptura venpadre;

	protected Color coloraplicar = Color.RED;

	public TablePane(VisualizarCaptura venpadre) {

		this.venpadre = venpadre;

		modelo = new MiTablaModelo();
		table = new JTable(modelo);
		auxTable = new JTable();
		//AplicarRender();

		table.addMouseListener(new java.awt.event.MouseAdapter() {
			public void mouseClicked(java.awt.event.MouseEvent evt) {
				fileMouseClicked(evt);
			}
		});

		//table.setPreferredScrollableViewportSize(new Dimension(30,30));

		table.getTableHeader().addMouseListener(
				new java.awt.event.MouseAdapter() {
					public void mouseClicked(java.awt.event.MouseEvent evt) {
						tableMouseClicked(evt);
					}
				});

		initColumnSizes(table);
		setValues(false);
		viewColumns();
		getprefToViewColumns();

	}

	public void getprefToViewColumns(){
		int i,count;
		boolean del_add, hayUno=false;
		String auxTableView, auxCampo;
		try {
			
			auxTableView = venpadre.leerPTV();
			
	
			if(auxTableView!=null || auxTableView==""){
				StringTokenizer status=new StringTokenizer(auxTableView,",");
				while(status.hasMoreTokens()){
					auxCampo = status.nextToken().trim();
					count = auxTable.getColumnModel().getColumnCount();
					for (i = 0; i < count; i++) {
						del_add = false;
						column = auxTable.getColumnModel().getColumn(i);
						if (column.getIdentifier().toString().equals(auxCampo)) {
							del_add = true;
						}
						if (del_add) {
							table.addColumn(column);
							auxTable.removeColumn(column);
							i= count;
							activateColumVar(auxCampo);
							hayUno= true;
						}
					}
					
				}
				
			}
			else {
				hayUno = false;
			}
			if (hayUno==false){
				setValues(true);
				viewColumns();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	public void activateColumVar(String sVar){
		if (sVar.equals("Num")) numeropaquete = true;
		if (sVar.equals("Time"))time = true;
		if (sVar.equals("Size"))size = true;
		if (sVar.equals("MAC Src"))macsource = true;
		if (sVar.equals("MAC Dest")) macdest = true;
		if (sVar.equals("Frame")) frame = true;
		if (sVar.equals("Protocol")) protocol = true;
		if (sVar.equals("IP Src")) ipsrc = true;
		if (sVar.equals("IP Dest")) ipdest = true;
		if (sVar.equals("Port Src")) portsrc = true;
		if (sVar.equals("Port Dest")) portdest = true;
		if (sVar.equals("SEQ")) seq = true;
		if (sVar.equals("ACK")) ack = true;
		if (sVar.equals("Length")) length = true;
	}
	
	public void AplicarRender(int fila) {
		TableCellRenderer renderer = new CustomTableCellRenderer(coloraplicar,
				venpadre, fila);
		table.setDefaultRenderer(String.class, renderer);
		table.repaint();
	}

	public void obtenercolor(Color color) {
		coloraplicar = color;
	}

	private void tableMouseClicked(java.awt.event.MouseEvent evt) {
		if ((evt.getModifiers() & InputEvent.BUTTON3_MASK) == InputEvent.BUTTON3_MASK) {
			// Opciones de table al pulsar el boton derecho sobre table.
			new FTableOptions(this).show();
		}

	}

	private void fileMouseClicked(java.awt.event.MouseEvent evt) {
		if ((evt.getModifiers() & InputEvent.BUTTON1_MASK) == InputEvent.BUTTON1_MASK) {
			getfila(table.getSelectedRow());

		}

	}

	/***************************************************************************
	 * Obtenercolumnas Funcion que obtiene la los campos (columnas) de la
	 * cabecera de la tabla
	 **************************************************************************/

	public void setColumnas(boolean numpaquete, boolean time,
			boolean macsource, boolean macdest, boolean frame,
			boolean protocol, boolean ipsrc, boolean ipdest, boolean portsrc,
			boolean portdest, boolean seq, boolean ack, boolean length,
			boolean size) {

		this.numeropaquete = numpaquete;
		this.time = time;
		this.macsource = macsource;
		this.macdest = macdest;
		this.frame = frame;
		this.protocol = protocol;
		this.ipsrc = ipsrc;
		this.ipdest = ipdest;
		this.portsrc = portsrc;
		this.portdest = portdest;
		this.seq = seq;
		this.ack = ack;
		this.length = length;
		this.size = size;
	}
	
	public void viewColumns(){
		int i;
		boolean del_add;

		for (i = 0; i < table.getColumnModel().getColumnCount(); i++) {
			del_add = false;
			column = table.getColumnModel().getColumn(i);
			if (column.getIdentifier().toString().equals("Num")
					&& numeropaquete == false) {
				del_add = true;
			}
			if (column.getIdentifier().toString().equals("Time")
					&& time == false) {
				del_add = true;
			}
			if (column.getIdentifier().toString().equals("Size")
					&& size == false) {
				del_add = true;
			}
			if (column.getIdentifier().toString().equals("MAC Src")
					&& macsource == false) {
				del_add = true;
			}
			if (column.getIdentifier().toString().equals("MAC Dest")
					&& macdest == false) {
				del_add = true;
			}
			if (column.getIdentifier().toString().equals("Frame")
					&& frame == false) {
				del_add = true;
			}
			if (column.getIdentifier().toString().equals("Protocol")
					&& protocol == false) {
				del_add = true;
			}
			if (column.getIdentifier().toString().equals("IP Src")
					&& ipsrc == false) {
				del_add = true;
			}
			if (column.getIdentifier().toString().equals("IP Dest")
					&& ipdest == false) {
				del_add = true;
			}
			if (column.getIdentifier().toString().equals("Port Src")
					&& portsrc == false) {
				del_add = true;
			}
			if (column.getIdentifier().toString().equals("Port Dest")
					&& portdest == false) {
				del_add = true;
			}
			if (column.getIdentifier().toString().equals("SEQ") && seq == false) {
				del_add = true;
			}
			if (column.getIdentifier().toString().equals("ACK") && ack == false) {
				del_add = true;
			}
			if (column.getIdentifier().toString().equals("Length")
					&& length == false) {
				del_add = true;
			}
			if (del_add) {
				auxTable.addColumn(column);
				table.removeColumn(column);
				i--;
			}
		}
		column = auxTable.getColumnModel().getColumn(0); 
		for (i = 0; i < auxTable.getColumnModel().getColumnCount(); i++) {
			del_add = false;
			column = auxTable.getColumnModel().getColumn(i);
			if (column.getIdentifier().toString().equals("Num")
					&& numeropaquete == true) {
				del_add = true;
			}
			if (column.getIdentifier().toString().equals("Time")
					&& time == true) {
				del_add = true;
			}
			if (column.getIdentifier().toString().equals("Size")
					&& size == true) {
				del_add = true;
			}
			if (column.getIdentifier().toString().equals("MAC Src")
					&& macsource == true) {
				del_add = true;
			}
			if (column.getIdentifier().toString().equals("MAC Dest")
					&& macdest == true) {
				del_add = true;
			}
			if (column.getIdentifier().toString().equals("Frame")
					&& frame == true) {
				del_add = true;
			}
			if (column.getIdentifier().toString().equals("Protocol")
					&& protocol == true) {
				del_add = true;
			}
			if (column.getIdentifier().toString().equals("IP Src")
					&& ipsrc == true) {
				del_add = true;
			}
			if (column.getIdentifier().toString().equals("IP Dest")
					&& ipdest == true) {
				del_add = true;
			}
			if (column.getIdentifier().toString().equals("Port Src")
					&& portsrc == true) {
				del_add = true;
			}
			if (column.getIdentifier().toString().equals("Port Dest")
					&& portdest == true) {
				del_add = true;
			}
			if (column.getIdentifier().toString().equals("SEQ") && seq == true) {
				del_add = true;
			}
			if (column.getIdentifier().toString().equals("ACK") && ack == true) {
				del_add = true;
			}
			if (column.getIdentifier().toString().equals("Length")
					&& length == true) {
				del_add = true;
			}
			if (del_add) {
				table.addColumn(column);
				auxTable.removeColumn(column);
				i--;
			}
		}
	}

	public String obtenerOrdenColumnas(){
		String auxS="";
		int i;
		for (i = 0; i < table.getColumnModel().getColumnCount(); i++) {
			column = table.getColumnModel().getColumn(i);
			auxS = auxS + column.getIdentifier().toString() + ",";
		}
		return auxS;
	}
	
	public void DatosPk() {
		modelo.newVector();
	}

	public void DatosRawPaquete(String numeropaquete, String time, String tamano) {
		modelo.addTimeRow(numeropaquete, time, tamano);
	}

	public void DatosPaquete(String macsource, String macdest, String frame,
			String protocol, String ipsrc, String ipdest, String portsrc,
			String portdest, String seq, String ack, String length) {
		//// cogemos los datos que necesitamos para la tabla de cada paquete
		modelo.addRow(macsource, macdest, frame, protocol, ipsrc, ipdest,
				portsrc, portdest, seq, ack, length);
		modelo.addPacket();
	}

	public JTableHeader Cabezera() {
		return table.getTableHeader();
	}

	public JTable Tabla() {
		return table;
	}

	public void getfila(int fila) {

		venpadre.CrearArbol(fila);
		venpadre.show();
	}

	private void initColumnSizes(JTable table) {
		column = table.getColumnModel().getColumn(0);
		column.setPreferredWidth(60);
		column = table.getColumnModel().getColumn(1);
		column.setPreferredWidth(160);
		column = table.getColumnModel().getColumn(2);
		column.setPreferredWidth(40);
		column = table.getColumnModel().getColumn(3);
		column.setPreferredWidth(130);
		column = table.getColumnModel().getColumn(4);
		column.setPreferredWidth(130);
	}

	public void setValues (boolean valor){
		this.numeropaquete = valor;
		this.time = valor;
		this.macsource = valor;
		this.macdest = valor;
		this.frame = valor;
		this.protocol = valor;
		this.ipsrc = valor;
		this.ipdest = valor;
		this.portsrc = valor;
		this.portdest = valor;
		this.seq = valor;
		this.ack = valor;
		this.length = valor;
		this.size = valor;
	}

	public boolean getNumeropaquete(){
		return this.numeropaquete;
	}

	public boolean getTime(){
		return this.time;
	}
	
	public boolean getMacsource(){
		return this.macsource;
	}

	public boolean getMacdest(){
		return this.macdest;
	}
	
	public boolean getFrame(){
		return this.frame;
	}

	public boolean getProtocol(){
		return this.protocol;
	}
	
	public boolean getIpsrc(){
		return this.ipsrc;
	}
	
	public boolean getIpdest(){
		return this.ipdest;
	}
	
	public boolean getPortsrc(){
		return this.portsrc;
	}
	
	public boolean getPortdest(){
		return this.portdest;
	}
	
	public boolean getSeq(){
		return this.seq;
	}
	
	public boolean getAck(){
		return this.ack;
	}	
	
	public boolean getLength(){
		return this.length;
	}	
	public boolean getSize(){
		return this.size;
	}
	
	public void clearTable(){
		modelo.clearAll();
	}
}
// Fin de la clase Table Pane

