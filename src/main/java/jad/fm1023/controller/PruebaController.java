package jad.fm1023.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import org.springframework.web.bind.annotation.GetMapping;



@RestController
@RequestMapping("/prueba")
public class PruebaController {

    @GetMapping
    public String prueba() {
        return "Hola Mundo";
    }
    

}
