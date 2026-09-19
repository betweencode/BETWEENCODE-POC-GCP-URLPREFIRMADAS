package com.betweencode.urlprefirmadas.subirArchivos.domain.core;

import com.betweencode.urlprefirmadas.subirArchivos.domain.incoming.subiendoArchivo;
import com.betweencode.urlprefirmadas.subirArchivos.infraestructure.modelos.ArchivosHash;
import com.betweencode.urlprefirmadas.subirArchivos.infraestructure.RepositoryHashCreation;
import com.google.cloud.storage.BlobInfo;
import com.google.cloud.storage.Storage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.UUID;


@Service
public class ArchivocCore implements subiendoArchivo {

    @Autowired
    private RepositoryHashCreation respository;

    @Autowired
    private Storage storage;

    @Value("${gcp.storage.bucket-name}")
    private String bucketName;

    @Override
    public ArchivosHash subirArchivo(String carpeta, MultipartFile file) {
        if (file.isEmpty()) {
           throw  new RuntimeException();
        }

        String folder = normalizarCarpeta(carpeta);
        String objectName = folder.concat("/").concat(file.getName());

        BlobInfo blobInfo = BlobInfo.newBuilder(bucketName, objectName)
                .setContentType(file.getContentType())
                .build();

        try {
            storage.createFrom(blobInfo, file.getInputStream());
        } catch (IOException exception) {
            throw new IllegalStateException("No fue posible guardar el archivo en Cloud Storage", exception);
        }

        ArchivosHash archivoHash = new ArchivosHash();
        archivoHash.setTokenUid(UUID.randomUUID().toString());
        archivoHash.setCarpeta(folder);
        archivoHash.setArchivo(file.getName());
        archivoHash.setBanActivo(true);

        try {
            return respository.save(archivoHash);
        } catch (RuntimeException exception) {
            storage.delete(bucketName, objectName);
            throw exception;
        }
    }

    private String normalizarCarpeta(String carpeta) {
        if (!StringUtils.hasText(carpeta)) {
            return "";
        }

        String folder = carpeta.trim().replace('\\', '/');
        folder = folder.replaceAll("^/+|/+$", "");
        if (folder.equals("..") || folder.startsWith("../") || folder.contains("/../")) {
            throw new IllegalArgumentException("La carpeta no puede contener '..'");
        }
        return folder;
    }
}
