//["powershell", "-Command", "Invoke-RestMethod -Headers @{Authorization = 'Bearer OTg...'} -Uri https://bitbucket.domain/repos/test/browse/base/test.txt?raw -OutFile test.txt"].execute();

//["powershell", "-Command", "Invoke-RestMethod -Uri https://jsonplaceholder.typicode.com/users/1 -OutFile test.txt"].execute();

def sout = new StringBuilder(), serr = new StringBuilder()
def args = ["powershell", "-Command", "Invoke-RestMethod -Headers @{Authorization = 'Bearer OTg...'} -Uri https://bitbucket.domain/repos/test/browse/base/test.txt?raw -OutFile test.txt"]
def proc = new ProcessBuilder( args )
Process process = proc.start()
process.consumeProcessOutput( sout, serr )
process.waitForOrKill( 10000 )
println serr

