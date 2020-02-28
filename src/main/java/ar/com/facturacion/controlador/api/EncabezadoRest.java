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

import ar.com.facturacion.dominio.Encabezado;
import ar.com.facturacion.repositorio.EncabezadoRepositorio;

@RestController
@RequestMapping("/api/encabezado")
public class EncabezadoRest {
	
	@Autowired
	private EncabezadoRepositorio encabezadoRepositorio;
	
	@GetMapping
	public List<Encabezado> getEncabezados(){
		return encabezadoRepositorio.findAll();
	}
	
	@GetMapping("/{id}")
	public Encabezado getEncabezadp(@PathVariable Long id) {
		Optional<Encabezado> encabezado = encabezadoRepositorio.findById(id);
		return encabezado.get();
	}
	
	@DeleteMapping("/{id}")
	public void removeEncabezado(@PathVariable Long id) {
		Optional<Encabezado> encabezado = encabezadoRepositorio.findById(id);
		encabezadoRepositorio.delete(encabezado.get());
	}
	
	@PostMapping
	public void createEncabezado(@Valid @RequestBody Encabezado encabezado) {
		if (encabezado.getId() == null) {
			encabezadoRepositorio.save(encabezado);
		}
	}
	
	@PutMapping
	public void updateEncabezado(@Valid @RequestBody Encabezado encabezado) {
		Optional<Encabezado> encabezadoActual = encabezadoRepositorio.findById(encabezado.getId());
		if(encabezadoActual.isPresent()) {
			encabezadoRepositorio.save(encabezado);
		}
	}
}
