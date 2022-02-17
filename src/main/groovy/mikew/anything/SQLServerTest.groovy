package mikew.anything
// https://mvnrepository.com/artifact/com.microsoft.sqlserver/mssql-jdbc
@Grapes(
        @Grab(group='com.microsoft.sqlserver', module='mssql-jdbc', version='6.1.5.jre8-preview')
)

import groovy.sql.*
def sql = Sql.newInstance("jdbc:sqlserver://mssql.test.com:1433;databaseName=TEST;", "admin", "admin" , "com.microsoft.sqlserver.jdbc.SQLServerDriver")
sql.eachRow("select * from word"){
    println it.spelling + " ${it.part_of_speech}"
}
