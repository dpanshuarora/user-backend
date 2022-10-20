CREATE SCHEMA logistics;
SET SCHEMA 'logistics';

CREATE TABLE USER_T (
                        USER_ID varchar(50) PRIMARY KEY,
                        CREATED_ON timestamp NOT NULL,
                        EMAIL varchar(50) NOT NULL,
                        FIRST_NAME varchar(50) NOT NULL,
                        SECOND_NAME varchar(50) NOT NULL
);


CREATE TABLE LOCATION_T (
                            ID integer NOT NULL,
                            USER_ID varchar(50) NOT NULL,
                            CREATED_ON timestamp NOT NULL,
                            LATITUDE decimal NOT NULL,
                            LONGITUDE decimal NOT NULL
);


INSERT INTO logistics.USER_T (USER_ID, CREATED_ON, EMAIL, FIRST_NAME, SECOND_NAME)
VALUES ('2e3b11b0-07a4-4873-8de5-d2ae2eab26b2', timestamp '2022-06-10 04:05:06', 'abc@gmail.com', 'John', 'Doe');

INSERT INTO logistics.LOCATION_T (ID, USER_ID, CREATED_ON, LATITUDE, LONGITUDE)
VALUES (2, '2e3b11b0-07a4-4873-8de5-d2ae2eab26b2', timestamp '2022-03-09 04:05:06', '52.25700491800493', '10.528306568625961');


INSERT INTO logistics.LOCATION_T (ID, USER_ID, CREATED_ON, LATITUDE, LONGITUDE)
VALUES (3, '2e3b11b0-07a4-4873-8de5-d2ae2eab26b2', timestamp '2022-03-09 05:05:06', '50.25700491800493', '15.528306568625961');




INSERT INTO logistics.USER_T (USER_ID, CREATED_ON, EMAIL, FIRST_NAME, SECOND_NAME)
VALUES ('ab', timestamp '2022-01-10 04:05:06', 'abc@gmail.com', 'John', 'Doe');

INSERT INTO logistics.LOCATION_T (ID, USER_ID, CREATED_ON, LATITUDE, LONGITUDE)
VALUES (2, 'ab', timestamp '2022-01-06 04:05:06', '93.25700491800493', '09.528306568625961');


INSERT INTO logistics.LOCATION_T (ID, USER_ID, CREATED_ON, LATITUDE, LONGITUDE)
VALUES (3, 'ab', timestamp '2022-01-04 05:05:06', '94.25700491800493', '09.528306568625961');



INSERT INTO logistics.USER_T (USER_ID, CREATED_ON, EMAIL, FIRST_NAME, SECOND_NAME)
VALUES ('abcd', timestamp '2022-06-10 04:05:06', 'abc@gmail.com', 'Jokhn', 'Doe');

