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

import ar.com.facturacion.dominio.Empresa;
import ar.com.facturacion.repositorio.EmpresaRepositorio;

@RestController
@RequestMapping("/api/empresa")
public class EmpresaRest {
	
	@Autowired
	private EmpresaRepositorio empresaRepositorio;
	
	@GetMapping
	public List<Empresa> getEmpresas() {
		return empresaRepositorio.findAll();
	}
	
	@GetMapping("/{id}")
	public Empresa getEmpresa(@PathVariable Long id) {
		Optional<Empresa> empresa = empresaRepositorio.findById(id);
		return empresa.get();
	}
	
	@DeleteMapping("/{id}")
	public void removeEmpresa(@PathVariable Long id) {
		Optional<Empresa> empresa = empresaRepositorio.findById(id);
		empresaRepositorio.delete(empresa.get());
	}
	
	@PostMapping
	public void createEmpresa(@Valid @RequestBody Empresa empresa) {
		if (empresa.getId() == null) {
			empresaRepositorio.save(empresa);
		}
	}
	
	@PutMapping
	public void updateEmpresa(@Valid @RequestBody Empresa empresa) {
		Optional<Empresa> empresaActual = empresaRepositorio.findById(empresa.getId());
		if (empresaActual.isPresent()) {
			empresaRepositorio.save(empresa);
		}
	}
}
