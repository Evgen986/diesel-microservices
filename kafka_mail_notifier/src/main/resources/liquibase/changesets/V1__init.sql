create table buyers.t_client
(
    client_id   serial primary key,
    c_firstname varchar(50) not null,
    c_surname   varchar(50),
    c_email     varchar(50) not null unique
);