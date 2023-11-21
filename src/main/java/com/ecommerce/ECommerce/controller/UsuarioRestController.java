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

import com.ecommerce.ECommerce.model.Usuname;
import com.ecommerce.ECommerce.response.UsuarioResponseRest;
import com.ecommerce.ECommerce.service.IUsuarioService;

@RestController
@RequestMapping("/api") // uri comun a todas las apis
public class UsuarioRestController {

    @Autowired
    private IUsuarioService service;

    @GetMapping("/usuario/username/{user}")
    public ResponseEntity<UsuarioResponseRest> consultaUsuario(@PathVariable String user) {

        ResponseEntity<UsuarioResponseRest> response = service.buscarPorUser(user);

        return response;
    }

    @GetMapping("/usuario/{id}")
    public ResponseEntity<UsuarioResponseRest> consultaPorId(@PathVariable Integer id) {
        ResponseEntity<UsuarioResponseRest> response = service.buscarPorId(id);
        return response;
    }

    @PostMapping("/usuario")
    public ResponseEntity<UsuarioResponseRest> crear(@RequestBody Usuname request) {
        ResponseEntity<UsuarioResponseRest> response = service.crear(request);
        return response;
    }

    @PutMapping("/usuario/{id}")
    public ResponseEntity<UsuarioResponseRest> actualizar(@RequestBody Usuname request, @PathVariable Integer id) {
        ResponseEntity<UsuarioResponseRest> response = service.actualizar(request, id);
        return response;
    }

    @DeleteMapping("/usuario/{id}")
    public ResponseEntity<UsuarioResponseRest> eliminar(@PathVariable Integer id) {
        ResponseEntity<UsuarioResponseRest> response = service.eliminar(id);
        return response;
    }

}
