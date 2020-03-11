package ar.com.facturacion.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;

import ar.com.facturacion.dominio.Encabezado;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface EncabezadoRepositorio extends JpaRepository<Encabezado, Long>{
    @Query(value = "SELECT * FROM facturacion.facturas_encabezado where anulado=0 order by fecha desc",nativeQuery = true)
    List<Encabezado> findByAnulado();

}
