package com.gestion.despacho.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gestion.despacho.DTO.DespachoDTO;
import com.gestion.despacho.DTO.PedidoExternoDTO;
import com.gestion.despacho.DTO.BodegaExternoDTO;
import com.gestion.despacho.model.Despacho;
import com.gestion.despacho.repository.DespachoRepository;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class DespachoService {

    @Autowired
    private DespachoValidaciones despachoValidaciones;

    @Autowired
    private DespachoRepository repo;

    public List<DespachoDTO> obtenerDespachos() {
        List<DespachoDTO> listaDTOs = new ArrayList<>();
        for (Despacho despacho : repo.findAll()) {
            listaDTOs.add(despachoValidaciones.convertirADTO(despacho));
        }
        return listaDTOs;
    }

    public DespachoDTO guardarDespacho(Despacho nuevoDespacho) {

        if (!despachoValidaciones.validarNullVacio(nuevoDespacho)) {
            throw new RuntimeException("Los datos del despacho son inválidos.");
        }

        // Validar Pedido
        PedidoExternoDTO pedido =
            despachoValidaciones.obtenerPedido(nuevoDespacho.getId_pedido());

        if (pedido.getIdPedidoExterno() == 0) {
            throw new RuntimeException("El pedido no existe.");
        }

        // Validar Bodega
        BodegaExternoDTO bodega =
            despachoValidaciones.obtenerBodega(nuevoDespacho.getId_bodega());

        if (bodega.getIdBodegaExterno() == 0) {
            throw new RuntimeException("La bodega no existe.");
        }

        Despacho guardado = repo.save(nuevoDespacho);
        return despachoValidaciones.convertirADTO(guardado);
    }
}
