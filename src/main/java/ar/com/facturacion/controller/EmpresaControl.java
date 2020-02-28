package ar.com.facturacion.controller;

import ar.com.facturacion.dominio.Empresa;
import ar.com.facturacion.repositorio.EmpresaRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Controller
@RequestMapping("/empresas")
public class EmpresaControl {

    @Autowired
    private EmpresaRepositorio repository;
    private static Integer currentPage = 1;
    private static Integer pageSize = 5;

    @GetMapping("/indexempresa.html")
    public String index_empresa(Model model, @RequestParam("page") Optional<Integer> page, @RequestParam("size") Optional<Integer> size) {
        Page<Empresa> dataPage;
        if (!page.isPresent() && !size.isPresent()){
            dataPage = repository.findAll(PageRequest.of(currentPage - 1, pageSize));
        }else{
            dataPage = repository.findAll(PageRequest.of(page.get() - 1, size.get()));
        }
        int totalPages = dataPage.getTotalPages();
        if (totalPages > 0) {
            List<Integer> pageNumbers = IntStream.rangeClosed(1, totalPages).boxed().collect(Collectors.toList());
            model.addAttribute("pageNumbers", pageNumbers);
        }
        model.addAttribute("titulo", "Lista de Empresas");
        model.addAttribute("data", dataPage);
        return "empresas/indexempresa";
    }

    /*@GetMapping(value = "/registar")
    public String registroGet(Model model){
	    model.addAttribute("empresa",new Empresa());
	    return "clientes/registro_empresa";
    }

    @RequestMapping(value = "/registrar")
    public String registroPost(@Valid Empresa empresa, Errors errors, Model model){
        if (errors.hasErrors()){
            return "empresas/registro_empresa";
        }

        model.addAttribute("empresa",new Empresa());
        model.addAttribute("empresaInfo",empresa);
        if(empresa.getId()==null){
            repository.save(empresa);
        }
        return "empresas/registro_empresa";
    }
*/



    @GetMapping(value = "/registrar")
    public String agregarEmpresa(@Valid Empresa empresa,Errors errors, Model model) {
        model.addAttribute("empresa", new Empresa());
        return "empresas/registro_empresa";
    }

    @PostMapping(value = "/registrar")
    public String guardarEmpresa(@Valid Empresa empresa,Errors errors, Model model) {
        if(errors.hasErrors()){
            return "empresas/registro_empresa";
        }

        model.addAttribute("empresaInfo",empresa);

        if(empresa.getId()==null){
            repository.save(empresa);
        }
        return "empresas/empresaregistrada.html";
    }





}
