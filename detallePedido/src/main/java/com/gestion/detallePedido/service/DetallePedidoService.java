package com.gestion.detallePedido.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gestion.detallePedido.DTO.DetallePedidoDTO;
import com.gestion.detallePedido.model.DetallePedido;
import com.gestion.detallePedido.repository.DetallePedidoRepository;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class DetallePedidoService {

    @Autowired
    private DetallePedidoRepository detallePedidoRepository;

    // Método para añadir un nuevo detalle de pedido
    public DetallePedido agregarDetallePedido(DetallePedido detallePedido) {
        return detallePedidoRepository.save(detallePedido);
    }


    //Metodo para transformar un DetallePedido a DetallePedidoDTO
    public DetallePedidoDTO convertirADetallePedidoDTO(DetallePedido detallePedido) {
        DetallePedidoDTO detallePedidoDTO = new DetallePedidoDTO();
        detallePedidoDTO.setId_detalle_pedido(detallePedido.getId_detalle_pedido());
        detallePedidoDTO.setCantidad(detallePedido.getCantidad());
        detallePedidoDTO.setPrecio_unitario(detallePedido.getPrecio_unitario());
        return detallePedidoDTO;
    }

    // Metodo para listar todos los detalles de pedido
    public java.util.List<DetallePedidoDTO> obtenerDetallesPedido(){
        return detallePedidoRepository.findAll().stream()
                .map(this::convertirADetallePedidoDTO)
                .toList();
    }

    // Metodo para obtener un detalle de pedido por su ID
    public DetallePedidoDTO obtenerDetallePedidoPorId(Integer id_detalle_pedido){
        DetallePedido detallePedido = detallePedidoRepository.findById(id_detalle_pedido)
                    .orElseThrow(() -> new RuntimeException("Detalle de pedido no encontrado con ID: " + id_detalle_pedido));
        return convertirADetallePedidoDTO(detallePedido);
    }

    // Metodo para eliminar un detalle de pedido por su ID
    public String eliminarDetallePedido(Integer id) {
        try {
            DetallePedido detallePedido = detallePedidoRepository.findById(id)
                    .orElseThrow(() -> new RuntimeException("¡Imposible eliminar! El detalle de pedido con ID " + id + " no existe."));
            detallePedidoRepository.delete(detallePedido);
            return "El detalle de pedido con ID '" + id + "' ha sido eliminado exitosamente.";
        } catch (RuntimeException e) {
            return e.getMessage();
        }
    }

    // Método para editar un detalle de pedido
    public DetallePedido editarDetallePedido(Integer id_detalle_pedido, DetallePedido detallePedidoActu) {
        DetallePedido detallePedido = detallePedidoRepository.findById(id_detalle_pedido)
                .orElseThrow(() -> new RuntimeException("¡Imposible editar! El detalle de pedido con ID " + id_detalle_pedido + " no existe."));
        
        if(detallePedido.getCantidad() != 0) {
            detallePedido.setCantidad(detallePedidoActu.getCantidad());
        }
        if(detallePedido.getPrecio_unitario() != 0) {
            detallePedido.setPrecio_unitario(detallePedidoActu.getPrecio_unitario());
        }
        return detallePedidoRepository.save(detallePedido);
    }
}
