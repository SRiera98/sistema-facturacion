package ar.com.facturacion.dominio;

import java.util.List;

public class Factura {
	private Encabezado encabezado;
	private List<Item> items;
	private Pie pie;
	public Encabezado getEncabezado() {
		return encabezado;
	}
	public void setEncabezado(Encabezado encabezado) {
		this.encabezado = encabezado;
	}
	public List<Item> getItems() {
		return items;
	}
	public void setItems(List<Item> items) {
		this.items = items;
	}
	public Pie getPie() {
		return pie;
	}
	public void setPie(Pie pie) {
		this.pie = pie;
	}
	@Override
	public String toString() {
		return "Factura [encabezado=" + encabezado + ", items=" + items + ", pie=" + pie + "]";
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((encabezado == null) ? 0 : encabezado.hashCode());
		result = prime * result + ((items == null) ? 0 : items.hashCode());
		result = prime * result + ((pie == null) ? 0 : pie.hashCode());
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
		Factura other = (Factura) obj;
		if (encabezado == null) {
			if (other.encabezado != null)
				return false;
		} else if (!encabezado.equals(other.encabezado))
			return false;
		if (items == null) {
			if (other.items != null)
				return false;
		} else if (!items.equals(other.items))
			return false;
		if (pie == null) {
			if (other.pie != null)
				return false;
		} else if (!pie.equals(other.pie))
			return false;
		return true;
	}
	
}
