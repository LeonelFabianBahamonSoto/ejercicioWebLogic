package com.example.ejercicioWebLogic.pedidoDetalleProducto.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.ejercicioWebLogic.pedidoDetalleProducto.PedidoDetalleProducto;
import com.example.ejercicioWebLogic.pedidoDetalleProducto.services.PedidoDetalleProductoService;


@RestController
@RequestMapping(path = "/pedidoDetalleProducto")
public class PedidoDetalleProductoController {
    private final PedidoDetalleProductoService pedidoDetalleProductoService;

    public PedidoDetalleProductoController(PedidoDetalleProductoService pedidoDetalleProductoService) {
        this.pedidoDetalleProductoService = pedidoDetalleProductoService;
    }

    @GetMapping("/todosPedidoDetalleProductos")
    public ResponseEntity<List<PedidoDetalleProducto>> todosPedidoDetalleProductos() throws Exception {
        List<PedidoDetalleProducto> allPedidoDetalleProducto = this.pedidoDetalleProductoService.obtenerAllPedidosDetalleProducto();

        return new ResponseEntity<List<PedidoDetalleProducto>>(allPedidoDetalleProducto, HttpStatus.OK);
    }

}
