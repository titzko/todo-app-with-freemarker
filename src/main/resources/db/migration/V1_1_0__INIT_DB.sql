create sequence task_id start with 1 increment by 1;
create sequence shopping_list_id start with 1 increment by 1;

create table task
(
    id            bigint  not null,
    description   varchar(255),
    done          boolean not null,
    priority      integer,
    category      integer,
    starting_date timestamp,
    title         varchar(255),
    user_id       bigint,
    primary key (id)
);

create table my_user
(
    id       bigint generated by default as identity,
    enabled  boolean not null,
    password varchar(300),
    role     varchar(255),
    username varchar(50),
    primary key (id)
);

create table shopping_item
(
    id              bigint  not null,
    actual_price    double  not null check (actual_price >= 0),
    amount          integer check (amount >= 0),
    estimated_price double check (estimated_price >= 0),
    name            varchar(50),
    priority        integer,
    purchased       boolean not null,
    purchasing_date timestamp,
    user_id         bigint,
    primary key (id)
);



ALTER SEQUENCE task_id
    RESTART WITH 3