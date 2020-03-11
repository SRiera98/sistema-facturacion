package ar.com.facturacion.controlador.api;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import ar.com.facturacion.repositorio.EncabezadoRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import ar.com.facturacion.dominio.Cliente;
import ar.com.facturacion.repositorio.ClienteRepositorio;

@RestController
@RequestMapping("/api/cliente")
public class ClienteRest {
	
	@Autowired
	private ClienteRepositorio clienteRepositorio;

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
    @ResponseStatus(HttpStatus.GONE)
    public void removeCliente(@PathVariable Long id) {
		Optional<Cliente> cliente = clienteRepositorio.findById(id);
		if(cliente.isPresent()){
		cliente.get().setVisibilidad(false);
		clienteRepositorio.save(cliente.get());
        }
	}
	
	@PostMapping("/crear")
    @ResponseStatus(HttpStatus.CREATED)
	public void createCliente(@Valid Cliente cliente) {
		if (cliente.getId() == null) {
			clienteRepositorio.save(cliente);
		}

	}
	
	@PutMapping("/modificar")
    @ResponseStatus(HttpStatus.OK)
    public void updateCliente(@Valid Cliente cliente){
		Optional<Cliente> clienteActual = clienteRepositorio.findById(cliente.getId());
		if(clienteActual.isPresent()) {
			clienteRepositorio.save(cliente);
		}
	}
}



