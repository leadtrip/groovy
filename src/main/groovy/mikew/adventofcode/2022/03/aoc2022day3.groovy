def input = new File( "../resources/day3input.txt" ).collect {it}

/*def input = '''vJrwpWtwJgWrhcsFMMfFFhFp
jqHRNqRjqzjGDLGLrsFMfFZSrLrFZsSL
PmmdzqPrVvPwwTWBwg
wMqvLMZHhHMvwLHjbvcjnnSBnvTQFn
ttgJtRGJQctTZtZT
CrZsJsPPZsGzwwsLwLmpwMDw'''*/

def letters = 'abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ'

// ------------------------- Part 1 -----------------------------------

def total = 0
input.each{aLine ->
    Integer half = aLine.length() / 2
    def firstHalf = aLine.substring( 0, half )
    def secondHalf = aLine.substring( half )

    def firstHalfCountBy = firstHalf.split("(?!^)").countBy{it}
    def secondHalfCountBy = secondHalf.split("(?!^)").countBy{it}

    def intersect = firstHalfCountBy.keySet().intersect( secondHalfCountBy.keySet() )

    total += letters.indexOf(intersect[0]) + 1
}

println total

// ------------------------- Part 2 -----------------------------------

def groupTotal = 0
def groupSackContents = [:]
def idx = 1
input.each{ aLine ->

    groupSackContents."k$idx" = aLine.split("(?!^)").groupBy{it}

    if ( idx == 3 ) {
        def s1 = groupSackContents.k1.keySet()
        def s2 = groupSackContents.k2.keySet()
        def s3 = groupSackContents.k3.keySet()

        def commonChar = s1.intersect(s2).intersect(s3)

        groupTotal += letters.indexOf(commonChar[0]) + 1

        groupSackContents = [:]
        idx = 1
    }
    else {
        idx++
    }
}

println groupTotal
