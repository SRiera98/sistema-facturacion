package ar.com.facturacion.repositorio;

import ar.com.facturacion.dominio.Encabezado;
import org.springframework.data.jpa.repository.JpaRepository;

import ar.com.facturacion.dominio.Item;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ItemRepositorio extends JpaRepository<Item, Long>{

    List<Item> findByEncabezado(Long id);

    @Query(value = "SELECT I.* FROM facturas_encabezado E,facturas_items I WHERE E.id=I.facturas_encabezado_id and E.id=?1",nativeQuery = true)
    List<Item> findByIdEncabezado(Long id);

}
