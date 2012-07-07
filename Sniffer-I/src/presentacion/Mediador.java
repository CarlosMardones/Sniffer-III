package presentacion;


import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.awt.Window;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;

import jpcap.JpcapCaptor;
import jpcap.packet.Packet;

import dominio.*;
import dominio.pcap.Insercion;
import dominio.preferences.*;
import dominio.properties.*;
import presentacion.avisos.Aviso;
import presentacion.comandos.Comando;
import presentacion.preferencias.*;
import presentacion.propiedadesVentana.CentrarVentana;
import presentacion.capturandoDumper.*;
import presentacion.seleccionFicheros.VentanaAbrirElegirFichero;
import presentacion.seleccionFicheros.VentanaElegirDirectorios;
import presentacion.seleccionFicheros.VentanaGuardarElegirFichero;
import presentacion.ventanaMenuSniffer.BarraHerramientas;
import presentacion.ventanaMenuSniffer.BarraMenu;
import presentacion.ventanaMenuSniffer.MenuSniffer;
import servicioAccesoDatos.FabricaAccesoDatos;
import servicioAccesoDatos.FabricaAccesoDatosIF;
import servicioAccesoDatos.FachadaFichero;
import servicioAccesoDatos.FachadaFicheroDirectorios;


import dominio.preferences.identificacion.*;
//import org.jfree.chart.ChartPanel;
//import javax.swing.tree.*;

/**
 * Mediador de la aplicación que comunica las diferentes ventanas.
 * 
 * @author Leonardo García & JoseRamón Gutierrez & Carlos Mardones Muga
 * @version 3.0
 * 
 * @see java.awt.event.ActionListener
 * @see java.awt.event.ItemListener
 * @see javax.swing.event.ListSelectionListener
 * @see javax.swing.event.TreeSelectionListener
 * @see java.awt.event.WindowListener
 */
public class Mediador implements ActionListener, ItemListener,
		ListSelectionListener, TreeSelectionListener, WindowListener {

	/**
	 * Aplicación gráfica.
	 */
	private MenuSniffer MSniffer;

	/**
	 * Ventana Capurado.
	 */
	private Fcaptura FCaptura;
	
	/**
	 * Control de hilos.
	 */
	Thread hilo;
	
	/**
	 * Ejecuta las instrucciones correspondientes al boton pulsado.
	 * 
	 * @param e
	 *            evento escuchado.
	 */
	public void actionPerformed(ActionEvent e) {
		Comando cmd = (Comando) e.getSource();
		cmd.ejecutar();
	} // actionPerformed

	/**
	 * Ejecuta las instrucciones correspondientes a seleccionar un elemento en
	 * un árbol.
	 * 
	 * @param e
	 *            evento escuchado.
	 */
	public void valueChanged(TreeSelectionEvent e) {
		Comando cmd = (Comando) e.getSource();
		cmd.ejecutar();
	}

	/**
	 * Ejecuta las instrucciones correspondientes a un cambio de estado.
	 * 
	 * @param e
	 *            evento escuchado.
	 */
	public void itemStateChanged(ItemEvent e) {
		Comando cmd = (Comando) e.getSource();
		cmd.ejecutar();
	} // itemStateChanged

	/**
	 * Ejecuta las instrucciones correspondientes a un cambio de estado.
	 * 
	 * @param e
	 *            evento escuchado.
	 */
	public void valueChanged(ListSelectionEvent e) {
		Comando cmd = (Comando) e.getSource();
		cmd.ejecutar();
	} // itemStateChanged

	/**
	 * Ejecuta las instrucciones correspondientes a una ventana desactivada.
	 * 
	 * @param e
	 *            evento escuchado.
	 */
	public void windowDeactivated(WindowEvent e) {
		Comando cmd = (Comando) e.getSource();
		cmd.ejecutar();
	} // itemStateChanged

	/**
	 * Ejecuta las instrucciones correspondientes a una ventana activada.
	 * 
	 * @param e
	 *            evento escuchado.
	 */
	public void windowActivated(WindowEvent e) {
		Comando cmd = (Comando) e.getSource();
		cmd.ejecutar();
	} // itemStateChanged

	/**
	 * Ejecuta las instrucciones correspondientes al icono de una ventana.
	 * 
	 * @param e
	 *            evento escuchado.
	 */
	public void windowIconified(WindowEvent e) {
		Comando cmd = (Comando) e.getSource();
		cmd.ejecutar();
	} // itemStateChanged

	/**
	 * Ejecuta las instrucciones correspondientes al icono de una ventana.
	 * 
	 * @param e
	 *            evento escuchado.
	 */
	public void windowDeiconified(WindowEvent e) {
		Comando cmd = (Comando) e.getSource();
		cmd.ejecutar();
	} // itemStateChanged

	/**
	 * Ejecuta las instrucciones correspondientes al cierre de una ventana.
	 * 
	 * @param e
	 *            evento escuchado.
	 */
	public void windowClosing(WindowEvent e) {
		Comando cmd = (Comando) e.getSource();
		cmd.ejecutar();
	} // itemStateChanged

	/**
	 * Ejecuta las instrucciones correspondientes al icono de cierre una
	 * ventana.
	 * 
	 * @param e
	 *            evento escuchado.
	 */
	public void windowClosed(WindowEvent e) {
		Comando cmd = (Comando) e.getSource();
		cmd.ejecutar();
	} // itemStateChanged

	/**
	 * Ejecuta las instrucciones correspondientes a la apertura de una ventana.
	 * 
	 * @param e
	 *            evento escuchado.
	 */
	public void windowOpening(WindowEvent e) {
		Comando cmd = (Comando) e.getSource();
		cmd.ejecutar();
	} // itemStateChanged

	/**
	 * Ejecuta las instrucciones correspondientes al icono de abrir de una
	 * ventana.
	 * 
	 * @param e
	 *            evento escuchado.
	 */
	public void windowOpened(WindowEvent e) {
		Comando cmd = (Comando) e.getSource();
		cmd.ejecutar();
	} // itemStateChanged

	
	/** 
	 * Actualiza el campo de texto en el cual se introduce un nombre
	 * de fichero al abrirse.
	 *
	 * @param fRuta ruta del fichero a ser abierto
	 * @param ventana nombre de la ventana en la que se cuentra el campo de texto que se quiere modificar su contenido.
	 * Valores que puede tomar:<code>"Capturas","Exportaciones", "Paremetrizacion","Scripts</code>.
	 *
	 * @see presentacion.preferencias.PreferenciasConfiguracion
	 */
	public void actualizaTfRuta(String fRuta, String ventana){
			
			if(ventana.equals("Capturas")){
				PreferenciasConfiguracion.setCapturas(fRuta);
			}
			if(ventana.equals("Exportaciones")){
				PreferenciasConfiguracion.setExportaciones(fRuta);
			}
			if(ventana.equals("Paremetrizacion")){
				PreferenciasConfiguracion.setParametrizacion(fRuta);
			}
			if(ventana.equals("Scripts")){
				PreferenciasConfiguracion.setScripts(fRuta);
			}
	}//actualizaTfRuta
	
	//-----------TODOS PARA VENTANA---------------------------------------

	/**
	 * Metodo que se ocupa de cerrar una ventana sin salir de la aplicacin.
	 * 
	 * @param ventana
	 *            ventana que se quiere cerrar
	 */
	public void cerrarVentana(Frame ventana) {
		ventana.dispose();
	}

	/**
	 * Devuelve la ventana de men.
	 * 
	 * @return ventana de menu
	 */
	public Frame getVentanaPresentacion() {
		Frame frame = VentanaPresentacion.getFrames()[0];
		return frame;
	}

	
	/**
	 * Metodo que se ocupa de cerrar una ventana sin salir de la aplicacin.
	 * 
	 * @param ventana
	 *            ventana que se quiere cerrar
	 */
	public void cerrarVentana(Window ventana) {
		ventana.dispose();
	}

	/**
	 * Devuelve la ventana de menu sniffer.
	 * 
	 * @return ventana de menu
	 */
	public Frame getVentanaMenuSniffer() {
		Frame frame = MenuSniffer.getFrames()[1];
		return frame;
	}

	/**
	 * Devuelve la ventana capturando.
	 * 
	 * @return Fcaptura de menu
	 */
	public Fcaptura getFCapture() {
		return FCaptura;
	}

	/**
	 * Repinta la ventana.
	 * 
	 * @param ventana nombre de la ventana.
	 */
	public void repaintVentana(Frame ventana) {
		ventana.show();
		//ventana.repaint();

	}
	

	/**
	 * Selecciona a qu parte del programa ir según la opción escogida la
	 * ventana <code>MenuSniffer</code>. Se podr iniciar la aplicación o
	 * salir de ella.
	 * <p>
	 * Si se inicia la aplicacin se activarán o desactivarán determinados
	 * componentes grficos.
	 * 
	 * @param nombreBoton
	 *            botn que se ha presionado en el men inicial. Valoes que
	 *            toma: <code>Salir</code> o <code>Sniffer</code>.
	 *            Nuevo
	 * @return indica si se ha salido de la aplicacion o no. True si se ha
	 *         salido de la aplicacin o false si no se sale de la aplicacin y
	 *         se inicia un anlisis nuevo.
	 * @see VentanaSalir
	 * @see MenuSniffer
	 */
	public boolean irAventana(String nombreBoton) {
		if (nombreBoton.equals("Salir")) {
			new VentanaSalir(this);
			return true;
		}
		if (nombreBoton.equals("Sniffer")) {

			MSniffer = new MenuSniffer(this);
			MSniffer.resize(MSniffer.WinWidth,MSniffer.WinHeight);
			//MSniffer.resize(800,600);
			new CentrarVentana(MSniffer);
			//MSniffer.resize(800,600);
			MSniffer.setVisible(true);
			//MSniffer.runVisualizacionCaptura();
			//MSniffer.abrirFichero("SDF");

			return false;
		}
		return true;
	}//irAventana

	//-----------------------------MTODOS PARA VENTANA MEN
	// SNIFFER------------------------------
	
	

	//Guarda los datos que tenemos en pantalla
	/**
	 * Guarda el pcap leido en otro pcap (pasado como parametro) con las settings 
	 * por defecto.
	 * 
	 * @param Destino
	 *            Ruta donde se guarda el fichero.
	 */
	public void savePcapFileCaptured(String Destino) {
		int aux;
		aux = FachadaDominio.savePcapToFile(Destino);
		if (aux == 0) setPanelEstado("Fichero Capturado guardado correctamente...");
		else setPanelEstado("No se ha podido guardar el fichero capturado");
	}
	/**
	 * Genera una instancia de la ventana inicio de captura.
	 * 
	 * @param tipo estado
	 */
	public void irInicioCapture(boolean tipo) {
		setPanelEstado("Iniciando preconfiguracion para captura de datos..");
		PreferenciasCaptura prefCap = new PreferenciasCaptura(tipo, this);
		PrefCapLlenarDispositivos();
		PrefCapLlenarDatos();
		prefCap.setVisible(true);
	}
	/**
	 * Genera una instancia de la ventana inicio de insercion.
	 * 
	 * @param tipo estado
	 */
	public void irInicioInsert(boolean tipo) {
		setPanelEstado("Insercion Paquetes Definidos");
		PreferenciasInsercion prefIns = new PreferenciasInsercion();
		PrefCapLlenarDispositivosInsercion();
		prefIns.setVisible(true);
	}
	
	/**
	 * Genera una instancia de la ventana inicio de insercion de paquetes definidos.
	 * 
	 * @param tipo estado
	 */
	public void irInicioInsertDef(boolean tipo) {
		setPanelEstado("Insercion Paquetes Definidos");
		PreferenciasInsercion prefIns = new PreferenciasInsercion();
		PrefCapLlenarDispositivosInsercion();
		prefIns.setVisible(true);
	}
		
	/**
	 * Genera una instancia de la ventana inicio de insercion de paquetes capturados.
	 * 
	 * @param tipo estado
	 */
	public void irInicioInsertCap(boolean tipo) {
		setPanelEstado("Insercion Paquetes Capturados");
		PreferenciasInsercionCapturados prefIns = new PreferenciasInsercionCapturados();
		PrefCapLlenarDispositivosInsercionCap();
		prefIns.setVisible(true);
	}
	
	/**
	 * Genera una instancia de la ventana inicio de captura desde fichero.
	 * 
	 * @param tipo estado
	 */
	public void irInicioCaptureFromFile(boolean tipo) {
		setPanelEstado("Iniciando preconfiguracin para captura de datos..");
		PreferenciasFromFile prefCapFromFile = new PreferenciasFromFile(tipo, this);
		PrefCapLlenarDatosFromFile();
		prefCapFromFile.setVisible(true);

	}//irAVisualFrameCapture
	
	/**
	 * Genera una instancia de la ventana encargada de exportar a fichero.
	 * 
	 * @param tipo estado
	 * @see VentanaGuardarElegirFichero
	 */
	public void irAExportFromFile(boolean tipo) {
		setPanelEstado("Iniciando preconfiguracin para exportacin de datos..");
		
		PreferenciasExportacion prefExp = new PreferenciasExportacion(tipo,this);
		PrefExpLlenarDatos();
		prefExp.setVisible(true);
	}//irAExportFromFile
	
	
	/**
	 * Lee las properties del programa.
	 */
	public void leerProperties(MenuSniffer aux) {
		PropertiesBeanSniffer pBS = FachadaDominio.getPropBeanSniffer();
		aux.WinX = Integer.parseInt(pBS.getWinX());
		aux.WinY = Integer.parseInt(pBS.getWinY());
		aux.WinHeight = Integer.parseInt(pBS.getWinHeight());
		aux.WinWidth = Integer.parseInt(pBS.getWinWidth());
	}
	
	/**
	 * Lee las properties para la tabla devisualizacion de paquetes.
	 */
	public String leerPropertiesTableView() {
		PropertiesBeanSniffer pBS = FachadaDominio.getPropBeanSniffer();
		String aux = pBS.getTableView();
		return aux;
	}
	
	/**
	 * Graba las propiedades de progra,a
	 */
	public void grabarProperties() {
		String aux = MSniffer.getColumPosition();
		FachadaDominio.setPropertiesColumns(MSniffer.getColumPosition());// Falla aqui se carga todos las columnas
		FachadaDominio.grabarProperties();
	}
	
	/**
	 * Establece la ruta del fichero en propiedades de captura.
	 * 
	 * @param aux fichero
	 */
	public void setPrefCapFichero(String aux) {
		PreferenciasCaptura.setFichero(aux);
	}
	
	/**
	 * Establece la ruta del fichero en propiedades de captura desde fichero
	 * @param aux fichero
	 */
	public void setPrefCapFicheroFromFileSource(String aux) {
		PreferenciasFromFile.setFromFile(aux);
	}
	
	/**
	 * Establece el fichero de salida en propuiedades de captura desde fichero
	 * @param aux fichero
	 */
	public void setPrefCapFicheroFromFileFichero(String aux) {
		PreferenciasFromFile.setFichero(aux);
	}
	
	/**
	 * Establece el fichero de preferencias en ventana generar bat
	 * @param aux fichero
	 */
	public void setPrefGenFicheroPref(String aux) {
		PreferenciasGenerarBat.setFicheroPref(aux);
	}
	
	/**
	 * Establece el fichero de Script en vantana generar bat
	 * @param aux fichero
	 */
	public void setPrefGenFicheroBat(String aux) {
		PreferenciasGenerarBat.setFicheroBat(aux);
	}
	
	/**
	 * Establece el fichero de origen en ventana captura desde fichero
	 * @param aux fichero
	 */
	public void setExportFromFilePref(String aux) {
		PreferenciasExportacion.setOrigen(aux);
	}
	
	/**
	 * Establece el fichero de destino en ventana exportar
	 * @param aux fichero
	 */
	public void setExportFromFileXML(String aux) {
		PreferenciasExportacion.setDestino(aux);
	}
	
	/**
	 * Lee las propiedades por defecto de Captura, exportación, y
	 * captura desde fichero.
	 */
	public void PrefLeerPreferencias(){
		FachadaDominio.leerPreferences();
	}
	
	/**
	 * Ultimo fichero de captura leído.
	 * 
	 * @param fReaded fichero.
	 */
	public void setFileReaded(String fReaded){
		FachadaDominio.setFileReaded(fReaded);
	}
	
	/**
	 * Carga las propiedades de programa.
	 */
	public void PropLeerProperties(){
		FachadaDominio.leerProperties();
	}
	
	/**
	 * Carga en la ventana de propiedades de captura los datos establecidos en el programa
	 */
	public void PrefCapLlenarDatos() {
		try {
			preferencesBeanCapture pBC = FachadaDominio.getPrefBeanCaptura();

			PreferenciasCaptura.setDispositivo(pBC.getCapDispositive());
			PreferenciasCaptura.setFichero(pBC.getFilLocate());
			//PreferenciasCaptura.setFichero(pBC.getFilLocate());
			PreferenciasCaptura.setPromiscuo(pBC.getCapPromiscuousMode());
			//if (pBC.getCapNormal() == true){
			PreferenciasCaptura.setHost(pBC.getCapHost());
			PreferenciasCaptura.setProtocolo(pBC.getCapProtocol());
			PreferenciasCaptura.setPuerto(pBC.getCapPort());
			//}
			//else {
			PreferenciasCaptura.setAvanzado(pBC.getCapAdvance());
			if (pBC.getCapAdvanceId() == true) {
				PreferenciasCaptura.setAvanzado(true);
			} else {
				PreferenciasCaptura.setAvanzado(false);
			}
			//}
			PreferenciasCaptura.setProximoEspacioTipo(pBC.getFilSpaceType());
			PreferenciasCaptura.setProximoEspacio(pBC.getFilSpaceId());
			PreferenciasCaptura.setProximoEspacio(pBC.getFilSpace());
			PreferenciasCaptura.setProximoTiempoTipo(pBC.getFilTimeType());
			PreferenciasCaptura.setProximoTiempo(pBC.getFilTimeId());
			PreferenciasCaptura.setProximoTiempo(pBC.getFilTime());
			PreferenciasCaptura.setPila(pBC.getFilRingBufferId());
			PreferenciasCaptura.setPila(pBC.getFilRingBuffer());
			PreferenciasCaptura.setStop(pBC.getFilStopAfterId());
			PreferenciasCaptura.setStop(pBC.getFilStopAfter());
			//Ponerlo despues para que deshabilite las subopciones que no esten
			// clicadas

			PreferenciasCaptura.setDespuesPaquetes(pBC.getstpAfterPacketsId());
			PreferenciasCaptura.setDespuesPaquetes(pBC.getstpAfterPackets());
			//Falta poner la medida de espacion (kb, mg, ..)
			// setDespuesEspacioTipo
			PreferenciasCaptura.setDespuesEspacio(pBC.getstpAfterSpaceId());
			PreferenciasCaptura.setDespuesEspacio(pBC.getstpAfterSpace());
			PreferenciasCaptura.setDespuesEspacioTipo(pBC
					.getstpAfterSpaceType());
			//Falta poner la medida de tiempo (sg, min, ..)
			PreferenciasCaptura.setDespuesTiempo(pBC.getstpAfterTimeId());
			PreferenciasCaptura.setDespuesTiempo(pBC.getstpAfterTime());
			PreferenciasCaptura.setDespuesTiempoTipo(pBC.getstpAfterTimeType());

			PreferenciasCaptura
					.setMultiplesFicheros(pBC.getFilMultipleFileId());
		} catch (NullPointerException e) {
			System.out
					.println("Error lectura preferencias. Null Pointer Exception");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Carga en la ventana de propiedades de captura desde fichero
	 *  los datos establecidos en el programa
	 */
	public void PrefCapLlenarDatosFromFile() {
		try {
			preferencesBeanFromFile pBFF = FachadaDominio.getPrefBeanFromFile();

			PreferenciasFromFile.setFromFile(pBFF.getffCapSource());
			PreferenciasFromFile.setFichero(pBFF.getffFilLocate());
			//PreferenciasCaptura.setFichero(pBC.getFilLocate());
			//PreferenciasFromFile.setPromiscuo(pBC.getCapPromiscuousMode());
			//if (pBC.getCapNormal() == true){
			PreferenciasFromFile.setHost(pBFF.getffCapHost());
			PreferenciasFromFile.setProtocolo(pBFF.getffCapProtocol());
			PreferenciasFromFile.setPuerto(pBFF.getffCapPort());
			//}
			//else {
			PreferenciasFromFile.setAvanzado(pBFF.getffCapAdvance());
			if (pBFF.getffCapAdvanceId() == true) {
				PreferenciasFromFile.setAvanzado(true);
			} else {
				PreferenciasFromFile.setAvanzado(false);
			}
			//}
			PreferenciasFromFile.setProximoEspacioTipo(pBFF.getffFilSpaceType());
			PreferenciasFromFile.setProximoEspacio(pBFF.getffFilSpaceId());
			PreferenciasFromFile.setProximoEspacio(pBFF.getffFilSpace());
//			PreferenciasFromFile.setProximoTiempoTipo(pBC.getffFilTimeType());
//			PreferenciasFromFile.setProximoTiempo(pBC.getffFilTimeId());
//			PreferenciasFromFile.setProximoTiempo(pBC.getffFilTime());
			PreferenciasFromFile.setPila(pBFF.getffFilRingBufferId());
			PreferenciasFromFile.setPila(pBFF.getffFilRingBuffer());
			PreferenciasFromFile.setStop(pBFF.getffFilStopAfterId());
			PreferenciasFromFile.setStop(pBFF.getffFilStopAfter());
			//Ponerlo despues para que deshabilite las subopciones que no esten
			// clicadas

			PreferenciasFromFile.setDespuesPaquetes(pBFF.getffStpAfterPacketsId());
			PreferenciasFromFile.setDespuesPaquetes(pBFF.getffStpAfterPackets());
			//Falta poner la medida de espacion (kb, mg, ..)
			// setDespuesEspacioTipo
			//PreferenciasFromFile.setDespuesEspacio(pBC.getffStpAfterSpaceId());
			//PreferenciasFromFile.setDespuesEspacio(pBC.getffStpAfterSpace());
			//PreferenciasFromFile.setDespuesEspacioTipo(pBC.getffStpAfterSpaceType());
			//Falta poner la medida de tiempo (sg, min, ..)
			//PreferenciasFromFile.setDespuesTiempo(pBC.getstpAfterTimeId());
			//PreferenciasFromFile.setDespuesTiempo(pBC.getstpAfterTime());
			//PreferenciasFromFile.setDespuesTiempoTipo(pBC.getstpAfterTimeType());

			PreferenciasFromFile.setMultiplesFicheros(pBFF.getffFilMultipleFileId());
		} catch (NullPointerException e) {
			System.out
					.println("Error lectura preferencias. Null Pointer Exception");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Carga en la ventana de propiedades de captura los dispositivos disponibles
	 * en el sistema actual.
	 */
	public void PrefCapLlenarDispositivos() {
		PreferenciasCaptura.addDispositivo(FachadaDominio.getCapDispositivosPcapLibDes(),
				FachadaDominio.getCapDispositivosPcapLibDes());
	}
	
	/**
	 * Carga en la ventana  de insercion definida por los usuarios los dispositivos disponibles
	 * en el sistema actual.
	 */
	public void PrefCapLlenarDispositivosInsercion() {
		PreferenciasInsercion.addDispositivo(FachadaDominio.getCapDispositivosPcapLibDes(),
				FachadaDominio.getCapDispositivosPcapLibDes());
	}
	
	/**
	 * Carga en la ventana de insercion de paquetes capturados los dispositivos disponibles
	 * en el sistema actual.
	 */
	public void PrefCapLlenarDispositivosInsercionCap() {
		PreferenciasInsercionCapturados.addDispositivo(FachadaDominio.getCapDispositivosPcapLibDes(),
				FachadaDominio.getCapDispositivosPcapLibDes());
	}

	/**
	 * Carga en la ventana de propiedades de exportación los datos establecidos en el programa
	 */
	public void PrefExpLlenarDatos() {
		try {
			preferencesBeanExport pBE = FachadaDominio.getPrefBeanExport();

			PreferenciasExportacion.setOrigen(pBE.getExpSource());
			PreferenciasExportacion.setDestino(pBE.getExpDestination());
			PreferenciasExportacion.setMultiFile(pBE.getExpMultifile());
		} catch (NullPointerException e) {
			System.out
					.println("Error lectura preferencias. Null Pointer Exception");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Carga en la ventana de configuración de captura los datos establecidos en el programa
	 */
	public void PrefConfLlenarDatos() {
		String aux;
		try {
			PreferenciasConfiguracion.setCapturas(
					FachadaFicheroDirectorios.getdirectorio("DIR_CAPTURAS"));
			PreferenciasConfiguracion.setExportaciones(
					FachadaFicheroDirectorios.getdirectorio("DIR_EXPORTACIONES"));
			PreferenciasConfiguracion.setParametrizacion(
					FachadaFicheroDirectorios.getdirectorio("DIR_PARAMETRIZACION"));
			PreferenciasConfiguracion.setScripts(
					FachadaFicheroDirectorios.getdirectorio("DIR_SCRIPTS"));

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Carga en la ventana de propiedades de exportación los datos establecidos en el programa
	 */
	public void PrefDefLlenarDatos(String ruta) {
		try {
			preferencesBeanDefinicion pBD = FachadaDominio.getPrefBeanDefinicion();

			PreferenciasDefinicion.setRFCProtocolo(pBD.getRFCProtocolo());
			PreferenciasDefinicion.setNombreProtocolo(pBD.getNomProtocolo());
			PreferenciasDefinicion.setCamposClaveProtocolo(pBD.getCamposClave());
			PreferenciasDefinicion.setNivelProtocolo(pBD.getNivelProtocolo());
			PreferenciasDefinicion.setCamposProtocolo(pBD.getNumCampos());
			PreferenciasDefinicion.setFicheroCapturas(ruta);
			//Relleno la tabla
			PreferenciasDefinicion.setTablaCampos(pBD.getTabla());
			PreferenciasDefinicion.refrescar();
			
		} catch (NullPointerException e) {
			System.out
					.println("Error lectura preferencias. Null Pointer Exception");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Carga en la ventana de propiedades de insercion los datos establecidos en las exportaciones
	 */
	public void PrefDefLlenarDatosInsercion(String ruta) {
		try {
			preferencesBeanDefinicion pBDefIns = FachadaDominio.getPrefBeanDefinicion();

			PreferenciasInsercion.setRFCProtocolo(pBDefIns.getRFCProtocolo());
			PreferenciasInsercion.setNombreProtocolo(pBDefIns.getNomProtocolo());
			PreferenciasInsercion.setNivelProtocolo(String.valueOf(pBDefIns.getNivelProtocolo()));
			PreferenciasInsercion.setFicheroDefinicion(ruta);
			//Relleno la tabla
			PreferenciasInsercion.setTablaCampos(pBDefIns.getTabla());
			//PreferenciasInsercion.refrescar();
			
		} catch (NullPointerException e) {
			System.out
					.println("Error lectura preferencias. Null Pointer Exception");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Carga en el programa las propiedades establecidas de captura.
	 */
	public void PrefCapCogerDatos() {
		preferencesBeanCapture pBC = FachadaDominio.getPrefBeanCaptura();

		pBC.setFilLocate(PreferenciasCaptura.getFichero());
		pBC.setCapDispositive(PreferenciasCaptura.getDispositivo());
		pBC.setCapPromiscuousMode(PreferenciasCaptura.getPromiscuo());
		pBC.setCapNormal(true);
		pBC.setCapHost(PreferenciasCaptura.getHost());
		pBC.setCapProtocol(PreferenciasCaptura.getProtocolo());
		pBC.setCapPort(PreferenciasCaptura.getPuerto());
		pBC.setCapAdvanceId(PreferenciasCaptura.getAvanzado());
		pBC.setCapAdvance(PreferenciasCaptura.getAvanzadoDato());

		pBC.setFilSpaceId(PreferenciasCaptura.getProximoEspacio());
		pBC.setFilSpaceType(PreferenciasCaptura.getProximoEspacioTipo());
		pBC.setFilSpace(PreferenciasCaptura.getProximoEspacioDato());

		pBC.setFilTimeId(PreferenciasCaptura.getProximoTiempo());
		pBC.setFilTimeType(PreferenciasCaptura.getProximoTiempoTipo());
		pBC.setFilTime(PreferenciasCaptura.getProximoTiempoDato());

		pBC.setFilRingBufferId(PreferenciasCaptura.getPila());
		//pBC.setFilRingBufferType();
		pBC.setFilRingBuffer(PreferenciasCaptura.getPilaDato());

		pBC.setFilStopAfterId(PreferenciasCaptura.getStop());
		pBC.setFilStopAfter(PreferenciasCaptura.getStopDato());

		pBC.setstpAfterPacketsId(PreferenciasCaptura.getDespuesPaquetes());
		pBC.setstpAfterPackets(PreferenciasCaptura.getDespuesPaquetesDato());
		pBC.setstpAfterSpaceId(PreferenciasCaptura.getDespuesEspacio());
		//pBC.setstpAfterSpaceType();
		pBC.setstpAfterSpaceType(PreferenciasCaptura.getDespuesEspacioTipo());
		pBC.setstpAfterSpace(PreferenciasCaptura.getDespuesEspacioDato());
		pBC.setstpAfterTimeId(PreferenciasCaptura.getDespuesTiempo());
		//pBC.setstpAfterTimeType();
		pBC.setstpAfterTimeType(PreferenciasCaptura.getDespuesTiempoTipo());
		pBC.setstpAfterTime(PreferenciasCaptura.getDespuesTiempoDato());

		pBC.setFilMultipleFileId(PreferenciasCaptura.getMultiplesFicheros());
	}
	
	/**
	 * Carga en el programa las propiedades establecidas de captura desde fichero.
	 */
	public void PrefCapCogerDatosFromFile() {
		preferencesBeanFromFile pBFF = FachadaDominio.getPrefBeanFromFile();
		
		pBFF.setffCapSource(PreferenciasFromFile.getFromFile());
		pBFF.setffFilLocate(PreferenciasFromFile.getFichero());
		//pBC.setCapDispositive(PreferenciasCaptura.getDispositivo());
		//pBC.setCapPromiscuousMode(PreferenciasCaptura.getPromiscuo());
		pBFF.setffCapNormal(true);
		pBFF.setffCapHost(PreferenciasFromFile.getHost());
		pBFF.setffCapProtocol(PreferenciasFromFile.getProtocolo());
		pBFF.setffCapPort(PreferenciasFromFile.getPuerto());
		pBFF.setffCapAdvanceId(PreferenciasFromFile.getAvanzado());
		pBFF.setffCapAdvance(PreferenciasFromFile.getAvanzadoDato());

		pBFF.setffFilSpaceId(PreferenciasFromFile.getProximoEspacio());
		pBFF.setffFilSpaceType(PreferenciasFromFile.getProximoEspacioTipo());
		pBFF.setffFilSpace(PreferenciasFromFile.getProximoEspacioDato());

		//pBC.setffFilTimeId(PreferenciasCaptura.getProximoTiempo());
		//pBC.setffFilTimeType(PreferenciasCaptura.getProximoTiempoTipo());
		//pBC.setffFilTime(PreferenciasCaptura.getProximoTiempoDato());

		pBFF.setffFilRingBufferId(PreferenciasFromFile.getPila());
		//pBC.setFilRingBufferType();
		pBFF.setffFilRingBuffer(PreferenciasFromFile.getPilaDato());

		pBFF.setffFilStopAfterId(PreferenciasFromFile.getStop());
		pBFF.setffFilStopAfter(PreferenciasFromFile.getStopDato());

		pBFF.setffStpAfterPacketsId(PreferenciasFromFile.getDespuesPaquetes());
		pBFF.setffStpAfterPackets(PreferenciasFromFile.getDespuesPaquetesDato());
		//pBC.setstpAfterSpaceId(PreferenciasCaptura.getDespuesEspacio());
		//pBC.setstpAfterSpaceType();
		//pBC.setstpAfterSpaceType(PreferenciasCaptura.getDespuesEspacioTipo());
		//pBC.setstpAfterSpace(PreferenciasCaptura.getDespuesEspacioDato());
		//pBC.setstpAfterTimeId(PreferenciasCaptura.getDespuesTiempo());
		//pBC.setstpAfterTimeType();
		//pBC.setstpAfterTimeType(PreferenciasCaptura.getDespuesTiempoTipo());
		//pBC.setstpAfterTime(PreferenciasCaptura.getDespuesTiempoDato());

		pBFF.setffFilMultipleFileId(PreferenciasFromFile.getMultiplesFicheros());
	}

	/**
	 * Carga en el programa las propiedades establecidas de exportación.
	 */
	public void PrefExpCogerDatos() {
		preferencesBeanExport pBE = FachadaDominio.getPrefBeanExport();
		
		pBE.setExpSource(PreferenciasExportacion.getOrigen());
		pBE.setExpDestination(PreferenciasExportacion.getDestino());
		pBE.setExpMultifile(PreferenciasExportacion.getMultiFile());
		pBE.setExpStatistics(false);
		pBE.setExpType("XML");
	}
	
	/**
	 * Carga en el programa las propiedades establecidas de configuración.
	 */
	public void PrefConfCogerDatos() {
		FachadaFicheroDirectorios.setdirectorio("DIR_CAPTURAS",
				PreferenciasConfiguracion.getCapturas());
		FachadaFicheroDirectorios.setdirectorio("DIR_EXPORTACIONES",
				PreferenciasConfiguracion.getExportaciones());
		FachadaFicheroDirectorios.setdirectorio("DIR_PARAMETRIZACION",
				PreferenciasConfiguracion.getParametrizacion());
		FachadaFicheroDirectorios.setdirectorio("DIR_SCRIPTS",
				PreferenciasConfiguracion.getScripts());

	}
	
	/**
	 * Guarda las propiedades de captura a fichero.
	 * Hay que determinar que es lo que se guarda (estate)
	 * 
	 * @param estate lo que guarda
	 * @param ruta fichero
	 */
	public void PrefCapGuardarXML(int estate, String ruta) {
		PrefCapCogerDatos();
		FachadaDominio.savePreferences(estate, ruta);
	}
	
	/**
	 * Guarda las propiedades de Exportación a fichero.
	 * Hay que determinar que es lo que se guarda (estate)
	 * 
	 * @param estate lo que guarda
	 * @param ruta fichero
	 */
	public void PrefCapGuardarXMLFromFile(int estate, String ruta) {
		PrefCapCogerDatosFromFile();
		FachadaDominio.savePreferences(estate, ruta);
	}


	/**
	 * lee las propiedades de captura y las carga en el programa.
	 * 
	 * @param ruta fichero
	 */
	public void PrefCapLeerXML(String ruta) {
		//FachadaDominio.getPreferencesCapture(ruta);
		FachadaDominio.setPreferencesCapture(ruta);
		FachadaDominio.getPreferencesCapture(true);
		PrefCapLlenarDatos();

	}
	
	/**
	 * lee las propiedades de exportación y las carga en el programa.
	 * 
	 * @param ruta fichero
	 */
	public void PrefExpLeerXML(String ruta) {
		FachadaDominio.setPreferencesExport(ruta);
		FachadaDominio.getPreferencesExport();
		PrefExpLlenarDatos();

	}

	/**
	 * Lee la definicion  de un protocolo y la carga en el programa.
	 * 
	 * @param ruta fichero
	 */
	public void PrefDefLeerXML(String ruta) {
		FachadaDominio.setPreferencesDefinicion(ruta);
		FachadaDominio.getPreferencesDefinicion();
		PrefDefLlenarDatos(ruta);

	}
	
	/**
	 * Lee la definicion  de un protocolo y la carga en el programa.
	 * 
	 * @param ruta fichero
	 */
	public void PrefDefLeerXMLInsercion(String ruta) {
		//FachadaDominio.setPreferencesExport(ruta);
		//FachadaDominio.getPreferencesExportInsercion();
		FachadaDominio.setPreferencesDefinicion(ruta);
		FachadaDominio.getPreferencesDefinicion();
		PrefDefLlenarDatosInsercion(ruta);

	}
	
	/**
	 * Guarda las propiedades de Exportación a fichero.
	 * Hay que determinar que es lo que se guarda (estate)
	 * 
	 * @param estate lo que guarda
	 * @param ruta fichero
	 */
	public void PrefExpGuardarXML(int estate,String ruta) {
		PrefExpCogerDatos();
		FachadaDominio.savePreferences(2, ruta);
	}
	

	/**
	 * lee las propiedades de captura desde fichero y las carga en el programa.
	 * 
	 * @param ruta fichero
	 */
	public void PrefFFLeerXML(String ruta) {

		FachadaDominio.setPreferencesFromFile(ruta);
		FachadaDominio.getPreferencesFromFile();
		PrefCapLlenarDatosFromFile();

	}
	
	/**
	 * Carga los valores establecidos en la ventana de definicion de protocolos
	 * en el fichero de definicion 
	 */
	public void PrefDefGuardarXML(int estate,String ruta) {
		try {
			preferencesBeanDefinicion pBD = FachadaDominio.getPrefBeanDefinicion();
			//Recojo los datos del formulario
			prefDefCogerDatos(pBD);
			FachadaDominio.savePreferencesDefinicion(estate, ruta,pBD);
			
		} catch (NullPointerException e) {
			System.out
					.println("Error recogida preferencias. Null Pointer Exception");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
    
	/**
	 * Recoje los valores directamente del formulario
	 */
	public void prefDefCogerDatos(preferencesBeanDefinicion aux){
		aux.setNomProtocolo(PreferenciasDefinicion.getNombreProtocolo());
		aux.setNumCampos(PreferenciasDefinicion.getNumCamposProtocolo());
		aux.setRFCProtocolo(PreferenciasDefinicion.getRFCProtocolo());
		aux.setCamposClave(PreferenciasDefinicion.getCamposClave());
		aux.setNivelProtocolo(PreferenciasDefinicion.getNivelProtocolo());
		//no hace nada porque debo pasarle la tabla y que recoja los valores
		aux.setTabla(PreferenciasDefinicion.getTabla());
	}
	/**
	 * Abre un fichero de captura.
	 * 
	 * @param ruta fichero
	 */
	public void AbrirFicheroCaptura(String ruta) {
		//MSniffer = new MenuSniffer(this);
//AKI PONERLO para leer lo capturado
		System.out.println("ARIR  => " + ruta);
		MSniffer.abrirFichero(ruta);
		//MSniffer.abrirFichero("./files/capturas/cap_090119_194640_1.pcap");
		repaintVentana(getVentanaMenuSniffer());
//		AKI PONERLO para leer lo capturado		
		MSniffer.recargarDatos();


	}
	
	/**
	 * Genera el bat correspondiente. Según las propiedades establecidas en el programa.
	 */
	public void GenerarBatCaptura() {
		String sPref, sBat, sMvm, sTipo, sSO;
		String param;
		
		sPref = PreferenciasGenerarBat.getFicheroPref();
		sBat = PreferenciasGenerarBat.getFicheroBat();
		sMvm = PreferenciasGenerarBat.getMvM();
		sTipo= PreferenciasGenerarBat.getTipo();
		sSO = PreferenciasGenerarBat.getSO();
		
		try {
			System.out.println("Pref => " + sPref);
			System.out.println("Bat => " + sBat);
			System.out.println("Tipo => " + sTipo);
			System.out.println("Mvm => " + sMvm);
			System.out.println("SO => " + sSO);
			FachadaDominio.setFileBat(sPref,sBat,sTipo,sMvm,sSO);
			
		} catch (Exception e) {

		}
	}
	/**
	 * GEnera el XML de un pcap o metadatos (conjunto de pcap)
	 * 
	 */
	public void GenerarXMLFromFile() {
		String origen, destino;
		String auxFile, ext;
		boolean mutifile;

		PrefExpCogerDatos();
		
		origen = PreferenciasExportacion.getOrigen();
		destino = PreferenciasExportacion.getDestino();
		mutifile = PreferenciasExportacion.getMultiFile();
		
		//guardarXMLOf fline(origen, destino);		
		FachadaDominio.saveMetaOrPcapToXML(origen,destino,mutifile);
		// esto si parece el bueno revisraloFachadaDominio.saveMetaOrPcapToFile(origen, destino);
	}
	/**
	 * GEnera el XML de un protocolo definido
	 * 
	 */
	public void GenerarXMLProtocolo() {
		String origen, destino;
		String auxFile, ext;
		boolean mutifile;

		PrefExpCogerDatos();
		
		origen = PreferenciasExportacion.getOrigen();
		destino = PreferenciasExportacion.getDestino();
		mutifile = PreferenciasExportacion.getMultiFile();
		
		//guardarXMLOf fline(origen, destino);		
		FachadaDominio.saveMetaOrPcapToXML(origen,destino,mutifile);
		// esto si parece el bueno revisraloFachadaDominio.saveMetaOrPcapToFile(origen, destino);
	}
	
	/**
	 * Genera una instancia de la ventana encargada de generar los script.
	 * 
	 * @see presentacion.preferencias.PreferenciasGenerarBat
	 */
	public void irAGenerarBat() {
		new PreferenciasGenerarBat(this);
	}

	/**
	 * Abrer despues de realizar una captura, mostrandolo en el programa.
	 */
	public void AbrirDespuesCaptura(){
		preferencesBeanMeta pBM;
		preferencesBeanCapture pBC;
		String sNameFile;
		String sPathFile;
		String sNamePathFile;
		
		pBM = FachadaDominio.getPrefBeanMeta();
		pBC = FachadaDominio.getPrefBeanCaptura();
		sNameFile = "";
		sPathFile = "";
		if (pBM.getMetLocRelativeId()== true){
			sNameFile = pBM.getMetLocRelative();
			sPathFile = ""; //AKI poner el pat correcto segund donde fue guardado		
		}
		if (pBM.getMetLocAbsotuteId()== true){
			sNameFile = pBM.getMetLocAbsotuteName();
			sPathFile = pBM.getMetLocAbsotutePath();
		}
		if (pBM.getMetMultipleFileId() == true || (pBC.getstpAfterSpaceId() == true || pBC.getstpAfterTimeId() ==true)) 
			sNamePathFile = sPathFile + System.getProperty("file.separator") + sNameFile + "_" + pBM.getMetMFEnd();
		else 
			sNamePathFile = sPathFile + System.getProperty("file.separator") + sNameFile;
		

		sNamePathFile = sNamePathFile + ".pcap";
//__________________________________-------------------
//----------------------------------___________________		
		//aki para que se abra	
		AbrirFicheroCaptura(sNamePathFile);
//		__________________________________-------------------
//		----------------------------------___________________
		
		if (hilo != null){
			//			System.out.println("...AbrirDespuesCaptura => Hilo a Null");
		}
		else {
			//			System.out.println("....AbrirDespuesCaptura => Hilo NO Null");
		}
		//		System.out.println("Mediador -> AbrirDespuesCaptura => FIN");
		setPanelEstado("Fichero Abierto => " + sNamePathFile);
		//AAKKII
	}
	public void freePantallaCaptura(){
		MSniffer.clearScreen();
		MSniffer.recargarDatos();
		repaintVentana(getVentanaMenuSniffer());
	}
	
	
	/**
	 * Para una captura.
	 */
	public void irFinCapture() {
		if (FachadaDominio.getEstadoCaptura() == true) {
			setPanelEstado("Captura Parada");
			System.out.println("Mediador -> Captura Parada");
			System.out.println("Mediador -> IrFinCapture => INICIO");
			FachadaDominio.stopCaptura();
			System.out.println("Mediador -> IrFinCapture => ABRIRDESPUESCAPTURA");
			AbrirDespuesCaptura();
			System.out.println("Mediador -> IrFinCapture => FIN");
		}
		else{
			System.out.println("IrFinCapture => Ya Parada");
			setPanelEstado("Captura Parada");
		}
		
		//llamar a inicioCApture
	}
	
	/**
	 * Para una Insercion.
	 * @author Carlos Mardones Muga
	 * @version 1.0
 	 * 
	 */
	public void irFinInsert() {
		PreferenciasVisualizarInsercion.detenerInsercion();
		//PreferenciasVisualizarInsercion.finInsercion();
	}
	/**
	 * Para una captura.
	 */
	public void irFinCapturePcapLib() {
		if (FachadaDominio.getEstadoCaptura() == true) {
			setPanelEstado("Parando Captura...");
			//			System.out.println("Mediador -> Captura Parada");
			//			System.out.println("Mediador -> IrFinCapture => INICIO");
			FachadaDominio.stopCapturaPcapLib();
			//			System.out.println("Mediador -> IrFinCapture => ABRIRDESPUESCAPTURA");
			while (FachadaDominio.getEndAll()== false){}
			setPanelEstado("Captura Parada");
			AbrirDespuesCaptura();
			//			System.out.println("Mediador -> IrFinCapture => FIN");
		}
		else {
			//		System.out.println("Mediador -> IrFinCapture => Ya Parada");
			setPanelEstado("Captura Parada");
		}
	}

	/**
	 * Genera una instancia de la ventana encargada de abrir un fichero.
	 * 
	 * @param ventana
	 *            nombre de la ventana desde la que se lo llama
	 * @see VentanaAbrirElegirFichero
	 */
	public void irAAbrirElegirFichero(String ventana) {
		new VentanaAbrirElegirFichero(ventana, this);
	}


	
	/**
	 * Genera una instancia de la ventana encargada de guardar un fichero.
	 * 
	 * @param ventana
	 *            nombre de la ventana desde la que se lo llama
	 * @see VentanaGuardarElegirFichero
	 */
	public void irAGuardarElegirFichero(String ventana) {
		//PreferenciasDefinicion.refrescar();
		new VentanaGuardarElegirFichero(ventana);
	}//irAVentanaGuardarElegirFichero

	/** 
	 * Genera una instancia de la ventana encargada de seleccionar un directorio.
	 *
	 * @param ventana nombre 
	 */
	public void irAVentanaElegirDirectorios(String ventana){
		new VentanaElegirDirectorios(ventana);
	}//irAVentanaGuardarElegirFichero
	
	/**
	 * Genera una instancia de la ventana ayuda.
	 * 
	 * @see FachadaDominio#mostrarAyudaSniffer(String)
	 */
	// OJO ESTA NO LA UTILIZO
	public void irAAyuda() {
		//File index=new
		// File("."+System.getProperty("file.separator")+"AyudaProyecto"+System.getProperty("file.separator")+"index1.htm");
		File index = new File(
				FachadaFicheroDirectorios.getdirectorioData("DIR_HELP") + 
				System.getProperty("file.separator") + 
				"Ayuda-Sniffer.htm");

		FachadaDominio.mostrarAyudaSniffer(index.getAbsolutePath());

	}//irAAyuda

	/**
	 * Boton de pruebas en modo gráfico.
	 */
	// OJO ESTA NO LA UTILIZO
	public void irAPrueba() {
	
		FachadaDominio.saveXMLfromEstate("C:\\Capturas\\captura_090708_180812_state.xml");
		
/*		ComandoAcciones com=new ComandoAcciones();
		com.runScan("E:\\060131 WWW\\WWW Trabajos 06 07\\PFC\\_PFC - Sniffer\\PFC - Sniffer I - Ubuntu\\files\\preferencias\\P_Capture.xml");
		
*/
/*		
		FachadaDominio.getPreferencesFromFile();
		FachadaDominio.saveMetaOrPcapToFile();
		
		this.lanzaraviso("Prueba del modo offline. pcap to pcap\n" +
				"Fichero guardado","Informacion");
		*/
		
		//guardardirectorios();
		//cargardirectorios();	
		
/*
 * 		String origen, origenMETA, destinoPcap1, destinoPcap2, destinoXML;
		String sPath;
		sPath = ".\\files\\Capturas\\";
		origen = FachadaDominio.getPrefBeanFromFile().getffCapSource();
		origenMETA =sPath + "command_cap_090426_173621_META.XML";
		destinoPcap1 = sPath + "Pcap_to_File_cap.pcap";
		destinoPcap2 = sPath + "Default_save_cap1.pcap";
		destinoXML = sPath + "Default_save.xml";
		

		
		
		this.lanzaraviso("->Prueba del modo offline. pcap to pcap\n" +
				"Los ficheros se encuentran en el Path " + sPath + "\n" +
				"Origen => " + origen +  "\n" +
				"Destino=> " + destinoPcap1 ,
				"Informacion");
*/
		//FachadaDominio.setPreferencesCapture(nomDestino);

		
		
//OOJOO FAlta ponerlo bien porque coge los datos del bean en vez de los 
		//datos pasados por parametros
		//FachadaDominio.saveMetaOrPcapToFile(origenMETA, destinoPcap2);

		//guardarXMLOffline(origen, destinoXML);
		//ComandoAcciones com=new ComandoAcciones();
		//com.runScan("E:\\060131 WWW\\WWW Trabajos 06 07\\PFC\\_PFC - Sniffer\\PFC - Sniffer I\\files\\preferencias\\DefaultPreferences.xml");
		
	}//irAAyuda
	
	/**
	 * Boton de pruebas en modo grafico. No visualizado
	 */
	public void irAPrueba2() {
		String ficheroXML;
		String ficheroPcap;
		
		
		ficheroPcap= "E:\\060131 WWW\\WWW Trabajos 06 07\\PFC\\_PFC - Sniffer\\PFC - Sniffer I - Ubuntu\\files\\pruebas\\camm.pcap";
		ficheroPcap= "E:\\060131 WWW\\WWW Trabajos 06 07\\PFC\\_PFC - Sniffer\\PFC - Sniffer I - Ubuntu\\files\\pruebas\\command_cap.pcap";
		
		ficheroXML = "E:\\060131 WWW\\WWW Trabajos 06 07\\PFC\\_PFC - Sniffer\\PFC - Sniffer I - Ubuntu\\files\\pruebas\\salida.xml";
		FachadaDominio.saveXMLOfflinePcapLib(ficheroPcap,ficheroXML);
		
/*
		FachadaDominio.savePreOffline();
		*/
/*
		ficheroXML = "E:\\060131 WWW\\WWW Trabajos 06 07\\PFC\\_PFC - Sniffer\\PFC - Sniffer I\\files\\preferencias\\DefaultPreferencesExport.xml";
		
		System.out.println("--> Run Export=> " + ficheroXML);
		try{
			FachadaDominio.setPreferencesExport(ficheroXML);
			FachadaDominio.getPreferencesExport();
			FachadaDominio.saveXMLOffline(FachadaDominio.getPrefBeanExport());
		}
		catch(Exception e){
			//System.out.println("No se ha podido realizar la exportacion en formato excel.");
		}
*/
	}
	
	/**
	 * Genera una instancia de la ventana ayuda.
	 * Ayuda para generer filtros
	 * 
	 * @see FachadaDominio#mostrarAyudaSniffer(String)
	 */
	public void irAAyudaFilter() {
		File index = new File(
				FachadaFicheroDirectorios.getdirectorioData("DIR_HELP") + 
				System.getProperty("file.separator") + 
				"filtrado.html");
		FachadaDominio.mostrarAyudaSniffer(index.getAbsolutePath());
	}//irAAyudaFilter

	
	/**
	 * Comienza una captura con los parametros inciailizados en el programa
	 * y visualizandolos en una pantalla.
	 */
	public void botonAceptarInicoCaptura() {
		//prueba
		/*		String dispos;
		preferencesBeanCapture pBC = FachadaDominio.getPrefBeanCaptura();
		try {
			PrefCapCogerDatos();
			dispos = pBC.getCapDispositive();
			FachadaDominio.getPcap().pruebaCaptura(dispos);
			//FachadaDominio.getPcap().start();
			
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Error al Capturar");
			new Aviso("Error al Capturar", "Error");
		}
		//FinPrueba
*/
// Prueba Esto es lo weno
		preferencesBeanCapture pBC = FachadaDominio.getPrefBeanCaptura();
		try {
			PrefCapCogerDatos();

			FachadaDominio.openCaptura();
			FachadaDominio.setPrefCaptura();
			FachadaDominio.setListener();
			//especial para visualizar la captura
			//FCaptura = new Fcaptura(this);
			//FCaptura.show();
			FachadaDominio.startCaptura();
			setPanelEstado(String.valueOf("Captura inicializada"));
			//hilo = new Thread(this);
			//if (pBC.getFilMultipleFileId()==true || pBC.getstpAfterTimeId() == true || pBC.getstpAfterSpaceId() == true){
				hilo = new ControlHilos(Thread.activeCount(), this);
				hilo.start();
			//}
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Error al Capturar");
			new Aviso("Error al Capturar", "Error");
		}
		
	}//botonAceptarInicoCaptura
	
	/**
	 * Guarda los datos de seleccionados de la Captura de preferencias en el
	 * beans de captura.
	 * 
	 */
	public void botonAceptarConfiguracion() {
		PrefConfCogerDatos();
		guardardirectorios();
	}
	/**
	 * Guarda los datos de seleccionados de la Captura de preferencias en el
	 * beans de captura.
	 * 
	 */
	public boolean botonAceptarInicoCapturaDumper() {
		preferencesBeanCapture pBC = FachadaDominio.getPrefBeanCaptura();
		try {
			PrefCapCogerDatos();
			String aux;
			aux=FachadaDominio.getPrefBeanCaptura().getCapDispositive();
			if (!FachadaDominio.getPrefBeanCaptura().getCapDispositive().equals("\n Seleccione un dispositivo")){
				FachadaDominio.openCapturaPcapLib();
				FachadaDominio.setPrefCapturaDumper();
				FachadaDominio.setListenerPcapLib();
				FachadaDominio.setControlPacket(true);
				FCaptura = new Fcaptura(this,FachadaDominio.getCountPacketHandler());
				FCaptura.show();
				FCaptura.repaint();
				FachadaDominio.startCapturaPcapLib();
				setPanelEstado(String.valueOf("Captura inicializada"));
				//hilo = new Thread(this);
				if (pBC.getFilMultipleFileId()==true || pBC.getstpAfterTimeId() == true || pBC.getstpAfterSpaceId() == true || pBC.getstpAfterPacketsId() == true){
					hilo = new ControlHilos(Thread.activeCount(), this);
					hilo.start();
				}
				return true;
			}
			else{
				lanzaraviso("Dispositivo no selleccionado", "Informacion");
				return false;
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Error al Capturar");
			new Aviso("Error al Capturar", "Error");
			return false;
		}
		
	}//botonAceptarInicoCaptura
	
	/**
	 * Guarda los datos de seleccionados de la Captura de preferencias en el
	 * beans de captura.
	 * 
	 */
	public void botonAceptarInicoCapturaDumperFromFile() {
		try {
			PrefCapCogerDatosFromFile();
			
			FachadaDominio.saveMetaOrPcapToFile();
			
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Error al Capturar");
			new Aviso("Error al Capturar", "Error");
		}
		
	}//botonAceptarInicoCaptura

	/**
	 * ventana de visualizacion de captura.
	 * @return visualizar captura
	 */
	public Fcaptura getFCaptura() {
		return new Fcaptura(FachadaDominio.getCountPacketHandler());
	}
/*
	public void guardarXMLOffline(String fRead, String fXml) {
		int val;
		//lanzaraviso("Opcin an no puesta pero si implementada, Da un pequeo errro que estamos depurando", "Depurando");
		val = FachadaDominio.saveXMLOffline(fRead, fXml);
		if (val == 1) lanzaraviso("Fichero XML guardado correctamente", "Terminado");
		else lanzaraviso("Fichero XML guardado correctamente", "Atencion");
	} // Abrir file
	*/
	/**
	 * Guarda la captura en formato XML.
	 * 
	 * @param fAux fichero
	 */
	public void guardarXML(File fAux) {
		int val;
		//lanzaraviso("Opcin an no puesta pero si implementada, Da un pequeo errro que estamos depurando", "Depurando");
		val = FachadaDominio.saveXML(fAux);
		if (val == 1) lanzaraviso("Fichero XML guardado correctamente", "Terminado");
		else lanzaraviso("Fichero XML guardado correctamente", "Atencion");

	}//irAVentanaSalir
	
	/**
	 * carga las rutas donde se guardan los ficheros.
	 */
	public void cargardirectorios(){
		String nomdir, dir;
		FabricaAccesoDatosIF fabrica=new FabricaAccesoDatos();
		String auxDirectorios = FachadaFicheroDirectorios.getdirectorioData("DIR_PROPERTIES") + 
			System.getProperty("file.separator") + "userfiles.property";
        FachadaFichero f=fabrica.creaFachadaFichero("directorios", auxDirectorios);
        Object[]directoriosnom=f.leer();
	}//cargardirectorios
	
	/**
	 * graba las rutas donde se guardan los ficheros.
	 */
	public void guardardirectorios(){
		FabricaAccesoDatosIF fabrica=new FabricaAccesoDatos();
		String auxDirectorios = FachadaFicheroDirectorios.getdirectorioData("DIR_PROPERTIES") + 
			System.getProperty("file.separator") + "userfiles.property";
        FachadaFichero f=fabrica.creaFachadaFichero("directorios", auxDirectorios);
        f.escribir("");
		System.out.println("Preferencias de usuario grabadas correctamente");
	}//cargardirectorios
	
	//
	//...........---OJO pa riba-------.........
	/**
	 * muestra
	 */
	public void irAFuenteDatos() {
		//Pone en la barra de estado de la aplicacion lo que en esta momento se
		// est realizando
		setPanelEstado("Seleccionando ficheros de datos...");
		//		new VentanaFuenteDatos();
	}//irAFuenteDatos

	/**
	 * Genera una instancia de la ventana salir.
	 * 
	 * @see VentanaSalir
	 */
	public void irAVentanaSalir() {
		new VentanaSalir(this);
	}//irAVentanaSalir

	/**
	 * Genera una instancia de la ventana de configuracin de directorios.
	 * 
	 * @see PreferenciasConfiguracion
	 */
	public void irAConfigDirectorios() {
		setPanelEstado("Configuracion de Sniffer I");
		cargardirectorios();
		PreferenciasConfiguracion prefConf = new PreferenciasConfiguracion(this);
		PrefConfLlenarDatos();
		//prefConf.setVisible(true);
		prefConf.setVisible(true);
	}//irAConfigDirectorios

	/**
	 * Genera una nueva ventana de ayuda que nos informa sobre el programa y la
	 * memoria utilizada.
	 * 
	 * @see VentanaAcercaDe
	 */
	public void irAAyudaAcercaDe() {
		new VentanaAcercaDe();
	}//irAAyudaAcercaDe

	/**
	 * Actualiza el contenido de la barra de estado de MenuSniffer.
	 * 
	 * @param estado
	 *            descripcion del estado en el que se encuentra la aplicacin
	 * @see MenuSniffer#setStatusPanel(String)
	 */
	public void setPanelEstado(String estado) {
		//desactivado hasta que se alimente correctamente con el estado
		//MenuSniffer.setStatusPanel(estado);
	}//setPanelEstado

	/**
	 * Habilita un elemento de la barra de herramientas de la ventana de Menu
	 * Sniffer.
	 * 
	 * @param posicion
	 *            posicin del elemento de la barra de herramientas que se
	 *            quiere habilitar.Entero del 0 al nmero de elementos.
	 * @see BarraHerramientas#habilitarComponenteBarraHerramientas(int)
	 */
	public void habiliatarBHelemento(int posicion) {
		BarraHerramientas.habilitarComponenteBarraHerramientas(posicion);
	}//habilitarBHelemento

	/**
	 * Deshabilita un elemento de la barra de herramientas de la ventana de Menu
	 * Sniffer.
	 * 
	 * @param posicion
	 *            posicin del elemento de la barra de herramientas que se
	 *            quiere deshabilitar.Entero del 0 al nmero de elementos.
	 * @see BarraHerramientas#deshabilitarComponenteBarraHerramientas(int)
	 */
	public void deshabilitarBHelemento(int posicion) {
		BarraHerramientas.deshabilitarComponenteBarraHerramientas(posicion);
	}//deshabilitaBHelemento



	/**
	 * Deshabilita un men de la barra de mens indicando su poscin.
	 * 
	 * @param posicion
	 *            nmero del 0 al nmero de elementos -1 que contiene la barra
	 *            de mens. Se deshabilitar el men que se encuentre en esta
	 *            posicin.
	 * @see BarraMenu#deshabilitarMenuBarraMenus(int)
	 */
	public void deshabilitarMenuBarraMenus(int posicion) {
		BarraMenu.deshabilitarMenuBarraMenus(posicion);
	}//deshabilitarMenuBarraMenus

	/**
	 * Habilita un men de la barra de mens indicando su poscin.
	 * 
	 * @param posicion
	 *            nmero del 0 al nmero de elementos -1 que contiene la barra
	 *            de mens. Se habilitar el men que se encuentre en esta
	 *            posicin.
	 * @see BarraMenu#habilitarMenuBarraMenus(int)
	 */
	public void habilitarMenuBarraMenus(int posicion) {
		BarraMenu.habilitarMenuBarraMenus(posicion);
	}//habilitarMenuBarraMenus

	/**
	 * Deshabilita un elemento de un men de la barra de mens indicando la
	 * posicin del men y del elemento del men.
	 * 
	 * @param posicion
	 *            nmero del 0 al nmero de elementos -1 que contiene la barra
	 *            de mens. Se deshabilitar el elemento indicado que se
	 *            encuentre en esta posicin.
	 * @param positem
	 *            nmero del 0 al numero de elementos que contiene el men
	 *            inciado. Se deshabilitar el elemento que se encuentre en esta
	 *            posicion del men indicado.
	 * @see BarraMenu#deshabilitarComponenteBarraMenus(int,int)
	 */
	public void deshabilitarComponenteBarraMenus(int posicion, int positem) {
		BarraMenu.deshabilitarComponenteBarraMenus(posicion, positem);
	}//deshabilitarComponenteBarraMenus

	/**
	 * Habilita un elemento de un men de la barra de mens indicando la
	 * posicin del men y del elemento del men.
	 * 
	 * @param posicion
	 *            nmero del 0 al nmero de elementos -1 que contiene la barra
	 *            de mens. Se habilitar el elemento indicado que se encuentre
	 *            en esta posicin.
	 * @param positem
	 *            nmero del 0 al numero de elementos que contiene el men
	 *            inciado. Se habilitar el elemento que se encuentre en esta
	 *            posicion del men indicado.
	 * @see BarraMenu#habilitarComponenteBarraMenus(int,int)
	 */
	public void habilitarComponenteBarraMenus(int posicion, int positem) {
		BarraMenu.habilitarComponenteBarraMenus(posicion, positem);
	}//habilitarComponenteBarraMenus
	
	/**
	 * Deshabilita un elemento de un men de la barra de mens indicando la
	 * posicin del men y del elemento del men.
	 * 
	 * @param posicion
	 *            nmero del 0 al nmero de elementos -1 que contiene la barra
	 *            de mens. Se deshabilitar el elemento indicado que se
	 *            encuentre en esta posicin.
	 * @param positem
	 *            nmero del 0 al numero de elementos que contiene el men
	 *            inciado. Se deshabilitar el elemento que se encuentre en esta
	 *            posicion del men indicado.
	 * @see BarraMenu#deshabilitarComponenteBarraMenus(int,int)
	 */
	public void EnabledComponenteBarraMenus(int posicion, int positem, int pos, boolean bEstado) {
		BarraMenu.EnabledComponenteBarraMenus(posicion, positem, pos, bEstado);
	}//deshabilitarComponenteBarraMenus

	/**
	 * Lanza una ventana de aviso con el mensaje indicado.
	 * 
	 * @param mensaje
	 *            mensaje a mostrar en la ventana de aviso.
	 * @param tipo
	 *            tipo de mensaje que se desea generar.Valores que puede tomar:
	 *            <code>"Error","Advertencia","Informacion"</code>
	 * @see Aviso
	 */
	public void lanzaraviso(String mensaje, String tipo) {
		new Aviso(mensaje, tipo);
	}//lanzaraviso

	/**
	 * Genera una instancia de la ventana encargada de abrir un fichero.
	 * 
	 * @param ventana
	 *            nombre de la ventana desde la que se llama a
	 *            AbrirElegirFichero.
	 * @see VentanaAbrirElegirFichero
	 */
	public void irAVentanaAbrirElegirFichero(String ventana) {
		new VentanaAbrirElegirFichero(ventana, this);
	}//irAVentanaAbrirElegirFichero

	//-----------------------MTODOS PARA FUENTE DE
	// DATOS----------------------------------
	
	//---------------METODOS PARA LA DEFINICION E INSERCION DE
	//-----------------------------PROTOCOLOS
	
	/**
	 * Genera una instancia de la ventana inicio de definicion.
	 * 
	 * @param tipo estado
	 */
	public void irInicioDefinicion(boolean tipo) {
		setPanelEstado("Definicion de Protocolos");
		PreferenciasDefinicion prefDef = new PreferenciasDefinicion();
		//PrefCapLlenarDispositivos();
		//PrefCapLlenarDatos();
		prefDef.setVisible(true);
	}
	
	//---------------METODOS PARA CHEQUEAR LOS PROTOCOLOS
	//-----------------------------PROTOCOLOS
	
	/**
	 * Chequea los valores de definicion de protocolos para que cumplan 
	 * con los valores especificados por el usuario
	 */
	public void ChequearDefinicionProtocolo(){
		FachadaDominio.chequearDefinicionProtocolo();
	}
		
	/**
	 * Comprueba que se leen correctamente las claves de cada protocolo
	 */
	public void ChequearIdentificacionProtocolo(){
		Object[][] arr;
		Object aux = null;
		int j;
		//reordena las filas 
		arr=PreferenciasDefinicion.getTabla();
		for(int i=0;i<(arr.length)-1;i++){
			j=i+1;
			if(Integer.valueOf(String.valueOf(arr[i][0]))>Integer.valueOf(String.valueOf(arr[j][0]))){
				for(int k=0;k<PreferenciasDefinicion.getColumnas();k++){
					aux=arr[i][k];
					arr[i][k]=arr[j][k];
					arr[j][k]=aux;
				}
				i=0;
				j=0;
			}
		}
		PreferenciasDefinicion.setTablaCampos(arr);
	}

	/**
	 * Establece la ruta del fichero de capturas para la insercion.
	 * @param fRuta
	 */
	public void PrefInserCapRuta(String fRuta) {
		PreferenciasInsercionCapturados.setFicheroCapturas(fRuta);
		
	}
	
	/**
	 * Establece la ruta del fichero de definicio para la insercion de definidos.
	 * @param fRuta
	 */
	public void PrefInserDefinidosRuta(String fRuta) {
		PreferenciasInsercionCapturados.setFicheroCapturas(fRuta);
		
	}
	
	/**
	 * Establece la ruta del fichero de definicion.
	 * @param fRuta
	 */
	public void PrefDefinicionRuta(String fRuta) {
		PreferenciasDefinicion.setFicheroCapturas(fRuta);
		
	}

	/**
	 * Genera la insercion de paquetes en la red.
	 * @param titulo
	 * @throws IOException 
	 */
	public void irAInsertarPaquetes(String titulo,int tot) {
		PreferenciasVisualizarInsercion prefIns;
		if(titulo.equals("Insertar Paquetes Capturados")){
			//System.out.println("Insertar Paquetes Capturados2");
			if(PreferenciasInsercionCapturados.getDispositivo().equals("Seleccione un dispositivo")  || PreferenciasInsercionCapturados.getRepeticiones() == 0 || PreferenciasInsercionCapturados.getRuta().equals("")){
				lanzaraviso("Datos Insercion Incompletos","Error");
			}else{
				Insercion ins =new Insercion(PreferenciasInsercionCapturados.getDispositivo(),PreferenciasInsercionCapturados.getRuta(),PreferenciasInsercionCapturados.getRepeticiones(),2);
				prefIns = new PreferenciasVisualizarInsercion("Insercion Paquetes Capturados",tot,ins);
				prefIns.iniciarInsercion();
				//PreferenciasVisualizarInsercion.iniciarInsercion(PreferenciasInsercionCapturados.getRepeticiones());
				prefIns.setVisible(true);
			}
		}
		if(titulo.equals("Insertar Paquetes Definidos")){
			//System.out.println("Insertar Paquetes Definidos2");
			Packet p;
			try {
				p = PreferenciasInsercion.crearPaquete();
				if(p!=null && PreferenciasInsercion.getDispositivo()!= ""){
					Insercion ins =new Insercion(PreferenciasInsercion.getDispositivo(),p,1);
					prefIns = new PreferenciasVisualizarInsercion("Insertar Paquetes Definidos",-1,ins);
					prefIns.iniciarInsercion();
					prefIns.setVisible(true);
				}else{
					lanzaraviso("Datos Insercion Incompletos","Error");
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
			
		}
	}

	/**
	 * Lee los paquetes almacenados y recoge sus valores.
	 * @param fRuta
	 */
	public void PrefDefLeerXMLExportaciones(String fRuta) {
		FachadaDominio.setPreferencesExportacion(fRuta);
		FachadaDominio.getPreferencesExportacion();
		PrefInsCapLlenarDatos();
	}

	public void PrefInsCapLlenarDatos() {
		try {
			preferencesBeanExportInsercion pBE = FachadaDominio.getPrefBeanExportInsercion();
			
			PreferenciasInsercion.setIpDestino(pBE.getIpDestino());
			PreferenciasInsercion.setIpOrigen(pBE.getIpOrigen());
			PreferenciasInsercion.setMacDestino(pBE.getMacDestino());
			PreferenciasInsercion.setMacOrigen(pBE.getMacOrigen());
			
		} catch (NullPointerException e) {
			System.out
					.println("Error lectura preferencias. Null Pointer Exception");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 
	 */
		/**
		 *  1 que todos los valores rellenos menos las descripciones y el valor por defecto de los que 
		 *  no son claves 
		 *  2 que el tamaño del campo sea valido
		 *  2.1 hay que saber que segun que tipo de dato el tamaño debe ser de un valor determinado
		 *  3 que el valor por defecto coincida con el tipo de valor que debe coincidir
		 *  
		 */
	
}//Clase Mediador
