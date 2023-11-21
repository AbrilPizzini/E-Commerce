package com.ecommerce.ECommerce.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ecommerce.ECommerce.model.DetallePedido;
import com.ecommerce.ECommerce.model.Pedido;
import com.ecommerce.ECommerce.repository.IDetallePedidoRepository;
import com.ecommerce.ECommerce.repository.IPedidoRepository;
import com.ecommerce.ECommerce.response.PedidoResponseRest;

@Service
public class PedidoServiceImpl implements IPedidoService {

    private static final Logger log = LoggerFactory.getLogger(PedidoServiceImpl.class);

    @Autowired
    private IPedidoRepository pedidoRepository;

    @Autowired
    private IDetallePedidoRepository detallePedidoRepository;

    @Override
    @Transactional
    public ResponseEntity<PedidoResponseRest> crear(Pedido pedido) {

        log.info("Inicio método crear Pedido");

        PedidoResponseRest response = new PedidoResponseRest();

        List<Pedido> list = new ArrayList<>();

        try {
            Pedido pedidoGuardado = pedidoRepository.save(pedido);

            if (pedidoGuardado != null) {
                list.add(pedidoGuardado);
                response.getPedidoResponse().setPedido(list);

            } else {
                log.error("Error en grabar pedido");
                response.setMetadata("Respuesta no ok", "-1", "Error al grabar pedido");
                return new ResponseEntity<PedidoResponseRest>(response, HttpStatus.BAD_REQUEST);
            }
        } catch (Exception e) {
            log.error("Error en grabar pedido");
            response.setMetadata("Respuesta no ok", "-1", "Error en grabar pedido");
            return new ResponseEntity<PedidoResponseRest>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        response.setMetadata("Respuesta ok", "00", "Pedido creado");
        return new ResponseEntity<PedidoResponseRest>(response, HttpStatus.OK);

    }

    @Override
    public ResponseEntity<PedidoResponseRest> crearItem(DetallePedido detallePedido, Integer idPedido) {
        log.info("Inicio método crear DetallePedido");

        PedidoResponseRest response = new PedidoResponseRest();

        List<DetallePedido> list = new ArrayList<>();

        try {

            DetallePedido detallePedidoGuardar = detallePedidoRepository.save(detallePedido);
            Optional<Pedido> pedido = pedidoRepository.findById(idPedido);

            if (pedido.isPresent() && detallePedido != null) {
                list.add(detallePedidoGuardar);
                pedido.get().getDetalles().add(detallePedidoGuardar);
            } else {
                log.error("Error en grabar detalle pedido");
                response.setMetadata("Respuesta no ok", "-1", "Error al grabar el detalle pedido");
                return new ResponseEntity<PedidoResponseRest>(response, HttpStatus.BAD_REQUEST);

            }

        } catch (Exception e) {
            log.error("Error al grabar detalle pedido");
            response.setMetadata("Respuesta no ok", "-1", "Detalle pedido no grabado");
            return new ResponseEntity<PedidoResponseRest>(response, HttpStatus.INTERNAL_SERVER_ERROR);

        }

        response.setMetadata("Respuesta ok", "00", "DetallePedido grabado");
        return new ResponseEntity<PedidoResponseRest>(response, HttpStatus.OK);

    }

    @Override
    public ResponseEntity<PedidoResponseRest> actualizar(Pedido pedido, Integer id) {
        log.info("Inicio método actualizar");
        PedidoResponseRest response = new PedidoResponseRest();
        List<Pedido> list = new ArrayList<>();

        try {
            Optional<Pedido> pedidoBuscado = pedidoRepository.findById(id);

            if (pedidoBuscado.isPresent()) {
                pedidoBuscado.get().setUsuname(pedido.getUsuname());
                pedidoBuscado.get().setDetalles(pedido.getDetalles());
                pedidoBuscado.get().setFechaPedido(pedido.getFechaPedido());

                Pedido pedidoActualizar = pedidoRepository.save(pedidoBuscado.get());

                if (pedidoActualizar != null) {
                    response.setMetadata("Respuesta ok", "00", "Pedido actualizado");
                    list.add(pedidoActualizar);
                    response.getPedidoResponse().setPedido(list);
                } else {
                    log.error("error en actualizar pedido");
                    response.setMetadata("Respuesta no ok", "-1", "Pedido no actualizado");
                    return new ResponseEntity<PedidoResponseRest>(response, HttpStatus.BAD_REQUEST);
                }
            } else {
                log.error("error en actualizar pedido");
                response.setMetadata("Respuesta nok", "-1", "Pedido no actualizado");
                return new ResponseEntity<PedidoResponseRest>(response, HttpStatus.NOT_FOUND);
            }

        } catch (Exception e) {
            log.error("error en actualizar pedido", e.getMessage());
            e.getStackTrace();
            response.setMetadata("Respuesta no ok", "-1", "Pedido no actualizado");
            return new ResponseEntity<PedidoResponseRest>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return new ResponseEntity<PedidoResponseRest>(response, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<PedidoResponseRest> eliminar(Integer idPedido) {
        log.info("Inicio metodo eliminar pedido");

        PedidoResponseRest response = new PedidoResponseRest();

        try {
            // eliminar el registro
            pedidoRepository.deleteById(idPedido);
            response.setMetadata("Respuesta ok", "00", "Pedido eliminado");

        } catch (Exception e) {
            log.error("Error en eliminar pedido", e.getMessage());
            e.getStackTrace();
            response.setMetadata("Respuesta no ok", "-1", "Pedido no eliminado");
            return new ResponseEntity<PedidoResponseRest>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return new ResponseEntity<PedidoResponseRest>(response, HttpStatus.OK);
    }

    @Override
    @Transactional
    public ResponseEntity<PedidoResponseRest> eliminarItem(Integer idPedido, Integer idDetalle) {
        PedidoResponseRest response = new PedidoResponseRest();

        try {
            Optional<Pedido> optionalPedido = pedidoRepository.findById(idPedido);

            if (optionalPedido.isPresent()) {

                Pedido pedido = optionalPedido.get();
                List<DetallePedido> detalles = pedido.getDetalles();

                Optional<DetallePedido> detalleAEliminar = detalles.stream()
                        .filter(detalle -> detalle.getId().equals(idDetalle))
                        .findFirst();

                if (detalleAEliminar.isPresent()) {
                    detallePedidoRepository.deleteById(idDetalle);
                    detalles.remove(detalleAEliminar.get());
                    response.setMetadata("Respuesta ok", "00", "Item eliminado");
                    return new ResponseEntity<PedidoResponseRest>(response, HttpStatus.OK);
                } else {
                    response.setMetadata("Respuesta no ok", "-1", "Detalle no encontrado");
                    return new ResponseEntity<PedidoResponseRest>(response, HttpStatus.NOT_FOUND);
                }
            } else {
                response.setMetadata("Respuesta no ok", "-1", "Pedido no encontrado");
                return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
            }

        } catch (Exception e) {
            log.error("Error al eliminar un item del pedido", e.getMessage());
            e.getStackTrace();
            response.setMetadata("Respuesta no ok", "-1", "Item no eliminado");
            return new ResponseEntity<PedidoResponseRest>(response, HttpStatus.INTERNAL_SERVER_ERROR);

        }
    }

    @Override
    @Transactional(readOnly = true)
    public ResponseEntity<PedidoResponseRest> buscarPorId(Integer idPedido) {
        log.info("Inicio del método buscarPorId");

        PedidoResponseRest response = new PedidoResponseRest();

        List<Pedido> list = new ArrayList<>();

        try {
            Optional<Pedido> pedido = pedidoRepository.findById(idPedido);

            if (pedido.isPresent()) {
                list.add(pedido.get());
                response.getPedidoResponse().setPedido(list);
            } else {
                log.error("Error en consultar pedido");
                response.setMetadata("Respuesta nok", "-1", "Pedido no encontrado");
                return new ResponseEntity<PedidoResponseRest>(response, HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            log.error("Error en consultar pedido");
            response.setMetadata("Respuesta nok", "-1", "Pedido no encontrado");
            return new ResponseEntity<PedidoResponseRest>(response, HttpStatus.INTERNAL_SERVER_ERROR);

        }

        response.setMetadata("Respuesta ok", "00", "Pedido encontrado");
        return new ResponseEntity<PedidoResponseRest>(response, HttpStatus.OK);
    }

    @Override
    @Transactional(readOnly = true)
    public ResponseEntity<PedidoResponseRest> buscarPorUsuario(String usuario) {
        log.info("Inicio método buscarPorUsuario");

        PedidoResponseRest response = new PedidoResponseRest();

        try {
            List<Pedido> pedidos = pedidoRepository.findByUsuname_User(usuario);

            if (!pedidos.isEmpty()) {
                response.getPedidoResponse().setPedido(pedidos);
            } else {
                log.error("Error en consultar pedido");
                response.setMetadata("Respuesta no ok", "-1", "Usuario no encontrado");
                return new ResponseEntity<PedidoResponseRest>(response, HttpStatus.NOT_FOUND);

            }

        } catch (Exception e) {
            log.error("Error en consultar pedido");
            response.setMetadata("Respuesta nok", "-1", "Pedido no encontrado");
            return new ResponseEntity<PedidoResponseRest>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        response.setMetadata("Respuesta ok", "00", "Pedido encontrado");
        return new ResponseEntity<PedidoResponseRest>(response, HttpStatus.OK);
    }

    @Override
    @Transactional(readOnly = true)
    public ResponseEntity<PedidoResponseRest> buscarDetalle(Integer idPedido, Integer idDetalle) {
        log.info("Inicio del método buscarDetalle");

        PedidoResponseRest response = new PedidoResponseRest();

        List<DetallePedido> list = new ArrayList<>();

        try {
            Optional<Pedido> optionalPedido = pedidoRepository.findById(idPedido);

            if (optionalPedido.isPresent()) {
                Pedido pedido = optionalPedido.get();

                List<DetallePedido> detalles = pedido.getDetalles();

                Optional<DetallePedido> detalleBuscado = detalles.stream()
                        .filter(detalle -> detalle.getId().equals(idDetalle))
                        .findFirst();

                if (detalleBuscado.isPresent()) {
                    list.add(detalleBuscado.get());
                    response.getDetalleResponse().setDetalle(list);
                    response.setMetadata("Respuesta ok", "00", "Detalle encontrado");
                    return new ResponseEntity<PedidoResponseRest>(response, HttpStatus.OK);
                } else {
                    response.setMetadata("Respuesta no ok", "-1", "Detalle no encontrado");
                    return new ResponseEntity<PedidoResponseRest>(response, HttpStatus.NOT_FOUND);
                }
            } else {
                log.error("Error en consultar detalle pedido");
                response.setMetadata("Respuesta nok", "-1", "Pedido no encontrado");
                return new ResponseEntity<PedidoResponseRest>(response, HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            log.error("Error en consultar pedido");
            response.setMetadata("Respuesta nok", "-1", "Pedido no encontrado");
            return new ResponseEntity<PedidoResponseRest>(response, HttpStatus.INTERNAL_SERVER_ERROR);

        }

    }

}
