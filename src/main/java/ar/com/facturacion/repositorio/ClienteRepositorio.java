package ar.com.facturacion.repositorio;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import ar.com.facturacion.dominio.Cliente;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface ClienteRepositorio extends JpaRepository<Cliente, Long>{
	Cliente findByNombreContaining(String nombre);

	@Query(value = "SELECT * FROM facturacion.clientes where visibilidad=1",nativeQuery = true)
	Page<Cliente> findByVisibilidad(Pageable pageable);

	@Query(value = "SELECT * FROM facturacion.clientes where visibilidad=1",nativeQuery = true)
	List<Cliente> findByVisibilidad();
}
