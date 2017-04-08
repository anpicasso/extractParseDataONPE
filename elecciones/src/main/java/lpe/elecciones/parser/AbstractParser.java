package lpe.elecciones.parser;

import java.util.ArrayList;
import java.util.List;

public class AbstractParser {

	/**
	 * Parsear HTML y retornar listado (codigo, nombre)
	 * 
	 * (excluir) <option value="-1?-1" selected="selected">--SELECCIONE--</option>
	 * (incluir) <option value="0397?021604">IE JULIO C TELLO</option>
	 */
	protected List<Opcion> parseHtmlOption(String html) {
		List<Opcion> listado = new ArrayList<Opcion>();

		int start = 0;
		while (true) {
			start = html.indexOf("<option value=", start);
			if (start == -1) {
				break;
			}

			start += 15;
			int end = html.indexOf("\"", start);

			String codigo = html.substring(start, end);
			if (html.charAt(end + 1) != '>') {
				continue;
			}

			start = end + 2;
			end = html.indexOf("</", start);
			String nombre = html.substring(start, end);
			listado.add(new Opcion(codigo, nombre));

			start = end;
		}
		return listado;
	}

	/**
	 * Parsear tags HREF de HTML y retornar listado
	 * 
	 * <a href="#">031173</a>
	 */
	protected List <String> parseHtmlHref(String html) {
		List <String> listaHref = new ArrayList <String> ();
		
		int start = 0;
		while(true) {
			start = html.indexOf("<a href=\"#\">", start);
			if(start == -1) {
				break;
			}
			
			start += 12;
			int end = html.indexOf("</", start);
			
			listaHref.add(html.substring(start, end));
			start = end;
		}
		return listaHref;
	}
	
	static class Opcion {
		private String codigo;
		private String nombre;

		public Opcion(String codigo, String nombre) {
			this.codigo = codigo;
			this.nombre = nombre;
		}

		public String getCodigo() {
			return codigo;
		}

		public String getNombre() {
			return nombre;
		}
	}
}
