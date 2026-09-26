alter table usuarios
add constraint uk_nome_usuario unique (nome_usuario);

alter table vendas_cabecalho
add constraint fk_venda_cabecalho_usuario
foreign key (nome_usuario)
references usuarios(nome_usuario)