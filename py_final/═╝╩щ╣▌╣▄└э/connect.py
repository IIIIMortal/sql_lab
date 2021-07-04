import pymysql
def connect():
    conn = pymysql.connect(host='localhost', user='haoge',password='13570958266_He',database='library')#连接数据库library，用户和密码按需修改
    cursor = conn.cursor()
    return cursor, conn