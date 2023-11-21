package com.ecommerce.ECommerce.response;

import java.util.List;

import com.ecommerce.ECommerce.model.Usuname;

public class UsuarioResponse {

    private List<Usuname> usuario;

    public List<Usuname> getUsuario() {
        return usuario;
    }

    public void setUsuario(List<Usuname> usuario) {
        this.usuario = usuario;
    }

}
