package ar.com.facturacion.controller;

import ar.com.facturacion.dominio.Item;
import ar.com.facturacion.dominio.Pie;
import ar.com.facturacion.repositorio.EncabezadoRepositorio;
import ar.com.facturacion.repositorio.PieRepositorio;
import ar.com.facturacion.servicios.FacturacionServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequestMapping("/factura")
public class PieControl {
    @Autowired
    private PieRepositorio pierepository;
    @Autowired
    private EncabezadoRepositorio encabezadorepository;

    @GetMapping(value = "/observaciones/{id}")
    public String establecerPie(Model model, @PathVariable Long id){
        model.addAttribute("pie",new Pie());
        model.addAttribute("titulo","Finalizando Factura");
        return "facturas/registropie";


    }
    @PostMapping(value = "/observaciones/{idd}")
    public String confirmarFactura(@PathVariable Long idd, Pie pie, RedirectAttributes redirectAttributes){
        List<Item> items=encabezadorepository.findById(idd).get().getItems();
        pie.setTotal(FacturacionServicio.calcularTotal(items));
        pie.setEncabezado(encabezadorepository.getOne(idd));
        if(pie.getId()==null){
            pierepository.save(pie);
        }
        redirectAttributes.addFlashAttribute("mensaje","¡Factura Creada Correctamente!");
        return "redirect:/factura/misfacturas";

    }




}
