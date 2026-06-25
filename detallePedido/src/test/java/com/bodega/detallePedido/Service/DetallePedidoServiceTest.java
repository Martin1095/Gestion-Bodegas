package com.bodega.detallePedido.Service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import com.gestion.detallePedido.DTO.DetallePedidoDTO;
import com.gestion.detallePedido.model.DetallePedido;
import com.gestion.detallePedido.repository.DetallePedidoRepository;
import com.gestion.detallePedido.service.DetallePedidoService;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class DetallePedidoServiceTest {

    @Mock
    private DetallePedidoRepository detallePedidoRepository;

    @InjectMocks
    private DetallePedidoService detallePedidoService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testObtenerDetallesPedido() {

        when(detallePedidoRepository.findAll())
                .thenReturn(Arrays.asList(
                        new DetallePedido(),
                        new DetallePedido()));

        List<DetallePedidoDTO> resultado =
                detallePedidoService.obtenerDetallesPedido();

        assertEquals(2, resultado.size());

        verify(detallePedidoRepository, times(1)).findAll();
    }

    @Test
    void testObtenerDetallePedidoPorId() {

        DetallePedido detalle = new DetallePedido();

        when(detallePedidoRepository.findById(1))
                .thenReturn(Optional.of(detalle));

        DetallePedidoDTO resultado =
                detallePedidoService.obtenerDetallePedidoPorId(1);

        assertNotNull(resultado);

        verify(detallePedidoRepository, times(1)).findById(1);
    }

    @Test
    void testAgregarDetallePedido() {

        DetallePedido detalle = new DetallePedido();

        when(detallePedidoRepository.save(detalle))
                .thenReturn(detalle);

        DetallePedido resultado =
                detallePedidoService.agregarDetallePedido(detalle);

        assertNotNull(resultado);

        verify(detallePedidoRepository, times(1)).save(detalle);
    }

    @Test
    void testEliminarDetallePedido() {

        DetallePedido detalle = new DetallePedido();

         when(detallePedidoRepository.findById(1))
            .thenReturn(Optional.of(detalle));

        String resultado =
            detallePedidoService.eliminarDetallePedido(1);

        assertEquals(
            "El detalle de pedido con ID '1' ha sido eliminado exitosamente.",
            resultado);

        verify(detallePedidoRepository).findById(1);
        verify(detallePedidoRepository).delete(detalle);
    }
}
