package com.gestion.bodega.Service;


import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import com.gestion.bodega.model.Bodega;
import com.gestion.bodega.repository.BodegaRepository;
import com.gestion.bodega.service.BodegaService;

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
public class BodegasApplicationTests {

    @Mock
    private BodegaRepository repo;

    @InjectMocks
    private BodegaService service;
    // test para listar todas las bodegas
    @Test
    void testListar() {

        Bodega b1 = new Bodega();
        Bodega b2 = new Bodega();

        when(repo.findAll())
                .thenReturn(Arrays.asList(b1, b2));

        List<Bodega> resultado = service.listar();

        assertEquals(2, resultado.size());

        verify(repo).findAll();
    }
    // test para  metodo guardar 
    @Test
    void testGuardar() {

        Bodega bodega = new Bodega();

        when(repo.save(bodega))
                .thenReturn(bodega);

        Bodega resultado = service.guardar(bodega);

        assertNotNull(resultado);

        verify(repo).save(bodega);
    }
    //test para buscar bodegas
    @Test
    void testBuscarPorId() {

        Bodega bodega = new Bodega();
        bodega.setId_bodega(1);

        when(repo.findById(1))
                .thenReturn(Optional.of(bodega));

        Bodega resultado = service.buscar(1);

        assertNotNull(resultado);
        assertEquals(1, resultado.getId_bodega());

        verify(repo).findById(1);
    }
    //test para metodo eliminar
    @Test
    void testEliminar() {

        Bodega bodega = new Bodega();
        bodega.setId_bodega(1);

        when(repo.findById(1))
                .thenReturn(Optional.of(bodega));

        service.eliminar(1);

        verify(repo).delete(bodega);
    }
}
