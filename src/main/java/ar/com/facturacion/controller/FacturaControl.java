package ar.com.facturacion.controller;

import ar.com.facturacion.dominio.*;
import ar.com.facturacion.repositorio.ClienteRepositorio;
import ar.com.facturacion.repositorio.EncabezadoRepositorio;
import ar.com.facturacion.repositorio.ItemRepositorio;
import ar.com.facturacion.repositorio.PieRepositorio;
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

    @GetMapping(value = "/misfacturas")
    public String misFacturas(Model model){
        List<Factura> facturas=null;
        List<Encabezado> encabezados=encabezadorepository.findAll();
        for (Encabezado i:encabezados) {
            Long id=i.getId();
            System.out.println("ANTES\n\n");
            List<Item> items=itemrepository.findByEncabezadoContaining(i);
            System.out.println("DESPUES\n\n");
            Factura factura=new Factura();
            factura.setEncabezado(i);
            factura.setItems(items);
            factura.setPie(pierepository.findByEncabezadoContaining(i));
            facturas.add(factura);
        }
        System.out.println(facturas);
        model.addAttribute("facturas",facturas);
        return "facturas/mis_facturas";

    }

    @PostMapping(value = "/crear")
    public String subirFactura(){
        return "facturas/creacion_factura.html";
    }
    @GetMapping(value = "/{id}")
    public String verFactura(Model model, @PathVariable Long id){
        Encabezado encabezado = encabezadorepository.getOne(id);
        System.out.println(encabezado.getItems());
        model.addAttribute("encabezado",encabezado);
        model.addAttribute("items",encabezado.getItems());
        BigDecimal total=new BigDecimal(0);
        for (Item i: encabezado.getItems()) {
            total=total.add(i.getSubTotal());
        }
        model.addAttribute("total",total);


        return "facturas/factura";
    }


}
