create table users(
	email varchar(50) not null,
	password char(32) not null,
	complete_name varchar(50) not null,
	username varchar(50) not null,
	registration_status varchar(20) not null default 'PENDING',
	confirmation_token char(6) default null,
	PRIMARY KEY(email),
	UNIQUE(username)
);