package ar.com.facturacion.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;

import ar.com.facturacion.dominio.Encabezado;

public interface EncabezadoRepositorio extends JpaRepository<Encabezado, Long>{

}
