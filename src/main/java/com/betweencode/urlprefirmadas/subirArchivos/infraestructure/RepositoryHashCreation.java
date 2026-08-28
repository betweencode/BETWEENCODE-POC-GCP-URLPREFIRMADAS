package com.betweencode.urlprefirmadas.subirArchivos.infraestructure;

import com.betweencode.urlprefirmadas.subirArchivos.infraestructure.modelos.ArchivosHash;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RepositoryHashCreation extends JpaRepository<ArchivosHash,String> {
}
