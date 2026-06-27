package com.gestion.Cliente.Service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import com.gestion.cliente.DTO.ClienteDTO;
import com.gestion.cliente.model.Cliente;
import com.gestion.cliente.repository.ClienteRepository;
import com.gestion.cliente.service.ClienteService;

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
    // Test para el metodo para buscar un cliente por id
    @Test
    void testBuscarPorId_exitoso(){
        Integer idSimulado = 67;
        String nombreSimulado = faker.name().firstName();
        String apellidoSimulado = faker.name().lastName();
        String rutFalso = faker.idNumber().valid();
        String correoFalso = faker.internet().emailAddress();
        String telefonoFalso = faker.number().digits(9);
        // Crear el cliente
        Cliente clienteFalso = new Cliente();
        // Asisgnar datos falsos a cliente
        clienteFalso.setId_cliente(idSimulado);
        clienteFalso.setNombre(nombreSimulado);
        clienteFalso.setApellido(apellidoSimulado);
        clienteFalso.setRut(rutFalso);
        clienteFalso.setCorreo(correoFalso);
        clienteFalso.setTelefono(telefonoFalso);
    
    when(clienteRepository.findById(idSimulado)).thenReturn(Optional.of(clienteFalso));


        ClienteDTO resultado = clienteService.buscarClientePorId(idSimulado);

        assertNotNull(resultado, "El DTO resultante no debería ser nulo");
        assertEquals(nombreSimulado, resultado.getNombre(), "El nombre transformado al DTO debe coincidir con el de la DB");

        verify(clienteRepository,times(1)).findById(idSimulado);
    }

    // Test para el metodo para buscar un cliente por el rut
    @Test
    void testBuscarPorRut_exitoso(){
        Integer idSimulado = 67;
        String nombreSimulado = faker.name().firstName();
        String apellidoSimulado = faker.name().lastName();
        String rutFalso = faker.idNumber().valid();
        String correoFalso = faker.internet().emailAddress();
        String telefonoFalso = faker.number().digits(9);
        // Crear el cliente
        Cliente clienteFalso = new Cliente();
        // Asisgnar datos falsos a cliente
        clienteFalso.setId_cliente(idSimulado);
        clienteFalso.setNombre(nombreSimulado);
        clienteFalso.setApellido(apellidoSimulado);
        clienteFalso.setRut(rutFalso);
        clienteFalso.setCorreo(correoFalso);
        clienteFalso.setTelefono(telefonoFalso);
    
    when(clienteRepository.findByRut(rutFalso)).thenReturn(clienteFalso);


        ClienteDTO resultado = clienteService.buscarPorRut(rutFalso);

        assertNotNull(resultado, "El DTO resultante no debería ser nulo");
        assertEquals(nombreSimulado, resultado.getNombre(), "El nombre transformado al DTO debe coincidir con el de la DB");

        verify(clienteRepository,times(1)).findByRut(rutFalso);
    }

    // Test para agregar un cliente
    @Test
    void testAgregarCliente_exitoso(){
        Integer idSimulado = 67;
        String nombreSimulado = faker.name().firstName();
        String apellidoSimulado = faker.name().lastName();
        String rutFalso = faker.idNumber().valid();
        String correoFalso = faker.internet().emailAddress();
        String telefonoFalso = faker.number().digits(9);
        // Crear el cliente
        Cliente clienteFalso = new Cliente();
        // Asisgnar datos falsos a cliente
        clienteFalso.setId_cliente(idSimulado);
        clienteFalso.setNombre(nombreSimulado);
        clienteFalso.setApellido(apellidoSimulado);
        clienteFalso.setRut(rutFalso);
        clienteFalso.setCorreo(correoFalso);
        clienteFalso.setTelefono(telefonoFalso);
        
    when(clienteRepository.save(clienteFalso)).thenReturn(clienteFalso);


        ClienteDTO resultado = clienteService.guardarCliente(clienteFalso);

        assertNotNull(resultado, "El cliente resultante no debería ser nulo");
        assertEquals(nombreSimulado, resultado.getNombre(), "El nombre transformado al DTO debe coincidir con el de la DB");

        verify(clienteRepository,times(1)).save(clienteFalso);
    }

    // Test para eliminar un cliente
    @Test
    void testEliminarCliente(){
        // Crear datos falsos
        Integer idSimulado = 67;
        String nombreSimulado = faker.name().firstName();
        String apellidoSimulado = faker.name().lastName();
        String rutFalso = faker.idNumber().valid();
        String correoFalso = faker.internet().emailAddress();
        String telefonoFalso = faker.number().digits(9);
        // Crear el cliente
        Cliente clienteFalso = new Cliente();
        // Asisgnar datos falsos a cliente
        clienteFalso.setId_cliente(idSimulado);
        clienteFalso.setNombre(nombreSimulado);
        clienteFalso.setApellido(apellidoSimulado);
        clienteFalso.setRut(rutFalso);
        clienteFalso.setCorreo(correoFalso);
        clienteFalso.setTelefono(telefonoFalso);

    when(clienteRepository.findById(idSimulado)).thenReturn(Optional.of(clienteFalso));


        String resultado = clienteService.eliminarCliente(idSimulado);
        assertNotNull(resultado, "El resultado no debería ser nulo");
        verify(clienteRepository,times(1)).delete(clienteFalso);
    }

    // Test para obtener todos los cliente
    @Test
    void testObtenerClientes(){
        // Crear datos falsos
        Integer idSimulado = 67;
        String nombreSimulado = faker.name().firstName();
        String apellidoSimulado = faker.name().lastName();
        String rutFalso = faker.idNumber().valid();
        String correoFalso = faker.internet().emailAddress();
        String telefonoFalso = faker.number().digits(9);
        // Crear el cliente
        Cliente clienteFalso = new Cliente();
        // Asisgnar datos falsos a cliente
        clienteFalso.setId_cliente(idSimulado);
        clienteFalso.setNombre(nombreSimulado);
        clienteFalso.setApellido(apellidoSimulado);
        clienteFalso.setRut(rutFalso);
        clienteFalso.setCorreo(correoFalso);
        clienteFalso.setTelefono(telefonoFalso);

    when(clienteRepository.findAll()).thenReturn(List.of(clienteFalso));

        List<ClienteDTO> resultado = clienteService.obtenerClientes();

        assertNotNull(resultado, "La lista resultante no debería ser nulo");
        assertFalse(resultado.isEmpty(), "La lista resultante no debería estar vacía");
        assertEquals(nombreSimulado, resultado.get(0).getNombre(), "El nombre transformado al DTO debe coincidir con el de la DB");

        verify(clienteRepository,times(1)).findAll();

    }

    // Test para actualizar datos de un cliente
    @Test
    void testActualizarCliente(){
        // Crear datos falsos
        Integer idSimulado = 67;
        String nombreSimulado = faker.name().firstName();
        String apellidoSimulado = faker.name().lastName();
        String rutFalso = faker.idNumber().valid();
        String correoFalso = faker.internet().emailAddress();
        String telefonoFalso = faker.number().digits(9);
        // Crear el cliente
        Cliente clienteFalso = new Cliente();
        // Asisgnar datos falsos a cliente
        clienteFalso.setId_cliente(idSimulado);
        clienteFalso.setNombre(nombreSimulado);
        clienteFalso.setApellido(apellidoSimulado);
        clienteFalso.setRut(rutFalso);
        clienteFalso.setCorreo(correoFalso);
        clienteFalso.setTelefono(telefonoFalso);

        when(clienteRepository.findById(idSimulado))
            .thenReturn(Optional.of(clienteFalso));

        when(clienteRepository.save(any(Cliente.class)))
            .thenReturn(clienteFalso);

        Cliente resultado = clienteService.actualizarCliente(idSimulado, clienteFalso);

        assertNotNull(resultado, "El DTO resultante no debería ser nulo");
        assertEquals(nombreSimulado, resultado.getNombre(), "El nombre transformado al DTO debe coincidir con el de la DB");

        verify(clienteRepository, times(1)).save(any(Cliente.class));
    }
    
}