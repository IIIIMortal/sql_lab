#! /usr/bin/python3


import pymysql
from connect import connector
import time
from common import *

class AdminClass():
    def __init__(self):
        self.conn,self.cursor = connector("localhost","haoge","13570958266_He","mylibrary")
    
    def AdminInterface(self):
        id = input('请输入你的管理员id\n')
        psw = input('请输入你的管理员密码\n')
        check_pswadminsql = "select `password` from admin where admin_id = '%s'" %id
        try:
            self.cursor.execute(check_pswadminsql)
            res = self.cursor.fetchone()[0]
        except:
            print('这个id没有登记在库！')
            return 

        Psw = '%s' %psw
        if Psw!=res:
            print("密码错误，请重新尝试")
            return
        while 1:
            print("**********************************************************") 
            print("\t你现在正在管理员操作界面\n\t请从下列操作中做出选择（更多功能请期待）")
            print("\t1: 删除书籍\t\t2: 增加书籍")
            print("\t0: 返回主界面")
            try:
                op = int(input())
            except:
                op = 10
            if op == 0:
                print("退出管理员选项界面")
                return
            elif op == 1: 
                self.delete_book()
            elif op == 2: 
                self.add_book()
            else:
                print("不存在的操作！请重试！")

    def delete_book(self):
        print("你正在办理管理员删除书籍手续，注意：需要确认书籍当时未被借用...")
        
        book_id = input('请输入你要删除的书本的ID:\n\t')
        self.delete_book_dboperation(book_id)
        return 

    def delete_book_dboperation(self,book_id):
        
        self.cursor.execute('select book_id from books')
        booklist = self.cursor.fetchall()
        if (book_id,) not in booklist:
            print("查无此书")
            return 
        # 书库里有书
        flag = checkbook(self.cursor,book_id)
        if flag == 1: # can be deleted, namely nobody has borrowed this book yet
            try:
                delete_book_sql = 'delete from books where book_id = "%s"' %book_id
                self.cursor.execute(delete_book_sql)
                res = self.cursor.fetch
                self.conn.commit()
                print(res)
                print("这本书被成功删除了，请注意！")
                return
            except:
                self.conn.rollback()
                print("删除失败，请检查是否有其余依赖关系未确认")
                return
        elif flag == 0:
            print("这本书目前正在被人借用，无法删除，请与借用者联系")
            return 
        elif flag == -1:
            print("查找此书失败，无法删除")
            return 
        
        return 
        
    def add_book(self):
        print("你现在正在办理添加书籍入数据库的手续...")
        
        
        new_bid = input("请输入新书id\n")
        new_bname = input("请输入新书名字\n")
        new_bauthor = input("请输入新书作者\n")
        try: 
            new_bprice = float(input("请输入新书价格\n"))
        except: 
            print('请输入数字！')
            return
        new_publishinghouse = input("请输入新书出版社\n")
        new_btype = input("请输入新书类型\n"+"可以从这些选项中进行选择：'技能','文学','休闲','学习','职场','其他'\n")

        try:
            addbook_sql = 'insert into books values ("%s","%s","%s",%f,"%s",true,"%s")' %(new_bid, new_bname,new_bauthor,new_bprice,new_publishinghouse,new_btype)
            self.cursor.execute(addbook_sql)
            self.conn.commit()
            print("添加新书成功: %s" %new_bname)
        except pymysql.err.IntegrityError:
            self.conn.rollback()
            print("这个ID： %s 已经存在了！" %new_bid)
        except pymysql.err.OperationalError:
            self.conn.rollback()
            print("请检查输入，输入不符合要求！")
        
        return
