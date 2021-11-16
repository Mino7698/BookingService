create database booking_project encoding 'UTF-8';

create table if not exists apartment (
	id SERIAL PRIMARY KEY,
	country VARCHAR(50) NOT NULL,
	city VARCHAR(50) NOT NULL,
	street_adress VARCHAR(50) NOT NULL,
	apartment_number INT NOT NULL,
	price VARCHAR(50) NOT NULL
);

create table if not exists client (
	id SERIAL PRIMARY KEY,
	first_name VARCHAR(50) NOT NULL,
	last_name VARCHAR(50) NOT NULL, 
	email VARCHAR(50),
	gender VARCHAR(50) NOT NULL,
	country VARCHAR(50)
);

create table if not exists booking (
    id BIGSERIAL,
	client_id integer not null references client(id),
	apartment_id integer not null references apartment(id),
	start_date_of_booking DATE not null,
	finish_date_of_booking DATE not null,
	constraint booking_pk primary key (client_id,apartment_id,start_date_of_booking)
);

insert into apartment (id, country, city, street_adress, apartment_number, price) values (1, 'United States', 'Newport News', '26 Crowley Plaza', 51, '$1422.62');
insert into apartment (id, country, city, street_adress, apartment_number, price) values (2, 'Malaysia', 'Kuala Lumpur', '7216 Warner Road', 24, '$223.77');
insert into apartment (id, country, city, street_adress, apartment_number, price) values (3, 'Philippines', 'Pambuhan', '6 Mosinee Parkway', 40, '$3452.52');
insert into apartment (id, country, city, street_adress, apartment_number, price) values (4, 'Portugal', 'São Pedro do Estoril', '8 Springview Hill', 48, '$2030.99');
insert into apartment (id, country, city, street_adress, apartment_number, price) values (5, 'China', 'Rongxiang', '59 Bay Alley', 32, '$371.91');
insert into apartment (id, country, city, street_adress, apartment_number, price) values (6, 'Bangladesh', 'Mirzāpur', '9067 Eastwood Street', 77, '$300.09');
insert into apartment (id, country, city, street_adress, apartment_number, price) values (7, 'Malaysia', 'Putrajaya', '95 Sage Junction', 3, '$2269.69');
insert into apartment (id, country, city, street_adress, apartment_number, price) values (8, 'Poland', 'Złotniki Kujawskie', '0715 Lyons Park', 24, '$3408.30');
insert into apartment (id, country, city, street_adress, apartment_number, price) values (9, 'Chad', 'Moundou', '19 Dayton Terrace', 46, '$455.67');
insert into apartment (id, country, city, street_adress, apartment_number, price) values (10, 'Indonesia', 'Heret', '25617 Namekagon Road', 59, '$759.71');

insert into client (id, first_name, last_name, email, gender, country) values (1, 'Marget', 'MacGillacolm', 'mmacgillacolm0@cbslocal.com', 'Genderfluid', 'China');
insert into client (id, first_name, last_name, email, gender, country) values (2, 'Martie', 'Order', 'morder1@craigslist.org', 'Genderfluid', 'Mexico');
insert into client (id, first_name, last_name, email, gender, country) values (3, 'Andie', 'Beagles', 'abeagles2@nymag.com', 'Agender', 'Indonesia');
insert into client (id, first_name, last_name, email, gender, country) values (4, 'Janeen', 'Lutz', 'jlutz3@narod.ru', 'Female', 'Afghanistan');
insert into client (id, first_name, last_name, email, gender, country) values (5, 'Cathi', 'McDowell', 'cmcdowell4@hp.com', 'Polygender', 'France');
insert into client (id, first_name, last_name, email, gender, country) values (6, 'Elysia', 'Carpe', 'ecarpe5@artisteer.com', 'Genderqueer', 'Argentina');
insert into client (id, first_name, last_name, email, gender, country) values (7, 'Malissia', 'Tease', 'mtease6@un.org', 'Non-binary', 'Ukraine');
insert into client (id, first_name, last_name, email, gender, country) values (8, 'Marie-ann', 'Isack', 'misack7@wikipedia.org', 'Bigender', 'Philippines');
insert into client (id, first_name, last_name, email, gender, country) values (9, 'Reinwald', 'Rigate', 'rrigate8@elegantthemes.com', 'Bigender', 'Norway');
insert into client (id, first_name, last_name, email, gender, country) values (10, 'Aleen', 'Castagnasso', 'acastagnasso9@comsenz.com', 'Genderfluid', 'Egypt');

insert into booking (client_id, apartment_id, start_date_of_booking, finish_date_of_booking) values (1,1,'01-01-2001','31-12-2001');
insert into booking (client_id, apartment_id, start_date_of_booking, finish_date_of_booking) values (1,2,'01-01-2002','31-12-2002');
insert into booking (client_id, apartment_id, start_date_of_booking, finish_date_of_booking) values (1,3,'01-06-2001','30-09-2001');
insert into booking (client_id, apartment_id, start_date_of_booking, finish_date_of_booking) values (2,4,'10-10-20010','31-12-2010');
insert into booking (client_id, apartment_id, start_date_of_booking, finish_date_of_booking) values (2,6,'01-01-2007','31-12-2007');
insert into booking (client_id, apartment_id, start_date_of_booking, finish_date_of_booking) values (3,8,'01-01-2009','31-12-2009');