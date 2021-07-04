
import reader
import admin

import pymysql as ps
from common import *
from connect import connector

reader = reader.ReaderClass()
admin = admin.AdminClass()


conn,cur = connector('localhost','haoge','13570958266_He','mylibrary')

while 1:
    print("*******************************************************")
    print("你好！\n\t你现在在主界面\n\t请从下列操作中做出选择（更多功能请期待）")
    print("\t0: 退出\t\t1: 进入读者中心界面\t\t2: 进入管理员界面")
    print("\t3: 查看所有书目\t\t4: 查找书籍情况（支持模糊查找）")
    try:
        op = int(input())
    except:
        op = 10
    if op == 0:
        print("退出程序")
        break
    elif op == 1: 
        reader.ReaderInterface1()
    elif op == 2: 
        admin.AdminInterface()
    elif op == 3: 
        show_all_books(cur)
    elif op == 4: 
        find_one_book(cur)
    else:
        print("不存在的操作！请重试！")