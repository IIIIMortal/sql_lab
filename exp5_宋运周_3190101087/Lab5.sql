use library;
create  table book
   (bno char(10), 
   category 	varchar(10),
   title 	varchar(20),
   press	varchar(20),
   year int,
   author varchar(10),
   price	decimal(7,2),
   total	int,
   stock	int,
   check(stock<=total),
   primary key(bno));
   
create table card
  (cno char(7),
  name varchar(10),
  department varchar(40),
  type char(1),
  primary key(cno),
  check(type in('T','S')));

  create table borrow
  (cno char(7),
  bno  char(8),
  borrow_date int,
  return_date int,
  state char(1),
  check(state in ('I', 'O')),
  primary key(cno,bno,borrow_date),
  foreign key (cno) references card(cno),
  foreign key (bno) references book(bno));	 

use library;
insert book value('101', 'A', 'sad', 'ZJU', 2019, 'me', 312.31, 5, 5);
insert book value('102', 'A', 'happy', 'THU', 1999, 'you', 29.8, 3, 3);
insert book value('103', 'B', 'math', 'ZJU', 2021, 'he', 39, 40, 40);
insert book value('104', 'B', 'english', 'ZJU', 1988, 'she', 50.3, 20, 20);
insert book value('105', 'C', 'physics', 'ZJU', 1957, 'the', 15.2, 10, 10);
insert book value('106', 'C', 'chemistry', 'PKU', 2020, 'it', 66.21, 4, 4);

insert card value('301', 'Tim', 'CS', 'S');
insert card value('302', 'Sam', 'PE', 'S');
insert card value('401', 'Patt', 'CS', 'T');

