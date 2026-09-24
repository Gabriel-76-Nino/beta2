create table condicoes_pagamento(
    id bigint primary key,
    id_fpg bigint,
    condicao varchar(255),
    quantidade_parcelas integer,

    constraint fk_condicao_pagamento_forma_pagamento
                                foreign key (id_fpg)
                                references formas_pagamento(id_fpg)
)