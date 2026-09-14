package com.techfix.beta2.domain.aparelho;

import com.techfix.beta2.domain.produto.Produto;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import java.util.Map;

@Entity
@Table(name = "aparelhos")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Aparelho {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "id_produto")
    private Produto produto;

    @Enumerated(EnumType.STRING)
    private TipoAparelho tipoAparelho;

    @Enumerated(EnumType.STRING)
    private CondicaoAparelho condicaoAparelho;

    @JdbcTypeCode(SqlTypes.JSON)
    @Column(columnDefinition = "jsonb")
    private Map<String, Object> especificacoes;

    private String imei;
    private String observacoes;
    private String pecasTrocadas;
    private String statusConservacao;

}
