package com.cotizador.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CotizacionResponse {
    private String servicio;
    private String referenciaCliente;
    private double costoTotal;
    private String desglose;
}