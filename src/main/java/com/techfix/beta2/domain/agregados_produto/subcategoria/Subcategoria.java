package com.techfix.beta2.domain.agregados_produto.subcategoria;

import com.techfix.beta2.domain.agregados_produto.categoria.Categoria;
import com.techfix.beta2.domain.agregados_produto.departamento.Departamento;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "subcategorias")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Subcategoria {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String nomeSubcategoria;

    @ManyToOne
    @JoinColumn(name = "id_departamento")
    private Departamento departamento;

    @ManyToOne
    @JoinColumn(name = "id_categoria")
    private Categoria categoria;


}
