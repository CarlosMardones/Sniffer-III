package dominio.pcap.rules;

import javax.swing.table.AbstractTableModel;

import net.sourceforge.jpcap.net.Packet;

import java.util.*;

/**
 * Clase MiTablaModeloAlerts.
 * 
 * @author Leonardo García & JoseRamón Gutierrez
 * @version 2.0
 *  
 */
class MiTablaModeloAlerts extends AbstractTableModel {

	Vector tableColumns = new Vector();

	Vector tableData = new Vector();

	public MiTablaModeloAlerts() {

		tableColumns.addElement(new String("Paquete"));
		tableColumns.addElement(new String("Timeval"));
		tableColumns.addElement(new String("Msg"));
		tableColumns.addElement(new String("IP Origen"));
		tableColumns.addElement(new String("IP Destino"));
		tableColumns.addElement(new String("Puerto Origen"));
		tableColumns.addElement(new String("Puerto Destino"));
		tableColumns.addElement(new String("Prioridad"));
		tableColumns.addElement(new String("Packet Dsc"));

	}

	//unicamente retornamos el numero de elementos del array
	// de los nombre de columnas
	public int getColumnCount() {
		return tableColumns.size();
	}

	// retornamos el numero de elementos del array de datos
	public int getRowCount() {
		return tableData.size();
	}

	//retornamos el elemento indicado
	public String getColumnName(int col) {
		return (String) tableColumns.elementAt(col);
	}

	// lo mismo para las celdas
	public Object getValueAt(int row, int col) {
		Vector rowVector = (Vector) tableData.elementAt(row);
		return rowVector.elementAt(col);
	}

	/***************************************************************************
	 * Este metodo sirve para determinar el editor predeterminado para cada una
	 * de las celdas
	 **************************************************************************/
	public Class getColumnClass(int c) {
		return getValueAt(0, c).getClass();

	}

	// add files
	public void addRow(Rule objetoRule, Packet paquete, int numeropaquete,
			String IpOrigen, String IpDestino, String portsrc, String portdest) {

		Vector newRow = new Vector();

		newRow.addElement(String.valueOf(numeropaquete));
		newRow.addElement(String.valueOf(paquete.getTimeval()));
		newRow.addElement(objetoRule.getMsg());
		newRow.addElement(IpOrigen);
		newRow.addElement(IpDestino);
		newRow.addElement(portsrc);
		newRow.addElement(portdest);
		newRow.addElement(objetoRule.getPriority());
		newRow.addElement(paquete.toString());

		tableData.addElement(newRow);

	}

}

