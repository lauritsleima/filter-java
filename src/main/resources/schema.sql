create table filter(
    id int auto_increment primary key,
    name varchar(255) not null,
    created_at timestamp default current_timestamp
);

create table criteria(
    id int auto_increment primary key,
    type varchar(255) not null,
    comparison varchar(255) not null,
    value_text varchar(255),
    value_number decimal(10,2),
    value_date date,
    filter_id int,
    foreign key (filter_id) references filter(id) on delete cascade,
    check((value_text is not null)
        or (value_number is not null)
        or (value_date is not null))
);
