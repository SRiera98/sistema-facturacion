package ar.com.facturacion.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;

import ar.com.facturacion.dominio.Item;

public interface ItemRepositorio extends JpaRepository<Item, Long>{

}
