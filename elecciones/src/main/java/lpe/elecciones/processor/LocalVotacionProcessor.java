package lpe.elecciones.processor;

import lpe.elecciones.parser.LocalVotacionParser;

public class LocalVotacionProcessor {

	private LocalVotacionParser parser = new LocalVotacionParser();

	public void crearArchivoLocalVotacion(String fileName) throws Exception {
		// recorrer distritos (ubigeo) y obtener locales por distrito
		// cargar locales en memoria y en archivo
		// previamente se debe haber ejecutado UbicacionGeograficaProcessor.main
		// o cargar estos datos desde un archivo 
	}

	public static void main(String[] args) throws Exception {
		LocalVotacionProcessor p = new LocalVotacionProcessor();
		p.crearArchivoLocalVotacion("localVotacion.txt");
	}
}
