package com.techfix.beta2.controller;

import com.techfix.beta2.domain.produto.dto.CadastroProdutoDto;
import com.techfix.beta2.domain.produto.dto.ProdutoDto;
import com.techfix.beta2.domain.produto.service.ProdutoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/produto")
public class ProdutoController {

    @Autowired
    private ProdutoService produtoService;

    @GetMapping
    public ResponseEntity<List<ProdutoDto>> listarProdutos(){
        List<ProdutoDto> produtos = produtoService.listarProdutos();
        return ResponseEntity.ok(produtos);
    }

    @PostMapping
    @Transactional
    public ResponseEntity<ProdutoDto> cadastroProduto(@RequestBody CadastroProdutoDto dto){
        ProdutoDto produtoDto = produtoService.cadastrarProduto(dto);
        return ResponseEntity.ok(produtoDto);
    }


}
