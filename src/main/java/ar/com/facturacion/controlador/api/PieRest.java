package ar.com.facturacion.controlador.api;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import ar.com.facturacion.dominio.Pie;
import ar.com.facturacion.repositorio.PieRepositorio;

@RestController
@RequestMapping("/api/pie")
public class PieRest {
	
	@Autowired
	private PieRepositorio pieRepositorio;
	
	@GetMapping("/todos")
	public List<Pie> getPies(){
		return pieRepositorio.findAll();
	}
	
	@GetMapping("/{id}")
	public Pie getPie(@PathVariable Long id) {
		Optional<Pie> pie = pieRepositorio.findById(id);
		return pie.get();
	}
	
	@DeleteMapping("/eliminar/{id}")
	@ResponseStatus(HttpStatus.GONE)
	public void removePie(@PathVariable Long id) {
		Optional<Pie> pie = pieRepositorio.findById(id);
		if(pie.isPresent()){
			pieRepositorio.delete(pie.get());
		}
	}
	
	@PostMapping("/crear")
	@ResponseStatus(HttpStatus.CREATED)
	public void createPie(@Valid Pie pie) {
		if (pie.getId() == null) {
			pieRepositorio.save(pie);
		}
	}
	
	@PutMapping("/modificar")
	@ResponseStatus(HttpStatus.OK)
	public void updatePie(@Valid Pie pie) {
		Optional<Pie> pieActual = pieRepositorio.findById(pie.getId());
		if(pieActual.isPresent()) {
			pieRepositorio.save(pie);
		}
	}
}
