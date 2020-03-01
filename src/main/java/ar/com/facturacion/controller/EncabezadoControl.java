package ar.com.facturacion.controller;


import ar.com.facturacion.dominio.Cliente;
import ar.com.facturacion.dominio.Encabezado;
import ar.com.facturacion.dominio.Item;
import ar.com.facturacion.dominio.Producto;
import ar.com.facturacion.repositorio.ClienteRepositorio;
import ar.com.facturacion.repositorio.EncabezadoRepositorio;
import ar.com.facturacion.repositorio.ProductoRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;
import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Controller
@RequestMapping("/factura")
public class EncabezadoControl {
    @Autowired
    private ClienteRepositorio clienterepository;
    @Autowired
    private ProductoRepositorio productorepository;
    @Autowired
    private EncabezadoRepositorio encabezadorepository;

    @GetMapping("/creacion_factura")
    public String creacionFactura(Encabezado encabezado,Errors errors,Model model ) {
        List<Cliente> clientes;
        List<Producto> productos;
        List<Item> items = null;
        productos=productorepository.findByEstado();
        clientes=clienterepository.findByVisibilidad();
        model.addAttribute("titulo", "Listado de Clientes");
        model.addAttribute("data", clientes);
        model.addAttribute("productos",productos);
        model.addAttribute("encabezado",new Encabezado());

        model.addAttribute("items",items);
        return "facturas/creacion_factura";
    }
//MAÑANA HACER OTRO CONTROLADOR PARA CREAR ITEMS Y GUARDARLO EN LA TABLA DE ITEMS CONSULTANDO SI ES NECESARIO LA INFO DEL ENCABEZADO (SE USA OTRA VISTA)
    @PostMapping(value = "/creacion_factura")
    public String guardarFactura(Encabezado encabezado,Errors errors,Model model) {
        System.out.println("ENTRO AL POST\n\n");
        Timestamp timestamp = new Timestamp(System.currentTimeMillis()); //Instanciamos fecha actual para el encabezado de factura.
        encabezado.setFecha(timestamp);
        System.out.println("PASO ERRORES\n\n");
        model.addAttribute("facturaInfo",encabezado);
        System.out.println(encabezado);
        System.out.println("PASO PRINT ENCABEZADO\n\n");

        if(encabezado.getId()==null){
            encabezadorepository.save(encabezado);
        }
        return "facturas/mis_facturas.html";
    }

}
