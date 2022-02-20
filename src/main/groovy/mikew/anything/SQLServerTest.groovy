package mikew.anything

import groovy.sql.*
def sql = Sql.newInstance("jdbc:sqlserver://mssql.test.com:1433;databaseName=TEST;", "admin", "admin" , "com.microsoft.sqlserver.jdbc.SQLServerDriver")
sql.eachRow("select * from word"){
    println it.spelling + " ${it.part_of_speech}"
}
