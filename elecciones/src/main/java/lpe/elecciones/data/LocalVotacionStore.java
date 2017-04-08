package lpe.elecciones.data;

import java.util.ArrayList;
import java.util.List;

import lpe.elecciones.model.LocalVotacion;

public class LocalVotacionStore {

	private static List<LocalVotacion> locales = new ArrayList<LocalVotacion>();

	public static void agregarLocales(List<LocalVotacion> listaLocales) {
		locales.addAll(listaLocales);
	}

	public static List<LocalVotacion> getLocales() {
		return locales;
	}
}
