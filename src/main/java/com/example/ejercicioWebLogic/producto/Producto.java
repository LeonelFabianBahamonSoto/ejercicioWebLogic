package com.example.ejercicioWebLogic.producto;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@Builder
@Entity
@Getter
@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
@NoArgsConstructor
@Setter
@Table(name = "productos")
public class Producto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_producto")
    private Long productoId;

    @NotBlank(message = "El nombre del producto no puede estar vacio")
    @Column(name = "nombre")
    private String nombreProducto;

    @NotNull(message = "El precio es obligatorio")
    @Column(name = "precio")
    private BigDecimal precio;

    @NotNull(message = "El stock es obligatorio")
    @Column(name = "stock")
    private Integer stock;
}
