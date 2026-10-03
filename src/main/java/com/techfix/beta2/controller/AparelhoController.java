package com.techfix.beta2.controller;

import com.techfix.beta2.domain.aparelho.AparelhoDto;
import com.techfix.beta2.domain.aparelho.AparelhoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/aparelho")
public class AparelhoController {

    @Autowired
    private AparelhoService aparelhoService;

    @GetMapping
    public ResponseEntity<List<AparelhoDto>> listarAparelhos (){
        return ResponseEntity.ok(aparelhoService.listarAparelhos());
    }

    @PostMapping
    @Transactional
    public ResponseEntity<AparelhoDto> cadastrarAparelho(@RequestBody AparelhoDto dto){
        return ResponseEntity.ok(aparelhoService.cadastrarAparelho(dto));
    }
}
