package lpe.elecciones.model;

public class UbicacionGeografica {
	
	private Ambito ambito;
	private TipoUbicacion tipo;
	
	private String codigo;
	private String nombre;
	
	public UbicacionGeografica(Ambito ambito, TipoUbicacion tipo, String codigo, String nombre) {
		this.ambito = ambito;
		this.tipo = tipo;
		this.codigo = codigo;
		this.nombre = nombre;
	}
	
	public Ambito getAmbito() {
		return ambito;
	}
	
	public TipoUbicacion getTipo() {
		return tipo;
	}
	
	public String getCodigo() {
		return codigo;
	}

	public String getNombre() {
		return nombre;
	}

	@Override
	public String toString() {
		return ambito + "-" + tipo + "[codigo=" + codigo + ", nombre=" + nombre + "]";
	}
}
