package ar.com.facturacion.controller;

import ar.com.facturacion.repositorio.EncabezadoRepositorio;
import ar.com.facturacion.repositorio.ItemRepositorio;
import ar.com.facturacion.repositorio.PieRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/factura")
public class FacturaControl {
    @Autowired
    private EncabezadoRepositorio encabezadoRepositorio;
    private ItemRepositorio itemRepositorio;
    private PieRepositorio pieRepositorio;


}
