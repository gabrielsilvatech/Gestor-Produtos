create table produtos (

    id bigint not null auto_increment,
    nome varchar(100) not null,
    cor varchar(100) not null,
    categoria varchar(100) not null,
    quantidade int not null,
    valor decimal(10, 2),
    ativo tinyint not null,

    primary key(id)

);