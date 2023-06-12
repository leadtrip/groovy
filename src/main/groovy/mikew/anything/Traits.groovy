package mikew.anything

/**
 Traits are a a structural construct of the language which allow:
  * composition of behaviors
  * runtime implementation of interfaces
  * behavior overriding
 */

/**
 * Basic trait with 2 concrete methods and one abstract
 */
trait Switchable{
    void on(){
        println "${name()} on"
        running()
    }

    abstract void name()

    void running() {
        println "Hummmmmmmmmmm"
    }

    void off() {
        println "${name()} off"
    }
}

// Fridge class uses default Switchable implementation
class Fridge implements Switchable{
    @Override
    void name() {
        println "Fridge"
    }
}

def fridge = new Fridge()
fridge.on()
fridge.off()

// MassageGun overrides the on method from Switchable
class MassageGun implements Switchable{
    @Override
    void name() {
        println "Massage gun"
    }

    @Override
    void running() {
        println "Buzzzzzzzzzz"
    }
}

def massageGun = new MassageGun()
massageGun.on()
massageGun.off()