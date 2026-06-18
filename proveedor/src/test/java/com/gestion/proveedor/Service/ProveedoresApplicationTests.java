package com.gestion.proveedor.Service;

import java.util.Arrays;
import java.util.List;

import com.gestion.proveedor.DTO.ProveedorDTO;
import com.gestion.proveedor.model.Proveedor;
import com.gestion.proveedor.repository.ProveedorRepository;
import com.gestion.proveedor.service.ProveedorService;

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
public class ProveedoresApplicationTests {

    @Mock
    private ProveedorRepository proveedorRepository;

    @InjectMocks
    private ProveedorService proveedorService;
    // test para obtener todos los proveedores
    @Test
    void obtenerTodos() {

        when(proveedorRepository.findAll())
                .thenReturn(Arrays.asList(new Proveedor(), new Proveedor()));

        List<ProveedorDTO> lista = proveedorService.obtenerTodos();

        assertEquals(2, lista.size());

        verify(proveedorRepository).findAll();
    }
    //test para guardar a los proveedores
    @Test
    void guardarProveedor() {

        Proveedor proveedor = new Proveedor();

        when(proveedorRepository.save(proveedor))
                .thenReturn(proveedor);

        Proveedor resultado = proveedorService.guardarProveedor(proveedor);

        assertNotNull(resultado);

        verify(proveedorRepository).save(proveedor);
    }
}
