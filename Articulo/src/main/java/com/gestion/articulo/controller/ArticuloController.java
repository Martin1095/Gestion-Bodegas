package com.gestion.articulo.controller;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gestion.articulo.DTO.ArticuloDTO;
import com.gestion.articulo.model.Articulo;
import com.gestion.articulo.service.ArticuloService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@Tag(name = "Articulos", description = "Operaciones para los artículos en la bodega")
@RequestMapping("/api/v1/articulos")
public class ArticuloController {

    @Autowired
    private ArticuloService articuloService;
    // GET ALL
    @GetMapping
    @Operation(summary = "Obtener todos los artículos", description = "Devuelve una lista de todos los artículosen el sistema.")
    @ApiResponse(responseCode = "200", description = "Lista de artículos obtenida exitosamente")
    @ApiResponse(responseCode = "204", description = "No se encontraron artículos")
    public ResponseEntity<List<ArticuloDTO>> obtenerTodos() {
        List<ArticuloDTO> lista = articuloService.obtenerArticulos();
        if (lista.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(lista, HttpStatus.OK);
    }
    // GET BY ID
    @GetMapping("/{id}")
    @Operation(summary = "Buscar artículo por ID", description = "Permite buscar un artículo por su ID.")
    @ApiResponse(responseCode = "200", description = "Artículo encontrado exitosamente")
    @ApiResponse(responseCode = "404", description = "Artículo no encontrado")
    public ResponseEntity<ArticuloDTO> buscarPorId(@PathVariable Integer id){
        try {
            ArticuloDTO articulo = articuloService.obtenerArticuloPorId(id);
            return new ResponseEntity<>(articulo, HttpStatus.OK);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    // POST
    @PostMapping
    @Operation(summary = "Guardar artículo", description = "Permite guardar un nuevo artículo en el sistema.")
    @ApiResponse(responseCode = "201", description = "Artículo guardado exitosamente")
    @ApiResponse(responseCode = "400", description = "Solicitud inválida")
    public ResponseEntity<Articulo> guardar(@RequestBody Articulo articulo) {
        try {
            Articulo nuevo = articuloService.guardarArticulo(articulo);
            return new ResponseEntity<>(nuevo, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
    // PATCH
    @PatchMapping("/{id}")
    @Operation(summary = "Actualizar artículo", description = "Permite actualizar la información de un artículo.")
    @ApiResponse(responseCode = "200", description = "Artículo actualizado exitosamente")
    @ApiResponse(responseCode = "404", description = "Artículo no encontrado")
    public ResponseEntity<Articulo> actualizar(@PathVariable Integer id, @RequestBody Articulo articulo) {
        try {
            Articulo actualizado = articuloService.actualizarArticulo(id, articulo);
            return new ResponseEntity<>(actualizado, HttpStatus.OK);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    // DELETE
    @DeleteMapping("/{id}")
    @Operation(summary = "Eliminar artículo", description = "Permite eliminar un artículo del sistema.")
    @ApiResponse(responseCode = "200", description = "Artículo eliminado exitosamente")
    @ApiResponse(responseCode = "404", description = "Artículo no encontrado")
    public ResponseEntity<String> eliminar(@PathVariable Integer id) {
        String resultado = articuloService.eliminarArticulo(id);
        if (resultado.contains("correctamente")) {
            return new ResponseEntity<>(resultado, HttpStatus.OK);
        }
        
        return new ResponseEntity<>(resultado, HttpStatus.NOT_FOUND);
    }
}