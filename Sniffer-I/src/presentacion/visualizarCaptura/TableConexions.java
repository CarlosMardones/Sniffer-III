package presentacion.visualizarCaptura;

import javax.swing.JTable;
import javax.swing.table.*;

import java.awt.Color;
import java.awt.event.*;
import java.util.*;
/**
 * Tabla de las conexiones establecidas
 * 
 * @author Leonardo García & Jose Ramón Gutierrez
 * @version 2.0
 *  
 * @see MiTablaModeloCx
 * @see JTable
 * @see TableColumn
 * @see TablePane
 * 
 */
public class TableConexions {
	/** * Table modelo. */
	private MiTablaModeloCx modelo;
	/** * Tabla de jaba. */
	private JTable table;
	/** * Vector de conexiones. */
	private Vector VectorConexionesTCP;
	/** * columnas de java. */
	private TableColumn column = null;
	/** * Fila seleccionada. */
	protected int filaseleccionada = -1;
	/** * Panel de captura */
	private TablePane RTablePane;

	/**
	 * Constructor por defecto de la clse.
	 * 
	 * @param RTablePane panel de captura
	 */
	public TableConexions(TablePane RTablePane) {

		this.RTablePane = RTablePane;
		modelo = new MiTablaModeloCx();
		table = new JTable(modelo);
		table.addMouseListener(new java.awt.event.MouseAdapter() {
			public void mouseClicked(java.awt.event.MouseEvent evt) {
				fileMouseClicked(evt);
			}
		});
		table.setOpaque(true);

		table.setBackground(new Color(0, 0, 0));
		table.setForeground(new Color(52, 255, 0));

	}
	/**
	 * Evento producido al Clicar en una fila.
	 * 
	 * @param evt evento
	 */
	private void fileMouseClicked(java.awt.event.MouseEvent evt) {
		if ((evt.getModifiers() & InputEvent.BUTTON1_MASK) == InputEvent.BUTTON1_MASK) {
			getfila(table.getSelectedRow());

		}
		if ((evt.getModifiers() & InputEvent.BUTTON3_MASK) == InputEvent.BUTTON3_MASK) {

			new SelectColor(RTablePane).show();
		}

	}

	/**
	 * Aplica el renderizado a la fila seleccionada
	 * 
	 * @param fila numero
	 */
	public void getfila(int fila) {
		filaseleccionada = fila;
		RTablePane.AplicarRender(fila);
	}
	
	/**
	 * Establece los datos de la tabla conexiones.
	 * 
	 * @param numeropaquete numero
	 * @param time hora 
	 * @param ipsrc IP Origen
	 * @param ipdest IP Destino
	 * @param portsrc puerto origen
	 * @param portdest puerto destino
	 * @param paquetes numero de paquetes
	 */
	public void DatosTablaConexion(String numeropaquete, String time,
			String ipsrc, String ipdest, String portsrc, String portdest,
			String paquetes) {
		modelo.addRow(numeropaquete, time, ipsrc, ipdest, portsrc, portdest,
				paquetes);
	}

	/**
	 * Devuelve las cabeceras de las conexiones
	 * 
	 * @return cabeceras de la tabla
	 */
	public JTableHeader Cabezera() {
		return table.getTableHeader();
	}

	/**
	 * tabla de conexiones
	 * 
	 * @return tabla
	 */
	public JTable Tabla() {
		return table;
	}
	
	/**
	 * Resetea los valores de la tabla de paquetes
	 */
	public void clearTable(){
		modelo.clearAll();
	}
} // Fin de la clase Table Pane

