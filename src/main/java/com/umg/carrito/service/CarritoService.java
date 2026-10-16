package com.umg.carrito.service;

import com.umg.carrito.model.Producto;
import com.umg.carrito.repository.ProductoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CarritoService {

    private List<Producto> carrito = new ArrayList<>();

    @Autowired
    private ProductoRepository productoRepository;

    public List<Producto> verCarrito() {
        return carrito;
    }

    public void agregarAlCarrito(Integer id) {
        productoRepository.findById(id.longValue()).ifPresent(producto -> carrito.add(producto));
    }

    public void eliminarDelCarrito(Integer id) {
        carrito.removeIf(p -> p.getIdProducto() != null && p.getIdProducto().equals(id));
    }
}
