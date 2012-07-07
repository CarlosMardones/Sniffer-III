package dominio.pcap.rules;

import java.io.*;
import java.util.*;
import org.jdom.*;
import org.jdom.output.*;

import net.sourceforge.jpcap.net.*;

/**
 * Clase XMLlog.
 * 
 * Sinopsis: Clase que crea un fichero en formato XML con la descripcion
 * detallada de los logs generados al realizar un Sniffado de datos con la
 * opcion de IDS activa.+
 * 
 * @author Leonardo García & JoseRamón Gutierrez
 * @version 2.0
 * 
 * @see net.sourceforge.jpcap.net
 * @see net.sourceforge.jpcap.client
 * @see org.jdom
 */

public class XMLlog {

	// root elemento padre de el documento XML.( comtendra todos los paquetes )
	private Element root;

	// packet elemento hijo de root ( contendra todos los datos de cada paquete
	// )
	private Element packet;

	private String nombrefichero;

	private boolean logcreado;

	private int nalert = 0;

	public XMLlog(int ncap) { //estoy pasando como parametros la cola de
		// packets

		Date d = new Date();
		this.nombrefichero = "./log/" + String.valueOf(d.getDate()) + "_"
				+ String.valueOf(d.getMonth()) + "_"
				+ String.valueOf(d.getTime()) + "_log" + String.valueOf(ncap)
				+ ".xml";
		// Creamos un elemento root
		this.root = new Element("Incidencia");

	}

	public void CrearLog() {
		int i;

		//Creamos el documento
		Document doc = new Document(root);

		try {

			XMLOutputter out = new XMLOutputter(Format.getPrettyFormat());
			FileOutputStream file = new FileOutputStream(nombrefichero);
			out.output(doc, file);
			file.flush();
			file.close();
			this.logcreado = true;

		} catch (org.jdom.IllegalAddException e) {
			System.err.println("El fichero log ya existe");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void addAlert(Rule objetoRule, int numeropaquete, Packet paquete,
			String IpOrigen, String IpDestino, String portsrc, String portdest) {
		nalert++;
		AlertType newalert = new AlertType(objetoRule, paquete, numeropaquete,
				IpOrigen, IpDestino, portsrc, portdest);
		newalert.setAttribute("numero", (String.valueOf(nalert)));
		root.addContent(newalert);
	}

	public boolean logCreado() {
		return logcreado;
	}

} // fin dela clase

