package com.cotizador.dto.request;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RentaDTO {

    @NotBlank(message = "El nombre del cliente es obligatorio")
    private String nombreCliente;

    @NotNull(message = "La edad del conductor es obligatoria")
    @Min(value = 1, message = "La edad debe ser mayor a 0")
    private Integer edadConductor;

    @NotBlank(message = "El tipo de vehículo es obligatorio")
    @Pattern(regexp = "^(COMPACTO|SEDAN|SUV|CAMIONETA)$", message = "Tipo no válido. Permitidos: COMPACTO, SEDAN, SUV, CAMIONETA")
    private String tipoVehiculo;

    @NotNull(message = "Los días de renta son obligatorios")
    @Positive(message = "Los días de renta deben ser mayor a 0")
    private Integer diasRenta;

    @NotNull(message = "Los kilómetros estimados son obligatorios")
    @PositiveOrZero(message = "Los kilómetros estimados no pueden ser negativos")
    private Integer kilometrosEstimados;

    @NotNull(message = "Debe indicar si contrata seguro completo")
    private Boolean seguroCompleto;
}