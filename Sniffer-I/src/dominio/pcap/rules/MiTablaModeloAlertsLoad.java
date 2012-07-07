package dominio.pcap.rules;

import javax.swing.table.AbstractTableModel;

import java.util.*;

/**
 * Clase MiTablaModeloAlertsLoad.
 * 
 * @author Leonardo García & JoseRamón Gutierrez
 * @version 2.0
 *  
 */
class MiTablaModeloAlertsLoad extends AbstractTableModel {

	Vector tableColumns = new Vector();

	Vector tableData = new Vector();

	public MiTablaModeloAlertsLoad() {

		tableColumns.addElement(new String("Regla"));

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
	public void addRow(String fichero_reglas) {

		Vector newRow = new Vector();
		newRow.addElement(fichero_reglas);
		tableData.addElement(newRow);

	}

}

