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

import lpe.elecciones.model.MesaVotacion;

public class MesaVotacionParser extends AbstractParser {

	// curl 'https://resultadoselecciones2016.onpe.gob.pe/PRPCP2016/ajax.php'
	// -H 'Content-Type: application/x-www-form-urlencoded; charset=UTF-8'
	// -H 'X-Requested-With: XMLHttpRequest'
	// --data
	// 'pid=8486680900003410&_clase=actas&_accion=displayActas&tipoElec=&ubigeo=140101&actasPor=4953&ubigeoLocal=140101&page=undefined'

	public List<MesaVotacion> obtenerMesas(String ubigeo, String codigoLocal) throws URISyntaxException {
		RestTemplate template = new RestTemplate();

		URI uri = new URI("https://resultadoselecciones2016.onpe.gob.pe/PRPCP2016/ajax.php");

		String body = "pid=8486680900003410&_clase=actas&_accion=displayActas&tipoElec=&ubigeo=" + ubigeo + "&actasPor="
				+ codigoLocal + "&ubigeoLocal=" + ubigeo + "&page=undefined";

		MultiValueMap<String, String> headers = new LinkedMultiValueMap<String, String>();
		headers.add("Content-Type", "application/x-www-form-urlencoded; charset=UTF-8");
		headers.add("X-Requested-With", "XMLHttpRequest");

		RequestEntity<String> request = new RequestEntity<String>(body, headers, HttpMethod.POST, uri);

		ResponseEntity<String> response = template.exchange(request, String.class);
		String htmlMesasPorLocal = response.getBody();
		List<String> listadoCodigoMesa = parseHtmlHref(htmlMesasPorLocal);

		List<MesaVotacion> listaMesas = new ArrayList<MesaVotacion>();
		for (String codigoMesa : listadoCodigoMesa) {
			listaMesas.add(new MesaVotacion(ubigeo, codigoLocal, codigoMesa));
		}
		return listaMesas;
	}

	public static void main(String[] args) throws URISyntaxException {
		MesaVotacionParser parser = new MesaVotacionParser();
		List<MesaVotacion> mesas = parser.obtenerMesas("140101", "4953");
		for (MesaVotacion mesa : mesas) {
			System.out.println(mesa);
		}
	}
}
