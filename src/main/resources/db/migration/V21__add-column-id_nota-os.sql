alter table ordens_servicos
add column id_nota_venda bigint;

alter table ordens_servicos
add constraint fk_os_nota_venda
foreign key (id_nota_venda)
references vendas_cabecalho(id)