package ar.com.facturacion.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;

import ar.com.facturacion.dominio.Empresa;

public interface EmpresaRepositorio extends JpaRepository<Empresa, Long> {
	
}
