package com.techfix.beta2.domain.contas_receber;

import com.techfix.beta2.domain.pagamento.*;
import com.techfix.beta2.domain.venda.VendaCabecalho;
import com.techfix.beta2.domain.venda.VendaCabecalhoDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

@Service
public class ContasReceberService {

    @Autowired
    private FormaPagamentoRepository formaPagamentoRepository;
    @Autowired
    private CondicaoPagamentoRepository condicaoPagamentoRepository;
    @Autowired
    private DiasCondicaoPagamentoRepository diasCondicaoPagamentoRepository;

    public List<ContasReceber> cadastrarFormaDePagamento(VendaCabecalho vendaCabecalho, VendaCabecalhoDto dto) {

        List<CondicaoPagamento> condicaoPagamento = dto.fpgs().stream()
                .map(c -> condicaoPagamentoRepository
                        .getReferenceById(c.idCondicao())).toList();

        List<CalcularParcelas> calculo = calcularNumeroParcela(vendaCabecalho, condicaoPagamento);

        return calculo.stream()
                .map(c -> new ContasReceber(null, vendaCabecalho, formaPagamentoRepository.getReferenceById(c.idFpg()),
                        condicaoPagamentoRepository.getReferenceById(c.idCondicao()), c.numeroParcela(), c.valorParcela(), c.vencimento(),
                        StatusParcela.EM_ABERTO)).toList();
    }

    private List<CalcularParcelas> calcularNumeroParcela(VendaCabecalho vendaCabecalho, List<CondicaoPagamento> condicaoPagamento) {
        List<DiasCondicaoPagamento> diasPagamentos = diasCondicaoPagamentoRepository.findAllByIdCondicao(condicaoPagamento);
        AtomicInteger contador = new AtomicInteger(1);


        return diasPagamentos.stream()
                .map(d -> new CalcularParcelas(d.getIdCondicao().getIdFpg().getIdFpg(), d.getIdCondicao().getId(),
                        contador.getAndIncrement(), vendaCabecalho.getValorTotalVenda() / d.getIdCondicao().getQuantidadeParcelas(), vendaCabecalho.getDataVenda().plusDays(d.getDias()))).toList();
    }
}
