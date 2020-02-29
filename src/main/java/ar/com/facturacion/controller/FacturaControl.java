package ar.com.facturacion.controller;

import ar.com.facturacion.dominio.Cliente;
import ar.com.facturacion.dominio.Encabezado;
import ar.com.facturacion.dominio.Pie;
import ar.com.facturacion.dominio.Producto;
import ar.com.facturacion.repositorio.ClienteRepositorio;
import ar.com.facturacion.repositorio.EncabezadoRepositorio;
import ar.com.facturacion.repositorio.ItemRepositorio;
import ar.com.facturacion.repositorio.PieRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;
import java.util.*;

@Controller
@RequestMapping("/factura")
public class FacturaControl {

    @Autowired
    private EncabezadoRepositorio encabezadoRepositorio;
    private ItemRepositorio itemRepositorio;
    private PieRepositorio pieRepositorio;
    private ClienteRepositorio clienteRepositorio;

    @GetMapping(value = "/generar")
    public String crearEncabezado(Model model){
        model.addAttribute("encabezado",new Encabezado());
        //model.addAttribute("pie",new Pie());
        model.addAttribute("titulo","Factura");
        System.out.println("HOLA SOY GET 1\n\n\n");
        model.addAttribute("clientes",clienteRepositorio.findAll());
        System.out.println("HOLA SOY GET\n\n\n");
        return "facturas/creacion_factura.html";
    }

    @PostMapping(value = "/generar")
    public String subirFactura(){
        System.out.println("HOLA SOY POST\n\n\n");

        return "facturas/creacion_factura.html";
    }



}
