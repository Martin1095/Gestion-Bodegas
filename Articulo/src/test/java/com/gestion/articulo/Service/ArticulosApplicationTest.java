package com.gestion.articulo.Service;

import java.util.Arrays;
import java.util.Optional;
import java.util.List;

import com.gestion.articulo.DTO.ArticuloDTO;
import com.gestion.articulo.model.Articulo;
import com.gestion.articulo.repository.ArticuloRepository;
import com.gestion.articulo.service.ArticuloService;

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
class ArticuloServiceTest {

    @Mock
    private ArticuloRepository articuloRepository;

    @InjectMocks
    private ArticuloService articuloService;
    // test para obtener todos los artículos
    @Test
    void obtenerTodos() {

        Articulo a1 = new Articulo();
        Articulo a2 = new Articulo();

        when(articuloRepository.findAll())
                .thenReturn(Arrays.asList(a1, a2));

        List<ArticuloDTO> resultado = articuloService.obtenerTodos();

        assertEquals(2, resultado.size());

        verify(articuloRepository).findAll();
    }
    //test para eliminar un artículo
    @Test
    void guardarArticulo() {

        Articulo articulo = new Articulo();

        when(articuloRepository.save(articulo))
                .thenReturn(articulo);

        Articulo resultado = articuloService.guardarArticulo(articulo);

        assertNotNull(resultado);

        verify(articuloRepository).save(articulo);
    }
    //test para buscar un artículo por id
    @Test
    void buscarPorId() {

        Articulo articulo = new Articulo();

        when(articuloRepository.findById(1))
                .thenReturn(Optional.of(articulo));

        ArticuloDTO resultado = articuloService.buscarPorId(1);

        assertNotNull(resultado);

        verify(articuloRepository).findById(1);
    }
}
