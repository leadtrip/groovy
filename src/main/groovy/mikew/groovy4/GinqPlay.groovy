package mikew.groovy4

import groovy.json.JsonSlurper

import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.time.format.FormatStyle
import java.time.temporal.ChronoUnit

// minimum query needs from and select
def numbers = [0,1,2]
assert [0, 1, 2] == GQ {
    from n in numbers
    select n
}.toList()

assert [10,12,14,16,18] == GQ{
    from n in [10,11,12,13,14,15,16,17,18]
    where n%2 == 0
    select n
}.toList()

assert [[1, 1], [2, 2], [2, 2], [3, 3], [3, 3], [3, 3], [4, 4], [4, 4], [4, 4], [4, 4]] == GQ {
    from v in (
        from n in [1, 4, 4, 3, 1, 2, 3, 1, 1, 4, 2, 3]
        select distinct(n)
    )
    join m in [1, 2, 2, 3, 3, 3, 4, 4, 4, 4] on m == v
    orderby v
    select v, m
}.toList()

// JSON parsing

def json = new JsonSlurper().parseText(
'''
        {
            "fruits": [
                {"name": "Orange", "price": 11},
                {"name": "Apple", "price": 6},
                {"name": "Banana", "price": 4},
                {"name": "Mongo", "price": 29},
                {"name": "Durian", "price": 32}
            ]
        }
    ''')
def expected = [['Mongo', 29], ['Orange', 11], ['Apple', 6], ['Banana', 4]]
assert expected == GQ {
    from f in json.fruits
    where f.price < 32
    orderby f.price in desc
    select f.name, f.price
}.toList()

// JSON parsing with joins & aggregation

def solarSystem = new JsonSlurper().parseText(
'''
        {
            "planets": [
                {"id": 1, "name": "Earth"},
                {"id": 2, "name": "Mars"},
                {"id": 3, "name": "Jupiter"},
                {"id": 4, "name": "Saturn"},
                {"id": 5, "name": "Uranus"}
            ],
            "moons": [
                {"id": 1, "planet": 1, "name": "Moon"},
                {"id": 2, "planet": 2, "name": "Phobos"},
                {"id": 3, "planet": 2, "name": "Deimos"},
                {"id": 4, "planet": 3, "name": "Io"},
                {"id": 5, "planet": 3, "name": "Europa"},
                {"id": 6, "planet": 3, "name": "Ganymede"},
                {"id": 7, "planet": 3, "name": "Calisto"},
                {"id": 8, "planet": 4, "name": "Mimas"},
                {"id": 9, "planet": 4, "name": "Enceladus"},
                {"id": 10, "planet": 4, "name": "Titan"},
                {"id": 11, "planet": 4, "name": "Tethys"},
                {"id": 12, "planet": 4, "name": "Dione"},
                {"id": 13, "planet": 5, "name": "Ariel"},
                {"id": 14, "planet": 5, "name": "Oberon"},
                {"id": 15, "planet": 5, "name": "Umbriel"}
            ]
        }
    ''')

assert [['Earth', 1], ['Mars', 2], ['Uranus', 3], ['Jupiter', 4], ['Saturn', 5]] == GQ {
    from p in solarSystem.planets
    join m in solarSystem.moons on p.id == m.planet
    groupby p.name
    orderby count()
    select p.name, count()
}.toList()

// Collection of records

def eddyMerckx = new Cyclist( 'Eddie', 'Merckx', 'Belgium', 11 )
def bernardHinault = new Cyclist( 'Bernard', 'Hinault', 'France', 10 )
def jacquesAnquetil = new Cyclist( 'Jacques', 'Anquetil', 'France', 8 )
def chrisFroome = new Cyclist( 'Chris', 'Froome', 'Great Britain', 7 )

def cyclists = [eddyMerckx, bernardHinault, jacquesAnquetil, chrisFroome]

assert [['France', 18], ['Belgium', 11], ['Great Britain', 7]] == GQ {
    from c in cyclists
    groupby c.country
    orderby sum(c.grandTourWins) in desc
    select c.country, sum(c.grandTourWins)
}.toList()

// object creation
record HedgeHog(String name, LocalDateTime lastMeal){}

def hhNames = ['Darlene', 'Bob', 'Malcolm', 'Sven', 'June']
def hedgeHogs = GQ{
    from n in hhNames
    orderby n
    select new HedgeHog(n, LocalDateTime.now().minusHours(++_rn))       // _rn is the row number, zero based
}

def isHungry = { h -> hoursAgo(h.lastMeal()) > 2 }

// stream the results
def hungryHedgehogs =
        hedgeHogs.stream()
        .peek(h-> System.out.println( "${h.name()}'s last meal was ${dateAndHoursAgo(h.lastMeal())}" ))
        .filter( isHungry )
        .toList()

['June', 'Malcolm', 'Sven'] == hungryHedgehogs*.name()

def dateAndHoursAgo(ldt) {
    "${ldt.format(DateTimeFormatter.ofLocalizedTime(FormatStyle.SHORT))} [${hoursAgo(ldt)}]"
}

def hoursAgo(ldt) {
    ChronoUnit.HOURS.between(ldt, LocalDateTime.now())
}