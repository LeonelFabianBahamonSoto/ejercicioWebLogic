package com.example.ejercicioWebLogic.producto.services;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.ejercicioWebLogic.producto.Producto;
import com.example.ejercicioWebLogic.producto.repositories.ProductoRepository;

@Service
public class ProductoServiceImpl implements ProductoService {

    private final ProductoRepository productoRepository;

    public ProductoServiceImpl(
        ProductoRepository productoRepository
    ) {
        this.productoRepository = productoRepository;
    }

    @Override
    @Transactional(readOnly = true)
    public Producto obtenerProductoByProductoId(Long productoId) throws Exception {
        Producto producto = this.productoRepository.findByProductoId(productoId)
            .orElseThrow(() -> new Exception("El producto a consultar no existe"));

        return producto;
    }

}
