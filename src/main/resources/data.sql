--INSERTS WORKER

INSERT INTO WORKER (WORKER_TYPE, ID, NAME, LASTNAME, MAIL, PHONE_NUMBER) VALUES('Facility', 1, 'Ruben', 'Zimmerman', 'fm1@boullosa.ch', '+41791234567');
INSERT INTO WORKER (WORKER_TYPE, ID, NAME, LASTNAME, MAIL, PHONE_NUMBER) VALUES('Facility', 2, 'José', 'Garcia', 'fm3@boullosa.ch', '+41791234568');
INSERT INTO WORKER (WORKER_TYPE, ID, NAME, LASTNAME, MAIL, PHONE_NUMBER) VALUES('Facility', 3, 'Mario', 'Rossi', 'fm3@boullosa.ch', '+41791234569');

--INSERTS AREA

INSERT INTO AREA (ID, NAME, DESCRIPTION) VALUES(1, 'Seeufer-Rechts', 'Lorem ipsum dolor sit amet, consectetur adipisicing elit.');
INSERT INTO AREA (ID, NAME, DESCRIPTION) VALUES(2, 'Hauptbahnhof','Lorem ipsum dolor sit amet, consectetur adipisicing elit.');

--INSERTS PERSONS

INSERT INTO PERSON (ID, NAME, LASTNAME) VALUES(33, 'Hans', 'Mustermann');

--INSERTS FACILITY_WORKERS_AREAS

INSERT INTO FACILITY_WORKERS_AREAS (AREA_ID, FACILITY_WORKER_ID) VALUES(1, 1);
INSERT INTO FACILITY_WORKERS_AREAS (AREA_ID, FACILITY_WORKER_ID) VALUES(1, 3);
INSERT INTO FACILITY_WORKERS_AREAS (AREA_ID, FACILITY_WORKER_ID) VALUES(2, 2);
INSERT INTO FACILITY_WORKERS_AREAS (AREA_ID, FACILITY_WORKER_ID) VALUES(2, 1);

--INSERTS BUILDING

INSERT INTO BUILDING (ID, STREET, BUILDING_NUMBER, BUILDING_STATE, COUNTRY, PLACE, POST_CODE, AREA_ID) VALUES(5, 'Seestrasse', 1, 2, 'Switzerland', 'Zurich', '8001', 1);

--INSERTS DWELLINGS

INSERT INTO DWELLING (ID, FLOOR, DOOR, BUILDING_ID) VALUES(123, 2, 2, 5);

--INSERTS DWELLINGS_PERSONS

INSERT INTO DWELLING_PERSONS (PERSON_ID, DWELLING_ID) VALUES(33, 123);

--INSERTS DEVICE

INSERT INTO DEVICE (ID, DWELLING_ID, LOCATION_IN_DWELLING) VALUES('2e001f001247343438323536', '123', 'Küche');
INSERT INTO DEVICE (ID, DWELLING_ID) VALUES('2e001f001247338323536578', '123', 'Bad');
INSERT INTO DEVICE (ID, DWELLING_ID) VALUES('2e001f001247343832336Z9J', '123', 'WC');
INSERT INTO DEVICE (ID, DWELLING_ID) VALUES('2e001f001247343832353693', '123', 'Waschküche');
INSERT INTO DEVICE (ID, DWELLING_ID) VALUES('2e001f001247343832353634', '123', 'Küche');
