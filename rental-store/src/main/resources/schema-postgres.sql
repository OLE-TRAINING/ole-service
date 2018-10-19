create type status as enum('REGISTERED', 'PENDING', 'INEXISTENT');
create table users(
	email varchar(50) not null,
	password varchar(10) not null,
	complete_name varchar(50) not null,
	username varchar(50) not null,
	registration_status status not null default 'PENDING',
	PRIMARY KEY(email),
	UNIQUE(username)
);