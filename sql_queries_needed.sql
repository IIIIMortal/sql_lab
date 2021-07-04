CREATE DATABASE IF NOT EXISTS library;

-- 用来记录使用人的信息，可以用于制作登陆系统，包括编号，特权，用户名，密码
CREATE TABLE IF NOT EXISTS user(
    'INDEX' INT UNSIGNED AUTO_INCREMENT,
    'NAME' varchar(40) NOT NULL,
    'PASSWARD' varchar(20) NOT NULL,
    'PRIVILEGE' INT UNSIGNED NOT NULL,
    primary key('INDEX')
)ENGINE = InnoDB DEFAULT CHARSET=utf8;

-- 用来记录书籍信息，包括书名，编号，借用状态
CREATE TABLE IF NOT EXISTS books(
    'book_name' varchar(40) not null,
    'book_index' int unsigned auto_increment,
    'status' boolean not null default 0,

)ENGINE = InnoDB DEFAULT CHARSET-utf8;



    