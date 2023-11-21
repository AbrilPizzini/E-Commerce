package com.ecommerce.ECommerce.repository;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.ecommerce.ECommerce.model.Usuname;

public interface IUsuarioRepository extends CrudRepository<Usuname, Integer> {

    Optional<Usuname> findByUser(String user);
}
