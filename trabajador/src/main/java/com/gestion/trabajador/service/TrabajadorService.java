package com.gestion.trabajador.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gestion.trabajador.DTO.TrabajadorDTO;
import com.gestion.trabajador.model.Trabajador;
import com.gestion.trabajador.repository.TrabajadorRepository;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class TrabajadorService {

    @Autowired
    private TrabajadorValidaciones trabajadorValidaciones;

    @Autowired
    private TrabajadorRepository trabajadorRepository;

    //metodo para obtener todos los trabajadores, devuelve una lista de TrabajadorDTO
    public List<TrabajadorDTO> obtenerTrabajadores(){
        List<TrabajadorDTO> listaDTOs = new ArrayList<>();
        for (Trabajador trabajador : trabajadorRepository.findAll()) {
            listaDTOs.add(trabajadorValidaciones.convertirADTO(trabajador));
        }
        return listaDTOs;
    }

    
    //metodo para guardar un trabajador, recibe un objeto Trabajador y lo guarda en la base de datos
    public Trabajador guardarTrabajador(Trabajador trabajador) {
        return trabajadorRepository.save(trabajador);
    }

    public TrabajadorDTO obtenerTrabajadorPorId(Integer id) {

        Trabajador trabajador = trabajadorRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Trabajador no encontrado"));
        return trabajadorValidaciones.convertirADTO(trabajador);
    }
}
