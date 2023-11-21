package com.ecommerce.ECommerce.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.ecommerce.ECommerce.model.Pedido;

public interface IPedidoRepository extends CrudRepository<Pedido, Integer> {

    List<Pedido> findByUsuname_User(String username);

}
