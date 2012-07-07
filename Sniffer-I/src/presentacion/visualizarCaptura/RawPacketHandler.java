package presentacion.visualizarCaptura;

import net.sourceforge.jpcap.capture.*;
import net.sourceforge.jpcap.net.*;
import java.util.Vector;
/**
 * NO VALIDA : VIEJA
 */
public class RawPacketHandler implements RawPacketListener {
	public int i;

	private static Vector VRawPackets;

	private TablePane RTablePane;

	public RawPacketHandler(TablePane RTablePane) {
		this.RTablePane = RTablePane;
		i = 0;
		VRawPackets = new Vector();
	}

	public void rawPacketArrived(RawPacket rawPacket) {
		RTablePane.DatosPk(); // Prepara la tabla para los datos de un nuevo
		// vector.
		RTablePane.DatosRawPaquete(String.valueOf(i), rawPacket.getTimeval()
				.toString(), String.valueOf(rawPacket.getData().length));
		VRawPackets.addElement(rawPacket);
		i++;
		//System.out.println("Packet " + i + "\n" + rawPacket + "\n");

	}

	public static Vector getVRawPackets() {
		return VRawPackets;
	}

}

