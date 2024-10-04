create table store
(
    name       varchar(20)   null,
    address    varchar(45)   null,
    rating     decimal(2, 1) null,
    created_at datetime      null,
    updated_at datetime(6)   null,
    id         bigint auto_increment
        primary key,
    region_id  bigint        null,
    constraint region_id
        foreign key (region_id) references region (id)
);

