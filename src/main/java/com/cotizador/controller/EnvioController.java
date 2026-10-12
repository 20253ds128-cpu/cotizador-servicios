package com.cotizador.controller;

import com.cotizador.dto.request.EnvioDTO;
import com.cotizador.dto.response.CotizacionResponse;
import com.cotizador.service.EnvioService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/envios")
@RequiredArgsConstructor
public class EnvioController {

    private final EnvioService envioService;

    @PostMapping("/cotizar")
    public ResponseEntity<CotizacionResponse> cotizar(@Valid @RequestBody EnvioDTO dto) {
        CotizacionResponse response = envioService.calcularCotizacion(dto);
        return ResponseEntity.ok(response);
    }
}