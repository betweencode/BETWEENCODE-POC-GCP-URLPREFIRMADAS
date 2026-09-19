package com.betweencode.urlprefirmadas.subirArchivos.application;


import com.betweencode.urlprefirmadas.subirArchivos.domain.incoming.RecuperacionListado;
import com.betweencode.urlprefirmadas.subirArchivos.domain.incoming.subiendoArchivo;
import com.betweencode.urlprefirmadas.subirArchivos.infraestructure.RepositoryHashCreation;
import com.betweencode.urlprefirmadas.subirArchivos.infraestructure.modelos.ArchivosHash;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@RequiredArgsConstructor
@Controller
@RequestMapping
public class RenderizadoHtmlPoc {



    private final subiendoArchivo subirArchivosCore;



    private final RecuperacionListado recuperacionListado;



    @GetMapping("/archivos")
    public String listado(Model model, HttpServletRequest request) {
        model.addAttribute("archivos", recuperacionListado.obtenerListdo());
        model.addAttribute("servidor", obtenerServidor(request));
        return "archivos/listado";
    }

    @GetMapping("/archivos/tabla")
    public String tablaEntity(Model model) {
        model.addAttribute("archivos", recuperacionListado.obtenerListdo());
        return "archivos/tabla-entity";
    }

    @PostMapping("/archivos/subir")
    public String subirDesdePagina(
            @RequestParam("carpeta") String carpeta,
            @RequestParam("archivo") MultipartFile file,
            RedirectAttributes redirectAttributes) {
        ArchivosHash archivo = subirArchivosCore.subirArchivo(carpeta, file);
        redirectAttributes.addFlashAttribute(
                "mensaje", "Archivo subido correctamente: " + archivo.getArchivo());
        return "redirect:/archivos";
    }


    private String obtenerServidor(HttpServletRequest request) {
        StringBuilder servidor = new StringBuilder(request.getScheme())
                .append("://")
                .append(request.getServerName());
        if (!(request.getScheme().equals("http") && request.getServerPort() == 80)
                && !(request.getScheme().equals("https") && request.getServerPort() == 443)) {
            servidor.append(":").append(request.getServerPort());
        }
        return servidor.toString();
    }
}
