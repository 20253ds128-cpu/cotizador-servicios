package com.cotizador.service;

import com.cotizador.dto.request.RentaDTO;
import com.cotizador.dto.response.CotizacionResponse;
import com.cotizador.exception.BusinessException;
import org.springframework.stereotype.Service;

@Service
public class RentaService {

    public CotizacionResponse calcularCotizacion(RentaDTO dto) {
        if (dto.getEdadConductor() < 18) {
            throw new BusinessException("El conductor debe ser mayor de 18 años.");
        }
        if (dto.getDiasRenta() > 30) {
            throw new BusinessException("La renta no puede superar los 30 días.");
        }
        if (dto.getKilometrosEstimados() > 5000) {
            throw new BusinessException("Los kilómetros estimados no pueden superar los 5,000 km.");
        }
        if ("CAMIONETA".equalsIgnoreCase(dto.getTipoVehiculo()) && dto.getEdadConductor() < 25) {
            throw new BusinessException("Para rentar una CAMIONETA el conductor debe tener al menos 25 años.");
        }

        double costoDiario = switch (dto.getTipoVehiculo().toUpperCase()) {
            case "COMPACTO" -> 550.0;
            case "SEDAN" -> 700.0;
            case "SUV" -> 950.0;
            case "CAMIONETA" -> 1200.0;
            default -> 0.0;
        };

        double costoRenta = costoDiario * dto.getDiasRenta();
        if (dto.getDiasRenta() >= 7) {
            costoRenta *= 0.90;
        }

        int kmIncluidos = dto.getDiasRenta() * 100;
        double cargoKmExtra = 0.0;
        if (dto.getKilometrosEstimados() > kmIncluidos) {
            cargoKmExtra = (dto.getKilometrosEstimados() - kmIncluidos) * 4.0;
        }

        double cargoEdad = 0.0;
        if (dto.getEdadConductor() >= 18 && dto.getEdadConductor() <= 24) {
            cargoEdad = (costoRenta + cargoKmExtra) * 0.15;
        }

        double costoSeguro = dto.getSeguroCompleto() ? (180.0 * dto.getDiasRenta()) : 0.0;

        double total = costoRenta + cargoKmExtra + cargoEdad + costoSeguro;

        return new CotizacionResponse(
                "Cotizador de Vehículos",
                dto.getNombreCliente(),
                Math.round(total * 100.0) / 100.0,
                String.format("Días: %d, Km Incluidos: %d, Seguro diario: $%.2f", dto.getDiasRenta(), kmIncluidos, costoSeguro)
        );
    }
}