create table mission
(
    point               int         null,
    deadline            datetime    null,
    mission_explanation varchar(90) null,
    created_at          datetime    null,
    updated_at          datetime(6) null,
    id                  bigint auto_increment
        primary key,
    store_id            int         null
);

