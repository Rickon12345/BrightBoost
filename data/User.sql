CREATE TABLE user (
id int NOT NULL AUTO_INCREMENT,
user_name varchar(30) NOT NULL,
user_password varchar(30) NOT NULL,
phone_number varchar(20) NOT NULL,
gender varchar(10) NOT NULL,
email varchar(30) NOT NULL,
status varchar(10) NOT NULL,
created_time TIMESTAMP NOT NULL,
role varchar(10) NOT NULL,
PRIMARY KEY (id)
);