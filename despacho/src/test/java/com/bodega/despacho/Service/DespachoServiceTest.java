package com.bodega.despacho.Service;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import com.gestion.despacho.DTO.DespachoDTO;
import com.gestion.despacho.model.Despacho;
import com.gestion.despacho.repository.DespachoRepository;
import com.gestion.despacho.service.DespachoService;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class DespachoServiceTest {

    @Mock
    private DespachoRepository despachoRepository;

    @InjectMocks
    private DespachoService despachoService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testListar() {

        List<Despacho> lista = Arrays.asList(
                new Despacho(),
                new Despacho());

        when(despachoRepository.findAll()).thenReturn(lista);

        List<DespachoDTO> resultado = despachoService.obtenerDespachos();

        assertEquals(2, resultado.size());

        verify(despachoRepository, times(1)).findAll();
    }

    @Test
    void testBuscar() {

        Despacho despacho = new Despacho();

        when(despachoRepository.findById(1))
                .thenReturn(Optional.of(despacho));

        DespachoDTO resultado = despachoService.buscarDespachoPorId(1);

        assertNotNull(resultado);

        verify(despachoRepository, times(1)).findById(1);
    }

    @Test
    void testGuardar() {

        Despacho despacho = new Despacho();

        when(despachoRepository.save(despacho))
                .thenReturn(despacho);

        Object resultado = despachoService.guardarDespacho(despacho);

        assertNotNull(resultado);

        verify(despachoRepository, times(1)).save(despacho);
    }
}
