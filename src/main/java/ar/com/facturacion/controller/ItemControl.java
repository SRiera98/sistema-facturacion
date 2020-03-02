package ar.com.facturacion.controller;

import ar.com.facturacion.dominio.Item;
import ar.com.facturacion.dominio.Producto;
import ar.com.facturacion.repositorio.EncabezadoRepositorio;
import ar.com.facturacion.repositorio.ItemRepositorio;
import ar.com.facturacion.repositorio.ProductoRepositorio;
import org.apache.catalina.Store;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/factura")
public class ItemControl {
    @Autowired
    private ItemRepositorio itemrepository;
    @Autowired
    private ProductoRepositorio productorepository;
    @Autowired
    private EncabezadoRepositorio encabezadorepository;

    @GetMapping(value = "/establecer_productos/{id}")
    public String indicarProductos(Model model, @PathVariable Long id){

        List<Producto> listaproductos=null;
        listaproductos=productorepository.findByEstado();

        model.addAttribute("titulo", "Selección de Productos");
        model.addAttribute("productos",listaproductos);

        model.addAttribute("item", new Item());


        return "facturas/seleccion_productos";
    }


    @PostMapping(value = "/establecer_productos/{ided}")
    public String procesarProductos(@PathVariable Long ided, Item item,Model model){
        System.out.println("----------------------------------------------------ENTROOOOO-------------------------------\n\n");
        if(item.getId()==null){
            BigDecimal cantidad=item.getCantidad();
            BigDecimal precio=item.getPrecioUnitario();
            item.setSubTotal(cantidad.multiply(precio));
            item.setEncabezado(encabezadorepository.getOne(ided));
            itemrepository.save(item);
        }
        System.out.println("ITEM\n\n\n"+item+"\n\n\n");
        return "redirect:/factura/establecer_productos/"+ided;
    }

}
