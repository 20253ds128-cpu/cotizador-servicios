package com.cotizador.service;

import com.cotizador.dto.request.HospedajeDTO;
import com.cotizador.dto.response.CotizacionResponse;
import com.cotizador.exception.BusinessException;
import org.springframework.stereotype.Service;

@Service
public class HospedajeService {

    public CotizacionResponse calcularCotizacion(HospedajeDTO dto) {
        if (dto.getNumeroNoches() > 30) {
            throw new BusinessException("El número de noches no puede superar los 30 días.");
        }

        String tipo = dto.getTipoHabitacion().toUpperCase();
        int capacidadMax = switch (tipo) {
            case "INDIVIDUAL" -> 1;
            case "DOBLE" -> 2;
            case "SUITE" -> 4;
            default -> 0;
        };

        if (dto.getNumeroHuespedes() > capacidadMax) {
            throw new BusinessException(String.format("La habitación %s no permite más de %d huésped(es).", tipo, capacidadMax));
        }

        double costoNoche = switch (tipo) {
            case "INDIVIDUAL" -> 700.0;
            case "DOBLE" -> 1100.0;
            case "SUITE" -> 1800.0;
            default -> 0.0;
        };

        double costoHospedaje = costoNoche * dto.getNumeroNoches();

        if ("BAJA".equalsIgnoreCase(dto.getTemporada())) {
            costoHospedaje *= 0.90;
        } else if ("ALTA".equalsIgnoreCase(dto.getTemporada())) {
            costoHospedaje *= 1.25;
        }

        if (dto.getNumeroNoches() >= 7) {
            costoHospedaje *= 0.92;
        }

        double costoDesayuno = dto.getIncluyeDesayuno() ? (dto.getNumeroHuespedes() * dto.getNumeroNoches() * 150.0) : 0.0;
        double costoEstacionamiento = dto.getIncluyeEstacionamiento() ? (dto.getNumeroNoches() * 100.0) : 0.0;

        double subtotal = costoHospedaje + costoDesayuno + costoEstacionamiento;
        double impuesto = subtotal * 0.04;
        double total = subtotal + impuesto;

        return new CotizacionResponse(
                "Cotizador de Hospedaje",
                dto.getNombreHuesped(),
                Math.round(total * 100.0) / 100.0,
                String.format("Subtotal: $%.2f, Impuesto (4%%): $%.2f", subtotal, impuesto)
        );
    }
}