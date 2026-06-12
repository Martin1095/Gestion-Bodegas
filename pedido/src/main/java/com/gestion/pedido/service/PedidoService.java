package com.gestion.pedido.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gestion.pedido.DTO.PedidoDTO;
import com.gestion.pedido.model.Pedido;
import com.gestion.pedido.repository.PedidoRepository;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class PedidoService {

    @Autowired
    private PedidoRepository pedidoRepository;

    //Metodo para obtener todos los pedidos
    public List<PedidoDTO> obtenerPedidos(){
        return pedidoRepository.findAll().stream()
                .map(this::convertirAPedidoDTO)
                .toList();
    }

    //Metodo para convertir a PedidoDTO
    private PedidoDTO convertirAPedidoDTO(Pedido pedido) {
        PedidoDTO pedidoDTO = new PedidoDTO();
        pedidoDTO.setId_pedido(pedido.getId_pedido());
        pedidoDTO.setFecha_entrega(pedido.getFecha_entrega());
        pedidoDTO.setDireccion_entrega(pedido.getDireccion_entrega());
        pedidoDTO.setEstado_pedido(pedido.getEstado_pedido());
        return pedidoDTO;
    }

    //Metodo para obtener un pedido por su ID
    public PedidoDTO obtenerPedidoPorId(Integer id_pedido){
        Pedido pedido = pedidoRepository.findById(id_pedido)
                    .orElseThrow(() -> new RuntimeException("Pedido no encontrado con el ID: " + id_pedido));
        return convertirAPedidoDTO(pedido);
    }

    //metodo para añdadir nuevo pedido
    public Pedido agregarPedido(Pedido pedido) {
        return pedidoRepository.save(pedido);
    }

    //Metodo para actualizar un pedido existente
    public Pedido actualizarPedido(Integer id_pedido, Pedido pedidoActu) {
        Pedido pedido = pedidoRepository.findById(id_pedido).orElseThrow(() -> new RuntimeException("¡Imposible editar! El pedido con ID " + id_pedido + " no existe."));
        
        if(pedido.getFecha_entrega() != null) {
            pedido.setFecha_entrega(pedidoActu.getFecha_entrega());
        }
        if(pedido.getDireccion_entrega() != null) {
            pedido.setDireccion_entrega(pedidoActu.getDireccion_entrega());
        }
        if(pedido.getEstado_pedido() != null) {
            pedido.setEstado_pedido(pedidoActu.getEstado_pedido());
        }
        return pedidoRepository.save(pedido);
    }

    //Metodo para eliminar un pedido por su ID
    public String eliminarPedido(Integer id) {
        try {
            Pedido pedido = pedidoRepository.findById(id)
                    .orElseThrow(() -> new RuntimeException("¡Imposible eliminar! El pedido con ID " + id + " no existe."));
            pedidoRepository.delete(pedido);
            return "El pedido con ID '" + id + "' ha sido eliminado exitosamente.";
        } catch (RuntimeException e) {
            return e.getMessage();
        }
    }
}