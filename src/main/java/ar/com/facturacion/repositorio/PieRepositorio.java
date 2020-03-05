package ar.com.facturacion.repositorio;

import ar.com.facturacion.dominio.Encabezado;
import org.springframework.data.jpa.repository.JpaRepository;

import ar.com.facturacion.dominio.Pie;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface PieRepositorio extends JpaRepository<Pie, Long>{
    Pie findByEncabezadoContaining(Encabezado encabezado);


    @Query(value = "SELECT P.* FROM facturas_encabezado E,facturas_pie P WHERE E.id=P.facturas_encabezado_id and E.id=?1",nativeQuery = true)
    Pie findByIdEncabezado(Long id);
}
