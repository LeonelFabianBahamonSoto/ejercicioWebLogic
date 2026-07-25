package com.example.ejercicioWebLogic.entities;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

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
@Table(name = "clientes")
public class Clientes {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_cliente")
    private Long clienteId;

    @NotBlank(message = "El nombre no puede estar vacio")
    @Column(name = "nombre")
    private String nombre;

    @NotBlank(message = "El email debe tener un formato válido")
    @Email(message = "El formato del email es inválido")
    @Column(name = "email")
    private String email;

    @NotNull(message = "La fecha de registro del usuario no puede ser nulo")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
    @Column(name = "fecha_registro")
    private LocalDate fechaRegistro;
}
