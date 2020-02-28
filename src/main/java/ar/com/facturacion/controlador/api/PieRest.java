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

import ar.com.facturacion.dominio.Pie;
import ar.com.facturacion.repositorio.PieRepositorio;

@RestController
@RequestMapping("/api/pie")
public class PieRest {
	
	@Autowired
	private PieRepositorio pieRepositorio;
	
	@GetMapping
	public List<Pie> getPies(){
		return pieRepositorio.findAll();
	}
	
	@GetMapping("/{id}")
	public Pie getPie(@PathVariable Long id) {
		Optional<Pie> pie = pieRepositorio.findById(id);
		return pie.get();
	}
	
	@DeleteMapping("/{id}")
	public void removePie(@PathVariable Long id) {
		Optional<Pie> pie = pieRepositorio.findById(id);
		pieRepositorio.delete(pie.get());
	}
	
	@PostMapping
	public void createPie(@Valid @RequestBody Pie pie) {
		if (pie.getId() == null) {
			pieRepositorio.save(pie);
		}
	}
	
	@PutMapping
	public void updatePie(@Valid @RequestBody Pie pie) {
		Optional<Pie> pieActual = pieRepositorio.findById(pie.getId());
		if(pieActual.isPresent()) {
			pieRepositorio.save(pie);
		}
	}
}
