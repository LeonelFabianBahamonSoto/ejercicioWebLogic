package com.example.ejercicioWebLogic.pedidoDetalleProducto.services;

import java.math.BigDecimal;

import org.springframework.stereotype.Service;

import com.example.ejercicioWebLogic.entities.Pedido;
import com.example.ejercicioWebLogic.pedidoDetalleProducto.PedidoDetalleProducto;
import com.example.ejercicioWebLogic.pedidoDetalleProducto.dtos.PedidoDetalleProductoDto;
import com.example.ejercicioWebLogic.pedidoDetalleProducto.repositories.PedidoDetalleProductoRepository;
import com.example.ejercicioWebLogic.producto.Producto;

@Service
public class PedidoDetalleProductoServiceImpl implements PedidoDetalleProductoService {

    private final PedidoDetalleProductoRepository pedidoDetalleProductoRepository;

    public PedidoDetalleProductoServiceImpl(
        PedidoDetalleProductoRepository pedidoDetalleProductoRepository
    ) {
        this.pedidoDetalleProductoRepository = pedidoDetalleProductoRepository;
    }

    @Override
    public PedidoDetalleProductoDto crearPedidoDetalleProductoByPedidos(
            Integer cantidad, BigDecimal precioUnitario, Pedido pedido, Producto producto) throws Exception {

        PedidoDetalleProducto pedidoDetalleProducto = PedidoDetalleProducto.builder()
            .cantidad(cantidad)
            .precioUnitario(precioUnitario)
            .pedido(pedido)
            .producto(producto)
            .build();

        this.pedidoDetalleProductoRepository.save(pedidoDetalleProducto);

        PedidoDetalleProductoDto pedidoDetalleProductoDto = PedidoDetalleProductoDto.builder()
                .cantidad(cantidad)
                .precioUnitario(precioUnitario)
                .pedidoId(pedido.getPedidoId())
                .productoId(producto.getProductoId())
                .build();

        return pedidoDetalleProductoDto;
    }

}
