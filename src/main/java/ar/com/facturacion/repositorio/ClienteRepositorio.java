package ar.com.facturacion.repositorio;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import ar.com.facturacion.dominio.Cliente;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ClienteRepositorio extends JpaRepository<Cliente, Long>{
	Optional<Cliente> findByCodigo(String codigo);

	Page<Cliente> findByVisibilidad(Pageable pageable,Boolean visibilidad);

	List<Cliente> findByVisibilidad(Boolean visibilidad);
}
