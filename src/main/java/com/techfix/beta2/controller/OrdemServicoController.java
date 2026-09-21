package com.techfix.beta2.controller;

import com.techfix.beta2.domain.orcamento.dto.DadosCadastroOrcamento;
import com.techfix.beta2.domain.ordemservico.OrdemServico;
import com.techfix.beta2.domain.ordemservico.dto.AlterarStatusOS;
import com.techfix.beta2.domain.ordemservico.dto.DadosAtribuirTecnico;
import com.techfix.beta2.domain.ordemservico.dto.DadosOSCadastro;
import com.techfix.beta2.domain.ordemservico.dto.AtribuirDiagnostico;
import com.techfix.beta2.domain.ordemservico.service.OrdemServicoService;
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
    public ResponseEntity<OrdemServico> atribuirTecnico (@RequestBody DadosAtribuirTecnico dto){
        OrdemServico os = ordemServicoService.atribuirTecnico(dto);
        return ResponseEntity.ok(os);
    }

    @PutMapping("/diagnostico")
    @Transactional
    public ResponseEntity atribuirDianostico (@RequestBody AtribuirDiagnostico dto){
        var os = ordemServicoService.atribuirDianostico(dto);
        return ResponseEntity.ok(os);
    }

    @PostMapping("/orcamento")
    @Transactional
    public ResponseEntity<List<DadosCadastroOrcamento>> cadastrarOrcamento (@RequestBody List<DadosCadastroOrcamento> dto){
        List<DadosCadastroOrcamento> dadosCadastroOrcamento = ordemServicoService.cadastrarOrcamento(dto);
        return ResponseEntity.ok(dadosCadastroOrcamento);
    }

    @PostMapping("/statusos")
    @Transactional
    public ResponseEntity<DadosOSCadastro> alterarStatusOS (@RequestBody AlterarStatusOS dto){
        DadosOSCadastro dadosOSCadastro = ordemServicoService.alterarStatusOS(dto);
        return ResponseEntity.ok(dadosOSCadastro);
    }

}
