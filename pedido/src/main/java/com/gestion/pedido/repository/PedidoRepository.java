package com.gestion.pedido.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.gestion.pedido.model.Pedido;


@Repository
public interface PedidoRepository extends JpaRepository<Pedido, Integer> {
}

