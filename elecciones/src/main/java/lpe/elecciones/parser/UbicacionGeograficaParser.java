package lpe.elecciones.parser;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpMethod;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import lpe.elecciones.model.Ambito;
import lpe.elecciones.model.TipoUbicacion;
import lpe.elecciones.model.UbicacionGeografica;

public class UbicacionGeograficaParser extends AbstractParser {

	/**
	 * Parsea un HTML y retorna listado:
	 * 
	 * <option value="010000">AMAZONAS</option>
	 * 
	 */
	private List<UbicacionGeografica> parseHtmlUbigeo(Ambito ambito, TipoUbicacion tipo, String html) {
		List<Opcion> listadoOpt = parseHtmlOption(html);

		List<UbicacionGeografica> listado = new ArrayList<UbicacionGeografica>();

		for (Opcion opt : listadoOpt) {
			listado.add(new UbicacionGeografica(ambito, tipo, opt.getCodigo(), opt.getNombre()));
		}
		return listado;
	}

	// curl 'https://resultadoselecciones2016.onpe.gob.pe/PRPCP2016/ajax.php'
	// -H 'Content-Type: application/x-www-form-urlencoded; charset=UTF-8'
	// -H 'X-Requested-With: XMLHttpRequest'
	// --data
	// 'pid=3214713747288308&_clase=ubigeo&_accion=getDistritos&tipoC=acta&prov_id=021600&tipoElec=&modElec='
	public List<UbicacionGeografica> obtenerDistritos(UbicacionGeografica provincia) throws URISyntaxException {
		RestTemplate template = new RestTemplate();

		URI uri = new URI("https://resultadoselecciones2016.onpe.gob.pe/PRPCP2016/ajax.php");

		String body = "pid=3214713747288308&_clase=ubigeo&_accion=getDistritos&tipoC=acta&tipoElec=&modElec=&prov_id="
				+ provincia.getCodigo();

		MultiValueMap<String, String> headers = new LinkedMultiValueMap<String, String>();
		headers.add("Content-Type", "application/x-www-form-urlencoded; charset=UTF-8");
		headers.add("X-Requested-With", "XMLHttpRequest");

		RequestEntity<String> request = new RequestEntity<String>(body, headers, HttpMethod.POST, uri);

		ResponseEntity<String> response = template.exchange(request, String.class);
		String text = response.getBody();
		List<UbicacionGeografica> distritos = parseHtmlUbigeo(provincia.getAmbito(), TipoUbicacion.DISTRITO, text);
		return distritos;
	}

	// curl 'https://resultadoselecciones2016.onpe.gob.pe/PRPCP2016/ajax.php'
	// -H 'Content-Type: application/x-www-form-urlencoded; charset=UTF-8'
	// -H 'X-Requested-With: XMLHttpRequest'
	// --data
	// 'pid=7258424414635062&_clase=ubigeo&_accion=getProvincias&tipoC=acta&tipoElec=&modElec=&dep_id=020000'

	public List<UbicacionGeografica> obtenerProvincias(UbicacionGeografica departamento) throws URISyntaxException {
		RestTemplate template = new RestTemplate();

		URI uri = new URI("https://resultadoselecciones2016.onpe.gob.pe/PRPCP2016/ajax.php");

		String body = "pid=7258424414635062&_clase=ubigeo&_accion=getProvincias&tipoC=acta&tipoElec=&modElec=&dep_id="
				+ departamento.getCodigo();

		MultiValueMap<String, String> headers = new LinkedMultiValueMap<String, String>();
		headers.add("Content-Type", "application/x-www-form-urlencoded; charset=UTF-8");
		headers.add("X-Requested-With", "XMLHttpRequest");

		RequestEntity<String> request = new RequestEntity<String>(body, headers, HttpMethod.POST, uri);

		ResponseEntity<String> response = template.exchange(request, String.class);
		String text = response.getBody();
		List<UbicacionGeografica> provincias = parseHtmlUbigeo(departamento.getAmbito(), TipoUbicacion.PROVINCIA, text);
		return provincias;
	}

	// curl 'https://resultadoselecciones2016.onpe.gob.pe/PRPCP2016/ajax.php'
	// -H 'Content-Type: application/x-www-form-urlencoded; charset=UTF-8'
	// -H 'X-Requested-With: XMLHttpRequest'
	// --data
	// 'pid=2344185757341507&_clase=ubigeo&_accion=getDepartamentos&dep_id=&tipoElec=&tipoC=acta&modElec=&ambito=P&pantalla='

	public List<UbicacionGeografica> obtenerDepartamentos(Ambito ambito) throws URISyntaxException {

		RestTemplate template = new RestTemplate();

		URI uri = new URI("https://resultadoselecciones2016.onpe.gob.pe/PRPCP2016/ajax.php");

		String body;
		if (Ambito.PERU.equals(ambito)) {
			body = "pid=2344185757341507&_clase=ubigeo&_accion=getDepartamentos&dep_id=&tipoElec=&tipoC=acta&modElec=&ambito=P&pantalla=";
		} else {
			body = "pid=2344185757341507&_clase=ubigeo&_accion=getDepartamentos&dep_id=&tipoElec=&tipoC=acta&modElec=&ambito=E&pantalla=";
		}

		MultiValueMap<String, String> headers = new LinkedMultiValueMap<String, String>();
		headers.add("Content-Type", "application/x-www-form-urlencoded; charset=UTF-8");
		headers.add("X-Requested-With", "XMLHttpRequest");

		RequestEntity<String> request = new RequestEntity<String>(body, headers, HttpMethod.POST, uri);

		ResponseEntity<String> response = template.exchange(request, String.class);
		String text = response.getBody();
		List<UbicacionGeografica> departamentos = parseHtmlUbigeo(ambito, TipoUbicacion.DEPARTAMENTO, text);
		return departamentos;
	}

	public static void main(String[] args) throws URISyntaxException {
		UbicacionGeograficaParser parser = new UbicacionGeograficaParser();

		List<UbicacionGeografica> departamentos = parser.obtenerDepartamentos(Ambito.EXTRANJERO);

		for (UbicacionGeografica departamento : departamentos) {
			System.out.println(departamento);

			List<UbicacionGeografica> provincias = parser.obtenerProvincias(departamento);
			for (UbicacionGeografica provincia : provincias) {
				System.out.println(provincia);

				List<UbicacionGeografica> distritos = parser.obtenerDistritos(provincia);
				for (UbicacionGeografica distrito : distritos) {
					System.out.println(distrito);
				}
			}
		}

		departamentos = parser.obtenerDepartamentos(Ambito.PERU);

		for (UbicacionGeografica departamento : departamentos) {
			System.out.println(departamento);

			List<UbicacionGeografica> provincias = parser.obtenerProvincias(departamento);
			for (UbicacionGeografica provincia : provincias) {
				System.out.println(provincia);

				List<UbicacionGeografica> distritos = parser.obtenerDistritos(provincia);
				for (UbicacionGeografica distrito : distritos) {
					System.out.println(distrito);
				}
			}
		}
	}
}
