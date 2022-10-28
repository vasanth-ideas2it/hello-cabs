create table user(id int primary key auto_increment, username varchar(20), password varchar(20));

create table payment(id int primary key auto_increment, payment_mode varchar(50), payment_status varchar(50));

create table location(id int primary key auto_increment,location_name varchar(50));

create table cab_category(id int primary key auto_increment, cab_type varchar(30), initial_fare double, extra_km_fare double, additional_fare double);

CREATE TABLE customer (id int NOT NULL AUTO_INCREMENT,  name varchar(30) DEFAULT NULL,  mobile_number bigint(20) DEFAULT NULL,  email varchar(20) DEFAULT NULL,  PRIMARY KEY (id));

create table ride(id int primary key auto_increment, pickup_location int, drop_location int, ride_booked_time timestamp, ride_picked_time timestamp, ride_dropped_time timestamp, price double, passenger_mobile_number bigint, rating double, ride_status varchar(20), customer_id int, cab_id int);
alter table ride add constraint fk_location_id foreign key(pickup_location) references location(id);
alter table ride add constraint fk_location_idd foreign key(drop_location) references location(id);
alter table ride add constraint fk_cab_id foreign key(cab_id) references cab(id);
alter table ride add constraint fk_customer_id foreign key(customer_id) references customer(id);

create table cab(id int primary key auto_increment,driver_name varchar(30),cab_number varchar(15),gender varchar(10),mobile_number bigint,email varchar(30),license_number varchar(30),car_model varchar(20),current_location varchar(15),cab_status varchar(20),driver_rating double, cab_category_id int, password varchar(20));
alter table cab add constraint fk_cab_category_id foreign key(cab_category_id) references cab_category(id);

