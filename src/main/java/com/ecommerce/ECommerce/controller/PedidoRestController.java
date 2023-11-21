package com.ecommerce.ECommerce.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ecommerce.ECommerce.model.DetallePedido;
import com.ecommerce.ECommerce.model.Pedido;
import com.ecommerce.ECommerce.response.PedidoResponseRest;
import com.ecommerce.ECommerce.service.IPedidoService;

@RestController
@RequestMapping("/api")
public class PedidoRestController {

    @Autowired
    private IPedidoService service;

    @PostMapping("/pedido")
    public ResponseEntity<PedidoResponseRest> crear(@RequestBody Pedido request) {
        ResponseEntity<PedidoResponseRest> response = service.crear(request);
        return response;
    }

    @PostMapping("/pedido/{idPedido}/detalle")
    public ResponseEntity<PedidoResponseRest> crearItem(@RequestBody DetallePedido request,
            @PathVariable Integer idPedido) {
        ResponseEntity<PedidoResponseRest> response = service.crearItem(request, idPedido);
        return response;
    }

    @PutMapping("/pedido/{idPedido}")
    public ResponseEntity<PedidoResponseRest> actualizar(@RequestBody Pedido request, @PathVariable Integer idPedido) {
        ResponseEntity<PedidoResponseRest> response = service.actualizar(request, idPedido);
        return response;
    }

    @DeleteMapping("/pedido/{idPedido}")
    public ResponseEntity<PedidoResponseRest> eliminar(@PathVariable Integer idPedido) {
        ResponseEntity<PedidoResponseRest> response = service.eliminar(idPedido);
        return response;
    }

    @DeleteMapping("/pedido/{idPedido}/detalle/{idDetalle}")
    public ResponseEntity<PedidoResponseRest> eliminarItem(@PathVariable Integer idPedido,
            @PathVariable Integer idDetalle) {
        ResponseEntity<PedidoResponseRest> response = service.eliminarItem(idPedido, idDetalle);
        return response;
    }

    @GetMapping("/pedido/{idPedido}")
    public ResponseEntity<PedidoResponseRest> consultar(@PathVariable Integer idPedido) {
        ResponseEntity<PedidoResponseRest> response = service.buscarPorId(idPedido);
        return response;
    }

    @GetMapping("/pedido/usuario/{user}")
    public ResponseEntity<PedidoResponseRest> consultaPorUsuario(@PathVariable String user) {
        ResponseEntity<PedidoResponseRest> response = service.buscarPorUsuario(user);
        return response;
    }

    @GetMapping("/pedido/{idPedido}/detalle/{idDetalle}")
    public ResponseEntity<PedidoResponseRest> consultarDetalle(@PathVariable Integer idPedido,
            @PathVariable Integer idDetalle) {
        ResponseEntity<PedidoResponseRest> response = service.buscarDetalle(idPedido, idDetalle);
        return response;
    }

}
