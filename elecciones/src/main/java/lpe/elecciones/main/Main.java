package lpe.elecciones.main;

import lpe.elecciones.processor.LocalVotacionProcessor;
import lpe.elecciones.processor.UbicacionGeograficaProcessor;

public class Main {

	private static UbicacionGeograficaProcessor ubigeoProcessor = new UbicacionGeograficaProcessor();
	private static LocalVotacionProcessor localProcessor = new LocalVotacionProcessor();

	// Esta clase deberia controlar *todo*
	public static void main(String[] args) throws Exception {
		// Implementar y llamar a metodo: void ubigeoProcessor.cargarDepartamentos();
		// -------------------------------------------------------------------------
		// Si el archivo departamento.txt NO existe
		//     ubigeoProcessor.crearArchivoDepartamentos("departamento.txt");
		// Si el archivo departamento.txt SI existe
		//     ubigeoProcessor.leerArchivoDepartamentos("departamento.txt");
		
		// Implementar y llamar a metodo: void ubigeoProcessor.cargarProvincias();
		
		// Implementar y llamar a metodo: void ubigeoProcessor.cargarDistritos();

		// Implementar y llamar a metodo: void localProcessor.cargarLocales();
	}
}
