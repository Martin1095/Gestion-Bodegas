package com.gestion.inventario.Service;


import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import com.gestion.inventario.model.Inventario;
import com.gestion.inventario.repository.InventarioRepository;
import com.gestion.inventario.service.InventarioService;

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
public class InventariosApplicationTests {

    @Mock
    private InventarioRepository repo;

    @InjectMocks
    private InventarioService service;

    //test para metodo listar todos los inventarios
    @Test
    void testListar() {

        when(repo.findAll())
                .thenReturn(Arrays.asList(new Inventario(), new Inventario()));

        List<Inventario> lista = service.listar();

        assertEquals(2, lista.size());

        verify(repo).findAll();
    }
    // test para guardar un nuevo inventario
    @Test
    void testGuardar() {

        Inventario inventario = new Inventario();

        when(repo.save(inventario))
                .thenReturn(inventario);

        Inventario resultado = service.guardar(inventario);

        assertNotNull(resultado);

        verify(repo).save(inventario);
    }
    // test para buscar un inventario por id
    @Test
    void testBuscar() {

        Inventario inventario = new Inventario();

        when(repo.findById(1))
                .thenReturn(Optional.of(inventario));

        Inventario resultado = service.buscar(1);

        assertNotNull(resultado);

        verify(repo).findById(1);
    }
}
