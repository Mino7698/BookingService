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