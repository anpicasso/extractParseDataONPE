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

import lpe.elecciones.model.LocalVotacion;

public class LocalVotacionParser extends AbstractParser {

	/**
	 * Parsear HTML y retornar listado:
	 * 
	 * <option value="0397?021604">IE JULIO C TELLO</option>
	 */
	private List<LocalVotacion> parseHtmlUbigeo(String ubigeo, String html) {
		List<Opcion> listadoOpt = parseHtmlOption(html);

		List<LocalVotacion> listado = new ArrayList<LocalVotacion>();
		for (Opcion opt : listadoOpt) {
			int p = opt.getCodigo().indexOf('?');
			String codigoLocal = opt.getCodigo().substring(0, p);

			listado.add(new LocalVotacion(ubigeo, codigoLocal, opt.getNombre()));
		}
		return listado;
	}

	// curl 'https://resultadoselecciones2016.onpe.gob.pe/PRPCP2016/ajax.php'
	// -H 'Content-Type: application/x-www-form-urlencoded; charset=UTF-8'
	// -H 'X-Requested-With: XMLHttpRequest'
	// --data
	// 'pid=3752478735718518.5&_clase=actas&_accion=getLocalesVotacion&tipoElec=&ubigeo=021604'

	public List<LocalVotacion> obtenerLocalesVotacion(String codigoDistrito) throws URISyntaxException {
		RestTemplate template = new RestTemplate();

		URI uri = new URI("https://resultadoselecciones2016.onpe.gob.pe/PRPCP2016/ajax.php");

		String body = "pid=3752478735718518.5&_clase=actas&_accion=getLocalesVotacion&tipoElec=&ubigeo="
				+ codigoDistrito;

		MultiValueMap<String, String> headers = new LinkedMultiValueMap<String, String>();
		headers.add("Content-Type", "application/x-www-form-urlencoded; charset=UTF-8");
		headers.add("X-Requested-With", "XMLHttpRequest");

		RequestEntity<String> request = new RequestEntity<String>(body, headers, HttpMethod.POST, uri);

		ResponseEntity<String> response = template.exchange(request, String.class);
		String text = response.getBody();
		List<LocalVotacion> locales = parseHtmlUbigeo(codigoDistrito, text);
		return locales;
	}

	public static void main(String[] args) throws URISyntaxException {
		LocalVotacionParser parser = new LocalVotacionParser();
		List<LocalVotacion> locales = parser.obtenerLocalesVotacion("250101");
		for (LocalVotacion local : locales) {
			System.out.println(local);
		}
	}

}
