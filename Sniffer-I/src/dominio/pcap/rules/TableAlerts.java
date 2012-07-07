package dominio.pcap.rules;

import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.*;

import java.awt.*;

import net.sourceforge.jpcap.net.Packet;

/**
 * Clase TableAlerts.
 * 
 * @author Leonardo García & JoseRamón Gutierrez
 * @version 2.0
 *  
 */
public class TableAlerts extends javax.swing.JFrame {

	private MiTablaModeloAlerts modelo;

	private JTable table;

	private TableColumn column = null;

	public TableAlerts() {
		super("TypeAlert");
		this.modelo = new MiTablaModeloAlerts();
		this.table = new JTable(modelo);
		table.setPreferredScrollableViewportSize(new Dimension(950, 50));
		JScrollPane scrollPane = new JScrollPane(table);
		getContentPane().add(scrollPane, java.awt.BorderLayout.CENTER);
		pack();
	}

	public void DatosTablaAlerts(Rule objetoRule, int numeropaquete,
			Packet paquete, String IpOrigen, String IpDestino, String portsrc,
			String portdest) {
		modelo.addRow(objetoRule, paquete, numeropaquete, IpOrigen, IpDestino,
				portsrc, portdest);

	}

	public JTableHeader Cabezera() {
		return table.getTableHeader();
	}

	public JTable Tabla() {
		return table;
	}
} // Fin de la clase Table Pane

