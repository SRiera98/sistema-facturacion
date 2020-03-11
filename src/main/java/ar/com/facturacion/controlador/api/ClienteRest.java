package ar.com.facturacion.controlador.api;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import ar.com.facturacion.repositorio.EncabezadoRepositorio;
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
	private EncabezadoRepositorio encabezadoRepositorio;
	
	@GetMapping("/todos")
	public List<Cliente> getClientes(){
		return clienteRepositorio.findAll();
	}

	@GetMapping("/visibles")
	public List<Cliente> getClientesVisibles(){
		return clienteRepositorio.findByVisibilidad();
	}
	
	@GetMapping("/{id}")
	public Cliente getCliente(@PathVariable Long id) {
		Optional<Cliente> cliente = clienteRepositorio.findById(id);
		return cliente.get();
	}
	
	@DeleteMapping("/eliminar/{id}")
	public void removeCliente(@PathVariable Long id) {
		Optional<Cliente> cliente = clienteRepositorio.findById(id);
		cliente.get().setVisibilidad(false);
		clienteRepositorio.save(cliente.get());
		/*
		Hay que pensar, debido a que cuando hacemos el soft-delete del cliente, quedan todavia las facturas que fueron
		creadas para ese Cliente antes que este sea borrado. Necesitamos hacer soft-delete de facturas de ese Cliente.
		Para ello lo mejor creo es hacer un @Query que devuelva una lista de encabezados, buscando que coincidan con
		el ID del cliente (ya que un cliente puede tener mas de una factura). luego usar foreach y guardar todos los objetos
		con el soft-delete hecho.
		*/
	}
	
	@PostMapping("/crear")
	public void createCliente(@Valid Cliente cliente) { //Con @RequestBody larga error, ver...
		if (cliente.getId() == null) {
			clienteRepositorio.save(cliente);
		}
	}
	
	@PutMapping("/modificar")
	public void updateCliente(@Valid Cliente cliente) { //Con @RequestBody larga error, ver... || En el PostMan se pasan todos los campos.
		Optional<Cliente> clienteActual = clienteRepositorio.findById(cliente.getId());
		if(clienteActual.isPresent()) {
			clienteRepositorio.save(cliente);
		}
	}
}



