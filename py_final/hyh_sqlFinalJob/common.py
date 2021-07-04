from tabulate import tabulate
from connect import connector
import pymysql as ps
import pandas as pd


def show_all_books(cur):
    show_all_books_sql = 'select * from books'
    try:
        cur.execute(show_all_books_sql)
        res = cur.fetchall()
    except:
        print("出现了一些小错误")
        return
    print(tabulate(res,headers=('ID','书名','作者','价格','出版社','是否在馆','书籍类型'),showindex=True,tablefmt='psql'))
    print("如要借书，请记住ID")
    return

def checkbook(cursor,book_id):
    # print(1)
    checkbook_sql = 'select storage_status from books where book_id = "%s"' %book_id
    try:
        # print(1)
        cursor.execute(checkbook_sql)
        res = cursor.fetchall()
        # print(res)
    except:
        print("anything wrong~")
        return -1
    if (True,) in res:
        return 1
    elif (False,) in res:
        return 0

def find_one_book(cur):
    print("你现在正在查找书籍，本搜索满足模糊查询\n为了更好地满足你，请根据我们的步骤依次输入条件，如果相关问题无法回答，可以敲击回车跳过")
    basic_sql = 'select * from books where '
    ID = input('请输入你要借用的书本的ID:\n\t')
    NAME = input('请输入你要借用的书本的名字:\n\t')
    AUTHOR = input('请输入你要借用的书本的作者:\n\t')
    P_H = input('请输入你要借用的书本的出版社:\n\t')
    TYPE = input('请输入你要借用的书本种类:\n\t')

    strID = 'book_id like "%%%s%%"' %ID
    strNAME = ' and book_name like "%%%s%%"' %NAME
    strAUTHOR = ' and book_author like "%%%s%%"' %AUTHOR
    strP_H = ' and publishing_house like "%%%s%%"' %P_H
    strTYPE = ' and book_type like "%%%s%%"' %TYPE

    sql = basic_sql+strID+strNAME+strAUTHOR+strP_H+strTYPE
    print 
    try:
        cur.execute(sql)
        res = cur.fetchall()
    except:
        print("")
    if len(res) == 0:
        print("\t\t-------->没有符合条件的书籍<--------")
        return
    print(tabulate(res,headers=('书籍编号','书籍名称','作者','价格','出版社','是否在馆','书籍种类'),tablefmt='grid',showindex=True))
    return

