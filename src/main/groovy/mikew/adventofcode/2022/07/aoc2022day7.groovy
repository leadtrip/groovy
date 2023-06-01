import groovy.transform.ToString

def input = new File( "../resources/day7input.txt" ).text

def testInput =
        '$ cd /\n' +
        '$ ls\n' +
        'dir a\n' +
        '14848514 b.txt\n' +
        '8504156 c.dat\n' +
        'dir d\n' +
        '$ cd a\n' +
        '$ ls\n' +
        'dir e\n' +
        '29116 f\n' +
        '2557 g\n' +
        '62596 h.lst\n' +
        '$ cd e\n' +
        '$ ls\n' +
        '584 i\n' +
        '$ cd ..\n' +
        '$ cd ..\n' +
        '$ cd d\n' +
        '$ ls\n' +
        '4060174 j\n' +
        '8033020 d.log\n' +
        '5626152 d.ext\n' +
        '7214296 k'

def rootDir = new DirectoryD7( '/' )
def currentDir = rootDir
input.eachLine {aLine ->

    switch (aLine) {
        case { aLine.startsWith('$ cd') }:
            def moveTo = aLine.split(' ')[2].trim()
            if (moveTo == '..') {
                if ( currentDir.parentDirectories ) {
                    currentDir = currentDir.parentDirectories.last()
                }
            }
            else if (moveTo == '/') {
                if ( currentDir.parentDirectories ) {
                    currentDir = currentDir.parentDirectories.first()
                }
            }
            else {
                // directory name to move to
                def subDir = currentDir.subDirectories.find{ it.directoryName == moveTo }
                if( subDir ){
                    currentDir = subDir
                }
                else {
                    def newDir = new DirectoryD7( moveTo, currentDir )
                    currentDir.subDirectories << newDir
                    currentDir = newDir
                }
            }
            break
        case { aLine.startsWith('dir ') }:
            def dirToAdd = aLine.split(' ' )[1]
            if( !currentDir.subDirectories.find{ it.directoryName == dirToAdd } ) {
                currentDir.subDirectories << new DirectoryD7( dirToAdd, currentDir )
            }
            break
        case { aLine.startsWith('$ ls') }:
            break
        case { ~/[0-9]* / }:
            def fileSize = aLine.split(' ')[0] as Integer
            currentDir.dirSize += fileSize
            currentDir.parentDirectories.each { parentDir -> parentDir.dirSize += fileSize }
            break
        default:
            throw new UnsupportedOperationException('What is this? ' + aLine)
    }
}
currentDir = currentDir.parentDirectories.first()

// ---- Part 1 ----
def dirList = recursiveSize(currentDir, [], 100000)
def sumOfAllDirs = dirList*.dirSize.sum()
println 'Sum of all dirs < 100000: ' + sumOfAllDirs     // Part 1 answer = 1086293

// -- Part 2 ----
dirList = recursiveSize(currentDir, [], Long.MAX_VALUE)
def rootDirSize = dirList[0].dirSize
def requiredForUpdate = 30000000 - (70000000 - rootDirSize)
def smallestBigEnoughDir = dirList*.dirSize.findAll{ it > requiredForUpdate }.sort()[0]
println 'Smallest big enough directory: ' + smallestBigEnoughDir    // Part 2 answer = 366028


List recursiveSize( DirectoryD7 directoryD7, List dirList, Long max ) {
    if ( directoryD7.dirSize <= max ) {
        dirList << directoryD7
    }
    directoryD7.subDirectories.each { aDir ->
        recursiveSize(aDir, dirList, max) + aDir.dirSize
    }
    return dirList
}

class DirectoryD7 {
    String directoryName
    List<DirectoryD7> parentDirectories = []
    List<DirectoryD7> subDirectories = []
    Long dirSize = 0

    DirectoryD7( String name, DirectoryD7 parentDir = null ) {
        this.directoryName = name
        if( parentDir ) {
            this.parentDirectories = parentDir.parentDirectories + parentDir
        }
    }

    def getCurrent() {
        'Current directory: ' + parentDirectories*.directoryName.join( ' > ' ) + directoryName + ' ' + "[${dirSize}]"
    }
}