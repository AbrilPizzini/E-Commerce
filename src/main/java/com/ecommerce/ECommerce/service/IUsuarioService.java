package com.ecommerce.ECommerce.service;

import org.springframework.http.ResponseEntity;

import com.ecommerce.ECommerce.model.Usuname;
import com.ecommerce.ECommerce.response.UsuarioResponseRest;

public interface IUsuarioService {

    public ResponseEntity<UsuarioResponseRest> buscarPorId(Integer id);

    public ResponseEntity<UsuarioResponseRest> buscarPorUser(String user);

    public ResponseEntity<UsuarioResponseRest> crear(Usuname usuario);

    public ResponseEntity<UsuarioResponseRest> actualizar(Usuname usuario, Integer id);

    public ResponseEntity<UsuarioResponseRest> eliminar(Integer id);

}
