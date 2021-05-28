#include<iostream>
#include <mysql_connection.h>
#include <mysql_driver.h>
#include <cppconn/driver.h>
#include <cppconn/exception.h>
#include <cppconn/resultset.h>
#include <cppconn/statement.h>
#include <cppconn/prepared_statement.h>
using namespace std;

int main()
{
	sql::mysql::MySQL_Driver *driver;
	sql::Connection *conn;
	sql::Statement *state;
	sql::ResultSet *result;
	driver = sql::mysql::get_driver_instance();
	conn = driver->connect("localhost", "haoge", "13570958266_He");
	state = conn->createStatement();
	state->execute("use test");
	result = state->executeQuery("select * from users");
	//// 输出查询  
	while (result->next())
	{
		cout<<result->getString("id")<<"	";
		cout<<result->getString("fullname")<<endl;
	}
	return 0;
}