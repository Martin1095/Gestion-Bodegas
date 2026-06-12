package com.gestion.recepcion.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.gestion.recepcion.model.Recepcion;

@Repository
public interface RecepcionRepository extends JpaRepository<Recepcion, Integer> {

}