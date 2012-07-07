package dominio;

import java.io.*;

/**
 * Controla en modo comando si se pulsa alguna tecla y con la combinacion
 * "e + Intro" sale del programa. Finalizando la acción correctamente
 * 
 * @author Leonardo García & Jose Ramón Gutierrez
 * @version 2.0
 * 
 * @see ComandoAcciones
 */
public class ModoComando extends Thread {
	/** * Comandos en modo comando. */
	private ComandoAcciones CAcciones;
	/** * Buffer para la lectura por teclado. */
	private BufferedReader in=new BufferedReader(new InputStreamReader(System.in));
	/** * Caracter leído. */
	private char c;
	/** * Se esta ejecutando. */
	private boolean running = true;
	/** * Tipo de accion que establecio el modod comando. */
	private String tipo;

	/**
	 * Contrustor de la clae
	 * @param aux
	 * 			ComandoAcciones para podor parar el porgrama correctmente.
	 * @see ComandoAcciones
	 */
	public ModoComando(ComandoAcciones aux) {
		CAcciones = aux;
		setTipo(0);
	}//ModoComando

	/**
	 * Establece el tipo de accion de escucha de teclado
	 * 
	 * @param auxTipo
	 * 			tipo de accion
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
	 * Muestra el menú de ayuda en modo texto
	 */
	private void menuHelp(){
		System.out.println("");
		System.out.println("**********************************");
		System.out.println("	Ayuda  Sniffer I");
		System.out.println("	  Modo Comando	");
		System.out.println("**********************************");
		System.out.println("");
		System.out.println("**********************************");
		System.out.println("	h -> Ayuda");
		System.out.println("	0 -> Salir");
		System.out.println("**********************************");
	}

	/**
	 * Ejecucion del thread que se encarga del control del teclado y 
	 * si termina por la acción del usuario, termina la accion correctamente
	 * 
	 * @see ComandoAcciones
	 */
	public void run() {
		while (running == true){

			try{
				String lee = "";
				String opc = "";
				int intOpt;
				
				intOpt = in.read();
				
				//lee=in.readLine();
		      	//opc=lee;
				//switch(Character.toLowerCase(opc.charAt(0))){
				switch(intOpt){
				case '0':
					System.out.println("   (( Exit command line ))");
					running = false;
					if (tipo=="Scan"){
						CAcciones.endScan(true);
						running=false;
						}
					else if (tipo=="Export"){
						System.out.println("   (( Salida abrupta ))");
						CAcciones.endExport(true);
						running=false;
						}
					else if (tipo=="FromFile"){
						System.out.println("   (( Salida abrupta ))");
						CAcciones.endFromFile(true);
						running=false;
						}	
					else if (tipo=="Estate"){
						System.out.println("   (( Salida abrupta ))");
						CAcciones.endState(true);
						running=false;
						}
					break;
				default:
						menuHelp();
						break;
				}
				//lee = "";

			}
			catch (IOException e){
				System.out.println("Upps un error!!");
			}
			catch (Exception e){
				menuHelp();
			}
		}
	}
	
	/**
	 * Pide por teclado hasta que des intro
	 * 
	 * @return
	 * 			capturado por pantalla
	 */
	public String pedirPorTeclado(){
		String lee = "";
		try{
			lee=in.readLine();
		}
		catch (IOException e){
			System.out.println("Upps un error!!");
		}
		catch (Exception e){
			System.out.println("Otro error");
		}
		return lee;
	}
	
	public void pararLeer(){
		try{
			in.close();
			this.interrupt();
		}
		catch (Exception e){
		}
	}

}//Clase ModoComando
