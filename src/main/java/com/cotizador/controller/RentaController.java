package com.cotizador.controller;

import com.cotizador.dto.request.RentaDTO;
import com.cotizador.dto.response.CotizacionResponse;
import com.cotizador.service.RentaService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/rentas")
@RequiredArgsConstructor
public class RentaController {

    private final RentaService rentaService;

    @PostMapping("/cotizar")
    public ResponseEntity<CotizacionResponse> cotizar(@Valid @RequestBody RentaDTO dto) {
        CotizacionResponse response = rentaService.calcularCotizacion(dto);
        return ResponseEntity.ok(response);
    }
}