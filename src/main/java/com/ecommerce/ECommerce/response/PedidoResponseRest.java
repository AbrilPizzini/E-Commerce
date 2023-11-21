package com.ecommerce.ECommerce.response;

public class PedidoResponseRest extends ResponseRest {

    private PedidoResponse pedidoResponse = new PedidoResponse();

    private DetalleResponse detalleResponse = new DetalleResponse();

    public PedidoResponse getPedidoResponse() {
        return pedidoResponse;
    }

    public DetalleResponse getDetalleResponse() {
        return detalleResponse;
    }

    public void setDetalleResponse(DetalleResponse detalleResponse) {
        this.detalleResponse = detalleResponse;
    }

    public void setPedidoResponse(PedidoResponse pedidoResponse) {
        this.pedidoResponse = pedidoResponse;
    }

}
