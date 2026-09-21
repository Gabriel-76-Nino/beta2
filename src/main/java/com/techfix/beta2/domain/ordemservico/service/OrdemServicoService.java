package com.techfix.beta2.domain.ordemservico.service;

import com.techfix.beta2.domain.orcamento.Orcamento;
import com.techfix.beta2.domain.orcamento.OrcamentoRepository;
import com.techfix.beta2.domain.orcamento.dto.DadosCadastroOrcamento;
import com.techfix.beta2.domain.ordemservico.OrdemServico;
import com.techfix.beta2.domain.ordemservico.OrdemServicoRepository;
import com.techfix.beta2.domain.ordemservico.StatusOS;
import com.techfix.beta2.domain.ordemservico.dto.AlterarStatusOS;
import com.techfix.beta2.domain.ordemservico.dto.AtribuirDiagnostico;
import com.techfix.beta2.domain.ordemservico.dto.DadosAtribuirTecnico;
import com.techfix.beta2.domain.ordemservico.dto.DadosOSCadastro;
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

    public DadosOSCadastro cadastrarOS(DadosOSCadastro dto) {
        Pessoa pessoa = pessoaRepository.getReferenceById(dto.pessoa());
        Usuario usuario = usuarioRepository.getReferenceByNomeUsuario(dto.atendenteRecebeu());
        Produto produto = produtoRepository.getReferenceById(dto.produto());
        OrdemServico ordemServico = ordemServicoRepository.save(new OrdemServico(dto, pessoa, usuario, produto));
        return new DadosOSCadastro(ordemServico.getId(), ordemServico.getPessoa().getId(),
                ordemServico.getAparelhoCliente().getId(), ordemServico.getProblemaRelatado(),
                ordemServico.getAcessoriosCliente(), ordemServico.getAtendenteRecebeu().getNomeUsuario(),
                ordemServico.getStatusOS(), ordemServico.getAparelhoCliente().getId(),
                ordemServico.getAparelhoCliente().getProduto().getId(), ordemServico.getAparelhoCliente().getTipoAparelho(),
                ordemServico.getAparelhoCliente().getEspecificacoes(), ordemServico.getAparelhoCliente().getImei());
    }

    public List<DadosOSCadastro> converteDtoOS(List<OrdemServico> dto) {
        return dto.stream().map(
                (o -> new DadosOSCadastro(o.getId(), o.getPessoa().getId(),
                        o.getAparelhoCliente().getId(), o.getProblemaRelatado(),
                        o.getAcessoriosCliente(), o.getAtendenteRecebeu().getNomeUsuario(),
                        o.getStatusOS(), o.getAparelhoCliente().getId(),
                        o.getAparelhoCliente().getProduto().getId(), o.getAparelhoCliente().getTipoAparelho(),
                        o.getAparelhoCliente().getEspecificacoes(), o.getAparelhoCliente().getImei()))).toList();
    }

    public List<DadosOSCadastro> listarOSEmAberto() {
        return converteDtoOS(ordemServicoRepository.buscarOSAbertas());
    }

    public OrdemServico atribuirTecnico(DadosAtribuirTecnico dto) {
        if (dto.tecnicoResponsavel() == null){
            throw new RuntimeException("não pode ser nulo");
        }
        OrdemServico os = ordemServicoRepository.getReferenceById(dto.id());
        Usuario usuario = usuarioRepository.getReferenceByNomeUsuario(dto.tecnicoResponsavel());
        os.setTecnicoResponsavel(usuario);
        os.setStatusOS(StatusOS.DIAGNOSTICO);
        return os;
    }

    public OrdemServico atribuirDianostico(AtribuirDiagnostico dto) {

        var os = ordemServicoRepository.getReferenceById(dto.id());
        if (dto.diagnosticoTecnico() == null || dto.diagnosticoIgualRelato() == null){
            throw new RuntimeException("não pode ser vazio");
        }

        os.setDiagnosticoTecnico(dto.diagnosticoTecnico());
        os.setDiagnosticoIgualRelato(dto.diagnosticoIgualRelato());
        return os;
    }

    public List<DadosCadastroOrcamento> cadastrarOrcamento(List<DadosCadastroOrcamento> dto) {
        List<Orcamento> listaOrcamento = dto.stream().map(d -> new Orcamento(d,
                ordemServicoRepository.getReferenceById(d.ordemServico()))).toList();
        orcamentoRepository.saveAll(listaOrcamento);
        return listaOrcamento.stream()
                .map(o -> new DadosCadastroOrcamento(o.getId(), o.getNumeroItem(),
                        o.getIdProduto(), o.getDescricao(), o.getQuantidade(), o.getPrecoPeca(),
                        o.getPrecoMaoObra(), o.getStatusItemOrcamento(), o.getObservacoes())).toList();
    }

    public DadosOSCadastro alterarStatusOS(AlterarStatusOS dto) {
        OrdemServico os = ordemServicoRepository.getReferenceById(dto.id());
        os.setStatusOS(dto.statusOS());
        List<OrdemServico> ordemParaConverter = new ArrayList<>();
        ordemParaConverter.add(os);
        return converteDtoOS(ordemParaConverter).getFirst();
    }
}
