package com.ecommerce.ECommerce.response;

import java.util.List;

import com.ecommerce.ECommerce.model.DetallePedido;

public class DetalleResponse {
    private List<DetallePedido> detalle;

    public List<DetallePedido> getDetalle() {
        return detalle;
    }

    public void setDetalle(List<DetallePedido> detalle) {
        this.detalle = detalle;
    }
}
