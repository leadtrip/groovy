package mikew.groovycollections

import mikew.util.Fish

import java.time.LocalDate
import java.time.Month

[1,2,3]*.plus(1) == [2,3,4]

assert [1,2,3].collect {it*2} == [2,4,6]

def aList = [2,4]
[5,6,7,8].collect(aList) {it%2==0}
aList == [2,4,6,8]

assert ['bob', 'dave', 'rob'].find {it.contains('ob')} == 'bob'
assert ['bob', 'dave', 'rob'].findAll {it.contains('ob')} == ['bob', 'rob']

assert [['a', 'b'], [1, 999], 'c'].combinations() == [['a', 1, 'c'], ['b', 1, 'c'], ['a', 999, 'c'], ['b', 999, 'c']]

def letters = ('a'..'d').toList()
assert letters == ['a', 'b', 'c', 'd']

// Returns the items from the List excluding the last item. Leaves the original List unchanged
assert letters.init() == ['a', 'b', 'c']
// Returns the items from the List excluding the first item
assert letters.tail() == ['b', 'c', 'd']
// Calculates the init values of this Iterable: the first value will be this list of all items from the iterable and the
// final one will be an empty list, with the intervening values the results of successive applications of init on the items.
assert letters.inits() == [
        ['a', 'b', 'c', 'd'],
        ['a', 'b', 'c'],
        ['a', 'b'],
        ['a'],
        []]
// like inits above calling tail
assert letters.tails() == [
        ['a', 'b', 'c', 'd'],
        ['b', 'c', 'd'],
        ['c', 'd'],
        ['d'],
        []]

// transpose, takes a collection of columns and returns a collection of rows.
// The first row consists of the first element from each column. Successive rows are constructed similarly
def tp1 = [['a', 'b'], [1, 2], [3, 4]].transpose()
assert tp1 == [['a', 1, 3], ['b', 2, 4]]

def tp2 = [['bob', 'jim', 'sue'], [22, 49, 39], ['admin', 'dev', 'HR']].transpose()
assert tp2 == [['bob', 22, 'admin'], ['jim', 49, 'dev'], ['sue', 39, 'HR']]

assert 213 == [12312, 9219, 213, 921].min()
assert 12312 == [12312, 9219, 213, 921].max()

def theMax = ['bob': 111, 'sue': 921].max { it.value }
assert theMax.key == 'sue'

assert 'bob and sue and dave' == ['bob', 'sue', 'dave'].join(' and ')

assert 200 == [100, 200, 300].average()
assert 10 == [1,2,3,4].sum()

assert 'The quick brown fox' ==
        ['quick', 'brown', 'fox'].inject('The') { acc, val -> acc + ' ' + val }

assert (4*(3*(2*(1*10)))) == [1,2,3,4].inject(10) { acc, val -> val * acc }

def fishes = [
        new Fish(first: 'Colin', type: 'Cod', bodyOfWater: 'Atlantic', dob: LocalDate.of(2022, Month.MARCH, 4)),
        new Fish(first: 'Barbara', type: 'Bream', bodyOfWater: 'Avon', dob: LocalDate.of(2022, Month.JUNE, 1)),
        new Fish(first: 'Terry', type: 'Tench', bodyOfWater: 'Avon', dob: LocalDate.of(2022, Month.MARCH, 4)),
        new Fish(first: 'Rod', type: 'Roach', bodyOfWater: 'Severn', dob: LocalDate.of(2022, Month.MARCH, 12)),
        new Fish(first: 'Eric', type: 'Eel', bodyOfWater: 'Pacific', dob: LocalDate.of(2021, Month.JANUARY, 14)),
        new Fish(first: 'Elizabeth', type: 'Eel', bodyOfWater: 'Pacific', dob: LocalDate.of(2017, Month.APRIL, 23)),
        new Fish(first: 'Gordon', type: 'Great white shark', bodyOfWater: 'Atlantic', dob: LocalDate.of(2019, Month.DECEMBER, 25))
]

def fishesByType = fishes.groupBy { fish -> fish.type }
assert fishesByType['Eel'].size() == 2

def fishesByYearOfBirth = fishes.groupBy { fish -> fish.dob.year }
assert fishesByYearOfBirth[2022].size() == 4

def fishesByBodyOfWaterAndType = fishes.groupBy( {it.bodyOfWater}, {it.type} )
assert fishesByBodyOfWaterAndType['Pacific']['Eel'].size() == 2     // Eric & Elizabeth

def fishDobCount = fishes.countBy {it.dob.year}
assert [2022:4, 2021:1, 2017:1, 2019:1] == fishDobCount
def mostFishBornIn = fishDobCount.max {it.value}.key
assert mostFishBornIn == 2022

[[1], ['bob', 1], ['sue'], ['sue', 'bob'], ['bob'], ['sue', 'bob', 1], ['sue', 1]] == (['sue', 'bob', 1].subsequences())

[['a', 1], ['b', 1], ['a', 2], ['b', 2]] == [['a', 'b'],[ 1, 2]].combinations()

// get all the duplicates, that is occurrences -1
def startingList = [1,2,3,4,5,4,5,5,5,5,6,7,8,9,10,10,10]
def duplicateEntries = startingList.countBy { it }
        .findAll { it.value > 1 }
        .collectMany { [it.key] * (it.value - 1) }
assert duplicateEntries == [4, 5, 5, 5, 5, 10, 10]

// get all letters that had duplicates
def startingLetters = 'abakckaliajlkjfoiajdljsaljfeliajfdlajfda'.toList()
def duplicateLetters= startingLetters.countBy {it}
                    .findAll {it.value > 1}*.getKey()
println duplicateLetters