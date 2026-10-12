package com.cotizador.controller;

import com.cotizador.dto.request.HospedajeDTO;
import com.cotizador.dto.response.CotizacionResponse;
import com.cotizador.service.HospedajeService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/hospedaje")
@RequiredArgsConstructor
public class HospedajeController {

    private final HospedajeService hospedajeService;

    @PostMapping("/cotizar")
    public ResponseEntity<CotizacionResponse> cotizar(@Valid @RequestBody HospedajeDTO dto) {
        CotizacionResponse response = hospedajeService.calcularCotizacion(dto);
        return ResponseEntity.ok(response);
    }
}