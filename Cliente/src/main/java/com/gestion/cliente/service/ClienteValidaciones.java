package com.gestion.cliente.service;

import com.gestion.cliente.DTO.ClienteDTO;
import com.gestion.cliente.model.Cliente;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

@Service
public class ClienteValidaciones {

   @Autowired
   private WebClient.Builder webClientBuilder;


   public Boolean validarNullVacio(Cliente cliente) {
      if(cliente.getNombre() == null || cliente.getNombre().trim().length() == 0){
         return false;
      }
      if(cliente.getApellido() == null || cliente.getApellido().trim().length() == 0){
         return false;
      }
      if(cliente.getRut() == null || cliente.getRut().trim().length() == 0 ){
         return false;
      }
      if(cliente.getCorreo() == null || cliente.getCorreo().trim().length() == 0){
         return false;
      }
      if(cliente.getTelefono() == null || cliente.getTelefono().trim().length() == 0){
         return false;
      }
      return true;
   }
      


   public ClienteDTO convertirAClienteDTO(Cliente cliente) {
      ClienteDTO clienteDTO = new ClienteDTO();
      clienteDTO.setId_cliente(cliente.getId_cliente());
      clienteDTO.setNombre(cliente.getNombre());
      clienteDTO.setCorreo(cliente.getCorreo());
      clienteDTO.setTelefono(cliente.getTelefono());
      return clienteDTO;
   }



}



