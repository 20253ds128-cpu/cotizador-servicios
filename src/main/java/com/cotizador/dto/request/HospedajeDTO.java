package com.cotizador.dto.request;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class HospedajeDTO {

    @NotBlank(message = "El nombre del huésped es obligatorio")
    private String nombreHuesped;

    @NotBlank(message = "El tipo de habitación es obligatorio")
    @Pattern(regexp = "^(INDIVIDUAL|DOBLE|SUITE)$", message = "Habitación no válida. Permitidos: INDIVIDUAL, DOBLE, SUITE")
    private String tipoHabitacion;

    @NotNull(message = "El número de noches es obligatorio")
    @Positive(message = "El número de noches debe ser mayor a 0")
    private Integer numeroNoches;

    @NotNull(message = "El número de huéspedes es obligatorio")
    @Positive(message = "El número de huéspedes debe ser mayor a 0")
    private Integer numeroHuespedes;

    @NotBlank(message = "La temporada es obligatoria")
    @Pattern(regexp = "^(BAJA|REGULAR|ALTA)$", message = "Temporada no válida. Permitidos: BAJA, REGULAR, ALTA")
    private String temporada;

    @NotNull(message = "Debe indicar si incluye desayuno")
    private Boolean incluyeDesayuno;

    @NotNull(message = "Debe indicar si incluye estacionamiento")
    private Boolean incluyeEstacionamiento;
}