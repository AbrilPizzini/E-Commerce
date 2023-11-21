package com.ecommerce.ECommerce.response;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Clase que añade metadata a la respuesta
 */
public class ResponseRest {

    private ArrayList<HashMap<String, String>> metadata = new ArrayList<>();

    public ArrayList<HashMap<String, String>> getMetadata() {
        return metadata;
    }

    public void setMetadata(String tipo, String codigo, String date) {
        HashMap<String, String> mapa = new HashMap<String, String>();

        mapa.put("codigo", codigo);
        mapa.put("tipo", tipo);
        mapa.put("date", date);

        metadata.add(mapa);
    }

}
