package com.betweencode.urlprefirmadas.subirArchivos.domain.incoming;

import java.net.URL;

public interface GeneracionUrlPrefirmada {

    URL generar(String hashUid);
}
