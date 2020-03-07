package ar.com.facturacion.controller;

import ar.com.facturacion.dominio.*;
import ar.com.facturacion.repositorio.*;
import ar.com.facturacion.servicios.FacturacionServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.math.BigDecimal;
import java.util.*;

@Controller
@RequestMapping("/factura")
public class FacturaControl {

    @Autowired
    private ItemRepositorio itemrepository;
    @Autowired
    private PieRepositorio pierepository;
    @Autowired
    private EncabezadoRepositorio encabezadorepository;
    @Autowired
    private EmpresaRepositorio empresarepository;

    @GetMapping(value = "/misfacturas")
    public String misFacturas(Model model){
        List<Factura> facturas=new LinkedList<Factura>();
        Factura factura=new Factura();
        List<Encabezado> encabezados=encabezadorepository.findByAnulado();
        for (Encabezado i:encabezados) {
            List<Item> items=itemrepository.findByIdEncabezado(i.getId());
            factura.setEncabezado(i);
            factura.setItems(items);
            factura.setPie(pierepository.findByIdEncabezado(i.getId()));
            facturas.add(factura);
            factura=new Factura();
        }
        model.addAttribute("facturas",facturas);
        return "facturas/mis_facturas";

    }


    @GetMapping(value = "/{id}")
    public String verFactura(Model model, @PathVariable Long id){
        Encabezado encabezado = encabezadorepository.getOne(id);
        model.addAttribute("encabezado",encabezado);
        model.addAttribute("items",encabezado.getItems());
        model.addAttribute("empresa",empresarepository.findAll());
        model.addAttribute("total", FacturacionServicio.calcularTotal(encabezado.getItems()));
        return "facturas/factura";

    }


    @GetMapping(value = "/eliminar/{id}")
    public String eliminarProducto(@PathVariable Long id, RedirectAttributes redirectAttributes){
        Encabezado encabezado= encabezadorepository.getOne(id);
        encabezado.setAnulado(true);
        encabezadorepository.save(encabezado);
        redirectAttributes.addFlashAttribute("mensaje","¡Factura Eliminada Correctamente!");
        return "redirect:/factura/misfacturas";
    }

}
