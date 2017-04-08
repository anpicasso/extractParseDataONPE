package lpe.elecciones.model;

public class LocalVotacion {

	private String ubigeo;
	private String codigo;
	private String nombre;
	
	public LocalVotacion(String ubigeo, String codigo, String nombre) {
		this.ubigeo = ubigeo;
		this.codigo = codigo;
		this.nombre = nombre;
	}

	public String getUbigeo() {
		return ubigeo;
	}

	public String getCodigo() {
		return codigo;
	}

	public String getNombre() {
		return nombre;
	}

	@Override
	public String toString() {
		return "LocalVotacion [ubigeo=" + ubigeo + ", codigo=" + codigo + ", nombre=" + nombre + "]";
	}
}
