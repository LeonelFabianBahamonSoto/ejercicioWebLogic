package com.example.ejercicioWebLogic.pedidoDetalleProducto.services;

import java.math.BigDecimal;
import java.util.List;

import com.example.ejercicioWebLogic.entities.Pedido;
import com.example.ejercicioWebLogic.pedidoDetalleProducto.PedidoDetalleProducto;
import com.example.ejercicioWebLogic.pedidoDetalleProducto.dtos.PedidoDetalleProductoDto;
import com.example.ejercicioWebLogic.producto.Producto;

public interface PedidoDetalleProductoService {
    PedidoDetalleProductoDto crearPedidoDetalleProductoByPedidos(Integer cantidad, BigDecimal precioUnitario, Pedido pedido, Producto producto) throws Exception;

    List<PedidoDetalleProducto> obtenerAllPedidosDetalleProducto() throws Exception;
}
