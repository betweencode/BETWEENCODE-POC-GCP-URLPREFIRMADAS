package com.betweencode.urlprefirmadas.subirArchivos.domain.incoming;

import com.betweencode.urlprefirmadas.subirArchivos.infraestructure.modelos.ArchivosHash;
import org.springframework.web.multipart.MultipartFile;

public interface subiendoArchivo {

    ArchivosHash subirArchivo(String carpeta, MultipartFile file);
}
