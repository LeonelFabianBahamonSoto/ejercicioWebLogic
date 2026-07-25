package com.example.ejercicioWebLogic.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.ejercicioWebLogic.dtos.PedidoNuevoDto;
import com.example.ejercicioWebLogic.services.PedidoServiceI;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

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

}
