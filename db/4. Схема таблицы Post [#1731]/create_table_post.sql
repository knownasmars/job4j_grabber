create table post(
	id serial primary key,
	name varchar(255),
	text text,
	link text,
	created timestamp UNIQUE NOT NULL
);