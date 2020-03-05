package ar.com.facturacion.controller;

import ar.com.facturacion.dominio.*;
import ar.com.facturacion.repositorio.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;
import java.math.BigDecimal;
import java.util.*;

@Controller
@RequestMapping("/factura")
public class FacturaControl {

    @Autowired
    private ClienteRepositorio clienterepositorio;
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
        List<Encabezado> encabezados=encabezadorepository.findAll();
        for (Encabezado i:encabezados) {
            List<Item> items=itemrepository.findByIdEncabezado(i.getId());
            factura.setEncabezado(i);
            factura.setItems(items);
            factura.setPie(pierepository.findByIdEncabezado(i.getId()));
            facturas.add(factura);
            factura=new Factura();
        }
        System.out.println(facturas);
        model.addAttribute("facturas",facturas);
        return "facturas/mis_facturas";

    }


    @GetMapping(value = "/{id}")
    public String verFactura(Model model, @PathVariable Long id){
        Encabezado encabezado = encabezadorepository.getOne(id);
        System.out.println(encabezado.getItems());
        model.addAttribute("encabezado",encabezado);
        model.addAttribute("items",encabezado.getItems());
        model.addAttribute("empresa",empresarepository.findAll());
        System.out.println(empresarepository.findAll());
        System.out.println("\n\n\n");
        BigDecimal total=new BigDecimal(0);
        for (Item i: encabezado.getItems()) {
            total=total.add(i.getSubTotal());
        }
        model.addAttribute("total",total);


        return "facturas/factura";
    }


   /* @GetMapping(value = "/eliminar/{id}")
    public String eliminarProducto(@PathVariable Long id){
        Producto producto= encabezadorepository.findById(id).get();
        producto.setEstado((byte)0);
        repository.save(producto);
        return "redirect:/producto/index.html";
    }*/

}
