package ar.com.facturacion.controlador.api;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ar.com.facturacion.dominio.Item;
import ar.com.facturacion.repositorio.ItemRepositorio;

@RestController
@RequestMapping("/api/item")
public class ItemRest {
	
	@Autowired
	private ItemRepositorio itemRepositorio;
	
	@GetMapping
	public List<Item> getItems(){
		return itemRepositorio.findAll();
	}
	
	@GetMapping("/{id}")
	public Item getItem(@PathVariable Long id) {
		Optional<Item> item = itemRepositorio.findById(id);
		return item.get();
	}
	
	@DeleteMapping("/{id}")
	public void removeItem(@PathVariable Long id) {
		Optional<Item> item = itemRepositorio.findById(id);
		itemRepositorio.delete(item.get());
	}
	
	@PostMapping
	public void createItem(@Valid @RequestBody Item item) {
		if (item.getId() == null) {
			itemRepositorio.save(item);
		}
	}
	
	@PutMapping
	public void updateItem(@Valid @RequestBody Item item) {
		Optional<Item> itemActual = itemRepositorio.findById(item.getId());
		if(itemActual.isPresent()) {
			itemRepositorio.save(item);
		}
	}
}
