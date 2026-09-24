package com.techfix.beta2.controller;

import com.techfix.beta2.domain.agregados_produto.garantia.GarantiaService;
import com.techfix.beta2.domain.agregados_produto.garantia.GarantiaDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/garantia")
public class GarantiaController {

    @Autowired
    private GarantiaService garantiaService;

    @GetMapping
    public ResponseEntity<List<GarantiaDto>> listarGarantias(){
        List<GarantiaDto> garantiaDto = garantiaService.listarGarantias();
        return ResponseEntity.ok(garantiaDto);
    }

    @PostMapping
    @Transactional
    public ResponseEntity<GarantiaDto> cadastrarGarantia(@RequestBody GarantiaDto dto){
        GarantiaDto garantiaDto = garantiaService.cadastrarGarantia(dto);
        return ResponseEntity.ok(garantiaDto);
    }
}
