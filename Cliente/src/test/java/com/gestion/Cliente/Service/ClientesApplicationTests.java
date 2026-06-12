package com.gestion.Cliente.Service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import com.gestion.cliente.DTO.ClienteDTO;
import com.gestion.cliente.model.Cliente;
import com.gestion.cliente.repository.ClienteRepository;
import com.gestion.cliente.service.ClienteService;

import jakarta.inject.Inject;
import net.datafaker.Faker;

@ExtendWith(MockitoExtension.class)
public class ClientesApplicationTests {
    
    @Mock
    private ClienteRepository clienteRepository;    

    @InjectMocks
    private ClienteService clienteService;

    private Faker faker = new Faker();

    @BeforeEach
    void setUp(){
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testBuscarPorId_exitoso(){
        Integer idSimulado = 67;
        String nombreSimulado = faker.name().firstName();
        String apellidoSimulado = faker.name().lastName();
        String rutFalso = faker.idNumber().valid();
        String correoFalso = faker.internet().emailAddress();
        String telefonoFalso = faker.number().digits(9);

        Cliente clienteFalso = new Cliente();

        clienteFalso.setId_cliente(idSimulado);
        clienteFalso.setNombre(nombreSimulado);
        clienteFalso.setApellido(apellidoSimulado);
        clienteFalso.setRut(rutFalso);
        clienteFalso.setCorreo(correoFalso);
        clienteFalso.setTelefono(telefonoFalso);
    
    when(clienteRepository.findById(idSimulado)).thenReturn(Optional.of(clienteFalso));


        ClienteDTO resultado = clienteService.obtenerClientePorId(idSimulado);

        assertNotNull(resultado, "El DTO resultante no debería ser nulo");
        assertEquals(nombreSimulado, resultado.getNombre(), "El nombre transformado al DTO debe coincidir con el de la DB");

        verify(clienteRepository,times(1)).findById(idSimulado);
    }

}
