package com.betweencode.urlprefirmadas.subirArchivos.domain.core;

import com.betweencode.urlprefirmadas.subirArchivos.domain.incoming.subiendoArchivo;
import com.betweencode.urlprefirmadas.subirArchivos.infraestructure.modelos.ArchivosHash;
import com.betweencode.urlprefirmadas.subirArchivos.infraestructure.RepositoryHashCreation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.UUID;


@Service
public class ArchivocCore implements subiendoArchivo {

    @Autowired
    private RepositoryHashCreation respository;

    @Override
    public ArchivosHash subirArchivo(String carpeta, MultipartFile file) {
        if (file.isEmpty()) {
           throw  new RuntimeException();
        }

        ArchivosHash archivoHash = new ArchivosHash();
        archivoHash.setTokenUid(UUID.randomUUID().toString());
        archivoHash.setCarpeta(carpeta);
        archivoHash.setArchivo(file.getOriginalFilename());
        archivoHash.setBanActivo(true);

        ArchivosHash savedArchivo = respository.save(archivoHash);

        return savedArchivo;
    }
}
