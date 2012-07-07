package dominio.preferences;

import java.io.Serializable;
/**
 * Java Bean que contiene la información necesaria para hacer una captura
 * 
 * @author Leonardo García & Jose Ramón Gutierrez
 * @version 2.0
 * 
 */
public class preferencesBeanCapture implements Serializable {
	/** * Interface de lectura. */
	private String capDispositive;
	/** * Modo promiscuo. */
	private boolean capPromiscuousMode;
	/** * Activar filtrado. */
	private boolean capFilter;
	/** * Activar filtrado avanzado. */
	private boolean capAdvanceId;
	/** * Filtro avanzado. */
	private String capAdvance;
	/** * Activar filtro normal. */
	private boolean capNormal;
	/** * Filtro por Host. */
	private String capHost;
	/** * Filtro por protocolo. */
	private String capProtocol;
	/** * Filtro por puerto. */
	private String capPort;
	/** * Donde se guarda la captura. */
	private String filLocate;
	/** * Activar multiples ficheros. */
	private boolean filMultipleFileId;
	/** * Activar multiples ficheros por espacio. */
	private boolean filSpaceId;
	/** * Media de partición por espacio. */
	private String filSpaceType;
	/** * Cantidad por fichero. */
	private String filSpace;
	/** * Activar multiples ficheros por tiempo. */
	private boolean filTimeId;
	/** * Media de partición por tiempo. */
	private String filTimeType;
	/** * Cantidad por fichero. */
	private String filtime;
	/** * Activar pila de ficheros. */
	private boolean filRingBufferId;
	/** * Tipo fichero. */
	private String filRingBufferType;
	/** * Cantidad por fichero. */
	private String filRingBuffer;
	/** * Activar parar despues de x ficharos. */
	private boolean filStopAfterId;
	/** * Tipo fichero. */
	private String filStopAfterType;
	/** * Cantidad. */
	private String filStopAfter;
	/** * Activar parar después de xxx paquetes. */
	private boolean stpAfterPacketsId;
	/** * Cantidad de paquetes. */
	private String stpAfterPackets;
	/** * Activar parar despues de xxx bytes. */
	private boolean stpAfterSpaceId;
	/** * Medidad de spacio. */
	private String stpAfterSpaceType;
	/** * Cantidad de bytes. */
	private String stpAfterSpace;
	/** * Activar parar despues de xxx segundos. */
	private boolean stpAfterTimeId;
	/** * Medida del tiempo. */
	private String stpAfterTimeType;
	/** * Cantidad de tiempo. */
	private String stpAfterTime;

	/**
	 * Constuctor de la clase.
	 */
	public preferencesBeanCapture() {
		setDefaultSettings();
	}

	/**
	 * Establece propiedades por defecto.
	 */
	public void setDefaultSettings() {
		setCapDispositive("");
		setCapPromiscuousMode(true);
		setCapFilter(false);
		setCapAdvanceId(false);
		setCapAdvance("");
		setCapNormal(false);
		setCapHost("");
		setCapProtocol("");
		setCapPort("");

		setFilLocate("");
		setFilMultipleFileId(false);
		setFilSpaceId(false);
		setFilSpaceType("");
		setFilSpace("");
		setFilTimeId(false);
		setFilTimeType("");
		setFilTime("");
		setFilRingBufferId(false);
		setFilRingBufferType("");
		setFilRingBuffer("");
		setFilStopAfterId(false);
		setFilStopAfterType("");
		setFilStopAfter("");

		setstpAfterPacketsId(false);
		setstpAfterPackets("");
		setstpAfterSpaceId(false);
		setstpAfterSpaceType("");
		setstpAfterSpace("");
		setstpAfterTimeId(false);
		setstpAfterTimeType("");
		setstpAfterTime("");
	}

	/**
	 * Devuelve el dispositivo de captura.
	 * 
	 * @return dispositivo de captura.
	 */
	public String getCapDispositive() {
		return capDispositive;
	}

	/**
	 * Establece el dispositivo de captura.
	 * 
	 * @param aux
	 *            dispositivo de captura.
	 */
	public void setCapDispositive(String aux) {
		this.capDispositive = aux;
	}

	/**
	 * Devuelve el estado del tipo de captura, promiscuo.
	 * 
	 * @return modo promiscuo.
	 */
	public boolean getCapPromiscuousMode() {
		return capPromiscuousMode;
	}

	/**
	 * Establece el estado del tipo de captura, promiscuo.
	 * 
	 * @param aux
	 *            modo promiscuo.
	 */
	public void setCapPromiscuousMode(boolean aux) {
		this.capPromiscuousMode = aux;
	}

	/**
	 * Devuelve si hay algún filtro activado.
	 * 
	 * @return si el filtro esta activado.
	 */
	public boolean getCapFilter() {
		return capFilter;
	}

	/**
	 * Establece si hay algún filtro activado.
	 * 
	 * @param aux activado
	 */
	public void setCapFilter(boolean aux) {
		this.capFilter = aux;
	}

	/**
	 * Devuelve si hay un filtro avanzado activado.
	 * 
	 * @return activado
	 */
	public boolean getCapAdvanceId() {
		return capAdvanceId;
	}

	/**
	 * Establece si hay filtro avanzado activado.
	 * @param aux activado
	 */
	public void setCapAdvanceId(boolean aux) {
		this.capAdvanceId = aux;
	}

	/**
	 * Devuelve el filtro avanzado.
	 * 
	 * @return filtro
	 */
	public String getCapAdvance() {
		return capAdvance;
	}

	/**
	 * Establece el filtro avanzado.
	 * 
	 * @param aux filtro
	 */
	public void setCapAdvance(String aux) {
		this.capAdvance = aux;
	}

	/**
	 * Devuelve si el filtro es norma.
	 * 
	 * @return filtro
	 */
	public boolean getCapNormal() {
		return capNormal;
	}

	/**
	 * Establece si el filtro es normal.
	 * 
	 * @param aux estado
	 */
	public void setCapNormal(boolean aux) {
		this.capNormal = aux;
	}

	/**
	 * Devuelve el Host del filtro.
	 * 
	 * @return host
	 */
	public String getCapHost() {
		return capHost;
	}

	/**
	 * Establece el Host del filtro.
	 * 
	 * @param aux host
	 */
	public void setCapHost(String aux) {
		this.capHost = aux;
	}
	
	/**
	 * Devuelve el protrocolo del filtro.
	 * 
	 * @return protocolo
	 */
	public String getCapProtocol() {
		return capProtocol;
	}

	/**
	 * Establece el protocolo del filtro.
	 * 
	 * @param aux protocolo
	 */
	public void setCapProtocol(String aux) {
		this.capProtocol = aux;
	}

	/**
	 * Devuelve el puerto del filtro.
	 * 
	 * @return puerto
	 */
	public String getCapPort() {
		return capPort;
	}

	/**
	 * Establece el puerto del filtro.
	 * @param aux
	 */
	public void setCapPort(String aux) {
		this.capPort = aux;
	}

	/**
	 * Devuelve la localizacion del fichero de captura.
	 * 
	 * @return fichero
	 */
	public String getFilLocate() {
		return filLocate;
	}

	/**
	 * Establece la localización del fichero de captura.
	 * 
	 * @param aux fichero
	 */
	public void setFilLocate(String aux) {
		this.filLocate = aux;
	}

	/**
	 * Devuelve si la opcion Multi fichero está activada.
	 * 
	 * @return estado
	 */
	public boolean getFilMultipleFileId() {
		return filMultipleFileId;
	}

	/**
	 * Establece si la opcion Multi fichero está activada.
	 * 
	 * @param aux estado
	 */
	public void setFilMultipleFileId(boolean aux) {
		this.filMultipleFileId = aux;
	}

	/**
	 * Devuelce si al opción por espacio está activa
	 * 
	 * @return estado
	 */
	public boolean getFilSpaceId() {
		return filSpaceId;
	}

	/**
	 * Establece si la opción por espacio está activa.
	 * 
	 * @param aux estado
	 */
	public void setFilSpaceId(boolean aux) {
		this.filSpaceId = aux;
	}

	/**
	 * Devuelce la medida del espacio de cada fichero.
	 * 
	 * @return espacio
	 */
	public String getFilSpaceType() {
		return filSpaceType;
	}

	/**
	 * Establece la medida del espacio de cada fichero.
	 * 
	 * @param aux espacio
	 */
	public void setFilSpaceType(String aux) {
		this.filSpaceType = aux;
	}

	/**
	 * Devuelve el espacio de cada fichero.
	 * 
	 * @return espacio
	 */
	public String getFilSpace() {
		return filSpace;
	}

	/**
	 * Establece el espacio de cada fichero.
	 * 
	 * @param aux espacio
	 */
	public void setFilSpace(String aux) {
		this.filSpace = aux;
	}

	/**
	 * Establece si la opción por tiempo está activa.
	 * 
	 * @return estado
	 */
	public boolean getFilTimeId() {
		return filTimeId;
	}

	/**
	 * Devuelve  si la opción por tiempo está activa.
	 * 
	 * @param aux estado
	 */
	public void setFilTimeId(boolean aux) {
		this.filTimeId = aux;
	}

	/**
	 * Devuelve la medida del tiempo de cada fichero.
	 * 
	 * @return medida
	 */
	public String getFilTimeType() {
		return filTimeType;
	}

	/**
	 * Establece la medida del teimpo de cada fichero.
	 * 
	 * @param aux medida
	 */
	public void setFilTimeType(String aux) {
		this.filTimeType = aux;
	}

	/**
	 * Devuelve el tiempo de cada fichero.
	 * 
	 * @return tiempo
	 */
	public String getFilTime() {
		return filtime;
	}

	/**
	 * Establece el tiempo de cada fichero.
	 * 
	 * @param aux tiempo
	 */
	public void setFilTime(String aux) {
		this.filtime = aux;
	}

	/**
	 * Devuelve si RingBuffer está activo.
	 * 
	 * @return estado
	 */
	public boolean getFilRingBufferId() {
		return filRingBufferId;
	}

	/**
	 * Establece si el RingBuffer está activo.
	 * 
	 * @param aux estado.
	 */
	public void setFilRingBufferId(boolean aux) {
		this.filRingBufferId = aux;
	}

	/**
	 * Devuelve el tipo de RingBuffer.
	 * 
	 * @return tipo
	 */
	public String getFilRingBufferType() {
		return filRingBufferType;
	}

	/**
	 * Establece el tipo de RingBuffer.
	 * 
	 * @param aux tipo
	 */
	public void setFilRingBufferType(String aux) {
		this.filRingBufferType = aux;
	}

	/**
	 * Devuelve RingBuffer.
	 * 
	 * @return cantidad
	 */
	public String getFilRingBuffer() {
		return filRingBuffer;
	}

	/**
	 * Establece RingBuffer.
	 * 
	 * @param aux cantidad
	 */
	public void setFilRingBuffer(String aux) {
		this.filRingBuffer = aux;
	}

	/**
	 * Devuelve si la para despues de x ficheros esta activa.
	 * 
	 * @return estado
	 */
	public boolean getFilStopAfterId() {
		return filStopAfterId;
	}

	/**
	 * Establece si la para despues de x ficheros esta activa.
	 * 
	 * @param aux estado
	 */
	public void setFilStopAfterId(boolean aux) {
		this.filStopAfterId = aux;
	}

	/**
	 * Devuelve el tipo de parar despues de.
	 * 
	 * @return tipo
	 */
	public String getFilStopAfterType() {
		return filStopAfterType;
	}

	/**
	 * Establece el tipo de parar después de.
	 * 
	 * @param aux tipo
	 */
	public void setFilStopAfterType(String aux) {
		this.filStopAfterType = aux;
	}

	/**
	 * Devuelve cantidad de files parar despues de.
	 * 
	 * @return cantidad
	 */
	public String getFilStopAfter() {
		return filStopAfter;
	}

	/**
	 * Establece cantidad de files parar despues de.
	 * 
	 * @param aux cantidad
	 */
	public void setFilStopAfter(String aux) {
		this.filStopAfter = aux;
	}

	/**
	 * Devuelce si parar despues de x paquetes está activo.
	 * 
	 * @return estado
	 */
	public boolean getstpAfterPacketsId() {
		return stpAfterPacketsId;
	}

	/**
	 * Establece si parar despues de x paquetes está activo.
	 * 
	 * @param aux estado.
	 */
	public void setstpAfterPacketsId(boolean aux) {
		this.stpAfterPacketsId = aux;
	}

	/**
	 * Devuelve el número de paquets de parada.
	 * 
	 * @return número paquetes
	 */
	public String getstpAfterPackets() {
		return stpAfterPackets;
	}

	/**
	 * Establece el número de paquetes de parada.
	 * 
	 * @param aux cantidad
	 */
	public void setstpAfterPackets(String aux) {
		this.stpAfterPackets = aux;
	}

	/**
	 * Devuleve si parar la única captura despues de un espacio de fichero.
	 * 
	 * @return estado
	 */
	public boolean getstpAfterSpaceId() {
		return stpAfterSpaceId;
	}

	/**
	 * Establece si parar la única captura despues de un espacio de fichero.
	 * 
	 * @param aux estado
	 */
	public void setstpAfterSpaceId(boolean aux) {
		this.stpAfterSpaceId = aux;
	}

	/**
	 * Devuelve el tipo de medida de espacio.
	 * 
	 * @return medida
	 */
	public String getstpAfterSpaceType() {
		return stpAfterSpaceType;
	}

	/**
	 * Establece el tipo de media de espacio.
	 * 
	 * @param aux medida
	 */
	public void setstpAfterSpaceType(String aux) {
		this.stpAfterSpaceType = aux;
	}

	/**
	 * Devuelve el spacio de la captura única.
	 * 
	 * @return espacio
	 */
	public String getstpAfterSpace() {
		return stpAfterSpace;
	}

	/**
	 * Establece el espacio de la captura única.
	 * 
	 * @param aux espacio
	 */
	public void setstpAfterSpace(String aux) {
		this.stpAfterSpace = aux;
	}

	/**
	 * Devuelve si parar la única captura despues de un tiempo está activo.
	 * 
	 * @return estado
	 */
	public boolean getstpAfterTimeId() {
		return stpAfterTimeId;
	}

	/**
	 * Establece si parar la única captura despues de un tiempo está activo.
	 * 
	 * @param aux estado
	 */
	public void setstpAfterTimeId(boolean aux) {
		this.stpAfterTimeId = aux;
	}

	/**
	 * Devuelve el tipo de medida para la parada despues de un tiempo.
	 * 
	 * @return medida
	 */
	public String getstpAfterTimeType() {
		return stpAfterTimeType;
	}

	/**
	 * Establece el tipo de medida para la parada despues de un tiempo.
	 * 
	 * @param aux medida
	 */
	public void setstpAfterTimeType(String aux) {
		this.stpAfterTimeType = aux;
	}

	/**
	 * Devuelve el tiempo de parada para la captura única.
	 * 
	 * @return tiempo
	 */
	public String getstpAfterTime() {
		return stpAfterTime;
	}

	/**
	 * Establece el tiempo de parada para la captura única.
	 * 
	 * @param aux tiempo.
	 */
	public void setstpAfterTime(String aux) {
		this.stpAfterTime = aux;
	}
}