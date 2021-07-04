

from tabulate import tabulate
from connect import connector
import time
import pymysql as ps
from common import *

class ReaderClass():
    def __init__(self):
        self.conn,self.cursor = connector("localhost","haoge","13570958266_He","mylibrary")

    def getReaderInfo(self,reader_id):
        personal_info_sql = 'select reader_id,reader_name,department,alr_borrowed from reader where reader_id = "%s"' %reader_id
        try:
            self.cursor.execute(personal_info_sql)
            res = self.cursor.fetchone()
            
        except:
            print("信息获取失败")
            return 
        
        if len(res) == 0:
            print("查无此人")
            return
        print(res)
        print("\tID:\t%s\n\t名字:\t%s\n\t部门:\t%s\n\t借书数量:\t%s" %res)
        return

    def borrow_book_dboperation(self, book_id, reader_id):
        '''
        1. get book and reader status (through the public checking funciton)
        '''
        try:

            nowtime = time.strftime("%Y-%m-%d", time.localtime())
            print(1)
            books_sql = "update books set storage_status = false where book_id = '%s'" %book_id
            print(11)
            reader_sql = "update reader set alr_borrowed = alr_borrowed + 1 where reader_id = '%s'" %reader_id
            print(111)
            record_sql = "insert into borrow_record values (%s,%s,'%s',null,true)" %(book_id,reader_id,nowtime)
            print(1111)
            a = self.cursor.execute(books_sql)
            a = self.cursor.execute(reader_sql)
            a = self.cursor.execute(record_sql)
            self.conn.commit()

            return True
        except:
            self.conn.rollback()
            print("false")
            return False

    def return_book_dboperation(self,book_id,reader_id):
        try:
            nowtime = time.strftime("%Y-%m-%d", time.localtime())
            books_sql = "update books set storage_status = true where book_id = '%s'" %book_id
            reader_sql = "update reader set alr_borrowed = alr_borrowed - 1 where reader_id = '%s'" %reader_id
            record_sql = "update borrow_record set status = false, back_time = '%s' where status = true and book_id = %s" %(nowtime,book_id)
            self.cursor.execute(books_sql)
            self.cursor.execute(reader_sql)
            self.cursor.execute(record_sql)
            self.conn.commit()
            return True
        except:
            self.conn.rollback()
            print('False')
            return False

    def change_personalfile(self,id):
        
        new_name = input('请输入你的新名字')
        new_department = input('请输入你所属的部门')
        reader_InfoChange = 'update reader set reader_name = "%s",department = "%s" where reader_id = "%s"' %(new_name,new_department,id)
        try:
            self.cursor.execute(reader_InfoChange)
            self.conn.commit()
            
        except:
            print("数据不合规范！修改信息失败")
            return
        self.getReaderInfo(id)
        return 

    def deleteaccount(self,reader_id):
        # find whether a book has borrowed
        sql = 'select alr_borrowed from reader where reader_id = "%s"' %reader_id
        try:
            self.cursor.execute(sql)
            res = self.cursor.fetchall()[0]
            print(res)
        except:
            self.conn.rollback()
            print("delete fail")
        if (0,) == res:
            print("你已经归还所有借阅书目，可以删除读者号")
            try:
                self.cursor.execute("delete from reader where reader_id = '%s'" %reader_id)
                self.conn.commit()
                return
            except:
                self.conn.rollback()
                print("注销失败！")
                return
        elif int(res[0]) > 0:
            print("请归还完所有的借阅书目之后再办理账号注销！！")
        elif int(res[0]) < 0:
            print("出现了一些小问题，请联系工作人员检查后台")
        return

    def view_borrowed(self,reader_id):
        try:
            self.cursor.execute("select reader_id from reader")
            res = self.cursor.fetchall()
        except:
            self.conn.rollback()
            print("出现了点小问题")
            return -1

        if (reader_id,) not in res:
            print("ID 无效")
            return 0
        else:
            sql = 'select books.book_id,book_name,book_author,out_time from books left join borrow_record \
                on books.book_id = borrow_record.book_id \
                    where borrow_record.book_id \
                    in \
                    (select book_id from borrow_record \
                        where status = true and reader_id = "%s")' %reader_id
            try:
                self.cursor.execute(sql)
                res = self.cursor.fetchall()
            except:
                self.conn.rollback()
                print("出现了点小问题")
                return
            if len(res) == 0:
                print("你没有借用任何书籍")
            else:
                print(tabulate(res,headers=('书籍编号','书籍名称','作者','借用日期'),tablefmt='grid'))
            
            return 1
    
    def Borrow(self):
        print("你正在办理书籍借用手续....")
        bookID = input('请输入你要借用的书本的ID:\n\t')
        flag = checkbook(self.cursor,bookID)
        print(flag)
        if flag == -1:
            print("没有这本书，请在查找界面仔细确认")
            return
        elif flag == 0:
            print("这本书已经被借用！")
            return
        elif flag == 1:
            readerID = input('请输入你的ID:\n\t')
            psw = input('请输入你的密码:\n\t')
            try:
                self.cursor.execute("select reader_id,`password` from reader")
                res = self.cursor.fetchall()
            except:
                print("出了点小错误")
                return
            if (readerID,psw,) not in res:
                print("身份查验失败！请检查ID和密码")
                return
            else:
                if self.borrow_book_dboperation(bookID,readerID):
                    print("借用成功！")
                else:
                    print("出了点问题，借用失败！请重试！")
        return

    def Return(self):
        print("你正在办理书籍归还手续....")
        bookID = input('请输入你要归还的书本的ID:\n\t')
        flag = checkbook(self.cursor,bookID)
        if flag == -1:
            print("没有这本书，请在查找界面仔细确认")
            return
        elif flag == 1:
            print("这本书尚未被借用！")
            return
        elif flag == 0:
            readerID = input('请输入你的ID:\n\t')
            psw = input('请输入你的密码:\n\t')
            try:
                self.cursor.execute("select reader_id,`password` from reader")
                res = self.cursor.fetchall()
            except:
                print("出了点小错误")
                return
            if (readerID,psw,) not in res:
                print("身份查验失败！请检查ID和密码")
                return
            else:
                if self.return_book_dboperation(bookID,readerID):
                    print("归还成功！")
                else:
                    print("出了点问题，归还失败！请重试！")
        return 

    def Reader_center(self):
        id = input('请输入你的ID\n\t')
        psw = input('请输入你的密码\n\t')
        check_pswreadersql = "select `password` from reader where reader_id = '%s'" %id
        try:
            self.cursor.execute(check_pswreadersql)
            res = self.cursor.fetchone()[0]
        except:
            print('帐号密码错误！!')
            return 

        Psw = '%s' %psw
        if Psw!=res:
            print("密码错误，请重新尝试")
            return
        
        while 1:
            print("**********************************************************")
            print("%s 号读者你好！\n\t你现在正在个人中心\n请从下列操作中做出选择（更多功能请期待）" %id)
            print("\t0: 返回主界面\t\t1: 查看个人资料")
            print("\t2: 修改个人资料\t\t3: 查看已经借用的书目")
            try:
                op = int(input())
            except:
                op = 10
            if op == 0:
                print("退出个人中心")
                return
            elif op == 1: 
                self.getReaderInfo(id)
            elif op == 2: 
                self.change_personalfile(id)
            elif op == 3: 
                self.view_borrowed(id)
            elif op == 4: 
                self.deleteaccount(id)
                return 
            else:
                print("不存在的操作！请重试！")

    def register_reader(self):
        print("你正在办理读者登记手续...")
        id = input('请输入你要注册的id\n\t')
        name = input('请输入你的名字\n\t')
        department = input('请输入你所属部门\n\t')
        psw = input('请输入你的密码\n\t')
        try:
            register_reader_sql = 'insert into reader values ("%s","%s","1","%s","%s",0)' %(id,name,department,psw)
            self.cursor.execute(register_reader_sql)
            self.conn.commit()
        except:
            self.conn.rollback()
            print("register failed!")
            return
        
        print("注册成功！")

    def ReaderInterface1(self):
        while 1:
            print("**********************************************************")
            print("你现在正在读者中心\n请从下列操作中做出选择（更多功能请期待）")
            print("0: 返回主界面\t\t1: 进入个人中心\t\t2: 办理读者登记")
            print("3: 书籍借用\t\t4: 书籍归还")
            try:
                op = int(input())
            except:
                op = 10
            if op == 0:
                print("退出读者中心")
                return
            elif op == 1: 
                self.Reader_center()
            elif op == 2: 
                self.register_reader()
            elif op == 3: 
                self.Borrow()
            elif op == 4:
                self.Return()
            else:
                print("不存在的操作！请重试！")