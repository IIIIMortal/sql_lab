create table item(
    orderNumber int,
    productName varchar(40),
    quantity int,
    unitPrice int,
    primary key(orderNumber,productName)
)ENGINE=InnoDB  DEFAULT CHARSET = utf8;

select customerName from order natural join item as A where A.productName = '打印机';

select orderNumber from item group by orderNumber having sum(quantity * unitPrice) = max(sum(quantity*unitPrice));

update item set unitPrice = unitPrice * 1.1 where orderNumber in (select orderNumber from item group by orderNumber having sum(quantity*unitPrice)<10000) and productName = '打印机';
