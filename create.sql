create sequence hibernate_sequence start 1 increment 1;
create table todo (todo_id int4 not null, body varchar(255) not null, created_date timestamp, due_date timestamp, modified_date timestamp, status int4, title varchar(255), user_id int4, primary key (todo_id));
create table user_details (id int4 not null, active boolean, age int4, avatar_url varchar(255), blood_group varchar(255), company varchar(255), created timestamp, email varchar(255), first_name varchar(255) not null, last_name varchar(255) not null, modified timestamp, name varchar(255), password varchar(255) not null, phone_number varchar(255), role int4, user_name varchar(255), verified boolean, primary key (id));
alter table todo add constraint FK7knkxefxb7l5dxvesv8xvyhrf foreign key (user_id) references user_details;
