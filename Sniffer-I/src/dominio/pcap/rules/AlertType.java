package dominio.pcap.rules;

import org.jdom.*;

import net.sourceforge.jpcap.net.*;

/**
 * 
 * @author Leonardo García & JoseRamón Gutierrez
 * @version 2.0
 *  
 */
public class AlertType extends Element {

	public AlertType(Rule objetoRule, Packet paquete, int numeropaquete,
			String IpOrigen, String IpDestino, String portsrc, String portdest) {
		super("TypeAlert");
		addContent(new Element("Paquete")
				.setText(String.valueOf(numeropaquete)));
		addContent(new Element("Timeval").setText(String.valueOf(paquete
				.getTimeval())));
		addContent(new Element("Msg").setText(objetoRule.getMsg()));
		addContent(new Element("IPOrigen").setText(IpOrigen));
		addContent(new Element("Destino").setText(IpDestino));
		addContent(new Element("PuertoOrigen").setText(portsrc));
		addContent(new Element("PuertoDestino").setText(portdest));
		addContent(new Element("Prioridad").setText(objetoRule.getPriority()));
		addContent(new Element("Packet").setText(paquete.toString()));
	}

}