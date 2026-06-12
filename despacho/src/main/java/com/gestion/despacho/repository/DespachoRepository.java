package com.gestion.despacho.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.gestion.despacho.model.Despacho;

public interface DespachoRepository extends JpaRepository<Despacho,Integer> {

}
