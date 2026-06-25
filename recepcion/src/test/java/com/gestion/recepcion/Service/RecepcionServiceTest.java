package com.gestion.recepcion.Service;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;

import com.gestion.recepcion.DTO.RecepcionDTO;
import com.gestion.recepcion.model.Recepcion;
import com.gestion.recepcion.repository.RecepcionRepository;
import com.gestion.recepcion.service.RecepcionService;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class RecepcionServiceTest {

    @Mock
    private RecepcionRepository recepcionRepository;

    @InjectMocks
    private RecepcionService recepcionService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testObtenerTodos() {

        when(recepcionRepository.findAll())
                .thenReturn(Arrays.asList(
                        new Recepcion(),
                        new Recepcion()));

        List<RecepcionDTO> resultado =
                recepcionService.obtenerTodos();

        assertEquals(2, resultado.size());

        verify(recepcionRepository, times(1)).findAll();
    }

    @Test
    void testGuardarRecepcion() {

        Recepcion recepcion = new Recepcion();

        when(recepcionRepository.save(recepcion))
                .thenReturn(recepcion);

        Recepcion resultado =
                recepcionService.guardarRecepcion(recepcion);

        assertNotNull(resultado);

        verify(recepcionRepository, times(1)).save(recepcion);
    }
}
