package dominio.pcap.rules;

import java.util.Vector;

/**
 * Clase Rule.
 * 
 * Sinopsis: Clase Rule que contiene cada uno de los datos de la regla.
 * 
 * @author Leonardo García & JoseRamón Gutierrez
 * @version 2.0
 *  
 */
public class Rule implements Comparable {

	// fuchero del cula se carga la regla
	private String rulefile;

	// Acción a llevar acabo (alert/log/...
	private String Accion;

	// Dirección Ip Origen
	private String IpSrc;

	// Dirección Ip Destino
	private String IpDst;

	// Protocolo
	private String Protocolo;

	// Puerto Origen
	private String PortSrc;

	// Puerto Destino
	private String PortDest;

	// Direccion de la Operación
	private String Direccion;

	// Tipo de Mensaje a mostrar
	private String msg;

	// Flags
	private String flags;

	// TTL ()
	private String ttl;

	// 
	private String id;

	private String itype;

	private String dsize;

	private String icode;

	private String session;

	// Prioridad de la regla
	private String priority;

	// depth => nos dice desde donde tenemos que coger el campo de datos
	// por defecto simpre cogeremos los datos del datagrama ip
	private String depth;

	private String sid;

	private String rev;

	private String classtype;

	private String reference;

	private String within;

	private String seq;

	private String ack;

	private String offset;

	// Vector que contendrá los datos.
	public Vector VectorContent;

	/**
	 * Rule : Constructor de la clase rule Crea un nuevo objeto VectorContent el
	 * cual contrendra todos los data de la regla. (Cuando se realiza la captura
	 * de tramas, se compararan los datos del datagrama con los estos datos de
	 * cada una de las reglas).
	 *  
	 */
	public Rule() {
		this.VectorContent = new Vector();
	}

	/**
	 * compareTo
	 * 
	 * Funcion que compara la prioridad de dos objetos tipo regla.
	 *  
	 */
	public int compareTo(Object o) {
		Rule obj = (Rule) o;
		return priority.compareTo(obj.getPriority());
	}

	/**
	 * setAccion
	 * 
	 * Metodo setAction establece la acción de la regla
	 * 
	 * @param Accion
	 */
	public void setAccion(String Accion) {
		this.Accion = Accion;
	}

	/**
	 * IpSrc
	 * 
	 * Metodo setIPSrc establece la dirección Ip origen de la regla
	 * 
	 * @param IpSrc
	 */
	public void setIpSrc(String IpSrc) {
		this.IpSrc = IpSrc;
	}

	public void setIpDst(String IpDst) {
		this.IpDst = IpDst;
	}

	public void setProtocolo(String Protocolo) {
		this.Protocolo = Protocolo;
	}

	public void setPortSrc(String PortSrc) {
		this.PortSrc = PortSrc;
	}

	public void setPortDest(String PortDest) {
		this.PortDest = PortDest;
	}

	public void setDireccion(String Direccion) {
		this.Direccion = Direccion;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public void setFlags(String flags) {
		this.flags = flags;
	}

	public void setTtl(String ttl) {
		this.ttl = ttl;
	}

	public void setid(String id) {
		this.id = id;
	}

	/**
	 * setContent
	 * 
	 * Metodo setContent añade contenido a el vector de contenidos de la regla.
	 * 
	 * Una regla puede constar de uno, o varios contenidos
	 * 
	 * @param content
	 */
	public void setContent(String content) {
		VectorContent.add(content);
	}

	public void setitype(String itype) {
		this.itype = itype;
	}

	public void setack(String ack) {
		this.ack = ack;
	}

	public void setseq(String seq) {
		this.seq = seq;
	}

	public void setoffset(String offset) {
		this.offset = offset;
	}

	public void setdsize(String dsize) {
		this.dsize = dsize;
	}

	public void seticode(String icode) {
		this.icode = icode;
	}

	public void setsid(String sid) {
		this.sid = sid;
	}

	public void setdepth(String depth) {
		this.depth = depth;
	}

	public void setrev(String rev) {
		this.rev = rev;
	}

	public void setclasstype(String classtype) {
		this.classtype = classtype;
	}

	public void setSession(String session) {
		this.session = session;
	}

	public void setpriority(String priority) {
		this.priority = priority;
	}

	public void setreference(String reference) {
		this.reference = reference;
	}

	public void setwithin(String within) {
		this.within = within;
	}

	public String getAccion() {
		return Accion;
	}

	public String getIpSrc() {
		return IpSrc;
	}

	public String getIpDst() {
		return IpDst;
	}

	public String getProtocolo() {
		return Protocolo;
	}

	public String getPortSrc() {
		return PortSrc;
	}

	public String getPortDest() {
		return PortDest;
	}

	public String getDireccion() {
		return Direccion;
	}

	public String getMsg() {
		return msg;
	}

	public String getFlags() {
		return flags;
	}

	public String getTtl() {
		return ttl;
	}

	public String getid() {
		return id;
	}

	public Vector getVectorContent() {
		return VectorContent;
	}

	public String getitype() {
		return itype;
	}

	public String getdsize() {
		return dsize;
	}

	public String geticode() {
		return icode;
	}

	public String getack() {
		return ack;
	}

	public String getseq() {
		return seq;
	}

	public String getoffset() {
		return offset;
	}

	public String getdepth() {
		return depth;
	}

	public String getsid() {
		return sid;
	}

	public String getrev() {
		return rev;
	}

	public String getclasstype() {
		return classtype;
	}

	public String getSession() {
		return session;
	}

	public String getPriority() {
		return priority;
	}

	public String getreference() {
		return reference;
	}

	public String getwithin() {
		return within;
	}

}