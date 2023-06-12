package mikew.anything

/**
 Traits are a a structural construct of the language which allow:
  * composition of behaviors
  * runtime implementation of interfaces
  * behavior overriding
 */

/**
 * Basic trait with 2 methods
 */
trait Switchable{
    void on(){
        println "Brrrrr"
    }

    void off() {
        println "Silence"
    }
}

// Fridge class uses default Switchable implementation
class Fridge implements Switchable{}

def fridge = new Fridge()
fridge.on()
fridge.off()

// MassageGun overrides the on method from Switchable
class MassageGun implements Switchable{
    @Override
    void on() {
        println "DDDDDDDDDDDDDDD"
    }
}

def massageGun = new MassageGun()
massageGun.on()
massageGun.off()