package ar.com.facturacion.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;

import ar.com.facturacion.dominio.Encabezado;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface EncabezadoRepositorio extends JpaRepository<Encabezado, Long>{
    Optional<Encabezado> findByNumero(String numero);

    List<Encabezado> findByAnuladoOrderByFechaDesc(Boolean anulado);

}
