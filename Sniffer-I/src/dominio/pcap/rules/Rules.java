package dominio.pcap.rules;

import java.io.*;

import java.io.File;
import java.io.FileReader;
import java.math.BigInteger;
import java.util.Vector;
import java.util.*;

import net.sourceforge.jpcap.net.Packet;

/**
 * Clase Rules.
 * 
 * Sinopsis: Clase que tratara la carga de reglas de la aplicacion. Tratará la
 * carga de los ficheros de configuracion (classification.config) así como el
 * propio proceso de tratamiento de los ficheros de reglas *.rules
 * 
 * @author Leonardo García & JoseRamón Gutierrez
 * @version 2.0
 *  
 */
public class Rules {

	private String accion;

	private String protocolo;

	private String IpOrigen;

	private String PuertoSrc;

	private String IpDestino;

	private String PuertoDst;

	private String Direccion;

	// Fichero : Nombre del fichero de propiedades a tratar.
	private String fichero;

	int inicio;

	int lines;

	int res;

	int inicio_opcion;

	public Rule NuevaRegla;

	public Vector VectorRules;

	public Vector VectorClasification;

	public TableAlerts TablaAlertas;

	private XMLlog log;

	/**
	 * Abrir el fichero, y cargar todos sus patrones de tramas Cada linea del
	 * fichero tendra un patron cada patron será un Objeto tipo rule que cargará
	 * en el Vector de Reglas VectorRules
	 *  
	 */
	public Rules(TableAlerts TablaAlertas, XMLlog log) {
		try {

			this.VectorRules = new Vector();
			lines = 0;
			int trat_cabezera = 0;
			String char_ini;
			String char_opciones;
			String des_corta;
			String des_larga;
			String prioridad;
			boolean comentario;

			this.TablaAlertas = TablaAlertas;
			this.log = log;
			this.VectorClasification = new Vector();
			BufferedReader lector = new BufferedReader(new FileReader(
					"./rules/classification.config"));
			// el while controla cada linea del fichero
			while (lector.ready()) {
				// Por cada linea del fichero se creara un objeto de tipo Rule
				// que será almacenado en un vector

				String cadena = lector.readLine();
				System.out.println("=>" + cadena);

				if ((cadena.indexOf("#") == 0) || (cadena.length() == 0)) {
					System.out.println("Linea de comentario");
					comentario = true;
				} else {
					comentario = false;
					inicio = cadena.indexOf(":");
					cadena = cadena.substring(inicio + 1);
					cadena = cadena.trim();

					inicio = cadena.indexOf(",");
					des_corta = cadena.substring(0, inicio);
					cadena = cadena.substring(inicio + 1);
					inicio = cadena.indexOf(",");
					des_larga = cadena.substring(0, inicio);
					cadena = cadena.substring(inicio + 1);
					prioridad = cadena;
					System.out.println("\ndes_corta:" + des_corta);
					System.out.println("\ndes_larga:" + des_larga);
					System.out.println("\nprioriadad:" + prioridad);
					ClassificationRules l_classification = new ClassificationRules();
					l_classification.setDescripcion_corta(des_corta);
					l_classification.setDescripcion_larga(des_larga);
					l_classification.setDescripcion_Prioridad(prioridad);
					VectorClasification.add(l_classification);
				}
			}

		} catch (Exception e) {
			e.printStackTrace();

		}

	}

	public void LoadRules(File fichero) {

		String token;
		System.out.println("Principio del tratamiento de fichero Rules");
		System.getProperties().list(System.out);

		String contenido_opcion;
		String msg;
		boolean comentario;

		try {

			this.fichero = fichero.getAbsolutePath();
			if (this.fichero.indexOf(".rules") == -1) {
				throw (new Exception("Fichero no de reglas"));
			}

			lines = 0;
			int trat_cabezera = 0;
			String char_ini;
			String char_opciones;
			BufferedReader lector = new BufferedReader(new FileReader(fichero));
			// el while controla cada linea del fichero
			while (lector.ready()) {
				// Por cada linea del fichero se creara un objeto de tipo Rule
				// que será almacenado en un vector

				String cadena = lector.readLine();
				System.out.println("=>" + cadena);

				if ((cadena.indexOf("#") == 0) || (cadena.length() == 0)) {
					System.out.println("Linea de comentario");
					comentario = true;
				} else {
					comentario = false;
					Rule NuevaRegla = new Rule();
					char_ini = (String) cadena.substring(0, 1);
					//    res = char_ini.compareTo(new String("("));

					while (char_ini.compareTo("(") != 0) {
						inicio = cadena.indexOf(" ", 0);
						System.out.println("Pos" + inicio);
						if (trat_cabezera == 0) {
							accion = cadena.substring(0, inicio);
							System.out.println("Accion :" + accion);
							NuevaRegla.setAccion(accion);
						}
						if (trat_cabezera == 1) {
							protocolo = cadena.substring(0, inicio);
							System.out.println("Protocolo :" + protocolo);
							NuevaRegla.setProtocolo(protocolo);
						}
						if (trat_cabezera == 2) {
							IpOrigen = cadena.substring(0, inicio);
							System.out.println("IPOrigen:" + IpOrigen);
							if (IpOrigen.equals(new String("$EXTERNAL_NET"))) {
								// Coger el valor del fichero de rule.properties
								IpOrigen = System.getProperty("EXTERNAL_NET");
								System.out.println("IpOrigen:" + IpOrigen);
								NuevaRegla.setIpSrc(IpOrigen);
							}
							if (IpOrigen.equals(new String("$HOME_NET"))) {
								// Coger el valor del fichero de rule.properties
								IpOrigen = System.getProperty("HOME_NET");
								System.out.println("IpOrigen:" + IpOrigen);
								NuevaRegla.setIpSrc(IpOrigen);
							}
							NuevaRegla.setIpSrc(IpOrigen);
						}
						if (trat_cabezera == 3) {
							PuertoSrc = cadena.substring(0, inicio);
							System.out.println("Puerto Origen:" + PuertoSrc);
							NuevaRegla.setPortSrc(PuertoSrc);
						}
						if (trat_cabezera == 4) {
							Direccion = cadena.substring(0, inicio);
							System.out.println("Direccion:" + Direccion);
							NuevaRegla.setDireccion(Direccion);
						}
						if (trat_cabezera == 5) {
							IpDestino = cadena.substring(0, inicio);
							System.out.println("IpDestino:" + IpDestino);
							if (IpDestino.compareTo(new String("$HOME_NET")) == 0) {
								// Coger el valor del fichero de rule.properties
								IpDestino = System.getProperty("HOME_NET");
								System.out.println("IpDestino:" + IpDestino);
								NuevaRegla.setIpDst(IpDestino);
							} else {
								NuevaRegla.setIpDst(IpDestino);
							}
							if (IpDestino.equals(new String("$EXTERNAL_NET"))) {
								// Coger el valor del fichero de rule.properties
								IpDestino = System.getProperty("EXTERNAL_NET");
								System.out.println("IpDestino:" + IpDestino);
								NuevaRegla.setIpDst(IpDestino);
							} else {
								NuevaRegla.setIpDst(IpDestino);
							}

						}
						if (trat_cabezera == 6) {
							PuertoDst = cadena.substring(0, inicio);
							System.out.println("Puerto Dst:" + PuertoDst);
							NuevaRegla.setPortDest(PuertoDst);
						}

						cadena = cadena.substring(inicio + 1);
						System.out.println("resultado=====>" + cadena);
						trat_cabezera++;
						char_ini = (String) cadena.substring(0, 1);
						//   res = char_ini.compareTo(new String("("));
					}
					// sale del bucle por encontrar el caracter "("
					// Tratamiento de las opciones.
					char_ini = (String) cadena.substring(0, 1);

					while (char_ini.compareTo(")") != 0) {

						if (char_ini.compareTo("(") == 0) {
							cadena = cadena.substring(1);
						}
						if (char_ini.compareTo(" ") == 0) {
							// elimino los espacios en blanco antes de las
							// opciones
							while (char_ini.compareTo(" ") == 0) {
								cadena = cadena.substring(1);
								char_ini = (String) cadena.substring(0, 1);
							}
						}

						inicio = cadena.indexOf(";", 0);
						System.out.println("\nPos:" + inicio);
						contenido_opcion = cadena.substring(0, inicio);

						tratarOpcion(contenido_opcion, NuevaRegla);

						cadena = cadena.substring(inicio + 1);
						char_ini = (String) cadena.substring(0, 1);
					}

					++lines;
					// Añadir una regla mas al vector de reglas.
					// una regla por linea.
					if (!comentario) {
						VectorRules.add(NuevaRegla);
					}
					trat_cabezera = 0;
				}

			}// fin_while

			System.out.println("Lines del fichero :" + lines);

		} catch (Exception e) {
			e.printStackTrace();
		}

	}// fin_loadrules

	public void tratarOpcion(String contenido_opcion, Rule NuevaRegla) {
		String res = contenido_opcion;
		String char_ini;
		String opcion;
		String contenido = null;
		String reference;

		int inicio;
		int long_res;
		int long_contenido;
		int priority;
		int within;
		int depth;
		int j = 0;
		boolean classification = false;
		long_res = res.length();

		System.out.println("\nOpcion Completa:" + res);

		inicio = res.indexOf(":", 0);
		if (inicio != -1) {

			opcion = res.substring(0, inicio);
			System.out.println("tipo Opcion:" + opcion);

			//Dependiendo del tipo de opcion hay que tratar su contenido de
			//una forma u otra.
			if (opcion.compareTo("msg") == 0) {

				contenido = res.substring(inicio + 2, long_res - 1);
				NuevaRegla.setMsg(contenido);

			}
			if (opcion.compareTo("content") == 0) {
				contenido = res.substring(inicio + 1, long_res);
				NuevaRegla.setContent(contenido);
			}
			if (opcion.compareTo("itype") == 0) {
				contenido = res.substring(inicio + 1, long_res);
				NuevaRegla.setitype(contenido);
			}
			if (opcion.compareTo("dsize") == 0) {
				contenido = res.substring(inicio + 1, long_res);
				NuevaRegla.setdsize(contenido);
			}
			if (opcion.compareTo("id") == 0) {
				contenido = res.substring(inicio + 1, long_res);
				NuevaRegla.setid(contenido);
			}
			if (opcion.compareTo("ttl") == 0) {
				contenido = res.substring(inicio + 1, long_res);
				NuevaRegla.setTtl(contenido);
			}
			if (opcion.compareTo("icode") == 0) {
				contenido = res.substring(inicio + 1, long_res);
				NuevaRegla.seticode(contenido);
			}
			if (opcion.compareTo("ack") == 0) {
				contenido = res.substring(inicio + 1, long_res);
				NuevaRegla.setack(contenido);
			}
			if (opcion.compareTo("seq") == 0) {
				contenido = res.substring(inicio + 1, long_res);
				NuevaRegla.setseq(contenido);
			}
			if (opcion.compareTo("offset") == 0) {
				contenido = res.substring(inicio + 1, long_res);
				NuevaRegla.setoffset(contenido);
			}
			if (opcion.compareTo("depth") == 0) {
				contenido = res.substring(inicio + 1, long_res);
				NuevaRegla.setdepth(contenido);
			}
			if (opcion.compareTo("flags") == 0) {
				contenido = res.substring(inicio + 1, long_res);
				NuevaRegla.setFlags(contenido);
			}
			if (opcion.compareTo("sid") == 0) {
				contenido = res.substring(inicio + 1, long_res);
				NuevaRegla.setsid(contenido);
			}
			if (opcion.compareTo("rev") == 0) {
				contenido = res.substring(inicio + 1, long_res);
				NuevaRegla.setrev(contenido);
			}
			if (opcion.compareTo("classtype") == 0) {
				contenido = res.substring(inicio + 1, long_res);
				NuevaRegla.setclasstype(contenido);
				while (j < VectorClasification.size()
						&& classification == false) {
					ClassificationRules l_classification = (ClassificationRules) VectorClasification
							.elementAt(j);
					if (l_classification.getDescripcion_corta().equals(
							contenido)) {
						//Cargamos la prioridad de la regla
						NuevaRegla.setpriority(l_classification.getPrioridad());
						classification = true;
					}

					j++;
				}

			} else {
				NuevaRegla.setpriority("5");
			}

			if (opcion.compareTo("priority") == 0) {
				contenido = res.substring(inicio + 1, long_res);
				NuevaRegla.setpriority(contenido);
			}
			if (opcion.compareTo("reference") == 0) {
				contenido = res.substring(inicio + 1, long_res);
				NuevaRegla.setreference(contenido);

			}

			if (opcion.compareTo("within") == 0) {
				contenido = res.substring(inicio + 1, long_res);
				NuevaRegla.setwithin(contenido);
			}
		}
		System.out.println("contenido Opcion:" + contenido);

		return;
	}// f intratar_opcion

	public void DatosRules(String IpOrigen, String IpDestino, String portsrc,
			String portdest, String protocol, byte[] data, String itype,
			String icode, String dsize, String id, String ttl, String flags,
			String ack, String seq, Packet packet, int numerodepaquete) {

		String Accion;
		String IpSrc;
		String IpDst;
		String Protocolo;
		String PortSrc;
		String PortDest;
		String Direccion;
		boolean rule_existente = false;
		int i = 0;
		while ((i < VectorRules.size()) && (!rule_existente)) {

			Rule objetoRule = (Rule) VectorRules.elementAt(i);
			// Comparo los datos del paquete con los de la regla

			if (objetoRule.getDireccion().equals("->")) {
				if ((IpOrigen.equals(objetoRule.getIpSrc()) || objetoRule
						.getIpSrc().equals("any"))
						&& (IpDestino.equals(objetoRule.getIpDst()) || objetoRule
								.getIpDst().equals("any"))
						&& (portsrc.equals(objetoRule.getPortSrc()) || objetoRule
								.getPortSrc().equals("any"))
						&& (portdest.equals(objetoRule.getPortDest()) || objetoRule
								.getPortDest().equals("any"))) {

					rule_existente = TratarPropiedades(objetoRule, protocol,
							data, itype, icode, dsize, id, ttl, flags, ack,
							seq, numerodepaquete);
					if (rule_existente) {
						System.out.println("ALerta Contenido ===>>>"
								+ rule_existente);
						System.out.println("ALerta Contenido MSG===>>>"
								+ objetoRule.getMsg() + "paquete:"
								+ numerodepaquete);
						log.addAlert(objetoRule, numerodepaquete, packet,
								IpOrigen, IpDestino, portsrc, portdest);
						TablaAlertas.DatosTablaAlerts(objetoRule,
								numerodepaquete, packet, IpOrigen, IpDestino,
								portsrc, portdest);
					}

				}
			} else {
				if (objetoRule.getDireccion().equals("<>")) { // Bidireccional.

					// Direccion ->
					if (((IpOrigen.equals(objetoRule.getIpSrc()) || objetoRule
							.getIpSrc().equals("any"))
							&& (IpDestino.equals(objetoRule.getIpDst()) || objetoRule
									.getIpDst().equals("any"))
							&& (portsrc.equals(objetoRule.getPortSrc()) || objetoRule
									.getPortSrc().equals("any")) && (portdest
							.equals(objetoRule.getPortDest()) || objetoRule
							.getPortDest().equals("any")))
							||
							// Direccion <-
							((IpOrigen.equals(objetoRule.getIpDst()) || objetoRule
									.getIpDst().equals("any"))
									&& (IpDestino.equals(objetoRule.getIpSrc()) || objetoRule
											.getIpSrc().equals("any"))
									&& (portsrc
											.equals(objetoRule.getPortDest()) || objetoRule
											.getPortDest().equals("any")) && (portdest
									.equals(objetoRule.getPortSrc()) || objetoRule
									.getPortSrc().equals("any")))) {

						rule_existente = TratarPropiedades(objetoRule,
								protocol, data, itype, icode, dsize, id, ttl,
								flags, ack, seq, numerodepaquete);
						if (rule_existente) {
							log.addAlert(objetoRule, numerodepaquete, packet,
									IpOrigen, IpDestino, portsrc, portdest);
							TablaAlertas.DatosTablaAlerts(objetoRule,
									numerodepaquete, packet, IpOrigen,
									IpDestino, portsrc, portdest);
						}
					}

				}
			}

			i++;
		}

		System.out.println("\nVectorRules size :" + VectorRules.size());

	}

	public boolean TratarPropiedades(Rule objetoRule, String protocol,
			byte[] data, String itype, String icode, String dsize, String id,
			String ttl, String flags, String ack, String seq,
			int numerodepaquete) {
		int i;
		int j = 0;
		String Contenido;
		String Contenido_std = "";
		String char_ini;
		String datospk = null;

		boolean contiene = true;
		boolean alert_contiene = true;

		try {

			if (data.length != 0) {

				while (j < objetoRule.getVectorContent().size()
						&& alert_contiene == true) {
					// contenido del elemento j-esimo del vector
					Contenido = (String) objetoRule.getVectorContent()
							.elementAt(j);
					char_ini = (String) Contenido.substring(0, 1);

					if (char_ini.equals("!")) {
						contiene = false;
						Contenido = Contenido.substring(1);
					}
					Contenido = Contenido.substring(1, Contenido.length() - 1);

					System.out.println("\nContenidoooooo:" + Contenido
							+ contiene);
					System.out.println(" ");
					char_ini = (String) Contenido.substring(0, 1);
					if (char_ini.equals("|")) {
						// Los datos de la regla estan en formato hexadecimal.
						Contenido = Contenido.substring(1,
								Contenido.length() - 1);
						// Elimimino los espacion en blanco del contenido
						// hexadecimal de la regla

						String[] str1 = Contenido.split(" ");
						for (i = 0; i < str1.length; i++)
							Contenido_std += str1[i];

						// Paso los data de cada paquete a un formato estandar
						// hexadecimal.

						BigInteger bi = new BigInteger(data);
						datospk = bi.toString(16);
						if (datospk.length() % 2 != 0) {
							// Pad with 0
							datospk = "0" + datospk;
						}

						// en datospk tenemos los data del paquete
						// en contenido tenemos los datos que queremos buscar en
						// el data del paquete
						System.out.println("\nContenido:" + Contenido_std);
						System.out.println("\ndatospk:" + datospk);
						System.out.println();
						if (contiene) {
							if (datospk.indexOf(Contenido_std) != -1) {
								alert_contiene = true;
							} else {
								alert_contiene = false;
							}
						} else {
							if (datospk.indexOf(Contenido_std) != -1) {
								alert_contiene = false;
							} else {
								alert_contiene = true;
							}

						}

					} else {
						//  Los datos de la rule estan en Asci
						if (contiene) {
							datospk = new String(data, "ISO-8859-1");
							if (datospk.indexOf(Contenido) != -1) {
								alert_contiene = true;
							} else {
								alert_contiene = false;
							}

						} else {
							if (datospk.indexOf(Contenido) != -1) {
								alert_contiene = false;
							} else {
								alert_contiene = true;
							}
						}
					}// fin_de_|
					j++;
				} // fin del for.ahora while

				// Sale bien porque el vector de contenidos se ha terminado o
				// existe un alert_contiene a false
				// por lo que la regla ya no se va a cumplir
				if ((objetoRule.getProtocolo() != null)
						&& !objetoRule.getProtocolo().equals(protocol)) {
					alert_contiene = false;
				} else {
					if ((objetoRule.getitype() != null)
							&& !objetoRule.getitype().equals(itype)) {
						// Si exixte el campo en la regla y no coinciden
						alert_contiene = false;
					} else { // Existe el campo en la regla y coinciden =>
						// seguir evaluando
						if ((objetoRule.geticode() != null)
								&& !objetoRule.geticode().equals(icode)) {
							alert_contiene = false;
						} else {
							if ((objetoRule.getdsize() != null)
									&& !objetoRule.getdsize().equals(dsize)) {
								alert_contiene = false;
							} else {
								if ((objetoRule.getid() != null)
										&& !objetoRule.getid().equals(id)) {
									alert_contiene = false;
								} else {
									if ((objetoRule.getTtl() != null)
											&& !objetoRule.getTtl().equals(ttl)) {
										alert_contiene = false;
									} else {
										if ((objetoRule.getFlags() != null)
												&& (flags.indexOf(objetoRule
														.getFlags()) == -1)) {
											alert_contiene = false;
										} else {
											if ((objetoRule.getack() != null)
													&& !objetoRule.getack()
															.equals(ack)) {
												alert_contiene = false;
											} else {
												if ((objetoRule.getseq() != null)
														&& !objetoRule.getseq()
																.equals(seq)) {
													alert_contiene = false;
												}
											}

										}

									}
								}
							}
						}
					}//2_end_if
				}//1_end_if

			} else {
				alert_contiene = false;
			}// Fin de data.length

		} catch (Exception e) {
			e.printStackTrace();
		}

		return alert_contiene;
	}

	public Vector getVectorRules() {
		return VectorRules;
	}

	public void OrdenarReglas() {
		// Ordenar Vector de Rules por Prioridad;
		Collections.sort(VectorRules);
	}

}// Fin de Clase

