alter table orcamentos
add column horario_orcamento timestamp;

alter table orcamentos
add column validade_orcamento timestamp;

alter table orcamentos
add column foto_diagnostico varchar(255);

alter table orcamentos
add column foto_concerto varchar(255)