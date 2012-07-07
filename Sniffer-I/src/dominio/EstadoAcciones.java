package dominio;

import java.io.*;

/**
 * Clase que accede en modo comamod a FachadaDominio para saber cuando los 
 * modos (captura, export, captura desde fichero) han terminado, y sengúnn el tipo
 * lo terminan correctamente llamado a ComandoAcciones
 * 
 * @author Leonardo García & Jose Ramón Gutierrez
 * @version 2.0
 */
public class EstadoAcciones extends Thread {
	/** * padre de la clase */
	private ComandoAcciones CAcciones;
	/** * variable para la captura desde teclado */
	private BufferedReader in=new BufferedReader(new InputStreamReader(System.in));	
	/** * acción de modo comando ejecuntadose */
	private boolean running = true;
	/** * tipo de modo comando */
	private String tipo;
	/** * Parada automatica */
	private boolean paradaAutomatica;
	

	/**
	 * Constructor de la clase.
	 * 
	 * @param aux 
	 * 			donde esta los comandos de las acciones
	 * @see ComandoAcciones
	 */
	public EstadoAcciones(ComandoAcciones aux) {
		CAcciones = aux;
		setTipo(0);
		paradaAutomatica=true;
	}

	/**
	 * Establece el tipo para la comprobación del estado de las acciones.
	 * 
	 * @param auxTipo
	 * 			del 1 al 3 y se corresponde con "Scan", "Export", "FromFile" 
	 * 			y sino lo establece a "Error".
	 */
	public void setTipo(int auxTipo){
		if (auxTipo == 1){
			tipo = "Scan";
		}
		else if (auxTipo == 2){
			tipo = "Export";
		}	
		else if (auxTipo == 3){
			tipo = "FromFile";
		}
		else if (auxTipo == 4){
			tipo = "Estate";
		}
		else{
			tipo = "error";
		}
	}
	
	/**
	 * Devuelve el tipo que se esta comprobando.
	 * 
	 * @return el tipo comprobado
	 */
	public String getTipo(){
		return tipo;
	}

	/**
	 * Ejecución del thread que comprueba si la accion selecciona en modo comando
	 * ha terminado
	 */
	public void run() {
		while (running == true){
			try{
				if (tipo == "Scan") {
					//Ha finalizado la captura por cualquier causa?
					if (FachadaDominio.getEndCapture() == true){
						running = false;
					}
				}
					//Ha finalizado la exportación por cualquier causa?
				if (tipo == "Export") {
					if (FachadaDominio.getRunningExport() == false){
						running = false;
					}
				}
				//Ha finalizado la captura desde ficheros por cualquier causa?				
				if (tipo == "FromFile") {
					if (FachadaDominio.getEndFromFile() == true){
						running = false;
					}
				}
				//Ha finalizado la exportacíon desde estate por cualquier causa?	
				if (tipo == "Estate") {
					if (FachadaDominio.getRunningExport() == false){
						running = false;
					}
				}
				//la comprobacion se hace cad 1 segundo
				EstadoAcciones.sleep(50);
			}
			catch (Exception e){
				System.out.println("Upps 2 error!!");
			}
		}
		//Por cada modo llama a su finalizacion correcta
		if(tipo == "Scan" && paradaAutomatica ==true){
			CAcciones.endScan(false);
			System.out.println("Upor!!");
			//System.exit(1);	
		}
		else if (tipo == "Export" && paradaAutomatica ==true){CAcciones.endExport(false);}
		else if (tipo == "FromFile" && paradaAutomatica ==true) {CAcciones.endFromFile(false);}
		else if (tipo == "Estate" && paradaAutomatica ==true) {CAcciones.endFromFile(false);}
	}
	
	public void setEstado (boolean aux){
		running = false;
		paradaAutomatica = false;
		
	}

}//Clase ModoComando
