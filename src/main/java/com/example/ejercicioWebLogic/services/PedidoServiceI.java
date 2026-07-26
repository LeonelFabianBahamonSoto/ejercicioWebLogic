package com.example.ejercicioWebLogic.services;

import java.util.List;

import com.example.ejercicioWebLogic.dtos.PedidoNuevoDto;
import com.example.ejercicioWebLogic.entities.Pedido;

public interface PedidoServiceI {
    PedidoNuevoDto crearPedido(PedidoNuevoDto pedidoNuevoDto) throws Exception;

    PedidoNuevoDto editarPedido(PedidoNuevoDto pedidoNuevoDto) throws Exception;

    List<Pedido> obtenerTodosPedidos() throws Exception;

    Pedido obtenerPedidoByPedidoId(Long pedidoId) throws Exception;

    Boolean eliminarPedidoByPedidoId(Long pedidoId) throws Exception;
}
