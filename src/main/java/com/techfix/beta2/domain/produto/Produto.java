package com.techfix.beta2.domain.produto;

import com.techfix.beta2.domain.aparelho.Aparelho;
import com.techfix.beta2.domain.compra.RequisicaoCorpo;
import com.techfix.beta2.domain.pessoa.Pessoa;
import com.techfix.beta2.domain.produto.dto.CadastroProdutoDto;
import com.techfix.beta2.domain.venda.VendaCorpo;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.List;

@Entity
@Table(name = "produtos")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Produto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String descricao;
    private String descricaoComercial;

    @Enumerated(EnumType.STRING)
    private Tipo tipo;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_marca")
    private Marca marca;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_departamento")
    private Departamento departamento;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_categoria")
    private Categoria categoria;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_subcategoria")
    private Subcategoria subcategoria;

    @ManyToOne
    @JoinColumn(name = "id_fornecedor")
    private Pessoa fornecedor;

    @OneToMany(mappedBy = "produto", cascade = CascadeType.ALL)
    private List<CodigoBarras> codigoBarras;

    @OneToMany(mappedBy = "produto", cascade = CascadeType.ALL)
    private List<Referencia> referencia;

    private Double margem;
    private BigDecimal custoMedio;
    private BigDecimal precoVenda;
    private BigDecimal precoSugestao;
    private int minimo;
    private int maximo;

    @OneToOne(mappedBy = "produto", cascade = CascadeType.ALL)
    private Estoque estoque;

    @ManyToOne
    @JoinColumn(name = "id_garantia")
    private Garatia garatia;

    @OneToMany(mappedBy = "produto", cascade = CascadeType.ALL)
    private List<Compatibilidade> compatibilidade;

    @ManyToOne
    @JoinColumn(name = "id_unidade_medida")
    private UnidadeMedida unidadeMedida;

    private Boolean ativo;

    @OneToMany(mappedBy = "produto")
    private List<VendaCorpo> vendasCorpo;

    private String foto;

    @OneToMany(mappedBy = "produto", cascade = CascadeType.ALL)
    private List<Aparelho> aparelhos;

    @OneToMany(mappedBy = "produto")
    private List<RequisicaoCorpo> requisicaoCorpo;

    public Produto(CadastroProdutoDto dto, Marca marca, Departamento departamento, Categoria categoria, Subcategoria subcategoria, Pessoa fornecedor, Garatia garantia, UnidadeMedida unidadeMedida) {
        this.descricao = dto.descricao();
        this.descricaoComercial = dto.descricaoComercial();
        this.tipo = dto.tipo();
        this.marca = marca;
        this.departamento = departamento;
        this.categoria = categoria;
        this.subcategoria = subcategoria;
        this.fornecedor = fornecedor;
        new CodigoBarras(null, this, dto.codigoBarras(), unidadeMedida);
        new Referencia(null, fornecedor, this, dto.referencia());
        this.margem = dto.margem();
        this.minimo = dto.minimo();
        this.maximo = dto.maximo();
        new Estoque(null, this, 0, 0, 0, 0);
        this.garatia = garantia;
        this.unidadeMedida = unidadeMedida;
        this.ativo = true;
        this.foto = dto.foto();
    }
}
