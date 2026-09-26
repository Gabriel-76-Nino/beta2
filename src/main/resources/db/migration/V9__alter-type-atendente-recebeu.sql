alter table ordens_servicos
alter column atendente_recebeu type varchar(255)
using atendente_recebeu::varchar(255);