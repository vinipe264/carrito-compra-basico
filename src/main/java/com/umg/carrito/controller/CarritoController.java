package com.umg.carrito.controller;

import com.umg.carrito.model.Producto;
import com.umg.carrito.service.CarritoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/carrito")
@CrossOrigin(origins = "*")
public class CarritoController {

    @Autowired
    private CarritoService carritoService;

    @GetMapping
    public List<Producto> verCarrito() {
        return carritoService.verCarrito();
    }

    @PostMapping("/agregar/{id}")
    public void agregarAlCarrito(@PathVariable Integer id) {
        carritoService.agregarAlCarrito(id);
    }

    @DeleteMapping("/eliminar/{id}")
    public void eliminarDelCarrito(@PathVariable Integer id) {
        carritoService.eliminarDelCarrito(id);
    }
}


