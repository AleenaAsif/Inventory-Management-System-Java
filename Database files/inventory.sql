create table inventory
(
    id               int auto_increment
        primary key,
    item_name        varchar(255) null,
    item_quantity    int          null,
    item_category_id int          null,
    item_location_id int          null,
    constraint inventory_ibfk_1
        foreign key (item_category_id) references itemcategory (id),
    constraint inventory_ibfk_2
        foreign key (item_location_id) references itemlocation (id)
);

create index item_category_id
    on inventory (item_category_id);

create index item_location_id
    on inventory (item_location_id);

