package lpe.elecciones.processor;

import java.util.List;

import lpe.elecciones.data.UbicacionGeograficaStore;
import lpe.elecciones.model.Ambito;
import lpe.elecciones.model.UbicacionGeografica;
import lpe.elecciones.parser.UbicacionGeograficaParser;

public class UbicacionGeograficaProcessor {

	private UbicacionGeograficaParser parser = new UbicacionGeograficaParser();

	public void crearArchivoDepartamentos(String fileName) throws Exception {
		System.out.println("Obteniendo departamentos de la web y cargandolos en memoria.");
		
		List <UbicacionGeografica> departamentos = parser.obtenerDepartamentos(Ambito.PERU);
		UbicacionGeograficaStore.agregarDepartamentos(departamentos);

		List <UbicacionGeografica> continentes = parser.obtenerDepartamentos(Ambito.EXTRANJERO);
		UbicacionGeograficaStore.agregarDepartamentos(continentes);

		System.out.println("Creando archivo de departamentos " + fileName + ".");

		for (UbicacionGeografica departamento : UbicacionGeograficaStore.getDepartamentos()) {
			// Grabar a archivo en lugar de imprimir en consola
			// En consola mostrar unicamente progreso del proceso
			System.out.println(departamento.getAmbito().toString().charAt(0) + " " + departamento.getCodigo() + " "
					+ departamento.getNombre());
		}
	}

	public void crearArchivoProvincias(String fileName) throws Exception {
		System.out.println("Creando archivo de provincias " + fileName + " y guardando datos en memoria.");

		for (UbicacionGeografica departamento : UbicacionGeograficaStore.getDepartamentos()) {
			List<UbicacionGeografica> provincias = parser.obtenerProvincias(departamento);
			UbicacionGeograficaStore.agregarProvincias(provincias);

			for (UbicacionGeografica provincia : provincias) {
				// Grabar a archivo en lugar de imprimir en consola
				// En consola mostrar unicamente progreso del proceso
				System.out.println(provincia.getAmbito().toString().charAt(0) + " " + provincia.getCodigo() + " "
						+ provincia.getNombre());
			}
		}
	}

	public void crearArchivoDistritos(String fileName) throws Exception {
		System.out.println("Creando archivo de distritos " + fileName + " y guardando datos en memoria.");

		for (UbicacionGeografica provincia : UbicacionGeograficaStore.getProvincias()) {
			List<UbicacionGeografica> distritos = parser.obtenerDistritos(provincia);
			UbicacionGeograficaStore.agregarDistritos(distritos);

			for (UbicacionGeografica distrito : distritos) {
				// Grabar a archivo en lugar de imprimir en consola
				// En consola mostrar unicamente progreso del proceso
				System.out.println(distrito.getAmbito().toString().charAt(0) + " " + distrito.getCodigo() + " "
						+ distrito.getNombre());
			}
		}
	}
	
	public static void main(String[] args) throws Exception {
		UbicacionGeograficaProcessor p = new UbicacionGeograficaProcessor();
		p.crearArchivoDepartamentos("departamento.txt");
		p.crearArchivoProvincias("provincia.txt");
		p.crearArchivoDistritos("distrito.txt");
	}
}
