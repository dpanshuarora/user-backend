CREATE SCHEMA IF NOT EXISTS logistics;

SET SCHEMA 'logistics';

CREATE TABLE IF NOT EXISTS USER_T
(
    USER_ID     varchar(50) PRIMARY KEY,
    CREATED_ON  timestamp   NOT NULL,
    EMAIL       varchar(50) NOT NULL,
    FIRST_NAME  varchar(50) NOT NULL,
    SECOND_NAME varchar(50) NOT NULL
);


CREATE TABLE IF NOT EXISTS LOCATION_T
(
    ID         integer     NOT NULL,
    USER_ID    varchar(50) NOT NULL,
    CREATED_ON timestamp   NOT NULL,
    LATITUDE   decimal     NOT NULL,
    LONGITUDE  decimal     NOT NULL
);
