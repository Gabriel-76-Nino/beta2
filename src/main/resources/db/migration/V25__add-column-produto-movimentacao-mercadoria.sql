alter table movimentacoes_mercadorias
add column id_produto bigint;

alter table movimentacoes_mercadorias
add constraint fk_movimentacao_mercadoria_produto
foreign key (id_produto)
references produtos(id)