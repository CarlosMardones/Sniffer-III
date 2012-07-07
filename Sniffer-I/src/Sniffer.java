
import presentacion.VentanaPresentacion;
import java.util.Properties;
import javax.swing.JOptionPane;
import jpcap.NetworkInterface;
import dominio.ComandoAcciones;


/**
 * <p>Inicio de la aplicación “Sniffer” que contiene el método <span class=SpellE>main</span>.
 * Argumentos a pasar a la aplicación: <br>
 * 
 * - Para ejecutar el programa hay que poner<span style='mso-spacerun:yes'> 
 * </span>lo siguiente y esto iniciará por defecto en modo grafico:</p>
 * 
 * <p style='text-indent:35.4pt'><span class=GramE><span style='font-family:"Courier New";
 * color:blue;background:white;mso-highlight:white'>java</span></span><span
 * style='font-family:"Courier New";color:blue;background:white;mso-highlight:
 * white'> -<span class=SpellE>jar</span> <span class=SpellE>sniffer.jar</span></span><span
 * style='font-family:"Courier New";color:blue'><o:p></o:p></span></p>
 * 
 * <p>-Parámetros de entrada:</p>
 * 
 * <p class=MsoNormal style='text-indent:35.4pt;mso-layout-grid-align:none;
 * text-autospace:none'><span class=GramE><span lang=EN-GB style='font-family:
 * "Courier New";color:blue;background:white;mso-highlight:white;mso-ansi-language:
 * EN-GB'>java</span></span><span lang=EN-GB style='font-family:"Courier New";
 * color:blue;background:white;mso-highlight:white;mso-ansi-language:EN-GB'> -jar <span
 * class=SpellE>sniffer.jar</span> [-graphic]</span><span lang=EN-GB
 * style='font-family:"Courier New";mso-ansi-language:EN-GB'><o:p></o:p></span></p>
 * 
 * <p class=MsoNormal style='mso-layout-grid-align:none;text-autospace:none'><span
 * lang=EN-GB style='font-family:"Courier New";color:blue;background:white;
 * mso-highlight:white;mso-ansi-language:EN-GB'><span
 * style='mso-spacerun:yes'>                      </span></span><span
 * style='font-family:"Courier New";color:blue;background:white;mso-highlight:
 * white'>[-<span class=SpellE>command</span> (-<span class=SpellE>scan</span> [-<span
 * class=SpellE>dispo</span>] |</span><span style='font-family:"Courier New"'><o:p></o:p></span></p>
 * 
 * <p class=MsoNormal style='mso-layout-grid-align:none;text-autospace:none'><span
 * style='font-family:"Courier New";color:blue;background:white;mso-highlight:
 * white'><span style='mso-spacerun:yes'>                      </span><span
 * style='mso-tab-count:1'>  </span><span
 * style='mso-spacerun:yes'>          </span>-<span class=SpellE>export</span> |</span><span
 * style='font-family:"Courier New"'><o:p></o:p></span></p>
 * 
 * <p class=MsoNormal style='mso-layout-grid-align:none;text-autospace:none'><span
 * style='font-family:"Courier New";color:blue;background:white;mso-highlight:
 * white'><span style='mso-spacerun:yes'>                                 
 * </span>-<span class=SpellE>fromfile</span>) parámetros]</span><span
 * style='font-family:"Courier New"'><o:p></o:p></span></p>
 * 
 * <p class=MsoNormal style='mso-layout-grid-align:none;text-autospace:none'><span
 * style='font-family:"Courier New";color:blue;background:white;mso-highlight:
 * white'><span style='mso-spacerun:yes'>                      </span>[-<span
 * class=SpellE>dispo</span>]</span><span style='font-family:"Courier New"'><o:p></o:p></span></p>
 * 
 * <p class=MsoNormal style='mso-layout-grid-align:none;text-autospace:none'><span
 * style='font-family:"Courier New"'><o:p>&nbsp;</o:p></span></p>
 * 
 * <p class=MsoNormal style='text-indent:35.4pt;mso-layout-grid-align:none;
 * text-autospace:none'><span class=GramE><span style='font-family:"Courier New";
 * color:blue;background:white;mso-highlight:white'>parámetros</span></span><span
 * style='font-family:"Courier New";color:blue;background:white;mso-highlight:
 * white'><span style='mso-tab-count:1'>    </span>Archivo .XML que contienen <span
 * class=SpellE>parametros</span> del Sniffer.</span><span style='font-family:
 * "Courier New"'><o:p></o:p></span></p>
 * 
 * <p class=MsoNormal style='mso-layout-grid-align:none;text-autospace:none'><span
 * style='font-family:"Courier New";color:blue;background:white;mso-highlight:
 * white'><span style='mso-spacerun:yes'>  </span><span style='mso-tab-count:1'>   </span>-<span
 * class=SpellE>graphic</span><span style='mso-tab-count:1'> </span>Modo gráfico.</span><span
 * style='font-family:"Courier New"'><o:p></o:p></span></p>
 * 
 * <p class=MsoNormal style='text-indent:35.4pt;mso-layout-grid-align:none;
 * text-autospace:none'><span style='font-family:"Courier New";color:blue;
 * background:white;mso-highlight:white'>-<span class=SpellE>command</span><span
 * style='mso-tab-count:1'> </span>Modo comando.</span><span style='font-family:
 * "Courier New"'><o:p></o:p></span></p>
 * 
 * <p class=MsoNormal style='text-indent:35.4pt;mso-layout-grid-align:none;
 * text-autospace:none'><span style='font-family:"Courier New";color:blue;
 * background:white;mso-highlight:white'>-<span class=SpellE>dispo</span><span
 * style='mso-tab-count:1'>   </span>Muestra o elige dispositivo de captura.</span><span
 * style='font-family:"Courier New"'><o:p></o:p></span></p>
 * 
 * <p class=MsoNormal style='mso-layout-grid-align:none;text-autospace:none'><span
 * style='font-family:"Courier New";color:blue;background:white;mso-highlight:
 * white'><span style='mso-spacerun:yes'>  </span><span style='mso-tab-count:1'>   </span>-<span
 * class=SpellE>scan</span><span style='mso-tab-count:2'>         </span>Captura
 * de paquetes según los parámetros</span><span style='font-family:"Courier New"'><o:p></o:p></span></p>
 * 
 * <p class=MsoNormal style='mso-layout-grid-align:none;text-autospace:none'><span
 * style='background:white;mso-highlight:white'><span style='mso-spacerun:yes'> 
 * </span><span style='mso-tab-count:1'>          </span></span><span
 * style='font-family:"Courier New";color:blue;background:white;mso-highlight:
 * white'>-<span class=SpellE>export</span><span style='mso-tab-count:1'>  </span>Exporta
 * paquetes según los parámetros<o:p></o:p></span></p>
 * 
 * <p class=MsoNormal style='text-indent:35.4pt;mso-layout-grid-align:none;
 * text-autospace:none'><span style='font-family:"Courier New";color:blue;
 * background:white;mso-highlight:white'>-<span class=SpellE>fromfile</span><span
 * style='mso-tab-count:1'> </span>Captura desde fichero según los parámetros</span><span
 * style='font-size:10.0pt;font-family:"Courier New";color:blue;background:white;
 * mso-highlight:white'><br>
 * </span>&nbsp;&nbsp;&nbsp;&nbsp;</p>
 * 
 * <p>&nbsp;<span style='mso-spacerun:yes'>  </span><b>1</b> - <span
 * style='font-family:"Courier New";color:blue;background:white;mso-highlight:
 * white'>[-<span class=SpellE>graphic</span>]</span><span style='font-family:
 * "Courier New";color:blue'> o sin ningún parámetro<o:p></o:p></span></p>
 * 
 * <p style='margin:0cm;margin-bottom:.0001pt'><span style='mso-spacerun:yes'>  
 * </span>Inicia el programa en modo gráfico donde nos permite realizar Capturas,
 * Exportaciones, y Capturas desde fichero.</p>
 * 
 * <p style='margin:0cm;margin-bottom:.0001pt'><span style='mso-spacerun:yes'>  
 * </span>Además de la visualización grafica de los paquetes capturados</p>
 * 
 * <p class=MsoNormal style='margin-top:6.0pt;margin-right:0cm;margin-bottom:6.0pt;
 * margin-left:0cm;mso-layout-grid-align:none;text-autospace:none'>&nbsp;&nbsp;&nbsp;<b>2</b>
 * - <span style='font-family:"Courier New";color:blue;background:white;
 * mso-highlight:white'>[-<span class=SpellE>command</span> (-<span class=SpellE>scan</span>
 * 
 * [-<span class=SpellE>dispo</span>] | -<span class=SpellE>export</span> | -<span
 * class=SpellE>fromfile</span>) parámetros]</span><span style='font-family:"Courier New"'><o:p></o:p></span></p>
 * 
 * <p><span style='mso-spacerun:yes'> </span><span style='mso-spacerun:yes'>   
 * </span><b style='mso-bidi-font-weight:normal'>2.1 - </b><span style='font-family:
 * "Courier New";color:blue;background:white;mso-highlight:white'>[-<span
 * class=SpellE>command</span> –<span class=SpellE>scan</span></span><span
 * style='font-family:"Courier New";color:blue'> <span class=GramE><span
 * style='background:white;mso-highlight:white'>parámetros</span><span
 * style='font-family:"Times New Roman";color:windowtext'> ]</span></span></span></p>
 * 
 * <p style='text-indent:35.4pt'>Permite iniciar una captura en modo comando con
 * los parámetros pasados en formato .XML</p>
 * 
 * <p><b style='mso-bidi-font-weight:normal'><span style='mso-spacerun:yes'>    
 * </span><span style='mso-tab-count:1'>       </span>2.1.1 -</b> <span
 * style='font-family:"Courier New";color:blue;background:white;mso-highlight:
 * white'>[-<span class=SpellE>command</span> –<span class=SpellE>scan</span></span><span
 * style='font-family:"Courier New";color:blue'> -<span class=SpellE>dispo</span> <span
 * class=GramE><span style='background:white;mso-highlight:white'>parámetros</span><span
 * style='font-family:"Times New Roman";color:windowtext'> ]</span></span></span></p>
 * 
 * <p style='text-indent:35.4pt'><span style='mso-spacerun:yes'>      </span>Permite
 * iniciar una captura en modo comando con los parámetros pasados en formato .XML,
 * pero además pide por teclado el dispositivo de captura</p>
 * 
 * <p><span style='mso-spacerun:yes'> </span><span style='mso-spacerun:yes'>   
 * </span><b style='mso-bidi-font-weight:normal'>2.2 - </b><span style='font-family:
 * "Courier New";color:blue;background:white;mso-highlight:white'>[-<span
 * class=SpellE>command</span> -<span class=SpellE>export</span></span><span
 * style='font-family:"Courier New";color:blue'> <span class=GramE><span
 * style='background:white;mso-highlight:white'>parámetros</span><span
 * style='font-family:"Times New Roman";color:windowtext'> ]</span></span></span></p>
 * 
 * <p style='text-indent:35.4pt'>Permite iniciar una exportación en modo comando
 * con los parámetros pasados en formato .XML</p>
 * 
 * <p><span style='mso-spacerun:yes'> </span><span style='mso-spacerun:yes'>   
 * </span><b style='mso-bidi-font-weight:normal'>2.3 - </b><span style='font-family:
 * "Courier New";color:blue;background:white;mso-highlight:white'>[-<span
 * class=SpellE>command</span> -<span class=SpellE>export</span></span><span
 * style='font-family:"Courier New";color:blue'> <span class=GramE><span
 * style='background:white;mso-highlight:white'>parámetros</span><span
 * style='font-family:"Times New Roman";color:windowtext'> ]</span></span></span></p>
 * 
 * <p style='text-indent:35.4pt'>Permite iniciar una captura desde fichero en modo
 * comando con los parámetros pasados en formato .XML</p>
 * 
 * <p>&nbsp;<span style='mso-spacerun:yes'>  </span><b>3</b> - <span
 * style='font-family:"Courier New";color:blue;background:white;mso-highlight:
 * white'>[-<span class=SpellE>dispo</span>]</span><span style='font-family:"Courier New";
 * color:blue'> <o:p></o:p></span></p>
 * 
 * <p><span style='mso-spacerun:yes'>      </span>Muestra los dispositivos de
 * captura existentes</p>
 * 
 * @author Leonardo Garca & Jose Ramón Gutierrez
 * @version 2.0
 * @see presentacion.VentanaPresentacion
 */
public class Sniffer {
	/**
	 * Genera una nueva instancia de la clase Sniffer que implementa la
	 * interface de usuario inicial de la aplicación.
	 * Se le pueden pasar parametros, sino se pasa ninguno arranca en modo gráfico
	 * con el parametro -help te muestra la estructura del commando
	 * 
	 * @param args
	 *            parmetros de entrada en la lnea de comando, según los
	 *            argumentos introducidos incia en modo gráfico, en modo lnea
	 *            de comandos o realiza directamente un sniffer.
	 * @see ComandoAcciones
	 * @see presentacion.VentanaPresentacion
	 */
	public static void main(String args[]) {
		try {
			int paramError = 0;
			if (args.length == 0) {
				System.out.println("Modo Grafico");
				new Sniffer();
			} else {
				if (args.length == 1) {
					if (args[0].equals("-graphic")) {
						System.out.println("Modo Grafico");
						new Sniffer();
					}else if (args[0].equals("-command")) {
						paramError= 3;
					}else if (args[0].equals("-help")) {
						ayudaGeneral();
					}else if (args[0].equals("-dispo")) {
						preConfigParam();
						ComandoAcciones com=new ComandoAcciones();
						com.pedirDispositivoCaptura(false);
					}else {
						paramError= 1;
					}
				} else {
					if (args[0].equals("-command")){
						if (args.length == 2) {
							if(args[1].equals("-scan")){
								paramError= 4;
							}else if(args[1].equals("-export")){
								paramError= 6;
							}else if(args[1].equals("-fromfile")){
								paramError= 7;
							}
							else if(args[1].equals("-state")){
								paramError= 9;
							}
							else{
								paramError= 3;
							}
						}else if (args.length == 3) {
							if(args[1].equals("-scan")){
								if (args[2].equals("-dispo")){
									paramError= 5;
								}else{
									//System.out.println("---> Scan" + args[2]);
									preConfigParam();
									ComandoAcciones com=new ComandoAcciones();
									com.runScan(args[2]);
								}
							}else if(args[1].equals("-export")){
								preConfigParam();
								//System.out.println("<-> " + args[2]);
								ComandoAcciones com=new ComandoAcciones();
								com.runExport(args[2]);
							}else if(args[1].equals("-fromfile")){
								preConfigParam();
								ComandoAcciones com=new ComandoAcciones();
								com.runFromFile(args[2]);
							}else if(args[1].equals("-state")){
								preConfigParam();
								ComandoAcciones com=new ComandoAcciones();
								com.runState(args[2]);
							}
							else{
								paramError= 3;
							}
						}else if (args.length == 4){
							if(args[1].equals("-scan")){
								if (args[2].equals("-dispo")){
									int opt;
									preConfigParam();
									ComandoAcciones com=new ComandoAcciones();
									opt=com.pedirDispositivoCaptura(true);
									if (opt==0){
										paramError = 101;
									}else if(opt==-1){
										paramError = 103;
									}
									else{
										com.runScanDispo(args[3], opt);
									}
									//
									//System.out.println("-scan -dispo yes o no");

								}
								else{
									paramError= 4;
								}
							}
							else{
								paramError= 3;
							}
						}
						else{
							paramError = 3;
						}
					} else {
						paramError= 2;
					}
				}
			}
			if (paramError > 0)System.out.println("");
			if (paramError == 1)System.out.println("!!! ERROR - Parametros incorrectos !!!");
			if (paramError == 2)System.out.println("!!! ERROR - Parametros incorrectos, no modo correcto !!!");
			if (paramError == 3)System.out.println("!!! ERROR - Parametros incorrectos en modo -command !!!");
			if (paramError == 4)System.out.println("!!! ERROR - Parametros incorrectos en modo -command -scan !!!");
			if (paramError == 5)System.out.println("!!! ERROR - Parametros incorrectos en modo -command -scan -dispo !!!");
			if (paramError == 6)System.out.println("!!! ERROR - Parametros incorrectos en modo -command -export !!!");
			if (paramError == 7)System.out.println("!!! ERROR - Parametros incorrectos en modo -command -fromfile !!!");
			if (paramError == 10)System.out.println("!!! ERROR - Parametros incorrectos en modo -command falta parametros!!!");
			if (paramError == 11)System.out.println("!!! ERROR - Parametros incorrectos en modo -command -estate !!!");
			
			if (paramError == 101)System.out.println("!!! No se ha seleccionado ningun dispositivo!!!");
			if (paramError == 102)System.out.println("!!! ERROR - Dispositivo seleccionado no es correcto!!!");
			if (paramError == 103)System.out.println("!!! ERROR - Sólo teclado numérico - vuelva a inicar la aplicación");
			if (paramError == 99)System.out.println("!!! ERROR - oooo !!!");
			
			if(paramError > 0 && paramError < 100){
				System.out.println("");
				ayudaGeneral();
			}
		} catch (java.lang.Exception e) {
			e.printStackTrace();
			System.err.println("Fallo.");
		}
	}//main

	/**
	 * Llama a la ventana principal de programa precargando las opciones
	 * correctas.
	 * 
	 * @see presentacion.VentanaPresentacion
	 */
	public Sniffer() { //VentanaPresentacion f=new VentanaPresentacion();

			preConfigParam();
			VentanaPresentacion f = new VentanaPresentacion(false);
	}
	
	/**
	 * Carga las preferencias del programa y comprueba la existencia
	 * de la libreria de captura
	 */
	public static void preConfigParam(){
		try {
			//System.out.println("Leer preferencias");
			// carga del fichero de propiedades tool.properties
			Properties p = new Properties(System.getProperties());
			//carga las propiedades
			/*
			FileInputStream propFile = new FileInputStream(FachadaFicheroDirectorios
					.getdirectorioData("DIR_PROPERTIES")+ System.getProperty("file.separator") + "tool.properties");
			FileInputStream propruleFile = new FileInputStream(FachadaFicheroDirectorios
					.getdirectorioData("DIR_PROPERTIES")+ System.getProperty("file.separator") + "ids.properties");
			p.load(propFile);
			p.load(propruleFile);
			*/	
			//selecciona las propiedades del sistema.
			System.setProperties(p);
			//System.out.println(System.getProperty("java.library.path"));
			//System.out.println(System.getProperty("user.dir"));
			
			//System.out.println("esto sera => " + p.getProperty("TableView"));
			
			if (System.getProperty("os.name").compareTo("Linux") == 0) {
				
			} else {
				System.loadLibrary("./dll/pcaplib/Jpcap");
				//System.loadLibrary("./dll/jpcap/Jpcap");
				//System.err.println("entro a leer librerias");
			}
			
			//System.loadLibrary("Jpcap_2");
			
		} catch (Exception e) {
			System.err.println("I/O failed.");
		}
		
		try{
			Class.forName("jpcap.JpcapCaptor");
			NetworkInterface[] devices=jpcap.JpcapCaptor.getDeviceList();
		}catch(ClassNotFoundException e){
			JOptionPane.showMessageDialog(null,"Cannot find Jpcap. Please install Jpcap.",
					"Error",JOptionPane.ERROR_MESSAGE);
			System.exit(-1);
		}
		
	}
	
	/**
	 * Ayuda del programa para el modo comando
	 */
	private static void ayudaGeneral(){
		System.out.println("Sniffer II - Ayuda");
		System.out.println("");
		System.out.println("java -jar sniffer.jar [-graphic]");
		System.out.println("                      [-command (-scan [-dispo] |");
		System.out.println("                      	         -export |");
		System.out.println("                                 -fromfile |");
		System.out.println("                                 -state) parámetros]");
		System.out.println("                      [-dispo]");
		System.out.println("\n  parámetros	Archivo .XML que contienen parametros del Sniffer.");
		System.out.println("  -graphic	Modo gráfico.");
		System.out.println("  -command	Modo comando.");
		System.out.println("  -dispo	Muestra o elige dispositivo de captura.");
		System.out.println("  -scan		Captura de paquetes según los parámetros");
		System.out.println("  -export	Exporta paquetes según los parámetros");
		System.out.println("  -fromfile	Captura desde fichero según los parámetros");
		System.out.println("  -estate	Exporta paquetes según los parámetros");
		System.out.println("");
		
	}
}//Class Sniffer
