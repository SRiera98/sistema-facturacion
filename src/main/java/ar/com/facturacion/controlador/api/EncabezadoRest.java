package ar.com.facturacion.controlador.api;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

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
	
	@DeleteMapping("/eliminar/{id}")
	@ResponseStatus(HttpStatus.GONE)
	public void removeEncabezado(@PathVariable Long id) {
		Optional<Encabezado> encabezado = encabezadoRepositorio.findById(id);
		if(encabezado.isPresent()){
		encabezado.get().setAnulado(false);
		encabezadoRepositorio.save(encabezado.get());
		}
	}
	
	@PostMapping("/crear")
	@ResponseStatus(HttpStatus.CREATED)
	public void createEncabezado(@Valid Encabezado encabezado) {
		if (encabezado.getId() == null) {
			encabezadoRepositorio.save(encabezado);
		}
	}
	
	@PutMapping("/modificar")
	@ResponseStatus(HttpStatus.OK)
	public void updateEncabezado(@Valid Encabezado encabezado) {
		Optional<Encabezado> encabezadoActual = encabezadoRepositorio.findById(encabezado.getId());
		if(encabezadoActual.isPresent()) {
			encabezadoRepositorio.save(encabezado);
		}
	}
}
