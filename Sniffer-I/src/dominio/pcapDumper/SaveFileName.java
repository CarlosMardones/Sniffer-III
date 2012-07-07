package dominio.pcapDumper;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Clase SaveFileName
 * 
 * Para gestionar la ruta y el nombre del fichero de captura Genera el nombre
 * por defecto, genera nombre para multiples ficheros, el siguiente numero,
 * control la pila
 * 
 * @author Leonardo García & JoseRamón Gutierrez
 * @version 2.0
 *  
 */
public class SaveFileName {
	/** * ruta del fichero. */
	private String path;
	/** * nombre del fichero. */
	private String file;
	/** * separador de ficheros. */
	private String separator;
	/** * nombre del fichero sin extension. */
	private String nameFile;
	/** * extensio del fichero. */
	private String nameExtension;
	/** * fecha de la captura. */
	private String dateTime;
	/** * numero del siguiente fichero. */
	private int next;
	/** * nuemro de ficheros de pila. */
	private int pila;
	/** * contador de ficheros. */
	private int contador;
	/** * maximo numero de ficheros. */
	private int maximo;
	/** contador total de ficherso. */
	private int contador_total;
	/** * ultimo fichero disponible. */
	private int last;
	/** * primer fichero disponible. */
	private int first;
	/** * determina si paso por pila. */
	private boolean paso; 
	/** * Permite grabar o leer el fichero state.*/
	private StateCaptura stateCaptura;

	/**
	 * Constructor de la clase
	 * 
	 * @param path
	 * 			ruta del fichero
	 * @param file
	 * 			nombre del fichero
	 */
	public SaveFileName(String path, String file) {
		setPath(path);
		setFile(file);
		separator();
		getPartFile();
		setTime();
		this.setSiguiente(0);
		this.setPila(0);
		this.setContador(0);
		this.setMaximo(0);
		this.setContadorTotal(0);
		this.setUltimo(1);
		this.setPrimero(1);
		this.stateCaptura = new StateCaptura();
	}

	/**
	 * Constructor de la clase.
	 * 
	 * @param fullPath
	 *            ruta y fichero donde se guardara la captura
	 */
	public SaveFileName(String fullPath) {
		getPartFullPath(fullPath);
		separator();
		getPartFile();
		setTime();
		this.setSiguiente(0);
		this.setPila(0);
		this.setContador(0);
		this.setMaximo(0);
		this.setContadorTotal(0);
		this.setUltimo(1);
		this.setPrimero(1);
		this.stateCaptura = new StateCaptura();
	}

	/**
	 * Constructor por defector de la clase.

	 */
	public SaveFileName() {
		separator();
		setTime();
		this.setSiguiente(0);
		this.setPila(0);
		this.setContador(0);
		this.setMaximo(0);
		this.setContadorTotal(0);
		this.setUltimo(1);
		this.setPrimero(1);
		this.stateCaptura = new StateCaptura();
	}
	/**
	 * Establece el Path, se utiliza cuando no se pasa ningun parametro en el contrusctor
	 * 
	 * @param fullPath
	 *            ruta y fichero donde se guardara la captura
	 */
	public void setSaveFileName(String fullPath) {
		getPartFullPath(fullPath);
		getPartFile();
		setTime();
	}
	
	/**
	 * Establece el Path y nombre file,
	 *  se utiliza cuando no se pasa ningun parametro en el contrusctor
	 * 
	 * @param path
	 *            ruta donde se guardara la captura
	 * @param file
	 *            fichero donde se guardara la captura
	 */
	public void setSaveFileName(String path, String file) {
		setPath(path);
		setFile(file);
		getPartFile();
		setTime();
	}

	/**
	 * Contrustor raro para Modo offline. Llena los campos para saber que leer
	 * en modo multifile o no
	 * 
	 * @param iPila
	 *            pila de ficheros
	 * @param iStart
	 *            pila de ficheros
	 * @param iEnd
	 *            pila de ficheros
	 */
	public int SFNOffline(int iPila, int iStart, int iEnd) {
		int errorTipo=0;
		this.next = iStart;
		this.setPila(iPila);
		if (this.getPila() == 0){
			if (iStart < iEnd) this.setMaximo(iEnd - iStart);
			else errorTipo=1;
		}
		else{ 
			if (iStart < iEnd) this.setMaximo(iEnd - iStart);
			else this.setMaximo(iPila);
		}
		return errorTipo;
	}
	
	/**
	 * Devuelve el nombre concatenado con el tiempo y el siguiente numero de
	 * fichero y extension .pcap
	 * 
	 * @return nombre fichero formato multifile
	 */
	public String getNameTime() {
		String aux;
		aux = getNameFile() + "_" + getDateTime() + "_" + getNext()
				+ getNameExtension();
		//aux = getNameFile() + "_" + i + getNameExtension();

		return aux;
	}
	
	/**
	 * Devuelve el nombre concatenado con el tiempo y el siguiente numero de
	 * fichero y extension .pcap
	 * 
	 * @return nombre fichero formato multifile
	 */
	public String getNameTimeTmp() {
		String aux;
		aux = getNameFile() + "_" + getDateTime() + "_" + getNext()
				+ ".tmp";
		//				+ getNameExtension();
		//aux = getNameFile() + "_" + i + getNameExtension();

		return aux;
	}

	/**
	 * Devuelve el nombre concatenado con el tiempo y extension .pcap
	 * 
	 * @return nombre fichero formato multifile
	 */
	public String getNT() {
		String aux;
		aux = getNameFile() + "_" + getDateTime() + getNameExtension();
		//aux = getNameFile() + "_" + i + getNameExtension();

		return aux;
	}

	/**
	 * Devuelve el nombre concatenado con el tiempo y pero sin extension .pcap
	 * 
	 * @return nombre fichero formato multifile
	 */
	public String getNTwithout() {
		String aux;
		aux = getNameFile() + "_" + getDateTime();
		return aux;
	}
	
	/**
	 * Devuelve el path completo, el nombre concatenado con el tiempo y el
	 * siguiente numero de fichero y extension .pcap
	 * 
	 * @return nombre fichero formato multifile
	 */
	public String getFullNameTime() {
		String aux;
		aux = getPath() + getSeparator() + getNameTime();
		return aux;
	}

	/**
	 * Devuelve el path completo, el nombre concatenado con el tiempo y
	 * extension .pcap
	 * 
	 * @return nombre fichero formato multifile
	 */
	public String getFNT() {
		String aux;
		aux = getPath() + getSeparator() + getNT();
		return aux;
	}

	/**
	 * Devuelve el path completo, el nombre y extension .pcap
	 * 
	 * @return nombre fichero formato multifile
	 */
	public String getFullPath() {
		String aux;
		aux = getPath() + getSeparator() + getFile();
		return aux;
	}

	/**
	 * Determina cual es el separado de ficheros del sistema operatio Windows
	 * "\" Unix "/"
	 */
	private void separator() {
		String aux;
		if (System.getProperty("os.name").compareTo("Linux") == 0) {
			aux = "/";
		} else {
			aux = "\\";
		}
		this.separator = aux;
	}

	/**
	 * Establece cual es la hora de la captura en el siguiente formato
	 * "yyMMdd_HHmmss"
	 */
	public void setTime() {
		Date hoy;
		SimpleDateFormat sdf;
		hoy = new Date();
		sdf = new SimpleDateFormat("yyMMdd_HHmmss");
		String horaActual = new String(sdf.format(hoy));

		this.dateTime = horaActual;
	}

	/**
	 * Determina cual es el nombre y extension de un fichero y lo guarde en la
	 * clase
	 */
	public void getPartFile() {
		String ext;
		String name;
		String auxFile = this.file;
		//System.out.println("getPartFile(auxFile) => " + auxFile);
		//		 where the last dot is. There may be more than one.
		int dotPlace = auxFile.lastIndexOf(".");

		if (dotPlace >= 0) {
			// possibly empty
			name = auxFile.substring(0, dotPlace);

			// possibly empty
			ext = auxFile.substring(dotPlace);
		} else {
			// was no extension
			name = auxFile;
			ext = ".pcap";
		}
		this.nameFile = name;
		this.nameExtension = ext;
	}

	/**
	 * Determina cual es el path del fichero y lo guarda en la clase Y sino coge
	 * la de por defecto
	 * 
	 * @param fullPath
	 *            y fichero
	 */
	public void getPartFullPath(String fullPath) {
		String fil;
		String path;
		String auxFile = fullPath;
		separator();
		//System.out.println("getPartFile(auxFile) => " + auxFile);
		//		 where the last dot is. There may be more than one.
		auxFile=auxFile.replace("/",getSeparator());
		auxFile=auxFile.replace("\\",getSeparator());
		int dotPlace = auxFile.lastIndexOf(getSeparator());

		if (dotPlace >= 0) {
			// possibly empty
			path = auxFile.substring(0, dotPlace);

			// possibly empty
			fil = auxFile.substring(dotPlace + getSeparator().length());
		} else {
			// was no extension
			path = "./files/capturas";
			fil = auxFile;
		}
		setPath(path);
		setFile(fil);
	}

	/**
	 * Establece la ruta donde se guarda el .pcap
	 * 
	 * @param str
	 *            path
	 */
	public void setPath(String str) {
		this.path = str;
	}

	/**
	 * Establece la ruta donde se guarda el .pcap
	 * 
	 * @return path
	 */
	public String getPath() {
		return this.path;
	}

	/**
	 * Establece el nombre como se guarda el .pcap
	 * 
	 * @param str
	 *            name file
	 */
	public void setFile(String str) {
		this.file = str;
	}

	/**
	 * Devuelve el nombre como se guarda el .pcap
	 * 
	 * @return name file
	 */
	public String getFile() {
		return this.getNameFile() + getNameExtension();
	}

	/**
	 * Devuelve el nombre del fichero sin extension
	 * 
	 * @return name file sin extension
	 */
	public String getNameFile() {
		return this.nameFile;
	}

	/**
	 * Devuelve la extension del fichero
	 * 
	 * @return extension
	 */
	public String getNameExtension() {
		return this.nameExtension;
	}

	/**
	 * Devuelve el tiempo en la que se hizo la primera captura
	 * 
	 * @return teimpo de la primera captura
	 */
	public String getDateTime() {
		return this.dateTime;
	}

	/**
	 * Devuelve el separado de carpetas
	 * 
	 * @return name separador
	 */
	public String getSeparator() {
		return this.separator;
	}

	/**
	 * Solo quiero una barra es para buscarla en windows
	 */
	public String getBarra() {
		if (separator.length() < 1)
			return separator.substring(0);
		else
			return this.separator;
	}

	/**
	 * Establece la contador de ficheros
	 * 
	 * @param nt
	 *            contador
	 */
	public void setContador(int nt) {
		this.contador = nt;
	}

	/**
	 * Devuelve el contador de ficheros
	 * 
	 * @return contador
	 */
	public int getContador() {
		return this.contador;
	}

	/**
	 * Establece el contador total de ficheros
	 * 
	 * @param nt
	 *            contador total
	 */
	public void setContadorTotal(int nt) {
		this.contador_total = nt;
	}

	/**
	 * Devuelve el contador total de ficheros
	 * 
	 * @return contador total
	 */
	public int getContadorTotal() {
		return this.contador_total;
	}

	/**
	 * Establece la pila de multifiles
	 * 
	 * @param nt
	 *            pila
	 */
	public void setPila(int nt) {
		this.pila = nt;
	}

	/**
	 * Devuelve la pila de multifiles
	 * 
	 * @return pila
	 */
	public int getPila() {
		return this.pila;
	}

	/**
	 * Establece la maximo numeros de ficheros multifile
	 * 
	 * @param nt
	 *            maximo numero de ficheros
	 */
	public void setMaximo(int nt) {
		this.maximo = nt;
	}

	/**
	 * Devuelve la maximo numeros de ficheros multifile
	 * 
	 * @return maximo numero de ficheros
	 */
	public int getMaximo() {
		return this.maximo;
	}

	/**
	 * Establece el siguiente numero de fichero multifile
	 * 
	 * @param nt
	 *            siguiete numero multifile
	 */
	private void setSiguiente(int nt) {
		this.next = nt;
		contador++;
	}

	/**
	 * Devuelve el siguiente numero de fichero multifile
	 * 
	 * @return siguiete numero multifile
	 */
	public int getSiguiente() {
		return this.next;
	}

	/**
	 * Establece el siguiente numero de fichero multifile
	 * 
	 * @param nt
	 *            siguiete numero multifile
	 */
	private void setUltimo(int nt) {
		this.last = nt;
	}

	/**
	 * Devuelve el siguiente numero de fichero multifile
	 * 
	 * @return siguiete numero multifile
	 */
	public int getPrimero() {
		return this.first;
	}
	
	/**
	 * Establece el siguiente numero de fichero multifile
	 * 
	 * @param nt
	 *            siguiete numero multifile
	 */
	private void setPrimero(int nt) {
		this.first = nt;
	}

	/**
	 * Devuelve el siguiente numero de fichero multifile
	 * 
	 * @return siguiete numero multifile
	 */
	public int getUltimo() {
		return this.last;
	}
	
	/**
	 * Control del siguiente numero multifile teniendo encuneta la pila
	 */
	public void setNext() {
		int aux;
		//System.out.println("pila:" + getPila());
		aux = getSiguiente() + 1;

		setContadorTotal(getContadorTotal() + 1);
		//System.out.println("aux: " + aux);
		if ((maximo != 0) && (maximo < getContadorTotal())) {
			if (getPrimero() > getPila()) setPrimero(1);
			aux = -1;
		}
		else{
			setUltimo(aux);
			if (getPila() != 0) {
				//System.out.println("entra en comprobacion pila");
				if (aux > getPila()) {
					paso = true;
					aux = 1;
					setUltimo(aux);
					if (getPila() == 1 ){
						setPrimero(1);
					}
					else{
						setPrimero(aux + 1);
					}
				}else{
					if (paso){
						setUltimo(aux);
						if(aux > getPila()){
							setPrimero(1);
						}
						else{
							setPrimero(aux+1);
						}
					}
				}
			}
			
		}
		setSiguiente(aux);
	}

	/**
	 * Devuelve el siguiente numero de fichero multifile
	 * 
	 * @return siguiete numero multifile
	 */
	public int getNext() {
		return this.next;
	}
	/**
	 * Devuelve el path y nombre en modo offline
	 * 
	 * @return nombre fichero modo offline
	 */
	public String getFullPathOffline() {
		String aux;
		if (this.getPila()== 0){
			if (this.getNext()!= 0)	aux = getPath() + getSeparator() + getNameFile() + "_" + getNext() + getNameExtension();
			else aux = getPath() + getSeparator() + getFile();
		}
		else{
			if (this.getNext()!= 0) aux = getPath() + getSeparator() + getNameFile() + "_" + getNext() + getNameExtension();
			else aux = getPath() + getSeparator() + getFile();
		}
		return aux;
	}
	
	/**
	 * Graba en el fichero_state.xml el estado de la captura. 
	 * Sin multifichero.
	 * 
	 * @param auxState
	 * 			estado de la captura
	 * 
	 * @see dominio.pcapDumper.StateCaptura
	 */
	public void saveState(boolean auxState){
		//stateCaptura.setCapturando(auxState);
		stateCaptura.setFile(getPath() + getSeparator() + getNameFile());
		stateCaptura.setLocate(getPath());
		stateCaptura.setName(getNameFile());
		stateCaptura.setExtension(getNameExtension());
		stateCaptura.setMultipleFile("No");
		stateCaptura.setRingBuffer(String.valueOf(getPila()));
		stateCaptura.setStopAfter(String.valueOf(getMaximo()));
		stateCaptura.setLastCaptureFile(getNameFile() + getNameExtension());
		stateCaptura.setLastNumFileCapturado(String.valueOf(getNext()));
		stateCaptura.grabarPorperties(auxState);

	}
	
	/**
	 * Graba en el fichero_state.xml el estado de la captura. 
	 * Sin multifichero.
	 * 
	 * @param auxState
	 * 			estado de la captura
	 * 
	 * @see dominio.pcapDumper.StateCaptura
	 */
	public void saveStateMulti(boolean auxState){
		//stateCaptura.setCapturando(auxState);
		stateCaptura.setFile(getPath() + getSeparator() + getNameFile() + "_" + getDateTime());
		stateCaptura.setLocate(getPath());
		stateCaptura.setName(getNameFile() + "_" + getDateTime());
		stateCaptura.setExtension(getNameExtension());
		stateCaptura.setMultipleFile("Yes");
		stateCaptura.setRingBuffer(String.valueOf(getPila()));
		stateCaptura.setStopAfter(String.valueOf(getMaximo()));
		stateCaptura.setLastCaptureFile(getNameTime());
		stateCaptura.setLastNumFileCapturado(String.valueOf(getNext()));
		stateCaptura.grabarPorperties(auxState);
	}
}