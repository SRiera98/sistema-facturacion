package ar.com.facturacion.dominio;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;
import javax.validation.constraints.Size;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.lang.Nullable;

@Entity
@Table(name="clientes")
public class Cliente implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 3159888465448526457L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	
	private Long id;
	@NotNull
	@Size(min = 4,max = 13)
	private String codigo;
	@NotNull
	@Size(min = 3,max = 120)
	private String nombre;
	@NotNull
	@Size(min = 6,max = 120)
	private String direccion;
	@Nullable
	@Size(min = 8,max = 13)
	private String cuit;
	@NotNull
	private String categoria;

	@NotNull
	private Byte visibilidad=1;

	public Byte getVisibilidad() {
		return visibilidad;
	}

	public void setVisibilidad(Byte visibilidad) {
		this.visibilidad = visibilidad;
	}

	public String getCategoria() {
		return categoria;
	}

	public void setCategoria(String categoria) {
		this.categoria = categoria;
	}

	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getCodigo() {
		return codigo;
	}
	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getDireccion() {
		return direccion;
	}
	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}
	public String getCuit() {
		return cuit;
	}
	public void setCuit(String cuit) {
		this.cuit = cuit;
	}
	@Override
	public String toString() {
		return "Cliente [id=" + id + ", codigo=" + codigo + ", nombre=" + nombre + ", direccion=" + direccion
				+ ", cuit=" + cuit + ",categoria="+categoria+",visibilidad="+visibilidad+"]";
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((codigo == null) ? 0 : codigo.hashCode());
		result = prime * result + ((cuit == null) ? 0 : cuit.hashCode());
		result = prime * result + ((direccion == null) ? 0 : direccion.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((nombre == null) ? 0 : nombre.hashCode());
		result = prime * result + ((categoria == null) ? 0 : categoria.hashCode());
		result = prime * result + ((visibilidad == null) ? 0 : visibilidad.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (!(o instanceof Cliente)) return false;
		Cliente cliente = (Cliente) o;
		return getId().equals(cliente.getId()) &&
				getCodigo().equals(cliente.getCodigo()) &&
				getNombre().equals(cliente.getNombre()) &&
				getDireccion().equals(cliente.getDireccion()) &&
				getCuit().equals(cliente.getCuit()) &&
				getCategoria().equals(cliente.getCategoria()) &&
				getVisibilidad().equals(cliente.getVisibilidad());
	}
}
