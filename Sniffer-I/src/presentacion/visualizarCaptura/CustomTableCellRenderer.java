package presentacion.visualizarCaptura;

import java.awt.Component;
import java.awt.Color;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import java.util.Vector;
/**
 * Se encarga del renderizado del la lista de paquetes de una captura cuando selecciones 
 * una conexión te marca en rojo todos los paquetes de esta conexión
 * 
 * @author Leonardo García & Jose Ramón Gutierrez
 * @version 2.0
 *  
 * @see Conexion
 * @see VisualizarCaptura
 */
public class CustomTableCellRenderer extends DefaultTableCellRenderer {
	/** * Fila seleccionada??. */
	private int conexiontcp;
	/** * Vector con todas las conexxiones establecidas. */
	private Vector VectorConexiones = new Vector();
	/** * variable de tipo Conexion. */
	private Conexion objetoConexion;
	/** * Vector con los paquetes que contiene un conexión. */
	private Vector Vectoraux = new Vector();
	/** Tipo de color. */
	protected Color color;
	/** quien llamo a la calse. tipo VisualizarCaptura. */
	public VisualizarCaptura venpadre;

	/**
	 * Establece con un color la fila selleccionada
	 * 
	 * @param color color de la fila
	 * @param venpadre quien llama a la función
	 * @param fila_seleccionada fila
	 */
	public CustomTableCellRenderer(Color color, VisualizarCaptura venpadre,
			int fila_seleccionada) {

		this.venpadre = venpadre;
		this.color = color;
		// al crear un onjeto de este tipo ya tengo la fila
		int i = 0;
		int j = 0;
		conexiontcp = fila_seleccionada;
		if (conexiontcp != -1) {
			VectorConexiones = venpadre.getConexionesTCP(); // Obtengo el Vector
			// de Conexiones TCP
			Conexion objetoConexion = (Conexion) VectorConexiones
					.elementAt(conexiontcp); //cast //Obtengo el objeto
			// correspondiente
			Vectoraux = objetoConexion.getvector();
			/*******************************************************************
			 * for (j = 0;j < Vectoraux.size();j++){ Integer pospk =
			 * (Integer)objetoConexion.getvector().elementAt(j);
			 * System.out.println("=>"+pospk.intValue()); }
			 ******************************************************************/
		}

	}
	
	/**
	 * .
	 */
	public Component getTableCellRendererComponent(JTable table, Object value,
			boolean isSelected, boolean hasFocus, int row, int column) {
		int t = 0;

		Component cell = super.getTableCellRendererComponent(table, value,
				isSelected, hasFocus, row, column);

		cell.setBackground(Color.white);

		if (conexiontcp == -1) { // no hay ninguna conexion seleccionada.
			cell.setBackground(Color.white);

		} else {

			for (t = 0; t < Vectoraux.size(); t++) {
				Integer pospk = (Integer) Vectoraux.elementAt(t);

				// Ojo posiciones de tabla y vector enpiezan en 0 y 1 diferentes
				//   if (row == (pospk.intValue()-1))
				// cell.setBackground(Color.red);
				if (row == (pospk.intValue() - 1))
					cell.setBackground(color);
			}

		}

		if (isSelected)
			cell.setBackground(Color.LIGHT_GRAY);

		return cell;

	}

	/**
	 * Establece fila de la conexión seleccionada.
	 * 
	 * @param fila número.
	 */
	public void filaconexion(int fila) {
		conexiontcp = fila;
	}

}

