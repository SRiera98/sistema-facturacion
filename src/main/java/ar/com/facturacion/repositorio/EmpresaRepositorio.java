package ar.com.facturacion.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;

import ar.com.facturacion.dominio.Empresa;
import org.springframework.stereotype.Repository;

@Repository
public interface EmpresaRepositorio extends JpaRepository<Empresa, Long> {
	
}
