package ar.com.facturacion.controller;

import ar.com.facturacion.repositorio.PieRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/facturacion")
public class PieControl {
    @Autowired
    private PieRepositorio pierepository;

   /* @GetMapping(value = "/observaciones/{id}"){





    }*/




}
