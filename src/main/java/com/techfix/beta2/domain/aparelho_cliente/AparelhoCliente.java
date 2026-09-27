package com.techfix.beta2.domain.aparelho_cliente;

import com.techfix.beta2.domain.aparelho.TipoAparelho;
import com.techfix.beta2.domain.ordem_servico.OrdemServico;
import com.techfix.beta2.domain.pessoa.Pessoa;
import com.techfix.beta2.domain.produto.Produto;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import java.util.List;
import java.util.Map;

@Entity
@Table(name = "aparelhos_clientes")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AparelhoCliente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "id_produto")
    private Produto produto;

    @Enumerated(EnumType.STRING)
    private TipoAparelho tipoAparelho;

    @JdbcTypeCode(SqlTypes.JSON)
    @Column(columnDefinition = "jsonb")
    private Map<String, Object> especificacoes;

    private String imei;

    @ManyToOne
    @JoinColumn(name = "id_pessoa")
    private Pessoa pessoa;

    @OneToMany(mappedBy = "aparelhoCliente")
    private List<OrdemServico> ordemServico;

    @Override
    public String toString() {
        return "produto " + produto.getDescricao();
    }
}
