package com.example.ejercicioWebLogic.pedidoDetalleProducto.dtos;

import java.math.BigDecimal;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter
@Setter
public class PedidoDetalleProductoDto {
    private Long detalleId;

    private Integer cantidad;

    private BigDecimal precioUnitario;

    private Long pedidoId;

    private Long productoId;
}
