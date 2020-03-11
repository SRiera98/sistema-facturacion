package ar.com.facturacion.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeControl {

    @GetMapping(value = "/")
    private String inicio(){
        return "index/main";
    }

}
