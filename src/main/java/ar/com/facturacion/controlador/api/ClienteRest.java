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

import ar.com.facturacion.dominio.Cliente;
import ar.com.facturacion.repositorio.ClienteRepositorio;

@RestController
@RequestMapping("/api/cliente")
public class ClienteRest {
	
	@Autowired
	private ClienteRepositorio clienteRepositorio;
	
	@GetMapping
	public List<Cliente> getClientes(){
		return clienteRepositorio.findAll();
	}
	
	@GetMapping("/{id}")
	public Cliente getCliente(@PathVariable Long id) {
		Optional<Cliente> cliente = clienteRepositorio.findById(id);
		return cliente.get();
	}
	
	@DeleteMapping("/{id}")
	public void removeCliente(@PathVariable Long id) {
		Optional<Cliente> cliente = clienteRepositorio.findById(id);
		clienteRepositorio.delete(cliente.get());
	}
	
	@PostMapping
	public void createCliente(@Valid @RequestBody Cliente cliente) {
		if (cliente.getId() == null) {
			clienteRepositorio.save(cliente);
		}
	}
	
	@PutMapping
	public void updateCliente(@Valid @RequestBody Cliente cliente) {
		Optional<Cliente> clienteActual = clienteRepositorio.findById(cliente.getId());
		if(clienteActual.isPresent()) {
			clienteRepositorio.save(cliente);
		}
	}
}



