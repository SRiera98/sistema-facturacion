package ar.com.facturacion.controller;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import ar.com.facturacion.dominio.Producto;
import ar.com.facturacion.repositorio.ProductoRepositorio;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
@Controller
@RequestMapping("/producto")
public class ProductoControl {
	
	@Autowired
	private ProductoRepositorio repository;
	private static Integer currentPage = 1;
	private static Integer pageSize = 5;
	
	@GetMapping("/index")
	public String index(Model model, @RequestParam("page") Optional<Integer> page, @RequestParam("size") Optional<Integer> size) {
        Page<Producto> dataPage;
        if (!page.isPresent() && !size.isPresent()){
            dataPage = repository.findByEstado(PageRequest.of(currentPage - 1, pageSize));
        }else{
            dataPage = repository.findByEstado(PageRequest.of(page.get() - 1, size.get()));
        }
		int totalPages = dataPage.getTotalPages();
		if (totalPages > 0) {
			List<Integer> pageNumbers = IntStream.rangeClosed(1, totalPages).boxed().collect(Collectors.toList());
			model.addAttribute("pageNumbers", pageNumbers);
		}
		model.addAttribute("titulo", "Listado de Productos");
		model.addAttribute("data", dataPage);
		return "productos/index";
	}


    @GetMapping(value = "/registrar")
    public String agregarProducto(@Valid Producto producto,Errors errors, Model model) {
        model.addAttribute("producto", new Producto());
        model.addAttribute("titulo","Registro Producto");
        model.addAttribute("action","/producto/registrar");
        return "productos/form_prod";
    }

    @PostMapping(value = "/registrar")
    public String guardarProducto(@Valid Producto producto,Errors errors, Model model) {
        if(errors.hasErrors()){
            return "productos/form_prod";
        }

        model.addAttribute("productoInfo",producto);

        if(producto.getId()==null){
            repository.save(producto);
        }
        return "redirect:/producto/index";
    }


    @GetMapping(value = "/eliminar/{id}")
    public String eliminarProducto(@PathVariable Long id){
        Producto producto= repository.findById(id).get();
        producto.setEstado((byte)0);
        repository.save(producto);
        return "redirect:/producto/index";
    }

    @GetMapping(value = "/modificar/{id}")
    public String modificarProducto(@PathVariable Long id, Model model){
        Producto producto=repository.findById(id).get();
        model.addAttribute("titulo","Modificado de Producto");
        model.addAttribute("producto",producto);
        model.addAttribute("action","/producto/modificar/"+id);
        return "productos/form_prod";
    }
    @PostMapping(value="/modificar/{id}")
    public String cambiosProdModif(@Valid Producto producto){
        repository.save(producto);

        return "redirect:/producto/index";
    }

}
