package com.betweencode.urlprefirmadas.subirArchivos.application;

import com.betweencode.urlprefirmadas.subirArchivos.domain.incoming.subiendoArchivo;
import com.betweencode.urlprefirmadas.subirArchivos.infraestructure.modelos.ArchivosHash;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api/subir-archivos")
public class SubirArchivosController {


    @Autowired
    private subiendoArchivo subirArchivosCore;

    @PostMapping("/upload")
    public ArchivosHash subirArchivo(
            @RequestParam("carpeta") String carpeta,
            @RequestParam("archivo") MultipartFile file) {
        return subirArchivosCore.subirArchivo(carpeta,file);

    }
}
