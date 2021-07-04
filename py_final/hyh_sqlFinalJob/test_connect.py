#! /usr/bin/python3


from reader import *
import pymysql as ps
from common import *

conn = ps.connect("localhost","haoge","13570958266_He","mylibrary")
cursor = conn.cursor()

cursor.execute("show tables")
a = cursor.fetchall()
print(a)
sql = 'select * from %s' %a[3]
a = cursor.execute(sql)
for i in cursor.fetchall():
    print(i[:2])

read = ReaderClass()
read.borrow_book_dboperation("102","301")
# read.return_book("102","301")
read.ReaderInterface1()
find_one_book(cursor)
