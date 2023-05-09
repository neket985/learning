create table AUTHOR
(
    ID         integer PRIMARY KEY,
    FIRST_NAME varchar(255),
    LAST_NAME  varchar(255),
    AGE        integer
);

create table ARTICLE
(
    ID          integer PRIMARY KEY,
    TITLE       varchar(255) not null,
    DESCRIPTION varchar(255),
    AUTHOR_ID   integer
        CONSTRAINT fk_author_id REFERENCES AUTHOR
);