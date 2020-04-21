create database userdata;

drop database userdata;

use userdata;

CREATE TABLE userdt(
fullname varchar(40),
email varchar(40),
phone varchar(15),
pass varchar(15)
);   

Insert into userdt(fullname,email,phone,pass) VALUES('ajay','aj@gmail.com','1234567890','qwerty');
Insert into userdt(fullname,email,phone,pass) VALUES('arun','ar@gmail.com','9876543210','asdfgh');

select *from userdt;

drop table userdt;


CREATE TABLE complaintdt(
location varchar(30),
category varchar(20),
comptype varchar(20),
compdetails varchar(100)
); 

Insert into complaintdt(location,category,comptype,compdetails) VALUES('montreal','Electricity','Complaint','slow internet');

select * from complaintdt;