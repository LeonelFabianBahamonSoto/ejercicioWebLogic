package com.example.ejercicioWebLogic.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.ejercicioWebLogic.dtos.PedidoNuevoDto;
import com.example.ejercicioWebLogic.entities.Pedido;
import com.example.ejercicioWebLogic.services.PedidoServiceI;


@RestController
@RequestMapping(path = "/Pedido")
public class PedidoController {
    private final PedidoServiceI pedidoServiceI;

    public PedidoController(
        PedidoServiceI pedidoServiceI
    ) {
        this.pedidoServiceI = pedidoServiceI;
    }

    @PostMapping("/crearPedido")
    public ResponseEntity<PedidoNuevoDto> postMethodName(@RequestBody PedidoNuevoDto pedidoNuevoDto) throws Exception {
        PedidoNuevoDto pedidoCreado = this.pedidoServiceI.crearPedido(pedidoNuevoDto);

        return new ResponseEntity<PedidoNuevoDto>(pedidoCreado, HttpStatus.OK);
    }

    @GetMapping("/pedidoById")
    public ResponseEntity<Pedido> getPedidoByPedidoId(@RequestParam Long pedidoById) throws Exception {
        Pedido pedido = this.pedidoServiceI.obtenerPedidoByPedidoId(pedidoById);

        return new ResponseEntity<Pedido>(pedido, HttpStatus.FOUND);
    }

}
