create table user
(
    id    int auto_increment
        primary key,
    name  varchar(255) not null,
    email varchar(255) not null
);

create table track
(
    lat        decimal(9, 6)                       not null,
    lng        decimal(9, 6)                       not null,
    created_at timestamp default CURRENT_TIMESTAMP not null on update CURRENT_TIMESTAMP,
    user_id    int                                 not null,
    constraint track_user_id_fk
        foreign key (user_id) references user (id)
);