#include <iostream>
#include <mysql_connection.h>
#include <mysql_driver.h>
#include <cppconn/driver.h>
#include <cppconn/exception.h>
#include <cppconn/resultset.h>
#include <cppconn/statement.h>
#include <cppconn/prepared_statement.h>
using namespace std;

#define HOST_NAME "haoge"
#define USER "localhost"
#define PASSWORD "13570958266_He"

class Insertion
{
public:
private:
};

int main()
{
    sql::mysql::MySQL_Driver *driver;
    sql::Connection *connector;
    sql::Statement *state;
    sql::ResultSet *result;

    driver = sql::mysql::get_driver_instance();
    connector = driver->connect( USER , HOST_NAME, PASSWORD);
    state = connector->createStatement();
    state->execute("use mysql");
    result = state->executeQuery("desc user");
    
    while (result->next())
	{
		cout<<result->get()<<"	";
		// cout<<result->getString("fullname")<<endl;
	}
    return 0;
}