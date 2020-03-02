package ar.com.facturacion.controller;

import ar.com.facturacion.dominio.Item;
import ar.com.facturacion.dominio.Producto;
import ar.com.facturacion.repositorio.ItemRepositorio;
import ar.com.facturacion.repositorio.ProductoRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/factura")
public class ItemControl {
    @Autowired
    private ItemRepositorio itemrepository;
    @Autowired
    private ProductoRepositorio productorepository;

    @GetMapping(value = "/establecer_productos")
    public String indicarProductos(Model model){
        List<Producto> listaproductos=null;
        List<Producto> seleccionprod=null;
        listaproductos=productorepository.findByEstado();
        Map<BigDecimal,Producto> productos=new HashMap<BigDecimal, Producto>();
        model.addAttribute("map",productos);

        model.addAttribute("titulo", "Selección de Productos");
        model.addAttribute("productos",listaproductos);

        model.addAttribute("item",new Item());

        model.addAttribute("seleccion",seleccionprod);

        return "facturas/seleccion_productos";
    }


    @PostMapping(value = "/establecer_productos")
    public String procesarProductos(Item item,Model model){
        System.out.println(item);
        return "facturas/mis_facturas";
    }

}
