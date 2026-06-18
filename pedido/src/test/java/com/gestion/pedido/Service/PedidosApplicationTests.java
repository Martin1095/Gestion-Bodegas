package com.gestion.pedido.Service;

import java.util.Arrays;
import java.util.Optional;
import java.util.List;

import com.gestion.pedido.DTO.PedidoDTO;
import com.gestion.pedido.model.Pedido;
import com.gestion.pedido.repository.PedidoRepository;
import com.gestion.pedido.service.PedidoService;

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
class PedidoServiceTest {

    @Mock
    private PedidoRepository pedidoRepository;

    @InjectMocks
    private PedidoService pedidoService;
    // test para obtener todos los pedidos
    @Test
    void obtenerPedidos() {

        when(pedidoRepository.findAll())
                .thenReturn(Arrays.asList(new Pedido(), new Pedido()));

        List<PedidoDTO> lista = pedidoService.obtenerPedidos();

        assertEquals(2, lista.size());

        verify(pedidoRepository).findAll();
    }
    // test para agregar un nuevo pedido
    @Test
    void agregarPedido() {

        Pedido pedido = new Pedido();

        when(pedidoRepository.save(pedido))
                .thenReturn(pedido);

        Pedido resultado = pedidoService.agregarPedido(pedido);

        assertNotNull(resultado);

        verify(pedidoRepository).save(pedido);
    }
    //test para obtener un pedido por id
    @Test
    void obtenerPedidoPorId() {

        Pedido pedido = new Pedido();

        when(pedidoRepository.findById(1))
                .thenReturn(Optional.of(pedido));

        PedidoDTO resultado = pedidoService.obtenerPedidoPorId(1);

        assertNotNull(resultado);

        verify(pedidoRepository).findById(1);
    }
}
