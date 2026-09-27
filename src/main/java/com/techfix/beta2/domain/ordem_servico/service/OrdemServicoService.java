package com.techfix.beta2.domain.ordem_servico.service;

import com.techfix.beta2.domain.aparelho_cliente.AparelhoCliente;
import com.techfix.beta2.domain.aparelho_cliente.AparelhoClienteDto;
import com.techfix.beta2.domain.aparelho_cliente.AparelhoClienteRepository;
import com.techfix.beta2.domain.orcamento.Orcamento;
import com.techfix.beta2.domain.orcamento.OrcamentoRepository;
import com.techfix.beta2.domain.orcamento.dto.DadosCadastroOrcamento;
import com.techfix.beta2.domain.orcamento.dto.ListaProdutosCadastroDto;
import com.techfix.beta2.domain.ordem_servico.OrdemServico;
import com.techfix.beta2.domain.ordem_servico.OrdemServicoRepository;
import com.techfix.beta2.domain.ordem_servico.StatusOS;
import com.techfix.beta2.domain.ordem_servico.dto.*;
import com.techfix.beta2.domain.pessoa.Pessoa;
import com.techfix.beta2.domain.pessoa.PessoaRepository;
import com.techfix.beta2.domain.produto.Produto;
import com.techfix.beta2.domain.produto.ProdutoRepository;
import com.techfix.beta2.domain.usuario.Usuario;
import com.techfix.beta2.domain.usuario.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class OrdemServicoService {

    @Autowired
    private OrcamentoRepository orcamentoRepository;
    @Autowired
    private PessoaRepository pessoaRepository;
    @Autowired
    private OrdemServicoRepository ordemServicoRepository;
    @Autowired
    private UsuarioRepository usuarioRepository;
    @Autowired
    private ProdutoRepository produtoRepository;
    @Autowired
    private AparelhoClienteRepository aparelhoClienteRepository;

    public DadosOSCadastro cadastrarOS(DadosOSCadastro dto) {
        Pessoa pessoa = pessoaRepository.getReferenceById(dto.pessoa());
        Usuario usuario = usuarioRepository.getReferenceByNomeUsuario(dto.atendenteRecebeu());
        Optional<AparelhoCliente> aparelhoClienteBusca = aparelhoClienteRepository.findById(dto.aparelhoClienteDto().id());
        AparelhoCliente aparelhoCliente;


        if (aparelhoClienteBusca.isPresent()){
            aparelhoCliente = aparelhoClienteBusca.get();
        } else {
            Produto produto = produtoRepository.getReferenceById(dto.aparelhoClienteDto().produto());
            aparelhoCliente = new AparelhoCliente(null, produto, dto.aparelhoClienteDto().tipoAparelho(),
                    dto.aparelhoClienteDto().especificacoes(), dto.aparelhoClienteDto().imei(), pessoa, null);
        }

        OrdemServico ordemServico = new OrdemServico(dto, pessoa, usuario, aparelhoCliente);
        aparelhoClienteRepository.save(aparelhoCliente);
        ordemServicoRepository.save(ordemServico);

        return converteUmaDtoOS(ordemServico);
    }

    public DadosOSCadastro converteUmaDtoOS(OrdemServico o) {
        return new DadosOSCadastro(o.getId(), o.getPessoa().getId(),
                        o.getAparelhoCliente().getId(),
                        new AparelhoClienteDto(
                                o.getAparelhoCliente().getId(),
                                o.getAparelhoCliente().getProduto().getId(),
                                o.getAparelhoCliente().getTipoAparelho(),
                                o.getAparelhoCliente().getEspecificacoes(),
                                o.getAparelhoCliente().getImei(),
                                o.getAparelhoCliente().getPessoa().getId()),
                        o.getProblemaRelatado(), o.getAcessoriosCliente(),
                        o.getAtendenteRecebeu().getNomeUsuario(), o.getStatusOS());
    }

    public List<DadosOSCadastro> converteDtoOS(List<OrdemServico> dto) {
        return dto.stream().map(this::converteUmaDtoOS).toList();
    }

    public List<DadosOSCadastro> listarOSEmAberto() {
        return converteDtoOS(ordemServicoRepository.buscarOSAbertas());
    }

    public TodosDadosOS atribuirTecnico(DadosAtribuirTecnico dto) {
        if (dto.tecnicoResponsavel() == null){
            throw new RuntimeException("não pode ser nulo");
        }
        OrdemServico os = ordemServicoRepository.getReferenceById(dto.id());
        Usuario usuario = usuarioRepository.getReferenceByNomeUsuario(dto.tecnicoResponsavel());
        os.setTecnicoResponsavel(usuario);
        os.setStatusOS(StatusOS.DIAGNOSTICO);
        return converteOSParaDto(os);
    }

    private TodosDadosOS converteOSParaDto(OrdemServico os) {

        return new TodosDadosOS(os.getId(), os.getPessoa().getId(), os.getDataEntrada(),
                new AparelhoClienteDto(os.getAparelhoCliente().getId(),
                        os.getAparelhoCliente().getProduto().getId(),
                        os.getAparelhoCliente().getTipoAparelho(),
                        os.getAparelhoCliente().getEspecificacoes(),
                        os.getAparelhoCliente().getImei(),
                        os.getAparelhoCliente().getPessoa().getId()),
                os.getProblemaRelatado(), os.getFotoEntrada(), os.getAcessoriosCliente(),
                os.getAtendenteRecebeu().getNomeUsuario(),
                os.getTecnicoResponsavel().getNomeUsuario(),
                os.getDiagnosticoTecnico(), os.getDiagnosticoIgualRelato(),
                new DadosCadastroOrcamento(os.getId(), os.getOrcamento().stream()
                        .map(o -> new ListaProdutosCadastroDto(
                                o.getNumeroItem(), o.getIdProduto(), o.getDescricao(),
                                o.getQuantidade(), o.getPrecoPeca(), o.getPrecoMaoObra(),
                                o.getStatusItemOrcamento(), o.getObservacoes())).toList()),
                os.getStatusOS(), os.getTestesRealizados(), os.getCpfRetirante(),
                os.getNomeRetirante(), os.getDataRetirada());

    }

    public TodosDadosOS atribuirDianostico(AtribuirDiagnostico dto) {

        var os = ordemServicoRepository.getReferenceById(dto.id());
        if (dto.diagnosticoTecnico() == null || dto.diagnosticoIgualRelato() == null){
            throw new RuntimeException("não pode ser vazio");
        }

        os.setDiagnosticoTecnico(dto.diagnosticoTecnico());
        os.setDiagnosticoIgualRelato(dto.diagnosticoIgualRelato());

        return converteOSParaDto(os);
    }

    public DadosCadastroOrcamento cadastrarOrcamento(DadosCadastroOrcamento dto) {
        OrdemServico ordemServico = ordemServicoRepository.getReferenceById(dto.ordemServico());
        List<Orcamento> orcamento = dto.listaItens().stream()
                        .map(o -> new Orcamento(o, ordemServico)).toList();
        orcamentoRepository.saveAll(orcamento);
        return new DadosCadastroOrcamento(orcamento.getFirst().getId(), orcamento.stream()
                .map(o -> new ListaProdutosCadastroDto(o.getNumeroItem(),
                        o.getIdProduto(), o.getDescricao(), o.getQuantidade(), o.getPrecoPeca(),
                        o.getPrecoMaoObra(), o.getStatusItemOrcamento(), o.getObservacoes())).toList());
    }

    public TodosDadosOS alterarStatusOS(AlterarStatusOS dto) {
        OrdemServico os = ordemServicoRepository.getReferenceById(dto.id());
        os.setStatusOS(dto.statusOS());

        return converteOSParaDto(os);

    }
}
