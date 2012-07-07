package dominio.pcap.rules;

import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.*;

import java.awt.*;

/**
 * Clase FAlertsLoad.
 * 
 * @author Leonardo García & JoseRamón Gutierrez
 * @version 2.0
 *  
 */
public class FAlertsLoad extends javax.swing.JFrame {

	private MiTablaModeloAlertsLoad modelo;

	private JTable table;

	private TableColumn column = null;

	public FAlertsLoad() {
		super("AlertsLoad");
		this.modelo = new MiTablaModeloAlertsLoad();
		this.table = new JTable(modelo);
		table.setPreferredScrollableViewportSize(new Dimension(250, 50));
		JScrollPane scrollPane = new JScrollPane(table);
		getContentPane().add(scrollPane, java.awt.BorderLayout.CENTER);
		pack();
	}

	public void DatosTablaAlertsLoad(String p_fichero_reglas) {
		modelo.addRow(p_fichero_reglas);

	}

	public JTableHeader Cabezera() {
		return table.getTableHeader();
	}

	public JTable Tabla() {
		return table;
	}
} // Fin de la clase Table Pane

