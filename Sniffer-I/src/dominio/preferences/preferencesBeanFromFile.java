package dominio.preferences;

import java.io.Serializable;

/**
 * Clase preferencesBeanFromFile Sinopsis: Java Bean que contiene la informaciòn
 * necesaria para hacer captura desde fichero
 * 
 * @author Leonardo García
 * @author JoseRamon Gutierrez
 * @version 2.0
 */
public class preferencesBeanFromFile implements Serializable {
	/** * Oreigen de la captura. */
	private String ffCapSource;
	/** * Activar filtrado. */
	private boolean ffCapFilterId;
	/** * Filtro. */
	private String ffCapFilter;
	/** * Activar filtrado avanzado. */
	private boolean ffCapAdvanceId;
	/** * Filtro avanzado. */
	private String ffCapAdvance;
	/** * Activar filtro normal. */
	private boolean ffCapNormal;
	/** * Filtro por Host. */
	private String ffCapHost;
	/** * Filtro por protocolo. */
	private String ffCapProtocol;
	/** * Filtro por puerto. */
	private String ffCapPort;
	/** * Donde se guarda la captura. */
	private String ffFilLocate;
	/** * Activar multiples ficheros. */
	private boolean ffFilMultipleFileId;
	/** * Activar multiples ficheros por espacio */
	private boolean ffFilSpaceId;
	/** * Media de partición por espacio */
	private String ffFilSpaceType;
	/** * Cantidad por fichero */
	private String ffFilSpace;
	/** * Activar pila de ficheros */
	private boolean ffFilRingBufferId;
	/** * Tipo fichero */
	private String ffFilRingBufferType;
	/** * Cantidad por fichero */
	private String ffFilRingBuffer;
	/** * Activar parar despues de x ficharos */
	private boolean ffFilStopAfterId;
	/** * Tipo fichero */
	private String ffFilStopAfterType;
	/** * Cantidad */
	private String ffFilStopAfter;
	/** * Activar parar después de xxx paquetes. */
	private boolean ffStpAfterPacketsId;
	/** * Cantidad de paquetes. */
	private String ffStpAfterPackets;
	
	/**
	 * Constuctor de la clase.
	 */
	public preferencesBeanFromFile() {
		setDefaultSettings();
	}

	/**
	 * Establece propiedades por defecto.
	 */
	public void setDefaultSettings() {
		setffCapSource("./files/capturas/capture.pcap");
		
		setffCapFilterId(true);
		setffCapAdvanceId(true);
		setffCapAdvance("");
		setffCapNormal(false);
		setffCapHost("");
		setffCapProtocol("");
		setffCapPort("");
		
		setffFilLocate("./files/capturas/capture_from_file.pcap");
		setffFilMultipleFileId(false);
		setffFilSpaceId(false);
		setffFilSpaceType("megabyte(s)");
		setffFilSpace("1");
		setffFilRingBufferId(false);
		setffFilRingBufferType("segundo(s)");
		setffFilRingBuffer("10");
		setffFilStopAfterId(false);
		setffFilStopAfterType("Files");
		setffFilStopAfter("3");
		
		setffStpAfterPacketsId(false);
		setffStpAfterPackets("0");
	}
	
	/**
	 * Devuelve desde donde se realiza la lectura desde captura.
	 *  
	 * @return ruta
	 */
	public String getffCapSource() {
		return ffCapSource;
	}

	/**
	 * Establece desde donde se realiza la lectura desde captura.
	 * 
	 * @param aux ruta
	 */
	public void setffCapSource(String aux) {
		this.ffCapSource = aux;
	}

	/**
	 * Devuelve si el filtro ha sido establecido.
	 * 
	 * @return estado
	 */
	public boolean getffCapFilterId() {
		return ffCapFilterId;
	}

	/**
	 * Establece si el filtro ha sido establecido
	 * @param aux
	 */
	public void setffCapFilterId(boolean aux) {
		this.ffCapFilterId = aux;
	}

	/**
	 * Devuelve si hay un filtro avanzado activado.
	 * 
	 * @return activado
	 */
	public boolean getffCapAdvanceId() {
		return ffCapAdvanceId;
	}

	/**
	 * Establece si hay filtro avanzado activado.
	 * @param aux activado
	 */
	public void setffCapAdvanceId(boolean aux) {
		this.ffCapAdvanceId = aux;
	}
	
	/**
	 * Devuelve el filtro avanzado.
	 * 
	 * @return filtro
	 */
	public String getffCapAdvance() {
		return ffCapAdvance;
	}

	/**
	 * Establece el filtro avanzado.
	 * 
	 * @param aux filtro
	 */
	public void setffCapAdvance(String aux) {
		this.ffCapAdvance = aux;
	}

	/**
	 * Devuelve si el filtro es norma.
	 * 
	 * @return filtro
	 */
	public boolean getffCapNormal() {
		return ffCapNormal;
	}

	/**
	 * Establece si el filtro es normal.
	 * 
	 * @param aux estado
	 */
	public void setffCapNormal(boolean aux) {
		this.ffCapNormal = aux;
	}

	/**
	 * Devuelve el Host del filtro.
	 * 
	 * @return host
	 */
	public String getffCapHost() {
		return ffCapHost;
	}

	/**
	 * Establece el Host del filtro.
	 * 
	 * @param aux host
	 */
	public void setffCapHost(String aux) {
		this.ffCapHost = aux;
	}
	
	/**
	 * Devuelve el protrocolo del filtro.
	 * 
	 * @return protocolo
	 */
	public String getffCapProtocol() {
		return ffCapProtocol;
	}

	/**
	 * Establece el protocolo del filtro.
	 * 
	 * @param aux protocolo
	 */
	public void setffCapProtocol(String aux) {
		this.ffCapProtocol = aux;
	}

	/**
	 * Devuelve el puerto del filtro.
	 * 
	 * @return puerto
	 */
	public String getffCapPort() {
		return ffCapPort;
	}

	/**
	 * Establece el puerto del filtro.
	 * @param aux
	 */
	public void setffCapPort(String aux) {
		this.ffCapPort = aux;
	}

	/**
	 * Devuelve la localizacion del fichero de captura.
	 * 
	 * @return fichero
	 */
	public String getffFilLocate() {
		return ffFilLocate;
	}

	/**
	 * Establece la localización del fichero de captura.
	 * 
	 * @param aux fichero
	 */
	public void setffFilLocate(String aux) {
		this.ffFilLocate = aux;
	}

	/**
	 * Devuelve si la opcion Multi fichero está activada.
	 * 
	 * @return estado
	 */
	public boolean getffFilMultipleFileId() {
		return ffFilMultipleFileId;
	}

	/**
	 * Establece si la opcion Multi fichero está activada.
	 * 
	 * @param aux estado
	 */
	public void setffFilMultipleFileId(boolean aux) {
		this.ffFilMultipleFileId = aux;
	}

	/**
	 * Devuelce si al opción por espacio está activa
	 * 
	 * @return estado
	 */
	public boolean getffFilSpaceId() {
		return ffFilSpaceId;
	}

	/**
	 * Establece si la opción por espacio está activa.
	 * 
	 * @param aux estado
	 */
	public void setffFilSpaceId(boolean aux) {
		this.ffFilSpaceId = aux;
	}

	/**
	 * Devuelce la medida del espacio de cada fichero.
	 * 
	 * @return espacio
	 */
	public String getffFilSpaceType() {
		return ffFilSpaceType;
	}

	/**
	 * Establece la medida del espacio de cada fichero.
	 * 
	 * @param aux espacio
	 */
	public void setffFilSpaceType(String aux) {
		this.ffFilSpaceType = aux;
	}

	/**
	 * Devuelve el espacio de cada fichero.
	 * 
	 * @return espacio
	 */
	public String getffFilSpace() {
		return ffFilSpace;
	}

	/**
	 * Establece el espacio de cada fichero.
	 * 
	 * @param aux espacio
	 */
	public void setffFilSpace(String aux) {
		this.ffFilSpace = aux;
	}

	/**
	 * Devuelve si RingBuffer está activo.
	 * 
	 * @return estado
	 */
	public boolean getffFilRingBufferId() {
		return ffFilRingBufferId;
	}

	/**
	 * Establece si el RingBuffer está activo.
	 * 
	 * @param aux estado.
	 */
	public void setffFilRingBufferId(boolean aux) {
		this.ffFilRingBufferId = aux;
	}

	/**
	 * Devuelve el tipo de RingBuffer.
	 * 
	 * @return tipo
	 */
	public String getffFilRingBufferType() {
		return ffFilRingBufferType;
	}

	/**
	 * Establece el tipo de RingBuffer.
	 * 
	 * @param aux tipo
	 */
	public void setffFilRingBufferType(String aux) {
		this.ffFilRingBufferType = aux;
	}

	/**
	 * Devuelve RingBuffer.
	 * 
	 * @return cantidad
	 */
	public String getffFilRingBuffer() {
		return ffFilRingBuffer;
	}

	/**
	 * Establece RingBuffer.
	 * 
	 * @param aux cantidad
	 */
	public void setffFilRingBuffer(String aux) {
		this.ffFilRingBuffer = aux;
	}

	/**
	 * Devuelve si la para despues de x ficheros esta activa.
	 * 
	 * @return estado
	 */
	public boolean getffFilStopAfterId() {
		return ffFilStopAfterId;
	}

	/**
	 * Establece si la para despues de x ficheros esta activa.
	 * 
	 * @param aux estado
	 */
	public void setffFilStopAfterId(boolean aux) {
		this.ffFilStopAfterId = aux;
	}

	/**
	 * Devuelve el tipo de parar despues de.
	 * 
	 * @return tipo
	 */
	public String getffFilStopAfterType() {
		return ffFilStopAfterType;
	}

	/**
	 * Establece el tipo de parar después de.
	 * 
	 * @param aux tipo
	 */
	public void setffFilStopAfterType(String aux) {
		this.ffFilStopAfterType = aux;
	}

	/**
	 * Devuelve cantidad de files parar despues de.
	 * 
	 * @return cantidad
	 */
	public String getffFilStopAfter() {
		return ffFilStopAfter;
	}

	/**
	 * Establece cantidad de files parar despues de.
	 * 
	 * @param aux cantidad
	 */
	public void setffFilStopAfter(String aux) {
		this.ffFilStopAfter = aux;
	}

	/**
	 * Devuelce si parar despues de x paquetes está activo.
	 * 
	 * @return estado
	 */
	public boolean getffStpAfterPacketsId() {
		return ffStpAfterPacketsId;
	}

	/**
	 * Establece si parar despues de x paquetes está activo.
	 * 
	 * @param aux estado.
	 */
	public void setffStpAfterPacketsId(boolean aux) {
		this.ffStpAfterPacketsId = aux;
	}

	/**
	 * Devuelve el número de paquets de parada.
	 * 
	 * @return número paquetes
	 */
	public String getffStpAfterPackets() {
		return ffStpAfterPackets;
	}

	/**
	 * Establece el número de paquetes de parada.
	 * 
	 * @param aux cantidad
	 */
	public void setffStpAfterPackets(String aux) {
		this.ffStpAfterPackets = aux;
	}

}