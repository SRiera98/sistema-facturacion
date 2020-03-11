package ar.com.facturacion.controlador.api;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import ar.com.facturacion.dominio.Item;
import ar.com.facturacion.repositorio.ItemRepositorio;

@RestController
@RequestMapping("/api/item")
public class ItemRest {
	
	@Autowired
	private ItemRepositorio itemRepositorio;
	
	@GetMapping("/todos")
	public List<Item> getItems(){
		return itemRepositorio.findAll();
	}
	
	@GetMapping("/{id}")
	public Item getItem(@PathVariable Long id) {
		Optional<Item> item = itemRepositorio.findById(id);
		return item.get();
	}
	
	@DeleteMapping("/eliminar/{id}")
	@ResponseStatus(HttpStatus.GONE)
	public void removeItem(@PathVariable Long id) {
		Optional<Item> item = itemRepositorio.findById(id);
		if (item.isPresent()){
			itemRepositorio.delete(item.get());
		}
	}
	
	@PostMapping("/crear")
	@ResponseStatus(HttpStatus.CREATED)
	public void createItem(@Valid Item item) {
		if (item.getId() == null) {
			itemRepositorio.save(item);
		}
	}
	
	@PutMapping("/modificar")
	@ResponseStatus(HttpStatus.OK)
	public void updateItem(@Valid Item item) {
		Optional<Item> itemActual = itemRepositorio.findById(item.getId());
		if(itemActual.isPresent()) {
			itemRepositorio.save(item);
		}
	}
}
