create table user_mission
(
    created_at datetime(6) null,
    updated_at datetime(6) null,
    user_id    bigint      null,
    mission_id bigint      null,
    id         bigint auto_increment
        primary key,
    constraint fk_mission_id
        foreign key (mission_id) references mission (id),
    constraint fk_user_id
        foreign key (user_id) references user (id)
);

