package com.example.ejercicioWebLogic.dtos;

import java.math.BigDecimal;
import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PedidoNuevoDto {
    private Long clienteId;

    private Long productoId;

    private Integer cantidad;

    private BigDecimal precioUnitario;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
    private LocalDate fechaPedido;
}
