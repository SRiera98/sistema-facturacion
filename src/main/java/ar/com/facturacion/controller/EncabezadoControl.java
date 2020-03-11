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
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;


@Controller
@RequestMapping("/factura")
public class EncabezadoControl {
    @Autowired
    private ClienteRepositorio clienterepository;
    @Autowired
    private EncabezadoRepositorio encabezadorepository;

    @GetMapping("/crear")
    public String creacionFactura(Model model) {
        List<Cliente> clientes=clienterepository.findByVisibilidad();
        model.addAttribute("titulo", "Listado de Clientes");
        model.addAttribute("data", clientes);
        model.addAttribute("encabezado",new Encabezado());

        return "facturas/creacion_factura";
    }

    @PostMapping(value = "/crear")
    public String guardarFactura(Encabezado encabezado, Model model, RedirectAttributes redirectAttributes) {
        Timestamp timestamp = new Timestamp(System.currentTimeMillis()); //Instanciamos fecha actual para el encabezado de factura.
        encabezado.setFecha(timestamp);

        Optional<Encabezado> optionalEncabezado= encabezadorepository.findByNumero(encabezado.getNumero());

        if(!optionalEncabezado.isPresent()){
            if(encabezado.getId()==null){
                encabezadorepository.save(encabezado);
            }
            return "redirect:/factura/agregarproductos/"+encabezado.getId();
        }else{
            redirectAttributes.addFlashAttribute("mensaje","¡Codigo de Factura Ya Utilizado!");
            return "redirect:/factura/misfacturas";
        }


    }

}
