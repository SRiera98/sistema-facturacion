package ar.com.facturacion.controlador.api;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import ar.com.facturacion.dominio.Producto;
import ar.com.facturacion.repositorio.ProductoRepositorio;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api/producto")
public class ProductoRest {

	@Autowired
	private ProductoRepositorio productoRepositorio;

	@GetMapping("/todos")
	public List<Producto> getProductos(){
		return productoRepositorio.findAll();
	}

	@GetMapping("/visibles")
	public List<Producto> getProductosVisibles(){
		return productoRepositorio.findByEstado(true);
	}

	@GetMapping("/{id}")
	public Producto getProducto(@PathVariable Long id) {
		Optional<Producto> producto = productoRepositorio.findById(id);
		return producto.get();
	}

	@GetMapping("/busqueda/{texto}")
	public List<Producto> getProductoPorTexto(@PathVariable String texto){
		List<Producto> lista = new ArrayList<Producto>();
		if(texto.length() >= 2) {
			lista = productoRepositorio.findByCodigoContainingOrNombreContaining(texto, texto);
		}
		return lista;
	}

	@DeleteMapping("/eliminar/{id}")
	@ResponseStatus(HttpStatus.GONE)
	public void removeProducto(@PathVariable Long id) {
		Optional<Producto> producto = productoRepositorio.findById(id);
		producto.get().setEstado(false);
		productoRepositorio.save(producto.get());
	}

	@PostMapping("/crear")
	@ResponseStatus(HttpStatus.CREATED)
	public void createProducto(@Valid  Producto producto) {
		if (producto.getId() == null) {
			productoRepositorio.save(producto);
		}
	}

	@PutMapping("/modificar")
	@ResponseStatus(HttpStatus.OK)
	public void updateProducto(@Valid Producto producto) {
		Optional<Producto> productoActual = productoRepositorio.findById(producto.getId());
		if(productoActual.isPresent()) {
			productoRepositorio.save(producto);
		}
	}
}