package ar.com.facturacion.dominio;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.sql.Timestamp;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.Cascade;
import org.springframework.lang.Nullable;


@EqualsAndHashCode
@Entity
@Table(name="facturas_encabezado")
public class Encabezado implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -3479505724865821556L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	
	private Long id;
	@NotNull
	private Timestamp fecha;
	@NotNull
	@Size(min = 1,max = 11)
	private String numero;
	@Nullable
	private String letra;
	@NotNull
	@OneToOne
	private Cliente cliente;
	@OneToMany(mappedBy = "encabezado")
	private List<Item> items;
	@NotNull
	private Boolean anulado=false;
	public Long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public Date getFecha() {
		return fecha;
	}
	public void setFecha(Timestamp fecha) {
		this.fecha = fecha;
	}
	public String getNumero() {
		return numero;
	}
	public void setNumero(String numero) {
		this.numero = numero;
	}
	public String getLetra() {
		return letra;
	}
	public void setLetra(String letra) {
		this.letra = letra;
	}
	public Cliente getCliente() {
		return cliente;
	}
	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}
	public Boolean getAnulado() {
		return anulado;
	}
	public void setAnulado(Boolean anulado) {
		this.anulado = anulado;
	}

    public List<Item> getItems() {
        return items;
    }

    @Override
	public String toString() {
		return "Encabezado{" +
				"id=" + id +
				", fecha=" + fecha +
				", numero='" + numero + '\'' +
				", letra='" + letra + '\'' +
				", cliente=" + cliente +
				", anulado=" + anulado +
				'}';
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((anulado == null) ? 0 : anulado.hashCode());
		result = prime * result + ((cliente == null) ? 0 : cliente.hashCode());
		result = prime * result + ((fecha == null) ? 0 : fecha.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((items == null) ? 0 : items.hashCode());
		result = prime * result + ((letra == null) ? 0 : letra.hashCode());
		result = prime * result + ((numero == null) ? 0 : numero.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Encabezado other = (Encabezado) obj;
		if (anulado == null) {
			if (other.anulado != null)
				return false;
		} else if (!anulado.equals(other.anulado))
			return false;
		if (cliente == null) {
			if (other.cliente != null)
				return false;
		} else if (!cliente.equals(other.cliente))
			return false;
		if (fecha == null) {
			if (other.fecha != null)
				return false;
		} else if (!fecha.equals(other.fecha))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (items == null) {
			if (other.items != null)
				return false;
		} else if (!items.equals(other.items))
			return false;
		if (letra == null) {
			if (other.letra != null)
				return false;
		} else if (!letra.equals(other.letra))
			return false;
		if (numero == null) {
			if (other.numero != null)
				return false;
		} else if (!numero.equals(other.numero))
			return false;
		return true;
	}
	public void addItem(Item item){
		items.add(item);
		item.setEncabezado(this);

	}
}
