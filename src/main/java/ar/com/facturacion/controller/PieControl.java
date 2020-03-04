package ar.com.facturacion.controller;

import ar.com.facturacion.dominio.Item;
import ar.com.facturacion.dominio.Pie;
import ar.com.facturacion.repositorio.EncabezadoRepositorio;
import ar.com.facturacion.repositorio.PieRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.math.BigDecimal;
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
    public String confirmarFactura(@PathVariable Long idd,Pie pie){
        List<Item> items=encabezadorepository.findById(idd).get().getItems();
        BigDecimal total=new BigDecimal(0);
        for (Item i: items) {
            total=total.add(i.getSubTotal());
        }
        pie.setTotal(total);
        pie.setEncabezado(encabezadorepository.getOne(idd));
        if(pie.getId()==null){
            pierepository.save(pie);
        }
        return "redirect:/";

    }




}
