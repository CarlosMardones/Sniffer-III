package dominio.pcap;

/**
 * Clase Filtro
 * 
 * Sinopsis: Genera el filtro correcto para luego pasarselo al pcap.
 * 
 * @author Leonardo García & JoseRamón Gutierrez
 * @version 2.0
 *  
 */

public class Filtro {
	/**
	 * Establece el host
	 */
	private String Host;

	/**
	 * Establece el protocolo
	 */
	private String Proto;

	/**
	 * Establece el puerto
	 */
	private String Puerto;

	/**
	 * 
	 * Cadena de filtro completa
	 */
	private String FILTER;

	/**
	 * Constructor de la clase Captura
	 */
	public Filtro() {
		Host = null;
		Proto = null;
		Puerto = null;
		FILTER = "";
	}

	/**
	 * Crea la cadena de filtro segun los parametros pasados host, protocolo,
	 * puerto
	 * 
	 * @param host
	 *            host
	 * @param proto
	 *            protocolo
	 * @param puerto
	 *            puerto
	 */
	public void setFiltro(String host, String proto, String puerto) {

		Host = host;
		Proto = proto;
		Puerto = puerto;
		if (Host == null)
			Host = "";
		if (Proto == null)
			Proto = "";
		if (Puerto == null)
			Puerto = "";
		if ((Host.trim().equals("")) & (Proto.trim().equals(""))
				&& (Puerto.trim().equals(""))) {
			FILTER = "";
		} else {
			if ((Host.trim().equals("")) && (Proto.trim().equals(""))
					&& !(Puerto.trim().equals(""))) {
				FILTER = "port " + Puerto;
			}
			if ((Host.trim().equals("")) && !(Proto.trim().equals(""))
					&& !(Puerto.trim().equals(""))) {
				FILTER = "proto " + Proto + " and port " + Puerto;
			}
			if ((Host.trim().equals("")) && !(Proto.trim().equals(""))
					&& (Puerto.trim().equals(""))) {
				FILTER = "proto " + Proto;
			}
			if (!(Host.trim().equals("")) && (Proto.trim().equals(""))
					&& (Puerto.trim().equals(""))) {
				FILTER = "host " + Host;
			}
			if (!(Host.trim().equals("")) && !(Proto.trim().equals(""))
					&& (Puerto.trim().equals(""))) {
				FILTER = "host " + Host + " and proto " + Proto;
			}
			if (!(Host.trim().equals("")) && (Proto.trim().equals(""))
					&& !(Puerto.trim().equals(""))) {
				FILTER = "host " + Host + " and port " + Puerto;
			}
			if (!(Host.trim().equals("")) && !(Proto.trim().equals(""))
					&& !(Puerto.trim().equals(""))) {
				FILTER = "host " + Host + " and proto " + Proto + " and port "
						+ Puerto;
			}
		}
	}

	/**
	 * Establece la cadena de filtro
	 * 
	 * @param filtro_AV
	 *            cadena de filtro generada
	 */
	public void setFiltro_AV(String filtro_AV) {
		FILTER = filtro_AV;
	}

	/**
	 * Retorno la cadena de filtro
	 * 
	 * @return filtro
	 */
	public String getfilter() {
		return FILTER;
	}

}