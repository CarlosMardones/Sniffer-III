package presentacion.visualizarCaptura;

import javax.swing.table.AbstractTableModel;
import java.util.*;

/**
 * Tabla modelo para mostrar los paquetes capturados
 * 
 * @author Leonardo García & Jose Ramón Gutierrez
 * @version 2.0
 *  
 * @see AbstractTableModel
 */
class MiTablaModelo extends AbstractTableModel {
	/** * Nombre de las columnas. */
	Vector tableColumns = new Vector();
	/** * Filas de la tabla. */
	Vector tableData = new Vector();
	/** * Fila que contiene un papuete. */
	public Vector newRow;

	/**
	 * Contructor por defecto de la tabla
	 */
	public MiTablaModelo() {

		tableColumns.addElement(new String("Num"));
		tableColumns.addElement(new String("Time"));
		tableColumns.addElement(new String("Size"));
		tableColumns.addElement(new String("MAC Src"));
		tableColumns.addElement(new String("MAC Dest"));
		tableColumns.addElement(new String("Frame"));
		tableColumns.addElement(new String("Protocol"));
		tableColumns.addElement(new String("IP Src"));
		tableColumns.addElement(new String("IP Dest"));
		tableColumns.addElement(new String("Port Src"));
		tableColumns.addElement(new String("Port Dest"));
		tableColumns.addElement(new String("SEQ"));
		tableColumns.addElement(new String("ACK"));
		tableColumns.addElement(new String("Length"));

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
	 * Genera una nueva fila en el vector de datos
	 */
	public void newVector() {
		newRow = new Vector();
	}
	
	/**
	 * 
	 * Añade a la fila nueva en el vector de datos los componetes de paquete
	 * 
	 * @param macsource MAC origen
	 * @param macdest MAC destino
	 * @param frame Frame
	 * @param protocol protocolo
	 * @param ipsrc IP origen
	 * @param ipdest IP destino
	 * @param portsrc puerto origen
	 * @param portdest puerto destino
	 * @param seq secuencia
	 * @param ack ACK
	 * @param length longitud de paquete
	 */
	public void addRow(String macsource, String macdest, String frame,
			String protocol, String ipsrc, String ipdest, String portsrc,
			String portdest, String seq, String ack, String length) {

		newRow.addElement(macsource);
		newRow.addElement(macdest);
		newRow.addElement(frame);
		newRow.addElement(protocol);
		newRow.addElement(ipsrc);
		newRow.addElement(ipdest);
		newRow.addElement(portsrc);
		newRow.addElement(portdest);
		newRow.addElement(seq);
		newRow.addElement(ack);
		newRow.addElement(length);

	}
	
	/**
	 * Añade a la fila nueva en el vector de datos los componetes de paquete referidos 
	 * al tiempo de captura
	 * 
	 * @param numeropaquete número
	 * @param time	hora
	 * @param size tamaño total del paquete
	 */
	public void addTimeRow(String numeropaquete, String time, String size) {

		newRow.addElement(numeropaquete);
		newRow.addElement(time);
		newRow.addElement(size);

	}

	/**
	 * Añade al vector de paquetes la nueva fila
	 */
	public void addPacket() {

		tableData.addElement(newRow);

	}

	/**
	 * Resetea los valores de la tabla de paquetes
	 */
	public void clearAll(){
		tableData.clear();
	}
}

