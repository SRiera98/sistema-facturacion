package ar.com.facturacion.controller;

import ar.com.facturacion.dominio.Cliente;
import ar.com.facturacion.repositorio.ClienteRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Controller
@RequestMapping("/cliente")

public class ClienteControl {

    @Autowired
    private ClienteRepositorio repository;
    private static Integer currentPage = 1;
    private static Integer pageSize = 5;
    @GetMapping("/indexcliente")
    public String index_cliente(Model model, @RequestParam("page") Optional<Integer> page, @RequestParam("size") Optional<Integer> size) {
        Page<Cliente> dataPage;
        if (!page.isPresent() && !size.isPresent()){
            dataPage = repository.findByVisibilidad(PageRequest.of(currentPage - 1, pageSize));
        }else{
            dataPage = repository.findByVisibilidad(PageRequest.of(page.get() - 1, size.get()));
        }
        int totalPages = dataPage.getTotalPages();
        if (totalPages > 0) {
            List<Integer> pageNumbers = IntStream.rangeClosed(1, totalPages).boxed().collect(Collectors.toList());
            model.addAttribute("pageNumbers", pageNumbers);
        }
        model.addAttribute("titulo", "Listado de Clientes");
        model.addAttribute("data", dataPage);
        return "clientes/indexcliente";
    }

    @GetMapping(value = "/registrar")
    public String agregarCliente(@Valid Cliente cliente,Errors errors, Model model) {
        model.addAttribute("cliente", new Cliente());
        model.addAttribute("action","/cliente/registrar");
        return "clientes/form_cliente";
    }

    @PostMapping(value = "/registrar")
    public String guardarCliente(@Valid Cliente cliente,Errors errors, Model model) {
        model.addAttribute("registrar",true);
        if(errors.hasErrors()){
            return "clientes/form_cliente";
        }

        model.addAttribute("clienteInfo",cliente);

        if(cliente.getId()==null){
            repository.save(cliente);
        }
        return "redirect:/cliente/indexcliente";
    }

    @GetMapping(value = "/eliminar/{id}")
    public String eliminarCliente(@PathVariable Long id){
        Cliente cliente= repository.findById(id).get();
        cliente.setVisibilidad((byte) 0);
        repository.save(cliente);
        return "redirect:/cliente/indexcliente";
    }

    @GetMapping(value = "/modificar/{id}")
    public String modificarCliente(@PathVariable Long id, Model model){
        Cliente cliente=repository.findById(id).get();
        model.addAttribute("titulo","Modificado de Cliente");
        model.addAttribute("cliente",cliente);
        model.addAttribute("action","/cliente/modificar/"+id);
        return "clientes/form_cliente";
    }
    @PostMapping(value="/modificar/{id}")
    public String cambiosClienteModif(@Valid Cliente cliente){
        repository.save(cliente);

        return "redirect:/cliente/indexcliente";
    }

}
