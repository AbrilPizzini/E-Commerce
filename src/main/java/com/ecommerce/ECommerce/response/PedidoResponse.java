package com.ecommerce.ECommerce.response;

import java.util.List;

import com.ecommerce.ECommerce.model.Pedido;

public class PedidoResponse {

    private List<Pedido> pedido;

    public List<Pedido> getPedido() {
        return pedido;
    }

    public void setPedido(List<Pedido> pedido) {
        this.pedido = pedido;
    }

}
