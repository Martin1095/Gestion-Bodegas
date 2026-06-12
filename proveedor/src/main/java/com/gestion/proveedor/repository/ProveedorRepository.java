package com.gestion.proveedor.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.gestion.proveedor.model.Proveedor;

@Repository
public interface ProveedorRepository extends JpaRepository<Proveedor,Integer> {

}
