package presentacion.visualizarCaptura;

import java.text.*;
import java.util.*;
import java.util.Vector;
/**
 * Contiene la información de cada conexión establecida en la captura
 * 
 * @author Leonardo García & Jose Ramón Gutierrez
 * @version 2.0
 *  
 */
public class Conexion {
	/** * Hora de establecimiento. */
	private String Establecimiento;
	/** * Ip Origen. */
	private String IpSrc;
	/** * Ip Destino. */
	private String IpDst;
	/** * Puerto Origen. */
	private int PortSrc;
	/** * Puerto Destino. */
	private int PortDest;
	/** * Número de paquetes. */
	private int numeropaquetes;
	/// pensar si almacenar los paquetes de cada conexion , o la posicion de
	// cada paquete
	/// de la conexion en un vector
	/** * Vector de paquetes. */
	private Vector VectorPaquetes;
	/** * Posición del paquete */
	private Integer posicionpk;

	/**
	 * Constructor de la clase.
	 * 
	 * @param IpOrigen Ip origen
	 * @param IpDestino Ip Destino
	 * @param PuertoOrigen  Puerto origen
	 * @param PuertoDestino Puerto Destino
	 */
	public Conexion(String IpOrigen, String IpDestino, int PuertoOrigen,
			int PuertoDestino) {
		VectorPaquetes = new Vector();
		this.IpSrc = IpOrigen;
		this.IpDst = IpDestino;
		this.PortSrc = PuertoOrigen;
		this.PortDest = PuertoDestino;
		this.numeropaquetes = 0;
		Establecimiento = GetTime();
	}

	/**
	 * Devuelve la hora actual
	 * 
	 * @return hora
	 */
	public String GetTime() {
		Date hoy;
		SimpleDateFormat sdf;

		hoy = new Date();
		sdf = new SimpleDateFormat("HH:mm:ss:ms");
		String horaActual = new String(sdf.format(hoy));

		return horaActual;
	}
	/**
	 * Devuelve el vector de paquetes
	 * @return paquetes
	 */
	public Vector getvector() {
		return VectorPaquetes;
	}

	/**
	 * Devuelve la hora de establecimiento de la conexión.
	 * 
	 * @return hora
	 */
	public String getTimeEstablecimiento() {
		return Establecimiento;
	}

	/**
	 * Devuelve IP origen.
	 * 
	 * @return IP
	 */
	public String getIpSrc() {
		return IpSrc;
	}

	/**
	 * Devuelve IP destino.
	 * @return IP
	 */
	public String getIpDest() {
		return IpDst;
	}

	/**
	 * Devuelve el puerto Origen.
	 * 
	 * @return puerto
	 */
	public int getPuertoSrc() {
		return PortSrc;
	}

	/**
	 * Devuelve el puerto destino.
	 * 
	 * @return puerto
	 */
	public int getPuertoDst() {
		return PortDest;
	}
	
	/**
	 * Devuelve el numero de paquetes de conexion
	 * 
	 * @return paquetes
	 */
	public int getnumeropaquetes() {
		return numeropaquetes;
	}

	/**
	 * Añade al vector la posición del paquete en la tabla principal.
	 * 
	 * @param posicionpaquete
	 */
	public void addpaquete(int posicionpaquete) {
		++numeropaquetes;
		posicionpk = new Integer(posicionpaquete);
		VectorPaquetes.add(posicionpk);
	}

}