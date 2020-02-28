package ar.com.facturacion.controlador.api;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ar.com.facturacion.dominio.Producto;
import ar.com.facturacion.repositorio.ProductoRepositorio;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api/producto")
public class ProductoRest {
	
	@Autowired
	private ProductoRepositorio productoRepositorio;
	
	@GetMapping
	public List<Producto> getProductos(){
		return productoRepositorio.findAll();
	}
	
	@GetMapping("/{id}")
	public Producto getProducto(@PathVariable Long id) {
		Optional<Producto> producto = productoRepositorio.findById(id);
		return producto.get();
	}
	
	@GetMapping("/busqueda/{texto}")
	public List<Producto> getProductoPorTexto(@PathVariable String texto){
		List<Producto> lista = new ArrayList<Producto>();
		if(texto.length() <= 2) {
			lista = productoRepositorio.findByCodigoContainingOrNombreContaining(texto, texto);
		} 
		return lista; 
	}
	
	@DeleteMapping("/{id}")
	public void removeProducto(@PathVariable Long id) {
		Optional<Producto> producto = productoRepositorio.findById(id);
		productoRepositorio.delete(producto.get());
	}
	
	@PostMapping
	public void createProducto(@Valid @RequestBody Producto producto) {
		if (producto.getId() == null) {
			productoRepositorio.save(producto);
		}
	}
	
	@PutMapping
	public void updateProducto(@Valid @RequestBody Producto producto) {
		Optional<Producto> productoActual = productoRepositorio.findById(producto.getId());
		if(productoActual.isPresent()) {
			productoRepositorio.save(producto);
		}
	}
}
