create table author
(
    id       int8 not null,
    name     varchar(255),
    surname  varchar(255),
    birthday date,
    primary key (id)
);

create table book
(
    id     int8 not null,
    name   varchar(255),
    volume int4,
    date_of_publishing date,
    is_received_book boolean,
    primary key (id)
);

create table book_author
(
    id        int8 not null,
    author_id int8,
    book_id   int8,
    primary key (id),
    FOREIGN KEY (author_id) REFERENCES author (id),
    FOREIGN KEY (book_id) REFERENCES book (id)
);

create table passport
(
    id                   int8 not null,
    name                 varchar(255),
    surname              varchar(255),
    birthday             date,
    number               varchar(255),
    series               varchar(255),
    authority_issuer     varchar(255),
    date_signing         date,
    primary key (id)
);

create table client
(
    id                   int8 not null,
    phone                varchar(255),
    email                varchar(255),
    passport_id          int8 not null,
    primary key (id),
    FOREIGN KEY (passport_id) REFERENCES passport (id)
);

create table library_card
(
    id                  int8 not null,
    client_id           int8 not null,
    primary key (id),
    FOREIGN KEY (client_id) REFERENCES client (id)
);

create table received_book
(
    id                          int8 not null,
    book_id                     int8 not null,
    library_card_id             int8 not null,
    date_of_book_receiving      date,
    date_of_book_return         date,
    primary key (id),
    FOREIGN KEY (book_id) REFERENCES book (id),
    FOREIGN KEY (library_card_id) REFERENCES library_card (id)
);