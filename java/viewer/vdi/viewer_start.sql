create database TEST8 on C: alias TEST8 using codeset 1251 territory RU

connect to TEST8 user db2student8 using 8

create table STUDENTS (first varchar(25), second varchar(25), last varchar(25), age integer)
create table TICKETS (subject varchar(10), number integer)
create table COURSES (name varchar(10), cost integer)

insert into STUDENTS values ('������', '�������', '�������������', 22)
insert into STUDENTS values ('������', '��������', '����������', 21)
insert into STUDENTS values ('�������', '������', '����������', 23)

insert into TICKETS values ('��������', 10)
insert into TICKETS values ('���������',7 )
insert into TICKETS values ('������', 1)

insert into COURSES values ('���', 55)
insert into COURSES values ('�����', 13)
insert into COURSES values ('������', 5)

disconnect TEST8
