package com.techfix.beta2.controller;

import com.techfix.beta2.domain.orcamento.Orcamento;
import com.techfix.beta2.domain.orcamento.OrcamentoRepository;
import com.techfix.beta2.domain.orcamento.dto.DadosCadastroOrcamento;
import com.techfix.beta2.domain.ordemservico.OrdemServico;
import com.techfix.beta2.domain.ordemservico.OrdemServicoRepository;
import com.techfix.beta2.domain.ordemservico.dto.AlterarStatusOS;
import com.techfix.beta2.domain.ordemservico.dto.DadosAtribuirTecnico;
import com.techfix.beta2.domain.ordemservico.dto.DadosOSCadastro;
import com.techfix.beta2.domain.ordemservico.dto.AtribuirDiagnostico;
import com.techfix.beta2.domain.ordemservico.service.OrdemServicoService;
import com.techfix.beta2.domain.pessoa.Pessoa;
import com.techfix.beta2.domain.pessoa.PessoaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/os")
public class OrdemServicoController {

    @Autowired
    private OrdemServicoRepository ordemServicoRepository;

    @Autowired
    private OrdemServicoService ordemServicoService;

    @Autowired
    private OrcamentoRepository orcamentoRepository;

    @Autowired
    private PessoaRepository pessoaRepository;

//    @PostMapping
//    @Transactional
//    public ResponseEntity cadastrarOS(@RequestBody DadosOSCadastro dto){
//        var pessoa = pessoaRepository.getReferenceById(dto.pessoaId());
//        OrdemServico novaOS = new OrdemServico(dto, pessoa);
//        ordemServicoRepository.save(novaOS);
//        return ResponseEntity.ok(novaOS);
//    }
//
//    @GetMapping
//    public ResponseEntity<List<DadosOSCadastro>> listarOSAberto(){
//        List<DadosOSCadastro> listaOSAberto = ordemServicoService.converteDtoOS(ordemServicoRepository.buscarOSAbertas());
//        return ResponseEntity.ok(listaOSAberto);
//    }
//
//    @PutMapping("/tecnico")
//    @Transactional
//    public ResponseEntity atribuirTecnico (@RequestBody DadosAtribuirTecnico dto){
//        var os = ordemServicoService.atribuirTecnico(dto);
//        return ResponseEntity.ok(os);
//    }
//
//    @PutMapping("/diagnostico")
//    @Transactional
//    public ResponseEntity atribuirDianostico (@RequestBody AtribuirDiagnostico dto){
//        var os = ordemServicoService.atribuirDianostico(dto);
//        return ResponseEntity.ok(os);
//    }
//
//    @PostMapping("/orcamento")
//    @Transactional
//    public ResponseEntity<String> cadastrarOrcamento (@RequestBody List<DadosCadastroOrcamento> dto){
//        List<Orcamento> listaOrcamento = dto.stream().map(d -> new Orcamento(d,
//                                                                ordemServicoRepository.getReferenceById(d.ordemServico()))).toList();
//        orcamentoRepository.saveAll(listaOrcamento);
//        return ResponseEntity.ok().body("tudo ok");
//    }
//
//    @PostMapping("/statusos")
//    @Transactional
//    public ResponseEntity alterarStatusOS (@RequestBody AlterarStatusOS dto){
//        var os = ordemServicoRepository.getReferenceById(dto.id());
//        os.setStatusOS(dto.statusOS());
//        return ResponseEntity.ok(os);
//    }

}
