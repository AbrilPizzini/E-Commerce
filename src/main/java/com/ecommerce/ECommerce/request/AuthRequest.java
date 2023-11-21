package com.ecommerce.ECommerce.request;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonProperty;

public class AuthRequest implements Serializable {

    private String usuario;

    @JsonProperty(value = "contrasenia")
    private String contrasenia;

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getContrasenia() {
        return contrasenia;
    }

    public void setContrasenia(String contrasenia) {
        this.contrasenia = contrasenia;
    }

}
