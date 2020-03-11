package ar.com.facturacion.repositorio;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import ar.com.facturacion.dominio.Producto;
@Repository
public interface ProductoRepositorio extends JpaRepository<Producto, Long>{
	List<Producto> findByCodigoContainingOrNombreContaining(String codigo, String nombre);
	@Query(value = "SELECT * FROM facturacion.productos where estado=1",nativeQuery = true)
	Page<Producto> findByEstado(Pageable pageable);

	@Query(value = "SELECT * FROM facturacion.productos where estado=1",nativeQuery = true)
	List<Producto> findByEstado();

}
