package ar.com.facturacion.servicios;

import ar.com.facturacion.dominio.Item;

import java.util.List;

public class CreacionItems {
    private List<Item> items;

    public void addBook(Item item) {
        this.items.add(item);
    }
}
