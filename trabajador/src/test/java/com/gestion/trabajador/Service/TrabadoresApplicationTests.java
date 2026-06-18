package com.gestion.trabajador.Service;


import java.util.Arrays;
import java.util.List;

import com.gestion.trabajador.DTO.TrabajadorDTO;
import com.gestion.trabajador.model.Trabajador;
import com.gestion.trabajador.repository.TrabajadorRepository;
import com.gestion.trabajador.service.TrabajadorService;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import static org.mockito.Mockito.when;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class TrabajadorServiceTest {

    @Mock
    private TrabajadorRepository trabajadorRepository;

    @InjectMocks
    private TrabajadorService trabajadorService;

    //test para obtener todos los trabajadores
    @Test
    void obtenerTodos() {

        when(trabajadorRepository.findAll())
                .thenReturn(Arrays.asList(new Trabajador(), new Trabajador()));

        List<TrabajadorDTO> lista = trabajadorService.obtenerTodos();

        assertEquals(2, lista.size());

        verify(trabajadorRepository).findAll();
    }
    //test para guardar a los trabajadores
    @Test
    void guardarTrabajador() {

        Trabajador trabajador = new Trabajador();

        when(trabajadorRepository.save(trabajador))
                .thenReturn(trabajador);

        Trabajador resultado = trabajadorService.guardarTrabajador(trabajador);

        assertNotNull(resultado);

        verify(trabajadorRepository).save(trabajador);
    }
}
