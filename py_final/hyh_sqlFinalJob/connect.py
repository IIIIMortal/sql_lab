import pymysql as ps 

def connector(host, user, password, database):
    conn = ps.connect(host,user,password,database)
    cursor = conn.cursor()
    return conn, cursor