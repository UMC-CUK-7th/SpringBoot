create table food
(
    image_url        text        null,
    created_at       datetime(6) null,
    updated_at       datetime    null,
    id               bigint auto_increment
        primary key,
    user_prefer_food int         null
);

