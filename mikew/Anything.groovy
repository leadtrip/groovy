import groovy.sql.Sql

class Test {

    private static final String PROJECT_INFO_FOR_USER ="select PROJECT_ID as PROJECT_NO, NAME as PROJECT_NAME from PROJECT_DIM where PROJECT_ID IN ( "

    private getProjectList( caseResult ) {
        def query = new StringBuffer( PROJECT_INFO_FOR_USER )
        def binds = []
        caseResult.each { projno ->
            if (query.length() == 0) {
                query.append '?,'
                binds << projno.project_no
            } else {
                if (query.indexOf(projno.project_no) == -1) {
                    query.append '?,'
                    binds << projno.project_no
                }
            }
        }

        query.deleteCharAt( query.length() - 1 )
        query.append( ' )' )
        [query: query.toString(), binds: binds]
    }

    private process() {
        def db = [url: "jdbc:oracle:thin:@x.xx.xx.xx:1521:ORCL",
                  user: 'xxxx',
                  password: 'xxxx',
                  driver: 'oracle.jdbc.pool.OracleDataSource']
        def sql = Sql.newInstance(db.url, db.user, db.password, db.driver)
        println "DB connection ready"

        def caseResult = [['project_no':'x-xxxx', 'case_nos':['12344'], 'updated_on':'1485335172'], ['project_no':'y-yyyy', 'case_nos':['56789'], 'updated_on':1490359241]]
        def projectList = "x-xxxx"
        def params = getProjectList(caseResult)

        def result = sql.rows( params.query, params.binds ).collect {          // If I replace params with projectList then 'result' is assigned a row from oracle database
            it as Map
        }
        println result
    }

    public static void main(String[] args) {
        Test t = new Test()
        t.process()
    }
}