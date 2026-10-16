package com.umg.carrito.service;

import com.umg.carrito.model.Producto;
import com.umg.carrito.repository.ProductoRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class CarritoServiceTest {

    @Mock
    private ProductoRepository productoRepository;

    @InjectMocks
    private CarritoService carritoService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testAgregarYVerCarrito() {
        // Arrange
        Producto producto = new Producto();
        producto.setIdProducto(1);
        producto.setNombre("Laptop");
        producto.setPrecio(5000.0);

        when(productoRepository.findById(1L)).thenReturn(Optional.of(producto));

        // Act
        carritoService.agregarAlCarrito(1);

        // Assert
        assertEquals(1, carritoService.verCarrito().size());
        assertEquals("Laptop", carritoService.verCarrito().get(0).getNombre());
    }

    @Test
    void testEliminarDelCarrito() {
        // Arrange
        Producto producto = new Producto();
        producto.setIdProducto(1);
        producto.setNombre("Mouse");

        when(productoRepository.findById(1L)).thenReturn(Optional.of(producto));

        // Act
        carritoService.agregarAlCarrito(1);
        assertEquals(1, carritoService.verCarrito().size());

        carritoService.eliminarDelCarrito(1);

        // Assert
        assertTrue(carritoService.verCarrito().isEmpty());
    }
}
