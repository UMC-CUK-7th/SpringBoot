create table user
(
    id         bigint      not null
        primary key,
    name       varchar(45) null,
    point      int         null,
    preference varchar(30) null,
    review     int         null,
    created_at datetime(6) null,
    updated_at datetime(6) null,
    gender     int         null,
    nickname   varchar(20) null,
    phone      int         null,
    birth      date        null,
    address    varchar(45) null,
    email      varchar(45) null,
    nick_nme   varchar(20) null
);

