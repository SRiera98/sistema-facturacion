package ar.com.facturacion.controller;


import ar.com.facturacion.dominio.Cliente;
import ar.com.facturacion.dominio.Encabezado;
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
import java.sql.Timestamp;
import java.util.List;


@Controller
@RequestMapping("/factura")
public class EncabezadoControl {
    @Autowired
    private ClienteRepositorio clienterepository;
    @Autowired
    private ProductoRepositorio productorepository;
    @Autowired
    private EncabezadoRepositorio encabezadorepository;

    @GetMapping("/crear")
    public String creacionFactura(Encabezado encabezado,Errors errors,Model model ) {
        List<Cliente> clientes;
        clientes=clienterepository.findByVisibilidad();
        model.addAttribute("titulo", "Listado de Clientes");
        model.addAttribute("data", clientes);
        model.addAttribute("encabezado",new Encabezado());

        return "facturas/creacion_factura";
    }

    @PostMapping(value = "/crear")
    public String guardarFactura(Encabezado encabezado,Model model) {
        Timestamp timestamp = new Timestamp(System.currentTimeMillis()); //Instanciamos fecha actual para el encabezado de factura.
        encabezado.setFecha(timestamp);
        model.addAttribute("facturaInfo",encabezado);

        if(encabezado.getId()==null){
            encabezadorepository.save(encabezado);
        }

        return "redirect:/factura/agregarproductos/"+encabezado.getId();
    }

}
