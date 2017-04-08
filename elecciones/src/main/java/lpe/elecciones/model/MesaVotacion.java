package lpe.elecciones.model;

public class MesaVotacion {
	private String ubigeo;
	private String codigoLocal;
	private String codigoMesa;
	
	public MesaVotacion(String ubigeo, String codigoLocal, String codigoMesa) {
		this.ubigeo = ubigeo;
		this.codigoLocal = codigoLocal;
		this.codigoMesa = codigoMesa;
	}

	public String getUbigeo() {
		return ubigeo;
	}

	public String getCodigoLocal() {
		return codigoLocal;
	}

	public String getCodigoMesa() {
		return codigoMesa;
	}

	@Override
	public String toString() {
		return "MesaVotacion [ubigeo=" + ubigeo + ", codigoLocal=" + codigoLocal + ", codigoMesa=" + codigoMesa + "]";
	}
}
