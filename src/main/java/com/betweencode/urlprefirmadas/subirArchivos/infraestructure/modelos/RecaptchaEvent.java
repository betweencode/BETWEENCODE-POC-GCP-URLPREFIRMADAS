package com.betweencode.urlprefirmadas.subirArchivos.infraestructure.modelos;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@Builder
@NoArgsConstructor
public class RecaptchaEvent {

    private  String token;
    private  String expectedAction;
    private  String siteKey;

}
