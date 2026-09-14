package com.techfix.beta2.domain.orcamento;

import com.techfix.beta2.domain.orcamento.dto.DadosCadastroOrcamento;
import com.techfix.beta2.domain.ordemservico.OrdemServico;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "orcamentos")
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Orcamento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private OrdemServico ordemServico;
    private Integer numeroItem;
    private Long idProduto;
    private String descricao;
    private Integer quantidade;
    private BigDecimal precoPeca;
    private BigDecimal precoMaoObra;

    @Enumerated(EnumType.STRING)
    private StatusItemOrcamento statusItemOrcamento;
    private String observacoes;
    private LocalDateTime horarioOrcamento;
    private LocalDate validadeOrcamento;

    private String fotoDiagnostico;
    private String fotoConcerto;

    public Orcamento(DadosCadastroOrcamento dto, OrdemServico os) {
        this.ordemServico = os;
        this.numeroItem = dto.numeroItem();
        this.idProduto = dto.idProduto();
        this.descricao = dto.descricao();
        this.quantidade = dto.quantidade();
        this.precoPeca = dto.precoPeca();
        this.precoMaoObra = dto.precoMaoObra();
        this.statusItemOrcamento = StatusItemOrcamento.PENDENTE;
        this.observacoes = dto.observacoes();
    }

}
