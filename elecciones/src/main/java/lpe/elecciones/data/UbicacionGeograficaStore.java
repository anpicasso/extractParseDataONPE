package lpe.elecciones.data;

import java.util.ArrayList;
import java.util.List;

import lpe.elecciones.model.UbicacionGeografica;

public class UbicacionGeograficaStore {

	private static List <UbicacionGeografica> departamentos = new ArrayList <UbicacionGeografica> ();
	private static List <UbicacionGeografica> provincias = new ArrayList <UbicacionGeografica> ();
	private static List <UbicacionGeografica> distritos = new ArrayList <UbicacionGeografica> ();
	
	public static void agregarDepartamentos(List <UbicacionGeografica> listaDepartamentos) {
		departamentos.addAll(listaDepartamentos);
	}
	
	public static void agregarProvincias(List <UbicacionGeografica> listaProvincias) {
		provincias.addAll(listaProvincias);
	}
	
	public static void agregarDistritos(List <UbicacionGeografica> listaDistritos) {
		distritos.addAll(listaDistritos);
	}

	public static List<UbicacionGeografica> getDepartamentos() {
		return departamentos;
	}

	public static List<UbicacionGeografica> getProvincias() {
		return provincias;
	}

	public static List<UbicacionGeografica> getDistritos() {
		return distritos;
	}
}
