create table user_prefer
(
    created_at datetime(6) null,
    updated_at datetime(6) null,
    user_id    bigint      null,
    id         bigint auto_increment
        primary key,
    food_id    bigint      null,
    constraint food_id
        foreign key (food_id) references food (id),
    constraint user_id
        foreign key (user_id) references user (id)
);

