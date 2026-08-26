package com.atiendelos.administracion.urlprefirmadas.subirArchivos.infraestructure.modelos;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "archivos_hash")
public class ArchivosHash {
    @Id
    @Column(name = "token_uid", nullable = false)
    private String tokenUid;

    @Column(name = "carpeta")
    private String carpeta;

    @Column(name = "archivo")
    private String archivo;

    @Column(name = "ban_activo")
    private Boolean banActivo;

    public String getTokenUid() {
        return tokenUid;
    }

    public void setTokenUid(String tokenUid) {
        this.tokenUid = tokenUid;
    }

    public String getCarpeta() {
        return carpeta;
    }

    public void setCarpeta(String carpeta) {
        this.carpeta = carpeta;
    }

    public String getArchivo() {
        return archivo;
    }

    public void setArchivo(String archivo) {
        this.archivo = archivo;
    }

    public Boolean getBanActivo() {
        return banActivo;
    }

    public void setBanActivo(Boolean banActivo) {
        this.banActivo = banActivo;
    }

}