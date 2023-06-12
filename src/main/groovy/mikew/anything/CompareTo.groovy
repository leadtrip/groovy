package mikew.anything

// We can use the spaceship operator <=> in groovy for referring to the compareTo method

assert 0 == ('same' <=> 'same')
assert 1 == (9 <=> 8)
assert -1 == (1 <=> 2)

/**
 * And our own implementation
 */
class CoffeeBean {
    String name
    Integer strength

    int compareTo(other) {
        this.strength <=> other.strength
    }
}

def ethiopian = new CoffeeBean(name: "eth", strength: 8)
def kenyan = new CoffeeBean(name: 'ken', strength: 10)

assert 1 == kenyan.compareTo(ethiopian)