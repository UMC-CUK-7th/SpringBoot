create table review
(
    content    varchar(45)   null,
    rating     decimal(2, 1) null,
    id         int auto_increment
        primary key,
    created_at datetime      null,
    updated_at datetime      null,
    store_id   bigint        null,
    constraint store_id
        foreign key (store_id) references store (id)
);

