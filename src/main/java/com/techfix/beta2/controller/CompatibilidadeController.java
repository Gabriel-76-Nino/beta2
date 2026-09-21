package com.techfix.beta2.controller;

import com.techfix.beta2.domain.agregadosProduto.compatibilidade.CompatibilidadeDto;
import com.techfix.beta2.domain.agregadosProduto.compatibilidade.CompatibilidadeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/compatibildade")
public class CompatibilidadeController {

    @Autowired
    private CompatibilidadeService compatibilidadeService;

    @GetMapping
    public ResponseEntity<List<CompatibilidadeDto>> listarCompatibilidades(){
        List<CompatibilidadeDto> dto = compatibilidadeService.listarCompatibilidades();
        return ResponseEntity.ok(dto);
    }

    @PostMapping
    @Transactional
    public ResponseEntity<CompatibilidadeDto> cadastrarCompatibilidade(@RequestBody CompatibilidadeDto dto){
        CompatibilidadeDto compatibilidadeDto = compatibilidadeService.cadastrarCompatibilidade(dto);
        return ResponseEntity.ok(compatibilidadeDto);
    }
}
