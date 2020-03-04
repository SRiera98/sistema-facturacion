package ar.com.facturacion.repositorio;

import ar.com.facturacion.dominio.Encabezado;
import org.springframework.data.jpa.repository.JpaRepository;

import ar.com.facturacion.dominio.Item;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ItemRepositorio extends JpaRepository<Item, Long>{

    List<Item> findByEncabezadoContaining(Encabezado encabezado);

    @Query(value = "SELECT * FROM facturacion.facturas_items where facturas_encabezado_id=?1",nativeQuery = true)
    List<Item> findByIdEncabezado();

}
