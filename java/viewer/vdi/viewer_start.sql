create database TEST8 on C: alias TEST8 using codeset 1251 territory RU

connect to TEST8 user db2student8 using 8

create table STUDENTS (first varchar(25), second varchar(25), last varchar(25), age integer)
create table TICKETS (subject varchar(10), number integer)
create table COURSES (name varchar(10), cost integer)

insert into STUDENTS values ('Фишкин', 'Генадий', 'Александрович', 22)
insert into STUDENTS values ('Петров', 'Владимир', 'Васильевич', 21)
insert into STUDENTS values ('Крупкин', 'Кузьма', 'Алексеевич', 23)

insert into TICKETS values ('Биология', 10)
insert into TICKETS values ('География',7 )
insert into TICKETS values ('Физика', 1)

insert into COURSES values ('Уха', 55)
insert into COURSES values ('Салат', 13)
insert into COURSES values ('Компот', 5)

disconnect TEST8
