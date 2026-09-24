package com.techfix.beta2.domain.venda;

import com.techfix.beta2.domain.contas_receber.ContasReceber;
import com.techfix.beta2.domain.contas_receber.ContasReceberService;
import com.techfix.beta2.domain.contas_receber.StatusParcela;
import com.techfix.beta2.domain.desconto.TabelaDescontoRepository;
import com.techfix.beta2.domain.ordem_servico.OrdemServico;
import com.techfix.beta2.domain.ordem_servico.OrdemServicoRepository;
import com.techfix.beta2.domain.pagamento.*;
import com.techfix.beta2.domain.pessoa.Pessoa;
import com.techfix.beta2.domain.pessoa.PessoaRepository;
import com.techfix.beta2.domain.produto.ProdutoRepository;
import com.techfix.beta2.domain.usuario.Usuario;
import com.techfix.beta2.domain.usuario.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VendaCabecalhoService {

    @Autowired
    private VendaCabecalhoRepository cabecalhoRepository;
    @Autowired
    private VendaCorpoRepository corpoRepository;
    @Autowired
    private PessoaRepository pessoaRepository;
    @Autowired
    private ProdutoRepository produtoRepository;
    @Autowired
    private UsuarioRepository usuarioRepository;
    @Autowired
    private OrdemServicoRepository ordemServicoRepository;
    @Autowired
    private FormaPagamentoRepository formaPagamentoRepository;
    @Autowired
    private CondicaoPagamentoRepository condicaoPagamentoRepository;
    @Autowired
    private TabelaDescontoRepository tabelaDescontoRepository;
    @Autowired
    private ContasReceberService contasReceberService;

    public VendaCabecalhoDto cadastrarVenda(VendaCabecalhoDto dto) {

        Pessoa cliente = pessoaRepository.getReferenceById(dto.cliente());
        Usuario usuario = usuarioRepository.getReferenceByNomeUsuario(dto.nomeUsuario());
        OrdemServico os = ordemServicoRepository.getReferenceById(dto.os());

        VendaCabecalho vendaCabecalho = new VendaCabecalho(cliente, usuario, os);

        List<VendaCorpo> vendaCorpo = dto.itens().stream()
                .map(p -> new VendaCorpo(null, vendaCabecalho,
                        p.numeroItem(), produtoRepository.getReferenceById(p.produto()),
                        p.quantidade(), p.precoVenda(), p.valorDesconto(),
                        p.percentualDesconto(), p.custoMedioVenda(),
                        p.margemVenda())).toList();

        List<ContasReceber> contasReceber = contasReceberService.cadastrarFormaDePagamento(vendaCabecalho, dto);

        vendaCabecalho.setItens(vendaCorpo);
        vendaCabecalho.setContasReceber(contasReceber);

        return converteVendaParaDto(vendaCabecalho);
    }

    private VendaCabecalhoDto converteVendaParaDto(VendaCabecalho venda) {
        List<FormaPagamentoECondicaoDto> fpg = venda.getContasReceber().stream()
                .map(f -> new FormaPagamentoECondicaoDto(f.getFpg().getIdFpg(),
                        null)).toList();
        List<VendaCorpoDto> vendaCorpoDto = venda.getItens().stream()
                .map(v -> new VendaCorpoDto(v.getId(), v.getCabecalho().getId(),
                        v.getNumeroItem(), v.getProduto().getId(), v.getQuantidade(),
                        v.getPrecoVenda(), v.getValorDesconto(), v.getPercentualDesconto(),
                        v.getCustoMedioVenda(), v.getMargemVenda())).toList();
        return new VendaCabecalhoDto(venda.getId(),
                venda.getNumeroNota(), venda.getCliente().getId(), venda.getDataVenda(),
                venda.getNomeUsuario().getNomeUsuario(), venda.getOs().getId(), fpg, vendaCorpoDto);
    }
}
