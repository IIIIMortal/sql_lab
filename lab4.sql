-- 创建db2020_lab4数据库
drop database if exists db2021_lab4;
create database db2021_lab4;
use db2021_lab4;

-- 创建四个表：employee, company, works, manages
create table employee(
    employee_name varchar(20),
    street varchar(15),
    city varchar(15),
    primary key (employee_name)
)ENGINE=InnoDB DEFAULT CHARSET=utf8;

create table company(
    company_name varchar(20),
    city varchar(15),
    primary key (company_name)
)ENGINE=InnoDB DEFAULT CHARSET=utf8;

create table works(
    employee_name varchar(20),
    company_name varchar(20),
    salary int, 
    primary key(employee_name),
    -- 这里我只定义了一个外键，而且是no action，等同于 restrict, 当在外键的来源表中删除数据时，查找该数据是否有对应外键，如果有则不允许删除
    foreign key (employee_name) references employee(employee_name)
        on delete cascade
        on update cascade,
    foreign key (company_name) references company(company_name)
        on delete set null
        on update cascade
)ENGINE=InnoDB DEFAULT CHARSET=utf8;

create table manages(
    employee_name varchar(20),
    manager_name varchar(20),
    primary key(employee_name),
    foreign key ( employee_name ) references employee(employee_name)
        on delete cascade
        on update cascade,
    foreign key(manager_name) references employee(employee_name)
        on delete set null
        on update cascade
)ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- 往四个表中插入一定的数据
insert into company(company_name, city) VALUES
("Bertelsmann", "Cambridge"),
("Chloris", "Nottingham"),
("Eldat GmbH", "Birmingham"),
("Greenteam", "Cambridge");

insert into employee(employee_name, street, city) VALUES
("Amanda", "King", "Birmingham"),
("Betty", "Strand", "Cambridge"),
("Dean", "Dover", "Sheffield"),
("Kevin", "King", "Birmingham"),
("Lily", "Regent", "Nottingham"),
("Mark", "Dover", "Sheffield"),
("Richard", "Strand", "Cambridge"),
("Whitney", "Regent", "Nottingham");

insert into works(employee_name, company_name, salary) VALUES
("Amanda", "Greenteam", 2800),
("Betty", "Bertelsmann", 3200),
("Dean", "Eldat GmbH", 3100),
("Kevin", "Greenteam", 2700),
("Lily", "Eldat GmbH", 3800),
("Mark", "Chloris", 4000),
("Richard", "Bertelsmann", 3000),
("Whitney", "Chloris", 2500);

insert into manages(employee_name, manager_name) VALUES
("Kevin", "Amanda"),
("Richard", "Betty"),
("Dean", "Lily"),
("Whitney", "Mark");