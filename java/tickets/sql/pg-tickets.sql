DROP TABLE orders;
DROP TABLE flights;
DROP TABLE aircrafts;
DROP TABLE cities;
DROP TABLE companies; 

CREATE FUNCTION timestamp(varchar(14), varchar(14))
    RETURNS timestamp
    AS 'SELECT to_timestamp(textcat(textcat(text($1), \' \'), text($2)), \'DD.MM.YYYY HH:MI:SS\')'
    LANGUAGE 'sql';

CREATE TABLE cities (
  id_city SERIAL,
  name_city varchar(20) NOT NULL,
  PRIMARY KEY (id_city)
);

CREATE TABLE companies (
  id_company SERIAL,
  name_company varchar(20) NOT NULL,
  PRIMARY KEY (id_company)      	
);

CREATE TABLE aircrafts (
  id_aircraft SERIAL,
  id_company int NOT NULL,
  model varchar(20) NOT NULL,
  count_1_class int NOT NULL,
  count_2_class int NOT NULL,
  PRIMARY KEY (id_aircraft),
  FOREIGN KEY (id_company) REFERENCES companies ON DELETE CASCADE		       
);

CREATE TABLE flights (
  id_flight SERIAL, 
  date_departure timestamp NOT NULL,
  date_arrival timestamp NOT NULL,
  id_departure_city int NOT NULL,
  id_arrival_city int NOT NULL,
  id_aircraft int NOT NULL,
  price_1_class decimal(30,2) NOT NULL,
  price_2_class decimal(30,2) NOT NULL,
  PRIMARY KEY (id_flight),
  FOREIGN KEY (id_departure_city) REFERENCES cities ON DELETE CASCADE,		       
  FOREIGN KEY (id_arrival_city) REFERENCES cities ON DELETE CASCADE,		       
  FOREIGN KEY (id_aircraft) REFERENCES aircrafts ON DELETE CASCADE
);

CREATE TABLE orders (
  id_order SERIAL,
  id_flight int NOT NULL,
  class int NOT NULL,
  count int NOT NULL,
  credit_card varchar(20) NOT NULL,
  PRIMARY KEY (id_order),
  FOREIGN KEY (id_flight) REFERENCES flights ON DELETE CASCADE		       
);

INSERT INTO cities (name_city) VALUES('Moscow');
INSERT INTO cities (name_city) VALUES('Samara');
INSERT INTO cities (name_city) VALUES('London');
INSERT INTO cities (name_city) VALUES('Milan');
INSERT INTO cities (name_city) VALUES('Tokio');
INSERT INTO cities (name_city) VALUES('Paris');
INSERT INTO cities (name_city) VALUES('Madrid');
INSERT INTO cities (name_city) VALUES('Milan');
INSERT INTO cities (name_city) VALUES('Kiev');

INSERT INTO companies (name_company) VALUES('Airflot');
INSERT INTO companies (name_company) VALUES('France Air Lines');
INSERT INTO companies (name_company) VALUES('Italy Air Lines');
INSERT INTO companies (name_company) VALUES('British Airways');

INSERT INTO aircrafts (id_company, model, count_1_class, count_2_class)	VALUES(1, 'TU-154', 56, 74);
INSERT INTO aircrafts (id_company, model, count_1_class, count_2_class)	VALUES(1, 'TU-154', 56, 74);
INSERT INTO aircrafts (id_company, model, count_1_class, count_2_class)	VALUES(2, 'Concord', 85, 58);
INSERT INTO aircrafts (id_company, model, count_1_class, count_2_class)	VALUES(3, 'Boing-747', 120, 100);
INSERT INTO aircrafts (id_company, model, count_1_class, count_2_class)	VALUES(3, 'Boing-747', 120, 100);

INSERT INTO flights (date_departure, date_arrival, id_departure_city, id_arrival_city, id_aircraft, price_1_class, price_2_class)
       VALUES('2002-05-14 05:23:45', '2002-05-15 07:23:45', 1, 3, 1, 300, 200);
INSERT INTO flights (date_departure, date_arrival, id_departure_city, id_arrival_city, id_aircraft, price_1_class, price_2_class)
       VALUES('2002-05-15 05:53:15', '2002-05-15 08:03:45', 9, 8, 4, 350, 200);
INSERT INTO flights (date_departure, date_arrival, id_departure_city, id_arrival_city, id_aircraft, price_1_class, price_2_class)
       VALUES('2002-05-17 01:14:00', '2002-05-17 03:03:56', 6, 5, 3, 1000, 700);
INSERT INTO flights (date_departure, date_arrival, id_departure_city, id_arrival_city, id_aircraft, price_1_class, price_2_class)
       VALUES('2002-05-18 05:23:45', '2002-05-19 08:29:04', 9, 2, 2, 200, 100);
INSERT INTO flights (date_departure, date_arrival, id_departure_city, id_arrival_city, id_aircraft, price_1_class, price_2_class)
       VALUES('2002-05-19 04:10:00', '2002-05-19 06:55:00', 3, 7, 5, 300, 220);

INSERT INTO orders (id_flight, class, count, credit_card) VALUES(1, 1, 2, '1234567890');
INSERT INTO orders (id_flight, class, count, credit_card) VALUES(1, 2, 1, '2935235353');
INSERT INTO orders (id_flight, class, count, credit_card) VALUES(1, 2, 4, '5734573405');
INSERT INTO orders (id_flight, class, count, credit_card) VALUES(1, 1, 7, '3530457304');
INSERT INTO orders (id_flight, class, count, credit_card) VALUES(2, 1, 2, '3534534123');
INSERT INTO orders (id_flight, class, count, credit_card) VALUES(2, 2, 6, '5333443645');
INSERT INTO orders (id_flight, class, count, credit_card) VALUES(2, 2, 2, '4364564564');
INSERT INTO orders (id_flight, class, count, credit_card) VALUES(3, 1, 34, '3534534534');
INSERT INTO orders (id_flight, class, count, credit_card) VALUES(5, 1, 2, '3453453455');
INSERT INTO orders (id_flight, class, count, credit_card) VALUES(5, 2, 1, '534767867');
