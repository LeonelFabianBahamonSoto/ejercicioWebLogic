package com.example.ejercicioWebLogic.pedidoDetalleProducto;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.example.ejercicioWebLogic.entities.Pedido;
import com.example.ejercicioWebLogic.producto.Producto;
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
@Table(name = "pedido_detalle_producto")
public class PedidoDetalleProducto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_detalle")
    private Long detalleId;

    @Column(name = "cantidad")
    private Integer cantidad;

    @Column(name = "precio_unitario")
    private BigDecimal precioUnitario;

    @JoinColumn(name = "id_pedido", referencedColumnName = "id_pedido", nullable = false)
    @ManyToOne(fetch = FetchType.LAZY)
    private Pedido pedido;

    @JoinColumn(name = "id_producto", referencedColumnName = "id_producto", nullable = false)
    @ManyToOne(fetch = FetchType.LAZY)
    private Producto producto;
}
