package ar.com.facturacion.repositorio;

import ar.com.facturacion.dominio.Encabezado;
import org.springframework.data.jpa.repository.JpaRepository;

import ar.com.facturacion.dominio.Pie;

public interface PieRepositorio extends JpaRepository<Pie, Long>{
    Pie findByEncabezadoContaining(Encabezado encabezado);
}
