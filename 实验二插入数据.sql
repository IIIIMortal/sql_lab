# 插入数据
insert into book(bno, category, title, press, year, author, price, total, stock)
Values('20200001', 'history', '世界历史', '安徽文艺出版社', 2009, '马健', 46.8, 30, 15),
('20200030', 'computer', '数据库系统原理教程', '清华大学出版社', 1998, '王珊', 18.5, 20, 7),
('20201005', 'Mathematics', '概率论与数理统计', '高等教育出版社', 2010, '盛骤', 41.8, 30, 20),
('20203001', 'computer', 'MYSQL必知必会', '人民邮电出版社', 2009, '刘晓霞', 39.0, 25, 13),
('20200304', 'music', '勋伯格和声学', '上海音乐出版社', 2007, '阿诺德·勋伯格', 28.0, 15, 6);
insert into card (cno, cname, department, type)
Values
('1000001', 'xiaoli', 'computer science', 1),
('2000005', 'xiaozhang', 'history', 2),
('7000302', 'Mike', 'english', 3),
('1000420', 'xiaowang', 'mathematics', 1),
('2003001', 'lihua', 'computer science', 2),
('1000830', 'hhh', 'computer science', 1),
('2200012', 'xiaohua', 'english', 2),
('1000330', 'xiaozhou', 'history', 1);
insert into administrator value('100001', 'admin','123456','13111111111');
insert into record (cno, bno, borrow_date, return_date, ano)
Values
('1000001', '20200030', '2019-03-05 09:53:56','2019-06-05 09:53:56', '100001'),
('1000001', '20203001', '2019-04-15 15:25:00' , '2019-07-15 15:25:00', '100001'),
('2000005', '20200001', '2019-03-29 14:20:00', '2019-04-12 14:49:00', '100001'),
('7000302', '20200304', '2019-07-20 11:20:00', '2019-08-12 10:30:00', '100001'),
('1000420', '20201005', '2019-09-20 11:20:00', '2019-10-23 11:30:00', '100001'),
('1000830', '20200030', '2019-01-10 08:40:00', '2019-03-01 09:20:00', '100001'),
('1000830', '20200304', '2019-10-20 09:20:00', '2019-11-23 12:30:00', '100001');