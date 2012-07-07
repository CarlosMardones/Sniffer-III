package presentacion.visualizarCaptura;

import javax.swing.table.AbstractTableModel;
import java.util.*;
/**
 * Tabla modelo para mostrar las conexiones establecidas
 * 
 * @author Leonardo García & Jose Ramón Gutierrez
 * @version 2.0
 *  
 * @see AbstractTableModel
 */
class MiTablaModeloCx extends AbstractTableModel {
	/** * Nombre de las columnas. */
	Vector tableColumns = new Vector();
	/** * Filas de la tabla. */
	Vector tableData = new Vector();

	/**
	 * Contructor por defecto de la tabla
	 */
	public MiTablaModeloCx() {

		tableColumns.addElement(new String("Num Conexión"));
		tableColumns.addElement(new String("Tiempo Inicio"));
		tableColumns.addElement(new String("IP Origen"));
		tableColumns.addElement(new String("IP Destino"));
		tableColumns.addElement(new String("Puerto Origen"));
		tableColumns.addElement(new String("Puerto Destino"));
		tableColumns.addElement(new String("Número Paquetes"));
	}

	/**
	 * Devuelve el número de elementos del array
	 * de los nombre de columnas
	 */
	public int getColumnCount() {
		return tableColumns.size();
	}

	/**
	 * Devuelve el número de elementos del array de datos
	 */
	public int getRowCount() {
		return tableData.size();
	}

	/**
	 * Devuelve el nombre de la colunma indicada
	 * 
	 * @param col numero
	 * @return nombre
	 */
	public String getColumnName(int col) {
		return (String) tableColumns.elementAt(col);
	}

	/**
	 * Devuelve el valor del un celda indicada
	 * 
	 * @param row numero de fila
	 * @param col numero de columna
	 * @return nombre
	 */
	public Object getValueAt(int row, int col) {
		Vector rowVector = (Vector) tableData.elementAt(row);
		return rowVector.elementAt(col);
	}

	/**
	 * Determina el editor predeterminado para cada una de las celdas
	 */
	public Class getColumnClass(int c) {
		return getValueAt(0, c).getClass();

	}

	/**
	 * Añade a la fila nueva en el vector de datos los componetes de conexión
	 * 
	 * @param numeropaquete número
	 * @param time hora
	 * @param ipsrc IP origen
	 * @param ipdest IP destino
	 * @param portsrc puerto origen
	 * @param portdest puerto destino
	 * @param paquetes número de paquetes
	 */
	public void addRow(String numeropaquete, String time, String ipsrc,
			String ipdest, String portsrc, String portdest, String paquetes) {

		Vector newRow = new Vector();

		newRow.addElement(numeropaquete);
		newRow.addElement(time);
		newRow.addElement(ipsrc);
		newRow.addElement(ipdest);
		newRow.addElement(portsrc);
		newRow.addElement(portdest);
		newRow.addElement(paquetes);

		tableData.addElement(newRow);

	}
	
	/**
	 * Resetea los valores de la tabla de paquetes
	 */
	public void clearAll(){
		tableData.clear();
	}

}

