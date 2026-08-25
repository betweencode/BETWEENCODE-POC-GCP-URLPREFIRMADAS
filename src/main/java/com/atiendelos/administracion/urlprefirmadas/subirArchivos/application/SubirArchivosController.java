package com.atiendelos.administracion.urlprefirmadas.subirArchivos.application;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/subir-archivos")
public class SubirArchivosController {

    @GetMapping("/hola")
    public String helloWorld() {
        return "Hello World";
    }
}
