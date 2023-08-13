create table users
(
    userId   int auto_increment
        primary key,
    username varchar(255) null,
    password varchar(255) null,
    role     varchar(255) null
);

