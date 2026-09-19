package com.betweencode.urlprefirmadas.subirArchivos.application;

import com.betweencode.urlprefirmadas.subirArchivos.domain.incoming.subiendoArchivo;
import com.betweencode.urlprefirmadas.subirArchivos.infraestructure.modelos.ArchivosHash;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;


@RequiredArgsConstructor
@RestController
@RequestMapping("/api/subir-archivos")
public class SubirArchivosController {



    private final subiendoArchivo subirArchivosCore;






    @PostMapping("/upload")
    public ArchivosHash subirArchivo(
            @RequestParam("carpeta") String carpeta,
            @RequestParam("archivo") MultipartFile file) {
        return subirArchivosCore.subirArchivo(carpeta,file);

    }


}
