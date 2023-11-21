package com.ecommerce.ECommerce.service;

import org.springframework.http.ResponseEntity;

import com.ecommerce.ECommerce.model.DetallePedido;
import com.ecommerce.ECommerce.model.Pedido;
import com.ecommerce.ECommerce.response.PedidoResponseRest;

public interface IPedidoService {

    public ResponseEntity<PedidoResponseRest> crear(Pedido pedido);

    public ResponseEntity<PedidoResponseRest> crearItem(DetallePedido detallePedido, Integer idPedido);

    public ResponseEntity<PedidoResponseRest> actualizar(Pedido pedido, Integer id);

    public ResponseEntity<PedidoResponseRest> eliminar(Integer idPedido);

    public ResponseEntity<PedidoResponseRest> eliminarItem(Integer idPedido, Integer idDetalle);

    public ResponseEntity<PedidoResponseRest> buscarPorId(Integer idPedido);

    public ResponseEntity<PedidoResponseRest> buscarPorUsuario(String usuario);

    public ResponseEntity<PedidoResponseRest> buscarDetalle(Integer idPedido, Integer idDetalle);
}
