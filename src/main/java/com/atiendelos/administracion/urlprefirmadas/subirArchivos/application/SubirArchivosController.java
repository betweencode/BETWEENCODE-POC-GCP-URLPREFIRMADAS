package com.atiendelos.administracion.urlprefirmadas.subirArchivos.application;

import com.atiendelos.administracion.urlprefirmadas.subirArchivos.domain.incoming.subiendoArchivo;
import com.atiendelos.administracion.urlprefirmadas.subirArchivos.infraestructure.modelos.ArchivosHash;
import com.atiendelos.administracion.urlprefirmadas.subirArchivos.infraestructure.modelos.RepositoryHashCreation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.UUID;

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
