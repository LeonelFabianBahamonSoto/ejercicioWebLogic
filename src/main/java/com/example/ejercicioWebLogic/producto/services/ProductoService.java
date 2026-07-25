package com.example.ejercicioWebLogic.producto.services;

import com.example.ejercicioWebLogic.producto.Producto;

public interface ProductoService {
    Producto obtenerProductoByProductoId(Long productoId) throws Exception;
}
