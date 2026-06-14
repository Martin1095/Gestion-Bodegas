package com.gestion.cliente.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.gestion.cliente.model.Cliente;
@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Integer> {

    // Buscar clientes por RUT
    Cliente findByRut(String rut);
}
