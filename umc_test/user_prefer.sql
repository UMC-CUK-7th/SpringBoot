create table user_prefer
(
    created_at datetime(6) null,
    updated_at datetime(6) null,
    user_id    bigint      null,
    constraint user_id
        foreign key (user_id) references user (id)
);

