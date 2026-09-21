package com.techfix.beta2.controller;

import com.techfix.beta2.domain.usuario.UsuarioDto;
import com.techfix.beta2.domain.usuario.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/usuario")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    @GetMapping
    public ResponseEntity<List<UsuarioDto>> listarUsuarios(){
        List<UsuarioDto> usuarioDto = usuarioService.listarUsuarios();
        return ResponseEntity.ok(usuarioDto);
    }

    @PostMapping
    @Transactional
    public ResponseEntity<UsuarioDto> cadastrarUsuarios(@RequestBody UsuarioDto dto){
        UsuarioDto usuarioDto = usuarioService.cadastrarUsuarios(dto);
        return ResponseEntity.ok(usuarioDto);
    }
}
