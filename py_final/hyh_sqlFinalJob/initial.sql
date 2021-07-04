
create database if not EXISTS mylibrary;
use mylibrary;

drop table if exists borrow_record;
drop table if exists books;
drop table if exists admin;
drop table if exists reader;
-- 创建 book 书库数据表

create table if not exists books
(
    book_id         varchar(40) not null,
    book_name       varchar(40) not null,
    book_author     varchar(40) not null,
    price           float not null,
    publishing_house    varchar(40) not null,
    storage_status  boolean not null,
    book_type       enum('技能','文学','休闲','学习','职场','其他') not null,

    PRIMARY KEY(book_id)
)ENGINE=InnoDB DEFAULT CHARSET=utf8;



-- 创建 reader 读者数据数据表

create table if not exists reader
(
    reader_id       varchar(40) not null,
    reader_name     varchar(40) not null,
    able            enum('1','0') not null,
    department      varchar(30) not null,
    `password`      varchar(20) not null,
    alr_borrowed    int not null,
    PRIMARY KEY(reader_id)
)ENGINE=InnoDB DEFAULT CHARSET=utf8;



-- 创建 admin 管理员数据表

create table if not exists admin
(
    admin_id       varchar(40) not null,
    admin_name     varchar(40) not null,
    
    department      varchar(30) DEFAULT null, 
    `password`      varchar(20) not null DEFAULT '123456',
    PRIMARY KEY(admin_id)
)ENGINE=InnoDB DEFAULT CHARSET=utf8;



-- 创建 borrow 借用情况登记数据表

create table if not exists borrow_record
(
    book_id varchar(40) not null,
    reader_id varchar(40) not null,
    out_time date not null,
    back_time date,
    status boolean not null,
    PRIMARY KEY(book_id,reader_id),
    FOREIGN KEY (book_id) REFERENCES books(book_id) on delete cascade,
    FOREIGN KEY (reader_id) REFERENCES reader(reader_id) on delete cascade
)ENGINE=InnoDB DEFAULT CHARSET=utf8;


use mylibrary;

insert INTO books 
    VALUES
    ('100','test1','T', 3.3, 'ZJU', true,'文学'),
    ('107','test2','T', 3.3, 'ZJU', true,'技能'),
    ('104','be sad','A', 3.3, 'ZJU', true,'休闲'),
    ('101','be happy','A', 3.3, 'ZJU', true,'休闲'),
    ('102','dont be sad', 'B', 3.4, 'ZJU', true,'休闲'),
    ('106','UZI！！！','A', 3.3, 'ZJU', true,'学习'),
    ('108','test3','T', 3.3, 'ZJU', true,'技能'),
    ('103','mein arbeit','c', 34, 'ZJU', true,'职场'); 

INSERT INTO reader
    VALUES
    ('300', 'test', 1, 'CS','123456',0),
    ('301', 'Tim', 1, 'CS','00000',0),
    ('302', 'sim', 1, 'Ca', '000',0),
    ('303', 'Tik', 1, 'Cb', '00',0);

INSERT INTO admin
    VALUES
    ('0', 'test','CS','0000'),
    ('1', 'fool','CS','1121'),
    ('2', 'sad','CS','1121'),
    ('3', 'happy','CS','1121'); 