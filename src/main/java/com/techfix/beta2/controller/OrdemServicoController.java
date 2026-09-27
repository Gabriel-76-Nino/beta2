package com.techfix.beta2.controller;

import com.techfix.beta2.domain.orcamento.dto.DadosCadastroOrcamento;
import com.techfix.beta2.domain.ordem_servico.OrdemServico;
import com.techfix.beta2.domain.ordem_servico.dto.*;
import com.techfix.beta2.domain.ordem_servico.service.OrdemServicoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/os")
public class OrdemServicoController {

    @Autowired
    private OrdemServicoService ordemServicoService;

    @PostMapping
    @Transactional
    public ResponseEntity<DadosOSCadastro> cadastrarOS(@RequestBody DadosOSCadastro dto){
        DadosOSCadastro novaOS = ordemServicoService.cadastrarOS(dto);
        return ResponseEntity.ok(novaOS);
    }

    @GetMapping
    public ResponseEntity<List<DadosOSCadastro>> listarOSAberto(){
        List<DadosOSCadastro> listaOSAberto = ordemServicoService.listarOSEmAberto();
        return ResponseEntity.ok(listaOSAberto);
    }

    @PutMapping("/tecnico")
    @Transactional
    public ResponseEntity<TodosDadosOS> atribuirTecnico (@RequestBody DadosAtribuirTecnico dto){
        TodosDadosOS os = ordemServicoService.atribuirTecnico(dto);
        return ResponseEntity.ok(os);
    }

    @PutMapping("/diagnostico")
    @Transactional
    public ResponseEntity<TodosDadosOS> atribuirDianostico (@RequestBody AtribuirDiagnostico dto){
        TodosDadosOS os = ordemServicoService.atribuirDianostico(dto);
        return ResponseEntity.ok(os);
    }

    @PostMapping("/orcamento")
    @Transactional
    public ResponseEntity<DadosCadastroOrcamento> cadastrarOrcamento (@RequestBody DadosCadastroOrcamento dto){
        DadosCadastroOrcamento dadosCadastroOrcamento = ordemServicoService.cadastrarOrcamento(dto);
        return ResponseEntity.ok(dadosCadastroOrcamento);
    }

    @PostMapping("/statusos")
    @Transactional
    public ResponseEntity<TodosDadosOS> alterarStatusOS (@RequestBody AlterarStatusOS dto){
        TodosDadosOS dadosOSCadastro = ordemServicoService.alterarStatusOS(dto);
        return ResponseEntity.ok(dadosOSCadastro);
    }

}
