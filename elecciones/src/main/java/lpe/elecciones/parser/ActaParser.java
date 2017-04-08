package lpe.elecciones.parser;

import java.net.URI;
import java.net.URISyntaxException;

import org.springframework.http.HttpMethod;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

public class ActaParser extends AbstractParser {

	// curl 'https://resultadoselecciones2016.onpe.gob.pe/PRPCP2016/ajax.php'
	// -H 'Content-Type: application/x-www-form-urlencoded; charset=UTF-8'
	// -H 'X-Requested-With: XMLHttpRequest'
	// --data
	// 'pid=5687439786260744&_clase=mesas&_accion=displayMesas&ubigeo=140101&nroMesa=031187&tipoElec=10&page=1&pornumero=1'
	public String obtenerResultadoMesa(String ubigeo, String codigoLocal, String codigoMesa) throws URISyntaxException {
		RestTemplate template = new RestTemplate();

		URI uri = new URI("https://resultadoselecciones2016.onpe.gob.pe/PRPCP2016/ajax.php");

		String body = "pid=5687439786260744&_clase=mesas&_accion=displayMesas&ubigeo=" + ubigeo + "&nroMesa="
				+ codigoMesa + "&tipoElec=10&page=1&pornumero=1";

		MultiValueMap<String, String> headers = new LinkedMultiValueMap<String, String>();
		headers.add("Content-Type", "application/x-www-form-urlencoded; charset=UTF-8");
		headers.add("X-Requested-With", "XMLHttpRequest");

		RequestEntity<String> request = new RequestEntity<String>(body, headers, HttpMethod.POST, uri);

		ResponseEntity<String> response = template.exchange(request, String.class);
		String htmlMesa = response.getBody();
		return htmlMesa;
	}

	public static void main(String[] args) throws URISyntaxException {
		ActaParser parser = new ActaParser();
		String html = parser.obtenerResultadoMesa("140101", "4953", "031172");
		System.out.println(html);
	}
}
