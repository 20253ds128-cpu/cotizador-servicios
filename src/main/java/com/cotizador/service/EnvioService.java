package com.cotizador.service;

import com.cotizador.dto.request.EnvioDTO;
import com.cotizador.dto.response.CotizacionResponse;
import com.cotizador.exception.BusinessException;
import org.springframework.stereotype.Service;

@Service
public class EnvioService {

    public CotizacionResponse calcularCotizacion(EnvioDTO dto) {
        if (dto.getPesoKg() > 50) {
            throw new BusinessException("No se aceptan paquetes que pesen más de 50 kg.");
        }
        if (dto.getLargoCm() > 150 || dto.getAnchoCm() > 150 || dto.getAltoCm() > 150) {
            throw new BusinessException("No se aceptan paquetes con alguna dimensión superior a 150 cm.");
        }

        double volumen = dto.getLargoCm() * dto.getAnchoCm() * dto.getAltoCm();
        if (volumen > 1_000_000) {
            throw new BusinessException("No se aceptan paquetes con un volumen superior a 1,000,000 cm³.");
        }

        double acumulado = 80.0;
        acumulado += dto.getPesoKg() * 12.0;

        if (volumen > 50_000) {
            acumulado += 100.0;
        }

        if ("EXPRESS".equalsIgnoreCase(dto.getTipoEnvio())) {
            acumulado *= 1.40;
        } else if ("MISMO_DIA".equalsIgnoreCase(dto.getTipoEnvio())) {
            acumulado *= 1.70;
        }

        double seguro = 0.0;
        if (dto.getValorDeclarado() > 10_000) {
            seguro = dto.getValorDeclarado() * 0.02;
        }

        double total = acumulado + seguro;

        return new CotizacionResponse(
                "Cotizador de Envíos",
                "CP: " + dto.getCodigoPostal(),
                Math.round(total * 100.0) / 100.0,
                String.format("Volumen: %.2f cm³, Seguro: $%.2f", volumen, seguro)
        );
    }
}