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

import com.ecommerce.ECommerce.model.Usuname;
import com.ecommerce.ECommerce.repository.IUsuarioRepository;
import com.ecommerce.ECommerce.response.UsuarioResponseRest;

@Service
public class UsuarioServiceImpl implements IUsuarioService {

    private static final Logger log = LoggerFactory.getLogger(UsuarioServiceImpl.class);

    @Autowired
    private IUsuarioRepository usuarioRepository;

    @Override
    @Transactional(readOnly = true)
    public ResponseEntity<UsuarioResponseRest> buscarPorId(Integer id) {
        log.info("Inicio método buscarPorId");

        UsuarioResponseRest response = new UsuarioResponseRest();

        List<Usuname> list = new ArrayList<>();

        try {
            Optional<Usuname> usuario = usuarioRepository.findById(id);

            if (usuario.isPresent()) {
                list.add(usuario.get());
                response.getUsuarioResponse().setUsuario(list);

            } else {
                log.error("Error en consultar usuario");
                response.setMetadata("Respuesta nok", "-1", "Usuario no encontrado");
                return new ResponseEntity<UsuarioResponseRest>(response, HttpStatus.NOT_FOUND);

            }

        } catch (Exception e) {
            log.error("Error en consultar usuario");
            response.setMetadata("Respuesta nok", "-1", "Usuario no encontrada");
            return new ResponseEntity<UsuarioResponseRest>(response, HttpStatus.INTERNAL_SERVER_ERROR);

        }

        response.setMetadata("Respuesta ok", "00", "Usuario encontrado");
        return new ResponseEntity<UsuarioResponseRest>(response, HttpStatus.OK);

    }

    @Override
    @Transactional(readOnly = true)
    public ResponseEntity<UsuarioResponseRest> buscarPorUser(String user) {
        log.info("Inicio método buscarPorUser");

        UsuarioResponseRest response = new UsuarioResponseRest();

        List<Usuname> list = new ArrayList<>();

        try {
            Optional<Usuname> usuario = usuarioRepository.findByUser(user);

            if (usuario.isPresent()) {
                list.add(usuario.get());
                response.getUsuarioResponse().setUsuario(list);

            } else {
                log.error("Error en consultar usuario");
                response.setMetadata("Respuesta nok", "-1", "Usuario no encontrado");
                return new ResponseEntity<UsuarioResponseRest>(response, HttpStatus.NOT_FOUND);

            }

        } catch (Exception e) {
            log.error("Error en consultar usuario");
            response.setMetadata("Respuesta nok", "-1", "Usuario no encontrada");
            return new ResponseEntity<UsuarioResponseRest>(response, HttpStatus.INTERNAL_SERVER_ERROR);

        }

        response.setMetadata("Respuesta ok", "00", "Usuario encontrado");
        return new ResponseEntity<UsuarioResponseRest>(response, HttpStatus.OK);

    }

    @Override
    @Transactional
    public ResponseEntity<UsuarioResponseRest> crear(Usuname usuario) {
        log.info("Inicio método crear usuario");

        UsuarioResponseRest response = new UsuarioResponseRest();

        List<Usuname> list = new ArrayList<>();

        try {

            Usuname usuarioGuardada = usuarioRepository.save(usuario);

            if (usuarioGuardada != null) {
                list.add(usuarioGuardada);
                response.getUsuarioResponse().setUsuario(list);
            } else {
                log.error("Error en grabar usuario");
                response.setMetadata("Respuesta nok", "-1", "Usuario no guardado");
                return new ResponseEntity<UsuarioResponseRest>(response, HttpStatus.BAD_REQUEST);
            }

        } catch (Exception e) {
            log.error("Error en grabar usuario");
            response.setMetadata("Respuesta nok", "-1", "Error al grabar usuario");
            return new ResponseEntity<UsuarioResponseRest>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        response.setMetadata("Respuesta ok", "00", "Usuario cread");
        return new ResponseEntity<UsuarioResponseRest>(response, HttpStatus.OK);

    }

    @Override
    @Transactional
    public ResponseEntity<UsuarioResponseRest> actualizar(Usuname usuario, Integer id) {
        log.info("Inicio método actualizar");
        UsuarioResponseRest response = new UsuarioResponseRest();
        List<Usuname> list = new ArrayList<>();

        try {
            Optional<Usuname> usuarioBuscado = usuarioRepository.findById(id);

            if (usuarioBuscado.isPresent()) {
                usuarioBuscado.get().setUser(usuario.getUser());
                usuarioBuscado.get().setPass(usuario.getPass());
                usuarioBuscado.get().setTipoUsuario(usuario.getTipoUsuario());

                Usuname usunameActualizar = usuarioRepository.save(usuarioBuscado.get());
                if (usunameActualizar != null) {
                    response.setMetadata("Respuesta ok", "00", "Usuario actualizado");
                    list.add(usunameActualizar);
                    response.getUsuarioResponse().setUsuario(list);
                } else {
                    log.error("error en actualizar usuario");
                    response.setMetadata("Respuesta nok", "-1", "Usuario no actualizado");
                    return new ResponseEntity<UsuarioResponseRest>(response, HttpStatus.BAD_REQUEST);
                }
            } else {
                log.error("error en actualizar usuario");
                response.setMetadata("Respuesta nok", "-1", "Usuario no actualizado");
                return new ResponseEntity<UsuarioResponseRest>(response, HttpStatus.NOT_FOUND);

            }

        } catch (Exception e) {
            log.error("error en actualizar usuario", e.getMessage());
            e.getStackTrace();
            response.setMetadata("Respuesta nok", "-1", "Usuario no actualizada");
            return new ResponseEntity<UsuarioResponseRest>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return new ResponseEntity<UsuarioResponseRest>(response, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<UsuarioResponseRest> eliminar(Integer id) {
        log.info("Inicio metodo eliminar usuario");

        UsuarioResponseRest response = new UsuarioResponseRest();

        try {
            // eliminar el registro
            usuarioRepository.deleteById(id);
            response.setMetadata("Respuesta ok", "00", "Usuario eliminada");
        } catch (Exception e) {
            log.error("Error en eliminar usuario", e.getMessage());
            e.getStackTrace();
            response.setMetadata("Respuesta nok", "-1", "Usuario no eliminado");
            return new ResponseEntity<UsuarioResponseRest>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return new ResponseEntity<UsuarioResponseRest>(response, HttpStatus.OK);
    }

}
